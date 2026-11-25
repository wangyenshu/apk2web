package android.system;

import static android.system.OsConstants.*;

import java.io.File;
import a2w.jdk.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import a2w.jdk.net.InetAddress;
import java.nio.ByteBuffer;

/**
 * android.system.Os: the POSIX calls of an app process, on the page's file system. File descriptors from open(),
 * pipe(), dup() and memfd_create() are real (an offset, flags, the file); the page has no sockets, other processes or
 * memory mappings, so those calls fail with the errno a device gives an app without that permission.
 */
public final class Os {
  private Os() {}
  static final java.util.HashMap<String, String> ENV = new java.util.HashMap<>();
  static {
    ENV.put("PATH", "/product/bin:/apex/com.android.runtime/bin:/apex/com.android.art/bin:/system_ext/bin:/system/bin:/system/xbin:/odm/bin:/vendor/bin:/vendor/xbin");
    ENV.put("ANDROID_ROOT", "/system");
    ENV.put("ANDROID_DATA", "/data");
    ENV.put("ANDROID_STORAGE", "/storage");
    ENV.put("EXTERNAL_STORAGE", "/sdcard");
    ENV.put("ASEC_MOUNTPOINT", "/mnt/asec");
    ENV.put("BOOTCLASSPATH", "/apex/com.android.art/javalib/core-oj.jar:/apex/com.android.art/javalib/core-libart.jar:/system/framework/framework.jar");
    ENV.put("ANDROID_ART_ROOT", "/apex/com.android.art");
    ENV.put("ANDROID_I18N_ROOT", "/apex/com.android.i18n");
    ENV.put("ANDROID_TZDATA_ROOT", "/apex/com.android.tzdata");
    ENV.put("TMPDIR", "/data/local/tmp");
  }

  // ---------------------------------------------------------------- open files
  /** an open file description: shared by dup()ed descriptors, as in POSIX */
  static final class Open {
    RandomAccessFile file;      // regular file
    Pipe pipe;                  // or one end of a pipe
    boolean writeEnd;
    String path;
    int flags;
    int refs = 1;
  }
  static final class Pipe {
    final java.util.ArrayDeque<Byte> bytes = new java.util.ArrayDeque<>();
    boolean readerOpen = true, writerOpen = true;
  }
  /** per descriptor: its open file description and FD_CLOEXEC */
  static final class Desc {
    Open open;
    boolean cloexec;
    int number;
  }
  private static final java.util.HashMap<Integer, FileDescriptor> TABLE = new java.util.HashMap<>();
  private static int nextFd = 3;

  static Desc desc(FileDescriptor fd) throws ErrnoException {
    if (fd == null) throw new ErrnoException("fd", EBADF);
    if (fd.handle instanceof Desc) return (Desc) fd.handle;
    // a descriptor of a stored file from elsewhere (FileInputStream.getFD(), ParcelFileDescriptor): open it now
    if (fd.path != null && Host_exists(fd.path)) {
      Desc d = new Desc();
      d.open = new Open();
      d.open.path = fd.path;
      try { d.open.file = new RandomAccessFile(fd.path, "rw"); d.open.flags = O_RDWR; }
      catch (IOException e) { try { d.open.file = new RandomAccessFile(fd.path, "r"); } catch (IOException e2) { throw new ErrnoException("open", ENOENT); } }
      register(fd, d);
      return d;
    }
    throw new ErrnoException("fd", EBADF);
  }
  private static boolean Host_exists(String path) { return new File(path).isFile(); }
  private static FileDescriptor register(FileDescriptor fd, Desc d) {
    int n = nextFd;
    while (TABLE.containsKey(n)) n++;
    nextFd = n + 1;
    d.number = n;
    fd.handle = d;
    fd.fd = n;
    TABLE.put(n, fd);
    return fd;
  }
  private static FileDescriptor newFd(Open o) {
    Desc d = new Desc();
    d.open = o;
    FileDescriptor fd = new FileDescriptor();
    fd.path = o.path;
    return register(fd, d);
  }

  public static FileDescriptor open(String path, int flags, int mode) throws ErrnoException {
    File f = new File(path);
    int acc = flags & O_ACCMODE;
    if (f.isDirectory()) {
      if (acc != O_RDONLY) throw new ErrnoException("open", EISDIR);
      Open o = new Open(); o.path = path; o.flags = flags;
      return newFd(o);
    }
    if ((flags & 040000) != 0)   // O_DIRECTORY (ARM)
    throw new ErrnoException("open", f.exists() ? ENOTDIR : ENOENT);
    boolean exists = f.isFile();
    if (exists && (flags & O_CREAT) != 0 && (flags & O_EXCL) != 0) throw new ErrnoException("open", EEXIST);
    if (!exists && (flags & O_CREAT) == 0) throw new ErrnoException("open", ENOENT);
    File parent = f.getAbsoluteFile().getParentFile();
    if (!exists && parent != null && !parent.isDirectory()) throw new ErrnoException("open", ENOENT);
    Open o = new Open();
    o.path = f.getAbsolutePath();
    o.flags = flags;
    try {
      String m = acc == O_RDONLY ? "r" : "rw";
      o.file = new RandomAccessFile(a2w.jdk.ctor.RandomAccessFile.create(f, m), m);
      if ((flags & O_TRUNC) != 0 && acc != O_RDONLY) o.file.setLength(0);
    } catch (IOException e) {
      throw new ErrnoException("open", exists ? EACCES : ENOENT);
    }
    return newFd(o);
  }
  public static FileDescriptor memfd_create(String name, int flags) throws ErrnoException {
    File dir = new File("/data/local/tmp/.memfd");
    dir.mkdirs();
    File f;
    int i = 0;
    do f = new File(dir, "memfd:" + name + (i == 0 ? "" : "." + i)); while (f.exists() && ++i > 0);
    FileDescriptor fd = open(f.getPath(), O_RDWR | O_CREAT | O_TRUNC, 0600);
    f.delete();   // anonymous, like an unlinked file (the descriptor keeps the data)
    if ((flags & MFD_CLOEXEC) != 0) ((Desc) fd.handle).cloexec = true;
    return fd;
  }
  public static void close(FileDescriptor fd) throws ErrnoException {
    Desc d = desc(fd);
    TABLE.remove(d.number);
    fd.handle = null;
    fd.fd = -1;
    Open o = d.open;
    if (--o.refs > 0) return;
    if (o.file != null) try { o.file.close(); } catch (IOException e) { throw new ErrnoException("close", EIO); }
    if (o.pipe != null) { if (o.writeEnd) o.pipe.writerOpen = false; else o.pipe.readerOpen = false; }
  }
  public static FileDescriptor dup(FileDescriptor oldFd) throws ErrnoException {
    Desc d = desc(oldFd);
    d.open.refs++;
    return newFd(d.open);
  }
  public static FileDescriptor dup2(FileDescriptor oldFd, int newFd) throws ErrnoException {
    Desc d = desc(oldFd);
    if (newFd < 0) throw new ErrnoException("dup2", EBADF);
    if (newFd == d.number) return oldFd;
    FileDescriptor prev = TABLE.get(newFd);
    if (prev != null) close(prev);
    d.open.refs++;
    Desc nd = new Desc();
    nd.open = d.open;
    nd.number = newFd;
    FileDescriptor fd = new FileDescriptor();
    fd.path = d.open.path;
    fd.handle = nd;
    fd.fd = newFd;
    TABLE.put(newFd, fd);
    return fd;
  }
  public static FileDescriptor[] pipe() throws ErrnoException {
    Pipe p = new Pipe();
    Open r = new Open(), w = new Open();
    r.pipe = w.pipe = p;
    w.writeEnd = true;
    r.flags = O_RDONLY;
    w.flags = O_WRONLY;
    return new FileDescriptor[] {newFd(r), newFd(w)};
  }
  public static int fcntlInt(FileDescriptor fd, int cmd, int arg) throws ErrnoException {
    Desc d = desc(fd);
    switch (cmd) {
      case F_GETFD: return d.cloexec ? FD_CLOEXEC : 0;
      case F_SETFD: d.cloexec = (arg & FD_CLOEXEC) != 0; return 0;
      case F_GETFL: return d.open.flags;
      case F_SETFL: d.open.flags = d.open.flags & O_ACCMODE | arg & (O_APPEND | O_NONBLOCK); return 0;
      case F_DUPFD: case F_DUPFD_CLOEXEC: {
        int n = Math.max(arg, 0);
        while (TABLE.containsKey(n)) n++;
        FileDescriptor nfd = dup2(fd, n);
        ((Desc) nfd.handle).cloexec = cmd == F_DUPFD_CLOEXEC;
        return n;
      }
      case F_GETOWN: return 0;
      default: throw new ErrnoException("fcntl", EINVAL);
    }
  }

  // ---------------------------------------------------------------- reading and writing
  public static int read(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount) throws ErrnoException {
    java.util.Objects.checkFromIndexSize(byteOffset, byteCount, bytes.length);
    Desc d = desc(fd);
    Open o = d.open;
    if ((o.flags & O_ACCMODE) == O_WRONLY) throw new ErrnoException("read", EBADF);
    if (o.pipe != null) {
      if (o.pipe.bytes.isEmpty()) {
        if (!o.pipe.writerOpen) return 0;
        throw new ErrnoException("read", EAGAIN);   // nothing can arrive while this call waits: the page has one thread of control
      }
      int n = 0;
      while (n < byteCount && !o.pipe.bytes.isEmpty()) bytes[byteOffset + n++] = o.pipe.bytes.poll();
      return n;
    }
    if (o.file == null) throw new ErrnoException("read", EISDIR);
    try { int n = o.file.read(bytes, byteOffset, byteCount); return n < 0 ? 0 : n; } catch (IOException e) { throw new ErrnoException("read", EIO); }
  }
  public static int read(FileDescriptor fd, ByteBuffer buffer) throws ErrnoException {
    byte[] b = new byte[buffer.remaining()];
    int n = read(fd, b, 0, b.length);
    buffer.put(b, 0, n);
    return n;
  }
  public static int write(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount) throws ErrnoException {
    java.util.Objects.checkFromIndexSize(byteOffset, byteCount, bytes.length);
    Desc d = desc(fd);
    Open o = d.open;
    if ((o.flags & O_ACCMODE) == O_RDONLY) throw new ErrnoException("write", EBADF);
    if (o.pipe != null) {
      if (!o.pipe.readerOpen) throw new ErrnoException("write", EPIPE);
      for (int i = 0; i < byteCount; i++) o.pipe.bytes.add(bytes[byteOffset + i]);
      return byteCount;
    }
    try {
      if ((o.flags & O_APPEND) != 0) o.file.seek(o.file.length());
      o.file.write(bytes, byteOffset, byteCount);
      return byteCount;
    } catch (IOException e) { throw new ErrnoException("write", ENOSPC); }
  }
  public static int write(FileDescriptor fd, ByteBuffer buffer) throws ErrnoException {
    byte[] b = new byte[buffer.remaining()];
    buffer.duplicate().get(b);
    int n = write(fd, b, 0, b.length);
    buffer.position(buffer.position() + n);
    return n;
  }
  private static RandomAccessFile file(FileDescriptor fd, String call) throws ErrnoException {
    Desc d = desc(fd);
    if (d.open.pipe != null) throw new ErrnoException(call, ESPIPE);
    if (d.open.file == null) throw new ErrnoException(call, EISDIR);
    return d.open.file;
  }
  public static int pread(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, long offset) throws ErrnoException {
    java.util.Objects.checkFromIndexSize(byteOffset, byteCount, bytes.length);
    RandomAccessFile f = file(fd, "pread");
    if (offset < 0) throw new ErrnoException("pread", EINVAL);
    try {
      long pos = f.getFilePointer();
      f.seek(offset);
      int n = f.read(bytes, byteOffset, byteCount);
      f.seek(pos);
      return n < 0 ? 0 : n;
    } catch (IOException e) { throw new ErrnoException("pread", EIO); }
  }
  public static int pread(FileDescriptor fd, ByteBuffer buffer, long offset) throws ErrnoException {
    byte[] b = new byte[buffer.remaining()];
    int n = pread(fd, b, 0, b.length, offset);
    buffer.put(b, 0, n);
    return n;
  }
  public static int pwrite(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, long offset) throws ErrnoException {
    java.util.Objects.checkFromIndexSize(byteOffset, byteCount, bytes.length);
    RandomAccessFile f = file(fd, "pwrite");
    if (offset < 0) throw new ErrnoException("pwrite", EINVAL);
    if ((desc(fd).open.flags & O_ACCMODE) == O_RDONLY) throw new ErrnoException("pwrite", EBADF);
    try {
      long pos = f.getFilePointer();
      f.seek(offset);
      f.write(bytes, byteOffset, byteCount);
      f.seek(pos);
      return byteCount;
    } catch (IOException e) { throw new ErrnoException("pwrite", ENOSPC); }
  }
  public static int pwrite(FileDescriptor fd, ByteBuffer buffer, long offset) throws ErrnoException {
    byte[] b = new byte[buffer.remaining()];
    buffer.duplicate().get(b);
    int n = pwrite(fd, b, 0, b.length, offset);
    buffer.position(buffer.position() + n);
    return n;
  }
  /** buffers: byte[] or ByteBuffer, with offsets and counts */
  public static int readv(FileDescriptor fd, Object[] buffers, int[] offsets, int[] byteCounts) throws ErrnoException {
    int total = 0;
    for (int i = 0; i < buffers.length; i++) {
      int n;
      if (buffers[i] instanceof ByteBuffer) { ByteBuffer b = ((ByteBuffer) buffers[i]).duplicate(); b.position(offsets[i]); b.limit(offsets[i] + byteCounts[i]); n = read(fd, b); }
      else n = read(fd, (byte[]) buffers[i], offsets[i], byteCounts[i]);
      total += n;
      if (n < byteCounts[i]) break;
    }
    return total;
  }
  public static int writev(FileDescriptor fd, Object[] buffers, int[] offsets, int[] byteCounts) throws ErrnoException {
    int total = 0;
    for (int i = 0; i < buffers.length; i++) {
      if (buffers[i] instanceof ByteBuffer) { ByteBuffer b = ((ByteBuffer) buffers[i]).duplicate(); b.position(offsets[i]); b.limit(offsets[i] + byteCounts[i]); total += write(fd, b); }
      else total += write(fd, (byte[]) buffers[i], offsets[i], byteCounts[i]);
    }
    return total;
  }
  public static long lseek(FileDescriptor fd, long offset, int whence) throws ErrnoException {
    RandomAccessFile f = file(fd, "lseek");
    try {
      long base = whence == SEEK_SET ? 0 : whence == SEEK_CUR ? f.getFilePointer() : whence == SEEK_END ? f.length() : -1;
      if (base < 0) throw new ErrnoException("lseek", EINVAL);
      long to = base + offset;
      if (to < 0) throw new ErrnoException("lseek", EINVAL);
      f.seek(to);
      return to;
    } catch (IOException e) { throw new ErrnoException("lseek", EIO); }
  }
  public static void ftruncate(FileDescriptor fd, long length) throws ErrnoException {
    RandomAccessFile f = file(fd, "ftruncate");
    if (length < 0) throw new ErrnoException("ftruncate", EINVAL);
    if ((desc(fd).open.flags & O_ACCMODE) == O_RDONLY) throw new ErrnoException("ftruncate", EINVAL);
    try { f.setLength(length); } catch (IOException e) { throw new ErrnoException("ftruncate", EIO); }
  }
  public static void posix_fallocate(FileDescriptor fd, long offset, long length) throws ErrnoException {
    RandomAccessFile f = file(fd, "posix_fallocate");
    if (offset < 0 || length <= 0) throw new ErrnoException("posix_fallocate", EINVAL);
    try { if (f.length() < offset + length) f.setLength(offset + length); } catch (IOException e) { throw new ErrnoException("posix_fallocate", ENOSPC); }
  }
  /** copies byteCount bytes from inFd (at *offset, or its own position) to outFd */
  public static long sendfile(FileDescriptor outFd, FileDescriptor inFd, Int64Ref offset, long byteCount) throws ErrnoException {
    byte[] buf = new byte[(int) Math.min(byteCount, 1 << 16)];
    long done = 0;
    while (done < byteCount) {
      int want = (int) Math.min(buf.length, byteCount - done);
      int n = offset != null ? pread(inFd, buf, 0, want, offset.value + done) : read(inFd, buf, 0, want);
      if (n <= 0) break;
      write(outFd, buf, 0, n);
      done += n;
    }
    if (offset != null) offset.value += done;
    return done;
  }
  public static long sendfile(FileDescriptor outFd, FileDescriptor inFd, android.util.MutableLong offset, long byteCount) throws ErrnoException {
    Int64Ref r = offset == null ? null : new Int64Ref(offset.value);
    long n = sendfile(outFd, inFd, r, byteCount);
    if (offset != null) offset.value = r.value;
    return n;
  }
  public static void fsync(FileDescriptor fd) throws ErrnoException { desc(fd); }
  public static void fdatasync(FileDescriptor fd) throws ErrnoException { desc(fd); }
  public static boolean isatty(FileDescriptor fd) { return false; }
  public static int poll(StructPollfd[] fds, int timeoutMs) throws ErrnoException {
    int ready = 0;
    for (StructPollfd p : fds) {
      p.revents = 0;
      if (p.fd == null) continue;
      Desc d;
      try { d = desc(p.fd); } catch (ErrnoException e) { p.revents = (short) POLLNVAL; ready++; continue; }
      Open o = d.open;
      int ev = 0;
      if (o.pipe != null) {
        if (!o.writeEnd && (!o.pipe.bytes.isEmpty())) ev |= POLLIN | POLLRDNORM;
        if (!o.writeEnd && !o.pipe.writerOpen) ev |= POLLHUP;
        if (o.writeEnd) ev |= o.pipe.readerOpen ? POLLOUT | POLLWRNORM : POLLERR;
      } else ev |= POLLIN | POLLRDNORM | POLLOUT | POLLWRNORM;   // regular files are always ready
      p.revents = (short) (ev & (p.events | POLLERR | POLLHUP | POLLNVAL));
      if (p.revents != 0) ready++;
    }
    return ready;
  }

  // ---------------------------------------------------------------- file metadata
  public static StructStat stat(String path) throws ErrnoException {
    File f = new File(path);
    if (!f.exists()) throw new ErrnoException("stat", ENOENT);
    return statOf(f);
  }
  static StructStat statOf(File f) {
    int mode = f.isDirectory() ? S_IFDIR | 0771 : S_IFREG | 0660;
    long ms = f.lastModified();
    StructTimespec t = new StructTimespec(ms / 1000, (ms % 1000) * 1000000);
    long size = f.isDirectory() ? 4096 : f.length();
    return new StructStat(0xfd00, f.getAbsolutePath().hashCode() & 0xffffffffL, mode, f.isDirectory() ? 2 : 1, getuid(), getgid(), 0, size, t, t, t, 4096, (size + 511) / 512);
  }
  public static StructStat lstat(String path) throws ErrnoException { return stat(path); }
  public static StructStat fstat(FileDescriptor fd) throws ErrnoException {
    Desc d = desc(fd);
    Open o = d.open;
    if (o.pipe != null) {
      StructTimespec t = new StructTimespec(System.currentTimeMillis() / 1000, 0);
      return new StructStat(0xc, d.number, S_IFIFO | 0600, 1, getuid(), getgid(), 0, 0, t, t, t, 4096, 0);
    }
    if (o.file != null && !new File(o.path).exists()) {   // unlinked (memfd): the open file's own size
      long size;
      try { size = o.file.length(); } catch (IOException e) { size = 0; }
      StructTimespec t = new StructTimespec(System.currentTimeMillis() / 1000, 0);
      return new StructStat(0x1, d.number, S_IFREG | 0600, 0, getuid(), getgid(), 0, size, t, t, t, 4096, (size + 511) / 512);
    }
    StructStat st = statOf(new File(o.path));
    if (o.file == null) return st;
    long size;
    try { size = o.file.length(); } catch (IOException e) { size = st.st_size; }
    return new StructStat(st.st_dev, st.st_ino, st.st_mode, st.st_nlink, st.st_uid, st.st_gid, st.st_rdev, size, st.st_atim, st.st_mtim, st.st_ctim, st.st_blksize, (size + 511) / 512);
  }
  public static StructStatVfs statvfs(String path) throws ErrnoException {
    if (!new File(path).exists()) throw new ErrnoException("statvfs", ENOENT);
    return vfs();
  }
  public static StructStatVfs fstatvfs(FileDescriptor fd) throws ErrnoException { desc(fd); return vfs(); }
  /** the page's storage: 4 KiB blocks, as much free as File.getFreeSpace() reports */
  private static StructStatVfs vfs() {
    File root = new File("/data");
    long total = Math.max(root.getTotalSpace(), 1L << 32), free = Math.max(root.getFreeSpace(), 0);
    long bs = 4096;
    return new StructStatVfs(bs, bs, total / bs, free / bs, free / bs, 1 << 20, 1 << 19, 1 << 19, 0xfd00, ST_NOSUID | ST_NODEV | ST_RELATIME, 255);
  }
  public static boolean access(String path, int mode) throws ErrnoException {
    if (!new File(path).exists()) throw new ErrnoException("access", ENOENT);
    return true;
  }
  public static void mkdir(String path, int mode) throws ErrnoException {
    File f = new File(path);
    if (f.exists()) throw new ErrnoException("mkdir", EEXIST);
    if (!f.mkdir()) throw new ErrnoException("mkdir", ENOENT);
  }
  public static void mkfifo(String path, int mode) throws ErrnoException {
    // a FIFO needs a second process at the other end; the page has none
    throw new ErrnoException("mkfifo", EPERM);
  }
  public static void remove(String path) throws ErrnoException {
    File f = new File(path);
    if (!f.exists()) throw new ErrnoException("remove", ENOENT);
    if (!f.delete()) throw new ErrnoException("remove", f.isDirectory() ? ENOTEMPTY : EACCES);
  }
  public static void rmdir(String path) throws ErrnoException {
    File f = new File(path);
    if (!f.exists()) throw new ErrnoException("rmdir", ENOENT);
    if (!f.isDirectory()) throw new ErrnoException("rmdir", ENOTDIR);
    if (!f.delete()) throw new ErrnoException("rmdir", ENOTEMPTY);
  }
  public static void rename(String oldPath, String newPath) throws ErrnoException {
    File from = new File(oldPath), to = new File(newPath);
    if (!from.exists()) throw new ErrnoException("rename", ENOENT);
    if (to.isFile() && from.isFile()) to.delete();
    if (!from.renameTo(to)) throw new ErrnoException("rename", ENOENT);
  }
  /** no hard links: copies the file (later writes to one do not show in the other) */
  public static void link(String oldPath, String newPath) throws ErrnoException {
    File from = new File(oldPath), to = new File(newPath);
    if (!from.isFile()) throw new ErrnoException("link", from.exists() ? EPERM : ENOENT);
    if (to.exists()) throw new ErrnoException("link", EEXIST);
    throw new ErrnoException("link", EPERM);
  }
  public static void chmod(String path, int mode) throws ErrnoException { if (!new File(path).exists()) throw new ErrnoException("chmod", ENOENT); }
  public static void fchmod(FileDescriptor fd, int mode) throws ErrnoException { desc(fd); }
  private static void owner(String call, int uid, int gid) throws ErrnoException {
    if (uid != -1 && uid != getuid() || gid != -1 && gid != getgid()) throw new ErrnoException(call, EPERM);
  }
  public static void chown(String path, int uid, int gid) throws ErrnoException { if (!new File(path).exists()) throw new ErrnoException("chown", ENOENT); owner("chown", uid, gid); }
  public static void lchown(String path, int uid, int gid) throws ErrnoException { if (!new File(path).exists()) throw new ErrnoException("lchown", ENOENT); owner("lchown", uid, gid); }
  public static void fchown(FileDescriptor fd, int uid, int gid) throws ErrnoException { desc(fd); owner("fchown", uid, gid); }
  public static void symlink(String oldPath, String newPath) throws ErrnoException { throw new ErrnoException("symlink", EPERM); }
  public static String readlink(String path) throws ErrnoException {
    if (!new File(path).exists()) throw new ErrnoException("readlink", ENOENT);
    throw new ErrnoException("readlink", EINVAL);   // nothing is a symbolic link
  }
  public static String realpath(String path) throws ErrnoException {
    File f = new File(path);
    if (!f.exists()) throw new ErrnoException("realpath", ENOENT);
    try { return f.getCanonicalPath(); } catch (IOException e) { throw new ErrnoException("realpath", ENOENT); }
  }
  public static int umask(int mask) { int old = umask; umask = mask & 0777; return old; }
  private static int umask = 077;
  /** extended attributes: the page's file system has none */
  public static byte[] getxattr(String path, String name) throws ErrnoException { exists("getxattr", path); throw new ErrnoException("getxattr", ENODATA); }
  public static String[] listxattr(String path) throws ErrnoException { exists("listxattr", path); return new String[0]; }
  public static void removexattr(String path, String name) throws ErrnoException { exists("removexattr", path); throw new ErrnoException("removexattr", ENODATA); }
  public static void setxattr(String path, String name, byte[] value, int flags) throws ErrnoException { exists("setxattr", path); throw new ErrnoException("setxattr", ENOTSUP); }
  private static void exists(String call, String path) throws ErrnoException { if (!new File(path).exists()) throw new ErrnoException(call, ENOENT); }

  // ---------------------------------------------------------------- the process
  public static String getenv(String name) { return ENV.get(name); }
  public static String[] environ() { java.util.ArrayList<String> l = new java.util.ArrayList<>(); for (java.util.Map.Entry<String, String> e : ENV.entrySet()) l.add(e.getKey() + "=" + e.getValue()); return l.toArray(new String[0]); }
  public static void setenv(String name, String value, boolean overwrite) throws ErrnoException {
    if (name == null || name.isEmpty() || name.indexOf('=') >= 0) throw new ErrnoException("setenv", EINVAL);
    if (overwrite || !ENV.containsKey(name)) ENV.put(name, value);
  }
  public static void unsetenv(String name) throws ErrnoException {
    if (name == null || name.isEmpty() || name.indexOf('=') >= 0) throw new ErrnoException("unsetenv", EINVAL);
    ENV.remove(name);
  }
  public static int getpid() { return android.os.Process.myPid(); }
  public static int getppid() { return 1; }
  public static int gettid() { return android.os.Process.myTid(); }
  public static int getuid() { return android.os.Process.myUid(); }
  public static int geteuid() { return android.os.Process.myUid(); }
  public static int getgid() { return android.os.Process.myUid(); }
  public static int getegid() { return android.os.Process.myUid(); }
  /** an app may only "change" to the ids it has */
  public static void setuid(int uid) throws ErrnoException { if (uid != getuid()) throw new ErrnoException("setuid", EPERM); }
  public static void seteuid(int uid) throws ErrnoException { if (uid != getuid()) throw new ErrnoException("seteuid", EPERM); }
  public static void setgid(int gid) throws ErrnoException { if (gid != getgid()) throw new ErrnoException("setgid", EPERM); }
  public static void setegid(int gid) throws ErrnoException { if (gid != getgid()) throw new ErrnoException("setegid", EPERM); }
  public static int setsid() throws ErrnoException { throw new ErrnoException("setsid", EPERM); }   // already a process group leader
  public static void kill(int pid, int signal) throws ErrnoException {
    if (pid != getpid() && pid != 0) throw new ErrnoException("kill", ESRCH);
    if (signal == SIGKILL || signal == SIGTERM) android.os.Process.killProcess(getpid());
  }
  public static int waitpid(int pid, android.util.MutableInt status, int options) throws ErrnoException { throw new ErrnoException("waitpid", ECHILD); }
  public static void execv(String filename, String[] argv) throws ErrnoException { throw new ErrnoException("execv", new File(filename).exists() ? EACCES : ENOENT); }
  public static void execve(String filename, String[] argv, String[] envp) throws ErrnoException { throw new ErrnoException("execve", new File(filename).exists() ? EACCES : ENOENT); }
  private static int dumpable = 1;
  public static int prctl(int option, long arg2, long arg3, long arg4, long arg5) throws ErrnoException {
    if (option == PR_GET_DUMPABLE) return dumpable;
    if (option == PR_SET_DUMPABLE) { if (arg2 != 0 && arg2 != 1) throw new ErrnoException("prctl", EINVAL); dumpable = (int) arg2; return 0; }
    if (option == PR_SET_NO_NEW_PRIVS) { if (arg2 != 1) throw new ErrnoException("prctl", EINVAL); return 0; }
    throw new ErrnoException("prctl", EINVAL);
  }
  public static long sysconf(int name) {
    switch (name) {
      case _SC_PAGESIZE: case _SC_PAGE_SIZE: return 4096;
      case _SC_NPROCESSORS_CONF: case _SC_NPROCESSORS_ONLN: return Runtime.getRuntime().availableProcessors();
      case _SC_CLK_TCK: return 100;
      case _SC_PHYS_PAGES: return (1L << 30) / 4096;
      case _SC_AVPHYS_PAGES: return Runtime.getRuntime().freeMemory() / 4096;
      case _SC_ARG_MAX: return 131072;
      case _SC_CHILD_MAX: return 8192;
      case _SC_OPEN_MAX: return 32768;
      case _SC_NGROUPS_MAX: return 65536;
      case _SC_LINE_MAX: return 2048;
      case _SC_IOV_MAX: return 1024;
      case _SC_STREAM_MAX: return 8;
      case _SC_TZNAME_MAX: return -1;
      case _SC_LOGIN_NAME_MAX: return 256;
      case _SC_TTY_NAME_MAX: return 32;
      case _SC_GETPW_R_SIZE_MAX: case _SC_GETGR_R_SIZE_MAX: return 1024;
      case _SC_THREAD_STACK_MIN: return 16384;
      case _SC_THREAD_KEYS_MAX: return 128;
      case _SC_THREAD_DESTRUCTOR_ITERATIONS: return 4;
      case _SC_THREAD_THREADS_MAX: return -1;
      case _SC_RTSIG_MAX: return 32;
      case _SC_SIGQUEUE_MAX: return 8192;
      case _SC_SEM_VALUE_MAX: return 0x3fffffff;
      case _SC_BC_BASE_MAX: case _SC_BC_DIM_MAX: case _SC_BC_SCALE_MAX: case _SC_COLL_WEIGHTS_MAX: case _SC_EXPR_NEST_MAX: case _SC_RE_DUP_MAX: return name == _SC_BC_DIM_MAX ? 2048 : name == _SC_BC_STRING_MAX ? 1000 : name == _SC_COLL_WEIGHTS_MAX ? 2 : name == _SC_EXPR_NEST_MAX ? 32 : name == _SC_RE_DUP_MAX ? 255 : 99;
      case _SC_BC_STRING_MAX: return 1000;
      case _SC_ATEXIT_MAX: return 2147483647;
      case _SC_VERSION: case _SC_2_VERSION: case _SC_2_C_BIND: case _SC_ASYNCHRONOUS_IO: case _SC_FSYNC: case _SC_MAPPED_FILES: case _SC_MEMLOCK:
      case _SC_MEMLOCK_RANGE: case _SC_MEMORY_PROTECTION: case _SC_PRIORITY_SCHEDULING: case _SC_REALTIME_SIGNALS: case _SC_SEMAPHORES:
      case _SC_SYNCHRONIZED_IO: case _SC_TIMERS: case _SC_THREADS: case _SC_THREAD_ATTR_STACKADDR: case _SC_THREAD_ATTR_STACKSIZE:
      case _SC_THREAD_PRIORITY_SCHEDULING: case _SC_THREAD_PRIO_INHERIT: case _SC_THREAD_PRIO_PROTECT: case _SC_THREAD_SAFE_FUNCTIONS:
      case _SC_SHARED_MEMORY_OBJECTS: return 200809;
      case _SC_JOB_CONTROL: case _SC_SAVED_IDS: case _SC_XOPEN_ENH_I18N: case _SC_XOPEN_UNIX: case _SC_XOPEN_REALTIME: case _SC_XOPEN_REALTIME_THREADS: return 1;
      case _SC_XOPEN_VERSION: return 700;
      case _SC_XOPEN_XCU_VERSION: return 4;
      default: return -1;
    }
  }
  public static StructUtsname uname() { return new StructUtsname("Linux", "localhost", "4.19.191", "#1 SMP PREEMPT", "armv7l"); }
  public static String strerror(int errno) { return OsConstants.strerror(errno); }
  public static String gai_strerror(int error) { return OsConstants.gaiStrerror(error); }
  public static String strsignal(int signal) {
    String[] names = {"Unknown signal 0", "Hangup", "Interrupt", "Quit", "Illegal instruction", "Trace/breakpoint trap", "Aborted", "Bus error",
        "Floating point exception", "Killed", "User signal 1", "Segmentation fault", "User signal 2", "Broken pipe", "Alarm clock", "Terminated",
        "Stack fault", "Child exited", "Continued", "Stopped (signal)", "Stopped", "Stopped (tty input)", "Stopped (tty output)", "Urgent I/O condition",
        "CPU time limit exceeded", "File size limit exceeded", "Virtual timer expired", "Profiling timer expired", "Window size changed", "I/O possible",
        "Power failure", "Bad system call"};
    if (signal >= 0 && signal < names.length) return names[signal];
    if (signal >= 32 && signal <= SIGRTMAX) return "Real-time signal " + (signal - 32);
    return "Unknown signal " + signal;
  }

  // ---------------------------------------------------------------- memory
  /** the app's memory is not addressable from Java on the page: no mappings */
  public static long mmap(long address, long byteCount, int prot, int flags, FileDescriptor fd, long offset) throws ErrnoException { throw new ErrnoException("mmap", ENODEV); }
  public static void munmap(long address, long byteCount) throws ErrnoException { throw new ErrnoException("munmap", EINVAL); }
  public static void msync(long address, long byteCount, int flags) throws ErrnoException { throw new ErrnoException("msync", ENOMEM); }
  public static void mlock(long address, long byteCount) throws ErrnoException { throw new ErrnoException("mlock", ENOMEM); }
  public static void munlock(long address, long byteCount) throws ErrnoException { throw new ErrnoException("munlock", ENOMEM); }
  public static void mincore(long address, long byteCount, byte[] vector) throws ErrnoException { throw new ErrnoException("mincore", ENOMEM); }

  // ---------------------------------------------------------------- terminals
  public static void tcdrain(FileDescriptor fd) throws ErrnoException { desc(fd); throw new ErrnoException("tcdrain", ENOTTY); }
  public static void tcsendbreak(FileDescriptor fd, int duration) throws ErrnoException { desc(fd); throw new ErrnoException("tcsendbreak", ENOTTY); }

  // ---------------------------------------------------------------- sockets: the page can open none
  public static FileDescriptor socket(int domain, int type, int protocol) throws ErrnoException { throw new ErrnoException("socket", EACCES); }
  public static void socketpair(int domain, int type, int protocol, FileDescriptor fd1, FileDescriptor fd2) throws ErrnoException { throw new ErrnoException("socketpair", EACCES); }
  private static ErrnoException notSocket(String call, FileDescriptor fd) throws ErrnoException { desc(fd); return new ErrnoException(call, ENOTSOCK); }
  public static FileDescriptor accept(FileDescriptor fd, a2w.jdk.net.InetSocketAddress peerAddress) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("accept", fd); }
  public static void bind(FileDescriptor fd, InetAddress address, int port) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("bind", fd); }
  public static void bind(FileDescriptor fd, a2w.jdk.net.SocketAddress address) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("bind", fd); }
  public static void connect(FileDescriptor fd, InetAddress address, int port) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("connect", fd); }
  public static void connect(FileDescriptor fd, a2w.jdk.net.SocketAddress address) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("connect", fd); }
  public static void listen(FileDescriptor fd, int backlog) throws ErrnoException { throw notSocket("listen", fd); }
  public static void shutdown(FileDescriptor fd, int how) throws ErrnoException { throw notSocket("shutdown", fd); }
  public static a2w.jdk.net.SocketAddress getpeername(FileDescriptor fd) throws ErrnoException { throw notSocket("getpeername", fd); }
  public static a2w.jdk.net.SocketAddress getsockname(FileDescriptor fd) throws ErrnoException { throw notSocket("getsockname", fd); }
  public static StructTimeval getsockoptTimeval(FileDescriptor fd, int level, int option) throws ErrnoException { throw notSocket("getsockopt", fd); }
  public static void setsockoptInt(FileDescriptor fd, int level, int option, int value) throws ErrnoException { throw notSocket("setsockopt", fd); }
  public static void setsockoptTimeval(FileDescriptor fd, int level, int option, StructTimeval value) throws ErrnoException { throw notSocket("setsockopt", fd); }
  public static int recvfrom(FileDescriptor fd, ByteBuffer buffer, int flags, a2w.jdk.net.InetSocketAddress srcAddress) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("recvfrom", fd); }
  public static int recvfrom(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, a2w.jdk.net.InetSocketAddress srcAddress) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("recvfrom", fd); }
  public static int recvmsg(FileDescriptor fd, StructMsghdr msg, int flags) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("recvmsg", fd); }
  public static int sendmsg(FileDescriptor fd, StructMsghdr msg, int flags) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("sendmsg", fd); }
  public static int sendto(FileDescriptor fd, ByteBuffer buffer, int flags, InetAddress inetAddress, int port) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("sendto", fd); }
  public static int sendto(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, InetAddress inetAddress, int port) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("sendto", fd); }
  public static int sendto(FileDescriptor fd, byte[] bytes, int byteOffset, int byteCount, int flags, a2w.jdk.net.SocketAddress address) throws ErrnoException, a2w.jdk.net.SocketException { throw notSocket("sendto", fd); }
  /** the loopback interface is the only one */
  public static int if_nametoindex(String name) { return "lo".equals(name) ? 1 : 0; }
  public static String if_indextoname(int index) { return index == 1 ? "lo" : null; }
}

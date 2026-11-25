package a2w;

import java.io.IOException;
import org.teavm.runtime.fs.VirtualFile;
import org.teavm.runtime.fs.VirtualFileAccessor;
import org.teavm.runtime.fs.VirtualFileSystem;

/**
 * The page's file system (HW.fs: the emulator's MEMFS, persisted in IndexedDB) as TeaVM's virtual file system, so
 * java.io.File, FileInputStream, RandomAccessFile, ZipFile... work on the same files as the native code.
 */
public final class Vfs implements VirtualFileSystem {
  public static void install() { org.teavm.runtime.fs.VirtualFileSystemProvider.setInstance(new Vfs()); }

  @Override public String getUserDir() { return "/"; }
  @Override public boolean isWindows() { return false; }
  @Override public String[] getRoots() { return new String[] {"/"}; }
  @Override public String canonicalize(String path) {
    String[] parts = path.split("/");
    java.util.ArrayList<String> out = new java.util.ArrayList<>();
    for (String p : parts) {
      if (p.isEmpty() || p.equals(".")) continue;
      if (p.equals("..")) { if (!out.isEmpty()) out.remove(out.size() - 1); continue; }
      out.add(p);
    }
    return "/" + String.join("/", out);
  }
  @Override public VirtualFile getFile(String path) { return new F(canonicalize(path)); }

  static final class F implements VirtualFile {
    final String path;
    F(String p) { path = p; }
    String child(String name) { return path.equals("/") ? "/" + name : path + "/" + name; }
    @Override public String getName() { int i = path.lastIndexOf('/'); return path.substring(i + 1); }
    @Override public boolean isDirectory() { return path.equals("/") || Host.dirExists(path); }
    @Override public boolean isFile() { return Host.fileExists(path); }
    @Override public String[] listFiles() {
      if (!isDirectory()) return null;
      String s = Host.listDir(path);
      return s == null || s.isEmpty() ? new String[0] : s.split("\n");
    }
    @Override public VirtualFileAccessor createAccessor(boolean readable, boolean writable, boolean append) {
      if (!isFile()) {
        if (!writable) return null;
        String parent = path.substring(0, Math.max(1, path.lastIndexOf('/')));
        if (!Host.dirExists(parent) && !parent.equals("/")) return null;
        Host.fileWrite(path, new byte[0], false);
      }
      return new Acc(path, append);
    }
    @Override public boolean createFile(String name) throws IOException {
      String p = child(name);
      if (Host.fileExists(p) || Host.dirExists(p)) return false;
      Host.fileWrite(p, new byte[0], false);
      return true;
    }
    @Override public boolean createDirectory(String name) {
      String p = child(name);
      if (Host.fileExists(p) || Host.dirExists(p)) return false;
      Host.dirCreate(p);
      return true;
    }
    @Override public boolean delete() {
      if (Host.fileExists(path)) { Host.fileDelete(path); return true; }
      if (Host.dirExists(path)) {
        String[] l = listFiles();
        if (l != null && l.length > 0) return false;
        Host.dirDelete(path);
        return true;
      }
      return false;
    }
    @Override public boolean adopt(VirtualFile file, String name) {
      if (!(file instanceof F)) return false;
      F f = (F) file;
      if (!Host.fileExists(f.path)) return false;
      Host.fileWrite(child(name), Host.fileRead(f.path), false);
      Host.fileDelete(f.path);
      return true;
    }
    @Override public boolean canRead() { return isFile() || isDirectory(); }
    @Override public boolean canWrite() { return canRead(); }
    @Override public long lastModified() { return (long) Host.fileTime(path); }
    @Override public boolean setLastModified(long t) { return canRead(); }
    @Override public boolean setReadOnly(boolean r) { return canRead(); }
    @Override public int length() { return isFile() ? Host.fileLength(path) : 0; }
  }

  /** whole-file buffer: read on open, written back on flush/close */
  static final class Acc implements VirtualFileAccessor {
    final String path;
    byte[] data;
    int size, pos;
    boolean dirty;
    Acc(String p, boolean append) {
      path = p;
      data = Host.fileRead(p);
      size = data.length;
      if (append) pos = size;
    }
    void ensure(int n) { if (n > data.length) data = java.util.Arrays.copyOf(data, Math.max(n, data.length * 2 + 16)); }
    @Override public int read(byte[] b, int off, int len) {
      if (pos >= size) return -1;
      int n = Math.min(len, size - pos);
      System.arraycopy(data, pos, b, off, n);
      pos += n;
      return n;
    }
    @Override public void write(byte[] b, int off, int len) {
      ensure(pos + len);
      System.arraycopy(b, off, data, pos, len);
      pos += len;
      if (pos > size) size = pos;
      dirty = true;
    }
    @Override public int tell() { return pos; }
    @Override public void seek(int p) { pos = p; }
    @Override public void skip(int n) { pos += n; }
    @Override public int size() { return size; }
    @Override public void resize(int n) { ensure(n); size = n; if (pos > n) pos = n; dirty = true; }
    @Override public void close() { flush(); }
    @Override public void flush() {
      if (!dirty) return;
      Host.fileWrite(path, size == data.length ? data : java.util.Arrays.copyOf(data, size), false);
      dirty = false;
    }
  }
}

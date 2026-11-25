package a2w.jdk;

import a2w.Host;
import java.io.*;

public class FileInputStream extends InputStream {
  private final byte[] data;
  private int pos, mark;
  private final FileDescriptor fd;

  public FileInputStream(File f) throws FileNotFoundException {
    if (!f.isFile()) throw new FileNotFoundException(f.getPath() + ": open failed: ENOENT (No such file or directory)");
    data = Host.fileRead(f.getAbsolutePath());
    fd = new FileDescriptor(); fd.path = f.getAbsolutePath();
  }
  public FileInputStream(String p) throws FileNotFoundException { this(new File(p)); }
  public FileInputStream(FileDescriptor fd) {
    this.fd = fd;
    data = fd.data != null ? fd.data : (fd.path != null ? Host.fileRead(fd.path) : new byte[0]);
  }
  /** Web: a stream over bytes that pretends to be a file (assets). */
  public FileInputStream(byte[] bytes, int off, int len) {
    if (off == 0 && len == bytes.length) data = bytes;
    else { data = new byte[len]; System.arraycopy(bytes, off, data, 0, len); }
    fd = new FileDescriptor(); fd.data = data;
  }
  public final FileDescriptor getFD() { return fd; }
  public byte[] buffer() { return data; }
  @Override public int read() { return pos < data.length ? data[pos++] & 0xff : -1; }
  @Override public int read(byte[] b, int off, int len) {
    if (len == 0) return 0;
    if (pos >= data.length) return -1;
    int n = Math.min(len, data.length - pos);
    System.arraycopy(data, pos, b, off, n);
    pos += n;
    return n;
  }
  @Override public long skip(long n) { long k = Math.max(0, Math.min(n, data.length - pos)); pos += (int) k; return k; }
  @Override public int available() { return data.length - pos; }
  @Override public boolean markSupported() { return true; }
  @Override public synchronized void mark(int l) { mark = pos; }
  @Override public synchronized void reset() { pos = mark; }
  @Override public void close() {}
}

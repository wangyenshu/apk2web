package android.content.res;

import a2w.jdk.FileDescriptor;
import a2w.jdk.FileInputStream;

public class AssetFileDescriptor implements java.io.Closeable {
  public static final long UNKNOWN_LENGTH = -1;
  final FileDescriptor fd;
  final long length;
  final android.os.ParcelFileDescriptor pfd;
  public AssetFileDescriptor(FileDescriptor fd, long start, long length) { this.fd = fd; this.length = length; pfd = null; }
  public AssetFileDescriptor(android.os.ParcelFileDescriptor pfd, long start, long length) { this.pfd = pfd; this.fd = pfd.getFileDescriptor(); this.length = length < 0 ? pfd.getStatSize() : length; }
  public AssetFileDescriptor(android.os.ParcelFileDescriptor pfd, long start, long length, android.os.Bundle extras) { this(pfd, start, length); }
  public android.os.ParcelFileDescriptor getParcelFileDescriptor() { return pfd != null ? pfd : android.os.ParcelFileDescriptor.fromData(fd.data, fd.apkEntry); }
  public android.os.Bundle getExtras() { return null; }
  public FileDescriptor getFileDescriptor() { return fd; }
  public long getStartOffset() { return 0; }
  public long getLength() { return length; }
  public long getDeclaredLength() { return length; }
  public FileInputStream createInputStream() { return new FileInputStream(fd); }
  public java.io.FileOutputStream createOutputStream() throws java.io.IOException {
    if (pfd == null || !pfd.writable()) throw new java.io.IOException("read-only");
    return new java.io.FileOutputStream(pfd.file(), (pfd.getStatSize() > 0));
  }
  public void close() {}
  /** web: the entry's name in the APK */
  public String name() { return fd.apkEntry != null ? fd.apkEntry : fd.path; }
  public String toString() { return "{AssetFileDescriptor: " + name() + "}"; }
}

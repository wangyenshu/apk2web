package android.os;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/** A file opened for a content provider's caller (here: the file and mode; streams open the file). */
public class ParcelFileDescriptor implements Parcelable, java.io.Closeable {
  public static final int MODE_WORLD_READABLE = 0x00000001, MODE_WORLD_WRITEABLE = 0x00000002, MODE_READ_ONLY = 0x10000000, MODE_WRITE_ONLY = 0x20000000,
      MODE_READ_WRITE = 0x30000000, MODE_CREATE = 0x08000000, MODE_TRUNCATE = 0x04000000, MODE_APPEND = 0x02000000;
  final File mFile;
  final int mMode;
  final byte[] mData;
  private boolean mClosed;
  ParcelFileDescriptor(File f, int mode, byte[] data) { mFile = f; mMode = mode; mData = data; }
  public ParcelFileDescriptor(ParcelFileDescriptor wrapped) { this(wrapped.mFile, wrapped.mMode, wrapped.mData); }
  public static ParcelFileDescriptor open(File file, int mode) throws FileNotFoundException {
    if ((mode & MODE_CREATE) == 0 && !file.exists()) throw new FileNotFoundException(file.getPath() + ": open failed: ENOENT (No such file or directory)");
    if ((mode & MODE_CREATE) != 0 && !file.exists()) { try { new FileOutputStream(file).close(); } catch (IOException e) { throw new FileNotFoundException(e.getMessage()); } }
    if ((mode & MODE_TRUNCATE) != 0) { try { new FileOutputStream(file).close(); } catch (IOException e) { throw new FileNotFoundException(e.getMessage()); } }
    return new ParcelFileDescriptor(file, mode, null);
  }
  /** web: in-memory contents (an asset or resource handed out as a descriptor) */
  public static ParcelFileDescriptor fromData(byte[] data, String name) { return new ParcelFileDescriptor(new File(name == null ? "data" : name), MODE_READ_ONLY, data); }
  public static int parseMode(String mode) {
    switch (mode) {
      case "r": return MODE_READ_ONLY;
      case "w": case "wt": return MODE_WRITE_ONLY | MODE_CREATE | MODE_TRUNCATE;
      case "wa": return MODE_WRITE_ONLY | MODE_CREATE | MODE_APPEND;
      case "rw": return MODE_READ_WRITE | MODE_CREATE;
      case "rwt": return MODE_READ_WRITE | MODE_CREATE | MODE_TRUNCATE;
      default: throw new IllegalArgumentException("Bad mode: " + mode);
    }
  }
  public java.io.InputStream openInput() throws FileNotFoundException { return mData != null ? new java.io.ByteArrayInputStream(mData) : new FileInputStream(mFile); }
  public java.io.OutputStream openOutput() throws FileNotFoundException { return new FileOutputStream(mFile, (mMode & MODE_APPEND) != 0); }
  public a2w.jdk.FileDescriptor getFileDescriptor() {
    a2w.jdk.FileDescriptor fd = new a2w.jdk.FileDescriptor();
    if (mData != null) fd.data = mData; else fd.path = mFile.getAbsolutePath();
    return fd;
  }
  /** web: the file this descriptor names */
  public File file() { return mFile; }
  public boolean writable() { return (mMode & MODE_WRITE_ONLY) != 0; }
  public long getStatSize() { return mData != null ? mData.length : mFile.length(); }
  public int getFd() { return -1; }
  public int detachFd() { return -1; }
  public void close() throws IOException { mClosed = true; }
  public void closeWithError(String msg) throws IOException { close(); }
  public void checkError() throws IOException {}
  public static ParcelFileDescriptor[] createPipe() throws IOException { throw new IOException("pipes are not supported"); }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel out, int flags) {}
  @Override public String toString() { return "{ParcelFileDescriptor: " + mFile + "}"; }
  public static class AutoCloseInputStream extends java.io.InputStream {
    private final java.io.InputStream in;
    public AutoCloseInputStream(ParcelFileDescriptor pfd) { java.io.InputStream s; try { s = pfd.openInput(); } catch (FileNotFoundException e) { s = new java.io.ByteArrayInputStream(new byte[0]); } in = s; }
    @Override public int read() throws IOException { return in.read(); }
    @Override public int read(byte[] b, int off, int len) throws IOException { return in.read(b, off, len); }
    @Override public int available() throws IOException { return in.available(); }
    @Override public long skip(long n) throws IOException { return in.skip(n); }
    @Override public void close() throws IOException { in.close(); }
  }
  public static class AutoCloseOutputStream extends java.io.OutputStream {
    private final java.io.OutputStream out;
    public AutoCloseOutputStream(ParcelFileDescriptor pfd) { java.io.OutputStream s; try { s = pfd.openOutput(); } catch (FileNotFoundException e) { s = new java.io.ByteArrayOutputStream(); } out = s; }
    @Override public void write(int b) throws IOException { out.write(b); }
    @Override public void write(byte[] b, int off, int len) throws IOException { out.write(b, off, len); }
    @Override public void flush() throws IOException { out.flush(); }
    @Override public void close() throws IOException { out.close(); }
  }
}

package a2w.jdk;

/**
 * java.io.FileDescriptor: names a stored file, a region of the APK (for AssetFileDescriptor), or an open file of
 * android.system.Os (its number and open file description).
 */
public final class FileDescriptor {
  public static final FileDescriptor in = new FileDescriptor(0), out = new FileDescriptor(1), err = new FileDescriptor(2);
  public String path;        // stored file
  public String apkEntry;    // asset/resource entry in the APK
  public byte[] data;        // contents for APK entries
  /** android.system.Os: the descriptor's number and its open file (null when closed) */
  public int fd = -1;
  public Object handle;
  public FileDescriptor() {}
  private FileDescriptor(int fd) { this.fd = fd; }
  public boolean valid() { return path != null || data != null || handle != null || fd >= 0 && fd <= 2; }
  public void sync() throws a2w.jdk.io.SyncFailedException { if (!valid()) throw new a2w.jdk.io.SyncFailedException("sync failed"); }
}

package android.util;

import java.io.*;

/** android.util.AtomicFile: writes go to a new file that replaces the old one on finishWrite (a backup kept meanwhile). */
public class AtomicFile {
  private final File mBaseName, mNewName, mLegacyBackupName;
  public AtomicFile(File baseName) { mBaseName = baseName; mNewName = new File(baseName.getPath() + ".new"); mLegacyBackupName = new File(baseName.getPath() + ".bak"); }
  public AtomicFile(File baseName, String commitTag) { this(baseName); }
  public File getBaseFile() { return mBaseName; }
  public void delete() { mBaseName.delete(); mNewName.delete(); mLegacyBackupName.delete(); }
  public FileOutputStream startWrite() throws IOException { return startWrite(0); }
  public FileOutputStream startWrite(long startTime) throws IOException {
    if (mLegacyBackupName.exists()) mLegacyBackupName.renameTo(mBaseName);
    try { return new FileOutputStream(mNewName); }
    catch (FileNotFoundException e) {
      File parent = mNewName.getParentFile();
      if (parent == null || !parent.mkdirs()) throw new IOException("Failed to create directory for " + mNewName);
      return new FileOutputStream(mNewName);
    }
  }
  public void finishWrite(FileOutputStream str) {
    if (str == null) return;
    try { str.close(); } catch (IOException e) { Log.e("AtomicFile", "Failed to close file output stream", e); }
    if (!mNewName.renameTo(mBaseName)) { mBaseName.delete(); mNewName.renameTo(mBaseName); }
  }
  public void failWrite(FileOutputStream str) {
    if (str == null) return;
    try { str.close(); } catch (IOException e) { Log.e("AtomicFile", "Failed to close file output stream", e); }
    mNewName.delete();
  }
  public a2w.jdk.FileInputStream openRead() throws FileNotFoundException {   // the app's java.io.FileInputStream
    if (mLegacyBackupName.exists()) mLegacyBackupName.renameTo(mBaseName);
    if (mNewName.exists() && mBaseName.exists()) mNewName.delete();
    return new a2w.jdk.FileInputStream(mBaseName);
  }
  public boolean exists() { return mBaseName.exists() || mLegacyBackupName.exists(); }
  public long getLastModifiedTime() { return mBaseName.lastModified(); }
  public byte[] readFully() throws IOException {
    try (java.io.InputStream in = openRead()) {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      byte[] b = new byte[8192];
      for (int n; (n = in.read(b)) > 0; ) out.write(b, 0, n);
      return out.toByteArray();
    }
  }
  @Override public String toString() { return "AtomicFile[" + mBaseName + "]"; }
}

// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os;

public class DropBoxManager {
  public static final java.lang.String ACTION_DROPBOX_ENTRY_ADDED = "android.intent.action.DROPBOX_ENTRY_ADDED";
  public static final java.lang.String EXTRA_DROPPED_COUNT = "android.os.extra.DROPPED_COUNT";
  public static final java.lang.String EXTRA_TAG = "tag";
  public static final java.lang.String EXTRA_TIME = "time";
  public static final int IS_EMPTY = 1;
  public static final int IS_GZIPPED = 4;
  public static final int IS_TEXT = 2;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public DropBoxManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented protected DropBoxManager() { this((a2w.Hidden) null); a2w.Stubs.hit("android.os.DropBoxManager.<init>"); }
  @a2w.NotImplemented public void addData(java.lang.String tag, byte[] data, int flags) { a2w.Stubs.hit("android.os.DropBoxManager.addData"); }
  @a2w.NotImplemented public void addFile(java.lang.String tag, java.io.File file, int flags) throws java.io.IOException { a2w.Stubs.hit("android.os.DropBoxManager.addFile"); }
  @a2w.NotImplemented public void addText(java.lang.String tag, java.lang.String data) { a2w.Stubs.hit("android.os.DropBoxManager.addText"); }
  @a2w.NotImplemented public android.os.DropBoxManager.Entry getNextEntry(java.lang.String tag, long msec) { a2w.Stubs.hit("android.os.DropBoxManager.getNextEntry"); return null; }
  @a2w.NotImplemented public boolean isTagEnabled(java.lang.String tag) { a2w.Stubs.hit("android.os.DropBoxManager.isTagEnabled"); return false; }

    public static class Entry implements java.io.Closeable, android.os.Parcelable {
    @a2w.NotImplemented public static final android.os.Parcelable.Creator<android.os.DropBoxManager.Entry> CREATOR = null;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public Entry(a2w.Hidden hidden) { }
    @a2w.NotImplemented public Entry(java.lang.String tag, long millis) { this((a2w.Hidden) null); a2w.Stubs.hit("android.os.DropBoxManager.Entry.<init>"); }
    @a2w.NotImplemented public Entry(java.lang.String tag, long millis, android.os.ParcelFileDescriptor data, int flags) { this((a2w.Hidden) null); a2w.Stubs.hit("android.os.DropBoxManager.Entry.<init>"); }
    @a2w.NotImplemented public Entry(java.lang.String tag, long millis, java.io.File data, int flags) throws java.io.IOException { this((a2w.Hidden) null); a2w.Stubs.hit("android.os.DropBoxManager.Entry.<init>"); }
    @a2w.NotImplemented public Entry(java.lang.String tag, long millis, java.lang.String text) { this((a2w.Hidden) null); a2w.Stubs.hit("android.os.DropBoxManager.Entry.<init>"); }
    @a2w.NotImplemented public Entry(java.lang.String tag, long millis, byte[] data, int flags) { this((a2w.Hidden) null); a2w.Stubs.hit("android.os.DropBoxManager.Entry.<init>"); }
    @a2w.NotImplemented public void close() { a2w.Stubs.hit("android.os.DropBoxManager.Entry.close"); }
    @a2w.NotImplemented public int describeContents() { a2w.Stubs.hit("android.os.DropBoxManager.Entry.describeContents"); return 0; }
    @a2w.NotImplemented public int getFlags() { a2w.Stubs.hit("android.os.DropBoxManager.Entry.getFlags"); return 0; }
    @a2w.NotImplemented public java.io.InputStream getInputStream() throws java.io.IOException { a2w.Stubs.hit("android.os.DropBoxManager.Entry.getInputStream"); return null; }
    @a2w.NotImplemented public java.lang.String getTag() { a2w.Stubs.hit("android.os.DropBoxManager.Entry.getTag"); return null; }
    @a2w.NotImplemented public java.lang.String getText(int maxBytes) { a2w.Stubs.hit("android.os.DropBoxManager.Entry.getText"); return null; }
    @a2w.NotImplemented public long getTimeMillis() { a2w.Stubs.hit("android.os.DropBoxManager.Entry.getTimeMillis"); return 0L; }
    @a2w.NotImplemented public void writeToParcel(android.os.Parcel out, int flags) { a2w.Stubs.hit("android.os.DropBoxManager.Entry.writeToParcel"); }
  }
}

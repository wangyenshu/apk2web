// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os;

public final class BugreportManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public BugreportManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void cancelBugreport() { a2w.Stubs.hit("android.os.BugreportManager.cancelBugreport"); }
  @a2w.NotImplemented public void startConnectivityBugreport(android.os.ParcelFileDescriptor bugreportFd, java.util.concurrent.Executor executor, android.os.BugreportManager.BugreportCallback callback) { a2w.Stubs.hit("android.os.BugreportManager.startConnectivityBugreport"); }

    public static abstract class BugreportCallback {
    public static final int BUGREPORT_ERROR_ANOTHER_REPORT_IN_PROGRESS = 5;
    public static final int BUGREPORT_ERROR_INVALID_INPUT = 1;
    public static final int BUGREPORT_ERROR_NO_BUGREPORT_TO_RETRIEVE = 6;
    public static final int BUGREPORT_ERROR_RUNTIME = 2;
    public static final int BUGREPORT_ERROR_USER_CONSENT_TIMED_OUT = 4;
    public static final int BUGREPORT_ERROR_USER_DENIED_CONSENT = 3;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public BugreportCallback(a2w.Hidden hidden) { }
    public BugreportCallback() { this((a2w.Hidden) null); }
    public void onEarlyReportFinished() { }
    public void onError(int errorCode) { }
    public void onFinished() { }
    public void onProgress(float progress) { }
  }
}

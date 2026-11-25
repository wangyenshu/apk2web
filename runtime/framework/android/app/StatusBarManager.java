// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app;

public class StatusBarManager {
  public static final int TILE_ADD_REQUEST_ERROR_APP_NOT_IN_FOREGROUND = 1004;
  public static final int TILE_ADD_REQUEST_ERROR_BAD_COMPONENT = 1002;
  public static final int TILE_ADD_REQUEST_ERROR_MISMATCHED_PACKAGE = 1000;
  public static final int TILE_ADD_REQUEST_ERROR_NOT_CURRENT_USER = 1003;
  public static final int TILE_ADD_REQUEST_ERROR_NO_STATUS_BAR_SERVICE = 1005;
  public static final int TILE_ADD_REQUEST_ERROR_REQUEST_IN_PROGRESS = 1001;
  public static final int TILE_ADD_REQUEST_RESULT_TILE_ADDED = 2;
  public static final int TILE_ADD_REQUEST_RESULT_TILE_ALREADY_ADDED = 1;
  public static final int TILE_ADD_REQUEST_RESULT_TILE_NOT_ADDED = 0;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public StatusBarManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public boolean canLaunchCaptureContentActivityForNote(android.app.Activity activity) { a2w.Stubs.hit("android.app.StatusBarManager.canLaunchCaptureContentActivityForNote"); return false; }
  @a2w.NotImplemented public void requestAddTileService(android.content.ComponentName tileServiceComponentName, java.lang.CharSequence tileLabel, android.graphics.drawable.Icon icon, java.util.concurrent.Executor resultExecutor, java.util.function.Consumer<java.lang.Integer> resultCallback) { a2w.Stubs.hit("android.app.StatusBarManager.requestAddTileService"); }
}

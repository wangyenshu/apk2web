// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.media.tv.interactive;

public final class TvInteractiveAppManager {
  public static final java.lang.String ACTION_APP_LINK_COMMAND = "android.media.tv.interactive.action.APP_LINK_COMMAND";
  public static final java.lang.String APP_LINK_KEY_BACK_URI = "back_uri";
  public static final java.lang.String APP_LINK_KEY_CLASS_NAME = "class_name";
  public static final java.lang.String APP_LINK_KEY_COMMAND_TYPE = "command_type";
  public static final java.lang.String APP_LINK_KEY_PACKAGE_NAME = "package_name";
  public static final java.lang.String APP_LINK_KEY_SERVICE_ID = "service_id";
  public static final int ERROR_BLOCKED = 5;
  public static final int ERROR_ENCRYPTED = 6;
  public static final int ERROR_NONE = 0;
  public static final int ERROR_NOT_SUPPORTED = 2;
  public static final int ERROR_RESOURCE_UNAVAILABLE = 4;
  public static final int ERROR_UNKNOWN = 1;
  public static final int ERROR_UNKNOWN_CHANNEL = 7;
  public static final int ERROR_WEAK_SIGNAL = 3;
  public static final java.lang.String INTENT_KEY_BI_INTERACTIVE_APP_TYPE = "bi_interactive_app_type";
  public static final java.lang.String INTENT_KEY_BI_INTERACTIVE_APP_URI = "bi_interactive_app_uri";
  public static final java.lang.String INTENT_KEY_CHANNEL_URI = "channel_uri";
  public static final java.lang.String INTENT_KEY_COMMAND_TYPE = "command_type";
  public static final java.lang.String INTENT_KEY_INTERACTIVE_APP_SERVICE_ID = "interactive_app_id";
  public static final java.lang.String INTENT_KEY_TV_INPUT_ID = "tv_input_id";
  public static final int INTERACTIVE_APP_STATE_ERROR = 3;
  public static final int INTERACTIVE_APP_STATE_RUNNING = 2;
  public static final int INTERACTIVE_APP_STATE_STOPPED = 1;
  public static final int SERVICE_STATE_ERROR = 4;
  public static final int SERVICE_STATE_PREPARING = 2;
  public static final int SERVICE_STATE_READY = 3;
  public static final int SERVICE_STATE_UNREALIZED = 1;
  public static final int TELETEXT_APP_STATE_ERROR = 3;
  public static final int TELETEXT_APP_STATE_HIDE = 2;
  public static final int TELETEXT_APP_STATE_SHOW = 1;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public TvInteractiveAppManager(a2w.Hidden hidden) { }
  @a2w.Absent public java.util.List<android.media.tv.interactive.AppLinkInfo> getAppLinkInfoList() { return java.util.Collections.emptyList(); }
  @a2w.Absent public java.util.List<android.media.tv.interactive.TvInteractiveAppServiceInfo> getTvInteractiveAppServiceList() { return java.util.Collections.emptyList(); }
  @a2w.Absent public void registerAppLinkInfo(java.lang.String tvIAppServiceId, android.media.tv.interactive.AppLinkInfo appLinkInfo) {  }
  @a2w.Absent public void registerCallback(java.util.concurrent.Executor executor, android.media.tv.interactive.TvInteractiveAppManager.TvInteractiveAppCallback callback) {  }
  @a2w.Absent public void sendAppLinkCommand(java.lang.String tvIAppServiceId, android.os.Bundle command) {  }
  @a2w.Absent public void unregisterAppLinkInfo(java.lang.String tvIAppServiceId, android.media.tv.interactive.AppLinkInfo appLinkInfo) {  }
  @a2w.Absent public void unregisterCallback(android.media.tv.interactive.TvInteractiveAppManager.TvInteractiveAppCallback callback) {  }

    public static abstract class TvInteractiveAppCallback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public TvInteractiveAppCallback(a2w.Hidden hidden) { }
    public TvInteractiveAppCallback() { this((a2w.Hidden) null); }
    public void onInteractiveAppServiceAdded(java.lang.String iAppServiceId) { }
    public void onInteractiveAppServiceRemoved(java.lang.String iAppServiceId) { }
    public void onInteractiveAppServiceUpdated(java.lang.String iAppServiceId) { }
    public void onTvInteractiveAppServiceStateChanged(java.lang.String iAppServiceId, int type, int state, int err) { }
  }
}

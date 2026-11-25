// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app;

public class AppOpsManager {
  public static final int MODE_ALLOWED = 0;
  public static final int MODE_DEFAULT = 3;
  public static final int MODE_ERRORED = 2;
  public static final int MODE_FOREGROUND = 4;
  public static final int MODE_IGNORED = 1;
  public static final java.lang.String OPSTR_ADD_VOICEMAIL = "android:add_voicemail";
  public static final java.lang.String OPSTR_ANSWER_PHONE_CALLS = "android:answer_phone_calls";
  public static final java.lang.String OPSTR_BODY_SENSORS = "android:body_sensors";
  public static final java.lang.String OPSTR_CALL_PHONE = "android:call_phone";
  public static final java.lang.String OPSTR_CAMERA = "android:camera";
  public static final java.lang.String OPSTR_COARSE_LOCATION = "android:coarse_location";
  public static final java.lang.String OPSTR_FINE_LOCATION = "android:fine_location";
  public static final java.lang.String OPSTR_GET_USAGE_STATS = "android:get_usage_stats";
  public static final java.lang.String OPSTR_MOCK_LOCATION = "android:mock_location";
  public static final java.lang.String OPSTR_MONITOR_HIGH_POWER_LOCATION = "android:monitor_location_high_power";
  public static final java.lang.String OPSTR_MONITOR_LOCATION = "android:monitor_location";
  public static final java.lang.String OPSTR_PICTURE_IN_PICTURE = "android:picture_in_picture";
  public static final java.lang.String OPSTR_PROCESS_OUTGOING_CALLS = "android:process_outgoing_calls";
  public static final java.lang.String OPSTR_READ_CALENDAR = "android:read_calendar";
  public static final java.lang.String OPSTR_READ_CALL_LOG = "android:read_call_log";
  public static final java.lang.String OPSTR_READ_CELL_BROADCASTS = "android:read_cell_broadcasts";
  public static final java.lang.String OPSTR_READ_CONTACTS = "android:read_contacts";
  public static final java.lang.String OPSTR_READ_EXTERNAL_STORAGE = "android:read_external_storage";
  public static final java.lang.String OPSTR_READ_PHONE_NUMBERS = "android:read_phone_numbers";
  public static final java.lang.String OPSTR_READ_PHONE_STATE = "android:read_phone_state";
  public static final java.lang.String OPSTR_READ_SMS = "android:read_sms";
  public static final java.lang.String OPSTR_RECEIVE_MMS = "android:receive_mms";
  public static final java.lang.String OPSTR_RECEIVE_SMS = "android:receive_sms";
  public static final java.lang.String OPSTR_RECEIVE_WAP_PUSH = "android:receive_wap_push";
  public static final java.lang.String OPSTR_RECORD_AUDIO = "android:record_audio";
  public static final java.lang.String OPSTR_SEND_SMS = "android:send_sms";
  public static final java.lang.String OPSTR_SYSTEM_ALERT_WINDOW = "android:system_alert_window";
  public static final java.lang.String OPSTR_USE_FINGERPRINT = "android:use_fingerprint";
  public static final java.lang.String OPSTR_USE_SIP = "android:use_sip";
  public static final java.lang.String OPSTR_WRITE_CALENDAR = "android:write_calendar";
  public static final java.lang.String OPSTR_WRITE_CALL_LOG = "android:write_call_log";
  public static final java.lang.String OPSTR_WRITE_CONTACTS = "android:write_contacts";
  public static final java.lang.String OPSTR_WRITE_EXTERNAL_STORAGE = "android:write_external_storage";
  public static final java.lang.String OPSTR_WRITE_SETTINGS = "android:write_settings";
  public static final int WATCH_FOREGROUND_CHANGES = 1;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public AppOpsManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public int checkOp(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.checkOp"); return 0; }
  @a2w.NotImplemented public int checkOpNoThrow(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.checkOpNoThrow"); return 0; }
  @a2w.NotImplemented public void checkPackage(int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.checkPackage"); }
  @a2w.NotImplemented public void finishOp(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.finishOp"); }
  @a2w.NotImplemented public void finishOp(java.lang.String op, int uid, java.lang.String packageName, java.lang.String attributionTag) { a2w.Stubs.hit("android.app.AppOpsManager.finishOp"); }
  @a2w.NotImplemented public void finishProxyOp(java.lang.String op, int proxiedUid, java.lang.String proxiedPackageName, java.lang.String proxiedAttributionTag) { a2w.Stubs.hit("android.app.AppOpsManager.finishProxyOp"); }
  @a2w.NotImplemented public boolean isOpActive(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.isOpActive"); return false; }
  @a2w.NotImplemented public int noteOp(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.noteOp"); return 0; }
  @a2w.NotImplemented public int noteOp(java.lang.String op, int uid, java.lang.String packageName, java.lang.String attributionTag, java.lang.String message) { a2w.Stubs.hit("android.app.AppOpsManager.noteOp"); return 0; }
  @a2w.NotImplemented public int noteOpNoThrow(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.noteOpNoThrow"); return 0; }
  @a2w.NotImplemented public int noteOpNoThrow(java.lang.String op, int uid, java.lang.String packageName, java.lang.String attributionTag, java.lang.String message) { a2w.Stubs.hit("android.app.AppOpsManager.noteOpNoThrow"); return 0; }
  @a2w.NotImplemented public int noteProxyOp(java.lang.String op, java.lang.String proxiedPackageName) { a2w.Stubs.hit("android.app.AppOpsManager.noteProxyOp"); return 0; }
  @a2w.NotImplemented public int noteProxyOp(java.lang.String op, java.lang.String proxiedPackageName, int proxiedUid, java.lang.String proxiedAttributionTag, java.lang.String message) { a2w.Stubs.hit("android.app.AppOpsManager.noteProxyOp"); return 0; }
  @a2w.NotImplemented public int noteProxyOpNoThrow(java.lang.String op, java.lang.String proxiedPackageName) { a2w.Stubs.hit("android.app.AppOpsManager.noteProxyOpNoThrow"); return 0; }
  @a2w.NotImplemented public int noteProxyOpNoThrow(java.lang.String op, java.lang.String proxiedPackageName, int proxiedUid) { a2w.Stubs.hit("android.app.AppOpsManager.noteProxyOpNoThrow"); return 0; }
  @a2w.NotImplemented public int noteProxyOpNoThrow(java.lang.String op, java.lang.String proxiedPackageName, int proxiedUid, java.lang.String proxiedAttributionTag, java.lang.String message) { a2w.Stubs.hit("android.app.AppOpsManager.noteProxyOpNoThrow"); return 0; }
  @a2w.NotImplemented public static java.lang.String permissionToOp(java.lang.String permission) { a2w.Stubs.hit("android.app.AppOpsManager.permissionToOp"); return null; }
  @a2w.NotImplemented public void setOnOpNotedCallback(java.util.concurrent.Executor asyncExecutor, android.app.AppOpsManager.OnOpNotedCallback callback) { a2w.Stubs.hit("android.app.AppOpsManager.setOnOpNotedCallback"); }
  @a2w.NotImplemented public int startOp(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.startOp"); return 0; }
  @a2w.NotImplemented public int startOp(java.lang.String op, int uid, java.lang.String packageName, java.lang.String attributionTag, java.lang.String message) { a2w.Stubs.hit("android.app.AppOpsManager.startOp"); return 0; }
  @a2w.NotImplemented public int startOpNoThrow(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.startOpNoThrow"); return 0; }
  @a2w.NotImplemented public int startOpNoThrow(java.lang.String op, int uid, java.lang.String packageName, java.lang.String attributionTag, java.lang.String message) { a2w.Stubs.hit("android.app.AppOpsManager.startOpNoThrow"); return 0; }
  @a2w.NotImplemented public int startProxyOp(java.lang.String op, int proxiedUid, java.lang.String proxiedPackageName, java.lang.String proxiedAttributionTag, java.lang.String message) { a2w.Stubs.hit("android.app.AppOpsManager.startProxyOp"); return 0; }
  @a2w.NotImplemented public int startProxyOpNoThrow(java.lang.String op, int proxiedUid, java.lang.String proxiedPackageName, java.lang.String proxiedAttributionTag, java.lang.String message) { a2w.Stubs.hit("android.app.AppOpsManager.startProxyOpNoThrow"); return 0; }
  @a2w.NotImplemented public void startWatchingActive(java.lang.String[] ops, java.util.concurrent.Executor executor, android.app.AppOpsManager.OnOpActiveChangedListener callback) { a2w.Stubs.hit("android.app.AppOpsManager.startWatchingActive"); }
  @a2w.NotImplemented public void startWatchingMode(java.lang.String op, java.lang.String packageName, int flags, android.app.AppOpsManager.OnOpChangedListener callback) { a2w.Stubs.hit("android.app.AppOpsManager.startWatchingMode"); }
  @a2w.NotImplemented public void startWatchingMode(java.lang.String op, java.lang.String packageName, android.app.AppOpsManager.OnOpChangedListener callback) { a2w.Stubs.hit("android.app.AppOpsManager.startWatchingMode"); }
  @a2w.NotImplemented public void stopWatchingActive(android.app.AppOpsManager.OnOpActiveChangedListener callback) { a2w.Stubs.hit("android.app.AppOpsManager.stopWatchingActive"); }
  @a2w.NotImplemented public void stopWatchingMode(android.app.AppOpsManager.OnOpChangedListener callback) { a2w.Stubs.hit("android.app.AppOpsManager.stopWatchingMode"); }
  @a2w.NotImplemented public int unsafeCheckOp(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.unsafeCheckOp"); return 0; }
  @a2w.NotImplemented public int unsafeCheckOpNoThrow(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.unsafeCheckOpNoThrow"); return 0; }
  @a2w.NotImplemented public int unsafeCheckOpRaw(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.unsafeCheckOpRaw"); return 0; }
  @a2w.NotImplemented public int unsafeCheckOpRawNoThrow(java.lang.String op, int uid, java.lang.String packageName) { a2w.Stubs.hit("android.app.AppOpsManager.unsafeCheckOpRawNoThrow"); return 0; }

    public interface OnOpActiveChangedListener {
    default void onOpActiveChanged(java.lang.String op, int uid, java.lang.String packageName, java.lang.String attributionTag, int virtualDeviceId, boolean active, int attributionFlags, int attributionChainId) { }
    void onOpActiveChanged(java.lang.String p0, int p1, java.lang.String p2, boolean p3);
  }

    public interface OnOpChangedListener {
    void onOpChanged(java.lang.String p0, java.lang.String p1);
    default void onOpChanged(java.lang.String op, java.lang.String packageName, int userId, java.lang.String persistentDeviceId) { }
  }

    public static abstract class OnOpNotedCallback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public OnOpNotedCallback(a2w.Hidden hidden) { }
    public OnOpNotedCallback() { this((a2w.Hidden) null); }
    public abstract void onAsyncNoted(android.app.AsyncNotedAppOp p0);
    public abstract void onNoted(android.app.SyncNotedAppOp p0);
    public abstract void onSelfNoted(android.app.SyncNotedAppOp p0);
  }
}

// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.appwidget;

public class AppWidgetManager {
  public static final java.lang.String ACTION_APPWIDGET_BIND = "android.appwidget.action.APPWIDGET_BIND";
  public static final java.lang.String ACTION_APPWIDGET_CONFIGURE = "android.appwidget.action.APPWIDGET_CONFIGURE";
  public static final java.lang.String ACTION_APPWIDGET_DELETED = "android.appwidget.action.APPWIDGET_DELETED";
  public static final java.lang.String ACTION_APPWIDGET_DISABLED = "android.appwidget.action.APPWIDGET_DISABLED";
  public static final java.lang.String ACTION_APPWIDGET_ENABLED = "android.appwidget.action.APPWIDGET_ENABLED";
  public static final java.lang.String ACTION_APPWIDGET_HOST_RESTORED = "android.appwidget.action.APPWIDGET_HOST_RESTORED";
  public static final java.lang.String ACTION_APPWIDGET_OPTIONS_CHANGED = "android.appwidget.action.APPWIDGET_UPDATE_OPTIONS";
  public static final java.lang.String ACTION_APPWIDGET_PICK = "android.appwidget.action.APPWIDGET_PICK";
  public static final java.lang.String ACTION_APPWIDGET_RESTORED = "android.appwidget.action.APPWIDGET_RESTORED";
  public static final java.lang.String ACTION_APPWIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";
  public static final java.lang.String EXTRA_APPWIDGET_ID = "appWidgetId";
  public static final java.lang.String EXTRA_APPWIDGET_IDS = "appWidgetIds";
  public static final java.lang.String EXTRA_APPWIDGET_OLD_IDS = "appWidgetOldIds";
  public static final java.lang.String EXTRA_APPWIDGET_OPTIONS = "appWidgetOptions";
  public static final java.lang.String EXTRA_APPWIDGET_PREVIEW = "appWidgetPreview";
  public static final java.lang.String EXTRA_APPWIDGET_PROVIDER = "appWidgetProvider";
  public static final java.lang.String EXTRA_APPWIDGET_PROVIDER_PROFILE = "appWidgetProviderProfile";
  public static final java.lang.String EXTRA_CUSTOM_EXTRAS = "customExtras";
  public static final java.lang.String EXTRA_CUSTOM_INFO = "customInfo";
  public static final java.lang.String EXTRA_HOST_ID = "hostId";
  public static final int INVALID_APPWIDGET_ID = 0;
  public static final java.lang.String META_DATA_APPWIDGET_PROVIDER = "android.appwidget.provider";
  public static final java.lang.String OPTION_APPWIDGET_HOST_CATEGORY = "appWidgetCategory";
  public static final java.lang.String OPTION_APPWIDGET_MAX_HEIGHT = "appWidgetMaxHeight";
  public static final java.lang.String OPTION_APPWIDGET_MAX_WIDTH = "appWidgetMaxWidth";
  public static final java.lang.String OPTION_APPWIDGET_MIN_HEIGHT = "appWidgetMinHeight";
  public static final java.lang.String OPTION_APPWIDGET_MIN_WIDTH = "appWidgetMinWidth";
  public static final java.lang.String OPTION_APPWIDGET_RESTORE_COMPLETED = "appWidgetRestoreCompleted";
  public static final java.lang.String OPTION_APPWIDGET_SIZES = "appWidgetSizes";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public AppWidgetManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void bindAppWidgetId(int appWidgetId, android.content.ComponentName provider) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.bindAppWidgetId"); }
  @a2w.NotImplemented public boolean bindAppWidgetIdIfAllowed(int appWidgetId, android.content.ComponentName provider) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.bindAppWidgetIdIfAllowed"); return false; }
  @a2w.NotImplemented public boolean bindAppWidgetIdIfAllowed(int appWidgetId, android.content.ComponentName provider, android.os.Bundle options) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.bindAppWidgetIdIfAllowed"); return false; }
  @a2w.NotImplemented public boolean bindAppWidgetIdIfAllowed(int appWidgetId, android.os.UserHandle user, android.content.ComponentName provider, android.os.Bundle options) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.bindAppWidgetIdIfAllowed"); return false; }
  @a2w.NotImplemented public int[] getAppWidgetIds(android.content.ComponentName provider) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.getAppWidgetIds"); return null; }
  @a2w.NotImplemented public android.appwidget.AppWidgetProviderInfo getAppWidgetInfo(int appWidgetId) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.getAppWidgetInfo"); return null; }
  @a2w.NotImplemented public android.os.Bundle getAppWidgetOptions(int appWidgetId) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.getAppWidgetOptions"); return null; }
  @a2w.NotImplemented public java.util.List<android.appwidget.AppWidgetProviderInfo> getInstalledProviders() { a2w.Stubs.hit("android.appwidget.AppWidgetManager.getInstalledProviders"); return null; }
  @a2w.NotImplemented public java.util.List<android.appwidget.AppWidgetProviderInfo> getInstalledProvidersForPackage(java.lang.String packageName, android.os.UserHandle profile) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.getInstalledProvidersForPackage"); return null; }
  @a2w.NotImplemented public java.util.List<android.appwidget.AppWidgetProviderInfo> getInstalledProvidersForProfile(android.os.UserHandle profile) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.getInstalledProvidersForProfile"); return null; }
  @a2w.NotImplemented public static android.appwidget.AppWidgetManager getInstance(android.content.Context context) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.getInstance"); return null; }
  @a2w.NotImplemented public android.widget.RemoteViews getWidgetPreview(android.content.ComponentName provider, android.os.UserHandle profile, int widgetCategory) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.getWidgetPreview"); return null; }
  @a2w.NotImplemented public boolean isRequestPinAppWidgetSupported() { a2w.Stubs.hit("android.appwidget.AppWidgetManager.isRequestPinAppWidgetSupported"); return false; }
  @a2w.NotImplemented public void notifyAppWidgetViewDataChanged(int appWidgetId, int viewId) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.notifyAppWidgetViewDataChanged"); }
  @a2w.NotImplemented public void notifyAppWidgetViewDataChanged(int[] appWidgetIds, int viewId) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.notifyAppWidgetViewDataChanged"); }
  @a2w.NotImplemented public void partiallyUpdateAppWidget(int appWidgetId, android.widget.RemoteViews views) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.partiallyUpdateAppWidget"); }
  @a2w.NotImplemented public void partiallyUpdateAppWidget(int[] appWidgetIds, android.widget.RemoteViews views) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.partiallyUpdateAppWidget"); }
  @a2w.NotImplemented public void removeWidgetPreview(android.content.ComponentName provider, int widgetCategories) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.removeWidgetPreview"); }
  @a2w.NotImplemented public boolean requestPinAppWidget(android.content.ComponentName provider, android.os.Bundle extras, android.app.PendingIntent successCallback) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.requestPinAppWidget"); return false; }
  @a2w.NotImplemented public boolean setWidgetPreview(android.content.ComponentName provider, int widgetCategories, android.widget.RemoteViews preview) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.setWidgetPreview"); return false; }
  @a2w.NotImplemented public void updateAppWidget(int appWidgetId, android.widget.RemoteViews views) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.updateAppWidget"); }
  @a2w.NotImplemented public void updateAppWidget(android.content.ComponentName provider, android.widget.RemoteViews views) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.updateAppWidget"); }
  @a2w.NotImplemented public void updateAppWidget(int[] appWidgetIds, android.widget.RemoteViews views) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.updateAppWidget"); }
  @a2w.NotImplemented public void updateAppWidgetOptions(int appWidgetId, android.os.Bundle options) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.updateAppWidgetOptions"); }
  @a2w.NotImplemented public void updateAppWidgetProviderInfo(android.content.ComponentName provider, java.lang.String metaDataKey) { a2w.Stubs.hit("android.appwidget.AppWidgetManager.updateAppWidgetProviderInfo"); }
}

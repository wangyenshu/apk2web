package android.webkit;

import android.content.Context;

/** A WebView's settings. They are kept and read back; the page's frame follows the ones a browser frame can. */
public class WebSettings {
  public enum LayoutAlgorithm { NORMAL, SINGLE_COLUMN, NARROW_COLUMNS, TEXT_AUTOSIZING }
  public enum PluginState { ON, ON_DEMAND, OFF }
  public enum RenderPriority { NORMAL, HIGH, LOW }
  public enum ZoomDensity { FAR(150), MEDIUM(100), CLOSE(75); final int value; ZoomDensity(int v) { value = v; } public int getValue() { return value; } }
  public enum TextSize { SMALLEST(50), SMALLER(75), NORMAL(100), LARGER(150), LARGEST(200); final int value; TextSize(int v) { value = v; } }
  public static final int LOAD_DEFAULT = -1, LOAD_NORMAL = 0, LOAD_CACHE_ELSE_NETWORK = 1, LOAD_NO_CACHE = 2, LOAD_CACHE_ONLY = 3;
  public static final int MIXED_CONTENT_ALWAYS_ALLOW = 0, MIXED_CONTENT_NEVER_ALLOW = 1, MIXED_CONTENT_COMPATIBILITY_MODE = 2;
  public static final int FORCE_DARK_OFF = 0, FORCE_DARK_AUTO = 1, FORCE_DARK_ON = 2;
  public static final int MENU_ITEM_NONE = 0, MENU_ITEM_SHARE = 1, MENU_ITEM_WEB_SEARCH = 2, MENU_ITEM_PROCESS_TEXT = 4;
  public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Linux; Android 14; apk2web) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/120.0.0.0 Mobile Safari/537.36";

  boolean js, supportZoom = true, builtInZoom, displayZoom = true, fileAccess = true, contentAccess = true, overview,
      smooth, saveForm = true, savePassword, lightTouch, wideViewPort, multipleWindows, loadImages = true, blockImages,
      blockLoads, universalFromFile, fileFromFile, database, domStorage, geolocation = true, jsOpenWindows,
      mediaGesture = true, offscreen, safeBrowsing = true, darkening, needFocus = true, appCache, navDump;
  int textZoom = 100, minFont = 8, minLogicalFont = 8, defaultFont = 16, defaultFixedFont = 13, cacheMode = LOAD_DEFAULT,
      mixed = MIXED_CONTENT_NEVER_ALLOW, forceDark = FORCE_DARK_AUTO, disabledMenu = MENU_ITEM_NONE;
  String standard = "sans-serif", fixed = "monospace", sansSerif = "sans-serif", serif = "serif", cursive = "cursive",
      fantasy = "fantasy", encoding = "UTF-8", userAgent, databasePath = "";
  LayoutAlgorithm layout = LayoutAlgorithm.NARROW_COLUMNS;
  PluginState plugins = PluginState.OFF;
  ZoomDensity zoom = ZoomDensity.MEDIUM;

  public static String getDefaultUserAgent(Context c) { return DEFAULT_USER_AGENT; }

  public void setSupportZoom(boolean f) { supportZoom = f; }
  public boolean supportZoom() { return supportZoom; }
  public void setMediaPlaybackRequiresUserGesture(boolean f) { mediaGesture = f; }
  public boolean getMediaPlaybackRequiresUserGesture() { return mediaGesture; }
  public void setBuiltInZoomControls(boolean f) { builtInZoom = f; }
  public boolean getBuiltInZoomControls() { return builtInZoom; }
  public void setDisplayZoomControls(boolean f) { displayZoom = f; }
  public boolean getDisplayZoomControls() { return displayZoom; }
  public void setAllowFileAccess(boolean f) { fileAccess = f; }
  public boolean getAllowFileAccess() { return fileAccess; }
  public void setAllowContentAccess(boolean f) { contentAccess = f; }
  public boolean getAllowContentAccess() { return contentAccess; }
  public void setLoadWithOverviewMode(boolean f) { overview = f; }
  public boolean getLoadWithOverviewMode() { return overview; }
  public void setEnableSmoothTransition(boolean f) { smooth = f; }
  public boolean enableSmoothTransition() { return smooth; }
  public void setUseWebViewBackgroundForOverscrollBackground(boolean f) {}
  public boolean getUseWebViewBackgroundForOverscrollBackground() { return false; }
  public void setSaveFormData(boolean f) { saveForm = f; }
  public boolean getSaveFormData() { return saveForm; }
  public void setSavePassword(boolean f) { savePassword = f; }
  public boolean getSavePassword() { return savePassword; }
  public void setTextZoom(int z) { textZoom = z; }
  public int getTextZoom() { return textZoom; }
  public void setTextSize(TextSize t) { if (t != null) textZoom = t.value; }
  public TextSize getTextSize() {
    TextSize best = TextSize.NORMAL;
    for (TextSize t : TextSize.values()) if (Math.abs(t.value - textZoom) < Math.abs(best.value - textZoom)) best = t;
    return best;
  }
  public void setDefaultZoom(ZoomDensity z) { if (z != null) zoom = z; }
  public ZoomDensity getDefaultZoom() { return zoom; }
  public void setLightTouchEnabled(boolean f) { lightTouch = f; }
  public boolean getLightTouchEnabled() { return lightTouch; }
  public void setUseDoubleTree(boolean f) {}
  public boolean getUseDoubleTree() { return false; }
  public void setUserAgent(int ua) {}
  public int getUserAgent() { return 0; }
  public void setUseWideViewPort(boolean f) { wideViewPort = f; }
  public boolean getUseWideViewPort() { return wideViewPort; }
  public void setSupportMultipleWindows(boolean f) { multipleWindows = f; }
  public boolean supportMultipleWindows() { return multipleWindows; }
  public void setLayoutAlgorithm(LayoutAlgorithm l) { if (l != null) layout = l; }
  public LayoutAlgorithm getLayoutAlgorithm() { return layout; }
  public void setStandardFontFamily(String f) { standard = f; }
  public String getStandardFontFamily() { return standard; }
  public void setFixedFontFamily(String f) { fixed = f; }
  public String getFixedFontFamily() { return fixed; }
  public void setSansSerifFontFamily(String f) { sansSerif = f; }
  public String getSansSerifFontFamily() { return sansSerif; }
  public void setSerifFontFamily(String f) { serif = f; }
  public String getSerifFontFamily() { return serif; }
  public void setCursiveFontFamily(String f) { cursive = f; }
  public String getCursiveFontFamily() { return cursive; }
  public void setFantasyFontFamily(String f) { fantasy = f; }
  public String getFantasyFontFamily() { return fantasy; }
  public void setMinimumFontSize(int s) { minFont = s; }
  public int getMinimumFontSize() { return minFont; }
  public void setMinimumLogicalFontSize(int s) { minLogicalFont = s; }
  public int getMinimumLogicalFontSize() { return minLogicalFont; }
  public void setDefaultFontSize(int s) { defaultFont = s; }
  public int getDefaultFontSize() { return defaultFont; }
  public void setDefaultFixedFontSize(int s) { defaultFixedFont = s; }
  public int getDefaultFixedFontSize() { return defaultFixedFont; }
  public void setLoadsImagesAutomatically(boolean f) { loadImages = f; }
  public boolean getLoadsImagesAutomatically() { return loadImages; }
  public void setBlockNetworkImage(boolean f) { blockImages = f; }
  public boolean getBlockNetworkImage() { return blockImages; }
  public void setBlockNetworkLoads(boolean f) { blockLoads = f; }
  public boolean getBlockNetworkLoads() { return blockLoads; }
  public void setJavaScriptEnabled(boolean f) { js = f; }
  public boolean getJavaScriptEnabled() { return js; }
  public void setAllowUniversalAccessFromFileURLs(boolean f) { universalFromFile = f; }
  public boolean getAllowUniversalAccessFromFileURLs() { return universalFromFile; }
  public void setAllowFileAccessFromFileURLs(boolean f) { fileFromFile = f; }
  public boolean getAllowFileAccessFromFileURLs() { return fileFromFile; }
  public void setPluginsEnabled(boolean f) { plugins = f ? PluginState.ON : PluginState.OFF; }
  public boolean getPluginsEnabled() { return plugins == PluginState.ON; }
  public void setPluginState(PluginState p) { if (p != null) plugins = p; }
  public PluginState getPluginState() { return plugins; }
  public void setPluginsPath(String p) {}
  public String getPluginsPath() { return ""; }
  public void setDatabasePath(String p) { if (p != null) databasePath = p; }
  public String getDatabasePath() { return databasePath; }
  public void setGeolocationDatabasePath(String p) {}
  public void setAppCacheEnabled(boolean f) { appCache = f; }
  public void setAppCachePath(String p) {}
  public void setAppCacheMaxSize(long s) {}
  public void setDatabaseEnabled(boolean f) { database = f; }
  public boolean getDatabaseEnabled() { return database; }
  public void setDomStorageEnabled(boolean f) { domStorage = f; }
  public boolean getDomStorageEnabled() { return domStorage; }
  public void setGeolocationEnabled(boolean f) { geolocation = f; }
  public void setJavaScriptCanOpenWindowsAutomatically(boolean f) { jsOpenWindows = f; }
  public boolean getJavaScriptCanOpenWindowsAutomatically() { return jsOpenWindows; }
  public void setDefaultTextEncodingName(String e) { if (e != null) encoding = e; }
  public String getDefaultTextEncodingName() { return encoding; }
  public void setUserAgentString(String ua) { userAgent = ua == null || ua.isEmpty() ? null : ua; }
  public String getUserAgentString() { return userAgent != null ? userAgent : DEFAULT_USER_AGENT; }
  public void setNeedInitialFocus(boolean f) { needFocus = f; }
  public void setRenderPriority(RenderPriority p) {}
  public void setCacheMode(int m) { cacheMode = m; }
  public int getCacheMode() { return cacheMode; }
  public void setMixedContentMode(int m) { mixed = m; }
  public int getMixedContentMode() { return mixed; }
  public void setOffscreenPreRaster(boolean f) { offscreen = f; }
  public boolean getOffscreenPreRaster() { return offscreen; }
  public void setSafeBrowsingEnabled(boolean f) { safeBrowsing = f; }
  public boolean getSafeBrowsingEnabled() { return safeBrowsing; }
  public void setForceDark(int m) { forceDark = m; }
  public int getForceDark() { return forceDark; }
  public void setAlgorithmicDarkeningAllowed(boolean f) { darkening = f; }
  public boolean isAlgorithmicDarkeningAllowed() { return darkening; }
  public void setDisabledActionModeMenuItems(int m) { disabledMenu = m; }
  public int getDisabledActionModeMenuItems() { return disabledMenu; }
  public void setNavDump(boolean f) { navDump = f; }
  public boolean getNavDump() { return navDump; }
  public void setTextSize(Object t) { if (t instanceof TextSize) setTextSize((TextSize) t); }
}

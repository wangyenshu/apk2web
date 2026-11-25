package android.webkit;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.AbsoluteLayout;
import a2w.Windows;
import java.util.ArrayList;
import java.util.HashMap;
import org.teavm.jso.JSBody;

/** A DOM iframe laid over the view's rectangle. */
public class WebView extends AbsoluteLayout {
  static final HashMap<Integer, WebView> live = new HashMap<>();
  static final ArrayList<WebView> drawn = new ArrayList<>();
  static int nextId = 1;
  final int mId = nextId++;
  final WebSettings mSettings = new WebSettings();
  WebViewClient mClient = new WebViewClient();
  WebChromeClient mChrome;
  String mUrl, mPendingUrl, mPendingData, mBase;
  boolean mCreated, mShown;
  int lx = -1, ly, lw, lh;

  public WebView(Context c) { super(c); init(); }
  public WebView(Context c, AttributeSet a) { super(c, a); init(); }
  public WebView(Context c, AttributeSet a, int d) { super(c, a, d); init(); }
  void init() { setWillNotDraw(false); setFocusable(true); setFocusableInTouchMode(true); }

  public WebSettings getSettings() { return mSettings; }
  public void setWebViewClient(WebViewClient c) { mClient = c == null ? new WebViewClient() : c; }
  public void setWebChromeClient(WebChromeClient c) { mChrome = c; }
  final HashMap<String, Object> mInterfaces = new HashMap<>();
  public void loadUrl(String url, java.util.Map<String, String> headers) { loadUrl(url); }
  public void loadUrl(String url) {
    if (url != null && url.regionMatches(true, 0, "javascript:", 0, 11)) {
      if (mCreated) evalJS(mId, url.substring(11), true);
      return;
    }
    mUrl = url;
    if (!mCreated) { mPendingUrl = url; mPendingData = null; return; }
    mClient.onPageStarted(this, url, null);
    loadUrlJS(mId, url);
  }
  public void loadData(String data, String mime, String enc) { loadDataWithBaseURL(null, data, mime, enc, null); }
  public void loadDataWithBaseURL(String base, String data, String mime, String enc, String history) {
    mUrl = "about:blank";
    mBase = base;
    if (!mCreated) { mPendingData = data == null ? "" : data; mPendingUrl = null; return; }
    mClient.onPageStarted(this, mUrl, null);
    loadDataJS(mId, data == null ? "" : data, base);
  }
  public String getUrl() { return mUrl; }
  public String getOriginalUrl() { return mUrl; }
  public String getTitle() { return ""; }
  public int getProgress() { return 100; }
  public void reload() { if (mUrl != null && mCreated) loadUrlJS(mId, mUrl); }
  public void stopLoading() {}
  public boolean canGoBack() { return false; }
  public boolean canGoForward() { return false; }
  public void goBack() {}
  public void goForward() {}
  public void clearCache(boolean d) {}
  public void clearHistory() {}
  public void clearFormData() {}
  public void destroy() { removeOverlay(); }
  public void onPause() {}
  public void onResume() {}
  public void pauseTimers() {}
  public void resumeTimers() {}
  public void setInitialScale(int s) {}
  /** the object's methods (those marked @JavascriptInterface) become window[name] in the pages this view loads */
  public void addJavascriptInterface(Object o, String name) {
    if (o == null || name == null) return;
    mInterfaces.put(name, o);
    if (mCreated) ifaceJS(mId, name, true);
  }
  public void removeJavascriptInterface(String name) { mInterfaces.remove(name); if (mCreated) ifaceJS(mId, name, false); }
  public void evaluateJavascript(String script, ValueCallback<String> cb) {
    String r = mCreated ? evalJS(mId, script, false) : "null";
    if (cb != null) post(() -> cb.onReceiveValue(r));
  }
  public static void setWebContentsDebuggingEnabled(boolean b) {}
  /** from a page: window.<name>.<method>(args) (args and result as JSON) */
  public static String jsCall(int id, String name, String method, String argsJson) {
    WebView w = live.get(id);
    Object o = w == null ? null : w.mInterfaces.get(name);
    if (o == null) return "undefined";
    try {
      org.json.JSONArray a = new org.json.JSONArray(argsJson);
      for (Class<?> k = o.getClass(); k != null; k = k.getSuperclass()) {
        int mid = a2w.Reflection.method(k, method, a.length());
        if (mid <= 0) continue;
        Class<?>[] ps = a2w.Reflection.methodParams(mid);
        Object[] args = new Object[ps.length];
        for (int i = 0; i < ps.length; i++) args[i] = fromJs(a.isNull(i) ? null : a.get(i), ps[i]);
        Object r = a2w.Reflection.invoke(mid, o, args);
        if (r == null) return "null";
        if (r instanceof Number || r instanceof Boolean) return String.valueOf(r);
        return org.json.JSONObject.quote(String.valueOf(r));
      }
      android.util.Log.w("WebView", "no JavaScript interface method " + name + "." + method);
    } catch (Throwable e) {
      android.util.Log.e("WebView", "JavaScript interface " + name + "." + method + " threw", e);
    }
    return "undefined";
  }
  static Object fromJs(Object v, Class<?> t) {
    if (t == String.class) return v == null ? (Object) null : v instanceof String ? v : String.valueOf(v);
    double d = v instanceof Number ? ((Number) v).doubleValue() : v instanceof Boolean ? (((Boolean) v) ? 1 : 0) : parse(v);
    if (t == int.class || t == Integer.class) return (int) d;
    if (t == long.class || t == Long.class) return (long) d;
    if (t == float.class || t == Float.class) return (float) d;
    if (t == double.class || t == Double.class) return d;
    if (t == short.class || t == Short.class) return (short) d;
    if (t == byte.class || t == Byte.class) return (byte) d;
    if (t == char.class || t == Character.class) return (char) d;
    if (t == boolean.class || t == Boolean.class) return v instanceof Boolean ? v : d != 0;
    return v;
  }
  static double parse(Object v) { try { return v == null ? 0 : Double.parseDouble(String.valueOf(v)); } catch (NumberFormatException e) { return 0; } }
  public void setDownloadListener(Object l) {}
  public void setScrollBarStyle(int s) {}
  public void setBackgroundColor(int c) { super.setBackgroundColor(c); }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (!mCreated) {
      mCreated = true;
      live.put(mId, this);
      createJS(mId);
      for (String n : mInterfaces.keySet()) ifaceJS(mId, n, true);
      if (mPendingUrl != null) loadUrl(mPendingUrl);
      else if (mPendingData != null) { String d = mPendingData; mPendingData = null; loadDataWithBaseURL(mBase, d, "text/html", null, null); }
    }
  }
  @Override protected void onDetachedFromWindow() { super.onDetachedFromWindow(); hideOverlay(); }
  void removeOverlay() { if (mCreated) { mCreated = false; live.remove(mId); removeJS(mId); } }
  void hideOverlay() { if (mShown) { mShown = false; lx = -1; placeJS(mId, 0, 0, 0, 0, false); } }

  @Override protected void onDraw(Canvas c) {
    super.onDraw(c);
    if (!mCreated) return;
    int[] loc = new int[2];
    getLocationOnScreen(loc);
    drawn.add(this);
    if (!mShown || loc[0] != lx || loc[1] != ly || getWidth() != lw || getHeight() != lh) {
      mShown = true; lx = loc[0]; ly = loc[1]; lw = getWidth(); lh = getHeight();
      placeJS(mId, lx, ly, lw, lh, true);
    }
  }
  /** Windows calls these around a full redraw so overlays of views that were not drawn are hidden. */
  public static void beginFrame() { drawn.clear(); }
  public static void endFrame() { for (WebView w : live.values()) if (!drawn.contains(w)) w.hideOverlay(); }

  /** from the page: 1 loaded, 2 error */
  public static void event(int id, int ev, String url) {
    WebView w = live.get(id);
    if (w == null) return;
    if (ev == 1) { w.mClient.onPageFinished(w, url == null ? w.mUrl : url); if (w.mChrome != null) w.mChrome.onProgressChanged(w, 100); }
    else if (ev == 2) w.mClient.onReceivedError(w, WebViewClient.ERROR_HOST_LOOKUP, "net::ERR_FAILED", url);
  }

  @JSBody(params = "id", script = "HW.web.create(id);") static native void createJS(int id);
  @JSBody(params = "id", script = "HW.web.remove(id);") static native void removeJS(int id);
  @JSBody(params = {"id", "u"}, script = "HW.web.url(id, u);") static native void loadUrlJS(int id, String u);
  @JSBody(params = {"id", "d", "b"}, script = "HW.web.html(id, d, b);") static native void loadDataJS(int id, String d, String b);
  @JSBody(params = {"id", "n", "on"}, script = "HW.web.iface(id, n, on);") static native void ifaceJS(int id, String n, boolean on);
  @JSBody(params = {"id", "c", "u"}, script = "return HW.web.js(id, c, u);") static native String evalJS(int id, String c, boolean urlEncoded);
  @JSBody(params = {"id", "x", "y", "w", "h", "v"}, script = "HW.web.place(id, x, y, w, h, v);") static native void placeJS(int id, int x, int y, int w, int h, boolean v);
}

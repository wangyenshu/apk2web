package android.webkit;
import android.graphics.Bitmap;
public class WebViewClient {
  public static final int ERROR_UNKNOWN = -1, ERROR_HOST_LOOKUP = -2, ERROR_CONNECT = -6, ERROR_TIMEOUT = -8;
  public boolean shouldOverrideUrlLoading(WebView view, String url) { return false; }
  public void onPageStarted(WebView view, String url, Bitmap favicon) {}
  public void onPageFinished(WebView view, String url) {}
  public void onLoadResource(WebView view, String url) {}
  public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {}
  public void onReceivedSslError(WebView view, Object handler, Object error) {}
  public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {}
  public void onScaleChanged(WebView view, float oldScale, float newScale) {}
}

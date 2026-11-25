package android.webkit;
import android.view.View;
public class WebChromeClient {
  public interface CustomViewCallback { void onCustomViewHidden(); }
  public void onProgressChanged(WebView view, int newProgress) {}
  public void onReceivedTitle(WebView view, String title) {}
  public void onShowCustomView(View view, CustomViewCallback callback) {}
  public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {}
  public void onHideCustomView() {}
  public boolean onJsAlert(WebView view, String url, String message, Object result) { return false; }
  public void onCloseWindow(WebView window) {}
}

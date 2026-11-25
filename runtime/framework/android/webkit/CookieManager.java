package android.webkit;
public class CookieManager {
  static final CookieManager I = new CookieManager();
  public static CookieManager getInstance() { return I; }
  public void removeAllCookie() {}
  public void removeSessionCookie() {}
  public void removeExpiredCookie() {}
  public void setAcceptCookie(boolean a) {}
  public boolean acceptCookie() { return true; }
  public void setCookie(String url, String value) {}
  public String getCookie(String url) { return null; }
  public boolean hasCookies() { return false; }
}

package a2w;

/** The emulated display: size in pixels and density, from the page config ("screen": "800x480@240"). */
public final class Screen {
  private Screen() {}
  public static final int W, H, DPI;
  public static final float DENSITY;
  static {
    int w = 800, h = 480, dpi = 240;
    String s = Host.config("screen");
    if (s != null) {
      try {
        int x = s.indexOf('x'), at = s.indexOf('@');
        w = Integer.parseInt(s.substring(0, x));
        h = Integer.parseInt(s.substring(x + 1, at < 0 ? s.length() : at));
        if (at > 0) dpi = Integer.parseInt(s.substring(at + 1));
      } catch (RuntimeException e) { /* keep defaults */ }
    }
    W = w; H = h; DPI = dpi;
    DENSITY = dpi / 160f;
  }
  public static boolean landscape() { return W > H; }
}

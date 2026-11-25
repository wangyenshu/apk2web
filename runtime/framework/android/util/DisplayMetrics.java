package android.util;

public class DisplayMetrics {
  public static final int DENSITY_LOW = 120, DENSITY_MEDIUM = 160, DENSITY_TV = 213, DENSITY_HIGH = 240,
      DENSITY_XHIGH = 320, DENSITY_XXHIGH = 480, DENSITY_DEFAULT = DENSITY_MEDIUM;
  public static final int DENSITY_DEVICE = a2w.Screen.DPI;
  public int widthPixels, heightPixels, densityDpi;
  public float density, scaledDensity, xdpi, ydpi;
  public DisplayMetrics() {}
  public void setTo(DisplayMetrics o) { widthPixels = o.widthPixels; heightPixels = o.heightPixels; density = o.density; densityDpi = o.densityDpi; scaledDensity = o.scaledDensity; xdpi = o.xdpi; ydpi = o.ydpi; }
  public void setToDefaults() { widthPixels = a2w.Screen.W; heightPixels = a2w.Screen.H; density = a2w.Screen.DENSITY; densityDpi = a2w.Screen.DPI; scaledDensity = a2w.Screen.DENSITY; xdpi = ydpi = a2w.Screen.DPI; }
  public String toString() { return "DisplayMetrics{density=" + density + ", width=" + widthPixels + ", height=" + heightPixels + "}"; }
}

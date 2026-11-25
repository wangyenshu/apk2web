package android.content.res;

import android.util.StateSet;

/** android.content.res.ColorStateList: colors per state spec, the first matching spec winning. */
public class ColorStateList implements android.os.Parcelable {
  final int[][] specs;
  final int[] colors;
  final int def;
  private final boolean opaque;
  private int changingConfigurations;

  public ColorStateList(int[][] states, int[] colors) {
    specs = states; this.colors = colors;
    int d = colors.length > 0 ? colors[0] : 0xff000000;
    for (int i = 0; i < states.length; i++) if (states[i].length == 0) { d = colors[i]; break; }
    def = d;
    boolean op = colors.length > 0;
    for (int c : colors) if ((c >>> 24) != 0xff) { op = false; break; }
    opaque = op;
  }
  public static ColorStateList valueOf(int c) { return new ColorStateList(new int[][] {new int[0]}, new int[] {c}); }
  /** a <selector> of <item android:color=... android:state_x=...> from a compiled resource parser */
  @Deprecated public static ColorStateList createFromXml(Resources r, org.xmlpull.v1.XmlPullParser parser)
      throws org.xmlpull.v1.XmlPullParserException, java.io.IOException { return createFromXml(r, parser, null); }
  public static ColorStateList createFromXml(Resources r, org.xmlpull.v1.XmlPullParser parser, Resources.Theme theme)
      throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
    if (!(parser instanceof a2w.XmlResParser)) throw new org.xmlpull.v1.XmlPullParserException("not a compiled resource parser");
    a2w.XmlNode n = ((a2w.XmlResParser) parser).currentNode();
    if (n == null) throw new org.xmlpull.v1.XmlPullParserException("No start tag found");
    if (!"selector".equals(n.tag)) throw new org.xmlpull.v1.XmlPullParserException(parser.getPositionDescription() + ": invalid color state list tag " + n.tag);
    return a2w.Drawables.colorList(r, n);
  }
  public boolean isStateful() { return specs.length >= 1 && specs[0].length > 0; }
  public boolean isOpaque() { return opaque; }
  public int getColorForState(int[] state, int defaultColor) {
    for (int i = 0; i < specs.length; i++) if (StateSet.stateSetMatches(specs[i], state)) return colors[i];
    return defaultColor;
  }
  public int getDefaultColor() { return def; }
  public int getChangingConfigurations() { return changingConfigurations; }
  public ColorStateList withAlpha(int a) {
    int[] c = new int[colors.length];
    for (int i = 0; i < c.length; i++) c[i] = (colors[i] & 0xffffff) | (a << 24);
    return new ColorStateList(specs, c);
  }
  /** the colors with the given CIE L* (lightness), keeping their a*, b* and alpha */
  public ColorStateList withLStar(float lStar) {
    int[] c = new int[colors.length];
    for (int i = 0; i < c.length; i++) {
      double[] lab = new double[3];
      androidx_lab(colors[i], lab);
      c[i] = (colors[i] & 0xff000000) | (fromLab(lStar, lab[1], lab[2]) & 0xffffff);
    }
    return new ColorStateList(specs, c);
  }
  private static double lin(int v) { double s = v / 255.0; return s <= 0.04045 ? s / 12.92 : Math.pow((s + 0.055) / 1.055, 2.4); }
  private static int gam(double l) { double s = l <= 0.0031308 ? 12.92 * l : 1.055 * Math.pow(l, 1 / 2.4) - 0.055; return (int) Math.max(0, Math.min(255, Math.round(s * 255))); }
  private static double f(double t) { return t > 216.0 / 24389 ? Math.cbrt(t) : (24389.0 / 27 * t + 16) / 116; }
  private static double fi(double t) { double t3 = t * t * t; return t3 > 216.0 / 24389 ? t3 : (116 * t - 16) / (24389.0 / 27); }
  private static final double XN = 0.95047, YN = 1.0, ZN = 1.08883;
  private static void androidx_lab(int c, double[] out) {
    double r = lin((c >> 16) & 255), g = lin((c >> 8) & 255), b = lin(c & 255);
    double x = 0.4124 * r + 0.3576 * g + 0.1805 * b, y = 0.2126 * r + 0.7152 * g + 0.0722 * b, z = 0.0193 * r + 0.1192 * g + 0.9505 * b;
    double fx = f(x / XN), fy = f(y / YN), fz = f(z / ZN);
    out[0] = 116 * fy - 16; out[1] = 500 * (fx - fy); out[2] = 200 * (fy - fz);
  }
  private static int fromLab(double l, double a, double bb) {
    double fy = (l + 16) / 116, fx = fy + a / 500, fz = fy - bb / 200;
    double x = XN * fi(fx), y = YN * fi(fy), z = ZN * fi(fz);
    double r = 3.2406 * x - 1.5372 * y - 0.4986 * z, g = -0.9689 * x + 1.8758 * y + 0.0415 * z, b = 0.0557 * x - 0.2040 * y + 1.0570 * z;
    return gam(r) << 16 | gam(g) << 8 | gam(b);
  }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {
    p.writeInt(specs.length);
    for (int[] s : specs) p.writeIntArray(s);
    p.writeIntArray(colors);
  }
  public static final android.os.Parcelable.Creator<ColorStateList> CREATOR = new android.os.Parcelable.Creator<ColorStateList>() {
    public ColorStateList createFromParcel(android.os.Parcel p) {
      int n = p.readInt();
      int[][] s = new int[n][];
      for (int i = 0; i < n; i++) s[i] = p.createIntArray();
      return new ColorStateList(s, p.createIntArray());
    }
    public ColorStateList[] newArray(int size) { return new ColorStateList[size]; }
  };
  public String toString() {
    StringBuilder b = new StringBuilder("ColorStateList{mThemeAttrs=null mChangingConfigurations=").append(changingConfigurations).append(" mStateSpecs=[");
    for (int i = 0; i < specs.length; i++) { if (i > 0) b.append(", "); b.append(java.util.Arrays.toString(specs[i])); }
    return b.append("] mColors=").append(java.util.Arrays.toString(colors)).append(" mDefaultColor=").append(def).append('}').toString();
  }
}

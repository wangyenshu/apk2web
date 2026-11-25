package android.util;

/** A typed resource value, with Android's packed "complex" encoding for dimensions and fractions. */
public class TypedValue {
  public static final int TYPE_NULL = 0, TYPE_REFERENCE = 1, TYPE_ATTRIBUTE = 2, TYPE_STRING = 3, TYPE_FLOAT = 4,
      TYPE_DIMENSION = 5, TYPE_FRACTION = 6, TYPE_DYNAMIC_REFERENCE = 7, TYPE_DYNAMIC_ATTRIBUTE = 8, TYPE_FIRST_INT = 16,
      TYPE_INT_DEC = 16, TYPE_INT_HEX = 17, TYPE_INT_BOOLEAN = 18, TYPE_FIRST_COLOR_INT = 28, TYPE_INT_COLOR_ARGB8 = 28,
      TYPE_INT_COLOR_RGB8 = 29, TYPE_INT_COLOR_ARGB4 = 30, TYPE_INT_COLOR_RGB4 = 31, TYPE_LAST_COLOR_INT = 31, TYPE_LAST_INT = 31;
  public static final int COMPLEX_UNIT_SHIFT = 0, COMPLEX_UNIT_MASK = 15, COMPLEX_UNIT_PX = 0, COMPLEX_UNIT_DIP = 1,
      COMPLEX_UNIT_SP = 2, COMPLEX_UNIT_PT = 3, COMPLEX_UNIT_IN = 4, COMPLEX_UNIT_MM = 5, COMPLEX_UNIT_FRACTION = 0,
      COMPLEX_UNIT_FRACTION_PARENT = 1, COMPLEX_RADIX_SHIFT = 4, COMPLEX_RADIX_MASK = 3, COMPLEX_RADIX_23p0 = 0,
      COMPLEX_RADIX_16p7 = 1, COMPLEX_RADIX_8p15 = 2, COMPLEX_RADIX_0p23 = 3, COMPLEX_MANTISSA_SHIFT = 8,
      COMPLEX_MANTISSA_MASK = 0xffffff;
  public static final int DATA_NULL_UNDEFINED = 0, DATA_NULL_EMPTY = 1, DENSITY_DEFAULT = 0, DENSITY_NONE = 0xffff;

  public int type, data, assetCookie, resourceId, changingConfigurations = -1, density, sourceResourceId;
  public CharSequence string;
  /** web: the float a dimension or float value was made from (kept beside the packed data) */
  public float fdata;

  private static final float MANTISSA_MULT = 1.0f / (1 << COMPLEX_MANTISSA_SHIFT);
  private static final float[] RADIX_MULTS = {
      1.0f * MANTISSA_MULT, 1.0f / (1 << 7) * MANTISSA_MULT, 1.0f / (1 << 15) * MANTISSA_MULT, 1.0f / (1 << 23) * MANTISSA_MULT};

  public static float complexToFloat(int complex) {
    return (complex & (COMPLEX_MANTISSA_MASK << COMPLEX_MANTISSA_SHIFT)) * RADIX_MULTS[(complex >> COMPLEX_RADIX_SHIFT) & COMPLEX_RADIX_MASK];
  }
  /** packs value with unit (one of COMPLEX_UNIT_*) the way aapt does */
  public static int createComplex(float value, int unit) {
    boolean neg = value < 0;
    if (neg) value = -value;
    long bits = (long) (value * (1 << 23) + .5f);
    int radix, shift;
    if ((bits & 0x7fffff) == 0) { radix = COMPLEX_RADIX_23p0; shift = 23; }
    else if ((bits & 0xffffffffff800000L) == 0) { radix = COMPLEX_RADIX_0p23; shift = 0; }
    else if ((bits & 0xffffffff80000000L) == 0) { radix = COMPLEX_RADIX_8p15; shift = 8; }
    else if ((bits & 0xffffff8000000000L) == 0) { radix = COMPLEX_RADIX_16p7; shift = 16; }
    else { radix = COMPLEX_RADIX_23p0; shift = 23; }
    int mantissa = (int) ((bits >> shift) & COMPLEX_MANTISSA_MASK);
    if (neg) mantissa = (-mantissa) & COMPLEX_MANTISSA_MASK;
    return (radix << COMPLEX_RADIX_SHIFT) | (mantissa << COMPLEX_MANTISSA_SHIFT) | (unit & COMPLEX_UNIT_MASK);
  }
  public static int createComplexDimension(float value, int units) { return createComplex(value, units); }
  public static int createComplexDimension(int value, int units) { return createComplex(value, units); }

  public static float complexToDimension(int data, DisplayMetrics m) {
    return applyDimension((data >> COMPLEX_UNIT_SHIFT) & COMPLEX_UNIT_MASK, complexToFloat(data), m);
  }
  public static int complexToDimensionPixelOffset(int data, DisplayMetrics m) {
    return (int) applyDimension((data >> COMPLEX_UNIT_SHIFT) & COMPLEX_UNIT_MASK, complexToFloat(data), m);
  }
  public static int complexToDimensionPixelSize(int data, DisplayMetrics m) {
    float value = complexToFloat(data);
    float f = applyDimension((data >> COMPLEX_UNIT_SHIFT) & COMPLEX_UNIT_MASK, value, m);
    int res = (int) (f >= 0 ? f + 0.5f : f - 0.5f);
    if (res != 0) return res;
    if (value == 0) return 0;
    return value > 0 ? 1 : -1;
  }
  @Deprecated public static float complexToDimensionNoisy(int data, DisplayMetrics m) { return complexToDimension(data, m); }
  public static float complexToFraction(int data, float base, float pbase) {
    switch ((data >> COMPLEX_UNIT_SHIFT) & COMPLEX_UNIT_MASK) {
      case COMPLEX_UNIT_FRACTION: return complexToFloat(data) * base;
      case COMPLEX_UNIT_FRACTION_PARENT: return complexToFloat(data) * pbase;
    }
    return 0;
  }

  public static float applyDimension(int unit, float value, DisplayMetrics m) {
    switch (unit) {
      case COMPLEX_UNIT_PX: return value;
      case COMPLEX_UNIT_DIP: return value * m.density;
      case COMPLEX_UNIT_SP: return value * m.scaledDensity;
      case COMPLEX_UNIT_PT: return value * m.xdpi * (1.0f / 72);
      case COMPLEX_UNIT_IN: return value * m.xdpi;
      case COMPLEX_UNIT_MM: return value * m.xdpi * (1.0f / 25.4f);
    }
    return 0;
  }
  /** the inverse of applyDimension (API 34) */
  public static float deriveDimension(int unit, float px, DisplayMetrics m) {
    switch (unit) {
      case COMPLEX_UNIT_PX: return px;
      case COMPLEX_UNIT_DIP: return m.density == 0 ? 0 : px / m.density;
      case COMPLEX_UNIT_SP: return m.scaledDensity == 0 ? 0 : px / m.scaledDensity;
      case COMPLEX_UNIT_PT: return m.xdpi == 0 ? 0 : px / m.xdpi / (1.0f / 72);
      case COMPLEX_UNIT_IN: return m.xdpi == 0 ? 0 : px / m.xdpi;
      case COMPLEX_UNIT_MM: return m.xdpi == 0 ? 0 : px / m.xdpi / (1.0f / 25.4f);
    }
    throw new IllegalArgumentException("Invalid unitToConvertTo " + unit);
  }
  public static float convertPixelsToDimension(int unit, float px, DisplayMetrics m) { return deriveDimension(unit, px, m); }
  public static float convertDimensionToPixels(int unit, float value, DisplayMetrics m) { return applyDimension(unit, value, m); }

  public final float getFloat() { return type == TYPE_FLOAT ? Float.intBitsToFloat(data) : fdata; }
  public float getDimension(DisplayMetrics m) { return complexToDimension(data, m); }
  public int getComplexUnit() { return COMPLEX_UNIT_MASK & (data >> COMPLEX_UNIT_SHIFT); }
  public float getFraction(float base, float pbase) { return complexToFraction(data, base, pbase); }
  public boolean isColorType() { return type >= TYPE_FIRST_COLOR_INT && type <= TYPE_LAST_COLOR_INT; }

  /** sets this to a dimension of value in unit */
  public TypedValue setDimension(float value, int unit) { type = TYPE_DIMENSION; data = createComplex(value, unit); fdata = applyDimension(unit, value, a2w.Values.METRICS); return this; }
  public TypedValue setFloat(float value) { type = TYPE_FLOAT; data = Float.floatToIntBits(value); fdata = value; return this; }

  public static String coerceToString(int type, int data) {
    switch (type) {
      case TYPE_NULL: return null;
      case TYPE_REFERENCE: return "@" + data;
      case TYPE_ATTRIBUTE: return "?" + data;
      case TYPE_FLOAT: return Float.toString(Float.intBitsToFloat(data));
      case TYPE_DIMENSION: return Float.toString(complexToFloat(data)) + DIMENSION_UNITS[(data >> COMPLEX_UNIT_SHIFT) & COMPLEX_UNIT_MASK & 7];
      case TYPE_FRACTION: return Float.toString(complexToFloat(data) * 100) + (((data >> COMPLEX_UNIT_SHIFT) & COMPLEX_UNIT_MASK) == 0 ? "%" : "%p");
      case TYPE_INT_HEX: return "0x" + Integer.toHexString(data);
      case TYPE_INT_BOOLEAN: return data != 0 ? "true" : "false";
    }
    if (type >= TYPE_FIRST_COLOR_INT && type <= TYPE_LAST_COLOR_INT) return "#" + Integer.toHexString(data);
    if (type >= TYPE_FIRST_INT && type <= TYPE_LAST_INT) return Integer.toString(data);
    return null;
  }
  private static final String[] DIMENSION_UNITS = {"px", "dip", "sp", "pt", "in", "mm", "", ""};
  public final CharSequence coerceToString() { return type == TYPE_STRING ? string : string != null ? string : coerceToString(type, data); }

  public void setTo(TypedValue o) {
    type = o.type; string = o.string; data = o.data; fdata = o.fdata; assetCookie = o.assetCookie; resourceId = o.resourceId;
    density = o.density; changingConfigurations = o.changingConfigurations; sourceResourceId = o.sourceResourceId;
  }
  public int getChangingConfigurations() { return changingConfigurations; }
  public int getSourceResourceId() { return sourceResourceId; }
  @Override public String toString() {
    return "TypedValue{t=0x" + Integer.toHexString(type) + "/d=0x" + Integer.toHexString(data)
        + (type == TYPE_STRING ? " \"" + (string != null ? string : "<null>") + "\"" : "")
        + (assetCookie != 0 ? " a=" + assetCookie : "") + (resourceId != 0 ? " r=0x" + Integer.toHexString(resourceId) : "") + "}";
  }
}

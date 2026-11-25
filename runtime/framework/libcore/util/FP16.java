package libcore.util;

/**
 * IEEE 754 half-precision (binary16) values stored in shorts: conversions to and from float (round to nearest even),
 * comparisons, rounding and classification. Written for apk2web; it provides the members android.util.Half uses
 * from ART's libcore.util.FP16.
 */
public final class FP16 {
  private FP16() {}
  public static final int SIZE = 16;
  public static final short EPSILON = (short) 0x1400, LOWEST_VALUE = (short) 0xfbff, MAX_VALUE = (short) 0x7bff, MIN_NORMAL = (short) 0x0400,
      MIN_VALUE = (short) 0x0001, NaN = (short) 0x7e00, NEGATIVE_INFINITY = (short) 0xfc00, NEGATIVE_ZERO = (short) 0x8000,
      POSITIVE_INFINITY = (short) 0x7c00, POSITIVE_ZERO = (short) 0x0000;
  public static final int MAX_EXPONENT = 15, MIN_EXPONENT = -14;
  public static final int SIGN_SHIFT = 15, SIGN_MASK = 0x8000, EXPONENT_SHIFT = 10, EXPONENT_MASK = 0x1f, SIGNIFICAND_MASK = 0x3ff,
      EXPONENT_SIGNIFICAND_MASK = 0x7fff, EXPONENT_BIAS = 15, SHIFTED_EXPONENT_MASK = EXPONENT_MASK << EXPONENT_SHIFT;

  private static final int FP32_SIGN_SHIFT = 31, FP32_EXPONENT_SHIFT = 23, FP32_SHIFTED_EXPONENT_MASK = 0xff, FP32_SIGNIFICAND_MASK = 0x7fffff,
      FP32_EXPONENT_BIAS = 127, FP32_QNAN_MASK = 0x400000, FP32_DENORMAL_MAGIC = 126 << 23;
  private static final float FP32_DENORMAL_FLOAT = Float.intBitsToFloat(FP32_DENORMAL_MAGIC);

  public static float toFloat(short h) {
    int bits = h & 0xffff;
    int s = bits & SIGN_MASK, e = (bits >>> EXPONENT_SHIFT) & EXPONENT_MASK, m = bits & SIGNIFICAND_MASK;
    int outE = 0, outM = 0;
    if (e == 0) {
      if (m != 0) {   // subnormal: m * 2^-24
        float o = Float.intBitsToFloat(FP32_DENORMAL_MAGIC + m) - FP32_DENORMAL_FLOAT;
        return s == 0 ? o : -o;
      }
    } else {
      outM = m << 13;
      if (e == 0x1f) { outE = 0xff; if (outM != 0) outM |= FP32_QNAN_MASK; }
      else outE = e - EXPONENT_BIAS + FP32_EXPONENT_BIAS;
    }
    return Float.intBitsToFloat((s << 16) | (outE << FP32_EXPONENT_SHIFT) | outM);
  }

  public static short toHalf(float f) {
    int bits = Float.floatToRawIntBits(f);
    int s = bits >>> FP32_SIGN_SHIFT, e = (bits >>> FP32_EXPONENT_SHIFT) & FP32_SHIFTED_EXPONENT_MASK, m = bits & FP32_SIGNIFICAND_MASK;
    int outE = 0, outM = 0;
    if (e == 0xff) {   // infinity or NaN
      outE = 0x1f;
      outM = m != 0 ? 0x200 : 0;
    } else {
      e = e - FP32_EXPONENT_BIAS + EXPONENT_BIAS;
      if (e >= 0x1f) {   // overflow
        outE = 0x1f;
      } else if (e <= 0) {   // subnormal or zero
        if (e >= -10) {
          m |= 0x800000;
          int shift = 14 - e;
          outM = m >> shift;
          int low = m & ((1 << shift) - 1), half = 1 << (shift - 1);
          if (low + (outM & 1) > half) outM++;   // round to nearest, ties to even
        }
      } else {
        outE = e;
        outM = m >> 13;
        if ((m & 0x1fff) + (outM & 1) > 0x1000) outM++;   // a carry into the exponent is the right result
      }
    }
    return (short) ((s << SIGN_SHIFT) | ((outE << EXPONENT_SHIFT) + outM));
  }

  public static boolean isNaN(short h) { return (h & EXPONENT_SIGNIFICAND_MASK) > POSITIVE_INFINITY; }
  public static boolean isInfinite(short h) { return (h & EXPONENT_SIGNIFICAND_MASK) == POSITIVE_INFINITY; }
  public static boolean isNormalized(short h) { return (h & POSITIVE_INFINITY) != 0 && (h & POSITIVE_INFINITY) != POSITIVE_INFINITY; }

  /** a value whose integer order is the order of the halves (NaN excluded) */
  private static int ordered(short h) { return (h & SIGN_MASK) != 0 ? SIGN_MASK - (h & 0xffff) : h & 0xffff; }
  public static boolean equals(short x, short y) {
    if (isNaN(x) || isNaN(y)) return false;
    return x == y || ((x | y) & EXPONENT_SIGNIFICAND_MASK) == 0;
  }
  public static boolean less(short x, short y) { return !isNaN(x) && !isNaN(y) && ordered(x) < ordered(y); }
  public static boolean lessEquals(short x, short y) { return !isNaN(x) && !isNaN(y) && ordered(x) <= ordered(y); }
  public static boolean greater(short x, short y) { return !isNaN(x) && !isNaN(y) && ordered(x) > ordered(y); }
  public static boolean greaterEquals(short x, short y) { return !isNaN(x) && !isNaN(y) && ordered(x) >= ordered(y); }
  /** Float.compare's order: -0 before +0, NaN after everything */
  public static int compare(short x, short y) {
    if (less(x, y)) return -1;
    if (greater(x, y)) return 1;
    short xb = isNaN(x) ? NaN : x, yb = isNaN(y) ? NaN : y;
    return xb == yb ? 0 : (xb < yb ? -1 : 1);
  }
  public static short min(short x, short y) {
    if (isNaN(x) || isNaN(y)) return NaN;
    if ((x & EXPONENT_SIGNIFICAND_MASK) == 0 && (y & EXPONENT_SIGNIFICAND_MASK) == 0) return (x & SIGN_MASK) != 0 ? x : y;
    return ordered(x) < ordered(y) ? x : y;
  }
  public static short max(short x, short y) {
    if (isNaN(x) || isNaN(y)) return NaN;
    if ((x & EXPONENT_SIGNIFICAND_MASK) == 0 && (y & EXPONENT_SIGNIFICAND_MASK) == 0) return (x & SIGN_MASK) != 0 ? y : x;
    return ordered(x) > ordered(y) ? x : y;
  }
  // every half is exact as a float and every integral result of these is exact as a half
  public static short ceil(short h) { return toHalf((float) Math.ceil(toFloat(h))); }
  public static short floor(short h) { return toHalf((float) Math.floor(toFloat(h))); }
  public static short rint(short h) { return toHalf((float) Math.rint(toFloat(h))); }
  public static short trunc(short h) { float f = toFloat(h); return toHalf((float) (f < 0 ? Math.ceil(f) : Math.floor(f))); }

  public static String toHexString(short h) {
    StringBuilder o = new StringBuilder();
    int bits = h & 0xffff, s = bits >>> SIGN_SHIFT, e = (bits >>> EXPONENT_SHIFT) & EXPONENT_MASK, m = bits & SIGNIFICAND_MASK;
    if (e == 0x1f) {
      if (m == 0) { if (s != 0) o.append('-'); o.append("Infinity"); }
      else o.append("NaN");
      return o.toString();
    }
    if (s == 1) o.append('-');
    if (e == 0 && m == 0) return o.append("0x0.0p0").toString();
    String digits = String.format("%03x", m << 2).replaceFirst("0+$", "");
    if (digits.isEmpty()) digits = "0";
    if (e == 0) o.append("0x0.").append(digits).append("p-14");
    else o.append("0x1.").append(digits).append('p').append(e - EXPONENT_BIAS);
    return o.toString();
  }
}

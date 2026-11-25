package a2w.jdk.util;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;

/** java.util.HexFormat: hexadecimal formatting and parsing of bytes and primitive values, with an optional delimiter,
 *  prefix and suffix around each formatted byte. */
public final class HexFormat {
  private static final char[] LOWER = "0123456789abcdef".toCharArray(), UPPER = "0123456789ABCDEF".toCharArray();
  private static final HexFormat HEX = new HexFormat("", "", "", LOWER);
  private final String delimiter, prefix, suffix;
  private final char[] digits;
  private HexFormat(String delimiter, String prefix, String suffix, char[] digits) {
    this.delimiter = Objects.requireNonNull(delimiter, "delimiter"); this.prefix = Objects.requireNonNull(prefix, "prefix");
    this.suffix = Objects.requireNonNull(suffix, "suffix"); this.digits = digits;
  }
  public static HexFormat of() { return HEX; }
  public static HexFormat ofDelimiter(String delimiter) { return new HexFormat(delimiter, "", "", LOWER); }
  public HexFormat withDelimiter(String delimiter) { return new HexFormat(delimiter, prefix, suffix, digits); }
  public HexFormat withPrefix(String prefix) { return new HexFormat(delimiter, prefix, suffix, digits); }
  public HexFormat withSuffix(String suffix) { return new HexFormat(delimiter, prefix, suffix, digits); }
  public HexFormat withUpperCase() { return new HexFormat(delimiter, prefix, suffix, UPPER); }
  public HexFormat withLowerCase() { return new HexFormat(delimiter, prefix, suffix, LOWER); }
  public String delimiter() { return delimiter; }
  public String prefix() { return prefix; }
  public String suffix() { return suffix; }
  public boolean isUpperCase() { return digits == UPPER; }

  public String formatHex(byte[] bytes) { return formatHex(bytes, 0, bytes.length); }
  public String formatHex(byte[] bytes, int fromIndex, int toIndex) { return formatHex(new StringBuilder(), bytes, fromIndex, toIndex).toString(); }
  public <A extends Appendable> A formatHex(A out, byte[] bytes) { return formatHex(out, bytes, 0, bytes.length); }
  public <A extends Appendable> A formatHex(A out, byte[] bytes, int fromIndex, int toIndex) {
    Objects.requireNonNull(out, "out");
    Objects.checkFromToIndex(fromIndex, toIndex, bytes.length);
    try {
      for (int i = fromIndex; i < toIndex; i++) {
        if (i > fromIndex) out.append(delimiter);
        out.append(prefix).append(toHighHexDigit(bytes[i])).append(toLowHexDigit(bytes[i])).append(suffix);
      }
    } catch (IOException e) { throw new UncheckedIOException(e.getMessage(), e); }
    return out;
  }

  public byte[] parseHex(CharSequence string) { return parseHex(string, 0, string.length()); }
  public byte[] parseHex(char[] chars, int fromIndex, int toIndex) { Objects.checkFromToIndex(fromIndex, toIndex, chars.length); return parseHex(new String(chars, fromIndex, toIndex - fromIndex)); }
  public byte[] parseHex(CharSequence string, int fromIndex, int toIndex) {
    Objects.requireNonNull(string, "string");
    Objects.checkFromToIndex(fromIndex, toIndex, string.length());
    String s = string.subSequence(fromIndex, toIndex).toString();
    if (s.isEmpty()) return new byte[0];
    int stride = prefix.length() + 2 + suffix.length() + delimiter.length();
    int len = s.length() + delimiter.length();
    if (len % stride != 0) throw new IllegalArgumentException("extra or missing delimiters or values consisting of prefix, two hexadecimal digits, and suffix");
    byte[] out = new byte[len / stride];
    int p = 0;
    for (int i = 0; i < out.length; i++) {
      if (i > 0) { expect(s, p, delimiter); p += delimiter.length(); }
      expect(s, p, prefix); p += prefix.length();
      out[i] = (byte) ((fromHexDigit(s.charAt(p)) << 4) | fromHexDigit(s.charAt(p + 1))); p += 2;
      expect(s, p, suffix); p += suffix.length();
    }
    return out;
  }
  private static void expect(String s, int at, String what) {
    if (!s.startsWith(what, at)) throw new IllegalArgumentException("found: \"" + s.substring(at, Math.min(s.length(), at + what.length())) + "\", expected: \"" + what + "\", index: " + at);
  }

  public char toLowHexDigit(int value) { return digits[value & 0xf]; }
  public char toHighHexDigit(int value) { return digits[(value >> 4) & 0xf]; }
  public <A extends Appendable> A toHexDigits(A out, byte value) {
    Objects.requireNonNull(out, "out");
    try { out.append(toHighHexDigit(value)).append(toLowHexDigit(value)); } catch (IOException e) { throw new UncheckedIOException(e.getMessage(), e); }
    return out;
  }
  public String toHexDigits(byte value) { return toHexDigits(value & 0xffL, 2); }
  public String toHexDigits(char value) { return toHexDigits(value & 0xffffL, 4); }
  public String toHexDigits(short value) { return toHexDigits(value & 0xffffL, 4); }
  public String toHexDigits(int value) { return toHexDigits(value & 0xffffffffL, 8); }
  public String toHexDigits(long value) { return toHexDigits(value, 16); }
  public String toHexDigits(long value, int digits) {
    if (digits < 0 || digits > 16) throw new IllegalArgumentException("number of digits: " + digits);
    char[] c = new char[digits];
    for (int i = digits - 1; i >= 0; i--) { c[i] = this.digits[(int) (value & 0xf)]; value >>>= 4; }
    return new String(c);
  }

  public static boolean isHexDigit(int ch) { return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F'); }
  public static int fromHexDigit(int ch) {
    if (ch >= '0' && ch <= '9') return ch - '0';
    if (ch >= 'a' && ch <= 'f') return ch - 'a' + 10;
    if (ch >= 'A' && ch <= 'F') return ch - 'A' + 10;
    throw new NumberFormatException("not a hexadecimal digit: \"" + (char) ch + "\" = " + ch);
  }
  public static int fromHexDigits(CharSequence string) { return fromHexDigits(string, 0, string.length()); }
  public static int fromHexDigits(CharSequence string, int fromIndex, int toIndex) {
    Objects.requireNonNull(string, "string");
    Objects.checkFromToIndex(fromIndex, toIndex, string.length());
    if (toIndex - fromIndex > 8) throw new IllegalArgumentException("string length greater than 8: " + (toIndex - fromIndex));
    int v = 0;
    for (int i = fromIndex; i < toIndex; i++) v = (v << 4) | fromHexDigit(string.charAt(i));
    return v;
  }
  public static long fromHexDigitsToLong(CharSequence string) { return fromHexDigitsToLong(string, 0, string.length()); }
  public static long fromHexDigitsToLong(CharSequence string, int fromIndex, int toIndex) {
    Objects.requireNonNull(string, "string");
    Objects.checkFromToIndex(fromIndex, toIndex, string.length());
    if (toIndex - fromIndex > 16) throw new IllegalArgumentException("string length greater than 16: " + (toIndex - fromIndex));
    long v = 0;
    for (int i = fromIndex; i < toIndex; i++) v = (v << 4) | fromHexDigit(string.charAt(i));
    return v;
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof HexFormat)) return false;
    HexFormat f = (HexFormat) o;
    return digits == f.digits && delimiter.equals(f.delimiter) && prefix.equals(f.prefix) && suffix.equals(f.suffix);
  }
  public int hashCode() { return Objects.hash(delimiter, prefix, suffix, isUpperCase()); }
  public String toString() { return "uppercase: " + isUpperCase() + ", delimiter: \"" + delimiter + "\", prefix: \"" + prefix + "\", suffix: \"" + suffix + "\""; }
}

package a2w.jdk.text;

/**
 * The collation elements of a string for a2w.jdk.text.Collator: per code point of its compatibility decomposition, a
 * primary weight (group << 21 | value: 1 whitespace, 2 punctuation, 3 symbols, 4 numbers by value, 5 letters by their
 * lowercase form, Katakana as Hiragana), a secondary weight (accents) and a tertiary one (case, compatibility forms).
 * Control and format characters are ignorable.
 */
final class Collation {
  private Collation() {}
  static final int COMMON = 5;
  /** elements of s: primaries[i] (0 for a mark), secondaries[i], tertiaries[i] */
  static int[][] elements(String s) {
    String d = Normalizer.normalize(s, Normalizer.Form.NFKD);
    String canonical = Normalizer.normalize(s, Normalizer.Form.NFD);
    boolean compat = !d.equals(canonical);
    int n = d.length();
    int[] p = new int[n + 8], sec = new int[n + 8], ter = new int[n + 8];
    int k = 0;
    for (int i = 0; i < n; ) {
      int cp = d.codePointAt(i);
      i += Character.charCount(cp);
      int type = Character.getType(cp);
      if (type == Character.CONTROL || type == Character.FORMAT) continue;
      if (k + 3 >= p.length) { p = java.util.Arrays.copyOf(p, p.length * 2); sec = java.util.Arrays.copyOf(sec, sec.length * 2); ter = java.util.Arrays.copyOf(ter, ter.length * 2); }
      if (type == Character.NON_SPACING_MARK || type == Character.ENCLOSING_MARK || type == Character.COMBINING_SPACING_MARK || a2w.UcdData.combiningClass(cp) != 0) {
        p[k] = 0; sec[k] = COMMON + 1 + Math.min(240, Math.max(0, cp - 0x300)); ter[k] = 1; k++;
        continue;
      }
      // expansions of letters that sort as two
      String exp = cp == 0xDF ? "ss" : cp == 0x1E9E ? "SS" : cp == 0xE6 ? "ae" : cp == 0xC6 ? "AE" : cp == 0x153 ? "oe" : cp == 0x152 ? "OE" : null;
      if (exp != null) {
        for (int j = 0; j < 2; j++) { char c = exp.charAt(j); p[k] = letter(Character.toLowerCase(c)); sec[k] = COMMON; ter[k] = (Character.isUpperCase(c) ? 2 : 1) + 4; k++; }
        continue;
      }
      int t = Character.isUpperCase(cp) || type == Character.TITLECASE_LETTER ? 2 : 1;
      if (compat) t += 4;
      int w;
      if (Character.isWhitespace(cp) || type == Character.SPACE_SEPARATOR || type == Character.LINE_SEPARATOR || type == Character.PARAGRAPH_SEPARATOR) w = 1 << 21 | cp;
      else if (type >= Character.DASH_PUNCTUATION && type <= Character.OTHER_PUNCTUATION || type == Character.INITIAL_QUOTE_PUNCTUATION || type == Character.FINAL_QUOTE_PUNCTUATION) w = 2 << 21 | cp;
      else if (type >= Character.MATH_SYMBOL && type <= Character.OTHER_SYMBOL) w = 3 << 21 | cp;
      else if (type == Character.DECIMAL_DIGIT_NUMBER || type == Character.LETTER_NUMBER || type == Character.OTHER_NUMBER) {
        double v = a2w.UcdData.numericValue(cp);
        w = Double.isNaN(v) ? 3 << 21 | cp : 4 << 21 | (int) Math.min((1 << 21) - 1, Math.max(0, v * 16));
        if (type != Character.DECIMAL_DIGIT_NUMBER || cp > '9') t += 8;
      } else {
        int lower = Character.toLowerCase(cp);
        if (lower >= 0x30A1 && lower <= 0x30F6) { lower -= 0x60; t += 16; }   // Katakana as Hiragana, a tertiary difference
        w = letter(lower);
      }
      p[k] = w; sec[k] = COMMON; ter[k] = t; k++;
    }
    return new int[][] {java.util.Arrays.copyOf(p, k), java.util.Arrays.copyOf(sec, k), java.util.Arrays.copyOf(ter, k)};
  }
  private static int letter(int lower) { return 5 << 21 | lower; }

  /** compares the levels up to strength (and the decomposed text for IDENTICAL) */
  static int compare(String a, String b, int strength) {
    if (a.equals(b)) return 0;
    int[][] x = elements(a), y = elements(b);
    int c = level(x[0], y[0], true);
    if (c != 0 || strength == Collator.PRIMARY) return c;
    c = level(x[1], y[1], false);
    if (c != 0 || strength == Collator.SECONDARY) return c;
    c = level(x[2], y[2], false);
    if (c != 0 || strength == Collator.TERTIARY) return c;
    return Normalizer.normalize(a, Normalizer.Form.NFD).compareTo(Normalizer.normalize(b, Normalizer.Form.NFD));
  }
  private static int level(int[] x, int[] y, boolean skipZero) {
    int i = 0, j = 0;
    while (true) {
      if (skipZero) { while (i < x.length && x[i] == 0) i++; while (j < y.length && y[j] == 0) j++; }
      if (i >= x.length || j >= y.length) return Integer.compare(x.length - i, y.length - j) == 0 ? 0 : i >= x.length ? (j >= y.length ? 0 : -1) : 1;
      if (x[i] != y[j]) return x[i] < y[j] ? -1 : 1;
      i++; j++;
    }
  }
  /** the levels as one byte string whose unsigned order is the collation order */
  static byte[] key(String s, int strength) {
    int[][] e = elements(s);
    java.io.ByteArrayOutputStream b = new java.io.ByteArrayOutputStream();
    for (int w : e[0]) if (w != 0) { b.write(w >>> 16); b.write(w >>> 8); b.write(w); }
    b.write(0); b.write(0); b.write(0);
    if (strength >= Collator.SECONDARY) { for (int w : e[1]) b.write(w); b.write(0); }
    if (strength >= Collator.TERTIARY) { for (int w : e[2]) b.write(w); b.write(0); }
    if (strength >= Collator.IDENTICAL) {
      String d = Normalizer.normalize(s, Normalizer.Form.NFD);
      for (int i = 0; i < d.length(); i++) { char c = d.charAt(i); b.write((c >>> 8) + 1); b.write(c); }
    }
    return b.toByteArray();
  }
}

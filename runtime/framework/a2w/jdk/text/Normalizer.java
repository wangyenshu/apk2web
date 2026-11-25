package a2w.jdk.text;

import a2w.UcdData;

/**
 * java.text.Normalizer: Unicode normalization forms NFD, NFC, NFKD and NFKC (UAX #15) over the decompositions,
 * combining classes and composition exclusions of a2w.UcdData.
 */
public final class Normalizer {
  private Normalizer() {}
  public enum Form { NFD, NFC, NFKD, NFKC }

  private static final int S_BASE = 0xAC00, L_BASE = 0x1100, V_BASE = 0x1161, T_BASE = 0x11A7, L_COUNT = 19, V_COUNT = 21, T_COUNT = 28,
      N_COUNT = V_COUNT * T_COUNT, S_COUNT = L_COUNT * N_COUNT;

  public static String normalize(CharSequence src, Form form) {
    if (src == null || form == null) throw new NullPointerException();
    String s = src.toString();
    if (isAscii(s)) return s;
    boolean compat = form == Form.NFKD || form == Form.NFKC;
    int[] cps = decompose(s, compat);
    reorder(cps);
    if (form == Form.NFC || form == Form.NFKC) cps = compose(cps);
    StringBuilder b = new StringBuilder(cps.length);
    for (int cp : cps) if (cp >= 0) b.appendCodePoint(cp);
    return b.toString();
  }
  public static boolean isNormalized(CharSequence src, Form form) { return normalize(src, form).contentEquals(src); }

  private static boolean isAscii(String s) { for (int i = 0; i < s.length(); i++) if (s.charAt(i) >= 0x80) return false; return true; }

  /** full decomposition, code point by code point */
  private static int[] decompose(String s, boolean compat) {
    int[] out = new int[s.length() * 2 + 8];
    int n = 0;
    for (int i = 0; i < s.length(); ) {
      int cp = s.codePointAt(i);
      i += Character.charCount(cp);
      int[] d = new int[32];
      int k = decomposeOne(cp, compat, d, 0);
      if (n + k > out.length) out = java.util.Arrays.copyOf(out, Math.max(out.length * 2, n + k));
      System.arraycopy(d, 0, out, n, k);
      n += k;
    }
    return java.util.Arrays.copyOf(out, n);
  }
  private static int decomposeOne(int cp, boolean compat, int[] out, int at) {
    if (cp >= S_BASE && cp < S_BASE + S_COUNT) {
      int si = cp - S_BASE;
      out[at++] = L_BASE + si / N_COUNT;
      out[at++] = V_BASE + (si % N_COUNT) / T_COUNT;
      int t = si % T_COUNT;
      if (t != 0) out[at++] = T_BASE + t;
      return at;
    }
    String d = cp < 0xA0 ? null : UcdData.decomposition(cp, compat);
    if (d == null) { out[at] = cp; return at + 1; }
    for (int i = 0; i < d.length(); ) {
      int c = d.codePointAt(i);
      i += Character.charCount(c);
      at = decomposeOne(c, compat, out, at);
    }
    return at;
  }
  /** canonical ordering: stable sort of each run of non-starters by combining class */
  private static void reorder(int[] cps) {
    for (int i = 1; i < cps.length; i++) {
      int cc = UcdData.combiningClass(cps[i]);
      if (cc == 0) continue;
      int j = i;
      while (j > 0) {
        int pc = UcdData.combiningClass(cps[j - 1]);
        if (pc <= cc) break;
        int t = cps[j]; cps[j] = cps[j - 1]; cps[j - 1] = t;
        j--;
      }
    }
  }

  private static java.util.HashMap<Long, Integer> compositions;
  /** primary composites: pairs from canonical two-code-point decompositions that are not excluded */
  private static java.util.HashMap<Long, Integer> compositions() {
    if (compositions == null) {
      java.util.HashMap<Long, Integer> m = new java.util.HashMap<>();
      String t = UcdData.DECOMPOSITIONS;
      for (int i = 0; i < t.length(); ) {
        int cp = t.charAt(i) << 16 | t.charAt(i + 1), len = t.charAt(i + 2);
        String d = t.substring(i + 3, i + 3 + (len & 0xff));
        i += 3 + (len & 0xff);
        if ((len & 0x100) != 0 || UcdData.compositionExcluded(cp)) continue;
        int a = d.codePointAt(0), ac = Character.charCount(a);
        if (ac >= d.length()) continue;   // singleton
        int b = d.codePointAt(ac);
        if (ac + Character.charCount(b) != d.length()) continue;
        m.put((long) a << 21 | b, cp);
      }
      compositions = m;
    }
    return compositions;
  }
  private static int composePair(int a, int b) {
    if (a >= L_BASE && a < L_BASE + L_COUNT && b >= V_BASE && b < V_BASE + V_COUNT) return S_BASE + ((a - L_BASE) * V_COUNT + (b - V_BASE)) * T_COUNT;
    if (a >= S_BASE && a < S_BASE + S_COUNT && (a - S_BASE) % T_COUNT == 0 && b > T_BASE && b < T_BASE + T_COUNT) return a + (b - T_BASE);
    Integer c = compositions().get((long) a << 21 | b);
    return c == null ? -1 : c;
  }
  /** canonical composition (UAX #15): composed code points become -1 */
  private static int[] compose(int[] cps) {
    int starter = -1, lastCc = -1;   // lastCc: class of the last uncomposed code point after the starter (-1: none)
    for (int i = 0; i < cps.length; i++) {
      int cp = cps[i], cc = UcdData.combiningClass(cp);
      if (starter >= 0) {
        boolean blocked = lastCc != -1 && (lastCc == 0 || lastCc >= cc);
        if (!blocked) {
          int c = composePair(cps[starter], cp);
          if (c >= 0) { cps[starter] = c; cps[i] = -1; continue; }
        }
      }
      if (cc == 0) { starter = i; lastCc = -1; } else lastCc = cc;
    }
    return cps;
  }
}

package android.util;

public class StateSet {
  public static final int[] WILD_CARD = new int[0];
  public static final int[] NOTHING = new int[] { 0 };
  public static boolean isWildCard(int[] s) { return s.length == 0 || s[0] == 0; }
  /** Android semantics: positive entries must be present, negative entries must be absent. */
  public static boolean stateSetMatches(int[] spec, int[] state) {
    if (state == null) return spec == null || isWildCard(spec);
    for (int s : spec) {
      if (s == 0) return true;
      boolean must = s > 0;
      int a = must ? s : -s;
      boolean found = false;
      for (int t : state) { if (t == 0) break; if (t == a) { found = true; break; } }
      if (must != found) return false;
    }
    return true;
  }
  /** whether the state set holds the state (a wildcard spec matches anything) */
  public static boolean stateSetMatches(int[] stateSpec, int state) {
    for (int s : stateSpec) {
      if (s == 0) return true;
      if (s > 0) { if (state != s) return false; }
      else if (state == -s) return false;
    }
    return true;
  }
  /** the first newSize states of a state set */
  public static int[] trimStateSet(int[] states, int newSize) {
    if (states.length == newSize) return states;
    int[] trimmed = new int[newSize];
    System.arraycopy(states, 0, trimmed, 0, newSize);
    return trimmed;
  }
  public static String dump(int[] s) { return java.util.Arrays.toString(s); }
}

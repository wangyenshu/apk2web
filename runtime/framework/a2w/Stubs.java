package a2w;

import java.util.LinkedHashMap;
import java.util.Map;

/** Calls into APIs the framework doesn't implement (generated stubs call {@link #hit}): logged once each. */
public final class Stubs {
  private Stubs() {}
  static final Map<String, Integer> hits = new LinkedHashMap<>();
  public static void hit(String member) {
    Integer n = hits.get(member);
    hits.put(member, n == null ? 1 : n + 1);
    if (n == null) Host.log(5, "W/apk2web: not implemented: " + member);
  }
  static String report() {
    StringBuilder b = new StringBuilder();
    for (Map.Entry<String, Integer> e : hits.entrySet()) b.append(e.getValue()).append('\t').append(e.getKey()).append('\n');
    return b.toString();
  }
}

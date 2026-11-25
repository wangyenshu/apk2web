package a2w;

import java.util.Map;

/**
 * The charset names Java and Android accept besides the canonical ones (TeaVM's Charset.forName knows only "UTF-8",
 * "US-ASCII", "ISO-8859-1" and the UTF-16s): jp.teavm.FiberFix has TeaVM's charset table call register.
 * Windows-1252 is read as ISO-8859-1, which differs only in 0x80-0x9F.
 */
public final class CharsetAliases {
  private CharsetAliases() {}
  public static void register(Map<String, Object> m) {
    alias(m, "UTF-8", "UTF8", "UTF_8", "UNICODE-1-1-UTF-8");
    alias(m, "US-ASCII", "ASCII", "US_ASCII", "ISO646-US", "ANSI_X3.4-1968", "646", "CP367", "DEFAULT");
    alias(m, "ISO-8859-1", "ISO8859_1", "ISO_8859_1", "ISO8859-1", "LATIN1", "L1", "8859_1", "CP819", "IBM819", "ISO-LATIN-1",
        "WINDOWS-1252", "CP1252", "CP-1252");
    alias(m, "UTF-16", "UTF_16", "UTF16", "UNICODE");
    alias(m, "UTF-16BE", "UTF_16BE", "UNICODEBIGUNMARKED", "X-UTF-16BE");
    alias(m, "UTF-16LE", "UTF_16LE", "UNICODELITTLEUNMARKED", "X-UTF-16LE");
  }
  private static void alias(Map<String, Object> m, String name, String... aliases) {
    Object c = m.get(name);
    if (c == null) return;
    for (String a : aliases) m.putIfAbsent(a, c);
  }
}

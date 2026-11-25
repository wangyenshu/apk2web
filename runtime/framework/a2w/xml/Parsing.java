package a2w.xml;

/** package bridges for javax.xml.parsers */
public final class Parsing {
  private Parsing() {}
  public static Object domBuilder(boolean ns, boolean ignoreComments, boolean coalescing) { return new DomBuilder(ns, ignoreComments, coalescing); }
  public static a2w.xml.dom.Document document(Object builder) { return ((DomBuilder) builder).doc; }
  public static void inputEncoding(a2w.xml.dom.Document d, String enc) { ((Dom.DocumentImpl) d).inputEncoding = enc; }
}

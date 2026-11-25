package a2w.xml.sax.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

public class NamespaceSupport {
  public static final String XMLNS = "http://www.w3.org/XML/1998/namespace", NSDECL = "http://www.w3.org/xmlns/2000/";
  private final ArrayList<HashMap<String, String>> contexts = new ArrayList<>();
  private boolean namespaceDeclUris;
  public NamespaceSupport() { reset(); }
  public void reset() { contexts.clear(); HashMap<String, String> m = new HashMap<>(); m.put("xml", XMLNS); contexts.add(m); contexts.add(new HashMap<>()); }
  public void pushContext() { contexts.add(new HashMap<>()); }
  public void popContext() { if (contexts.size() > 1) contexts.remove(contexts.size() - 1); }
  public boolean declarePrefix(String prefix, String uri) { if (prefix.equals("xml") || prefix.equals("xmlns")) return false; contexts.get(contexts.size() - 1).put(prefix, uri); return true; }
  public String[] processName(String qName, String[] parts, boolean isAttribute) {
    int c = qName.indexOf(':');
    String prefix = c < 0 ? "" : qName.substring(0, c);
    if (c < 0 && isAttribute) { parts[0] = ""; parts[1] = qName; parts[2] = qName; return parts; }
    String uri = getURI(prefix);
    if (uri == null && c >= 0) return null;
    parts[0] = uri == null ? "" : uri; parts[1] = c < 0 ? qName : qName.substring(c + 1); parts[2] = qName;
    return parts;
  }
  public String getURI(String prefix) { for (int i = contexts.size() - 1; i >= 0; i--) { String u = contexts.get(i).get(prefix); if (u != null) return u; } return null; }
  public Enumeration<String> getPrefixes() { ArrayList<String> l = new ArrayList<>(); for (HashMap<String, String> m : contexts) for (String p : m.keySet()) if (!p.isEmpty() && !l.contains(p)) l.add(p); return Collections.enumeration(l); }
  public String getPrefix(String uri) { for (int i = contexts.size() - 1; i >= 0; i--) for (java.util.Map.Entry<String, String> e : contexts.get(i).entrySet()) if (e.getValue().equals(uri) && !e.getKey().isEmpty()) return e.getKey(); return null; }
  public Enumeration<String> getPrefixes(String uri) { ArrayList<String> l = new ArrayList<>(); for (HashMap<String, String> m : contexts) for (java.util.Map.Entry<String, String> e : m.entrySet()) if (e.getValue().equals(uri) && !e.getKey().isEmpty()) l.add(e.getKey()); return Collections.enumeration(l); }
  public Enumeration<String> getDeclaredPrefixes() { return Collections.enumeration(new ArrayList<>(contexts.get(contexts.size() - 1).keySet())); }
  public void setNamespaceDeclUris(boolean b) { namespaceDeclUris = b; }
  public boolean isNamespaceDeclUris() { return namespaceDeclUris; }
}

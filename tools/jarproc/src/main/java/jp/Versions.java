package jp;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import java.nio.file.*;
import java.util.*;
import javax.xml.stream.*;

/** api-versions.xml: the API levels each public class and member exists at. */
final class Versions {
  static final int MAX = 40;
  private Versions() {}

  static List<VClass> read(Path p) throws Exception {
    List<VClass> out = new ArrayList<>();
    XMLInputFactory f = XMLInputFactory.newInstance();
    f.setProperty(XMLInputFactory.SUPPORT_DTD, false);
    f.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
    try (var in = Files.newInputStream(p)) {
      XMLStreamReader r = f.createXMLStreamReader(in);
      VClass cur = null;
      while (r.hasNext()) {
        int ev = r.next();
        if (ev == XMLStreamConstants.START_ELEMENT) {
          String tag = r.getLocalName();
          if (tag.equals("class")) {
            cur = new VClass();
            cur.name = r.getAttributeValue(null, "name");
            cur.since = level(r.getAttributeValue(null, "since"), 1);
            cur.removed = level(r.getAttributeValue(null, "removed"), 0);
            cur.deprecated = level(r.getAttributeValue(null, "deprecated"), 0);
            if (keep(cur.name)) out.add(cur);
          } else if (cur != null && (tag.equals("extends") || tag.equals("implements"))) {
            String n = r.getAttributeValue(null, "name");
            boolean gone = r.getAttributeValue(null, "removed") != null;
            if (tag.equals("extends")) { if (!gone || cur.sup == null) cur.sup = n; }
            else if (!gone) cur.itfs.add(n);
          } else if (cur != null && (tag.equals("method") || tag.equals("field"))) {
            VMember m = new VMember();
            String n = r.getAttributeValue(null, "name");
            if (tag.equals("method")) { int i = n.indexOf('('); m.name = n.substring(0, i); m.desc = n.substring(i); }
            else m.name = n;
            m.since = level(r.getAttributeValue(null, "since"), 0);
            m.removed = level(r.getAttributeValue(null, "removed"), 0);
            m.deprecated = level(r.getAttributeValue(null, "deprecated"), 0);
            (tag.equals("method") ? cur.methods : cur.fields).add(m);
          }
        } else if (ev == XMLStreamConstants.END_ELEMENT && r.getLocalName().equals("class")) cur = null;
      }
    }
    return out;
  }
  /** android.jar alone: one level, no history */
  static List<VClass> fromJar(Collection<ClassNode> classes) {
    List<VClass> out = new ArrayList<>();
    for (ClassNode cn : classes) {
      VClass c = new VClass();
      c.name = cn.name; c.since = 1;
      for (MethodNode m : cn.methods) {
        if (!ApiReport.visible(m.access) || (m.access & (Opcodes.ACC_SYNTHETIC | Opcodes.ACC_BRIDGE)) != 0) continue;
        VMember v = new VMember(); v.name = m.name; v.desc = m.desc; c.methods.add(v);
      }
      for (FieldNode fd : cn.fields) {
        if (!ApiReport.visible(fd.access) || (fd.access & Opcodes.ACC_SYNTHETIC) != 0) continue;
        VMember v = new VMember(); v.name = fd.name; c.fields.add(v);
      }
      out.add(c);
    }
    return out;
  }
  /** android.jar's classes and members that the list lacks */
  static void merge(List<VClass> api, Collection<ClassNode> jar) {
    Map<String, VClass> by = new HashMap<>();
    for (VClass c : api) by.put(c.name, c);
    for (VClass c : fromJar(jar)) {
      if (!keep(c.name)) continue;
      VClass have = by.get(c.name);
      if (have == null) { api.add(c); continue; }
      Set<String> ms = new HashSet<>(), fs = new HashSet<>();
      for (VMember m : have.methods) ms.add(m.name + m.desc);
      for (VMember f : have.fields) fs.add(f.name);
      for (VMember m : c.methods) if (ms.add(m.name + m.desc)) have.methods.add(m);
      for (VMember f : c.fields) if (fs.add(f.name)) have.fields.add(f);
    }
  }
  static boolean keep(String n) {
    return n.startsWith("android/") || n.startsWith("java/") || n.startsWith("javax/") || n.startsWith("org/") || n.startsWith("dalvik/")
        || n.startsWith("junit/");
  }
  static int level(String v, int def) {
    if (v == null || v.isEmpty()) return def;
    try { return (int) Double.parseDouble(v.contains(":") ? v.substring(v.lastIndexOf(':') + 1) : v); } catch (NumberFormatException e) { return def; }
  }
}

/** a class of the API, with the levels it exists at */
final class VClass {
  String name, sup;
  final List<String> itfs = new ArrayList<>();
  int since = 1, removed, deprecated;
  final List<VMember> methods = new ArrayList<>(), fields = new ArrayList<>();
  /** "API 1+", "API 9-22 (removed in 23)", ", deprecated in 26" for a member (null: the class itself) */
  String levels(VMember m) {
    int s = m == null ? since : Math.max(since, m.since == 0 ? since : m.since);
    int r = m == null ? removed : m.removed != 0 ? m.removed : removed;
    int d = m == null ? deprecated : m.deprecated != 0 ? m.deprecated : 0;
    StringBuilder b = new StringBuilder("[API ").append(s);
    if (r != 0) b.append('-').append(r - 1).append(", removed in ").append(r);
    else b.append('+');
    if (d != 0) b.append(", deprecated in ").append(d);
    return b.append(']').toString();
  }
}

/** a method (name and descriptor) or field of the API, with the levels it exists at (0: as its class) */
final class VMember {
  String name, desc;
  int since, removed, deprecated;
  boolean at(VClass c, int level) {
    int s = Math.max(c.since, since == 0 ? c.since : since);
    int r = removed != 0 ? removed : c.removed;
    return level >= s && (r == 0 || level < r);
  }
  boolean constantName() {
    for (int i = 0; i < name.length(); i++) { char ch = name.charAt(i); if (Character.isLowerCase(ch)) return false; }
    return true;
  }
}

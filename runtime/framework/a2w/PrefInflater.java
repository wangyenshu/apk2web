package a2w;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;

/** Builds a Preference hierarchy from compiled preferences XML (PreferenceInflater). */
public final class PrefInflater {
  private PrefInflater() {}
  public static Preference inflate(Context c, XmlNode root, PreferenceScreen into) {
    Preference top;
    if (into != null && root.tag.equals("PreferenceScreen")) top = into;
    else top = create(c, root);
    children(c, root, top);
    return top;
  }
  private static void children(Context c, XmlNode n, Preference parent) {
    for (XmlNode ch : n.children()) {
      if (ch.tag.equals("intent")) { parent.setIntent(intent(c, ch)); continue; }
      if (ch.tag.equals("extra")) { String k = ch.a("name"), v = ch.a("value"); if (k != null) parent.getExtras().putString(k, v); continue; }
      if (ch.tag.equals("requestFocus")) continue;
      Preference p = create(c, ch);
      if (p == null) continue;
      if (parent instanceof PreferenceGroup) ((PreferenceGroup) parent).addPreference(p);
      children(c, ch, p);
    }
  }
  static Preference create(Context c, XmlNode n) {
    String cls = n.tag.indexOf('.') < 0 ? "android.preference." + n.tag : n.tag;
    Attrs attrs = new Attrs(n);
    Object o = Reflection.newInstance(cls, new Class<?>[] {Context.class, android.util.AttributeSet.class}, c, attrs);
    if (o == null && n.tag.indexOf('.') < 0) o = Reflection.newInstance(n.tag, new Class<?>[] {Context.class, android.util.AttributeSet.class}, c, attrs);
    if (!(o instanceof Preference)) {
      android.util.Log.w("PreferenceInflater", "Error inflating class " + n.tag);
      return new Preference(c, attrs);
    }
    return (Preference) o;
  }
  public static Intent intent(Context c, XmlNode n) {
    Intent i = new Intent();
    String v;
    if ((v = n.a("action")) != null) i.setAction(v);
    if ((v = n.a("data")) != null) i.setData(android.net.Uri.parse(v));
    if ((v = n.a("mimeType")) != null) i.setType(v);
    String pkg = n.a("targetPackage"), cls = n.a("targetClass");
    if (cls != null) i.setClassName(pkg != null ? pkg : c.getPackageName(), cls);
    for (XmlNode e : n.children()) {
      if (e.tag.equals("extra") && e.a("name") != null) i.putExtra(e.a("name"), e.a("value"));
      if (e.tag.equals("category") && e.a("name") != null) i.addCategory(e.a("name"));
    }
    return i;
  }
}

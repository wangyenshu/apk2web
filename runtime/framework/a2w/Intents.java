package a2w;

import android.content.ComponentName;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

/** Resolves intents to the app's manifest components, as PackageManagerService does for one package. */
public final class Intents {
  private Intents() {}
  public static final String ACTIVITIES = "activities", SERVICES = "services", RECEIVERS = "receivers", PROVIDERS = "providers";

  /** class names of the components of a kind (ACTIVITIES...) that the intent addresses */
  public static List<String> resolve(String kind, Intent intent) {
    ArrayList<String> out = new ArrayList<>();
    ComponentName c = intent.getComponent();
    if (c != null) {
      if (c.getPackageName() != null && !c.getPackageName().equals(Res.pkg())) return out;
      String cls = Res.componentClass(kind, c.getClassName());
      if (cls != null) out.add(cls);
      else if (Reflection.known(c.getClassName())) out.add(c.getClassName());   // not in the manifest, but present
      return out;
    }
    if (intent.getPackage() != null && !intent.getPackage().equals(Res.pkg())) return out;
    StringBuilder cats = new StringBuilder();
    if (intent.getCategories() != null) for (String s : intent.getCategories()) { if (cats.length() > 0) cats.append('\u0001'); cats.append(s); }
    android.net.Uri d = intent.getData();
    String r = Res.resolveIntent(kind, intent.getAction(), cats.toString(), d == null ? null : d.getScheme(), d == null ? null : d.getHost(), intent.getType());
    if (r != null && !r.isEmpty()) for (String s : Res.split(r)) out.add(s);
    return out;
  }
  public static String resolveOne(String kind, Intent intent) {
    List<String> l = resolve(kind, intent);
    return l.isEmpty() ? null : l.get(0);
  }
  /** a copy addressed to the resolved component (what the started component sees as its intent) */
  public static Intent explicit(Intent intent, String cls) {
    Intent i = new Intent(intent);
    i.setComponent(new ComponentName(Res.pkg(), cls));
    return i;
  }
}

package android.animation;

import android.content.Context;
import a2w.Values;
import a2w.XmlNode;
import java.util.ArrayList;

/** Builds animators from animator XML (<set>, <objectAnimator>, <animator>, <propertyValuesHolder>). */
public class AnimatorInflater {
  public AnimatorInflater() {}
  public static Animator loadAnimator(Context context, int id) {
    XmlNode n = XmlNode.of(a2w.Res.anim(id));
    if (n == null) throw new android.content.res.Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
    return create(context, n);
  }
  public static StateListAnimator loadStateListAnimator(Context context, int id) { return new StateListAnimator(); }
  static Animator create(Context c, XmlNode n) {
    switch (n.tag) {
      case "set": {
        AnimatorSet set = new AnimatorSet();
        ArrayList<Animator> kids = new ArrayList<>();
        for (XmlNode k : n.children()) { Animator a = create(c, k); if (a != null) kids.add(a); }
        if ("sequentially".equals(n.a("ordering"))) set.playSequentially(kids); else set.playTogether(kids);
        return set;
      }
      case "objectAnimator": case "animator": {
        ValueAnimator a = n.tag.equals("objectAnimator") ? new ObjectAnimator() : new ValueAnimator();
        String v;
        if ((v = n.a("duration")) != null) a.setDuration(Values.parseInt(res(c, v), 300));
        if ((v = n.a("startOffset")) != null) a.setStartDelay(Values.parseInt(res(c, v), 0));
        if ((v = n.a("repeatCount")) != null) a.setRepeatCount(v.equals("infinite") ? ValueAnimator.INFINITE : Values.parseInt(v, 0));
        if ((v = n.a("repeatMode")) != null) a.setRepeatMode(v.equals("reverse") || v.equals("2") ? ValueAnimator.REVERSE : ValueAnimator.RESTART);
        if ((v = n.a("interpolator")) != null && Values.isRef(v)) a.setInterpolator(android.view.animation.AnimationUtils.loadInterpolator(c, Values.refId(v)));
        if (a instanceof ObjectAnimator && (v = n.a("propertyName")) != null) ((ObjectAnimator) a).setPropertyName(v);
        ArrayList<PropertyValuesHolder> holders = new ArrayList<>();
        for (XmlNode k : n.children()) if (k.tag.equals("propertyValuesHolder")) { PropertyValuesHolder h = holder(c, k, k.a("propertyName")); if (h != null) holders.add(h); }
        if (!holders.isEmpty()) a.setValues(holders.toArray(new PropertyValuesHolder[0]));
        else {
          PropertyValuesHolder h = holder(c, n, a instanceof ObjectAnimator ? ((ObjectAnimator) a).getPropertyName() : "");
          if (h != null) a.setValues(h);
        }
        return a;
      }
      default: return null;
    }
  }
  private static String res(Context c, String v) { return Values.isRef(v) ? String.valueOf(c.getResources().getInteger(Values.refId(v))) : v; }
  private static PropertyValuesHolder holder(Context c, XmlNode n, String name) {
    String from = n.a("valueFrom"), to = n.a("valueTo"), type = n.a("valueType");
    if (from == null && to == null) return null;
    boolean color = "colorType".equals(type) || (from != null && from.startsWith("#")) || (to != null && to.startsWith("#"));
    boolean isInt = "intType".equals(type) || color;
    if (isInt) {
      int a = color ? color(c, from) : (int) num(c, from), b = color ? color(c, to) : (int) num(c, to);
      PropertyValuesHolder h = from == null ? PropertyValuesHolder.ofInt(name, b) : PropertyValuesHolder.ofInt(name, a, b);
      if (color) h.setEvaluator(ArgbEvaluator.getInstance());
      return h;
    }
    float a = num(c, from), b = num(c, to);
    return from == null ? PropertyValuesHolder.ofFloat(name, b) : PropertyValuesHolder.ofFloat(name, a, b);
  }
  private static float num(Context c, String v) {
    if (v == null) return 0;
    if (Values.isRef(v)) return c.getResources().getDimension(Values.refId(v));
    if (v.endsWith("dp") || v.endsWith("dip") || v.endsWith("sp") || v.endsWith("px")) return Values.dimension(v);
    return Values.parseFloat(v, 0);
  }
  private static int color(Context c, String v) { return v == null ? 0 : Values.isRef(v) ? c.getResources().getColor(Values.refId(v)) : Values.parseColor(v); }
}

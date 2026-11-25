package android.view.animation;

import android.content.Context;
import a2w.Values;
import a2w.XmlNode;

public class AnimationUtils {
  public static long currentAnimationTimeMillis() { return android.os.SystemClock.uptimeMillis(); }

  public static Animation loadAnimation(Context c, int id) {
    if (Values.isFramework(id)) {
      String n = Values.frameworkName(id);
      Animation a;
      if ("anim/fade_out".equals(n)) a = new AlphaAnimation(1, 0);
      else if ("anim/slide_in_left".equals(n)) a = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
      else if ("anim/slide_out_right".equals(n)) a = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
      else a = new AlphaAnimation(0, 1);
      a.setDuration(300);
      a.setInterpolator(new DecelerateInterpolator());
      return a;
    }
    XmlNode n = c.getResources().animNode(id);
    if (n == null) throw new android.content.res.Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(id));
    return create(c, n, null);
  }

  static Animation create(Context c, XmlNode n, AnimationSet parent) {
    Animation a;
    switch (n.tag) {
      case "set": {
        AnimationSet s = new AnimationSet(Values.parseBool(n.a("shareInterpolator"), true));
        a = s;
        common(c, a, n);
        for (XmlNode ch : n.children()) create(c, ch, s);
        break;
      }
      case "alpha": a = new AlphaAnimation(Values.parseFloat(n.a("fromAlpha"), 1), Values.parseFloat(n.a("toAlpha"), 1)); common(c, a, n); break;
      case "scale": {
        Animation.Description px = Animation.Description.parse(n.a("pivotX")), py = Animation.Description.parse(n.a("pivotY"));
        a = new ScaleAnimation(Values.parseFloat(n.a("fromXScale"), 1), Values.parseFloat(n.a("toXScale"), 1), Values.parseFloat(n.a("fromYScale"), 1), Values.parseFloat(n.a("toYScale"), 1), px.type, px.value, py.type, py.value);
        common(c, a, n);
        break;
      }
      case "rotate": {
        Animation.Description px = Animation.Description.parse(n.a("pivotX")), py = Animation.Description.parse(n.a("pivotY"));
        a = new RotateAnimation(Values.parseFloat(n.a("fromDegrees"), 0), Values.parseFloat(n.a("toDegrees"), 0), px.type, px.value, py.type, py.value);
        common(c, a, n);
        break;
      }
      case "translate": {
        Animation.Description fx = Animation.Description.parse(n.a("fromXDelta")), tx = Animation.Description.parse(n.a("toXDelta")),
            fy = Animation.Description.parse(n.a("fromYDelta")), ty = Animation.Description.parse(n.a("toYDelta"));
        a = new TranslateAnimation(fx.type, fx.value, tx.type, tx.value, fy.type, fy.value, ty.type, ty.value);
        common(c, a, n);
        break;
      }
      default: throw new RuntimeException("Unknown animation name: " + n.tag);
    }
    if (parent != null) parent.addAnimation(a);
    return a;
  }
  static void common(Context c, Animation a, XmlNode n) {
    if (n.a("duration") != null) a.setDuration(Values.parseInt(n.a("duration"), 0));
    if (n.a("startOffset") != null) a.setStartOffset(Values.parseInt(n.a("startOffset"), 0));
    if (n.a("fillEnabled") != null) a.setFillEnabled(Values.parseBool(n.a("fillEnabled"), false));
    if (n.a("fillBefore") != null) a.setFillBefore(Values.parseBool(n.a("fillBefore"), true));
    if (n.a("fillAfter") != null) a.setFillAfter(Values.parseBool(n.a("fillAfter"), false));
    String rc = n.a("repeatCount");
    if (rc != null) a.setRepeatCount(rc.equals("infinite") ? Animation.INFINITE : Values.parseInt(rc, 0));
    String rm = n.a("repeatMode");
    if (rm != null) a.setRepeatMode(rm.equals("reverse") || rm.equals("2") ? Animation.REVERSE : Animation.RESTART);
    String ip = n.a("interpolator");
    if (ip != null) a.setInterpolator(loadInterpolator(c, Values.refId(ip)));
  }

  /** <layoutAnimation> or <gridLayoutAnimation> */
  public static LayoutAnimationController loadLayoutAnimation(Context c, int id) {
    XmlNode n = c.getResources().animNode(id);
    if (n == null) throw new android.content.res.Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(id));
    a2w.Attrs attrs = new a2w.Attrs(n);
    switch (n.tag) {
      case "layoutAnimation": return new LayoutAnimationController(c, attrs);
      case "gridLayoutAnimation": return new GridLayoutAnimationController(c, attrs);
      default: throw new RuntimeException("Unknown layout animation name: " + n.tag);
    }
  }
  public static Interpolator loadInterpolator(Context c, int id) {
    // the app's interpolator XML (<pathInterpolator controlX1=...>, <overshootInterpolator tension=...>)
    a2w.XmlNode x = id == 0 || Values.isFramework(id) && a2w.Res.anim(id) == null ? null : a2w.XmlNode.of(a2w.Res.anim(id));
    if (x != null) {
      Interpolator ip = fromXml(c, x);
      if (ip != null) return ip;
    }
    String n = Values.isFramework(id) ? Values.frameworkName(id) : a2w.Res.name(id);
    if (n == null) return new AccelerateDecelerateInterpolator();
    if (n.endsWith("fast_out_slow_in")) return new PathInterpolator(0.4f, 0f, 0.2f, 1f);
    if (n.endsWith("fast_out_linear_in")) return new PathInterpolator(0.4f, 0f, 1f, 1f);
    if (n.endsWith("linear_out_slow_in")) return new PathInterpolator(0f, 0f, 0.2f, 1f);
    if (n.endsWith("fast_out_extra_slow_in")) return new PathInterpolator(0.2f, 0f, 0f, 1f);
    if (n.endsWith("linear_interpolator") || n.endsWith("linear")) return new LinearInterpolator();
    if (n.endsWith("accelerate_decelerate_interpolator")) return new AccelerateDecelerateInterpolator();
    if (n.endsWith("accelerate_interpolator")) return new AccelerateInterpolator();
    if (n.endsWith("decelerate_interpolator")) return new DecelerateInterpolator();
    if (n.endsWith("anticipate_overshoot_interpolator")) return new AnticipateOvershootInterpolator();
    if (n.endsWith("anticipate_interpolator")) return new AnticipateInterpolator();
    if (n.endsWith("overshoot_interpolator")) return new OvershootInterpolator();
    if (n.endsWith("bounce_interpolator")) return new BounceInterpolator();
    if (n.endsWith("cycle_interpolator")) return new CycleInterpolator(1);
    return new AccelerateDecelerateInterpolator();
  }
  static Interpolator fromXml(Context c, a2w.XmlNode x) {
    float f;
    switch (x.tag) {
      case "linearInterpolator": return new LinearInterpolator();
      case "accelerateInterpolator": return new AccelerateInterpolator(Values.parseFloat(x.a("factor"), 1));
      case "decelerateInterpolator": return new DecelerateInterpolator(Values.parseFloat(x.a("factor"), 1));
      case "accelerateDecelerateInterpolator": return new AccelerateDecelerateInterpolator();
      case "anticipateInterpolator": return new AnticipateInterpolator(Values.parseFloat(x.a("tension"), 2));
      case "overshootInterpolator": return new OvershootInterpolator(Values.parseFloat(x.a("tension"), 2));
      case "anticipateOvershootInterpolator": return new AnticipateOvershootInterpolator(Values.parseFloat(x.a("tension"), 2), Values.parseFloat(x.a("extraTension"), 1.5f));
      case "bounceInterpolator": return new BounceInterpolator();
      case "cycleInterpolator": return new CycleInterpolator(Values.parseFloat(x.a("cycles"), 1));
      case "pathInterpolator": return new PathInterpolator(c, new a2w.Attrs(x));
      default: return null;
    }
  }
  public static Animation makeInAnimation(Context c, boolean fromLeft) {
    Animation a = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, fromLeft ? -1 : 1, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
    a.setDuration(300); a.setInterpolator(new DecelerateInterpolator()); a.setStartTime(currentAnimationTimeMillis());
    return a;
  }
  public static Animation makeOutAnimation(Context c, boolean toRight) {
    Animation a = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_PARENT, toRight ? 1 : -1, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
    a.setDuration(300); a.setInterpolator(new AccelerateInterpolator()); a.setStartTime(currentAnimationTimeMillis());
    return a;
  }
  public static Animation makeInChildBottomAnimation(Context c) {
    Animation a = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.ABSOLUTE, 0);
    a.setDuration(300); a.setInterpolator(new AccelerateInterpolator()); a.setStartTime(currentAnimationTimeMillis());
    return a;
  }
}

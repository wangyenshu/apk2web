package a2w;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.*;
import android.graphics.drawable.*;

/**
 * The framework's compound-button indicators and switch parts, drawn in code: check boxes, radio buttons, stars and
 * the toggle bar in the look of the framework's other widgets; switches as Material (apps targeting API 21+) or Holo.
 */
public final class WidgetDrawables {
  private WidgetDrawables() {}
  static float dp(float v) { return v * Values.METRICS.density; }
  static boolean has(int[] s, int a) { for (int x : s) if (x == a) return true; return false; }
  static boolean enabled(int[] s) { return s.length == 0 || has(s, android.R.attr.state_enabled); }

  /** base: stateful, honors alpha and color filters (tints) */
  abstract static class Base extends Drawable {
    final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    int alpha = 255;
    ColorFilter cf;
    @Override public boolean isStateful() { return true; }
    @Override protected boolean onStateChange(int[] s) { super.onStateChange(s); invalidateSelf(); return true; }
    @Override public void setAlpha(int a) { alpha = a; invalidateSelf(); }
    @Override public int getAlpha() { return alpha; }
    @Override public void setColorFilter(ColorFilter f) { cf = f; invalidateSelf(); }
    @Override public ColorFilter getColorFilter() { return cf; }
    @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
    void paint(int color) { p.setShader(null); p.setColor(color); p.setAlpha(Color.alpha(color) * alpha / 255); p.setColorFilter(cf); }
  }

  /** a check box (CHECK), radio button (RADIO) or star (STAR) indicator, 32dp */
  public static final class Indicator extends Base {
    public static final int CHECK = 0, RADIO = 1, STAR = 2;
    final int kind, size;
    public Indicator(int kind) { this.kind = kind; size = Math.round(dp(32)); }
    @Override public int getIntrinsicWidth() { return size; }
    @Override public int getIntrinsicHeight() { return size; }
    @Override public boolean hasFocusStateSpecified() { return true; }
    @Override public void draw(Canvas c) {
      int[] s = getState();
      boolean checked = has(s, android.R.attr.state_checked), pressed = has(s, android.R.attr.state_pressed), on = enabled(s);
      boolean focused = has(s, android.R.attr.state_focused);
      Rect b = getBounds();
      float cx = b.exactCenterX(), cy = b.exactCenterY(), box = Math.min(b.width(), b.height()) * 22f / 32f, h = box / 2;
      int top = !on ? 0xffe2e2e2 : pressed ? 0xffffc94c : 0xfff6f6f6, bottom = !on ? 0xffc8c8c8 : pressed ? 0xffff8a00 : 0xffc9c9c9;
      int border = !on ? 0xffa0a0a0 : pressed ? 0xff8d5a00 : focused ? 0xffff9900 : 0xff7a7a7a;
      int mark = on ? 0xff3d9b00 : 0xff9a9a9a;
      Path shape = new Path();
      if (kind == RADIO) shape.addCircle(cx, cy, h, Path.Direction.CW);
      else if (kind == STAR) star(shape, cx, cy, h * 1.2f);
      else shape.addRoundRect(new RectF(cx - h, cy - h, cx + h, cy + h), dp(3), dp(3), Path.Direction.CW);
      p.setStyle(Paint.Style.FILL);
      if (kind == STAR && checked) paint(on ? 0xffffc000 : 0xffd8c060);
      else {
        paint(0xff000000);
        p.setShader(new LinearGradient(0, cy - h, 0, cy + h, top, bottom, Shader.TileMode.CLAMP));
      }
      c.drawPath(shape, p);
      paint(kind == STAR && checked ? 0xffb07800 : border);
      p.setStyle(Paint.Style.STROKE);
      p.setStrokeWidth(focused && !pressed ? dp(2) : dp(1));
      c.drawPath(shape, p);
      if (!checked) return;
      if (kind == CHECK) {
        paint(mark);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(dp(3.5f));
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeJoin(Paint.Join.ROUND);
        Path m = new Path();
        m.moveTo(cx - h * 0.58f, cy + h * 0.02f);
        m.lineTo(cx - h * 0.16f, cy + h * 0.44f);
        m.lineTo(cx + h * 0.62f, cy - h * 0.42f);
        c.drawPath(m, p);
      } else if (kind == RADIO) {
        paint(mark);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(cx, cy, h * 0.48f, p);
      }
    }
    static void star(Path path, float cx, float cy, float r) {
      for (int i = 0; i < 10; i++) {
        double a = -Math.PI / 2 + i * Math.PI / 5;
        float rr = i % 2 == 0 ? r : r * 0.45f;
        float x = cx + (float) Math.cos(a) * rr, y = cy + (float) Math.sin(a) * rr;
        if (i == 0) path.moveTo(x, y); else path.lineTo(x, y);
      }
      path.close();
    }
  }

  /** the light bar at the bottom of a ToggleButton: green when checked */
  public static final class ToggleBar extends Base {
    @Override public void draw(Canvas c) {
      int[] s = getState();
      boolean checked = has(s, android.R.attr.state_checked), on = enabled(s);
      Rect b = getBounds();
      paint(checked ? (on ? 0xff5cc000 : 0xff9ccc80) : (on ? 0xff8a8a8a : 0xffbcbcbc));
      p.setStyle(Paint.Style.FILL);
      float l = b.left + dp(12), r = b.right - dp(12), bot = b.bottom - dp(7);
      c.drawRoundRect(new RectF(l, bot - dp(4), r, bot), dp(1), dp(1), p);
    }
  }
  /** the framework button with its toggle bar (layer ids android:id/background and android:id/toggle) */
  public static Drawable toggleBackground(android.content.res.Resources r) {
    LayerDrawable l = new LayerDrawable(new Drawable[] {Drawables.framework(r, "drawable/btn_default"), new ToggleBar()});
    l.setId(0, android.R.id.background);
    l.setId(1, android.R.id.toggle);
    return l;
  }

  /** a switch thumb or track; colors from the theme's colorControlActivated, light or dark */
  public static final class SwitchPart extends Base {
    public static final int THUMB = 0, TRACK = 1;
    final int kind, accent;
    final boolean material, light;
    public SwitchPart(int kind, boolean material, int accent, boolean light) { this.kind = kind; this.material = material; this.accent = accent; this.light = light; }
    @Override public int getIntrinsicWidth() { return kind == TRACK ? -1 : Math.round(dp(material ? 20 : 24)); }
    @Override public int getIntrinsicHeight() { return Math.round(dp(material ? (kind == TRACK ? 14 : 20) : 28)); }
    @Override public boolean hasFocusStateSpecified() { return true; }
    @Override public void draw(Canvas c) {
      int[] s = getState();
      boolean checked = has(s, android.R.attr.state_checked), on = enabled(s), pressed = has(s, android.R.attr.state_pressed);
      boolean focused = has(s, android.R.attr.state_focused);
      Rect b = getBounds();
      p.setStyle(Paint.Style.FILL);
      if (material) {
        if (kind == TRACK) {
          int col = checked ? (accent & 0x00ffffff) | 0x4d000000 : light ? 0x61000000 : 0x4dffffff;
          if (!on) col = (col & 0x00ffffff) | ((Color.alpha(col) / 2) << 24);
          paint(col);
          float th = dp(14), cy = b.exactCenterY(), l = b.left + dp(3), r = b.right - dp(3);
          c.drawRoundRect(new RectF(l, cy - th / 2, r, cy + th / 2), th / 2, th / 2, p);
        } else {
          float cx = b.exactCenterX(), cy = b.exactCenterY(), rad = Math.min(b.width(), b.height()) / 2f - dp(1);
          if (pressed || focused) { paint(((checked ? accent : light ? 0xff000000 : 0xffffffff) & 0x00ffffff) | 0x26000000); c.drawCircle(cx, cy, rad * 1.9f, p); }
          paint(0x3d000000);
          c.drawCircle(cx, cy + dp(0.8f), rad + dp(0.5f), p);
          int col = !on ? (light ? 0xffbdbdbd : 0xff424242) : checked ? accent : light ? 0xfff1f1f1 : 0xffbdbdbd;
          paint(col);
          c.drawCircle(cx, cy, rad, p);
        }
      } else {
        float rr = dp(2);
        if (kind == TRACK) {
          paint(light ? 0xffd4d4d4 : 0xff3a3a3a);
          c.drawRoundRect(new RectF(b.left, b.top + dp(2), b.right, b.bottom - dp(2)), rr, rr, p);
        } else {
          int col = checked ? accent : light ? 0xffa8a8a8 : 0xff6a6a6a;
          if (pressed) col = blend(col, 0xffffffff, 0.25f);
          if (!on) col = (col & 0x00ffffff) | 0x80000000;
          paint(col);
          c.drawRoundRect(new RectF(b.left, b.top + dp(2), b.right, b.bottom - dp(2)), rr, rr, p);
          if (focused) { paint(0xffff9900); p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(dp(2)); c.drawRoundRect(new RectF(b.left + dp(1), b.top + dp(3), b.right - dp(1), b.bottom - dp(3)), rr, rr, p); }
        }
      }
    }
    static int blend(int a, int b, float t) {
      int r = (int) (Color.red(a) + (Color.red(b) - Color.red(a)) * t), g = (int) (Color.green(a) + (Color.green(b) - Color.green(a)) * t);
      int bl = (int) (Color.blue(a) + (Color.blue(b) - Color.blue(a)) * t);
      return Color.argb(Color.alpha(a), r, g, bl);
    }
  }
  /** the theme's colorControlActivated, and whether it is a light theme */
  public static int accent(Context c) {
    try {
      android.content.res.TypedArray ta = c.obtainStyledAttributes(new int[] {android.R.attr.colorControlActivated});
      int v = ta.getColor(0, 0xff33b5e5);
      ta.recycle();
      return v;
    } catch (RuntimeException e) { return 0xff33b5e5; }
  }
  public static boolean lightTheme(Context c) {
    try {
      android.content.res.TypedArray ta = c.obtainStyledAttributes(new int[] {android.R.attr.textColorPrimary});
      ColorStateList l = ta.getColorStateList(0);
      ta.recycle();
      if (l == null) return false;
      int col = l.getDefaultColor();
      return Color.red(col) + Color.green(col) + Color.blue(col) < 384;
    } catch (RuntimeException e) { return false; }
  }
}

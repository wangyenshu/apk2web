package android.graphics.drawable;

import android.graphics.*;

public class ColorDrawable extends Drawable {
  int base, color;
  private final Paint paint = new Paint();
  public ColorDrawable() { this(0); }
  public ColorDrawable(int c) { base = color = c; }
  public int getColor() { return color; }
  public void setColor(int c) { if (base != c || color != c) { base = color = c; invalidateSelf(); } }
  @Override public void draw(Canvas c) {
    if ((color >>> 24) != 0) { paint.setColor(color); c.drawRect(getBounds(), paint); }
  }
  @Override public int getAlpha() { return color >>> 24; }
  @Override public void setAlpha(int a) {
    int ba = base >>> 24;
    int na = ba * (a + (a >> 7)) >> 8;
    int old = color;
    color = (base & 0xffffff) | (na << 24);
    if (old != color) invalidateSelf();
  }
  @Override public void setColorFilter(ColorFilter cf) {}
  @Override public int getOpacity() { int a = color >>> 24; return a == 255 ? PixelFormat.OPAQUE : a == 0 ? PixelFormat.TRANSPARENT : PixelFormat.TRANSLUCENT; }
  @Override public ConstantState getConstantState() {
    final int c = base;
    return new ConstantState() { public Drawable newDrawable() { return new ColorDrawable(c); } public int getChangingConfigurations() { return 0; } };
  }
}

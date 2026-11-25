package android.text;
public class TextPaint extends android.graphics.Paint {
  public int bgColor, baselineShift, linkColor; public int[] drawableState; public float density = 1;
  public TextPaint() {}
  public TextPaint(int flags) { super(flags); }
  public TextPaint(android.graphics.Paint p) {
    super(p);
    if (p instanceof TextPaint) { TextPaint t = (TextPaint) p; bgColor = t.bgColor; baselineShift = t.baselineShift; linkColor = t.linkColor; drawableState = t.drawableState; density = t.density; }
  }
  public void set(TextPaint p) { super.set(p); bgColor = p.bgColor; baselineShift = p.baselineShift; linkColor = p.linkColor; drawableState = p.drawableState; density = p.density; }
  public float getUnderlineThickness() { return getTextSize() / 18f; }
  public float getStrikeThruThickness() { return getTextSize() / 18f; }
  public int underlineColor;
  public float underlineThickness;
}

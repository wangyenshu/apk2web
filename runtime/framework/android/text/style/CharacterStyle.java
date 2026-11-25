package android.text.style;

import android.text.TextPaint;

/** A span that changes how characters are drawn (colour, style, size ...). */
public abstract class CharacterStyle {
  public abstract void updateDrawState(TextPaint tp);
  public CharacterStyle getUnderlying() { return this; }
  public static CharacterStyle wrap(CharacterStyle cs) {
    if (cs instanceof MetricAffectingSpan) return new MetricAffectingSpan.Passthrough((MetricAffectingSpan) cs);
    return new Passthrough(cs);
  }
  static class Passthrough extends CharacterStyle {
    private final CharacterStyle style;
    Passthrough(CharacterStyle cs) { style = cs; }
    @Override public void updateDrawState(TextPaint tp) { style.updateDrawState(tp); }
    @Override public CharacterStyle getUnderlying() { return style.getUnderlying(); }
  }
}

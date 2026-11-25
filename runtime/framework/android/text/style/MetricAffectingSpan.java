package android.text.style;

import android.text.TextPaint;

/** A span that changes the size or shape of characters, and so the layout. */
public abstract class MetricAffectingSpan extends CharacterStyle implements UpdateLayout {
  public abstract void updateMeasureState(TextPaint p);
  @Override public MetricAffectingSpan getUnderlying() { return this; }
  static class Passthrough extends MetricAffectingSpan {
    private final MetricAffectingSpan style;
    Passthrough(MetricAffectingSpan cs) { style = cs; }
    @Override public void updateDrawState(TextPaint tp) { style.updateDrawState(tp); }
    @Override public void updateMeasureState(TextPaint tp) { style.updateMeasureState(tp); }
    @Override public MetricAffectingSpan getUnderlying() { return style.getUnderlying(); }
  }
}

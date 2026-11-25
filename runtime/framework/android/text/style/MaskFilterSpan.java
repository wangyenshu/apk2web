package android.text.style;

import android.graphics.MaskFilter;
import android.text.TextPaint;

public class MaskFilterSpan extends CharacterStyle implements UpdateAppearance {
  private final MaskFilter filter;
  public MaskFilterSpan(MaskFilter filter) { this.filter = filter; }
  public MaskFilter getMaskFilter() { return filter; }
  @Override public void updateDrawState(TextPaint ds) { ds.setMaskFilter(filter); }
}

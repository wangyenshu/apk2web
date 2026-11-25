package android.text.style;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

/** Bold and/or italic. */
public class StyleSpan extends MetricAffectingSpan implements android.text.ParcelableSpan {
  private final int style;
  public StyleSpan(int style) { this.style = style; }
  public StyleSpan(int style, int fontWeightAdjustment) { this(style); }
  public StyleSpan(android.os.Parcel src) { style = src.readInt(); }
  public int getStyle() { return style; }
  public int getFontWeightAdjustment() { return 0; }
  @Override public void updateDrawState(TextPaint ds) { apply(ds, style); }
  @Override public void updateMeasureState(TextPaint p) { apply(p, style); }
  static void apply(Paint paint, int style) {
    Typeface old = paint.getTypeface();
    int oldStyle = old == null ? 0 : old.getStyle();
    int want = oldStyle | style;
    Typeface tf = old == null ? Typeface.defaultFromStyle(want) : Typeface.create(old, want);
    int fake = want & ~tf.getStyle();
    if ((fake & Typeface.BOLD) != 0) paint.setFakeBoldText(true);
    if ((fake & Typeface.ITALIC) != 0) paint.setTextSkewX(-0.25f);
    paint.setTypeface(tf);
  }
  @Override public int getSpanTypeId() { return 7; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeInt(style); }
}

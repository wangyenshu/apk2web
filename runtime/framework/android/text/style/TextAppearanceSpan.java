package android.text.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;

/** Colour, size, style and family taken from a TextAppearance style. */
public class TextAppearanceSpan extends MetricAffectingSpan implements android.text.ParcelableSpan {
  private final String family;
  private final int style, size;
  private final ColorStateList color, linkColor;
  public TextAppearanceSpan(Context context, int appearance) { this(context, appearance, -1); }
  public TextAppearanceSpan(Context context, int appearance, int colorList) {
    TypedArray a = context.obtainStyledAttributes(appearance, new int[] {android.R.attr.textSize, android.R.attr.typeface, android.R.attr.textStyle, android.R.attr.textColor, android.R.attr.textColorLink, android.R.attr.fontFamily});
    ColorStateList c = a.getColorStateList(3);
    linkColor = a.getColorStateList(4);
    size = a.getDimensionPixelSize(0, -1);
    style = a.getInt(2, 0);
    String fam = a.getString(5);
    if (fam == null) {
      int tf = a.getInt(1, 0);
      fam = tf == 1 ? "sans" : tf == 2 ? "serif" : tf == 3 ? "monospace" : null;
    }
    family = fam;
    a.recycle();
    if (colorList >= 0) {
      TypedArray b = context.obtainStyledAttributes(android.R.style.Theme, new int[] {android.R.attr.textColorPrimary});
      ColorStateList cl = b.getColorStateList(0);
      b.recycle();
      if (cl != null) c = cl;
    }
    color = c;
  }
  public TextAppearanceSpan(String family, int style, int size, ColorStateList color, ColorStateList linkColor) {
    this.family = family; this.style = style; this.size = size; this.color = color; this.linkColor = linkColor;
  }
  public TextAppearanceSpan(android.os.Parcel src) { family = src.readString(); style = src.readInt(); size = src.readInt(); color = null; linkColor = null; }
  public String getFamily() { return family; }
  public ColorStateList getTextColor() { return color; }
  public ColorStateList getLinkTextColor() { return linkColor; }
  public int getTextSize() { return size; }
  public int getTextStyle() { return style; }
  @Override public void updateDrawState(TextPaint ds) {
    updateMeasureState(ds);
    if (color != null) ds.setColor(color.getColorForState(ds.drawableState, 0));
    if (linkColor != null) ds.linkColor = linkColor.getColorForState(ds.drawableState, 0);
  }
  @Override public void updateMeasureState(TextPaint ds) {
    if (family != null || style != 0) {
      Typeface tf = ds.getTypeface();
      int st = (tf == null ? 0 : tf.getStyle()) | style;
      Typeface n = family != null ? Typeface.create(family, st) : tf == null ? Typeface.defaultFromStyle(st) : Typeface.create(tf, st);
      int fake = st & ~n.getStyle();
      if ((fake & Typeface.BOLD) != 0) ds.setFakeBoldText(true);
      if ((fake & Typeface.ITALIC) != 0) ds.setTextSkewX(-0.25f);
      ds.setTypeface(n);
    }
    if (size > 0) ds.setTextSize(size);
  }
  @Override public int getSpanTypeId() { return 17; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeString(family); dest.writeInt(style); dest.writeInt(size); }
}

package android.text.style;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

public class TypefaceSpan extends MetricAffectingSpan implements android.text.ParcelableSpan {
  private final String family;
  private final Typeface typeface;
  public TypefaceSpan(String family) { this.family = family; typeface = null; }
  public TypefaceSpan(Typeface typeface) { family = null; this.typeface = typeface; }
  public TypefaceSpan(android.os.Parcel src) { family = src.readString(); typeface = null; }
  public String getFamily() { return family; }
  public Typeface getTypeface() { return typeface; }
  private void apply(Paint p) {
    Typeface old = p.getTypeface();
    int style = old == null ? 0 : old.getStyle();
    p.setTypeface(typeface != null ? Typeface.create(typeface, style) : family != null ? Typeface.create(family, style) : old);
  }
  @Override public void updateDrawState(TextPaint ds) { apply(ds); }
  @Override public void updateMeasureState(TextPaint ds) { apply(ds); }
  @Override public int getSpanTypeId() { return 13; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeString(family); }
}

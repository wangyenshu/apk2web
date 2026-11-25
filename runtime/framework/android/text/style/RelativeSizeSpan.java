package android.text.style;

import android.text.TextPaint;

public class RelativeSizeSpan extends MetricAffectingSpan implements android.text.ParcelableSpan {
  private final float proportion;
  public RelativeSizeSpan(float proportion) { this.proportion = proportion; }
  public RelativeSizeSpan(android.os.Parcel src) { proportion = src.readFloat(); }
  public float getSizeChange() { return proportion; }
  @Override public void updateDrawState(TextPaint ds) { ds.setTextSize(ds.getTextSize() * proportion); }
  @Override public void updateMeasureState(TextPaint ds) { ds.setTextSize(ds.getTextSize() * proportion); }
  @Override public int getSpanTypeId() { return 3; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeFloat(proportion); }
}

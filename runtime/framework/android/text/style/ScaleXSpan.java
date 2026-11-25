package android.text.style;

import android.text.TextPaint;

public class ScaleXSpan extends MetricAffectingSpan implements android.text.ParcelableSpan {
  private final float proportion;
  public ScaleXSpan(float proportion) { this.proportion = proportion; }
  public ScaleXSpan(android.os.Parcel src) { proportion = src.readFloat(); }
  public float getScaleX() { return proportion; }
  @Override public void updateDrawState(TextPaint ds) { ds.setTextScaleX(ds.getTextScaleX() * proportion); }
  @Override public void updateMeasureState(TextPaint ds) { ds.setTextScaleX(ds.getTextScaleX() * proportion); }
  @Override public int getSpanTypeId() { return 4; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeFloat(proportion); }
}

package android.text.style;

import android.text.TextPaint;

public class SuperscriptSpan extends MetricAffectingSpan implements android.text.ParcelableSpan {
  public SuperscriptSpan() {}
  public SuperscriptSpan(android.os.Parcel src) {}
  @Override public void updateDrawState(TextPaint tp) { tp.baselineShift += (int) (tp.ascent() / 2); }
  @Override public void updateMeasureState(TextPaint tp) { tp.baselineShift += (int) (tp.ascent() / 2); }
  @Override public int getSpanTypeId() { return 14; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) {}
}

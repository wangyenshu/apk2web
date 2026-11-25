package android.text.style;

import android.text.TextPaint;

public class SubscriptSpan extends MetricAffectingSpan implements android.text.ParcelableSpan {
  public SubscriptSpan() {}
  public SubscriptSpan(android.os.Parcel src) {}
  @Override public void updateDrawState(TextPaint tp) { tp.baselineShift -= (int) (tp.ascent() / 2); }
  @Override public void updateMeasureState(TextPaint tp) { tp.baselineShift -= (int) (tp.ascent() / 2); }
  @Override public int getSpanTypeId() { return 15; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) {}
}

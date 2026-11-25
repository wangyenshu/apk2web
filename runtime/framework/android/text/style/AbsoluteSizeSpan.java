package android.text.style;

import android.text.TextPaint;

public class AbsoluteSizeSpan extends MetricAffectingSpan implements android.text.ParcelableSpan {
  private final int size;
  private final boolean dip;
  public AbsoluteSizeSpan(int size) { this(size, false); }
  public AbsoluteSizeSpan(int size, boolean dip) { this.size = size; this.dip = dip; }
  public AbsoluteSizeSpan(android.os.Parcel src) { size = src.readInt(); dip = src.readInt() != 0; }
  public int getSize() { return size; }
  public boolean getDip() { return dip; }
  private void apply(TextPaint ds) { ds.setTextSize(dip ? size * (ds.density > 0 ? ds.density : a2w.Values.METRICS.density) : size); }
  @Override public void updateDrawState(TextPaint ds) { apply(ds); }
  @Override public void updateMeasureState(TextPaint ds) { apply(ds); }
  @Override public int getSpanTypeId() { return 16; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeInt(size); dest.writeInt(dip ? 1 : 0); }
}

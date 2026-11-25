package android.text.style;

import android.text.TextPaint;

public class ForegroundColorSpan extends CharacterStyle implements UpdateAppearance, android.text.ParcelableSpan {
  private final int color;
  public ForegroundColorSpan(int color) { this.color = color; }
  public ForegroundColorSpan(android.os.Parcel src) { color = src.readInt(); }
  public int getForegroundColor() { return color; }
  @Override public void updateDrawState(TextPaint tp) { tp.setColor(color); }
  @Override public int getSpanTypeId() { return 2; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeInt(color); }
}

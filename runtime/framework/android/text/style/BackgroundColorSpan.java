package android.text.style;

import android.text.TextPaint;

public class BackgroundColorSpan extends CharacterStyle implements UpdateAppearance, android.text.ParcelableSpan {
  private final int color;
  public BackgroundColorSpan(int color) { this.color = color; }
  public BackgroundColorSpan(android.os.Parcel src) { color = src.readInt(); }
  public int getBackgroundColor() { return color; }
  @Override public void updateDrawState(TextPaint tp) { tp.bgColor = color; }
  @Override public int getSpanTypeId() { return 12; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeInt(color); }
}

package android.text.style;

import android.text.TextPaint;

public class StrikethroughSpan extends CharacterStyle implements UpdateAppearance, android.text.ParcelableSpan {
  public StrikethroughSpan() {}
  public StrikethroughSpan(android.os.Parcel src) {}
  @Override public void updateDrawState(TextPaint ds) { ds.setStrikeThruText(true); }
  @Override public int getSpanTypeId() { return 5; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) {}
}

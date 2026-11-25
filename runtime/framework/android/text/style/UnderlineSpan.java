package android.text.style;

import android.text.TextPaint;

public class UnderlineSpan extends CharacterStyle implements UpdateAppearance, android.text.ParcelableSpan {
  public UnderlineSpan() {}
  public UnderlineSpan(android.os.Parcel src) {}
  @Override public void updateDrawState(TextPaint ds) { ds.setUnderlineText(true); }
  @Override public int getSpanTypeId() { return 6; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) {}
}

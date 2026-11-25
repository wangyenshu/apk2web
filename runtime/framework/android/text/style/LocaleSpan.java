package android.text.style;

import android.text.TextPaint;

public class LocaleSpan extends MetricAffectingSpan implements android.text.ParcelableSpan {
  private final java.util.Locale locale;
  public LocaleSpan(java.util.Locale locale) { this.locale = locale; }
  public LocaleSpan(android.os.Parcel src) { locale = java.util.Locale.forLanguageTag(src.readString()); }
  public java.util.Locale getLocale() { return locale; }
  @Override public void updateDrawState(TextPaint ds) {}
  @Override public void updateMeasureState(TextPaint p) {}
  @Override public int getSpanTypeId() { return 23; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeString(locale.toLanguageTag()); }
}

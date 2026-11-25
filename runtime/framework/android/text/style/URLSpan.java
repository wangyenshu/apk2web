package android.text.style;

import android.view.View;

public class URLSpan extends ClickableSpan implements android.text.ParcelableSpan {
  private final String url;
  public URLSpan(String url) { this.url = url; }
  public URLSpan(android.os.Parcel src) { url = src.readString(); }
  public String getURL() { return url; }
  @Override public void onClick(View widget) {
    android.net.Uri uri = android.net.Uri.parse(url);
    android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_VIEW, uri);
    intent.putExtra("com.android.browser.application_id", widget.getContext().getPackageName());
    try { widget.getContext().startActivity(intent); }
    catch (RuntimeException e) { android.util.Log.w("URLSpan", "Activity was not found for intent, " + intent); }
  }
  @Override public int getSpanTypeId() { return 11; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeString(url); }
}

package android.content;
import android.net.Uri;
public class ContentProviderResult implements android.os.Parcelable {
  public final Uri uri;
  public final Integer count;
  public final android.os.Bundle extras;
  public final Throwable exception;
  public ContentProviderResult(Uri uri) { this.uri = uri; count = null; extras = null; exception = null; }
  public ContentProviderResult(int count) { uri = null; this.count = count; extras = null; exception = null; }
  public ContentProviderResult(android.os.Bundle extras) { uri = null; count = null; this.extras = extras; exception = null; }
  public ContentProviderResult(Throwable exception) { uri = null; count = null; extras = null; this.exception = exception; }
  @Override public String toString() { return uri != null ? "ContentProviderResult(uri=" + uri + ")" : "ContentProviderResult(count=" + count + ")"; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel dest, int flags) {}
}

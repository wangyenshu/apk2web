package android.text.style;

public class EasyEditSpan implements android.text.ParcelableSpan {
  public static final String EXTRA_TEXT_CHANGED_TYPE = "android.text.style.EXTRA_TEXT_CHANGED_TYPE";
  public static final int TEXT_DELETED = 1, TEXT_MODIFIED = 2;
  public EasyEditSpan() {}
  public EasyEditSpan(android.app.PendingIntent p) {}
  @Override public int getSpanTypeId() { return 22; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) {}
}

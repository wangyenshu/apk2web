package android.text.style;

public class SpellCheckSpan implements android.text.ParcelableSpan {
  public SpellCheckSpan() {}
  @Override public int getSpanTypeId() { return 20; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) {}
}

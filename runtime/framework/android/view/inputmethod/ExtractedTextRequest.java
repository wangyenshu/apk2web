package android.view.inputmethod;

/** What an input method asks of InputConnection.getExtractedText. */
public class ExtractedTextRequest implements android.os.Parcelable {
  public int token, flags, hintMaxLines, hintMaxChars;
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
}

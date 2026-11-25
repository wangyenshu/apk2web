package android.view.inputmethod;

/** A snapshot of an editor's text for an input method. */
public class ExtractedText implements android.os.Parcelable {
  public static final int FLAG_SINGLE_LINE = 1, FLAG_SELECTING = 2;
  public CharSequence text, hint;
  public int startOffset, partialStartOffset = -1, partialEndOffset, selectionStart, selectionEnd, flags;
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
}

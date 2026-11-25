package android.view.inputmethod;

/** A completion an editor offers an input method. */
public final class CompletionInfo implements android.os.Parcelable {
  private final long mId; private final int mPosition; private final CharSequence mText, mLabel;
  public CompletionInfo(long id, int index, CharSequence text) { this(id, index, text, null); }
  public CompletionInfo(long id, int index, CharSequence text, CharSequence label) { mId = id; mPosition = index; mText = text; mLabel = label; }
  public long getId() { return mId; }
  public int getPosition() { return mPosition; }
  public CharSequence getText() { return mText; }
  public CharSequence getLabel() { return mLabel; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
}

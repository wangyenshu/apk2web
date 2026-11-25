package android.view.inputmethod;

/** A correction an input method applies. */
public final class CorrectionInfo implements android.os.Parcelable {
  private final int mOffset; private final CharSequence mOld, mNew;
  public CorrectionInfo(int offset, CharSequence oldText, CharSequence newText) { mOffset = offset; mOld = oldText; mNew = newText; }
  public int getOffset() { return mOffset; }
  public CharSequence getOldText() { return mOld; }
  public CharSequence getNewText() { return mNew; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
}

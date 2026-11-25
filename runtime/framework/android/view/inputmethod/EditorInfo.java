package android.view.inputmethod;

import android.os.Parcel;
import android.text.TextUtils;

/** What a text field tells the input method: its input type, IME options, initial selection and surrounding text. */
public class EditorInfo implements android.text.InputType, android.os.Parcelable {
  public static final int IME_MASK_ACTION = 255, IME_ACTION_UNSPECIFIED = 0, IME_ACTION_NONE = 1, IME_ACTION_GO = 2, IME_ACTION_SEARCH = 3,
      IME_ACTION_SEND = 4, IME_ACTION_NEXT = 5, IME_ACTION_DONE = 6, IME_ACTION_PREVIOUS = 7, IME_NULL = 0;
  public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 0x1000000, IME_FLAG_NO_FULLSCREEN = 0x2000000, IME_FLAG_NAVIGATE_PREVIOUS = 0x4000000,
      IME_FLAG_NAVIGATE_NEXT = 0x8000000, IME_FLAG_NO_EXTRACT_UI = 0x10000000, IME_FLAG_NO_ACCESSORY_ACTION = 0x20000000,
      IME_FLAG_NO_ENTER_ACTION = 0x40000000, IME_FLAG_FORCE_ASCII = 0x80000000;
  public int inputType = TYPE_NULL, imeOptions = IME_NULL, actionId, initialSelStart = -1, initialSelEnd = -1, initialCapsMode, fieldId;
  public String privateImeOptions, packageName, fieldName;
  public String[] contentMimeTypes;
  public CharSequence actionLabel, hintText, label;
  public android.os.Bundle extras;
  public android.os.LocaleList hintLocales;
  private SurroundingText mInitialSurroundingText;
  private int mInitialToolType;
  private boolean mStylusHandwritingEnabled;
  private java.util.List<Class<? extends HandwritingGesture>> mSupportedGestures = new java.util.ArrayList<>();
  private java.util.Set<Class<? extends PreviewableHandwritingGesture>> mSupportedGesturePreviews = new java.util.HashSet<>();
  public EditorInfo() {}

  public void setSupportedHandwritingGestures(java.util.List<Class<? extends HandwritingGesture>> gestures) { mSupportedGestures = new java.util.ArrayList<>(gestures); }
  public java.util.List<Class<? extends HandwritingGesture>> getSupportedHandwritingGestures() { return new java.util.ArrayList<>(mSupportedGestures); }
  public void setSupportedHandwritingGesturePreviews(java.util.Set<Class<? extends PreviewableHandwritingGesture>> gestures) { mSupportedGesturePreviews = new java.util.HashSet<>(gestures); }
  public java.util.Set<Class<? extends PreviewableHandwritingGesture>> getSupportedHandwritingGesturePreviews() { return new java.util.HashSet<>(mSupportedGesturePreviews); }
  public void setStylusHandwritingEnabled(boolean enabled) { mStylusHandwritingEnabled = enabled; }
  public boolean isStylusHandwritingEnabled() { return mStylusHandwritingEnabled; }
  public int getInitialToolType() { return mInitialToolType; }
  public void setInitialToolType(int toolType) { mInitialToolType = toolType; }

  // ---- the text around the initial selection (none for passwords)
  public void setInitialSurroundingText(CharSequence sourceText) { setInitialSurroundingSubText(sourceText, 0); }
  public void setInitialSurroundingSubText(CharSequence subText, int subTextStart) {
    java.util.Objects.requireNonNull(subText);
    if (isPasswordInputType(inputType)) { mInitialSurroundingText = null; return; }
    final int selStart = initialSelStart > initialSelEnd ? initialSelEnd - subTextStart : initialSelStart - subTextStart;
    final int selEnd = initialSelStart > initialSelEnd ? initialSelStart - subTextStart : initialSelEnd - subTextStart;
    if (subTextStart < 0 || selStart < 0 || selEnd > subText.length()) { mInitialSurroundingText = null; return; }
    mInitialSurroundingText = new SurroundingText(subText, selStart, selEnd, subTextStart);
  }
  private static boolean isPasswordInputType(int t) {
    int v = t & (TYPE_MASK_CLASS | TYPE_MASK_VARIATION);
    return v == (TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD) || v == (TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_WEB_PASSWORD)
        || v == (TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) || v == (TYPE_CLASS_NUMBER | TYPE_NUMBER_VARIATION_PASSWORD);
  }
  private static CharSequence part(CharSequence s, int a, int b, int flags) { return (flags & InputConnection.GET_TEXT_WITH_STYLES) != 0 ? s.subSequence(a, b) : TextUtils.substring(s, a, b); }
  public CharSequence getInitialTextBeforeCursor(int length, int flags) {
    if (mInitialSurroundingText == null) return null;
    int selStart = Math.min(mInitialSurroundingText.getSelectionStart(), mInitialSurroundingText.getSelectionEnd());
    int n = Math.min(length, selStart);
    return part(mInitialSurroundingText.getText(), selStart - n, selStart, flags);
  }
  public CharSequence getInitialSelectedText(int flags) {
    if (mInitialSurroundingText == null) return null;
    int sourceLength = Math.abs(initialSelEnd - initialSelStart);
    int a = mInitialSurroundingText.getSelectionStart(), b = mInitialSurroundingText.getSelectionEnd();
    if (a > b) { int t = a; a = b; b = t; }
    if (initialSelStart < 0 || initialSelEnd < 0 || b - a != sourceLength) return null;
    return part(mInitialSurroundingText.getText(), a, b, flags);
  }
  public CharSequence getInitialTextAfterCursor(int length, int flags) {
    if (mInitialSurroundingText == null) return null;
    CharSequence t = mInitialSurroundingText.getText();
    int selEnd = Math.max(mInitialSurroundingText.getSelectionStart(), mInitialSurroundingText.getSelectionEnd());
    int n = Math.min(length, t.length() - selEnd);
    return part(t, selEnd, selEnd + n, flags);
  }
  public SurroundingText getInitialSurroundingText(int beforeLength, int afterLength, int flags) {
    if (beforeLength < 0 || afterLength < 0) throw new IllegalArgumentException("length must be non-negative");
    if (mInitialSurroundingText == null) return null;
    CharSequence t = mInitialSurroundingText.getText();
    int a = mInitialSurroundingText.getSelectionStart(), b = mInitialSurroundingText.getSelectionEnd();
    if (a > b) { int x = a; a = b; b = x; }
    int start = Math.max(0, a - beforeLength), end = Math.min(t.length(), b + afterLength);
    return new SurroundingText(part(t, start, end, flags), a - start, b - start, mInitialSurroundingText.getOffset() + start);
  }
  /** the input types older apps (before API 11) understand */
  public final void makeCompatible(int targetSdkVersion) {
    if (targetSdkVersion >= 11) return;
    switch (inputType & (TYPE_MASK_CLASS | TYPE_MASK_VARIATION)) {
      case TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS: inputType = TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_EMAIL_ADDRESS | (inputType & TYPE_MASK_FLAGS); break;
      case TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_WEB_PASSWORD: inputType = TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD | (inputType & TYPE_MASK_FLAGS); break;
      case TYPE_CLASS_NUMBER | TYPE_NUMBER_VARIATION_NORMAL: case TYPE_CLASS_NUMBER | TYPE_NUMBER_VARIATION_PASSWORD: inputType = TYPE_CLASS_NUMBER | (inputType & TYPE_MASK_FLAGS); break;
    }
  }
  public void dump(android.util.Printer pw, String prefix) {
    pw.println(prefix + "inputType=0x" + Integer.toHexString(inputType) + " imeOptions=0x" + Integer.toHexString(imeOptions) + " privateImeOptions=" + privateImeOptions);
    pw.println(prefix + "actionLabel=" + actionLabel + " actionId=" + actionId);
    pw.println(prefix + "initialSelStart=" + initialSelStart + " initialSelEnd=" + initialSelEnd + " initialCapsMode=0x" + Integer.toHexString(initialCapsMode));
    pw.println(prefix + "hintText=" + hintText + " label=" + label);
    pw.println(prefix + "packageName=" + packageName + " fieldId=" + fieldId + " fieldName=" + fieldName);
    pw.println(prefix + "extras=" + extras);
    pw.println(prefix + "hintLocales=" + hintLocales);
    pw.println(prefix + "contentMimeTypes=" + java.util.Arrays.toString(contentMimeTypes));
  }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(inputType); dest.writeInt(imeOptions); dest.writeString(privateImeOptions); dest.writeInt(actionId);
    TextUtils.writeToParcel(actionLabel, dest, flags); dest.writeInt(initialSelStart); dest.writeInt(initialSelEnd); dest.writeInt(initialCapsMode);
    TextUtils.writeToParcel(hintText, dest, flags); TextUtils.writeToParcel(label, dest, flags); dest.writeString(packageName); dest.writeInt(fieldId);
    dest.writeString(fieldName); dest.writeBundle(extras); dest.writeStringArray(contentMimeTypes); dest.writeInt(mInitialToolType);
    dest.writeInt(mStylusHandwritingEnabled ? 1 : 0);
    if (mInitialSurroundingText != null) {
      dest.writeInt(1); TextUtils.writeToParcel(mInitialSurroundingText.getText(), dest, flags);
      dest.writeInt(mInitialSurroundingText.getSelectionStart()); dest.writeInt(mInitialSurroundingText.getSelectionEnd()); dest.writeInt(mInitialSurroundingText.getOffset());
    } else dest.writeInt(0);
  }
  public static final android.os.Parcelable.Creator<EditorInfo> CREATOR = new android.os.Parcelable.Creator<EditorInfo>() {
    public EditorInfo createFromParcel(Parcel s) {
      EditorInfo r = new EditorInfo();
      r.inputType = s.readInt(); r.imeOptions = s.readInt(); r.privateImeOptions = s.readString(); r.actionId = s.readInt();
      r.actionLabel = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(s); r.initialSelStart = s.readInt(); r.initialSelEnd = s.readInt(); r.initialCapsMode = s.readInt();
      r.hintText = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(s); r.label = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(s); r.packageName = s.readString(); r.fieldId = s.readInt();
      r.fieldName = s.readString(); r.extras = s.readBundle(); r.contentMimeTypes = s.createStringArray(); r.mInitialToolType = s.readInt();
      r.mStylusHandwritingEnabled = s.readInt() != 0;
      if (s.readInt() != 0) r.mInitialSurroundingText = new SurroundingText(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(s), s.readInt(), s.readInt(), s.readInt());
      return r;
    }
    public EditorInfo[] newArray(int size) { return new EditorInfo[size]; }
  };
}

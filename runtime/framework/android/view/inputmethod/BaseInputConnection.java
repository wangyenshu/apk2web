package android.view.inputmethod;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;

/** An InputConnection over an Editable (the view's own when it is a TextView), as the platform's. */
public class BaseInputConnection implements InputConnection {
  private static final Object COMPOSING = new ComposingText();
  static final class ComposingText implements android.text.NoCopySpan {}
  protected final InputMethodManager mIMM;
  final View mTargetView;
  final boolean mFallbackMode;
  private Editable mEditable;
  private int mBatch;

  public BaseInputConnection(View targetView, boolean fullEditor) {
    mTargetView = targetView;
    mFallbackMode = !fullEditor;
    mIMM = null;
  }
  public static final void removeComposingSpans(Spannable text) {
    text.removeSpan(COMPOSING);
    Object[] sps = text.getSpans(0, text.length(), Object.class);
    for (int i = sps.length - 1; i >= 0; i--) if ((text.getSpanFlags(sps[i]) & Spanned.SPAN_COMPOSING) != 0) text.removeSpan(sps[i]);
  }
  public static void setComposingSpans(Spannable text) { setComposingSpans(text, 0, text.length()); }
  public static void setComposingSpans(Spannable text, int start, int end) {
    text.setSpan(COMPOSING, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);
  }
  public static int getComposingSpanStart(Spannable text) { return text.getSpanStart(COMPOSING); }
  public static int getComposingSpanEnd(Spannable text) { return text.getSpanEnd(COMPOSING); }

  public Editable getEditable() {
    if (mTargetView instanceof android.widget.TextView) {
      CharSequence t = ((android.widget.TextView) mTargetView).getText();
      if (t instanceof Editable) return (Editable) t;
    }
    if (mEditable == null) { mEditable = new SpannableStringBuilder(""); Selection.setSelection(mEditable, 0); }
    return mEditable;
  }
  public boolean beginBatchEdit() { mBatch++; return true; }
  public boolean endBatchEdit() { if (mBatch > 0) mBatch--; return mBatch > 0; }
  public void closeConnection() { finishComposingText(); }
  public boolean clearMetaKeyStates(int states) { return false; }
  public boolean commitCompletion(CompletionInfo text) { return false; }
  public boolean commitCorrection(CorrectionInfo info) { return false; }
  public boolean commitText(CharSequence text, int newCursorPosition) { replaceText(text, newCursorPosition, false); sendCurrentText(); return true; }
  public boolean deleteSurroundingText(int beforeLength, int afterLength) {
    Editable content = getEditable();
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a > b) { int t = a; a = b; b = t; }
    if (a < 0) a = b = content.length();
    int ca = getComposingSpanStart(content), cb = getComposingSpanEnd(content);
    if (cb < ca) { int t = ca; ca = cb; cb = t; }
    if (ca != -1 && cb != -1) { if (ca < a) a = ca; if (cb > b) b = cb; }
    int deleted = 0;
    if (beforeLength > 0) { int start = Math.max(0, a - beforeLength); content.delete(start, a); deleted = a - start; }
    if (afterLength > 0) { b -= deleted; int end = Math.min(content.length(), b + afterLength); content.delete(b, end); }
    sendCurrentText();
    return true;
  }
  public boolean finishComposingText() {
    Editable content = getEditable();
    if (content != null) removeComposingSpans(content);
    sendCurrentText();
    return true;
  }
  public int getCursorCapsMode(int reqModes) {
    Editable content = getEditable();
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a > b) a = b;
    return android.text.TextUtils.getCapsMode(content, Math.max(0, a), reqModes);
  }
  public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
    Editable content = getEditable();
    ExtractedText e = new ExtractedText();
    e.text = content.toString();
    e.selectionStart = Selection.getSelectionStart(content);
    e.selectionEnd = Selection.getSelectionEnd(content);
    return e;
  }
  public CharSequence getTextBeforeCursor(int length, int flags) {
    Editable content = getEditable();
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a > b) a = b;
    if (a <= 0) return "";
    if (length > a) length = a;
    return content.subSequence(a - length, a).toString();
  }
  public CharSequence getSelectedText(int flags) {
    Editable content = getEditable();
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a > b) { int t = a; a = b; b = t; }
    if (a == b || a < 0) return null;
    return content.subSequence(a, b).toString();
  }
  public CharSequence getTextAfterCursor(int length, int flags) {
    Editable content = getEditable();
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a > b) b = a;
    if (b < 0) b = 0;
    if (b + length > content.length()) length = content.length() - b;
    return content.subSequence(b, b + length).toString();
  }
  public boolean performEditorAction(int actionCode) {
    if (mTargetView instanceof android.widget.TextView) { ((android.widget.TextView) mTargetView).onEditorAction(actionCode); return true; }
    long t = android.os.SystemClock.uptimeMillis();
    sendKeyEvent(new KeyEvent(t, t, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER, 0));
    sendKeyEvent(new KeyEvent(t, t, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER, 0));
    return true;
  }
  public boolean performContextMenuAction(int id) { return false; }
  public boolean performPrivateCommand(String action, android.os.Bundle data) { return false; }
  public boolean requestCursorUpdates(int mode) { return false; }
  public android.os.Handler getHandler() { return null; }
  public boolean setComposingText(CharSequence text, int newCursorPosition) { replaceText(text, newCursorPosition, true); return true; }
  public boolean setComposingRegion(int start, int end) {
    Editable content = getEditable();
    removeComposingSpans(content);
    int a = Math.max(0, Math.min(start, end)), b = Math.min(content.length(), Math.max(start, end));
    if (a != b) setComposingSpans(content, a, b);
    return true;
  }
  public boolean setSelection(int start, int end) {
    Editable content = getEditable();
    int len = content.length();
    if (start > len || end > len || start < 0 || end < 0) return true;
    Selection.setSelection(content, start, end);
    return true;
  }
  public boolean sendKeyEvent(KeyEvent event) { return mTargetView != null && mTargetView.dispatchKeyEvent(event); }
  public boolean reportFullscreenMode(boolean enabled) { return true; }
  /** deletes code points (a surrogate pair is one) around the selection, composing text excluded like deleteSurroundingText */
  public boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
    Editable content = getEditable();
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a > b) { int t = a; a = b; b = t; }
    if (a < 0) a = b = content.length();
    int ca = getComposingSpanStart(content), cb = getComposingSpanEnd(content);
    if (cb < ca) { int t = ca; ca = cb; cb = t; }
    if (ca != -1 && cb != -1) { if (ca < a) a = ca; if (cb > b) b = cb; }
    int start = a;
    for (int n = 0; n < beforeLength && start > 0; n++) {
      start--;
      if (start > 0 && Character.isLowSurrogate(content.charAt(start)) && Character.isHighSurrogate(content.charAt(start - 1))) start--;
      else if (Character.isSurrogate(content.charAt(start))) return false;
    }
    int end = b, len = content.length();
    for (int n = 0; n < afterLength && end < len; n++) {
      if (end + 1 < len && Character.isHighSurrogate(content.charAt(end)) && Character.isLowSurrogate(content.charAt(end + 1))) end += 2;
      else if (Character.isSurrogate(content.charAt(end))) return false;
      else end++;
    }
    beginBatchEdit();
    if (end > b) content.delete(b, end);
    if (start < a) content.delete(start, a);
    endBatchEdit();
    return true;
  }
  @Override public SurroundingText getSurroundingText(int beforeLength, int afterLength, int flags) {
    if (beforeLength < 0 || afterLength < 0) throw new IllegalArgumentException("negative length");
    Editable content = getEditable();
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a < 0 || b < 0) return null;
    if (a > b) { int t = a; a = b; b = t; }
    int s = Math.max(0, a - beforeLength), e = (int) Math.min((long) content.length(), (long) b + afterLength);
    CharSequence t = (flags & GET_TEXT_WITH_STYLES) != 0 ? content.subSequence(s, e) : android.text.TextUtils.substring(content, s, e);
    return new SurroundingText(t, a - s, b - s, s);
  }
  @Override public boolean replaceText(int start, int end, CharSequence text, int newCursorPosition, TextAttribute textAttribute) {
    Editable content = getEditable();
    if (start < 0 || end < 0) throw new IllegalArgumentException("negative range");
    beginBatchEdit();
    finishComposingText();
    int a = Math.min(start, end), b = Math.min(content.length(), Math.max(start, end));
    a = Math.min(a, content.length());
    Selection.setSelection(content, a, b);
    replaceText(text, newCursorPosition, false);
    endBatchEdit();
    return true;
  }
  /** rich content goes to the target view's receiver (OnReceiveContentListener) */
  @Override public boolean commitContent(InputContentInfo inputContentInfo, int flags, android.os.Bundle opts) {
    if (mTargetView == null || mTargetView.getReceiveContentMimeTypes() == null) return false;
    if ((flags & INPUT_CONTENT_GRANT_READ_URI_PERMISSION) != 0) inputContentInfo.requestPermission();
    android.content.ClipData clip = new android.content.ClipData(inputContentInfo.getDescription(), new android.content.ClipData.Item(inputContentInfo.getContentUri()));
    android.view.ContentInfo payload = new android.view.ContentInfo.Builder(clip, android.view.ContentInfo.SOURCE_INPUT_METHOD)
        .setLinkUri(inputContentInfo.getLinkUri()).setExtras(opts).build();
    return mTargetView.performReceiveContent(payload) != payload;
  }
  @Override public TextSnapshot takeSnapshot() {
    Editable content = getEditable();
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a < 0 || b < 0) return null;
    int ca = getComposingSpanStart(content), cb = getComposingSpanEnd(content);
    if (ca > cb) { int t = ca; ca = cb; cb = t; }
    SurroundingText st = getSurroundingText(1024, 1024, GET_TEXT_WITH_STYLES);
    if (st == null) return null;
    int caps = getCursorCapsMode(android.text.TextUtils.CAP_MODE_CHARACTERS | android.text.TextUtils.CAP_MODE_WORDS | android.text.TextUtils.CAP_MODE_SENTENCES);
    return new TextSnapshot(st, ca, cb, caps);
  }
  private void sendCurrentText() {
    if (!mFallbackMode) return;
    Editable content = getEditable();
    int n = content.length();
    if (n == 0) return;
    if (n == 1) {
      // a single character: sent as key events, as the platform does for views without an editor
      KeyEvent[] events = android.view.KeyCharacterMap.load(-1).getEvents(new char[] {content.charAt(0)});
      if (events != null) { for (KeyEvent e : events) sendKeyEvent(e); content.clear(); return; }
    }
    long t = android.os.SystemClock.uptimeMillis();
    sendKeyEvent(new KeyEvent(t, content.toString(), -1, 0));
    content.clear();
  }
  private void replaceText(CharSequence text, int newCursorPosition, boolean composing) {
    Editable content = getEditable();
    beginBatchEdit();
    int a = getComposingSpanStart(content), b = getComposingSpanEnd(content);
    if (b < a) { int t = a; a = b; b = t; }
    if (a != -1 && b != -1) removeComposingSpans(content);
    else {
      a = Selection.getSelectionStart(content); b = Selection.getSelectionEnd(content);
      if (a < 0) a = 0;
      if (b < 0) b = 0;
      if (b < a) { int t = a; a = b; b = t; }
    }
    if (composing) {
      Spannable sp;
      if (!(text instanceof Spannable)) { sp = new android.text.SpannableStringBuilder(text); text = sp; } else sp = (Spannable) text;
      setComposingSpans(sp);
    }
    if (newCursorPosition > 0) newCursorPosition += b - 1; else newCursorPosition += a;
    if (newCursorPosition < 0) newCursorPosition = 0;
    if (newCursorPosition > content.length()) newCursorPosition = content.length();
    Selection.setSelection(content, newCursorPosition);
    content.replace(a, b, text);
    // (the cursor ends after the new text when it was asked for there)
    if (newCursorPosition == b && Selection.getSelectionEnd(content) < a + text.length()) Selection.setSelection(content, Math.min(content.length(), a + text.length()));
    endBatchEdit();
  }
}

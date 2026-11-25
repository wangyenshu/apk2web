package android.view.inputmethod;

/** Forwards every call to a target InputConnection (settable once unless mutable). */
public class InputConnectionWrapper implements InputConnection {
  private InputConnection mTarget;
  final boolean mMutable;
  public InputConnectionWrapper(InputConnection target, boolean mutable) { mMutable = mutable; mTarget = target; }
  public void setTarget(InputConnection target) {
    if (mTarget != null && !mMutable) throw new SecurityException("not mutable");
    mTarget = target;
  }
  public java.lang.CharSequence getTextBeforeCursor(int p0, int p1) { return mTarget.getTextBeforeCursor(p0, p1); }
  public java.lang.CharSequence getTextAfterCursor(int p0, int p1) { return mTarget.getTextAfterCursor(p0, p1); }
  public java.lang.CharSequence getSelectedText(int p0) { return mTarget.getSelectedText(p0); }
  public android.view.inputmethod.SurroundingText getSurroundingText(int p0, int p1, int p2) { return mTarget.getSurroundingText(p0, p1, p2); }
  public int getCursorCapsMode(int p0) { return mTarget.getCursorCapsMode(p0); }
  public android.view.inputmethod.ExtractedText getExtractedText(android.view.inputmethod.ExtractedTextRequest p0, int p1) { return mTarget.getExtractedText(p0, p1); }
  public boolean deleteSurroundingTextInCodePoints(int p0, int p1) { return mTarget.deleteSurroundingTextInCodePoints(p0, p1); }
  public boolean deleteSurroundingText(int p0, int p1) { return mTarget.deleteSurroundingText(p0, p1); }
  public boolean setComposingText(java.lang.CharSequence p0, int p1) { return mTarget.setComposingText(p0, p1); }
  public boolean setComposingText(java.lang.CharSequence p0, int p1, android.view.inputmethod.TextAttribute p2) { return mTarget.setComposingText(p0, p1, p2); }
  public boolean setComposingRegion(int p0, int p1) { return mTarget.setComposingRegion(p0, p1); }
  public boolean setComposingRegion(int p0, int p1, android.view.inputmethod.TextAttribute p2) { return mTarget.setComposingRegion(p0, p1, p2); }
  public boolean finishComposingText() { return mTarget.finishComposingText(); }
  public boolean commitText(java.lang.CharSequence p0, int p1) { return mTarget.commitText(p0, p1); }
  public boolean commitText(java.lang.CharSequence p0, int p1, android.view.inputmethod.TextAttribute p2) { return mTarget.commitText(p0, p1, p2); }
  public boolean commitCompletion(android.view.inputmethod.CompletionInfo p0) { return mTarget.commitCompletion(p0); }
  public boolean commitCorrection(android.view.inputmethod.CorrectionInfo p0) { return mTarget.commitCorrection(p0); }
  public boolean setSelection(int p0, int p1) { return mTarget.setSelection(p0, p1); }
  public boolean performEditorAction(int p0) { return mTarget.performEditorAction(p0); }
  public boolean performContextMenuAction(int p0) { return mTarget.performContextMenuAction(p0); }
  public boolean beginBatchEdit() { return mTarget.beginBatchEdit(); }
  public boolean endBatchEdit() { return mTarget.endBatchEdit(); }
  public boolean sendKeyEvent(android.view.KeyEvent p0) { return mTarget.sendKeyEvent(p0); }
  public boolean clearMetaKeyStates(int p0) { return mTarget.clearMetaKeyStates(p0); }
  public boolean reportFullscreenMode(boolean p0) { return mTarget.reportFullscreenMode(p0); }
  public boolean performSpellCheck() { return mTarget.performSpellCheck(); }
  public boolean performPrivateCommand(java.lang.String p0, android.os.Bundle p1) { return mTarget.performPrivateCommand(p0, p1); }
  public void performHandwritingGesture(android.view.inputmethod.HandwritingGesture p0, java.util.concurrent.Executor p1, java.util.function.IntConsumer p2) { mTarget.performHandwritingGesture(p0, p1, p2); }
  public boolean previewHandwritingGesture(android.view.inputmethod.PreviewableHandwritingGesture p0, android.os.CancellationSignal p1) { return mTarget.previewHandwritingGesture(p0, p1); }
  public boolean requestCursorUpdates(int p0) { return mTarget.requestCursorUpdates(p0); }
  public boolean requestCursorUpdates(int p0, int p1) { return mTarget.requestCursorUpdates(p0, p1); }
  public void requestTextBoundsInfo(android.graphics.RectF p0, java.util.concurrent.Executor p1, java.util.function.Consumer<android.view.inputmethod.TextBoundsInfoResult> p2) { mTarget.requestTextBoundsInfo(p0, p1, p2); }
  public android.os.Handler getHandler() { return mTarget.getHandler(); }
  public void closeConnection() { mTarget.closeConnection(); }
  public boolean commitContent(android.view.inputmethod.InputContentInfo p0, int p1, android.os.Bundle p2) { return mTarget.commitContent(p0, p1, p2); }
  public boolean setImeConsumesInput(boolean p0) { return mTarget.setImeConsumesInput(p0); }
  public android.view.inputmethod.TextSnapshot takeSnapshot() { return mTarget.takeSnapshot(); }
  public boolean replaceText(int p0, int p1, java.lang.CharSequence p2, int p3, android.view.inputmethod.TextAttribute p4) { return mTarget.replaceText(p0, p1, p2, p3, p4); }
}

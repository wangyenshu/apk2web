package android.view.inputmethod;

import android.os.Bundle;
import android.view.KeyEvent;

/**
 * The link between an input method and the view receiving its text. The methods abstract on Android have defaults
 * here (a minimal answer or no effect) so connections written for older levels still link.
 */
public interface InputConnection {
  int GET_TEXT_WITH_STYLES = 1, GET_EXTRACTED_TEXT_MONITOR = 1, INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;
  int CURSOR_UPDATE_IMMEDIATE = 1, CURSOR_UPDATE_MONITOR = 2, CURSOR_UPDATE_FILTER_EDITOR_BOUNDS = 4, CURSOR_UPDATE_FILTER_CHARACTER_BOUNDS = 8,
      CURSOR_UPDATE_FILTER_INSERTION_MARKER = 16, CURSOR_UPDATE_FILTER_VISIBLE_LINE_BOUNDS = 32, CURSOR_UPDATE_FILTER_TEXT_APPEARANCE = 64;
  int HANDWRITING_GESTURE_RESULT_UNKNOWN = 0, HANDWRITING_GESTURE_RESULT_SUCCESS = 1, HANDWRITING_GESTURE_RESULT_UNSUPPORTED = 2,
      HANDWRITING_GESTURE_RESULT_FAILED = 3, HANDWRITING_GESTURE_RESULT_CANCELLED = 4, HANDWRITING_GESTURE_RESULT_FALLBACK = 5;
  boolean commitText(CharSequence text, int newCursorPosition);
  boolean deleteSurroundingText(int beforeLength, int afterLength);
  boolean sendKeyEvent(KeyEvent event);
  boolean finishComposingText();
  boolean performEditorAction(int editorAction);
  default CharSequence getTextBeforeCursor(int n, int flags) { return null; }
  default CharSequence getTextAfterCursor(int n, int flags) { return null; }
  default CharSequence getSelectedText(int flags) { return null; }
  /** the text around the selection, from getTextBeforeCursor, getSelectedText and getTextAfterCursor */
  default SurroundingText getSurroundingText(int beforeLength, int afterLength, int flags) {
    if (beforeLength < 0 || afterLength < 0) throw new IllegalArgumentException("negative length");
    CharSequence before = getTextBeforeCursor(beforeLength, flags);
    if (before == null) return null;
    CharSequence after = getTextAfterCursor(afterLength, flags);
    if (after == null) return null;
    CharSequence selected = getSelectedText(flags);
    if (selected == null) selected = "";
    return new SurroundingText(android.text.TextUtils.concat(before, selected, after), before.length(), before.length() + selected.length(), -1);
  }
  default int getCursorCapsMode(int reqModes) { return 0; }
  default ExtractedText getExtractedText(ExtractedTextRequest request, int flags) { return null; }
  default boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) { return deleteSurroundingText(beforeLength, afterLength); }
  default boolean setComposingText(CharSequence text, int newCursorPosition) { return commitText(text, newCursorPosition); }
  default boolean setComposingText(CharSequence text, int newCursorPosition, TextAttribute textAttribute) { return setComposingText(text, newCursorPosition); }
  default boolean setComposingRegion(int start, int end) { return false; }
  default boolean setComposingRegion(int start, int end, TextAttribute textAttribute) { return setComposingRegion(start, end); }
  default boolean commitText(CharSequence text, int newCursorPosition, TextAttribute textAttribute) { return commitText(text, newCursorPosition); }
  default boolean commitCompletion(CompletionInfo text) { return text != null && commitText(text.getText(), 1); }
  default boolean commitCorrection(CorrectionInfo correctionInfo) { return false; }
  default boolean setSelection(int start, int end) { return false; }
  default boolean performContextMenuAction(int id) { return false; }
  default boolean beginBatchEdit() { return false; }
  default boolean endBatchEdit() { return false; }
  default boolean clearMetaKeyStates(int states) { return false; }
  default boolean reportFullscreenMode(boolean enabled) { return false; }
  default boolean performPrivateCommand(String action, Bundle data) { return false; }
  default boolean performSpellCheck() { return false; }
  /** handwriting gestures are not supported: the consumer gets RESULT_UNSUPPORTED */
  default void performHandwritingGesture(HandwritingGesture gesture, java.util.concurrent.Executor executor, java.util.function.IntConsumer consumer) {
    if (executor != null && consumer != null) executor.execute(new GestureUnsupported(consumer));
  }
  default boolean previewHandwritingGesture(PreviewableHandwritingGesture gesture, android.os.CancellationSignal cancellationSignal) { return false; }
  default boolean requestCursorUpdates(int cursorUpdateMode) { return false; }
  /** a filter (which bounds to report) this connection does not know: false */
  default boolean requestCursorUpdates(int cursorUpdateMode, int cursorUpdateFilter) { return cursorUpdateFilter == 0 && requestCursorUpdates(cursorUpdateMode); }
  default void requestTextBoundsInfo(android.graphics.RectF bounds, java.util.concurrent.Executor executor, java.util.function.Consumer<TextBoundsInfoResult> consumer) {
    java.util.Objects.requireNonNull(executor); java.util.Objects.requireNonNull(consumer);
    executor.execute(new BoundsUnsupported(consumer));
  }
  default android.os.Handler getHandler() { return null; }
  default void closeConnection() {}
  default boolean commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts) { return false; }
  default boolean setImeConsumesInput(boolean imeConsumesInput) { return false; }
  default TextSnapshot takeSnapshot() { return null; }
  /** selects [start, end) and commits text over it, in one batch */
  default boolean replaceText(int start, int end, CharSequence text, int newCursorPosition, TextAttribute textAttribute) {
    if (start < 0 || end < 0) throw new IllegalArgumentException("negative range");
    beginBatchEdit();
    finishComposingText();
    setSelection(start, end);
    commitText(text, newCursorPosition, textAttribute);
    endBatchEdit();
    return true;
  }

  final class GestureUnsupported implements Runnable {
    private final java.util.function.IntConsumer mConsumer;
    GestureUnsupported(java.util.function.IntConsumer c) { mConsumer = c; }
    public void run() { mConsumer.accept(HANDWRITING_GESTURE_RESULT_UNSUPPORTED); }
  }
  final class BoundsUnsupported implements Runnable {
    private final java.util.function.Consumer<TextBoundsInfoResult> mConsumer;
    BoundsUnsupported(java.util.function.Consumer<TextBoundsInfoResult> c) { mConsumer = c; }
    public void run() { mConsumer.accept(new TextBoundsInfoResult(TextBoundsInfoResult.CODE_UNSUPPORTED)); }
  }
}

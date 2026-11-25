package android.view.inputmethod;

/** The editor's text around the selection, the composing region and the caps mode, taken at one moment. */
public final class TextSnapshot {
  private final SurroundingText mSurroundingText;
  private final int mCompositionStart, mCompositionEnd, mCursorCapsMode;
  public TextSnapshot(SurroundingText surroundingText, int compositionStart, int compositionEnd, int cursorCapsMode) {
    mSurroundingText = java.util.Objects.requireNonNull(surroundingText);
    if (compositionStart < -1 || compositionEnd < -1) throw new IllegalArgumentException("compositionStart and compositionEnd must be -1 or higher.");
    if ((compositionStart == -1) != (compositionEnd == -1)) throw new IllegalArgumentException("compositionStart and compositionEnd must be -1 at the same time.");
    if (compositionStart > compositionEnd) throw new IllegalArgumentException("compositionStart must not be larger than compositionEnd.");
    mCompositionStart = compositionStart; mCompositionEnd = compositionEnd; mCursorCapsMode = cursorCapsMode;
  }
  public SurroundingText getSurroundingText() { return mSurroundingText; }
  public int getSelectionStart() { return mSurroundingText.getOffset() < 0 ? -1 : mSurroundingText.getSelectionStart() + mSurroundingText.getOffset(); }
  public int getSelectionEnd() { return mSurroundingText.getOffset() < 0 ? -1 : mSurroundingText.getSelectionEnd() + mSurroundingText.getOffset(); }
  public int getCompositionStart() { return mCompositionStart; }
  public int getCompositionEnd() { return mCompositionEnd; }
  public int getCursorCapsMode() { return mCursorCapsMode; }
}

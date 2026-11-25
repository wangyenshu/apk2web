package android.view.inputmethod;

import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.view.KeyEvent;
import android.view.View;

/**
 * The input method: the page's text field placed over the focused EditText (a2w.TextInput) is the one keyboard. It
 * has no subtypes, no stylus handwriting, and no system picker or status icon.
 */
public final class InputMethodManager {
  public static final int SHOW_IMPLICIT = 1, SHOW_FORCED = 2, HIDE_IMPLICIT_ONLY = 1, HIDE_NOT_ALWAYS = 2,
      RESULT_UNCHANGED_SHOWN = 0, RESULT_UNCHANGED_HIDDEN = 1, RESULT_SHOWN = 2, RESULT_HIDDEN = 3;
  private static final InputMethodManager I = new InputMethodManager();
  /** the page's keyboard, as the one installed input method */
  static final InputMethodInfo KEYBOARD = new InputMethodInfo("a2w.page", "a2w.TextInput", "Browser keyboard", null);
  InputMethodManager() {}
  public static InputMethodManager get() { return I; }
  public boolean showSoftInput(View v, int flags) { return a2w.TextInput.show(v); }
  public boolean showSoftInput(View v, int flags, ResultReceiver r) {
    boolean was = a2w.TextInput.isActive(v), shown = showSoftInput(v, flags);
    if (r != null) r.send(shown ? (was ? RESULT_UNCHANGED_SHOWN : RESULT_SHOWN) : RESULT_UNCHANGED_HIDDEN, null);
    return shown;
  }
  @Deprecated public void showSoftInputFromInputMethod(IBinder t, int f) {}
  public boolean hideSoftInputFromWindow(IBinder token, int flags) { return a2w.TextInput.hide(); }
  public boolean hideSoftInputFromWindow(IBinder token, int flags, ResultReceiver r) {
    boolean hid = hideSoftInputFromWindow(token, flags);
    if (r != null) r.send(hid ? RESULT_HIDDEN : RESULT_UNCHANGED_HIDDEN, null);
    return hid;
  }
  @Deprecated public void hideSoftInputFromInputMethod(IBinder token, int flags) { a2w.TextInput.hide(); }
  @Deprecated public void toggleSoftInput(int show, int hide) { a2w.TextInput.toggle(); }
  @Deprecated public void toggleSoftInputFromWindow(IBinder t, int show, int hide) { a2w.TextInput.toggle(); }
  public boolean isActive(View v) { return a2w.TextInput.isActive(v); }
  public boolean isActive() { return a2w.TextInput.isActive(null); }
  public boolean isAcceptingText() { return a2w.TextInput.isActive(null); }
  public boolean isFullscreenMode() { return false; }
  /** the view's text changed under the input method: the page's field takes it again */
  public void restartInput(View v) { if (a2w.TextInput.isActive(v)) a2w.TextInput.show(v); }
  public void invalidateInput(View v) { restartInput(v); }
  public void updateSelection(View v, int selStart, int selEnd, int candidatesStart, int candidatesEnd) {}
  @Deprecated public void updateCursor(View v, int left, int top, int right, int bottom) {}
  public void updateCursorAnchorInfo(View v, CursorAnchorInfo info) {}
  public void updateExtractedText(View v, int token, ExtractedText text) {}
  /** the browser's keyboard shows no completions */
  public void displayCompletions(View v, CompletionInfo[] completions) {}
  public boolean isWatchingCursor(View v) { return false; }
  public void sendAppPrivateCommand(View v, String action, Bundle data) {}
  @Deprecated public void viewClicked(View v) {}
  /** a key as if the input method sent it: through the view's window */
  public void dispatchKeyEventFromInputMethod(View targetView, KeyEvent event) {
    if (targetView == null) return;
    a2w.ViewRoot r = a2w.Windows.rootOf(targetView.getRootView());
    if (r != null) r.processKey(event); else targetView.dispatchKeyEvent(event);
  }
  // ---- input methods and subtypes: the page's keyboard only
  public java.util.List<InputMethodInfo> getInputMethodList() { return java.util.Collections.singletonList(KEYBOARD); }
  public java.util.List<InputMethodInfo> getEnabledInputMethodList() { return java.util.Collections.singletonList(KEYBOARD); }
  public InputMethodInfo getCurrentInputMethodInfo() { return KEYBOARD; }
  public java.util.List<InputMethodSubtype> getEnabledInputMethodSubtypeList(InputMethodInfo imi, boolean allowsImplicitlyEnabledSubtypes) { return new java.util.ArrayList<>(); }
  public InputMethodSubtype getCurrentInputMethodSubtype() { return null; }
  public InputMethodSubtype getLastInputMethodSubtype() { return null; }
  public java.util.Map<InputMethodInfo, java.util.List<InputMethodSubtype>> getShortcutInputMethodsAndSubtypes() { return new java.util.HashMap<>(); }
  @Deprecated public boolean setCurrentInputMethodSubtype(InputMethodSubtype subtype) { return false; }
  @Deprecated public void setAdditionalInputMethodSubtypes(String imiId, InputMethodSubtype[] subtypes) {}
  public void setExplicitlyEnabledInputMethodSubtypes(String imiId, int[] subtypeHashCodes) {}
  public void showInputMethodPicker() {}
  public void showInputMethodAndSubtypeEnabler(String imiId) {}
  @Deprecated public void setInputMethod(IBinder token, String id) {}
  @Deprecated public void setInputMethodAndSubtype(IBinder token, String id, InputMethodSubtype subtype) {}
  @Deprecated public boolean switchToLastInputMethod(IBinder imeToken) { return false; }
  @Deprecated public boolean switchToNextInputMethod(IBinder imeToken, boolean onlyCurrentIme) { return false; }
  @Deprecated public boolean shouldOfferSwitchingToNextInputMethod(IBinder imeToken) { return false; }
  @Deprecated public void showStatusIcon(IBinder imeToken, String packageName, int iconId) {}
  @Deprecated public void hideStatusIcon(IBinder imeToken) {}
  public boolean isInputMethodSuppressingSpellChecker() { return false; }
  // ---- stylus handwriting: not available
  public boolean isStylusHandwritingAvailable() { return false; }
  public boolean isConnectionlessStylusHandwritingAvailable() { return false; }
  public void startStylusHandwriting(View view) {}
  public void prepareStylusHandwritingDelegation(View delegatorView) {}
  public void prepareStylusHandwritingDelegation(View delegatorView, String delegatePackageName) {}
  public boolean acceptStylusHandwritingDelegation(View delegateView) { return false; }
  public boolean acceptStylusHandwritingDelegation(View delegateView, String delegatorPackageName) { return false; }
  public void acceptStylusHandwritingDelegation(View delegateView, String delegatorPackageName, java.util.concurrent.Executor executor, java.util.function.Consumer<Boolean> callback) {
    executor.execute(() -> callback.accept(false));
  }
  public void acceptStylusHandwritingDelegation(View delegateView, String delegatorPackageName, int flags, java.util.concurrent.Executor executor, java.util.function.Consumer<Boolean> callback) {
    executor.execute(() -> callback.accept(false));
  }
  public void startConnectionlessStylusHandwriting(View view, CursorAnchorInfo cursorAnchorInfo, java.util.concurrent.Executor executor, ConnectionlessHandwritingCallback callback) {
    executor.execute(() -> callback.onError(ConnectionlessHandwritingCallback.CONNECTIONLESS_HANDWRITING_ERROR_UNSUPPORTED));
  }
  public void startConnectionlessStylusHandwritingForDelegation(View view, CursorAnchorInfo cursorAnchorInfo, java.util.concurrent.Executor executor, ConnectionlessHandwritingCallback callback) {
    startConnectionlessStylusHandwriting(view, cursorAnchorInfo, executor, callback);
  }
  public void startConnectionlessStylusHandwritingForDelegation(View view, CursorAnchorInfo cursorAnchorInfo, String delegatorPackageName, java.util.concurrent.Executor executor, ConnectionlessHandwritingCallback callback) {
    startConnectionlessStylusHandwriting(view, cursorAnchorInfo, executor, callback);
  }
}

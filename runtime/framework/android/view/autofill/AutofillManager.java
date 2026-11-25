// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.view.autofill;

public final class AutofillManager {
  public static final java.lang.String EXTRA_ASSIST_STRUCTURE = "android.view.autofill.extra.ASSIST_STRUCTURE";
  public static final java.lang.String EXTRA_AUTHENTICATION_RESULT = "android.view.autofill.extra.AUTHENTICATION_RESULT";
  public static final java.lang.String EXTRA_AUTHENTICATION_RESULT_EPHEMERAL_DATASET = "android.view.autofill.extra.AUTHENTICATION_RESULT_EPHEMERAL_DATASET";
  public static final java.lang.String EXTRA_CLIENT_STATE = "android.view.autofill.extra.CLIENT_STATE";
  public static final java.lang.String EXTRA_INLINE_SUGGESTIONS_REQUEST = "android.view.autofill.extra.INLINE_SUGGESTIONS_REQUEST";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public AutofillManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void cancel() { a2w.Stubs.hit("android.view.autofill.AutofillManager.cancel"); }
  @a2w.NotImplemented public void commit() { a2w.Stubs.hit("android.view.autofill.AutofillManager.commit"); }
  @a2w.NotImplemented public void disableAutofillServices() { a2w.Stubs.hit("android.view.autofill.AutofillManager.disableAutofillServices"); }
  @a2w.NotImplemented public android.content.ComponentName getAutofillServiceComponentName() { a2w.Stubs.hit("android.view.autofill.AutofillManager.getAutofillServiceComponentName"); return null; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getAvailableFieldClassificationAlgorithms() { a2w.Stubs.hit("android.view.autofill.AutofillManager.getAvailableFieldClassificationAlgorithms"); return null; }
  @a2w.NotImplemented public java.lang.String getDefaultFieldClassificationAlgorithm() { a2w.Stubs.hit("android.view.autofill.AutofillManager.getDefaultFieldClassificationAlgorithm"); return null; }
  @a2w.NotImplemented public android.view.autofill.AutofillId getNextAutofillId() { a2w.Stubs.hit("android.view.autofill.AutofillManager.getNextAutofillId"); return null; }
  @a2w.NotImplemented public android.service.autofill.UserData getUserData() { a2w.Stubs.hit("android.view.autofill.AutofillManager.getUserData"); return null; }
  @a2w.NotImplemented public java.lang.String getUserDataId() { a2w.Stubs.hit("android.view.autofill.AutofillManager.getUserDataId"); return null; }
  @a2w.NotImplemented public boolean hasEnabledAutofillServices() { a2w.Stubs.hit("android.view.autofill.AutofillManager.hasEnabledAutofillServices"); return false; }
  @a2w.NotImplemented public boolean isAutofillSupported() { a2w.Stubs.hit("android.view.autofill.AutofillManager.isAutofillSupported"); return false; }
  @a2w.NotImplemented public boolean isEnabled() { a2w.Stubs.hit("android.view.autofill.AutofillManager.isEnabled"); return false; }
  @a2w.NotImplemented public boolean isFieldClassificationEnabled() { a2w.Stubs.hit("android.view.autofill.AutofillManager.isFieldClassificationEnabled"); return false; }
  @a2w.NotImplemented public void notifyValueChanged(android.view.View view) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyValueChanged"); }
  @a2w.NotImplemented public void notifyValueChanged(android.view.View view, int virtualId, android.view.autofill.AutofillValue value) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyValueChanged"); }
  @a2w.NotImplemented public void notifyViewClicked(android.view.View view) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyViewClicked"); }
  @a2w.NotImplemented public void notifyViewClicked(android.view.View view, int virtualId) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyViewClicked"); }
  @a2w.NotImplemented public void notifyViewEntered(android.view.View view) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyViewEntered"); }
  @a2w.NotImplemented public void notifyViewEntered(android.view.View view, int virtualId, android.graphics.Rect absBounds) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyViewEntered"); }
  @a2w.NotImplemented public void notifyViewExited(android.view.View view) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyViewExited"); }
  @a2w.NotImplemented public void notifyViewExited(android.view.View view, int virtualId) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyViewExited"); }
  @a2w.NotImplemented public void notifyViewVisibilityChanged(android.view.View view, int virtualId, boolean isVisible) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyViewVisibilityChanged"); }
  @a2w.NotImplemented public void notifyViewVisibilityChanged(android.view.View view, boolean isVisible) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyViewVisibilityChanged"); }
  @a2w.NotImplemented public void notifyVirtualViewsReady(android.view.View view, android.util.SparseArray<android.view.autofill.VirtualViewFillInfo> infos) { a2w.Stubs.hit("android.view.autofill.AutofillManager.notifyVirtualViewsReady"); }
  @a2w.NotImplemented public void registerCallback(android.view.autofill.AutofillManager.AutofillCallback callback) { a2w.Stubs.hit("android.view.autofill.AutofillManager.registerCallback"); }
  @a2w.NotImplemented public void requestAutofill(android.view.View view) { a2w.Stubs.hit("android.view.autofill.AutofillManager.requestAutofill"); }
  @a2w.NotImplemented public void requestAutofill(android.view.View view, int virtualId, android.graphics.Rect absBounds) { a2w.Stubs.hit("android.view.autofill.AutofillManager.requestAutofill"); }
  @a2w.NotImplemented public void setUserData(android.service.autofill.UserData userData) { a2w.Stubs.hit("android.view.autofill.AutofillManager.setUserData"); }
  @a2w.NotImplemented public boolean showAutofillDialog(android.view.View view) { a2w.Stubs.hit("android.view.autofill.AutofillManager.showAutofillDialog"); return false; }
  @a2w.NotImplemented public boolean showAutofillDialog(android.view.View view, int virtualId) { a2w.Stubs.hit("android.view.autofill.AutofillManager.showAutofillDialog"); return false; }
  @a2w.NotImplemented public void unregisterCallback(android.view.autofill.AutofillManager.AutofillCallback callback) { a2w.Stubs.hit("android.view.autofill.AutofillManager.unregisterCallback"); }

    public static abstract class AutofillCallback {
    public static final int EVENT_INPUT_HIDDEN = 2;
    public static final int EVENT_INPUT_SHOWN = 1;
    public static final int EVENT_INPUT_UNAVAILABLE = 3;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public AutofillCallback(a2w.Hidden hidden) { }
    public AutofillCallback() { this((a2w.Hidden) null); }
    public void onAutofillEvent(android.view.View view, int event) { }
    public void onAutofillEvent(android.view.View view, int virtualId, int event) { }
  }
}

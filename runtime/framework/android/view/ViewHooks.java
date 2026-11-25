package android.view;

/** Package-private View internals the web window system needs. */
public final class ViewHooks {
  private ViewHooks() {}
  public static void setParent(View v, ViewParent p) { v.mParent = p; }
  public static void attach(View v, View.AttachInfo info) { v.dispatchAttachedToWindow(info, View.VISIBLE); }
  public static void detach(View v) { v.dispatchDetachedFromWindow(); }
  public static View.AttachInfo attachInfo(View v) { return v.mAttachInfo; }
  public static void touchModeChanged(View.AttachInfo ai, boolean inTouchMode) { ai.treeObserver.dispatchOnTouchModeChanged(inTouchMode); }
}

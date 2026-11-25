package android.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.os.Bundle;

public abstract class Window {
  public static final int FEATURE_OPTIONS_PANEL = 0, FEATURE_NO_TITLE = 1, FEATURE_PROGRESS = 2, FEATURE_LEFT_ICON = 3, FEATURE_RIGHT_ICON = 4,
      FEATURE_INDETERMINATE_PROGRESS = 5, FEATURE_CONTEXT_MENU = 6, FEATURE_CUSTOM_TITLE = 7, FEATURE_ACTION_BAR = 8, ID_ANDROID_CONTENT = 0x01020002,
      FEATURE_ACTION_BAR_OVERLAY = 9, FEATURE_ACTION_MODE_OVERLAY = 10, FEATURE_SWIPE_TO_DISMISS = 11, FEATURE_CONTENT_TRANSITIONS = 12,
      FEATURE_ACTIVITY_TRANSITIONS = 13, FEATURE_MAX = 13;
  public static final int PROGRESS_VISIBILITY_ON = -1, PROGRESS_VISIBILITY_OFF = -2, PROGRESS_START = 0, PROGRESS_END = 10000;
  public interface Callback {
    boolean dispatchKeyEvent(KeyEvent event);
    boolean dispatchTouchEvent(MotionEvent event);
    boolean dispatchTrackballEvent(MotionEvent event);
    View onCreatePanelView(int featureId);
    boolean onCreatePanelMenu(int featureId, Menu menu);
    boolean onPreparePanel(int featureId, View view, Menu menu);
    boolean onMenuOpened(int featureId, Menu menu);
    boolean onMenuItemSelected(int featureId, MenuItem item);
    void onWindowAttributesChanged(WindowManager.LayoutParams attrs);
    void onContentChanged();
    void onWindowFocusChanged(boolean hasFocus);
    void onAttachedToWindow();
    void onDetachedFromWindow();
    void onPanelClosed(int featureId, Menu menu);
    boolean onSearchRequested();
    default boolean dispatchGenericMotionEvent(MotionEvent event) { return false; }
    default boolean dispatchKeyShortcutEvent(KeyEvent event) { return false; }
    default boolean dispatchPopulateAccessibilityEvent(android.view.accessibility.AccessibilityEvent event) { return false; }
    default void onActionModeStarted(ActionMode mode) {}
    default void onActionModeFinished(ActionMode mode) {}
    default void onPointerCaptureChanged(boolean hasCapture) {}
    default void onProvideKeyboardShortcuts(java.util.List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {}
    default boolean onSearchRequested(SearchEvent searchEvent) { return onSearchRequested(); }
    default ActionMode onWindowStartingActionMode(ActionMode.Callback callback) { return null; }
    default ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) { return null; }
  }
  final Context mContext;
  Callback mCallback;
  final WindowManager.LayoutParams mAttrs = new WindowManager.LayoutParams();
  public Window(Context c) { mContext = c; }
  public final Context getContext() { return mContext; }
  public void setCallback(Callback c) { mCallback = c; }
  public final Callback getCallback() { return mCallback; }
  public void setWindowManager(WindowManager wm, android.os.IBinder token, String appName) {}
  public WindowManager getWindowManager() { return a2w.Windows.manager(); }
  public final WindowManager.LayoutParams getAttributes() { return mAttrs; }
  public void setAttributes(WindowManager.LayoutParams a) { mAttrs.copyFrom(a); dispatchAttributesChanged(); }
  protected void dispatchAttributesChanged() { if (mCallback != null) mCallback.onWindowAttributesChanged(mAttrs); }
  public void setFlags(int flags, int mask) { mAttrs.flags = (mAttrs.flags & ~mask) | (flags & mask); dispatchAttributesChanged(); }
  public void addFlags(int f) { setFlags(f, f); }
  public void clearFlags(int f) { setFlags(0, f); }
  public void setLayout(int w, int h) { mAttrs.width = w; mAttrs.height = h; dispatchAttributesChanged(); }
  public void setGravity(int g) { mAttrs.gravity = g; dispatchAttributesChanged(); }
  public void setType(int t) { mAttrs.type = t; }
  public void setFormat(int f) { mAttrs.format = f; }
  public void setWindowAnimations(int a) { mAttrs.windowAnimations = a; }
  public void setSoftInputMode(int m) { mAttrs.softInputMode = m; }
  public void setDimAmount(float a) { mAttrs.dimAmount = a; dispatchAttributesChanged(); }
  public void setTitle(CharSequence t) { mAttrs.setTitle(t); }
  int mTitleColor;
  public void setTitleColor(int c) { mTitleColor = c; }
  /** (the title color set; Activity.getTitleColor) */
  public int webTitleColor() { return mTitleColor; }
  /** the window features (requestFeature, and the theme's windowNoTitle/windowActionBar) */
  protected int mFeatures = 1 << FEATURE_NO_TITLE;
  /** the features the app asked for itself (they win over the theme's) */
  protected int mRequestedFeatures;
  public boolean requestFeature(int f) {
    mRequestedFeatures |= 1 << f;
    if (f == FEATURE_NO_TITLE) mFeatures &= ~(1 << FEATURE_ACTION_BAR);
    if (f == FEATURE_ACTION_BAR) mFeatures &= ~(1 << FEATURE_NO_TITLE);
    mFeatures |= 1 << f;
    return true;
  }
  protected final int getFeatures() { return mFeatures; }
  protected final int getLocalFeatures() { return mFeatures; }
  public static int getDefaultFeatures(android.content.Context c) { return 0; }
  public void setFeatureInt(int f, int v) {}
  public void setFeatureDrawableResource(int f, int r) {}
  int mVolumeControlStream = android.media.AudioManager.USE_DEFAULT_STREAM_TYPE;
  public void setVolumeControlStream(int s) { mVolumeControlStream = s; }
  public int getVolumeControlStream() { return mVolumeControlStream; }
  public void takeKeyEvents(boolean get) {}
  public void makeActive() {}
  public boolean isActive() { return true; }
  public abstract boolean isFloating();
  public abstract void setContentView(int layoutResID);
  public abstract void setContentView(View view);
  public abstract void setContentView(View view, ViewGroup.LayoutParams params);
  public abstract void addContentView(View view, ViewGroup.LayoutParams params);
  public abstract View getCurrentFocus();
  public abstract LayoutInflater getLayoutInflater();
  public abstract View getDecorView();
  public abstract View peekDecorView();
  public abstract void setBackgroundDrawable(Drawable d);
  public void setBackgroundDrawableResource(int id) { setBackgroundDrawable(mContext.getResources().getDrawable(id)); }
  public abstract boolean superDispatchKeyEvent(KeyEvent event);
  public abstract boolean superDispatchTouchEvent(MotionEvent event);
  public boolean superDispatchTrackballEvent(MotionEvent e) { return false; }
  public View findViewById(int id) { return getDecorView().findViewById(id); }
  public void closeAllPanels() {}
  public void openPanel(int f, KeyEvent e) {}
  public void closePanel(int f) {}
  public boolean hasFeature(int f) { return (mFeatures & (1 << f)) != 0; }
  int mUiOptions;
  public void setUiOptions(int o) { mUiOptions = o; }
  public void setUiOptions(int uiOptions, int mask) { mUiOptions = (mUiOptions & ~mask) | (uiOptions & mask); }

  // ---- containers (one level: a window inside an ActivityGroup's)
  Window mContainer; boolean mHasChildren;
  public void setContainer(Window container) { mContainer = container; if (container != null) container.mHasChildren = true; }
  public final Window getContainer() { return mContainer; }
  public final boolean hasChildren() { return mHasChildren; }
  public void setWindowManager(WindowManager wm, android.os.IBinder appToken, String appName, boolean hardwareAccelerated) { setWindowManager(wm, appToken, appName); }
  protected final int getForcedWindowFlags() { return 0; }
  protected final boolean hasSoftInputMode() { return mAttrs.softInputMode != 0; }
  public final TypedArray getWindowStyle() { return mContext.obtainStyledAttributes(new int[0]); }
  protected void setDefaultWindowFormat(int format) { if (mAttrs.format == 0) mAttrs.format = format; }
  public void setLocalFocus(boolean hasFocus, boolean inTouchMode) {}
  public void injectInputEvent(InputEvent event) {
    if (event instanceof KeyEvent) superDispatchKeyEvent((KeyEvent) event);
    else if (event instanceof MotionEvent) superDispatchTouchEvent((MotionEvent) event);
  }
  public boolean superDispatchKeyShortcutEvent(KeyEvent event) { return false; }
  public boolean superDispatchGenericMotionEvent(MotionEvent event) { return false; }
  protected void onActive() {}
  public void onConfigurationChanged(android.content.res.Configuration newConfig) {}
  @SuppressWarnings("unchecked")
  public final <T extends View> T requireViewById(int id) {
    T view = (T) findViewById(id);
    if (view == null) throw new IllegalArgumentException("ID does not reference a View inside this Window");
    return view;
  }
  // ---- the view hierarchy's saved state
  public Bundle saveHierarchyState() {
    Bundle b = new Bundle();
    View d = peekDecorView();
    if (d == null) return b;
    android.util.SparseArray<android.os.Parcelable> states = new android.util.SparseArray<>();
    d.saveHierarchyState(states);
    b.putSparseParcelableArray("android:views", states);
    View f = d.findFocus();
    if (f != null && f.getId() != View.NO_ID) b.putInt("android:focusedViewId", f.getId());
    return b;
  }
  public void restoreHierarchyState(Bundle savedInstanceState) {
    View d = peekDecorView();
    if (d == null || savedInstanceState == null) return;
    android.util.SparseArray<android.os.Parcelable> states = savedInstanceState.getSparseParcelableArray("android:views");
    if (states != null) d.restoreHierarchyState(states);
    int focused = savedInstanceState.getInt("android:focusedViewId", View.NO_ID);
    if (focused != View.NO_ID) { View f = d.findViewById(focused); if (f != null) f.requestFocus(); }
  }
  // ---- panels and shortcuts
  public void togglePanel(int featureId, KeyEvent event) { openPanel(featureId, event); }
  public void invalidatePanelMenu(int featureId) { if (mCallback instanceof android.app.Activity) ((android.app.Activity) mCallback).invalidateOptionsMenu(); }
  public boolean performPanelShortcut(int featureId, int keyCode, KeyEvent event, int flags) { return false; }
  public boolean performPanelIdentifierAction(int featureId, int id, int flags) {
    if (mCallback instanceof android.app.Activity) {
      Menu m = ((android.app.Activity) mCallback).webOptionsMenu();
      MenuItem item = m == null ? null : m.findItem(id);
      return item != null && m.performIdentifierAction(id, flags);
    }
    return false;
  }
  public boolean performContextMenuIdentifierAction(int id, int flags) { return false; }
  public boolean isShortcutKey(int keyCode, KeyEvent event) {
    if (mCallback instanceof android.app.Activity) { Menu m = ((android.app.Activity) mCallback).webOptionsMenu(); return m != null && m.isShortcutKey(keyCode, event); }
    return false;
  }
  public void takeSurface(SurfaceHolder$Callback2 callback) {}
  public void takeInputQueue(InputQueue.Callback callback) {}
  // ---- feature drawables (the left and right icons of the title)
  final android.util.SparseArray<Drawable> mFeatureDrawables = new android.util.SparseArray<>();
  public void setFeatureDrawable(int featureId, Drawable drawable) { mFeatureDrawables.put(featureId, drawable); }
  public void setFeatureDrawableUri(int featureId, android.net.Uri uri) {
    Drawable d = null;
    try (java.io.InputStream in = mContext.getContentResolver().openInputStream(uri)) { d = Drawable.createFromStream(in, uri.toString()); } catch (Exception e) { /* none */ }
    setFeatureDrawable(featureId, d);
  }
  public void setFeatureDrawableAlpha(int featureId, int alpha) { Drawable d = mFeatureDrawables.get(featureId); if (d != null) d.setAlpha(alpha); }
  public void setChildDrawable(int featureId, Drawable drawable) { setFeatureDrawable(featureId, drawable); }
  public void setChildInt(int featureId, int value) { setFeatureInt(featureId, value); }
  public void setIcon(int resId) { if (mCallback instanceof android.app.Activity && ((android.app.Activity) mCallback).getActionBar() != null) ((android.app.Activity) mCallback).getActionBar().setIcon(resId); }
  public void setLogo(int resId) { if (mCallback instanceof android.app.Activity && ((android.app.Activity) mCallback).getActionBar() != null) ((android.app.Activity) mCallback).getActionBar().setLogo(resId); }
  // ---- system bars: the status bar color becomes the page's theme color
  int mStatusBarColor = 0xff000000, mNavigationBarColor = 0xff000000, mNavigationBarDividerColor;
  boolean mStatusBarContrastEnforced, mNavigationBarContrastEnforced = true, mDecorFitsSystemWindows = true;
  public void setStatusBarColor(int color) { mStatusBarColor = color; a2w.Host.setThemeColor(color); }
  public int getStatusBarColor() { return mStatusBarColor; }
  public void setNavigationBarColor(int color) { mNavigationBarColor = color; }
  public int getNavigationBarColor() { return mNavigationBarColor; }
  public void setNavigationBarDividerColor(int dividerColor) { mNavigationBarDividerColor = dividerColor; }
  public int getNavigationBarDividerColor() { return mNavigationBarDividerColor; }
  public void setStatusBarContrastEnforced(boolean ensureContrast) { mStatusBarContrastEnforced = ensureContrast; }
  public boolean isStatusBarContrastEnforced() { return mStatusBarContrastEnforced; }
  public void setNavigationBarContrastEnforced(boolean enforceContrast) { mNavigationBarContrastEnforced = enforceContrast; }
  public boolean isNavigationBarContrastEnforced() { return mNavigationBarContrastEnforced; }
  public void setDecorFitsSystemWindows(boolean decorFitsSystemWindows) { mDecorFitsSystemWindows = decorFitsSystemWindows; }
  public WindowInsetsController getInsetsController() { return null; }
  public void setSystemGestureExclusionRects(java.util.List<android.graphics.Rect> rects) { mExclusionRects = new java.util.ArrayList<>(rects); }
  public java.util.List<android.graphics.Rect> getSystemGestureExclusionRects() { return mExclusionRects; }
  java.util.List<android.graphics.Rect> mExclusionRects = java.util.Collections.emptyList();
  public void setDecorCaptionShade(int decorCaptionShade) {}
  public void setResizingCaptionDrawable(Drawable drawable) {}
  public void setRestrictedCaptionAreaListener(OnRestrictedCaptionAreaChangedListener listener) {}
  public interface OnRestrictedCaptionAreaChangedListener { void onRestrictedCaptionAreaChanged(android.graphics.Rect rect); }
  // ---- surface and color: an sRGB 2D page
  int mColorMode; float mDesiredHdrHeadroom;
  public void setColorMode(int colorMode) { mColorMode = colorMode; }
  public int getColorMode() { return mColorMode; }
  public boolean isWideColorGamut() { return false; }
  public void setDesiredHdrHeadroom(float desiredHeadroom) {
    if (!Float.isFinite(desiredHeadroom) || desiredHeadroom != 0 && (desiredHeadroom < 1 || desiredHeadroom > 10000)) throw new IllegalArgumentException("desiredHeadroom must be 0 or within [1, 10000]");
    mDesiredHdrHeadroom = desiredHeadroom;
  }
  public float getDesiredHdrHeadroom() { return mDesiredHdrHeadroom; }
  float mElevation; boolean mClipToOutline;
  public void setElevation(float elevation) { mElevation = elevation; }
  public void setClipToOutline(boolean clipToOutline) { mClipToOutline = clipToOutline; }
  public void setBackgroundBlurRadius(int blurRadius) {}
  public void setPreferMinimalPostProcessing(boolean isPreferred) {}
  public void setSustainedPerformanceMode(boolean enable) {}
  public void setHideOverlayWindows(boolean hide) {}
  boolean mFrameRateBoost = true, mFrameRatePowerSavings = true;
  public void setFrameRateBoostOnTouchEnabled(boolean enabled) { mFrameRateBoost = enabled; }
  public boolean getFrameRateBoostOnTouchEnabled() { return mFrameRateBoost; }
  public void setFrameRatePowerSavingsBalanced(boolean enabled) { mFrameRatePowerSavings = enabled; }
  public boolean isFrameRatePowerSavingsBalanced() { return mFrameRatePowerSavings; }
  public AttachedSurfaceControl getRootSurfaceControl() { return null; }
  public void registerScrollCaptureCallback(ScrollCaptureCallback callback) {}
  public void unregisterScrollCaptureCallback(ScrollCaptureCallback callback) {}
  public interface OnFrameMetricsAvailableListener { void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int dropCountSinceLastInvocation); }
  public final void addOnFrameMetricsAvailableListener(OnFrameMetricsAvailableListener listener, android.os.Handler handler) { if (listener == null) throw new NullPointerException("listener cannot be null"); }
  public final void removeOnFrameMetricsAvailableListener(OnFrameMetricsAvailableListener listener) {}
  public android.window.OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
    if (mCallback instanceof android.app.Activity) return ((android.app.Activity) mCallback).getOnBackInvokedDispatcher();
    if (mBackDispatcher == null) mBackDispatcher = new a2w.BackDispatcher();
    return mBackDispatcher;
  }
  a2w.BackDispatcher mBackDispatcher;
  android.media.session.MediaController mMediaController;
  public void setMediaController(android.media.session.MediaController controller) { mMediaController = controller; }
  public android.media.session.MediaController getMediaController() { return mMediaController; }
  // ---- transitions (kept; activities change without animation)
  android.transition.Transition mEnter, mExit, mReenter, mReturn, mSharedEnter, mSharedExit, mSharedReenter, mSharedReturn;
  boolean mAllowEnterOverlap = true, mAllowReturnOverlap = true, mSharedElementsUseOverlay = true;
  long mTransitionFadeDuration = 300;
  android.transition.TransitionManager mTransitionManager;
  public void setEnterTransition(android.transition.Transition t) { mEnter = t; }
  public android.transition.Transition getEnterTransition() { return mEnter; }
  public void setExitTransition(android.transition.Transition t) { mExit = t; }
  public android.transition.Transition getExitTransition() { return mExit; }
  public void setReenterTransition(android.transition.Transition t) { mReenter = t; }
  public android.transition.Transition getReenterTransition() { return mReenter; }
  public void setReturnTransition(android.transition.Transition t) { mReturn = t; }
  public android.transition.Transition getReturnTransition() { return mReturn; }
  public void setSharedElementEnterTransition(android.transition.Transition t) { mSharedEnter = t; }
  public android.transition.Transition getSharedElementEnterTransition() { return mSharedEnter; }
  public void setSharedElementExitTransition(android.transition.Transition t) { mSharedExit = t; }
  public android.transition.Transition getSharedElementExitTransition() { return mSharedExit; }
  public void setSharedElementReenterTransition(android.transition.Transition t) { mSharedReenter = t; }
  public android.transition.Transition getSharedElementReenterTransition() { return mSharedReenter; }
  public void setSharedElementReturnTransition(android.transition.Transition t) { mSharedReturn = t; }
  public android.transition.Transition getSharedElementReturnTransition() { return mSharedReturn; }
  public void setAllowEnterTransitionOverlap(boolean allow) { mAllowEnterOverlap = allow; }
  public boolean getAllowEnterTransitionOverlap() { return mAllowEnterOverlap; }
  public void setAllowReturnTransitionOverlap(boolean allow) { mAllowReturnOverlap = allow; }
  public boolean getAllowReturnTransitionOverlap() { return mAllowReturnOverlap; }
  public void setSharedElementsUseOverlay(boolean sharedElementsUseOverlay) { mSharedElementsUseOverlay = sharedElementsUseOverlay; }
  public boolean getSharedElementsUseOverlay() { return mSharedElementsUseOverlay; }
  public void setTransitionBackgroundFadeDuration(long fadeDurationMillis) { if (fadeDurationMillis < 0) throw new IllegalArgumentException("negative durations are not allowed"); mTransitionFadeDuration = fadeDurationMillis; }
  public long getTransitionBackgroundFadeDuration() { return mTransitionFadeDuration; }
  public void setTransitionManager(android.transition.TransitionManager tm) { mTransitionManager = tm; }
  public android.transition.TransitionManager getTransitionManager() { return mTransitionManager; }
  public android.transition.Scene getContentScene() { return null; }
}

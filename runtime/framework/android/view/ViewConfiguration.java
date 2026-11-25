package android.view;

import android.content.Context;

public class ViewConfiguration {
  static final float D = a2w.Screen.DENSITY;
  private static final ViewConfiguration I = new ViewConfiguration();
  public static ViewConfiguration get(Context c) { return I; }
  public static int getScrollBarSize() { return 10; }
  public static int getScrollBarFadeDuration() { return 250; }
  public static int getScrollDefaultDelay() { return 300; }
  public static int getFadingEdgeLength() { return 12; }
  public static int getPressedStateDuration() { return 64; }
  public static int getLongPressTimeout() { return 500; }
  public static int getKeyRepeatTimeout() { return 500; }
  public static int getKeyRepeatDelay() { return 50; }
  public static int getTapTimeout() { return 180; }
  public static int getJumpTapTimeout() { return 500; }
  public static int getDoubleTapTimeout() { return 300; }
  public static int getEdgeSlop() { return 12; }
  public static int getTouchSlop() { return 8; }
  public static int getWindowTouchSlop() { return 16; }
  public static int getMinimumFlingVelocity() { return 50; }
  public static int getMaximumFlingVelocity() { return 8000; }
  public static int getMaximumDrawingCacheSize() { return a2w.Screen.W * a2w.Screen.H * 4; }
  public static long getZoomControlsTimeout() { return 3000; }
  public static long getGlobalActionKeyTimeout() { return 500; }
  public static float getScrollFriction() { return 0.015f; }
  public int getScaledScrollBarSize() { return (int) (4 * D + 0.5f); }
  public int getScaledFadingEdgeLength() { return (int) (12 * D + 0.5f); }
  public int getScaledEdgeSlop() { return (int) (12 * D + 0.5f); }
  public int getScaledTouchSlop() { return (int) (8 * D + 0.5f); }
  public int getScaledPagingTouchSlop() { return (int) (16 * D + 0.5f); }
  public int getScaledDoubleTapSlop() { return (int) (100 * D + 0.5f); }
  public int getScaledWindowTouchSlop() { return (int) (16 * D + 0.5f); }
  public int getScaledMinimumFlingVelocity() { return (int) (50 * D + 0.5f); }
  public int getScaledMaximumFlingVelocity() { return (int) (8000 * D + 0.5f); }
  public int getScaledMaximumDrawingCacheSize() { return a2w.Screen.W * a2w.Screen.H * 4; }
  public int getScaledOverscrollDistance() { return 0; }
  public int getScaledOverflingDistance() { return (int) (6 * D + 0.5f); }
  // instance fields despite their names (as on Android, where apps flip them by reflection to force the overflow menu)
  private boolean sHasPermanentMenuKey = true;
  private boolean sHasPermanentMenuKeySet = true;
  public boolean hasPermanentMenuKey() { return sHasPermanentMenuKey; }
  /** scroll per unit of AXIS_VSCROLL / AXIS_HSCROLL (a wheel notch): 64dp, Android's config_verticalScrollFactor */
  public float getScaledVerticalScrollFactor() { return 64 * D; }
  public float getScaledHorizontalScrollFactor() { return 64 * D; }
  /** (hidden API, used by ViewConfigurationCompat) */
  public int getScaledScrollFactor() { return (int) (64 * D + 0.5f); }
  public int getScaledHoverSlop() { return (int) (4 * D + 0.5f); }
  public int getScaledMinimumScalingSpan() { return (int) (16 * D + 0.5f) * 2; }
  public float getScaledAmbiguousGestureMultiplier() { return 2f; }
  @Deprecated public static float getAmbiguousGestureMultiplier() { return 2f; }
  public static long getDefaultActionModeHideDuration() { return 2000; }
  public static int getMultiPressTimeout() { return 300; }
  public int getScaledHandwritingSlop() { return (int) (2 * D + 0.5f); }
  public int getScaledHandwritingGestureLineMargin() { return (int) (16 * D + 0.5f); }
  public int getScaledMinimumFlingVelocity(int inputDeviceId, int axis, int source) { return getScaledMinimumFlingVelocity(); }
  public int getScaledMaximumFlingVelocity(int inputDeviceId, int axis, int source) { return getScaledMaximumFlingVelocity(); }
  public boolean shouldShowMenuShortcutsWhenKeyboardPresent() { return true; }
}

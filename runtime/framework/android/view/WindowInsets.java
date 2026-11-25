package android.view;

import android.graphics.Insets;
import android.graphics.Rect;

/**
 * The insets of a window: the page has no system bars, display cutout or on-screen keyboard over the content, so
 * a window's insets are zero unless an app replaces them.
 */
public final class WindowInsets {
  public static final WindowInsets CONSUMED = new WindowInsets(Insets.NONE, Insets.NONE, true);
  private final Insets mSystem, mStable;
  private final boolean mConsumed;
  WindowInsets(Insets system, Insets stable, boolean consumed) { mSystem = system; mStable = stable; mConsumed = consumed; }
  public WindowInsets(WindowInsets src) { this(src.mSystem, src.mStable, src.mConsumed); }
  /** web: the insets windows get */
  public static WindowInsets zero() { return new WindowInsets(Insets.NONE, Insets.NONE, false); }

  public int getSystemWindowInsetLeft() { return mSystem.left; }
  public int getSystemWindowInsetTop() { return mSystem.top; }
  public int getSystemWindowInsetRight() { return mSystem.right; }
  public int getSystemWindowInsetBottom() { return mSystem.bottom; }
  public Insets getSystemWindowInsets() { return mSystem; }
  public int getStableInsetLeft() { return mStable.left; }
  public int getStableInsetTop() { return mStable.top; }
  public int getStableInsetRight() { return mStable.right; }
  public int getStableInsetBottom() { return mStable.bottom; }
  public Insets getStableInsets() { return mStable; }
  public Insets getSystemGestureInsets() { return Insets.NONE; }
  public Insets getMandatorySystemGestureInsets() { return Insets.NONE; }
  public Insets getTappableElementInsets() { return Insets.NONE; }
  public Insets getInsets(int typeMask) { return (typeMask & (Type.STATUS_BARS | Type.NAVIGATION_BARS | Type.CAPTION_BAR)) != 0 ? mSystem : Insets.NONE; }
  public Insets getInsetsIgnoringVisibility(int typeMask) { return getInsets(typeMask); }
  public boolean isVisible(int typeMask) { return false; }
  public boolean hasSystemWindowInsets() { return !mSystem.equals(Insets.NONE); }
  public boolean hasStableInsets() { return !mStable.equals(Insets.NONE); }
  public boolean hasInsets() { return hasSystemWindowInsets(); }
  public boolean isConsumed() { return mConsumed; }
  public boolean isRound() { return false; }
  public DisplayCutout getDisplayCutout() { return null; }
  public WindowInsets consumeSystemWindowInsets() { return new WindowInsets(Insets.NONE, mStable, true); }
  public WindowInsets consumeStableInsets() { return new WindowInsets(mSystem, Insets.NONE, mConsumed); }
  public WindowInsets consumeDisplayCutout() { return this; }
  public WindowInsets replaceSystemWindowInsets(int l, int t, int r, int b) { return new WindowInsets(Insets.of(l, t, r, b), mStable, false); }
  public WindowInsets replaceSystemWindowInsets(Rect r) { return replaceSystemWindowInsets(r.left, r.top, r.right, r.bottom); }
  public WindowInsets inset(int l, int t, int r, int b) {
    return new WindowInsets(Insets.of(Math.max(0, mSystem.left - l), Math.max(0, mSystem.top - t), Math.max(0, mSystem.right - r), Math.max(0, mSystem.bottom - b)), mStable, mConsumed);
  }
  public WindowInsets inset(Insets i) { return inset(i.left, i.top, i.right, i.bottom); }
  @Override public boolean equals(Object o) { return o instanceof WindowInsets && ((WindowInsets) o).mSystem.equals(mSystem) && ((WindowInsets) o).mStable.equals(mStable) && ((WindowInsets) o).mConsumed == mConsumed; }
  @Override public int hashCode() { return mSystem.hashCode() * 31 + mStable.hashCode() + (mConsumed ? 1 : 0); }
  @Override public String toString() { return "WindowInsets{systemWindowInsets=" + mSystem + " stable=" + mStable + (mConsumed ? " consumed" : "") + "}"; }

  public static final class Builder {
    private Insets mSystem = Insets.NONE, mStable = Insets.NONE;
    public Builder() {}
    public Builder(WindowInsets i) { mSystem = i.mSystem; mStable = i.mStable; }
    public Builder setSystemWindowInsets(Insets i) { mSystem = i; return this; }
    public Builder setStableInsets(Insets i) { mStable = i; return this; }
    public Builder setSystemGestureInsets(Insets i) { return this; }
    public Builder setMandatorySystemGestureInsets(Insets i) { return this; }
    public Builder setTappableElementInsets(Insets i) { return this; }
    public Builder setInsets(int typeMask, Insets i) { if ((typeMask & (Type.STATUS_BARS | Type.NAVIGATION_BARS)) != 0) mSystem = i; return this; }
    public Builder setInsetsIgnoringVisibility(int typeMask, Insets i) { return setInsets(typeMask, i); }
    public Builder setVisible(int typeMask, boolean visible) { return this; }
    public Builder setDisplayCutout(DisplayCutout c) { return this; }
    public WindowInsets build() { return new WindowInsets(mSystem, mStable, false); }
  }
  public static final class Type {
    static final int STATUS_BARS = 1, NAVIGATION_BARS = 2, CAPTION_BAR = 4, IME = 8, SYSTEM_GESTURES = 16, MANDATORY_SYSTEM_GESTURES = 32,
        TAPPABLE_ELEMENT = 64, DISPLAY_CUTOUT = 128;
    private Type() {}
    public static int statusBars() { return STATUS_BARS; }
    public static int navigationBars() { return NAVIGATION_BARS; }
    public static int captionBar() { return CAPTION_BAR; }
    public static int ime() { return IME; }
    public static int systemGestures() { return SYSTEM_GESTURES; }
    public static int mandatorySystemGestures() { return MANDATORY_SYSTEM_GESTURES; }
    public static int tappableElement() { return TAPPABLE_ELEMENT; }
    public static int displayCutout() { return DISPLAY_CUTOUT; }
    public static int systemBars() { return STATUS_BARS | NAVIGATION_BARS | CAPTION_BAR; }
    public static int systemOverlays() { return 0; }
  }
  public static final class Side {
    public static final int LEFT = 1, TOP = 2, RIGHT = 4, BOTTOM = 8;
    private Side() {}
    public static int all() { return LEFT | TOP | RIGHT | BOTTOM; }
  }
}

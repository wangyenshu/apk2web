package android.view;

import android.os.Parcel;

/** The window manager (a2w.Windows): adding, updating and removing windows; the display and window metrics. */
public interface WindowManager extends ViewManager {
  class BadTokenException extends RuntimeException { public BadTokenException() {} public BadTokenException(String m) { super(m); } }
  class InvalidDisplayException extends RuntimeException { public InvalidDisplayException() {} public InvalidDisplayException(String m) { super(m); } }
  int SCREEN_RECORDING_STATE_NOT_VISIBLE = 0, SCREEN_RECORDING_STATE_VISIBLE = 1;
  Display getDefaultDisplay();
  void removeViewImmediate(View view);
  /** the window's bounds and insets: the page's screen */
  default WindowMetrics getCurrentWindowMetrics() {
    return new WindowMetrics(new android.graphics.Rect(0, 0, a2w.Screen.W, a2w.Screen.H), WindowInsets.zero(), a2w.Screen.DENSITY);
  }
  default WindowMetrics getMaximumWindowMetrics() { return getCurrentWindowMetrics(); }
  /** the page composites no cross-window blur */
  default boolean isCrossWindowBlurEnabled() { return false; }
  default void addCrossWindowBlurEnabledListener(java.util.function.Consumer<Boolean> listener) { listener.accept(false); }
  default void addCrossWindowBlurEnabledListener(java.util.concurrent.Executor executor, java.util.function.Consumer<Boolean> listener) { executor.execute(() -> listener.accept(false)); }
  default void removeCrossWindowBlurEnabledListener(java.util.function.Consumer<Boolean> listener) {}
  /** the display does not rotate */
  default void addProposedRotationListener(java.util.concurrent.Executor executor, java.util.function.IntConsumer listener) {}
  default void removeProposedRotationListener(java.util.function.IntConsumer listener) {}
  /** no screen recording is observable from the page */
  default int addScreenRecordingCallback(java.util.concurrent.Executor executor, java.util.function.Consumer<Integer> callback) { return SCREEN_RECORDING_STATE_NOT_VISIBLE; }
  default void removeScreenRecordingCallback(java.util.function.Consumer<Integer> callback) {}
  /** surface controls and presentation thresholds belong to the system compositor, which the page lacks */
  default void registerTrustedPresentationListener(android.os.IBinder window, android.window.TrustedPresentationThresholds thresholds, java.util.concurrent.Executor executor, java.util.function.Consumer<Boolean> listener) {}
  default void unregisterTrustedPresentationListener(java.util.function.Consumer<Boolean> listener) {}
  default boolean registerBatchedSurfaceControlInputReceiver(android.window.InputTransferToken hostToken, android.view.SurfaceControl surfaceControl, Choreographer choreographer, SurfaceControlInputReceiver receiver) { return false; }
  default boolean registerUnbatchedSurfaceControlInputReceiver(android.window.InputTransferToken hostToken, android.view.SurfaceControl surfaceControl, android.os.Looper looper, SurfaceControlInputReceiver receiver) { return false; }
  default void unregisterSurfaceControlInputReceiver(android.view.SurfaceControl surfaceControl) {}
  default boolean transferTouchGesture(android.window.InputTransferToken transferFromToken, android.window.InputTransferToken transferToToken) { return false; }

  /** A window's layout and attributes (size, position, type, flags, soft input mode, dimming...). */
  class LayoutParams extends ViewGroup.LayoutParams implements android.os.Parcelable {
    public static final int ALPHA_CHANGED = 128;
    public static final int ANIMATION_CHANGED = 16;
    public static final float BRIGHTNESS_OVERRIDE_FULL = 1.0f;
    public static final float BRIGHTNESS_OVERRIDE_NONE = -1.0f;
    public static final float BRIGHTNESS_OVERRIDE_OFF = 0.0f;
    public static final int DIM_AMOUNT_CHANGED = 32;
    public static final int DISPLAY_FLAG_DISABLE_HDR_CONVERSION = 1;
    public static final int FIRST_APPLICATION_WINDOW = 1;
    public static final int FIRST_SUB_WINDOW = 1000;
    public static final int FIRST_SYSTEM_WINDOW = 2000;
    public static final int FLAGS_CHANGED = 4;
    public static final int FLAG_ALLOW_LOCK_WHILE_SCREEN_ON = 1;
    public static final int FLAG_ALT_FOCUSABLE_IM = 131072;
    public static final int FLAG_BLUR_BEHIND = 4;
    public static final int FLAG_DIM_BEHIND = 2;
    public static final int FLAG_DISMISS_KEYGUARD = 4194304;
    public static final int FLAG_DITHER = 4096;
    public static final int FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS = -2147483648;
    public static final int FLAG_FORCE_NOT_FULLSCREEN = 2048;
    public static final int FLAG_FULLSCREEN = 1024;
    public static final int FLAG_HARDWARE_ACCELERATED = 16777216;
    public static final int FLAG_IGNORE_CHEEK_PRESSES = 32768;
    public static final int FLAG_KEEP_SCREEN_ON = 128;
    public static final int FLAG_LAYOUT_ATTACHED_IN_DECOR = 1073741824;
    public static final int FLAG_LAYOUT_INSET_DECOR = 65536;
    public static final int FLAG_LAYOUT_IN_OVERSCAN = 33554432;
    public static final int FLAG_LAYOUT_IN_SCREEN = 256;
    public static final int FLAG_LAYOUT_NO_LIMITS = 512;
    public static final int FLAG_LOCAL_FOCUS_MODE = 268435456;
    public static final int FLAG_NOT_FOCUSABLE = 8;
    public static final int FLAG_NOT_TOUCHABLE = 16;
    public static final int FLAG_NOT_TOUCH_MODAL = 32;
    public static final int FLAG_SCALED = 16384;
    public static final int FLAG_SECURE = 8192;
    public static final int FLAG_SHOW_WALLPAPER = 1048576;
    public static final int FLAG_SHOW_WHEN_LOCKED = 524288;
    public static final int FLAG_SPLIT_TOUCH = 8388608;
    public static final int FLAG_TOUCHABLE_WHEN_WAKING = 64;
    public static final int FLAG_TRANSLUCENT_NAVIGATION = 134217728;
    public static final int FLAG_TRANSLUCENT_STATUS = 67108864;
    public static final int FLAG_TURN_SCREEN_ON = 2097152;
    public static final int FLAG_WATCH_OUTSIDE_TOUCH = 262144;
    public static final int FORMAT_CHANGED = 8;
    public static final int LAST_APPLICATION_WINDOW = 99;
    public static final int LAST_SUB_WINDOW = 1999;
    public static final int LAST_SYSTEM_WINDOW = 2999;
    public static final int LAYOUT_CHANGED = 1;
    public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS = 3;
    public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT = 0;
    public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER = 2;
    public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES = 1;
    public static final int MEMORY_TYPE_CHANGED = 256;
    public static final int MEMORY_TYPE_GPU = 2;
    public static final int MEMORY_TYPE_HARDWARE = 1;
    public static final int MEMORY_TYPE_NORMAL = 0;
    public static final int MEMORY_TYPE_PUSH_BUFFERS = 3;
    public static final int ROTATION_ANIMATION_CHANGED = 4096;
    public static final int ROTATION_ANIMATION_CROSSFADE = 1;
    public static final int ROTATION_ANIMATION_JUMPCUT = 2;
    public static final int ROTATION_ANIMATION_ROTATE = 0;
    public static final int ROTATION_ANIMATION_SEAMLESS = 3;
    public static final int SCREEN_BRIGHTNESS_CHANGED = 2048;
    public static final int SCREEN_ORIENTATION_CHANGED = 1024;
    public static final int SOFT_INPUT_ADJUST_NOTHING = 48;
    public static final int SOFT_INPUT_ADJUST_PAN = 32;
    public static final int SOFT_INPUT_ADJUST_RESIZE = 16;
    public static final int SOFT_INPUT_ADJUST_UNSPECIFIED = 0;
    public static final int SOFT_INPUT_IS_FORWARD_NAVIGATION = 256;
    public static final int SOFT_INPUT_MASK_ADJUST = 240;
    public static final int SOFT_INPUT_MASK_STATE = 15;
    public static final int SOFT_INPUT_MODE_CHANGED = 512;
    public static final int SOFT_INPUT_STATE_ALWAYS_HIDDEN = 3;
    public static final int SOFT_INPUT_STATE_ALWAYS_VISIBLE = 5;
    public static final int SOFT_INPUT_STATE_HIDDEN = 2;
    public static final int SOFT_INPUT_STATE_UNCHANGED = 1;
    public static final int SOFT_INPUT_STATE_UNSPECIFIED = 0;
    public static final int SOFT_INPUT_STATE_VISIBLE = 4;
    public static final int TITLE_CHANGED = 64;
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 2032;
    public static final int TYPE_APPLICATION = 2;
    public static final int TYPE_APPLICATION_ATTACHED_DIALOG = 1003;
    public static final int TYPE_APPLICATION_MEDIA = 1001;
    public static final int TYPE_APPLICATION_OVERLAY = 2038;
    public static final int TYPE_APPLICATION_PANEL = 1000;
    public static final int TYPE_APPLICATION_STARTING = 3;
    public static final int TYPE_APPLICATION_SUB_PANEL = 1002;
    public static final int TYPE_BASE_APPLICATION = 1;
    public static final int TYPE_CHANGED = 2;
    public static final int TYPE_DRAWN_APPLICATION = 4;
    public static final int TYPE_INPUT_METHOD = 2011;
    public static final int TYPE_INPUT_METHOD_DIALOG = 2012;
    public static final int TYPE_KEYGUARD_DIALOG = 2009;
    public static final int TYPE_PHONE = 2002;
    public static final int TYPE_PRIORITY_PHONE = 2007;
    public static final int TYPE_PRIVATE_PRESENTATION = 2030;
    public static final int TYPE_SEARCH_BAR = 2001;
    public static final int TYPE_STATUS_BAR = 2000;
    public static final int TYPE_SYSTEM_ALERT = 2003;
    public static final int TYPE_SYSTEM_DIALOG = 2008;
    public static final int TYPE_SYSTEM_ERROR = 2010;
    public static final int TYPE_SYSTEM_OVERLAY = 2006;
    public static final int TYPE_TOAST = 2005;
    public static final int TYPE_WALLPAPER = 2013;
    public int x, y, type, memoryType, flags, softInputMode, gravity, format, windowAnimations, rotationAnimation;
    public int screenOrientation = -1, systemUiVisibility, layoutInDisplayCutoutMode, preferredDisplayModeId;
    public float horizontalMargin, verticalMargin, horizontalWeight, verticalWeight, alpha = 1, dimAmount = 1.0f;
    public float screenBrightness = BRIGHTNESS_OVERRIDE_NONE, buttonBrightness = BRIGHTNESS_OVERRIDE_NONE, preferredRefreshRate;
    public boolean preferMinimalPostProcessing;
    public android.os.IBinder token;
    public String packageName;
    private CharSequence mTitle = "";
    private int mFitInsetsTypes = WindowInsets.Type.systemBars(), mFitInsetsSides = WindowInsets.Side.all(), mColorMode, mBlurBehindRadius;
    private boolean mFitInsetsIgnoringVisibility, mWallpaperTouchEventsEnabled = true, mCanPlayMoveAnimation = true, mHdrConversionEnabled = true;
    private boolean mFrameRateBoostOnTouch = true, mFrameRatePowerSavingsBalanced = true;
    private float mDesiredHdrHeadroom;
    public LayoutParams() { super(MATCH_PARENT, MATCH_PARENT); type = TYPE_APPLICATION; format = android.graphics.PixelFormat.OPAQUE; }
    public LayoutParams(int type) { this(); this.type = type; }
    public LayoutParams(int type, int flags) { this(); this.type = type; this.flags = flags; }
    public LayoutParams(int type, int flags, int format) { this(type, flags); this.format = format; }
    public LayoutParams(int w, int h, int type, int flags, int format) { super(w, h); this.type = type; this.flags = flags; this.format = format; }
    public LayoutParams(int w, int h, int x, int y, int type, int flags, int format) { this(w, h, type, flags, format); this.x = x; this.y = y; }
    public LayoutParams(Parcel in) {
      this();
      width = in.readInt(); height = in.readInt(); x = in.readInt(); y = in.readInt(); type = in.readInt(); flags = in.readInt();
      softInputMode = in.readInt(); layoutInDisplayCutoutMode = in.readInt(); gravity = in.readInt(); horizontalMargin = in.readFloat();
      verticalMargin = in.readFloat(); format = in.readInt(); windowAnimations = in.readInt(); alpha = in.readFloat(); dimAmount = in.readFloat();
      screenBrightness = in.readFloat(); buttonBrightness = in.readFloat(); rotationAnimation = in.readInt(); token = in.readStrongBinder();
      packageName = in.readString(); mTitle = android.text.TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in); screenOrientation = in.readInt();
      preferredRefreshRate = in.readFloat(); preferredDisplayModeId = in.readInt(); preferMinimalPostProcessing = in.readInt() != 0;
      systemUiVisibility = in.readInt(); horizontalWeight = in.readFloat(); verticalWeight = in.readFloat(); memoryType = in.readInt();
      mFitInsetsTypes = in.readInt(); mFitInsetsSides = in.readInt(); mFitInsetsIgnoringVisibility = in.readInt() != 0; mColorMode = in.readInt();
      mBlurBehindRadius = in.readInt(); mWallpaperTouchEventsEnabled = in.readInt() != 0; mCanPlayMoveAnimation = in.readInt() != 0;
      mHdrConversionEnabled = in.readInt() != 0; mFrameRateBoostOnTouch = in.readInt() != 0; mFrameRatePowerSavingsBalanced = in.readInt() != 0;
      mDesiredHdrHeadroom = in.readFloat();
    }
    public void writeToParcel(Parcel out, int f) {
      out.writeInt(width); out.writeInt(height); out.writeInt(x); out.writeInt(y); out.writeInt(type); out.writeInt(flags);
      out.writeInt(softInputMode); out.writeInt(layoutInDisplayCutoutMode); out.writeInt(gravity); out.writeFloat(horizontalMargin);
      out.writeFloat(verticalMargin); out.writeInt(format); out.writeInt(windowAnimations); out.writeFloat(alpha); out.writeFloat(dimAmount);
      out.writeFloat(screenBrightness); out.writeFloat(buttonBrightness); out.writeInt(rotationAnimation); out.writeStrongBinder(token);
      out.writeString(packageName); android.text.TextUtils.writeToParcel(mTitle, out, f); out.writeInt(screenOrientation);
      out.writeFloat(preferredRefreshRate); out.writeInt(preferredDisplayModeId); out.writeInt(preferMinimalPostProcessing ? 1 : 0);
      out.writeInt(systemUiVisibility); out.writeFloat(horizontalWeight); out.writeFloat(verticalWeight); out.writeInt(memoryType);
      out.writeInt(mFitInsetsTypes); out.writeInt(mFitInsetsSides); out.writeInt(mFitInsetsIgnoringVisibility ? 1 : 0); out.writeInt(mColorMode);
      out.writeInt(mBlurBehindRadius); out.writeInt(mWallpaperTouchEventsEnabled ? 1 : 0); out.writeInt(mCanPlayMoveAnimation ? 1 : 0);
      out.writeInt(mHdrConversionEnabled ? 1 : 0); out.writeInt(mFrameRateBoostOnTouch ? 1 : 0); out.writeInt(mFrameRatePowerSavingsBalanced ? 1 : 0);
      out.writeFloat(mDesiredHdrHeadroom);
    }
    public static final android.os.Parcelable.Creator<LayoutParams> CREATOR = new android.os.Parcelable.Creator<LayoutParams>() {
      public LayoutParams createFromParcel(Parcel in) { return new LayoutParams(in); }
      public LayoutParams[] newArray(int size) { return new LayoutParams[size]; }
    };
    public int describeContents() { return 0; }
    /** whether a window with these flags may get the soft keyboard: focusable, or not focusable with ALT_FOCUSABLE_IM */
    public static boolean mayUseInputMethod(int flags) {
      int f = flags & (FLAG_NOT_FOCUSABLE | FLAG_ALT_FOCUSABLE_IM);
      return f == 0 || f == (FLAG_NOT_FOCUSABLE | FLAG_ALT_FOCUSABLE_IM);
    }
    public final void setTitle(CharSequence t) { mTitle = t == null ? "" : t; }
    public final CharSequence getTitle() { return mTitle; }
    public void setFitInsetsTypes(int types) { mFitInsetsTypes = types; }
    public int getFitInsetsTypes() { return mFitInsetsTypes; }
    public void setFitInsetsSides(int sides) { mFitInsetsSides = sides; }
    public int getFitInsetsSides() { return mFitInsetsSides; }
    public void setFitInsetsIgnoringVisibility(boolean ignore) { mFitInsetsIgnoringVisibility = ignore; }
    public boolean isFitInsetsIgnoringVisibility() { return mFitInsetsIgnoringVisibility; }
    public void setWallpaperTouchEventsEnabled(boolean enable) { mWallpaperTouchEventsEnabled = enable; }
    public boolean areWallpaperTouchEventsEnabled() { return mWallpaperTouchEventsEnabled; }
    public void setCanPlayMoveAnimation(boolean enable) { mCanPlayMoveAnimation = enable; }
    public boolean canPlayMoveAnimation() { return mCanPlayMoveAnimation; }
    public void setHdrConversionEnabled(boolean enabled) { mHdrConversionEnabled = enabled; }
    public boolean isHdrConversionEnabled() { return mHdrConversionEnabled; }
    public void setColorMode(int colorMode) { mColorMode = colorMode; }
    public int getColorMode() { return mColorMode; }
    public void setDesiredHdrHeadroom(float desiredHeadroom) {
      if (desiredHeadroom != 0 && (desiredHeadroom < 1 || desiredHeadroom > 10000)) throw new IllegalArgumentException("desiredHeadroom must be 0 or between 1.0 and 10000.0");
      mDesiredHdrHeadroom = desiredHeadroom;
    }
    public float getDesiredHdrHeadroom() { return mDesiredHdrHeadroom; }
    public void setFrameRateBoostOnTouchEnabled(boolean enabled) { mFrameRateBoostOnTouch = enabled; }
    public boolean getFrameRateBoostOnTouchEnabled() { return mFrameRateBoostOnTouch; }
    public void setFrameRatePowerSavingsBalanced(boolean enabled) { mFrameRatePowerSavingsBalanced = enabled; }
    public boolean isFrameRatePowerSavingsBalanced() { return mFrameRatePowerSavingsBalanced; }
    public void setBlurBehindRadius(int blurBehindRadius) { mBlurBehindRadius = blurBehindRadius; }
    public int getBlurBehindRadius() { return mBlurBehindRadius; }
    /** copies o's attributes; returns the *_CHANGED bits of what differed */
    public final int copyFrom(LayoutParams o) {
      int changes = 0;
      if (width != o.width) { width = o.width; changes |= LAYOUT_CHANGED; }
      if (height != o.height) { height = o.height; changes |= LAYOUT_CHANGED; }
      if (x != o.x) { x = o.x; changes |= LAYOUT_CHANGED; }
      if (y != o.y) { y = o.y; changes |= LAYOUT_CHANGED; }
      if (horizontalWeight != o.horizontalWeight) { horizontalWeight = o.horizontalWeight; changes |= LAYOUT_CHANGED; }
      if (verticalWeight != o.verticalWeight) { verticalWeight = o.verticalWeight; changes |= LAYOUT_CHANGED; }
      if (horizontalMargin != o.horizontalMargin) { horizontalMargin = o.horizontalMargin; changes |= LAYOUT_CHANGED; }
      if (verticalMargin != o.verticalMargin) { verticalMargin = o.verticalMargin; changes |= LAYOUT_CHANGED; }
      if (type != o.type) { type = o.type; changes |= TYPE_CHANGED; }
      if (flags != o.flags) { flags = o.flags; changes |= FLAGS_CHANGED; }
      if (softInputMode != o.softInputMode) { softInputMode = o.softInputMode; changes |= SOFT_INPUT_MODE_CHANGED; }
      if (layoutInDisplayCutoutMode != o.layoutInDisplayCutoutMode) { layoutInDisplayCutoutMode = o.layoutInDisplayCutoutMode; changes |= LAYOUT_CHANGED; }
      if (gravity != o.gravity) { gravity = o.gravity; changes |= LAYOUT_CHANGED; }
      if (format != o.format) { format = o.format; changes |= FORMAT_CHANGED; }
      if (windowAnimations != o.windowAnimations) { windowAnimations = o.windowAnimations; changes |= ANIMATION_CHANGED; }
      if (token == null) token = o.token;
      if (packageName == null) packageName = o.packageName;
      if (!java.util.Objects.equals(mTitle, o.mTitle) && o.mTitle != null) { mTitle = o.mTitle; changes |= TITLE_CHANGED; }
      if (alpha != o.alpha) { alpha = o.alpha; changes |= ALPHA_CHANGED; }
      if (dimAmount != o.dimAmount) { dimAmount = o.dimAmount; changes |= DIM_AMOUNT_CHANGED; }
      if (screenBrightness != o.screenBrightness) { screenBrightness = o.screenBrightness; changes |= SCREEN_BRIGHTNESS_CHANGED; }
      if (buttonBrightness != o.buttonBrightness) { buttonBrightness = o.buttonBrightness; changes |= SCREEN_BRIGHTNESS_CHANGED; }
      if (rotationAnimation != o.rotationAnimation) { rotationAnimation = o.rotationAnimation; changes |= ROTATION_ANIMATION_CHANGED; }
      if (screenOrientation != o.screenOrientation) { screenOrientation = o.screenOrientation; changes |= SCREEN_ORIENTATION_CHANGED; }
      if (preferredRefreshRate != o.preferredRefreshRate) { preferredRefreshRate = o.preferredRefreshRate; changes |= LAYOUT_CHANGED; }
      if (preferredDisplayModeId != o.preferredDisplayModeId) { preferredDisplayModeId = o.preferredDisplayModeId; changes |= LAYOUT_CHANGED; }
      if (preferMinimalPostProcessing != o.preferMinimalPostProcessing) { preferMinimalPostProcessing = o.preferMinimalPostProcessing; changes |= LAYOUT_CHANGED; }
      if (systemUiVisibility != o.systemUiVisibility) { systemUiVisibility = o.systemUiVisibility; changes |= LAYOUT_CHANGED; }
      if (memoryType != o.memoryType) { memoryType = o.memoryType; changes |= MEMORY_TYPE_CHANGED; }
      if (mFitInsetsTypes != o.mFitInsetsTypes || mFitInsetsSides != o.mFitInsetsSides || mFitInsetsIgnoringVisibility != o.mFitInsetsIgnoringVisibility) {
        mFitInsetsTypes = o.mFitInsetsTypes; mFitInsetsSides = o.mFitInsetsSides; mFitInsetsIgnoringVisibility = o.mFitInsetsIgnoringVisibility; changes |= LAYOUT_CHANGED;
      }
      if (mColorMode != o.mColorMode) { mColorMode = o.mColorMode; changes |= LAYOUT_CHANGED; }
      mBlurBehindRadius = o.mBlurBehindRadius; mWallpaperTouchEventsEnabled = o.mWallpaperTouchEventsEnabled; mCanPlayMoveAnimation = o.mCanPlayMoveAnimation;
      mHdrConversionEnabled = o.mHdrConversionEnabled; mFrameRateBoostOnTouch = o.mFrameRateBoostOnTouch; mFrameRatePowerSavingsBalanced = o.mFrameRatePowerSavingsBalanced;
      mDesiredHdrHeadroom = o.mDesiredHdrHeadroom;
      return changes;
    }
    public String debug(String output) { return output + "Contents of " + this + ":"; }
    @Override public String toString() {
      StringBuilder sb = new StringBuilder(256).append("{(").append(x).append(',').append(y).append(")(")
          .append(width == MATCH_PARENT ? "fill" : width == WRAP_CONTENT ? "wrap" : String.valueOf(width)).append('x')
          .append(height == MATCH_PARENT ? "fill" : height == WRAP_CONTENT ? "wrap" : String.valueOf(height)).append(')');
      if (horizontalMargin != 0) sb.append(" hm=").append(horizontalMargin);
      if (verticalMargin != 0) sb.append(" vm=").append(verticalMargin);
      if (gravity != 0) sb.append(" gr=#").append(Integer.toHexString(gravity));
      if (softInputMode != 0) sb.append(" sim=#").append(Integer.toHexString(softInputMode));
      sb.append(" ty=").append(type).append(" fmt=").append(format);
      if (windowAnimations != 0) sb.append(" wanim=0x").append(Integer.toHexString(windowAnimations));
      if (screenOrientation != -1) sb.append(" or=").append(screenOrientation);
      if (alpha != 1.0f) sb.append(" alpha=").append(alpha);
      if (screenBrightness != BRIGHTNESS_OVERRIDE_NONE) sb.append(" sbrt=").append(screenBrightness);
      if (dimAmount != 1.0f) sb.append(" dimAmount=").append(dimAmount);
      sb.append(" fl=#").append(Integer.toHexString(flags));
      if (mTitle != null && mTitle.length() > 0) sb.append(" title=").append(mTitle);
      return sb.append('}').toString();
    }
  }
}

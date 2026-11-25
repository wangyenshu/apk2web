package android.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.util.DisplayMetrics;

/**
 * The one display: the page's screen (a2w.Screen), 60 Hz, sRGB, no HDR, no cutout or rounded corners. A landscape
 * screen reports ROTATION_90 (the natural orientation is portrait, as on a phone).
 */
public final class Display {
  public static final int DEFAULT_DISPLAY = 0, INVALID_DISPLAY = -1;
  public static final int STATE_UNKNOWN = 0, STATE_OFF = 1, STATE_ON = 2, STATE_DOZE = 3, STATE_DOZE_SUSPEND = 4, STATE_VR = 5, STATE_ON_SUSPEND = 6;
  public static final int FLAG_SUPPORTS_PROTECTED_BUFFERS = 1, FLAG_SECURE = 2, FLAG_PRIVATE = 4, FLAG_PRESENTATION = 8, FLAG_ROUND = 16;
  public static final Display DEFAULT = new Display();
  private static final Mode MODE = new Mode(1, 0, 0, 60f);
  Display() {}
  public int getDisplayId() { return DEFAULT_DISPLAY; }
  public String getName() { return "Built-in Screen"; }
  public boolean isValid() { return true; }
  public int getFlags() { return 0; }
  public int getState() { return STATE_ON; }
  public int getWidth() { return a2w.Screen.W; }
  public int getHeight() { return a2w.Screen.H; }
  public void getSize(Point p) { p.set(a2w.Screen.W, a2w.Screen.H); }
  public void getRealSize(Point p) { p.set(a2w.Screen.W, a2w.Screen.H); }
  public void getRectSize(Rect r) { r.set(0, 0, a2w.Screen.W, a2w.Screen.H); }
  /** the smallest and largest app sizes over the rotations: the screen's shorter and longer side */
  public void getCurrentSizeRange(Point outSmallestSize, Point outLargestSize) {
    int s = Math.min(a2w.Screen.W, a2w.Screen.H), l = Math.max(a2w.Screen.W, a2w.Screen.H);
    outSmallestSize.set(s, s);
    outLargestSize.set(l, l);
  }
  @Deprecated public int getOrientation() { return getRotation(); }
  public int getRotation() { return a2w.Screen.landscape() ? Surface.ROTATION_90 : Surface.ROTATION_0; }
  public float getRefreshRate() { return 60; }
  @Deprecated public float[] getSupportedRefreshRates() { return new float[] {60}; }
  public Mode getMode() { return MODE.sized(); }
  public Mode[] getSupportedModes() { return new Mode[] {getMode()}; }
  public long getAppVsyncOffsetNanos() { return 0; }
  /** a frame must be ready a vsync period ahead */
  public long getPresentationDeadlineNanos() { return 16_666_666L; }
  public int getPixelFormat() { return android.graphics.PixelFormat.RGBA_8888; }
  public void getMetrics(DisplayMetrics m) { m.setTo(a2w.Values.METRICS); }
  public void getRealMetrics(DisplayMetrics m) { m.setTo(a2w.Values.METRICS); }
  public HdrCapabilities getHdrCapabilities() { return new HdrCapabilities(new int[0], HdrCapabilities.INVALID_LUMINANCE, HdrCapabilities.INVALID_LUMINANCE, HdrCapabilities.INVALID_LUMINANCE); }
  public boolean isHdr() { return false; }
  public boolean isWideColorGamut() { return false; }
  /** null: the display is not wide gamut */
  public android.graphics.ColorSpace getPreferredWideGamutColorSpace() { return null; }
  public boolean isMinimalPostProcessingSupported() { return false; }
  public boolean isHdrSdrRatioAvailable() { return false; }
  public float getHdrSdrRatio() { return 1.0f; }
  public float getHighestHdrSdrRatio() { return 1.0f; }
  public void registerHdrSdrRatioChangedListener(java.util.concurrent.Executor executor, java.util.function.Consumer<Display> listener) {
    throw new IllegalStateException("HDR/SDR ratio changed not available");
  }
  public void unregisterHdrSdrRatioChangedListener(java.util.function.Consumer<Display> listener) {}
  /** no cutout, no rounded corners, a rectangular shape */
  public DisplayCutout getCutout() { return null; }
  public RoundedCorner getRoundedCorner(int position) { return null; }
  public DisplayShape getShape() { return new DisplayShape(a2w.Screen.W, a2w.Screen.H); }
  /** null for a built-in display */
  public android.hardware.display.DeviceProductInfo getDeviceProductInfo() { return null; }
  public android.hardware.OverlayProperties getOverlaySupport() { return new android.hardware.OverlayProperties(); }
  @Override public String toString() {
    return "Display id " + DEFAULT_DISPLAY + ": " + getName() + ", " + a2w.Screen.W + "x" + a2w.Screen.H + ", " + getRefreshRate() + " fps, rotation " + getRotation();
  }

  /** a display mode: id, physical size, refresh rate */
  public static final class Mode implements android.os.Parcelable {
    private final int mModeId, mWidth, mHeight;
    private final float mRefreshRate;
    Mode(int modeId, int width, int height, float refreshRate) { mModeId = modeId; mWidth = width; mHeight = height; mRefreshRate = refreshRate; }
    Mode sized() { return new Mode(mModeId, Math.min(a2w.Screen.W, a2w.Screen.H), Math.max(a2w.Screen.W, a2w.Screen.H), mRefreshRate); }
    public int getModeId() { return mModeId; }
    public int getPhysicalWidth() { return mWidth; }
    public int getPhysicalHeight() { return mHeight; }
    public float getRefreshRate() { return mRefreshRate; }
    public float[] getAlternativeRefreshRates() { return new float[0]; }
    public int[] getSupportedHdrTypes() { return new int[0]; }
    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Mode)) return false;
      Mode m = (Mode) o;
      return mModeId == m.mModeId && mWidth == m.mWidth && mHeight == m.mHeight && Float.floatToIntBits(mRefreshRate) == Float.floatToIntBits(m.mRefreshRate);
    }
    @Override public int hashCode() { return ((17 * 17 + mModeId) * 17 + mWidth) * 17 + mHeight + 17 * Float.floatToIntBits(mRefreshRate); }
    @Override public String toString() { return "{id=" + mModeId + ", width=" + mWidth + ", height=" + mHeight + ", fps=" + mRefreshRate + "}"; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel out, int flags) { out.writeInt(mModeId); out.writeInt(mWidth); out.writeInt(mHeight); out.writeFloat(mRefreshRate); }
    public static final android.os.Parcelable.Creator<Mode> CREATOR = new android.os.Parcelable.Creator<Mode>() {
      public Mode createFromParcel(Parcel in) { return new Mode(in.readInt(), in.readInt(), in.readInt(), in.readFloat()); }
      public Mode[] newArray(int size) { return new Mode[size]; }
    };
  }

  /** the display's HDR support: none */
  public static final class HdrCapabilities implements android.os.Parcelable {
    public static final float INVALID_LUMINANCE = -1;
    public static final int HDR_TYPE_DOLBY_VISION = 1, HDR_TYPE_HDR10 = 2, HDR_TYPE_HLG = 3, HDR_TYPE_HDR10_PLUS = 4;
    private final int[] mTypes;
    private final float mMax, mAvg, mMin;
    HdrCapabilities(int[] types, float max, float avg, float min) { mTypes = types; mMax = max; mAvg = avg; mMin = min; }
    @Deprecated public int[] getSupportedHdrTypes() { return mTypes.clone(); }
    public float getDesiredMaxLuminance() { return mMax; }
    public float getDesiredMaxAverageLuminance() { return mAvg; }
    public float getDesiredMinLuminance() { return mMin; }
    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof HdrCapabilities)) return false;
      HdrCapabilities h = (HdrCapabilities) o;
      return java.util.Arrays.equals(mTypes, h.mTypes) && mMax == h.mMax && mAvg == h.mAvg && mMin == h.mMin;
    }
    @Override public int hashCode() { return java.util.Arrays.hashCode(mTypes) * 31 + Float.floatToIntBits(mMax) * 17 + Float.floatToIntBits(mAvg) * 7 + Float.floatToIntBits(mMin); }
    @Override public String toString() { return "HdrCapabilities{mSupportedHdrTypes=" + java.util.Arrays.toString(mTypes) + ", mMaxLuminance=" + mMax + ", mMaxAverageLuminance=" + mAvg + ", mMinLuminance=" + mMin + "}"; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel out, int flags) { out.writeIntArray(mTypes); out.writeFloat(mMax); out.writeFloat(mAvg); out.writeFloat(mMin); }
    public static final android.os.Parcelable.Creator<HdrCapabilities> CREATOR = new android.os.Parcelable.Creator<HdrCapabilities>() {
      public HdrCapabilities createFromParcel(Parcel in) { return new HdrCapabilities(in.createIntArray(), in.readFloat(), in.readFloat(), in.readFloat()); }
      public HdrCapabilities[] newArray(int size) { return new HdrCapabilities[size]; }
    };
  }
}

package android.view;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * The page's input devices: the built-in keyboard (id 0, which also carries the page's pointer: touch and mouse)
 * and Android's virtual keyboard (id -1, KeyCharacterMap.VIRTUAL_KEYBOARD).
 */
public final class InputDevice implements Parcelable {
  public static final int SOURCE_CLASS_MASK = 0xff, SOURCE_CLASS_NONE = 0, SOURCE_CLASS_BUTTON = 1, SOURCE_CLASS_POINTER = 2,
      SOURCE_CLASS_TRACKBALL = 4, SOURCE_CLASS_POSITION = 8, SOURCE_CLASS_JOYSTICK = 16;
  public static final int SOURCE_UNKNOWN = 0, SOURCE_KEYBOARD = 0x101, SOURCE_DPAD = 0x201, SOURCE_GAMEPAD = 0x401,
      SOURCE_TOUCHSCREEN = 0x1002, SOURCE_MOUSE = 0x2002, SOURCE_STYLUS = 0x4002, SOURCE_BLUETOOTH_STYLUS = 0xc002,
      SOURCE_TRACKBALL = 0x10004, SOURCE_MOUSE_RELATIVE = 0x20004, SOURCE_TOUCHPAD = 0x100008,
      SOURCE_TOUCH_NAVIGATION = 0x200000, SOURCE_ROTARY_ENCODER = 0x400000, SOURCE_JOYSTICK = 0x1000010,
      SOURCE_HDMI = 0x2000001, SOURCE_SENSOR = 0x4000000, SOURCE_ANY = 0xffffff00;
  public static final int KEYBOARD_TYPE_NONE = 0, KEYBOARD_TYPE_NON_ALPHABETIC = 1, KEYBOARD_TYPE_ALPHABETIC = 2;
  @Deprecated public static final int MOTION_RANGE_X = 0, MOTION_RANGE_Y = 1, MOTION_RANGE_PRESSURE = 2, MOTION_RANGE_SIZE = 3,
      MOTION_RANGE_TOUCH_MAJOR = 4, MOTION_RANGE_TOUCH_MINOR = 5, MOTION_RANGE_TOOL_MAJOR = 6, MOTION_RANGE_TOOL_MINOR = 7,
      MOTION_RANGE_ORIENTATION = 8;

  private final int mId, mSources, mKeyboardType;
  private final String mName;
  private final boolean mVirtual;

  private InputDevice(int id, String name, int sources, int keyboardType, boolean virtual) {
    mId = id; mName = name; mSources = sources; mKeyboardType = keyboardType; mVirtual = virtual;
  }
  private static final InputDevice BUILT_IN = new InputDevice(0, "apk2web-input",
      SOURCE_KEYBOARD | SOURCE_DPAD | SOURCE_TOUCHSCREEN | SOURCE_MOUSE, KEYBOARD_TYPE_ALPHABETIC, false);
  private static final InputDevice VIRTUAL = new InputDevice(-1, "Virtual", SOURCE_KEYBOARD, KEYBOARD_TYPE_ALPHABETIC, true);

  public static InputDevice getDevice(int id) { return id == 0 ? BUILT_IN : id == -1 ? VIRTUAL : null; }
  public static int[] getDeviceIds() { return new int[] {-1, 0}; }

  public int getId() { return mId; }
  public int getControllerNumber() { return 0; }
  public String getDescriptor() { return mVirtual ? "virtual" : "a2w:" + mName; }
  public int getGeneration() { return 1; }
  public int getVendorId() { return 0; }
  public int getProductId() { return 0; }
  public boolean isVirtual() { return mVirtual; }
  public boolean isExternal() { return false; }
  public boolean isFullKeyboard() { return mKeyboardType == KEYBOARD_TYPE_ALPHABETIC; }
  public boolean isEnabled() { return true; }
  public String getName() { return mName; }
  public int getSources() { return mSources; }
  public boolean supportsSource(int source) { return (mSources & source) == source; }
  public int getKeyboardType() { return mKeyboardType; }
  public KeyCharacterMap getKeyCharacterMap() { return KeyCharacterMap.load(mId); }
  public boolean[] hasKeys(int... keys) {
    boolean[] r = new boolean[keys.length];
    for (int i = 0; i < keys.length; i++) r[i] = mKeyboardType != KEYBOARD_TYPE_NONE && keys[i] > 0 && keys[i] <= KeyEvent.getMaxKeyCode();
    return r;
  }
  public boolean hasMicrophone() { return false; }
  public android.os.Vibrator getVibrator() { return null; }

  private static int screen(boolean x) {
    android.util.DisplayMetrics m = android.content.res.Resources.getSystem().getDisplayMetrics();
    return m == null ? 1 : Math.max(1, x ? m.widthPixels : m.heightPixels);
  }
  public MotionRange getMotionRange(int axis) {
    if ((mSources & SOURCE_CLASS_POINTER) == 0) return null;
    switch (axis) {
      case MotionEvent.AXIS_X: return new MotionRange(axis, SOURCE_TOUCHSCREEN, 0, screen(true) - 1, 0, 0, 1);
      case MotionEvent.AXIS_Y: return new MotionRange(axis, SOURCE_TOUCHSCREEN, 0, screen(false) - 1, 0, 0, 1);
      case MotionEvent.AXIS_PRESSURE: case MotionEvent.AXIS_SIZE: return new MotionRange(axis, SOURCE_TOUCHSCREEN, 0, 1, 0, 0, 0);
      default: return null;
    }
  }
  public MotionRange getMotionRange(int axis, int source) {
    MotionRange r = getMotionRange(axis);
    return r != null && (source & SOURCE_CLASS_POINTER) != 0 ? r : null;
  }
  public List<MotionRange> getMotionRanges() {
    List<MotionRange> l = new ArrayList<>();
    for (int a : new int[] {MotionEvent.AXIS_X, MotionEvent.AXIS_Y, MotionEvent.AXIS_PRESSURE, MotionEvent.AXIS_SIZE}) {
      MotionRange r = getMotionRange(a);
      if (r != null) l.add(r);
    }
    return l;
  }

  public static final class MotionRange {
    private final int mAxis, mSource;
    private final float mMin, mMax, mFlat, mFuzz, mResolution;
    MotionRange(int axis, int source, float min, float max, float flat, float fuzz, float resolution) {
      mAxis = axis; mSource = source; mMin = min; mMax = max; mFlat = flat; mFuzz = fuzz; mResolution = resolution;
    }
    public int getAxis() { return mAxis; }
    public int getSource() { return mSource; }
    public boolean isFromSource(int source) { return (mSource & source) == source; }
    public float getMin() { return mMin; }
    public float getMax() { return mMax; }
    public float getRange() { return mMax - mMin; }
    public float getFlat() { return mFlat; }
    public float getFuzz() { return mFuzz; }
    public float getResolution() { return mResolution; }
  }

  public interface InputDeviceListener {
    void onInputDeviceAdded(int deviceId);
    void onInputDeviceRemoved(int deviceId);
    void onInputDeviceChanged(int deviceId);
  }

  @Override public String toString() { return "Input Device " + mId + ": " + mName; }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel p, int flags) { p.writeInt(mId); }
  public static final Parcelable.Creator<InputDevice> CREATOR = new Parcelable.Creator<InputDevice>() {
    public InputDevice createFromParcel(Parcel p) { InputDevice d = getDevice(p.readInt()); return d != null ? d : BUILT_IN; }
    public InputDevice[] newArray(int n) { return new InputDevice[n]; }
  };
}

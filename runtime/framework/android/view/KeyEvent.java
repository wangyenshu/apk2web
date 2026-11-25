package android.view;

public class KeyEvent extends InputEvent {
  /** the rest of android.jar's KeyEvent constants (API 35) */
  public static final int FLAG_FALLBACK = 1024, KEYCODE_1 = 8, KEYCODE_11 = 227, KEYCODE_12 = 228, KEYCODE_2 = 9, KEYCODE_3 = 10;
  public static final int KEYCODE_3D_MODE = 206, KEYCODE_4 = 11, KEYCODE_5 = 12, KEYCODE_6 = 13, KEYCODE_7 = 14, KEYCODE_8 = 15;
  public static final int KEYCODE_ALL_APPS = 284, KEYCODE_ALT_LEFT = 57, KEYCODE_ALT_RIGHT = 58, KEYCODE_APOSTROPHE = 75, KEYCODE_APP_SWITCH = 187, KEYCODE_ASSIST = 219;
  public static final int KEYCODE_AT = 77, KEYCODE_AVR_INPUT = 182, KEYCODE_AVR_POWER = 181, KEYCODE_B = 30, KEYCODE_BACKSLASH = 73, KEYCODE_BOOKMARK = 174;
  public static final int KEYCODE_BREAK = 121, KEYCODE_BRIGHTNESS_DOWN = 220, KEYCODE_BRIGHTNESS_UP = 221, KEYCODE_BUTTON_1 = 188, KEYCODE_BUTTON_10 = 197, KEYCODE_BUTTON_11 = 198;
  public static final int KEYCODE_BUTTON_12 = 199, KEYCODE_BUTTON_13 = 200, KEYCODE_BUTTON_14 = 201, KEYCODE_BUTTON_15 = 202, KEYCODE_BUTTON_16 = 203, KEYCODE_BUTTON_2 = 189;
  public static final int KEYCODE_BUTTON_3 = 190, KEYCODE_BUTTON_4 = 191, KEYCODE_BUTTON_5 = 192, KEYCODE_BUTTON_6 = 193, KEYCODE_BUTTON_7 = 194, KEYCODE_BUTTON_8 = 195;
  public static final int KEYCODE_BUTTON_9 = 196, KEYCODE_BUTTON_A = 96, KEYCODE_BUTTON_B = 97, KEYCODE_BUTTON_C = 98, KEYCODE_BUTTON_L1 = 102, KEYCODE_BUTTON_L2 = 104;
  public static final int KEYCODE_BUTTON_MODE = 110, KEYCODE_BUTTON_R1 = 103, KEYCODE_BUTTON_R2 = 105, KEYCODE_BUTTON_SELECT = 109, KEYCODE_BUTTON_START = 108, KEYCODE_BUTTON_THUMBL = 106;
  public static final int KEYCODE_BUTTON_THUMBR = 107, KEYCODE_BUTTON_X = 99, KEYCODE_BUTTON_Y = 100, KEYCODE_BUTTON_Z = 101, KEYCODE_C = 31, KEYCODE_CALCULATOR = 210;
  public static final int KEYCODE_CALENDAR = 208, KEYCODE_CAPS_LOCK = 115, KEYCODE_CAPTIONS = 175, KEYCODE_CHANNEL_DOWN = 167, KEYCODE_CHANNEL_UP = 166, KEYCODE_CONTACTS = 207;
  public static final int KEYCODE_COPY = 278, KEYCODE_CTRL_LEFT = 113, KEYCODE_CTRL_RIGHT = 114, KEYCODE_CUT = 277, KEYCODE_D = 32, KEYCODE_DEMO_APP_1 = 301;
  public static final int KEYCODE_DEMO_APP_2 = 302, KEYCODE_DEMO_APP_3 = 303, KEYCODE_DEMO_APP_4 = 304, KEYCODE_DPAD_DOWN_LEFT = 269, KEYCODE_DPAD_DOWN_RIGHT = 271, KEYCODE_DPAD_UP_LEFT = 268;
  public static final int KEYCODE_DPAD_UP_RIGHT = 270, KEYCODE_DVR = 173, KEYCODE_E = 33, KEYCODE_EISU = 212, KEYCODE_EMOJI_PICKER = 317, KEYCODE_ENVELOPE = 65;
  public static final int KEYCODE_EQUALS = 70, KEYCODE_EXPLORER = 64, KEYCODE_F = 34, KEYCODE_F1 = 131, KEYCODE_F10 = 140, KEYCODE_F11 = 141;
  public static final int KEYCODE_F12 = 142, KEYCODE_F2 = 132, KEYCODE_F3 = 133, KEYCODE_F4 = 134, KEYCODE_F5 = 135, KEYCODE_F6 = 136;
  public static final int KEYCODE_F7 = 137, KEYCODE_F8 = 138, KEYCODE_F9 = 139, KEYCODE_FEATURED_APP_1 = 297, KEYCODE_FEATURED_APP_2 = 298, KEYCODE_FEATURED_APP_3 = 299;
  public static final int KEYCODE_FEATURED_APP_4 = 300, KEYCODE_FOCUS = 80, KEYCODE_FORWARD = 125, KEYCODE_FUNCTION = 119, KEYCODE_G = 35, KEYCODE_GRAVE = 68;
  public static final int KEYCODE_GUIDE = 172, KEYCODE_H = 36, KEYCODE_HEADSETHOOK = 79, KEYCODE_HELP = 259, KEYCODE_HENKAN = 214, KEYCODE_I = 37;
  public static final int KEYCODE_INFO = 165, KEYCODE_INSERT = 124, KEYCODE_J = 38, KEYCODE_K = 39, KEYCODE_KANA = 218, KEYCODE_KATAKANA_HIRAGANA = 215;
  public static final int KEYCODE_KEYBOARD_BACKLIGHT_DOWN = 305, KEYCODE_KEYBOARD_BACKLIGHT_TOGGLE = 307, KEYCODE_KEYBOARD_BACKLIGHT_UP = 306, KEYCODE_L = 40, KEYCODE_LANGUAGE_SWITCH = 204, KEYCODE_LAST_CHANNEL = 229;
  public static final int KEYCODE_LEFT_BRACKET = 71, KEYCODE_M = 41, KEYCODE_MACRO_1 = 313, KEYCODE_MACRO_2 = 314, KEYCODE_MACRO_3 = 315, KEYCODE_MACRO_4 = 316;
  public static final int KEYCODE_MANNER_MODE = 205, KEYCODE_MEDIA_AUDIO_TRACK = 222, KEYCODE_MEDIA_CLOSE = 128, KEYCODE_MEDIA_EJECT = 129, KEYCODE_MEDIA_FAST_FORWARD = 90, KEYCODE_MEDIA_NEXT = 87;
  public static final int KEYCODE_MEDIA_PAUSE = 127, KEYCODE_MEDIA_PLAY = 126, KEYCODE_MEDIA_PLAY_PAUSE = 85, KEYCODE_MEDIA_PREVIOUS = 88, KEYCODE_MEDIA_RECORD = 130, KEYCODE_MEDIA_REWIND = 89;
  public static final int KEYCODE_MEDIA_SKIP_BACKWARD = 273, KEYCODE_MEDIA_SKIP_FORWARD = 272, KEYCODE_MEDIA_STEP_BACKWARD = 275, KEYCODE_MEDIA_STEP_FORWARD = 274, KEYCODE_MEDIA_STOP = 86, KEYCODE_MEDIA_TOP_MENU = 226;
  public static final int KEYCODE_META_LEFT = 117, KEYCODE_META_RIGHT = 118, KEYCODE_MINUS = 69, KEYCODE_MOVE_END = 123, KEYCODE_MOVE_HOME = 122, KEYCODE_MUHENKAN = 213;
  public static final int KEYCODE_MUSIC = 209, KEYCODE_MUTE = 91, KEYCODE_N = 42, KEYCODE_NAVIGATE_IN = 262, KEYCODE_NAVIGATE_NEXT = 261, KEYCODE_NAVIGATE_OUT = 263;
  public static final int KEYCODE_NAVIGATE_PREVIOUS = 260, KEYCODE_NOTIFICATION = 83, KEYCODE_NUM = 78, KEYCODE_NUMPAD_0 = 144, KEYCODE_NUMPAD_1 = 145, KEYCODE_NUMPAD_2 = 146;
  public static final int KEYCODE_NUMPAD_3 = 147, KEYCODE_NUMPAD_4 = 148, KEYCODE_NUMPAD_5 = 149, KEYCODE_NUMPAD_6 = 150, KEYCODE_NUMPAD_7 = 151, KEYCODE_NUMPAD_8 = 152;
  public static final int KEYCODE_NUMPAD_9 = 153, KEYCODE_NUMPAD_ADD = 157, KEYCODE_NUMPAD_COMMA = 159, KEYCODE_NUMPAD_DIVIDE = 154, KEYCODE_NUMPAD_DOT = 158, KEYCODE_NUMPAD_ENTER = 160;
  public static final int KEYCODE_NUMPAD_EQUALS = 161, KEYCODE_NUMPAD_LEFT_PAREN = 162, KEYCODE_NUMPAD_MULTIPLY = 155, KEYCODE_NUMPAD_RIGHT_PAREN = 163, KEYCODE_NUMPAD_SUBTRACT = 156, KEYCODE_NUM_LOCK = 143;
  public static final int KEYCODE_O = 43, KEYCODE_P = 44, KEYCODE_PAGE_DOWN = 93, KEYCODE_PAGE_UP = 92, KEYCODE_PAIRING = 225, KEYCODE_PASTE = 279;
  public static final int KEYCODE_PICTSYMBOLS = 94, KEYCODE_PLUS = 81, KEYCODE_PROFILE_SWITCH = 288, KEYCODE_PROG_BLUE = 186, KEYCODE_PROG_GREEN = 184, KEYCODE_PROG_RED = 183;
  public static final int KEYCODE_PROG_YELLOW = 185, KEYCODE_Q = 45, KEYCODE_R = 46, KEYCODE_RECENT_APPS = 312, KEYCODE_REFRESH = 285, KEYCODE_RIGHT_BRACKET = 72;
  public static final int KEYCODE_RO = 217, KEYCODE_S = 47, KEYCODE_SCREENSHOT = 318, KEYCODE_SCROLL_LOCK = 116, KEYCODE_SEMICOLON = 74, KEYCODE_SETTINGS = 176;
  public static final int KEYCODE_SHIFT_LEFT = 59, KEYCODE_SHIFT_RIGHT = 60, KEYCODE_SLASH = 76, KEYCODE_SLEEP = 223, KEYCODE_SOFT_SLEEP = 276, KEYCODE_STB_INPUT = 180;
  public static final int KEYCODE_STB_POWER = 179, KEYCODE_STEM_1 = 265, KEYCODE_STEM_2 = 266, KEYCODE_STEM_3 = 267, KEYCODE_STEM_PRIMARY = 264, KEYCODE_STYLUS_BUTTON_PRIMARY = 308;
  public static final int KEYCODE_STYLUS_BUTTON_SECONDARY = 309, KEYCODE_STYLUS_BUTTON_TAIL = 311, KEYCODE_STYLUS_BUTTON_TERTIARY = 310, KEYCODE_SWITCH_CHARSET = 95, KEYCODE_SYM = 63, KEYCODE_SYSRQ = 120;
  public static final int KEYCODE_SYSTEM_NAVIGATION_DOWN = 281, KEYCODE_SYSTEM_NAVIGATION_LEFT = 282, KEYCODE_SYSTEM_NAVIGATION_RIGHT = 283, KEYCODE_SYSTEM_NAVIGATION_UP = 280, KEYCODE_T = 48, KEYCODE_THUMBS_DOWN = 287;
  public static final int KEYCODE_THUMBS_UP = 286, KEYCODE_TV = 170, KEYCODE_TV_ANTENNA_CABLE = 242, KEYCODE_TV_AUDIO_DESCRIPTION = 252, KEYCODE_TV_AUDIO_DESCRIPTION_MIX_DOWN = 254, KEYCODE_TV_AUDIO_DESCRIPTION_MIX_UP = 253;
  public static final int KEYCODE_TV_CONTENTS_MENU = 256, KEYCODE_TV_DATA_SERVICE = 230, KEYCODE_TV_INPUT = 178, KEYCODE_TV_INPUT_COMPONENT_1 = 249, KEYCODE_TV_INPUT_COMPONENT_2 = 250, KEYCODE_TV_INPUT_COMPOSITE_1 = 247;
  public static final int KEYCODE_TV_INPUT_COMPOSITE_2 = 248, KEYCODE_TV_INPUT_HDMI_1 = 243, KEYCODE_TV_INPUT_HDMI_2 = 244, KEYCODE_TV_INPUT_HDMI_3 = 245, KEYCODE_TV_INPUT_HDMI_4 = 246, KEYCODE_TV_INPUT_VGA_1 = 251;
  public static final int KEYCODE_TV_MEDIA_CONTEXT_MENU = 257, KEYCODE_TV_NETWORK = 241, KEYCODE_TV_NUMBER_ENTRY = 234, KEYCODE_TV_POWER = 177, KEYCODE_TV_RADIO_SERVICE = 232, KEYCODE_TV_SATELLITE = 237;
  public static final int KEYCODE_TV_SATELLITE_BS = 238, KEYCODE_TV_SATELLITE_CS = 239, KEYCODE_TV_SATELLITE_SERVICE = 240, KEYCODE_TV_TELETEXT = 233, KEYCODE_TV_TERRESTRIAL_ANALOG = 235, KEYCODE_TV_TERRESTRIAL_DIGITAL = 236;
  public static final int KEYCODE_TV_TIMER_PROGRAMMING = 258, KEYCODE_TV_ZOOM_MODE = 255, KEYCODE_U = 49, KEYCODE_V = 50, KEYCODE_VIDEO_APP_1 = 289, KEYCODE_VIDEO_APP_2 = 290;
  public static final int KEYCODE_VIDEO_APP_3 = 291, KEYCODE_VIDEO_APP_4 = 292, KEYCODE_VIDEO_APP_5 = 293, KEYCODE_VIDEO_APP_6 = 294, KEYCODE_VIDEO_APP_7 = 295, KEYCODE_VIDEO_APP_8 = 296;
  public static final int KEYCODE_VOICE_ASSIST = 231, KEYCODE_VOLUME_MUTE = 164, KEYCODE_W = 51, KEYCODE_WAKEUP = 224, KEYCODE_WINDOW = 171, KEYCODE_X = 52;
  public static final int KEYCODE_Y = 53, KEYCODE_YEN = 216, KEYCODE_ZENKAKU_HANKAKU = 211, KEYCODE_ZOOM_IN = 168, KEYCODE_ZOOM_OUT = 169, MAX_KEYCODE = 84;
  public static final int META_ALT_LEFT_ON = 16, META_ALT_MASK = 50, META_ALT_RIGHT_ON = 32, META_CAPS_LOCK_ON = 1048576, META_CTRL_LEFT_ON = 8192, META_CTRL_MASK = 28672;
  public static final int META_CTRL_RIGHT_ON = 16384, META_FUNCTION_ON = 8, META_META_LEFT_ON = 131072, META_META_MASK = 458752, META_META_ON = 65536, META_META_RIGHT_ON = 262144;
  public static final int META_NUM_LOCK_ON = 2097152, META_SCROLL_LOCK_ON = 4194304, META_SHIFT_LEFT_ON = 64, META_SHIFT_MASK = 193, META_SHIFT_RIGHT_ON = 128;

  public static final int KEYCODE_UNKNOWN = 0, KEYCODE_SOFT_LEFT = 1, KEYCODE_SOFT_RIGHT = 2, KEYCODE_HOME = 3, KEYCODE_BACK = 4, KEYCODE_CALL = 5,
      KEYCODE_ENDCALL = 6, KEYCODE_0 = 7, KEYCODE_9 = 16, KEYCODE_STAR = 17, KEYCODE_POUND = 18, KEYCODE_DPAD_UP = 19, KEYCODE_DPAD_DOWN = 20,
      KEYCODE_DPAD_LEFT = 21, KEYCODE_DPAD_RIGHT = 22, KEYCODE_DPAD_CENTER = 23, KEYCODE_VOLUME_UP = 24, KEYCODE_VOLUME_DOWN = 25, KEYCODE_POWER = 26,
      KEYCODE_CAMERA = 27, KEYCODE_CLEAR = 28, KEYCODE_A = 29, KEYCODE_Z = 54, KEYCODE_COMMA = 55, KEYCODE_PERIOD = 56, KEYCODE_TAB = 61,
      KEYCODE_SPACE = 62, KEYCODE_ENTER = 66, KEYCODE_DEL = 67, KEYCODE_MENU = 82, KEYCODE_SEARCH = 84, KEYCODE_ESCAPE = 111, KEYCODE_FORWARD_DEL = 112;
  public static final int ACTION_DOWN = 0, ACTION_UP = 1, ACTION_MULTIPLE = 2;
  public static final int FLAG_WOKE_HERE = 1, FLAG_SOFT_KEYBOARD = 2, FLAG_KEEP_TOUCH_MODE = 4, FLAG_FROM_SYSTEM = 8, FLAG_EDITOR_ACTION = 16,
      FLAG_CANCELED = 32, FLAG_VIRTUAL_HARD_KEY = 64, FLAG_LONG_PRESS = 128, FLAG_CANCELED_LONG_PRESS = 256, FLAG_TRACKING = 512;
  public static final int META_ALT_ON = 2, META_SHIFT_ON = 1, META_SYM_ON = 4, META_CTRL_ON = 0x1000;

  public interface Callback {
    boolean onKeyDown(int keyCode, KeyEvent event);
    boolean onKeyLongPress(int keyCode, KeyEvent event);
    boolean onKeyUp(int keyCode, KeyEvent event);
    boolean onKeyMultiple(int keyCode, int count, KeyEvent event);
  }
  public static class DispatcherState {
    int downKey; Object downTarget;
    public void reset() { downKey = 0; downTarget = null; }
    public void reset(Object target) { if (downTarget == target) reset(); }
    public void startTracking(KeyEvent e, Object target) { downKey = e.getKeyCode(); downTarget = target; }
    public boolean isTracking(KeyEvent e) { return downKey == e.getKeyCode(); }
    public void performedLongPress(KeyEvent e) {}
    public void handleUpEvent(KeyEvent e) { if (downKey == e.getKeyCode()) { e.flags |= FLAG_TRACKING; reset(); } }
  }


  int action, code, repeat, meta, flags, scanCode, deviceId, source = InputDevice.SOURCE_KEYBOARD;
  long downTime, eventTime;
  String characters;
  private static final int META_CAP_LOCKED = 0x100, META_ALT_LOCKED = 0x200, META_SYM_LOCKED = 0x400, META_SELECTING = 0x800;
  private static final int META_MODIFIER_MASK = META_SHIFT_ON | META_SHIFT_LEFT_ON | META_SHIFT_RIGHT_ON | META_ALT_ON | META_ALT_LEFT_ON | META_ALT_RIGHT_ON
      | META_CTRL_ON | META_CTRL_LEFT_ON | META_CTRL_RIGHT_ON | META_META_ON | META_META_LEFT_ON | META_META_RIGHT_ON | META_SYM_ON | META_FUNCTION_ON;
  private static final int META_LOCK_MASK = META_CAPS_LOCK_ON | META_NUM_LOCK_ON | META_SCROLL_LOCK_ON;
  private static final int META_ALL_MASK = META_MODIFIER_MASK | META_LOCK_MASK;
  private static final int META_INVALID_MODIFIER_MASK = META_LOCK_MASK | META_CAP_LOCKED | META_ALT_LOCKED | META_SYM_LOCKED | META_SELECTING;
  private static final String[] NAMES = {"UNKNOWN", "SOFT_LEFT", "SOFT_RIGHT", "HOME", "BACK", "CALL", "ENDCALL", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "STAR", "POUND", "DPAD_UP", "DPAD_DOWN", "DPAD_LEFT", "DPAD_RIGHT", "DPAD_CENTER", "VOLUME_UP", "VOLUME_DOWN", "POWER", "CAMERA", "CLEAR", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "COMMA", "PERIOD", "ALT_LEFT", "ALT_RIGHT", "SHIFT_LEFT", "SHIFT_RIGHT", "TAB", "SPACE", "SYM", "EXPLORER", "ENVELOPE", "ENTER", "DEL", "GRAVE", "MINUS", "EQUALS", "LEFT_BRACKET", "RIGHT_BRACKET", "BACKSLASH", "SEMICOLON", "APOSTROPHE", "SLASH", "AT", "NUM", "HEADSETHOOK", "FOCUS", "PLUS", "MENU", "NOTIFICATION", "SEARCH", "MEDIA_PLAY_PAUSE", "MEDIA_STOP", "MEDIA_NEXT", "MEDIA_PREVIOUS", "MEDIA_REWIND", "MEDIA_FAST_FORWARD", "MUTE", "PAGE_UP", "PAGE_DOWN", "PICTSYMBOLS", "SWITCH_CHARSET", "BUTTON_A", "BUTTON_B", "BUTTON_C", "BUTTON_X", "BUTTON_Y", "BUTTON_Z", "BUTTON_L1", "BUTTON_R1", "BUTTON_L2", "BUTTON_R2", "BUTTON_THUMBL", "BUTTON_THUMBR", "BUTTON_START", "BUTTON_SELECT", "BUTTON_MODE", "ESCAPE", "FORWARD_DEL", "CTRL_LEFT", "CTRL_RIGHT", "CAPS_LOCK", "SCROLL_LOCK", "META_LEFT", "META_RIGHT", "FUNCTION", "SYSRQ", "BREAK", "MOVE_HOME", "MOVE_END", "INSERT", "FORWARD", "MEDIA_PLAY", "MEDIA_PAUSE", "MEDIA_CLOSE", "MEDIA_EJECT", "MEDIA_RECORD", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "NUM_LOCK", "NUMPAD_0", "NUMPAD_1", "NUMPAD_2", "NUMPAD_3", "NUMPAD_4", "NUMPAD_5", "NUMPAD_6", "NUMPAD_7", "NUMPAD_8", "NUMPAD_9", "NUMPAD_DIVIDE", "NUMPAD_MULTIPLY", "NUMPAD_SUBTRACT", "NUMPAD_ADD", "NUMPAD_DOT", "NUMPAD_COMMA", "NUMPAD_ENTER", "NUMPAD_EQUALS", "NUMPAD_LEFT_PAREN", "NUMPAD_RIGHT_PAREN", "VOLUME_MUTE", "INFO", "CHANNEL_UP", "CHANNEL_DOWN", "ZOOM_IN", "ZOOM_OUT", "TV", "WINDOW", "GUIDE", "DVR", "BOOKMARK", "CAPTIONS", "SETTINGS", "TV_POWER", "TV_INPUT", "STB_POWER", "STB_INPUT", "AVR_POWER", "AVR_INPUT", "PROG_RED", "PROG_GREEN", "PROG_YELLOW", "PROG_BLUE", "APP_SWITCH", "BUTTON_1", "BUTTON_2", "BUTTON_3", "BUTTON_4", "BUTTON_5", "BUTTON_6", "BUTTON_7", "BUTTON_8", "BUTTON_9", "BUTTON_10", "BUTTON_11", "BUTTON_12", "BUTTON_13", "BUTTON_14", "BUTTON_15", "BUTTON_16", "LANGUAGE_SWITCH", "MANNER_MODE", "3D_MODE", "CONTACTS", "CALENDAR", "MUSIC", "CALCULATOR", "ZENKAKU_HANKAKU", "EISU", "MUHENKAN", "HENKAN", "KATAKANA_HIRAGANA", "YEN", "RO", "KANA", "ASSIST", "BRIGHTNESS_DOWN", "BRIGHTNESS_UP", "MEDIA_AUDIO_TRACK", "SLEEP", "WAKEUP", "PAIRING", "MEDIA_TOP_MENU", "11", "12", "LAST_CHANNEL", "TV_DATA_SERVICE", "VOICE_ASSIST", "TV_RADIO_SERVICE", "TV_TELETEXT", "TV_NUMBER_ENTRY", "TV_TERRESTRIAL_ANALOG", "TV_TERRESTRIAL_DIGITAL", "TV_SATELLITE", "TV_SATELLITE_BS", "TV_SATELLITE_CS", "TV_SATELLITE_SERVICE", "TV_NETWORK", "TV_ANTENNA_CABLE", "TV_INPUT_HDMI_1", "TV_INPUT_HDMI_2", "TV_INPUT_HDMI_3", "TV_INPUT_HDMI_4", "TV_INPUT_COMPOSITE_1", "TV_INPUT_COMPOSITE_2", "TV_INPUT_COMPONENT_1", "TV_INPUT_COMPONENT_2", "TV_INPUT_VGA_1", "TV_AUDIO_DESCRIPTION", "TV_AUDIO_DESCRIPTION_MIX_UP", "TV_AUDIO_DESCRIPTION_MIX_DOWN", "TV_ZOOM_MODE", "TV_CONTENTS_MENU", "TV_MEDIA_CONTEXT_MENU", "TV_TIMER_PROGRAMMING", "HELP", "NAVIGATE_PREVIOUS", "NAVIGATE_NEXT", "NAVIGATE_IN", "NAVIGATE_OUT", "STEM_PRIMARY", "STEM_1", "STEM_2", "STEM_3", "DPAD_UP_LEFT", "DPAD_DOWN_LEFT", "DPAD_UP_RIGHT", "DPAD_DOWN_RIGHT", "MEDIA_SKIP_FORWARD", "MEDIA_SKIP_BACKWARD", "MEDIA_STEP_FORWARD", "MEDIA_STEP_BACKWARD", "SOFT_SLEEP", "CUT", "COPY", "PASTE", "SYSTEM_NAVIGATION_UP", "SYSTEM_NAVIGATION_DOWN", "SYSTEM_NAVIGATION_LEFT", "SYSTEM_NAVIGATION_RIGHT", "ALL_APPS", "REFRESH", "THUMBS_UP", "THUMBS_DOWN", "PROFILE_SWITCH", "VIDEO_APP_1", "VIDEO_APP_2", "VIDEO_APP_3", "VIDEO_APP_4", "VIDEO_APP_5", "VIDEO_APP_6", "VIDEO_APP_7", "VIDEO_APP_8", "FEATURED_APP_1", "FEATURED_APP_2", "FEATURED_APP_3", "FEATURED_APP_4", "DEMO_APP_1", "DEMO_APP_2", "DEMO_APP_3", "DEMO_APP_4", "KEYBOARD_BACKLIGHT_DOWN", "KEYBOARD_BACKLIGHT_UP", "KEYBOARD_BACKLIGHT_TOGGLE", "STYLUS_BUTTON_PRIMARY", "STYLUS_BUTTON_SECONDARY", "STYLUS_BUTTON_TERTIARY", "STYLUS_BUTTON_TAIL", "RECENT_APPS", "MACRO_1", "MACRO_2", "MACRO_3", "MACRO_4", "EMOJI_PICKER", "SCREENSHOT"};

  public KeyEvent(int action, int code) { this.action = action; this.code = code; downTime = eventTime = android.os.SystemClock.uptimeMillis(); }
  public KeyEvent(long downTime, long eventTime, int action, int code, int repeat) { this(action, code); this.downTime = downTime; this.eventTime = eventTime; this.repeat = repeat; }
  public KeyEvent(long downTime, long eventTime, int action, int code, int repeat, int meta) { this(downTime, eventTime, action, code, repeat); this.meta = meta; }
  public KeyEvent(long downTime, long eventTime, int action, int code, int repeat, int meta, int deviceId, int scancode) { this(downTime, eventTime, action, code, repeat, meta); this.deviceId = deviceId; scanCode = scancode; }
  public KeyEvent(long downTime, long eventTime, int action, int code, int repeat, int meta, int device, int scan, int flags) { this(downTime, eventTime, action, code, repeat, meta, device, scan); this.flags = flags; }
  public KeyEvent(long downTime, long eventTime, int action, int code, int repeat, int meta, int device, int scan, int flags, int source) { this(downTime, eventTime, action, code, repeat, meta, device, scan, flags); this.source = source; }
  public KeyEvent(long time, String characters, int device, int flags) { this(time, time, ACTION_MULTIPLE, KEYCODE_UNKNOWN, 0, 0, device, 0, flags); this.characters = characters; }
  public KeyEvent(KeyEvent o) {
    action = o.action; code = o.code; repeat = o.repeat; meta = o.meta; flags = o.flags; scanCode = o.scanCode; deviceId = o.deviceId; source = o.source;
    downTime = o.downTime; eventTime = o.eventTime; characters = o.characters;
  }
  @Deprecated public KeyEvent(KeyEvent origEvent, long eventTime, int newRepeat) { this(origEvent); this.eventTime = eventTime; repeat = newRepeat; }
  public static KeyEvent changeAction(KeyEvent e, int action) { KeyEvent k = new KeyEvent(e); k.action = action; return k; }
  public static KeyEvent changeFlags(KeyEvent e, int flags) { KeyEvent k = new KeyEvent(e); k.flags = flags; return k; }
  public static KeyEvent changeTimeRepeat(KeyEvent e, long eventTime, int newRepeat) { KeyEvent k = new KeyEvent(e); k.eventTime = eventTime; k.repeat = newRepeat; return k; }
  public static KeyEvent changeTimeRepeat(KeyEvent e, long eventTime, int newRepeat, int newFlags) { KeyEvent k = changeTimeRepeat(e, eventTime, newRepeat); k.flags = newFlags; return k; }

  public final int getAction() { return action; }
  public final int getKeyCode() { return code; }
  public final int getRepeatCount() { return repeat; }
  public final int getMetaState() { return meta; }
  public final int getModifiers() { return normalizeMetaState(meta) & META_MODIFIER_MASK; }
  public final int getFlags() { return flags; }
  public final int getScanCode() { return scanCode; }
  public final int getDeviceId() { return deviceId; }
  public final int getSource() { return source; }
  public final void setSource(int s) { source = s; }
  public final long getDownTime() { return downTime; }
  public final long getEventTime() { return eventTime; }
  public final String getCharacters() { return characters; }
  public final KeyCharacterMap getKeyCharacterMap() { return KeyCharacterMap.load(deviceId); }
  public final boolean isCanceled() { return (flags & FLAG_CANCELED) != 0; }
  public final void startTracking() { flags |= FLAG_TRACKING; }
  public final boolean isTracking() { return (flags & FLAG_TRACKING) != 0; }
  public final boolean isLongPress() { return (flags & FLAG_LONG_PRESS) != 0; }
  public final boolean isShiftPressed() { return (meta & META_SHIFT_ON) != 0; }
  public final boolean isAltPressed() { return (meta & META_ALT_ON) != 0; }
  public final boolean isCtrlPressed() { return (meta & META_CTRL_ON) != 0; }
  public final boolean isMetaPressed() { return (meta & META_META_ON) != 0; }
  public final boolean isSymPressed() { return (meta & META_SYM_ON) != 0; }
  public final boolean isFunctionPressed() { return (meta & META_FUNCTION_ON) != 0; }
  public final boolean isCapsLockOn() { return (meta & META_CAPS_LOCK_ON) != 0; }
  public final boolean isNumLockOn() { return (meta & META_NUM_LOCK_ON) != 0; }
  public final boolean isScrollLockOn() { return (meta & META_SCROLL_LOCK_ON) != 0; }
  public final boolean hasNoModifiers() { return metaStateHasNoModifiers(meta); }
  public final boolean hasModifiers(int modifiers) { return metaStateHasModifiers(meta, modifiers); }
  public final boolean isSystem() { return isSystemKey(code); }
  static boolean isSystemKey(int k) {
    switch (k) {
      case KEYCODE_MENU: case KEYCODE_SOFT_RIGHT: case KEYCODE_HOME: case KEYCODE_BACK: case KEYCODE_CALL: case KEYCODE_ENDCALL: case KEYCODE_VOLUME_UP:
      case KEYCODE_VOLUME_DOWN: case KEYCODE_VOLUME_MUTE: case KEYCODE_MUTE: case KEYCODE_POWER: case KEYCODE_HEADSETHOOK: case KEYCODE_MEDIA_PLAY:
      case KEYCODE_MEDIA_PAUSE: case KEYCODE_MEDIA_PLAY_PAUSE: case KEYCODE_MEDIA_STOP: case KEYCODE_MEDIA_NEXT: case KEYCODE_MEDIA_PREVIOUS:
      case KEYCODE_MEDIA_REWIND: case KEYCODE_MEDIA_RECORD: case KEYCODE_MEDIA_FAST_FORWARD: case KEYCODE_CAMERA: case KEYCODE_FOCUS: case KEYCODE_SEARCH:
      case KEYCODE_BRIGHTNESS_DOWN: case KEYCODE_BRIGHTNESS_UP: case KEYCODE_MEDIA_AUDIO_TRACK: return true;
      default: return false;
    }
  }
  public boolean isPrintingKey() { int c = KeyCharacterMap.INSTANCE.get(code, 0); return c != 0 && !Character.isISOControl(c) || code == KEYCODE_SPACE; }
  public int getUnicodeChar() { return getUnicodeChar(meta); }
  public int getUnicodeChar(int metaState) { return KeyCharacterMap.INSTANCE.get(code, metaState); }
  public char getNumber() { return KeyCharacterMap.INSTANCE.getNumber(code); }
  public char getMatch(char[] chars) { return getMatch(chars, 0); }
  public char getMatch(char[] chars, int metaState) { return KeyCharacterMap.INSTANCE.getMatch(code, chars, metaState); }
  public char getDisplayLabel() { return KeyCharacterMap.INSTANCE.getDisplayLabel(code); }
  @Deprecated public boolean getKeyData(KeyCharacterMap.KeyData results) { return KeyCharacterMap.INSTANCE.getKeyData(code, results); }
  public static int getDeadChar(int accent, int c) { return KeyCharacterMap.getDeadChar(accent, c); }

  // ---- meta states
  public static int getModifierMetaStateMask() { return META_MODIFIER_MASK; }
  public static boolean isModifierKey(int keyCode) {
    switch (keyCode) {
      case KEYCODE_SHIFT_LEFT: case KEYCODE_SHIFT_RIGHT: case KEYCODE_ALT_LEFT: case KEYCODE_ALT_RIGHT: case KEYCODE_CTRL_LEFT: case KEYCODE_CTRL_RIGHT:
      case KEYCODE_META_LEFT: case KEYCODE_META_RIGHT: case KEYCODE_SYM: case KEYCODE_NUM: case KEYCODE_FUNCTION: return true;
      default: return false;
    }
  }
  public static int normalizeMetaState(int metaState) {
    if ((metaState & (META_SHIFT_LEFT_ON | META_SHIFT_RIGHT_ON)) != 0) metaState |= META_SHIFT_ON;
    if ((metaState & (META_ALT_LEFT_ON | META_ALT_RIGHT_ON)) != 0) metaState |= META_ALT_ON;
    if ((metaState & (META_CTRL_LEFT_ON | META_CTRL_RIGHT_ON)) != 0) metaState |= META_CTRL_ON;
    if ((metaState & (META_META_LEFT_ON | META_META_RIGHT_ON)) != 0) metaState |= META_META_ON;
    if ((metaState & META_CAP_LOCKED) != 0) metaState |= META_CAPS_LOCK_ON;
    if ((metaState & META_ALT_LOCKED) != 0) metaState |= META_ALT_ON;
    if ((metaState & META_SYM_LOCKED) != 0) metaState |= META_SYM_ON;
    return metaState & META_ALL_MASK;
  }
  public static boolean metaStateHasNoModifiers(int metaState) { return (normalizeMetaState(metaState) & META_MODIFIER_MASK) == 0; }
  public static boolean metaStateHasModifiers(int metaState, int modifiers) {
    if ((modifiers & META_INVALID_MODIFIER_MASK) != 0)
      throw new IllegalArgumentException("modifiers must not contain META_CAPS_LOCK_ON, META_NUM_LOCK_ON, META_SCROLL_LOCK_ON, META_CAP_LOCKED, META_ALT_LOCKED, META_SYM_LOCKED, or META_SELECTING");
    metaState = normalizeMetaState(metaState) & META_MODIFIER_MASK;
    metaState = filterDirectional(metaState, modifiers, META_SHIFT_ON, META_SHIFT_LEFT_ON, META_SHIFT_RIGHT_ON);
    metaState = filterDirectional(metaState, modifiers, META_ALT_ON, META_ALT_LEFT_ON, META_ALT_RIGHT_ON);
    metaState = filterDirectional(metaState, modifiers, META_CTRL_ON, META_CTRL_LEFT_ON, META_CTRL_RIGHT_ON);
    metaState = filterDirectional(metaState, modifiers, META_META_ON, META_META_LEFT_ON, META_META_RIGHT_ON);
    return metaState == modifiers;
  }
  private static int filterDirectional(int metaState, int modifiers, int basic, int left, int right) {
    boolean wantBasic = (modifiers & basic) != 0;
    int directional = left | right;
    boolean wantLeftOrRight = (modifiers & directional) != 0;
    if (wantBasic) {
      if (wantLeftOrRight) throw new IllegalArgumentException("modifiers must not contain " + metaStateToString(basic) + " combined with " + metaStateToString(left) + " or " + metaStateToString(right));
      return metaState & ~directional;
    }
    return wantLeftOrRight ? metaState & ~basic : metaState;
  }
  private static final String[] META_NAMES = {"META_SHIFT_ON", "META_ALT_ON", "META_SYM_ON", "META_FUNCTION_ON", "META_ALT_LEFT_ON", "META_ALT_RIGHT_ON",
      "META_SHIFT_LEFT_ON", "META_SHIFT_RIGHT_ON", "META_CAP_LOCKED", "META_ALT_LOCKED", "META_SYM_LOCKED", "0x00000800", "META_CTRL_ON", "META_CTRL_LEFT_ON",
      "META_CTRL_RIGHT_ON", "0x00008000", "META_META_ON", "META_META_LEFT_ON", "META_META_RIGHT_ON", "0x00080000", "META_CAPS_LOCK_ON", "META_NUM_LOCK_ON",
      "META_SCROLL_LOCK_ON"};
  /** (hidden API) the meta flags' names joined by '|', or "0" */
  public static String metaStateToString(int metaState) {
    if (metaState == 0) return "0";
    StringBuilder b = new StringBuilder();
    for (int i = 0; metaState != 0; i++) {
      if ((metaState & 1) != 0) { if (b.length() > 0) b.append('|'); b.append(i < META_NAMES.length ? META_NAMES[i] : String.format("0x%08x", 1 << i)); }
      metaState >>>= 1;
    }
    return b.toString();
  }

  // ---- key codes
  public static String keyCodeToString(int keyCode) {
    String n = keyCode >= 0 && keyCode < NAMES.length ? NAMES[keyCode] : null;
    return n != null ? "KEYCODE_" + n : Integer.toString(keyCode);
  }
  public static int keyCodeFromString(String symbolicName) {
    try { int k = Integer.parseInt(symbolicName); if (k >= 0 && k < NAMES.length) return k; } catch (NumberFormatException ignored) {}
    if (symbolicName.startsWith("KEYCODE_")) symbolicName = symbolicName.substring(8);
    for (int i = 0; i < NAMES.length; i++) if (symbolicName.equals(NAMES[i])) return i;
    return KEYCODE_UNKNOWN;
  }
  public static int getMaxKeyCode() { return NAMES.length - 1; }
  public static boolean isGamepadButton(int k) {
    return (k >= KEYCODE_BUTTON_A && k <= KEYCODE_BUTTON_MODE) || (k >= KEYCODE_BUTTON_1 && k <= KEYCODE_BUTTON_16);
  }
  public static boolean isMediaSessionKey(int k) {
    switch (k) {
      case KEYCODE_MEDIA_PLAY: case KEYCODE_MEDIA_PAUSE: case KEYCODE_MEDIA_PLAY_PAUSE: case KEYCODE_MUTE: case KEYCODE_HEADSETHOOK: case KEYCODE_MEDIA_STOP:
      case KEYCODE_MEDIA_NEXT: case KEYCODE_MEDIA_PREVIOUS: case KEYCODE_MEDIA_REWIND: case KEYCODE_MEDIA_RECORD: case KEYCODE_MEDIA_FAST_FORWARD: return true;
      default: return false;
    }
  }

  public final boolean dispatch(Callback r) { return dispatch(r, null, null); }
  public final boolean dispatch(Callback r, DispatcherState state, Object target) {
    switch (action) {
      case ACTION_DOWN: {
        flags &= ~FLAG_TRACKING;
        boolean res = r.onKeyDown(code, this);
        if (state != null) {
          if (res && repeat == 0 && (flags & FLAG_TRACKING) != 0) state.startTracking(this, target);
          else if (isLongPress() && state.isTracking(this)) { if (r.onKeyLongPress(code, this)) { state.performedLongPress(this); res = true; } }
        }
        return res;
      }
      case ACTION_UP:
        if (state != null) state.handleUpEvent(this);
        return r.onKeyUp(code, this);
      case ACTION_MULTIPLE:
        if (r.onKeyMultiple(code, repeat, this)) return true;
        if (code != KEYCODE_UNKNOWN) {
          int count = repeat;
          action = ACTION_DOWN;
          repeat = 0;
          boolean handled = r.onKeyDown(code, this);
          if (handled) { action = ACTION_UP; r.onKeyUp(code, this); }
          action = ACTION_MULTIPLE;
          repeat = count;
          return handled;
        }
        return false;
    }
    return false;
  }
  @Override public String toString() {
    return "KeyEvent { action=" + actionToString(action) + ", keyCode=" + keyCodeToString(code) + ", scanCode=" + scanCode
        + (characters != null ? ", characters=\"" + characters + "\"" : "") + ", metaState=" + metaStateToString(meta) + ", flags=0x" + Integer.toHexString(flags)
        + ", repeatCount=" + repeat + ", eventTime=" + eventTime + ", downTime=" + downTime + ", deviceId=" + deviceId + ", source=0x" + Integer.toHexString(source) + ", displayId=0 }";
  }
  /** (hidden API) */
  public static String actionToString(int action) {
    switch (action) { case ACTION_DOWN: return "ACTION_DOWN"; case ACTION_UP: return "ACTION_UP"; case ACTION_MULTIPLE: return "ACTION_MULTIPLE"; default: return Integer.toString(action); }
  }
  @Override public void writeToParcel(android.os.Parcel out, int f) {
    out.writeInt(deviceId); out.writeInt(source); out.writeInt(action); out.writeInt(code); out.writeInt(repeat); out.writeInt(meta);
    out.writeInt(scanCode); out.writeInt(flags); out.writeLong(downTime); out.writeLong(eventTime); out.writeString(characters);
  }
  public static final Creator<KeyEvent> CREATOR = new Creator<KeyEvent>() {
    public KeyEvent createFromParcel(android.os.Parcel in) {
      int dev = in.readInt(), src = in.readInt(), act = in.readInt(), c = in.readInt(), rep = in.readInt(), m = in.readInt(), sc = in.readInt(), fl = in.readInt();
      long dt = in.readLong(), et = in.readLong();
      KeyEvent e = new KeyEvent(dt, et, act, c, rep, m, dev, sc, fl, src);
      e.characters = in.readString();
      return e;
    }
    public KeyEvent[] newArray(int size) { return new KeyEvent[size]; }
  };
}

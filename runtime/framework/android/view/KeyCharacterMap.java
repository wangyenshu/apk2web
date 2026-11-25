package android.view;

/** android.view.KeyCharacterMap: a full keyboard with the US layout (Android's Generic.kcm, without the alt layer). */
public class KeyCharacterMap implements android.os.Parcelable {
  public static final int BUILT_IN_KEYBOARD = 0, VIRTUAL_KEYBOARD = -1, NUMERIC = 1, PREDICTIVE = 2, ALPHA = 3, FULL = 4, SPECIAL_FUNCTION = 5;
  public static final char HEX_INPUT = '', PICKER_DIALOG_INPUT = '';
  public static final int MODIFIER_BEHAVIOR_CHORDED = 0, MODIFIER_BEHAVIOR_CHORDED_OR_TOGGLED = 1;
  public static final int COMBINING_ACCENT = 0x80000000, COMBINING_ACCENT_MASK = 0x7FFFFFFF;
  static final KeyCharacterMap INSTANCE = new KeyCharacterMap();
  public static class KeyData { public static final int META_LENGTH = 4; public char displayLabel, number; public char[] meta = new char[META_LENGTH]; public KeyData() {} }
  public static class UnavailableException extends android.util.AndroidRuntimeException { public UnavailableException(String msg) { super(msg); } }
  protected KeyCharacterMap() {}
  public static KeyCharacterMap load(int deviceId) { return INSTANCE; }
  public int getKeyboardType() { return FULL; }
  public int getModifierBehavior() { return MODIFIER_BEHAVIOR_CHORDED; }
  static final String DIGITS_SHIFTED = ")!@#$%^&*(";

  /** the character of a key with the meta state (shift, caps lock, num lock), or 0 */
  public int get(int keyCode, int metaState) {
    metaState = KeyEvent.normalizeMetaState(metaState);
    boolean shift = (metaState & KeyEvent.META_SHIFT_ON) != 0, caps = (metaState & KeyEvent.META_CAPS_LOCK_ON) != 0;
    boolean num = (metaState & KeyEvent.META_NUM_LOCK_ON) != 0;
    if (keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z) { char c = (char) ('a' + keyCode - KeyEvent.KEYCODE_A); return shift != caps ? Character.toUpperCase(c) : c; }
    if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) return shift ? DIGITS_SHIFTED.charAt(keyCode - KeyEvent.KEYCODE_0) : '0' + keyCode - KeyEvent.KEYCODE_0;
    if (keyCode >= KeyEvent.KEYCODE_NUMPAD_0 && keyCode <= KeyEvent.KEYCODE_NUMPAD_9) return num ? '0' + keyCode - KeyEvent.KEYCODE_NUMPAD_0 : 0;
    switch (keyCode) {
      case KeyEvent.KEYCODE_SPACE: return ' ';
      case KeyEvent.KEYCODE_ENTER: case KeyEvent.KEYCODE_NUMPAD_ENTER: return '\n';
      case KeyEvent.KEYCODE_TAB: return '\t';
      case KeyEvent.KEYCODE_COMMA: return shift ? '<' : ',';
      case KeyEvent.KEYCODE_PERIOD: return shift ? '>' : '.';
      case KeyEvent.KEYCODE_MINUS: return shift ? '_' : '-';
      case KeyEvent.KEYCODE_EQUALS: return shift ? '+' : '=';
      case KeyEvent.KEYCODE_SLASH: return shift ? '?' : '/';
      case KeyEvent.KEYCODE_SEMICOLON: return shift ? ':' : ';';
      case KeyEvent.KEYCODE_APOSTROPHE: return shift ? '"' : '\'';
      case KeyEvent.KEYCODE_LEFT_BRACKET: return shift ? '{' : '[';
      case KeyEvent.KEYCODE_RIGHT_BRACKET: return shift ? '}' : ']';
      case KeyEvent.KEYCODE_BACKSLASH: return shift ? '|' : '\\';
      case KeyEvent.KEYCODE_GRAVE: return shift ? '~' : '`';
      case KeyEvent.KEYCODE_AT: return '@';
      case KeyEvent.KEYCODE_PLUS: return '+';
      case KeyEvent.KEYCODE_STAR: return '*';
      case KeyEvent.KEYCODE_POUND: return '#';
      case KeyEvent.KEYCODE_NUMPAD_DIVIDE: return '/';
      case KeyEvent.KEYCODE_NUMPAD_MULTIPLY: return '*';
      case KeyEvent.KEYCODE_NUMPAD_SUBTRACT: return '-';
      case KeyEvent.KEYCODE_NUMPAD_ADD: return '+';
      case KeyEvent.KEYCODE_NUMPAD_DOT: return num ? '.' : 0;
      case KeyEvent.KEYCODE_NUMPAD_COMMA: return ',';
      case KeyEvent.KEYCODE_NUMPAD_EQUALS: return '=';
      case KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN: return '(';
      case KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN: return ')';
      default: return 0;
    }
  }
  public char getNumber(int keyCode) {
    if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) return (char) ('0' + keyCode - KeyEvent.KEYCODE_0);
    if (keyCode >= KeyEvent.KEYCODE_NUMPAD_0 && keyCode <= KeyEvent.KEYCODE_NUMPAD_9) return (char) ('0' + keyCode - KeyEvent.KEYCODE_NUMPAD_0);
    switch (keyCode) {
      case KeyEvent.KEYCODE_STAR: return '*';
      case KeyEvent.KEYCODE_POUND: return '#';
      case KeyEvent.KEYCODE_PLUS: return '+';
      case KeyEvent.KEYCODE_COMMA: return ',';
      case KeyEvent.KEYCODE_PERIOD: return '.';
      default: return 0;
    }
  }
  public char getMatch(int keyCode, char[] chars) { return getMatch(keyCode, chars, 0); }
  public char getMatch(int keyCode, char[] chars, int metaState) {
    if (chars == null) throw new IllegalArgumentException("chars must not be null.");
    int c = get(keyCode, metaState);
    for (char x : chars) if (x == c) return x;
    int[] metas = {0, KeyEvent.META_SHIFT_ON, KeyEvent.META_NUM_LOCK_ON};
    for (int m : metas) { int d = get(keyCode, m); if (d != 0) for (char x : chars) if (x == d) return x; }
    return 0;
  }
  public char getDisplayLabel(int keyCode) {
    int c = get(keyCode, 0);
    if (c == 0) c = get(keyCode, KeyEvent.META_NUM_LOCK_ON);
    return (char) (Character.isLetter(c) ? Character.toUpperCase(c) : c);
  }
  /** a combining accent applied to a character: the composed character, or 0 */
  public static int getDeadChar(int accent, int c) {
    int combining = accent;
    switch (accent) {
      case '`': combining = 0x0300; break;
      case '´': case '\'': combining = 0x0301; break;
      case '^': combining = 0x0302; break;
      case '~': case '˜': combining = 0x0303; break;
      case '¯': combining = 0x0304; break;
      case '˘': combining = 0x0306; break;
      case '˙': combining = 0x0307; break;
      case '¨': case '"': combining = 0x0308; break;
      case '˚': combining = 0x030a; break;
      case '˝': combining = 0x030b; break;
      case 'ˇ': combining = 0x030c; break;
      case '¸': combining = 0x0327; break;
      case '˛': combining = 0x0328; break;
      default: break;
    }
    if (combining < 0x0300 || combining > 0x036f) return 0;
    if (c == ' ') return accent < 0x0300 ? accent : 0;
    if (c == combining) return accent;
    String s = a2w.jdk.text.Normalizer.normalize(new String(new char[] {(char) c, (char) combining}), a2w.jdk.text.Normalizer.Form.NFC);
    return s.length() == 1 ? s.charAt(0) : 0;
  }
  @Deprecated public boolean getKeyData(int keyCode, KeyData results) {
    if (results.meta.length < KeyData.META_LENGTH) throw new IndexOutOfBoundsException("results.meta.length must be >= " + KeyData.META_LENGTH);
    char label = getDisplayLabel(keyCode);
    if (label == 0) return false;
    results.displayLabel = label;
    results.number = getNumber(keyCode);
    results.meta[0] = (char) get(keyCode, 0);
    results.meta[1] = (char) get(keyCode, KeyEvent.META_SHIFT_ON);
    results.meta[2] = (char) get(keyCode, KeyEvent.META_ALT_ON);
    results.meta[3] = (char) get(keyCode, KeyEvent.META_ALT_ON | KeyEvent.META_SHIFT_ON);
    return true;
  }
  /** key presses typing the characters (shift held where needed), or null when one cannot be typed */
  public KeyEvent[] getEvents(char[] chars) {
    if (chars == null) throw new IllegalArgumentException("chars must not be null.");
    java.util.ArrayList<KeyEvent> out = new java.util.ArrayList<>();
    long now = android.os.SystemClock.uptimeMillis();
    for (char ch : chars) {
      boolean found = false;
      for (int k = 0; k <= KeyEvent.getMaxKeyCode() && !found; k++) {
        boolean plain = get(k, 0) == ch, shifted = !plain && get(k, KeyEvent.META_SHIFT_ON) == ch;
        if (!plain && !shifted) continue;
        int meta = shifted ? KeyEvent.META_SHIFT_ON | KeyEvent.META_SHIFT_LEFT_ON : 0;
        if (shifted) out.add(new KeyEvent(now, now, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT, 0, meta, VIRTUAL_KEYBOARD, 0));
        out.add(new KeyEvent(now, now, KeyEvent.ACTION_DOWN, k, 0, meta, VIRTUAL_KEYBOARD, 0));
        out.add(new KeyEvent(now, now, KeyEvent.ACTION_UP, k, 0, meta, VIRTUAL_KEYBOARD, 0));
        if (shifted) out.add(new KeyEvent(now, now, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT, 0, 0, VIRTUAL_KEYBOARD, 0));
        found = true;
      }
      if (!found) return null;
    }
    return out.toArray(new KeyEvent[0]);
  }
  public boolean isPrintingKey(int keyCode) { int c = get(keyCode, 0); return c != 0 && (c == ' ' || !Character.isISOControl(c)); }
  public static boolean deviceHasKey(int keyCode) { return keyCode >= 0 && keyCode <= KeyEvent.getMaxKeyCode(); }
  public static boolean[] deviceHasKeys(int[] keyCodes) { boolean[] r = new boolean[keyCodes.length]; for (int i = 0; i < r.length; i++) r[i] = deviceHasKey(keyCodes[i]); return r; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel out, int flags) { out.writeInt(FULL); }
  public static final android.os.Parcelable.Creator<KeyCharacterMap> CREATOR = new android.os.Parcelable.Creator<KeyCharacterMap>() {
    public KeyCharacterMap createFromParcel(android.os.Parcel in) { in.readInt(); return INSTANCE; }
    public KeyCharacterMap[] newArray(int size) { return new KeyCharacterMap[size]; }
  };
}

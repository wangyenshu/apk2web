package android.content.res;

import java.util.Locale;

/**
 * A device configuration (as the platform's): locale list, screen size classes and dimensions, density, orientation,
 * UI mode, input devices, color mode. new Configuration() is all undefined (setToDefaults); the device's is
 * Resources.getConfiguration(). diff/updateFrom/compareTo/equals compare every field as Android does.
 */
public final class Configuration implements android.os.Parcelable, Comparable<Configuration> {
  public static final int ORIENTATION_UNDEFINED = 0, ORIENTATION_PORTRAIT = 1, ORIENTATION_LANDSCAPE = 2, ORIENTATION_SQUARE = 3;
  public static final int SCREENLAYOUT_UNDEFINED = 0, SCREENLAYOUT_SIZE_MASK = 0x0f, SCREENLAYOUT_SIZE_UNDEFINED = 0, SCREENLAYOUT_SIZE_SMALL = 1,
      SCREENLAYOUT_SIZE_NORMAL = 2, SCREENLAYOUT_SIZE_LARGE = 3, SCREENLAYOUT_SIZE_XLARGE = 4, SCREENLAYOUT_LONG_MASK = 0x30,
      SCREENLAYOUT_LONG_UNDEFINED = 0, SCREENLAYOUT_LONG_NO = 0x10, SCREENLAYOUT_LONG_YES = 0x20, SCREENLAYOUT_LAYOUTDIR_MASK = 0xc0,
      SCREENLAYOUT_LAYOUTDIR_SHIFT = 6, SCREENLAYOUT_LAYOUTDIR_UNDEFINED = 0, SCREENLAYOUT_LAYOUTDIR_LTR = 0x40, SCREENLAYOUT_LAYOUTDIR_RTL = 0x80,
      SCREENLAYOUT_ROUND_MASK = 0x300, SCREENLAYOUT_ROUND_UNDEFINED = 0, SCREENLAYOUT_ROUND_NO = 0x100, SCREENLAYOUT_ROUND_YES = 0x200;
  public static final int COLOR_MODE_UNDEFINED = 0, COLOR_MODE_WIDE_COLOR_GAMUT_MASK = 0x3, COLOR_MODE_WIDE_COLOR_GAMUT_UNDEFINED = 0,
      COLOR_MODE_WIDE_COLOR_GAMUT_NO = 0x1, COLOR_MODE_WIDE_COLOR_GAMUT_YES = 0x2, COLOR_MODE_HDR_MASK = 0xc, COLOR_MODE_HDR_SHIFT = 2,
      COLOR_MODE_HDR_UNDEFINED = 0, COLOR_MODE_HDR_NO = 0x4, COLOR_MODE_HDR_YES = 0x8;
  public static final int TOUCHSCREEN_UNDEFINED = 0, TOUCHSCREEN_NOTOUCH = 1, TOUCHSCREEN_STYLUS = 2, TOUCHSCREEN_FINGER = 3;
  public static final int KEYBOARD_UNDEFINED = 0, KEYBOARD_NOKEYS = 1, KEYBOARD_QWERTY = 2, KEYBOARD_12KEY = 3;
  public static final int KEYBOARDHIDDEN_UNDEFINED = 0, KEYBOARDHIDDEN_NO = 1, KEYBOARDHIDDEN_YES = 2;
  public static final int HARDKEYBOARDHIDDEN_UNDEFINED = 0, HARDKEYBOARDHIDDEN_NO = 1, HARDKEYBOARDHIDDEN_YES = 2;
  public static final int NAVIGATION_UNDEFINED = 0, NAVIGATION_NONAV = 1, NAVIGATION_DPAD = 2, NAVIGATION_TRACKBALL = 3, NAVIGATION_WHEEL = 4;
  public static final int NAVIGATIONHIDDEN_UNDEFINED = 0, NAVIGATIONHIDDEN_NO = 1, NAVIGATIONHIDDEN_YES = 2;
  public static final int UI_MODE_TYPE_MASK = 0x0f, UI_MODE_TYPE_UNDEFINED = 0, UI_MODE_TYPE_NORMAL = 1, UI_MODE_TYPE_DESK = 2, UI_MODE_TYPE_CAR = 3,
      UI_MODE_TYPE_TELEVISION = 4, UI_MODE_TYPE_APPLIANCE = 5, UI_MODE_TYPE_WATCH = 6, UI_MODE_TYPE_VR_HEADSET = 7;
  public static final int UI_MODE_NIGHT_MASK = 0x30, UI_MODE_NIGHT_UNDEFINED = 0, UI_MODE_NIGHT_NO = 0x10, UI_MODE_NIGHT_YES = 0x20;
  public static final int SCREEN_WIDTH_DP_UNDEFINED = 0, SCREEN_HEIGHT_DP_UNDEFINED = 0, SMALLEST_SCREEN_WIDTH_DP_UNDEFINED = 0, DENSITY_DPI_UNDEFINED = 0;
  public static final int FONT_WEIGHT_ADJUSTMENT_UNDEFINED = Integer.MAX_VALUE;
  public static final int GRAMMATICAL_GENDER_NOT_SPECIFIED = 0, GRAMMATICAL_GENDER_NEUTRAL = 1, GRAMMATICAL_GENDER_FEMININE = 2, GRAMMATICAL_GENDER_MASCULINE = 3;
  public static final int MNC_ZERO = 0xffff;
  // the change flags of diff (ActivityInfo.CONFIG_*)
  static final int CONFIG_MCC = 0x1, CONFIG_MNC = 0x2, CONFIG_LOCALE = 0x4, CONFIG_TOUCHSCREEN = 0x8, CONFIG_KEYBOARD = 0x10, CONFIG_KEYBOARD_HIDDEN = 0x20,
      CONFIG_NAVIGATION = 0x40, CONFIG_ORIENTATION = 0x80, CONFIG_SCREEN_LAYOUT = 0x100, CONFIG_UI_MODE = 0x200, CONFIG_SCREEN_SIZE = 0x400,
      CONFIG_SMALLEST_SCREEN_SIZE = 0x800, CONFIG_DENSITY = 0x1000, CONFIG_LAYOUT_DIRECTION = 0x2000, CONFIG_COLOR_MODE = 0x4000,
      CONFIG_GRAMMATICAL_GENDER = 0x8000, CONFIG_FONT_WEIGHT_ADJUSTMENT = 0x10000000, CONFIG_FONT_SCALE = 0x40000000;

  public float fontScale;
  public int mcc, mnc;
  @Deprecated public Locale locale;
  private android.os.LocaleList mLocaleList = android.os.LocaleList.getEmptyLocaleList();
  public int screenLayout, colorMode, touchscreen, keyboard, keyboardHidden, hardKeyboardHidden, navigation, navigationHidden, orientation, uiMode;
  public int screenWidthDp, screenHeightDp, smallestScreenWidthDp, densityDpi, fontWeightAdjustment;
  private int mGrammaticalGender;

  public Configuration() { setToDefaults(); }
  public Configuration(Configuration o) { setTo(o); }
  /** the page's configuration: its size, density and orientation, the browser's locales, a touchscreen without keys */
  static Configuration device() {
    Configuration c = new Configuration();
    c.fontScale = 1;
    c.setLocales(android.os.LocaleList.getDefault());
    boolean land = a2w.Screen.landscape();
    c.screenWidthDp = (int) (a2w.Screen.W / a2w.Screen.DENSITY);
    c.screenHeightDp = (int) (a2w.Screen.H / a2w.Screen.DENSITY);
    c.smallestScreenWidthDp = Math.min(c.screenWidthDp, c.screenHeightDp);
    int sw = c.smallestScreenWidthDp, lw = Math.max(c.screenWidthDp, c.screenHeightDp);
    int size = sw >= 720 ? SCREENLAYOUT_SIZE_XLARGE : sw >= 600 ? SCREENLAYOUT_SIZE_LARGE : lw >= 470 && sw >= 320 ? SCREENLAYOUT_SIZE_NORMAL : SCREENLAYOUT_SIZE_SMALL;
    boolean isLong = lw * 3 / 5 >= sw - 1;
    c.screenLayout = size | (isLong ? SCREENLAYOUT_LONG_YES : SCREENLAYOUT_LONG_NO) | SCREENLAYOUT_ROUND_NO
        | (android.text.TextUtils.getLayoutDirectionFromLocale(c.getLocales().get(0)) == android.view.View.LAYOUT_DIRECTION_RTL ? SCREENLAYOUT_LAYOUTDIR_RTL : SCREENLAYOUT_LAYOUTDIR_LTR);
    c.colorMode = COLOR_MODE_WIDE_COLOR_GAMUT_NO | COLOR_MODE_HDR_NO;
    c.touchscreen = TOUCHSCREEN_FINGER; c.keyboard = KEYBOARD_NOKEYS; c.keyboardHidden = KEYBOARDHIDDEN_YES; c.hardKeyboardHidden = HARDKEYBOARDHIDDEN_YES;
    c.navigation = NAVIGATION_NONAV; c.navigationHidden = NAVIGATIONHIDDEN_YES;
    c.orientation = land ? ORIENTATION_LANDSCAPE : ORIENTATION_PORTRAIT;
    c.uiMode = UI_MODE_TYPE_NORMAL | UI_MODE_NIGHT_NO;
    c.densityDpi = a2w.Screen.DPI;
    c.fontWeightAdjustment = 0;
    return c;
  }
  public void setTo(Configuration o) {
    fontScale = o.fontScale; mcc = o.mcc; mnc = o.mnc; locale = o.locale; mLocaleList = o.mLocaleList; screenLayout = o.screenLayout; colorMode = o.colorMode;
    touchscreen = o.touchscreen; keyboard = o.keyboard; keyboardHidden = o.keyboardHidden; hardKeyboardHidden = o.hardKeyboardHidden; navigation = o.navigation;
    navigationHidden = o.navigationHidden; orientation = o.orientation; uiMode = o.uiMode; screenWidthDp = o.screenWidthDp; screenHeightDp = o.screenHeightDp;
    smallestScreenWidthDp = o.smallestScreenWidthDp; densityDpi = o.densityDpi; fontWeightAdjustment = o.fontWeightAdjustment; mGrammaticalGender = o.mGrammaticalGender;
  }
  public void setToDefaults() {
    fontScale = 1; mcc = mnc = 0; mLocaleList = android.os.LocaleList.getEmptyLocaleList(); locale = null;
    touchscreen = TOUCHSCREEN_UNDEFINED; keyboard = KEYBOARD_UNDEFINED; keyboardHidden = KEYBOARDHIDDEN_UNDEFINED; hardKeyboardHidden = HARDKEYBOARDHIDDEN_UNDEFINED;
    navigation = NAVIGATION_UNDEFINED; navigationHidden = NAVIGATIONHIDDEN_UNDEFINED; orientation = ORIENTATION_UNDEFINED; screenLayout = SCREENLAYOUT_UNDEFINED;
    colorMode = COLOR_MODE_UNDEFINED; uiMode = UI_MODE_TYPE_UNDEFINED; screenWidthDp = SCREEN_WIDTH_DP_UNDEFINED; screenHeightDp = SCREEN_HEIGHT_DP_UNDEFINED;
    smallestScreenWidthDp = SMALLEST_SCREEN_WIDTH_DP_UNDEFINED; densityDpi = DENSITY_DPI_UNDEFINED; fontWeightAdjustment = FONT_WEIGHT_ADJUSTMENT_UNDEFINED;
    mGrammaticalGender = GRAMMATICAL_GENDER_NOT_SPECIFIED;
  }
  private void fixUpLocaleList() {
    if ((locale == null && !mLocaleList.isEmpty()) || (locale != null && !locale.equals(mLocaleList.get(0))))
      mLocaleList = locale == null ? android.os.LocaleList.getEmptyLocaleList() : new android.os.LocaleList(locale);
  }
  public android.os.LocaleList getLocales() { fixUpLocaleList(); return mLocaleList; }
  public void setLocales(android.os.LocaleList locales) {
    mLocaleList = locales == null ? android.os.LocaleList.getEmptyLocaleList() : locales;
    locale = mLocaleList.get(0);
    setLayoutDirection(locale);
  }
  public void setLocale(Locale loc) { setLocales(loc == null ? android.os.LocaleList.getEmptyLocaleList() : new android.os.LocaleList(loc)); }
  public int getLayoutDirection() {
    return (screenLayout & SCREENLAYOUT_LAYOUTDIR_MASK) == SCREENLAYOUT_LAYOUTDIR_RTL ? android.view.View.LAYOUT_DIRECTION_RTL : android.view.View.LAYOUT_DIRECTION_LTR;
  }
  public void setLayoutDirection(Locale loc) {
    int layoutDirection = 1 + android.text.TextUtils.getLayoutDirectionFromLocale(loc);
    screenLayout = (screenLayout & ~SCREENLAYOUT_LAYOUTDIR_MASK) | (layoutDirection << SCREENLAYOUT_LAYOUTDIR_SHIFT);
  }
  public int getGrammaticalGender() { return mGrammaticalGender; }
  public boolean isLayoutSizeAtLeast(int size) {
    int cur = screenLayout & SCREENLAYOUT_SIZE_MASK;
    return cur != SCREENLAYOUT_SIZE_UNDEFINED && cur >= size;
  }
  public boolean isScreenRound() { return (screenLayout & SCREENLAYOUT_ROUND_MASK) == SCREENLAYOUT_ROUND_YES; }
  public boolean isScreenWideColorGamut() { return (colorMode & COLOR_MODE_WIDE_COLOR_GAMUT_MASK) == COLOR_MODE_WIDE_COLOR_GAMUT_YES; }
  public boolean isScreenHdr() { return (colorMode & COLOR_MODE_HDR_MASK) == COLOR_MODE_HDR_YES; }
  public boolean isNightModeActive() { return (uiMode & UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES; }

  /** the change flags (ActivityInfo.CONFIG_*) of the fields of delta that are defined and differ from this */
  public int diff(Configuration delta) { return diff(delta, false); }
  private int diff(Configuration delta, boolean compareUndefined) {
    int changed = 0;
    if ((compareUndefined || delta.fontScale > 0) && fontScale != delta.fontScale) changed |= CONFIG_FONT_SCALE;
    if ((compareUndefined || delta.mcc != 0) && mcc != delta.mcc) changed |= CONFIG_MCC;
    if ((compareUndefined || delta.mnc != 0) && mnc != delta.mnc) changed |= CONFIG_MNC;
    fixUpLocaleList(); delta.fixUpLocaleList();
    if ((compareUndefined || !delta.mLocaleList.isEmpty()) && !mLocaleList.equals(delta.mLocaleList)) { changed |= CONFIG_LOCALE; changed |= CONFIG_LAYOUT_DIRECTION; }
    int deltaDir = delta.screenLayout & SCREENLAYOUT_LAYOUTDIR_MASK;
    if ((compareUndefined || deltaDir != SCREENLAYOUT_LAYOUTDIR_UNDEFINED) && deltaDir != (screenLayout & SCREENLAYOUT_LAYOUTDIR_MASK)) changed |= CONFIG_LAYOUT_DIRECTION;
    if ((compareUndefined || delta.touchscreen != TOUCHSCREEN_UNDEFINED) && touchscreen != delta.touchscreen) changed |= CONFIG_TOUCHSCREEN;
    if ((compareUndefined || delta.keyboard != KEYBOARD_UNDEFINED) && keyboard != delta.keyboard) changed |= CONFIG_KEYBOARD;
    if ((compareUndefined || delta.keyboardHidden != KEYBOARDHIDDEN_UNDEFINED) && keyboardHidden != delta.keyboardHidden) changed |= CONFIG_KEYBOARD_HIDDEN;
    if ((compareUndefined || delta.hardKeyboardHidden != HARDKEYBOARDHIDDEN_UNDEFINED) && hardKeyboardHidden != delta.hardKeyboardHidden) changed |= CONFIG_KEYBOARD_HIDDEN;
    if ((compareUndefined || delta.navigation != NAVIGATION_UNDEFINED) && navigation != delta.navigation) changed |= CONFIG_NAVIGATION;
    if ((compareUndefined || delta.navigationHidden != NAVIGATIONHIDDEN_UNDEFINED) && navigationHidden != delta.navigationHidden) changed |= CONFIG_KEYBOARD_HIDDEN;
    if ((compareUndefined || delta.orientation != ORIENTATION_UNDEFINED) && orientation != delta.orientation) changed |= CONFIG_ORIENTATION;
    if ((compareUndefined || getScreenLayoutNoDirection(delta.screenLayout) != SCREENLAYOUT_UNDEFINED)
        && getScreenLayoutNoDirection(screenLayout) != getScreenLayoutNoDirection(delta.screenLayout)) changed |= CONFIG_SCREEN_LAYOUT;
    if ((compareUndefined || (delta.colorMode & COLOR_MODE_HDR_MASK) != COLOR_MODE_HDR_UNDEFINED) && (colorMode & COLOR_MODE_HDR_MASK) != (delta.colorMode & COLOR_MODE_HDR_MASK)) changed |= CONFIG_COLOR_MODE;
    if ((compareUndefined || (delta.colorMode & COLOR_MODE_WIDE_COLOR_GAMUT_MASK) != COLOR_MODE_WIDE_COLOR_GAMUT_UNDEFINED)
        && (colorMode & COLOR_MODE_WIDE_COLOR_GAMUT_MASK) != (delta.colorMode & COLOR_MODE_WIDE_COLOR_GAMUT_MASK)) changed |= CONFIG_COLOR_MODE;
    if ((compareUndefined || delta.uiMode != (UI_MODE_TYPE_UNDEFINED | UI_MODE_NIGHT_UNDEFINED)) && uiMode != delta.uiMode) changed |= CONFIG_UI_MODE;
    if ((compareUndefined || delta.screenWidthDp != SCREEN_WIDTH_DP_UNDEFINED) && screenWidthDp != delta.screenWidthDp) changed |= CONFIG_SCREEN_SIZE;
    if ((compareUndefined || delta.screenHeightDp != SCREEN_HEIGHT_DP_UNDEFINED) && screenHeightDp != delta.screenHeightDp) changed |= CONFIG_SCREEN_SIZE;
    if ((compareUndefined || delta.smallestScreenWidthDp != SMALLEST_SCREEN_WIDTH_DP_UNDEFINED) && smallestScreenWidthDp != delta.smallestScreenWidthDp) changed |= CONFIG_SMALLEST_SCREEN_SIZE;
    if ((compareUndefined || delta.densityDpi != DENSITY_DPI_UNDEFINED) && densityDpi != delta.densityDpi) changed |= CONFIG_DENSITY;
    if ((compareUndefined || delta.fontWeightAdjustment != FONT_WEIGHT_ADJUSTMENT_UNDEFINED) && fontWeightAdjustment != delta.fontWeightAdjustment) changed |= CONFIG_FONT_WEIGHT_ADJUSTMENT;
    if ((compareUndefined || delta.mGrammaticalGender != GRAMMATICAL_GENDER_NOT_SPECIFIED) && mGrammaticalGender != delta.mGrammaticalGender) changed |= CONFIG_GRAMMATICAL_GENDER;
    return changed;
  }
  private static int getScreenLayoutNoDirection(int screenLayout) { return screenLayout & ~SCREENLAYOUT_LAYOUTDIR_MASK; }
  /** copies the defined fields of delta; returns the changes */
  public int updateFrom(Configuration delta) {
    int changed = diff(delta);
    if (delta.fontScale > 0) fontScale = delta.fontScale;
    if (delta.mcc != 0) mcc = delta.mcc;
    if (delta.mnc != 0) mnc = delta.mnc;
    delta.fixUpLocaleList();
    if (!delta.mLocaleList.isEmpty()) { mLocaleList = delta.mLocaleList; locale = mLocaleList.get(0); }
    int deltaDir = delta.screenLayout & SCREENLAYOUT_LAYOUTDIR_MASK;
    if (deltaDir != SCREENLAYOUT_LAYOUTDIR_UNDEFINED) screenLayout = (screenLayout & ~SCREENLAYOUT_LAYOUTDIR_MASK) | deltaDir;
    if (delta.touchscreen != TOUCHSCREEN_UNDEFINED) touchscreen = delta.touchscreen;
    if (delta.keyboard != KEYBOARD_UNDEFINED) keyboard = delta.keyboard;
    if (delta.keyboardHidden != KEYBOARDHIDDEN_UNDEFINED) keyboardHidden = delta.keyboardHidden;
    if (delta.hardKeyboardHidden != HARDKEYBOARDHIDDEN_UNDEFINED) hardKeyboardHidden = delta.hardKeyboardHidden;
    if (delta.navigation != NAVIGATION_UNDEFINED) navigation = delta.navigation;
    if (delta.navigationHidden != NAVIGATIONHIDDEN_UNDEFINED) navigationHidden = delta.navigationHidden;
    if (delta.orientation != ORIENTATION_UNDEFINED) orientation = delta.orientation;
    if ((delta.screenLayout & SCREENLAYOUT_SIZE_MASK) != SCREENLAYOUT_SIZE_UNDEFINED) screenLayout = (screenLayout & ~SCREENLAYOUT_SIZE_MASK) | (delta.screenLayout & SCREENLAYOUT_SIZE_MASK);
    if ((delta.screenLayout & SCREENLAYOUT_LONG_MASK) != SCREENLAYOUT_LONG_UNDEFINED) screenLayout = (screenLayout & ~SCREENLAYOUT_LONG_MASK) | (delta.screenLayout & SCREENLAYOUT_LONG_MASK);
    if ((delta.screenLayout & SCREENLAYOUT_ROUND_MASK) != SCREENLAYOUT_ROUND_UNDEFINED) screenLayout = (screenLayout & ~SCREENLAYOUT_ROUND_MASK) | (delta.screenLayout & SCREENLAYOUT_ROUND_MASK);
    if ((delta.colorMode & COLOR_MODE_WIDE_COLOR_GAMUT_MASK) != COLOR_MODE_WIDE_COLOR_GAMUT_UNDEFINED) colorMode = (colorMode & ~COLOR_MODE_WIDE_COLOR_GAMUT_MASK) | (delta.colorMode & COLOR_MODE_WIDE_COLOR_GAMUT_MASK);
    if ((delta.colorMode & COLOR_MODE_HDR_MASK) != COLOR_MODE_HDR_UNDEFINED) colorMode = (colorMode & ~COLOR_MODE_HDR_MASK) | (delta.colorMode & COLOR_MODE_HDR_MASK);
    if (delta.uiMode != (UI_MODE_TYPE_UNDEFINED | UI_MODE_NIGHT_UNDEFINED)) {
      int t = delta.uiMode & UI_MODE_TYPE_MASK, n = delta.uiMode & UI_MODE_NIGHT_MASK;
      if (t != UI_MODE_TYPE_UNDEFINED) uiMode = (uiMode & ~UI_MODE_TYPE_MASK) | t;
      if (n != UI_MODE_NIGHT_UNDEFINED) uiMode = (uiMode & ~UI_MODE_NIGHT_MASK) | n;
    }
    if (delta.screenWidthDp != SCREEN_WIDTH_DP_UNDEFINED) screenWidthDp = delta.screenWidthDp;
    if (delta.screenHeightDp != SCREEN_HEIGHT_DP_UNDEFINED) screenHeightDp = delta.screenHeightDp;
    if (delta.smallestScreenWidthDp != SMALLEST_SCREEN_WIDTH_DP_UNDEFINED) smallestScreenWidthDp = delta.smallestScreenWidthDp;
    if (delta.densityDpi != DENSITY_DPI_UNDEFINED) densityDpi = delta.densityDpi;
    if (delta.fontWeightAdjustment != FONT_WEIGHT_ADJUSTMENT_UNDEFINED) fontWeightAdjustment = delta.fontWeightAdjustment;
    if (delta.mGrammaticalGender != GRAMMATICAL_GENDER_NOT_SPECIFIED) mGrammaticalGender = delta.mGrammaticalGender;
    return changed;
  }
  /** the fields of change that differ from base (others undefined) */
  public static Configuration generateDelta(Configuration base, Configuration change) {
    Configuration delta = new Configuration();
    if (base.fontScale != change.fontScale) delta.fontScale = change.fontScale;
    if (base.mcc != change.mcc) delta.mcc = change.mcc;
    if (base.mnc != change.mnc) delta.mnc = change.mnc;
    base.fixUpLocaleList(); change.fixUpLocaleList();
    if (!base.mLocaleList.equals(change.mLocaleList)) { delta.mLocaleList = change.mLocaleList; delta.locale = change.locale; }
    if (base.touchscreen != change.touchscreen) delta.touchscreen = change.touchscreen;
    if (base.keyboard != change.keyboard) delta.keyboard = change.keyboard;
    if (base.keyboardHidden != change.keyboardHidden) delta.keyboardHidden = change.keyboardHidden;
    if (base.navigation != change.navigation) delta.navigation = change.navigation;
    if (base.navigationHidden != change.navigationHidden) delta.navigationHidden = change.navigationHidden;
    if (base.hardKeyboardHidden != change.hardKeyboardHidden) delta.hardKeyboardHidden = change.hardKeyboardHidden;
    if (base.orientation != change.orientation) delta.orientation = change.orientation;
    for (int mask : new int[] {SCREENLAYOUT_SIZE_MASK, SCREENLAYOUT_LONG_MASK, SCREENLAYOUT_LAYOUTDIR_MASK, SCREENLAYOUT_ROUND_MASK})
      if ((base.screenLayout & mask) != (change.screenLayout & mask)) delta.screenLayout |= change.screenLayout & mask;
    for (int mask : new int[] {COLOR_MODE_WIDE_COLOR_GAMUT_MASK, COLOR_MODE_HDR_MASK})
      if ((base.colorMode & mask) != (change.colorMode & mask)) delta.colorMode |= change.colorMode & mask;
    for (int mask : new int[] {UI_MODE_TYPE_MASK, UI_MODE_NIGHT_MASK})
      if ((base.uiMode & mask) != (change.uiMode & mask)) delta.uiMode |= change.uiMode & mask;
    if (base.screenWidthDp != change.screenWidthDp) delta.screenWidthDp = change.screenWidthDp;
    if (base.screenHeightDp != change.screenHeightDp) delta.screenHeightDp = change.screenHeightDp;
    if (base.smallestScreenWidthDp != change.smallestScreenWidthDp) delta.smallestScreenWidthDp = change.smallestScreenWidthDp;
    if (base.densityDpi != change.densityDpi) delta.densityDpi = change.densityDpi;
    if (base.fontWeightAdjustment != change.fontWeightAdjustment) delta.fontWeightAdjustment = change.fontWeightAdjustment;
    if (base.mGrammaticalGender != change.mGrammaticalGender) delta.mGrammaticalGender = change.mGrammaticalGender;
    return delta;
  }
  /** whether a change (diff's flags) touches the configurations a resource depends on (interestingChanges) */
  public static boolean needNewResources(int configChanges, int interestingChanges) {
    interestingChanges = interestingChanges | CONFIG_FONT_SCALE | CONFIG_FONT_WEIGHT_ADJUSTMENT;
    return (configChanges & interestingChanges) != 0;
  }
  public int compareTo(Configuration that) {
    int n;
    float a = fontScale, b = that.fontScale;
    if (a < b) return -1;
    if (a > b) return 1;
    n = mcc - that.mcc; if (n != 0) return n;
    n = mnc - that.mnc; if (n != 0) return n;
    fixUpLocaleList(); that.fixUpLocaleList();
    if (mLocaleList.isEmpty()) { if (!that.mLocaleList.isEmpty()) return 1; }
    else if (that.mLocaleList.isEmpty()) return -1;
    else {
      int minSize = Math.min(mLocaleList.size(), that.mLocaleList.size());
      for (int i = 0; i < minSize; i++) {
        Locale t = mLocaleList.get(i), o = that.mLocaleList.get(i);
        n = t.getLanguage().compareTo(o.getLanguage()); if (n != 0) return n;
        n = t.getCountry().compareTo(o.getCountry()); if (n != 0) return n;
        n = t.getVariant().compareTo(o.getVariant()); if (n != 0) return n;
        n = t.toLanguageTag().compareTo(o.toLanguageTag()); if (n != 0) return n;
      }
      n = mLocaleList.size() - that.mLocaleList.size(); if (n != 0) return n;
    }
    n = mGrammaticalGender - that.mGrammaticalGender; if (n != 0) return n;
    n = touchscreen - that.touchscreen; if (n != 0) return n;
    n = keyboard - that.keyboard; if (n != 0) return n;
    n = keyboardHidden - that.keyboardHidden; if (n != 0) return n;
    n = hardKeyboardHidden - that.hardKeyboardHidden; if (n != 0) return n;
    n = navigation - that.navigation; if (n != 0) return n;
    n = navigationHidden - that.navigationHidden; if (n != 0) return n;
    n = orientation - that.orientation; if (n != 0) return n;
    n = colorMode - that.colorMode; if (n != 0) return n;
    n = screenLayout - that.screenLayout; if (n != 0) return n;
    n = uiMode - that.uiMode; if (n != 0) return n;
    n = screenWidthDp - that.screenWidthDp; if (n != 0) return n;
    n = screenHeightDp - that.screenHeightDp; if (n != 0) return n;
    n = smallestScreenWidthDp - that.smallestScreenWidthDp; if (n != 0) return n;
    n = densityDpi - that.densityDpi; if (n != 0) return n;
    return fontWeightAdjustment - that.fontWeightAdjustment;
  }
  public boolean equals(Configuration that) { return that == this || that != null && compareTo(that) == 0; }
  @Override public boolean equals(Object that) { return that instanceof Configuration && equals((Configuration) that); }
  @Override public int hashCode() {
    int result = 17;
    result = 31 * result + Float.floatToIntBits(fontScale);
    result = 31 * result + mcc; result = 31 * result + mnc;
    result = 31 * result + getLocales().hashCode();
    result = 31 * result + touchscreen; result = 31 * result + keyboard; result = 31 * result + keyboardHidden; result = 31 * result + hardKeyboardHidden;
    result = 31 * result + navigation; result = 31 * result + navigationHidden; result = 31 * result + orientation; result = 31 * result + screenLayout;
    result = 31 * result + colorMode; result = 31 * result + uiMode; result = 31 * result + screenWidthDp; result = 31 * result + screenHeightDp;
    result = 31 * result + smallestScreenWidthDp; result = 31 * result + densityDpi; result = 31 * result + fontWeightAdjustment; result = 31 * result + mGrammaticalGender;
    return result;
  }
  @Override public String toString() {
    StringBuilder sb = new StringBuilder(128);
    sb.append("{").append(fontScale).append(' ');
    if (mcc != 0) sb.append(mcc).append("mcc"); else sb.append("?mcc");
    if (mnc != 0) sb.append(mnc).append("mnc"); else sb.append("?mnc");
    fixUpLocaleList();
    sb.append(mLocaleList.isEmpty() ? " ?localeList" : " " + mLocaleList.toLanguageTags());
    int layoutDir = screenLayout & SCREENLAYOUT_LAYOUTDIR_MASK;
    sb.append(layoutDir == SCREENLAYOUT_LAYOUTDIR_LTR ? " ldltr" : layoutDir == SCREENLAYOUT_LAYOUTDIR_RTL ? " ldrtl" : " ?layoutDir");
    sb.append(smallestScreenWidthDp != 0 ? " sw" + smallestScreenWidthDp + "dp" : " ?swdp");
    sb.append(screenWidthDp != 0 ? " w" + screenWidthDp + "dp" : " ?wdp");
    sb.append(screenHeightDp != 0 ? " h" + screenHeightDp + "dp" : " ?hdp");
    sb.append(densityDpi != 0 ? " " + densityDpi + "dpi" : " ?density");
    switch (screenLayout & SCREENLAYOUT_SIZE_MASK) {
      case SCREENLAYOUT_SIZE_SMALL: sb.append(" smll"); break; case SCREENLAYOUT_SIZE_NORMAL: sb.append(" nrml"); break;
      case SCREENLAYOUT_SIZE_LARGE: sb.append(" lrg"); break; case SCREENLAYOUT_SIZE_XLARGE: sb.append(" xlrg"); break; default: sb.append(" ?lsize");
    }
    switch (screenLayout & SCREENLAYOUT_LONG_MASK) { case SCREENLAYOUT_LONG_NO: break; case SCREENLAYOUT_LONG_YES: sb.append(" long"); break; default: sb.append(" ?long"); }
    switch (orientation) { case ORIENTATION_LANDSCAPE: sb.append(" land"); break; case ORIENTATION_PORTRAIT: sb.append(" port"); break; default: sb.append(" ?orien"); }
    switch (uiMode & UI_MODE_NIGHT_MASK) { case UI_MODE_NIGHT_NO: break; case UI_MODE_NIGHT_YES: sb.append(" night"); break; default: sb.append(" ?night"); }
    switch (touchscreen) { case TOUCHSCREEN_NOTOUCH: sb.append(" -touch"); break; case TOUCHSCREEN_STYLUS: sb.append(" stylus"); break; case TOUCHSCREEN_FINGER: sb.append(" finger"); break; default: sb.append(" ?touch"); }
    switch (keyboard) { case KEYBOARD_NOKEYS: sb.append(" -keyb"); break; case KEYBOARD_QWERTY: sb.append(" qwerty"); break; case KEYBOARD_12KEY: sb.append(" 12key"); break; default: sb.append(" ?keyb"); }
    sb.append('}');
    return sb.toString();
  }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel dest, int flags) {
    dest.writeFloat(fontScale); dest.writeInt(mcc); dest.writeInt(mnc);
    fixUpLocaleList();
    dest.writeString(mLocaleList.toLanguageTags());
    dest.writeInt(touchscreen); dest.writeInt(keyboard); dest.writeInt(keyboardHidden); dest.writeInt(hardKeyboardHidden); dest.writeInt(navigation);
    dest.writeInt(navigationHidden); dest.writeInt(orientation); dest.writeInt(screenLayout); dest.writeInt(colorMode); dest.writeInt(uiMode);
    dest.writeInt(screenWidthDp); dest.writeInt(screenHeightDp); dest.writeInt(smallestScreenWidthDp); dest.writeInt(densityDpi);
    dest.writeInt(fontWeightAdjustment); dest.writeInt(mGrammaticalGender);
  }
  public void readFromParcel(android.os.Parcel source) {
    fontScale = source.readFloat(); mcc = source.readInt(); mnc = source.readInt();
    String tags = source.readString();
    mLocaleList = tags == null || tags.isEmpty() ? android.os.LocaleList.getEmptyLocaleList() : android.os.LocaleList.forLanguageTags(tags);
    locale = mLocaleList.get(0);
    touchscreen = source.readInt(); keyboard = source.readInt(); keyboardHidden = source.readInt(); hardKeyboardHidden = source.readInt(); navigation = source.readInt();
    navigationHidden = source.readInt(); orientation = source.readInt(); screenLayout = source.readInt(); colorMode = source.readInt(); uiMode = source.readInt();
    screenWidthDp = source.readInt(); screenHeightDp = source.readInt(); smallestScreenWidthDp = source.readInt(); densityDpi = source.readInt();
    fontWeightAdjustment = source.readInt(); mGrammaticalGender = source.readInt();
  }
  public static final android.os.Parcelable.Creator<Configuration> CREATOR = new Creator();
  static final class Creator implements android.os.Parcelable.Creator<Configuration> {
    public Configuration createFromParcel(android.os.Parcel source) { Configuration c = new Configuration(); c.readFromParcel(source); return c; }
    public Configuration[] newArray(int size) { return new Configuration[size]; }
  }
}

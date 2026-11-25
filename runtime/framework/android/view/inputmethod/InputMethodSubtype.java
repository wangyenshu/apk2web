package android.view.inputmethod;

import android.os.Parcel;

/** A subtype of an input method: a locale and mode (keyboard, voice...), with extra values. */
public final class InputMethodSubtype implements android.os.Parcelable {
  private final int mNameResId, mIconResId, mId;
  private final String mLocale, mLanguageTag, mMode, mExtraValue, mPkLayoutType;
  private final boolean mAuxiliary, mOverridesImplicit, mAsciiCapable;
  private final CharSequence mNameOverride;
  private final android.icu.util.ULocale mPkLanguageTag;
  @Deprecated public InputMethodSubtype(int nameId, int iconId, String locale, String mode, String extraValue, boolean isAuxiliary, boolean overridesImplicitlyEnabledSubtype) {
    this(nameId, iconId, locale, mode, extraValue, isAuxiliary, overridesImplicitlyEnabledSubtype, 0);
  }
  @Deprecated public InputMethodSubtype(int nameId, int iconId, String locale, String mode, String extraValue, boolean isAuxiliary, boolean overridesImplicitlyEnabledSubtype, int id) {
    this(nameId, iconId, locale, null, mode, extraValue, isAuxiliary, overridesImplicitlyEnabledSubtype, id, false, null, null, null);
  }
  InputMethodSubtype(int nameId, int iconId, String locale, String languageTag, String mode, String extraValue, boolean aux, boolean overrides, int id,
      boolean ascii, CharSequence nameOverride, android.icu.util.ULocale pkTag, String pkLayout) {
    mNameResId = nameId; mIconResId = iconId; mLocale = locale == null ? "" : locale; mLanguageTag = languageTag == null ? "" : languageTag;
    mMode = mode == null ? "" : mode; mExtraValue = extraValue == null ? "" : extraValue; mAuxiliary = aux; mOverridesImplicit = overrides;
    mAsciiCapable = ascii; mNameOverride = nameOverride == null ? "" : nameOverride; mPkLanguageTag = pkTag; mPkLayoutType = pkLayout == null ? "" : pkLayout;
    mId = id != 0 ? id : java.util.Arrays.hashCode(new Object[] {mLocale, mMode, mExtraValue, mAuxiliary, mOverridesImplicit, mAsciiCapable});
  }
  public int getNameResId() { return mNameResId; }
  public CharSequence getNameOverride() { return mNameOverride; }
  public int getIconResId() { return mIconResId; }
  @Deprecated public String getLocale() { return mLocale; }
  public String getLanguageTag() { return mLanguageTag; }
  public String getMode() { return mMode; }
  public String getExtraValue() { return mExtraValue; }
  public boolean isAuxiliary() { return mAuxiliary; }
  public boolean overridesImplicitlyEnabledSubtype() { return mOverridesImplicit; }
  public boolean isAsciiCapable() { return mAsciiCapable; }
  public android.icu.util.ULocale getPhysicalKeyboardHintLanguageTag() { return mPkLanguageTag; }
  public String getPhysicalKeyboardHintLayoutType() { return mPkLayoutType; }
  /** the name override, else the name resource (formatted with the locale's display name when it has %s), else the locale */
  public CharSequence getDisplayName(android.content.Context context, String packageName, android.content.pm.ApplicationInfo appInfo) {
    if (mNameOverride.length() > 0) return mNameOverride;
    String localeName = mLocale.isEmpty() ? "" : java.util.Locale.forLanguageTag(mLocale.replace('_', '-')).getDisplayName();
    if (mNameResId == 0) return localeName;
    try {
      String name = context.getResources().getString(mNameResId);
      return name.contains("%s") ? String.format(name, localeName) : name;
    } catch (RuntimeException e) { return localeName; }
  }
  private java.util.HashMap<String, String> extras() {
    java.util.HashMap<String, String> m = new java.util.HashMap<>();
    for (String pair : mExtraValue.split(",")) {
      if (pair.isEmpty()) continue;
      int eq = pair.indexOf('=');
      if (eq < 0) m.put(pair, null); else m.put(pair.substring(0, eq), pair.substring(eq + 1));
    }
    return m;
  }
  public boolean containsExtraValueKey(String key) { return extras().containsKey(key); }
  public String getExtraValueOf(String key) { return extras().get(key); }
  @Override public int hashCode() { return mId; }
  @Override public boolean equals(Object o) {
    if (!(o instanceof InputMethodSubtype)) return false;
    InputMethodSubtype s = (InputMethodSubtype) o;
    return s.mId == mId && s.mLocale.equals(mLocale) && s.mLanguageTag.equals(mLanguageTag) && s.mMode.equals(mMode) && s.mExtraValue.equals(mExtraValue)
        && s.mNameResId == mNameResId && s.mIconResId == mIconResId && s.mAuxiliary == mAuxiliary && s.mOverridesImplicit == mOverridesImplicit && s.mAsciiCapable == mAsciiCapable;
  }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel d, int f) {
    d.writeInt(mNameResId); d.writeInt(mIconResId); d.writeString(mLocale); d.writeString(mLanguageTag); d.writeString(mMode); d.writeString(mExtraValue);
    d.writeInt(mAuxiliary ? 1 : 0); d.writeInt(mOverridesImplicit ? 1 : 0); d.writeInt(mId); d.writeInt(mAsciiCapable ? 1 : 0);
    android.text.TextUtils.writeToParcel(mNameOverride, d, f); d.writeString(mPkLanguageTag == null ? null : mPkLanguageTag.toLanguageTag()); d.writeString(mPkLayoutType);
  }
  public static final android.os.Parcelable.Creator<InputMethodSubtype> CREATOR = new android.os.Parcelable.Creator<InputMethodSubtype>() {
    public InputMethodSubtype createFromParcel(Parcel s) {
      int name = s.readInt(), icon = s.readInt();
      String locale = s.readString(), tag = s.readString(), mode = s.readString(), extra = s.readString();
      boolean aux = s.readInt() != 0, ov = s.readInt() != 0;
      int id = s.readInt();
      boolean ascii = s.readInt() != 0;
      CharSequence no = android.text.TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(s);
      String pk = s.readString(), layout = s.readString();
      return new InputMethodSubtype(name, icon, locale, tag, mode, extra, aux, ov, id, ascii, no, pk == null ? null : android.icu.util.ULocale.forLanguageTag(pk), layout);
    }
    public InputMethodSubtype[] newArray(int size) { return new InputMethodSubtype[size]; }
  };

  public static class InputMethodSubtypeBuilder {
    private int mNameResId, mIconResId, mId;
    private String mLocale, mLanguageTag, mMode, mExtraValue, mPkLayout;
    private boolean mAux, mOverrides, mAscii;
    private CharSequence mNameOverride;
    private android.icu.util.ULocale mPkTag;
    public InputMethodSubtypeBuilder() {}
    public InputMethodSubtypeBuilder setIsAuxiliary(boolean isAuxiliary) { mAux = isAuxiliary; return this; }
    public InputMethodSubtypeBuilder setOverridesImplicitlyEnabledSubtype(boolean b) { mOverrides = b; return this; }
    public InputMethodSubtypeBuilder setIsAsciiCapable(boolean b) { mAscii = b; return this; }
    public InputMethodSubtypeBuilder setSubtypeIconResId(int id) { mIconResId = id; return this; }
    public InputMethodSubtypeBuilder setSubtypeNameResId(int id) { mNameResId = id; return this; }
    public InputMethodSubtypeBuilder setSubtypeNameOverride(CharSequence name) { mNameOverride = name; return this; }
    public InputMethodSubtypeBuilder setPhysicalKeyboardHint(android.icu.util.ULocale languageTag, String layoutType) { mPkTag = languageTag; mPkLayout = layoutType; return this; }
    public InputMethodSubtypeBuilder setSubtypeId(int id) { mId = id; return this; }
    public InputMethodSubtypeBuilder setSubtypeLocale(String locale) { mLocale = locale; return this; }
    public InputMethodSubtypeBuilder setLanguageTag(String tag) { mLanguageTag = tag; return this; }
    public InputMethodSubtypeBuilder setSubtypeMode(String mode) { mMode = mode; return this; }
    public InputMethodSubtypeBuilder setSubtypeExtraValue(String extra) { mExtraValue = extra; return this; }
    public InputMethodSubtype build() { return new InputMethodSubtype(mNameResId, mIconResId, mLocale, mLanguageTag, mMode, mExtraValue, mAux, mOverrides, mId, mAscii, mNameOverride, mPkTag, mPkLayout); }
  }
}

package android.icu.lang;

/** android.icu.lang.UCharacter: the properties java.lang.Character and a2w.UnicodeData have; the rest are stubs. */
public final class UCharacter implements android.icu.lang.UCharacterEnums.ECharacterCategory, android.icu.lang.UCharacterEnums.ECharacterDirection {
  public static final int FOLD_CASE_DEFAULT = 0;
  public static final int FOLD_CASE_EXCLUDE_SPECIAL_I = 1;
  public static final int MAX_CODE_POINT = 1114111;
  public static final char MAX_HIGH_SURROGATE = (char) 56319;
  public static final char MAX_LOW_SURROGATE = (char) 57343;
  public static final int MAX_RADIX = 36;
  public static final char MAX_SURROGATE = (char) 57343;
  public static final int MAX_VALUE = 1114111;
  public static final int MIN_CODE_POINT = 0;
  public static final char MIN_HIGH_SURROGATE = (char) 55296;
  public static final char MIN_LOW_SURROGATE = (char) 56320;
  public static final int MIN_RADIX = 2;
  public static final int MIN_SUPPLEMENTARY_CODE_POINT = 65536;
  public static final char MIN_SURROGATE = (char) 55296;
  public static final int MIN_VALUE = 0;
  public static final double NO_NUMERIC_VALUE = -1.23456789E8d;
  public static final int REPLACEMENT_CHAR = 65533;
  public static final int SUPPLEMENTARY_MIN_VALUE = 65536;
  public static final int TITLECASE_NO_BREAK_ADJUSTMENT = 512;
  public static final int TITLECASE_NO_LOWERCASE = 256;
  /** for subclasses and this class's own constructors (not part of the API) */
  protected UCharacter(a2w.Hidden hidden) { }
  /** ECharacterDirection by java.lang.Character.DIRECTIONALITY_* (+ 1): undefined is left-to-right */
  private static final int[] DIRECTION = {LEFT_TO_RIGHT, LEFT_TO_RIGHT, RIGHT_TO_LEFT, RIGHT_TO_LEFT_ARABIC, EUROPEAN_NUMBER, EUROPEAN_NUMBER_SEPARATOR,
      EUROPEAN_NUMBER_TERMINATOR, ARABIC_NUMBER, COMMON_NUMBER_SEPARATOR, DIR_NON_SPACING_MARK, BOUNDARY_NEUTRAL, BLOCK_SEPARATOR, SEGMENT_SEPARATOR,
      WHITE_SPACE_NEUTRAL, OTHER_NEUTRAL, LEFT_TO_RIGHT_EMBEDDING, LEFT_TO_RIGHT_OVERRIDE, RIGHT_TO_LEFT_EMBEDDING, RIGHT_TO_LEFT_OVERRIDE,
      POP_DIRECTIONAL_FORMAT, LEFT_TO_RIGHT_ISOLATE, RIGHT_TO_LEFT_ISOLATE, FIRST_STRONG_ISOLATE, POP_DIRECTIONAL_ISOLATE};
  public static int charCount(int cp) { return Character.charCount(cp); }
  public static int codePointAt(java.lang.CharSequence seq, int index) { return Character.codePointAt(seq, index); }
  public static int codePointAt(char[] text, int index) { return Character.codePointAt(text, index); }
  public static int codePointAt(char[] text, int index, int limit) { if (index >= limit || limit > text.length) throw new IndexOutOfBoundsException(); return Character.codePointAt(text, index, limit); }
  public static int codePointBefore(java.lang.CharSequence seq, int index) { return Character.codePointBefore(seq, index); }
  public static int codePointBefore(char[] text, int index) { return Character.codePointBefore(text, index); }
  public static int codePointBefore(char[] text, int index, int limit) { if (index <= limit || limit < 0) throw new IndexOutOfBoundsException(); return Character.codePointBefore(text, index, limit); }
  public static int codePointCount(java.lang.CharSequence text, int start, int limit) { if (start < 0 || limit < start || limit > text.length()) throw new IndexOutOfBoundsException("start (" + start + ") or limit (" + limit + ") invalid or out of range 0, " + text.length()); return Character.codePointCount(text, start, limit); }
  public static int codePointCount(char[] text, int start, int limit) { if (start < 0 || limit < start || limit > text.length) throw new IndexOutOfBoundsException("start (" + start + ") or limit (" + limit + ") invalid or out of range 0, " + text.length); return Character.codePointCount(text, start, limit - start); }
  public static int digit(int ch) { return Character.digit(ch, 10); }
  public static int digit(int ch, int radix) { return Character.digit(ch, radix); }
  public static int foldCase(int ch, int options) { return a2w.CaseFolding.simple(ch, options == FOLD_CASE_EXCLUDE_SPECIAL_I); }
  public static int foldCase(int ch, boolean defaultmapping) { return foldCase(ch, defaultmapping ? FOLD_CASE_DEFAULT : FOLD_CASE_EXCLUDE_SPECIAL_I); }
  public static java.lang.String foldCase(java.lang.String str, int options) { return a2w.CaseFolding.full(str, options == FOLD_CASE_EXCLUDE_SPECIAL_I); }
  public static java.lang.String foldCase(java.lang.String str, boolean defaultmapping) { return foldCase(str, defaultmapping ? FOLD_CASE_DEFAULT : FOLD_CASE_EXCLUDE_SPECIAL_I); }
  public static char forDigit(int digit, int radix) { return Character.forDigit(digit, radix); }
  @a2w.NotImplemented public static android.icu.util.VersionInfo getAge(int ch) { a2w.Stubs.hit("android.icu.lang.UCharacter.getAge"); return null; }
  public static int getBidiPairedBracket(int c) { return a2w.UcdData.pairedBracket(c); }
  @a2w.NotImplemented public static int getCharFromExtendedName(java.lang.String name) { a2w.Stubs.hit("android.icu.lang.UCharacter.getCharFromExtendedName"); return 0; }
  @a2w.NotImplemented public static int getCharFromName(java.lang.String name) { a2w.Stubs.hit("android.icu.lang.UCharacter.getCharFromName"); return 0; }
  @a2w.NotImplemented public static int getCharFromNameAlias(java.lang.String name) { a2w.Stubs.hit("android.icu.lang.UCharacter.getCharFromNameAlias"); return 0; }
  public static int getCodePoint(char char16) { if (Character.isSurrogate(char16)) throw new IllegalArgumentException("Illegal codepoint"); return char16; }
  public static int getCodePoint(char lead, char trail) { if (Character.isSurrogatePair(lead, trail)) return Character.toCodePoint(lead, trail); throw new IllegalArgumentException("Illegal surrogate characters"); }
  public static int getCodePoint(int lead, int trail) { if (lead >= 0 && lead <= 0xFFFF && trail >= 0 && trail <= 0xFFFF && Character.isSurrogatePair((char) lead, (char) trail)) return Character.toCodePoint((char) lead, (char) trail); throw new IllegalArgumentException("Not a valid surrogate pair"); }
  public static int getCombiningClass(int ch) { if (ch < MIN_VALUE || ch > MAX_VALUE) throw new IllegalArgumentException("Codepoint out of bounds"); return a2w.UcdData.combiningClass(ch); }
  public static int getDirection(int ch) { return DIRECTION[a2w.jdk.Compat.Character_getDirectionality(ch) + 1]; }
  public static byte getDirectionality(int cp) { return a2w.jdk.Compat.Character_getDirectionality(cp); }
  @a2w.NotImplemented public static java.lang.String getExtendedName(int ch) { a2w.Stubs.hit("android.icu.lang.UCharacter.getExtendedName"); return null; }
  @a2w.NotImplemented public static android.icu.util.ValueIterator getExtendedNameIterator() { a2w.Stubs.hit("android.icu.lang.UCharacter.getExtendedNameIterator"); return null; }
  @a2w.NotImplemented public static int getHanNumericValue(int ch) { a2w.Stubs.hit("android.icu.lang.UCharacter.getHanNumericValue"); return 0; }
  @a2w.NotImplemented public static int getIntPropertyMaxValue(int type) { a2w.Stubs.hit("android.icu.lang.UCharacter.getIntPropertyMaxValue"); return 0; }
  @a2w.NotImplemented public static int getIntPropertyMinValue(int type) { a2w.Stubs.hit("android.icu.lang.UCharacter.getIntPropertyMinValue"); return 0; }
  @a2w.NotImplemented public static int getIntPropertyValue(int ch, int type) { a2w.Stubs.hit("android.icu.lang.UCharacter.getIntPropertyValue"); return 0; }
  public static int getMirror(int ch) { return a2w.UcdData.mirror(ch); }
  @a2w.NotImplemented public static java.lang.String getName(int ch) { a2w.Stubs.hit("android.icu.lang.UCharacter.getName"); return null; }
  @a2w.NotImplemented public static java.lang.String getName(java.lang.String s, java.lang.String separator) { a2w.Stubs.hit("android.icu.lang.UCharacter.getName"); return null; }
  @a2w.NotImplemented public static java.lang.String getNameAlias(int ch) { a2w.Stubs.hit("android.icu.lang.UCharacter.getNameAlias"); return null; }
  @a2w.NotImplemented public static android.icu.util.ValueIterator getNameIterator() { a2w.Stubs.hit("android.icu.lang.UCharacter.getNameIterator"); return null; }
  public static int getNumericValue(int ch) { return Character.getNumericValue(ch); }
  @a2w.NotImplemented public static int getPropertyEnum(java.lang.CharSequence propertyAlias) { a2w.Stubs.hit("android.icu.lang.UCharacter.getPropertyEnum"); return 0; }
  @a2w.NotImplemented public static java.lang.String getPropertyName(int property, int nameChoice) { a2w.Stubs.hit("android.icu.lang.UCharacter.getPropertyName"); return null; }
  @a2w.NotImplemented public static int getPropertyValueEnum(int property, java.lang.CharSequence valueAlias) { a2w.Stubs.hit("android.icu.lang.UCharacter.getPropertyValueEnum"); return 0; }
  @a2w.NotImplemented public static java.lang.String getPropertyValueName(int property, int value, int nameChoice) { a2w.Stubs.hit("android.icu.lang.UCharacter.getPropertyValueName"); return null; }
  public static int getType(int ch) { int t = Character.getType(ch); return t >= 18 ? t - 1 : t; }
  @a2w.NotImplemented public static android.icu.util.RangeValueIterator getTypeIterator() { a2w.Stubs.hit("android.icu.lang.UCharacter.getTypeIterator"); return null; }
  public static double getUnicodeNumericValue(int ch) { double v = a2w.UcdData.numericValue(ch); return Double.isNaN(v) ? NO_NUMERIC_VALUE : v; }
  @a2w.NotImplemented public static android.icu.util.VersionInfo getUnicodeVersion() { a2w.Stubs.hit("android.icu.lang.UCharacter.getUnicodeVersion"); return null; }
  @a2w.NotImplemented public static boolean hasBinaryProperty(int ch, int property) { a2w.Stubs.hit("android.icu.lang.UCharacter.hasBinaryProperty"); return false; }
  @a2w.NotImplemented public static boolean hasBinaryProperty(java.lang.CharSequence s, int property) { a2w.Stubs.hit("android.icu.lang.UCharacter.hasBinaryProperty"); return false; }
  public static boolean isBMP(int ch) { return ch >= 0 && ch <= 0xFFFF; }
  public static boolean isBaseForm(int ch) { int t = getType(ch); return t == DECIMAL_DIGIT_NUMBER || t == OTHER_NUMBER || t == LETTER_NUMBER || t == UPPERCASE_LETTER || t == LOWERCASE_LETTER || t == TITLECASE_LETTER || t == MODIFIER_LETTER || t == OTHER_LETTER || t == NON_SPACING_MARK || t == ENCLOSING_MARK || t == COMBINING_SPACING_MARK; }
  public static boolean isDefined(int ch) { return Character.isDefined(ch); }
  public static boolean isDigit(int ch) { return Character.isDigit(ch); }
  public static boolean isHighSurrogate(char ch) { return Character.isHighSurrogate(ch); }
  public static boolean isHighSurrogate(int codePoint) { return codePoint >= MIN_HIGH_SURROGATE && codePoint <= MAX_HIGH_SURROGATE; }
  public static boolean isISOControl(int ch) { return Character.isISOControl(ch); }
  public static boolean isIdentifierIgnorable(int ch) { return Character.isIdentifierIgnorable(ch); }
  public static boolean isJavaIdentifierPart(int cp) { return Character.isJavaIdentifierPart(cp); }
  public static boolean isJavaIdentifierStart(int cp) { return Character.isJavaIdentifierStart(cp); }
  public static boolean isLegal(int ch) { if (ch < MIN_VALUE || ch > MAX_VALUE) return false; if (ch >= MIN_SURROGATE && ch <= MAX_SURROGATE) return false; return (ch & 0xFFFE) != 0xFFFE && !(ch >= 0xFDD0 && ch <= 0xFDEF); }
  public static boolean isLegal(java.lang.String str) { if (str == null) return false; for (int i = 0; i < str.length(); ) { int cp = str.codePointAt(i); if (!isLegal(cp)) return false; i += Character.charCount(cp); } return true; }
  public static boolean isLetter(int ch) { return Character.isLetter(ch); }
  public static boolean isLetterOrDigit(int ch) { return Character.isLetterOrDigit(ch); }
  public static boolean isLowSurrogate(char ch) { return Character.isLowSurrogate(ch); }
  public static boolean isLowSurrogate(int codePoint) { return codePoint >= MIN_LOW_SURROGATE && codePoint <= MAX_LOW_SURROGATE; }
  public static boolean isLowerCase(int ch) { return getType(ch) == LOWERCASE_LETTER; }
  public static boolean isMirrored(int ch) { return a2w.jdk.Compat.Character_isMirrored(ch); }
  public static boolean isPrintable(int ch) { int t = getType(ch); return t != UNASSIGNED && t != CONTROL && t != FORMAT && t != PRIVATE_USE && t != SURROGATE; }
  public static boolean isSpaceChar(int ch) { return Character.isSpaceChar(ch); }
  public static boolean isSupplementary(int ch) { return ch >= MIN_SUPPLEMENTARY_CODE_POINT && ch <= MAX_CODE_POINT; }
  public static boolean isSupplementaryCodePoint(int cp) { return Character.isSupplementaryCodePoint(cp); }
  public static boolean isSurrogatePair(char high, char low) { return Character.isSurrogatePair(high, low); }
  public static boolean isSurrogatePair(int high, int low) { return isHighSurrogate(high) && isLowSurrogate(low); }
  public static boolean isTitleCase(int ch) { return getType(ch) == TITLECASE_LETTER; }
  public static boolean isUAlphabetic(int ch) { return Character.isAlphabetic(ch); }
  public static boolean isULowercase(int ch) { return Character.isLowerCase(ch); }
  public static boolean isUUppercase(int ch) { return Character.isUpperCase(ch); }
  public static boolean isUWhiteSpace(int ch) { return ch >= 0x9 && ch <= 0xD || ch == 0x20 || ch == 0x85 || ch == 0xA0 || ch == 0x1680 || ch >= 0x2000 && ch <= 0x200A || ch == 0x2028 || ch == 0x2029 || ch == 0x202F || ch == 0x205F || ch == 0x3000; }
  public static boolean isUnicodeIdentifierPart(int ch) { return Character.isUnicodeIdentifierPart(ch); }
  public static boolean isUnicodeIdentifierStart(int ch) { return Character.isUnicodeIdentifierStart(ch); }
  public static boolean isUpperCase(int ch) { return getType(ch) == UPPERCASE_LETTER; }
  public static boolean isValidCodePoint(int cp) { return Character.isValidCodePoint(cp); }
  public static boolean isWhitespace(int ch) { return Character.isWhitespace(ch); }
  public static int offsetByCodePoints(java.lang.CharSequence text, int index, int codePointOffset) { return Character.offsetByCodePoints(text, index, codePointOffset); }
  public static int offsetByCodePoints(char[] text, int start, int count, int index, int codePointOffset) { return Character.offsetByCodePoints(text, start, count, index, codePointOffset); }
  public static char[] toChars(int cp) { return Character.toChars(cp); }
  public static int toChars(int cp, char[] dst, int dstIndex) { return Character.toChars(cp, dst, dstIndex); }
  public static int toCodePoint(char high, char low) { return Character.toCodePoint(high, low); }
  public static int toCodePoint(int high, int low) { return Character.toCodePoint((char) high, (char) low); }
  public static int toLowerCase(int ch) { return Character.toLowerCase(ch); }
  @a2w.NotImplemented public static java.lang.String toLowerCase(android.icu.util.ULocale locale, java.lang.String str) { a2w.Stubs.hit("android.icu.lang.UCharacter.toLowerCase"); return null; }
  public static java.lang.String toLowerCase(java.lang.String str) { return str.toLowerCase(java.util.Locale.getDefault()); }
  public static java.lang.String toLowerCase(java.util.Locale locale, java.lang.String str) { return str.toLowerCase(locale == null ? java.util.Locale.getDefault() : locale); }
  public static java.lang.String toString(int ch) { if (ch < MIN_VALUE || ch > MAX_VALUE) return null; return new String(Character.toChars(ch)); }
  public static int toTitleCase(int ch) { return Character.toTitleCase(ch); }
  @a2w.NotImplemented public static java.lang.String toTitleCase(android.icu.util.ULocale locale, java.lang.String str, android.icu.text.BreakIterator titleIter) { a2w.Stubs.hit("android.icu.lang.UCharacter.toTitleCase"); return null; }
  @a2w.NotImplemented public static java.lang.String toTitleCase(android.icu.util.ULocale locale, java.lang.String str, android.icu.text.BreakIterator titleIter, int options) { a2w.Stubs.hit("android.icu.lang.UCharacter.toTitleCase"); return null; }
  @a2w.NotImplemented public static java.lang.String toTitleCase(java.lang.String str, android.icu.text.BreakIterator breakiter) { a2w.Stubs.hit("android.icu.lang.UCharacter.toTitleCase"); return null; }
  @a2w.NotImplemented public static java.lang.String toTitleCase(java.util.Locale locale, java.lang.String str, android.icu.text.BreakIterator breakiter) { a2w.Stubs.hit("android.icu.lang.UCharacter.toTitleCase"); return null; }
  @a2w.NotImplemented public static java.lang.String toTitleCase(java.util.Locale locale, java.lang.String str, android.icu.text.BreakIterator titleIter, int options) { a2w.Stubs.hit("android.icu.lang.UCharacter.toTitleCase"); return null; }
  public static int toUpperCase(int ch) { return Character.toUpperCase(ch); }
  @a2w.NotImplemented public static java.lang.String toUpperCase(android.icu.util.ULocale locale, java.lang.String str) { a2w.Stubs.hit("android.icu.lang.UCharacter.toUpperCase"); return null; }
  public static java.lang.String toUpperCase(java.lang.String str) { return str.toUpperCase(java.util.Locale.getDefault()); }
  public static java.lang.String toUpperCase(java.util.Locale locale, java.lang.String str) { return str.toUpperCase(locale == null ? java.util.Locale.getDefault() : locale); }

  public interface BidiPairedBracketType {
    int CLOSE = 2;
    int NONE = 0;
    int OPEN = 1;
  }

  public interface DecompositionType {
    int CANONICAL = 1;
    int CIRCLE = 3;
    int COMPAT = 2;
    int FINAL = 4;
    int FONT = 5;
    int FRACTION = 6;
    int INITIAL = 7;
    int ISOLATED = 8;
    int MEDIAL = 9;
    int NARROW = 10;
    int NOBREAK = 11;
    int NONE = 0;
    int SMALL = 12;
    int SQUARE = 13;
    int SUB = 14;
    int SUPER = 15;
    int VERTICAL = 16;
    int WIDE = 17;
  }

  public interface EastAsianWidth {
    int AMBIGUOUS = 1;
    int FULLWIDTH = 3;
    int HALFWIDTH = 2;
    int NARROW = 4;
    int NEUTRAL = 0;
    int WIDE = 5;
  }

  public interface GraphemeClusterBreak {
    int CONTROL = 1;
    int CR = 2;
    int EXTEND = 3;
    int E_BASE = 13;
    int E_BASE_GAZ = 14;
    int E_MODIFIER = 15;
    int GLUE_AFTER_ZWJ = 16;
    int L = 4;
    int LF = 5;
    int LV = 6;
    int LVT = 7;
    int OTHER = 0;
    int PREPEND = 11;
    int REGIONAL_INDICATOR = 12;
    int SPACING_MARK = 10;
    int T = 8;
    int V = 9;
    int ZWJ = 17;
  }

  public interface HangulSyllableType {
    int LEADING_JAMO = 1;
    int LVT_SYLLABLE = 5;
    int LV_SYLLABLE = 4;
    int NOT_APPLICABLE = 0;
    int TRAILING_JAMO = 3;
    int VOWEL_JAMO = 2;
  }

  public interface IndicPositionalCategory {
    int BOTTOM = 1;
    int BOTTOM_AND_LEFT = 2;
    int BOTTOM_AND_RIGHT = 3;
    int LEFT = 4;
    int LEFT_AND_RIGHT = 5;
    int NA = 0;
    int OVERSTRUCK = 6;
    int RIGHT = 7;
    int TOP = 8;
    int TOP_AND_BOTTOM = 9;
    int TOP_AND_BOTTOM_AND_LEFT = 15;
    int TOP_AND_BOTTOM_AND_RIGHT = 10;
    int TOP_AND_LEFT = 11;
    int TOP_AND_LEFT_AND_RIGHT = 12;
    int TOP_AND_RIGHT = 13;
    int VISUAL_ORDER_LEFT = 14;
  }

  public interface IndicSyllabicCategory {
    int AVAGRAHA = 1;
    int BINDU = 2;
    int BRAHMI_JOINING_NUMBER = 3;
    int CANTILLATION_MARK = 4;
    int CONSONANT = 5;
    int CONSONANT_DEAD = 6;
    int CONSONANT_FINAL = 7;
    int CONSONANT_HEAD_LETTER = 8;
    int CONSONANT_INITIAL_POSTFIXED = 9;
    int CONSONANT_KILLER = 10;
    int CONSONANT_MEDIAL = 11;
    int CONSONANT_PLACEHOLDER = 12;
    int CONSONANT_PRECEDING_REPHA = 13;
    int CONSONANT_PREFIXED = 14;
    int CONSONANT_SUBJOINED = 15;
    int CONSONANT_SUCCEEDING_REPHA = 16;
    int CONSONANT_WITH_STACKER = 17;
    int GEMINATION_MARK = 18;
    int INVISIBLE_STACKER = 19;
    int JOINER = 20;
    int MODIFYING_LETTER = 21;
    int NON_JOINER = 22;
    int NUKTA = 23;
    int NUMBER = 24;
    int NUMBER_JOINER = 25;
    int OTHER = 0;
    int PURE_KILLER = 26;
    int REGISTER_SHIFTER = 27;
    int SYLLABLE_MODIFIER = 28;
    int TONE_LETTER = 29;
    int TONE_MARK = 30;
    int VIRAMA = 31;
    int VISARGA = 32;
    int VOWEL = 33;
    int VOWEL_DEPENDENT = 34;
    int VOWEL_INDEPENDENT = 35;
  }

  public interface JoiningGroup {
    int AFRICAN_FEH = 86;
    int AFRICAN_NOON = 87;
    int AFRICAN_QAF = 88;
    int AIN = 1;
    int ALAPH = 2;
    int ALEF = 3;
    int BEH = 4;
    int BETH = 5;
    int BURUSHASKI_YEH_BARREE = 54;
    int DAL = 6;
    int DALATH_RISH = 7;
    int E = 8;
    int FARSI_YEH = 55;
    int FE = 51;
    int FEH = 9;
    int FINAL_SEMKATH = 10;
    int GAF = 11;
    int GAMAL = 12;
    int HAH = 13;
    int HAMZA_ON_HEH_GOAL = 14;
    int HANIFI_ROHINGYA_KINNA_YA = 100;
    int HANIFI_ROHINGYA_PA = 101;
    int HE = 15;
    int HEH = 16;
    int HEH_GOAL = 17;
    int HETH = 18;
    int KAF = 19;
    int KAPH = 20;
    int KHAPH = 52;
    int KNOTTED_HEH = 21;
    int LAM = 22;
    int LAMADH = 23;
    int MALAYALAM_BHA = 89;
    int MALAYALAM_JA = 90;
    int MALAYALAM_LLA = 91;
    int MALAYALAM_LLLA = 92;
    int MALAYALAM_NGA = 93;
    int MALAYALAM_NNA = 94;
    int MALAYALAM_NNNA = 95;
    int MALAYALAM_NYA = 96;
    int MALAYALAM_RA = 97;
    int MALAYALAM_SSA = 98;
    int MALAYALAM_TTA = 99;
    int MANICHAEAN_ALEPH = 58;
    int MANICHAEAN_AYIN = 59;
    int MANICHAEAN_BETH = 60;
    int MANICHAEAN_DALETH = 61;
    int MANICHAEAN_DHAMEDH = 62;
    int MANICHAEAN_FIVE = 63;
    int MANICHAEAN_GIMEL = 64;
    int MANICHAEAN_HETH = 65;
    int MANICHAEAN_HUNDRED = 66;
    int MANICHAEAN_KAPH = 67;
    int MANICHAEAN_LAMEDH = 68;
    int MANICHAEAN_MEM = 69;
    int MANICHAEAN_NUN = 70;
    int MANICHAEAN_ONE = 71;
    int MANICHAEAN_PE = 72;
    int MANICHAEAN_QOPH = 73;
    int MANICHAEAN_RESH = 74;
    int MANICHAEAN_SADHE = 75;
    int MANICHAEAN_SAMEKH = 76;
    int MANICHAEAN_TAW = 77;
    int MANICHAEAN_TEN = 78;
    int MANICHAEAN_TETH = 79;
    int MANICHAEAN_THAMEDH = 80;
    int MANICHAEAN_TWENTY = 81;
    int MANICHAEAN_WAW = 82;
    int MANICHAEAN_YODH = 83;
    int MANICHAEAN_ZAYIN = 84;
    int MEEM = 24;
    int MIM = 25;
    int NOON = 26;
    int NO_JOINING_GROUP = 0;
    int NUN = 27;
    int NYA = 56;
    int PE = 28;
    int QAF = 29;
    int QAPH = 30;
    int REH = 31;
    int REVERSED_PE = 32;
    int ROHINGYA_YEH = 57;
    int SAD = 33;
    int SADHE = 34;
    int SEEN = 35;
    int SEMKATH = 36;
    int SHIN = 37;
    int STRAIGHT_WAW = 85;
    int SWASH_KAF = 38;
    int SYRIAC_WAW = 39;
    int TAH = 40;
    int TAW = 41;
    int TEH_MARBUTA = 42;
    int TEH_MARBUTA_GOAL = 14;
    int TETH = 43;
    int THIN_YEH = 102;
    int VERTICAL_TAIL = 103;
    int WAW = 44;
    int YEH = 45;
    int YEH_BARREE = 46;
    int YEH_WITH_TAIL = 47;
    int YUDH = 48;
    int YUDH_HE = 49;
    int ZAIN = 50;
    int ZHAIN = 53;
  }

  public interface JoiningType {
    int DUAL_JOINING = 2;
    int JOIN_CAUSING = 1;
    int LEFT_JOINING = 3;
    int NON_JOINING = 0;
    int RIGHT_JOINING = 4;
    int TRANSPARENT = 5;
  }

  public interface LineBreak {
    int AKSARA = 43;
    int AKSARA_PREBASE = 44;
    int AKSARA_START = 45;
    int ALPHABETIC = 2;
    int AMBIGUOUS = 1;
    int BREAK_AFTER = 4;
    int BREAK_BEFORE = 5;
    int BREAK_BOTH = 3;
    int BREAK_SYMBOLS = 27;
    int CARRIAGE_RETURN = 10;
    int CLOSE_PARENTHESIS = 36;
    int CLOSE_PUNCTUATION = 8;
    int COMBINING_MARK = 9;
    int COMPLEX_CONTEXT = 24;
    int CONDITIONAL_JAPANESE_STARTER = 37;
    int CONTINGENT_BREAK = 7;
    int EXCLAMATION = 11;
    int E_BASE = 40;
    int E_MODIFIER = 41;
    int GLUE = 12;
    int H2 = 31;
    int H3 = 32;
    int HEBREW_LETTER = 38;
    int HYPHEN = 13;
    int IDEOGRAPHIC = 14;
    int INFIX_NUMERIC = 16;
    int INSEPARABLE = 15;
    int INSEPERABLE = 15;
    int JL = 33;
    int JT = 34;
    int JV = 35;
    int LINE_FEED = 17;
    int MANDATORY_BREAK = 6;
    int NEXT_LINE = 29;
    int NONSTARTER = 18;
    int NUMERIC = 19;
    int OPEN_PUNCTUATION = 20;
    int POSTFIX_NUMERIC = 21;
    int PREFIX_NUMERIC = 22;
    int QUOTATION = 23;
    int REGIONAL_INDICATOR = 39;
    int SPACE = 26;
    int SURROGATE = 25;
    int UNKNOWN = 0;
    int VIRAMA = 47;
    int VIRAMA_FINAL = 46;
    int WORD_JOINER = 30;
    int ZWJ = 42;
    int ZWSPACE = 28;
  }

  public interface NumericType {
    int DECIMAL = 1;
    int DIGIT = 2;
    int NONE = 0;
    int NUMERIC = 3;
  }

  public interface SentenceBreak {
    int ATERM = 1;
    int CLOSE = 2;
    int CR = 11;
    int EXTEND = 12;
    int FORMAT = 3;
    int LF = 13;
    int LOWER = 4;
    int NUMERIC = 5;
    int OLETTER = 6;
    int OTHER = 0;
    int SCONTINUE = 14;
    int SEP = 7;
    int SP = 8;
    int STERM = 9;
    int UPPER = 10;
  }

  public static final class UnicodeBlock extends a2w.jdk.lang.Character$Subset {
    private static final java.util.HashMap<Integer, UnicodeBlock> BY_ID = new java.util.HashMap<>();
    private static final java.util.HashMap<String, UnicodeBlock> BY_NAME = new java.util.HashMap<>();
    public static final int INVALID_CODE_ID = -1;
    public static final int BASIC_LATIN_ID = 1;
    public static final int LATIN_1_SUPPLEMENT_ID = 2;
    public static final int LATIN_EXTENDED_A_ID = 3;
    public static final int LATIN_EXTENDED_B_ID = 4;
    public static final int IPA_EXTENSIONS_ID = 5;
    public static final int SPACING_MODIFIER_LETTERS_ID = 6;
    public static final int COMBINING_DIACRITICAL_MARKS_ID = 7;
    public static final int GREEK_ID = 8;
    public static final int CYRILLIC_ID = 9;
    public static final int ARMENIAN_ID = 10;
    public static final int HEBREW_ID = 11;
    public static final int ARABIC_ID = 12;
    public static final int SYRIAC_ID = 13;
    public static final int THAANA_ID = 14;
    public static final int DEVANAGARI_ID = 15;
    public static final int BENGALI_ID = 16;
    public static final int GURMUKHI_ID = 17;
    public static final int GUJARATI_ID = 18;
    public static final int ORIYA_ID = 19;
    public static final int TAMIL_ID = 20;
    public static final int TELUGU_ID = 21;
    public static final int KANNADA_ID = 22;
    public static final int MALAYALAM_ID = 23;
    public static final int SINHALA_ID = 24;
    public static final int THAI_ID = 25;
    public static final int LAO_ID = 26;
    public static final int TIBETAN_ID = 27;
    public static final int MYANMAR_ID = 28;
    public static final int GEORGIAN_ID = 29;
    public static final int HANGUL_JAMO_ID = 30;
    public static final int ETHIOPIC_ID = 31;
    public static final int CHEROKEE_ID = 32;
    public static final int UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_ID = 33;
    public static final int OGHAM_ID = 34;
    public static final int RUNIC_ID = 35;
    public static final int KHMER_ID = 36;
    public static final int MONGOLIAN_ID = 37;
    public static final int LATIN_EXTENDED_ADDITIONAL_ID = 38;
    public static final int GREEK_EXTENDED_ID = 39;
    public static final int GENERAL_PUNCTUATION_ID = 40;
    public static final int SUPERSCRIPTS_AND_SUBSCRIPTS_ID = 41;
    public static final int CURRENCY_SYMBOLS_ID = 42;
    public static final int COMBINING_MARKS_FOR_SYMBOLS_ID = 43;
    public static final int LETTERLIKE_SYMBOLS_ID = 44;
    public static final int NUMBER_FORMS_ID = 45;
    public static final int ARROWS_ID = 46;
    public static final int MATHEMATICAL_OPERATORS_ID = 47;
    public static final int MISCELLANEOUS_TECHNICAL_ID = 48;
    public static final int CONTROL_PICTURES_ID = 49;
    public static final int OPTICAL_CHARACTER_RECOGNITION_ID = 50;
    public static final int ENCLOSED_ALPHANUMERICS_ID = 51;
    public static final int BOX_DRAWING_ID = 52;
    public static final int BLOCK_ELEMENTS_ID = 53;
    public static final int GEOMETRIC_SHAPES_ID = 54;
    public static final int MISCELLANEOUS_SYMBOLS_ID = 55;
    public static final int DINGBATS_ID = 56;
    public static final int BRAILLE_PATTERNS_ID = 57;
    public static final int CJK_RADICALS_SUPPLEMENT_ID = 58;
    public static final int KANGXI_RADICALS_ID = 59;
    public static final int IDEOGRAPHIC_DESCRIPTION_CHARACTERS_ID = 60;
    public static final int CJK_SYMBOLS_AND_PUNCTUATION_ID = 61;
    public static final int HIRAGANA_ID = 62;
    public static final int KATAKANA_ID = 63;
    public static final int BOPOMOFO_ID = 64;
    public static final int HANGUL_COMPATIBILITY_JAMO_ID = 65;
    public static final int KANBUN_ID = 66;
    public static final int BOPOMOFO_EXTENDED_ID = 67;
    public static final int ENCLOSED_CJK_LETTERS_AND_MONTHS_ID = 68;
    public static final int CJK_COMPATIBILITY_ID = 69;
    public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A_ID = 70;
    public static final int CJK_UNIFIED_IDEOGRAPHS_ID = 71;
    public static final int YI_SYLLABLES_ID = 72;
    public static final int YI_RADICALS_ID = 73;
    public static final int HANGUL_SYLLABLES_ID = 74;
    public static final int HIGH_SURROGATES_ID = 75;
    public static final int HIGH_PRIVATE_USE_SURROGATES_ID = 76;
    public static final int LOW_SURROGATES_ID = 77;
    public static final int PRIVATE_USE_AREA_ID = 78;
    public static final int PRIVATE_USE_ID = 78;
    public static final int CJK_COMPATIBILITY_IDEOGRAPHS_ID = 79;
    public static final int ALPHABETIC_PRESENTATION_FORMS_ID = 80;
    public static final int ARABIC_PRESENTATION_FORMS_A_ID = 81;
    public static final int COMBINING_HALF_MARKS_ID = 82;
    public static final int CJK_COMPATIBILITY_FORMS_ID = 83;
    public static final int SMALL_FORM_VARIANTS_ID = 84;
    public static final int ARABIC_PRESENTATION_FORMS_B_ID = 85;
    public static final int SPECIALS_ID = 86;
    public static final int HALFWIDTH_AND_FULLWIDTH_FORMS_ID = 87;
    public static final int OLD_ITALIC_ID = 88;
    public static final int GOTHIC_ID = 89;
    public static final int DESERET_ID = 90;
    public static final int BYZANTINE_MUSICAL_SYMBOLS_ID = 91;
    public static final int MUSICAL_SYMBOLS_ID = 92;
    public static final int MATHEMATICAL_ALPHANUMERIC_SYMBOLS_ID = 93;
    public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B_ID = 94;
    public static final int CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT_ID = 95;
    public static final int TAGS_ID = 96;
    public static final int CYRILLIC_SUPPLEMENTARY_ID = 97;
    public static final int CYRILLIC_SUPPLEMENT_ID = 97;
    public static final int TAGALOG_ID = 98;
    public static final int HANUNOO_ID = 99;
    public static final int BUHID_ID = 100;
    public static final int TAGBANWA_ID = 101;
    public static final int MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A_ID = 102;
    public static final int SUPPLEMENTAL_ARROWS_A_ID = 103;
    public static final int SUPPLEMENTAL_ARROWS_B_ID = 104;
    public static final int MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_ID = 105;
    public static final int SUPPLEMENTAL_MATHEMATICAL_OPERATORS_ID = 106;
    public static final int KATAKANA_PHONETIC_EXTENSIONS_ID = 107;
    public static final int VARIATION_SELECTORS_ID = 108;
    public static final int SUPPLEMENTARY_PRIVATE_USE_AREA_A_ID = 109;
    public static final int SUPPLEMENTARY_PRIVATE_USE_AREA_B_ID = 110;
    public static final int LIMBU_ID = 111;
    public static final int TAI_LE_ID = 112;
    public static final int KHMER_SYMBOLS_ID = 113;
    public static final int PHONETIC_EXTENSIONS_ID = 114;
    public static final int MISCELLANEOUS_SYMBOLS_AND_ARROWS_ID = 115;
    public static final int YIJING_HEXAGRAM_SYMBOLS_ID = 116;
    public static final int LINEAR_B_SYLLABARY_ID = 117;
    public static final int LINEAR_B_IDEOGRAMS_ID = 118;
    public static final int AEGEAN_NUMBERS_ID = 119;
    public static final int UGARITIC_ID = 120;
    public static final int SHAVIAN_ID = 121;
    public static final int OSMANYA_ID = 122;
    public static final int CYPRIOT_SYLLABARY_ID = 123;
    public static final int TAI_XUAN_JING_SYMBOLS_ID = 124;
    public static final int VARIATION_SELECTORS_SUPPLEMENT_ID = 125;
    public static final int ANCIENT_GREEK_MUSICAL_NOTATION_ID = 126;
    public static final int ANCIENT_GREEK_NUMBERS_ID = 127;
    public static final int ARABIC_SUPPLEMENT_ID = 128;
    public static final int BUGINESE_ID = 129;
    public static final int CJK_STROKES_ID = 130;
    public static final int COMBINING_DIACRITICAL_MARKS_SUPPLEMENT_ID = 131;
    public static final int COPTIC_ID = 132;
    public static final int ETHIOPIC_EXTENDED_ID = 133;
    public static final int ETHIOPIC_SUPPLEMENT_ID = 134;
    public static final int GEORGIAN_SUPPLEMENT_ID = 135;
    public static final int GLAGOLITIC_ID = 136;
    public static final int KHAROSHTHI_ID = 137;
    public static final int MODIFIER_TONE_LETTERS_ID = 138;
    public static final int NEW_TAI_LUE_ID = 139;
    public static final int OLD_PERSIAN_ID = 140;
    public static final int PHONETIC_EXTENSIONS_SUPPLEMENT_ID = 141;
    public static final int SUPPLEMENTAL_PUNCTUATION_ID = 142;
    public static final int SYLOTI_NAGRI_ID = 143;
    public static final int TIFINAGH_ID = 144;
    public static final int VERTICAL_FORMS_ID = 145;
    public static final int NKO_ID = 146;
    public static final int BALINESE_ID = 147;
    public static final int LATIN_EXTENDED_C_ID = 148;
    public static final int LATIN_EXTENDED_D_ID = 149;
    public static final int PHAGS_PA_ID = 150;
    public static final int PHOENICIAN_ID = 151;
    public static final int CUNEIFORM_ID = 152;
    public static final int CUNEIFORM_NUMBERS_AND_PUNCTUATION_ID = 153;
    public static final int COUNTING_ROD_NUMERALS_ID = 154;
    public static final int SUNDANESE_ID = 155;
    public static final int LEPCHA_ID = 156;
    public static final int OL_CHIKI_ID = 157;
    public static final int CYRILLIC_EXTENDED_A_ID = 158;
    public static final int VAI_ID = 159;
    public static final int CYRILLIC_EXTENDED_B_ID = 160;
    public static final int SAURASHTRA_ID = 161;
    public static final int KAYAH_LI_ID = 162;
    public static final int REJANG_ID = 163;
    public static final int CHAM_ID = 164;
    public static final int ANCIENT_SYMBOLS_ID = 165;
    public static final int PHAISTOS_DISC_ID = 166;
    public static final int LYCIAN_ID = 167;
    public static final int CARIAN_ID = 168;
    public static final int LYDIAN_ID = 169;
    public static final int MAHJONG_TILES_ID = 170;
    public static final int DOMINO_TILES_ID = 171;
    public static final int SAMARITAN_ID = 172;
    public static final int UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_ID = 173;
    public static final int TAI_THAM_ID = 174;
    public static final int VEDIC_EXTENSIONS_ID = 175;
    public static final int LISU_ID = 176;
    public static final int BAMUM_ID = 177;
    public static final int COMMON_INDIC_NUMBER_FORMS_ID = 178;
    public static final int DEVANAGARI_EXTENDED_ID = 179;
    public static final int HANGUL_JAMO_EXTENDED_A_ID = 180;
    public static final int JAVANESE_ID = 181;
    public static final int MYANMAR_EXTENDED_A_ID = 182;
    public static final int TAI_VIET_ID = 183;
    public static final int MEETEI_MAYEK_ID = 184;
    public static final int HANGUL_JAMO_EXTENDED_B_ID = 185;
    public static final int IMPERIAL_ARAMAIC_ID = 186;
    public static final int OLD_SOUTH_ARABIAN_ID = 187;
    public static final int AVESTAN_ID = 188;
    public static final int INSCRIPTIONAL_PARTHIAN_ID = 189;
    public static final int INSCRIPTIONAL_PAHLAVI_ID = 190;
    public static final int OLD_TURKIC_ID = 191;
    public static final int RUMI_NUMERAL_SYMBOLS_ID = 192;
    public static final int KAITHI_ID = 193;
    public static final int EGYPTIAN_HIEROGLYPHS_ID = 194;
    public static final int ENCLOSED_ALPHANUMERIC_SUPPLEMENT_ID = 195;
    public static final int ENCLOSED_IDEOGRAPHIC_SUPPLEMENT_ID = 196;
    public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C_ID = 197;
    public static final int MANDAIC_ID = 198;
    public static final int BATAK_ID = 199;
    public static final int ETHIOPIC_EXTENDED_A_ID = 200;
    public static final int BRAHMI_ID = 201;
    public static final int BAMUM_SUPPLEMENT_ID = 202;
    public static final int KANA_SUPPLEMENT_ID = 203;
    public static final int PLAYING_CARDS_ID = 204;
    public static final int MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS_ID = 205;
    public static final int EMOTICONS_ID = 206;
    public static final int TRANSPORT_AND_MAP_SYMBOLS_ID = 207;
    public static final int ALCHEMICAL_SYMBOLS_ID = 208;
    public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D_ID = 209;
    public static final int ARABIC_EXTENDED_A_ID = 210;
    public static final int ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS_ID = 211;
    public static final int CHAKMA_ID = 212;
    public static final int MEETEI_MAYEK_EXTENSIONS_ID = 213;
    public static final int MEROITIC_CURSIVE_ID = 214;
    public static final int MEROITIC_HIEROGLYPHS_ID = 215;
    public static final int MIAO_ID = 216;
    public static final int SHARADA_ID = 217;
    public static final int SORA_SOMPENG_ID = 218;
    public static final int SUNDANESE_SUPPLEMENT_ID = 219;
    public static final int TAKRI_ID = 220;
    public static final int BASSA_VAH_ID = 221;
    public static final int CAUCASIAN_ALBANIAN_ID = 222;
    public static final int COPTIC_EPACT_NUMBERS_ID = 223;
    public static final int COMBINING_DIACRITICAL_MARKS_EXTENDED_ID = 224;
    public static final int DUPLOYAN_ID = 225;
    public static final int ELBASAN_ID = 226;
    public static final int GEOMETRIC_SHAPES_EXTENDED_ID = 227;
    public static final int GRANTHA_ID = 228;
    public static final int KHOJKI_ID = 229;
    public static final int KHUDAWADI_ID = 230;
    public static final int LATIN_EXTENDED_E_ID = 231;
    public static final int LINEAR_A_ID = 232;
    public static final int MAHAJANI_ID = 233;
    public static final int MANICHAEAN_ID = 234;
    public static final int MENDE_KIKAKUI_ID = 235;
    public static final int MODI_ID = 236;
    public static final int MRO_ID = 237;
    public static final int MYANMAR_EXTENDED_B_ID = 238;
    public static final int NABATAEAN_ID = 239;
    public static final int OLD_NORTH_ARABIAN_ID = 240;
    public static final int OLD_PERMIC_ID = 241;
    public static final int ORNAMENTAL_DINGBATS_ID = 242;
    public static final int PAHAWH_HMONG_ID = 243;
    public static final int PALMYRENE_ID = 244;
    public static final int PAU_CIN_HAU_ID = 245;
    public static final int PSALTER_PAHLAVI_ID = 246;
    public static final int SHORTHAND_FORMAT_CONTROLS_ID = 247;
    public static final int SIDDHAM_ID = 248;
    public static final int SINHALA_ARCHAIC_NUMBERS_ID = 249;
    public static final int SUPPLEMENTAL_ARROWS_C_ID = 250;
    public static final int TIRHUTA_ID = 251;
    public static final int WARANG_CITI_ID = 252;
    public static final int AHOM_ID = 253;
    public static final int ANATOLIAN_HIEROGLYPHS_ID = 254;
    public static final int CHEROKEE_SUPPLEMENT_ID = 255;
    public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E_ID = 256;
    public static final int EARLY_DYNASTIC_CUNEIFORM_ID = 257;
    public static final int HATRAN_ID = 258;
    public static final int MULTANI_ID = 259;
    public static final int OLD_HUNGARIAN_ID = 260;
    public static final int SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS_ID = 261;
    public static final int SUTTON_SIGNWRITING_ID = 262;
    public static final int ADLAM_ID = 263;
    public static final int BHAIKSUKI_ID = 264;
    public static final int CYRILLIC_EXTENDED_C_ID = 265;
    public static final int GLAGOLITIC_SUPPLEMENT_ID = 266;
    public static final int IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION_ID = 267;
    public static final int MARCHEN_ID = 268;
    public static final int MONGOLIAN_SUPPLEMENT_ID = 269;
    public static final int NEWA_ID = 270;
    public static final int OSAGE_ID = 271;
    public static final int TANGUT_ID = 272;
    public static final int TANGUT_COMPONENTS_ID = 273;
    public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F_ID = 274;
    public static final int KANA_EXTENDED_A_ID = 275;
    public static final int MASARAM_GONDI_ID = 276;
    public static final int NUSHU_ID = 277;
    public static final int SOYOMBO_ID = 278;
    public static final int SYRIAC_SUPPLEMENT_ID = 279;
    public static final int ZANABAZAR_SQUARE_ID = 280;
    public static final int CHESS_SYMBOLS_ID = 281;
    public static final int DOGRA_ID = 282;
    public static final int GEORGIAN_EXTENDED_ID = 283;
    public static final int GUNJALA_GONDI_ID = 284;
    public static final int HANIFI_ROHINGYA_ID = 285;
    public static final int INDIC_SIYAQ_NUMBERS_ID = 286;
    public static final int MAKASAR_ID = 287;
    public static final int MAYAN_NUMERALS_ID = 288;
    public static final int MEDEFAIDRIN_ID = 289;
    public static final int OLD_SOGDIAN_ID = 290;
    public static final int SOGDIAN_ID = 291;
    public static final int EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS_ID = 292;
    public static final int ELYMAIC_ID = 293;
    public static final int NANDINAGARI_ID = 294;
    public static final int NYIAKENG_PUACHUE_HMONG_ID = 295;
    public static final int OTTOMAN_SIYAQ_NUMBERS_ID = 296;
    public static final int SMALL_KANA_EXTENSION_ID = 297;
    public static final int SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A_ID = 298;
    public static final int TAMIL_SUPPLEMENT_ID = 299;
    public static final int WANCHO_ID = 300;
    public static final int CHORASMIAN_ID = 301;
    public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G_ID = 302;
    public static final int DIVES_AKURU_ID = 303;
    public static final int KHITAN_SMALL_SCRIPT_ID = 304;
    public static final int LISU_SUPPLEMENT_ID = 305;
    public static final int SYMBOLS_FOR_LEGACY_COMPUTING_ID = 306;
    public static final int TANGUT_SUPPLEMENT_ID = 307;
    public static final int YEZIDI_ID = 308;
    public static final int ARABIC_EXTENDED_B_ID = 309;
    public static final int CYPRO_MINOAN_ID = 310;
    public static final int ETHIOPIC_EXTENDED_B_ID = 311;
    public static final int KANA_EXTENDED_B_ID = 312;
    public static final int LATIN_EXTENDED_F_ID = 313;
    public static final int LATIN_EXTENDED_G_ID = 314;
    public static final int OLD_UYGHUR_ID = 315;
    public static final int TANGSA_ID = 316;
    public static final int TOTO_ID = 317;
    public static final int UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_A_ID = 318;
    public static final int VITHKUQI_ID = 319;
    public static final int ZNAMENNY_MUSICAL_NOTATION_ID = 320;
    public static final int ARABIC_EXTENDED_C_ID = 321;
    public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_H_ID = 322;
    public static final int CYRILLIC_EXTENDED_D_ID = 323;
    public static final int DEVANAGARI_EXTENDED_A_ID = 324;
    public static final int KAKTOVIK_NUMERALS_ID = 325;
    public static final int KAWI_ID = 326;
    public static final int NAG_MUNDARI_ID = 327;
    public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_I_ID = 328;
    public static final android.icu.lang.UCharacter.UnicodeBlock ADLAM = new UnicodeBlock("ADLAM", ADLAM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock AEGEAN_NUMBERS = new UnicodeBlock("AEGEAN_NUMBERS", AEGEAN_NUMBERS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock AHOM = new UnicodeBlock("AHOM", AHOM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ALCHEMICAL_SYMBOLS = new UnicodeBlock("ALCHEMICAL_SYMBOLS", ALCHEMICAL_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ALPHABETIC_PRESENTATION_FORMS = new UnicodeBlock("ALPHABETIC_PRESENTATION_FORMS", ALPHABETIC_PRESENTATION_FORMS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ANATOLIAN_HIEROGLYPHS = new UnicodeBlock("ANATOLIAN_HIEROGLYPHS", ANATOLIAN_HIEROGLYPHS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ANCIENT_GREEK_MUSICAL_NOTATION = new UnicodeBlock("ANCIENT_GREEK_MUSICAL_NOTATION", ANCIENT_GREEK_MUSICAL_NOTATION_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ANCIENT_GREEK_NUMBERS = new UnicodeBlock("ANCIENT_GREEK_NUMBERS", ANCIENT_GREEK_NUMBERS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ANCIENT_SYMBOLS = new UnicodeBlock("ANCIENT_SYMBOLS", ANCIENT_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARABIC = new UnicodeBlock("ARABIC", ARABIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARABIC_EXTENDED_A = new UnicodeBlock("ARABIC_EXTENDED_A", ARABIC_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARABIC_EXTENDED_B = new UnicodeBlock("ARABIC_EXTENDED_B", ARABIC_EXTENDED_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARABIC_EXTENDED_C = new UnicodeBlock("ARABIC_EXTENDED_C", ARABIC_EXTENDED_C_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS = new UnicodeBlock("ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS", ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARABIC_PRESENTATION_FORMS_A = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_A", ARABIC_PRESENTATION_FORMS_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARABIC_PRESENTATION_FORMS_B = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_B", ARABIC_PRESENTATION_FORMS_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARABIC_SUPPLEMENT = new UnicodeBlock("ARABIC_SUPPLEMENT", ARABIC_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARMENIAN = new UnicodeBlock("ARMENIAN", ARMENIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ARROWS = new UnicodeBlock("ARROWS", ARROWS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock AVESTAN = new UnicodeBlock("AVESTAN", AVESTAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BALINESE = new UnicodeBlock("BALINESE", BALINESE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BAMUM = new UnicodeBlock("BAMUM", BAMUM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BAMUM_SUPPLEMENT = new UnicodeBlock("BAMUM_SUPPLEMENT", BAMUM_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BASIC_LATIN = new UnicodeBlock("BASIC_LATIN", BASIC_LATIN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BASSA_VAH = new UnicodeBlock("BASSA_VAH", BASSA_VAH_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BATAK = new UnicodeBlock("BATAK", BATAK_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BENGALI = new UnicodeBlock("BENGALI", BENGALI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BHAIKSUKI = new UnicodeBlock("BHAIKSUKI", BHAIKSUKI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BLOCK_ELEMENTS = new UnicodeBlock("BLOCK_ELEMENTS", BLOCK_ELEMENTS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BOPOMOFO = new UnicodeBlock("BOPOMOFO", BOPOMOFO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BOPOMOFO_EXTENDED = new UnicodeBlock("BOPOMOFO_EXTENDED", BOPOMOFO_EXTENDED_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BOX_DRAWING = new UnicodeBlock("BOX_DRAWING", BOX_DRAWING_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BRAHMI = new UnicodeBlock("BRAHMI", BRAHMI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BRAILLE_PATTERNS = new UnicodeBlock("BRAILLE_PATTERNS", BRAILLE_PATTERNS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BUGINESE = new UnicodeBlock("BUGINESE", BUGINESE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BUHID = new UnicodeBlock("BUHID", BUHID_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock BYZANTINE_MUSICAL_SYMBOLS = new UnicodeBlock("BYZANTINE_MUSICAL_SYMBOLS", BYZANTINE_MUSICAL_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CARIAN = new UnicodeBlock("CARIAN", CARIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CAUCASIAN_ALBANIAN = new UnicodeBlock("CAUCASIAN_ALBANIAN", CAUCASIAN_ALBANIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CHAKMA = new UnicodeBlock("CHAKMA", CHAKMA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CHAM = new UnicodeBlock("CHAM", CHAM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CHEROKEE = new UnicodeBlock("CHEROKEE", CHEROKEE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CHEROKEE_SUPPLEMENT = new UnicodeBlock("CHEROKEE_SUPPLEMENT", CHEROKEE_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CHESS_SYMBOLS = new UnicodeBlock("CHESS_SYMBOLS", CHESS_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CHORASMIAN = new UnicodeBlock("CHORASMIAN", CHORASMIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_COMPATIBILITY = new UnicodeBlock("CJK_COMPATIBILITY", CJK_COMPATIBILITY_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_COMPATIBILITY_FORMS = new UnicodeBlock("CJK_COMPATIBILITY_FORMS", CJK_COMPATIBILITY_FORMS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS = new UnicodeBlock("CJK_COMPATIBILITY_IDEOGRAPHS", CJK_COMPATIBILITY_IDEOGRAPHS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT = new UnicodeBlock("CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT", CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_RADICALS_SUPPLEMENT = new UnicodeBlock("CJK_RADICALS_SUPPLEMENT", CJK_RADICALS_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_STROKES = new UnicodeBlock("CJK_STROKES", CJK_STROKES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_SYMBOLS_AND_PUNCTUATION = new UnicodeBlock("CJK_SYMBOLS_AND_PUNCTUATION", CJK_SYMBOLS_AND_PUNCTUATION_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS", CJK_UNIFIED_IDEOGRAPHS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_H = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_H", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_H_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_I = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_I", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_I_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock COMBINING_DIACRITICAL_MARKS = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS", COMBINING_DIACRITICAL_MARKS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock COMBINING_DIACRITICAL_MARKS_EXTENDED = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS_EXTENDED", COMBINING_DIACRITICAL_MARKS_EXTENDED_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock COMBINING_DIACRITICAL_MARKS_SUPPLEMENT = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS_SUPPLEMENT", COMBINING_DIACRITICAL_MARKS_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock COMBINING_HALF_MARKS = new UnicodeBlock("COMBINING_HALF_MARKS", COMBINING_HALF_MARKS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock COMBINING_MARKS_FOR_SYMBOLS = new UnicodeBlock("COMBINING_MARKS_FOR_SYMBOLS", COMBINING_MARKS_FOR_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock COMMON_INDIC_NUMBER_FORMS = new UnicodeBlock("COMMON_INDIC_NUMBER_FORMS", COMMON_INDIC_NUMBER_FORMS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CONTROL_PICTURES = new UnicodeBlock("CONTROL_PICTURES", CONTROL_PICTURES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock COPTIC = new UnicodeBlock("COPTIC", COPTIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock COPTIC_EPACT_NUMBERS = new UnicodeBlock("COPTIC_EPACT_NUMBERS", COPTIC_EPACT_NUMBERS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock COUNTING_ROD_NUMERALS = new UnicodeBlock("COUNTING_ROD_NUMERALS", COUNTING_ROD_NUMERALS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CUNEIFORM = new UnicodeBlock("CUNEIFORM", CUNEIFORM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CUNEIFORM_NUMBERS_AND_PUNCTUATION = new UnicodeBlock("CUNEIFORM_NUMBERS_AND_PUNCTUATION", CUNEIFORM_NUMBERS_AND_PUNCTUATION_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CURRENCY_SYMBOLS = new UnicodeBlock("CURRENCY_SYMBOLS", CURRENCY_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CYPRIOT_SYLLABARY = new UnicodeBlock("CYPRIOT_SYLLABARY", CYPRIOT_SYLLABARY_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CYPRO_MINOAN = new UnicodeBlock("CYPRO_MINOAN", CYPRO_MINOAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CYRILLIC = new UnicodeBlock("CYRILLIC", CYRILLIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CYRILLIC_EXTENDED_A = new UnicodeBlock("CYRILLIC_EXTENDED_A", CYRILLIC_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CYRILLIC_EXTENDED_B = new UnicodeBlock("CYRILLIC_EXTENDED_B", CYRILLIC_EXTENDED_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CYRILLIC_EXTENDED_C = new UnicodeBlock("CYRILLIC_EXTENDED_C", CYRILLIC_EXTENDED_C_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CYRILLIC_EXTENDED_D = new UnicodeBlock("CYRILLIC_EXTENDED_D", CYRILLIC_EXTENDED_D_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CYRILLIC_SUPPLEMENT = new UnicodeBlock("CYRILLIC_SUPPLEMENT", CYRILLIC_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock CYRILLIC_SUPPLEMENTARY = new UnicodeBlock("CYRILLIC_SUPPLEMENTARY", CYRILLIC_SUPPLEMENTARY_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock DESERET = new UnicodeBlock("DESERET", DESERET_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock DEVANAGARI = new UnicodeBlock("DEVANAGARI", DEVANAGARI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock DEVANAGARI_EXTENDED = new UnicodeBlock("DEVANAGARI_EXTENDED", DEVANAGARI_EXTENDED_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock DEVANAGARI_EXTENDED_A = new UnicodeBlock("DEVANAGARI_EXTENDED_A", DEVANAGARI_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock DINGBATS = new UnicodeBlock("DINGBATS", DINGBATS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock DIVES_AKURU = new UnicodeBlock("DIVES_AKURU", DIVES_AKURU_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock DOGRA = new UnicodeBlock("DOGRA", DOGRA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock DOMINO_TILES = new UnicodeBlock("DOMINO_TILES", DOMINO_TILES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock DUPLOYAN = new UnicodeBlock("DUPLOYAN", DUPLOYAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock EARLY_DYNASTIC_CUNEIFORM = new UnicodeBlock("EARLY_DYNASTIC_CUNEIFORM", EARLY_DYNASTIC_CUNEIFORM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock EGYPTIAN_HIEROGLYPHS = new UnicodeBlock("EGYPTIAN_HIEROGLYPHS", EGYPTIAN_HIEROGLYPHS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS = new UnicodeBlock("EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS", EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ELBASAN = new UnicodeBlock("ELBASAN", ELBASAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ELYMAIC = new UnicodeBlock("ELYMAIC", ELYMAIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock EMOTICONS = new UnicodeBlock("EMOTICONS", EMOTICONS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ENCLOSED_ALPHANUMERICS = new UnicodeBlock("ENCLOSED_ALPHANUMERICS", ENCLOSED_ALPHANUMERICS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ENCLOSED_ALPHANUMERIC_SUPPLEMENT = new UnicodeBlock("ENCLOSED_ALPHANUMERIC_SUPPLEMENT", ENCLOSED_ALPHANUMERIC_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ENCLOSED_CJK_LETTERS_AND_MONTHS = new UnicodeBlock("ENCLOSED_CJK_LETTERS_AND_MONTHS", ENCLOSED_CJK_LETTERS_AND_MONTHS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ENCLOSED_IDEOGRAPHIC_SUPPLEMENT = new UnicodeBlock("ENCLOSED_IDEOGRAPHIC_SUPPLEMENT", ENCLOSED_IDEOGRAPHIC_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ETHIOPIC = new UnicodeBlock("ETHIOPIC", ETHIOPIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ETHIOPIC_EXTENDED = new UnicodeBlock("ETHIOPIC_EXTENDED", ETHIOPIC_EXTENDED_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ETHIOPIC_EXTENDED_A = new UnicodeBlock("ETHIOPIC_EXTENDED_A", ETHIOPIC_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ETHIOPIC_EXTENDED_B = new UnicodeBlock("ETHIOPIC_EXTENDED_B", ETHIOPIC_EXTENDED_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ETHIOPIC_SUPPLEMENT = new UnicodeBlock("ETHIOPIC_SUPPLEMENT", ETHIOPIC_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GENERAL_PUNCTUATION = new UnicodeBlock("GENERAL_PUNCTUATION", GENERAL_PUNCTUATION_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GEOMETRIC_SHAPES = new UnicodeBlock("GEOMETRIC_SHAPES", GEOMETRIC_SHAPES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GEOMETRIC_SHAPES_EXTENDED = new UnicodeBlock("GEOMETRIC_SHAPES_EXTENDED", GEOMETRIC_SHAPES_EXTENDED_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GEORGIAN = new UnicodeBlock("GEORGIAN", GEORGIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GEORGIAN_EXTENDED = new UnicodeBlock("GEORGIAN_EXTENDED", GEORGIAN_EXTENDED_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GEORGIAN_SUPPLEMENT = new UnicodeBlock("GEORGIAN_SUPPLEMENT", GEORGIAN_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GLAGOLITIC = new UnicodeBlock("GLAGOLITIC", GLAGOLITIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GLAGOLITIC_SUPPLEMENT = new UnicodeBlock("GLAGOLITIC_SUPPLEMENT", GLAGOLITIC_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GOTHIC = new UnicodeBlock("GOTHIC", GOTHIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GRANTHA = new UnicodeBlock("GRANTHA", GRANTHA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GREEK = new UnicodeBlock("GREEK", GREEK_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GREEK_EXTENDED = new UnicodeBlock("GREEK_EXTENDED", GREEK_EXTENDED_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GUJARATI = new UnicodeBlock("GUJARATI", GUJARATI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GUNJALA_GONDI = new UnicodeBlock("GUNJALA_GONDI", GUNJALA_GONDI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock GURMUKHI = new UnicodeBlock("GURMUKHI", GURMUKHI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HALFWIDTH_AND_FULLWIDTH_FORMS = new UnicodeBlock("HALFWIDTH_AND_FULLWIDTH_FORMS", HALFWIDTH_AND_FULLWIDTH_FORMS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HANGUL_COMPATIBILITY_JAMO = new UnicodeBlock("HANGUL_COMPATIBILITY_JAMO", HANGUL_COMPATIBILITY_JAMO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HANGUL_JAMO = new UnicodeBlock("HANGUL_JAMO", HANGUL_JAMO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HANGUL_JAMO_EXTENDED_A = new UnicodeBlock("HANGUL_JAMO_EXTENDED_A", HANGUL_JAMO_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HANGUL_JAMO_EXTENDED_B = new UnicodeBlock("HANGUL_JAMO_EXTENDED_B", HANGUL_JAMO_EXTENDED_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HANGUL_SYLLABLES = new UnicodeBlock("HANGUL_SYLLABLES", HANGUL_SYLLABLES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HANIFI_ROHINGYA = new UnicodeBlock("HANIFI_ROHINGYA", HANIFI_ROHINGYA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HANUNOO = new UnicodeBlock("HANUNOO", HANUNOO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HATRAN = new UnicodeBlock("HATRAN", HATRAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HEBREW = new UnicodeBlock("HEBREW", HEBREW_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HIGH_PRIVATE_USE_SURROGATES = new UnicodeBlock("HIGH_PRIVATE_USE_SURROGATES", HIGH_PRIVATE_USE_SURROGATES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HIGH_SURROGATES = new UnicodeBlock("HIGH_SURROGATES", HIGH_SURROGATES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock HIRAGANA = new UnicodeBlock("HIRAGANA", HIRAGANA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock IDEOGRAPHIC_DESCRIPTION_CHARACTERS = new UnicodeBlock("IDEOGRAPHIC_DESCRIPTION_CHARACTERS", IDEOGRAPHIC_DESCRIPTION_CHARACTERS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION = new UnicodeBlock("IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION", IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock IMPERIAL_ARAMAIC = new UnicodeBlock("IMPERIAL_ARAMAIC", IMPERIAL_ARAMAIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock INDIC_SIYAQ_NUMBERS = new UnicodeBlock("INDIC_SIYAQ_NUMBERS", INDIC_SIYAQ_NUMBERS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock INSCRIPTIONAL_PAHLAVI = new UnicodeBlock("INSCRIPTIONAL_PAHLAVI", INSCRIPTIONAL_PAHLAVI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock INSCRIPTIONAL_PARTHIAN = new UnicodeBlock("INSCRIPTIONAL_PARTHIAN", INSCRIPTIONAL_PARTHIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock INVALID_CODE = new UnicodeBlock("INVALID_CODE", INVALID_CODE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock IPA_EXTENSIONS = new UnicodeBlock("IPA_EXTENSIONS", IPA_EXTENSIONS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock JAVANESE = new UnicodeBlock("JAVANESE", JAVANESE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KAITHI = new UnicodeBlock("KAITHI", KAITHI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KAKTOVIK_NUMERALS = new UnicodeBlock("KAKTOVIK_NUMERALS", KAKTOVIK_NUMERALS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KANA_EXTENDED_A = new UnicodeBlock("KANA_EXTENDED_A", KANA_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KANA_EXTENDED_B = new UnicodeBlock("KANA_EXTENDED_B", KANA_EXTENDED_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KANA_SUPPLEMENT = new UnicodeBlock("KANA_SUPPLEMENT", KANA_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KANBUN = new UnicodeBlock("KANBUN", KANBUN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KANGXI_RADICALS = new UnicodeBlock("KANGXI_RADICALS", KANGXI_RADICALS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KANNADA = new UnicodeBlock("KANNADA", KANNADA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KATAKANA = new UnicodeBlock("KATAKANA", KATAKANA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KATAKANA_PHONETIC_EXTENSIONS = new UnicodeBlock("KATAKANA_PHONETIC_EXTENSIONS", KATAKANA_PHONETIC_EXTENSIONS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KAWI = new UnicodeBlock("KAWI", KAWI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KAYAH_LI = new UnicodeBlock("KAYAH_LI", KAYAH_LI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KHAROSHTHI = new UnicodeBlock("KHAROSHTHI", KHAROSHTHI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KHITAN_SMALL_SCRIPT = new UnicodeBlock("KHITAN_SMALL_SCRIPT", KHITAN_SMALL_SCRIPT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KHMER = new UnicodeBlock("KHMER", KHMER_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KHMER_SYMBOLS = new UnicodeBlock("KHMER_SYMBOLS", KHMER_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KHOJKI = new UnicodeBlock("KHOJKI", KHOJKI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock KHUDAWADI = new UnicodeBlock("KHUDAWADI", KHUDAWADI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LAO = new UnicodeBlock("LAO", LAO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LATIN_1_SUPPLEMENT = new UnicodeBlock("LATIN_1_SUPPLEMENT", LATIN_1_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LATIN_EXTENDED_A = new UnicodeBlock("LATIN_EXTENDED_A", LATIN_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LATIN_EXTENDED_ADDITIONAL = new UnicodeBlock("LATIN_EXTENDED_ADDITIONAL", LATIN_EXTENDED_ADDITIONAL_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LATIN_EXTENDED_B = new UnicodeBlock("LATIN_EXTENDED_B", LATIN_EXTENDED_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LATIN_EXTENDED_C = new UnicodeBlock("LATIN_EXTENDED_C", LATIN_EXTENDED_C_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LATIN_EXTENDED_D = new UnicodeBlock("LATIN_EXTENDED_D", LATIN_EXTENDED_D_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LATIN_EXTENDED_E = new UnicodeBlock("LATIN_EXTENDED_E", LATIN_EXTENDED_E_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LATIN_EXTENDED_F = new UnicodeBlock("LATIN_EXTENDED_F", LATIN_EXTENDED_F_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LATIN_EXTENDED_G = new UnicodeBlock("LATIN_EXTENDED_G", LATIN_EXTENDED_G_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LEPCHA = new UnicodeBlock("LEPCHA", LEPCHA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LETTERLIKE_SYMBOLS = new UnicodeBlock("LETTERLIKE_SYMBOLS", LETTERLIKE_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LIMBU = new UnicodeBlock("LIMBU", LIMBU_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LINEAR_A = new UnicodeBlock("LINEAR_A", LINEAR_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LINEAR_B_IDEOGRAMS = new UnicodeBlock("LINEAR_B_IDEOGRAMS", LINEAR_B_IDEOGRAMS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LINEAR_B_SYLLABARY = new UnicodeBlock("LINEAR_B_SYLLABARY", LINEAR_B_SYLLABARY_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LISU = new UnicodeBlock("LISU", LISU_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LISU_SUPPLEMENT = new UnicodeBlock("LISU_SUPPLEMENT", LISU_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LOW_SURROGATES = new UnicodeBlock("LOW_SURROGATES", LOW_SURROGATES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LYCIAN = new UnicodeBlock("LYCIAN", LYCIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock LYDIAN = new UnicodeBlock("LYDIAN", LYDIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MAHAJANI = new UnicodeBlock("MAHAJANI", MAHAJANI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MAHJONG_TILES = new UnicodeBlock("MAHJONG_TILES", MAHJONG_TILES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MAKASAR = new UnicodeBlock("MAKASAR", MAKASAR_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MALAYALAM = new UnicodeBlock("MALAYALAM", MALAYALAM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MANDAIC = new UnicodeBlock("MANDAIC", MANDAIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MANICHAEAN = new UnicodeBlock("MANICHAEAN", MANICHAEAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MARCHEN = new UnicodeBlock("MARCHEN", MARCHEN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MASARAM_GONDI = new UnicodeBlock("MASARAM_GONDI", MASARAM_GONDI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MATHEMATICAL_ALPHANUMERIC_SYMBOLS = new UnicodeBlock("MATHEMATICAL_ALPHANUMERIC_SYMBOLS", MATHEMATICAL_ALPHANUMERIC_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MATHEMATICAL_OPERATORS = new UnicodeBlock("MATHEMATICAL_OPERATORS", MATHEMATICAL_OPERATORS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MAYAN_NUMERALS = new UnicodeBlock("MAYAN_NUMERALS", MAYAN_NUMERALS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MEDEFAIDRIN = new UnicodeBlock("MEDEFAIDRIN", MEDEFAIDRIN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MEETEI_MAYEK = new UnicodeBlock("MEETEI_MAYEK", MEETEI_MAYEK_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MEETEI_MAYEK_EXTENSIONS = new UnicodeBlock("MEETEI_MAYEK_EXTENSIONS", MEETEI_MAYEK_EXTENSIONS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MENDE_KIKAKUI = new UnicodeBlock("MENDE_KIKAKUI", MENDE_KIKAKUI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MEROITIC_CURSIVE = new UnicodeBlock("MEROITIC_CURSIVE", MEROITIC_CURSIVE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MEROITIC_HIEROGLYPHS = new UnicodeBlock("MEROITIC_HIEROGLYPHS", MEROITIC_HIEROGLYPHS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MIAO = new UnicodeBlock("MIAO", MIAO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A = new UnicodeBlock("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A", MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B = new UnicodeBlock("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B", MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MISCELLANEOUS_SYMBOLS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS", MISCELLANEOUS_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MISCELLANEOUS_SYMBOLS_AND_ARROWS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS_AND_ARROWS", MISCELLANEOUS_SYMBOLS_AND_ARROWS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS", MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MISCELLANEOUS_TECHNICAL = new UnicodeBlock("MISCELLANEOUS_TECHNICAL", MISCELLANEOUS_TECHNICAL_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MODI = new UnicodeBlock("MODI", MODI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MODIFIER_TONE_LETTERS = new UnicodeBlock("MODIFIER_TONE_LETTERS", MODIFIER_TONE_LETTERS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MONGOLIAN = new UnicodeBlock("MONGOLIAN", MONGOLIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MONGOLIAN_SUPPLEMENT = new UnicodeBlock("MONGOLIAN_SUPPLEMENT", MONGOLIAN_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MRO = new UnicodeBlock("MRO", MRO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MULTANI = new UnicodeBlock("MULTANI", MULTANI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MUSICAL_SYMBOLS = new UnicodeBlock("MUSICAL_SYMBOLS", MUSICAL_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MYANMAR = new UnicodeBlock("MYANMAR", MYANMAR_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MYANMAR_EXTENDED_A = new UnicodeBlock("MYANMAR_EXTENDED_A", MYANMAR_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock MYANMAR_EXTENDED_B = new UnicodeBlock("MYANMAR_EXTENDED_B", MYANMAR_EXTENDED_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock NABATAEAN = new UnicodeBlock("NABATAEAN", NABATAEAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock NAG_MUNDARI = new UnicodeBlock("NAG_MUNDARI", NAG_MUNDARI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock NANDINAGARI = new UnicodeBlock("NANDINAGARI", NANDINAGARI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock NEWA = new UnicodeBlock("NEWA", NEWA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock NEW_TAI_LUE = new UnicodeBlock("NEW_TAI_LUE", NEW_TAI_LUE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock NKO = new UnicodeBlock("NKO", NKO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock NO_BLOCK = new UnicodeBlock("NO_BLOCK", 0);
    public static final android.icu.lang.UCharacter.UnicodeBlock NUMBER_FORMS = new UnicodeBlock("NUMBER_FORMS", NUMBER_FORMS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock NUSHU = new UnicodeBlock("NUSHU", NUSHU_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock NYIAKENG_PUACHUE_HMONG = new UnicodeBlock("NYIAKENG_PUACHUE_HMONG", NYIAKENG_PUACHUE_HMONG_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OGHAM = new UnicodeBlock("OGHAM", OGHAM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OLD_HUNGARIAN = new UnicodeBlock("OLD_HUNGARIAN", OLD_HUNGARIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OLD_ITALIC = new UnicodeBlock("OLD_ITALIC", OLD_ITALIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OLD_NORTH_ARABIAN = new UnicodeBlock("OLD_NORTH_ARABIAN", OLD_NORTH_ARABIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OLD_PERMIC = new UnicodeBlock("OLD_PERMIC", OLD_PERMIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OLD_PERSIAN = new UnicodeBlock("OLD_PERSIAN", OLD_PERSIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OLD_SOGDIAN = new UnicodeBlock("OLD_SOGDIAN", OLD_SOGDIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OLD_SOUTH_ARABIAN = new UnicodeBlock("OLD_SOUTH_ARABIAN", OLD_SOUTH_ARABIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OLD_TURKIC = new UnicodeBlock("OLD_TURKIC", OLD_TURKIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OLD_UYGHUR = new UnicodeBlock("OLD_UYGHUR", OLD_UYGHUR_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OL_CHIKI = new UnicodeBlock("OL_CHIKI", OL_CHIKI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OPTICAL_CHARACTER_RECOGNITION = new UnicodeBlock("OPTICAL_CHARACTER_RECOGNITION", OPTICAL_CHARACTER_RECOGNITION_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ORIYA = new UnicodeBlock("ORIYA", ORIYA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ORNAMENTAL_DINGBATS = new UnicodeBlock("ORNAMENTAL_DINGBATS", ORNAMENTAL_DINGBATS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OSAGE = new UnicodeBlock("OSAGE", OSAGE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OSMANYA = new UnicodeBlock("OSMANYA", OSMANYA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock OTTOMAN_SIYAQ_NUMBERS = new UnicodeBlock("OTTOMAN_SIYAQ_NUMBERS", OTTOMAN_SIYAQ_NUMBERS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PAHAWH_HMONG = new UnicodeBlock("PAHAWH_HMONG", PAHAWH_HMONG_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PALMYRENE = new UnicodeBlock("PALMYRENE", PALMYRENE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PAU_CIN_HAU = new UnicodeBlock("PAU_CIN_HAU", PAU_CIN_HAU_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PHAGS_PA = new UnicodeBlock("PHAGS_PA", PHAGS_PA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PHAISTOS_DISC = new UnicodeBlock("PHAISTOS_DISC", PHAISTOS_DISC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PHOENICIAN = new UnicodeBlock("PHOENICIAN", PHOENICIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PHONETIC_EXTENSIONS = new UnicodeBlock("PHONETIC_EXTENSIONS", PHONETIC_EXTENSIONS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PHONETIC_EXTENSIONS_SUPPLEMENT = new UnicodeBlock("PHONETIC_EXTENSIONS_SUPPLEMENT", PHONETIC_EXTENSIONS_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PLAYING_CARDS = new UnicodeBlock("PLAYING_CARDS", PLAYING_CARDS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PRIVATE_USE = new UnicodeBlock("PRIVATE_USE", PRIVATE_USE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PRIVATE_USE_AREA = new UnicodeBlock("PRIVATE_USE_AREA", PRIVATE_USE_AREA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock PSALTER_PAHLAVI = new UnicodeBlock("PSALTER_PAHLAVI", PSALTER_PAHLAVI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock REJANG = new UnicodeBlock("REJANG", REJANG_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock RUMI_NUMERAL_SYMBOLS = new UnicodeBlock("RUMI_NUMERAL_SYMBOLS", RUMI_NUMERAL_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock RUNIC = new UnicodeBlock("RUNIC", RUNIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SAMARITAN = new UnicodeBlock("SAMARITAN", SAMARITAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SAURASHTRA = new UnicodeBlock("SAURASHTRA", SAURASHTRA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SHARADA = new UnicodeBlock("SHARADA", SHARADA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SHAVIAN = new UnicodeBlock("SHAVIAN", SHAVIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SHORTHAND_FORMAT_CONTROLS = new UnicodeBlock("SHORTHAND_FORMAT_CONTROLS", SHORTHAND_FORMAT_CONTROLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SIDDHAM = new UnicodeBlock("SIDDHAM", SIDDHAM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SINHALA = new UnicodeBlock("SINHALA", SINHALA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SINHALA_ARCHAIC_NUMBERS = new UnicodeBlock("SINHALA_ARCHAIC_NUMBERS", SINHALA_ARCHAIC_NUMBERS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SMALL_FORM_VARIANTS = new UnicodeBlock("SMALL_FORM_VARIANTS", SMALL_FORM_VARIANTS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SMALL_KANA_EXTENSION = new UnicodeBlock("SMALL_KANA_EXTENSION", SMALL_KANA_EXTENSION_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SOGDIAN = new UnicodeBlock("SOGDIAN", SOGDIAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SORA_SOMPENG = new UnicodeBlock("SORA_SOMPENG", SORA_SOMPENG_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SOYOMBO = new UnicodeBlock("SOYOMBO", SOYOMBO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SPACING_MODIFIER_LETTERS = new UnicodeBlock("SPACING_MODIFIER_LETTERS", SPACING_MODIFIER_LETTERS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SPECIALS = new UnicodeBlock("SPECIALS", SPECIALS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUNDANESE = new UnicodeBlock("SUNDANESE", SUNDANESE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUNDANESE_SUPPLEMENT = new UnicodeBlock("SUNDANESE_SUPPLEMENT", SUNDANESE_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUPERSCRIPTS_AND_SUBSCRIPTS = new UnicodeBlock("SUPERSCRIPTS_AND_SUBSCRIPTS", SUPERSCRIPTS_AND_SUBSCRIPTS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUPPLEMENTAL_ARROWS_A = new UnicodeBlock("SUPPLEMENTAL_ARROWS_A", SUPPLEMENTAL_ARROWS_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUPPLEMENTAL_ARROWS_B = new UnicodeBlock("SUPPLEMENTAL_ARROWS_B", SUPPLEMENTAL_ARROWS_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUPPLEMENTAL_ARROWS_C = new UnicodeBlock("SUPPLEMENTAL_ARROWS_C", SUPPLEMENTAL_ARROWS_C_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUPPLEMENTAL_MATHEMATICAL_OPERATORS = new UnicodeBlock("SUPPLEMENTAL_MATHEMATICAL_OPERATORS", SUPPLEMENTAL_MATHEMATICAL_OPERATORS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUPPLEMENTAL_PUNCTUATION = new UnicodeBlock("SUPPLEMENTAL_PUNCTUATION", SUPPLEMENTAL_PUNCTUATION_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS = new UnicodeBlock("SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS", SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUPPLEMENTARY_PRIVATE_USE_AREA_A = new UnicodeBlock("SUPPLEMENTARY_PRIVATE_USE_AREA_A", SUPPLEMENTARY_PRIVATE_USE_AREA_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUPPLEMENTARY_PRIVATE_USE_AREA_B = new UnicodeBlock("SUPPLEMENTARY_PRIVATE_USE_AREA_B", SUPPLEMENTARY_PRIVATE_USE_AREA_B_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SUTTON_SIGNWRITING = new UnicodeBlock("SUTTON_SIGNWRITING", SUTTON_SIGNWRITING_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SYLOTI_NAGRI = new UnicodeBlock("SYLOTI_NAGRI", SYLOTI_NAGRI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A = new UnicodeBlock("SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A", SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SYMBOLS_FOR_LEGACY_COMPUTING = new UnicodeBlock("SYMBOLS_FOR_LEGACY_COMPUTING", SYMBOLS_FOR_LEGACY_COMPUTING_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SYRIAC = new UnicodeBlock("SYRIAC", SYRIAC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock SYRIAC_SUPPLEMENT = new UnicodeBlock("SYRIAC_SUPPLEMENT", SYRIAC_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAGALOG = new UnicodeBlock("TAGALOG", TAGALOG_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAGBANWA = new UnicodeBlock("TAGBANWA", TAGBANWA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAGS = new UnicodeBlock("TAGS", TAGS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAI_LE = new UnicodeBlock("TAI_LE", TAI_LE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAI_THAM = new UnicodeBlock("TAI_THAM", TAI_THAM_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAI_VIET = new UnicodeBlock("TAI_VIET", TAI_VIET_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAI_XUAN_JING_SYMBOLS = new UnicodeBlock("TAI_XUAN_JING_SYMBOLS", TAI_XUAN_JING_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAKRI = new UnicodeBlock("TAKRI", TAKRI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAMIL = new UnicodeBlock("TAMIL", TAMIL_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TAMIL_SUPPLEMENT = new UnicodeBlock("TAMIL_SUPPLEMENT", TAMIL_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TANGSA = new UnicodeBlock("TANGSA", TANGSA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TANGUT = new UnicodeBlock("TANGUT", TANGUT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TANGUT_COMPONENTS = new UnicodeBlock("TANGUT_COMPONENTS", TANGUT_COMPONENTS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TANGUT_SUPPLEMENT = new UnicodeBlock("TANGUT_SUPPLEMENT", TANGUT_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TELUGU = new UnicodeBlock("TELUGU", TELUGU_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock THAANA = new UnicodeBlock("THAANA", THAANA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock THAI = new UnicodeBlock("THAI", THAI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TIBETAN = new UnicodeBlock("TIBETAN", TIBETAN_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TIFINAGH = new UnicodeBlock("TIFINAGH", TIFINAGH_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TIRHUTA = new UnicodeBlock("TIRHUTA", TIRHUTA_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TOTO = new UnicodeBlock("TOTO", TOTO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock TRANSPORT_AND_MAP_SYMBOLS = new UnicodeBlock("TRANSPORT_AND_MAP_SYMBOLS", TRANSPORT_AND_MAP_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock UGARITIC = new UnicodeBlock("UGARITIC", UGARITIC_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS = new UnicodeBlock("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS", UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED = new UnicodeBlock("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED", UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_A = new UnicodeBlock("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_A", UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_A_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock VAI = new UnicodeBlock("VAI", VAI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock VARIATION_SELECTORS = new UnicodeBlock("VARIATION_SELECTORS", VARIATION_SELECTORS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock VARIATION_SELECTORS_SUPPLEMENT = new UnicodeBlock("VARIATION_SELECTORS_SUPPLEMENT", VARIATION_SELECTORS_SUPPLEMENT_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock VEDIC_EXTENSIONS = new UnicodeBlock("VEDIC_EXTENSIONS", VEDIC_EXTENSIONS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock VERTICAL_FORMS = new UnicodeBlock("VERTICAL_FORMS", VERTICAL_FORMS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock VITHKUQI = new UnicodeBlock("VITHKUQI", VITHKUQI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock WANCHO = new UnicodeBlock("WANCHO", WANCHO_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock WARANG_CITI = new UnicodeBlock("WARANG_CITI", WARANG_CITI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock YEZIDI = new UnicodeBlock("YEZIDI", YEZIDI_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock YIJING_HEXAGRAM_SYMBOLS = new UnicodeBlock("YIJING_HEXAGRAM_SYMBOLS", YIJING_HEXAGRAM_SYMBOLS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock YI_RADICALS = new UnicodeBlock("YI_RADICALS", YI_RADICALS_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock YI_SYLLABLES = new UnicodeBlock("YI_SYLLABLES", YI_SYLLABLES_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ZANABAZAR_SQUARE = new UnicodeBlock("ZANABAZAR_SQUARE", ZANABAZAR_SQUARE_ID);
    public static final android.icu.lang.UCharacter.UnicodeBlock ZNAMENNY_MUSICAL_NOTATION = new UnicodeBlock("ZNAMENNY_MUSICAL_NOTATION", ZNAMENNY_MUSICAL_NOTATION_ID);
    private final int id;
    private UnicodeBlock(String name, int id) { super(name); this.id = id; if (!BY_ID.containsKey(id)) BY_ID.put(id, this); BY_NAME.put(name, this); }
    public int getID() { return id; }
    public static UnicodeBlock getInstance(int id) { UnicodeBlock b = BY_ID.get(id); return b != null ? b : INVALID_CODE; }
    /** the block of ch: java.lang.Character's block of the same name (NO_BLOCK where it has none) */
    public static UnicodeBlock of(int ch) {
      if (ch < MIN_VALUE || ch > MAX_VALUE) return INVALID_CODE;
      a2w.jdk.lang.Character$UnicodeBlock j = a2w.jdk.lang.Character$UnicodeBlock.of(ch);
      UnicodeBlock b = j == null ? null : BY_NAME.get(j.toString());
      return b != null ? b : NO_BLOCK;
    }
    /** by the Unicode block name or alias, as java.lang.Character.UnicodeBlock.forName reads them */
    public static UnicodeBlock forName(String blockName) {
      UnicodeBlock b = BY_NAME.get(blockName.toUpperCase(java.util.Locale.ENGLISH).replace(' ', '_').replace('-', '_'));
      if (b == null) {
        try { b = BY_NAME.get(a2w.jdk.lang.Character$UnicodeBlock.forName(blockName).toString()); } catch (IllegalArgumentException e) {}
      }
      if (b == null) throw new IllegalArgumentException("Not a valid block name: " + blockName);
      return b;
    }
  }


  public interface VerticalOrientation {
    int ROTATED = 0;
    int TRANSFORMED_ROTATED = 1;
    int TRANSFORMED_UPRIGHT = 2;
    int UPRIGHT = 3;
  }

  public interface WordBreak {
    int ALETTER = 1;
    int CR = 8;
    int DOUBLE_QUOTE = 16;
    int EXTEND = 9;
    int EXTENDNUMLET = 7;
    int E_BASE = 17;
    int E_BASE_GAZ = 18;
    int E_MODIFIER = 19;
    int FORMAT = 2;
    int GLUE_AFTER_ZWJ = 20;
    int HEBREW_LETTER = 14;
    int KATAKANA = 3;
    int LF = 10;
    int MIDLETTER = 4;
    int MIDNUM = 5;
    int MIDNUMLET = 11;
    int NEWLINE = 12;
    int NUMERIC = 6;
    int OTHER = 0;
    int REGIONAL_INDICATOR = 13;
    int SINGLE_QUOTE = 15;
    int WSEGSPACE = 22;
    int ZWJ = 21;
  }
}

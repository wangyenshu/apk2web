# java.lang

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractMethodError — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ AbstractMethodError()  [API 1+]
+ AbstractMethodError(String)  [API 1+]
```

## Appendable — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ append(CharSequence): Appendable  [API 1+]
+ append(CharSequence, int, int): Appendable  [API 1+]
+ append(char): Appendable  [API 1+]
```

## ArithmeticException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ArithmeticException()  [API 1+]
+ ArithmeticException(String)  [API 1+]
```

## ArrayIndexOutOfBoundsException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ ArrayIndexOutOfBoundsException()  [API 1+]
+ ArrayIndexOutOfBoundsException(String)  [API 1+]
+ ArrayIndexOutOfBoundsException(int)  [API 1+]
```

## ArrayStoreException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ArrayStoreException()  [API 1+]
+ ArrayStoreException(String)  [API 1+]
```

## AssertionError — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ AssertionError()  [API 1+]
+ AssertionError(Object)  [API 1+]
+ AssertionError(String, Throwable)  [API 19+]
+ AssertionError(boolean)  [API 1+]
+ AssertionError(char)  [API 1+]
+ AssertionError(double)  [API 1+]
+ AssertionError(float)  [API 1+]
+ AssertionError(int)  [API 1+]
+ AssertionError(long)  [API 1+]
```

## AutoCloseable — TeaVM class library; API 19+ (1 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 19+]
```

## Boolean — TeaVM class library; API 1+ (17 +, 0 *, 0 ~, 0 -)

```
+ Boolean(String)  [API 1+, deprecated in 33]
+ Boolean(boolean)  [API 1+, deprecated in 33]
+ FALSE [field]  [API 1+]
+ TRUE [field]  [API 1+]
+ TYPE [field]  [API 1+]
+ booleanValue(): boolean  [API 1+]
+ compare(boolean, boolean): int [static]  [API 19+]
+ compareTo(Boolean): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getBoolean(String): boolean [static]  [API 1+]
+ hashCode(): int  [API 1+]
+ hashCode(boolean): int [static]  [API 24+]
+ logicalAnd(boolean, boolean): boolean [static]  [API 24+]
+ logicalOr(boolean, boolean): boolean [static]  [API 24+]
+ logicalXor(boolean, boolean): boolean [static]  [API 24+]
+ parseBoolean(String): boolean [static]  [API 1+]
+ toString(): String  [API 1+]
+ toString(boolean): String [static]  [API 1+]
+ valueOf(String): Boolean [static]  [API 1+]
+ valueOf(boolean): Boolean [static]  [API 1+]
```

## BootstrapMethodError — declaration; API 26+ (3 +, 0 *, 0 ~, 1 -)

```
+ BootstrapMethodError()  [API 26+]
+ BootstrapMethodError(String)  [API 26+]
+ BootstrapMethodError(String, Throwable)  [API 26+]
- BootstrapMethodError(Throwable)  [API 26+]
```

## Byte — TeaVM class library; API 1+ (24 +, 0 *, 0 ~, 0 -)

```
+ Byte(String)  [API 1+, deprecated in 31]
+ Byte(byte)  [API 1+, deprecated in 31]
+ TYPE [field]  [API 1+]
+ byteValue(): byte  [API 1+]
+ compare(byte, byte): int [static]  [API 19+]
+ compareTo(Byte): int  [API 1+]
+ compareUnsigned(byte, byte): int [static]  [API 31+]
+ decode(String): Byte [static]  [API 1+]
+ doubleValue(): double  [API 1+]
+ equals(Object): boolean  [API 1+]
+ floatValue(): float  [API 1+]
+ hashCode(): int  [API 1+]
+ hashCode(byte): int [static]  [API 24+]
+ intValue(): int  [API 1+]
+ longValue(): long  [API 1+]
+ parseByte(String): byte [static]  [API 1+]
+ parseByte(String, int): byte [static]  [API 1+]
+ shortValue(): short  [API 1+]
+ toString(): String  [API 1+]
+ toString(byte): String [static]  [API 1+]
+ toUnsignedInt(byte): int [static]  [API 26+]
+ toUnsignedLong(byte): long [static]  [API 26+]
+ valueOf(String): Byte [static]  [API 1+]
+ valueOf(String, int): Byte [static]  [API 1+]
+ valueOf(byte): Byte [static]  [API 1+]
```

## CharSequence — TeaVM class library; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ charAt(int): char  [API 1+]
+ chars(): IntStream  [API 24+]
+ codePoints(): IntStream  [API 24+]
+ compare(CharSequence, CharSequence): int [static]  [API 34+]
+ isEmpty(): boolean  [API 35+]
+ length(): int  [API 1+]
+ subSequence(int, int): CharSequence  [API 1+]
+ toString(): String  [API 1+]
```

## Character — TeaVM class library; API 1+ (93 +, 0 *, 0 ~, 2 -)

```
+ Character(char)  [API 1+, deprecated in 34]
+ TYPE [field]  [API 1+]
+ charCount(int): int [static]  [API 1+]
+ charValue(): char  [API 1+]
+ codePointAt(CharSequence, int): int [static]  [API 1+]
+ codePointAt(char[], int): int [static]  [API 1+]
+ codePointAt(char[], int, int): int [static]  [API 1+]
+ codePointBefore(CharSequence, int): int [static]  [API 1+]
+ codePointBefore(char[], int): int [static]  [API 1+]
+ codePointBefore(char[], int, int): int [static]  [API 1+]
+ codePointCount(CharSequence, int, int): int [static]  [API 1+]
+ codePointCount(char[], int, int): int [static]  [API 1+]
- codePointOf(String): int [static]  [API 35+]
+ compare(char, char): int [static]  [API 19+]
+ compareTo(Character): int  [API 1+]
+ digit(char, int): int [static]  [API 1+]
+ digit(int, int): int [static]  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forDigit(int, int): char [static]  [API 1+]
+ getDirectionality(char): byte [static]  [API 1+]
+ getDirectionality(int): byte [static]  [API 1+]
- getName(int): String [static]  [API 19+]
+ getNumericValue(char): int [static]  [API 1+]
+ getNumericValue(int): int [static]  [API 1+]
+ getType(char): int [static]  [API 1+]
+ getType(int): int [static]  [API 1+]
+ hashCode(): int  [API 1+]
+ hashCode(char): int [static]  [API 24+]
+ highSurrogate(int): char [static]  [API 19+]
+ isAlphabetic(int): boolean [static]  [API 19+]
+ isBmpCodePoint(int): boolean [static]  [API 19+]
+ isDefined(char): boolean [static]  [API 1+]
+ isDefined(int): boolean [static]  [API 1+]
+ isDigit(char): boolean [static]  [API 1+]
+ isDigit(int): boolean [static]  [API 1+]
+ isEmoji(int): boolean  [API 36+]
+ isEmojiComponent(int): boolean  [API 36+]
+ isEmojiModifier(int): boolean  [API 36+]
+ isEmojiModifierBase(int): boolean  [API 36+]
+ isEmojiPresentation(int): boolean  [API 36+]
+ isExtendedPictographic(int): boolean  [API 36+]
+ isHighSurrogate(char): boolean [static]  [API 1+]
+ isISOControl(char): boolean [static]  [API 1+]
+ isISOControl(int): boolean [static]  [API 1+]
+ isIdentifierIgnorable(char): boolean [static]  [API 1+]
+ isIdentifierIgnorable(int): boolean [static]  [API 1+]
+ isIdeographic(int): boolean [static]  [API 19+]
+ isJavaIdentifierPart(char): boolean [static]  [API 1+]
+ isJavaIdentifierPart(int): boolean [static]  [API 1+]
+ isJavaIdentifierStart(char): boolean [static]  [API 1+]
+ isJavaIdentifierStart(int): boolean [static]  [API 1+]
+ isJavaLetter(char): boolean [static]  [API 1+, deprecated in 15]
+ isJavaLetterOrDigit(char): boolean [static]  [API 1+, deprecated in 15]
+ isLetter(char): boolean [static]  [API 1+]
+ isLetter(int): boolean [static]  [API 1+]
+ isLetterOrDigit(char): boolean [static]  [API 1+]
+ isLetterOrDigit(int): boolean [static]  [API 1+]
+ isLowSurrogate(char): boolean [static]  [API 1+]
+ isLowerCase(char): boolean [static]  [API 1+]
+ isLowerCase(int): boolean [static]  [API 1+]
+ isMirrored(char): boolean [static]  [API 1+]
+ isMirrored(int): boolean [static]  [API 1+]
+ isSpace(char): boolean [static]  [API 1+, deprecated in 15]
+ isSpaceChar(char): boolean [static]  [API 1+]
+ isSpaceChar(int): boolean [static]  [API 1+]
+ isSupplementaryCodePoint(int): boolean [static]  [API 1+]
+ isSurrogate(char): boolean [static]  [API 19+]
+ isSurrogatePair(char, char): boolean [static]  [API 1+]
+ isTitleCase(char): boolean [static]  [API 1+]
+ isTitleCase(int): boolean [static]  [API 1+]
+ isUnicodeIdentifierPart(char): boolean [static]  [API 1+]
+ isUnicodeIdentifierPart(int): boolean [static]  [API 1+]
+ isUnicodeIdentifierStart(char): boolean [static]  [API 1+]
+ isUnicodeIdentifierStart(int): boolean [static]  [API 1+]
+ isUpperCase(char): boolean [static]  [API 1+]
+ isUpperCase(int): boolean [static]  [API 1+]
+ isValidCodePoint(int): boolean [static]  [API 1+]
+ isWhitespace(char): boolean [static]  [API 1+]
+ isWhitespace(int): boolean [static]  [API 1+]
+ lowSurrogate(int): char [static]  [API 19+]
+ offsetByCodePoints(CharSequence, int, int): int [static]  [API 1+]
+ offsetByCodePoints(char[], int, int, int, int): int [static]  [API 1+]
+ reverseBytes(char): char [static]  [API 1+]
+ toChars(int): char[] [static]  [API 1+]
+ toChars(int, char[], int): int [static]  [API 1+]
+ toCodePoint(char, char): int [static]  [API 1+]
+ toLowerCase(char): char [static]  [API 1+]
+ toLowerCase(int): int [static]  [API 1+]
+ toString(): String  [API 1+]
+ toString(char): String [static]  [API 1+]
+ toString(int): String [static]  [API 35+]
+ toTitleCase(char): char [static]  [API 1+]
+ toTitleCase(int): int [static]  [API 1+]
+ toUpperCase(char): char [static]  [API 1+]
+ toUpperCase(int): int [static]  [API 1+]
+ valueOf(char): Character [static]  [API 1+]
```

## Character.Subset — framework (a2w.jdk.lang.Character$Subset); API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ Subset(String)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ toString(): String  [API 1+]
```

## Character.UnicodeBlock — framework (a2w.jdk.lang.Character$UnicodeBlock); API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ ADLAM [field]  [API 34+]
+ AEGEAN_NUMBERS [field]  [API 1+]
+ AHOM [field]  [API 34+]
+ ALCHEMICAL_SYMBOLS [field]  [API 19+]
+ ALPHABETIC_PRESENTATION_FORMS [field]  [API 1+]
+ ANATOLIAN_HIEROGLYPHS [field]  [API 34+]
+ ANCIENT_GREEK_MUSICAL_NOTATION [field]  [API 19+]
+ ANCIENT_GREEK_NUMBERS [field]  [API 19+]
+ ANCIENT_SYMBOLS [field]  [API 19+]
+ ARABIC [field]  [API 1+]
+ ARABIC_EXTENDED_A [field]  [API 26+]
+ ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS [field]  [API 26+]
+ ARABIC_PRESENTATION_FORMS_A [field]  [API 1+]
+ ARABIC_PRESENTATION_FORMS_B [field]  [API 1+]
+ ARABIC_SUPPLEMENT [field]  [API 19+]
+ ARMENIAN [field]  [API 1+]
+ ARROWS [field]  [API 1+]
+ AVESTAN [field]  [API 19+]
+ BALINESE [field]  [API 19+]
+ BAMUM [field]  [API 19+]
+ BAMUM_SUPPLEMENT [field]  [API 19+]
+ BASIC_LATIN [field]  [API 1+]
+ BASSA_VAH [field]  [API 34+]
+ BATAK [field]  [API 19+]
+ BENGALI [field]  [API 1+]
+ BHAIKSUKI [field]  [API 34+]
+ BLOCK_ELEMENTS [field]  [API 1+]
+ BOPOMOFO [field]  [API 1+]
+ BOPOMOFO_EXTENDED [field]  [API 1+]
+ BOX_DRAWING [field]  [API 1+]
+ BRAHMI [field]  [API 19+]
+ BRAILLE_PATTERNS [field]  [API 1+]
+ BUGINESE [field]  [API 19+]
+ BUHID [field]  [API 1+]
+ BYZANTINE_MUSICAL_SYMBOLS [field]  [API 1+]
+ CARIAN [field]  [API 19+]
+ CAUCASIAN_ALBANIAN [field]  [API 34+]
+ CHAKMA [field]  [API 26+]
+ CHAM [field]  [API 19+]
+ CHEROKEE [field]  [API 1+]
+ CHEROKEE_SUPPLEMENT [field]  [API 34+]
+ CHESS_SYMBOLS [field]  [API 34+]
+ CHORASMIAN [field]  [API 34+]
+ CJK_COMPATIBILITY [field]  [API 1+]
+ CJK_COMPATIBILITY_FORMS [field]  [API 1+]
+ CJK_COMPATIBILITY_IDEOGRAPHS [field]  [API 1+]
+ CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT [field]  [API 1+]
+ CJK_RADICALS_SUPPLEMENT [field]  [API 1+]
+ CJK_STROKES [field]  [API 19+]
+ CJK_SYMBOLS_AND_PUNCTUATION [field]  [API 1+]
+ CJK_UNIFIED_IDEOGRAPHS [field]  [API 1+]
+ CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A [field]  [API 1+]
+ CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B [field]  [API 1+]
+ CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C [field]  [API 19+]
+ CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D [field]  [API 19+]
+ CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E [field]  [API 34+]
+ CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F [field]  [API 34+]
+ CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G [field]  [API 34+]
+ COMBINING_DIACRITICAL_MARKS [field]  [API 1+]
+ COMBINING_DIACRITICAL_MARKS_EXTENDED [field]  [API 34+]
+ COMBINING_DIACRITICAL_MARKS_SUPPLEMENT [field]  [API 19+]
+ COMBINING_HALF_MARKS [field]  [API 1+]
+ COMBINING_MARKS_FOR_SYMBOLS [field]  [API 1+]
+ COMMON_INDIC_NUMBER_FORMS [field]  [API 19+]
+ CONTROL_PICTURES [field]  [API 1+]
+ COPTIC [field]  [API 19+]
+ COPTIC_EPACT_NUMBERS [field]  [API 34+]
+ COUNTING_ROD_NUMERALS [field]  [API 19+]
+ CUNEIFORM [field]  [API 19+]
+ CUNEIFORM_NUMBERS_AND_PUNCTUATION [field]  [API 19+]
+ CURRENCY_SYMBOLS [field]  [API 1+]
+ CYPRIOT_SYLLABARY [field]  [API 1+]
+ CYRILLIC [field]  [API 1+]
+ CYRILLIC_EXTENDED_A [field]  [API 19+]
+ CYRILLIC_EXTENDED_B [field]  [API 19+]
+ CYRILLIC_EXTENDED_C [field]  [API 34+]
+ CYRILLIC_SUPPLEMENTARY [field]  [API 1+]
+ DESERET [field]  [API 1+]
+ DEVANAGARI [field]  [API 1+]
+ DEVANAGARI_EXTENDED [field]  [API 19+]
+ DINGBATS [field]  [API 1+]
+ DIVES_AKURU [field]  [API 34+]
+ DOGRA [field]  [API 34+]
+ DOMINO_TILES [field]  [API 19+]
+ DUPLOYAN [field]  [API 34+]
+ EARLY_DYNASTIC_CUNEIFORM [field]  [API 34+]
+ EGYPTIAN_HIEROGLYPHS [field]  [API 19+]
+ EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS [field]  [API 34+]
+ ELBASAN [field]  [API 34+]
+ ELYMAIC [field]  [API 34+]
+ EMOTICONS [field]  [API 19+]
+ ENCLOSED_ALPHANUMERICS [field]  [API 1+]
+ ENCLOSED_ALPHANUMERIC_SUPPLEMENT [field]  [API 19+]
+ ENCLOSED_CJK_LETTERS_AND_MONTHS [field]  [API 1+]
+ ENCLOSED_IDEOGRAPHIC_SUPPLEMENT [field]  [API 19+]
+ ETHIOPIC [field]  [API 1+]
+ ETHIOPIC_EXTENDED [field]  [API 19+]
+ ETHIOPIC_EXTENDED_A [field]  [API 19+]
+ ETHIOPIC_SUPPLEMENT [field]  [API 19+]
+ GENERAL_PUNCTUATION [field]  [API 1+]
+ GEOMETRIC_SHAPES [field]  [API 1+]
+ GEOMETRIC_SHAPES_EXTENDED [field]  [API 34+]
+ GEORGIAN [field]  [API 1+]
+ GEORGIAN_EXTENDED [field]  [API 34+]
+ GEORGIAN_SUPPLEMENT [field]  [API 19+]
+ GLAGOLITIC [field]  [API 19+]
+ GLAGOLITIC_SUPPLEMENT [field]  [API 34+]
+ GOTHIC [field]  [API 1+]
+ GRANTHA [field]  [API 34+]
+ GREEK [field]  [API 1+]
+ GREEK_EXTENDED [field]  [API 1+]
+ GUJARATI [field]  [API 1+]
+ GUNJALA_GONDI [field]  [API 34+]
+ GURMUKHI [field]  [API 1+]
+ HALFWIDTH_AND_FULLWIDTH_FORMS [field]  [API 1+]
+ HANGUL_COMPATIBILITY_JAMO [field]  [API 1+]
+ HANGUL_JAMO [field]  [API 1+]
+ HANGUL_JAMO_EXTENDED_A [field]  [API 19+]
+ HANGUL_JAMO_EXTENDED_B [field]  [API 19+]
+ HANGUL_SYLLABLES [field]  [API 1+]
+ HANIFI_ROHINGYA [field]  [API 34+]
+ HANUNOO [field]  [API 1+]
+ HATRAN [field]  [API 34+]
+ HEBREW [field]  [API 1+]
+ HIGH_PRIVATE_USE_SURROGATES [field]  [API 1+]
+ HIGH_SURROGATES [field]  [API 1+]
+ HIRAGANA [field]  [API 1+]
+ IDEOGRAPHIC_DESCRIPTION_CHARACTERS [field]  [API 1+]
+ IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION [field]  [API 34+]
+ IMPERIAL_ARAMAIC [field]  [API 19+]
+ INDIC_SIYAQ_NUMBERS [field]  [API 34+]
+ INSCRIPTIONAL_PAHLAVI [field]  [API 19+]
+ INSCRIPTIONAL_PARTHIAN [field]  [API 19+]
+ IPA_EXTENSIONS [field]  [API 1+]
+ JAVANESE [field]  [API 19+]
+ KAITHI [field]  [API 19+]
+ KANA_EXTENDED_A [field]  [API 34+]
+ KANA_SUPPLEMENT [field]  [API 19+]
+ KANBUN [field]  [API 1+]
+ KANGXI_RADICALS [field]  [API 1+]
+ KANNADA [field]  [API 1+]
+ KATAKANA [field]  [API 1+]
+ KATAKANA_PHONETIC_EXTENSIONS [field]  [API 1+]
+ KAYAH_LI [field]  [API 19+]
+ KHAROSHTHI [field]  [API 19+]
+ KHITAN_SMALL_SCRIPT [field]  [API 34+]
+ KHMER [field]  [API 1+]
+ KHMER_SYMBOLS [field]  [API 1+]
+ KHOJKI [field]  [API 34+]
+ KHUDAWADI [field]  [API 34+]
+ LAO [field]  [API 1+]
+ LATIN_1_SUPPLEMENT [field]  [API 1+]
+ LATIN_EXTENDED_A [field]  [API 1+]
+ LATIN_EXTENDED_ADDITIONAL [field]  [API 1+]
+ LATIN_EXTENDED_B [field]  [API 1+]
+ LATIN_EXTENDED_C [field]  [API 19+]
+ LATIN_EXTENDED_D [field]  [API 19+]
+ LATIN_EXTENDED_E [field]  [API 34+]
+ LEPCHA [field]  [API 19+]
+ LETTERLIKE_SYMBOLS [field]  [API 1+]
+ LIMBU [field]  [API 1+]
+ LINEAR_A [field]  [API 34+]
+ LINEAR_B_IDEOGRAMS [field]  [API 1+]
+ LINEAR_B_SYLLABARY [field]  [API 1+]
+ LISU [field]  [API 19+]
+ LISU_SUPPLEMENT [field]  [API 34+]
+ LOW_SURROGATES [field]  [API 1+]
+ LYCIAN [field]  [API 19+]
+ LYDIAN [field]  [API 19+]
+ MAHAJANI [field]  [API 34+]
+ MAHJONG_TILES [field]  [API 19+]
+ MAKASAR [field]  [API 34+]
+ MALAYALAM [field]  [API 1+]
+ MANDAIC [field]  [API 19+]
+ MANICHAEAN [field]  [API 34+]
+ MARCHEN [field]  [API 34+]
+ MASARAM_GONDI [field]  [API 34+]
+ MATHEMATICAL_ALPHANUMERIC_SYMBOLS [field]  [API 1+]
+ MATHEMATICAL_OPERATORS [field]  [API 1+]
+ MAYAN_NUMERALS [field]  [API 34+]
+ MEDEFAIDRIN [field]  [API 34+]
+ MEETEI_MAYEK [field]  [API 19+]
+ MEETEI_MAYEK_EXTENSIONS [field]  [API 26+]
+ MENDE_KIKAKUI [field]  [API 34+]
+ MEROITIC_CURSIVE [field]  [API 26+]
+ MEROITIC_HIEROGLYPHS [field]  [API 26+]
+ MIAO [field]  [API 26+]
+ MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A [field]  [API 1+]
+ MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B [field]  [API 1+]
+ MISCELLANEOUS_SYMBOLS [field]  [API 1+]
+ MISCELLANEOUS_SYMBOLS_AND_ARROWS [field]  [API 1+]
+ MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS [field]  [API 19+]
+ MISCELLANEOUS_TECHNICAL [field]  [API 1+]
+ MODI [field]  [API 34+]
+ MODIFIER_TONE_LETTERS [field]  [API 19+]
+ MONGOLIAN [field]  [API 1+]
+ MONGOLIAN_SUPPLEMENT [field]  [API 34+]
+ MRO [field]  [API 34+]
+ MULTANI [field]  [API 34+]
+ MUSICAL_SYMBOLS [field]  [API 1+]
+ MYANMAR [field]  [API 1+]
+ MYANMAR_EXTENDED_A [field]  [API 19+]
+ MYANMAR_EXTENDED_B [field]  [API 34+]
+ NABATAEAN [field]  [API 34+]
+ NANDINAGARI [field]  [API 34+]
+ NEWA [field]  [API 34+]
+ NEW_TAI_LUE [field]  [API 19+]
+ NKO [field]  [API 19+]
+ NUMBER_FORMS [field]  [API 1+]
+ NUSHU [field]  [API 34+]
+ NYIAKENG_PUACHUE_HMONG [field]  [API 34+]
+ OGHAM [field]  [API 1+]
+ OLD_HUNGARIAN [field]  [API 34+]
+ OLD_ITALIC [field]  [API 1+]
+ OLD_NORTH_ARABIAN [field]  [API 34+]
+ OLD_PERMIC [field]  [API 34+]
+ OLD_PERSIAN [field]  [API 19+]
+ OLD_SOGDIAN [field]  [API 34+]
+ OLD_SOUTH_ARABIAN [field]  [API 19+]
+ OLD_TURKIC [field]  [API 19+]
+ OL_CHIKI [field]  [API 19+]
+ OPTICAL_CHARACTER_RECOGNITION [field]  [API 1+]
+ ORIYA [field]  [API 1+]
+ ORNAMENTAL_DINGBATS [field]  [API 34+]
+ OSAGE [field]  [API 34+]
+ OSMANYA [field]  [API 1+]
+ OTTOMAN_SIYAQ_NUMBERS [field]  [API 34+]
+ PAHAWH_HMONG [field]  [API 34+]
+ PALMYRENE [field]  [API 34+]
+ PAU_CIN_HAU [field]  [API 34+]
+ PHAGS_PA [field]  [API 19+]
+ PHAISTOS_DISC [field]  [API 19+]
+ PHOENICIAN [field]  [API 19+]
+ PHONETIC_EXTENSIONS [field]  [API 1+]
+ PHONETIC_EXTENSIONS_SUPPLEMENT [field]  [API 19+]
+ PLAYING_CARDS [field]  [API 19+]
+ PRIVATE_USE_AREA [field]  [API 1+]
+ PSALTER_PAHLAVI [field]  [API 34+]
+ REJANG [field]  [API 19+]
+ RUMI_NUMERAL_SYMBOLS [field]  [API 19+]
+ RUNIC [field]  [API 1+]
+ SAMARITAN [field]  [API 19+]
+ SAURASHTRA [field]  [API 19+]
+ SHARADA [field]  [API 26+]
+ SHAVIAN [field]  [API 1+]
+ SHORTHAND_FORMAT_CONTROLS [field]  [API 34+]
+ SIDDHAM [field]  [API 34+]
+ SINHALA [field]  [API 1+]
+ SINHALA_ARCHAIC_NUMBERS [field]  [API 34+]
+ SMALL_FORM_VARIANTS [field]  [API 1+]
+ SMALL_KANA_EXTENSION [field]  [API 34+]
+ SOGDIAN [field]  [API 34+]
+ SORA_SOMPENG [field]  [API 26+]
+ SOYOMBO [field]  [API 34+]
+ SPACING_MODIFIER_LETTERS [field]  [API 1+]
+ SPECIALS [field]  [API 1+]
+ SUNDANESE [field]  [API 19+]
+ SUNDANESE_SUPPLEMENT [field]  [API 26+]
+ SUPERSCRIPTS_AND_SUBSCRIPTS [field]  [API 1+]
+ SUPPLEMENTAL_ARROWS_A [field]  [API 1+]
+ SUPPLEMENTAL_ARROWS_B [field]  [API 1+]
+ SUPPLEMENTAL_ARROWS_C [field]  [API 34+]
+ SUPPLEMENTAL_MATHEMATICAL_OPERATORS [field]  [API 1+]
+ SUPPLEMENTAL_PUNCTUATION [field]  [API 19+]
+ SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS [field]  [API 34+]
+ SUPPLEMENTARY_PRIVATE_USE_AREA_A [field]  [API 1+]
+ SUPPLEMENTARY_PRIVATE_USE_AREA_B [field]  [API 1+]
+ SURROGATES_AREA [field]  [API 1+, deprecated in 15]
+ SUTTON_SIGNWRITING [field]  [API 34+]
+ SYLOTI_NAGRI [field]  [API 19+]
+ SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A [field]  [API 34+]
+ SYMBOLS_FOR_LEGACY_COMPUTING [field]  [API 34+]
+ SYRIAC [field]  [API 1+]
+ SYRIAC_SUPPLEMENT [field]  [API 34+]
+ TAGALOG [field]  [API 1+]
+ TAGBANWA [field]  [API 1+]
+ TAGS [field]  [API 1+]
+ TAI_LE [field]  [API 1+]
+ TAI_THAM [field]  [API 19+]
+ TAI_VIET [field]  [API 19+]
+ TAI_XUAN_JING_SYMBOLS [field]  [API 1+]
+ TAKRI [field]  [API 26+]
+ TAMIL [field]  [API 1+]
+ TAMIL_SUPPLEMENT [field]  [API 34+]
+ TANGUT [field]  [API 34+]
+ TANGUT_COMPONENTS [field]  [API 34+]
+ TANGUT_SUPPLEMENT [field]  [API 34+]
+ TELUGU [field]  [API 1+]
+ THAANA [field]  [API 1+]
+ THAI [field]  [API 1+]
+ TIBETAN [field]  [API 1+]
+ TIFINAGH [field]  [API 19+]
+ TIRHUTA [field]  [API 34+]
+ TRANSPORT_AND_MAP_SYMBOLS [field]  [API 19+]
+ UGARITIC [field]  [API 1+]
+ UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS [field]  [API 1+]
+ UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED [field]  [API 19+]
+ VAI [field]  [API 19+]
+ VARIATION_SELECTORS [field]  [API 1+]
+ VARIATION_SELECTORS_SUPPLEMENT [field]  [API 1+]
+ VEDIC_EXTENSIONS [field]  [API 19+]
+ VERTICAL_FORMS [field]  [API 19+]
+ WANCHO [field]  [API 34+]
+ WARANG_CITI [field]  [API 34+]
+ YEZIDI [field]  [API 34+]
+ YIJING_HEXAGRAM_SYMBOLS [field]  [API 1+]
+ YI_RADICALS [field]  [API 1+]
+ YI_SYLLABLES [field]  [API 1+]
+ ZANABAZAR_SQUARE [field]  [API 34+]
+ forName(String): Character.UnicodeBlock [static]  [API 1+]
+ of(char): Character.UnicodeBlock [static]  [API 1+]
+ of(int): Character.UnicodeBlock [static]  [API 1+]
```

## Character.UnicodeScript — framework (a2w.jdk.lang.Character$UnicodeScript); API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ ADLAM [field]  [API 34+]
+ AHOM [field]  [API 34+]
+ ANATOLIAN_HIEROGLYPHS [field]  [API 34+]
+ ARABIC [field]  [API 24+]
+ ARMENIAN [field]  [API 24+]
+ AVESTAN [field]  [API 24+]
+ BALINESE [field]  [API 24+]
+ BAMUM [field]  [API 24+]
+ BASSA_VAH [field]  [API 34+]
+ BATAK [field]  [API 24+]
+ BENGALI [field]  [API 24+]
+ BHAIKSUKI [field]  [API 34+]
+ BOPOMOFO [field]  [API 24+]
+ BRAHMI [field]  [API 24+]
+ BRAILLE [field]  [API 24+]
+ BUGINESE [field]  [API 24+]
+ BUHID [field]  [API 24+]
+ CANADIAN_ABORIGINAL [field]  [API 24+]
+ CARIAN [field]  [API 24+]
+ CAUCASIAN_ALBANIAN [field]  [API 34+]
+ CHAKMA [field]  [API 26+]
+ CHAM [field]  [API 24+]
+ CHEROKEE [field]  [API 24+]
+ CHORASMIAN [field]  [API 34+]
+ COMMON [field]  [API 24+]
+ COPTIC [field]  [API 24+]
+ CUNEIFORM [field]  [API 24+]
+ CYPRIOT [field]  [API 24+]
+ CYRILLIC [field]  [API 24+]
+ DESERET [field]  [API 24+]
+ DEVANAGARI [field]  [API 24+]
+ DIVES_AKURU [field]  [API 34+]
+ DOGRA [field]  [API 34+]
+ DUPLOYAN [field]  [API 34+]
+ EGYPTIAN_HIEROGLYPHS [field]  [API 24+]
+ ELBASAN [field]  [API 34+]
+ ELYMAIC [field]  [API 34+]
+ ETHIOPIC [field]  [API 24+]
+ GEORGIAN [field]  [API 24+]
+ GLAGOLITIC [field]  [API 24+]
+ GOTHIC [field]  [API 24+]
+ GRANTHA [field]  [API 34+]
+ GREEK [field]  [API 24+]
+ GUJARATI [field]  [API 24+]
+ GUNJALA_GONDI [field]  [API 34+]
+ GURMUKHI [field]  [API 24+]
+ HAN [field]  [API 24+]
+ HANGUL [field]  [API 24+]
+ HANIFI_ROHINGYA [field]  [API 34+]
+ HANUNOO [field]  [API 24+]
+ HATRAN [field]  [API 34+]
+ HEBREW [field]  [API 24+]
+ HIRAGANA [field]  [API 24+]
+ IMPERIAL_ARAMAIC [field]  [API 24+]
+ INHERITED [field]  [API 24+]
+ INSCRIPTIONAL_PAHLAVI [field]  [API 24+]
+ INSCRIPTIONAL_PARTHIAN [field]  [API 24+]
+ JAVANESE [field]  [API 24+]
+ KAITHI [field]  [API 24+]
+ KANNADA [field]  [API 24+]
+ KATAKANA [field]  [API 24+]
+ KAYAH_LI [field]  [API 24+]
+ KHAROSHTHI [field]  [API 24+]
+ KHITAN_SMALL_SCRIPT [field]  [API 34+]
+ KHMER [field]  [API 24+]
+ KHOJKI [field]  [API 34+]
+ KHUDAWADI [field]  [API 34+]
+ LAO [field]  [API 24+]
+ LATIN [field]  [API 24+]
+ LEPCHA [field]  [API 24+]
+ LIMBU [field]  [API 24+]
+ LINEAR_A [field]  [API 34+]
+ LINEAR_B [field]  [API 24+]
+ LISU [field]  [API 24+]
+ LYCIAN [field]  [API 24+]
+ LYDIAN [field]  [API 24+]
+ MAHAJANI [field]  [API 34+]
+ MAKASAR [field]  [API 34+]
+ MALAYALAM [field]  [API 24+]
+ MANDAIC [field]  [API 24+]
+ MANICHAEAN [field]  [API 34+]
+ MARCHEN [field]  [API 34+]
+ MASARAM_GONDI [field]  [API 34+]
+ MEDEFAIDRIN [field]  [API 34+]
+ MEETEI_MAYEK [field]  [API 24+]
+ MENDE_KIKAKUI [field]  [API 34+]
+ MEROITIC_CURSIVE [field]  [API 26+]
+ MEROITIC_HIEROGLYPHS [field]  [API 26+]
+ MIAO [field]  [API 26+]
+ MODI [field]  [API 34+]
+ MONGOLIAN [field]  [API 24+]
+ MRO [field]  [API 34+]
+ MULTANI [field]  [API 34+]
+ MYANMAR [field]  [API 24+]
+ NABATAEAN [field]  [API 34+]
+ NANDINAGARI [field]  [API 34+]
+ NEWA [field]  [API 34+]
+ NEW_TAI_LUE [field]  [API 24+]
+ NKO [field]  [API 24+]
+ NUSHU [field]  [API 34+]
+ NYIAKENG_PUACHUE_HMONG [field]  [API 34+]
+ OGHAM [field]  [API 24+]
+ OLD_HUNGARIAN [field]  [API 34+]
+ OLD_ITALIC [field]  [API 24+]
+ OLD_NORTH_ARABIAN [field]  [API 34+]
+ OLD_PERMIC [field]  [API 34+]
+ OLD_PERSIAN [field]  [API 24+]
+ OLD_SOGDIAN [field]  [API 34+]
+ OLD_SOUTH_ARABIAN [field]  [API 24+]
+ OLD_TURKIC [field]  [API 24+]
+ OL_CHIKI [field]  [API 24+]
+ ORIYA [field]  [API 24+]
+ OSAGE [field]  [API 34+]
+ OSMANYA [field]  [API 24+]
+ PAHAWH_HMONG [field]  [API 34+]
+ PALMYRENE [field]  [API 34+]
+ PAU_CIN_HAU [field]  [API 34+]
+ PHAGS_PA [field]  [API 24+]
+ PHOENICIAN [field]  [API 24+]
+ PSALTER_PAHLAVI [field]  [API 34+]
+ REJANG [field]  [API 24+]
+ RUNIC [field]  [API 24+]
+ SAMARITAN [field]  [API 24+]
+ SAURASHTRA [field]  [API 24+]
+ SHARADA [field]  [API 26+]
+ SHAVIAN [field]  [API 24+]
+ SIDDHAM [field]  [API 34+]
+ SIGNWRITING [field]  [API 34+]
+ SINHALA [field]  [API 24+]
+ SOGDIAN [field]  [API 34+]
+ SORA_SOMPENG [field]  [API 26+]
+ SOYOMBO [field]  [API 34+]
+ SUNDANESE [field]  [API 24+]
+ SYLOTI_NAGRI [field]  [API 24+]
+ SYRIAC [field]  [API 24+]
+ TAGALOG [field]  [API 24+]
+ TAGBANWA [field]  [API 24+]
+ TAI_LE [field]  [API 24+]
+ TAI_THAM [field]  [API 24+]
+ TAI_VIET [field]  [API 24+]
+ TAKRI [field]  [API 26+]
+ TAMIL [field]  [API 24+]
+ TANGUT [field]  [API 34+]
+ TELUGU [field]  [API 24+]
+ THAANA [field]  [API 24+]
+ THAI [field]  [API 24+]
+ TIBETAN [field]  [API 24+]
+ TIFINAGH [field]  [API 24+]
+ TIRHUTA [field]  [API 34+]
+ UGARITIC [field]  [API 24+]
+ UNKNOWN [field]  [API 24+]
+ VAI [field]  [API 24+]
+ WANCHO [field]  [API 34+]
+ WARANG_CITI [field]  [API 34+]
+ YEZIDI [field]  [API 34+]
+ YI [field]  [API 24+]
+ ZANABAZAR_SQUARE [field]  [API 34+]
+ forName(String): Character.UnicodeScript [static]  [API 24+]
+ of(int): Character.UnicodeScript [static]  [API 24+]
+ valueOf(String): Character.UnicodeScript [static]  [API 24+]
+ values(): Character.UnicodeScript[] [static]  [API 24+]
```

## Class — TeaVM class library; API 1+ (62 +, 0 *, 0 ~, 11 -)

```
+ arrayType(): Class  [API 34+]
+ asSubclass(Class): Class  [API 1+]
+ cast(Object): Object  [API 1+]
+ componentType(): Class  [API 34+]
+ descriptorString(): String  [API 1+]
+ desiredAssertionStatus(): boolean  [API 1+]
+ forName(String): Class [static]  [API 1+]
+ forName(String, boolean, ClassLoader): Class [static]  [API 1+]
+ getAnnotation(Class): Annotation  [API 1+]
+ getAnnotations(): Annotation[]  [API 1+]
+ getAnnotationsByType(Class): Annotation[]  [API 1+]
+ getCanonicalName(): String  [API 1+]
+ getClassLoader(): ClassLoader  [API 1+]
+ getClasses(): Class[]  [API 1+]
+ getComponentType(): Class  [API 1+]
+ getConstructor(Class[]): Constructor  [API 1+]
+ getConstructors(): Constructor[]  [API 1+]
+ getDeclaredAnnotation(Class): Annotation  [API 1+]
+ getDeclaredAnnotations(): Annotation[]  [API 1+]
+ getDeclaredAnnotationsByType(Class): Annotation[]  [API 1+]
+ getDeclaredClasses(): Class[]  [API 1+]
+ getDeclaredConstructor(Class[]): Constructor  [API 1+]
+ getDeclaredConstructors(): Constructor[]  [API 1+]
+ getDeclaredField(String): Field  [API 1+]
+ getDeclaredFields(): Field[]  [API 1+]
+ getDeclaredMethod(String, Class[]): Method  [API 1+]
+ getDeclaredMethods(): Method[]  [API 1+]
+ getDeclaringClass(): Class  [API 1+]
+ getEnclosingClass(): Class  [API 1+]
- getEnclosingConstructor(): Constructor  [API 1+]
- getEnclosingMethod(): Method  [API 1+]
+ getEnumConstants(): Object[]  [API 1+]
+ getField(String): Field  [API 1+]
+ getFields(): Field[]  [API 1+]
+ getGenericInterfaces(): Type[]  [API 1+]
+ getGenericSuperclass(): Type  [API 1+]
+ getInterfaces(): Class[]  [API 1+]
+ getMethod(String, Class[]): Method  [API 1+]
+ getMethods(): Method[]  [API 1+]
+ getModifiers(): int  [API 1+]
+ getName(): String  [API 1+]
- getNestHost(): Class  [API 34+]
- getNestMembers(): Class[]  [API 34+]
+ getPackage(): Package  [API 1+]
+ getPackageName(): String  [API 31+]
- getPermittedSubclasses(): Class[]  [API 34+]
- getProtectionDomain(): ProtectionDomain  [API 1+]
- getRecordComponents(): RecordComponent[]  [API 34+]
+ getResource(String): URL  [API 1+]
+ getResourceAsStream(String): InputStream  [API 1+]
- getSigners(): Object[]  [API 1+]
+ getSimpleName(): String  [API 1+]
+ getSuperclass(): Class  [API 1+]
+ getTypeName(): String  [API 26+]
+ getTypeParameters(): TypeVariable[]  [API 1+]
+ isAnnotation(): boolean  [API 1+]
+ isAnnotationPresent(Class): boolean  [API 1+]
+ isAnonymousClass(): boolean  [API 1+]
+ isArray(): boolean  [API 1+]
+ isAssignableFrom(Class): boolean  [API 1+]
+ isEnum(): boolean  [API 1+]
+ isInstance(Object): boolean  [API 1+]
+ isInterface(): boolean  [API 1+]
+ isLocalClass(): boolean  [API 1+]
+ isMemberClass(): boolean  [API 1+]
- isNestmateOf(Class): boolean  [API 34+]
+ isPrimitive(): boolean  [API 1+]
+ isRecord(): boolean  [API 34+]
- isSealed(): boolean  [API 34+]
- isSynthetic(): boolean  [API 1+]
+ newInstance(): Object  [API 1+, deprecated in 34]
+ toGenericString(): String  [API 26+]
+ toString(): String  [API 1+]
```

## ClassCastException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ClassCastException()  [API 1+]
+ ClassCastException(String)  [API 1+]
```

## ClassCircularityError — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ClassCircularityError()  [API 1+]
+ ClassCircularityError(String)  [API 1+]
```

## ClassFormatError — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ClassFormatError()  [API 1+]
+ ClassFormatError(String)  [API 1+]
```

## ClassLoader — TeaVM class library; API 1+ (8 +, 0 *, 0 ~, 24 -)

```
+ ClassLoader()  [API 1+]
+ ClassLoader(ClassLoader)  [API 1+]
- clearAssertionStatus(): void  [API 1+]
- defineClass(String, ByteBuffer, ProtectionDomain): Class  [API 1+]
- defineClass(String, byte[], int, int): Class  [API 1+]
- defineClass(String, byte[], int, int, ProtectionDomain): Class  [API 1+]
- defineClass(byte[], int, int): Class  [API 1+, deprecated in 15]
- definePackage(String, String, String, String, String, String, String, URL): Package  [API 1+]
- findClass(String): Class  [API 1+]
- findLibrary(String): String  [API 1+]
- findLoadedClass(String): Class  [API 1+]
- findResource(String): URL  [API 1+]
- findResources(String): Enumeration  [API 1+]
- findSystemClass(String): Class  [API 1+]
- getPackage(String): Package  [API 1+, deprecated in 35]
- getPackages(): Package[]  [API 1+]
+ getParent(): ClassLoader  [API 1+]
- getResource(String): URL  [API 1+]
+ getResourceAsStream(String): InputStream  [API 1+]
+ getResources(String): Enumeration  [API 1+]
+ getSystemClassLoader(): ClassLoader [static]  [API 1+]
- getSystemResource(String): URL [static]  [API 1+]
+ getSystemResourceAsStream(String): InputStream [static]  [API 1+]
- getSystemResources(String): Enumeration [static]  [API 1+]
+ loadClass(String): Class  [API 1+]
- loadClass(String, boolean): Class  [API 1+]
- registerAsParallelCapable(): boolean [static]  [API 24+]
- resolveClass(Class): void  [API 1+]
- setClassAssertionStatus(String, boolean): void  [API 1+]
- setDefaultAssertionStatus(boolean): void  [API 1+]
- setPackageAssertionStatus(String, boolean): void  [API 1+]
- setSigners(Class, Object[]): void  [API 1+]
```

## ClassNotFoundException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ ClassNotFoundException()  [API 1+]
+ ClassNotFoundException(String)  [API 1+]
+ ClassNotFoundException(String, Throwable)  [API 1+]
+ getException(): Throwable  [API 1+]
```

## ClassValue — TeaVM class library; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ ClassValue()  [API 34+]
+ computeValue(Class): Object  [API 34+]
+ get(Class): Object  [API 34+]
+ remove(Class): void  [API 34+]
```

## CloneNotSupportedException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CloneNotSupportedException()  [API 1+]
+ CloneNotSupportedException(String)  [API 1+]
```

## Cloneable — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## Comparable — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ compareTo(Object): int  [API 1+]
```

## Compiler — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- command(Object): Object [static]  [API 1+]
- compileClass(Class): boolean [static]  [API 1+]
- compileClasses(String): boolean [static]  [API 1+]
- disable(): void [static]  [API 1+]
- enable(): void [static]  [API 1+]
```

## Deprecated — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- forRemoval(): boolean  [API 31+]
- since(): String  [API 31+]
```

## Double — TeaVM class library; API 1+ (30 +, 0 *, 0 ~, 0 -)

```
+ Double(String)  [API 1+, deprecated in 33]
+ Double(double)  [API 1+, deprecated in 33]
+ TYPE [field]  [API 1+]
+ byteValue(): byte  [API 1+]
+ compare(double, double): int [static]  [API 1+]
+ compareTo(Double): int  [API 1+]
+ doubleToLongBits(double): long [static]  [API 1+]
+ doubleToRawLongBits(double): long [static]  [API 1+]
+ doubleValue(): double  [API 1+]
+ equals(Object): boolean  [API 1+]
+ floatValue(): float  [API 1+]
+ hashCode(): int  [API 1+]
+ hashCode(double): int [static]  [API 24+]
+ intValue(): int  [API 1+]
+ isFinite(double): boolean [static]  [API 24+]
+ isInfinite(): boolean  [API 1+]
+ isInfinite(double): boolean [static]  [API 1+]
+ isNaN(): boolean  [API 1+]
+ isNaN(double): boolean [static]  [API 1+]
+ longBitsToDouble(long): double [static]  [API 1+]
+ longValue(): long  [API 1+]
+ max(double, double): double [static]  [API 24+]
+ min(double, double): double [static]  [API 24+]
+ parseDouble(String): double [static]  [API 1+]
+ shortValue(): short  [API 1+]
+ sum(double, double): double [static]  [API 24+]
+ toHexString(double): String [static]  [API 1+]
+ toString(): String  [API 1+]
+ toString(double): String [static]  [API 1+]
+ valueOf(String): Double [static]  [API 1+]
+ valueOf(double): Double [static]  [API 1+]
```

## Enum — TeaVM class library; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ Enum(String, int)  [API 1+]
+ clone(): Object  [API 1+]
+ compareTo(Enum): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ finalize(): void  [API 1+]
+ getDeclaringClass(): Class  [API 1+]
+ hashCode(): int  [API 1+]
+ name(): String  [API 1+]
+ ordinal(): int  [API 1+]
+ toString(): String  [API 1+]
+ valueOf(Class, String): Enum [static]  [API 1+]
```

## EnumConstantNotPresentException — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- EnumConstantNotPresentException(Class, String)  [API 1+]
- constantName(): String  [API 1+]
- enumType(): Class  [API 1+]
```

## Error — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 1 -)

```
+ Error()  [API 1+]
+ Error(String)  [API 1+]
+ Error(String, Throwable)  [API 1+]
- Error(String, Throwable, boolean, boolean)  [API 24+]
+ Error(Throwable)  [API 1+]
```

## Exception — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 1 -)

```
+ Exception()  [API 1+]
+ Exception(String)  [API 1+]
+ Exception(String, Throwable)  [API 1+]
- Exception(String, Throwable, boolean, boolean)  [API 24+]
+ Exception(Throwable)  [API 1+]
```

## ExceptionInInitializerError — declaration; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
+ ExceptionInInitializerError()  [API 1+]
+ ExceptionInInitializerError(String)  [API 1+]
- ExceptionInInitializerError(Throwable)  [API 1+]
- getException(): Throwable  [API 1+]
```

## Float — TeaVM class library; API 1+ (31 +, 0 *, 0 ~, 2 -)

```
+ Float(String)  [API 1+, deprecated in 33]
+ Float(double)  [API 1+, deprecated in 33]
+ Float(float)  [API 1+, deprecated in 33]
+ TYPE [field]  [API 1+]
+ byteValue(): byte  [API 1+]
+ compare(float, float): int [static]  [API 1+]
+ compareTo(Float): int  [API 1+]
+ doubleValue(): double  [API 1+]
+ equals(Object): boolean  [API 1+]
- float16ToFloat(short): float  [API 36+]
- floatToFloat16(float): short  [API 36+]
+ floatToIntBits(float): int [static]  [API 1+]
+ floatToRawIntBits(float): int [static]  [API 1+]
+ floatValue(): float  [API 1+]
+ hashCode(): int  [API 1+]
+ hashCode(float): int [static]  [API 24+]
+ intBitsToFloat(int): float [static]  [API 1+]
+ intValue(): int  [API 1+]
+ isFinite(float): boolean [static]  [API 24+]
+ isInfinite(): boolean  [API 1+]
+ isInfinite(float): boolean [static]  [API 1+]
+ isNaN(): boolean  [API 1+]
+ isNaN(float): boolean [static]  [API 1+]
+ longValue(): long  [API 1+]
+ max(float, float): float [static]  [API 24+]
+ min(float, float): float [static]  [API 24+]
+ parseFloat(String): float [static]  [API 1+]
+ shortValue(): short  [API 1+]
+ sum(float, float): float [static]  [API 24+]
+ toHexString(float): String [static]  [API 1+]
+ toString(): String  [API 1+]
+ toString(float): String [static]  [API 1+]
+ valueOf(String): Float [static]  [API 1+]
+ valueOf(float): Float [static]  [API 1+]
```

## FunctionalInterface — TeaVM class library; API 24+ (0 +, 0 *, 0 ~, 0 -)

## IllegalAccessError — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ IllegalAccessError()  [API 1+]
+ IllegalAccessError(String)  [API 1+]
```

## IllegalAccessException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ IllegalAccessException()  [API 1+]
+ IllegalAccessException(String)  [API 1+]
```

## IllegalArgumentException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ IllegalArgumentException()  [API 1+]
+ IllegalArgumentException(String)  [API 1+]
+ IllegalArgumentException(String, Throwable)  [API 1+]
+ IllegalArgumentException(Throwable)  [API 1+]
```

## IllegalMonitorStateException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ IllegalMonitorStateException()  [API 1+]
+ IllegalMonitorStateException(String)  [API 1+]
```

## IllegalStateException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ IllegalStateException()  [API 1+]
+ IllegalStateException(String)  [API 1+]
+ IllegalStateException(String, Throwable)  [API 1+]
+ IllegalStateException(Throwable)  [API 1+]
```

## IllegalThreadStateException — framework (a2w.jdk.IllegalThreadStateException); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ IllegalThreadStateException()  [API 1+]
+ IllegalThreadStateException(String)  [API 1+]
```

## IncompatibleClassChangeError — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ IncompatibleClassChangeError()  [API 1+]
+ IncompatibleClassChangeError(String)  [API 1+]
```

## IndexOutOfBoundsException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 1 -)

```
+ IndexOutOfBoundsException()  [API 1+]
+ IndexOutOfBoundsException(String)  [API 1+]
+ IndexOutOfBoundsException(int)  [API 33+]
- IndexOutOfBoundsException(long)  [API 34+]
```

## InheritableThreadLocal — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ InheritableThreadLocal()  [API 1+]
+ childValue(Object): Object  [API 1+]
```

## InstantiationError — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ InstantiationError()  [API 1+]
+ InstantiationError(String)  [API 1+]
```

## InstantiationException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ InstantiationException()  [API 1+]
+ InstantiationException(String)  [API 1+]
```

## Integer — TeaVM class library; API 1+ (53 +, 0 *, 0 ~, 0 -)

```
+ Integer(String)  [API 1+, deprecated in 33]
+ Integer(int)  [API 1+, deprecated in 33]
+ TYPE [field]  [API 1+]
+ bitCount(int): int [static]  [API 1+]
+ byteValue(): byte  [API 1+]
+ compare(int, int): int [static]  [API 19+]
+ compareTo(Integer): int  [API 1+]
+ compareUnsigned(int, int): int [static]  [API 26+]
+ compress(int, int): int  [API 36+]
+ decode(String): Integer [static]  [API 1+]
+ divideUnsigned(int, int): int [static]  [API 26+]
+ doubleValue(): double  [API 1+]
+ equals(Object): boolean  [API 1+]
+ expand(int, int): int  [API 36+]
+ floatValue(): float  [API 1+]
+ getInteger(String): Integer [static]  [API 1+]
+ getInteger(String, Integer): Integer [static]  [API 1+]
+ getInteger(String, int): Integer [static]  [API 1+]
+ hashCode(): int  [API 1+]
+ hashCode(int): int [static]  [API 24+]
+ highestOneBit(int): int [static]  [API 1+]
+ intValue(): int  [API 1+]
+ longValue(): long  [API 1+]
+ lowestOneBit(int): int [static]  [API 1+]
+ max(int, int): int [static]  [API 24+]
+ min(int, int): int [static]  [API 24+]
+ numberOfLeadingZeros(int): int [static]  [API 1+]
+ numberOfTrailingZeros(int): int [static]  [API 1+]
+ parseInt(CharSequence, int, int, int): int [static]  [API 33+]
+ parseInt(String): int [static]  [API 1+]
+ parseInt(String, int): int [static]  [API 1+]
+ parseUnsignedInt(CharSequence, int, int, int): int [static]  [API 33+]
+ parseUnsignedInt(String): int [static]  [API 26+]
+ parseUnsignedInt(String, int): int [static]  [API 26+]
+ remainderUnsigned(int, int): int [static]  [API 26+]
+ reverse(int): int [static]  [API 1+]
+ reverseBytes(int): int [static]  [API 1+]
+ rotateLeft(int, int): int [static]  [API 1+]
+ rotateRight(int, int): int [static]  [API 1+]
+ shortValue(): short  [API 1+]
+ signum(int): int [static]  [API 1+]
+ sum(int, int): int [static]  [API 24+]
+ toBinaryString(int): String [static]  [API 1+]
+ toHexString(int): String [static]  [API 1+]
+ toOctalString(int): String [static]  [API 1+]
+ toString(): String  [API 1+]
+ toString(int): String [static]  [API 1+]
+ toString(int, int): String [static]  [API 1+]
+ toUnsignedLong(int): long [static]  [API 26+]
+ toUnsignedString(int): String [static]  [API 26+]
+ toUnsignedString(int, int): String [static]  [API 26+]
+ valueOf(String): Integer [static]  [API 1+]
+ valueOf(String, int): Integer [static]  [API 1+]
+ valueOf(int): Integer [static]  [API 1+]
```

## InternalError — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ InternalError()  [API 1+]
+ InternalError(String)  [API 1+]
+ InternalError(String, Throwable)  [API 24+]
+ InternalError(Throwable)  [API 24+]
```

## InterruptedException — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
+ InterruptedException()  [API 1+]
- InterruptedException(String)  [API 1+]
```

## Iterable — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ forEach(Consumer): void  [API 24+]
+ iterator(): Iterator  [API 1+]
+ spliterator(): Spliterator  [API 24+]
```

## LinkageError — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ LinkageError()  [API 1+]
+ LinkageError(String)  [API 1+]
+ LinkageError(String, Throwable)  [API 19+]
```

## Long — TeaVM class library; API 1+ (52 +, 0 *, 0 ~, 0 -)

```
+ Long(String)  [API 1+, deprecated in 33]
+ Long(long)  [API 1+, deprecated in 33]
+ TYPE [field]  [API 1+]
+ bitCount(long): int [static]  [API 1+]
+ byteValue(): byte  [API 1+]
+ compare(long, long): int [static]  [API 19+]
+ compareTo(Long): int  [API 1+]
+ compareUnsigned(long, long): int [static]  [API 26+]
+ compress(long, long): long  [API 36+]
+ decode(String): Long [static]  [API 1+]
+ divideUnsigned(long, long): long [static]  [API 26+]
+ doubleValue(): double  [API 1+]
+ equals(Object): boolean  [API 1+]
+ expand(long, long): long  [API 36+]
+ floatValue(): float  [API 1+]
+ getLong(String): Long [static]  [API 1+]
+ getLong(String, Long): Long [static]  [API 1+]
+ getLong(String, long): Long [static]  [API 1+]
+ hashCode(): int  [API 1+]
+ hashCode(long): int [static]  [API 24+]
+ highestOneBit(long): long [static]  [API 1+]
+ intValue(): int  [API 1+]
+ longValue(): long  [API 1+]
+ lowestOneBit(long): long [static]  [API 1+]
+ max(long, long): long [static]  [API 24+]
+ min(long, long): long [static]  [API 24+]
+ numberOfLeadingZeros(long): int [static]  [API 1+]
+ numberOfTrailingZeros(long): int [static]  [API 1+]
+ parseLong(CharSequence, int, int, int): long [static]  [API 33+]
+ parseLong(String): long [static]  [API 1+]
+ parseLong(String, int): long [static]  [API 1+]
+ parseUnsignedLong(CharSequence, int, int, int): long [static]  [API 33+]
+ parseUnsignedLong(String): long [static]  [API 26+]
+ parseUnsignedLong(String, int): long [static]  [API 26+]
+ remainderUnsigned(long, long): long [static]  [API 26+]
+ reverse(long): long [static]  [API 1+]
+ reverseBytes(long): long [static]  [API 1+]
+ rotateLeft(long, int): long [static]  [API 1+]
+ rotateRight(long, int): long [static]  [API 1+]
+ shortValue(): short  [API 1+]
+ signum(long): int [static]  [API 1+]
+ sum(long, long): long [static]  [API 24+]
+ toBinaryString(long): String [static]  [API 1+]
+ toHexString(long): String [static]  [API 1+]
+ toOctalString(long): String [static]  [API 1+]
+ toString(): String  [API 1+]
+ toString(long): String [static]  [API 1+]
+ toString(long, int): String [static]  [API 1+]
+ toUnsignedString(long): String [static]  [API 26+]
+ toUnsignedString(long, int): String [static]  [API 26+]
+ valueOf(String): Long [static]  [API 1+]
+ valueOf(String, int): Long [static]  [API 1+]
+ valueOf(long): Long [static]  [API 1+]
```

## MatchException — TeaVM class library; API 36+ (1 +, 0 *, 0 ~, 0 -)

```
+ MatchException(String, Throwable)  [API 36+]
```

## Math — TeaVM class library; API 1+ (99 +, 0 *, 0 ~, 0 -)

```
+ IEEEremainder(double, double): double [static]  [API 1+]
+ abs(double): double [static]  [API 1+]
+ abs(float): float [static]  [API 1+]
+ abs(int): int [static]  [API 1+]
+ abs(long): long [static]  [API 1+]
+ absExact(int): int [static]  [API 34+]
+ absExact(long): long [static]  [API 34+]
+ acos(double): double [static]  [API 1+]
+ addExact(int, int): int [static]  [API 24+]
+ addExact(long, long): long [static]  [API 24+]
+ asin(double): double [static]  [API 1+]
+ atan(double): double [static]  [API 1+]
+ atan2(double, double): double [static]  [API 1+]
+ cbrt(double): double [static]  [API 1+]
+ ceil(double): double [static]  [API 1+]
+ ceilDiv(int, int): int [static]  [API 35+]
+ ceilDiv(long, int): long [static]  [API 35+]
+ ceilDiv(long, long): long [static]  [API 35+]
+ ceilDivExact(int, int): int [static]  [API 35+]
+ ceilDivExact(long, long): long [static]  [API 35+]
+ ceilMod(int, int): int [static]  [API 35+]
+ ceilMod(long, int): int [static]  [API 35+]
+ ceilMod(long, long): long [static]  [API 35+]
+ clamp(double, double, double): double [static]  [API 35+]
+ clamp(float, float, float): float [static]  [API 35+]
+ clamp(long, int, int): int [static]  [API 35+]
+ clamp(long, long, long): long [static]  [API 35+]
+ copySign(double, double): double [static]  [API 9+]
+ copySign(float, float): float [static]  [API 9+]
+ cos(double): double [static]  [API 1+]
+ cosh(double): double [static]  [API 1+]
+ decrementExact(int): int [static]  [API 24+]
+ decrementExact(long): long [static]  [API 24+]
+ divideExact(int, int): int [static]  [API 35+]
+ divideExact(long, long): long [static]  [API 35+]
+ exp(double): double [static]  [API 1+]
+ expm1(double): double [static]  [API 1+]
+ floor(double): double [static]  [API 1+]
+ floorDiv(int, int): int [static]  [API 24+]
+ floorDiv(long, int): long [static]  [API 31+]
+ floorDiv(long, long): long [static]  [API 24+]
+ floorDivExact(int, int): int [static]  [API 35+]
+ floorDivExact(long, long): long [static]  [API 35+]
+ floorMod(int, int): int [static]  [API 24+]
+ floorMod(long, int): int [static]  [API 31+]
+ floorMod(long, long): long [static]  [API 24+]
+ fma(double, double, double): double [static]  [API 33+]
+ fma(float, float, float): float [static]  [API 33+]
+ getExponent(double): int [static]  [API 9+]
+ getExponent(float): int [static]  [API 9+]
+ hypot(double, double): double [static]  [API 1+]
+ incrementExact(int): int [static]  [API 24+]
+ incrementExact(long): long [static]  [API 24+]
+ log(double): double [static]  [API 1+]
+ log10(double): double [static]  [API 1+]
+ log1p(double): double [static]  [API 1+]
+ max(double, double): double [static]  [API 1+]
+ max(float, float): float [static]  [API 1+]
+ max(int, int): int [static]  [API 1+]
+ max(long, long): long [static]  [API 1+]
+ min(double, double): double [static]  [API 1+]
+ min(float, float): float [static]  [API 1+]
+ min(int, int): int [static]  [API 1+]
+ min(long, long): long [static]  [API 1+]
+ multiplyExact(int, int): int [static]  [API 24+]
+ multiplyExact(long, int): long [static]  [API 31+]
+ multiplyExact(long, long): long [static]  [API 24+]
+ multiplyFull(int, int): long [static]  [API 31+]
+ multiplyHigh(long, long): long [static]  [API 31+]
+ negateExact(int): int [static]  [API 24+]
+ negateExact(long): long [static]  [API 24+]
+ nextAfter(double, double): double [static]  [API 9+]
+ nextAfter(float, double): float [static]  [API 9+]
+ nextDown(double): double [static]  [API 24+]
+ nextDown(float): float [static]  [API 24+]
+ nextUp(double): double [static]  [API 9+]
+ nextUp(float): float [static]  [API 9+]
+ pow(double, double): double [static]  [API 1+]
+ random(): double [static]  [API 1+]
+ rint(double): double [static]  [API 1+]
+ round(double): long [static]  [API 1+]
+ round(float): int [static]  [API 1+]
+ scalb(double, int): double [static]  [API 9+]
+ scalb(float, int): float [static]  [API 9+]
+ signum(double): double [static]  [API 1+]
+ signum(float): float [static]  [API 1+]
+ sin(double): double [static]  [API 1+]
+ sinh(double): double [static]  [API 1+]
+ sqrt(double): double [static]  [API 1+]
+ subtractExact(int, int): int [static]  [API 24+]
+ subtractExact(long, long): long [static]  [API 24+]
+ tan(double): double [static]  [API 1+]
+ tanh(double): double [static]  [API 1+]
+ toDegrees(double): double [static]  [API 1+]
+ toIntExact(long): int [static]  [API 24+]
+ toRadians(double): double [static]  [API 1+]
+ ulp(double): double [static]  [API 1+]
+ ulp(float): float [static]  [API 1+]
+ unsignedMultiplyHigh(long, long): long [static]  [API 35+]
```

## NegativeArraySizeException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NegativeArraySizeException()  [API 1+]
+ NegativeArraySizeException(String)  [API 1+]
```

## NoClassDefFoundError — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoClassDefFoundError()  [API 1+]
+ NoClassDefFoundError(String)  [API 1+]
```

## NoSuchFieldError — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoSuchFieldError()  [API 1+]
+ NoSuchFieldError(String)  [API 1+]
```

## NoSuchFieldException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoSuchFieldException()  [API 1+]
+ NoSuchFieldException(String)  [API 1+]
```

## NoSuchMethodError — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoSuchMethodError()  [API 1+]
+ NoSuchMethodError(String)  [API 1+]
```

## NoSuchMethodException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoSuchMethodException()  [API 1+]
+ NoSuchMethodException(String)  [API 1+]
```

## NullPointerException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NullPointerException()  [API 1+]
+ NullPointerException(String)  [API 1+]
```

## Number — TeaVM class library; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ Number()  [API 1+]
+ byteValue(): byte  [API 1+]
+ doubleValue(): double  [API 1+]
+ floatValue(): float  [API 1+]
+ intValue(): int  [API 1+]
+ longValue(): long  [API 1+]
+ shortValue(): short  [API 1+]
```

## NumberFormatException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NumberFormatException()  [API 1+]
+ NumberFormatException(String)  [API 1+]
```

## Object — TeaVM class library; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ Object()  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ finalize(): void  [API 1+]
+ getClass(): Class  [API 1+]
+ hashCode(): int  [API 1+]
+ notify(): void  [API 1+]
+ notifyAll(): void  [API 1+]
+ toString(): String  [API 1+]
+ wait(): void  [API 1+]
+ wait(long): void  [API 1+]
+ wait(long, int): void  [API 1+]
```

## OutOfMemoryError — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ OutOfMemoryError()  [API 1+]
+ OutOfMemoryError(String)  [API 1+]
```

## Override — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## Package — TeaVM class library; API 1+ (11 +, 0 *, 0 ~, 10 -)

```
+ getAnnotation(Class): Annotation  [API 1+]
+ getAnnotations(): Annotation[]  [API 1+]
+ getAnnotationsByType(Class): Annotation[]  [API 1+]
+ getDeclaredAnnotation(Class): Annotation  [API 1+]
+ getDeclaredAnnotations(): Annotation[]  [API 1+]
+ getDeclaredAnnotationsByType(Class): Annotation[]  [API 1+]
- getImplementationTitle(): String  [API 1+]
- getImplementationVendor(): String  [API 1+]
- getImplementationVersion(): String  [API 1+]
+ getName(): String  [API 1+]
+ getPackage(String): Package [static]  [API 1+]
- getPackages(): Package[] [static]  [API 1+]
- getSpecificationTitle(): String  [API 1+]
- getSpecificationVendor(): String  [API 1+]
- getSpecificationVersion(): String  [API 1+]
+ hashCode(): int  [API 1+]
+ isAnnotationPresent(Class): boolean  [API 1+]
- isCompatibleWith(String): boolean  [API 1+]
- isSealed(): boolean  [API 1+]
- isSealed(URL): boolean  [API 1+]
+ toString(): String  [API 1+]
```

## Process — declaration; API 1+ (6 +, 0 *, 0 ~, 4 -)

```
- Process()  [API 1+]
+ destroy(): void  [API 1+]
- destroyForcibly(): Process  [API 26+]
+ exitValue(): int  [API 1+]
+ getErrorStream(): InputStream  [API 1+]
+ getInputStream(): InputStream  [API 1+]
+ getOutputStream(): OutputStream  [API 1+]
- isAlive(): boolean  [API 26+]
+ waitFor(): int  [API 1+]
- waitFor(long, TimeUnit): boolean  [API 26+]
```

## ProcessBuilder — declaration; API 1+ (0 +, 0 *, 0 ~, 21 -)

```
- ProcessBuilder(List)  [API 1+]
- ProcessBuilder(String[])  [API 1+]
- command(): List  [API 1+]
- command(List): ProcessBuilder  [API 1+]
- command(String[]): ProcessBuilder  [API 1+]
- directory(): File  [API 1+]
- directory(File): ProcessBuilder  [API 1+]
- environment(): Map  [API 1+]
- inheritIO(): ProcessBuilder  [API 26+]
- redirectError(): ProcessBuilder.Redirect  [API 26+]
- redirectError(File): ProcessBuilder  [API 26+]
- redirectError(ProcessBuilder.Redirect): ProcessBuilder  [API 26+]
- redirectErrorStream(): boolean  [API 1+]
- redirectErrorStream(boolean): ProcessBuilder  [API 1+]
- redirectInput(): ProcessBuilder.Redirect  [API 26+]
- redirectInput(File): ProcessBuilder  [API 26+]
- redirectInput(ProcessBuilder.Redirect): ProcessBuilder  [API 26+]
- redirectOutput(): ProcessBuilder.Redirect  [API 26+]
- redirectOutput(File): ProcessBuilder  [API 26+]
- redirectOutput(ProcessBuilder.Redirect): ProcessBuilder  [API 26+]
- start(): Process  [API 1+]
```

## ProcessBuilder.Redirect — declaration; API 26+ (1 +, 0 *, 0 ~, 6 -)

```
- INHERIT [field]  [API 26+]
- PIPE [field]  [API 26+]
- appendTo(File): ProcessBuilder.Redirect [static]  [API 26+]
- equals(Object): boolean  [API 26+]
- file(): File  [API 26+]
- from(File): ProcessBuilder.Redirect [static]  [API 26+]
- hashCode(): int  [API 26+]
- to(File): ProcessBuilder.Redirect [static]  [API 26+]
+ type(): ProcessBuilder.Redirect.Type  [API 26+]
```

## ProcessBuilder.Redirect.Type — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ APPEND [field]  [API 26+]
+ INHERIT [field]  [API 26+]
+ PIPE [field]  [API 26+]
+ READ [field]  [API 26+]
+ WRITE [field]  [API 26+]
+ valueOf(String): ProcessBuilder.Redirect.Type [static]  [API 26+]
+ values(): ProcessBuilder.Redirect.Type[] [static]  [API 26+]
```

## Readable — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ read(CharBuffer): int  [API 1+]
```

## Record — TeaVM class library; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ Record()  [API 34+]
+ equals(Object): boolean  [API 34+]
+ hashCode(): int  [API 34+]
+ toString(): String  [API 34+]
```

## ReflectiveOperationException — TeaVM class library; API 19+ (4 +, 0 *, 0 ~, 0 -)

```
+ ReflectiveOperationException()  [API 19+]
+ ReflectiveOperationException(String)  [API 19+]
+ ReflectiveOperationException(String, Throwable)  [API 19+]
+ ReflectiveOperationException(Throwable)  [API 19+]
```

## Runnable — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ run(): void  [API 1+]
```

## Runtime — TeaVM class library; API 1+ (16 +, 0 *, 0 ~, 8 -)

```
+ addShutdownHook(Thread): void  [API 1+]
+ availableProcessors(): int  [API 1+]
- exec(String): Process  [API 1+]
- exec(String, String[]): Process  [API 1+]
- exec(String, String[], File): Process  [API 1+]
- exec(String[]): Process  [API 1+]
- exec(String[], String[]): Process  [API 1+]
- exec(String[], String[], File): Process  [API 1+]
+ exit(int): void  [API 1+]
+ freeMemory(): long  [API 1+]
+ gc(): void  [API 1+]
+ getLocalizedInputStream(InputStream): InputStream  [API 1-33, removed in 34, deprecated in 15]
+ getLocalizedOutputStream(OutputStream): OutputStream  [API 1-33, removed in 34, deprecated in 15]
+ getRuntime(): Runtime [static]  [API 1+]
+ halt(int): void  [API 1+]
- load(String): void  [API 1+]
+ loadLibrary(String): void  [API 1+]
+ maxMemory(): long  [API 1+]
+ removeShutdownHook(Thread): boolean  [API 1+]
+ runFinalization(): void  [API 1+]
- runFinalizersOnExit(boolean): void [static]  [API 1+, deprecated in 15]
+ totalMemory(): long  [API 1+]
+ traceInstructions(boolean): void  [API 1+, deprecated in 34]
+ traceMethodCalls(boolean): void  [API 1+, deprecated in 34]
```

## RuntimeException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 1 -)

```
+ RuntimeException()  [API 1+]
+ RuntimeException(String)  [API 1+]
+ RuntimeException(String, Throwable)  [API 1+]
- RuntimeException(String, Throwable, boolean, boolean)  [API 24+]
+ RuntimeException(Throwable)  [API 1+]
```

## RuntimePermission — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- RuntimePermission(String)  [API 1+]
- RuntimePermission(String, String)  [API 1+]
```

## SafeVarargs — TeaVM class library; API 19+ (0 +, 0 *, 0 ~, 0 -)

## SecurityException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ SecurityException()  [API 1+]
+ SecurityException(String)  [API 1+]
+ SecurityException(String, Throwable)  [API 1+]
+ SecurityException(Throwable)  [API 1+]
```

## SecurityManager — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 41 -)

```
+ SecurityManager()  [API 1+]
- checkAccept(String, int): void  [API 1+]
- checkAccess(Thread): void  [API 1+]
- checkAccess(ThreadGroup): void  [API 1+]
- checkAwtEventQueueAccess(): void  [API 1+]
- checkConnect(String, int): void  [API 1+]
- checkConnect(String, int, Object): void  [API 1+]
- checkCreateClassLoader(): void  [API 1+]
- checkDelete(String): void  [API 1+]
- checkExec(String): void  [API 1+]
- checkExit(int): void  [API 1+]
- checkLink(String): void  [API 1+]
- checkListen(int): void  [API 1+]
- checkMemberAccess(Class, int): void  [API 1+, deprecated in 33]
- checkMulticast(InetAddress): void  [API 1+]
- checkMulticast(InetAddress, byte): void  [API 1+, deprecated in 15]
- checkPackageAccess(String): void  [API 1+]
- checkPackageDefinition(String): void  [API 1+]
- checkPermission(Permission): void  [API 1+]
- checkPermission(Permission, Object): void  [API 1+]
- checkPrintJobAccess(): void  [API 1+]
- checkPropertiesAccess(): void  [API 1+]
- checkPropertyAccess(String): void  [API 1+]
- checkRead(FileDescriptor): void  [API 1+]
- checkRead(String): void  [API 1+]
- checkRead(String, Object): void  [API 1+]
- checkSecurityAccess(String): void  [API 1+]
- checkSetFactory(): void  [API 1+]
- checkSystemClipboardAccess(): void  [API 1+]
- checkTopLevelWindow(Object): boolean  [API 1+, deprecated in 33]
- checkWrite(FileDescriptor): void  [API 1+]
- checkWrite(String): void  [API 1+]
- classDepth(String): int  [API 1+, deprecated in 15]
- classLoaderDepth(): int  [API 1+, deprecated in 15]
- currentClassLoader(): ClassLoader  [API 1+, deprecated in 15]
- currentLoadedClass(): Class  [API 1+, deprecated in 15]
- getClassContext(): Class[]  [API 1+]
- getInCheck(): boolean  [API 1+, deprecated in 15]
- getSecurityContext(): Object  [API 1+]
- getThreadGroup(): ThreadGroup  [API 1+]
+ inCheck [field]  [API 1+, deprecated in 15]
- inClass(String): boolean  [API 1+, deprecated in 15]
- inClassLoader(): boolean  [API 1+, deprecated in 15]
```

## Short — TeaVM class library; API 1+ (25 +, 0 *, 0 ~, 0 -)

```
+ Short(String)  [API 1+, deprecated in 31]
+ Short(short)  [API 1+, deprecated in 31]
+ TYPE [field]  [API 1+]
+ byteValue(): byte  [API 1+]
+ compare(short, short): int [static]  [API 19+]
+ compareTo(Short): int  [API 1+]
+ compareUnsigned(short, short): int [static]  [API 31+]
+ decode(String): Short [static]  [API 1+]
+ doubleValue(): double  [API 1+]
+ equals(Object): boolean  [API 1+]
+ floatValue(): float  [API 1+]
+ hashCode(): int  [API 1+]
+ hashCode(short): int [static]  [API 24+]
+ intValue(): int  [API 1+]
+ longValue(): long  [API 1+]
+ parseShort(String): short [static]  [API 1+]
+ parseShort(String, int): short [static]  [API 1+]
+ reverseBytes(short): short [static]  [API 1+]
+ shortValue(): short  [API 1+]
+ toString(): String  [API 1+]
+ toString(short): String [static]  [API 1+]
+ toUnsignedInt(short): int [static]  [API 26+]
+ toUnsignedLong(short): long [static]  [API 26+]
+ valueOf(String): Short [static]  [API 1+]
+ valueOf(String, int): Short [static]  [API 1+]
+ valueOf(short): Short [static]  [API 1+]
```

## StackOverflowError — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ StackOverflowError()  [API 1+]
+ StackOverflowError(String)  [API 1+]
```

## StackTraceElement — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ StackTraceElement(String, String, String, int)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getClassName(): String  [API 1+]
+ getFileName(): String  [API 1+]
+ getLineNumber(): int  [API 1+]
+ getMethodName(): String  [API 1+]
+ hashCode(): int  [API 1+]
+ isNativeMethod(): boolean  [API 1+]
+ toString(): String  [API 1+]
```

## StackWalker — declaration; API 34+ (0 +, 0 *, 0 ~, 7 -)

```
- forEach(Consumer): void  [API 34+]
- getCallerClass(): Class  [API 34+]
- getInstance(): StackWalker [static]  [API 34+]
- getInstance(Set): StackWalker [static]  [API 34+]
- getInstance(Set, int): StackWalker [static]  [API 34+]
- getInstance(StackWalker.Option): StackWalker [static]  [API 34+]
- walk(Function): Object  [API 34+]
```

## StackWalker.Option — declaration; API 34+ (2 +, 0 *, 0 ~, 0 -)

```
+ RETAIN_CLASS_REFERENCE [field]  [API 34+]
+ SHOW_HIDDEN_FRAMES [field]  [API 34+]
+ SHOW_REFLECT_FRAMES [field]  [API 34+]
+ valueOf(String): StackWalker.Option [static]  [API 34+]
+ values(): StackWalker.Option[] [static]  [API 34+]
```

## StackWalker.StackFrame — declaration; API 34+ (8 +, 0 *, 0 ~, 2 -)

```
+ getByteCodeIndex(): int  [API 34+]
+ getClassName(): String  [API 34+]
+ getDeclaringClass(): Class  [API 34+]
- getDescriptor(): String  [API 34+]
+ getFileName(): String  [API 34+]
+ getLineNumber(): int  [API 34+]
+ getMethodName(): String  [API 34+]
- getMethodType(): MethodType  [API 34+]
+ isNativeMethod(): boolean  [API 34+]
+ toStackTraceElement(): StackTraceElement  [API 34+]
```

## StrictMath — TeaVM class library; API 1+ (99 +, 0 *, 0 ~, 0 -)

```
+ IEEEremainder(double, double): double [static]  [API 1+]
+ abs(double): double [static]  [API 1+]
+ abs(float): float [static]  [API 1+]
+ abs(int): int [static]  [API 1+]
+ abs(long): long [static]  [API 1+]
+ absExact(int): int [static]  [API 34+]
+ absExact(long): long [static]  [API 34+]
+ acos(double): double [static]  [API 1+]
+ addExact(int, int): int [static]  [API 24+]
+ addExact(long, long): long [static]  [API 24+]
+ asin(double): double [static]  [API 1+]
+ atan(double): double [static]  [API 1+]
+ atan2(double, double): double [static]  [API 1+]
+ cbrt(double): double [static]  [API 1+]
+ ceil(double): double [static]  [API 1+]
+ ceilDiv(int, int): int [static]  [API 35+]
+ ceilDiv(long, int): long [static]  [API 35+]
+ ceilDiv(long, long): long [static]  [API 35+]
+ ceilDivExact(int, int): int [static]  [API 35+]
+ ceilDivExact(long, long): long [static]  [API 35+]
+ ceilMod(int, int): int [static]  [API 35+]
+ ceilMod(long, int): int [static]  [API 35+]
+ ceilMod(long, long): long [static]  [API 35+]
+ clamp(double, double, double): double [static]  [API 35+]
+ clamp(float, float, float): float [static]  [API 35+]
+ clamp(long, int, int): int [static]  [API 35+]
+ clamp(long, long, long): long [static]  [API 35+]
+ copySign(double, double): double [static]  [API 9+]
+ copySign(float, float): float [static]  [API 9+]
+ cos(double): double [static]  [API 1+]
+ cosh(double): double [static]  [API 1+]
+ decrementExact(int): int [static]  [API 34+]
+ decrementExact(long): long [static]  [API 34+]
+ divideExact(int, int): int [static]  [API 35+]
+ divideExact(long, long): long [static]  [API 35+]
+ exp(double): double [static]  [API 1+]
+ expm1(double): double [static]  [API 1+]
+ floor(double): double [static]  [API 1+]
+ floorDiv(int, int): int [static]  [API 24+]
+ floorDiv(long, int): long [static]  [API 31+]
+ floorDiv(long, long): long [static]  [API 24+]
+ floorDivExact(int, int): int [static]  [API 35+]
+ floorDivExact(long, long): long [static]  [API 35+]
+ floorMod(int, int): int [static]  [API 24+]
+ floorMod(long, int): int [static]  [API 31+]
+ floorMod(long, long): long [static]  [API 24+]
+ fma(double, double, double): double [static]  [API 33+]
+ fma(float, float, float): float [static]  [API 33+]
+ getExponent(double): int [static]  [API 9+]
+ getExponent(float): int [static]  [API 9+]
+ hypot(double, double): double [static]  [API 1+]
+ incrementExact(int): int [static]  [API 34+]
+ incrementExact(long): long [static]  [API 34+]
+ log(double): double [static]  [API 1+]
+ log10(double): double [static]  [API 1+]
+ log1p(double): double [static]  [API 1+]
+ max(double, double): double [static]  [API 1+]
+ max(float, float): float [static]  [API 1+]
+ max(int, int): int [static]  [API 1+]
+ max(long, long): long [static]  [API 1+]
+ min(double, double): double [static]  [API 1+]
+ min(float, float): float [static]  [API 1+]
+ min(int, int): int [static]  [API 1+]
+ min(long, long): long [static]  [API 1+]
+ multiplyExact(int, int): int [static]  [API 24+]
+ multiplyExact(long, int): long [static]  [API 31+]
+ multiplyExact(long, long): long [static]  [API 24+]
+ multiplyFull(int, int): long [static]  [API 31+]
+ multiplyHigh(long, long): long [static]  [API 31+]
+ negateExact(int): int [static]  [API 34+]
+ negateExact(long): long [static]  [API 34+]
+ nextAfter(double, double): double [static]  [API 9+]
+ nextAfter(float, double): float [static]  [API 9+]
+ nextDown(double): double [static]  [API 24+]
+ nextDown(float): float [static]  [API 24+]
+ nextUp(double): double [static]  [API 9+]
+ nextUp(float): float [static]  [API 9+]
+ pow(double, double): double [static]  [API 1+]
+ random(): double [static]  [API 1+]
+ rint(double): double [static]  [API 1+]
+ round(double): long [static]  [API 1+]
+ round(float): int [static]  [API 1+]
+ scalb(double, int): double [static]  [API 9+]
+ scalb(float, int): float [static]  [API 9+]
+ signum(double): double [static]  [API 1+]
+ signum(float): float [static]  [API 1+]
+ sin(double): double [static]  [API 1+]
+ sinh(double): double [static]  [API 1+]
+ sqrt(double): double [static]  [API 1+]
+ subtractExact(int, int): int [static]  [API 24+]
+ subtractExact(long, long): long [static]  [API 24+]
+ tan(double): double [static]  [API 1+]
+ tanh(double): double [static]  [API 1+]
+ toDegrees(double): double [static]  [API 1+]
+ toIntExact(long): int [static]  [API 24+]
+ toRadians(double): double [static]  [API 1+]
+ ulp(double): double [static]  [API 1+]
+ ulp(float): float [static]  [API 1+]
+ unsignedMultiplyHigh(long, long): long [static]  [API 35+]
```

## String — TeaVM class library; API 1+ (93 +, 0 *, 0 ~, 2 -)

```
+ CASE_INSENSITIVE_ORDER [field]  [API 1+]
+ String()  [API 1+]
+ String(String)  [API 1+]
+ String(StringBuffer)  [API 1+]
+ String(StringBuilder)  [API 1+]
+ String(byte[])  [API 1+]
+ String(byte[], Charset)  [API 9+]
+ String(byte[], String)  [API 1+]
- String(byte[], int)  [API 1+, deprecated in 15]
+ String(byte[], int, int)  [API 1+]
+ String(byte[], int, int, Charset)  [API 9+]
+ String(byte[], int, int, String)  [API 1+]
- String(byte[], int, int, int)  [API 1+, deprecated in 15]
+ String(char[])  [API 1+]
+ String(char[], int, int)  [API 1+]
+ String(int[], int, int)  [API 1+]
+ charAt(int): char  [API 1+]
+ chars(): IntStream  [API 1+]
+ codePointAt(int): int  [API 1+]
+ codePointBefore(int): int  [API 1+]
+ codePointCount(int, int): int  [API 1+]
+ codePoints(): IntStream  [API 1+]
+ compareTo(String): int  [API 1+]
+ compareToIgnoreCase(String): int  [API 1+]
+ concat(String): String  [API 1+]
+ contains(CharSequence): boolean  [API 1+]
+ contentEquals(CharSequence): boolean  [API 1+]
+ contentEquals(StringBuffer): boolean  [API 1+]
+ copyValueOf(char[]): String [static]  [API 1+]
+ copyValueOf(char[], int, int): String [static]  [API 1+]
+ endsWith(String): boolean  [API 1+]
+ equals(Object): boolean  [API 1+]
+ equalsIgnoreCase(String): boolean  [API 1+]
+ format(Locale, String, Object[]): String [static]  [API 1+]
+ format(String, Object[]): String [static]  [API 1+]
+ formatted(Object[]): String  [API 34+]
+ getBytes(): byte[]  [API 1+]
+ getBytes(Charset): byte[]  [API 9+]
+ getBytes(String): byte[]  [API 1+]
+ getBytes(int, int, byte[], int): void  [API 1+, deprecated in 15]
+ getChars(int, int, char[], int): void  [API 1+]
+ hashCode(): int  [API 1+]
+ indent(int): String  [API 34+]
+ indexOf(String): int  [API 1+]
+ indexOf(String, int): int  [API 1+]
+ indexOf(int): int  [API 1+]
+ indexOf(int, int): int  [API 1+]
+ intern(): String  [API 1+]
+ isBlank(): boolean  [API 33+]
+ isEmpty(): boolean  [API 9+]
+ join(CharSequence, CharSequence[]): String [static]  [API 26+]
+ join(CharSequence, Iterable): String [static]  [API 26+]
+ lastIndexOf(String): int  [API 1+]
+ lastIndexOf(String, int): int  [API 1+]
+ lastIndexOf(int): int  [API 1+]
+ lastIndexOf(int, int): int  [API 1+]
+ length(): int  [API 1+]
+ lines(): Stream  [API 33+]
+ matches(String): boolean  [API 1+]
+ offsetByCodePoints(int, int): int  [API 1+]
+ regionMatches(boolean, int, String, int, int): boolean  [API 1+]
+ regionMatches(int, String, int, int): boolean  [API 1+]
+ repeat(int): String  [API 33+]
+ replace(CharSequence, CharSequence): String  [API 1+]
+ replace(char, char): String  [API 1+]
+ replaceAll(String, String): String  [API 1+]
+ replaceFirst(String, String): String  [API 1+]
+ split(String): String[]  [API 1+]
+ split(String, int): String[]  [API 1+]
+ startsWith(String): boolean  [API 1+]
+ startsWith(String, int): boolean  [API 1+]
+ strip(): String  [API 33+]
+ stripIndent(): String  [API 34+]
+ stripLeading(): String  [API 33+]
+ stripTrailing(): String  [API 33+]
+ subSequence(int, int): CharSequence  [API 1+]
+ substring(int): String  [API 1+]
+ substring(int, int): String  [API 1+]
+ toCharArray(): char[]  [API 1+]
+ toLowerCase(): String  [API 1+]
+ toLowerCase(Locale): String  [API 1+]
+ toString(): String  [API 1+]
+ toUpperCase(): String  [API 1+]
+ toUpperCase(Locale): String  [API 1+]
+ transform(Function): Object  [API 34+]
+ translateEscapes(): String  [API 34+]
+ trim(): String  [API 1+]
+ valueOf(Object): String [static]  [API 1+]
+ valueOf(boolean): String [static]  [API 1+]
+ valueOf(char): String [static]  [API 1+]
+ valueOf(char[]): String [static]  [API 1+]
+ valueOf(char[], int, int): String [static]  [API 1+]
+ valueOf(double): String [static]  [API 1+]
+ valueOf(float): String [static]  [API 1+]
+ valueOf(int): String [static]  [API 1+]
+ valueOf(long): String [static]  [API 1+]
```

## StringBuffer — TeaVM class library; API 1+ (57 +, 0 *, 0 ~, 0 -)

```
+ StringBuffer()  [API 1+]
+ StringBuffer(CharSequence)  [API 1+]
+ StringBuffer(String)  [API 1+]
+ StringBuffer(int)  [API 1+]
+ append(CharSequence): StringBuffer  [API 1+]
+ append(CharSequence, int, int): StringBuffer  [API 1+]
+ append(Object): StringBuffer  [API 1+]
+ append(String): StringBuffer  [API 1+]
+ append(StringBuffer): StringBuffer  [API 1+]
+ append(boolean): StringBuffer  [API 1+]
+ append(char): StringBuffer  [API 1+]
+ append(char[]): StringBuffer  [API 1+]
+ append(char[], int, int): StringBuffer  [API 1+]
+ append(double): StringBuffer  [API 1+]
+ append(float): StringBuffer  [API 1+]
+ append(int): StringBuffer  [API 1+]
+ append(long): StringBuffer  [API 1+]
+ appendCodePoint(int): StringBuffer  [API 1+]
+ capacity(): int  [API 1+]
+ charAt(int): char  [API 1+]
+ chars(): IntStream  [API 1+]
+ codePointAt(int): int  [API 1+]
+ codePointBefore(int): int  [API 1+]
+ codePointCount(int, int): int  [API 1+]
+ codePoints(): IntStream  [API 1+]
+ compareTo(StringBuffer): int  [API 34+]
+ delete(int, int): StringBuffer  [API 1+]
+ deleteCharAt(int): StringBuffer  [API 1+]
+ ensureCapacity(int): void  [API 1+]
+ getChars(int, int, char[], int): void  [API 1+]
+ indexOf(String): int  [API 1+]
+ indexOf(String, int): int  [API 1+]
+ insert(int, CharSequence): StringBuffer  [API 1+]
+ insert(int, CharSequence, int, int): StringBuffer  [API 1+]
+ insert(int, Object): StringBuffer  [API 1+]
+ insert(int, String): StringBuffer  [API 1+]
+ insert(int, boolean): StringBuffer  [API 1+]
+ insert(int, char): StringBuffer  [API 1+]
+ insert(int, char[]): StringBuffer  [API 1+]
+ insert(int, char[], int, int): StringBuffer  [API 1+]
+ insert(int, double): StringBuffer  [API 1+]
+ insert(int, float): StringBuffer  [API 1+]
+ insert(int, int): StringBuffer  [API 1+]
+ insert(int, long): StringBuffer  [API 1+]
+ lastIndexOf(String): int  [API 1+]
+ lastIndexOf(String, int): int  [API 1+]
+ length(): int  [API 1+]
+ offsetByCodePoints(int, int): int  [API 1+]
+ replace(int, int, String): StringBuffer  [API 1+]
+ reverse(): StringBuffer  [API 1+]
+ setCharAt(int, char): void  [API 1+]
+ setLength(int): void  [API 1+]
+ subSequence(int, int): CharSequence  [API 1+]
+ substring(int): String  [API 1+]
+ substring(int, int): String  [API 1+]
+ toString(): String  [API 1+]
+ trimToSize(): void  [API 1+]
```

## StringBuilder — TeaVM class library; API 1+ (57 +, 0 *, 0 ~, 0 -)

```
+ StringBuilder()  [API 1+]
+ StringBuilder(CharSequence)  [API 1+]
+ StringBuilder(String)  [API 1+]
+ StringBuilder(int)  [API 1+]
+ append(CharSequence): StringBuilder  [API 1+]
+ append(CharSequence, int, int): StringBuilder  [API 1+]
+ append(Object): StringBuilder  [API 1+]
+ append(String): StringBuilder  [API 1+]
+ append(StringBuffer): StringBuilder  [API 1+]
+ append(boolean): StringBuilder  [API 1+]
+ append(char): StringBuilder  [API 1+]
+ append(char[]): StringBuilder  [API 1+]
+ append(char[], int, int): StringBuilder  [API 1+]
+ append(double): StringBuilder  [API 1+]
+ append(float): StringBuilder  [API 1+]
+ append(int): StringBuilder  [API 1+]
+ append(long): StringBuilder  [API 1+]
+ appendCodePoint(int): StringBuilder  [API 1+]
+ capacity(): int  [API 1+]
+ charAt(int): char  [API 1+]
+ chars(): IntStream  [API 1+]
+ codePointAt(int): int  [API 1+]
+ codePointBefore(int): int  [API 1+]
+ codePointCount(int, int): int  [API 1+]
+ codePoints(): IntStream  [API 1+]
+ compareTo(StringBuilder): int  [API 34+]
+ delete(int, int): StringBuilder  [API 1+]
+ deleteCharAt(int): StringBuilder  [API 1+]
+ ensureCapacity(int): void  [API 1+]
+ getChars(int, int, char[], int): void  [API 1+]
+ indexOf(String): int  [API 1+]
+ indexOf(String, int): int  [API 1+]
+ insert(int, CharSequence): StringBuilder  [API 1+]
+ insert(int, CharSequence, int, int): StringBuilder  [API 1+]
+ insert(int, Object): StringBuilder  [API 1+]
+ insert(int, String): StringBuilder  [API 1+]
+ insert(int, boolean): StringBuilder  [API 1+]
+ insert(int, char): StringBuilder  [API 1+]
+ insert(int, char[]): StringBuilder  [API 1+]
+ insert(int, char[], int, int): StringBuilder  [API 1+]
+ insert(int, double): StringBuilder  [API 1+]
+ insert(int, float): StringBuilder  [API 1+]
+ insert(int, int): StringBuilder  [API 1+]
+ insert(int, long): StringBuilder  [API 1+]
+ lastIndexOf(String): int  [API 1+]
+ lastIndexOf(String, int): int  [API 1+]
+ length(): int  [API 1+]
+ offsetByCodePoints(int, int): int  [API 1+]
+ replace(int, int, String): StringBuilder  [API 1+]
+ reverse(): StringBuilder  [API 1+]
+ setCharAt(int, char): void  [API 1+]
+ setLength(int): void  [API 1+]
+ subSequence(int, int): CharSequence  [API 1+]
+ substring(int): String  [API 1+]
+ substring(int, int): String  [API 1+]
+ toString(): String  [API 1+]
+ trimToSize(): void  [API 1+]
```

## StringIndexOutOfBoundsException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ StringIndexOutOfBoundsException()  [API 1+]
+ StringIndexOutOfBoundsException(String)  [API 1+]
+ StringIndexOutOfBoundsException(int)  [API 1+]
```

## SuppressWarnings — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- value(): String[]  [API 1+]
```

## System — TeaVM class library; API 1+ (23 +, 0 *, 0 ~, 4 -)

```
+ arraycopy(Object, int, Object, int, int): void [static]  [API 1+]
+ clearProperty(String): String [static]  [API 1+]
+ console(): Console [static]  [API 9+]
+ currentTimeMillis(): long [static]  [API 1+]
+ err [field]  [API 1+]
+ exit(int): void [static]  [API 1+]
+ gc(): void [static]  [API 1+]
+ getProperties(): Properties [static]  [API 1+]
+ getProperty(String): String [static]  [API 1+]
+ getProperty(String, String): String [static]  [API 1+]
+ getSecurityManager(): SecurityManager [static]  [API 1+]
+ getenv(): Map [static]  [API 1+]
+ getenv(String): String [static]  [API 1+]
+ identityHashCode(Object): int [static]  [API 1+]
+ in [field]  [API 1+]
- inheritedChannel(): Channel [static]  [API 1+]
+ lineSeparator(): String [static]  [API 19+]
+ load(String): void [static]  [API 1+]
+ loadLibrary(String): void [static]  [API 1+]
+ mapLibraryName(String): String [static]  [API 1+]
+ nanoTime(): long [static]  [API 1+]
+ out [field]  [API 1+]
+ runFinalization(): void [static]  [API 1+]
- runFinalizersOnExit(boolean): void [static]  [API 1+, deprecated in 15]
+ setErr(PrintStream): void [static]  [API 1+]
- setIn(InputStream): void [static]  [API 1+]
+ setOut(PrintStream): void [static]  [API 1+]
+ setProperties(Properties): void [static]  [API 1+]
+ setProperty(String, String): String [static]  [API 1+]
- setSecurityManager(SecurityManager): void [static]  [API 1+]
```

## Thread — TeaVM class library; API 1+ (46 +, 0 *, 0 ~, 9 -)

```
+ Thread()  [API 1+]
+ Thread(Runnable)  [API 1+]
+ Thread(Runnable, String)  [API 1+]
+ Thread(String)  [API 1+]
- Thread(ThreadGroup, Runnable)  [API 1+]
- Thread(ThreadGroup, Runnable, String)  [API 1+]
- Thread(ThreadGroup, Runnable, String, long)  [API 1+]
- Thread(ThreadGroup, Runnable, String, long, boolean)  [API 33+]
- Thread(ThreadGroup, String)  [API 1+]
+ activeCount(): int [static]  [API 1+]
+ checkAccess(): void  [API 1+, deprecated in 36]
+ clone(): Object  [API 1+]
+ countStackFrames(): int  [API 1-35, removed in 36, deprecated in 15]
+ currentThread(): Thread [static]  [API 1+]
+ destroy(): void  [API 1-35, removed in 36, deprecated in 15]
+ dumpStack(): void [static]  [API 1+]
- enumerate(Thread[]): int [static]  [API 1+]
- getAllStackTraces(): Map [static]  [API 1+]
+ getContextClassLoader(): ClassLoader  [API 1+]
+ getDefaultUncaughtExceptionHandler(): Thread.UncaughtExceptionHandler [static]  [API 1+]
+ getId(): long  [API 1+, deprecated in 36]
+ getName(): String  [API 1+]
+ getPriority(): int  [API 1+]
+ getStackTrace(): StackTraceElement[]  [API 1+]
- getState(): Thread.State  [API 1+]
+ getThreadGroup(): ThreadGroup  [API 1+]
+ getUncaughtExceptionHandler(): Thread.UncaughtExceptionHandler  [API 1+]
+ holdsLock(Object): boolean [static]  [API 1+]
+ interrupt(): void  [API 1+]
+ interrupted(): boolean [static]  [API 1+]
+ isAlive(): boolean  [API 1+]
+ isDaemon(): boolean  [API 1+]
+ isInterrupted(): boolean  [API 1+]
+ isVirtual(): boolean  [API 36+]
+ join(): void  [API 1+]
+ join(long): void  [API 1+]
+ join(long, int): void  [API 1+]
+ onSpinWait(): void [static]  [API 33+]
+ resume(): void  [API 1-35, removed in 36, deprecated in 15]
+ run(): void  [API 1+]
- setContextClassLoader(ClassLoader): void  [API 1+]
+ setDaemon(boolean): void  [API 1+]
+ setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler): void [static]  [API 1+]
+ setName(String): void  [API 1+]
+ setPriority(int): void  [API 1+]
+ setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler): void  [API 1+]
+ sleep(long): void [static]  [API 1+]
+ sleep(long, int): void [static]  [API 1+]
+ start(): void  [API 1+]
+ stop(): void  [API 1+, deprecated in 15]
+ stop(Throwable): void  [API 1-35, removed in 36, deprecated in 15]
+ suspend(): void  [API 1-35, removed in 36, deprecated in 15]
+ threadId(): long  [API 36+]
+ toString(): String  [API 1+]
+ yield(): void [static]  [API 1+]
```

## Thread.State — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BLOCKED [field]  [API 1+]
+ NEW [field]  [API 1+]
+ RUNNABLE [field]  [API 1+]
+ TERMINATED [field]  [API 1+]
+ TIMED_WAITING [field]  [API 1+]
+ WAITING [field]  [API 1+]
+ valueOf(String): Thread.State [static]  [API 1+]
+ values(): Thread.State[] [static]  [API 1+]
```

## Thread.UncaughtExceptionHandler — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ uncaughtException(Thread, Throwable): void  [API 1+]
```

## ThreadDeath — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ThreadDeath()  [API 1+]
```

## ThreadGroup — declaration; API 1+ (0 +, 8 *, 0 ~, 18 -)

```
* ThreadGroup(String)  [API 1+]
* ThreadGroup(ThreadGroup, String)  [API 1+]
- activeCount(): int  [API 1+]
- activeGroupCount(): int  [API 1+]
- allowThreadSuspension(boolean): boolean  [API 1+, deprecated in 15]
- checkAccess(): void  [API 1+]
- destroy(): void  [API 1+]
- enumerate(ThreadGroup[]): int  [API 1+]
- enumerate(ThreadGroup[], boolean): int  [API 1+]
- enumerate(Thread[]): int  [API 1+]
- enumerate(Thread[], boolean): int  [API 1+]
* getMaxPriority(): int  [API 1+]
* getName(): String  [API 1+]
* getParent(): ThreadGroup  [API 1+]
- interrupt(): void  [API 1+]
* isDaemon(): boolean  [API 1+]
- isDestroyed(): boolean  [API 1+]
- list(): void  [API 1+]
- parentOf(ThreadGroup): boolean  [API 1+]
- resume(): void  [API 1+, deprecated in 15]
* setDaemon(boolean): void  [API 1+]
* setMaxPriority(int): void  [API 1+]
- stop(): void  [API 1+, deprecated in 15]
- suspend(): void  [API 1+, deprecated in 15]
- toString(): String  [API 1+]
- uncaughtException(Thread, Throwable): void  [API 1+]
```

## ThreadLocal — TeaVM class library; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ ThreadLocal()  [API 1+]
+ get(): Object  [API 1+]
+ initialValue(): Object  [API 1+]
+ remove(): void  [API 1+]
+ set(Object): void  [API 1+]
+ withInitial(Supplier): ThreadLocal [static]  [API 26+]
```

## Throwable — TeaVM class library; API 1+ (18 +, 0 *, 0 ~, 0 -)

```
+ Throwable()  [API 1+]
+ Throwable(String)  [API 1+]
+ Throwable(String, Throwable)  [API 1+]
+ Throwable(String, Throwable, boolean, boolean)  [API 19+]
+ Throwable(Throwable)  [API 1+]
+ addSuppressed(Throwable): void  [API 19+]
+ fillInStackTrace(): Throwable  [API 1+]
+ getCause(): Throwable  [API 1+]
+ getLocalizedMessage(): String  [API 1+]
+ getMessage(): String  [API 1+]
+ getStackTrace(): StackTraceElement[]  [API 1+]
+ getSuppressed(): Throwable[]  [API 19+]
+ initCause(Throwable): Throwable  [API 1+]
+ printStackTrace(): void  [API 1+]
+ printStackTrace(PrintStream): void  [API 1+]
+ printStackTrace(PrintWriter): void  [API 1+]
+ setStackTrace(StackTraceElement[]): void  [API 1+]
+ toString(): String  [API 1+]
```

## TypeNotPresentException — declaration; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
+ TypeNotPresentException(String, Throwable)  [API 1+]
- typeName(): String  [API 1+]
```

## UnknownError — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnknownError()  [API 1+]
+ UnknownError(String)  [API 1+]
```

## UnsatisfiedLinkError — framework (a2w.jdk.UnsatisfiedLinkError); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnsatisfiedLinkError()  [API 1+]
+ UnsatisfiedLinkError(String)  [API 1+]
```

## UnsupportedClassVersionError — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnsupportedClassVersionError()  [API 1+]
+ UnsupportedClassVersionError(String)  [API 1+]
```

## UnsupportedOperationException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ UnsupportedOperationException()  [API 1+]
+ UnsupportedOperationException(String)  [API 1+]
+ UnsupportedOperationException(String, Throwable)  [API 1+]
+ UnsupportedOperationException(Throwable)  [API 1+]
```

## VerifyError — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ VerifyError()  [API 1+]
+ VerifyError(String)  [API 1+]
```

## VirtualMachineError — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ VirtualMachineError()  [API 1+]
+ VirtualMachineError(String)  [API 1+]
+ VirtualMachineError(String, Throwable)  [API 24+]
+ VirtualMachineError(Throwable)  [API 24+]
```

## Void — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

```
+ TYPE [field]  [API 1+]
```


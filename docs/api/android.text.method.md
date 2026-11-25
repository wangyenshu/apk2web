# android.text.method

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ArrowKeyMovementMethod — declaration; API 1+ (0 +, 0 *, 0 ~, 21 -)

```
- ArrowKeyMovementMethod()  [API 1+]
- bottom(TextView, Spannable): boolean  [API 1+]
- canSelectArbitrarily(): boolean  [API 1+]
- down(TextView, Spannable): boolean  [API 1+]
- end(TextView, Spannable): boolean  [API 1+]
- getInstance(): MovementMethod [static]  [API 1+]
- handleMovementKey(TextView, Spannable, int, int, KeyEvent): boolean  [API 1+]
- home(TextView, Spannable): boolean  [API 1+]
- initialize(TextView, Spannable): void  [API 1+]
- left(TextView, Spannable): boolean  [API 1+]
- lineEnd(TextView, Spannable): boolean  [API 1+]
- lineStart(TextView, Spannable): boolean  [API 1+]
- nextParagraph(TextView, Spannable): boolean  [API 1+]
- onTakeFocus(TextView, Spannable, int): void  [API 1+]
- onTouchEvent(TextView, Spannable, MotionEvent): boolean  [API 1+]
- pageDown(TextView, Spannable): boolean  [API 1+]
- pageUp(TextView, Spannable): boolean  [API 1+]
- previousParagraph(TextView, Spannable): boolean  [API 1+]
- right(TextView, Spannable): boolean  [API 1+]
- top(TextView, Spannable): boolean  [API 1+]
- up(TextView, Spannable): boolean  [API 1+]
```

## BaseKeyListener — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ BaseKeyListener()  [API 1+]
+ backspace(View, Editable, int, KeyEvent): boolean  [API 1+]
+ forwardDelete(View, Editable, int, KeyEvent): boolean  [API 11+]
+ onKeyDown(View, Editable, int, KeyEvent): boolean  [API 1+]
+ onKeyOther(View, Editable, KeyEvent): boolean  [API 1+]
```

## BaseMovementMethod — declaration; API 11+ (0 +, 0 *, 0 ~, 26 -)

```
- BaseMovementMethod()  [API 11+]
- bottom(TextView, Spannable): boolean  [API 11+]
- canSelectArbitrarily(): boolean  [API 11+]
- down(TextView, Spannable): boolean  [API 11+]
- end(TextView, Spannable): boolean  [API 11+]
- getMovementMetaState(Spannable, KeyEvent): int  [API 11+]
- handleMovementKey(TextView, Spannable, int, int, KeyEvent): boolean  [API 11+]
- home(TextView, Spannable): boolean  [API 11+]
- initialize(TextView, Spannable): void  [API 11+]
- left(TextView, Spannable): boolean  [API 11+]
- lineEnd(TextView, Spannable): boolean  [API 11+]
- lineStart(TextView, Spannable): boolean  [API 11+]
- nextParagraph(TextView, Spannable): boolean  [API 34+]
- onGenericMotionEvent(TextView, Spannable, MotionEvent): boolean  [API 11+]
- onKeyDown(TextView, Spannable, int, KeyEvent): boolean  [API 11+]
- onKeyOther(TextView, Spannable, KeyEvent): boolean  [API 11+]
- onKeyUp(TextView, Spannable, int, KeyEvent): boolean  [API 11+]
- onTakeFocus(TextView, Spannable, int): void  [API 11+]
- onTouchEvent(TextView, Spannable, MotionEvent): boolean  [API 11+]
- onTrackballEvent(TextView, Spannable, MotionEvent): boolean  [API 11+]
- pageDown(TextView, Spannable): boolean  [API 11+]
- pageUp(TextView, Spannable): boolean  [API 11+]
- previousParagraph(TextView, Spannable): boolean  [API 34+]
- right(TextView, Spannable): boolean  [API 11+]
- top(TextView, Spannable): boolean  [API 11+]
- up(TextView, Spannable): boolean  [API 11+]
```

## CharacterPickerDialog — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- CharacterPickerDialog(Context, View, Editable, String, boolean)  [API 1+]
- onClick(View): void  [API 1+]
- onCreate(Bundle): void  [API 1+]
- onItemClick(AdapterView, View, int, long): void  [API 1+]
```

## DateKeyListener — framework; API 1+ (5 +, 0 *, 1 ~, 0 -)

```
+ CHARACTERS [field]  [API 1+, deprecated in 26]
+ DateKeyListener()  [API 1+, deprecated in 26]
+ DateKeyListener(Locale)  [API 26+]
+ getAcceptedChars(): char[]  [API 1+]
~ getInputType(): int  [API 1+]
+ getInstance(): DateKeyListener [static]  [API 1+, deprecated in 26]
+ getInstance(Locale): DateKeyListener [static]  [API 26+]
```

## DateTimeKeyListener — framework; API 1+ (5 +, 0 *, 1 ~, 0 -)

```
+ CHARACTERS [field]  [API 1+, deprecated in 26]
+ DateTimeKeyListener()  [API 1+, deprecated in 26]
+ DateTimeKeyListener(Locale)  [API 26+]
+ getAcceptedChars(): char[]  [API 1+]
~ getInputType(): int  [API 1+]
+ getInstance(): DateTimeKeyListener [static]  [API 1+, deprecated in 26]
+ getInstance(Locale): DateTimeKeyListener [static]  [API 26+]
```

## DialerKeyListener — framework; API 1+ (3 +, 0 *, 1 ~, 1 -)

```
+ CHARACTERS [field]  [API 1+]
+ DialerKeyListener()  [API 1+]
+ getAcceptedChars(): char[]  [API 1+]
~ getInputType(): int  [API 1+]
+ getInstance(): DialerKeyListener [static]  [API 1+]
- lookup(KeyEvent, Spannable): int  [API 1+]
```

## DigitsKeyListener — framework; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ DigitsKeyListener()  [API 1+, deprecated in 26]
+ DigitsKeyListener(Locale)  [API 26+]
+ DigitsKeyListener(Locale, boolean, boolean)  [API 26+]
+ DigitsKeyListener(boolean, boolean)  [API 1+, deprecated in 26]
+ filter(CharSequence, int, int, Spanned, int, int): CharSequence  [API 1+]
+ getAcceptedChars(): char[]  [API 1+]
+ getInputType(): int  [API 1+]
+ getInstance(): DigitsKeyListener [static]  [API 1+, deprecated in 26]
+ getInstance(Locale): DigitsKeyListener [static]  [API 26+]
+ getInstance(Locale, boolean, boolean): DigitsKeyListener [static]  [API 26+]
+ getInstance(String): DigitsKeyListener [static]  [API 1+]
+ getInstance(boolean, boolean): DigitsKeyListener [static]  [API 1+, deprecated in 26]
```

## HideReturnsTransformationMethod — framework; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
+ HideReturnsTransformationMethod()  [API 1+]
+ getInstance(): HideReturnsTransformationMethod [static]  [API 1+]
- getOriginal(): char[]  [API 1+]
- getReplacement(): char[]  [API 1+]
```

## KeyListener — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ clearMetaKeyState(View, Editable, int): void  [API 3+]
+ getInputType(): int  [API 3+]
+ onKeyDown(View, Editable, int, KeyEvent): boolean  [API 1+]
+ onKeyOther(View, Editable, KeyEvent): boolean  [API 3+]
+ onKeyUp(View, Editable, int, KeyEvent): boolean  [API 1+]
```

## LinkMovementMethod — framework; API 1+ (2 +, 0 *, 0 ~, 9 -)

```
+ LinkMovementMethod()  [API 1+]
- canSelectArbitrarily(): boolean  [API 1+]
- down(TextView, Spannable): boolean  [API 1+]
+ getInstance(): MovementMethod [static]  [API 1+]
- handleMovementKey(TextView, Spannable, int, int, KeyEvent): boolean  [API 1+]
- initialize(TextView, Spannable): void  [API 1+]
- left(TextView, Spannable): boolean  [API 1+]
- onTakeFocus(TextView, Spannable, int): void  [API 1+]
- onTouchEvent(TextView, Spannable, MotionEvent): boolean  [API 1+]
- right(TextView, Spannable): boolean  [API 1+]
- up(TextView, Spannable): boolean  [API 1+]
```

## MetaKeyKeyListener — framework; API 1+ (8 +, 0 *, 11 ~, 2 -)

```
+ MetaKeyKeyListener()  [API 1+]
~ adjustMetaAfterKeypress(Spannable): void [static]  [API 1+]
+ adjustMetaAfterKeypress(long): long [static]  [API 3+]
~ clearMetaKeyState(Editable, int): void [static]  [API 3+]
~ clearMetaKeyState(View, Editable, int): void  [API 3+]
+ clearMetaKeyState(long, int): long  [API 3+]
~ getMetaState(CharSequence): int [static]  [API 1+]
- getMetaState(CharSequence, KeyEvent): int [static]  [API 19+]
~ getMetaState(CharSequence, int): int [static]  [API 1+]
- getMetaState(CharSequence, int, KeyEvent): int [static]  [API 19+]
~ getMetaState(long): int [static]  [API 3+]
~ getMetaState(long, int): int [static]  [API 3+]
+ handleKeyDown(long, int, KeyEvent): long [static]  [API 3+]
+ handleKeyUp(long, int, KeyEvent): long [static]  [API 3+]
~ isMetaTracker(CharSequence, Object): boolean [static]  [API 1+]
~ isSelectingMetaTracker(CharSequence, Object): boolean [static]  [API 3+]
+ onKeyDown(View, Editable, int, KeyEvent): boolean  [API 1+]
+ onKeyUp(View, Editable, int, KeyEvent): boolean  [API 1+]
~ resetLockedMeta(Spannable): void [static]  [API 1+]
+ resetLockedMeta(long): long [static]  [API 3+]
~ resetMetaState(Spannable): void [static]  [API 1+]
```

## MovementMethod — framework; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- canSelectArbitrarily(): boolean  [API 1+]
- initialize(TextView, Spannable): void  [API 1+]
- onGenericMotionEvent(TextView, Spannable, MotionEvent): boolean  [API 12+]
- onKeyDown(TextView, Spannable, int, KeyEvent): boolean  [API 1+]
- onKeyOther(TextView, Spannable, KeyEvent): boolean  [API 3+]
- onKeyUp(TextView, Spannable, int, KeyEvent): boolean  [API 1+]
- onTakeFocus(TextView, Spannable, int): void  [API 1+]
- onTouchEvent(TextView, Spannable, MotionEvent): boolean  [API 1+]
- onTrackballEvent(TextView, Spannable, MotionEvent): boolean  [API 1+]
```

## MultiTapKeyListener — declaration; API 1+ (3 +, 0 *, 0 ~, 4 -)

```
- MultiTapKeyListener(TextKeyListener.Capitalize, boolean)  [API 1+]
- getInputType(): int  [API 1+]
- getInstance(boolean, TextKeyListener.Capitalize): MultiTapKeyListener [static]  [API 1+]
- onKeyDown(View, Editable, int, KeyEvent): boolean  [API 1+]
+ onSpanAdded(Spannable, Object, int, int): void  [API 1+]
+ onSpanChanged(Spannable, Object, int, int, int, int): void  [API 1+]
+ onSpanRemoved(Spannable, Object, int, int): void  [API 1+]
```

## NumberKeyListener — framework; API 1+ (5 +, 0 *, 0 ~, 1 -)

```
+ NumberKeyListener()  [API 1+]
+ filter(CharSequence, int, int, Spanned, int, int): CharSequence  [API 1+]
+ getAcceptedChars(): char[]  [API 1+]
- lookup(KeyEvent, Spannable): int  [API 1+]
+ ok(char[], char): boolean [static]  [API 1+]
+ onKeyDown(View, Editable, int, KeyEvent): boolean  [API 1+]
```

## PasswordTransformationMethod — framework; API 1+ (3 +, 0 *, 0 ~, 4 -)

```
+ PasswordTransformationMethod()  [API 1+]
- afterTextChanged(Editable): void  [API 1+]
- beforeTextChanged(CharSequence, int, int, int): void  [API 1+]
+ getInstance(): PasswordTransformationMethod [static]  [API 1+]
+ getTransformation(CharSequence, View): CharSequence  [API 1+]
- onFocusChanged(View, CharSequence, boolean, int, Rect): void  [API 1+]
- onTextChanged(CharSequence, int, int, int): void  [API 1+]
```

## QwertyKeyListener — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- QwertyKeyListener(TextKeyListener.Capitalize, boolean)  [API 1+]
- getInputType(): int  [API 1+]
- getInstance(boolean, TextKeyListener.Capitalize): QwertyKeyListener [static]  [API 1+]
- getInstanceForFullKeyboard(): QwertyKeyListener [static]  [API 11+]
- markAsReplaced(Spannable, int, int, String): void [static]  [API 1+]
- onKeyDown(View, Editable, int, KeyEvent): boolean  [API 1+]
```

## ReplacementTransformationMethod — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ ReplacementTransformationMethod()  [API 1+]
+ getOriginal(): char[]  [API 1+]
+ getReplacement(): char[]  [API 1+]
+ getTransformation(CharSequence, View): CharSequence  [API 1+]
+ onFocusChanged(View, CharSequence, boolean, int, Rect): void  [API 1+]
```

## ScrollingMovementMethod — framework; API 1+ (2 +, 0 *, 0 ~, 14 -)

```
+ ScrollingMovementMethod()  [API 1+]
- bottom(TextView, Spannable): boolean  [API 1+]
- down(TextView, Spannable): boolean  [API 1+]
- end(TextView, Spannable): boolean  [API 1+]
+ getInstance(): MovementMethod [static]  [API 1+]
- home(TextView, Spannable): boolean  [API 1+]
- left(TextView, Spannable): boolean  [API 1+]
- lineEnd(TextView, Spannable): boolean  [API 1+]
- lineStart(TextView, Spannable): boolean  [API 1+]
- onTakeFocus(TextView, Spannable, int): void  [API 1+]
- onTouchEvent(TextView, Spannable, MotionEvent): boolean  [API 1+]
- pageDown(TextView, Spannable): boolean  [API 1+]
- pageUp(TextView, Spannable): boolean  [API 1+]
- right(TextView, Spannable): boolean  [API 1+]
- top(TextView, Spannable): boolean  [API 1+]
- up(TextView, Spannable): boolean  [API 1+]
```

## SingleLineTransformationMethod — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ SingleLineTransformationMethod()  [API 1+]
+ getInstance(): SingleLineTransformationMethod [static]  [API 1+]
+ getOriginal(): char[]  [API 1+]
+ getReplacement(): char[]  [API 1+]
```

## TextKeyListener — framework; API 1+ (9 +, 0 *, 0 ~, 4 -)

```
+ TextKeyListener(TextKeyListener.Capitalize, boolean)  [API 1+]
+ clear(Editable): void [static]  [API 1+]
+ getInputType(): int  [API 1+]
+ getInstance(): TextKeyListener [static]  [API 1+]
+ getInstance(boolean, TextKeyListener.Capitalize): TextKeyListener [static]  [API 1+]
+ onKeyDown(View, Editable, int, KeyEvent): boolean  [API 1+]
+ onKeyOther(View, Editable, KeyEvent): boolean  [API 1+]
+ onKeyUp(View, Editable, int, KeyEvent): boolean  [API 1+]
- onSpanAdded(Spannable, Object, int, int): void  [API 1+]
- onSpanChanged(Spannable, Object, int, int, int, int): void  [API 1+]
- onSpanRemoved(Spannable, Object, int, int): void  [API 1+]
- release(): void  [API 1+]
+ shouldCap(TextKeyListener.Capitalize, CharSequence, int): boolean [static]  [API 1+]
```

## TextKeyListener.Capitalize — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CHARACTERS [field]  [API 1+]
+ NONE [field]  [API 1+]
+ SENTENCES [field]  [API 1+]
+ WORDS [field]  [API 1+]
+ valueOf(String): TextKeyListener.Capitalize [static]  [API 1+]
+ values(): TextKeyListener.Capitalize[] [static]  [API 1+]
```

## TimeKeyListener — framework; API 1+ (5 +, 0 *, 1 ~, 0 -)

```
+ CHARACTERS [field]  [API 1+, deprecated in 26]
+ TimeKeyListener()  [API 1+, deprecated in 26]
+ TimeKeyListener(Locale)  [API 26+]
+ getAcceptedChars(): char[]  [API 1+]
~ getInputType(): int  [API 1+]
+ getInstance(): TimeKeyListener [static]  [API 1+, deprecated in 26]
+ getInstance(Locale): TimeKeyListener [static]  [API 26+]
```

## Touch — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- getInitialScrollX(TextView, Spannable): int [static]  [API 3+]
- getInitialScrollY(TextView, Spannable): int [static]  [API 3+]
- onTouchEvent(TextView, Spannable, MotionEvent): boolean [static]  [API 1+]
- scrollTo(TextView, Layout, int, int): void [static]  [API 1+]
```

## TransformationMethod — framework; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
+ getTransformation(CharSequence, View): CharSequence  [API 1+]
- onFocusChanged(View, CharSequence, boolean, int, Rect): void  [API 1+]
```


# android.inputmethodservice

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractInputMethodService — declaration; API 3+ (6 +, 0 *, 0 ~, 9 -)

```
- AbstractInputMethodService()  [API 3+]
- dump(FileDescriptor, PrintWriter, String[]): void  [API 3+]
- getKeyDispatcherState(): KeyEvent.DispatcherState  [API 5+]
- getSystemService(String): Object  [API 3+]
- onBind(Intent): IBinder  [API 3+]
+ onConfigurationChanged(Configuration): void  [API 3+]
+ onCreateInputMethodInterface(): AbstractInputMethodService.AbstractInputMethodImpl  [API 3+]
+ onCreateInputMethodSessionInterface(): AbstractInputMethodService.AbstractInputMethodSessionImpl  [API 3+]
+ onDestroy(): void  [API 3+]
- onGenericMotionEvent(MotionEvent): boolean  [API 17+]
+ onLowMemory(): void  [API 3+]
- onTrackballEvent(MotionEvent): boolean  [API 3+]
+ onTrimMemory(int): void  [API 3+]
- registerComponentCallbacks(ComponentCallbacks): void  [API 3+]
- unregisterComponentCallbacks(ComponentCallbacks): void  [API 3+]
```

## AbstractInputMethodService.AbstractInputMethodImpl — declaration; API 3+ (0 +, 0 *, 0 ~, 4 -)

```
- AbstractInputMethodImpl(AbstractInputMethodService)  [API 3+]
- createSession(InputMethod.SessionCallback): void  [API 3+]
- revokeSession(InputMethodSession): void  [API 3+]
- setSessionEnabled(InputMethodSession, boolean): void  [API 3+]
```

## AbstractInputMethodService.AbstractInputMethodSessionImpl — declaration; API 3+ (0 +, 2 *, 0 ~, 6 -)

```
- AbstractInputMethodSessionImpl(AbstractInputMethodService)  [API 3+]
- dispatchGenericMotionEvent(int, MotionEvent, InputMethodSession.EventCallback): void  [API 3+]
- dispatchKeyEvent(int, KeyEvent, InputMethodSession.EventCallback): void  [API 3+]
- dispatchTrackballEvent(int, MotionEvent, InputMethodSession.EventCallback): void  [API 3+]
* isEnabled(): boolean  [API 3+]
- isRevoked(): boolean  [API 3+]
- revokeSelf(): void  [API 3+]
* setEnabled(boolean): void  [API 3+]
```

## ExtractEditText — declaration; API 3+ (0 +, 0 *, 0 ~, 15 -)

```
- ExtractEditText(Context)  [API 3+]
- ExtractEditText(Context, AttributeSet)  [API 3+]
- ExtractEditText(Context, AttributeSet, int)  [API 3+]
- ExtractEditText(Context, AttributeSet, int, int)  [API 21+]
- finishInternalChanges(): void  [API 3+]
- hasFocus(): boolean  [API 3+]
- hasVerticalScrollBar(): boolean  [API 3+]
- hasWindowFocus(): boolean  [API 3+]
- isFocused(): boolean  [API 3+]
- isInputMethodTarget(): boolean  [API 3+]
- onSelectionChanged(int, int): void  [API 3+]
- onTextContextMenuItem(int): boolean  [API 3+]
- performClick(): boolean  [API 3+]
- setExtractedText(ExtractedText): void  [API 3+]
- startInternalChanges(): void  [API 3+]
```

## InputMethodService — declaration; API 3+ (0 +, 0 *, 0 ~, 101 -)

```
- InputMethodService()  [API 3+]
- dump(FileDescriptor, PrintWriter, String[]): void  [API 3+]
- enableHardwareAcceleration(): boolean  [API 17+, deprecated in 21]
- finishConnectionlessStylusHandwriting(CharSequence): void  [API 35+]
- finishStylusHandwriting(): void  [API 33+]
- getBackDisposition(): int  [API 11+]
- getCandidatesHiddenVisibility(): int  [API 3+]
- getCurrentInputBinding(): InputBinding  [API 3+]
- getCurrentInputConnection(): InputConnection  [API 3+]
- getCurrentInputEditorInfo(): EditorInfo  [API 3+]
- getCurrentInputStarted(): boolean  [API 3+]
- getInputMethodWindowRecommendedHeight(): int  [API 21+, deprecated in 29]
- getLayoutInflater(): LayoutInflater  [API 3+]
- getMaxWidth(): int  [API 3+]
- getStylusHandwritingIdleTimeoutMax(): Duration [static]  [API 34+]
- getStylusHandwritingSessionTimeout(): Duration  [API 34+]
- getStylusHandwritingWindow(): Window  [API 33+]
- getSystemService(String): Object  [API 3+]
- getTextForImeAction(int): CharSequence  [API 3+]
- getWindow(): Dialog  [API 3+]
- hideStatusIcon(): void  [API 3+]
- hideWindow(): void  [API 3+]
- isExtractViewShown(): boolean  [API 3+]
- isFullscreenMode(): boolean  [API 3+]
- isInputViewShown(): boolean  [API 3+]
- isShowInputRequested(): boolean  [API 3+]
- onAppPrivateCommand(String, Bundle): void  [API 3+]
- onBindInput(): void  [API 3+]
- onComputeInsets(InputMethodService.Insets): void  [API 3+]
- onConfigurationChanged(Configuration): void  [API 3+]
- onConfigureWindow(Window, boolean, boolean): void  [API 3+]
- onCreate(): void  [API 3+]
- onCreateCandidatesView(): View  [API 3+]
- onCreateExtractTextView(): View  [API 3+]
- onCreateInlineSuggestionsRequest(Bundle): InlineSuggestionsRequest  [API 30+]
- onCreateInputMethodInterface(): AbstractInputMethodService.AbstractInputMethodImpl  [API 3+]
- onCreateInputMethodSessionInterface(): AbstractInputMethodService.AbstractInputMethodSessionImpl  [API 3+]
- onCreateInputView(): View  [API 3+]
- onCurrentInputMethodSubtypeChanged(InputMethodSubtype): void  [API 11+]
- onDestroy(): void  [API 3+]
- onDisplayCompletions(CompletionInfo[]): void  [API 3+]
- onEvaluateFullscreenMode(): boolean  [API 3+]
- onEvaluateInputViewShown(): boolean  [API 3+]
- onExtractTextContextMenuItem(int): boolean  [API 3+]
- onExtractedCursorMovement(int, int): void  [API 3+]
- onExtractedSelectionChanged(int, int): void  [API 3+]
- onExtractedTextClicked(): void  [API 3+]
- onExtractingInputChanged(EditorInfo): void  [API 3+]
- onFinishCandidatesView(boolean): void  [API 3+]
- onFinishInput(): void  [API 3+]
- onFinishInputView(boolean): void  [API 3+]
- onFinishStylusHandwriting(): void  [API 33+]
- onGenericMotionEvent(MotionEvent): boolean  [API 3+]
- onInitializeInterface(): void  [API 3+]
- onInlineSuggestionsResponse(InlineSuggestionsResponse): boolean  [API 30+]
- onKeyDown(int, KeyEvent): boolean  [API 3+]
- onKeyLongPress(int, KeyEvent): boolean  [API 3+]
- onKeyMultiple(int, int, KeyEvent): boolean  [API 3+]
- onKeyUp(int, KeyEvent): boolean  [API 3+]
- onPrepareStylusHandwriting(): void  [API 33+]
- onShowInputRequested(int, boolean): boolean  [API 3+]
- onStartCandidatesView(EditorInfo, boolean): void  [API 3+]
- onStartConnectionlessStylusHandwriting(int, CursorAnchorInfo): boolean  [API 35+]
- onStartInput(EditorInfo, boolean): void  [API 3+]
- onStartInputView(EditorInfo, boolean): void  [API 3+]
- onStartStylusHandwriting(): boolean  [API 33+]
- onStylusHandwritingMotionEvent(MotionEvent): void  [API 33+]
- onTrackballEvent(MotionEvent): boolean  [API 3+]
- onUnbindInput(): void  [API 3+]
- onUpdateCursor(Rect): void  [API 3+, deprecated in 21]
- onUpdateCursorAnchorInfo(CursorAnchorInfo): void  [API 21+]
- onUpdateEditorToolType(int): void  [API 34+]
- onUpdateExtractedText(int, ExtractedText): void  [API 3+]
- onUpdateExtractingViews(EditorInfo): void  [API 3+]
- onUpdateExtractingVisibility(EditorInfo): void  [API 3+]
- onUpdateSelection(int, int, int, int, int, int): void  [API 3+]
- onViewClicked(boolean): void  [API 14+, deprecated in 29]
- onWindowHidden(): void  [API 3+]
- onWindowShown(): void  [API 3+]
- requestHideSelf(int): void  [API 3+]
- requestShowSelf(int): void  [API 28+]
- sendDefaultEditorAction(boolean): boolean  [API 3+]
- sendDownUpKeyEvents(int): void  [API 3+]
- sendKeyChar(char): void  [API 3+]
- setBackDisposition(int): void  [API 11+]
- setCandidatesView(View): void  [API 3+]
- setCandidatesViewShown(boolean): void  [API 3+]
- setExtractView(View): void  [API 3+]
- setExtractViewShown(boolean): void  [API 3+]
- setInputView(View): void  [API 3+]
- setStylusHandwritingSessionTimeout(Duration): void  [API 34+]
- setTheme(int): void  [API 3+]
- shouldOfferSwitchingToNextInputMethod(): boolean  [API 28+]
- showStatusIcon(int): void  [API 3+]
- showWindow(boolean): void  [API 3+]
- switchInputMethod(String): void  [API 3+]
- switchInputMethod(String, InputMethodSubtype): void  [API 28+]
- switchToNextInputMethod(boolean): boolean  [API 28+]
- switchToPreviousInputMethod(): boolean  [API 28+]
- updateFullscreenMode(): void  [API 3+]
- updateInputViewShown(): void  [API 3+]
```

## InputMethodService.InputMethodImpl — declaration; API 3+ (0 +, 0 *, 0 ~, 9 -)

```
- InputMethodImpl(InputMethodService)  [API 3+]
- attachToken(IBinder): void  [API 3+]
- bindInput(InputBinding): void  [API 3+]
- changeInputMethodSubtype(InputMethodSubtype): void  [API 3+]
- hideSoftInput(int, ResultReceiver): void  [API 3+]
- restartInput(InputConnection, EditorInfo): void  [API 3+]
- showSoftInput(int, ResultReceiver): void  [API 3+]
- startInput(InputConnection, EditorInfo): void  [API 3+]
- unbindInput(): void  [API 3+]
```

## InputMethodService.InputMethodSessionImpl — declaration; API 3+ (0 +, 0 *, 0 ~, 10 -)

```
- InputMethodSessionImpl(InputMethodService)  [API 3+]
- appPrivateCommand(String, Bundle): void  [API 3+]
- displayCompletions(CompletionInfo[]): void  [API 3+]
- finishInput(): void  [API 3+]
- toggleSoftInput(int, int): void  [API 3+]
- updateCursor(Rect): void  [API 3+]
- updateCursorAnchorInfo(CursorAnchorInfo): void  [API 3+]
- updateExtractedText(int, ExtractedText): void  [API 3+]
- updateSelection(int, int, int, int, int, int): void  [API 3+]
- viewClicked(boolean): void  [API 3+]
```

## InputMethodService.Insets — declaration; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ Insets()  [API 3+]
+ contentTopInsets [field]  [API 3+]
+ touchableInsets [field]  [API 3+]
- touchableRegion [field]  [API 11+]
+ visibleTopInsets [field]  [API 3+]
```

## Keyboard — declaration; API 3+, deprecated in 29 (0 +, 8 *, 0 ~, 14 -)

```
- Keyboard(Context, int)  [API 3+]
- Keyboard(Context, int, CharSequence, int, int)  [API 3+]
- Keyboard(Context, int, int)  [API 3+]
- Keyboard(Context, int, int, int, int)  [API 11+]
- createKeyFromXml(Resources, Keyboard.Row, int, int, XmlResourceParser): Keyboard.Key  [API 3+]
- createRowFromXml(Resources, XmlResourceParser): Keyboard.Row  [API 3+]
- getHeight(): int  [API 3+]
* getHorizontalGap(): int  [API 3+]
* getKeyHeight(): int  [API 3+]
* getKeyWidth(): int  [API 3+]
- getKeys(): List  [API 3+]
- getMinWidth(): int  [API 3+]
- getModifierKeys(): List  [API 3+]
- getNearestKeys(int, int): int[]  [API 3+]
- getShiftKeyIndex(): int  [API 3+]
* getVerticalGap(): int  [API 3+]
- isShifted(): boolean  [API 3+]
* setHorizontalGap(int): void  [API 3+]
* setKeyHeight(int): void  [API 3+]
* setKeyWidth(int): void  [API 3+]
- setShifted(boolean): boolean  [API 3+]
* setVerticalGap(int): void  [API 3+]
```

## Keyboard.Key — declaration; API 3+, deprecated in 29 (0 +, 0 *, 0 ~, 7 -)

```
- Key(Keyboard.Row)  [API 3+]
- Key(Resources, Keyboard.Row, int, int, XmlResourceParser)  [API 3+]
+ codes [field]  [API 3+]
+ edgeFlags [field]  [API 3+]
+ gap [field]  [API 3+]
- getCurrentDrawableState(): int[]  [API 3+]
+ height [field]  [API 3+]
+ icon [field]  [API 3+]
+ iconPreview [field]  [API 3+]
- isInside(int, int): boolean  [API 3+]
+ label [field]  [API 3+]
+ modifier [field]  [API 3+]
+ on [field]  [API 3+]
- onPressed(): void  [API 3+]
- onReleased(boolean): void  [API 3+]
+ popupCharacters [field]  [API 3+]
+ popupResId [field]  [API 3+]
+ pressed [field]  [API 3+]
+ repeatable [field]  [API 3+]
- squaredDistanceFrom(int, int): int  [API 3+]
+ sticky [field]  [API 3+]
+ text [field]  [API 3+]
+ width [field]  [API 3+]
+ x [field]  [API 3+]
+ y [field]  [API 3+]
```

## Keyboard.Row — declaration; API 3+, deprecated in 29 (0 +, 0 *, 0 ~, 2 -)

```
- Row(Keyboard)  [API 3+]
- Row(Resources, Keyboard, XmlResourceParser)  [API 3+]
+ defaultHeight [field]  [API 3+]
+ defaultHorizontalGap [field]  [API 3+]
+ defaultWidth [field]  [API 3+]
+ mode [field]  [API 3+]
+ rowEdgeFlags [field]  [API 3+]
+ verticalGap [field]  [API 3+]
```

## KeyboardView — declaration; API 3+, deprecated in 29 (0 +, 0 *, 0 ~, 33 -)

```
- KeyboardView(Context, AttributeSet)  [API 3+]
- KeyboardView(Context, AttributeSet, int)  [API 3+]
- KeyboardView(Context, AttributeSet, int, int)  [API 21+]
- closing(): void  [API 3+]
- getKeyboard(): Keyboard  [API 3+]
- getOnKeyboardActionListener(): KeyboardView.OnKeyboardActionListener  [API 3+]
- handleBack(): boolean  [API 3+]
- invalidateAllKeys(): void  [API 4+]
- invalidateKey(int): void  [API 4+]
- isPreviewEnabled(): boolean  [API 3+]
- isProximityCorrectionEnabled(): boolean  [API 3+]
- isShifted(): boolean  [API 3+]
- onAttachedToWindow(): void  [API 3+]
- onClick(View): void  [API 3+]
- onDetachedFromWindow(): void  [API 3+]
- onDraw(Canvas): void  [API 3+]
- onHoverEvent(MotionEvent): boolean  [API 3+]
- onLongPress(Keyboard.Key): boolean  [API 3+]
- onMeasure(int, int): void  [API 3+]
- onSizeChanged(int, int, int, int): void  [API 3+]
- onTouchEvent(MotionEvent): boolean  [API 3+]
- setKeyboard(Keyboard): void  [API 3+]
- setOnKeyboardActionListener(KeyboardView.OnKeyboardActionListener): void  [API 3+]
- setPopupOffset(int, int): void  [API 3+]
- setPopupParent(View): void  [API 3+]
- setPreviewEnabled(boolean): void  [API 3+]
- setProximityCorrectionEnabled(boolean): void  [API 3+]
- setShifted(boolean): boolean  [API 3+]
- setVerticalCorrection(int): void  [API 3+]
- swipeDown(): void  [API 3+]
- swipeLeft(): void  [API 3+]
- swipeRight(): void  [API 3+]
- swipeUp(): void  [API 3+]
```

## KeyboardView.OnKeyboardActionListener — declaration; API 3+, deprecated in 29 (8 +, 0 *, 0 ~, 0 -)

```
+ onKey(int, int[]): void  [API 3+]
+ onPress(int): void  [API 3+]
+ onRelease(int): void  [API 3+]
+ onText(CharSequence): void  [API 3+]
+ swipeDown(): void  [API 3+]
+ swipeLeft(): void  [API 3+]
+ swipeRight(): void  [API 3+]
+ swipeUp(): void  [API 3+]
```


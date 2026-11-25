# android.view.inputmethod

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BaseInputConnection — framework; API 3+ (27 +, 0 *, 8 ~, 0 -)

```
+ BaseInputConnection(View, boolean)  [API 3+]
+ beginBatchEdit(): boolean  [API 3+]
~ clearMetaKeyStates(int): boolean  [API 3+]
+ closeConnection(): void  [API 3+]
~ commitCompletion(CompletionInfo): boolean  [API 3+]
+ commitContent(InputContentInfo, int, Bundle): boolean  [API 3+]
~ commitCorrection(CorrectionInfo): boolean  [API 3+]
+ commitText(CharSequence, int): boolean  [API 3+]
+ deleteSurroundingText(int, int): boolean  [API 3+]
+ deleteSurroundingTextInCodePoints(int, int): boolean  [API 3+]
+ endBatchEdit(): boolean  [API 3+]
+ finishComposingText(): boolean  [API 3+]
+ getComposingSpanEnd(Spannable): int [static]  [API 3+]
+ getComposingSpanStart(Spannable): int [static]  [API 3+]
+ getCursorCapsMode(int): int  [API 3+]
+ getEditable(): Editable  [API 3+]
+ getExtractedText(ExtractedTextRequest, int): ExtractedText  [API 3+]
~ getHandler(): Handler  [API 3+]
+ getSelectedText(int): CharSequence  [API 3+]
+ getSurroundingText(int, int, int): SurroundingText  [API 3+]
+ getTextAfterCursor(int, int): CharSequence  [API 3+]
+ getTextBeforeCursor(int, int): CharSequence  [API 3+]
~ performContextMenuAction(int): boolean  [API 3+]
+ performEditorAction(int): boolean  [API 3+]
~ performPrivateCommand(String, Bundle): boolean  [API 3+]
+ removeComposingSpans(Spannable): void [static]  [API 3+]
+ replaceText(int, int, CharSequence, int, TextAttribute): boolean  [API 3+]
~ reportFullscreenMode(boolean): boolean  [API 3+]
~ requestCursorUpdates(int): boolean  [API 3+]
+ sendKeyEvent(KeyEvent): boolean  [API 3+]
+ setComposingRegion(int, int): boolean  [API 3+]
+ setComposingSpans(Spannable): void [static]  [API 3+]
+ setComposingText(CharSequence, int): boolean  [API 3+]
+ setSelection(int, int): boolean  [API 3+]
+ takeSnapshot(): TextSnapshot  [API 3+]
```

## CompletionInfo — framework; API 3+ (7 +, 0 *, 2 ~, 0 -)

```
- CREATOR [field]  [API 3+]
+ CompletionInfo(long, int, CharSequence)  [API 3+]
+ CompletionInfo(long, int, CharSequence, CharSequence)  [API 3+]
~ describeContents(): int  [API 3+]
+ getId(): long  [API 3+]
+ getLabel(): CharSequence  [API 3+]
+ getPosition(): int  [API 3+]
+ getText(): CharSequence  [API 3+]
+ toString(): String  [API 3+]
~ writeToParcel(Parcel, int): void  [API 3+]
```

## ConnectionlessHandwritingCallback — declaration; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ onError(int): void  [API 35+]
+ onResult(CharSequence): void  [API 35+]
```

## CorrectionInfo — framework; API 11+ (5 +, 0 *, 2 ~, 0 -)

```
- CREATOR [field]  [API 11+]
+ CorrectionInfo(int, CharSequence, CharSequence)  [API 11+]
~ describeContents(): int  [API 11+]
+ getNewText(): CharSequence  [API 11+]
+ getOffset(): int  [API 11+]
+ getOldText(): CharSequence  [API 11+]
+ toString(): String  [API 11+]
~ writeToParcel(Parcel, int): void  [API 11+]
```

## CursorAnchorInfo — framework; API 21+ (20 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 21+]
+ CursorAnchorInfo(Parcel)  [API 21+]
~ describeContents(): int  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getCharacterBounds(int): RectF  [API 21+]
+ getCharacterBoundsFlags(int): int  [API 21+]
+ getComposingText(): CharSequence  [API 21+]
+ getComposingTextStart(): int  [API 21+]
+ getEditorBoundsInfo(): EditorBoundsInfo  [API 33+]
+ getInsertionMarkerBaseline(): float  [API 21+]
+ getInsertionMarkerBottom(): float  [API 21+]
+ getInsertionMarkerFlags(): int  [API 21+]
+ getInsertionMarkerHorizontal(): float  [API 21+]
+ getInsertionMarkerTop(): float  [API 21+]
+ getMatrix(): Matrix  [API 21+]
+ getSelectionEnd(): int  [API 21+]
+ getSelectionStart(): int  [API 21+]
+ getTextAppearanceInfo(): TextAppearanceInfo  [API 34+]
+ getVisibleLineBounds(): List  [API 34+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## CursorAnchorInfo.Builder — framework; API 21+ (12 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 21+]
+ addCharacterBounds(int, float, float, float, float, int): CursorAnchorInfo.Builder  [API 21+]
+ addVisibleLineBounds(float, float, float, float): CursorAnchorInfo.Builder  [API 34+]
+ build(): CursorAnchorInfo  [API 21+]
+ clearVisibleLineBounds(): CursorAnchorInfo.Builder  [API 34+]
+ reset(): void  [API 21+]
+ setComposingText(int, CharSequence): CursorAnchorInfo.Builder  [API 21+]
+ setEditorBoundsInfo(EditorBoundsInfo): CursorAnchorInfo.Builder  [API 33+]
+ setInsertionMarkerLocation(float, float, float, float, int): CursorAnchorInfo.Builder  [API 21+]
+ setMatrix(Matrix): CursorAnchorInfo.Builder  [API 21+]
+ setSelectionRange(int, int): CursorAnchorInfo.Builder  [API 21+]
+ setTextAppearanceInfo(TextAppearanceInfo): CursorAnchorInfo.Builder  [API 34+]
```

## DeleteGesture — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getDeletionArea(): RectF  [API 34+]
* getGranularity(): int  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## DeleteGesture.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): DeleteGesture  [API 34+]
* setDeletionArea(RectF): DeleteGesture.Builder  [API 34+]
* setFallbackText(String): DeleteGesture.Builder  [API 34+]
* setGranularity(int): DeleteGesture.Builder  [API 34+]
```

## DeleteRangeGesture — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getDeletionEndArea(): RectF  [API 34+]
* getDeletionStartArea(): RectF  [API 34+]
* getGranularity(): int  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## DeleteRangeGesture.Builder — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): DeleteRangeGesture  [API 34+]
* setDeletionEndArea(RectF): DeleteRangeGesture.Builder  [API 34+]
* setDeletionStartArea(RectF): DeleteRangeGesture.Builder  [API 34+]
* setFallbackText(String): DeleteRangeGesture.Builder  [API 34+]
* setGranularity(int): DeleteRangeGesture.Builder  [API 34+]
```

## EditorBoundsInfo — declaration; API 33+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getEditorBounds(): RectF  [API 33+]
* getHandwritingBounds(): RectF  [API 33+]
* hashCode(): int  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## EditorBoundsInfo.Builder — declaration; API 33+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): EditorBoundsInfo  [API 33+]
* setEditorBounds(RectF): EditorBoundsInfo.Builder  [API 33+]
* setHandwritingBounds(RectF): EditorBoundsInfo.Builder  [API 33+]
```

## EditorInfo — framework; API 3+ (18 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 3+]
+ EditorInfo()  [API 3+]
+ actionId [field]  [API 3+]
+ actionLabel [field]  [API 3+]
+ contentMimeTypes [field]  [API 25+]
~ describeContents(): int  [API 3+]
+ dump(Printer, String): void  [API 3+]
+ extras [field]  [API 3+]
+ fieldId [field]  [API 3+]
+ fieldName [field]  [API 3+]
+ getInitialSelectedText(int): CharSequence  [API 30+]
+ getInitialSurroundingText(int, int, int): SurroundingText  [API 31+]
+ getInitialTextAfterCursor(int, int): CharSequence  [API 30+]
+ getInitialTextBeforeCursor(int, int): CharSequence  [API 30+]
+ getInitialToolType(): int  [API 34+]
+ getSupportedHandwritingGesturePreviews(): Set  [API 34+]
+ getSupportedHandwritingGestures(): List  [API 34+]
+ hintLocales [field]  [API 24+]
+ hintText [field]  [API 3+]
+ imeOptions [field]  [API 3+]
+ initialCapsMode [field]  [API 3+]
+ initialSelEnd [field]  [API 3+]
+ initialSelStart [field]  [API 3+]
+ inputType [field]  [API 3+]
+ isStylusHandwritingEnabled(): boolean  [API 35+]
+ label [field]  [API 3+]
+ makeCompatible(int): void  [API 11+]
+ packageName [field]  [API 3+]
+ privateImeOptions [field]  [API 3+]
+ setInitialSurroundingSubText(CharSequence, int): void  [API 30+]
+ setInitialSurroundingText(CharSequence): void  [API 30+]
+ setInitialToolType(int): void  [API 34+]
+ setStylusHandwritingEnabled(boolean): void  [API 35+]
+ setSupportedHandwritingGesturePreviews(Set): void  [API 34+]
+ setSupportedHandwritingGestures(List): void  [API 34+]
+ writeToParcel(Parcel, int): void  [API 3+]
```

## ExtractedText — framework; API 3+ (1 +, 0 *, 2 ~, 0 -)

```
- CREATOR [field]  [API 3+]
+ ExtractedText()  [API 3+]
~ describeContents(): int  [API 3+]
+ flags [field]  [API 3+]
+ hint [field]  [API 28+]
+ partialEndOffset [field]  [API 3+]
+ partialStartOffset [field]  [API 3+]
+ selectionEnd [field]  [API 3+]
+ selectionStart [field]  [API 3+]
+ startOffset [field]  [API 3+]
+ text [field]  [API 3+]
~ writeToParcel(Parcel, int): void  [API 3+]
```

## ExtractedTextRequest — framework; API 3+ (1 +, 0 *, 2 ~, 0 -)

```
- CREATOR [field]  [API 3+]
+ ExtractedTextRequest()  [API 3+]
~ describeContents(): int  [API 3+]
+ flags [field]  [API 3+]
+ hintMaxChars [field]  [API 3+]
+ hintMaxLines [field]  [API 3+]
+ token [field]  [API 3+]
~ writeToParcel(Parcel, int): void  [API 3+]
```

## HandwritingGesture — declaration; API 34+ (0 +, 1 *, 0 ~, 0 -)

```
* getFallbackText(): String  [API 34+]
```

## InlineSuggestion — declaration; API 30+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getInfo(): InlineSuggestionInfo  [API 30+]
- hashCode(): int  [API 30+]
- inflate(Context, Size, Executor, Consumer): void  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## InlineSuggestionInfo — declaration; API 30+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getAutofillHints(): String[]  [API 30+]
- getInlinePresentationSpec(): InlinePresentationSpec  [API 30+]
- getSource(): String  [API 30+]
- getType(): String  [API 30+]
- hashCode(): int  [API 30+]
- isPinned(): boolean  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## InlineSuggestionsRequest — declaration; API 30+ (0 +, 5 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
* getExtras(): Bundle  [API 30+]
- getHostPackageName(): String  [API 30+]
* getInlinePresentationSpecs(): List  [API 30+]
* getInlineTooltipPresentationSpec(): InlinePresentationSpec  [API 31+]
* getMaxSuggestionCount(): int  [API 30+]
* getSupportedLocales(): LocaleList  [API 30+]
- hashCode(): int  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## InlineSuggestionsRequest.Builder — declaration; API 30+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder(List)  [API 30+]
* addInlinePresentationSpecs(InlinePresentationSpec): InlineSuggestionsRequest.Builder  [API 30+]
* build(): InlineSuggestionsRequest  [API 30+]
* setExtras(Bundle): InlineSuggestionsRequest.Builder  [API 30+]
* setInlinePresentationSpecs(List): InlineSuggestionsRequest.Builder  [API 30+]
* setInlineTooltipPresentationSpec(InlinePresentationSpec): InlineSuggestionsRequest.Builder  [API 31+]
* setMaxSuggestionCount(int): InlineSuggestionsRequest.Builder  [API 30+]
* setSupportedLocales(LocaleList): InlineSuggestionsRequest.Builder  [API 30+]
```

## InlineSuggestionsResponse — declaration; API 30+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getInlineSuggestions(): List  [API 30+]
- hashCode(): int  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## InputBinding — framework; API 3+ (8 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 3+]
+ InputBinding(InputConnection, IBinder, int, int)  [API 3+]
+ InputBinding(InputConnection, InputBinding)  [API 3+]
~ describeContents(): int  [API 3+]
+ getConnection(): InputConnection  [API 3+]
+ getConnectionToken(): IBinder  [API 3+]
+ getPid(): int  [API 3+]
+ getUid(): int  [API 3+]
+ toString(): String  [API 3+]
+ writeToParcel(Parcel, int): void  [API 3+]
```

## InputConnection — framework; API 3+ (16 +, 0 *, 22 ~, 0 -)

```
~ beginBatchEdit(): boolean  [API 3+]
~ clearMetaKeyStates(int): boolean  [API 3+]
~ closeConnection(): void  [API 24+]
+ commitCompletion(CompletionInfo): boolean  [API 3+]
~ commitContent(InputContentInfo, int, Bundle): boolean  [API 25+]
~ commitCorrection(CorrectionInfo): boolean  [API 11+]
+ commitText(CharSequence, int): boolean  [API 3+]
+ commitText(CharSequence, int, TextAttribute): boolean  [API 33+]
+ deleteSurroundingText(int, int): boolean  [API 3+]
+ deleteSurroundingTextInCodePoints(int, int): boolean  [API 24+]
~ endBatchEdit(): boolean  [API 3+]
+ finishComposingText(): boolean  [API 3+]
~ getCursorCapsMode(int): int  [API 3+]
~ getExtractedText(ExtractedTextRequest, int): ExtractedText  [API 3+]
~ getHandler(): Handler  [API 24+]
~ getSelectedText(int): CharSequence  [API 9+]
+ getSurroundingText(int, int, int): SurroundingText  [API 31+]
~ getTextAfterCursor(int, int): CharSequence  [API 3+]
~ getTextBeforeCursor(int, int): CharSequence  [API 3+]
~ performContextMenuAction(int): boolean  [API 3+]
+ performEditorAction(int): boolean  [API 3+]
+ performHandwritingGesture(HandwritingGesture, Executor, IntConsumer): void  [API 34+]
~ performPrivateCommand(String, Bundle): boolean  [API 3+]
~ performSpellCheck(): boolean  [API 31+]
~ previewHandwritingGesture(PreviewableHandwritingGesture, CancellationSignal): boolean  [API 34+]
+ replaceText(int, int, CharSequence, int, TextAttribute): boolean  [API 34+]
~ reportFullscreenMode(boolean): boolean  [API 3+]
~ requestCursorUpdates(int): boolean  [API 21+]
+ requestCursorUpdates(int, int): boolean  [API 33+]
+ requestTextBoundsInfo(RectF, Executor, Consumer): void  [API 34+]
+ sendKeyEvent(KeyEvent): boolean  [API 3+]
~ setComposingRegion(int, int): boolean  [API 9+]
+ setComposingRegion(int, int, TextAttribute): boolean  [API 33+]
+ setComposingText(CharSequence, int): boolean  [API 3+]
+ setComposingText(CharSequence, int, TextAttribute): boolean  [API 33+]
~ setImeConsumesInput(boolean): boolean  [API 31+]
~ setSelection(int, int): boolean  [API 3+]
~ takeSnapshot(): TextSnapshot  [API 33+]
```

## InputConnectionWrapper — framework; API 3+ (40 +, 0 *, 0 ~, 0 -)

```
+ InputConnectionWrapper(InputConnection, boolean)  [API 3+]
+ beginBatchEdit(): boolean  [API 3+]
+ clearMetaKeyStates(int): boolean  [API 3+]
+ closeConnection(): void  [API 3+]
+ commitCompletion(CompletionInfo): boolean  [API 3+]
+ commitContent(InputContentInfo, int, Bundle): boolean  [API 3+]
+ commitCorrection(CorrectionInfo): boolean  [API 3+]
+ commitText(CharSequence, int): boolean  [API 3+]
+ commitText(CharSequence, int, TextAttribute): boolean  [API 3+]
+ deleteSurroundingText(int, int): boolean  [API 3+]
+ deleteSurroundingTextInCodePoints(int, int): boolean  [API 3+]
+ endBatchEdit(): boolean  [API 3+]
+ finishComposingText(): boolean  [API 3+]
+ getCursorCapsMode(int): int  [API 3+]
+ getExtractedText(ExtractedTextRequest, int): ExtractedText  [API 3+]
+ getHandler(): Handler  [API 3+]
+ getSelectedText(int): CharSequence  [API 3+]
+ getSurroundingText(int, int, int): SurroundingText  [API 3+]
+ getTextAfterCursor(int, int): CharSequence  [API 3+]
+ getTextBeforeCursor(int, int): CharSequence  [API 3+]
+ performContextMenuAction(int): boolean  [API 3+]
+ performEditorAction(int): boolean  [API 3+]
+ performHandwritingGesture(HandwritingGesture, Executor, IntConsumer): void  [API 3+]
+ performPrivateCommand(String, Bundle): boolean  [API 3+]
+ performSpellCheck(): boolean  [API 3+]
+ previewHandwritingGesture(PreviewableHandwritingGesture, CancellationSignal): boolean  [API 3+]
+ replaceText(int, int, CharSequence, int, TextAttribute): boolean  [API 3+]
+ reportFullscreenMode(boolean): boolean  [API 3+]
+ requestCursorUpdates(int): boolean  [API 3+]
+ requestCursorUpdates(int, int): boolean  [API 3+]
+ requestTextBoundsInfo(RectF, Executor, Consumer): void  [API 3+]
+ sendKeyEvent(KeyEvent): boolean  [API 3+]
+ setComposingRegion(int, int): boolean  [API 3+]
+ setComposingRegion(int, int, TextAttribute): boolean  [API 3+]
+ setComposingText(CharSequence, int): boolean  [API 3+]
+ setComposingText(CharSequence, int, TextAttribute): boolean  [API 3+]
+ setImeConsumesInput(boolean): boolean  [API 3+]
+ setSelection(int, int): boolean  [API 3+]
+ setTarget(InputConnection): void  [API 3+]
+ takeSnapshot(): TextSnapshot  [API 3+]
```

## InputContentInfo — framework; API 25+ (6 +, 0 *, 3 ~, 0 -)

```
+ CREATOR [field]  [API 25+]
+ InputContentInfo(Uri, ClipDescription)  [API 25+]
+ InputContentInfo(Uri, ClipDescription, Uri)  [API 25+]
~ describeContents(): int  [API 25+]
+ getContentUri(): Uri  [API 25+]
+ getDescription(): ClipDescription  [API 25+]
+ getLinkUri(): Uri  [API 25+]
~ releasePermission(): void  [API 25+]
~ requestPermission(): void  [API 25+]
+ writeToParcel(Parcel, int): void  [API 25+]
```

## InputMethod — declaration; API 3+ (11 +, 0 *, 0 ~, 0 -)

```
+ attachToken(IBinder): void  [API 3+]
+ bindInput(InputBinding): void  [API 3+]
+ changeInputMethodSubtype(InputMethodSubtype): void  [API 11+]
+ createSession(InputMethod.SessionCallback): void  [API 3+]
+ hideSoftInput(int, ResultReceiver): void  [API 3+]
+ restartInput(InputConnection, EditorInfo): void  [API 3+]
+ revokeSession(InputMethodSession): void  [API 3+]
+ setSessionEnabled(InputMethodSession, boolean): void  [API 3+]
+ showSoftInput(int, ResultReceiver): void  [API 3+]
+ startInput(InputConnection, EditorInfo): void  [API 3+]
+ unbindInput(): void  [API 3+]
```

## InputMethod.SessionCallback — declaration; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ sessionCreated(InputMethodSession): void  [API 3+]
```

## InputMethodInfo — framework; API 3+ (16 +, 0 *, 10 ~, 0 -)

```
+ CREATOR [field]  [API 3+]
+ InputMethodInfo(Context, ResolveInfo)  [API 3+]
+ InputMethodInfo(String, String, CharSequence, String)  [API 3+]
~ createImeLanguageSettingsActivityIntent(): Intent  [API 36+]
~ createStylusHandwritingSettingsActivityIntent(): Intent  [API 34+]
~ describeContents(): int  [API 3+]
+ dump(Printer, String): void  [API 3+]
+ equals(Object): boolean  [API 3+]
+ getComponent(): ComponentName  [API 3+]
~ getConfigChanges(): int  [API 31+]
+ getId(): String  [API 3+]
~ getIsDefaultResourceId(): int  [API 3+]
+ getPackageName(): String  [API 3+]
+ getServiceInfo(): ServiceInfo  [API 3+]
+ getServiceName(): String  [API 3+]
+ getSettingsActivity(): String  [API 3+]
+ getSubtypeAt(int): InputMethodSubtype  [API 11+]
+ getSubtypeCount(): int  [API 11+]
+ hashCode(): int  [API 3+]
~ loadIcon(PackageManager): Drawable  [API 3+]
+ loadLabel(PackageManager): CharSequence  [API 3+]
~ shouldShowInInputMethodPicker(): boolean  [API 31+]
~ supportsConnectionlessStylusHandwriting(): boolean  [API 35+]
~ supportsStylusHandwriting(): boolean  [API 33+]
~ suppressesSpellChecker(): boolean  [API 31+]
+ toString(): String  [API 3+]
+ writeToParcel(Parcel, int): void  [API 3+]
```

## InputMethodManager — framework; API 3+ (23 +, 0 *, 32 ~, 0 -)

```
~ acceptStylusHandwritingDelegation(View): boolean  [API 34+]
~ acceptStylusHandwritingDelegation(View, String): boolean  [API 34+]
+ acceptStylusHandwritingDelegation(View, String, Executor, Consumer): void  [API 35+]
+ acceptStylusHandwritingDelegation(View, String, int, Executor, Consumer): void  [API 35+]
+ dispatchKeyEventFromInputMethod(View, KeyEvent): void  [API 24+]
~ displayCompletions(View, CompletionInfo[]): void  [API 3+]
+ getCurrentInputMethodInfo(): InputMethodInfo  [API 34+]
~ getCurrentInputMethodSubtype(): InputMethodSubtype  [API 11+]
+ getEnabledInputMethodList(): List  [API 3+]
+ getEnabledInputMethodSubtypeList(InputMethodInfo, boolean): List  [API 11+]
+ getInputMethodList(): List  [API 3+]
~ getLastInputMethodSubtype(): InputMethodSubtype  [API 14+]
+ getShortcutInputMethodsAndSubtypes(): Map  [API 11+]
+ hideSoftInputFromInputMethod(IBinder, int): void  [API 3+, deprecated in 28]
+ hideSoftInputFromWindow(IBinder, int): boolean  [API 3+]
+ hideSoftInputFromWindow(IBinder, int, ResultReceiver): boolean  [API 3+]
~ hideStatusIcon(IBinder): void  [API 3+, deprecated in 28]
+ invalidateInput(View): void  [API 33+]
+ isAcceptingText(): boolean  [API 3+]
+ isActive(): boolean  [API 3+]
+ isActive(View): boolean  [API 3+]
~ isConnectionlessStylusHandwritingAvailable(): boolean  [API 35+]
~ isFullscreenMode(): boolean  [API 3+]
~ isInputMethodSuppressingSpellChecker(): boolean  [API 31+]
~ isStylusHandwritingAvailable(): boolean  [API 34+]
~ isWatchingCursor(View): boolean  [API 3+, deprecated in 21]
~ prepareStylusHandwritingDelegation(View): void  [API 34+]
~ prepareStylusHandwritingDelegation(View, String): void  [API 34+]
+ restartInput(View): void  [API 3+]
~ sendAppPrivateCommand(View, String, Bundle): void  [API 3+]
~ setAdditionalInputMethodSubtypes(String, InputMethodSubtype[]): void  [API 14+, deprecated in 29]
~ setCurrentInputMethodSubtype(InputMethodSubtype): boolean  [API 11+, deprecated in 29]
~ setExplicitlyEnabledInputMethodSubtypes(String, int[]): void  [API 34+]
~ setInputMethod(IBinder, String): void  [API 3+, deprecated in 28]
~ setInputMethodAndSubtype(IBinder, String, InputMethodSubtype): void  [API 11+, deprecated in 28]
~ shouldOfferSwitchingToNextInputMethod(IBinder): boolean  [API 19+, deprecated in 28]
~ showInputMethodAndSubtypeEnabler(String): void  [API 11+]
~ showInputMethodPicker(): void  [API 3+]
+ showSoftInput(View, int): boolean  [API 3+]
+ showSoftInput(View, int, ResultReceiver): boolean  [API 3+]
~ showSoftInputFromInputMethod(IBinder, int): void  [API 3+, deprecated in 28]
~ showStatusIcon(IBinder, String, int): void  [API 3+, deprecated in 28]
+ startConnectionlessStylusHandwriting(View, CursorAnchorInfo, Executor, ConnectionlessHandwritingCallback): void  [API 35+]
+ startConnectionlessStylusHandwritingForDelegation(View, CursorAnchorInfo, Executor, ConnectionlessHandwritingCallback): void  [API 35+]
+ startConnectionlessStylusHandwritingForDelegation(View, CursorAnchorInfo, String, Executor, ConnectionlessHandwritingCallback): void  [API 35+]
~ startStylusHandwriting(View): void  [API 33+]
~ switchToLastInputMethod(IBinder): boolean  [API 11+, deprecated in 28]
~ switchToNextInputMethod(IBinder, boolean): boolean  [API 16+, deprecated in 28]
+ toggleSoftInput(int, int): void  [API 3+, deprecated in 31]
+ toggleSoftInputFromWindow(IBinder, int, int): void  [API 3+, deprecated in 31]
~ updateCursor(View, int, int, int, int): void  [API 3+, deprecated in 21]
~ updateCursorAnchorInfo(View, CursorAnchorInfo): void  [API 21+]
~ updateExtractedText(View, int, ExtractedText): void  [API 3+]
~ updateSelection(View, int, int, int, int): void  [API 3+]
~ viewClicked(View): void  [API 14+, deprecated in 29]
```

## InputMethodSession — declaration; API 3+ (12 +, 0 *, 0 ~, 0 -)

```
+ appPrivateCommand(String, Bundle): void  [API 3+]
+ dispatchGenericMotionEvent(int, MotionEvent, InputMethodSession.EventCallback): void  [API 17+]
+ dispatchKeyEvent(int, KeyEvent, InputMethodSession.EventCallback): void  [API 3+]
+ dispatchTrackballEvent(int, MotionEvent, InputMethodSession.EventCallback): void  [API 3+]
+ displayCompletions(CompletionInfo[]): void  [API 3+]
+ finishInput(): void  [API 3+]
+ toggleSoftInput(int, int): void  [API 3+, deprecated in 31]
+ updateCursor(Rect): void  [API 3+]
+ updateCursorAnchorInfo(CursorAnchorInfo): void  [API 21+]
+ updateExtractedText(int, ExtractedText): void  [API 3+]
+ updateSelection(int, int, int, int, int, int): void  [API 3+]
+ viewClicked(boolean): void  [API 14+]
```

## InputMethodSession.EventCallback — declaration; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ finishedEvent(int, boolean): void  [API 3+]
```

## InputMethodSubtype — framework; API 11+ (20 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 11+]
+ InputMethodSubtype(int, int, String, String, String, boolean, boolean)  [API 14+, deprecated in 19]
+ InputMethodSubtype(int, int, String, String, String, boolean, boolean, int)  [API 17+, deprecated in 19]
+ containsExtraValueKey(String): boolean  [API 12+]
~ describeContents(): int  [API 11+]
+ equals(Object): boolean  [API 11+]
+ getDisplayName(Context, String, ApplicationInfo): CharSequence  [API 14+]
+ getExtraValue(): String  [API 11+]
+ getExtraValueOf(String): String  [API 12+]
+ getIconResId(): int  [API 11+]
+ getLanguageTag(): String  [API 24+]
+ getLocale(): String  [API 11+, deprecated in 24]
+ getMode(): String  [API 11+]
+ getNameOverride(): CharSequence  [API 34+]
+ getNameResId(): int  [API 11+]
+ getPhysicalKeyboardHintLanguageTag(): ULocale  [API 34+]
+ getPhysicalKeyboardHintLayoutType(): String  [API 34+]
+ hashCode(): int  [API 11+]
+ isAsciiCapable(): boolean  [API 19+]
+ isAuxiliary(): boolean  [API 14+]
+ overridesImplicitlyEnabledSubtype(): boolean  [API 14+]
+ writeToParcel(Parcel, int): void  [API 11+]
```

## InputMethodSubtype.InputMethodSubtypeBuilder — framework; API 19+ (14 +, 0 *, 0 ~, 0 -)

```
+ InputMethodSubtypeBuilder()  [API 19+]
+ build(): InputMethodSubtype  [API 19+]
+ setIsAsciiCapable(boolean): InputMethodSubtype.InputMethodSubtypeBuilder  [API 19+]
+ setIsAuxiliary(boolean): InputMethodSubtype.InputMethodSubtypeBuilder  [API 19+]
+ setLanguageTag(String): InputMethodSubtype.InputMethodSubtypeBuilder  [API 24+]
+ setOverridesImplicitlyEnabledSubtype(boolean): InputMethodSubtype.InputMethodSubtypeBuilder  [API 19+]
+ setPhysicalKeyboardHint(ULocale, String): InputMethodSubtype.InputMethodSubtypeBuilder  [API 34+]
+ setSubtypeExtraValue(String): InputMethodSubtype.InputMethodSubtypeBuilder  [API 19+]
+ setSubtypeIconResId(int): InputMethodSubtype.InputMethodSubtypeBuilder  [API 19+]
+ setSubtypeId(int): InputMethodSubtype.InputMethodSubtypeBuilder  [API 19+]
+ setSubtypeLocale(String): InputMethodSubtype.InputMethodSubtypeBuilder  [API 19+]
+ setSubtypeMode(String): InputMethodSubtype.InputMethodSubtypeBuilder  [API 19+]
+ setSubtypeNameOverride(CharSequence): InputMethodSubtype.InputMethodSubtypeBuilder  [API 34+]
+ setSubtypeNameResId(int): InputMethodSubtype.InputMethodSubtypeBuilder  [API 19+]
```

## InsertGesture — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getInsertionPoint(): PointF  [API 34+]
* getTextToInsert(): String  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## InsertGesture.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): InsertGesture  [API 34+]
* setFallbackText(String): InsertGesture.Builder  [API 34+]
* setInsertionPoint(PointF): InsertGesture.Builder  [API 34+]
* setTextToInsert(String): InsertGesture.Builder  [API 34+]
```

## InsertModeGesture — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getCancellationSignal(): CancellationSignal  [API 34+]
* getInsertionPoint(): PointF  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## InsertModeGesture.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): InsertModeGesture  [API 34+]
* setCancellationSignal(CancellationSignal): InsertModeGesture.Builder  [API 34+]
* setFallbackText(String): InsertModeGesture.Builder  [API 34+]
* setInsertionPoint(PointF): InsertModeGesture.Builder  [API 34+]
```

## JoinOrSplitGesture — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getJoinOrSplitPoint(): PointF  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## JoinOrSplitGesture.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): JoinOrSplitGesture  [API 34+]
* setFallbackText(String): JoinOrSplitGesture.Builder  [API 34+]
* setJoinOrSplitPoint(PointF): JoinOrSplitGesture.Builder  [API 34+]
```

## PreviewableHandwritingGesture — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## RemoveSpaceGesture — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- getEndPoint(): PointF  [API 34+]
- getStartPoint(): PointF  [API 34+]
- hashCode(): int  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## RemoveSpaceGesture.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder()  [API 34+]
* build(): RemoveSpaceGesture  [API 34+]
* setFallbackText(String): RemoveSpaceGesture.Builder  [API 34+]
- setPoints(PointF, PointF): RemoveSpaceGesture.Builder  [API 34+]
```

## SelectGesture — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getGranularity(): int  [API 34+]
* getSelectionArea(): RectF  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## SelectGesture.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): SelectGesture  [API 34+]
* setFallbackText(String): SelectGesture.Builder  [API 34+]
* setGranularity(int): SelectGesture.Builder  [API 34+]
* setSelectionArea(RectF): SelectGesture.Builder  [API 34+]
```

## SelectRangeGesture — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getGranularity(): int  [API 34+]
* getSelectionEndArea(): RectF  [API 34+]
* getSelectionStartArea(): RectF  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## SelectRangeGesture.Builder — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): SelectRangeGesture  [API 34+]
* setFallbackText(String): SelectRangeGesture.Builder  [API 34+]
* setGranularity(int): SelectRangeGesture.Builder  [API 34+]
* setSelectionEndArea(RectF): SelectRangeGesture.Builder  [API 34+]
* setSelectionStartArea(RectF): SelectRangeGesture.Builder  [API 34+]
```

## SurroundingText — declaration; API 31+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* SurroundingText(CharSequence, int, int, int)  [API 31+]
* describeContents(): int  [API 31+]
* getOffset(): int  [API 31+]
* getSelectionEnd(): int  [API 31+]
* getSelectionStart(): int  [API 31+]
* getText(): CharSequence  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## TextAppearanceInfo — declaration; API 34+ (0 +, 27 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getFontFeatureSettings(): String  [API 34+]
* getFontVariationSettings(): String  [API 34+]
* getHighlightTextColor(): int  [API 34+]
* getHintTextColor(): int  [API 34+]
* getLetterSpacing(): float  [API 34+]
* getLineBreakStyle(): int  [API 34+]
* getLineBreakWordStyle(): int  [API 34+]
* getLinkTextColor(): int  [API 34+]
* getShadowColor(): int  [API 34+]
* getShadowDx(): float  [API 34+]
* getShadowDy(): float  [API 34+]
* getShadowRadius(): float  [API 34+]
* getSystemFontFamilyName(): String  [API 34+]
* getTextColor(): int  [API 34+]
* getTextFontWeight(): int  [API 34+]
* getTextLocales(): LocaleList  [API 34+]
* getTextScaleX(): float  [API 34+]
* getTextSize(): float  [API 34+]
* getTextStyle(): int  [API 34+]
* hashCode(): int  [API 34+]
* isAllCaps(): boolean  [API 34+]
* isElegantTextHeight(): boolean  [API 34+]
* isFallbackLineSpacing(): boolean  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## TextAppearanceInfo.Builder — declaration; API 34+ (0 +, 24 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): TextAppearanceInfo  [API 34+]
* setAllCaps(boolean): TextAppearanceInfo.Builder  [API 34+]
* setElegantTextHeight(boolean): TextAppearanceInfo.Builder  [API 34+]
* setFallbackLineSpacing(boolean): TextAppearanceInfo.Builder  [API 34+]
* setFontFeatureSettings(String): TextAppearanceInfo.Builder  [API 34+]
* setFontVariationSettings(String): TextAppearanceInfo.Builder  [API 34+]
* setHighlightTextColor(int): TextAppearanceInfo.Builder  [API 34+]
* setHintTextColor(int): TextAppearanceInfo.Builder  [API 34+]
* setLetterSpacing(float): TextAppearanceInfo.Builder  [API 34+]
* setLineBreakStyle(int): TextAppearanceInfo.Builder  [API 34+]
* setLineBreakWordStyle(int): TextAppearanceInfo.Builder  [API 34+]
* setLinkTextColor(int): TextAppearanceInfo.Builder  [API 34+]
* setShadowColor(int): TextAppearanceInfo.Builder  [API 34+]
* setShadowDx(float): TextAppearanceInfo.Builder  [API 34+]
* setShadowDy(float): TextAppearanceInfo.Builder  [API 34+]
* setShadowRadius(float): TextAppearanceInfo.Builder  [API 34+]
* setSystemFontFamilyName(String): TextAppearanceInfo.Builder  [API 34+]
* setTextColor(int): TextAppearanceInfo.Builder  [API 34+]
* setTextFontWeight(int): TextAppearanceInfo.Builder  [API 34+]
* setTextLocales(LocaleList): TextAppearanceInfo.Builder  [API 34+]
* setTextScaleX(float): TextAppearanceInfo.Builder  [API 34+]
* setTextSize(float): TextAppearanceInfo.Builder  [API 34+]
* setTextStyle(int): TextAppearanceInfo.Builder  [API 34+]
```

## TextAttribute — framework; API 33+ (3 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
~ describeContents(): int  [API 33+]
+ getExtras(): PersistableBundle  [API 33+]
+ getTextConversionSuggestions(): List  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TextAttribute.Builder — framework; API 33+ (4 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 33+]
+ build(): TextAttribute  [API 33+]
+ setExtras(PersistableBundle): TextAttribute.Builder  [API 33+]
+ setTextConversionSuggestions(List): TextAttribute.Builder  [API 33+]
```

## TextBoundsInfo — framework; API 34+ (12 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
~ describeContents(): int  [API 34+]
+ getCharacterBidiLevel(int): int  [API 34+]
+ getCharacterBounds(int, RectF): void  [API 34+]
+ getCharacterFlags(int): int  [API 34+]
+ getEndIndex(): int  [API 34+]
+ getGraphemeSegmentFinder(): SegmentFinder  [API 34+]
+ getLineSegmentFinder(): SegmentFinder  [API 34+]
+ getMatrix(Matrix): void  [API 34+]
+ getOffsetForPosition(float, float): int  [API 34+]
+ getRangeForRect(RectF, SegmentFinder, Layout.TextInclusionStrategy): int[]  [API 34+]
+ getStartIndex(): int  [API 34+]
+ getWordSegmentFinder(): SegmentFinder  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## TextBoundsInfo.Builder — framework; API 34+ (11 +, 0 *, 0 ~, 0 -)

```
+ Builder(int, int)  [API 34+]
+ build(): TextBoundsInfo  [API 34+]
+ clear(): TextBoundsInfo.Builder  [API 34+]
+ setCharacterBidiLevel(int[]): TextBoundsInfo.Builder  [API 34+]
+ setCharacterBounds(float[]): TextBoundsInfo.Builder  [API 34+]
+ setCharacterFlags(int[]): TextBoundsInfo.Builder  [API 34+]
+ setGraphemeSegmentFinder(SegmentFinder): TextBoundsInfo.Builder  [API 34+]
+ setLineSegmentFinder(SegmentFinder): TextBoundsInfo.Builder  [API 34+]
+ setMatrix(Matrix): TextBoundsInfo.Builder  [API 34+]
+ setStartAndEnd(int, int): TextBoundsInfo.Builder  [API 34+]
+ setWordSegmentFinder(SegmentFinder): TextBoundsInfo.Builder  [API 34+]
```

## TextBoundsInfoResult — framework; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ TextBoundsInfoResult(int)  [API 34+]
+ TextBoundsInfoResult(int, TextBoundsInfo)  [API 34+]
+ getResultCode(): int  [API 34+]
+ getTextBoundsInfo(): TextBoundsInfo  [API 34+]
```

## TextSnapshot — framework; API 33+ (7 +, 0 *, 0 ~, 0 -)

```
+ TextSnapshot(SurroundingText, int, int, int)  [API 33+]
+ getCompositionEnd(): int  [API 33+]
+ getCompositionStart(): int  [API 33+]
+ getCursorCapsMode(): int  [API 33+]
+ getSelectionEnd(): int  [API 33+]
+ getSelectionStart(): int  [API 33+]
+ getSurroundingText(): SurroundingText  [API 33+]
```


# android.app.assist

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AssistContent — declaration; API 23+ (0 +, 9 *, 0 ~, 5 -)

```
* AssistContent()  [API 23+]
- CREATOR [field]  [API 23+]
- describeContents(): int  [API 23+]
* getClipData(): ClipData  [API 23+]
- getExtras(): Bundle  [API 23+]
* getIntent(): Intent  [API 23+]
* getStructuredData(): String  [API 23+]
* getWebUri(): Uri  [API 23+]
- isAppProvidedIntent(): boolean  [API 23+]
- isAppProvidedWebUri(): boolean  [API 24+]
* setClipData(ClipData): void  [API 23+]
* setIntent(Intent): void  [API 23+]
* setStructuredData(String): void  [API 23+]
* setWebUri(Uri): void  [API 23+]
- writeToParcel(Parcel, int): void  [API 23+]
```

## AssistStructure — declaration; API 23+ (0 +, 0 *, 0 ~, 9 -)

```
- AssistStructure()  [API 23+]
- CREATOR [field]  [API 23+]
- describeContents(): int  [API 23+]
- getAcquisitionEndTime(): long  [API 26+]
- getAcquisitionStartTime(): long  [API 26+]
- getActivityComponent(): ComponentName  [API 23+]
- getWindowNodeAt(int): AssistStructure.WindowNode  [API 23+]
- getWindowNodeCount(): int  [API 23+]
- isHomeActivity(): boolean  [API 26+]
- writeToParcel(Parcel, int): void  [API 23+]
```

## AssistStructure.ViewNode — declaration; API 23+ (0 +, 0 *, 0 ~, 59 -)

```
- getAlpha(): float  [API 23+]
- getAutofillHints(): String[]  [API 26+]
- getAutofillId(): AutofillId  [API 26+]
- getAutofillOptions(): CharSequence[]  [API 26+]
- getAutofillType(): int  [API 26+]
- getAutofillValue(): AutofillValue  [API 26+]
- getChildAt(int): AssistStructure.ViewNode  [API 23+]
- getChildCount(): int  [API 23+]
- getClassName(): String  [API 23+]
- getContentDescription(): CharSequence  [API 23+]
- getElevation(): float  [API 23+]
- getExtras(): Bundle  [API 23+]
- getHeight(): int  [API 23+]
- getHint(): String  [API 23+]
- getHintIdEntry(): String  [API 30+]
- getHtmlInfo(): ViewStructure.HtmlInfo  [API 26+]
- getId(): int  [API 23+]
- getIdEntry(): String  [API 23+]
- getIdPackage(): String  [API 23+]
- getIdType(): String  [API 23+]
- getImportantForAutofill(): int  [API 28+]
- getInputType(): int  [API 26+]
- getLeft(): int  [API 23+]
- getLocaleList(): LocaleList  [API 26+]
- getMaxTextEms(): int  [API 28+]
- getMaxTextLength(): int  [API 28+]
- getMinTextEms(): int  [API 28+]
- getReceiveContentMimeTypes(): String[]  [API 31+]
- getScrollX(): int  [API 23+]
- getScrollY(): int  [API 23+]
- getText(): CharSequence  [API 23+]
- getTextBackgroundColor(): int  [API 23+]
- getTextColor(): int  [API 23+]
- getTextIdEntry(): String  [API 28+]
- getTextLineBaselines(): int[]  [API 23+]
- getTextLineCharOffsets(): int[]  [API 23+]
- getTextSelectionEnd(): int  [API 23+]
- getTextSelectionStart(): int  [API 23+]
- getTextSize(): float  [API 23+]
- getTextStyle(): int  [API 23+]
- getTop(): int  [API 23+]
- getTransformation(): Matrix  [API 23+]
- getVisibility(): int  [API 23+]
- getWebDomain(): String  [API 26+]
- getWebScheme(): String  [API 28+]
- getWidth(): int  [API 23+]
- isAccessibilityFocused(): boolean  [API 23+]
- isActivated(): boolean  [API 23+]
- isAssistBlocked(): boolean  [API 23+]
- isCheckable(): boolean  [API 23+]
- isChecked(): boolean  [API 23+]
- isClickable(): boolean  [API 23+]
- isContextClickable(): boolean  [API 23+]
- isEnabled(): boolean  [API 23+]
- isFocusable(): boolean  [API 23+]
- isFocused(): boolean  [API 23+]
- isLongClickable(): boolean  [API 23+]
- isOpaque(): boolean  [API 26+]
- isSelected(): boolean  [API 23+]
```

## AssistStructure.WindowNode — declaration; API 23+ (0 +, 0 *, 0 ~, 7 -)

```
- getDisplayId(): int  [API 23+]
- getHeight(): int  [API 23+]
- getLeft(): int  [API 23+]
- getRootViewNode(): AssistStructure.ViewNode  [API 23+]
- getTitle(): CharSequence  [API 23+]
- getTop(): int  [API 23+]
- getWidth(): int  [API 23+]
```


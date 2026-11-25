# android.view.translation

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## TranslationCapability — declaration; API 31+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- getSourceSpec(): TranslationSpec  [API 31+]
- getState(): int  [API 31+]
- getSupportedTranslationFlags(): int  [API 31+]
- getTargetSpec(): TranslationSpec  [API 31+]
- isUiTranslationEnabled(): boolean  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## TranslationContext — declaration; API 31+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* getSourceSpec(): TranslationSpec  [API 31+]
* getTargetSpec(): TranslationSpec  [API 31+]
* getTranslationFlags(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## TranslationContext.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(TranslationSpec, TranslationSpec)  [API 31+]
* build(): TranslationContext  [API 31+]
* setTranslationFlags(int): TranslationContext.Builder  [API 31+]
```

## TranslationManager — framework; API 31+ (0 +, 0 *, 0 ~, 5 -)

```
- addOnDeviceTranslationCapabilityUpdateListener(Executor, Consumer): void  [API 31+]
- createOnDeviceTranslator(TranslationContext, Executor, Consumer): void  [API 31+]
- getOnDeviceTranslationCapabilities(int, int): Set  [API 31+]
- getOnDeviceTranslationSettingsActivityIntent(): PendingIntent  [API 31+]
- removeOnDeviceTranslationCapabilityUpdateListener(Consumer): void  [API 31+]
```

## TranslationRequest — declaration; API 31+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* getFlags(): int  [API 31+]
* getTranslationRequestValues(): List  [API 31+]
* getViewTranslationRequests(): List  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## TranslationRequest.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* build(): TranslationRequest  [API 31+]
* setFlags(int): TranslationRequest.Builder  [API 31+]
* setTranslationRequestValues(List): TranslationRequest.Builder  [API 31+]
* setViewTranslationRequests(List): TranslationRequest.Builder  [API 31+]
```

## TranslationRequestValue — declaration; API 31+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
- forText(CharSequence): TranslationRequestValue [static]  [API 31+]
- getText(): CharSequence  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## TranslationResponse — declaration; API 31+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* getTranslationResponseValues(): SparseArray  [API 31+]
* getTranslationStatus(): int  [API 31+]
* getViewTranslationResponses(): SparseArray  [API 31+]
* isFinalResponse(): boolean  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## TranslationResponse.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 2 -)

```
* Builder(int)  [API 31+]
* build(): TranslationResponse  [API 31+]
* setFinalResponse(boolean): TranslationResponse.Builder  [API 31+]
- setTranslationResponseValue(int, TranslationResponseValue): TranslationResponse.Builder  [API 31+]
* setTranslationResponseValues(SparseArray): TranslationResponse.Builder  [API 31+]
- setViewTranslationResponse(int, ViewTranslationResponse): TranslationResponse.Builder  [API 31+]
* setViewTranslationResponses(SparseArray): TranslationResponse.Builder  [API 31+]
```

## TranslationResponseValue — declaration; API 31+ (0 +, 9 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
- forError(): TranslationResponseValue [static]  [API 31+]
* getExtras(): Bundle  [API 31+]
* getStatusCode(): int  [API 31+]
* getText(): CharSequence  [API 31+]
* getTransliteration(): CharSequence  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## TranslationResponseValue.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(int)  [API 31+]
* build(): TranslationResponseValue  [API 31+]
* setExtras(Bundle): TranslationResponseValue.Builder  [API 31+]
* setText(CharSequence): TranslationResponseValue.Builder  [API 31+]
* setTransliteration(CharSequence): TranslationResponseValue.Builder  [API 31+]
```

## TranslationSpec — declaration; API 31+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* TranslationSpec(ULocale, int)  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getDataFormat(): int  [API 31+]
* getLocale(): ULocale  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## Translator — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- destroy(): void  [API 31+]
- isDestroyed(): boolean  [API 31+]
- translate(TranslationRequest, CancellationSignal, Executor, Consumer): void  [API 31+]
```

## UiTranslationManager — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- registerUiTranslationStateCallback(Executor, UiTranslationStateCallback): void  [API 31+]
- unregisterUiTranslationStateCallback(UiTranslationStateCallback): void  [API 31+]
```

## UiTranslationStateCallback — declaration; API 31+ (8 +, 0 *, 0 ~, 0 -)

```
+ onFinished(): void  [API 31+]
+ onFinished(String): void  [API 33+]
+ onPaused(): void  [API 31+]
+ onPaused(String): void  [API 33+]
+ onResumed(ULocale, ULocale): void  [API 31+]
+ onResumed(ULocale, ULocale, String): void  [API 33+]
+ onStarted(ULocale, ULocale): void  [API 31+]
+ onStarted(ULocale, ULocale, String): void  [API 33+]
```

## ViewTranslationCallback — declaration; API 31+ (3 +, 0 *, 0 ~, 0 -)

```
+ onClearTranslation(View): boolean  [API 31+]
+ onHideTranslation(View): boolean  [API 31+]
+ onShowTranslation(View): boolean  [API 31+]
```

## ViewTranslationRequest — declaration; API 31+ (0 +, 1 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
* getAutofillId(): AutofillId  [API 31+]
- getKeys(): Set  [API 31+]
- getValue(String): TranslationRequestValue  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## ViewTranslationRequest.Builder — declaration; API 31+ (0 +, 2 *, 0 ~, 2 -)

```
* Builder(AutofillId)  [API 31+]
- Builder(AutofillId, long)  [API 31+]
* build(): ViewTranslationRequest  [API 31+]
- setValue(String, TranslationRequestValue): ViewTranslationRequest.Builder  [API 31+]
```

## ViewTranslationResponse — declaration; API 31+ (0 +, 1 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
* getAutofillId(): AutofillId  [API 31+]
- getKeys(): Set  [API 31+]
- getValue(String): TranslationResponseValue  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## ViewTranslationResponse.Builder — declaration; API 31+ (0 +, 2 *, 0 ~, 1 -)

```
* Builder(AutofillId)  [API 31+]
* build(): ViewTranslationResponse  [API 31+]
- setValue(String, TranslationResponseValue): ViewTranslationResponse.Builder  [API 31+]
```


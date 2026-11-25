# android.speech

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AlternativeSpan — declaration; API 34+ (0 +, 9 *, 0 ~, 0 -)

```
* AlternativeSpan(int, int, List)  [API 34+]
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getAlternatives(): List  [API 34+]
* getEndPosition(): int  [API 34+]
* getStartPosition(): int  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## AlternativeSpans — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
* AlternativeSpans(List)  [API 34+]
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getSpans(): List  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## ModelDownloadListener — declaration; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ onError(int): void  [API 34+]
+ onProgress(int): void  [API 34+]
+ onScheduled(): void  [API 34+]
+ onSuccess(): void  [API 34+]
```

## RecognitionListener — declaration; API 8+ (12 +, 0 *, 0 ~, 0 -)

```
+ onBeginningOfSpeech(): void  [API 8+]
+ onBufferReceived(byte[]): void  [API 8+]
+ onEndOfSegmentedSession(): void  [API 33+]
+ onEndOfSpeech(): void  [API 8+]
+ onError(int): void  [API 8+]
+ onEvent(int, Bundle): void  [API 8+]
+ onLanguageDetection(Bundle): void  [API 34+]
+ onPartialResults(Bundle): void  [API 8+]
+ onReadyForSpeech(Bundle): void  [API 8+]
+ onResults(Bundle): void  [API 8+]
+ onRmsChanged(float): void  [API 8+]
+ onSegmentResults(Bundle): void  [API 33+]
```

## RecognitionPart — declaration; API 34+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getConfidenceLevel(): int  [API 34+]
* getFormattedText(): String  [API 34+]
* getRawText(): String  [API 34+]
* getTimestampMillis(): long  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## RecognitionPart.Builder — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder(String)  [API 34+]
* build(): RecognitionPart  [API 34+]
* setConfidenceLevel(int): RecognitionPart.Builder  [API 34+]
* setFormattedText(String): RecognitionPart.Builder  [API 34+]
* setRawText(String): RecognitionPart.Builder  [API 34+]
* setTimestampMillis(long): RecognitionPart.Builder  [API 34+]
```

## RecognitionService — declaration; API 8+ (9 +, 0 *, 0 ~, 4 -)

```
- RecognitionService()  [API 8+]
- createContext(ContextParams): Context  [API 8+]
- getMaxConcurrentSessionsCount(): int  [API 34+]
- onBind(Intent): IBinder  [API 8+]
+ onCancel(RecognitionService.Callback): void  [API 8+]
+ onCheckRecognitionSupport(Intent, AttributionSource, RecognitionService.SupportCallback): void  [API 34+]
+ onCheckRecognitionSupport(Intent, RecognitionService.SupportCallback): void  [API 33+]
+ onDestroy(): void  [API 8+]
+ onStartListening(Intent, RecognitionService.Callback): void  [API 8+]
+ onStopListening(RecognitionService.Callback): void  [API 8+]
+ onTriggerModelDownload(Intent): void  [API 33+]
+ onTriggerModelDownload(Intent, AttributionSource): void  [API 34+]
+ onTriggerModelDownload(Intent, AttributionSource, ModelDownloadListener): void  [API 34+]
```

## RecognitionService.Callback — declaration; API 8+ (0 +, 0 *, 0 ~, 13 -)

```
- beginningOfSpeech(): void  [API 8+]
- bufferReceived(byte[]): void  [API 8+]
- endOfSegmentedSession(): void  [API 33+]
- endOfSpeech(): void  [API 8+]
- error(int): void  [API 8+]
- getCallingAttributionSource(): AttributionSource  [API 31+]
- getCallingUid(): int  [API 23+]
- languageDetection(Bundle): void  [API 34+]
- partialResults(Bundle): void  [API 8+]
- readyForSpeech(Bundle): void  [API 8+]
- results(Bundle): void  [API 8+]
- rmsChanged(float): void  [API 8+]
- segmentResults(Bundle): void  [API 33+]
```

## RecognitionService.SupportCallback — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ onError(int): void  [API 33+]
+ onSupportResult(RecognitionSupport): void  [API 33+]
```

## RecognitionSupport — declaration; API 33+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getInstalledOnDeviceLanguages(): List  [API 33+]
* getOnlineLanguages(): List  [API 33+]
* getPendingOnDeviceLanguages(): List  [API 33+]
* getSupportedOnDeviceLanguages(): List  [API 33+]
* hashCode(): int  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## RecognitionSupport.Builder — declaration; API 33+ (0 +, 10 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* addInstalledOnDeviceLanguage(String): RecognitionSupport.Builder  [API 33+]
* addOnlineLanguage(String): RecognitionSupport.Builder  [API 33+]
* addPendingOnDeviceLanguage(String): RecognitionSupport.Builder  [API 33+]
* addSupportedOnDeviceLanguage(String): RecognitionSupport.Builder  [API 33+]
* build(): RecognitionSupport  [API 33+]
* setInstalledOnDeviceLanguages(List): RecognitionSupport.Builder  [API 33+]
* setOnlineLanguages(List): RecognitionSupport.Builder  [API 33+]
* setPendingOnDeviceLanguages(List): RecognitionSupport.Builder  [API 33+]
* setSupportedOnDeviceLanguages(List): RecognitionSupport.Builder  [API 33+]
```

## RecognitionSupportCallback — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ onError(int): void  [API 33+]
+ onSupportResult(RecognitionSupport): void  [API 33+]
```

## RecognizerIntent — declaration; API 3+ (0 +, 0 *, 0 ~, 1 -)

```
- getVoiceDetailsIntent(Context): Intent [static]  [API 8+]
```

## RecognizerResultsIntent — declaration; API 10+ (0 +, 0 *, 0 ~, 0 -)

## SpeechRecognizer — declaration; API 8+ (0 +, 0 *, 0 ~, 13 -)

```
- cancel(): void  [API 8+]
- checkRecognitionSupport(Intent, Executor, RecognitionSupportCallback): void  [API 33+]
- createOnDeviceSpeechRecognizer(Context): SpeechRecognizer [static]  [API 31+]
- createSpeechRecognizer(Context): SpeechRecognizer [static]  [API 8+]
- createSpeechRecognizer(Context, ComponentName): SpeechRecognizer [static]  [API 8+]
- destroy(): void  [API 8+]
- isOnDeviceRecognitionAvailable(Context): boolean [static]  [API 31+]
- isRecognitionAvailable(Context): boolean [static]  [API 8+]
- setRecognitionListener(RecognitionListener): void  [API 8+]
- startListening(Intent): void  [API 8+]
- stopListening(): void  [API 8+]
- triggerModelDownload(Intent): void  [API 33+]
- triggerModelDownload(Intent, Executor, ModelDownloadListener): void  [API 34+]
```


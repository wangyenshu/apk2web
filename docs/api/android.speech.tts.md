# android.speech.tts

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## SynthesisCallback — declaration; API 14+ (8 +, 0 *, 0 ~, 1 -)

```
+ audioAvailable(byte[], int, int): int  [API 14+]
+ done(): int  [API 14+]
+ error(): void  [API 14+]
+ error(int): void  [API 21+]
+ getMaxBufferSize(): int  [API 14+]
+ hasFinished(): boolean  [API 21+]
+ hasStarted(): boolean  [API 21+]
- rangeStart(int, int, int): void  [API 26+]
+ start(int, int, int): int  [API 14+]
```

## SynthesisRequest — declaration; API 14+ (0 +, 3 *, 0 ~, 9 -)

```
- SynthesisRequest(CharSequence, Bundle)  [API 21+]
* SynthesisRequest(String, Bundle)  [API 14+]
- getCallerUid(): int  [API 19+]
- getCharSequenceText(): CharSequence  [API 21+]
- getCountry(): String  [API 14+]
- getLanguage(): String  [API 14+]
* getParams(): Bundle  [API 14+]
- getPitch(): int  [API 14+]
- getSpeechRate(): int  [API 14+]
* getText(): String  [API 14+, deprecated in 21]
- getVariant(): String  [API 14+]
- getVoiceName(): String  [API 21+]
```

## TextToSpeech — declaration; API 4+ (0 +, 0 *, 0 ~, 43 -)

```
- TextToSpeech(Context, TextToSpeech.OnInitListener)  [API 4+]
- TextToSpeech(Context, TextToSpeech.OnInitListener, String)  [API 14+]
- addEarcon(String, File): int  [API 21+]
- addEarcon(String, String): int  [API 4+, deprecated in 21]
- addEarcon(String, String, int): int  [API 4+]
- addEarcon(String, Uri): int  [API 31+]
- addSpeech(CharSequence, File): int  [API 21+]
- addSpeech(CharSequence, String, int): int  [API 21+]
- addSpeech(CharSequence, Uri): int  [API 31+]
- addSpeech(String, String): int  [API 4+]
- addSpeech(String, String, int): int  [API 4+]
- areDefaultsEnforced(): boolean  [API 8+, deprecated in 21]
- getAvailableLanguages(): Set  [API 21+]
- getDefaultEngine(): String  [API 8+]
- getDefaultLanguage(): Locale  [API 18+, deprecated in 21]
- getDefaultVoice(): Voice  [API 21+]
- getEngines(): List  [API 14+]
- getFeatures(Locale): Set  [API 15+, deprecated in 21]
- getLanguage(): Locale  [API 4+, deprecated in 21]
- getMaxSpeechInputLength(): int [static]  [API 18+]
- getVoice(): Voice  [API 21+]
- getVoices(): Set  [API 21+]
- isLanguageAvailable(Locale): int  [API 4+]
- isSpeaking(): boolean  [API 4+]
- playEarcon(String, int, Bundle, String): int  [API 21+]
- playEarcon(String, int, HashMap): int  [API 4+, deprecated in 21]
- playSilence(long, int, HashMap): int  [API 4+, deprecated in 21]
- playSilentUtterance(long, int, String): int  [API 21+]
- setAudioAttributes(AudioAttributes): int  [API 21+]
- setEngineByPackageName(String): int  [API 8+, deprecated in 15]
- setLanguage(Locale): int  [API 4+]
- setOnUtteranceCompletedListener(TextToSpeech.OnUtteranceCompletedListener): int  [API 4+, deprecated in 15]
- setOnUtteranceProgressListener(UtteranceProgressListener): int  [API 15+]
- setPitch(float): int  [API 4+]
- setSpeechRate(float): int  [API 4+]
- setVoice(Voice): int  [API 21+]
- shutdown(): void  [API 4+]
- speak(CharSequence, int, Bundle, String): int  [API 21+]
- speak(String, int, HashMap): int  [API 4+, deprecated in 21]
- stop(): int  [API 4+]
- synthesizeToFile(CharSequence, Bundle, File, String): int  [API 21+]
- synthesizeToFile(CharSequence, Bundle, ParcelFileDescriptor, String): int  [API 30+]
- synthesizeToFile(String, HashMap, String): int  [API 4+, deprecated in 21]
```

## TextToSpeech.Engine — declaration; API 4+ (0 +, 0 *, 0 ~, 1 -)

```
- Engine(TextToSpeech)  [API 4+]
```

## TextToSpeech.EngineInfo — declaration; API 14+ (0 +, 0 *, 0 ~, 2 -)

```
- EngineInfo()  [API 14+]
+ icon [field]  [API 14+]
+ label [field]  [API 14+]
+ name [field]  [API 14+]
- toString(): String  [API 14+]
```

## TextToSpeech.OnInitListener — declaration; API 4+ (1 +, 0 *, 0 ~, 0 -)

```
+ onInit(int): void  [API 4+]
```

## TextToSpeech.OnUtteranceCompletedListener — declaration; API 4+, deprecated in 18 (1 +, 0 *, 0 ~, 0 -)

```
+ onUtteranceCompleted(String): void  [API 4+]
```

## TextToSpeechService — declaration; API 14+ (7 +, 0 *, 0 ~, 7 -)

```
- TextToSpeechService()  [API 14+]
- onBind(Intent): IBinder  [API 14+]
+ onCreate(): void  [API 14+]
+ onDestroy(): void  [API 14+]
- onGetDefaultVoiceNameFor(String, String, String): String  [API 21+]
- onGetFeaturesForLanguage(String, String, String): Set  [API 15+]
+ onGetLanguage(): String[]  [API 14+]
- onGetVoices(): List  [API 21+]
+ onIsLanguageAvailable(String, String, String): int  [API 14+]
- onIsValidVoiceName(String): int  [API 21+]
+ onLoadLanguage(String, String, String): int  [API 14+]
- onLoadVoice(String): int  [API 21+]
+ onStop(): void  [API 14+]
+ onSynthesizeText(SynthesisRequest, SynthesisCallback): void  [API 14+]
```

## UtteranceProgressListener — declaration; API 15+ (9 +, 0 *, 0 ~, 0 -)

```
+ UtteranceProgressListener()  [API 15+]
+ onAudioAvailable(String, byte[]): void  [API 24+]
+ onBeginSynthesis(String, int, int, int): void  [API 24+]
+ onDone(String): void  [API 15+]
+ onError(String): void  [API 15+, deprecated in 21]
+ onError(String, int): void  [API 21+]
+ onRangeStart(String, int, int, int): void  [API 26+]
+ onStart(String): void  [API 15+]
+ onStop(String, boolean): void  [API 23+]
```

## Voice — declaration; API 21+ (0 +, 0 *, 0 ~, 12 -)

```
- CREATOR [field]  [API 21+]
- Voice(String, Locale, int, int, boolean, Set)  [API 21+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
- getFeatures(): Set  [API 21+]
- getLatency(): int  [API 21+]
- getLocale(): Locale  [API 21+]
- getName(): String  [API 21+]
- getQuality(): int  [API 21+]
- hashCode(): int  [API 21+]
- isNetworkConnectionRequired(): boolean  [API 21+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```


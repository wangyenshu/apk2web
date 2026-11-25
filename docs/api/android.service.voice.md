# android.service.voice

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AlwaysOnHotwordDetector — declaration; API 21-30, removed in 31 (0 +, 0 *, 0 ~, 10 -)

```
- createEnrollIntent(): Intent  [API 21-30, removed in 31]
- createReEnrollIntent(): Intent  [API 21-30, removed in 31]
- createUnEnrollIntent(): Intent  [API 21-30, removed in 31]
- getParameter(int): int  [API 30-30, removed in 31]
- getSupportedAudioCapabilities(): int  [API 30-30, removed in 31]
- getSupportedRecognitionModes(): int  [API 21-30, removed in 31]
- queryParameter(int): AlwaysOnHotwordDetector.ModelParamRange  [API 30-30, removed in 31]
- setParameter(int, int): int  [API 30-30, removed in 31]
- startRecognition(int): boolean  [API 21-30, removed in 31]
- stopRecognition(): boolean  [API 21-30, removed in 31]
```

## AlwaysOnHotwordDetector.Callback — declaration; API 21-30, removed in 31 (6 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 21-30, removed in 31]
+ onAvailabilityChanged(int): void  [API 21-30, removed in 31]
+ onDetected(AlwaysOnHotwordDetector.EventPayload): void  [API 21-30, removed in 31]
+ onError(): void  [API 21-30, removed in 31]
+ onRecognitionPaused(): void  [API 21-30, removed in 31]
+ onRecognitionResumed(): void  [API 21-30, removed in 31]
```

## AlwaysOnHotwordDetector.EventPayload — declaration; API 21-30, removed in 31 (0 +, 0 *, 0 ~, 2 -)

```
- getCaptureAudioFormat(): AudioFormat  [API 21-30, removed in 31]
- getTriggerAudio(): byte[]  [API 21-30, removed in 31]
```

## AlwaysOnHotwordDetector.ModelParamRange — declaration; API 30-30, removed in 31 (0 +, 0 *, 0 ~, 2 -)

```
- getEnd(): int  [API 30-30, removed in 31]
- getStart(): int  [API 30-30, removed in 31]
```

## VisibleActivityInfo — declaration; API 32+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 32+]
- describeContents(): int  [API 32+]
- equals(Object): boolean  [API 32+]
- getActivityId(): VoiceInteractionSession.ActivityId  [API 32+]
- hashCode(): int  [API 32+]
- toString(): String  [API 32+]
- writeToParcel(Parcel, int): void  [API 32+]
```

## VoiceInteractionService — declaration; API 21+ (0 +, 0 *, 0 ~, 16 -)

```
- VoiceInteractionService()  [API 21+]
- createAlwaysOnHotwordDetector(String, Locale, AlwaysOnHotwordDetector.Callback): AlwaysOnHotwordDetector  [API 21-30, removed in 31]
- dump(FileDescriptor, PrintWriter, String[]): void  [API 21+]
- getDisabledShowContext(): int  [API 23+]
- isActiveService(Context, ComponentName): boolean [static]  [API 21+]
- onBind(Intent): IBinder  [API 21+]
- onGetSupportedVoiceActions(Set): Set  [API 29+]
- onLaunchVoiceAssistFromKeyguard(): void  [API 23+]
- onPrepareToShowSession(Bundle, int): void  [API 34+]
- onReady(): void  [API 21+]
- onShowSessionFailed(Bundle): void  [API 34+]
- onShutdown(): void  [API 21+]
- setDisabledShowContext(int): void  [API 23+]
- setUiHints(Bundle): void  [API 29+]
- showSession(Bundle, int): void  [API 23+]
- startSession(Bundle): void  [API 21-22, removed in 23]
```

## VoiceInteractionSession — declaration; API 21+ (0 +, 0 *, 0 ~, 57 -)

```
- VoiceInteractionSession(Context)  [API 21+]
- VoiceInteractionSession(Context, Handler)  [API 21+]
- closeSystemDialogs(): void  [API 23+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 23+]
- finish(): void  [API 21+]
- getContext(): Context  [API 23+]
- getDisabledShowContext(): int  [API 23+]
- getLayoutInflater(): LayoutInflater  [API 23+]
- getUserDisabledShowContext(): int  [API 23+]
- getWindow(): Dialog  [API 23+]
- hide(): void  [API 23+]
- onAssistStructureFailure(Throwable): void  [API 23+]
- onBackPressed(): void  [API 23+]
- onCancelRequest(VoiceInteractionSession.Request): void  [API 23+]
- onCloseSystemDialogs(): void  [API 21+]
- onComputeInsets(VoiceInteractionSession.Insets): void  [API 23+]
- onConfigurationChanged(Configuration): void  [API 21+]
- onCreate(): void  [API 23+]
- onCreate(Bundle): void  [API 21-22, removed in 23]
- onCreateContentView(): View  [API 23+]
- onDestroy(): void  [API 21+]
- onDirectActionsInvalidated(VoiceInteractionSession.ActivityId): void  [API 29+]
- onGetSupportedCommands(String[]): boolean[]  [API 23+]
- onHandleAssist(Bundle, AssistStructure, AssistContent): void  [API 23+, deprecated in 29]
- onHandleAssist(VoiceInteractionSession.AssistState): void  [API 29+]
- onHandleAssistSecondary(Bundle, AssistStructure, AssistContent, int, int): void  [API 24+, deprecated in 29]
- onHandleScreenshot(Bitmap): void  [API 23+]
- onHide(): void  [API 23+]
- onKeyDown(int, KeyEvent): boolean  [API 21+]
- onKeyLongPress(int, KeyEvent): boolean  [API 21+]
- onKeyMultiple(int, int, KeyEvent): boolean  [API 21+]
- onKeyUp(int, KeyEvent): boolean  [API 21+]
- onLockscreenShown(): void  [API 23+]
- onLowMemory(): void  [API 21+]
- onPrepareShow(Bundle, int): void  [API 26+]
- onRequestAbortVoice(VoiceInteractionSession.AbortVoiceRequest): void  [API 23+]
- onRequestCommand(VoiceInteractionSession.CommandRequest): void  [API 23+]
- onRequestCompleteVoice(VoiceInteractionSession.CompleteVoiceRequest): void  [API 23+]
- onRequestConfirmation(VoiceInteractionSession.ConfirmationRequest): void  [API 23+]
- onRequestPickOption(VoiceInteractionSession.PickOptionRequest): void  [API 23+]
- onShow(Bundle, int): void  [API 23+]
- onTaskFinished(Intent, int): void  [API 23+]
- onTaskStarted(Intent, int): void  [API 23+]
- onTrimMemory(int): void  [API 21+]
- performDirectAction(DirectAction, Bundle, CancellationSignal, Executor, Consumer): void  [API 29+]
- registerVisibleActivityCallback(Executor, VoiceInteractionSession.VisibleActivityCallback): void  [API 32+]
- requestDirectActions(VoiceInteractionSession.ActivityId, CancellationSignal, Executor, Consumer): void  [API 29+]
- setContentView(View): void  [API 21+]
- setDisabledShowContext(int): void  [API 23+]
- setKeepAwake(boolean): void  [API 23+]
- setTheme(int): void  [API 23+]
- setUiEnabled(boolean): void  [API 26+]
- show(Bundle, int): void  [API 23+]
- startAssistantActivity(Intent): void  [API 26+]
- startAssistantActivity(Intent, Bundle): void  [API 34+]
- startVoiceActivity(Intent): void  [API 23+]
- unregisterVisibleActivityCallback(VoiceInteractionSession.VisibleActivityCallback): void  [API 32+]
```

## VoiceInteractionSession.AbortVoiceRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 3 -)

```
- getMessage(): CharSequence  [API 23+, deprecated in 23]
- getVoicePrompt(): VoiceInteractor.Prompt  [API 23+]
- sendAbortResult(Bundle): void  [API 23+]
```

## VoiceInteractionSession.ActivityId — declaration; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
- equals(Object): boolean  [API 29+]
- hashCode(): int  [API 29+]
```

## VoiceInteractionSession.AssistState — declaration; API 29+ (0 +, 0 *, 0 ~, 7 -)

```
- getActivityId(): VoiceInteractionSession.ActivityId  [API 29+]
- getAssistContent(): AssistContent  [API 29+]
- getAssistData(): Bundle  [API 29+]
- getAssistStructure(): AssistStructure  [API 29+]
- getCount(): int  [API 29+]
- getIndex(): int  [API 29+]
- isFocused(): boolean  [API 29+]
```

## VoiceInteractionSession.CommandRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 3 -)

```
- getCommand(): String  [API 23+]
- sendIntermediateResult(Bundle): void  [API 23+]
- sendResult(Bundle): void  [API 23+]
```

## VoiceInteractionSession.CompleteVoiceRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 3 -)

```
- getMessage(): CharSequence  [API 23+, deprecated in 23]
- getVoicePrompt(): VoiceInteractor.Prompt  [API 23+]
- sendCompleteResult(Bundle): void  [API 23+]
```

## VoiceInteractionSession.ConfirmationRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 3 -)

```
- getPrompt(): CharSequence  [API 23+, deprecated in 23]
- getVoicePrompt(): VoiceInteractor.Prompt  [API 23+]
- sendConfirmationResult(boolean, Bundle): void  [API 23+]
```

## VoiceInteractionSession.Insets — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ Insets()  [API 23+]
- contentInsets [field]  [API 23+]
+ touchableInsets [field]  [API 23+]
- touchableRegion [field]  [API 23+]
```

## VoiceInteractionSession.PickOptionRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 5 -)

```
- getOptions(): VoiceInteractor.PickOptionRequest.Option[]  [API 23+]
- getPrompt(): CharSequence  [API 23+, deprecated in 23]
- getVoicePrompt(): VoiceInteractor.Prompt  [API 23+]
- sendIntermediatePickOptionResult(VoiceInteractor.PickOptionRequest.Option[], Bundle): void  [API 23+]
- sendPickOptionResult(VoiceInteractor.PickOptionRequest.Option[], Bundle): void  [API 23+]
```

## VoiceInteractionSession.Request — declaration; API 23+ (0 +, 0 *, 0 ~, 6 -)

```
- cancel(): void  [API 23+]
- getCallingPackage(): String  [API 23+]
- getCallingUid(): int  [API 23+]
- getExtras(): Bundle  [API 23+]
- isActive(): boolean  [API 23+]
- toString(): String  [API 23+]
```

## VoiceInteractionSession.VisibleActivityCallback — declaration; API 32+ (2 +, 0 *, 0 ~, 0 -)

```
+ onInvisible(VoiceInteractionSession.ActivityId): void  [API 32+]
+ onVisible(VisibleActivityInfo): void  [API 32+]
```

## VoiceInteractionSessionService — declaration; API 21+ (5 +, 0 *, 0 ~, 3 -)

```
- VoiceInteractionSessionService()  [API 21+]
- dump(FileDescriptor, PrintWriter, String[]): void  [API 21+]
- onBind(Intent): IBinder  [API 21+]
+ onConfigurationChanged(Configuration): void  [API 21+]
+ onCreate(): void  [API 21+]
+ onLowMemory(): void  [API 21+]
+ onNewSession(Bundle): VoiceInteractionSession  [API 21+]
+ onTrimMemory(int): void  [API 21+]
```


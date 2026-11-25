# android.media.tv.interactive

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AppLinkInfo — declaration; API 33+ (5 +, 0 *, 0 ~, 1 -)

```
- AppLinkInfo(String, String, String)  [API 33+]
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
+ getComponentName(): ComponentName  [API 33+]
+ getUri(): Uri  [API 33+]
+ toString(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TvInteractiveAppManager — framework; API 33+ (7 +, 0 *, 0 ~, 0 -)

```
+ getAppLinkInfoList(): List  [API 34+]
+ getTvInteractiveAppServiceList(): List  [API 33+]
+ registerAppLinkInfo(String, AppLinkInfo): void  [API 33+]
+ registerCallback(Executor, TvInteractiveAppManager.TvInteractiveAppCallback): void  [API 33+]
+ sendAppLinkCommand(String, Bundle): void  [API 33+]
+ unregisterAppLinkInfo(String, AppLinkInfo): void  [API 33+]
+ unregisterCallback(TvInteractiveAppManager.TvInteractiveAppCallback): void  [API 33+]
```

## TvInteractiveAppManager.TvInteractiveAppCallback — framework; API 33+ (5 +, 0 *, 0 ~, 0 -)

```
+ TvInteractiveAppCallback()  [API 33+]
+ onInteractiveAppServiceAdded(String): void  [API 33+]
+ onInteractiveAppServiceRemoved(String): void  [API 33+]
+ onInteractiveAppServiceUpdated(String): void  [API 33+]
+ onTvInteractiveAppServiceStateChanged(String, int, int, int): void  [API 33+]
```

## TvInteractiveAppService — declaration; API 33+ (6 +, 0 *, 0 ~, 1 -)

```
- TvInteractiveAppService()  [API 33+]
+ notifyStateChanged(int, int, int): void  [API 33+]
+ onAppLinkCommand(Bundle): void  [API 33+]
+ onBind(Intent): IBinder  [API 33+]
+ onCreateSession(String, int): TvInteractiveAppService.Session  [API 33+]
+ onRegisterAppLinkInfo(AppLinkInfo): void  [API 33+]
+ onUnregisterAppLinkInfo(AppLinkInfo): void  [API 33+]
```

## TvInteractiveAppService.Session — declaration; API 33+ (82 +, 0 *, 0 ~, 1 -)

```
- Session(Context)  [API 33+]
+ isMediaViewEnabled(): boolean  [API 33+]
+ layoutSurface(int, int, int, int): void  [API 33+]
+ notifyAdBufferReady(AdBuffer): void  [API 34+]
+ notifyBiInteractiveAppCreated(Uri, String): void  [API 33+]
+ notifySessionStateChanged(int, int): void  [API 33+]
+ notifyTeletextAppStateChanged(int): void  [API 33+]
+ onAdBufferConsumed(AdBuffer): void  [API 34+]
+ onAdResponse(AdResponse): void  [API 33+]
+ onAvailableSpeeds(float[]): void  [API 34+]
+ onBroadcastInfoResponse(BroadcastInfoResponse): void  [API 33+]
+ onContentAllowed(): void  [API 33+]
+ onContentBlocked(TvContentRating): void  [API 33+]
+ onCreateBiInteractiveAppRequest(Uri, Bundle): void  [API 33+]
+ onCreateMediaView(): View  [API 33+]
+ onCurrentChannelLcn(int): void  [API 33+]
+ onCurrentChannelUri(Uri): void  [API 33+]
+ onCurrentTvInputId(String): void  [API 33+]
+ onCurrentVideoBounds(Rect): void  [API 34+]
+ onDestroyBiInteractiveAppRequest(String): void  [API 33+]
+ onError(String, Bundle): void  [API 33+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 33+]
+ onKeyDown(int, KeyEvent): boolean  [API 33+]
+ onKeyLongPress(int, KeyEvent): boolean  [API 33+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 33+]
+ onKeyUp(int, KeyEvent): boolean  [API 33+]
+ onMediaViewSizeChanged(int, int): void  [API 33+]
+ onRecordingConnectionFailed(String, String): void  [API 34+]
+ onRecordingDisconnected(String, String): void  [API 34+]
+ onRecordingError(String, int): void  [API 34+]
+ onRecordingScheduled(String, String): void  [API 34+]
+ onRecordingStarted(String, String): void  [API 34+]
+ onRecordingStopped(String): void  [API 34+]
+ onRecordingTuned(String, Uri): void  [API 34+]
+ onRelease(): void  [API 33+]
+ onResetInteractiveApp(): void  [API 33+]
+ onSetSurface(Surface): boolean  [API 33+]
+ onSetTeletextAppEnabled(boolean): void  [API 33+]
+ onSignalStrength(int): void  [API 33+]
+ onSigningResult(String, byte[]): void  [API 33+]
+ onStartInteractiveApp(): void  [API 33+]
+ onStopInteractiveApp(): void  [API 33+]
+ onStreamVolume(float): void  [API 33+]
+ onSurfaceChanged(int, int, int): void  [API 33+]
+ onTimeShiftCurrentPositionChanged(String, long): void  [API 34+]
+ onTimeShiftMode(int): void  [API 34+]
+ onTimeShiftPlaybackParams(PlaybackParams): void  [API 34+]
+ onTimeShiftStartPositionChanged(String, long): void  [API 34+]
+ onTimeShiftStatusChanged(String, int): void  [API 34+]
+ onTouchEvent(MotionEvent): boolean  [API 33+]
+ onTrackInfoList(List): void  [API 33+]
+ onTrackSelected(int, String): void  [API 33+]
+ onTrackballEvent(MotionEvent): boolean  [API 33+]
+ onTracksChanged(List): void  [API 33+]
+ onTuned(Uri): void  [API 33+]
+ onTvMessage(int, Bundle): void  [API 34+]
+ onTvRecordingInfo(TvRecordingInfo): void  [API 34+]
+ onTvRecordingInfoList(List): void  [API 34+]
+ onVideoAvailable(): void  [API 33+]
+ onVideoUnavailable(int): void  [API 33+]
+ removeBroadcastInfo(int): void  [API 33+]
+ requestAd(AdRequest): void  [API 33+]
+ requestAvailableSpeeds(): void  [API 34+]
+ requestBroadcastInfo(BroadcastInfoRequest): void  [API 33+]
+ requestCurrentChannelLcn(): void  [API 33+]
+ requestCurrentChannelUri(): void  [API 33+]
+ requestCurrentTvInputId(): void  [API 33+]
+ requestCurrentVideoBounds(): void  [API 34+]
+ requestScheduleRecording(String, String, Uri, Uri, Bundle): void  [API 34+]
+ requestScheduleRecording(String, String, Uri, long, long, int, Bundle): void  [API 34+]
+ requestSigning(String, String, String, byte[]): void  [API 33+]
+ requestStartRecording(String, Uri): void  [API 34+]
+ requestStopRecording(String): void  [API 34+]
+ requestStreamVolume(): void  [API 33+]
+ requestTimeShiftMode(): void  [API 34+]
+ requestTrackInfoList(): void  [API 33+]
+ requestTvRecordingInfo(String): void  [API 34+]
+ requestTvRecordingInfoList(int): void  [API 34+]
+ sendPlaybackCommandRequest(String, Bundle): void  [API 33+]
+ sendTimeShiftCommandRequest(String, Bundle): void  [API 34+]
+ setMediaViewEnabled(boolean): void  [API 33+]
+ setTvRecordingInfo(String, TvRecordingInfo): void  [API 34+]
+ setVideoBounds(Rect): void  [API 33+]
```

## TvInteractiveAppServiceInfo — declaration; API 33+ (6 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 33+]
- TvInteractiveAppServiceInfo(Context, ComponentName)  [API 33+]
+ describeContents(): int  [API 33+]
+ getCustomSupportedTypes(): List  [API 34+]
+ getId(): String  [API 33+]
+ getServiceInfo(): ServiceInfo  [API 33+]
+ getSupportedTypes(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TvInteractiveAppView — declaration; API 33+ (42 +, 0 *, 0 ~, 3 -)

```
- TvInteractiveAppView(Context)  [API 33+]
- TvInteractiveAppView(Context, AttributeSet)  [API 33+]
- TvInteractiveAppView(Context, AttributeSet, int)  [API 33+]
+ clearCallback(): void  [API 33+]
+ clearOnUnhandledInputEventListener(): void  [API 33+]
+ createBiInteractiveApp(Uri, Bundle): void  [API 33+]
+ destroyBiInteractiveApp(String): void  [API 33+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 33+]
+ dispatchUnhandledInputEvent(InputEvent): boolean  [API 33+]
+ getOnUnhandledInputEventListener(): TvInteractiveAppView.OnUnhandledInputEventListener  [API 33+]
+ notifyError(String, Bundle): void  [API 33+]
+ notifyRecordingScheduled(String, String): void  [API 34+]
+ notifyRecordingStarted(String, String): void  [API 34+]
+ notifyRecordingStopped(String): void  [API 34+]
+ notifyTimeShiftCurrentPositionChanged(String, long): void  [API 34+]
+ notifyTimeShiftPlaybackParams(PlaybackParams): void  [API 34+]
+ notifyTimeShiftStartPositionChanged(String, long): void  [API 34+]
+ notifyTimeShiftStatusChanged(String, int): void  [API 34+]
+ notifyTvMessage(int, Bundle): void  [API 34+]
+ onAttachedToWindow(): void  [API 33+]
+ onDetachedFromWindow(): void  [API 33+]
+ onLayout(boolean, int, int, int, int): void  [API 33+]
+ onMeasure(int, int): void  [API 33+]
+ onUnhandledInputEvent(InputEvent): boolean  [API 33+]
+ onVisibilityChanged(View, int): void  [API 33+]
+ prepareInteractiveApp(String, int): void  [API 33+]
+ reset(): void  [API 33+]
+ resetInteractiveApp(): void  [API 33+]
+ sendAvailableSpeeds(float[]): void  [API 34+]
+ sendCurrentChannelLcn(int): void  [API 33+]
+ sendCurrentChannelUri(Uri): void  [API 33+]
+ sendCurrentTvInputId(String): void  [API 33+]
+ sendCurrentVideoBounds(Rect): void  [API 34+]
+ sendSigningResult(String, byte[]): void  [API 33+]
+ sendStreamVolume(float): void  [API 33+]
+ sendTimeShiftMode(int): void  [API 34+]
+ sendTrackInfoList(List): void  [API 33+]
+ sendTvRecordingInfo(TvRecordingInfo): void  [API 34+]
+ sendTvRecordingInfoList(List): void  [API 34+]
+ setCallback(Executor, TvInteractiveAppView.TvInteractiveAppCallback): void  [API 33+]
+ setOnUnhandledInputEventListener(Executor, TvInteractiveAppView.OnUnhandledInputEventListener): void  [API 33+]
+ setTeletextAppEnabled(boolean): void  [API 33+]
+ setTvView(TvView): int  [API 33+]
+ startInteractiveApp(): void  [API 33+]
+ stopInteractiveApp(): void  [API 33+]
```

## TvInteractiveAppView.OnUnhandledInputEventListener — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ onUnhandledInputEvent(InputEvent): boolean  [API 33+]
```

## TvInteractiveAppView.TvInteractiveAppCallback — declaration; API 33+ (23 +, 0 *, 0 ~, 0 -)

```
+ TvInteractiveAppCallback()  [API 33+]
+ onBiInteractiveAppCreated(String, Uri, String): void  [API 33+]
+ onPlaybackCommandRequest(String, String, Bundle): void  [API 33+]
+ onRequestAvailableSpeeds(String): void  [API 34+]
+ onRequestCurrentChannelLcn(String): void  [API 33+]
+ onRequestCurrentChannelUri(String): void  [API 33+]
+ onRequestCurrentTvInputId(String): void  [API 33+]
+ onRequestCurrentVideoBounds(String): void  [API 34+]
+ onRequestScheduleRecording(String, String, String, Uri, Uri, Bundle): void  [API 34+]
+ onRequestScheduleRecording(String, String, String, Uri, long, long, int, Bundle): void  [API 34+]
+ onRequestSigning(String, String, String, String, byte[]): void  [API 33+]
+ onRequestStartRecording(String, String, Uri): void  [API 34+]
+ onRequestStopRecording(String, String): void  [API 34+]
+ onRequestStreamVolume(String): void  [API 33+]
+ onRequestTimeShiftMode(String): void  [API 34+]
+ onRequestTrackInfoList(String): void  [API 33+]
+ onRequestTvRecordingInfo(String, String): void  [API 34+]
+ onRequestTvRecordingInfoList(String, int): void  [API 34+]
+ onSetTvRecordingInfo(String, String, TvRecordingInfo): void  [API 34+]
+ onSetVideoBounds(String, Rect): void  [API 33+]
+ onStateChanged(String, int, int): void  [API 33+]
+ onTeletextAppStateChanged(String, int): void  [API 33+]
+ onTimeShiftCommandRequest(String, String, Bundle): void  [API 34+]
```


# android.media.tv

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AdBuffer — declaration; API 34+ (9 +, 0 *, 0 ~, 1 -)

```
- AdBuffer(int, String, SharedMemory, int, int, long, int)  [API 34+]
- CREATOR [field]  [API 34+]
+ describeContents(): int  [API 34+]
+ getFlags(): int  [API 34+]
+ getId(): int  [API 34+]
+ getLength(): int  [API 34+]
+ getMimeType(): String  [API 34+]
+ getOffset(): int  [API 34+]
+ getPresentationTimeUs(): long  [API 34+]
+ getSharedMemory(): SharedMemory  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## AdRequest — declaration; API 33+ (11 +, 0 *, 0 ~, 2 -)

```
- AdRequest(int, int, ParcelFileDescriptor, long, long, long, String, Bundle)  [API 33+]
- AdRequest(int, int, Uri, long, long, long, Bundle)  [API 34+]
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
+ getEchoIntervalMillis(): long  [API 33+]
+ getFileDescriptor(): ParcelFileDescriptor  [API 33+]
+ getId(): int  [API 33+]
+ getMediaFileType(): String  [API 33+]
+ getMetadata(): Bundle  [API 33+]
+ getRequestType(): int  [API 33+]
+ getStartTimeMillis(): long  [API 33+]
+ getStopTimeMillis(): long  [API 33+]
+ getUri(): Uri  [API 34+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## AdResponse — declaration; API 33+ (5 +, 0 *, 0 ~, 1 -)

```
- AdResponse(int, int, long)  [API 33+]
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
+ getElapsedTimeMillis(): long  [API 33+]
+ getId(): int  [API 33+]
+ getResponseType(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## AitInfo — declaration; API 33+ (0 +, 6 *, 0 ~, 0 -)

```
* AitInfo(int, int)  [API 33+]
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* getType(): int  [API 33+]
* getVersion(): int  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## BroadcastInfoRequest — declaration; API 33+ (3 +, 2 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
* getOption(): int  [API 33+]
* getRequestId(): int  [API 33+]
+ getType(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## BroadcastInfoResponse — declaration; API 33+ (3 +, 3 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
* getRequestId(): int  [API 33+]
* getResponseResult(): int  [API 33+]
* getSequence(): int  [API 33+]
+ getType(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## CommandRequest — declaration; API 33+ (2 +, 5 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* CommandRequest(int, int, String, String, String, String)  [API 33+]
+ describeContents(): int  [API 33+]
* getArgumentType(): String  [API 33+]
* getArguments(): String  [API 33+]
* getName(): String  [API 33+]
* getNamespace(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## CommandResponse — declaration; API 33+ (2 +, 3 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* CommandResponse(int, int, int, String, String)  [API 33+]
+ describeContents(): int  [API 33+]
* getResponse(): String  [API 33+]
* getResponseType(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## DsmccRequest — declaration; API 33+ (2 +, 2 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* DsmccRequest(int, int, Uri)  [API 33+]
+ describeContents(): int  [API 33+]
* getUri(): Uri  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## DsmccResponse — declaration; API 33+ (6 +, 2 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 33+]
* DsmccResponse(int, int, int, ParcelFileDescriptor)  [API 33+]
- DsmccResponse(int, int, int, boolean, List)  [API 33+]
- DsmccResponse(int, int, int, int[], String[])  [API 33+]
+ describeContents(): int  [API 33+]
+ getBiopMessageType(): String  [API 33+]
+ getChildList(): List  [API 33+]
* getFile(): ParcelFileDescriptor  [API 33+]
+ getStreamEventIds(): int[]  [API 33+]
+ getStreamEventNames(): String[]  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## PesRequest — declaration; API 33+ (2 +, 3 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* PesRequest(int, int, int, int)  [API 33+]
+ describeContents(): int  [API 33+]
* getStreamId(): int  [API 33+]
* getTsPid(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## PesResponse — declaration; API 33+ (2 +, 2 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* PesResponse(int, int, int, String)  [API 33+]
+ describeContents(): int  [API 33+]
* getSharedFilterToken(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## SectionRequest — declaration; API 33+ (2 +, 4 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* SectionRequest(int, int, int, int, int)  [API 33+]
+ describeContents(): int  [API 33+]
* getTableId(): int  [API 33+]
* getTsPid(): int  [API 33+]
* getVersion(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## SectionResponse — declaration; API 33+ (2 +, 4 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* SectionResponse(int, int, int, int, int, Bundle)  [API 33+]
+ describeContents(): int  [API 33+]
* getSessionData(): Bundle  [API 33+]
* getSessionId(): int  [API 33+]
* getVersion(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## StreamEventRequest — declaration; API 33+ (2 +, 3 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* StreamEventRequest(int, int, Uri, String)  [API 33+]
+ describeContents(): int  [API 33+]
* getEventName(): String  [API 33+]
* getTargetUri(): Uri  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## StreamEventResponse — declaration; API 33+ (2 +, 4 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* StreamEventResponse(int, int, int, int, long, byte[])  [API 33+]
+ describeContents(): int  [API 33+]
* getData(): byte[]  [API 33+]
* getEventId(): int  [API 33+]
* getNptMillis(): long  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TableRequest — declaration; API 33+ (2 +, 4 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* TableRequest(int, int, int, int, int)  [API 33+]
+ describeContents(): int  [API 33+]
* getTableId(): int  [API 33+]
* getTableName(): int  [API 33+]
* getVersion(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TableResponse — declaration; API 33+ (2 +, 6 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* TableResponse(int, int, int, Uri, int, int)  [API 33+, deprecated in 34]
+ describeContents(): int  [API 33+]
* getSize(): int  [API 33+]
* getTableByteArray(): byte[]  [API 34+]
* getTableSharedMemory(): SharedMemory  [API 34+]
* getTableUri(): Uri  [API 33+]
* getVersion(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TableResponse.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(int, int, int, int, int)  [API 34+]
* build(): TableResponse  [API 34+]
* setTableByteArray(byte[]): TableResponse.Builder  [API 34+]
* setTableSharedMemory(SharedMemory): TableResponse.Builder  [API 34+]
* setTableUri(Uri): TableResponse.Builder  [API 34+]
```

## TimelineRequest — declaration; API 33+ (2 +, 4 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* TimelineRequest(int, int, int)  [API 33+]
* TimelineRequest(int, int, int, String)  [API 34+]
+ describeContents(): int  [API 33+]
* getIntervalMillis(): int  [API 33+]
* getSelector(): String  [API 34+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TimelineResponse — declaration; API 33+ (7 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 33+]
- TimelineResponse(int, int, int, String, int, int, long, long)  [API 33+]
+ describeContents(): int  [API 33+]
+ getSelector(): Uri  [API 33+]
+ getTicks(): long  [API 33+]
+ getUnitsPerSecond(): int  [API 33+]
+ getUnitsPerTick(): int  [API 33+]
+ getWallClock(): long  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TsRequest — declaration; API 33+ (2 +, 2 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* TsRequest(int, int, int)  [API 33+]
+ describeContents(): int  [API 33+]
* getTsPid(): int  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TsResponse — declaration; API 33+ (2 +, 2 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
* TsResponse(int, int, int, String)  [API 33+]
+ describeContents(): int  [API 33+]
* getSharedFilterToken(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## TvContentRating — declaration; API 21+ (10 +, 0 *, 0 ~, 0 -)

```
- UNRATED [field]  [API 23+]
+ contains(TvContentRating): boolean  [API 24+]
+ createRating(String, String, String, String[]): TvContentRating [static]  [API 21+]
+ equals(Object): boolean  [API 21+]
+ flattenToString(): String  [API 21+]
+ getDomain(): String  [API 21+]
+ getMainRating(): String  [API 21+]
+ getRatingSystem(): String  [API 21+]
+ getSubRatings(): List  [API 21+]
+ hashCode(): int  [API 21+]
+ unflattenFromString(String): TvContentRating [static]  [API 21+]
```

## TvContract — declaration; API 21+ (22 +, 0 *, 0 ~, 0 -)

```
+ buildChannelLogoUri(Uri): Uri [static]  [API 21+]
+ buildChannelLogoUri(long): Uri [static]  [API 21+]
+ buildChannelUri(long): Uri [static]  [API 21+]
+ buildChannelUriForPassthroughInput(String): Uri [static]  [API 21+]
+ buildChannelsUriForInput(String): Uri [static]  [API 21+]
+ buildInputId(ComponentName): String [static]  [API 21+]
+ buildPreviewProgramUri(long): Uri [static]  [API 26+]
+ buildPreviewProgramsUriForChannel(Uri): Uri [static]  [API 26+]
+ buildPreviewProgramsUriForChannel(long): Uri [static]  [API 26+]
+ buildProgramUri(long): Uri [static]  [API 21+]
+ buildProgramsUriForChannel(Uri): Uri [static]  [API 21+]
+ buildProgramsUriForChannel(Uri, long, long): Uri [static]  [API 21+]
+ buildProgramsUriForChannel(long): Uri [static]  [API 21+]
+ buildProgramsUriForChannel(long, long, long): Uri [static]  [API 21+]
+ buildRecordedProgramUri(long): Uri [static]  [API 24+]
+ buildWatchNextProgramUri(long): Uri [static]  [API 26+]
+ isChannelUri(Uri): boolean [static]  [API 24+]
+ isChannelUriForPassthroughInput(Uri): boolean [static]  [API 24+]
+ isChannelUriForTunerInput(Uri): boolean [static]  [API 24+]
+ isProgramUri(Uri): boolean [static]  [API 24+]
+ isRecordedProgramUri(Uri): boolean [static]  [API 27+]
+ requestChannelBrowsable(Context, long): void [static]  [API 26+]
```

## TvContract.BaseTvColumns — declaration; API 21+ (0 +, 0 *, 0 ~, 0 -)

## TvContract.Channels — declaration; API 21+ (1 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 21+]
+ getVideoResolution(String): String [static]  [API 21+]
```

## TvContract.Channels.Logo — declaration; API 21+ (0 +, 0 *, 0 ~, 0 -)

## TvContract.PreviewPrograms — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 26+]
```

## TvContract.Programs — declaration; API 21+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 21+]
```

## TvContract.Programs.Genres — declaration; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ decode(String): String[] [static]  [API 21+]
+ encode(String[]): String [static]  [API 21+]
+ isCanonical(String): boolean [static]  [API 24+]
```

## TvContract.RecordedPrograms — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 24+]
```

## TvContract.WatchNextPrograms — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 26+]
```

## TvInputInfo — declaration; API 21+ (18 +, 2 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 21+]
+ canPauseRecording(): boolean  [API 31+]
+ canRecord(): boolean  [API 24+]
+ createSettingsIntent(): Intent  [API 21+, deprecated in 26]
+ createSetupIntent(): Intent  [API 21+]
+ describeContents(): int  [API 21+]
+ equals(Object): boolean  [API 21+]
* getExtras(): Bundle  [API 24+]
+ getId(): String  [API 21+]
+ getParentId(): String  [API 21+]
+ getServiceInfo(): ServiceInfo  [API 21+]
* getTunerCount(): int  [API 24+]
+ getType(): int  [API 21+]
+ hashCode(): int  [API 21+]
+ isHidden(Context): boolean  [API 24+]
+ isPassthroughInput(): boolean  [API 21+]
+ loadCustomLabel(Context): CharSequence  [API 24+]
+ loadIcon(Context): Drawable  [API 21+]
+ loadLabel(Context): CharSequence  [API 21+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## TvInputInfo.Builder — declaration; API 24+ (2 +, 3 *, 0 ~, 1 -)

```
- Builder(Context, ComponentName)  [API 24+]
* build(): TvInputInfo  [API 24+]
+ setCanPauseRecording(boolean): TvInputInfo.Builder  [API 31+]
+ setCanRecord(boolean): TvInputInfo.Builder  [API 24+]
* setExtras(Bundle): TvInputInfo.Builder  [API 24+]
* setTunerCount(int): TvInputInfo.Builder  [API 24+]
```

## TvInputManager — framework; API 21+ (9 +, 0 *, 0 ~, 0 -)

```
+ getBlockedRatings(): List  [API 28+]
+ getInputState(String): int  [API 21+]
+ getTvInputInfo(String): TvInputInfo  [API 21+]
+ getTvInputList(): List  [API 21+]
+ isParentalControlsEnabled(): boolean  [API 21+]
+ isRatingBlocked(TvContentRating): boolean  [API 21+]
+ registerCallback(TvInputManager.TvInputCallback, Handler): void  [API 21+]
+ unregisterCallback(TvInputManager.TvInputCallback): void  [API 21+]
+ updateTvInputInfo(TvInputInfo): void  [API 24+]
```

## TvInputManager.TvInputCallback — framework; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
+ TvInputCallback()  [API 21+]
+ onInputAdded(String): void  [API 21+]
+ onInputRemoved(String): void  [API 21+]
+ onInputStateChanged(String, int): void  [API 21+]
+ onInputUpdated(String): void  [API 24+]
+ onTvInputInfoUpdated(TvInputInfo): void  [API 24+]
```

## TvInputService — declaration; API 21+ (6 +, 0 *, 0 ~, 1 -)

```
- TvInputService()  [API 21+]
+ onBind(Intent): IBinder  [API 21+]
+ onCreateRecordingSession(String): TvInputService.RecordingSession  [API 24+]
+ onCreateRecordingSession(String, String): TvInputService.RecordingSession  [API 30+]
+ onCreateSession(String): TvInputService.Session  [API 21+]
+ onCreateSession(String, String): TvInputService.Session  [API 30+]
+ onCreateSession(String, String, AttributionSource): TvInputService.Session  [API 34+]
```

## TvInputService.HardwareSession — declaration; API 21+ (4 +, 0 *, 0 ~, 1 -)

```
- HardwareSession(Context)  [API 21+]
+ getHardwareInputId(): String  [API 21+]
+ onHardwareVideoAvailable(): void  [API 21+]
+ onHardwareVideoUnavailable(int): void  [API 21+]
+ onSetSurface(Surface): boolean  [API 21+]
```

## TvInputService.RecordingSession — declaration; API 24+ (12 +, 0 *, 0 ~, 1 -)

```
- RecordingSession(Context)  [API 24+]
+ notifyError(int): void  [API 24+]
+ notifyRecordingStopped(Uri): void  [API 24+]
+ notifyTuned(Uri): void  [API 24+]
+ onAppPrivateCommand(String, Bundle): void  [API 24+]
+ onPauseRecording(Bundle): void  [API 31+]
+ onRelease(): void  [API 24+]
+ onResumeRecording(Bundle): void  [API 31+]
+ onStartRecording(Uri): void  [API 24+]
+ onStartRecording(Uri, Bundle): void  [API 30+]
+ onStopRecording(): void  [API 24+]
+ onTune(Uri): void  [API 24+]
+ onTune(Uri, Bundle): void  [API 24+]
```

## TvInputService.Session — declaration; API 21+ (57 +, 0 *, 0 ~, 1 -)

```
- Session(Context)  [API 21+]
+ layoutSurface(int, int, int, int): void  [API 23+]
+ notifyAdBufferConsumed(AdBuffer): void  [API 34+]
+ notifyAdResponse(AdResponse): void  [API 33+]
+ notifyAitInfoUpdated(AitInfo): void  [API 33+]
+ notifyAudioPresentationChanged(List): void  [API 34+]
+ notifyAudioPresentationSelected(int, int): void  [API 34+]
+ notifyAvailableSpeeds(float[]): void  [API 34+]
+ notifyBroadcastInfoResponse(BroadcastInfoResponse): void  [API 33+]
+ notifyChannelRetuned(Uri): void  [API 21+]
+ notifyContentAllowed(): void  [API 21+]
+ notifyContentBlocked(TvContentRating): void  [API 21+]
+ notifyCueingMessageAvailability(boolean): void  [API 34+]
+ notifySignalStrength(int): void  [API 33+]
+ notifyTimeShiftMode(int): void  [API 34+]
+ notifyTimeShiftStatusChanged(int): void  [API 23+]
+ notifyTrackSelected(int, String): void  [API 21+]
+ notifyTracksChanged(List): void  [API 21+]
+ notifyTuned(Uri): void  [API 33+]
+ notifyTvMessage(int, Bundle): void  [API 34+]
+ notifyVideoAvailable(): void  [API 21+]
+ notifyVideoUnavailable(int): void  [API 21+]
+ onAdBufferReady(AdBuffer): void  [API 34+]
+ onAppPrivateCommand(String, Bundle): void  [API 24+]
+ onCreateOverlayView(): View  [API 21+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 21+]
+ onKeyDown(int, KeyEvent): boolean  [API 21+]
+ onKeyLongPress(int, KeyEvent): boolean  [API 21+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 21+]
+ onKeyUp(int, KeyEvent): boolean  [API 21+]
+ onOverlayViewSizeChanged(int, int): void  [API 23+]
+ onRelease(): void  [API 21+]
+ onRemoveBroadcastInfo(int): void  [API 33+]
+ onRequestAd(AdRequest): void  [API 33+]
+ onRequestBroadcastInfo(BroadcastInfoRequest): void  [API 33+]
+ onSelectAudioPresentation(int, int): boolean  [API 34+]
+ onSelectTrack(int, String): boolean  [API 21+]
+ onSetCaptionEnabled(boolean): void  [API 21+]
+ onSetInteractiveAppNotificationEnabled(boolean): void  [API 33+]
+ onSetStreamVolume(float): void  [API 21+]
+ onSetSurface(Surface): boolean  [API 21+]
+ onSetTvMessageEnabled(int, boolean): void  [API 34+]
+ onSurfaceChanged(int, int, int): void  [API 21+]
+ onTimeShiftGetCurrentPosition(): long  [API 23+]
+ onTimeShiftGetStartPosition(): long  [API 23+]
+ onTimeShiftPause(): void  [API 23+]
+ onTimeShiftPlay(Uri): void  [API 24+]
+ onTimeShiftResume(): void  [API 23+]
+ onTimeShiftSeekTo(long): void  [API 23+]
+ onTimeShiftSetMode(int): void  [API 34+]
+ onTimeShiftSetPlaybackParams(PlaybackParams): void  [API 23+]
+ onTouchEvent(MotionEvent): boolean  [API 21+]
+ onTrackballEvent(MotionEvent): boolean  [API 21+]
+ onTune(Uri): boolean  [API 21+]
+ onTune(Uri, Bundle): boolean  [API 24+]
+ onTvMessage(int, Bundle): void  [API 34+]
+ onUnblockContent(TvContentRating): void  [API 21+]
+ setOverlayViewEnabled(boolean): void  [API 21+]
```

## TvRecordingClient — declaration; API 24+ (12 +, 0 *, 0 ~, 1 -)

```
- TvRecordingClient(Context, String, TvRecordingClient.RecordingCallback, Handler)  [API 24+]
+ pauseRecording(): void  [API 31+]
+ pauseRecording(Bundle): void  [API 31+]
+ release(): void  [API 24+]
+ resumeRecording(): void  [API 31+]
+ resumeRecording(Bundle): void  [API 31+]
+ sendAppPrivateCommand(String, Bundle): void  [API 24+]
+ setTvInteractiveAppView(TvInteractiveAppView, String): void  [API 34+]
+ startRecording(Uri): void  [API 24+]
+ startRecording(Uri, Bundle): void  [API 30+]
+ stopRecording(): void  [API 24+]
+ tune(String, Uri): void  [API 24+]
+ tune(String, Uri, Bundle): void  [API 24+]
```

## TvRecordingClient.RecordingCallback — declaration; API 24+ (6 +, 0 *, 0 ~, 0 -)

```
+ RecordingCallback()  [API 24+]
+ onConnectionFailed(String): void  [API 24+]
+ onDisconnected(String): void  [API 24+]
+ onError(int): void  [API 24+]
+ onRecordingStopped(Uri): void  [API 24+]
+ onTuned(Uri): void  [API 24+]
```

## TvRecordingInfo — declaration; API 34+ (14 +, 4 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 34+]
- TvRecordingInfo(String, long, long, int, String, String, long, long, Uri, Uri, List, Uri, long, long)  [API 34+]
+ describeContents(): int  [API 34+]
+ getChannelUri(): Uri  [API 34+]
+ getContentRatings(): List  [API 34+]
* getDescription(): String  [API 34+]
+ getEndPaddingMillis(): long  [API 34+]
* getName(): String  [API 34+]
+ getProgramUri(): Uri  [API 34+]
+ getRecordingDurationMillis(): long  [API 34+]
+ getRecordingId(): String  [API 34+]
+ getRecordingStartTimeMillis(): long  [API 34+]
+ getRecordingUri(): Uri  [API 34+]
+ getRepeatDays(): int  [API 34+]
+ getScheduledDurationMillis(): long  [API 34+]
+ getScheduledStartTimeMillis(): long  [API 34+]
+ getStartPaddingMillis(): long  [API 34+]
* setDescription(String): void  [API 34+]
* setName(String): void  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## TvTrackInfo — declaration; API 21+ (0 +, 21 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 21+]
* describeContents(): int  [API 21+]
* equals(Object): boolean  [API 21+]
* getAudioChannelCount(): int  [API 21+]
* getAudioSampleRate(): int  [API 21+]
* getDescription(): CharSequence  [API 23+]
* getEncoding(): String  [API 30+]
* getExtra(): Bundle  [API 21+]
* getId(): String  [API 21+]
* getLanguage(): String  [API 21+]
* getType(): int  [API 21+]
* getVideoActiveFormatDescription(): byte  [API 24+]
* getVideoFrameRate(): float  [API 21+]
* getVideoHeight(): int  [API 21+]
* getVideoPixelAspectRatio(): float  [API 23+]
* getVideoWidth(): int  [API 21+]
* hashCode(): int  [API 21+]
* isAudioDescription(): boolean  [API 30+]
* isEncrypted(): boolean  [API 30+]
* isHardOfHearing(): boolean  [API 30+]
* isSpokenSubtitle(): boolean  [API 30+]
* writeToParcel(Parcel, int): void  [API 21+]
```

## TvTrackInfo.Builder — declaration; API 21+ (0 +, 17 *, 0 ~, 0 -)

```
* Builder(int, String)  [API 21+]
* build(): TvTrackInfo  [API 21+]
* setAudioChannelCount(int): TvTrackInfo.Builder  [API 21+]
* setAudioDescription(boolean): TvTrackInfo.Builder  [API 30+]
* setAudioSampleRate(int): TvTrackInfo.Builder  [API 21+]
* setDescription(CharSequence): TvTrackInfo.Builder  [API 23+]
* setEncoding(String): TvTrackInfo.Builder  [API 30+]
* setEncrypted(boolean): TvTrackInfo.Builder  [API 30+]
* setExtra(Bundle): TvTrackInfo.Builder  [API 21+]
* setHardOfHearing(boolean): TvTrackInfo.Builder  [API 30+]
* setLanguage(String): TvTrackInfo.Builder  [API 21+]
* setSpokenSubtitle(boolean): TvTrackInfo.Builder  [API 30+]
* setVideoActiveFormatDescription(byte): TvTrackInfo.Builder  [API 24+]
* setVideoFrameRate(float): TvTrackInfo.Builder  [API 21+]
* setVideoHeight(int): TvTrackInfo.Builder  [API 21+]
* setVideoPixelAspectRatio(float): TvTrackInfo.Builder  [API 23+]
* setVideoWidth(int): TvTrackInfo.Builder  [API 21+]
```

## TvView — declaration; API 21+ (40 +, 0 *, 0 ~, 3 -)

```
- TvView(Context)  [API 21+]
- TvView(Context, AttributeSet)  [API 21+]
- TvView(Context, AttributeSet, int)  [API 21+]
+ dispatchDraw(Canvas): void  [API 21+]
+ dispatchGenericMotionEvent(MotionEvent): boolean  [API 21+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 21+]
+ dispatchTouchEvent(MotionEvent): boolean  [API 21+]
+ dispatchTrackballEvent(MotionEvent): boolean  [API 21+]
+ dispatchUnhandledInputEvent(InputEvent): boolean  [API 21+]
+ dispatchWindowFocusChanged(boolean): void  [API 21+]
+ draw(Canvas): void  [API 21+]
+ gatherTransparentRegion(Region): boolean  [API 21+]
+ getAudioPresentations(): List  [API 34+]
+ getSelectedTrack(int): String  [API 21+]
+ getTracks(int): List  [API 21+]
+ onAttachedToWindow(): void  [API 21+]
+ onDetachedFromWindow(): void  [API 21+]
+ onLayout(boolean, int, int, int, int): void  [API 21+]
+ onMeasure(int, int): void  [API 21+]
+ onUnhandledInputEvent(InputEvent): boolean  [API 21+]
+ onVisibilityChanged(View, int): void  [API 21+]
+ overrideTvAppAttributionSource(AttributionSource): void  [API 34+]
+ reset(): void  [API 21+]
+ selectAudioPresentation(int, int): void  [API 34+]
+ selectTrack(int, String): void  [API 21+]
+ sendAppPrivateCommand(String, Bundle): void  [API 24+]
+ setCallback(TvView.TvInputCallback): void  [API 21+]
+ setCaptionEnabled(boolean): void  [API 21+]
+ setInteractiveAppNotificationEnabled(boolean): void  [API 33+]
+ setOnUnhandledInputEventListener(TvView.OnUnhandledInputEventListener): void  [API 21+]
+ setStreamVolume(float): void  [API 21+]
+ setTimeShiftPositionCallback(TvView.TimeShiftPositionCallback): void  [API 23+]
+ setTvMessageEnabled(int, boolean): void  [API 34+]
+ setZOrderMediaOverlay(boolean): void  [API 24+]
+ setZOrderOnTop(boolean): void  [API 24+]
+ timeShiftPause(): void  [API 23+]
+ timeShiftPlay(String, Uri): void  [API 24+]
+ timeShiftResume(): void  [API 23+]
+ timeShiftSeekTo(long): void  [API 23+]
+ timeShiftSetMode(int): void  [API 34+]
+ timeShiftSetPlaybackParams(PlaybackParams): void  [API 23+]
+ tune(String, Uri): void  [API 21+]
+ tune(String, Uri, Bundle): void  [API 24+]
```

## TvView.OnUnhandledInputEventListener — declaration; API 21+ (1 +, 0 *, 0 ~, 0 -)

```
+ onUnhandledInputEvent(InputEvent): boolean  [API 21+]
```

## TvView.TimeShiftPositionCallback — declaration; API 23+ (3 +, 0 *, 0 ~, 0 -)

```
+ TimeShiftPositionCallback()  [API 23+]
+ onTimeShiftCurrentPositionChanged(String, long): void  [API 23+]
+ onTimeShiftStartPositionChanged(String, long): void  [API 23+]
```

## TvView.TvInputCallback — declaration; API 21+ (21 +, 0 *, 0 ~, 0 -)

```
+ TvInputCallback()  [API 21+]
+ onAitInfoUpdated(String, AitInfo): void  [API 33+]
+ onAudioPresentationSelected(String, int, int): void  [API 34+]
+ onAudioPresentationsChanged(String, List): void  [API 34+]
+ onAvailableSpeeds(String, float[]): void  [API 34+]
+ onChannelRetuned(String, Uri): void  [API 21+]
+ onConnectionFailed(String): void  [API 21+]
+ onContentAllowed(String): void  [API 21+]
+ onContentBlocked(String, TvContentRating): void  [API 21+]
+ onCueingMessageAvailability(String, boolean): void  [API 34+]
+ onDisconnected(String): void  [API 21+]
+ onSignalStrengthUpdated(String, int): void  [API 33+]
+ onTimeShiftMode(String, int): void  [API 34+]
+ onTimeShiftStatusChanged(String, int): void  [API 23+]
+ onTrackSelected(String, int, String): void  [API 21+]
+ onTracksChanged(String, List): void  [API 21+]
+ onTuned(String, Uri): void  [API 33+]
+ onTvMessage(String, int, Bundle): void  [API 34+]
+ onVideoAvailable(String): void  [API 21+]
+ onVideoSizeChanged(String, int, int): void  [API 21+]
+ onVideoUnavailable(String, int): void  [API 21+]
```


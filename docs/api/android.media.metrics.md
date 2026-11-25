# android.media.metrics

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BundleSession — declaration; API 33+ (0 +, 0 *, 0 ~, 5 -)

```
- close(): void  [API 33+]
- equals(Object): boolean  [API 33+]
- getSessionId(): LogSessionId  [API 33+]
- hashCode(): int  [API 33+]
- reportBundleMetrics(PersistableBundle): void  [API 33+]
```

## EditingEndedEvent — declaration; API 35+ (0 +, 9 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
* getErrorCode(): int  [API 35+]
* getExporterName(): String  [API 35+]
* getFinalProgressPercent(): float  [API 35+]
* getFinalState(): int  [API 35+]
* getInputMediaItemInfos(): List  [API 35+]
* getMetricsBundle(): Bundle  [API 35+]
* getMuxerName(): String  [API 35+]
- getOperationTypes(): long  [API 35+]
* getOutputMediaItemInfo(): MediaItemInfo  [API 35+]
* getTimeSinceCreatedMillis(): long  [API 35+]
- hashCode(): int  [API 35+]
- toString(): String  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## EditingEndedEvent.Builder — declaration; API 35+ (0 +, 10 *, 0 ~, 1 -)

```
* Builder(int)  [API 35+]
* addInputMediaItemInfo(MediaItemInfo): EditingEndedEvent.Builder  [API 35+]
- addOperationType(long): EditingEndedEvent.Builder  [API 35+]
* build(): EditingEndedEvent  [API 35+]
* setErrorCode(int): EditingEndedEvent.Builder  [API 35+]
* setExporterName(String): EditingEndedEvent.Builder  [API 35+]
* setFinalProgressPercent(float): EditingEndedEvent.Builder  [API 35+]
* setMetricsBundle(Bundle): EditingEndedEvent.Builder  [API 35+]
* setMuxerName(String): EditingEndedEvent.Builder  [API 35+]
* setOutputMediaItemInfo(MediaItemInfo): EditingEndedEvent.Builder  [API 35+]
* setTimeSinceCreatedMillis(long): EditingEndedEvent.Builder  [API 35+]
```

## EditingSession — declaration; API 33+ (0 +, 0 *, 0 ~, 5 -)

```
- close(): void  [API 33+]
- equals(Object): boolean  [API 33+]
- getSessionId(): LogSessionId  [API 33+]
- hashCode(): int  [API 33+]
- reportEditingEndedEvent(EditingEndedEvent): void  [API 35+]
```

## Event — declaration; API 31+ (0 +, 2 *, 0 ~, 0 -)

```
* getMetricsBundle(): Bundle  [API 31+]
* getTimeSinceCreatedMillis(): long  [API 31+]
```

## LogSessionId — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- LOG_SESSION_ID_NONE [field]  [API 31+]
- equals(Object): boolean  [API 31+]
- getStringId(): String  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
```

## MediaItemInfo — declaration; API 35+ (0 +, 13 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
* getAudioChannelCount(): int  [API 35+]
* getAudioSampleCount(): long  [API 35+]
* getAudioSampleRateHz(): int  [API 35+]
* getClipDurationMillis(): long  [API 35+]
* getCodecNames(): List  [API 35+]
* getContainerMimeType(): String  [API 35+]
- getDataTypes(): long  [API 35+]
* getDurationMillis(): long  [API 35+]
* getSampleMimeTypes(): List  [API 35+]
* getSourceType(): int  [API 35+]
* getVideoDataSpace(): int  [API 35+]
* getVideoFrameRate(): float  [API 35+]
* getVideoSampleCount(): long  [API 35+]
* getVideoSize(): Size  [API 35+]
- hashCode(): int  [API 35+]
- toString(): String  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## MediaItemInfo.Builder — declaration; API 35+ (0 +, 15 *, 0 ~, 1 -)

```
* Builder()  [API 35+]
* addCodecName(String): MediaItemInfo.Builder  [API 35+]
- addDataType(long): MediaItemInfo.Builder  [API 35+]
* addSampleMimeType(String): MediaItemInfo.Builder  [API 35+]
* build(): MediaItemInfo  [API 35+]
* setAudioChannelCount(int): MediaItemInfo.Builder  [API 35+]
* setAudioSampleCount(long): MediaItemInfo.Builder  [API 35+]
* setAudioSampleRateHz(int): MediaItemInfo.Builder  [API 35+]
* setClipDurationMillis(long): MediaItemInfo.Builder  [API 35+]
* setContainerMimeType(String): MediaItemInfo.Builder  [API 35+]
* setDurationMillis(long): MediaItemInfo.Builder  [API 35+]
* setSourceType(int): MediaItemInfo.Builder  [API 35+]
* setVideoDataSpace(int): MediaItemInfo.Builder  [API 35+]
* setVideoFrameRate(float): MediaItemInfo.Builder  [API 35+]
* setVideoSampleCount(long): MediaItemInfo.Builder  [API 35+]
* setVideoSize(Size): MediaItemInfo.Builder  [API 35+]
```

## MediaMetricsManager — framework; API 31+ (0 +, 0 *, 0 ~, 6 -)

```
- createBundleSession(): BundleSession  [API 33+]
- createEditingSession(): EditingSession  [API 33+]
- createPlaybackSession(): PlaybackSession  [API 31+]
- createRecordingSession(): RecordingSession  [API 31+]
- createTranscodingSession(): TranscodingSession  [API 33+]
- releaseSessionId(String): void  [API 33+]
```

## NetworkEvent — declaration; API 31+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getMetricsBundle(): Bundle  [API 31+]
* getNetworkType(): int  [API 31+]
* getTimeSinceCreatedMillis(): long  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## NetworkEvent.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* build(): NetworkEvent  [API 31+]
* setMetricsBundle(Bundle): NetworkEvent.Builder  [API 31+]
* setNetworkType(int): NetworkEvent.Builder  [API 31+]
* setTimeSinceCreatedMillis(long): NetworkEvent.Builder  [API 31+]
```

## PlaybackErrorEvent — declaration; API 31+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getErrorCode(): int  [API 31+]
* getMetricsBundle(): Bundle  [API 31+]
* getSubErrorCode(): int  [API 31+]
* getTimeSinceCreatedMillis(): long  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## PlaybackErrorEvent.Builder — declaration; API 31+ (0 +, 6 *, 0 ~, 1 -)

```
* Builder()  [API 31+]
* build(): PlaybackErrorEvent  [API 31+]
* setErrorCode(int): PlaybackErrorEvent.Builder  [API 31+]
- setException(Exception): PlaybackErrorEvent.Builder  [API 31+]
* setMetricsBundle(Bundle): PlaybackErrorEvent.Builder  [API 31+]
* setSubErrorCode(int): PlaybackErrorEvent.Builder  [API 31+]
* setTimeSinceCreatedMillis(long): PlaybackErrorEvent.Builder  [API 31+]
```

## PlaybackMetrics — declaration; API 31+ (0 +, 16 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
* getAudioUnderrunCount(): int  [API 31+]
* getContentType(): int  [API 31+]
* getDrmSessionId(): byte[]  [API 31+]
* getDrmType(): int  [API 31+]
- getExperimentIds(): long[]  [API 31+]
* getLocalBytesRead(): long  [API 31+]
* getMediaDurationMillis(): long  [API 31+]
* getMetricsBundle(): Bundle  [API 31+]
* getNetworkBytesRead(): long  [API 31+]
* getNetworkTransferDurationMillis(): long  [API 31+]
* getPlaybackType(): int  [API 31+]
* getPlayerName(): String  [API 31+]
* getPlayerVersion(): String  [API 31+]
* getStreamSource(): int  [API 31+]
* getStreamType(): int  [API 31+]
* getVideoFramesDropped(): int  [API 31+]
* getVideoFramesPlayed(): int  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## PlaybackMetrics.Builder — declaration; API 31+ (0 +, 18 *, 0 ~, 1 -)

```
* Builder()  [API 31+]
- addExperimentId(long): PlaybackMetrics.Builder  [API 31+]
* build(): PlaybackMetrics  [API 31+]
* setAudioUnderrunCount(int): PlaybackMetrics.Builder  [API 31+]
* setContentType(int): PlaybackMetrics.Builder  [API 31+]
* setDrmSessionId(byte[]): PlaybackMetrics.Builder  [API 31+]
* setDrmType(int): PlaybackMetrics.Builder  [API 31+]
* setLocalBytesRead(long): PlaybackMetrics.Builder  [API 31+]
* setMediaDurationMillis(long): PlaybackMetrics.Builder  [API 31+]
* setMetricsBundle(Bundle): PlaybackMetrics.Builder  [API 31+]
* setNetworkBytesRead(long): PlaybackMetrics.Builder  [API 31+]
* setNetworkTransferDurationMillis(long): PlaybackMetrics.Builder  [API 31+]
* setPlaybackType(int): PlaybackMetrics.Builder  [API 31+]
* setPlayerName(String): PlaybackMetrics.Builder  [API 31+]
* setPlayerVersion(String): PlaybackMetrics.Builder  [API 31+]
* setStreamSource(int): PlaybackMetrics.Builder  [API 31+]
* setStreamType(int): PlaybackMetrics.Builder  [API 31+]
* setVideoFramesDropped(int): PlaybackMetrics.Builder  [API 31+]
* setVideoFramesPlayed(int): PlaybackMetrics.Builder  [API 31+]
```

## PlaybackSession — declaration; API 31+ (0 +, 0 *, 0 ~, 9 -)

```
- close(): void  [API 31+]
- equals(Object): boolean  [API 31+]
- getSessionId(): LogSessionId  [API 31+]
- hashCode(): int  [API 31+]
- reportNetworkEvent(NetworkEvent): void  [API 31+]
- reportPlaybackErrorEvent(PlaybackErrorEvent): void  [API 31+]
- reportPlaybackMetrics(PlaybackMetrics): void  [API 31+]
- reportPlaybackStateEvent(PlaybackStateEvent): void  [API 31+]
- reportTrackChangeEvent(TrackChangeEvent): void  [API 31+]
```

## PlaybackStateEvent — declaration; API 31+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getMetricsBundle(): Bundle  [API 31+]
* getState(): int  [API 31+]
* getTimeSinceCreatedMillis(): long  [API 31+]
* hashCode(): int  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## PlaybackStateEvent.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* build(): PlaybackStateEvent  [API 31+]
* setMetricsBundle(Bundle): PlaybackStateEvent.Builder  [API 31+]
* setState(int): PlaybackStateEvent.Builder  [API 31+]
* setTimeSinceCreatedMillis(long): PlaybackStateEvent.Builder  [API 31+]
```

## RecordingSession — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- close(): void  [API 31+]
- equals(Object): boolean  [API 31+]
- getSessionId(): LogSessionId  [API 31+]
- hashCode(): int  [API 31+]
```

## TrackChangeEvent — declaration; API 31+ (0 +, 15 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
* getAudioSampleRate(): int  [API 31+]
* getBitrate(): int  [API 31+]
* getChannelCount(): int  [API 31+]
* getCodecName(): String  [API 31+]
* getContainerMimeType(): String  [API 31+]
* getHeight(): int  [API 31+]
* getLanguage(): String  [API 31+]
* getLanguageRegion(): String  [API 31+]
* getMetricsBundle(): Bundle  [API 31+]
* getSampleMimeType(): String  [API 31+]
* getTimeSinceCreatedMillis(): long  [API 31+]
* getTrackChangeReason(): int  [API 31+]
* getTrackState(): int  [API 31+]
- getTrackType(): int  [API 31+]
* getVideoFrameRate(): float  [API 31+]
* getWidth(): int  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## TrackChangeEvent.Builder — declaration; API 31+ (0 +, 16 *, 0 ~, 1 -)

```
- Builder(int)  [API 31+]
* build(): TrackChangeEvent  [API 31+]
* setAudioSampleRate(int): TrackChangeEvent.Builder  [API 31+]
* setBitrate(int): TrackChangeEvent.Builder  [API 31+]
* setChannelCount(int): TrackChangeEvent.Builder  [API 31+]
* setCodecName(String): TrackChangeEvent.Builder  [API 31+]
* setContainerMimeType(String): TrackChangeEvent.Builder  [API 31+]
* setHeight(int): TrackChangeEvent.Builder  [API 31+]
* setLanguage(String): TrackChangeEvent.Builder  [API 31+]
* setLanguageRegion(String): TrackChangeEvent.Builder  [API 31+]
* setMetricsBundle(Bundle): TrackChangeEvent.Builder  [API 31+]
* setSampleMimeType(String): TrackChangeEvent.Builder  [API 31+]
* setTimeSinceCreatedMillis(long): TrackChangeEvent.Builder  [API 31+]
* setTrackChangeReason(int): TrackChangeEvent.Builder  [API 31+]
* setTrackState(int): TrackChangeEvent.Builder  [API 31+]
* setVideoFrameRate(float): TrackChangeEvent.Builder  [API 31+]
* setWidth(int): TrackChangeEvent.Builder  [API 31+]
```

## TranscodingSession — declaration; API 33+ (0 +, 0 *, 0 ~, 4 -)

```
- close(): void  [API 33+]
- equals(Object): boolean  [API 33+]
- getSessionId(): LogSessionId  [API 33+]
- hashCode(): int  [API 33+]
```


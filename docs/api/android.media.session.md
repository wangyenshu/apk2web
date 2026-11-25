# android.media.session

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## MediaController — declaration; API 21+ (0 +, 0 *, 0 ~, 22 -)

```
- MediaController(Context, MediaSession.Token)  [API 21+]
- adjustVolume(int, int): void  [API 21+]
- dispatchMediaButtonEvent(KeyEvent): boolean  [API 21+]
- getExtras(): Bundle  [API 21+]
- getFlags(): long  [API 21+]
- getMetadata(): MediaMetadata  [API 21+]
- getPackageName(): String  [API 21+]
- getPlaybackInfo(): MediaController.PlaybackInfo  [API 21+]
- getPlaybackState(): PlaybackState  [API 21+]
- getQueue(): List  [API 21+]
- getQueueTitle(): CharSequence  [API 21+]
- getRatingType(): int  [API 21+]
- getSessionActivity(): PendingIntent  [API 21+]
- getSessionInfo(): Bundle  [API 29+]
- getSessionToken(): MediaSession.Token  [API 21+]
- getTag(): String  [API 30+]
- getTransportControls(): MediaController.TransportControls  [API 21+]
- registerCallback(MediaController.Callback): void  [API 21+]
- registerCallback(MediaController.Callback, Handler): void  [API 21+]
- sendCommand(String, Bundle, ResultReceiver): void  [API 21+]
- setVolumeTo(int, int): void  [API 21+]
- unregisterCallback(MediaController.Callback): void  [API 21+]
```

## MediaController.Callback — declaration; API 21+ (9 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 21+]
+ onAudioInfoChanged(MediaController.PlaybackInfo): void  [API 21+]
+ onExtrasChanged(Bundle): void  [API 21+]
+ onMetadataChanged(MediaMetadata): void  [API 21+]
+ onPlaybackStateChanged(PlaybackState): void  [API 21+]
+ onQueueChanged(List): void  [API 21+]
+ onQueueTitleChanged(CharSequence): void  [API 21+]
+ onSessionDestroyed(): void  [API 21+]
+ onSessionEvent(String, Bundle): void  [API 21+]
```

## MediaController.PlaybackInfo — declaration; API 21+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 21+]
- getAudioAttributes(): AudioAttributes  [API 21+]
- getCurrentVolume(): int  [API 21+]
- getMaxVolume(): int  [API 21+]
- getPlaybackType(): int  [API 21+]
- getVolumeControl(): int  [API 21+]
- getVolumeControlId(): String  [API 30+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## MediaController.TransportControls — declaration; API 21+ (0 +, 0 *, 0 ~, 20 -)

```
- fastForward(): void  [API 21+]
- pause(): void  [API 21+]
- play(): void  [API 21+]
- playFromMediaId(String, Bundle): void  [API 21+]
- playFromSearch(String, Bundle): void  [API 21+]
- playFromUri(Uri, Bundle): void  [API 23+]
- prepare(): void  [API 24+]
- prepareFromMediaId(String, Bundle): void  [API 24+]
- prepareFromSearch(String, Bundle): void  [API 24+]
- prepareFromUri(Uri, Bundle): void  [API 24+]
- rewind(): void  [API 21+]
- seekTo(long): void  [API 21+]
- sendCustomAction(PlaybackState.CustomAction, Bundle): void  [API 21+]
- sendCustomAction(String, Bundle): void  [API 21+]
- setPlaybackSpeed(float): void  [API 29+]
- setRating(Rating): void  [API 21+]
- skipToNext(): void  [API 21+]
- skipToPrevious(): void  [API 21+]
- skipToQueueItem(long): void  [API 21+]
- stop(): void  [API 21+]
```

## MediaSession — declaration; API 21+ (0 +, 0 *, 0 ~, 23 -)

```
- MediaSession(Context, String)  [API 21+]
- MediaSession(Context, String, Bundle)  [API 29+]
- getController(): MediaController  [API 21+]
- getCurrentControllerInfo(): MediaSessionManager.RemoteUserInfo  [API 28+]
- getSessionToken(): MediaSession.Token  [API 21+]
- isActive(): boolean  [API 21+]
- release(): void  [API 21+]
- sendSessionEvent(String, Bundle): void  [API 21+]
- setActive(boolean): void  [API 21+]
- setCallback(MediaSession.Callback): void  [API 21+]
- setCallback(MediaSession.Callback, Handler): void  [API 21+]
- setExtras(Bundle): void  [API 21+]
- setFlags(int): void  [API 21+]
- setMediaButtonBroadcastReceiver(ComponentName): void  [API 31+]
- setMediaButtonReceiver(PendingIntent): void  [API 21+, deprecated in 31]
- setMetadata(MediaMetadata): void  [API 21+]
- setPlaybackState(PlaybackState): void  [API 21+]
- setPlaybackToLocal(AudioAttributes): void  [API 21+]
- setPlaybackToRemote(VolumeProvider): void  [API 21+]
- setQueue(List): void  [API 21+]
- setQueueTitle(CharSequence): void  [API 21+]
- setRatingType(int): void  [API 22+]
- setSessionActivity(PendingIntent): void  [API 21+]
```

## MediaSession.Callback — declaration; API 21+ (20 +, 0 *, 0 ~, 2 -)

```
- Callback()  [API 21+]
+ onCommand(String, Bundle, ResultReceiver): void  [API 21+]
+ onCustomAction(String, Bundle): void  [API 21+]
+ onFastForward(): void  [API 21+]
- onMediaButtonEvent(Intent): boolean  [API 21+]
+ onPause(): void  [API 21+]
+ onPlay(): void  [API 21+]
+ onPlayFromMediaId(String, Bundle): void  [API 21+]
+ onPlayFromSearch(String, Bundle): void  [API 21+]
+ onPlayFromUri(Uri, Bundle): void  [API 23+]
+ onPrepare(): void  [API 24+]
+ onPrepareFromMediaId(String, Bundle): void  [API 24+]
+ onPrepareFromSearch(String, Bundle): void  [API 24+]
+ onPrepareFromUri(Uri, Bundle): void  [API 24+]
+ onRewind(): void  [API 21+]
+ onSeekTo(long): void  [API 21+]
+ onSetPlaybackSpeed(float): void  [API 29+]
+ onSetRating(Rating): void  [API 21+]
+ onSkipToNext(): void  [API 21+]
+ onSkipToPrevious(): void  [API 21+]
+ onSkipToQueueItem(long): void  [API 21+]
+ onStop(): void  [API 21+]
```

## MediaSession.QueueItem — declaration; API 21+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 21+]
- QueueItem(MediaDescription, long)  [API 21+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
- getDescription(): MediaDescription  [API 21+]
- getQueueId(): long  [API 21+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## MediaSession.Token — declaration; API 21+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 21+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
- hashCode(): int  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## MediaSessionManager — framework; API 21+ (0 +, 0 *, 0 ~, 14 -)

```
- addOnActiveSessionsChangedListener(MediaSessionManager.OnActiveSessionsChangedListener, ComponentName): void  [API 21+]
- addOnActiveSessionsChangedListener(MediaSessionManager.OnActiveSessionsChangedListener, ComponentName, Handler): void  [API 21+]
- addOnMediaKeyEventSessionChangedListener(Executor, MediaSessionManager.OnMediaKeyEventSessionChangedListener): void  [API 33+]
- addOnSession2TokensChangedListener(MediaSessionManager.OnSession2TokensChangedListener): void  [API 29+]
- addOnSession2TokensChangedListener(MediaSessionManager.OnSession2TokensChangedListener, Handler): void  [API 29+]
- getActiveSessions(ComponentName): List  [API 21+]
- getMediaKeyEventSession(): MediaSession.Token  [API 33+]
- getMediaKeyEventSessionPackageName(): String  [API 33+]
- getSession2Tokens(): List  [API 29+]
- isTrustedForMediaControl(MediaSessionManager.RemoteUserInfo): boolean  [API 28+]
- notifySession2Created(Session2Token): void  [API 29+, deprecated in 31]
- removeOnActiveSessionsChangedListener(MediaSessionManager.OnActiveSessionsChangedListener): void  [API 21+]
- removeOnMediaKeyEventSessionChangedListener(MediaSessionManager.OnMediaKeyEventSessionChangedListener): void  [API 33+]
- removeOnSession2TokensChangedListener(MediaSessionManager.OnSession2TokensChangedListener): void  [API 29+]
```

## MediaSessionManager.OnActiveSessionsChangedListener — framework; API 21+ (1 +, 0 *, 0 ~, 0 -)

```
+ onActiveSessionsChanged(List): void  [API 21+]
```

## MediaSessionManager.OnMediaKeyEventSessionChangedListener — framework; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ onMediaKeyEventSessionChanged(String, MediaSession.Token): void  [API 33+]
```

## MediaSessionManager.OnSession2TokensChangedListener — framework; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSession2TokensChanged(List): void  [API 29+]
```

## MediaSessionManager.RemoteUserInfo — framework; API 28+ (0 +, 6 *, 0 ~, 0 -)

```
* RemoteUserInfo(String, int, int)  [API 28+]
* equals(Object): boolean  [API 28+]
* getPackageName(): String  [API 28+]
* getPid(): int  [API 28+]
* getUid(): int  [API 28+]
* hashCode(): int  [API 28+]
```

## PlaybackState — declaration; API 21+ (0 +, 14 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 21+]
* describeContents(): int  [API 21+]
* getActions(): long  [API 21+]
* getActiveQueueItemId(): long  [API 21+]
* getBufferedPosition(): long  [API 21+]
* getCustomActions(): List  [API 21+]
* getErrorMessage(): CharSequence  [API 21+]
* getExtras(): Bundle  [API 22+]
* getLastPositionUpdateTime(): long  [API 21+]
* getPlaybackSpeed(): float  [API 21+]
* getPosition(): long  [API 21+]
* getState(): int  [API 21+]
* isActive(): boolean  [API 31+]
* toString(): String  [API 21+]
* writeToParcel(Parcel, int): void  [API 21+]
```

## PlaybackState.Builder — declaration; API 21+ (0 +, 9 *, 0 ~, 3 -)

```
* Builder()  [API 21+]
* Builder(PlaybackState)  [API 21+]
* addCustomAction(PlaybackState.CustomAction): PlaybackState.Builder  [API 21+]
- addCustomAction(String, String, int): PlaybackState.Builder  [API 21+]
* build(): PlaybackState  [API 21+]
* setActions(long): PlaybackState.Builder  [API 21+]
* setActiveQueueItemId(long): PlaybackState.Builder  [API 21+]
* setBufferedPosition(long): PlaybackState.Builder  [API 21+]
* setErrorMessage(CharSequence): PlaybackState.Builder  [API 21+]
* setExtras(Bundle): PlaybackState.Builder  [API 22+]
- setState(int, long, float): PlaybackState.Builder  [API 21+]
- setState(int, long, float, long): PlaybackState.Builder  [API 21+]
```

## PlaybackState.CustomAction — declaration; API 21+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 21+]
* describeContents(): int  [API 21+]
* getAction(): String  [API 21+]
* getExtras(): Bundle  [API 21+]
* getIcon(): int  [API 21+]
* getName(): CharSequence  [API 21+]
* toString(): String  [API 21+]
* writeToParcel(Parcel, int): void  [API 21+]
```

## PlaybackState.CustomAction.Builder — declaration; API 21+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(String, CharSequence, int)  [API 21+]
* build(): PlaybackState.CustomAction  [API 21+]
* setExtras(Bundle): PlaybackState.CustomAction.Builder  [API 21+]
```


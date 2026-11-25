# android.media

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ApplicationMediaCapabilities — declaration; API 31+ (0 +, 7 *, 0 ~, 4 -)

```
+ CREATOR [field]  [API 31+]
- createFromXml(XmlPullParser): ApplicationMediaCapabilities [static]  [API 31+]
* describeContents(): int  [API 31+]
* getSupportedHdrTypes(): List  [API 31+]
* getSupportedVideoMimeTypes(): List  [API 31+]
* getUnsupportedHdrTypes(): List  [API 31+]
* getUnsupportedVideoMimeTypes(): List  [API 31+]
- isFormatSpecified(String): boolean  [API 31+]
- isHdrTypeSupported(String): boolean  [API 31+]
- isVideoMimeTypeSupported(String): boolean  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## ApplicationMediaCapabilities.Builder — declaration; API 31+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* addSupportedHdrType(String): ApplicationMediaCapabilities.Builder  [API 31+]
* addSupportedVideoMimeType(String): ApplicationMediaCapabilities.Builder  [API 31+]
* addUnsupportedHdrType(String): ApplicationMediaCapabilities.Builder  [API 31+]
* addUnsupportedVideoMimeType(String): ApplicationMediaCapabilities.Builder  [API 31+]
* build(): ApplicationMediaCapabilities  [API 31+]
```

## AsyncPlayer — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- AsyncPlayer(String)  [API 1+]
- play(Context, Uri, boolean, AudioAttributes): void  [API 23+]
- play(Context, Uri, boolean, int): void  [API 1+, deprecated in 23]
- stop(): void  [API 1+]
```

## AudioAttributes — framework; API 21+ (5 +, 0 *, 1 ~, 7 -)

```
- CREATOR [field]  [API 21+]
- areHapticChannelsMuted(): boolean  [API 29+]
- describeContents(): int  [API 21+]
+ equals(Object): boolean  [API 21+]
- getAllowedCapturePolicy(): int  [API 29+]
+ getContentType(): int  [API 21+]
~ getFlags(): int  [API 21+]
- getSpatializationBehavior(): int  [API 32+]
+ getUsage(): int  [API 21+]
- getVolumeControlStream(): int  [API 26+]
+ hashCode(): int  [API 21+]
- isContentSpatialized(): boolean  [API 32+]
+ toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## AudioAttributes.Builder — framework; API 21+ (4 +, 0 *, 2 ~, 5 -)

```
+ Builder()  [API 21+]
- Builder(AudioAttributes)  [API 21+]
+ build(): AudioAttributes  [API 21+]
- setAllowedCapturePolicy(int): AudioAttributes.Builder  [API 29+]
+ setContentType(int): AudioAttributes.Builder  [API 21+]
~ setFlags(int): AudioAttributes.Builder  [API 21+]
- setHapticChannelsMuted(boolean): AudioAttributes.Builder  [API 29+]
- setIsContentSpatialized(boolean): AudioAttributes.Builder  [API 32+]
~ setLegacyStreamType(int): AudioAttributes.Builder  [API 21+]
- setSpatializationBehavior(int): AudioAttributes.Builder  [API 32+]
+ setUsage(int): AudioAttributes.Builder  [API 21+]
```

## AudioDescriptor — declaration; API 31+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 33+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
- getDescriptor(): byte[]  [API 31+]
- getEncapsulationType(): int  [API 31+]
- getStandard(): int  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## AudioDeviceCallback — declaration; API 23+ (3 +, 0 *, 0 ~, 0 -)

```
+ AudioDeviceCallback()  [API 23+]
+ onAudioDevicesAdded(AudioDeviceInfo[]): void  [API 23+]
+ onAudioDevicesRemoved(AudioDeviceInfo[]): void  [API 23+]
```

## AudioDeviceInfo — declaration; API 23+ (0 +, 0 *, 0 ~, 17 -)

```
- equals(Object): boolean  [API 23+]
- getAddress(): String  [API 28+]
- getAudioDescriptors(): List  [API 31+]
- getAudioProfiles(): List  [API 31+]
- getChannelCounts(): int[]  [API 23+]
- getChannelIndexMasks(): int[]  [API 23+]
- getChannelMasks(): int[]  [API 23+]
- getEncapsulationMetadataTypes(): int[]  [API 30+]
- getEncapsulationModes(): int[]  [API 30+]
- getEncodings(): int[]  [API 23+]
- getId(): int  [API 23+]
- getProductName(): CharSequence  [API 23+]
- getSampleRates(): int[]  [API 23+]
- getType(): int  [API 23+]
- hashCode(): int  [API 23+]
- isSink(): boolean  [API 23+]
- isSource(): boolean  [API 23+]
```

## AudioFocusRequest — declaration; API 26+ (0 +, 2 *, 0 ~, 2 -)

```
- acceptsDelayedFocusGain(): boolean  [API 26+]
* getAudioAttributes(): AudioAttributes  [API 26+]
* getFocusGain(): int  [API 26+]
- willPauseWhenDucked(): boolean  [API 26+]
```

## AudioFocusRequest.Builder — declaration; API 26+ (0 +, 5 *, 0 ~, 5 -)

```
* Builder(AudioFocusRequest)  [API 26+]
* Builder(int)  [API 26+]
* build(): AudioFocusRequest  [API 26+]
- setAcceptsDelayedFocusGain(boolean): AudioFocusRequest.Builder  [API 26+]
* setAudioAttributes(AudioAttributes): AudioFocusRequest.Builder  [API 26+]
* setFocusGain(int): AudioFocusRequest.Builder  [API 26+]
- setForceDucking(boolean): AudioFocusRequest.Builder  [API 28+]
- setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener): AudioFocusRequest.Builder  [API 26+]
- setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener, Handler): AudioFocusRequest.Builder  [API 26+]
- setWillPauseWhenDucked(boolean): AudioFocusRequest.Builder  [API 26+]
```

## AudioFormat — framework; API 3+ (7 +, 0 *, 0 ~, 5 -)

```
- AudioFormat()  [API 3-20, removed in 21]
- CREATOR [field]  [API 24+]
- describeContents(): int  [API 3+]
+ equals(Object): boolean  [API 3+]
+ getChannelCount(): int  [API 23+]
- getChannelIndexMask(): int  [API 23+]
+ getChannelMask(): int  [API 21+]
+ getEncoding(): int  [API 21+]
- getFrameSizeInBytes(): int  [API 29+]
+ getSampleRate(): int  [API 21+]
+ hashCode(): int  [API 3+]
+ toString(): String  [API 3+]
- writeToParcel(Parcel, int): void  [API 3+]
```

## AudioFormat.Builder — framework; API 21+ (5 +, 0 *, 0 ~, 2 -)

```
+ Builder()  [API 21+]
- Builder(AudioFormat)  [API 21+]
+ build(): AudioFormat  [API 21+]
- setChannelIndexMask(int): AudioFormat.Builder  [API 23+]
+ setChannelMask(int): AudioFormat.Builder  [API 21+]
+ setEncoding(int): AudioFormat.Builder  [API 21+]
+ setSampleRate(int): AudioFormat.Builder  [API 21+]
```

## AudioManager — framework; API 1+ (4 +, 0 *, 9 ~, 87 -)

```
~ abandonAudioFocus(AudioManager.OnAudioFocusChangeListener): int  [API 8+, deprecated in 26]
- abandonAudioFocusRequest(AudioFocusRequest): int  [API 26+]
- addOnCommunicationDeviceChangedListener(Executor, AudioManager.OnCommunicationDeviceChangedListener): void  [API 31+]
- addOnModeChangedListener(Executor, AudioManager.OnModeChangedListener): void  [API 31+]
- addOnPreferredMixerAttributesChangedListener(Executor, AudioManager.OnPreferredMixerAttributesChangedListener): void  [API 34+]
+ adjustStreamVolume(int, int, int): void  [API 1+]
- adjustSuggestedStreamVolume(int, int, int): void  [API 1+]
+ adjustVolume(int, int): void  [API 1+]
- adjustVolumeGroupVolume(int, int, int): void  [API 34+]
- clearCommunicationDevice(): void  [API 31+]
- clearPreferredMixerAttributes(AudioAttributes, AudioDeviceInfo): boolean  [API 34+]
- dispatchMediaKeyEvent(KeyEvent): void  [API 19+]
- generateAudioSessionId(): int  [API 21+]
- getActivePlaybackConfigurations(): List  [API 26+]
- getActiveRecordingConfigurations(): List  [API 24+]
- getAllowedCapturePolicy(): int  [API 29+]
- getAudioDevicesForAttributes(AudioAttributes): List  [API 33+]
- getAudioHwSyncForSession(int): int  [API 31+]
- getAvailableCommunicationDevices(): List  [API 31+]
- getCommunicationDevice(): AudioDeviceInfo  [API 31+]
- getDevices(int): AudioDeviceInfo[]  [API 23+]
- getDirectPlaybackSupport(AudioFormat, AudioAttributes): int [static]  [API 33+]
- getDirectProfilesForAttributes(AudioAttributes): List  [API 33+]
- getEncodedSurroundMode(): int  [API 31+]
- getMicrophones(): List  [API 28+]
~ getMode(): int  [API 1+]
- getParameters(String): String  [API 5+]
- getPlaybackOffloadSupport(AudioFormat, AudioAttributes): int [static]  [API 31+, deprecated in 33]
- getPreferredMixerAttributes(AudioAttributes, AudioDeviceInfo): AudioMixerAttributes  [API 34+]
- getProperty(String): String  [API 17+]
~ getRingerMode(): int  [API 1+]
- getRouting(int): int  [API 1+, deprecated in 15]
- getSpatializer(): Spatializer  [API 32+]
~ getStreamMaxVolume(int): int  [API 1+]
- getStreamMinVolume(int): int  [API 28+]
+ getStreamVolume(int): int  [API 1+]
- getStreamVolumeDb(int, int, int): float  [API 28+]
- getSupportedDeviceTypes(int): Set  [API 35+]
- getSupportedMixerAttributes(AudioDeviceInfo): List  [API 34+]
- getVibrateSetting(int): int  [API 1+, deprecated in 16]
- getVolumeGroupIdForAttributes(AudioAttributes): int  [API 34+]
- isBluetoothA2dpOn(): boolean  [API 3+, deprecated in 26]
- isBluetoothScoAvailableOffCall(): boolean  [API 8+]
- isBluetoothScoOn(): boolean  [API 1+, deprecated in 34]
- isCallScreeningModeSupported(): boolean  [API 30+]
- isHapticPlaybackSupported(): boolean [static]  [API 29+]
- isMicrophoneMute(): boolean  [API 1+]
~ isMusicActive(): boolean  [API 1+]
- isOffloadedPlaybackSupported(AudioFormat, AudioAttributes): boolean [static]  [API 29+]
- isRampingRingerEnabled(): boolean  [API 33+]
~ isSpeakerphoneOn(): boolean  [API 1+, deprecated in 34]
- isStreamMute(int): boolean  [API 23+]
- isSurroundFormatEnabled(int): boolean  [API 31+]
- isVolumeFixed(): boolean  [API 21+]
- isVolumeGroupMuted(int): boolean  [API 34+]
- isWiredHeadsetOn(): boolean  [API 5+, deprecated in 15]
- loadSoundEffects(): void  [API 1+]
~ playSoundEffect(int): void  [API 1+]
- playSoundEffect(int, float): void  [API 3+]
- registerAudioDeviceCallback(AudioDeviceCallback, Handler): void  [API 23+]
- registerAudioPlaybackCallback(AudioManager.AudioPlaybackCallback, Handler): void  [API 26+]
- registerAudioRecordingCallback(AudioManager.AudioRecordingCallback, Handler): void  [API 24+]
- registerMediaButtonEventReceiver(ComponentName): void  [API 8+, deprecated in 21]
- registerMediaButtonEventReceiver(PendingIntent): void  [API 18+, deprecated in 21]
- registerRemoteControlClient(RemoteControlClient): void  [API 14+, deprecated in 21]
- registerRemoteController(RemoteController): boolean  [API 19+, deprecated in 21]
- removeOnCommunicationDeviceChangedListener(AudioManager.OnCommunicationDeviceChangedListener): void  [API 31+]
- removeOnModeChangedListener(AudioManager.OnModeChangedListener): void  [API 31+]
- removeOnPreferredMixerAttributesChangedListener(AudioManager.OnPreferredMixerAttributesChangedListener): void  [API 34+]
- requestAudioFocus(AudioFocusRequest): int  [API 26+]
~ requestAudioFocus(AudioManager.OnAudioFocusChangeListener, int, int): int  [API 8+, deprecated in 26]
- setAllowedCapturePolicy(int): void  [API 29+]
- setBluetoothA2dpOn(boolean): void  [API 3+, deprecated in 15]
- setBluetoothScoOn(boolean): void  [API 1+]
- setCommunicationDevice(AudioDeviceInfo): boolean  [API 31+]
- setEncodedSurroundMode(int): boolean  [API 31+]
- setMicrophoneMute(boolean): void  [API 1+]
- setMode(int): void  [API 1+]
- setParameters(String): void  [API 5+]
- setPreferredMixerAttributes(AudioAttributes, AudioDeviceInfo, AudioMixerAttributes): boolean  [API 34+]
~ setRingerMode(int): void  [API 1+]
- setRouting(int, int, int): void  [API 1+, deprecated in 15]
- setSpeakerphoneOn(boolean): void  [API 1+, deprecated in 34]
- setStreamMute(int, boolean): void  [API 1+, deprecated in 23]
- setStreamSolo(int, boolean): void  [API 1+, deprecated in 23]
+ setStreamVolume(int, int, int): void  [API 1+]
- setSurroundFormatEnabled(int, boolean): boolean  [API 31+]
- setVibrateSetting(int, int): void  [API 1+, deprecated in 16]
- setWiredHeadsetOn(boolean): void  [API 5+, deprecated in 15]
- shouldVibrate(int): boolean  [API 1+, deprecated in 16]
- startBluetoothSco(): void  [API 8+, deprecated in 34]
- stopBluetoothSco(): void  [API 8+, deprecated in 34]
- unloadSoundEffects(): void  [API 1+]
- unregisterAudioDeviceCallback(AudioDeviceCallback): void  [API 23+]
- unregisterAudioPlaybackCallback(AudioManager.AudioPlaybackCallback): void  [API 26+]
- unregisterAudioRecordingCallback(AudioManager.AudioRecordingCallback): void  [API 24+]
- unregisterMediaButtonEventReceiver(ComponentName): void  [API 8+, deprecated in 21]
- unregisterMediaButtonEventReceiver(PendingIntent): void  [API 18+, deprecated in 21]
- unregisterRemoteControlClient(RemoteControlClient): void  [API 14+, deprecated in 21]
- unregisterRemoteController(RemoteController): void  [API 19+, deprecated in 21]
```

## AudioManager.AudioPlaybackCallback — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ AudioPlaybackCallback()  [API 26+]
+ onPlaybackConfigChanged(List): void  [API 26+]
```

## AudioManager.AudioRecordingCallback — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ AudioRecordingCallback()  [API 24+]
+ onRecordingConfigChanged(List): void  [API 24+]
```

## AudioManager.OnAudioFocusChangeListener — framework; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ onAudioFocusChange(int): void  [API 8+]
```

## AudioManager.OnCommunicationDeviceChangedListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCommunicationDeviceChanged(AudioDeviceInfo): void  [API 31+]
```

## AudioManager.OnModeChangedListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onModeChanged(int): void  [API 31+]
```

## AudioManager.OnPreferredMixerAttributesChangedListener — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPreferredMixerAttributesChanged(AudioAttributes, AudioDeviceInfo, AudioMixerAttributes): void  [API 34+]
```

## AudioMetadata — declaration; API 30+ (0 +, 0 *, 0 ~, 1 -)

```
- createMap(): AudioMetadataMap [static]  [API 30+]
```

## AudioMetadata.Format — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

```
- KEY_ATMOS_PRESENT [field]  [API 30+]
- KEY_AUDIO_ENCODING [field]  [API 30+]
- KEY_BIT_RATE [field]  [API 30+]
- KEY_BIT_WIDTH [field]  [API 30+]
- KEY_CHANNEL_MASK [field]  [API 30+]
- KEY_MIME [field]  [API 30+]
- KEY_PRESENTATION_CONTENT_CLASSIFIER [field]  [API 31+]
- KEY_PRESENTATION_ID [field]  [API 31+]
- KEY_PRESENTATION_LANGUAGE [field]  [API 31+]
- KEY_PROGRAM_ID [field]  [API 31+]
- KEY_SAMPLE_RATE [field]  [API 30+]
```

## AudioMetadata.Key — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ getName(): String  [API 30+]
+ getValueClass(): Class  [API 30+]
```

## AudioMetadataMap — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ remove(AudioMetadata.Key): Object  [API 30+]
+ set(AudioMetadata.Key, Object): Object  [API 30+]
```

## AudioMetadataReadMap — declaration; API 30+ (4 +, 0 *, 0 ~, 0 -)

```
+ containsKey(AudioMetadata.Key): boolean  [API 30+]
+ dup(): AudioMetadataMap  [API 30+]
+ get(AudioMetadata.Key): Object  [API 30+]
+ size(): int  [API 30+]
```

## AudioMixerAttributes — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getFormat(): AudioFormat  [API 34+]
* getMixerBehavior(): int  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## AudioMixerAttributes.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(AudioFormat)  [API 34+]
* build(): AudioMixerAttributes  [API 34+]
* setMixerBehavior(int): AudioMixerAttributes.Builder  [API 34+]
```

## AudioPlaybackCaptureConfiguration — declaration; API 29+ (0 +, 0 *, 0 ~, 5 -)

```
- getExcludeUids(): int[]  [API 29+]
- getExcludeUsages(): int[]  [API 29+]
- getMatchingUids(): int[]  [API 29+]
- getMatchingUsages(): int[]  [API 29+]
- getMediaProjection(): MediaProjection  [API 29+]
```

## AudioPlaybackCaptureConfiguration.Builder — declaration; API 29+ (0 +, 0 *, 0 ~, 6 -)

```
- Builder(MediaProjection)  [API 29+]
- addMatchingUid(int): AudioPlaybackCaptureConfiguration.Builder  [API 29+]
- addMatchingUsage(int): AudioPlaybackCaptureConfiguration.Builder  [API 29+]
- build(): AudioPlaybackCaptureConfiguration  [API 29+]
- excludeUid(int): AudioPlaybackCaptureConfiguration.Builder  [API 29+]
- excludeUsage(int): AudioPlaybackCaptureConfiguration.Builder  [API 29+]
```

## AudioPlaybackConfiguration — declaration; API 26+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- equals(Object): boolean  [API 26+]
- getAudioAttributes(): AudioAttributes  [API 26+]
- getAudioDeviceInfo(): AudioDeviceInfo  [API 31+]
- hashCode(): int  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## AudioPresentation — declaration; API 28+ (0 +, 4 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 28+]
- equals(Object): boolean  [API 28+]
* getLabels(): Map  [API 28+]
- getLocale(): Locale  [API 28+]
* getMasteringIndication(): int  [API 28+]
* getPresentationId(): int  [API 29+]
* getProgramId(): int  [API 29+]
- hasAudioDescription(): boolean  [API 28+]
- hasDialogueEnhancement(): boolean  [API 28+]
- hasSpokenSubtitles(): boolean  [API 28+]
- hashCode(): int  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## AudioPresentation.Builder — declaration; API 29+ (0 +, 5 *, 0 ~, 4 -)

```
* Builder(int)  [API 29+]
* build(): AudioPresentation  [API 29+]
- setHasAudioDescription(boolean): AudioPresentation.Builder  [API 29+]
- setHasDialogueEnhancement(boolean): AudioPresentation.Builder  [API 29+]
- setHasSpokenSubtitles(boolean): AudioPresentation.Builder  [API 29+]
* setLabels(Map): AudioPresentation.Builder  [API 29+]
- setLocale(ULocale): AudioPresentation.Builder  [API 29+]
* setMasteringIndication(int): AudioPresentation.Builder  [API 29+]
* setProgramId(int): AudioPresentation.Builder  [API 29+]
```

## AudioProfile — declaration; API 31+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 33+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
- getChannelIndexMasks(): int[]  [API 31+]
- getChannelMasks(): int[]  [API 31+]
- getEncapsulationType(): int  [API 31+]
- getFormat(): int  [API 31+]
- getSampleRates(): int[]  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## AudioRecord — declaration; API 3+ (0 +, 4 *, 0 ~, 44 -)

```
- AudioRecord(int, int, int, int, int)  [API 3+]
- addOnRoutingChangedListener(AudioRecord.OnRoutingChangedListener, Handler): void  [API 23+, deprecated in 24]
- addOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener, Handler): void  [API 3+]
- finalize(): void  [API 3+]
- getActiveMicrophones(): List  [API 28+]
- getActiveRecordingConfiguration(): AudioRecordingConfiguration  [API 3+]
- getAudioFormat(): int  [API 3+]
- getAudioSessionId(): int  [API 16+]
* getAudioSource(): int  [API 3+]
- getBufferSizeInFrames(): int  [API 23+]
- getChannelConfiguration(): int  [API 3+]
- getChannelCount(): int  [API 3+]
- getFormat(): AudioFormat  [API 23+]
* getLogSessionId(): LogSessionId  [API 31+]
- getMetrics(): PersistableBundle  [API 28+]
- getMinBufferSize(int, int, int): int [static]  [API 3+]
- getNotificationMarkerPosition(): int  [API 3+]
- getPositionNotificationPeriod(): int  [API 3+]
- getPreferredDevice(): AudioDeviceInfo  [API 23+]
- getRecordingState(): int  [API 3+]
- getRoutedDevice(): AudioDeviceInfo  [API 23+]
- getSampleRate(): int  [API 3+]
- getState(): int  [API 3+]
- getTimestamp(AudioTimestamp, int): int  [API 24+]
* isPrivacySensitive(): boolean  [API 30+]
- read(ByteBuffer, int): int  [API 3+]
- read(ByteBuffer, int, int): int  [API 23+]
- read(byte[], int, int): int  [API 3+]
- read(byte[], int, int, int): int  [API 23+]
- read(float[], int, int, int): int  [API 23+]
- read(short[], int, int): int  [API 3+]
- read(short[], int, int, int): int  [API 23+]
- registerAudioRecordingCallback(Executor, AudioManager.AudioRecordingCallback): void  [API 3+]
- release(): void  [API 3+]
- removeOnRoutingChangedListener(AudioRecord.OnRoutingChangedListener): void  [API 23+, deprecated in 24]
- removeOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener): void  [API 3+]
* setLogSessionId(LogSessionId): void  [API 31+]
- setNotificationMarkerPosition(int): int  [API 3+]
- setPositionNotificationPeriod(int): int  [API 3+]
- setPreferredDevice(AudioDeviceInfo): boolean  [API 23+]
- setPreferredMicrophoneDirection(int): boolean  [API 3+]
- setPreferredMicrophoneFieldDimension(float): boolean  [API 3+]
- setRecordPositionUpdateListener(AudioRecord.OnRecordPositionUpdateListener): void  [API 3+]
- setRecordPositionUpdateListener(AudioRecord.OnRecordPositionUpdateListener, Handler): void  [API 3+]
- startRecording(): void  [API 3+]
- startRecording(MediaSyncEvent): void  [API 16+]
- stop(): void  [API 3+]
- unregisterAudioRecordingCallback(AudioManager.AudioRecordingCallback): void  [API 3+]
```

## AudioRecord.Builder — declaration; API 23+ (0 +, 4 *, 0 ~, 4 -)

```
* Builder()  [API 23+]
* build(): AudioRecord  [API 23+]
- setAudioFormat(AudioFormat): AudioRecord.Builder  [API 23+]
- setAudioPlaybackCaptureConfig(AudioPlaybackCaptureConfiguration): AudioRecord.Builder  [API 29+]
* setAudioSource(int): AudioRecord.Builder  [API 23+]
- setBufferSizeInBytes(int): AudioRecord.Builder  [API 23+]
- setContext(Context): AudioRecord.Builder  [API 31+]
* setPrivacySensitive(boolean): AudioRecord.Builder  [API 30+]
```

## AudioRecord.MetricsConstants — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## AudioRecord.OnRecordPositionUpdateListener — declaration; API 3+ (2 +, 0 *, 0 ~, 0 -)

```
+ onMarkerReached(AudioRecord): void  [API 3+]
+ onPeriodicNotification(AudioRecord): void  [API 3+]
```

## AudioRecord.OnRoutingChangedListener — declaration; API 23+, deprecated in 24 (2 +, 0 *, 0 ~, 0 -)

```
+ onRoutingChanged(AudioRecord): void  [API 23+]
+ onRoutingChanged(AudioRouting): void  [API 23+]
```

## AudioRecordingConfiguration — declaration; API 24+ (0 +, 0 *, 0 ~, 13 -)

```
- CREATOR [field]  [API 24+]
- describeContents(): int  [API 24+]
- equals(Object): boolean  [API 24+]
- getAudioDevice(): AudioDeviceInfo  [API 24+]
- getAudioSource(): int  [API 29+]
- getClientAudioSessionId(): int  [API 24+]
- getClientAudioSource(): int  [API 24+]
- getClientEffects(): List  [API 29+]
- getClientFormat(): AudioFormat  [API 24+]
- getEffects(): List  [API 29+]
- getFormat(): AudioFormat  [API 24+]
- hashCode(): int  [API 24+]
- isClientSilenced(): boolean  [API 29+]
- writeToParcel(Parcel, int): void  [API 24+]
```

## AudioRecordingMonitor — declaration; API 29+ (3 +, 0 *, 0 ~, 0 -)

```
+ getActiveRecordingConfiguration(): AudioRecordingConfiguration  [API 29+]
+ registerAudioRecordingCallback(Executor, AudioManager.AudioRecordingCallback): void  [API 29+]
+ unregisterAudioRecordingCallback(AudioManager.AudioRecordingCallback): void  [API 29+]
```

## AudioRouting — declaration; API 24+ (5 +, 0 *, 0 ~, 0 -)

```
+ addOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener, Handler): void  [API 24+]
+ getPreferredDevice(): AudioDeviceInfo  [API 24+]
+ getRoutedDevice(): AudioDeviceInfo  [API 24+]
+ removeOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener): void  [API 24+]
+ setPreferredDevice(AudioDeviceInfo): boolean  [API 24+]
```

## AudioRouting.OnRoutingChangedListener — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ onRoutingChanged(AudioRouting): void  [API 24+]
```

## AudioTimestamp — declaration; API 19+ (0 +, 0 *, 0 ~, 4 -)

```
- AudioTimestamp()  [API 19+]
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 19+]
+ framePosition [field]  [API 19+]
+ nanoTime [field]  [API 19+]
- toString(): String  [API 19+]
- writeToParcel(Parcel, int): void  [API 19+]
```

## AudioTrack — framework; API 3+ (28 +, 0 *, 14 ~, 42 -)

```
+ AudioTrack(AudioAttributes, AudioFormat, int, int, int)  [API 21+]
+ AudioTrack(int, int, int, int, int, int)  [API 3+, deprecated in 26]
+ AudioTrack(int, int, int, int, int, int, int)  [API 9+, deprecated in 26]
- addOnCodecFormatChangedListener(Executor, AudioTrack.OnCodecFormatChangedListener): void  [API 30+]
- addOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener, Handler): void  [API 3+]
- addOnRoutingChangedListener(AudioTrack.OnRoutingChangedListener, Handler): void  [API 23+, deprecated in 24]
~ attachAuxEffect(int): int  [API 9+]
- createVolumeShaper(VolumeShaper.Configuration): VolumeShaper  [API 3+]
+ finalize(): void  [API 3+]
+ flush(): void  [API 3+]
- getAudioAttributes(): AudioAttributes  [API 29+]
- getAudioDescriptionMixLeveldB(): float  [API 30+]
+ getAudioFormat(): int  [API 3+]
+ getAudioSessionId(): int  [API 9+]
- getBufferCapacityInFrames(): int  [API 24+]
+ getBufferSizeInFrames(): int  [API 23+]
- getChannelConfiguration(): int  [API 3+]
+ getChannelCount(): int  [API 3+]
- getDualMonoMode(): int  [API 30+]
- getFormat(): AudioFormat  [API 23+]
- getLogSessionId(): LogSessionId  [API 31+]
~ getMaxVolume(): float [static]  [API 3+]
- getMetrics(): PersistableBundle  [API 28+]
+ getMinBufferSize(int, int, int): int [static]  [API 3+]
~ getMinVolume(): float [static]  [API 3+]
- getNativeFrameCount(): int  [API 3+, deprecated in 19]
~ getNativeOutputSampleRate(int): int [static]  [API 3+]
~ getNotificationMarkerPosition(): int  [API 3+]
- getOffloadDelay(): int  [API 29+]
- getOffloadPadding(): int  [API 29+]
- getPerformanceMode(): int  [API 26+]
+ getPlayState(): int  [API 3+]
+ getPlaybackHeadPosition(): int  [API 3+]
- getPlaybackParams(): PlaybackParams  [API 23+]
+ getPlaybackRate(): int  [API 3+]
~ getPositionNotificationPeriod(): int  [API 3+]
- getPreferredDevice(): AudioDeviceInfo  [API 23+]
- getRoutedDevice(): AudioDeviceInfo  [API 23+]
+ getSampleRate(): int  [API 3+]
- getStartThresholdInFrames(): int  [API 31+]
+ getState(): int  [API 3+]
+ getStreamType(): int  [API 3+]
- getTimestamp(AudioTimestamp): boolean  [API 19+]
- getUnderrunCount(): int  [API 24+]
- isDirectPlaybackSupported(AudioFormat, AudioAttributes): boolean [static]  [API 29+, deprecated in 33]
- isOffloadedPlayback(): boolean  [API 29+]
+ pause(): void  [API 3+]
+ play(): void  [API 3+]
- registerStreamEventCallback(Executor, AudioTrack.StreamEventCallback): void  [API 29+]
+ release(): void  [API 3+]
~ reloadStaticData(): int  [API 3+]
- removeOnCodecFormatChangedListener(AudioTrack.OnCodecFormatChangedListener): void  [API 30+]
- removeOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener): void  [API 3+]
- removeOnRoutingChangedListener(AudioTrack.OnRoutingChangedListener): void  [API 23+, deprecated in 24]
- setAudioDescriptionMixLeveldB(float): boolean  [API 30+]
~ setAuxEffectSendLevel(float): int  [API 9+]
- setBufferSizeInFrames(int): int  [API 24+]
- setDualMonoMode(int): boolean  [API 30+]
- setLogSessionId(LogSessionId): void  [API 31+]
~ setLoopPoints(int, int, int): int  [API 3+]
~ setNotificationMarkerPosition(int): int  [API 3+]
- setOffloadDelayPadding(int, int): void  [API 29+]
- setOffloadEndOfStream(): void  [API 29+]
- setPlaybackHeadPosition(int): int  [API 3+]
- setPlaybackParams(PlaybackParams): void  [API 23+]
~ setPlaybackPositionUpdateListener(AudioTrack.OnPlaybackPositionUpdateListener): void  [API 3+]
~ setPlaybackPositionUpdateListener(AudioTrack.OnPlaybackPositionUpdateListener, Handler): void  [API 3+]
~ setPlaybackRate(int): int  [API 3+]
~ setPositionNotificationPeriod(int): int  [API 3+]
- setPreferredDevice(AudioDeviceInfo): boolean  [API 23+]
- setPresentation(AudioPresentation): int  [API 28+]
- setStartThresholdInFrames(int): int  [API 31+]
- setState(int): void  [API 3+, deprecated in 19]
+ setStereoVolume(float, float): int  [API 3+, deprecated in 21]
+ setVolume(float): int  [API 21+]
+ stop(): void  [API 3+]
- unregisterStreamEventCallback(AudioTrack.StreamEventCallback): void  [API 29+]
+ write(ByteBuffer, int, int): int  [API 21+]
- write(ByteBuffer, int, int, long): int  [API 23+]
+ write(byte[], int, int): int  [API 3+]
+ write(byte[], int, int, int): int  [API 23+]
+ write(float[], int, int, int): int  [API 21+]
+ write(short[], int, int): int  [API 3+]
+ write(short[], int, int, int): int  [API 23+]
```

## AudioTrack.Builder — declaration; API 23+ (0 +, 0 *, 0 ~, 11 -)

```
- Builder()  [API 23+]
- build(): AudioTrack  [API 23+]
- setAudioAttributes(AudioAttributes): AudioTrack.Builder  [API 23+]
- setAudioFormat(AudioFormat): AudioTrack.Builder  [API 23+]
- setBufferSizeInBytes(int): AudioTrack.Builder  [API 23+]
- setContext(Context): AudioTrack.Builder  [API 34+]
- setEncapsulationMode(int): AudioTrack.Builder  [API 30+]
- setOffloadedPlayback(boolean): AudioTrack.Builder  [API 29+]
- setPerformanceMode(int): AudioTrack.Builder  [API 26+]
- setSessionId(int): AudioTrack.Builder  [API 23+]
- setTransferMode(int): AudioTrack.Builder  [API 23+]
```

## AudioTrack.MetricsConstants — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## AudioTrack.OnCodecFormatChangedListener — declaration; API 30+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCodecFormatChanged(AudioTrack, AudioMetadataReadMap): void  [API 30+]
```

## AudioTrack.OnPlaybackPositionUpdateListener — framework; API 3+ (2 +, 0 *, 0 ~, 0 -)

```
+ onMarkerReached(AudioTrack): void  [API 3+]
+ onPeriodicNotification(AudioTrack): void  [API 3+]
```

## AudioTrack.OnRoutingChangedListener — declaration; API 23+, deprecated in 24 (2 +, 0 *, 0 ~, 0 -)

```
+ onRoutingChanged(AudioRouting): void  [API 23+]
+ onRoutingChanged(AudioTrack): void  [API 23+]
```

## AudioTrack.StreamEventCallback — declaration; API 29+ (4 +, 0 *, 0 ~, 0 -)

```
+ StreamEventCallback()  [API 29+]
+ onDataRequest(AudioTrack, int): void  [API 29+]
+ onPresentationEnded(AudioTrack): void  [API 29+]
+ onTearDown(AudioTrack): void  [API 29+]
```

## CamcorderProfile — declaration; API 8+ (0 +, 0 *, 0 ~, 5 -)

```
+ audioBitRate [field]  [API 8+]
+ audioChannels [field]  [API 8+]
+ audioCodec [field]  [API 8+]
+ audioSampleRate [field]  [API 8+]
+ duration [field]  [API 8+]
+ fileFormat [field]  [API 8+]
- get(int): CamcorderProfile [static]  [API 8+, deprecated in 31]
- get(int, int): CamcorderProfile [static]  [API 9+, deprecated in 31]
- getAll(String, int): EncoderProfiles [static]  [API 31+]
- hasProfile(int): boolean [static]  [API 11+]
- hasProfile(int, int): boolean [static]  [API 11+]
+ quality [field]  [API 8+]
+ videoBitRate [field]  [API 8+]
+ videoCodec [field]  [API 8+]
+ videoFrameHeight [field]  [API 8+]
+ videoFrameRate [field]  [API 8+]
+ videoFrameWidth [field]  [API 8+]
```

## CameraProfile — declaration; API 8+ (0 +, 0 *, 0 ~, 3 -)

```
- CameraProfile()  [API 8+]
- getJpegEncodingQualityParameter(int): int [static]  [API 8+]
- getJpegEncodingQualityParameter(int, int): int [static]  [API 9+]
```

## DeniedByServerException — declaration; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ DeniedByServerException(String)  [API 18+]
```

## DrmInitData — declaration; API 24+ (1 +, 0 *, 0 ~, 2 -)

```
+ get(UUID): DrmInitData.SchemeInitData  [API 24+, deprecated in 30]
- getSchemeInitDataAt(int): DrmInitData.SchemeInitData  [API 30+]
- getSchemeInitDataCount(): int  [API 30+]
```

## DrmInitData.SchemeInitData — declaration; API 24+ (0 +, 0 *, 0 ~, 3 -)

```
- SchemeInitData(UUID, String, byte[])  [API 31+]
- UUID_NIL [field]  [API 30+]
- data [field]  [API 24+]
- equals(Object): boolean  [API 24+]
- hashCode(): int  [API 24+]
- mimeType [field]  [API 24+]
- uuid [field]  [API 30+]
```

## EncoderProfiles — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- getAudioProfiles(): List  [API 31+]
- getDefaultDurationSeconds(): int  [API 31+]
- getRecommendedFileFormat(): int  [API 31+]
- getVideoProfiles(): List  [API 31+]
```

## EncoderProfiles.AudioProfile — declaration; API 31+ (0 +, 0 *, 0 ~, 6 -)

```
- getBitrate(): int  [API 31+]
- getChannels(): int  [API 31+]
- getCodec(): int  [API 31+]
- getMediaType(): String  [API 31+]
- getProfile(): int  [API 31+]
- getSampleRate(): int  [API 31+]
```

## EncoderProfiles.VideoProfile — declaration; API 31+ (0 +, 0 *, 0 ~, 10 -)

```
- getBitDepth(): int  [API 33+]
- getBitrate(): int  [API 31+]
- getChromaSubsampling(): int  [API 33+]
- getCodec(): int  [API 31+]
- getFrameRate(): int  [API 31+]
- getHdrFormat(): int  [API 33+]
- getHeight(): int  [API 31+]
- getMediaType(): String  [API 31+]
- getProfile(): int  [API 31+]
- getWidth(): int  [API 31+]
```

## ExifInterface — declaration; API 5+ (0 +, 0 *, 0 ~, 26 -)

```
- ExifInterface(File)  [API 29+]
- ExifInterface(FileDescriptor)  [API 24+]
- ExifInterface(InputStream)  [API 24+]
- ExifInterface(InputStream, int)  [API 30+]
- ExifInterface(String)  [API 5+]
- getAltitude(double): double  [API 9+]
- getAttribute(String): String  [API 5+]
- getAttributeBytes(String): byte[]  [API 29+]
- getAttributeDouble(String, double): double  [API 8+]
- getAttributeInt(String, int): int  [API 5+]
- getAttributeRange(String): long[]  [API 29+]
- getDateTime(): long  [API 31+]
- getDateTimeDigitized(): long  [API 31+]
- getDateTimeOriginal(): long  [API 31+]
- getGpsDateTime(): long  [API 31+]
- getLatLong(float[]): boolean  [API 5+]
- getThumbnail(): byte[]  [API 5+]
- getThumbnailBitmap(): Bitmap  [API 26+]
- getThumbnailBytes(): byte[]  [API 26+]
- getThumbnailRange(): long[]  [API 24+]
- hasAttribute(String): boolean  [API 29+]
- hasThumbnail(): boolean  [API 5+]
- isSupportedMimeType(String): boolean [static]  [API 30+]
- isThumbnailCompressed(): boolean  [API 26+]
- saveAttributes(): void  [API 5+]
- setAttribute(String, String): void  [API 5+]
```

## FaceDetector — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- FaceDetector(int, int, int)  [API 1+]
- finalize(): void  [API 1+]
- findFaces(Bitmap, FaceDetector.Face[]): int  [API 1+]
```

## FaceDetector.Face — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- confidence(): float  [API 1+]
- eyesDistance(): float  [API 1+]
- getMidPoint(PointF): void  [API 1+]
- pose(int): float  [API 1+]
```

## Image — declaration; API 19+ (6 +, 6 *, 0 ~, 2 -)

```
+ close(): void  [API 19+]
* getCropRect(): Rect  [API 21+]
* getDataSpace(): int  [API 33+]
* getFence(): SyncFence  [API 33+]
+ getFormat(): int  [API 19+]
- getHardwareBuffer(): HardwareBuffer  [API 28+]
+ getHeight(): int  [API 19+]
+ getPlanes(): Image.Plane[]  [API 19+]
+ getTimestamp(): long  [API 19+]
+ getWidth(): int  [API 19+]
* setCropRect(Rect): void  [API 21+]
* setDataSpace(int): void  [API 33+]
* setFence(SyncFence): void  [API 33+]
- setTimestamp(long): void  [API 23+]
```

## Image.Plane — declaration; API 19+ (3 +, 0 *, 0 ~, 0 -)

```
+ getBuffer(): ByteBuffer  [API 19+]
+ getPixelStride(): int  [API 19+]
+ getRowStride(): int  [API 19+]
```

## ImageReader — declaration; API 19+ (0 +, 5 *, 0 ~, 11 -)

```
- acquireLatestImage(): Image  [API 19+]
- acquireNextImage(): Image  [API 19+]
- close(): void  [API 19+]
- discardFreeBuffers(): void  [API 28+]
- finalize(): void  [API 19+]
- getDataSpace(): int  [API 33+]
- getHardwareBufferFormat(): int  [API 33+]
* getHeight(): int  [API 19+]
* getImageFormat(): int  [API 19+]
* getMaxImages(): int  [API 19+]
- getSurface(): Surface  [API 19+]
* getUsage(): long  [API 33+]
* getWidth(): int  [API 19+]
- newInstance(int, int, int, int): ImageReader [static]  [API 19+]
- newInstance(int, int, int, int, long): ImageReader [static]  [API 29+]
- setOnImageAvailableListener(ImageReader.OnImageAvailableListener, Handler): void  [API 19+]
```

## ImageReader.Builder — declaration; API 33+ (0 +, 5 *, 0 ~, 2 -)

```
* Builder(int, int)  [API 33+]
* build(): ImageReader  [API 33+]
- setDefaultDataSpace(int): ImageReader.Builder  [API 33+]
- setDefaultHardwareBufferFormat(int): ImageReader.Builder  [API 33+]
* setImageFormat(int): ImageReader.Builder  [API 33+]
* setMaxImages(int): ImageReader.Builder  [API 33+]
* setUsage(long): ImageReader.Builder  [API 33+]
```

## ImageReader.OnImageAvailableListener — declaration; API 19+ (1 +, 0 *, 0 ~, 0 -)

```
+ onImageAvailable(ImageReader): void  [API 19+]
```

## ImageWriter — declaration; API 23+ (0 +, 4 *, 0 ~, 10 -)

```
- close(): void  [API 23+]
- dequeueInputImage(): Image  [API 23+]
- finalize(): void  [API 23+]
* getDataSpace(): int  [API 33+]
- getFormat(): int  [API 23+]
* getHardwareBufferFormat(): int  [API 33+]
- getHeight(): int  [API 33+]
* getMaxImages(): int  [API 23+]
* getUsage(): long  [API 33+]
- getWidth(): int  [API 33+]
- newInstance(Surface, int): ImageWriter [static]  [API 23+]
- newInstance(Surface, int, int): ImageWriter [static]  [API 29+]
- queueInputImage(Image): void  [API 23+]
- setOnImageReleasedListener(ImageWriter.OnImageReleasedListener, Handler): void  [API 23+]
```

## ImageWriter.Builder — declaration; API 33+ (0 +, 5 *, 0 ~, 3 -)

```
- Builder(Surface)  [API 33+]
* build(): ImageWriter  [API 33+]
* setDataSpace(int): ImageWriter.Builder  [API 33+]
* setHardwareBufferFormat(int): ImageWriter.Builder  [API 33+]
- setImageFormat(int): ImageWriter.Builder  [API 33+]
* setMaxImages(int): ImageWriter.Builder  [API 33+]
* setUsage(long): ImageWriter.Builder  [API 33+]
- setWidthAndHeight(int, int): ImageWriter.Builder  [API 33+]
```

## ImageWriter.OnImageReleasedListener — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onImageReleased(ImageWriter): void  [API 23+]
```

## JetPlayer — declaration; API 3+ (0 +, 0 *, 0 ~, 19 -)

```
- clearQueue(): boolean  [API 3+]
- clone(): Object  [API 3+]
- closeJetFile(): boolean  [API 3+]
- finalize(): void  [API 3+]
- getJetPlayer(): JetPlayer [static]  [API 3+]
- getMaxTracks(): int [static]  [API 3+]
- loadJetFile(AssetFileDescriptor): boolean  [API 3+]
- loadJetFile(String): boolean  [API 3+]
- pause(): boolean  [API 3+]
- play(): boolean  [API 3+]
- queueJetSegment(int, int, int, int, int, byte): boolean  [API 3+]
- queueJetSegmentMuteArray(int, int, int, int, boolean[], byte): boolean  [API 3+]
- release(): void  [API 3+]
- setEventListener(JetPlayer.OnJetEventListener): void  [API 3+]
- setEventListener(JetPlayer.OnJetEventListener, Handler): void  [API 3+]
- setMuteArray(boolean[], boolean): boolean  [API 3+]
- setMuteFlag(int, boolean, boolean): boolean  [API 3+]
- setMuteFlags(int, boolean): boolean  [API 3+]
- triggerClip(int): boolean  [API 3+]
```

## JetPlayer.OnJetEventListener — declaration; API 3+ (4 +, 0 *, 0 ~, 0 -)

```
+ onJetEvent(JetPlayer, short, byte, byte, byte, byte): void  [API 3+]
+ onJetNumQueuedSegmentUpdate(JetPlayer, int): void  [API 3+]
+ onJetPauseUpdate(JetPlayer, int): void  [API 3+]
+ onJetUserIdUpdate(JetPlayer, int, int): void  [API 3+]
```

## LoudnessCodecController — declaration; API 35+ (0 +, 0 *, 0 ~, 6 -)

```
- addMediaCodec(MediaCodec): boolean  [API 35+]
- close(): void  [API 35+]
- create(int): LoudnessCodecController [static]  [API 35+]
- create(int, Executor, LoudnessCodecController.OnLoudnessCodecUpdateListener): LoudnessCodecController [static]  [API 35+]
- getLoudnessCodecParams(MediaCodec): Bundle  [API 35+]
- removeMediaCodec(MediaCodec): void  [API 35+]
```

## LoudnessCodecController.OnLoudnessCodecUpdateListener — declaration; API 35+ (0 +, 0 *, 0 ~, 1 -)

```
- onLoudnessCodecUpdate(MediaCodec, Bundle): Bundle  [API 35+]
```

## MediaActionSound — declaration; API 16+ (0 +, 0 *, 0 ~, 5 -)

```
- MediaActionSound()  [API 16+]
- load(int): void  [API 16+]
- mustPlayShutterSound(): boolean [static]  [API 33+]
- play(int): void  [API 16+]
- release(): void  [API 16+]
```

## MediaCas — declaration; API 26+ (0 +, 0 *, 0 ~, 16 -)

```
- MediaCas(Context, int, String, int)  [API 30+]
- MediaCas(Context, int, String, int, Handler, MediaCas.EventListener)  [API 31+]
- MediaCas(int)  [API 26+]
- close(): void  [API 26+]
- enumeratePlugins(): MediaCas.PluginDescriptor[] [static]  [API 26+]
- finalize(): void  [API 26+]
- isSystemIdSupported(int): boolean [static]  [API 26+]
- openSession(): MediaCas.Session  [API 26+]
- openSession(int, int): MediaCas.Session  [API 30+]
- processEmm(byte[]): void  [API 26+]
- processEmm(byte[], int, int): void  [API 26+]
- provision(String): void  [API 26+]
- refreshEntitlements(int, byte[]): void  [API 26+]
- sendEvent(int, int, byte[]): void  [API 26+]
- setEventListener(MediaCas.EventListener, Handler): void  [API 26+]
- setPrivateData(byte[]): void  [API 26+]
```

## MediaCas.EventListener — declaration; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ onEvent(MediaCas, int, int, byte[]): void  [API 26+]
+ onPluginStatusUpdate(MediaCas, int, int): void  [API 30+]
+ onResourceLost(MediaCas): void  [API 30+]
+ onSessionEvent(MediaCas, MediaCas.Session, int, int, byte[]): void  [API 29+]
```

## MediaCas.PluginDescriptor — declaration; API 26+ (0 +, 0 *, 0 ~, 3 -)

```
- getName(): String  [API 26+]
- getSystemId(): int  [API 26+]
- toString(): String  [API 26+]
```

## MediaCas.Session — declaration; API 26+ (0 +, 0 *, 0 ~, 7 -)

```
- close(): void  [API 26+]
- equals(Object): boolean  [API 26+]
- getSessionId(): byte[]  [API 30+]
- processEcm(byte[]): void  [API 26+]
- processEcm(byte[], int, int): void  [API 26+]
- sendSessionEvent(int, int, byte[]): void  [API 29+]
- setPrivateData(byte[]): void  [API 26+]
```

## MediaCasException — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## MediaCasException.DeniedByServerException — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## MediaCasException.InsufficientResourceException — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## MediaCasException.NotProvisionedException — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## MediaCasException.ResourceBusyException — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## MediaCasException.UnsupportedCasException — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## MediaCasStateException — declaration; API 26+ (0 +, 0 *, 0 ~, 1 -)

```
- getDiagnosticInfo(): String  [API 26+]
```

## MediaCodec — declaration; API 16+ (0 +, 0 *, 0 ~, 52 -)

```
- configure(MediaFormat, Surface, MediaCrypto, int): void  [API 16+]
- configure(MediaFormat, Surface, int, MediaDescrambler): void  [API 26+]
- createByCodecName(String): MediaCodec [static]  [API 16+]
- createDecoderByType(String): MediaCodec [static]  [API 16+]
- createEncoderByType(String): MediaCodec [static]  [API 16+]
- createInputSurface(): Surface  [API 18+]
- createPersistentInputSurface(): Surface [static]  [API 23+]
- dequeueInputBuffer(long): int  [API 16+]
- dequeueOutputBuffer(MediaCodec.BufferInfo, long): int  [API 16+]
- detachOutputSurface(): void  [API 35+]
- finalize(): void  [API 16+]
- flush(): void  [API 16+]
- getCanonicalName(): String  [API 29+]
- getCodecInfo(): MediaCodecInfo  [API 18+]
- getInputBuffer(int): ByteBuffer  [API 21+]
- getInputBuffers(): ByteBuffer[]  [API 16+, deprecated in 21]
- getInputFormat(): MediaFormat  [API 21+]
- getInputImage(int): Image  [API 21+]
- getMetrics(): PersistableBundle  [API 26+]
- getName(): String  [API 18+]
- getOutputBuffer(int): ByteBuffer  [API 21+]
- getOutputBuffers(): ByteBuffer[]  [API 16+, deprecated in 21]
- getOutputFormat(): MediaFormat  [API 16+]
- getOutputFormat(int): MediaFormat  [API 21+]
- getOutputFrame(int): MediaCodec.OutputFrame  [API 30+]
- getOutputImage(int): Image  [API 21+]
- getParameterDescriptor(String): MediaCodec.ParameterDescriptor  [API 31+]
- getQueueRequest(int): MediaCodec.QueueRequest  [API 30+]
- getSupportedVendorParameters(): List  [API 31+]
- mapHardwareBuffer(HardwareBuffer): Image [static]  [API 30+]
- queueInputBuffer(int, int, int, long, int): void  [API 16+]
- queueInputBuffers(int, ArrayDeque): void  [API 35+]
- queueSecureInputBuffer(int, int, MediaCodec.CryptoInfo, long, int): void  [API 16+]
- queueSecureInputBuffers(int, ArrayDeque, ArrayDeque): void  [API 35+]
- release(): void  [API 16+]
- releaseOutputBuffer(int, boolean): void  [API 16+]
- releaseOutputBuffer(int, long): void  [API 21+]
- reset(): void  [API 21+]
- setAudioPresentation(AudioPresentation): void  [API 29+]
- setCallback(MediaCodec.Callback): void  [API 21+]
- setCallback(MediaCodec.Callback, Handler): void  [API 23+]
- setInputSurface(Surface): void  [API 23+]
- setOnFirstTunnelFrameReadyListener(Handler, MediaCodec.OnFirstTunnelFrameReadyListener): void  [API 31+]
- setOnFrameRenderedListener(MediaCodec.OnFrameRenderedListener, Handler): void  [API 23+]
- setOutputSurface(Surface): void  [API 23+]
- setParameters(Bundle): void  [API 19+]
- setVideoScalingMode(int): void  [API 16+]
- signalEndOfInputStream(): void  [API 18+]
- start(): void  [API 16+]
- stop(): void  [API 16+]
- subscribeToVendorParameters(List): void  [API 31+]
- unsubscribeFromVendorParameters(List): void  [API 31+]
```

## MediaCodec.BufferInfo — declaration; API 16+ (0 +, 0 *, 0 ~, 2 -)

```
- BufferInfo()  [API 16+]
+ flags [field]  [API 16+]
+ offset [field]  [API 16+]
+ presentationTimeUs [field]  [API 16+]
- set(int, int, long, int): void  [API 16+]
+ size [field]  [API 16+]
```

## MediaCodec.Callback — declaration; API 21+ (7 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 21+]
+ onCryptoError(MediaCodec, MediaCodec.CryptoException): void  [API 34+]
+ onError(MediaCodec, MediaCodec.CodecException): void  [API 21+]
+ onInputBufferAvailable(MediaCodec, int): void  [API 21+]
+ onOutputBufferAvailable(MediaCodec, int, MediaCodec.BufferInfo): void  [API 21+]
+ onOutputBuffersAvailable(MediaCodec, int, ArrayDeque): void  [API 35+]
+ onOutputFormatChanged(MediaCodec, MediaFormat): void  [API 21+]
```

## MediaCodec.CodecException — declaration; API 21+ (0 +, 0 *, 0 ~, 4 -)

```
- getDiagnosticInfo(): String  [API 21+]
- getErrorCode(): int  [API 23+]
- isRecoverable(): boolean  [API 21+]
- isTransient(): boolean  [API 21+]
```

## MediaCodec.CryptoException — declaration; API 16+ (0 +, 0 *, 0 ~, 6 -)

```
- CryptoException(int, String)  [API 16+]
- getCryptoInfo(): MediaCodec.CryptoInfo  [API 34+]
- getErrorCode(): int  [API 16+]
- getErrorContext(): int  [API 16+]
- getOemError(): int  [API 16+]
- getVendorError(): int  [API 16+]
```

## MediaCodec.CryptoInfo — declaration; API 16+ (0 +, 4 *, 0 ~, 1 -)

```
* CryptoInfo()  [API 16+]
* getPattern(): MediaCodec.CryptoInfo.Pattern  [API 31+]
+ iv [field]  [API 16+]
+ key [field]  [API 16+]
+ mode [field]  [API 16+]
+ numBytesOfClearData [field]  [API 16+]
+ numBytesOfEncryptedData [field]  [API 16+]
+ numSubSamples [field]  [API 16+]
- set(int, int[], int[], byte[], byte[], int): void  [API 16+]
* setPattern(MediaCodec.CryptoInfo.Pattern): void  [API 24+]
* toString(): String  [API 16+]
```

## MediaCodec.CryptoInfo.Pattern — declaration; API 24+ (0 +, 0 *, 0 ~, 4 -)

```
- Pattern(int, int)  [API 24+]
- getEncryptBlocks(): int  [API 24+]
- getSkipBlocks(): int  [API 24+]
- set(int, int): void  [API 24+]
```

## MediaCodec.IncompatibleWithBlockModelException — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## MediaCodec.InvalidBufferFlagsException — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## MediaCodec.LinearBlock — declaration; API 30+ (0 +, 0 *, 0 ~, 6 -)

```
- finalize(): void  [API 30+]
- isCodecCopyFreeCompatible(String[]): boolean [static]  [API 30+]
- isMappable(): boolean  [API 30+]
- map(): ByteBuffer  [API 30+]
- obtain(int, String[]): MediaCodec.LinearBlock [static]  [API 30+]
- recycle(): void  [API 30+]
```

## MediaCodec.MetricsConstants — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## MediaCodec.OnFirstTunnelFrameReadyListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onFirstTunnelFrameReady(MediaCodec): void  [API 31+]
```

## MediaCodec.OnFrameRenderedListener — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onFrameRendered(MediaCodec, long, long): void  [API 23+]
```

## MediaCodec.OutputFrame — declaration; API 30+ (0 +, 0 *, 0 ~, 7 -)

```
- getBufferInfos(): ArrayDeque  [API 35+]
- getChangedKeys(): Set  [API 30+]
- getFlags(): int  [API 30+]
- getFormat(): MediaFormat  [API 30+]
- getHardwareBuffer(): HardwareBuffer  [API 30+]
- getLinearBlock(): MediaCodec.LinearBlock  [API 30+]
- getPresentationTimeUs(): long  [API 30+]
```

## MediaCodec.ParameterDescriptor — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- equals(Object): boolean  [API 31+]
- getName(): String  [API 31+]
- getType(): int  [API 31+]
- hashCode(): int  [API 31+]
```

## MediaCodec.QueueRequest — declaration; API 30+ (0 +, 0 *, 0 ~, 13 -)

```
- queue(): void  [API 30+]
- setByteBufferParameter(String, ByteBuffer): MediaCodec.QueueRequest  [API 30+]
- setEncryptedLinearBlock(MediaCodec.LinearBlock, int, int, MediaCodec.CryptoInfo): MediaCodec.QueueRequest  [API 30+]
- setFlags(int): MediaCodec.QueueRequest  [API 30+]
- setFloatParameter(String, float): MediaCodec.QueueRequest  [API 30+]
- setHardwareBuffer(HardwareBuffer): MediaCodec.QueueRequest  [API 30+]
- setIntegerParameter(String, int): MediaCodec.QueueRequest  [API 30+]
- setLinearBlock(MediaCodec.LinearBlock, int, int): MediaCodec.QueueRequest  [API 30+]
- setLongParameter(String, long): MediaCodec.QueueRequest  [API 30+]
- setMultiFrameEncryptedLinearBlock(MediaCodec.LinearBlock, ArrayDeque, ArrayDeque): MediaCodec.QueueRequest  [API 35+]
- setMultiFrameLinearBlock(MediaCodec.LinearBlock, ArrayDeque): MediaCodec.QueueRequest  [API 35+]
- setPresentationTimeUs(long): MediaCodec.QueueRequest  [API 30+]
- setStringParameter(String, String): MediaCodec.QueueRequest  [API 30+]
```

## MediaCodecInfo — declaration; API 16+ (0 +, 0 *, 0 ~, 9 -)

```
- getCanonicalName(): String  [API 29+]
- getCapabilitiesForType(String): MediaCodecInfo.CodecCapabilities  [API 16+]
- getName(): String  [API 16+]
- getSupportedTypes(): String[]  [API 16+]
- isAlias(): boolean  [API 29+]
- isEncoder(): boolean  [API 16+]
- isHardwareAccelerated(): boolean  [API 29+]
- isSoftwareOnly(): boolean  [API 29+]
- isVendor(): boolean  [API 29+]
```

## MediaCodecInfo.AudioCapabilities — declaration; API 21+ (0 +, 0 *, 0 ~, 7 -)

```
- getBitrateRange(): Range  [API 21+]
- getInputChannelCountRanges(): Range[]  [API 31+]
- getMaxInputChannelCount(): int  [API 21+]
- getMinInputChannelCount(): int  [API 31+]
- getSupportedSampleRateRanges(): Range[]  [API 21+]
- getSupportedSampleRates(): int[]  [API 21+]
- isSampleRateSupported(int): boolean  [API 21+]
```

## MediaCodecInfo.CodecCapabilities — declaration; API 16+ (0 +, 0 *, 0 ~, 11 -)

```
- CodecCapabilities()  [API 16+]
+ colorFormats [field]  [API 16+]
- createFromProfileLevel(String, int, int): MediaCodecInfo.CodecCapabilities [static]  [API 21+]
- getAudioCapabilities(): MediaCodecInfo.AudioCapabilities  [API 21+]
- getDefaultFormat(): MediaFormat  [API 21+]
- getEncoderCapabilities(): MediaCodecInfo.EncoderCapabilities  [API 21+]
- getMaxSupportedInstances(): int  [API 23+]
- getMimeType(): String  [API 21+]
- getVideoCapabilities(): MediaCodecInfo.VideoCapabilities  [API 21+]
- isFeatureRequired(String): boolean  [API 21+]
- isFeatureSupported(String): boolean  [API 19+]
- isFormatSupported(MediaFormat): boolean  [API 21+]
+ profileLevels [field]  [API 16+]
```

## MediaCodecInfo.CodecProfileLevel — declaration; API 16+ (0 +, 0 *, 0 ~, 3 -)

```
- CodecProfileLevel()  [API 16+]
- equals(Object): boolean  [API 16+]
- hashCode(): int  [API 16+]
+ level [field]  [API 16+]
+ profile [field]  [API 16+]
```

## MediaCodecInfo.EncoderCapabilities — declaration; API 21+ (0 +, 0 *, 0 ~, 3 -)

```
- getComplexityRange(): Range  [API 21+]
- getQualityRange(): Range  [API 28+]
- isBitrateModeSupported(int): boolean  [API 21+]
```

## MediaCodecInfo.VideoCapabilities — declaration; API 21+ (0 +, 0 *, 0 ~, 13 -)

```
- areSizeAndRateSupported(int, int, double): boolean  [API 21+]
- getAchievableFrameRatesFor(int, int): Range  [API 23+]
- getBitrateRange(): Range  [API 21+]
- getHeightAlignment(): int  [API 21+]
- getSupportedFrameRates(): Range  [API 21+]
- getSupportedFrameRatesFor(int, int): Range  [API 21+]
- getSupportedHeights(): Range  [API 21+]
- getSupportedHeightsFor(int): Range  [API 21+]
- getSupportedPerformancePoints(): List  [API 29+]
- getSupportedWidths(): Range  [API 21+]
- getSupportedWidthsFor(int): Range  [API 21+]
- getWidthAlignment(): int  [API 21+]
- isSizeSupported(int, int): boolean  [API 21+]
```

## MediaCodecInfo.VideoCapabilities.PerformancePoint — declaration; API 29+ (0 +, 0 *, 0 ~, 6 -)

```
- FHD_100 [field]  [API 29+]
- FHD_120 [field]  [API 29+]
- FHD_200 [field]  [API 29+]
- FHD_24 [field]  [API 29+]
- FHD_240 [field]  [API 29+]
- FHD_25 [field]  [API 29+]
- FHD_30 [field]  [API 29+]
- FHD_50 [field]  [API 29+]
- FHD_60 [field]  [API 29+]
- HD_100 [field]  [API 29+]
- HD_120 [field]  [API 29+]
- HD_200 [field]  [API 29+]
- HD_24 [field]  [API 29+]
- HD_240 [field]  [API 29+]
- HD_25 [field]  [API 29+]
- HD_30 [field]  [API 29+]
- HD_50 [field]  [API 29+]
- HD_60 [field]  [API 29+]
- PerformancePoint(int, int, int)  [API 29+]
- SD_24 [field]  [API 29+]
- SD_25 [field]  [API 29+]
- SD_30 [field]  [API 29+]
- SD_48 [field]  [API 29+]
- SD_50 [field]  [API 29+]
- SD_60 [field]  [API 29+]
- UHD_100 [field]  [API 29+]
- UHD_120 [field]  [API 29+]
- UHD_200 [field]  [API 29+]
- UHD_24 [field]  [API 29+]
- UHD_240 [field]  [API 29+]
- UHD_25 [field]  [API 29+]
- UHD_30 [field]  [API 29+]
- UHD_50 [field]  [API 29+]
- UHD_60 [field]  [API 29+]
- covers(MediaCodecInfo.VideoCapabilities.PerformancePoint): boolean  [API 29+]
- covers(MediaFormat): boolean  [API 29+]
- equals(Object): boolean  [API 29+]
- hashCode(): int  [API 29+]
- toString(): String  [API 29+]
```

## MediaCodecList — declaration; API 16+ (0 +, 0 *, 0 ~, 6 -)

```
- MediaCodecList(int)  [API 21+]
- findDecoderForFormat(MediaFormat): String  [API 21+]
- findEncoderForFormat(MediaFormat): String  [API 21+]
- getCodecCount(): int [static]  [API 16+, deprecated in 21]
- getCodecInfoAt(int): MediaCodecInfo [static]  [API 16+, deprecated in 21]
- getCodecInfos(): MediaCodecInfo[]  [API 21+]
```

## MediaCommunicationManager — framework; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- getSession2Tokens(): List  [API 31+]
- getVersion(): int  [API 31+]
```

## MediaController2 — declaration; API 29+ (0 +, 0 *, 0 ~, 5 -)

```
- cancelSessionCommand(Object): void  [API 29+]
- close(): void  [API 29+]
- getConnectedToken(): Session2Token  [API 29+]
- isPlaybackActive(): boolean  [API 29+]
- sendSessionCommand(Session2Command, Bundle): Object  [API 29+]
```

## MediaController2.Builder — declaration; API 29+ (0 +, 0 *, 0 ~, 4 -)

```
- Builder(Context, Session2Token)  [API 29+]
- build(): MediaController2  [API 29+]
- setConnectionHints(Bundle): MediaController2.Builder  [API 29+]
- setControllerCallback(Executor, MediaController2.ControllerCallback): MediaController2.Builder  [API 29+]
```

## MediaController2.ControllerCallback — declaration; API 29+ (4 +, 0 *, 0 ~, 2 -)

```
- ControllerCallback()  [API 29+]
+ onCommandResult(MediaController2, Object, Session2Command, Session2Command.Result): void  [API 29+]
+ onConnected(MediaController2, Session2CommandGroup): void  [API 29+]
+ onDisconnected(MediaController2): void  [API 29+]
+ onPlaybackActiveChanged(MediaController2, boolean): void  [API 29+]
- onSessionCommand(MediaController2, Session2Command, Bundle): Session2Command.Result  [API 29+]
```

## MediaCrypto — declaration; API 16+ (0 +, 0 *, 0 ~, 6 -)

```
- MediaCrypto(UUID, byte[])  [API 16+]
- finalize(): void  [API 16+]
- isCryptoSchemeSupported(UUID): boolean [static]  [API 16+]
- release(): void  [API 16+]
- requiresSecureDecoderComponent(String): boolean  [API 16+]
- setMediaDrmSession(byte[]): void  [API 23+]
```

## MediaCryptoException — declaration; API 16+ (1 +, 0 *, 0 ~, 3 -)

```
+ MediaCryptoException(String)  [API 16+]
- getErrorContext(): int  [API 16+]
- getOemError(): int  [API 16+]
- getVendorError(): int  [API 16+]
```

## MediaDataSource — declaration; API 23+ (3 +, 0 *, 0 ~, 0 -)

```
+ MediaDataSource()  [API 23+]
+ getSize(): long  [API 23+]
+ readAt(long, byte[], int, int): int  [API 23+]
```

## MediaDescrambler — declaration; API 26+ (0 +, 0 *, 0 ~, 6 -)

```
- MediaDescrambler(int)  [API 26+]
- close(): void  [API 26+]
- descramble(ByteBuffer, ByteBuffer, MediaCodec.CryptoInfo): int  [API 26+]
- finalize(): void  [API 26+]
- requiresSecureDecoderComponent(String): boolean  [API 26+]
- setMediaCasSession(MediaCas.Session): void  [API 26+]
```

## MediaDescription — declaration; API 21+ (0 +, 12 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 21+]
* describeContents(): int  [API 21+]
* equals(Object): boolean  [API 21+]
* getDescription(): CharSequence  [API 21+]
* getExtras(): Bundle  [API 21+]
* getIconBitmap(): Bitmap  [API 21+]
* getIconUri(): Uri  [API 21+]
* getMediaId(): String  [API 21+]
* getMediaUri(): Uri  [API 23+]
* getSubtitle(): CharSequence  [API 21+]
* getTitle(): CharSequence  [API 21+]
* toString(): String  [API 21+]
* writeToParcel(Parcel, int): void  [API 21+]
```

## MediaDescription.Builder — declaration; API 21+ (0 +, 10 *, 0 ~, 0 -)

```
* Builder()  [API 21+]
* build(): MediaDescription  [API 21+]
* setDescription(CharSequence): MediaDescription.Builder  [API 21+]
* setExtras(Bundle): MediaDescription.Builder  [API 21+]
* setIconBitmap(Bitmap): MediaDescription.Builder  [API 21+]
* setIconUri(Uri): MediaDescription.Builder  [API 21+]
* setMediaId(String): MediaDescription.Builder  [API 21+]
* setMediaUri(Uri): MediaDescription.Builder  [API 23+]
* setSubtitle(CharSequence): MediaDescription.Builder  [API 21+]
* setTitle(CharSequence): MediaDescription.Builder  [API 21+]
```

## MediaDrm — declaration; API 18+ (0 +, 0 *, 0 ~, 57 -)

```
- MediaDrm(UUID)  [API 18+]
- clearOnEventListener(): void  [API 29+]
- clearOnExpirationUpdateListener(): void  [API 29+]
- clearOnKeyStatusChangeListener(): void  [API 29+]
- clearOnSessionLostStateListener(): void  [API 29+]
- close(): void  [API 18+]
- closeSession(byte[]): void  [API 18+]
- finalize(): void  [API 18+]
- getConnectedHdcpLevel(): int  [API 28+]
- getCryptoSession(byte[], String, String): MediaDrm.CryptoSession  [API 18+]
- getKeyRequest(byte[], byte[], String, int, HashMap): MediaDrm.KeyRequest  [API 18+]
- getLogMessages(): List  [API 31+]
- getMaxHdcpLevel(): int  [API 28+]
- getMaxSecurityLevel(): int [static]  [API 28+]
- getMaxSessionCount(): int  [API 28+]
- getMetrics(): PersistableBundle  [API 28+]
- getOfflineLicenseKeySetIds(): List  [API 29+]
- getOfflineLicenseState(byte[]): int  [API 29+]
- getOpenSessionCount(): int  [API 28+]
- getPlaybackComponent(byte[]): MediaDrm.PlaybackComponent  [API 31+]
- getPropertyByteArray(String): byte[]  [API 18+]
- getPropertyString(String): String  [API 18+]
- getProvisionRequest(): MediaDrm.ProvisionRequest  [API 18+]
- getSecureStop(byte[]): byte[]  [API 22+, deprecated in 33]
- getSecureStopIds(): List  [API 28+, deprecated in 33]
- getSecureStops(): List  [API 18+, deprecated in 33]
- getSecurityLevel(byte[]): int  [API 28+]
- getSupportedCryptoSchemes(): List [static]  [API 30+]
- isCryptoSchemeSupported(UUID): boolean [static]  [API 18+]
- isCryptoSchemeSupported(UUID, String): boolean [static]  [API 19+]
- isCryptoSchemeSupported(UUID, String, int): boolean [static]  [API 29+]
- openSession(): byte[]  [API 18+]
- openSession(int): byte[]  [API 28+]
- provideKeyResponse(byte[], byte[]): byte[]  [API 18+]
- provideProvisionResponse(byte[]): void  [API 18+]
- queryKeyStatus(byte[]): HashMap  [API 18+]
- release(): void  [API 18+, deprecated in 28]
- releaseAllSecureStops(): void  [API 22+, deprecated in 28]
- releaseSecureStops(byte[]): void  [API 18+, deprecated in 33]
- removeAllSecureStops(): void  [API 28+, deprecated in 33]
- removeKeys(byte[]): void  [API 18+]
- removeOfflineLicense(byte[]): void  [API 29+]
- removeSecureStop(byte[]): void  [API 28+, deprecated in 33]
- requiresSecureDecoder(String): boolean  [API 31+]
- requiresSecureDecoder(String, int): boolean  [API 31+]
- restoreKeys(byte[], byte[]): void  [API 18+]
- setOnEventListener(Executor, MediaDrm.OnEventListener): void  [API 29+]
- setOnEventListener(MediaDrm.OnEventListener): void  [API 18+]
- setOnEventListener(MediaDrm.OnEventListener, Handler): void  [API 29+]
- setOnExpirationUpdateListener(Executor, MediaDrm.OnExpirationUpdateListener): void  [API 29+]
- setOnExpirationUpdateListener(MediaDrm.OnExpirationUpdateListener, Handler): void  [API 23+]
- setOnKeyStatusChangeListener(Executor, MediaDrm.OnKeyStatusChangeListener): void  [API 29+]
- setOnKeyStatusChangeListener(MediaDrm.OnKeyStatusChangeListener, Handler): void  [API 23+]
- setOnSessionLostStateListener(Executor, MediaDrm.OnSessionLostStateListener): void  [API 29+]
- setOnSessionLostStateListener(MediaDrm.OnSessionLostStateListener, Handler): void  [API 29+]
- setPropertyByteArray(String, byte[]): void  [API 18+]
- setPropertyString(String, String): void  [API 18+]
```

## MediaDrm.CryptoSession — declaration; API 18+ (0 +, 0 *, 0 ~, 4 -)

```
- decrypt(byte[], byte[], byte[]): byte[]  [API 18+]
- encrypt(byte[], byte[], byte[]): byte[]  [API 18+]
- sign(byte[], byte[]): byte[]  [API 18+]
- verify(byte[], byte[], byte[]): boolean  [API 18+]
```

## MediaDrm.ErrorCodes — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## MediaDrm.HdcpLevel — declaration; API 28-34, removed in 35, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

## MediaDrm.KeyRequest — declaration; API 18+ (0 +, 0 *, 0 ~, 3 -)

```
- getData(): byte[]  [API 18+]
- getDefaultUrl(): String  [API 18+]
- getRequestType(): int  [API 23+]
```

## MediaDrm.KeyStatus — declaration; API 23+ (0 +, 0 *, 0 ~, 2 -)

```
- getKeyId(): byte[]  [API 23+]
- getStatusCode(): int  [API 23+]
```

## MediaDrm.LogMessage — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- getMessage(): String  [API 31+]
- getPriority(): int  [API 31+]
- getTimestampMillis(): long  [API 31+]
- toString(): String  [API 31+]
```

## MediaDrm.MediaDrmStateException — declaration; API 21+ (0 +, 0 *, 0 ~, 6 -)

```
- getDiagnosticInfo(): String  [API 21+]
- getErrorCode(): int  [API 31+]
- getErrorContext(): int  [API 21+]
- getOemError(): int  [API 21+]
- getVendorError(): int  [API 21+]
- isTransient(): boolean  [API 31+]
```

## MediaDrm.MetricsConstants — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## MediaDrm.OnEventListener — declaration; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ onEvent(MediaDrm, byte[], int, int, byte[]): void  [API 18+]
```

## MediaDrm.OnExpirationUpdateListener — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onExpirationUpdate(MediaDrm, byte[], long): void  [API 23+]
```

## MediaDrm.OnKeyStatusChangeListener — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onKeyStatusChange(MediaDrm, byte[], List, boolean): void  [API 23+]
```

## MediaDrm.OnSessionLostStateListener — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSessionLostState(MediaDrm, byte[]): void  [API 29+]
```

## MediaDrm.PlaybackComponent — declaration; API 31+ (0 +, 2 *, 0 ~, 0 -)

```
* getLogSessionId(): LogSessionId  [API 31+]
* setLogSessionId(LogSessionId): void  [API 31+]
```

## MediaDrm.ProvisionRequest — declaration; API 18+ (0 +, 0 *, 0 ~, 2 -)

```
- getData(): byte[]  [API 18+]
- getDefaultUrl(): String  [API 18+]
```

## MediaDrm.SecurityLevel — declaration; API 28-34, removed in 35, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

## MediaDrm.SessionException — declaration; API 29+ (0 +, 0 *, 0 ~, 6 -)

```
- SessionException(int, String)  [API 29+]
- getErrorCode(): int  [API 29+, deprecated in 31]
- getErrorContext(): int  [API 29+]
- getOemError(): int  [API 29+]
- getVendorError(): int  [API 29+]
- isTransient(): boolean  [API 31+]
```

## MediaDrmException — declaration; API 18+ (1 +, 0 *, 0 ~, 3 -)

```
+ MediaDrmException(String)  [API 18+]
- getErrorContext(): int  [API 18+]
- getOemError(): int  [API 18+]
- getVendorError(): int  [API 18+]
```

## MediaDrmResetException — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ MediaDrmResetException(String)  [API 23+]
```

## MediaDrmThrowable — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- getErrorContext(): int  [API 34+]
- getOemError(): int  [API 34+]
- getVendorError(): int  [API 34+]
```

## MediaExtractor — declaration; API 16+ (0 +, 3 *, 0 ~, 29 -)

```
* MediaExtractor()  [API 16+]
- advance(): boolean  [API 16+]
- finalize(): void  [API 16+]
- getAudioPresentations(int): List  [API 28+]
- getCachedDuration(): long  [API 16+]
- getCasInfo(int): MediaExtractor.CasInfo  [API 26+]
- getDrmInitData(): DrmInitData  [API 24+]
* getLogSessionId(): LogSessionId  [API 31+]
- getMetrics(): PersistableBundle  [API 26+]
- getPsshInfo(): Map  [API 18+]
- getSampleCryptoInfo(MediaCodec.CryptoInfo): boolean  [API 16+]
- getSampleFlags(): int  [API 16+]
- getSampleSize(): long  [API 28+]
- getSampleTime(): long  [API 16+]
- getSampleTrackIndex(): int  [API 16+]
- getTrackCount(): int  [API 16+]
- getTrackFormat(int): MediaFormat  [API 16+]
- hasCacheReachedEndOfStream(): boolean  [API 16+]
- readSampleData(ByteBuffer, int): int  [API 16+]
- release(): void  [API 16+]
- seekTo(long, int): void  [API 16+]
- selectTrack(int): void  [API 16+]
- setDataSource(AssetFileDescriptor): void  [API 24+]
- setDataSource(Context, Uri, Map): void  [API 16+]
- setDataSource(FileDescriptor): void  [API 16+]
- setDataSource(FileDescriptor, long, long): void  [API 16+]
- setDataSource(MediaDataSource): void  [API 23+]
- setDataSource(String): void  [API 16+]
- setDataSource(String, Map): void  [API 16+]
* setLogSessionId(LogSessionId): void  [API 31+]
- setMediaCas(MediaCas): void  [API 26+, deprecated in 33]
- unselectTrack(int): void  [API 16+]
```

## MediaExtractor.CasInfo — declaration; API 26+ (0 +, 0 *, 0 ~, 3 -)

```
- getPrivateData(): byte[]  [API 29+]
- getSession(): MediaCas.Session  [API 26+]
- getSystemId(): int  [API 26+]
```

## MediaExtractor.MetricsConstants — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## MediaFeature — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ MediaFeature()  [API 31+]
```

## MediaFeature.HdrType — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## MediaFormat — declaration; API 16+ (0 +, 0 *, 0 ~, 32 -)

```
- MediaFormat()  [API 16+]
- MediaFormat(MediaFormat)  [API 29+]
- containsFeature(String): boolean  [API 29+]
- containsKey(String): boolean  [API 16+]
- createAudioFormat(String, int, int): MediaFormat [static]  [API 16+]
- createSubtitleFormat(String, String): MediaFormat [static]  [API 19+]
- createVideoFormat(String, int, int): MediaFormat [static]  [API 16+]
- getByteBuffer(String): ByteBuffer  [API 16+]
- getByteBuffer(String, ByteBuffer): ByteBuffer  [API 29+]
- getFeatureEnabled(String): boolean  [API 21+]
- getFeatures(): Set  [API 29+]
- getFloat(String): float  [API 16+]
- getFloat(String, float): float  [API 29+]
- getInteger(String): int  [API 16+]
- getInteger(String, int): int  [API 29+]
- getKeys(): Set  [API 29+]
- getLong(String): long  [API 16+]
- getLong(String, long): long  [API 29+]
- getNumber(String): Number  [API 29+]
- getNumber(String, Number): Number  [API 29+]
- getString(String): String  [API 16+]
- getString(String, String): String  [API 29+]
- getValueTypeForKey(String): int  [API 29+]
- removeFeature(String): void  [API 29+]
- removeKey(String): void  [API 29+]
- setByteBuffer(String, ByteBuffer): void  [API 16+]
- setFeatureEnabled(String, boolean): void  [API 21+]
- setFloat(String, float): void  [API 16+]
- setInteger(String, int): void  [API 16+]
- setLong(String, long): void  [API 16+]
- setString(String, String): void  [API 16+]
- toString(): String  [API 16+]
```

## MediaFormat.QpOffsetRect — declaration; API 35+ (0 +, 0 *, 0 ~, 4 -)

```
- QpOffsetRect(Rect, int)  [API 35+]
- flattenToString(): String  [API 35+]
- flattenToString(List): String [static]  [API 35+]
- set(Rect, int): void  [API 35+]
```

## MediaMetadata — declaration; API 21+ (0 +, 6 *, 0 ~, 8 -)

```
+ CREATOR [field]  [API 21+]
- containsKey(String): boolean  [API 21+]
* describeContents(): int  [API 21+]
* equals(Object): boolean  [API 21+]
- getBitmap(String): Bitmap  [API 21+]
* getBitmapDimensionLimit(): int  [API 31+]
* getDescription(): MediaDescription  [API 21+]
- getLong(String): long  [API 21+]
- getRating(String): Rating  [API 21+]
- getString(String): String  [API 21+]
- getText(String): CharSequence  [API 21+]
* hashCode(): int  [API 21+]
- keySet(): Set  [API 21+]
- size(): int  [API 21+]
* writeToParcel(Parcel, int): void  [API 21+]
```

## MediaMetadata.Builder — declaration; API 21+ (0 +, 4 *, 0 ~, 5 -)

```
* Builder()  [API 21+]
* Builder(MediaMetadata)  [API 21+]
* build(): MediaMetadata  [API 21+]
- putBitmap(String, Bitmap): MediaMetadata.Builder  [API 21+]
- putLong(String, long): MediaMetadata.Builder  [API 21+]
- putRating(String, Rating): MediaMetadata.Builder  [API 21+]
- putString(String, String): MediaMetadata.Builder  [API 21+]
- putText(String, CharSequence): MediaMetadata.Builder  [API 21+]
* setBitmapDimensionLimit(int): MediaMetadata.Builder  [API 31+]
```

## MediaMetadataEditor — declaration; API 19+, deprecated in 21 (1 +, 0 *, 0 ~, 12 -)

```
- addEditableKey(int): void  [API 19+]
+ apply(): void  [API 19+]
- clear(): void  [API 19+]
- getBitmap(int, Bitmap): Bitmap  [API 19+]
- getEditableKeys(): int[]  [API 19+]
- getLong(int, long): long  [API 19+]
- getObject(int, Object): Object  [API 19+]
- getString(int, String): String  [API 19+]
- putBitmap(int, Bitmap): MediaMetadataEditor  [API 19+]
- putLong(int, long): MediaMetadataEditor  [API 19+]
- putObject(int, Object): MediaMetadataEditor  [API 19+]
- putString(int, String): MediaMetadataEditor  [API 19+]
- removeEditableKeys(): void  [API 19+]
```

## MediaMetadataRetriever — declaration; API 10+ (0 +, 0 *, 0 ~, 26 -)

```
- MediaMetadataRetriever()  [API 10+]
- close(): void  [API 10+]
- extractMetadata(int): String  [API 10+]
- finalize(): void  [API 10+]
- getEmbeddedPicture(): byte[]  [API 10+]
- getFrameAtIndex(int): Bitmap  [API 28+]
- getFrameAtIndex(int, MediaMetadataRetriever.BitmapParams): Bitmap  [API 28+]
- getFrameAtTime(): Bitmap  [API 10+]
- getFrameAtTime(long): Bitmap  [API 10+]
- getFrameAtTime(long, int): Bitmap  [API 10+]
- getFrameAtTime(long, int, MediaMetadataRetriever.BitmapParams): Bitmap  [API 30+]
- getFramesAtIndex(int, int): List  [API 28+]
- getFramesAtIndex(int, int, MediaMetadataRetriever.BitmapParams): List  [API 28+]
- getImageAtIndex(int): Bitmap  [API 28+]
- getImageAtIndex(int, MediaMetadataRetriever.BitmapParams): Bitmap  [API 28+]
- getPrimaryImage(): Bitmap  [API 28+]
- getPrimaryImage(MediaMetadataRetriever.BitmapParams): Bitmap  [API 28+]
- getScaledFrameAtTime(long, int, int, int): Bitmap  [API 27+]
- getScaledFrameAtTime(long, int, int, int, MediaMetadataRetriever.BitmapParams): Bitmap  [API 30+]
- release(): void  [API 10+]
- setDataSource(Context, Uri): void  [API 10+]
- setDataSource(FileDescriptor): void  [API 10+]
- setDataSource(FileDescriptor, long, long): void  [API 10+]
- setDataSource(MediaDataSource): void  [API 23+]
- setDataSource(String): void  [API 10+]
- setDataSource(String, Map): void  [API 14+]
```

## MediaMetadataRetriever.BitmapParams — declaration; API 28+ (0 +, 3 *, 0 ~, 1 -)

```
* BitmapParams()  [API 28+]
- getActualConfig(): Bitmap.Config  [API 28+]
* getPreferredConfig(): Bitmap.Config  [API 28+]
* setPreferredConfig(Bitmap.Config): void  [API 28+]
```

## MediaMuxer — declaration; API 18+ (0 +, 0 *, 0 ~, 10 -)

```
- MediaMuxer(FileDescriptor, int)  [API 26+]
- MediaMuxer(String, int)  [API 18+]
- addTrack(MediaFormat): int  [API 18+]
- finalize(): void  [API 18+]
- release(): void  [API 18+]
- setLocation(float, float): void  [API 19+]
- setOrientationHint(int): void  [API 18+]
- start(): void  [API 18+]
- stop(): void  [API 18+]
- writeSampleData(int, ByteBuffer, MediaCodec.BufferInfo): void  [API 18+]
```

## MediaMuxer.OutputFormat — declaration; API 18+ (0 +, 0 *, 0 ~, 0 -)

## MediaParser — declaration; API 30+ (0 +, 2 *, 0 ~, 9 -)

```
- advance(MediaParser.SeekableInputReader): boolean  [API 30+]
- create(MediaParser.OutputConsumer, String[]): MediaParser [static]  [API 30+]
- createByName(String, MediaParser.OutputConsumer): MediaParser [static]  [API 30+]
* getLogSessionId(): LogSessionId  [API 31+]
- getParserName(): String  [API 30+]
- getParserNames(MediaFormat): List [static]  [API 30+]
- release(): void  [API 30+]
- seek(MediaParser.SeekPoint): void  [API 30+]
* setLogSessionId(LogSessionId): void  [API 31+]
- setParameter(String, Object): MediaParser  [API 30+]
- supportsParameter(String): boolean  [API 30+]
```

## MediaParser.InputReader — declaration; API 30+ (3 +, 0 *, 0 ~, 0 -)

```
+ getLength(): long  [API 30+]
+ getPosition(): long  [API 30+]
+ read(byte[], int, int): int  [API 30+]
```

## MediaParser.OutputConsumer — declaration; API 30+ (5 +, 0 *, 0 ~, 0 -)

```
+ onSampleCompleted(int, long, int, int, int, MediaCodec.CryptoInfo): void  [API 30+]
+ onSampleDataFound(int, MediaParser.InputReader): void  [API 30+]
+ onSeekMapFound(MediaParser.SeekMap): void  [API 30+]
+ onTrackCountFound(int): void  [API 30+]
+ onTrackDataFound(int, MediaParser.TrackData): void  [API 30+]
```

## MediaParser.ParsingException — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## MediaParser.SeekMap — declaration; API 30+ (0 +, 0 *, 0 ~, 3 -)

```
- getDurationMicros(): long  [API 30+]
- getSeekPoints(long): Pair  [API 30+]
- isSeekable(): boolean  [API 30+]
```

## MediaParser.SeekPoint — declaration; API 30+ (0 +, 0 *, 0 ~, 3 -)

```
- START [field]  [API 30+]
- equals(Object): boolean  [API 30+]
- hashCode(): int  [API 30+]
- position [field]  [API 30+]
- timeMicros [field]  [API 30+]
- toString(): String  [API 30+]
```

## MediaParser.SeekableInputReader — declaration; API 30+ (1 +, 0 *, 0 ~, 0 -)

```
+ seekToPosition(long): void  [API 30+]
```

## MediaParser.TrackData — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

```
- drmInitData [field]  [API 30+]
- mediaFormat [field]  [API 30+]
```

## MediaParser.UnrecognizedInputFormatException — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## MediaPlayer — framework; API 1+ (26 +, 0 *, 9 ~, 58 -)

```
+ MediaPlayer()  [API 1+]
- MediaPlayer(Context)  [API 34+]
- addOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener, Handler): void  [API 1+]
- addTimedTextSource(Context, Uri, String): void  [API 16+]
- addTimedTextSource(FileDescriptor, String): void  [API 16+]
- addTimedTextSource(FileDescriptor, long, long, String): void  [API 16+]
- addTimedTextSource(String, String): void  [API 16+]
- attachAuxEffect(int): void  [API 9+]
- clearOnMediaTimeDiscontinuityListener(): void  [API 28+]
- clearOnSubtitleDataListener(): void  [API 28+]
~ create(Context, Uri): MediaPlayer [static]  [API 1+]
- create(Context, Uri, SurfaceHolder): MediaPlayer [static]  [API 1+]
- create(Context, Uri, SurfaceHolder, AudioAttributes, int): MediaPlayer [static]  [API 21+]
+ create(Context, int): MediaPlayer [static]  [API 1+]
- create(Context, int, AudioAttributes, int): MediaPlayer [static]  [API 21+]
- createVolumeShaper(VolumeShaper.Configuration): VolumeShaper  [API 1+]
- deselectTrack(int): void  [API 16+]
+ finalize(): void  [API 1+]
+ getAudioSessionId(): int  [API 9+]
+ getCurrentPosition(): int  [API 1+]
- getDrmInfo(): MediaPlayer.DrmInfo  [API 26+]
- getDrmPropertyString(String): String  [API 26+]
+ getDuration(): int  [API 1+]
- getKeyRequest(byte[], byte[], String, int, Map): MediaDrm.KeyRequest  [API 26+]
- getMetrics(): PersistableBundle  [API 26+]
- getPlaybackParams(): PlaybackParams  [API 23+]
- getPreferredDevice(): AudioDeviceInfo  [API 1+]
- getRoutedDevice(): AudioDeviceInfo  [API 1+]
- getSelectedTrack(int): int  [API 21+]
- getSyncParams(): SyncParams  [API 23+]
- getTimestamp(): MediaTimestamp  [API 23+]
- getTrackInfo(): MediaPlayer.TrackInfo[]  [API 16+]
~ getVideoHeight(): int  [API 1+]
~ getVideoWidth(): int  [API 1+]
+ isLooping(): boolean  [API 3+]
+ isPlaying(): boolean  [API 1+]
+ pause(): void  [API 1+]
+ prepare(): void  [API 1+]
+ prepareAsync(): void  [API 1+]
- prepareDrm(UUID): void  [API 26+]
- provideKeyResponse(byte[], byte[]): byte[]  [API 26+]
+ release(): void  [API 1+]
- releaseDrm(): void  [API 26+]
- removeOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener): void  [API 1+]
+ reset(): void  [API 1+]
- restoreKeys(byte[]): void  [API 26+]
+ seekTo(int): void  [API 1+]
- seekTo(long, int): void  [API 26+]
- selectTrack(int): void  [API 16+]
- setAudioAttributes(AudioAttributes): void  [API 21+]
- setAudioSessionId(int): void  [API 9+]
~ setAudioStreamType(int): void  [API 1+, deprecated in 26]
- setAuxEffectSendLevel(float): void  [API 9+]
- setDataSource(AssetFileDescriptor): void  [API 24+]
+ setDataSource(Context, Uri): void  [API 1+]
- setDataSource(Context, Uri, Map): void  [API 14+]
- setDataSource(Context, Uri, Map, List): void  [API 26+]
+ setDataSource(FileDescriptor): void  [API 1+]
+ setDataSource(FileDescriptor, long, long): void  [API 1+]
- setDataSource(MediaDataSource): void  [API 23+]
+ setDataSource(String): void  [API 1+]
- setDisplay(SurfaceHolder): void  [API 1+]
- setDrmPropertyString(String, String): void  [API 26+]
+ setLooping(boolean): void  [API 1+]
- setNextMediaPlayer(MediaPlayer): void  [API 16+]
~ setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener): void  [API 1+]
+ setOnCompletionListener(MediaPlayer.OnCompletionListener): void  [API 1+]
- setOnDrmConfigHelper(MediaPlayer.OnDrmConfigHelper): void  [API 26+]
- setOnDrmInfoListener(MediaPlayer.OnDrmInfoListener): void  [API 26+]
- setOnDrmInfoListener(MediaPlayer.OnDrmInfoListener, Handler): void  [API 26+]
- setOnDrmPreparedListener(MediaPlayer.OnDrmPreparedListener): void  [API 26+]
- setOnDrmPreparedListener(MediaPlayer.OnDrmPreparedListener, Handler): void  [API 26+]
+ setOnErrorListener(MediaPlayer.OnErrorListener): void  [API 1+]
~ setOnInfoListener(MediaPlayer.OnInfoListener): void  [API 3+]
- setOnMediaTimeDiscontinuityListener(MediaPlayer.OnMediaTimeDiscontinuityListener): void  [API 28+]
- setOnMediaTimeDiscontinuityListener(MediaPlayer.OnMediaTimeDiscontinuityListener, Handler): void  [API 28+]
+ setOnPreparedListener(MediaPlayer.OnPreparedListener): void  [API 1+]
+ setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener): void  [API 1+]
- setOnSubtitleDataListener(MediaPlayer.OnSubtitleDataListener): void  [API 28+]
- setOnSubtitleDataListener(MediaPlayer.OnSubtitleDataListener, Handler): void  [API 28+]
- setOnTimedMetaDataAvailableListener(MediaPlayer.OnTimedMetaDataAvailableListener): void  [API 23+]
- setOnTimedTextListener(MediaPlayer.OnTimedTextListener): void  [API 16+]
~ setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener): void  [API 3+]
- setPlaybackParams(PlaybackParams): void  [API 23+]
- setPreferredDevice(AudioDeviceInfo): boolean  [API 1+]
~ setScreenOnWhilePlaying(boolean): void  [API 1+]
- setSurface(Surface): void  [API 14+]
- setSyncParams(SyncParams): void  [API 23+]
- setVideoScalingMode(int): void  [API 16+]
+ setVolume(float, float): void  [API 1+]
~ setWakeMode(Context, int): void  [API 1+]
+ start(): void  [API 1+]
+ stop(): void  [API 1+]
```

## MediaPlayer.DrmInfo — declaration; API 26+ (0 +, 0 *, 0 ~, 2 -)

```
- getPssh(): Map  [API 26+]
- getSupportedSchemes(): UUID[]  [API 26+]
```

## MediaPlayer.MetricsConstants — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## MediaPlayer.NoDrmSchemeException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ NoDrmSchemeException(String)  [API 26+]
```

## MediaPlayer.OnBufferingUpdateListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onBufferingUpdate(MediaPlayer, int): void  [API 1+]
```

## MediaPlayer.OnCompletionListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCompletion(MediaPlayer): void  [API 1+]
```

## MediaPlayer.OnDrmConfigHelper — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDrmConfig(MediaPlayer): void  [API 26+]
```

## MediaPlayer.OnDrmInfoListener — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDrmInfo(MediaPlayer, MediaPlayer.DrmInfo): void  [API 26+]
```

## MediaPlayer.OnDrmPreparedListener — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDrmPrepared(MediaPlayer, int): void  [API 26+]
```

## MediaPlayer.OnErrorListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onError(MediaPlayer, int, int): boolean  [API 1+]
```

## MediaPlayer.OnInfoListener — framework; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ onInfo(MediaPlayer, int, int): boolean  [API 3+]
```

## MediaPlayer.OnMediaTimeDiscontinuityListener — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onMediaTimeDiscontinuity(MediaPlayer, MediaTimestamp): void  [API 28+]
```

## MediaPlayer.OnPreparedListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPrepared(MediaPlayer): void  [API 1+]
```

## MediaPlayer.OnSeekCompleteListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSeekComplete(MediaPlayer): void  [API 1+]
```

## MediaPlayer.OnSubtitleDataListener — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSubtitleData(MediaPlayer, SubtitleData): void  [API 28+]
```

## MediaPlayer.OnTimedMetaDataAvailableListener — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTimedMetaDataAvailable(MediaPlayer, TimedMetaData): void  [API 23+]
```

## MediaPlayer.OnTimedTextListener — declaration; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTimedText(MediaPlayer, TimedText): void  [API 16+]
```

## MediaPlayer.OnVideoSizeChangedListener — framework; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ onVideoSizeChanged(MediaPlayer, int, int): void  [API 3+]
```

## MediaPlayer.ProvisioningNetworkErrorException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ ProvisioningNetworkErrorException(String)  [API 26+]
```

## MediaPlayer.ProvisioningServerErrorException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ ProvisioningServerErrorException(String)  [API 26+]
```

## MediaPlayer.TrackInfo — declaration; API 16+ (0 +, 0 *, 0 ~, 6 -)

```
- describeContents(): int  [API 16+]
- getFormat(): MediaFormat  [API 19+]
- getLanguage(): String  [API 16+]
- getTrackType(): int  [API 16+]
- toString(): String  [API 16+]
- writeToParcel(Parcel, int): void  [API 16+]
```

## MediaRecorder — declaration; API 1+ (0 +, 0 *, 0 ~, 61 -)

```
- MediaRecorder()  [API 1+, deprecated in 31]
- MediaRecorder(Context)  [API 31+]
- addOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener, Handler): void  [API 1+]
- finalize(): void  [API 1+]
- getActiveMicrophones(): List  [API 28+]
- getActiveRecordingConfiguration(): AudioRecordingConfiguration  [API 1+]
- getAudioSourceMax(): int [static]  [API 4+]
- getLogSessionId(): LogSessionId  [API 31+]
- getMaxAmplitude(): int  [API 1+]
- getMetrics(): PersistableBundle  [API 26+]
- getPreferredDevice(): AudioDeviceInfo  [API 1+]
- getRoutedDevice(): AudioDeviceInfo  [API 1+]
- getSurface(): Surface  [API 21+]
- isPrivacySensitive(): boolean  [API 30+]
- pause(): void  [API 24+]
- prepare(): void  [API 1+]
- registerAudioRecordingCallback(Executor, AudioManager.AudioRecordingCallback): void  [API 1+]
- release(): void  [API 1+]
- removeOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener): void  [API 1+]
- reset(): void  [API 1+]
- resume(): void  [API 24+]
- setAudioChannels(int): void  [API 8+]
- setAudioEncoder(int): void  [API 1+]
- setAudioEncodingBitRate(int): void  [API 8+]
- setAudioProfile(EncoderProfiles.AudioProfile): void  [API 31+]
- setAudioSamplingRate(int): void  [API 8+]
- setAudioSource(int): void  [API 1+]
- setAuxiliaryOutputFile(FileDescriptor): void  [API 11-15, removed in 16, deprecated in 15]
- setAuxiliaryOutputFile(String): void  [API 11-15, removed in 16, deprecated in 15]
- setCamera(Camera): void  [API 3+, deprecated in 21]
- setCaptureRate(double): void  [API 11+]
- setInputSurface(Surface): void  [API 23+]
- setLocation(float, float): void  [API 14+]
- setLogSessionId(LogSessionId): void  [API 31+]
- setMaxDuration(int): void  [API 3+]
- setMaxFileSize(long): void  [API 3+]
- setNextOutputFile(File): void  [API 26+]
- setNextOutputFile(FileDescriptor): void  [API 26+]
- setOnErrorListener(MediaRecorder.OnErrorListener): void  [API 3+]
- setOnInfoListener(MediaRecorder.OnInfoListener): void  [API 3+]
- setOrientationHint(int): void  [API 9+]
- setOutputFile(File): void  [API 26+]
- setOutputFile(FileDescriptor): void  [API 3+]
- setOutputFile(String): void  [API 1+]
- setOutputFormat(int): void  [API 1+]
- setPreferredDevice(AudioDeviceInfo): boolean  [API 1+]
- setPreferredMicrophoneDirection(int): boolean  [API 1+]
- setPreferredMicrophoneFieldDimension(float): boolean  [API 1+]
- setPreviewDisplay(Surface): void  [API 1+]
- setPrivacySensitive(boolean): void  [API 30+]
- setProfile(CamcorderProfile): void  [API 8+]
- setVideoEncoder(int): void  [API 3+]
- setVideoEncodingBitRate(int): void  [API 8+]
- setVideoEncodingProfileLevel(int, int): void  [API 26+]
- setVideoFrameRate(int): void  [API 3+]
- setVideoProfile(EncoderProfiles.VideoProfile): void  [API 31+]
- setVideoSize(int, int): void  [API 3+]
- setVideoSource(int): void  [API 3+]
- start(): void  [API 1+]
- stop(): void  [API 1+]
- unregisterAudioRecordingCallback(AudioManager.AudioRecordingCallback): void  [API 1+]
```

## MediaRecorder.AudioEncoder — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## MediaRecorder.AudioSource — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## MediaRecorder.MetricsConstants — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## MediaRecorder.OnErrorListener — declaration; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ onError(MediaRecorder, int, int): void  [API 3+]
```

## MediaRecorder.OnInfoListener — declaration; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ onInfo(MediaRecorder, int, int): void  [API 3+]
```

## MediaRecorder.OutputFormat — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## MediaRecorder.VideoEncoder — declaration; API 3+ (0 +, 0 *, 0 ~, 0 -)

## MediaRecorder.VideoSource — declaration; API 3+ (0 +, 0 *, 0 ~, 0 -)

## MediaRoute2Info — declaration; API 30+ (0 +, 20 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getClientPackageName(): String  [API 30+]
* getConnectionState(): int  [API 30+]
* getDeduplicationIds(): Set  [API 34+]
* getDescription(): CharSequence  [API 30+]
* getExtras(): Bundle  [API 30+]
* getFeatures(): List  [API 30+]
* getIconUri(): Uri  [API 30+]
* getId(): String  [API 30+]
* getName(): CharSequence  [API 30+]
* getSuitabilityStatus(): int  [API 35+]
* getType(): int  [API 34+]
* getVolume(): int  [API 30+]
* getVolumeHandling(): int  [API 30+]
* getVolumeMax(): int  [API 30+]
* hashCode(): int  [API 30+]
* isSystemRoute(): boolean  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## MediaRoute2Info.Builder — declaration; API 30+ (0 +, 16 *, 0 ~, 3 -)

```
* Builder(MediaRoute2Info)  [API 30+]
* Builder(String, CharSequence)  [API 30+]
* addFeature(String): MediaRoute2Info.Builder  [API 30+]
- addFeatures(Collection): MediaRoute2Info.Builder  [API 30+]
* build(): MediaRoute2Info  [API 30+]
* clearFeatures(): MediaRoute2Info.Builder  [API 30+]
* setClientPackageName(String): MediaRoute2Info.Builder  [API 30+]
* setConnectionState(int): MediaRoute2Info.Builder  [API 30+]
* setDeduplicationIds(Set): MediaRoute2Info.Builder  [API 34+]
* setDescription(CharSequence): MediaRoute2Info.Builder  [API 30+]
* setExtras(Bundle): MediaRoute2Info.Builder  [API 30+]
* setIconUri(Uri): MediaRoute2Info.Builder  [API 30+]
* setSuitabilityStatus(int): MediaRoute2Info.Builder  [API 35+]
* setType(int): MediaRoute2Info.Builder  [API 34+]
- setVisibilityPublic(): MediaRoute2Info.Builder  [API 34+]
- setVisibilityRestricted(Set): MediaRoute2Info.Builder  [API 34+]
* setVolume(int): MediaRoute2Info.Builder  [API 30+]
* setVolumeHandling(int): MediaRoute2Info.Builder  [API 30+]
* setVolumeMax(int): MediaRoute2Info.Builder  [API 30+]
```

## MediaRoute2ProviderService — declaration; API 30+ (8 +, 0 *, 0 ~, 9 -)

```
- MediaRoute2ProviderService()  [API 30+]
- getAllSessionInfo(): List  [API 30+]
- getSessionInfo(String): RoutingSessionInfo  [API 30+]
- notifyRequestFailed(long, int): void  [API 30+]
- notifyRoutes(Collection): void  [API 30+]
- notifySessionCreated(long, RoutingSessionInfo): void  [API 30+]
- notifySessionReleased(String): void  [API 30+]
- notifySessionUpdated(RoutingSessionInfo): void  [API 30+]
- onBind(Intent): IBinder  [API 30+]
+ onCreateSession(long, String, String, Bundle): void  [API 30+]
+ onDeselectRoute(long, String, String): void  [API 30+]
+ onDiscoveryPreferenceChanged(RouteDiscoveryPreference): void  [API 30+]
+ onReleaseSession(long, String): void  [API 30+]
+ onSelectRoute(long, String, String): void  [API 30+]
+ onSetRouteVolume(long, String, int): void  [API 30+]
+ onSetSessionVolume(long, String, int): void  [API 30+]
+ onTransferToRoute(long, String, String): void  [API 30+]
```

## MediaRouter — framework; API 16+ (0 +, 0 *, 0 ~, 16 -)

```
- addCallback(int, MediaRouter.Callback): void  [API 16+]
- addCallback(int, MediaRouter.Callback, int): void  [API 18+]
- addUserRoute(MediaRouter.UserRouteInfo): void  [API 16+]
- clearUserRoutes(): void  [API 16+]
- createRouteCategory(CharSequence, boolean): MediaRouter.RouteCategory  [API 16+]
- createRouteCategory(int, boolean): MediaRouter.RouteCategory  [API 16+]
- createUserRoute(MediaRouter.RouteCategory): MediaRouter.UserRouteInfo  [API 16+]
- getCategoryAt(int): MediaRouter.RouteCategory  [API 16+]
- getCategoryCount(): int  [API 16+]
- getDefaultRoute(): MediaRouter.RouteInfo  [API 18+]
- getRouteAt(int): MediaRouter.RouteInfo  [API 16+]
- getRouteCount(): int  [API 16+]
- getSelectedRoute(int): MediaRouter.RouteInfo  [API 16+]
- removeCallback(MediaRouter.Callback): void  [API 16+]
- removeUserRoute(MediaRouter.UserRouteInfo): void  [API 16+]
- selectRoute(int, MediaRouter.RouteInfo): void  [API 16+]
```

## MediaRouter.Callback — framework; API 16+ (10 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 16+]
+ onRouteAdded(MediaRouter, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteChanged(MediaRouter, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteGrouped(MediaRouter, MediaRouter.RouteInfo, MediaRouter.RouteGroup, int): void  [API 16+]
+ onRoutePresentationDisplayChanged(MediaRouter, MediaRouter.RouteInfo): void  [API 17+]
+ onRouteRemoved(MediaRouter, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteSelected(MediaRouter, int, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteUngrouped(MediaRouter, MediaRouter.RouteInfo, MediaRouter.RouteGroup): void  [API 16+]
+ onRouteUnselected(MediaRouter, int, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteVolumeChanged(MediaRouter, MediaRouter.RouteInfo): void  [API 16+]
```

## MediaRouter.RouteCategory — framework; API 16+ (0 +, 0 *, 0 ~, 6 -)

```
- getName(): CharSequence  [API 16+]
- getName(Context): CharSequence  [API 16+]
- getRoutes(List): List  [API 16+]
- getSupportedTypes(): int  [API 16+]
- isGroupable(): boolean  [API 16+]
- toString(): String  [API 16+]
```

## MediaRouter.RouteGroup — framework; API 16+ (0 +, 1 *, 0 ~, 10 -)

```
- addRoute(MediaRouter.RouteInfo): void  [API 16+]
- addRoute(MediaRouter.RouteInfo, int): void  [API 16+]
- getRouteAt(int): MediaRouter.RouteInfo  [API 16+]
- getRouteCount(): int  [API 16+]
- removeRoute(MediaRouter.RouteInfo): void  [API 16+]
- removeRoute(int): void  [API 16+]
- requestSetVolume(int): void  [API 16+]
- requestUpdateVolume(int): void  [API 16+]
* setIconDrawable(Drawable): void  [API 16+]
- setIconResource(int): void  [API 16+]
- toString(): String  [API 16+]
```

## MediaRouter.RouteInfo — framework; API 16+ (0 +, 11 *, 0 ~, 11 -)

```
- getCategory(): MediaRouter.RouteCategory  [API 16+]
* getDescription(): CharSequence  [API 18+]
- getDeviceType(): int  [API 24+]
- getGroup(): MediaRouter.RouteGroup  [API 16+]
* getIconDrawable(): Drawable  [API 16+]
* getName(): CharSequence  [API 16+]
- getName(Context): CharSequence  [API 16+]
* getPlaybackStream(): int  [API 16+]
* getPlaybackType(): int  [API 16+]
- getPresentationDisplay(): Display  [API 17+]
* getStatus(): CharSequence  [API 16+]
- getSupportedTypes(): int  [API 16+]
* getTag(): Object  [API 16+]
* getVolume(): int  [API 16+]
* getVolumeHandling(): int  [API 16+]
* getVolumeMax(): int  [API 16+]
- isConnecting(): boolean  [API 18+]
- isEnabled(): boolean  [API 17+]
- requestSetVolume(int): void  [API 16+]
- requestUpdateVolume(int): void  [API 16+]
* setTag(Object): void  [API 16+]
- toString(): String  [API 16+]
```

## MediaRouter.SimpleCallback — framework; API 16+ (9 +, 0 *, 0 ~, 0 -)

```
+ SimpleCallback()  [API 16+]
+ onRouteAdded(MediaRouter, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteChanged(MediaRouter, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteGrouped(MediaRouter, MediaRouter.RouteInfo, MediaRouter.RouteGroup, int): void  [API 16+]
+ onRouteRemoved(MediaRouter, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteSelected(MediaRouter, int, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteUngrouped(MediaRouter, MediaRouter.RouteInfo, MediaRouter.RouteGroup): void  [API 16+]
+ onRouteUnselected(MediaRouter, int, MediaRouter.RouteInfo): void  [API 16+]
+ onRouteVolumeChanged(MediaRouter, MediaRouter.RouteInfo): void  [API 16+]
```

## MediaRouter.UserRouteInfo — framework; API 16+ (0 +, 11 *, 0 ~, 5 -)

```
* getRemoteControlClient(): RemoteControlClient  [API 16+]
- requestSetVolume(int): void  [API 16+]
- requestUpdateVolume(int): void  [API 16+]
* setDescription(CharSequence): void  [API 18+]
* setIconDrawable(Drawable): void  [API 16+]
- setIconResource(int): void  [API 16+]
* setName(CharSequence): void  [API 16+]
- setName(int): void  [API 16+]
* setPlaybackStream(int): void  [API 16+]
* setPlaybackType(int): void  [API 16+]
* setRemoteControlClient(RemoteControlClient): void  [API 16+]
* setStatus(CharSequence): void  [API 16+]
* setVolume(int): void  [API 16+]
- setVolumeCallback(MediaRouter.VolumeCallback): void  [API 16+]
* setVolumeHandling(int): void  [API 16+]
* setVolumeMax(int): void  [API 16+]
```

## MediaRouter.VolumeCallback — framework; API 16+ (3 +, 0 *, 0 ~, 0 -)

```
+ VolumeCallback()  [API 16+]
+ onVolumeSetRequest(MediaRouter.RouteInfo, int): void  [API 16+]
+ onVolumeUpdateRequest(MediaRouter.RouteInfo, int): void  [API 16+]
```

## MediaRouter2 — declaration; API 30+ (0 +, 2 *, 0 ~, 21 -)

```
- cancelScanRequest(MediaRouter2.ScanToken): void  [API 35+]
- getController(String): MediaRouter2.RoutingController  [API 31+]
- getControllers(): List  [API 30+]
- getInstance(Context): MediaRouter2 [static]  [API 30+]
- getInstance(Context, String, Executor, Runnable): MediaRouter2 [static]  [API 35+]
* getRouteListingPreference(): RouteListingPreference  [API 35+]
- getRoutes(): List  [API 30+]
- getSystemController(): MediaRouter2.RoutingController  [API 30+]
- registerControllerCallback(Executor, MediaRouter2.ControllerCallback): void  [API 30+]
- registerRouteCallback(Executor, MediaRouter2.RouteCallback, RouteDiscoveryPreference): void  [API 30+]
- registerRouteListingPreferenceUpdatedCallback(Executor, Consumer): void  [API 35+]
- registerTransferCallback(Executor, MediaRouter2.TransferCallback): void  [API 30+]
- requestScan(MediaRouter2.ScanRequest): MediaRouter2.ScanToken  [API 35+]
- setOnGetControllerHintsListener(MediaRouter2.OnGetControllerHintsListener): void  [API 30+]
* setRouteListingPreference(RouteListingPreference): void  [API 34+]
- setRouteVolume(MediaRoute2Info, int): void  [API 35+]
- showSystemOutputSwitcher(): boolean  [API 34+]
- stop(): void  [API 30+]
- transferTo(MediaRoute2Info): void  [API 30+]
- unregisterControllerCallback(MediaRouter2.ControllerCallback): void  [API 30+]
- unregisterRouteCallback(MediaRouter2.RouteCallback): void  [API 30+]
- unregisterRouteListingPreferenceUpdatedCallback(Consumer): void  [API 35+]
- unregisterTransferCallback(MediaRouter2.TransferCallback): void  [API 30+]
```

## MediaRouter2.ControllerCallback — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ ControllerCallback()  [API 30+]
+ onControllerUpdated(MediaRouter2.RoutingController): void  [API 30+]
```

## MediaRouter2.OnGetControllerHintsListener — declaration; API 30+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGetControllerHints(MediaRoute2Info): Bundle  [API 30+]
```

## MediaRouter2.RouteCallback — declaration; API 30+ (5 +, 0 *, 0 ~, 0 -)

```
+ RouteCallback()  [API 30+]
+ onRoutesAdded(List): void  [API 30+, deprecated in 34]
+ onRoutesChanged(List): void  [API 30+, deprecated in 34]
+ onRoutesRemoved(List): void  [API 30+, deprecated in 34]
+ onRoutesUpdated(List): void  [API 34+]
```

## MediaRouter2.RoutingController — declaration; API 30+ (0 +, 0 *, 0 ~, 17 -)

```
- deselectRoute(MediaRoute2Info): void  [API 30+]
- getControlHints(): Bundle  [API 30+]
- getDeselectableRoutes(): List  [API 30+]
- getId(): String  [API 30+]
- getRoutingSessionInfo(): RoutingSessionInfo  [API 34+]
- getSelectableRoutes(): List  [API 30+]
- getSelectedRoutes(): List  [API 30+]
- getTransferableRoutes(): List  [API 35+]
- getVolume(): int  [API 30+]
- getVolumeHandling(): int  [API 30+]
- getVolumeMax(): int  [API 30+]
- isReleased(): boolean  [API 30+]
- release(): void  [API 30+]
- selectRoute(MediaRoute2Info): void  [API 30+]
- setVolume(int): void  [API 30+]
- toString(): String  [API 30+]
- wasTransferInitiatedBySelf(): boolean  [API 35+]
```

## MediaRouter2.ScanRequest — declaration; API 35+ (0 +, 1 *, 0 ~, 0 -)

```
* isScreenOffScan(): boolean  [API 35+]
```

## MediaRouter2.ScanRequest.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): MediaRouter2.ScanRequest  [API 35+]
* setScreenOffScan(boolean): MediaRouter2.ScanRequest.Builder  [API 35+]
```

## MediaRouter2.ScanToken — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## MediaRouter2.TransferCallback — declaration; API 30+ (4 +, 0 *, 0 ~, 0 -)

```
+ TransferCallback()  [API 30+]
+ onStop(MediaRouter2.RoutingController): void  [API 30+]
+ onTransfer(MediaRouter2.RoutingController, MediaRouter2.RoutingController): void  [API 30+]
+ onTransferFailure(MediaRoute2Info): void  [API 30+]
```

## MediaScannerConnection — declaration; API 1+ (0 +, 0 *, 0 ~, 8 -)

```
- MediaScannerConnection(Context, MediaScannerConnection.MediaScannerConnectionClient)  [API 1+]
- connect(): void  [API 1+]
- disconnect(): void  [API 1+]
- isConnected(): boolean  [API 1+]
- onServiceConnected(ComponentName, IBinder): void  [API 1+]
- onServiceDisconnected(ComponentName): void  [API 1+]
- scanFile(Context, String[], String[], MediaScannerConnection.OnScanCompletedListener): void [static]  [API 8+]
- scanFile(String, String): void  [API 1+]
```

## MediaScannerConnection.MediaScannerConnectionClient — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ onMediaScannerConnected(): void  [API 1+]
+ onScanCompleted(String, Uri): void  [API 1-29, removed in 30]
```

## MediaScannerConnection.OnScanCompletedListener — declaration; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ onScanCompleted(String, Uri): void  [API 8+]
```

## MediaSession2 — declaration; API 29+ (0 +, 3 *, 0 ~, 6 -)

```
- broadcastSessionCommand(Session2Command, Bundle): void  [API 29+]
- cancelSessionCommand(MediaSession2.ControllerInfo, Object): void  [API 29+]
- close(): void  [API 29+]
- getConnectedControllers(): List  [API 29+]
* getId(): String  [API 29+]
- getToken(): Session2Token  [API 29+]
* isPlaybackActive(): boolean  [API 29+]
- sendSessionCommand(MediaSession2.ControllerInfo, Session2Command, Bundle): Object  [API 29+]
* setPlaybackActive(boolean): void  [API 29+]
```

## MediaSession2.Builder — declaration; API 29+ (0 +, 2 *, 0 ~, 4 -)

```
- Builder(Context)  [API 29+]
* build(): MediaSession2  [API 29+]
- setExtras(Bundle): MediaSession2.Builder  [API 29+]
* setId(String): MediaSession2.Builder  [API 29+]
- setSessionActivity(PendingIntent): MediaSession2.Builder  [API 29+]
- setSessionCallback(Executor, MediaSession2.SessionCallback): MediaSession2.Builder  [API 29+]
```

## MediaSession2.ControllerInfo — declaration; API 29+ (0 +, 0 *, 0 ~, 7 -)

```
- equals(Object): boolean  [API 29+]
- getConnectionHints(): Bundle  [API 29+]
- getPackageName(): String  [API 29+]
- getRemoteUserInfo(): MediaSessionManager.RemoteUserInfo  [API 29+]
- getUid(): int  [API 29+]
- hashCode(): int  [API 29+]
- toString(): String  [API 29+]
```

## MediaSession2.SessionCallback — declaration; API 29+ (3 +, 0 *, 0 ~, 3 -)

```
- SessionCallback()  [API 29+]
+ onCommandResult(MediaSession2, MediaSession2.ControllerInfo, Object, Session2Command, Session2Command.Result): void  [API 29+]
- onConnect(MediaSession2, MediaSession2.ControllerInfo): Session2CommandGroup  [API 29+]
+ onDisconnected(MediaSession2, MediaSession2.ControllerInfo): void  [API 29+]
+ onPostConnect(MediaSession2, MediaSession2.ControllerInfo): void  [API 29+]
- onSessionCommand(MediaSession2, MediaSession2.ControllerInfo, Session2Command, Bundle): Session2Command.Result  [API 29+]
```

## MediaSession2Service — declaration; API 29+ (4 +, 0 *, 0 ~, 5 -)

```
- MediaSession2Service()  [API 29+]
- addSession(MediaSession2): void  [API 29+]
- getSessions(): List  [API 29+]
- onBind(Intent): IBinder  [API 29+]
+ onCreate(): void  [API 29+]
+ onDestroy(): void  [API 29+]
+ onGetSession(MediaSession2.ControllerInfo): MediaSession2  [API 29+]
+ onUpdateNotification(MediaSession2): MediaSession2Service.MediaNotification  [API 29+]
- removeSession(MediaSession2): void  [API 29+]
```

## MediaSession2Service.MediaNotification — declaration; API 29+ (0 +, 3 *, 0 ~, 0 -)

```
* MediaNotification(int, Notification)  [API 29+]
* getNotification(): Notification  [API 29+]
* getNotificationId(): int  [API 29+]
```

## MediaSync — declaration; API 23+ (0 +, 5 *, 0 ~, 10 -)

```
* MediaSync()  [API 23+]
- createInputSurface(): Surface  [API 23+]
- finalize(): void  [API 23+]
- flush(): void  [API 23+]
* getPlaybackParams(): PlaybackParams  [API 23+]
* getSyncParams(): SyncParams  [API 23+]
- getTimestamp(): MediaTimestamp  [API 23+]
- queueAudio(ByteBuffer, int, long): void  [API 23+]
- release(): void  [API 23+]
- setAudioTrack(AudioTrack): void  [API 23+]
- setCallback(MediaSync.Callback, Handler): void  [API 23+]
- setOnErrorListener(MediaSync.OnErrorListener, Handler): void  [API 23+]
* setPlaybackParams(PlaybackParams): void  [API 23+]
- setSurface(Surface): void  [API 23+]
* setSyncParams(SyncParams): void  [API 23+]
```

## MediaSync.Callback — declaration; API 23+ (2 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 23+]
+ onAudioBufferConsumed(MediaSync, ByteBuffer, int): void  [API 23+]
```

## MediaSync.OnErrorListener — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onError(MediaSync, int, int): void  [API 23+]
```

## MediaSyncEvent — declaration; API 16+ (0 +, 2 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 31+]
- createEvent(int): MediaSyncEvent [static]  [API 16+]
- describeContents(): int  [API 16+]
- equals(Object): boolean  [API 16+]
* getAudioSessionId(): int  [API 16+]
- getType(): int  [API 16+]
- hashCode(): int  [API 16+]
* setAudioSessionId(int): MediaSyncEvent  [API 16+]
- toString(): String  [API 16+]
- writeToParcel(Parcel, int): void  [API 16+]
```

## MediaTimestamp — declaration; API 23+ (0 +, 0 *, 0 ~, 7 -)

```
- MediaTimestamp(long, long, float)  [API 29+]
- TIMESTAMP_UNKNOWN [field]  [API 28+]
- equals(Object): boolean  [API 23+]
- getAnchorMediaTimeUs(): long  [API 23+]
- getAnchorSystemNanoTime(): long  [API 29+]
- getAnchorSytemNanoTime(): long  [API 23+, deprecated in 29]
- getMediaClockRate(): float  [API 23+]
- toString(): String  [API 23+]
```

## MicrophoneDirection — declaration; API 29+ (2 +, 0 *, 0 ~, 0 -)

```
+ setPreferredMicrophoneDirection(int): boolean  [API 29+]
+ setPreferredMicrophoneFieldDimension(float): boolean  [API 29+]
```

## MicrophoneInfo — declaration; API 28+ (0 +, 0 *, 0 ~, 15 -)

```
- ORIENTATION_UNKNOWN [field]  [API 28+]
- POSITION_UNKNOWN [field]  [API 28+]
- getAddress(): String  [API 28+]
- getChannelMapping(): List  [API 28+]
- getDescription(): String  [API 28+]
- getDirectionality(): int  [API 28+]
- getFrequencyResponse(): List  [API 28+]
- getGroup(): int  [API 28+]
- getId(): int  [API 28+]
- getIndexInTheGroup(): int  [API 28+]
- getLocation(): int  [API 28+]
- getMaxSpl(): float  [API 28+]
- getMinSpl(): float  [API 28+]
- getOrientation(): MicrophoneInfo.Coordinate3F  [API 28+]
- getPosition(): MicrophoneInfo.Coordinate3F  [API 28+]
- getSensitivity(): float  [API 28+]
- getType(): int  [API 28+]
```

## MicrophoneInfo.Coordinate3F — declaration; API 28+ (0 +, 0 *, 0 ~, 1 -)

```
- equals(Object): boolean  [API 28+]
- x [field]  [API 28+]
- y [field]  [API 28+]
- z [field]  [API 28+]
```

## NotProvisionedException — declaration; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ NotProvisionedException(String)  [API 18+]
```

## PlaybackParams — declaration; API 23+ (0 +, 9 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 23+]
* PlaybackParams()  [API 23+]
- allowDefaults(): PlaybackParams  [API 23+]
* describeContents(): int  [API 23+]
* getAudioFallbackMode(): int  [API 23+]
* getPitch(): float  [API 23+]
* getSpeed(): float  [API 23+]
* setAudioFallbackMode(int): PlaybackParams  [API 23+]
* setPitch(float): PlaybackParams  [API 23+]
* setSpeed(float): PlaybackParams  [API 23+]
* writeToParcel(Parcel, int): void  [API 23+]
```

## Rating — declaration; API 19+ (0 +, 0 *, 0 ~, 14 -)

```
- CREATOR [field]  [API 19+]
- describeContents(): int  [API 19+]
- getPercentRating(): float  [API 19+]
- getRatingStyle(): int  [API 19+]
- getStarRating(): float  [API 19+]
- hasHeart(): boolean  [API 19+]
- isRated(): boolean  [API 19+]
- isThumbUp(): boolean  [API 19+]
- newHeartRating(boolean): Rating [static]  [API 19+]
- newPercentageRating(float): Rating [static]  [API 19+]
- newStarRating(int, float): Rating [static]  [API 19+]
- newThumbRating(boolean): Rating [static]  [API 19+]
- newUnratedRating(int): Rating [static]  [API 19+]
- toString(): String  [API 19+]
- writeToParcel(Parcel, int): void  [API 19+]
```

## RemoteControlClient — declaration; API 14+, deprecated in 21 (0 +, 0 *, 0 ~, 10 -)

```
- RemoteControlClient(PendingIntent)  [API 14+]
- RemoteControlClient(PendingIntent, Looper)  [API 14+]
- editMetadata(boolean): RemoteControlClient.MetadataEditor  [API 14+]
- getMediaSession(): MediaSession  [API 21+]
- setMetadataUpdateListener(RemoteControlClient.OnMetadataUpdateListener): void  [API 19+]
- setOnGetPlaybackPositionListener(RemoteControlClient.OnGetPlaybackPositionListener): void  [API 18+]
- setPlaybackPositionUpdateListener(RemoteControlClient.OnPlaybackPositionUpdateListener): void  [API 18+]
- setPlaybackState(int): void  [API 14+]
- setPlaybackState(int, long, float): void  [API 18+]
- setTransportControlFlags(int): void  [API 14+]
```

## RemoteControlClient.MetadataEditor — declaration; API 14+, deprecated in 21 (0 +, 0 *, 0 ~, 7 -)

```
- apply(): void  [API 14+]
- clear(): void  [API 14+]
- clone(): Object  [API 14+]
- putBitmap(int, Bitmap): RemoteControlClient.MetadataEditor  [API 14+]
- putLong(int, long): RemoteControlClient.MetadataEditor  [API 14+]
- putObject(int, Object): RemoteControlClient.MetadataEditor  [API 21+]
- putString(int, String): RemoteControlClient.MetadataEditor  [API 14+]
```

## RemoteControlClient.OnGetPlaybackPositionListener — declaration; API 18+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onGetPlaybackPosition(): long  [API 18+]
```

## RemoteControlClient.OnMetadataUpdateListener — declaration; API 19+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onMetadataUpdate(int, Object): void  [API 19+]
```

## RemoteControlClient.OnPlaybackPositionUpdateListener — declaration; API 18+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onPlaybackPositionUpdate(long): void  [API 18+]
```

## RemoteController — declaration; API 19+, deprecated in 21 (0 +, 0 *, 0 ~, 9 -)

```
- RemoteController(Context, RemoteController.OnClientUpdateListener)  [API 19+]
- RemoteController(Context, RemoteController.OnClientUpdateListener, Looper)  [API 19+]
- clearArtworkConfiguration(): boolean  [API 19+]
- editMetadata(): RemoteController.MetadataEditor  [API 19+]
- getEstimatedMediaPosition(): long  [API 19+]
- seekTo(long): boolean  [API 19+]
- sendMediaKeyEvent(KeyEvent): boolean  [API 19+]
- setArtworkConfiguration(int, int): boolean  [API 19+]
- setSynchronizationMode(int): boolean  [API 19+]
```

## RemoteController.MetadataEditor — declaration; API 19+, deprecated in 29 (0 +, 0 *, 0 ~, 1 -)

```
- apply(): void  [API 19+]
```

## RemoteController.OnClientUpdateListener — declaration; API 19+, deprecated in 29 (5 +, 0 *, 0 ~, 0 -)

```
+ onClientChange(boolean): void  [API 19+]
+ onClientMetadataUpdate(RemoteController.MetadataEditor): void  [API 19+]
+ onClientPlaybackStateUpdate(int): void  [API 19+]
+ onClientPlaybackStateUpdate(int, long, long, float): void  [API 19+]
+ onClientTransportControlUpdate(int): void  [API 19+]
```

## ResourceBusyException — declaration; API 19+ (1 +, 0 *, 0 ~, 0 -)

```
+ ResourceBusyException(String)  [API 19+]
```

## Ringtone — declaration; API 1+ (0 +, 8 *, 0 ~, 7 -)

```
- finalize(): void  [API 1+]
* getAudioAttributes(): AudioAttributes  [API 21+]
* getStreamType(): int  [API 1+, deprecated in 21]
- getTitle(Context): String  [API 1+]
* getVolume(): float  [API 28+]
- isHapticGeneratorEnabled(): boolean  [API 31+]
* isLooping(): boolean  [API 28+]
- isPlaying(): boolean  [API 1+]
- play(): void  [API 1+]
* setAudioAttributes(AudioAttributes): void  [API 21+]
- setHapticGeneratorEnabled(boolean): boolean  [API 31+]
* setLooping(boolean): void  [API 28+]
* setStreamType(int): void  [API 1+, deprecated in 21]
* setVolume(float): void  [API 28+]
- stop(): void  [API 1+]
```

## RingtoneManager — declaration; API 1+ (0 +, 0 *, 0 ~, 24 -)

```
- RingtoneManager(Activity)  [API 1+]
- RingtoneManager(Context)  [API 1+]
- getActualDefaultRingtoneUri(Context, int): Uri [static]  [API 1+]
- getCursor(): Cursor  [API 1+]
- getDefaultType(Uri): int [static]  [API 1+]
- getDefaultUri(int): Uri [static]  [API 1+]
- getIncludeDrm(): boolean  [API 1+, deprecated in 19]
- getRingtone(Context, Uri): Ringtone [static]  [API 1+]
- getRingtone(int): Ringtone  [API 1+]
- getRingtonePosition(Uri): int  [API 1+]
- getRingtoneUri(int): Uri  [API 1+]
- getStopPreviousRingtone(): boolean  [API 1+]
- getValidRingtoneUri(Context): Uri [static]  [API 1+]
- hasHapticChannels(Context, Uri): boolean [static]  [API 33+]
- hasHapticChannels(Uri): boolean [static]  [API 29+]
- hasHapticChannels(int): boolean  [API 29+]
- inferStreamType(): int  [API 3+]
- isDefault(Uri): boolean [static]  [API 1+]
- openDefaultRingtoneUri(Context, Uri): AssetFileDescriptor [static]  [API 29+]
- setActualDefaultRingtoneUri(Context, int, Uri): void [static]  [API 1+]
- setIncludeDrm(boolean): void  [API 1+, deprecated in 19]
- setStopPreviousRingtone(boolean): void  [API 1+]
- setType(int): void  [API 1+]
- stopPreviousRingtone(): void  [API 1+]
```

## RouteDiscoveryPreference — declaration; API 30+ (0 +, 6 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 30+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getPreferredFeatures(): List  [API 30+]
* hashCode(): int  [API 30+]
- shouldPerformActiveScan(): boolean  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## RouteDiscoveryPreference.Builder — declaration; API 30+ (0 +, 3 *, 0 ~, 2 -)

```
- Builder(List, boolean)  [API 30+]
* Builder(RouteDiscoveryPreference)  [API 30+]
* build(): RouteDiscoveryPreference  [API 30+]
* setPreferredFeatures(List): RouteDiscoveryPreference.Builder  [API 30+]
- setShouldPerformActiveScan(boolean): RouteDiscoveryPreference.Builder  [API 30+]
```

## RouteListingPreference — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getItems(): List  [API 34+]
* getLinkedItemComponentName(): ComponentName  [API 34+]
* getUseSystemOrdering(): boolean  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## RouteListingPreference.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): RouteListingPreference  [API 34+]
* setItems(List): RouteListingPreference.Builder  [API 34+]
* setLinkedItemComponentName(ComponentName): RouteListingPreference.Builder  [API 34+]
* setUseSystemOrdering(boolean): RouteListingPreference.Builder  [API 34+]
```

## RouteListingPreference.Item — declaration; API 34+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getCustomSubtextMessage(): CharSequence  [API 34+]
* getFlags(): int  [API 34+]
* getRouteId(): String  [API 34+]
* getSelectionBehavior(): int  [API 34+]
* getSubText(): int  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## RouteListingPreference.Item.Builder — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder(String)  [API 34+]
* build(): RouteListingPreference.Item  [API 34+]
* setCustomSubtextMessage(CharSequence): RouteListingPreference.Item.Builder  [API 34+]
* setFlags(int): RouteListingPreference.Item.Builder  [API 34+]
* setSelectionBehavior(int): RouteListingPreference.Item.Builder  [API 34+]
* setSubText(int): RouteListingPreference.Item.Builder  [API 34+]
```

## RoutingSessionInfo — declaration; API 30+ (0 +, 17 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getClientPackageName(): String  [API 30+]
* getControlHints(): Bundle  [API 30+]
* getDeselectableRoutes(): List  [API 30+]
* getId(): String  [API 30+]
* getName(): CharSequence  [API 30+]
* getSelectableRoutes(): List  [API 30+]
* getSelectedRoutes(): List  [API 30+]
* getTransferReason(): int  [API 35+]
* getTransferableRoutes(): List  [API 30+]
* getVolume(): int  [API 30+]
* getVolumeHandling(): int  [API 30+]
* getVolumeMax(): int  [API 30+]
* hashCode(): int  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## RoutingSessionInfo.Builder — declaration; API 30+ (0 +, 17 *, 0 ~, 5 -)

```
* Builder(RoutingSessionInfo)  [API 30+]
* Builder(String, String)  [API 30+]
* addDeselectableRoute(String): RoutingSessionInfo.Builder  [API 30+]
* addSelectableRoute(String): RoutingSessionInfo.Builder  [API 30+]
* addSelectedRoute(String): RoutingSessionInfo.Builder  [API 30+]
* addTransferableRoute(String): RoutingSessionInfo.Builder  [API 30+]
* build(): RoutingSessionInfo  [API 30+]
* clearDeselectableRoutes(): RoutingSessionInfo.Builder  [API 30+]
* clearSelectableRoutes(): RoutingSessionInfo.Builder  [API 30+]
* clearSelectedRoutes(): RoutingSessionInfo.Builder  [API 30+]
* clearTransferableRoutes(): RoutingSessionInfo.Builder  [API 30+]
- removeDeselectableRoute(String): RoutingSessionInfo.Builder  [API 30+]
- removeSelectableRoute(String): RoutingSessionInfo.Builder  [API 30+]
- removeSelectedRoute(String): RoutingSessionInfo.Builder  [API 30+]
- removeTransferableRoute(String): RoutingSessionInfo.Builder  [API 30+]
* setControlHints(Bundle): RoutingSessionInfo.Builder  [API 30+]
* setName(CharSequence): RoutingSessionInfo.Builder  [API 30+]
- setTransferInitiator(UserHandle, String): RoutingSessionInfo.Builder  [API 35+]
* setTransferReason(int): RoutingSessionInfo.Builder  [API 35+]
* setVolume(int): RoutingSessionInfo.Builder  [API 30+]
* setVolumeHandling(int): RoutingSessionInfo.Builder  [API 30+]
* setVolumeMax(int): RoutingSessionInfo.Builder  [API 30+]
```

## Session2Command — declaration; API 29+ (0 +, 2 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 29+]
- Session2Command(String, Bundle)  [API 29+]
* Session2Command(int)  [API 29+]
- describeContents(): int  [API 29+]
- equals(Object): boolean  [API 29+]
* getCommandCode(): int  [API 29+]
- getCustomAction(): String  [API 29+]
- getCustomExtras(): Bundle  [API 29+]
- hashCode(): int  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## Session2Command.Result — declaration; API 29+ (0 +, 3 *, 0 ~, 0 -)

```
* Result(int, Bundle)  [API 29+]
* getResultCode(): int  [API 29+]
* getResultData(): Bundle  [API 29+]
```

## Session2CommandGroup — declaration; API 29+ (0 +, 3 *, 0 ~, 2 -)

```
+ CREATOR [field]  [API 29+]
* describeContents(): int  [API 29+]
* getCommands(): Set  [API 29+]
- hasCommand(Session2Command): boolean  [API 29+]
- hasCommand(int): boolean  [API 29+]
* writeToParcel(Parcel, int): void  [API 29+]
```

## Session2CommandGroup.Builder — declaration; API 29+ (0 +, 3 *, 0 ~, 2 -)

```
* Builder()  [API 29+]
* Builder(Session2CommandGroup)  [API 29+]
- addCommand(Session2Command): Session2CommandGroup.Builder  [API 29+]
* build(): Session2CommandGroup  [API 29+]
- removeCommand(Session2Command): Session2CommandGroup.Builder  [API 29+]
```

## Session2Token — declaration; API 29+ (0 +, 0 *, 0 ~, 11 -)

```
- CREATOR [field]  [API 29+]
- Session2Token(Context, ComponentName)  [API 29+]
- describeContents(): int  [API 29+]
- equals(Object): boolean  [API 29+]
- getExtras(): Bundle  [API 29+]
- getPackageName(): String  [API 29+]
- getServiceName(): String  [API 29+]
- getType(): int  [API 29+]
- getUid(): int  [API 29+]
- hashCode(): int  [API 29+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## SoundPool — framework; API 1+ (16 +, 0 *, 3 ~, 0 -)

```
+ SoundPool(int, int, int)  [API 1+, deprecated in 21]
+ autoPause(): void  [API 8+]
+ autoResume(): void  [API 8+]
+ finalize(): void  [API 1+]
+ load(AssetFileDescriptor, int): int  [API 3+]
+ load(Context, int, int): int  [API 1+]
+ load(FileDescriptor, long, long, int): int  [API 3+]
+ load(String, int): int  [API 1+]
+ pause(int): void  [API 1+]
+ play(int, float, float, int, int, float): int  [API 1+]
+ release(): void  [API 1+]
+ resume(int): void  [API 1+]
~ setLoop(int, int): void  [API 1+]
+ setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener): void  [API 8+]
~ setPriority(int, int): void  [API 1+]
~ setRate(int, float): void  [API 1+]
+ setVolume(int, float, float): void  [API 1+]
+ stop(int): void  [API 1+]
+ unload(int): boolean  [API 1+]
```

## SoundPool.Builder — declaration; API 21+ (0 +, 0 *, 0 ~, 6 -)

```
- Builder()  [API 21+]
- build(): SoundPool  [API 21+]
- setAudioAttributes(AudioAttributes): SoundPool.Builder  [API 21+]
- setAudioSessionId(int): SoundPool.Builder  [API 34+]
- setContext(Context): SoundPool.Builder  [API 34+]
- setMaxStreams(int): SoundPool.Builder  [API 21+]
```

## SoundPool.OnLoadCompleteListener — framework; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ onLoadComplete(SoundPool, int, int): void  [API 8+]
```

## Spatializer — declaration; API 32+ (0 +, 0 *, 0 ~, 9 -)

```
- addOnHeadTrackerAvailableListener(Executor, Spatializer.OnHeadTrackerAvailableListener): void  [API 33+]
- addOnSpatializerStateChangedListener(Executor, Spatializer.OnSpatializerStateChangedListener): void  [API 32+]
- canBeSpatialized(AudioAttributes, AudioFormat): boolean  [API 32+]
- getImmersiveAudioLevel(): int  [API 32+]
- isAvailable(): boolean  [API 32+]
- isEnabled(): boolean  [API 32+]
- isHeadTrackerAvailable(): boolean  [API 33+]
- removeOnHeadTrackerAvailableListener(Spatializer.OnHeadTrackerAvailableListener): void  [API 33+]
- removeOnSpatializerStateChangedListener(Spatializer.OnSpatializerStateChangedListener): void  [API 32+]
```

## Spatializer.OnHeadTrackerAvailableListener — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ onHeadTrackerAvailableChanged(Spatializer, boolean): void  [API 33+]
```

## Spatializer.OnSpatializerStateChangedListener — declaration; API 32+ (2 +, 0 *, 0 ~, 0 -)

```
+ onSpatializerAvailableChanged(Spatializer, boolean): void  [API 32+]
+ onSpatializerEnabledChanged(Spatializer, boolean): void  [API 32+]
```

## SubtitleData — declaration; API 28+ (0 +, 5 *, 0 ~, 0 -)

```
* SubtitleData(int, long, long, byte[])  [API 29+]
* getData(): byte[]  [API 28+]
* getDurationUs(): long  [API 28+]
* getStartTimeUs(): long  [API 28+]
* getTrackIndex(): int  [API 28+]
```

## SyncParams — declaration; API 23+ (0 +, 9 *, 0 ~, 1 -)

```
* SyncParams()  [API 23+]
- allowDefaults(): SyncParams  [API 23+]
* getAudioAdjustMode(): int  [API 23+]
* getFrameRate(): float  [API 23+]
* getSyncSource(): int  [API 23+]
* getTolerance(): float  [API 23+]
* setAudioAdjustMode(int): SyncParams  [API 23+]
* setFrameRate(float): SyncParams  [API 23+]
* setSyncSource(int): SyncParams  [API 23+]
* setTolerance(float): SyncParams  [API 23+]
```

## ThumbnailUtils — declaration; API 8+ (0 +, 0 *, 0 ~, 9 -)

```
- ThumbnailUtils()  [API 8+]
- createAudioThumbnail(File, Size, CancellationSignal): Bitmap [static]  [API 29+]
- createAudioThumbnail(String, int): Bitmap [static]  [API 29+, deprecated in 29]
- createImageThumbnail(File, Size, CancellationSignal): Bitmap [static]  [API 29+]
- createImageThumbnail(String, int): Bitmap [static]  [API 29+, deprecated in 29]
- createVideoThumbnail(File, Size, CancellationSignal): Bitmap [static]  [API 29+]
- createVideoThumbnail(String, int): Bitmap [static]  [API 8+, deprecated in 29]
- extractThumbnail(Bitmap, int, int): Bitmap [static]  [API 8+]
- extractThumbnail(Bitmap, int, int, int): Bitmap [static]  [API 8+]
```

## TimedMetaData — declaration; API 23+ (0 +, 0 *, 0 ~, 3 -)

```
- TimedMetaData(long, byte[])  [API 29+]
- getMetaData(): byte[]  [API 23+]
- getTimestamp(): long  [API 23+]
```

## TimedText — declaration; API 16+ (0 +, 0 *, 0 ~, 2 -)

```
- getBounds(): Rect  [API 16+]
- getText(): String  [API 16+]
```

## ToneGenerator — declaration; API 1+ (0 +, 0 *, 0 ~, 7 -)

```
- ToneGenerator(int, int)  [API 1+]
- finalize(): void  [API 1+]
- getAudioSessionId(): int  [API 16+]
- release(): void  [API 1+]
- startTone(int): boolean  [API 1+]
- startTone(int, int): boolean  [API 5+]
- stopTone(): void  [API 1+]
```

## UnsupportedSchemeException — declaration; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ UnsupportedSchemeException(String)  [API 18+]
```

## VolumeAutomation — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ createVolumeShaper(VolumeShaper.Configuration): VolumeShaper  [API 26+]
```

## VolumeProvider — declaration; API 21+ (2 +, 0 *, 0 ~, 7 -)

```
- VolumeProvider(int, int, int)  [API 21+]
- VolumeProvider(int, int, int, String)  [API 30+]
- getCurrentVolume(): int  [API 21+]
- getMaxVolume(): int  [API 21+]
- getVolumeControl(): int  [API 21+]
- getVolumeControlId(): String  [API 30+]
+ onAdjustVolume(int): void  [API 21+]
+ onSetVolumeTo(int): void  [API 21+]
- setCurrentVolume(int): void  [API 21+]
```

## VolumeShaper — declaration; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- apply(VolumeShaper.Operation): void  [API 26+]
- close(): void  [API 26+]
- finalize(): void  [API 26+]
- getVolume(): float  [API 26+]
- replace(VolumeShaper.Configuration, VolumeShaper.Operation, boolean): void  [API 26+]
```

## VolumeShaper.Configuration — declaration; API 26+ (0 +, 9 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 26+]
- CUBIC_RAMP [field]  [API 26+]
- LINEAR_RAMP [field]  [API 26+]
- SCURVE_RAMP [field]  [API 26+]
- SINE_RAMP [field]  [API 26+]
* describeContents(): int  [API 26+]
* equals(Object): boolean  [API 26+]
* getDuration(): long  [API 26+]
* getInterpolatorType(): int  [API 26+]
- getMaximumCurvePoints(): int [static]  [API 26+]
* getTimes(): float[]  [API 26+]
* getVolumes(): float[]  [API 26+]
* hashCode(): int  [API 26+]
* toString(): String  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## VolumeShaper.Configuration.Builder — declaration; API 26+ (0 +, 5 *, 0 ~, 5 -)

```
* Builder()  [API 26+]
* Builder(VolumeShaper.Configuration)  [API 26+]
* build(): VolumeShaper.Configuration  [API 26+]
- invertVolumes(): VolumeShaper.Configuration.Builder  [API 26+]
- reflectTimes(): VolumeShaper.Configuration.Builder  [API 26+]
- scaleToEndVolume(float): VolumeShaper.Configuration.Builder  [API 26+]
- scaleToStartVolume(float): VolumeShaper.Configuration.Builder  [API 26+]
- setCurve(float[], float[]): VolumeShaper.Configuration.Builder  [API 26+]
* setDuration(long): VolumeShaper.Configuration.Builder  [API 26+]
* setInterpolatorType(int): VolumeShaper.Configuration.Builder  [API 26+]
```

## VolumeShaper.Operation — declaration; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 26+]
- PLAY [field]  [API 26+]
- REVERSE [field]  [API 26+]
- describeContents(): int  [API 26+]
- equals(Object): boolean  [API 26+]
- hashCode(): int  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```


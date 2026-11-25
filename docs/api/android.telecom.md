# android.telecom

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Call — declaration; API 23+ (40 +, 0 *, 0 ~, 0 -)

```
+ addConferenceParticipants(List): void  [API 31+]
+ answer(int): void  [API 23+]
+ conference(Call): void  [API 23+]
+ deflect(Uri): void  [API 28+]
+ disconnect(): void  [API 23+]
+ getCannedTextResponses(): List  [API 23+]
+ getChildren(): List  [API 23+]
+ getConferenceableCalls(): List  [API 23+]
+ getDetails(): Call.Details  [API 23+]
+ getGenericConferenceActiveChildCall(): Call  [API 30+]
+ getParent(): Call  [API 23+]
+ getRemainingPostDialSequence(): String  [API 23+]
+ getRttCall(): Call.RttCall  [API 26+]
+ getState(): int  [API 23+, deprecated in 31]
+ getVideoCall(): InCallService.VideoCall  [API 23+]
+ handoverTo(PhoneAccountHandle, int, Bundle): void  [API 28+]
+ hold(): void  [API 23+]
+ isRttActive(): boolean  [API 26+]
+ mergeConference(): void  [API 23+]
+ phoneAccountSelected(PhoneAccountHandle, boolean): void  [API 23+]
+ playDtmfTone(char): void  [API 23+]
+ postDialContinue(boolean): void  [API 23+]
+ pullExternalCall(): void  [API 25+]
+ putExtras(Bundle): void  [API 25+]
+ registerCallback(Call.Callback): void  [API 23+]
+ registerCallback(Call.Callback, Handler): void  [API 23+]
+ reject(boolean, String): void  [API 23+]
+ reject(int): void  [API 30+]
+ removeExtras(List): void  [API 25+]
+ removeExtras(String[]): void  [API 25+]
+ respondToRttRequest(int, boolean): void  [API 26+]
+ sendCallEvent(String, Bundle): void  [API 25+]
+ sendRttRequest(): void  [API 26+]
+ splitFromConference(): void  [API 23+]
+ stopDtmfTone(): void  [API 23+]
+ stopRtt(): void  [API 26+]
+ swapConference(): void  [API 23+]
+ toString(): String  [API 23+]
+ unhold(): void  [API 23+]
+ unregisterCallback(Call.Callback): void  [API 23+]
```

## Call.Callback — declaration; API 23+ (17 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 23+]
+ onCallDestroyed(Call): void  [API 23+]
+ onCannedTextResponsesLoaded(Call, List): void  [API 23+]
+ onChildrenChanged(Call, List): void  [API 23+]
+ onConferenceableCallsChanged(Call, List): void  [API 23+]
+ onConnectionEvent(Call, String, Bundle): void  [API 25+]
+ onDetailsChanged(Call, Call.Details): void  [API 23+]
+ onHandoverComplete(Call): void  [API 28+]
+ onHandoverFailed(Call, int): void  [API 28+]
+ onParentChanged(Call, Call): void  [API 23+]
+ onPostDialWait(Call, String): void  [API 23+]
+ onRttInitiationFailure(Call, int): void  [API 26+]
+ onRttModeChanged(Call, int): void  [API 26+]
+ onRttRequest(Call, int): void  [API 26+]
+ onRttStatusChanged(Call, boolean, Call.RttCall): void  [API 26+]
+ onStateChanged(Call, int): void  [API 23+]
+ onVideoCallChanged(Call, InCallService.VideoCall): void  [API 23+]
```

## Call.Details — declaration; API 23+ (30 +, 0 *, 0 ~, 0 -)

```
+ can(int): boolean  [API 23+]
+ can(int, int): boolean [static]  [API 23+]
+ capabilitiesToString(int): String [static]  [API 23+]
+ equals(Object): boolean  [API 23+]
+ getAccountHandle(): PhoneAccountHandle  [API 23+]
+ getCallCapabilities(): int  [API 23+]
+ getCallDirection(): int  [API 29+]
+ getCallProperties(): int  [API 23+]
+ getCallerDisplayName(): String  [API 23+]
+ getCallerDisplayNamePresentation(): int  [API 23+]
+ getCallerNumberVerificationStatus(): int  [API 30+]
+ getConnectTimeMillis(): long  [API 23+]
+ getContactDisplayName(): String  [API 30+]
+ getContactPhotoUri(): Uri  [API 34+]
+ getCreationTimeMillis(): long  [API 26+]
+ getDisconnectCause(): DisconnectCause  [API 23+]
+ getExtras(): Bundle  [API 23+]
+ getGatewayInfo(): GatewayInfo  [API 23+]
+ getHandle(): Uri  [API 23+]
+ getHandlePresentation(): int  [API 23+]
+ getId(): String  [API 35+]
+ getIntentExtras(): Bundle  [API 23+]
+ getState(): int  [API 31+]
+ getStatusHints(): StatusHints  [API 23+]
+ getVideoState(): int  [API 23+]
+ hasProperty(int): boolean  [API 23+]
+ hasProperty(int, int): boolean [static]  [API 23+]
+ hashCode(): int  [API 23+]
+ propertiesToString(int): String [static]  [API 23+]
+ toString(): String  [API 23+]
```

## Call.RttCall — declaration; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
+ getRttAudioMode(): int  [API 26+]
+ read(): String  [API 26+]
+ readImmediately(): String  [API 27+]
+ setRttMode(int): void  [API 26+]
+ write(String): void  [API 26+]
```

## CallAttributes — declaration; API 34+ (8 +, 3 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 34+]
+ describeContents(): int  [API 34+]
+ equals(Object): boolean  [API 34+]
+ getAddress(): Uri  [API 34+]
* getCallCapabilities(): int  [API 34+]
* getCallType(): int  [API 34+]
+ getDirection(): int  [API 34+]
+ getDisplayName(): CharSequence  [API 34+]
* getPhoneAccountHandle(): PhoneAccountHandle  [API 34+]
+ hashCode(): int  [API 34+]
+ toString(): String  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## CallAttributes.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
- Builder(PhoneAccountHandle, int, CharSequence, Uri)  [API 34+]
* build(): CallAttributes  [API 34+]
* setCallCapabilities(int): CallAttributes.Builder  [API 34+]
* setCallType(int): CallAttributes.Builder  [API 34+]
```

## CallAudioState — declaration; API 23+ (7 +, 4 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 23+]
* CallAudioState(boolean, int, int)  [API 23+]
+ audioRouteToString(int): String [static]  [API 23+]
+ describeContents(): int  [API 23+]
+ equals(Object): boolean  [API 23+]
+ getActiveBluetoothDevice(): BluetoothDevice  [API 28+]
* getRoute(): int  [API 23+]
+ getSupportedBluetoothDevices(): Collection  [API 28+]
* getSupportedRouteMask(): int  [API 23+]
* isMuted(): boolean  [API 23+]
+ toString(): String  [API 23+]
+ writeToParcel(Parcel, int): void  [API 23+]
```

## CallControl — declaration; API 34+ (10 +, 0 *, 0 ~, 0 -)

```
+ answer(int, Executor, OutcomeReceiver): void  [API 34+]
+ disconnect(DisconnectCause, Executor, OutcomeReceiver): void  [API 34+]
+ getCallId(): ParcelUuid  [API 34+]
+ requestCallEndpointChange(CallEndpoint, Executor, OutcomeReceiver): void  [API 34+]
+ requestMuteState(boolean, Executor, OutcomeReceiver): void  [API 35+]
+ requestVideoState(int, Executor, OutcomeReceiver): void  [API 35+]
+ sendEvent(String, Bundle): void  [API 34+]
+ setActive(Executor, OutcomeReceiver): void  [API 34+]
+ setInactive(Executor, OutcomeReceiver): void  [API 34+]
+ startCallStreaming(Executor, OutcomeReceiver): void  [API 34+]
```

## CallControlCallback — declaration; API 34+ (5 +, 0 *, 0 ~, 0 -)

```
+ onAnswer(int, Consumer): void  [API 34+]
+ onCallStreamingStarted(Consumer): void  [API 34+]
+ onDisconnect(DisconnectCause, Consumer): void  [API 34+]
+ onSetActive(Consumer): void  [API 34+]
+ onSetInactive(Consumer): void  [API 34+]
```

## CallEndpoint — declaration; API 34+ (8 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 34+]
- CallEndpoint(CharSequence, int, ParcelUuid)  [API 34+]
+ describeContents(): int  [API 34+]
+ equals(Object): boolean  [API 34+]
+ getEndpointName(): CharSequence  [API 34+]
+ getEndpointType(): int  [API 34+]
+ getIdentifier(): ParcelUuid  [API 34+]
+ hashCode(): int  [API 34+]
+ toString(): String  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## CallEndpointException — declaration; API 34+ (3 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 34+]
- CallEndpointException(String, int)  [API 34+]
+ describeContents(): int  [API 34+]
+ getCode(): int  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## CallEventCallback — declaration; API 34+ (6 +, 0 *, 0 ~, 0 -)

```
+ onAvailableCallEndpointsChanged(List): void  [API 34+]
+ onCallEndpointChanged(CallEndpoint): void  [API 34+]
+ onCallStreamingFailed(int): void  [API 34+]
+ onEvent(String, Bundle): void  [API 34+]
+ onMuteStateChanged(boolean): void  [API 34+]
+ onVideoStateChanged(int): void  [API 35+]
```

## CallException — declaration; API 34+ (3 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 34+]
- CallException(String, int)  [API 34+]
+ describeContents(): int  [API 34+]
+ getCode(): int  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## CallRedirectionService — declaration; API 29+ (7 +, 0 *, 0 ~, 1 -)

```
- CallRedirectionService()  [API 29+]
+ cancelCall(): void  [API 29+]
+ onBind(Intent): IBinder  [API 29+]
+ onPlaceCall(Uri, PhoneAccountHandle, boolean): void  [API 29+]
+ onRedirectionTimeout(): void  [API 33+]
+ onUnbind(Intent): boolean  [API 29+]
+ placeCallUnmodified(): void  [API 29+]
+ redirectCall(Uri, PhoneAccountHandle, boolean): void  [API 29+]
```

## CallScreeningService — declaration; API 24+ (4 +, 0 *, 0 ~, 1 -)

```
- CallScreeningService()  [API 24+]
+ onBind(Intent): IBinder  [API 24+]
+ onScreenCall(Call.Details): void  [API 24+]
+ onUnbind(Intent): boolean  [API 24+]
+ respondToCall(Call.Details, CallScreeningService.CallResponse): void  [API 24+]
```

## CallScreeningService.CallResponse — declaration; API 24+ (0 +, 8 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 24+]
* getCallComposerAttachmentsToShow(): int  [API 31+]
* getDisallowCall(): boolean  [API 24+]
* getRejectCall(): boolean  [API 24+]
* getSilenceCall(): boolean  [API 29+]
* getSkipCallLog(): boolean  [API 24+]
* getSkipNotification(): boolean  [API 24+]
* hashCode(): int  [API 24+]
```

## CallScreeningService.CallResponse.Builder — declaration; API 24+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder()  [API 24+]
* build(): CallScreeningService.CallResponse  [API 24+]
* setCallComposerAttachmentsToShow(int): CallScreeningService.CallResponse.Builder  [API 31+]
* setDisallowCall(boolean): CallScreeningService.CallResponse.Builder  [API 24+]
* setRejectCall(boolean): CallScreeningService.CallResponse.Builder  [API 24+]
* setSilenceCall(boolean): CallScreeningService.CallResponse.Builder  [API 29+]
* setSkipCallLog(boolean): CallScreeningService.CallResponse.Builder  [API 24+]
* setSkipNotification(boolean): CallScreeningService.CallResponse.Builder  [API 24+]
```

## Conference — declaration; API 23+ (43 +, 16 *, 0 ~, 1 -)

```
- Conference(PhoneAccountHandle)  [API 23+]
+ addConnection(Connection): boolean  [API 23+]
+ createFailedConference(DisconnectCause, PhoneAccountHandle): Conference [static]  [API 31+]
+ destroy(): void  [API 23+]
+ getCallAudioState(): CallAudioState  [API 23+, deprecated in 34]
* getConferenceableConnections(): List  [API 23+]
* getConnectionCapabilities(): int  [API 23+]
* getConnectionProperties(): int  [API 25+]
* getConnectionStartElapsedRealtimeMillis(): long  [API 30+]
* getConnectionTime(): long  [API 23+]
+ getConnections(): List  [API 23+]
+ getCurrentCallEndpoint(): CallEndpoint  [API 34+]
+ getDisconnectCause(): DisconnectCause  [API 23+]
* getExtras(): Bundle  [API 23+]
+ getPhoneAccountHandle(): PhoneAccountHandle  [API 23+]
+ getState(): int  [API 23+]
* getStatusHints(): StatusHints  [API 23+]
+ getVideoProvider(): Connection.VideoProvider  [API 23+]
+ getVideoState(): int  [API 23+]
* isRingbackRequested(): boolean  [API 31+]
+ onAddConferenceParticipants(List): void  [API 31+]
+ onAnswer(int): void  [API 31+]
+ onAvailableCallEndpointsChanged(List): void  [API 34+]
+ onCallAudioStateChanged(CallAudioState): void  [API 23+, deprecated in 34]
+ onCallEndpointChanged(CallEndpoint): void  [API 34+]
+ onConnectionAdded(Connection): void  [API 23+]
+ onDisconnect(): void  [API 23+]
+ onExtrasChanged(Bundle): void  [API 25+]
+ onHold(): void  [API 23+]
+ onMerge(): void  [API 23+]
+ onMerge(Connection): void  [API 23+]
+ onMuteStateChanged(boolean): void  [API 34+]
+ onPlayDtmfTone(char): void  [API 23+]
+ onReject(): void  [API 31+]
+ onSeparate(Connection): void  [API 23+]
+ onStopDtmfTone(): void  [API 23+]
+ onSwap(): void  [API 23+]
+ onUnhold(): void  [API 23+]
+ putExtras(Bundle): void  [API 25+]
+ removeConnection(Connection): void  [API 23+]
+ removeExtras(List): void  [API 25+]
+ removeExtras(String[]): void  [API 25+]
+ sendConferenceEvent(String, Bundle): void  [API 30+]
+ setActive(): void  [API 23+]
* setConferenceableConnections(List): void  [API 23+]
* setConnectionCapabilities(int): void  [API 23+]
* setConnectionProperties(int): void  [API 25+]
+ setConnectionStartElapsedRealTime(long): void  [API 28+, deprecated in 30]
* setConnectionStartElapsedRealtimeMillis(long): void  [API 30+]
* setConnectionTime(long): void  [API 23+]
+ setDialing(): void  [API 23+]
+ setDisconnected(DisconnectCause): void  [API 23+]
* setExtras(Bundle): void  [API 23+]
+ setOnHold(): void  [API 23+]
* setRingbackRequested(boolean): void  [API 31+]
+ setRinging(): void  [API 31+]
* setStatusHints(StatusHints): void  [API 23+]
+ setVideoProvider(Connection, Connection.VideoProvider): void  [API 23+]
+ setVideoState(Connection, int): void  [API 23+]
+ toString(): String  [API 23+]
```

## Conferenceable — declaration; API 23+ (0 +, 0 *, 0 ~, 0 -)

## Connection — declaration; API 23+ (92 +, 0 *, 0 ~, 1 -)

```
- Connection()  [API 23+]
+ capabilitiesToString(int): String [static]  [API 23+]
+ createCanceledConnection(): Connection [static]  [API 23+]
+ createFailedConnection(DisconnectCause): Connection [static]  [API 23+]
+ destroy(): void  [API 23+]
+ getAddress(): Uri  [API 23+]
+ getAddressPresentation(): int  [API 23+]
+ getAudioModeIsVoip(): boolean  [API 23+]
+ getCallAudioState(): CallAudioState  [API 23+, deprecated in 34]
+ getCallerDisplayName(): String  [API 23+]
+ getCallerDisplayNamePresentation(): int  [API 23+]
+ getCallerNumberVerificationStatus(): int  [API 30+]
+ getConference(): Conference  [API 23+]
+ getConferenceables(): List  [API 23+]
+ getConnectionCapabilities(): int  [API 23+]
+ getConnectionProperties(): int  [API 25+]
+ getCurrentCallEndpoint(): CallEndpoint  [API 34+]
+ getDisconnectCause(): DisconnectCause  [API 23+]
+ getExtras(): Bundle  [API 23+]
+ getState(): int  [API 23+]
+ getStatusHints(): StatusHints  [API 23+]
+ getVideoProvider(): Connection.VideoProvider  [API 23+]
+ getVideoState(): int  [API 30+]
+ handleRttUpgradeResponse(Connection.RttTextStream): void  [API 28+]
+ isRingbackRequested(): boolean  [API 23+]
+ notifyConferenceMergeFailed(): void  [API 30+]
+ onAbort(): void  [API 23+]
+ onAddConferenceParticipants(List): void  [API 31+]
+ onAnswer(): void  [API 23+]
+ onAnswer(int): void  [API 23+]
+ onAvailableCallEndpointsChanged(List): void  [API 34+]
+ onCallAudioStateChanged(CallAudioState): void  [API 23+, deprecated in 34]
+ onCallEndpointChanged(CallEndpoint): void  [API 34+]
+ onCallEvent(String, Bundle): void  [API 25+]
+ onDeflect(Uri): void  [API 28+]
+ onDisconnect(): void  [API 23+]
+ onExtrasChanged(Bundle): void  [API 25+]
+ onHandoverComplete(): void  [API 28+]
+ onHold(): void  [API 23+]
+ onMuteStateChanged(boolean): void  [API 34+]
+ onPlayDtmfTone(char): void  [API 23+]
+ onPostDialContinue(boolean): void  [API 23+]
+ onPullExternalCall(): void  [API 25+]
+ onReject(): void  [API 23+]
+ onReject(String): void  [API 24+]
+ onReject(int): void  [API 30+]
+ onSeparate(): void  [API 23+]
+ onShowIncomingCallUi(): void  [API 26+]
+ onSilence(): void  [API 29+]
+ onStartRtt(Connection.RttTextStream): void  [API 28+]
+ onStateChanged(int): void  [API 23+]
+ onStopDtmfTone(): void  [API 23+]
+ onStopRtt(): void  [API 28+]
+ onTrackedByNonUiService(boolean): void  [API 31+]
+ onUnhold(): void  [API 23+]
+ onUsingAlternativeUi(boolean): void  [API 31+]
+ propertiesToString(int): String [static]  [API 25+]
+ putExtras(Bundle): void  [API 25+]
+ queryLocationForEmergency(long, String, Executor, OutcomeReceiver): void  [API 34+]
+ removeExtras(List): void  [API 25+]
+ removeExtras(String[]): void  [API 25+]
+ requestBluetoothAudio(BluetoothDevice): void  [API 28+, deprecated in 34]
+ requestCallEndpointChange(CallEndpoint, Executor, OutcomeReceiver): void  [API 34+]
+ sendConnectionEvent(String, Bundle): void  [API 25+]
+ sendRemoteRttRequest(): void  [API 28+]
+ sendRttInitiationFailure(int): void  [API 28+]
+ sendRttInitiationSuccess(): void  [API 28+]
+ sendRttSessionRemotelyTerminated(): void  [API 28+]
+ setActive(): void  [API 23+]
+ setAddress(Uri, int): void  [API 23+]
+ setAudioModeIsVoip(boolean): void  [API 23+]
+ setAudioRoute(int): void  [API 26+, deprecated in 34]
+ setCallerDisplayName(String, int): void  [API 23+]
+ setCallerNumberVerificationStatus(int): void  [API 30+]
+ setConferenceableConnections(List): void  [API 23+]
+ setConferenceables(List): void  [API 23+]
+ setConnectionCapabilities(int): void  [API 23+]
+ setConnectionProperties(int): void  [API 25+]
+ setDialing(): void  [API 23+]
+ setDisconnected(DisconnectCause): void  [API 23+]
+ setExtras(Bundle): void  [API 23+]
+ setInitialized(): void  [API 23+]
+ setInitializing(): void  [API 23+]
+ setNextPostDialChar(char): void  [API 23+]
+ setOnHold(): void  [API 23+]
+ setPostDialWait(String): void  [API 23+]
+ setPulling(): void  [API 25+]
+ setRingbackRequested(boolean): void  [API 23+]
+ setRinging(): void  [API 23+]
+ setStatusHints(StatusHints): void  [API 23+]
+ setVideoProvider(Connection.VideoProvider): void  [API 23+]
+ setVideoState(int): void  [API 23+]
+ stateToString(int): String [static]  [API 23+]
```

## Connection.RttModifyStatus — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## Connection.RttTextStream — declaration; API 28+ (3 +, 0 *, 0 ~, 0 -)

```
+ read(): String  [API 28+]
+ readImmediately(): String  [API 28+]
+ write(String): void  [API 28+]
```

## Connection.VideoProvider — declaration; API 23+ (17 +, 0 *, 0 ~, 1 -)

```
- VideoProvider()  [API 23+]
+ changeCameraCapabilities(VideoProfile.CameraCapabilities): void  [API 23+]
+ changePeerDimensions(int, int): void  [API 23+]
+ changeVideoQuality(int): void  [API 23+]
+ handleCallSessionEvent(int): void  [API 23+]
+ onRequestCameraCapabilities(): void  [API 23+]
+ onRequestConnectionDataUsage(): void  [API 23+]
+ onSendSessionModifyRequest(VideoProfile, VideoProfile): void  [API 23+]
+ onSendSessionModifyResponse(VideoProfile): void  [API 23+]
+ onSetCamera(String): void  [API 23+]
+ onSetDeviceOrientation(int): void  [API 23+]
+ onSetDisplaySurface(Surface): void  [API 23+]
+ onSetPauseImage(Uri): void  [API 23+]
+ onSetPreviewSurface(Surface): void  [API 23+]
+ onSetZoom(float): void  [API 23+]
+ receiveSessionModifyRequest(VideoProfile): void  [API 23+]
+ receiveSessionModifyResponse(int, VideoProfile, VideoProfile): void  [API 23+]
+ setCallDataUsage(long): void  [API 23+]
```

## ConnectionRequest — declaration; API 23+ (11 +, 0 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 23+]
- ConnectionRequest(PhoneAccountHandle, Uri, Bundle)  [API 23+]
- ConnectionRequest(PhoneAccountHandle, Uri, Bundle, int)  [API 23+]
+ describeContents(): int  [API 23+]
+ getAccountHandle(): PhoneAccountHandle  [API 23+]
+ getAddress(): Uri  [API 23+]
+ getExtras(): Bundle  [API 23+]
+ getParticipants(): List  [API 30+]
+ getRttTextStream(): Connection.RttTextStream  [API 28+]
+ getVideoState(): int  [API 23+]
+ isAdhocConferenceCall(): boolean  [API 30+]
+ isRequestingRtt(): boolean  [API 28+]
+ toString(): String  [API 23+]
+ writeToParcel(Parcel, int): void  [API 23+]
```

## ConnectionService — declaration; API 23+ (28 +, 0 *, 0 ~, 1 -)

```
- ConnectionService()  [API 23+]
+ addConference(Conference): void  [API 23+]
+ addExistingConnection(PhoneAccountHandle, Connection): void  [API 23+]
+ conferenceRemoteConnections(RemoteConnection, RemoteConnection): void  [API 23+]
+ connectionServiceFocusReleased(): void  [API 28+]
+ createRemoteIncomingConference(PhoneAccountHandle, ConnectionRequest): RemoteConference  [API 31+]
+ createRemoteIncomingConnection(PhoneAccountHandle, ConnectionRequest): RemoteConnection  [API 23+]
+ createRemoteOutgoingConference(PhoneAccountHandle, ConnectionRequest): RemoteConference  [API 31+]
+ createRemoteOutgoingConnection(PhoneAccountHandle, ConnectionRequest): RemoteConnection  [API 23+]
+ getAllConferences(): Collection  [API 24+]
+ getAllConnections(): Collection  [API 23+]
+ onBind(Intent): IBinder  [API 23+]
+ onConference(Connection, Connection): void  [API 23+]
+ onConnectionServiceFocusGained(): void  [API 28+]
+ onConnectionServiceFocusLost(): void  [API 28+]
+ onCreateIncomingConference(PhoneAccountHandle, ConnectionRequest): Conference  [API 31+]
+ onCreateIncomingConferenceFailed(PhoneAccountHandle, ConnectionRequest): void  [API 31+]
+ onCreateIncomingConnection(PhoneAccountHandle, ConnectionRequest): Connection  [API 23+]
+ onCreateIncomingConnectionFailed(PhoneAccountHandle, ConnectionRequest): void  [API 26+]
+ onCreateIncomingHandoverConnection(PhoneAccountHandle, ConnectionRequest): Connection  [API 28+]
+ onCreateOutgoingConference(PhoneAccountHandle, ConnectionRequest): Conference  [API 31+]
+ onCreateOutgoingConferenceFailed(PhoneAccountHandle, ConnectionRequest): void  [API 31+]
+ onCreateOutgoingConnection(PhoneAccountHandle, ConnectionRequest): Connection  [API 23+]
+ onCreateOutgoingConnectionFailed(PhoneAccountHandle, ConnectionRequest): void  [API 26+]
+ onCreateOutgoingHandoverConnection(PhoneAccountHandle, ConnectionRequest): Connection  [API 28+]
+ onHandoverFailed(ConnectionRequest, int): void  [API 28+]
+ onRemoteConferenceAdded(RemoteConference): void  [API 23+]
+ onRemoteExistingConnectionAdded(RemoteConnection): void  [API 23+]
+ onUnbind(Intent): boolean  [API 23+]
```

## DisconnectCause — declaration; API 23+ (6 +, 7 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 23+]
* DisconnectCause(int)  [API 23+]
* DisconnectCause(int, CharSequence, CharSequence, String)  [API 23+]
- DisconnectCause(int, CharSequence, CharSequence, String, int)  [API 23+]
* DisconnectCause(int, String)  [API 23+]
+ describeContents(): int  [API 23+]
+ equals(Object): boolean  [API 23+]
* getCode(): int  [API 23+]
* getDescription(): CharSequence  [API 23+]
* getLabel(): CharSequence  [API 23+]
* getReason(): String  [API 23+]
+ getTone(): int  [API 23+]
+ hashCode(): int  [API 23+]
+ toString(): String  [API 23+]
+ writeToParcel(Parcel, int): void  [API 23+]
```

## GatewayInfo — declaration; API 23+ (6 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 23+]
- GatewayInfo(String, Uri, Uri)  [API 23+]
+ describeContents(): int  [API 23+]
+ getGatewayAddress(): Uri  [API 23+]
+ getGatewayProviderPackageName(): String  [API 23+]
+ getOriginalAddress(): Uri  [API 23+]
+ isEmpty(): boolean  [API 23+]
+ writeToParcel(Parcel, int): void  [API 23+]
```

## InCallService — declaration; API 23+ (20 +, 0 *, 0 ~, 1 -)

```
- InCallService()  [API 23+]
+ canAddCall(): boolean  [API 23+]
+ getCallAudioState(): CallAudioState  [API 23+, deprecated in 34]
+ getCalls(): List  [API 23+]
+ getCurrentCallEndpoint(): CallEndpoint  [API 34+]
+ onAvailableCallEndpointsChanged(List): void  [API 34+]
+ onBind(Intent): IBinder  [API 23+]
+ onBringToForeground(boolean): void  [API 23+]
+ onCallAdded(Call): void  [API 23+]
+ onCallAudioStateChanged(CallAudioState): void  [API 23+, deprecated in 34]
+ onCallEndpointChanged(CallEndpoint): void  [API 34+]
+ onCallRemoved(Call): void  [API 23+]
+ onCanAddCallChanged(boolean): void  [API 23+]
+ onConnectionEvent(Call, String, Bundle): void  [API 25+]
+ onMuteStateChanged(boolean): void  [API 34+]
+ onSilenceRinger(): void  [API 24+]
+ onUnbind(Intent): boolean  [API 23+]
+ requestBluetoothAudio(BluetoothDevice): void  [API 28+, deprecated in 34]
+ requestCallEndpointChange(CallEndpoint, Executor, OutcomeReceiver): void  [API 34+]
+ setAudioRoute(int): void  [API 23+, deprecated in 34]
+ setMuted(boolean): void  [API 23+]
```

## InCallService.VideoCall — declaration; API 23+ (14 +, 0 *, 0 ~, 0 -)

```
+ VideoCall()  [API 23+]
+ registerCallback(InCallService.VideoCall.Callback): void  [API 23+]
+ registerCallback(InCallService.VideoCall.Callback, Handler): void  [API 23+]
+ requestCallDataUsage(): void  [API 23+]
+ requestCameraCapabilities(): void  [API 23+]
+ sendSessionModifyRequest(VideoProfile): void  [API 23+]
+ sendSessionModifyResponse(VideoProfile): void  [API 23+]
+ setCamera(String): void  [API 23+]
+ setDeviceOrientation(int): void  [API 23+]
+ setDisplaySurface(Surface): void  [API 23+]
+ setPauseImage(Uri): void  [API 23+]
+ setPreviewSurface(Surface): void  [API 23+]
+ setZoom(float): void  [API 23+]
+ unregisterCallback(InCallService.VideoCall.Callback): void  [API 23+]
```

## InCallService.VideoCall.Callback — declaration; API 23+ (8 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 23+]
+ onCallDataUsageChanged(long): void  [API 23+]
+ onCallSessionEvent(int): void  [API 23+]
+ onCameraCapabilitiesChanged(VideoProfile.CameraCapabilities): void  [API 23+]
+ onPeerDimensionsChanged(int, int): void  [API 23+]
+ onSessionModifyRequestReceived(VideoProfile): void  [API 23+]
+ onSessionModifyResponseReceived(int, VideoProfile, VideoProfile): void  [API 23+]
+ onVideoQualityChanged(int): void  [API 23+]
```

## PhoneAccount — declaration; API 23+ (5 +, 17 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 23+]
+ builder(PhoneAccountHandle, CharSequence): PhoneAccount.Builder [static]  [API 23+]
* describeContents(): int  [API 23+]
* equals(Object): boolean  [API 23+]
* getAccountHandle(): PhoneAccountHandle  [API 23+]
* getAddress(): Uri  [API 23+]
* getCapabilities(): int  [API 23+]
* getExtras(): Bundle  [API 24+]
* getHighlightColor(): int  [API 23+]
* getIcon(): Icon  [API 23+]
* getLabel(): CharSequence  [API 23+]
* getShortDescription(): CharSequence  [API 23+]
* getSimultaneousCallingRestriction(): Set  [API 35+]
* getSubscriptionAddress(): Uri  [API 23+]
* getSupportedUriSchemes(): List  [API 23+]
+ hasCapabilities(int): boolean  [API 23+]
+ hasSimultaneousCallingRestriction(): boolean  [API 35+]
* hashCode(): int  [API 23+]
* isEnabled(): boolean  [API 23+]
+ supportsUriScheme(String): boolean  [API 23+]
+ toBuilder(): PhoneAccount.Builder  [API 23+]
* toString(): String  [API 23+]
* writeToParcel(Parcel, int): void  [API 23+]
```

## PhoneAccount.Builder — declaration; API 23+ (1 +, 13 *, 0 ~, 0 -)

```
* Builder(PhoneAccount)  [API 23+]
* Builder(PhoneAccountHandle, CharSequence)  [API 23+]
* addSupportedUriScheme(String): PhoneAccount.Builder  [API 23+]
* build(): PhoneAccount  [API 23+]
+ clearSimultaneousCallingRestriction(): PhoneAccount.Builder  [API 35+]
* setAddress(Uri): PhoneAccount.Builder  [API 23+]
* setCapabilities(int): PhoneAccount.Builder  [API 23+]
* setExtras(Bundle): PhoneAccount.Builder  [API 24+]
* setHighlightColor(int): PhoneAccount.Builder  [API 23+]
* setIcon(Icon): PhoneAccount.Builder  [API 23+]
* setShortDescription(CharSequence): PhoneAccount.Builder  [API 23+]
* setSimultaneousCallingRestriction(Set): PhoneAccount.Builder  [API 35+]
* setSubscriptionAddress(Uri): PhoneAccount.Builder  [API 23+]
* setSupportedUriSchemes(List): PhoneAccount.Builder  [API 23+]
```

## PhoneAccountHandle — declaration; API 23+ (0 +, 10 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 23+]
* PhoneAccountHandle(ComponentName, String)  [API 23+]
* PhoneAccountHandle(ComponentName, String, UserHandle)  [API 23+]
* describeContents(): int  [API 23+]
* equals(Object): boolean  [API 23+]
* getComponentName(): ComponentName  [API 23+]
* getId(): String  [API 23+]
* getUserHandle(): UserHandle  [API 23+]
* hashCode(): int  [API 23+]
* toString(): String  [API 23+]
* writeToParcel(Parcel, int): void  [API 23+]
```

## PhoneAccountSuggestion — declaration; API 29+ (7 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 29+]
- PhoneAccountSuggestion(PhoneAccountHandle, int, boolean)  [API 29+]
+ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getPhoneAccountHandle(): PhoneAccountHandle  [API 29+]
+ getReason(): int  [API 29+]
+ hashCode(): int  [API 29+]
+ shouldAutoSelect(): boolean  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## QueryLocationException — declaration; API 34+ (4 +, 0 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 34+]
+ QueryLocationException(String)  [API 34+]
- QueryLocationException(String, int)  [API 34+]
- QueryLocationException(String, int, Throwable)  [API 34+]
+ describeContents(): int  [API 34+]
+ getCode(): int  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## RemoteConference — declaration; API 23+ (19 +, 0 *, 0 ~, 0 -)

```
+ disconnect(): void  [API 23+]
+ getConferenceableConnections(): List  [API 23+]
+ getConnectionCapabilities(): int  [API 23+]
+ getConnectionProperties(): int  [API 24+]
+ getConnections(): List  [API 23+]
+ getDisconnectCause(): DisconnectCause  [API 23+]
+ getExtras(): Bundle  [API 23+]
+ getState(): int  [API 23+]
+ hold(): void  [API 23+]
+ merge(): void  [API 23+]
+ playDtmfTone(char): void  [API 23+]
+ registerCallback(RemoteConference.Callback): void  [API 23+]
+ registerCallback(RemoteConference.Callback, Handler): void  [API 23+]
+ separate(RemoteConnection): void  [API 23+]
+ setCallAudioState(CallAudioState): void  [API 23+]
+ stopDtmfTone(): void  [API 23+]
+ swap(): void  [API 23+]
+ unhold(): void  [API 23+]
+ unregisterCallback(RemoteConference.Callback): void  [API 23+]
```

## RemoteConference.Callback — declaration; API 23+ (10 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 23+]
+ onConferenceableConnectionsChanged(RemoteConference, List): void  [API 23+]
+ onConnectionAdded(RemoteConference, RemoteConnection): void  [API 23+]
+ onConnectionCapabilitiesChanged(RemoteConference, int): void  [API 23+]
+ onConnectionPropertiesChanged(RemoteConference, int): void  [API 25+]
+ onConnectionRemoved(RemoteConference, RemoteConnection): void  [API 23+]
+ onDestroyed(RemoteConference): void  [API 23+]
+ onDisconnected(RemoteConference, DisconnectCause): void  [API 23+]
+ onExtrasChanged(RemoteConference, Bundle): void  [API 23+]
+ onStateChanged(RemoteConference, int, int): void  [API 23+]
```

## RemoteConnection — declaration; API 23+ (31 +, 0 *, 0 ~, 0 -)

```
+ abort(): void  [API 23+]
+ addConferenceParticipants(List): void  [API 31+]
+ answer(): void  [API 23+]
+ disconnect(): void  [API 23+]
+ getAddress(): Uri  [API 23+]
+ getAddressPresentation(): int  [API 23+]
+ getCallerDisplayName(): CharSequence  [API 23+]
+ getCallerDisplayNamePresentation(): int  [API 23+]
+ getConference(): RemoteConference  [API 23+]
+ getConferenceableConnections(): List  [API 23+]
+ getConnectionCapabilities(): int  [API 23+]
+ getConnectionProperties(): int  [API 25+]
+ getDisconnectCause(): DisconnectCause  [API 23+]
+ getExtras(): Bundle  [API 23+]
+ getState(): int  [API 23+]
+ getStatusHints(): StatusHints  [API 23+]
+ getVideoProvider(): RemoteConnection.VideoProvider  [API 23+]
+ getVideoState(): int  [API 23+]
+ hold(): void  [API 23+]
+ isRingbackRequested(): boolean  [API 23+]
+ isVoipAudioMode(): boolean  [API 23+]
+ playDtmfTone(char): void  [API 23+]
+ postDialContinue(boolean): void  [API 23+]
+ pullExternalCall(): void  [API 25+]
+ registerCallback(RemoteConnection.Callback): void  [API 23+]
+ registerCallback(RemoteConnection.Callback, Handler): void  [API 23+]
+ reject(): void  [API 23+]
+ setCallAudioState(CallAudioState): void  [API 23+]
+ stopDtmfTone(): void  [API 23+]
+ unhold(): void  [API 23+]
+ unregisterCallback(RemoteConnection.Callback): void  [API 23+]
```

## RemoteConnection.Callback — declaration; API 23+ (19 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 23+]
+ onAddressChanged(RemoteConnection, Uri, int): void  [API 23+]
+ onCallerDisplayNameChanged(RemoteConnection, String, int): void  [API 23+]
+ onConferenceChanged(RemoteConnection, RemoteConference): void  [API 23+]
+ onConferenceableConnectionsChanged(RemoteConnection, List): void  [API 23+]
+ onConnectionCapabilitiesChanged(RemoteConnection, int): void  [API 23+]
+ onConnectionEvent(RemoteConnection, String, Bundle): void  [API 25+]
+ onConnectionPropertiesChanged(RemoteConnection, int): void  [API 25+]
+ onDestroyed(RemoteConnection): void  [API 23+]
+ onDisconnected(RemoteConnection, DisconnectCause): void  [API 23+]
+ onExtrasChanged(RemoteConnection, Bundle): void  [API 23+]
+ onPostDialChar(RemoteConnection, char): void  [API 23+]
+ onPostDialWait(RemoteConnection, String): void  [API 23+]
+ onRingbackRequested(RemoteConnection, boolean): void  [API 23+]
+ onStateChanged(RemoteConnection, int): void  [API 23+]
+ onStatusHintsChanged(RemoteConnection, StatusHints): void  [API 23+]
+ onVideoProviderChanged(RemoteConnection, RemoteConnection.VideoProvider): void  [API 23+]
+ onVideoStateChanged(RemoteConnection, int): void  [API 23+]
+ onVoipAudioChanged(RemoteConnection, boolean): void  [API 23+]
```

## RemoteConnection.VideoProvider — declaration; API 23+ (12 +, 0 *, 0 ~, 0 -)

```
+ registerCallback(RemoteConnection.VideoProvider.Callback): void  [API 23+]
+ requestCallDataUsage(): void  [API 23+]
+ requestCameraCapabilities(): void  [API 23+]
+ sendSessionModifyRequest(VideoProfile, VideoProfile): void  [API 23+]
+ sendSessionModifyResponse(VideoProfile): void  [API 23+]
+ setCamera(String): void  [API 23+]
+ setDeviceOrientation(int): void  [API 23+]
+ setDisplaySurface(Surface): void  [API 23+]
+ setPauseImage(Uri): void  [API 23+]
+ setPreviewSurface(Surface): void  [API 23+]
+ setZoom(float): void  [API 23+]
+ unregisterCallback(RemoteConnection.VideoProvider.Callback): void  [API 23+]
```

## RemoteConnection.VideoProvider.Callback — declaration; API 23+ (8 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 23+]
+ onCallDataUsageChanged(RemoteConnection.VideoProvider, long): void  [API 23+]
+ onCallSessionEvent(RemoteConnection.VideoProvider, int): void  [API 23+]
+ onCameraCapabilitiesChanged(RemoteConnection.VideoProvider, VideoProfile.CameraCapabilities): void  [API 23+]
+ onPeerDimensionsChanged(RemoteConnection.VideoProvider, int, int): void  [API 23+]
+ onSessionModifyRequestReceived(RemoteConnection.VideoProvider, VideoProfile): void  [API 23+]
+ onSessionModifyResponseReceived(RemoteConnection.VideoProvider, int, VideoProfile, VideoProfile): void  [API 23+]
+ onVideoQualityChanged(RemoteConnection.VideoProvider, int): void  [API 23+]
```

## StatusHints — declaration; API 23+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 23+]
* StatusHints(CharSequence, Icon, Bundle)  [API 23+]
* describeContents(): int  [API 23+]
* equals(Object): boolean  [API 23+]
* getExtras(): Bundle  [API 23+]
* getIcon(): Icon  [API 23+]
* getLabel(): CharSequence  [API 23+]
* hashCode(): int  [API 23+]
* writeToParcel(Parcel, int): void  [API 23+]
```

## TelecomManager — framework; API 21+ (38 +, 0 *, 0 ~, 0 -)

```
+ acceptHandover(Uri, int, PhoneAccountHandle): void  [API 28+]
+ acceptRingingCall(): void  [API 26+, deprecated in 29]
+ acceptRingingCall(int): void  [API 26+, deprecated in 29]
+ addCall(CallAttributes, Executor, OutcomeReceiver, CallControlCallback, CallEventCallback): void  [API 34+]
+ addNewIncomingCall(PhoneAccountHandle, Bundle): void  [API 23+]
+ addNewIncomingConference(PhoneAccountHandle, Bundle): void  [API 31+]
+ cancelMissedCallsNotification(): void  [API 21+]
+ createManageBlockedNumbersIntent(): Intent  [API 24+]
+ endCall(): boolean  [API 28+, deprecated in 29]
+ getAdnUriForPhoneAccount(PhoneAccountHandle): Uri  [API 23+]
+ getCallCapablePhoneAccounts(): List  [API 23+]
+ getDefaultDialerPackage(): String  [API 23+]
+ getDefaultOutgoingPhoneAccount(String): PhoneAccountHandle  [API 23+]
+ getLine1Number(PhoneAccountHandle): String  [API 23+, deprecated in 33]
+ getOwnSelfManagedPhoneAccounts(): List  [API 33+]
+ getPhoneAccount(PhoneAccountHandle): PhoneAccount  [API 23+]
+ getRegisteredPhoneAccounts(): List  [API 35+]
+ getSelfManagedPhoneAccounts(): List  [API 26+]
+ getSimCallManager(): PhoneAccountHandle  [API 23+]
+ getSimCallManagerForSubscription(int): PhoneAccountHandle  [API 30+]
+ getSystemDialerPackage(): String  [API 29+]
+ getUserSelectedOutgoingPhoneAccount(): PhoneAccountHandle  [API 29+]
+ getVoiceMailNumber(PhoneAccountHandle): String  [API 23+]
+ handleMmi(String): boolean  [API 21+]
+ handleMmi(String, PhoneAccountHandle): boolean  [API 23+]
+ hasManageOngoingCallsPermission(): boolean  [API 31+]
+ isInCall(): boolean  [API 21+]
+ isInManagedCall(): boolean  [API 26+]
+ isIncomingCallPermitted(PhoneAccountHandle): boolean  [API 26+]
+ isOutgoingCallPermitted(PhoneAccountHandle): boolean  [API 26+]
+ isTtySupported(): boolean  [API 28+]
+ isVoiceMailNumber(PhoneAccountHandle, String): boolean  [API 23+]
+ placeCall(Uri, Bundle): void  [API 23+]
+ registerPhoneAccount(PhoneAccount): void  [API 23+]
+ showInCallScreen(boolean): void  [API 21+]
+ silenceRinger(): void  [API 23+]
+ startConference(List, Bundle): void  [API 31+]
+ unregisterPhoneAccount(PhoneAccountHandle): void  [API 23+]
```

## VideoProfile — declaration; API 23+ (7 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 23+]
* VideoProfile(int)  [API 23+]
* VideoProfile(int, int)  [API 23+]
* describeContents(): int  [API 23+]
* getQuality(): int  [API 23+]
* getVideoState(): int  [API 23+]
+ isAudioOnly(int): boolean [static]  [API 23+]
+ isBidirectional(int): boolean [static]  [API 23+]
+ isPaused(int): boolean [static]  [API 23+]
+ isReceptionEnabled(int): boolean [static]  [API 23+]
+ isTransmissionEnabled(int): boolean [static]  [API 23+]
+ isVideo(int): boolean [static]  [API 23+]
* toString(): String  [API 23+]
+ videoStateToString(int): String [static]  [API 23+]
* writeToParcel(Parcel, int): void  [API 23+]
```

## VideoProfile.CameraCapabilities — declaration; API 23+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 23+]
* CameraCapabilities(int, int)  [API 23+]
* CameraCapabilities(int, int, boolean, float)  [API 29+]
* describeContents(): int  [API 23+]
* getHeight(): int  [API 23+]
* getMaxZoom(): float  [API 29+]
* getWidth(): int  [API 23+]
* isZoomSupported(): boolean  [API 29+]
* writeToParcel(Parcel, int): void  [API 23+]
```


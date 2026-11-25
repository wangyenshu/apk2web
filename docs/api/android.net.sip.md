# android.net.sip

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## SipAudioCall — declaration; API 9+, deprecated in 31 (20 +, 0 *, 0 ~, 1 -)

```
- SipAudioCall(Context, SipProfile)  [API 9+]
+ answerCall(int): void  [API 9+]
+ attachCall(SipSession, String): void  [API 9+]
+ close(): void  [API 9+]
+ continueCall(int): void  [API 9+]
+ endCall(): void  [API 9+]
+ getLocalProfile(): SipProfile  [API 9+]
+ getPeerProfile(): SipProfile  [API 9+]
+ getState(): int  [API 9+]
+ holdCall(int): void  [API 9+]
+ isInCall(): boolean  [API 9+]
+ isMuted(): boolean  [API 9+]
+ isOnHold(): boolean  [API 9+]
+ makeCall(SipProfile, SipSession, int): void  [API 9+]
+ sendDtmf(int): void  [API 9+]
+ sendDtmf(int, Message): void  [API 9+]
+ setListener(SipAudioCall.Listener): void  [API 9+]
+ setListener(SipAudioCall.Listener, boolean): void  [API 9+]
+ setSpeakerMode(boolean): void  [API 9+]
+ startAudio(): void  [API 9+]
+ toggleMute(): void  [API 9+]
```

## SipAudioCall.Listener — declaration; API 9+, deprecated in 31 (11 +, 0 *, 0 ~, 0 -)

```
+ Listener()  [API 9+]
+ onCallBusy(SipAudioCall): void  [API 9+]
+ onCallEnded(SipAudioCall): void  [API 9+]
+ onCallEstablished(SipAudioCall): void  [API 9+]
+ onCallHeld(SipAudioCall): void  [API 9+]
+ onCalling(SipAudioCall): void  [API 9+]
+ onChanged(SipAudioCall): void  [API 9+]
+ onError(SipAudioCall, int, String): void  [API 9+]
+ onReadyToCall(SipAudioCall): void  [API 9+]
+ onRinging(SipAudioCall, SipProfile): void  [API 9+]
+ onRingingBack(SipAudioCall): void  [API 9+]
```

## SipErrorCode — declaration; API 9+, deprecated in 31 (1 +, 0 *, 0 ~, 0 -)

```
+ toString(int): String [static]  [API 9+]
```

## SipException — declaration; API 9+, deprecated in 31 (3 +, 0 *, 0 ~, 0 -)

```
+ SipException()  [API 9+]
+ SipException(String)  [API 9+]
+ SipException(String, Throwable)  [API 9+]
```

## SipManager — declaration; API 9+, deprecated in 31 (20 +, 0 *, 0 ~, 0 -)

```
+ close(String): void  [API 9+]
+ createSipSession(SipProfile, SipSession.Listener): SipSession  [API 9+]
+ getCallId(Intent): String [static]  [API 9+]
+ getOfferSessionDescription(Intent): String [static]  [API 9+]
+ getSessionFor(Intent): SipSession  [API 9+]
+ isApiSupported(Context): boolean [static]  [API 9+]
+ isIncomingCallIntent(Intent): boolean [static]  [API 9+]
+ isOpened(String): boolean  [API 9+]
+ isRegistered(String): boolean  [API 9+]
+ isSipWifiOnly(Context): boolean [static]  [API 9+]
+ isVoipSupported(Context): boolean [static]  [API 9+]
+ makeAudioCall(SipProfile, SipProfile, SipAudioCall.Listener, int): SipAudioCall  [API 9+]
+ makeAudioCall(String, String, SipAudioCall.Listener, int): SipAudioCall  [API 9+]
+ newInstance(Context): SipManager [static]  [API 9+]
+ open(SipProfile): void  [API 9+]
+ open(SipProfile, PendingIntent, SipRegistrationListener): void  [API 9+]
+ register(SipProfile, int, SipRegistrationListener): void  [API 9+]
+ setRegistrationListener(String, SipRegistrationListener): void  [API 9+]
+ takeAudioCall(Intent, SipAudioCall.Listener): SipAudioCall  [API 9+]
+ unregister(SipProfile, SipRegistrationListener): void  [API 9+]
```

## SipProfile — declaration; API 9+, deprecated in 31 (1 +, 14 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 9+]
* describeContents(): int  [API 9+]
* getAuthUserName(): String  [API 12+]
* getAutoRegistration(): boolean  [API 9+]
* getDisplayName(): String  [API 9+]
* getPassword(): String  [API 9+]
* getPort(): int  [API 9+]
* getProfileName(): String  [API 9+]
* getProtocol(): String  [API 9+]
* getProxyAddress(): String  [API 9+]
* getSendKeepAlive(): boolean  [API 9+]
* getSipDomain(): String  [API 9+]
* getUriString(): String  [API 9+]
* getUserName(): String  [API 9+]
+ setCallingUid(int): void  [API 30+]
* writeToParcel(Parcel, int): void  [API 9+]
```

## SipProfile.Builder — declaration; API 9+, deprecated in 31 (1 +, 11 *, 0 ~, 1 -)

```
* Builder(SipProfile)  [API 9+]
* Builder(String)  [API 9+]
- Builder(String, String)  [API 9+]
* build(): SipProfile  [API 9+]
* setAuthUserName(String): SipProfile.Builder  [API 12+]
* setAutoRegistration(boolean): SipProfile.Builder  [API 9+]
* setDisplayName(String): SipProfile.Builder  [API 9+]
+ setOutboundProxy(String): SipProfile.Builder  [API 9+]
* setPassword(String): SipProfile.Builder  [API 9+]
* setPort(int): SipProfile.Builder  [API 9+]
* setProfileName(String): SipProfile.Builder  [API 9+]
* setProtocol(String): SipProfile.Builder  [API 9+]
* setSendKeepAlive(boolean): SipProfile.Builder  [API 9+]
```

## SipRegistrationListener — declaration; API 9+, deprecated in 31 (3 +, 0 *, 0 ~, 0 -)

```
+ onRegistering(String): void  [API 9+]
+ onRegistrationDone(String, long): void  [API 9+]
+ onRegistrationFailed(String, int, String): void  [API 9+]
```

## SipSession — declaration; API 9+, deprecated in 31 (13 +, 0 *, 0 ~, 0 -)

```
+ answerCall(String, int): void  [API 9+]
+ changeCall(String, int): void  [API 9+]
+ endCall(): void  [API 9+]
+ getCallId(): String  [API 9+]
+ getLocalIp(): String  [API 9+]
+ getLocalProfile(): SipProfile  [API 9+]
+ getPeerProfile(): SipProfile  [API 9+]
+ getState(): int  [API 9+]
+ isInCall(): boolean  [API 9+]
+ makeCall(SipProfile, String, int): void  [API 9+]
+ register(int): void  [API 9+]
+ setListener(SipSession.Listener): void  [API 9+]
+ unregister(): void  [API 9+]
```

## SipSession.Listener — declaration; API 9+, deprecated in 31 (13 +, 0 *, 0 ~, 0 -)

```
+ Listener()  [API 9+]
+ onCallBusy(SipSession): void  [API 9+]
+ onCallChangeFailed(SipSession, int, String): void  [API 9+]
+ onCallEnded(SipSession): void  [API 9+]
+ onCallEstablished(SipSession, String): void  [API 9+]
+ onCalling(SipSession): void  [API 9+]
+ onError(SipSession, int, String): void  [API 9+]
+ onRegistering(SipSession): void  [API 9+]
+ onRegistrationDone(SipSession, int): void  [API 9+]
+ onRegistrationFailed(SipSession, int, String): void  [API 9+]
+ onRegistrationTimeout(SipSession): void  [API 9+]
+ onRinging(SipSession, SipProfile, String): void  [API 9+]
+ onRingingBack(SipSession): void  [API 9+]
```

## SipSession.State — declaration; API 9+, deprecated in 31 (1 +, 0 *, 0 ~, 0 -)

```
+ toString(int): String [static]  [API 9+]
```


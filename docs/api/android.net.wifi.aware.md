# android.net.wifi.aware

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AttachCallback — declaration; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ AttachCallback()  [API 26+]
+ onAttachFailed(): void  [API 26+]
+ onAttached(WifiAwareSession): void  [API 26+]
+ onAwareSessionTerminated(): void  [API 33+]
```

## AwarePairingConfig — declaration; API 34+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getBootstrappingMethods(): int  [API 34+]
* hashCode(): int  [API 34+]
* isPairingCacheEnabled(): boolean  [API 34+]
* isPairingSetupEnabled(): boolean  [API 34+]
* isPairingVerificationEnabled(): boolean  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## AwarePairingConfig.Builder — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): AwarePairingConfig  [API 34+]
* setBootstrappingMethods(int): AwarePairingConfig.Builder  [API 34+]
* setPairingCacheEnabled(boolean): AwarePairingConfig.Builder  [API 34+]
* setPairingSetupEnabled(boolean): AwarePairingConfig.Builder  [API 34+]
* setPairingVerificationEnabled(boolean): AwarePairingConfig.Builder  [API 34+]
```

## AwareResources — declaration; API 31+ (0 +, 6 *, 0 ~, 0 -)

```
* AwareResources(int, int, int)  [API 31+]
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* getAvailableDataPathsCount(): int  [API 31+]
* getAvailablePublishSessionsCount(): int  [API 31+]
* getAvailableSubscribeSessionsCount(): int  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## Characteristics — declaration; API 26+ (14 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 26+]
+ describeContents(): int  [API 26+]
+ getMaxMatchFilterLength(): int  [API 26+]
+ getMaxServiceNameLength(): int  [API 26+]
+ getMaxServiceSpecificInfoLength(): int  [API 26+]
+ getNumberOfSupportedDataInterfaces(): int  [API 33+]
+ getNumberOfSupportedDataPaths(): int  [API 33+]
+ getNumberOfSupportedPublishSessions(): int  [API 33+]
+ getNumberOfSupportedSubscribeSessions(): int  [API 33+]
+ getSupportedCipherSuites(): int  [API 30+]
+ getSupportedPairingCipherSuites(): int  [API 34+]
+ isAwarePairingSupported(): boolean  [API 34+]
+ isInstantCommunicationModeSupported(): boolean  [API 31+]
+ isSuspensionSupported(): boolean  [API 34+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## DiscoverySession — declaration; API 26+ (9 +, 0 *, 0 ~, 0 -)

```
+ acceptPairingRequest(int, PeerHandle, String, int, String): void  [API 34+]
+ close(): void  [API 26+]
+ createNetworkSpecifierOpen(PeerHandle): NetworkSpecifier  [API 26+, deprecated in 29]
+ createNetworkSpecifierPassphrase(PeerHandle, String): NetworkSpecifier  [API 26+, deprecated in 29]
+ finalize(): void  [API 26+]
+ initiateBootstrappingRequest(PeerHandle, int): void  [API 34+]
+ initiatePairingRequest(PeerHandle, String, int, String): void  [API 34+]
+ rejectPairingRequest(int, PeerHandle): void  [API 34+]
+ sendMessage(PeerHandle, int, byte[]): void  [API 26+]
```

## DiscoverySessionCallback — declaration; API 26+ (21 +, 0 *, 0 ~, 0 -)

```
+ DiscoverySessionCallback()  [API 26+]
+ onBootstrappingFailed(PeerHandle): void  [API 34+]
+ onBootstrappingSucceeded(PeerHandle, int): void  [API 34+]
+ onMessageReceived(PeerHandle, byte[]): void  [API 26+]
+ onMessageSendFailed(int): void  [API 26+]
+ onMessageSendSucceeded(int): void  [API 26+]
+ onPairingSetupFailed(PeerHandle): void  [API 34+]
+ onPairingSetupRequestReceived(PeerHandle, int): void  [API 34+]
+ onPairingSetupSucceeded(PeerHandle, String): void  [API 34+]
+ onPairingVerificationFailed(PeerHandle): void  [API 34+]
+ onPairingVerificationSucceed(PeerHandle, String): void  [API 34+]
+ onPublishStarted(PublishDiscoverySession): void  [API 26+]
+ onServiceDiscovered(PeerHandle, byte[], List): void  [API 26+]
+ onServiceDiscovered(ServiceDiscoveryInfo): void  [API 33+]
+ onServiceDiscoveredWithinRange(PeerHandle, byte[], List, int): void  [API 28+]
+ onServiceDiscoveredWithinRange(ServiceDiscoveryInfo, int): void  [API 33+]
+ onServiceLost(PeerHandle, int): void  [API 31+]
+ onSessionConfigFailed(): void  [API 26+]
+ onSessionConfigUpdated(): void  [API 26+]
+ onSessionTerminated(): void  [API 26+]
+ onSubscribeStarted(SubscribeDiscoverySession): void  [API 26+]
```

## IdentityChangedListener — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ IdentityChangedListener()  [API 26+]
+ onClusterIdChanged(int, MacAddress): void  [API 34+]
+ onIdentityChanged(byte[]): void  [API 26+]
```

## ParcelablePeerHandle — declaration; API 29+ (2 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 29+]
- ParcelablePeerHandle(PeerHandle)  [API 29+]
+ describeContents(): int  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## PeerHandle — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 26+]
+ hashCode(): int  [API 26+]
```

## PublishConfig — declaration; API 26+ (8 +, 1 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 26+]
+ describeContents(): int  [API 26+]
+ equals(Object): boolean  [API 26+]
+ getInstantCommunicationBand(): int  [API 33+]
* getPairingConfig(): AwarePairingConfig  [API 34+]
+ getSecurityConfig(): WifiAwareDataPathSecurityConfig  [API 33+]
+ hashCode(): int  [API 26+]
+ isInstantCommunicationModeEnabled(): boolean  [API 33+]
+ toString(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## PublishConfig.Builder — declaration; API 26+ (9 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 26+]
* build(): PublishConfig  [API 26+]
+ setDataPathSecurityConfig(WifiAwareDataPathSecurityConfig): PublishConfig.Builder  [API 33+]
+ setInstantCommunicationModeEnabled(boolean, int): PublishConfig.Builder  [API 33+]
+ setMatchFilter(List): PublishConfig.Builder  [API 26+]
* setPairingConfig(AwarePairingConfig): PublishConfig.Builder  [API 34+]
+ setPublishType(int): PublishConfig.Builder  [API 26+]
+ setRangingEnabled(boolean): PublishConfig.Builder  [API 28+]
+ setServiceName(String): PublishConfig.Builder  [API 26+]
+ setServiceSpecificInfo(byte[]): PublishConfig.Builder  [API 26+]
+ setTerminateNotificationEnabled(boolean): PublishConfig.Builder  [API 26+]
+ setTtlSec(int): PublishConfig.Builder  [API 26+]
```

## PublishDiscoverySession — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ updatePublish(PublishConfig): void  [API 26+]
```

## ServiceDiscoveryInfo — declaration; API 33+ (7 +, 0 *, 0 ~, 0 -)

```
+ getMatchFilters(): List  [API 33+]
+ getPairedAlias(): String  [API 34+]
+ getPairingConfig(): AwarePairingConfig  [API 34+]
+ getPeerCipherSuite(): int  [API 33+]
+ getPeerHandle(): PeerHandle  [API 33+]
+ getScid(): byte[]  [API 33+]
+ getServiceSpecificInfo(): byte[]  [API 33+]
```

## SubscribeConfig — declaration; API 26+ (7 +, 1 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 26+]
+ describeContents(): int  [API 26+]
+ equals(Object): boolean  [API 26+]
+ getInstantCommunicationBand(): int  [API 33+]
* getPairingConfig(): AwarePairingConfig  [API 34+]
+ hashCode(): int  [API 26+]
+ isInstantCommunicationModeEnabled(): boolean  [API 33+]
+ toString(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## SubscribeConfig.Builder — declaration; API 26+ (9 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 26+]
* build(): SubscribeConfig  [API 26+]
+ setInstantCommunicationModeEnabled(boolean, int): SubscribeConfig.Builder  [API 33+]
+ setMatchFilter(List): SubscribeConfig.Builder  [API 26+]
+ setMaxDistanceMm(int): SubscribeConfig.Builder  [API 28+]
+ setMinDistanceMm(int): SubscribeConfig.Builder  [API 28+]
* setPairingConfig(AwarePairingConfig): SubscribeConfig.Builder  [API 34+]
+ setServiceName(String): SubscribeConfig.Builder  [API 26+]
+ setServiceSpecificInfo(byte[]): SubscribeConfig.Builder  [API 26+]
+ setSubscribeType(int): SubscribeConfig.Builder  [API 26+]
+ setTerminateNotificationEnabled(boolean): SubscribeConfig.Builder  [API 26+]
+ setTtlSec(int): SubscribeConfig.Builder  [API 26+]
```

## SubscribeDiscoverySession — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ updateSubscribe(SubscribeConfig): void  [API 26+]
```

## WifiAwareChannelInfo — declaration; API 33+ (8 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
+ equals(Object): boolean  [API 33+]
+ getChannelBandwidth(): int  [API 33+]
+ getChannelFrequencyMhz(): int  [API 33+]
+ getSpatialStreamCount(): int  [API 33+]
+ hashCode(): int  [API 33+]
+ toString(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## WifiAwareDataPathSecurityConfig — declaration; API 33+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getCipherSuite(): int  [API 33+]
* getPmk(): byte[]  [API 33+]
* getPmkId(): byte[]  [API 33+]
* getPskPassphrase(): String  [API 33+]
* hashCode(): int  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## WifiAwareDataPathSecurityConfig.Builder — declaration; API 33+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(int)  [API 33+]
* build(): WifiAwareDataPathSecurityConfig  [API 33+]
* setPmk(byte[]): WifiAwareDataPathSecurityConfig.Builder  [API 33+]
* setPmkId(byte[]): WifiAwareDataPathSecurityConfig.Builder  [API 33+]
* setPskPassphrase(String): WifiAwareDataPathSecurityConfig.Builder  [API 33+]
```

## WifiAwareManager — declaration; API 26+ (13 +, 0 *, 0 ~, 0 -)

```
+ attach(AttachCallback, Handler): void  [API 26+]
+ attach(AttachCallback, IdentityChangedListener, Handler): void  [API 26+]
+ getAvailableAwareResources(): AwareResources  [API 31+]
+ getCharacteristics(): Characteristics  [API 26+]
+ getPairedDevices(Executor, Consumer): void  [API 34+]
+ isAvailable(): boolean  [API 26+]
+ isDeviceAttached(): boolean  [API 31+]
+ isInstantCommunicationModeEnabled(): boolean  [API 31+]
+ isOpportunisticModeEnabled(Executor, Consumer): void  [API 34+]
+ isSetChannelOnDataPathSupported(): boolean  [API 33+]
+ removePairedDevice(String): void  [API 34+]
+ resetPairedDevices(): void  [API 34+]
+ setOpportunisticModeEnabled(boolean): void  [API 34+]
```

## WifiAwareNetworkInfo — declaration; API 29+ (9 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
+ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getChannelInfoList(): List  [API 33+]
+ getPeerIpv6Addr(): Inet6Address  [API 29+]
+ getPort(): int  [API 29+]
+ getTransportProtocol(): int  [API 29+]
+ hashCode(): int  [API 29+]
+ toString(): String  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## WifiAwareNetworkSpecifier — declaration; API 29+ (9 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
+ canBeSatisfiedBy(NetworkSpecifier): boolean  [API 31+]
+ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getChannelFrequencyMhz(): int  [API 33+]
+ getWifiAwareDataPathSecurityConfig(): WifiAwareDataPathSecurityConfig  [API 33+]
+ hashCode(): int  [API 29+]
+ isChannelRequired(): boolean  [API 33+]
+ toString(): String  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## WifiAwareNetworkSpecifier.Builder — declaration; API 29+ (7 +, 0 *, 0 ~, 2 -)

```
- Builder(DiscoverySession, PeerHandle)  [API 29+]
- Builder(PublishDiscoverySession)  [API 31+]
+ build(): WifiAwareNetworkSpecifier  [API 29+]
+ setChannelFrequencyMhz(int, boolean): WifiAwareNetworkSpecifier.Builder  [API 33+]
+ setDataPathSecurityConfig(WifiAwareDataPathSecurityConfig): WifiAwareNetworkSpecifier.Builder  [API 33+]
+ setPmk(byte[]): WifiAwareNetworkSpecifier.Builder  [API 30+]
+ setPort(int): WifiAwareNetworkSpecifier.Builder  [API 29+]
+ setPskPassphrase(String): WifiAwareNetworkSpecifier.Builder  [API 29+]
+ setTransportProtocol(int): WifiAwareNetworkSpecifier.Builder  [API 29+]
```

## WifiAwareSession — declaration; API 26+ (6 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 26+]
+ createNetworkSpecifierOpen(int, byte[]): NetworkSpecifier  [API 26+, deprecated in 31]
+ createNetworkSpecifierPassphrase(int, byte[], String): NetworkSpecifier  [API 26+, deprecated in 31]
+ finalize(): void  [API 26+]
+ publish(PublishConfig, DiscoverySessionCallback, Handler): void  [API 26+]
+ subscribe(SubscribeConfig, DiscoverySessionCallback, Handler): void  [API 26+]
```


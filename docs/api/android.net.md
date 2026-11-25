# android.net

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CaptivePortal — declaration; API 23+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 23+]
- describeContents(): int  [API 23+]
- ignoreNetwork(): void  [API 23+]
- reportCaptivePortalDismissed(): void  [API 23+]
- writeToParcel(Parcel, int): void  [API 23+]
```

## ConnectivityDiagnosticsManager — framework; API 30+ (0 +, 0 *, 0 ~, 2 -)

```
- registerConnectivityDiagnosticsCallback(NetworkRequest, Executor, ConnectivityDiagnosticsManager.ConnectivityDiagnosticsCallback): void  [API 30+]
- unregisterConnectivityDiagnosticsCallback(ConnectivityDiagnosticsManager.ConnectivityDiagnosticsCallback): void  [API 30+]
```

## ConnectivityDiagnosticsManager.ConnectivityDiagnosticsCallback — framework; API 30+ (4 +, 0 *, 0 ~, 0 -)

```
+ ConnectivityDiagnosticsCallback()  [API 30+]
+ onConnectivityReportAvailable(ConnectivityDiagnosticsManager.ConnectivityReport): void  [API 30+]
+ onDataStallSuspected(ConnectivityDiagnosticsManager.DataStallReport): void  [API 30+]
+ onNetworkConnectivityReported(Network, boolean): void  [API 30+]
```

## ConnectivityDiagnosticsManager.ConnectivityReport — framework; API 30+ (0 +, 10 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* ConnectivityReport(Network, long, LinkProperties, NetworkCapabilities, PersistableBundle)  [API 30+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getAdditionalInfo(): PersistableBundle  [API 30+]
* getLinkProperties(): LinkProperties  [API 30+]
* getNetwork(): Network  [API 30+]
* getNetworkCapabilities(): NetworkCapabilities  [API 30+]
* getReportTimestamp(): long  [API 30+]
* hashCode(): int  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## ConnectivityDiagnosticsManager.DataStallReport — framework; API 30+ (0 +, 11 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* DataStallReport(Network, long, int, LinkProperties, NetworkCapabilities, PersistableBundle)  [API 30+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getDetectionMethod(): int  [API 30+]
* getLinkProperties(): LinkProperties  [API 30+]
* getNetwork(): Network  [API 30+]
* getNetworkCapabilities(): NetworkCapabilities  [API 30+]
* getReportTimestamp(): long  [API 30+]
* getStallDetails(): PersistableBundle  [API 30+]
* hashCode(): int  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## ConnectivityManager — framework; API 1+ (3 +, 0 *, 3 ~, 40 -)

```
- addDefaultNetworkActiveListener(ConnectivityManager.OnNetworkActiveListener): void  [API 21+]
- bindProcessToNetwork(Network): boolean  [API 23+]
- createSocketKeepalive(Network, IpSecManager.UdpEncapsulationSocket, InetAddress, InetAddress, Executor, SocketKeepalive.Callback): SocketKeepalive  [API 29+]
- getActiveNetwork(): Network  [API 23+]
+ getActiveNetworkInfo(): NetworkInfo  [API 1+, deprecated in 29]
+ getAllNetworkInfo(): NetworkInfo[]  [API 1+, deprecated in 23]
- getAllNetworks(): Network[]  [API 21+, deprecated in 31]
~ getBackgroundDataSetting(): boolean  [API 3+, deprecated in 15]
- getBoundNetworkForProcess(): Network  [API 23+]
- getConnectionOwnerUid(int, InetSocketAddress, InetSocketAddress): int  [API 29+]
- getDefaultProxy(): ProxyInfo  [API 23+]
- getLinkProperties(Network): LinkProperties  [API 21+]
- getMultipathPreference(Network): int  [API 26+]
- getNetworkCapabilities(Network): NetworkCapabilities  [API 21+]
- getNetworkInfo(Network): NetworkInfo  [API 21+, deprecated in 29]
+ getNetworkInfo(int): NetworkInfo  [API 1+, deprecated in 23]
~ getNetworkPreference(): int  [API 1+, deprecated in 21]
- getNetworkWatchlistConfigHash(): byte[]  [API 28+]
- getProcessDefaultNetwork(): Network [static]  [API 21+, deprecated in 23]
- getRestrictBackgroundStatus(): int  [API 24+]
~ isActiveNetworkMetered(): boolean  [API 16+]
- isDefaultNetworkActive(): boolean  [API 21+]
- isNetworkTypeValid(int): boolean [static]  [API 1+, deprecated in 23]
- registerBestMatchingNetworkCallback(NetworkRequest, ConnectivityManager.NetworkCallback, Handler): void  [API 31+]
- registerDefaultNetworkCallback(ConnectivityManager.NetworkCallback): void  [API 24+]
- registerDefaultNetworkCallback(ConnectivityManager.NetworkCallback, Handler): void  [API 26+]
- registerNetworkCallback(NetworkRequest, ConnectivityManager.NetworkCallback): void  [API 21+]
- registerNetworkCallback(NetworkRequest, ConnectivityManager.NetworkCallback, Handler): void  [API 26+]
- registerNetworkCallback(NetworkRequest, PendingIntent): void  [API 23+]
- releaseNetworkRequest(PendingIntent): void  [API 22+]
- removeDefaultNetworkActiveListener(ConnectivityManager.OnNetworkActiveListener): void  [API 21+]
- reportBadNetwork(Network): void  [API 21+, deprecated in 23]
- reportNetworkConnectivity(Network, boolean): void  [API 23+]
- requestBandwidthUpdate(Network): boolean  [API 23+]
- requestNetwork(NetworkRequest, ConnectivityManager.NetworkCallback): void  [API 21+]
- requestNetwork(NetworkRequest, ConnectivityManager.NetworkCallback, Handler): void  [API 26+]
- requestNetwork(NetworkRequest, ConnectivityManager.NetworkCallback, Handler, int): void  [API 26+]
- requestNetwork(NetworkRequest, ConnectivityManager.NetworkCallback, int): void  [API 26+]
- requestNetwork(NetworkRequest, PendingIntent): void  [API 22+]
- requestRouteToHost(int, int): boolean  [API 1-25, removed in 26, deprecated in 21]
- setNetworkPreference(int): void  [API 1+, deprecated in 21]
- setProcessDefaultNetwork(Network): boolean [static]  [API 21+, deprecated in 23]
- startUsingNetworkFeature(int, String): int  [API 1-25, removed in 26, deprecated in 21]
- stopUsingNetworkFeature(int, String): int  [API 1-25, removed in 26, deprecated in 21]
- unregisterNetworkCallback(ConnectivityManager.NetworkCallback): void  [API 21+]
- unregisterNetworkCallback(PendingIntent): void  [API 23+]
```

## ConnectivityManager.NetworkCallback — declaration; API 21+ (8 +, 0 *, 0 ~, 1 -)

```
+ NetworkCallback()  [API 21+]
- NetworkCallback(int)  [API 31+]
+ onAvailable(Network): void  [API 21+]
+ onBlockedStatusChanged(Network, boolean): void  [API 29+]
+ onCapabilitiesChanged(Network, NetworkCapabilities): void  [API 21+]
+ onLinkPropertiesChanged(Network, LinkProperties): void  [API 21+]
+ onLosing(Network, int): void  [API 21+]
+ onLost(Network): void  [API 21+]
+ onUnavailable(): void  [API 26+]
```

## ConnectivityManager.OnNetworkActiveListener — declaration; API 21+ (1 +, 0 *, 0 ~, 0 -)

```
+ onNetworkActive(): void  [API 21+]
```

## Credentials — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ Credentials(int, int, int)  [API 1+]
+ getGid(): int  [API 1+]
+ getPid(): int  [API 1+]
+ getUid(): int  [API 1+]
```

## DhcpInfo — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 30+]
- DhcpInfo()  [API 1+]
- describeContents(): int  [API 1+]
+ dns1 [field]  [API 1+]
+ dns2 [field]  [API 1+]
+ gateway [field]  [API 1+]
+ ipAddress [field]  [API 1+]
+ leaseDuration [field]  [API 1+]
+ netmask [field]  [API 1+]
+ serverAddress [field]  [API 1+]
- toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## DnsResolver — declaration; API 29+ (0 +, 0 *, 0 ~, 5 -)

```
- getInstance(): DnsResolver [static]  [API 29+]
- query(Network, String, int, Executor, CancellationSignal, DnsResolver.Callback): void  [API 29+]
- query(Network, String, int, int, Executor, CancellationSignal, DnsResolver.Callback): void  [API 29+]
- rawQuery(Network, String, int, int, int, Executor, CancellationSignal, DnsResolver.Callback): void  [API 29+]
- rawQuery(Network, byte[], int, Executor, CancellationSignal, DnsResolver.Callback): void  [API 29+]
```

## DnsResolver.Callback — declaration; API 29+ (2 +, 0 *, 0 ~, 0 -)

```
+ onAnswer(Object, int): void  [API 29+]
+ onError(DnsResolver.DnsException): void  [API 29+]
```

## DnsResolver.DnsException — declaration; API 29+ (0 +, 0 *, 0 ~, 1 -)

```
- DnsException(int, Throwable)  [API 33+]
- code [field]  [API 29+]
```

## EthernetNetworkSpecifier — declaration; API 33+ (0 +, 7 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 33+]
* EthernetNetworkSpecifier(String)  [API 33+]
- canBeSatisfiedBy(NetworkSpecifier): boolean  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getInterfaceName(): String  [API 33+]
* hashCode(): int  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## Ikev2VpnProfile — declaration; API 30+ (0 +, 7 *, 0 ~, 12 -)

```
- equals(Object): boolean  [API 30+]
* getAllowedAlgorithms(): List  [API 30+]
- getIkeTunnelConnectionParams(): IkeTunnelConnectionParams  [API 33+]
* getMaxMtu(): int  [API 30+]
- getPassword(): String  [API 30+]
- getPresharedKey(): byte[]  [API 30+]
- getProxyInfo(): ProxyInfo  [API 30+]
- getRsaPrivateKey(): PrivateKey  [API 30+]
* getServerAddr(): String  [API 30+]
- getServerRootCaCert(): X509Certificate  [API 30+]
- getUserCert(): X509Certificate  [API 30+]
- getUserIdentity(): String  [API 30+]
- getUsername(): String  [API 30+]
- hashCode(): int  [API 30+]
* isAutomaticIpVersionSelectionEnabled(): boolean  [API 34+]
* isAutomaticNattKeepaliveTimerEnabled(): boolean  [API 34+]
* isBypassable(): boolean  [API 30+]
* isMetered(): boolean  [API 30+]
- toString(): String  [API 30+]
```

## Ikev2VpnProfile.Builder — declaration; API 30+ (0 +, 7 *, 0 ~, 8 -)

```
- Builder(IkeTunnelConnectionParams)  [API 33+]
- Builder(String, String)  [API 30+]
* build(): Ikev2VpnProfile  [API 30+]
* setAllowedAlgorithms(List): Ikev2VpnProfile.Builder  [API 30+]
- setAuthDigitalSignature(X509Certificate, PrivateKey, X509Certificate): Ikev2VpnProfile.Builder  [API 30+]
- setAuthPsk(byte[]): Ikev2VpnProfile.Builder  [API 30+]
- setAuthUsernamePassword(String, String, X509Certificate): Ikev2VpnProfile.Builder  [API 30+]
* setAutomaticIpVersionSelectionEnabled(boolean): Ikev2VpnProfile.Builder  [API 34+]
* setAutomaticNattKeepaliveTimerEnabled(boolean): Ikev2VpnProfile.Builder  [API 34+]
* setBypassable(boolean): Ikev2VpnProfile.Builder  [API 30+]
- setLocalRoutesExcluded(boolean): Ikev2VpnProfile.Builder  [API 33+]
* setMaxMtu(int): Ikev2VpnProfile.Builder  [API 30+]
* setMetered(boolean): Ikev2VpnProfile.Builder  [API 30+]
- setProxy(ProxyInfo): Ikev2VpnProfile.Builder  [API 30+]
- setRequiresInternetValidation(boolean): Ikev2VpnProfile.Builder  [API 33+]
```

## InetAddresses — declaration; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
- isNumericAddress(String): boolean [static]  [API 29+]
- parseNumericAddress(String): InetAddress [static]  [API 29+]
```

## IpConfiguration — declaration; API 33+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getHttpProxy(): ProxyInfo  [API 33+]
* getStaticIpConfiguration(): StaticIpConfiguration  [API 33+]
* hashCode(): int  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## IpConfiguration.Builder — declaration; API 33+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): IpConfiguration  [API 33+]
* setHttpProxy(ProxyInfo): IpConfiguration.Builder  [API 33+]
* setStaticIpConfiguration(StaticIpConfiguration): IpConfiguration.Builder  [API 33+]
```

## IpPrefix — declaration; API 21+ (0 +, 3 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 21+]
* IpPrefix(InetAddress, int)  [API 33+]
- contains(InetAddress): boolean  [API 23+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
* getAddress(): InetAddress  [API 21+]
* getPrefixLength(): int  [API 21+]
- getRawAddress(): byte[]  [API 21+]
- hashCode(): int  [API 21+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## IpSecAlgorithm — declaration; API 28+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 28+]
- IpSecAlgorithm(String, byte[])  [API 28+]
- IpSecAlgorithm(String, byte[], int)  [API 28+]
- describeContents(): int  [API 28+]
- getKey(): byte[]  [API 28+]
- getName(): String  [API 28+]
- getSupportedAlgorithms(): Set [static]  [API 31+]
- getTruncationLengthBits(): int  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## IpSecManager — framework; API 28+ (0 +, 0 *, 0 ~, 10 -)

```
- allocateSecurityParameterIndex(InetAddress): IpSecManager.SecurityParameterIndex  [API 28+]
- allocateSecurityParameterIndex(InetAddress, int): IpSecManager.SecurityParameterIndex  [API 28+]
- applyTransportModeTransform(DatagramSocket, int, IpSecTransform): void  [API 28+]
- applyTransportModeTransform(FileDescriptor, int, IpSecTransform): void  [API 28+]
- applyTransportModeTransform(Socket, int, IpSecTransform): void  [API 28+]
- openUdpEncapsulationSocket(): IpSecManager.UdpEncapsulationSocket  [API 28+]
- openUdpEncapsulationSocket(int): IpSecManager.UdpEncapsulationSocket  [API 28+]
- removeTransportModeTransforms(DatagramSocket): void  [API 28+]
- removeTransportModeTransforms(FileDescriptor): void  [API 28+]
- removeTransportModeTransforms(Socket): void  [API 28+]
```

## IpSecManager.ResourceUnavailableException — framework; API 28+ (0 +, 0 *, 0 ~, 0 -)

## IpSecManager.SecurityParameterIndex — framework; API 28+ (0 +, 0 *, 0 ~, 4 -)

```
- close(): void  [API 28+]
- finalize(): void  [API 28+]
- getSpi(): int  [API 28+]
- toString(): String  [API 28+]
```

## IpSecManager.SpiUnavailableException — framework; API 28+ (0 +, 0 *, 0 ~, 1 -)

```
- getSpi(): int  [API 28+]
```

## IpSecManager.UdpEncapsulationSocket — framework; API 28+ (0 +, 0 *, 0 ~, 5 -)

```
- close(): void  [API 28+]
- finalize(): void  [API 28+]
- getFileDescriptor(): FileDescriptor  [API 28+]
- getPort(): int  [API 28+]
- toString(): String  [API 28+]
```

## IpSecTransform — declaration; API 28+ (0 +, 0 *, 0 ~, 5 -)

```
- close(): void  [API 28+]
- equals(Object): boolean  [API 28+]
- finalize(): void  [API 28+]
- requestIpSecTransformState(Executor, OutcomeReceiver): void  [API 35+]
- toString(): String  [API 28+]
```

## IpSecTransform.Builder — declaration; API 28+ (0 +, 0 *, 0 ~, 6 -)

```
- Builder(Context)  [API 28+]
- buildTransportModeTransform(InetAddress, IpSecManager.SecurityParameterIndex): IpSecTransform  [API 28+]
- setAuthenticatedEncryption(IpSecAlgorithm): IpSecTransform.Builder  [API 28+]
- setAuthentication(IpSecAlgorithm): IpSecTransform.Builder  [API 28+]
- setEncryption(IpSecAlgorithm): IpSecTransform.Builder  [API 28+]
- setIpv4Encapsulation(IpSecManager.UdpEncapsulationSocket, int): IpSecTransform.Builder  [API 28+]
```

## IpSecTransformState — declaration; API 35+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* getByteCount(): long  [API 35+]
* getPacketCount(): long  [API 35+]
* getReplayBitmap(): byte[]  [API 35+]
* getRxHighestSequenceNumber(): long  [API 35+]
* getTimestampMillis(): long  [API 35+]
* getTxHighestSequenceNumber(): long  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## IpSecTransformState.Builder — declaration; API 35+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): IpSecTransformState  [API 35+]
* setByteCount(long): IpSecTransformState.Builder  [API 35+]
* setPacketCount(long): IpSecTransformState.Builder  [API 35+]
* setReplayBitmap(byte[]): IpSecTransformState.Builder  [API 35+]
* setRxHighestSequenceNumber(long): IpSecTransformState.Builder  [API 35+]
* setTimestampMillis(long): IpSecTransformState.Builder  [API 35+]
* setTxHighestSequenceNumber(long): IpSecTransformState.Builder  [API 35+]
```

## LinkAddress — declaration; API 21+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 21+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
- getAddress(): InetAddress  [API 21+]
- getFlags(): int  [API 21+]
- getPrefixLength(): int  [API 21+]
- getScope(): int  [API 21+]
- hashCode(): int  [API 21+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## LinkProperties — declaration; API 21+ (0 +, 13 *, 0 ~, 15 -)

```
- CREATOR [field]  [API 21+]
* LinkProperties()  [API 29+]
- addRoute(RouteInfo): boolean  [API 29+]
- clear(): void  [API 29+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
* getDhcpServerAddress(): Inet4Address  [API 30+]
- getDnsServers(): List  [API 21+]
* getDomains(): String  [API 21+]
* getHttpProxy(): ProxyInfo  [API 21+]
* getInterfaceName(): String  [API 21+]
- getLinkAddresses(): List  [API 21+]
* getMtu(): int  [API 29+]
* getNat64Prefix(): IpPrefix  [API 30+]
- getPrivateDnsServerName(): String  [API 28+]
- getRoutes(): List  [API 21+]
- hashCode(): int  [API 21+]
- isPrivateDnsActive(): boolean  [API 28+]
- isWakeOnLanSupported(): boolean  [API 30+]
* setDhcpServerAddress(Inet4Address): void  [API 30+]
- setDnsServers(Collection): void  [API 29+]
* setDomains(String): void  [API 29+]
* setHttpProxy(ProxyInfo): void  [API 29+]
* setInterfaceName(String): void  [API 29+]
- setLinkAddresses(Collection): void  [API 29+]
* setMtu(int): void  [API 29+]
* setNat64Prefix(IpPrefix): void  [API 30+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## LocalServerSocket — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- LocalServerSocket(FileDescriptor)  [API 1+]
- LocalServerSocket(String)  [API 1+]
- accept(): LocalSocket  [API 1+]
- close(): void  [API 1+]
- getFileDescriptor(): FileDescriptor  [API 1+]
- getLocalSocketAddress(): LocalSocketAddress  [API 1+]
```

## LocalSocket — declaration; API 1+ (0 +, 7 *, 0 ~, 21 -)

```
* LocalSocket()  [API 1+]
- LocalSocket(int)  [API 19+]
- bind(LocalSocketAddress): void  [API 1+]
- close(): void  [API 1+]
- connect(LocalSocketAddress): void  [API 1+]
- connect(LocalSocketAddress, int): void  [API 1+]
- getAncillaryFileDescriptors(): FileDescriptor[]  [API 1+]
- getFileDescriptor(): FileDescriptor  [API 1+]
- getInputStream(): InputStream  [API 1+]
- getLocalSocketAddress(): LocalSocketAddress  [API 1+]
- getOutputStream(): OutputStream  [API 1+]
- getPeerCredentials(): Credentials  [API 1+]
* getReceiveBufferSize(): int  [API 1+]
- getRemoteSocketAddress(): LocalSocketAddress  [API 1+]
* getSendBufferSize(): int  [API 1+]
* getSoTimeout(): int  [API 1+]
- isBound(): boolean  [API 1+]
- isClosed(): boolean  [API 1+]
- isConnected(): boolean  [API 1+]
- isInputShutdown(): boolean  [API 1+]
- isOutputShutdown(): boolean  [API 1+]
- setFileDescriptorsForSend(FileDescriptor[]): void  [API 1+]
* setReceiveBufferSize(int): void  [API 1+]
* setSendBufferSize(int): void  [API 1+]
* setSoTimeout(int): void  [API 1+]
- shutdownInput(): void  [API 1+]
- shutdownOutput(): void  [API 1+]
- toString(): String  [API 1+]
```

## LocalSocketAddress — declaration; API 1+ (0 +, 4 *, 0 ~, 0 -)

```
* LocalSocketAddress(String)  [API 1+]
* LocalSocketAddress(String, LocalSocketAddress.Namespace)  [API 1+]
* getName(): String  [API 1+]
* getNamespace(): LocalSocketAddress.Namespace  [API 1+]
```

## LocalSocketAddress.Namespace — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ABSTRACT [field]  [API 1+]
+ FILESYSTEM [field]  [API 1+]
+ RESERVED [field]  [API 1+]
+ valueOf(String): LocalSocketAddress.Namespace [static]  [API 1+]
+ values(): LocalSocketAddress.Namespace[] [static]  [API 1+]
```

## MacAddress — declaration; API 28+ (0 +, 0 *, 0 ~, 13 -)

```
- BROADCAST_ADDRESS [field]  [API 28+]
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- equals(Object): boolean  [API 28+]
- fromBytes(byte[]): MacAddress [static]  [API 28+]
- fromString(String): MacAddress [static]  [API 28+]
- getAddressType(): int  [API 28+]
- getLinkLocalIpv6FromEui48Mac(): Inet6Address  [API 30+]
- hashCode(): int  [API 28+]
- isLocallyAssigned(): boolean  [API 28+]
- matches(MacAddress, MacAddress): boolean  [API 30+]
- toByteArray(): byte[]  [API 28+]
- toOuiString(): String  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## MailTo — framework; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ getBody(): String  [API 1+]
+ getCc(): String  [API 1+]
+ getHeaders(): Map  [API 1+]
+ getSubject(): String  [API 1+]
+ getTo(): String  [API 1+]
+ isMailTo(String): boolean [static]  [API 1+]
+ parse(String): MailTo [static]  [API 1+]
+ toString(): String  [API 1+]
```

## Network — declaration; API 21+ (0 +, 0 *, 0 ~, 15 -)

```
- CREATOR [field]  [API 21+]
- bindSocket(DatagramSocket): void  [API 22+]
- bindSocket(FileDescriptor): void  [API 23+]
- bindSocket(Socket): void  [API 21+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
- fromNetworkHandle(long): Network [static]  [API 28+]
- getAllByName(String): InetAddress[]  [API 21+]
- getByName(String): InetAddress  [API 21+]
- getNetworkHandle(): long  [API 23+]
- getSocketFactory(): SocketFactory  [API 21+]
- hashCode(): int  [API 21+]
- openConnection(URL): URLConnection  [API 21+]
- openConnection(URL, Proxy): URLConnection  [API 23+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## NetworkCapabilities — declaration; API 21+ (0 +, 0 *, 0 ~, 19 -)

```
- CREATOR [field]  [API 21+]
- NetworkCapabilities()  [API 30+]
- NetworkCapabilities(NetworkCapabilities)  [API 21+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
- getCapabilities(): int[]  [API 31+]
- getEnterpriseIds(): int[]  [API 33+]
- getLinkDownstreamBandwidthKbps(): int  [API 21+]
- getLinkUpstreamBandwidthKbps(): int  [API 21+]
- getNetworkSpecifier(): NetworkSpecifier  [API 30+]
- getOwnerUid(): int  [API 30+]
- getSignalStrength(): int  [API 29+]
- getSubscriptionIds(): Set  [API 35+]
- getTransportInfo(): TransportInfo  [API 29+]
- hasCapability(int): boolean  [API 21+]
- hasEnterpriseId(int): boolean  [API 33+]
- hasTransport(int): boolean  [API 21+]
- hashCode(): int  [API 21+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## NetworkInfo — framework; API 1+, deprecated in 29 (3 +, 0 *, 13 ~, 3 -)

```
- CREATOR [field]  [API 24+]
- NetworkInfo(int)  [API 1-2, removed in 3]
- NetworkInfo(int, int, String, String)  [API 30+, deprecated in 30]
~ describeContents(): int  [API 1+]
+ getDetailedState(): NetworkInfo.DetailedState  [API 1+]
~ getExtraInfo(): String  [API 1+]
~ getReason(): String  [API 1+, deprecated in 28]
+ getState(): NetworkInfo.State  [API 1+, deprecated in 28]
~ getSubtype(): int  [API 3+]
~ getSubtypeName(): String  [API 3+]
~ getType(): int  [API 1+, deprecated in 28]
~ getTypeName(): String  [API 1+, deprecated in 28]
~ isAvailable(): boolean  [API 1+, deprecated in 28]
~ isConnected(): boolean  [API 1+]
~ isConnectedOrConnecting(): boolean  [API 1+, deprecated in 28]
~ isFailover(): boolean  [API 1+, deprecated in 28]
~ isRoaming(): boolean  [API 3+, deprecated in 28]
- setDetailedState(NetworkInfo.DetailedState, String, String): void  [API 30+, deprecated in 30]
+ toString(): String  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## NetworkInfo.DetailedState — framework; API 1+, deprecated in 29 (2 +, 0 *, 0 ~, 0 -)

```
+ AUTHENTICATING [field]  [API 1+]
+ BLOCKED [field]  [API 14+]
+ CAPTIVE_PORTAL_CHECK [field]  [API 17+]
+ CONNECTED [field]  [API 1+]
+ CONNECTING [field]  [API 1+]
+ DISCONNECTED [field]  [API 1+]
+ DISCONNECTING [field]  [API 1+]
+ FAILED [field]  [API 1+]
+ IDLE [field]  [API 1+]
+ OBTAINING_IPADDR [field]  [API 1+]
+ SCANNING [field]  [API 1+]
+ SUSPENDED [field]  [API 1+]
+ VERIFYING_POOR_LINK [field]  [API 16+]
+ valueOf(String): NetworkInfo.DetailedState [static]  [API 1+, deprecated in 36]
+ values(): NetworkInfo.DetailedState[] [static]  [API 1+, deprecated in 36]
```

## NetworkInfo.State — framework; API 1+, deprecated in 29 (2 +, 0 *, 0 ~, 0 -)

```
+ CONNECTED [field]  [API 1+]
+ CONNECTING [field]  [API 1+]
+ DISCONNECTED [field]  [API 1+]
+ DISCONNECTING [field]  [API 1+]
+ SUSPENDED [field]  [API 1+]
+ UNKNOWN [field]  [API 1+]
+ valueOf(String): NetworkInfo.State [static]  [API 1+, deprecated in 36]
+ values(): NetworkInfo.State[] [static]  [API 1+, deprecated in 36]
```

## NetworkRequest — declaration; API 21+ (0 +, 9 *, 0 ~, 3 -)

```
+ CREATOR [field]  [API 21+]
- canBeSatisfiedBy(NetworkCapabilities): boolean  [API 30+]
* describeContents(): int  [API 21+]
* equals(Object): boolean  [API 21+]
* getCapabilities(): int[]  [API 31+]
* getNetworkSpecifier(): NetworkSpecifier  [API 30+]
* getSubscriptionIds(): Set  [API 35+]
* getTransportTypes(): int[]  [API 31+]
- hasCapability(int): boolean  [API 28+]
- hasTransport(int): boolean  [API 28+]
* hashCode(): int  [API 21+]
* toString(): String  [API 21+]
* writeToParcel(Parcel, int): void  [API 21+]
```

## NetworkRequest.Builder — declaration; API 21+ (0 +, 5 *, 0 ~, 7 -)

```
* Builder()  [API 21+]
* Builder(NetworkRequest)  [API 31+]
- addCapability(int): NetworkRequest.Builder  [API 21+]
- addTransportType(int): NetworkRequest.Builder  [API 21+]
* build(): NetworkRequest  [API 21+]
- clearCapabilities(): NetworkRequest.Builder  [API 30+]
- removeCapability(int): NetworkRequest.Builder  [API 21+]
- removeTransportType(int): NetworkRequest.Builder  [API 21+]
- setIncludeOtherUidNetworks(boolean): NetworkRequest.Builder  [API 31+]
* setNetworkSpecifier(NetworkSpecifier): NetworkRequest.Builder  [API 26+]
- setNetworkSpecifier(String): NetworkRequest.Builder  [API 21+, deprecated in 30]
* setSubscriptionIds(Set): NetworkRequest.Builder  [API 35+]
```

## NetworkSpecifier — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ NetworkSpecifier()  [API 29+]
```

## ParseException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ParseException(String)  [API 31+]
+ ParseException(String, Throwable)  [API 31+]
+ response [field]  [API 1+]
```

## PlatformVpnProfile — declaration; API 30+ (0 +, 0 *, 0 ~, 4 -)

```
- areLocalRoutesExcluded(): boolean  [API 33+]
- getType(): int  [API 30+]
- getTypeString(): String  [API 30+]
- isInternetValidationRequired(): boolean  [API 33+]
```

## Proxy — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- Proxy()  [API 1+]
- getDefaultHost(): String [static]  [API 1+, deprecated in 15]
- getDefaultPort(): int [static]  [API 1+, deprecated in 15]
- getHost(Context): String [static]  [API 1+, deprecated in 15]
- getPort(Context): int [static]  [API 1+, deprecated in 15]
```

## ProxyInfo — declaration; API 21+ (0 +, 0 *, 0 ~, 15 -)

```
- CREATOR [field]  [API 21+]
- ProxyInfo(ProxyInfo)  [API 30+]
- buildDirectProxy(String, int): ProxyInfo [static]  [API 21+]
- buildDirectProxy(String, int, List): ProxyInfo [static]  [API 21+]
- buildPacProxy(Uri): ProxyInfo [static]  [API 21+]
- buildPacProxy(Uri, int): ProxyInfo [static]  [API 30+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
- getExclusionList(): String[]  [API 21+]
- getHost(): String  [API 21+]
- getPacFileUrl(): Uri  [API 21+]
- getPort(): int  [API 21+]
- hashCode(): int  [API 21+]
- isValid(): boolean  [API 30+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## PskKeyManager — declaration; API 21-25, removed in 26 (0 +, 0 *, 0 ~, 7 -)

```
- PskKeyManager()  [API 21-25, removed in 26]
- chooseClientKeyIdentity(String, SSLEngine): String  [API 21-25, removed in 26]
- chooseClientKeyIdentity(String, Socket): String  [API 21-25, removed in 26]
- chooseServerKeyIdentityHint(SSLEngine): String  [API 21-25, removed in 26]
- chooseServerKeyIdentityHint(Socket): String  [API 21-25, removed in 26]
- getKey(String, String, SSLEngine): SecretKey  [API 21-25, removed in 26]
- getKey(String, String, Socket): SecretKey  [API 21-25, removed in 26]
```

## RouteInfo — declaration; API 21+ (0 +, 0 *, 0 ~, 12 -)

```
- CREATOR [field]  [API 21+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
- getDestination(): IpPrefix  [API 21+]
- getGateway(): InetAddress  [API 21+]
- getInterface(): String  [API 21+]
- getType(): int  [API 33+]
- hasGateway(): boolean  [API 29+]
- hashCode(): int  [API 21+]
- isDefaultRoute(): boolean  [API 21+]
- matches(InetAddress): boolean  [API 21+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## SSLCertificateSocketFactory — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 19 -)

```
- SSLCertificateSocketFactory(int)  [API 1+, deprecated in 15]
- createSocket(): Socket  [API 1+]
- createSocket(InetAddress, int): Socket  [API 1+]
- createSocket(InetAddress, int, InetAddress, int): Socket  [API 1+]
- createSocket(Socket, String, int, boolean): Socket  [API 1+]
- createSocket(String, int): Socket  [API 1+]
- createSocket(String, int, InetAddress, int): Socket  [API 1+]
- getDefault(int): SocketFactory [static]  [API 1+]
- getDefault(int, SSLSessionCache): SSLSocketFactory [static]  [API 8+]
- getDefaultCipherSuites(): String[]  [API 1+]
- getHttpSocketFactory(int, SSLSessionCache): SSLSocketFactory  [API 8-22, removed in 23, deprecated in 22]
- getInsecure(int, SSLSessionCache): SSLSocketFactory [static]  [API 8+]
- getNpnSelectedProtocol(Socket): byte[]  [API 16+]
- getSupportedCipherSuites(): String[]  [API 1+]
- setHostname(Socket, String): void  [API 17+]
- setKeyManagers(KeyManager[]): void  [API 14+]
- setNpnProtocols(byte[][]): void  [API 16+]
- setTrustManagers(TrustManager[]): void  [API 14+]
- setUseSessionTickets(Socket, boolean): void  [API 17+]
```

## SSLSessionCache — declaration; API 8+ (0 +, 0 *, 0 ~, 2 -)

```
- SSLSessionCache(Context)  [API 8+]
- SSLSessionCache(File)  [API 8+]
```

## SocketKeepalive — declaration; API 29+ (0 +, 0 *, 0 ~, 3 -)

```
- close(): void  [API 29+]
- start(int): void  [API 29+]
- stop(): void  [API 29+]
```

## SocketKeepalive.Callback — declaration; API 29+ (5 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 29+]
+ onDataReceived(): void  [API 29+]
+ onError(int): void  [API 29+]
+ onStarted(): void  [API 29+]
+ onStopped(): void  [API 29+]
```

## StaticIpConfiguration — declaration; API 33+ (0 +, 3 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 33+]
- describeContents(): int  [API 33+]
- equals(Object): boolean  [API 33+]
- getDnsServers(): List  [API 33+]
* getDomains(): String  [API 33+]
* getGateway(): InetAddress  [API 33+]
* getIpAddress(): LinkAddress  [API 33+]
- hashCode(): int  [API 33+]
- toString(): String  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## StaticIpConfiguration.Builder — declaration; API 33+ (0 +, 5 *, 0 ~, 1 -)

```
* Builder()  [API 33+]
* build(): StaticIpConfiguration  [API 33+]
- setDnsServers(Iterable): StaticIpConfiguration.Builder  [API 33+]
* setDomains(String): StaticIpConfiguration.Builder  [API 33+]
* setGateway(InetAddress): StaticIpConfiguration.Builder  [API 33+]
* setIpAddress(LinkAddress): StaticIpConfiguration.Builder  [API 33+]
```

## TelephonyNetworkSpecifier — declaration; API 30+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getSubscriptionId(): int  [API 30+]
* hashCode(): int  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## TelephonyNetworkSpecifier.Builder — declaration; API 30+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 30+]
* build(): TelephonyNetworkSpecifier  [API 30+]
* setSubscriptionId(int): TelephonyNetworkSpecifier.Builder  [API 30+]
```

## TetheringInterface — declaration; API 36+ (0 +, 0 *, 0 ~, 3 -)

```
- TetheringInterface(int, String)  [API 36+]
- getInterface(): String  [API 36+]
- getType(): int  [API 36+]
```

## TetheringManager — declaration; API 36+ (0 +, 0 *, 0 ~, 3 -)

```
- registerTetheringEventCallback(Executor, TetheringManager.TetheringEventCallback): void  [API 36+]
- startTethering(TetheringManager.TetheringRequest, Executor, TetheringManager.StartTetheringCallback): void  [API 36+]
- unregisterTetheringEventCallback(TetheringManager.TetheringEventCallback): void  [API 36+]
```

## TetheringManager.StartTetheringCallback — declaration; API 36+ (2 +, 0 *, 0 ~, 0 -)

```
+ onTetheringFailed(int): void  [API 36+]
+ onTetheringStarted(): void  [API 36+]
```

## TetheringManager.TetheringEventCallback — declaration; API 36+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTetheredInterfacesChanged(Set): void  [API 36+]
```

## TetheringManager.TetheringRequest — declaration; API 36+ (0 +, 0 *, 0 ~, 0 -)

## TetheringManager.TetheringRequest.Builder — declaration; API 36+ (0 +, 0 *, 0 ~, 2 -)

```
- Builder(int)  [API 36+]
- build(): TetheringManager.TetheringRequest  [API 36+]
```

## TrafficStats — declaration; API 8+ (0 +, 0 *, 0 ~, 40 -)

```
- TrafficStats()  [API 8+]
- clearThreadStatsTag(): void [static]  [API 14+]
- clearThreadStatsUid(): void [static]  [API 28+]
- getAndSetThreadStatsTag(int): int [static]  [API 26+]
- getMobileRxBytes(): long [static]  [API 8+]
- getMobileRxPackets(): long [static]  [API 8+]
- getMobileTxBytes(): long [static]  [API 8+]
- getMobileTxPackets(): long [static]  [API 8+]
- getRxBytes(String): long [static]  [API 31+]
- getRxPackets(String): long [static]  [API 30+]
- getThreadStatsTag(): int [static]  [API 14+]
- getThreadStatsUid(): int [static]  [API 28+]
- getTotalRxBytes(): long [static]  [API 8+]
- getTotalRxPackets(): long [static]  [API 8+]
- getTotalTxBytes(): long [static]  [API 8+]
- getTotalTxPackets(): long [static]  [API 8+]
- getTxBytes(String): long [static]  [API 31+]
- getTxPackets(String): long [static]  [API 30+]
- getUidRxBytes(int): long [static]  [API 8+]
- getUidRxPackets(int): long [static]  [API 12+]
- getUidTcpRxBytes(int): long [static]  [API 12+, deprecated in 18]
- getUidTcpRxSegments(int): long [static]  [API 12+, deprecated in 18]
- getUidTcpTxBytes(int): long [static]  [API 12+, deprecated in 18]
- getUidTcpTxSegments(int): long [static]  [API 12+, deprecated in 18]
- getUidTxBytes(int): long [static]  [API 8+]
- getUidTxPackets(int): long [static]  [API 12+]
- getUidUdpRxBytes(int): long [static]  [API 12+, deprecated in 18]
- getUidUdpRxPackets(int): long [static]  [API 12+, deprecated in 18]
- getUidUdpTxBytes(int): long [static]  [API 12+, deprecated in 18]
- getUidUdpTxPackets(int): long [static]  [API 12+, deprecated in 18]
- incrementOperationCount(int): void [static]  [API 14+]
- incrementOperationCount(int, int): void [static]  [API 14+]
- setThreadStatsTag(int): void [static]  [API 14+]
- setThreadStatsUid(int): void [static]  [API 28+]
- tagDatagramSocket(DatagramSocket): void [static]  [API 24+]
- tagFileDescriptor(FileDescriptor): void [static]  [API 28+]
- tagSocket(Socket): void [static]  [API 14+]
- untagDatagramSocket(DatagramSocket): void [static]  [API 24+]
- untagFileDescriptor(FileDescriptor): void [static]  [API 28+]
- untagSocket(Socket): void [static]  [API 14+]
```

## TransportInfo — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

## Uri — framework; API 1+ (39 +, 0 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ EMPTY [field]  [API 1+]
+ buildUpon(): Uri.Builder  [API 1+]
+ compareTo(Uri): int  [API 1+]
+ decode(String): String [static]  [API 1+]
+ encode(String): String [static]  [API 1+]
+ encode(String, String): String [static]  [API 1+]
+ equals(Object): boolean  [API 1+]
+ fromFile(File): Uri [static]  [API 1+]
+ fromParts(String, String, String): Uri [static]  [API 1+]
+ getAuthority(): String  [API 1+]
+ getBooleanQueryParameter(String, boolean): boolean  [API 11+]
+ getEncodedAuthority(): String  [API 1+]
+ getEncodedFragment(): String  [API 1+]
+ getEncodedPath(): String  [API 1+]
+ getEncodedQuery(): String  [API 1+]
+ getEncodedSchemeSpecificPart(): String  [API 1+]
+ getEncodedUserInfo(): String  [API 1+]
+ getFragment(): String  [API 1+]
+ getHost(): String  [API 1+]
+ getLastPathSegment(): String  [API 1+]
+ getPath(): String  [API 1+]
+ getPathSegments(): List  [API 1+]
+ getPort(): int  [API 1+]
+ getQuery(): String  [API 1+]
+ getQueryParameter(String): String  [API 1+]
+ getQueryParameterNames(): Set  [API 11+]
+ getQueryParameters(String): List  [API 1+]
+ getScheme(): String  [API 1+]
+ getSchemeSpecificPart(): String  [API 1+]
+ getUserInfo(): String  [API 1+]
+ hashCode(): int  [API 1+]
+ isAbsolute(): boolean  [API 1+]
+ isHierarchical(): boolean  [API 1+]
+ isOpaque(): boolean  [API 1+]
+ isRelative(): boolean  [API 1+]
+ normalizeScheme(): Uri  [API 16+]
+ parse(String): Uri [static]  [API 1+]
+ toString(): String  [API 1+]
+ withAppendedPath(Uri, String): Uri [static]  [API 1+]
+ writeToParcel(Parcel, Uri): void [static]  [API 1+]
```

## Uri.Builder — framework; API 1+ (18 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 1+]
+ appendEncodedPath(String): Uri.Builder  [API 1+]
+ appendPath(String): Uri.Builder  [API 1+]
+ appendQueryParameter(String, String): Uri.Builder  [API 1+]
+ authority(String): Uri.Builder  [API 1+]
+ build(): Uri  [API 1+]
+ clearQuery(): Uri.Builder  [API 11+]
+ encodedAuthority(String): Uri.Builder  [API 1+]
+ encodedFragment(String): Uri.Builder  [API 1+]
+ encodedOpaquePart(String): Uri.Builder  [API 1+]
+ encodedPath(String): Uri.Builder  [API 1+]
+ encodedQuery(String): Uri.Builder  [API 1+]
+ fragment(String): Uri.Builder  [API 1+]
+ opaquePart(String): Uri.Builder  [API 1+]
+ path(String): Uri.Builder  [API 1+]
+ query(String): Uri.Builder  [API 1+]
+ scheme(String): Uri.Builder  [API 1+]
+ toString(): String  [API 1+]
```

## UrlQuerySanitizer — framework; API 1+ (33 +, 0 *, 0 ~, 0 -)

```
+ UrlQuerySanitizer()  [API 1+]
+ UrlQuerySanitizer(String)  [API 1+]
+ addSanitizedEntry(String, String): void  [API 1+]
+ clear(): void  [API 1+]
+ decodeHexDigit(char): int  [API 1+]
+ getAllButNulAndAngleBracketsLegal(): UrlQuerySanitizer.ValueSanitizer [static]  [API 1+]
+ getAllButNulLegal(): UrlQuerySanitizer.ValueSanitizer [static]  [API 1+]
+ getAllButWhitespaceLegal(): UrlQuerySanitizer.ValueSanitizer [static]  [API 1+]
+ getAllIllegal(): UrlQuerySanitizer.ValueSanitizer [static]  [API 1+]
+ getAllowUnregisteredParamaters(): boolean  [API 1+]
+ getAmpAndSpaceLegal(): UrlQuerySanitizer.ValueSanitizer [static]  [API 1+]
+ getAmpLegal(): UrlQuerySanitizer.ValueSanitizer [static]  [API 1+]
+ getEffectiveValueSanitizer(String): UrlQuerySanitizer.ValueSanitizer  [API 1+]
+ getParameterList(): List  [API 1+]
+ getParameterSet(): Set  [API 1+]
+ getPreferFirstRepeatedParameter(): boolean  [API 1+]
+ getSpaceLegal(): UrlQuerySanitizer.ValueSanitizer [static]  [API 1+]
+ getUnregisteredParameterValueSanitizer(): UrlQuerySanitizer.ValueSanitizer  [API 1+]
+ getUrlAndSpaceLegal(): UrlQuerySanitizer.ValueSanitizer [static]  [API 1+]
+ getUrlLegal(): UrlQuerySanitizer.ValueSanitizer [static]  [API 1+]
+ getValue(String): String  [API 1+]
+ getValueSanitizer(String): UrlQuerySanitizer.ValueSanitizer  [API 1+]
+ hasParameter(String): boolean  [API 1+]
+ isHexDigit(char): boolean  [API 1+]
+ parseEntry(String, String): void  [API 1+]
+ parseQuery(String): void  [API 1+]
+ parseUrl(String): void  [API 1+]
+ registerParameter(String, UrlQuerySanitizer.ValueSanitizer): void  [API 1+]
+ registerParameters(String[], UrlQuerySanitizer.ValueSanitizer): void  [API 1+]
+ setAllowUnregisteredParamaters(boolean): void  [API 1+]
+ setPreferFirstRepeatedParameter(boolean): void  [API 1+]
+ setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.ValueSanitizer): void  [API 1+]
+ unescape(String): String  [API 1+]
```

## UrlQuerySanitizer.IllegalCharacterValueSanitizer — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ IllegalCharacterValueSanitizer(int)  [API 1+]
+ sanitize(String): String  [API 1+]
```

## UrlQuerySanitizer.ParameterValuePair — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ParameterValuePair(UrlQuerySanitizer, String, String)  [API 1+]
+ mParameter [field]  [API 1+]
+ mValue [field]  [API 1+]
```

## UrlQuerySanitizer.ValueSanitizer — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ sanitize(String): String  [API 1+]
```

## VpnManager — framework; API 30+ (0 +, 0 *, 0 ~, 6 -)

```
- deleteProvisionedVpnProfile(): void  [API 30+]
- getProvisionedVpnProfileState(): VpnProfileState  [API 33+]
- provisionVpnProfile(PlatformVpnProfile): Intent  [API 30+]
- startProvisionedVpnProfile(): void  [API 30+, deprecated in 33]
- startProvisionedVpnProfileSession(): String  [API 33+]
- stopProvisionedVpnProfile(): void  [API 30+]
```

## VpnProfileState — declaration; API 33+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 33+]
- VpnProfileState(int, String, boolean, boolean)  [API 33+]
- describeContents(): int  [API 33+]
- equals(Object): boolean  [API 33+]
- getSessionId(): String  [API 33+]
- getState(): int  [API 33+]
- hashCode(): int  [API 33+]
- isAlwaysOn(): boolean  [API 33+]
- isLockdownEnabled(): boolean  [API 33+]
- toString(): String  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## VpnService — declaration; API 14+ (0 +, 0 *, 0 ~, 10 -)

```
- VpnService()  [API 14+]
- isAlwaysOn(): boolean  [API 29+]
- isLockdownEnabled(): boolean  [API 29+]
- onBind(Intent): IBinder  [API 14+]
- onRevoke(): void  [API 14+]
- prepare(Context): Intent [static]  [API 14+]
- protect(DatagramSocket): boolean  [API 14+]
- protect(Socket): boolean  [API 14+]
- protect(int): boolean  [API 14+]
- setUnderlyingNetworks(Network[]): boolean  [API 22+]
```

## VpnService.Builder — declaration; API 14+ (0 +, 0 *, 0 ~, 22 -)

```
- Builder(VpnService)  [API 14+]
- addAddress(InetAddress, int): VpnService.Builder  [API 14+]
- addAddress(String, int): VpnService.Builder  [API 14+]
- addAllowedApplication(String): VpnService.Builder  [API 21+]
- addDisallowedApplication(String): VpnService.Builder  [API 21+]
- addDnsServer(InetAddress): VpnService.Builder  [API 14+]
- addDnsServer(String): VpnService.Builder  [API 14+]
- addRoute(InetAddress, int): VpnService.Builder  [API 14+]
- addRoute(IpPrefix): VpnService.Builder  [API 33+]
- addRoute(String, int): VpnService.Builder  [API 14+]
- addSearchDomain(String): VpnService.Builder  [API 14+]
- allowBypass(): VpnService.Builder  [API 21+]
- allowFamily(int): VpnService.Builder  [API 21+]
- establish(): ParcelFileDescriptor  [API 14+]
- excludeRoute(IpPrefix): VpnService.Builder  [API 33+]
- setBlocking(boolean): VpnService.Builder  [API 21+]
- setConfigureIntent(PendingIntent): VpnService.Builder  [API 14+]
- setHttpProxy(ProxyInfo): VpnService.Builder  [API 29+]
- setMetered(boolean): VpnService.Builder  [API 29+]
- setMtu(int): VpnService.Builder  [API 14+]
- setSession(String): VpnService.Builder  [API 14+]
- setUnderlyingNetworks(Network[]): VpnService.Builder  [API 22+]
```


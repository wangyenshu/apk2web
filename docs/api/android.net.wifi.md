# android.net.wifi

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## EasyConnectStatusCallback — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## MloLink — declaration; API 33+ (0 +, 0 *, 0 ~, 15 -)

```
- CREATOR [field]  [API 33+]
- MloLink()  [API 33+]
- describeContents(): int  [API 33+]
- equals(Object): boolean  [API 33+]
- getApMacAddress(): MacAddress  [API 33+]
- getBand(): int  [API 33+]
- getChannel(): int  [API 33+]
- getLinkId(): int  [API 33+]
- getRssi(): int  [API 34+]
- getRxLinkSpeedMbps(): int  [API 34+]
- getStaMacAddress(): MacAddress  [API 33+]
- getState(): int  [API 33+]
- getTxLinkSpeedMbps(): int  [API 34+]
- hashCode(): int  [API 33+]
- toString(): String  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## ScanResult — declaration; API 1+ (0 +, 0 *, 0 ~, 18 -)

```
+ BSSID [field]  [API 1+]
- CREATOR [field]  [API 30+]
+ SSID [field]  [API 1+, deprecated in 33]
- ScanResult()  [API 30+]
- ScanResult(ScanResult)  [API 30+]
+ capabilities [field]  [API 1+]
+ centerFreq0 [field]  [API 23+]
+ centerFreq1 [field]  [API 23+]
+ channelWidth [field]  [API 23+]
- convertChannelToFrequencyMhzIfSupported(int, int): int [static]  [API 31+]
- convertFrequencyMhzToChannelIfSupported(int): int [static]  [API 31+]
- describeContents(): int  [API 1+]
+ frequency [field]  [API 1+]
- getAffiliatedMloLinks(): List  [API 33+]
- getApMldMacAddress(): MacAddress  [API 33+]
- getApMloLinkId(): int  [API 33+]
- getInformationElements(): List  [API 30+]
- getSecurityTypes(): int[]  [API 33+]
- getWifiSsid(): WifiSsid  [API 33+]
- getWifiStandard(): int  [API 30+]
- is80211azNtbResponder(): boolean  [API 35+]
- is80211mcResponder(): boolean  [API 23+]
- isPasspointNetwork(): boolean  [API 23+]
- isTwtResponder(): boolean  [API 35+]
+ level [field]  [API 1+]
+ operatorFriendlyName [field]  [API 23+, deprecated in 31]
+ timestamp [field]  [API 17+]
- toString(): String  [API 1+]
+ venueName [field]  [API 23+, deprecated in 31]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ScanResult.InformationElement — declaration; API 30+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 31+]
- InformationElement(ScanResult.InformationElement)  [API 30+]
- InformationElement(int, int, byte[])  [API 33+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getBytes(): ByteBuffer  [API 30+]
- getId(): int  [API 30+]
- getIdExt(): int  [API 30+]
- hashCode(): int  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## SoftApConfiguration — declaration; API 30+ (0 +, 0 *, 0 ~, 11 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getBssid(): MacAddress  [API 30+]
- getPassphrase(): String  [API 30+]
- getSecurityType(): int  [API 30+]
- getSsid(): String  [API 30+, deprecated in 33]
- getWifiSsid(): WifiSsid  [API 33+]
- hashCode(): int  [API 30+]
- isHiddenSsid(): boolean  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## SupplicantState — declaration; API 1+ (2 +, 0 *, 0 ~, 3 -)

```
+ ASSOCIATED [field]  [API 1+]
+ ASSOCIATING [field]  [API 1+]
+ AUTHENTICATING [field]  [API 14+]
+ COMPLETED [field]  [API 1+]
+ DISCONNECTED [field]  [API 1+]
+ DORMANT [field]  [API 1+]
+ FOUR_WAY_HANDSHAKE [field]  [API 1+]
+ GROUP_HANDSHAKE [field]  [API 1+]
+ INACTIVE [field]  [API 1+]
+ INTERFACE_DISABLED [field]  [API 14+]
+ INVALID [field]  [API 1+]
+ SCANNING [field]  [API 1+]
+ UNINITIALIZED [field]  [API 1+]
- describeContents(): int  [API 1+]
- isValidState(SupplicantState): boolean [static]  [API 1+]
+ valueOf(String): SupplicantState [static]  [API 1+]
+ values(): SupplicantState[] [static]  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## WifiAvailableChannel — declaration; API 34+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 34+]
- WifiAvailableChannel(int, int)  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- getFrequencyMhz(): int  [API 34+]
- getOperationalModes(): int  [API 34+]
- hashCode(): int  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## WifiConfiguration — declaration; API 1+, deprecated in 29 (0 +, 5 *, 0 ~, 10 -)

```
+ BSSID [field]  [API 1+]
+ FQDN [field]  [API 21+]
+ SSID [field]  [API 1+]
* WifiConfiguration()  [API 1+]
- WifiConfiguration(WifiConfiguration)  [API 30+, deprecated in 30]
+ allowedAuthAlgorithms [field]  [API 1+]
+ allowedGroupCiphers [field]  [API 1+]
+ allowedGroupManagementCiphers [field]  [API 29+]
+ allowedKeyManagement [field]  [API 1+]
+ allowedPairwiseCiphers [field]  [API 1+]
+ allowedProtocols [field]  [API 1+]
+ allowedSuiteBCiphers [field]  [API 29+]
- describeContents(): int  [API 1+]
+ enterpriseConfig [field]  [API 18+]
* getHttpProxy(): ProxyInfo  [API 26+]
- getKey(): String  [API 30+, deprecated in 30]
* getMacRandomizationSetting(): int  [API 33+, deprecated in 33]
- getRandomizedMacAddress(): MacAddress  [API 29+]
+ hiddenSSID [field]  [API 1+]
- isDppConfigurator(): boolean  [API 33+, deprecated in 33]
+ isHomeProviderNetwork [field]  [API 26+]
- isPasspoint(): boolean  [API 23+]
+ networkId [field]  [API 1+]
+ preSharedKey [field]  [API 1+]
+ priority [field]  [API 1+, deprecated in 26]
+ providerFriendlyName [field]  [API 23+]
+ roamingConsortiumIds [field]  [API 23+]
* setHttpProxy(ProxyInfo): void  [API 26+]
- setIpConfiguration(IpConfiguration): void  [API 33+, deprecated in 33]
* setMacRandomizationSetting(int): void  [API 33+, deprecated in 33]
- setSecurityParams(int): void  [API 30+, deprecated in 30]
+ status [field]  [API 1+]
- toString(): String  [API 1+]
+ wepKeys [field]  [API 1+, deprecated in 28]
+ wepTxKeyIndex [field]  [API 1+, deprecated in 28]
- writeToParcel(Parcel, int): void  [API 1+]
```

## WifiConfiguration.AuthAlgorithm — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

```
- strings [field]  [API 1+]
```

## WifiConfiguration.GroupCipher — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

```
- strings [field]  [API 1+]
```

## WifiConfiguration.GroupMgmtCipher — declaration; API 29+, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

## WifiConfiguration.KeyMgmt — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

```
- strings [field]  [API 1+]
```

## WifiConfiguration.PairwiseCipher — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

```
- strings [field]  [API 1+]
```

## WifiConfiguration.Protocol — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

```
- strings [field]  [API 1+]
```

## WifiConfiguration.Status — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

```
- strings [field]  [API 1+]
```

## WifiEnterpriseConfig — declaration; API 18+ (0 +, 31 *, 0 ~, 15 -)

```
- CREATOR [field]  [API 18+]
* WifiEnterpriseConfig()  [API 18+]
- WifiEnterpriseConfig(WifiEnterpriseConfig)  [API 18+]
- describeContents(): int  [API 18+]
- enableTrustOnFirstUse(boolean): void  [API 33+]
* getAltSubjectMatch(): String  [API 23+]
* getAnonymousIdentity(): String  [API 18+]
* getCaCertificate(): X509Certificate  [API 18+]
* getCaCertificates(): X509Certificate[]  [API 24+]
- getClientCertificate(): X509Certificate  [API 18+]
- getClientCertificateChain(): X509Certificate[]  [API 26+]
* getClientKeyPairAlias(): String  [API 31+]
- getClientPrivateKey(): PrivateKey  [API 30+]
* getDecoratedIdentityPrefix(): String  [API 31+]
* getDomainSuffixMatch(): String  [API 23+]
* getEapMethod(): int  [API 18+]
* getIdentity(): String  [API 18+]
* getMinimumTlsVersion(): int  [API 34+]
* getPassword(): String  [API 18+]
* getPhase2Method(): int  [API 18+]
* getPlmn(): String  [API 23+]
* getRealm(): String  [API 23+]
* getSubjectMatch(): String  [API 18+, deprecated in 23]
- hasCaCertificate(): boolean  [API 33+]
- isAuthenticationSimBased(): boolean  [API 30+]
- isEapMethodServerCertUsed(): boolean  [API 31+]
- isServerCertValidationEnabled(): boolean  [API 31+]
- isTrustOnFirstUseEnabled(): boolean  [API 33+]
* setAltSubjectMatch(String): void  [API 23+]
* setAnonymousIdentity(String): void  [API 18+]
* setCaCertificate(X509Certificate): void  [API 18+]
* setCaCertificates(X509Certificate[]): void  [API 24+]
- setClientKeyEntry(PrivateKey, X509Certificate): void  [API 18+]
- setClientKeyEntryWithCertificateChain(PrivateKey, X509Certificate[]): void  [API 26+]
* setClientKeyPairAlias(String): void  [API 31+]
* setDecoratedIdentityPrefix(String): void  [API 31+]
* setDomainSuffixMatch(String): void  [API 23+]
* setEapMethod(int): void  [API 18+]
* setIdentity(String): void  [API 18+]
* setMinimumTlsVersion(int): void  [API 34+]
* setPassword(String): void  [API 18+]
* setPhase2Method(int): void  [API 18+]
* setPlmn(String): void  [API 23+]
* setRealm(String): void  [API 23+]
* setSubjectMatch(String): void  [API 18+, deprecated in 23]
- toString(): String  [API 18+]
- writeToParcel(Parcel, int): void  [API 18+]
```

## WifiEnterpriseConfig.Eap — declaration; API 18+ (0 +, 0 *, 0 ~, 0 -)

## WifiEnterpriseConfig.Phase2 — declaration; API 18+ (0 +, 0 *, 0 ~, 0 -)

## WifiInfo — framework; API 1+ (3 +, 0 *, 3 ~, 29 -)

```
- describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
- getAffiliatedMloLinks(): List  [API 33+]
- getApMldMacAddress(): MacAddress  [API 33+]
- getApMloLinkId(): int  [API 33+]
- getApplicableRedactions(): long  [API 31+]
- getAssociatedMloLinks(): List  [API 34+]
- getBSSID(): String  [API 1+]
- getCurrentSecurityType(): int  [API 31+]
- getDetailedStateOf(SupplicantState): NetworkInfo.DetailedState [static]  [API 1+]
- getFrequency(): int  [API 21+]
- getHiddenSSID(): boolean  [API 1+]
- getInformationElements(): List  [API 31+]
~ getIpAddress(): int  [API 1+, deprecated in 31]
- getLinkSpeed(): int  [API 1+]
~ getMacAddress(): String  [API 1+]
- getMaxSupportedRxLinkSpeedMbps(): int  [API 30+]
- getMaxSupportedTxLinkSpeedMbps(): int  [API 30+]
- getNetworkId(): int  [API 1+]
- getPasspointFqdn(): String  [API 29+]
- getPasspointProviderFriendlyName(): String  [API 29+]
- getPasspointUniqueId(): String  [API 35+]
- getRssi(): int  [API 1+]
- getRxLinkSpeedMbps(): int  [API 29+]
~ getSSID(): String  [API 1+]
- getSubscriptionId(): int  [API 31+]
- getSupplicantState(): SupplicantState  [API 1+]
- getTxLinkSpeedMbps(): int  [API 29+]
- getWifiStandard(): int  [API 30+]
+ hashCode(): int  [API 1+]
- isRestricted(): boolean  [API 33+]
- makeCopy(long): TransportInfo  [API 31-34, removed in 35]
- makeCopy(long): WifiInfo  [API 31+]
+ toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## WifiInfo.Builder — declaration; API 30+ (0 +, 0 *, 0 ~, 8 -)

```
- Builder()  [API 30+]
- build(): WifiInfo  [API 30+]
- setBssid(String): WifiInfo.Builder  [API 30+]
- setCurrentSecurityType(int): WifiInfo.Builder  [API 31+]
- setNetworkId(int): WifiInfo.Builder  [API 30+]
- setRssi(int): WifiInfo.Builder  [API 30+]
- setSsid(byte[]): WifiInfo.Builder  [API 30+]
- setSubscriptionId(int): WifiInfo.Builder  [API 35+]
```

## WifiManager — framework; API 1+ (2 +, 0 *, 1 ~, 110 -)

```
- addLocalOnlyConnectionFailureListener(Executor, WifiManager.LocalOnlyConnectionFailureListener): void  [API 34+]
- addNetwork(WifiConfiguration): int  [API 1+, deprecated in 29]
- addNetworkPrivileged(WifiConfiguration): WifiManager.AddNetworkResult  [API 31+]
- addNetworkSuggestions(List): int  [API 29+]
- addOrUpdatePasspointConfiguration(PasspointConfiguration): void  [API 26+]
- addSuggestionConnectionStatusListener(Executor, WifiManager.SuggestionConnectionStatusListener): void  [API 30+]
- addSuggestionUserApprovalStatusListener(Executor, WifiManager.SuggestionUserApprovalStatusListener): void  [API 31+]
- allowAutojoinGlobal(boolean): void  [API 33+]
- calculateSignalLevel(int): int  [API 30+]
- calculateSignalLevel(int, int): int [static]  [API 1+, deprecated in 30]
- cancelWps(WifiManager.WpsCallback): void  [API 21+, deprecated in 28]
- compareSignalLevel(int, int): int [static]  [API 1+]
- createMulticastLock(String): WifiManager.MulticastLock  [API 4+]
+ createWifiLock(String): WifiManager.WifiLock  [API 1+, deprecated in 29]
- createWifiLock(int, String): WifiManager.WifiLock  [API 3+]
- disableNetwork(int): boolean  [API 1+, deprecated in 29]
- disconnect(): boolean  [API 1+, deprecated in 29]
- enableNetwork(int, boolean): boolean  [API 1+, deprecated in 29]
- flushPasspointAnqpCache(): void  [API 31+]
- getAllowedChannels(int, int): List  [API 34+]
- getCallerConfiguredNetworks(): List  [API 31+]
- getChannelData(Executor, Consumer): void  [API 34+]
- getConfiguredNetworks(): List  [API 1+, deprecated in 29]
+ getConnectionInfo(): WifiInfo  [API 1+, deprecated in 31]
- getDhcpInfo(): DhcpInfo  [API 1+, deprecated in 31]
- getMaxNumberOfChannelsPerNetworkSpecifierRequest(): int  [API 34+]
- getMaxNumberOfNetworkSuggestionsPerApp(): int  [API 29+]
- getMaxSignalLevel(): int  [API 30+]
- getMaxSupportedConcurrentTdlsSessions(Executor, Consumer): void  [API 34+]
- getNetworkSuggestions(): List  [API 30+]
- getNumberOfEnabledTdlsSessions(Executor, Consumer): void  [API 34+]
- getPasspointConfigurations(): List  [API 26+, deprecated in 29]
- getPerSsidRoamingModes(Executor, Consumer): void  [API 35+]
- getScanResults(): List  [API 1+]
- getStaConcurrencyForMultiInternetMode(): int  [API 33+]
- getUsableChannels(int, int): List  [API 34+]
- getWifiState(): int  [API 1+]
- is24GHzBandSupported(): boolean  [API 31+]
- is5GHzBandSupported(): boolean  [API 21+]
- is60GHzBandSupported(): boolean  [API 31+]
- is6GHzBandSupported(): boolean  [API 30+]
- isAggressiveRoamingModeSupported(): boolean  [API 35+]
- isAutoWakeupEnabled(): boolean  [API 30+]
- isBridgedApConcurrencySupported(): boolean  [API 31+]
- isCarrierNetworkOffloadEnabled(int, boolean): boolean  [API 31+]
- isD2dSupportedWhenInfraStaDisabled(): boolean  [API 35+]
- isDecoratedIdentitySupported(): boolean  [API 31+]
- isDeviceToApRttSupported(): boolean  [API 21+, deprecated in 29]
- isDualBandSimultaneousSupported(): boolean  [API 34+]
- isEasyConnectDppAkmSupported(): boolean  [API 33+]
- isEasyConnectEnrolleeResponderModeSupported(): boolean  [API 31+]
- isEasyConnectSupported(): boolean  [API 29+]
- isEnhancedOpenSupported(): boolean  [API 29+]
- isEnhancedPowerReportingSupported(): boolean  [API 21+]
- isMakeBeforeBreakWifiSwitchingSupported(): boolean  [API 31+]
- isP2pSupported(): boolean  [API 21+]
- isPasspointTermsAndConditionsSupported(): boolean  [API 31+]
- isPreferredNetworkOffloadSupported(): boolean  [API 21+]
- isScanAlwaysAvailable(): boolean  [API 18+, deprecated in 29]
- isScanThrottleEnabled(): boolean  [API 30+]
- isStaApConcurrencySupported(): boolean  [API 30+]
- isStaBridgedApConcurrencySupported(): boolean  [API 31+]
- isStaConcurrencyForLocalOnlyConnectionsSupported(): boolean  [API 31+]
- isStaConcurrencyForMultiInternetSupported(): boolean  [API 33+]
- isTdlsOperationCurrentlyAvailable(Executor, Consumer): void  [API 34+]
- isTdlsSupported(): boolean  [API 21+]
- isTidToLinkMappingNegotiationSupported(): boolean  [API 34+]
- isTlsMinimumVersionSupported(): boolean  [API 34+]
- isTlsV13Supported(): boolean  [API 34+]
- isTrustOnFirstUseSupported(): boolean  [API 33+]
- isWapiSupported(): boolean  [API 30+]
- isWepSupported(): boolean  [API 35+]
- isWifiDisplayR2Supported(): boolean  [API 31+]
~ isWifiEnabled(): boolean  [API 1+]
- isWifiPasspointEnabled(): boolean  [API 33+]
- isWifiStandardSupported(int): boolean  [API 30+]
- isWpa3SaeH2eSupported(): boolean  [API 31+]
- isWpa3SaePublicKeySupported(): boolean  [API 31+]
- isWpa3SaeSupported(): boolean  [API 29+]
- isWpa3SuiteBSupported(): boolean  [API 29+]
- isWpaPersonalSupported(): boolean  [API 35+]
- pingSupplicant(): boolean  [API 1+, deprecated in 26]
- queryAutojoinGlobal(Executor, Consumer): void  [API 33+]
- querySendDhcpHostnameRestriction(Executor, IntConsumer): void  [API 35+]
- reassociate(): boolean  [API 1+, deprecated in 29]
- reconnect(): boolean  [API 1+, deprecated in 29]
- registerScanResultsCallback(Executor, WifiManager.ScanResultsCallback): void  [API 30+]
- registerSubsystemRestartTrackingCallback(Executor, WifiManager.SubsystemRestartTrackingCallback): void  [API 31+]
- removeLocalOnlyConnectionFailureListener(WifiManager.LocalOnlyConnectionFailureListener): void  [API 34+]
- removeNetwork(int): boolean  [API 1+, deprecated in 29]
- removeNetworkSuggestions(List): int  [API 29+]
- removeNetworkSuggestions(List, int): int  [API 33+]
- removeNonCallerConfiguredNetworks(): boolean  [API 31+]
- removePasspointConfiguration(String): void  [API 26+, deprecated in 29]
- removePerSsidRoamingMode(WifiSsid): void  [API 35+]
- removeSuggestionConnectionStatusListener(WifiManager.SuggestionConnectionStatusListener): void  [API 30+]
- removeSuggestionUserApprovalStatusListener(WifiManager.SuggestionUserApprovalStatusListener): void  [API 31+]
- reportCreateInterfaceImpact(int, boolean, Executor, BiConsumer): void  [API 33+]
- saveConfiguration(): boolean  [API 1+, deprecated in 26]
- setPerSsidRoamingMode(WifiSsid, int): void  [API 35+]
- setSendDhcpHostnameRestriction(int): void  [API 35+]
- setTdlsEnabled(InetAddress, boolean): void  [API 19+]
- setTdlsEnabled(InetAddress, boolean, Executor, Consumer): void  [API 34+]
- setTdlsEnabledWithMacAddress(String, boolean): void  [API 19+]
- setTdlsEnabledWithMacAddress(String, boolean, Executor, Consumer): void  [API 34+]
- setWifiEnabled(boolean): boolean  [API 1+, deprecated in 29]
- startLocalOnlyHotspot(WifiManager.LocalOnlyHotspotCallback, Handler): void  [API 26+]
- startScan(): boolean  [API 1+, deprecated in 28]
- startWps(WpsInfo, WifiManager.WpsCallback): void  [API 21+, deprecated in 28]
- unregisterScanResultsCallback(WifiManager.ScanResultsCallback): void  [API 30+]
- unregisterSubsystemRestartTrackingCallback(WifiManager.SubsystemRestartTrackingCallback): void  [API 31+]
- updateNetwork(WifiConfiguration): int  [API 1+, deprecated in 29]
- validateSoftApConfiguration(SoftApConfiguration): boolean  [API 34+]
```

## WifiManager.AddNetworkResult — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- AddNetworkResult(int, int)  [API 31+]
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- networkId [field]  [API 31+]
- statusCode [field]  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## WifiManager.InterfaceCreationImpact — declaration; API 33+ (0 +, 5 *, 0 ~, 0 -)

```
* InterfaceCreationImpact(int, Set)  [API 33+]
* equals(Object): boolean  [API 33+]
* getInterfaceType(): int  [API 33+]
* getPackages(): Set  [API 33+]
* hashCode(): int  [API 33+]
```

## WifiManager.LocalOnlyConnectionFailureListener — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onConnectionFailed(WifiNetworkSpecifier, int): void  [API 34+]
```

## WifiManager.LocalOnlyHotspotCallback — declaration; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ LocalOnlyHotspotCallback()  [API 26+]
+ onFailed(int): void  [API 26+]
+ onStarted(WifiManager.LocalOnlyHotspotReservation): void  [API 26+]
+ onStopped(): void  [API 26+]
```

## WifiManager.LocalOnlyHotspotReservation — declaration; API 26+ (0 +, 0 *, 0 ~, 4 -)

```
- close(): void  [API 26+]
- finalize(): void  [API 26+]
- getSoftApConfiguration(): SoftApConfiguration  [API 30+]
- getWifiConfiguration(): WifiConfiguration  [API 26+, deprecated in 30]
```

## WifiManager.MulticastLock — declaration; API 4+ (0 +, 0 *, 0 ~, 6 -)

```
- acquire(): void  [API 4+]
- finalize(): void  [API 4+]
- isHeld(): boolean  [API 4+]
- release(): void  [API 4+]
- setReferenceCounted(boolean): void  [API 4+]
- toString(): String  [API 4+]
```

## WifiManager.ScanResultsCallback — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ ScanResultsCallback()  [API 30+]
+ onScanResultsAvailable(): void  [API 30+]
```

## WifiManager.SubsystemRestartTrackingCallback — declaration; API 31+ (3 +, 0 *, 0 ~, 0 -)

```
+ SubsystemRestartTrackingCallback()  [API 31+]
+ onSubsystemRestarted(): void  [API 31+]
+ onSubsystemRestarting(): void  [API 31+]
```

## WifiManager.SuggestionConnectionStatusListener — declaration; API 30+ (1 +, 0 *, 0 ~, 0 -)

```
+ onConnectionStatus(WifiNetworkSuggestion, int): void  [API 30+]
```

## WifiManager.SuggestionUserApprovalStatusListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onUserApprovalStatusChange(int): void  [API 31+]
```

## WifiManager.WifiLock — framework; API 1+ (2 +, 0 *, 3 ~, 2 -)

```
~ acquire(): void  [API 1+]
+ finalize(): void  [API 1+]
~ isHeld(): boolean  [API 1+]
~ release(): void  [API 1+]
- setReferenceCounted(boolean): void  [API 1+]
- setWorkSource(WorkSource): void  [API 9+]
+ toString(): String  [API 1+]
```

## WifiManager.WpsCallback — declaration; API 21+, deprecated in 28 (4 +, 0 *, 0 ~, 0 -)

```
+ WpsCallback()  [API 21+]
+ onFailed(int): void  [API 21+]
+ onStarted(String): void  [API 21+]
+ onSucceeded(): void  [API 21+]
```

## WifiNetworkSpecifier — declaration; API 29+ (0 +, 1 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 29+]
- canBeSatisfiedBy(NetworkSpecifier): boolean  [API 31+]
- describeContents(): int  [API 29+]
- equals(Object): boolean  [API 29+]
* getBand(): int  [API 31+]
- getPreferredChannelFrequenciesMhz(): int[]  [API 34+]
- hashCode(): int  [API 29+]
- redact(): NetworkSpecifier  [API 31+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## WifiNetworkSpecifier.Builder — declaration; API 29+ (0 +, 3 *, 0 ~, 13 -)

```
* Builder()  [API 29+]
* build(): WifiNetworkSpecifier  [API 29+]
* setBand(int): WifiNetworkSpecifier.Builder  [API 31+]
- setBssid(MacAddress): WifiNetworkSpecifier.Builder  [API 29+]
- setBssidPattern(MacAddress, MacAddress): WifiNetworkSpecifier.Builder  [API 29+]
- setIsEnhancedOpen(boolean): WifiNetworkSpecifier.Builder  [API 29+]
- setIsHiddenSsid(boolean): WifiNetworkSpecifier.Builder  [API 29+]
- setPreferredChannelsFrequenciesMhz(int[]): WifiNetworkSpecifier.Builder  [API 34+]
- setSsid(String): WifiNetworkSpecifier.Builder  [API 29+]
- setSsidPattern(PatternMatcher): WifiNetworkSpecifier.Builder  [API 29+]
- setWpa2EnterpriseConfig(WifiEnterpriseConfig): WifiNetworkSpecifier.Builder  [API 29+]
- setWpa2Passphrase(String): WifiNetworkSpecifier.Builder  [API 29+]
- setWpa3Enterprise192BitModeConfig(WifiEnterpriseConfig): WifiNetworkSpecifier.Builder  [API 31+]
- setWpa3EnterpriseConfig(WifiEnterpriseConfig): WifiNetworkSpecifier.Builder  [API 29+, deprecated in 31]
- setWpa3EnterpriseStandardModeConfig(WifiEnterpriseConfig): WifiNetworkSpecifier.Builder  [API 31+]
- setWpa3Passphrase(String): WifiNetworkSpecifier.Builder  [API 29+]
```

## WifiNetworkSuggestion — declaration; API 29+ (0 +, 14 *, 0 ~, 13 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- equals(Object): boolean  [API 29+]
* getBssid(): MacAddress  [API 30+]
- getEnterpriseConfig(): WifiEnterpriseConfig  [API 30+]
* getMacRandomizationSetting(): int  [API 33+]
- getPassphrase(): String  [API 30+]
* getPasspointConfig(): PasspointConfiguration  [API 30+]
* getPriority(): int  [API 30+]
* getPriorityGroup(): int  [API 31+]
* getSsid(): String  [API 30+]
* getSubscriptionGroup(): ParcelUuid  [API 33+]
* getSubscriptionId(): int  [API 31+]
* getWifiSsid(): WifiSsid  [API 33+]
- hashCode(): int  [API 29+]
- isAppInteractionRequired(): boolean  [API 30+]
* isCarrierMerged(): boolean  [API 31+]
* isCredentialSharedWithUser(): boolean  [API 30+]
- isEnhancedOpen(): boolean  [API 30+]
- isHiddenSsid(): boolean  [API 30+]
- isInitialAutojoinEnabled(): boolean  [API 30+]
- isMetered(): boolean  [API 30+]
* isRestricted(): boolean  [API 33+]
* isUntrusted(): boolean  [API 30+]
- isUserInteractionRequired(): boolean  [API 30+]
* isWifi7Enabled(): boolean  [API 35+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## WifiNetworkSuggestion.Builder — declaration; API 29+ (0 +, 16 *, 0 ~, 15 -)

```
* Builder()  [API 29+]
* build(): WifiNetworkSuggestion  [API 29+]
* setBssid(MacAddress): WifiNetworkSuggestion.Builder  [API 29+]
* setCarrierMerged(boolean): WifiNetworkSuggestion.Builder  [API 31+]
* setCredentialSharedWithUser(boolean): WifiNetworkSuggestion.Builder  [API 30+]
- setIsAppInteractionRequired(boolean): WifiNetworkSuggestion.Builder  [API 29+]
- setIsEnhancedOpen(boolean): WifiNetworkSuggestion.Builder  [API 29+]
- setIsHiddenSsid(boolean): WifiNetworkSuggestion.Builder  [API 29+]
- setIsInitialAutojoinEnabled(boolean): WifiNetworkSuggestion.Builder  [API 30+]
- setIsMetered(boolean): WifiNetworkSuggestion.Builder  [API 29+]
- setIsUserInteractionRequired(boolean): WifiNetworkSuggestion.Builder  [API 29+]
- setIsWpa3SaeH2eOnlyModeEnabled(boolean): WifiNetworkSuggestion.Builder  [API 31+]
* setMacRandomizationSetting(int): WifiNetworkSuggestion.Builder  [API 31+]
* setPasspointConfig(PasspointConfiguration): WifiNetworkSuggestion.Builder  [API 30+]
* setPriority(int): WifiNetworkSuggestion.Builder  [API 29+]
* setPriorityGroup(int): WifiNetworkSuggestion.Builder  [API 31+]
* setRestricted(boolean): WifiNetworkSuggestion.Builder  [API 33+]
* setSsid(String): WifiNetworkSuggestion.Builder  [API 29+]
* setSubscriptionGroup(ParcelUuid): WifiNetworkSuggestion.Builder  [API 33+]
* setSubscriptionId(int): WifiNetworkSuggestion.Builder  [API 31+]
* setUntrusted(boolean): WifiNetworkSuggestion.Builder  [API 30+]
- setWapiEnterpriseConfig(WifiEnterpriseConfig): WifiNetworkSuggestion.Builder  [API 30+]
- setWapiPassphrase(String): WifiNetworkSuggestion.Builder  [API 30+]
* setWifi7Enabled(boolean): WifiNetworkSuggestion.Builder  [API 35+]
* setWifiSsid(WifiSsid): WifiNetworkSuggestion.Builder  [API 33+]
- setWpa2EnterpriseConfig(WifiEnterpriseConfig): WifiNetworkSuggestion.Builder  [API 29+]
- setWpa2Passphrase(String): WifiNetworkSuggestion.Builder  [API 29+]
- setWpa3Enterprise192BitModeConfig(WifiEnterpriseConfig): WifiNetworkSuggestion.Builder  [API 31+]
- setWpa3EnterpriseConfig(WifiEnterpriseConfig): WifiNetworkSuggestion.Builder  [API 29+, deprecated in 31]
- setWpa3EnterpriseStandardModeConfig(WifiEnterpriseConfig): WifiNetworkSuggestion.Builder  [API 31+]
- setWpa3Passphrase(String): WifiNetworkSuggestion.Builder  [API 29+]
```

## WifiSsid — declaration; API 33+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 33+]
- describeContents(): int  [API 33+]
- equals(Object): boolean  [API 33+]
- fromBytes(byte[]): WifiSsid [static]  [API 33+]
- getBytes(): byte[]  [API 33+]
- hashCode(): int  [API 33+]
- toString(): String  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## WpsInfo — declaration; API 14+ (0 +, 0 *, 0 ~, 5 -)

```
+ BSSID [field]  [API 21+]
- CREATOR [field]  [API 14+]
- WpsInfo()  [API 14+]
- WpsInfo(WpsInfo)  [API 14+]
- describeContents(): int  [API 14+]
+ pin [field]  [API 14+]
+ setup [field]  [API 14+]
- toString(): String  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```


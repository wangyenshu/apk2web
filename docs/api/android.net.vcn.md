# android.net.vcn

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## VcnCellUnderlyingNetworkTemplate — declaration; API 33+ (0 +, 10 *, 0 ~, 2 -)

```
- equals(Object): boolean  [API 33+]
* getCbs(): int  [API 34+]
* getDun(): int  [API 34+]
* getIms(): int  [API 34+]
* getInternet(): int  [API 34+]
* getMms(): int  [API 34+]
* getOperatorPlmnIds(): Set  [API 33+]
* getOpportunistic(): int  [API 33+]
* getRcs(): int  [API 34+]
* getRoaming(): int  [API 33+]
* getSimSpecificCarrierIds(): Set  [API 33+]
- hashCode(): int  [API 33+]
```

## VcnCellUnderlyingNetworkTemplate.Builder — declaration; API 33+ (0 +, 13 *, 0 ~, 2 -)

```
* Builder()  [API 33+]
* build(): VcnCellUnderlyingNetworkTemplate  [API 33+]
* setCbs(int): VcnCellUnderlyingNetworkTemplate.Builder  [API 34+]
* setDun(int): VcnCellUnderlyingNetworkTemplate.Builder  [API 34+]
* setIms(int): VcnCellUnderlyingNetworkTemplate.Builder  [API 34+]
* setInternet(int): VcnCellUnderlyingNetworkTemplate.Builder  [API 34+]
* setMetered(int): VcnCellUnderlyingNetworkTemplate.Builder  [API 33+]
- setMinDownstreamBandwidthKbps(int, int): VcnCellUnderlyingNetworkTemplate.Builder  [API 33+]
- setMinUpstreamBandwidthKbps(int, int): VcnCellUnderlyingNetworkTemplate.Builder  [API 33+]
* setMms(int): VcnCellUnderlyingNetworkTemplate.Builder  [API 34+]
* setOperatorPlmnIds(Set): VcnCellUnderlyingNetworkTemplate.Builder  [API 33+]
* setOpportunistic(int): VcnCellUnderlyingNetworkTemplate.Builder  [API 33+]
* setRcs(int): VcnCellUnderlyingNetworkTemplate.Builder  [API 34+]
* setRoaming(int): VcnCellUnderlyingNetworkTemplate.Builder  [API 33+]
* setSimSpecificCarrierIds(Set): VcnCellUnderlyingNetworkTemplate.Builder  [API 33+]
```

## VcnConfig — declaration; API 31+ (0 +, 1 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
- getGatewayConnectionConfigs(): Set  [API 31+]
* getRestrictedUnderlyingNetworkTransports(): Set  [API 34+]
- hashCode(): int  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## VcnConfig.Builder — declaration; API 31+ (0 +, 2 *, 0 ~, 2 -)

```
- Builder(Context)  [API 31+]
- addGatewayConnectionConfig(VcnGatewayConnectionConfig): VcnConfig.Builder  [API 31+]
* build(): VcnConfig  [API 31+]
* setRestrictedUnderlyingNetworkTransports(Set): VcnConfig.Builder  [API 34+]
```

## VcnGatewayConnectionConfig — declaration; API 31+ (0 +, 6 *, 0 ~, 4 -)

```
- equals(Object): boolean  [API 31+]
- getExposedCapabilities(): int[]  [API 31+]
* getGatewayConnectionName(): String  [API 31+]
* getMaxMtu(): int  [API 31+]
* getMinUdpPort4500NatTimeoutSeconds(): int  [API 34+]
* getRetryIntervalsMillis(): long[]  [API 31+]
* getVcnUnderlyingNetworkPriorities(): List  [API 33+]
- hasGatewayOption(int): boolean  [API 34+]
- hashCode(): int  [API 31+]
* isSafeModeEnabled(): boolean  [API 35+]
```

## VcnGatewayConnectionConfig.Builder — declaration; API 31+ (0 +, 6 *, 0 ~, 5 -)

```
- Builder(String, IkeTunnelConnectionParams)  [API 31+]
- addExposedCapability(int): VcnGatewayConnectionConfig.Builder  [API 31+]
- addGatewayOption(int): VcnGatewayConnectionConfig.Builder  [API 34+]
* build(): VcnGatewayConnectionConfig  [API 31+]
- removeExposedCapability(int): VcnGatewayConnectionConfig.Builder  [API 31+]
- removeGatewayOption(int): VcnGatewayConnectionConfig.Builder  [API 34+]
* setMaxMtu(int): VcnGatewayConnectionConfig.Builder  [API 31+]
* setMinUdpPort4500NatTimeoutSeconds(int): VcnGatewayConnectionConfig.Builder  [API 34+]
* setRetryIntervalsMillis(long[]): VcnGatewayConnectionConfig.Builder  [API 31+]
* setSafeModeEnabled(boolean): VcnGatewayConnectionConfig.Builder  [API 35+]
* setVcnUnderlyingNetworkPriorities(List): VcnGatewayConnectionConfig.Builder  [API 33+]
```

## VcnManager — declaration; API 31+ (0 +, 0 *, 0 ~, 5 -)

```
- clearVcnConfig(ParcelUuid): void  [API 31+]
- getConfiguredSubscriptionGroups(): List  [API 31+]
- registerVcnStatusCallback(ParcelUuid, Executor, VcnManager.VcnStatusCallback): void  [API 31+]
- setVcnConfig(ParcelUuid, VcnConfig): void  [API 31+]
- unregisterVcnStatusCallback(VcnManager.VcnStatusCallback): void  [API 31+]
```

## VcnManager.VcnStatusCallback — declaration; API 31+ (3 +, 0 *, 0 ~, 0 -)

```
+ VcnStatusCallback()  [API 31+]
+ onGatewayConnectionError(String, int, Throwable): void  [API 31+]
+ onStatusChanged(int): void  [API 31+]
```

## VcnUnderlyingNetworkTemplate — declaration; API 33+ (0 +, 1 *, 0 ~, 6 -)

```
- equals(Object): boolean  [API 33+]
* getMetered(): int  [API 33+]
- getMinEntryDownstreamBandwidthKbps(): int  [API 33+]
- getMinEntryUpstreamBandwidthKbps(): int  [API 33+]
- getMinExitDownstreamBandwidthKbps(): int  [API 33+]
- getMinExitUpstreamBandwidthKbps(): int  [API 33+]
- hashCode(): int  [API 33+]
```

## VcnWifiUnderlyingNetworkTemplate — declaration; API 33+ (0 +, 1 *, 0 ~, 2 -)

```
- equals(Object): boolean  [API 33+]
* getSsids(): Set  [API 33+]
- hashCode(): int  [API 33+]
```

## VcnWifiUnderlyingNetworkTemplate.Builder — declaration; API 33+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder()  [API 33+]
* build(): VcnWifiUnderlyingNetworkTemplate  [API 33+]
* setMetered(int): VcnWifiUnderlyingNetworkTemplate.Builder  [API 33+]
- setMinDownstreamBandwidthKbps(int, int): VcnWifiUnderlyingNetworkTemplate.Builder  [API 33+]
- setMinUpstreamBandwidthKbps(int, int): VcnWifiUnderlyingNetworkTemplate.Builder  [API 33+]
* setSsids(Set): VcnWifiUnderlyingNetworkTemplate.Builder  [API 33+]
```


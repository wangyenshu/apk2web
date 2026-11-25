# android.telephony.data

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ApnSetting — declaration; API 28+ (9 +, 22 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 28+]
+ describeContents(): int  [API 28+]
+ equals(Object): boolean  [API 28+]
* getApnName(): String  [API 28+]
* getApnTypeBitmask(): int  [API 28+]
* getAuthType(): int  [API 28+]
* getCarrierId(): int  [API 29+]
* getEntryName(): String  [API 28+]
+ getId(): int  [API 28+]
* getMmsProxyAddress(): InetAddress  [API 28+, deprecated in 29]
+ getMmsProxyAddressAsString(): String  [API 29+]
* getMmsProxyPort(): int  [API 28+]
* getMmsc(): Uri  [API 28+]
* getMtuV4(): int  [API 33+]
* getMtuV6(): int  [API 33+]
* getMvnoType(): int  [API 28+]
* getNetworkTypeBitmask(): int  [API 28+]
* getOperatorNumeric(): String  [API 28+]
* getPassword(): String  [API 28+]
* getProfileId(): int  [API 33+]
* getProtocol(): int  [API 28+]
* getProxyAddress(): InetAddress  [API 28+, deprecated in 29]
+ getProxyAddressAsString(): String  [API 29+]
* getProxyPort(): int  [API 28+]
* getRoamingProtocol(): int  [API 28+]
* getUser(): String  [API 28+]
+ hashCode(): int  [API 28+]
* isAlwaysOn(): boolean  [API 35+]
+ isEnabled(): boolean  [API 28+]
* isPersistent(): boolean  [API 33+]
+ toString(): String  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## ApnSetting.Builder — declaration; API 28+ (3 +, 24 *, 0 ~, 0 -)

```
* Builder()  [API 28+]
* build(): ApnSetting  [API 28+]
* setAlwaysOn(boolean): ApnSetting.Builder  [API 35+]
* setApnName(String): ApnSetting.Builder  [API 28+]
* setApnTypeBitmask(int): ApnSetting.Builder  [API 28+]
* setAuthType(int): ApnSetting.Builder  [API 28+]
+ setCarrierEnabled(boolean): ApnSetting.Builder  [API 28+]
* setCarrierId(int): ApnSetting.Builder  [API 29+]
* setEntryName(String): ApnSetting.Builder  [API 28+]
* setMmsProxyAddress(InetAddress): ApnSetting.Builder  [API 28+, deprecated in 29]
+ setMmsProxyAddress(String): ApnSetting.Builder  [API 29+]
* setMmsProxyPort(int): ApnSetting.Builder  [API 28+]
* setMmsc(Uri): ApnSetting.Builder  [API 28+]
* setMtuV4(int): ApnSetting.Builder  [API 33+]
* setMtuV6(int): ApnSetting.Builder  [API 33+]
* setMvnoType(int): ApnSetting.Builder  [API 28+]
* setNetworkTypeBitmask(int): ApnSetting.Builder  [API 28+]
* setOperatorNumeric(String): ApnSetting.Builder  [API 28+]
* setPassword(String): ApnSetting.Builder  [API 28+]
* setPersistent(boolean): ApnSetting.Builder  [API 33+]
* setProfileId(int): ApnSetting.Builder  [API 33+]
* setProtocol(int): ApnSetting.Builder  [API 28+]
* setProxyAddress(InetAddress): ApnSetting.Builder  [API 28+, deprecated in 29]
+ setProxyAddress(String): ApnSetting.Builder  [API 29+]
* setProxyPort(int): ApnSetting.Builder  [API 28+]
* setRoamingProtocol(int): ApnSetting.Builder  [API 28+]
* setUser(String): ApnSetting.Builder  [API 28+]
```

## NetworkSliceInfo — declaration; API 31+ (0 +, 10 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getMappedHplmnSliceDifferentiator(): int  [API 31+]
* getMappedHplmnSliceServiceType(): int  [API 31+]
* getSliceDifferentiator(): int  [API 31+]
* getSliceServiceType(): int  [API 31+]
* getStatus(): int  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## NetworkSliceInfo.Builder — declaration; API 31+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* build(): NetworkSliceInfo  [API 31+]
* setMappedHplmnSliceDifferentiator(int): NetworkSliceInfo.Builder  [API 31+]
* setMappedHplmnSliceServiceType(int): NetworkSliceInfo.Builder  [API 31+]
* setSliceDifferentiator(int): NetworkSliceInfo.Builder  [API 31+]
* setSliceServiceType(int): NetworkSliceInfo.Builder  [API 31+]
* setStatus(int): NetworkSliceInfo.Builder  [API 31+]
```

## NetworkSlicingConfig — declaration; API 31+ (7 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 31+]
- NetworkSlicingConfig()  [API 31+]
+ describeContents(): int  [API 31+]
+ equals(Object): boolean  [API 31+]
+ getSliceInfo(): List  [API 31+]
+ getUrspRules(): List  [API 31+]
+ hashCode(): int  [API 31+]
+ toString(): String  [API 31+]
+ writeToParcel(Parcel, int): void  [API 31+]
```

## RouteSelectionDescriptor — declaration; API 31+ (10 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 31+]
+ describeContents(): int  [API 31+]
+ equals(Object): boolean  [API 31+]
+ getDataNetworkName(): List  [API 31+]
+ getPrecedence(): int  [API 31+]
+ getSessionType(): int  [API 31+]
+ getSliceInfo(): List  [API 31+]
+ getSscMode(): int  [API 31+]
+ hashCode(): int  [API 31+]
+ toString(): String  [API 31+]
+ writeToParcel(Parcel, int): void  [API 31+]
```

## TrafficDescriptor — declaration; API 31+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getDataNetworkName(): String  [API 31+]
* getOsAppId(): byte[]  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## TrafficDescriptor.Builder — declaration; API 31+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* build(): TrafficDescriptor  [API 31+]
* setDataNetworkName(String): TrafficDescriptor.Builder  [API 31+]
* setOsAppId(byte[]): TrafficDescriptor.Builder  [API 31+]
```

## UrspRule — declaration; API 31+ (8 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 31+]
+ describeContents(): int  [API 31+]
+ equals(Object): boolean  [API 31+]
+ getPrecedence(): int  [API 31+]
+ getRouteSelectionDescriptor(): List  [API 31+]
+ getTrafficDescriptors(): List  [API 31+]
+ hashCode(): int  [API 31+]
+ toString(): String  [API 31+]
+ writeToParcel(Parcel, int): void  [API 31+]
```


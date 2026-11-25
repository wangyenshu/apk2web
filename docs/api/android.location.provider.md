# android.location.provider

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ProviderProperties — declaration; API 31+ (0 +, 7 *, 0 ~, 7 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getAccuracy(): int  [API 31+]
* getPowerUsage(): int  [API 31+]
- hasAltitudeSupport(): boolean  [API 31+]
- hasBearingSupport(): boolean  [API 31+]
- hasCellRequirement(): boolean  [API 31+]
- hasMonetaryCost(): boolean  [API 31+]
- hasNetworkRequirement(): boolean  [API 31+]
- hasSatelliteRequirement(): boolean  [API 31+]
- hasSpeedSupport(): boolean  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## ProviderProperties.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 7 -)

```
* Builder()  [API 31+]
* Builder(ProviderProperties)  [API 31+]
* build(): ProviderProperties  [API 31+]
* setAccuracy(int): ProviderProperties.Builder  [API 31+]
- setHasAltitudeSupport(boolean): ProviderProperties.Builder  [API 31+]
- setHasBearingSupport(boolean): ProviderProperties.Builder  [API 31+]
- setHasCellRequirement(boolean): ProviderProperties.Builder  [API 31+]
- setHasMonetaryCost(boolean): ProviderProperties.Builder  [API 31+]
- setHasNetworkRequirement(boolean): ProviderProperties.Builder  [API 31+]
- setHasSatelliteRequirement(boolean): ProviderProperties.Builder  [API 31+]
- setHasSpeedSupport(boolean): ProviderProperties.Builder  [API 31+]
* setPowerUsage(int): ProviderProperties.Builder  [API 31+]
```


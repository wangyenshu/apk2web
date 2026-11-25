# android.adservices.common

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AdData — declaration; API 34+ (0 +, 10 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getAdCounterKeys(): Set  [API 35+]
* getAdFilters(): AdFilters  [API 35+]
* getAdRenderId(): String  [API 35+]
* getMetadata(): String  [API 34+]
* getRenderUri(): Uri  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## AdData.Builder — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): AdData  [API 34+]
* setAdCounterKeys(Set): AdData.Builder  [API 35+]
* setAdFilters(AdFilters): AdData.Builder  [API 35+]
* setAdRenderId(String): AdData.Builder  [API 35+]
* setMetadata(String): AdData.Builder  [API 34+]
* setRenderUri(Uri): AdData.Builder  [API 34+]
```

## AdFilters — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getAppInstallFilters(): AppInstallFilters  [API 35+]
* getFrequencyCapFilters(): FrequencyCapFilters  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## AdFilters.Builder — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): AdFilters  [API 35+]
* setAppInstallFilters(AppInstallFilters): AdFilters.Builder  [API 35+]
* setFrequencyCapFilters(FrequencyCapFilters): AdFilters.Builder  [API 35+]
```

## AdSelectionSignals — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 34+]
- EMPTY [field]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- fromString(String): AdSelectionSignals [static]  [API 34+]
- hashCode(): int  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## AdServicesOutcomeReceiver — declaration; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ onError(Throwable): void  [API 35+]
+ onResult(Object): void  [API 35+]
```

## AdServicesPermissions — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## AdTechIdentifier — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- fromString(String): AdTechIdentifier [static]  [API 34+]
- hashCode(): int  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## AppInstallFilters — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getPackageNames(): Set  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## AppInstallFilters.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): AppInstallFilters  [API 35+]
* setPackageNames(Set): AppInstallFilters.Builder  [API 35+]
```

## FrequencyCapFilters — declaration; API 35+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getKeyedFrequencyCapsForClickEvents(): List  [API 35+]
* getKeyedFrequencyCapsForImpressionEvents(): List  [API 35+]
* getKeyedFrequencyCapsForViewEvents(): List  [API 35+]
* getKeyedFrequencyCapsForWinEvents(): List  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## FrequencyCapFilters.Builder — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): FrequencyCapFilters  [API 35+]
* setKeyedFrequencyCapsForClickEvents(List): FrequencyCapFilters.Builder  [API 35+]
* setKeyedFrequencyCapsForImpressionEvents(List): FrequencyCapFilters.Builder  [API 35+]
* setKeyedFrequencyCapsForViewEvents(List): FrequencyCapFilters.Builder  [API 35+]
* setKeyedFrequencyCapsForWinEvents(List): FrequencyCapFilters.Builder  [API 35+]
```

## KeyedFrequencyCap — declaration; API 35+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getAdCounterKey(): int  [API 35+]
* getInterval(): Duration  [API 35+]
* getMaxCount(): int  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## KeyedFrequencyCap.Builder — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(int, int, Duration)  [API 35+]
* build(): KeyedFrequencyCap  [API 35+]
* setAdCounterKey(int): KeyedFrequencyCap.Builder  [API 35+]
* setInterval(Duration): KeyedFrequencyCap.Builder  [API 35+]
* setMaxCount(int): KeyedFrequencyCap.Builder  [API 35+]
```


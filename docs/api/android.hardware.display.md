# android.hardware.display

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DeviceProductInfo — declaration; API 31+ (0 +, 6 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 31+]
* DeviceProductInfo(String, String, String, int, int)  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
* getConnectionToSinkType(): int  [API 31+]
- getManufactureWeek(): int  [API 31+]
- getManufactureYear(): int  [API 31+]
* getManufacturerPnpId(): String  [API 31+]
* getModelYear(): int  [API 31+]
* getName(): String  [API 31+]
* getProductId(): String  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## DisplayManager — framework; API 17+ (0 +, 0 *, 0 ~, 11 -)

```
- createVirtualDisplay(String, int, int, int, Surface, int): VirtualDisplay  [API 19+]
- createVirtualDisplay(String, int, int, int, Surface, int, VirtualDisplay.Callback, Handler): VirtualDisplay  [API 21+]
- createVirtualDisplay(VirtualDisplayConfig): VirtualDisplay  [API 34+]
- createVirtualDisplay(VirtualDisplayConfig, Handler, VirtualDisplay.Callback): VirtualDisplay  [API 34+]
- getDisplay(int): Display  [API 17+]
- getDisplays(): Display[]  [API 17+]
- getDisplays(String): Display[]  [API 17+]
- getHdrConversionMode(): HdrConversionMode  [API 34+]
- getMatchContentFrameRateUserPreference(): int  [API 31+]
- registerDisplayListener(DisplayManager.DisplayListener, Handler): void  [API 17+]
- unregisterDisplayListener(DisplayManager.DisplayListener): void  [API 17+]
```

## DisplayManager.DisplayListener — framework; API 17+ (3 +, 0 *, 0 ~, 0 -)

```
+ onDisplayAdded(int): void  [API 17+]
+ onDisplayChanged(int): void  [API 17+]
+ onDisplayRemoved(int): void  [API 17+]
```

## HdrConversionMode — declaration; API 34+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* HdrConversionMode(int)  [API 34+]
* HdrConversionMode(int, int)  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getConversionMode(): int  [API 34+]
* getPreferredHdrOutputType(): int  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## VirtualDisplay — declaration; API 19+ (0 +, 2 *, 0 ~, 4 -)

```
- getDisplay(): Display  [API 19+]
* getSurface(): Surface  [API 20+]
- release(): void  [API 19+]
- resize(int, int, int): void  [API 21+]
* setSurface(Surface): void  [API 20+]
- toString(): String  [API 19+]
```

## VirtualDisplay.Callback — declaration; API 21+ (4 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 21+]
+ onPaused(): void  [API 21+]
+ onResumed(): void  [API 21+]
+ onStopped(): void  [API 21+]
```

## VirtualDisplayConfig — declaration; API 34+ (0 +, 13 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getDensityDpi(): int  [API 34+]
* getDisplayCategories(): Set  [API 34+]
* getFlags(): int  [API 34+]
* getHeight(): int  [API 34+]
* getName(): String  [API 34+]
* getRequestedRefreshRate(): float  [API 34+]
* getSurface(): Surface  [API 34+]
* getWidth(): int  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## VirtualDisplayConfig.Builder — declaration; API 34+ (0 +, 6 *, 0 ~, 1 -)

```
* Builder(String, int, int, int)  [API 34+]
- addDisplayCategory(String): VirtualDisplayConfig.Builder  [API 34+]
* build(): VirtualDisplayConfig  [API 34+]
* setDisplayCategories(Set): VirtualDisplayConfig.Builder  [API 34+]
* setFlags(int): VirtualDisplayConfig.Builder  [API 34+]
* setRequestedRefreshRate(float): VirtualDisplayConfig.Builder  [API 34+]
* setSurface(Surface): VirtualDisplayConfig.Builder  [API 34+]
```


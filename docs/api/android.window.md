# android.window

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BackEvent — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* BackEvent(float, float, float, int)  [API 34+]
* getProgress(): float  [API 34+]
* getSwipeEdge(): int  [API 34+]
* getTouchX(): float  [API 34+]
* getTouchY(): float  [API 34+]
* toString(): String  [API 34+]
```

## InputTransferToken — declaration; API 35+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
- hashCode(): int  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## OnBackAnimationCallback — declaration; API 34+ (3 +, 0 *, 0 ~, 0 -)

```
+ onBackCancelled(): void  [API 34+]
+ onBackProgressed(BackEvent): void  [API 34+]
+ onBackStarted(BackEvent): void  [API 34+]
```

## OnBackInvokedCallback — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ onBackInvoked(): void  [API 33+]
```

## OnBackInvokedDispatcher — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ registerOnBackInvokedCallback(int, OnBackInvokedCallback): void  [API 33+]
+ unregisterOnBackInvokedCallback(OnBackInvokedCallback): void  [API 33+]
```

## SplashScreen — declaration; API 31+ (3 +, 0 *, 0 ~, 0 -)

```
+ clearOnExitAnimationListener(): void  [API 31+]
+ setOnExitAnimationListener(SplashScreen.OnExitAnimationListener): void  [API 31+]
+ setSplashScreenTheme(int): void  [API 31+]
```

## SplashScreen.OnExitAnimationListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSplashScreenExit(SplashScreenView): void  [API 31+]
```

## SplashScreenView — declaration; API 31+ (0 +, 0 *, 0 ~, 7 -)

```
- getIconAnimationDuration(): Duration  [API 31+]
- getIconAnimationStart(): Instant  [API 31+]
- getIconView(): View  [API 31+]
- onDetachedFromWindow(): void  [API 31+]
- onLayout(boolean, int, int, int, int): void  [API 31+]
- remove(): void  [API 31+]
- setAlpha(float): void  [API 31+]
```

## SurfaceSyncGroup — declaration; API 34+ (0 +, 0 *, 0 ~, 5 -)

```
- SurfaceSyncGroup(String)  [API 34+]
- add(AttachedSurfaceControl, Runnable): boolean  [API 34+]
- add(SurfaceControlViewHost.SurfacePackage, Runnable): boolean  [API 34+]
- addTransaction(SurfaceControl.Transaction): void  [API 34+]
- markSyncReady(): void  [API 34+]
```

## TrustedPresentationThresholds — declaration; API 35+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 35+]
- TrustedPresentationThresholds(float, float, int)  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
- getMinAlpha(): float  [API 35+]
- getMinFractionRendered(): float  [API 35+]
- getStabilityRequirementMillis(): int  [API 35+]
- hashCode(): int  [API 35+]
- toString(): String  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```


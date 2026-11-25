# android.media.projection

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## MediaProjection — declaration; API 21+ (0 +, 0 *, 0 ~, 4 -)

```
- createVirtualDisplay(String, int, int, int, int, Surface, VirtualDisplay.Callback, Handler): VirtualDisplay  [API 21+]
- registerCallback(MediaProjection.Callback, Handler): void  [API 21+]
- stop(): void  [API 21+]
- unregisterCallback(MediaProjection.Callback): void  [API 21+]
```

## MediaProjection.Callback — declaration; API 21+ (4 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 21+]
+ onCapturedContentResize(int, int): void  [API 34+]
+ onCapturedContentVisibilityChanged(boolean): void  [API 34+]
+ onStop(): void  [API 21+]
```

## MediaProjectionConfig — declaration; API 34+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 34+]
- createConfigForDefaultDisplay(): MediaProjectionConfig [static]  [API 34+]
- createConfigForUserChoice(): MediaProjectionConfig [static]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- hashCode(): int  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## MediaProjectionManager — framework; API 21+ (0 +, 0 *, 0 ~, 3 -)

```
- createScreenCaptureIntent(): Intent  [API 21+]
- createScreenCaptureIntent(MediaProjectionConfig): Intent  [API 34+]
- getMediaProjection(int, Intent): MediaProjection  [API 21+]
```


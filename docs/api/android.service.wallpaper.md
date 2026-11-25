# android.service.wallpaper

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## WallpaperService — declaration; API 7+ (3 +, 0 *, 0 ~, 3 -)

```
- WallpaperService()  [API 7+]
- dump(FileDescriptor, PrintWriter, String[]): void  [API 7+]
- onBind(Intent): IBinder  [API 7+]
+ onCreate(): void  [API 7+]
+ onCreateEngine(): WallpaperService.Engine  [API 7+]
+ onDestroy(): void  [API 7+]
```

## WallpaperService.Engine — declaration; API 7+ (0 +, 0 *, 0 ~, 27 -)

```
- Engine(WallpaperService)  [API 7+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 14+]
- getDesiredMinimumHeight(): int  [API 7+]
- getDesiredMinimumWidth(): int  [API 7+]
- getDisplayContext(): Context  [API 29+]
- getSurfaceHolder(): SurfaceHolder  [API 7+]
- getWallpaperFlags(): int  [API 34+]
- isPreview(): boolean  [API 7+]
- isVisible(): boolean  [API 7+]
- notifyColorsChanged(): void  [API 27+]
- onApplyWindowInsets(WindowInsets): void  [API 21+]
- onCommand(String, int, int, int, Bundle, boolean): Bundle  [API 7+]
- onComputeColors(): WallpaperColors  [API 27+]
- onCreate(SurfaceHolder): void  [API 7+]
- onDesiredSizeChanged(int, int): void  [API 7+]
- onDestroy(): void  [API 7+]
- onOffsetsChanged(float, float, float, float, int, int): void  [API 7+]
- onSurfaceChanged(SurfaceHolder, int, int, int): void  [API 7+]
- onSurfaceCreated(SurfaceHolder): void  [API 7+]
- onSurfaceDestroyed(SurfaceHolder): void  [API 7+]
- onSurfaceRedrawNeeded(SurfaceHolder): void  [API 9+]
- onTouchEvent(MotionEvent): void  [API 7+]
- onVisibilityChanged(boolean): void  [API 7+]
- onWallpaperFlagsChanged(int): void  [API 34+]
- onZoomChanged(float): void  [API 30+]
- setOffsetNotificationsEnabled(boolean): void  [API 15+]
- setTouchEventsEnabled(boolean): void  [API 7+]
```


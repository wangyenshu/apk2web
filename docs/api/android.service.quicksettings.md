# android.service.quicksettings

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Tile — declaration; API 24+ (0 +, 16 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 24+]
* describeContents(): int  [API 24+]
* getActivityLaunchForClick(): PendingIntent  [API 34+]
* getContentDescription(): CharSequence  [API 24+]
* getIcon(): Icon  [API 24+]
* getLabel(): CharSequence  [API 24+]
* getState(): int  [API 24+]
* getStateDescription(): CharSequence  [API 30+]
* getSubtitle(): CharSequence  [API 29+]
* setActivityLaunchForClick(PendingIntent): void  [API 34+]
* setContentDescription(CharSequence): void  [API 24+]
* setIcon(Icon): void  [API 24+]
* setLabel(CharSequence): void  [API 24+]
* setState(int): void  [API 24+]
* setStateDescription(CharSequence): void  [API 30+]
* setSubtitle(CharSequence): void  [API 29+]
- updateTile(): void  [API 24+]
* writeToParcel(Parcel, int): void  [API 24+]
```

## TileService — declaration; API 24+ (0 +, 0 *, 0 ~, 16 -)

```
- TileService()  [API 24+]
- getQsTile(): Tile  [API 24+]
- isLocked(): boolean  [API 24+]
- isSecure(): boolean  [API 24+]
- onBind(Intent): IBinder  [API 24+]
- onClick(): void  [API 24+]
- onDestroy(): void  [API 24+]
- onStartListening(): void  [API 24+]
- onStopListening(): void  [API 24+]
- onTileAdded(): void  [API 24+]
- onTileRemoved(): void  [API 24+]
- requestListeningState(Context, ComponentName): void [static]  [API 24+]
- showDialog(Dialog): void  [API 24+]
- startActivityAndCollapse(Intent): void  [API 24+, deprecated in 34]
- startActivityAndCollapse(PendingIntent): void  [API 34+]
- unlockAndRun(Runnable): void  [API 24+]
```


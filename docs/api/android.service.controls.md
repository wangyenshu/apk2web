# android.service.controls

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Control — declaration; API 30+ (0 +, 0 *, 0 ~, 15 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- getAppIntent(): PendingIntent  [API 30+]
- getControlId(): String  [API 30+]
- getControlTemplate(): ControlTemplate  [API 30+]
- getCustomColor(): ColorStateList  [API 30+]
- getCustomIcon(): Icon  [API 30+]
- getDeviceType(): int  [API 30+]
- getStatus(): int  [API 30+]
- getStatusText(): CharSequence  [API 30+]
- getStructure(): CharSequence  [API 30+]
- getSubtitle(): CharSequence  [API 30+]
- getTitle(): CharSequence  [API 30+]
- getZone(): CharSequence  [API 30+]
- isAuthRequired(): boolean  [API 33+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## Control.StatefulBuilder — declaration; API 30+ (0 +, 0 *, 0 ~, 16 -)

```
- StatefulBuilder(Control)  [API 30+]
- StatefulBuilder(String, PendingIntent)  [API 30+]
- build(): Control  [API 30+]
- setAppIntent(PendingIntent): Control.StatefulBuilder  [API 30+]
- setAuthRequired(boolean): Control.StatefulBuilder  [API 33+]
- setControlId(String): Control.StatefulBuilder  [API 30+]
- setControlTemplate(ControlTemplate): Control.StatefulBuilder  [API 30+]
- setCustomColor(ColorStateList): Control.StatefulBuilder  [API 30+]
- setCustomIcon(Icon): Control.StatefulBuilder  [API 30+]
- setDeviceType(int): Control.StatefulBuilder  [API 30+]
- setStatus(int): Control.StatefulBuilder  [API 30+]
- setStatusText(CharSequence): Control.StatefulBuilder  [API 30+]
- setStructure(CharSequence): Control.StatefulBuilder  [API 30+]
- setSubtitle(CharSequence): Control.StatefulBuilder  [API 30+]
- setTitle(CharSequence): Control.StatefulBuilder  [API 30+]
- setZone(CharSequence): Control.StatefulBuilder  [API 30+]
```

## Control.StatelessBuilder — declaration; API 30+ (0 +, 0 *, 0 ~, 12 -)

```
- StatelessBuilder(Control)  [API 30+]
- StatelessBuilder(String, PendingIntent)  [API 30+]
- build(): Control  [API 30+]
- setAppIntent(PendingIntent): Control.StatelessBuilder  [API 30+]
- setControlId(String): Control.StatelessBuilder  [API 30+]
- setCustomColor(ColorStateList): Control.StatelessBuilder  [API 30+]
- setCustomIcon(Icon): Control.StatelessBuilder  [API 30+]
- setDeviceType(int): Control.StatelessBuilder  [API 30+]
- setStructure(CharSequence): Control.StatelessBuilder  [API 30+]
- setSubtitle(CharSequence): Control.StatelessBuilder  [API 30+]
- setTitle(CharSequence): Control.StatelessBuilder  [API 30+]
- setZone(CharSequence): Control.StatelessBuilder  [API 30+]
```

## ControlsProviderService — declaration; API 30+ (3 +, 0 *, 0 ~, 5 -)

```
- ControlsProviderService()  [API 30+]
+ createPublisherFor(List): Flow.Publisher  [API 30+]
+ createPublisherForAllAvailable(): Flow.Publisher  [API 30+]
- createPublisherForSuggested(): Flow.Publisher  [API 30+]
- onBind(Intent): IBinder  [API 30+]
- onUnbind(Intent): boolean  [API 30+]
+ performControlAction(String, ControlAction, Consumer): void  [API 30+]
- requestAddControl(Context, ComponentName, Control): void [static]  [API 30+]
```

## DeviceTypes — declaration; API 30+ (0 +, 0 *, 0 ~, 1 -)

```
- validDeviceType(int): boolean [static]  [API 30+]
```


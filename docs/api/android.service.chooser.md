# android.service.chooser

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AdditionalContentContract — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## AdditionalContentContract.Columns — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## AdditionalContentContract.CursorExtraKeys — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## AdditionalContentContract.MethodNames — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## ChooserAction — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* getAction(): PendingIntent  [API 34+]
* getIcon(): Icon  [API 34+]
* getLabel(): CharSequence  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## ChooserAction.Builder — declaration; API 34+ (0 +, 2 *, 0 ~, 0 -)

```
* Builder(Icon, CharSequence, PendingIntent)  [API 34+]
* build(): ChooserAction  [API 34+]
```

## ChooserResult — declaration; API 35+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
- getSelectedComponent(): ComponentName  [API 35+]
- getType(): int  [API 35+]
- hashCode(): int  [API 35+]
- isShortcut(): boolean  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## ChooserTarget — declaration; API 23+, deprecated in 30 (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 23+]
* ChooserTarget(CharSequence, Icon, float, ComponentName, Bundle)  [API 23+]
* describeContents(): int  [API 23+]
* getComponentName(): ComponentName  [API 23+]
* getIcon(): Icon  [API 23+]
* getIntentExtras(): Bundle  [API 23+]
* getScore(): float  [API 23+]
* getTitle(): CharSequence  [API 23+]
* toString(): String  [API 23+]
* writeToParcel(Parcel, int): void  [API 23+]
```

## ChooserTargetService — declaration; API 23+, deprecated in 30 (1 +, 0 *, 0 ~, 2 -)

```
- ChooserTargetService()  [API 23+]
- onBind(Intent): IBinder  [API 23+]
+ onGetChooserTargets(ComponentName, IntentFilter): List  [API 23+]
```


# android.content.om

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## FabricatedOverlay — declaration; API 34+ (0 +, 0 *, 0 ~, 8 -)

```
- FabricatedOverlay(String, String)  [API 34+]
- getIdentifier(): OverlayIdentifier  [API 34+]
- setNinePatchResourceValue(String, ParcelFileDescriptor, String): void  [API 35+]
- setResourceValue(String, AssetFileDescriptor, String): void  [API 35+]
- setResourceValue(String, ParcelFileDescriptor, String): void  [API 34+]
- setResourceValue(String, int, String, String): void  [API 34+]
- setResourceValue(String, int, int, String): void  [API 34+]
- setTargetOverlayable(String): void  [API 34+]
```

## OverlayIdentifier — declaration; API 34+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- hashCode(): int  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## OverlayInfo — declaration; API 34+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- getOverlayIdentifier(): OverlayIdentifier  [API 34+]
- getOverlayName(): String  [API 34+]
- getTargetOverlayableName(): String  [API 34+]
- getTargetPackageName(): String  [API 34+]
- hashCode(): int  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## OverlayManager — framework; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- commit(OverlayManagerTransaction): void  [API 34+]
- getOverlayInfosForTarget(String): List  [API 34+]
```

## OverlayManagerTransaction — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- newInstance(): OverlayManagerTransaction [static]  [API 34+]
- registerFabricatedOverlay(FabricatedOverlay): void  [API 34+]
- toString(): String  [API 34+]
- unregisterFabricatedOverlay(OverlayIdentifier): void  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```


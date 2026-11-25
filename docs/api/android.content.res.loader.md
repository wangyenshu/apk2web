# android.content.res.loader

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AssetsProvider — declaration; API 30+ (0 +, 0 *, 0 ~, 1 -)

```
- loadAssetFd(String, int): AssetFileDescriptor  [API 30+]
```

## ResourcesLoader — declaration; API 30+ (0 +, 3 *, 0 ~, 3 -)

```
* ResourcesLoader()  [API 30+]
- addProvider(ResourcesProvider): void  [API 30+]
- clearProviders(): void  [API 30+]
* getProviders(): List  [API 30+]
- removeProvider(ResourcesProvider): void  [API 30+]
* setProviders(List): void  [API 30+]
```

## ResourcesProvider — declaration; API 30+ (0 +, 0 *, 0 ~, 9 -)

```
- close(): void  [API 30+]
- empty(AssetsProvider): ResourcesProvider [static]  [API 30+]
- finalize(): void  [API 30+]
- loadFromApk(ParcelFileDescriptor): ResourcesProvider [static]  [API 30+]
- loadFromApk(ParcelFileDescriptor, AssetsProvider): ResourcesProvider [static]  [API 30+]
- loadFromDirectory(String, AssetsProvider): ResourcesProvider [static]  [API 30+]
- loadFromSplit(Context, String): ResourcesProvider [static]  [API 30+]
- loadFromTable(ParcelFileDescriptor, AssetsProvider): ResourcesProvider [static]  [API 30+]
- loadOverlay(OverlayInfo): ResourcesProvider [static]  [API 34+]
```


# android.view.displayhash

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DisplayHash — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## DisplayHashManager — framework; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- getSupportedHashAlgorithms(): Set  [API 31+]
- verifyDisplayHash(DisplayHash): VerifiedDisplayHash  [API 31+]
```

## DisplayHashResultCallback — declaration; API 31+ (2 +, 0 *, 0 ~, 0 -)

```
+ onDisplayHashError(int): void  [API 31+]
+ onDisplayHashResult(DisplayHash): void  [API 31+]
```

## VerifiedDisplayHash — declaration; API 31+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* VerifiedDisplayHash(long, Rect, String, byte[])  [API 31+]
* describeContents(): int  [API 31+]
* getBoundsInWindow(): Rect  [API 31+]
* getHashAlgorithm(): String  [API 31+]
* getImageHash(): byte[]  [API 31+]
* getTimeMillis(): long  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```


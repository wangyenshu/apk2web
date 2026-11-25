# android.app.blob

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BlobHandle — declaration; API 30+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 30+]
- createWithSha256(byte[], CharSequence, long, String): BlobHandle [static]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getExpiryTimeMillis(): long  [API 30+]
- getLabel(): CharSequence  [API 30+]
- getSha256Digest(): byte[]  [API 30+]
- getTag(): String  [API 30+]
- hashCode(): int  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## BlobStoreManager — framework; API 30+ (0 +, 0 *, 0 ~, 11 -)

```
- abandonSession(long): void  [API 30+]
- acquireLease(BlobHandle, CharSequence): void  [API 30+]
- acquireLease(BlobHandle, CharSequence, long): void  [API 30+]
- acquireLease(BlobHandle, int): void  [API 30+]
- acquireLease(BlobHandle, int, long): void  [API 30+]
- createSession(BlobHandle): long  [API 30+]
- getLeasedBlobs(): List  [API 30+]
- getRemainingLeaseQuotaBytes(): long  [API 30+]
- openBlob(BlobHandle): ParcelFileDescriptor  [API 30+]
- openSession(long): BlobStoreManager.Session  [API 30+]
- releaseLease(BlobHandle): void  [API 30+]
```

## BlobStoreManager.Session — framework; API 30+ (0 +, 0 *, 0 ~, 12 -)

```
- abandon(): void  [API 30+]
- allowPackageAccess(String, byte[]): void  [API 30+]
- allowPublicAccess(): void  [API 30+]
- allowSameSignatureAccess(): void  [API 30+]
- close(): void  [API 30+]
- commit(Executor, Consumer): void  [API 30+]
- getSize(): long  [API 30+]
- isPackageAccessAllowed(String, byte[]): boolean  [API 30+]
- isPublicAccessAllowed(): boolean  [API 30+]
- isSameSignatureAccessAllowed(): boolean  [API 30+]
- openRead(): ParcelFileDescriptor  [API 30+]
- openWrite(long, long): ParcelFileDescriptor  [API 30+]
```


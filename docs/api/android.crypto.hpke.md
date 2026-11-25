# android.crypto.hpke

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AeadParameterSpec — declaration; API 36+ (0 +, 0 *, 0 ~, 0 -)

## Hpke — declaration; API 36+ (0 +, 0 *, 0 ~, 7 -)

```
- getInstance(String): Hpke  [API 36+]
- getInstance(String, Provider): Hpke  [API 36+]
- getInstance(String, String): Hpke  [API 36+]
- getProvider(): Provider  [API 36+]
- getSuiteName(KemParameterSpec, KdfParameterSpec, AeadParameterSpec): String  [API 36+]
- open(PrivateKey, byte[], Message, byte[]): byte[]  [API 36+]
- seal(PublicKey, byte[], byte[], byte[]): Message  [API 36+]
```

## HpkeSpi — declaration; API 35+ (7 +, 0 *, 0 ~, 0 -)

```
+ engineExport(int, byte[]): byte[]  [API 35+]
+ engineInitRecipient(byte[], PrivateKey, byte[], PublicKey, byte[], byte[]): void  [API 35+]
+ engineInitSender(PublicKey, byte[], PrivateKey, byte[], byte[]): void  [API 35+]
+ engineInitSenderWithSeed(PublicKey, byte[], PrivateKey, byte[], byte[], byte[]): void  [API 35+]
+ engineOpen(byte[], byte[]): byte[]  [API 35+]
+ engineSeal(byte[], byte[]): byte[]  [API 35+]
+ getEncapsulated(): byte[]  [API 35+]
```

## KdfParameterSpec — declaration; API 36+ (0 +, 0 *, 0 ~, 0 -)

## KemParameterSpec — declaration; API 36+ (0 +, 0 *, 0 ~, 0 -)

## Message — declaration; API 36+ (0 +, 3 *, 0 ~, 0 -)

```
* Message(byte[], byte[])  [API 36+]
* getCiphertext(): byte[]  [API 36+]
* getEncapsulated(): byte[]  [API 36+]
```

## Recipient — declaration; API 36+ (0 +, 0 *, 0 ~, 4 -)

```
- export(int, byte[]): byte[]  [API 36+]
- getProvider(): Provider  [API 36+]
- getSpi(): HpkeSpi  [API 36+]
- open(byte[], byte[]): byte[]  [API 36+]
```

## Recipient.Builder — declaration; API 36+ (0 +, 0 *, 0 ~, 5 -)

```
- Builder(Hpke, byte[], PrivateKey)  [API 36+]
- build(): Recipient  [API 36+]
- setApplicationInfo(byte[]): Recipient.Builder  [API 36+]
- setPsk(byte[], byte[]): Recipient.Builder  [API 36+]
- setSenderKey(PublicKey): Recipient.Builder  [API 36+]
```

## Sender — declaration; API 36+ (0 +, 0 *, 0 ~, 5 -)

```
- export(int, byte[]): byte[]  [API 36+]
- getEncapsulated(): byte[]  [API 36+]
- getProvider(): Provider  [API 36+]
- getSpi(): HpkeSpi  [API 36+]
- seal(byte[], byte[]): byte[]  [API 36+]
```

## Sender.Builder — declaration; API 36+ (0 +, 0 *, 0 ~, 5 -)

```
- Builder(Hpke, PublicKey)  [API 36+]
- build(): Sender  [API 36+]
- setApplicationInfo(byte[]): Sender.Builder  [API 36+]
- setPsk(byte[], byte[]): Sender.Builder  [API 36+]
- setSenderKey(PrivateKey): Sender.Builder  [API 36+]
```

## XdhKeySpec — declaration; API 35+ (0 +, 1 *, 0 ~, 4 -)

```
* XdhKeySpec(byte[])  [API 35+]
- equals(Object): boolean  [API 35+]
- getFormat(): String  [API 35+]
- getKey(): byte[]  [API 35+]
- hashCode(): int  [API 35+]
```


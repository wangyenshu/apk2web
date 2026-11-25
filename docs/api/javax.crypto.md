# javax.crypto

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AEADBadTagException — declaration; API 19+ (2 +, 0 *, 0 ~, 0 -)

```
+ AEADBadTagException()  [API 19+]
+ AEADBadTagException(String)  [API 19+]
```

## BadPaddingException — framework (a2w.jdk.crypto.BadPaddingException); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BadPaddingException()  [API 1+]
+ BadPaddingException(String)  [API 1+]
```

## Cipher — framework (a2w.jdk.crypto.Cipher); API 1+ (10 +, 0 *, 1 ~, 27 -)

```
- Cipher(CipherSpi, Provider, String)  [API 1+]
+ doFinal(): byte[]  [API 1+]
- doFinal(ByteBuffer, ByteBuffer): int  [API 1+]
+ doFinal(byte[]): byte[]  [API 1+]
- doFinal(byte[], int): int  [API 1+]
+ doFinal(byte[], int, int): byte[]  [API 1+]
- doFinal(byte[], int, int, byte[]): int  [API 1+]
- doFinal(byte[], int, int, byte[], int): int  [API 1+]
+ getAlgorithm(): String  [API 1+]
~ getBlockSize(): int  [API 1+]
- getExemptionMechanism(): ExemptionMechanism  [API 1+]
+ getIV(): byte[]  [API 1+]
+ getInstance(String): Cipher [static]  [API 1+]
- getInstance(String, Provider): Cipher [static]  [API 1+]
- getInstance(String, String): Cipher [static]  [API 1+]
- getMaxAllowedKeyLength(String): int [static]  [API 1+]
- getMaxAllowedParameterSpec(String): AlgorithmParameterSpec [static]  [API 1+]
- getOutputSize(int): int  [API 1+]
- getParameters(): AlgorithmParameters  [API 1+]
- getProvider(): Provider  [API 1+]
- init(int, Certificate): void  [API 1+]
- init(int, Certificate, SecureRandom): void  [API 1+]
+ init(int, Key): void  [API 1+]
+ init(int, Key, AlgorithmParameterSpec): void  [API 1+]
- init(int, Key, AlgorithmParameterSpec, SecureRandom): void  [API 1+]
- init(int, Key, AlgorithmParameters): void  [API 1+]
- init(int, Key, AlgorithmParameters, SecureRandom): void  [API 1+]
- init(int, Key, SecureRandom): void  [API 1+]
- unwrap(byte[], String, int): Key  [API 1+]
- update(ByteBuffer, ByteBuffer): int  [API 1+]
+ update(byte[]): byte[]  [API 1+]
+ update(byte[], int, int): byte[]  [API 1+]
- update(byte[], int, int, byte[]): int  [API 1+]
- update(byte[], int, int, byte[], int): int  [API 1+]
- updateAAD(ByteBuffer): void  [API 19+]
- updateAAD(byte[]): void  [API 19+]
- updateAAD(byte[], int, int): void  [API 19+]
- wrap(Key): byte[]  [API 1+]
```

## CipherInputStream — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- CipherInputStream(InputStream)  [API 1+]
- CipherInputStream(InputStream, Cipher)  [API 1+]
- available(): int  [API 1+]
- close(): void  [API 1+]
- markSupported(): boolean  [API 1+]
- read(): int  [API 1+]
- read(byte[]): int  [API 1+]
- read(byte[], int, int): int  [API 1+]
- skip(long): long  [API 1+]
```

## CipherOutputStream — declaration; API 1+ (0 +, 0 *, 0 ~, 7 -)

```
- CipherOutputStream(OutputStream)  [API 1+]
- CipherOutputStream(OutputStream, Cipher)  [API 1+]
- close(): void  [API 1+]
- flush(): void  [API 1+]
- write(byte[]): void  [API 1+]
- write(byte[], int, int): void  [API 1+]
- write(int): void  [API 1+]
```

## CipherSpi — declaration; API 1+ (13 +, 0 *, 0 ~, 8 -)

```
- CipherSpi()  [API 1+]
- engineDoFinal(ByteBuffer, ByteBuffer): int  [API 1+]
+ engineDoFinal(byte[], int, int): byte[]  [API 1+]
+ engineDoFinal(byte[], int, int, byte[], int): int  [API 1+]
+ engineGetBlockSize(): int  [API 1+]
+ engineGetIV(): byte[]  [API 1+]
- engineGetKeySize(Key): int  [API 1+]
+ engineGetOutputSize(int): int  [API 1+]
+ engineGetParameters(): AlgorithmParameters  [API 1+]
+ engineInit(int, Key, AlgorithmParameterSpec, SecureRandom): void  [API 1+]
+ engineInit(int, Key, AlgorithmParameters, SecureRandom): void  [API 1+]
+ engineInit(int, Key, SecureRandom): void  [API 1+]
+ engineSetMode(String): void  [API 1+]
+ engineSetPadding(String): void  [API 1+]
- engineUnwrap(byte[], String, int): Key  [API 1+]
- engineUpdate(ByteBuffer, ByteBuffer): int  [API 1+]
+ engineUpdate(byte[], int, int): byte[]  [API 1+]
+ engineUpdate(byte[], int, int, byte[], int): int  [API 1+]
- engineUpdateAAD(ByteBuffer): void  [API 19+]
- engineUpdateAAD(byte[], int, int): void  [API 19+]
- engineWrap(Key): byte[]  [API 1+]
```

## EncryptedPrivateKeyInfo — declaration; API 1+ (0 +, 5 *, 0 ~, 6 -)

```
- EncryptedPrivateKeyInfo(AlgorithmParameters, byte[])  [API 1+]
* EncryptedPrivateKeyInfo(String, byte[])  [API 1+]
* EncryptedPrivateKeyInfo(byte[])  [API 1+]
* getAlgName(): String  [API 1+]
- getAlgParameters(): AlgorithmParameters  [API 1+]
* getEncoded(): byte[]  [API 1+]
* getEncryptedData(): byte[]  [API 1+]
- getKeySpec(Cipher): PKCS8EncodedKeySpec  [API 1+]
- getKeySpec(Key): PKCS8EncodedKeySpec  [API 1+]
- getKeySpec(Key, Provider): PKCS8EncodedKeySpec  [API 1+]
- getKeySpec(Key, String): PKCS8EncodedKeySpec  [API 1+]
```

## ExemptionMechanism — declaration; API 1+ (0 +, 0 *, 0 ~, 14 -)

```
- ExemptionMechanism(ExemptionMechanismSpi, Provider, String)  [API 1+]
- genExemptionBlob(): byte[]  [API 1+]
- genExemptionBlob(byte[]): int  [API 1+]
- genExemptionBlob(byte[], int): int  [API 1+]
- getInstance(String): ExemptionMechanism [static]  [API 1+]
- getInstance(String, Provider): ExemptionMechanism [static]  [API 1+]
- getInstance(String, String): ExemptionMechanism [static]  [API 1+]
- getName(): String  [API 1+]
- getOutputSize(int): int  [API 1+]
- getProvider(): Provider  [API 1+]
- init(Key): void  [API 1+]
- init(Key, AlgorithmParameterSpec): void  [API 1+]
- init(Key, AlgorithmParameters): void  [API 1+]
- isCryptoAllowed(Key): boolean  [API 1+]
```

## ExemptionMechanismException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ExemptionMechanismException()  [API 1+]
+ ExemptionMechanismException(String)  [API 1+]
```

## ExemptionMechanismSpi — declaration; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ ExemptionMechanismSpi()  [API 1+]
+ engineGenExemptionBlob(): byte[]  [API 1+]
+ engineGenExemptionBlob(byte[], int): int  [API 1+]
+ engineGetOutputSize(int): int  [API 1+]
+ engineInit(Key): void  [API 1+]
+ engineInit(Key, AlgorithmParameterSpec): void  [API 1+]
+ engineInit(Key, AlgorithmParameters): void  [API 1+]
```

## IllegalBlockSizeException — framework (a2w.jdk.crypto.IllegalBlockSizeException); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ IllegalBlockSizeException()  [API 1+]
+ IllegalBlockSizeException(String)  [API 1+]
```

## KeyAgreement — declaration; API 1+ (0 +, 0 *, 0 ~, 14 -)

```
- KeyAgreement(KeyAgreementSpi, Provider, String)  [API 1+]
- doPhase(Key, boolean): Key  [API 1+]
- generateSecret(): byte[]  [API 1+]
- generateSecret(String): SecretKey  [API 1+]
- generateSecret(byte[], int): int  [API 1+]
- getAlgorithm(): String  [API 1+]
- getInstance(String): KeyAgreement [static]  [API 1+]
- getInstance(String, Provider): KeyAgreement [static]  [API 1+]
- getInstance(String, String): KeyAgreement [static]  [API 1+]
- getProvider(): Provider  [API 1+]
- init(Key): void  [API 1+]
- init(Key, AlgorithmParameterSpec): void  [API 1+]
- init(Key, AlgorithmParameterSpec, SecureRandom): void  [API 1+]
- init(Key, SecureRandom): void  [API 1+]
```

## KeyAgreementSpi — declaration; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ KeyAgreementSpi()  [API 1+]
+ engineDoPhase(Key, boolean): Key  [API 1+]
+ engineGenerateSecret(): byte[]  [API 1+]
+ engineGenerateSecret(String): SecretKey  [API 1+]
+ engineGenerateSecret(byte[], int): int  [API 1+]
+ engineInit(Key, AlgorithmParameterSpec, SecureRandom): void  [API 1+]
+ engineInit(Key, SecureRandom): void  [API 1+]
```

## KeyGenerator — declaration; API 1+ (0 +, 0 *, 0 ~, 12 -)

```
- KeyGenerator(KeyGeneratorSpi, Provider, String)  [API 1+]
- generateKey(): SecretKey  [API 1+]
- getAlgorithm(): String  [API 1+]
- getInstance(String): KeyGenerator [static]  [API 1+]
- getInstance(String, Provider): KeyGenerator [static]  [API 1+]
- getInstance(String, String): KeyGenerator [static]  [API 1+]
- getProvider(): Provider  [API 1+]
- init(AlgorithmParameterSpec): void  [API 1+]
- init(AlgorithmParameterSpec, SecureRandom): void  [API 1+]
- init(SecureRandom): void  [API 1+]
- init(int): void  [API 1+]
- init(int, SecureRandom): void  [API 1+]
```

## KeyGeneratorSpi — declaration; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ KeyGeneratorSpi()  [API 1+]
+ engineGenerateKey(): SecretKey  [API 1+]
+ engineInit(AlgorithmParameterSpec, SecureRandom): void  [API 1+]
+ engineInit(SecureRandom): void  [API 1+]
+ engineInit(int, SecureRandom): void  [API 1+]
```

## Mac — declaration; API 1+ (0 +, 0 *, 0 ~, 18 -)

```
- Mac(MacSpi, Provider, String)  [API 1+]
- clone(): Object  [API 1+]
- doFinal(): byte[]  [API 1+]
- doFinal(byte[]): byte[]  [API 1+]
- doFinal(byte[], int): void  [API 1+]
- getAlgorithm(): String  [API 1+]
- getInstance(String): Mac [static]  [API 1+]
- getInstance(String, Provider): Mac [static]  [API 1+]
- getInstance(String, String): Mac [static]  [API 1+]
- getMacLength(): int  [API 1+]
- getProvider(): Provider  [API 1+]
- init(Key): void  [API 1+]
- init(Key, AlgorithmParameterSpec): void  [API 1+]
- reset(): void  [API 1+]
- update(ByteBuffer): void  [API 1+]
- update(byte): void  [API 1+]
- update(byte[]): void  [API 1+]
- update(byte[], int, int): void  [API 1+]
```

## MacSpi — declaration; API 1+ (6 +, 0 *, 0 ~, 3 -)

```
- MacSpi()  [API 1+]
- clone(): Object  [API 1+]
+ engineDoFinal(): byte[]  [API 1+]
+ engineGetMacLength(): int  [API 1+]
+ engineInit(Key, AlgorithmParameterSpec): void  [API 1+]
+ engineReset(): void  [API 1+]
- engineUpdate(ByteBuffer): void  [API 1+]
+ engineUpdate(byte): void  [API 1+]
+ engineUpdate(byte[], int, int): void  [API 1+]
```

## NoSuchPaddingException — framework (a2w.jdk.crypto.NoSuchPaddingException); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoSuchPaddingException()  [API 1+]
+ NoSuchPaddingException(String)  [API 1+]
```

## NullCipher — missing; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- NullCipher()  [API 1+]
```

## SealedObject — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- SealedObject(SealedObject)  [API 1+]
- SealedObject(Serializable, Cipher)  [API 1+]
+ encodedParams [field]  [API 1+]
- getAlgorithm(): String  [API 1+]
- getObject(Cipher): Object  [API 1+]
- getObject(Key): Object  [API 1+]
- getObject(Key, String): Object  [API 1+]
```

## SecretKey — framework (a2w.jdk.crypto.SecretKey); API 1+ (0 +, 0 *, 0 ~, 0 -)

## SecretKeyFactory — framework (a2w.jdk.crypto.SecretKeyFactory); API 1+ (3 +, 0 *, 0 ~, 6 -)

```
- SecretKeyFactory(SecretKeyFactorySpi, Provider, String)  [API 1+]
+ generateSecret(KeySpec): SecretKey  [API 1+]
+ getAlgorithm(): String  [API 1+]
+ getInstance(String): SecretKeyFactory [static]  [API 1+]
- getInstance(String, Provider): SecretKeyFactory [static]  [API 1+]
- getInstance(String, String): SecretKeyFactory [static]  [API 1+]
- getKeySpec(SecretKey, Class): KeySpec  [API 1+]
- getProvider(): Provider  [API 1+]
- translateKey(SecretKey): SecretKey  [API 1+]
```

## SecretKeyFactorySpi — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ SecretKeyFactorySpi()  [API 1+]
+ engineGenerateSecret(KeySpec): SecretKey  [API 1+]
+ engineGetKeySpec(SecretKey, Class): KeySpec  [API 1+]
+ engineTranslateKey(SecretKey): SecretKey  [API 1+]
```

## ShortBufferException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ShortBufferException()  [API 1+]
+ ShortBufferException(String)  [API 1+]
```


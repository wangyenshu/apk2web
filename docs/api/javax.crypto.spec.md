# javax.crypto.spec

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ChaCha20ParameterSpec — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* ChaCha20ParameterSpec(byte[], int)  [API 35+]
* getCounter(): int  [API 35+]
* getNonce(): byte[]  [API 35+]
```

## DESKeySpec — framework (a2w.jdk.crypto.spec.DESKeySpec); API 1+ (3 +, 0 *, 0 ~, 2 -)

```
+ DESKeySpec(byte[])  [API 1+]
+ DESKeySpec(byte[], int)  [API 1+]
+ getKey(): byte[]  [API 1+]
- isParityAdjusted(byte[], int): boolean [static]  [API 1+]
- isWeak(byte[], int): boolean [static]  [API 1+]
```

## DESedeKeySpec — declaration; API 1+ (0 +, 2 *, 0 ~, 2 -)

```
* DESedeKeySpec(byte[])  [API 1+]
- DESedeKeySpec(byte[], int)  [API 1+]
* getKey(): byte[]  [API 1+]
- isParityAdjusted(byte[], int): boolean [static]  [API 1+]
```

## DHGenParameterSpec — declaration; API 1+ (0 +, 3 *, 0 ~, 0 -)

```
* DHGenParameterSpec(int, int)  [API 1+]
* getExponentSize(): int  [API 1+]
* getPrimeSize(): int  [API 1+]
```

## DHParameterSpec — declaration; API 1+ (0 +, 5 *, 0 ~, 0 -)

```
* DHParameterSpec(BigInteger, BigInteger)  [API 1+]
* DHParameterSpec(BigInteger, BigInteger, int)  [API 1+]
* getG(): BigInteger  [API 1+]
* getL(): int  [API 1+]
* getP(): BigInteger  [API 1+]
```

## DHPrivateKeySpec — declaration; API 1+ (0 +, 4 *, 0 ~, 0 -)

```
* DHPrivateKeySpec(BigInteger, BigInteger, BigInteger)  [API 1+]
* getG(): BigInteger  [API 1+]
* getP(): BigInteger  [API 1+]
* getX(): BigInteger  [API 1+]
```

## DHPublicKeySpec — declaration; API 1+ (0 +, 4 *, 0 ~, 0 -)

```
* DHPublicKeySpec(BigInteger, BigInteger, BigInteger)  [API 1+]
* getG(): BigInteger  [API 1+]
* getP(): BigInteger  [API 1+]
* getY(): BigInteger  [API 1+]
```

## GCMParameterSpec — declaration; API 19+ (0 +, 0 *, 0 ~, 4 -)

```
- GCMParameterSpec(int, byte[])  [API 19+]
- GCMParameterSpec(int, byte[], int, int)  [API 19+]
- getIV(): byte[]  [API 19+]
- getTLen(): int  [API 19+]
```

## IvParameterSpec — framework (a2w.jdk.crypto.spec.IvParameterSpec); API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ IvParameterSpec(byte[])  [API 1+]
+ IvParameterSpec(byte[], int, int)  [API 1+]
+ getIV(): byte[]  [API 1+]
```

## OAEPParameterSpec — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- DEFAULT [field]  [API 1+]
- OAEPParameterSpec(String, String, AlgorithmParameterSpec, PSource)  [API 1+]
- getDigestAlgorithm(): String  [API 1+]
- getMGFAlgorithm(): String  [API 1+]
- getMGFParameters(): AlgorithmParameterSpec  [API 1+]
- getPSource(): PSource  [API 1+]
```

## PBEKeySpec — declaration; API 1+ (0 +, 7 *, 0 ~, 1 -)

```
* PBEKeySpec(char[])  [API 1+]
* PBEKeySpec(char[], byte[], int)  [API 1+]
* PBEKeySpec(char[], byte[], int, int)  [API 1+]
- clearPassword(): void  [API 1+]
* getIterationCount(): int  [API 1+]
* getKeyLength(): int  [API 1+]
* getPassword(): char[]  [API 1+]
* getSalt(): byte[]  [API 1+]
```

## PBEParameterSpec — declaration; API 1+ (0 +, 3 *, 0 ~, 2 -)

```
* PBEParameterSpec(byte[], int)  [API 1+]
- PBEParameterSpec(byte[], int, AlgorithmParameterSpec)  [API 26+]
* getIterationCount(): int  [API 1+]
- getParameterSpec(): AlgorithmParameterSpec  [API 26+]
* getSalt(): byte[]  [API 1+]
```

## PSource — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- PSource(String)  [API 1+]
- getAlgorithm(): String  [API 1+]
```

## PSource.PSpecified — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- DEFAULT [field]  [API 1+]
- PSpecified(byte[])  [API 1+]
- getValue(): byte[]  [API 1+]
```

## RC2ParameterSpec — declaration; API 1+ (0 +, 6 *, 0 ~, 1 -)

```
* RC2ParameterSpec(int)  [API 1+]
* RC2ParameterSpec(int, byte[])  [API 1+]
- RC2ParameterSpec(int, byte[], int)  [API 1+]
* equals(Object): boolean  [API 1+]
* getEffectiveKeyBits(): int  [API 1+]
* getIV(): byte[]  [API 1+]
* hashCode(): int  [API 1+]
```

## RC5ParameterSpec — declaration; API 1+ (0 +, 8 *, 0 ~, 1 -)

```
* RC5ParameterSpec(int, int, int)  [API 1+]
* RC5ParameterSpec(int, int, int, byte[])  [API 1+]
- RC5ParameterSpec(int, int, int, byte[], int)  [API 1+]
* equals(Object): boolean  [API 1+]
* getIV(): byte[]  [API 1+]
* getRounds(): int  [API 1+]
* getVersion(): int  [API 1+]
* getWordSize(): int  [API 1+]
* hashCode(): int  [API 1+]
```

## SecretKeySpec — framework (a2w.jdk.crypto.spec.SecretKeySpec); API 1+ (5 +, 0 *, 1 ~, 1 -)

```
+ SecretKeySpec(byte[], String)  [API 1+]
- SecretKeySpec(byte[], int, int, String)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getAlgorithm(): String  [API 1+]
+ getEncoded(): byte[]  [API 1+]
~ getFormat(): String  [API 1+]
+ hashCode(): int  [API 1+]
```


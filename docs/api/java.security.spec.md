# java.security.spec

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AlgorithmParameterSpec — framework (a2w.jdk.sec.spec.AlgorithmParameterSpec); API 1+ (0 +, 0 *, 0 ~, 0 -)

## DSAGenParameterSpec — declaration; API 35+ (0 +, 0 *, 0 ~, 5 -)

```
- DSAGenParameterSpec(int, int)  [API 35+]
- DSAGenParameterSpec(int, int, int)  [API 35+]
- getPrimePLength(): int  [API 35+]
- getSeedLength(): int  [API 35+]
- getSubprimeQLength(): int  [API 35+]
```

## DSAParameterSpec — declaration; API 1+ (0 +, 4 *, 0 ~, 0 -)

```
* DSAParameterSpec(BigInteger, BigInteger, BigInteger)  [API 1+]
* getG(): BigInteger  [API 1+]
* getP(): BigInteger  [API 1+]
* getQ(): BigInteger  [API 1+]
```

## DSAPrivateKeySpec — declaration; API 1+ (0 +, 5 *, 0 ~, 0 -)

```
* DSAPrivateKeySpec(BigInteger, BigInteger, BigInteger, BigInteger)  [API 1+]
* getG(): BigInteger  [API 1+]
* getP(): BigInteger  [API 1+]
* getQ(): BigInteger  [API 1+]
* getX(): BigInteger  [API 1+]
```

## DSAPublicKeySpec — declaration; API 1+ (0 +, 5 *, 0 ~, 0 -)

```
* DSAPublicKeySpec(BigInteger, BigInteger, BigInteger, BigInteger)  [API 1+]
* getG(): BigInteger  [API 1+]
* getP(): BigInteger  [API 1+]
* getQ(): BigInteger  [API 1+]
* getY(): BigInteger  [API 1+]
```

## ECField — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getFieldSize(): int  [API 1+]
```

## ECFieldF2m — declaration; API 1+ (0 +, 2 *, 0 ~, 7 -)

```
* ECFieldF2m(int)  [API 1+]
- ECFieldF2m(int, BigInteger)  [API 1+]
- ECFieldF2m(int, int[])  [API 1+]
- equals(Object): boolean  [API 1+]
- getFieldSize(): int  [API 1+]
* getM(): int  [API 1+]
- getMidTermsOfReductionPolynomial(): int[]  [API 1+]
- getReductionPolynomial(): BigInteger  [API 1+]
- hashCode(): int  [API 1+]
```

## ECFieldFp — declaration; API 1+ (0 +, 2 *, 0 ~, 3 -)

```
* ECFieldFp(BigInteger)  [API 1+]
- equals(Object): boolean  [API 1+]
- getFieldSize(): int  [API 1+]
* getP(): BigInteger  [API 1+]
- hashCode(): int  [API 1+]
```

## ECGenParameterSpec — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- ECGenParameterSpec(String)  [API 1+]
- getName(): String  [API 1+]
```

## ECParameterSpec — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- ECParameterSpec(EllipticCurve, ECPoint, BigInteger, int)  [API 1+]
- getCofactor(): int  [API 1+]
- getCurve(): EllipticCurve  [API 1+]
- getGenerator(): ECPoint  [API 1+]
- getOrder(): BigInteger  [API 1+]
```

## ECPoint — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- ECPoint(BigInteger, BigInteger)  [API 1+]
- POINT_INFINITY [field]  [API 1+]
- equals(Object): boolean  [API 1+]
- getAffineX(): BigInteger  [API 1+]
- getAffineY(): BigInteger  [API 1+]
- hashCode(): int  [API 1+]
```

## ECPrivateKeySpec — declaration; API 1+ (0 +, 3 *, 0 ~, 0 -)

```
* ECPrivateKeySpec(BigInteger, ECParameterSpec)  [API 1+]
* getParams(): ECParameterSpec  [API 1+]
* getS(): BigInteger  [API 1+]
```

## ECPublicKeySpec — declaration; API 1+ (0 +, 3 *, 0 ~, 0 -)

```
* ECPublicKeySpec(ECPoint, ECParameterSpec)  [API 1+]
* getParams(): ECParameterSpec  [API 1+]
* getW(): ECPoint  [API 1+]
```

## EdDSAParameterSpec — declaration; API 35+ (0 +, 2 *, 0 ~, 2 -)

```
* EdDSAParameterSpec(boolean)  [API 35+]
- EdDSAParameterSpec(boolean, byte[])  [API 35+]
- getContext(): Optional  [API 35+]
* isPrehash(): boolean  [API 35+]
```

## EdECPoint — declaration; API 33+ (0 +, 3 *, 0 ~, 0 -)

```
* EdECPoint(boolean, BigInteger)  [API 33+]
* getY(): BigInteger  [API 33+]
* isXOdd(): boolean  [API 33+]
```

## EdECPrivateKeySpec — declaration; API 33+ (0 +, 3 *, 0 ~, 0 -)

```
* EdECPrivateKeySpec(NamedParameterSpec, byte[])  [API 33+]
* getBytes(): byte[]  [API 33+]
* getParams(): NamedParameterSpec  [API 33+]
```

## EdECPublicKeySpec — declaration; API 33+ (0 +, 3 *, 0 ~, 0 -)

```
* EdECPublicKeySpec(NamedParameterSpec, EdECPoint)  [API 33+]
* getParams(): NamedParameterSpec  [API 33+]
* getPoint(): EdECPoint  [API 33+]
```

## EllipticCurve — declaration; API 1+ (0 +, 8 *, 0 ~, 0 -)

```
* EllipticCurve(ECField, BigInteger, BigInteger)  [API 1+]
* EllipticCurve(ECField, BigInteger, BigInteger, byte[])  [API 1+]
* equals(Object): boolean  [API 1+]
* getA(): BigInteger  [API 1+]
* getB(): BigInteger  [API 1+]
* getField(): ECField  [API 1+]
* getSeed(): byte[]  [API 1+]
* hashCode(): int  [API 1+]
```

## EncodedKeySpec — declaration; API 1+ (1 +, 1 *, 0 ~, 3 -)

```
- EncodedKeySpec(byte[])  [API 1+]
- EncodedKeySpec(byte[], String)  [API 35+]
- getAlgorithm(): String  [API 35+]
* getEncoded(): byte[]  [API 1+]
+ getFormat(): String  [API 1+]
```

## InvalidKeySpecException — framework (a2w.jdk.sec.spec.InvalidKeySpecException); API 1+ (2 +, 0 *, 0 ~, 2 -)

```
+ InvalidKeySpecException()  [API 1+]
+ InvalidKeySpecException(String)  [API 1+]
- InvalidKeySpecException(String, Throwable)  [API 1+]
- InvalidKeySpecException(Throwable)  [API 1+]
```

## InvalidParameterSpecException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ InvalidParameterSpecException()  [API 1+]
+ InvalidParameterSpecException(String)  [API 1+]
```

## KeySpec — framework (a2w.jdk.sec.spec.KeySpec); API 1+ (0 +, 0 *, 0 ~, 0 -)

## MGF1ParameterSpec — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- MGF1ParameterSpec(String)  [API 1+]
- SHA1 [field]  [API 1+]
- SHA224 [field]  [API 26+]
- SHA256 [field]  [API 1+]
- SHA384 [field]  [API 1+]
- SHA3_224 [field]  [API 35+]
- SHA3_256 [field]  [API 35+]
- SHA3_384 [field]  [API 35+]
- SHA3_512 [field]  [API 35+]
- SHA512 [field]  [API 1+]
- SHA512_224 [field]  [API 35+]
- SHA512_256 [field]  [API 35+]
- getDigestAlgorithm(): String  [API 1+]
- toString(): String  [API 1+]
```

## NamedParameterSpec — declaration; API 33+ (0 +, 0 *, 0 ~, 2 -)

```
- ED25519 [field]  [API 33+]
- ED448 [field]  [API 33+]
- NamedParameterSpec(String)  [API 33+]
- X25519 [field]  [API 33+]
- X448 [field]  [API 33+]
- getName(): String  [API 33+]
```

## PKCS8EncodedKeySpec — declaration; API 1+ (0 +, 1 *, 0 ~, 3 -)

```
- PKCS8EncodedKeySpec(byte[])  [API 1+]
- PKCS8EncodedKeySpec(byte[], String)  [API 35+]
* getEncoded(): byte[]  [API 1+]
- getFormat(): String  [API 1+]
```

## PSSParameterSpec — declaration; API 1+ (0 +, 0 *, 0 ~, 8 -)

```
- DEFAULT [field]  [API 1+]
- PSSParameterSpec(String, String, AlgorithmParameterSpec, int, int)  [API 1+]
- PSSParameterSpec(int)  [API 1+]
- getDigestAlgorithm(): String  [API 1+]
- getMGFAlgorithm(): String  [API 1+]
- getMGFParameters(): AlgorithmParameterSpec  [API 1+]
- getSaltLength(): int  [API 1+]
- getTrailerField(): int  [API 1+]
- toString(): String  [API 1+]
```

## RSAKeyGenParameterSpec — declaration; API 1+ (0 +, 5 *, 0 ~, 0 -)

```
- F0 [field]  [API 1+]
- F4 [field]  [API 1+]
* RSAKeyGenParameterSpec(int, BigInteger)  [API 1+]
* RSAKeyGenParameterSpec(int, BigInteger, AlgorithmParameterSpec)  [API 35+]
* getKeyParams(): AlgorithmParameterSpec  [API 35+]
* getKeysize(): int  [API 1+]
* getPublicExponent(): BigInteger  [API 1+]
```

## RSAMultiPrimePrivateCrtKeySpec — declaration; API 1+ (0 +, 8 *, 0 ~, 1 -)

```
* RSAMultiPrimePrivateCrtKeySpec(BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, RSAOtherPrimeInfo[])  [API 1+]
- RSAMultiPrimePrivateCrtKeySpec(BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, RSAOtherPrimeInfo[], AlgorithmParameterSpec)  [API 35+]
* getCrtCoefficient(): BigInteger  [API 1+]
* getOtherPrimeInfo(): RSAOtherPrimeInfo[]  [API 1+]
* getPrimeExponentP(): BigInteger  [API 1+]
* getPrimeExponentQ(): BigInteger  [API 1+]
* getPrimeP(): BigInteger  [API 1+]
* getPrimeQ(): BigInteger  [API 1+]
* getPublicExponent(): BigInteger  [API 1+]
```

## RSAOtherPrimeInfo — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- RSAOtherPrimeInfo(BigInteger, BigInteger, BigInteger)  [API 1+]
- getCrtCoefficient(): BigInteger  [API 1+]
- getExponent(): BigInteger  [API 1+]
- getPrime(): BigInteger  [API 1+]
```

## RSAPrivateCrtKeySpec — declaration; API 1+ (0 +, 7 *, 0 ~, 1 -)

```
* RSAPrivateCrtKeySpec(BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger)  [API 1+]
- RSAPrivateCrtKeySpec(BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, AlgorithmParameterSpec)  [API 35+]
* getCrtCoefficient(): BigInteger  [API 1+]
* getPrimeExponentP(): BigInteger  [API 1+]
* getPrimeExponentQ(): BigInteger  [API 1+]
* getPrimeP(): BigInteger  [API 1+]
* getPrimeQ(): BigInteger  [API 1+]
* getPublicExponent(): BigInteger  [API 1+]
```

## RSAPrivateKeySpec — declaration; API 1+ (0 +, 5 *, 0 ~, 0 -)

```
* RSAPrivateKeySpec(BigInteger, BigInteger)  [API 1+]
* RSAPrivateKeySpec(BigInteger, BigInteger, AlgorithmParameterSpec)  [API 35+]
* getModulus(): BigInteger  [API 1+]
* getParams(): AlgorithmParameterSpec  [API 35+]
* getPrivateExponent(): BigInteger  [API 1+]
```

## RSAPublicKeySpec — declaration; API 1+ (0 +, 5 *, 0 ~, 0 -)

```
* RSAPublicKeySpec(BigInteger, BigInteger)  [API 1+]
* RSAPublicKeySpec(BigInteger, BigInteger, AlgorithmParameterSpec)  [API 35+]
* getModulus(): BigInteger  [API 1+]
* getParams(): AlgorithmParameterSpec  [API 35+]
* getPublicExponent(): BigInteger  [API 1+]
```

## X509EncodedKeySpec — declaration; API 1+ (0 +, 1 *, 0 ~, 3 -)

```
- X509EncodedKeySpec(byte[])  [API 1+]
- X509EncodedKeySpec(byte[], String)  [API 35+]
* getEncoded(): byte[]  [API 1+]
- getFormat(): String  [API 1+]
```

## XECPrivateKeySpec — declaration; API 33+ (0 +, 3 *, 0 ~, 0 -)

```
* XECPrivateKeySpec(AlgorithmParameterSpec, byte[])  [API 33+]
* getParams(): AlgorithmParameterSpec  [API 33+]
* getScalar(): byte[]  [API 33+]
```

## XECPublicKeySpec — declaration; API 33+ (0 +, 3 *, 0 ~, 0 -)

```
* XECPublicKeySpec(AlgorithmParameterSpec, BigInteger)  [API 33+]
* getParams(): AlgorithmParameterSpec  [API 33+]
* getU(): BigInteger  [API 33+]
```


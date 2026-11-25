# java.security.interfaces

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DSAKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getParams(): DSAParams  [API 1+]
```

## DSAKeyPairGenerator — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ initialize(DSAParams, SecureRandom): void  [API 1+]
+ initialize(int, boolean, SecureRandom): void  [API 1+]
```

## DSAParams — declaration; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ getG(): BigInteger  [API 1+]
+ getP(): BigInteger  [API 1+]
+ getQ(): BigInteger  [API 1+]
```

## DSAPrivateKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getX(): BigInteger  [API 1+]
```

## DSAPublicKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getY(): BigInteger  [API 1+]
```

## ECKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getParams(): ECParameterSpec  [API 1+]
```

## ECPrivateKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getS(): BigInteger  [API 1+]
```

## ECPublicKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getW(): ECPoint  [API 1+]
```

## EdECKey — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ getParams(): NamedParameterSpec  [API 33+]
```

## EdECPrivateKey — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ getBytes(): Optional  [API 33+]
```

## EdECPublicKey — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ getPoint(): EdECPoint  [API 33+]
```

## RSAKey — declaration; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
+ getModulus(): BigInteger  [API 1+]
- getParams(): AlgorithmParameterSpec  [API 35+]
```

## RSAMultiPrimePrivateCrtKey — declaration; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ getCrtCoefficient(): BigInteger  [API 1+]
+ getOtherPrimeInfo(): RSAOtherPrimeInfo[]  [API 1+]
+ getPrimeExponentP(): BigInteger  [API 1+]
+ getPrimeExponentQ(): BigInteger  [API 1+]
+ getPrimeP(): BigInteger  [API 1+]
+ getPrimeQ(): BigInteger  [API 1+]
+ getPublicExponent(): BigInteger  [API 1+]
```

## RSAPrivateCrtKey — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ getCrtCoefficient(): BigInteger  [API 1+]
+ getPrimeExponentP(): BigInteger  [API 1+]
+ getPrimeExponentQ(): BigInteger  [API 1+]
+ getPrimeP(): BigInteger  [API 1+]
+ getPrimeQ(): BigInteger  [API 1+]
+ getPublicExponent(): BigInteger  [API 1+]
```

## RSAPrivateKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getPrivateExponent(): BigInteger  [API 1+]
```

## RSAPublicKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getPublicExponent(): BigInteger  [API 1+]
```

## XECKey — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ getParams(): AlgorithmParameterSpec  [API 33+]
```

## XECPrivateKey — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ getScalar(): Optional  [API 33+]
```

## XECPublicKey — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ getU(): BigInteger  [API 33+]
```


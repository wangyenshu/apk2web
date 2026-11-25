# javax.security.cert

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Certificate — declaration; API 1+ (5 +, 0 *, 0 ~, 3 -)

```
- Certificate()  [API 1+]
- equals(Object): boolean  [API 1+]
+ getEncoded(): byte[]  [API 1+]
+ getPublicKey(): PublicKey  [API 1+]
- hashCode(): int  [API 1+]
+ toString(): String  [API 1+]
+ verify(PublicKey): void  [API 1+]
+ verify(PublicKey, String): void  [API 1+]
```

## CertificateEncodingException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CertificateEncodingException()  [API 1+]
+ CertificateEncodingException(String)  [API 1+]
```

## CertificateException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CertificateException()  [API 1+]
+ CertificateException(String)  [API 1+]
```

## CertificateExpiredException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CertificateExpiredException()  [API 1+]
+ CertificateExpiredException(String)  [API 1+]
```

## CertificateNotYetValidException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CertificateNotYetValidException()  [API 1+]
+ CertificateNotYetValidException(String)  [API 1+]
```

## CertificateParsingException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CertificateParsingException()  [API 1+]
+ CertificateParsingException(String)  [API 1+]
```

## X509Certificate — declaration; API 1+ (11 +, 0 *, 0 ~, 3 -)

```
- X509Certificate()  [API 1+]
+ checkValidity(): void  [API 1+]
+ checkValidity(Date): void  [API 1+]
- getInstance(InputStream): X509Certificate [static]  [API 1+]
- getInstance(byte[]): X509Certificate [static]  [API 1+]
+ getIssuerDN(): Principal  [API 1+]
+ getNotAfter(): Date  [API 1+]
+ getNotBefore(): Date  [API 1+]
+ getSerialNumber(): BigInteger  [API 1+]
+ getSigAlgName(): String  [API 1+]
+ getSigAlgOID(): String  [API 1+]
+ getSigAlgParams(): byte[]  [API 1+]
+ getSubjectDN(): Principal  [API 1+]
+ getVersion(): int  [API 1+]
```


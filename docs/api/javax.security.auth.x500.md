# javax.security.auth.x500

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## X500Principal — declaration; API 1+ (0 +, 2 *, 0 ~, 9 -)

```
- X500Principal(InputStream)  [API 1+]
* X500Principal(String)  [API 1+]
- X500Principal(String, Map)  [API 9+]
- X500Principal(byte[])  [API 1+]
- equals(Object): boolean  [API 1+]
- getEncoded(): byte[]  [API 1+]
* getName(): String  [API 1+]
- getName(String): String  [API 1+]
- getName(String, Map): String  [API 9+]
- hashCode(): int  [API 1+]
- toString(): String  [API 1+]
```

## X500PrivateCredential — declaration; API 35+ (0 +, 0 *, 0 ~, 7 -)

```
- X500PrivateCredential(X509Certificate, PrivateKey)  [API 35+]
- X500PrivateCredential(X509Certificate, PrivateKey, String)  [API 35+]
- destroy(): void  [API 35+]
- getAlias(): String  [API 35+]
- getCertificate(): X509Certificate  [API 35+]
- getPrivateKey(): PrivateKey  [API 35+]
- isDestroyed(): boolean  [API 35+]
```


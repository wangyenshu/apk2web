# org.apache.http.conn.ssl

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractVerifier — bundled library; API 1+, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ AbstractVerifier()  [API 1+]
+ acceptableCountryWildcard(String): boolean [static]  [API 1+]
+ countDots(String): int [static]  [API 1+]
+ getCNs(X509Certificate): String[] [static]  [API 1+]
+ getDNSSubjectAlts(X509Certificate): String[] [static]  [API 1+]
+ verify(String, SSLSession): boolean  [API 1+]
+ verify(String, SSLSocket): void  [API 1+]
+ verify(String, String[], String[], boolean): void  [API 1+]
+ verify(String, X509Certificate): void  [API 1+]
```

## AllowAllHostnameVerifier — bundled library; API 1+, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ AllowAllHostnameVerifier()  [API 1+]
+ toString(): String  [API 1+]
+ verify(String, String[], String[]): void  [API 1+]
```

## BrowserCompatHostnameVerifier — bundled library; API 1+, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ BrowserCompatHostnameVerifier()  [API 1+]
+ toString(): String  [API 1+]
+ verify(String, String[], String[]): void  [API 1+]
```

## SSLSocketFactory — bundled library; API 1+, deprecated in 22 (11 +, 0 *, 0 ~, 0 -)

```
+ ALLOW_ALL_HOSTNAME_VERIFIER [field]  [API 1+]
+ BROWSER_COMPATIBLE_HOSTNAME_VERIFIER [field]  [API 1+]
+ SSLSocketFactory(KeyStore)  [API 1+]
+ SSLSocketFactory(KeyStore, String)  [API 1+]
+ SSLSocketFactory(KeyStore, String, KeyStore)  [API 1+]
+ SSLSocketFactory(String, KeyStore, String, KeyStore, SecureRandom, HostNameResolver)  [API 1+]
+ STRICT_HOSTNAME_VERIFIER [field]  [API 1+]
+ connectSocket(Socket, String, int, InetAddress, int, HttpParams): Socket  [API 1+]
+ createSocket(): Socket  [API 1+]
+ createSocket(Socket, String, int, boolean): Socket  [API 1+]
+ getHostnameVerifier(): X509HostnameVerifier  [API 1+]
+ getSocketFactory(): SSLSocketFactory [static]  [API 1+]
+ isSecure(Socket): boolean  [API 1+]
+ setHostnameVerifier(X509HostnameVerifier): void  [API 1+]
```

## StrictHostnameVerifier — bundled library; API 1+, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ StrictHostnameVerifier()  [API 1+]
+ toString(): String  [API 1+]
+ verify(String, String[], String[]): void  [API 1+]
```

## X509HostnameVerifier — bundled library; API 1+, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ verify(String, SSLSession): boolean  [API 1+]
+ verify(String, SSLSocket): void  [API 1+]
+ verify(String, String[], String[]): void  [API 1+]
+ verify(String, X509Certificate): void  [API 1+]
```


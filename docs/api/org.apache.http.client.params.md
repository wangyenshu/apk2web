# org.apache.http.client.params

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AllClientPNames — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## AuthPolicy — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## ClientPNames — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## ClientParamBean — bundled library; API 1-22, removed in 23, deprecated in 22 (12 +, 0 *, 0 ~, 0 -)

```
+ ClientParamBean(HttpParams)  [API 1-22, removed in 23]
+ setAllowCircularRedirects(boolean): void  [API 1-22, removed in 23]
+ setConnectionManagerFactory(ClientConnectionManagerFactory): void  [API 1-22, removed in 23]
+ setConnectionManagerFactoryClassName(String): void  [API 1-22, removed in 23]
+ setCookiePolicy(String): void  [API 1-22, removed in 23]
+ setDefaultHeaders(Collection): void  [API 1-22, removed in 23]
+ setDefaultHost(HttpHost): void  [API 1-22, removed in 23]
+ setHandleAuthentication(boolean): void  [API 1-22, removed in 23]
+ setHandleRedirects(boolean): void  [API 1-22, removed in 23]
+ setMaxRedirects(int): void  [API 1-22, removed in 23]
+ setRejectRelativeRedirect(boolean): void  [API 1-22, removed in 23]
+ setVirtualHost(HttpHost): void  [API 1-22, removed in 23]
```

## CookiePolicy — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## HttpClientParams — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ getCookiePolicy(HttpParams): String  [API 1-22, removed in 23]
+ isAuthenticating(HttpParams): boolean  [API 1-22, removed in 23]
+ isRedirecting(HttpParams): boolean  [API 1-22, removed in 23]
+ setAuthenticating(HttpParams, boolean): void  [API 1-22, removed in 23]
+ setCookiePolicy(HttpParams, String): void  [API 1-22, removed in 23]
+ setRedirecting(HttpParams, boolean): void  [API 1-22, removed in 23]
```


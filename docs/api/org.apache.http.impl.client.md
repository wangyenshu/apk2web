# org.apache.http.impl.client

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractAuthenticationHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ AbstractAuthenticationHandler()  [API 1-22, removed in 23]
+ getAuthPreferences(): List  [API 1-22, removed in 23]
+ parseChallenges(Header[]): Map  [API 1-22, removed in 23]
```

## AbstractHttpClient — bundled library; API 1-22, removed in 23, deprecated in 22 (59 +, 0 *, 0 ~, 0 -)

```
+ AbstractHttpClient(ClientConnectionManager, HttpParams)  [API 1-22, removed in 23]
+ addRequestInterceptor(HttpRequestInterceptor): void  [API 1-22, removed in 23]
+ addRequestInterceptor(HttpRequestInterceptor, int): void  [API 1-22, removed in 23]
+ addResponseInterceptor(HttpResponseInterceptor): void  [API 1-22, removed in 23]
+ addResponseInterceptor(HttpResponseInterceptor, int): void  [API 1-22, removed in 23]
+ clearRequestInterceptors(): void  [API 1-22, removed in 23]
+ clearResponseInterceptors(): void  [API 1-22, removed in 23]
+ createAuthSchemeRegistry(): AuthSchemeRegistry  [API 1-22, removed in 23]
+ createClientConnectionManager(): ClientConnectionManager  [API 1-22, removed in 23]
+ createClientRequestDirector(HttpRequestExecutor, ClientConnectionManager, ConnectionReuseStrategy, ConnectionKeepAliveStrategy, HttpRoutePlanner, HttpProcessor, HttpRequestRetryHandler, RedirectHandler, AuthenticationHandler, AuthenticationHandler, UserTokenHandler, HttpParams): RequestDirector  [API 1-22, removed in 23]
+ createConnectionKeepAliveStrategy(): ConnectionKeepAliveStrategy  [API 1-22, removed in 23]
+ createConnectionReuseStrategy(): ConnectionReuseStrategy  [API 1-22, removed in 23]
+ createCookieSpecRegistry(): CookieSpecRegistry  [API 1-22, removed in 23]
+ createCookieStore(): CookieStore  [API 1-22, removed in 23]
+ createCredentialsProvider(): CredentialsProvider  [API 1-22, removed in 23]
+ createHttpContext(): HttpContext  [API 1-22, removed in 23]
+ createHttpParams(): HttpParams  [API 1-22, removed in 23]
+ createHttpProcessor(): BasicHttpProcessor  [API 1-22, removed in 23]
+ createHttpRequestRetryHandler(): HttpRequestRetryHandler  [API 1-22, removed in 23]
+ createHttpRoutePlanner(): HttpRoutePlanner  [API 1-22, removed in 23]
+ createProxyAuthenticationHandler(): AuthenticationHandler  [API 1-22, removed in 23]
+ createRedirectHandler(): RedirectHandler  [API 1-22, removed in 23]
+ createRequestExecutor(): HttpRequestExecutor  [API 1-22, removed in 23]
+ createTargetAuthenticationHandler(): AuthenticationHandler  [API 1-22, removed in 23]
+ createUserTokenHandler(): UserTokenHandler  [API 1-22, removed in 23]
+ determineParams(HttpRequest): HttpParams  [API 1-22, removed in 23]
+ getAuthSchemes(): AuthSchemeRegistry  [API 1-22, removed in 23]
+ getConnectionKeepAliveStrategy(): ConnectionKeepAliveStrategy  [API 1-22, removed in 23]
+ getConnectionReuseStrategy(): ConnectionReuseStrategy  [API 1-22, removed in 23]
+ getCookieSpecs(): CookieSpecRegistry  [API 1-22, removed in 23]
+ getCookieStore(): CookieStore  [API 1-22, removed in 23]
+ getCredentialsProvider(): CredentialsProvider  [API 1-22, removed in 23]
+ getHttpProcessor(): BasicHttpProcessor  [API 1-22, removed in 23]
+ getHttpRequestRetryHandler(): HttpRequestRetryHandler  [API 1-22, removed in 23]
+ getProxyAuthenticationHandler(): AuthenticationHandler  [API 1-22, removed in 23]
+ getRedirectHandler(): RedirectHandler  [API 1-22, removed in 23]
+ getRequestExecutor(): HttpRequestExecutor  [API 1-22, removed in 23]
+ getRequestInterceptor(int): HttpRequestInterceptor  [API 1-22, removed in 23]
+ getRequestInterceptorCount(): int  [API 1-22, removed in 23]
+ getResponseInterceptor(int): HttpResponseInterceptor  [API 1-22, removed in 23]
+ getResponseInterceptorCount(): int  [API 1-22, removed in 23]
+ getRoutePlanner(): HttpRoutePlanner  [API 1-22, removed in 23]
+ getTargetAuthenticationHandler(): AuthenticationHandler  [API 1-22, removed in 23]
+ getUserTokenHandler(): UserTokenHandler  [API 1-22, removed in 23]
+ removeRequestInterceptorByClass(Class): void  [API 1-22, removed in 23]
+ removeResponseInterceptorByClass(Class): void  [API 1-22, removed in 23]
+ setAuthSchemes(AuthSchemeRegistry): void  [API 1-22, removed in 23]
+ setCookieSpecs(CookieSpecRegistry): void  [API 1-22, removed in 23]
+ setCookieStore(CookieStore): void  [API 1-22, removed in 23]
+ setCredentialsProvider(CredentialsProvider): void  [API 1-22, removed in 23]
+ setHttpRequestRetryHandler(HttpRequestRetryHandler): void  [API 1-22, removed in 23]
+ setKeepAliveStrategy(ConnectionKeepAliveStrategy): void  [API 1-22, removed in 23]
+ setParams(HttpParams): void  [API 1-22, removed in 23]
+ setProxyAuthenticationHandler(AuthenticationHandler): void  [API 1-22, removed in 23]
+ setRedirectHandler(RedirectHandler): void  [API 1-22, removed in 23]
+ setReuseStrategy(ConnectionReuseStrategy): void  [API 1-22, removed in 23]
+ setRoutePlanner(HttpRoutePlanner): void  [API 1-22, removed in 23]
+ setTargetAuthenticationHandler(AuthenticationHandler): void  [API 1-22, removed in 23]
+ setUserTokenHandler(UserTokenHandler): void  [API 1-22, removed in 23]
```

## BasicCookieStore — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicCookieStore()  [API 1-22, removed in 23]
+ addCookies(Cookie[]): void  [API 1-22, removed in 23]
```

## BasicCredentialsProvider — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicCredentialsProvider()  [API 1-22, removed in 23]
```

## BasicResponseHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicResponseHandler()  [API 1-22, removed in 23]
+ handleResponse(HttpResponse): String  [API 1-22, removed in 23]
```

## ClientParamsStack — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ ClientParamsStack(ClientParamsStack)  [API 1-22, removed in 23]
+ ClientParamsStack(ClientParamsStack, HttpParams, HttpParams, HttpParams, HttpParams)  [API 1-22, removed in 23]
+ ClientParamsStack(HttpParams, HttpParams, HttpParams, HttpParams)  [API 1-22, removed in 23]
+ applicationParams [field]  [API 1-22, removed in 23]
+ clientParams [field]  [API 1-22, removed in 23]
+ getApplicationParams(): HttpParams  [API 1-22, removed in 23]
+ getClientParams(): HttpParams  [API 1-22, removed in 23]
+ getOverrideParams(): HttpParams  [API 1-22, removed in 23]
+ getRequestParams(): HttpParams  [API 1-22, removed in 23]
+ overrideParams [field]  [API 1-22, removed in 23]
+ requestParams [field]  [API 1-22, removed in 23]
```

## DefaultConnectionKeepAliveStrategy — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultConnectionKeepAliveStrategy()  [API 1-22, removed in 23]
```

## DefaultHttpClient — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ DefaultHttpClient()  [API 1-22, removed in 23]
+ DefaultHttpClient(ClientConnectionManager, HttpParams)  [API 1-22, removed in 23]
+ DefaultHttpClient(HttpParams)  [API 1-22, removed in 23]
```

## DefaultHttpRequestRetryHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ DefaultHttpRequestRetryHandler()  [API 1-22, removed in 23]
+ DefaultHttpRequestRetryHandler(int, boolean)  [API 1-22, removed in 23]
+ getRetryCount(): int  [API 1-22, removed in 23]
+ isRequestSentRetryEnabled(): boolean  [API 1-22, removed in 23]
```

## DefaultProxyAuthenticationHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultProxyAuthenticationHandler()  [API 1-22, removed in 23]
```

## DefaultRedirectHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultRedirectHandler()  [API 1-22, removed in 23]
```

## DefaultRequestDirector — framework; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 1 ~, 7 -)

```
+ DefaultRequestDirector(HttpRequestExecutor, ClientConnectionManager, ConnectionReuseStrategy, ConnectionKeepAliveStrategy, HttpRoutePlanner, HttpProcessor, HttpRequestRetryHandler, RedirectHandler, AuthenticationHandler, AuthenticationHandler, UserTokenHandler, HttpParams)  [API 1-22, removed in 23]
+ connManager [field]  [API 1-22, removed in 23]
- createConnectRequest(HttpRoute, HttpContext): HttpRequest  [API 1-22, removed in 23]
- createTunnelToProxy(HttpRoute, int, HttpContext): boolean  [API 1-22, removed in 23]
- createTunnelToTarget(HttpRoute, HttpContext): boolean  [API 1-22, removed in 23]
- determineRoute(HttpHost, HttpRequest, HttpContext): HttpRoute  [API 1-22, removed in 23]
- establishRoute(HttpRoute, HttpContext): void  [API 1-22, removed in 23]
- handleResponse(RoutedRequest, HttpResponse, HttpContext): RoutedRequest  [API 1-22, removed in 23]
+ httpProcessor [field]  [API 1-22, removed in 23]
+ keepAliveStrategy [field]  [API 1-22, removed in 23]
- managedConn [field]  [API 1-22, removed in 23]
+ params [field]  [API 1-22, removed in 23]
+ redirectHandler [field]  [API 1-22, removed in 23]
~ releaseConnection(): void  [API 1-22, removed in 23]
+ requestExec [field]  [API 1-22, removed in 23]
+ retryHandler [field]  [API 1-22, removed in 23]
+ reuseStrategy [field]  [API 1-22, removed in 23]
- rewriteRequestURI(RequestWrapper, HttpRoute): void  [API 1-22, removed in 23]
+ routePlanner [field]  [API 1-22, removed in 23]
```

## DefaultTargetAuthenticationHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultTargetAuthenticationHandler()  [API 1-22, removed in 23]
```

## DefaultUserTokenHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultUserTokenHandler()  [API 1-22, removed in 23]
```

## EntityEnclosingRequestWrapper — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ EntityEnclosingRequestWrapper(HttpEntityEnclosingRequest)  [API 1-22, removed in 23]
```

## RedirectLocations — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ RedirectLocations()  [API 1-22, removed in 23]
+ add(URI): void  [API 1-22, removed in 23]
+ contains(URI): boolean  [API 1-22, removed in 23]
+ remove(URI): boolean  [API 1-22, removed in 23]
```

## RequestWrapper — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ RequestWrapper(HttpRequest)  [API 1-22, removed in 23]
+ getExecCount(): int  [API 1-22, removed in 23]
+ getOriginal(): HttpRequest  [API 1-22, removed in 23]
+ incrementExecCount(): void  [API 1-22, removed in 23]
+ isRepeatable(): boolean  [API 1-22, removed in 23]
+ resetHeaders(): void  [API 1-22, removed in 23]
+ setMethod(String): void  [API 1-22, removed in 23]
+ setProtocolVersion(ProtocolVersion): void  [API 1-22, removed in 23]
+ setURI(URI): void  [API 1-22, removed in 23]
```

## RoutedRequest — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ RoutedRequest(RequestWrapper, HttpRoute)  [API 1-22, removed in 23]
+ getRequest(): RequestWrapper  [API 1-22, removed in 23]
+ getRoute(): HttpRoute  [API 1-22, removed in 23]
+ request [field]  [API 1-22, removed in 23]
+ route [field]  [API 1-22, removed in 23]
```

## TunnelRefusedException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ TunnelRefusedException(String, HttpResponse)  [API 1-22, removed in 23]
+ getResponse(): HttpResponse  [API 1-22, removed in 23]
```


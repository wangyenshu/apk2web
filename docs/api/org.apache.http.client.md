# org.apache.http.client

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AuthenticationHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ getChallenges(HttpResponse, HttpContext): Map  [API 1-22, removed in 23]
+ isAuthenticationRequested(HttpResponse, HttpContext): boolean  [API 1-22, removed in 23]
+ selectScheme(Map, HttpResponse, HttpContext): AuthScheme  [API 1-22, removed in 23]
```

## CircularRedirectException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ CircularRedirectException()  [API 1-22, removed in 23]
+ CircularRedirectException(String)  [API 1-22, removed in 23]
+ CircularRedirectException(String, Throwable)  [API 1-22, removed in 23]
```

## ClientProtocolException — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ ClientProtocolException()  [API 1-22, removed in 23]
+ ClientProtocolException(String)  [API 1-22, removed in 23]
+ ClientProtocolException(String, Throwable)  [API 1-22, removed in 23]
+ ClientProtocolException(Throwable)  [API 1-22, removed in 23]
```

## CookieStore — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ addCookie(Cookie): void  [API 1-22, removed in 23]
+ clear(): void  [API 1-22, removed in 23]
+ clearExpired(Date): boolean  [API 1-22, removed in 23]
+ getCookies(): List  [API 1-22, removed in 23]
```

## CredentialsProvider — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ clear(): void  [API 1-22, removed in 23]
+ getCredentials(AuthScope): Credentials  [API 1-22, removed in 23]
+ setCredentials(AuthScope, Credentials): void  [API 1-22, removed in 23]
```

## HttpClient — bundled library; API 1-22, removed in 23, deprecated in 22 (10 +, 0 *, 0 ~, 0 -)

```
+ execute(HttpHost, HttpRequest): HttpResponse  [API 1-22, removed in 23]
+ execute(HttpHost, HttpRequest, HttpContext): HttpResponse  [API 1-22, removed in 23]
+ execute(HttpHost, HttpRequest, ResponseHandler): Object  [API 1-22, removed in 23]
+ execute(HttpHost, HttpRequest, ResponseHandler, HttpContext): Object  [API 1-22, removed in 23]
+ execute(HttpUriRequest): HttpResponse  [API 1-22, removed in 23]
+ execute(HttpUriRequest, HttpContext): HttpResponse  [API 1-22, removed in 23]
+ execute(HttpUriRequest, ResponseHandler): Object  [API 1-22, removed in 23]
+ execute(HttpUriRequest, ResponseHandler, HttpContext): Object  [API 1-22, removed in 23]
+ getConnectionManager(): ClientConnectionManager  [API 1-22, removed in 23]
+ getParams(): HttpParams  [API 1-22, removed in 23]
```

## HttpRequestRetryHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ retryRequest(IOException, int, HttpContext): boolean  [API 1-22, removed in 23]
```

## HttpResponseException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ HttpResponseException(int, String)  [API 1-22, removed in 23]
+ getStatusCode(): int  [API 1-22, removed in 23]
```

## NonRepeatableRequestException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ NonRepeatableRequestException()  [API 1-22, removed in 23]
+ NonRepeatableRequestException(String)  [API 1-22, removed in 23]
```

## RedirectException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ RedirectException()  [API 1-22, removed in 23]
+ RedirectException(String)  [API 1-22, removed in 23]
+ RedirectException(String, Throwable)  [API 1-22, removed in 23]
```

## RedirectHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ getLocationURI(HttpResponse, HttpContext): URI  [API 1-22, removed in 23]
+ isRedirectRequested(HttpResponse, HttpContext): boolean  [API 1-22, removed in 23]
```

## RequestDirector — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ execute(HttpHost, HttpRequest, HttpContext): HttpResponse  [API 1-22, removed in 23]
```

## ResponseHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ handleResponse(HttpResponse): Object  [API 1-22, removed in 23]
```

## UserTokenHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ getUserToken(HttpContext): Object  [API 1-22, removed in 23]
```


# org.apache.http.auth

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AUTH — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## AuthScheme — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ authenticate(Credentials, HttpRequest): Header  [API 1-22, removed in 23]
+ getParameter(String): String  [API 1-22, removed in 23]
+ getRealm(): String  [API 1-22, removed in 23]
+ getSchemeName(): String  [API 1-22, removed in 23]
+ isComplete(): boolean  [API 1-22, removed in 23]
+ isConnectionBased(): boolean  [API 1-22, removed in 23]
+ processChallenge(Header): void  [API 1-22, removed in 23]
```

## AuthSchemeFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ newInstance(HttpParams): AuthScheme  [API 1-22, removed in 23]
```

## AuthSchemeRegistry — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ AuthSchemeRegistry()  [API 1-22, removed in 23]
+ getAuthScheme(String, HttpParams): AuthScheme  [API 1-22, removed in 23]
+ getSchemeNames(): List  [API 1-22, removed in 23]
+ register(String, AuthSchemeFactory): void  [API 1-22, removed in 23]
+ setItems(Map): void  [API 1-22, removed in 23]
+ unregister(String): void  [API 1-22, removed in 23]
```

## AuthScope — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ AuthScope(AuthScope)  [API 1-22, removed in 23]
+ AuthScope(String, int)  [API 1-22, removed in 23]
+ AuthScope(String, int, String)  [API 1-22, removed in 23]
+ AuthScope(String, int, String, String)  [API 1-22, removed in 23]
+ getHost(): String  [API 1-22, removed in 23]
+ getPort(): int  [API 1-22, removed in 23]
+ getRealm(): String  [API 1-22, removed in 23]
+ getScheme(): String  [API 1-22, removed in 23]
+ match(AuthScope): int  [API 1-22, removed in 23]
```

## AuthState — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ AuthState()  [API 1-22, removed in 23]
+ getAuthScheme(): AuthScheme  [API 1-22, removed in 23]
+ getAuthScope(): AuthScope  [API 1-22, removed in 23]
+ getCredentials(): Credentials  [API 1-22, removed in 23]
+ invalidate(): void  [API 1-22, removed in 23]
+ isValid(): boolean  [API 1-22, removed in 23]
+ setAuthScheme(AuthScheme): void  [API 1-22, removed in 23]
+ setAuthScope(AuthScope): void  [API 1-22, removed in 23]
+ setCredentials(Credentials): void  [API 1-22, removed in 23]
```

## AuthenticationException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ AuthenticationException()  [API 1-22, removed in 23]
+ AuthenticationException(String)  [API 1-22, removed in 23]
+ AuthenticationException(String, Throwable)  [API 1-22, removed in 23]
```

## BasicUserPrincipal — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicUserPrincipal(String)  [API 1-22, removed in 23]
```

## Credentials — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ getPassword(): String  [API 1-22, removed in 23]
+ getUserPrincipal(): Principal  [API 1-22, removed in 23]
```

## InvalidCredentialsException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ InvalidCredentialsException()  [API 1-22, removed in 23]
+ InvalidCredentialsException(String)  [API 1-22, removed in 23]
+ InvalidCredentialsException(String, Throwable)  [API 1-22, removed in 23]
```

## MalformedChallengeException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ MalformedChallengeException()  [API 1-22, removed in 23]
+ MalformedChallengeException(String)  [API 1-22, removed in 23]
+ MalformedChallengeException(String, Throwable)  [API 1-22, removed in 23]
```

## NTCredentials — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ NTCredentials(String)  [API 1-22, removed in 23]
+ NTCredentials(String, String, String, String)  [API 1-22, removed in 23]
+ getDomain(): String  [API 1-22, removed in 23]
+ getUserName(): String  [API 1-22, removed in 23]
+ getWorkstation(): String  [API 1-22, removed in 23]
```

## NTUserPrincipal — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ NTUserPrincipal(String, String)  [API 1-22, removed in 23]
+ getDomain(): String  [API 1-22, removed in 23]
+ getUsername(): String  [API 1-22, removed in 23]
```

## UsernamePasswordCredentials — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ UsernamePasswordCredentials(String)  [API 1-22, removed in 23]
+ UsernamePasswordCredentials(String, String)  [API 1-22, removed in 23]
+ getUserName(): String  [API 1-22, removed in 23]
```


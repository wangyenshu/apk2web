# org.apache.http.cookie

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ClientCookie — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ containsAttribute(String): boolean  [API 1-22, removed in 23]
+ getAttribute(String): String  [API 1-22, removed in 23]
```

## Cookie — bundled library; API 1-22, removed in 23, deprecated in 22 (12 +, 0 *, 0 ~, 0 -)

```
+ getComment(): String  [API 1-22, removed in 23]
+ getCommentURL(): String  [API 1-22, removed in 23]
+ getDomain(): String  [API 1-22, removed in 23]
+ getExpiryDate(): Date  [API 1-22, removed in 23]
+ getName(): String  [API 1-22, removed in 23]
+ getPath(): String  [API 1-22, removed in 23]
+ getPorts(): int[]  [API 1-22, removed in 23]
+ getValue(): String  [API 1-22, removed in 23]
+ getVersion(): int  [API 1-22, removed in 23]
+ isExpired(Date): boolean  [API 1-22, removed in 23]
+ isPersistent(): boolean  [API 1-22, removed in 23]
+ isSecure(): boolean  [API 1-22, removed in 23]
```

## CookieAttributeHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ match(Cookie, CookieOrigin): boolean  [API 1-22, removed in 23]
+ parse(SetCookie, String): void  [API 1-22, removed in 23]
+ validate(Cookie, CookieOrigin): void  [API 1-22, removed in 23]
```

## CookieIdentityComparator — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ CookieIdentityComparator()  [API 1-22, removed in 23]
+ compare(Cookie, Cookie): int  [API 1-22, removed in 23]
```

## CookieOrigin — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ CookieOrigin(String, int, String, boolean)  [API 1-22, removed in 23]
+ getHost(): String  [API 1-22, removed in 23]
+ getPath(): String  [API 1-22, removed in 23]
+ getPort(): int  [API 1-22, removed in 23]
+ isSecure(): boolean  [API 1-22, removed in 23]
```

## CookiePathComparator — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ CookiePathComparator()  [API 1-22, removed in 23]
+ compare(Cookie, Cookie): int  [API 1-22, removed in 23]
```

## CookieSpec — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ formatCookies(List): List  [API 1-22, removed in 23]
+ getVersion(): int  [API 1-22, removed in 23]
+ getVersionHeader(): Header  [API 1-22, removed in 23]
+ match(Cookie, CookieOrigin): boolean  [API 1-22, removed in 23]
+ parse(Header, CookieOrigin): List  [API 1-22, removed in 23]
+ validate(Cookie, CookieOrigin): void  [API 1-22, removed in 23]
```

## CookieSpecFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ newInstance(HttpParams): CookieSpec  [API 1-22, removed in 23]
```

## CookieSpecRegistry — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ CookieSpecRegistry()  [API 1-22, removed in 23]
+ getCookieSpec(String): CookieSpec  [API 1-22, removed in 23]
+ getCookieSpec(String, HttpParams): CookieSpec  [API 1-22, removed in 23]
+ getSpecNames(): List  [API 1-22, removed in 23]
+ register(String, CookieSpecFactory): void  [API 1-22, removed in 23]
+ setItems(Map): void  [API 1-22, removed in 23]
+ unregister(String): void  [API 1-22, removed in 23]
```

## MalformedCookieException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ MalformedCookieException()  [API 1-22, removed in 23]
+ MalformedCookieException(String)  [API 1-22, removed in 23]
+ MalformedCookieException(String, Throwable)  [API 1-22, removed in 23]
```

## SM — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## SetCookie — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ setComment(String): void  [API 1-22, removed in 23]
+ setDomain(String): void  [API 1-22, removed in 23]
+ setExpiryDate(Date): void  [API 1-22, removed in 23]
+ setPath(String): void  [API 1-22, removed in 23]
+ setSecure(boolean): void  [API 1-22, removed in 23]
+ setValue(String): void  [API 1-22, removed in 23]
+ setVersion(int): void  [API 1-22, removed in 23]
```

## SetCookie2 — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ setCommentURL(String): void  [API 1-22, removed in 23]
+ setDiscard(boolean): void  [API 1-22, removed in 23]
+ setPorts(int[]): void  [API 1-22, removed in 23]
```


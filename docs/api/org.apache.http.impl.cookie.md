# org.apache.http.impl.cookie

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractCookieAttributeHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ AbstractCookieAttributeHandler()  [API 1-22, removed in 23]
```

## AbstractCookieSpec — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ AbstractCookieSpec()  [API 1-22, removed in 23]
+ findAttribHandler(String): CookieAttributeHandler  [API 1-22, removed in 23]
+ getAttribHandler(String): CookieAttributeHandler  [API 1-22, removed in 23]
+ getAttribHandlers(): Collection  [API 1-22, removed in 23]
+ registerAttribHandler(String, CookieAttributeHandler): void  [API 1-22, removed in 23]
```

## BasicClientCookie — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicClientCookie(String, String)  [API 1-22, removed in 23]
+ setAttribute(String, String): void  [API 1-22, removed in 23]
```

## BasicClientCookie2 — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicClientCookie2(String, String)  [API 1-22, removed in 23]
```

## BasicCommentHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicCommentHandler()  [API 1-22, removed in 23]
```

## BasicDomainHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicDomainHandler()  [API 1-22, removed in 23]
```

## BasicExpiresHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicExpiresHandler(String[])  [API 1-22, removed in 23]
```

## BasicMaxAgeHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicMaxAgeHandler()  [API 1-22, removed in 23]
```

## BasicPathHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicPathHandler()  [API 1-22, removed in 23]
```

## BasicSecureHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicSecureHandler()  [API 1-22, removed in 23]
```

## BestMatchSpec — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BestMatchSpec()  [API 1-22, removed in 23]
+ BestMatchSpec(String[], boolean)  [API 1-22, removed in 23]
```

## BestMatchSpecFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BestMatchSpecFactory()  [API 1-22, removed in 23]
```

## BrowserCompatSpec — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BrowserCompatSpec()  [API 1-22, removed in 23]
+ BrowserCompatSpec(String[])  [API 1-22, removed in 23]
```

## BrowserCompatSpecFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BrowserCompatSpecFactory()  [API 1-22, removed in 23]
```

## CookieSpecBase — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ CookieSpecBase()  [API 1-22, removed in 23]
+ getDefaultDomain(CookieOrigin): String  [API 1-22, removed in 23]
+ getDefaultPath(CookieOrigin): String  [API 1-22, removed in 23]
+ parse(HeaderElement[], CookieOrigin): List  [API 1-22, removed in 23]
```

## DateParseException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ DateParseException()  [API 1-22, removed in 23]
+ DateParseException(String)  [API 1-22, removed in 23]
```

## DateUtils — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ formatDate(Date): String  [API 1-22, removed in 23]
+ formatDate(Date, String): String  [API 1-22, removed in 23]
+ parseDate(String): Date  [API 1-22, removed in 23]
+ parseDate(String, String[]): Date  [API 1-22, removed in 23]
+ parseDate(String, String[], Date): Date  [API 1-22, removed in 23]
```

## NetscapeDomainHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ NetscapeDomainHandler()  [API 1-22, removed in 23]
```

## NetscapeDraftHeaderParser — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ NetscapeDraftHeaderParser()  [API 1-22, removed in 23]
+ parseHeader(CharArrayBuffer, ParserCursor): HeaderElement  [API 1-22, removed in 23]
```

## NetscapeDraftSpec — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ NetscapeDraftSpec()  [API 1-22, removed in 23]
+ NetscapeDraftSpec(String[])  [API 1-22, removed in 23]
```

## NetscapeDraftSpecFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ NetscapeDraftSpecFactory()  [API 1-22, removed in 23]
```

## RFC2109DomainHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RFC2109DomainHandler()  [API 1-22, removed in 23]
```

## RFC2109Spec — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ RFC2109Spec()  [API 1-22, removed in 23]
+ RFC2109Spec(String[], boolean)  [API 1-22, removed in 23]
+ formatCookieAsVer(CharArrayBuffer, Cookie, int): void  [API 1-22, removed in 23]
+ formatParamAsVer(CharArrayBuffer, String, String, int): void  [API 1-22, removed in 23]
```

## RFC2109SpecFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RFC2109SpecFactory()  [API 1-22, removed in 23]
```

## RFC2109VersionHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RFC2109VersionHandler()  [API 1-22, removed in 23]
```

## RFC2965CommentUrlAttributeHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RFC2965CommentUrlAttributeHandler()  [API 1-22, removed in 23]
```

## RFC2965DiscardAttributeHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RFC2965DiscardAttributeHandler()  [API 1-22, removed in 23]
```

## RFC2965DomainAttributeHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ RFC2965DomainAttributeHandler()  [API 1-22, removed in 23]
+ domainMatch(String, String): boolean  [API 1-22, removed in 23]
```

## RFC2965PortAttributeHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RFC2965PortAttributeHandler()  [API 1-22, removed in 23]
```

## RFC2965Spec — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ RFC2965Spec()  [API 1-22, removed in 23]
+ RFC2965Spec(String[], boolean)  [API 1-22, removed in 23]
```

## RFC2965SpecFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RFC2965SpecFactory()  [API 1-22, removed in 23]
```

## RFC2965VersionAttributeHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RFC2965VersionAttributeHandler()  [API 1-22, removed in 23]
```


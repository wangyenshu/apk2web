# org.apache.http.client.utils

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CloneUtils — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ clone(Object): Object  [API 1-22, removed in 23]
```

## URIUtils — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ createURI(String, String, int, String, String, String): URI  [API 1-22, removed in 23]
+ resolve(URI, String): URI  [API 1-22, removed in 23]
+ resolve(URI, URI): URI  [API 1-22, removed in 23]
+ rewriteURI(URI, HttpHost): URI  [API 1-22, removed in 23]
+ rewriteURI(URI, HttpHost, boolean): URI  [API 1-22, removed in 23]
```

## URLEncodedUtils — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ URLEncodedUtils()  [API 1-22, removed in 23]
+ format(List, String): String  [API 1-22, removed in 23]
+ isEncoded(HttpEntity): boolean  [API 1-22, removed in 23]
+ parse(HttpEntity): List  [API 1-22, removed in 23]
+ parse(List, Scanner, String): void  [API 1-22, removed in 23]
+ parse(URI, String): List  [API 1-22, removed in 23]
```


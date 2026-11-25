# javax.xml.namespace

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## NamespaceContext — declaration; API 8+ (3 +, 0 *, 0 ~, 0 -)

```
+ getNamespaceURI(String): String  [API 8+]
+ getPrefix(String): String  [API 8+]
+ getPrefixes(String): Iterator  [API 8+]
```

## QName — declaration; API 8+ (0 +, 9 *, 0 ~, 1 -)

```
* QName(String)  [API 8+]
* QName(String, String)  [API 8+]
* QName(String, String, String)  [API 8+]
* equals(Object): boolean  [API 8+]
* getLocalPart(): String  [API 8+]
* getNamespaceURI(): String  [API 8+]
* getPrefix(): String  [API 8+]
* hashCode(): int  [API 8+]
* toString(): String  [API 8+]
- valueOf(String): QName [static]  [API 8+]
```


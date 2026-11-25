# org.xml.sax.ext

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Attributes2 — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ isDeclared(String): boolean  [API 1+]
+ isDeclared(String, String): boolean  [API 1+]
+ isDeclared(int): boolean  [API 1+]
+ isSpecified(String): boolean  [API 1+]
+ isSpecified(String, String): boolean  [API 1+]
+ isSpecified(int): boolean  [API 1+]
```

## Attributes2Impl — declaration; API 1+ (0 +, 0 *, 0 ~, 13 -)

```
- Attributes2Impl()  [API 1+]
- Attributes2Impl(Attributes)  [API 1+]
- addAttribute(String, String, String, String, String): void  [API 1+]
- isDeclared(String): boolean  [API 1+]
- isDeclared(String, String): boolean  [API 1+]
- isDeclared(int): boolean  [API 1+]
- isSpecified(String): boolean  [API 1+]
- isSpecified(String, String): boolean  [API 1+]
- isSpecified(int): boolean  [API 1+]
- removeAttribute(int): void  [API 1+]
- setAttributes(Attributes): void  [API 1+]
- setDeclared(int, boolean): void  [API 1+]
- setSpecified(int, boolean): void  [API 1+]
```

## DeclHandler — framework (a2w.xml.sax.ext.DeclHandler); API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ attributeDecl(String, String, String, String, String): void  [API 1+]
+ elementDecl(String, String): void  [API 1+]
+ externalEntityDecl(String, String, String): void  [API 1+]
+ internalEntityDecl(String, String): void  [API 1+]
```

## DefaultHandler2 — framework (a2w.xml.sax.ext.DefaultHandler2); API 1+ (2 +, 0 *, 13 ~, 0 -)

```
+ DefaultHandler2()  [API 1+]
~ attributeDecl(String, String, String, String, String): void  [API 1+]
~ comment(char[], int, int): void  [API 1+]
~ elementDecl(String, String): void  [API 1+]
~ endCDATA(): void  [API 1+]
~ endDTD(): void  [API 1+]
~ endEntity(String): void  [API 1+]
~ externalEntityDecl(String, String, String): void  [API 1+]
~ getExternalSubset(String, String): InputSource  [API 1+]
~ internalEntityDecl(String, String): void  [API 1+]
+ resolveEntity(String, String): InputSource  [API 1+]
~ resolveEntity(String, String, String, String): InputSource  [API 1+]
~ startCDATA(): void  [API 1+]
~ startDTD(String, String, String): void  [API 1+]
~ startEntity(String): void  [API 1+]
```

## EntityResolver2 — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ getExternalSubset(String, String): InputSource  [API 1+]
+ resolveEntity(String, String, String, String): InputSource  [API 1+]
```

## LexicalHandler — framework (a2w.xml.sax.ext.LexicalHandler); API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ comment(char[], int, int): void  [API 1+]
+ endCDATA(): void  [API 1+]
+ endDTD(): void  [API 1+]
+ endEntity(String): void  [API 1+]
+ startCDATA(): void  [API 1+]
+ startDTD(String, String, String): void  [API 1+]
+ startEntity(String): void  [API 1+]
```

## Locator2 — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ getEncoding(): String  [API 1+]
+ getXMLVersion(): String  [API 1+]
```

## Locator2Impl — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- Locator2Impl()  [API 1+]
- Locator2Impl(Locator)  [API 1+]
- getEncoding(): String  [API 1+]
- getXMLVersion(): String  [API 1+]
- setEncoding(String): void  [API 1+]
- setXMLVersion(String): void  [API 1+]
```


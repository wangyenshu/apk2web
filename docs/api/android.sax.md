# android.sax

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Element — framework; API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ getChild(String): Element  [API 1+]
+ getChild(String, String): Element  [API 1+]
+ requireChild(String): Element  [API 1+]
+ requireChild(String, String): Element  [API 1+]
+ setElementListener(ElementListener): void  [API 1+]
+ setEndElementListener(EndElementListener): void  [API 1+]
+ setEndTextElementListener(EndTextElementListener): void  [API 1+]
+ setStartElementListener(StartElementListener): void  [API 1+]
+ setTextElementListener(TextElementListener): void  [API 1+]
+ toString(): String  [API 1+]
```

## ElementListener — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)

## EndElementListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ end(): void  [API 1+]
```

## EndTextElementListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ end(String): void  [API 1+]
```

## RootElement — framework; API 1+ (2 +, 0 *, 0 ~, 1 -)

```
+ RootElement(String)  [API 1+]
+ RootElement(String, String)  [API 1+]
- getContentHandler(): ContentHandler  [API 1+]
```

## StartElementListener — framework; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- start(Attributes): void  [API 1+]
```

## TextElementListener — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)


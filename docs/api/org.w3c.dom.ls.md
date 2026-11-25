# org.w3c.dom.ls

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DOMImplementationLS — declaration; API 8+ (4 +, 0 *, 0 ~, 0 -)

```
+ createLSInput(): LSInput  [API 8+]
+ createLSOutput(): LSOutput  [API 8+]
+ createLSParser(short, String): LSParser  [API 8+]
+ createLSSerializer(): LSSerializer  [API 8+]
```

## LSException — declaration; API 8+ (0 +, 0 *, 0 ~, 1 -)

```
- LSException(short, String)  [API 8+]
+ code [field]  [API 8+]
```

## LSInput — declaration; API 8+ (16 +, 0 *, 0 ~, 0 -)

```
+ getBaseURI(): String  [API 8+]
+ getByteStream(): InputStream  [API 8+]
+ getCertifiedText(): boolean  [API 8+]
+ getCharacterStream(): Reader  [API 8+]
+ getEncoding(): String  [API 8+]
+ getPublicId(): String  [API 8+]
+ getStringData(): String  [API 8+]
+ getSystemId(): String  [API 8+]
+ setBaseURI(String): void  [API 8+]
+ setByteStream(InputStream): void  [API 8+]
+ setCertifiedText(boolean): void  [API 8+]
+ setCharacterStream(Reader): void  [API 8+]
+ setEncoding(String): void  [API 8+]
+ setPublicId(String): void  [API 8+]
+ setStringData(String): void  [API 8+]
+ setSystemId(String): void  [API 8+]
```

## LSOutput — declaration; API 8+ (8 +, 0 *, 0 ~, 0 -)

```
+ getByteStream(): OutputStream  [API 8+]
+ getCharacterStream(): Writer  [API 8+]
+ getEncoding(): String  [API 8+]
+ getSystemId(): String  [API 8+]
+ setByteStream(OutputStream): void  [API 8+]
+ setCharacterStream(Writer): void  [API 8+]
+ setEncoding(String): void  [API 8+]
+ setSystemId(String): void  [API 8+]
```

## LSParser — declaration; API 8+ (9 +, 0 *, 0 ~, 0 -)

```
+ abort(): void  [API 8+]
+ getAsync(): boolean  [API 8+]
+ getBusy(): boolean  [API 8+]
+ getDomConfig(): DOMConfiguration  [API 8+]
+ getFilter(): LSParserFilter  [API 8+]
+ parse(LSInput): Document  [API 8+]
+ parseURI(String): Document  [API 8+]
+ parseWithContext(LSInput, Node, short): Node  [API 8+]
+ setFilter(LSParserFilter): void  [API 8+]
```

## LSParserFilter — declaration; API 8+ (3 +, 0 *, 0 ~, 0 -)

```
+ acceptNode(Node): short  [API 8+]
+ getWhatToShow(): int  [API 8+]
+ startElement(Element): short  [API 8+]
```

## LSResourceResolver — declaration; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ resolveResource(String, String, String, String, String): LSInput  [API 8+]
```

## LSSerializer — declaration; API 8+ (6 +, 0 *, 0 ~, 0 -)

```
+ getDomConfig(): DOMConfiguration  [API 8+]
+ getNewLine(): String  [API 8+]
+ setNewLine(String): void  [API 8+]
+ write(Node, LSOutput): boolean  [API 8+]
+ writeToString(Node): String  [API 8+]
+ writeToURI(Node, String): boolean  [API 8+]
```


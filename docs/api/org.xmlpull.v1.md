# org.xmlpull.v1

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## XmlPullParser — bundled library; API 1+ (37 +, 0 *, 0 ~, 0 -)

```
+ TYPES [field]  [API 1+]
+ defineEntityReplacementText(String, String): void  [API 1+]
+ getAttributeCount(): int  [API 1+]
+ getAttributeName(int): String  [API 1+]
+ getAttributeNamespace(int): String  [API 1+]
+ getAttributePrefix(int): String  [API 1+]
+ getAttributeType(int): String  [API 1+]
+ getAttributeValue(String, String): String  [API 1+]
+ getAttributeValue(int): String  [API 1+]
+ getColumnNumber(): int  [API 1+]
+ getDepth(): int  [API 1+]
+ getEventType(): int  [API 1+]
+ getFeature(String): boolean  [API 1+]
+ getInputEncoding(): String  [API 1+]
+ getLineNumber(): int  [API 1+]
+ getName(): String  [API 1+]
+ getNamespace(): String  [API 1+]
+ getNamespace(String): String  [API 1+]
+ getNamespaceCount(int): int  [API 1+]
+ getNamespacePrefix(int): String  [API 1+]
+ getNamespaceUri(int): String  [API 1+]
+ getPositionDescription(): String  [API 1+]
+ getPrefix(): String  [API 1+]
+ getProperty(String): Object  [API 1+]
+ getText(): String  [API 1+]
+ getTextCharacters(int[]): char[]  [API 1+]
+ isAttributeDefault(int): boolean  [API 1+]
+ isEmptyElementTag(): boolean  [API 1+]
+ isWhitespace(): boolean  [API 1+]
+ next(): int  [API 1+]
+ nextTag(): int  [API 1+]
+ nextText(): String  [API 1+]
+ nextToken(): int  [API 1+]
+ require(int, String, String): void  [API 1+]
+ setFeature(String, boolean): void  [API 1+]
+ setInput(InputStream, String): void  [API 1+]
+ setInput(Reader): void  [API 1+]
+ setProperty(String, Object): void  [API 1+]
```

## XmlPullParserException — bundled library; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ XmlPullParserException(String)  [API 1+]
+ XmlPullParserException(String, XmlPullParser, Throwable)  [API 1+]
+ column [field]  [API 1+]
+ detail [field]  [API 1+]
+ getColumnNumber(): int  [API 1+]
+ getDetail(): Throwable  [API 1+]
+ getLineNumber(): int  [API 1+]
+ printStackTrace(): void  [API 1+]
+ row [field]  [API 1+]
```

## XmlPullParserFactory — framework; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ XmlPullParserFactory()  [API 1+]
+ classNamesLocation [field]  [API 1+]
+ features [field]  [API 1+]
+ getFeature(String): boolean  [API 1+]
+ isNamespaceAware(): boolean  [API 1+]
+ isValidating(): boolean  [API 1+]
+ newInstance(): XmlPullParserFactory [static]  [API 1+]
+ newInstance(String, Class): XmlPullParserFactory [static]  [API 1+]
+ newPullParser(): XmlPullParser  [API 1+]
+ newSerializer(): XmlSerializer  [API 1+]
+ parserClasses [field]  [API 1+]
+ serializerClasses [field]  [API 1+]
+ setFeature(String, boolean): void  [API 1+]
+ setNamespaceAware(boolean): void  [API 1+]
+ setValidating(boolean): void  [API 1+]
```

## XmlSerializer — bundled library; API 1+ (25 +, 0 *, 0 ~, 0 -)

```
+ attribute(String, String, String): XmlSerializer  [API 1+]
+ cdsect(String): void  [API 1+]
+ comment(String): void  [API 1+]
+ docdecl(String): void  [API 1+]
+ endDocument(): void  [API 1+]
+ endTag(String, String): XmlSerializer  [API 1+]
+ entityRef(String): void  [API 1+]
+ flush(): void  [API 1+]
+ getDepth(): int  [API 1+]
+ getFeature(String): boolean  [API 1+]
+ getName(): String  [API 1+]
+ getNamespace(): String  [API 1+]
+ getPrefix(String, boolean): String  [API 1+]
+ getProperty(String): Object  [API 1+]
+ ignorableWhitespace(String): void  [API 1+]
+ processingInstruction(String): void  [API 1+]
+ setFeature(String, boolean): void  [API 1+]
+ setOutput(OutputStream, String): void  [API 1+]
+ setOutput(Writer): void  [API 1+]
+ setPrefix(String, String): void  [API 1+]
+ setProperty(String, Object): void  [API 1+]
+ startDocument(String, Boolean): void  [API 1+]
+ startTag(String, String): XmlSerializer  [API 1+]
+ text(String): XmlSerializer  [API 1+]
+ text(char[], int, int): XmlSerializer  [API 1+]
```


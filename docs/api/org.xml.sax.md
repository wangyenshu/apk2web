# org.xml.sax

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AttributeList — framework (a2w.xml.sax.AttributeList); API 1+, deprecated in 9 (6 +, 0 *, 0 ~, 0 -)

```
+ getLength(): int  [API 1+]
+ getName(int): String  [API 1+]
+ getType(String): String  [API 1+]
+ getType(int): String  [API 1+]
+ getValue(String): String  [API 1+]
+ getValue(int): String  [API 1+]
```

## Attributes — framework (a2w.xml.sax.Attributes); API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ getIndex(String): int  [API 1+]
+ getIndex(String, String): int  [API 1+]
+ getLength(): int  [API 1+]
+ getLocalName(int): String  [API 1+]
+ getQName(int): String  [API 1+]
+ getType(String): String  [API 1+]
+ getType(String, String): String  [API 1+]
+ getType(int): String  [API 1+]
+ getURI(int): String  [API 1+]
+ getValue(String): String  [API 1+]
+ getValue(String, String): String  [API 1+]
+ getValue(int): String  [API 1+]
```

## ContentHandler — framework (a2w.xml.sax.ContentHandler); API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ characters(char[], int, int): void  [API 1+]
+ endDocument(): void  [API 1+]
+ endElement(String, String, String): void  [API 1+]
+ endPrefixMapping(String): void  [API 1+]
+ ignorableWhitespace(char[], int, int): void  [API 1+]
+ processingInstruction(String, String): void  [API 1+]
+ setDocumentLocator(Locator): void  [API 1+]
+ skippedEntity(String): void  [API 1+]
+ startDocument(): void  [API 1+]
+ startElement(String, String, String, Attributes): void  [API 1+]
+ startPrefixMapping(String, String): void  [API 1+]
```

## DTDHandler — framework (a2w.xml.sax.DTDHandler); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ notationDecl(String, String, String): void  [API 1+]
+ unparsedEntityDecl(String, String, String, String): void  [API 1+]
```

## DocumentHandler — declaration; API 1+, deprecated in 9 (8 +, 0 *, 0 ~, 0 -)

```
+ characters(char[], int, int): void  [API 1+]
+ endDocument(): void  [API 1+]
+ endElement(String): void  [API 1+]
+ ignorableWhitespace(char[], int, int): void  [API 1+]
+ processingInstruction(String, String): void  [API 1+]
+ setDocumentLocator(Locator): void  [API 1+]
+ startDocument(): void  [API 1+]
+ startElement(String, AttributeList): void  [API 1+]
```

## EntityResolver — framework (a2w.xml.sax.EntityResolver); API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ resolveEntity(String, String): InputSource  [API 1+]
```

## ErrorHandler — framework (a2w.xml.sax.ErrorHandler); API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ error(SAXParseException): void  [API 1+]
+ fatalError(SAXParseException): void  [API 1+]
+ warning(SAXParseException): void  [API 1+]
```

## HandlerBase — declaration; API 1+, deprecated in 9 (0 +, 0 *, 0 ~, 15 -)

```
- HandlerBase()  [API 1+]
- characters(char[], int, int): void  [API 1+]
- endDocument(): void  [API 1+]
- endElement(String): void  [API 1+]
- error(SAXParseException): void  [API 1+]
- fatalError(SAXParseException): void  [API 1+]
- ignorableWhitespace(char[], int, int): void  [API 1+]
- notationDecl(String, String, String): void  [API 1+]
- processingInstruction(String, String): void  [API 1+]
- resolveEntity(String, String): InputSource  [API 1+]
- setDocumentLocator(Locator): void  [API 1+]
- startDocument(): void  [API 1+]
- startElement(String, AttributeList): void  [API 1+]
- unparsedEntityDecl(String, String, String, String): void  [API 1+]
- warning(SAXParseException): void  [API 1+]
```

## InputSource — framework (a2w.xml.sax.InputSource); API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ InputSource()  [API 1+]
+ InputSource(InputStream)  [API 1+]
+ InputSource(Reader)  [API 1+]
+ InputSource(String)  [API 1+]
+ getByteStream(): InputStream  [API 1+]
+ getCharacterStream(): Reader  [API 1+]
+ getEncoding(): String  [API 1+]
+ getPublicId(): String  [API 1+]
+ getSystemId(): String  [API 1+]
+ setByteStream(InputStream): void  [API 1+]
+ setCharacterStream(Reader): void  [API 1+]
+ setEncoding(String): void  [API 1+]
+ setPublicId(String): void  [API 1+]
+ setSystemId(String): void  [API 1+]
```

## Locator — framework (a2w.xml.sax.Locator); API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ getColumnNumber(): int  [API 1+]
+ getLineNumber(): int  [API 1+]
+ getPublicId(): String  [API 1+]
+ getSystemId(): String  [API 1+]
```

## Parser — declaration; API 1+, deprecated in 9 (7 +, 0 *, 0 ~, 0 -)

```
+ parse(InputSource): void  [API 1+]
+ parse(String): void  [API 1+]
+ setDTDHandler(DTDHandler): void  [API 1+]
+ setDocumentHandler(DocumentHandler): void  [API 1+]
+ setEntityResolver(EntityResolver): void  [API 1+]
+ setErrorHandler(ErrorHandler): void  [API 1+]
+ setLocale(Locale): void  [API 1+]
```

## SAXException — framework (a2w.xml.sax.SAXException); API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ SAXException()  [API 1+]
+ SAXException(Exception)  [API 1+]
+ SAXException(String)  [API 1+]
+ SAXException(String, Exception)  [API 1+]
+ getException(): Exception  [API 1+]
+ getMessage(): String  [API 1+]
+ toString(): String  [API 1+]
```

## SAXNotRecognizedException — framework (a2w.xml.sax.SAXNotRecognizedException); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SAXNotRecognizedException()  [API 1+]
+ SAXNotRecognizedException(String)  [API 1+]
```

## SAXNotSupportedException — framework (a2w.xml.sax.SAXNotSupportedException); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SAXNotSupportedException()  [API 1+]
+ SAXNotSupportedException(String)  [API 1+]
```

## SAXParseException — framework (a2w.xml.sax.SAXParseException); API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ SAXParseException(String, Locator)  [API 1+]
+ SAXParseException(String, Locator, Exception)  [API 1+]
+ SAXParseException(String, String, String, int, int)  [API 1+]
+ SAXParseException(String, String, String, int, int, Exception)  [API 1+]
+ getColumnNumber(): int  [API 1+]
+ getLineNumber(): int  [API 1+]
+ getPublicId(): String  [API 1+]
+ getSystemId(): String  [API 1+]
```

## XMLFilter — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ getParent(): XMLReader  [API 1+]
+ setParent(XMLReader): void  [API 1+]
```

## XMLReader — framework (a2w.xml.sax.XMLReader); API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ getContentHandler(): ContentHandler  [API 1+]
+ getDTDHandler(): DTDHandler  [API 1+]
+ getEntityResolver(): EntityResolver  [API 1+]
+ getErrorHandler(): ErrorHandler  [API 1+]
+ getFeature(String): boolean  [API 1+]
+ getProperty(String): Object  [API 1+]
+ parse(InputSource): void  [API 1+]
+ parse(String): void  [API 1+]
+ setContentHandler(ContentHandler): void  [API 1+]
+ setDTDHandler(DTDHandler): void  [API 1+]
+ setEntityResolver(EntityResolver): void  [API 1+]
+ setErrorHandler(ErrorHandler): void  [API 1+]
+ setFeature(String, boolean): void  [API 1+]
+ setProperty(String, Object): void  [API 1+]
```


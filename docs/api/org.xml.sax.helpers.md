# org.xml.sax.helpers

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AttributeListImpl — declaration; API 1+, deprecated in 9 (0 +, 0 *, 0 ~, 12 -)

```
- AttributeListImpl()  [API 1+]
- AttributeListImpl(AttributeList)  [API 1+]
- addAttribute(String, String, String): void  [API 1+]
- clear(): void  [API 1+]
- getLength(): int  [API 1+]
- getName(int): String  [API 1+]
- getType(String): String  [API 1+]
- getType(int): String  [API 1+]
- getValue(String): String  [API 1+]
- getValue(int): String  [API 1+]
- removeAttribute(String): void  [API 1+]
- setAttributeList(AttributeList): void  [API 1+]
```

## AttributesImpl — framework (a2w.xml.sax.helpers.AttributesImpl); API 1+ (24 +, 0 *, 0 ~, 0 -)

```
+ AttributesImpl()  [API 1+]
+ AttributesImpl(Attributes)  [API 1+]
+ addAttribute(String, String, String, String, String): void  [API 1+]
+ clear(): void  [API 1+]
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
+ removeAttribute(int): void  [API 1+]
+ setAttribute(int, String, String, String, String, String): void  [API 1+]
+ setAttributes(Attributes): void  [API 1+]
+ setLocalName(int, String): void  [API 1+]
+ setQName(int, String): void  [API 1+]
+ setType(int, String): void  [API 1+]
+ setURI(int, String): void  [API 1+]
+ setValue(int, String): void  [API 1+]
```

## DefaultHandler — framework (a2w.xml.sax.helpers.DefaultHandler); API 1+ (1 +, 0 *, 17 ~, 0 -)

```
+ DefaultHandler()  [API 1+]
~ characters(char[], int, int): void  [API 1+]
~ endDocument(): void  [API 1+]
~ endElement(String, String, String): void  [API 1+]
~ endPrefixMapping(String): void  [API 1+]
~ error(SAXParseException): void  [API 1+]
~ fatalError(SAXParseException): void  [API 1+]
~ ignorableWhitespace(char[], int, int): void  [API 1+]
~ notationDecl(String, String, String): void  [API 1+]
~ processingInstruction(String, String): void  [API 1+]
~ resolveEntity(String, String): InputSource  [API 1+]
~ setDocumentLocator(Locator): void  [API 1+]
~ skippedEntity(String): void  [API 1+]
~ startDocument(): void  [API 1+]
~ startElement(String, String, String, Attributes): void  [API 1+]
~ startPrefixMapping(String, String): void  [API 1+]
~ unparsedEntityDecl(String, String, String, String): void  [API 1+]
~ warning(SAXParseException): void  [API 1+]
```

## LocatorImpl — framework (a2w.xml.sax.helpers.LocatorImpl); API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ LocatorImpl()  [API 1+]
+ LocatorImpl(Locator)  [API 1+]
+ getColumnNumber(): int  [API 1+]
+ getLineNumber(): int  [API 1+]
+ getPublicId(): String  [API 1+]
+ getSystemId(): String  [API 1+]
+ setColumnNumber(int): void  [API 1+]
+ setLineNumber(int): void  [API 1+]
+ setPublicId(String): void  [API 1+]
+ setSystemId(String): void  [API 1+]
```

## NamespaceSupport — framework (a2w.xml.sax.helpers.NamespaceSupport); API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ NamespaceSupport()  [API 1+]
+ declarePrefix(String, String): boolean  [API 1+]
+ getDeclaredPrefixes(): Enumeration  [API 1+]
+ getPrefix(String): String  [API 1+]
+ getPrefixes(): Enumeration  [API 1+]
+ getPrefixes(String): Enumeration  [API 1+]
+ getURI(String): String  [API 1+]
+ isNamespaceDeclUris(): boolean  [API 1+]
+ popContext(): void  [API 1+]
+ processName(String, String[], boolean): String[]  [API 1+]
+ pushContext(): void  [API 1+]
+ reset(): void  [API 1+]
+ setNamespaceDeclUris(boolean): void  [API 1+]
```

## ParserAdapter — declaration; API 1+ (0 +, 0 *, 0 ~, 24 -)

```
- ParserAdapter()  [API 1+]
- ParserAdapter(Parser)  [API 1+]
- characters(char[], int, int): void  [API 1+]
- endDocument(): void  [API 1+]
- endElement(String): void  [API 1+]
- getContentHandler(): ContentHandler  [API 1+]
- getDTDHandler(): DTDHandler  [API 1+]
- getEntityResolver(): EntityResolver  [API 1+]
- getErrorHandler(): ErrorHandler  [API 1+]
- getFeature(String): boolean  [API 1+]
- getProperty(String): Object  [API 1+]
- ignorableWhitespace(char[], int, int): void  [API 1+]
- parse(InputSource): void  [API 1+]
- parse(String): void  [API 1+]
- processingInstruction(String, String): void  [API 1+]
- setContentHandler(ContentHandler): void  [API 1+]
- setDTDHandler(DTDHandler): void  [API 1+]
- setDocumentLocator(Locator): void  [API 1+]
- setEntityResolver(EntityResolver): void  [API 1+]
- setErrorHandler(ErrorHandler): void  [API 1+]
- setFeature(String, boolean): void  [API 1+]
- setProperty(String, Object): void  [API 1+]
- startDocument(): void  [API 1+]
- startElement(String, AttributeList): void  [API 1+]
```

## ParserFactory — declaration; API 1+, deprecated in 9 (0 +, 0 *, 0 ~, 2 -)

```
- makeParser(): Parser [static]  [API 1+]
- makeParser(String): Parser [static]  [API 1+]
```

## XMLFilterImpl — declaration; API 1+ (0 +, 12 *, 0 ~, 23 -)

```
* XMLFilterImpl()  [API 1+]
* XMLFilterImpl(XMLReader)  [API 1+]
- characters(char[], int, int): void  [API 1+]
- endDocument(): void  [API 1+]
- endElement(String, String, String): void  [API 1+]
- endPrefixMapping(String): void  [API 1+]
- error(SAXParseException): void  [API 1+]
- fatalError(SAXParseException): void  [API 1+]
* getContentHandler(): ContentHandler  [API 1+]
* getDTDHandler(): DTDHandler  [API 1+]
* getEntityResolver(): EntityResolver  [API 1+]
* getErrorHandler(): ErrorHandler  [API 1+]
- getFeature(String): boolean  [API 1+]
* getParent(): XMLReader  [API 1+]
- getProperty(String): Object  [API 1+]
- ignorableWhitespace(char[], int, int): void  [API 1+]
- notationDecl(String, String, String): void  [API 1+]
- parse(InputSource): void  [API 1+]
- parse(String): void  [API 1+]
- processingInstruction(String, String): void  [API 1+]
- resolveEntity(String, String): InputSource  [API 1+]
* setContentHandler(ContentHandler): void  [API 1+]
* setDTDHandler(DTDHandler): void  [API 1+]
- setDocumentLocator(Locator): void  [API 1+]
* setEntityResolver(EntityResolver): void  [API 1+]
* setErrorHandler(ErrorHandler): void  [API 1+]
- setFeature(String, boolean): void  [API 1+]
* setParent(XMLReader): void  [API 1+]
- setProperty(String, Object): void  [API 1+]
- skippedEntity(String): void  [API 1+]
- startDocument(): void  [API 1+]
- startElement(String, String, String, Attributes): void  [API 1+]
- startPrefixMapping(String, String): void  [API 1+]
- unparsedEntityDecl(String, String, String, String): void  [API 1+]
- warning(SAXParseException): void  [API 1+]
```

## XMLReaderAdapter — declaration; API 1+ (0 +, 0 *, 0 ~, 20 -)

```
- XMLReaderAdapter()  [API 1+]
- XMLReaderAdapter(XMLReader)  [API 1+]
- characters(char[], int, int): void  [API 1+]
- endDocument(): void  [API 1+]
- endElement(String, String, String): void  [API 1+]
- endPrefixMapping(String): void  [API 1+]
- ignorableWhitespace(char[], int, int): void  [API 1+]
- parse(InputSource): void  [API 1+]
- parse(String): void  [API 1+]
- processingInstruction(String, String): void  [API 1+]
- setDTDHandler(DTDHandler): void  [API 1+]
- setDocumentHandler(DocumentHandler): void  [API 1+]
- setDocumentLocator(Locator): void  [API 1+]
- setEntityResolver(EntityResolver): void  [API 1+]
- setErrorHandler(ErrorHandler): void  [API 1+]
- setLocale(Locale): void  [API 1+]
- skippedEntity(String): void  [API 1+]
- startDocument(): void  [API 1+]
- startElement(String, String, String, Attributes): void  [API 1+]
- startPrefixMapping(String, String): void  [API 1+]
```

## XMLReaderFactory — framework (a2w.xml.sax.helpers.XMLReaderFactory); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ createXMLReader(): XMLReader [static]  [API 1+]
+ createXMLReader(String): XMLReader [static]  [API 1+]
```


# org.xmlpull.v1.sax2

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Driver — declaration; API 1+ (0 +, 9 *, 0 ~, 25 -)

```
* Driver()  [API 1+]
- Driver(XmlPullParser)  [API 1+]
+ contentHandler [field]  [API 1+]
+ errorHandler [field]  [API 1+]
- getColumnNumber(): int  [API 1+]
* getContentHandler(): ContentHandler  [API 1+]
* getDTDHandler(): DTDHandler  [API 1+]
* getEntityResolver(): EntityResolver  [API 1+]
* getErrorHandler(): ErrorHandler  [API 1+]
- getFeature(String): boolean  [API 1+]
- getIndex(String): int  [API 1+]
- getIndex(String, String): int  [API 1+]
- getLength(): int  [API 1+]
- getLineNumber(): int  [API 1+]
- getLocalName(int): String  [API 1+]
- getProperty(String): Object  [API 1+]
- getPublicId(): String  [API 1+]
- getQName(int): String  [API 1+]
- getSystemId(): String  [API 1+]
- getType(String): String  [API 1+]
- getType(String, String): String  [API 1+]
- getType(int): String  [API 1+]
- getURI(int): String  [API 1+]
- getValue(String): String  [API 1+]
- getValue(String, String): String  [API 1+]
- getValue(int): String  [API 1+]
- parse(InputSource): void  [API 1+]
- parse(String): void  [API 1+]
- parseSubTree(XmlPullParser): void  [API 1+]
+ pp [field]  [API 1+]
* setContentHandler(ContentHandler): void  [API 1+]
* setDTDHandler(DTDHandler): void  [API 1+]
* setEntityResolver(EntityResolver): void  [API 1+]
* setErrorHandler(ErrorHandler): void  [API 1+]
- setFeature(String, boolean): void  [API 1+]
- setProperty(String, Object): void  [API 1+]
- startElement(String, String, String): void  [API 1+]
+ systemId [field]  [API 1+]
```


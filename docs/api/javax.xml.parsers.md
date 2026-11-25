# javax.xml.parsers

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DocumentBuilder — framework (a2w.xml.parsers.DocumentBuilder); API 1+ (12 +, 0 *, 3 ~, 0 -)

```
+ DocumentBuilder()  [API 1+]
+ getDOMImplementation(): DOMImplementation  [API 1+]
~ getSchema(): Schema  [API 8+]
+ isNamespaceAware(): boolean  [API 1+]
+ isValidating(): boolean  [API 1+]
~ isXIncludeAware(): boolean  [API 1+]
+ newDocument(): Document  [API 1+]
+ parse(File): Document  [API 1+]
+ parse(InputSource): Document  [API 1+]
+ parse(InputStream): Document  [API 1+]
+ parse(InputStream, String): Document  [API 1+]
+ parse(String): Document  [API 1+]
~ reset(): void  [API 1+]
+ setEntityResolver(EntityResolver): void  [API 1+]
+ setErrorHandler(ErrorHandler): void  [API 1+]
```

## DocumentBuilderFactory — framework (a2w.xml.parsers.DocumentBuilderFactory); API 1+ (24 +, 0 *, 0 ~, 0 -)

```
+ DocumentBuilderFactory()  [API 1+]
+ getAttribute(String): Object  [API 1+]
+ getFeature(String): boolean  [API 1+]
+ getSchema(): Schema  [API 8+]
+ isCoalescing(): boolean  [API 1+]
+ isExpandEntityReferences(): boolean  [API 1+]
+ isIgnoringComments(): boolean  [API 1+]
+ isIgnoringElementContentWhitespace(): boolean  [API 1+]
+ isNamespaceAware(): boolean  [API 1+]
+ isValidating(): boolean  [API 1+]
+ isXIncludeAware(): boolean  [API 1+]
+ newDocumentBuilder(): DocumentBuilder  [API 1+]
+ newInstance(): DocumentBuilderFactory [static]  [API 1+]
+ newInstance(String, ClassLoader): DocumentBuilderFactory [static]  [API 9+]
+ setAttribute(String, Object): void  [API 1+]
+ setCoalescing(boolean): void  [API 1+]
+ setExpandEntityReferences(boolean): void  [API 1+]
+ setFeature(String, boolean): void  [API 1+]
+ setIgnoringComments(boolean): void  [API 1+]
+ setIgnoringElementContentWhitespace(boolean): void  [API 1+]
+ setNamespaceAware(boolean): void  [API 1+]
+ setSchema(Schema): void  [API 8+]
+ setValidating(boolean): void  [API 1+]
+ setXIncludeAware(boolean): void  [API 1+]
```

## FactoryConfigurationError — framework (a2w.xml.parsers.FactoryConfigurationError); API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ FactoryConfigurationError()  [API 1+]
+ FactoryConfigurationError(Exception)  [API 1+]
+ FactoryConfigurationError(Exception, String)  [API 1+]
+ FactoryConfigurationError(String)  [API 1+]
+ getException(): Exception  [API 1+]
+ getMessage(): String  [API 1+]
```

## ParserConfigurationException — framework (a2w.xml.parsers.ParserConfigurationException); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ParserConfigurationException()  [API 1+]
+ ParserConfigurationException(String)  [API 1+]
```

## SAXParser — framework (a2w.xml.parsers.SAXParser); API 1+ (11 +, 0 *, 3 ~, 6 -)

```
+ SAXParser()  [API 1+]
- getParser(): Parser  [API 1+]
+ getProperty(String): Object  [API 1+]
~ getSchema(): Schema  [API 8+]
+ getXMLReader(): XMLReader  [API 1+]
+ isNamespaceAware(): boolean  [API 1+]
+ isValidating(): boolean  [API 1+]
~ isXIncludeAware(): boolean  [API 1+]
+ parse(File, DefaultHandler): void  [API 1+]
- parse(File, HandlerBase): void  [API 1+]
+ parse(InputSource, DefaultHandler): void  [API 1+]
- parse(InputSource, HandlerBase): void  [API 1+]
+ parse(InputStream, DefaultHandler): void  [API 1+]
+ parse(InputStream, DefaultHandler, String): void  [API 1+]
- parse(InputStream, HandlerBase): void  [API 1+]
- parse(InputStream, HandlerBase, String): void  [API 1+]
+ parse(String, DefaultHandler): void  [API 1+]
- parse(String, HandlerBase): void  [API 1+]
~ reset(): void  [API 1+]
+ setProperty(String, Object): void  [API 1+]
```

## SAXParserFactory — framework (a2w.xml.parsers.SAXParserFactory); API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ SAXParserFactory()  [API 1+]
+ getFeature(String): boolean  [API 1+]
+ getSchema(): Schema  [API 8+]
+ isNamespaceAware(): boolean  [API 1+]
+ isValidating(): boolean  [API 1+]
+ isXIncludeAware(): boolean  [API 1+]
+ newInstance(): SAXParserFactory [static]  [API 1+]
+ newInstance(String, ClassLoader): SAXParserFactory [static]  [API 9+]
+ newSAXParser(): SAXParser  [API 1+]
+ setFeature(String, boolean): void  [API 1+]
+ setNamespaceAware(boolean): void  [API 1+]
+ setSchema(Schema): void  [API 8+]
+ setValidating(boolean): void  [API 1+]
+ setXIncludeAware(boolean): void  [API 1+]
```


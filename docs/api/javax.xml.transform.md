# javax.xml.transform

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ErrorListener — framework (a2w.xml.transform.ErrorListener); API 8+ (3 +, 0 *, 0 ~, 0 -)

```
+ error(TransformerException): void  [API 8+]
+ fatalError(TransformerException): void  [API 8+]
+ warning(TransformerException): void  [API 8+]
```

## OutputKeys — framework (a2w.xml.transform.OutputKeys); API 8+ (0 +, 0 *, 0 ~, 0 -)

## Result — framework (a2w.xml.transform.Result); API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ getSystemId(): String  [API 8+]
+ setSystemId(String): void  [API 8+]
```

## Source — framework (a2w.xml.transform.Source); API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ getSystemId(): String  [API 8+]
+ setSystemId(String): void  [API 8+]
```

## SourceLocator — framework (a2w.xml.transform.SourceLocator); API 8+ (4 +, 0 *, 0 ~, 0 -)

```
+ getColumnNumber(): int  [API 8+]
+ getLineNumber(): int  [API 8+]
+ getPublicId(): String  [API 8+]
+ getSystemId(): String  [API 8+]
```

## Templates — framework (a2w.xml.transform.Templates); API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ getOutputProperties(): Properties  [API 8+]
+ newTransformer(): Transformer  [API 8+]
```

## Transformer — framework (a2w.xml.transform.Transformer); API 8+ (13 +, 0 *, 1 ~, 0 -)

```
+ Transformer()  [API 8+]
+ clearParameters(): void  [API 8+]
+ getErrorListener(): ErrorListener  [API 8+]
+ getOutputProperties(): Properties  [API 8+]
+ getOutputProperty(String): String  [API 8+]
+ getParameter(String): Object  [API 8+]
+ getURIResolver(): URIResolver  [API 8+]
~ reset(): void  [API 8+]
+ setErrorListener(ErrorListener): void  [API 8+]
+ setOutputProperties(Properties): void  [API 8+]
+ setOutputProperty(String, String): void  [API 8+]
+ setParameter(String, Object): void  [API 8+]
+ setURIResolver(URIResolver): void  [API 8+]
+ transform(Source, Result): void  [API 8+]
```

## TransformerConfigurationException — framework (a2w.xml.transform.TransformerConfigurationException); API 8+ (4 +, 0 *, 0 ~, 2 -)

```
+ TransformerConfigurationException()  [API 8+]
+ TransformerConfigurationException(String)  [API 8+]
- TransformerConfigurationException(String, SourceLocator)  [API 8+]
- TransformerConfigurationException(String, SourceLocator, Throwable)  [API 8+]
+ TransformerConfigurationException(String, Throwable)  [API 8+]
+ TransformerConfigurationException(Throwable)  [API 8+]
```

## TransformerException — framework (a2w.xml.transform.TransformerException); API 8+ (15 +, 0 *, 0 ~, 0 -)

```
+ TransformerException(String)  [API 8+]
+ TransformerException(String, SourceLocator)  [API 8+]
+ TransformerException(String, SourceLocator, Throwable)  [API 8+]
+ TransformerException(String, Throwable)  [API 8+]
+ TransformerException(Throwable)  [API 8+]
+ getCause(): Throwable  [API 8+]
+ getException(): Throwable  [API 8+]
+ getLocationAsString(): String  [API 8+]
+ getLocator(): SourceLocator  [API 8+]
+ getMessageAndLocation(): String  [API 8+]
+ initCause(Throwable): Throwable  [API 8+]
+ printStackTrace(): void  [API 8+]
+ printStackTrace(PrintStream): void  [API 8+]
+ printStackTrace(PrintWriter): void  [API 8+]
+ setLocator(SourceLocator): void  [API 8+]
```

## TransformerFactory — framework (a2w.xml.transform.TransformerFactory); API 8+ (15 +, 0 *, 0 ~, 0 -)

```
+ TransformerFactory()  [API 8+]
+ getAssociatedStylesheet(Source, String, String, String): Source  [API 8+]
+ getAttribute(String): Object  [API 8+]
+ getErrorListener(): ErrorListener  [API 8+]
+ getFeature(String): boolean  [API 8+]
+ getURIResolver(): URIResolver  [API 8+]
+ newInstance(): TransformerFactory [static]  [API 8+]
+ newInstance(String, ClassLoader): TransformerFactory [static]  [API 9+]
+ newTemplates(Source): Templates  [API 8+]
+ newTransformer(): Transformer  [API 8+]
+ newTransformer(Source): Transformer  [API 8+]
+ setAttribute(String, Object): void  [API 8+]
+ setErrorListener(ErrorListener): void  [API 8+]
+ setFeature(String, boolean): void  [API 8+]
+ setURIResolver(URIResolver): void  [API 8+]
```

## TransformerFactoryConfigurationError — framework (a2w.xml.transform.TransformerFactoryConfigurationError); API 8+ (6 +, 0 *, 0 ~, 0 -)

```
+ TransformerFactoryConfigurationError()  [API 8+]
+ TransformerFactoryConfigurationError(Exception)  [API 8+]
+ TransformerFactoryConfigurationError(Exception, String)  [API 8+]
+ TransformerFactoryConfigurationError(String)  [API 8+]
+ getException(): Exception  [API 8+]
+ getMessage(): String  [API 8+]
```

## URIResolver — framework (a2w.xml.transform.URIResolver); API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ resolve(String, String): Source  [API 8+]
```


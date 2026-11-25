# javax.xml.xpath

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## XPath — declaration; API 8+ (12 +, 0 *, 0 ~, 0 -)

```
+ compile(String): XPathExpression  [API 8+]
+ evaluate(String, InputSource): String  [API 8+]
+ evaluate(String, InputSource, QName): Object  [API 8+]
+ evaluate(String, Object): String  [API 8+]
+ evaluate(String, Object, QName): Object  [API 8+]
+ getNamespaceContext(): NamespaceContext  [API 8+]
+ getXPathFunctionResolver(): XPathFunctionResolver  [API 8+]
+ getXPathVariableResolver(): XPathVariableResolver  [API 8+]
+ reset(): void  [API 8+]
+ setNamespaceContext(NamespaceContext): void  [API 8+]
+ setXPathFunctionResolver(XPathFunctionResolver): void  [API 8+]
+ setXPathVariableResolver(XPathVariableResolver): void  [API 8+]
```

## XPathConstants — declaration; API 8+ (0 +, 0 *, 0 ~, 0 -)

```
- BOOLEAN [field]  [API 8+]
- NODE [field]  [API 8+]
- NODESET [field]  [API 8+]
- NUMBER [field]  [API 8+]
- STRING [field]  [API 8+]
```

## XPathException — declaration; API 8+ (2 +, 0 *, 0 ~, 4 -)

```
+ XPathException(String)  [API 8+]
+ XPathException(Throwable)  [API 8+]
- getCause(): Throwable  [API 8+]
- printStackTrace(): void  [API 8+]
- printStackTrace(PrintStream): void  [API 8+]
- printStackTrace(PrintWriter): void  [API 8+]
```

## XPathExpression — declaration; API 8+ (4 +, 0 *, 0 ~, 0 -)

```
+ evaluate(InputSource): String  [API 8+]
+ evaluate(InputSource, QName): Object  [API 8+]
+ evaluate(Object): String  [API 8+]
+ evaluate(Object, QName): Object  [API 8+]
```

## XPathExpressionException — declaration; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ XPathExpressionException(String)  [API 8+]
+ XPathExpressionException(Throwable)  [API 8+]
```

## XPathFactory — declaration; API 8+ (6 +, 0 *, 0 ~, 4 -)

```
- XPathFactory()  [API 8+]
+ getFeature(String): boolean  [API 8+]
+ isObjectModelSupported(String): boolean  [API 8+]
- newInstance(): XPathFactory [static]  [API 8+]
- newInstance(String): XPathFactory [static]  [API 8+]
- newInstance(String, String, ClassLoader): XPathFactory [static]  [API 8+]
+ newXPath(): XPath  [API 8+]
+ setFeature(String, boolean): void  [API 8+]
+ setXPathFunctionResolver(XPathFunctionResolver): void  [API 8+]
+ setXPathVariableResolver(XPathVariableResolver): void  [API 8+]
```

## XPathFactoryConfigurationException — declaration; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ XPathFactoryConfigurationException(String)  [API 8+]
+ XPathFactoryConfigurationException(Throwable)  [API 8+]
```

## XPathFunction — declaration; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ evaluate(List): Object  [API 8+]
```

## XPathFunctionException — declaration; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ XPathFunctionException(String)  [API 8+]
+ XPathFunctionException(Throwable)  [API 8+]
```

## XPathFunctionResolver — declaration; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ resolveFunction(QName, int): XPathFunction  [API 8+]
```

## XPathVariableResolver — declaration; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ resolveVariable(QName): Object  [API 8+]
```


# javax.xml.validation

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Schema — framework (a2w.xml.validation.Schema); API 8+ (1 +, 0 *, 0 ~, 2 -)

```
+ Schema()  [API 8+]
- newValidator(): Validator  [API 8+]
- newValidatorHandler(): ValidatorHandler  [API 8+]
```

## SchemaFactory — declaration; API 8+ (7 +, 0 *, 0 ~, 10 -)

```
- SchemaFactory()  [API 8+]
+ getErrorHandler(): ErrorHandler  [API 8+]
- getFeature(String): boolean  [API 8+]
- getProperty(String): Object  [API 8+]
+ getResourceResolver(): LSResourceResolver  [API 8+]
+ isSchemaLanguageSupported(String): boolean  [API 8+]
- newInstance(String): SchemaFactory [static]  [API 8+]
- newInstance(String, String, ClassLoader): SchemaFactory [static]  [API 9+]
+ newSchema(): Schema  [API 8+]
- newSchema(File): Schema  [API 8+]
- newSchema(Source): Schema  [API 8+]
+ newSchema(Source[]): Schema  [API 8+]
- newSchema(URL): Schema  [API 8+]
+ setErrorHandler(ErrorHandler): void  [API 8+]
- setFeature(String, boolean): void  [API 8+]
- setProperty(String, Object): void  [API 8+]
+ setResourceResolver(LSResourceResolver): void  [API 8+]
```

## SchemaFactoryLoader — declaration; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ SchemaFactoryLoader()  [API 8+]
+ newFactory(String): SchemaFactory  [API 8+]
```

## TypeInfoProvider — declaration; API 8+ (5 +, 0 *, 0 ~, 0 -)

```
+ TypeInfoProvider()  [API 8+]
+ getAttributeTypeInfo(int): TypeInfo  [API 8+]
+ getElementTypeInfo(): TypeInfo  [API 8+]
+ isIdAttribute(int): boolean  [API 8+]
+ isSpecified(int): boolean  [API 8+]
```

## Validator — declaration; API 8+ (6 +, 0 *, 0 ~, 6 -)

```
- Validator()  [API 8+]
+ getErrorHandler(): ErrorHandler  [API 8+]
- getFeature(String): boolean  [API 8+]
- getProperty(String): Object  [API 8+]
+ getResourceResolver(): LSResourceResolver  [API 8+]
+ reset(): void  [API 8+]
+ setErrorHandler(ErrorHandler): void  [API 8+]
- setFeature(String, boolean): void  [API 8+]
- setProperty(String, Object): void  [API 8+]
+ setResourceResolver(LSResourceResolver): void  [API 8+]
- validate(Source): void  [API 8+]
+ validate(Source, Result): void  [API 8+]
```

## ValidatorHandler — declaration; API 8+ (7 +, 0 *, 0 ~, 5 -)

```
- ValidatorHandler()  [API 8+]
+ getContentHandler(): ContentHandler  [API 8+]
+ getErrorHandler(): ErrorHandler  [API 8+]
- getFeature(String): boolean  [API 8+]
- getProperty(String): Object  [API 8+]
+ getResourceResolver(): LSResourceResolver  [API 8+]
+ getTypeInfoProvider(): TypeInfoProvider  [API 8+]
+ setContentHandler(ContentHandler): void  [API 8+]
+ setErrorHandler(ErrorHandler): void  [API 8+]
- setFeature(String, boolean): void  [API 8+]
- setProperty(String, Object): void  [API 8+]
+ setResourceResolver(LSResourceResolver): void  [API 8+]
```


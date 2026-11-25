# javax.xml.transform.sax

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## SAXResult — declaration; API 8+ (0 +, 8 *, 0 ~, 0 -)

```
* SAXResult()  [API 8+]
* SAXResult(ContentHandler)  [API 8+]
* getHandler(): ContentHandler  [API 8+]
* getLexicalHandler(): LexicalHandler  [API 8+]
* getSystemId(): String  [API 8+]
* setHandler(ContentHandler): void  [API 8+]
* setLexicalHandler(LexicalHandler): void  [API 8+]
* setSystemId(String): void  [API 8+]
```

## SAXSource — declaration; API 8+ (0 +, 8 *, 0 ~, 2 -)

```
* SAXSource()  [API 8+]
* SAXSource(InputSource)  [API 8+]
- SAXSource(XMLReader, InputSource)  [API 8+]
* getInputSource(): InputSource  [API 8+]
* getSystemId(): String  [API 8+]
* getXMLReader(): XMLReader  [API 8+]
* setInputSource(InputSource): void  [API 8+]
* setSystemId(String): void  [API 8+]
* setXMLReader(XMLReader): void  [API 8+]
- sourceToInputSource(Source): InputSource [static]  [API 8+]
```

## SAXTransformerFactory — declaration; API 8+ (7 +, 0 *, 0 ~, 0 -)

```
+ SAXTransformerFactory()  [API 8+]
+ newTemplatesHandler(): TemplatesHandler  [API 8+]
+ newTransformerHandler(): TransformerHandler  [API 8+]
+ newTransformerHandler(Source): TransformerHandler  [API 8+]
+ newTransformerHandler(Templates): TransformerHandler  [API 8+]
+ newXMLFilter(Source): XMLFilter  [API 8+]
+ newXMLFilter(Templates): XMLFilter  [API 8+]
```

## TemplatesHandler — declaration; API 8+ (3 +, 0 *, 0 ~, 0 -)

```
+ getSystemId(): String  [API 8+]
+ getTemplates(): Templates  [API 8+]
+ setSystemId(String): void  [API 8+]
```

## TransformerHandler — declaration; API 8+ (4 +, 0 *, 0 ~, 0 -)

```
+ getSystemId(): String  [API 8+]
+ getTransformer(): Transformer  [API 8+]
+ setResult(Result): void  [API 8+]
+ setSystemId(String): void  [API 8+]
```


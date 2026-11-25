# javax.xml.transform.stream

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## StreamResult — framework (a2w.xml.transform.stream.StreamResult); API 8+ (12 +, 0 *, 0 ~, 0 -)

```
+ StreamResult()  [API 8+]
+ StreamResult(File)  [API 8+]
+ StreamResult(OutputStream)  [API 8+]
+ StreamResult(String)  [API 8+]
+ StreamResult(Writer)  [API 8+]
+ getOutputStream(): OutputStream  [API 8+]
+ getSystemId(): String  [API 8+]
+ getWriter(): Writer  [API 8+]
+ setOutputStream(OutputStream): void  [API 8+]
+ setSystemId(File): void  [API 8+]
+ setSystemId(String): void  [API 8+]
+ setWriter(Writer): void  [API 8+]
```

## StreamSource — framework (a2w.xml.transform.stream.StreamSource); API 8+ (16 +, 0 *, 0 ~, 0 -)

```
+ StreamSource()  [API 8+]
+ StreamSource(File)  [API 8+]
+ StreamSource(InputStream)  [API 8+]
+ StreamSource(InputStream, String)  [API 8+]
+ StreamSource(Reader)  [API 8+]
+ StreamSource(Reader, String)  [API 8+]
+ StreamSource(String)  [API 8+]
+ getInputStream(): InputStream  [API 8+]
+ getPublicId(): String  [API 8+]
+ getReader(): Reader  [API 8+]
+ getSystemId(): String  [API 8+]
+ setInputStream(InputStream): void  [API 8+]
+ setPublicId(String): void  [API 8+]
+ setReader(Reader): void  [API 8+]
+ setSystemId(File): void  [API 8+]
+ setSystemId(String): void  [API 8+]
```


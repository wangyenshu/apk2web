# org.apache.http.entity

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractHttpEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ AbstractHttpEntity()  [API 1-22, removed in 23]
+ chunked [field]  [API 1-22, removed in 23]
+ contentEncoding [field]  [API 1-22, removed in 23]
+ contentType [field]  [API 1-22, removed in 23]
+ setChunked(boolean): void  [API 1-22, removed in 23]
+ setContentEncoding(Header): void  [API 1-22, removed in 23]
+ setContentEncoding(String): void  [API 1-22, removed in 23]
+ setContentType(Header): void  [API 1-22, removed in 23]
+ setContentType(String): void  [API 1-22, removed in 23]
```

## BasicHttpEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ BasicHttpEntity()  [API 1-22, removed in 23]
+ setContent(InputStream): void  [API 1-22, removed in 23]
+ setContentLength(long): void  [API 1-22, removed in 23]
```

## BufferedHttpEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BufferedHttpEntity(HttpEntity)  [API 1-22, removed in 23]
```

## ByteArrayEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ ByteArrayEntity(byte[])  [API 1-22, removed in 23]
+ content [field]  [API 1-22, removed in 23]
```

## ContentLengthStrategy — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ determineLength(HttpMessage): long  [API 1-22, removed in 23]
```

## ContentProducer — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ writeTo(OutputStream): void  [API 1-22, removed in 23]
```

## EntityTemplate — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ EntityTemplate(ContentProducer)  [API 1-22, removed in 23]
```

## FileEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ FileEntity(File, String)  [API 1-22, removed in 23]
+ file [field]  [API 1-22, removed in 23]
```

## HttpEntityWrapper — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ HttpEntityWrapper(HttpEntity)  [API 1-22, removed in 23]
+ wrappedEntity [field]  [API 1-22, removed in 23]
```

## InputStreamEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ InputStreamEntity(InputStream, long)  [API 1-22, removed in 23]
```

## SerializableEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ SerializableEntity(Serializable, boolean)  [API 1-22, removed in 23]
```

## StringEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ StringEntity(String)  [API 1-22, removed in 23]
+ StringEntity(String, String)  [API 1-22, removed in 23]
+ content [field]  [API 1-22, removed in 23]
```


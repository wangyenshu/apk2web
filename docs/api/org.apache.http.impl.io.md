# org.apache.http.impl.io

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractMessageParser — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ AbstractMessageParser(SessionInputBuffer, LineParser, HttpParams)  [API 1-22, removed in 23]
+ lineParser [field]  [API 1-22, removed in 23]
+ parseHead(SessionInputBuffer): HttpMessage  [API 1-22, removed in 23]
+ parseHeaders(SessionInputBuffer, int, int, LineParser): Header[]  [API 1-22, removed in 23]
```

## AbstractMessageWriter — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ AbstractMessageWriter(SessionOutputBuffer, LineFormatter, HttpParams)  [API 1-22, removed in 23]
+ lineBuf [field]  [API 1-22, removed in 23]
+ lineFormatter [field]  [API 1-22, removed in 23]
+ sessionBuffer [field]  [API 1-22, removed in 23]
+ writeHeadLine(HttpMessage): void  [API 1-22, removed in 23]
```

## AbstractSessionInputBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ AbstractSessionInputBuffer()  [API 1-22, removed in 23]
+ fillBuffer(): int  [API 1-22, removed in 23]
+ hasBufferedData(): boolean  [API 1-22, removed in 23]
+ init(InputStream, int, HttpParams): void  [API 1-22, removed in 23]
```

## AbstractSessionOutputBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ AbstractSessionOutputBuffer()  [API 1-22, removed in 23]
+ flushBuffer(): void  [API 1-22, removed in 23]
+ init(OutputStream, int, HttpParams): void  [API 1-22, removed in 23]
```

## ChunkedInputStream — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ ChunkedInputStream(SessionInputBuffer)  [API 1-22, removed in 23]
+ getFooters(): Header[]  [API 1-22, removed in 23]
```

## ChunkedOutputStream — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ ChunkedOutputStream(SessionOutputBuffer)  [API 1-22, removed in 23]
+ ChunkedOutputStream(SessionOutputBuffer, int)  [API 1-22, removed in 23]
+ finish(): void  [API 1-22, removed in 23]
+ flushCache(): void  [API 1-22, removed in 23]
+ flushCacheWithAppend(byte[], int, int): void  [API 1-22, removed in 23]
+ writeClosingChunk(): void  [API 1-22, removed in 23]
```

## ContentLengthInputStream — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ ContentLengthInputStream(SessionInputBuffer, long)  [API 1-22, removed in 23]
```

## ContentLengthOutputStream — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ ContentLengthOutputStream(SessionOutputBuffer, long)  [API 1-22, removed in 23]
```

## HttpRequestParser — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ HttpRequestParser(SessionInputBuffer, LineParser, HttpRequestFactory, HttpParams)  [API 1-22, removed in 23]
```

## HttpRequestWriter — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ HttpRequestWriter(SessionOutputBuffer, LineFormatter, HttpParams)  [API 1-22, removed in 23]
```

## HttpResponseParser — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ HttpResponseParser(SessionInputBuffer, LineParser, HttpResponseFactory, HttpParams)  [API 1-22, removed in 23]
```

## HttpResponseWriter — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ HttpResponseWriter(SessionOutputBuffer, LineFormatter, HttpParams)  [API 1-22, removed in 23]
```

## HttpTransportMetricsImpl — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ HttpTransportMetricsImpl()  [API 1-22, removed in 23]
+ incrementBytesTransferred(long): void  [API 1-22, removed in 23]
+ setBytesTransferred(long): void  [API 1-22, removed in 23]
```

## IdentityInputStream — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ IdentityInputStream(SessionInputBuffer)  [API 1-22, removed in 23]
```

## IdentityOutputStream — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ IdentityOutputStream(SessionOutputBuffer)  [API 1-22, removed in 23]
```

## SocketInputBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ SocketInputBuffer(Socket, int, HttpParams)  [API 1-22, removed in 23]
```

## SocketOutputBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ SocketOutputBuffer(Socket, int, HttpParams)  [API 1-22, removed in 23]
```


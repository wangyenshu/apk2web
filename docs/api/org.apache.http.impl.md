# org.apache.http.impl

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractHttpClientConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ AbstractHttpClientConnection()  [API 1-22, removed in 23]
+ assertOpen(): void  [API 1-22, removed in 23]
+ createEntityDeserializer(): EntityDeserializer  [API 1-22, removed in 23]
+ createEntitySerializer(): EntitySerializer  [API 1-22, removed in 23]
+ createHttpResponseFactory(): HttpResponseFactory  [API 1-22, removed in 23]
+ createRequestWriter(SessionOutputBuffer, HttpParams): HttpMessageWriter  [API 1-22, removed in 23]
+ createResponseParser(SessionInputBuffer, HttpResponseFactory, HttpParams): HttpMessageParser  [API 1-22, removed in 23]
+ doFlush(): void  [API 1-22, removed in 23]
+ init(SessionInputBuffer, SessionOutputBuffer, HttpParams): void  [API 1-22, removed in 23]
```

## AbstractHttpServerConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ AbstractHttpServerConnection()  [API 1-22, removed in 23]
+ assertOpen(): void  [API 1-22, removed in 23]
+ createEntityDeserializer(): EntityDeserializer  [API 1-22, removed in 23]
+ createEntitySerializer(): EntitySerializer  [API 1-22, removed in 23]
+ createHttpRequestFactory(): HttpRequestFactory  [API 1-22, removed in 23]
+ createRequestParser(SessionInputBuffer, HttpRequestFactory, HttpParams): HttpMessageParser  [API 1-22, removed in 23]
+ createResponseWriter(SessionOutputBuffer, HttpParams): HttpMessageWriter  [API 1-22, removed in 23]
+ doFlush(): void  [API 1-22, removed in 23]
+ init(SessionInputBuffer, SessionOutputBuffer, HttpParams): void  [API 1-22, removed in 23]
```

## DefaultConnectionReuseStrategy — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ DefaultConnectionReuseStrategy()  [API 1-22, removed in 23]
+ createTokenIterator(HeaderIterator): TokenIterator  [API 1-22, removed in 23]
```

## DefaultHttpClientConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultHttpClientConnection()  [API 1-22, removed in 23]
```

## DefaultHttpRequestFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultHttpRequestFactory()  [API 1-22, removed in 23]
```

## DefaultHttpResponseFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ DefaultHttpResponseFactory()  [API 1-22, removed in 23]
+ DefaultHttpResponseFactory(ReasonPhraseCatalog)  [API 1-22, removed in 23]
+ determineLocale(HttpContext): Locale  [API 1-22, removed in 23]
+ reasonCatalog [field]  [API 1-22, removed in 23]
```

## DefaultHttpServerConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultHttpServerConnection()  [API 1-22, removed in 23]
```

## EnglishReasonPhraseCatalog — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ EnglishReasonPhraseCatalog()  [API 1-22, removed in 23]
```

## HttpConnectionMetricsImpl — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ HttpConnectionMetricsImpl(HttpTransportMetrics, HttpTransportMetrics)  [API 1-22, removed in 23]
+ incrementRequestCount(): void  [API 1-22, removed in 23]
+ incrementResponseCount(): void  [API 1-22, removed in 23]
+ setMetric(String, Object): void  [API 1-22, removed in 23]
```

## NoConnectionReuseStrategy — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ NoConnectionReuseStrategy()  [API 1-22, removed in 23]
```

## SocketHttpClientConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ SocketHttpClientConnection()  [API 1-22, removed in 23]
+ assertNotOpen(): void  [API 1-22, removed in 23]
+ bind(Socket, HttpParams): void  [API 1-22, removed in 23]
+ createSessionInputBuffer(Socket, int, HttpParams): SessionInputBuffer  [API 1-22, removed in 23]
+ createSessionOutputBuffer(Socket, int, HttpParams): SessionOutputBuffer  [API 1-22, removed in 23]
+ getSocket(): Socket  [API 1-22, removed in 23]
```

## SocketHttpServerConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ SocketHttpServerConnection()  [API 1-22, removed in 23]
+ assertNotOpen(): void  [API 1-22, removed in 23]
+ bind(Socket, HttpParams): void  [API 1-22, removed in 23]
+ createHttpDataReceiver(Socket, int, HttpParams): SessionInputBuffer  [API 1-22, removed in 23]
+ createHttpDataTransmitter(Socket, int, HttpParams): SessionOutputBuffer  [API 1-22, removed in 23]
+ getSocket(): Socket  [API 1-22, removed in 23]
```


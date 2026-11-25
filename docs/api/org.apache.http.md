# org.apache.http

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ConnectionClosedException — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ ConnectionClosedException(String)  [API 1-22, removed in 23]
```

## ConnectionReuseStrategy — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ keepAlive(HttpResponse, HttpContext): boolean  [API 1-22, removed in 23]
```

## FormattedHeader — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ getBuffer(): CharArrayBuffer  [API 1-22, removed in 23]
+ getValuePos(): int  [API 1-22, removed in 23]
```

## Header — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ getElements(): HeaderElement[]  [API 1-22, removed in 23]
+ getName(): String  [API 1-22, removed in 23]
+ getValue(): String  [API 1-22, removed in 23]
```

## HeaderElement — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ getName(): String  [API 1-22, removed in 23]
+ getParameter(int): NameValuePair  [API 1-22, removed in 23]
+ getParameterByName(String): NameValuePair  [API 1-22, removed in 23]
+ getParameterCount(): int  [API 1-22, removed in 23]
+ getParameters(): NameValuePair[]  [API 1-22, removed in 23]
+ getValue(): String  [API 1-22, removed in 23]
```

## HeaderElementIterator — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ nextElement(): HeaderElement  [API 1-22, removed in 23]
```

## HeaderIterator — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ nextHeader(): Header  [API 1-22, removed in 23]
```

## HttpClientConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ flush(): void  [API 1-22, removed in 23]
+ isResponseAvailable(int): boolean  [API 1-22, removed in 23]
+ receiveResponseEntity(HttpResponse): void  [API 1-22, removed in 23]
+ receiveResponseHeader(): HttpResponse  [API 1-22, removed in 23]
+ sendRequestEntity(HttpEntityEnclosingRequest): void  [API 1-22, removed in 23]
+ sendRequestHeader(HttpRequest): void  [API 1-22, removed in 23]
```

## HttpConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 1-22, removed in 23]
+ getMetrics(): HttpConnectionMetrics  [API 1-22, removed in 23]
+ getSocketTimeout(): int  [API 1-22, removed in 23]
+ isOpen(): boolean  [API 1-22, removed in 23]
+ isStale(): boolean  [API 1-22, removed in 23]
+ setSocketTimeout(int): void  [API 1-22, removed in 23]
+ shutdown(): void  [API 1-22, removed in 23]
```

## HttpConnectionMetrics — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ getMetric(String): Object  [API 1-22, removed in 23]
+ getReceivedBytesCount(): long  [API 1-22, removed in 23]
+ getRequestCount(): long  [API 1-22, removed in 23]
+ getResponseCount(): long  [API 1-22, removed in 23]
+ getSentBytesCount(): long  [API 1-22, removed in 23]
+ reset(): void  [API 1-22, removed in 23]
```

## HttpEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ consumeContent(): void  [API 1-22, removed in 23]
+ getContent(): InputStream  [API 1-22, removed in 23]
+ getContentEncoding(): Header  [API 1-22, removed in 23]
+ getContentLength(): long  [API 1-22, removed in 23]
+ getContentType(): Header  [API 1-22, removed in 23]
+ isChunked(): boolean  [API 1-22, removed in 23]
+ isRepeatable(): boolean  [API 1-22, removed in 23]
+ isStreaming(): boolean  [API 1-22, removed in 23]
+ writeTo(OutputStream): void  [API 1-22, removed in 23]
```

## HttpEntityEnclosingRequest — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ expectContinue(): boolean  [API 1-22, removed in 23]
+ getEntity(): HttpEntity  [API 1-22, removed in 23]
+ setEntity(HttpEntity): void  [API 1-22, removed in 23]
```

## HttpException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ HttpException()  [API 1-22, removed in 23]
+ HttpException(String)  [API 1-22, removed in 23]
+ HttpException(String, Throwable)  [API 1-22, removed in 23]
```

## HttpHost — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ HttpHost(HttpHost)  [API 1-22, removed in 23]
+ HttpHost(String)  [API 1-22, removed in 23]
+ HttpHost(String, int)  [API 1-22, removed in 23]
+ HttpHost(String, int, String)  [API 1-22, removed in 23]
+ getHostName(): String  [API 1-22, removed in 23]
+ getPort(): int  [API 1-22, removed in 23]
+ getSchemeName(): String  [API 1-22, removed in 23]
+ hostname [field]  [API 1-22, removed in 23]
+ lcHostname [field]  [API 1-22, removed in 23]
+ port [field]  [API 1-22, removed in 23]
+ schemeName [field]  [API 1-22, removed in 23]
+ toHostString(): String  [API 1-22, removed in 23]
+ toURI(): String  [API 1-22, removed in 23]
```

## HttpInetConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ getLocalAddress(): InetAddress  [API 1-22, removed in 23]
+ getLocalPort(): int  [API 1-22, removed in 23]
+ getRemoteAddress(): InetAddress  [API 1-22, removed in 23]
+ getRemotePort(): int  [API 1-22, removed in 23]
```

## HttpMessage — bundled library; API 1-22, removed in 23, deprecated in 22 (17 +, 0 *, 0 ~, 0 -)

```
+ addHeader(Header): void  [API 1-22, removed in 23]
+ addHeader(String, String): void  [API 1-22, removed in 23]
+ containsHeader(String): boolean  [API 1-22, removed in 23]
+ getAllHeaders(): Header[]  [API 1-22, removed in 23]
+ getFirstHeader(String): Header  [API 1-22, removed in 23]
+ getHeaders(String): Header[]  [API 1-22, removed in 23]
+ getLastHeader(String): Header  [API 1-22, removed in 23]
+ getParams(): HttpParams  [API 1-22, removed in 23]
+ getProtocolVersion(): ProtocolVersion  [API 1-22, removed in 23]
+ headerIterator(): HeaderIterator  [API 1-22, removed in 23]
+ headerIterator(String): HeaderIterator  [API 1-22, removed in 23]
+ removeHeader(Header): void  [API 1-22, removed in 23]
+ removeHeaders(String): void  [API 1-22, removed in 23]
+ setHeader(Header): void  [API 1-22, removed in 23]
+ setHeader(String, String): void  [API 1-22, removed in 23]
+ setHeaders(Header[]): void  [API 1-22, removed in 23]
+ setParams(HttpParams): void  [API 1-22, removed in 23]
```

## HttpRequest — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ getRequestLine(): RequestLine  [API 1-22, removed in 23]
```

## HttpRequestFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ newHttpRequest(RequestLine): HttpRequest  [API 1-22, removed in 23]
+ newHttpRequest(String, String): HttpRequest  [API 1-22, removed in 23]
```

## HttpRequestInterceptor — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ process(HttpRequest, HttpContext): void  [API 1-22, removed in 23]
```

## HttpResponse — bundled library; API 1-22, removed in 23, deprecated in 22 (10 +, 0 *, 0 ~, 0 -)

```
+ getEntity(): HttpEntity  [API 1-22, removed in 23]
+ getLocale(): Locale  [API 1-22, removed in 23]
+ getStatusLine(): StatusLine  [API 1-22, removed in 23]
+ setEntity(HttpEntity): void  [API 1-22, removed in 23]
+ setLocale(Locale): void  [API 1-22, removed in 23]
+ setReasonPhrase(String): void  [API 1-22, removed in 23]
+ setStatusCode(int): void  [API 1-22, removed in 23]
+ setStatusLine(ProtocolVersion, int): void  [API 1-22, removed in 23]
+ setStatusLine(ProtocolVersion, int, String): void  [API 1-22, removed in 23]
+ setStatusLine(StatusLine): void  [API 1-22, removed in 23]
```

## HttpResponseFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ newHttpResponse(ProtocolVersion, int, HttpContext): HttpResponse  [API 1-22, removed in 23]
+ newHttpResponse(StatusLine, HttpContext): HttpResponse  [API 1-22, removed in 23]
```

## HttpResponseInterceptor — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ process(HttpResponse, HttpContext): void  [API 1-22, removed in 23]
```

## HttpServerConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ flush(): void  [API 1-22, removed in 23]
+ receiveRequestEntity(HttpEntityEnclosingRequest): void  [API 1-22, removed in 23]
+ receiveRequestHeader(): HttpRequest  [API 1-22, removed in 23]
+ sendResponseEntity(HttpResponse): void  [API 1-22, removed in 23]
+ sendResponseHeader(HttpResponse): void  [API 1-22, removed in 23]
```

## HttpStatus — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## HttpVersion — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ HttpVersion(int, int)  [API 1-22, removed in 23]
```

## MalformedChunkCodingException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ MalformedChunkCodingException()  [API 1-22, removed in 23]
+ MalformedChunkCodingException(String)  [API 1-22, removed in 23]
```

## MethodNotSupportedException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ MethodNotSupportedException(String)  [API 1-22, removed in 23]
+ MethodNotSupportedException(String, Throwable)  [API 1-22, removed in 23]
```

## NameValuePair — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ getName(): String  [API 1-22, removed in 23]
+ getValue(): String  [API 1-22, removed in 23]
```

## NoHttpResponseException — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ NoHttpResponseException(String)  [API 1-22, removed in 23]
```

## ParseException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ ParseException()  [API 1-22, removed in 23]
+ ParseException(String)  [API 1-22, removed in 23]
```

## ProtocolException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ ProtocolException()  [API 1-22, removed in 23]
+ ProtocolException(String)  [API 1-22, removed in 23]
+ ProtocolException(String, Throwable)  [API 1-22, removed in 23]
```

## ProtocolVersion — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ ProtocolVersion(String, int, int)  [API 1-22, removed in 23]
+ compareToVersion(ProtocolVersion): int  [API 1-22, removed in 23]
+ forVersion(int, int): ProtocolVersion  [API 1-22, removed in 23]
+ getMajor(): int  [API 1-22, removed in 23]
+ getMinor(): int  [API 1-22, removed in 23]
+ getProtocol(): String  [API 1-22, removed in 23]
+ greaterEquals(ProtocolVersion): boolean  [API 1-22, removed in 23]
+ isComparable(ProtocolVersion): boolean  [API 1-22, removed in 23]
+ lessEquals(ProtocolVersion): boolean  [API 1-22, removed in 23]
+ major [field]  [API 1-22, removed in 23]
+ minor [field]  [API 1-22, removed in 23]
+ protocol [field]  [API 1-22, removed in 23]
```

## ReasonPhraseCatalog — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ getReason(int, Locale): String  [API 1-22, removed in 23]
```

## RequestLine — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ getMethod(): String  [API 1-22, removed in 23]
+ getProtocolVersion(): ProtocolVersion  [API 1-22, removed in 23]
+ getUri(): String  [API 1-22, removed in 23]
```

## StatusLine — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ getProtocolVersion(): ProtocolVersion  [API 1-22, removed in 23]
+ getReasonPhrase(): String  [API 1-22, removed in 23]
+ getStatusCode(): int  [API 1-22, removed in 23]
```

## TokenIterator — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ nextToken(): String  [API 1-22, removed in 23]
```

## UnsupportedHttpVersionException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ UnsupportedHttpVersionException()  [API 1-22, removed in 23]
+ UnsupportedHttpVersionException(String)  [API 1-22, removed in 23]
```


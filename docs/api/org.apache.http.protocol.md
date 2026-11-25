# org.apache.http.protocol

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BasicHttpContext — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicHttpContext()  [API 1-22, removed in 23]
+ BasicHttpContext(HttpContext)  [API 1-22, removed in 23]
```

## BasicHttpProcessor — bundled library; API 1-22, removed in 23, deprecated in 22 (8 +, 0 *, 0 ~, 0 -)

```
+ BasicHttpProcessor()  [API 1-22, removed in 23]
+ addInterceptor(HttpRequestInterceptor): void  [API 1-22, removed in 23]
+ addInterceptor(HttpRequestInterceptor, int): void  [API 1-22, removed in 23]
+ addInterceptor(HttpResponseInterceptor): void  [API 1-22, removed in 23]
+ addInterceptor(HttpResponseInterceptor, int): void  [API 1-22, removed in 23]
+ clearInterceptors(): void  [API 1-22, removed in 23]
+ copy(): BasicHttpProcessor  [API 1-22, removed in 23]
+ copyInterceptors(BasicHttpProcessor): void  [API 1-22, removed in 23]
+ requestInterceptors [field]  [API 1-22, removed in 23]
+ responseInterceptors [field]  [API 1-22, removed in 23]
```

## DefaultedHttpContext — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ DefaultedHttpContext(HttpContext, HttpContext)  [API 1-22, removed in 23]
+ getDefaults(): HttpContext  [API 1-22, removed in 23]
```

## ExecutionContext — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## HTTP — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ isWhitespace(char): boolean  [API 1-22, removed in 23]
```

## HttpContext — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ getAttribute(String): Object  [API 1-22, removed in 23]
+ removeAttribute(String): Object  [API 1-22, removed in 23]
+ setAttribute(String, Object): void  [API 1-22, removed in 23]
```

## HttpDateGenerator — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ HttpDateGenerator()  [API 1-22, removed in 23]
+ getCurrentDate(): String  [API 1-22, removed in 23]
```

## HttpExpectationVerifier — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ verify(HttpRequest, HttpResponse, HttpContext): void  [API 1-22, removed in 23]
```

## HttpProcessor — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## HttpRequestExecutor — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ HttpRequestExecutor()  [API 1-22, removed in 23]
+ canResponseHaveBody(HttpRequest, HttpResponse): boolean  [API 1-22, removed in 23]
+ doReceiveResponse(HttpRequest, HttpClientConnection, HttpContext): HttpResponse  [API 1-22, removed in 23]
+ doSendRequest(HttpRequest, HttpClientConnection, HttpContext): HttpResponse  [API 1-22, removed in 23]
+ execute(HttpRequest, HttpClientConnection, HttpContext): HttpResponse  [API 1-22, removed in 23]
+ postProcess(HttpResponse, HttpProcessor, HttpContext): void  [API 1-22, removed in 23]
+ preProcess(HttpRequest, HttpProcessor, HttpContext): void  [API 1-22, removed in 23]
```

## HttpRequestHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ handle(HttpRequest, HttpResponse, HttpContext): void  [API 1-22, removed in 23]
```

## HttpRequestHandlerRegistry — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ HttpRequestHandlerRegistry()  [API 1-22, removed in 23]
+ matchUriRequestPattern(String, String): boolean  [API 1-22, removed in 23, deprecated in 15]
+ register(String, HttpRequestHandler): void  [API 1-22, removed in 23]
+ setHandlers(Map): void  [API 1-22, removed in 23]
+ unregister(String): void  [API 1-22, removed in 23]
```

## HttpRequestHandlerResolver — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ lookup(String): HttpRequestHandler  [API 1-22, removed in 23]
```

## HttpRequestInterceptorList — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ addRequestInterceptor(HttpRequestInterceptor): void  [API 1-22, removed in 23]
+ addRequestInterceptor(HttpRequestInterceptor, int): void  [API 1-22, removed in 23]
+ clearRequestInterceptors(): void  [API 1-22, removed in 23]
+ getRequestInterceptor(int): HttpRequestInterceptor  [API 1-22, removed in 23]
+ getRequestInterceptorCount(): int  [API 1-22, removed in 23]
+ removeRequestInterceptorByClass(Class): void  [API 1-22, removed in 23]
+ setInterceptors(List): void  [API 1-22, removed in 23]
```

## HttpResponseInterceptorList — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ addResponseInterceptor(HttpResponseInterceptor): void  [API 1-22, removed in 23]
+ addResponseInterceptor(HttpResponseInterceptor, int): void  [API 1-22, removed in 23]
+ clearResponseInterceptors(): void  [API 1-22, removed in 23]
+ getResponseInterceptor(int): HttpResponseInterceptor  [API 1-22, removed in 23]
+ getResponseInterceptorCount(): int  [API 1-22, removed in 23]
+ removeResponseInterceptorByClass(Class): void  [API 1-22, removed in 23]
+ setInterceptors(List): void  [API 1-22, removed in 23]
```

## HttpService — bundled library; API 1-22, removed in 23, deprecated in 22 (11 +, 0 *, 0 ~, 0 -)

```
+ HttpService(HttpProcessor, ConnectionReuseStrategy, HttpResponseFactory)  [API 1-22, removed in 23]
+ doService(HttpRequest, HttpResponse, HttpContext): void  [API 1-22, removed in 23]
+ getParams(): HttpParams  [API 1-22, removed in 23]
+ handleException(HttpException, HttpResponse): void  [API 1-22, removed in 23]
+ handleRequest(HttpServerConnection, HttpContext): void  [API 1-22, removed in 23]
+ setConnReuseStrategy(ConnectionReuseStrategy): void  [API 1-22, removed in 23]
+ setExpectationVerifier(HttpExpectationVerifier): void  [API 1-22, removed in 23]
+ setHandlerResolver(HttpRequestHandlerResolver): void  [API 1-22, removed in 23]
+ setHttpProcessor(HttpProcessor): void  [API 1-22, removed in 23]
+ setParams(HttpParams): void  [API 1-22, removed in 23]
+ setResponseFactory(HttpResponseFactory): void  [API 1-22, removed in 23]
```

## RequestConnControl — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RequestConnControl()  [API 1-22, removed in 23]
```

## RequestContent — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RequestContent()  [API 1-22, removed in 23]
```

## RequestDate — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RequestDate()  [API 1-22, removed in 23]
```

## RequestExpectContinue — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RequestExpectContinue()  [API 1-22, removed in 23]
```

## RequestTargetHost — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RequestTargetHost()  [API 1-22, removed in 23]
```

## RequestUserAgent — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ RequestUserAgent()  [API 1-22, removed in 23]
```

## ResponseConnControl — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ ResponseConnControl()  [API 1-22, removed in 23]
```

## ResponseContent — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ ResponseContent()  [API 1-22, removed in 23]
```

## ResponseDate — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ ResponseDate()  [API 1-22, removed in 23]
```

## ResponseServer — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ ResponseServer()  [API 1-22, removed in 23]
```

## SyncBasicHttpContext — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ SyncBasicHttpContext(HttpContext)  [API 1-22, removed in 23]
```

## UriPatternMatcher — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ UriPatternMatcher()  [API 1-22, removed in 23]
+ lookup(String): Object  [API 1-22, removed in 23]
+ matchUriRequestPattern(String, String): boolean  [API 1-22, removed in 23]
+ register(String, Object): void  [API 1-22, removed in 23]
+ setHandlers(Map): void  [API 1-22, removed in 23]
+ unregister(String): void  [API 1-22, removed in 23]
```


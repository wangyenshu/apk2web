# org.apache.http.conn

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BasicEofSensorWatcher — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicEofSensorWatcher(ManagedClientConnection, boolean)  [API 1-22, removed in 23]
+ attemptReuse [field]  [API 1-22, removed in 23]
+ managedConn [field]  [API 1-22, removed in 23]
```

## BasicManagedEntity — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicManagedEntity(HttpEntity, ManagedClientConnection, boolean)  [API 1-22, removed in 23]
+ attemptReuse [field]  [API 1-22, removed in 23]
+ managedConn [field]  [API 1-22, removed in 23]
+ releaseManagedConnection(): void  [API 1-22, removed in 23]
```

## ClientConnectionManager — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ closeExpiredConnections(): void  [API 1-22, removed in 23]
+ closeIdleConnections(long, TimeUnit): void  [API 1-22, removed in 23]
+ getSchemeRegistry(): SchemeRegistry  [API 1-22, removed in 23]
+ releaseConnection(ManagedClientConnection, long, TimeUnit): void  [API 1-22, removed in 23]
+ requestConnection(HttpRoute, Object): ClientConnectionRequest  [API 1-22, removed in 23]
+ shutdown(): void  [API 1-22, removed in 23]
```

## ClientConnectionManagerFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ newInstance(HttpParams, SchemeRegistry): ClientConnectionManager  [API 1-22, removed in 23]
```

## ClientConnectionOperator — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ createConnection(): OperatedClientConnection  [API 1-22, removed in 23]
+ openConnection(OperatedClientConnection, HttpHost, InetAddress, HttpContext, HttpParams): void  [API 1-22, removed in 23]
+ updateSecureConnection(OperatedClientConnection, HttpHost, HttpContext, HttpParams): void  [API 1-22, removed in 23]
```

## ClientConnectionRequest — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ abortRequest(): void  [API 1-22, removed in 23]
+ getConnection(long, TimeUnit): ManagedClientConnection  [API 1-22, removed in 23]
```

## ConnectTimeoutException — bundled library; API 1+, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ ConnectTimeoutException()  [API 1+]
+ ConnectTimeoutException(String)  [API 1+]
```

## ConnectionKeepAliveStrategy — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ getKeepAliveDuration(HttpResponse, HttpContext): long  [API 1-22, removed in 23]
```

## ConnectionPoolTimeoutException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ ConnectionPoolTimeoutException()  [API 1-22, removed in 23]
+ ConnectionPoolTimeoutException(String)  [API 1-22, removed in 23]
```

## ConnectionReleaseTrigger — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ abortConnection(): void  [API 1-22, removed in 23]
+ releaseConnection(): void  [API 1-22, removed in 23]
```

## EofSensorInputStream — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ EofSensorInputStream(InputStream, EofSensorWatcher)  [API 1-22, removed in 23]
+ checkAbort(): void  [API 1-22, removed in 23]
+ checkClose(): void  [API 1-22, removed in 23]
+ checkEOF(int): void  [API 1-22, removed in 23]
+ isReadAllowed(): boolean  [API 1-22, removed in 23]
+ wrappedStream [field]  [API 1-22, removed in 23]
```

## EofSensorWatcher — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ eofDetected(InputStream): boolean  [API 1-22, removed in 23]
+ streamAbort(InputStream): boolean  [API 1-22, removed in 23]
+ streamClosed(InputStream): boolean  [API 1-22, removed in 23]
```

## HttpHostConnectException — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ HttpHostConnectException(HttpHost, ConnectException)  [API 1-22, removed in 23]
+ getHost(): HttpHost  [API 1-22, removed in 23]
```

## ManagedClientConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (13 +, 0 *, 0 ~, 0 -)

```
+ getRoute(): HttpRoute  [API 1-22, removed in 23]
+ getSSLSession(): SSLSession  [API 1-22, removed in 23]
+ getState(): Object  [API 1-22, removed in 23]
+ isMarkedReusable(): boolean  [API 1-22, removed in 23]
+ isSecure(): boolean  [API 1-22, removed in 23]
+ layerProtocol(HttpContext, HttpParams): void  [API 1-22, removed in 23]
+ markReusable(): void  [API 1-22, removed in 23]
+ open(HttpRoute, HttpContext, HttpParams): void  [API 1-22, removed in 23]
+ setIdleDuration(long, TimeUnit): void  [API 1-22, removed in 23]
+ setState(Object): void  [API 1-22, removed in 23]
+ tunnelProxy(HttpHost, boolean, HttpParams): void  [API 1-22, removed in 23]
+ tunnelTarget(boolean, HttpParams): void  [API 1-22, removed in 23]
+ unmarkReusable(): void  [API 1-22, removed in 23]
```

## MultihomePlainSocketFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ getSocketFactory(): MultihomePlainSocketFactory  [API 1-22, removed in 23]
```

## OperatedClientConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ getSocket(): Socket  [API 1-22, removed in 23]
+ getTargetHost(): HttpHost  [API 1-22, removed in 23]
+ isSecure(): boolean  [API 1-22, removed in 23]
+ openCompleted(boolean, HttpParams): void  [API 1-22, removed in 23]
+ opening(Socket, HttpHost): void  [API 1-22, removed in 23]
+ update(Socket, HttpHost, boolean, HttpParams): void  [API 1-22, removed in 23]
```


# org.apache.http.impl.conn

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractClientConnAdapter — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ AbstractClientConnAdapter(ClientConnectionManager, OperatedClientConnection)  [API 1-22, removed in 23]
+ assertNotAborted(): void  [API 1-22, removed in 23]
+ assertValid(OperatedClientConnection): void  [API 1-22, removed in 23]
+ detach(): void  [API 1-22, removed in 23]
+ getManager(): ClientConnectionManager  [API 1-22, removed in 23]
+ getWrappedConnection(): OperatedClientConnection  [API 1-22, removed in 23]
```

## AbstractPoolEntry — bundled library; API 1-22, removed in 23, deprecated in 22 (8 +, 0 *, 0 ~, 0 -)

```
+ AbstractPoolEntry(ClientConnectionOperator, HttpRoute)  [API 1-22, removed in 23]
+ connOperator [field]  [API 1-22, removed in 23]
+ connection [field]  [API 1-22, removed in 23]
+ getState(): Object  [API 1-22, removed in 23]
+ layerProtocol(HttpContext, HttpParams): void  [API 1-22, removed in 23]
+ open(HttpRoute, HttpContext, HttpParams): void  [API 1-22, removed in 23]
+ route [field]  [API 1-22, removed in 23]
+ setState(Object): void  [API 1-22, removed in 23]
+ shutdownEntry(): void  [API 1-22, removed in 23]
+ state [field]  [API 1-22, removed in 23]
+ tracker [field]  [API 1-22, removed in 23]
+ tunnelProxy(HttpHost, boolean, HttpParams): void  [API 1-22, removed in 23]
+ tunnelTarget(boolean, HttpParams): void  [API 1-22, removed in 23]
```

## AbstractPooledConnAdapter — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ AbstractPooledConnAdapter(ClientConnectionManager, AbstractPoolEntry)  [API 1-22, removed in 23]
+ assertAttached(): void  [API 1-22, removed in 23]
+ poolEntry [field]  [API 1-22, removed in 23]
```

## DefaultClientConnection — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultClientConnection()  [API 1-22, removed in 23]
```

## DefaultClientConnectionOperator — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ DefaultClientConnectionOperator(SchemeRegistry)  [API 1-22, removed in 23]
+ prepareSocket(Socket, HttpContext, HttpParams): void  [API 1-22, removed in 23]
+ schemeRegistry [field]  [API 1-22, removed in 23]
```

## DefaultHttpRoutePlanner — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultHttpRoutePlanner(SchemeRegistry)  [API 1-22, removed in 23]
+ schemeRegistry [field]  [API 1-22, removed in 23]
```

## DefaultResponseParser — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DefaultResponseParser(SessionInputBuffer, LineParser, HttpResponseFactory, HttpParams)  [API 1-22, removed in 23]
```

## IdleConnectionHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ IdleConnectionHandler()  [API 1-22, removed in 23]
+ add(HttpConnection, long, TimeUnit): void  [API 1-22, removed in 23]
+ closeExpiredConnections(): void  [API 1-22, removed in 23]
+ closeIdleConnections(long): void  [API 1-22, removed in 23]
+ remove(HttpConnection): boolean  [API 1-22, removed in 23]
+ removeAll(): void  [API 1-22, removed in 23]
```

## LoggingSessionInputBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ LoggingSessionInputBuffer(SessionInputBuffer, Wire)  [API 1-22, removed in 23]
```

## LoggingSessionOutputBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ LoggingSessionOutputBuffer(SessionOutputBuffer, Wire)  [API 1-22, removed in 23]
```

## ProxySelectorRoutePlanner — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ ProxySelectorRoutePlanner(SchemeRegistry, ProxySelector)  [API 1-22, removed in 23]
+ chooseProxy(List, HttpHost, HttpRequest, HttpContext): Proxy  [API 1-22, removed in 23]
+ determineProxy(HttpHost, HttpRequest, HttpContext): HttpHost  [API 1-22, removed in 23]
+ getHost(InetSocketAddress): String  [API 1-22, removed in 23]
+ getProxySelector(): ProxySelector  [API 1-22, removed in 23]
+ proxySelector [field]  [API 1-22, removed in 23]
+ schemeRegistry [field]  [API 1-22, removed in 23]
+ setProxySelector(ProxySelector): void  [API 1-22, removed in 23]
```

## SingleClientConnManager — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ SingleClientConnManager(HttpParams, SchemeRegistry)  [API 1-22, removed in 23]
+ alwaysShutDown [field]  [API 1-22, removed in 23]
+ assertStillUp(): void  [API 1-22, removed in 23]
+ connOperator [field]  [API 1-22, removed in 23]
+ connectionExpiresTime [field]  [API 1-22, removed in 23]
+ createConnectionOperator(SchemeRegistry): ClientConnectionOperator  [API 1-22, removed in 23]
+ getConnection(HttpRoute, Object): ManagedClientConnection  [API 1-22, removed in 23]
+ isShutDown [field]  [API 1-22, removed in 23]
+ lastReleaseTime [field]  [API 1-22, removed in 23]
+ managedConn [field]  [API 1-22, removed in 23]
+ revokeConnection(): void  [API 1-22, removed in 23]
+ schemeRegistry [field]  [API 1-22, removed in 23]
+ uniquePoolEntry [field]  [API 1-22, removed in 23]
```

## SingleClientConnManager.ConnAdapter — bundled library; API 1-22, removed in 23 (1 +, 0 *, 0 ~, 0 -)

```
+ ConnAdapter(SingleClientConnManager, SingleClientConnManager.PoolEntry, HttpRoute)  [API 1-22, removed in 23]
```

## SingleClientConnManager.PoolEntry — bundled library; API 1-22, removed in 23 (3 +, 0 *, 0 ~, 0 -)

```
+ PoolEntry(SingleClientConnManager)  [API 1-22, removed in 23]
+ close(): void  [API 1-22, removed in 23]
+ shutdown(): void  [API 1-22, removed in 23]
```

## Wire — bundled library; API 1-22, removed in 23, deprecated in 22 (12 +, 0 *, 0 ~, 0 -)

```
+ Wire(Log)  [API 1-22, removed in 23]
+ enabled(): boolean  [API 1-22, removed in 23]
+ input(InputStream): void  [API 1-22, removed in 23]
+ input(String): void  [API 1-22, removed in 23]
+ input(byte[]): void  [API 1-22, removed in 23]
+ input(byte[], int, int): void  [API 1-22, removed in 23]
+ input(int): void  [API 1-22, removed in 23]
+ output(InputStream): void  [API 1-22, removed in 23]
+ output(String): void  [API 1-22, removed in 23]
+ output(byte[]): void  [API 1-22, removed in 23]
+ output(byte[], int, int): void  [API 1-22, removed in 23]
+ output(int): void  [API 1-22, removed in 23]
```


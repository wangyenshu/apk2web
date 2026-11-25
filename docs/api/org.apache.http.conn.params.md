# org.apache.http.conn.params

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ConnConnectionPNames — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## ConnConnectionParamBean — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ ConnConnectionParamBean(HttpParams)  [API 1-22, removed in 23]
+ setMaxStatusLineGarbage(int): void  [API 1-22, removed in 23]
```

## ConnManagerPNames — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## ConnManagerParamBean — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ ConnManagerParamBean(HttpParams)  [API 1-22, removed in 23]
+ setConnectionsPerRoute(ConnPerRouteBean): void  [API 1-22, removed in 23]
+ setMaxTotalConnections(int): void  [API 1-22, removed in 23]
+ setTimeout(long): void  [API 1-22, removed in 23]
```

## ConnManagerParams — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ ConnManagerParams()  [API 1-22, removed in 23]
+ getMaxConnectionsPerRoute(HttpParams): ConnPerRoute  [API 1-22, removed in 23]
+ getMaxTotalConnections(HttpParams): int  [API 1-22, removed in 23]
+ getTimeout(HttpParams): long  [API 1-22, removed in 23]
+ setMaxConnectionsPerRoute(HttpParams, ConnPerRoute): void  [API 1-22, removed in 23]
+ setMaxTotalConnections(HttpParams, int): void  [API 1-22, removed in 23]
+ setTimeout(HttpParams, long): void  [API 1-22, removed in 23]
```

## ConnPerRoute — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ getMaxForRoute(HttpRoute): int  [API 1-22, removed in 23]
```

## ConnPerRouteBean — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ ConnPerRouteBean()  [API 1-22, removed in 23]
+ ConnPerRouteBean(int)  [API 1-22, removed in 23]
+ getDefaultMax(): int  [API 1-22, removed in 23]
+ setDefaultMaxPerRoute(int): void  [API 1-22, removed in 23]
+ setMaxForRoute(HttpRoute, int): void  [API 1-22, removed in 23]
+ setMaxForRoutes(Map): void  [API 1-22, removed in 23]
```

## ConnRoutePNames — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## ConnRouteParamBean — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ ConnRouteParamBean(HttpParams)  [API 1-22, removed in 23]
+ setDefaultProxy(HttpHost): void  [API 1-22, removed in 23]
+ setForcedRoute(HttpRoute): void  [API 1-22, removed in 23]
+ setLocalAddress(InetAddress): void  [API 1-22, removed in 23]
```

## ConnRouteParams — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ getDefaultProxy(HttpParams): HttpHost  [API 1-22, removed in 23]
+ getForcedRoute(HttpParams): HttpRoute  [API 1-22, removed in 23]
+ getLocalAddress(HttpParams): InetAddress  [API 1-22, removed in 23]
+ setDefaultProxy(HttpParams, HttpHost): void  [API 1-22, removed in 23]
+ setForcedRoute(HttpParams, HttpRoute): void  [API 1-22, removed in 23]
+ setLocalAddress(HttpParams, InetAddress): void  [API 1-22, removed in 23]
```


# org.apache.http.conn.routing

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BasicRouteDirector — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ BasicRouteDirector()  [API 1-22, removed in 23]
+ directStep(RouteInfo, RouteInfo): int  [API 1-22, removed in 23]
+ firstStep(RouteInfo): int  [API 1-22, removed in 23]
+ proxiedStep(RouteInfo, RouteInfo): int  [API 1-22, removed in 23]
```

## HttpRoute — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ HttpRoute(HttpHost)  [API 1-22, removed in 23]
+ HttpRoute(HttpHost, InetAddress, HttpHost, boolean)  [API 1-22, removed in 23]
+ HttpRoute(HttpHost, InetAddress, HttpHost, boolean, RouteInfo.TunnelType, RouteInfo.LayerType)  [API 1-22, removed in 23]
+ HttpRoute(HttpHost, InetAddress, HttpHost[], boolean, RouteInfo.TunnelType, RouteInfo.LayerType)  [API 1-22, removed in 23]
+ HttpRoute(HttpHost, InetAddress, boolean)  [API 1-22, removed in 23]
```

## HttpRouteDirector — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ nextStep(RouteInfo, RouteInfo): int  [API 1-22, removed in 23]
```

## HttpRoutePlanner — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ determineRoute(HttpHost, HttpRequest, HttpContext): HttpRoute  [API 1-22, removed in 23]
```

## RouteInfo — bundled library; API 1-22, removed in 23, deprecated in 22 (10 +, 0 *, 0 ~, 0 -)

```
+ getHopCount(): int  [API 1-22, removed in 23]
+ getHopTarget(int): HttpHost  [API 1-22, removed in 23]
+ getLayerType(): RouteInfo.LayerType  [API 1-22, removed in 23]
+ getLocalAddress(): InetAddress  [API 1-22, removed in 23]
+ getProxyHost(): HttpHost  [API 1-22, removed in 23]
+ getTargetHost(): HttpHost  [API 1-22, removed in 23]
+ getTunnelType(): RouteInfo.TunnelType  [API 1-22, removed in 23]
+ isLayered(): boolean  [API 1-22, removed in 23]
+ isSecure(): boolean  [API 1-22, removed in 23]
+ isTunnelled(): boolean  [API 1-22, removed in 23]
```

## RouteInfo.LayerType — bundled library; API 1-22, removed in 23 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): RouteInfo.LayerType  [API 1-22, removed in 23]
+ values(): RouteInfo.LayerType[]  [API 1-22, removed in 23]
```

## RouteInfo.TunnelType — bundled library; API 1-22, removed in 23 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): RouteInfo.TunnelType  [API 1-22, removed in 23]
+ values(): RouteInfo.TunnelType[]  [API 1-22, removed in 23]
```

## RouteTracker — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ RouteTracker(HttpHost, InetAddress)  [API 1-22, removed in 23]
+ RouteTracker(HttpRoute)  [API 1-22, removed in 23]
+ connectProxy(HttpHost, boolean): void  [API 1-22, removed in 23]
+ connectTarget(boolean): void  [API 1-22, removed in 23]
+ isConnected(): boolean  [API 1-22, removed in 23]
+ layerProtocol(boolean): void  [API 1-22, removed in 23]
+ toRoute(): HttpRoute  [API 1-22, removed in 23]
+ tunnelProxy(HttpHost, boolean): void  [API 1-22, removed in 23]
+ tunnelTarget(boolean): void  [API 1-22, removed in 23]
```


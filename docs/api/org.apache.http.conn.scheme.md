# org.apache.http.conn.scheme

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## HostNameResolver — bundled library; API 1+, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ resolve(String): InetAddress  [API 1+]
```

## LayeredSocketFactory — bundled library; API 1+, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ createSocket(Socket, String, int, boolean): Socket  [API 1+]
```

## PlainSocketFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ PlainSocketFactory()  [API 1-22, removed in 23]
+ PlainSocketFactory(HostNameResolver)  [API 1-22, removed in 23]
+ getSocketFactory(): PlainSocketFactory  [API 1-22, removed in 23]
```

## Scheme — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ Scheme(String, SocketFactory, int)  [API 1-22, removed in 23]
+ getDefaultPort(): int  [API 1-22, removed in 23]
+ getName(): String  [API 1-22, removed in 23]
+ getSocketFactory(): SocketFactory  [API 1-22, removed in 23]
+ isLayered(): boolean  [API 1-22, removed in 23]
+ resolvePort(int): int  [API 1-22, removed in 23]
```

## SchemeRegistry — bundled library; API 1-22, removed in 23, deprecated in 22 (8 +, 0 *, 0 ~, 0 -)

```
+ SchemeRegistry()  [API 1-22, removed in 23]
+ get(String): Scheme  [API 1-22, removed in 23]
+ getScheme(HttpHost): Scheme  [API 1-22, removed in 23]
+ getScheme(String): Scheme  [API 1-22, removed in 23]
+ getSchemeNames(): List  [API 1-22, removed in 23]
+ register(Scheme): Scheme  [API 1-22, removed in 23]
+ setItems(Map): void  [API 1-22, removed in 23]
+ unregister(String): Scheme  [API 1-22, removed in 23]
```

## SocketFactory — bundled library; API 1+, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ connectSocket(Socket, String, int, InetAddress, int, HttpParams): Socket  [API 1+]
+ createSocket(): Socket  [API 1+]
+ isSecure(Socket): boolean  [API 1+]
```


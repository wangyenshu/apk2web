# javax.net

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ServerSocketFactory — declaration; API 1+ (3 +, 0 *, 0 ~, 3 -)

```
- ServerSocketFactory()  [API 1+]
- createServerSocket(): ServerSocket  [API 1+]
+ createServerSocket(int): ServerSocket  [API 1+]
+ createServerSocket(int, int): ServerSocket  [API 1+]
+ createServerSocket(int, int, InetAddress): ServerSocket  [API 1+]
- getDefault(): ServerSocketFactory [static]  [API 1+]
```

## SocketFactory — declaration; API 1+ (4 +, 0 *, 0 ~, 3 -)

```
- SocketFactory()  [API 1+]
- createSocket(): Socket  [API 1+]
+ createSocket(InetAddress, int): Socket  [API 1+]
+ createSocket(InetAddress, int, InetAddress, int): Socket  [API 1+]
+ createSocket(String, int): Socket  [API 1+]
+ createSocket(String, int, InetAddress, int): Socket  [API 1+]
- getDefault(): SocketFactory [static]  [API 1+]
```


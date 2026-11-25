# android.net.ipsec.ike.exceptions

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## IkeException — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## IkeIOException — declaration; API 33+ (0 +, 0 *, 0 ~, 3 -)

```
- IkeIOException(IOException)  [API 33+]
- getCause(): IOException  [API 33+]
- initCause(Throwable): Throwable  [API 33+]
```

## IkeInternalException — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- IkeInternalException(String, Throwable)  [API 31+]
- IkeInternalException(Throwable)  [API 31+]
```

## IkeNetworkLostException — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- IkeNetworkLostException(Network)  [API 31+]
- getNetwork(): Network  [API 31+]
```

## IkeNonProtocolException — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## IkeProtocolException — declaration; API 31+ (0 +, 0 *, 0 ~, 1 -)

```
- getErrorType(): int  [API 31+]
```

## IkeTimeoutException — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ IkeTimeoutException(String)  [API 33+]
```

## InvalidKeException — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- InvalidKeException(int)  [API 31+]
- getDhGroup(): int  [API 31+]
```

## InvalidMajorVersionException — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- InvalidMajorVersionException(byte)  [API 31+]
- getMajorVersion(): byte  [API 31+]
```

## InvalidSelectorsException — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- InvalidSelectorsException(int, byte[])  [API 31+]
- getIpSecPacketInfo(): byte[]  [API 31+]
- getIpSecSpi(): int  [API 31+]
```


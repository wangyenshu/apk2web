# android.se.omapi

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Channel — declaration; API 28+ (7 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 28+]
+ getSelectResponse(): byte[]  [API 28+]
+ getSession(): Session  [API 28+]
+ isBasicChannel(): boolean  [API 28+]
+ isOpen(): boolean  [API 28+]
+ selectNext(): boolean  [API 28+]
+ transmit(byte[]): byte[]  [API 28+]
```

## Reader — declaration; API 28+ (5 +, 0 *, 0 ~, 0 -)

```
+ closeSessions(): void  [API 28+]
+ getName(): String  [API 28+]
+ getSEService(): SEService  [API 28+]
+ isSecureElementPresent(): boolean  [API 28+]
+ openSession(): Session  [API 28+]
```

## SEService — declaration; API 28+ (5 +, 0 *, 0 ~, 1 -)

```
- SEService(Context, Executor, SEService.OnConnectedListener)  [API 28+]
+ getReaders(): Reader[]  [API 28+]
+ getUiccReader(int): Reader  [API 30+]
+ getVersion(): String  [API 28+]
+ isConnected(): boolean  [API 28+]
+ shutdown(): void  [API 28+]
```

## SEService.OnConnectedListener — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onConnected(): void  [API 28+]
```

## Session — declaration; API 28+ (9 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 28+]
+ closeChannels(): void  [API 28+]
+ getATR(): byte[]  [API 28+]
+ getReader(): Reader  [API 28+]
+ isClosed(): boolean  [API 28+]
+ openBasicChannel(byte[]): Channel  [API 28+]
+ openBasicChannel(byte[], byte): Channel  [API 28+]
+ openLogicalChannel(byte[]): Channel  [API 28+]
+ openLogicalChannel(byte[], byte): Channel  [API 28+]
```


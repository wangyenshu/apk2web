# android.net.ssl

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## SSLEngines — declaration; API 29+ (0 +, 0 *, 0 ~, 3 -)

```
- exportKeyingMaterial(SSLEngine, String, byte[], int): byte[] [static]  [API 31+]
- isSupportedEngine(SSLEngine): boolean [static]  [API 29+]
- setUseSessionTickets(SSLEngine, boolean): void [static]  [API 29+]
```

## SSLSockets — declaration; API 29+ (0 +, 0 *, 0 ~, 3 -)

```
- exportKeyingMaterial(SSLSocket, String, byte[], int): byte[] [static]  [API 31+]
- isSupportedSocket(SSLSocket): boolean [static]  [API 29+]
- setUseSessionTickets(SSLSocket, boolean): void [static]  [API 29+]
```


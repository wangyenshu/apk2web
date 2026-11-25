# android.util.proto

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ProtoOutputStream — declaration; API 30+ (0 +, 0 *, 0 ~, 25 -)

```
- ProtoOutputStream()  [API 30+]
- ProtoOutputStream(OutputStream)  [API 30+]
- ProtoOutputStream(int)  [API 30+]
- checkFieldId(long, long): int [static]  [API 30+]
- dump(String): void  [API 30+]
- end(long): void  [API 30+]
- flush(): void  [API 30+]
- getBytes(): byte[]  [API 30+]
- getFieldCountString(long): String [static]  [API 30+]
- getFieldIdString(long): String [static]  [API 30+]
- getFieldTypeString(long): String [static]  [API 30+]
- getRawSize(): int  [API 30+]
- getWireTypeString(int): String [static]  [API 30+]
- makeFieldId(int, long): long [static]  [API 30+]
- makeToken(int, boolean, int, int, int): long [static]  [API 30+]
- start(long): long  [API 30+]
- token2String(long): String [static]  [API 30+]
- write(long, String): void  [API 30+]
- write(long, boolean): void  [API 30+]
- write(long, byte[]): void  [API 30+]
- write(long, double): void  [API 30+]
- write(long, float): void  [API 30+]
- write(long, int): void  [API 30+]
- write(long, long): void  [API 30+]
- writeTag(int, int): void  [API 30+]
```


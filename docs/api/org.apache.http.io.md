# org.apache.http.io

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## HttpMessageParser — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ parse(): HttpMessage  [API 1-22, removed in 23]
```

## HttpMessageWriter — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ write(HttpMessage): void  [API 1-22, removed in 23]
```

## HttpTransportMetrics — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ getBytesTransferred(): long  [API 1-22, removed in 23]
+ reset(): void  [API 1-22, removed in 23]
```

## SessionInputBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ getMetrics(): HttpTransportMetrics  [API 1-22, removed in 23]
+ isDataAvailable(int): boolean  [API 1-22, removed in 23]
+ read(): int  [API 1-22, removed in 23]
+ read(byte[]): int  [API 1-22, removed in 23]
+ read(byte[], int, int): int  [API 1-22, removed in 23]
+ readLine(): String  [API 1-22, removed in 23]
+ readLine(CharArrayBuffer): int  [API 1-22, removed in 23]
```

## SessionOutputBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ flush(): void  [API 1-22, removed in 23]
+ getMetrics(): HttpTransportMetrics  [API 1-22, removed in 23]
+ write(byte[]): void  [API 1-22, removed in 23]
+ write(byte[], int, int): void  [API 1-22, removed in 23]
+ write(int): void  [API 1-22, removed in 23]
+ writeLine(CharArrayBuffer): void  [API 1-22, removed in 23]
+ writeLine(String): void  [API 1-22, removed in 23]
```


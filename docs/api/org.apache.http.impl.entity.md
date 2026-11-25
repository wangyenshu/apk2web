# org.apache.http.impl.entity

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## EntityDeserializer — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ EntityDeserializer(ContentLengthStrategy)  [API 1-22, removed in 23]
+ deserialize(SessionInputBuffer, HttpMessage): HttpEntity  [API 1-22, removed in 23]
+ doDeserialize(SessionInputBuffer, HttpMessage): BasicHttpEntity  [API 1-22, removed in 23]
```

## EntitySerializer — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ EntitySerializer(ContentLengthStrategy)  [API 1-22, removed in 23]
+ doSerialize(SessionOutputBuffer, HttpMessage): OutputStream  [API 1-22, removed in 23]
+ serialize(SessionOutputBuffer, HttpMessage, HttpEntity): void  [API 1-22, removed in 23]
```

## LaxContentLengthStrategy — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ LaxContentLengthStrategy()  [API 1-22, removed in 23]
```

## StrictContentLengthStrategy — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ StrictContentLengthStrategy()  [API 1-22, removed in 23]
```


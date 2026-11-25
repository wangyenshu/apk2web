# android.adservices.topics

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## EncryptedTopic — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* EncryptedTopic(byte[], String, byte[])  [API 35+]
* equals(Object): boolean  [API 35+]
* getEncapsulatedKey(): byte[]  [API 35+]
* getEncryptedTopic(): byte[]  [API 35+]
* getKeyIdentifier(): String  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
```

## GetTopicsRequest — declaration; API 34+ (0 +, 1 *, 0 ~, 1 -)

```
* getAdsSdkName(): String  [API 34+]
- shouldRecordObservation(): boolean  [API 34+]
```

## GetTopicsRequest.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder()  [API 34+]
* build(): GetTopicsRequest  [API 34+]
* setAdsSdkName(String): GetTopicsRequest.Builder  [API 34+]
- setShouldRecordObservation(boolean): GetTopicsRequest.Builder  [API 34+]
```

## GetTopicsResponse — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getEncryptedTopics(): List  [API 35+]
* getTopics(): List  [API 34+]
* hashCode(): int  [API 34+]
```

## GetTopicsResponse.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(List)  [API 34+, deprecated in 36]
* Builder(List, List)  [API 35+]
* build(): GetTopicsResponse  [API 34+]
```

## Topic — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
* Topic(long, long, int)  [API 34+]
* equals(Object): boolean  [API 34+]
* getModelVersion(): long  [API 34+]
* getTaxonomyVersion(): long  [API 34+]
* getTopicId(): int  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
```

## TopicsManager — declaration; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- get(Context): TopicsManager [static]  [API 34+]
- getTopics(GetTopicsRequest, Executor, OutcomeReceiver): void  [API 34+]
```


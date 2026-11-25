# android.adservices.adid

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AdId — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* AdId(String, boolean)  [API 34+]
* equals(Object): boolean  [API 34+]
* getAdId(): String  [API 34+]
* hashCode(): int  [API 34+]
* isLimitAdTrackingEnabled(): boolean  [API 34+]
* toString(): String  [API 34+]
```

## AdIdManager — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- get(Context): AdIdManager [static]  [API 34+]
- getAdId(Executor, AdServicesOutcomeReceiver): void  [API 35+]
- getAdId(Executor, OutcomeReceiver): void  [API 34+]
```


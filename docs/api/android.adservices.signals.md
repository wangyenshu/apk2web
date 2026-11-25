# android.adservices.signals

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ProtectedSignalsManager — declaration; API 35+ (0 +, 0 *, 0 ~, 2 -)

```
- get(Context): ProtectedSignalsManager [static]  [API 35+]
- updateSignals(UpdateSignalsRequest, Executor, OutcomeReceiver): void  [API 35+]
```

## UpdateSignalsRequest — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getUpdateUri(): Uri  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
```

## UpdateSignalsRequest.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(Uri)  [API 35+]
* build(): UpdateSignalsRequest  [API 35+]
* setUpdateUri(Uri): UpdateSignalsRequest.Builder  [API 35+]
```


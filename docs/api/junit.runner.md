# junit.runner

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BaseTestRunner — declaration; API 1+ (4 +, 0 *, 0 ~, 21 -)

```
- BaseTestRunner()  [API 1+]
- clearStatus(): void  [API 1+]
- elapsedTimeAsString(long): String  [API 1+]
- extractClassName(String): String  [API 1+]
- getFilteredTrace(String): String  [API 1+]
- getFilteredTrace(Throwable): String  [API 1+]
- getLoader(): TestSuiteLoader  [API 1+, deprecated in 16]
- getPreference(String): String  [API 1+]
- getPreference(String, int): int  [API 1+]
- getPreferences(): Properties  [API 1+]
- getTest(String): Test  [API 1+]
- inVAJava(): boolean  [API 1+, deprecated in 16]
- loadSuiteClass(String): Class  [API 1+]
- processArguments(String[]): String  [API 1+]
+ runFailed(String): void  [API 1+]
- savePreferences(): void  [API 1+]
- setLoading(boolean): void  [API 1+]
- setPreference(String, String): void  [API 1+]
- setPreferences(Properties): void  [API 1+]
- showStackRaw(): boolean  [API 1+]
+ testEnded(String): void  [API 1+]
+ testFailed(int, Test, Throwable): void  [API 1+]
+ testStarted(String): void  [API 1+]
- truncate(String): String  [API 1+]
- useReloadingTestSuiteLoader(): boolean  [API 1+]
```

## TestSuiteLoader — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ load(String): Class  [API 1+]
+ reload(Class): Class  [API 1+]
```

## Version — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- id(): String  [API 1+]
```


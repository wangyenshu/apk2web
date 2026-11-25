# android.app.appsearch.functions

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AppFunctionManager — declaration; API 36+ (0 +, 0 *, 0 ~, 1 -)

```
- executeAppFunction(ExecuteAppFunctionRequest, Executor, Consumer): void  [API 36+]
```

## AppFunctionService — declaration; API 36+ (1 +, 0 *, 0 ~, 1 -)

```
- AppFunctionService()  [API 36+]
+ onExecuteFunction(ExecuteAppFunctionRequest, Consumer): void  [API 36+]
```

## ExecuteAppFunctionRequest — declaration; API 36+ (0 +, 3 *, 0 ~, 2 -)

```
* getExtras(): Bundle  [API 36+]
- getFunctionIdentifier(): String  [API 36+]
* getParameters(): GenericDocument  [API 36+]
* getSha256Certificate(): byte[]  [API 36+]
- getTargetPackageName(): String  [API 36+]
```

## ExecuteAppFunctionRequest.Builder — declaration; API 36+ (0 +, 4 *, 0 ~, 1 -)

```
- Builder(String, String)  [API 36+]
* build(): ExecuteAppFunctionRequest  [API 36+]
* setExtras(Bundle): ExecuteAppFunctionRequest.Builder  [API 36+]
* setParameters(GenericDocument): ExecuteAppFunctionRequest.Builder  [API 36+]
* setSha256Certificate(byte[]): ExecuteAppFunctionRequest.Builder  [API 36+]
```

## ExecuteAppFunctionResponse — declaration; API 36+ (0 +, 2 *, 0 ~, 0 -)

```
* getExtras(): Bundle  [API 36+]
* getResult(): GenericDocument  [API 36+]
```

## ExecuteAppFunctionResponse.Builder — declaration; API 36+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 36+]
* build(): ExecuteAppFunctionResponse  [API 36+]
* setExtras(Bundle): ExecuteAppFunctionResponse.Builder  [API 36+]
* setResult(GenericDocument): ExecuteAppFunctionResponse.Builder  [API 36+]
```


# android.app.appfunctions

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

## ExecuteAppFunctionRequest — declaration; API 36+ (0 +, 4 *, 0 ~, 0 -)

```
* getExtras(): Bundle  [API 36+]
* getFunctionIdentifier(): String  [API 36+]
* getParameters(): GenericDocument  [API 36+]
* getTargetPackageName(): String  [API 36+]
```

## ExecuteAppFunctionRequest.Builder — declaration; API 36+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder(String, String)  [API 36+]
* build(): ExecuteAppFunctionRequest  [API 36+]
* setExtras(Bundle): ExecuteAppFunctionRequest.Builder  [API 36+]
* setParameters(GenericDocument): ExecuteAppFunctionRequest.Builder  [API 36+]
```

## ExecuteAppFunctionResponse — declaration; API 36+ (0 +, 0 *, 0 ~, 7 -)

```
- getErrorMessage(): String  [API 36+]
- getExtras(): Bundle  [API 36+]
- getResultCode(): int  [API 36+]
- getResultDocument(): GenericDocument  [API 36+]
- isSuccess(): boolean  [API 36+]
- newFailure(int, String, Bundle): ExecuteAppFunctionResponse  [API 36+]
- newSuccess(GenericDocument, Bundle): ExecuteAppFunctionResponse  [API 36+]
```


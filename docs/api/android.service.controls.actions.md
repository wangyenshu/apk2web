# android.service.controls.actions

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BooleanAction — declaration; API 30+ (0 +, 3 *, 0 ~, 1 -)

```
* BooleanAction(String, boolean)  [API 30+]
* BooleanAction(String, boolean, String)  [API 30+]
- getActionType(): int  [API 30+]
* getNewState(): boolean  [API 30+]
```

## CommandAction — declaration; API 30+ (0 +, 2 *, 0 ~, 1 -)

```
* CommandAction(String)  [API 30+]
* CommandAction(String, String)  [API 30+]
- getActionType(): int  [API 30+]
```

## ControlAction — declaration; API 30+ (1 +, 2 *, 0 ~, 2 -)

```
+ getActionType(): int  [API 30+]
* getChallengeValue(): String  [API 30+]
- getErrorAction(): ControlAction [static]  [API 30+]
* getTemplateId(): String  [API 30+]
- isValidResponse(int): boolean [static]  [API 30+]
```

## FloatAction — declaration; API 30+ (0 +, 3 *, 0 ~, 1 -)

```
* FloatAction(String, float)  [API 30+]
* FloatAction(String, float, String)  [API 30+]
- getActionType(): int  [API 30+]
* getNewValue(): float  [API 30+]
```

## ModeAction — declaration; API 30+ (0 +, 3 *, 0 ~, 1 -)

```
* ModeAction(String, int)  [API 30+]
* ModeAction(String, int, String)  [API 30+]
- getActionType(): int  [API 30+]
* getNewMode(): int  [API 30+]
```


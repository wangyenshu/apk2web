# java.lang.runtime

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ObjectMethods — declaration; API 34+ (0 +, 0 *, 0 ~, 1 -)

```
- bootstrap(MethodHandles.Lookup, String, TypeDescriptor, Class, String, MethodHandle[]): Object [static]  [API 34+]
```

## SwitchBootstraps — declaration; API 36+ (0 +, 0 *, 0 ~, 2 -)

```
- enumSwitch(MethodHandles.Lookup, String, MethodType, Object[]): CallSite  [API 36+]
- typeSwitch(MethodHandles.Lookup, String, MethodType, Object[]): CallSite  [API 36+]
```


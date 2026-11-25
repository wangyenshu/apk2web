# android.service.vr

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## VrListenerService — declaration; API 24+ (1 +, 0 *, 0 ~, 3 -)

```
- VrListenerService()  [API 24+]
- isVrModePackageEnabled(Context, ComponentName): boolean [static]  [API 24+]
- onBind(Intent): IBinder  [API 24+]
+ onCurrentVrActivityChanged(ComponentName): void  [API 24+]
```


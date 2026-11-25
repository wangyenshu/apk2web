# android.media.effect

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Effect — declaration; API 14+ (4 +, 0 *, 0 ~, 2 -)

```
- Effect()  [API 14+]
+ apply(int, int, int, int): void  [API 14+]
+ getName(): String  [API 14+]
+ release(): void  [API 14+]
+ setParameter(String, Object): void  [API 14+]
- setUpdateListener(EffectUpdateListener): void  [API 14+]
```

## EffectContext — declaration; API 14+ (0 +, 0 *, 0 ~, 3 -)

```
- createWithCurrentGlContext(): EffectContext [static]  [API 14+]
- getFactory(): EffectFactory  [API 14+]
- release(): void  [API 14+]
```

## EffectFactory — declaration; API 14+ (0 +, 0 *, 0 ~, 2 -)

```
- createEffect(String): Effect  [API 14+]
- isEffectSupported(String): boolean [static]  [API 14+]
```

## EffectUpdateListener — declaration; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ onEffectUpdated(Effect, Object): void  [API 14+]
```


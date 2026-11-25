# javax.security.auth.callback

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Callback — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## CallbackHandler — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ handle(Callback[]): void  [API 1+]
```

## PasswordCallback — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- PasswordCallback(String, boolean)  [API 1+]
- clearPassword(): void  [API 1+]
- getPassword(): char[]  [API 1+]
- getPrompt(): String  [API 1+]
- isEchoOn(): boolean  [API 1+]
- setPassword(char[]): void  [API 1+]
```

## UnsupportedCallbackException — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- UnsupportedCallbackException(Callback)  [API 1+]
- UnsupportedCallbackException(Callback, String)  [API 1+]
- getCallback(): Callback  [API 1+]
```


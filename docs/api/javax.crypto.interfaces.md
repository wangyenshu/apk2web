# javax.crypto.interfaces

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DHKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getParams(): DHParameterSpec  [API 1+]
```

## DHPrivateKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getX(): BigInteger  [API 1+]
```

## DHPublicKey — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getY(): BigInteger  [API 1+]
```

## PBEKey — declaration; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ getIterationCount(): int  [API 1+]
+ getPassword(): char[]  [API 1+]
+ getSalt(): byte[]  [API 1+]
```


# android.net.eap

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## EapAkaInfo — declaration; API 33+ (0 +, 1 *, 0 ~, 0 -)

```
* getReauthId(): byte[]  [API 33+]
```

## EapAkaInfo.Builder — declaration; API 33+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): EapAkaInfo  [API 33+]
* setReauthId(byte[]): EapAkaInfo.Builder  [API 33+]
```

## EapInfo — declaration; API 33+ (0 +, 0 *, 0 ~, 1 -)

```
- getEapMethodType(): int  [API 33+]
```

## EapSessionConfig — declaration; API 31+ (0 +, 1 *, 0 ~, 7 -)

```
- equals(Object): boolean  [API 31+]
- getEapAkaConfig(): EapSessionConfig.EapAkaConfig  [API 31+]
- getEapAkaPrimeConfig(): EapSessionConfig.EapAkaPrimeConfig  [API 31+]
* getEapIdentity(): byte[]  [API 31+]
- getEapMsChapV2Config(): EapSessionConfig.EapMsChapV2Config  [API 31+]
- getEapSimConfig(): EapSessionConfig.EapSimConfig  [API 31+]
- getEapTtlsConfig(): EapSessionConfig.EapTtlsConfig  [API 31+]
- hashCode(): int  [API 31+]
```

## EapSessionConfig.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 6 -)

```
* Builder()  [API 31+]
* build(): EapSessionConfig  [API 31+]
- setEapAkaConfig(int, int): EapSessionConfig.Builder  [API 31+]
- setEapAkaConfig(int, int, EapSessionConfig.EapAkaOption): EapSessionConfig.Builder  [API 33+]
- setEapAkaPrimeConfig(int, int, String, boolean): EapSessionConfig.Builder  [API 31+]
* setEapIdentity(byte[]): EapSessionConfig.Builder  [API 31+]
- setEapMsChapV2Config(String, String): EapSessionConfig.Builder  [API 31+]
- setEapSimConfig(int, int): EapSessionConfig.Builder  [API 31+]
- setEapTtlsConfig(X509Certificate, EapSessionConfig): EapSessionConfig.Builder  [API 31+]
```

## EapSessionConfig.EapAkaConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 5 -)

```
- equals(Object): boolean  [API 31+]
- getAppType(): int  [API 31+]
- getEapAkaOption(): EapSessionConfig.EapAkaOption  [API 33+]
- getSubId(): int  [API 31+]
- hashCode(): int  [API 31+]
```

## EapSessionConfig.EapAkaOption — declaration; API 33+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 33+]
* getReauthId(): byte[]  [API 33+]
* hashCode(): int  [API 33+]
```

## EapSessionConfig.EapAkaOption.Builder — declaration; API 33+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): EapSessionConfig.EapAkaOption  [API 33+]
* setReauthId(byte[]): EapSessionConfig.EapAkaOption.Builder  [API 33+]
```

## EapSessionConfig.EapAkaPrimeConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- allowsMismatchedNetworkNames(): boolean  [API 31+]
- equals(Object): boolean  [API 31+]
- getNetworkName(): String  [API 31+]
- hashCode(): int  [API 31+]
```

## EapSessionConfig.EapMethodConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- equals(Object): boolean  [API 31+]
- getMethodType(): int  [API 31+]
- hashCode(): int  [API 31+]
```

## EapSessionConfig.EapMsChapV2Config — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- equals(Object): boolean  [API 31+]
- getPassword(): String  [API 31+]
- getUsername(): String  [API 31+]
- hashCode(): int  [API 31+]
```

## EapSessionConfig.EapSimConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- equals(Object): boolean  [API 31+]
- getAppType(): int  [API 31+]
- getSubId(): int  [API 31+]
- hashCode(): int  [API 31+]
```

## EapSessionConfig.EapTtlsConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- equals(Object): boolean  [API 31+]
- getInnerEapSessionConfig(): EapSessionConfig  [API 31+]
- getServerCaCert(): X509Certificate  [API 31+]
- hashCode(): int  [API 31+]
```


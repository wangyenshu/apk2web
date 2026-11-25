# org.apache.http.impl.auth

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AuthSchemeBase — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ AuthSchemeBase()  [API 1-22, removed in 23]
+ isProxy(): boolean  [API 1-22, removed in 23]
+ parseChallenge(CharArrayBuffer, int, int): void  [API 1-22, removed in 23]
```

## BasicScheme — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicScheme()  [API 1-22, removed in 23]
+ authenticate(Credentials, String, boolean): Header  [API 1-22, removed in 23]
```

## BasicSchemeFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicSchemeFactory()  [API 1-22, removed in 23]
```

## DigestScheme — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ DigestScheme()  [API 1-22, removed in 23]
+ createCnonce(): String  [API 1-22, removed in 23]
+ overrideParamter(String, String): void  [API 1-22, removed in 23]
```

## DigestSchemeFactory — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ DigestSchemeFactory()  [API 1-22, removed in 23]
```

## NTLMEngine — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ generateType1Msg(String, String): String  [API 1-22, removed in 23]
+ generateType3Msg(String, String, String, String, String): String  [API 1-22, removed in 23]
```

## NTLMEngineException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ NTLMEngineException()  [API 1-22, removed in 23]
+ NTLMEngineException(String)  [API 1-22, removed in 23]
+ NTLMEngineException(String, Throwable)  [API 1-22, removed in 23]
```

## NTLMScheme — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ NTLMScheme(NTLMEngine)  [API 1-22, removed in 23]
```

## RFC2617Scheme — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ RFC2617Scheme()  [API 1-22, removed in 23]
+ getParameters(): Map  [API 1-22, removed in 23]
```

## UnsupportedDigestAlgorithmException — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ UnsupportedDigestAlgorithmException()  [API 1-22, removed in 23]
+ UnsupportedDigestAlgorithmException(String)  [API 1-22, removed in 23]
+ UnsupportedDigestAlgorithmException(String, Throwable)  [API 1-22, removed in 23]
```


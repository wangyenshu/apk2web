# android.hardware.fingerprint

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## FingerprintManager — declaration; API 23+, deprecated in 28 (3 +, 0 *, 0 ~, 0 -)

```
+ authenticate(FingerprintManager.CryptoObject, CancellationSignal, int, FingerprintManager.AuthenticationCallback, Handler): void  [API 23+]
+ hasEnrolledFingerprints(): boolean  [API 23+]
+ isHardwareDetected(): boolean  [API 23+]
```

## FingerprintManager.AuthenticationCallback — declaration; API 23+, deprecated in 28 (5 +, 0 *, 0 ~, 0 -)

```
+ AuthenticationCallback()  [API 23+]
+ onAuthenticationError(int, CharSequence): void  [API 23+]
+ onAuthenticationFailed(): void  [API 23+]
+ onAuthenticationHelp(int, CharSequence): void  [API 23+]
+ onAuthenticationSucceeded(FingerprintManager.AuthenticationResult): void  [API 23+]
```

## FingerprintManager.AuthenticationResult — declaration; API 23+, deprecated in 28 (1 +, 0 *, 0 ~, 0 -)

```
+ getCryptoObject(): FingerprintManager.CryptoObject  [API 23+]
```

## FingerprintManager.CryptoObject — declaration; API 23+, deprecated in 28 (0 +, 6 *, 0 ~, 0 -)

```
* CryptoObject(Cipher)  [API 23+]
* CryptoObject(Mac)  [API 23+]
* CryptoObject(Signature)  [API 23+]
* getCipher(): Cipher  [API 23+]
* getMac(): Mac  [API 23+]
* getSignature(): Signature  [API 23+]
```


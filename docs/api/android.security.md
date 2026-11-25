# android.security

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AppUriAuthenticationPolicy — declaration; API 31+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
- getAppAndUriMappings(): Map  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## AppUriAuthenticationPolicy.Builder — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- Builder()  [API 31+]
- addAppAndUriMapping(String, Uri, String): AppUriAuthenticationPolicy.Builder  [API 31+]
- build(): AppUriAuthenticationPolicy  [API 31+]
```

## AttestedKeyPair — declaration; API 28+ (0 +, 3 *, 0 ~, 0 -)

```
* AttestedKeyPair(KeyPair, List)  [API 30+]
* getAttestationRecord(): List  [API 28+]
* getKeyPair(): KeyPair  [API 28+]
```

## ConfirmationAlreadyPresentingException — declaration; API 28+ (2 +, 0 *, 0 ~, 0 -)

```
+ ConfirmationAlreadyPresentingException()  [API 28+]
+ ConfirmationAlreadyPresentingException(String)  [API 28+]
```

## ConfirmationCallback — declaration; API 28+ (5 +, 0 *, 0 ~, 0 -)

```
+ ConfirmationCallback()  [API 28+]
+ onCanceled(): void  [API 28+]
+ onConfirmed(byte[]): void  [API 28+]
+ onDismissed(): void  [API 28+]
+ onError(Throwable): void  [API 28+]
```

## ConfirmationNotAvailableException — declaration; API 28+ (2 +, 0 *, 0 ~, 0 -)

```
+ ConfirmationNotAvailableException()  [API 28+]
+ ConfirmationNotAvailableException(String)  [API 28+]
```

## ConfirmationPrompt — declaration; API 28+ (0 +, 0 *, 0 ~, 3 -)

```
- cancelPrompt(): void  [API 28+]
- isSupported(Context): boolean [static]  [API 28+]
- presentPrompt(Executor, ConfirmationCallback): void  [API 28+]
```

## ConfirmationPrompt.Builder — declaration; API 28+ (0 +, 0 *, 0 ~, 4 -)

```
- Builder(Context)  [API 28+]
- build(): ConfirmationPrompt  [API 28+]
- setExtraData(byte[]): ConfirmationPrompt.Builder  [API 28+]
- setPromptText(CharSequence): ConfirmationPrompt.Builder  [API 28+]
```

## FileIntegrityManager — framework; API 30+ (0 +, 0 *, 0 ~, 2 -)

```
- isApkVeritySupported(): boolean  [API 30+]
- isAppSourceCertificateTrusted(X509Certificate): boolean  [API 30+, deprecated in 35]
```

## KeyChain — declaration; API 14+ (0 +, 0 *, 0 ~, 12 -)

```
- KeyChain()  [API 14+]
- choosePrivateKeyAlias(Activity, KeyChainAliasCallback, String[], Principal[], String, int, String): void [static]  [API 14+]
- choosePrivateKeyAlias(Activity, KeyChainAliasCallback, String[], Principal[], Uri, String): void [static]  [API 23+]
- createInstallIntent(): Intent [static]  [API 14+]
- createManageCredentialsIntent(AppUriAuthenticationPolicy): Intent [static]  [API 31+]
- getCertificateChain(Context, String): X509Certificate[] [static]  [API 14+]
- getCredentialManagementAppPolicy(Context): AppUriAuthenticationPolicy [static]  [API 31+]
- getPrivateKey(Context, String): PrivateKey [static]  [API 14+]
- isBoundKeyAlgorithm(String): boolean [static]  [API 18+, deprecated in 23]
- isCredentialManagementApp(Context): boolean [static]  [API 31+]
- isKeyAlgorithmSupported(String): boolean [static]  [API 18+]
- removeCredentialManagementApp(Context): boolean [static]  [API 31+]
```

## KeyChainAliasCallback — declaration; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ alias(String): void  [API 14+]
```

## KeyChainException — declaration; API 14+ (4 +, 0 *, 0 ~, 0 -)

```
+ KeyChainException()  [API 14+]
+ KeyChainException(String)  [API 14+]
+ KeyChainException(String, Throwable)  [API 14+]
+ KeyChainException(Throwable)  [API 14+]
```

## KeyPairGeneratorSpec — declaration; API 18+, deprecated in 23 (0 +, 7 *, 0 ~, 3 -)

```
* getAlgorithmParameterSpec(): AlgorithmParameterSpec  [API 19+]
* getContext(): Context  [API 18+]
* getEndDate(): Date  [API 18+]
* getKeySize(): int  [API 19+]
* getKeyType(): String  [API 19+]
- getKeystoreAlias(): String  [API 18+]
* getSerialNumber(): BigInteger  [API 18+]
* getStartDate(): Date  [API 18+]
- getSubjectDN(): X500Principal  [API 18+]
- isEncryptionRequired(): boolean  [API 18+]
```

## KeyPairGeneratorSpec.Builder — declaration; API 18+, deprecated in 23 (0 +, 8 *, 0 ~, 3 -)

```
* Builder(Context)  [API 18+]
* build(): KeyPairGeneratorSpec  [API 18+]
* setAlgorithmParameterSpec(AlgorithmParameterSpec): KeyPairGeneratorSpec.Builder  [API 19+]
- setAlias(String): KeyPairGeneratorSpec.Builder  [API 18+]
- setEncryptionRequired(): KeyPairGeneratorSpec.Builder  [API 18+]
* setEndDate(Date): KeyPairGeneratorSpec.Builder  [API 18+]
* setKeySize(int): KeyPairGeneratorSpec.Builder  [API 19+]
* setKeyType(String): KeyPairGeneratorSpec.Builder  [API 19+]
* setSerialNumber(BigInteger): KeyPairGeneratorSpec.Builder  [API 18+]
* setStartDate(Date): KeyPairGeneratorSpec.Builder  [API 18+]
- setSubject(X500Principal): KeyPairGeneratorSpec.Builder  [API 18+]
```

## KeyStoreException — declaration; API 33+ (0 +, 0 *, 0 ~, 6 -)

```
- getNumericErrorCode(): int  [API 33+]
- getRetryPolicy(): int  [API 33+]
- isSystemError(): boolean  [API 33+]
- isTransientFailure(): boolean  [API 33+]
- requiresUserAuthentication(): boolean  [API 33+]
- toString(): String  [API 33+]
```

## KeyStoreParameter — declaration; API 18+, deprecated in 23 (0 +, 1 *, 0 ~, 0 -)

```
* isEncryptionRequired(): boolean  [API 18+]
```

## KeyStoreParameter.Builder — declaration; API 18+, deprecated in 23 (0 +, 2 *, 0 ~, 1 -)

```
- Builder(Context)  [API 18+]
* build(): KeyStoreParameter  [API 18+]
* setEncryptionRequired(boolean): KeyStoreParameter.Builder  [API 18+]
```

## NetworkSecurityPolicy — declaration; API 23+ (0 +, 0 *, 0 ~, 3 -)

```
- getInstance(): NetworkSecurityPolicy [static]  [API 23+]
- isCleartextTrafficPermitted(): boolean  [API 23+]
- isCleartextTrafficPermitted(String): boolean  [API 24+]
```


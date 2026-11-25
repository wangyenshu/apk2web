# android.security.identity

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AccessControlProfile — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## AccessControlProfile.Builder — declaration; API 30+ (0 +, 0 *, 0 ~, 5 -)

```
- Builder(AccessControlProfileId)  [API 30+]
- build(): AccessControlProfile  [API 30+]
- setReaderCertificate(X509Certificate): AccessControlProfile.Builder  [API 30+]
- setUserAuthenticationRequired(boolean): AccessControlProfile.Builder  [API 30+]
- setUserAuthenticationTimeout(long): AccessControlProfile.Builder  [API 30+]
```

## AccessControlProfileId — declaration; API 30+ (0 +, 2 *, 0 ~, 0 -)

```
* AccessControlProfileId(int)  [API 30+]
* getId(): int  [API 30+]
```

## AlreadyPersonalizedException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ AlreadyPersonalizedException(String)  [API 30+]
+ AlreadyPersonalizedException(String, Throwable)  [API 30+]
```

## AuthenticationKeyMetadata — declaration; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- getExpirationDate(): Instant  [API 34+]
- getUsageCount(): int  [API 34+]
```

## CipherSuiteNotSupportedException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ CipherSuiteNotSupportedException(String)  [API 30+]
+ CipherSuiteNotSupportedException(String, Throwable)  [API 30+]
```

## CredentialDataRequest — declaration; API 33+ (0 +, 7 *, 0 ~, 0 -)

```
* getDeviceSignedEntriesToRequest(): Map  [API 33+]
* getIssuerSignedEntriesToRequest(): Map  [API 33+]
* getReaderSignature(): byte[]  [API 33+]
* getRequestMessage(): byte[]  [API 33+]
* isAllowUsingExhaustedKeys(): boolean  [API 33+]
* isAllowUsingExpiredKeys(): boolean  [API 33+]
* isIncrementUseCount(): boolean  [API 33+]
```

## CredentialDataRequest.Builder — declaration; API 33+ (0 +, 9 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): CredentialDataRequest  [API 33+]
* setAllowUsingExhaustedKeys(boolean): CredentialDataRequest.Builder  [API 33+]
* setAllowUsingExpiredKeys(boolean): CredentialDataRequest.Builder  [API 33+]
* setDeviceSignedEntriesToRequest(Map): CredentialDataRequest.Builder  [API 33+]
* setIncrementUseCount(boolean): CredentialDataRequest.Builder  [API 33+]
* setIssuerSignedEntriesToRequest(Map): CredentialDataRequest.Builder  [API 33+]
* setReaderSignature(byte[]): CredentialDataRequest.Builder  [API 33+]
* setRequestMessage(byte[]): CredentialDataRequest.Builder  [API 33+]
```

## CredentialDataResult — declaration; API 33+ (5 +, 0 *, 0 ~, 1 -)

```
+ getDeviceMac(): byte[]  [API 33+]
+ getDeviceNameSpaces(): byte[]  [API 33+]
- getDeviceSignature(): byte[]  [API 34+]
+ getDeviceSignedEntries(): CredentialDataResult.Entries  [API 33+]
+ getIssuerSignedEntries(): CredentialDataResult.Entries  [API 33+]
+ getStaticAuthenticationData(): byte[]  [API 33+]
```

## CredentialDataResult.Entries — declaration; API 33+ (5 +, 0 *, 0 ~, 0 -)

```
+ getEntry(String, String): byte[]  [API 33+]
+ getEntryNames(String): Collection  [API 33+]
+ getNamespaces(): Collection  [API 33+]
+ getRetrievedEntryNames(String): Collection  [API 33+]
+ getStatus(String, String): int  [API 33+]
```

## DocTypeNotSupportedException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ DocTypeNotSupportedException(String)  [API 30+]
+ DocTypeNotSupportedException(String, Throwable)  [API 30+]
```

## EphemeralPublicKeyNotFoundException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ EphemeralPublicKeyNotFoundException(String)  [API 30+]
+ EphemeralPublicKeyNotFoundException(String, Throwable)  [API 30+]
```

## IdentityCredential — declaration; API 30+ (11 +, 0 *, 0 ~, 7 -)

```
+ createEphemeralKeyPair(): KeyPair  [API 30+, deprecated in 33]
+ decryptMessageFromReader(byte[]): byte[]  [API 30+, deprecated in 33]
- delete(byte[]): byte[]  [API 31+]
+ encryptMessageToReader(byte[]): byte[]  [API 30+, deprecated in 33]
+ getAuthKeysNeedingCertification(): Collection  [API 30+]
+ getAuthenticationDataUsageCount(): int[]  [API 30+, deprecated in 34]
- getAuthenticationKeyMetadata(): List  [API 34+]
+ getCredentialKeyCertificateChain(): Collection  [API 30+]
+ getEntries(byte[], Map, byte[], byte[]): ResultData  [API 30+, deprecated in 33]
- proveOwnership(byte[]): byte[]  [API 31+]
+ setAllowUsingExhaustedKeys(boolean): void  [API 30+, deprecated in 33]
- setAllowUsingExpiredKeys(boolean): void  [API 31+, deprecated in 33]
+ setAvailableAuthenticationKeys(int, int): void  [API 30+, deprecated in 34]
- setAvailableAuthenticationKeys(int, int, long): void  [API 34+]
+ setReaderEphemeralPublicKey(PublicKey): void  [API 30+, deprecated in 33]
- storeStaticAuthenticationData(X509Certificate, Instant, byte[]): void  [API 31+]
+ storeStaticAuthenticationData(X509Certificate, byte[]): void  [API 30+, deprecated in 31]
- update(PersonalizationData): byte[]  [API 31+]
```

## IdentityCredentialException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ IdentityCredentialException(String)  [API 30+]
+ IdentityCredentialException(String, Throwable)  [API 30+]
```

## IdentityCredentialStore — declaration; API 30+ (4 +, 0 *, 0 ~, 3 -)

```
+ createCredential(String, String): WritableIdentityCredential  [API 30+]
- createPresentationSession(int): PresentationSession  [API 33+]
+ deleteCredentialByName(String): byte[]  [API 30+, deprecated in 31]
+ getCredentialByName(String, int): IdentityCredential  [API 30+]
- getDirectAccessInstance(Context): IdentityCredentialStore [static]  [API 30+]
- getInstance(Context): IdentityCredentialStore [static]  [API 30+]
+ getSupportedDocTypes(): String[]  [API 30+]
```

## InvalidReaderSignatureException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ InvalidReaderSignatureException(String)  [API 30+]
+ InvalidReaderSignatureException(String, Throwable)  [API 30+]
```

## InvalidRequestMessageException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ InvalidRequestMessageException(String)  [API 30+]
+ InvalidRequestMessageException(String, Throwable)  [API 30+]
```

## MessageDecryptionException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ MessageDecryptionException(String)  [API 30+]
+ MessageDecryptionException(String, Throwable)  [API 30+]
```

## NoAuthenticationKeyAvailableException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoAuthenticationKeyAvailableException(String)  [API 30+]
+ NoAuthenticationKeyAvailableException(String, Throwable)  [API 30+]
```

## PersonalizationData — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## PersonalizationData.Builder — declaration; API 30+ (0 +, 0 *, 0 ~, 4 -)

```
- Builder()  [API 30+]
- addAccessControlProfile(AccessControlProfile): PersonalizationData.Builder  [API 30+]
- build(): PersonalizationData  [API 30+]
- putEntry(String, String, Collection, byte[]): PersonalizationData.Builder  [API 30+]
```

## PresentationSession — declaration; API 33+ (4 +, 0 *, 0 ~, 0 -)

```
+ getCredentialData(String, CredentialDataRequest): CredentialDataResult  [API 33+]
+ getEphemeralKeyPair(): KeyPair  [API 33+]
+ setReaderEphemeralPublicKey(PublicKey): void  [API 33+]
+ setSessionTranscript(byte[]): void  [API 33+]
```

## ResultData — declaration; API 30+, deprecated in 33 (8 +, 0 *, 0 ~, 0 -)

```
+ getAuthenticatedData(): byte[]  [API 30+]
+ getEntry(String, String): byte[]  [API 30+]
+ getEntryNames(String): Collection  [API 30+]
+ getMessageAuthenticationCode(): byte[]  [API 30+]
+ getNamespaces(): Collection  [API 30+]
+ getRetrievedEntryNames(String): Collection  [API 30+]
+ getStaticAuthenticationData(): byte[]  [API 30+]
+ getStatus(String, String): int  [API 30+]
```

## SessionTranscriptMismatchException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ SessionTranscriptMismatchException(String)  [API 30+]
+ SessionTranscriptMismatchException(String, Throwable)  [API 30+]
```

## UnknownAuthenticationKeyException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnknownAuthenticationKeyException(String)  [API 30+]
+ UnknownAuthenticationKeyException(String, Throwable)  [API 30+]
```

## WritableIdentityCredential — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ getCredentialKeyCertificateChain(byte[]): Collection  [API 30+]
+ personalize(PersonalizationData): byte[]  [API 30+]
```


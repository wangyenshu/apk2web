# android.security.keystore

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BackendBusyException — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- BackendBusyException(long)  [API 31+]
- BackendBusyException(long, String)  [API 31+]
- BackendBusyException(long, String, Throwable)  [API 31+]
- getBackOffHintMillis(): long  [API 31+]
```

## KeyExpiredException — declaration; API 23+ (2 +, 0 *, 0 ~, 1 -)

```
+ KeyExpiredException()  [API 23+]
+ KeyExpiredException(String)  [API 23+]
- KeyExpiredException(String, Throwable)  [API 23+]
```

## KeyGenParameterSpec — declaration; API 23+ (0 +, 27 *, 0 ~, 5 -)

```
* getAlgorithmParameterSpec(): AlgorithmParameterSpec  [API 23+]
* getAttestKeyAlias(): String  [API 31+]
* getAttestationChallenge(): byte[]  [API 24+]
* getBlockModes(): String[]  [API 23+]
* getCertificateNotAfter(): Date  [API 23+]
* getCertificateNotBefore(): Date  [API 23+]
* getCertificateSerialNumber(): BigInteger  [API 23+]
* getCertificateSubject(): X500Principal  [API 23+]
* getDigests(): String[]  [API 23+]
* getEncryptionPaddings(): String[]  [API 23+]
* getKeySize(): int  [API 23+]
* getKeyValidityForConsumptionEnd(): Date  [API 23+]
* getKeyValidityForOriginationEnd(): Date  [API 23+]
* getKeyValidityStart(): Date  [API 23+]
* getKeystoreAlias(): String  [API 23+]
* getMaxUsageCount(): int  [API 31+]
- getMgf1Digests(): Set  [API 35+]
* getPurposes(): int  [API 23+]
* getSignaturePaddings(): String[]  [API 23+]
- getUserAuthenticationType(): int  [API 30+]
* getUserAuthenticationValidityDurationSeconds(): int  [API 23+]
* isDevicePropertiesAttestationIncluded(): boolean  [API 31+]
- isDigestsSpecified(): boolean  [API 23+]
* isInvalidatedByBiometricEnrollment(): boolean  [API 24+]
- isMgf1DigestsSpecified(): boolean  [API 35+]
* isRandomizedEncryptionRequired(): boolean  [API 23+]
- isStrongBoxBacked(): boolean  [API 28+]
* isUnlockedDeviceRequired(): boolean  [API 28+]
* isUserAuthenticationRequired(): boolean  [API 23+]
* isUserAuthenticationValidWhileOnBody(): boolean  [API 24+]
* isUserConfirmationRequired(): boolean  [API 28+]
* isUserPresenceRequired(): boolean  [API 28+]
```

## KeyGenParameterSpec.Builder — declaration; API 23+ (0 +, 27 *, 0 ~, 4 -)

```
* Builder(String, int)  [API 23+]
* build(): KeyGenParameterSpec  [API 23+]
* setAlgorithmParameterSpec(AlgorithmParameterSpec): KeyGenParameterSpec.Builder  [API 23+]
* setAttestKeyAlias(String): KeyGenParameterSpec.Builder  [API 31+]
* setAttestationChallenge(byte[]): KeyGenParameterSpec.Builder  [API 24+]
* setBlockModes(String[]): KeyGenParameterSpec.Builder  [API 23+]
* setCertificateNotAfter(Date): KeyGenParameterSpec.Builder  [API 23+]
* setCertificateNotBefore(Date): KeyGenParameterSpec.Builder  [API 23+]
* setCertificateSerialNumber(BigInteger): KeyGenParameterSpec.Builder  [API 23+]
* setCertificateSubject(X500Principal): KeyGenParameterSpec.Builder  [API 23+]
* setDevicePropertiesAttestationIncluded(boolean): KeyGenParameterSpec.Builder  [API 31+]
* setDigests(String[]): KeyGenParameterSpec.Builder  [API 23+]
* setEncryptionPaddings(String[]): KeyGenParameterSpec.Builder  [API 23+]
* setInvalidatedByBiometricEnrollment(boolean): KeyGenParameterSpec.Builder  [API 24+]
- setIsStrongBoxBacked(boolean): KeyGenParameterSpec.Builder  [API 28+]
* setKeySize(int): KeyGenParameterSpec.Builder  [API 23+]
- setKeyValidityEnd(Date): KeyGenParameterSpec.Builder  [API 23+]
* setKeyValidityForConsumptionEnd(Date): KeyGenParameterSpec.Builder  [API 23+]
* setKeyValidityForOriginationEnd(Date): KeyGenParameterSpec.Builder  [API 23+]
* setKeyValidityStart(Date): KeyGenParameterSpec.Builder  [API 23+]
* setMaxUsageCount(int): KeyGenParameterSpec.Builder  [API 31+]
- setMgf1Digests(String[]): KeyGenParameterSpec.Builder  [API 35+]
* setRandomizedEncryptionRequired(boolean): KeyGenParameterSpec.Builder  [API 23+]
* setSignaturePaddings(String[]): KeyGenParameterSpec.Builder  [API 23+]
* setUnlockedDeviceRequired(boolean): KeyGenParameterSpec.Builder  [API 28+]
- setUserAuthenticationParameters(int, int): KeyGenParameterSpec.Builder  [API 30+]
* setUserAuthenticationRequired(boolean): KeyGenParameterSpec.Builder  [API 23+]
* setUserAuthenticationValidWhileOnBody(boolean): KeyGenParameterSpec.Builder  [API 24+]
* setUserAuthenticationValidityDurationSeconds(int): KeyGenParameterSpec.Builder  [API 23+, deprecated in 30]
* setUserConfirmationRequired(boolean): KeyGenParameterSpec.Builder  [API 28+]
* setUserPresenceRequired(boolean): KeyGenParameterSpec.Builder  [API 28+]
```

## KeyInfo — declaration; API 23+ (0 +, 0 *, 0 ~, 22 -)

```
- getBlockModes(): String[]  [API 23+]
- getDigests(): String[]  [API 23+]
- getEncryptionPaddings(): String[]  [API 23+]
- getKeySize(): int  [API 23+]
- getKeyValidityForConsumptionEnd(): Date  [API 23+]
- getKeyValidityForOriginationEnd(): Date  [API 23+]
- getKeyValidityStart(): Date  [API 23+]
- getKeystoreAlias(): String  [API 23+]
- getOrigin(): int  [API 23+]
- getPurposes(): int  [API 23+]
- getRemainingUsageCount(): int  [API 31+]
- getSecurityLevel(): int  [API 31+]
- getSignaturePaddings(): String[]  [API 23+]
- getUserAuthenticationType(): int  [API 30+]
- getUserAuthenticationValidityDurationSeconds(): int  [API 23+]
- isInsideSecureHardware(): boolean  [API 23+, deprecated in 31]
- isInvalidatedByBiometricEnrollment(): boolean  [API 24+]
- isTrustedUserPresenceRequired(): boolean  [API 28+]
- isUserAuthenticationRequired(): boolean  [API 23+]
- isUserAuthenticationRequirementEnforcedBySecureHardware(): boolean  [API 23+]
- isUserAuthenticationValidWhileOnBody(): boolean  [API 24+]
- isUserConfirmationRequired(): boolean  [API 28+]
```

## KeyNotYetValidException — declaration; API 23+ (2 +, 0 *, 0 ~, 1 -)

```
+ KeyNotYetValidException()  [API 23+]
+ KeyNotYetValidException(String)  [API 23+]
- KeyNotYetValidException(String, Throwable)  [API 23+]
```

## KeyPermanentlyInvalidatedException — declaration; API 23+ (2 +, 0 *, 0 ~, 1 -)

```
+ KeyPermanentlyInvalidatedException()  [API 23+]
+ KeyPermanentlyInvalidatedException(String)  [API 23+]
- KeyPermanentlyInvalidatedException(String, Throwable)  [API 23+]
```

## KeyProperties — declaration; API 23+ (0 +, 0 *, 0 ~, 0 -)

## KeyProtection — declaration; API 23+ (0 +, 17 *, 0 ~, 4 -)

```
* getBlockModes(): String[]  [API 23+]
* getDigests(): String[]  [API 23+]
* getEncryptionPaddings(): String[]  [API 23+]
* getKeyValidityForConsumptionEnd(): Date  [API 23+]
* getKeyValidityForOriginationEnd(): Date  [API 23+]
* getKeyValidityStart(): Date  [API 23+]
* getMaxUsageCount(): int  [API 31+]
- getMgf1Digests(): Set  [API 35+]
* getPurposes(): int  [API 23+]
* getSignaturePaddings(): String[]  [API 23+]
- getUserAuthenticationType(): int  [API 30+]
* getUserAuthenticationValidityDurationSeconds(): int  [API 23+]
- isDigestsSpecified(): boolean  [API 23+]
* isInvalidatedByBiometricEnrollment(): boolean  [API 24+]
- isMgf1DigestsSpecified(): boolean  [API 35+]
* isRandomizedEncryptionRequired(): boolean  [API 23+]
* isUnlockedDeviceRequired(): boolean  [API 28+]
* isUserAuthenticationRequired(): boolean  [API 23+]
* isUserAuthenticationValidWhileOnBody(): boolean  [API 24+]
* isUserConfirmationRequired(): boolean  [API 28+]
* isUserPresenceRequired(): boolean  [API 28+]
```

## KeyProtection.Builder — declaration; API 23+ (0 +, 18 *, 0 ~, 4 -)

```
* Builder(int)  [API 23+]
* build(): KeyProtection  [API 23+]
* setBlockModes(String[]): KeyProtection.Builder  [API 23+]
* setDigests(String[]): KeyProtection.Builder  [API 23+]
* setEncryptionPaddings(String[]): KeyProtection.Builder  [API 23+]
* setInvalidatedByBiometricEnrollment(boolean): KeyProtection.Builder  [API 24+]
- setIsStrongBoxBacked(boolean): KeyProtection.Builder  [API 31+]
- setKeyValidityEnd(Date): KeyProtection.Builder  [API 23+]
* setKeyValidityForConsumptionEnd(Date): KeyProtection.Builder  [API 23+]
* setKeyValidityForOriginationEnd(Date): KeyProtection.Builder  [API 23+]
* setKeyValidityStart(Date): KeyProtection.Builder  [API 23+]
* setMaxUsageCount(int): KeyProtection.Builder  [API 31+]
- setMgf1Digests(String[]): KeyProtection.Builder  [API 35+]
* setRandomizedEncryptionRequired(boolean): KeyProtection.Builder  [API 23+]
* setSignaturePaddings(String[]): KeyProtection.Builder  [API 23+]
* setUnlockedDeviceRequired(boolean): KeyProtection.Builder  [API 28+]
- setUserAuthenticationParameters(int, int): KeyProtection.Builder  [API 30+]
* setUserAuthenticationRequired(boolean): KeyProtection.Builder  [API 23+]
* setUserAuthenticationValidWhileOnBody(boolean): KeyProtection.Builder  [API 24+]
* setUserAuthenticationValidityDurationSeconds(int): KeyProtection.Builder  [API 23+, deprecated in 30]
* setUserConfirmationRequired(boolean): KeyProtection.Builder  [API 28+]
* setUserPresenceRequired(boolean): KeyProtection.Builder  [API 28+]
```

## SecureKeyImportUnavailableException — declaration; API 28+ (4 +, 0 *, 0 ~, 0 -)

```
+ SecureKeyImportUnavailableException()  [API 28+]
+ SecureKeyImportUnavailableException(String)  [API 28+]
+ SecureKeyImportUnavailableException(String, Throwable)  [API 28+]
+ SecureKeyImportUnavailableException(Throwable)  [API 28+]
```

## StrongBoxUnavailableException — declaration; API 28+ (4 +, 0 *, 0 ~, 0 -)

```
+ StrongBoxUnavailableException()  [API 28+]
+ StrongBoxUnavailableException(String)  [API 28+]
+ StrongBoxUnavailableException(String, Throwable)  [API 28+]
+ StrongBoxUnavailableException(Throwable)  [API 28+]
```

## UserNotAuthenticatedException — declaration; API 23+ (2 +, 0 *, 0 ~, 1 -)

```
+ UserNotAuthenticatedException()  [API 23+]
+ UserNotAuthenticatedException(String)  [API 23+]
- UserNotAuthenticatedException(String, Throwable)  [API 23+]
```

## UserPresenceUnavailableException — declaration; API 28+ (2 +, 0 *, 0 ~, 1 -)

```
+ UserPresenceUnavailableException()  [API 28+]
+ UserPresenceUnavailableException(String)  [API 28+]
- UserPresenceUnavailableException(String, Throwable)  [API 28+]
```

## WrappedKeyEntry — declaration; API 28+ (0 +, 5 *, 0 ~, 0 -)

```
* WrappedKeyEntry(byte[], String, String, AlgorithmParameterSpec)  [API 28+]
* getAlgorithmParameterSpec(): AlgorithmParameterSpec  [API 28+]
* getTransformation(): String  [API 28+]
* getWrappedKeyBytes(): byte[]  [API 28+]
* getWrappingKeyAlias(): String  [API 28+]
```


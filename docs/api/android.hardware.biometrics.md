# android.hardware.biometrics

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BiometricManager — framework; API 29+ (4 +, 0 *, 0 ~, 0 -)

```
+ canAuthenticate(): int  [API 29+, deprecated in 30]
+ canAuthenticate(int): int  [API 30+]
+ getLastAuthenticationTime(int): long  [API 35+]
+ getStrings(int): BiometricManager.Strings  [API 31+]
```

## BiometricManager.Authenticators — framework; API 30+ (0 +, 0 *, 0 ~, 0 -)

## BiometricManager.Strings — framework; API 31+ (3 +, 0 *, 0 ~, 0 -)

```
+ getButtonLabel(): CharSequence  [API 31+]
+ getPromptMessage(): CharSequence  [API 31+]
+ getSettingName(): CharSequence  [API 31+]
```

## BiometricPrompt — declaration; API 28+ (3 +, 9 *, 0 ~, 0 -)

```
+ authenticate(BiometricPrompt.CryptoObject, CancellationSignal, Executor, BiometricPrompt.AuthenticationCallback): void  [API 28+]
+ authenticate(CancellationSignal, Executor, BiometricPrompt.AuthenticationCallback): void  [API 28+]
* getAllowedAuthenticators(): int  [API 30+]
* getContentView(): PromptContentView  [API 35+]
* getDescription(): CharSequence  [API 30+]
* getLogoBitmap(): Bitmap  [API 35+]
* getLogoDescription(): String  [API 35+]
* getLogoRes(): int  [API 35+]
+ getNegativeButtonText(): CharSequence  [API 30+]
* getSubtitle(): CharSequence  [API 30+]
* getTitle(): CharSequence  [API 30+]
* isConfirmationRequired(): boolean  [API 30+]
```

## BiometricPrompt.AuthenticationCallback — declaration; API 28+ (5 +, 0 *, 0 ~, 0 -)

```
+ AuthenticationCallback()  [API 28+]
+ onAuthenticationError(int, CharSequence): void  [API 28+]
+ onAuthenticationFailed(): void  [API 28+]
+ onAuthenticationHelp(int, CharSequence): void  [API 28+]
+ onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult): void  [API 28+]
```

## BiometricPrompt.AuthenticationResult — declaration; API 28+ (2 +, 0 *, 0 ~, 0 -)

```
+ getAuthenticationType(): int  [API 30+]
+ getCryptoObject(): BiometricPrompt.CryptoObject  [API 28+]
```

## BiometricPrompt.Builder — declaration; API 28+ (2 +, 10 *, 0 ~, 1 -)

```
- Builder(Context)  [API 28+]
* build(): BiometricPrompt  [API 28+]
* setAllowedAuthenticators(int): BiometricPrompt.Builder  [API 30+]
* setConfirmationRequired(boolean): BiometricPrompt.Builder  [API 29+]
* setContentView(PromptContentView): BiometricPrompt.Builder  [API 35+]
* setDescription(CharSequence): BiometricPrompt.Builder  [API 28+]
+ setDeviceCredentialAllowed(boolean): BiometricPrompt.Builder  [API 29+, deprecated in 30]
* setLogoBitmap(Bitmap): BiometricPrompt.Builder  [API 35+]
* setLogoDescription(String): BiometricPrompt.Builder  [API 35+]
* setLogoRes(int): BiometricPrompt.Builder  [API 35+]
+ setNegativeButton(CharSequence, Executor, DialogInterface.OnClickListener): BiometricPrompt.Builder  [API 28+]
* setSubtitle(CharSequence): BiometricPrompt.Builder  [API 28+]
* setTitle(CharSequence): BiometricPrompt.Builder  [API 28+]
```

## BiometricPrompt.CryptoObject — declaration; API 28+ (2 +, 8 *, 0 ~, 2 -)

```
* CryptoObject(Cipher)  [API 28+]
- CryptoObject(IdentityCredential)  [API 30+, deprecated in 33]
* CryptoObject(Mac)  [API 28+]
- CryptoObject(PresentationSession)  [API 33+]
* CryptoObject(Signature)  [API 28+]
* CryptoObject(long)  [API 35+]
* getCipher(): Cipher  [API 28+]
+ getIdentityCredential(): IdentityCredential  [API 30+, deprecated in 33]
* getMac(): Mac  [API 28+]
* getOperationHandle(): long  [API 35+]
+ getPresentationSession(): PresentationSession  [API 33+]
* getSignature(): Signature  [API 28+]
```

## PromptContentItem — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## PromptContentItemBulletedText — declaration; API 35+ (2 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 35+]
- PromptContentItemBulletedText(String)  [API 35+]
+ describeContents(): int  [API 35+]
+ writeToParcel(Parcel, int): void  [API 35+]
```

## PromptContentItemPlainText — declaration; API 35+ (2 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 35+]
- PromptContentItemPlainText(String)  [API 35+]
+ describeContents(): int  [API 35+]
+ writeToParcel(Parcel, int): void  [API 35+]
```

## PromptContentView — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## PromptContentViewWithMoreOptionsButton — declaration; API 35+ (3 +, 1 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 35+]
+ describeContents(): int  [API 35+]
* getDescription(): String  [API 35+]
+ getMoreOptionsButtonListener(): DialogInterface.OnClickListener  [API 35+]
+ writeToParcel(Parcel, int): void  [API 35+]
```

## PromptContentViewWithMoreOptionsButton.Builder — declaration; API 35+ (1 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): PromptContentViewWithMoreOptionsButton  [API 35+]
* setDescription(String): PromptContentViewWithMoreOptionsButton.Builder  [API 35+]
+ setMoreOptionsButtonListener(Executor, DialogInterface.OnClickListener): PromptContentViewWithMoreOptionsButton.Builder  [API 35+]
```

## PromptVerticalListContentView — declaration; API 35+ (2 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* getDescription(): String  [API 35+]
* getListItems(): List  [API 35+]
+ getMaxEachItemCharacterNumber(): int [static]  [API 35+]
+ getMaxItemCount(): int [static]  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## PromptVerticalListContentView.Builder — declaration; API 35+ (1 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* addListItem(PromptContentItem): PromptVerticalListContentView.Builder  [API 35+]
+ addListItem(PromptContentItem, int): PromptVerticalListContentView.Builder  [API 35+]
* build(): PromptVerticalListContentView  [API 35+]
* setDescription(String): PromptVerticalListContentView.Builder  [API 35+]
```


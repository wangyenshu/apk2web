# android.credentials

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ClearCredentialStateException — declaration; API 34+ (2 +, 0 *, 0 ~, 3 -)

```
+ ClearCredentialStateException(String)  [API 34+]
- ClearCredentialStateException(String, String)  [API 34+]
- ClearCredentialStateException(String, String, Throwable)  [API 34+]
+ ClearCredentialStateException(String, Throwable)  [API 34+]
- getType(): String  [API 34+]
```

## ClearCredentialStateRequest — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* ClearCredentialStateRequest(Bundle)  [API 34+]
* describeContents(): int  [API 34+]
* getData(): Bundle  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## CreateCredentialException — declaration; API 34+ (2 +, 0 *, 0 ~, 3 -)

```
+ CreateCredentialException(String)  [API 34+]
- CreateCredentialException(String, String)  [API 34+]
- CreateCredentialException(String, String, Throwable)  [API 34+]
+ CreateCredentialException(String, Throwable)  [API 34+]
- getType(): String  [API 34+]
```

## CreateCredentialRequest — declaration; API 34+ (0 +, 4 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 34+]
- alwaysSendAppInfoToProvider(): boolean  [API 34+]
- describeContents(): int  [API 34+]
* getCandidateQueryData(): Bundle  [API 34+]
* getCredentialData(): Bundle  [API 34+]
* getOrigin(): String  [API 34+]
* getType(): String  [API 34+]
- isSystemProviderRequired(): boolean  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## CreateCredentialRequest.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 2 -)

```
* Builder(String, Bundle, Bundle)  [API 34+]
* build(): CreateCredentialRequest  [API 34+]
- setAlwaysSendAppInfoToProvider(boolean): CreateCredentialRequest.Builder  [API 34+]
- setIsSystemProviderRequired(boolean): CreateCredentialRequest.Builder  [API 34+]
* setOrigin(String): CreateCredentialRequest.Builder  [API 34+]
```

## CreateCredentialResponse — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* CreateCredentialResponse(Bundle)  [API 34+]
* describeContents(): int  [API 34+]
* getData(): Bundle  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## Credential — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* Credential(String, Bundle)  [API 34+]
* describeContents(): int  [API 34+]
* getData(): Bundle  [API 34+]
* getType(): String  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## CredentialDescription — declaration; API 34+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* CredentialDescription(String, Set, List)  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getCredentialEntries(): List  [API 34+]
* getSupportedElementKeys(): Set  [API 34+]
* getType(): String  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## CredentialManager — framework; API 34+ (0 +, 0 *, 0 ~, 8 -)

```
- clearCredentialState(ClearCredentialStateRequest, CancellationSignal, Executor, OutcomeReceiver): void  [API 34+]
- createCredential(Context, CreateCredentialRequest, CancellationSignal, Executor, OutcomeReceiver): void  [API 34+]
- getCredential(Context, GetCredentialRequest, CancellationSignal, Executor, OutcomeReceiver): void  [API 34+]
- getCredential(Context, PrepareGetCredentialResponse.PendingGetCredentialHandle, CancellationSignal, Executor, OutcomeReceiver): void  [API 34+]
- isEnabledCredentialProviderService(ComponentName): boolean  [API 34+]
- prepareGetCredential(GetCredentialRequest, CancellationSignal, Executor, OutcomeReceiver): void  [API 34+]
- registerCredentialDescription(RegisterCredentialDescriptionRequest): void  [API 34+]
- unregisterCredentialDescription(UnregisterCredentialDescriptionRequest): void  [API 34+]
```

## CredentialOption — declaration; API 34+ (0 +, 4 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
* getAllowedProviders(): Set  [API 34+]
* getCandidateQueryData(): Bundle  [API 34+]
* getCredentialRetrievalData(): Bundle  [API 34+]
* getType(): String  [API 34+]
- isSystemProviderRequired(): boolean  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## CredentialOption.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 2 -)

```
* Builder(String, Bundle, Bundle)  [API 34+]
- addAllowedProvider(ComponentName): CredentialOption.Builder  [API 34+]
* build(): CredentialOption  [API 34+]
* setAllowedProviders(Set): CredentialOption.Builder  [API 34+]
- setIsSystemProviderRequired(boolean): CredentialOption.Builder  [API 34+]
```

## GetCredentialException — declaration; API 34+ (2 +, 0 *, 0 ~, 3 -)

```
+ GetCredentialException(String)  [API 34+]
- GetCredentialException(String, String)  [API 34+]
- GetCredentialException(String, String, Throwable)  [API 34+]
+ GetCredentialException(String, Throwable)  [API 34+]
- getType(): String  [API 34+]
```

## GetCredentialRequest — declaration; API 34+ (0 +, 6 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 34+]
- alwaysSendAppInfoToProvider(): boolean  [API 34+]
* describeContents(): int  [API 34+]
* getCredentialOptions(): List  [API 34+]
* getData(): Bundle  [API 34+]
* getOrigin(): String  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## GetCredentialRequest.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 1 -)

```
* Builder(Bundle)  [API 34+]
* addCredentialOption(CredentialOption): GetCredentialRequest.Builder  [API 34+]
* build(): GetCredentialRequest  [API 34+]
- setAlwaysSendAppInfoToProvider(boolean): GetCredentialRequest.Builder  [API 34+]
* setCredentialOptions(List): GetCredentialRequest.Builder  [API 34+]
* setOrigin(String): GetCredentialRequest.Builder  [API 34+]
```

## GetCredentialResponse — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* GetCredentialResponse(Credential)  [API 34+]
* describeContents(): int  [API 34+]
* getCredential(): Credential  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## PrepareGetCredentialResponse — declaration; API 34+ (0 +, 0 *, 0 ~, 4 -)

```
- getPendingGetCredentialHandle(): PrepareGetCredentialResponse.PendingGetCredentialHandle  [API 34+]
- hasAuthenticationResults(): boolean  [API 34+]
- hasCredentialResults(String): boolean  [API 34+]
- hasRemoteResults(): boolean  [API 34+]
```

## PrepareGetCredentialResponse.PendingGetCredentialHandle — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## RegisterCredentialDescriptionRequest — declaration; API 34+ (0 +, 4 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 34+]
- RegisterCredentialDescriptionRequest(CredentialDescription)  [API 34+]
* RegisterCredentialDescriptionRequest(Set)  [API 34+]
* describeContents(): int  [API 34+]
* getCredentialDescriptions(): Set  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## UnregisterCredentialDescriptionRequest — declaration; API 34+ (0 +, 4 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 34+]
- UnregisterCredentialDescriptionRequest(CredentialDescription)  [API 34+]
* UnregisterCredentialDescriptionRequest(Set)  [API 34+]
* describeContents(): int  [API 34+]
* getCredentialDescriptions(): Set  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```


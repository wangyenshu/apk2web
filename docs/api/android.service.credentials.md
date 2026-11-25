# android.service.credentials

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Action — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* Action(Slice)  [API 34+]
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* getSlice(): Slice  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## BeginCreateCredentialRequest — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
* BeginCreateCredentialRequest(String, Bundle)  [API 34+]
* BeginCreateCredentialRequest(String, Bundle, CallingAppInfo)  [API 34+]
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* getCallingAppInfo(): CallingAppInfo  [API 34+]
* getData(): Bundle  [API 34+]
* getType(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## BeginCreateCredentialResponse — declaration; API 34+ (0 +, 4 *, 0 ~, 1 -)

```
- BeginCreateCredentialResponse()  [API 34+]
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* getCreateEntries(): List  [API 34+]
* getRemoteCreateEntry(): RemoteEntry  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## BeginCreateCredentialResponse.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* addCreateEntry(CreateEntry): BeginCreateCredentialResponse.Builder  [API 34+]
* build(): BeginCreateCredentialResponse  [API 34+]
* setCreateEntries(List): BeginCreateCredentialResponse.Builder  [API 34+]
* setRemoteCreateEntry(RemoteEntry): BeginCreateCredentialResponse.Builder  [API 34+]
```

## BeginGetCredentialOption — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
* BeginGetCredentialOption(String, String, Bundle)  [API 34+]
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* getCandidateQueryData(): Bundle  [API 34+]
* getId(): String  [API 34+]
* getType(): String  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## BeginGetCredentialRequest — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* getBeginGetCredentialOptions(): List  [API 34+]
* getCallingAppInfo(): CallingAppInfo  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## BeginGetCredentialRequest.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* addBeginGetCredentialOption(BeginGetCredentialOption): BeginGetCredentialRequest.Builder  [API 34+]
* build(): BeginGetCredentialRequest  [API 34+]
* setBeginGetCredentialOptions(List): BeginGetCredentialRequest.Builder  [API 34+]
* setCallingAppInfo(CallingAppInfo): BeginGetCredentialRequest.Builder  [API 34+]
```

## BeginGetCredentialResponse — declaration; API 34+ (0 +, 6 *, 0 ~, 1 -)

```
- BeginGetCredentialResponse()  [API 34+]
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* getActions(): List  [API 34+]
* getAuthenticationActions(): List  [API 34+]
* getCredentialEntries(): List  [API 34+]
* getRemoteCredentialEntry(): RemoteEntry  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## BeginGetCredentialResponse.Builder — declaration; API 34+ (0 +, 9 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* addAction(Action): BeginGetCredentialResponse.Builder  [API 34+]
* addAuthenticationAction(Action): BeginGetCredentialResponse.Builder  [API 34+]
* addCredentialEntry(CredentialEntry): BeginGetCredentialResponse.Builder  [API 34+]
* build(): BeginGetCredentialResponse  [API 34+]
* setActions(List): BeginGetCredentialResponse.Builder  [API 34+]
* setAuthenticationActions(List): BeginGetCredentialResponse.Builder  [API 34+]
* setCredentialEntries(List): BeginGetCredentialResponse.Builder  [API 34+]
* setRemoteCredentialEntry(RemoteEntry): BeginGetCredentialResponse.Builder  [API 34+]
```

## CallingAppInfo — declaration; API 34+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* CallingAppInfo(String, SigningInfo)  [API 34+]
* CallingAppInfo(String, SigningInfo, String)  [API 34+]
* describeContents(): int  [API 34+]
* getOrigin(): String  [API 34+]
* getPackageName(): String  [API 34+]
* getSigningInfo(): SigningInfo  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## ClearCredentialStateRequest — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* ClearCredentialStateRequest(CallingAppInfo, Bundle)  [API 34+]
* describeContents(): int  [API 34+]
* getCallingAppInfo(): CallingAppInfo  [API 34+]
* getData(): Bundle  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## CreateCredentialRequest — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* CreateCredentialRequest(CallingAppInfo, String, Bundle)  [API 34+]
* describeContents(): int  [API 34+]
* getCallingAppInfo(): CallingAppInfo  [API 34+]
* getData(): Bundle  [API 34+]
* getType(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## CreateEntry — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* CreateEntry(Slice)  [API 34+]
* describeContents(): int  [API 34+]
* getSlice(): Slice  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## CredentialEntry — declaration; API 34+ (0 +, 7 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 34+]
- CredentialEntry(BeginGetCredentialOption, Slice)  [API 34+]
* CredentialEntry(String, Slice)  [API 34+]
* CredentialEntry(String, String, Slice)  [API 34+]
* describeContents(): int  [API 34+]
* getBeginGetCredentialOptionId(): String  [API 34+]
* getSlice(): Slice  [API 34+]
* getType(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## CredentialProviderService — declaration; API 34+ (4 +, 0 *, 0 ~, 2 -)

```
- CredentialProviderService()  [API 34+]
+ onBeginCreateCredential(BeginCreateCredentialRequest, CancellationSignal, OutcomeReceiver): void  [API 34+]
+ onBeginGetCredential(BeginGetCredentialRequest, CancellationSignal, OutcomeReceiver): void  [API 34+]
- onBind(Intent): IBinder  [API 34+]
+ onClearCredentialState(ClearCredentialStateRequest, CancellationSignal, OutcomeReceiver): void  [API 34+]
+ onCreate(): void  [API 34+]
```

## GetCredentialRequest — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* GetCredentialRequest(CallingAppInfo, List)  [API 34+]
* describeContents(): int  [API 34+]
* getCallingAppInfo(): CallingAppInfo  [API 34+]
* getCredentialOptions(): List  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## RemoteEntry — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* RemoteEntry(Slice)  [API 34+]
* describeContents(): int  [API 34+]
* getSlice(): Slice  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```


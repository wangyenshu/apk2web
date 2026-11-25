# android.adservices.measurement

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DeletionRequest — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* getDeletionMode(): int  [API 34+]
* getDomainUris(): List  [API 34+]
* getEnd(): Instant  [API 34+]
* getMatchBehavior(): int  [API 34+]
* getOriginUris(): List  [API 34+]
* getStart(): Instant  [API 34+]
```

## DeletionRequest.Builder — declaration; API 34+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): DeletionRequest  [API 34+]
* setDeletionMode(int): DeletionRequest.Builder  [API 34+]
* setDomainUris(List): DeletionRequest.Builder  [API 34+]
* setEnd(Instant): DeletionRequest.Builder  [API 34+]
* setMatchBehavior(int): DeletionRequest.Builder  [API 34+]
* setOriginUris(List): DeletionRequest.Builder  [API 34+]
* setStart(Instant): DeletionRequest.Builder  [API 34+]
```

## MeasurementManager — declaration; API 34+ (0 +, 0 *, 0 ~, 15 -)

```
- deleteRegistrations(DeletionRequest, Executor, AdServicesOutcomeReceiver): void  [API 35+]
- deleteRegistrations(DeletionRequest, Executor, OutcomeReceiver): void  [API 34+]
- get(Context): MeasurementManager [static]  [API 34+]
- getMeasurementApiStatus(Executor, AdServicesOutcomeReceiver): void  [API 35+]
- getMeasurementApiStatus(Executor, OutcomeReceiver): void  [API 34+]
- registerSource(SourceRegistrationRequest, Executor, AdServicesOutcomeReceiver): void  [API 35+]
- registerSource(SourceRegistrationRequest, Executor, OutcomeReceiver): void  [API 35+]
- registerSource(Uri, InputEvent, Executor, AdServicesOutcomeReceiver): void  [API 35+]
- registerSource(Uri, InputEvent, Executor, OutcomeReceiver): void  [API 34+]
- registerTrigger(Uri, Executor, AdServicesOutcomeReceiver): void  [API 35+]
- registerTrigger(Uri, Executor, OutcomeReceiver): void  [API 34+]
- registerWebSource(WebSourceRegistrationRequest, Executor, AdServicesOutcomeReceiver): void  [API 35+]
- registerWebSource(WebSourceRegistrationRequest, Executor, OutcomeReceiver): void  [API 34+]
- registerWebTrigger(WebTriggerRegistrationRequest, Executor, AdServicesOutcomeReceiver): void  [API 35+]
- registerWebTrigger(WebTriggerRegistrationRequest, Executor, OutcomeReceiver): void  [API 34+]
```

## SourceRegistrationRequest — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getInputEvent(): InputEvent  [API 35+]
* getRegistrationUris(): List  [API 35+]
* hashCode(): int  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## SourceRegistrationRequest.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(List)  [API 35+]
* build(): SourceRegistrationRequest  [API 35+]
* setInputEvent(InputEvent): SourceRegistrationRequest.Builder  [API 35+]
```

## WebSourceParams — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getRegistrationUri(): Uri  [API 34+]
* hashCode(): int  [API 34+]
* isDebugKeyAllowed(): boolean  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## WebSourceParams.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(Uri)  [API 34+]
* build(): WebSourceParams  [API 34+]
* setDebugKeyAllowed(boolean): WebSourceParams.Builder  [API 34+]
```

## WebSourceRegistrationRequest — declaration; API 34+ (0 +, 4 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
* getAppDestination(): Uri  [API 34+]
* getInputEvent(): InputEvent  [API 34+]
- getSourceParams(): List  [API 34+]
- getTopOriginUri(): Uri  [API 34+]
* getVerifiedDestination(): Uri  [API 34+]
* getWebDestination(): Uri  [API 34+]
- hashCode(): int  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## WebSourceRegistrationRequest.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 1 -)

```
- Builder(List, Uri)  [API 34+]
* build(): WebSourceRegistrationRequest  [API 34+]
* setAppDestination(Uri): WebSourceRegistrationRequest.Builder  [API 34+]
* setInputEvent(InputEvent): WebSourceRegistrationRequest.Builder  [API 34+]
* setVerifiedDestination(Uri): WebSourceRegistrationRequest.Builder  [API 34+]
* setWebDestination(Uri): WebSourceRegistrationRequest.Builder  [API 34+]
```

## WebTriggerParams — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getRegistrationUri(): Uri  [API 34+]
* hashCode(): int  [API 34+]
* isDebugKeyAllowed(): boolean  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## WebTriggerParams.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(Uri)  [API 34+]
* build(): WebTriggerParams  [API 34+]
* setDebugKeyAllowed(boolean): WebTriggerParams.Builder  [API 34+]
```

## WebTriggerRegistrationRequest — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- getDestination(): Uri  [API 34+]
- getTriggerParams(): List  [API 34+]
- hashCode(): int  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## WebTriggerRegistrationRequest.Builder — declaration; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- Builder(List, Uri)  [API 34+]
- build(): WebTriggerRegistrationRequest  [API 34+]
```


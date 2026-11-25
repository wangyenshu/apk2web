# android.adservices.customaudience

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AddCustomAudienceOverrideRequest — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* AddCustomAudienceOverrideRequest(AdTechIdentifier, String, String, AdSelectionSignals)  [API 34+]
* getBiddingLogicJs(): String  [API 34+]
* getBiddingLogicJsVersion(): long  [API 35+]
* getBuyer(): AdTechIdentifier  [API 34+]
* getName(): String  [API 34+]
* getTrustedBiddingSignals(): AdSelectionSignals  [API 34+]
```

## AddCustomAudienceOverrideRequest.Builder — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): AddCustomAudienceOverrideRequest  [API 34+]
* setBiddingLogicJs(String): AddCustomAudienceOverrideRequest.Builder  [API 34+]
* setBiddingLogicJsVersion(long): AddCustomAudienceOverrideRequest.Builder  [API 35+]
* setBuyer(AdTechIdentifier): AddCustomAudienceOverrideRequest.Builder  [API 34+]
* setName(String): AddCustomAudienceOverrideRequest.Builder  [API 34+]
* setTrustedBiddingSignals(AdSelectionSignals): AddCustomAudienceOverrideRequest.Builder  [API 34+]
```

## CustomAudience — declaration; API 34+ (0 +, 16 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getActivationTime(): Instant  [API 34+]
* getAds(): List  [API 34+]
* getAuctionServerRequestFlags(): int  [API 35+]
* getBiddingLogicUri(): Uri  [API 34+]
* getBuyer(): AdTechIdentifier  [API 34+]
* getDailyUpdateUri(): Uri  [API 34+]
* getExpirationTime(): Instant  [API 34+]
* getName(): String  [API 34+]
* getPriority(): double  [API 36+]
* getTrustedBiddingData(): TrustedBiddingData  [API 34+]
* getUserBiddingSignals(): AdSelectionSignals  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## CustomAudience.Builder — declaration; API 34+ (0 +, 13 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): CustomAudience  [API 34+]
* setActivationTime(Instant): CustomAudience.Builder  [API 34+]
* setAds(List): CustomAudience.Builder  [API 34+]
* setAuctionServerRequestFlags(int): CustomAudience.Builder  [API 35+]
* setBiddingLogicUri(Uri): CustomAudience.Builder  [API 34+]
* setBuyer(AdTechIdentifier): CustomAudience.Builder  [API 34+]
* setDailyUpdateUri(Uri): CustomAudience.Builder  [API 34+]
* setExpirationTime(Instant): CustomAudience.Builder  [API 34+]
* setName(String): CustomAudience.Builder  [API 34+]
* setPriority(double): CustomAudience.Builder  [API 36+]
* setTrustedBiddingData(TrustedBiddingData): CustomAudience.Builder  [API 34+]
* setUserBiddingSignals(AdSelectionSignals): CustomAudience.Builder  [API 34+]
```

## CustomAudienceManager — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- fetchAndJoinCustomAudience(FetchAndJoinCustomAudienceRequest, Executor, OutcomeReceiver): void  [API 35+]
- get(Context): CustomAudienceManager [static]  [API 34+]
- getTestCustomAudienceManager(): TestCustomAudienceManager  [API 34+]
- joinCustomAudience(JoinCustomAudienceRequest, Executor, OutcomeReceiver): void  [API 34+]
- leaveCustomAudience(LeaveCustomAudienceRequest, Executor, OutcomeReceiver): void  [API 34+]
- scheduleCustomAudienceUpdate(ScheduleCustomAudienceUpdateRequest, Executor, AdServicesOutcomeReceiver): void  [API 36+]
```

## FetchAndJoinCustomAudienceRequest — declaration; API 35+ (0 +, 8 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getActivationTime(): Instant  [API 35+]
* getExpirationTime(): Instant  [API 35+]
* getFetchUri(): Uri  [API 35+]
* getName(): String  [API 35+]
* getUserBiddingSignals(): AdSelectionSignals  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
```

## FetchAndJoinCustomAudienceRequest.Builder — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder(Uri)  [API 35+]
* build(): FetchAndJoinCustomAudienceRequest  [API 35+]
* setActivationTime(Instant): FetchAndJoinCustomAudienceRequest.Builder  [API 35+]
* setExpirationTime(Instant): FetchAndJoinCustomAudienceRequest.Builder  [API 35+]
* setFetchUri(Uri): FetchAndJoinCustomAudienceRequest.Builder  [API 35+]
* setName(String): FetchAndJoinCustomAudienceRequest.Builder  [API 35+]
* setUserBiddingSignals(AdSelectionSignals): FetchAndJoinCustomAudienceRequest.Builder  [API 35+]
```

## JoinCustomAudienceRequest — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getCustomAudience(): CustomAudience  [API 34+]
* hashCode(): int  [API 34+]
```

## JoinCustomAudienceRequest.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): JoinCustomAudienceRequest  [API 34+]
* setCustomAudience(CustomAudience): JoinCustomAudienceRequest.Builder  [API 34+]
```

## LeaveCustomAudienceRequest — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getBuyer(): AdTechIdentifier  [API 34+]
* getName(): String  [API 34+]
* hashCode(): int  [API 34+]
```

## LeaveCustomAudienceRequest.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): LeaveCustomAudienceRequest  [API 34+]
* setBuyer(AdTechIdentifier): LeaveCustomAudienceRequest.Builder  [API 34+]
* setName(String): LeaveCustomAudienceRequest.Builder  [API 34+]
```

## PartialCustomAudience — declaration; API 36+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 36+]
* describeContents(): int  [API 36+]
* equals(Object): boolean  [API 36+]
* getActivationTime(): Instant  [API 36+]
* getExpirationTime(): Instant  [API 36+]
* getName(): String  [API 36+]
* getUserBiddingSignals(): AdSelectionSignals  [API 36+]
* hashCode(): int  [API 36+]
* toString(): String  [API 36+]
* writeToParcel(Parcel, int): void  [API 36+]
```

## PartialCustomAudience.Builder — declaration; API 36+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(String)  [API 36+]
* build(): PartialCustomAudience  [API 36+]
* setActivationTime(Instant): PartialCustomAudience.Builder  [API 36+]
* setExpirationTime(Instant): PartialCustomAudience.Builder  [API 36+]
* setUserBiddingSignals(AdSelectionSignals): PartialCustomAudience.Builder  [API 36+]
```

## RemoveCustomAudienceOverrideRequest — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* RemoveCustomAudienceOverrideRequest(AdTechIdentifier, String)  [API 34+]
* getBuyer(): AdTechIdentifier  [API 34+]
* getName(): String  [API 34+]
```

## RemoveCustomAudienceOverrideRequest.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): RemoveCustomAudienceOverrideRequest  [API 34+]
* setBuyer(AdTechIdentifier): RemoveCustomAudienceOverrideRequest.Builder  [API 34+]
* setName(String): RemoveCustomAudienceOverrideRequest.Builder  [API 34+]
```

## ScheduleCustomAudienceUpdateRequest — declaration; API 36+ (0 +, 6 *, 0 ~, 1 -)

```
* equals(Object): boolean  [API 36+]
* getMinDelay(): Duration  [API 36+]
* getPartialCustomAudienceList(): List  [API 36+]
* getUpdateUri(): Uri  [API 36+]
* hashCode(): int  [API 36+]
- shouldReplacePendingUpdates(): boolean  [API 36+]
* toString(): String  [API 36+]
```

## ScheduleCustomAudienceUpdateRequest.Builder — declaration; API 36+ (0 +, 5 *, 0 ~, 1 -)

```
* Builder(Uri, Duration, List)  [API 36+]
* build(): ScheduleCustomAudienceUpdateRequest  [API 36+]
* setMinDelay(Duration): ScheduleCustomAudienceUpdateRequest.Builder  [API 36+]
* setPartialCustomAudienceList(List): ScheduleCustomAudienceUpdateRequest.Builder  [API 36+]
- setShouldReplacePendingUpdates(boolean): ScheduleCustomAudienceUpdateRequest.Builder  [API 36+]
* setUpdateUri(Uri): ScheduleCustomAudienceUpdateRequest.Builder  [API 36+]
```

## TestCustomAudienceManager — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- overrideCustomAudienceRemoteInfo(AddCustomAudienceOverrideRequest, Executor, OutcomeReceiver): void  [API 34+]
- removeCustomAudienceRemoteInfoOverride(RemoveCustomAudienceOverrideRequest, Executor, OutcomeReceiver): void  [API 34+]
- resetAllCustomAudienceOverrides(Executor, OutcomeReceiver): void  [API 34+]
```

## TrustedBiddingData — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getTrustedBiddingKeys(): List  [API 34+]
* getTrustedBiddingUri(): Uri  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## TrustedBiddingData.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): TrustedBiddingData  [API 34+]
* setTrustedBiddingKeys(List): TrustedBiddingData.Builder  [API 34+]
* setTrustedBiddingUri(Uri): TrustedBiddingData.Builder  [API 34+]
```


# android.adservices.adselection

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AdSelectionConfig — declaration; API 34+ (0 +, 12 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getAdSelectionSignals(): AdSelectionSignals  [API 34+]
* getCustomAudienceBuyers(): List  [API 34+]
* getDecisionLogicUri(): Uri  [API 34+]
* getPerBuyerSignals(): Map  [API 34+]
* getPerBuyerSignedContextualAds(): Map  [API 35+]
* getSeller(): AdTechIdentifier  [API 34+]
* getSellerSignals(): AdSelectionSignals  [API 34+]
* getTrustedScoringSignalsUri(): Uri  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## AdSelectionConfig.Builder — declaration; API 34+ (0 +, 10 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): AdSelectionConfig  [API 34+]
* setAdSelectionSignals(AdSelectionSignals): AdSelectionConfig.Builder  [API 34+]
* setCustomAudienceBuyers(List): AdSelectionConfig.Builder  [API 34+]
* setDecisionLogicUri(Uri): AdSelectionConfig.Builder  [API 34+]
* setPerBuyerSignals(Map): AdSelectionConfig.Builder  [API 34+]
* setPerBuyerSignedContextualAds(Map): AdSelectionConfig.Builder  [API 35+]
* setSeller(AdTechIdentifier): AdSelectionConfig.Builder  [API 34+]
* setSellerSignals(AdSelectionSignals): AdSelectionConfig.Builder  [API 34+]
* setTrustedScoringSignalsUri(Uri): AdSelectionConfig.Builder  [API 34+]
```

## AdSelectionFromOutcomesConfig — declaration; API 35+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getAdSelectionIds(): List  [API 35+]
* getSelectionLogicUri(): Uri  [API 35+]
* getSelectionSignals(): AdSelectionSignals  [API 35+]
* getSeller(): AdTechIdentifier  [API 35+]
* hashCode(): int  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## AdSelectionFromOutcomesConfig.Builder — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): AdSelectionFromOutcomesConfig  [API 35+]
* setAdSelectionIds(List): AdSelectionFromOutcomesConfig.Builder  [API 35+]
* setSelectionLogicUri(Uri): AdSelectionFromOutcomesConfig.Builder  [API 35+]
* setSelectionSignals(AdSelectionSignals): AdSelectionFromOutcomesConfig.Builder  [API 35+]
* setSeller(AdTechIdentifier): AdSelectionFromOutcomesConfig.Builder  [API 35+]
```

## AdSelectionManager — declaration; API 34+ (0 +, 0 *, 0 ~, 10 -)

```
- get(Context): AdSelectionManager [static]  [API 34+]
- getAdSelectionData(GetAdSelectionDataRequest, Executor, OutcomeReceiver): void  [API 35+]
- getTestAdSelectionManager(): TestAdSelectionManager  [API 34+]
- persistAdSelectionResult(PersistAdSelectionResultRequest, Executor, OutcomeReceiver): void  [API 35+]
- reportEvent(ReportEventRequest, Executor, OutcomeReceiver): void  [API 35+]
- reportImpression(ReportImpressionRequest, Executor, OutcomeReceiver): void  [API 34+]
- selectAds(AdSelectionConfig, Executor, OutcomeReceiver): void  [API 34+]
- selectAds(AdSelectionFromOutcomesConfig, Executor, OutcomeReceiver): void  [API 35+]
- setAppInstallAdvertisers(SetAppInstallAdvertisersRequest, Executor, OutcomeReceiver): void  [API 35+]
- updateAdCounterHistogram(UpdateAdCounterHistogramRequest, Executor, OutcomeReceiver): void  [API 35+]
```

## AdSelectionOutcome — declaration; API 34+ (0 +, 4 *, 0 ~, 1 -)

```
- NO_OUTCOME [field]  [API 35+]
* equals(Object): boolean  [API 34+]
* getAdSelectionId(): long  [API 34+]
* getRenderUri(): Uri  [API 34+]
- hasOutcome(): boolean  [API 35+]
* hashCode(): int  [API 34+]
```

## AdSelectionOutcome.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): AdSelectionOutcome  [API 34+]
* setAdSelectionId(long): AdSelectionOutcome.Builder  [API 34+]
* setRenderUri(Uri): AdSelectionOutcome.Builder  [API 34+]
```

## AdWithBid — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* AdWithBid(AdData, double)  [API 35+]
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getAdData(): AdData  [API 35+]
* getBid(): double  [API 35+]
* hashCode(): int  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## AddAdSelectionFromOutcomesOverrideRequest — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* AddAdSelectionFromOutcomesOverrideRequest(AdSelectionFromOutcomesConfig, String, AdSelectionSignals)  [API 35+]
* getAdSelectionFromOutcomesConfig(): AdSelectionFromOutcomesConfig  [API 35+]
* getOutcomeSelectionLogicJs(): String  [API 35+]
* getOutcomeSelectionTrustedSignals(): AdSelectionSignals  [API 35+]
```

## AddAdSelectionOverrideRequest — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* AddAdSelectionOverrideRequest(AdSelectionConfig, String, AdSelectionSignals)  [API 34+]
* AddAdSelectionOverrideRequest(AdSelectionConfig, String, AdSelectionSignals, PerBuyerDecisionLogic)  [API 35+]
* getAdSelectionConfig(): AdSelectionConfig  [API 34+]
* getDecisionLogicJs(): String  [API 34+]
* getPerBuyerDecisionLogic(): PerBuyerDecisionLogic  [API 35+]
* getTrustedScoringSignals(): AdSelectionSignals  [API 34+]
```

## DecisionLogic — declaration; API 35+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 35+]
- DecisionLogic(String)  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
- getLogic(): String  [API 35+]
- hashCode(): int  [API 35+]
- toString(): String  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## GetAdSelectionDataOutcome — declaration; API 35+ (0 +, 0 *, 0 ~, 3 -)

```
- getAdSelectionData(): byte[]  [API 35+]
- getAdSelectionDataId(): long  [API 35+]
- getAdSelectionId(): long  [API 35+, deprecated in 36]
```

## GetAdSelectionDataRequest — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* getCoordinatorOriginUri(): Uri  [API 35+]
* getSeller(): AdTechIdentifier  [API 35+]
* getSellerConfiguration(): SellerConfiguration  [API 36+]
```

## GetAdSelectionDataRequest.Builder — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): GetAdSelectionDataRequest  [API 35+]
* setCoordinatorOriginUri(Uri): GetAdSelectionDataRequest.Builder  [API 35+]
* setSeller(AdTechIdentifier): GetAdSelectionDataRequest.Builder  [API 35+]
* setSellerConfiguration(SellerConfiguration): GetAdSelectionDataRequest.Builder  [API 36+]
```

## PerBuyerConfiguration — declaration; API 36+ (0 +, 2 *, 0 ~, 0 -)

```
* getBuyer(): AdTechIdentifier  [API 36+]
* getTargetInputSizeBytes(): int  [API 36+]
```

## PerBuyerConfiguration.Builder — declaration; API 36+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 36+]
* build(): PerBuyerConfiguration  [API 36+]
* setBuyer(AdTechIdentifier): PerBuyerConfiguration.Builder  [API 36+]
* setTargetInputSizeBytes(int): PerBuyerConfiguration.Builder  [API 36+]
```

## PerBuyerDecisionLogic — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
- EMPTY [field]  [API 35+]
* PerBuyerDecisionLogic(Map)  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getPerBuyerLogicMap(): Map  [API 35+]
* hashCode(): int  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## PersistAdSelectionResultRequest — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* getAdSelectionDataId(): long  [API 35+]
* getAdSelectionId(): long  [API 35+, deprecated in 36]
* getAdSelectionResult(): byte[]  [API 35+]
* getSeller(): AdTechIdentifier  [API 35+]
```

## PersistAdSelectionResultRequest.Builder — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): PersistAdSelectionResultRequest  [API 35+]
* setAdSelectionDataId(long): PersistAdSelectionResultRequest.Builder  [API 35+]
* setAdSelectionId(long): PersistAdSelectionResultRequest.Builder  [API 35+, deprecated in 36]
* setAdSelectionResult(byte[]): PersistAdSelectionResultRequest.Builder  [API 35+]
* setSeller(AdTechIdentifier): PersistAdSelectionResultRequest.Builder  [API 35+]
```

## RemoveAdSelectionFromOutcomesOverrideRequest — declaration; API 35+ (0 +, 0 *, 0 ~, 2 -)

```
- RemoveAdSelectionFromOutcomesOverrideRequest(AdSelectionFromOutcomesConfig)  [API 35+]
- getAdSelectionFromOutcomesConfig(): AdSelectionFromOutcomesConfig  [API 35+]
```

## RemoveAdSelectionOverrideRequest — declaration; API 34+ (0 +, 2 *, 0 ~, 0 -)

```
* RemoveAdSelectionOverrideRequest(AdSelectionConfig)  [API 34+]
* getAdSelectionConfig(): AdSelectionConfig  [API 34+]
```

## ReportEventRequest — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* getAdSelectionId(): long  [API 35+]
* getData(): String  [API 35+]
* getInputEvent(): InputEvent  [API 35+]
* getKey(): String  [API 35+]
* getReportingDestinations(): int  [API 35+]
```

## ReportEventRequest.Builder — declaration; API 35+ (0 +, 6 *, 0 ~, 1 -)

```
- Builder(long, String, String, int)  [API 35+]
* build(): ReportEventRequest  [API 35+]
* setAdSelectionId(long): ReportEventRequest.Builder  [API 35+]
* setData(String): ReportEventRequest.Builder  [API 35+]
* setInputEvent(InputEvent): ReportEventRequest.Builder  [API 35+]
* setKey(String): ReportEventRequest.Builder  [API 35+]
* setReportingDestinations(int): ReportEventRequest.Builder  [API 35+]
```

## ReportImpressionRequest — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* ReportImpressionRequest(long)  [API 35+]
* ReportImpressionRequest(long, AdSelectionConfig)  [API 34+]
* getAdSelectionConfig(): AdSelectionConfig  [API 34+]
* getAdSelectionId(): long  [API 34+]
```

## SellerConfiguration — declaration; API 36+ (0 +, 2 *, 0 ~, 0 -)

```
* getMaximumPayloadSizeBytes(): int  [API 36+]
* getPerBuyerConfigurations(): Set  [API 36+]
```

## SellerConfiguration.Builder — declaration; API 36+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 36+]
* build(): SellerConfiguration  [API 36+]
* setMaximumPayloadSizeBytes(int): SellerConfiguration.Builder  [API 36+]
* setPerBuyerConfigurations(Set): SellerConfiguration.Builder  [API 36+]
```

## SetAppInstallAdvertisersRequest — declaration; API 35+ (0 +, 1 *, 0 ~, 0 -)

```
* getAdvertisers(): Set  [API 35+]
```

## SetAppInstallAdvertisersRequest.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): SetAppInstallAdvertisersRequest  [API 35+]
* setAdvertisers(Set): SetAppInstallAdvertisersRequest.Builder  [API 35+]
```

## SignedContextualAds — declaration; API 35+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getAdsWithBid(): List  [API 35+]
* getBuyer(): AdTechIdentifier  [API 35+]
* getDecisionLogicUri(): Uri  [API 35+]
* getSignature(): byte[]  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## SignedContextualAds.Builder — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* Builder(SignedContextualAds)  [API 35+]
* build(): SignedContextualAds  [API 35+]
* setAdsWithBid(List): SignedContextualAds.Builder  [API 35+]
* setBuyer(AdTechIdentifier): SignedContextualAds.Builder  [API 35+]
* setDecisionLogicUri(Uri): SignedContextualAds.Builder  [API 35+]
* setSignature(byte[]): SignedContextualAds.Builder  [API 35+]
```

## TestAdSelectionManager — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- overrideAdSelectionConfigRemoteInfo(AddAdSelectionOverrideRequest, Executor, OutcomeReceiver): void  [API 34+]
- overrideAdSelectionFromOutcomesConfigRemoteInfo(AddAdSelectionFromOutcomesOverrideRequest, Executor, OutcomeReceiver): void  [API 35+]
- removeAdSelectionConfigRemoteInfoOverride(RemoveAdSelectionOverrideRequest, Executor, OutcomeReceiver): void  [API 34+]
- removeAdSelectionFromOutcomesConfigRemoteInfoOverride(RemoveAdSelectionFromOutcomesOverrideRequest, Executor, OutcomeReceiver): void  [API 35+]
- resetAllAdSelectionConfigRemoteOverrides(Executor, OutcomeReceiver): void  [API 34+]
- resetAllAdSelectionFromOutcomesConfigRemoteOverrides(Executor, OutcomeReceiver): void  [API 35+]
```

## UpdateAdCounterHistogramRequest — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getAdEventType(): int  [API 35+]
* getAdSelectionId(): long  [API 35+]
* getCallerAdTech(): AdTechIdentifier  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
```

## UpdateAdCounterHistogramRequest.Builder — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(long, int, AdTechIdentifier)  [API 35+]
* build(): UpdateAdCounterHistogramRequest  [API 35+]
* setAdEventType(int): UpdateAdCounterHistogramRequest.Builder  [API 35+]
* setAdSelectionId(long): UpdateAdCounterHistogramRequest.Builder  [API 35+]
* setCallerAdTech(AdTechIdentifier): UpdateAdCounterHistogramRequest.Builder  [API 35+]
```


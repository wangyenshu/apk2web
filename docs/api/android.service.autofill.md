# android.service.autofill

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AutofillService — declaration; API 26+ (6 +, 0 *, 0 ~, 3 -)

```
- AutofillService()  [API 26+]
- getFillEventHistory(): FillEventHistory  [API 26+]
- onBind(Intent): IBinder  [API 26+]
+ onConnected(): void  [API 26+]
+ onCreate(): void  [API 26+]
+ onDisconnected(): void  [API 26+]
+ onFillRequest(FillRequest, CancellationSignal, FillCallback): void  [API 26+]
+ onSaveRequest(SaveRequest, SaveCallback): void  [API 26+]
+ onSavedDatasetsInfoRequest(SavedDatasetsInfoCallback): void  [API 31+]
```

## BatchUpdates — declaration; API 28+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## BatchUpdates.Builder — declaration; API 28+ (0 +, 0 *, 0 ~, 4 -)

```
- Builder()  [API 28+]
- build(): BatchUpdates  [API 28+]
- transformChild(int, Transformation): BatchUpdates.Builder  [API 28+]
- updateTemplate(RemoteViews): BatchUpdates.Builder  [API 28+]
```

## CharSequenceTransformation — declaration; API 27+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 27+]
- describeContents(): int  [API 27+]
- toString(): String  [API 27+]
- writeToParcel(Parcel, int): void  [API 27+]
```

## CharSequenceTransformation.Builder — declaration; API 27+ (0 +, 0 *, 0 ~, 3 -)

```
- Builder(AutofillId, Pattern, String)  [API 27+]
- addField(AutofillId, Pattern, String): CharSequenceTransformation.Builder  [API 27+]
- build(): CharSequenceTransformation  [API 27+]
```

## CustomDescription — declaration; API 27+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 27+]
- describeContents(): int  [API 27+]
- toString(): String  [API 27+]
- writeToParcel(Parcel, int): void  [API 27+]
```

## CustomDescription.Builder — declaration; API 27+ (0 +, 0 *, 0 ~, 5 -)

```
- Builder(RemoteViews)  [API 27+]
- addChild(int, Transformation): CustomDescription.Builder  [API 27+]
- addOnClickAction(int, OnClickAction): CustomDescription.Builder  [API 29+]
- batchUpdate(Validator, BatchUpdates): CustomDescription.Builder  [API 28+]
- build(): CustomDescription  [API 27+]
```

## Dataset — declaration; API 26+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## Dataset.Builder — declaration; API 26+ (0 +, 0 *, 0 ~, 19 -)

```
- Builder()  [API 26+]
- Builder(Presentations)  [API 33+]
- Builder(RemoteViews)  [API 26+, deprecated in 33]
- build(): Dataset  [API 26+]
- setAuthentication(IntentSender): Dataset.Builder  [API 26+]
- setField(AutofillId, Field): Dataset.Builder  [API 33+]
- setField(String, Field): Dataset.Builder  [API 34+]
- setFieldForAllHints(Field): Dataset.Builder  [API 34+]
- setId(String): Dataset.Builder  [API 26+]
- setInlinePresentation(InlinePresentation): Dataset.Builder  [API 30+, deprecated in 33]
- setInlinePresentation(InlinePresentation, InlinePresentation): Dataset.Builder  [API 31+, deprecated in 33]
- setValue(AutofillId, AutofillValue): Dataset.Builder  [API 26+, deprecated in 33]
- setValue(AutofillId, AutofillValue, Pattern): Dataset.Builder  [API 28+, deprecated in 33]
- setValue(AutofillId, AutofillValue, Pattern, RemoteViews): Dataset.Builder  [API 28+, deprecated in 33]
- setValue(AutofillId, AutofillValue, Pattern, RemoteViews, InlinePresentation): Dataset.Builder  [API 30+, deprecated in 33]
- setValue(AutofillId, AutofillValue, Pattern, RemoteViews, InlinePresentation, InlinePresentation): Dataset.Builder  [API 31+, deprecated in 33]
- setValue(AutofillId, AutofillValue, RemoteViews): Dataset.Builder  [API 26+, deprecated in 33]
- setValue(AutofillId, AutofillValue, RemoteViews, InlinePresentation): Dataset.Builder  [API 30+, deprecated in 33]
- setValue(AutofillId, AutofillValue, RemoteViews, InlinePresentation, InlinePresentation): Dataset.Builder  [API 31+, deprecated in 33]
```

## DateTransformation — declaration; API 28+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 28+]
- DateTransformation(AutofillId, DateFormat)  [API 28+]
- describeContents(): int  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## DateValueSanitizer — declaration; API 28+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 28+]
- DateValueSanitizer(DateFormat)  [API 28+]
- describeContents(): int  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## Field — declaration; API 33+ (0 +, 3 *, 0 ~, 0 -)

```
* getFilter(): Pattern  [API 33+]
* getPresentations(): Presentations  [API 33+]
* getValue(): AutofillValue  [API 33+]
```

## Field.Builder — declaration; API 33+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): Field  [API 33+]
* setFilter(Pattern): Field.Builder  [API 33+]
* setPresentations(Presentations): Field.Builder  [API 33+]
* setValue(AutofillValue): Field.Builder  [API 33+]
```

## FieldClassification — declaration; API 28+ (0 +, 0 *, 0 ~, 2 -)

```
- getMatches(): List  [API 28+]
- toString(): String  [API 28+]
```

## FieldClassification.Match — declaration; API 28+ (0 +, 0 *, 0 ~, 3 -)

```
- getCategoryId(): String  [API 28+]
- getScore(): float  [API 28+]
- toString(): String  [API 28+]
```

## FillCallback — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ onFailure(CharSequence): void  [API 26+]
+ onSuccess(FillResponse): void  [API 26+]
```

## FillContext — declaration; API 26+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getFocusedId(): AutofillId  [API 29+]
- getRequestId(): int  [API 26+]
- getStructure(): AssistStructure  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## FillEventHistory — declaration; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getClientState(): Bundle  [API 26+, deprecated in 28]
- getEvents(): List  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## FillEventHistory.Event — declaration; API 26+ (0 +, 0 *, 0 ~, 11 -)

```
- getChangedFields(): Map  [API 28+]
- getClientState(): Bundle  [API 28+]
- getDatasetId(): String  [API 26+]
- getFieldsClassification(): Map  [API 28+]
- getIgnoredDatasetIds(): Set  [API 28+]
- getManuallyEnteredField(): Map  [API 28+]
- getNoSaveUiReason(): int  [API 31+]
- getSelectedDatasetIds(): Set  [API 28+]
- getType(): int  [API 26+]
- getUiType(): int  [API 33+]
- toString(): String  [API 26+]
```

## FillRequest — declaration; API 26+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getClientState(): Bundle  [API 26+]
- getDelayedFillIntentSender(): IntentSender  [API 33+]
- getFillContexts(): List  [API 26+]
- getFlags(): int  [API 26+]
- getHints(): List  [API 34+]
- getId(): int  [API 26+]
- getInlineSuggestionsRequest(): InlineSuggestionsRequest  [API 30+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## FillResponse — declaration; API 26+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## FillResponse.Builder — declaration; API 26+ (0 +, 0 *, 0 ~, 24 -)

```
- Builder()  [API 26+]
- addDataset(Dataset): FillResponse.Builder  [API 26+]
- build(): FillResponse  [API 26+]
- disableAutofill(long): FillResponse.Builder  [API 28+]
- setAuthentication(AutofillId[], IntentSender, Presentations): FillResponse.Builder  [API 33+]
- setAuthentication(AutofillId[], IntentSender, RemoteViews): FillResponse.Builder  [API 26+, deprecated in 33]
- setAuthentication(AutofillId[], IntentSender, RemoteViews, InlinePresentation): FillResponse.Builder  [API 30+, deprecated in 33]
- setAuthentication(AutofillId[], IntentSender, RemoteViews, InlinePresentation, InlinePresentation): FillResponse.Builder  [API 31+, deprecated in 33]
- setClientState(Bundle): FillResponse.Builder  [API 26+]
- setDetectedFieldClassifications(Set): FillResponse.Builder  [API 34+]
- setDialogHeader(RemoteViews): FillResponse.Builder  [API 33+]
- setFieldClassificationIds(AutofillId[]): FillResponse.Builder  [API 28+]
- setFillDialogTriggerIds(AutofillId[]): FillResponse.Builder  [API 33+]
- setFlags(int): FillResponse.Builder  [API 28+]
- setFooter(RemoteViews): FillResponse.Builder  [API 28+]
- setHeader(RemoteViews): FillResponse.Builder  [API 28+]
- setIconResourceId(int): FillResponse.Builder  [API 34+]
- setIgnoredIds(AutofillId[]): FillResponse.Builder  [API 26+]
- setPresentationCancelIds(int[]): FillResponse.Builder  [API 30+]
- setSaveInfo(SaveInfo): FillResponse.Builder  [API 26+]
- setServiceDisplayNameResourceId(int): FillResponse.Builder  [API 34+]
- setShowFillDialogIcon(boolean): FillResponse.Builder  [API 34+]
- setShowSaveDialogIcon(boolean): FillResponse.Builder  [API 34+]
- setUserData(UserData): FillResponse.Builder  [API 29+]
```

## ImageTransformation — declaration; API 27+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 27+]
- describeContents(): int  [API 27+]
- toString(): String  [API 27+]
- writeToParcel(Parcel, int): void  [API 27+]
```

## ImageTransformation.Builder — declaration; API 27+ (0 +, 0 *, 0 ~, 5 -)

```
- Builder(AutofillId, Pattern, int)  [API 27+, deprecated in 28]
- Builder(AutofillId, Pattern, int, CharSequence)  [API 28+]
- addOption(Pattern, int): ImageTransformation.Builder  [API 27+, deprecated in 28]
- addOption(Pattern, int, CharSequence): ImageTransformation.Builder  [API 28+]
- build(): ImageTransformation  [API 27+]
```

## InlinePresentation — declaration; API 30+ (0 +, 9 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 30+]
* InlinePresentation(Slice, InlinePresentationSpec, boolean)  [API 30+]
- createTooltipPresentation(Slice, InlinePresentationSpec): InlinePresentation [static]  [API 31+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getInlinePresentationSpec(): InlinePresentationSpec  [API 30+]
* getSlice(): Slice  [API 30+]
* hashCode(): int  [API 30+]
* isPinned(): boolean  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## LuhnChecksumValidator — declaration; API 27+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 27+]
- LuhnChecksumValidator(AutofillId[])  [API 27+]
- describeContents(): int  [API 27+]
- toString(): String  [API 27+]
- writeToParcel(Parcel, int): void  [API 27+]
```

## OnClickAction — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

## Presentations — declaration; API 33+ (0 +, 4 *, 0 ~, 0 -)

```
* getDialogPresentation(): RemoteViews  [API 33+]
* getInlinePresentation(): InlinePresentation  [API 33+]
* getInlineTooltipPresentation(): InlinePresentation  [API 33+]
* getMenuPresentation(): RemoteViews  [API 33+]
```

## Presentations.Builder — declaration; API 33+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): Presentations  [API 33+]
* setDialogPresentation(RemoteViews): Presentations.Builder  [API 33+]
* setInlinePresentation(InlinePresentation): Presentations.Builder  [API 33+]
* setInlineTooltipPresentation(InlinePresentation): Presentations.Builder  [API 33+]
* setMenuPresentation(RemoteViews): Presentations.Builder  [API 33+]
```

## RegexValidator — declaration; API 27+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 27+]
- RegexValidator(AutofillId, Pattern)  [API 27+]
- describeContents(): int  [API 27+]
- toString(): String  [API 27+]
- writeToParcel(Parcel, int): void  [API 27+]
```

## Sanitizer — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## SaveCallback — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ onFailure(CharSequence): void  [API 26+]
+ onSuccess(): void  [API 26+]
+ onSuccess(IntentSender): void  [API 28+]
```

## SaveInfo — declaration; API 26+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## SaveInfo.Builder — declaration; API 26+ (0 +, 0 *, 0 ~, 12 -)

```
- Builder(int)  [API 27+]
- Builder(int, AutofillId[])  [API 26+]
- addSanitizer(Sanitizer, AutofillId[]): SaveInfo.Builder  [API 28+]
- build(): SaveInfo  [API 26+]
- setCustomDescription(CustomDescription): SaveInfo.Builder  [API 27+]
- setDescription(CharSequence): SaveInfo.Builder  [API 26+]
- setFlags(int): SaveInfo.Builder  [API 26+]
- setNegativeAction(int, IntentSender): SaveInfo.Builder  [API 26+]
- setOptionalIds(AutofillId[]): SaveInfo.Builder  [API 26+]
- setPositiveAction(int): SaveInfo.Builder  [API 30+]
- setTriggerId(AutofillId): SaveInfo.Builder  [API 28+]
- setValidator(Validator): SaveInfo.Builder  [API 27+]
```

## SaveRequest — declaration; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getClientState(): Bundle  [API 26+]
- getDatasetIds(): List  [API 28+]
- getFillContexts(): List  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## SavedDatasetsInfo — declaration; API 31+ (0 +, 6 *, 0 ~, 0 -)

```
* SavedDatasetsInfo(String, int)  [API 31+]
* equals(Object): boolean  [API 31+]
* getCount(): int  [API 31+]
* getType(): String  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
```

## SavedDatasetsInfoCallback — declaration; API 31+ (2 +, 0 *, 0 ~, 0 -)

```
+ onError(int): void  [API 31+]
+ onSuccess(Set): void  [API 31+]
```

## TextValueSanitizer — declaration; API 28+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 28+]
- TextValueSanitizer(Pattern, String)  [API 28+]
- describeContents(): int  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## Transformation — declaration; API 27+ (0 +, 0 *, 0 ~, 0 -)

## UserData — declaration; API 28+ (0 +, 1 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- getFieldClassificationAlgorithm(): String  [API 28+]
- getFieldClassificationAlgorithmForCategory(String): String  [API 29+]
* getId(): String  [API 28+]
- getMaxCategoryCount(): int [static]  [API 28+]
- getMaxFieldClassificationIdsSize(): int [static]  [API 28+]
- getMaxUserDataSize(): int [static]  [API 28+]
- getMaxValueLength(): int [static]  [API 28+]
- getMinValueLength(): int [static]  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## UserData.Builder — declaration; API 28+ (0 +, 1 *, 0 ~, 4 -)

```
- Builder(String, String, String)  [API 28+]
- add(String, String): UserData.Builder  [API 28+]
* build(): UserData  [API 28+]
- setFieldClassificationAlgorithm(String, Bundle): UserData.Builder  [API 28+]
- setFieldClassificationAlgorithmForCategory(String, String, Bundle): UserData.Builder  [API 29+]
```

## Validator — declaration; API 27+ (0 +, 0 *, 0 ~, 0 -)

## Validators — declaration; API 27+ (0 +, 0 *, 0 ~, 3 -)

```
- and(Validator[]): Validator [static]  [API 27+]
- not(Validator): Validator [static]  [API 28+]
- or(Validator[]): Validator [static]  [API 27+]
```

## VisibilitySetterAction — declaration; API 29+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## VisibilitySetterAction.Builder — declaration; API 29+ (0 +, 0 *, 0 ~, 3 -)

```
- Builder(int, int)  [API 29+]
- build(): VisibilitySetterAction  [API 29+]
- setVisibility(int, int): VisibilitySetterAction.Builder  [API 29+]
```


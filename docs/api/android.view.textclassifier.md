# android.view.textclassifier

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ConversationAction — declaration; API 29+ (0 +, 4 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
* getAction(): RemoteAction  [API 29+]
* getConfidenceScore(): float  [API 29+]
* getExtras(): Bundle  [API 29+]
* getTextReply(): CharSequence  [API 29+]
- getType(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## ConversationAction.Builder — declaration; API 29+ (0 +, 5 *, 0 ~, 1 -)

```
- Builder(String)  [API 29+]
* build(): ConversationAction  [API 29+]
* setAction(RemoteAction): ConversationAction.Builder  [API 29+]
* setConfidenceScore(float): ConversationAction.Builder  [API 29+]
* setExtras(Bundle): ConversationAction.Builder  [API 29+]
* setTextReply(CharSequence): ConversationAction.Builder  [API 29+]
```

## ConversationActions — declaration; API 29+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 29+]
* ConversationActions(List, String)  [API 29+]
* describeContents(): int  [API 29+]
* getConversationActions(): List  [API 29+]
* getId(): String  [API 29+]
* writeToParcel(Parcel, int): void  [API 29+]
```

## ConversationActions.Message — declaration; API 29+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 29+]
- PERSON_USER_OTHERS [field]  [API 29+]
- PERSON_USER_SELF [field]  [API 29+]
* describeContents(): int  [API 29+]
* getAuthor(): Person  [API 29+]
* getExtras(): Bundle  [API 29+]
* getReferenceTime(): ZonedDateTime  [API 29+]
* getText(): CharSequence  [API 29+]
* writeToParcel(Parcel, int): void  [API 29+]
```

## ConversationActions.Message.Builder — declaration; API 29+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(Person)  [API 29+]
* build(): ConversationActions.Message  [API 29+]
* setExtras(Bundle): ConversationActions.Message.Builder  [API 29+]
* setReferenceTime(ZonedDateTime): ConversationActions.Message.Builder  [API 29+]
* setText(CharSequence): ConversationActions.Message.Builder  [API 29+]
```

## ConversationActions.Request — declaration; API 29+ (0 +, 5 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- getCallingPackageName(): String  [API 29+]
* getConversation(): List  [API 29+]
* getExtras(): Bundle  [API 29+]
* getHints(): List  [API 29+]
* getMaxSuggestions(): int  [API 29+]
* getTypeConfig(): TextClassifier.EntityConfig  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## ConversationActions.Request.Builder — declaration; API 29+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder(List)  [API 29+]
* build(): ConversationActions.Request  [API 29+]
* setExtras(Bundle): ConversationActions.Request.Builder  [API 29+]
* setHints(List): ConversationActions.Request.Builder  [API 29+]
* setMaxSuggestions(int): ConversationActions.Request.Builder  [API 29+]
* setTypeConfig(TextClassifier.EntityConfig): ConversationActions.Request.Builder  [API 29+]
```

## SelectionEvent — declaration; API 28+ (0 +, 0 *, 0 ~, 28 -)

```
- CREATOR [field]  [API 28+]
- createSelectionActionEvent(int, int, int): SelectionEvent [static]  [API 28+]
- createSelectionActionEvent(int, int, int, TextClassification): SelectionEvent [static]  [API 28+]
- createSelectionModifiedEvent(int, int): SelectionEvent [static]  [API 28+]
- createSelectionModifiedEvent(int, int, TextClassification): SelectionEvent [static]  [API 28+]
- createSelectionModifiedEvent(int, int, TextSelection): SelectionEvent [static]  [API 28+]
- createSelectionStartedEvent(int, int): SelectionEvent [static]  [API 28+]
- describeContents(): int  [API 28+]
- equals(Object): boolean  [API 28+]
- getDurationSincePreviousEvent(): long  [API 28+]
- getDurationSinceSessionStart(): long  [API 28+]
- getEnd(): int  [API 28+]
- getEntityType(): String  [API 28+]
- getEventIndex(): int  [API 28+]
- getEventTime(): long  [API 28+]
- getEventType(): int  [API 28+]
- getInvocationMethod(): int  [API 28+]
- getPackageName(): String  [API 28+]
- getResultId(): String  [API 28+]
- getSessionId(): TextClassificationSessionId  [API 28+]
- getSmartEnd(): int  [API 28+]
- getSmartStart(): int  [API 28+]
- getStart(): int  [API 28+]
- getWidgetType(): String  [API 28+]
- getWidgetVersion(): String  [API 28+]
- hashCode(): int  [API 28+]
- isTerminal(int): boolean [static]  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## TextClassification — declaration; API 26+ (0 +, 7 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 26+]
* getActions(): List  [API 28+]
- getConfidenceScore(String): float  [API 26+]
- getEntity(int): String  [API 26+]
- getEntityCount(): int  [API 26+]
* getExtras(): Bundle  [API 29+]
* getIcon(): Drawable  [API 26+, deprecated in 28]
* getId(): String  [API 28+]
* getIntent(): Intent  [API 26+, deprecated in 28]
- getLabel(): CharSequence  [API 26+, deprecated in 28]
* getOnClickListener(): View.OnClickListener  [API 26+, deprecated in 28]
* getText(): String  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## TextClassification.Builder — declaration; API 26+ (0 +, 9 *, 0 ~, 2 -)

```
* Builder()  [API 26+]
* addAction(RemoteAction): TextClassification.Builder  [API 28+]
* build(): TextClassification  [API 26+]
- setEntityType(String, float): TextClassification.Builder  [API 26+]
* setExtras(Bundle): TextClassification.Builder  [API 29+]
* setIcon(Drawable): TextClassification.Builder  [API 26+, deprecated in 28]
* setId(String): TextClassification.Builder  [API 28+]
* setIntent(Intent): TextClassification.Builder  [API 26+, deprecated in 28]
- setLabel(String): TextClassification.Builder  [API 26+, deprecated in 28]
* setOnClickListener(View.OnClickListener): TextClassification.Builder  [API 26+, deprecated in 28]
* setText(String): TextClassification.Builder  [API 26+]
```

## TextClassification.Request — declaration; API 28+ (0 +, 6 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- getCallingPackageName(): String  [API 29+]
* getDefaultLocales(): LocaleList  [API 28+]
* getEndIndex(): int  [API 28+]
* getExtras(): Bundle  [API 29+]
* getReferenceTime(): ZonedDateTime  [API 28+]
* getStartIndex(): int  [API 28+]
* getText(): CharSequence  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## TextClassification.Request.Builder — declaration; API 28+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(CharSequence, int, int)  [API 28+]
* build(): TextClassification.Request  [API 28+]
* setDefaultLocales(LocaleList): TextClassification.Request.Builder  [API 28+]
* setExtras(Bundle): TextClassification.Request.Builder  [API 29+]
* setReferenceTime(ZonedDateTime): TextClassification.Request.Builder  [API 28+]
```

## TextClassificationContext — declaration; API 28+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 28+]
* describeContents(): int  [API 28+]
* getPackageName(): String  [API 28+]
* getWidgetType(): String  [API 28+]
* getWidgetVersion(): String  [API 28+]
* toString(): String  [API 28+]
* writeToParcel(Parcel, int): void  [API 28+]
```

## TextClassificationContext.Builder — declaration; API 28+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(String, String)  [API 28+]
* build(): TextClassificationContext  [API 28+]
* setWidgetVersion(String): TextClassificationContext.Builder  [API 28+]
```

## TextClassificationManager — framework; API 26+ (0 +, 0 *, 0 ~, 4 -)

```
- createTextClassificationSession(TextClassificationContext): TextClassifier  [API 28+]
- getTextClassifier(): TextClassifier  [API 26+]
- setTextClassificationSessionFactory(TextClassificationSessionFactory): void  [API 28+]
- setTextClassifier(TextClassifier): void  [API 26+]
```

## TextClassificationSessionFactory — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ createTextClassificationSession(TextClassificationContext): TextClassifier  [API 28+]
```

## TextClassificationSessionId — declaration; API 28+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- equals(Object): boolean  [API 28+]
- getValue(): String  [API 30+]
- hashCode(): int  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## TextClassifier — declaration; API 26+ (2 +, 0 *, 0 ~, 10 -)

```
- NO_OP [field]  [API 26+]
- classifyText(CharSequence, int, int, LocaleList): TextClassification  [API 26+]
- classifyText(TextClassification.Request): TextClassification  [API 28+]
- destroy(): void  [API 28+]
- detectLanguage(TextLanguage.Request): TextLanguage  [API 29+]
- generateLinks(TextLinks.Request): TextLinks  [API 28+]
- getMaxGenerateLinksTextLength(): int  [API 28+]
- isDestroyed(): boolean  [API 28+]
+ onSelectionEvent(SelectionEvent): void  [API 28+]
+ onTextClassifierEvent(TextClassifierEvent): void  [API 29+]
- suggestConversationActions(ConversationActions.Request): ConversationActions  [API 29+]
- suggestSelection(CharSequence, int, int, LocaleList): TextSelection  [API 26+]
- suggestSelection(TextSelection.Request): TextSelection  [API 28+]
```

## TextClassifier.EntityConfig — declaration; API 28+ (0 +, 3 *, 0 ~, 5 -)

```
+ CREATOR [field]  [API 28+]
- create(Collection, Collection, Collection): TextClassifier.EntityConfig [static]  [API 28+, deprecated in 29]
- createWithExplicitEntityList(Collection): TextClassifier.EntityConfig [static]  [API 28+, deprecated in 29]
- createWithHints(Collection): TextClassifier.EntityConfig [static]  [API 28+, deprecated in 29]
* describeContents(): int  [API 28+]
* getHints(): Collection  [API 28+]
- resolveEntityListModifications(Collection): Collection  [API 28+]
- shouldIncludeTypesFromTextClassifier(): boolean  [API 29+]
* writeToParcel(Parcel, int): void  [API 28+]
```

## TextClassifier.EntityConfig.Builder — declaration; API 29+ (0 +, 3 *, 0 ~, 3 -)

```
* Builder()  [API 29+]
* build(): TextClassifier.EntityConfig  [API 29+]
- includeTypesFromTextClassifier(boolean): TextClassifier.EntityConfig.Builder  [API 29+]
- setExcludedTypes(Collection): TextClassifier.EntityConfig.Builder  [API 29+]
* setHints(Collection): TextClassifier.EntityConfig.Builder  [API 29+]
- setIncludedTypes(Collection): TextClassifier.EntityConfig.Builder  [API 29+]
```

## TextClassifierEvent — declaration; API 29+ (0 +, 1 *, 0 ~, 13 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- getActionIndices(): int[]  [API 29+]
- getEntityTypes(): String[]  [API 29+]
- getEventCategory(): int  [API 29+]
- getEventContext(): TextClassificationContext  [API 29+]
- getEventIndex(): int  [API 29+]
* getEventType(): int  [API 29+]
- getExtras(): Bundle  [API 29+]
- getLocale(): ULocale  [API 29+]
- getModelName(): String  [API 29+]
- getResultId(): String  [API 29+]
- getScores(): float[]  [API 29+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## TextClassifierEvent.Builder — declaration; API 29+ (0 +, 0 *, 0 ~, 9 -)

```
- setActionIndices(int[]): TextClassifierEvent.Builder  [API 29+]
- setEntityTypes(String[]): TextClassifierEvent.Builder  [API 29+]
- setEventContext(TextClassificationContext): TextClassifierEvent.Builder  [API 29+]
- setEventIndex(int): TextClassifierEvent.Builder  [API 29+]
- setExtras(Bundle): TextClassifierEvent.Builder  [API 29+]
- setLocale(ULocale): TextClassifierEvent.Builder  [API 29+]
- setModelName(String): TextClassifierEvent.Builder  [API 29+]
- setResultId(String): TextClassifierEvent.Builder  [API 29+]
- setScores(float[]): TextClassifierEvent.Builder  [API 29+]
```

## TextClassifierEvent.ConversationActionsEvent — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
```

## TextClassifierEvent.ConversationActionsEvent.Builder — declaration; API 29+ (0 +, 2 *, 0 ~, 0 -)

```
* Builder(int)  [API 29+]
* build(): TextClassifierEvent.ConversationActionsEvent  [API 29+]
```

## TextClassifierEvent.LanguageDetectionEvent — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
```

## TextClassifierEvent.LanguageDetectionEvent.Builder — declaration; API 29+ (0 +, 2 *, 0 ~, 0 -)

```
* Builder(int)  [API 29+]
* build(): TextClassifierEvent.LanguageDetectionEvent  [API 29+]
```

## TextClassifierEvent.TextLinkifyEvent — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
```

## TextClassifierEvent.TextLinkifyEvent.Builder — declaration; API 29+ (0 +, 2 *, 0 ~, 0 -)

```
* Builder(int)  [API 29+]
* build(): TextClassifierEvent.TextLinkifyEvent  [API 29+]
```

## TextClassifierEvent.TextSelectionEvent — declaration; API 29+ (0 +, 4 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 29+]
* getRelativeSuggestedWordEndIndex(): int  [API 29+]
* getRelativeSuggestedWordStartIndex(): int  [API 29+]
* getRelativeWordEndIndex(): int  [API 29+]
* getRelativeWordStartIndex(): int  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## TextClassifierEvent.TextSelectionEvent.Builder — declaration; API 29+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder(int)  [API 29+]
* build(): TextClassifierEvent.TextSelectionEvent  [API 29+]
* setRelativeSuggestedWordEndIndex(int): TextClassifierEvent.TextSelectionEvent.Builder  [API 29+]
* setRelativeSuggestedWordStartIndex(int): TextClassifierEvent.TextSelectionEvent.Builder  [API 29+]
* setRelativeWordEndIndex(int): TextClassifierEvent.TextSelectionEvent.Builder  [API 29+]
* setRelativeWordStartIndex(int): TextClassifierEvent.TextSelectionEvent.Builder  [API 29+]
```

## TextLanguage — declaration; API 29+ (0 +, 2 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- getConfidenceScore(ULocale): float  [API 29+]
* getExtras(): Bundle  [API 29+]
* getId(): String  [API 29+]
- getLocale(int): ULocale  [API 29+]
- getLocaleHypothesisCount(): int  [API 29+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## TextLanguage.Builder — declaration; API 29+ (0 +, 4 *, 0 ~, 1 -)

```
* Builder()  [API 29+]
* build(): TextLanguage  [API 29+]
- putLocale(ULocale, float): TextLanguage.Builder  [API 29+]
* setExtras(Bundle): TextLanguage.Builder  [API 29+]
* setId(String): TextLanguage.Builder  [API 29+]
```

## TextLanguage.Request — declaration; API 29+ (0 +, 2 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- getCallingPackageName(): String  [API 29+]
* getExtras(): Bundle  [API 29+]
* getText(): CharSequence  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## TextLanguage.Request.Builder — declaration; API 29+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(CharSequence)  [API 29+]
* build(): TextLanguage.Request  [API 29+]
* setExtras(Bundle): TextLanguage.Request.Builder  [API 29+]
```

## TextLinks — declaration; API 28+ (0 +, 1 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 28+]
- apply(Spannable, int, Function): int  [API 28+]
- describeContents(): int  [API 28+]
* getExtras(): Bundle  [API 29+]
- getLinks(): Collection  [API 28+]
- getText(): CharSequence  [API 30+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## TextLinks.Builder — declaration; API 28+ (0 +, 2 *, 0 ~, 4 -)

```
- Builder(String)  [API 28+]
- addLink(int, int, Map): TextLinks.Builder  [API 28+]
- addLink(int, int, Map, Bundle): TextLinks.Builder  [API 29+]
* build(): TextLinks  [API 28+]
- clearTextLinks(): TextLinks.Builder  [API 28+]
* setExtras(Bundle): TextLinks.Builder  [API 29+]
```

## TextLinks.Request — declaration; API 28+ (0 +, 5 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- getCallingPackageName(): String  [API 29+]
* getDefaultLocales(): LocaleList  [API 28+]
* getEntityConfig(): TextClassifier.EntityConfig  [API 28+]
* getExtras(): Bundle  [API 29+]
* getReferenceTime(): ZonedDateTime  [API 30+]
* getText(): CharSequence  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## TextLinks.Request.Builder — declaration; API 28+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder(CharSequence)  [API 28+]
* build(): TextLinks.Request  [API 28+]
* setDefaultLocales(LocaleList): TextLinks.Request.Builder  [API 28+]
* setEntityConfig(TextClassifier.EntityConfig): TextLinks.Request.Builder  [API 28+]
* setExtras(Bundle): TextLinks.Request.Builder  [API 29+]
* setReferenceTime(ZonedDateTime): TextLinks.Request.Builder  [API 30+]
```

## TextLinks.TextLink — declaration; API 28+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- getConfidenceScore(String): float  [API 28+]
- getEnd(): int  [API 28+]
- getEntity(int): String  [API 28+]
- getEntityCount(): int  [API 28+]
- getExtras(): Bundle  [API 29+]
- getStart(): int  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## TextLinks.TextLinkSpan — declaration; API 28+ (0 +, 0 *, 0 ~, 3 -)

```
- TextLinkSpan(TextLinks.TextLink)  [API 28+]
- getTextLink(): TextLinks.TextLink  [API 28+]
- onClick(View): void  [API 28+]
```

## TextSelection — declaration; API 26+ (0 +, 3 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 26+]
- getConfidenceScore(String): float  [API 26+]
- getEntity(int): String  [API 26+]
- getEntityCount(): int  [API 26+]
* getExtras(): Bundle  [API 29+]
* getId(): String  [API 28+]
- getSelectionEndIndex(): int  [API 26+]
- getSelectionStartIndex(): int  [API 26+]
* getTextClassification(): TextClassification  [API 31+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## TextSelection.Builder — declaration; API 26+ (0 +, 4 *, 0 ~, 2 -)

```
- Builder(int, int)  [API 26+]
* build(): TextSelection  [API 26+]
- setEntityType(String, float): TextSelection.Builder  [API 26+]
* setExtras(Bundle): TextSelection.Builder  [API 29+]
* setId(String): TextSelection.Builder  [API 28+]
* setTextClassification(TextClassification): TextSelection.Builder  [API 31+]
```

## TextSelection.Request — declaration; API 28+ (0 +, 5 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- getCallingPackageName(): String  [API 29+]
* getDefaultLocales(): LocaleList  [API 28+]
* getEndIndex(): int  [API 28+]
* getExtras(): Bundle  [API 29+]
* getStartIndex(): int  [API 28+]
* getText(): CharSequence  [API 28+]
- shouldIncludeTextClassification(): boolean  [API 31+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## TextSelection.Request.Builder — declaration; API 28+ (0 +, 4 *, 0 ~, 1 -)

```
* Builder(CharSequence, int, int)  [API 28+]
* build(): TextSelection.Request  [API 28+]
* setDefaultLocales(LocaleList): TextSelection.Request.Builder  [API 28+]
* setExtras(Bundle): TextSelection.Request.Builder  [API 29+]
- setIncludeTextClassification(boolean): TextSelection.Request.Builder  [API 31+]
```


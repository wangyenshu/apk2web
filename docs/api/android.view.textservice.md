# android.view.textservice

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## SentenceSuggestionsInfo — declaration; API 16+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 16+]
- SentenceSuggestionsInfo(Parcel)  [API 16+]
- SentenceSuggestionsInfo(SuggestionsInfo[], int[], int[])  [API 16+]
- describeContents(): int  [API 16+]
- getLengthAt(int): int  [API 16+]
- getOffsetAt(int): int  [API 16+]
- getSuggestionsCount(): int  [API 16+]
- getSuggestionsInfoAt(int): SuggestionsInfo  [API 16+]
- writeToParcel(Parcel, int): void  [API 16+]
```

## SpellCheckerInfo — declaration; API 14+ (0 +, 0 *, 0 ~, 11 -)

```
- CREATOR [field]  [API 14+]
- describeContents(): int  [API 14+]
- getComponent(): ComponentName  [API 14+]
- getId(): String  [API 14+]
- getPackageName(): String  [API 14+]
- getServiceInfo(): ServiceInfo  [API 14+]
- getSettingsActivity(): String  [API 14+]
- getSubtypeAt(int): SpellCheckerSubtype  [API 14+]
- getSubtypeCount(): int  [API 14+]
- loadIcon(PackageManager): Drawable  [API 14+]
- loadLabel(PackageManager): CharSequence  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```

## SpellCheckerSession — declaration; API 14+ (0 +, 0 *, 0 ~, 8 -)

```
- cancel(): void  [API 15+]
- close(): void  [API 14+]
- finalize(): void  [API 14+]
- getSentenceSuggestions(TextInfo[], int): void  [API 16+]
- getSpellChecker(): SpellCheckerInfo  [API 14+]
- getSuggestions(TextInfo, int): void  [API 14+, deprecated in 16]
- getSuggestions(TextInfo[], int, boolean): void  [API 14+, deprecated in 16]
- isSessionDisconnected(): boolean  [API 14+]
```

## SpellCheckerSession.SpellCheckerSessionListener — declaration; API 14+ (2 +, 0 *, 0 ~, 0 -)

```
+ onGetSentenceSuggestions(SentenceSuggestionsInfo[]): void  [API 16+]
+ onGetSuggestions(SuggestionsInfo[]): void  [API 14+]
```

## SpellCheckerSession.SpellCheckerSessionParams — declaration; API 31+ (0 +, 3 *, 0 ~, 1 -)

```
* getExtras(): Bundle  [API 31+]
* getLocale(): Locale  [API 31+]
* getSupportedAttributes(): int  [API 31+]
- shouldReferToSpellCheckerLanguageSettings(): boolean  [API 31+]
```

## SpellCheckerSession.SpellCheckerSessionParams.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 1 -)

```
* Builder()  [API 31+]
* build(): SpellCheckerSession.SpellCheckerSessionParams  [API 31+]
* setExtras(Bundle): SpellCheckerSession.SpellCheckerSessionParams.Builder  [API 31+]
* setLocale(Locale): SpellCheckerSession.SpellCheckerSessionParams.Builder  [API 31+]
- setShouldReferToSpellCheckerLanguageSettings(boolean): SpellCheckerSession.SpellCheckerSessionParams.Builder  [API 31+]
* setSupportedAttributes(int): SpellCheckerSession.SpellCheckerSessionParams.Builder  [API 31+]
```

## SpellCheckerSubtype — declaration; API 14+ (0 +, 0 *, 0 ~, 12 -)

```
- CREATOR [field]  [API 14+]
- SpellCheckerSubtype(int, String, String)  [API 14+, deprecated in 24]
- containsExtraValueKey(String): boolean  [API 16+]
- describeContents(): int  [API 14+]
- equals(Object): boolean  [API 14+]
- getDisplayName(Context, String, ApplicationInfo): CharSequence  [API 14+]
- getExtraValue(): String  [API 14+]
- getExtraValueOf(String): String  [API 16+]
- getLanguageTag(): String  [API 24+]
- getLocale(): String  [API 14+, deprecated in 24]
- getNameResId(): int  [API 14+]
- hashCode(): int  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```

## SuggestionsInfo — declaration; API 14+ (0 +, 0 *, 0 ~, 11 -)

```
- CREATOR [field]  [API 14+]
- SuggestionsInfo(Parcel)  [API 14+]
- SuggestionsInfo(int, String[])  [API 14+]
- SuggestionsInfo(int, String[], int, int)  [API 14+]
- describeContents(): int  [API 14+]
- getCookie(): int  [API 14+]
- getSequence(): int  [API 14+]
- getSuggestionAt(int): String  [API 14+]
- getSuggestionsAttributes(): int  [API 14+]
- getSuggestionsCount(): int  [API 14+]
- setCookieAndSequence(int, int): void  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```

## TextInfo — declaration; API 14+ (0 +, 2 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 14+]
- TextInfo(CharSequence, int, int, int, int)  [API 21+]
- TextInfo(Parcel)  [API 14+]
* TextInfo(String)  [API 14+]
- TextInfo(String, int, int)  [API 14+]
- describeContents(): int  [API 14+]
- getCharSequence(): CharSequence  [API 21+]
- getCookie(): int  [API 14+]
- getSequence(): int  [API 14+]
* getText(): String  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```

## TextServicesManager — framework; API 14+ (0 +, 0 *, 0 ~, 5 -)

```
- getCurrentSpellCheckerInfo(): SpellCheckerInfo  [API 31+]
- getEnabledSpellCheckerInfos(): List  [API 31+]
- isSpellCheckerEnabled(): boolean  [API 31+]
- newSpellCheckerSession(Bundle, Locale, SpellCheckerSession.SpellCheckerSessionListener, boolean): SpellCheckerSession  [API 14+]
- newSpellCheckerSession(SpellCheckerSession.SpellCheckerSessionParams, Executor, SpellCheckerSession.SpellCheckerSessionListener): SpellCheckerSession  [API 31+]
```


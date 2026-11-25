# android.service.textservice

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## SpellCheckerService — declaration; API 14+ (1 +, 0 *, 0 ~, 2 -)

```
- SpellCheckerService()  [API 14+]
+ createSession(): SpellCheckerService.Session  [API 14+]
- onBind(Intent): IBinder  [API 14+]
```

## SpellCheckerService.Session — declaration; API 14+ (4 +, 0 *, 0 ~, 6 -)

```
- Session()  [API 14+]
- getBundle(): Bundle  [API 14+]
- getLocale(): String  [API 14+]
- getSupportedAttributes(): int  [API 31+]
+ onCancel(): void  [API 14+]
+ onClose(): void  [API 15+]
+ onCreate(): void  [API 14+]
- onGetSentenceSuggestionsMultiple(TextInfo[], int): SentenceSuggestionsInfo[]  [API 16+]
+ onGetSuggestions(TextInfo, int): SuggestionsInfo  [API 14+]
- onGetSuggestionsMultiple(TextInfo[], int, boolean): SuggestionsInfo[]  [API 14+]
```


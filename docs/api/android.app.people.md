# android.app.people

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ConversationStatus — declaration; API 31+ (0 +, 12 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getActivity(): int  [API 31+]
* getAvailability(): int  [API 31+]
* getDescription(): CharSequence  [API 31+]
* getEndTimeMillis(): long  [API 31+]
* getIcon(): Icon  [API 31+]
* getId(): String  [API 31+]
* getStartTimeMillis(): long  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## ConversationStatus.Builder — declaration; API 31+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder(String, int)  [API 31+]
* build(): ConversationStatus  [API 31+]
* setAvailability(int): ConversationStatus.Builder  [API 31+]
* setDescription(CharSequence): ConversationStatus.Builder  [API 31+]
* setEndTimeMillis(long): ConversationStatus.Builder  [API 31+]
* setIcon(Icon): ConversationStatus.Builder  [API 31+]
* setStartTimeMillis(long): ConversationStatus.Builder  [API 31+]
```

## PeopleManager — framework; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- addOrUpdateStatus(String, ConversationStatus): void  [API 31+]
- clearStatus(String, String): void  [API 31+]
- clearStatuses(String): void  [API 31+]
- getStatuses(String): List  [API 31+]
```


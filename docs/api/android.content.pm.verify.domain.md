# android.content.pm.verify.domain

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DomainVerificationManager — framework; API 31+ (0 +, 0 *, 0 ~, 1 -)

```
- getDomainVerificationUserState(String): DomainVerificationUserState  [API 31+]
```

## DomainVerificationUserState — declaration; API 31+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
- getHostToStateMap(): Map  [API 31+]
- getPackageName(): String  [API 31+]
- getUser(): UserHandle  [API 31+]
- hashCode(): int  [API 31+]
- isLinkHandlingAllowed(): boolean  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```


# android.net.wifi.hotspot2

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ConfigParser — declaration; API 26+ (0 +, 0 *, 0 ~, 1 -)

```
- parsePasspointConfig(String, byte[]): PasspointConfiguration [static]  [API 26+]
```

## PasspointConfiguration — declaration; API 26+ (0 +, 7 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 26+]
* PasspointConfiguration()  [API 26+]
- PasspointConfiguration(PasspointConfiguration)  [API 26+]
- describeContents(): int  [API 26+]
- equals(Object): boolean  [API 26+]
* getCredential(): Credential  [API 26+]
* getDecoratedIdentityPrefix(): String  [API 31+]
* getHomeSp(): HomeSp  [API 26+]
- getSubscriptionExpirationTimeMillis(): long  [API 30+]
- getUniqueId(): String  [API 30+]
- hashCode(): int  [API 26+]
- isOsuProvisioned(): boolean  [API 30+]
* setCredential(Credential): void  [API 26+]
* setDecoratedIdentityPrefix(String): void  [API 31+]
* setHomeSp(HomeSp): void  [API 26+]
- setSubscriptionExpirationTimeInMillis(long): void  [API 33+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```


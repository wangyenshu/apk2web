# android.telephony.euicc

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DownloadableSubscription — declaration; API 28+ (1 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 28+]
* describeContents(): int  [API 28+]
+ forActivationCode(String): DownloadableSubscription [static]  [API 28+]
* getConfirmationCode(): String  [API 28+]
* getEncodedActivationCode(): String  [API 28+]
* writeToParcel(Parcel, int): void  [API 28+]
```

## DownloadableSubscription.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(DownloadableSubscription)  [API 31+]
* Builder(String)  [API 31+]
* build(): DownloadableSubscription  [API 31+]
* setConfirmationCode(String): DownloadableSubscription.Builder  [API 31+]
* setEncodedActivationCode(String): DownloadableSubscription.Builder  [API 31+]
```

## EuiccInfo — declaration; API 28+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 28+]
* EuiccInfo(String)  [API 28+]
* describeContents(): int  [API 28+]
* getOsVersion(): String  [API 28+]
* writeToParcel(Parcel, int): void  [API 28+]
```

## EuiccManager — framework; API 28+ (12 +, 0 *, 0 ~, 0 -)

```
+ createForCardId(int): EuiccManager  [API 29+]
+ deleteSubscription(int, PendingIntent): void  [API 28+]
+ downloadSubscription(DownloadableSubscription, boolean, PendingIntent): void  [API 28+]
+ getAvailableMemoryInBytes(): long  [API 35+]
+ getEid(): String  [API 28+]
+ getEuiccInfo(): EuiccInfo  [API 28+]
+ isEnabled(): boolean  [API 28+]
+ isSimPortAvailable(int): boolean  [API 33+]
+ startResolutionActivity(Activity, int, Intent, PendingIntent): void  [API 28+]
+ switchToSubscription(int, PendingIntent): void  [API 28+]
+ switchToSubscription(int, int, PendingIntent): void  [API 33+]
+ updateSubscriptionNickname(int, String, PendingIntent): void  [API 29+]
```


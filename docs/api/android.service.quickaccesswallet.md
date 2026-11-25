# android.service.quickaccesswallet

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## GetWalletCardsCallback — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ onFailure(GetWalletCardsError): void  [API 30+]
+ onSuccess(GetWalletCardsResponse): void  [API 30+]
```

## GetWalletCardsError — declaration; API 30+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 30+]
- GetWalletCardsError(Icon, CharSequence)  [API 30+]
- describeContents(): int  [API 30+]
- getIcon(): Icon  [API 30+]
- getMessage(): CharSequence  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## GetWalletCardsRequest — declaration; API 30+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* GetWalletCardsRequest(int, int, int, int)  [API 30+]
* describeContents(): int  [API 30+]
* getCardHeightPx(): int  [API 30+]
* getCardWidthPx(): int  [API 30+]
* getIconSizePx(): int  [API 30+]
* getMaxCards(): int  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## GetWalletCardsResponse — declaration; API 30+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* GetWalletCardsResponse(List, int)  [API 30+]
* describeContents(): int  [API 30+]
* getSelectedIndex(): int  [API 30+]
* getWalletCards(): List  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## QuickAccessWalletService — declaration; API 30+ (3 +, 0 *, 0 ~, 4 -)

```
- QuickAccessWalletService()  [API 30+]
- getTargetActivityPendingIntent(): PendingIntent  [API 33+]
- onBind(Intent): IBinder  [API 30+]
+ onWalletCardSelected(SelectWalletCardRequest): void  [API 30+]
+ onWalletCardsRequested(GetWalletCardsRequest, GetWalletCardsCallback): void  [API 30+]
+ onWalletDismissed(): void  [API 30+]
- sendWalletServiceEvent(WalletServiceEvent): void  [API 30+]
```

## SelectWalletCardRequest — declaration; API 30+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* SelectWalletCardRequest(String)  [API 30+]
* describeContents(): int  [API 30+]
* getCardId(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## WalletCard — declaration; API 30+ (0 +, 11 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* describeContents(): int  [API 30+]
* getCardIcon(): Icon  [API 30+]
* getCardId(): String  [API 30+]
* getCardImage(): Icon  [API 30+]
* getCardLabel(): CharSequence  [API 30+]
* getCardLocations(): List  [API 34+]
* getCardType(): int  [API 34+]
* getContentDescription(): CharSequence  [API 30+]
* getNonPaymentCardSecondaryImage(): Icon  [API 34+]
* getPendingIntent(): PendingIntent  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## WalletCard.Builder — declaration; API 30+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder(String, Icon, CharSequence, PendingIntent)  [API 30+]
* Builder(String, int, Icon, CharSequence, PendingIntent)  [API 34+]
* build(): WalletCard  [API 30+]
* setCardIcon(Icon): WalletCard.Builder  [API 30+]
* setCardLabel(CharSequence): WalletCard.Builder  [API 30+]
* setCardLocations(List): WalletCard.Builder  [API 34+]
* setNonPaymentCardSecondaryImage(Icon): WalletCard.Builder  [API 34+]
```

## WalletServiceEvent — declaration; API 30+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* WalletServiceEvent(int)  [API 30+]
* describeContents(): int  [API 30+]
* getEventType(): int  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```


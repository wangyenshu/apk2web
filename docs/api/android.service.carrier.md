# android.service.carrier

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CarrierIdentifier — declaration; API 23+ (0 +, 15 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 23+]
* CarrierIdentifier(String, String, String, String, String, String)  [API 23+]
* CarrierIdentifier(String, String, String, String, String, String, int, int)  [API 29+]
- CarrierIdentifier(byte[], String, String)  [API 28+]
* describeContents(): int  [API 23+]
* equals(Object): boolean  [API 23+]
* getCarrierId(): int  [API 29+]
* getGid1(): String  [API 23+]
* getGid2(): String  [API 23+]
* getImsi(): String  [API 23+]
* getMcc(): String  [API 23+]
* getMnc(): String  [API 23+]
* getSpecificCarrierId(): int  [API 29+]
* getSpn(): String  [API 23+]
* hashCode(): int  [API 23+]
* toString(): String  [API 23+]
* writeToParcel(Parcel, int): void  [API 23+]
```

## CarrierMessagingClientService — declaration; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
- CarrierMessagingClientService()  [API 29+]
- onBind(Intent): IBinder  [API 29+]
```

## CarrierMessagingService — declaration; API 22+ (10 +, 0 *, 0 ~, 2 -)

```
- CarrierMessagingService()  [API 22+]
- onBind(Intent): IBinder  [API 22+]
+ onDownloadMms(Uri, int, Uri, CarrierMessagingService.ResultCallback): void  [API 22+]
+ onFilterSms(MessagePdu, String, int, int, CarrierMessagingService.ResultCallback): void  [API 22+, deprecated in 24]
+ onReceiveTextSms(MessagePdu, String, int, int, CarrierMessagingService.ResultCallback): void  [API 24+]
+ onSendDataSms(byte[], int, String, int, CarrierMessagingService.ResultCallback): void  [API 22+, deprecated in 23]
+ onSendDataSms(byte[], int, String, int, int, CarrierMessagingService.ResultCallback): void  [API 23+]
+ onSendMms(Uri, int, Uri, CarrierMessagingService.ResultCallback): void  [API 22+]
+ onSendMultipartTextSms(List, int, String, CarrierMessagingService.ResultCallback): void  [API 22+, deprecated in 23]
+ onSendMultipartTextSms(List, int, String, int, CarrierMessagingService.ResultCallback): void  [API 23+]
+ onSendTextSms(String, int, String, CarrierMessagingService.ResultCallback): void  [API 22+, deprecated in 23]
+ onSendTextSms(String, int, String, int, CarrierMessagingService.ResultCallback): void  [API 23+]
```

## CarrierMessagingService.ResultCallback — declaration; API 22+ (1 +, 0 *, 0 ~, 0 -)

```
+ onReceiveResult(Object): void  [API 22+]
```

## CarrierMessagingService.SendMmsResult — declaration; API 22+ (0 +, 3 *, 0 ~, 0 -)

```
* SendMmsResult(int, byte[])  [API 22+]
* getSendConfPdu(): byte[]  [API 22+]
* getSendStatus(): int  [API 22+]
```

## CarrierMessagingService.SendMultipartSmsResult — declaration; API 22+ (0 +, 3 *, 0 ~, 0 -)

```
* SendMultipartSmsResult(int, int[])  [API 22+]
* getMessageRefs(): int[]  [API 22+]
* getSendStatus(): int  [API 22+]
```

## CarrierMessagingService.SendSmsResult — declaration; API 22+ (0 +, 3 *, 0 ~, 0 -)

```
* SendSmsResult(int, int)  [API 22+]
* getMessageRef(): int  [API 22+]
* getSendStatus(): int  [API 22+]
```

## CarrierService — declaration; API 23+ (1 +, 0 *, 0 ~, 5 -)

```
- CarrierService()  [API 23+]
- notifyCarrierNetworkChange(boolean): void  [API 23+, deprecated in 33]
- notifyCarrierNetworkChange(int, boolean): void  [API 33+]
- onBind(Intent): IBinder  [API 23+]
+ onLoadConfig(CarrierIdentifier): PersistableBundle  [API 23+, deprecated in 33]
- onLoadConfig(int, CarrierIdentifier): PersistableBundle  [API 33+]
```

## MessagePdu — declaration; API 22+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 22+]
- MessagePdu(List)  [API 22+]
- describeContents(): int  [API 22+]
- getPdus(): List  [API 22+]
- writeToParcel(Parcel, int): void  [API 22+]
```


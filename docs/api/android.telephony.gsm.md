# android.telephony.gsm

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## GsmCellLocation — declaration; API 1+, deprecated in 31 (9 +, 0 *, 0 ~, 2 -)

```
- GsmCellLocation()  [API 1+]
- GsmCellLocation(Bundle)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ fillInNotifierBundle(Bundle): void  [API 1+]
+ getCid(): int  [API 1+]
+ getLac(): int  [API 1+]
+ getPsc(): int  [API 9+]
+ hashCode(): int  [API 1+]
+ setLacAndCid(int, int): void  [API 1+]
+ setStateInvalid(): void  [API 1+]
+ toString(): String  [API 1+]
```

## SmsManager — declaration; API 1+, deprecated in 4 (5 +, 0 *, 0 ~, 0 -)

```
+ divideMessage(String): ArrayList  [API 1+]
+ getDefault(): SmsManager [static]  [API 1+]
+ sendDataMessage(String, String, short, byte[], PendingIntent, PendingIntent): void  [API 1+]
+ sendMultipartTextMessage(String, String, ArrayList, ArrayList, ArrayList): void  [API 1+]
+ sendTextMessage(String, String, String, PendingIntent, PendingIntent): void  [API 1+]
```

## SmsMessage — declaration; API 1+, deprecated in 4 (30 +, 0 *, 0 ~, 1 -)

```
- SmsMessage()  [API 1+]
+ calculateLength(CharSequence, boolean): int[] [static]  [API 3+]
+ calculateLength(String, boolean): int[] [static]  [API 1+]
+ createFromPdu(byte[]): SmsMessage [static]  [API 1+]
+ getDisplayMessageBody(): String  [API 1+]
+ getDisplayOriginatingAddress(): String  [API 1+]
+ getEmailBody(): String  [API 1+]
+ getEmailFrom(): String  [API 1+]
+ getIndexOnSim(): int  [API 1+]
+ getMessageBody(): String  [API 1+]
+ getMessageClass(): SmsMessage.MessageClass  [API 1+]
+ getOriginatingAddress(): String  [API 1+]
+ getPdu(): byte[]  [API 1+]
+ getProtocolIdentifier(): int  [API 1+]
+ getPseudoSubject(): String  [API 1+]
+ getServiceCenterAddress(): String  [API 1+]
+ getStatus(): int  [API 1+]
+ getStatusOnSim(): int  [API 1+]
+ getSubmitPdu(String, String, String, boolean): SmsMessage.SubmitPdu [static]  [API 1+]
+ getSubmitPdu(String, String, short, byte[], boolean): SmsMessage.SubmitPdu [static]  [API 1+]
+ getTPLayerLengthForPDU(String): int [static]  [API 1+]
+ getTimestampMillis(): long  [API 1+]
+ getUserData(): byte[]  [API 1+]
+ isCphsMwiMessage(): boolean  [API 1+]
+ isEmail(): boolean  [API 1+]
+ isMWIClearMessage(): boolean  [API 1+]
+ isMWISetMessage(): boolean  [API 1+]
+ isMwiDontStore(): boolean  [API 1+]
+ isReplace(): boolean  [API 1+]
+ isReplyPathPresent(): boolean  [API 1+]
+ isStatusReportMessage(): boolean  [API 1+]
```

## SmsMessage.MessageClass — declaration; API 1+, deprecated in 4 (2 +, 0 *, 0 ~, 0 -)

```
+ CLASS_0 [field]  [API 1+]
+ CLASS_1 [field]  [API 1+]
+ CLASS_2 [field]  [API 1+]
+ CLASS_3 [field]  [API 1+]
+ UNKNOWN [field]  [API 1+]
+ valueOf(String): SmsMessage.MessageClass [static]  [API 1+]
+ values(): SmsMessage.MessageClass[] [static]  [API 1+]
```

## SmsMessage.SubmitPdu — declaration; API 1+, deprecated in 4 (1 +, 0 *, 0 ~, 1 -)

```
- SubmitPdu()  [API 1+]
+ encodedMessage [field]  [API 1+]
+ encodedScAddress [field]  [API 1+]
+ toString(): String  [API 1+]
```


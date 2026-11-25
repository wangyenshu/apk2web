# android.printservice

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CustomPrinterIconCallback — declaration; API 24+ (0 +, 0 *, 0 ~, 1 -)

```
- onCustomPrinterIconLoaded(Icon): boolean  [API 24+]
```

## PrintDocument — declaration; API 19+ (0 +, 0 *, 0 ~, 2 -)

```
- getData(): ParcelFileDescriptor  [API 19+]
- getInfo(): PrintDocumentInfo  [API 19+]
```

## PrintJob — declaration; API 19+ (0 +, 0 *, 0 ~, 24 -)

```
- block(String): boolean  [API 19+]
- cancel(): boolean  [API 19+]
- complete(): boolean  [API 19+]
- equals(Object): boolean  [API 19+]
- fail(String): boolean  [API 19+]
- getAdvancedIntOption(String): int  [API 19+]
- getAdvancedStringOption(String): String  [API 19+]
- getDocument(): PrintDocument  [API 19+]
- getId(): PrintJobId  [API 19+]
- getInfo(): PrintJobInfo  [API 19+]
- getTag(): String  [API 19+]
- hasAdvancedOption(String): boolean  [API 19+]
- hashCode(): int  [API 19+]
- isBlocked(): boolean  [API 19+]
- isCancelled(): boolean  [API 19+]
- isCompleted(): boolean  [API 19+]
- isFailed(): boolean  [API 19+]
- isQueued(): boolean  [API 19+]
- isStarted(): boolean  [API 19+]
- setProgress(float): void  [API 24+]
- setStatus(CharSequence): void  [API 24+]
- setStatus(int): void  [API 24+]
- setTag(String): boolean  [API 19+]
- start(): boolean  [API 19+]
```

## PrintService — declaration; API 19+ (5 +, 0 *, 0 ~, 5 -)

```
- PrintService()  [API 19+]
- attachBaseContext(Context): void  [API 19+]
- generatePrinterId(String): PrinterId  [API 19+]
- getActivePrintJobs(): List  [API 19+]
- onBind(Intent): IBinder  [API 19+]
+ onConnected(): void  [API 19+]
+ onCreatePrinterDiscoverySession(): PrinterDiscoverySession  [API 19+]
+ onDisconnected(): void  [API 19+]
+ onPrintJobQueued(PrintJob): void  [API 19+]
+ onRequestCancelPrintJob(PrintJob): void  [API 19+]
```

## PrinterDiscoverySession — declaration; API 19+ (7 +, 0 *, 0 ~, 7 -)

```
- PrinterDiscoverySession()  [API 19+]
- addPrinters(List): void  [API 19+]
- getPrinters(): List  [API 19+]
- getTrackedPrinters(): List  [API 19+]
- isDestroyed(): boolean  [API 19+]
- isPrinterDiscoveryStarted(): boolean  [API 19+]
+ onDestroy(): void  [API 19+]
+ onRequestCustomPrinterIcon(PrinterId, CancellationSignal, CustomPrinterIconCallback): void  [API 24+]
+ onStartPrinterDiscovery(List): void  [API 19+]
+ onStartPrinterStateTracking(PrinterId): void  [API 19+]
+ onStopPrinterDiscovery(): void  [API 19+]
+ onStopPrinterStateTracking(PrinterId): void  [API 19+]
+ onValidatePrinters(List): void  [API 19+]
- removePrinters(List): void  [API 19+]
```


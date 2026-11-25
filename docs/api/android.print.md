# android.print

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## PageRange — declaration; API 19+ (0 +, 8 *, 0 ~, 0 -)

```
- ALL_PAGES [field]  [API 19+]
+ CREATOR [field]  [API 19+]
* PageRange(int, int)  [API 19+]
* describeContents(): int  [API 19+]
* equals(Object): boolean  [API 19+]
* getEnd(): int  [API 19+]
* getStart(): int  [API 19+]
* hashCode(): int  [API 19+]
* toString(): String  [API 19+]
* writeToParcel(Parcel, int): void  [API 19+]
```

## PrintAttributes — declaration; API 19+ (0 +, 10 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 19+]
* describeContents(): int  [API 19+]
* equals(Object): boolean  [API 19+]
* getColorMode(): int  [API 19+]
* getDuplexMode(): int  [API 23+]
* getMediaSize(): PrintAttributes.MediaSize  [API 19+]
* getMinMargins(): PrintAttributes.Margins  [API 19+]
* getResolution(): PrintAttributes.Resolution  [API 19+]
* hashCode(): int  [API 19+]
* toString(): String  [API 19+]
* writeToParcel(Parcel, int): void  [API 19+]
```

## PrintAttributes.Builder — declaration; API 19+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder()  [API 19+]
* build(): PrintAttributes  [API 19+]
* setColorMode(int): PrintAttributes.Builder  [API 19+]
* setDuplexMode(int): PrintAttributes.Builder  [API 23+]
* setMediaSize(PrintAttributes.MediaSize): PrintAttributes.Builder  [API 19+]
* setMinMargins(PrintAttributes.Margins): PrintAttributes.Builder  [API 19+]
* setResolution(PrintAttributes.Resolution): PrintAttributes.Builder  [API 19+]
```

## PrintAttributes.Margins — declaration; API 19+ (0 +, 8 *, 0 ~, 0 -)

```
* Margins(int, int, int, int)  [API 19+]
- NO_MARGINS [field]  [API 19+]
* equals(Object): boolean  [API 19+]
* getBottomMils(): int  [API 19+]
* getLeftMils(): int  [API 19+]
* getRightMils(): int  [API 19+]
* getTopMils(): int  [API 19+]
* hashCode(): int  [API 19+]
* toString(): String  [API 19+]
```

## PrintAttributes.MediaSize — declaration; API 19+ (0 +, 0 *, 0 ~, 11 -)

```
- ANSI_C [field]  [API 31+]
- ANSI_D [field]  [API 31+]
- ANSI_E [field]  [API 31+]
- ANSI_F [field]  [API 31+]
- ISO_A0 [field]  [API 19+]
- ISO_A1 [field]  [API 19+]
- ISO_A10 [field]  [API 19+]
- ISO_A2 [field]  [API 19+]
- ISO_A3 [field]  [API 19+]
- ISO_A4 [field]  [API 19+]
- ISO_A5 [field]  [API 19+]
- ISO_A6 [field]  [API 19+]
- ISO_A7 [field]  [API 19+]
- ISO_A8 [field]  [API 19+]
- ISO_A9 [field]  [API 19+]
- ISO_B0 [field]  [API 19+]
- ISO_B1 [field]  [API 19+]
- ISO_B10 [field]  [API 19+]
- ISO_B2 [field]  [API 19+]
- ISO_B3 [field]  [API 19+]
- ISO_B4 [field]  [API 19+]
- ISO_B5 [field]  [API 19+]
- ISO_B6 [field]  [API 19+]
- ISO_B7 [field]  [API 19+]
- ISO_B8 [field]  [API 19+]
- ISO_B9 [field]  [API 19+]
- ISO_C0 [field]  [API 19+]
- ISO_C1 [field]  [API 19+]
- ISO_C10 [field]  [API 19+]
- ISO_C2 [field]  [API 19+]
- ISO_C3 [field]  [API 19+]
- ISO_C4 [field]  [API 19+]
- ISO_C5 [field]  [API 19+]
- ISO_C6 [field]  [API 19+]
- ISO_C7 [field]  [API 19+]
- ISO_C8 [field]  [API 19+]
- ISO_C9 [field]  [API 19+]
- JIS_B0 [field]  [API 19+]
- JIS_B1 [field]  [API 19+]
- JIS_B10 [field]  [API 19+]
- JIS_B2 [field]  [API 19+]
- JIS_B3 [field]  [API 19+]
- JIS_B4 [field]  [API 19+]
- JIS_B5 [field]  [API 19+]
- JIS_B6 [field]  [API 19+]
- JIS_B7 [field]  [API 19+]
- JIS_B8 [field]  [API 19+]
- JIS_B9 [field]  [API 19+]
- JIS_EXEC [field]  [API 19+]
- JPN_CHOU2 [field]  [API 19+]
- JPN_CHOU3 [field]  [API 19+]
- JPN_CHOU4 [field]  [API 19+]
- JPN_HAGAKI [field]  [API 19+]
- JPN_KAHU [field]  [API 19+]
- JPN_KAKU2 [field]  [API 19+]
- JPN_OE_PHOTO_L [field]  [API 31+]
- JPN_OUFUKU [field]  [API 19+]
- JPN_YOU4 [field]  [API 19+]
- MediaSize(String, String, int, int)  [API 19+]
- NA_ARCH_A [field]  [API 31+]
- NA_ARCH_B [field]  [API 31+]
- NA_ARCH_C [field]  [API 31+]
- NA_ARCH_D [field]  [API 31+]
- NA_ARCH_E [field]  [API 31+]
- NA_ARCH_E1 [field]  [API 31+]
- NA_FOOLSCAP [field]  [API 19+]
- NA_GOVT_LETTER [field]  [API 19+]
- NA_INDEX_3X5 [field]  [API 19+]
- NA_INDEX_4X6 [field]  [API 19+]
- NA_INDEX_5X8 [field]  [API 19+]
- NA_JUNIOR_LEGAL [field]  [API 19+]
- NA_LEDGER [field]  [API 19+]
- NA_LEGAL [field]  [API 19+]
- NA_LETTER [field]  [API 19+]
- NA_MONARCH [field]  [API 19+]
- NA_QUARTO [field]  [API 19+]
- NA_SUPER_B [field]  [API 31+]
- NA_TABLOID [field]  [API 19+]
- OM_DAI_PA_KAI [field]  [API 19+]
- OM_JUURO_KU_KAI [field]  [API 19+]
- OM_PA_KAI [field]  [API 19+]
- PRC_1 [field]  [API 19+]
- PRC_10 [field]  [API 19+]
- PRC_16K [field]  [API 19+]
- PRC_2 [field]  [API 19+]
- PRC_3 [field]  [API 19+]
- PRC_4 [field]  [API 19+]
- PRC_5 [field]  [API 19+]
- PRC_6 [field]  [API 19+]
- PRC_7 [field]  [API 19+]
- PRC_8 [field]  [API 19+]
- PRC_9 [field]  [API 19+]
- ROC_16K [field]  [API 19+]
- ROC_8K [field]  [API 19+]
- UNKNOWN_LANDSCAPE [field]  [API 19+]
- UNKNOWN_PORTRAIT [field]  [API 19+]
- asLandscape(): PrintAttributes.MediaSize  [API 19+]
- asPortrait(): PrintAttributes.MediaSize  [API 19+]
- equals(Object): boolean  [API 19+]
- getHeightMils(): int  [API 19+]
- getId(): String  [API 19+]
- getLabel(PackageManager): String  [API 19+]
- getWidthMils(): int  [API 19+]
- hashCode(): int  [API 19+]
- isPortrait(): boolean  [API 19+]
- toString(): String  [API 19+]
```

## PrintAttributes.Resolution — declaration; API 19+ (0 +, 8 *, 0 ~, 0 -)

```
* Resolution(String, String, int, int)  [API 19+]
* equals(Object): boolean  [API 19+]
* getHorizontalDpi(): int  [API 19+]
* getId(): String  [API 19+]
* getLabel(): String  [API 19+]
* getVerticalDpi(): int  [API 19+]
* hashCode(): int  [API 19+]
* toString(): String  [API 19+]
```

## PrintDocumentAdapter — declaration; API 19+ (5 +, 0 *, 0 ~, 0 -)

```
+ PrintDocumentAdapter()  [API 19+]
+ onFinish(): void  [API 19+]
+ onLayout(PrintAttributes, PrintAttributes, CancellationSignal, PrintDocumentAdapter.LayoutResultCallback, Bundle): void  [API 19+]
+ onStart(): void  [API 19+]
+ onWrite(PageRange[], ParcelFileDescriptor, CancellationSignal, PrintDocumentAdapter.WriteResultCallback): void  [API 19+]
```

## PrintDocumentAdapter.LayoutResultCallback — declaration; API 19+ (3 +, 0 *, 0 ~, 0 -)

```
+ onLayoutCancelled(): void  [API 19+]
+ onLayoutFailed(CharSequence): void  [API 19+]
+ onLayoutFinished(PrintDocumentInfo, boolean): void  [API 19+]
```

## PrintDocumentAdapter.WriteResultCallback — declaration; API 19+ (3 +, 0 *, 0 ~, 0 -)

```
+ onWriteCancelled(): void  [API 19+]
+ onWriteFailed(CharSequence): void  [API 19+]
+ onWriteFinished(PageRange[]): void  [API 19+]
```

## PrintDocumentInfo — declaration; API 19+ (0 +, 3 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 19+]
- describeContents(): int  [API 19+]
- equals(Object): boolean  [API 19+]
* getContentType(): int  [API 19+]
- getDataSize(): long  [API 19+]
* getName(): String  [API 19+]
* getPageCount(): int  [API 19+]
- hashCode(): int  [API 19+]
- toString(): String  [API 19+]
- writeToParcel(Parcel, int): void  [API 19+]
```

## PrintDocumentInfo.Builder — declaration; API 19+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder(String)  [API 19+]
* build(): PrintDocumentInfo  [API 19+]
* setContentType(int): PrintDocumentInfo.Builder  [API 19+]
* setPageCount(int): PrintDocumentInfo.Builder  [API 19+]
```

## PrintJob — declaration; API 19+ (0 +, 0 *, 0 ~, 12 -)

```
- cancel(): void  [API 19+]
- equals(Object): boolean  [API 19+]
- getId(): PrintJobId  [API 19+]
- getInfo(): PrintJobInfo  [API 19+]
- hashCode(): int  [API 19+]
- isBlocked(): boolean  [API 19+]
- isCancelled(): boolean  [API 19+]
- isCompleted(): boolean  [API 19+]
- isFailed(): boolean  [API 19+]
- isQueued(): boolean  [API 19+]
- isStarted(): boolean  [API 19+]
- restart(): void  [API 19+]
```

## PrintJobId — declaration; API 19+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 19+]
- describeContents(): int  [API 19+]
- equals(Object): boolean  [API 19+]
- hashCode(): int  [API 19+]
- writeToParcel(Parcel, int): void  [API 19+]
```

## PrintJobInfo — declaration; API 19+ (0 +, 11 *, 0 ~, 3 -)

```
+ CREATOR [field]  [API 19+]
* describeContents(): int  [API 19+]
- getAdvancedIntOption(String): int  [API 26+]
- getAdvancedStringOption(String): String  [API 26+]
* getAttributes(): PrintAttributes  [API 19+]
* getCopies(): int  [API 19+]
* getCreationTime(): long  [API 19+]
* getId(): PrintJobId  [API 19+]
* getLabel(): String  [API 19+]
* getPages(): PageRange[]  [API 19+]
* getPrinterId(): PrinterId  [API 19+]
* getState(): int  [API 19+]
- hasAdvancedOption(String): boolean  [API 26+]
* toString(): String  [API 19+]
* writeToParcel(Parcel, int): void  [API 19+]
```

## PrintJobInfo.Builder — declaration; API 19+ (0 +, 2 *, 0 ~, 5 -)

```
* Builder(PrintJobInfo)  [API 19+]
* build(): PrintJobInfo  [API 19+]
- putAdvancedOption(String, String): void  [API 19+]
- putAdvancedOption(String, int): void  [API 19+]
- setAttributes(PrintAttributes): void  [API 19+]
- setCopies(int): void  [API 19+]
- setPages(PageRange[]): void  [API 19+]
```

## PrintManager — framework; API 19+ (0 +, 0 *, 0 ~, 3 -)

```
- getPrintJobs(): List  [API 19+]
- isPrintServiceEnabled(ComponentName): boolean  [API 33+]
- print(String, PrintDocumentAdapter, PrintAttributes): PrintJob  [API 19+]
```

## PrinterCapabilitiesInfo — declaration; API 19+ (0 +, 1 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 19+]
- describeContents(): int  [API 19+]
- equals(Object): boolean  [API 19+]
- getColorModes(): int  [API 19+]
- getDefaults(): PrintAttributes  [API 19+]
- getDuplexModes(): int  [API 23+]
- getMediaSizes(): List  [API 19+]
* getMinMargins(): PrintAttributes.Margins  [API 19+]
- getResolutions(): List  [API 19+]
- hashCode(): int  [API 19+]
- toString(): String  [API 19+]
- writeToParcel(Parcel, int): void  [API 19+]
```

## PrinterCapabilitiesInfo.Builder — declaration; API 19+ (0 +, 2 *, 0 ~, 5 -)

```
- Builder(PrinterId)  [API 19+]
- addMediaSize(PrintAttributes.MediaSize, boolean): PrinterCapabilitiesInfo.Builder  [API 19+]
- addResolution(PrintAttributes.Resolution, boolean): PrinterCapabilitiesInfo.Builder  [API 19+]
* build(): PrinterCapabilitiesInfo  [API 19+]
- setColorModes(int, int): PrinterCapabilitiesInfo.Builder  [API 19+]
- setDuplexModes(int, int): PrinterCapabilitiesInfo.Builder  [API 23+]
* setMinMargins(PrintAttributes.Margins): PrinterCapabilitiesInfo.Builder  [API 19+]
```

## PrinterId — declaration; API 19+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 19+]
- describeContents(): int  [API 19+]
- equals(Object): boolean  [API 19+]
- getLocalId(): String  [API 19+]
- hashCode(): int  [API 19+]
- toString(): String  [API 19+]
- writeToParcel(Parcel, int): void  [API 19+]
```

## PrinterInfo — declaration; API 19+ (0 +, 10 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 19+]
* describeContents(): int  [API 19+]
* equals(Object): boolean  [API 19+]
* getCapabilities(): PrinterCapabilitiesInfo  [API 19+]
* getDescription(): String  [API 19+]
* getId(): PrinterId  [API 19+]
* getName(): String  [API 19+]
* getStatus(): int  [API 19+]
* hashCode(): int  [API 19+]
* toString(): String  [API 19+]
* writeToParcel(Parcel, int): void  [API 19+]
```

## PrinterInfo.Builder — declaration; API 19+ (0 +, 6 *, 0 ~, 4 -)

```
- Builder(PrinterId, String, int)  [API 19+]
* Builder(PrinterInfo)  [API 19+]
* build(): PrinterInfo  [API 19+]
* setCapabilities(PrinterCapabilitiesInfo): PrinterInfo.Builder  [API 19+]
* setDescription(String): PrinterInfo.Builder  [API 19+]
- setHasCustomPrinterIcon(boolean): PrinterInfo.Builder  [API 24+]
- setIconResourceId(int): PrinterInfo.Builder  [API 24+]
- setInfoIntent(PendingIntent): PrinterInfo.Builder  [API 24+]
* setName(String): PrinterInfo.Builder  [API 19+]
* setStatus(int): PrinterInfo.Builder  [API 19+]
```


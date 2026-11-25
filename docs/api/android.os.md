# android.os

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AsyncTask — framework; API 3+, deprecated in 30 (16 +, 0 *, 0 ~, 0 -)

```
+ AsyncTask()  [API 3+]
+ SERIAL_EXECUTOR [field]  [API 11+]
+ THREAD_POOL_EXECUTOR [field]  [API 11+]
+ cancel(boolean): boolean  [API 3+]
+ doInBackground(Object[]): Object  [API 3+]
+ execute(Object[]): AsyncTask  [API 3+]
+ execute(Runnable): void [static]  [API 11+]
+ executeOnExecutor(Executor, Object[]): AsyncTask  [API 11+]
+ get(): Object  [API 3+]
+ get(long, TimeUnit): Object  [API 3+]
+ getStatus(): AsyncTask.Status  [API 3+]
+ isCancelled(): boolean  [API 3+]
+ onCancelled(): void  [API 3+]
+ onCancelled(Object): void  [API 11+]
+ onPostExecute(Object): void  [API 3+]
+ onPreExecute(): void  [API 3+]
+ onProgressUpdate(Object[]): void  [API 3+]
+ publishProgress(Object[]): void  [API 3+]
```

## AsyncTask.Status — framework; API 3+, deprecated in 30 (2 +, 0 *, 0 ~, 0 -)

```
+ FINISHED [field]  [API 3+]
+ PENDING [field]  [API 3+]
+ RUNNING [field]  [API 3+]
+ valueOf(String): AsyncTask.Status [static]  [API 3+]
+ values(): AsyncTask.Status[] [static]  [API 3+]
```

## BadParcelableException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BadParcelableException(Exception)  [API 1+]
+ BadParcelableException(String)  [API 1+]
```

## BaseBundle — framework; API 21+ (33 +, 0 *, 0 ~, 0 -)

```
+ clear(): void  [API 21+]
+ containsKey(String): boolean  [API 21+]
+ get(String): Object  [API 21+, deprecated in 33]
+ getBoolean(String): boolean  [API 22+]
+ getBoolean(String, boolean): boolean  [API 22+]
+ getBooleanArray(String): boolean[]  [API 22+]
+ getDouble(String): double  [API 21+]
+ getDouble(String, double): double  [API 21+]
+ getDoubleArray(String): double[]  [API 21+]
+ getInt(String): int  [API 21+]
+ getInt(String, int): int  [API 21+]
+ getIntArray(String): int[]  [API 21+]
+ getLong(String): long  [API 21+]
+ getLong(String, long): long  [API 21+]
+ getLongArray(String): long[]  [API 21+]
+ getString(String): String  [API 21+]
+ getString(String, String): String  [API 21+]
+ getStringArray(String): String[]  [API 21+]
+ isEmpty(): boolean  [API 21+]
+ keySet(): Set  [API 21+]
+ putAll(PersistableBundle): void  [API 21+]
+ putBoolean(String, boolean): void  [API 22+]
+ putBooleanArray(String, boolean[]): void  [API 22+]
+ putDouble(String, double): void  [API 21+]
+ putDoubleArray(String, double[]): void  [API 21+]
+ putInt(String, int): void  [API 21+]
+ putIntArray(String, int[]): void  [API 21+]
+ putLong(String, long): void  [API 21+]
+ putLongArray(String, long[]): void  [API 21+]
+ putString(String, String): void  [API 21+]
+ putStringArray(String, String[]): void  [API 21+]
+ remove(String): void  [API 21+]
+ size(): int  [API 21+]
```

## BatteryManager — framework; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- BatteryManager()  [API 1-22, removed in 23]
- computeChargeTimeRemaining(): long  [API 28+]
- getIntProperty(int): int  [API 21+]
- getLongProperty(int): long  [API 21+]
- getStringProperty(int): String  [API 35+]
- isCharging(): boolean  [API 23+]
```

## Binder — framework; API 1+ (19 +, 0 *, 6 ~, 1 -)

```
+ Binder()  [API 1+]
+ Binder(String)  [API 29+]
+ attachInterface(IInterface, String): void  [API 1+]
+ clearCallingIdentity(): long [static]  [API 1+]
+ clearCallingWorkSource(): long [static]  [API 29+]
~ dump(FileDescriptor, PrintWriter, String[]): void  [API 1+]
+ dump(FileDescriptor, String[]): void  [API 1+]
+ dumpAsync(FileDescriptor, String[]): void  [API 1+]
~ flushPendingCommands(): void [static]  [API 1+]
+ getCallingPid(): int [static]  [API 1+]
+ getCallingUid(): int [static]  [API 1+]
+ getCallingUidOrThrow(): int [static]  [API 29+]
+ getCallingUserHandle(): UserHandle [static]  [API 17+]
+ getCallingWorkSourceUid(): int [static]  [API 29+]
+ getInterfaceDescriptor(): String  [API 1+]
~ isBinderAlive(): boolean  [API 1+]
- joinThreadPool(): void [static]  [API 1+]
~ linkToDeath(IBinder.DeathRecipient, int): void  [API 1+]
+ onTransact(int, Parcel, Parcel, int): boolean  [API 1+]
~ pingBinder(): boolean  [API 1+]
+ queryLocalInterface(String): IInterface  [API 1+]
+ restoreCallingIdentity(long): void [static]  [API 1+]
+ restoreCallingWorkSource(long): void [static]  [API 29+]
+ setCallingWorkSourceUid(int): long [static]  [API 29+]
+ transact(int, Parcel, Parcel, int): boolean  [API 1+]
~ unlinkToDeath(IBinder.DeathRecipient, int): boolean  [API 1+]
```

## BugreportManager — framework; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- cancelBugreport(): void  [API 31+]
- startConnectivityBugreport(ParcelFileDescriptor, Executor, BugreportManager.BugreportCallback): void  [API 31+]
```

## BugreportManager.BugreportCallback — framework; API 31+ (5 +, 0 *, 0 ~, 0 -)

```
+ BugreportCallback()  [API 31+]
+ onEarlyReportFinished(): void  [API 31+]
+ onError(int): void  [API 31+]
+ onFinished(): void  [API 31+]
+ onProgress(float): void  [API 31+]
```

## Build — framework; API 1+ (1 +, 0 *, 1 ~, 2 -)

```
+ BOARD [field]  [API 1+]
+ BOOTLOADER [field]  [API 8+]
+ BRAND [field]  [API 1+]
+ Build()  [API 1+]
+ CPU_ABI [field]  [API 4+, deprecated in 21]
+ CPU_ABI2 [field]  [API 8+, deprecated in 21]
+ DEVICE [field]  [API 1+]
+ DISPLAY [field]  [API 3+]
+ FINGERPRINT [field]  [API 1+]
+ HARDWARE [field]  [API 8+]
+ HOST [field]  [API 1+]
+ ID [field]  [API 1+]
+ MANUFACTURER [field]  [API 4+]
+ MODEL [field]  [API 1+]
- ODM_SKU [field]  [API 31+]
+ PRODUCT [field]  [API 1+]
+ RADIO [field]  [API 8+, deprecated in 15]
+ SERIAL [field]  [API 9+, deprecated in 26]
- SKU [field]  [API 31+]
- SOC_MANUFACTURER [field]  [API 31+]
- SOC_MODEL [field]  [API 31+]
+ SUPPORTED_32_BIT_ABIS [field]  [API 21+]
+ SUPPORTED_64_BIT_ABIS [field]  [API 21+]
+ SUPPORTED_ABIS [field]  [API 21+]
+ TAGS [field]  [API 1+]
+ TIME [field]  [API 1+]
+ TYPE [field]  [API 1+]
+ USER [field]  [API 1+]
- getFingerprintedPartitions(): List [static]  [API 29+]
~ getRadioVersion(): String [static]  [API 14+]
- getSerial(): String [static]  [API 26+]
```

## Build.Partition — declaration; API 29+ (0 +, 0 *, 0 ~, 5 -)

```
- equals(Object): boolean  [API 29+]
- getBuildTimeMillis(): long  [API 29+]
- getFingerprint(): String  [API 29+]
- getName(): String  [API 29+]
- hashCode(): int  [API 29+]
```

## Build.VERSION — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ BASE_OS [field]  [API 23+]
+ CODENAME [field]  [API 4+]
+ INCREMENTAL [field]  [API 1+]
- MEDIA_PERFORMANCE_CLASS [field]  [API 31+]
+ PREVIEW_SDK_INT [field]  [API 23+]
+ RELEASE [field]  [API 1+]
- RELEASE_OR_CODENAME [field]  [API 30+]
- RELEASE_OR_PREVIEW_DISPLAY [field]  [API 33+]
+ SDK [field]  [API 1+, deprecated in 15]
+ SDK_INT [field]  [API 4+]
+ SECURITY_PATCH [field]  [API 23+]
+ VERSION()  [API 1+]
```

## Build.VERSION_CODES — framework; API 4+ (1 +, 0 *, 0 ~, 0 -)

```
+ VERSION_CODES()  [API 4+]
```

## Bundle — framework; API 1+ (101 +, 0 *, 0 ~, 0 -)

```
+ Bundle()  [API 1+]
+ Bundle(Bundle)  [API 1+]
+ Bundle(ClassLoader)  [API 1+]
+ Bundle(PersistableBundle)  [API 21+]
+ Bundle(int)  [API 1+]
+ CREATOR [field]  [API 1+]
+ EMPTY [field]  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ containsKey(String): boolean  [API 1-20, removed in 21]
+ deepCopy(): Bundle  [API 26+]
+ describeContents(): int  [API 1+]
+ get(String): Object  [API 1-20, removed in 21]
+ getBinder(String): IBinder  [API 18+]
+ getBoolean(String): boolean  [API 1-21, removed in 22]
+ getBoolean(String, boolean): boolean  [API 1-21, removed in 22]
+ getBooleanArray(String): boolean[]  [API 1-21, removed in 22]
+ getBundle(String): Bundle  [API 1+]
+ getByte(String): byte  [API 1+]
+ getByte(String, byte): Byte  [API 1+]
+ getByteArray(String): byte[]  [API 1+]
+ getChar(String): char  [API 1+]
+ getChar(String, char): char  [API 1+]
+ getCharArray(String): char[]  [API 1+]
+ getCharSequence(String): CharSequence  [API 1+]
+ getCharSequence(String, CharSequence): CharSequence  [API 12+]
+ getCharSequenceArray(String): CharSequence[]  [API 8+]
+ getCharSequenceArrayList(String): ArrayList  [API 8+]
+ getClassLoader(): ClassLoader  [API 11+]
+ getDouble(String): double  [API 1-20, removed in 21]
+ getDouble(String, double): double  [API 1-20, removed in 21]
+ getDoubleArray(String): double[]  [API 1-20, removed in 21]
+ getFloat(String): float  [API 1+]
+ getFloat(String, float): float  [API 1+]
+ getFloatArray(String): float[]  [API 1+]
+ getInt(String): int  [API 1-20, removed in 21]
+ getInt(String, int): int  [API 1-20, removed in 21]
+ getIntArray(String): int[]  [API 1-20, removed in 21]
+ getIntegerArrayList(String): ArrayList  [API 1+]
+ getLong(String): long  [API 1-20, removed in 21]
+ getLong(String, long): long  [API 1-20, removed in 21]
+ getLongArray(String): long[]  [API 1-20, removed in 21]
+ getParcelable(String): Parcelable  [API 1+, deprecated in 33]
+ getParcelable(String, Class): Object  [API 33+]
+ getParcelableArray(String): Parcelable[]  [API 1+, deprecated in 33]
+ getParcelableArray(String, Class): Object[]  [API 33+]
+ getParcelableArrayList(String): ArrayList  [API 1+, deprecated in 33]
+ getParcelableArrayList(String, Class): ArrayList  [API 33+]
+ getSerializable(String): Serializable  [API 1+, deprecated in 33]
+ getSerializable(String, Class): Serializable  [API 33+]
+ getShort(String): short  [API 1+]
+ getShort(String, short): short  [API 1+]
+ getShortArray(String): short[]  [API 1+]
+ getSize(String): Size  [API 21+]
+ getSizeF(String): SizeF  [API 21+]
+ getSparseParcelableArray(String): SparseArray  [API 1+, deprecated in 33]
+ getSparseParcelableArray(String, Class): SparseArray  [API 33+]
+ getString(String): String  [API 1-20, removed in 21]
+ getString(String, String): String  [API 12-20, removed in 21]
+ getStringArray(String): String[]  [API 1-20, removed in 21]
+ getStringArrayList(String): ArrayList  [API 1+]
+ hasFileDescriptors(): boolean  [API 1+]
+ isEmpty(): boolean  [API 1-20, removed in 21]
+ keySet(): Set  [API 1-20, removed in 21]
+ putAll(Bundle): void  [API 1+]
+ putBinder(String, IBinder): void  [API 18+]
+ putBoolean(String, boolean): void  [API 1-21, removed in 22]
+ putBooleanArray(String, boolean[]): void  [API 1-21, removed in 22]
+ putBundle(String, Bundle): void  [API 1+]
+ putByte(String, byte): void  [API 1+]
+ putByteArray(String, byte[]): void  [API 1+]
+ putChar(String, char): void  [API 1+]
+ putCharArray(String, char[]): void  [API 1+]
+ putCharSequence(String, CharSequence): void  [API 1+]
+ putCharSequenceArray(String, CharSequence[]): void  [API 8+]
+ putCharSequenceArrayList(String, ArrayList): void  [API 8+]
+ putDouble(String, double): void  [API 1-20, removed in 21]
+ putDoubleArray(String, double[]): void  [API 1-20, removed in 21]
+ putFloat(String, float): void  [API 1+]
+ putFloatArray(String, float[]): void  [API 1+]
+ putInt(String, int): void  [API 1-20, removed in 21]
+ putIntArray(String, int[]): void  [API 1-20, removed in 21]
+ putIntegerArrayList(String, ArrayList): void  [API 1+]
+ putLong(String, long): void  [API 1-20, removed in 21]
+ putLongArray(String, long[]): void  [API 1-20, removed in 21]
+ putParcelable(String, Parcelable): void  [API 1+]
+ putParcelableArray(String, Parcelable[]): void  [API 1+]
+ putParcelableArrayList(String, ArrayList): void  [API 1+]
+ putSerializable(String, Serializable): void  [API 1+]
+ putShort(String, short): void  [API 1+]
+ putShortArray(String, short[]): void  [API 1+]
+ putSize(String, Size): void  [API 21+]
+ putSizeF(String, SizeF): void  [API 21+]
+ putSparseParcelableArray(String, SparseArray): void  [API 1+]
+ putString(String, String): void  [API 1-20, removed in 21]
+ putStringArray(String, String[]): void  [API 1-20, removed in 21]
+ putStringArrayList(String, ArrayList): void  [API 1+]
+ readFromParcel(Parcel): void  [API 1+]
+ remove(String): void  [API 1+]
+ setClassLoader(ClassLoader): void  [API 1+]
+ size(): int  [API 1-20, removed in 21]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## CancellationSignal — framework; API 16+ (5 +, 0 *, 0 ~, 0 -)

```
+ CancellationSignal()  [API 16+]
+ cancel(): void  [API 16+]
+ isCanceled(): boolean  [API 16+]
+ setOnCancelListener(CancellationSignal.OnCancelListener): void  [API 16+]
+ throwIfCanceled(): void  [API 16+]
```

## CancellationSignal.OnCancelListener — framework; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCancel(): void  [API 16+]
```

## CombinedVibration — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 31+]
- createParallel(VibrationEffect): CombinedVibration [static]  [API 31+]
- describeContents(): int  [API 31+]
- startParallel(): CombinedVibration.ParallelCombination [static]  [API 31+]
```

## CombinedVibration.ParallelCombination — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- addVibrator(int, VibrationEffect): CombinedVibration.ParallelCombination  [API 31+]
- combine(): CombinedVibration  [API 31+]
```

## ConditionVariable — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ ConditionVariable()  [API 1+]
+ ConditionVariable(boolean)  [API 1+]
+ block(): void  [API 1+]
+ block(long): boolean  [API 1+]
+ close(): void  [API 1+]
+ open(): void  [API 1+]
```

## CountDownTimer — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ CountDownTimer(long, long)  [API 1+]
+ cancel(): void  [API 1+]
+ onFinish(): void  [API 1+]
+ onTick(long): void  [API 1+]
+ start(): CountDownTimer  [API 1+]
```

## CpuUsageInfo — framework; API 24+ (3 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 24+]
~ describeContents(): int  [API 24+]
+ getActive(): long  [API 24+]
+ getTotal(): long  [API 24+]
+ writeToParcel(Parcel, int): void  [API 24+]
```

## DeadObjectException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ DeadObjectException()  [API 1+]
+ DeadObjectException(String)  [API 23+]
```

## DeadSystemException — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ DeadSystemException()  [API 24+]
```

## Debug — framework; API 1+ (1 +, 0 *, 8 ~, 60 -)

```
- attachJvmtiAgent(String, String, ClassLoader): void [static]  [API 28+]
- changeDebugPort(int): void [static]  [API 1+, deprecated in 15]
- dumpHprofData(String): void [static]  [API 3+]
- dumpService(String, FileDescriptor, String[]): boolean [static]  [API 8+]
- enableEmulatorTraceOutput(): void [static]  [API 1+, deprecated in 34]
- getBinderDeathObjectCount(): int [static]  [API 1+]
- getBinderLocalObjectCount(): int [static]  [API 1+]
- getBinderProxyObjectCount(): int [static]  [API 1+]
- getBinderReceivedTransactions(): int [static]  [API 1+]
- getBinderSentTransactions(): int [static]  [API 1+]
- getGlobalAllocCount(): int [static]  [API 1+, deprecated in 23]
- getGlobalAllocSize(): int [static]  [API 1+, deprecated in 23]
- getGlobalClassInitCount(): int [static]  [API 8+, deprecated in 23]
- getGlobalClassInitTime(): int [static]  [API 8+, deprecated in 23]
- getGlobalExternalAllocCount(): int [static]  [API 1+, deprecated in 15]
- getGlobalExternalAllocSize(): int [static]  [API 1+, deprecated in 15]
- getGlobalExternalFreedCount(): int [static]  [API 1+, deprecated in 15]
- getGlobalExternalFreedSize(): int [static]  [API 1+, deprecated in 15]
- getGlobalFreedCount(): int [static]  [API 1+, deprecated in 23]
- getGlobalFreedSize(): int [static]  [API 1+, deprecated in 23]
- getGlobalGcInvocationCount(): int [static]  [API 1+, deprecated in 23]
- getLoadedClassCount(): int [static]  [API 1+]
+ getMemoryInfo(Debug.MemoryInfo): void [static]  [API 1+]
~ getNativeHeapAllocatedSize(): long [static]  [API 1+]
~ getNativeHeapFreeSize(): long [static]  [API 1+]
~ getNativeHeapSize(): long [static]  [API 1+]
~ getPss(): long [static]  [API 14+]
- getRss(): long [static]  [API 35+]
- getRuntimeStat(String): String [static]  [API 23+]
- getRuntimeStats(): Map [static]  [API 23+]
- getThreadAllocCount(): int [static]  [API 1+, deprecated in 23]
- getThreadAllocSize(): int [static]  [API 1+, deprecated in 23]
- getThreadExternalAllocCount(): int [static]  [API 1+, deprecated in 15]
- getThreadExternalAllocSize(): int [static]  [API 1+, deprecated in 15]
- getThreadGcInvocationCount(): int [static]  [API 1+, deprecated in 23]
~ isDebuggerConnected(): boolean [static]  [API 1+]
- printLoadedClasses(int): void [static]  [API 1+]
- resetAllCounts(): void [static]  [API 1+, deprecated in 23]
- resetGlobalAllocCount(): void [static]  [API 1+, deprecated in 23]
- resetGlobalAllocSize(): void [static]  [API 1+, deprecated in 23]
- resetGlobalClassInitCount(): void [static]  [API 8+, deprecated in 23]
- resetGlobalClassInitTime(): void [static]  [API 8+, deprecated in 23]
- resetGlobalExternalAllocCount(): void [static]  [API 1+, deprecated in 15]
- resetGlobalExternalAllocSize(): void [static]  [API 1+, deprecated in 15]
- resetGlobalExternalFreedCount(): void [static]  [API 1+, deprecated in 15]
- resetGlobalExternalFreedSize(): void [static]  [API 1+, deprecated in 15]
- resetGlobalFreedCount(): void [static]  [API 1+, deprecated in 23]
- resetGlobalFreedSize(): void [static]  [API 1+, deprecated in 23]
- resetGlobalGcInvocationCount(): void [static]  [API 1+, deprecated in 23]
- resetThreadAllocCount(): void [static]  [API 1+, deprecated in 23]
- resetThreadAllocSize(): void [static]  [API 1+, deprecated in 23]
- resetThreadExternalAllocCount(): void [static]  [API 1+, deprecated in 15]
- resetThreadExternalAllocSize(): void [static]  [API 1+, deprecated in 15]
- resetThreadGcInvocationCount(): void [static]  [API 1+, deprecated in 23]
- setAllocationLimit(int): int [static]  [API 1+, deprecated in 15]
- setGlobalAllocationLimit(int): int [static]  [API 1+, deprecated in 15]
- startAllocCounting(): void [static]  [API 1+, deprecated in 18]
~ startMethodTracing(): void [static]  [API 1+]
- startMethodTracing(String): void [static]  [API 1+]
- startMethodTracing(String, int): void [static]  [API 1+]
- startMethodTracing(String, int, int): void [static]  [API 1+]
- startMethodTracingSampling(String, int, int): void [static]  [API 21+]
- startNativeTracing(): void [static]  [API 1+]
- stopAllocCounting(): void [static]  [API 1+, deprecated in 18]
~ stopMethodTracing(): void [static]  [API 1+]
- stopNativeTracing(): void [static]  [API 1+]
- threadCpuTimeNanos(): long [static]  [API 1+]
- waitForDebugger(): void [static]  [API 1+]
~ waitingForDebugger(): boolean [static]  [API 1+]
```

## Debug.InstructionCount — declaration; API 1+, deprecated in 23 (0 +, 0 *, 0 ~, 5 -)

```
- InstructionCount()  [API 1+]
- collect(): boolean  [API 1+]
- globalMethodInvocations(): int  [API 1+]
- globalTotal(): int  [API 1+]
- resetAndStart(): boolean  [API 1+]
```

## Debug.MemoryInfo — framework; API 1+ (4 +, 0 *, 4 ~, 8 -)

```
- CREATOR [field]  [API 5+]
+ MemoryInfo()  [API 1+]
+ dalvikPrivateDirty [field]  [API 1+]
+ dalvikPss [field]  [API 1+]
+ dalvikSharedDirty [field]  [API 1+]
- describeContents(): int  [API 1+]
~ getMemoryStat(String): String  [API 23+]
- getMemoryStats(): Map  [API 23+]
- getOtherLabel(int): String  [API 14-18, removed in 19]
- getOtherPrivateDirty(int): int  [API 14-18, removed in 19]
- getOtherPss(int): int  [API 14-18, removed in 19]
- getOtherSharedDirty(int): int  [API 14-18, removed in 19]
~ getTotalPrivateClean(): int  [API 19+]
+ getTotalPrivateDirty(): int  [API 5+]
+ getTotalPss(): int  [API 5+]
~ getTotalSharedClean(): int  [API 19+]
+ getTotalSharedDirty(): int  [API 5+]
~ getTotalSwappablePss(): int  [API 19+]
+ nativePrivateDirty [field]  [API 1+]
+ nativePss [field]  [API 1+]
+ nativeSharedDirty [field]  [API 1+]
+ otherPrivateDirty [field]  [API 1+]
+ otherPss [field]  [API 1+]
+ otherSharedDirty [field]  [API 1+]
- readFromParcel(Parcel): void  [API 5+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## DropBoxManager — framework; API 8+ (0 +, 0 *, 0 ~, 6 -)

```
- DropBoxManager()  [API 8+]
- addData(String, byte[], int): void  [API 8+]
- addFile(String, File, int): void  [API 8+]
- addText(String, String): void  [API 8+]
- getNextEntry(String, long): DropBoxManager.Entry  [API 8+]
- isTagEnabled(String): boolean  [API 8+]
```

## DropBoxManager.Entry — framework; API 8+ (0 +, 0 *, 0 ~, 13 -)

```
- CREATOR [field]  [API 8+]
- Entry(String, long)  [API 8+]
- Entry(String, long, File, int)  [API 8+]
- Entry(String, long, ParcelFileDescriptor, int)  [API 8+]
- Entry(String, long, String)  [API 8+]
- Entry(String, long, byte[], int)  [API 8+]
- close(): void  [API 8+]
- describeContents(): int  [API 8+]
- getFlags(): int  [API 8+]
- getInputStream(): InputStream  [API 8+]
- getTag(): String  [API 8+]
- getText(int): String  [API 8+]
- getTimeMillis(): long  [API 8+]
- writeToParcel(Parcel, int): void  [API 8+]
```

## Environment — framework; API 1+ (6 +, 0 *, 5 ~, 7 -)

```
+ DIRECTORY_ALARMS [field]  [API 8+]
- DIRECTORY_AUDIOBOOKS [field]  [API 29+]
+ DIRECTORY_DCIM [field]  [API 8+]
+ DIRECTORY_DOCUMENTS [field]  [API 19+]
+ DIRECTORY_DOWNLOADS [field]  [API 8+]
+ DIRECTORY_MOVIES [field]  [API 8+]
+ DIRECTORY_MUSIC [field]  [API 8+]
+ DIRECTORY_NOTIFICATIONS [field]  [API 8+]
+ DIRECTORY_PICTURES [field]  [API 8+]
+ DIRECTORY_PODCASTS [field]  [API 8+]
- DIRECTORY_RECORDINGS [field]  [API 31+]
+ DIRECTORY_RINGTONES [field]  [API 8+]
- DIRECTORY_SCREENSHOTS [field]  [API 29+]
+ Environment()  [API 1+]
+ getDataDirectory(): File [static]  [API 1+]
+ getDownloadCacheDirectory(): File [static]  [API 1+]
+ getExternalStorageDirectory(): File [static]  [API 1+]
+ getExternalStoragePublicDirectory(String): File [static]  [API 8+]
~ getExternalStorageState(): String [static]  [API 1+]
~ getExternalStorageState(File): String [static]  [API 21+]
+ getRootDirectory(): File [static]  [API 1+]
- getStorageDirectory(): File [static]  [API 30+]
~ getStorageState(File): String [static]  [API 19+, deprecated in 21]
~ isExternalStorageEmulated(): boolean [static]  [API 11+]
- isExternalStorageEmulated(File): boolean [static]  [API 21+]
- isExternalStorageLegacy(): boolean [static]  [API 29+]
- isExternalStorageLegacy(File): boolean [static]  [API 29+]
- isExternalStorageManager(): boolean [static]  [API 30+]
- isExternalStorageManager(File): boolean [static]  [API 30+]
~ isExternalStorageRemovable(): boolean [static]  [API 9+]
- isExternalStorageRemovable(File): boolean [static]  [API 21+]
```

## FileObserver — framework; API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ FileObserver(File)  [API 29+]
+ FileObserver(File, int)  [API 29+]
+ FileObserver(List)  [API 29+]
+ FileObserver(List, int)  [API 29+]
+ FileObserver(String)  [API 1+, deprecated in 29]
+ FileObserver(String, int)  [API 1+, deprecated in 29]
+ finalize(): void  [API 1+]
+ onEvent(int, String): void  [API 1+]
+ startWatching(): void  [API 1+]
+ stopWatching(): void  [API 1+]
```

## FileUriExposedException — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ FileUriExposedException(String)  [API 24+]
```

## FileUtils — declaration; API 29+ (0 +, 0 *, 0 ~, 6 -)

```
- closeQuietly(AutoCloseable): void [static]  [API 29+, deprecated in 30]
- closeQuietly(FileDescriptor): void [static]  [API 29+, deprecated in 30]
- copy(FileDescriptor, FileDescriptor): long [static]  [API 29+]
- copy(FileDescriptor, FileDescriptor, CancellationSignal, Executor, FileUtils.ProgressListener): long [static]  [API 29+]
- copy(InputStream, OutputStream): long [static]  [API 29+]
- copy(InputStream, OutputStream, CancellationSignal, Executor, FileUtils.ProgressListener): long [static]  [API 29+]
```

## FileUtils.ProgressListener — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ onProgress(long): void  [API 29+]
```

## Handler — framework; API 1+ (37 +, 0 *, 1 ~, 0 -)

```
+ Handler()  [API 1+, deprecated in 30]
+ Handler(Handler.Callback)  [API 3+, deprecated in 30]
+ Handler(Looper)  [API 1+]
+ Handler(Looper, Handler.Callback)  [API 3+]
+ createAsync(Looper): Handler [static]  [API 28+]
+ createAsync(Looper, Handler.Callback): Handler [static]  [API 28+]
+ dispatchMessage(Message): void  [API 1+]
+ dump(Printer, String): void  [API 1+]
+ getLooper(): Looper  [API 1+]
+ getMessageName(Message): String  [API 14+]
~ handleMessage(Message): void  [API 1+]
+ hasCallbacks(Runnable): boolean  [API 29+]
+ hasMessages(int): boolean  [API 1+]
+ hasMessages(int, Object): boolean  [API 1+]
+ obtainMessage(): Message  [API 1+]
+ obtainMessage(int): Message  [API 1+]
+ obtainMessage(int, Object): Message  [API 1+]
+ obtainMessage(int, int, int): Message  [API 1+]
+ obtainMessage(int, int, int, Object): Message  [API 1+]
+ post(Runnable): boolean  [API 1+]
+ postAtFrontOfQueue(Runnable): boolean  [API 1+]
+ postAtTime(Runnable, Object, long): boolean  [API 1+]
+ postAtTime(Runnable, long): boolean  [API 1+]
+ postDelayed(Runnable, Object, long): boolean  [API 28+]
+ postDelayed(Runnable, long): boolean  [API 1+]
+ removeCallbacks(Runnable): void  [API 1+]
+ removeCallbacks(Runnable, Object): void  [API 1+]
+ removeCallbacksAndMessages(Object): void  [API 1+]
+ removeMessages(int): void  [API 1+]
+ removeMessages(int, Object): void  [API 1+]
+ sendEmptyMessage(int): boolean  [API 1+]
+ sendEmptyMessageAtTime(int, long): boolean  [API 1+]
+ sendEmptyMessageDelayed(int, long): boolean  [API 1+]
+ sendMessage(Message): boolean  [API 1+]
+ sendMessageAtFrontOfQueue(Message): boolean  [API 1+]
+ sendMessageAtTime(Message, long): boolean  [API 1+]
+ sendMessageDelayed(Message, long): boolean  [API 1+]
+ toString(): String  [API 1+]
```

## Handler.Callback — framework; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ handleMessage(Message): boolean  [API 3+]
```

## HandlerThread — framework; API 1+ (6 +, 0 *, 0 ~, 2 -)

```
+ HandlerThread(String)  [API 1+]
+ HandlerThread(String, int)  [API 1+]
+ getLooper(): Looper  [API 1+]
- getThreadId(): int  [API 1+]
+ onLooperPrepared(): void  [API 1+]
+ quit(): boolean  [API 5+]
- quitSafely(): boolean  [API 18+]
+ run(): void  [API 1+]
```

## HardwarePropertiesManager — framework; API 24+ (0 +, 0 *, 0 ~, 3 -)

```
- getCpuUsages(): CpuUsageInfo[]  [API 24+]
- getDeviceTemperatures(int, int): float[]  [API 24+]
- getFanSpeeds(): float[]  [API 24+]
```

## IBinder — framework; API 1+ (9 +, 0 *, 1 ~, 0 -)

```
+ dump(FileDescriptor, String[]): void  [API 3+]
+ dumpAsync(FileDescriptor, String[]): void  [API 13+]
+ getInterfaceDescriptor(): String  [API 1+]
~ getSuggestedMaxIpcSizeBytes(): int [static]  [API 30+]
+ isBinderAlive(): boolean  [API 1+]
+ linkToDeath(IBinder.DeathRecipient, int): void  [API 1+]
+ pingBinder(): boolean  [API 1+]
+ queryLocalInterface(String): IInterface  [API 1+]
+ transact(int, Parcel, Parcel, int): boolean  [API 1+]
+ unlinkToDeath(IBinder.DeathRecipient, int): boolean  [API 1+]
```

## IBinder.DeathRecipient — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ binderDied(): void  [API 1+]
+ binderDied(IBinder): void  [API 34+]
```

## IInterface — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ asBinder(): IBinder  [API 1+]
```

## LimitExceededException — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ LimitExceededException()  [API 30+]
+ LimitExceededException(String)  [API 30+]
```

## LocaleList — framework; API 24+ (18 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 24+]
+ LocaleList(Locale[])  [API 24+]
~ describeContents(): int  [API 24+]
+ equals(Object): boolean  [API 24+]
+ forLanguageTags(String): LocaleList [static]  [API 24+]
+ get(int): Locale  [API 24+]
+ getAdjustedDefault(): LocaleList [static]  [API 24+]
+ getDefault(): LocaleList [static]  [API 24+]
+ getEmptyLocaleList(): LocaleList [static]  [API 24+]
+ getFirstMatch(String[]): Locale  [API 24+]
+ hashCode(): int  [API 24+]
+ indexOf(Locale): int  [API 24+]
+ isEmpty(): boolean  [API 24+]
+ isPseudoLocale(ULocale): boolean [static]  [API 29+]
+ matchesLanguageAndScript(Locale, Locale): boolean [static]  [API 33+]
+ setDefault(LocaleList): void [static]  [API 24+]
+ size(): int  [API 24+]
+ toLanguageTags(): String  [API 24+]
+ toString(): String  [API 24+]
+ writeToParcel(Parcel, int): void  [API 24+]
```

## Looper — framework; API 1+ (10 +, 0 *, 0 ~, 4 -)

```
- dump(Printer, String): void  [API 1+]
+ getMainLooper(): Looper [static]  [API 1+]
+ getQueue(): MessageQueue  [API 23+]
+ getThread(): Thread  [API 3+]
- isCurrentThread(): boolean  [API 23+]
+ loop(): void [static]  [API 1+]
+ myLooper(): Looper [static]  [API 1+]
+ myQueue(): MessageQueue [static]  [API 1+]
+ prepare(): void [static]  [API 1+]
+ prepareMainLooper(): void [static]  [API 1+, deprecated in 30]
+ quit(): void  [API 1+]
- quitSafely(): void  [API 18+]
- setMessageLogging(Printer): void  [API 1+]
+ toString(): String  [API 1+]
```

## MemoryFile — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- MemoryFile(String, int)  [API 1+]
- allowPurging(boolean): boolean  [API 1+, deprecated in 27]
- close(): void  [API 1+]
- getInputStream(): InputStream  [API 1+]
- getOutputStream(): OutputStream  [API 1+]
- isPurgingAllowed(): boolean  [API 1+, deprecated in 27]
- length(): int  [API 1+]
- readBytes(byte[], int, int, int): int  [API 1+]
- writeBytes(byte[], int, int, int): void  [API 1+]
```

## Message — framework; API 1+ (20 +, 0 *, 1 ~, 3 -)

```
- CREATOR [field]  [API 1+]
+ Message()  [API 1+]
+ arg1 [field]  [API 1+]
+ arg2 [field]  [API 1+]
- copyFrom(Message): void  [API 1+]
- describeContents(): int  [API 1+]
+ getCallback(): Runnable  [API 1+]
+ getData(): Bundle  [API 1+]
+ getTarget(): Handler  [API 1+]
+ getWhen(): long  [API 1+]
+ isAsynchronous(): boolean  [API 22+]
+ obj [field]  [API 1+]
+ obtain(): Message [static]  [API 1+]
+ obtain(Handler): Message [static]  [API 1+]
+ obtain(Handler, Runnable): Message [static]  [API 1+]
+ obtain(Handler, int): Message [static]  [API 1+]
+ obtain(Handler, int, Object): Message [static]  [API 1+]
+ obtain(Handler, int, int, int): Message [static]  [API 1+]
+ obtain(Handler, int, int, int, Object): Message [static]  [API 1+]
+ obtain(Message): Message [static]  [API 1+]
+ peekData(): Bundle  [API 1+]
~ recycle(): void  [API 1+]
+ replyTo [field]  [API 1+]
+ sendToTarget(): void  [API 1+]
- sendingUid [field]  [API 21+]
+ setAsynchronous(boolean): void  [API 22+]
+ setData(Bundle): void  [API 1+]
+ setTarget(Handler): void  [API 1+]
+ toString(): String  [API 1+]
+ what [field]  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## MessageQueue — framework; API 1+ (3 +, 0 *, 0 ~, 3 -)

```
+ addIdleHandler(MessageQueue.IdleHandler): void  [API 1+]
- addOnFileDescriptorEventListener(FileDescriptor, int, MessageQueue.OnFileDescriptorEventListener): void  [API 23+]
+ finalize(): void  [API 1+]
- isIdle(): boolean  [API 23+]
+ removeIdleHandler(MessageQueue.IdleHandler): void  [API 1+]
- removeOnFileDescriptorEventListener(FileDescriptor): void  [API 23+]
```

## MessageQueue.IdleHandler — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ queueIdle(): boolean  [API 1+]
```

## MessageQueue.OnFileDescriptorEventListener — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onFileDescriptorEvents(FileDescriptor, int): int  [API 23+]
```

## Messenger — framework; API 1+ (6 +, 0 *, 2 ~, 2 -)

```
- CREATOR [field]  [API 1+]
+ Messenger(Handler)  [API 1+]
+ Messenger(IBinder)  [API 1+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getBinder(): IBinder  [API 1+]
+ hashCode(): int  [API 1+]
- readMessengerOrNullFromParcel(Parcel): Messenger [static]  [API 1+]
+ send(Message): void  [API 1+]
- writeMessengerOrNullToParcel(Messenger, Parcel): void [static]  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## NetworkOnMainThreadException — declaration; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ NetworkOnMainThreadException()  [API 11+]
```

## OperationCanceledException — framework; API 16+ (2 +, 0 *, 0 ~, 0 -)

```
+ OperationCanceledException()  [API 16+]
+ OperationCanceledException(String)  [API 16+]
```

## OutcomeReceiver — declaration; API 31+ (2 +, 0 *, 0 ~, 0 -)

```
+ onError(Throwable): void  [API 31+]
+ onResult(Object): void  [API 31+]
```

## Parcel — framework; API 1+ (150 +, 0 *, 1 ~, 1 -)

```
+ STRING_CREATOR [field]  [API 1+]
+ appendFrom(Parcel, int, int): void  [API 1+]
+ createBinderArray(): IBinder[]  [API 1+]
+ createBinderArrayList(): ArrayList  [API 1+]
+ createBooleanArray(): boolean[]  [API 1+]
+ createByteArray(): byte[]  [API 1+]
+ createCharArray(): char[]  [API 1+]
+ createDoubleArray(): double[]  [API 1+]
+ createFixedArray(Class, Function, int[]): Object  [API 33+]
+ createFixedArray(Class, Parcelable.Creator, int[]): Object  [API 33+]
+ createFixedArray(Class, int[]): Object  [API 33+]
+ createFloatArray(): float[]  [API 1+]
+ createIntArray(): int[]  [API 1+]
+ createInterfaceArray(IntFunction, Function): IInterface[]  [API 33+]
+ createInterfaceArrayList(Function): ArrayList  [API 33+]
+ createLongArray(): long[]  [API 1+]
+ createStringArray(): String[]  [API 1+]
+ createStringArrayList(): ArrayList  [API 1+]
+ createTypedArray(Parcelable.Creator): Object[]  [API 1+]
+ createTypedArrayList(Parcelable.Creator): ArrayList  [API 1+]
+ createTypedArrayMap(Parcelable.Creator): ArrayMap  [API 29+]
+ createTypedSparseArray(Parcelable.Creator): SparseArray  [API 29+]
+ dataAvail(): int  [API 1+]
+ dataCapacity(): int  [API 1+]
+ dataPosition(): int  [API 1+]
+ dataSize(): int  [API 1+]
+ enforceInterface(String): void  [API 1+]
+ enforceNoDataAvail(): void  [API 33+]
+ finalize(): void  [API 1+]
+ hasFileDescriptors(): boolean  [API 1+]
+ hasFileDescriptors(int, int): boolean  [API 33+]
+ marshall(): byte[]  [API 1+]
+ obtain(): Parcel [static]  [API 1+]
+ obtain(IBinder): Parcel [static]  [API 33+]
+ obtain(int): Parcel  [API 1-20, removed in 21]
+ readArray(ClassLoader): Object[]  [API 1+, deprecated in 33]
+ readArray(ClassLoader, Class): Object[]  [API 33+]
+ readArrayList(ClassLoader): ArrayList  [API 1+, deprecated in 33]
+ readArrayList(ClassLoader, Class): ArrayList  [API 33+]
+ readBinderArray(IBinder[]): void  [API 1+]
+ readBinderList(List): void  [API 1+]
+ readBlob(): byte[]  [API 33+]
+ readBoolean(): boolean  [API 29+]
+ readBooleanArray(boolean[]): void  [API 1+]
+ readBundle(): Bundle  [API 1+]
+ readBundle(ClassLoader): Bundle  [API 1+]
+ readByte(): byte  [API 1+]
+ readByteArray(byte[]): void  [API 1+]
+ readCharArray(char[]): void  [API 1+]
+ readDouble(): double  [API 1+]
+ readDoubleArray(double[]): void  [API 1+]
+ readException(): void  [API 1+]
+ readException(int, String): void  [API 1+]
+ readFileDescriptor(): ParcelFileDescriptor  [API 1+]
+ readFixedArray(Object): void  [API 33+]
+ readFixedArray(Object, Function): void  [API 33+]
+ readFixedArray(Object, Parcelable.Creator): void  [API 33+]
+ readFloat(): float  [API 1+]
+ readFloatArray(float[]): void  [API 1+]
+ readHashMap(ClassLoader): HashMap  [API 1+, deprecated in 33]
+ readHashMap(ClassLoader, Class, Class): HashMap  [API 33+]
+ readInt(): int  [API 1+]
+ readIntArray(int[]): void  [API 1+]
+ readInterfaceArray(IInterface[], Function): void  [API 33+]
+ readInterfaceList(List, Function): void  [API 33+]
+ readList(List, ClassLoader): void  [API 1+, deprecated in 33]
+ readList(List, ClassLoader, Class): void  [API 33+]
+ readLong(): long  [API 1+]
+ readLongArray(long[]): void  [API 1+]
+ readMap(Map, ClassLoader): void  [API 1+, deprecated in 33]
+ readMap(Map, ClassLoader, Class, Class): void  [API 33+]
+ readParcelable(ClassLoader): Parcelable  [API 1+, deprecated in 33]
+ readParcelable(ClassLoader, Class): Object  [API 33+]
+ readParcelableArray(ClassLoader): Parcelable[]  [API 1+, deprecated in 33]
+ readParcelableArray(ClassLoader, Class): Object[]  [API 33+]
+ readParcelableCreator(ClassLoader): Parcelable.Creator  [API 30+, deprecated in 33]
+ readParcelableCreator(ClassLoader, Class): Parcelable.Creator  [API 33+]
+ readParcelableList(List, ClassLoader): List  [API 29+, deprecated in 33]
+ readParcelableList(List, ClassLoader, Class): List  [API 33+]
+ readPersistableBundle(): PersistableBundle  [API 21+]
+ readPersistableBundle(ClassLoader): PersistableBundle  [API 21+]
+ readSerializable(): Serializable  [API 1+, deprecated in 33]
+ readSerializable(ClassLoader, Class): Object  [API 33+]
+ readSize(): Size  [API 21+]
+ readSizeF(): SizeF  [API 21+]
+ readSparseArray(ClassLoader): SparseArray  [API 1+, deprecated in 33]
+ readSparseArray(ClassLoader, Class): SparseArray  [API 33+]
+ readSparseBooleanArray(): SparseBooleanArray  [API 1+]
+ readString(): String  [API 1+]
+ readStringArray(String[]): void  [API 1+]
+ readStringList(List): void  [API 1+]
+ readStrongBinder(): IBinder  [API 1+]
+ readTypedArray(Object[], Parcelable.Creator): void  [API 1+]
+ readTypedList(List, Parcelable.Creator): void  [API 1+]
+ readTypedObject(Parcelable.Creator): Object  [API 23+]
+ readValue(ClassLoader): Object  [API 1+]
+ recycle(): void  [API 1+]
+ setDataCapacity(int): void  [API 1+]
+ setDataPosition(int): void  [API 1+]
+ setDataSize(int): void  [API 1+]
~ setPropagateAllowBlocking(): void  [API 33+]
+ unmarshall(byte[], int, int): void  [API 1+]
+ writeArray(Object[]): void  [API 1+]
+ writeBinderArray(IBinder[]): void  [API 1+]
+ writeBinderList(List): void  [API 1+]
+ writeBlob(byte[]): void  [API 33+]
+ writeBlob(byte[], int, int): void  [API 33+]
+ writeBoolean(boolean): void  [API 29+]
+ writeBooleanArray(boolean[]): void  [API 1+]
+ writeBundle(Bundle): void  [API 1+]
+ writeByte(byte): void  [API 1+]
+ writeByteArray(byte[]): void  [API 1+]
+ writeByteArray(byte[], int, int): void  [API 1+]
+ writeCharArray(char[]): void  [API 1+]
+ writeDouble(double): void  [API 1+]
+ writeDoubleArray(double[]): void  [API 1+]
+ writeException(Exception): void  [API 1+]
- writeFileDescriptor(FileDescriptor): void  [API 1+]
+ writeFixedArray(Object, int, int[]): void  [API 33+]
+ writeFloat(float): void  [API 1+]
+ writeFloatArray(float[]): void  [API 1+]
+ writeInt(int): void  [API 1+]
+ writeIntArray(int[]): void  [API 1+]
+ writeInterfaceArray(IInterface[]): void  [API 33+]
+ writeInterfaceList(List): void  [API 33+]
+ writeInterfaceToken(String): void  [API 1+]
+ writeList(List): void  [API 1+]
+ writeLong(long): void  [API 1+]
+ writeLongArray(long[]): void  [API 1+]
+ writeMap(Map): void  [API 1+]
+ writeNoException(): void  [API 1+]
+ writeParcelable(Parcelable, int): void  [API 1+]
+ writeParcelableArray(Parcelable[], int): void  [API 1+]
+ writeParcelableCreator(Parcelable): void  [API 30+]
+ writeParcelableList(List, int): void  [API 29+]
+ writePersistableBundle(PersistableBundle): void  [API 21+]
+ writeSerializable(Serializable): void  [API 1+]
+ writeSize(Size): void  [API 21+]
+ writeSizeF(SizeF): void  [API 21+]
+ writeSparseArray(SparseArray): void  [API 1+]
+ writeSparseBooleanArray(SparseBooleanArray): void  [API 1+]
+ writeString(String): void  [API 1+]
+ writeStringArray(String[]): void  [API 1+]
+ writeStringList(List): void  [API 1+]
+ writeStrongBinder(IBinder): void  [API 1+]
+ writeStrongInterface(IInterface): void  [API 1+]
+ writeTypedArray(Parcelable[], int): void  [API 1+]
+ writeTypedArrayMap(ArrayMap, int): void  [API 29+]
+ writeTypedList(List): void  [API 1+]
+ writeTypedList(List, int): void  [API 34+]
+ writeTypedObject(Parcelable, int): void  [API 23+]
+ writeTypedSparseArray(SparseArray, int): void  [API 29+]
+ writeValue(Object): void  [API 1+]
```

## ParcelFileDescriptor — framework; API 1+ (9 +, 0 *, 6 ~, 12 -)

```
- CREATOR [field]  [API 1+]
+ ParcelFileDescriptor(ParcelFileDescriptor)  [API 1+]
- adoptFd(int): ParcelFileDescriptor [static]  [API 13+]
- canDetectErrors(): boolean  [API 19+]
~ checkError(): void  [API 19+]
+ close(): void  [API 1+]
+ closeWithError(String): void  [API 19+]
~ createPipe(): ParcelFileDescriptor[] [static]  [API 9+]
- createReliablePipe(): ParcelFileDescriptor[] [static]  [API 19+]
- createReliableSocketPair(): ParcelFileDescriptor[] [static]  [API 19+]
- createSocketPair(): ParcelFileDescriptor[] [static]  [API 19+]
~ describeContents(): int  [API 1+]
~ detachFd(): int  [API 12+]
- dup(): ParcelFileDescriptor  [API 14+]
- dup(FileDescriptor): ParcelFileDescriptor [static]  [API 13+]
+ finalize(): void  [API 1+]
- fromDatagramSocket(DatagramSocket): ParcelFileDescriptor [static]  [API 14+]
- fromFd(int): ParcelFileDescriptor [static]  [API 13+]
- fromSocket(Socket): ParcelFileDescriptor [static]  [API 1+]
~ getFd(): int  [API 12+]
+ getFileDescriptor(): FileDescriptor  [API 1+]
+ getStatSize(): long  [API 3+]
+ open(File, int): ParcelFileDescriptor [static]  [API 1+]
- open(File, int, Handler, ParcelFileDescriptor.OnCloseListener): ParcelFileDescriptor [static]  [API 19+]
+ parseMode(String): int [static]  [API 19+]
+ toString(): String  [API 1+]
- wrap(ParcelFileDescriptor, Handler, ParcelFileDescriptor.OnCloseListener): ParcelFileDescriptor [static]  [API 30+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## ParcelFileDescriptor.AutoCloseInputStream — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ AutoCloseInputStream(ParcelFileDescriptor)  [API 1+]
+ close(): void  [API 1+]
+ read(): int  [API 1+]
+ read(byte[]): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
```

## ParcelFileDescriptor.AutoCloseOutputStream — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ AutoCloseOutputStream(ParcelFileDescriptor)  [API 1+]
+ close(): void  [API 1+]
```

## ParcelFileDescriptor.FileDescriptorDetachedException — declaration; API 19+ (1 +, 0 *, 0 ~, 0 -)

```
+ FileDescriptorDetachedException()  [API 19+]
```

## ParcelFileDescriptor.OnCloseListener — declaration; API 19+ (1 +, 0 *, 0 ~, 0 -)

```
+ onClose(IOException): void  [API 19+]
```

## ParcelFormatException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ParcelFormatException()  [API 1+]
+ ParcelFormatException(String)  [API 1+]
```

## ParcelUuid — framework; API 5+ (7 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 5+]
+ ParcelUuid(UUID)  [API 5+]
~ describeContents(): int  [API 5+]
+ equals(Object): boolean  [API 5+]
+ fromString(String): ParcelUuid [static]  [API 5+]
+ getUuid(): UUID  [API 5+]
+ hashCode(): int  [API 5+]
+ toString(): String  [API 5+]
+ writeToParcel(Parcel, int): void  [API 5+]
```

## Parcelable — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ describeContents(): int  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## Parcelable.ClassLoaderCreator — framework; API 13+ (1 +, 0 *, 0 ~, 0 -)

```
+ createFromParcel(Parcel, ClassLoader): Object  [API 13+]
```

## Parcelable.Creator — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ createFromParcel(Parcel): Object  [API 1+]
+ newArray(int): Object[]  [API 1+]
```

## PatternMatcher — framework; API 1+ (7 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ PatternMatcher(Parcel)  [API 1+]
+ PatternMatcher(String, int)  [API 1+]
~ describeContents(): int  [API 1+]
+ getPath(): String  [API 1+]
+ getType(): int  [API 1+]
+ match(String): boolean  [API 1+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## PerformanceHintManager — framework; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- createHintSession(int[], long): PerformanceHintManager.Session  [API 31+]
- getPreferredUpdateRateNanos(): long  [API 31+]
```

## PerformanceHintManager.Session — framework; API 31+ (0 +, 0 *, 0 ~, 7 -)

```
- close(): void  [API 31+]
- finalize(): void  [API 31+]
- reportActualWorkDuration(WorkDuration): void  [API 35+]
- reportActualWorkDuration(long): void  [API 31+]
- setPreferPowerEfficiency(boolean): void  [API 35+]
- setThreads(int[]): void  [API 34+]
- updateTargetWorkDuration(long): void  [API 31+]
```

## PersistableBundle — framework; API 21+ (11 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 21+]
+ EMPTY [field]  [API 21+]
+ PersistableBundle()  [API 21+]
+ PersistableBundle(PersistableBundle)  [API 21+]
+ PersistableBundle(int)  [API 21+]
+ clone(): Object  [API 21+]
+ deepCopy(): PersistableBundle  [API 26+]
~ describeContents(): int  [API 21+]
+ getPersistableBundle(String): PersistableBundle  [API 21+]
+ putPersistableBundle(String, PersistableBundle): void  [API 21+]
+ readFromStream(InputStream): PersistableBundle [static]  [API 30+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
+ writeToStream(OutputStream): void  [API 30+]
```

## PowerManager — framework; API 1+ (1 +, 0 *, 1 ~, 25 -)

```
- addThermalStatusListener(Executor, PowerManager.OnThermalStatusChangedListener): void  [API 29+]
- addThermalStatusListener(PowerManager.OnThermalStatusChangedListener): void  [API 29+]
- getBatteryDischargePrediction(): Duration  [API 31+]
- getCurrentThermalStatus(): int  [API 29+]
- getLocationPowerSaveMode(): int  [API 28+]
- getThermalHeadroom(int): float  [API 30+]
- getThermalHeadroomThresholds(): Map  [API 35+]
- goToSleep(long): void  [API 1-20, removed in 21]
- isAllowedInLowPowerStandby(String): boolean  [API 34+]
- isAllowedInLowPowerStandby(int): boolean  [API 34+]
- isBatteryDischargePredictionPersonalized(): boolean  [API 31+]
- isDeviceIdleMode(): boolean  [API 23+]
- isDeviceLightIdleMode(): boolean  [API 33+]
- isExemptFromLowPowerStandby(): boolean  [API 34+]
- isIgnoringBatteryOptimizations(String): boolean  [API 23+]
- isInteractive(): boolean  [API 20+]
- isLowPowerStandbyEnabled(): boolean  [API 33+]
- isPowerSaveMode(): boolean  [API 21+]
- isRebootingUserspaceSupported(): boolean  [API 30+, deprecated in 35]
~ isScreenOn(): boolean  [API 7+, deprecated in 20]
- isSustainedPerformanceModeSupported(): boolean  [API 24+]
- isWakeLockLevelSupported(int): boolean  [API 21+]
+ newWakeLock(int, String): PowerManager.WakeLock  [API 1+]
- reboot(String): void  [API 8+]
- removeThermalStatusListener(PowerManager.OnThermalStatusChangedListener): void  [API 29+]
- userActivity(long, boolean): void  [API 1-20, removed in 21]
- wakeUp(long): void  [API 17-20, removed in 21]
```

## PowerManager.OnThermalStatusChangedListener — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ onThermalStatusChanged(int): void  [API 29+]
```

## PowerManager.WakeLock — framework; API 1+ (6 +, 0 *, 1 ~, 3 -)

```
+ acquire(): void  [API 1+]
+ acquire(long): void  [API 1+]
+ finalize(): void  [API 1+]
+ isHeld(): boolean  [API 1+]
+ release(): void  [API 1+]
- release(int): void  [API 21+]
~ setReferenceCounted(boolean): void  [API 1+]
- setStateListener(Executor, PowerManager.WakeLockStateListener): void  [API 33+]
- setWorkSource(WorkSource): void  [API 9+]
+ toString(): String  [API 1+]
```

## PowerManager.WakeLockStateListener — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ onStateChanged(boolean): void  [API 33+]
```

## PowerMonitor — declaration; API 35+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- getName(): String  [API 35+]
- getType(): int  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## PowerMonitorReadings — declaration; API 35+ (0 +, 0 *, 0 ~, 3 -)

```
- getConsumedEnergy(PowerMonitor): long  [API 35+]
- getTimestampMillis(PowerMonitor): long  [API 35+]
- toString(): String  [API 35+]
```

## Process — framework; API 1+ (9 +, 0 *, 11 ~, 8 -)

```
+ Process()  [API 1+]
- getAppUidForSdkSandboxUid(int): int [static]  [API 35+]
+ getElapsedCpuTime(): long [static]  [API 1+]
- getExclusiveCores(): int[] [static]  [API 24+]
~ getGidForName(String): int [static]  [API 1+]
- getSdkSandboxUidForAppUid(int): int [static]  [API 35+]
+ getStartElapsedRealtime(): long [static]  [API 24+]
- getStartRequestedElapsedRealtime(): long [static]  [API 33+]
- getStartRequestedUptimeMillis(): long [static]  [API 33+]
+ getStartUptimeMillis(): long [static]  [API 24+]
~ getThreadPriority(int): int [static]  [API 1+]
~ getUidForName(String): int [static]  [API 1+]
~ is64Bit(): boolean [static]  [API 23+]
+ isApplicationUid(int): boolean [static]  [API 24+]
~ isIsolated(): boolean [static]  [API 28+]
- isIsolatedUid(int): boolean [static]  [API 34+]
- isSdkSandbox(): boolean [static]  [API 33+]
- isSdkSandboxUid(int): boolean [static]  [API 35+]
+ killProcess(int): void [static]  [API 1+]
~ myPid(): int [static]  [API 1+]
+ myProcessName(): String [static]  [API 33+]
~ myTid(): int [static]  [API 1+]
~ myUid(): int [static]  [API 2+]
+ myUserHandle(): UserHandle [static]  [API 17+]
+ sendSignal(int, int): void [static]  [API 1+]
~ setThreadPriority(int): void [static]  [API 1+]
~ setThreadPriority(int, int): void [static]  [API 1+]
~ supportsProcesses(): boolean [static]  [API 1+, deprecated in 15]
```

## ProfilingManager — framework; API 35+ (0 +, 0 *, 0 ~, 3 -)

```
- registerForAllProfilingResults(Executor, Consumer): void  [API 35+]
- requestProfiling(int, Bundle, String, CancellationSignal, Executor, Consumer): void  [API 35+]
- unregisterForAllProfilingResults(Consumer): void  [API 35+]
```

## ProfilingResult — declaration; API 35+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- getErrorCode(): int  [API 35+]
- getErrorMessage(): String  [API 35+]
- getResultFilePath(): String  [API 35+]
- getTag(): String  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## ProxyFileDescriptorCallback — declaration; API 26+ (2 +, 0 *, 0 ~, 4 -)

```
- ProxyFileDescriptorCallback()  [API 26+]
+ onFsync(): void  [API 26+]
- onGetSize(): long  [API 26+]
- onRead(long, int, byte[]): int  [API 26+]
+ onRelease(): void  [API 26+]
- onWrite(long, int, byte[]): int  [API 26+]
```

## RecoverySystem — declaration; API 8+ (0 +, 0 *, 0 ~, 5 -)

```
- RecoverySystem()  [API 8-22, removed in 23]
- installPackage(Context, File): void [static]  [API 8+]
- rebootWipeCache(Context): void [static]  [API 14+]
- rebootWipeUserData(Context): void [static]  [API 8+]
- verifyPackage(File, RecoverySystem.ProgressListener, File): void [static]  [API 8+]
```

## RecoverySystem.ProgressListener — declaration; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ onProgress(int): void  [API 8+]
```

## RemoteCallbackList — framework; API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ RemoteCallbackList()  [API 1+]
+ beginBroadcast(): int  [API 1+]
+ finishBroadcast(): void  [API 1+]
+ getBroadcastCookie(int): Object  [API 4+]
+ getBroadcastItem(int): IInterface  [API 1+]
+ getRegisteredCallbackCookie(int): Object  [API 26+]
+ getRegisteredCallbackCount(): int  [API 17+]
+ getRegisteredCallbackItem(int): IInterface  [API 26+]
+ kill(): void  [API 1+]
+ onCallbackDied(IInterface): void  [API 1+]
+ onCallbackDied(IInterface, Object): void  [API 4+]
+ register(IInterface): boolean  [API 1+]
+ register(IInterface, Object): boolean  [API 4+]
+ unregister(IInterface): boolean  [API 1+]
```

## RemoteException — framework; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
+ RemoteException()  [API 1+]
+ RemoteException(String)  [API 15+]
- rethrowAsRuntimeException(): RuntimeException  [API 30+]
- rethrowFromSystemServer(): RuntimeException  [API 30+]
```

## ResultReceiver — framework; API 3+ (3 +, 0 *, 2 ~, 0 -)

```
- CREATOR [field]  [API 3+]
+ ResultReceiver(Handler)  [API 3+]
~ describeContents(): int  [API 3+]
+ onReceiveResult(int, Bundle): void  [API 3+]
+ send(int, Bundle): void  [API 3+]
~ writeToParcel(Parcel, int): void  [API 3+]
```

## SecurityStateManager — framework; API 35+ (0 +, 0 *, 0 ~, 1 -)

```
- getGlobalSecurityState(): Bundle  [API 35+]
```

## SharedMemory — declaration; API 27+ (0 +, 0 *, 0 ~, 11 -)

```
- CREATOR [field]  [API 27+]
- close(): void  [API 27+]
- create(String, int): SharedMemory [static]  [API 27+]
- describeContents(): int  [API 27+]
- fromFileDescriptor(ParcelFileDescriptor): SharedMemory [static]  [API 33+]
- getSize(): int  [API 27+]
- map(int, int, int): ByteBuffer  [API 27+]
- mapReadOnly(): ByteBuffer  [API 27+]
- mapReadWrite(): ByteBuffer  [API 27+]
- setProtect(int): boolean  [API 27+]
- unmap(ByteBuffer): void [static]  [API 27+]
- writeToParcel(Parcel, int): void  [API 27+]
```

## StatFs — framework; API 1+ (1 +, 0 *, 5 ~, 7 -)

```
+ StatFs(String)  [API 1+]
~ getAvailableBlocks(): int  [API 1+, deprecated in 18]
- getAvailableBlocksLong(): long  [API 18+]
- getAvailableBytes(): long  [API 18+]
~ getBlockCount(): int  [API 1+, deprecated in 18]
- getBlockCountLong(): long  [API 18+]
~ getBlockSize(): int  [API 1+, deprecated in 18]
- getBlockSizeLong(): long  [API 18+]
~ getFreeBlocks(): int  [API 1+, deprecated in 18]
- getFreeBlocksLong(): long  [API 18+]
- getFreeBytes(): long  [API 18+]
- getTotalBytes(): long  [API 18+]
~ restat(String): void  [API 1+]
```

## StrictMode — framework; API 9+ (3 +, 0 *, 2 ~, 3 -)

```
+ allowThreadDiskReads(): StrictMode.ThreadPolicy [static]  [API 9+]
+ allowThreadDiskWrites(): StrictMode.ThreadPolicy [static]  [API 9+]
- enableDefaults(): void [static]  [API 9+]
+ getThreadPolicy(): StrictMode.ThreadPolicy [static]  [API 9+]
- getVmPolicy(): StrictMode.VmPolicy [static]  [API 9+]
- noteSlowCall(String): void [static]  [API 11+]
~ setThreadPolicy(StrictMode.ThreadPolicy): void [static]  [API 9+]
~ setVmPolicy(StrictMode.VmPolicy): void [static]  [API 9+]
```

## StrictMode.OnThreadViolationListener — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onThreadViolation(Violation): void  [API 28+]
```

## StrictMode.OnVmViolationListener — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onVmViolation(Violation): void  [API 28+]
```

## StrictMode.ThreadPolicy — framework; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ LAX [field]  [API 9+]
+ toString(): String  [API 9+]
```

## StrictMode.ThreadPolicy.Builder — framework; API 9+ (3 +, 0 *, 3 ~, 20 -)

```
+ Builder()  [API 9+]
+ Builder(StrictMode.ThreadPolicy)  [API 9+]
+ build(): StrictMode.ThreadPolicy  [API 9+]
~ detectAll(): StrictMode.ThreadPolicy.Builder  [API 9+]
- detectCustomSlowCalls(): StrictMode.ThreadPolicy.Builder  [API 11+]
- detectDiskReads(): StrictMode.ThreadPolicy.Builder  [API 9+]
- detectDiskWrites(): StrictMode.ThreadPolicy.Builder  [API 9+]
- detectExplicitGc(): StrictMode.ThreadPolicy.Builder  [API 34+]
- detectNetwork(): StrictMode.ThreadPolicy.Builder  [API 9+]
- detectResourceMismatches(): StrictMode.ThreadPolicy.Builder  [API 23+]
- detectUnbufferedIo(): StrictMode.ThreadPolicy.Builder  [API 26+]
- penaltyDeath(): StrictMode.ThreadPolicy.Builder  [API 9+]
- penaltyDeathOnNetwork(): StrictMode.ThreadPolicy.Builder  [API 11+]
- penaltyDialog(): StrictMode.ThreadPolicy.Builder  [API 9+]
- penaltyDropBox(): StrictMode.ThreadPolicy.Builder  [API 9+]
- penaltyFlashScreen(): StrictMode.ThreadPolicy.Builder  [API 11+]
- penaltyListener(Executor, StrictMode.OnThreadViolationListener): StrictMode.ThreadPolicy.Builder  [API 28+]
~ penaltyLog(): StrictMode.ThreadPolicy.Builder  [API 9+]
~ permitAll(): StrictMode.ThreadPolicy.Builder  [API 9+]
- permitCustomSlowCalls(): StrictMode.ThreadPolicy.Builder  [API 11+]
- permitDiskReads(): StrictMode.ThreadPolicy.Builder  [API 9+]
- permitDiskWrites(): StrictMode.ThreadPolicy.Builder  [API 9+]
- permitExplicitGc(): StrictMode.ThreadPolicy.Builder  [API 34+]
- permitNetwork(): StrictMode.ThreadPolicy.Builder  [API 9+]
- permitResourceMismatches(): StrictMode.ThreadPolicy.Builder  [API 23+]
- permitUnbufferedIo(): StrictMode.ThreadPolicy.Builder  [API 26+]
```

## StrictMode.VmPolicy — framework; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ LAX [field]  [API 9+]
+ toString(): String  [API 9+]
```

## StrictMode.VmPolicy.Builder — framework; API 9+ (2 +, 0 *, 2 ~, 22 -)

```
+ Builder()  [API 9+]
- Builder(StrictMode.VmPolicy)  [API 11+]
+ build(): StrictMode.VmPolicy  [API 9+]
- detectActivityLeaks(): StrictMode.VmPolicy.Builder  [API 11+]
~ detectAll(): StrictMode.VmPolicy.Builder  [API 9+]
- detectCleartextNetwork(): StrictMode.VmPolicy.Builder  [API 23+]
- detectContentUriWithoutPermission(): StrictMode.VmPolicy.Builder  [API 26+]
- detectCredentialProtectedWhileLocked(): StrictMode.VmPolicy.Builder  [API 29+]
- detectFileUriExposure(): StrictMode.VmPolicy.Builder  [API 18+]
- detectImplicitDirectBoot(): StrictMode.VmPolicy.Builder  [API 29+]
- detectIncorrectContextUse(): StrictMode.VmPolicy.Builder  [API 31+]
- detectLeakedClosableObjects(): StrictMode.VmPolicy.Builder  [API 11+]
- detectLeakedRegistrationObjects(): StrictMode.VmPolicy.Builder  [API 16+]
- detectLeakedSqlLiteObjects(): StrictMode.VmPolicy.Builder  [API 9+]
- detectNonSdkApiUsage(): StrictMode.VmPolicy.Builder  [API 28+]
- detectUnsafeIntentLaunch(): StrictMode.VmPolicy.Builder  [API 31+]
- detectUntaggedSockets(): StrictMode.VmPolicy.Builder  [API 26+]
- penaltyDeath(): StrictMode.VmPolicy.Builder  [API 9+]
- penaltyDeathOnCleartextNetwork(): StrictMode.VmPolicy.Builder  [API 23+]
- penaltyDeathOnFileUriExposure(): StrictMode.VmPolicy.Builder  [API 24+]
- penaltyDropBox(): StrictMode.VmPolicy.Builder  [API 9+]
- penaltyListener(Executor, StrictMode.OnVmViolationListener): StrictMode.VmPolicy.Builder  [API 28+]
~ penaltyLog(): StrictMode.VmPolicy.Builder  [API 9+]
- permitNonSdkApiUsage(): StrictMode.VmPolicy.Builder  [API 28+]
- permitUnsafeIntentLaunch(): StrictMode.VmPolicy.Builder  [API 31+]
- setClassInstanceLimit(Class, int): StrictMode.VmPolicy.Builder  [API 11+]
```

## SystemClock — framework; API 1+ (4 +, 0 *, 1 ~, 4 -)

```
- currentGnssTimeClock(): Clock [static]  [API 29+]
- currentNetworkTimeClock(): Clock [static]  [API 33+]
+ currentThreadTimeMillis(): long [static]  [API 1+]
+ elapsedRealtime(): long [static]  [API 1+]
- elapsedRealtimeNanos(): long [static]  [API 17+]
~ setCurrentTimeMillis(long): boolean [static]  [API 1+]
+ sleep(long): void [static]  [API 1+]
+ uptimeMillis(): long [static]  [API 1+]
- uptimeNanos(): long [static]  [API 35+]
```

## TestLooperManager — declaration; API 26+ (0 +, 0 *, 0 ~, 7 -)

```
- execute(Message): void  [API 26+]
- getMessageQueue(): MessageQueue  [API 26+]
- hasMessages(Handler, Object, Runnable): boolean  [API 26+]
- hasMessages(Handler, Object, int): boolean  [API 26+]
- next(): Message  [API 26+]
- recycle(Message): void  [API 26+]
- release(): void  [API 26+]
```

## TokenWatcher — framework; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ TokenWatcher(Handler, String)  [API 1+]
+ acquire(IBinder, String): void  [API 1+]
+ acquired(): void  [API 1+]
+ cleanup(IBinder, boolean): void  [API 1+]
+ dump(): void  [API 1+]
+ dump(PrintWriter): void  [API 16+]
+ isAcquired(): boolean  [API 1+]
+ release(IBinder): void  [API 1+]
+ released(): void  [API 1+]
```

## Trace — framework; API 18+ (1 +, 0 *, 5 ~, 0 -)

```
~ beginAsyncSection(String, int): void [static]  [API 29+]
+ beginSection(String): void [static]  [API 18+]
~ endAsyncSection(String, int): void [static]  [API 29+]
~ endSection(): void [static]  [API 18+]
~ isEnabled(): boolean [static]  [API 29+]
~ setCounter(String, long): void [static]  [API 29+]
```

## TransactionTooLargeException — declaration; API 15+ (2 +, 0 *, 0 ~, 0 -)

```
+ TransactionTooLargeException()  [API 15+]
+ TransactionTooLargeException(String)  [API 23+]
```

## UserHandle — framework; API 17+ (8 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 17+]
+ UserHandle(Parcel)  [API 17+]
~ describeContents(): int  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getUserHandleForUid(int): UserHandle [static]  [API 24+]
+ hashCode(): int  [API 17+]
+ readFromParcel(Parcel): UserHandle [static]  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
+ writeToParcel(UserHandle, Parcel): void [static]  [API 17+]
```

## UserManager — framework; API 17+ (0 +, 0 *, 0 ~, 31 -)

```
- createUserCreationIntent(String, String, String, PersistableBundle): Intent [static]  [API 24+]
- getApplicationRestrictions(String): Bundle  [API 18+]
- getSerialNumberForUser(UserHandle): long  [API 17+]
- getUserCount(): int  [API 17+]
- getUserCreationTime(UserHandle): long  [API 23+]
- getUserForSerialNumber(long): UserHandle  [API 17+]
- getUserName(): String  [API 17+]
- getUserProfiles(): List  [API 21+]
- getUserRestrictions(): Bundle  [API 18+]
- getUserRestrictions(UserHandle): Bundle  [API 18+]
- hasUserRestriction(String): boolean  [API 21+]
- isAdminUser(): boolean  [API 34+]
- isDemoUser(): boolean  [API 25+]
- isHeadlessSystemUserMode(): boolean [static]  [API 31+]
- isManagedProfile(): boolean  [API 30+]
- isProfile(): boolean  [API 33+]
- isQuietModeEnabled(UserHandle): boolean  [API 24+]
- isSystemUser(): boolean  [API 23+]
- isUserAGoat(): boolean  [API 17+]
- isUserForeground(): boolean  [API 31+]
- isUserRunning(UserHandle): boolean  [API 17+]
- isUserRunningOrStopping(UserHandle): boolean  [API 17+]
- isUserUnlocked(): boolean  [API 24+]
- isUserUnlocked(UserHandle): boolean  [API 24+]
- requestQuietModeEnabled(boolean, UserHandle): boolean  [API 28+]
- requestQuietModeEnabled(boolean, UserHandle, int): boolean  [API 30+]
- setRestrictionsChallenge(String): boolean  [API 19+, deprecated in 23]
- setUserRestriction(String, boolean): void  [API 18+, deprecated in 21]
- setUserRestrictions(Bundle): void  [API 18+, deprecated in 21]
- setUserRestrictions(Bundle, UserHandle): void  [API 18+, deprecated in 21]
- supportsMultipleUsers(): boolean [static]  [API 24+]
```

## UserManager.UserOperationException — framework; API 28+ (0 +, 0 *, 0 ~, 1 -)

```
- getUserOperationResult(): int  [API 28+]
```

## VibrationAttributes — declaration; API 30+ (0 +, 8 *, 0 ~, 2 -)

```
+ CREATOR [field]  [API 30+]
- createForUsage(int): VibrationAttributes [static]  [API 33+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getFlags(): int  [API 30+]
* getUsage(): int  [API 30+]
* getUsageClass(): int  [API 30+]
* hashCode(): int  [API 30+]
- isFlagSet(int): boolean  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## VibrationAttributes.Builder — declaration; API 30+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder()  [API 30+]
- Builder(AudioAttributes)  [API 33+]
* Builder(VibrationAttributes)  [API 30+]
* build(): VibrationAttributes  [API 30+]
- setFlags(int, int): VibrationAttributes.Builder  [API 30+]
* setUsage(int): VibrationAttributes.Builder  [API 30+]
```

## VibrationEffect — declaration; API 26+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 26+]
- createOneShot(long, int): VibrationEffect [static]  [API 26+]
- createPredefined(int): VibrationEffect [static]  [API 29+]
- createWaveform(long[], int): VibrationEffect [static]  [API 26+]
- createWaveform(long[], int[], int): VibrationEffect [static]  [API 26+]
- describeContents(): int  [API 26+]
- startComposition(): VibrationEffect.Composition [static]  [API 30+]
```

## VibrationEffect.Composition — declaration; API 30+ (0 +, 0 *, 0 ~, 4 -)

```
- addPrimitive(int): VibrationEffect.Composition  [API 30+]
- addPrimitive(int, float): VibrationEffect.Composition  [API 30+]
- addPrimitive(int, float, int): VibrationEffect.Composition  [API 30+]
- compose(): VibrationEffect  [API 30+]
```

## Vibrator — framework; API 1+ (0 +, 0 *, 4 ~, 14 -)

```
- areAllEffectsSupported(int[]): int  [API 30+]
- areAllPrimitivesSupported(int[]): boolean  [API 30+]
- areEffectsSupported(int[]): int[]  [API 30+]
- arePrimitivesSupported(int[]): boolean[]  [API 30+]
~ cancel(): void  [API 1+]
- getId(): int  [API 31+]
- getPrimitiveDurations(int[]): int[]  [API 31+]
- getQFactor(): float  [API 34+]
- getResonantFrequency(): float  [API 34+]
- hasAmplitudeControl(): boolean  [API 26+]
~ hasVibrator(): boolean  [API 11+]
- vibrate(VibrationEffect): void  [API 26+]
- vibrate(VibrationEffect, AudioAttributes): void  [API 26+, deprecated in 33]
- vibrate(VibrationEffect, VibrationAttributes): void  [API 33+]
~ vibrate(long): void  [API 1+, deprecated in 26]
- vibrate(long, AudioAttributes): void  [API 21+, deprecated in 26]
~ vibrate(long[], int): void  [API 1+, deprecated in 26]
- vibrate(long[], int, AudioAttributes): void  [API 21+, deprecated in 26]
```

## VibratorManager — framework; API 31+ (4 +, 0 *, 0 ~, 2 -)

```
+ cancel(): void  [API 31+]
+ getDefaultVibrator(): Vibrator  [API 31+]
+ getVibrator(int): Vibrator  [API 31+]
+ getVibratorIds(): int[]  [API 31+]
- vibrate(CombinedVibration): void  [API 31+]
- vibrate(CombinedVibration, VibrationAttributes): void  [API 31+]
```

## WorkDuration — declaration; API 35+ (0 +, 11 *, 0 ~, 0 -)

```
* WorkDuration()  [API 35+]
* equals(Object): boolean  [API 35+]
* getActualCpuDurationNanos(): long  [API 35+]
* getActualGpuDurationNanos(): long  [API 35+]
* getActualTotalDurationNanos(): long  [API 35+]
* getWorkPeriodStartTimestampNanos(): long  [API 35+]
* hashCode(): int  [API 35+]
* setActualCpuDurationNanos(long): void  [API 35+]
* setActualGpuDurationNanos(long): void  [API 35+]
* setActualTotalDurationNanos(long): void  [API 35+]
* setWorkPeriodStartTimestampNanos(long): void  [API 35+]
```

## WorkSource — declaration; API 9+ (0 +, 0 *, 0 ~, 12 -)

```
- CREATOR [field]  [API 9+]
- WorkSource()  [API 9+]
- WorkSource(WorkSource)  [API 9+]
- add(WorkSource): boolean  [API 9+]
- clear(): void  [API 9+]
- describeContents(): int  [API 9+]
- diff(WorkSource): boolean  [API 9+]
- equals(Object): boolean  [API 9+]
- hashCode(): int  [API 9+]
- remove(WorkSource): boolean  [API 9+]
- set(WorkSource): void  [API 9+]
- toString(): String  [API 9+]
- writeToParcel(Parcel, int): void  [API 9+]
```


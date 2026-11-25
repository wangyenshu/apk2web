# dalvik.system

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AllocationLimitError — declaration; API 1-8, removed in 9 (2 +, 0 *, 0 ~, 0 -)

```
+ AllocationLimitError()  [API 1-8, removed in 9]
+ AllocationLimitError(String)  [API 1-8, removed in 9]
```

## ApplicationRuntime — declaration; API 34+ (0 +, 0 *, 0 ~, 1 -)

```
- getBaseApkOptimizationInfo(): DexFile.OptimizationInfo [static]  [API 34+]
```

## BaseDexClassLoader — framework; API 14+ (4 +, 0 *, 0 ~, 3 -)

```
+ BaseDexClassLoader(String, File, String, ClassLoader)  [API 14+]
+ findClass(String): Class  [API 14+]
+ findLibrary(String): String  [API 14+]
- findResource(String): URL  [API 14+]
- findResources(String): Enumeration  [API 14+]
- getPackage(String): Package  [API 14+]
+ toString(): String  [API 14+]
```

## DelegateLastClassLoader — declaration; API 27+ (0 +, 0 *, 0 ~, 6 -)

```
- DelegateLastClassLoader(String, ClassLoader)  [API 27+]
- DelegateLastClassLoader(String, String, ClassLoader)  [API 27+]
- DelegateLastClassLoader(String, String, ClassLoader, boolean)  [API 29+]
- getResource(String): URL  [API 27+]
- getResources(String): Enumeration  [API 27+]
- loadClass(String, boolean): Class  [API 27+]
```

## DexClassLoader — framework; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ DexClassLoader(String, String, String, ClassLoader)  [API 3+]
```

## DexFile — framework; API 1+ (8 +, 0 *, 2 ~, 0 -)

```
+ DexFile(File)  [API 1+, deprecated in 26]
+ DexFile(String)  [API 1+, deprecated in 26]
~ close(): void  [API 1+, deprecated in 26]
+ entries(): Enumeration  [API 1+, deprecated in 26]
+ finalize(): void  [API 1+]
+ getName(): String  [API 1+, deprecated in 26]
~ isDexOptNeeded(String): boolean [static]  [API 1+, deprecated in 26]
+ loadClass(String, ClassLoader): Class  [API 1+, deprecated in 26]
+ loadDex(String, String, int): DexFile [static]  [API 3+, deprecated in 26]
+ toString(): String  [API 1+]
```

## DexFile.OptimizationInfo — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- isFullyCompiled(): boolean  [API 34+]
- isOptimized(): boolean  [API 34+]
- isVerified(): boolean  [API 34+]
```

## InMemoryDexClassLoader — framework; API 26+ (2 +, 0 *, 0 ~, 1 -)

```
+ InMemoryDexClassLoader(ByteBuffer, ClassLoader)  [API 26+]
+ InMemoryDexClassLoader(ByteBuffer[], ClassLoader)  [API 27+]
- InMemoryDexClassLoader(ByteBuffer[], String, ClassLoader)  [API 29+]
```

## PathClassLoader — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ PathClassLoader(String, ClassLoader)  [API 1+]
+ PathClassLoader(String, String, ClassLoader)  [API 1+]
```

## PotentialDeadlockError — declaration; API 1-8, removed in 9 (2 +, 0 *, 0 ~, 0 -)

```
+ PotentialDeadlockError()  [API 1-8, removed in 9]
+ PotentialDeadlockError(String)  [API 1-8, removed in 9]
```

## StaleDexCacheError — declaration; API 1-8, removed in 9 (2 +, 0 *, 0 ~, 0 -)

```
+ StaleDexCacheError()  [API 1-8, removed in 9]
+ StaleDexCacheError(String)  [API 1-8, removed in 9]
```

## TemporaryDirectory — declaration; API 1-8, removed in 9 (0 +, 0 *, 0 ~, 3 -)

```
- TemporaryDirectory()  [API 1-8, removed in 9]
- setUpDirectory(File): void  [API 1-8, removed in 9]
- setUpDirectory(String): void  [API 1-8, removed in 9]
```

## TouchDex — declaration; API 1-8, removed in 9 (0 +, 0 *, 0 ~, 3 -)

```
- TouchDex()  [API 1-8, removed in 9]
- main(String[]): void  [API 1-8, removed in 9]
- start(String): int  [API 1-8, removed in 9]
```

## VMDebug — declaration; API 1-8, removed in 9 (0 +, 0 *, 0 ~, 22 -)

```
- dumpHprofData(String): void  [API 3-8, removed in 9]
- getAllocCount(int): int  [API 1-8, removed in 9]
- getInstructionCount(int[]): void  [API 1-8, removed in 9]
- getLoadedClassCount(): int  [API 1-8, removed in 9]
- isDebuggerConnected(): boolean  [API 1-8, removed in 9]
- isDebuggingEnabled(): boolean  [API 3-8, removed in 9]
- lastDebuggerActivity(): long  [API 1-8, removed in 9]
- printLoadedClasses(int): void  [API 1-8, removed in 9]
- resetAllocCount(int): void  [API 1-8, removed in 9]
- resetInstructionCount(): void  [API 1-8, removed in 9]
- setAllocationLimit(int): int  [API 1-8, removed in 9]
- setGlobalAllocationLimit(int): int  [API 1-8, removed in 9]
- startAllocCounting(): void  [API 1-8, removed in 9]
- startEmulatorTracing(): void  [API 1-8, removed in 9]
- startInstructionCounting(): void  [API 1-8, removed in 9]
- startMethodTracing(): void  [API 1-8, removed in 9]
- startMethodTracing(String, int, int): void  [API 1-8, removed in 9]
- stopAllocCounting(): void  [API 1-8, removed in 9]
- stopEmulatorTracing(): void  [API 1-8, removed in 9]
- stopInstructionCounting(): void  [API 1-8, removed in 9]
- stopMethodTracing(): void  [API 1-8, removed in 9]
- threadCpuTimeNanos(): long  [API 1-8, removed in 9]
```

## VMRuntime — framework; API 1-8, removed in 9 (4 +, 0 *, 4 ~, 0 -)

```
+ gcSoftReferences(): void  [API 1-8, removed in 9]
~ getExternalBytesAllocated(): long  [API 1-8, removed in 9]
~ getMinimumHeapSize(): long  [API 1-8, removed in 9]
+ getRuntime(): VMRuntime  [API 1-8, removed in 9]
+ getTargetHeapUtilization(): float  [API 1-8, removed in 9]
~ runFinalizationSync(): void  [API 1-8, removed in 9]
~ setMinimumHeapSize(long): long  [API 1-8, removed in 9]
+ setTargetHeapUtilization(float): float  [API 1-8, removed in 9]
```

## VMStack — declaration; API 1-8, removed in 9 (0 +, 0 *, 0 ~, 5 -)

```
- VMStack()  [API 1-8, removed in 9]
- getCallingClassLoader(): ClassLoader  [API 1-8, removed in 9]
- getCallingClassLoader2(): ClassLoader  [API 1-8, removed in 9]
- getClasses(int, boolean): Class[]  [API 1-8, removed in 9]
- getThreadStackTrace(Thread): StackTraceElement[]  [API 1-8, removed in 9]
```

## ZipPathValidator — declaration; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- clearCallback(): void [static]  [API 34+]
- setCallback(ZipPathValidator.Callback): void [static]  [API 34+]
```

## ZipPathValidator.Callback — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onZipEntryAccess(String): void  [API 34+]
```

## Zygote — declaration; API 1-8, removed in 9 (0 +, 0 *, 0 ~, 5 -)

```
- fork(): int  [API 1-8, removed in 9]
- forkAndSpecialize(int, int, int[], boolean, int[][]): int  [API 1-8, removed in 9]
- forkAndSpecialize(int, int, int[], int, int[][]): int  [API 3-8, removed in 9]
- forkSystemServer(int, int, int[], boolean, int[][]): int  [API 1-8, removed in 9]
- forkSystemServer(int, int, int[], int, int[][]): int  [API 3-8, removed in 9]
```


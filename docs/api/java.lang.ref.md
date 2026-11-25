# java.lang.ref

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Cleaner — TeaVM class library; API 33+ (2 +, 0 *, 0 ~, 1 -)

```
+ create(): Cleaner [static]  [API 33+]
- create(ThreadFactory): Cleaner [static]  [API 33+]
+ register(Object, Runnable): Cleaner.Cleanable  [API 33+]
```

## Cleaner.Cleanable — TeaVM class library; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ clean(): void  [API 33+]
```

## PhantomReference — missing; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- PhantomReference(Object, ReferenceQueue)  [API 1+]
- get(): Object  [API 1+]
```

## Reference — TeaVM class library; API 1+ (5 +, 0 *, 0 ~, 2 -)

```
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ enqueue(): boolean  [API 1+]
+ get(): Object  [API 1+]
+ isEnqueued(): boolean  [API 1+, deprecated in 31]
- reachabilityFence(Object): void [static]  [API 28+]
- refersTo(Object): boolean  [API 33+]
```

## ReferenceQueue — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ ReferenceQueue()  [API 1+]
+ poll(): Reference  [API 1+]
+ remove(): Reference  [API 1+]
+ remove(long): Reference  [API 1+]
```

## SoftReference — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ SoftReference(Object)  [API 1+]
+ SoftReference(Object, ReferenceQueue)  [API 1+]
+ get(): Object  [API 1+]
```

## WeakReference — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ WeakReference(Object)  [API 1+]
+ WeakReference(Object, ReferenceQueue)  [API 1+]
```


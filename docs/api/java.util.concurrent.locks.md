# java.util.concurrent.locks

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractOwnableSynchronizer — framework (a2w.jdk.util.concurrent.locks.AbstractOwnableSynchronizer); API 5+ (3 +, 0 *, 0 ~, 0 -)

```
+ AbstractOwnableSynchronizer()  [API 5+]
+ getExclusiveOwnerThread(): Thread  [API 5+]
+ setExclusiveOwnerThread(Thread): void  [API 5+]
```

## AbstractQueuedLongSynchronizer — framework (a2w.jdk.util.concurrent.locks.AbstractQueuedLongSynchronizer); API 9+ (26 +, 0 *, 5 ~, 0 -)

```
+ AbstractQueuedLongSynchronizer()  [API 9+]
+ acquire(long): void  [API 9+]
+ acquireInterruptibly(long): void  [API 9+]
+ acquireShared(long): void  [API 9+]
+ acquireSharedInterruptibly(long): void  [API 9+]
+ compareAndSetState(long, long): boolean  [API 9+]
+ getExclusiveQueuedThreads(): Collection  [API 9+]
+ getFirstQueuedThread(): Thread  [API 9+]
+ getQueueLength(): int  [API 9+]
+ getQueuedThreads(): Collection  [API 9+]
+ getSharedQueuedThreads(): Collection  [API 9+]
+ getState(): long  [API 9+]
+ getWaitQueueLength(AbstractQueuedLongSynchronizer.ConditionObject): int  [API 9+]
+ getWaitingThreads(AbstractQueuedLongSynchronizer.ConditionObject): Collection  [API 9+]
+ hasContended(): boolean  [API 9+]
+ hasQueuedPredecessors(): boolean  [API 21+]
+ hasQueuedThreads(): boolean  [API 9+]
+ hasWaiters(AbstractQueuedLongSynchronizer.ConditionObject): boolean  [API 9+]
~ isHeldExclusively(): boolean  [API 9+]
+ isQueued(Thread): boolean  [API 9+]
+ owns(AbstractQueuedLongSynchronizer.ConditionObject): boolean  [API 9+]
+ release(long): boolean  [API 9+]
+ releaseShared(long): boolean  [API 9+]
+ setState(long): void  [API 9+]
+ toString(): String  [API 9+]
~ tryAcquire(long): boolean  [API 9+]
+ tryAcquireNanos(long, long): boolean  [API 9+]
~ tryAcquireShared(long): long  [API 9+]
+ tryAcquireSharedNanos(long, long): boolean  [API 9+]
~ tryRelease(long): boolean  [API 9+]
~ tryReleaseShared(long): boolean  [API 9+]
```

## AbstractQueuedLongSynchronizer.ConditionObject — framework (a2w.jdk.util.concurrent.locks.AbstractQueuedLongSynchronizer$ConditionObject); API 9+ (11 +, 0 *, 0 ~, 0 -)

```
+ ConditionObject(AbstractQueuedLongSynchronizer)  [API 9+]
+ await(): void  [API 9+]
+ await(long, TimeUnit): boolean  [API 9+]
+ awaitNanos(long): long  [API 9+]
+ awaitUninterruptibly(): void  [API 9+]
+ awaitUntil(Date): boolean  [API 9+]
+ getWaitQueueLength(): int  [API 9+]
+ getWaitingThreads(): Collection  [API 9+]
+ hasWaiters(): boolean  [API 9+]
+ signal(): void  [API 9+]
+ signalAll(): void  [API 9+]
```

## AbstractQueuedSynchronizer — framework (a2w.jdk.util.concurrent.locks.AbstractQueuedSynchronizer); API 1+ (26 +, 0 *, 5 ~, 0 -)

```
+ AbstractQueuedSynchronizer()  [API 1+]
+ acquire(int): void  [API 1+]
+ acquireInterruptibly(int): void  [API 1+]
+ acquireShared(int): void  [API 1+]
+ acquireSharedInterruptibly(int): void  [API 1+]
+ compareAndSetState(int, int): boolean  [API 1+]
+ getExclusiveQueuedThreads(): Collection  [API 1+]
+ getFirstQueuedThread(): Thread  [API 1+]
+ getQueueLength(): int  [API 1+]
+ getQueuedThreads(): Collection  [API 1+]
+ getSharedQueuedThreads(): Collection  [API 1+]
+ getState(): int  [API 1+]
+ getWaitQueueLength(AbstractQueuedSynchronizer.ConditionObject): int  [API 1+]
+ getWaitingThreads(AbstractQueuedSynchronizer.ConditionObject): Collection  [API 1+]
+ hasContended(): boolean  [API 1+]
+ hasQueuedPredecessors(): boolean  [API 21+]
+ hasQueuedThreads(): boolean  [API 1+]
+ hasWaiters(AbstractQueuedSynchronizer.ConditionObject): boolean  [API 1+]
~ isHeldExclusively(): boolean  [API 1+]
+ isQueued(Thread): boolean  [API 1+]
+ owns(AbstractQueuedSynchronizer.ConditionObject): boolean  [API 1+]
+ release(int): boolean  [API 1+]
+ releaseShared(int): boolean  [API 1+]
+ setState(int): void  [API 1+]
+ toString(): String  [API 1+]
~ tryAcquire(int): boolean  [API 1+]
+ tryAcquireNanos(int, long): boolean  [API 1+]
~ tryAcquireShared(int): int  [API 1+]
+ tryAcquireSharedNanos(int, long): boolean  [API 1+]
~ tryRelease(int): boolean  [API 1+]
~ tryReleaseShared(int): boolean  [API 1+]
```

## AbstractQueuedSynchronizer.ConditionObject — framework (a2w.jdk.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject); API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ ConditionObject(AbstractQueuedSynchronizer)  [API 1+]
+ await(): void  [API 1+]
+ await(long, TimeUnit): boolean  [API 1+]
+ awaitNanos(long): long  [API 1+]
+ awaitUninterruptibly(): void  [API 1+]
+ awaitUntil(Date): boolean  [API 1+]
+ getWaitQueueLength(): int  [API 1+]
+ getWaitingThreads(): Collection  [API 1+]
+ hasWaiters(): boolean  [API 1+]
+ signal(): void  [API 1+]
+ signalAll(): void  [API 1+]
```

## Condition — framework (a2w.jdk.util.concurrent.locks.Condition); API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ await(): void  [API 1+]
+ await(long, TimeUnit): boolean  [API 1+]
+ awaitNanos(long): long  [API 1+]
+ awaitUninterruptibly(): void  [API 1+]
+ awaitUntil(Date): boolean  [API 1+]
+ signal(): void  [API 1+]
+ signalAll(): void  [API 1+]
```

## Lock — framework (a2w.jdk.util.concurrent.locks.Lock); API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ lock(): void  [API 1+]
+ lockInterruptibly(): void  [API 1+]
+ newCondition(): Condition  [API 1+]
+ tryLock(): boolean  [API 1+]
+ tryLock(long, TimeUnit): boolean  [API 1+]
+ unlock(): void  [API 1+]
```

## LockSupport — framework (a2w.jdk.util.concurrent.locks.LockSupport); API 1+ (8 +, 0 *, 0 ~, 1 -)

```
+ getBlocker(Thread): Object [static]  [API 9+]
+ park(): void [static]  [API 1+]
+ park(Object): void [static]  [API 9+]
+ parkNanos(Object, long): void [static]  [API 9+]
+ parkNanos(long): void [static]  [API 1+]
+ parkUntil(Object, long): void [static]  [API 9+]
+ parkUntil(long): void [static]  [API 1+]
- setCurrentBlocker(Object): void [static]  [API 34+]
+ unpark(Thread): void [static]  [API 1+]
```

## ReadWriteLock — framework (a2w.jdk.util.concurrent.locks.ReadWriteLock); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ readLock(): Lock  [API 1+]
+ writeLock(): Lock  [API 1+]
```

## ReentrantLock — framework (a2w.jdk.util.concurrent.locks.ReentrantLock); API 1+ (21 +, 0 *, 0 ~, 0 -)

```
+ ReentrantLock()  [API 1+]
+ ReentrantLock(boolean)  [API 1+]
+ getHoldCount(): int  [API 1+]
+ getOwner(): Thread  [API 1+]
+ getQueueLength(): int  [API 1+]
+ getQueuedThreads(): Collection  [API 1+]
+ getWaitQueueLength(Condition): int  [API 1+]
+ getWaitingThreads(Condition): Collection  [API 1+]
+ hasQueuedThread(Thread): boolean  [API 1+]
+ hasQueuedThreads(): boolean  [API 1+]
+ hasWaiters(Condition): boolean  [API 1+]
+ isFair(): boolean  [API 1+]
+ isHeldByCurrentThread(): boolean  [API 1+]
+ isLocked(): boolean  [API 1+]
+ lock(): void  [API 1+]
+ lockInterruptibly(): void  [API 1+]
+ newCondition(): Condition  [API 1+]
+ toString(): String  [API 1+]
+ tryLock(): boolean  [API 1+]
+ tryLock(long, TimeUnit): boolean  [API 1+]
+ unlock(): void  [API 1+]
```

## ReentrantReadWriteLock — framework (a2w.jdk.util.concurrent.locks.ReentrantReadWriteLock); API 1+ (21 +, 0 *, 0 ~, 0 -)

```
+ ReentrantReadWriteLock()  [API 1+]
+ ReentrantReadWriteLock(boolean)  [API 1+]
+ getOwner(): Thread  [API 1+]
+ getQueueLength(): int  [API 1+]
+ getQueuedReaderThreads(): Collection  [API 1+]
+ getQueuedThreads(): Collection  [API 1+]
+ getQueuedWriterThreads(): Collection  [API 1+]
+ getReadHoldCount(): int  [API 9+]
+ getReadLockCount(): int  [API 1+]
+ getWaitQueueLength(Condition): int  [API 1+]
+ getWaitingThreads(Condition): Collection  [API 1+]
+ getWriteHoldCount(): int  [API 1+]
+ hasQueuedThread(Thread): boolean  [API 1+]
+ hasQueuedThreads(): boolean  [API 1+]
+ hasWaiters(Condition): boolean  [API 1+]
+ isFair(): boolean  [API 1+]
+ isWriteLocked(): boolean  [API 1+]
+ isWriteLockedByCurrentThread(): boolean  [API 1+]
+ readLock(): ReentrantReadWriteLock.ReadLock  [API 1+]
+ toString(): String  [API 1+]
+ writeLock(): ReentrantReadWriteLock.WriteLock  [API 1+]
```

## ReentrantReadWriteLock.ReadLock — framework (a2w.jdk.util.concurrent.locks.ReentrantReadWriteLock$ReadLock); API 1+ (7 +, 0 *, 1 ~, 0 -)

```
+ ReadLock(ReentrantReadWriteLock)  [API 1+]
+ lock(): void  [API 1+]
+ lockInterruptibly(): void  [API 1+]
~ newCondition(): Condition  [API 1+]
+ toString(): String  [API 1+]
+ tryLock(): boolean  [API 1+]
+ tryLock(long, TimeUnit): boolean  [API 1+]
+ unlock(): void  [API 1+]
```

## ReentrantReadWriteLock.WriteLock — framework (a2w.jdk.util.concurrent.locks.ReentrantReadWriteLock$WriteLock); API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ WriteLock(ReentrantReadWriteLock)  [API 1+]
+ getHoldCount(): int  [API 9+]
+ isHeldByCurrentThread(): boolean  [API 9+]
+ lock(): void  [API 1+]
+ lockInterruptibly(): void  [API 1+]
+ newCondition(): Condition  [API 1+]
+ toString(): String  [API 1+]
+ tryLock(): boolean  [API 1+]
+ tryLock(long, TimeUnit): boolean  [API 1+]
+ unlock(): void  [API 1+]
```

## StampedLock — framework (a2w.jdk.util.concurrent.locks.StampedLock); API 24+ (30 +, 0 *, 0 ~, 0 -)

```
+ StampedLock()  [API 24+]
+ asReadLock(): Lock  [API 24+]
+ asReadWriteLock(): ReadWriteLock  [API 24+]
+ asWriteLock(): Lock  [API 24+]
+ getReadLockCount(): int  [API 24+]
+ isLockStamp(long): boolean [static]  [API 33+]
+ isOptimisticReadStamp(long): boolean [static]  [API 33+]
+ isReadLockStamp(long): boolean [static]  [API 33+]
+ isReadLocked(): boolean  [API 24+]
+ isWriteLockStamp(long): boolean [static]  [API 33+]
+ isWriteLocked(): boolean  [API 24+]
+ readLock(): long  [API 24+]
+ readLockInterruptibly(): long  [API 24+]
+ toString(): String  [API 24+]
+ tryConvertToOptimisticRead(long): long  [API 24+]
+ tryConvertToReadLock(long): long  [API 24+]
+ tryConvertToWriteLock(long): long  [API 24+]
+ tryOptimisticRead(): long  [API 24+]
+ tryReadLock(): long  [API 24+]
+ tryReadLock(long, TimeUnit): long  [API 24+]
+ tryUnlockRead(): boolean  [API 24+]
+ tryUnlockWrite(): boolean  [API 24+]
+ tryWriteLock(): long  [API 24+]
+ tryWriteLock(long, TimeUnit): long  [API 24+]
+ unlock(long): void  [API 24+]
+ unlockRead(long): void  [API 24+]
+ unlockWrite(long): void  [API 24+]
+ validate(long): boolean  [API 24+]
+ writeLock(): long  [API 24+]
+ writeLockInterruptibly(): long  [API 24+]
```


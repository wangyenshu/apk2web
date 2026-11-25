# java.util.concurrent

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractExecutorService — framework (a2w.jdk.util.concurrent.AbstractExecutorService); API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ AbstractExecutorService()  [API 1+]
+ invokeAll(Collection): List  [API 1+]
+ invokeAll(Collection, long, TimeUnit): List  [API 1+]
+ invokeAny(Collection): Object  [API 1+]
+ invokeAny(Collection, long, TimeUnit): Object  [API 1+]
+ newTaskFor(Callable): RunnableFuture  [API 9+]
+ newTaskFor(Runnable, Object): RunnableFuture  [API 9+]
+ submit(Callable): Future  [API 1+]
+ submit(Runnable): Future  [API 1+]
+ submit(Runnable, Object): Future  [API 1+]
```

## ArrayBlockingQueue — TeaVM class library; API 1+ (27 +, 0 *, 0 ~, 0 -)

```
+ ArrayBlockingQueue(int)  [API 1+]
+ ArrayBlockingQueue(int, boolean)  [API 1+]
+ ArrayBlockingQueue(int, boolean, Collection)  [API 1+]
+ add(Object): boolean  [API 1+]
+ clear(): void  [API 1+]
+ contains(Object): boolean  [API 1+]
+ drainTo(Collection): int  [API 1+]
+ drainTo(Collection, int): int  [API 1+]
+ forEach(Consumer): void  [API 1+]
+ iterator(): Iterator  [API 1+]
+ offer(Object): boolean  [API 1+]
+ offer(Object, long, TimeUnit): boolean  [API 1+]
+ peek(): Object  [API 1+]
+ poll(): Object  [API 1+]
+ poll(long, TimeUnit): Object  [API 1+]
+ put(Object): void  [API 1+]
+ remainingCapacity(): int  [API 1+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeIf(Predicate): boolean  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ take(): Object  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
+ toString(): String  [API 1+]
```

## BlockingDeque — declaration; API 9+ (29 +, 0 *, 0 ~, 0 -)

```
+ add(Object): boolean  [API 9+]
+ addFirst(Object): void  [API 9+]
+ addLast(Object): void  [API 9+]
+ contains(Object): boolean  [API 9+]
+ element(): Object  [API 9+]
+ iterator(): Iterator  [API 9+]
+ offer(Object): boolean  [API 9+]
+ offer(Object, long, TimeUnit): boolean  [API 9+]
+ offerFirst(Object): boolean  [API 9+]
+ offerFirst(Object, long, TimeUnit): boolean  [API 9+]
+ offerLast(Object): boolean  [API 9+]
+ offerLast(Object, long, TimeUnit): boolean  [API 9+]
+ peek(): Object  [API 9+]
+ poll(): Object  [API 9+]
+ poll(long, TimeUnit): Object  [API 9+]
+ pollFirst(long, TimeUnit): Object  [API 9+]
+ pollLast(long, TimeUnit): Object  [API 9+]
+ push(Object): void  [API 9+]
+ put(Object): void  [API 9+]
+ putFirst(Object): void  [API 9+]
+ putLast(Object): void  [API 9+]
+ remove(): Object  [API 9+]
+ remove(Object): boolean  [API 9+]
+ removeFirstOccurrence(Object): boolean  [API 9+]
+ removeLastOccurrence(Object): boolean  [API 9+]
+ size(): int  [API 9+]
+ take(): Object  [API 9+]
+ takeFirst(): Object  [API 9+]
+ takeLast(): Object  [API 9+]
```

## BlockingQueue — TeaVM class library; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ add(Object): boolean  [API 1+]
+ contains(Object): boolean  [API 1+]
+ drainTo(Collection): int  [API 1+]
+ drainTo(Collection, int): int  [API 1+]
+ offer(Object): boolean  [API 1+]
+ offer(Object, long, TimeUnit): boolean  [API 1+]
+ poll(long, TimeUnit): Object  [API 1+]
+ put(Object): void  [API 1+]
+ remainingCapacity(): int  [API 1+]
+ remove(Object): boolean  [API 1+]
+ take(): Object  [API 1+]
```

## BrokenBarrierException — framework (a2w.jdk.util.concurrent.BrokenBarrierException); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BrokenBarrierException()  [API 1+]
+ BrokenBarrierException(String)  [API 1+]
```

## Callable — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ call(): Object  [API 1+]
```

## CancellationException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CancellationException()  [API 1+]
+ CancellationException(String)  [API 1+]
```

## CompletableFuture — framework (a2w.jdk.util.concurrent.CompletableFuture); API 24+ (77 +, 0 *, 1 ~, 0 -)

```
+ CompletableFuture()  [API 24+]
+ acceptEither(CompletionStage, Consumer): CompletableFuture  [API 24+]
+ acceptEitherAsync(CompletionStage, Consumer): CompletableFuture  [API 24+]
+ acceptEitherAsync(CompletionStage, Consumer, Executor): CompletableFuture  [API 24+]
+ allOf(CompletableFuture[]): CompletableFuture [static]  [API 24+]
+ anyOf(CompletableFuture[]): CompletableFuture [static]  [API 24+]
+ applyToEither(CompletionStage, Function): CompletableFuture  [API 24+]
+ applyToEitherAsync(CompletionStage, Function): CompletableFuture  [API 24+]
+ applyToEitherAsync(CompletionStage, Function, Executor): CompletableFuture  [API 24+]
+ cancel(boolean): boolean  [API 24+]
+ complete(Object): boolean  [API 24+]
+ completeAsync(Supplier): CompletableFuture  [API 31+]
+ completeAsync(Supplier, Executor): CompletableFuture  [API 31+]
+ completeExceptionally(Throwable): boolean  [API 24+]
+ completeOnTimeout(Object, long, TimeUnit): CompletableFuture  [API 31+]
+ completedFuture(Object): CompletableFuture [static]  [API 24+]
+ completedStage(Object): CompletionStage [static]  [API 31+]
+ copy(): CompletableFuture  [API 31+]
+ defaultExecutor(): Executor  [API 31+]
+ delayedExecutor(long, TimeUnit): Executor [static]  [API 31+]
+ delayedExecutor(long, TimeUnit, Executor): Executor [static]  [API 31+]
+ exceptionally(Function): CompletableFuture  [API 24+]
+ exceptionallyAsync(Function): CompletableFuture  [API 34+]
+ exceptionallyAsync(Function, Executor): CompletableFuture  [API 34+]
+ exceptionallyCompose(Function): CompletableFuture  [API 34+]
+ exceptionallyComposeAsync(Function): CompletableFuture  [API 34+]
+ exceptionallyComposeAsync(Function, Executor): CompletableFuture  [API 34+]
+ failedFuture(Throwable): CompletableFuture [static]  [API 31+]
+ failedStage(Throwable): CompletionStage [static]  [API 31+]
+ get(): Object  [API 24+]
+ get(long, TimeUnit): Object  [API 24+]
+ getNow(Object): Object  [API 24+]
+ getNumberOfDependents(): int  [API 24+]
+ handle(BiFunction): CompletableFuture  [API 24+]
+ handleAsync(BiFunction): CompletableFuture  [API 24+]
+ handleAsync(BiFunction, Executor): CompletableFuture  [API 24+]
+ isCancelled(): boolean  [API 24+]
+ isCompletedExceptionally(): boolean  [API 24+]
+ isDone(): boolean  [API 24+]
+ join(): Object  [API 24+]
+ minimalCompletionStage(): CompletionStage  [API 31+]
+ newIncompleteFuture(): CompletableFuture  [API 31+]
+ obtrudeException(Throwable): void  [API 24+]
+ obtrudeValue(Object): void  [API 24+]
+ orTimeout(long, TimeUnit): CompletableFuture  [API 31+]
+ runAfterBoth(CompletionStage, Runnable): CompletableFuture  [API 24+]
+ runAfterBothAsync(CompletionStage, Runnable): CompletableFuture  [API 24+]
+ runAfterBothAsync(CompletionStage, Runnable, Executor): CompletableFuture  [API 24+]
+ runAfterEither(CompletionStage, Runnable): CompletableFuture  [API 24+]
+ runAfterEitherAsync(CompletionStage, Runnable): CompletableFuture  [API 24+]
+ runAfterEitherAsync(CompletionStage, Runnable, Executor): CompletableFuture  [API 24+]
+ runAsync(Runnable): CompletableFuture [static]  [API 24+]
+ runAsync(Runnable, Executor): CompletableFuture [static]  [API 24+]
+ supplyAsync(Supplier): CompletableFuture [static]  [API 24+]
+ supplyAsync(Supplier, Executor): CompletableFuture [static]  [API 24+]
+ thenAccept(Consumer): CompletableFuture  [API 24+]
+ thenAcceptAsync(Consumer): CompletableFuture  [API 24+]
+ thenAcceptAsync(Consumer, Executor): CompletableFuture  [API 24+]
+ thenAcceptBoth(CompletionStage, BiConsumer): CompletableFuture  [API 24+]
+ thenAcceptBothAsync(CompletionStage, BiConsumer): CompletableFuture  [API 24+]
+ thenAcceptBothAsync(CompletionStage, BiConsumer, Executor): CompletableFuture  [API 24+]
+ thenApply(Function): CompletableFuture  [API 24+]
+ thenApplyAsync(Function): CompletableFuture  [API 24+]
+ thenApplyAsync(Function, Executor): CompletableFuture  [API 24+]
+ thenCombine(CompletionStage, BiFunction): CompletableFuture  [API 24+]
+ thenCombineAsync(CompletionStage, BiFunction): CompletableFuture  [API 24+]
+ thenCombineAsync(CompletionStage, BiFunction, Executor): CompletableFuture  [API 24+]
+ thenCompose(Function): CompletableFuture  [API 24+]
+ thenComposeAsync(Function): CompletableFuture  [API 24+]
+ thenComposeAsync(Function, Executor): CompletableFuture  [API 24+]
+ thenRun(Runnable): CompletableFuture  [API 24+]
+ thenRunAsync(Runnable): CompletableFuture  [API 24+]
+ thenRunAsync(Runnable, Executor): CompletableFuture  [API 24+]
~ toCompletableFuture(): CompletableFuture  [API 24+]
+ toString(): String  [API 24+]
+ whenComplete(BiConsumer): CompletableFuture  [API 24+]
+ whenCompleteAsync(BiConsumer): CompletableFuture  [API 24+]
+ whenCompleteAsync(BiConsumer, Executor): CompletableFuture  [API 24+]
```

## CompletableFuture.AsynchronousCompletionTask — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## CompletionException — declaration; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ CompletionException()  [API 24+]
+ CompletionException(String)  [API 24+]
+ CompletionException(String, Throwable)  [API 24+]
+ CompletionException(Throwable)  [API 24+]
```

## CompletionService — framework (a2w.jdk.util.concurrent.CompletionService); API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ poll(): Future  [API 1+]
+ poll(long, TimeUnit): Future  [API 1+]
+ submit(Callable): Future  [API 1+]
+ submit(Runnable, Object): Future  [API 1+]
+ take(): Future  [API 1+]
```

## CompletionStage — framework (a2w.jdk.util.concurrent.CompletionStage); API 24+ (43 +, 0 *, 0 ~, 0 -)

```
+ acceptEither(CompletionStage, Consumer): CompletionStage  [API 24+]
+ acceptEitherAsync(CompletionStage, Consumer): CompletionStage  [API 24+]
+ acceptEitherAsync(CompletionStage, Consumer, Executor): CompletionStage  [API 24+]
+ applyToEither(CompletionStage, Function): CompletionStage  [API 24+]
+ applyToEitherAsync(CompletionStage, Function): CompletionStage  [API 24+]
+ applyToEitherAsync(CompletionStage, Function, Executor): CompletionStage  [API 24+]
+ exceptionally(Function): CompletionStage  [API 24+]
+ exceptionallyAsync(Function): CompletionStage  [API 34+]
+ exceptionallyAsync(Function, Executor): CompletionStage  [API 34+]
+ exceptionallyCompose(Function): CompletionStage  [API 34+]
+ exceptionallyComposeAsync(Function): CompletionStage  [API 34+]
+ exceptionallyComposeAsync(Function, Executor): CompletionStage  [API 34+]
+ handle(BiFunction): CompletionStage  [API 24+]
+ handleAsync(BiFunction): CompletionStage  [API 24+]
+ handleAsync(BiFunction, Executor): CompletionStage  [API 24+]
+ runAfterBoth(CompletionStage, Runnable): CompletionStage  [API 24+]
+ runAfterBothAsync(CompletionStage, Runnable): CompletionStage  [API 24+]
+ runAfterBothAsync(CompletionStage, Runnable, Executor): CompletionStage  [API 24+]
+ runAfterEither(CompletionStage, Runnable): CompletionStage  [API 24+]
+ runAfterEitherAsync(CompletionStage, Runnable): CompletionStage  [API 24+]
+ runAfterEitherAsync(CompletionStage, Runnable, Executor): CompletionStage  [API 24+]
+ thenAccept(Consumer): CompletionStage  [API 24+]
+ thenAcceptAsync(Consumer): CompletionStage  [API 24+]
+ thenAcceptAsync(Consumer, Executor): CompletionStage  [API 24+]
+ thenAcceptBoth(CompletionStage, BiConsumer): CompletionStage  [API 24+]
+ thenAcceptBothAsync(CompletionStage, BiConsumer): CompletionStage  [API 24+]
+ thenAcceptBothAsync(CompletionStage, BiConsumer, Executor): CompletionStage  [API 24+]
+ thenApply(Function): CompletionStage  [API 24+]
+ thenApplyAsync(Function): CompletionStage  [API 24+]
+ thenApplyAsync(Function, Executor): CompletionStage  [API 24+]
+ thenCombine(CompletionStage, BiFunction): CompletionStage  [API 24+]
+ thenCombineAsync(CompletionStage, BiFunction): CompletionStage  [API 24+]
+ thenCombineAsync(CompletionStage, BiFunction, Executor): CompletionStage  [API 24+]
+ thenCompose(Function): CompletionStage  [API 24+]
+ thenComposeAsync(Function): CompletionStage  [API 24+]
+ thenComposeAsync(Function, Executor): CompletionStage  [API 24+]
+ thenRun(Runnable): CompletionStage  [API 24+]
+ thenRunAsync(Runnable): CompletionStage  [API 24+]
+ thenRunAsync(Runnable, Executor): CompletionStage  [API 24+]
+ toCompletableFuture(): CompletableFuture  [API 24+]
+ whenComplete(BiConsumer): CompletionStage  [API 24+]
+ whenCompleteAsync(BiConsumer): CompletionStage  [API 24+]
+ whenCompleteAsync(BiConsumer, Executor): CompletionStage  [API 24+]
```

## ConcurrentHashMap — TeaVM class library; API 1+ (69 +, 0 *, 0 ~, 0 -)

```
+ ConcurrentHashMap()  [API 1+]
+ ConcurrentHashMap(Map)  [API 1+]
+ ConcurrentHashMap(int)  [API 1+]
+ ConcurrentHashMap(int, float)  [API 9+]
+ ConcurrentHashMap(int, float, int)  [API 1+]
+ clear(): void  [API 1+]
+ compute(Object, BiFunction): Object  [API 1+]
+ computeIfAbsent(Object, Function): Object  [API 1+]
+ computeIfPresent(Object, BiFunction): Object  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ elements(): Enumeration  [API 1+]
+ entrySet(): Set  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forEach(BiConsumer): void  [API 1+]
+ forEach(long, BiConsumer): void  [API 24+]
+ forEach(long, BiFunction, Consumer): void  [API 24+]
+ forEachEntry(long, Consumer): void  [API 24+]
+ forEachEntry(long, Function, Consumer): void  [API 24+]
+ forEachKey(long, Consumer): void  [API 24+]
+ forEachKey(long, Function, Consumer): void  [API 24+]
+ forEachValue(long, Consumer): void  [API 24+]
+ forEachValue(long, Function, Consumer): void  [API 24+]
+ get(Object): Object  [API 1+]
+ getOrDefault(Object, Object): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keySet(): Set  [API 1+]
+ keySet(Object): ConcurrentHashMap.KeySetView  [API 24+]
+ keys(): Enumeration  [API 1+]
+ mappingCount(): long  [API 24+]
+ merge(Object, Object, BiFunction): Object  [API 1+]
+ newKeySet(): ConcurrentHashMap.KeySetView [static]  [API 24+]
+ newKeySet(int): ConcurrentHashMap.KeySetView [static]  [API 24+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ putIfAbsent(Object, Object): Object  [API 1+]
+ reduce(long, BiFunction, BiFunction): Object  [API 24+]
+ reduceEntries(long, BiFunction): Map.Entry  [API 24+]
+ reduceEntries(long, Function, BiFunction): Object  [API 24+]
+ reduceEntriesToDouble(long, ToDoubleFunction, double, DoubleBinaryOperator): double  [API 24+]
+ reduceEntriesToInt(long, ToIntFunction, int, IntBinaryOperator): int  [API 24+]
+ reduceEntriesToLong(long, ToLongFunction, long, LongBinaryOperator): long  [API 24+]
+ reduceKeys(long, BiFunction): Object  [API 24+]
+ reduceKeys(long, Function, BiFunction): Object  [API 24+]
+ reduceKeysToDouble(long, ToDoubleFunction, double, DoubleBinaryOperator): double  [API 24+]
+ reduceKeysToInt(long, ToIntFunction, int, IntBinaryOperator): int  [API 24+]
+ reduceKeysToLong(long, ToLongFunction, long, LongBinaryOperator): long  [API 24+]
+ reduceToDouble(long, ToDoubleBiFunction, double, DoubleBinaryOperator): double  [API 24+]
+ reduceToInt(long, ToIntBiFunction, int, IntBinaryOperator): int  [API 24+]
+ reduceToLong(long, ToLongBiFunction, long, LongBinaryOperator): long  [API 24+]
+ reduceValues(long, BiFunction): Object  [API 24+]
+ reduceValues(long, Function, BiFunction): Object  [API 24+]
+ reduceValuesToDouble(long, ToDoubleFunction, double, DoubleBinaryOperator): double  [API 24+]
+ reduceValuesToInt(long, ToIntFunction, int, IntBinaryOperator): int  [API 24+]
+ reduceValuesToLong(long, ToLongFunction, long, LongBinaryOperator): long  [API 24+]
+ remove(Object): Object  [API 1+]
+ remove(Object, Object): boolean  [API 1+]
+ replace(Object, Object): Object  [API 1+]
+ replace(Object, Object, Object): boolean  [API 1+]
+ replaceAll(BiFunction): void  [API 1+]
+ search(long, BiFunction): Object  [API 24+]
+ searchEntries(long, Function): Object  [API 24+]
+ searchKeys(long, Function): Object  [API 24+]
+ searchValues(long, Function): Object  [API 24+]
+ size(): int  [API 1+]
+ toString(): String  [API 1+]
+ values(): Collection  [API 1+]
```

## ConcurrentHashMap.KeySetView — framework (a2w.jdk.util.concurrent.ConcurrentHashMap$KeySetView); API 24+ (20 +, 0 *, 0 ~, 0 -)

```
+ add(Object): boolean  [API 24+]
+ addAll(Collection): boolean  [API 24+]
+ clear(): void  [API 24+]
+ contains(Object): boolean  [API 24+]
+ containsAll(Collection): boolean  [API 24+]
+ equals(Object): boolean  [API 24+]
+ forEach(Consumer): void  [API 24+]
+ getMap(): ConcurrentHashMap  [API 24+]
+ getMappedValue(): Object  [API 24+]
+ hashCode(): int  [API 24+]
+ isEmpty(): boolean  [API 24+]
+ iterator(): Iterator  [API 24+]
+ remove(Object): boolean  [API 24+]
+ removeAll(Collection): boolean  [API 24+]
+ retainAll(Collection): boolean  [API 24+]
+ size(): int  [API 24+]
+ spliterator(): Spliterator  [API 24+]
+ toArray(): Object[]  [API 24+]
+ toArray(Object[]): Object[]  [API 24+]
+ toString(): String  [API 24+]
```

## ConcurrentLinkedDeque — framework (a2w.jdk.util.concurrent.ConcurrentLinkedDeque); API 21+ (40 +, 0 *, 0 ~, 0 -)

```
+ ConcurrentLinkedDeque()  [API 21+]
+ ConcurrentLinkedDeque(Collection)  [API 21+]
+ add(Object): boolean  [API 21+]
+ addAll(Collection): boolean  [API 21+]
+ addFirst(Object): void  [API 21+]
+ addLast(Object): void  [API 21+]
+ clear(): void  [API 21+]
+ contains(Object): boolean  [API 21+]
+ descendingIterator(): Iterator  [API 21+]
+ element(): Object  [API 21+]
+ forEach(Consumer): void  [API 21+]
+ getFirst(): Object  [API 21+]
+ getLast(): Object  [API 21+]
+ isEmpty(): boolean  [API 21+]
+ iterator(): Iterator  [API 21+]
+ offer(Object): boolean  [API 21+]
+ offerFirst(Object): boolean  [API 21+]
+ offerLast(Object): boolean  [API 21+]
+ peek(): Object  [API 21+]
+ peekFirst(): Object  [API 21+]
+ peekLast(): Object  [API 21+]
+ poll(): Object  [API 21+]
+ pollFirst(): Object  [API 21+]
+ pollLast(): Object  [API 21+]
+ pop(): Object  [API 21+]
+ push(Object): void  [API 21+]
+ remove(): Object  [API 21+]
+ remove(Object): boolean  [API 21+]
+ removeAll(Collection): boolean  [API 21+]
+ removeFirst(): Object  [API 21+]
+ removeFirstOccurrence(Object): boolean  [API 21+]
+ removeIf(Predicate): boolean  [API 21+]
+ removeLast(): Object  [API 21+]
+ removeLastOccurrence(Object): boolean  [API 21+]
+ retainAll(Collection): boolean  [API 21+]
+ size(): int  [API 21+]
+ spliterator(): Spliterator  [API 21+]
+ toArray(): Object[]  [API 21+]
+ toArray(Object[]): Object[]  [API 21+]
+ toString(): String  [API 21+]
```

## ConcurrentLinkedQueue — framework (a2w.jdk.ConcurrentLinkedQueue); API 1+ (21 +, 0 *, 0 ~, 0 -)

```
+ ConcurrentLinkedQueue()  [API 1+]
+ ConcurrentLinkedQueue(Collection)  [API 1+]
+ add(Object): boolean  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ clear(): void  [API 1+]
+ contains(Object): boolean  [API 1+]
+ forEach(Consumer): void  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ offer(Object): boolean  [API 1+]
+ peek(): Object  [API 1+]
+ poll(): Object  [API 1+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeIf(Predicate): boolean  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
+ toString(): String  [API 1+]
```

## ConcurrentMap — TeaVM class library; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ compute(Object, BiFunction): Object  [API 1+]
+ computeIfAbsent(Object, Function): Object  [API 1+]
+ computeIfPresent(Object, BiFunction): Object  [API 1+]
+ forEach(BiConsumer): void  [API 1+]
+ getOrDefault(Object, Object): Object  [API 1+]
+ merge(Object, Object, BiFunction): Object  [API 1+]
+ putIfAbsent(Object, Object): Object  [API 1+]
+ remove(Object, Object): boolean  [API 1+]
+ replace(Object, Object): Object  [API 1+]
+ replace(Object, Object, Object): boolean  [API 1+]
+ replaceAll(BiFunction): void  [API 1+]
```

## ConcurrentNavigableMap — framework (a2w.jdk.util.concurrent.ConcurrentNavigableMap); API 9+ (10 +, 0 *, 0 ~, 0 -)

```
+ descendingKeySet(): NavigableSet  [API 9+]
+ descendingMap(): ConcurrentNavigableMap  [API 9+]
+ headMap(Object): ConcurrentNavigableMap  [API 9+]
+ headMap(Object, boolean): ConcurrentNavigableMap  [API 9+]
+ keySet(): NavigableSet  [API 9+]
+ navigableKeySet(): NavigableSet  [API 9+]
+ subMap(Object, Object): ConcurrentNavigableMap  [API 9+]
+ subMap(Object, boolean, Object, boolean): ConcurrentNavigableMap  [API 9+]
+ tailMap(Object): ConcurrentNavigableMap  [API 9+]
+ tailMap(Object, boolean): ConcurrentNavigableMap  [API 9+]
```

## ConcurrentSkipListMap — framework (a2w.jdk.util.concurrent.ConcurrentSkipListMap); API 9+ (54 +, 0 *, 0 ~, 0 -)

```
+ ConcurrentSkipListMap()  [API 9+]
+ ConcurrentSkipListMap(Comparator)  [API 9+]
+ ConcurrentSkipListMap(Map)  [API 9+]
+ ConcurrentSkipListMap(SortedMap)  [API 9+]
+ ceilingEntry(Object): Map.Entry  [API 9+]
+ ceilingKey(Object): Object  [API 9+]
+ clear(): void  [API 9+]
+ clone(): ConcurrentSkipListMap  [API 9+]
+ comparator(): Comparator  [API 9+]
+ compute(Object, BiFunction): Object  [API 9+]
+ computeIfAbsent(Object, Function): Object  [API 9+]
+ computeIfPresent(Object, BiFunction): Object  [API 9+]
+ containsKey(Object): boolean  [API 9+]
+ containsValue(Object): boolean  [API 9+]
+ descendingKeySet(): NavigableSet  [API 9+]
+ descendingMap(): ConcurrentNavigableMap  [API 9+]
+ entrySet(): Set  [API 9+]
+ equals(Object): boolean  [API 9+]
+ firstEntry(): Map.Entry  [API 9+]
+ firstKey(): Object  [API 9+]
+ floorEntry(Object): Map.Entry  [API 9+]
+ floorKey(Object): Object  [API 9+]
+ forEach(BiConsumer): void  [API 9+]
+ get(Object): Object  [API 9+]
+ getOrDefault(Object, Object): Object  [API 9+]
+ headMap(Object): ConcurrentNavigableMap  [API 9+]
+ headMap(Object, boolean): ConcurrentNavigableMap  [API 9+]
+ higherEntry(Object): Map.Entry  [API 9+]
+ higherKey(Object): Object  [API 9+]
+ isEmpty(): boolean  [API 9+]
+ keySet(): NavigableSet  [API 9+]
+ lastEntry(): Map.Entry  [API 9+]
+ lastKey(): Object  [API 9+]
+ lowerEntry(Object): Map.Entry  [API 9+]
+ lowerKey(Object): Object  [API 9+]
+ merge(Object, Object, BiFunction): Object  [API 9+]
+ navigableKeySet(): NavigableSet  [API 9+]
+ pollFirstEntry(): Map.Entry  [API 9+]
+ pollLastEntry(): Map.Entry  [API 9+]
+ put(Object, Object): Object  [API 9+]
+ putFirst(Object, Object): Object  [API 9+]
+ putIfAbsent(Object, Object): Object  [API 9+]
+ putLast(Object, Object): Object  [API 9+]
+ remove(Object): Object  [API 9+]
+ remove(Object, Object): boolean  [API 9+]
+ replace(Object, Object): Object  [API 9+]
+ replace(Object, Object, Object): boolean  [API 9+]
+ replaceAll(BiFunction): void  [API 9+]
+ size(): int  [API 9+]
+ subMap(Object, Object): ConcurrentNavigableMap  [API 9+]
+ subMap(Object, boolean, Object, boolean): ConcurrentNavigableMap  [API 9+]
+ tailMap(Object): ConcurrentNavigableMap  [API 9+]
+ tailMap(Object, boolean): ConcurrentNavigableMap  [API 9+]
+ values(): Collection  [API 9+]
```

## ConcurrentSkipListSet — framework (a2w.jdk.util.concurrent.ConcurrentSkipListSet); API 9+ (34 +, 0 *, 0 ~, 0 -)

```
+ ConcurrentSkipListSet()  [API 9+]
+ ConcurrentSkipListSet(Collection)  [API 9+]
+ ConcurrentSkipListSet(Comparator)  [API 9+]
+ ConcurrentSkipListSet(SortedSet)  [API 9+]
+ add(Object): boolean  [API 9+]
+ addFirst(Object): void  [API 9+]
+ addLast(Object): void  [API 9+]
+ ceiling(Object): Object  [API 9+]
+ clear(): void  [API 9+]
+ clone(): ConcurrentSkipListSet  [API 9+]
+ comparator(): Comparator  [API 9+]
+ contains(Object): boolean  [API 9+]
+ descendingIterator(): Iterator  [API 9+]
+ descendingSet(): NavigableSet  [API 9+]
+ equals(Object): boolean  [API 9+]
+ first(): Object  [API 9+]
+ floor(Object): Object  [API 9+]
+ headSet(Object): NavigableSet  [API 9+]
+ headSet(Object, boolean): NavigableSet  [API 9+]
+ higher(Object): Object  [API 9+]
+ isEmpty(): boolean  [API 9+]
+ iterator(): Iterator  [API 9+]
+ last(): Object  [API 9+]
+ lower(Object): Object  [API 9+]
+ pollFirst(): Object  [API 9+]
+ pollLast(): Object  [API 9+]
+ remove(Object): boolean  [API 9+]
+ removeAll(Collection): boolean  [API 9+]
+ size(): int  [API 9+]
+ spliterator(): Spliterator  [API 9+]
+ subSet(Object, Object): NavigableSet  [API 9+]
+ subSet(Object, boolean, Object, boolean): NavigableSet  [API 9+]
+ tailSet(Object): NavigableSet  [API 9+]
+ tailSet(Object, boolean): NavigableSet  [API 9+]
```

## CopyOnWriteArrayList — TeaVM class library; API 1+ (46 +, 0 *, 0 ~, 0 -)

```
+ CopyOnWriteArrayList()  [API 1+]
+ CopyOnWriteArrayList(Collection)  [API 1+]
+ CopyOnWriteArrayList(Object[])  [API 1+]
+ add(Object): boolean  [API 1+]
+ add(int, Object): void  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ addAll(int, Collection): boolean  [API 1+]
+ addAllAbsent(Collection): int  [API 1+]
+ addFirst(Object): void  [API 1+]
+ addIfAbsent(Object): boolean  [API 1+]
+ addLast(Object): void  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsAll(Collection): boolean  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forEach(Consumer): void  [API 1+]
+ get(int): Object  [API 1+]
+ getFirst(): Object  [API 1+]
+ getLast(): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ indexOf(Object): int  [API 1+]
+ indexOf(Object, int): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ lastIndexOf(Object): int  [API 1+]
+ lastIndexOf(Object, int): int  [API 1+]
+ listIterator(): ListIterator  [API 1+]
+ listIterator(int): ListIterator  [API 1+]
+ remove(Object): boolean  [API 1+]
+ remove(int): Object  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeFirst(): Object  [API 1+]
+ removeIf(Predicate): boolean  [API 1+]
+ removeLast(): Object  [API 1+]
+ replaceAll(UnaryOperator): void  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ reversed(): List  [API 1+]
+ set(int, Object): Object  [API 1+]
+ size(): int  [API 1+]
+ sort(Comparator): void  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ subList(int, int): List  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
+ toString(): String  [API 1+]
```

## CopyOnWriteArraySet — framework (a2w.jdk.util.concurrent.CopyOnWriteArraySet); API 1+ (19 +, 0 *, 0 ~, 0 -)

```
+ CopyOnWriteArraySet()  [API 1+]
+ CopyOnWriteArraySet(Collection)  [API 1+]
+ add(Object): boolean  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ clear(): void  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsAll(Collection): boolean  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forEach(Consumer): void  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeIf(Predicate): boolean  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
```

## CountDownLatch — framework (a2w.jdk.util.concurrent.CountDownLatch); API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ CountDownLatch(int)  [API 1+]
+ await(): void  [API 1+]
+ await(long, TimeUnit): boolean  [API 1+]
+ countDown(): void  [API 1+]
+ getCount(): long  [API 1+]
+ toString(): String  [API 1+]
```

## CountedCompleter — framework (a2w.jdk.util.concurrent.CountedCompleter); API 24+ (21 +, 0 *, 2 ~, 0 -)

```
+ CountedCompleter()  [API 24+]
+ CountedCompleter(CountedCompleter)  [API 24+]
+ CountedCompleter(CountedCompleter, int)  [API 24+]
+ addToPendingCount(int): void  [API 24+]
+ compareAndSetPendingCount(int, int): boolean  [API 24+]
+ complete(Object): void  [API 24+]
+ compute(): void  [API 24+]
+ decrementPendingCountUnlessZero(): int  [API 24+]
+ exec(): boolean  [API 24+]
+ firstComplete(): CountedCompleter  [API 24+]
+ getCompleter(): CountedCompleter  [API 24+]
+ getPendingCount(): int  [API 24+]
~ getRawResult(): Object  [API 24+]
+ getRoot(): CountedCompleter  [API 24+]
+ helpComplete(int): void  [API 24+]
+ nextComplete(): CountedCompleter  [API 24+]
+ onCompletion(CountedCompleter): void  [API 24+]
+ onExceptionalCompletion(Throwable, CountedCompleter): boolean  [API 24+]
+ propagateCompletion(): void  [API 24+]
+ quietlyCompleteRoot(): void  [API 24+]
+ setPendingCount(int): void  [API 24+]
~ setRawResult(Object): void  [API 24+]
+ tryComplete(): void  [API 24+]
```

## CyclicBarrier — framework (a2w.jdk.util.concurrent.CyclicBarrier); API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ CyclicBarrier(int)  [API 1+]
+ CyclicBarrier(int, Runnable)  [API 1+]
+ await(): int  [API 1+]
+ await(long, TimeUnit): int  [API 1+]
+ getNumberWaiting(): int  [API 1+]
+ getParties(): int  [API 1+]
+ isBroken(): boolean  [API 1+]
+ reset(): void  [API 1+]
```

## DelayQueue — framework (a2w.jdk.util.concurrent.DelayQueue); API 1+ (19 +, 0 *, 1 ~, 0 -)

```
+ DelayQueue()  [API 1+]
+ DelayQueue(Collection)  [API 1+]
+ add(Delayed): boolean  [API 1+]
+ clear(): void  [API 1+]
+ drainTo(Collection): int  [API 1+]
+ drainTo(Collection, int): int  [API 1+]
+ iterator(): Iterator  [API 1+]
+ offer(Delayed): boolean  [API 1+]
+ offer(Delayed, long, TimeUnit): boolean  [API 1+]
+ peek(): Delayed  [API 1+]
+ poll(): Delayed  [API 1+]
+ poll(long, TimeUnit): Delayed  [API 1+]
+ put(Delayed): void  [API 1+]
~ remainingCapacity(): int  [API 1+]
+ remove(): Delayed  [API 36+]
+ remove(Object): boolean  [API 1+]
+ size(): int  [API 1+]
+ take(): Delayed  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
```

## Delayed — framework (a2w.jdk.util.concurrent.Delayed); API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getDelay(TimeUnit): long  [API 1+]
```

## Exchanger — framework (a2w.jdk.util.concurrent.Exchanger); API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ Exchanger()  [API 1+]
+ exchange(Object): Object  [API 1+]
+ exchange(Object, long, TimeUnit): Object  [API 1+]
```

## ExecutionException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
- ExecutionException()  [API 1+]
- ExecutionException(String)  [API 1+]
+ ExecutionException(String, Throwable)  [API 1+]
+ ExecutionException(Throwable)  [API 1+]
```

## Executor — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ execute(Runnable): void  [API 1+]
```

## ExecutorCompletionService — framework (a2w.jdk.util.concurrent.ExecutorCompletionService); API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ ExecutorCompletionService(Executor)  [API 1+]
+ ExecutorCompletionService(Executor, BlockingQueue)  [API 1+]
+ poll(): Future  [API 1+]
+ poll(long, TimeUnit): Future  [API 1+]
+ submit(Callable): Future  [API 1+]
+ submit(Runnable, Object): Future  [API 1+]
+ take(): Future  [API 1+]
```

## ExecutorService — framework (a2w.jdk.util.concurrent.ExecutorService); API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ awaitTermination(long, TimeUnit): boolean  [API 1+]
+ invokeAll(Collection): List  [API 1+]
+ invokeAll(Collection, long, TimeUnit): List  [API 1+]
+ invokeAny(Collection): Object  [API 1+]
+ invokeAny(Collection, long, TimeUnit): Object  [API 1+]
+ isShutdown(): boolean  [API 1+]
+ isTerminated(): boolean  [API 1+]
+ shutdown(): void  [API 1+]
+ shutdownNow(): List  [API 1+]
+ submit(Callable): Future  [API 1+]
+ submit(Runnable): Future  [API 1+]
+ submit(Runnable, Object): Future  [API 1+]
```

## Executors — framework (a2w.jdk.util.concurrent.Executors); API 1+ (18 +, 0 *, 0 ~, 4 -)

```
- callable(PrivilegedAction): Callable [static]  [API 1+]
- callable(PrivilegedExceptionAction): Callable [static]  [API 1+]
+ callable(Runnable): Callable [static]  [API 1+]
+ callable(Runnable, Object): Callable [static]  [API 1+]
+ defaultThreadFactory(): ThreadFactory [static]  [API 1+]
+ newCachedThreadPool(): ExecutorService [static]  [API 1+]
+ newCachedThreadPool(ThreadFactory): ExecutorService [static]  [API 1+]
+ newFixedThreadPool(int): ExecutorService [static]  [API 1+]
+ newFixedThreadPool(int, ThreadFactory): ExecutorService [static]  [API 1+]
+ newScheduledThreadPool(int): ScheduledExecutorService [static]  [API 1+]
+ newScheduledThreadPool(int, ThreadFactory): ScheduledExecutorService [static]  [API 1+]
+ newSingleThreadExecutor(): ExecutorService [static]  [API 1+]
+ newSingleThreadExecutor(ThreadFactory): ExecutorService [static]  [API 1+]
+ newSingleThreadScheduledExecutor(): ScheduledExecutorService [static]  [API 1+]
+ newSingleThreadScheduledExecutor(ThreadFactory): ScheduledExecutorService [static]  [API 1+]
+ newWorkStealingPool(): ExecutorService [static]  [API 24+]
+ newWorkStealingPool(int): ExecutorService [static]  [API 24+]
- privilegedCallable(Callable): Callable [static]  [API 1+, deprecated in 34]
- privilegedCallableUsingCurrentClassLoader(Callable): Callable [static]  [API 1+, deprecated in 34]
+ privilegedThreadFactory(): ThreadFactory [static]  [API 1+, deprecated in 34]
+ unconfigurableExecutorService(ExecutorService): ExecutorService [static]  [API 1+]
+ unconfigurableScheduledExecutorService(ScheduledExecutorService): ScheduledExecutorService [static]  [API 1+]
```

## Flow — TeaVM class library; API 30+ (0 +, 0 *, 0 ~, 1 -)

```
- defaultBufferSize(): int [static]  [API 30+]
```

## Flow.Processor — TeaVM class library; API 30+ (0 +, 0 *, 0 ~, 0 -)

## Flow.Publisher — TeaVM class library; API 30+ (1 +, 0 *, 0 ~, 0 -)

```
+ subscribe(Flow.Subscriber): void  [API 30+]
```

## Flow.Subscriber — TeaVM class library; API 30+ (4 +, 0 *, 0 ~, 0 -)

```
+ onComplete(): void  [API 30+]
+ onError(Throwable): void  [API 30+]
+ onNext(Object): void  [API 30+]
+ onSubscribe(Flow.Subscription): void  [API 30+]
```

## Flow.Subscription — TeaVM class library; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ cancel(): void  [API 30+]
+ request(long): void  [API 30+]
```

## ForkJoinPool — framework (a2w.jdk.util.concurrent.ForkJoinPool); API 21+ (44 +, 0 *, 1 ~, 0 -)

```
+ ForkJoinPool()  [API 21+]
+ ForkJoinPool(int)  [API 21+]
+ ForkJoinPool(int, ForkJoinPool.ForkJoinWorkerThreadFactory, Thread.UncaughtExceptionHandler, boolean)  [API 21+]
+ ForkJoinPool(int, ForkJoinPool.ForkJoinWorkerThreadFactory, Thread.UncaughtExceptionHandler, boolean, int, int, int, Predicate, long, TimeUnit)  [API 33+]
+ awaitQuiescence(long, TimeUnit): boolean  [API 21+]
+ awaitTermination(long, TimeUnit): boolean  [API 21+]
+ commonPool(): ForkJoinPool [static]  [API 24+]
+ defaultForkJoinWorkerThreadFactory [field]  [API 21+]
+ drainTasksTo(Collection): int  [API 21+]
+ execute(ForkJoinTask): void  [API 21+]
+ execute(Runnable): void  [API 21+]
+ externalSubmit(ForkJoinTask): ForkJoinTask  [API 36+]
+ getActiveThreadCount(): int  [API 21+]
+ getAsyncMode(): boolean  [API 21+]
+ getCommonPoolParallelism(): int [static]  [API 24+]
+ getFactory(): ForkJoinPool.ForkJoinWorkerThreadFactory  [API 21+]
+ getParallelism(): int  [API 21+]
+ getPoolSize(): int  [API 21+]
+ getQueuedSubmissionCount(): int  [API 21+]
+ getQueuedTaskCount(): long  [API 21+]
+ getRunningThreadCount(): int  [API 21+]
~ getStealCount(): long  [API 21+]
+ getUncaughtExceptionHandler(): Thread.UncaughtExceptionHandler  [API 21+]
+ hasQueuedSubmissions(): boolean  [API 21+]
+ invoke(ForkJoinTask): Object  [API 21+]
+ invokeAll(Collection): List  [API 21+]
+ invokeAll(Collection, long, TimeUnit): List  [API 21+]
+ invokeAny(Collection): Object  [API 21+]
+ invokeAny(Collection, long, TimeUnit): Object  [API 21+]
+ isQuiescent(): boolean  [API 21+]
+ isShutdown(): boolean  [API 21+]
+ isTerminated(): boolean  [API 21+]
+ isTerminating(): boolean  [API 21+]
+ lazySubmit(ForkJoinTask): ForkJoinTask  [API 36+]
+ managedBlock(ForkJoinPool.ManagedBlocker): void [static]  [API 21+]
+ newTaskFor(Callable): RunnableFuture  [API 21+]
+ newTaskFor(Runnable, Object): RunnableFuture  [API 21+]
+ pollSubmission(): ForkJoinTask  [API 21+]
+ setParallelism(int): int  [API 36+]
+ shutdown(): void  [API 21+]
+ shutdownNow(): List  [API 21+]
+ submit(Callable): ForkJoinTask  [API 21+]
+ submit(ForkJoinTask): ForkJoinTask  [API 21+]
+ submit(Runnable): ForkJoinTask  [API 21+]
+ submit(Runnable, Object): ForkJoinTask  [API 21+]
+ toString(): String  [API 21+]
```

## ForkJoinPool.ForkJoinWorkerThreadFactory — framework (a2w.jdk.util.concurrent.ForkJoinPool$ForkJoinWorkerThreadFactory); API 21+ (1 +, 0 *, 0 ~, 0 -)

```
+ newThread(ForkJoinPool): ForkJoinWorkerThread  [API 21+]
```

## ForkJoinPool.ManagedBlocker — framework (a2w.jdk.util.concurrent.ForkJoinPool$ManagedBlocker); API 21+ (2 +, 0 *, 0 ~, 0 -)

```
+ block(): boolean  [API 21+]
+ isReleasable(): boolean  [API 21+]
```

## ForkJoinTask — framework (a2w.jdk.util.concurrent.ForkJoinTask); API 21+ (42 +, 0 *, 0 ~, 0 -)

```
+ ForkJoinTask()  [API 21+]
+ adapt(Callable): ForkJoinTask [static]  [API 21+]
+ adapt(Runnable): ForkJoinTask [static]  [API 21+]
+ adapt(Runnable, Object): ForkJoinTask [static]  [API 21+]
+ adaptInterruptible(Callable): ForkJoinTask  [API 36+]
+ cancel(boolean): boolean  [API 21+]
+ compareAndSetForkJoinTaskTag(short, short): boolean  [API 24+]
+ complete(Object): void  [API 21+]
+ completeExceptionally(Throwable): void  [API 21+]
+ exec(): boolean  [API 21+]
+ fork(): ForkJoinTask  [API 21+]
+ get(): Object  [API 21+]
+ get(long, TimeUnit): Object  [API 21+]
+ getException(): Throwable  [API 21+]
+ getForkJoinTaskTag(): short  [API 24+]
+ getPool(): ForkJoinPool [static]  [API 21+]
+ getQueuedTaskCount(): int [static]  [API 21+]
+ getRawResult(): Object  [API 21+]
+ getSurplusQueuedTaskCount(): int [static]  [API 21+]
+ helpQuiesce(): void [static]  [API 21+]
+ inForkJoinPool(): boolean [static]  [API 21+]
+ invoke(): Object  [API 21+]
+ invokeAll(Collection): Collection [static]  [API 21+]
+ invokeAll(ForkJoinTask, ForkJoinTask): void [static]  [API 21+]
+ invokeAll(ForkJoinTask[]): void [static]  [API 21+]
+ isCancelled(): boolean  [API 21+]
+ isCompletedAbnormally(): boolean  [API 21+]
+ isCompletedNormally(): boolean  [API 21+]
+ isDone(): boolean  [API 21+]
+ join(): Object  [API 21+]
+ peekNextLocalTask(): ForkJoinTask [static]  [API 21+]
+ pollNextLocalTask(): ForkJoinTask [static]  [API 21+]
+ pollTask(): ForkJoinTask [static]  [API 21+]
+ quietlyComplete(): void  [API 24+]
+ quietlyInvoke(): void  [API 21+]
+ quietlyJoin(): void  [API 21+]
+ quietlyJoin(long, TimeUnit): boolean  [API 36+]
+ quietlyJoinUninterruptibly(long, TimeUnit): boolean  [API 36+]
+ reinitialize(): void  [API 21+]
+ setForkJoinTaskTag(short): short  [API 24+]
+ setRawResult(Object): void  [API 21+]
+ tryUnfork(): boolean  [API 21+]
```

## ForkJoinWorkerThread — framework (a2w.jdk.util.concurrent.ForkJoinWorkerThread); API 21+ (8 +, 0 *, 0 ~, 0 -)

```
+ ForkJoinWorkerThread(ForkJoinPool)  [API 21+]
+ ForkJoinWorkerThread(ThreadGroup, ForkJoinPool, boolean)  [API 36+]
+ getPool(): ForkJoinPool  [API 21+]
+ getPoolIndex(): int  [API 21+]
+ getQueuedTaskCount(): int  [API 36+]
+ onStart(): void  [API 21+]
+ onTermination(Throwable): void  [API 21+]
+ run(): void  [API 21+]
```

## Future — framework (a2w.jdk.util.concurrent.Future); API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ cancel(boolean): boolean  [API 1+]
+ exceptionNow(): Throwable  [API 36+]
+ get(): Object  [API 1+]
+ get(long, TimeUnit): Object  [API 1+]
+ isCancelled(): boolean  [API 1+]
+ isDone(): boolean  [API 1+]
+ resultNow(): Object  [API 36+]
+ state(): Future.State  [API 36+]
```

## Future.State — declaration; API 36+ (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): Future.State  [API 36+]
+ values(): Future.State[]  [API 36+]
```

## FutureTask — framework (a2w.jdk.util.concurrent.FutureTask); API 1+ (12 +, 0 *, 1 ~, 0 -)

```
+ FutureTask(Callable)  [API 1+]
+ FutureTask(Runnable, Object)  [API 1+]
+ cancel(boolean): boolean  [API 1+]
~ done(): void  [API 1+]
+ get(): Object  [API 1+]
+ get(long, TimeUnit): Object  [API 1+]
+ isCancelled(): boolean  [API 1+]
+ isDone(): boolean  [API 1+]
+ run(): void  [API 1+]
+ runAndReset(): boolean  [API 1+]
+ set(Object): void  [API 1+]
+ setException(Throwable): void  [API 1+]
+ toString(): String  [API 1+]
```

## LinkedBlockingDeque — framework (a2w.jdk.util.concurrent.LinkedBlockingDeque); API 9+ (55 +, 0 *, 0 ~, 0 -)

```
+ LinkedBlockingDeque()  [API 9+]
+ LinkedBlockingDeque(Collection)  [API 9+]
+ LinkedBlockingDeque(int)  [API 9+]
+ add(Object): boolean  [API 9+]
+ addAll(Collection): boolean  [API 9+]
+ addFirst(Object): void  [API 9+]
+ addLast(Object): void  [API 9+]
+ clear(): void  [API 9+]
+ contains(Object): boolean  [API 9+]
+ descendingIterator(): Iterator  [API 9+]
+ drainTo(Collection): int  [API 9+]
+ drainTo(Collection, int): int  [API 9+]
+ element(): Object  [API 9+]
+ forEach(Consumer): void  [API 9+]
+ getFirst(): Object  [API 9+]
+ getLast(): Object  [API 9+]
+ iterator(): Iterator  [API 9+]
+ offer(Object): boolean  [API 9+]
+ offer(Object, long, TimeUnit): boolean  [API 9+]
+ offerFirst(Object): boolean  [API 9+]
+ offerFirst(Object, long, TimeUnit): boolean  [API 9+]
+ offerLast(Object): boolean  [API 9+]
+ offerLast(Object, long, TimeUnit): boolean  [API 9+]
+ peek(): Object  [API 9+]
+ peekFirst(): Object  [API 9+]
+ peekLast(): Object  [API 9+]
+ poll(): Object  [API 9+]
+ poll(long, TimeUnit): Object  [API 9+]
+ pollFirst(): Object  [API 9+]
+ pollFirst(long, TimeUnit): Object  [API 9+]
+ pollLast(): Object  [API 9+]
+ pollLast(long, TimeUnit): Object  [API 9+]
+ pop(): Object  [API 9+]
+ push(Object): void  [API 9+]
+ put(Object): void  [API 9+]
+ putFirst(Object): void  [API 9+]
+ putLast(Object): void  [API 9+]
+ remainingCapacity(): int  [API 9+]
+ remove(): Object  [API 9+]
+ remove(Object): boolean  [API 9+]
+ removeAll(Collection): boolean  [API 9+]
+ removeFirst(): Object  [API 9+]
+ removeFirstOccurrence(Object): boolean  [API 9+]
+ removeIf(Predicate): boolean  [API 9+]
+ removeLast(): Object  [API 9+]
+ removeLastOccurrence(Object): boolean  [API 9+]
+ retainAll(Collection): boolean  [API 9+]
+ size(): int  [API 9+]
+ spliterator(): Spliterator  [API 9+]
+ take(): Object  [API 9+]
+ takeFirst(): Object  [API 9+]
+ takeLast(): Object  [API 9+]
+ toArray(): Object[]  [API 9+]
+ toArray(Object[]): Object[]  [API 9+]
+ toString(): String  [API 9+]
```

## LinkedBlockingQueue — framework (a2w.jdk.util.concurrent.LinkedBlockingQueue); API 1+ (26 +, 0 *, 0 ~, 0 -)

```
+ LinkedBlockingQueue()  [API 1+]
+ LinkedBlockingQueue(Collection)  [API 1+]
+ LinkedBlockingQueue(int)  [API 1+]
+ clear(): void  [API 1+]
+ contains(Object): boolean  [API 1+]
+ drainTo(Collection): int  [API 1+]
+ drainTo(Collection, int): int  [API 1+]
+ forEach(Consumer): void  [API 1+]
+ iterator(): Iterator  [API 1+]
+ offer(Object): boolean  [API 1+]
+ offer(Object, long, TimeUnit): boolean  [API 1+]
+ peek(): Object  [API 1+]
+ poll(): Object  [API 1+]
+ poll(long, TimeUnit): Object  [API 1+]
+ put(Object): void  [API 1+]
+ remainingCapacity(): int  [API 1+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeIf(Predicate): boolean  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ take(): Object  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
+ toString(): String  [API 1+]
```

## LinkedTransferQueue — framework (a2w.jdk.util.concurrent.LinkedTransferQueue); API 21+ (31 +, 0 *, 1 ~, 0 -)

```
+ LinkedTransferQueue()  [API 21+]
+ LinkedTransferQueue(Collection)  [API 21+]
+ add(Object): boolean  [API 21+]
+ clear(): void  [API 21+]
+ contains(Object): boolean  [API 21+]
+ drainTo(Collection): int  [API 21+]
+ drainTo(Collection, int): int  [API 21+]
+ forEach(Consumer): void  [API 21+]
+ getWaitingConsumerCount(): int  [API 21+]
+ hasWaitingConsumer(): boolean  [API 21+]
+ isEmpty(): boolean  [API 21+]
+ iterator(): Iterator  [API 21+]
+ offer(Object): boolean  [API 21+]
+ offer(Object, long, TimeUnit): boolean  [API 21+]
+ peek(): Object  [API 21+]
+ poll(): Object  [API 21+]
+ poll(long, TimeUnit): Object  [API 21+]
+ put(Object): void  [API 21+]
~ remainingCapacity(): int  [API 21+]
+ remove(Object): boolean  [API 21+]
+ removeAll(Collection): boolean  [API 21+]
+ removeIf(Predicate): boolean  [API 21+]
+ retainAll(Collection): boolean  [API 21+]
+ size(): int  [API 21+]
+ spliterator(): Spliterator  [API 21+]
+ take(): Object  [API 21+]
+ toArray(): Object[]  [API 21+]
+ toArray(Object[]): Object[]  [API 21+]
+ toString(): String  [API 21+]
+ transfer(Object): void  [API 21+]
+ tryTransfer(Object): boolean  [API 21+]
+ tryTransfer(Object, long, TimeUnit): boolean  [API 21+]
```

## Phaser — framework (a2w.jdk.util.concurrent.Phaser); API 21+ (22 +, 0 *, 0 ~, 0 -)

```
+ Phaser()  [API 21+]
+ Phaser(Phaser)  [API 21+]
+ Phaser(Phaser, int)  [API 21+]
+ Phaser(int)  [API 21+]
+ arrive(): int  [API 21+]
+ arriveAndAwaitAdvance(): int  [API 21+]
+ arriveAndDeregister(): int  [API 21+]
+ awaitAdvance(int): int  [API 21+]
+ awaitAdvanceInterruptibly(int): int  [API 21+]
+ awaitAdvanceInterruptibly(int, long, TimeUnit): int  [API 21+]
+ bulkRegister(int): int  [API 21+]
+ forceTermination(): void  [API 21+]
+ getArrivedParties(): int  [API 21+]
+ getParent(): Phaser  [API 21+]
+ getPhase(): int  [API 21+]
+ getRegisteredParties(): int  [API 21+]
+ getRoot(): Phaser  [API 21+]
+ getUnarrivedParties(): int  [API 21+]
+ isTerminated(): boolean  [API 21+]
+ onAdvance(int, int): boolean  [API 21+]
+ register(): int  [API 21+]
+ toString(): String  [API 21+]
```

## PriorityBlockingQueue — framework (a2w.jdk.util.concurrent.PriorityBlockingQueue); API 1+ (28 +, 0 *, 1 ~, 0 -)

```
+ PriorityBlockingQueue()  [API 1+]
+ PriorityBlockingQueue(Collection)  [API 1+]
+ PriorityBlockingQueue(int)  [API 1+]
+ PriorityBlockingQueue(int, Comparator)  [API 1+]
+ add(Object): boolean  [API 1+]
+ clear(): void  [API 1+]
+ comparator(): Comparator  [API 1+]
+ contains(Object): boolean  [API 1+]
+ drainTo(Collection): int  [API 1+]
+ drainTo(Collection, int): int  [API 1+]
+ forEach(Consumer): void  [API 1+]
+ iterator(): Iterator  [API 1+]
+ offer(Object): boolean  [API 1+]
+ offer(Object, long, TimeUnit): boolean  [API 1+]
+ peek(): Object  [API 1+]
+ poll(): Object  [API 1+]
+ poll(long, TimeUnit): Object  [API 1+]
+ put(Object): void  [API 1+]
~ remainingCapacity(): int  [API 1+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeIf(Predicate): boolean  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ take(): Object  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
+ toString(): String  [API 1+]
```

## RecursiveAction — framework (a2w.jdk.util.concurrent.RecursiveAction); API 21+ (3 +, 0 *, 2 ~, 0 -)

```
+ RecursiveAction()  [API 21+]
+ compute(): void  [API 21+]
+ exec(): boolean  [API 21+]
~ getRawResult(): Void  [API 21+]
~ setRawResult(Void): void  [API 21+]
```

## RecursiveTask — framework (a2w.jdk.util.concurrent.RecursiveTask); API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ RecursiveTask()  [API 21+]
+ compute(): Object  [API 21+]
+ exec(): boolean  [API 21+]
+ getRawResult(): Object  [API 21+]
+ setRawResult(Object): void  [API 21+]
```

## RejectedExecutionException — framework (a2w.jdk.util.concurrent.RejectedExecutionException); API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ RejectedExecutionException()  [API 1+]
+ RejectedExecutionException(String)  [API 1+]
+ RejectedExecutionException(String, Throwable)  [API 1+]
+ RejectedExecutionException(Throwable)  [API 1+]
```

## RejectedExecutionHandler — framework (a2w.jdk.util.concurrent.RejectedExecutionHandler); API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ rejectedExecution(Runnable, ThreadPoolExecutor): void  [API 1+]
```

## RunnableFuture — framework (a2w.jdk.util.concurrent.RunnableFuture); API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ run(): void  [API 9+]
```

## RunnableScheduledFuture — framework (a2w.jdk.util.concurrent.RunnableScheduledFuture); API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ isPeriodic(): boolean  [API 9+]
```

## ScheduledExecutorService — framework (a2w.jdk.util.concurrent.ScheduledExecutorService); API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ schedule(Callable, long, TimeUnit): ScheduledFuture  [API 1+]
+ schedule(Runnable, long, TimeUnit): ScheduledFuture  [API 1+]
+ scheduleAtFixedRate(Runnable, long, long, TimeUnit): ScheduledFuture  [API 1+]
+ scheduleWithFixedDelay(Runnable, long, long, TimeUnit): ScheduledFuture  [API 1+]
```

## ScheduledFuture — framework (a2w.jdk.util.concurrent.ScheduledFuture); API 1+ (0 +, 0 *, 0 ~, 0 -)

## ScheduledThreadPoolExecutor — framework (a2w.jdk.util.concurrent.ScheduledThreadPoolExecutor); API 1+ (23 +, 0 *, 0 ~, 0 -)

```
+ ScheduledThreadPoolExecutor(int)  [API 1+]
+ ScheduledThreadPoolExecutor(int, RejectedExecutionHandler)  [API 1+]
+ ScheduledThreadPoolExecutor(int, ThreadFactory)  [API 1+]
+ ScheduledThreadPoolExecutor(int, ThreadFactory, RejectedExecutionHandler)  [API 1+]
+ decorateTask(Callable, RunnableScheduledFuture): RunnableScheduledFuture  [API 9+]
+ decorateTask(Runnable, RunnableScheduledFuture): RunnableScheduledFuture  [API 9+]
+ execute(Runnable): void  [API 1+]
+ getContinueExistingPeriodicTasksAfterShutdownPolicy(): boolean  [API 1+]
+ getExecuteExistingDelayedTasksAfterShutdownPolicy(): boolean  [API 1+]
+ getQueue(): BlockingQueue  [API 1+]
+ getRemoveOnCancelPolicy(): boolean  [API 21+]
+ schedule(Callable, long, TimeUnit): ScheduledFuture  [API 1+]
+ schedule(Runnable, long, TimeUnit): ScheduledFuture  [API 1+]
+ scheduleAtFixedRate(Runnable, long, long, TimeUnit): ScheduledFuture  [API 1+]
+ scheduleWithFixedDelay(Runnable, long, long, TimeUnit): ScheduledFuture  [API 1+]
+ setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean): void  [API 1+]
+ setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean): void  [API 1+]
+ setRemoveOnCancelPolicy(boolean): void  [API 21+]
+ shutdown(): void  [API 1+]
+ shutdownNow(): List  [API 1+]
+ submit(Callable): Future  [API 1+]
+ submit(Runnable): Future  [API 1+]
+ submit(Runnable, Object): Future  [API 1+]
```

## Semaphore — framework (a2w.jdk.util.concurrent.Semaphore); API 1+ (20 +, 0 *, 0 ~, 0 -)

```
+ Semaphore(int)  [API 1+]
+ Semaphore(int, boolean)  [API 1+]
+ acquire(): void  [API 1+]
+ acquire(int): void  [API 1+]
+ acquireUninterruptibly(): void  [API 1+]
+ acquireUninterruptibly(int): void  [API 1+]
+ availablePermits(): int  [API 1+]
+ drainPermits(): int  [API 1+]
+ getQueueLength(): int  [API 1+]
+ getQueuedThreads(): Collection  [API 1+]
+ hasQueuedThreads(): boolean  [API 1+]
+ isFair(): boolean  [API 1+]
+ reducePermits(int): void  [API 1+]
+ release(): void  [API 1+]
+ release(int): void  [API 1+]
+ toString(): String  [API 1+]
+ tryAcquire(): boolean  [API 1+]
+ tryAcquire(int): boolean  [API 1+]
+ tryAcquire(int, long, TimeUnit): boolean  [API 1+]
+ tryAcquire(long, TimeUnit): boolean  [API 1+]
```

## SubmissionPublisher — declaration; API 33+ (0 +, 3 *, 0 ~, 17 -)

```
- SubmissionPublisher()  [API 33+]
* SubmissionPublisher(Executor, int)  [API 33+]
- SubmissionPublisher(Executor, int, BiConsumer)  [API 33+]
- close(): void  [API 33+]
- closeExceptionally(Throwable): void  [API 33+]
- consume(Consumer): CompletableFuture  [API 33+]
- estimateMaximumLag(): int  [API 33+]
- estimateMinimumDemand(): long  [API 33+]
- getClosedException(): Throwable  [API 33+]
* getExecutor(): Executor  [API 33+]
* getMaxBufferCapacity(): int  [API 33+]
- getNumberOfSubscribers(): int  [API 33+]
- getSubscribers(): List  [API 33+]
- hasSubscribers(): boolean  [API 33+]
- isClosed(): boolean  [API 33+]
- isSubscribed(Flow.Subscriber): boolean  [API 33+]
- offer(Object, BiPredicate): int  [API 33+]
- offer(Object, long, TimeUnit, BiPredicate): int  [API 33+]
- submit(Object): int  [API 33+]
- subscribe(Flow.Subscriber): void  [API 33+]
```

## SynchronousQueue — framework (a2w.jdk.util.concurrent.SynchronousQueue); API 1+ (21 +, 0 *, 4 ~, 0 -)

```
+ SynchronousQueue()  [API 1+]
+ SynchronousQueue(boolean)  [API 1+]
+ clear(): void  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsAll(Collection): boolean  [API 1+]
+ drainTo(Collection): int  [API 1+]
+ drainTo(Collection, int): int  [API 1+]
~ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ offer(Object): boolean  [API 1+]
+ offer(Object, long, TimeUnit): boolean  [API 1+]
~ peek(): Object  [API 1+]
+ poll(): Object  [API 1+]
+ poll(long, TimeUnit): Object  [API 1+]
+ put(Object): void  [API 1+]
~ remainingCapacity(): int  [API 1+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
~ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ take(): Object  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
+ toString(): String  [API 1+]
```

## ThreadFactory — framework (a2w.jdk.util.concurrent.ThreadFactory); API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ newThread(Runnable): Thread  [API 1+]
```

## ThreadLocalRandom — TeaVM class library; API 21+ (28 +, 0 *, 0 ~, 0 -)

```
+ current(): ThreadLocalRandom [static]  [API 21+]
+ doubles(): DoubleStream  [API 21+]
+ doubles(double, double): DoubleStream  [API 21+]
+ doubles(long): DoubleStream  [API 21+]
+ doubles(long, double, double): DoubleStream  [API 21+]
+ ints(): IntStream  [API 21+]
+ ints(int, int): IntStream  [API 21+]
+ ints(long): IntStream  [API 21+]
+ ints(long, int, int): IntStream  [API 21+]
+ longs(): LongStream  [API 21+]
+ longs(long): LongStream  [API 21+]
+ longs(long, long): LongStream  [API 21+]
+ longs(long, long, long): LongStream  [API 21+]
+ next(int): int  [API 21+]
+ nextBoolean(): boolean  [API 21+]
+ nextDouble(): double  [API 21+]
+ nextDouble(double): double  [API 21+]
+ nextDouble(double, double): double  [API 21+]
+ nextFloat(): float  [API 21+]
+ nextFloat(float): float  [API 21+]
+ nextFloat(float, float): float  [API 21+]
+ nextInt(): int  [API 21+]
+ nextInt(int): int  [API 21+]
+ nextInt(int, int): int  [API 21+]
+ nextLong(): long  [API 21+]
+ nextLong(long): long  [API 21+]
+ nextLong(long, long): long  [API 21+]
+ setSeed(long): void  [API 21+]
```

## ThreadPoolExecutor — framework (a2w.jdk.util.concurrent.ThreadPoolExecutor); API 1+ (35 +, 0 *, 3 ~, 0 -)

```
+ ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue)  [API 1+]
+ ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue, RejectedExecutionHandler)  [API 1+]
+ ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue, ThreadFactory)  [API 1+]
+ ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue, ThreadFactory, RejectedExecutionHandler)  [API 1+]
~ afterExecute(Runnable, Throwable): void  [API 1+]
+ allowCoreThreadTimeOut(boolean): void  [API 9+]
+ allowsCoreThreadTimeOut(): boolean  [API 9+]
+ awaitTermination(long, TimeUnit): boolean  [API 1+]
~ beforeExecute(Thread, Runnable): void  [API 1+]
+ execute(Runnable): void  [API 1+]
+ finalize(): void  [API 1+]
+ getActiveCount(): int  [API 1+]
+ getCompletedTaskCount(): long  [API 1+]
+ getCorePoolSize(): int  [API 1+]
+ getKeepAliveTime(TimeUnit): long  [API 1+]
+ getLargestPoolSize(): int  [API 1+]
+ getMaximumPoolSize(): int  [API 1+]
+ getPoolSize(): int  [API 1+]
+ getQueue(): BlockingQueue  [API 1+]
+ getRejectedExecutionHandler(): RejectedExecutionHandler  [API 1+]
+ getTaskCount(): long  [API 1+]
+ getThreadFactory(): ThreadFactory  [API 1+]
+ isShutdown(): boolean  [API 1+]
+ isTerminated(): boolean  [API 1+]
+ isTerminating(): boolean  [API 1+]
+ prestartAllCoreThreads(): int  [API 1+]
+ prestartCoreThread(): boolean  [API 1+]
+ purge(): void  [API 1+]
+ remove(Runnable): boolean  [API 1+]
+ setCorePoolSize(int): void  [API 1+]
+ setKeepAliveTime(long, TimeUnit): void  [API 1+]
+ setMaximumPoolSize(int): void  [API 1+]
+ setRejectedExecutionHandler(RejectedExecutionHandler): void  [API 1+]
+ setThreadFactory(ThreadFactory): void  [API 1+]
+ shutdown(): void  [API 1+]
+ shutdownNow(): List  [API 1+]
~ terminated(): void  [API 1+]
+ toString(): String  [API 1+]
```

## ThreadPoolExecutor.AbortPolicy — framework (a2w.jdk.util.concurrent.ThreadPoolExecutor$AbortPolicy); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ AbortPolicy()  [API 1+]
+ rejectedExecution(Runnable, ThreadPoolExecutor): void  [API 1+]
```

## ThreadPoolExecutor.CallerRunsPolicy — framework (a2w.jdk.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CallerRunsPolicy()  [API 1+]
+ rejectedExecution(Runnable, ThreadPoolExecutor): void  [API 1+]
```

## ThreadPoolExecutor.DiscardOldestPolicy — framework (a2w.jdk.util.concurrent.ThreadPoolExecutor$DiscardOldestPolicy); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ DiscardOldestPolicy()  [API 1+]
+ rejectedExecution(Runnable, ThreadPoolExecutor): void  [API 1+]
```

## ThreadPoolExecutor.DiscardPolicy — framework (a2w.jdk.util.concurrent.ThreadPoolExecutor$DiscardPolicy); API 1+ (1 +, 0 *, 1 ~, 0 -)

```
+ DiscardPolicy()  [API 1+]
~ rejectedExecution(Runnable, ThreadPoolExecutor): void  [API 1+]
```

## TimeUnit — TeaVM class library; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ DAYS [field]  [API 9+]
+ HOURS [field]  [API 9+]
+ MICROSECONDS [field]  [API 1+]
+ MILLISECONDS [field]  [API 1+]
+ MINUTES [field]  [API 9+]
+ NANOSECONDS [field]  [API 1+]
+ SECONDS [field]  [API 1+]
+ convert(Duration): long  [API 33+]
+ convert(long, TimeUnit): long  [API 1+]
+ of(ChronoUnit): TimeUnit [static]  [API 33+]
+ sleep(long): void  [API 1+]
+ timedJoin(Thread, long): void  [API 1+]
+ timedWait(Object, long): void  [API 1+]
+ toChronoUnit(): ChronoUnit  [API 33+]
+ toDays(long): long  [API 9+]
+ toHours(long): long  [API 9+]
+ toMicros(long): long  [API 1+]
+ toMillis(long): long  [API 1+]
+ toMinutes(long): long  [API 9+]
+ toNanos(long): long  [API 1+]
+ toSeconds(long): long  [API 1+]
+ valueOf(String): TimeUnit [static]  [API 1+]
+ values(): TimeUnit[] [static]  [API 1+]
```

## TimeoutException — framework (a2w.jdk.util.concurrent.TimeoutException); API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ TimeoutException()  [API 1+]
+ TimeoutException(String)  [API 1+]
```

## TransferQueue — declaration; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ getWaitingConsumerCount(): int  [API 21+]
+ hasWaitingConsumer(): boolean  [API 21+]
+ transfer(Object): void  [API 21+]
+ tryTransfer(Object): boolean  [API 21+]
+ tryTransfer(Object, long, TimeUnit): boolean  [API 21+]
```


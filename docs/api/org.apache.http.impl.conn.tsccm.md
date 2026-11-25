# org.apache.http.impl.conn.tsccm

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractConnPool — bundled library; API 1-22, removed in 23, deprecated in 22 (11 +, 0 *, 0 ~, 0 -)

```
+ AbstractConnPool()  [API 1-22, removed in 23]
+ closeConnection(OperatedClientConnection): void  [API 1-22, removed in 23]
+ closeExpiredConnections(): void  [API 1-22, removed in 23]
+ closeIdleConnections(long, TimeUnit): void  [API 1-22, removed in 23]
+ deleteClosedConnections(): void  [API 1-22, removed in 23]
+ enableConnectionGC(): void  [API 1-22, removed in 23]
+ freeEntry(BasicPoolEntry, boolean, long, TimeUnit): void  [API 1-22, removed in 23]
+ getEntry(HttpRoute, Object, long, TimeUnit): BasicPoolEntry  [API 1-22, removed in 23]
+ handleLostEntry(HttpRoute): void  [API 1-22, removed in 23]
+ idleConnHandler [field]  [API 1-22, removed in 23]
+ isShutDown [field]  [API 1-22, removed in 23]
+ issuedConnections [field]  [API 1-22, removed in 23]
+ numConnections [field]  [API 1-22, removed in 23]
+ poolLock [field]  [API 1-22, removed in 23]
+ refQueue [field]  [API 1-22, removed in 23]
+ requestPoolEntry(HttpRoute, Object): PoolEntryRequest  [API 1-22, removed in 23]
+ shutdown(): void  [API 1-22, removed in 23]
```

## BasicPoolEntry — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ BasicPoolEntry(ClientConnectionOperator, HttpRoute, ReferenceQueue)  [API 1-22, removed in 23]
+ getConnection(): OperatedClientConnection  [API 1-22, removed in 23]
+ getPlannedRoute(): HttpRoute  [API 1-22, removed in 23]
+ getWeakRef(): BasicPoolEntryRef  [API 1-22, removed in 23]
```

## BasicPoolEntryRef — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicPoolEntryRef(BasicPoolEntry, ReferenceQueue)  [API 1-22, removed in 23]
+ getRoute(): HttpRoute  [API 1-22, removed in 23]
```

## BasicPooledConnAdapter — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicPooledConnAdapter(ThreadSafeClientConnManager, AbstractPoolEntry)  [API 1-22, removed in 23]
+ getPoolEntry(): AbstractPoolEntry  [API 1-22, removed in 23]
```

## ConnPoolByRoute — bundled library; API 1-22, removed in 23, deprecated in 22 (14 +, 0 *, 0 ~, 0 -)

```
+ ConnPoolByRoute(ClientConnectionOperator, HttpParams)  [API 1-22, removed in 23]
+ createEntry(RouteSpecificPool, ClientConnectionOperator): BasicPoolEntry  [API 1-22, removed in 23]
+ createFreeConnQueue(): Queue  [API 1-22, removed in 23]
+ createRouteToPoolMap(): Map  [API 1-22, removed in 23]
+ createWaitingThreadQueue(): Queue  [API 1-22, removed in 23]
+ deleteEntry(BasicPoolEntry): void  [API 1-22, removed in 23]
+ deleteLeastUsedEntry(): void  [API 1-22, removed in 23]
+ freeConnections [field]  [API 1-22, removed in 23]
+ getConnectionsInPool(HttpRoute): int  [API 1-22, removed in 23]
+ getEntryBlocking(HttpRoute, Object, long, TimeUnit, WaitingThreadAborter): BasicPoolEntry  [API 1-22, removed in 23]
+ getFreeEntry(RouteSpecificPool, Object): BasicPoolEntry  [API 1-22, removed in 23]
+ getRoutePool(HttpRoute, boolean): RouteSpecificPool  [API 1-22, removed in 23]
- maxTotalConnections [field]  [API 1-22, removed in 23]
+ newRouteSpecificPool(HttpRoute): RouteSpecificPool  [API 1-22, removed in 23]
+ newWaitingThread(Condition, RouteSpecificPool): WaitingThread  [API 1-22, removed in 23]
+ notifyWaitingThread(RouteSpecificPool): void  [API 1-22, removed in 23]
+ operator [field]  [API 1-22, removed in 23]
+ routeToPool [field]  [API 1-22, removed in 23]
+ waitingThreads [field]  [API 1-22, removed in 23]
```

## PoolEntryRequest — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ abortRequest(): void  [API 1-22, removed in 23]
+ getPoolEntry(long, TimeUnit): BasicPoolEntry  [API 1-22, removed in 23]
```

## RefQueueHandler — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ handleReference(Reference): void  [API 1-22, removed in 23]
```

## RefQueueWorker — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ RefQueueWorker(ReferenceQueue, RefQueueHandler)  [API 1-22, removed in 23]
+ refHandler [field]  [API 1-22, removed in 23]
+ refQueue [field]  [API 1-22, removed in 23]
+ shutdown(): void  [API 1-22, removed in 23]
+ workerThread [field]  [API 1-22, removed in 23]
```

## RouteSpecificPool — bundled library; API 1-22, removed in 23, deprecated in 22 (15 +, 0 *, 0 ~, 0 -)

```
+ RouteSpecificPool(HttpRoute, int)  [API 1-22, removed in 23]
+ allocEntry(Object): BasicPoolEntry  [API 1-22, removed in 23]
+ createdEntry(BasicPoolEntry): void  [API 1-22, removed in 23]
+ deleteEntry(BasicPoolEntry): boolean  [API 1-22, removed in 23]
+ dropEntry(): void  [API 1-22, removed in 23]
+ freeEntries [field]  [API 1-22, removed in 23]
+ freeEntry(BasicPoolEntry): void  [API 1-22, removed in 23]
+ getCapacity(): int  [API 1-22, removed in 23]
+ getEntryCount(): int  [API 1-22, removed in 23]
+ getMaxEntries(): int  [API 1-22, removed in 23]
+ getRoute(): HttpRoute  [API 1-22, removed in 23]
+ hasThread(): boolean  [API 1-22, removed in 23]
+ isUnused(): boolean  [API 1-22, removed in 23]
+ maxEntries [field]  [API 1-22, removed in 23]
+ nextThread(): WaitingThread  [API 1-22, removed in 23]
+ numEntries [field]  [API 1-22, removed in 23]
+ queueThread(WaitingThread): void  [API 1-22, removed in 23]
+ removeThread(WaitingThread): void  [API 1-22, removed in 23]
+ route [field]  [API 1-22, removed in 23]
+ waitingThreads [field]  [API 1-22, removed in 23]
```

## ThreadSafeClientConnManager — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ ThreadSafeClientConnManager(HttpParams, SchemeRegistry)  [API 1-22, removed in 23]
+ connOperator [field]  [API 1-22, removed in 23]
+ connectionPool [field]  [API 1-22, removed in 23]
+ createConnectionOperator(SchemeRegistry): ClientConnectionOperator  [API 1-22, removed in 23]
+ createConnectionPool(HttpParams): AbstractConnPool  [API 1-22, removed in 23]
+ getConnectionsInPool(): int  [API 1-22, removed in 23]
+ getConnectionsInPool(HttpRoute): int  [API 1-22, removed in 23]
+ schemeRegistry [field]  [API 1-22, removed in 23]
```

## WaitingThread — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ WaitingThread(Condition, RouteSpecificPool)  [API 1-22, removed in 23]
+ await(Date): boolean  [API 1-22, removed in 23]
+ getCondition(): Condition  [API 1-22, removed in 23]
+ getPool(): RouteSpecificPool  [API 1-22, removed in 23]
+ getThread(): Thread  [API 1-22, removed in 23]
+ interrupt(): void  [API 1-22, removed in 23]
+ wakeup(): void  [API 1-22, removed in 23]
```

## WaitingThreadAborter — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ WaitingThreadAborter()  [API 1-22, removed in 23]
+ abort(): void  [API 1-22, removed in 23]
+ setWaitingThread(WaitingThread): void  [API 1-22, removed in 23]
```


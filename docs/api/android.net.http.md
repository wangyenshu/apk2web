# android.net.http

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AndroidHttpClient — framework; API 8-22, removed in 23, deprecated in 22 (6 +, 0 *, 3 ~, 1 -)

```
~ close(): void  [API 8-22, removed in 23]
~ disableCurlLogging(): void  [API 8-22, removed in 23]
~ enableCurlLogging(String, int): void  [API 8-22, removed in 23]
+ getCompressedEntity(byte[], ContentResolver): AbstractHttpEntity  [API 8-22, removed in 23]
- getMinGzipSize(ContentResolver): long  [API 8-22, removed in 23]
+ getUngzippedContent(HttpEntity): InputStream  [API 8-22, removed in 23]
+ modifyRequestToAcceptGzipResponse(HttpRequest): void  [API 8-22, removed in 23]
+ newInstance(String): AndroidHttpClient  [API 8-22, removed in 23]
+ newInstance(String, Context): AndroidHttpClient  [API 8-22, removed in 23]
+ parseDate(String): long  [API 8-22, removed in 23]
```

## BidirectionalStream — declaration; API 34+ (15 +, 0 *, 0 ~, 0 -)

```
+ BidirectionalStream()  [API 34+]
+ cancel(): void  [API 34+]
+ flush(): void  [API 34+]
+ getHeaders(): HeaderBlock  [API 34+]
+ getHttpMethod(): String  [API 34+]
+ getPriority(): int  [API 34+]
+ getTrafficStatsTag(): int  [API 34+]
+ getTrafficStatsUid(): int  [API 34+]
+ hasTrafficStatsTag(): boolean  [API 34+]
+ hasTrafficStatsUid(): boolean  [API 34+]
+ isDelayRequestHeadersUntilFirstFlushEnabled(): boolean  [API 34+]
+ isDone(): boolean  [API 34+]
+ read(ByteBuffer): void  [API 34+]
+ start(): void  [API 34+]
+ write(ByteBuffer, boolean): void  [API 34+]
```

## BidirectionalStream.Builder — declaration; API 34+ (8 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 34+]
+ addHeader(String, String): BidirectionalStream.Builder  [API 34+]
+ build(): BidirectionalStream  [API 34+]
+ setDelayRequestHeadersUntilFirstFlushEnabled(boolean): BidirectionalStream.Builder  [API 34+]
+ setHttpMethod(String): BidirectionalStream.Builder  [API 34+]
+ setPriority(int): BidirectionalStream.Builder  [API 34+]
+ setTrafficStatsTag(int): BidirectionalStream.Builder  [API 34+]
+ setTrafficStatsUid(int): BidirectionalStream.Builder  [API 34+]
```

## BidirectionalStream.Callback — declaration; API 34+ (8 +, 0 *, 0 ~, 0 -)

```
+ onCanceled(BidirectionalStream, UrlResponseInfo): void  [API 34+]
+ onFailed(BidirectionalStream, UrlResponseInfo, HttpException): void  [API 34+]
+ onReadCompleted(BidirectionalStream, UrlResponseInfo, ByteBuffer, boolean): void  [API 34+]
+ onResponseHeadersReceived(BidirectionalStream, UrlResponseInfo): void  [API 34+]
+ onResponseTrailersReceived(BidirectionalStream, UrlResponseInfo, HeaderBlock): void  [API 34+]
+ onStreamReady(BidirectionalStream): void  [API 34+]
+ onSucceeded(BidirectionalStream, UrlResponseInfo): void  [API 34+]
+ onWriteCompleted(BidirectionalStream, UrlResponseInfo, ByteBuffer, boolean): void  [API 34+]
```

## CallbackException — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ CallbackException(String, Throwable)  [API 34+]
```

## ConnectionMigrationOptions — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* getAllowNonDefaultNetworkUsage(): int  [API 34+]
* getDefaultNetworkMigration(): int  [API 34+]
* getPathDegradationMigration(): int  [API 34+]
```

## ConnectionMigrationOptions.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): ConnectionMigrationOptions  [API 34+]
* setAllowNonDefaultNetworkUsage(int): ConnectionMigrationOptions.Builder  [API 34+]
* setDefaultNetworkMigration(int): ConnectionMigrationOptions.Builder  [API 34+]
* setPathDegradationMigration(int): ConnectionMigrationOptions.Builder  [API 34+]
```

## DnsOptions — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* getPersistHostCache(): int  [API 34+]
* getPersistHostCachePeriod(): Duration  [API 34+]
* getPreestablishConnectionsToStaleDnsResults(): int  [API 34+]
* getStaleDns(): int  [API 34+]
* getStaleDnsOptions(): DnsOptions.StaleDnsOptions  [API 34+]
* getUseHttpStackDnsResolver(): int  [API 34+]
```

## DnsOptions.Builder — declaration; API 34+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): DnsOptions  [API 34+]
* setPersistHostCache(int): DnsOptions.Builder  [API 34+]
* setPersistHostCachePeriod(Duration): DnsOptions.Builder  [API 34+]
* setPreestablishConnectionsToStaleDnsResults(int): DnsOptions.Builder  [API 34+]
* setStaleDns(int): DnsOptions.Builder  [API 34+]
* setStaleDnsOptions(DnsOptions.StaleDnsOptions): DnsOptions.Builder  [API 34+]
* setUseHttpStackDnsResolver(int): DnsOptions.Builder  [API 34+]
```

## DnsOptions.StaleDnsOptions — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* getAllowCrossNetworkUsage(): int  [API 34+]
* getFreshLookupTimeout(): Duration  [API 34+]
* getMaxExpiredDelay(): Duration  [API 34+]
* getUseStaleOnNameNotResolved(): int  [API 34+]
```

## DnsOptions.StaleDnsOptions.Builder — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): DnsOptions.StaleDnsOptions  [API 34+]
* setAllowCrossNetworkUsage(int): DnsOptions.StaleDnsOptions.Builder  [API 34+]
* setFreshLookupTimeout(Duration): DnsOptions.StaleDnsOptions.Builder  [API 34+]
* setMaxExpiredDelay(Duration): DnsOptions.StaleDnsOptions.Builder  [API 34+]
* setUseStaleOnNameNotResolved(int): DnsOptions.StaleDnsOptions.Builder  [API 34+]
```

## HeaderBlock — declaration; API 34+ (3 +, 0 *, 0 ~, 0 -)

```
+ HeaderBlock()  [API 34+]
+ getAsList(): List  [API 34+]
+ getAsMap(): Map  [API 34+]
```

## HttpEngine — declaration; API 34+ (5 +, 0 *, 0 ~, 2 -)

```
- bindToNetwork(Network): void  [API 34+]
+ createUrlStreamHandlerFactory(): URLStreamHandlerFactory  [API 34+]
- getVersionString(): String [static]  [API 34+]
+ newBidirectionalStreamBuilder(String, Executor, BidirectionalStream.Callback): BidirectionalStream.Builder  [API 34+]
+ newUrlRequestBuilder(String, Executor, UrlRequest.Callback): UrlRequest.Builder  [API 34+]
+ openConnection(URL): URLConnection  [API 34+]
+ shutdown(): void  [API 34+]
```

## HttpEngine.Builder — declaration; API 34+ (0 +, 0 *, 0 ~, 15 -)

```
- Builder(Context)  [API 34+]
- addPublicKeyPins(String, Set, boolean, Instant): HttpEngine.Builder  [API 34+]
- addQuicHint(String, int, int): HttpEngine.Builder  [API 34+]
- build(): HttpEngine  [API 34+]
- getDefaultUserAgent(): String  [API 34+]
- setConnectionMigrationOptions(ConnectionMigrationOptions): HttpEngine.Builder  [API 34+]
- setDnsOptions(DnsOptions): HttpEngine.Builder  [API 34+]
- setEnableBrotli(boolean): HttpEngine.Builder  [API 34+]
- setEnableHttp2(boolean): HttpEngine.Builder  [API 34+]
- setEnableHttpCache(int, long): HttpEngine.Builder  [API 34+]
- setEnablePublicKeyPinningBypassForLocalTrustAnchors(boolean): HttpEngine.Builder  [API 34+]
- setEnableQuic(boolean): HttpEngine.Builder  [API 34+]
- setQuicOptions(QuicOptions): HttpEngine.Builder  [API 34+]
- setStoragePath(String): HttpEngine.Builder  [API 34+]
- setUserAgent(String): HttpEngine.Builder  [API 34+]
```

## HttpException — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ HttpException(String, Throwable)  [API 34+]
```

## HttpResponseCache — declaration; API 14+ (0 +, 0 *, 0 ~, 12 -)

```
- close(): void  [API 14+]
- delete(): void  [API 14+]
- flush(): void  [API 14+]
- get(URI, String, Map): CacheResponse  [API 14+]
- getHitCount(): int  [API 14+]
- getInstalled(): HttpResponseCache [static]  [API 14+]
- getNetworkCount(): int  [API 14+]
- getRequestCount(): int  [API 14+]
- install(File, long): HttpResponseCache [static]  [API 14+]
- maxSize(): long  [API 14+]
- put(URI, URLConnection): CacheRequest  [API 14+]
- size(): long  [API 14+]
```

## InlineExecutionProhibitedException — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ InlineExecutionProhibitedException()  [API 34+]
```

## NetworkException — declaration; API 34+ (3 +, 0 *, 0 ~, 0 -)

```
+ NetworkException(String, Throwable)  [API 34+]
+ getErrorCode(): int  [API 34+]
+ isImmediatelyRetryable(): boolean  [API 34+]
```

## QuicException — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ QuicException(String, Throwable)  [API 34+]
```

## QuicOptions — declaration; API 34+ (0 +, 3 *, 0 ~, 2 -)

```
- getAllowedQuicHosts(): Set  [API 34+]
* getHandshakeUserAgent(): String  [API 34+]
* getIdleConnectionTimeout(): Duration  [API 34+]
* getInMemoryServerConfigsCacheSize(): int  [API 34+]
- hasInMemoryServerConfigsCacheSize(): boolean  [API 34+]
```

## QuicOptions.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 1 -)

```
* Builder()  [API 34+]
- addAllowedQuicHost(String): QuicOptions.Builder  [API 34+]
* build(): QuicOptions  [API 34+]
* setHandshakeUserAgent(String): QuicOptions.Builder  [API 34+]
* setIdleConnectionTimeout(Duration): QuicOptions.Builder  [API 34+]
* setInMemoryServerConfigsCacheSize(int): QuicOptions.Builder  [API 34+]
```

## SslCertificate — declaration; API 1+ (0 +, 0 *, 0 ~, 13 -)

```
- SslCertificate(String, String, Date, Date)  [API 8+, deprecated in 15]
- SslCertificate(String, String, String, String)  [API 1+, deprecated in 15]
- SslCertificate(X509Certificate)  [API 1+]
- getIssuedBy(): SslCertificate.DName  [API 1+]
- getIssuedTo(): SslCertificate.DName  [API 1+]
- getValidNotAfter(): String  [API 1+, deprecated in 15]
- getValidNotAfterDate(): Date  [API 8+]
- getValidNotBefore(): String  [API 1+, deprecated in 15]
- getValidNotBeforeDate(): Date  [API 8+]
- getX509Certificate(): X509Certificate  [API 29+]
- restoreState(Bundle): SslCertificate [static]  [API 1+]
- saveState(SslCertificate): Bundle [static]  [API 1+]
- toString(): String  [API 1+]
```

## SslCertificate.DName — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- DName(SslCertificate, String)  [API 1+]
- getCName(): String  [API 1+]
- getDName(): String  [API 1+]
- getOName(): String  [API 1+]
- getUName(): String  [API 1+]
```

## SslError — declaration; API 8+ (0 +, 0 *, 0 ~, 10 -)

```
- SslError(int, SslCertificate)  [API 8+, deprecated in 15]
- SslError(int, SslCertificate, String)  [API 14+]
- SslError(int, X509Certificate)  [API 8+, deprecated in 15]
- SslError(int, X509Certificate, String)  [API 14+]
- addError(int): boolean  [API 8+]
- getCertificate(): SslCertificate  [API 8+]
- getPrimaryError(): int  [API 8+]
- getUrl(): String  [API 14+]
- hasError(int): boolean  [API 8+]
- toString(): String  [API 8+]
```

## UploadDataProvider — declaration; API 34+ (3 +, 0 *, 0 ~, 2 -)

```
- UploadDataProvider()  [API 34+]
- close(): void  [API 34+]
+ getLength(): long  [API 34+]
+ read(UploadDataSink, ByteBuffer): void  [API 34+]
+ rewind(UploadDataSink): void  [API 34+]
```

## UploadDataSink — declaration; API 34+ (5 +, 0 *, 0 ~, 0 -)

```
+ UploadDataSink()  [API 34+]
+ onReadError(Exception): void  [API 34+]
+ onReadSucceeded(boolean): void  [API 34+]
+ onRewindError(Exception): void  [API 34+]
+ onRewindSucceeded(): void  [API 34+]
```

## UrlRequest — declaration; API 34+ (15 +, 0 *, 0 ~, 0 -)

```
+ cancel(): void  [API 34+]
+ followRedirect(): void  [API 34+]
+ getHeaders(): HeaderBlock  [API 34+]
+ getHttpMethod(): String  [API 34+]
+ getPriority(): int  [API 34+]
+ getStatus(UrlRequest.StatusListener): void  [API 34+]
+ getTrafficStatsTag(): int  [API 34+]
+ getTrafficStatsUid(): int  [API 34+]
+ hasTrafficStatsTag(): boolean  [API 34+]
+ hasTrafficStatsUid(): boolean  [API 34+]
+ isCacheDisabled(): boolean  [API 34+]
+ isDirectExecutorAllowed(): boolean  [API 34+]
+ isDone(): boolean  [API 34+]
+ read(ByteBuffer): void  [API 34+]
+ start(): void  [API 34+]
```

## UrlRequest.Builder — declaration; API 34+ (10 +, 0 *, 0 ~, 0 -)

```
+ addHeader(String, String): UrlRequest.Builder  [API 34+]
+ bindToNetwork(Network): UrlRequest.Builder  [API 34+]
+ build(): UrlRequest  [API 34+]
+ setCacheDisabled(boolean): UrlRequest.Builder  [API 34+]
+ setDirectExecutorAllowed(boolean): UrlRequest.Builder  [API 34+]
+ setHttpMethod(String): UrlRequest.Builder  [API 34+]
+ setPriority(int): UrlRequest.Builder  [API 34+]
+ setTrafficStatsTag(int): UrlRequest.Builder  [API 34+]
+ setTrafficStatsUid(int): UrlRequest.Builder  [API 34+]
+ setUploadDataProvider(UploadDataProvider, Executor): UrlRequest.Builder  [API 34+]
```

## UrlRequest.Callback — declaration; API 34+ (6 +, 0 *, 0 ~, 0 -)

```
+ onCanceled(UrlRequest, UrlResponseInfo): void  [API 34+]
+ onFailed(UrlRequest, UrlResponseInfo, HttpException): void  [API 34+]
+ onReadCompleted(UrlRequest, UrlResponseInfo, ByteBuffer): void  [API 34+]
+ onRedirectReceived(UrlRequest, UrlResponseInfo, String): void  [API 34+]
+ onResponseStarted(UrlRequest, UrlResponseInfo): void  [API 34+]
+ onSucceeded(UrlRequest, UrlResponseInfo): void  [API 34+]
```

## UrlRequest.Status — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## UrlRequest.StatusListener — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onStatus(int): void  [API 34+]
```

## UrlResponseInfo — declaration; API 34+ (9 +, 0 *, 0 ~, 0 -)

```
+ UrlResponseInfo()  [API 34+]
+ getHeaders(): HeaderBlock  [API 34+]
+ getHttpStatusCode(): int  [API 34+]
+ getHttpStatusText(): String  [API 34+]
+ getNegotiatedProtocol(): String  [API 34+]
+ getReceivedByteCount(): long  [API 34+]
+ getUrl(): String  [API 34+]
+ getUrlChain(): List  [API 34+]
+ wasCached(): boolean  [API 34+]
```

## X509TrustManagerExtensions — declaration; API 17+ (0 +, 0 *, 0 ~, 4 -)

```
- X509TrustManagerExtensions(X509TrustManager)  [API 17+]
- checkServerTrusted(X509Certificate[], String, String): List  [API 17+]
- isSameTrustConfiguration(String, String): boolean  [API 28+]
- isUserAddedCertificate(X509Certificate): boolean  [API 21+]
```


# java.nio.channels

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AcceptPendingException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ AcceptPendingException()  [API 26+]
```

## AlreadyBoundException — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ AlreadyBoundException()  [API 24+]
```

## AlreadyConnectedException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ AlreadyConnectedException()  [API 1+]
```

## AsynchronousByteChannel — declaration; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ read(ByteBuffer): Future  [API 26+]
+ read(ByteBuffer, Object, CompletionHandler): void  [API 26+]
+ write(ByteBuffer): Future  [API 26+]
+ write(ByteBuffer, Object, CompletionHandler): void  [API 26+]
```

## AsynchronousChannel — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 26+]
```

## AsynchronousChannelGroup — declaration; API 26+ (5 +, 0 *, 0 ~, 5 -)

```
- AsynchronousChannelGroup(AsynchronousChannelProvider)  [API 26+]
+ awaitTermination(long, TimeUnit): boolean  [API 26+]
+ isShutdown(): boolean  [API 26+]
+ isTerminated(): boolean  [API 26+]
- provider(): AsynchronousChannelProvider  [API 26+]
+ shutdown(): void  [API 26+]
+ shutdownNow(): void  [API 26+]
- withCachedThreadPool(ExecutorService, int): AsynchronousChannelGroup [static]  [API 26+]
- withFixedThreadPool(int, ThreadFactory): AsynchronousChannelGroup [static]  [API 26+]
- withThreadPool(ExecutorService): AsynchronousChannelGroup [static]  [API 26+]
```

## AsynchronousCloseException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ AsynchronousCloseException()  [API 1+]
```

## AsynchronousFileChannel — missing; API 26+ (0 +, 0 *, 0 ~, 16 -)

```
- AsynchronousFileChannel()  [API 26+]
- force(boolean): void  [API 26+]
- lock(): Future  [API 26+]
- lock(Object, CompletionHandler): void  [API 26+]
- lock(long, long, boolean): Future  [API 26+]
- lock(long, long, boolean, Object, CompletionHandler): void  [API 26+]
- open(Path, OpenOption[]): AsynchronousFileChannel [static]  [API 26+]
- open(Path, Set, ExecutorService, FileAttribute[]): AsynchronousFileChannel [static]  [API 26+]
- read(ByteBuffer, long): Future  [API 26+]
- read(ByteBuffer, long, Object, CompletionHandler): void  [API 26+]
- size(): long  [API 26+]
- truncate(long): AsynchronousFileChannel  [API 26+]
- tryLock(): FileLock  [API 26+]
- tryLock(long, long, boolean): FileLock  [API 26+]
- write(ByteBuffer, long): Future  [API 26+]
- write(ByteBuffer, long, Object, CompletionHandler): void  [API 26+]
```

## AsynchronousServerSocketChannel — declaration; API 26+ (5 +, 0 *, 0 ~, 5 -)

```
- AsynchronousServerSocketChannel(AsynchronousChannelProvider)  [API 26+]
+ accept(): Future  [API 26+]
+ accept(Object, CompletionHandler): void  [API 26+]
- bind(SocketAddress): AsynchronousServerSocketChannel  [API 26+]
+ bind(SocketAddress, int): AsynchronousServerSocketChannel  [API 26+]
+ getLocalAddress(): SocketAddress  [API 26+]
- open(): AsynchronousServerSocketChannel [static]  [API 26+]
- open(AsynchronousChannelGroup): AsynchronousServerSocketChannel [static]  [API 26+]
- provider(): AsynchronousChannelProvider  [API 26+]
+ setOption(SocketOption, Object): AsynchronousServerSocketChannel  [API 26+]
```

## AsynchronousSocketChannel — declaration; API 26+ (14 +, 0 *, 0 ~, 6 -)

```
- AsynchronousSocketChannel(AsynchronousChannelProvider)  [API 26+]
+ bind(SocketAddress): AsynchronousSocketChannel  [API 26+]
+ connect(SocketAddress): Future  [API 26+]
+ connect(SocketAddress, Object, CompletionHandler): void  [API 26+]
+ getLocalAddress(): SocketAddress  [API 26+]
+ getRemoteAddress(): SocketAddress  [API 26+]
- open(): AsynchronousSocketChannel [static]  [API 26+]
- open(AsynchronousChannelGroup): AsynchronousSocketChannel [static]  [API 26+]
- provider(): AsynchronousChannelProvider  [API 26+]
+ read(ByteBuffer): Future  [API 26+]
- read(ByteBuffer, Object, CompletionHandler): void  [API 26+]
+ read(ByteBuffer, long, TimeUnit, Object, CompletionHandler): void  [API 26+]
+ read(ByteBuffer[], int, int, long, TimeUnit, Object, CompletionHandler): void  [API 26+]
+ setOption(SocketOption, Object): AsynchronousSocketChannel  [API 26+]
+ shutdownInput(): AsynchronousSocketChannel  [API 26+]
+ shutdownOutput(): AsynchronousSocketChannel  [API 26+]
+ write(ByteBuffer): Future  [API 26+]
- write(ByteBuffer, Object, CompletionHandler): void  [API 26+]
+ write(ByteBuffer, long, TimeUnit, Object, CompletionHandler): void  [API 26+]
+ write(ByteBuffer[], int, int, long, TimeUnit, Object, CompletionHandler): void  [API 26+]
```

## ByteChannel — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## CancelledKeyException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ CancelledKeyException()  [API 1+]
```

## Channel — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 1+]
+ isOpen(): boolean  [API 1+]
```

## Channels — declaration; API 1+ (0 +, 0 *, 0 ~, 12 -)

```
- newChannel(InputStream): ReadableByteChannel [static]  [API 1+]
- newChannel(OutputStream): WritableByteChannel [static]  [API 1+]
- newInputStream(AsynchronousByteChannel): InputStream [static]  [API 26+]
- newInputStream(ReadableByteChannel): InputStream [static]  [API 1+]
- newOutputStream(AsynchronousByteChannel): OutputStream [static]  [API 26+]
- newOutputStream(WritableByteChannel): OutputStream [static]  [API 1+]
- newReader(ReadableByteChannel, Charset): Reader [static]  [API 33+]
- newReader(ReadableByteChannel, CharsetDecoder, int): Reader [static]  [API 1+]
- newReader(ReadableByteChannel, String): Reader [static]  [API 1+]
- newWriter(WritableByteChannel, Charset): Writer [static]  [API 33+]
- newWriter(WritableByteChannel, CharsetEncoder, int): Writer [static]  [API 1+]
- newWriter(WritableByteChannel, String): Writer [static]  [API 1+]
```

## ClosedByInterruptException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ClosedByInterruptException()  [API 1+]
```

## ClosedChannelException — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ClosedChannelException()  [API 1+]
```

## ClosedSelectorException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ClosedSelectorException()  [API 1+]
```

## CompletionHandler — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ completed(Object, Object): void  [API 26+]
+ failed(Throwable, Object): void  [API 26+]
```

## ConnectionPendingException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ConnectionPendingException()  [API 1+]
```

## DatagramChannel — declaration; API 1+ (14 +, 0 *, 0 ~, 6 -)

```
- DatagramChannel(SelectorProvider)  [API 1+]
+ bind(SocketAddress): DatagramChannel  [API 24+]
+ connect(SocketAddress): DatagramChannel  [API 1+]
+ disconnect(): DatagramChannel  [API 1+]
+ getLocalAddress(): SocketAddress  [API 1+]
+ getRemoteAddress(): SocketAddress  [API 24+]
+ isConnected(): boolean  [API 1+]
- open(): DatagramChannel [static]  [API 1+]
- open(ProtocolFamily): DatagramChannel [static]  [API 24+]
+ read(ByteBuffer): int  [API 1+]
- read(ByteBuffer[]): long  [API 1+]
+ read(ByteBuffer[], int, int): long  [API 1+]
+ receive(ByteBuffer): SocketAddress  [API 1+]
+ send(ByteBuffer, SocketAddress): int  [API 1+]
+ setOption(SocketOption, Object): DatagramChannel  [API 24+]
+ socket(): DatagramSocket  [API 1+]
- validOps(): int  [API 1+]
+ write(ByteBuffer): int  [API 1+]
- write(ByteBuffer[]): long  [API 1+]
+ write(ByteBuffer[], int, int): long  [API 1+]
```

## FileChannel — missing; API 1+ (0 +, 0 *, 0 ~, 23 -)

```
- FileChannel()  [API 1+]
- force(boolean): void  [API 1+]
- lock(): FileLock  [API 1+]
- lock(long, long, boolean): FileLock  [API 1+]
- map(FileChannel.MapMode, long, long): MappedByteBuffer  [API 1+]
- open(Path, OpenOption[]): FileChannel [static]  [API 26+]
- open(Path, Set, FileAttribute[]): FileChannel [static]  [API 26+]
- position(): long  [API 1+]
- position(long): FileChannel  [API 1+]
- read(ByteBuffer): int  [API 1+]
- read(ByteBuffer, long): int  [API 1+]
- read(ByteBuffer[]): long  [API 1+]
- read(ByteBuffer[], int, int): long  [API 1+]
- size(): long  [API 1+]
- transferFrom(ReadableByteChannel, long, long): long  [API 1+]
- transferTo(long, long, WritableByteChannel): long  [API 1+]
- truncate(long): FileChannel  [API 1+]
- tryLock(): FileLock  [API 1+]
- tryLock(long, long, boolean): FileLock  [API 1+]
- write(ByteBuffer): int  [API 1+]
- write(ByteBuffer, long): int  [API 1+]
- write(ByteBuffer[]): long  [API 1+]
- write(ByteBuffer[], int, int): long  [API 1+]
```

## FileChannel.MapMode — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- PRIVATE [field]  [API 1+]
- READ_ONLY [field]  [API 1+]
- READ_WRITE [field]  [API 1+]
- toString(): String  [API 1+]
```

## FileLock — missing; API 1+ (0 +, 0 *, 0 ~, 12 -)

```
- FileLock(AsynchronousFileChannel, long, long, boolean)  [API 26+]
- FileLock(FileChannel, long, long, boolean)  [API 1+]
- acquiredBy(): Channel  [API 24+]
- channel(): FileChannel  [API 1+]
- close(): void  [API 1+]
- isShared(): boolean  [API 1+]
- isValid(): boolean  [API 1+]
- overlaps(long, long): boolean  [API 1+]
- position(): long  [API 1+]
- release(): void  [API 1+]
- size(): long  [API 1+]
- toString(): String  [API 1+]
```

## FileLockInterruptionException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ FileLockInterruptionException()  [API 1+]
```

## GatheringByteChannel — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ write(ByteBuffer[]): long  [API 1+]
+ write(ByteBuffer[], int, int): long  [API 1+]
```

## IllegalBlockingModeException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ IllegalBlockingModeException()  [API 1+]
```

## IllegalChannelGroupException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ IllegalChannelGroupException()  [API 26+]
```

## IllegalSelectorException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ IllegalSelectorException()  [API 1+]
```

## InterruptedByTimeoutException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ InterruptedByTimeoutException()  [API 26+]
```

## InterruptibleChannel — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 1+]
```

## MembershipKey — declaration; API 26+ (9 +, 0 *, 0 ~, 0 -)

```
+ MembershipKey()  [API 26+]
+ block(InetAddress): MembershipKey  [API 26+]
+ channel(): MulticastChannel  [API 26+]
+ drop(): void  [API 26+]
+ group(): InetAddress  [API 26+]
+ isValid(): boolean  [API 26+]
+ networkInterface(): NetworkInterface  [API 26+]
+ sourceAddress(): InetAddress  [API 26+]
+ unblock(InetAddress): MembershipKey  [API 26+]
```

## MulticastChannel — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 26+]
+ join(InetAddress, NetworkInterface): MembershipKey  [API 26+]
+ join(InetAddress, NetworkInterface, InetAddress): MembershipKey  [API 26+]
```

## NetworkChannel — declaration; API 24+ (5 +, 0 *, 0 ~, 0 -)

```
+ bind(SocketAddress): NetworkChannel  [API 24+]
+ getLocalAddress(): SocketAddress  [API 24+]
+ getOption(SocketOption): Object  [API 24+]
+ setOption(SocketOption, Object): NetworkChannel  [API 24+]
+ supportedOptions(): Set  [API 24+]
```

## NoConnectionPendingException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ NoConnectionPendingException()  [API 1+]
```

## NonReadableChannelException — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ NonReadableChannelException()  [API 1+]
```

## NonWritableChannelException — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ NonWritableChannelException()  [API 1+]
```

## NotYetBoundException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ NotYetBoundException()  [API 1+]
```

## NotYetConnectedException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ NotYetConnectedException()  [API 1+]
```

## OverlappingFileLockException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ OverlappingFileLockException()  [API 1+]
```

## Pipe — declaration; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
- Pipe()  [API 1+]
- open(): Pipe [static]  [API 1+]
+ sink(): Pipe.SinkChannel  [API 1+]
+ source(): Pipe.SourceChannel  [API 1+]
```

## Pipe.SinkChannel — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- SinkChannel(SelectorProvider)  [API 1+]
- validOps(): int  [API 1+]
```

## Pipe.SourceChannel — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- SourceChannel(SelectorProvider)  [API 1+]
- validOps(): int  [API 1+]
```

## ReadPendingException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ ReadPendingException()  [API 26+]
```

## ReadableByteChannel — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ read(ByteBuffer): int  [API 1+]
```

## ScatteringByteChannel — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ read(ByteBuffer[]): long  [API 1+]
+ read(ByteBuffer[], int, int): long  [API 1+]
```

## SeekableByteChannel — TeaVM class library; API 24+ (6 +, 0 *, 0 ~, 0 -)

```
+ position(): long  [API 24+]
+ position(long): SeekableByteChannel  [API 24+]
+ read(ByteBuffer): int  [API 24+]
+ size(): long  [API 24+]
+ truncate(long): SeekableByteChannel  [API 24+]
+ write(ByteBuffer): int  [API 24+]
```

## SelectableChannel — declaration; API 1+ (8 +, 0 *, 0 ~, 2 -)

```
- SelectableChannel()  [API 1+]
+ blockingLock(): Object  [API 1+]
+ configureBlocking(boolean): SelectableChannel  [API 1+]
+ isBlocking(): boolean  [API 1+]
+ isRegistered(): boolean  [API 1+]
+ keyFor(Selector): SelectionKey  [API 1+]
+ provider(): SelectorProvider  [API 1+]
- register(Selector, int): SelectionKey  [API 1+]
+ register(Selector, int, Object): SelectionKey  [API 1+]
+ validOps(): int  [API 1+]
```

## SelectionKey — declaration; API 1+ (7 +, 0 *, 0 ~, 9 -)

```
- SelectionKey()  [API 1+]
- attach(Object): Object  [API 1+]
- attachment(): Object  [API 1+]
+ cancel(): void  [API 1+]
+ channel(): SelectableChannel  [API 1+]
+ interestOps(): int  [API 1+]
+ interestOps(int): SelectionKey  [API 1+]
- interestOpsAnd(int): int  [API 33+]
- interestOpsOr(int): int  [API 33+]
- isAcceptable(): boolean  [API 1+]
- isConnectable(): boolean  [API 1+]
- isReadable(): boolean  [API 1+]
+ isValid(): boolean  [API 1+]
- isWritable(): boolean  [API 1+]
+ readyOps(): int  [API 1+]
+ selector(): Selector  [API 1+]
```

## Selector — declaration; API 1+ (9 +, 0 *, 0 ~, 5 -)

```
- Selector()  [API 1+]
+ close(): void  [API 1+]
+ isOpen(): boolean  [API 1+]
+ keys(): Set  [API 1+]
- open(): Selector [static]  [API 1+]
+ provider(): SelectorProvider  [API 1+]
+ select(): int  [API 1+]
- select(Consumer): int  [API 33+]
- select(Consumer, long): int  [API 33+]
+ select(long): int  [API 1+]
+ selectNow(): int  [API 1+]
- selectNow(Consumer): int  [API 33+]
+ selectedKeys(): Set  [API 1+]
+ wakeup(): Selector  [API 1+]
```

## ServerSocketChannel — declaration; API 1+ (5 +, 0 *, 0 ~, 4 -)

```
- ServerSocketChannel(SelectorProvider)  [API 1+]
+ accept(): SocketChannel  [API 1+]
- bind(SocketAddress): ServerSocketChannel  [API 24+]
+ bind(SocketAddress, int): ServerSocketChannel  [API 24+]
+ getLocalAddress(): SocketAddress  [API 1+]
- open(): ServerSocketChannel [static]  [API 1+]
+ setOption(SocketOption, Object): ServerSocketChannel  [API 24+]
+ socket(): ServerSocket  [API 1+]
- validOps(): int  [API 1+]
```

## ShutdownChannelGroupException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ ShutdownChannelGroupException()  [API 26+]
```

## SocketChannel — declaration; API 1+ (15 +, 0 *, 0 ~, 6 -)

```
- SocketChannel(SelectorProvider)  [API 1+]
+ bind(SocketAddress): SocketChannel  [API 24+]
+ connect(SocketAddress): boolean  [API 1+]
+ finishConnect(): boolean  [API 1+]
+ getLocalAddress(): SocketAddress  [API 1+]
+ getRemoteAddress(): SocketAddress  [API 24+]
+ isConnected(): boolean  [API 1+]
+ isConnectionPending(): boolean  [API 1+]
- open(): SocketChannel [static]  [API 1+]
- open(SocketAddress): SocketChannel [static]  [API 1+]
+ read(ByteBuffer): int  [API 1+]
- read(ByteBuffer[]): long  [API 1+]
+ read(ByteBuffer[], int, int): long  [API 1+]
+ setOption(SocketOption, Object): SocketChannel  [API 24+]
+ shutdownInput(): SocketChannel  [API 24+]
+ shutdownOutput(): SocketChannel  [API 24+]
+ socket(): Socket  [API 1+]
- validOps(): int  [API 1+]
+ write(ByteBuffer): int  [API 1+]
- write(ByteBuffer[]): long  [API 1+]
+ write(ByteBuffer[], int, int): long  [API 1+]
```

## UnresolvedAddressException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ UnresolvedAddressException()  [API 1+]
```

## UnsupportedAddressTypeException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ UnsupportedAddressTypeException()  [API 1+]
```

## WritableByteChannel — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ write(ByteBuffer): int  [API 1+]
```

## WritePendingException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ WritePendingException()  [API 26+]
```


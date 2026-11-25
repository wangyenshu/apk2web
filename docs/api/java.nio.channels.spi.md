# java.nio.channels.spi

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractInterruptibleChannel — declaration; API 1+ (1 +, 0 *, 0 ~, 5 -)

```
- AbstractInterruptibleChannel()  [API 1+]
- begin(): void  [API 1+]
- close(): void  [API 1+]
- end(boolean): void  [API 1+]
+ implCloseChannel(): void  [API 1+]
- isOpen(): boolean  [API 1+]
```

## AbstractSelectableChannel — declaration; API 1+ (2 +, 0 *, 0 ~, 9 -)

```
- AbstractSelectableChannel(SelectorProvider)  [API 1+]
- blockingLock(): Object  [API 1+]
- configureBlocking(boolean): SelectableChannel  [API 1+]
- implCloseChannel(): void  [API 1+]
+ implCloseSelectableChannel(): void  [API 1+]
+ implConfigureBlocking(boolean): void  [API 1+]
- isBlocking(): boolean  [API 1+]
- isRegistered(): boolean  [API 1+]
- keyFor(Selector): SelectionKey  [API 1+]
- provider(): SelectorProvider  [API 1+]
- register(Selector, int, Object): SelectionKey  [API 1+]
```

## AbstractSelectionKey — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- AbstractSelectionKey()  [API 1+]
- cancel(): void  [API 1+]
- isValid(): boolean  [API 1+]
```

## AbstractSelector — declaration; API 1+ (2 +, 0 *, 0 ~, 8 -)

```
- AbstractSelector(SelectorProvider)  [API 1+]
- begin(): void  [API 1+]
- cancelledKeys(): Set  [API 1+]
- close(): void  [API 1+]
- deregister(AbstractSelectionKey): void  [API 1+]
- end(): void  [API 1+]
+ implCloseSelector(): void  [API 1+]
- isOpen(): boolean  [API 1+]
- provider(): SelectorProvider  [API 1+]
+ register(AbstractSelectableChannel, int, Object): SelectionKey  [API 1+]
```

## AsynchronousChannelProvider — declaration; API 26+ (4 +, 0 *, 0 ~, 2 -)

```
- AsynchronousChannelProvider()  [API 26+]
+ openAsynchronousChannelGroup(ExecutorService, int): AsynchronousChannelGroup  [API 26+]
+ openAsynchronousChannelGroup(int, ThreadFactory): AsynchronousChannelGroup  [API 26+]
+ openAsynchronousServerSocketChannel(AsynchronousChannelGroup): AsynchronousServerSocketChannel  [API 26+]
+ openAsynchronousSocketChannel(AsynchronousChannelGroup): AsynchronousSocketChannel  [API 26+]
- provider(): AsynchronousChannelProvider [static]  [API 26+]
```

## SelectorProvider — declaration; API 1+ (6 +, 0 *, 0 ~, 3 -)

```
- SelectorProvider()  [API 1+]
- inheritedChannel(): Channel  [API 1+]
+ openDatagramChannel(): DatagramChannel  [API 1+]
+ openDatagramChannel(ProtocolFamily): DatagramChannel  [API 24+]
+ openPipe(): Pipe  [API 1+]
+ openSelector(): AbstractSelector  [API 1+]
+ openServerSocketChannel(): ServerSocketChannel  [API 1+]
+ openSocketChannel(): SocketChannel  [API 1+]
- provider(): SelectorProvider [static]  [API 1+]
```


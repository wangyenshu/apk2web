# java.net

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Authenticator — declaration; API 1+ (0 +, 0 *, 0 ~, 14 -)

```
- Authenticator()  [API 1+]
- getPasswordAuthentication(): PasswordAuthentication  [API 1+]
- getRequestingHost(): String  [API 1+]
- getRequestingPort(): int  [API 1+]
- getRequestingPrompt(): String  [API 1+]
- getRequestingProtocol(): String  [API 1+]
- getRequestingScheme(): String  [API 1+]
- getRequestingSite(): InetAddress  [API 1+]
- getRequestingURL(): URL  [API 1+]
- getRequestorType(): Authenticator.RequestorType  [API 1+]
- requestPasswordAuthentication(InetAddress, int, String, String, String): PasswordAuthentication [static]  [API 1+]
- requestPasswordAuthentication(String, InetAddress, int, String, String, String): PasswordAuthentication [static]  [API 1+]
- requestPasswordAuthentication(String, InetAddress, int, String, String, String, URL, Authenticator.RequestorType): PasswordAuthentication [static]  [API 1+]
- setDefault(Authenticator): void [static]  [API 1+]
```

## Authenticator.RequestorType — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ PROXY [field]  [API 1+]
+ SERVER [field]  [API 1+]
+ valueOf(String): Authenticator.RequestorType [static]  [API 1+]
+ values(): Authenticator.RequestorType[] [static]  [API 1+]
```

## BindException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BindException()  [API 1+]
+ BindException(String)  [API 1+]
```

## CacheRequest — declaration; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ CacheRequest()  [API 1+]
+ abort(): void  [API 1+]
+ getBody(): OutputStream  [API 1+]
```

## CacheResponse — declaration; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ CacheResponse()  [API 1+]
+ getBody(): InputStream  [API 1+]
+ getHeaders(): Map  [API 1+]
```

## ConnectException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ConnectException()  [API 1+]
+ ConnectException(String)  [API 1+]
```

## ContentHandler — declaration; API 1+ (1 +, 0 *, 0 ~, 2 -)

```
- ContentHandler()  [API 1+]
+ getContent(URLConnection): Object  [API 1+]
- getContent(URLConnection, Class[]): Object  [API 1+]
```

## ContentHandlerFactory — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ createContentHandler(String): ContentHandler  [API 1+]
```

## CookieHandler — declaration; API 1+ (2 +, 0 *, 0 ~, 3 -)

```
- CookieHandler()  [API 1+]
+ get(URI, Map): Map  [API 1+]
- getDefault(): CookieHandler [static]  [API 1+]
+ put(URI, Map): void  [API 1+]
- setDefault(CookieHandler): void [static]  [API 1+]
```

## CookieManager — declaration; API 9+ (0 +, 0 *, 0 ~, 6 -)

```
- CookieManager()  [API 9+]
- CookieManager(CookieStore, CookiePolicy)  [API 9+]
- get(URI, Map): Map  [API 9+]
- getCookieStore(): CookieStore  [API 9+]
- put(URI, Map): void  [API 9+]
- setCookiePolicy(CookiePolicy): void  [API 9+]
```

## CookiePolicy — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
- ACCEPT_ALL [field]  [API 9+]
- ACCEPT_NONE [field]  [API 9+]
- ACCEPT_ORIGINAL_SERVER [field]  [API 9+]
+ shouldAccept(URI, HttpCookie): boolean  [API 9+]
```

## CookieStore — declaration; API 9+ (6 +, 0 *, 0 ~, 0 -)

```
+ add(URI, HttpCookie): void  [API 9+]
+ get(URI): List  [API 9+]
+ getCookies(): List  [API 9+]
+ getURIs(): List  [API 9+]
+ remove(URI, HttpCookie): boolean  [API 9+]
+ removeAll(): boolean  [API 9+]
```

## DatagramPacket — declaration; API 1+ (0 +, 10 *, 0 ~, 8 -)

```
- DatagramPacket(byte[], int)  [API 1+]
- DatagramPacket(byte[], int, InetAddress, int)  [API 1+]
- DatagramPacket(byte[], int, SocketAddress)  [API 1+]
- DatagramPacket(byte[], int, int)  [API 1+]
- DatagramPacket(byte[], int, int, InetAddress, int)  [API 1+]
- DatagramPacket(byte[], int, int, SocketAddress)  [API 1+]
* getAddress(): InetAddress  [API 1+]
* getData(): byte[]  [API 1+]
* getLength(): int  [API 1+]
- getOffset(): int  [API 1+]
* getPort(): int  [API 1+]
* getSocketAddress(): SocketAddress  [API 1+]
* setAddress(InetAddress): void  [API 1+]
* setData(byte[]): void  [API 1+]
- setData(byte[], int, int): void  [API 1+]
* setLength(int): void  [API 1+]
* setPort(int): void  [API 1+]
* setSocketAddress(SocketAddress): void  [API 1+]
```

## DatagramSocket — declaration; API 1+ (0 +, 15 *, 0 ~, 23 -)

```
* DatagramSocket()  [API 1+]
- DatagramSocket(DatagramSocketImpl)  [API 1+]
- DatagramSocket(SocketAddress)  [API 1+]
* DatagramSocket(int)  [API 1+]
- DatagramSocket(int, InetAddress)  [API 1+]
- bind(SocketAddress): void  [API 1+]
- close(): void  [API 1+]
- connect(InetAddress, int): void  [API 1+]
- connect(SocketAddress): void  [API 1+]
- disconnect(): void  [API 1+]
* getBroadcast(): boolean  [API 1+]
- getChannel(): DatagramChannel  [API 1+]
- getInetAddress(): InetAddress  [API 1+]
- getLocalAddress(): InetAddress  [API 1+]
- getLocalPort(): int  [API 1+]
- getLocalSocketAddress(): SocketAddress  [API 1+]
- getOption(SocketOption): Object  [API 33+]
* getPort(): int  [API 1+]
* getReceiveBufferSize(): int  [API 1+]
- getRemoteSocketAddress(): SocketAddress  [API 1+]
* getReuseAddress(): boolean  [API 1+]
* getSendBufferSize(): int  [API 1+]
* getSoTimeout(): int  [API 1+]
* getTrafficClass(): int  [API 1+]
- isBound(): boolean  [API 1+]
- isClosed(): boolean  [API 1+]
- isConnected(): boolean  [API 1+]
- receive(DatagramPacket): void  [API 1+]
- send(DatagramPacket): void  [API 1+]
* setBroadcast(boolean): void  [API 1+]
- setDatagramSocketImplFactory(DatagramSocketImplFactory): void [static]  [API 1+]
- setOption(SocketOption, Object): DatagramSocket  [API 33+]
* setReceiveBufferSize(int): void  [API 1+]
* setReuseAddress(boolean): void  [API 1+]
* setSendBufferSize(int): void  [API 1+]
* setSoTimeout(int): void  [API 1+]
* setTrafficClass(int): void  [API 1+]
- supportedOptions(): Set  [API 33+]
```

## DatagramSocketImpl — declaration; API 1+ (15 +, 0 *, 0 ~, 8 -)

```
- DatagramSocketImpl()  [API 1+]
+ bind(int, InetAddress): void  [API 1+]
+ close(): void  [API 1+]
- connect(InetAddress, int): void  [API 1+]
+ create(): void  [API 1+]
- disconnect(): void  [API 1+]
+ fd [field]  [API 1+]
- getFileDescriptor(): FileDescriptor  [API 1+]
- getLocalPort(): int  [API 1+]
- getOption(SocketOption): Object  [API 33+]
+ getTTL(): byte  [API 1+, deprecated in 15]
+ getTimeToLive(): int  [API 1+]
+ join(InetAddress): void  [API 1+]
+ joinGroup(SocketAddress, NetworkInterface): void  [API 1+]
+ leave(InetAddress): void  [API 1+]
+ leaveGroup(SocketAddress, NetworkInterface): void  [API 1+]
+ localPort [field]  [API 1+]
+ peek(InetAddress): int  [API 1+]
+ peekData(DatagramPacket): int  [API 1+]
+ receive(DatagramPacket): void  [API 1+]
+ send(DatagramPacket): void  [API 1+]
- setOption(SocketOption, Object): void  [API 33+]
+ setTTL(byte): void  [API 1+, deprecated in 15]
+ setTimeToLive(int): void  [API 1+]
- supportedOptions(): Set  [API 33+]
```

## DatagramSocketImplFactory — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ createDatagramSocketImpl(): DatagramSocketImpl  [API 1+]
```

## FileNameMap — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getContentTypeFor(String): String  [API 1+]
```

## HttpCookie — declaration; API 9+ (0 +, 27 *, 0 ~, 4 -)

```
* HttpCookie(String, String)  [API 9+]
- clone(): Object  [API 9+]
- domainMatches(String, String): boolean [static]  [API 9+]
* equals(Object): boolean  [API 9+]
* getComment(): String  [API 9+]
* getCommentURL(): String  [API 9+]
* getDiscard(): boolean  [API 9+]
* getDomain(): String  [API 9+]
* getMaxAge(): long  [API 9+]
* getName(): String  [API 9+]
* getPath(): String  [API 9+]
* getPortlist(): String  [API 9+]
* getSecure(): boolean  [API 9+]
* getValue(): String  [API 9+]
* getVersion(): int  [API 9+]
- hasExpired(): boolean  [API 9+]
* hashCode(): int  [API 9+]
* isHttpOnly(): boolean  [API 24+]
- parse(String): List [static]  [API 9+]
* setComment(String): void  [API 9+]
* setCommentURL(String): void  [API 9+]
* setDiscard(boolean): void  [API 9+]
* setDomain(String): void  [API 9+]
* setHttpOnly(boolean): void  [API 24+]
* setMaxAge(long): void  [API 9+]
* setPath(String): void  [API 9+]
* setPortlist(String): void  [API 9+]
* setSecure(boolean): void  [API 9+]
* setValue(String): void  [API 9+]
* setVersion(int): void  [API 9+]
* toString(): String  [API 9+]
```

## HttpRetryException — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- HttpRetryException(String, int)  [API 1+]
- HttpRetryException(String, int, String)  [API 1+]
- getLocation(): String  [API 1+]
- getReason(): String  [API 1+]
- responseCode(): int  [API 1+]
```

## HttpURLConnection — TeaVM class library; API 1+ (16 +, 0 *, 0 ~, 3 -)

```
+ HttpURLConnection(URL)  [API 1+]
+ chunkLength [field]  [API 1+]
+ disconnect(): void  [API 1+]
+ fixedContentLength [field]  [API 1+]
+ fixedContentLengthLong [field]  [API 19+]
+ getErrorStream(): InputStream  [API 1+]
+ getFollowRedirects(): boolean [static]  [API 1+]
+ getHeaderField(int): String  [API 1+]
+ getHeaderFieldDate(String, long): long  [API 1+]
+ getHeaderFieldKey(int): String  [API 1+]
+ getInstanceFollowRedirects(): boolean  [API 1+]
- getPermission(): Permission  [API 1+]
+ getRequestMethod(): String  [API 1+]
+ getResponseCode(): int  [API 1+]
+ getResponseMessage(): String  [API 1+]
+ instanceFollowRedirects [field]  [API 1+]
+ method [field]  [API 1+]
+ responseCode [field]  [API 1+]
+ responseMessage [field]  [API 1+]
+ setChunkedStreamingMode(int): void  [API 1+]
+ setFixedLengthStreamingMode(int): void  [API 1+]
- setFixedLengthStreamingMode(long): void  [API 19+]
+ setFollowRedirects(boolean): void [static]  [API 1+]
+ setInstanceFollowRedirects(boolean): void  [API 1+]
+ setRequestMethod(String): void  [API 1+]
- usingProxy(): boolean  [API 1+]
```

## IDN — declaration; API 9+ (0 +, 0 *, 0 ~, 4 -)

```
- toASCII(String): String [static]  [API 9+]
- toASCII(String, int): String [static]  [API 9+]
- toUnicode(String): String [static]  [API 9+]
- toUnicode(String, int): String [static]  [API 9+]
```

## Inet4Address — declaration; API 1+ (0 +, 0 *, 0 ~, 14 -)

```
- equals(Object): boolean  [API 1+]
- getAddress(): byte[]  [API 1+]
- getHostAddress(): String  [API 1+]
- hashCode(): int  [API 1+]
- isAnyLocalAddress(): boolean  [API 1+]
- isLinkLocalAddress(): boolean  [API 1+]
- isLoopbackAddress(): boolean  [API 1+]
- isMCGlobal(): boolean  [API 1+]
- isMCLinkLocal(): boolean  [API 1+]
- isMCNodeLocal(): boolean  [API 1+]
- isMCOrgLocal(): boolean  [API 1+]
- isMCSiteLocal(): boolean  [API 1+]
- isMulticastAddress(): boolean  [API 1+]
- isSiteLocalAddress(): boolean  [API 1+]
```

## Inet6Address — declaration; API 1+ (0 +, 0 *, 0 ~, 19 -)

```
- equals(Object): boolean  [API 1+]
- getAddress(): byte[]  [API 1+]
- getByAddress(String, byte[], NetworkInterface): Inet6Address [static]  [API 1+]
- getByAddress(String, byte[], int): Inet6Address [static]  [API 1+]
- getHostAddress(): String  [API 1+]
- getScopeId(): int  [API 1+]
- getScopedInterface(): NetworkInterface  [API 1+]
- hashCode(): int  [API 1+]
- isAnyLocalAddress(): boolean  [API 1+]
- isIPv4CompatibleAddress(): boolean  [API 1+]
- isLinkLocalAddress(): boolean  [API 1+]
- isLoopbackAddress(): boolean  [API 1+]
- isMCGlobal(): boolean  [API 1+]
- isMCLinkLocal(): boolean  [API 1+]
- isMCNodeLocal(): boolean  [API 1+]
- isMCOrgLocal(): boolean  [API 1+]
- isMCSiteLocal(): boolean  [API 1+]
- isMulticastAddress(): boolean  [API 1+]
- isSiteLocalAddress(): boolean  [API 1+]
```

## InetAddress — declaration; API 1+ (0 +, 0 *, 0 ~, 25 -)

```
- equals(Object): boolean  [API 1+]
- getAddress(): byte[]  [API 1+]
- getAllByName(String): InetAddress[] [static]  [API 1+]
- getByAddress(String, byte[]): InetAddress [static]  [API 1+]
- getByAddress(byte[]): InetAddress [static]  [API 1+]
- getByName(String): InetAddress [static]  [API 1+]
- getCanonicalHostName(): String  [API 1+]
- getHostAddress(): String  [API 1+]
- getHostName(): String  [API 1+]
- getLocalHost(): InetAddress [static]  [API 1+]
- getLoopbackAddress(): InetAddress [static]  [API 19+]
- hashCode(): int  [API 1+]
- isAnyLocalAddress(): boolean  [API 1+]
- isLinkLocalAddress(): boolean  [API 1+]
- isLoopbackAddress(): boolean  [API 1+]
- isMCGlobal(): boolean  [API 1+]
- isMCLinkLocal(): boolean  [API 1+]
- isMCNodeLocal(): boolean  [API 1+]
- isMCOrgLocal(): boolean  [API 1+]
- isMCSiteLocal(): boolean  [API 1+]
- isMulticastAddress(): boolean  [API 1+]
- isReachable(NetworkInterface, int, int): boolean  [API 1+]
- isReachable(int): boolean  [API 1+]
- isSiteLocalAddress(): boolean  [API 1+]
- toString(): String  [API 1+]
```

## InetSocketAddress — declaration; API 1+ (0 +, 4 *, 0 ~, 8 -)

```
- InetSocketAddress(InetAddress, int)  [API 1+]
* InetSocketAddress(String, int)  [API 1+]
* InetSocketAddress(int)  [API 1+]
- createUnresolved(String, int): InetSocketAddress [static]  [API 1+]
- equals(Object): boolean  [API 1+]
- getAddress(): InetAddress  [API 1+]
* getHostName(): String  [API 1+]
- getHostString(): String  [API 19+]
* getPort(): int  [API 1+]
- hashCode(): int  [API 1+]
- isUnresolved(): boolean  [API 1+]
- toString(): String  [API 1+]
```

## InterfaceAddress — declaration; API 9+ (0 +, 0 *, 0 ~, 6 -)

```
- equals(Object): boolean  [API 9+]
- getAddress(): InetAddress  [API 9+]
- getBroadcast(): InetAddress  [API 9+]
- getNetworkPrefixLength(): short  [API 9+]
- hashCode(): int  [API 9+]
- toString(): String  [API 9+]
```

## JarURLConnection — declaration; API 1+ (1 +, 0 *, 0 ~, 8 -)

```
- JarURLConnection(URL)  [API 1+]
- getAttributes(): Attributes  [API 1+]
- getCertificates(): Certificate[]  [API 1+]
- getEntryName(): String  [API 1+]
- getJarEntry(): JarEntry  [API 1+]
+ getJarFile(): JarFile  [API 1+]
- getJarFileURL(): URL  [API 1+]
- getMainAttributes(): Attributes  [API 1+]
- getManifest(): Manifest  [API 1+]
+ jarFileURLConnection [field]  [API 1+]
```

## MalformedURLException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ MalformedURLException()  [API 1+]
+ MalformedURLException(String)  [API 1+]
```

## MulticastSocket — declaration; API 1+ (0 +, 12 *, 0 ~, 7 -)

```
* MulticastSocket()  [API 1+]
- MulticastSocket(SocketAddress)  [API 1+]
* MulticastSocket(int)  [API 1+]
* getInterface(): InetAddress  [API 1+]
* getLoopbackMode(): boolean  [API 1+]
* getNetworkInterface(): NetworkInterface  [API 1+]
* getTTL(): byte  [API 1+, deprecated in 15]
* getTimeToLive(): int  [API 1+]
- joinGroup(InetAddress): void  [API 1+]
- joinGroup(SocketAddress, NetworkInterface): void  [API 1+]
- leaveGroup(InetAddress): void  [API 1+]
- leaveGroup(SocketAddress, NetworkInterface): void  [API 1+]
- send(DatagramPacket, byte): void  [API 1+, deprecated in 15]
* setInterface(InetAddress): void  [API 1+]
* setLoopbackMode(boolean): void  [API 1+]
* setNetworkInterface(NetworkInterface): void  [API 1+]
* setTTL(byte): void  [API 1+, deprecated in 15]
* setTimeToLive(int): void  [API 1+]
- supportedOptions(): Set  [API 1+]
```

## NetPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- NetPermission(String)  [API 1+]
- NetPermission(String, String)  [API 1+]
```

## NetworkInterface — declaration; API 1+ (0 +, 0 *, 0 ~, 21 -)

```
- equals(Object): boolean  [API 1+]
- getByIndex(int): NetworkInterface [static]  [API 19+]
- getByInetAddress(InetAddress): NetworkInterface [static]  [API 1+]
- getByName(String): NetworkInterface [static]  [API 1+]
- getDisplayName(): String  [API 1+]
- getHardwareAddress(): byte[]  [API 9+]
- getIndex(): int  [API 19+]
- getInetAddresses(): Enumeration  [API 1+]
- getInterfaceAddresses(): List  [API 9+]
- getMTU(): int  [API 9+]
- getName(): String  [API 1+]
- getNetworkInterfaces(): Enumeration [static]  [API 1+]
- getParent(): NetworkInterface  [API 9+]
- getSubInterfaces(): Enumeration  [API 9+]
- hashCode(): int  [API 1+]
- isLoopback(): boolean  [API 9+]
- isPointToPoint(): boolean  [API 9+]
- isUp(): boolean  [API 9+]
- isVirtual(): boolean  [API 9+]
- supportsMulticast(): boolean  [API 9+]
- toString(): String  [API 1+]
```

## NoRouteToHostException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoRouteToHostException()  [API 1+]
+ NoRouteToHostException(String)  [API 1+]
```

## PasswordAuthentication — declaration; API 1+ (0 +, 3 *, 0 ~, 0 -)

```
* PasswordAuthentication(String, char[])  [API 1+]
* getPassword(): char[]  [API 1+]
* getUserName(): String  [API 1+]
```

## PortUnreachableException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ PortUnreachableException()  [API 1+]
+ PortUnreachableException(String)  [API 1+]
```

## ProtocolException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ProtocolException()  [API 1+]
+ ProtocolException(String)  [API 1+]
```

## ProtocolFamily — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ name(): String  [API 24+]
```

## Proxy — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- NO_PROXY [field]  [API 1+]
- Proxy(Proxy.Type, SocketAddress)  [API 1+]
- address(): SocketAddress  [API 1+]
- equals(Object): boolean  [API 1+]
- hashCode(): int  [API 1+]
- toString(): String  [API 1+]
- type(): Proxy.Type  [API 1+]
```

## Proxy.Type — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ DIRECT [field]  [API 1+]
+ HTTP [field]  [API 1+]
+ SOCKS [field]  [API 1+]
+ valueOf(String): Proxy.Type [static]  [API 1+]
+ values(): Proxy.Type[] [static]  [API 1+]
```

## ProxySelector — declaration; API 1+ (2 +, 0 *, 0 ~, 3 -)

```
- ProxySelector()  [API 1+]
+ connectFailed(URI, SocketAddress, IOException): void  [API 1+]
- getDefault(): ProxySelector [static]  [API 1+]
+ select(URI): List  [API 1+]
- setDefault(ProxySelector): void [static]  [API 1+]
```

## ResponseCache — declaration; API 1+ (2 +, 0 *, 0 ~, 3 -)

```
- ResponseCache()  [API 1+]
+ get(URI, String, Map): CacheResponse  [API 1+]
- getDefault(): ResponseCache [static]  [API 1+]
+ put(URI, URLConnection): CacheRequest  [API 1+]
- setDefault(ResponseCache): void [static]  [API 1+]
```

## SecureCacheResponse — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ SecureCacheResponse()  [API 1+]
+ getCipherSuite(): String  [API 1+]
+ getLocalCertificateChain(): List  [API 1+]
+ getLocalPrincipal(): Principal  [API 1+]
+ getPeerPrincipal(): Principal  [API 1+]
+ getServerCertificateChain(): List  [API 1+]
```

## ServerSocket — declaration; API 1+ (0 +, 7 *, 0 ~, 21 -)

```
* ServerSocket()  [API 1+]
- ServerSocket(SocketImpl)  [API 1-1, removed in 2]
- ServerSocket(int)  [API 1+]
- ServerSocket(int, int)  [API 1+]
- ServerSocket(int, int, InetAddress)  [API 1+]
- accept(): Socket  [API 1+]
- bind(SocketAddress): void  [API 1+]
- bind(SocketAddress, int): void  [API 1+]
- close(): void  [API 1+]
- getChannel(): ServerSocketChannel  [API 1+]
- getInetAddress(): InetAddress  [API 1+]
- getLocalPort(): int  [API 1+]
- getLocalSocketAddress(): SocketAddress  [API 1+]
- getOption(SocketOption): Object  [API 33+]
* getReceiveBufferSize(): int  [API 1+]
* getReuseAddress(): boolean  [API 1+]
* getSoTimeout(): int  [API 1+]
- implAccept(Socket): void  [API 1+]
- isBound(): boolean  [API 1+]
- isClosed(): boolean  [API 1+]
- setOption(SocketOption, Object): ServerSocket  [API 33+]
- setPerformancePreferences(int, int, int): void  [API 1+]
* setReceiveBufferSize(int): void  [API 1+]
* setReuseAddress(boolean): void  [API 1+]
* setSoTimeout(int): void  [API 1+]
- setSocketFactory(SocketImplFactory): void [static]  [API 1+]
- supportedOptions(): Set  [API 33+]
- toString(): String  [API 1+]
```

## Socket — declaration; API 1+ (0 +, 17 *, 0 ~, 37 -)

```
* Socket()  [API 1+]
- Socket(InetAddress, int)  [API 1+]
- Socket(InetAddress, int, InetAddress, int)  [API 1+]
- Socket(InetAddress, int, boolean)  [API 1+, deprecated in 15]
- Socket(Proxy)  [API 1+]
- Socket(SocketImpl)  [API 1+]
- Socket(String, int)  [API 1+]
- Socket(String, int, InetAddress, int)  [API 1+]
- Socket(String, int, boolean)  [API 1+, deprecated in 15]
- bind(SocketAddress): void  [API 1+]
- close(): void  [API 1+]
- connect(SocketAddress): void  [API 1+]
- connect(SocketAddress, int): void  [API 1+]
- getChannel(): SocketChannel  [API 1+]
- getInetAddress(): InetAddress  [API 1+]
- getInputStream(): InputStream  [API 1+]
* getKeepAlive(): boolean  [API 1+]
- getLocalAddress(): InetAddress  [API 1+]
- getLocalPort(): int  [API 1+]
- getLocalSocketAddress(): SocketAddress  [API 1+]
* getOOBInline(): boolean  [API 1+]
- getOption(SocketOption): Object  [API 33+]
- getOutputStream(): OutputStream  [API 1+]
- getPort(): int  [API 1+]
* getReceiveBufferSize(): int  [API 1+]
- getRemoteSocketAddress(): SocketAddress  [API 1+]
* getReuseAddress(): boolean  [API 1+]
* getSendBufferSize(): int  [API 1+]
- getSoLinger(): int  [API 1+]
* getSoTimeout(): int  [API 1+]
* getTcpNoDelay(): boolean  [API 1+]
* getTrafficClass(): int  [API 1+]
- isBound(): boolean  [API 1+]
- isClosed(): boolean  [API 1+]
- isConnected(): boolean  [API 1+]
- isInputShutdown(): boolean  [API 1+]
- isOutputShutdown(): boolean  [API 1+]
- sendUrgentData(int): void  [API 1+]
* setKeepAlive(boolean): void  [API 1+]
* setOOBInline(boolean): void  [API 1+]
- setOption(SocketOption, Object): Socket  [API 33+]
- setPerformancePreferences(int, int, int): void  [API 1+]
* setReceiveBufferSize(int): void  [API 1+]
* setReuseAddress(boolean): void  [API 1+]
* setSendBufferSize(int): void  [API 1+]
- setSoLinger(boolean, int): void  [API 1+]
* setSoTimeout(int): void  [API 1+]
- setSocketImplFactory(SocketImplFactory): void [static]  [API 1+]
* setTcpNoDelay(boolean): void  [API 1+]
* setTrafficClass(int): void  [API 1+]
- shutdownInput(): void  [API 1+]
- shutdownOutput(): void  [API 1+]
- supportedOptions(): Set  [API 33+]
- toString(): String  [API 1+]
```

## SocketAddress — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ SocketAddress()  [API 1+]
```

## SocketException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SocketException()  [API 1+]
+ SocketException(String)  [API 1+]
```

## SocketImpl — declaration; API 1+ (12 +, 0 *, 0 ~, 13 -)

```
- SocketImpl()  [API 1+]
+ accept(SocketImpl): void  [API 1+]
+ address [field]  [API 1+]
+ available(): int  [API 1+]
+ bind(InetAddress, int): void  [API 1+]
+ close(): void  [API 1+]
+ connect(InetAddress, int): void  [API 1+]
+ connect(SocketAddress, int): void  [API 1+]
+ connect(String, int): void  [API 1+]
+ create(boolean): void  [API 1+]
+ fd [field]  [API 1+]
- getFileDescriptor(): FileDescriptor  [API 1+]
- getInetAddress(): InetAddress  [API 1+]
+ getInputStream(): InputStream  [API 1+]
- getLocalPort(): int  [API 1+]
- getOption(SocketOption): Object  [API 33+]
+ getOutputStream(): OutputStream  [API 1+]
- getPort(): int  [API 1+]
+ listen(int): void  [API 1+]
+ localport [field]  [API 1+]
+ port [field]  [API 1+]
+ sendUrgentData(int): void  [API 1+]
- setOption(SocketOption, Object): void  [API 33+]
- setPerformancePreferences(int, int, int): void  [API 1+]
- shutdownInput(): void  [API 1+]
- shutdownOutput(): void  [API 1+]
- supportedOptions(): Set  [API 33+]
- supportsUrgentData(): boolean  [API 1+]
- toString(): String  [API 1+]
```

## SocketImplFactory — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ createSocketImpl(): SocketImpl  [API 1+]
```

## SocketOption — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ name(): String  [API 24+]
+ type(): Class  [API 24+]
```

## SocketOptions — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ getOption(int): Object  [API 1+]
+ setOption(int, Object): void  [API 1+]
```

## SocketPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- SocketPermission(String, String)  [API 1+]
- getActions(): String  [API 1+]
- implies(Permission): boolean  [API 1+]
```

## SocketTimeoutException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SocketTimeoutException()  [API 1+]
+ SocketTimeoutException(String)  [API 1+]
```

## StandardProtocolFamily — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ INET [field]  [API 24+]
+ INET6 [field]  [API 24+]
+ UNIX [field]  [API 34+]
+ valueOf(String): StandardProtocolFamily [static]  [API 24+]
+ values(): StandardProtocolFamily[] [static]  [API 24+]
```

## StandardSocketOptions — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

```
- IP_MULTICAST_IF [field]  [API 24+]
- IP_MULTICAST_LOOP [field]  [API 24+]
- IP_MULTICAST_TTL [field]  [API 24+]
- IP_TOS [field]  [API 24+]
- SO_BROADCAST [field]  [API 24+]
- SO_KEEPALIVE [field]  [API 24+]
- SO_LINGER [field]  [API 24+]
- SO_RCVBUF [field]  [API 24+]
- SO_REUSEADDR [field]  [API 24+]
- SO_REUSEPORT [field]  [API 33+]
- SO_SNDBUF [field]  [API 24+]
- TCP_NODELAY [field]  [API 24+]
```

## URI — TeaVM class library; API 1+ (34 +, 0 *, 0 ~, 0 -)

```
+ URI(String)  [API 1+]
+ URI(String, String, String)  [API 1+]
+ URI(String, String, String, String)  [API 1+]
+ URI(String, String, String, String, String)  [API 1+]
+ URI(String, String, String, int, String, String, String)  [API 1+]
+ compareTo(URI): int  [API 1+]
+ create(String): URI [static]  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getAuthority(): String  [API 1+]
+ getFragment(): String  [API 1+]
+ getHost(): String  [API 1+]
+ getPath(): String  [API 1+]
+ getPort(): int  [API 1+]
+ getQuery(): String  [API 1+]
+ getRawAuthority(): String  [API 1+]
+ getRawFragment(): String  [API 1+]
+ getRawPath(): String  [API 1+]
+ getRawQuery(): String  [API 1+]
+ getRawSchemeSpecificPart(): String  [API 1+]
+ getRawUserInfo(): String  [API 1+]
+ getScheme(): String  [API 1+]
+ getSchemeSpecificPart(): String  [API 1+]
+ getUserInfo(): String  [API 1+]
+ hashCode(): int  [API 1+]
+ isAbsolute(): boolean  [API 1+]
+ isOpaque(): boolean  [API 1+]
+ normalize(): URI  [API 1+]
+ parseServerAuthority(): URI  [API 1+]
+ relativize(URI): URI  [API 1+]
+ resolve(String): URI  [API 1+]
+ resolve(URI): URI  [API 1+]
+ toASCIIString(): String  [API 1+]
+ toString(): String  [API 1+]
+ toURL(): URL  [API 1+]
```

## URISyntaxException — TeaVM class library; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ URISyntaxException(String, String)  [API 1+]
+ URISyntaxException(String, String, int)  [API 1+]
+ getIndex(): int  [API 1+]
+ getInput(): String  [API 1+]
+ getMessage(): String  [API 1+]
+ getReason(): String  [API 1+]
```

## URL — TeaVM class library; API 1+ (27 +, 0 *, 0 ~, 3 -)

```
+ URL(String)  [API 1+]
+ URL(String, String, String)  [API 1+]
+ URL(String, String, int, String)  [API 1+]
+ URL(String, String, int, String, URLStreamHandler)  [API 1+]
+ URL(URL, String)  [API 1+]
+ URL(URL, String, URLStreamHandler)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getAuthority(): String  [API 1+]
- getContent(): Object  [API 1+]
- getContent(Class[]): Object  [API 1+]
+ getDefaultPort(): int  [API 1+]
+ getFile(): String  [API 1+]
+ getHost(): String  [API 1+]
+ getPath(): String  [API 1+]
+ getPort(): int  [API 1+]
+ getProtocol(): String  [API 1+]
+ getQuery(): String  [API 1+]
+ getRef(): String  [API 1+]
+ getUserInfo(): String  [API 1+]
+ hashCode(): int  [API 1+]
+ openConnection(): URLConnection  [API 1+]
- openConnection(Proxy): URLConnection  [API 1+]
+ openStream(): InputStream  [API 1+]
+ sameFile(URL): boolean  [API 1+]
+ set(String, String, int, String, String): void  [API 1-25, removed in 26]
+ set(String, String, int, String, String, String, String, String): void  [API 1-25, removed in 26]
+ setURLStreamHandlerFactory(URLStreamHandlerFactory): void [static]  [API 1+]
+ toExternalForm(): String  [API 1+]
+ toString(): String  [API 1+]
+ toURI(): URI  [API 1+]
```

## URLClassLoader — declaration; API 1+ (0 +, 0 *, 0 ~, 14 -)

```
- URLClassLoader(URL[])  [API 1+]
- URLClassLoader(URL[], ClassLoader)  [API 1+]
- URLClassLoader(URL[], ClassLoader, URLStreamHandlerFactory)  [API 1+]
- addURL(URL): void  [API 1+]
- close(): void  [API 1+]
- definePackage(String, Manifest, URL): Package  [API 1+]
- findClass(String): Class  [API 1+]
- findResource(String): URL  [API 1+]
- findResources(String): Enumeration  [API 1+]
- getPermissions(CodeSource): PermissionCollection  [API 1+]
- getResourceAsStream(String): InputStream  [API 1+]
- getURLs(): URL[]  [API 1+]
- newInstance(URL[]): URLClassLoader [static]  [API 1+]
- newInstance(URL[], ClassLoader): URLClassLoader [static]  [API 1+]
```

## URLConnection — TeaVM class library; API 1+ (42 +, 0 *, 0 ~, 10 -)

```
+ URLConnection(URL)  [API 1+]
+ addRequestProperty(String, String): void  [API 1+]
+ allowUserInteraction [field]  [API 1+]
+ connect(): void  [API 1+]
+ connected [field]  [API 1+]
+ doInput [field]  [API 1+]
+ doOutput [field]  [API 1+]
+ getAllowUserInteraction(): boolean  [API 1+]
+ getConnectTimeout(): int  [API 1+]
- getContent(): Object  [API 1+]
- getContent(Class[]): Object  [API 1+]
+ getContentEncoding(): String  [API 1+]
+ getContentLength(): int  [API 1+]
- getContentLengthLong(): long  [API 24+]
+ getContentType(): String  [API 1+]
+ getDate(): long  [API 1+]
+ getDefaultAllowUserInteraction(): boolean [static]  [API 1+]
+ getDefaultRequestProperty(String): String [static]  [API 1+, deprecated in 15]
+ getDefaultUseCaches(): boolean  [API 1+]
+ getDoInput(): boolean  [API 1+]
+ getDoOutput(): boolean  [API 1+]
+ getExpiration(): long  [API 1+]
- getFileNameMap(): FileNameMap [static]  [API 1+]
+ getHeaderField(String): String  [API 1+]
+ getHeaderField(int): String  [API 1+]
+ getHeaderFieldDate(String, long): long  [API 1+]
+ getHeaderFieldInt(String, int): int  [API 1+]
+ getHeaderFieldKey(int): String  [API 1+]
- getHeaderFieldLong(String, long): long  [API 24+]
+ getHeaderFields(): Map  [API 1+]
+ getIfModifiedSince(): long  [API 1+]
+ getInputStream(): InputStream  [API 1+]
+ getLastModified(): long  [API 1+]
+ getOutputStream(): OutputStream  [API 1+]
- getPermission(): Permission  [API 1+]
+ getReadTimeout(): int  [API 1+]
+ getRequestProperties(): Map  [API 1+]
+ getRequestProperty(String): String  [API 1+]
+ getURL(): URL  [API 1+]
+ getUseCaches(): boolean  [API 1+]
- guessContentTypeFromName(String): String [static]  [API 1+]
- guessContentTypeFromStream(InputStream): String [static]  [API 1+]
+ ifModifiedSince [field]  [API 1+]
+ setAllowUserInteraction(boolean): void  [API 1+]
+ setConnectTimeout(int): void  [API 1+]
- setContentHandlerFactory(ContentHandlerFactory): void [static]  [API 1+]
+ setDefaultAllowUserInteraction(boolean): void [static]  [API 1+]
+ setDefaultRequestProperty(String, String): void [static]  [API 1+, deprecated in 15]
+ setDefaultUseCaches(boolean): void  [API 1+]
+ setDoInput(boolean): void  [API 1+]
+ setDoOutput(boolean): void  [API 1+]
- setFileNameMap(FileNameMap): void [static]  [API 1+]
+ setIfModifiedSince(long): void  [API 1+]
+ setReadTimeout(int): void  [API 1+]
+ setRequestProperty(String, String): void  [API 1+]
+ setUseCaches(boolean): void  [API 1+]
+ toString(): String  [API 1+]
+ url [field]  [API 1+]
+ useCaches [field]  [API 1+]
```

## URLDecoder — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
- URLDecoder()  [API 1+]
+ decode(String): String [static]  [API 1+, deprecated in 15]
- decode(String, Charset): String [static]  [API 33+]
+ decode(String, String): String [static]  [API 1+]
```

## URLEncoder — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ encode(String): String [static]  [API 1+, deprecated in 15]
+ encode(String, Charset): String [static]  [API 33+]
+ encode(String, String): String [static]  [API 1+]
```

## URLStreamHandler — TeaVM class library; API 1+ (11 +, 0 *, 0 ~, 2 -)

```
+ URLStreamHandler()  [API 1+]
+ equals(URL, URL): boolean  [API 1+]
+ getDefaultPort(): int  [API 1+]
- getHostAddress(URL): InetAddress  [API 1+]
+ hashCode(URL): int  [API 1+]
+ hostsEqual(URL, URL): boolean  [API 1+]
+ openConnection(URL): URLConnection  [API 1+]
- openConnection(URL, Proxy): URLConnection  [API 1+]
+ parseURL(URL, String, int, int): void  [API 1+]
+ sameFile(URL, URL): boolean  [API 1+]
+ setURL(URL, String, String, int, String, String): void  [API 1+, deprecated in 15]
+ setURL(URL, String, String, int, String, String, String, String, String): void  [API 1+]
+ toExternalForm(URL): String  [API 1+]
```

## URLStreamHandlerFactory — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ createURLStreamHandler(String): URLStreamHandler  [API 1+]
```

## UnknownHostException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnknownHostException()  [API 1+]
+ UnknownHostException(String)  [API 1+]
```

## UnknownServiceException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnknownServiceException()  [API 1+]
+ UnknownServiceException(String)  [API 1+]
```


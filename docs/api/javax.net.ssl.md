# javax.net.ssl

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CertPathTrustManagerParameters — declaration; API 1+ (0 +, 2 *, 0 ~, 0 -)

```
* CertPathTrustManagerParameters(CertPathParameters)  [API 1+]
* getParameters(): CertPathParameters  [API 1+]
```

## ExtendedSSLSession — declaration; API 24+ (2 +, 0 *, 0 ~, 2 -)

```
- ExtendedSSLSession()  [API 24+]
+ getLocalSupportedSignatureAlgorithms(): String[]  [API 24+]
+ getPeerSupportedSignatureAlgorithms(): String[]  [API 24+]
- getRequestedServerNames(): List  [API 24+]
```

## HandshakeCompletedEvent — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- HandshakeCompletedEvent(SSLSocket, SSLSession)  [API 1+]
- getCipherSuite(): String  [API 1+]
- getLocalCertificates(): Certificate[]  [API 1+]
- getLocalPrincipal(): Principal  [API 1+]
- getPeerCertificateChain(): X509Certificate[]  [API 1+]
- getPeerCertificates(): Certificate[]  [API 1+]
- getPeerPrincipal(): Principal  [API 1+]
- getSession(): SSLSession  [API 1+]
- getSocket(): SSLSocket  [API 1+]
```

## HandshakeCompletedListener — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ handshakeCompleted(HandshakeCompletedEvent): void  [API 1+]
```

## HostnameVerifier — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ verify(String, SSLSession): boolean  [API 1+]
```

## HttpsURLConnection — framework (a2w.jdk.HttpsURLConnection); API 1+ (1 +, 0 *, 1 ~, 12 -)

```
+ HttpsURLConnection(URL)  [API 1+]
~ getCipherSuite(): String  [API 1+]
- getDefaultHostnameVerifier(): HostnameVerifier [static]  [API 1+]
- getDefaultSSLSocketFactory(): SSLSocketFactory [static]  [API 1+]
- getHostnameVerifier(): HostnameVerifier  [API 1+]
- getLocalCertificates(): Certificate[]  [API 1+]
- getLocalPrincipal(): Principal  [API 1+]
- getPeerPrincipal(): Principal  [API 1+]
- getSSLSocketFactory(): SSLSocketFactory  [API 1+]
- getServerCertificates(): Certificate[]  [API 1+]
- hostnameVerifier [field]  [API 1+]
- setDefaultHostnameVerifier(HostnameVerifier): void [static]  [API 1+]
- setDefaultSSLSocketFactory(SSLSocketFactory): void [static]  [API 1+]
- setHostnameVerifier(HostnameVerifier): void  [API 1+]
- setSSLSocketFactory(SSLSocketFactory): void  [API 1+]
```

## KeyManager — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## KeyManagerFactory — declaration; API 1+ (0 +, 0 *, 0 ~, 10 -)

```
- KeyManagerFactory(KeyManagerFactorySpi, Provider, String)  [API 1+]
- getAlgorithm(): String  [API 1+]
- getDefaultAlgorithm(): String [static]  [API 1+]
- getInstance(String): KeyManagerFactory [static]  [API 1+]
- getInstance(String, Provider): KeyManagerFactory [static]  [API 1+]
- getInstance(String, String): KeyManagerFactory [static]  [API 1+]
- getKeyManagers(): KeyManager[]  [API 1+]
- getProvider(): Provider  [API 1+]
- init(KeyStore, char[]): void  [API 1+]
- init(ManagerFactoryParameters): void  [API 1+]
```

## KeyManagerFactorySpi — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ KeyManagerFactorySpi()  [API 1+]
+ engineGetKeyManagers(): KeyManager[]  [API 1+]
+ engineInit(KeyStore, char[]): void  [API 1+]
+ engineInit(ManagerFactoryParameters): void  [API 1+]
```

## KeyStoreBuilderParameters — declaration; API 1+ (0 +, 2 *, 0 ~, 1 -)

```
- KeyStoreBuilderParameters(KeyStore.Builder)  [API 1+]
* KeyStoreBuilderParameters(List)  [API 1+]
* getParameters(): List  [API 1+]
```

## ManagerFactoryParameters — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## SNIHostName — declaration; API 24+ (0 +, 1 *, 0 ~, 6 -)

```
- SNIHostName(String)  [API 24+]
* SNIHostName(byte[])  [API 24+]
- createSNIMatcher(String): SNIMatcher [static]  [API 24+]
- equals(Object): boolean  [API 24+]
- getAsciiName(): String  [API 24+]
- hashCode(): int  [API 24+]
- toString(): String  [API 24+]
```

## SNIMatcher — declaration; API 24+ (1 +, 2 *, 0 ~, 0 -)

```
* SNIMatcher(int)  [API 24+]
* getType(): int  [API 24+]
+ matches(SNIServerName): boolean  [API 24+]
```

## SNIServerName — declaration; API 24+ (0 +, 6 *, 0 ~, 0 -)

```
* SNIServerName(int, byte[])  [API 24+]
* equals(Object): boolean  [API 24+]
* getEncoded(): byte[]  [API 24+]
* getType(): int  [API 24+]
* hashCode(): int  [API 24+]
* toString(): String  [API 24+]
```

## SSLContext — declaration; API 1+ (0 +, 0 *, 0 ~, 17 -)

```
- SSLContext(SSLContextSpi, Provider, String)  [API 1+]
- createSSLEngine(): SSLEngine  [API 1+]
- createSSLEngine(String, int): SSLEngine  [API 1+]
- getClientSessionContext(): SSLSessionContext  [API 1+]
- getDefault(): SSLContext [static]  [API 9+]
- getDefaultSSLParameters(): SSLParameters  [API 9+]
- getInstance(String): SSLContext [static]  [API 1+]
- getInstance(String, Provider): SSLContext [static]  [API 1+]
- getInstance(String, String): SSLContext [static]  [API 1+]
- getProtocol(): String  [API 1+]
- getProvider(): Provider  [API 1+]
- getServerSessionContext(): SSLSessionContext  [API 1+]
- getServerSocketFactory(): SSLServerSocketFactory  [API 1+]
- getSocketFactory(): SSLSocketFactory  [API 1+]
- getSupportedSSLParameters(): SSLParameters  [API 9+]
- init(KeyManager[], TrustManager[], SecureRandom): void  [API 1+]
- setDefault(SSLContext): void [static]  [API 9+]
```

## SSLContextSpi — declaration; API 1+ (7 +, 0 *, 0 ~, 3 -)

```
- SSLContextSpi()  [API 1+]
+ engineCreateSSLEngine(): SSLEngine  [API 1+]
+ engineCreateSSLEngine(String, int): SSLEngine  [API 1+]
+ engineGetClientSessionContext(): SSLSessionContext  [API 1+]
- engineGetDefaultSSLParameters(): SSLParameters  [API 9+]
+ engineGetServerSessionContext(): SSLSessionContext  [API 1+]
+ engineGetServerSocketFactory(): SSLServerSocketFactory  [API 1+]
+ engineGetSocketFactory(): SSLSocketFactory  [API 1+]
- engineGetSupportedSSLParameters(): SSLParameters  [API 9+]
+ engineInit(KeyManager[], TrustManager[], SecureRandom): void  [API 1+]
```

## SSLEngine — declaration; API 1+ (24 +, 0 *, 0 ~, 15 -)

```
- SSLEngine()  [API 1+]
- SSLEngine(String, int)  [API 1+]
+ beginHandshake(): void  [API 1+]
+ closeInbound(): void  [API 1+]
+ closeOutbound(): void  [API 1+]
- getApplicationProtocol(): String  [API 29+]
+ getDelegatedTask(): Runnable  [API 1+]
+ getEnableSessionCreation(): boolean  [API 1+]
+ getEnabledCipherSuites(): String[]  [API 1+]
+ getEnabledProtocols(): String[]  [API 1+]
- getHandshakeApplicationProtocol(): String  [API 29+]
- getHandshakeApplicationProtocolSelector(): BiFunction  [API 29+]
- getHandshakeSession(): SSLSession  [API 24+]
+ getHandshakeStatus(): SSLEngineResult.HandshakeStatus  [API 1+]
+ getNeedClientAuth(): boolean  [API 1+]
- getPeerHost(): String  [API 1+]
- getPeerPort(): int  [API 1+]
- getSSLParameters(): SSLParameters  [API 9+]
+ getSession(): SSLSession  [API 1+]
+ getSupportedCipherSuites(): String[]  [API 1+]
+ getSupportedProtocols(): String[]  [API 1+]
+ getUseClientMode(): boolean  [API 1+]
+ getWantClientAuth(): boolean  [API 1+]
+ isInboundDone(): boolean  [API 1+]
+ isOutboundDone(): boolean  [API 1+]
+ setEnableSessionCreation(boolean): void  [API 1+]
+ setEnabledCipherSuites(String[]): void  [API 1+]
+ setEnabledProtocols(String[]): void  [API 1+]
- setHandshakeApplicationProtocolSelector(BiFunction): void  [API 29+]
+ setNeedClientAuth(boolean): void  [API 1+]
- setSSLParameters(SSLParameters): void  [API 9+]
+ setUseClientMode(boolean): void  [API 1+]
+ setWantClientAuth(boolean): void  [API 1+]
- unwrap(ByteBuffer, ByteBuffer): SSLEngineResult  [API 1+]
- unwrap(ByteBuffer, ByteBuffer[]): SSLEngineResult  [API 1+]
+ unwrap(ByteBuffer, ByteBuffer[], int, int): SSLEngineResult  [API 1+]
- wrap(ByteBuffer, ByteBuffer): SSLEngineResult  [API 1+]
- wrap(ByteBuffer[], ByteBuffer): SSLEngineResult  [API 1+]
+ wrap(ByteBuffer[], int, int, ByteBuffer): SSLEngineResult  [API 1+]
```

## SSLEngineResult — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- SSLEngineResult(SSLEngineResult.Status, SSLEngineResult.HandshakeStatus, int, int)  [API 1+]
- bytesConsumed(): int  [API 1+]
- bytesProduced(): int  [API 1+]
- getHandshakeStatus(): SSLEngineResult.HandshakeStatus  [API 1+]
- getStatus(): SSLEngineResult.Status  [API 1+]
- toString(): String  [API 1+]
```

## SSLEngineResult.HandshakeStatus — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ FINISHED [field]  [API 1+]
+ NEED_TASK [field]  [API 1+]
+ NEED_UNWRAP [field]  [API 1+]
+ NEED_WRAP [field]  [API 1+]
+ NOT_HANDSHAKING [field]  [API 1+]
+ valueOf(String): SSLEngineResult.HandshakeStatus [static]  [API 1+]
+ values(): SSLEngineResult.HandshakeStatus[] [static]  [API 1+]
```

## SSLEngineResult.Status — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BUFFER_OVERFLOW [field]  [API 1+]
+ BUFFER_UNDERFLOW [field]  [API 1+]
+ CLOSED [field]  [API 1+]
+ OK [field]  [API 1+]
+ valueOf(String): SSLEngineResult.Status [static]  [API 1+]
+ values(): SSLEngineResult.Status[] [static]  [API 1+]
```

## SSLException — declaration; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ SSLException(String)  [API 1+]
+ SSLException(String, Throwable)  [API 1+]
+ SSLException(Throwable)  [API 1+]
```

## SSLHandshakeException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ SSLHandshakeException(String)  [API 1+]
```

## SSLKeyException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ SSLKeyException(String)  [API 1+]
```

## SSLParameters — declaration; API 9+ (0 +, 23 *, 0 ~, 0 -)

```
* SSLParameters()  [API 9+]
* SSLParameters(String[])  [API 9+]
* SSLParameters(String[], String[])  [API 9+]
* getAlgorithmConstraints(): AlgorithmConstraints  [API 24+]
* getApplicationProtocols(): String[]  [API 29+]
* getCipherSuites(): String[]  [API 9+]
* getEndpointIdentificationAlgorithm(): String  [API 24+]
* getNeedClientAuth(): boolean  [API 9+]
* getProtocols(): String[]  [API 9+]
* getSNIMatchers(): Collection  [API 24+]
* getServerNames(): List  [API 24+]
* getUseCipherSuitesOrder(): boolean  [API 24+]
* getWantClientAuth(): boolean  [API 9+]
* setAlgorithmConstraints(AlgorithmConstraints): void  [API 24+]
* setApplicationProtocols(String[]): void  [API 29+]
* setCipherSuites(String[]): void  [API 9+]
* setEndpointIdentificationAlgorithm(String): void  [API 24+]
* setNeedClientAuth(boolean): void  [API 9+]
* setProtocols(String[]): void  [API 9+]
* setSNIMatchers(Collection): void  [API 24+]
* setServerNames(List): void  [API 24+]
* setUseCipherSuitesOrder(boolean): void  [API 24+]
* setWantClientAuth(boolean): void  [API 9+]
```

## SSLPeerUnverifiedException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ SSLPeerUnverifiedException(String)  [API 1+]
```

## SSLPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- SSLPermission(String)  [API 1+]
- SSLPermission(String, String)  [API 1+]
```

## SSLProtocolException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ SSLProtocolException(String)  [API 1+]
```

## SSLServerSocket — declaration; API 1+ (14 +, 3 *, 0 ~, 4 -)

```
* SSLServerSocket()  [API 1+]
- SSLServerSocket(int)  [API 1+]
- SSLServerSocket(int, int)  [API 1+]
- SSLServerSocket(int, int, InetAddress)  [API 1+]
+ getEnableSessionCreation(): boolean  [API 1+]
+ getEnabledCipherSuites(): String[]  [API 1+]
+ getEnabledProtocols(): String[]  [API 1+]
+ getNeedClientAuth(): boolean  [API 1+]
* getSSLParameters(): SSLParameters  [API 24+]
+ getSupportedCipherSuites(): String[]  [API 1+]
+ getSupportedProtocols(): String[]  [API 1+]
+ getUseClientMode(): boolean  [API 1+]
+ getWantClientAuth(): boolean  [API 1+]
+ setEnableSessionCreation(boolean): void  [API 1+]
+ setEnabledCipherSuites(String[]): void  [API 1+]
+ setEnabledProtocols(String[]): void  [API 1+]
+ setNeedClientAuth(boolean): void  [API 1+]
* setSSLParameters(SSLParameters): void  [API 24+]
+ setUseClientMode(boolean): void  [API 1+]
+ setWantClientAuth(boolean): void  [API 1+]
- toString(): String  [API 1+]
```

## SSLServerSocketFactory — declaration; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
- SSLServerSocketFactory()  [API 1+]
- getDefault(): ServerSocketFactory [static]  [API 1+]
+ getDefaultCipherSuites(): String[]  [API 1+]
+ getSupportedCipherSuites(): String[]  [API 1+]
```

## SSLSession — declaration; API 1+ (21 +, 0 *, 0 ~, 0 -)

```
+ getApplicationBufferSize(): int  [API 1+]
+ getCipherSuite(): String  [API 1+]
+ getCreationTime(): long  [API 1+]
+ getId(): byte[]  [API 1+]
+ getLastAccessedTime(): long  [API 1+]
+ getLocalCertificates(): Certificate[]  [API 1+]
+ getLocalPrincipal(): Principal  [API 1+]
+ getPacketBufferSize(): int  [API 1+]
+ getPeerCertificateChain(): X509Certificate[]  [API 1+]
+ getPeerCertificates(): Certificate[]  [API 1+]
+ getPeerHost(): String  [API 1+]
+ getPeerPort(): int  [API 1+]
+ getPeerPrincipal(): Principal  [API 1+]
+ getProtocol(): String  [API 1+]
+ getSessionContext(): SSLSessionContext  [API 1+]
+ getValue(String): Object  [API 1+]
+ getValueNames(): String[]  [API 1+]
+ invalidate(): void  [API 1+]
+ isValid(): boolean  [API 1+]
+ putValue(String, Object): void  [API 1+]
+ removeValue(String): void  [API 1+]
```

## SSLSessionBindingEvent — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- SSLSessionBindingEvent(SSLSession, String)  [API 1+]
- getName(): String  [API 1+]
- getSession(): SSLSession  [API 1+]
```

## SSLSessionBindingListener — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ valueBound(SSLSessionBindingEvent): void  [API 1+]
+ valueUnbound(SSLSessionBindingEvent): void  [API 1+]
```

## SSLSessionContext — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ getIds(): Enumeration  [API 1+]
+ getSession(byte[]): SSLSession  [API 1+]
+ getSessionCacheSize(): int  [API 1+]
+ getSessionTimeout(): int  [API 1+]
+ setSessionCacheSize(int): void  [API 1+]
+ setSessionTimeout(int): void  [API 1+]
```

## SSLSocket — declaration; API 1+ (18 +, 5 *, 0 ~, 8 -)

```
* SSLSocket()  [API 1+]
- SSLSocket(InetAddress, int)  [API 1+]
- SSLSocket(InetAddress, int, InetAddress, int)  [API 1+]
- SSLSocket(String, int)  [API 1+]
- SSLSocket(String, int, InetAddress, int)  [API 1+]
+ addHandshakeCompletedListener(HandshakeCompletedListener): void  [API 1+]
- getApplicationProtocol(): String  [API 29+]
+ getEnableSessionCreation(): boolean  [API 1+]
+ getEnabledCipherSuites(): String[]  [API 1+]
+ getEnabledProtocols(): String[]  [API 1+]
- getHandshakeApplicationProtocol(): String  [API 29+]
* getHandshakeApplicationProtocolSelector(): BiFunction  [API 29+]
- getHandshakeSession(): SSLSession  [API 24+]
+ getNeedClientAuth(): boolean  [API 1+]
* getSSLParameters(): SSLParameters  [API 9+]
+ getSession(): SSLSession  [API 1+]
+ getSupportedCipherSuites(): String[]  [API 1+]
+ getSupportedProtocols(): String[]  [API 1+]
+ getUseClientMode(): boolean  [API 1+]
+ getWantClientAuth(): boolean  [API 1+]
+ removeHandshakeCompletedListener(HandshakeCompletedListener): void  [API 1+]
+ setEnableSessionCreation(boolean): void  [API 1+]
+ setEnabledCipherSuites(String[]): void  [API 1+]
+ setEnabledProtocols(String[]): void  [API 1+]
* setHandshakeApplicationProtocolSelector(BiFunction): void  [API 29+]
+ setNeedClientAuth(boolean): void  [API 1+]
* setSSLParameters(SSLParameters): void  [API 9+]
+ setUseClientMode(boolean): void  [API 1+]
+ setWantClientAuth(boolean): void  [API 1+]
+ startHandshake(): void  [API 1+]
- toString(): String  [API 1+]
```

## SSLSocketFactory — declaration; API 1+ (3 +, 0 *, 0 ~, 2 -)

```
- SSLSocketFactory()  [API 1+]
+ createSocket(Socket, String, int, boolean): Socket  [API 1+]
- getDefault(): SocketFactory [static]  [API 1+]
+ getDefaultCipherSuites(): String[]  [API 1+]
+ getSupportedCipherSuites(): String[]  [API 1+]
```

## StandardConstants — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## TrustManager — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## TrustManagerFactory — declaration; API 1+ (0 +, 0 *, 0 ~, 10 -)

```
- TrustManagerFactory(TrustManagerFactorySpi, Provider, String)  [API 1+]
- getAlgorithm(): String  [API 1+]
- getDefaultAlgorithm(): String [static]  [API 1+]
- getInstance(String): TrustManagerFactory [static]  [API 1+]
- getInstance(String, Provider): TrustManagerFactory [static]  [API 1+]
- getInstance(String, String): TrustManagerFactory [static]  [API 1+]
- getProvider(): Provider  [API 1+]
- getTrustManagers(): TrustManager[]  [API 1+]
- init(KeyStore): void  [API 1+]
- init(ManagerFactoryParameters): void  [API 1+]
```

## TrustManagerFactorySpi — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ TrustManagerFactorySpi()  [API 1+]
+ engineGetTrustManagers(): TrustManager[]  [API 1+]
+ engineInit(KeyStore): void  [API 1+]
+ engineInit(ManagerFactoryParameters): void  [API 1+]
```

## X509ExtendedKeyManager — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- X509ExtendedKeyManager()  [API 1+]
- chooseEngineClientAlias(String[], Principal[], SSLEngine): String  [API 1+]
- chooseEngineServerAlias(String, Principal[], SSLEngine): String  [API 1+]
```

## X509ExtendedTrustManager — declaration; API 24+ (5 +, 0 *, 0 ~, 0 -)

```
+ X509ExtendedTrustManager()  [API 24+]
+ checkClientTrusted(X509Certificate[], String, SSLEngine): void  [API 24+]
+ checkClientTrusted(X509Certificate[], String, Socket): void  [API 24+]
+ checkServerTrusted(X509Certificate[], String, SSLEngine): void  [API 24+]
+ checkServerTrusted(X509Certificate[], String, Socket): void  [API 24+]
```

## X509KeyManager — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ chooseClientAlias(String[], Principal[], Socket): String  [API 1+]
+ chooseServerAlias(String, Principal[], Socket): String  [API 1+]
+ getCertificateChain(String): X509Certificate[]  [API 1+]
+ getClientAliases(String, Principal[]): String[]  [API 1+]
+ getPrivateKey(String): PrivateKey  [API 1+]
+ getServerAliases(String, Principal[]): String[]  [API 1+]
```

## X509TrustManager — declaration; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ checkClientTrusted(X509Certificate[], String): void  [API 1+]
+ checkServerTrusted(X509Certificate[], String): void  [API 1+]
+ getAcceptedIssuers(): X509Certificate[]  [API 1+]
```


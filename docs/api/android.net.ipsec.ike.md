# android.net.ipsec.ike

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ChildSaProposal — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- equals(Object): boolean  [API 31+]
- getSupportedEncryptionAlgorithms(): Set [static]  [API 31+]
- getSupportedIntegrityAlgorithms(): Set [static]  [API 31+]
- hashCode(): int  [API 31+]
```

## ChildSaProposal.Builder — declaration; API 31+ (0 +, 0 *, 0 ~, 5 -)

```
- Builder()  [API 31+]
- addDhGroup(int): ChildSaProposal.Builder  [API 31+]
- addEncryptionAlgorithm(int, int): ChildSaProposal.Builder  [API 31+]
- addIntegrityAlgorithm(int): ChildSaProposal.Builder  [API 31+]
- build(): ChildSaProposal  [API 31+]
```

## ChildSessionCallback — declaration; API 31+ (5 +, 0 *, 0 ~, 0 -)

```
+ onClosed(): void  [API 31+]
+ onClosedWithException(IkeException): void  [API 31+]
+ onIpSecTransformCreated(IpSecTransform, int): void  [API 31+]
+ onIpSecTransformDeleted(IpSecTransform, int): void  [API 31+]
+ onOpened(ChildSessionConfiguration): void  [API 31+]
```

## ChildSessionConfiguration — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- getInboundTrafficSelectors(): List  [API 31+]
- getOutboundTrafficSelectors(): List  [API 31+]
```

## ChildSessionConfiguration.Builder — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- Builder(List, List)  [API 31+]
- build(): ChildSessionConfiguration  [API 31+]
```

## ChildSessionParams — declaration; API 31+ (0 +, 7 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 31+]
* getChildSaProposals(): List  [API 31+]
* getHardLifetimeSeconds(): int  [API 31+]
* getInboundTrafficSelectors(): List  [API 31+]
* getOutboundTrafficSelectors(): List  [API 31+]
* getSoftLifetimeSeconds(): int  [API 31+]
* hashCode(): int  [API 31+]
```

## IkeDerAsn1DnIdentification — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- IkeDerAsn1DnIdentification(X500Principal)  [API 31+]
- derAsn1Dn [field]  [API 31+]
- equals(Object): boolean  [API 31+]
- hashCode(): int  [API 31+]
```

## IkeFqdnIdentification — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- IkeFqdnIdentification(String)  [API 31+]
- equals(Object): boolean  [API 31+]
- fqdn [field]  [API 31+]
- hashCode(): int  [API 31+]
```

## IkeIdentification — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## IkeIpv4AddrIdentification — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- IkeIpv4AddrIdentification(Inet4Address)  [API 31+]
- equals(Object): boolean  [API 31+]
- hashCode(): int  [API 31+]
- ipv4Address [field]  [API 31+]
```

## IkeIpv6AddrIdentification — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- IkeIpv6AddrIdentification(Inet6Address)  [API 31+]
- equals(Object): boolean  [API 31+]
- hashCode(): int  [API 31+]
- ipv6Address [field]  [API 31+]
```

## IkeKeyIdIdentification — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- IkeKeyIdIdentification(byte[])  [API 31+]
- equals(Object): boolean  [API 31+]
- hashCode(): int  [API 31+]
- keyId [field]  [API 31+]
```

## IkeRfc822AddrIdentification — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- IkeRfc822AddrIdentification(String)  [API 31+]
- equals(Object): boolean  [API 31+]
- hashCode(): int  [API 31+]
- rfc822Name [field]  [API 31+]
```

## IkeSaProposal — declaration; API 31+ (0 +, 0 *, 0 ~, 6 -)

```
- equals(Object): boolean  [API 31+]
- getPseudorandomFunctions(): List  [API 31+]
- getSupportedEncryptionAlgorithms(): Set [static]  [API 31+]
- getSupportedIntegrityAlgorithms(): Set [static]  [API 31+]
- getSupportedPseudorandomFunctions(): Set [static]  [API 31+]
- hashCode(): int  [API 31+]
```

## IkeSaProposal.Builder — declaration; API 31+ (0 +, 0 *, 0 ~, 6 -)

```
- Builder()  [API 31+]
- addDhGroup(int): IkeSaProposal.Builder  [API 31+]
- addEncryptionAlgorithm(int, int): IkeSaProposal.Builder  [API 31+]
- addIntegrityAlgorithm(int): IkeSaProposal.Builder  [API 31+]
- addPseudorandomFunction(int): IkeSaProposal.Builder  [API 31+]
- build(): IkeSaProposal  [API 31+]
```

## IkeSession — declaration; API 31+ (0 +, 0 *, 0 ~, 7 -)

```
- IkeSession(Context, IkeSessionParams, ChildSessionParams, Executor, IkeSessionCallback, ChildSessionCallback)  [API 31+]
- close(): void  [API 31+]
- closeChildSession(ChildSessionCallback): void  [API 31+]
- dump(PrintWriter): void  [API 35+]
- finalize(): void  [API 31+]
- kill(): void  [API 31+]
- openChildSession(ChildSessionParams, ChildSessionCallback): void  [API 31+]
```

## IkeSessionCallback — declaration; API 31+ (4 +, 0 *, 0 ~, 0 -)

```
+ onClosed(): void  [API 31+]
+ onClosedWithException(IkeException): void  [API 31+]
+ onError(IkeException): void  [API 31+]
+ onOpened(IkeSessionConfiguration): void  [API 31+]
```

## IkeSessionConfiguration — declaration; API 31+ (0 +, 3 *, 0 ~, 2 -)

```
* getEapInfo(): EapInfo  [API 33+]
- getIkeSessionConnectionInfo(): IkeSessionConnectionInfo  [API 31+]
* getRemoteApplicationVersion(): String  [API 31+]
* getRemoteVendorIds(): List  [API 31+]
- isIkeExtensionEnabled(int): boolean  [API 31+]
```

## IkeSessionConfiguration.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 4 -)

```
- Builder(IkeSessionConnectionInfo)  [API 31+]
- addIkeExtension(int): IkeSessionConfiguration.Builder  [API 31+]
* addRemoteVendorId(byte[]): IkeSessionConfiguration.Builder  [API 31+]
* build(): IkeSessionConfiguration  [API 31+]
- clearIkeExtensions(): IkeSessionConfiguration.Builder  [API 31+]
- clearRemoteApplicationVersion(): IkeSessionConfiguration.Builder  [API 31+]
* clearRemoteVendorIds(): IkeSessionConfiguration.Builder  [API 31+]
* setEapInfo(EapInfo): IkeSessionConfiguration.Builder  [API 33+]
* setRemoteApplicationVersion(String): IkeSessionConfiguration.Builder  [API 31+]
```

## IkeSessionConnectionInfo — declaration; API 31+ (0 +, 4 *, 0 ~, 0 -)

```
* IkeSessionConnectionInfo(InetAddress, InetAddress, Network)  [API 31+]
* getLocalAddress(): InetAddress  [API 31+]
* getNetwork(): Network  [API 31+]
* getRemoteAddress(): InetAddress  [API 31+]
```

## IkeSessionParams — declaration; API 31+ (0 +, 15 *, 0 ~, 1 -)

```
* equals(Object): boolean  [API 31+]
* getDpdDelaySeconds(): int  [API 31+]
* getHardLifetimeSeconds(): int  [API 31+]
* getIkeOptions(): Set  [API 35+]
* getIkeSaProposals(): List  [API 31+]
* getLocalAuthConfig(): IkeSessionParams.IkeAuthConfig  [API 31+]
* getLocalIdentification(): IkeIdentification  [API 31+]
* getNattKeepAliveDelaySeconds(): int  [API 31+]
* getNetwork(): Network  [API 31+]
* getRemoteAuthConfig(): IkeSessionParams.IkeAuthConfig  [API 31+]
* getRemoteIdentification(): IkeIdentification  [API 31+]
* getRetransmissionTimeoutsMillis(): int[]  [API 31+]
* getServerHostname(): String  [API 31+]
* getSoftLifetimeSeconds(): int  [API 31+]
- hasIkeOption(int): boolean  [API 31+]
* hashCode(): int  [API 31+]
```

## IkeSessionParams.Builder — declaration; API 31+ (0 +, 11 *, 0 ~, 7 -)

```
* Builder()  [API 31+]
* Builder(IkeSessionParams)  [API 31+]
- addIkeOption(int): IkeSessionParams.Builder  [API 31+]
* addIkeSaProposal(IkeSaProposal): IkeSessionParams.Builder  [API 31+]
* build(): IkeSessionParams  [API 31+]
- removeIkeOption(int): IkeSessionParams.Builder  [API 31+]
- setAuthDigitalSignature(X509Certificate, X509Certificate, List, PrivateKey): IkeSessionParams.Builder  [API 31+]
- setAuthDigitalSignature(X509Certificate, X509Certificate, PrivateKey): IkeSessionParams.Builder  [API 31+]
- setAuthEap(X509Certificate, EapSessionConfig): IkeSessionParams.Builder  [API 31+]
- setAuthPsk(byte[]): IkeSessionParams.Builder  [API 31+]
* setDpdDelaySeconds(int): IkeSessionParams.Builder  [API 31+]
- setLifetimeSeconds(int, int): IkeSessionParams.Builder  [API 31+]
* setLocalIdentification(IkeIdentification): IkeSessionParams.Builder  [API 31+]
* setNattKeepAliveDelaySeconds(int): IkeSessionParams.Builder  [API 31+]
* setNetwork(Network): IkeSessionParams.Builder  [API 31+]
* setRemoteIdentification(IkeIdentification): IkeSessionParams.Builder  [API 31+]
* setRetransmissionTimeoutsMillis(int[]): IkeSessionParams.Builder  [API 31+]
* setServerHostname(String): IkeSessionParams.Builder  [API 31+]
```

## IkeSessionParams.IkeAuthConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- equals(Object): boolean  [API 31+]
- hashCode(): int  [API 31+]
```

## IkeSessionParams.IkeAuthDigitalSignLocalConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 5 -)

```
- equals(Object): boolean  [API 31+]
- getClientEndCertificate(): X509Certificate  [API 31+]
- getIntermediateCertificates(): List  [API 31+]
- getPrivateKey(): PrivateKey  [API 31+]
- hashCode(): int  [API 31+]
```

## IkeSessionParams.IkeAuthDigitalSignRemoteConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- equals(Object): boolean  [API 31+]
- getRemoteCaCert(): X509Certificate  [API 31+]
- hashCode(): int  [API 31+]
```

## IkeSessionParams.IkeAuthEapConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- equals(Object): boolean  [API 31+]
- getEapConfig(): EapSessionConfig  [API 31+]
- hashCode(): int  [API 31+]
```

## IkeSessionParams.IkeAuthPskConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- equals(Object): boolean  [API 31+]
- getPsk(): byte[]  [API 31+]
- hashCode(): int  [API 31+]
```

## IkeTrafficSelector — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- IkeTrafficSelector(int, int, InetAddress, InetAddress)  [API 31+]
- endPort [field]  [API 31+]
- endingAddress [field]  [API 31+]
- equals(Object): boolean  [API 31+]
- hashCode(): int  [API 31+]
- startPort [field]  [API 31+]
- startingAddress [field]  [API 31+]
```

## IkeTunnelConnectionParams — declaration; API 31+ (0 +, 0 *, 0 ~, 5 -)

```
- IkeTunnelConnectionParams(IkeSessionParams, TunnelModeChildSessionParams)  [API 31+]
- equals(Object): boolean  [API 31+]
- getIkeSessionParams(): IkeSessionParams  [API 31+]
- getTunnelModeChildSessionParams(): TunnelModeChildSessionParams  [API 31+]
- hashCode(): int  [API 31+]
```

## SaProposal — declaration; API 31+ (0 +, 0 *, 0 ~, 7 -)

```
- equals(Object): boolean  [API 31+]
- getDhGroups(): List  [API 31+]
- getEncryptionAlgorithms(): List  [API 31+]
- getIntegrityAlgorithms(): List  [API 31+]
- getSupportedDhGroups(): Set [static]  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
```

## TransportModeChildSessionParams — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## TransportModeChildSessionParams.Builder — declaration; API 31+ (0 +, 6 *, 0 ~, 1 -)

```
* Builder()  [API 31+]
* Builder(TransportModeChildSessionParams)  [API 31+]
* addChildSaProposal(ChildSaProposal): TransportModeChildSessionParams.Builder  [API 31+]
* addInboundTrafficSelectors(IkeTrafficSelector): TransportModeChildSessionParams.Builder  [API 31+]
* addOutboundTrafficSelectors(IkeTrafficSelector): TransportModeChildSessionParams.Builder  [API 31+]
* build(): TransportModeChildSessionParams  [API 31+]
- setLifetimeSeconds(int, int): TransportModeChildSessionParams.Builder  [API 31+]
```

## TunnelModeChildSessionParams — declaration; API 31+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 31+]
* getConfigurationRequests(): List  [API 31+]
* hashCode(): int  [API 31+]
```

## TunnelModeChildSessionParams.Builder — declaration; API 31+ (0 +, 6 *, 0 ~, 6 -)

```
* Builder()  [API 31+]
* Builder(TunnelModeChildSessionParams)  [API 31+]
* addChildSaProposal(ChildSaProposal): TunnelModeChildSessionParams.Builder  [API 31+]
* addInboundTrafficSelectors(IkeTrafficSelector): TunnelModeChildSessionParams.Builder  [API 31+]
- addInternalAddressRequest(Inet4Address): TunnelModeChildSessionParams.Builder  [API 31+]
- addInternalAddressRequest(Inet6Address, int): TunnelModeChildSessionParams.Builder  [API 31+]
- addInternalAddressRequest(int): TunnelModeChildSessionParams.Builder  [API 31+]
- addInternalDhcpServerRequest(int): TunnelModeChildSessionParams.Builder  [API 31+]
- addInternalDnsServerRequest(int): TunnelModeChildSessionParams.Builder  [API 31+]
* addOutboundTrafficSelectors(IkeTrafficSelector): TunnelModeChildSessionParams.Builder  [API 31+]
* build(): TunnelModeChildSessionParams  [API 31+]
- setLifetimeSeconds(int, int): TunnelModeChildSessionParams.Builder  [API 31+]
```

## TunnelModeChildSessionParams.ConfigRequestIpv4Address — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ getAddress(): Inet4Address  [API 31+]
```

## TunnelModeChildSessionParams.ConfigRequestIpv4DhcpServer — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## TunnelModeChildSessionParams.ConfigRequestIpv4DnsServer — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## TunnelModeChildSessionParams.ConfigRequestIpv4Netmask — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## TunnelModeChildSessionParams.ConfigRequestIpv6Address — declaration; API 31+ (2 +, 0 *, 0 ~, 0 -)

```
+ getAddress(): Inet6Address  [API 31+]
+ getPrefixLength(): int  [API 31+]
```

## TunnelModeChildSessionParams.ConfigRequestIpv6DnsServer — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## TunnelModeChildSessionParams.TunnelModeChildConfigRequest — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)


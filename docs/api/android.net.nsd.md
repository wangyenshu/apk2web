# android.net.nsd

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DiscoveryRequest — declaration; API 35+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getNetwork(): Network  [API 35+]
* getServiceType(): String  [API 35+]
* getSubtype(): String  [API 35+]
* hashCode(): int  [API 35+]
* toString(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## DiscoveryRequest.Builder — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder(String)  [API 35+]
* build(): DiscoveryRequest  [API 35+]
* setNetwork(Network): DiscoveryRequest.Builder  [API 35+]
* setSubtype(String): DiscoveryRequest.Builder  [API 35+]
```

## NsdManager — framework; API 16+ (0 +, 0 *, 0 ~, 13 -)

```
- discoverServices(DiscoveryRequest, Executor, NsdManager.DiscoveryListener): void  [API 35+]
- discoverServices(String, int, Network, Executor, NsdManager.DiscoveryListener): void  [API 33+]
- discoverServices(String, int, NetworkRequest, Executor, NsdManager.DiscoveryListener): void  [API 33+]
- discoverServices(String, int, NsdManager.DiscoveryListener): void  [API 16+]
- registerService(NsdServiceInfo, int, Executor, NsdManager.RegistrationListener): void  [API 33+]
- registerService(NsdServiceInfo, int, NsdManager.RegistrationListener): void  [API 16+]
- registerServiceInfoCallback(NsdServiceInfo, Executor, NsdManager.ServiceInfoCallback): void  [API 34+]
- resolveService(NsdServiceInfo, Executor, NsdManager.ResolveListener): void  [API 33+, deprecated in 36]
- resolveService(NsdServiceInfo, NsdManager.ResolveListener): void  [API 16+, deprecated in 36]
- stopServiceDiscovery(NsdManager.DiscoveryListener): void  [API 16+]
- stopServiceResolution(NsdManager.ResolveListener): void  [API 34+]
- unregisterService(NsdManager.RegistrationListener): void  [API 16+]
- unregisterServiceInfoCallback(NsdManager.ServiceInfoCallback): void  [API 34+]
```

## NsdManager.DiscoveryListener — framework; API 16+ (6 +, 0 *, 0 ~, 0 -)

```
+ onDiscoveryStarted(String): void  [API 16+]
+ onDiscoveryStopped(String): void  [API 16+]
+ onServiceFound(NsdServiceInfo): void  [API 16+]
+ onServiceLost(NsdServiceInfo): void  [API 16+]
+ onStartDiscoveryFailed(String, int): void  [API 16+]
+ onStopDiscoveryFailed(String, int): void  [API 16+]
```

## NsdManager.RegistrationListener — framework; API 16+ (4 +, 0 *, 0 ~, 0 -)

```
+ onRegistrationFailed(NsdServiceInfo, int): void  [API 16+]
+ onServiceRegistered(NsdServiceInfo): void  [API 16+]
+ onServiceUnregistered(NsdServiceInfo): void  [API 16+]
+ onUnregistrationFailed(NsdServiceInfo, int): void  [API 16+]
```

## NsdManager.ResolveListener — framework; API 16+ (4 +, 0 *, 0 ~, 0 -)

```
+ onResolutionStopped(NsdServiceInfo): void  [API 34+]
+ onResolveFailed(NsdServiceInfo, int): void  [API 16+]
+ onServiceResolved(NsdServiceInfo): void  [API 16+]
+ onStopResolutionFailed(NsdServiceInfo, int): void  [API 34+]
```

## NsdManager.ServiceInfoCallback — framework; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ onServiceInfoCallbackRegistrationFailed(int): void  [API 34+]
+ onServiceInfoCallbackUnregistered(): void  [API 34+]
+ onServiceLost(): void  [API 34+]
+ onServiceUpdated(NsdServiceInfo): void  [API 34+]
```

## NsdServiceInfo — declaration; API 16+ (0 +, 15 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 16+]
* NsdServiceInfo()  [API 16+]
- describeContents(): int  [API 16+]
- getAttributes(): Map  [API 21+]
* getHost(): InetAddress  [API 16+, deprecated in 36]
* getHostAddresses(): List  [API 34+]
* getNetwork(): Network  [API 33+]
* getPort(): int  [API 16+]
* getServiceName(): String  [API 16+]
* getServiceType(): String  [API 16+]
* getSubtypes(): Set  [API 35+]
- removeAttribute(String): void  [API 21+]
- setAttribute(String, String): void  [API 21+]
* setHost(InetAddress): void  [API 16+, deprecated in 36]
* setHostAddresses(List): void  [API 34+]
* setNetwork(Network): void  [API 33+]
* setPort(int): void  [API 16+]
* setServiceName(String): void  [API 16+]
* setServiceType(String): void  [API 16+]
* setSubtypes(Set): void  [API 35+]
- toString(): String  [API 16+]
- writeToParcel(Parcel, int): void  [API 16+]
```


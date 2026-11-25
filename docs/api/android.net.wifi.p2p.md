# android.net.wifi.p2p

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## WifiP2pConfig — declaration; API 14+ (5 +, 3 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 14+]
- WifiP2pConfig()  [API 14+]
- WifiP2pConfig(WifiP2pConfig)  [API 14+]
+ describeContents(): int  [API 14+]
+ deviceAddress [field]  [API 14+]
* getGroupClientIpProvisioningMode(): int  [API 34+]
+ getGroupOwnerBand(): int  [API 30+]
+ getNetworkId(): int  [API 30+]
* getNetworkName(): String  [API 30+]
* getPassphrase(): String  [API 30+]
+ groupOwnerIntent [field]  [API 14+]
+ toString(): String  [API 14+]
+ wps [field]  [API 14+]
+ writeToParcel(Parcel, int): void  [API 14+]
```

## WifiP2pConfig.Builder — declaration; API 29+ (4 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 29+]
* build(): WifiP2pConfig  [API 29+]
+ enablePersistentMode(boolean): WifiP2pConfig.Builder  [API 29+]
+ setDeviceAddress(MacAddress): WifiP2pConfig.Builder  [API 29+]
* setGroupClientIpProvisioningMode(int): WifiP2pConfig.Builder  [API 34+]
+ setGroupOperatingBand(int): WifiP2pConfig.Builder  [API 29+]
+ setGroupOperatingFrequency(int): WifiP2pConfig.Builder  [API 29+]
* setNetworkName(String): WifiP2pConfig.Builder  [API 29+]
* setPassphrase(String): WifiP2pConfig.Builder  [API 29+]
```

## WifiP2pDevice — declaration; API 14+ (14 +, 0 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 14+]
- WifiP2pDevice()  [API 14+]
- WifiP2pDevice(WifiP2pDevice)  [API 14+]
+ describeContents(): int  [API 14+]
+ deviceAddress [field]  [API 14+]
+ deviceName [field]  [API 14+]
+ equals(Object): boolean  [API 14+]
+ getIpAddress(): InetAddress  [API 35+]
+ getVendorElements(): List  [API 33+]
+ getWfdInfo(): WifiP2pWfdInfo  [API 30+]
+ hashCode(): int  [API 14+]
+ isGroupOwner(): boolean  [API 14+]
+ isServiceDiscoveryCapable(): boolean  [API 14+]
+ primaryDeviceType [field]  [API 14+]
+ secondaryDeviceType [field]  [API 14+]
+ status [field]  [API 14+]
+ toString(): String  [API 14+]
+ update(WifiP2pDevice): void  [API 30+]
+ wpsDisplaySupported(): boolean  [API 14+]
+ wpsKeypadSupported(): boolean  [API 14+]
+ wpsPbcSupported(): boolean  [API 14+]
+ writeToParcel(Parcel, int): void  [API 14+]
```

## WifiP2pDeviceList — declaration; API 14+ (5 +, 0 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 14+]
- WifiP2pDeviceList()  [API 14+]
- WifiP2pDeviceList(WifiP2pDeviceList)  [API 14+]
+ describeContents(): int  [API 14+]
+ get(String): WifiP2pDevice  [API 18+]
+ getDeviceList(): Collection  [API 14+]
+ toString(): String  [API 14+]
+ writeToParcel(Parcel, int): void  [API 14+]
```

## WifiP2pDiscoveryConfig — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* getFrequencyMhz(): int  [API 35+]
* getScanType(): int  [API 35+]
* toString(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## WifiP2pDiscoveryConfig.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(int)  [API 35+]
* build(): WifiP2pDiscoveryConfig  [API 35+]
* setFrequencyMhz(int): WifiP2pDiscoveryConfig.Builder  [API 35+]
```

## WifiP2pGroup — declaration; API 14+ (11 +, 0 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 14+]
- WifiP2pGroup()  [API 14+]
- WifiP2pGroup(WifiP2pGroup)  [API 14+]
+ describeContents(): int  [API 14+]
+ getClientList(): Collection  [API 14+]
+ getFrequency(): int  [API 29+]
+ getInterface(): String  [API 14+]
+ getNetworkId(): int  [API 30+]
+ getNetworkName(): String  [API 14+]
+ getOwner(): WifiP2pDevice  [API 14+]
+ getPassphrase(): String  [API 14+]
+ isGroupOwner(): boolean  [API 14+]
+ toString(): String  [API 14+]
+ writeToParcel(Parcel, int): void  [API 14+]
```

## WifiP2pInfo — declaration; API 14+ (3 +, 0 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 14+]
- WifiP2pInfo()  [API 14+]
- WifiP2pInfo(WifiP2pInfo)  [API 14+]
+ describeContents(): int  [API 14+]
+ groupFormed [field]  [API 14+]
+ groupOwnerAddress [field]  [API 14+]
+ isGroupOwner [field]  [API 14+]
+ toString(): String  [API 14+]
+ writeToParcel(Parcel, int): void  [API 14+]
```

## WifiP2pManager — declaration; API 14+ (45 +, 0 *, 0 ~, 0 -)

```
+ addExternalApprover(WifiP2pManager.Channel, MacAddress, WifiP2pManager.ExternalApproverRequestListener): void  [API 33+]
+ addLocalService(WifiP2pManager.Channel, WifiP2pServiceInfo, WifiP2pManager.ActionListener): void  [API 16+]
+ addServiceRequest(WifiP2pManager.Channel, WifiP2pServiceRequest, WifiP2pManager.ActionListener): void  [API 16+]
+ cancelConnect(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 14+]
+ clearLocalServices(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 16+]
+ clearServiceRequests(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 16+]
+ connect(WifiP2pManager.Channel, WifiP2pConfig, WifiP2pManager.ActionListener): void  [API 14+]
+ createGroup(WifiP2pManager.Channel, WifiP2pConfig, WifiP2pManager.ActionListener): void  [API 29+]
+ createGroup(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 14+]
+ discoverPeers(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 14+]
+ discoverPeersOnSocialChannels(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 33+]
+ discoverPeersOnSpecificFrequency(WifiP2pManager.Channel, int, WifiP2pManager.ActionListener): void  [API 33+]
+ discoverServices(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 16+]
+ getListenState(WifiP2pManager.Channel, Executor, Consumer): void  [API 34+]
+ getP2pMaxAllowedVendorElementsLengthBytes(): int [static]  [API 33+]
+ initialize(Context, Looper, WifiP2pManager.ChannelListener): WifiP2pManager.Channel  [API 14+]
+ isChannelConstrainedDiscoverySupported(): boolean  [API 33+]
+ isGroupClientRemovalSupported(): boolean  [API 33+]
+ isGroupOwnerIPv6LinkLocalAddressProvided(): boolean  [API 34+]
+ isSetVendorElementsSupported(): boolean  [API 33+]
+ registerWifiP2pListener(Executor, WifiP2pManager.WifiP2pListener): void  [API 35+]
+ removeClient(WifiP2pManager.Channel, MacAddress, WifiP2pManager.ActionListener): void  [API 33+]
+ removeExternalApprover(WifiP2pManager.Channel, MacAddress, WifiP2pManager.ActionListener): void  [API 33+]
+ removeGroup(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 14+]
+ removeLocalService(WifiP2pManager.Channel, WifiP2pServiceInfo, WifiP2pManager.ActionListener): void  [API 16+]
+ removeServiceRequest(WifiP2pManager.Channel, WifiP2pServiceRequest, WifiP2pManager.ActionListener): void  [API 16+]
+ requestConnectionInfo(WifiP2pManager.Channel, WifiP2pManager.ConnectionInfoListener): void  [API 14+]
+ requestDeviceInfo(WifiP2pManager.Channel, WifiP2pManager.DeviceInfoListener): void  [API 29+]
+ requestDiscoveryState(WifiP2pManager.Channel, WifiP2pManager.DiscoveryStateListener): void  [API 29+]
+ requestGroupInfo(WifiP2pManager.Channel, WifiP2pManager.GroupInfoListener): void  [API 14+]
+ requestNetworkInfo(WifiP2pManager.Channel, WifiP2pManager.NetworkInfoListener): void  [API 29+]
+ requestP2pState(WifiP2pManager.Channel, WifiP2pManager.P2pStateListener): void  [API 29+]
+ requestPeers(WifiP2pManager.Channel, WifiP2pManager.PeerListListener): void  [API 14+]
+ setConnectionRequestResult(WifiP2pManager.Channel, MacAddress, int, String, WifiP2pManager.ActionListener): void  [API 33+]
+ setConnectionRequestResult(WifiP2pManager.Channel, MacAddress, int, WifiP2pManager.ActionListener): void  [API 33+]
+ setDnsSdResponseListeners(WifiP2pManager.Channel, WifiP2pManager.DnsSdServiceResponseListener, WifiP2pManager.DnsSdTxtRecordListener): void  [API 16+]
+ setServiceResponseListener(WifiP2pManager.Channel, WifiP2pManager.ServiceResponseListener): void  [API 16+]
+ setUpnpServiceResponseListener(WifiP2pManager.Channel, WifiP2pManager.UpnpServiceResponseListener): void  [API 16+]
+ setVendorElements(WifiP2pManager.Channel, List, WifiP2pManager.ActionListener): void  [API 33+]
+ setWfdInfo(WifiP2pManager.Channel, WifiP2pWfdInfo, WifiP2pManager.ActionListener): void  [API 34+]
+ startListening(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 33+]
+ startPeerDiscovery(WifiP2pManager.Channel, WifiP2pDiscoveryConfig, WifiP2pManager.ActionListener): void  [API 35+]
+ stopListening(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 33+]
+ stopPeerDiscovery(WifiP2pManager.Channel, WifiP2pManager.ActionListener): void  [API 16+]
+ unregisterWifiP2pListener(WifiP2pManager.WifiP2pListener): void  [API 35+]
```

## WifiP2pManager.ActionListener — declaration; API 14+ (2 +, 0 *, 0 ~, 0 -)

```
+ onFailure(int): void  [API 14+]
+ onSuccess(): void  [API 14+]
```

## WifiP2pManager.Channel — declaration; API 14+ (2 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 14+]
+ finalize(): void  [API 14+]
```

## WifiP2pManager.ChannelListener — declaration; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ onChannelDisconnected(): void  [API 14+]
```

## WifiP2pManager.ConnectionInfoListener — declaration; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ onConnectionInfoAvailable(WifiP2pInfo): void  [API 14+]
```

## WifiP2pManager.DeviceInfoListener — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDeviceInfoAvailable(WifiP2pDevice): void  [API 29+]
```

## WifiP2pManager.DiscoveryStateListener — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDiscoveryStateAvailable(int): void  [API 29+]
```

## WifiP2pManager.DnsSdServiceResponseListener — declaration; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDnsSdServiceAvailable(String, String, WifiP2pDevice): void  [API 16+]
```

## WifiP2pManager.DnsSdTxtRecordListener — declaration; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDnsSdTxtRecordAvailable(String, Map, WifiP2pDevice): void  [API 16+]
```

## WifiP2pManager.ExternalApproverRequestListener — declaration; API 33+ (4 +, 0 *, 0 ~, 0 -)

```
+ onAttached(MacAddress): void  [API 33+]
+ onConnectionRequested(int, WifiP2pConfig, WifiP2pDevice): void  [API 33+]
+ onDetached(MacAddress, int): void  [API 33+]
+ onPinGenerated(MacAddress, String): void  [API 33+]
```

## WifiP2pManager.GroupInfoListener — declaration; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGroupInfoAvailable(WifiP2pGroup): void  [API 14+]
```

## WifiP2pManager.NetworkInfoListener — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ onNetworkInfoAvailable(NetworkInfo): void  [API 29+]
```

## WifiP2pManager.P2pStateListener — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ onP2pStateAvailable(int): void  [API 29+]
```

## WifiP2pManager.PeerListListener — declaration; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPeersAvailable(WifiP2pDeviceList): void  [API 14+]
```

## WifiP2pManager.ServiceResponseListener — declaration; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onServiceAvailable(int, byte[], WifiP2pDevice): void  [API 16+]
```

## WifiP2pManager.UpnpServiceResponseListener — declaration; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onUpnpServiceAvailable(List, WifiP2pDevice): void  [API 16+]
```

## WifiP2pManager.WifiP2pListener — declaration; API 35+ (13 +, 0 *, 0 ~, 0 -)

```
+ onDeviceConfigurationChanged(WifiP2pDevice): void  [API 35+]
+ onDiscoveryStateChanged(int): void  [API 35+]
+ onFrequencyChanged(WifiP2pInfo, WifiP2pGroup): void  [API 35+]
+ onGroupCreated(WifiP2pInfo, WifiP2pGroup): void  [API 35+]
+ onGroupCreating(): void  [API 35+]
+ onGroupCreationFailed(int): void  [API 35+]
+ onGroupNegotiationRejectedByUser(): void  [API 35+]
+ onGroupRemoved(): void  [API 35+]
+ onListenStateChanged(int): void  [API 35+]
+ onP2pStateChanged(int): void  [API 35+]
+ onPeerClientDisconnected(WifiP2pInfo, WifiP2pGroup): void  [API 35+]
+ onPeerClientJoined(WifiP2pInfo, WifiP2pGroup): void  [API 35+]
+ onPeerListChanged(WifiP2pDeviceList): void  [API 35+]
```

## WifiP2pWfdInfo — declaration; API 30+ (14 +, 11 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 30+]
* WifiP2pWfdInfo()  [API 30+]
- WifiP2pWfdInfo(WifiP2pWfdInfo)  [API 30+]
+ describeContents(): int  [API 30+]
* getControlPort(): int  [API 30+]
+ getDeviceInfo(): int  [API 31+]
+ getDeviceType(): int  [API 30+]
* getMaxThroughput(): int  [API 30+]
+ getR2DeviceInfo(): int  [API 31+]
+ getR2DeviceType(): int  [API 31+]
* isContentProtectionSupported(): boolean  [API 30+]
+ isCoupledSinkSupportedAtSink(): boolean  [API 31+]
+ isCoupledSinkSupportedAtSource(): boolean  [API 31+]
* isEnabled(): boolean  [API 30+]
+ isR2Supported(): boolean  [API 31+]
* isSessionAvailable(): boolean  [API 30+]
* setContentProtectionSupported(boolean): void  [API 30+]
* setControlPort(int): void  [API 30+]
+ setCoupledSinkSupportAtSink(boolean): void  [API 31+]
+ setCoupledSinkSupportAtSource(boolean): void  [API 31+]
+ setDeviceType(int): boolean  [API 30+]
* setEnabled(boolean): void  [API 30+]
* setMaxThroughput(int): void  [API 30+]
+ setR2DeviceType(int): boolean  [API 31+]
* setSessionAvailable(boolean): void  [API 30+]
+ toString(): String  [API 30+]
+ writeToParcel(Parcel, int): void  [API 30+]
```


# android.media.midi

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## MidiDevice — declaration; API 23+ (7 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 23+]
+ connectPorts(MidiInputPort, int): MidiDevice.MidiConnection  [API 23+]
+ finalize(): void  [API 23+]
+ getInfo(): MidiDeviceInfo  [API 23+]
+ openInputPort(int): MidiInputPort  [API 23+]
+ openOutputPort(int): MidiOutputPort  [API 23+]
+ toString(): String  [API 23+]
```

## MidiDevice.MidiConnection — declaration; API 23+ (2 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 23+]
+ finalize(): void  [API 23+]
```

## MidiDeviceInfo — declaration; API 23+ (13 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 23+]
+ describeContents(): int  [API 23+]
+ equals(Object): boolean  [API 23+]
+ getDefaultProtocol(): int  [API 33+]
+ getId(): int  [API 23+]
+ getInputPortCount(): int  [API 23+]
+ getOutputPortCount(): int  [API 23+]
+ getPorts(): MidiDeviceInfo.PortInfo[]  [API 23+]
+ getProperties(): Bundle  [API 23+]
+ getType(): int  [API 23+]
+ hashCode(): int  [API 23+]
+ isPrivate(): boolean  [API 23+]
+ toString(): String  [API 23+]
+ writeToParcel(Parcel, int): void  [API 23+]
```

## MidiDeviceInfo.PortInfo — declaration; API 23+ (3 +, 0 *, 0 ~, 0 -)

```
+ getName(): String  [API 23+]
+ getPortNumber(): int  [API 23+]
+ getType(): int  [API 23+]
```

## MidiDeviceService — declaration; API 23+ (7 +, 0 *, 0 ~, 1 -)

```
- MidiDeviceService()  [API 23+]
+ getDeviceInfo(): MidiDeviceInfo  [API 23+]
+ getOutputPortReceivers(): MidiReceiver[]  [API 23+]
+ onBind(Intent): IBinder  [API 23+]
+ onClose(): void  [API 23+]
+ onCreate(): void  [API 23+]
+ onDeviceStatusChanged(MidiDeviceStatus): void  [API 23+]
+ onGetInputPortReceivers(): MidiReceiver[]  [API 23+]
```

## MidiDeviceStatus — declaration; API 23+ (6 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 23+]
+ describeContents(): int  [API 23+]
+ getDeviceInfo(): MidiDeviceInfo  [API 23+]
+ getOutputPortOpenCount(int): int  [API 23+]
+ isInputPortOpen(int): boolean  [API 23+]
+ toString(): String  [API 23+]
+ writeToParcel(Parcel, int): void  [API 23+]
```

## MidiInputPort — declaration; API 23+ (5 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 23+]
+ finalize(): void  [API 23+]
+ getPortNumber(): int  [API 23+]
+ onFlush(): void  [API 23+]
+ onSend(byte[], int, int, long): void  [API 23+]
```

## MidiManager — framework; API 23+ (7 +, 0 *, 0 ~, 0 -)

```
+ getDevices(): MidiDeviceInfo[]  [API 23+, deprecated in 33]
+ getDevicesForTransport(int): Set  [API 33+]
+ openBluetoothDevice(BluetoothDevice, MidiManager.OnDeviceOpenedListener, Handler): void  [API 23+]
+ openDevice(MidiDeviceInfo, MidiManager.OnDeviceOpenedListener, Handler): void  [API 23+]
+ registerDeviceCallback(MidiManager.DeviceCallback, Handler): void  [API 23+, deprecated in 33]
+ registerDeviceCallback(int, Executor, MidiManager.DeviceCallback): void  [API 33+]
+ unregisterDeviceCallback(MidiManager.DeviceCallback): void  [API 23+]
```

## MidiManager.DeviceCallback — framework; API 23+ (4 +, 0 *, 0 ~, 0 -)

```
+ DeviceCallback()  [API 23+]
+ onDeviceAdded(MidiDeviceInfo): void  [API 23+]
+ onDeviceRemoved(MidiDeviceInfo): void  [API 23+]
+ onDeviceStatusChanged(MidiDeviceStatus): void  [API 23+]
```

## MidiManager.OnDeviceOpenedListener — framework; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDeviceOpened(MidiDevice): void  [API 23+]
```

## MidiOutputPort — declaration; API 23+ (5 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 23+]
+ finalize(): void  [API 23+]
+ getPortNumber(): int  [API 23+]
+ onConnect(MidiReceiver): void  [API 23+]
+ onDisconnect(MidiReceiver): void  [API 23+]
```

## MidiReceiver — declaration; API 23+ (5 +, 2 *, 0 ~, 1 -)

```
- MidiReceiver()  [API 23+]
* MidiReceiver(int)  [API 23+]
+ flush(): void  [API 23+]
* getMaxMessageSize(): int  [API 23+]
+ onFlush(): void  [API 23+]
+ onSend(byte[], int, int, long): void  [API 23+]
+ send(byte[], int, int): void  [API 23+]
+ send(byte[], int, int, long): void  [API 23+]
```

## MidiSender — declaration; API 23+ (4 +, 0 *, 0 ~, 1 -)

```
- MidiSender()  [API 23+]
+ connect(MidiReceiver): void  [API 23+]
+ disconnect(MidiReceiver): void  [API 23+]
+ onConnect(MidiReceiver): void  [API 23+]
+ onDisconnect(MidiReceiver): void  [API 23+]
```

## MidiUmpDeviceService — declaration; API 35+ (7 +, 0 *, 0 ~, 1 -)

```
- MidiUmpDeviceService()  [API 35+]
+ getDeviceInfo(): MidiDeviceInfo  [API 35+]
+ getOutputPortReceivers(): List  [API 35+]
+ onBind(Intent): IBinder  [API 35+]
+ onClose(): void  [API 35+]
+ onCreate(): void  [API 35+]
+ onDeviceStatusChanged(MidiDeviceStatus): void  [API 35+]
+ onGetInputPortReceivers(): List  [API 35+]
```


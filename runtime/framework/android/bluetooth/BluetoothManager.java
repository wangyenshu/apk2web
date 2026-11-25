// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.bluetooth;

public final class BluetoothManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public BluetoothManager(a2w.Hidden hidden) { }
  @a2w.Absent public android.bluetooth.BluetoothAdapter getAdapter() { return null; }
  @a2w.Absent public java.util.List<android.bluetooth.BluetoothDevice> getConnectedDevices(int profile) { return java.util.Collections.emptyList(); }
  @a2w.Absent public int getConnectionState(android.bluetooth.BluetoothDevice device, int profile) { return 0; }
  @a2w.Absent public java.util.List<android.bluetooth.BluetoothDevice> getDevicesMatchingConnectionStates(int profile, int[] states) { return java.util.Collections.emptyList(); }
  @a2w.Absent public android.bluetooth.BluetoothGattServer openGattServer(android.content.Context context, android.bluetooth.BluetoothGattServerCallback callback) { return null; }
}

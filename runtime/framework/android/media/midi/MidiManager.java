// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.media.midi;

public final class MidiManager {
  public static final int TRANSPORT_MIDI_BYTE_STREAM = 1;
  public static final int TRANSPORT_UNIVERSAL_MIDI_PACKETS = 2;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public MidiManager(a2w.Hidden hidden) { }
  @a2w.Absent public android.media.midi.MidiDeviceInfo[] getDevices() { return new android.media.midi.MidiDeviceInfo[0]; }
  @a2w.Absent public java.util.Set<android.media.midi.MidiDeviceInfo> getDevicesForTransport(int transport) { return java.util.Collections.emptySet(); }
  @a2w.Absent public void openBluetoothDevice(android.bluetooth.BluetoothDevice bluetoothDevice, android.media.midi.MidiManager.OnDeviceOpenedListener listener, android.os.Handler handler) {  }
  @a2w.Absent public void openDevice(android.media.midi.MidiDeviceInfo deviceInfo, android.media.midi.MidiManager.OnDeviceOpenedListener listener, android.os.Handler handler) {  }
  @a2w.Absent public void registerDeviceCallback(int transport, java.util.concurrent.Executor executor, android.media.midi.MidiManager.DeviceCallback callback) {  }
  @a2w.Absent public void registerDeviceCallback(android.media.midi.MidiManager.DeviceCallback callback, android.os.Handler handler) {  }
  @a2w.Absent public void unregisterDeviceCallback(android.media.midi.MidiManager.DeviceCallback callback) {  }

    public static class DeviceCallback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public DeviceCallback(a2w.Hidden hidden) { }
    public DeviceCallback() { this((a2w.Hidden) null); }
    public void onDeviceAdded(android.media.midi.MidiDeviceInfo device) { }
    public void onDeviceRemoved(android.media.midi.MidiDeviceInfo device) { }
    public void onDeviceStatusChanged(android.media.midi.MidiDeviceStatus status) { }
  }

    public interface OnDeviceOpenedListener {
    void onDeviceOpened(android.media.midi.MidiDevice p0);
  }
}

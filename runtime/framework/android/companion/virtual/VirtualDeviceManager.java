// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.companion.virtual;

public final class VirtualDeviceManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public VirtualDeviceManager(a2w.Hidden hidden) { }
  @a2w.Absent public android.companion.virtual.VirtualDevice getVirtualDevice(int deviceId) { return null; }
  @a2w.Absent public java.util.List<android.companion.virtual.VirtualDevice> getVirtualDevices() { return java.util.Collections.emptyList(); }
  @a2w.Absent public void registerVirtualDeviceListener(java.util.concurrent.Executor executor, android.companion.virtual.VirtualDeviceManager.VirtualDeviceListener listener) {  }
  @a2w.Absent public void unregisterVirtualDeviceListener(android.companion.virtual.VirtualDeviceManager.VirtualDeviceListener listener) {  }

    public interface VirtualDeviceListener {
    default void onVirtualDeviceClosed(int deviceId) { }
    default void onVirtualDeviceCreated(int deviceId) { }
  }
}

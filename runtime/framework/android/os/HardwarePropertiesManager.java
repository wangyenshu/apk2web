// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os;

public class HardwarePropertiesManager {
  public static final int DEVICE_TEMPERATURE_BATTERY = 2;
  public static final int DEVICE_TEMPERATURE_CPU = 0;
  public static final int DEVICE_TEMPERATURE_GPU = 1;
  public static final int DEVICE_TEMPERATURE_SKIN = 3;
  public static final int TEMPERATURE_CURRENT = 0;
  public static final int TEMPERATURE_SHUTDOWN = 2;
  public static final int TEMPERATURE_THROTTLING = 1;
  public static final int TEMPERATURE_THROTTLING_BELOW_VR_MIN = 3;
  public static final float UNDEFINED_TEMPERATURE = -3.4028235E38f;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public HardwarePropertiesManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.os.CpuUsageInfo[] getCpuUsages() { a2w.Stubs.hit("android.os.HardwarePropertiesManager.getCpuUsages"); return null; }
  @a2w.NotImplemented public float[] getDeviceTemperatures(int type, int source) { a2w.Stubs.hit("android.os.HardwarePropertiesManager.getDeviceTemperatures"); return null; }
  @a2w.NotImplemented public float[] getFanSpeeds() { a2w.Stubs.hit("android.os.HardwarePropertiesManager.getFanSpeeds"); return null; }
}

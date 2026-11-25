// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os;

public class BatteryManager {
  public static final java.lang.String ACTION_CHARGING = "android.os.action.CHARGING";
  public static final java.lang.String ACTION_DISCHARGING = "android.os.action.DISCHARGING";
  public static final int BATTERY_CAPACITY_LEVEL_CRITICAL = 1;
  public static final int BATTERY_CAPACITY_LEVEL_FULL = 5;
  public static final int BATTERY_CAPACITY_LEVEL_HIGH = 4;
  public static final int BATTERY_CAPACITY_LEVEL_LOW = 2;
  public static final int BATTERY_CAPACITY_LEVEL_NORMAL = 3;
  public static final int BATTERY_CAPACITY_LEVEL_UNKNOWN = 0;
  public static final int BATTERY_CAPACITY_LEVEL_UNSUPPORTED = -1;
  public static final int BATTERY_HEALTH_COLD = 7;
  public static final int BATTERY_HEALTH_DEAD = 4;
  public static final int BATTERY_HEALTH_GOOD = 2;
  public static final int BATTERY_HEALTH_OVERHEAT = 3;
  public static final int BATTERY_HEALTH_OVER_VOLTAGE = 5;
  public static final int BATTERY_HEALTH_UNKNOWN = 1;
  public static final int BATTERY_HEALTH_UNSPECIFIED_FAILURE = 6;
  public static final int BATTERY_PLUGGED_AC = 1;
  public static final int BATTERY_PLUGGED_DOCK = 8;
  public static final int BATTERY_PLUGGED_USB = 2;
  public static final int BATTERY_PLUGGED_WIRELESS = 4;
  public static final int BATTERY_PROPERTY_CAPACITY = 4;
  public static final int BATTERY_PROPERTY_CHARGE_COUNTER = 1;
  public static final int BATTERY_PROPERTY_CURRENT_AVERAGE = 3;
  public static final int BATTERY_PROPERTY_CURRENT_NOW = 2;
  public static final int BATTERY_PROPERTY_ENERGY_COUNTER = 5;
  public static final int BATTERY_PROPERTY_STATUS = 6;
  public static final int BATTERY_STATUS_CHARGING = 2;
  public static final int BATTERY_STATUS_DISCHARGING = 3;
  public static final int BATTERY_STATUS_FULL = 5;
  public static final int BATTERY_STATUS_NOT_CHARGING = 4;
  public static final int BATTERY_STATUS_UNKNOWN = 1;
  public static final java.lang.String EXTRA_BATTERY_LOW = "battery_low";
  public static final java.lang.String EXTRA_CAPACITY_LEVEL = "android.os.extra.CAPACITY_LEVEL";
  public static final java.lang.String EXTRA_CHARGING_STATUS = "android.os.extra.CHARGING_STATUS";
  public static final java.lang.String EXTRA_CYCLE_COUNT = "android.os.extra.CYCLE_COUNT";
  public static final java.lang.String EXTRA_HEALTH = "health";
  public static final java.lang.String EXTRA_ICON_SMALL = "icon-small";
  public static final java.lang.String EXTRA_LEVEL = "level";
  public static final java.lang.String EXTRA_PLUGGED = "plugged";
  public static final java.lang.String EXTRA_PRESENT = "present";
  public static final java.lang.String EXTRA_SCALE = "scale";
  public static final java.lang.String EXTRA_STATUS = "status";
  public static final java.lang.String EXTRA_TECHNOLOGY = "technology";
  public static final java.lang.String EXTRA_TEMPERATURE = "temperature";
  public static final java.lang.String EXTRA_VOLTAGE = "voltage";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public BatteryManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public BatteryManager() { this((a2w.Hidden) null); a2w.Stubs.hit("android.os.BatteryManager.<init>"); }
  @a2w.NotImplemented public long computeChargeTimeRemaining() { a2w.Stubs.hit("android.os.BatteryManager.computeChargeTimeRemaining"); return 0L; }
  @a2w.NotImplemented public int getIntProperty(int id) { a2w.Stubs.hit("android.os.BatteryManager.getIntProperty"); return 0; }
  @a2w.NotImplemented public long getLongProperty(int id) { a2w.Stubs.hit("android.os.BatteryManager.getLongProperty"); return 0L; }
  @a2w.NotImplemented public java.lang.String getStringProperty(int id) { a2w.Stubs.hit("android.os.BatteryManager.getStringProperty"); return null; }
  @a2w.NotImplemented public boolean isCharging() { a2w.Stubs.hit("android.os.BatteryManager.isCharging"); return false; }
}

package android.hardware;
public final class Sensor {
  public static final int TYPE_ACCELEROMETER = 1, TYPE_MAGNETIC_FIELD = 2, TYPE_ORIENTATION = 3, TYPE_GYROSCOPE = 4, TYPE_LIGHT = 5, TYPE_PRESSURE = 6,
      TYPE_TEMPERATURE = 7, TYPE_PROXIMITY = 8, TYPE_GRAVITY = 9, TYPE_LINEAR_ACCELERATION = 10, TYPE_ROTATION_VECTOR = 11, TYPE_ALL = -1;
  final int type;
  Sensor(int t) { type = t; }
  public int getType() { return type; }
  public String getName() { return "web sensor " + type; }
  public String getVendor() { return "web"; }
  public int getVersion() { return 1; }
  public float getMaximumRange() { return 19.6f; }
  public float getResolution() { return 0.01f; }
  public float getPower() { return 0; }
  public int getMinDelay() { return 20000; }
}

package android.hardware;

import java.util.ArrayList;
import java.util.List;

/** No motion sensors on the web build: listeners register but never fire. */
public class SensorManager {
  public static final float STANDARD_GRAVITY = 9.80665f, GRAVITY_EARTH = 9.80665f;
  public static final int SENSOR_DELAY_FASTEST = 0, SENSOR_DELAY_GAME = 1, SENSOR_DELAY_UI = 2, SENSOR_DELAY_NORMAL = 3,
      SENSOR_STATUS_UNRELIABLE = 0, SENSOR_STATUS_ACCURACY_LOW = 1, SENSOR_STATUS_ACCURACY_MEDIUM = 2, SENSOR_STATUS_ACCURACY_HIGH = 3;
  static final Sensor ACC = new Sensor(Sensor.TYPE_ACCELEROMETER);
  public Sensor getDefaultSensor(int type) { return type == Sensor.TYPE_ACCELEROMETER ? ACC : null; }
  public List<Sensor> getSensorList(int type) { ArrayList<Sensor> l = new ArrayList<>(); if (type == Sensor.TYPE_ACCELEROMETER || type == Sensor.TYPE_ALL) l.add(ACC); return l; }
  public boolean registerListener(SensorEventListener l, Sensor s, int rate) { return true; }
  public boolean registerListener(SensorEventListener l, Sensor s, int rate, android.os.Handler h) { return true; }
  public void unregisterListener(SensorEventListener l) {}
  public void unregisterListener(SensorEventListener l, Sensor s) {}
  public boolean registerListener(SensorListener l, int sensors) { return true; }
  public boolean registerListener(SensorListener l, int sensors, int rate) { return true; }
  public void unregisterListener(SensorListener l) {}
  public static boolean getRotationMatrix(float[] R, float[] I, float[] g, float[] m) { return false; }
  public static float[] getOrientation(float[] R, float[] v) { return v; }
}

package android.location;

public class Criteria {
  public static final int NO_REQUIREMENT = 0, POWER_LOW = 1, POWER_MEDIUM = 2, POWER_HIGH = 3, ACCURACY_FINE = 1, ACCURACY_COARSE = 2,
      ACCURACY_LOW = 1, ACCURACY_MEDIUM = 2, ACCURACY_HIGH = 3;
  private int accuracy, power;
  private boolean altitude, bearing, speed, cost;
  public void setAccuracy(int a) { accuracy = a; }
  public int getAccuracy() { return accuracy; }
  public void setPowerRequirement(int p) { power = p; }
  public int getPowerRequirement() { return power; }
  public void setAltitudeRequired(boolean b) { altitude = b; }
  public boolean isAltitudeRequired() { return altitude; }
  public void setBearingRequired(boolean b) { bearing = b; }
  public boolean isBearingRequired() { return bearing; }
  public void setSpeedRequired(boolean b) { speed = b; }
  public boolean isSpeedRequired() { return speed; }
  public void setCostAllowed(boolean b) { cost = b; }
  public boolean isCostAllowed() { return cost; }
  public void setHorizontalAccuracy(int a) {}
  public void setVerticalAccuracy(int a) {}
}

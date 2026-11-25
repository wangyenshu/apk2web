package android.location;

/** a position fix (the page has no location provider; apps can still build and pass these) */
public class Location implements android.os.Parcelable {
  private String provider;
  private double latitude, longitude, altitude;
  private float accuracy, bearing, speed;
  private long time;
  private boolean hasAccuracy, hasAltitude, hasBearing, hasSpeed;
  private android.os.Bundle extras;
  public Location(String provider) { this.provider = provider; }
  public Location(Location l) { set(l); }
  public void set(Location l) {
    provider = l.provider; latitude = l.latitude; longitude = l.longitude; altitude = l.altitude; accuracy = l.accuracy;
    bearing = l.bearing; speed = l.speed; time = l.time; hasAccuracy = l.hasAccuracy; hasAltitude = l.hasAltitude;
    hasBearing = l.hasBearing; hasSpeed = l.hasSpeed; extras = l.extras;
  }
  public String getProvider() { return provider; }
  public void setProvider(String p) { provider = p; }
  public double getLatitude() { return latitude; }
  public void setLatitude(double v) { latitude = v; }
  public double getLongitude() { return longitude; }
  public void setLongitude(double v) { longitude = v; }
  public double getAltitude() { return altitude; }
  public void setAltitude(double v) { altitude = v; hasAltitude = true; }
  public boolean hasAltitude() { return hasAltitude; }
  public float getAccuracy() { return accuracy; }
  public void setAccuracy(float v) { accuracy = v; hasAccuracy = true; }
  public boolean hasAccuracy() { return hasAccuracy; }
  public float getBearing() { return bearing; }
  public void setBearing(float v) { bearing = v; hasBearing = true; }
  public boolean hasBearing() { return hasBearing; }
  public float getSpeed() { return speed; }
  public void setSpeed(float v) { speed = v; hasSpeed = true; }
  public boolean hasSpeed() { return hasSpeed; }
  public long getTime() { return time; }
  public void setTime(long t) { time = t; }
  public long getElapsedRealtimeNanos() { return time * 1000000L; }
  public android.os.Bundle getExtras() { return extras; }
  public void setExtras(android.os.Bundle b) { extras = b; }
  public void reset() { set(new Location(provider)); }
  /** great-circle distance in meters (spherical earth) */
  public float distanceTo(Location d) {
    float[] r = new float[1];
    distanceBetween(latitude, longitude, d.latitude, d.longitude, r);
    return r[0];
  }
  public float bearingTo(Location d) {
    double f1 = Math.toRadians(latitude), f2 = Math.toRadians(d.latitude), dl = Math.toRadians(d.longitude - longitude);
    return (float) Math.toDegrees(Math.atan2(Math.sin(dl) * Math.cos(f2), Math.cos(f1) * Math.sin(f2) - Math.sin(f1) * Math.cos(f2) * Math.cos(dl)));
  }
  public static void distanceBetween(double lat1, double lon1, double lat2, double lon2, float[] results) {
    double f1 = Math.toRadians(lat1), f2 = Math.toRadians(lat2), df = f2 - f1, dl = Math.toRadians(lon2 - lon1);
    double a = Math.sin(df / 2) * Math.sin(df / 2) + Math.cos(f1) * Math.cos(f2) * Math.sin(dl / 2) * Math.sin(dl / 2);
    results[0] = (float) (6371000.0 * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
  }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel p, int flags) {}
  @Override public String toString() { return "Location[" + provider + " " + latitude + "," + longitude + "]"; }
}

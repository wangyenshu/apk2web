package android.location;

public interface LocationListener {
  void onLocationChanged(Location location);
  void onStatusChanged(String provider, int status, android.os.Bundle extras);
  void onProviderEnabled(String provider);
  void onProviderDisabled(String provider);
}

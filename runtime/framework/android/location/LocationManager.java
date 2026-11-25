package android.location;

import java.util.ArrayList;
import java.util.List;

/** no location providers on the page: nothing is enabled, there is no last fix, requests never deliver */
public class LocationManager {
  public static final String GPS_PROVIDER = "gps", NETWORK_PROVIDER = "network", PASSIVE_PROVIDER = "passive", FUSED_PROVIDER = "fused";
  public static final String KEY_LOCATION_CHANGED = "location", KEY_PROVIDER_ENABLED = "providerEnabled", KEY_STATUS_CHANGED = "status",
      KEY_PROXIMITY_ENTERING = "entering", PROVIDERS_CHANGED_ACTION = "android.location.PROVIDERS_CHANGED";
  public List<String> getAllProviders() { return new ArrayList<>(); }
  public List<String> getProviders(boolean enabledOnly) { return new ArrayList<>(); }
  public List<String> getProviders(Criteria c, boolean enabledOnly) { return new ArrayList<>(); }
  public String getBestProvider(Criteria c, boolean enabledOnly) { return null; }
  public boolean isProviderEnabled(String provider) { return false; }
  public boolean isLocationEnabled() { return false; }
  public Location getLastKnownLocation(String provider) { return null; }
  public void requestLocationUpdates(String provider, long minTime, float minDistance, LocationListener l) {}
  public void requestLocationUpdates(String provider, long minTime, float minDistance, LocationListener l, android.os.Looper looper) {}
  public void requestLocationUpdates(long minTime, float minDistance, Criteria c, LocationListener l, android.os.Looper looper) {}
  public void requestLocationUpdates(String provider, long minTime, float minDistance, android.app.PendingIntent intent) {}
  public void requestSingleUpdate(String provider, LocationListener l, android.os.Looper looper) {}
  public void requestSingleUpdate(Criteria c, LocationListener l, android.os.Looper looper) {}
  public void removeUpdates(LocationListener l) {}
  public void removeUpdates(android.app.PendingIntent intent) {}
  public boolean addGpsStatusListener(Object l) { return false; }
  public void removeGpsStatusListener(Object l) {}
  public void addProximityAlert(double lat, double lon, float radius, long expiration, android.app.PendingIntent intent) {}
  public void removeProximityAlert(android.app.PendingIntent intent) {}
}

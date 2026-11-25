package android.net;
public class ConnectivityManager {
  public static final String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE", EXTRA_NO_CONNECTIVITY = "noConnectivity", EXTRA_NETWORK_INFO = "networkInfo";
  public static final int TYPE_MOBILE = 0, TYPE_WIFI = 1, TYPE_MOBILE_MMS = 2, TYPE_WIMAX = 6, TYPE_ETHERNET = 9, DEFAULT_NETWORK_PREFERENCE = 1;
  final NetworkInfo info = new NetworkInfo();
  public NetworkInfo getActiveNetworkInfo() { return info; }
  public NetworkInfo getNetworkInfo(int type) { return type == TYPE_WIFI ? info : null; }
  public NetworkInfo[] getAllNetworkInfo() { return new NetworkInfo[] {info}; }
  public boolean getBackgroundDataSetting() { return true; }
  public int getNetworkPreference() { return TYPE_WIFI; }
  public boolean isActiveNetworkMetered() { return false; }
}

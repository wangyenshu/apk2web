package android.net.wifi;
public class WifiManager {
  public boolean isWifiEnabled() { return true; }
  public WifiInfo getConnectionInfo() { return new WifiInfo(); }
  public class WifiLock { public void acquire() {} public void release() {} public boolean isHeld() { return false; } }
  public WifiLock createWifiLock(String tag) { return new WifiLock(); }
}

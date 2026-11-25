package android.telephony;
public class TelephonyManager {
  public static final int PHONE_TYPE_NONE = 0, PHONE_TYPE_GSM = 1, NETWORK_TYPE_UNKNOWN = 0, SIM_STATE_ABSENT = 1, CALL_STATE_IDLE = 0, DATA_DISCONNECTED = 0;
  public String getDeviceId() { return a2w.Host.deviceId(); }
  public String getSubscriberId() { return null; }
  public String getSimSerialNumber() { return null; }
  public String getLine1Number() { return null; }
  public String getNetworkOperator() { return ""; }
  public String getNetworkOperatorName() { return ""; }
  public String getNetworkCountryIso() { return ""; }
  public String getSimOperator() { return ""; }
  public String getSimOperatorName() { return ""; }
  public String getSimCountryIso() { return ""; }
  public String getDeviceSoftwareVersion() { return null; }
  public int getPhoneType() { return PHONE_TYPE_NONE; }
  public int getNetworkType() { return NETWORK_TYPE_UNKNOWN; }
  public int getSimState() { return SIM_STATE_ABSENT; }
  public int getCallState() { return CALL_STATE_IDLE; }
  public int getDataState() { return DATA_DISCONNECTED; }
  public boolean isNetworkRoaming() { return false; }
  public void listen(Object l, int events) {}
}

// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.net;

public class ConnectivityDiagnosticsManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public ConnectivityDiagnosticsManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void registerConnectivityDiagnosticsCallback(android.net.NetworkRequest request, java.util.concurrent.Executor e, android.net.ConnectivityDiagnosticsManager.ConnectivityDiagnosticsCallback callback) { a2w.Stubs.hit("android.net.ConnectivityDiagnosticsManager.registerConnectivityDiagnosticsCallback"); }
  @a2w.NotImplemented public void unregisterConnectivityDiagnosticsCallback(android.net.ConnectivityDiagnosticsManager.ConnectivityDiagnosticsCallback callback) { a2w.Stubs.hit("android.net.ConnectivityDiagnosticsManager.unregisterConnectivityDiagnosticsCallback"); }

    public static abstract class ConnectivityDiagnosticsCallback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public ConnectivityDiagnosticsCallback(a2w.Hidden hidden) { }
    public ConnectivityDiagnosticsCallback() { this((a2w.Hidden) null); }
    public void onConnectivityReportAvailable(android.net.ConnectivityDiagnosticsManager.ConnectivityReport report) { }
    public void onDataStallSuspected(android.net.ConnectivityDiagnosticsManager.DataStallReport report) { }
    public void onNetworkConnectivityReported(android.net.Network network, boolean hasConnectivity) { }
  }

    public static final class ConnectivityReport implements android.os.Parcelable {
    @a2w.Derived public static final android.os.Parcelable.Creator<android.net.ConnectivityDiagnosticsManager.ConnectivityReport> CREATOR = new android.os.Parcelable.Creator<android.net.ConnectivityDiagnosticsManager.ConnectivityReport>() { public android.net.ConnectivityDiagnosticsManager.ConnectivityReport createFromParcel(android.os.Parcel in) { android.net.ConnectivityDiagnosticsManager.ConnectivityReport x = new android.net.ConnectivityDiagnosticsManager.ConnectivityReport((a2w.Hidden) null); x.a2w$additionalInfo = (android.os.PersistableBundle) in.readValue(null); x.a2w$linkProperties = (android.net.LinkProperties) in.readValue(null); x.a2w$network = (android.net.Network) in.readValue(null); x.a2w$networkCapabilities = (android.net.NetworkCapabilities) in.readValue(null); x.a2w$reportTimestamp = (java.lang.Long) in.readValue(null); return x; } public android.net.ConnectivityDiagnosticsManager.ConnectivityReport[] newArray(int size) { return new android.net.ConnectivityDiagnosticsManager.ConnectivityReport[size]; } };
    public static final java.lang.String KEY_NETWORK_PROBES_ATTEMPTED_BITMASK = "networkProbesAttempted";
    public static final java.lang.String KEY_NETWORK_PROBES_SUCCEEDED_BITMASK = "networkProbesSucceeded";
    public static final java.lang.String KEY_NETWORK_VALIDATION_RESULT = "networkValidationResult";
    public static final int NETWORK_PROBE_DNS = 4;
    public static final int NETWORK_PROBE_FALLBACK = 32;
    public static final int NETWORK_PROBE_HTTP = 8;
    public static final int NETWORK_PROBE_HTTPS = 16;
    public static final int NETWORK_PROBE_PRIVATE_DNS = 64;
    public static final int NETWORK_VALIDATION_RESULT_INVALID = 0;
    public static final int NETWORK_VALIDATION_RESULT_PARTIALLY_VALID = 2;
    public static final int NETWORK_VALIDATION_RESULT_SKIPPED = 3;
    public static final int NETWORK_VALIDATION_RESULT_VALID = 1;
    /** (state of the property additionalInfo, not part of the API) */ public android.os.PersistableBundle a2w$additionalInfo;
    /** (state of the property linkProperties, not part of the API) */ public android.net.LinkProperties a2w$linkProperties;
    /** (state of the property network, not part of the API) */ public android.net.Network a2w$network;
    /** (state of the property networkCapabilities, not part of the API) */ public android.net.NetworkCapabilities a2w$networkCapabilities;
    /** (state of the property reportTimestamp, not part of the API) */ public long a2w$reportTimestamp;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public ConnectivityReport(a2w.Hidden hidden) { }
    @a2w.Derived public ConnectivityReport(android.net.Network network, long reportTimestamp, android.net.LinkProperties linkProperties, android.net.NetworkCapabilities networkCapabilities, android.os.PersistableBundle additionalInfo) { this((a2w.Hidden) null); this.a2w$network = network; this.a2w$reportTimestamp = reportTimestamp; this.a2w$linkProperties = linkProperties; this.a2w$networkCapabilities = networkCapabilities; this.a2w$additionalInfo = additionalInfo; }
    @a2w.Derived public int describeContents() { return 0; }
    @a2w.Derived public boolean equals(java.lang.Object o) { if (this == o) return true; if (!(o instanceof android.net.ConnectivityDiagnosticsManager.ConnectivityReport)) return false; android.net.ConnectivityDiagnosticsManager.ConnectivityReport x = (android.net.ConnectivityDiagnosticsManager.ConnectivityReport) o; return true && java.util.Objects.equals(a2w$additionalInfo, x.a2w$additionalInfo) && java.util.Objects.equals(a2w$linkProperties, x.a2w$linkProperties) && java.util.Objects.equals(a2w$network, x.a2w$network) && java.util.Objects.equals(a2w$networkCapabilities, x.a2w$networkCapabilities) && java.util.Objects.equals(a2w$reportTimestamp, x.a2w$reportTimestamp); }
    @a2w.Derived public android.os.PersistableBundle getAdditionalInfo() { return this.a2w$additionalInfo; }
    @a2w.Derived public android.net.LinkProperties getLinkProperties() { return this.a2w$linkProperties; }
    @a2w.Derived public android.net.Network getNetwork() { return this.a2w$network; }
    @a2w.Derived public android.net.NetworkCapabilities getNetworkCapabilities() { return this.a2w$networkCapabilities; }
    @a2w.Derived public long getReportTimestamp() { return this.a2w$reportTimestamp; }
    @a2w.Derived public int hashCode() { return java.util.Arrays.deepHashCode(new Object[]{a2w$additionalInfo, a2w$linkProperties, a2w$network, a2w$networkCapabilities, a2w$reportTimestamp}); }
    @a2w.Derived public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeValue(a2w$additionalInfo); dest.writeValue(a2w$linkProperties); dest.writeValue(a2w$network); dest.writeValue(a2w$networkCapabilities); dest.writeValue(a2w$reportTimestamp); }
  }

    public static final class DataStallReport implements android.os.Parcelable {
    @a2w.Derived public static final android.os.Parcelable.Creator<android.net.ConnectivityDiagnosticsManager.DataStallReport> CREATOR = new android.os.Parcelable.Creator<android.net.ConnectivityDiagnosticsManager.DataStallReport>() { public android.net.ConnectivityDiagnosticsManager.DataStallReport createFromParcel(android.os.Parcel in) { android.net.ConnectivityDiagnosticsManager.DataStallReport x = new android.net.ConnectivityDiagnosticsManager.DataStallReport((a2w.Hidden) null); x.a2w$detectionMethod = (java.lang.Integer) in.readValue(null); x.a2w$linkProperties = (android.net.LinkProperties) in.readValue(null); x.a2w$network = (android.net.Network) in.readValue(null); x.a2w$networkCapabilities = (android.net.NetworkCapabilities) in.readValue(null); x.a2w$reportTimestamp = (java.lang.Long) in.readValue(null); x.a2w$stallDetails = (android.os.PersistableBundle) in.readValue(null); return x; } public android.net.ConnectivityDiagnosticsManager.DataStallReport[] newArray(int size) { return new android.net.ConnectivityDiagnosticsManager.DataStallReport[size]; } };
    public static final int DETECTION_METHOD_DNS_EVENTS = 1;
    public static final int DETECTION_METHOD_TCP_METRICS = 2;
    public static final java.lang.String KEY_DNS_CONSECUTIVE_TIMEOUTS = "dnsConsecutiveTimeouts";
    public static final java.lang.String KEY_TCP_METRICS_COLLECTION_PERIOD_MILLIS = "tcpMetricsCollectionPeriodMillis";
    public static final java.lang.String KEY_TCP_PACKET_FAIL_RATE = "tcpPacketFailRate";
    /** (state of the property detectionMethod, not part of the API) */ public int a2w$detectionMethod;
    /** (state of the property linkProperties, not part of the API) */ public android.net.LinkProperties a2w$linkProperties;
    /** (state of the property network, not part of the API) */ public android.net.Network a2w$network;
    /** (state of the property networkCapabilities, not part of the API) */ public android.net.NetworkCapabilities a2w$networkCapabilities;
    /** (state of the property reportTimestamp, not part of the API) */ public long a2w$reportTimestamp;
    /** (state of the property stallDetails, not part of the API) */ public android.os.PersistableBundle a2w$stallDetails;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public DataStallReport(a2w.Hidden hidden) { }
    @a2w.Derived public DataStallReport(android.net.Network network, long reportTimestamp, int detectionMethod, android.net.LinkProperties linkProperties, android.net.NetworkCapabilities networkCapabilities, android.os.PersistableBundle stallDetails) { this((a2w.Hidden) null); this.a2w$network = network; this.a2w$reportTimestamp = reportTimestamp; this.a2w$detectionMethod = detectionMethod; this.a2w$linkProperties = linkProperties; this.a2w$networkCapabilities = networkCapabilities; this.a2w$stallDetails = stallDetails; }
    @a2w.Derived public int describeContents() { return 0; }
    @a2w.Derived public boolean equals(java.lang.Object o) { if (this == o) return true; if (!(o instanceof android.net.ConnectivityDiagnosticsManager.DataStallReport)) return false; android.net.ConnectivityDiagnosticsManager.DataStallReport x = (android.net.ConnectivityDiagnosticsManager.DataStallReport) o; return true && java.util.Objects.equals(a2w$detectionMethod, x.a2w$detectionMethod) && java.util.Objects.equals(a2w$linkProperties, x.a2w$linkProperties) && java.util.Objects.equals(a2w$network, x.a2w$network) && java.util.Objects.equals(a2w$networkCapabilities, x.a2w$networkCapabilities) && java.util.Objects.equals(a2w$reportTimestamp, x.a2w$reportTimestamp) && java.util.Objects.equals(a2w$stallDetails, x.a2w$stallDetails); }
    @a2w.Derived public int getDetectionMethod() { return this.a2w$detectionMethod; }
    @a2w.Derived public android.net.LinkProperties getLinkProperties() { return this.a2w$linkProperties; }
    @a2w.Derived public android.net.Network getNetwork() { return this.a2w$network; }
    @a2w.Derived public android.net.NetworkCapabilities getNetworkCapabilities() { return this.a2w$networkCapabilities; }
    @a2w.Derived public long getReportTimestamp() { return this.a2w$reportTimestamp; }
    @a2w.Derived public android.os.PersistableBundle getStallDetails() { return this.a2w$stallDetails; }
    @a2w.Derived public int hashCode() { return java.util.Arrays.deepHashCode(new Object[]{a2w$detectionMethod, a2w$linkProperties, a2w$network, a2w$networkCapabilities, a2w$reportTimestamp, a2w$stallDetails}); }
    @a2w.Derived public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeValue(a2w$detectionMethod); dest.writeValue(a2w$linkProperties); dest.writeValue(a2w$network); dest.writeValue(a2w$networkCapabilities); dest.writeValue(a2w$reportTimestamp); dest.writeValue(a2w$stallDetails); }
  }
}

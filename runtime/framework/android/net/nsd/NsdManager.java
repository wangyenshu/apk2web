// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.net.nsd;

public final class NsdManager {
  public static final java.lang.String ACTION_NSD_STATE_CHANGED = "android.net.nsd.STATE_CHANGED";
  public static final java.lang.String EXTRA_NSD_STATE = "nsd_state";
  public static final int FAILURE_ALREADY_ACTIVE = 3;
  public static final int FAILURE_BAD_PARAMETERS = 6;
  public static final int FAILURE_INTERNAL_ERROR = 0;
  public static final int FAILURE_MAX_LIMIT = 4;
  public static final int FAILURE_OPERATION_NOT_RUNNING = 5;
  public static final int NSD_STATE_DISABLED = 1;
  public static final int NSD_STATE_ENABLED = 2;
  public static final int PROTOCOL_DNS_SD = 1;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public NsdManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void discoverServices(android.net.nsd.DiscoveryRequest discoveryRequest, java.util.concurrent.Executor executor, android.net.nsd.NsdManager.DiscoveryListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.discoverServices"); }
  @a2w.NotImplemented public void discoverServices(java.lang.String serviceType, int protocolType, android.net.Network network, java.util.concurrent.Executor executor, android.net.nsd.NsdManager.DiscoveryListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.discoverServices"); }
  @a2w.NotImplemented public void discoverServices(java.lang.String serviceType, int protocolType, android.net.NetworkRequest networkRequest, java.util.concurrent.Executor executor, android.net.nsd.NsdManager.DiscoveryListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.discoverServices"); }
  @a2w.NotImplemented public void discoverServices(java.lang.String serviceType, int protocolType, android.net.nsd.NsdManager.DiscoveryListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.discoverServices"); }
  @a2w.NotImplemented public void registerService(android.net.nsd.NsdServiceInfo serviceInfo, int protocolType, android.net.nsd.NsdManager.RegistrationListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.registerService"); }
  @a2w.NotImplemented public void registerService(android.net.nsd.NsdServiceInfo serviceInfo, int protocolType, java.util.concurrent.Executor executor, android.net.nsd.NsdManager.RegistrationListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.registerService"); }
  @a2w.NotImplemented public void registerServiceInfoCallback(android.net.nsd.NsdServiceInfo serviceInfo, java.util.concurrent.Executor executor, android.net.nsd.NsdManager.ServiceInfoCallback listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.registerServiceInfoCallback"); }
  @a2w.NotImplemented public void resolveService(android.net.nsd.NsdServiceInfo serviceInfo, android.net.nsd.NsdManager.ResolveListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.resolveService"); }
  @a2w.NotImplemented public void resolveService(android.net.nsd.NsdServiceInfo serviceInfo, java.util.concurrent.Executor executor, android.net.nsd.NsdManager.ResolveListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.resolveService"); }
  @a2w.NotImplemented public void stopServiceDiscovery(android.net.nsd.NsdManager.DiscoveryListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.stopServiceDiscovery"); }
  @a2w.NotImplemented public void stopServiceResolution(android.net.nsd.NsdManager.ResolveListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.stopServiceResolution"); }
  @a2w.NotImplemented public void unregisterService(android.net.nsd.NsdManager.RegistrationListener listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.unregisterService"); }
  @a2w.NotImplemented public void unregisterServiceInfoCallback(android.net.nsd.NsdManager.ServiceInfoCallback listener) { a2w.Stubs.hit("android.net.nsd.NsdManager.unregisterServiceInfoCallback"); }

    public interface DiscoveryListener {
    void onDiscoveryStarted(java.lang.String p0);
    void onDiscoveryStopped(java.lang.String p0);
    void onServiceFound(android.net.nsd.NsdServiceInfo p0);
    void onServiceLost(android.net.nsd.NsdServiceInfo p0);
    void onStartDiscoveryFailed(java.lang.String p0, int p1);
    void onStopDiscoveryFailed(java.lang.String p0, int p1);
  }

    public interface RegistrationListener {
    void onRegistrationFailed(android.net.nsd.NsdServiceInfo p0, int p1);
    void onServiceRegistered(android.net.nsd.NsdServiceInfo p0);
    void onServiceUnregistered(android.net.nsd.NsdServiceInfo p0);
    void onUnregistrationFailed(android.net.nsd.NsdServiceInfo p0, int p1);
  }

    public interface ResolveListener {
    default void onResolutionStopped(android.net.nsd.NsdServiceInfo serviceInfo) { }
    void onResolveFailed(android.net.nsd.NsdServiceInfo p0, int p1);
    void onServiceResolved(android.net.nsd.NsdServiceInfo p0);
    default void onStopResolutionFailed(android.net.nsd.NsdServiceInfo serviceInfo, int errorCode) { }
  }

    public interface ServiceInfoCallback {
    void onServiceInfoCallbackRegistrationFailed(int p0);
    void onServiceInfoCallbackUnregistered();
    void onServiceLost();
    void onServiceUpdated(android.net.nsd.NsdServiceInfo p0);
  }
}

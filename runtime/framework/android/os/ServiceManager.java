package android.os;

/** android.os.ServiceManager (hidden): no binder services in the page; getService answers null. */
public final class ServiceManager {
  private ServiceManager() {}
  public static IBinder getService(String name) { return null; }
  public static IBinder checkService(String name) { return null; }
  public static IBinder getServiceOrThrow(String name) throws Exception { throw new Exception("No service published for: " + name); }
  public static void addService(String name, IBinder service) {}
  public static String[] listServices() { return new String[0]; }
}

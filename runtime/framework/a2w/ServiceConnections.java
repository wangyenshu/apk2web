package a2w;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;

/** service connections whose callbacks run on an executor (the same wrapper for the same connection, for unbinding) */
public final class ServiceConnections {
  private ServiceConnections() {}
  private static final WeakHashMap<ServiceConnection, ServiceConnection> wrappers = new WeakHashMap<>();
  public static synchronized ServiceConnection onExecutor(Executor e, ServiceConnection c) {
    ServiceConnection w = wrappers.get(c);
    if (w != null) return w;
    w = new ServiceConnection() {
      public void onServiceConnected(ComponentName name, IBinder service) { e.execute(() -> c.onServiceConnected(name, service)); }
      public void onServiceDisconnected(ComponentName name) { e.execute(() -> c.onServiceDisconnected(name)); }
      public void onBindingDied(ComponentName name) { e.execute(() -> c.onBindingDied(name)); }
      public void onNullBinding(ComponentName name) { e.execute(() -> c.onNullBinding(name)); }
    };
    wrappers.put(c, w);
    return w;
  }
  /** the connection registered for an app's connection (itself when it was bound without an executor) */
  public static synchronized ServiceConnection registered(ServiceConnection c) { ServiceConnection w = wrappers.get(c); return w != null ? w : c; }
}

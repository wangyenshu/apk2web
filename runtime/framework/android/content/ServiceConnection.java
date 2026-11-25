package android.content;
public interface ServiceConnection {
  void onServiceConnected(ComponentName name, android.os.IBinder service);
  void onServiceDisconnected(ComponentName name);
  /** the binding's service process died (never here: services run in the app) */
  default void onBindingDied(ComponentName name) {}
  /** the service returned no binder from onBind */
  default void onNullBinding(ComponentName name) {}
}

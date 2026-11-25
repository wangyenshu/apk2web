package android.webkit;
import android.content.Context;
public class CookieSyncManager {
  static CookieSyncManager I;
  public static synchronized CookieSyncManager createInstance(Context c) { if (I == null) I = new CookieSyncManager(); return I; }
  public static CookieSyncManager getInstance() { if (I == null) throw new IllegalStateException("CookieSyncManager::createInstance() needs to be called before CookieSyncManager::getInstance()"); return I; }
  public void sync() {}
  public void startSync() {}
  public void stopSync() {}
  public void resetSync() {}
}

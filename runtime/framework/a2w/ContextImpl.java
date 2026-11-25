package a2w;

import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Looper;
import android.view.LayoutInflater;
import java.io.File;
import a2w.jdk.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

/** The base context of the application and its activities. */
public final class ContextImpl extends Context {
  static final Resources RESOURCES = Resources.getSystem();
  private static final HashMap<String, SharedPreferencesImpl> prefs = new HashMap<>();
  static final String DATA = "/data/data/";
  android.app.Application app;
  final Resources.Theme theme;
  final PackageManager pm = new PackageManager(this);
  final ContentResolver resolver = new ContentResolver(this);
  LayoutInflater inflater;

  public ContextImpl() {
    theme = RESOURCES.newTheme();
    theme.applyStyle(Styles.defaultTheme(), true);
    int t = Values.refId(Res.appTheme());
    if (t != 0) theme.applyStyle(t, true);
  }
  public AssetManager getAssets() { return RESOURCES.getAssets(); }
  public Resources getResources() { return RESOURCES; }
  public PackageManager getPackageManager() { return pm; }
  public ContentResolver getContentResolver() { return resolver; }
  public Looper getMainLooper() { return Looper.getMainLooper(); }
  public Context getApplicationContext() { return app != null ? app : this; }
  public void setTheme(int resid) { theme.applyStyle(resid, true); }
  public Resources.Theme getTheme() { return theme; }
  public ClassLoader getClassLoader() { return ContextImpl.class.getClassLoader(); }
  public String getPackageName() { return Res.pkg(); }
  public ApplicationInfo getApplicationInfo() { return ApplicationInfo.app(); }
  public String getPackageResourcePath() { return ApplicationInfo.app().sourceDir; }
  public String getPackageCodePath() { return ApplicationInfo.app().sourceDir; }
  public SharedPreferences getSharedPreferences(String name, int mode) {
    synchronized (prefs) {
      if (name == null) name = "null";
      SharedPreferencesImpl p = prefs.get(name);
      if (p == null) { p = new SharedPreferencesImpl(name); prefs.put(name, p); }
      return p;
    }
  }
  File dataDir() { File d = new File(DATA + getPackageName()); if (!d.isDirectory()) d.mkdirs(); return d; }
  public FileInputStream openFileInput(String name) throws java.io.FileNotFoundException { return new FileInputStream(new File(getFilesDir(), name)); }
  public FileOutputStream openFileOutput(String name, int mode) throws java.io.FileNotFoundException { return new FileOutputStream(new File(getFilesDir(), name), (mode & MODE_APPEND) != 0); }
  public boolean deleteFile(String name) { return new File(getFilesDir(), name).delete(); }
  public File getFileStreamPath(String name) { return new File(getFilesDir(), name); }
  public File getFilesDir() { File f = new File(dataDir(), "files"); if (!f.isDirectory()) f.mkdirs(); return f; }
  /** external storage: /sdcard (the page's, kept like app data), with the app's directories under Android/ */
  public File getExternalFilesDir(String type) { File f = new File("/sdcard/Android/data/" + getPackageName() + "/files" + (type == null ? "" : "/" + type)); f.mkdirs(); return f; }
  public File getObbDir() { File f = new File("/sdcard/Android/obb/" + getPackageName()); f.mkdirs(); return f; }
  public File getCacheDir() { File f = new File(dataDir(), "cache"); if (!f.isDirectory()) f.mkdirs(); return f; }
  public File getExternalCacheDir() { File f = new File("/sdcard/Android/data/" + getPackageName() + "/cache"); f.mkdirs(); return f; }
  public String[] fileList() { String[] l = getFilesDir().list(); return l == null ? new String[0] : l; }
  public File getDir(String name, int mode) { File f = new File(dataDir(), "app_" + name); if (!f.isDirectory()) f.mkdirs(); return f; }
  public File getDatabasePath(String name) {
    if (name.startsWith("/")) return new File(name);   // absolute paths are allowed for databases
    File dir = new File(dataDir(), "databases");
    if (!dir.isDirectory()) dir.mkdirs();
    return new File(dir, name);
  }
  public android.database.sqlite.SQLiteDatabase openOrCreateDatabase(String name, int mode, android.database.sqlite.SQLiteDatabase.CursorFactory f) { return openOrCreateDatabase(name, mode, f, null); }
  public android.database.sqlite.SQLiteDatabase openOrCreateDatabase(String name, int mode, android.database.sqlite.SQLiteDatabase.CursorFactory f, android.database.DatabaseErrorHandler eh) {
    return android.database.sqlite.SQLiteDatabase.openDatabase(getDatabasePath(name).getPath(), f, android.database.sqlite.SQLiteDatabase.CREATE_IF_NECESSARY, eh);
  }
  public boolean deleteDatabase(String name) { return android.database.sqlite.SQLiteDatabase.deleteDatabase(getDatabasePath(name)); }
  public String[] databaseList() { String[] l = new File(dataDir(), "databases").list(); return l == null ? new String[0] : l; }
  public void startActivity(Intent intent) { ActivityThread.startActivity(null, intent, -1); }
  public void sendBroadcast(Intent intent) { ActivityThread.broadcast(this, intent); }
  public Intent registerReceiver(BroadcastReceiver r, IntentFilter f) { return Broadcasts.register(this, r, f, null); }
  public void unregisterReceiver(BroadcastReceiver r) { Broadcasts.unregister(r); }
  public ComponentName startService(Intent service) { return ActivityThread.startService(this, service); }
  public boolean stopService(Intent service) { return ActivityThread.stopService(service); }
  public boolean bindService(Intent service, ServiceConnection conn, int flags) { return ActivityThread.bindService(this, service, conn); }
  public void unbindService(ServiceConnection conn) { ActivityThread.unbindService(ServiceConnections.registered(conn)); }
  public Object getSystemService(String name) {
    if (LAYOUT_INFLATER_SERVICE.equals(name)) { if (inflater == null) inflater = LayoutInflater.create(app != null ? app : this); return inflater; }
    return SystemServices.get(this, name);
  }
  public int checkPermission(String p, int pid, int uid) { return PackageManager.PERMISSION_GRANTED; }
  public int checkCallingOrSelfPermission(String p) { return PackageManager.PERMISSION_GRANTED; }
  public Context createPackageContext(String pkg, int flags) throws PackageManager.NameNotFoundException {
    if (getPackageName().equals(pkg)) return this;
    throw new PackageManager.NameNotFoundException(pkg);
  }
}

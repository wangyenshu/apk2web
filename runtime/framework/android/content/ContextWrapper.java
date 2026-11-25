package android.content;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Looper;
import java.io.File;
import a2w.jdk.FileInputStream;
import java.io.FileOutputStream;

public class ContextWrapper extends Context {
  Context mBase;
  public ContextWrapper(Context base) { mBase = base; }
  protected void attachBaseContext(Context base) { if (mBase != null) throw new IllegalStateException("Base context already set"); mBase = base; }
  public Context getBaseContext() { return mBase; }
  public AssetManager getAssets() { return mBase.getAssets(); }
  public Resources getResources() { return mBase.getResources(); }
  public PackageManager getPackageManager() { return mBase.getPackageManager(); }
  public ContentResolver getContentResolver() { return mBase.getContentResolver(); }
  public Looper getMainLooper() { return mBase.getMainLooper(); }
  public Context getApplicationContext() { return mBase.getApplicationContext(); }
  public void setTheme(int resid) { mBase.setTheme(resid); }
  public Resources.Theme getTheme() { return mBase.getTheme(); }
  public ClassLoader getClassLoader() { return mBase.getClassLoader(); }
  public String getPackageName() { return mBase.getPackageName(); }
  public ApplicationInfo getApplicationInfo() { return mBase.getApplicationInfo(); }
  public String getPackageResourcePath() { return mBase.getPackageResourcePath(); }
  public String getPackageCodePath() { return mBase.getPackageCodePath(); }
  public SharedPreferences getSharedPreferences(String name, int mode) { return mBase.getSharedPreferences(name, mode); }
  public FileInputStream openFileInput(String name) throws java.io.FileNotFoundException { return mBase.openFileInput(name); }
  public FileOutputStream openFileOutput(String name, int mode) throws java.io.FileNotFoundException { return mBase.openFileOutput(name, mode); }
  public boolean deleteFile(String name) { return mBase.deleteFile(name); }
  public File getFileStreamPath(String name) { return mBase.getFileStreamPath(name); }
  public File getFilesDir() { return mBase.getFilesDir(); }
  public File getExternalFilesDir(String type) { return mBase.getExternalFilesDir(type); }
  public File getCacheDir() { return mBase.getCacheDir(); }
  public File getExternalCacheDir() { return mBase.getExternalCacheDir(); }
  @Override public File getObbDir() { return mBase.getObbDir(); }
  public String[] fileList() { return mBase.fileList(); }
  public File getDir(String name, int mode) { return mBase.getDir(name, mode); }
  public File getDatabasePath(String name) { return mBase.getDatabasePath(name); }
  public android.database.sqlite.SQLiteDatabase openOrCreateDatabase(String name, int mode, android.database.sqlite.SQLiteDatabase.CursorFactory f) { return mBase.openOrCreateDatabase(name, mode, f); }
  public boolean deleteDatabase(String name) { return mBase.deleteDatabase(name); }
  @Override public android.database.sqlite.SQLiteDatabase openOrCreateDatabase(String name, int mode, android.database.sqlite.SQLiteDatabase.CursorFactory f, android.database.DatabaseErrorHandler eh) { return mBase.openOrCreateDatabase(name, mode, f, eh); }
  @Override public String[] databaseList() { return mBase.databaseList(); }
  public void startActivity(Intent intent) { mBase.startActivity(intent); }
  public void sendBroadcast(Intent intent) { mBase.sendBroadcast(intent); }
  public Intent registerReceiver(BroadcastReceiver r, IntentFilter f) { return mBase.registerReceiver(r, f); }
  public void unregisterReceiver(BroadcastReceiver r) { mBase.unregisterReceiver(r); }
  public ComponentName startService(Intent s) { return mBase.startService(s); }
  public boolean stopService(Intent s) { return mBase.stopService(s); }
  public boolean bindService(Intent s, ServiceConnection c, int f) { return mBase.bindService(s, c, f); }
  public void unbindService(ServiceConnection c) { mBase.unbindService(c); }
  public Object getSystemService(String name) { return mBase.getSystemService(name); }
  public int checkPermission(String p, int pid, int uid) { return mBase.checkPermission(p, pid, uid); }
  public int checkCallingOrSelfPermission(String p) { return mBase.checkCallingOrSelfPermission(p); }
  public Context createPackageContext(String pkg, int flags) throws PackageManager.NameNotFoundException { return mBase.createPackageContext(pkg, flags); }
  // the base context answers these (a wrapper around this wrapper would send getResources() back here)
  @Override public Context createConfigurationContext(android.content.res.Configuration c) { return mBase.createConfigurationContext(c); }
  @Override public Context createDisplayContext(android.view.Display d) { return mBase.createDisplayContext(d); }
  @Override public Context createDeviceProtectedStorageContext() { return mBase.createDeviceProtectedStorageContext(); }
}

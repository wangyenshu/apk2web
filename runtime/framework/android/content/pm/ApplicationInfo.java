package android.content.pm;

public class ApplicationInfo extends PackageItemInfo implements android.os.Parcelable {
  public static final int FLAG_SYSTEM = 1, FLAG_DEBUGGABLE = 2, FLAG_HAS_CODE = 4, FLAG_PERSISTENT = 8, FLAG_EXTERNAL_STORAGE = 0x40000, FLAG_UPDATED_SYSTEM_APP = 128;
  public String sourceDir, publicSourceDir, dataDir, nativeLibraryDir, processName, taskAffinity, className, permission;
  public int flags, uid = 10042, targetSdkVersion = 19, minSdkVersion = 1, compileSdkVersion, theme, descriptionRes;
  public boolean enabled = true;
  public String[] sharedLibraryFiles;
  private static ApplicationInfo app;
  public static ApplicationInfo app() {
    if (app == null) {
      ApplicationInfo a = new ApplicationInfo();
      a.packageName = a.processName = a2w.Res.pkg();
      a.sourceDir = a.publicSourceDir = "/data/app/" + a.packageName + ".apk";
      a.dataDir = "/data/data/" + a.packageName;
      a.nativeLibraryDir = a.dataDir + "/lib";
      a.flags = FLAG_HAS_CODE | ("1".equals(a2w.Host.config("debug")) ? FLAG_DEBUGGABLE : 0);
      a.metaData = PackageManager.metaBundle(a2w.Res.metaJoined("application", null));
      a.className = a2w.Res.application();
      a.minSdkVersion = a2w.Res.minSdk();
      a.targetSdkVersion = a2w.Res.targetSdk() > 0 ? a2w.Res.targetSdk() : a2w.Res.deviceSdk();  // the manifest's level
      String label = a2w.Res.appLabelRef(), icon = a2w.Res.appIconRef();
      if (a2w.Values.isRef(label)) a.labelRes = a2w.Values.refId(label); else if (!label.isEmpty()) a.nonLocalizedLabel = label;
      if (a2w.Values.isRef(icon)) a.icon = a2w.Values.refId(icon);
      if (a2w.Values.isRef(a2w.Res.appTheme())) a.theme = a2w.Values.refId(a2w.Res.appTheme());
      app = a;
    }
    return app;
  }
  public CharSequence loadLabel(PackageManager pm) { return pm.getApplicationLabel(this); }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
}

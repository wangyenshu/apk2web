package android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

public class PackageManager {
  public static class NameNotFoundException extends Exception {
    public NameNotFoundException() {}
    public NameNotFoundException(String m) { super(m); }
  }
  public static final int GET_ACTIVITIES = 1, GET_RECEIVERS = 2, GET_SERVICES = 4, GET_PROVIDERS = 8, GET_INSTRUMENTATION = 16,
      GET_INTENT_FILTERS = 32, GET_SIGNATURES = 64, GET_RESOLVED_FILTER = 64, GET_META_DATA = 128, GET_GIDS = 256, GET_DISABLED_COMPONENTS = 512,
      GET_SHARED_LIBRARY_FILES = 1024, GET_URI_PERMISSION_PATTERNS = 2048, GET_PERMISSIONS = 4096, GET_UNINSTALLED_PACKAGES = 8192,
      GET_CONFIGURATIONS = 16384, MATCH_DEFAULT_ONLY = 65536, PERMISSION_GRANTED = 0, PERMISSION_DENIED = -1,
      COMPONENT_ENABLED_STATE_DEFAULT = 0, COMPONENT_ENABLED_STATE_ENABLED = 1, COMPONENT_ENABLED_STATE_DISABLED = 2, DONT_KILL_APP = 1;
  public static final String FEATURE_TOUCHSCREEN = "android.hardware.touchscreen", FEATURE_TELEPHONY = "android.hardware.telephony";

  final android.content.Context ctx;
  public PackageManager(android.content.Context c) { ctx = c; }
  String pkg() { return a2w.Res.pkg(); }

  public ApplicationInfo getApplicationInfo(String name, int flags) throws NameNotFoundException {
    if (!pkg().equals(name)) throw new NameNotFoundException(name);
    return ApplicationInfo.app();
  }
  public android.content.res.Resources getResourcesForApplication(String name) throws NameNotFoundException {
    if (!pkg().equals(name)) throw new NameNotFoundException(name);
    return ctx.getResources();
  }
  public android.content.res.Resources getResourcesForApplication(ApplicationInfo app) throws NameNotFoundException {
    return getResourcesForApplication(app == null || app.packageName == null ? pkg() : app.packageName);
  }
  public android.content.res.Resources getResourcesForActivity(android.content.ComponentName activity) throws NameNotFoundException {
    return getResourcesForApplication(activity.getPackageName());
  }
  public PackageInfo getPackageInfo(String name, int flags) throws NameNotFoundException {
    if (!pkg().equals(name)) throw new NameNotFoundException(name);
    PackageInfo p = new PackageInfo();
    p.packageName = name;
    p.versionName = a2w.Host.config("versionName");
    String vc = a2w.Host.config("versionCode");
    p.versionCode = vc == null ? 1 : Integer.parseInt(vc);
    p.applicationInfo = ApplicationInfo.app();
    p.signatures = new Signature[] {new Signature("00")};
    p.firstInstallTime = p.lastUpdateTime = System.currentTimeMillis();
    return p;
  }
  public Intent getLaunchIntentForPackage(String name) {
    if (!pkg().equals(name)) return null;
    Intent i = new Intent(Intent.ACTION_MAIN);
    i.addCategory(Intent.CATEGORY_LAUNCHER);
    i.setClassName(name, a2w.Res.launcher());
    return i;
  }
  public List<ResolveInfo> queryIntentActivities(Intent i, int flags) { return query(a2w.Intents.ACTIVITIES, i); }
  public List<ResolveInfo> queryIntentServices(Intent i, int flags) { return query(a2w.Intents.SERVICES, i); }
  public List<ResolveInfo> queryBroadcastReceivers(Intent i, int flags) { return query(a2w.Intents.RECEIVERS, i); }
  public List<ResolveInfo> queryIntentContentProviders(Intent i, int flags) { return query(a2w.Intents.PROVIDERS, i); }
  public ResolveInfo resolveActivity(Intent i, int flags) { List<ResolveInfo> l = queryIntentActivities(i, flags); return l.isEmpty() ? null : l.get(0); }
  public ResolveInfo resolveService(Intent i, int flags) { List<ResolveInfo> l = queryIntentServices(i, flags); return l.isEmpty() ? null : l.get(0); }
  List<ResolveInfo> query(String kind, Intent i) {
    ArrayList<ResolveInfo> out = new ArrayList<>();
    for (String cls : a2w.Intents.resolve(kind, i)) {
      ResolveInfo r = new ResolveInfo();
      ComponentName c = new ComponentName(pkg(), cls);
      try {
        if (kind.equals(a2w.Intents.SERVICES)) r.serviceInfo = fill(new ServiceInfo(), kind, c);
        else if (kind.equals(a2w.Intents.PROVIDERS)) r.providerInfo = fill(new ProviderInfo(), kind, c);
        else r.activityInfo = fill(new ActivityInfo(), kind, c);
      } catch (NameNotFoundException e) {
        ActivityInfo ai = new ActivityInfo(); ai.name = cls; ai.packageName = pkg(); ai.applicationInfo = ApplicationInfo.app();
        if (kind.equals(a2w.Intents.SERVICES)) { ServiceInfo si = new ServiceInfo(); si.name = cls; si.packageName = pkg(); r.serviceInfo = si; } else r.activityInfo = ai;
      }
      r.match = 0x108000; r.isDefault = true;
      out.add(r);
    }
    return out;
  }
  public android.graphics.drawable.Drawable getDefaultActivityIcon() {
    int id = a2w.Res.id("mipmap", "ic_launcher");
    if (id == 0) id = a2w.Res.id("drawable", "icon");
    if (id == 0) id = a2w.Res.id("drawable", "ic_launcher");
    try { return id == 0 ? new android.graphics.drawable.ColorDrawable(0) : ctx.getResources().getDrawable(id); } catch (RuntimeException e) { return new android.graphics.drawable.ColorDrawable(0); }
  }
  public List<PackageInfo> getInstalledPackages(int flags) { return new ArrayList<>(); }
  public List<ApplicationInfo> getInstalledApplications(int flags) { return new ArrayList<>(); }
  public int checkPermission(String perm, String pkg) { return PERMISSION_GRANTED; }
  public boolean hasSystemFeature(String f) { return FEATURE_TOUCHSCREEN.equals(f); }
  public CharSequence getApplicationLabel(ApplicationInfo i) { return ctx.getString(a2w.Res.id("string", "app_name")); }
  public android.graphics.drawable.Drawable getApplicationIcon(String p) throws NameNotFoundException { return ctx.getResources().getDrawable(a2w.Res.id("drawable", "icon")); }
  public android.graphics.drawable.Drawable getApplicationIcon(ApplicationInfo i) {
    int id = i != null && i.icon != 0 ? i.icon : a2w.Res.id("drawable", "icon");
    if (id == 0) return getDefaultActivityIcon();
    try { return ctx.getResources().getDrawable(id); } catch (RuntimeException e) { return getDefaultActivityIcon(); }
  }
  public ActivityInfo getActivityInfo(ComponentName c, int flags) throws NameNotFoundException { return fill(new ActivityInfo(), "activities", c); }
  public ActivityInfo getReceiverInfo(ComponentName c, int flags) throws NameNotFoundException { return fill(new ActivityInfo(), "receivers", c); }
  public ServiceInfo getServiceInfo(ComponentName c, int flags) throws NameNotFoundException { return fill(new ServiceInfo(), "services", c); }
  public ProviderInfo getProviderInfo(ComponentName c, int flags) throws NameNotFoundException { return fill(new ProviderInfo(), "providers", c); }
  /** a manifest component's info, with its meta-data; NameNotFoundException when the manifest has no such component */
  <T extends PackageItemInfo> T fill(T info, String kind, ComponentName c) throws NameNotFoundException {
    String meta = a2w.Res.metaJoined(kind, c.getClassName());
    if (meta == null) throw new NameNotFoundException(c.flattenToString());
    info.name = c.getClassName();
    info.packageName = c.getPackageName();
    info.metaData = metaBundle(meta);
    if (info instanceof ActivityInfo) ((ActivityInfo) info).applicationInfo = ApplicationInfo.app();
    if (info instanceof ServiceInfo) ((ServiceInfo) info).applicationInfo = ApplicationInfo.app();
    if (info instanceof ProviderInfo) ((ProviderInfo) info).applicationInfo = ApplicationInfo.app();
    return info;
  }
  /** manifest meta-data ("name\u0001value..." from res.json) as the typed Bundle Android builds */
  static android.os.Bundle metaBundle(String joined) {
    android.os.Bundle b = new android.os.Bundle();
    if (joined == null || joined.isEmpty()) return b;
    String[] kv = a2w.Res.split(joined);
    for (int i = 0; i + 1 < kv.length; i += 2) {
      String k = kv[i], v = kv[i + 1];
      if (v.equals("true") || v.equals("false")) b.putBoolean(k, Boolean.parseBoolean(v));
      else if (v.startsWith("@")) b.putInt(k, a2w.Values.refId(v));
      else if (v.matches("-?\\d+")) { try { b.putInt(k, Integer.parseInt(v)); } catch (NumberFormatException e) { b.putString(k, v); } }
      else if (v.matches("-?\\d*\\.\\d+")) b.putFloat(k, Float.parseFloat(v));
      else b.putString(k, v);
    }
    return b;
  }
  public void setComponentEnabledSetting(ComponentName c, int s, int f) {}
  public int getComponentEnabledSetting(ComponentName c) { return COMPONENT_ENABLED_STATE_DEFAULT; }
  public String getInstallerPackageName(String p) { return "com.android.vending"; }
}

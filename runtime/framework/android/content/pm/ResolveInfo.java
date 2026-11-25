package android.content.pm;

public class ResolveInfo {
  public ActivityInfo activityInfo;
  public ServiceInfo serviceInfo;
  public ProviderInfo providerInfo;
  public android.content.IntentFilter filter;
  public int priority, preferredOrder, match, specificIndex = -1, labelRes, icon;
  public boolean isDefault;
  public CharSequence nonLocalizedLabel;
  public String resolvePackageName;
  public ResolveInfo() {}
  public ResolveInfo(ResolveInfo o) { activityInfo = o.activityInfo; serviceInfo = o.serviceInfo; providerInfo = o.providerInfo; priority = o.priority; match = o.match; }
  private PackageItemInfo item() { return activityInfo != null ? activityInfo : serviceInfo != null ? serviceInfo : providerInfo; }
  public CharSequence loadLabel(PackageManager pm) {
    if (nonLocalizedLabel != null) return nonLocalizedLabel;
    PackageItemInfo i = item();
    return i == null ? "" : i.loadLabel(pm);
  }
  public android.graphics.drawable.Drawable loadIcon(PackageManager pm) { PackageItemInfo i = item(); return i == null ? null : i.loadIcon(pm); }
  public int getIconResource() { return icon; }
  @Override public String toString() { PackageItemInfo i = item(); return "ResolveInfo{" + (i == null ? "" : i.name) + "}"; }
}

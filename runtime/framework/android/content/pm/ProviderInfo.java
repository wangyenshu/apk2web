package android.content.pm;

public class ProviderInfo extends PackageItemInfo {
  public ApplicationInfo applicationInfo;
  public String authority, readPermission, writePermission;
  public boolean grantUriPermissions, multiprocess, exported = true;
  public int initOrder;
}

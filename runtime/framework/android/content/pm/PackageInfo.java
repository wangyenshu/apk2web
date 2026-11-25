package android.content.pm;
public class PackageInfo implements android.os.Parcelable {
  public String packageName, versionName, sharedUserId;
  public int versionCode;
  public long firstInstallTime, lastUpdateTime;
  public ApplicationInfo applicationInfo;
  public ActivityInfo[] activities;
  public Signature[] signatures;
  public String[] requestedPermissions;
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
}

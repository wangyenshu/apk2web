package android.view.inputmethod;

import android.content.ComponentName;
import android.os.Parcel;

/** An input method: its service (package and class), label, settings activity and subtypes. */
public final class InputMethodInfo implements android.os.Parcelable {
  private final String mPackageName, mClassName, mSettingsActivity, mId;
  private final CharSequence mLabel;
  private final java.util.ArrayList<InputMethodSubtype> mSubtypes = new java.util.ArrayList<>();
  private final android.content.pm.ServiceInfo mService;
  public InputMethodInfo(String packageName, String className, CharSequence label, String settingsActivity) {
    mPackageName = packageName; mClassName = className; mLabel = label; mSettingsActivity = settingsActivity;
    mId = new ComponentName(packageName, className).flattenToShortString();
    mService = new android.content.pm.ServiceInfo();
    mService.packageName = packageName;
    mService.name = className;
    mService.nonLocalizedLabel = label;
    mService.permission = "android.permission.BIND_INPUT_METHOD";
  }
  /** from the resolved service of an input method (its meta-data settings activity is not read) */
  public InputMethodInfo(android.content.Context context, android.content.pm.ResolveInfo service) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
    this(service.serviceInfo.packageName, service.serviceInfo.name, service.loadLabel(context.getPackageManager()), null);
  }
  public String getId() { return mId; }
  public String getPackageName() { return mPackageName; }
  public String getServiceName() { return mClassName; }
  public android.content.pm.ServiceInfo getServiceInfo() { return mService; }
  public ComponentName getComponent() { return new ComponentName(mPackageName, mClassName); }
  public CharSequence loadLabel(android.content.pm.PackageManager pm) { return mLabel; }
  public android.graphics.drawable.Drawable loadIcon(android.content.pm.PackageManager pm) { return null; }
  public String getSettingsActivity() { return mSettingsActivity; }
  public android.content.Intent createStylusHandwritingSettingsActivityIntent() { return null; }
  public android.content.Intent createImeLanguageSettingsActivityIntent() { return null; }
  public int getSubtypeCount() { return mSubtypes.size(); }
  public InputMethodSubtype getSubtypeAt(int index) { return mSubtypes.get(index); }
  public int getIsDefaultResourceId() { return 0; }
  public int getConfigChanges() { return 0; }
  public boolean shouldShowInInputMethodPicker() { return true; }
  public boolean suppressesSpellChecker() { return false; }
  public boolean supportsStylusHandwriting() { return false; }
  public boolean supportsConnectionlessStylusHandwriting() { return false; }
  public void dump(android.util.Printer pw, String prefix) {
    pw.println(prefix + "mId=" + mId + " mSettingsActivity=" + mSettingsActivity);
    pw.println(prefix + "Service:");
    pw.println(prefix + "  " + mPackageName + "/" + mClassName);
  }
  @Override public String toString() { return "InputMethodInfo{" + mId + ", settings: " + mSettingsActivity + "}"; }
  @Override public boolean equals(Object o) { return o == this || (o instanceof InputMethodInfo && mId.equals(((InputMethodInfo) o).mId)); }
  @Override public int hashCode() { return mId.hashCode(); }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel d, int f) {
    d.writeString(mPackageName); d.writeString(mClassName); android.text.TextUtils.writeToParcel(mLabel, d, f); d.writeString(mSettingsActivity);
    d.writeTypedList(mSubtypes);
  }
  public static final android.os.Parcelable.Creator<InputMethodInfo> CREATOR = new android.os.Parcelable.Creator<InputMethodInfo>() {
    public InputMethodInfo createFromParcel(Parcel s) {
      InputMethodInfo i = new InputMethodInfo(s.readString(), s.readString(), android.text.TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(s), s.readString());
      s.readTypedList(i.mSubtypes, InputMethodSubtype.CREATOR);
      return i;
    }
    public InputMethodInfo[] newArray(int size) { return new InputMethodInfo[size]; }
  };
}

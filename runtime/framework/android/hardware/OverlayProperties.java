package android.hardware;

import android.os.Parcel;

/** The display's hardware overlay support: the page composites everything itself, so no overlay combinations. */
public final class OverlayProperties implements android.os.Parcelable {
  public OverlayProperties() {}
  public boolean isCombinationSupported(int dataspace, int format) { return false; }
  public boolean isMixedColorSpacesSupported() { return false; }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel dest, int flags) {}
  public static final android.os.Parcelable.Creator<OverlayProperties> CREATOR = new android.os.Parcelable.Creator<OverlayProperties>() {
    public OverlayProperties createFromParcel(Parcel in) { return new OverlayProperties(); }
    public OverlayProperties[] newArray(int size) { return new OverlayProperties[size]; }
  };
}

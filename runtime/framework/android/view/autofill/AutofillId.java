package android.view.autofill;

/** Identifies a view (and optionally a virtual child of it) for autofill. */
public final class AutofillId implements android.os.Parcelable {
  private static final int NO_VIRTUAL = -1;
  private final int mViewId;
  private final int mVirtualIntId;
  private final long mVirtualLongId;
  private final boolean mLongVirtual;
  private AutofillId(int viewId, int virtualIntId, long virtualLongId, boolean longVirtual) {
    mViewId = viewId; mVirtualIntId = virtualIntId; mVirtualLongId = virtualLongId; mLongVirtual = longVirtual;
  }
  /** for android.view.View: the id of a view (not part of the API) */
  public static AutofillId forView(int viewId) { return new AutofillId(viewId, NO_VIRTUAL, NO_VIRTUAL, false); }
  public static AutofillId create(android.view.View host, int virtualId) {
    java.util.Objects.requireNonNull(host);
    return new AutofillId(host.getAutofillId().mViewId, virtualId, NO_VIRTUAL, false);
  }
  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof AutofillId)) return false;
    AutofillId o = (AutofillId) obj;
    return mViewId == o.mViewId && mVirtualIntId == o.mVirtualIntId && mVirtualLongId == o.mVirtualLongId && mLongVirtual == o.mLongVirtual;
  }
  @Override public int hashCode() { int r = 31 + mViewId; r = 31 * r + mVirtualIntId; return 31 * r + Long.hashCode(mVirtualLongId); }
  @Override public String toString() {
    StringBuilder b = new StringBuilder().append(mViewId);
    if (mLongVirtual) b.append(":l").append(mVirtualLongId);
    else if (mVirtualIntId != NO_VIRTUAL) b.append(':').append(mVirtualIntId);
    return b.toString();
  }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel parcel, int flags) {
    parcel.writeInt(mViewId);
    parcel.writeInt(mLongVirtual ? 2 : mVirtualIntId != NO_VIRTUAL ? 1 : 0);
    if (mLongVirtual) parcel.writeLong(mVirtualLongId); else if (mVirtualIntId != NO_VIRTUAL) parcel.writeInt(mVirtualIntId);
  }
  public static final android.os.Parcelable.Creator<AutofillId> CREATOR = new android.os.Parcelable.Creator<AutofillId>() {
    @Override public AutofillId createFromParcel(android.os.Parcel in) {
      int viewId = in.readInt(), kind = in.readInt();
      if (kind == 2) return new AutofillId(viewId, NO_VIRTUAL, in.readLong(), true);
      if (kind == 1) return new AutofillId(viewId, in.readInt(), NO_VIRTUAL, false);
      return forView(viewId);
    }
    @Override public AutofillId[] newArray(int size) { return new AutofillId[size]; }
  };
}

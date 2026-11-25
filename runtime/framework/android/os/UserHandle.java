package android.os;

/** android.os.UserHandle: the one user (0). */
public final class UserHandle implements Parcelable {
  public static final int PER_USER_RANGE = 100000, USER_ALL = -1, USER_CURRENT = -2, USER_SYSTEM = 0, USER_OWNER = 0;
  public static final UserHandle ALL = new UserHandle(USER_ALL), CURRENT = new UserHandle(USER_CURRENT), SYSTEM = new UserHandle(0), OWNER = SYSTEM;
  final int mHandle;
  public UserHandle(int h) { mHandle = h; }
  public UserHandle(Parcel in) { mHandle = in.readInt(); }
  public static UserHandle myUserId0() { return SYSTEM; }
  public static int myUserId() { return 0; }
  public static UserHandle getUserHandleForUid(int uid) { return SYSTEM; }
  public static int getUserId(int uid) { return uid / PER_USER_RANGE; }
  public static int getAppId(int uid) { return uid % PER_USER_RANGE; }
  public static int getUid(int userId, int appId) { return userId * PER_USER_RANGE + appId % PER_USER_RANGE; }
  public static boolean isApp(int uid) { int a = getAppId(uid); return a >= Process.FIRST_APPLICATION_UID && a <= Process.LAST_APPLICATION_UID; }
  public static UserHandle of(int userId) { return userId == 0 ? SYSTEM : new UserHandle(userId); }
  public int getIdentifier() { return mHandle; }
  public boolean isOwner() { return mHandle == 0; }
  public boolean isSystem() { return mHandle == 0; }
  @Override public boolean equals(Object o) { return o instanceof UserHandle && ((UserHandle) o).mHandle == mHandle; }
  @Override public int hashCode() { return mHandle; }
  @Override public String toString() { return "UserHandle{" + mHandle + "}"; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(Parcel out, int flags) { out.writeInt(mHandle); }
  public static void writeToParcel(UserHandle h, Parcel out) { out.writeInt(h == null ? -10000 : h.mHandle); }
  public static UserHandle readFromParcel(Parcel in) { int h = in.readInt(); return h != -10000 ? new UserHandle(h) : null; }
  public static final Parcelable.Creator<UserHandle> CREATOR = new Parcelable.Creator<UserHandle>() {
    public UserHandle createFromParcel(Parcel in) { return new UserHandle(in); }
    public UserHandle[] newArray(int size) { return new UserHandle[size]; }
  };
}

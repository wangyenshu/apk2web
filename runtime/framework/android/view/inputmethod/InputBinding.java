package android.view.inputmethod;

/** An input method's binding to a client: the connection and the client's ids. */
public final class InputBinding implements android.os.Parcelable {
  private final InputConnection mConnection;
  private final android.os.IBinder mConnectionToken;
  private final int mUid, mPid;
  public InputBinding(InputConnection conn, android.os.IBinder connToken, int uid, int pid) { mConnection = conn; mConnectionToken = connToken; mUid = uid; mPid = pid; }
  public InputBinding(InputConnection conn, InputBinding binding) { this(conn, binding.mConnectionToken, binding.mUid, binding.mPid); }
  public InputConnection getConnection() { return mConnection; }
  public android.os.IBinder getConnectionToken() { return mConnectionToken; }
  public int getUid() { return mUid; }
  public int getPid() { return mPid; }
  @Override public String toString() { return "InputBinding{" + mConnectionToken + " / uid " + mUid + " / pid " + mPid + "}"; }
  public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeStrongBinder(mConnectionToken); dest.writeInt(mUid); dest.writeInt(mPid); }
  public int describeContents() { return 0; }
  public static final android.os.Parcelable.Creator<InputBinding> CREATOR = new Creator();
  static final class Creator implements android.os.Parcelable.Creator<InputBinding> {
    public InputBinding createFromParcel(android.os.Parcel in) { return new InputBinding(null, in.readStrongBinder(), in.readInt(), in.readInt()); }
    public InputBinding[] newArray(int size) { return new InputBinding[size]; }
  }
}

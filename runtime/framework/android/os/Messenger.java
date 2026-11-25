package android.os;
public final class Messenger implements Parcelable {
  private final Handler target;
  public Messenger(Handler h) { target = h; }
  public Messenger(IBinder b) { target = null; }
  public void send(Message m) throws RemoteException { if (target != null) target.sendMessage(m); }
  public IBinder getBinder() { return new Binder(); }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel p, int f) {}
}

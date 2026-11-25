package android.os;
public class ResultReceiver implements Parcelable {
  final Handler h;
  public ResultReceiver(Handler h) { this.h = h; }
  public void send(int code, Bundle data) { if (h != null) h.post(() -> onReceiveResult(code, data)); else onReceiveResult(code, data); }
  protected void onReceiveResult(int code, Bundle data) {}
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel p, int f) {}
}

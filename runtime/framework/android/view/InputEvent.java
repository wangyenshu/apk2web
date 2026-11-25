package android.view;
public abstract class InputEvent implements android.os.Parcelable {
  InputEvent() {}
  public abstract int getDeviceId();
  public abstract int getSource();
  public void setSource(int source) {}
  public final InputDevice getDevice() { return InputDevice.getDevice(getDeviceId()); }
  public boolean isFromSource(int source) { return (getSource() & source) == source; }
  public abstract long getEventTime();
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
}

package android.content;

/** android.content.IntentSender: the PendingIntent it was made from (parcelled as a token of this process). */
public class IntentSender implements android.os.Parcelable {
  public static class SendIntentException extends android.util.AndroidException {
    public SendIntentException() {}
    public SendIntentException(String name) { super(name); }
    public SendIntentException(Exception cause) { super(cause); }
  }
  public interface OnFinished { void onSendFinished(IntentSender IntentSender, Intent intent, int resultCode, String resultData, android.os.Bundle resultExtras); }
  private final android.app.PendingIntent mTarget;
  /** (not part of the API: PendingIntent.getIntentSender) */
  public IntentSender(android.app.PendingIntent target) { mTarget = target; }
  public void sendIntent(Context context, int code, Intent intent, OnFinished onFinished, android.os.Handler handler) throws SendIntentException {
    sendIntent(context, code, intent, onFinished, handler, null);
  }
  public void sendIntent(Context context, int code, Intent intent, OnFinished onFinished, android.os.Handler handler, String requiredPermission) throws SendIntentException {
    if (mTarget == null) throw new SendIntentException();
    try {
      mTarget.send(context, code, intent, onFinished == null ? null : (p, i, rc, rd, re) -> onFinished.onSendFinished(this, i, rc, rd, re), handler, requiredPermission);
    } catch (android.app.PendingIntent.CanceledException e) { throw new SendIntentException(e); }
  }
  /** (not part of the API) the PendingIntent this sends */
  public android.app.PendingIntent webTarget() { return mTarget; }
  public String getTargetPackage() { return mTarget == null ? null : mTarget.getCreatorPackage(); }
  public String getCreatorPackage() { return mTarget == null ? null : mTarget.getCreatorPackage(); }
  public int getCreatorUid() { return android.os.Process.myUid(); }
  public android.os.UserHandle getCreatorUserHandle() { return android.os.Process.myUserHandle(); }
  public boolean equals(Object o) { return o instanceof IntentSender && ((IntentSender) o).mTarget == mTarget; }
  public int hashCode() { return System.identityHashCode(mTarget); }
  public String toString() { return "IntentSender{" + Integer.toHexString(System.identityHashCode(this)) + ": " + mTarget + "}"; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel out, int flags) { android.app.PendingIntent.writePendingIntentOrNullToParcel(mTarget, out); }
  public static void writeIntentSenderOrNullToParcel(IntentSender sender, android.os.Parcel out) { android.app.PendingIntent.writePendingIntentOrNullToParcel(sender == null ? null : sender.mTarget, out); }
  public static IntentSender readIntentSenderOrNullFromParcel(android.os.Parcel in) { android.app.PendingIntent p = android.app.PendingIntent.readPendingIntentOrNullFromParcel(in); return p == null ? null : p.getIntentSender(); }
  public static final android.os.Parcelable.Creator<IntentSender> CREATOR = new android.os.Parcelable.Creator<IntentSender>() {
    public IntentSender createFromParcel(android.os.Parcel in) { return readIntentSenderOrNullFromParcel(in); }
    public IntentSender[] newArray(int size) { return new IntentSender[size]; }
  };
}

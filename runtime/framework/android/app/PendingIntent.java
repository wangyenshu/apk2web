package android.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import java.util.ArrayList;

/** A token for sending an intent later; equal requests (type, code, Intent.filterEquals) share one token. */
public final class PendingIntent implements android.os.Parcelable {
  public static final int FLAG_ONE_SHOT = 0x40000000, FLAG_NO_CREATE = 0x20000000, FLAG_CANCEL_CURRENT = 0x10000000, FLAG_UPDATE_CURRENT = 0x08000000,
      FLAG_IMMUTABLE = 0x04000000, FLAG_MUTABLE = 0x02000000;
  static final int TYPE_ACTIVITY = 1, TYPE_BROADCAST = 2, TYPE_SERVICE = 3;
  public static class CanceledException extends android.util.AndroidException {
    public CanceledException() {}
    public CanceledException(String m) { super(m); }
    public CanceledException(Exception e) { super(e); }
  }
  public interface OnFinished { void onSendFinished(PendingIntent p, Intent i, int resultCode, String resultData, android.os.Bundle resultExtras); }
  private static final ArrayList<PendingIntent> live = new ArrayList<>();
  final Context ctx; Intent intent; final int type, code, flags; final boolean oneShot; boolean canceled, foreground;
  private PendingIntent(Context c, Intent i, int t, int code, int flags) { ctx = c.getApplicationContext() != null ? c.getApplicationContext() : c; intent = i; type = t; this.code = code; this.flags = flags; oneShot = (flags & FLAG_ONE_SHOT) != 0; }
  private static PendingIntent get(Context c, int code, Intent i, int flags, int type) {
    for (PendingIntent p : live) {
      if (p.type == type && p.code == code && p.intent.filterEquals(i)) {
        if ((flags & FLAG_CANCEL_CURRENT) != 0) { p.cancel(); break; }
        if ((flags & FLAG_UPDATE_CURRENT) != 0) p.intent = new Intent(i);
        return p;
      }
    }
    if ((flags & FLAG_NO_CREATE) != 0) return null;
    PendingIntent p = new PendingIntent(c, new Intent(i), type, code, flags);
    live.add(p);
    return p;
  }
  public static PendingIntent getActivity(Context c, int req, Intent i, int flags) { return get(c, req, i, flags, TYPE_ACTIVITY); }
  public static PendingIntent getActivity(Context c, int req, Intent i, int flags, android.os.Bundle o) { return getActivity(c, req, i, flags); }
  public static PendingIntent getActivities(Context c, int req, Intent[] is, int flags) { return getActivity(c, req, is[is.length - 1], flags); }
  public static PendingIntent getBroadcast(Context c, int req, Intent i, int flags) { return get(c, req, i, flags, TYPE_BROADCAST); }
  public static PendingIntent getService(Context c, int req, Intent i, int flags) { return get(c, req, i, flags, TYPE_SERVICE); }
  public static PendingIntent getForegroundService(Context c, int req, Intent i, int flags) { PendingIntent p = getService(c, req, i, flags); if (p != null) p.foreground = true; return p; }
  public static PendingIntent getActivities(Context c, int req, Intent[] is, int flags, android.os.Bundle options) { return getActivities(c, req, is, flags); }
  public IntentSender getIntentSender() { return new IntentSender(this); }
  public void cancel() { canceled = true; live.remove(this); }
  public void send() throws CanceledException { send(ctx, 0, null); }
  public void send(int code) throws CanceledException { send(ctx, code, null); }
  public void send(Context c, int code, Intent fill) throws CanceledException {
    if (canceled) throw new CanceledException();
    Intent i = new Intent(intent);
    if (fill != null) i.fillIn(fill, 0);
    if (type == TYPE_ACTIVITY) { i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); ctx.startActivity(i); }
    else if (type == TYPE_BROADCAST) ctx.sendBroadcast(i);
    else if (type == TYPE_SERVICE) ctx.startService(i);
    if (oneShot) cancel();
  }
  public void send(int code, OnFinished f, android.os.Handler h) throws CanceledException { send(ctx, code, null, f, h); }
  public void send(Context c, int code, Intent fill, OnFinished f, android.os.Handler h) throws CanceledException { send(c, code, fill, f, h, null); }
  public void send(Context c, int code, Intent fill, OnFinished f, android.os.Handler h, String requiredPermission) throws CanceledException { send(c, code, fill, f, h, requiredPermission, null); }
  public void send(Context c, int code, Intent fill, OnFinished f, android.os.Handler h, String requiredPermission, android.os.Bundle options) throws CanceledException {
    if (f == null) { send(c, code, fill); return; }
    if (canceled) throw new CanceledException();
    Intent i = new Intent(intent);
    if (fill != null) i.fillIn(fill, 0);
    android.os.Handler where = h != null ? h : new android.os.Handler(android.os.Looper.getMainLooper());
    if (type == TYPE_BROADCAST) {
      // an ordered broadcast whose result reaches onSendFinished
      ctx.sendOrderedBroadcast(i, requiredPermission, new android.content.BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) { f.onSendFinished(PendingIntent.this, intent, getResultCode(), getResultData(), getResultExtras(false)); }
      }, where, code, null, null);
    } else {
      if (type == TYPE_ACTIVITY) { i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); ctx.startActivity(i); }
      else if (type == TYPE_SERVICE) ctx.startService(i);
      where.post(() -> f.onSendFinished(PendingIntent.this, i, code, null, null));
    }
    if (oneShot) cancel();
  }
  public String getTargetPackage() { return ctx.getPackageName(); }
  public String getCreatorPackage() { return ctx.getPackageName(); }
  public boolean isActivity() { return type == TYPE_ACTIVITY; }
  public boolean isBroadcast() { return type == TYPE_BROADCAST; }
  public boolean isService() { return type == TYPE_SERVICE; }
  @Override public boolean equals(Object o) { return o == this; }
  @Override public int hashCode() { return System.identityHashCode(this); }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) { p.writeInt(token()); }
  public void send(android.os.Bundle options) throws CanceledException { send(); }
  public int getCreatorUid() { return android.os.Process.myUid(); }
  public android.os.UserHandle getCreatorUserHandle() { return android.os.Process.myUserHandle(); }
  public boolean isForegroundService() { return type == TYPE_SERVICE && foreground; }
  public boolean isImmutable() { return (flags & FLAG_IMMUTABLE) != 0; }
  // ---- parcels: tokens of this process (a registry id)
  private static final java.util.HashMap<Integer, PendingIntent> tokens = new java.util.HashMap<>();
  private static int nextToken = 1;
  synchronized int token() { for (java.util.Map.Entry<Integer, PendingIntent> e : tokens.entrySet()) if (e.getValue() == this) return e.getKey(); tokens.put(nextToken, this); return nextToken++; }
  static PendingIntent fromToken(int t) { synchronized (PendingIntent.class) { return tokens.get(t); } }
  public static void writePendingIntentOrNullToParcel(PendingIntent sender, android.os.Parcel out) { out.writeInt(sender == null ? 0 : sender.token()); }
  public static PendingIntent readPendingIntentOrNullFromParcel(android.os.Parcel in) { int t = in.readInt(); return t == 0 ? null : fromToken(t); }
  public static final android.os.Parcelable.Creator<PendingIntent> CREATOR = new android.os.Parcelable.Creator<PendingIntent>() {
    public PendingIntent createFromParcel(android.os.Parcel in) { return fromToken(in.readInt()); }
    public PendingIntent[] newArray(int size) { return new PendingIntent[size]; }
  };
  /** (not part of the API) the intent this sends, filled in */
  public Intent webIntent(Intent fill) { Intent i = new Intent(intent); if (fill != null) i.fillIn(fill, 0); return i; }
}

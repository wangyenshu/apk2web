package android.os;

/**
 * A local binder: attachInterface/queryLocalInterface give AIDL clients the object itself, and transact() calls
 * onTransact() directly with the parcels (a converted app runs in one process, so every binder is local).
 */
public class Binder implements IBinder {
  private IInterface mOwner;
  private String mDescriptor;
  private static long sCallingIdentity;   // pid << 32 | uid of the "caller": always this process
  private static int sWorkSourceUid = -1;
  public Binder() {}
  public Binder(String descriptor) { mDescriptor = descriptor; }

  public static final int getCallingPid() { return Process.myPid(); }
  public static final int getCallingUid() { return Process.myUid(); }
  public static final int getCallingUidOrThrow() { return Process.myUid(); }
  public static final UserHandle getCallingUserHandle() { return UserHandle.getUserHandleForUid(getCallingUid()); }
  public static final long clearCallingIdentity() { return ((long) Process.myPid() << 32) | (Process.myUid() & 0xffffffffL); }
  public static final void restoreCallingIdentity(long token) { sCallingIdentity = token; }
  public static final int getCallingWorkSourceUid() { return sWorkSourceUid; }
  public static final long setCallingWorkSourceUid(int workSource) { long old = sWorkSourceUid & 0xffffffffL; sWorkSourceUid = workSource; return old; }
  public static final long clearCallingWorkSource() { return setCallingWorkSourceUid(-1); }
  public static final void restoreCallingWorkSource(long token) { sWorkSourceUid = (int) token; }
  /** commands go straight to the local object: nothing is pending */
  public static final void flushPendingCommands() {}

  public void attachInterface(IInterface owner, String descriptor) { mOwner = owner; mDescriptor = descriptor; }
  public String getInterfaceDescriptor() { return mDescriptor; }
  public boolean pingBinder() { return true; }
  public boolean isBinderAlive() { return true; }
  public IInterface queryLocalInterface(String descriptor) { return mDescriptor != null && mDescriptor.equals(descriptor) ? mOwner : null; }

  protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
    if (code == INTERFACE_TRANSACTION) {
      if (reply != null) reply.writeString(getInterfaceDescriptor());
      return true;
    }
    if (code == PING_TRANSACTION) return true;
    if (code == DUMP_TRANSACTION) {
      java.io.StringWriter sw = new java.io.StringWriter();
      java.io.PrintWriter pw = new java.io.PrintWriter(sw);
      dump(null, pw, data == null ? new String[0] : data.createStringArray());
      pw.flush();
      if (reply != null) reply.writeString(sw.toString());
      return true;
    }
    return false;
  }
  public final boolean transact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
    if (data != null) data.setDataPosition(0);
    boolean r = onTransact(code, data, reply, flags);
    if (reply != null) reply.setDataPosition(0);
    return r;
  }
  /** a local binder never dies */
  public void linkToDeath(DeathRecipient recipient, int flags) {}
  public boolean unlinkToDeath(DeathRecipient recipient, int flags) { return true; }

  public void dump(a2w.jdk.FileDescriptor fd, String[] args) {
    java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.OutputStreamWriter(fdStream(fd)));
    try { dump(fd, pw, args); } finally { pw.flush(); }
  }
  public void dumpAsync(a2w.jdk.FileDescriptor fd, String[] args) { dump(fd, args); }
  protected void dump(a2w.jdk.FileDescriptor fd, java.io.PrintWriter fout, String[] args) {}
  private static java.io.OutputStream fdStream(a2w.jdk.FileDescriptor fd) {
    return new java.io.OutputStream() {
      @Override public void write(int b) throws java.io.IOException { write(new byte[] {(byte) b}, 0, 1); }
      @Override public void write(byte[] b, int off, int len) throws java.io.IOException {
        if (fd == null || fd == a2w.jdk.FileDescriptor.out || fd == a2w.jdk.FileDescriptor.err) { System.out.print(new String(b, off, len)); return; }
        try { android.system.Os.write(fd, b, off, len); } catch (android.system.ErrnoException e) { throw new java.io.IOException(e.getMessage()); }
      }
    };
  }
}

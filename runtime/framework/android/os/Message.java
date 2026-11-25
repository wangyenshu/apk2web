package android.os;

public final class Message {
  public int what, arg1, arg2;
  public Object obj;
  public Messenger replyTo;
  long when;
  Handler target;
  Runnable callback;
  Message next;
  Bundle data;
  boolean async;
  public boolean isAsynchronous() { return async; }
  public void setAsynchronous(boolean a) { async = a; }

  public Message() {}
  public static Message obtain() { return new Message(); }
  public static Message obtain(Handler h) { Message m = new Message(); m.target = h; return m; }
  public static Message obtain(Handler h, int what) { Message m = obtain(h); m.what = what; return m; }
  public static Message obtain(Handler h, int what, Object obj) { Message m = obtain(h, what); m.obj = obj; return m; }
  public static Message obtain(Handler h, int what, int a1, int a2) { Message m = obtain(h, what); m.arg1 = a1; m.arg2 = a2; return m; }
  public static Message obtain(Handler h, int what, int a1, int a2, Object obj) { Message m = obtain(h, what, a1, a2); m.obj = obj; return m; }
  public static Message obtain(Handler h, Runnable r) { Message m = obtain(h); m.callback = r; return m; }
  public static Message obtain(Message o) { Message m = new Message(); m.what = o.what; m.arg1 = o.arg1; m.arg2 = o.arg2; m.obj = o.obj; m.target = o.target; m.callback = o.callback; m.data = o.data; return m; }
  public void recycle() {}
  public Handler getTarget() { return target; }
  public void setTarget(Handler h) { target = h; }
  public Runnable getCallback() { return callback; }
  public long getWhen() { return when; }
  public Bundle getData() { if (data == null) data = new Bundle(); return data; }
  public Bundle peekData() { return data; }
  public void setData(Bundle b) { data = b; }
  public void sendToTarget() { target.sendMessage(this); }
  public String toString() { return "Message{what=" + what + " obj=" + obj + (target != null ? " target=" + target.getClass().getName() : "") + (callback != null ? " callback=" + callback.getClass().getName() : "") + "}"; }
}

package a2w;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Broadcast delivery, as ActivityManager's broadcast queue does for one app: registered receivers match by
 * IntentFilter.match (action, categories, data, type), manifest receivers by the manifest's filters. Normal
 * broadcasts reach every receiver; ordered ones reach them one at a time by priority (registered first at equal
 * priority), carrying the result code, data and extras, and stop when one aborts; the final result goes to the
 * sender's result receiver. Sticky broadcasts are kept and delivered to receivers registered later.
 */
public final class Broadcasts {
  private Broadcasts() {}
  private static final String TAG = "Broadcasts";

  static final class Reg {
    final Context ctx; final BroadcastReceiver receiver; final IntentFilter filter; final Handler handler;
    Reg(Context ctx, BroadcastReceiver receiver, IntentFilter filter, Handler handler) { this.ctx = ctx; this.receiver = receiver; this.filter = filter; this.handler = handler; }
  }
  private static final ArrayList<Reg> regs = new ArrayList<>();
  private static final ArrayList<Intent> sticky = new ArrayList<>();

  private static Handler main() { return ActivityThread.h(); }
  private static Context app() { Context a = ActivityThread.app; return a != null ? a : ActivityThread.base; }
  private static boolean matches(Context c, IntentFilter f, Intent intent) {
    try { return f.match(c.getContentResolver(), intent, true, TAG) >= 0; } catch (RuntimeException e) { return false; }
  }

  // ---- registration
  public static Intent register(Context ctx, BroadcastReceiver r, IntentFilter f, Handler h) {
    ArrayList<Intent> stickies = new ArrayList<>();
    synchronized (sticky) { for (Intent i : sticky) if (matches(ctx, f, i)) stickies.add(i); }
    if (r != null) {
      Reg reg = new Reg(ctx, r, f, h != null ? h : main());
      synchronized (regs) { regs.add(reg); }
      for (Intent i : stickies) deliverOne(reg.handler, reg.ctx, r, new Intent(i), false, true, null);
    }
    return stickies.isEmpty() ? null : new Intent(stickies.get(0));
  }
  public static void unregister(BroadcastReceiver r) {
    boolean removed;
    synchronized (regs) { removed = regs.removeIf(x -> x.receiver == r); }
    if (!removed) throw new IllegalArgumentException("Receiver not registered: " + r);
  }

  // ---- sending
  /** a target: a registered receiver or a manifest receiver class (instantiated for each broadcast) */
  static final class Target { Reg reg; String cls; int priority; }
  private static List<Target> targets(Context c, Intent intent) {
    ArrayList<Target> out = new ArrayList<>();
    if (intent.getComponent() == null) {
      ArrayList<Reg> snapshot;
      synchronized (regs) { snapshot = new ArrayList<>(regs); }
      for (Reg r : snapshot) if (matches(r.ctx, r.filter, intent)) { Target t = new Target(); t.reg = r; t.priority = r.filter.getPriority(); out.add(t); }
    }
    if ((intent.getFlags() & Intent.FLAG_RECEIVER_REGISTERED_ONLY) == 0)
      for (String cls : Intents.resolve(Intents.RECEIVERS, intent)) { Target t = new Target(); t.cls = cls; out.add(t); }
    out.sort((a, b) -> Integer.compare(b.priority, a.priority));   // stable: registered first at equal priority
    return out;
  }
  private static BroadcastReceiver instantiate(String cls) {
    Object o = Reflection.newInstance(cls, new Class<?>[0]);
    if (o instanceof BroadcastReceiver) return (BroadcastReceiver) o;
    Log.w(TAG, "Unable to instantiate receiver " + cls);
    return null;
  }

  public static void send(Context c, Intent intent) {
    for (Target t : targets(c, intent)) {
      if (t.reg != null) deliverOne(t.reg.handler, t.reg.ctx, t.reg.receiver, new Intent(intent), false, false, null);
      else { String cls = t.cls; main().post(() -> { BroadcastReceiver r = instantiate(cls); if (r != null) runReceiver(app(), r, new Intent(intent), false, false, null, 0, null, null); }); }
    }
  }
  public static void sendSticky(Context c, Intent intent) {
    synchronized (sticky) { sticky.removeIf(i -> i.filterEquals(intent)); sticky.add(new Intent(intent)); }
    send(c, intent);
  }
  public static void removeSticky(Intent intent) { synchronized (sticky) { sticky.removeIf(i -> i.filterEquals(intent)); } }

  /** ordered delivery: each receiver runs after the previous one finished (returned, or finished its goAsync result) */
  public static void sendOrdered(Context c, Intent intent, BroadcastReceiver resultReceiver, Handler scheduler, int code, String data, Bundle extras, boolean stickyToo) {
    if (stickyToo) synchronized (sticky) { sticky.removeIf(i -> i.filterEquals(intent)); sticky.add(new Intent(intent)); }
    List<Target> list = targets(c, intent);
    Ordered o = new Ordered(c, intent, list, resultReceiver, scheduler != null ? scheduler : main(), code, data, extras);
    main().post(o::next);
  }
  static final class Ordered {
    final Context ctx; final Intent intent; final List<Target> list; final BroadcastReceiver resultReceiver; final Handler scheduler;
    int index, code; String data; Bundle extras;
    Ordered(Context ctx, Intent intent, List<Target> list, BroadcastReceiver rr, Handler scheduler, int code, String data, Bundle extras) {
      this.ctx = ctx; this.intent = intent; this.list = list; resultReceiver = rr; this.scheduler = scheduler; this.code = code; this.data = data; this.extras = extras;
    }
    void next() {
      if (index >= list.size()) { finish(); return; }
      Target t = list.get(index++);
      Handler h = t.reg != null ? t.reg.handler : main();
      h.post(() -> {
        BroadcastReceiver r = t.reg != null ? t.reg.receiver : instantiate(t.cls);
        if (r == null) { main().post(this::next); return; }
        runReceiver(t.reg != null ? t.reg.ctx : app(), r, new Intent(intent), true, false, this, code, data, extras);
      });
    }
    /** the receiver's result, when it finished */
    void done(BroadcastReceiver.PendingResult p) {
      code = p.getResultCode(); data = p.getResultData(); extras = p.getResultExtras(false);
      if (p.getAbortBroadcast()) index = list.size();
      main().post(this::next);
    }
    void finish() {
      if (resultReceiver == null) return;
      scheduler.post(() -> runReceiver(ctx, resultReceiver, new Intent(intent), false, false, null, code, data, extras));
    }
  }

  private static void deliverOne(Handler h, Context ctx, BroadcastReceiver r, Intent intent, boolean ordered, boolean initialSticky, Ordered chain) {
    h.post(() -> runReceiver(ctx, r, intent, ordered, initialSticky, chain, 0, null, null));
  }
  /** runs onReceive with a pending result; the chain continues when the result finishes */
  static void runReceiver(Context ctx, BroadcastReceiver r, Intent intent, boolean ordered, boolean initialSticky, Ordered chain, int code, String data, Bundle extras) {
    BroadcastReceiver.PendingResult p = BroadcastReceiver.PendingResult.webCreate(code, data, extras, ordered, initialSticky, chain == null ? null : chain::done);
    r.setPendingResult(p);
    try { r.onReceive(ctx, intent); }
    catch (Throwable t) { Host.uncaught(t); }
    if (r.getPendingResult() == p) { r.setPendingResult(null); p.finish(); }   // not taken by goAsync
  }
}

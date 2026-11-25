package android.os;

public class HandlerThread extends Thread {
  private Looper looper;
  private boolean dead;
  public HandlerThread(String name) { super(name); }
  public HandlerThread(String name, int prio) { super(name); }
  protected void onLooperPrepared() {}
  @Override public void run() {
    try {
      Looper.prepare();
      synchronized (this) { looper = Looper.myLooper(); notifyAll(); }
      onLooperPrepared();
      Looper.loop();
    } finally {
      synchronized (this) { dead = true; notifyAll(); }   // a thread's end wakes whoever waits on it (as on Android)
    }
  }
  public Looper getLooper() {
    synchronized (this) {
      while (!dead && looper == null) { try { wait(); } catch (InterruptedException e) {} }
    }
    return looper;
  }
  public boolean quit() { Looper l = getLooper(); if (l != null) { l.quit(); return true; } return false; }
}

package a2w.jdk.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/** A task that runs once; get() waits for it. */
public class FutureTask<V> implements RunnableFuture<V> {
  private Callable<V> callable;
  private V result;
  private Throwable failure;
  private volatile int state;   // 0 new, 1 running, 2 done, 3 cancelled
  private Thread runner;
  public FutureTask(Callable<V> c) { if (c == null) throw new NullPointerException(); callable = c; }
  public FutureTask(Runnable r, V result) { this(Executors.callable(r, result)); }
  private synchronized boolean start() { if (state != 0) return false; state = 1; runner = Thread.currentThread(); return true; }
  @Override public void run() {
    if (!start()) return;
    try { set(callable.call()); } catch (Throwable t) { setException(t); }
  }
  protected boolean runAndReset() {
    if (!start()) return false;
    try {
      callable.call();
      synchronized (this) { if (state == 1) { state = 0; runner = null; return true; } return false; }
    } catch (Throwable t) { setException(t); return false; }
  }
  protected void set(V v) { synchronized (this) { if (state >= 2) return; result = v; state = 2; runner = null; notifyAll(); } done(); }
  protected void setException(Throwable t) { synchronized (this) { if (state >= 2) return; failure = t; state = 2; runner = null; notifyAll(); } done(); }
  protected void done() {}
  @Override public boolean cancel(boolean interrupt) {
    Thread r;
    synchronized (this) {
      if (state >= 2) return false;
      r = runner;
      state = 3; runner = null; notifyAll();
    }
    if (interrupt && r != null) r.interrupt();
    done();
    return true;
  }
  @Override public boolean isCancelled() { return state == 3; }
  @Override public boolean isDone() { return state >= 2; }
  private V report() throws ExecutionException {
    if (state == 3) throw new CancellationException();
    if (failure != null) throw new ExecutionException(failure);
    return result;
  }
  @Override public V get() throws InterruptedException, ExecutionException {
    synchronized (this) { while (state < 2) wait(); }
    return report();
  }
  @Override public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    long end = System.currentTimeMillis() + Math.max(0, unit.toMillis(timeout));
    synchronized (this) {
      while (state < 2) { long left = end - System.currentTimeMillis(); if (left <= 0) throw new TimeoutException(); wait(left); }
    }
    return report();
  }
}

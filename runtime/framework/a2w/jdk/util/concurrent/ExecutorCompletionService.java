package a2w.jdk.util.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class ExecutorCompletionService<V> implements CompletionService<V> {
  private final Executor executor;
  private final BlockingQueue<Future<V>> done;
  public ExecutorCompletionService(Executor e) { this(e, new LinkedBlockingQueue<Future<V>>()); }
  public ExecutorCompletionService(Executor e, BlockingQueue<Future<V>> q) { if (e == null || q == null) throw new NullPointerException(); executor = e; done = q; }
  private final class Item extends FutureTask<V> {
    Item(Callable<V> c) { super(c); }
    @Override protected void done() { done.add(this); }
  }
  @Override public Future<V> submit(Callable<V> c) { Item f = new Item(c); executor.execute(f); return f; }
  @Override public Future<V> submit(Runnable r, V v) { Item f = new Item(Executors.callable(r, v)); executor.execute(f); return f; }
  @Override public Future<V> take() throws InterruptedException { return done.take(); }
  @Override public Future<V> poll() { return done.poll(); }
  @Override public Future<V> poll(long t, TimeUnit u) throws InterruptedException { return done.poll(t, u); }
}

package a2w.jdk.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/** java.util.concurrent.Executors: the JDK's pool shapes on real threads. */
public class Executors {
  private Executors() {}
  public static ExecutorService newFixedThreadPool(int n) { return new ThreadPoolExecutor(n, n, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()); }
  public static ExecutorService newFixedThreadPool(int n, ThreadFactory f) { return new ThreadPoolExecutor(n, n, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), f); }
  public static ExecutorService newCachedThreadPool() { return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()); }
  public static ExecutorService newCachedThreadPool(ThreadFactory f) { return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), f); }
  public static ExecutorService newSingleThreadExecutor() { return newFixedThreadPool(1); }
  public static ExecutorService newSingleThreadExecutor(ThreadFactory f) { return newFixedThreadPool(1, f); }
  public static ExecutorService newWorkStealingPool() { return new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true); }
  public static ExecutorService newWorkStealingPool(int n) { return new ForkJoinPool(n, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true); }
  public static ScheduledExecutorService newScheduledThreadPool(int n) { return new ScheduledThreadPoolExecutor(n); }
  public static ScheduledExecutorService newScheduledThreadPool(int n, ThreadFactory f) { return new ScheduledThreadPoolExecutor(n, f); }
  public static ScheduledExecutorService newSingleThreadScheduledExecutor() { return new ScheduledThreadPoolExecutor(1); }
  public static ScheduledExecutorService newSingleThreadScheduledExecutor(ThreadFactory f) { return new ScheduledThreadPoolExecutor(1, f); }
  public static ExecutorService unconfigurableExecutorService(ExecutorService e) { if (e == null) throw new NullPointerException(); return e; }
  public static ScheduledExecutorService unconfigurableScheduledExecutorService(ScheduledExecutorService e) { if (e == null) throw new NullPointerException(); return e; }
  private static int poolNumber;
  public static ThreadFactory defaultThreadFactory() {
    int pool;
    synchronized (Executors.class) { pool = ++poolNumber; }
    int[] next = {0};
    return r -> {
      int n;
      synchronized (next) { n = ++next[0]; }
      Thread t = new Thread(r, "pool-" + pool + "-thread-" + n);
      t.setDaemon(false);
      return t;
    };
  }
  public static ThreadFactory privilegedThreadFactory() { return defaultThreadFactory(); }
  public static <T> Callable<T> callable(Runnable r, T result) { if (r == null) throw new NullPointerException(); return () -> { r.run(); return result; }; }
  public static Callable<Object> callable(Runnable r) { return callable(r, null); }
}

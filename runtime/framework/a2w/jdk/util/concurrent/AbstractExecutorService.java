package a2w.jdk.util.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public abstract class AbstractExecutorService implements ExecutorService {
  protected <T> RunnableFuture<T> newTaskFor(Runnable r, T v) { return new FutureTask<>(r, v); }
  protected <T> RunnableFuture<T> newTaskFor(Callable<T> c) { return new FutureTask<>(c); }
  @Override public Future<?> submit(Runnable task) { if (task == null) throw new NullPointerException(); RunnableFuture<Object> f = newTaskFor(task, null); execute(f); return f; }
  @Override public <T> Future<T> submit(Runnable task, T result) { if (task == null) throw new NullPointerException(); RunnableFuture<T> f = newTaskFor(task, result); execute(f); return f; }
  @Override public <T> Future<T> submit(Callable<T> task) { if (task == null) throw new NullPointerException(); RunnableFuture<T> f = newTaskFor(task); execute(f); return f; }
  @Override public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
    List<Future<T>> out = new ArrayList<>();
    for (Callable<T> c : tasks) { RunnableFuture<T> f = newTaskFor(c); out.add(f); execute(f); }
    for (Future<T> f : out) { try { f.get(); } catch (ExecutionException | CancellationException e) { /* reported by the future */ } }
    return out;
  }
  @Override public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long t, TimeUnit u) throws InterruptedException {
    long end = System.currentTimeMillis() + Math.max(0, u.toMillis(t));
    List<Future<T>> out = new ArrayList<>();
    for (Callable<T> c : tasks) { RunnableFuture<T> f = newTaskFor(c); out.add(f); execute(f); }
    for (Future<T> f : out) {
      long left = end - System.currentTimeMillis();
      if (left <= 0) { for (Future<T> g : out) g.cancel(true); break; }
      try { f.get(left, TimeUnit.MILLISECONDS); } catch (ExecutionException | CancellationException e) { /* reported */ } catch (TimeoutException e) { for (Future<T> g : out) g.cancel(true); break; }
    }
    return out;
  }
  @Override public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
    try { return invokeAny(tasks, Long.MAX_VALUE / 4, TimeUnit.MILLISECONDS); } catch (TimeoutException e) { throw new ExecutionException(e); }
  }
  @Override public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long t, TimeUnit u) throws InterruptedException, ExecutionException, TimeoutException {
    if (tasks.isEmpty()) throw new IllegalArgumentException();
    ExecutorCompletionService<T> ecs = new ExecutorCompletionService<>(this);
    List<Future<T>> fs = new ArrayList<>();
    long end = System.currentTimeMillis() + Math.max(0, u.toMillis(t));
    try {
      for (Callable<T> c : tasks) fs.add(ecs.submit(c));
      ExecutionException last = null;
      for (int i = 0; i < fs.size(); i++) {
        long left = end - System.currentTimeMillis();
        Future<T> f = ecs.poll(Math.max(0, left), TimeUnit.MILLISECONDS);
        if (f == null) throw new TimeoutException();
        try { return f.get(); } catch (ExecutionException e) { last = e; } catch (CancellationException e) { last = new ExecutionException(e); }
      }
      throw last != null ? last : new ExecutionException(new IllegalStateException());
    } finally { for (Future<T> f : fs) f.cancel(true); }
  }
}

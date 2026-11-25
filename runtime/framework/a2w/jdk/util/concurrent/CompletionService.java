package a2w.jdk.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public interface CompletionService<V> {
  Future<V> submit(Callable<V> task);
  Future<V> submit(Runnable task, V result);
  Future<V> take() throws InterruptedException;
  Future<V> poll();
  Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;
}

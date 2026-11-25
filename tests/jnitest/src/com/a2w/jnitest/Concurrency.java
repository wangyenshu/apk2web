package com.a2w.jnitest;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** blocking java.util.concurrent behaviour that only works if executors and locks use real, waiting threads */
final class Concurrency {
  static final String EXPECTED = "other thread true, queue 4950, ping-pong 10, latch true, rate true, barrier 4, semaphore true, "
      + "future 7, park true, rw 2, sync 3, invokeAll 6, shutdown true";
  static String run(Thread me) {
    StringBuilder out = new StringBuilder();
    try {
      ExecutorService pool = Executors.newFixedThreadPool(2);
      out.append("other thread ").append(pool.submit(() -> Thread.currentThread() != me).get(2, TimeUnit.SECONDS));

      BlockingQueue<Integer> q = new LinkedBlockingQueue<>(5);
      pool.execute(() -> { try { for (int i = 0; i < 100; i++) q.put(i); } catch (InterruptedException e) { } });
      int sum = 0;
      for (int i = 0; i < 100; i++) { Integer v = q.poll(2, TimeUnit.SECONDS); if (v == null) break; sum += v; }
      out.append(", queue ").append(sum);

      ReentrantLock lock = new ReentrantLock();
      Condition turn = lock.newCondition();
      int[] state = {0, 0};   // whose turn (0 me, 1 other), rounds
      Thread other = new Thread(() -> {
        for (int r = 0; r < 10; r++) {
          lock.lock();
          try { while (state[0] != 1) turn.await(); state[0] = 0; state[1]++; turn.signalAll(); }
          catch (InterruptedException e) { return; }
          finally { lock.unlock(); }
        }
      });
      other.start();
      for (int r = 0; r < 10; r++) {
        lock.lock();
        try { state[0] = 1; turn.signalAll(); while (state[0] != 0) if (!turn.await(2, TimeUnit.SECONDS)) break; }
        finally { lock.unlock(); }
      }
      out.append(", ping-pong ").append(state[1]);

      CountDownLatch latch = new CountDownLatch(3);
      for (int i = 0; i < 3; i++) pool.execute(latch::countDown);
      out.append(", latch ").append(latch.await(2, TimeUnit.SECONDS));

      ScheduledExecutorService sched = Executors.newSingleThreadScheduledExecutor();
      AtomicInteger ticks = new AtomicInteger();
      ScheduledFuture<?> tick = sched.scheduleAtFixedRate(ticks::incrementAndGet, 0, 20, TimeUnit.MILLISECONDS);
      Thread.sleep(150);
      tick.cancel(false);
      out.append(", rate ").append(ticks.get() >= 3);
      sched.shutdown();

      ExecutorService cached = Executors.newCachedThreadPool();
      CyclicBarrier barrier = new CyclicBarrier(4);
      AtomicInteger met = new AtomicInteger();
      for (int i = 0; i < 4; i++) cached.execute(() -> { try { barrier.await(2, TimeUnit.SECONDS); met.incrementAndGet(); } catch (Exception e) { } });
      for (int i = 0; i < 100 && met.get() < 4; i++) Thread.sleep(10);
      out.append(", barrier ").append(met.get());

      Semaphore sem = new Semaphore(0);
      pool.execute(() -> { try { Thread.sleep(20); } catch (InterruptedException e) { } sem.release(); });
      out.append(", semaphore ").append(sem.tryAcquire(2, TimeUnit.SECONDS));

      Future<Integer> slow = pool.submit(() -> { Thread.sleep(30); return 7; });
      Thread.sleep(5);
      out.append(", future ").append(slow.get());

      boolean[] unparked = {false};
      Thread parker = new Thread(() -> { LockSupport.park(); unparked[0] = true; });
      parker.start();
      Thread.sleep(10);
      LockSupport.unpark(parker);
      parker.join(2000);
      out.append(", park ").append(unparked[0]);

      ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
      rw.readLock().lock();
      int[] readers = {1};
      Thread r2 = new Thread(() -> { rw.readLock().lock(); readers[0]++; rw.readLock().unlock(); });
      r2.start(); r2.join(2000);
      rw.readLock().unlock();
      out.append(", rw ").append(readers[0]);

      SynchronousQueue<Integer> sq = new SynchronousQueue<>();
      pool.execute(() -> { try { sq.put(3); } catch (InterruptedException e) { } });
      out.append(", sync ").append(sq.poll(2, TimeUnit.SECONDS));

      int total = 0;
      for (Future<Integer> f : pool.invokeAll(java.util.Arrays.<Callable<Integer>>asList(() -> 1, () -> 2, () -> 3))) total += f.get();
      out.append(", invokeAll ").append(total);

      pool.shutdown();
      cached.shutdown();
      out.append(", shutdown ").append(pool.awaitTermination(2, TimeUnit.SECONDS));
    } catch (Throwable e) {
      out.append(" !! ").append(e);
      android.util.Log.e("JNITEST", "concurrency", e);
    }
    return out.toString();
  }
}

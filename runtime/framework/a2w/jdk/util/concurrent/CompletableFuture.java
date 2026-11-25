package a2w.jdk.util.concurrent;

import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

/**
 * java.util.concurrent.CompletableFuture. A result (a value, null or an exception) is set once; dependent stages
 * registered before run when it is set, later ones at once. Exceptions reach dependents wrapped in
 * CompletionException as in the JDK; join() throws that, get() an ExecutionException with the cause. The default
 * executor is the common ForkJoinPool when its parallelism is above 1, otherwise a new thread per task (as the JDK).
 */
public class CompletableFuture<T> implements Future<T>, CompletionStage<T> {
  /** a null value or an exception */
  static final class Alt { final Throwable ex; Alt(Throwable ex) { this.ex = ex; } }
  static final Alt NIL = new Alt(null);

  volatile Object result;
  private ArrayList<Runnable> deps;

  public CompletableFuture() {}
  CompletableFuture(Object r) { result = r; }

  // ---- the result
  static Throwable wrap(Throwable x) { return x instanceof CompletionException ? x : new CompletionException(x); }
  static Object encode(Object r) { return r instanceof Alt && ((Alt) r).ex != null && !(((Alt) r).ex instanceof CompletionException) ? new Alt(wrap(((Alt) r).ex)) : r; }
  static Throwable exOf(Object r) { return r instanceof Alt ? ((Alt) r).ex : null; }
  @SuppressWarnings("unchecked") static <X> X valueOf(Object r) { return r instanceof Alt ? null : (X) r; }

  final boolean completeRaw(Object r) {
    ArrayList<Runnable> d;
    synchronized (this) {
      if (result != null) return false;
      result = r;
      d = deps; deps = null;
      notifyAll();
    }
    if (d != null) for (Runnable x : d) x.run();
    return true;
  }
  final boolean completeValue(Object v) { return completeRaw(v == null ? NIL : v); }
  final boolean completeThrown(Throwable x) { return completeRaw(new Alt(wrap(x))); }
  final void onDone(Runnable r) {
    synchronized (this) {
      if (result == null) { if (deps == null) deps = new ArrayList<>(); deps.add(r); return; }
    }
    r.run();
  }
  static void run(Executor e, CompletableFuture<?> d, Runnable r) {
    Runnable guarded = () -> { try { r.run(); } catch (Throwable x) { d.completeThrown(x); } };
    if (e == null) { guarded.run(); return; }
    try { e.execute(guarded); } catch (Throwable x) { d.completeThrown(x); }
  }

  // ---- reading
  public boolean isDone() { return result != null; }
  public boolean isCancelled() { Object r = result; return r instanceof Alt && ((Alt) r).ex instanceof CancellationException; }
  public boolean isCompletedExceptionally() { Object r = result; return r instanceof Alt && ((Alt) r).ex != null; }
  private Object await(long deadline) throws InterruptedException {
    synchronized (this) {
      while (result == null) {
        if (deadline == 0) wait();
        else { long left = deadline - System.currentTimeMillis(); if (left <= 0) return null; wait(left); }
      }
      return result;
    }
  }
  private static <X> X reportJoin(Object r) {
    Throwable x = exOf(r);
    if (x == null) return valueOf(r);
    if (x instanceof CancellationException) throw (CancellationException) x;
    if (x instanceof CompletionException) throw (CompletionException) x;
    throw new CompletionException(x);
  }
  private static <X> X reportGet(Object r) throws ExecutionException {
    Throwable x = exOf(r);
    if (x == null) return valueOf(r);
    if (x instanceof CancellationException) throw (CancellationException) x;
    Throwable cause;
    if (x instanceof CompletionException && (cause = x.getCause()) != null) x = cause;
    throw new ExecutionException(x);
  }
  public T get() throws InterruptedException, ExecutionException { return reportGet(await(0)); }
  public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    if (Thread.interrupted()) throw new InterruptedException();
    Object r = await(System.currentTimeMillis() + Math.max(1, unit.toMillis(timeout)));
    if (r == null) throw new TimeoutException();
    return reportGet(r);
  }
  public T join() {
    boolean interrupted = false;
    Object r;
    for (;;) { try { r = await(0); break; } catch (InterruptedException e) { interrupted = true; } }
    if (interrupted) Thread.currentThread().interrupt();
    return reportJoin(r);
  }
  public T getNow(T valueIfAbsent) { Object r = result; return r == null ? valueIfAbsent : reportJoin(r); }
  public T resultNow() {
    Object r = result;
    if (r == null) throw new IllegalStateException("Task has not completed");
    Throwable x = exOf(r);
    if (x instanceof CancellationException) throw new IllegalStateException("Task was cancelled");
    if (x != null) throw new IllegalStateException("Task completed with exception");
    return valueOf(r);
  }
  public Throwable exceptionNow() {
    Object r = result;
    if (r == null) throw new IllegalStateException("Task has not completed");
    Throwable x = exOf(r);
    if (x instanceof CancellationException) throw new IllegalStateException("Task was cancelled");
    if (x == null) throw new IllegalStateException("Task completed with a result");
    if (x instanceof CompletionException && x.getCause() != null) x = x.getCause();
    return x;
  }
  public Future$State state() {
    Object r = result;
    if (r == null) return Future$State.RUNNING;
    Throwable x = exOf(r);
    return x == null ? Future$State.SUCCESS : x instanceof CancellationException ? Future$State.CANCELLED : Future$State.FAILED;
  }
  public synchronized int getNumberOfDependents() { return deps == null ? 0 : deps.size(); }

  // ---- completing
  public boolean complete(T value) { return completeValue(value); }
  public boolean completeExceptionally(Throwable ex) { return completeRaw(new Alt(java.util.Objects.requireNonNull(ex))); }
  public boolean cancel(boolean mayInterruptIfRunning) { boolean c = result == null && completeRaw(new Alt(new CancellationException())); return c || isCancelled(); }
  public void obtrudeValue(T value) { synchronized (this) { result = value == null ? NIL : value; notifyAll(); } }
  public void obtrudeException(Throwable ex) { java.util.Objects.requireNonNull(ex); synchronized (this) { result = new Alt(ex); notifyAll(); } }
  public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor) {
    java.util.Objects.requireNonNull(supplier); java.util.Objects.requireNonNull(executor);
    run(executor, this, () -> completeValue(supplier.get()));
    return this;
  }
  public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier) { return completeAsync(supplier, defaultExecutor()); }
  public CompletableFuture<T> orTimeout(long timeout, TimeUnit unit) {
    java.util.Objects.requireNonNull(unit);
    if (result == null) delayer().schedule(() -> { if (result == null) completeRaw(new Alt(new TimeoutException())); }, timeout, unit);
    return this;
  }
  public CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit) {
    java.util.Objects.requireNonNull(unit);
    if (result == null) delayer().schedule(() -> completeValue(value), timeout, unit);
    return this;
  }

  // ---- executors
  private static ScheduledExecutorService delayer;
  static synchronized ScheduledExecutorService delayer() {
    if (delayer == null) {
      delayer = new ScheduledThreadPoolExecutor(1, r -> { Thread t = new Thread(r, "CompletableFutureDelayScheduler"); t.setDaemon(true); return t; });
    }
    return delayer;
  }
  static final Executor THREAD_PER_TASK = r -> { java.util.Objects.requireNonNull(r); new Thread(r).start(); };
  static Executor asyncPool() { return ForkJoinPool.getCommonPoolParallelism() > 1 ? ForkJoinPool.commonPool() : THREAD_PER_TASK; }
  public Executor defaultExecutor() { return asyncPool(); }
  public static Executor delayedExecutor(long delay, TimeUnit unit, Executor executor) {
    java.util.Objects.requireNonNull(unit); java.util.Objects.requireNonNull(executor);
    return r -> delayer().schedule(() -> executor.execute(r), delay, unit);
  }
  public static Executor delayedExecutor(long delay, TimeUnit unit) { return delayedExecutor(delay, unit, asyncPool()); }
  static Executor screen(Executor e) { return java.util.Objects.requireNonNull(e); }

  // ---- making futures
  public <U> CompletableFuture<U> newIncompleteFuture() { return new CompletableFuture<U>(); }
  public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor) {
    java.util.Objects.requireNonNull(supplier);
    CompletableFuture<U> d = new CompletableFuture<>();
    run(screen(executor), d, () -> d.completeValue(supplier.get()));
    return d;
  }
  public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) { return supplyAsync(supplier, asyncPool()); }
  public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor) {
    java.util.Objects.requireNonNull(runnable);
    CompletableFuture<Void> d = new CompletableFuture<>();
    run(screen(executor), d, () -> { runnable.run(); d.completeValue(null); });
    return d;
  }
  public static CompletableFuture<Void> runAsync(Runnable runnable) { return runAsync(runnable, asyncPool()); }
  public static <U> CompletableFuture<U> completedFuture(U value) { return new CompletableFuture<>(value == null ? NIL : value); }
  public static <U> CompletableFuture<U> failedFuture(Throwable ex) { return new CompletableFuture<>(new Alt(java.util.Objects.requireNonNull(ex))); }
  public static <U> CompletionStage<U> completedStage(U value) { return new MinimalStage<>(value == null ? NIL : value); }
  public static <U> CompletionStage<U> failedStage(Throwable ex) { return new MinimalStage<>(new Alt(java.util.Objects.requireNonNull(ex))); }
  public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs) {
    CompletableFuture<Void> d = new CompletableFuture<>();
    if (cfs.length == 0) { d.completeValue(null); return d; }
    int[] left = {cfs.length};
    for (CompletableFuture<?> f : cfs) {
      java.util.Objects.requireNonNull(f);
      f.onDone(() -> {
        boolean last;
        synchronized (left) { last = --left[0] == 0; }
        if (!last) return;
        for (CompletableFuture<?> g : cfs) { Throwable x = exOf(g.result); if (x != null) { d.completeRaw(new Alt(wrap(x))); return; } }
        d.completeValue(null);
      });
    }
    return d;
  }
  public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs) {
    CompletableFuture<Object> d = new CompletableFuture<>();
    for (CompletableFuture<?> f : cfs) { java.util.Objects.requireNonNull(f); f.onDone(() -> d.completeRaw(encode(f.result))); }
    return d;
  }
  public CompletableFuture<T> copy() { return uniStage(null, (r, d) -> d.completeRaw(encode(r))); }
  public CompletionStage<T> minimalCompletionStage() {
    MinimalStage<T> m = new MinimalStage<>(null);
    onDone(() -> m.completeRaw(encode(result)));
    return m;
  }
  public CompletableFuture<T> toCompletableFuture() { return this; }

  // ---- dependent stages
  private <U> CompletableFuture<U> uniStage(Executor e, BiConsumer<Object, CompletableFuture<U>> step) {
    CompletableFuture<U> d = newIncompleteFuture();
    onDone(() -> {
      Object r = result;
      if (exOf(r) != null && step instanceof Normal) { d.completeRaw(encode(r)); return; }
      run(e, d, () -> step.accept(r, d));
    });
    return d;
  }
  /** a step that runs only for a normal result (an exception passes to the dependent unchanged but wrapped) */
  interface Normal<U> extends BiConsumer<Object, CompletableFuture<U>> {}
  private <U> CompletableFuture<U> normal(Executor e, Normal<U> step) { return uniStage(e, step); }

  public <U> CompletableFuture<U> thenApply(Function<? super T, ? extends U> fn) { return thenApplyAsync0(null, fn); }
  public <U> CompletableFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn) { return thenApplyAsync0(defaultExecutor(), fn); }
  public <U> CompletableFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor) { return thenApplyAsync0(screen(executor), fn); }
  private <U> CompletableFuture<U> thenApplyAsync0(Executor e, Function<? super T, ? extends U> fn) {
    java.util.Objects.requireNonNull(fn);
    return normal(e, (Normal<U>) (r, d) -> d.completeValue(fn.apply(valueOf(r))));
  }
  public CompletableFuture<Void> thenAccept(Consumer<? super T> action) { return thenAccept0(null, action); }
  public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action) { return thenAccept0(defaultExecutor(), action); }
  public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor) { return thenAccept0(screen(executor), action); }
  private CompletableFuture<Void> thenAccept0(Executor e, Consumer<? super T> action) {
    java.util.Objects.requireNonNull(action);
    return normal(e, (Normal<Void>) (r, d) -> { action.accept(valueOf(r)); d.completeValue(null); });
  }
  public CompletableFuture<Void> thenRun(Runnable action) { return thenRun0(null, action); }
  public CompletableFuture<Void> thenRunAsync(Runnable action) { return thenRun0(defaultExecutor(), action); }
  public CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor) { return thenRun0(screen(executor), action); }
  private CompletableFuture<Void> thenRun0(Executor e, Runnable action) {
    java.util.Objects.requireNonNull(action);
    return normal(e, (Normal<Void>) (r, d) -> { action.run(); d.completeValue(null); });
  }
  public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn) { return thenCompose0(null, fn); }
  public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) { return thenCompose0(defaultExecutor(), fn); }
  public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) { return thenCompose0(screen(executor), fn); }
  private <U> CompletableFuture<U> thenCompose0(Executor e, Function<? super T, ? extends CompletionStage<U>> fn) {
    java.util.Objects.requireNonNull(fn);
    return normal(e, (Normal<U>) (r, d) -> {
      CompletableFuture<U> f = fn.apply(valueOf(r)).toCompletableFuture();
      f.onDone(() -> d.completeRaw(encode(f.result)));
    });
  }
  public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) { return whenComplete0(null, action); }
  public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action) { return whenComplete0(defaultExecutor(), action); }
  public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor) { return whenComplete0(screen(executor), action); }
  private CompletableFuture<T> whenComplete0(Executor e, BiConsumer<? super T, ? super Throwable> action) {
    java.util.Objects.requireNonNull(action);
    return uniStage(e, (r, d) -> {
      Throwable x = exOf(r);
      try { action.accept(x == null ? valueOf(r) : null, x); }
      catch (Throwable ex) {
        if (x == null) { d.completeThrown(ex); return; }
        if (x != ex) x.addSuppressed(ex);
      }
      d.completeRaw(encode(r));
    });
  }
  public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) { return handle0(null, fn); }
  public <U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) { return handle0(defaultExecutor(), fn); }
  public <U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) { return handle0(screen(executor), fn); }
  private <U> CompletableFuture<U> handle0(Executor e, BiFunction<? super T, Throwable, ? extends U> fn) {
    java.util.Objects.requireNonNull(fn);
    return uniStage(e, (r, d) -> { Throwable x = exOf(r); d.completeValue(fn.apply(x == null ? valueOf(r) : null, x)); });
  }
  public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn) { return exceptionally0(null, fn); }
  public CompletableFuture<T> exceptionallyAsync(Function<Throwable, ? extends T> fn) { return exceptionally0(defaultExecutor(), fn); }
  public CompletableFuture<T> exceptionallyAsync(Function<Throwable, ? extends T> fn, Executor executor) { return exceptionally0(screen(executor), fn); }
  private CompletableFuture<T> exceptionally0(Executor e, Function<Throwable, ? extends T> fn) {
    java.util.Objects.requireNonNull(fn);
    CompletableFuture<T> d = newIncompleteFuture();
    onDone(() -> {
      Object r = result;
      Throwable x = exOf(r);
      if (x == null) d.completeRaw(r);
      else run(e, d, () -> d.completeValue(fn.apply(x)));
    });
    return d;
  }
  public CompletableFuture<T> exceptionallyCompose(Function<Throwable, ? extends CompletionStage<T>> fn) { return exceptionallyCompose0(null, fn); }
  public CompletableFuture<T> exceptionallyComposeAsync(Function<Throwable, ? extends CompletionStage<T>> fn) { return exceptionallyCompose0(defaultExecutor(), fn); }
  public CompletableFuture<T> exceptionallyComposeAsync(Function<Throwable, ? extends CompletionStage<T>> fn, Executor executor) { return exceptionallyCompose0(screen(executor), fn); }
  private CompletableFuture<T> exceptionallyCompose0(Executor e, Function<Throwable, ? extends CompletionStage<T>> fn) {
    java.util.Objects.requireNonNull(fn);
    CompletableFuture<T> d = newIncompleteFuture();
    onDone(() -> {
      Object r = result;
      Throwable x = exOf(r);
      if (x == null) { d.completeRaw(r); return; }
      run(e, d, () -> { CompletableFuture<T> f = fn.apply(x).toCompletableFuture(); f.onDone(() -> d.completeRaw(encode(f.result))); });
    });
    return d;
  }

  // ---- two stages
  private <U, V> CompletableFuture<V> both(CompletionStage<U> other, Executor e, BiFunction<Object, Object, Object> fn, boolean value) {
    CompletableFuture<?> o = other.toCompletableFuture();
    CompletableFuture<V> d = newIncompleteFuture();
    onDone(() -> o.onDone(() -> {
      Object r = result, s = o.result;
      Throwable x = exOf(r) != null ? exOf(r) : exOf(s);
      if (x != null) { d.completeRaw(new Alt(wrap(x))); return; }
      run(e, d, () -> d.completeValue(fn.apply(valueOf(r), valueOf(s))));
    }));
    return d;
  }
  public <U, V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) { return thenCombine0(other, null, fn); }
  public <U, V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) { return thenCombine0(other, defaultExecutor(), fn); }
  public <U, V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor) { return thenCombine0(other, screen(executor), fn); }
  @SuppressWarnings("unchecked")
  private <U, V> CompletableFuture<V> thenCombine0(CompletionStage<? extends U> other, Executor e, BiFunction<? super T, ? super U, ? extends V> fn) {
    java.util.Objects.requireNonNull(fn);
    return both((CompletionStage<U>) other, e, (a, b) -> fn.apply((T) a, (U) b), true);
  }
  public <U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) { return thenAcceptBoth0(other, null, action); }
  public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) { return thenAcceptBoth0(other, defaultExecutor(), action); }
  public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor) { return thenAcceptBoth0(other, screen(executor), action); }
  @SuppressWarnings("unchecked")
  private <U> CompletableFuture<Void> thenAcceptBoth0(CompletionStage<? extends U> other, Executor e, BiConsumer<? super T, ? super U> action) {
    java.util.Objects.requireNonNull(action);
    return both((CompletionStage<U>) other, e, (a, b) -> { action.accept((T) a, (U) b); return null; }, false);
  }
  public CompletableFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action) { return runAfterBoth0(other, null, action); }
  public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action) { return runAfterBoth0(other, defaultExecutor(), action); }
  public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor) { return runAfterBoth0(other, screen(executor), action); }
  private CompletableFuture<Void> runAfterBoth0(CompletionStage<?> other, Executor e, Runnable action) {
    java.util.Objects.requireNonNull(action);
    return both(other, e, (a, b) -> { action.run(); return null; }, false);
  }
  private <V> CompletableFuture<V> either(CompletionStage<?> other, Executor e, Function<Object, Object> fn) {
    CompletableFuture<?> o = other.toCompletableFuture();
    CompletableFuture<V> d = newIncompleteFuture();
    boolean[] fired = {false};
    Consumer<CompletableFuture<?>> first = src -> {
      synchronized (fired) { if (fired[0]) return; fired[0] = true; }
      Object r = src.result;
      if (exOf(r) != null) { d.completeRaw(encode(r)); return; }
      run(e, d, () -> d.completeValue(fn.apply(valueOf(r))));
    };
    onDone(() -> first.accept(this));
    o.onDone(() -> first.accept(o));
    return d;
  }
  public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn) { return applyToEither0(other, null, fn); }
  public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn) { return applyToEither0(other, defaultExecutor(), fn); }
  public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor) { return applyToEither0(other, screen(executor), fn); }
  @SuppressWarnings("unchecked")
  private <U> CompletableFuture<U> applyToEither0(CompletionStage<? extends T> other, Executor e, Function<? super T, U> fn) {
    java.util.Objects.requireNonNull(fn);
    return either(other, e, a -> fn.apply((T) a));
  }
  public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) { return acceptEither0(other, null, action); }
  public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action) { return acceptEither0(other, defaultExecutor(), action); }
  public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor) { return acceptEither0(other, screen(executor), action); }
  @SuppressWarnings("unchecked")
  private CompletableFuture<Void> acceptEither0(CompletionStage<? extends T> other, Executor e, Consumer<? super T> action) {
    java.util.Objects.requireNonNull(action);
    return either(other, e, a -> { action.accept((T) a); return null; });
  }
  public CompletableFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action) { return runAfterEither0(other, null, action); }
  public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action) { return runAfterEither0(other, defaultExecutor(), action); }
  public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor) { return runAfterEither0(other, screen(executor), action); }
  private CompletableFuture<Void> runAfterEither0(CompletionStage<?> other, Executor e, Runnable action) {
    java.util.Objects.requireNonNull(action);
    return either(other, e, a -> { action.run(); return null; });
  }

  public String toString() {
    Object r = result;
    int n; synchronized (this) { n = deps == null ? 0 : deps.size(); }
    String s = r == null ? (n == 0 ? "[Incomplete]" : "[Incomplete with " + n + " dependents]")
        : exOf(r) != null ? "[Completed exceptionally: " + exOf(r) + "]" : "[Completed normally]";
    return super.toString() + s;
  }

  /** completedStage, failedStage, minimalCompletionStage: only the CompletionStage methods */
  static final class MinimalStage<T> extends CompletableFuture<T> {
    MinimalStage(Object r) { super(r); }
    public <U> CompletableFuture<U> newIncompleteFuture() { return new MinimalStage<U>(null); }
    public T get() { throw new UnsupportedOperationException(); }
    public T get(long timeout, TimeUnit unit) { throw new UnsupportedOperationException(); }
    public T getNow(T valueIfAbsent) { throw new UnsupportedOperationException(); }
    public T join() { throw new UnsupportedOperationException(); }
    public T resultNow() { throw new UnsupportedOperationException(); }
    public Throwable exceptionNow() { throw new UnsupportedOperationException(); }
    public boolean complete(T value) { throw new UnsupportedOperationException(); }
    public boolean completeExceptionally(Throwable ex) { throw new UnsupportedOperationException(); }
    public boolean cancel(boolean mayInterruptIfRunning) { throw new UnsupportedOperationException(); }
    public void obtrudeValue(T value) { throw new UnsupportedOperationException(); }
    public void obtrudeException(Throwable ex) { throw new UnsupportedOperationException(); }
    public boolean isDone() { throw new UnsupportedOperationException(); }
    public boolean isCancelled() { throw new UnsupportedOperationException(); }
    public boolean isCompletedExceptionally() { throw new UnsupportedOperationException(); }
    public Future$State state() { throw new UnsupportedOperationException(); }
    public int getNumberOfDependents() { throw new UnsupportedOperationException(); }
    public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor) { throw new UnsupportedOperationException(); }
    public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier) { throw new UnsupportedOperationException(); }
    public CompletableFuture<T> orTimeout(long timeout, TimeUnit unit) { throw new UnsupportedOperationException(); }
    public CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit) { throw new UnsupportedOperationException(); }
    public CompletableFuture<T> toCompletableFuture() { CompletableFuture<T> f = new CompletableFuture<>(); onDone(() -> f.completeRaw(encode(result))); return f; }
  }
}

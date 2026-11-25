package a2w.jdk.util.concurrent;

import java.util.concurrent.Executor;
import java.util.function.*;

/** java.util.concurrent.CompletionStage, with the Java 12 default methods (exceptionallyAsync, exceptionallyCompose). */
public interface CompletionStage<T> {
  <U> CompletionStage<U> thenApply(Function<? super T, ? extends U> fn);
  <U> CompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn);
  <U> CompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor);
  CompletionStage<Void> thenAccept(Consumer<? super T> action);
  CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
  CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor);
  CompletionStage<Void> thenRun(Runnable action);
  CompletionStage<Void> thenRunAsync(Runnable action);
  CompletionStage<Void> thenRunAsync(Runnable action, Executor executor);
  <U, V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn);
  <U, V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn);
  <U, V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor);
  <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action);
  <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action);
  <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor);
  CompletionStage<Void> runAfterBoth(CompletionStage<?> other, Runnable action);
  CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action);
  CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor);
  <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn);
  <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn);
  <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor);
  CompletionStage<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action);
  CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action);
  CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor);
  CompletionStage<Void> runAfterEither(CompletionStage<?> other, Runnable action);
  CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action);
  CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor);
  <U> CompletionStage<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
  <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn);
  <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor);
  <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
  <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
  <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor);
  CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
  CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action);
  CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor);
  CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn);
  CompletableFuture<T> toCompletableFuture();
  default CompletionStage<T> exceptionallyAsync(Function<Throwable, ? extends T> fn) { return exceptionallyAsync(fn, toCompletableFuture().defaultExecutor()); }
  default CompletionStage<T> exceptionallyAsync(Function<Throwable, ? extends T> fn, Executor executor) {
    return handle((r, ex) -> (ex == null) ? this : this.<T>handleAsync((r1, ex1) -> fn.apply(ex1), executor)).thenCompose(Function.identity());
  }
  default CompletionStage<T> exceptionallyCompose(Function<Throwable, ? extends CompletionStage<T>> fn) {
    return handle((r, ex) -> (ex == null) ? this : fn.apply(ex)).thenCompose(Function.identity());
  }
  default CompletionStage<T> exceptionallyComposeAsync(Function<Throwable, ? extends CompletionStage<T>> fn) { return exceptionallyComposeAsync(fn, toCompletableFuture().defaultExecutor()); }
  default CompletionStage<T> exceptionallyComposeAsync(Function<Throwable, ? extends CompletionStage<T>> fn, Executor executor) {
    return handle((r, ex) -> (ex == null) ? this : this.handleAsync((r1, ex1) -> fn.apply(ex1), executor).thenCompose(Function.identity())).thenCompose(Function.identity());
  }
}

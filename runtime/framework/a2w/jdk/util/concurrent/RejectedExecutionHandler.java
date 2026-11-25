package a2w.jdk.util.concurrent;

public interface RejectedExecutionHandler {
  void rejectedExecution(Runnable r, ThreadPoolExecutor executor);
}

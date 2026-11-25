package a2w.jdk.util.concurrent;

public interface ThreadFactory {
  Thread newThread(Runnable r);
}

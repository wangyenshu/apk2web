package a2w.jdk.util.concurrent;

public class RejectedExecutionException extends RuntimeException {
  public RejectedExecutionException() {}
  public RejectedExecutionException(String m) { super(m); }
  public RejectedExecutionException(String m, Throwable c) { super(m, c); }
  public RejectedExecutionException(Throwable c) { super(c); }
}

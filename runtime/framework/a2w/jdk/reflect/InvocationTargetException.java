package a2w.jdk.reflect;

public class InvocationTargetException extends ReflectiveOperationException {
  private final Throwable target;
  public InvocationTargetException() { this(null); }
  public InvocationTargetException(Throwable t) { super((Throwable) null); target = t; }
  public InvocationTargetException(Throwable t, String s) { super(s); target = t; }
  public Throwable getTargetException() { return target; }
  @Override public Throwable getCause() { return target; }
}

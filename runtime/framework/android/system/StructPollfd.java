package android.system;

/** struct pollfd for Os.poll. */
public final class StructPollfd {
  public a2w.jdk.FileDescriptor fd;
  public short events, revents;
  public Object userData;
  public StructPollfd() {}
  @Override public String toString() { return "StructPollfd{fd=" + fd + ", events=" + events + ", revents=" + revents + ", userData=" + userData + "}"; }
}

package a2w.jdk.util.concurrent.locks;

/** java.util.concurrent.locks.AbstractOwnableSynchronizer: the thread holding a synchronizer exclusively. */
public abstract class AbstractOwnableSynchronizer implements java.io.Serializable {
  private transient Thread exclusiveOwnerThread;
  protected AbstractOwnableSynchronizer() {}
  protected final void setExclusiveOwnerThread(Thread thread) { exclusiveOwnerThread = thread; }
  protected final Thread getExclusiveOwnerThread() { return exclusiveOwnerThread; }
}

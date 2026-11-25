package a2w.jdk.util.concurrent.locks;

public interface ReadWriteLock {
  Lock readLock();
  Lock writeLock();
}

package a2w.jdk.util.concurrent.atomic;

/** java.util.concurrent.atomic.AtomicStampedReference: a reference and an int stamp, compared and set together
 *  (framework code is not preempted, so each operation runs alone). */
public class AtomicStampedReference<V> {
  private V ref;
  private int stamp;
  public AtomicStampedReference(V initialRef, int initialStamp) { ref = initialRef; stamp = initialStamp; }
  public V getReference() { return ref; }
  public int getStamp() { return stamp; }
  public V get(int[] stampHolder) { stampHolder[0] = stamp; return ref; }
  public void set(V newReference, int newStamp) { ref = newReference; stamp = newStamp; }
  public boolean compareAndSet(V expectedReference, V newReference, int expectedStamp, int newStamp) {
    if (ref != expectedReference || stamp != expectedStamp) return false;
    ref = newReference; stamp = newStamp; return true;
  }
  public boolean weakCompareAndSet(V expectedReference, V newReference, int expectedStamp, int newStamp) {
    return compareAndSet(expectedReference, newReference, expectedStamp, newStamp);
  }
  public boolean attemptStamp(V expectedReference, int newStamp) {
    if (ref != expectedReference) return false;
    stamp = newStamp; return true;
  }
}

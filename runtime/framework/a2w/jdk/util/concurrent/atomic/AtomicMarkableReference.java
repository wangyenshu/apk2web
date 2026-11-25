package a2w.jdk.util.concurrent.atomic;

/** java.util.concurrent.atomic.AtomicMarkableReference: a reference and a mark, compared and set together (framework
 *  code is not preempted, so each operation runs alone). */
public class AtomicMarkableReference<V> {
  private V ref;
  private boolean mark;
  public AtomicMarkableReference(V initialRef, boolean initialMark) { ref = initialRef; mark = initialMark; }
  public V getReference() { return ref; }
  public boolean isMarked() { return mark; }
  public V get(boolean[] markHolder) { markHolder[0] = mark; return ref; }
  public void set(V newReference, boolean newMark) { ref = newReference; mark = newMark; }
  public boolean compareAndSet(V expectedReference, V newReference, boolean expectedMark, boolean newMark) {
    if (ref != expectedReference || mark != expectedMark) return false;
    ref = newReference; mark = newMark; return true;
  }
  public boolean weakCompareAndSet(V expectedReference, V newReference, boolean expectedMark, boolean newMark) {
    return compareAndSet(expectedReference, newReference, expectedMark, newMark);
  }
  public boolean attemptMark(V expectedReference, boolean newMark) {
    if (ref != expectedReference) return false;
    mark = newMark; return true;
  }
}

package android.util;

/** Maps int keys to objects: keys kept sorted in an array (binary search), as on Android. */
public class SparseArray<E> implements Cloneable {
  private int[] keys;
  private Object[] values;
  private int size;

  public SparseArray() { this(10); }
  public SparseArray(int initialCapacity) { keys = new int[Math.max(1, initialCapacity)]; values = new Object[keys.length]; }

  static int search(int[] a, int n, int key) {
    int lo = 0, hi = n - 1;
    while (lo <= hi) {
      int mid = (lo + hi) >>> 1, v = a[mid];
      if (v < key) lo = mid + 1; else if (v > key) hi = mid - 1; else return mid;
    }
    return ~lo;
  }
  public E get(int key) { return get(key, null); }
  @SuppressWarnings("unchecked") public E get(int key, E valueIfKeyNotFound) { int i = search(keys, size, key); return i < 0 ? valueIfKeyNotFound : (E) values[i]; }
  public boolean contains(int key) { return indexOfKey(key) >= 0; }
  public void put(int key, E value) {
    int i = search(keys, size, key);
    if (i >= 0) { values[i] = value; return; }
    i = ~i;
    if (size == keys.length) { keys = java.util.Arrays.copyOf(keys, size * 2 + 1); values = java.util.Arrays.copyOf(values, size * 2 + 1); }
    System.arraycopy(keys, i, keys, i + 1, size - i);
    System.arraycopy(values, i, values, i + 1, size - i);
    keys[i] = key; values[i] = value; size++;
  }
  public void set(int key, E value) { put(key, value); }
  public void append(int key, E value) {
    if (size > 0 && key <= keys[size - 1]) { put(key, value); return; }
    if (size == keys.length) { keys = java.util.Arrays.copyOf(keys, size * 2 + 1); values = java.util.Arrays.copyOf(values, size * 2 + 1); }
    keys[size] = key; values[size] = value; size++;
  }
  public void delete(int key) { int i = search(keys, size, key); if (i >= 0) removeAt(i); }
  public void remove(int key) { delete(key); }
  public void removeAt(int index) {
    if (index >= size) throw new ArrayIndexOutOfBoundsException(index);
    System.arraycopy(keys, index + 1, keys, index, size - index - 1);
    System.arraycopy(values, index + 1, values, index, size - index - 1);
    values[--size] = null;
  }
  public void removeAtRange(int index, int size) { int end = Math.min(this.size, index + size); for (int i = end - 1; i >= index; i--) removeAt(i); }
  public E removeReturnOld(int key) { int i = search(keys, size, key); if (i < 0) return null; E v = valueAt(i); removeAt(i); return v; }
  public int size() { return size; }
  public int keyAt(int index) { if (index >= size) throw new ArrayIndexOutOfBoundsException(index); return keys[index]; }
  @SuppressWarnings("unchecked") public E valueAt(int index) { if (index >= size) throw new ArrayIndexOutOfBoundsException(index); return (E) values[index]; }
  public void setValueAt(int index, E value) { if (index >= size) throw new ArrayIndexOutOfBoundsException(index); values[index] = value; }
  public int indexOfKey(int key) { return search(keys, size, key); }
  /** by identity (==), as on Android */
  public int indexOfValue(E value) { for (int i = 0; i < size; i++) if (values[i] == value) return i; return -1; }
  public int indexOfValueByValue(E value) { for (int i = 0; i < size; i++) if (java.util.Objects.equals(values[i], value)) return i; return -1; }
  public void clear() { for (int i = 0; i < size; i++) values[i] = null; size = 0; }
  public boolean contentEquals(SparseArray<?> other) {
    if (other == null || other.size != size) return false;
    for (int i = 0; i < size; i++) if (keys[i] != other.keys[i] || !java.util.Objects.equals(values[i], other.values[i])) return false;
    return true;
  }
  public int contentHashCode() { int h = 0; for (int i = 0; i < size; i++) h = 31 * h + keys[i] ^ java.util.Objects.hashCode(values[i]); return h; }
  @Override @SuppressWarnings("unchecked") public SparseArray<E> clone() {
    try { SparseArray<E> c = (SparseArray<E>) super.clone(); c.keys = keys.clone(); c.values = values.clone(); return c; }
    catch (CloneNotSupportedException e) { throw new AssertionError(e); }
  }
  @Override public String toString() {
    if (size == 0) return "{}";
    StringBuilder b = new StringBuilder("{");
    for (int i = 0; i < size; i++) { if (i > 0) b.append(", "); b.append(keys[i]).append('=').append(values[i] == this ? "(this Map)" : values[i]); }
    return b.append('}').toString();
  }
}

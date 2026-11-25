package android.util;

/** android.util.LongSparseArray: long keys kept sorted. */
public class LongSparseArray<E> implements Cloneable {
  private long[] keys;
  private Object[] values;
  private int size;
  public LongSparseArray() { this(10); }
  public LongSparseArray(int capacity) { keys = new long[Math.max(1, capacity)]; values = new Object[keys.length]; }
  private int find(long key) {
    int lo = 0, hi = size - 1;
    while (lo <= hi) { int mid = (lo + hi) >>> 1; long v = keys[mid]; if (v < key) lo = mid + 1; else if (v > key) hi = mid - 1; else return mid; }
    return ~lo;
  }
  @SuppressWarnings("unchecked") public E get(long key) { return get(key, null); }
  @SuppressWarnings("unchecked") public E get(long key, E def) { int i = find(key); return i < 0 ? def : (E) values[i]; }
  public void delete(long key) { int i = find(key); if (i >= 0) removeAt(i); }
  public void remove(long key) { delete(key); }
  public void removeAt(int index) { System.arraycopy(keys, index + 1, keys, index, size - index - 1); System.arraycopy(values, index + 1, values, index, size - index - 1); size--; values[size] = null; }
  public void put(long key, E value) {
    int i = find(key);
    if (i >= 0) { values[i] = value; return; }
    i = ~i;
    if (size == keys.length) { keys = java.util.Arrays.copyOf(keys, size * 2); values = java.util.Arrays.copyOf(values, size * 2); }
    System.arraycopy(keys, i, keys, i + 1, size - i);
    System.arraycopy(values, i, values, i + 1, size - i);
    keys[i] = key; values[i] = value; size++;
  }
  public int size() { return size; }
  public long keyAt(int index) { return keys[index]; }
  @SuppressWarnings("unchecked") public E valueAt(int index) { return (E) values[index]; }
  public void setValueAt(int index, E value) { values[index] = value; }
  public int indexOfKey(long key) { return find(key); }
  public int indexOfValue(E value) { for (int i = 0; i < size; i++) if (values[i] == value) return i; return -1; }
  public void clear() { java.util.Arrays.fill(values, 0, size, null); size = 0; }
  public void append(long key, E value) { if (size > 0 && key <= keys[size - 1]) { put(key, value); return; } put(key, value); }
  @SuppressWarnings("unchecked") @Override public LongSparseArray<E> clone() {
    LongSparseArray<E> c = new LongSparseArray<>(keys.length);
    System.arraycopy(keys, 0, c.keys, 0, size); System.arraycopy(values, 0, c.values, 0, size); c.size = size;
    return c;
  }
  @Override public String toString() {
    StringBuilder b = new StringBuilder("{");
    for (int i = 0; i < size; i++) b.append(i == 0 ? "" : ", ").append(keys[i]).append('=').append(values[i]);
    return b.append('}').toString();
  }
}

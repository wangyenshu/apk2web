package android.util;

/** android.util.SparseLongArray: int keys to long values, keys kept sorted. */
public class SparseLongArray implements Cloneable {
  private int[] keys;
  private long[] values;
  private int size;
  public SparseLongArray() { this(10); }
  public SparseLongArray(int capacity) { keys = new int[Math.max(1, capacity)]; values = new long[keys.length]; }
  private int find(int key) { int i = java.util.Arrays.binarySearch(keys, 0, size, key); return i; }
  public long get(int key) { return get(key, 0); }
  public long get(int key, long def) { int i = find(key); return i < 0 ? def : values[i]; }
  public void delete(int key) { int i = find(key); if (i >= 0) removeAt(i); }
  public void removeAt(int index) { System.arraycopy(keys, index + 1, keys, index, size - index - 1); System.arraycopy(values, index + 1, values, index, size - index - 1); size--; }
  public void put(int key, long value) {
    int i = find(key);
    if (i >= 0) { values[i] = value; return; }
    i = ~i;
    if (size == keys.length) { keys = java.util.Arrays.copyOf(keys, size * 2); values = java.util.Arrays.copyOf(values, size * 2); }
    System.arraycopy(keys, i, keys, i + 1, size - i);
    System.arraycopy(values, i, values, i + 1, size - i);
    keys[i] = key; values[i] = value; size++;
  }
  public int size() { return size; }
  public int keyAt(int index) { return keys[index]; }
  public long valueAt(int index) { return values[index]; }
  public int indexOfKey(int key) { return find(key); }
  public int indexOfValue(long value) { for (int i = 0; i < size; i++) if (values[i] == value) return i; return -1; }
  public void clear() { size = 0; }
  public void append(int key, long value) { put(key, value); }
  @Override public SparseLongArray clone() { SparseLongArray c = new SparseLongArray(keys.length); System.arraycopy(keys, 0, c.keys, 0, size); System.arraycopy(values, 0, c.values, 0, size); c.size = size; return c; }
}

package android.util;

/** Maps int keys to booleans: keys kept sorted (binary search), as on Android. */
public class SparseBooleanArray implements Cloneable {
  private int[] keys;
  private boolean[] values;
  private int size;

  public SparseBooleanArray() { this(10); }
  public SparseBooleanArray(int initialCapacity) { keys = new int[Math.max(1, initialCapacity)]; values = new boolean[keys.length]; }
  public boolean get(int key) { return get(key, false); }
  public boolean get(int key, boolean valueIfKeyNotFound) { int i = SparseArray.search(keys, size, key); return i < 0 ? valueIfKeyNotFound : values[i]; }
  public void put(int key, boolean value) {
    int i = SparseArray.search(keys, size, key);
    if (i >= 0) { values[i] = value; return; }
    i = ~i;
    if (size == keys.length) { keys = java.util.Arrays.copyOf(keys, size * 2 + 1); values = java.util.Arrays.copyOf(values, size * 2 + 1); }
    System.arraycopy(keys, i, keys, i + 1, size - i);
    System.arraycopy(values, i, values, i + 1, size - i);
    keys[i] = key; values[i] = value; size++;
  }
  public void append(int key, boolean value) {
    if (size > 0 && key <= keys[size - 1]) { put(key, value); return; }
    if (size == keys.length) { keys = java.util.Arrays.copyOf(keys, size * 2 + 1); values = java.util.Arrays.copyOf(values, size * 2 + 1); }
    keys[size] = key; values[size] = value; size++;
  }
  public void delete(int key) { int i = SparseArray.search(keys, size, key); if (i >= 0) removeAt(i); }
  public void removeAt(int index) {
    System.arraycopy(keys, index + 1, keys, index, size - index - 1);
    System.arraycopy(values, index + 1, values, index, size - index - 1);
    size--;
  }
  public int size() { return size; }
  public int keyAt(int index) { if (index >= size) throw new ArrayIndexOutOfBoundsException(index); return keys[index]; }
  public boolean valueAt(int index) { if (index >= size) throw new ArrayIndexOutOfBoundsException(index); return values[index]; }
  public void setValueAt(int index, boolean value) { if (index >= size) throw new ArrayIndexOutOfBoundsException(index); values[index] = value; }
  public int indexOfKey(int key) { return SparseArray.search(keys, size, key); }
  public int indexOfValue(boolean value) { for (int i = 0; i < size; i++) if (values[i] == value) return i; return -1; }
  public void clear() { size = 0; }
  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SparseBooleanArray other) || other.size != size) return false;
    for (int i = 0; i < size; i++) if (keys[i] != other.keys[i] || values[i] != other.values[i]) return false;
    return true;
  }
  @Override public int hashCode() { int h = size; for (int i = 0; i < size; i++) h = 31 * h + keys[i] | (values[i] ? 1 : 0); return h; }
  @Override public SparseBooleanArray clone() {
    try { SparseBooleanArray c = (SparseBooleanArray) super.clone(); c.keys = keys.clone(); c.values = values.clone(); return c; }
    catch (CloneNotSupportedException e) { throw new AssertionError(e); }
  }
  @Override public String toString() {
    if (size == 0) return "{}";
    StringBuilder b = new StringBuilder("{");
    for (int i = 0; i < size; i++) { if (i > 0) b.append(", "); b.append(keys[i]).append('=').append(values[i]); }
    return b.append('}').toString();
  }
}

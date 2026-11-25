package android.content;

import java.util.*;

/** Column name to value, in insertion order (statements list the columns in that order, as on Android's ArrayMap... close enough). */
public final class ContentValues implements android.os.Parcelable {
  public static final String TAG = "ContentValues";
  final LinkedHashMap<String, Object> values;
  public ContentValues() { values = new LinkedHashMap<>(8); }
  public ContentValues(int size) { values = new LinkedHashMap<>(Math.max(size, 1) * 2); }
  public ContentValues(ContentValues from) { values = new LinkedHashMap<>(from.values); }
  @Override public boolean equals(Object object) { return object instanceof ContentValues && values.equals(((ContentValues) object).values); }
  @Override public int hashCode() { return values.hashCode(); }
  public void put(String key, String value) { values.put(key, value); }
  public void putAll(ContentValues other) { values.putAll(other.values); }
  public void put(String key, Byte value) { values.put(key, value); }
  public void put(String key, Short value) { values.put(key, value); }
  public void put(String key, Integer value) { values.put(key, value); }
  public void put(String key, Long value) { values.put(key, value); }
  public void put(String key, Float value) { values.put(key, value); }
  public void put(String key, Double value) { values.put(key, value); }
  public void put(String key, Boolean value) { values.put(key, value); }
  public void put(String key, byte[] value) { values.put(key, value); }
  public void putNull(String key) { values.put(key, null); }
  public void putObject(String key, Object value) { values.put(key, value); }
  public int size() { return values.size(); }
  public boolean isEmpty() { return values.isEmpty(); }
  public void remove(String key) { values.remove(key); }
  public void clear() { values.clear(); }
  public boolean containsKey(String key) { return values.containsKey(key); }
  public Object get(String key) { return values.get(key); }
  public String getAsString(String key) { Object value = values.get(key); return value != null ? value.toString() : null; }
  public Long getAsLong(String key) {
    Object value = values.get(key);
    try { return value != null ? ((Number) value).longValue() : null; }
    catch (ClassCastException e) { if (value instanceof CharSequence) { try { return Long.valueOf(value.toString()); } catch (NumberFormatException e2) { return null; } } return null; }
  }
  public Integer getAsInteger(String key) {
    Object value = values.get(key);
    try { return value != null ? ((Number) value).intValue() : null; }
    catch (ClassCastException e) { if (value instanceof CharSequence) { try { return Integer.valueOf(value.toString()); } catch (NumberFormatException e2) { return null; } } return null; }
  }
  public Short getAsShort(String key) {
    Object value = values.get(key);
    try { return value != null ? ((Number) value).shortValue() : null; }
    catch (ClassCastException e) { if (value instanceof CharSequence) { try { return Short.valueOf(value.toString()); } catch (NumberFormatException e2) { return null; } } return null; }
  }
  public Byte getAsByte(String key) {
    Object value = values.get(key);
    try { return value != null ? ((Number) value).byteValue() : null; }
    catch (ClassCastException e) { if (value instanceof CharSequence) { try { return Byte.valueOf(value.toString()); } catch (NumberFormatException e2) { return null; } } return null; }
  }
  public Double getAsDouble(String key) {
    Object value = values.get(key);
    try { return value != null ? ((Number) value).doubleValue() : null; }
    catch (ClassCastException e) { if (value instanceof CharSequence) { try { return Double.valueOf(value.toString()); } catch (NumberFormatException e2) { return null; } } return null; }
  }
  public Float getAsFloat(String key) {
    Object value = values.get(key);
    try { return value != null ? ((Number) value).floatValue() : null; }
    catch (ClassCastException e) { if (value instanceof CharSequence) { try { return Float.valueOf(value.toString()); } catch (NumberFormatException e2) { return null; } } return null; }
  }
  public Boolean getAsBoolean(String key) {
    Object value = values.get(key);
    try { return (Boolean) value; }
    catch (ClassCastException e) {
      if (value instanceof CharSequence) return Boolean.valueOf(value.toString()) || "1".equals(value);
      else if (value instanceof Number) return ((Number) value).intValue() != 0;
      return null;
    }
  }
  public byte[] getAsByteArray(String key) { Object value = values.get(key); return value instanceof byte[] ? (byte[]) value : null; }
  public Set<Map.Entry<String, Object>> valueSet() { return values.entrySet(); }
  public Set<String> keySet() { return values.keySet(); }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String name : values.keySet()) { String value = getAsString(name); if (sb.length() > 0) sb.append(" "); sb.append(name).append("=").append(value); }
    return sb.toString();
  }
}

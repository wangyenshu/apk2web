package a2w;

import android.content.SharedPreferences;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

/** SharedPreferences kept in the page's store as JSON ({"k": [type, value]}). */
final class SharedPreferencesImpl implements SharedPreferences {
  final String name;
  final HashMap<String, Object> map = new HashMap<>();
  final ArrayList<OnSharedPreferenceChangeListener> listeners = new ArrayList<>();

  SharedPreferencesImpl(String name) {
    this.name = name;
    String json = Host.prefsLoad(name);
    if (json != null) {
      try {
        JSONObject o = new JSONObject(json);
        Iterator<String> it = o.keys();
        while (it.hasNext()) {
          String k = it.next();
          JSONArray a = o.getJSONArray(k);
          String t = a.getString(0);
          switch (t) {
            case "s": map.put(k, a.getString(1)); break;
            case "i": map.put(k, a.getInt(1)); break;
            case "l": map.put(k, a.getLong(1)); break;
            case "f": map.put(k, (float) a.getDouble(1)); break;
            case "b": map.put(k, a.getBoolean(1)); break;
            case "set": { HashSet<String> s = new HashSet<>(); JSONArray v = a.getJSONArray(1); for (int i = 0; i < v.length(); i++) s.add(v.getString(i)); map.put(k, s); break; }
          }
        }
      } catch (Exception e) { Host.log(5, "W/SharedPreferences: bad " + name + ": " + e); }
    }
  }
  void save() {
    try {
      JSONObject o = new JSONObject();
      for (Map.Entry<String, Object> e : map.entrySet()) {
        Object v = e.getValue();
        JSONArray a = new JSONArray();
        if (v instanceof String) { a.put("s"); a.put(v); }
        else if (v instanceof Integer) { a.put("i"); a.put(v); }
        else if (v instanceof Long) { a.put("l"); a.put(((Long) v).longValue()); }
        else if (v instanceof Float) { a.put("f"); a.put(((Float) v).doubleValue()); }
        else if (v instanceof Boolean) { a.put("b"); a.put(v); }
        else if (v instanceof Set) { a.put("set"); JSONArray s = new JSONArray(); for (Object x : (Set) v) s.put(x); a.put(s); }
        else continue;
        o.put(e.getKey(), a);
      }
      Host.prefsSave(name, o.toString());
    } catch (Exception e) { Host.uncaught(e); }
  }
  public synchronized Map<String, ?> getAll() { return new HashMap<>(map); }
  public synchronized String getString(String k, String d) { Object v = map.get(k); return v instanceof String ? (String) v : d; }
  @SuppressWarnings("unchecked") public synchronized Set<String> getStringSet(String k, Set<String> d) { Object v = map.get(k); return v instanceof Set ? (Set<String>) v : d; }
  public synchronized int getInt(String k, int d) { Object v = map.get(k); if (v instanceof Integer) return (Integer) v; if (v != null && !(v instanceof Number)) throw new ClassCastException(); return v instanceof Number ? ((Number) v).intValue() : d; }
  public synchronized long getLong(String k, long d) { Object v = map.get(k); return v instanceof Number ? ((Number) v).longValue() : d; }
  public synchronized float getFloat(String k, float d) { Object v = map.get(k); return v instanceof Number ? ((Number) v).floatValue() : d; }
  public synchronized boolean getBoolean(String k, boolean d) { Object v = map.get(k); return v instanceof Boolean ? (Boolean) v : d; }
  public synchronized boolean contains(String k) { return map.containsKey(k); }
  public Editor edit() { return new EditorImpl(); }
  public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener l) { listeners.add(l); }
  public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener l) { listeners.remove(l); }

  final class EditorImpl implements Editor {
    final HashMap<String, Object> mods = new HashMap<>();
    boolean clear;
    final Object REMOVE = new Object();
    public Editor putString(String k, String v) { mods.put(k, v == null ? REMOVE : v); return this; }
    public Editor putStringSet(String k, Set<String> v) { mods.put(k, v == null ? REMOVE : new HashSet<>(v)); return this; }
    public Editor putInt(String k, int v) { mods.put(k, v); return this; }
    public Editor putLong(String k, long v) { mods.put(k, v); return this; }
    public Editor putFloat(String k, float v) { mods.put(k, v); return this; }
    public Editor putBoolean(String k, boolean v) { mods.put(k, v); return this; }
    public Editor remove(String k) { mods.put(k, REMOVE); return this; }
    public Editor clear() { clear = true; return this; }
    public boolean commit() {
      ArrayList<String> changed = new ArrayList<>();
      synchronized (SharedPreferencesImpl.this) {
        if (clear) { changed.addAll(map.keySet()); map.clear(); }
        for (Map.Entry<String, Object> e : mods.entrySet()) {
          if (e.getValue() == REMOVE) map.remove(e.getKey()); else map.put(e.getKey(), e.getValue());
          changed.add(e.getKey());
        }
        save();
      }
      if (!listeners.isEmpty()) for (String k : changed) for (OnSharedPreferenceChangeListener l : new ArrayList<>(listeners)) l.onSharedPreferenceChanged(SharedPreferencesImpl.this, k);
      return true;
    }
    public void apply() { commit(); }
  }
}

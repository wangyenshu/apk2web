package android.preference;
import java.util.Set;
public interface PreferenceDataStore {
  default void putString(String key, String value) {}
  default void putStringSet(String key, Set<String> values) {}
  default void putInt(String key, int value) {}
  default void putLong(String key, long value) {}
  default void putFloat(String key, float value) {}
  default void putBoolean(String key, boolean value) {}
  default String getString(String key, String defValue) { return defValue; }
  default Set<String> getStringSet(String key, Set<String> defValues) { return defValues; }
  default int getInt(String key, int defValue) { return defValue; }
  default long getLong(String key, long defValue) { return defValue; }
  default float getFloat(String key, float defValue) { return defValue; }
  default boolean getBoolean(String key, boolean defValue) { return defValue; }
}

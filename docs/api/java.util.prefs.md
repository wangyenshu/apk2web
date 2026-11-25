# java.util.prefs

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractPreferences — declaration; API 1+ (9 +, 0 *, 0 ~, 38 -)

```
- AbstractPreferences(AbstractPreferences, String)  [API 1+]
- absolutePath(): String  [API 1+]
- addNodeChangeListener(NodeChangeListener): void  [API 1+]
- addPreferenceChangeListener(PreferenceChangeListener): void  [API 1+]
- cachedChildren(): AbstractPreferences[]  [API 1+]
+ childSpi(String): AbstractPreferences  [API 1+]
- childrenNames(): String[]  [API 1+]
+ childrenNamesSpi(): String[]  [API 1+]
- clear(): void  [API 1+]
- exportNode(OutputStream): void  [API 1+]
- exportSubtree(OutputStream): void  [API 1+]
- flush(): void  [API 1+]
+ flushSpi(): void  [API 1+]
- get(String, String): String  [API 1+]
- getBoolean(String, boolean): boolean  [API 1+]
- getByteArray(String, byte[]): byte[]  [API 1+]
- getChild(String): AbstractPreferences  [API 1+]
- getDouble(String, double): double  [API 1+]
- getFloat(String, float): float  [API 1+]
- getInt(String, int): int  [API 1+]
- getLong(String, long): long  [API 1+]
+ getSpi(String): String  [API 1+]
- isRemoved(): boolean  [API 1+]
- isUserNode(): boolean  [API 1+]
- keys(): String[]  [API 1+]
+ keysSpi(): String[]  [API 1+]
- lock [field]  [API 1+]
- name(): String  [API 1+]
+ newNode [field]  [API 1+]
- node(String): Preferences  [API 1+]
- nodeExists(String): boolean  [API 1+]
- parent(): Preferences  [API 1+]
- put(String, String): void  [API 1+]
- putBoolean(String, boolean): void  [API 1+]
- putByteArray(String, byte[]): void  [API 1+]
- putDouble(String, double): void  [API 1+]
- putFloat(String, float): void  [API 1+]
- putInt(String, int): void  [API 1+]
- putLong(String, long): void  [API 1+]
+ putSpi(String, String): void  [API 1+]
- remove(String): void  [API 1+]
- removeNode(): void  [API 1+]
- removeNodeChangeListener(NodeChangeListener): void  [API 1+]
+ removeNodeSpi(): void  [API 1+]
- removePreferenceChangeListener(PreferenceChangeListener): void  [API 1+]
+ removeSpi(String): void  [API 1+]
- sync(): void  [API 1+]
+ syncSpi(): void  [API 1+]
- toString(): String  [API 1+]
```

## BackingStoreException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BackingStoreException(String)  [API 1+]
+ BackingStoreException(Throwable)  [API 1+]
```

## InvalidPreferencesFormatException — declaration; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ InvalidPreferencesFormatException(String)  [API 1+]
+ InvalidPreferencesFormatException(String, Throwable)  [API 1+]
+ InvalidPreferencesFormatException(Throwable)  [API 1+]
```

## NodeChangeEvent — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- NodeChangeEvent(Preferences, Preferences)  [API 1+]
- getChild(): Preferences  [API 1+]
- getParent(): Preferences  [API 1+]
```

## NodeChangeListener — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ childAdded(NodeChangeEvent): void  [API 1+]
+ childRemoved(NodeChangeEvent): void  [API 1+]
```

## PreferenceChangeEvent — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- PreferenceChangeEvent(Preferences, String, String)  [API 1+]
- getKey(): String  [API 1+]
- getNewValue(): String  [API 1+]
- getNode(): Preferences  [API 1+]
```

## PreferenceChangeListener — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ preferenceChange(PreferenceChangeEvent): void  [API 1+]
```

## Preferences — declaration; API 1+ (34 +, 0 *, 0 ~, 6 -)

```
- Preferences()  [API 1+]
+ absolutePath(): String  [API 1+]
+ addNodeChangeListener(NodeChangeListener): void  [API 1+]
+ addPreferenceChangeListener(PreferenceChangeListener): void  [API 1+]
+ childrenNames(): String[]  [API 1+]
+ clear(): void  [API 1+]
+ exportNode(OutputStream): void  [API 1+]
+ exportSubtree(OutputStream): void  [API 1+]
+ flush(): void  [API 1+]
+ get(String, String): String  [API 1+]
+ getBoolean(String, boolean): boolean  [API 1+]
+ getByteArray(String, byte[]): byte[]  [API 1+]
+ getDouble(String, double): double  [API 1+]
+ getFloat(String, float): float  [API 1+]
+ getInt(String, int): int  [API 1+]
+ getLong(String, long): long  [API 1+]
- importPreferences(InputStream): void [static]  [API 1+]
+ isUserNode(): boolean  [API 1+]
+ keys(): String[]  [API 1+]
+ name(): String  [API 1+]
+ node(String): Preferences  [API 1+]
+ nodeExists(String): boolean  [API 1+]
+ parent(): Preferences  [API 1+]
+ put(String, String): void  [API 1+]
+ putBoolean(String, boolean): void  [API 1+]
+ putByteArray(String, byte[]): void  [API 1+]
+ putDouble(String, double): void  [API 1+]
+ putFloat(String, float): void  [API 1+]
+ putInt(String, int): void  [API 1+]
+ putLong(String, long): void  [API 1+]
+ remove(String): void  [API 1+]
+ removeNode(): void  [API 1+]
+ removeNodeChangeListener(NodeChangeListener): void  [API 1+]
+ removePreferenceChangeListener(PreferenceChangeListener): void  [API 1+]
+ sync(): void  [API 1+]
- systemNodeForPackage(Class): Preferences [static]  [API 1+]
- systemRoot(): Preferences [static]  [API 1+]
+ toString(): String  [API 1+]
- userNodeForPackage(Class): Preferences [static]  [API 1+]
- userRoot(): Preferences [static]  [API 1+]
```

## PreferencesFactory — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ systemRoot(): Preferences  [API 1+]
+ userRoot(): Preferences  [API 1+]
```


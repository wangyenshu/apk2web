package dalvik.system;

/** dalvik.system.DexFile: names the APK; classes come from the page. */
public final class DexFile {
  private final String name;
  public DexFile(java.io.File file) throws java.io.IOException { name = file.getPath(); }
  public DexFile(String fileName) throws java.io.IOException { name = fileName; }
  public static DexFile loadDex(String sourcePathName, String outputPathName, int flags) throws java.io.IOException { return new DexFile(sourcePathName); }
  public String getName() { return name; }
  public void close() {}
  public Class<?> loadClass(String name, ClassLoader loader) { try { return a2w.Reflection.forName(name.replace('/', '.')); } catch (ClassNotFoundException e) { return null; } }
  public java.util.Enumeration<String> entries() { return java.util.Collections.enumeration(java.util.Arrays.asList(a2w.Reflection.classNames())); }
  public static boolean isDexOptNeeded(String fileName) { return false; }
}

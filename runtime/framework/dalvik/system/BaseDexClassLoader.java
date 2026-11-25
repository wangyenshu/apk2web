package dalvik.system;

/**
 * dalvik.system.BaseDexClassLoader: the app's classes are all in the page, so a dex class loader finds those (the
 * classes the app can name); new dex code cannot be loaded at run time.
 */
public class BaseDexClassLoader extends ClassLoader {
  private final String path;
  public BaseDexClassLoader(String dexPath, java.io.File optimizedDirectory, String librarySearchPath, ClassLoader parent) { super(parent); path = dexPath; }
  public BaseDexClassLoader(String dexPath, String librarySearchPath, ClassLoader parent) { this(dexPath, null, librarySearchPath, parent); }
  @Override protected Class<?> findClass(String name) throws ClassNotFoundException { return a2w.Reflection.forName(name); }
  @Override public Class<?> loadClass(String name) throws ClassNotFoundException { return a2w.Reflection.forName(name); }
  public String findLibrary(String name) { return "/data/app-lib/" + a2w.Res.pkg() + "/lib" + name + ".so"; }
  public void addDexPath(String dexPath) {}
  @Override public String toString() { return getClass().getName() + "[DexPathList[[zip file \"" + path + "\"]]]"; }
}

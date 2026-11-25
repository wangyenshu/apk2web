package dalvik.system;

public class DexClassLoader extends BaseDexClassLoader {
  public DexClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
    super(dexPath, optimizedDirectory == null ? null : new java.io.File(optimizedDirectory), librarySearchPath, parent);
    android.util.Log.w("apk2web", "DexClassLoader(" + dexPath + "): code loaded at run time is not available; the app's own classes are");
  }
}

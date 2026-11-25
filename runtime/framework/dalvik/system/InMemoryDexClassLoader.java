package dalvik.system;

public final class InMemoryDexClassLoader extends BaseDexClassLoader {
  public InMemoryDexClassLoader(java.nio.ByteBuffer dexBuffer, ClassLoader parent) { super("memory", null, null, parent); }
  public InMemoryDexClassLoader(java.nio.ByteBuffer[] dexBuffers, ClassLoader parent) { super("memory", null, null, parent); }
}

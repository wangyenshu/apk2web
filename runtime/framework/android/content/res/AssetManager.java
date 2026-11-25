package android.content.res;

import a2w.Host;
import a2w.jdk.FileDescriptor;
import a2w.jdk.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/** Reads assets/ and res/ straight from the APK the page loaded. */
public final class AssetManager {
  public static final int ACCESS_UNKNOWN = 0, ACCESS_RANDOM = 1, ACCESS_STREAMING = 2, ACCESS_BUFFER = 3;
  static final AssetManager INSTANCE = new AssetManager();
  AssetManager() {}

  public byte[] readAsset(String name) { return Host.apkRead("assets/" + strip(name)); }
  public byte[] readEntry(String name) { return Host.apkRead(name); }
  static String strip(String n) { while (n.startsWith("/")) n = n.substring(1); if (n.startsWith("./")) n = n.substring(2); return n; }

  public InputStream open(String name) throws IOException { return open(name, ACCESS_STREAMING); }
  public InputStream open(String name, int mode) throws IOException {
    byte[] d = readAsset(name);
    if (d == null) throw new FileNotFoundException(name);
    return new FileInputStream(d, 0, d.length);
  }
  public AssetFileDescriptor openFd(String name) throws IOException {
    String e = "assets/" + strip(name);
    return fd(e, name);
  }
  public AssetFileDescriptor openNonAssetFd(String name) throws IOException { return openNonAssetFd(0, name); }
  public AssetFileDescriptor openNonAssetFd(int cookie, String name) throws IOException { return fd(strip(name), name); }
  private AssetFileDescriptor fd(String entry, String name) throws IOException {
    byte[] d = Host.apkRead(entry);
    if (d == null) throw new FileNotFoundException(name);
    FileDescriptor f = new FileDescriptor();
    f.apkEntry = entry;
    f.data = d;
    return new AssetFileDescriptor(f, 0, d.length);
  }
  public InputStream openNonAsset(String name) throws IOException { return openNonAsset(0, name, ACCESS_STREAMING); }
  public InputStream openNonAsset(int cookie, String name, int mode) throws IOException {
    byte[] d = Host.apkRead(strip(name));
    if (d == null) throw new FileNotFoundException(name);
    return new FileInputStream(d, 0, d.length);
  }
  public String[] list(String path) throws IOException {
    String p = strip(path);
    String s = Host.apkList("assets/" + (p.isEmpty() ? "" : p + "/"));
    return s.isEmpty() ? new String[0] : s.split("\n");
  }
  public final String[] getLocales() { return new String[] {"en"}; }
  public void close() {}
}

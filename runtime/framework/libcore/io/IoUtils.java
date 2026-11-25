package libcore.io;

/** The libcore helpers AOSP's framework code uses (not public API). */
public final class IoUtils {
  private IoUtils() {}
  public static void closeQuietly(AutoCloseable c) { if (c != null) try { c.close(); } catch (RuntimeException e) { throw e; } catch (Exception ignored) {} }
  public static void closeQuietly(a2w.jdk.FileDescriptor fd) { if (fd != null) try { android.system.Os.close(fd); } catch (android.system.ErrnoException ignored) {} }
  public static byte[] readFileAsByteArray(String path) throws java.io.IOException { return java.nio.file.Files.readAllBytes(new java.io.File(path).toPath()); }
  public static String readFileAsString(String path) throws java.io.IOException { return new String(readFileAsByteArray(path), java.nio.charset.StandardCharsets.UTF_8); }
}

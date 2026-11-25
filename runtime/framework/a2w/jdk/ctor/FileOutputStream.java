package a2w.jdk.ctor;

import java.io.File;
import java.io.FileNotFoundException;

/** new FileOutputStream(FileDescriptor): the descriptor names a stored file (app code sees java.io.FileDescriptor as a2w.jdk.FileDescriptor). */
public class FileOutputStream extends java.io.FileOutputStream {
  public FileOutputStream(a2w.jdk.FileDescriptor fd) throws FileNotFoundException { super(target(fd), true); }
  private static File target(a2w.jdk.FileDescriptor fd) {
    if (fd != null && fd.path != null) return new File(fd.path);
    File f = new File("/data/local/tmp/fd-" + System.identityHashCode(fd));
    f.getParentFile().mkdirs();
    return f;
  }
}

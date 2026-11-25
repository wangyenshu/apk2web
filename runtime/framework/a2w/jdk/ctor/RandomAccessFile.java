package a2w.jdk.ctor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/** new RandomAccessFile(file, mode): a mode with "w" creates a missing file, as on Android (TeaVM's needs it to exist). */
public class RandomAccessFile extends java.io.RandomAccessFile {
  public RandomAccessFile(File file, String mode) throws FileNotFoundException { super(create(file, mode), mode); }
  public RandomAccessFile(String name, String mode) throws FileNotFoundException { super(create(new File(name), mode), mode); }
  public static File create(File f, String mode) throws FileNotFoundException {
    if (mode.indexOf('w') >= 0 && !f.exists()) {
      File parent = f.getAbsoluteFile().getParentFile();
      if (parent != null && !parent.isDirectory()) throw new FileNotFoundException(f + ": open failed: ENOENT (No such file or directory)");
      try { new java.io.FileOutputStream(f, true).close(); } catch (IOException e) { throw new FileNotFoundException(f + ": open failed: EACCES (Permission denied)"); }
    }
    if (f.isDirectory()) throw new FileNotFoundException(f + ": open failed: EISDIR (Is a directory)");
    if (!f.exists()) throw new FileNotFoundException(f + ": open failed: ENOENT (No such file or directory)");
    return f;
  }
}

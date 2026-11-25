package a2w.jdk.ctor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

/** the java.io.PrintStream constructors that open a file by name */
public class PrintStream extends java.io.PrintStream {
  public PrintStream(String fileName) throws FileNotFoundException { super(new FileOutputStream(fileName), false); }
  public PrintStream(File file) throws FileNotFoundException { super(new FileOutputStream(file), false); }
  public PrintStream(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException { super(new FileOutputStream(fileName), false, csn); }
  public PrintStream(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException { super(new FileOutputStream(file), false, csn); }
}

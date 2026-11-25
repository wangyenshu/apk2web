package a2w.jdk.ctor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/** the java.io.PrintWriter constructors that open a file by name (the web JDK only wraps streams and writers) */
public class PrintWriter extends java.io.PrintWriter {
  public PrintWriter(String fileName) throws FileNotFoundException { super(open(new File(fileName), null), false); }
  public PrintWriter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException { super(open(new File(fileName), csn), false); }
  public PrintWriter(File file) throws FileNotFoundException { super(open(file, null), false); }
  public PrintWriter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException { super(open(file, csn), false); }
  static Writer open(File f, String csn) throws FileNotFoundException {
    FileOutputStream out = new FileOutputStream(f);
    if (csn == null) return new OutputStreamWriter(out);
    try { return new OutputStreamWriter(out, csn); }
    catch (UnsupportedEncodingException e) { return new OutputStreamWriter(out); }
  }
}

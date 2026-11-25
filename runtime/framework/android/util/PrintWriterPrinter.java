package android.util;

public class PrintWriterPrinter implements Printer {
  private final java.io.PrintWriter mPW;
  public PrintWriterPrinter(java.io.PrintWriter pw) { mPW = pw; }
  @Override public void println(String x) { mPW.println(x); }
}

// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.print;

public final class PrintManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public PrintManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public java.util.List<android.print.PrintJob> getPrintJobs() { a2w.Stubs.hit("android.print.PrintManager.getPrintJobs"); return null; }
  @a2w.NotImplemented public boolean isPrintServiceEnabled(android.content.ComponentName service) { a2w.Stubs.hit("android.print.PrintManager.isPrintServiceEnabled"); return false; }
  @a2w.NotImplemented public android.print.PrintJob print(java.lang.String printJobName, android.print.PrintDocumentAdapter documentAdapter, android.print.PrintAttributes attributes) { a2w.Stubs.hit("android.print.PrintManager.print"); return null; }
}

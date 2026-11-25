package a2w.xml.transform.stream;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

public class StreamResult implements a2w.xml.transform.Result {
  public static final String FEATURE = "http://javax.xml.transform.stream.StreamResult/feature";
  private String systemId;
  private OutputStream outputStream;
  private Writer writer;
  public StreamResult() {}
  public StreamResult(OutputStream out) { outputStream = out; }
  public StreamResult(Writer w) { writer = w; }
  public StreamResult(String systemId) { this.systemId = systemId; }
  public StreamResult(File f) { systemId = "file:" + f.getAbsolutePath(); }
  public void setOutputStream(OutputStream out) { outputStream = out; }
  public OutputStream getOutputStream() { return outputStream; }
  public void setWriter(Writer w) { writer = w; }
  public Writer getWriter() { return writer; }
  @Override public void setSystemId(String s) { systemId = s; }
  public void setSystemId(File f) { systemId = "file:" + f.getAbsolutePath(); }
  @Override public String getSystemId() { return systemId; }
}

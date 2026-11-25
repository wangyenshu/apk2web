package a2w.xml.transform.stream;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

public class StreamSource implements a2w.xml.transform.Source {
  public static final String FEATURE = "http://javax.xml.transform.stream.StreamSource/feature";
  private String publicId, systemId;
  private InputStream inputStream;
  private Reader reader;
  public StreamSource() {}
  public StreamSource(InputStream in) { inputStream = in; }
  public StreamSource(InputStream in, String systemId) { inputStream = in; this.systemId = systemId; }
  public StreamSource(Reader r) { reader = r; }
  public StreamSource(Reader r, String systemId) { reader = r; this.systemId = systemId; }
  public StreamSource(String systemId) { this.systemId = systemId; }
  public StreamSource(File f) { systemId = "file:" + f.getAbsolutePath(); }
  public void setInputStream(InputStream in) { inputStream = in; }
  public InputStream getInputStream() { return inputStream; }
  public void setReader(Reader r) { reader = r; }
  public Reader getReader() { return reader; }
  public void setPublicId(String p) { publicId = p; }
  public String getPublicId() { return publicId; }
  @Override public void setSystemId(String s) { systemId = s; }
  @Override public String getSystemId() { return systemId; }
  public void setSystemId(File f) { systemId = "file:" + f.getAbsolutePath(); }
  @Override public boolean isEmpty() { return systemId == null && inputStream == null && reader == null; }
}

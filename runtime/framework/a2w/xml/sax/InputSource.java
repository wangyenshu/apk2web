package a2w.xml.sax;

import java.io.InputStream;
import java.io.Reader;

public class InputSource {
  private String publicId, systemId, encoding;
  private InputStream byteStream;
  private Reader characterStream;
  public InputSource() {}
  public InputSource(String systemId) { this.systemId = systemId; }
  public InputSource(InputStream byteStream) { this.byteStream = byteStream; }
  public InputSource(Reader characterStream) { this.characterStream = characterStream; }
  public void setPublicId(String p) { publicId = p; }
  public String getPublicId() { return publicId; }
  public void setSystemId(String s) { systemId = s; }
  public String getSystemId() { return systemId; }
  public void setByteStream(InputStream b) { byteStream = b; }
  public InputStream getByteStream() { return byteStream; }
  public void setEncoding(String e) { encoding = e; }
  public String getEncoding() { return encoding; }
  public void setCharacterStream(Reader r) { characterStream = r; }
  public Reader getCharacterStream() { return characterStream; }
}

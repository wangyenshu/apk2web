package a2w;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.teavm.interop.Async;
import org.teavm.interop.AsyncCallback;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.Int8Array;

/**
 * java.net.URL's http and https connections, over XMLHttpRequest (installed by {@link #install}). Unlike TeaVM's
 * own, a request that fails (no network, a name that does not resolve, refused by the browser's CORS policy) throws
 * IOException as on Android, instead of answering an empty body; HTTP errors throw FileNotFoundException (404, 410)
 * or IOException from getInputStream, with the body in getErrorStream. Headers the browser sets itself are skipped.
 * Requests made while native code waits for Java (a JNI call) are synchronous: the calling thread cannot suspend there.
 */
public class XhrConnection extends HttpURLConnection {
  public static void install() {
    URLStreamHandler h = new URLStreamHandler() {
      @Override protected java.net.URLConnection openConnection(URL u) {
        return "https".equals(u.getProtocol()) ? new a2w.jdk.HttpsURLConnection(u) : new XhrConnection(u);
      }
    };
    URL.setURLStreamHandlerFactory(p -> "http".equals(p) || "https".equals(p) ? h : null);
  }

  @JSFunctor interface Done extends JSObject { void done(int status, String statusText, String headers, Int8Array body); }
  @JSBody(params = {"method", "url", "headers", "body", "sync", "done"}, script =
      "var x = new XMLHttpRequest(), finished = false, threw = false;"
      + "function end() { if (finished) return; finished = true; var b = null;"
      + "  if (x.status) { if (sync) { var t = x.responseText || ''; b = new Int8Array(t.length); for (var i = 0; i < t.length; i++) b[i] = t.charCodeAt(i); }"
      + "  else b = new Int8Array(x.response || new ArrayBuffer(0)); }"
      + "  done(x.status | 0, x.statusText || '', x.getAllResponseHeaders() || '', b); }"
      + "try { x.open(method, url, !sync);"
      + "  for (var i = 0; i + 1 < headers.length; i += 2) x.setRequestHeader(headers[i], headers[i + 1]);"
      + "  if (sync) x.overrideMimeType('text/plain; charset=x-user-defined'); else { x.responseType = 'arraybuffer'; x.onloadend = end; }"
      + "  x.send(body);"
      + "} catch (e) { threw = true; }"
      + "if (sync) end(); else if (threw) setTimeout(end, 0);")
  static native void request(String method, String url, String[] headers, Int8Array body, boolean sync, Done done);
  @JSBody(script = "return typeof HW !== 'undefined' && !!HW.inNative && HW.inNative();")
  static native boolean inNative();

  private ByteArrayOutputStream out;
  private InputStream in, err;
  private boolean done, failed;
  private final List<String> keys = new ArrayList<>(), values = new ArrayList<>();
  private final Map<String, List<String>> fields = new LinkedHashMap<>();
  private final Map<String, String> byLower = new HashMap<>();

  public XhrConnection(URL u) { super(u); }

  /** request headers the browser refuses to let pages set (it logs an error for each) */
  static boolean forbidden(String k) {
    String l = k.toLowerCase();
    return l.equals("content-length") || l.equals("host") || l.equals("connection") || l.equals("keep-alive") || l.equals("accept-encoding")
        || l.equals("accept-charset") || l.equals("cookie") || l.equals("cookie2") || l.equals("date") || l.equals("dnt") || l.equals("expect")
        || l.equals("referer") || l.equals("te") || l.equals("trailer") || l.equals("transfer-encoding") || l.equals("upgrade") || l.equals("user-agent")
        || l.equals("via") || l.startsWith("proxy-") || l.startsWith("sec-");
  }

  private List<String> headers;
  /** the request headers, taken when connecting (they cannot change afterwards) */
  @Override public void connect() {
    if (connected) return;
    headers = new ArrayList<>();
    for (Map.Entry<String, List<String>> e : getRequestProperties().entrySet())
      if (e.getKey() != null && !forbidden(e.getKey())) for (String v : e.getValue()) { headers.add(e.getKey()); headers.add(v); }
    connected = true;
  }

  private void perform() throws IOException {
    if (!done) {
      done = true;
      connect();
      List<String> h = headers;
      String m = method;
      Int8Array body = null;
      if (out != null) {
        if ("GET".equals(m)) m = "POST";   // as Android: writing a body makes a GET a POST
        byte[] b = out.toByteArray();
        body = new Int8Array(b.length);
        for (int i = 0; i < b.length; i++) body.set(i, b[i]);
      }
      Object[] r = inNative() ? send(m, h.toArray(new String[0]), body, true) : sendAsync(m, h.toArray(new String[0]), body);
      int status = (Integer) r[0];
      if (status == 0) failed = true;
      else {
        responseCode = status;
        responseMessage = (String) r[1];
        parseHeaders((String) r[2]);
        Int8Array a = (Int8Array) r[3];
        byte[] bytes = new byte[a == null ? 0 : a.getLength()];
        for (int i = 0; i < bytes.length; i++) bytes[i] = a.get(i);
        if (status >= 400) err = new ByteArrayInputStream(bytes); else in = new ByteArrayInputStream(bytes);
      }
    }
    if (failed) throw new IOException("Unable to reach " + url + " (no network, or refused by the browser's CORS policy)");
  }
  private Object[] send(String m, String[] h, Int8Array body, boolean sync) {
    Object[][] box = new Object[1][];
    request(m, url.toString(), h, body, sync, (st, text, headers, b) -> box[0] = new Object[] {st, text, headers, b});
    return box[0] != null ? box[0] : new Object[] {0, "", "", null};
  }
  @Async private native Object[] sendAsync(String m, String[] h, Int8Array body);
  private void sendAsync(String m, String[] h, Int8Array body, AsyncCallback<Object[]> cb) {
    request(m, url.toString(), h, body, false, (st, text, headers, b) -> cb.complete(new Object[] {st, text, headers, b}));
  }

  private void parseHeaders(String all) {
    for (String line : all.split("\r\n")) {
      int c = line.indexOf(':');
      if (c <= 0) continue;
      String k = line.substring(0, c).trim(), v = line.substring(c + 1).trim();
      keys.add(k); values.add(v);
      fields.computeIfAbsent(k, x -> new ArrayList<>()).add(v);
      byLower.put(k.toLowerCase(), v);
    }
  }

  @Override public InputStream getInputStream() throws IOException {
    perform();
    if (responseCode >= 400) {
      if (responseCode == 404 || responseCode == 410) throw new FileNotFoundException(url.toString());
      throw new IOException("Server returned HTTP response code: " + responseCode + " for URL: " + url);
    }
    return in;
  }
  @Override public InputStream getErrorStream() { return done && !failed ? err : null; }
  @Override public OutputStream getOutputStream() throws IOException {
    if (done) throw new IOException("the request was already sent");
    if (out == null) out = new ByteArrayOutputStream();
    return out;
  }
  @Override public int getResponseCode() throws IOException { perform(); return responseCode; }
  @Override public String getResponseMessage() throws IOException { perform(); return responseMessage; }
  private boolean tryPerform() { try { perform(); return true; } catch (IOException e) { return false; } }
  @Override public String getHeaderField(String k) { return k != null && tryPerform() ? byLower.get(k.toLowerCase()) : null; }
  @Override public String getHeaderField(int n) { return tryPerform() && n >= 0 && n < values.size() ? values.get(n) : null; }
  @Override public String getHeaderFieldKey(int n) { return tryPerform() && n >= 0 && n < keys.size() ? keys.get(n) : null; }
  @Override public Map<String, List<String>> getHeaderFields() { tryPerform(); return Collections.unmodifiableMap(fields); }
  @Override public int getContentLength() { String v = getHeaderField("content-length"); try { return v == null ? -1 : Integer.parseInt(v.trim()); } catch (NumberFormatException e) { return -1; } }
  @Override public String getContentType() { return getHeaderField("content-type"); }
  @Override public String getContentEncoding() { return getHeaderField("content-encoding"); }
  @Override public boolean usingProxy() { return false; }
  @Override public void disconnect() { connected = false; }
}

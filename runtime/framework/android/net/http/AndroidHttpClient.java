package android.net.http;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

/** android.net.http.AndroidHttpClient: the Apache client with Android's defaults (timeouts, user agent). */
public final class AndroidHttpClient implements HttpClient {
  public static long DEFAULT_SYNC_MIN_GZIP_BYTES = 256;
  private final DefaultHttpClient delegate;
  private AndroidHttpClient(HttpParams params) { delegate = new DefaultHttpClient(params); }
  public static AndroidHttpClient newInstance(String userAgent, android.content.Context context) { return newInstance(userAgent); }
  public static AndroidHttpClient newInstance(String userAgent) {
    HttpParams params = new BasicHttpParams();
    HttpConnectionParams.setStaleCheckingEnabled(params, false);
    HttpConnectionParams.setConnectionTimeout(params, 60 * 1000);
    HttpConnectionParams.setSoTimeout(params, 60 * 1000);
    HttpConnectionParams.setSocketBufferSize(params, 8192);
    if (userAgent != null) HttpProtocolParams.setUserAgent(params, userAgent);
    return new AndroidHttpClient(params);
  }
  public void close() {}
  @Override public HttpParams getParams() { return delegate.getParams(); }
  @Override public ClientConnectionManager getConnectionManager() { return delegate.getConnectionManager(); }
  @Override public HttpResponse execute(HttpUriRequest request) throws IOException { return delegate.execute(request); }
  @Override public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException { return delegate.execute(request, context); }
  @Override public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException { return delegate.execute(target, request); }
  @Override public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException { return delegate.execute(target, request, context); }
  @Override public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> h) throws IOException { return delegate.execute(request, h); }
  @Override public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> h, HttpContext context) throws IOException { return delegate.execute(request, h, context); }
  @Override public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> h) throws IOException { return delegate.execute(target, request, h); }
  @Override public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> h, HttpContext context) throws IOException { return delegate.execute(target, request, h, context); }
  public static AbstractHttpEntity getCompressedEntity(byte[] data, android.content.ContentResolver resolver) throws IOException { return new ByteArrayEntity(data); }
  public static void modifyRequestToAcceptGzipResponse(HttpRequest request) { request.addHeader("Accept-Encoding", "gzip"); }
  public static InputStream getUngzippedContent(HttpEntity entity) throws IOException {
    InputStream in = entity.getContent();
    if (in == null) return null;
    Header h = entity.getContentEncoding();
    if (h != null && h.getValue().toLowerCase(java.util.Locale.ROOT).contains("gzip")) return new java.util.zip.GZIPInputStream(in);
    return in;
  }
  public void enableCurlLogging(String name, int level) {}
  public void disableCurlLogging() {}
  public static long parseDate(String dateString) { try { return new java.text.SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", java.util.Locale.US).parse(dateString).getTime(); } catch (java.text.ParseException e) { throw new IllegalArgumentException(dateString); } }
}

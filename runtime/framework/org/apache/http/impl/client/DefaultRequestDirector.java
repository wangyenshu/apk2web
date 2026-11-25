package org.apache.http.impl.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import org.apache.http.*;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.AbortableHttpRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;

/**
 * The Apache HTTP client's request director, on the page's XMLHttpRequest (through java.net.URL, a2w.XhrConnection):
 * the client classes, entities, parameters and cookies are the library's own; requests the browser cannot make
 * (no network, no CORS headers) fail with IOException, as on a device without network.
 */
public class DefaultRequestDirector implements RequestDirector {
  protected final ClientConnectionManager connManager;
  protected final HttpRoutePlanner routePlanner;
  protected final ConnectionReuseStrategy reuseStrategy;
  protected final ConnectionKeepAliveStrategy keepAliveStrategy;
  protected final HttpRequestExecutor requestExec;
  protected final HttpProcessor httpProcessor;
  protected final HttpRequestRetryHandler retryHandler;
  protected final RedirectHandler redirectHandler;
  protected final AuthenticationHandler targetAuthHandler;
  protected final AuthenticationHandler proxyAuthHandler;
  protected final UserTokenHandler userTokenHandler;
  protected final HttpParams params;

  public DefaultRequestDirector(HttpRequestExecutor requestExec, ClientConnectionManager conman, ConnectionReuseStrategy reustrat,
      ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor, HttpRequestRetryHandler retryHandler,
      RedirectHandler redirectHandler, AuthenticationHandler targetAuthHandler, AuthenticationHandler proxyAuthHandler,
      UserTokenHandler userTokenHandler, HttpParams params) {
    this.requestExec = requestExec; this.connManager = conman; this.reuseStrategy = reustrat; this.keepAliveStrategy = kastrat;
    this.routePlanner = rouplan; this.httpProcessor = httpProcessor; this.retryHandler = retryHandler; this.redirectHandler = redirectHandler;
    this.targetAuthHandler = targetAuthHandler; this.proxyAuthHandler = proxyAuthHandler; this.userTokenHandler = userTokenHandler; this.params = params;
  }

  /** (the form httpclient's AbstractHttpClient calls) */
  public DefaultRequestDirector(org.apache.commons.logging.Log log, HttpRequestExecutor requestExec, ClientConnectionManager conman, ConnectionReuseStrategy reustrat,
      ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor, HttpRequestRetryHandler retryHandler,
      RedirectHandler redirectHandler, AuthenticationHandler targetAuthHandler, AuthenticationHandler proxyAuthHandler,
      UserTokenHandler userTokenHandler, HttpParams params) {
    this(requestExec, conman, reustrat, kastrat, rouplan, httpProcessor, retryHandler, redirectHandler, targetAuthHandler, proxyAuthHandler, userTokenHandler, params);
  }

  @Override public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws HttpException, IOException {
    String uri = request.getRequestLine().getUri();
    if (!uri.matches("^[a-zA-Z][a-zA-Z0-9+.-]*:.*")) {
      if (target == null) throw new org.apache.http.client.ClientProtocolException("URI does not specify a valid host name: " + uri);
      uri = target.toURI() + (uri.startsWith("/") ? "" : "/") + uri;
    }
    String method = request.getRequestLine().getMethod();
    HttpURLConnection c = (HttpURLConnection) new java.net.URL(uri).openConnection();
    if (request instanceof AbortableHttpRequest) { /* (aborting a request that is in flight is not supported) */ }
    c.setRequestMethod(method);
    c.setInstanceFollowRedirects(true);
    int timeout = params == null ? 0 : HttpConnectionParams.getConnectionTimeout(params);
    if (timeout > 0) c.setConnectTimeout(timeout);
    int so = params == null ? 0 : HttpConnectionParams.getSoTimeout(params);
    if (so > 0) c.setReadTimeout(so);
    for (Header h : request.getAllHeaders()) {
      String n = h.getName();
      if (n.equalsIgnoreCase("Content-Length") || n.equalsIgnoreCase("Host") || n.equalsIgnoreCase("Connection")) continue;
      c.addRequestProperty(n, h.getValue());
    }
    if (request instanceof HttpEntityEnclosingRequest) {
      HttpEntity e = ((HttpEntityEnclosingRequest) request).getEntity();
      if (e != null) {
        if (e.getContentType() != null && c.getRequestProperty("Content-Type") == null) c.setRequestProperty("Content-Type", e.getContentType().getValue());
        if (e.getContentEncoding() != null) c.setRequestProperty("Content-Encoding", e.getContentEncoding().getValue());
        c.setDoOutput(true);
        try (OutputStream out = c.getOutputStream()) { e.writeTo(out); }
      }
    }
    int code = c.getResponseCode();
    String reason = c.getResponseMessage();
    BasicHttpResponse r = new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, code, reason == null ? "" : reason));
    for (Map.Entry<String, List<String>> e : c.getHeaderFields().entrySet()) {
      if (e.getKey() == null) continue;
      for (String v : e.getValue()) r.addHeader(e.getKey(), v);
    }
    if (!method.equalsIgnoreCase("HEAD") && code != 204 && code != 304) {
      InputStream in;
      try { in = c.getInputStream(); } catch (IOException ex) { in = c.getErrorStream(); }
      ByteArrayOutputStream body = new ByteArrayOutputStream();
      if (in != null) {
        byte[] b = new byte[8192];
        for (int n; (n = in.read(b)) > 0; ) body.write(b, 0, n);
        in.close();
      }
      ByteArrayEntity entity = new ByteArrayEntity(body.toByteArray());
      String ct = c.getContentType();
      if (ct != null) entity.setContentType(ct);
      String ce = c.getContentEncoding();
      if (ce != null) entity.setContentEncoding(ce);
      r.setEntity(entity);
    }
    r.setParams(params);
    if (context != null) {
      context.setAttribute("http.request", request);
      context.setAttribute("http.response", r);
      context.setAttribute("http.target_host", target);
    }
    return r;
  }

  protected void releaseConnection() {}
}

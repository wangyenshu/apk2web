package a2w.jdk;

/**
 * javax.net.ssl.HttpsURLConnection (the linker maps it here): an https connection over XMLHttpRequest, where the
 * browser does TLS. Socket factories and hostname verifiers have nothing to configure; the linker stubs those methods.
 */
public class HttpsURLConnection extends a2w.XhrConnection {
  public HttpsURLConnection(java.net.URL u) { super(u); }
  public String getCipherSuite() { return "TLS_AES_128_GCM_SHA256"; }
}

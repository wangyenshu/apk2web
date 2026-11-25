// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.security;

public final class FileIntegrityManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public FileIntegrityManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public boolean isApkVeritySupported() { a2w.Stubs.hit("android.security.FileIntegrityManager.isApkVeritySupported"); return false; }
  @a2w.NotImplemented public boolean isAppSourceCertificateTrusted(a2w.jdk.security.cert.X509Certificate certificate) throws a2w.jdk.security.cert.CertificateEncodingException { a2w.Stubs.hit("android.security.FileIntegrityManager.isAppSourceCertificateTrusted"); return false; }
}

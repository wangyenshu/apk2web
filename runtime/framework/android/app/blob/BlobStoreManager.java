// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app.blob;

public class BlobStoreManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public BlobStoreManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void abandonSession(long sessionId) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.abandonSession"); }
  @a2w.NotImplemented public void acquireLease(android.app.blob.BlobHandle blobHandle, int descriptionResId) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.acquireLease"); }
  @a2w.NotImplemented public void acquireLease(android.app.blob.BlobHandle blobHandle, int descriptionResId, long leaseExpiryTimeMillis) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.acquireLease"); }
  @a2w.NotImplemented public void acquireLease(android.app.blob.BlobHandle blobHandle, java.lang.CharSequence description) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.acquireLease"); }
  @a2w.NotImplemented public void acquireLease(android.app.blob.BlobHandle blobHandle, java.lang.CharSequence description, long leaseExpiryTimeMillis) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.acquireLease"); }
  @a2w.NotImplemented public long createSession(android.app.blob.BlobHandle blobHandle) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.createSession"); return 0L; }
  @a2w.NotImplemented public java.util.List<android.app.blob.BlobHandle> getLeasedBlobs() throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.getLeasedBlobs"); return null; }
  @a2w.NotImplemented public long getRemainingLeaseQuotaBytes() { a2w.Stubs.hit("android.app.blob.BlobStoreManager.getRemainingLeaseQuotaBytes"); return 0L; }
  @a2w.NotImplemented public android.os.ParcelFileDescriptor openBlob(android.app.blob.BlobHandle blobHandle) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.openBlob"); return null; }
  @a2w.NotImplemented public android.app.blob.BlobStoreManager.Session openSession(long sessionId) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.openSession"); return null; }
  @a2w.NotImplemented public void releaseLease(android.app.blob.BlobHandle blobHandle) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.releaseLease"); }

    public static class Session implements java.io.Closeable {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public Session(a2w.Hidden hidden) { }
    @a2w.NotImplemented public void abandon() throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.abandon"); }
    @a2w.NotImplemented public void allowPackageAccess(java.lang.String packageName, byte[] certificate) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.allowPackageAccess"); }
    @a2w.NotImplemented public void allowPublicAccess() throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.allowPublicAccess"); }
    @a2w.NotImplemented public void allowSameSignatureAccess() throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.allowSameSignatureAccess"); }
    @a2w.NotImplemented public void close() throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.close"); }
    @a2w.NotImplemented public void commit(java.util.concurrent.Executor executor, java.util.function.Consumer<java.lang.Integer> resultCallback) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.commit"); }
    @a2w.NotImplemented public long getSize() throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.getSize"); return 0L; }
    @a2w.NotImplemented public boolean isPackageAccessAllowed(java.lang.String packageName, byte[] certificate) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.isPackageAccessAllowed"); return false; }
    @a2w.NotImplemented public boolean isPublicAccessAllowed() throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.isPublicAccessAllowed"); return false; }
    @a2w.NotImplemented public boolean isSameSignatureAccessAllowed() throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.isSameSignatureAccessAllowed"); return false; }
    @a2w.NotImplemented public android.os.ParcelFileDescriptor openRead() throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.openRead"); return null; }
    @a2w.NotImplemented public android.os.ParcelFileDescriptor openWrite(long offsetBytes, long lengthBytes) throws java.io.IOException { a2w.Stubs.hit("android.app.blob.BlobStoreManager.Session.openWrite"); return null; }
  }
}

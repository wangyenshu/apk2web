// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.provider;

public final class E2eeContactKeysManager {
  public static final int VERIFICATION_STATE_UNVERIFIED = 0;
  public static final int VERIFICATION_STATE_VERIFICATION_FAILED = 1;
  public static final int VERIFICATION_STATE_VERIFIED = 2;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public E2eeContactKeysManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public java.util.List<android.provider.E2eeContactKeysManager.E2eeContactKey> getAllE2eeContactKeys(java.lang.String lookupKey) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.getAllE2eeContactKeys"); return null; }
  @a2w.NotImplemented public java.util.List<android.provider.E2eeContactKeysManager.E2eeSelfKey> getAllE2eeSelfKeys() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.getAllE2eeSelfKeys"); return null; }
  @a2w.NotImplemented public android.provider.E2eeContactKeysManager.E2eeContactKey getE2eeContactKey(java.lang.String lookupKey, java.lang.String deviceId, java.lang.String accountId) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.getE2eeContactKey"); return null; }
  @a2w.NotImplemented public android.provider.E2eeContactKeysManager.E2eeSelfKey getE2eeSelfKey(java.lang.String deviceId, java.lang.String accountId) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.getE2eeSelfKey"); return null; }
  @a2w.NotImplemented public static int getMaxKeySizeBytes() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.getMaxKeySizeBytes"); return 0; }
  @a2w.NotImplemented public java.util.List<android.provider.E2eeContactKeysManager.E2eeContactKey> getOwnerE2eeContactKeys(java.lang.String lookupKey) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.getOwnerE2eeContactKeys"); return null; }
  @a2w.NotImplemented public java.util.List<android.provider.E2eeContactKeysManager.E2eeSelfKey> getOwnerE2eeSelfKeys() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.getOwnerE2eeSelfKeys"); return null; }
  @a2w.NotImplemented public boolean removeE2eeContactKey(java.lang.String lookupKey, java.lang.String deviceId, java.lang.String accountId) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.removeE2eeContactKey"); return false; }
  @a2w.NotImplemented public boolean removeE2eeSelfKey(java.lang.String deviceId, java.lang.String accountId) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.removeE2eeSelfKey"); return false; }
  @a2w.NotImplemented public boolean updateE2eeContactKeyLocalVerificationState(java.lang.String lookupKey, java.lang.String deviceId, java.lang.String accountId, int localVerificationState) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.updateE2eeContactKeyLocalVerificationState"); return false; }
  @a2w.NotImplemented public boolean updateE2eeContactKeyRemoteVerificationState(java.lang.String lookupKey, java.lang.String deviceId, java.lang.String accountId, int remoteVerificationState) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.updateE2eeContactKeyRemoteVerificationState"); return false; }
  @a2w.NotImplemented public boolean updateE2eeSelfKeyRemoteVerificationState(java.lang.String deviceId, java.lang.String accountId, int remoteVerificationState) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.updateE2eeSelfKeyRemoteVerificationState"); return false; }
  @a2w.NotImplemented public void updateOrInsertE2eeContactKey(java.lang.String lookupKey, java.lang.String deviceId, java.lang.String accountId, byte[] keyValue) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.updateOrInsertE2eeContactKey"); }
  @a2w.NotImplemented public boolean updateOrInsertE2eeSelfKey(java.lang.String deviceId, java.lang.String accountId, byte[] keyValue) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.updateOrInsertE2eeSelfKey"); return false; }

    public static final class E2eeContactKey implements android.os.Parcelable {
    @a2w.NotImplemented public static final android.os.Parcelable.Creator<android.provider.E2eeContactKeysManager.E2eeContactKey> CREATOR = null;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public E2eeContactKey(a2w.Hidden hidden) { }
    @a2w.NotImplemented public int describeContents() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.describeContents"); return 0; }
    @a2w.NotImplemented public boolean equals(java.lang.Object obj) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.equals"); return super.equals(obj); }
    @a2w.NotImplemented public java.lang.String getAccountId() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getAccountId"); return null; }
    @a2w.NotImplemented public java.lang.String getDeviceId() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getDeviceId"); return null; }
    @a2w.NotImplemented public java.lang.String getDisplayName() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getDisplayName"); return null; }
    @a2w.NotImplemented public java.lang.String getEmailAddress() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getEmailAddress"); return null; }
    @a2w.NotImplemented public byte[] getKeyValue() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getKeyValue"); return null; }
    @a2w.NotImplemented public int getLocalVerificationState() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getLocalVerificationState"); return 0; }
    @a2w.NotImplemented public java.lang.String getOwnerPackageName() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getOwnerPackageName"); return null; }
    @a2w.NotImplemented public java.lang.String getPhoneNumber() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getPhoneNumber"); return null; }
    @a2w.NotImplemented public int getRemoteVerificationState() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getRemoteVerificationState"); return 0; }
    @a2w.NotImplemented public long getTimeUpdated() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.getTimeUpdated"); return 0L; }
    @a2w.NotImplemented public int hashCode() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.hashCode"); return super.hashCode(); }
    @a2w.NotImplemented public void writeToParcel(android.os.Parcel dest, int flags) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeContactKey.writeToParcel"); }
  }

    public static final class E2eeSelfKey implements android.os.Parcelable {
    @a2w.NotImplemented public static final android.os.Parcelable.Creator<android.provider.E2eeContactKeysManager.E2eeSelfKey> CREATOR = null;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public E2eeSelfKey(a2w.Hidden hidden) { }
    @a2w.NotImplemented public int describeContents() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.describeContents"); return 0; }
    @a2w.NotImplemented public boolean equals(java.lang.Object obj) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.equals"); return super.equals(obj); }
    @a2w.NotImplemented public java.lang.String getAccountId() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.getAccountId"); return null; }
    @a2w.NotImplemented public java.lang.String getDeviceId() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.getDeviceId"); return null; }
    @a2w.NotImplemented public byte[] getKeyValue() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.getKeyValue"); return null; }
    @a2w.NotImplemented public java.lang.String getOwnerPackageName() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.getOwnerPackageName"); return null; }
    @a2w.NotImplemented public int getRemoteVerificationState() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.getRemoteVerificationState"); return 0; }
    @a2w.NotImplemented public long getTimeUpdated() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.getTimeUpdated"); return 0L; }
    @a2w.NotImplemented public int hashCode() { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.hashCode"); return super.hashCode(); }
    @a2w.NotImplemented public void writeToParcel(android.os.Parcel dest, int flags) { a2w.Stubs.hit("android.provider.E2eeContactKeysManager.E2eeSelfKey.writeToParcel"); }
  }
}

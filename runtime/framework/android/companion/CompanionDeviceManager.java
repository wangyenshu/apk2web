// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.companion;

public final class CompanionDeviceManager {
  public static final java.lang.String EXTRA_ASSOCIATION = "android.companion.extra.ASSOCIATION";
  public static final java.lang.String EXTRA_DEVICE = "android.companion.extra.DEVICE";
  public static final int FLAG_CALL_METADATA = 1;
  public static final int RESULT_CANCELED = 0;
  public static final int RESULT_DISCOVERY_TIMEOUT = 2;
  public static final int RESULT_INTERNAL_ERROR = 3;
  public static final int RESULT_OK = -1;
  public static final int RESULT_USER_REJECTED = 1;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public CompanionDeviceManager(a2w.Hidden hidden) { }
  @a2w.Absent public void associate(android.companion.AssociationRequest request, android.companion.CompanionDeviceManager.Callback callback, android.os.Handler handler) {  }
  @a2w.Absent public void associate(android.companion.AssociationRequest request, java.util.concurrent.Executor executor, android.companion.CompanionDeviceManager.Callback callback) {  }
  @a2w.Absent public void attachSystemDataTransport(int associationId, java.io.InputStream in, java.io.OutputStream out) throws android.companion.DeviceNotAssociatedException {  }
  @a2w.Absent public android.content.IntentSender buildAssociationCancellationIntent() { return null; }
  @a2w.Absent public android.content.IntentSender buildPermissionTransferUserConsentIntent(int associationId) throws android.companion.DeviceNotAssociatedException { return null; }
  @a2w.Absent public void detachSystemDataTransport(int associationId) throws android.companion.DeviceNotAssociatedException {  }
  @a2w.Absent public void disableSystemDataSyncForTypes(int associationId, int flags) {  }
  @a2w.Absent public void disassociate(int associationId) {  }
  @a2w.Absent public void disassociate(java.lang.String deviceMacAddress) {  }
  @a2w.Absent public void enableSystemDataSyncForTypes(int associationId, int flags) {  }
  @a2w.Absent public java.util.List<java.lang.String> getAssociations() { return java.util.Collections.emptyList(); }
  @a2w.Absent public java.util.List<android.companion.AssociationInfo> getMyAssociations() { return java.util.Collections.emptyList(); }
  @a2w.Absent public boolean hasNotificationAccess(android.content.ComponentName component) { return false; }
  @a2w.Absent public boolean isPermissionTransferUserConsented(int associationId) { return false; }
  @a2w.Absent public void requestNotificationAccess(android.content.ComponentName component) {  }
  @a2w.Absent public void startObservingDevicePresence(java.lang.String deviceAddress) throws android.companion.DeviceNotAssociatedException {  }
  @a2w.Absent public void startSystemDataTransfer(int associationId, java.util.concurrent.Executor executor, android.os.OutcomeReceiver<java.lang.Void, android.companion.CompanionException> result) throws android.companion.DeviceNotAssociatedException {  }
  @a2w.Absent public void stopObservingDevicePresence(java.lang.String deviceAddress) throws android.companion.DeviceNotAssociatedException {  }

    public static abstract class Callback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public Callback(a2w.Hidden hidden) { }
    public Callback() { this((a2w.Hidden) null); }
    public void onAssociationCreated(android.companion.AssociationInfo associationInfo) { }
    public void onAssociationPending(android.content.IntentSender intentSender) { }
    public void onDeviceFound(android.content.IntentSender intentSender) { }
    public abstract void onFailure(java.lang.CharSequence p0);
  }
}

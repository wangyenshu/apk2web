// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.credentials;

public final class CredentialManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public CredentialManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void clearCredentialState(android.credentials.ClearCredentialStateRequest request, android.os.CancellationSignal cancellationSignal, java.util.concurrent.Executor executor, android.os.OutcomeReceiver<java.lang.Void, android.credentials.ClearCredentialStateException> callback) { a2w.Stubs.hit("android.credentials.CredentialManager.clearCredentialState"); }
  @a2w.NotImplemented public void createCredential(android.content.Context context, android.credentials.CreateCredentialRequest request, android.os.CancellationSignal cancellationSignal, java.util.concurrent.Executor executor, android.os.OutcomeReceiver<android.credentials.CreateCredentialResponse, android.credentials.CreateCredentialException> callback) { a2w.Stubs.hit("android.credentials.CredentialManager.createCredential"); }
  @a2w.NotImplemented public void getCredential(android.content.Context context, android.credentials.GetCredentialRequest request, android.os.CancellationSignal cancellationSignal, java.util.concurrent.Executor executor, android.os.OutcomeReceiver<android.credentials.GetCredentialResponse, android.credentials.GetCredentialException> callback) { a2w.Stubs.hit("android.credentials.CredentialManager.getCredential"); }
  @a2w.NotImplemented public void getCredential(android.content.Context context, android.credentials.PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle, android.os.CancellationSignal cancellationSignal, java.util.concurrent.Executor executor, android.os.OutcomeReceiver<android.credentials.GetCredentialResponse, android.credentials.GetCredentialException> callback) { a2w.Stubs.hit("android.credentials.CredentialManager.getCredential"); }
  @a2w.NotImplemented public boolean isEnabledCredentialProviderService(android.content.ComponentName componentName) { a2w.Stubs.hit("android.credentials.CredentialManager.isEnabledCredentialProviderService"); return false; }
  @a2w.NotImplemented public void prepareGetCredential(android.credentials.GetCredentialRequest request, android.os.CancellationSignal cancellationSignal, java.util.concurrent.Executor executor, android.os.OutcomeReceiver<android.credentials.PrepareGetCredentialResponse, android.credentials.GetCredentialException> callback) { a2w.Stubs.hit("android.credentials.CredentialManager.prepareGetCredential"); }
  @a2w.NotImplemented public void registerCredentialDescription(android.credentials.RegisterCredentialDescriptionRequest request) { a2w.Stubs.hit("android.credentials.CredentialManager.registerCredentialDescription"); }
  @a2w.NotImplemented public void unregisterCredentialDescription(android.credentials.UnregisterCredentialDescriptionRequest request) { a2w.Stubs.hit("android.credentials.CredentialManager.unregisterCredentialDescription"); }
}

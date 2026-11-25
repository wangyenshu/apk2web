// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.view.translation;

public final class TranslationManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public TranslationManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void addOnDeviceTranslationCapabilityUpdateListener(java.util.concurrent.Executor executor, java.util.function.Consumer<android.view.translation.TranslationCapability> capabilityListener) { a2w.Stubs.hit("android.view.translation.TranslationManager.addOnDeviceTranslationCapabilityUpdateListener"); }
  @a2w.NotImplemented public void createOnDeviceTranslator(android.view.translation.TranslationContext translationContext, java.util.concurrent.Executor executor, java.util.function.Consumer<android.view.translation.Translator> callback) { a2w.Stubs.hit("android.view.translation.TranslationManager.createOnDeviceTranslator"); }
  @a2w.NotImplemented public java.util.Set<android.view.translation.TranslationCapability> getOnDeviceTranslationCapabilities(int sourceFormat, int targetFormat) { a2w.Stubs.hit("android.view.translation.TranslationManager.getOnDeviceTranslationCapabilities"); return null; }
  @a2w.NotImplemented public android.app.PendingIntent getOnDeviceTranslationSettingsActivityIntent() { a2w.Stubs.hit("android.view.translation.TranslationManager.getOnDeviceTranslationSettingsActivityIntent"); return null; }
  @a2w.NotImplemented public void removeOnDeviceTranslationCapabilityUpdateListener(java.util.function.Consumer<android.view.translation.TranslationCapability> capabilityListener) { a2w.Stubs.hit("android.view.translation.TranslationManager.removeOnDeviceTranslationCapabilityUpdateListener"); }
}

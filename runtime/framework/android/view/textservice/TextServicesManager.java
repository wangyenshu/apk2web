// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.view.textservice;

public final class TextServicesManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public TextServicesManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.view.textservice.SpellCheckerInfo getCurrentSpellCheckerInfo() { a2w.Stubs.hit("android.view.textservice.TextServicesManager.getCurrentSpellCheckerInfo"); return null; }
  @a2w.NotImplemented public java.util.List<android.view.textservice.SpellCheckerInfo> getEnabledSpellCheckerInfos() { a2w.Stubs.hit("android.view.textservice.TextServicesManager.getEnabledSpellCheckerInfos"); return null; }
  @a2w.NotImplemented public boolean isSpellCheckerEnabled() { a2w.Stubs.hit("android.view.textservice.TextServicesManager.isSpellCheckerEnabled"); return false; }
  @a2w.NotImplemented public android.view.textservice.SpellCheckerSession newSpellCheckerSession(android.os.Bundle bundle, java.util.Locale locale, android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener listener, boolean referToSpellCheckerLanguageSettings) { a2w.Stubs.hit("android.view.textservice.TextServicesManager.newSpellCheckerSession"); return null; }
  @a2w.NotImplemented public android.view.textservice.SpellCheckerSession newSpellCheckerSession(android.view.textservice.SpellCheckerSession.SpellCheckerSessionParams params, java.util.concurrent.Executor executor, android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener listener) { a2w.Stubs.hit("android.view.textservice.TextServicesManager.newSpellCheckerSession"); return null; }
}

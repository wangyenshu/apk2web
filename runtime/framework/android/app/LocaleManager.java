// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app;

public class LocaleManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public LocaleManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.os.LocaleList getApplicationLocales() { a2w.Stubs.hit("android.app.LocaleManager.getApplicationLocales"); return null; }
  @a2w.NotImplemented public android.os.LocaleList getApplicationLocales(java.lang.String appPackageName) { a2w.Stubs.hit("android.app.LocaleManager.getApplicationLocales"); return null; }
  @a2w.NotImplemented public android.app.LocaleConfig getOverrideLocaleConfig() { a2w.Stubs.hit("android.app.LocaleManager.getOverrideLocaleConfig"); return null; }
  @a2w.NotImplemented public android.os.LocaleList getSystemLocales() { a2w.Stubs.hit("android.app.LocaleManager.getSystemLocales"); return null; }
  @a2w.NotImplemented public void setApplicationLocales(android.os.LocaleList locales) { a2w.Stubs.hit("android.app.LocaleManager.setApplicationLocales"); }
  @a2w.NotImplemented public void setOverrideLocaleConfig(android.app.LocaleConfig localeConfig) { a2w.Stubs.hit("android.app.LocaleManager.setOverrideLocaleConfig"); }
}

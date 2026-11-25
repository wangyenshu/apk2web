// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.content.pm;

public class ShortcutManager {
  public static final int FLAG_MATCH_CACHED = 8;
  public static final int FLAG_MATCH_DYNAMIC = 2;
  public static final int FLAG_MATCH_MANIFEST = 1;
  public static final int FLAG_MATCH_PINNED = 4;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public ShortcutManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public boolean addDynamicShortcuts(java.util.List<android.content.pm.ShortcutInfo> shortcutInfoList) { a2w.Stubs.hit("android.content.pm.ShortcutManager.addDynamicShortcuts"); return false; }
  @a2w.NotImplemented public android.content.Intent createShortcutResultIntent(android.content.pm.ShortcutInfo shortcut) { a2w.Stubs.hit("android.content.pm.ShortcutManager.createShortcutResultIntent"); return null; }
  @a2w.NotImplemented public void disableShortcuts(java.util.List<java.lang.String> shortcutIds) { a2w.Stubs.hit("android.content.pm.ShortcutManager.disableShortcuts"); }
  @a2w.NotImplemented public void disableShortcuts(java.util.List<java.lang.String> shortcutIds, java.lang.CharSequence disabledMessage) { a2w.Stubs.hit("android.content.pm.ShortcutManager.disableShortcuts"); }
  @a2w.NotImplemented public void enableShortcuts(java.util.List<java.lang.String> shortcutIds) { a2w.Stubs.hit("android.content.pm.ShortcutManager.enableShortcuts"); }
  @a2w.NotImplemented public java.util.List<android.content.pm.ShortcutInfo> getDynamicShortcuts() { a2w.Stubs.hit("android.content.pm.ShortcutManager.getDynamicShortcuts"); return null; }
  @a2w.NotImplemented public int getIconMaxHeight() { a2w.Stubs.hit("android.content.pm.ShortcutManager.getIconMaxHeight"); return 0; }
  @a2w.NotImplemented public int getIconMaxWidth() { a2w.Stubs.hit("android.content.pm.ShortcutManager.getIconMaxWidth"); return 0; }
  @a2w.NotImplemented public java.util.List<android.content.pm.ShortcutInfo> getManifestShortcuts() { a2w.Stubs.hit("android.content.pm.ShortcutManager.getManifestShortcuts"); return null; }
  @a2w.NotImplemented public int getMaxShortcutCountPerActivity() { a2w.Stubs.hit("android.content.pm.ShortcutManager.getMaxShortcutCountPerActivity"); return 0; }
  @a2w.NotImplemented public java.util.List<android.content.pm.ShortcutInfo> getPinnedShortcuts() { a2w.Stubs.hit("android.content.pm.ShortcutManager.getPinnedShortcuts"); return null; }
  @a2w.NotImplemented public java.util.List<android.content.pm.ShortcutInfo> getShortcuts(int matchFlags) { a2w.Stubs.hit("android.content.pm.ShortcutManager.getShortcuts"); return null; }
  @a2w.NotImplemented public boolean isRateLimitingActive() { a2w.Stubs.hit("android.content.pm.ShortcutManager.isRateLimitingActive"); return false; }
  @a2w.NotImplemented public boolean isRequestPinShortcutSupported() { a2w.Stubs.hit("android.content.pm.ShortcutManager.isRequestPinShortcutSupported"); return false; }
  @a2w.NotImplemented public void pushDynamicShortcut(android.content.pm.ShortcutInfo shortcut) { a2w.Stubs.hit("android.content.pm.ShortcutManager.pushDynamicShortcut"); }
  @a2w.NotImplemented public void removeAllDynamicShortcuts() { a2w.Stubs.hit("android.content.pm.ShortcutManager.removeAllDynamicShortcuts"); }
  @a2w.NotImplemented public void removeDynamicShortcuts(java.util.List<java.lang.String> shortcutIds) { a2w.Stubs.hit("android.content.pm.ShortcutManager.removeDynamicShortcuts"); }
  @a2w.NotImplemented public void removeLongLivedShortcuts(java.util.List<java.lang.String> shortcutIds) { a2w.Stubs.hit("android.content.pm.ShortcutManager.removeLongLivedShortcuts"); }
  @a2w.NotImplemented public void reportShortcutUsed(java.lang.String shortcutId) { a2w.Stubs.hit("android.content.pm.ShortcutManager.reportShortcutUsed"); }
  @a2w.NotImplemented public boolean requestPinShortcut(android.content.pm.ShortcutInfo shortcut, android.content.IntentSender resultIntent) { a2w.Stubs.hit("android.content.pm.ShortcutManager.requestPinShortcut"); return false; }
  @a2w.NotImplemented public boolean setDynamicShortcuts(java.util.List<android.content.pm.ShortcutInfo> shortcutInfoList) { a2w.Stubs.hit("android.content.pm.ShortcutManager.setDynamicShortcuts"); return false; }
  @a2w.NotImplemented public boolean updateShortcuts(java.util.List<android.content.pm.ShortcutInfo> shortcutInfoList) { a2w.Stubs.hit("android.content.pm.ShortcutManager.updateShortcuts"); return false; }
}

// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.content.om;

public class OverlayManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public OverlayManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void commit(android.content.om.OverlayManagerTransaction transaction) { a2w.Stubs.hit("android.content.om.OverlayManager.commit"); }
  @a2w.NotImplemented public java.util.List<android.content.om.OverlayInfo> getOverlayInfosForTarget(java.lang.String targetPackageName) { a2w.Stubs.hit("android.content.om.OverlayManager.getOverlayInfosForTarget"); return null; }
}

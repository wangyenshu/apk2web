// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.view.displayhash;

public final class DisplayHashManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public DisplayHashManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public java.util.Set<java.lang.String> getSupportedHashAlgorithms() { a2w.Stubs.hit("android.view.displayhash.DisplayHashManager.getSupportedHashAlgorithms"); return null; }
  @a2w.NotImplemented public android.view.displayhash.VerifiedDisplayHash verifyDisplayHash(android.view.displayhash.DisplayHash displayHash) { a2w.Stubs.hit("android.view.displayhash.DisplayHashManager.verifyDisplayHash"); return null; }
}

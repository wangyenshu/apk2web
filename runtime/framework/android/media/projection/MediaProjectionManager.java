// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.media.projection;

public final class MediaProjectionManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public MediaProjectionManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.content.Intent createScreenCaptureIntent() { a2w.Stubs.hit("android.media.projection.MediaProjectionManager.createScreenCaptureIntent"); return null; }
  @a2w.NotImplemented public android.content.Intent createScreenCaptureIntent(android.media.projection.MediaProjectionConfig config) { a2w.Stubs.hit("android.media.projection.MediaProjectionManager.createScreenCaptureIntent"); return null; }
  @a2w.NotImplemented public android.media.projection.MediaProjection getMediaProjection(int resultCode, android.content.Intent resultData) { a2w.Stubs.hit("android.media.projection.MediaProjectionManager.getMediaProjection"); return null; }
}

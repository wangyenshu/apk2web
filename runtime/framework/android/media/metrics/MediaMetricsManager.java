// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.media.metrics;

public final class MediaMetricsManager {
  public static final long INVALID_TIMESTAMP = -1L;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public MediaMetricsManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.media.metrics.BundleSession createBundleSession() { a2w.Stubs.hit("android.media.metrics.MediaMetricsManager.createBundleSession"); return null; }
  @a2w.NotImplemented public android.media.metrics.EditingSession createEditingSession() { a2w.Stubs.hit("android.media.metrics.MediaMetricsManager.createEditingSession"); return null; }
  @a2w.NotImplemented public android.media.metrics.PlaybackSession createPlaybackSession() { a2w.Stubs.hit("android.media.metrics.MediaMetricsManager.createPlaybackSession"); return null; }
  @a2w.NotImplemented public android.media.metrics.RecordingSession createRecordingSession() { a2w.Stubs.hit("android.media.metrics.MediaMetricsManager.createRecordingSession"); return null; }
  @a2w.NotImplemented public android.media.metrics.TranscodingSession createTranscodingSession() { a2w.Stubs.hit("android.media.metrics.MediaMetricsManager.createTranscodingSession"); return null; }
  @a2w.NotImplemented public void releaseSessionId(java.lang.String sessionId) { a2w.Stubs.hit("android.media.metrics.MediaMetricsManager.releaseSessionId"); }
}

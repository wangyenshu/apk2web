package android.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import a2w.Audio;
import a2w.Host;
import a2w.jdk.FileDescriptor;

/** MediaPlayer on an HTMLAudioElement. */
public class MediaPlayer {
  public interface OnPreparedListener { void onPrepared(MediaPlayer mp); }
  public interface OnCompletionListener { void onCompletion(MediaPlayer mp); }
  public interface OnErrorListener { boolean onError(MediaPlayer mp, int what, int extra); }
  public interface OnInfoListener { boolean onInfo(MediaPlayer mp, int what, int extra); }
  public interface OnSeekCompleteListener { void onSeekComplete(MediaPlayer mp); }
  public interface OnBufferingUpdateListener { void onBufferingUpdate(MediaPlayer mp, int percent); }
  public interface OnVideoSizeChangedListener { void onVideoSizeChanged(MediaPlayer mp, int w, int h); }
  public static final int MEDIA_ERROR_UNKNOWN = 1, MEDIA_ERROR_SERVER_DIED = 100, MEDIA_INFO_UNKNOWN = 1;
  final int id = Audio.mpCreate();
  byte[] data;
  boolean looping, prepared, released, playing;
  float volume = 1;
  OnPreparedListener onPrepared;
  OnCompletionListener onCompletion;
  OnErrorListener onError;
  OnSeekCompleteListener onSeek;

  public MediaPlayer() {
    Audio.listen(id, ev -> {
      if (ev == 1) { playing = false; if (onCompletion != null) onCompletion.onCompletion(this); }
      else if (ev == 2) { playing = false; if (onError == null || !onError.onError(this, MEDIA_ERROR_UNKNOWN, 0)) { if (onCompletion != null) onCompletion.onCompletion(this); } }
    });
  }
  public static MediaPlayer create(Context c, int resid) {
    try {
      AssetFileDescriptor afd = c.getResources().openRawResourceFd(resid);
      MediaPlayer mp = new MediaPlayer();
      mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
      mp.prepare();
      return mp;
    } catch (Exception e) { return null; }
  }
  public static MediaPlayer create(Context c, android.net.Uri uri) { return null; }
  public void setDataSource(String path) throws java.io.IOException {
    byte[] d;
    if (path.startsWith("/android_asset/")) d = Host.apkRead("assets/" + path.substring(15));
    else if (path.startsWith("file://")) d = Host.fileExists(path.substring(7)) ? Host.fileRead(path.substring(7)) : null;
    else if (path.startsWith("/")) d = Host.fileExists(path) ? Host.fileRead(path) : null;
    else d = Host.apkRead("assets/" + path);
    if (d == null) throw new java.io.IOException("setDataSource failed.: status=0x80000000");
    data = d;
  }
  public void setDataSource(Context c, android.net.Uri uri) throws java.io.IOException { setDataSource(uri.getPath()); }
  public void setDataSource(FileDescriptor fd) throws java.io.IOException { setDataSource(fd, 0, Long.MAX_VALUE); }
  public void setDataSource(FileDescriptor fd, long offset, long length) throws java.io.IOException {
    byte[] d = fd.data != null ? fd.data : fd.path != null ? Host.fileRead(fd.path) : null;
    if (d == null) throw new java.io.IOException("setDataSource failed");
    if (offset > 0 || length < d.length) {
      int len = (int) Math.min(length, d.length - offset);
      byte[] s = new byte[len];
      System.arraycopy(d, (int) offset, s, 0, len);
      d = s;
    }
    data = d;
  }
  public void prepare() throws java.io.IOException {
    if (data == null) throw new IllegalStateException("prepare called in state 1");
    Audio.mpSource(id, data, looping);
    Audio.mpVolume(id, volume);
    prepared = true;
  }
  public void prepareAsync() {
    try { prepare(); } catch (java.io.IOException e) {}
    if (onPrepared != null) a2w.ViewRoot.mainHandler().post(() -> { if (onPrepared != null) onPrepared.onPrepared(this); });
  }
  public void start() { if (prepared && !released) { Audio.mpPlay(id); playing = true; } }
  public void pause() { if (prepared) { Audio.mpPause(id); playing = false; } }
  public void stop() { if (prepared) { Audio.mpStop(id); playing = false; prepared = false; } }
  public void reset() { if (prepared) Audio.mpStop(id); playing = false; prepared = false; data = null; looping = false; }
  public void release() { if (!released) { Audio.mpRelease(id); Audio.listen(id, null); released = true; playing = false; prepared = false; } }
  public boolean isPlaying() { return playing && !released; }
  public void seekTo(int ms) { Audio.mpSeek(id, ms); if (onSeek != null) onSeek.onSeekComplete(this); }
  public int getCurrentPosition() { return Audio.mpPosition(id); }
  public int getDuration() { return Audio.mpDuration(id); }
  public void setLooping(boolean l) { looping = l; Audio.mpLoop(id, l); }
  public boolean isLooping() { return looping; }
  public void setVolume(float l, float r) { volume = Math.max(l, r); Audio.mpVolume(id, volume); }
  public void setAudioStreamType(int t) {}
  public void setWakeMode(Context c, int mode) {}
  public void setScreenOnWhilePlaying(boolean s) {}
  public int getAudioSessionId() { return id; }
  public int getVideoWidth() { return 0; }
  public int getVideoHeight() { return 0; }
  public void setDisplay(Object holder) {}
  public void setOnPreparedListener(OnPreparedListener l) { onPrepared = l; }
  public void setOnCompletionListener(OnCompletionListener l) { onCompletion = l; }
  public void setOnErrorListener(OnErrorListener l) { onError = l; }
  public void setOnInfoListener(OnInfoListener l) {}
  public void setOnSeekCompleteListener(OnSeekCompleteListener l) { onSeek = l; }
  public void setOnBufferingUpdateListener(OnBufferingUpdateListener l) {}
  public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener l) {}
}

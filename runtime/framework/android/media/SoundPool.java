package android.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import a2w.Audio;
import a2w.Host;
import java.util.HashSet;

/** SoundPool on WebAudio buffers. */
public class SoundPool {
  public interface OnLoadCompleteListener { void onLoadComplete(SoundPool pool, int sampleId, int status); }
  private static int nextId = 1;
  final int maxStreams;
  OnLoadCompleteListener listener;
  final HashSet<Integer> streams = new HashSet<>();
  public SoundPool(int maxStreams, int streamType, int srcQuality) { this.maxStreams = maxStreams; }
  public void setOnLoadCompleteListener(OnLoadCompleteListener l) { listener = l; }
  int load(byte[] d) {
    final int id = nextId++;
    if (d == null) { if (listener != null) a2w.ViewRoot.mainHandler().post(() -> { if (listener != null) listener.onLoadComplete(this, id, 1); }); return 0; }
    Audio.listen(id | 0x40000000, ev -> { Audio.listen(id | 0x40000000, null); if (listener != null) listener.onLoadComplete(this, id, ev == 0 ? 0 : 1); });
    Audio.spLoad(id, d);
    return id;
  }
  public int load(String path, int priority) {
    byte[] d = path.startsWith("/android_asset/") ? Host.apkRead("assets/" + path.substring(15)) : Host.fileExists(path) ? Host.fileRead(path) : Host.apkRead("assets/" + path);
    return load(d);
  }
  public int load(Context c, int resId, int priority) {
    try { return load(c.getResources().openRawResourceFd(resId), priority); } catch (Exception e) { return 0; }
  }
  public int load(AssetFileDescriptor afd, int priority) {
    byte[] d = afd.getFileDescriptor().data;
    return load(d);
  }
  public int load(a2w.jdk.FileDescriptor fd, long offset, long length, int priority) {
    byte[] d = fd.data;
    if (d != null && (offset > 0 || length < d.length)) { byte[] s = new byte[(int) length]; System.arraycopy(d, (int) offset, s, 0, (int) length); d = s; }
    return load(d);
  }
  public final boolean unload(int soundId) { Audio.spUnload(soundId); return true; }
  public final int play(int soundId, float left, float right, int priority, int loop, float rate) {
    if (soundId <= 0) return 0;
    int s = Audio.spPlay(soundId, left, right, loop, rate);
    if (s > 0) streams.add(s);
    return s;
  }
  public final void pause(int stream) { Audio.spPause(stream); }
  public final void resume(int stream) { Audio.spResume(stream); }
  public final void autoPause() { for (int s : streams) Audio.spPause(s); }
  public final void autoResume() { for (int s : streams) Audio.spResume(s); }
  public final void stop(int stream) { Audio.spStop(stream); streams.remove(stream); }
  public final void setVolume(int stream, float l, float r) { Audio.spVolume(stream, l, r); }
  public final void setPriority(int stream, int p) {}
  public final void setLoop(int stream, int loop) {}
  public final void setRate(int stream, float rate) {}
  public final void release() { for (int s : streams) Audio.spStop(s); streams.clear(); }
}

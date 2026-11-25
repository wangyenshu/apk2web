package a2w;

import java.util.HashMap;
import org.teavm.jso.JSBody;
import org.teavm.jso.typedarrays.Int8Array;

/** Bridge to the page's audio (HTMLAudioElement for MediaPlayer, WebAudio for SoundPool). */
public final class Audio {
  private Audio() {}
  public interface Listener { void onAudioEvent(int event); }
  static final HashMap<Integer, Listener> listeners = new HashMap<>();
  public static void listen(int id, Listener l) { if (l == null) listeners.remove(id); else listeners.put(id, l); }
  static void event(int id, int ev) { Listener l = listeners.get(id); if (l != null) l.onAudioEvent(ev); }
  static void pageHidden(boolean h) { pageHiddenJS(h); }

  // ---- AudioTrack: PCM, as 16-bit samples, queued on Web Audio. trackWrite returns how many ms the writer should wait
  // (the queue is longer than the track's buffer); a native writer waits in the emulator instead and gets 0
  @JSBody(params = {"rate", "ch", "bufMs"}, script = "return HW.audio.trackCreate(rate, ch, bufMs);")
  public static native int trackCreate(int rate, int channels, double bufferMs);
  @JSBody(params = {"id", "pcm"}, script = "return HW.audio.trackWrite(id, pcm);")
  static native double trackWriteJS(int id, org.teavm.jso.typedarrays.Int16Array pcm);
  public static double trackWrite(int id, short[] pcm, int off, int n) {
    org.teavm.jso.typedarrays.Int16Array a = org.teavm.jso.typedarrays.Int16Array.create(n);
    for (int i = 0; i < n; i++) a.set(i, pcm[off + i]);
    return trackWriteJS(id, a);
  }
  @JSBody(params = {"id", "op"}, script = "HW.audio.trackControl(id, op);")
  public static native void trackControl(int id, int op);   // 1 play 2 pause 3 stop 4 flush 5 release
  @JSBody(params = {"id", "v"}, script = "HW.audio.trackVolume(id, v);")
  public static native void trackVolume(int id, float v);
  @JSBody(params = {"id"}, script = "return HW.audio.trackPosition(id);")
  public static native int trackPosition(int id);

  @JSBody(params = {"h"}, script = "HW.audio.pageHidden(h);")
  static native void pageHiddenJS(boolean h);
  @JSBody(script = "return HW.audio.mpCreate();")
  public static native int mpCreate();
  @JSBody(params = {"id", "d", "loop"}, script = "HW.audio.mpSource(id, d, loop);")
  static native void mpSourceJS(int id, Int8Array d, boolean loop);
  public static void mpSource(int id, byte[] d, boolean loop) { mpSourceJS(id, Host.js(d), loop); }
  @JSBody(params = {"id"}, script = "HW.audio.mpPlay(id);")
  public static native void mpPlay(int id);
  @JSBody(params = {"id"}, script = "HW.audio.mpPause(id);")
  public static native void mpPause(int id);
  @JSBody(params = {"id"}, script = "HW.audio.mpStop(id);")
  public static native void mpStop(int id);
  @JSBody(params = {"id", "ms"}, script = "HW.audio.mpSeek(id, ms);")
  public static native void mpSeek(int id, int ms);
  @JSBody(params = {"id", "l"}, script = "HW.audio.mpLoop(id, l);")
  public static native void mpLoop(int id, boolean loop);
  @JSBody(params = {"id", "v"}, script = "HW.audio.mpVolume(id, v);")
  public static native void mpVolume(int id, float v);
  @JSBody(params = {"id"}, script = "return HW.audio.mpPosition(id);")
  public static native int mpPosition(int id);
  @JSBody(params = {"id"}, script = "return HW.audio.mpDuration(id);")
  public static native int mpDuration(int id);
  @JSBody(params = {"id"}, script = "return HW.audio.mpPlaying(id);")
  public static native boolean mpPlaying(int id);
  @JSBody(params = {"id"}, script = "HW.audio.mpRelease(id);")
  public static native void mpRelease(int id);

  @JSBody(params = {"id", "d"}, script = "HW.audio.spLoad(id, d);")
  static native void spLoadJS(int id, Int8Array d);
  public static void spLoad(int id, byte[] d) { spLoadJS(id, Host.js(d)); }
  @JSBody(params = {"id", "l", "r", "loop", "rate"}, script = "return HW.audio.spPlay(id, l, r, loop, rate);")
  public static native int spPlay(int id, float l, float r, int loop, float rate);
  @JSBody(params = {"s"}, script = "HW.audio.spStop(s);")
  public static native void spStop(int stream);
  @JSBody(params = {"s"}, script = "HW.audio.spPause(s);")
  public static native void spPause(int stream);
  @JSBody(params = {"s"}, script = "HW.audio.spResume(s);")
  public static native void spResume(int stream);
  @JSBody(params = {"s", "l", "r"}, script = "HW.audio.spVolume(s, l, r);")
  public static native void spVolume(int stream, float l, float r);
  @JSBody(params = {"id"}, script = "HW.audio.spUnload(id);")
  public static native void spUnload(int id);
}

package android.media;
public class AudioManager {
  public static final int STREAM_VOICE_CALL = 0, STREAM_SYSTEM = 1, STREAM_RING = 2, STREAM_MUSIC = 3, STREAM_ALARM = 4, STREAM_NOTIFICATION = 5, USE_DEFAULT_STREAM_TYPE = Integer.MIN_VALUE;
  public static final int ADJUST_RAISE = 1, ADJUST_LOWER = -1, ADJUST_SAME = 0, FLAG_SHOW_UI = 1, FLAG_PLAY_SOUND = 4;
  public static final int RINGER_MODE_SILENT = 0, RINGER_MODE_VIBRATE = 1, RINGER_MODE_NORMAL = 2;
  public static final int AUDIOFOCUS_GAIN = 1, AUDIOFOCUS_LOSS = -1, AUDIOFOCUS_REQUEST_GRANTED = 1, AUDIOFOCUS_REQUEST_FAILED = 0;
  public static final String ACTION_AUDIO_BECOMING_NOISY = "android.media.AUDIO_BECOMING_NOISY";
  public interface OnAudioFocusChangeListener { void onAudioFocusChange(int focusChange); }
  int volume = 15;
  public int getStreamVolume(int s) { return volume; }
  public int getStreamMaxVolume(int s) { return 15; }
  public void setStreamVolume(int s, int v, int f) { volume = Math.max(0, Math.min(15, v)); }
  public void adjustStreamVolume(int s, int d, int f) { setStreamVolume(s, volume + d, f); }
  public void adjustVolume(int d, int f) { adjustStreamVolume(STREAM_MUSIC, d, f); }
  public int getRingerMode() { return RINGER_MODE_NORMAL; }
  public void setRingerMode(int m) {}
  public boolean isMusicActive() { return false; }
  public boolean isSpeakerphoneOn() { return true; }
  public int requestAudioFocus(OnAudioFocusChangeListener l, int stream, int hint) { return AUDIOFOCUS_REQUEST_GRANTED; }
  public int abandonAudioFocus(OnAudioFocusChangeListener l) { return AUDIOFOCUS_REQUEST_GRANTED; }
  public int getMode() { return 0; }
  public void playSoundEffect(int e) {}
}

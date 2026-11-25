package android.media;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * PCM playback on Web Audio. write() queues samples and blocks the way a device does when the queue is full: a Java
 * thread sleeps; native code (writing through JNI) waits in the emulator.
 */
public class AudioTrack {
  public static final int STATE_UNINITIALIZED = 0, STATE_INITIALIZED = 1, STATE_NO_STATIC_DATA = 2;
  public static final int PLAYSTATE_STOPPED = 1, PLAYSTATE_PAUSED = 2, PLAYSTATE_PLAYING = 3;
  public static final int MODE_STATIC = 0, MODE_STREAM = 1;
  public static final int SUCCESS = 0, ERROR = -1, ERROR_BAD_VALUE = -2, ERROR_INVALID_OPERATION = -3, ERROR_DEAD_OBJECT = -6;
  public static final int WRITE_BLOCKING = 0, WRITE_NON_BLOCKING = 1;
  public interface OnPlaybackPositionUpdateListener {
    void onMarkerReached(AudioTrack track);
    void onPeriodicNotification(AudioTrack track);
  }
  private final int id, rate, channels, encoding, bufferBytes, mode, stream;
  private int state = STATE_INITIALIZED, playState = PLAYSTATE_STOPPED, sessionId = 1;
  private short[] staticData;
  private float volume = 1;

  public AudioTrack(int streamType, int sampleRate, int channelConfig, int audioFormat, int bufferSizeInBytes, int mode) {
    this(streamType, sampleRate, channelConfig, audioFormat, bufferSizeInBytes, mode, 1);
  }
  public AudioTrack(int streamType, int sampleRate, int channelConfig, int audioFormat, int bufferSizeInBytes, int mode, int sessionId) {
    this.stream = streamType;
    this.rate = sampleRate > 0 ? sampleRate : 44100;
    this.channels = AudioFormat.channels(channelConfig);
    this.encoding = audioFormat == AudioFormat.ENCODING_DEFAULT ? AudioFormat.ENCODING_PCM_16BIT : audioFormat;
    this.bufferBytes = bufferSizeInBytes > 0 ? bufferSizeInBytes : getMinBufferSize(rate, channelConfig, audioFormat);
    this.mode = mode;
    this.sessionId = sessionId;
    double ms = 1000.0 * bufferBytes / (rate * channels * bytesPerSample());
    this.id = a2w.Audio.trackCreate(rate, channels, Math.max(ms, 40));
    if (mode == MODE_STATIC) state = STATE_NO_STATIC_DATA;
  }
  public AudioTrack(AudioAttributes attributes, AudioFormat format, int bufferSizeInBytes, int mode, int sessionId) {
    this(3, format.getSampleRate(), format.getChannelMask(), format.getEncoding(), bufferSizeInBytes, mode, sessionId);
  }
  private int bytesPerSample() { return encoding == AudioFormat.ENCODING_PCM_8BIT ? 1 : encoding == AudioFormat.ENCODING_PCM_FLOAT ? 4 : 2; }

  public static int getMinBufferSize(int sampleRate, int channelConfig, int audioFormat) {
    int ch = AudioFormat.channels(channelConfig), b = audioFormat == AudioFormat.ENCODING_PCM_8BIT ? 1 : audioFormat == AudioFormat.ENCODING_PCM_FLOAT ? 4 : 2;
    if (sampleRate < 4000 || sampleRate > 192000) return ERROR_BAD_VALUE;
    return (sampleRate * ch * b / 20 + 255) & ~255;   // about 50 ms
  }
  public static int getNativeOutputSampleRate(int streamType) { return 44100; }
  public static float getMinVolume() { return 0; }
  public static float getMaxVolume() { return 1; }

  public int getState() { return state; }
  public int getPlayState() { return playState; }
  public int getSampleRate() { return rate; }
  public int getPlaybackRate() { return rate; }
  public int setPlaybackRate(int r) { return SUCCESS; }
  public int getChannelCount() { return channels; }
  public int getAudioFormat() { return encoding; }
  public int getStreamType() { return stream; }
  public int getAudioSessionId() { return sessionId; }
  public int getBufferSizeInFrames() { return bufferBytes / (channels * bytesPerSample()); }
  public int getNotificationMarkerPosition() { return 0; }
  public int getPositionNotificationPeriod() { return 0; }
  public int getPlaybackHeadPosition() { return a2w.Audio.trackPosition(id); }
  public int setNotificationMarkerPosition(int frames) { return SUCCESS; }
  public int setPositionNotificationPeriod(int frames) { return SUCCESS; }
  public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener l) {}
  public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener l, android.os.Handler h) {}
  public int setStereoVolume(float left, float right) { volume = Math.max(left, right); a2w.Audio.trackVolume(id, volume); return SUCCESS; }
  public int setVolume(float v) { return setStereoVolume(v, v); }
  public int setLoopPoints(int start, int end, int count) { return SUCCESS; }
  public int reloadStaticData() { return SUCCESS; }
  public int attachAuxEffect(int effectId) { return SUCCESS; }
  public int setAuxEffectSendLevel(float level) { return SUCCESS; }

  public void play() {
    if (state == STATE_UNINITIALIZED) throw new IllegalStateException("play() called on an uninitialized AudioTrack");
    playState = PLAYSTATE_PLAYING;
    a2w.Audio.trackControl(id, 1);
    if (mode == MODE_STATIC && staticData != null) a2w.Audio.trackWrite(id, staticData, 0, staticData.length);
  }
  public void pause() { playState = PLAYSTATE_PAUSED; a2w.Audio.trackControl(id, 2); }
  public void stop() { playState = PLAYSTATE_STOPPED; a2w.Audio.trackControl(id, 3); }
  public void flush() { a2w.Audio.trackControl(id, 4); }
  public void release() { state = STATE_UNINITIALIZED; playState = PLAYSTATE_STOPPED; a2w.Audio.trackControl(id, 5); }

  public int write(short[] data, int off, int n) { return write(data, off, n, WRITE_BLOCKING); }
  public int write(short[] data, int off, int n, int writeMode) {
    if (state == STATE_UNINITIALIZED) return ERROR_INVALID_OPERATION;
    if (data == null || off < 0 || n < 0 || off + n > data.length) return ERROR_BAD_VALUE;
    if (mode == MODE_STATIC) { staticData = java.util.Arrays.copyOfRange(data, off, off + n); state = STATE_INITIALIZED; return n; }
    double wait = a2w.Audio.trackWrite(id, data, off, n);
    if (wait > 0 && writeMode == WRITE_BLOCKING) {
      try { Thread.sleep((long) Math.ceil(wait)); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    return n;
  }
  public int write(byte[] data, int off, int n) { return write(data, off, n, WRITE_BLOCKING); }
  public int write(byte[] data, int off, int n, int writeMode) {
    if (data == null || off < 0 || n < 0 || off + n > data.length) return ERROR_BAD_VALUE;
    short[] s;
    if (encoding == AudioFormat.ENCODING_PCM_8BIT) { s = new short[n]; for (int i = 0; i < n; i++) s[i] = (short) (((data[off + i] & 0xFF) - 128) << 8); }
    else { s = new short[n / 2]; for (int i = 0; i < s.length; i++) s[i] = (short) ((data[off + 2 * i] & 0xFF) | (data[off + 2 * i + 1] << 8)); }
    int r = write(s, 0, s.length, writeMode);
    return r < 0 ? r : encoding == AudioFormat.ENCODING_PCM_8BIT ? r : r * 2;
  }
  public int write(float[] data, int off, int n, int writeMode) {
    if (data == null || off < 0 || n < 0 || off + n > data.length) return ERROR_BAD_VALUE;
    short[] s = new short[n];
    for (int i = 0; i < n; i++) s[i] = (short) Math.max(-32768, Math.min(32767, Math.round(data[off + i] * 32767f)));
    return write(s, 0, n, writeMode);
  }
  public int write(ByteBuffer b, int size, int writeMode) {
    byte[] d = new byte[size];
    b.order(ByteOrder.LITTLE_ENDIAN).get(d);
    return write(d, 0, size, writeMode);
  }
}

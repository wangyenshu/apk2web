package android.media;

public class AudioFormat {
  public static final int ENCODING_INVALID = 0, ENCODING_DEFAULT = 1, ENCODING_PCM_16BIT = 2, ENCODING_PCM_8BIT = 3, ENCODING_PCM_FLOAT = 4;
  public static final int CHANNEL_INVALID = 0, CHANNEL_OUT_DEFAULT = 1, CHANNEL_OUT_MONO = 4, CHANNEL_OUT_STEREO = 12,
      CHANNEL_OUT_FRONT_LEFT = 4, CHANNEL_OUT_FRONT_RIGHT = 8, CHANNEL_IN_MONO = 16, CHANNEL_IN_STEREO = 12,
      CHANNEL_CONFIGURATION_DEFAULT = 1, CHANNEL_CONFIGURATION_MONO = 2, CHANNEL_CONFIGURATION_STEREO = 3;
  private final int encoding, rate, mask;
  AudioFormat(int encoding, int rate, int mask) { this.encoding = encoding; this.rate = rate; this.mask = mask; }
  public int getEncoding() { return encoding; }
  public int getSampleRate() { return rate; }
  public int getChannelMask() { return mask; }
  public int getChannelCount() { return channels(mask); }
  /** channels of a channel mask or of an old CHANNEL_CONFIGURATION value */
  static int channels(int config) {
    if (config == CHANNEL_CONFIGURATION_STEREO || config == CHANNEL_OUT_STEREO) return 2;
    if (config == CHANNEL_CONFIGURATION_MONO || config == CHANNEL_OUT_MONO || config == CHANNEL_CONFIGURATION_DEFAULT || config == CHANNEL_IN_MONO) return 1;
    return Math.max(1, Integer.bitCount(config & 0xFFFC));
  }
  public static class Builder {
    int encoding = ENCODING_PCM_16BIT, rate = 44100, mask = CHANNEL_OUT_STEREO;
    public Builder setEncoding(int e) { encoding = e; return this; }
    public Builder setSampleRate(int r) { rate = r; return this; }
    public Builder setChannelMask(int m) { mask = m; return this; }
    public AudioFormat build() { return new AudioFormat(encoding, rate, mask); }
  }
}

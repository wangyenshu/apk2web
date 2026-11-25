package android.media;

public final class AudioAttributes {
  public static final int USAGE_UNKNOWN = 0, USAGE_MEDIA = 1, USAGE_GAME = 14, CONTENT_TYPE_UNKNOWN = 0, CONTENT_TYPE_SPEECH = 1,
      CONTENT_TYPE_MUSIC = 2, CONTENT_TYPE_MOVIE = 3, CONTENT_TYPE_SONIFICATION = 4, FLAG_AUDIBILITY_ENFORCED = 1;
  private final int usage, content;
  AudioAttributes(int usage, int content) { this.usage = usage; this.content = content; }
  public int getUsage() { return usage; }
  public int getContentType() { return content; }
  public int getFlags() { return 0; }
  public static class Builder {
    int usage, content;
    public Builder setUsage(int u) { usage = u; return this; }
    public Builder setContentType(int c) { content = c; return this; }
    public Builder setFlags(int f) { return this; }
    public Builder setLegacyStreamType(int t) { return this; }
    public AudioAttributes build() { return new AudioAttributes(usage, content); }
  }
}

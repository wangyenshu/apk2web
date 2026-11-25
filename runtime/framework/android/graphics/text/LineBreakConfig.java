package android.graphics.text;

/** The line-break style, word style and hyphenation of a paragraph (the line breaker is greedy, see LineBreaker). */
public final class LineBreakConfig implements android.os.Parcelable {
  public static final int HYPHENATION_DISABLED = 0, HYPHENATION_ENABLED = 1, HYPHENATION_UNSPECIFIED = -1;
  public static final int LINE_BREAK_STYLE_UNSPECIFIED = -1, LINE_BREAK_STYLE_NONE = 0, LINE_BREAK_STYLE_LOOSE = 1, LINE_BREAK_STYLE_NORMAL = 2,
      LINE_BREAK_STYLE_STRICT = 3, LINE_BREAK_STYLE_NO_BREAK = 4, LINE_BREAK_STYLE_AUTO = 5;
  public static final int LINE_BREAK_WORD_STYLE_UNSPECIFIED = -1, LINE_BREAK_WORD_STYLE_NONE = 0, LINE_BREAK_WORD_STYLE_PHRASE = 1, LINE_BREAK_WORD_STYLE_AUTO = 2;
  private final int mLineBreakStyle, mLineBreakWordStyle, mHyphenation;
  LineBreakConfig(int style, int wordStyle, int hyphenation) { mLineBreakStyle = style; mLineBreakWordStyle = wordStyle; mHyphenation = hyphenation; }
  public int getLineBreakStyle() { return mLineBreakStyle; }
  public int getLineBreakWordStyle() { return mLineBreakWordStyle; }
  public int getHyphenation() { return mHyphenation; }
  /** this config with the specified (not UNSPECIFIED) values of config */
  public LineBreakConfig merge(LineBreakConfig config) {
    if (config == null) return this;
    return new LineBreakConfig(config.mLineBreakStyle == LINE_BREAK_STYLE_UNSPECIFIED ? mLineBreakStyle : config.mLineBreakStyle,
        config.mLineBreakWordStyle == LINE_BREAK_WORD_STYLE_UNSPECIFIED ? mLineBreakWordStyle : config.mLineBreakWordStyle,
        config.mHyphenation == HYPHENATION_UNSPECIFIED ? mHyphenation : config.mHyphenation);
  }
  @Override public boolean equals(Object o) {
    if (!(o instanceof LineBreakConfig)) return false;
    LineBreakConfig c = (LineBreakConfig) o;
    return c.mLineBreakStyle == mLineBreakStyle && c.mLineBreakWordStyle == mLineBreakWordStyle && c.mHyphenation == mHyphenation;
  }
  @Override public int hashCode() { return (mLineBreakStyle * 31 + mLineBreakWordStyle) * 31 + mHyphenation; }
  @Override public String toString() { return "LineBreakConfig{mLineBreakStyle=" + mLineBreakStyle + ", mLineBreakWordStyle=" + mLineBreakWordStyle + ", mHyphenation=" + mHyphenation + "}"; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeInt(mLineBreakStyle); dest.writeInt(mLineBreakWordStyle); dest.writeInt(mHyphenation); }
  public static final android.os.Parcelable.Creator<LineBreakConfig> CREATOR = new Creator();
  static final class Creator implements android.os.Parcelable.Creator<LineBreakConfig> {
    public LineBreakConfig createFromParcel(android.os.Parcel in) { return new LineBreakConfig(in.readInt(), in.readInt(), in.readInt()); }
    public LineBreakConfig[] newArray(int size) { return new LineBreakConfig[size]; }
  }

  public static final class Builder {
    private int mLineBreakStyle = LINE_BREAK_STYLE_UNSPECIFIED, mLineBreakWordStyle = LINE_BREAK_WORD_STYLE_UNSPECIFIED, mHyphenation = HYPHENATION_UNSPECIFIED;
    public Builder() {}
    public Builder merge(LineBreakConfig config) {
      if (config.mLineBreakStyle != LINE_BREAK_STYLE_UNSPECIFIED) mLineBreakStyle = config.mLineBreakStyle;
      if (config.mLineBreakWordStyle != LINE_BREAK_WORD_STYLE_UNSPECIFIED) mLineBreakWordStyle = config.mLineBreakWordStyle;
      if (config.mHyphenation != HYPHENATION_UNSPECIFIED) mHyphenation = config.mHyphenation;
      return this;
    }
    public Builder setLineBreakStyle(int lineBreakStyle) { mLineBreakStyle = lineBreakStyle; return this; }
    public Builder setLineBreakWordStyle(int lineBreakWordStyle) { mLineBreakWordStyle = lineBreakWordStyle; return this; }
    public Builder setHyphenation(int hyphenation) { mHyphenation = hyphenation; return this; }
    public LineBreakConfig build() { return new LineBreakConfig(mLineBreakStyle, mLineBreakWordStyle, mHyphenation); }
  }
}

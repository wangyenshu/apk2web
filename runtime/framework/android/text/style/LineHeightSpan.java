package android.text.style;

import android.graphics.Paint;

public interface LineHeightSpan extends ParagraphStyle, WrapTogetherSpan {
  void chooseHeight(CharSequence text, int start, int end, int spanstartv, int lineHeight, Paint.FontMetricsInt fm);
  interface WithDensity extends LineHeightSpan {
    void chooseHeight(CharSequence text, int start, int end, int spanstartv, int lineHeight, Paint.FontMetricsInt fm, android.text.TextPaint paint);
  }
  class Standard implements LineHeightSpan {
    private final int height;
    public Standard(int height) { this.height = height; }
    public int getHeight() { return height; }
    @Override public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int lineHeight, Paint.FontMetricsInt fm) {
      int original = fm.descent - fm.ascent;
      if (original <= 0) return;
      float ratio = height * 1f / original;
      fm.descent = Math.round(fm.descent * ratio);
      fm.ascent = fm.descent - height;
    }
  }
}

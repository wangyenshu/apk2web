package android.text.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;

public interface LeadingMarginSpan extends ParagraphStyle {
  int getLeadingMargin(boolean first);
  void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout);
  interface LeadingMarginSpan2 extends LeadingMarginSpan, WrapTogetherSpan { int getLeadingMarginLineCount(); }
  class Standard implements LeadingMarginSpan, android.text.ParcelableSpan {
    private final int first, rest;
    public Standard(int first, int rest) { this.first = first; this.rest = rest; }
    public Standard(int every) { this(every, every); }
    public Standard(android.os.Parcel src) { first = src.readInt(); rest = src.readInt(); }
    @Override public int getLeadingMargin(boolean f) { return f ? first : rest; }
    @Override public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean f, Layout layout) {}
    @Override public int getSpanTypeId() { return 10; }
    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeInt(first); dest.writeInt(rest); }
  }
}

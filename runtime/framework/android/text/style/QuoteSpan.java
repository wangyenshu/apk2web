package android.text.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;

public class QuoteSpan implements LeadingMarginSpan, android.text.ParcelableSpan {
  private final int color, stripe, gap;
  public QuoteSpan() { this(0xff0000ff); }
  public QuoteSpan(int color) { this(color, 2, 2); }
  public QuoteSpan(int color, int stripeWidth, int gapWidth) { this.color = color; stripe = stripeWidth; gap = gapWidth; }
  public QuoteSpan(android.os.Parcel src) { color = src.readInt(); stripe = src.readInt(); gap = src.readInt(); }
  public int getColor() { return color; }
  public int getStripeWidth() { return stripe; }
  public int getGapWidth() { return gap; }
  @Override public int getLeadingMargin(boolean first) { return stripe + gap; }
  @Override public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
    Paint.Style style = p.getStyle();
    int old = p.getColor();
    p.setStyle(Paint.Style.FILL);
    p.setColor(color);
    c.drawRect(x, top, x + dir * stripe, bottom, p);
    p.setStyle(style);
    p.setColor(old);
  }
  @Override public int getSpanTypeId() { return 9; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeInt(color); dest.writeInt(stripe); dest.writeInt(gap); }
}

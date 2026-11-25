package android.text.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Spanned;

public class BulletSpan implements LeadingMarginSpan, android.text.ParcelableSpan {
  public static final int STANDARD_GAP_WIDTH = 2;
  private final int gap, color, radius;
  private final boolean wantColor;
  public BulletSpan() { this(STANDARD_GAP_WIDTH, 0, false, 4); }
  public BulletSpan(int gapWidth) { this(gapWidth, 0, false, 4); }
  public BulletSpan(int gapWidth, int color) { this(gapWidth, color, true, 4); }
  public BulletSpan(int gapWidth, int color, int bulletRadius) { this(gapWidth, color, true, bulletRadius); }
  private BulletSpan(int gapWidth, int color, boolean wantColor, int radius) { gap = gapWidth; this.color = color; this.wantColor = wantColor; this.radius = radius; }
  public BulletSpan(android.os.Parcel src) { gap = src.readInt(); wantColor = src.readInt() != 0; color = src.readInt(); radius = src.readInt(); }
  public int getGapWidth() { return gap; }
  public int getBulletRadius() { return radius; }
  public int getColor() { return color; }
  @Override public int getLeadingMargin(boolean first) { return 2 * radius + gap; }
  @Override public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout l) {
    if (!(text instanceof Spanned) || ((Spanned) text).getSpanStart(this) != start) return;
    Paint.Style style = p.getStyle();
    int old = p.getColor();
    if (wantColor) p.setColor(color);
    p.setStyle(Paint.Style.FILL);
    c.drawCircle(x + dir * radius, (top + bottom) / 2f, radius, p);
    if (wantColor) p.setColor(old);
    p.setStyle(style);
  }
  @Override public int getSpanTypeId() { return 8; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeInt(gap); dest.writeInt(wantColor ? 1 : 0); dest.writeInt(color); dest.writeInt(radius); }
}

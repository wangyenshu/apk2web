package android.text.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Spanned;

public class DrawableMarginSpan implements LeadingMarginSpan, LineHeightSpan {
  private final Drawable drawable;
  private final int pad;
  public DrawableMarginSpan(Drawable d) { this(d, 0); }
  public DrawableMarginSpan(Drawable d, int pad) { drawable = d; this.pad = pad; }
  @Override public int getLeadingMargin(boolean first) { return drawable.getIntrinsicWidth() + pad; }
  @Override public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
    int st = ((Spanned) text).getSpanStart(this);
    int itop = layout.getLineTop(layout.getLineForOffset(st));
    drawable.setBounds(x, itop, x + drawable.getIntrinsicWidth(), itop + drawable.getIntrinsicHeight());
    drawable.draw(c);
  }
  @Override public void chooseHeight(CharSequence text, int start, int end, int istartv, int v, Paint.FontMetricsInt fm) {
    if (end == ((Spanned) text).getSpanEnd(this)) {
      int ht = drawable.getIntrinsicHeight();
      int need = ht - (v + fm.descent - fm.ascent - istartv);
      if (need > 0) fm.descent += need;
    }
  }
}

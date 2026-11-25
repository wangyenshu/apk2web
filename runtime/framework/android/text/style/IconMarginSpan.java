package android.text.style;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Spanned;

public class IconMarginSpan implements LeadingMarginSpan, LineHeightSpan {
  private final Bitmap bitmap;
  private final int pad;
  public IconMarginSpan(Bitmap b) { this(b, 0); }
  public IconMarginSpan(Bitmap b, int pad) { bitmap = b; this.pad = pad; }
  @Override public int getLeadingMargin(boolean first) { return bitmap.getWidth() + pad; }
  @Override public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
    int st = ((Spanned) text).getSpanStart(this);
    int itop = layout.getLineTop(layout.getLineForOffset(st));
    if (dir < 0) x -= bitmap.getWidth();
    c.drawBitmap(bitmap, x, itop, p);
  }
  @Override public void chooseHeight(CharSequence text, int start, int end, int istartv, int v, Paint.FontMetricsInt fm) {
    if (end == ((Spanned) text).getSpanEnd(this)) {
      int ht = bitmap.getHeight();
      int need = ht - (v + fm.descent - fm.ascent - istartv);
      if (need > 0) fm.descent += need;
      need = ht - (v + fm.bottom - fm.top - istartv);
      if (need > 0) fm.bottom += need;
    }
  }
}

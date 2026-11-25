package a2w;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.LeadingMarginSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;

/** Measures and draws one line of styled text run by run (CharacterStyle spans), for TextView and StaticLayout. */
public final class SpanText {
  private SpanText() {}

  public static boolean styled(CharSequence cs) {
    if (!(cs instanceof Spanned)) return false;
    Spanned sp = (Spanned) cs;
    for (Object o : sp.getSpans(0, sp.length(), Object.class)) if (o instanceof CharacterStyle || o instanceof LeadingMarginSpan) return true;
    return false;
  }
  /** the spans of a kind over [start, end) (as getSpans, without a typed array) */
  static CharacterStyle[] styles(Spanned sp, int start, int end) {
    java.util.ArrayList<CharacterStyle> l = new java.util.ArrayList<>();
    for (Object o : sp.getSpans(start, end, Object.class)) if (o instanceof CharacterStyle) l.add((CharacterStyle) o);
    return l.toArray(new CharacterStyle[0]);
  }
  static LeadingMarginSpan[] margins(Spanned sp, int start, int end) {
    java.util.ArrayList<LeadingMarginSpan> l = new java.util.ArrayList<>();
    for (Object o : sp.getSpans(start, end, Object.class)) if (o instanceof LeadingMarginSpan) l.add((LeadingMarginSpan) o);
    return l.toArray(new LeadingMarginSpan[0]);
  }

  static TextPaint runPaint(TextPaint base, CharacterStyle[] spans, boolean draw) {
    TextPaint tp = new TextPaint(base);
    tp.bgColor = 0;
    tp.baselineShift = 0;
    for (CharacterStyle cs : spans) {
      if (draw) cs.updateDrawState(tp);
      else if (cs instanceof MetricAffectingSpan) ((MetricAffectingSpan) cs).updateMeasureState(tp);
    }
    return tp;
  }
  static ReplacementSpan replacement(CharacterStyle[] spans) {
    for (int i = spans.length - 1; i >= 0; i--) if (spans[i] instanceof ReplacementSpan) return (ReplacementSpan) spans[i];
    return null;
  }

  /** leading margin (bullets, quotes, indents) of the line that starts at start */
  public static int margin(Spanned sp, int start, int end) {
    int m = 0;
    for (LeadingMarginSpan l : margins(sp, start, Math.max(start, end))) {
      int st = sp.getSpanStart(l);
      boolean first = st == start || st > 0 && st < sp.length() && paragraphStart(sp, start) <= st;
      m += l.getLeadingMargin(first);
    }
    return m;
  }
  private static int paragraphStart(Spanned sp, int at) { int i = at; while (i > 0 && sp.charAt(i - 1) != '\n') i--; return i; }

  public static float width(TextPaint base, Spanned sp, int start, int end) {
    float w = 0;
    for (int i = start, next; i < end; i = next) {
      next = sp.nextSpanTransition(i, end, CharacterStyle.class);
      CharacterStyle[] spans = styles(sp, i, next);
      ReplacementSpan r = replacement(spans);
      if (r != null) {
        int re = Math.min(end, sp.getSpanEnd(r));
        w += r.getSize(runPaint(base, spans, false), sp, Math.max(start, sp.getSpanStart(r)), re, null);
        next = Math.max(next, re);
        continue;
      }
      w += runPaint(base, spans, false).measureText(trimEnd(sp, i, next, end));
    }
    return w;
  }
  private static String trimEnd(Spanned sp, int i, int next, int end) {
    String s = sp.subSequence(i, next).toString();
    if (next == end) { int e = s.length(); while (e > 0 && (s.charAt(e - 1) == '\n' || s.charAt(e - 1) == ' ')) e--; s = s.substring(0, e); }
    return s;
  }

  /** the line's ascent/descent grown to its largest run (headings, big text, images) */
  public static void metrics(TextPaint base, Spanned sp, int start, int end, Paint.FontMetricsInt fm) {
    for (int i = start, next; i < end; i = next) {
      next = sp.nextSpanTransition(i, end, CharacterStyle.class);
      CharacterStyle[] spans = styles(sp, i, next);
      if (spans.length == 0) continue;
      TextPaint tp = runPaint(base, spans, false);
      Paint.FontMetricsInt m = new Paint.FontMetricsInt();
      ReplacementSpan r = replacement(spans);
      if (r != null) {
        tp.getFontMetricsInt(m);
        r.getSize(tp, sp, Math.max(start, sp.getSpanStart(r)), Math.min(end, sp.getSpanEnd(r)), m);
        next = Math.max(next, Math.min(end, sp.getSpanEnd(r)));
      } else tp.getFontMetricsInt(m);
      int shift = tp.baselineShift;
      fm.ascent = Math.min(fm.ascent, m.ascent + shift);
      fm.top = Math.min(fm.top, m.top + shift);
      fm.descent = Math.max(fm.descent, m.descent + shift);
      fm.bottom = Math.max(fm.bottom, m.bottom + shift);
    }
  }

  /** draws [start, end) of sp at x on the baseline y; top/bottom bound the line (backgrounds, images) */
  public static void draw(Canvas c, TextPaint base, Spanned sp, int start, int end, float x, int top, int y, int bottom) {
    for (int i = start, next; i < end; i = next) {
      next = sp.nextSpanTransition(i, end, CharacterStyle.class);
      CharacterStyle[] spans = styles(sp, i, next);
      TextPaint tp = runPaint(base, spans, true);
      ReplacementSpan r = replacement(spans);
      if (r != null) {
        int rs = Math.max(start, sp.getSpanStart(r)), re = Math.min(end, sp.getSpanEnd(r));
        float w = r.getSize(tp, sp, rs, re, null);
        r.draw(c, sp, rs, re, x, top, y, bottom, tp);
        x += w;
        next = Math.max(next, re);
        continue;
      }
      String s = trimEnd(sp, i, next, end);
      float w = tp.measureText(s);
      if (tp.bgColor != 0) {
        int old = tp.getColor();
        Paint.Style st = tp.getStyle();
        tp.setColor(tp.bgColor);
        tp.setStyle(Paint.Style.FILL);
        c.drawRect(x, top, x + w, bottom, tp);
        tp.setColor(old);
        tp.setStyle(st);
      }
      if (!s.isEmpty()) c.drawText(s, x, y + tp.baselineShift, tp);
      x += w;
    }
  }

  /** the ParagraphStyle margins of the line: draws them and returns how far the text moves right */
  public static int drawMargins(Canvas c, TextPaint base, Spanned sp, int start, int end, int top, int baseline, int bottom, android.text.Layout layout) {
    int x = 0;
    for (LeadingMarginSpan l : margins(sp, start, Math.max(start, end))) {
      boolean first = sp.getSpanStart(l) == start || paragraphStart(sp, start) == start;
      l.drawLeadingMargin(c, base, x, 1, top, baseline, bottom, sp, start, end, first, layout);
      x += l.getLeadingMargin(first);
    }
    return x;
  }
}

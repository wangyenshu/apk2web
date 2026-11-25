package android.text;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * android.text.Html: HTML to styled text (the tags Android's own converter knows) and back. Unknown tags go to the
 * TagHandler. Entities: the named ones of HTML 4 that matter in practice, and every numeric one.
 */
public class Html {
  public interface ImageGetter { Drawable getDrawable(String source); }
  public interface TagHandler { void handleTag(boolean opening, String tag, Editable output, org.xml.sax.XMLReader xmlReader); }

  public static final int FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH = 1, FROM_HTML_SEPARATOR_LINE_BREAK_HEADING = 2,
      FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM = 4, FROM_HTML_SEPARATOR_LINE_BREAK_LIST = 8, FROM_HTML_SEPARATOR_LINE_BREAK_DIV = 16,
      FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE = 32, FROM_HTML_OPTION_USE_CSS_COLORS = 256, FROM_HTML_MODE_LEGACY = 0,
      FROM_HTML_MODE_COMPACT = 63, TO_HTML_PARAGRAPH_LINES_CONSECUTIVE = 0, TO_HTML_PARAGRAPH_LINES_INDIVIDUAL = 1;

  private Html() {}

  @Deprecated public static Spanned fromHtml(String source) { return fromHtml(source, FROM_HTML_MODE_LEGACY, null, null); }
  public static Spanned fromHtml(String source, int flags) { return fromHtml(source, flags, null, null); }
  @Deprecated public static Spanned fromHtml(String source, ImageGetter g, TagHandler h) { return fromHtml(source, FROM_HTML_MODE_LEGACY, g, h); }
  public static Spanned fromHtml(String source, int flags, ImageGetter g, TagHandler h) {
    return new Converter(source == null ? "" : source, flags, g, h).convert();
  }

  @Deprecated public static String toHtml(Spanned text) { return toHtml(text, TO_HTML_PARAGRAPH_LINES_CONSECUTIVE); }
  public static String toHtml(Spanned text, int option) {
    StringBuilder out = new StringBuilder();
    String s = text.toString();
    int n = s.length(), next;
    for (int i = 0; i < n; i = next) {
      next = s.indexOf('\n', i);
      if (next < 0) next = n;
      int nl = 0;
      while (next < n && s.charAt(next) == '\n') { nl++; next++; }
      int end = next - nl;
      out.append(option == TO_HTML_PARAGRAPH_LINES_INDIVIDUAL ? "<p dir=\"ltr\">" : "<p dir=\"ltr\">");
      withinParagraph(out, text, i, end);
      if (option == TO_HTML_PARAGRAPH_LINES_INDIVIDUAL) { out.append("</p>\n"); for (int k = 1; k < nl; k++) out.append("<br>\n"); }
      else { for (int k = 1; k < nl; k++) out.append("<br>"); out.append("</p>\n"); }
    }
    return out.toString();
  }
  private static void withinParagraph(StringBuilder out, Spanned text, int start, int end) {
    int next;
    for (int i = start; i < end; i = next) {
      next = text.nextSpanTransition(i, end, CharacterStyle.class);
      CharacterStyle[] style = text.getSpans(i, next, CharacterStyle.class);
      for (CharacterStyle cs : style) out.append(openTag(cs));
      withinStyle(out, text, i, next);
      for (int j = style.length - 1; j >= 0; j--) out.append(closeTag(style[j]));
    }
  }
  private static String openTag(CharacterStyle cs) {
    if (cs instanceof StyleSpan) { int s = ((StyleSpan) cs).getStyle(); return ((s & Typeface.BOLD) != 0 ? "<b>" : "") + ((s & Typeface.ITALIC) != 0 ? "<i>" : ""); }
    if (cs instanceof TypefaceSpan && "monospace".equals(((TypefaceSpan) cs).getFamily())) return "<tt>";
    if (cs instanceof SuperscriptSpan) return "<sup>";
    if (cs instanceof SubscriptSpan) return "<sub>";
    if (cs instanceof UnderlineSpan) return "<u>";
    if (cs instanceof StrikethroughSpan) return "<span style=\"text-decoration:line-through;\">";
    if (cs instanceof URLSpan) return "<a href=\"" + ((URLSpan) cs).getURL() + "\">";
    if (cs instanceof ImageSpan) return "<img src=\"" + ((ImageSpan) cs).getSource() + "\">";
    if (cs instanceof RelativeSizeSpan) return "<span style=\"font-size:" + ((RelativeSizeSpan) cs).getSizeChange() + "em;\">";
    if (cs instanceof ForegroundColorSpan) return String.format("<span style=\"color:#%06X;\">", ((ForegroundColorSpan) cs).getForegroundColor() & 0xFFFFFF);
    if (cs instanceof BackgroundColorSpan) return String.format("<span style=\"background-color:#%06X;\">", ((BackgroundColorSpan) cs).getBackgroundColor() & 0xFFFFFF);
    return "";
  }
  private static String closeTag(CharacterStyle cs) {
    if (cs instanceof StyleSpan) { int s = ((StyleSpan) cs).getStyle(); return ((s & Typeface.ITALIC) != 0 ? "</i>" : "") + ((s & Typeface.BOLD) != 0 ? "</b>" : ""); }
    if (cs instanceof TypefaceSpan) return "monospace".equals(((TypefaceSpan) cs).getFamily()) ? "</tt>" : "";
    if (cs instanceof SuperscriptSpan) return "</sup>";
    if (cs instanceof SubscriptSpan) return "</sub>";
    if (cs instanceof UnderlineSpan) return "</u>";
    if (cs instanceof URLSpan) return "</a>";
    if (cs instanceof StrikethroughSpan || cs instanceof RelativeSizeSpan || cs instanceof ForegroundColorSpan || cs instanceof BackgroundColorSpan) return "</span>";
    return "";
  }
  private static void withinStyle(StringBuilder out, CharSequence text, int start, int end) {
    for (int i = start; i < end; i++) {
      char c = text.charAt(i);
      if (c == '<') out.append("&lt;");
      else if (c == '>') out.append("&gt;");
      else if (c == '&') out.append("&amp;");
      else if (Character.isHighSurrogate(c) && i + 1 < end && Character.isLowSurrogate(text.charAt(i + 1))) { out.append("&#").append(Character.toCodePoint(c, text.charAt(i + 1))).append(';'); i++; }
      else if (c > 0x7E || c < ' ') out.append("&#").append((int) c).append(';');
      else if (c == ' ') { while (i + 1 < end && text.charAt(i + 1) == ' ') { out.append("&nbsp;"); i++; } out.append(' '); }
      else out.append(c);
    }
  }
  public static String escapeHtml(CharSequence text) { StringBuilder out = new StringBuilder(); withinStyle(out, text, 0, text.length()); return out.toString(); }

  // ---------------------------------------------------------------- the converter
  static final HashMap<String, Integer> ENTITIES = new HashMap<>();
  static {
    String[] e = {"quot", "34", "amp", "38", "apos", "39", "lt", "60", "gt", "62", "nbsp", "160", "iexcl", "161", "cent", "162", "pound", "163",
        "curren", "164", "yen", "165", "brvbar", "166", "sect", "167", "uml", "168", "copy", "169", "ordf", "170", "laquo", "171", "not", "172",
        "shy", "173", "reg", "174", "macr", "175", "deg", "176", "plusmn", "177", "sup2", "178", "sup3", "179", "acute", "180", "micro", "181",
        "para", "182", "middot", "183", "cedil", "184", "sup1", "185", "ordm", "186", "raquo", "187", "frac14", "188", "frac12", "189",
        "frac34", "190", "iquest", "191", "Agrave", "192", "Aacute", "193", "Acirc", "194", "Atilde", "195", "Auml", "196", "Aring", "197",
        "AElig", "198", "Ccedil", "199", "Egrave", "200", "Eacute", "201", "Ecirc", "202", "Euml", "203", "Igrave", "204", "Iacute", "205",
        "Icirc", "206", "Iuml", "207", "ETH", "208", "Ntilde", "209", "Ograve", "210", "Oacute", "211", "Ocirc", "212", "Otilde", "213",
        "Ouml", "214", "times", "215", "Oslash", "216", "Ugrave", "217", "Uacute", "218", "Ucirc", "219", "Uuml", "220", "Yacute", "221",
        "THORN", "222", "szlig", "223", "agrave", "224", "aacute", "225", "acirc", "226", "atilde", "227", "auml", "228", "aring", "229",
        "aelig", "230", "ccedil", "231", "egrave", "232", "eacute", "233", "ecirc", "234", "euml", "235", "igrave", "236", "iacute", "237",
        "icirc", "238", "iuml", "239", "eth", "240", "ntilde", "241", "ograve", "242", "oacute", "243", "ocirc", "244", "otilde", "245",
        "ouml", "246", "divide", "247", "oslash", "248", "ugrave", "249", "uacute", "250", "ucirc", "251", "uuml", "252", "yacute", "253",
        "thorn", "254", "yuml", "255", "OElig", "338", "oelig", "339", "Scaron", "352", "scaron", "353", "Yuml", "376", "fnof", "402",
        "circ", "710", "tilde", "732", "Alpha", "913", "Beta", "914", "Gamma", "915", "Delta", "916", "Omega", "937", "alpha", "945",
        "beta", "946", "gamma", "947", "delta", "948", "epsilon", "949", "lambda", "955", "mu", "956", "pi", "960", "sigma", "963",
        "omega", "969", "ensp", "8194", "emsp", "8195", "thinsp", "8201", "zwnj", "8204", "zwj", "8205", "lrm", "8206", "rlm", "8207",
        "ndash", "8211", "mdash", "8212", "lsquo", "8216", "rsquo", "8217", "sbquo", "8218", "ldquo", "8220", "rdquo", "8221", "bdquo", "8222",
        "dagger", "8224", "Dagger", "8225", "bull", "8226", "hellip", "8230", "permil", "8240", "prime", "8242", "Prime", "8243",
        "lsaquo", "8249", "rsaquo", "8250", "oline", "8254", "euro", "8364", "trade", "8482", "larr", "8592", "uarr", "8593", "rarr", "8594",
        "darr", "8595", "harr", "8596", "rArr", "8658", "hArr", "8660", "minus", "8722", "infin", "8734", "ne", "8800", "le", "8804",
        "ge", "8805", "asymp", "8776", "spades", "9824", "clubs", "9827", "hearts", "9829", "diams", "9830", "loz", "9674", "check", "10003"};
    for (int i = 0; i < e.length; i += 2) ENTITIES.put(e[i], Integer.parseInt(e[i + 1]));
  }
  /** decodes character references in s ("&amp;", "&#8226;", "&#x2022;"); unknown ones stay as written */
  public static String decodeEntities(String s) {
    if (s.indexOf('&') < 0) return s;
    StringBuilder out = new StringBuilder(s.length());
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c != '&') { out.append(c); continue; }
      int semi = s.indexOf(';', i);
      if (semi < 0 || semi - i > 12) { out.append(c); continue; }
      String name = s.substring(i + 1, semi);
      int cp = -1;
      try {
        if (name.startsWith("#x") || name.startsWith("#X")) cp = Integer.parseInt(name.substring(2), 16);
        else if (name.startsWith("#")) cp = Integer.parseInt(name.substring(1));
        else { Integer v = ENTITIES.get(name); if (v != null) cp = v; }
      } catch (NumberFormatException e) { cp = -1; }
      if (cp < 0 || cp > 0x10FFFF) { out.append(c); continue; }
      out.appendCodePoint(cp);
      i = semi;
    }
    return out.toString();
  }

  static final float[] HEADING_SIZES = {1.5f, 1.4f, 1.3f, 1.2f, 1.1f, 1f};

  static final class Converter {
    final String src;
    final int flags;
    final ImageGetter images;
    final TagHandler handler;
    final SpannableStringBuilder out = new SpannableStringBuilder();
    final ArrayList<Object[]> open = new ArrayList<>();   // {tag, mark start, extra}
    Converter(String src, int flags, ImageGetter g, TagHandler h) { this.src = src; this.flags = flags; images = g; handler = h; }

    Spanned convert() {
      int i = 0, n = src.length();
      StringBuilder text = new StringBuilder();
      while (i < n) {
        char c = src.charAt(i);
        if (c == '<') {
          if (src.startsWith("<!--", i)) { int e = src.indexOf("-->", i + 4); i = e < 0 ? n : e + 3; continue; }
          int e = tagEnd(i);
          if (e < 0) { text.append(c); i++; continue; }
          flushText(text);
          tag(src.substring(i + 1, e));
          i = e + 1;
          continue;
        }
        text.append(c);
        i++;
      }
      flushText(text);
      // close what was left open, then turn paragraph-style spans' marks into real spans
      for (int k = open.size() - 1; k >= 0; k--) end((String) open.get(k)[0]);
      // trailing newlines of block elements are kept, as Android does
      return out;
    }
    int tagEnd(int lt) {
      char q = 0;
      for (int j = lt + 1; j < src.length(); j++) {
        char c = src.charAt(j);
        if (q != 0) { if (c == q) q = 0; continue; }
        if (c == '"' || c == '\'') q = c;
        else if (c == '>') return j;
        else if (c == '<') return -1;
      }
      return -1;
    }
    void flushText(StringBuilder text) {
      if (text.length() == 0) return;
      String s = decodeEntities(text.toString());
      text.setLength(0);
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
          int len = sb.length();
          char pred;
          if (len == 0) {
            len = out.length();
            pred = len == 0 ? '\n' : out.charAt(len - 1);
          } else pred = sb.charAt(len - 1);
          if (pred != ' ' && pred != '\n') sb.append(' ');
        } else sb.append(c);
      }
      out.append(sb);
    }
    static String attr(String tag, String name) {
      String low = tag.toLowerCase(Locale.ROOT);
      int i = 0;
      while ((i = low.indexOf(name, i)) >= 0) {
        boolean startOk = i > 0 && Character.isWhitespace(low.charAt(i - 1));
        int j = i + name.length();
        while (j < low.length() && low.charAt(j) == ' ') j++;
        if (startOk && j < low.length() && low.charAt(j) == '=') {
          j++;
          while (j < low.length() && low.charAt(j) == ' ') j++;
          if (j >= tag.length()) return "";
          char q = tag.charAt(j);
          if (q == '"' || q == '\'') { int e = tag.indexOf(q, j + 1); return decodeEntities(tag.substring(j + 1, e < 0 ? tag.length() : e)); }
          int e = j;
          while (e < tag.length() && !Character.isWhitespace(tag.charAt(e)) && tag.charAt(e) != '/') e++;
          return decodeEntities(tag.substring(j, e));
        }
        i = j;
      }
      return null;
    }
    void tag(String body) {
      body = body.trim();
      if (body.isEmpty() || body.charAt(0) == '!' || body.charAt(0) == '?') return;
      boolean closing = body.charAt(0) == '/';
      if (closing) body = body.substring(1).trim();
      boolean selfClosing = body.endsWith("/");
      if (selfClosing) body = body.substring(0, body.length() - 1).trim();
      int sp = 0;
      while (sp < body.length() && !Character.isWhitespace(body.charAt(sp))) sp++;
      String name = body.substring(0, sp).toLowerCase(Locale.ROOT);
      if (closing) { end(name); return; }
      start(name, body);
      if (selfClosing && !name.equals("br") && !name.equals("img")) end(name);
    }
    int margin(int flag) { return (flags & flag) != 0 ? 1 : 2; }
    void blockBreak(int lines) {
      int len = out.length();
      if (len == 0) return;
      int have = 0;
      for (int i = len - 1; i >= 0 && out.charAt(i) == '\n'; i--) have++;
      while (have < lines) { out.append('\n'); have++; }
    }
    void push(String tag, Object extra) { open.add(new Object[] {tag, out.length(), extra}); }
    void start(String t, String body) {
      switch (t) {
        case "br": out.append('\n'); return;
        case "p": blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH)); push(t, style(body)); return;
        case "div": blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_DIV)); push(t, style(body)); return;
        case "ul": blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_LIST)); push(t, null); return;
        case "li": blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM)); push(t, style(body)); return;
        case "blockquote": blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE)); push(t, null); return;
        case "h1": case "h2": case "h3": case "h4": case "h5": case "h6": blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_HEADING)); push(t, null); return;
        case "img": image(attr(body, "src")); return;
        case "a": push(t, attr(body, "href")); return;
        case "font": push(t, new String[] {attr(body, "color"), attr(body, "face")}); return;
        case "span": push(t, style(body)); return;
        case "b": case "strong": case "i": case "em": case "cite": case "dfn": case "u": case "ins": case "s": case "strike": case "del":
        case "big": case "small": case "tt": case "code": case "sup": case "sub":
          push(t, null); return;
        default:
          push(t, null);
          if (handler != null) handler.handleTag(true, t, out, null);
      }
    }
    void end(String t) {
      int k = open.size() - 1;
      while (k >= 0 && !open.get(k)[0].equals(t)) k--;
      if (k < 0) { if (t.equals("br")) return; if (handler != null && !known(t)) handler.handleTag(false, t, out, null); return; }
      // implicitly close what was opened inside it
      while (open.size() - 1 > k) end((String) open.get(open.size() - 1)[0]);
      Object[] o = open.remove(k);
      int st = (Integer) o[1];
      Object extra = o[2];
      int en = out.length();
      switch (t) {
        case "b": case "strong": span(new StyleSpan(Typeface.BOLD), st, en); break;
        case "i": case "em": case "cite": case "dfn": span(new StyleSpan(Typeface.ITALIC), st, en); break;
        case "u": case "ins": span(new UnderlineSpan(), st, en); break;
        case "s": case "strike": case "del": span(new StrikethroughSpan(), st, en); break;
        case "big": span(new RelativeSizeSpan(1.25f), st, en); break;
        case "small": span(new RelativeSizeSpan(0.8f), st, en); break;
        case "tt": case "code": span(new TypefaceSpan("monospace"), st, en); break;
        case "sup": span(new SuperscriptSpan(), st, en); break;
        case "sub": span(new SubscriptSpan(), st, en); break;
        case "a": if (extra != null) span(new URLSpan((String) extra), st, en); break;
        case "font": {
          String[] f = (String[]) extra;
          if (f[0] != null) { int c = color(f[0]); if (c != -1) span(new ForegroundColorSpan(c | 0xFF000000), st, en); }
          if (f[1] != null) span(new TypefaceSpan(f[1]), st, en);
          break;
        }
        case "span": cssSpans((String[]) extra, st, en); break;
        case "p": cssSpans((String[]) extra, st, en); blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH)); break;
        case "div": cssSpans((String[]) extra, st, en); blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_DIV)); break;
        case "ul": blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_LIST)); break;
        case "li": cssSpans((String[]) extra, st, en); blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM)); span(new BulletSpan(), st, out.length()); break;
        case "blockquote": blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE)); span(new QuoteSpan(), st, out.length()); break;
        case "h1": case "h2": case "h3": case "h4": case "h5": case "h6": {
          span(new RelativeSizeSpan(HEADING_SIZES[t.charAt(1) - '1']), st, en);
          span(new StyleSpan(Typeface.BOLD), st, en);
          blockBreak(margin(FROM_HTML_SEPARATOR_LINE_BREAK_HEADING));
          break;
        }
        default: if (handler != null) handler.handleTag(false, t, out, null);
      }
    }
    static boolean known(String t) {
      switch (t) {
        case "p": case "div": case "ul": case "li": case "blockquote": case "h1": case "h2": case "h3": case "h4": case "h5": case "h6":
        case "img": case "a": case "font": case "span": case "b": case "strong": case "i": case "em": case "cite": case "dfn": case "u":
        case "ins": case "s": case "strike": case "del": case "big": case "small": case "tt": case "code": case "sup": case "sub": return true;
        default: return false;
      }
    }
    void span(Object what, int st, int en) { if (en > st) out.setSpan(what, st, en, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); }
    void image(String src) {
      Drawable d = null;
      if (images != null && src != null) d = images.getDrawable(src);
      if (d == null) {
        d = new android.graphics.drawable.ColorDrawable(0x00000000);
        d.setBounds(0, 0, 0, 0);
      }
      int st = out.length();
      out.append("￼");
      out.setSpan(new ImageSpan(d, src), st, out.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    /** {color, background-color, text-decoration, text-align, font-size} of a style="" attribute */
    static String[] style(String body) {
      String s = attr(body, "style");
      if (s == null) return null;
      String[] r = new String[5];
      for (String decl : s.split(";")) {
        int c = decl.indexOf(':');
        if (c < 0) continue;
        String k = decl.substring(0, c).trim().toLowerCase(Locale.ROOT), v = decl.substring(c + 1).trim();
        switch (k) {
          case "color": r[0] = v; break;
          case "background-color": case "background": r[1] = v; break;
          case "text-decoration": r[2] = v.toLowerCase(Locale.ROOT); break;
          case "text-align": r[3] = v.toLowerCase(Locale.ROOT); break;
          case "font-size": r[4] = v.toLowerCase(Locale.ROOT); break;
          default:
        }
      }
      return r;
    }
    void cssSpans(String[] css, int st, int en) {
      if (css == null) return;
      if (css[0] != null) { int c = color(css[0]); if (c != -1) span(new ForegroundColorSpan(c | 0xFF000000), st, en); }
      if (css[1] != null) { int c = color(css[1]); if (c != -1) span(new BackgroundColorSpan(c | 0xFF000000), st, en); }
      if (css[2] != null && css[2].contains("line-through")) span(new StrikethroughSpan(), st, en);
      if (css[2] != null && css[2].contains("underline")) span(new UnderlineSpan(), st, en);
      if (css[3] != null) {
        android.text.Layout.Alignment a = css[3].equals("center") ? android.text.Layout.Alignment.ALIGN_CENTER
            : css[3].equals("end") || css[3].equals("right") ? android.text.Layout.Alignment.ALIGN_OPPOSITE : android.text.Layout.Alignment.ALIGN_NORMAL;
        span(new AlignmentSpan.Standard(a), st, en);
      }
      if (css[4] != null) {
        try {
          if (css[4].endsWith("em")) span(new RelativeSizeSpan(Float.parseFloat(css[4].substring(0, css[4].length() - 2))), st, en);
          else if (css[4].endsWith("px")) span(new AbsoluteSizeSpan(Math.round(Float.parseFloat(css[4].substring(0, css[4].length() - 2))), true), st, en);
        } catch (NumberFormatException e) { /* ignored, as Android does */ }
      }
    }
    static int color(String c) {
      c = c.trim();
      try {
        if (c.startsWith("#")) {
          String h = c.substring(1);
          if (h.length() == 3) h = "" + h.charAt(0) + h.charAt(0) + h.charAt(1) + h.charAt(1) + h.charAt(2) + h.charAt(2);
          return (int) Long.parseLong(h, 16) & 0xFFFFFF;
        }
        if (c.startsWith("0x") || c.startsWith("0X")) return (int) Long.parseLong(c.substring(2), 16) & 0xFFFFFF;
        if (c.startsWith("rgb(")) {
          String[] p = c.substring(4, c.indexOf(')')).split(",");
          return Integer.parseInt(p[0].trim()) << 16 | Integer.parseInt(p[1].trim()) << 8 | Integer.parseInt(p[2].trim());
        }
        return Color.parseColor(c.toLowerCase(Locale.ROOT)) & 0xFFFFFF;
      } catch (RuntimeException e) { return -1; }
    }
  }
}

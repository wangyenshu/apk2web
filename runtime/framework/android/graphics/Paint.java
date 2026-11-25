package android.graphics;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.Float32Array;
import org.teavm.jso.typedarrays.Int32Array;

public class Paint {
  public static final int ANTI_ALIAS_FLAG = 1, FILTER_BITMAP_FLAG = 2, DITHER_FLAG = 4, UNDERLINE_TEXT_FLAG = 8,
      STRIKE_THRU_TEXT_FLAG = 16, FAKE_BOLD_TEXT_FLAG = 32, LINEAR_TEXT_FLAG = 64, SUBPIXEL_TEXT_FLAG = 128, DEV_KERN_TEXT_FLAG = 256;
  public enum Style { FILL, STROKE, FILL_AND_STROKE }
  public enum Cap { BUTT, ROUND, SQUARE }
  public enum Join { MITER, ROUND, BEVEL }
  public enum Align { LEFT, CENTER, RIGHT }

  public static class FontMetrics { public float top, ascent, descent, bottom, leading; }
  public static class FontMetricsInt {
    public int top, ascent, descent, bottom, leading;
    public String toString() { return "FontMetricsInt: top=" + top + " ascent=" + ascent + " descent=" + descent + " bottom=" + bottom + " leading=" + leading; }
  }

  int flags, color = 0xff000000;
  Style style = Style.FILL;
  float strokeWidth, strokeMiter = 4, textSize = 12, textScaleX = 1, textSkewX;
  Cap cap = Cap.BUTT; Join join = Join.MITER;
  Typeface typeface;
  Align align = Align.LEFT;
  float shadowRadius, shadowDx, shadowDy; int shadowColor;
  ColorFilter colorFilter; Shader shader; Xfermode xfermode; MaskFilter maskFilter; PathEffect pathEffect;
  private JSObject js;
  private boolean dirty = true;

  public Paint() { this(0); }
  public Paint(int flags) { this.flags = flags | DEV_KERN_TEXT_FLAG; }
  public Paint(Paint p) { set(p); }
  public void set(Paint p) {
    if (p == this) return;
    flags = p.flags; color = p.color; style = p.style; strokeWidth = p.strokeWidth; strokeMiter = p.strokeMiter; textSize = p.textSize;
    textScaleX = p.textScaleX; textSkewX = p.textSkewX; cap = p.cap; join = p.join; typeface = p.typeface; align = p.align;
    shadowRadius = p.shadowRadius; shadowDx = p.shadowDx; shadowDy = p.shadowDy; shadowColor = p.shadowColor;
    colorFilter = p.colorFilter; shader = p.shader; xfermode = p.xfermode; maskFilter = p.maskFilter; pathEffect = p.pathEffect;
    letterSpacing = p.letterSpacing; wordSpacing = p.wordSpacing; fontFeatureSettings = p.fontFeatureSettings; fontVariationSettings = p.fontVariationSettings;
    elegantTextHeight = p.elegantTextHeight; locales = p.locales; hinting = p.hinting; blendMode = p.blendMode; startHyphenEdit = p.startHyphenEdit;
    endHyphenEdit = p.endHyphenEdit;
    dirty = true;
  }
  public void reset() { set(new Paint()); }
  private void d() { dirty = true; }

  public int getFlags() { return flags; }
  public void setFlags(int f) { flags = f; d(); }
  public final boolean isAntiAlias() { return (flags & ANTI_ALIAS_FLAG) != 0; }
  public void setAntiAlias(boolean a) { flags = a ? flags | ANTI_ALIAS_FLAG : flags & ~ANTI_ALIAS_FLAG; d(); }
  public final boolean isDither() { return (flags & DITHER_FLAG) != 0; }
  public void setDither(boolean b) { flags = b ? flags | DITHER_FLAG : flags & ~DITHER_FLAG; }
  public final boolean isFilterBitmap() { return (flags & FILTER_BITMAP_FLAG) != 0; }
  public void setFilterBitmap(boolean b) { flags = b ? flags | FILTER_BITMAP_FLAG : flags & ~FILTER_BITMAP_FLAG; d(); }
  public final boolean isFakeBoldText() { return (flags & FAKE_BOLD_TEXT_FLAG) != 0; }
  public void setFakeBoldText(boolean b) { flags = b ? flags | FAKE_BOLD_TEXT_FLAG : flags & ~FAKE_BOLD_TEXT_FLAG; d(); }
  public final boolean isUnderlineText() { return (flags & UNDERLINE_TEXT_FLAG) != 0; }
  public void setUnderlineText(boolean b) { flags = b ? flags | UNDERLINE_TEXT_FLAG : flags & ~UNDERLINE_TEXT_FLAG; d(); }
  public final boolean isStrikeThruText() { return (flags & STRIKE_THRU_TEXT_FLAG) != 0; }
  public void setStrikeThruText(boolean b) { flags = b ? flags | STRIKE_THRU_TEXT_FLAG : flags & ~STRIKE_THRU_TEXT_FLAG; d(); }
  public void setLinearText(boolean b) { flags = b ? flags | LINEAR_TEXT_FLAG : flags & ~LINEAR_TEXT_FLAG; }
  public void setSubpixelText(boolean b) { flags = b ? flags | SUBPIXEL_TEXT_FLAG : flags & ~SUBPIXEL_TEXT_FLAG; }
  public final boolean isLinearText() { return (flags & LINEAR_TEXT_FLAG) != 0; }
  public final boolean isSubpixelText() { return (flags & SUBPIXEL_TEXT_FLAG) != 0; }
  public int getColor() { return color; }
  public void setColor(int c) { color = c; d(); }
  public int getAlpha() { return color >>> 24; }
  public void setAlpha(int a) { color = (color & 0xffffff) | ((a & 0xff) << 24); d(); }
  public void setARGB(int a, int r, int g, int b) { setColor(Color.argb(a, r, g, b)); }
  public Style getStyle() { return style; }
  public void setStyle(Style s) { style = s; d(); }
  public float getStrokeWidth() { return strokeWidth; }
  public void setStrokeWidth(float w) { strokeWidth = w; d(); }
  public float getStrokeMiter() { return strokeMiter; }
  public void setStrokeMiter(float m) { strokeMiter = m; d(); }
  public Cap getStrokeCap() { return cap; }
  public void setStrokeCap(Cap c) { cap = c; d(); }
  public Join getStrokeJoin() { return join; }
  public void setStrokeJoin(Join j) { join = j; d(); }
  public float getTextSize() { return textSize; }
  public void setTextSize(float s) { if (s > 0) { textSize = s; d(); } }
  public float getTextScaleX() { return textScaleX; }
  public void setTextScaleX(float s) { textScaleX = s; d(); }
  public float getTextSkewX() { return textSkewX; }
  public void setTextSkewX(float s) { textSkewX = s; d(); }
  public Typeface getTypeface() { return typeface; }
  public Typeface setTypeface(Typeface t) { typeface = t; d(); return t; }
  public Align getTextAlign() { return align; }
  public void setTextAlign(Align a) { align = a; d(); }
  public void setShadowLayer(float r, float dx, float dy, int c) { shadowRadius = r; shadowDx = dx; shadowDy = dy; shadowColor = c; d(); }
  public void clearShadowLayer() { setShadowLayer(0, 0, 0, 0); }
  public ColorFilter getColorFilter() { return colorFilter; }
  public ColorFilter setColorFilter(ColorFilter f) { colorFilter = f; d(); return f; }
  public Shader getShader() { return shader; }
  public Shader setShader(Shader s) { shader = s; d(); return s; }
  public Xfermode getXfermode() { return xfermode; }
  public Xfermode setXfermode(Xfermode x) { xfermode = x; d(); return x; }
  public MaskFilter getMaskFilter() { return maskFilter; }
  public MaskFilter setMaskFilter(MaskFilter m) { maskFilter = m; return m; }
  public PathEffect getPathEffect() { return pathEffect; }
  public PathEffect setPathEffect(PathEffect p) { pathEffect = p; d(); return p; }
  public static final int HINTING_OFF = 0, HINTING_ON = 1;
  int hinting = HINTING_ON;
  public void setHinting(int mode) { hinting = mode; }
  public int getHinting() { return hinting; }
  android.os.LocaleList locales = android.os.LocaleList.getDefault();
  public void setTextLocale(java.util.Locale locale) { if (locale == null) throw new IllegalArgumentException("locale cannot be null"); locales = new android.os.LocaleList(locale); }
  public java.util.Locale getTextLocale() { return locales.get(0); }
  public void setTextLocales(android.os.LocaleList l) { if (l == null || l.isEmpty()) throw new IllegalArgumentException("locales cannot be null or empty"); locales = l; }
  public android.os.LocaleList getTextLocales() { return locales; }

  // ---- text settings: spacing reaches the page's canvas; font features, variations and hyphen edits are kept
  float letterSpacing, wordSpacing;
  String fontFeatureSettings, fontVariationSettings;
  boolean elegantTextHeight;
  int startHyphenEdit, endHyphenEdit;
  public float getLetterSpacing() { return letterSpacing; }
  public void setLetterSpacing(float em) { letterSpacing = em; d(); }
  public float getWordSpacing() { return wordSpacing; }
  public void setWordSpacing(float px) { wordSpacing = px; d(); }
  public String getFontFeatureSettings() { return fontFeatureSettings; }
  public void setFontFeatureSettings(String settings) { fontFeatureSettings = settings == null || settings.isEmpty() ? null : settings; }
  public String getFontVariationSettings() { return fontVariationSettings; }
  /** settings of the form 'wght' 700, 'ital' 1; false (and unchanged) when they do not parse */
  public boolean setFontVariationSettings(String settings) {
    if (settings == null || settings.isEmpty()) { fontVariationSettings = null; return true; }
    for (String part : settings.split(",")) {
      String t = part.trim();
      if (!t.matches("(['\"]).{4}\\1\\s+[-+]?(\\d+\\.?\\d*|\\.\\d+)")) throw new IllegalArgumentException("Failed to parse font variation settings.");
    }
    fontVariationSettings = settings;
    return true;
  }
  public boolean isElegantTextHeight() { return elegantTextHeight; }
  public void setElegantTextHeight(boolean elegant) { elegantTextHeight = elegant; }
  public int getStartHyphenEdit() { return startHyphenEdit; }
  public void setStartHyphenEdit(int startHyphen) { startHyphenEdit = startHyphen; }
  public int getEndHyphenEdit() { return endHyphenEdit; }
  public void setEndHyphenEdit(int endHyphen) { endHyphenEdit = endHyphen; }
  public boolean equalsForTextMeasurement(Paint other) {
    return textSize == other.textSize && textScaleX == other.textScaleX && textSkewX == other.textSkewX && letterSpacing == other.letterSpacing
        && wordSpacing == other.wordSpacing && typeface == other.typeface && (flags & ~(ANTI_ALIAS_FLAG | DITHER_FLAG | FILTER_BITMAP_FLAG)) == (other.flags & ~(ANTI_ALIAS_FLAG | DITHER_FLAG | FILTER_BITMAP_FLAG))
        && java.util.Objects.equals(fontFeatureSettings, other.fontFeatureSettings) && java.util.Objects.equals(fontVariationSettings, other.fontVariationSettings)
        && locales.equals(other.locales) && elegantTextHeight == other.elegantTextHeight && startHyphenEdit == other.startHyphenEdit && endHyphenEdit == other.endHyphenEdit;
  }

  // ---- colors as longs (sRGB packed as argb << 32; other color spaces as half floats, converted without gamut mapping)
  static int toArgb(long c) {
    if ((c & 0x3f) == 0) return (int) (c >>> 32);
    float r = libcore.util.FP16.toFloat((short) (c >>> 48)), g = libcore.util.FP16.toFloat((short) (c >>> 32)), b = libcore.util.FP16.toFloat((short) (c >>> 16));
    float a = ((c >>> 6) & 0x3ff) / 1023f;
    return (Math.round(Math.max(0, Math.min(1, a)) * 255) << 24) | (Math.round(Math.max(0, Math.min(1, r)) * 255) << 16)
        | (Math.round(Math.max(0, Math.min(1, g)) * 255) << 8) | Math.round(Math.max(0, Math.min(1, b)) * 255);
  }
  public void setColor(long color) { setColor(toArgb(color)); }
  public long getColorLong() { return (color & 0xffffffffL) << 32; }
  public void setShadowLayer(float radius, float dx, float dy, long shadowColor) { setShadowLayer(radius, dx, dy, toArgb(shadowColor)); }
  public float getShadowLayerRadius() { return shadowRadius; }
  public float getShadowLayerDx() { return shadowDx; }
  public float getShadowLayerDy() { return shadowDy; }
  public int getShadowLayerColor() { return shadowColor; }
  public long getShadowLayerColorLong() { return (shadowColor & 0xffffffffL) << 32; }

  // ---- blend modes (those with a Porter-Duff equivalent reach the page)
  BlendMode blendMode;
  public void setBlendMode(BlendMode mode) {
    blendMode = mode;
    if (mode == null) { setXfermode(null); return; }
    String n = mode.name();
    if (n.equals("PLUS")) n = "ADD"; else if (n.equals("MODULATE")) n = "MULTIPLY";
    PorterDuff.Mode pd = null;
    for (PorterDuff.Mode m : PorterDuff.Mode.values()) if (m.name().equals(n)) pd = m;
    setXfermode(pd == null ? null : new PorterDuffXfermode(pd));
  }
  public BlendMode getBlendMode() { return blendMode; }

  // ---- decoration metrics (as the page draws them)
  public float getUnderlinePosition() { return textSize * 0.11f; }
  public float getUnderlineThickness() { return Math.max(1, textSize / 18); }
  public float getStrikeThruPosition() { return -textSize * 0.28f; }
  public float getStrikeThruThickness() { return Math.max(1, textSize / 18); }

  // ---------------------------------------------------------------- page state
  @JSBody(script = "return HW.cv.newPaint();")
  private static native JSObject newPaint();
  @JSBody(params = {"p", "color", "style", "w", "size", "family", "bold", "italic", "align", "sr", "sdx", "sdy", "sc", "cap", "join", "flags", "scaleX"},
      script = "HW.cv.setPaint(p, color, style, w, size, family, bold, italic, align, sr, sdx, sdy, sc, cap, join, flags, scaleX);")
  private static native void push(JSObject p, int color, int style, float w, float size, String family, boolean bold, boolean italic, int align,
      float sr, float sdx, float sdy, int sc, int cap, int join, int flags, float scaleX);
  @JSBody(params = {"p", "d", "ph"}, script = "p.dash = d; p.dashPhase = ph;")
  private static native void pushDash(JSObject p, Float32Array d, float phase);
  @JSBody(params = {"p", "s"}, script = "p.shader = s;")
  private static native void pushShader(JSObject p, JSObject s);
  private int shaderVersion;
  @JSBody(params = {"p", "m", "pc", "pm", "x"}, script = "p.filter = m ? {m: m} : (pm ? {pc: pc, pm: pm} : null); p.xfer = x;")
  private static native void pushFilter(JSObject p, Float32Array m, int pc, String pm, String xfer);

  /** Effective paint color after the color filter (filters for bitmaps are applied page-side). */
  public JSObject js() {
    if (js == null) js = newPaint();
    if (shader != null && shader.version() != shaderVersion) dirty = true;
    if (dirty) {
      dirty = false;
      Typeface t = typeface == null ? Typeface.DEFAULT : typeface;
      int st = t.style;
      boolean bold = (st & Typeface.BOLD) != 0 || isFakeBoldText();
      int c = colorFilter != null ? colorFilter.filter(color) : color;
      push(js, c, style.ordinal(), strokeWidth, textSize, t.family, bold, (st & Typeface.ITALIC) != 0, align.ordinal(),
          shadowRadius, shadowDx, shadowDy, shadowColor, cap.ordinal(), join.ordinal(), flags, textScaleX);
      pushSpacing(js, letterSpacing, wordSpacing);
      pushShader(js, shader == null ? null : shader.js());
      if (pathEffect instanceof DashPathEffect) {
        DashPathEffect de = (DashPathEffect) pathEffect;
        float[] iv = de.intervals.length % 2 == 0 ? de.intervals : java.util.Arrays.copyOf(de.intervals, de.intervals.length - 1);
        pushDash(js, Float32Array.copyFromJavaArray(iv), de.phase);
      } else pushDash(js, null, 0);
      shaderVersion = shader == null ? 0 : shader.version();
      String xf = xfermode instanceof PorterDuffXfermode ? ((PorterDuffXfermode) xfermode).mode.name() : null;
      if (colorFilter != null && colorFilter.matrix != null) pushFilter(js, Float32Array.copyFromJavaArray(colorFilter.matrix), 0, null, xf);
      else if (colorFilter != null && colorFilter.pdMode != null) pushFilter(js, null, colorFilter.pdColor, colorFilter.pdMode.name(), xf);
      else pushFilter(js, null, 0, null, xf);
    }
    return js;
  }

  // ---------------------------------------------------------------- text metrics
  @JSBody(params = {"p", "s"}, script = "return HW.cv.measure(p, s);")
  private static native float measure(JSObject p, String s);
  @JSBody(params = {"p", "ls", "ws"}, script = "HW.cv.setSpacing(p, ls, ws);")
  private static native void pushSpacing(JSObject p, float ls, float ws);
  @JSBody(params = {"p", "i"}, script = "return HW.cv.metric(p, i);")
  private static native float metric(JSObject p, int i);
  @JSBody(params = {"p", "s"}, script = "return HW.cv.widths(p, s);")
  private static native Float32Array widthsJS(JSObject p, String s);

  public float measureText(String s) { return s == null || s.isEmpty() ? 0 : measure(js(), s); }
  public float measureText(String s, int start, int end) { return measureText(s.substring(start, end)); }
  public float measureText(CharSequence s, int start, int end) { return measureText(s.subSequence(start, end).toString()); }
  public float measureText(char[] t, int i, int c) { return measureText(new String(t, i, c)); }
  public int getTextWidths(String s, float[] w) { return getTextWidths(s, 0, s.length(), w); }
  public int getTextWidths(String s, int start, int end, float[] w) {
    float[] r = widthsJS(js(), s.substring(start, end)).copyToJavaArray();
    System.arraycopy(r, 0, w, 0, Math.min(r.length, w.length));
    return r.length;
  }
  public int getTextWidths(CharSequence s, int start, int end, float[] w) { return getTextWidths(s.toString(), start, end, w); }
  public int getTextWidths(char[] t, int i, int c, float[] w) { return getTextWidths(new String(t, i, c), w); }
  public int breakText(String text, boolean forward, float maxWidth, float[] measured) {
    float[] w = widthsJS(js(), text).copyToJavaArray();
    float sum = 0; int n = 0;
    if (forward) { for (; n < w.length; n++) { if (sum + w[n] > maxWidth) break; sum += w[n]; } }
    else { for (int i = w.length - 1; i >= 0; i--, n++) { if (sum + w[i] > maxWidth) break; sum += w[i]; } }
    if (measured != null && measured.length > 0) measured[0] = sum;
    return n;
  }
  public int breakText(CharSequence text, int start, int end, boolean forward, float maxWidth, float[] measured) { return breakText(text.subSequence(start, end).toString(), forward, maxWidth, measured); }
  public int breakText(char[] text, int i, int c, float maxWidth, float[] measured) { return breakText(new String(text, i, c), true, maxWidth, measured); }
  public void getTextBounds(String s, int start, int end, Rect b) {
    float w = measureText(s, start, end);
    b.set(0, (int) Math.floor(metric(js(), 1)), (int) Math.ceil(w), (int) Math.ceil(metric(js(), 2)));
  }
  public void getTextBounds(char[] t, int i, int c, Rect b) { getTextBounds(new String(t, i, c), 0, c, b); }
  public float ascent() { return metric(js(), 1); }
  public float descent() { return metric(js(), 2); }
  public float getFontSpacing() { return getFontMetrics(null); }
  public float getFontMetrics(FontMetrics m) {
    JSObject p = js();
    float top = metric(p, 0), asc = metric(p, 1), desc = metric(p, 2), bot = metric(p, 3);
    if (m != null) { m.top = top; m.ascent = asc; m.descent = desc; m.bottom = bot; m.leading = 0; }
    return desc - asc;
  }
  public FontMetrics getFontMetrics() { FontMetrics m = new FontMetrics(); getFontMetrics(m); return m; }
  public int getFontMetricsInt(FontMetricsInt m) {
    JSObject p = js();
    int top = (int) Math.floor(metric(p, 0)), asc = Math.round(metric(p, 1)), desc = Math.round(metric(p, 2)), bot = (int) Math.ceil(metric(p, 3));
    if (m != null) { m.top = top; m.ascent = asc; m.descent = desc; m.bottom = bot; m.leading = 0; }
    return desc - asc;
  }
  public FontMetricsInt getFontMetricsInt() { FontMetricsInt m = new FontMetricsInt(); getFontMetricsInt(m); return m; }
  public void getTextPath(String text, int start, int end, float x, float y, Path path) {}

  public void getTextBounds(CharSequence text, int start, int end, Rect bounds) { getTextBounds(text.toString(), start, end, bounds); }
  public void getTextPath(char[] text, int index, int count, float x, float y, Path path) {}
  // ---- text runs (left to right; a right-to-left run measures from the other end)
  private float[] advances(CharSequence text, int start, int end) {
    float[] w = new float[end - start];
    if (end > start) getTextWidths(text.toString(), start, end, w);
    return w;
  }
  private static void checkRun(int len, int start, int end, int ctxStart, int ctxEnd, int offset) {
    if ((ctxStart | start | offset | end | ctxEnd | start - ctxStart | offset - start | end - offset | ctxEnd - end | len - ctxEnd) < 0) throw new IndexOutOfBoundsException();
  }
  public float getRunAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, int offset) {
    if (text == null) throw new IllegalArgumentException("text cannot be null");
    checkRun(text.length(), start, end, contextStart, contextEnd, offset);
    if (end == start) return 0;
    float[] w = advances(text, start, end);
    float sum = 0;
    if (!isRtl) for (int i = start; i < offset; i++) sum += w[i - start];
    else for (int i = offset; i < end; i++) sum += w[i - start];
    return sum;
  }
  public float getRunAdvance(char[] text, int start, int end, int contextStart, int contextEnd, boolean isRtl, int offset) {
    if (text == null) throw new IllegalArgumentException("text cannot be null");
    return getRunAdvance(new String(text), start, end, contextStart, contextEnd, isRtl, offset);
  }
  public float getRunCharacterAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, int offset, float[] advances, int advancesIndex) {
    if (text == null) throw new IllegalArgumentException("text cannot be null");
    checkRun(text.length(), start, end, contextStart, contextEnd, offset);
    float[] w = advances(text, start, end);
    if (advances != null) {
      if (advancesIndex < 0 || advancesIndex + (end - start) > advances.length) throw new IndexOutOfBoundsException();
      System.arraycopy(w, 0, advances, advancesIndex, w.length);
    }
    return getRunAdvance(text, start, end, contextStart, contextEnd, isRtl, offset);
  }
  public float getRunCharacterAdvance(char[] text, int start, int end, int contextStart, int contextEnd, boolean isRtl, int offset, float[] advances, int advancesIndex) {
    if (text == null) throw new IllegalArgumentException("text cannot be null");
    return getRunCharacterAdvance(new String(text), start, end, contextStart, contextEnd, isRtl, offset, advances, advancesIndex);
  }
  /** the cursor position in [start, end] whose run advance is closest to the given one */
  public int getOffsetForAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, float advance) {
    if (text == null) throw new IllegalArgumentException("text cannot be null");
    checkRun(text.length(), start, end, contextStart, contextEnd, start);
    float[] w = advances(text, start, end);
    int best = start; float bestDist = Float.MAX_VALUE, sum = 0;
    for (int i = start; i <= end; i++) {
      boolean boundary = i == start || i == end || !Character.isLowSurrogate(text.charAt(i));
      float a = isRtl ? -1 : sum;
      if (isRtl) { a = 0; for (int k = i; k < end; k++) a += w[k - start]; }
      if (boundary && Math.abs(a - advance) < bestDist) { bestDist = Math.abs(a - advance); best = i; }
      if (i < end) sum += w[i - start];
    }
    return best;
  }
  public int getOffsetForAdvance(char[] text, int start, int end, int contextStart, int contextEnd, boolean isRtl, float advance) {
    if (text == null) throw new IllegalArgumentException("text cannot be null");
    return getOffsetForAdvance(new String(text), start, end, contextStart, contextEnd, isRtl, advance);
  }
  public float getTextRunAdvances(char[] chars, int index, int count, int contextIndex, int contextCount, boolean isRtl, float[] advances, int advancesIndex) {
    if (chars == null) throw new IllegalArgumentException("text cannot be null");
    if ((index | count | contextIndex | contextCount | advancesIndex | (index - contextIndex) | (contextCount - count) | ((contextIndex + contextCount) - (index + count))
        | (chars.length - (contextIndex + contextCount)) | (advances == null ? 0 : (advances.length - (advancesIndex + count)))) < 0) throw new IndexOutOfBoundsException();
    float[] w = advances(new String(chars), index, index + count);
    float sum = 0;
    for (float f : w) sum += f;
    if (advances != null) System.arraycopy(w, 0, advances, advancesIndex, count);
    return sum;
  }
  public static final int CURSOR_AFTER = 0, CURSOR_AT_OR_AFTER = 1, CURSOR_BEFORE = 2, CURSOR_AT_OR_BEFORE = 3, CURSOR_AT = 4;
  /** cursor movement over code points (a surrogate pair is one position) */
  public int getTextRunCursor(CharSequence text, int contextStart, int contextEnd, boolean isRtl, int offset, int cursorOpt) {
    if ((contextStart | contextEnd | offset | (contextEnd - contextStart) | (offset - contextStart) | (contextEnd - offset) | (text.length() - contextEnd) | cursorOpt) < 0 || cursorOpt > CURSOR_AT)
      throw new IndexOutOfBoundsException();
    java.util.function.IntPredicate boundary = i -> i <= contextStart || i >= contextEnd || !(Character.isHighSurrogate(text.charAt(i - 1)) && Character.isLowSurrogate(text.charAt(i)));
    switch (cursorOpt) {
      case CURSOR_AT: return boundary.test(offset) ? offset : -1;
      case CURSOR_AT_OR_AFTER: if (boundary.test(offset)) return offset;
      case CURSOR_AFTER: { int i = offset + 1; while (i < contextEnd && !boundary.test(i)) i++; return i <= contextEnd ? i : -1; }
      case CURSOR_AT_OR_BEFORE: if (boundary.test(offset)) return offset;
      default: { int i = offset - 1; while (i > contextStart && !boundary.test(i)) i--; return i >= contextStart ? i : -1; }
    }
  }
  public int getTextRunCursor(char[] text, int contextStart, int contextLength, boolean isRtl, int offset, int cursorOpt) {
    return getTextRunCursor(new String(text), contextStart, contextStart + contextLength, isRtl, offset, cursorOpt);
  }
  public void getFontMetricsInt(CharSequence text, int start, int count, int contextStart, int contextCount, boolean isRtl, FontMetricsInt outMetrics) {
    if (text == null) throw new IllegalArgumentException("text must not be null");
    if (outMetrics == null) throw new IllegalArgumentException("outMetrics must not be null");
    getFontMetricsInt(outMetrics);
  }
  public void getFontMetricsInt(char[] text, int start, int count, int contextStart, int contextCount, boolean isRtl, FontMetricsInt outMetrics) {
    if (text == null) throw new IllegalArgumentException("text must not be null");
    if (outMetrics == null) throw new IllegalArgumentException("outMetrics must not be null");
    getFontMetricsInt(outMetrics);
  }
  public void getFontMetricsForLocale(FontMetrics metrics) { getFontMetrics(metrics); }
  public void getFontMetricsIntForLocale(FontMetricsInt metrics) { getFontMetricsInt(metrics); }
  /** whether the fonts show the string as one glyph cluster: a defined code point sequence that measures wider than nothing */
  public boolean hasGlyph(String string) {
    if (string == null) throw new IllegalArgumentException("string cannot be null");
    if (string.isEmpty()) return false;
    for (int i = 0; i < string.length(); ) { int cp = string.codePointAt(i); if (!Character.isDefined(cp)) return false; i += Character.charCount(cp); }
    return measureText(string) > 0;
  }
  /** the outline a stroke covers, as filled shapes (segments, joins and caps; miter joins are drawn as bevels) */
  public boolean getFillPath(Path src, Path dst) {
    if (style == Style.FILL) { dst.set(src); return true; }
    float w = strokeWidth;
    if (w <= 0) { dst.set(src); return false; }
    Path out = new Path();
    if (style == Style.FILL_AND_STROKE) out.addPath(src);
    float h = w / 2;
    for (Path.Contour c : src.contours(0.25f)) {
      int k = c.n / 2;
      int segs = c.closed ? k : k - 1;
      for (int i = 0; i < segs; i++) {
        float x0 = c.p[2 * i], y0 = c.p[2 * i + 1], x1 = c.p[2 * ((i + 1) % k)], y1 = c.p[2 * ((i + 1) % k) + 1];
        float dx = x1 - x0, dy = y1 - y0, len = (float) Math.hypot(dx, dy);
        if (len == 0) continue;
        float nx = -dy / len * h, ny = dx / len * h, ex = 0, ey = 0;
        boolean first = i == 0 && !c.closed, last = i == segs - 1 && !c.closed;
        if (cap == Cap.SQUARE) { ex = dx / len * h; ey = dy / len * h; }
        float sx0 = first ? x0 - ex : x0, sy0 = first ? y0 - ey : y0, sx1 = last ? x1 + ex : x1, sy1 = last ? y1 + ey : y1;
        out.moveTo(sx0 + nx, sy0 + ny); out.lineTo(sx1 + nx, sy1 + ny); out.lineTo(sx1 - nx, sy1 - ny); out.lineTo(sx0 - nx, sy0 - ny); out.close();
      }
      for (int i = 0; i < k; i++) {
        boolean end = !c.closed && (i == 0 || i == k - 1);
        float x = c.p[2 * i], y = c.p[2 * i + 1];
        if (end ? cap == Cap.ROUND : join == Join.ROUND) { out.addCircle(x, y, h, Path.Direction.CW); continue; }
        if (end || k < 3) continue;
        int pi = (i - 1 + k) % k, ni = (i + 1) % k;
        float ax = x - c.p[2 * pi], ay = y - c.p[2 * pi + 1], bx = c.p[2 * ni] - x, by = c.p[2 * ni + 1] - y;
        float la = (float) Math.hypot(ax, ay), lb = (float) Math.hypot(bx, by);
        if (la == 0 || lb == 0) continue;
        out.moveTo(x, y);
        out.lineTo(x - ay / la * h, y + ax / la * h); out.lineTo(x - by / lb * h, y + bx / lb * h); out.close();
        out.moveTo(x, y);
        out.lineTo(x + ay / la * h, y - ax / la * h); out.lineTo(x + by / lb * h, y - bx / lb * h); out.close();
      }
    }
    dst.set(out);
    return true;
  }
}

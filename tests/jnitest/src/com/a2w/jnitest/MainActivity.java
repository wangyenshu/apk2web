package com.a2w.jnitest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/** JNI conformance checks for apk2web: native code (jni/jnitest.c) calls back into this class. */
public class MainActivity extends Activity {
  static { System.loadLibrary("jnitest"); }

  // used by native code
  public int counter = 41;
  public static String label = "static";
  public long big = 0x1122334455667788L;
  public double ratio = 0.25;

  public String callback(int i, String s, long l, double d) { return s + ":" + i + ":" + Long.toHexString(l) + ":" + d; }
  public static int twice(int x) { return 2 * x; }
  public void fail() { throw new IllegalArgumentException("from Java"); }
  public float half(float f) { return f / 2; }
  static void throwLocal() { throw new IllegalStateException("local"); }

  native String nativeRun();                                   // runs the native checks, returns a report
  static native double nativeMix(int a, long b, float c, double d);
  native int registeredAdd(int a, int b);                      // bound by RegisterNatives in JNI_OnLoad
  static native void nativeThrow();                            // ThrowNew(IllegalStateException)
  static native int[] nativeSquares(int[] in);
  static native long nativeLong(long a, long b);

  final StringBuilder report = new StringBuilder();
  int passed, failed;

  static final class GenericLog implements android.view.View.OnGenericMotionListener {
    final StringBuilder b;
    GenericLog(StringBuilder b) { this.b = b; }
    public boolean onGenericMotion(android.view.View v, android.view.MotionEvent e) { b.append(e.getActionMasked()); return false; }
  }
  void check(String what, boolean ok, String detail) {
    if (ok) passed++; else failed++;
    String line = (ok ? "PASS " : "FAIL ") + what + (detail == null ? "" : "  [" + detail + "]");
    report.append(line).append('\n');
    Log.i("JNITEST", line);
  }

  @Override protected void onCreate(Bundle b) {
    super.onCreate(b);
    setContentView(R.layout.main);
    runAll();
  }

  public void runAgain(View v) { runAll(); }

  void runAll() {
    report.setLength(0);
    passed = failed = 0;
    try { throwLocal(); check("Java exception caught", false, "no exception"); }
    catch (IllegalStateException e) { check("Java exception caught", true, null); }
    try { Object o = null; o.hashCode(); check("NullPointerException caught", false, null); }
    catch (NullPointerException e) { check("NullPointerException caught", true, null); }
    try {
      String r = nativeRun();
      for (String line : r.split("\n")) if (!line.isEmpty()) check("native: " + line.substring(5), line.startsWith("PASS"), null);
    } catch (Throwable t) { check("nativeRun", false, String.valueOf(t)); }
    try {
      double d = nativeMix(3, 0x100000000L, 1.5f, 2.25);
      check("args int/long/float/double -> double", d == 3 + 4294967296.0 + 1.5 + 2.25, String.valueOf(d));
    } catch (Throwable t) { check("nativeMix", false, String.valueOf(t)); }
    try { check("RegisterNatives", registeredAdd(20, 22) == 42, null); } catch (Throwable t) { check("RegisterNatives", false, String.valueOf(t)); }
    try { nativeThrow(); check("ThrowNew reaches Java", false, "no exception"); }
    catch (IllegalStateException e) { check("ThrowNew reaches Java", "from native".equals(e.getMessage()), e.getMessage()); }
    catch (Throwable t) { check("ThrowNew reaches Java", false, String.valueOf(t)); }
    try {
      int[] sq = nativeSquares(new int[] {1, 2, 3, 4});
      check("int[] in and out", sq != null && sq.length == 4 && sq[3] == 16, sq == null ? "null" : java.util.Arrays.toString(sq));
    } catch (Throwable t) { check("nativeSquares", false, String.valueOf(t)); }
    try {
      long l = nativeLong(0x7fffffffL, 0x100000001L);
      check("long args and result", l == 0x7fffffffL + 0x100000001L, Long.toHexString(l));
    } catch (Throwable t) { check("nativeLong", false, String.valueOf(t)); }
    check("field written by native code", counter == 42, String.valueOf(counter));
    check("static field written by native code", "native".equals(label), label);
    try {
      String u = new String(new byte[] {(byte) 0xC3, (byte) 0xA9}, "utf8"), l = new String(new byte[] {(byte) 0xE9}, "latin1");
      check("charset aliases (utf8, latin1)", u.equals("\u00e9") && l.equals("\u00e9"), u + " " + l);
    } catch (Throwable t) { check("charset aliases (utf8, latin1)", false, String.valueOf(t)); }
    // resources and assets (in a split APK set they come from the density and base splits)
    try {
      android.graphics.drawable.Drawable d = getResources().getDrawable(R.drawable.dot);
      check("density-specific drawable", d != null && d.getIntrinsicWidth() > 0, d == null ? "null" : d.getIntrinsicWidth() + "px");
    } catch (Throwable t) { check("density-specific drawable", false, String.valueOf(t)); }
    try {
      java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(getAssets().open("hello.txt")));
      String line = r.readLine();
      r.close();
      check("asset file", "hello from assets".equals(line), line);
    } catch (Throwable t) { check("asset file", false, String.valueOf(t)); }
    // an APK expansion file, when the app came with one: Android/obb/<package>/main.<version>.<package>.obb
    java.io.File obb = new java.io.File(getObbDir(), "main.1.com.a2w.jnitest.obb");
    if (obb.exists()) {
      try {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.FileReader(obb));
        String line = r.readLine();
        r.close();
        check("expansion file (OBB)", "expansion data".equals(line), line);
      } catch (Throwable t) { check("expansion file (OBB)", false, String.valueOf(t)); }
    } else Log.i("JNITEST", "no expansion file (OBB)");
    platformChecks();
    textFormatChecks();
    spanChecks();
    annotationChecks();
    themeChecks();
    uiChecks();
    versionChecks();
    timeChecks();
    parcelChecks();
    systemChecks();
    String summary = getResources().getQuantityString(R.plurals.passed, passed, passed) + (failed > 0 ? ", " + failed + " failed" : "");
    Log.i("JNITEST", "SUMMARY " + summary);
    ((TextView) findViewById(R.id.summary)).setText(summary);
    ((TextView) findViewById(R.id.out)).setText(report.toString());
    counter = 41;   // the native checks change these: back to their starting values for "Run again"
    label = "static";
    ratio = 0.25;
    threadChecks();
    if (!glStarted) {
      glStarted = true;
      // lifecycle callbacks around the GL activity's start; and this activity's pause
      final StringBuilder events = new StringBuilder();
      getApplication().registerActivityLifecycleCallbacks(new LifecycleLog(events));
      startActivity(new android.content.Intent(this, GLActivity.class));
      new android.os.Handler().postDelayed(() -> {
        String got = events.toString();
        check("activity lifecycle callbacks", got.startsWith("MainActivity:postCreated MainActivity:started MainActivity:resumed MainActivity:prePaused MainActivity:paused MainActivity:postPaused GLActivity:preCreated GLActivity:created GLActivity:postCreated GLActivity:started"), got);
      }, 600);
    }
  }
  static boolean glStarted;
  /** like an analytics SDK: a task initializes, then posts to a HandlerThread that a class initializer started */
  static final class Sdk {
    static boolean ready; static String id; static volatile String handled = "no";
    static synchronized void init() { id = "app"; ready = true; }
  }
  static final class Processing {
    static android.os.Handler d, e;
    static final android.os.HandlerThread g = new android.os.HandlerThread("ProcessingThread");
    static { g.start(); }
    static synchronized android.os.Handler a() { if (e == null) f(); return e; }
    private static void f() {
      if (d == null) d = new android.os.Handler(g.getLooper());
      if (e == null) e = new android.os.Handler(g.getLooper()) { @Override public void handleMessage(android.os.Message m) { Sdk.handled = "what " + m.what; } };
    }
  }
  static final class SdkTask implements Runnable {
    static volatile boolean finished;
    @Override public void run() {
      try {
        if (!Sdk.ready) Sdk.init();
        if (Sdk.id == null) return;
        Processing.a().sendMessage(android.os.Message.obtain(Processing.a(), 7, "x"));
      } catch (Throwable t) { }
      finished = true;
    }
  }
  static final class SaxRecorder extends org.xml.sax.helpers.DefaultHandler {
    final StringBuilder sb = new StringBuilder();
    @Override public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes a) { sb.append('<').append(qName).append(a.getLength()); }
    @Override public void characters(char[] ch, int start, int length) { sb.append(ch, start, length); }
  }
  /** platform libraries Android ships beside the SDK classes: XML (DOM, SAX, transform), digests, styled text */
  void platformChecks() {
    String xml = "<?xml version=\"1.0\"?><levels n=\"2\"><level id=\"1\">A &amp; B</level><level id=\"2\"><![CDATA[<b>]]></level></levels>";
    try {
      org.w3c.dom.Document d = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder()
          .parse(new java.io.ByteArrayInputStream(xml.getBytes("UTF-8")));
      org.w3c.dom.NodeList l = d.getElementsByTagName("level");
      String got = d.getDocumentElement().getAttribute("n") + " " + l.getLength() + " " + l.item(0).getTextContent() + " " + ((org.w3c.dom.Element) l.item(1)).getAttribute("id") + " " + l.item(1).getTextContent();
      check("XML: DOM (DocumentBuilder)", got.equals("2 2 A & B 2 <b>"), got);
    } catch (Throwable t) { check("XML: DOM (DocumentBuilder)", false, String.valueOf(t)); Log.e("JNITEST", "dom", t); }
    try {
      SaxRecorder rec = new SaxRecorder();
      javax.xml.parsers.SAXParserFactory.newInstance().newSAXParser().parse(new java.io.ByteArrayInputStream(xml.getBytes("UTF-8")), rec);
      StringBuilder sb = rec.sb;
      check("XML: SAX (SAXParser)", sb.toString().equals("<levels1<level1A & B<level1<b>"), sb.toString());
    } catch (Throwable t) { check("XML: SAX (SAXParser)", false, String.valueOf(t)); Log.e("JNITEST", "sax", t); }
    try {
      org.w3c.dom.Document d = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      org.w3c.dom.Element e = d.createElement("save");
      e.setAttribute("score", "10");
      d.appendChild(e);
      javax.xml.transform.Transformer t = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
      t.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
      java.io.StringWriter w = new java.io.StringWriter();
      t.transform(new javax.xml.transform.dom.DOMSource(d), new javax.xml.transform.stream.StreamResult(w));
      check("XML: Transformer writes a DOM", w.toString().equals("<save score=\"10\"/>"), w.toString());
    } catch (Throwable t) { check("XML: Transformer writes a DOM", false, String.valueOf(t)); Log.e("JNITEST", "transform", t); }
    try {
      byte[] h = java.security.MessageDigest.getInstance("SHA-256").digest("abc".getBytes("UTF-8"));
      StringBuilder hex = new StringBuilder();
      for (byte b : h) hex.append(String.format("%02x", b & 0xff));
      check("MessageDigest SHA-256", hex.toString().equals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad"), hex.toString());
    } catch (Throwable t) { check("MessageDigest SHA-256", false, String.valueOf(t)); }
    try {
      android.text.Spanned sp = android.text.Html.fromHtml("<b>bold</b> &#8226; <font color=\"#ff0000\">red</font> &hellip;");
      android.text.style.StyleSpan[] b = sp.getSpans(0, sp.length(), android.text.style.StyleSpan.class);
      android.text.style.ForegroundColorSpan[] c = sp.getSpans(0, sp.length(), android.text.style.ForegroundColorSpan.class);
      String got = sp + " " + b.length + " " + (c.length == 1 ? Integer.toHexString(c[0].getForegroundColor()) + "@" + sp.getSpanStart(c[0]) : "none");
      check("Html.fromHtml: entities and spans", got.equals("bold \u2022 red \u2026 1 ffff0000@7"), got);
    } catch (Throwable t) { check("Html.fromHtml: entities and spans", false, String.valueOf(t)); }
  }
  void annotationChecks() {
    try {
      String got = Annotated.run();
      check("annotations on methods and fields (EventBus, Gson)", got.equals("onMessage Stringfalse 3 ab first_name hi true"), got);
    } catch (Throwable t) { check("annotations on methods and fields (EventBus, Gson)", false, String.valueOf(t)); Log.e("JNITEST", "annotations", t); }
  }
  /** themes: attribute resolution (?attr chains, platform themes), default styles, vector drawables */
  void themeChecks() {
    String name = "themes: resolveAttribute, obtainStyledAttributes with a default style, vector drawables";
    try {
      android.content.res.Resources.Theme t = getResources().newTheme();
      t.applyStyle(R.style.TestTheme, true);
      android.util.TypedValue tv = new android.util.TypedValue();
      boolean p1 = t.resolveAttribute(android.R.attr.textColorPrimary, tv, true);
      int primary = tv.data;
      boolean p2 = t.resolveAttribute(android.R.attr.textColorLink, tv, true);
      int link = tv.data;
      boolean p3 = t.resolveAttribute(android.R.attr.actionBarSize, tv, true);
      int bar = android.util.TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
      int want = Math.round(48 * getResources().getDisplayMetrics().density);
      android.content.res.TypedArray a = t.obtainStyledAttributes(null, R.styleable.Card, R.attr.cardStyle, 0);
      int pad = a.getDimensionPixelSize(R.styleable.Card_android_padding, -1);
      int acc = a.getColor(R.styleable.Card_accentish, 0);
      a.recycle();
      android.view.ContextThemeWrapper w = new android.view.ContextThemeWrapper(this, R.style.TestTheme);
      android.content.res.TypedArray b = w.obtainStyledAttributes(new int[] {android.R.attr.colorBackground});
      int bg = b.getColor(0, 0);
      b.recycle();
      android.graphics.drawable.Drawable d = getResources().getDrawable(R.drawable.arrow);
      android.graphics.Bitmap bm = android.graphics.Bitmap.createBitmap(24, 24, android.graphics.Bitmap.Config.ARGB_8888);
      android.graphics.Canvas c = new android.graphics.Canvas(bm);
      d.setBounds(0, 0, 24, 24);
      d.draw(c);
      int edge = bm.getPixel(4, 4), mid = bm.getPixel(12, 12), out = bm.getPixel(0, 0);
      String got = p1 + " " + Integer.toHexString(primary) + " " + p2 + " " + Integer.toHexString(link) + " " + p3 + " " + (bar == want)
          + " " + (pad == Math.round(7 * getResources().getDisplayMetrics().density)) + " " + Integer.toHexString(acc) + " " + Integer.toHexString(bg)
          + " " + d.getClass().getSimpleName() + " " + (d.getIntrinsicWidth() == want / 2) + " " + Integer.toHexString(edge) + " " + Integer.toHexString(mid) + " " + Integer.toHexString(out);
      check(name, got.equals("true ff000000 true ff123456 true true true ff123456 fff3f3f3 VectorDrawable true ff00ff00 ffff0000 0"), got);
    } catch (Throwable e) { check(name, false, String.valueOf(e)); Log.e("JNITEST", "themes", e); }
  }
  /** counts the gestures a GestureDetector reports */
  static final class Gestures extends android.view.GestureDetector.SimpleOnGestureListener {
    int downs, taps, scrolls, flings;
    @Override public boolean onDown(android.view.MotionEvent e) { downs++; return true; }
    @Override public boolean onSingleTapUp(android.view.MotionEvent e) { taps++; return true; }
    @Override public boolean onScroll(android.view.MotionEvent a, android.view.MotionEvent b, float dx, float dy) { scrolls++; return true; }
    @Override public boolean onFling(android.view.MotionEvent a, android.view.MotionEvent b, float vx, float vy) { flings++; return true; }
  }
  static final class Pinch extends android.view.ScaleGestureDetector.SimpleOnScaleGestureListener {
    float scale = 1;
    @Override public boolean onScale(android.view.ScaleGestureDetector d) { scale *= d.getScaleFactor(); return true; }
  }
  static final class Frame implements android.view.Choreographer.FrameCallback {
    long nanos;
    public void doFrame(long t) { nanos = t; }
  }
  static final class Dismissed implements android.widget.PopupWindow.OnDismissListener {
    boolean called;
    public void onDismiss() { called = true; }
  }
  static android.view.MotionEvent touch(long down, long t, int action, float x, float y) { return android.view.MotionEvent.obtain(down, t, action, x, y, 0); }
  static android.view.MotionEvent touch2(long down, long t, int action, float x0, float y0, float x1, float y1) {
    android.view.MotionEvent.PointerProperties[] pp = new android.view.MotionEvent.PointerProperties[2];
    android.view.MotionEvent.PointerCoords[] pc = new android.view.MotionEvent.PointerCoords[2];
    for (int i = 0; i < 2; i++) { pp[i] = new android.view.MotionEvent.PointerProperties(); pp[i].id = i; pc[i] = new android.view.MotionEvent.PointerCoords(); }
    pc[0].x = x0; pc[0].y = y0; pc[1].x = x1; pc[1].y = y1;
    return android.view.MotionEvent.obtain(down, t, action, 2, pp, pc, 0, 0, 1, 1, 0, 0, 0x1002, 0);
  }
  /** gestures, pinch zoom, frame callbacks, popup windows, the action bar */
  /** members whose SDK signature differs from the inherited one (covariant returns), and the manifest's levels */
  void versionChecks() {
    android.content.pm.ApplicationInfo ai = getApplicationInfo();
    check("ApplicationInfo levels (manifest min 14, target 19)", ai.targetSdkVersion == 19 && ai.minSdkVersion == 14,
        ai.minSdkVersion + "/" + ai.targetSdkVersion + ", SDK_INT " + android.os.Build.VERSION.SDK_INT);
    try {
      android.app.Notification n = new android.app.Notification();
      n.icon = 7;
      android.app.Notification nc = n.clone();
      android.util.SparseIntArray sa = new android.util.SparseIntArray();
      sa.put(3, 30);
      android.util.SparseIntArray sc = sa.clone();
      sa.put(3, 31);
      java.util.concurrent.ConcurrentSkipListMap<Integer, String> m = new java.util.concurrent.ConcurrentSkipListMap<>();
      for (int i = 0; i < 10; i++) m.put(i, "v" + i);
      java.util.concurrent.ConcurrentNavigableMap<Integer, String> head = m.headMap(4);
      java.util.NavigableSet<Integer> keys = m.descendingMap().keySet();
      android.view.animation.AnimationSet set = new android.view.animation.AnimationSet(true);
      set.addAnimation(new android.view.animation.AlphaAnimation(0f, 1f));
      String got = (nc != null && nc != n ? nc.icon : -1) + " " + sc.get(3) + " " + head.size() + " " + head.lastKey() + " " + keys.first()
          + " " + m.clone().size() + " " + new java.util.concurrent.ConcurrentSkipListSet<>(m.keySet()).headSet(2).size()
          + " " + set.getAnimations().size();
      check("covariant returns (clone, headMap, keySet, headSet)", "7 30 4 3 9 10 2 1".equals(got), got);
    } catch (Throwable t) { check("covariant returns (clone, headMap, keySet, headSet)", false, String.valueOf(t)); }
  }

  /** java.time (API 26): ThreeTen; ChronoLocalDate is an interface in java.time and a class in ThreeTen */
  void timeChecks() {
    int step = 0;
    try {
      java.time.LocalDate d = java.time.LocalDate.of(2024, 2, 28).plusDays(2);
      step = 1;
      java.time.chrono.ChronoLocalDate cd = d;
      StringBuilder got = new StringBuilder(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE.format(d));
      step = 2;
      got.append(' ').append(java.time.Duration.ofMinutes(90).toHours());
      step = 3;
      got.append(' ').append(new java.util.Date(86400000L).toInstant().getEpochSecond());
      step = 4;
      got.append(' ').append(cd.isAfter(java.time.LocalDate.of(2024, 1, 1)));
      step = 5;
      got.append(' ').append(java.time.temporal.ChronoUnit.DAYS.between(java.time.LocalDate.of(2024, 1, 1), d));
      step = 6;
      got.append(' ').append(java.time.ZonedDateTime.of(2024, 6, 1, 12, 0, 0, 0, java.time.ZoneId.of("Europe/Paris")).getOffset());
      step = 7;
      got.append(' ').append(java.util.TimeZone.getTimeZone("UTC").toZoneId().getId());
      step = 8;
      got.append(' ').append(java.time.LocalTime.parse("10:15:30").plusHours(15));
      check("java.time (ThreeTen backport)", "2024-03-01 1 86400 true 60 +02:00 UTC 01:15:30".equals(got.toString()), got.toString());
    } catch (Throwable t) { android.util.Log.e("JNITEST", "java.time", t); check("java.time (ThreeTen backport)", false, t + " at step " + step); }
  }

  /** Parcel (values in order, typed objects copied through their creator), Bundle, PersistableBundle, SparseArray, Color */
  /** android.system (OsConstants, Os, ErrnoException), Unicode tables, ICU's UCharacter */
  void systemChecks() {
    try {
      String got = android.system.OsConstants.EACCES + " " + Integer.toOctalString(android.system.OsConstants.O_NOFOLLOW) + " " + android.system.OsConstants._SC_PAGE_SIZE
          + " " + android.system.OsConstants.AI_V4MAPPED + " " + android.system.OsConstants.errnoName(2);
      check("OsConstants (ARM values)", got.equals("13 100000 40 2048 ENOENT"), got);
    } catch (Throwable t) { check("OsConstants (ARM values)", false, String.valueOf(t)); }
    try {
      String path = getFilesDir() + "/os.bin";
      java.io.FileDescriptor fd = android.system.Os.open(path, android.system.OsConstants.O_RDWR | android.system.OsConstants.O_CREAT | android.system.OsConstants.O_TRUNC, 0600);
      android.system.Os.write(fd, "hello world".getBytes("UTF-8"), 0, 11);
      android.system.Os.lseek(fd, 6, android.system.OsConstants.SEEK_SET);
      byte[] b = new byte[5];
      int n = android.system.Os.read(fd, b, 0, 5);
      long size = android.system.Os.fstat(fd).st_size;
      android.system.Os.close(fd);
      String msg = "";
      try { android.system.Os.open(getFilesDir() + "/none", android.system.OsConstants.O_RDONLY, 0); } catch (android.system.ErrnoException e) { msg = e.getMessage(); }
      java.io.FileDescriptor[] pp = android.system.Os.pipe();
      android.system.Os.write(pp[1], new byte[] {7, 8}, 0, 2);
      byte[] pb = new byte[4];
      int pn = android.system.Os.read(pp[0], pb, 0, 4);
      String got = n + new String(b, 0, n, "UTF-8") + " " + size + " " + msg + " " + pn + pb[1];
      check("Os file descriptors", got.equals("5world 11 open failed: ENOENT (No such file or directory) 28"), got);
    } catch (Throwable t) { check("Os file descriptors", false, String.valueOf(t)); Log.e("JNITEST", "os", t); }
    try {
      String got = Character.UnicodeBlock.of('a') + " " + Character.UnicodeBlock.of(0x3042) + " " + Character.UnicodeScript.of(0x0416) + " "
          + Character.UnicodeBlock.forName("Greek and Coptic") + " " + Character.isMirrored('(') + Character.isIdeographic(0x4E00) + " "
          + Character.getDirectionality(0x05D0) + Character.getDirectionality('1') + " " + Integer.parseUnsignedInt("4294967295");
      check("Unicode blocks, scripts, properties", got.equals("BASIC_LATIN HIRAGANA CYRILLIC GREEK truetrue 13 -1"), got);
    } catch (Throwable t) { check("Unicode blocks, scripts, properties", false, String.valueOf(t)); Log.e("JNITEST", "unicode", t); }
    try {
      String nfd = java.text.Normalizer.normalize("\u00e9\uAC01", java.text.Normalizer.Form.NFD);
      String nfc = java.text.Normalizer.normalize("e\u0301\u1100\u1161\u11A8", java.text.Normalizer.Form.NFC);
      String nfkc = java.text.Normalizer.normalize("\uFB01\u00BD", java.text.Normalizer.Form.NFKC);
      java.util.List<String> l = new java.util.ArrayList<>(java.util.Arrays.asList("zebra", "\u00c9clair", "apple", "Apple", "eclair", "Zurich"));
      java.util.Collections.sort(l, java.text.Collator.getInstance());
      java.text.Collator primary = java.text.Collator.getInstance();
      primary.setStrength(java.text.Collator.PRIMARY);
      java.text.StringCharacterIterator it = new java.text.StringCharacterIterator("abc");
      String got = nfd.length() + " " + (nfc.equals("\u00e9\uAC01")) + " " + nfkc + " " + l + " " + primary.compare("R\u00e9sum\u00e9", "resume") + " " + it.last() + it.previous()
          + " " + android.text.AndroidCharacter.getEastAsianWidth('\u4e00') + android.text.AndroidCharacter.getMirror('(');
      check("Normalizer, Collator, AndroidCharacter", got.equals("5 true fi1\u20442 [apple, Apple, eclair, \u00c9clair, zebra, Zurich] 0 cb 5)"), got);
    } catch (Throwable t) { check("Normalizer, Collator, AndroidCharacter", false, String.valueOf(t)); Log.e("JNITEST", "text", t); }
    try {
      Intent it = new Intent("a.b.ACT", android.net.Uri.parse("http://x.y/p?q=1#f"));
      it.addCategory("c1");
      it.putExtra("s", "v");
      it.putExtra("i", 5);
      it.setComponent(new android.content.ComponentName("p.k", "p.k.C"));
      android.os.Parcel p = android.os.Parcel.obtain();
      it.writeToParcel(p, 0);
      p.setDataPosition(0);
      Intent back = Intent.CREATOR.createFromParcel(p);
      p.recycle();
      Intent parsed = Intent.parseUri(it.toUri(Intent.URI_INTENT_SCHEME), Intent.URI_INTENT_SCHEME);
      android.net.Uri u = android.net.Uri.parse("http://x.y/a%20b?k=v%26w");
      String got = back.getAction() + " " + back.getData() + " " + back.getStringExtra("s") + back.getIntExtra("i", 0) + " " + back.getComponent().getClassName()
          + " " + parsed.filterEquals(it) + " " + parsed.getIntExtra("i", 0) + " " + u.getPath() + " " + u.getQueryParameter("k") + " " + u.getEncodedPath();
      check("Intent parcel and URI, Uri parts", got.equals("a.b.ACT http://x.y/p?q=1#f v5 p.k.C true 5 /a b v&w /a%20b"), got);
    } catch (Throwable t) { check("Intent parcel and URI, Uri parts", false, String.valueOf(t)); Log.e("JNITEST", "intent", t); }
    try {
      android.text.TextPaint tp = new android.text.TextPaint();
      tp.setTextSize(20);
      String text = "hello world foo bar baz";
      android.text.StaticLayout sl = new android.text.StaticLayout(text, tp, 60, android.text.Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
      int n = sl.getLineCount();
      boolean ordered = true;
      for (int i = 0; i < n; i++) ordered &= sl.getLineTop(i) < sl.getLineTop(i + 1) && sl.getLineStart(i) < sl.getLineEnd(i);
      android.text.StaticLayout el = android.text.StaticLayout.Builder.obtain("a very long single line", 0, 23, tp, 40).setMaxLines(1)
          .setEllipsize(android.text.TextUtils.TruncateAt.END).build();
      android.text.SpannableStringBuilder ed = new android.text.SpannableStringBuilder("ab");
      android.text.DynamicLayout dl = new android.text.DynamicLayout(ed, tp, 60, android.text.Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
      int before = dl.getLineCount();
      ed.append(" cdef ghij klmn");
      String got = (n > 1) + " " + ordered + " " + (sl.getLineEnd(n - 1) == text.length()) + " " + sl.getLineForOffset(0) + sl.getOffsetForHorizontal(0, 0)
          + " " + (el.getLineCount() == 1 && el.getEllipsisCount(0) > 0) + " " + (android.text.BoringLayout.isBoring("abc", tp) != null) + (android.text.BoringLayout.isBoring("a\nb", tp) == null)
          + " " + before + (dl.getLineCount() > 1);
      check("StaticLayout, BoringLayout, DynamicLayout", got.equals("true true true 00 true truetrue 1true"), got);
    } catch (Throwable t) { check("StaticLayout, BoringLayout, DynamicLayout", false, String.valueOf(t)); Log.e("JNITEST", "layout", t); }
    try {
      java.util.StringJoiner j = new java.util.StringJoiner(", ", "[", "]"); j.setEmptyValue("E"); String e = j.toString(); j.add("a").add("b");
      java.util.Scanner s = new java.util.Scanner("12 x 3.5\nline two\n"); int i = s.nextInt(); boolean hi = s.hasNextInt(); String x = s.next(); double d = s.nextDouble(); s.nextLine(); String l2 = s.nextLine();
      java.util.SplittableRandom r = new java.util.SplittableRandom(42); String sr = r.nextInt() + " " + r.nextLong(100) + " " + r.nextInt(5, 10);
      java.util.concurrent.atomic.AtomicIntegerArray ai = new java.util.concurrent.atomic.AtomicIntegerArray(3); ai.incrementAndGet(1); ai.compareAndSet(1, 1, 7);
      java.util.concurrent.atomic.LongAdder la = new java.util.concurrent.atomic.LongAdder(); la.add(5); la.increment();
      java.util.Set<String> ks = java.util.concurrent.ConcurrentHashMap.newKeySet(); ks.add("k"); ks.add("k");
      java.util.SortedSet<Integer> us = java.util.Collections.unmodifiableSortedSet(new java.util.TreeSet<>(java.util.Arrays.asList(3, 1, 2)));
      boolean threw = false; try { us.add(4); } catch (UnsupportedOperationException ex) { threw = true; }
      java.util.concurrent.LinkedBlockingDeque<Integer> dq = new java.util.concurrent.LinkedBlockingDeque<>(2); dq.putFirst(1); dq.putLast(2); boolean off = dq.offer(3); int tk = dq.takeLast();
      String iso = java.util.Locale.GERMANY.getISO3Language() + java.util.Locale.GERMANY.getISO3Country();
      int ag = java.lang.reflect.Array.getInt(new short[]{9}, 0);
      String got = e + j + " " + i + hi + x + d + l2 + " " + sr + " " + ai + la + ks.size() + us.first() + threw + " " + off + tk + iso + ag;
      check("JDK gaps: StringJoiner, Scanner, SplittableRandom, atomics, collections", got.equals("E[a, b] 12falsex3.5line two -491277234 45 6 [0, 7, 0]611true false2deuDEU9"), got);
    } catch (Throwable t) { check("JDK gaps: StringJoiner, Scanner, SplittableRandom, atomics, collections", false, String.valueOf(t)); Log.e("JNITEST", "jdk", t); }
    try {
      java.util.concurrent.CompletableFuture<Integer> cf = java.util.concurrent.CompletableFuture.supplyAsync(() -> 20).thenApply(v -> v + 1)
          .thenCombine(java.util.concurrent.CompletableFuture.completedFuture(2), (a, b) -> a * b);
      int cfv = cf.join();
      Object ex = java.util.concurrent.CompletableFuture.supplyAsync(() -> { if (cfv > 0) throw new IllegalStateException("x"); return "no"; })
          .exceptionally(t -> t.getClass().getSimpleName() + ":" + t.getCause().getMessage()).join();
      int fib = java.util.concurrent.ForkJoinPool.commonPool().invoke(new Fib(15));
      java.util.concurrent.CompletableFuture<String> late = new java.util.concurrent.CompletableFuture<>();
      java.util.concurrent.CompletableFuture<Void> all = java.util.concurrent.CompletableFuture.allOf(late, java.util.concurrent.CompletableFuture.runAsync(() -> late.complete("ok")));
      all.join();
      String got = cfv + " " + ex + " " + fib + " " + late.getNow("?") + all.isDone();
      check("CompletableFuture, ForkJoinPool", got.equals("42 CompletionException:x 610 oktrue"), got);
    } catch (Throwable t) { check("CompletableFuture, ForkJoinPool", false, String.valueOf(t)); Log.e("JNITEST", "cf", t); }
    try {
      final java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock(true);
      final java.util.concurrent.locks.Condition ready = lock.newCondition();
      final int[] counter = {0};
      Thread[] ts = new Thread[3];
      for (int k = 0; k < ts.length; k++) {
        ts[k] = new Thread(() -> {
          for (int n = 0; n < 50; n++) { lock.lock(); try { counter[0]++; if (counter[0] == 150) ready.signalAll(); } finally { lock.unlock(); } Thread.yield(); }
        });
        ts[k].start();
      }
      lock.lock();
      try { while (counter[0] < 150) ready.await(); } finally { lock.unlock(); }
      for (Thread t : ts) t.join();
      java.util.concurrent.locks.StampedLock sl = new java.util.concurrent.locks.StampedLock();
      long opt = sl.tryOptimisticRead(); long w = sl.writeLock(); boolean valid = sl.validate(opt); sl.unlockWrite(w);
      long r = sl.readLock(); int rc = sl.getReadLockCount(); sl.unlockRead(r);
      final java.util.concurrent.Phaser ph = new java.util.concurrent.Phaser(1);
      final java.util.concurrent.Exchanger<String> ex = new java.util.concurrent.Exchanger<>();
      final String[] got2 = new String[1];
      ph.register();
      Thread other = new Thread(() -> { try { got2[0] = ex.exchange("B"); } catch (InterruptedException e) { got2[0] = "int"; } ph.arriveAndDeregister(); });
      other.start();
      String mine = ex.exchange("A");
      int phase = ph.arriveAndAwaitAdvance();
      other.join();
      java.util.concurrent.LinkedTransferQueue<Integer> tq = new java.util.concurrent.LinkedTransferQueue<>();
      Thread taker = new Thread(() -> { try { tq.take(); } catch (InterruptedException e) { } });
      taker.start();
      tq.transfer(7);
      taker.join();
      java.util.concurrent.Semaphore sem = new java.util.concurrent.Semaphore(2);
      boolean a1 = sem.tryAcquire(2), a2 = sem.tryAcquire();
      sem.release(2);
      String got = counter[0] + " " + lock.isLocked() + lock.hasQueuedThreads() + " " + valid + rc + " " + mine + got2[0] + phase + " " + tq.isEmpty() + a1 + a2 + sem.availablePermits();
      check("locks: ReentrantLock and Condition, StampedLock, Phaser, Exchanger, transfer, Semaphore", got.equals("150 falsefalse false1 BA1 truetruefalse2"), got);
    } catch (Throwable t) { check("locks: ReentrantLock and Condition, StampedLock, Phaser, Exchanger, transfer, Semaphore", false, String.valueOf(t)); Log.e("JNITEST", "locks", t); }
    try {
      android.graphics.Path path = new android.graphics.Path();
      path.moveTo(0, 0); path.lineTo(100, 0); path.lineTo(100, 100);
      android.graphics.PathMeasure pm = new android.graphics.PathMeasure(path, false);
      float[] pos = new float[2], tan = new float[2];
      pm.getPosTan(150, pos, tan);
      android.graphics.Path circle = new android.graphics.Path();
      circle.addCircle(0, 0, 50, android.graphics.Path.Direction.CW);
      android.graphics.PathMeasure cm = new android.graphics.PathMeasure(circle, false);
      boolean circleOk = Math.abs(cm.getLength() - 2 * Math.PI * 50) < 3 && cm.isClosed();
      short h = android.util.Half.toHalf(1.5f);
      android.util.LruCache<String, String> lru = new android.util.LruCache<>(2);
      lru.put("a", "1"); lru.put("b", "2"); lru.get("a"); lru.put("c", "3");
      String got = (int) pm.getLength() + " " + (int) pos[0] + "," + (int) pos[1] + " " + (int) tan[0] + "," + (int) tan[1] + " " + pm.nextContour() + circleOk
          + " " + Integer.toHexString(h & 0xffff) + android.util.Half.toFloat(android.util.Half.toHalf(65520f)) + " " + android.util.Half.toHexString(h)
          + " " + lru.evictionCount() + lru.hitCount() + lru.snapshot().keySet();
      check("PathMeasure, Half, LruCache", got.equals("200 100,50 0,1 falsetrue 3e00Infinity 0x1.8p0 11[a, c]"), got);
    } catch (Throwable t) { check("PathMeasure, Half, LruCache", false, String.valueOf(t)); Log.e("JNITEST", "pm", t); }
    try {
      // Region: banded rectangles, boolean ops, paths, iteration, parcel
      android.graphics.Region ring = new android.graphics.Region(0, 0, 100, 100);
      ring.op(25, 25, 75, 75, android.graphics.Region.Op.DIFFERENCE);
      android.graphics.RegionIterator it = new android.graphics.RegionIterator(ring);
      android.graphics.Rect rr = new android.graphics.Rect();
      int rects = 0;
      while (it.next(rr)) rects++;
      android.graphics.Path disc = new android.graphics.Path();
      disc.addCircle(50, 50, 40, android.graphics.Path.Direction.CW);
      android.graphics.Region dr = new android.graphics.Region();
      dr.setPath(disc, new android.graphics.Region(0, 0, 100, 100));
      android.graphics.Region both = new android.graphics.Region();
      both.op(ring, dr, android.graphics.Region.Op.INTERSECT);
      android.os.Parcel par = android.os.Parcel.obtain();
      ring.writeToParcel(par, 0); par.setDataPosition(0);
      android.graphics.Region back = android.graphics.Region.CREATOR.createFromParcel(par);
      par.recycle();
      android.graphics.RectF pb = new android.graphics.RectF();
      ring.getBoundaryPath().computeBounds(pb, true);
      String reg = rects + " " + ring.isComplex() + ring.contains(10, 10) + ring.contains(50, 50) + " " + dr.contains(50, 50) + dr.contains(12, 12) + dr.getBounds().toShortString()
          + " " + both.contains(20, 50) + both.contains(50, 50) + back.equals(ring) + ring.quickReject(30, 30, 70, 70) + ring.quickContains(0, 0, 10, 10) + " " + pb.toShortString();
      // Canvas clip tracking
      android.graphics.Bitmap cb = android.graphics.Bitmap.createBitmap(100, 100, android.graphics.Bitmap.Config.ARGB_8888);
      android.graphics.Canvas cv = new android.graphics.Canvas(cb);
      cv.save(); cv.clipRect(10, 10, 50, 50); cv.translate(5, 5);
      android.graphics.Rect clip = cv.getClipBounds();
      boolean rej = cv.quickReject(60, 60, 70, 70) && !cv.quickReject(0, 0, 10, 10);
      cv.restore();
      String cl = clip.toShortString() + rej + cv.getClipBounds().toShortString();
      // pixels: PNG round trip, sameAs, RGB_565 buffers, extractAlpha, mesh
      android.graphics.Bitmap red = android.graphics.Bitmap.createBitmap(4, 4, android.graphics.Bitmap.Config.ARGB_8888);
      red.eraseColor(0xffff0000); red.setPixel(1, 1, 0x800000ff);
      java.io.ByteArrayOutputStream png = new java.io.ByteArrayOutputStream();
      boolean comp = red.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, png);
      android.graphics.Bitmap dec = android.graphics.BitmapFactory.decodeByteArray(png.toByteArray(), 0, png.size());
      android.graphics.Bitmap c565 = android.graphics.Bitmap.createBitmap(2, 1, android.graphics.Bitmap.Config.RGB_565);
      c565.setPixel(0, 0, 0xff00ff00);
      java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(c565.getByteCount());
      c565.copyPixelsToBuffer(buf); buf.rewind();
      android.graphics.Bitmap d565 = android.graphics.Bitmap.createBitmap(2, 1, android.graphics.Bitmap.Config.RGB_565);
      d565.copyPixelsFromBuffer(buf);
      android.graphics.Bitmap alpha = red.extractAlpha();
      android.graphics.Bitmap mesh = android.graphics.Bitmap.createBitmap(20, 20, android.graphics.Bitmap.Config.ARGB_8888);
      new android.graphics.Canvas(mesh).drawBitmapMesh(red, 1, 1, new float[] {0, 0, 20, 0, 0, 20, 20, 20}, 0, null, 0, null);
      String px = comp + " " + Integer.toHexString(dec.getPixel(0, 0)) + " " + (dec.getPixel(1, 1) >>> 24) + red.sameAs(dec.copy(android.graphics.Bitmap.Config.ARGB_8888, false))
          + " " + c565.getRowBytes() + Integer.toHexString(buf.get(0) & 0xff) + Integer.toHexString(buf.get(1) & 0xff) + " " + Integer.toHexString(d565.getPixel(0, 0)) + Integer.toHexString(d565.getPixel(1, 0))
          + " " + alpha.getConfig() + Integer.toHexString(alpha.getPixel(1, 1)) + " " + Integer.toHexString(mesh.getPixel(15, 15));
      // nine-patch: 3x3 source, corners red, edges green, center blue, stretched to 30x30
      android.graphics.Bitmap src = android.graphics.Bitmap.createBitmap(3, 3, android.graphics.Bitmap.Config.ARGB_8888);
      src.setDensity(android.util.DisplayMetrics.DENSITY_DEFAULT);
      for (int y = 0; y < 3; y++) for (int x = 0; x < 3; x++) src.setPixel(x, y, x == 1 && y == 1 ? 0xff0000ff : (x == 1 || y == 1) ? 0xff00ff00 : 0xffff0000);
      byte[] chunk = new byte[32 + 4 * 4 + 4 * 9];
      chunk[1] = 2; chunk[2] = 2; chunk[3] = 9;
      int[] ints = {1, 1, 1, 1};   // padding left, right, top, bottom
      for (int i = 0; i < 4; i++) chunk[12 + 4 * i] = (byte) ints[i];
      chunk[32] = 1; chunk[36] = 2; chunk[40] = 1; chunk[44] = 2;
      android.graphics.NinePatch np = new android.graphics.NinePatch(src, chunk, "t");
      android.graphics.Bitmap out9 = android.graphics.Bitmap.createBitmap(30, 30, android.graphics.Bitmap.Config.ARGB_8888);
      android.graphics.Canvas c9 = new android.graphics.Canvas(out9);
      c9.setDensity(android.util.DisplayMetrics.DENSITY_DEFAULT);
      np.draw(c9, new android.graphics.Rect(0, 0, 30, 30));
      android.graphics.drawable.NinePatchDrawable npd = new android.graphics.drawable.NinePatchDrawable(np);
      android.graphics.Rect pad = new android.graphics.Rect();
      boolean hasPad = npd.getPadding(pad);
      String nine = android.graphics.NinePatch.isNinePatchChunk(chunk) + " " + Integer.toHexString(out9.getPixel(0, 0)) + Integer.toHexString(out9.getPixel(15, 15)) + Integer.toHexString(out9.getPixel(15, 0))
          + Integer.toHexString(out9.getPixel(29, 29)) + " " + hasPad + pad.toShortString() + (((Object) npd) instanceof android.graphics.drawable.BitmapDrawable);
      // text along a path draws something
      android.graphics.Bitmap tb = android.graphics.Bitmap.createBitmap(200, 50, android.graphics.Bitmap.Config.ARGB_8888);
      android.graphics.Path line = new android.graphics.Path(); line.moveTo(10, 40); line.lineTo(190, 40);
      android.graphics.Paint tp = new android.graphics.Paint(); tp.setTextSize(20);
      new android.graphics.Canvas(tb).drawTextOnPath("Hello", line, 0, 0, tp);
      int[] tpx = new int[200 * 50]; tb.getPixels(tpx, 0, 200, 0, 0, 200, 50);
      int ink = 0;
      for (int v : tpx) if ((v >>> 24) > 0) ink++;
      String got = reg + " | " + cl + " | " + px + " | " + nine + " | " + (ink > 50);
      check("graphics: Region ops, clip bounds, PNG compress, pixel buffers, nine-patch, text on path", got.equals(
          "4 truetruefalse truefalse[10,10][90,90] truefalsetruetruefalse [0.0,0.0][100.0,100.0] | [5,5][45,45]true[0,0][100,100] | true ffff0000 128true 4e07 ff00ff00ff000000 ALPHA_880000000 ffff0000 | true ffff0000ff0000ffff00ff00ffff0000 true[1,1][1,1]false | true"), got);
    } catch (Throwable t) { check("graphics: Region ops, clip bounds, PNG compress, pixel buffers, nine-patch, text on path", false, String.valueOf(t)); Log.e("JNITEST", "gfx", t); }
    try {
      android.graphics.Matrix pm = new android.graphics.Matrix();
      boolean poly = pm.setPolyToPoly(new float[] {0, 0, 100, 0, 100, 100, 0, 100}, 0, new float[] {0, 0, 100, 0, 80, 100, 20, 100}, 0, 4);
      float[] q = {100, 100, 0, 100, 50, 0};
      pm.mapPoints(q);
      android.graphics.Path sq = new android.graphics.Path();
      sq.addRect(0, 0, 100, 100, android.graphics.Path.Direction.CW);
      android.graphics.Path hole = new android.graphics.Path();
      hole.addCircle(50, 50, 25, android.graphics.Path.Direction.CW);
      android.graphics.Path diff = new android.graphics.Path();
      boolean opOk = diff.op(sq, hole, android.graphics.Path.Op.DIFFERENCE);
      android.graphics.Region all = new android.graphics.Region(-10, -10, 200, 200), dr = new android.graphics.Region();
      dr.setPath(diff, all);
      android.graphics.Path uni = new android.graphics.Path(sq);
      android.graphics.Path right = new android.graphics.Path(); right.addRect(50, 50, 150, 150, android.graphics.Path.Direction.CW);
      uni.op(right, android.graphics.Path.Op.UNION);
      android.graphics.Region ur = new android.graphics.Region(); ur.setPath(uni, all);
      android.graphics.Path frame = new android.graphics.Path();
      frame.addRect(0, 0, 100, 100, android.graphics.Path.Direction.CW);
      frame.addRect(25, 25, 75, 75, android.graphics.Path.Direction.CCW);
      android.graphics.Region fr = new android.graphics.Region(); fr.setPath(frame, all);
      android.graphics.Path poly4 = new android.graphics.Path();
      poly4.moveTo(0, 0); poly4.lineTo(10, 0); poly4.lineTo(10, 5); poly4.lineTo(0, 5); poly4.close();
      android.graphics.Path tri = new android.graphics.Path();
      tri.moveTo(0, 0); tri.lineTo(10, 0); tri.lineTo(0, 5); tri.close();
      android.graphics.RectF ir = new android.graphics.RectF();
      boolean rects = sq.isRect(null) && poly4.isRect(ir) && !tri.isRect(null);
      android.graphics.Path rot = new android.graphics.Path();
      rot.addRect(0, 0, 100, 50, android.graphics.Path.Direction.CW);
      android.graphics.Matrix r90 = new android.graphics.Matrix(); r90.setRotate(90);
      rot.transform(r90);
      android.graphics.RectF rb = new android.graphics.RectF(); rot.computeBounds(rb, true);
      StringBuilder verbs = new StringBuilder();
      android.graphics.PathIterator pit = tri.getPathIterator();
      float[] pts = new float[8];
      int v;
      while ((v = pit.next(pts, 0)) != android.graphics.PathIterator.VERB_DONE) verbs.append(v);
      android.graphics.Path rr = new android.graphics.Path();
      rr.addRoundRect(new android.graphics.RectF(0, 0, 100, 100), new float[] {50, 50, 0, 0, 0, 0, 0, 0}, android.graphics.Path.Direction.CW);
      android.graphics.Region rrr = new android.graphics.Region(); rrr.setPath(rr, all);
      String got = poly + " " + Math.round(q[0]) + "," + Math.round(q[1]) + " " + Math.round(q[2]) + "," + Math.round(q[3]) + " " + Math.round(q[4]) + "," + Math.round(q[5])
          + " | " + opOk + dr.contains(10, 10) + dr.contains(50, 50) + dr.contains(50, 20) + " " + ur.contains(120, 120) + ur.contains(120, 20) + ur.getBounds().toShortString()
          + " " + fr.contains(10, 10) + fr.contains(50, 50) + " " + rects + ir.toShortString() + " " + rb.toShortString() + " " + verbs + " " + rrr.contains(3, 3) + rrr.contains(97, 3);
      check("geometry: setPolyToPoly, Path.op, path direction, isRect, rotated rect, PathIterator, corner radii", got.equals(
          "true 80,100 20,100 50,0 | truetruefalsetrue truefalse[0,0][150,150] truefalse true[0.0,0.0][10.0,5.0] [-50.0,0.0][0.0,100.0] 0115 falsetrue"), got);
    } catch (Throwable t) { check("geometry: setPolyToPoly, Path.op, path direction, isRect, rotated rect, PathIterator, corner radii", false, String.valueOf(t)); Log.e("JNITEST", "geo", t); }
    try {
      // shaders: bitmap patterns (repeat, mirror), gradients with local matrices and tiling, sweep, compose
      android.graphics.Bitmap q = android.graphics.Bitmap.createBitmap(4, 4, android.graphics.Bitmap.Config.ARGB_8888);
      for (int y = 0; y < 4; y++) for (int x = 0; x < 4; x++) q.setPixel(x, y, y < 2 ? (x < 2 ? 0xffff0000 : 0xff00ff00) : (x < 2 ? 0xff0000ff : 0xffffffff));
      android.graphics.Bitmap out = android.graphics.Bitmap.createBitmap(16, 16, android.graphics.Bitmap.Config.ARGB_8888);
      android.graphics.Canvas oc = new android.graphics.Canvas(out);
      android.graphics.Paint sp = new android.graphics.Paint();
      sp.setShader(new android.graphics.BitmapShader(q, android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.REPEAT));
      oc.drawRect(0, 0, 16, 16, sp);
      String rep = Integer.toHexString(out.getPixel(4, 0)) + Integer.toHexString(out.getPixel(6, 0)) + Integer.toHexString(out.getPixel(9, 6));
      out.eraseColor(0);
      sp.setShader(new android.graphics.BitmapShader(q, android.graphics.Shader.TileMode.MIRROR, android.graphics.Shader.TileMode.CLAMP));
      oc.drawRect(0, 0, 16, 4, sp);
      String mir = Integer.toHexString(out.getPixel(4, 0)) + Integer.toHexString(out.getPixel(6, 0));
      // a shader matrix set after setShader still applies (CircleImageView does this)
      android.graphics.BitmapShader bs = new android.graphics.BitmapShader(q, android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP);
      sp.setShader(bs);
      android.graphics.Matrix sm = new android.graphics.Matrix(); sm.setScale(4, 4);
      bs.setLocalMatrix(sm);
      out.eraseColor(0);
      oc.drawRect(0, 0, 16, 16, sp);
      String loc = Integer.toHexString(out.getPixel(3, 3)) + Integer.toHexString(out.getPixel(12, 12));
      android.graphics.Bitmap gb = android.graphics.Bitmap.createBitmap(100, 100, android.graphics.Bitmap.Config.ARGB_8888);
      android.graphics.Canvas gc = new android.graphics.Canvas(gb);
      android.graphics.LinearGradient lg = new android.graphics.LinearGradient(0, 0, 100, 0, 0xff000000, 0xffffffff, android.graphics.Shader.TileMode.CLAMP);
      android.graphics.Matrix r90 = new android.graphics.Matrix(); r90.setRotate(90);
      lg.setLocalMatrix(r90);
      android.graphics.Paint gp = new android.graphics.Paint(); gp.setShader(lg);
      gc.drawRect(0, 0, 100, 100, gp);
      boolean vertical = (gb.getPixel(10, 90) & 0xff) > 200 && (gb.getPixel(10, 5) & 0xff) < 40 && (gb.getPixel(90, 5) & 0xff) < 40;
      gp.setShader(new android.graphics.LinearGradient(0, 0, 10, 0, 0xff000000, 0xffffffff, android.graphics.Shader.TileMode.REPEAT));
      gc.drawRect(0, 0, 100, 100, gp);
      boolean repeats = (gb.getPixel(51, 50) & 0xff) < 60 && (gb.getPixel(58, 50) & 0xff) > 180;
      gp.setShader(new android.graphics.SweepGradient(50, 50, 0xffff0000, 0xff0000ff));
      gc.drawRect(0, 0, 100, 100, gp);
      int east = gb.getPixel(95, 52), west = gb.getPixel(5, 50);
      boolean sweep = ((east >> 16) & 0xff) > 200 && ((west >> 16) & 0xff) > 90 && (west & 0xff) > 90;
      android.graphics.Bitmap redB = android.graphics.Bitmap.createBitmap(1, 1, android.graphics.Bitmap.Config.ARGB_8888); redB.eraseColor(0xffff0000);
      gp.setShader(new android.graphics.ComposeShader(new android.graphics.BitmapShader(redB, android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.REPEAT),
          new android.graphics.LinearGradient(0, 0, 100, 0, 0x000000ff, 0xff0000ff, android.graphics.Shader.TileMode.CLAMP), android.graphics.PorterDuff.Mode.SRC_OVER));
      gb.eraseColor(0);
      gc.drawRect(0, 0, 100, 100, gp);
      boolean compose = Integer.toHexString(gb.getPixel(0, 50)).startsWith("fffe") || Integer.toHexString(gb.getPixel(0, 50)).startsWith("ffff");
      compose &= (gb.getPixel(99, 50) & 0xff) > 240;
      // Camera: Sk3DView projections
      android.graphics.Camera cam = new android.graphics.Camera();
      android.graphics.Matrix cm = new android.graphics.Matrix();
      cam.getMatrix(cm);
      boolean ident = cm.isIdentity();
      cam.save(); cam.rotateY(180); cam.getMatrix(cm); cam.restore();
      float[] pt = {100, 0}; cm.mapPoints(pt);
      cam.save(); cam.rotateY(90); cam.getMatrix(cm); float n90 = cam.dotWithNormal(0, 0, 1); cam.restore();
      float[] pt90 = {100, 0}; cm.mapPoints(pt90);
      cam.save(); cam.rotateX(30); cam.getMatrix(cm); cam.restore();
      boolean persp = !cm.isAffine();
      cam.setLocation(0, 0, -16);
      // Movie: a two-frame animated GIF (red, then blue)
      byte[] gif = {71, 73, 70, 56, 57, 97, 2, 0, 2, 0, -15, 0, 0, -1, 0, 0, 0, 0, -1, 0, -1, 0, -1, -1, -1, 33, -1, 11, 78, 69, 84, 83, 67, 65, 80, 69, 50, 46, 48, 3, 1, 0, 0, 0,
          33, -7, 4, 4, 10, 0, 0, 0, 44, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 3, 4, 8, 20, 0, 33, -7, 4, 4, 10, 0, 0, 0, 44, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 3, 76, -104, 20, 0, 59};
      android.graphics.Movie mv = android.graphics.Movie.decodeByteArray(gif, 0, gif.length);
      android.graphics.Bitmap mb = android.graphics.Bitmap.createBitmap(2, 2, android.graphics.Bitmap.Config.ARGB_8888);
      android.graphics.Canvas mc = new android.graphics.Canvas(mb);
      mv.setTime(50); mv.draw(mc, 0, 0);
      String f1 = Integer.toHexString(mb.getPixel(1, 1));
      mv.setTime(150); mv.draw(mc, 0, 0);
      String f2 = Integer.toHexString(mb.getPixel(1, 1));
      String got = rep + " " + mir + " " + loc + " " + vertical + repeats + sweep + compose + " " + ident + Math.round(pt[0]) + "," + Math.round(pt[1]) + " " + Math.round(pt90[0]) + " " + (Math.abs(n90) < 1e-3) + persp
          + " " + cam.getLocationZ() + " " + mv.width() + "x" + mv.height() + " " + mv.duration() + " " + f1 + f2;
      check("shaders, Camera, Movie: patterns, gradient matrices and tiling, sweep, compose, 3D projection, GIF frames", got.equals(
          "ffff0000ff00ff00ff0000ff ff00ff00ffff0000 ffff0000ffffffff truetruetruetrue true-100,0 0 truetrue -16.0 2x2 200 ffff0000ff0000ff"), got);
    } catch (Throwable t) { check("shaders, Camera, Movie: patterns, gradient matrices and tiling, sweep, compose, 3D projection, GIF frames", false, String.valueOf(t)); Log.e("JNITEST", "shader", t); }
    try {
      // drawables: layer padding modes, gravity, fractional insets, shapes, corner radii, rings, state colors, adaptive icons, icons
      android.graphics.drawable.GradientDrawable padded = new android.graphics.drawable.GradientDrawable();
      padded.setPadding(5, 5, 5, 5);
      android.graphics.drawable.ColorDrawable inner = new android.graphics.drawable.ColorDrawable(0xff00ff00);
      android.graphics.drawable.LayerDrawable ld = new android.graphics.drawable.LayerDrawable(new android.graphics.drawable.Drawable[] {padded, inner});
      ld.setBounds(0, 0, 100, 100);
      String nest = inner.getBounds().toShortString();
      ld.setPaddingMode(android.graphics.drawable.LayerDrawable.PADDING_MODE_STACK);
      String stack = inner.getBounds().toShortString();
      android.graphics.drawable.ColorDrawable dot = new android.graphics.drawable.ColorDrawable(0xff0000ff);
      int di = ld.addLayer(dot);
      ld.setLayerSize(di, 20, 20); ld.setLayerGravity(di, android.view.Gravity.CENTER);
      String grav = dot.getBounds().toShortString() + ld.findIndexByLayerId(-5);
      android.graphics.drawable.ColorDrawable c2 = new android.graphics.drawable.ColorDrawable(0xffff0000);
      android.graphics.drawable.InsetDrawable frac = new android.graphics.drawable.InsetDrawable(c2, 0.1f);
      frac.setBounds(0, 0, 100, 100);
      String fr = c2.getBounds().toShortString();
      android.graphics.Bitmap sb = android.graphics.Bitmap.createBitmap(100, 100, android.graphics.Bitmap.Config.ARGB_8888);
      android.graphics.Canvas sc = new android.graphics.Canvas(sb);
      android.graphics.drawable.ShapeDrawable oval = new android.graphics.drawable.ShapeDrawable(new android.graphics.drawable.shapes.OvalShape());
      oval.getPaint().setColor(0xffff0000);
      oval.setBounds(0, 0, 100, 100); oval.draw(sc);
      String ov = Integer.toHexString(sb.getPixel(50, 50)) + Integer.toHexString(sb.getPixel(2, 2));
      sb.eraseColor(0);
      float[] r8 = {20, 20, 20, 20, 20, 20, 20, 20};
      android.graphics.drawable.ShapeDrawable ringS = new android.graphics.drawable.ShapeDrawable(new android.graphics.drawable.shapes.RoundRectShape(r8, new android.graphics.RectF(20, 20, 20, 20), null));
      ringS.getPaint().setColor(0xff0000ff);
      ringS.setBounds(0, 0, 100, 100); ringS.draw(sc);
      String rr = Integer.toHexString(sb.getPixel(50, 50)) + Integer.toHexString(sb.getPixel(10, 50));
      sb.eraseColor(0);
      android.graphics.drawable.GradientDrawable tl = new android.graphics.drawable.GradientDrawable();
      tl.setColor(0xff00ff00); tl.setCornerRadii(new float[] {40, 40, 0, 0, 0, 0, 0, 0});
      tl.setBounds(0, 0, 100, 100); tl.draw(sc);
      String corner = Integer.toHexString(sb.getPixel(2, 2)) + Integer.toHexString(sb.getPixel(97, 2)) + tl.getCornerRadii()[0];
      sb.eraseColor(0);
      android.graphics.drawable.GradientDrawable ring = new android.graphics.drawable.GradientDrawable();
      ring.setShape(android.graphics.drawable.GradientDrawable.RING); ring.setColor(0xffff00ff); ring.setInnerRadius(20); ring.setThickness(10);
      ring.setBounds(0, 0, 100, 100); ring.draw(sc);
      String rg = Integer.toHexString(sb.getPixel(50, 50)) + Integer.toHexString(sb.getPixel(75, 50)) + ring.getThickness();
      android.content.res.ColorStateList csl = new android.content.res.ColorStateList(new int[][] {{android.R.attr.state_pressed}, {}}, new int[] {0xffff0000, 0xff0000ff});
      android.graphics.drawable.GradientDrawable sg = new android.graphics.drawable.GradientDrawable();
      sg.setColor(csl);
      sg.setState(new int[] {android.R.attr.state_pressed});
      sb.eraseColor(0); sg.setBounds(0, 0, 10, 10); sg.draw(sc);
      String st = Integer.toHexString(sb.getPixel(5, 5)) + sg.isStateful();
      android.graphics.drawable.ColorStateListDrawable cld = new android.graphics.drawable.ColorStateListDrawable(csl);
      cld.setState(new int[0]);
      sb.eraseColor(0); cld.setBounds(0, 0, 10, 10); cld.draw(sc);
      st += Integer.toHexString(sb.getPixel(5, 5));
      android.graphics.drawable.ColorDrawable bg = new android.graphics.drawable.ColorDrawable(0xffffff00);
      android.graphics.drawable.AdaptiveIconDrawable aid = new android.graphics.drawable.AdaptiveIconDrawable(bg, new android.graphics.drawable.ColorDrawable(0x00000000));
      sb.eraseColor(0); aid.setBounds(0, 0, 100, 100); aid.draw(sc);
      String ad = Integer.toHexString(sb.getPixel(50, 50)) + Integer.toHexString(sb.getPixel(3, 3)) + bg.getBounds().toShortString() + android.graphics.drawable.AdaptiveIconDrawable.getExtraInsetFraction();
      android.graphics.Bitmap ib = android.graphics.Bitmap.createBitmap(12, 7, android.graphics.Bitmap.Config.ARGB_8888);
      android.graphics.drawable.Icon icon = android.graphics.drawable.Icon.createWithBitmap(ib);
      android.graphics.drawable.Drawable idr = icon.loadDrawable(this);
      android.os.Parcel ip = android.os.Parcel.obtain();
      icon.writeToParcel(ip, 0); ip.setDataPosition(0);
      android.graphics.drawable.Icon back = android.graphics.drawable.Icon.CREATOR.createFromParcel(ip);
      ip.recycle();
      android.graphics.drawable.Icon ri = android.graphics.drawable.Icon.createWithResource("com.a2w.jnitest", 0x7f010000);
      String ic = idr.getClass().getSimpleName() + back.getType() + " " + ri.getResId() + ri.getResPackage().length() + " " + back.toString();
      android.graphics.Picture pic = new android.graphics.Picture();
      android.graphics.Canvas pc = pic.beginRecording(10, 10); pc.drawColor(0xff123456); pic.endRecording();
      android.graphics.drawable.PictureDrawable pd = new android.graphics.drawable.PictureDrawable(pic);
      sb.eraseColor(0); pd.setBounds(20, 20, 30, 30); pd.draw(sc);
      String pdr = Integer.toHexString(sb.getPixel(25, 25)) + Integer.toHexString(sb.getPixel(35, 25)) + pd.getIntrinsicWidth();
      android.content.res.ColorStateList gray = android.content.res.ColorStateList.valueOf(0xffffffff).withLStar(50);
      int gv = gray.getDefaultColor() & 0xff;
      android.os.Parcel cp = android.os.Parcel.obtain();
      csl.writeToParcel(cp, 0); cp.setDataPosition(0);
      android.content.res.ColorStateList csl2 = android.content.res.ColorStateList.CREATOR.createFromParcel(cp);
      cp.recycle();
      String cs = (gv > 110 && gv < 125) + " " + Integer.toHexString(csl2.getColorForState(new int[] {android.R.attr.state_pressed}, 0)) + csl.isOpaque() + csl.isStateful();
      android.graphics.drawable.RotateDrawable rot = new android.graphics.drawable.RotateDrawable(); rot.setFromDegrees(10); rot.setToDegrees(100); rot.setPivotXRelative(false);
      String ro = rot.getFromDegrees() + "," + rot.getToDegrees() + rot.isPivotXRelative() + (((Object) rot) instanceof android.graphics.drawable.DrawableWrapper);
      String got = nest + stack + " " + grav + " " + fr + " " + ov + " " + rr + " " + corner + " " + rg + " " + st + " " + ad + " " + ic + " " + pdr + " " + cs + " " + ro;
      check("drawables: layers (nest, stack, gravity), inset fractions, shapes, corner radii, rings, state colors, adaptive icons, Icon, PictureDrawable", got.equals(
          "[5,5][95,95][0,0][100,100] [40,40][60,60]-1 [10,10][90,90] ffff00000 0ff0000ff 0ff00ff0040.0 0ffff00ff10 ffff0000trueff0000ff ffffff000[-25,-25][125,125]0.25 BitmapDrawable1 213077196815 Icon(typ=BITMAP size=12x7) ff123456010 true ffff0000truetrue 10.0,100.0falsetrue"), got);
    } catch (Throwable t) { check("drawables: layers (nest, stack, gravity), inset fractions, shapes, corner radii, rings, state colors, adaptive icons, Icon, PictureDrawable", false, String.valueOf(t)); Log.e("JNITEST", "drawables", t); }
    try {
      // input: key characters and meta states, key names, motion history and axes, generic motion (wheel) to scroll views, hover
      android.view.KeyEvent ka = new android.view.KeyEvent(0, 0, android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_A, 0,
          android.view.KeyEvent.META_SHIFT_ON | android.view.KeyEvent.META_SHIFT_LEFT_ON);
      android.view.KeyEvent k2 = new android.view.KeyEvent(0, 0, android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_2, 0, android.view.KeyEvent.META_SHIFT_ON);
      android.view.KeyEvent kc = new android.view.KeyEvent(0, 0, android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_A, 0, android.view.KeyEvent.META_CAPS_LOCK_ON);
      String keys = (char) ka.getUnicodeChar() + "" + (char) k2.getUnicodeChar() + (char) kc.getUnicodeChar() + (char) ka.getUnicodeChar(0)
          + " " + ka.hasModifiers(android.view.KeyEvent.META_SHIFT_ON) + ka.hasModifiers(android.view.KeyEvent.META_SHIFT_LEFT_ON) + ka.hasNoModifiers()
          + " " + android.view.KeyEvent.keyCodeToString(android.view.KeyEvent.KEYCODE_ENTER) + android.view.KeyEvent.keyCodeFromString("KEYCODE_SPACE")
          + android.view.KeyEvent.keyCodeFromString("BUTTON_A") + " " + android.view.KeyEvent.isModifierKey(android.view.KeyEvent.KEYCODE_SHIFT_LEFT)
          + android.view.KeyEvent.isGamepadButton(android.view.KeyEvent.KEYCODE_BUTTON_X) + " " + android.view.KeyCharacterMap.getDeadChar('`', 'e')
          + " " + android.view.KeyEvent.normalizeMetaState(android.view.KeyEvent.META_CTRL_LEFT_ON);
      android.view.MotionEvent me = android.view.MotionEvent.obtain(0, 10, android.view.MotionEvent.ACTION_MOVE, 5, 6, 0);
      me.addBatch(20, 7, 8, 0.5f, 0.2f, 0);
      me.offsetLocation(100, 0);
      android.os.Parcel mp = android.os.Parcel.obtain();
      me.writeToParcel(mp, 0); mp.setDataPosition(0);
      android.view.MotionEvent me2 = android.view.MotionEvent.CREATOR.createFromParcel(mp);
      mp.recycle();
      String motion = me.getHistorySize() + " " + me.getHistoricalX(0) + "," + me.getHistoricalY(0) + " " + me.getX() + "," + me.getY() + " " + me.getPressure()
          + " " + me.getEventTime() + me.getHistoricalEventTime(0) + " " + me2.getHistorySize() + me2.getX() + " " + android.view.MotionEvent.axisToString(android.view.MotionEvent.AXIS_VSCROLL)
          + android.view.MotionEvent.axisFromString("AXIS_HSCROLL") + " " + android.view.MotionEvent.actionToString(android.view.MotionEvent.ACTION_POINTER_DOWN | (1 << 8));
      // a wheel notch down on a scroll view scrolls it by the scroll factor (64dp)
      android.widget.ScrollView sv = new android.widget.ScrollView(this);
      android.view.View tall = new android.view.View(this);
      tall.setMinimumHeight(5000);
      sv.addView(tall, new android.widget.FrameLayout.LayoutParams(100, 5000));
      sv.measure(android.view.View.MeasureSpec.makeMeasureSpec(100, android.view.View.MeasureSpec.EXACTLY), android.view.View.MeasureSpec.makeMeasureSpec(300, android.view.View.MeasureSpec.EXACTLY));
      sv.layout(0, 0, 100, 300);
      android.view.MotionEvent.PointerProperties pp = new android.view.MotionEvent.PointerProperties(); pp.id = 0; pp.toolType = android.view.MotionEvent.TOOL_TYPE_MOUSE;
      android.view.MotionEvent.PointerCoords pc = new android.view.MotionEvent.PointerCoords(); pc.x = 50; pc.y = 50; pc.setAxisValue(android.view.MotionEvent.AXIS_VSCROLL, -1);
      android.view.MotionEvent wheel = android.view.MotionEvent.obtain(0, 0, android.view.MotionEvent.ACTION_SCROLL, 1, new android.view.MotionEvent.PointerProperties[] {pp},
          new android.view.MotionEvent.PointerCoords[] {pc}, 0, 0, 1, 1, 0, 0, android.view.InputDevice.SOURCE_MOUSE, 0);
      boolean wh = sv.dispatchGenericMotionEvent(wheel);
      int expect = Math.round(64 * getResources().getDisplayMetrics().density);
      // hover: a clickable child becomes hovered when the pointer enters it
      android.widget.FrameLayout fl = new android.widget.FrameLayout(this);
      android.view.View hv = new android.view.View(this); hv.setClickable(true);
      fl.addView(hv, new android.widget.FrameLayout.LayoutParams(50, 50));
      fl.measure(android.view.View.MeasureSpec.makeMeasureSpec(100, android.view.View.MeasureSpec.EXACTLY), android.view.View.MeasureSpec.makeMeasureSpec(100, android.view.View.MeasureSpec.EXACTLY));
      fl.layout(0, 0, 100, 100);
      final StringBuilder hovers = new StringBuilder();
      hv.setOnGenericMotionListener(new GenericLog(hovers));
      pc.x = 20; pc.y = 20;
      android.view.MotionEvent hover = android.view.MotionEvent.obtain(0, 0, android.view.MotionEvent.ACTION_HOVER_MOVE, 1, new android.view.MotionEvent.PointerProperties[] {pp},
          new android.view.MotionEvent.PointerCoords[] {pc}, 0, 0, 1, 1, 0, 0, android.view.InputDevice.SOURCE_MOUSE, 0);
      fl.dispatchGenericMotionEvent(hover);
      boolean hovered = hv.isHovered();
      pc.x = 80;
      fl.dispatchGenericMotionEvent(android.view.MotionEvent.obtain(0, 0, android.view.MotionEvent.ACTION_HOVER_MOVE, 1, new android.view.MotionEvent.PointerProperties[] {pp},
          new android.view.MotionEvent.PointerCoords[] {pc}, 0, 0, 1, 1, 0, 0, android.view.InputDevice.SOURCE_MOUSE, 0));
      String gen = wh + " " + (sv.getScrollY() == expect) + " " + hovered + hv.isHovered() + " " + hovers + " " + ((Object) android.view.View.ALPHA.get(hv));
      String got = keys + " | " + motion + " | " + gen;
      check("input: key characters, meta states and names, motion history, parcel, wheel scrolling, hover, view properties", got.equals(
          "A@Aa truetruefalse KEYCODE_ENTER6296 truetrue 232 12288 | 1 105.0,6.0 107.0,8.0 0.5 2010 1107.0 AXIS_VSCROLL10 ACTION_POINTER_DOWN(1) | true true truefalse 97710 1.0"), got);
    } catch (Throwable t) { check("input: key characters, meta states and names, motion history, parcel, wheel scrolling, hover, view properties", false, String.valueOf(t)); Log.e("JNITEST", "input", t); }
    try {
      // widgets: compound button indicators, Switch, ToggleButton, ProgressBar min and tints, SeekBar keys, CheckedTextView, list scrolling and checks
      final int W = android.view.View.MeasureSpec.makeMeasureSpec(300, android.view.View.MeasureSpec.EXACTLY), HW = android.view.View.MeasureSpec.makeMeasureSpec(80, android.view.View.MeasureSpec.AT_MOST);
      android.widget.CheckBox cb = new android.widget.CheckBox(this);
      cb.setText("box");
      cb.measure(W, HW); cb.layout(0, 0, 300, cb.getMeasuredHeight());
      boolean cbOk = cb.getButtonDrawable() != null && cb.getButtonDrawable().getIntrinsicWidth() > 0 && cb.getCompoundPaddingLeft() >= cb.getButtonDrawable().getIntrinsicWidth() && cb.isFocusable();
      cb.setChecked(true);
      boolean cbState = java.util.Arrays.binarySearch(sorted(cb.getButtonDrawable().getState()), android.R.attr.state_checked) >= 0;
      android.widget.RadioButton rb = new android.widget.RadioButton(this);
      rb.toggle(); rb.toggle();
      android.widget.Switch sw = new android.widget.Switch(this);
      sw.measure(android.view.View.MeasureSpec.makeMeasureSpec(0, android.view.View.MeasureSpec.UNSPECIFIED), android.view.View.MeasureSpec.makeMeasureSpec(0, android.view.View.MeasureSpec.UNSPECIFIED));
      sw.layout(0, 0, sw.getMeasuredWidth(), sw.getMeasuredHeight());
      int sw0 = pixelAt(sw, sw.getWidth() * 3 / 4, sw.getHeight() / 2);
      sw.setChecked(true);
      int sw1 = pixelAt(sw, sw.getWidth() * 3 / 4, sw.getHeight() / 2);
      String swGot = (sw.getThumbDrawable() != null) + "" + (sw.getTrackDrawable() != null) + sw.getTextOn() + sw.getShowText() + (sw.getMeasuredWidth() >= sw.getSwitchMinWidth()) + (sw0 != sw1);
      android.widget.ToggleButton tb = new android.widget.ToggleButton(this);
      String tbGot = tb.getText() + "";
      tb.setChecked(true);
      tbGot += String.valueOf(tb.getText()) + (tb.getBackground() instanceof android.graphics.drawable.LayerDrawable && ((android.graphics.drawable.LayerDrawable) tb.getBackground()).findDrawableByLayerId(android.R.id.toggle) != null);
      android.widget.ProgressBar pb = new android.widget.ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
      pb.setMax(20); pb.setMin(10); pb.setProgress(5);
      int pbLow = pb.getProgress();
      pb.setProgressTintList(android.content.res.ColorStateList.valueOf(0xffff0000));
      String pbGot = pbLow + " " + pb.getMin() + (pb.getProgressTintList() != null) + pb.isAnimating() + (pb.getInterpolator() != null);
      android.widget.SeekBar sb = new android.widget.SeekBar(this);
      sb.setMax(100); sb.setProgress(50);
      sb.onKeyDown(android.view.KeyEvent.KEYCODE_PLUS, new android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_PLUS));
      android.widget.CheckedTextView ct = (android.widget.CheckedTextView) android.view.LayoutInflater.from(this).inflate(android.R.layout.simple_list_item_single_choice, null);
      ct.setChecked(true);
      boolean ctOk = ct.getCheckMarkDrawable() != null && java.util.Arrays.binarySearch(sorted(ct.getCheckMarkDrawable().getState()), android.R.attr.state_checked) >= 0;
      android.widget.ListView wl = new android.widget.ListView(this);
      java.util.ArrayList<String> rows = new java.util.ArrayList<>();
      for (int i = 0; i < 20; i++) rows.add("row " + i);
      wl.setAdapter(new android.widget.ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rows));
      wl.measure(W, android.view.View.MeasureSpec.makeMeasureSpec(150, android.view.View.MeasureSpec.EXACTLY));
      wl.layout(0, 0, 300, 150);
      String wlGot = wl.canScrollList(1) + "" + wl.canScrollList(-1);
      wl.scrollListBy(30);
      wlGot += wl.canScrollList(-1);
      wl.setChoiceMode(android.widget.AbsListView.CHOICE_MODE_MULTIPLE);
      wl.setItemChecked(1, true); wl.setItemChecked(3, true); wl.setItemChecked(1, false);
      wlGot += " " + wl.getCheckedItemCount() + wl.getCheckedItemIds().length;
      String got = cbOk + "" + cbState + rb.isChecked() + " " + swGot + " " + tbGot + " " + pbGot + " " + sb.getProgress() + " " + ctOk + " " + wlGot;
      check("widgets: compound button indicators, Switch, ToggleButton, ProgressBar min and tints, SeekBar keys, CheckedTextView, list scrolling and checks",
          got.equals("truetruetrue truetrueONtruetruetrue OFFONtrue 10 10truefalsetrue 55 true truefalsetrue 10"), got);
    } catch (Throwable t) { check("widgets: compound button indicators, Switch, ToggleButton, ProgressBar min and tints, SeekBar keys, CheckedTextView, list scrolling and checks", false, String.valueOf(t)); Log.e("JNITEST", "widgets", t); }
    try {
      // action modes: the window's contextual bar (items, finish), a modal multiple-choice list
      final StringBuilder am = new StringBuilder();
      android.view.ActionMode mode = startActionMode(new ModeLog(am));
      String modeGot = "null";
      if (mode != null) {
        mode.setTitle("2 selected");
        modeGot = mode.getTitle() + " " + mode.getMenu().size() + mode.getType();
        mode.getMenu().performIdentifierAction(1, 0);
      }
      android.widget.ListView ml = new android.widget.ListView(this);
      ml.setAdapter(new android.widget.ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[] {"a", "b", "c", "d"}));
      ml.setChoiceMode(android.widget.AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
      ml.setMultiChoiceModeListener(new ChoiceLog(am));
      android.view.ViewGroup content = (android.view.ViewGroup) findViewById(android.R.id.content);
      content.addView(ml, new android.widget.FrameLayout.LayoutParams(10, 10));
      ml.setItemChecked(2, true);
      int checkedNow = ml.getCheckedItemCount();
      ml.setItemChecked(2, false);
      content.removeView(ml);
      String got = modeGot + " " + am + " " + checkedNow + " " + modesStarted + modesFinished;
      check("action modes: contextual bar with items, finish, modal multiple-choice list", got.equals("2 selected 20 cpi1dCPk2+k2-D 1 22"), got);
    } catch (Throwable t) { check("action modes: contextual bar with items, finish, modal multiple-choice list", false, String.valueOf(t)); Log.e("JNITEST", "actionmode", t); }
    try {
      // windows, displays, input methods and text layout (API 30-35 additions)
      StringBuilder w = new StringBuilder();
      android.view.WindowManager.LayoutParams lp = new android.view.WindowManager.LayoutParams(android.view.WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
          android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, android.graphics.PixelFormat.TRANSLUCENT);
      lp.setTitle("t"); lp.x = 5;
      android.os.Parcel par = android.os.Parcel.obtain();
      lp.writeToParcel(par, 0); par.setDataPosition(0);
      android.view.WindowManager.LayoutParams lp2 = android.view.WindowManager.LayoutParams.CREATOR.createFromParcel(par);
      w.append(lp2.x).append(lp2.getTitle()).append(lp2.type == lp.type).append(new android.view.WindowManager.LayoutParams().copyFrom(lp)).append(' ');
      android.view.Display d = getWindowManager().getDefaultDisplay();
      w.append(d.getMode().getPhysicalWidth() > 0).append(d.getSupportedModes().length).append(d.getHdrCapabilities() != null)
          .append(getWindowManager().getCurrentWindowMetrics().getBounds().width() == d.getMode().getPhysicalWidth() || getWindowManager().getCurrentWindowMetrics().getBounds().width() > 0).append(' ');
      android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
      w.append(imm.getInputMethodList().size()).append(imm.getEnabledInputMethodList().get(0).getId()).append(' ');
      android.view.inputmethod.EditorInfo ei = new android.view.inputmethod.EditorInfo();
      ei.initialSelStart = 5; ei.initialSelEnd = 5;
      ei.setInitialSurroundingText("hello world");
      w.append(ei.getInitialTextBeforeCursor(3, 0)).append('|').append(ei.getInitialTextAfterCursor(3, 0)).append('|').append(ei.getInitialSelectedText(0)).append(' ');
      android.widget.EditText et = new android.widget.EditText(this);
      et.setText("abc def"); et.setSelection(3);
      android.view.inputmethod.InputConnectionWrapper icw = new android.view.inputmethod.InputConnectionWrapper(new android.view.inputmethod.BaseInputConnection(et, true), false);
      android.view.inputmethod.SurroundingText st = icw.getSurroundingText(2, 2, 0);
      w.append(st.getText()).append(st.getSelectionStart()).append(st.getOffset());
      icw.replaceText(0, 3, "xyz", 1, null);
      w.append(et.getText());
      icw.deleteSurroundingTextInCodePoints(1, 1);
      w.append('|').append(et.getText()).append(icw.takeSnapshot().getSelectionStart());
      try { icw.setTarget(null); w.append("set"); } catch (SecurityException e) { w.append("immutable"); }
      w.append(' ');
      android.view.inputmethod.CursorAnchorInfo cai = new android.view.inputmethod.CursorAnchorInfo.Builder().setSelectionRange(1, 2).setComposingText(0, "ab")
          .addCharacterBounds(0, 1, 2, 3, 4, android.view.inputmethod.CursorAnchorInfo.FLAG_HAS_VISIBLE_REGION).build();
      android.os.Parcel cp = android.os.Parcel.obtain();
      cai.writeToParcel(cp, 0); cp.setDataPosition(0);
      android.view.inputmethod.CursorAnchorInfo cai2 = android.view.inputmethod.CursorAnchorInfo.CREATOR.createFromParcel(cp);
      w.append(cai2.equals(cai)).append(cai2.getCharacterBounds(0).right).append(' ');
      android.text.TextPaint tp = new android.text.TextPaint();
      tp.setTextSize(20);
      android.text.Layout lay = new android.text.Layout.Builder("hello world", 0, 11, tp, 1000).setMaxLines(3).setBreakStrategy(1).build();
      w.append(lay.getMaxLines()).append(lay.getBreakStrategy()).append(lay.getLineBreakConfig().getLineBreakStyle()).append(lay.isFontPaddingIncluded());
      android.text.SegmentFinder words = new android.text.WordSegmentFinder("hello world", android.icu.util.ULocale.US);
      int[] all = lay.getRangeForRect(new android.graphics.RectF(0, 0, 1000, lay.getHeight()), words, android.text.Layout.INCLUSION_STRATEGY_ANY_OVERLAP);
      float hw = tp.measureText("hello");
      int[] left = lay.getRangeForRect(new android.graphics.RectF(0, 0, hw + 2, lay.getHeight()), words, android.text.Layout.INCLUSION_STRATEGY_CONTAINS_CENTER);
      w.append(' ').append(all[0]).append(all[1]).append(left[0]).append(left[1]).append(lay.getLineLetterSpacingUnitCount(0, false)).append(' ');
      w.append(new android.text.GraphemeClusterSegmentFinder("e\u0301x", tp).nextEndBoundary(0));
      android.text.SegmentFinder ws = new android.text.WordSegmentFinder("it's ok", android.icu.util.ULocale.US);
      w.append(ws.nextEndBoundary(0)).append(ws.nextStartBoundary(0)).append(' ');
      android.graphics.text.MeasuredText mt = new android.graphics.text.MeasuredText.Builder("aaa bbb ccc".toCharArray()).appendStyleRun(tp, 11, false).build();
      android.graphics.text.LineBreaker.ParagraphConstraints pc = new android.graphics.text.LineBreaker.ParagraphConstraints();
      pc.setWidth(mt.getWidth(0, 7) + 1);
      android.graphics.text.LineBreaker.Result lr = new android.graphics.text.LineBreaker.Builder().build().computeLineBreaks(mt, pc, 0);
      w.append(lr.getLineCount()).append(lr.getLineBreakOffset(0)).append(lr.getLineBreakOffset(1)).append(lr.getLineWidth(0) == mt.getWidth(0, 7)).append(' ');
      TextView ptv = new TextView(this);
      android.text.PrecomputedText pt = android.text.PrecomputedText.create("ab\ncd", ptv.getTextMetricsParams());
      w.append(pt.getParagraphCount()).append(pt.getParagraphStart(1)).append(pt.getWidth(0, 2) > 0);
      try { pt.setSpan(new android.text.style.RelativeSizeSpan(2f), 0, 1, 0); w.append("set"); } catch (IllegalArgumentException e) { w.append("refused"); }
      ptv.setText(pt);
      w.append(ptv.getText().toString().length()).append(' ');
      android.graphics.Paint hp = new android.graphics.Paint();
      android.text.Highlights hl = new android.text.Highlights.Builder().addRange(hp, 0, 2).addRange(hp, 3, 4).build();
      w.append(hl.getSize()).append(hl.getRanges(0).length);
      android.text.SpannableString dest = new android.text.SpannableString("");
      w.append(new android.text.InputFilter.AllCaps(java.util.Locale.US).filter("ab", 0, 2, dest, 0, 0)).append(new android.text.InputFilter.AllCaps().filter("AB", 0, 2, dest, 0, 0))
          .append(new android.text.InputFilter.LengthFilter(5).getMax());
      CharSequence noText = null;
      StringBuffer noBuffer = null;
      w.append(' ').append(new StringBuilder().append(noText).insert(0, noText).append(noBuffer).append(noText, 1, 3)).append(new StringBuffer().append(noText).length());
      String got = w.toString();
      check("windows, displays, input methods, text layout: LayoutParams parcel/copyFrom, display modes, IME list, EditorInfo, InputConnection, CursorAnchorInfo, Layout.Builder, segments, LineBreaker, PrecomputedText",
          got.equals("5ttrue79 true1truetrue 1a2w.page/a2w.TextInput llo| wo| bc d21xyz def|xydef2immutable true3.0 31-1true 0110511 245 2811true 23truerefused5 14ABnull5 nullnullnullul4"), got);
    } catch (Throwable t) { check("windows, displays, input methods, text layout: LayoutParams parcel/copyFrom, display modes, IME list, EditorInfo, InputConnection, CursorAnchorInfo, Layout.Builder, segments, LineBreaker, PrecomputedText", false, String.valueOf(t)); Log.e("JNITEST", "wm", t); }
    try {
      // widgets: Toolbar (insets, title, action items, overflow), Activity.setActionBar, GridLayout (weights, fill, auto
      // placement), TextClock, AnalogClock, framework Intent constants
      StringBuilder t = new StringBuilder();
      float dens = getResources().getDisplayMetrics().density;
      android.widget.Toolbar tb = new android.widget.Toolbar(this);
      tb.setTitle("Title"); tb.setSubtitle("Sub");
      tb.setNavigationIcon(new android.graphics.drawable.ColorDrawable(0xff00ff00));
      tb.getMenu().add(0, 7, 0, "Act").setShowAsAction(android.view.MenuItem.SHOW_AS_ACTION_ALWAYS);
      tb.getMenu().add(0, 8, 0, "More");
      int[] tbClicked = new int[1];
      tb.setOnMenuItemClickListener(new ToolbarClick(tbClicked));
      int tw = Math.round(400 * dens);
      tb.measure(View.MeasureSpec.makeMeasureSpec(tw, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
      tb.layout(0, 0, tw, tb.getMeasuredHeight());
      TextView title = null; android.widget.ActionMenuView amv = null; View nav = null;
      for (int i = 0; i < tb.getChildCount(); i++) {
        View c = tb.getChildAt(i);
        if (c instanceof TextView && "Title".contentEquals(((TextView) c).getText())) title = (TextView) c;
        if (c instanceof android.widget.ActionMenuView) amv = (android.widget.ActionMenuView) c;
        if (c instanceof android.widget.ImageButton) nav = c;
      }
      t.append(Math.round(nav.getLeft() / dens)).append(',').append(Math.round(title.getLeft() / dens)).append(',').append(amv.getRight() == tw).append(amv.getChildCount());
      amv.getChildAt(0).performClick();
      t.append(tbClicked[0]).append(tb.getCurrentContentInsetStart() == Math.round(72 * dens)).append(' ');
      android.widget.Toolbar tb2 = new android.widget.Toolbar(this);
      try {
        setActionBar(tb2);
        getActionBar().setTitle("Bar");
        t.append(tb2.getTitle()).append(getActionBar().getDisplayOptions() & android.app.ActionBar.DISPLAY_SHOW_TITLE);
        setActionBar(null);
      } catch (IllegalStateException e) { t.append("decor"); }
      t.append(' ');
      android.widget.GridLayout gl = new android.widget.GridLayout(this);
      gl.setColumnCount(2);
      View[] cells = new View[4];
      for (int i = 0; i < 4; i++) {
        cells[i] = new View(this);
        android.widget.GridLayout.LayoutParams glp = i == 1 ? new android.widget.GridLayout.LayoutParams(android.widget.GridLayout.spec(android.widget.GridLayout.UNDEFINED),
            android.widget.GridLayout.spec(android.widget.GridLayout.UNDEFINED, 1f)) : new android.widget.GridLayout.LayoutParams();
        glp.width = 50; glp.height = 20;
        if (i == 3) glp.setGravity(android.view.Gravity.FILL_HORIZONTAL);
        gl.addView(cells[i], glp);
      }
      gl.measure(View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
      gl.layout(0, 0, 300, gl.getMeasuredHeight());
      t.append(gl.getMeasuredHeight()).append(',').append(cells[1].getLeft()).append(',').append(cells[1].getWidth()).append(',').append(cells[2].getTop())
          .append(',').append(cells[3].getLeft()).append(',').append(cells[3].getWidth()).append(',').append(gl.getRowCount()).append(' ');
      android.widget.TextClock tc = new android.widget.TextClock(this);
      tc.setFormat24Hour("HH:mm:ss"); tc.setFormat12Hour("hh:mm:ss a");
      t.append(tc.getText().toString().matches("\\d\\d:\\d\\d:\\d\\d.*"));
      android.widget.AnalogClock ac = new android.widget.AnalogClock(this);
      ac.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
      t.append(Math.round(ac.getMeasuredWidth() / dens)).append(' ');
      t.append(Intent.makeMainActivity(new android.content.ComponentName("a", "b")).getAction());
      String got = t.toString();
      check("widgets: Toolbar, Activity.setActionBar, GridLayout, TextClock, AnalogClock, framework Intent constants",
          got.equals("0,72,true27true decor 40,50,50,20,50,250,2 true160 android.intent.action.MAIN"), got);
    } catch (Throwable t) { check("widgets: Toolbar, Activity.setActionBar, GridLayout, TextClock, AnalogClock, framework Intent constants", false, String.valueOf(t)); Log.e("JNITEST", "toolbar", t); }
    try {
      // SearchView (iconified, query listener), Configuration (undefined defaults, diff, setTo), view state saving
      // (EditText text, CheckBox checked), EditText.setError
      StringBuilder s2 = new StringBuilder();
      android.widget.SearchView sv = new android.widget.SearchView(this);
      QueryLog ql = new QueryLog();
      sv.setOnQueryTextListener(ql);
      s2.append(sv.isIconified());
      sv.setIconified(false);
      s2.append(sv.isIconified());
      sv.setQuery("abc", false);
      sv.setQuery("abc", true);
      s2.append(ql.log).append(' ');
      sv.setQueryHint("Find");
      s2.append(sv.getQueryHint()).append(' ');
      android.content.res.Configuration c0 = new android.content.res.Configuration();
      android.content.res.Configuration dev = getResources().getConfiguration();
      android.content.res.Configuration c1 = new android.content.res.Configuration(dev);
      c1.orientation = dev.orientation == 1 ? 2 : 1;
      c1.fontScale = 1.5f;
      s2.append(c0.orientation).append(c0.screenWidthDp).append(' ').append(Integer.toHexString(dev.diff(c1))).append(' ').append(dev.diff(c0)).append(' ');
      c1.setTo(dev);
      s2.append(c1.equals(dev)).append(dev.getLocales().size() > 0).append(' ');
      android.widget.LinearLayout holder = new android.widget.LinearLayout(this);
      android.widget.EditText e1 = new android.widget.EditText(this);
      e1.setId(0x7f0a1001); e1.setText("kept"); holder.addView(e1);
      android.widget.CheckBox cb1 = new android.widget.CheckBox(this);
      cb1.setId(0x7f0a1002); cb1.setChecked(true); holder.addView(cb1);
      android.util.SparseArray<android.os.Parcelable> st = new android.util.SparseArray<>();
      holder.saveHierarchyState(st);
      e1.setText("lost"); cb1.setChecked(false);
      holder.restoreHierarchyState(st);
      s2.append(e1.getText()).append(cb1.isChecked()).append(st.get(0x7f0a1001) instanceof TextView.SavedState).append(' ');
      e1.setError("bad");
      s2.append(e1.getError()).append(e1.getCompoundDrawables()[2] != null);
      e1.setError(null);
      s2.append(e1.getError() == null).append(e1.getCompoundDrawables()[2] == null);
      String got = s2.toString();
      check("SearchView, Configuration, view state saving, EditText.setError", got.equals("truefalsec:abc s:abc Find 00 40000080 0 truetrue kepttruetrue badtruetruetrue"), got);
    } catch (Throwable t) { check("SearchView, Configuration, view state saving, EditText.setError", false, String.valueOf(t)); Log.e("JNITEST", "search", t); }
    try {
      // JobScheduler (schedule, pending jobs, a JobService run and released), system services, JobInfo checks
      android.app.job.JobScheduler js = (android.app.job.JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
      android.os.PersistableBundle pb = new android.os.PersistableBundle();
      pb.putString("k", "v");
      android.app.job.JobInfo ji = new android.app.job.JobInfo.Builder(42, new android.content.ComponentName(this, TestJobService.class)).setExtras(pb).setMinimumLatency(10).build();
      int jr = js.schedule(ji);
      final String pending = jr + " " + js.getAllPendingJobs().size() + " " + (js.getPendingJob(42) != null);
      final String svc = (getSystemService("storage") != null) + "" + (getSystemService(android.os.UserManager.class) != null) + (getSystemService("fingerprint") == null)
          + (getSystemService(SEARCH_SERVICE) != null);
      boolean threw = false;
      try { new android.app.job.JobInfo.Builder(1, new android.content.ComponentName(this, TestJobService.class)).build(); } catch (IllegalArgumentException e) { threw = true; }
      final boolean noConstraintThrew = threw;
      new android.os.Handler().postDelayed(() -> {
        String got = pending + " " + svc + noConstraintThrew + " " + TestJobService.log + " " + js.getAllPendingJobs().size();
        check("JobScheduler and system services", got.equals("1 1 true truetruetruetruetrue start42v destroyed 0"), got);
      }, 800);
    } catch (Throwable t) { check("JobScheduler and system services", false, String.valueOf(t)); Log.e("JNITEST", "jobs", t); }
    try {
      // layout animations: staggered child animations at the first draw, grid delays by column and row
      android.view.animation.AlphaAnimation fade = new android.view.animation.AlphaAnimation(0, 1);
      fade.setDuration(100);
      android.view.animation.LayoutAnimationController lac = new android.view.animation.LayoutAnimationController(fade, 0.5f);
      android.widget.LinearLayout ll = new android.widget.LinearLayout(this);
      for (int i = 0; i < 3; i++) ll.addView(new android.view.View(this), new android.widget.LinearLayout.LayoutParams(10, 10));
      ll.setLayoutAnimation(lac);
      ll.measure(android.view.View.MeasureSpec.makeMeasureSpec(100, android.view.View.MeasureSpec.EXACTLY), android.view.View.MeasureSpec.makeMeasureSpec(100, android.view.View.MeasureSpec.EXACTLY));
      ll.layout(0, 0, 100, 100);
      ll.draw(new android.graphics.Canvas(android.graphics.Bitmap.createBitmap(100, 100, android.graphics.Bitmap.Config.ARGB_8888)));
      StringBuilder offs = new StringBuilder();
      for (int i = 0; i < 3; i++) { android.view.animation.Animation a = ll.getChildAt(i).getAnimation(); offs.append(a == null ? "-" : String.valueOf(a.getStartOffset())).append(','); }
      lac.setOrder(android.view.animation.LayoutAnimationController.ORDER_REVERSE);
      android.view.animation.GridLayoutAnimationController glac = new android.view.animation.GridLayoutAnimationController(fade, 0.5f, 1f);
      android.view.View gv = new android.view.View(this);
      android.view.ViewGroup.LayoutParams glp = new android.view.ViewGroup.LayoutParams(10, 10);
      android.view.animation.GridLayoutAnimationController.AnimationParameters gp = new android.view.animation.GridLayoutAnimationController.AnimationParameters();
      gp.column = 2; gp.row = 1; gp.columnsCount = 3; gp.rowsCount = 2; gp.count = 6; gp.index = 5;
      glp.layoutAnimationParameters = gp; gv.setLayoutParams(glp);
      String got = offs + " " + lac.willOverlap() + glac.willOverlap() + " " + glac.getAnimationForView(gv).getStartOffset();
      check("layout animations: staggered offsets at first draw, grid column and row delays", got.equals("0,50,100, truetrue 200"), got);
    } catch (Throwable t) { check("layout animations: staggered offsets at first draw, grid column and row delays", false, String.valueOf(t)); Log.e("JNITEST", "layoutanim", t); }
    {
      // array casts through Object: primitive arrays of arrays, object arrays of arrays
      StringBuilder ac = new StringBuilder();
      Object[] objs = {new double[][] {{1.5}}, new int[][] {{2}}, new String[][] {{"s"}}, new byte[][][] {{{3}}}, new double[] {4}};
      try { ac.append(((double[][]) objs[0])[0][0]); } catch (Throwable t) { ac.append("cce-d2"); }
      try { ac.append(((int[][]) objs[1])[0][0]); } catch (Throwable t) { ac.append("cce-i2"); }
      try { ac.append(((String[][]) objs[2])[0][0]); } catch (Throwable t) { ac.append("cce-s2"); }
      try { ac.append(((byte[][][]) objs[3])[0][0][0]); } catch (Throwable t) { ac.append("cce-b3"); }
      try { ac.append(((Object[]) objs[1]).length); } catch (Throwable t) { ac.append("cce-o"); }
      try { ac.append(((double[]) objs[4])[0]); } catch (Throwable t) { ac.append("cce-d1"); }
      java.util.HashMap<String, double[][]> hm = new java.util.HashMap<>();
      hm.put("k", new double[][] {{5}});
      try { ac.append(hm.get("k")[0][0]); } catch (Throwable t) { ac.append("cce-map"); }
      ac.append(objs[0] instanceof double[][]).append(objs[0] instanceof Object[]).append(objs[1] instanceof double[][]);
      // the pattern that once failed in Path.op: double[][] values built from array initializers, removed and iterated
      java.util.HashMap<Long, double[][]> chains = new java.util.HashMap<>();
      try {
        for (int k = 0; k < 3; k++) {
          java.util.HashMap<Long, double[][]> nx = new java.util.HashMap<>();
          long key = 1L << 32 | 2;
          double[][] ch = k > 0 ? chains.remove(key) : null;
          if (ch == null) ch = new double[][] {{1, 2}, {3, 4}};
          ch = new double[][] {java.util.Arrays.copyOf(ch[0], ch[0].length + 2), java.util.Arrays.copyOf(ch[1], ch[1].length + 2)};
          nx.put(key, ch);
          for (double[][] c : chains.values()) ac.append(c.length);
          chains = nx;
        }
        for (double[][] c : chains.values()) ac.append(c[0].length);
      } catch (Throwable t) { ac.append("cce-chain"); Log.e("JNITEST", "chain", t); }
      check("array casts: double[][], int[][], String[][], byte[][][] through Object", ac.toString().equals("1.52s314.05.0truetruefalse8"), ac.toString());
    }
    try {
      // ordered broadcast: priorities, result passing, abort; sticky delivery on register; data matching
      final StringBuilder order = new StringBuilder();
      android.content.IntentFilter lo = new android.content.IntentFilter("com.a2w.T"); lo.setPriority(1);
      android.content.IntentFilter hi = new android.content.IntentFilter("com.a2w.T"); hi.setPriority(10);
      android.content.IntentFilter never = new android.content.IntentFilter("com.a2w.T"); never.setPriority(-5);
      registerReceiver(new FnReceiver(r -> { order.append("lo" + r.getResultCode()); r.setResultCode(r.getResultCode() + 1); r.abortBroadcast(); }), lo);
      registerReceiver(new FnReceiver(r -> { order.append("hi" + r.getResultCode() + r.isOrderedBroadcast()); r.setResultCode(r.getResultCode() * 10); }), hi);
      registerReceiver(new FnReceiver(r -> order.append("never")), never);
      android.content.IntentFilter data = new android.content.IntentFilter("com.a2w.D"); data.addDataScheme("geo");
      boolean dataMatch = data.match(getContentResolver(), new android.content.Intent("com.a2w.D", android.net.Uri.parse("geo:1,2")), false, "t") >= 0
          && data.match(getContentResolver(), new android.content.Intent("com.a2w.D", android.net.Uri.parse("http://x")), false, "t") < 0;
      sendStickyBroadcast(new android.content.Intent("com.a2w.STICKY").putExtra("v", 5));
      sendOrderedBroadcast(new android.content.Intent("com.a2w.T"), null, new FnReceiver(r -> {
          android.content.Intent st = registerReceiver(null, new android.content.IntentFilter("com.a2w.STICKY"));
          String got = order + " " + r.getResultCode() + " " + (st != null ? st.getIntExtra("v", 0) : -1) + " " + dataMatch;
          check("ordered and sticky broadcasts, IntentFilter.match", got.equals("hi1truelo10 11 5 true"), got);
      }), null, 1, null, null);
      // text: selection, auto-size, the context menu's copy and paste, paint spacing and runs, stroke outlines
      android.widget.EditText et = new android.widget.EditText(this);
      et.setText("hello world"); et.setSelection(6, 11);
      et.onTextContextMenuItem(android.R.id.copy); et.setSelection(0, 5); et.onTextContextMenuItem(android.R.id.paste);
      android.widget.TextView tv = new android.widget.TextView(this);
      tv.setText("auto sized text that has to fit");
      tv.setAutoSizeTextTypeUniformWithConfiguration(8, 100, 2, android.util.TypedValue.COMPLEX_UNIT_PX);
      tv.measure(android.view.View.MeasureSpec.makeMeasureSpec(200, android.view.View.MeasureSpec.EXACTLY), android.view.View.MeasureSpec.makeMeasureSpec(60, android.view.View.MeasureSpec.EXACTLY));
      float autoSize = tv.getTextSize();
      android.graphics.Paint p = new android.graphics.Paint(); p.setTextSize(20);
      float plain = p.measureText("abcdef"); p.setLetterSpacing(0.2f); float spaced = p.measureText("abcdef");
      float run = p.getRunAdvance("abcdef", 0, 6, 0, 6, false, 3);
      int cur = p.getTextRunCursor("a\ud83d\ude00b", 0, 4, false, 1, android.graphics.Paint.CURSOR_AFTER);
      android.graphics.Path line = new android.graphics.Path(); line.moveTo(0, 0); line.lineTo(100, 0);
      p.setStyle(android.graphics.Paint.Style.STROKE); p.setStrokeWidth(10);
      android.graphics.Path outline = new android.graphics.Path(); p.getFillPath(line, outline);
      android.graphics.RectF ob = new android.graphics.RectF(); outline.computeBounds(ob, true);
      String got = et.getText() + " " + et.getSelectionStart() + "," + et.getSelectionEnd() + " " + (autoSize > 8 && autoSize < 60) + " " + (spaced > plain) + " " + (run > 0 && run < spaced)
          + " " + cur + " " + (int) ob.top + "," + (int) ob.bottom;
      check("TextView selection, clipboard, auto-size; Paint spacing, runs, fill path", got.equals("world world 5,5 true true true 3 -5,5"), got);
    } catch (Throwable t) { check("TextView selection, clipboard, auto-size; Paint spacing, runs, fill path", false, String.valueOf(t)); Log.e("JNITEST", "tv", t); }
    try {
      getWindow().setStatusBarColor(0xff336699);
      android.os.Bundle saved = getWindow().saveHierarchyState();
      setTitleColor(0xff00ff00);
      boolean threw = false;
      try { getWindow().requireViewById(0x7f7f7f7f); } catch (IllegalArgumentException e) { threw = true; }
      String got = Integer.toHexString(getWindow().getStatusBarColor()) + " " + (saved != null) + " " + Integer.toHexString(getTitleColor()) + " " + threw
          + " " + (getParentActivityIntent() == null) + " " + (startActionMode(null) == null);
      check("Window and Activity properties", got.equals("ff336699 true ff00ff00 true true true"), got);
    } catch (Throwable t) { check("Window and Activity properties", false, String.valueOf(t)); Log.e("JNITEST", "win", t); }
    if (android.os.Build.VERSION.SDK_INT >= 24) try {
      String got = android.icu.lang.UCharacter.getType('A') + " " + android.icu.lang.UCharacter.UnicodeBlock.of('a').getID() + " "
          + android.icu.lang.UCharacter.getDirection(0x0627) + " " + android.icu.lang.UCharacter.foldCase("Stra\u00dfe", true)
          + " " + android.icu.lang.UCharacter.isUWhiteSpace(0x3000);
      check("ICU UCharacter", got.equals("1 1 13 strasse true"), got);
    } catch (Throwable t) { check("ICU UCharacter", false, String.valueOf(t)); Log.e("JNITEST", "icu", t); }
  }

  static final class Fib extends java.util.concurrent.RecursiveTask<Integer> {
    final int n;
    Fib(int n) { this.n = n; }
    protected Integer compute() { if (n < 2) return n; Fib a = new Fib(n - 1); a.fork(); return new Fib(n - 2).compute() + a.join(); }
  }

  interface OnReceive { void run(android.content.BroadcastReceiver r); }
  static final class FnReceiver extends android.content.BroadcastReceiver {
    final OnReceive f;
    FnReceiver(OnReceive f) { this.f = f; }
    @Override public void onReceive(android.content.Context c, android.content.Intent i) { f.run(this); }
  }

  static final class LifecycleLog implements android.app.Application.ActivityLifecycleCallbacks {
    final StringBuilder out;
    LifecycleLog(StringBuilder out) { this.out = out; }
    void add(android.app.Activity a, String e) { if (out.length() < 400) out.append(out.length() > 0 ? " " : "").append(a.getClass().getSimpleName()).append(':').append(e); }
    public void onActivityCreated(android.app.Activity a, android.os.Bundle b) { add(a, "created"); }
    public void onActivityStarted(android.app.Activity a) { add(a, "started"); }
    public void onActivityResumed(android.app.Activity a) { add(a, "resumed"); }
    public void onActivityPaused(android.app.Activity a) { add(a, "paused"); }
    public void onActivityStopped(android.app.Activity a) { add(a, "stopped"); }
    public void onActivitySaveInstanceState(android.app.Activity a, android.os.Bundle b) {}
    public void onActivityDestroyed(android.app.Activity a) { add(a, "destroyed"); }
    public void onActivityPreCreated(android.app.Activity a, android.os.Bundle b) { add(a, "preCreated"); }
    public void onActivityPostCreated(android.app.Activity a, android.os.Bundle b) { add(a, "postCreated"); }
    public void onActivityPrePaused(android.app.Activity a) { add(a, "prePaused"); }
    public void onActivityPostPaused(android.app.Activity a) { add(a, "postPaused"); }
  }

  void parcelChecks() {
    try {
      android.os.Parcel p = android.os.Parcel.obtain();
      android.os.Bundle b = new android.os.Bundle();
      b.putString("s", "x");
      b.putIntArray("a", new int[]{1, 2});
      android.os.Bundle inner = new android.os.Bundle();
      inner.putLong("l", 7L);
      b.putBundle("in", inner);
      p.writeInt(42);
      p.writeString("h\u00e9llo");
      p.writeLong(-3L);
      p.writeBundle(b);
      p.writeDouble(2.5);
      java.util.ArrayList<String> list = new java.util.ArrayList<>(java.util.Arrays.asList("a", "b"));
      p.writeStringList(list);
      int end = p.dataPosition();
      p.setDataPosition(0);
      int i = p.readInt();
      String s = p.readString();
      long l = p.readLong();
      android.os.Bundle rb = p.readBundle();
      double d = p.readDouble();
      java.util.ArrayList<String> rl = p.createStringArrayList();
      byte[] bytes = p.marshall();
      android.os.Parcel q = android.os.Parcel.obtain();
      q.unmarshall(bytes, 0, bytes.length);
      q.setDataPosition(0);
      android.os.PersistableBundle pb = new android.os.PersistableBundle();
      pb.putInt("n", 5);
      pb.putStringArray("arr", new String[]{"p", "q"});
      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
      pb.writeToStream(out);
      android.os.PersistableBundle pb2 = android.os.PersistableBundle.readFromStream(new java.io.ByteArrayInputStream(out.toByteArray()));
      android.util.SparseArray<String> sa = new android.util.SparseArray<>();
      sa.put(30, "c"); sa.put(10, "a"); sa.put(20, "b"); sa.removeAt(1);
      String got = i + " " + s + " " + l + " " + rb.getString("s") + rb.getIntArray("a")[1] + rb.getBundle("in").getLong("l") + " " + d + " " + rl
          + " " + (end == bytes.length) + q.readInt() + " " + pb2.getInt("n") + pb2.getStringArray("arr")[1]
          + " " + sa.keyAt(1) + sa.valueAt(0) + sa.indexOfKey(30) + " " + Integer.toHexString(android.graphics.Color.HSVToColor(new float[]{120, 1, 1}));
      check("Parcel, Bundle, PersistableBundle, SparseArray, Color", "42 h\u00e9llo -3 x27 2.5 [a, b] true42 5q 30a1 ff00ff00".equals(got), got);
    } catch (Throwable t) { android.util.Log.e("JNITEST", "parcel", t); check("Parcel, Bundle, PersistableBundle, SparseArray, Color", false, String.valueOf(t)); }
  }

  void uiChecks() {
    String name = "UI: GestureDetector, ScaleGestureDetector, PopupWindow, ActionBar";
    try {
      Gestures g = new Gestures();
      android.view.GestureDetector gd = new android.view.GestureDetector(this, g);
      long t = android.os.SystemClock.uptimeMillis();
      gd.onTouchEvent(touch(t, t, android.view.MotionEvent.ACTION_DOWN, 10, 10));
      gd.onTouchEvent(touch(t, t + 50, android.view.MotionEvent.ACTION_UP, 10, 10));
      long t2 = t + 1000;
      gd.onTouchEvent(touch(t2, t2, android.view.MotionEvent.ACTION_DOWN, 100, 100));
      gd.onTouchEvent(touch(t2, t2 + 10, android.view.MotionEvent.ACTION_MOVE, 100, 200));
      gd.onTouchEvent(touch(t2, t2 + 20, android.view.MotionEvent.ACTION_MOVE, 100, 400));
      gd.onTouchEvent(touch(t2, t2 + 30, android.view.MotionEvent.ACTION_UP, 100, 500));
      Pinch p = new Pinch();
      android.view.ScaleGestureDetector sd = new android.view.ScaleGestureDetector(this, p);
      long t3 = t + 2000;
      sd.onTouchEvent(touch(t3, t3, android.view.MotionEvent.ACTION_DOWN, 100, 300));
      sd.onTouchEvent(touch2(t3, t3 + 10, android.view.MotionEvent.ACTION_POINTER_DOWN | (1 << android.view.MotionEvent.ACTION_POINTER_INDEX_SHIFT), 100, 300, 300, 300));
      for (int i = 1; i <= 5; i++) sd.onTouchEvent(touch2(t3, t3 + 10 + i * 10, android.view.MotionEvent.ACTION_MOVE, 100 - i * 20, 300, 300 + i * 20, 300));
      sd.onTouchEvent(touch2(t3, t3 + 100, android.view.MotionEvent.ACTION_POINTER_UP | (1 << android.view.MotionEvent.ACTION_POINTER_INDEX_SHIFT), 0, 300, 400, 300));
      TextView tv = new TextView(this);
      tv.setText("popup");
      android.widget.PopupWindow pw = new android.widget.PopupWindow(tv, 100, 50, true);
      Dismissed dl = new Dismissed();
      pw.setOnDismissListener(dl);
      pw.showAtLocation(getWindow().getDecorView(), android.view.Gravity.CENTER, 0, 0);
      boolean shown = pw.isShowing();
      pw.dismiss();
      android.app.ActionBar ab = getActionBar();
      String got = g.downs + " " + g.taps + " " + g.scrolls + " " + (g.flings > 0) + " " + (p.scale > 1.5f) + " " + shown + " " + pw.isShowing() + " " + dl.called
          + " " + (ab != null ? ab.getTitle() : "no action bar");
      check(name, got.equals("2 1 " + g.scrolls + " true true true false true apk2web JNI test") && g.scrolls >= 1, got + " (scale " + p.scale + ")");
    } catch (Throwable e) { check(name, false, String.valueOf(e)); Log.e("JNITEST", "ui", e); }
    final Frame f = new Frame();
    android.view.Choreographer.getInstance().postFrameCallback(f);
    new android.os.Handler().postDelayed(() -> check("Choreographer frame callback", f.nanos > 0, "frame time " + f.nanos), 300);
    new android.os.Handler().postDelayed(this::focusChecks, 2500);
  }
  static final class QueryLog implements android.widget.SearchView.OnQueryTextListener {
    final StringBuilder log = new StringBuilder();
    public boolean onQueryTextSubmit(String query) { log.append(log.length() > 0 ? " " : "").append("s:").append(query); return true; }
    public boolean onQueryTextChange(String newText) { log.append(log.length() > 0 ? " " : "").append("c:").append(newText); return true; }
  }
  static final class ToolbarClick implements android.widget.Toolbar.OnMenuItemClickListener {
    final int[] out;
    ToolbarClick(int[] out) { this.out = out; }
    public boolean onMenuItemClick(android.view.MenuItem item) { out[0] = item.getItemId(); return true; }
  }
  static final class ModeLog implements android.view.ActionMode.Callback {
    final StringBuilder am;
    ModeLog(StringBuilder am) { this.am = am; }
    public boolean onCreateActionMode(android.view.ActionMode m, android.view.Menu menu) {
      menu.add(0, 1, 0, "Copy").setShowAsAction(android.view.MenuItem.SHOW_AS_ACTION_ALWAYS); menu.add(0, 2, 0, "More"); am.append('c'); return true;
    }
    public boolean onPrepareActionMode(android.view.ActionMode m, android.view.Menu menu) { am.append('p'); return false; }
    public boolean onActionItemClicked(android.view.ActionMode m, android.view.MenuItem item) { am.append('i').append(item.getItemId()); m.finish(); return true; }
    public void onDestroyActionMode(android.view.ActionMode m) { am.append('d'); }
  }
  static final class ChoiceLog implements android.widget.AbsListView.MultiChoiceModeListener {
    final StringBuilder am;
    ChoiceLog(StringBuilder am) { this.am = am; }
    public void onItemCheckedStateChanged(android.view.ActionMode m, int position, long id, boolean checked) { am.append('k').append(position).append(checked ? '+' : '-'); }
    public boolean onCreateActionMode(android.view.ActionMode m, android.view.Menu menu) { am.append('C'); return true; }
    public boolean onPrepareActionMode(android.view.ActionMode m, android.view.Menu menu) { am.append('P'); return false; }
    public boolean onActionItemClicked(android.view.ActionMode m, android.view.MenuItem item) { return false; }
    public void onDestroyActionMode(android.view.ActionMode m) { am.append('D'); }
  }
  int modesStarted, modesFinished;
  @Override public void onActionModeStarted(android.view.ActionMode mode) { modesStarted++; }
  @Override public void onActionModeFinished(android.view.ActionMode mode) { modesFinished++; }
  static int[] sorted(int[] a) { int[] b = a.clone(); java.util.Arrays.sort(b); return b; }
  static int pixelAt(android.view.View v, int x, int y) {
    android.graphics.Bitmap bm = android.graphics.Bitmap.createBitmap(Math.max(1, v.getWidth()), Math.max(1, v.getHeight()), android.graphics.Bitmap.Config.ARGB_8888);
    v.draw(new android.graphics.Canvas(bm));
    return bm.getPixel(Math.min(x, bm.getWidth() - 1), Math.min(y, bm.getHeight() - 1));
  }
  /** keyboard focus: touch mode, arrows, Tab, list selection with a header, Enter, nextFocusForwardId, FocusFinder; keys go through Instrumentation */
  void focusChecks() {
    final String name = "keyboard focus: touch mode, arrows, Tab order, list selection and keys, headers, Enter clicks, FocusFinder";
    try {
      // a window of its own on top (an activity started by an earlier check may be in front of this one)
      final android.view.WindowManager wm = getWindowManager();
      final android.widget.LinearLayout root = new android.widget.LinearLayout(this);
      root.setBackgroundColor(0xff202020);
      root.setOrientation(android.widget.LinearLayout.VERTICAL);
      android.widget.LinearLayout row = new android.widget.LinearLayout(this);
      final android.widget.Button b1 = new android.widget.Button(this), b2 = new android.widget.Button(this), b3 = new android.widget.Button(this);
      b1.setText("one"); b2.setText("two"); b3.setText("three");
      b3.setId(0x7f0f0003);
      row.addView(b1, new android.widget.LinearLayout.LayoutParams(150, 60));
      row.addView(b2, new android.widget.LinearLayout.LayoutParams(150, 60));
      root.addView(row);
      final android.widget.ListView lv = new android.widget.ListView(this);
      android.widget.TextView head = new android.widget.TextView(this);
      head.setText("head");
      lv.addHeaderView(head);
      java.util.ArrayList<String> items = new java.util.ArrayList<>();
      for (int i = 0; i < 30; i++) items.add("item " + i);
      lv.setAdapter(new android.widget.ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
      root.addView(lv, new android.widget.LinearLayout.LayoutParams(300, 250));
      root.addView(b3, new android.widget.LinearLayout.LayoutParams(150, 60));
      final int[] clicks = {0, -1};
      b3.setOnClickListener(v -> clicks[0]++);
      lv.setOnItemClickListener((p, v, pos, id) -> clicks[1] = pos);
      final android.view.WindowManager.LayoutParams wlp = new android.view.WindowManager.LayoutParams(400, 500, android.view.WindowManager.LayoutParams.TYPE_APPLICATION, 0, android.graphics.PixelFormat.OPAQUE);
      wm.addView(root, wlp);
      new android.os.Handler().postDelayed(() -> {
        try {
          android.app.Instrumentation in = new android.app.Instrumentation();
          StringBuilder sb = new StringBuilder();
          // targetSdkVersion < 28: a new window gives initial focus even in touch mode (to the list, focusable in touch mode)
          boolean touch0 = b1.isInTouchMode(), list0 = lv.isFocused();
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_DPAD_DOWN);        // leaves touch mode; the focused list selects its first item
          sb.append(touch0).append(list0).append(b1.isInTouchMode()).append(lv.getSelectedItemPosition()).append(' ');
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_DPAD_UP);          // nothing above the first item: focus goes up
          sb.append(b1.isFocused()).append(' ');
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_DPAD_RIGHT);
          sb.append(b2.isFocused()).append(' ');
          android.view.View ffDown = android.view.FocusFinder.getInstance().findNextFocus(root, b2, android.view.View.FOCUS_DOWN);
          sb.append(ffDown == lv).append(android.view.FocusFinder.getInstance().findNextFocus(root, b2, android.view.View.FOCUS_LEFT) == b1).append(' ');
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_DPAD_DOWN);        // into the list: the item nearest the button (the header)
          sb.append(lv.isFocused()).append(lv.getSelectedItemPosition()).append(' ');
          for (int i = 0; i < 3; i++) in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_DPAD_DOWN);
          sb.append(lv.getSelectedItemPosition()).append(lv.getSelectedItem()).append(' ');
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_ENTER);
          sb.append(clicks[1]).append(' ');
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_MOVE_END);
          sb.append(lv.getSelectedItemPosition()).append(lv.getLastVisiblePosition() == 30).append(' ');
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_DPAD_DOWN);        // past the end: focus moves on
          sb.append(b3.isFocused()).append(' ');
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_ENTER);
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_SPACE);
          sb.append(clicks[0]).append(' ');
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_TAB);              // Tab wraps to the first focusable
          sb.append(b1.isFocused());
          long t = android.os.SystemClock.uptimeMillis();
          in.sendKeySync(new android.view.KeyEvent(t, t, android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_TAB, 0, android.view.KeyEvent.META_SHIFT_ON));
          in.sendKeySync(new android.view.KeyEvent(t, t, android.view.KeyEvent.ACTION_UP, android.view.KeyEvent.KEYCODE_TAB, 0, android.view.KeyEvent.META_SHIFT_ON));
          sb.append(b3.isFocused()).append(' ');
          b1.setNextFocusForwardId(0x7f0f0003);
          b1.requestFocus();
          in.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_TAB);              // nextFocusForwardId
          sb.append(b3.isFocused()).append(' ');
          int[] loc = new int[2];
          b2.getLocationOnScreen(loc);
          long t2 = android.os.SystemClock.uptimeMillis();
          in.sendPointerSync(android.view.MotionEvent.obtain(t2, t2, android.view.MotionEvent.ACTION_DOWN, 5, 5, 0));   // a touch: back to touch mode, buttons drop focus
          in.sendPointerSync(android.view.MotionEvent.obtain(t2, t2, android.view.MotionEvent.ACTION_CANCEL, 5, 5, 0));
          sb.append(b1.isInTouchMode()).append(b3.isFocused()).append(lv.getSelectedItemPosition()).append(' ');
          android.view.View auto = new android.view.View(MainActivity.this);
          boolean f0 = auto.isFocusable();
          auto.setClickable(true);
          sb.append(f0).append(auto.isFocusable()).append(auto.getFocusable()).append(' ');
          sb.append(lv.getHeaderViewsCount()).append(lv.getCount()).append(lv.getAdapter() instanceof android.widget.HeaderViewListAdapter).append(' ')
              .append(android.view.SoundEffectConstants.getContantForFocusDirection(android.view.View.FOCUS_DOWN));
          android.widget.NumberPicker np = new android.widget.NumberPicker(MainActivity.this);
          np.setMinValue(0); np.setMaxValue(5); np.setValue(2);
          np.dispatchKeyEvent(new android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_DPAD_DOWN));
          np.dispatchKeyEvent(new android.view.KeyEvent(android.view.KeyEvent.ACTION_UP, android.view.KeyEvent.KEYCODE_DPAD_DOWN));
          int npv = np.getValue();
          np.dispatchKeyEvent(new android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_DPAD_UP));
          sb.append(' ').append(npv).append(np.getValue()).append(np.isFocusable());
          wm.removeView(root);
          String got = sb.toString();
          check(name, got.equals("truetruefalse0 true true truetrue true0 3item 2 3 30true true 2 truetrue true truefalse-1 falsetrue16 131true 4 32true"), got);
        } catch (Throwable e) { check(name, false, String.valueOf(e)); Log.e("JNITEST", "focus", e); try { wm.removeView(root); } catch (Throwable ignored) {} }
      }, 400);
    } catch (Throwable e) { check(name, false, String.valueOf(e)); Log.e("JNITEST", "focus", e); }
  }
  void spanChecks() {
    try {
      android.text.Spanned sp = android.text.Html.fromHtml("<b>x</b> <a href=\"http://example.com/\">y</a>");
      android.text.style.CharacterStyle[] cs = sp.getSpans(0, sp.length(), android.text.style.CharacterStyle.class);
      android.text.style.LeadingMarginSpan[] lm = sp.getSpans(0, sp.length(), android.text.style.LeadingMarginSpan.class);
      android.text.style.URLSpan[] us = sp.getSpans(0, sp.length(), android.text.style.URLSpan.class);
      TextView tv = new TextView(this);
      tv.setText(sp);
      tv.measure(View.MeasureSpec.makeMeasureSpec(400, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
      android.text.SpannableStringBuilder b = new android.text.SpannableStringBuilder("a").append("b").append('c');
      b.setSpan(new android.text.style.ForegroundColorSpan(0xff00ff00), 0, 1, android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      String got = cs.length + " " + lm.length + " " + (us.length == 1 ? us[0].getURL() : "none") + " " + (tv.getMeasuredWidth() > 0) + " " + b;
      check("spans: typed span arrays, a TextView with spans, SpannableStringBuilder", got.equals("2 0 http://example.com/ true abc"), got);
    } catch (Throwable t) { check("spans: typed span arrays, a TextView with spans, SpannableStringBuilder", false, String.valueOf(t)); Log.e("JNITEST", "spans", t); }
  }
  void textFormatChecks() {
    try {
      String d = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date(0));
      String n = java.text.NumberFormat.getInstance(java.util.Locale.US).format(1234.5);
      String df = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, java.util.Locale.US).format(new java.util.Date(0));
      check("java.text: SimpleDateFormat, NumberFormat, DateFormat", d.equals("1970-01-01") && n.equals("1,234.5") && !df.isEmpty(), d + " " + n + " " + df + " (" + java.util.Locale.getDefault() + ")");
    } catch (Throwable t) { check("java.text: SimpleDateFormat, NumberFormat, DateFormat", false, String.valueOf(t)); Log.e("JNITEST", "text", t); }
  }
  /** started by its class initializer, like many SDKs do */
  static final class Worker {
    static final android.os.HandlerThread thread = new android.os.HandlerThread("worker");
    static { thread.start(); }
    static synchronized android.os.Looper looper() { return thread.getLooper(); }
  }
  static volatile int threadSteps;

  /** a Java thread that sleeps, and an executor task; checked a little later from the main thread */
  void threadChecks() {
    threadSteps = 0;
    Thread t = new Thread(() -> {
      for (int i = 0; i < 3; i++) {
        try { Thread.sleep(20); } catch (InterruptedException e) { return; }
        threadSteps++;
      }
    });
    t.start();
    final java.util.concurrent.atomic.AtomicInteger ran = new java.util.concurrent.atomic.AtomicInteger();
    java.util.concurrent.ExecutorService ex = java.util.concurrent.Executors.newSingleThreadExecutor();
    java.util.concurrent.Future<Integer> f = ex.submit(() -> { ran.incrementAndGet(); return 42; });
    // a HandlerThread started by a class initializer and waited for (getLooper) from inside an executor task
    final String[] looperResult = {"not run"};
    ex.execute(() -> {
      final ThreadLocal<String> tl = new ThreadLocal<>();
      tl.set("main");
      android.os.Looper mainLooper = android.os.Looper.myLooper();
      android.os.Handler h = new android.os.Handler(Worker.looper());
      final android.os.Looper[] seen = new android.os.Looper[1];
      h.post(() -> { seen[0] = android.os.Looper.myLooper(); });
      looperResult[0] = "posted; after wait: myLooper " + (android.os.Looper.myLooper() == mainLooper ? "same" : "CHANGED")
          + ", thread-local " + tl.get() + ", worker looper " + (Worker.looper() != null);
      new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
        looperResult[0] += ", ran on worker " + (seen[0] == Worker.looper());
      }, 100);
    });
    // the main thread suspends (an image decode) while another thread runs: it resumes as itself
    final String[] resumed = {"not run"};
    new android.os.Handler().post(() -> {
      Thread me = Thread.currentThread();
      final boolean[] stop = {false};
      Thread busy = new Thread(() -> { while (!stop[0]) try { Thread.sleep(1); } catch (InterruptedException e) { return; } });
      busy.start();
      try { Thread.sleep(5); } catch (InterruptedException e) { }
      byte[] png = android.util.Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAADElEQVR4nGP4z8AAAAMBAQDJ/pLvAAAAAElFTkSuQmCC", 0);
      android.graphics.Bitmap bm = android.graphics.BitmapFactory.decodeByteArray(png, 0, png.length);
      resumed[0] = "pixel " + (bm == null ? "none" : Integer.toHexString(bm.getPixel(0, 0))) + ", thread " + (Thread.currentThread() == me ? "same" : "CHANGED")
          + ", looper " + (android.os.Looper.myLooper() == android.os.Looper.getMainLooper() ? "main" : "OTHER");
      stop[0] = true;
    });
    java.util.concurrent.Executors.newSingleThreadExecutor().execute(new SdkTask());
    // a thread that spins on a flag (no sleep, no wait) leaves the page and the main thread time to set it
    final boolean[] flag = {false};
    final String[] spun = {"not run"};
    new Thread(() -> {
      long t0 = System.currentTimeMillis();
      while (!flag[0] && System.currentTimeMillis() - t0 < 3000) { }
      spun[0] = flag[0] ? "flag seen" : "timed out";
    }).start();
    new android.os.Handler().postDelayed(() -> flag[0] = true, 100);
    // a request that cannot be made fails with IOException, as on a device without network
    final String[] net = {"not run"};
    new Thread(() -> {
      try { new java.net.URL("http://unreachable.invalid/x").openStream(); net[0] = "no exception"; }
      catch (java.io.IOException e) { net[0] = "IOException"; }
      catch (Throwable e) { net[0] = e.toString(); Log.e("JNITEST", "net", e); }
    }).start();
    final String[] conc = {"not run"};
    final Thread concThread = new Thread(() -> conc[0] = Concurrency.run(Thread.currentThread()), "conc");
    concThread.start();
    // the Apache HTTP client Android bundles: the same failure as a device without network
    final String[] apache = {"not run"};
    new Thread(() -> {
      try {
        org.apache.http.client.methods.HttpPost post = new org.apache.http.client.methods.HttpPost("http://unreachable.invalid/form");
        java.util.List<org.apache.http.NameValuePair> form = new java.util.ArrayList<>();
        form.add(new org.apache.http.message.BasicNameValuePair("a", "1 2"));
        post.setEntity(new org.apache.http.client.entity.UrlEncodedFormEntity(form));
        String body = org.apache.http.util.EntityUtils.toString(post.getEntity());
        try { new org.apache.http.impl.client.DefaultHttpClient().execute(post); apache[0] = body + " no exception"; }
        catch (java.io.IOException e) { apache[0] = body + " IOException"; }
      } catch (Throwable e) { apache[0] = e.toString(); Log.e("JNITEST", "apache", e); }
    }).start();
    new android.os.Handler().postDelayed(() -> {
      check("Apache HttpClient: entities, and IOException without network", apache[0].equals("a=1+2 IOException"), apache[0]);
      try { concThread.join(5000); } catch (InterruptedException e) { }
      check("java.util.concurrent: pools, queues, locks, latches (on real threads)", conc[0].equals(Concurrency.EXPECTED), conc[0]);
      check("busy loop on a Java thread is preempted", spun[0].equals("flag seen"), spun[0]);
      check("URL to an unreachable host throws IOException", net[0].equals("IOException"), net[0]);
      check("SDK task: HandlerThread from a class initializer", SdkTask.finished && Sdk.handled.equals("what 7"), "finished " + SdkTask.finished + ", handled " + Sdk.handled);
      check("thread after a suspending call (image decode)", resumed[0].equals("pixel ffff0000, thread same, looper main"), resumed[0]);
      check("HandlerThread.getLooper from an executor task", looperResult[0].equals(
          "posted; after wait: myLooper same, thread-local main, worker looper true, ran on worker true"), looperResult[0]);
      check("Thread.start + Thread.sleep", threadSteps == 3, "steps " + threadSteps);
      int v = -1;
      try { v = f.get(); } catch (Exception e) { }
      check("ExecutorService.submit + Future.get", v == 42 && ran.get() == 1, "value " + v);
      Log.i("JNITEST", "THREADS DONE");
    }, 500);
  }
}

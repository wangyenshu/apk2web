package a2w.jdk;

import a2w.Reflection;

/**
 * Hand-written replacements for JDK members TeaVM's class library lacks. When the linker (tools/jarproc) finds a
 * call to a missing member of a JDK class, it looks here first for a static method named {@code <SimpleClass>_<name>}
 * taking the receiver first (for instance members), and only generates a no-op stub when there is none.
 */
public final class Compat {
  private Compat() {}
  public static int Timer_purge(java.util.Timer t) { return 0; }

  // ---- null CharSequence arguments of StringBuilder/StringBuffer (TeaVM throws; Java uses "null"), see Link.NULL_SAFE
  public static StringBuilder StringBuilder_appendCS(StringBuilder b, CharSequence s) { return s == null ? b.append("null") : b.append(s); }
  public static StringBuilder StringBuilder_appendCSRange(StringBuilder b, CharSequence s, int start, int end) { return b.append(s == null ? "null" : s, start, end); }
  public static StringBuilder StringBuilder_appendSB(StringBuilder b, StringBuffer s) { return s == null ? b.append("null") : b.append(s); }
  public static StringBuilder StringBuilder_insertCS(StringBuilder b, int offset, CharSequence s) { return b.insert(offset, s == null ? "null" : s); }
  public static StringBuilder StringBuilder_insertCSRange(StringBuilder b, int offset, CharSequence s, int start, int end) { return b.insert(offset, s == null ? "null" : s, start, end); }
  public static StringBuffer StringBuffer_appendCS(StringBuffer b, CharSequence s) { return s == null ? b.append("null") : b.append(s); }
  public static StringBuffer StringBuffer_appendCSRange(StringBuffer b, CharSequence s, int start, int end) { return b.append(s == null ? "null" : s, start, end); }
  public static StringBuffer StringBuffer_appendSB(StringBuffer b, StringBuffer s) { return s == null ? b.append("null") : b.append(s); }
  public static StringBuffer StringBuffer_insertCS(StringBuffer b, int offset, CharSequence s) { return b.insert(offset, s == null ? "null" : s); }
  public static StringBuffer StringBuffer_insertCSRange(StringBuffer b, int offset, CharSequence s, int start, int end) { return b.insert(offset, s == null ? "null" : s, start, end); }
  public static boolean ConcurrentHashMap_contains(java.util.concurrent.ConcurrentHashMap<?, ?> m, Object v) { return m.containsValue(v); }

  // ---- overloads and members TeaVM's class library lacks
  public static void Thread_sleep(long ms, int nanos) throws InterruptedException { Thread.sleep(ms + (nanos > 0 && ms == 0 ? 1 : 0)); }
  public static void Object_wait(Object o, long ms, int nanos) throws InterruptedException { o.wait(ms + (nanos > 0 && ms == 0 ? 1 : 0)); }
  public static String System_mapLibraryName(String n) { return "lib" + n + ".so"; }
  /** the heap limit an app sees (Dalvik's growth limit on a 1 GB device) */
  public static long Runtime_maxMemory(Runtime r) { return 256L << 20; }
  public static void Runtime_runFinalization(Runtime r) {}
  public static void Runtime_addShutdownHook(Runtime r, Thread t) {}
  public static boolean Runtime_removeShutdownHook(Runtime r, Thread t) { return true; }
  public static void PrintStream_print(java.io.PrintStream p, boolean b) { p.print(String.valueOf(b)); }
  public static void PrintStream_print(java.io.PrintStream p, float f) { p.print(String.valueOf(f)); }
  public static void PrintStream_println(java.io.PrintStream p, char[] c) { p.println(new String(c)); }
  public static void PrintStream_print(java.io.PrintStream p, char[] c) { p.print(new String(c)); }
  public static String Locale_getScript(java.util.Locale l) { return ""; }
  public static String Locale_getUnicodeLocaleType(java.util.Locale l, String key) { return null; }
  public static java.util.Locale Locale_forLanguageTag(String tag) {
    String[] p = tag.replace('_', '-').split("-");
    if (p.length == 0 || p[0].isEmpty() || p[0].equals("und")) return java.util.Locale.ROOT;
    String region = "";
    for (int i = 1; i < p.length; i++) if (p[i].length() == 2 || p[i].length() == 3 && Character.isDigit(p[i].charAt(0))) { region = p[i]; break; }
    return new java.util.Locale(p[0], region);
  }
  public static String Locale_toLanguageTag(java.util.Locale l) {
    String c = l.getCountry();
    return (l.getLanguage().isEmpty() ? "und" : l.getLanguage()) + (c.isEmpty() ? "" : "-" + c);
  }
  /** Unicode bidirectional class (a2w.UnicodeData) */
  public static byte Character_getDirectionality(int cp) { return Character.isValidCodePoint(cp) ? (byte) a2w.UnicodeData.lookup(a2w.UnicodeData.DIRECTIONALITY, cp) : Character.DIRECTIONALITY_UNDEFINED; }
  public static byte Character_getDirectionality(char c) { return Character_getDirectionality((int) c); }
  public static boolean Class_isAnonymousClass(Class<?> c) {
    String n = c.getName();
    int i = n.lastIndexOf('$');
    return i >= 0 && i + 1 < n.length() && Character.isDigit(n.charAt(i + 1));
  }
  /** from the binary name (TeaVM's needs enclosing-class metadata that dex2jar output does not always carry) */
  public static String Class_getCanonicalName(Class<?> c) {
    if (c.isArray()) { String n = Class_getCanonicalName(c.getComponentType()); return n == null ? null : n + "[]"; }
    if (c.isPrimitive()) return c.getName();
    if (Class_isAnonymousClass(c)) return null;   // anonymous and local classes (Outer$1, Outer$1Local) have none
    return c.getName().replace('$', '.');
  }
  public static java.lang.reflect.Type[] Class_getGenericInterfaces(Class<?> c) { return a2w.Generics.interfaces(c); }
  public static java.lang.reflect.Type Class_getGenericSuperclass(Class<?> c) { return a2w.Generics.superclass(c); }
  public static java.net.URL Class_getResource(Class<?> c, String name) { return null; }
  public static java.io.InputStream Class_getResourceAsStream(Class<?> c, String name) {
    // Java resources packed in the APK root (what class-loader resources are on Android)
    String n = name.startsWith("/") ? name.substring(1) : c.getName().replace('.', '/').replaceAll("/[^/]*$", "/") + name;
    byte[] b = a2w.Host.apkRead(n);
    return b == null ? null : new java.io.ByteArrayInputStream(b);
  }
  public static java.io.InputStream ClassLoader_getResourceAsStream(ClassLoader l, String name) {
    byte[] b = a2w.Host.apkRead(name.startsWith("/") ? name.substring(1) : name);
    return b == null ? null : new java.io.ByteArrayInputStream(b);
  }
  public static java.util.Enumeration<java.net.URL> ClassLoader_getResources(ClassLoader l, String name) { return java.util.Collections.emptyEnumeration(); }

  // ---- reflection: answered from the per-app tables the linker generates (a2w.gen.Reflect)
  public static a2w.jdk.reflect.Constructor Class_getConstructor(Class<?> c, Class<?>[] params) throws NoSuchMethodException {
    a2w.jdk.reflect.Constructor k = a2w.jdk.reflect.Constructor.find(c, params);
    if (k == null) throw new NoSuchMethodException(c.getName() + ".<init>");
    return k;
  }
  public static a2w.jdk.reflect.Constructor Class_getDeclaredConstructor(Class<?> c, Class<?>[] params) throws NoSuchMethodException {
    return Class_getConstructor(c, params);
  }
  public static a2w.jdk.reflect.Constructor[] Class_getConstructors(Class<?> c) { return a2w.jdk.reflect.Constructor.all(c); }
  public static a2w.jdk.reflect.Constructor[] Class_getDeclaredConstructors(Class<?> c) { return a2w.jdk.reflect.Constructor.all(c); }
  public static a2w.jdk.reflect.Method Class_getMethod(Class<?> c, String name, Class<?>[] params) throws NoSuchMethodException {
    a2w.jdk.reflect.Method m = a2w.jdk.reflect.Method.find(c, name, params, true);
    if (m == null) throw new NoSuchMethodException(c.getName() + "." + name);
    return m;
  }
  public static a2w.jdk.reflect.Method Class_getDeclaredMethod(Class<?> c, String name, Class<?>[] params) throws NoSuchMethodException {
    a2w.jdk.reflect.Method m = a2w.jdk.reflect.Method.find(c, name, params, false);
    if (m == null) throw new NoSuchMethodException(c.getName() + "." + name);
    return m;
  }
  public static a2w.jdk.reflect.Method[] Class_getMethods(Class<?> c) { return a2w.jdk.reflect.Method.all(c, true); }
  public static a2w.jdk.reflect.Method[] Class_getDeclaredMethods(Class<?> c) { return a2w.jdk.reflect.Method.all(c, false); }
  public static a2w.jdk.reflect.Field Class_getField(Class<?> c, String name) throws NoSuchFieldException { return a2w.jdk.reflect.Field.find(c, name); }
  public static a2w.jdk.reflect.Field Class_getDeclaredField(Class<?> c, String name) throws NoSuchFieldException { return a2w.jdk.reflect.Field.findDeclared(c, name); }
  public static a2w.jdk.reflect.Field[] Class_getFields(Class<?> c) { return a2w.jdk.reflect.Field.all(c, false); }
  public static a2w.jdk.reflect.Field[] Class_getDeclaredFields(Class<?> c) { return a2w.jdk.reflect.Field.all(c, true); }
  public static Object Class_newInstance(Class<?> c) throws InstantiationException, IllegalAccessException {
    Object o = Reflection.construct(c, 0, null);
    if (o == null) throw new InstantiationException(c.getName());
    return o;
  }
  public static ClassLoader Class_getClassLoader(Class<?> c) { return Loader.INSTANCE; }
  public static Class<?> ClassLoader_loadClass(ClassLoader l, String name) throws ClassNotFoundException { return Reflection.forName(name); }
  public static ClassLoader Thread_getContextClassLoader(Thread t) { return Loader.INSTANCE; }
  public static ClassLoader ClassLoader_getSystemClassLoader() { return Loader.INSTANCE; }
  public static ClassLoader ClassLoader_getParent(ClassLoader l) { return null; }

  /** what getClassLoader() returns: loadClass answers from the generated name table */
  public static final class Loader extends ClassLoader {
    static final Loader INSTANCE = new Loader();
    Loader() { super(null); }
    @Override public Class<?> loadClass(String name) throws ClassNotFoundException { return Reflection.forName(name); }
  }
  public static java.io.IOException DirectoryIteratorException_getCause(java.nio.file.DirectoryIteratorException e) { return (java.io.IOException) ((Throwable) e).getCause(); }

  // ---- java.time conversions of JDK classes (API 26+); TeaVM presents the ThreeTen backport as java.time
  public static java.time.Instant Date_toInstant(java.util.Date d) { return java.time.Instant.ofEpochMilli(d.getTime()); }
  public static java.util.Date Date_from(java.time.Instant i) {
    try { return new java.util.Date(i.toEpochMilli()); } catch (ArithmeticException e) { throw new IllegalArgumentException(e); }
  }
  public static java.time.Instant Calendar_toInstant(java.util.Calendar c) { return java.time.Instant.ofEpochMilli(c.getTimeInMillis()); }
  public static java.time.ZonedDateTime GregorianCalendar_toZonedDateTime(java.util.GregorianCalendar c) {
    return java.time.ZonedDateTime.ofInstant(java.time.Instant.ofEpochMilli(c.getTimeInMillis()), TimeZone_toZoneId(c.getTimeZone()));
  }
  public static java.util.GregorianCalendar GregorianCalendar_from(java.time.ZonedDateTime z) {
    java.util.GregorianCalendar c = new java.util.GregorianCalendar(TimeZone_getTimeZone(z.getZone()));
    c.setGregorianChange(new java.util.Date(Long.MIN_VALUE));   // proleptic, ISO weeks: like java.time
    c.setFirstDayOfWeek(java.util.Calendar.MONDAY);
    c.setMinimalDaysInFirstWeek(4);
    try {
      c.setTimeInMillis(Math.addExact(Math.multiplyExact(z.toEpochSecond(), 1000L), z.get(java.time.temporal.ChronoField.MILLI_OF_SECOND)));
    } catch (ArithmeticException e) { throw new IllegalArgumentException(e); }
    return c;
  }
  public static java.time.ZoneId TimeZone_toZoneId(java.util.TimeZone tz) {
    String id = tz.getID();
    return java.time.ZoneId.of(id, java.time.ZoneId.SHORT_IDS);
  }
  public static java.util.TimeZone TimeZone_getTimeZone(java.time.ZoneId z) {
    String id = z.getId();
    char c = id.charAt(0);
    if (c == '+' || c == '-') id = "GMT" + id;
    else if (c == 'Z' && id.length() == 1) id = "UTC";
    return java.util.TimeZone.getTimeZone(id);
  }
  private static final java.util.concurrent.TimeUnit[] TIME_UNITS = {java.util.concurrent.TimeUnit.NANOSECONDS,
      java.util.concurrent.TimeUnit.MICROSECONDS, java.util.concurrent.TimeUnit.MILLISECONDS, java.util.concurrent.TimeUnit.SECONDS,
      java.util.concurrent.TimeUnit.MINUTES, java.util.concurrent.TimeUnit.HOURS, java.util.concurrent.TimeUnit.DAYS};
  private static final java.time.temporal.ChronoUnit[] CHRONO_UNITS = {java.time.temporal.ChronoUnit.NANOS,
      java.time.temporal.ChronoUnit.MICROS, java.time.temporal.ChronoUnit.MILLIS, java.time.temporal.ChronoUnit.SECONDS,
      java.time.temporal.ChronoUnit.MINUTES, java.time.temporal.ChronoUnit.HOURS, java.time.temporal.ChronoUnit.DAYS};
  public static java.time.temporal.ChronoUnit TimeUnit_toChronoUnit(java.util.concurrent.TimeUnit u) { return CHRONO_UNITS[u.ordinal()]; }
  public static java.util.concurrent.TimeUnit TimeUnit_of(java.time.temporal.ChronoUnit c) {
    java.util.Objects.requireNonNull(c, "chronoUnit");
    for (int i = 0; i < CHRONO_UNITS.length; i++) if (CHRONO_UNITS[i] == c) return TIME_UNITS[i];
    throw new IllegalArgumentException("No TimeUnit equivalent for " + c);
  }
  /** Duration in this unit, truncated toward zero, saturated at Long.MIN_VALUE / MAX_VALUE */
  public static long TimeUnit_convert(java.util.concurrent.TimeUnit u, java.time.Duration d) {
    java.math.BigInteger n = java.math.BigInteger.valueOf(d.getSeconds()).multiply(java.math.BigInteger.valueOf(1_000_000_000L))
        .add(java.math.BigInteger.valueOf(d.getNano())).divide(java.math.BigInteger.valueOf(u.toNanos(1)));
    if (n.bitLength() > 63) return n.signum() > 0 ? Long.MAX_VALUE : Long.MIN_VALUE;
    return n.longValue();
  }
  public static java.time.LocalDateTime ZipEntry_getTimeLocal(java.util.zip.ZipEntry e) {
    long t = e.getTime();
    return t == -1 ? null : java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(t), java.time.ZoneId.systemDefault());
  }
  public static void ZipEntry_setTimeLocal(java.util.zip.ZipEntry e, java.time.LocalDateTime t) {
    e.setTime(t.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
  }

  // ---- Unicode properties TeaVM's Character lacks (a2w.UnicodeData, from the JDK's tables)
  private static boolean prop(String runs, int cp) { return Character.isValidCodePoint(cp) && a2w.UnicodeData.lookup(runs, cp) != 0; }
  public static boolean Character_isMirrored(int cp) { return prop(a2w.UnicodeData.MIRRORED, cp); }
  public static boolean Character_isMirrored(char c) { return prop(a2w.UnicodeData.MIRRORED, c); }
  public static boolean Character_isIdeographic(int cp) { return prop(a2w.UnicodeData.IDEOGRAPHIC, cp); }
  public static boolean Character_isEmoji(int cp) { return prop(a2w.UnicodeData.EMOJI, cp); }
  public static boolean Character_isEmojiPresentation(int cp) { return prop(a2w.UnicodeData.EMOJI_PRESENTATION, cp); }
  public static boolean Character_isEmojiModifier(int cp) { return prop(a2w.UnicodeData.EMOJI_MODIFIER, cp); }
  public static boolean Character_isEmojiModifierBase(int cp) { return prop(a2w.UnicodeData.EMOJI_MODIFIER_BASE, cp); }
  public static boolean Character_isEmojiComponent(int cp) { return prop(a2w.UnicodeData.EMOJI_COMPONENT, cp); }
  public static boolean Character_isExtendedPictographic(int cp) { return prop(a2w.UnicodeData.EXTENDED_PICTOGRAPHIC, cp); }
  public static String Character_toString(int cp) { return new String(Character.toChars(cp)); }

  // ---- java.lang number methods of newer JDKs
  public static int Integer_parseUnsignedInt(String s) { return Integer_parseUnsignedInt(s, 10); }
  public static int Integer_parseUnsignedInt(String s, int radix) {
    if (s == null) throw new NumberFormatException("Cannot parse null string: null");
    if (s.startsWith("-")) throw new NumberFormatException("Illegal leading minus sign on unsigned string " + s + ".");
    long v = Long.parseLong(s, radix);
    if ((v >>> 32) != 0) throw new NumberFormatException("String value " + s + " exceeds range of unsigned int.");
    return (int) v;
  }
  public static int Integer_parseUnsignedInt(CharSequence s, int begin, int end, int radix) {
    java.util.Objects.checkFromToIndex(begin, end, s.length());
    return Integer_parseUnsignedInt(s.subSequence(begin, end).toString(), radix);
  }
  public static long Long_parseUnsignedLong(String s) { return Long_parseUnsignedLong(s, 10); }
  public static long Long_parseUnsignedLong(String s, int radix) {
    if (s == null) throw new NumberFormatException("Cannot parse null string: null");
    if (s.startsWith("-")) throw new NumberFormatException("Illegal leading minus sign on unsigned string " + s + ".");
    java.math.BigInteger v = new java.math.BigInteger(s, radix);
    if (v.bitLength() > 64) throw new NumberFormatException("String value " + s + " exceeds range of unsigned long.");
    return v.longValue();
  }
  public static long Long_parseUnsignedLong(CharSequence s, int begin, int end, int radix) {
    java.util.Objects.checkFromToIndex(begin, end, s.length());
    return Long_parseUnsignedLong(s.subSequence(begin, end).toString(), radix);
  }
  public static int Long_hashCode(long v) { return (int) (v ^ (v >>> 32)); }
  public static int Integer_compress(int i, int mask) {
    int r = 0;
    for (int bit = 0, out = 0; bit < 32; bit++) if ((mask >>> bit & 1) != 0) { r |= (i >>> bit & 1) << out; out++; }
    return r;
  }
  public static int Integer_expand(int i, int mask) {
    int r = 0;
    for (int bit = 0, in = 0; bit < 32; bit++) if ((mask >>> bit & 1) != 0) { r |= (i >>> in & 1) << bit; in++; }
    return r;
  }
  public static long Long_compress(long i, long mask) {
    long r = 0;
    for (int bit = 0, out = 0; bit < 64; bit++) if ((mask >>> bit & 1) != 0) { r |= (i >>> bit & 1) << out; out++; }
    return r;
  }
  public static long Long_expand(long i, long mask) {
    long r = 0;
    for (int bit = 0, in = 0; bit < 64; bit++) if ((mask >>> bit & 1) != 0) { r |= (i >>> in & 1) << bit; in++; }
    return r;
  }
  public static long Math_multiplyFull(int x, int y) { return (long) x * (long) y; }
  public static long Math_multiplyHigh(long x, long y) {
    long x1 = x >> 32, x2 = x & 0xFFFFFFFFL, y1 = y >> 32, y2 = y & 0xFFFFFFFFL;
    long z2 = x2 * y2, t = x1 * y2 + (z2 >>> 32), z1 = t & 0xFFFFFFFFL, z0 = t >> 32;
    z1 += x2 * y1;
    return x1 * y1 + z0 + (z1 >> 32);
  }
  public static long Math_unsignedMultiplyHigh(long x, long y) { return Math_multiplyHigh(x, y) + (y & (x >> 63)) + (x & (y >> 63)); }
  /** a * b + c rounded once, as java.lang.Math.fma does it (exact product and sum in BigDecimal) */
  public static double Math_fma(double a, double b, double c) {
    if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c)) return Double.NaN;
    boolean inf = Double.isInfinite(a) || Double.isInfinite(b) || Double.isInfinite(c);
    if (inf || a == 0.0 || b == 0.0) return a * b + c;
    java.math.BigDecimal p = new java.math.BigDecimal(a).multiply(new java.math.BigDecimal(b));
    if (c == 0.0) {
      // the exact product, rounded once; a zero sum keeps the product's sign
      return p.doubleValue();
    }
    java.math.BigDecimal s = p.add(new java.math.BigDecimal(c));
    return s.signum() == 0 ? 0.0 : s.doubleValue();
  }
  public static float Math_fma(float a, float b, float c) {
    if (Float.isNaN(a) || Float.isNaN(b) || Float.isNaN(c)) return Float.NaN;
    if (Float.isInfinite(a) || Float.isInfinite(b) || Float.isInfinite(c) || a == 0f || b == 0f) return a * b + c;
    // the double product of two floats is exact; the sum then rounds once to float through BigDecimal
    java.math.BigDecimal s = new java.math.BigDecimal((double) a * (double) b).add(new java.math.BigDecimal(c));
    return s.signum() == 0 ? 0f : s.floatValue();
  }

  // ---- String methods of newer JDKs and old deprecated ones
  public static String String_indent(String s, int n) {
    if (s.isEmpty()) return "";
    StringBuilder b = new StringBuilder();
    for (Object o : String_lines(s).toArray()) {
      String line = (String) o;
      if (n > 0) for (int k = 0; k < n; k++) b.append(' ');
      else if (n < 0) {
        int ws = 0;
        while (ws < line.length() && (n == Integer.MIN_VALUE || ws < -n) && Character.isWhitespace(line.charAt(ws))) ws++;
        line = line.substring(ws);
      }
      b.append(line).append('\n');
    }
    return b.toString();
  }
  public static java.util.stream.Stream<String> String_lines(String s) {
    java.util.ArrayList<String> l = new java.util.ArrayList<>();
    int start = 0, n = s.length();
    for (int i = 0; i < n; i++) {
      char c = s.charAt(i);
      if (c == '\n' || c == '\r') {
        l.add(s.substring(start, i));
        if (c == '\r' && i + 1 < n && s.charAt(i + 1) == '\n') i++;
        start = i + 1;
      }
    }
    if (start < n) l.add(s.substring(start));
    return l.stream();
  }
  public static <R> R String_transform(String s, java.util.function.Function<? super String, ? extends R> f) { return f.apply(s); }
  /** the deprecated getBytes(int, int, byte[], int): the low byte of each char */
  @SuppressWarnings("deprecation")
  public static void String_getBytes(String s, int srcBegin, int srcEnd, byte[] dst, int dstBegin) {
    if (srcBegin < 0 || srcBegin > srcEnd || srcEnd > s.length()) throw new StringIndexOutOfBoundsException("srcBegin " + srcBegin + ", srcEnd " + srcEnd + ", length " + s.length());
    for (int i = srcBegin; i < srcEnd; i++) dst[dstBegin++] = (byte) s.charAt(i);
  }

  // ---- java.lang.Thread and Runtime members of a single-process page
  public static long Thread_threadId(Thread t) { return t.getId(); }
  public static boolean Thread_isVirtual(Thread t) { return false; }
  public static void Thread_onSpinWait() {}
  public static void Thread_checkAccess(Thread t) {}
  public static void Thread_dumpStack() { new Exception("Stack trace").printStackTrace(); }
  public static int Thread_countStackFrames(Thread t) { return t.getStackTrace().length; }
  /** Android: these were never supported */
  public static void Thread_stop(Thread t) { throw new UnsupportedOperationException(); }
  public static void Thread_stop(Thread t, Throwable obj) { throw new UnsupportedOperationException(); }
  public static void Thread_suspend(Thread t) { throw new UnsupportedOperationException(); }
  public static void Thread_resume(Thread t) { throw new UnsupportedOperationException(); }
  public static void Thread_destroy(Thread t) { throw new UnsupportedOperationException(); }
  public static java.util.Map<String, String> System_getenv() {
    java.util.HashMap<String, String> m = new java.util.HashMap<>();
    for (String kv : android.system.Os.environ()) { int i = kv.indexOf('='); if (i > 0) m.put(kv.substring(0, i), kv.substring(i + 1)); }
    return java.util.Collections.unmodifiableMap(m);
  }
  public static java.io.InputStream Runtime_getLocalizedInputStream(Runtime r, java.io.InputStream in) { return in; }
  public static java.io.OutputStream Runtime_getLocalizedOutputStream(Runtime r, java.io.OutputStream out) { return out; }
  public static void Runtime_traceInstructions(Runtime r, boolean on) {}
  public static void Runtime_traceMethodCalls(Runtime r, boolean on) {}
  /** the page runs no other programs */
  public static Process Runtime_exec(Runtime r, String command) throws java.io.IOException { throw cannotRun(command); }
  public static Process Runtime_exec(Runtime r, String command, String[] env) throws java.io.IOException { throw cannotRun(command); }
  public static Process Runtime_exec(Runtime r, String command, String[] env, java.io.File dir) throws java.io.IOException { throw cannotRun(command); }
  public static Process Runtime_exec(Runtime r, String[] cmd) throws java.io.IOException { throw cannotRun(cmd.length > 0 ? cmd[0] : ""); }
  public static Process Runtime_exec(Runtime r, String[] cmd, String[] env) throws java.io.IOException { throw cannotRun(cmd.length > 0 ? cmd[0] : ""); }
  public static Process Runtime_exec(Runtime r, String[] cmd, String[] env, java.io.File dir) throws java.io.IOException { throw cannotRun(cmd.length > 0 ? cmd[0] : ""); }
  private static java.io.IOException cannotRun(String command) {
    String prog = command.trim().split("\\s+")[0];
    return new java.io.IOException("Cannot run program \"" + prog + "\": error=13, Permission denied");
  }

  // ---- java.lang.Class members of newer JDKs
  public static Class<?> Class_componentType(Class<?> c) { return c.getComponentType(); }
  public static Class<?> Class_arrayType(Class<?> c) { return java.lang.reflect.Array.newInstance(c, 0).getClass(); }
  public static String Class_descriptorString(Class<?> c) {
    if (c.isPrimitive()) {
      switch (c.getName()) {
        case "int": return "I"; case "long": return "J"; case "boolean": return "Z"; case "byte": return "B"; case "char": return "C";
        case "short": return "S"; case "float": return "F"; case "double": return "D"; default: return "V";
      }
    }
    if (c.isArray()) return "[" + Class_descriptorString(c.getComponentType());
    return "L" + c.getName().replace('.', '/') + ";";
  }
  public static String Class_getPackageName(Class<?> c) {
    while (c.isArray()) c = c.getComponentType();
    if (c.isPrimitive()) return "java.lang";
    String n = c.getName();
    int i = n.lastIndexOf('.');
    return i < 0 ? "" : n.substring(0, i);
  }
  public static boolean Class_isRecord(Class<?> c) { return c.getSuperclass() != null && c.getSuperclass().getName().equals("java.lang.Record"); }
  public static String Class_toGenericString(Class<?> c) {
    if (c.isPrimitive()) return c.getName();
    String kind = c.isAnnotation() ? "@interface " : c.isInterface() ? "interface " : c.isEnum() ? "enum " : Class_isRecord(c) ? "record " : "class ";
    String mods = java.lang.reflect.Modifier.toString(c.getModifiers() & (c.isInterface() ? java.lang.reflect.Modifier.PUBLIC | java.lang.reflect.Modifier.PROTECTED | java.lang.reflect.Modifier.PRIVATE | java.lang.reflect.Modifier.STATIC : 0x0C1F));
    return (mods.isEmpty() ? "" : mods + " ") + kind + c.getName();
  }

  // ---- java.util.concurrent.atomic: the memory-order variants are the plain operations (framework code runs alone)
  public static boolean AtomicBoolean_getPlain(java.util.concurrent.atomic.AtomicBoolean a) { return a.get(); }
  public static boolean AtomicBoolean_getOpaque(java.util.concurrent.atomic.AtomicBoolean a) { return a.get(); }
  public static boolean AtomicBoolean_getAcquire(java.util.concurrent.atomic.AtomicBoolean a) { return a.get(); }
  public static void AtomicBoolean_setPlain(java.util.concurrent.atomic.AtomicBoolean a, boolean v) { a.set(v); }
  public static void AtomicBoolean_setOpaque(java.util.concurrent.atomic.AtomicBoolean a, boolean v) { a.set(v); }
  public static void AtomicBoolean_setRelease(java.util.concurrent.atomic.AtomicBoolean a, boolean v) { a.set(v); }
  public static boolean AtomicBoolean_compareAndExchange(java.util.concurrent.atomic.AtomicBoolean a, boolean e, boolean n) { boolean o = a.get(); if (o == e) a.set(n); return o; }
  public static boolean AtomicBoolean_compareAndExchangeAcquire(java.util.concurrent.atomic.AtomicBoolean a, boolean e, boolean n) { boolean o = a.get(); if (o == e) a.set(n); return o; }
  public static boolean AtomicBoolean_compareAndExchangeRelease(java.util.concurrent.atomic.AtomicBoolean a, boolean e, boolean n) { boolean o = a.get(); if (o == e) a.set(n); return o; }
  public static boolean AtomicBoolean_weakCompareAndSetPlain(java.util.concurrent.atomic.AtomicBoolean a, boolean e, boolean n) { return a.compareAndSet(e, n); }
  public static boolean AtomicBoolean_weakCompareAndSetVolatile(java.util.concurrent.atomic.AtomicBoolean a, boolean e, boolean n) { return a.compareAndSet(e, n); }
  public static boolean AtomicBoolean_weakCompareAndSetAcquire(java.util.concurrent.atomic.AtomicBoolean a, boolean e, boolean n) { return a.compareAndSet(e, n); }
  public static boolean AtomicBoolean_weakCompareAndSetRelease(java.util.concurrent.atomic.AtomicBoolean a, boolean e, boolean n) { return a.compareAndSet(e, n); }
  public static int AtomicInteger_getPlain(java.util.concurrent.atomic.AtomicInteger a) { return a.get(); }
  public static int AtomicInteger_getOpaque(java.util.concurrent.atomic.AtomicInteger a) { return a.get(); }
  public static int AtomicInteger_getAcquire(java.util.concurrent.atomic.AtomicInteger a) { return a.get(); }
  public static void AtomicInteger_setPlain(java.util.concurrent.atomic.AtomicInteger a, int v) { a.set(v); }
  public static void AtomicInteger_setOpaque(java.util.concurrent.atomic.AtomicInteger a, int v) { a.set(v); }
  public static void AtomicInteger_setRelease(java.util.concurrent.atomic.AtomicInteger a, int v) { a.set(v); }
  public static int AtomicInteger_compareAndExchange(java.util.concurrent.atomic.AtomicInteger a, int e, int n) { int o = a.get(); if (o == e) a.set(n); return o; }
  public static int AtomicInteger_compareAndExchangeAcquire(java.util.concurrent.atomic.AtomicInteger a, int e, int n) { int o = a.get(); if (o == e) a.set(n); return o; }
  public static int AtomicInteger_compareAndExchangeRelease(java.util.concurrent.atomic.AtomicInteger a, int e, int n) { int o = a.get(); if (o == e) a.set(n); return o; }
  public static boolean AtomicInteger_weakCompareAndSetPlain(java.util.concurrent.atomic.AtomicInteger a, int e, int n) { return a.compareAndSet(e, n); }
  public static boolean AtomicInteger_weakCompareAndSetVolatile(java.util.concurrent.atomic.AtomicInteger a, int e, int n) { return a.compareAndSet(e, n); }
  public static boolean AtomicInteger_weakCompareAndSetAcquire(java.util.concurrent.atomic.AtomicInteger a, int e, int n) { return a.compareAndSet(e, n); }
  public static boolean AtomicInteger_weakCompareAndSetRelease(java.util.concurrent.atomic.AtomicInteger a, int e, int n) { return a.compareAndSet(e, n); }
  public static long AtomicLong_getPlain(java.util.concurrent.atomic.AtomicLong a) { return a.get(); }
  public static long AtomicLong_getOpaque(java.util.concurrent.atomic.AtomicLong a) { return a.get(); }
  public static long AtomicLong_getAcquire(java.util.concurrent.atomic.AtomicLong a) { return a.get(); }
  public static void AtomicLong_setPlain(java.util.concurrent.atomic.AtomicLong a, long v) { a.set(v); }
  public static void AtomicLong_setOpaque(java.util.concurrent.atomic.AtomicLong a, long v) { a.set(v); }
  public static void AtomicLong_setRelease(java.util.concurrent.atomic.AtomicLong a, long v) { a.set(v); }
  public static long AtomicLong_compareAndExchange(java.util.concurrent.atomic.AtomicLong a, long e, long n) { long o = a.get(); if (o == e) a.set(n); return o; }
  public static long AtomicLong_compareAndExchangeAcquire(java.util.concurrent.atomic.AtomicLong a, long e, long n) { long o = a.get(); if (o == e) a.set(n); return o; }
  public static long AtomicLong_compareAndExchangeRelease(java.util.concurrent.atomic.AtomicLong a, long e, long n) { long o = a.get(); if (o == e) a.set(n); return o; }
  public static boolean AtomicLong_weakCompareAndSetPlain(java.util.concurrent.atomic.AtomicLong a, long e, long n) { return a.compareAndSet(e, n); }
  public static boolean AtomicLong_weakCompareAndSetVolatile(java.util.concurrent.atomic.AtomicLong a, long e, long n) { return a.compareAndSet(e, n); }
  public static boolean AtomicLong_weakCompareAndSetAcquire(java.util.concurrent.atomic.AtomicLong a, long e, long n) { return a.compareAndSet(e, n); }
  public static boolean AtomicLong_weakCompareAndSetRelease(java.util.concurrent.atomic.AtomicLong a, long e, long n) { return a.compareAndSet(e, n); }
  public static <V> V AtomicReference_compareAndExchangeAcquire(java.util.concurrent.atomic.AtomicReference<V> a, V e, V n) { V o = a.get(); if (o == e) a.set(n); return o; }
  public static <V> V AtomicReference_compareAndExchangeRelease(java.util.concurrent.atomic.AtomicReference<V> a, V e, V n) { V o = a.get(); if (o == e) a.set(n); return o; }
  public static <V> boolean AtomicReference_weakCompareAndSetPlain(java.util.concurrent.atomic.AtomicReference<V> a, V e, V n) { return a.compareAndSet(e, n); }
  public static <V> boolean AtomicReference_weakCompareAndSetVolatile(java.util.concurrent.atomic.AtomicReference<V> a, V e, V n) { return a.compareAndSet(e, n); }
  public static <V> boolean AtomicReference_weakCompareAndSetAcquire(java.util.concurrent.atomic.AtomicReference<V> a, V e, V n) { return a.compareAndSet(e, n); }
  public static <V> boolean AtomicReference_weakCompareAndSetRelease(java.util.concurrent.atomic.AtomicReference<V> a, V e, V n) { return a.compareAndSet(e, n); }

  // ---- java.util.Arrays
  static void arrayRange(int length, int from, int to) {
    if (from > to) throw new IllegalArgumentException("fromIndex(" + from + ") > toIndex(" + to + ")");
    if (from < 0) throw new ArrayIndexOutOfBoundsException(from);
    if (to > length) throw new ArrayIndexOutOfBoundsException(to);
  }
  static int cmp_boolean(boolean[] a, int af, int at, boolean[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { boolean x = a[af + i], y = b[bf + i]; int c = Boolean.compare(x, y); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compare(boolean[] a, boolean[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmp_boolean(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compare(boolean[] a, int af, int at, boolean[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmp_boolean(a, af, at, b, bf, bt); }
  static int cmp_byte(byte[] a, int af, int at, byte[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { byte x = a[af + i], y = b[bf + i]; int c = Byte.compare(x, y); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compare(byte[] a, byte[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmp_byte(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compare(byte[] a, int af, int at, byte[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmp_byte(a, af, at, b, bf, bt); }
  static int cmp_char(char[] a, int af, int at, char[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { char x = a[af + i], y = b[bf + i]; int c = Character.compare(x, y); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compare(char[] a, char[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmp_char(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compare(char[] a, int af, int at, char[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmp_char(a, af, at, b, bf, bt); }
  static int cmp_short(short[] a, int af, int at, short[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { short x = a[af + i], y = b[bf + i]; int c = Short.compare(x, y); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compare(short[] a, short[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmp_short(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compare(short[] a, int af, int at, short[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmp_short(a, af, at, b, bf, bt); }
  static int cmp_int(int[] a, int af, int at, int[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { int x = a[af + i], y = b[bf + i]; int c = Integer.compare(x, y); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compare(int[] a, int[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmp_int(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compare(int[] a, int af, int at, int[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmp_int(a, af, at, b, bf, bt); }
  static int cmp_long(long[] a, int af, int at, long[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { long x = a[af + i], y = b[bf + i]; int c = Long.compare(x, y); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compare(long[] a, long[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmp_long(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compare(long[] a, int af, int at, long[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmp_long(a, af, at, b, bf, bt); }
  static int cmp_float(float[] a, int af, int at, float[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { float x = a[af + i], y = b[bf + i]; int c = Float.compare(x, y); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compare(float[] a, float[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmp_float(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compare(float[] a, int af, int at, float[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmp_float(a, af, at, b, bf, bt); }
  static int cmp_double(double[] a, int af, int at, double[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { double x = a[af + i], y = b[bf + i]; int c = Double.compare(x, y); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compare(double[] a, double[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmp_double(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compare(double[] a, int af, int at, double[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmp_double(a, af, at, b, bf, bt); }
  static int cmpu_byte(byte[] a, int af, int at, byte[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { byte x = a[af + i], y = b[bf + i]; int c = Integer.compare(x & 0xff, y & 0xff); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compareUnsigned(byte[] a, byte[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmpu_byte(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compareUnsigned(byte[] a, int af, int at, byte[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmpu_byte(a, af, at, b, bf, bt); }
  static int cmpu_short(short[] a, int af, int at, short[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { short x = a[af + i], y = b[bf + i]; int c = Integer.compare(x & 0xffff, y & 0xffff); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compareUnsigned(short[] a, short[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmpu_short(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compareUnsigned(short[] a, int af, int at, short[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmpu_short(a, af, at, b, bf, bt); }
  static int cmpu_int(int[] a, int af, int at, int[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { int x = a[af + i], y = b[bf + i]; int c = Integer.compare(x ^ Integer.MIN_VALUE, y ^ Integer.MIN_VALUE); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compareUnsigned(int[] a, int[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmpu_int(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compareUnsigned(int[] a, int af, int at, int[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmpu_int(a, af, at, b, bf, bt); }
  static int cmpu_long(long[] a, int af, int at, long[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { long x = a[af + i], y = b[bf + i]; int c = Long.compare(x ^ Long.MIN_VALUE, y ^ Long.MIN_VALUE); if (c != 0) return c; }
    return (at - af) - (bt - bf);
  }
  public static int Arrays_compareUnsigned(long[] a, long[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmpu_long(a, 0, a.length, b, 0, b.length); }
  public static int Arrays_compareUnsigned(long[] a, int af, int at, long[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmpu_long(a, af, at, b, bf, bt); }
  static <T extends Comparable<? super T>> int cmpComparable(T[] a, int af, int at, T[] b, int bf, int bt) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) {
      T x = a[af + i], y = b[bf + i];
      if (x != y) { if (x == null || y == null) return x == null ? -1 : 1; int c = x.compareTo(y); if (c != 0) return c; }
    }
    return (at - af) - (bt - bf);
  }
  public static <T extends Comparable<? super T>> int Arrays_compare(T[] a, T[] b) { if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmpComparable(a, 0, a.length, b, 0, b.length); }
  public static <T extends Comparable<? super T>> int Arrays_compare(T[] a, int af, int at, T[] b, int bf, int bt) { arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmpComparable(a, af, at, b, bf, bt); }
  static <T> int cmpWith(T[] a, int af, int at, T[] b, int bf, int bt, java.util.Comparator<? super T> c) {
    int n = Math.min(at - af, bt - bf);
    for (int i = 0; i < n; i++) { T x = a[af + i], y = b[bf + i]; if (x != y) { int r = c.compare(x, y); if (r != 0) return r; } }
    return (at - af) - (bt - bf);
  }
  public static <T> int Arrays_compare(T[] a, T[] b, java.util.Comparator<? super T> c) { java.util.Objects.requireNonNull(c); if (a == b) return 0; if (a == null || b == null) return a == null ? -1 : 1; return cmpWith(a, 0, a.length, b, 0, b.length, c); }
  public static <T> int Arrays_compare(T[] a, int af, int at, T[] b, int bf, int bt, java.util.Comparator<? super T> c) { java.util.Objects.requireNonNull(c); arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return cmpWith(a, af, at, b, bf, bt, c); }
  static <T> int mismatchWith(T[] a, int af, int at, T[] b, int bf, int bt, java.util.Comparator<? super T> c) {
    int la = at - af, lb = bt - bf, n = Math.min(la, lb);
    for (int i = 0; i < n; i++) { T x = a[af + i], y = b[bf + i]; if (x != y && c.compare(x, y) != 0) return i; }
    return la == lb ? -1 : n;
  }
  public static <T> int Arrays_mismatch(T[] a, T[] b, java.util.Comparator<? super T> c) { java.util.Objects.requireNonNull(c); return mismatchWith(a, 0, a.length, b, 0, b.length, c); }
  public static <T> int Arrays_mismatch(T[] a, int af, int at, T[] b, int bf, int bt, java.util.Comparator<? super T> c) { java.util.Objects.requireNonNull(c); arrayRange(a.length, af, at); arrayRange(b.length, bf, bt); return mismatchWith(a, af, at, b, bf, bt, c); }
  public static <T> boolean Arrays_equals(T[] a, T[] b, java.util.Comparator<? super T> c) {
    java.util.Objects.requireNonNull(c);
    if (a == b) return true;
    if (a == null || b == null || a.length != b.length) return false;
    return mismatchWith(a, 0, a.length, b, 0, b.length, c) < 0;
  }
  public static <T> boolean Arrays_equals(T[] a, int af, int at, T[] b, int bf, int bt, java.util.Comparator<? super T> c) {
    java.util.Objects.requireNonNull(c); arrayRange(a.length, af, at); arrayRange(b.length, bf, bt);
    return at - af == bt - bf && mismatchWith(a, af, at, b, bf, bt, c) < 0;
  }
  public static <T> void Arrays_parallelPrefix(T[] a, java.util.function.BinaryOperator<T> op) { java.util.Objects.requireNonNull(op); for (int i = 1; i < a.length; i++) a[i] = op.apply(a[i - 1], a[i]); }
  public static <T> void Arrays_parallelPrefix(T[] a, int from, int to, java.util.function.BinaryOperator<T> op) { java.util.Objects.requireNonNull(op); arrayRange(a.length, from, to); for (int i = from + 1; i < to; i++) a[i] = op.apply(a[i - 1], a[i]); }
  public static void Arrays_parallelPrefix(int[] a, java.util.function.IntBinaryOperator op) { java.util.Objects.requireNonNull(op); for (int i = 1; i < a.length; i++) a[i] = op.applyAsInt(a[i - 1], a[i]); }
  public static void Arrays_parallelPrefix(int[] a, int from, int to, java.util.function.IntBinaryOperator op) { java.util.Objects.requireNonNull(op); arrayRange(a.length, from, to); for (int i = from + 1; i < to; i++) a[i] = op.applyAsInt(a[i - 1], a[i]); }
  public static void Arrays_parallelPrefix(long[] a, java.util.function.LongBinaryOperator op) { java.util.Objects.requireNonNull(op); for (int i = 1; i < a.length; i++) a[i] = op.applyAsLong(a[i - 1], a[i]); }
  public static void Arrays_parallelPrefix(long[] a, int from, int to, java.util.function.LongBinaryOperator op) { java.util.Objects.requireNonNull(op); arrayRange(a.length, from, to); for (int i = from + 1; i < to; i++) a[i] = op.applyAsLong(a[i - 1], a[i]); }
  public static void Arrays_parallelPrefix(double[] a, java.util.function.DoubleBinaryOperator op) { java.util.Objects.requireNonNull(op); for (int i = 1; i < a.length; i++) a[i] = op.applyAsDouble(a[i - 1], a[i]); }
  public static void Arrays_parallelPrefix(double[] a, int from, int to, java.util.function.DoubleBinaryOperator op) { java.util.Objects.requireNonNull(op); arrayRange(a.length, from, to); for (int i = from + 1; i < to; i++) a[i] = op.applyAsDouble(a[i - 1], a[i]); }
  public static <T> void Arrays_parallelSetAll(T[] a, java.util.function.IntFunction<? extends T> f) { java.util.Objects.requireNonNull(f); for (int i = 0; i < a.length; i++) a[i] = f.apply(i); }
  public static void Arrays_parallelSetAll(int[] a, java.util.function.IntUnaryOperator f) { java.util.Objects.requireNonNull(f); for (int i = 0; i < a.length; i++) a[i] = f.applyAsInt(i); }
  public static void Arrays_parallelSetAll(long[] a, java.util.function.IntToLongFunction f) { java.util.Objects.requireNonNull(f); for (int i = 0; i < a.length; i++) a[i] = f.applyAsLong(i); }
  public static void Arrays_parallelSetAll(double[] a, java.util.function.IntToDoubleFunction f) { java.util.Objects.requireNonNull(f); for (int i = 0; i < a.length; i++) a[i] = f.applyAsDouble(i); }
  public static void Arrays_parallelSort(byte[] a) { java.util.Arrays.sort(a); }
  public static void Arrays_parallelSort(byte[] a, int from, int to) { java.util.Arrays.sort(a, from, to); }
  public static void Arrays_parallelSort(char[] a) { java.util.Arrays.sort(a); }
  public static void Arrays_parallelSort(char[] a, int from, int to) { java.util.Arrays.sort(a, from, to); }
  public static void Arrays_parallelSort(short[] a) { java.util.Arrays.sort(a); }
  public static void Arrays_parallelSort(short[] a, int from, int to) { java.util.Arrays.sort(a, from, to); }
  public static void Arrays_parallelSort(int[] a) { java.util.Arrays.sort(a); }
  public static void Arrays_parallelSort(int[] a, int from, int to) { java.util.Arrays.sort(a, from, to); }
  public static void Arrays_parallelSort(long[] a) { java.util.Arrays.sort(a); }
  public static void Arrays_parallelSort(long[] a, int from, int to) { java.util.Arrays.sort(a, from, to); }
  public static void Arrays_parallelSort(float[] a) { java.util.Arrays.sort(a); }
  public static void Arrays_parallelSort(float[] a, int from, int to) { java.util.Arrays.sort(a, from, to); }
  public static void Arrays_parallelSort(double[] a) { java.util.Arrays.sort(a); }
  public static void Arrays_parallelSort(double[] a, int from, int to) { java.util.Arrays.sort(a, from, to); }
  public static <T extends Comparable<? super T>> void Arrays_parallelSort(T[] a) { java.util.Arrays.sort(a); }
  public static <T extends Comparable<? super T>> void Arrays_parallelSort(T[] a, int from, int to) { java.util.Arrays.sort(a, from, to); }
  public static <T> void Arrays_parallelSort(T[] a, java.util.Comparator<? super T> c) { java.util.Arrays.sort(a, c); }
  public static <T> void Arrays_parallelSort(T[] a, int from, int to, java.util.Comparator<? super T> c) { java.util.Arrays.sort(a, from, to, c); }
  public static <T> java.util.Spliterator<T> Arrays_spliterator(T[] a) { return a2w.jdk.util.Spl.of(a, 0, a.length, java.util.Spliterator.ORDERED | java.util.Spliterator.IMMUTABLE); }
  public static <T> java.util.Spliterator<T> Arrays_spliterator(T[] a, int from, int to) { return a2w.jdk.util.Spl.of(a, from, to, java.util.Spliterator.ORDERED | java.util.Spliterator.IMMUTABLE); }
  public static java.util.Spliterator.OfInt Arrays_spliterator(int[] a) { return a2w.jdk.util.Spl.of(a, 0, a.length, java.util.Spliterator.ORDERED | java.util.Spliterator.IMMUTABLE); }
  public static java.util.Spliterator.OfInt Arrays_spliterator(int[] a, int from, int to) { return a2w.jdk.util.Spl.of(a, from, to, java.util.Spliterator.ORDERED | java.util.Spliterator.IMMUTABLE); }
  public static java.util.Spliterator.OfLong Arrays_spliterator(long[] a) { return a2w.jdk.util.Spl.of(a, 0, a.length, java.util.Spliterator.ORDERED | java.util.Spliterator.IMMUTABLE); }
  public static java.util.Spliterator.OfLong Arrays_spliterator(long[] a, int from, int to) { return a2w.jdk.util.Spl.of(a, from, to, java.util.Spliterator.ORDERED | java.util.Spliterator.IMMUTABLE); }
  public static java.util.Spliterator.OfDouble Arrays_spliterator(double[] a) { return a2w.jdk.util.Spl.of(a, 0, a.length, java.util.Spliterator.ORDERED | java.util.Spliterator.IMMUTABLE); }
  public static java.util.Spliterator.OfDouble Arrays_spliterator(double[] a, int from, int to) { return a2w.jdk.util.Spl.of(a, from, to, java.util.Spliterator.ORDERED | java.util.Spliterator.IMMUTABLE); }

  // ---- java.util.Collections: sorted, navigable, sequenced and queue views (a2w.jdk.util.Wrap)
  public static <T> java.util.Queue<T> Collections_asLifoQueue(java.util.Deque<T> d) { return a2w.jdk.util.Wrap.asLifoQueue(d); }
  public static <K, V> java.util.NavigableMap<K, V> Collections_checkedNavigableMap(java.util.NavigableMap<K, V> m, Class<K> kt, Class<V> vt) { return a2w.jdk.util.Wrap.checkedMap(m, kt, vt); }
  public static <E> java.util.NavigableSet<E> Collections_checkedNavigableSet(java.util.NavigableSet<E> s, Class<E> t) { return a2w.jdk.util.Wrap.checkedSet(s, t); }
  public static <E> java.util.Queue<E> Collections_checkedQueue(java.util.Queue<E> q, Class<E> t) { return a2w.jdk.util.Wrap.checkedQueue(q, t); }
  public static <K, V> java.util.SortedMap<K, V> Collections_checkedSortedMap(java.util.SortedMap<K, V> m, Class<K> kt, Class<V> vt) { return a2w.jdk.util.Wrap.checkedMap(m, kt, vt); }
  public static <E> java.util.SortedSet<E> Collections_checkedSortedSet(java.util.SortedSet<E> s, Class<E> t) { return a2w.jdk.util.Wrap.checkedSet(s, t); }
  public static <K, V> java.util.NavigableMap<K, V> Collections_emptyNavigableMap() { return a2w.jdk.util.Wrap.emptyNavigableMap(); }
  public static <E> java.util.NavigableSet<E> Collections_emptyNavigableSet() { return a2w.jdk.util.Wrap.emptyNavigableSet(); }
  public static <K, V> java.util.SortedMap<K, V> Collections_emptySortedMap() { return a2w.jdk.util.Wrap.emptyNavigableMap(); }
  public static <E> java.util.SortedSet<E> Collections_emptySortedSet() { return a2w.jdk.util.Wrap.emptyNavigableSet(); }
  public static <K, V> java.util.NavigableMap<K, V> Collections_synchronizedNavigableMap(java.util.NavigableMap<K, V> m) { return a2w.jdk.util.Wrap.synchronizedMap(m); }
  public static <E> java.util.NavigableSet<E> Collections_synchronizedNavigableSet(java.util.NavigableSet<E> s) { return a2w.jdk.util.Wrap.synchronizedSet(s); }
  public static <K, V> java.util.SortedMap<K, V> Collections_synchronizedSortedMap(java.util.SortedMap<K, V> m) { return a2w.jdk.util.Wrap.synchronizedMap(m); }
  public static <E> java.util.SortedSet<E> Collections_synchronizedSortedSet(java.util.SortedSet<E> s) { return a2w.jdk.util.Wrap.synchronizedSet(s); }
  public static <K, V> java.util.NavigableMap<K, V> Collections_unmodifiableNavigableMap(java.util.NavigableMap<K, ? extends V> m) { return a2w.jdk.util.Wrap.unmodifiableMap(m); }
  public static <E> java.util.NavigableSet<E> Collections_unmodifiableNavigableSet(java.util.NavigableSet<E> s) { return a2w.jdk.util.Wrap.unmodifiableSet(s); }
  public static <K, V> java.util.SortedMap<K, V> Collections_unmodifiableSortedMap(java.util.SortedMap<K, ? extends V> m) { return a2w.jdk.util.Wrap.unmodifiableMap(m); }
  public static <E> java.util.SortedSet<E> Collections_unmodifiableSortedSet(java.util.SortedSet<E> s) { return a2w.jdk.util.Wrap.unmodifiableSet(s); }

  // ---- java.util.concurrent.ConcurrentHashMap: the bulk operations run sequentially (the parallelism threshold is unused)
  public static <K, V> java.util.Enumeration<K> ConcurrentHashMap_keys(java.util.concurrent.ConcurrentHashMap<K, V> m) { return java.util.Collections.enumeration(new java.util.ArrayList<>(m.keySet())); }
  public static <K, V> java.util.Enumeration<V> ConcurrentHashMap_elements(java.util.concurrent.ConcurrentHashMap<K, V> m) { return java.util.Collections.enumeration(new java.util.ArrayList<>(m.values())); }
  public static long ConcurrentHashMap_mappingCount(java.util.concurrent.ConcurrentHashMap<?, ?> m) { return m.size(); }
  public static <K, V> a2w.jdk.util.concurrent.ConcurrentHashMap$KeySetView<K, V> ConcurrentHashMap_keySet(java.util.concurrent.ConcurrentHashMap<K, V> m, V v) {
    return new a2w.jdk.util.concurrent.ConcurrentHashMap$KeySetView<>(m, java.util.Objects.requireNonNull(v));
  }
  public static <K> a2w.jdk.util.concurrent.ConcurrentHashMap$KeySetView<K, Boolean> ConcurrentHashMap_newKeySet() { return new a2w.jdk.util.concurrent.ConcurrentHashMap$KeySetView<>(new java.util.concurrent.ConcurrentHashMap<K, Boolean>(), Boolean.TRUE); }
  public static <K> a2w.jdk.util.concurrent.ConcurrentHashMap$KeySetView<K, Boolean> ConcurrentHashMap_newKeySet(int n) { return new a2w.jdk.util.concurrent.ConcurrentHashMap$KeySetView<>(new java.util.concurrent.ConcurrentHashMap<K, Boolean>(n), Boolean.TRUE); }
  static <K, V> java.util.List<java.util.Map.Entry<K, V>> chmEntries(java.util.concurrent.ConcurrentHashMap<K, V> m) {
    java.util.List<java.util.Map.Entry<K, V>> l = new java.util.ArrayList<>();
    for (java.util.Map.Entry<K, V> e : m.entrySet()) l.add(new java.util.AbstractMap.SimpleImmutableEntry<>(e));
    return l;
  }
  public static <K, V> void ConcurrentHashMap_forEach(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.BiConsumer<? super K, ? super V> a) { java.util.Objects.requireNonNull(a); for (java.util.Map.Entry<K, V> e : chmEntries(m)) a.accept(e.getKey(), e.getValue()); }
  public static <K, V, U> void ConcurrentHashMap_forEach(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.BiFunction<? super K, ? super V, ? extends U> f, java.util.function.Consumer<? super U> a) {
    java.util.Objects.requireNonNull(f); java.util.Objects.requireNonNull(a);
    for (java.util.Map.Entry<K, V> e : chmEntries(m)) { U u = f.apply(e.getKey(), e.getValue()); if (u != null) a.accept(u); }
  }
  public static <K, V> void ConcurrentHashMap_forEachEntry(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Consumer<? super java.util.Map.Entry<K, V>> a) { java.util.Objects.requireNonNull(a); for (java.util.Map.Entry<K, V> e : chmEntries(m)) a.accept(e); }
  public static <K, V, U> void ConcurrentHashMap_forEachEntry(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Function<java.util.Map.Entry<K, V>, ? extends U> f, java.util.function.Consumer<? super U> a) {
    java.util.Objects.requireNonNull(f); java.util.Objects.requireNonNull(a);
    for (java.util.Map.Entry<K, V> e : chmEntries(m)) { U u = f.apply(e); if (u != null) a.accept(u); }
  }
  public static <K, V> void ConcurrentHashMap_forEachKey(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Consumer<? super K> a) { java.util.Objects.requireNonNull(a); for (java.util.Map.Entry<K, V> e : chmEntries(m)) a.accept(e.getKey()); }
  public static <K, V, U> void ConcurrentHashMap_forEachKey(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Function<? super K, ? extends U> f, java.util.function.Consumer<? super U> a) {
    java.util.Objects.requireNonNull(f); java.util.Objects.requireNonNull(a);
    for (java.util.Map.Entry<K, V> e : chmEntries(m)) { U u = f.apply(e.getKey()); if (u != null) a.accept(u); }
  }
  public static <K, V> void ConcurrentHashMap_forEachValue(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Consumer<? super V> a) { java.util.Objects.requireNonNull(a); for (java.util.Map.Entry<K, V> e : chmEntries(m)) a.accept(e.getValue()); }
  public static <K, V, U> void ConcurrentHashMap_forEachValue(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Function<? super V, ? extends U> f, java.util.function.Consumer<? super U> a) {
    java.util.Objects.requireNonNull(f); java.util.Objects.requireNonNull(a);
    for (java.util.Map.Entry<K, V> e : chmEntries(m)) { U u = f.apply(e.getValue()); if (u != null) a.accept(u); }
  }
  public static <K, V, U> U ConcurrentHashMap_search(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.BiFunction<? super K, ? super V, ? extends U> f) {
    java.util.Objects.requireNonNull(f); for (java.util.Map.Entry<K, V> e : chmEntries(m)) { U u = f.apply(e.getKey(), e.getValue()); if (u != null) return u; } return null;
  }
  public static <K, V, U> U ConcurrentHashMap_searchEntries(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Function<java.util.Map.Entry<K, V>, ? extends U> f) {
    java.util.Objects.requireNonNull(f); for (java.util.Map.Entry<K, V> e : chmEntries(m)) { U u = f.apply(e); if (u != null) return u; } return null;
  }
  public static <K, V, U> U ConcurrentHashMap_searchKeys(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Function<? super K, ? extends U> f) {
    java.util.Objects.requireNonNull(f); for (java.util.Map.Entry<K, V> e : chmEntries(m)) { U u = f.apply(e.getKey()); if (u != null) return u; } return null;
  }
  public static <K, V, U> U ConcurrentHashMap_searchValues(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Function<? super V, ? extends U> f) {
    java.util.Objects.requireNonNull(f); for (java.util.Map.Entry<K, V> e : chmEntries(m)) { U u = f.apply(e.getValue()); if (u != null) return u; } return null;
  }
  static <T, U> U chmReduce(java.util.List<T> items, java.util.function.Function<? super T, ? extends U> f, java.util.function.BiFunction<? super U, ? super U, ? extends U> r) {
    java.util.Objects.requireNonNull(f); java.util.Objects.requireNonNull(r);
    U acc = null;
    for (T x : items) { U u = f.apply(x); if (u != null) acc = acc == null ? u : r.apply(acc, u); }
    return acc;
  }
  static <K, V> java.util.List<K> chmKeys(java.util.concurrent.ConcurrentHashMap<K, V> m) { java.util.List<K> l = new java.util.ArrayList<>(); for (java.util.Map.Entry<K, V> e : chmEntries(m)) l.add(e.getKey()); return l; }
  static <K, V> java.util.List<V> chmValues(java.util.concurrent.ConcurrentHashMap<K, V> m) { java.util.List<V> l = new java.util.ArrayList<>(); for (java.util.Map.Entry<K, V> e : chmEntries(m)) l.add(e.getValue()); return l; }
  public static <K, V, U> U ConcurrentHashMap_reduce(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.BiFunction<? super K, ? super V, ? extends U> f, java.util.function.BiFunction<? super U, ? super U, ? extends U> r) {
    java.util.Objects.requireNonNull(f); return chmReduce(chmEntries(m), e -> f.apply(e.getKey(), e.getValue()), r);
  }
  public static <K, V> java.util.Map.Entry<K, V> ConcurrentHashMap_reduceEntries(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.BiFunction<java.util.Map.Entry<K, V>, java.util.Map.Entry<K, V>, ? extends java.util.Map.Entry<K, V>> r) {
    return chmReduce(chmEntries(m), e -> e, r);
  }
  public static <K, V, U> U ConcurrentHashMap_reduceEntries(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Function<java.util.Map.Entry<K, V>, ? extends U> f, java.util.function.BiFunction<? super U, ? super U, ? extends U> r) { return chmReduce(chmEntries(m), f, r); }
  public static <K, V> K ConcurrentHashMap_reduceKeys(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.BiFunction<? super K, ? super K, ? extends K> r) { return chmReduce(chmKeys(m), k -> k, r); }
  public static <K, V, U> U ConcurrentHashMap_reduceKeys(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Function<? super K, ? extends U> f, java.util.function.BiFunction<? super U, ? super U, ? extends U> r) { return chmReduce(chmKeys(m), f, r); }
  public static <K, V> V ConcurrentHashMap_reduceValues(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.BiFunction<? super V, ? super V, ? extends V> r) { return chmReduce(chmValues(m), v -> v, r); }
  public static <K, V, U> U ConcurrentHashMap_reduceValues(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.Function<? super V, ? extends U> f, java.util.function.BiFunction<? super U, ? super U, ? extends U> r) { return chmReduce(chmValues(m), f, r); }
  public static <K, V> double ConcurrentHashMap_reduceToDouble(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToDoubleBiFunction<? super K, ? super V> f, double basis, java.util.function.DoubleBinaryOperator r) {
    double acc = basis; for (java.util.Map.Entry<K, V> e : chmEntries(m)) acc = r.applyAsDouble(acc, f.applyAsDouble(e.getKey(), e.getValue())); return acc;
  }
  public static <K, V> int ConcurrentHashMap_reduceToInt(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToIntBiFunction<? super K, ? super V> f, int basis, java.util.function.IntBinaryOperator r) {
    int acc = basis; for (java.util.Map.Entry<K, V> e : chmEntries(m)) acc = r.applyAsInt(acc, f.applyAsInt(e.getKey(), e.getValue())); return acc;
  }
  public static <K, V> long ConcurrentHashMap_reduceToLong(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToLongBiFunction<? super K, ? super V> f, long basis, java.util.function.LongBinaryOperator r) {
    long acc = basis; for (java.util.Map.Entry<K, V> e : chmEntries(m)) acc = r.applyAsLong(acc, f.applyAsLong(e.getKey(), e.getValue())); return acc;
  }
  public static <K, V> double ConcurrentHashMap_reduceEntriesToDouble(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToDoubleFunction<java.util.Map.Entry<K, V>> f, double basis, java.util.function.DoubleBinaryOperator r) {
    double acc = basis; for (java.util.Map.Entry<K, V> x : chmEntries(m)) acc = r.applyAsDouble(acc, f.applyAsDouble(x)); return acc;
  }
  public static <K, V> int ConcurrentHashMap_reduceEntriesToInt(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToIntFunction<java.util.Map.Entry<K, V>> f, int basis, java.util.function.IntBinaryOperator r) {
    int acc = basis; for (java.util.Map.Entry<K, V> x : chmEntries(m)) acc = r.applyAsInt(acc, f.applyAsInt(x)); return acc;
  }
  public static <K, V> long ConcurrentHashMap_reduceEntriesToLong(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToLongFunction<java.util.Map.Entry<K, V>> f, long basis, java.util.function.LongBinaryOperator r) {
    long acc = basis; for (java.util.Map.Entry<K, V> x : chmEntries(m)) acc = r.applyAsLong(acc, f.applyAsLong(x)); return acc;
  }
  public static <K, V> double ConcurrentHashMap_reduceKeysToDouble(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToDoubleFunction<? super K> f, double basis, java.util.function.DoubleBinaryOperator r) {
    double acc = basis; for (K x : chmKeys(m)) acc = r.applyAsDouble(acc, f.applyAsDouble(x)); return acc;
  }
  public static <K, V> int ConcurrentHashMap_reduceKeysToInt(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToIntFunction<? super K> f, int basis, java.util.function.IntBinaryOperator r) {
    int acc = basis; for (K x : chmKeys(m)) acc = r.applyAsInt(acc, f.applyAsInt(x)); return acc;
  }
  public static <K, V> long ConcurrentHashMap_reduceKeysToLong(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToLongFunction<? super K> f, long basis, java.util.function.LongBinaryOperator r) {
    long acc = basis; for (K x : chmKeys(m)) acc = r.applyAsLong(acc, f.applyAsLong(x)); return acc;
  }
  public static <K, V> double ConcurrentHashMap_reduceValuesToDouble(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToDoubleFunction<? super V> f, double basis, java.util.function.DoubleBinaryOperator r) {
    double acc = basis; for (V x : chmValues(m)) acc = r.applyAsDouble(acc, f.applyAsDouble(x)); return acc;
  }
  public static <K, V> int ConcurrentHashMap_reduceValuesToInt(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToIntFunction<? super V> f, int basis, java.util.function.IntBinaryOperator r) {
    int acc = basis; for (V x : chmValues(m)) acc = r.applyAsInt(acc, f.applyAsInt(x)); return acc;
  }
  public static <K, V> long ConcurrentHashMap_reduceValuesToLong(java.util.concurrent.ConcurrentHashMap<K, V> m, long t, java.util.function.ToLongFunction<? super V> f, long basis, java.util.function.LongBinaryOperator r) {
    long acc = basis; for (V x : chmValues(m)) acc = r.applyAsLong(acc, f.applyAsLong(x)); return acc;
  }

  // ---- java.util.Spliterators (a2w.jdk.util.Spl)
  public static <T> java.util.Spliterator<T> Spliterators_emptySpliterator() { return a2w.jdk.util.Spl.empty(); }
  public static java.util.Spliterator.OfInt Spliterators_emptyIntSpliterator() { return a2w.jdk.util.Spl.emptyInt(); }
  public static java.util.Spliterator.OfLong Spliterators_emptyLongSpliterator() { return a2w.jdk.util.Spl.emptyLong(); }
  public static java.util.Spliterator.OfDouble Spliterators_emptyDoubleSpliterator() { return a2w.jdk.util.Spl.emptyDouble(); }
  public static <T> java.util.Iterator<T> Spliterators_iterator(java.util.Spliterator<? extends T> s) { return a2w.jdk.util.Spl.iterator(s); }
  public static java.util.PrimitiveIterator.OfInt Spliterators_iterator(java.util.Spliterator.OfInt s) { return a2w.jdk.util.Spl.iterator(s); }
  public static java.util.PrimitiveIterator.OfLong Spliterators_iterator(java.util.Spliterator.OfLong s) { return a2w.jdk.util.Spl.iterator(s); }
  public static java.util.PrimitiveIterator.OfDouble Spliterators_iterator(java.util.Spliterator.OfDouble s) { return a2w.jdk.util.Spl.iterator(s); }
  public static <T> java.util.Spliterator<T> Spliterators_spliterator(Object[] a, int from, int to, int ch) { return a2w.jdk.util.Spl.of(a, from, to, ch); }
  public static java.util.Spliterator.OfLong Spliterators_spliterator(long[] a, int ch) { return a2w.jdk.util.Spl.of(a, 0, a.length, ch); }
  public static java.util.Spliterator.OfLong Spliterators_spliterator(long[] a, int from, int to, int ch) { return a2w.jdk.util.Spl.of(a, from, to, ch); }
  public static java.util.Spliterator.OfDouble Spliterators_spliterator(double[] a, int ch) { return a2w.jdk.util.Spl.of(a, 0, a.length, ch); }
  public static java.util.Spliterator.OfDouble Spliterators_spliterator(double[] a, int from, int to, int ch) { return a2w.jdk.util.Spl.of(a, from, to, ch); }

  // ---- java.lang.StrictMath (the same results as Math: the operations are exact or correctly rounded)
  public static int StrictMath_absExact(int a) { if (a == Integer.MIN_VALUE) throw new ArithmeticException("Overflow to represent absolute value of Integer.MIN_VALUE"); return Math.abs(a); }
  public static long StrictMath_absExact(long a) { if (a == Long.MIN_VALUE) throw new ArithmeticException("Overflow to represent absolute value of Long.MIN_VALUE"); return Math.abs(a); }
  public static int StrictMath_ceilDiv(int x, int y) { int q = x / y; if ((x ^ y) >= 0 && q * y != x) q++; return q; }
  public static long StrictMath_ceilDiv(long x, long y) { long q = x / y; if ((x ^ y) >= 0 && q * y != x) q++; return q; }
  public static long StrictMath_ceilDiv(long x, int y) { return StrictMath_ceilDiv(x, (long) y); }
  public static int StrictMath_ceilDivExact(int x, int y) { if (x == Integer.MIN_VALUE && y == -1) throw new ArithmeticException("integer overflow"); return StrictMath_ceilDiv(x, y); }
  public static long StrictMath_ceilDivExact(long x, long y) { if (x == Long.MIN_VALUE && y == -1) throw new ArithmeticException("long overflow"); return StrictMath_ceilDiv(x, y); }
  public static int StrictMath_ceilMod(int x, int y) { int r = x % y; if ((x ^ y) >= 0 && r != 0) r -= y; return r; }
  public static long StrictMath_ceilMod(long x, long y) { long r = x % y; if ((x ^ y) >= 0 && r != 0) r -= y; return r; }
  public static int StrictMath_ceilMod(long x, int y) { return (int) StrictMath_ceilMod(x, (long) y); }
  public static int StrictMath_floorDivExact(int x, int y) { if (x == Integer.MIN_VALUE && y == -1) throw new ArithmeticException("integer overflow"); return Math.floorDiv(x, y); }
  public static long StrictMath_floorDivExact(long x, long y) { if (x == Long.MIN_VALUE && y == -1) throw new ArithmeticException("long overflow"); return Math.floorDiv(x, y); }
  public static double StrictMath_fma(double a, double b, double c) { return Math_fma(a, b, c); }
  public static float StrictMath_fma(float a, float b, float c) { return Math_fma(a, b, c); }
  public static long StrictMath_multiplyFull(int x, int y) { return Math_multiplyFull(x, y); }
  public static long StrictMath_multiplyHigh(long x, long y) { return Math_multiplyHigh(x, y); }
  public static long StrictMath_unsignedMultiplyHigh(long x, long y) { return Math_unsignedMultiplyHigh(x, y); }
  public static double StrictMath_nextDown(double d) { return Math.nextDown(d); }
  public static float StrictMath_nextDown(float f) { return Math.nextDown(f); }
  public static double StrictMath_scalb(double d, int s) { return Math.scalb(d, s); }
  public static float StrictMath_scalb(float f, int s) { return Math.scalb(f, s); }

  // ---- java.lang.reflect.Array: primitive access with the widening conversions
  static IllegalArgumentException arrayMismatch(Object a) {
    if (a == null) throw new NullPointerException();
    return new IllegalArgumentException(a.getClass().isArray() ? "argument type mismatch" : "Argument is not an array");
  }
  public static boolean Array_getBoolean(Object a, int i) { if (a instanceof boolean[]) return ((boolean[]) a)[i]; throw arrayMismatch(a); }
  public static byte Array_getByte(Object a, int i) { if (a instanceof byte[]) return ((byte[]) a)[i]; throw arrayMismatch(a); }
  public static char Array_getChar(Object a, int i) { if (a instanceof char[]) return ((char[]) a)[i]; throw arrayMismatch(a); }
  public static short Array_getShort(Object a, int i) { if (a instanceof short[]) return ((short[]) a)[i]; if (a instanceof byte[]) return ((byte[]) a)[i]; throw arrayMismatch(a); }
  public static int Array_getInt(Object a, int i) {
    if (a instanceof int[]) return ((int[]) a)[i];
    if (a instanceof char[]) return ((char[]) a)[i];
    if (a instanceof short[]) return ((short[]) a)[i];
    if (a instanceof byte[]) return ((byte[]) a)[i];
    throw arrayMismatch(a);
  }
  public static long Array_getLong(Object a, int i) { if (a instanceof long[]) return ((long[]) a)[i]; return Array_getInt(a, i); }
  public static float Array_getFloat(Object a, int i) { if (a instanceof float[]) return ((float[]) a)[i]; return Array_getLong(a, i); }
  public static double Array_getDouble(Object a, int i) { if (a instanceof double[]) return ((double[]) a)[i]; return Array_getFloat(a, i); }
  public static void Array_setBoolean(Object a, int i, boolean v) { if (a instanceof boolean[]) ((boolean[]) a)[i] = v; else throw arrayMismatch(a); }
  public static void Array_setDouble(Object a, int i, double v) { if (a instanceof double[]) ((double[]) a)[i] = v; else throw arrayMismatch(a); }
  public static void Array_setFloat(Object a, int i, float v) { if (a instanceof float[]) ((float[]) a)[i] = v; else Array_setDouble(a, i, v); }
  public static void Array_setLong(Object a, int i, long v) { if (a instanceof long[]) ((long[]) a)[i] = v; else Array_setFloat(a, i, v); }
  public static void Array_setInt(Object a, int i, int v) { if (a instanceof int[]) ((int[]) a)[i] = v; else Array_setLong(a, i, v); }
  public static void Array_setShort(Object a, int i, short v) { if (a instanceof short[]) ((short[]) a)[i] = v; else Array_setInt(a, i, v); }
  public static void Array_setChar(Object a, int i, char v) { if (a instanceof char[]) ((char[]) a)[i] = v; else Array_setInt(a, i, v); }
  public static void Array_setByte(Object a, int i, byte v) { if (a instanceof byte[]) ((byte[]) a)[i] = v; else Array_setShort(a, i, v); }
  public static Object Array_newInstance(Class<?> c, int[] dims) {
    if (dims.length == 0) throw new IllegalArgumentException("Empty dimensions array");
    if (c == void.class) throw new IllegalArgumentException();
    return multiArray(c, dims, 0);
  }
  static Object multiArray(Class<?> c, int[] d, int k) {
    if (d[k] < 0) throw new NegativeArraySizeException(String.valueOf(d[k]));
    if (k == d.length - 1) return java.lang.reflect.Array.newInstance(c, d[k]);
    Class<?> sub = c;
    for (int j = k + 1; j < d.length; j++) sub = java.lang.reflect.Array.newInstance(sub, 0).getClass();
    Object[] arr = (Object[]) java.lang.reflect.Array.newInstance(sub, d[k]);
    for (int i = 0; i < arr.length; i++) arr[i] = multiArray(c, d, k + 1);
    return arr;
  }

  // ---- java.lang.reflect.Modifier
  public static int Modifier_classModifiers() { return 0xC1F; }
  public static int Modifier_interfaceModifiers() { return 0xC0F; }
  public static int Modifier_constructorModifiers() { return 0x7; }
  public static int Modifier_methodModifiers() { return 0xD3F; }
  public static int Modifier_fieldModifiers() { return 0xDF; }
  public static int Modifier_parameterModifiers() { return 0x10; }

  // ---- java.time.Duration (TeaVM's java.time is ThreeTen's, without the Java 9 part accessors)
  public static long Duration_toSeconds(java.time.Duration d) { return d.getSeconds(); }
  public static long Duration_toDaysPart(java.time.Duration d) { return d.getSeconds() / 86400; }
  public static int Duration_toHoursPart(java.time.Duration d) { return (int) (d.toHours() % 24); }
  public static int Duration_toMinutesPart(java.time.Duration d) { return (int) (d.toMinutes() % 60); }
  public static int Duration_toSecondsPart(java.time.Duration d) { return (int) (d.getSeconds() % 60); }
  public static int Duration_toMillisPart(java.time.Duration d) { return d.getNano() / 1_000_000; }
  public static int Duration_toNanosPart(java.time.Duration d) { return d.getNano(); }
  public static long Duration_dividedBy(java.time.Duration d, java.time.Duration divisor) {
    java.math.BigInteger a = java.math.BigInteger.valueOf(d.getSeconds()).multiply(java.math.BigInteger.valueOf(1_000_000_000)).add(java.math.BigInteger.valueOf(d.getNano()));
    java.math.BigInteger b = java.math.BigInteger.valueOf(divisor.getSeconds()).multiply(java.math.BigInteger.valueOf(1_000_000_000)).add(java.math.BigInteger.valueOf(divisor.getNano()));
    if (b.signum() == 0) throw new ArithmeticException("Cannot divide by zero");
    return a.divide(b).longValueExact();
  }
  public static java.time.Duration Duration_truncatedTo(java.time.Duration d, java.time.temporal.TemporalUnit unit) {
    if (unit == java.time.temporal.ChronoUnit.SECONDS && (d.getSeconds() >= 0 || d.getNano() == 0)) return java.time.Duration.ofSeconds(d.getSeconds());
    if (unit == java.time.temporal.ChronoUnit.NANOS) return d;
    java.time.Duration u = unit.getDuration();
    if (u.getSeconds() > 86400) throw new java.time.temporal.UnsupportedTemporalTypeException("Unit is too large to be used for truncation");
    long dur = u.toNanos();
    if (86_400_000_000_000L % dur != 0) throw new java.time.temporal.UnsupportedTemporalTypeException("Unit must divide into a standard day without remainder");
    long nod = (d.getSeconds() % 86400) * 1_000_000_000L + d.getNano();
    long result = (nod / dur) * dur;
    return d.plusNanos(result - nod);
  }

  // ---- java.util.regex
  public static java.util.regex.Matcher Matcher_appendReplacement(java.util.regex.Matcher m, StringBuilder b, String r) { StringBuffer s = new StringBuffer(); m.appendReplacement(s, r); b.append(s); return m; }
  public static StringBuilder Matcher_appendTail(java.util.regex.Matcher m, StringBuilder b) { StringBuffer s = new StringBuffer(); m.appendTail(s); return b.append(s); }
  public static String Matcher_replaceAll(java.util.regex.Matcher m, java.util.function.Function<java.util.regex.MatchResult, String> f) {
    java.util.Objects.requireNonNull(f);
    m.reset();
    StringBuffer s = new StringBuffer();
    while (m.find()) m.appendReplacement(s, java.util.regex.Matcher.quoteReplacement(f.apply(m.toMatchResult())));
    m.appendTail(s);
    return s.toString();
  }
  public static String Matcher_replaceFirst(java.util.regex.Matcher m, java.util.function.Function<java.util.regex.MatchResult, String> f) {
    java.util.Objects.requireNonNull(f);
    m.reset();
    if (!m.find()) return textOf(m);
    StringBuffer s = new StringBuffer();
    m.appendReplacement(s, java.util.regex.Matcher.quoteReplacement(f.apply(m.toMatchResult())));
    m.appendTail(s);
    return s.toString();
  }
  static String textOf(java.util.regex.Matcher m) { m.reset(); StringBuffer s = new StringBuffer(); m.appendTail(s); return s.toString(); }
  public static java.util.stream.Stream<java.util.regex.MatchResult> Matcher_results(java.util.regex.Matcher m) {
    java.util.List<java.util.regex.MatchResult> l = new java.util.ArrayList<>();
    while (m.find()) l.add(m.toMatchResult());
    return l.stream();
  }
  public static java.util.function.Predicate<String> Pattern_asPredicate(java.util.regex.Pattern p) { return s -> p.matcher(s).find(); }
  public static java.util.function.Predicate<String> Pattern_asMatchPredicate(java.util.regex.Pattern p) { return s -> p.matcher(s).matches(); }
  public static java.util.stream.Stream<String> Pattern_splitAsStream(java.util.regex.Pattern p, CharSequence s) { return java.util.Arrays.stream(s.length() == 0 ? new String[0] : p.split(s)); }

  // ---- java.util.stream (sequential: parallel streams run on the calling thread)
  public static java.util.stream.IntStream IntStream_parallel(java.util.stream.IntStream s) { return s; }
  public static java.util.stream.IntStream IntStream_sequential(java.util.stream.IntStream s) { return s; }
  public static java.util.stream.LongStream LongStream_parallel(java.util.stream.LongStream s) { return s; }
  public static java.util.stream.LongStream LongStream_sequential(java.util.stream.LongStream s) { return s; }
  public static java.util.stream.DoubleStream DoubleStream_parallel(java.util.stream.DoubleStream s) { return s; }
  public static java.util.stream.DoubleStream DoubleStream_sequential(java.util.stream.DoubleStream s) { return s; }
  public static <T> java.util.stream.Stream<T> Stream_ofNullable(T t) { return t == null ? java.util.stream.Stream.empty() : java.util.stream.Stream.of(t); }
  public static java.util.stream.IntStream StreamSupport_intStream(java.util.Spliterator.OfInt s, boolean parallel) { return java.util.stream.StreamSupport.stream(s, false).mapToInt(Integer::intValue); }
  public static java.util.stream.LongStream StreamSupport_longStream(java.util.Spliterator.OfLong s, boolean parallel) { return java.util.stream.StreamSupport.stream(s, false).mapToLong(Long::longValue); }
  public static java.util.stream.DoubleStream StreamSupport_doubleStream(java.util.Spliterator.OfDouble s, boolean parallel) { return java.util.stream.StreamSupport.stream(s, false).mapToDouble(Double::doubleValue); }
  public static java.util.stream.IntStream StreamSupport_intStream(java.util.function.Supplier<? extends java.util.Spliterator.OfInt> s, int ch, boolean parallel) {
    return java.util.stream.Stream.of(s).flatMapToInt(x -> StreamSupport_intStream(x.get(), false));
  }
  public static java.util.stream.LongStream StreamSupport_longStream(java.util.function.Supplier<? extends java.util.Spliterator.OfLong> s, int ch, boolean parallel) {
    return java.util.stream.Stream.of(s).flatMapToLong(x -> StreamSupport_longStream(x.get(), false));
  }
  public static java.util.stream.DoubleStream StreamSupport_doubleStream(java.util.function.Supplier<? extends java.util.Spliterator.OfDouble> s, int ch, boolean parallel) {
    return java.util.stream.Stream.of(s).flatMapToDouble(x -> StreamSupport_doubleStream(x.get(), false));
  }
  public static <T, K> java.util.stream.Collector<T, ?, java.util.concurrent.ConcurrentMap<K, java.util.List<T>>> Collectors_groupingByConcurrent(java.util.function.Function<? super T, ? extends K> f) {
    return java.util.stream.Collectors.groupingBy(f, java.util.concurrent.ConcurrentHashMap::new, java.util.stream.Collectors.toList());
  }
  public static <T, K, A, D> java.util.stream.Collector<T, ?, java.util.concurrent.ConcurrentMap<K, D>> Collectors_groupingByConcurrent(java.util.function.Function<? super T, ? extends K> f, java.util.stream.Collector<? super T, A, D> down) {
    return java.util.stream.Collectors.groupingBy(f, java.util.concurrent.ConcurrentHashMap::new, down);
  }
  public static <T, K, D, A, M extends java.util.concurrent.ConcurrentMap<K, D>> java.util.stream.Collector<T, ?, M> Collectors_groupingByConcurrent(java.util.function.Function<? super T, ? extends K> f, java.util.function.Supplier<M> factory, java.util.stream.Collector<? super T, A, D> down) {
    return java.util.stream.Collectors.groupingBy(f, factory, down);
  }
  public static <T, K, U> java.util.stream.Collector<T, ?, java.util.concurrent.ConcurrentMap<K, U>> Collectors_toConcurrentMap(java.util.function.Function<? super T, ? extends K> k, java.util.function.Function<? super T, ? extends U> v) {
    return java.util.stream.Collectors.toMap(k, v, (a, b) -> { throw new IllegalStateException("Duplicate key (attempted merging values " + a + " and " + b + ")"); }, java.util.concurrent.ConcurrentHashMap::new);
  }
  public static <T, K, U> java.util.stream.Collector<T, ?, java.util.concurrent.ConcurrentMap<K, U>> Collectors_toConcurrentMap(java.util.function.Function<? super T, ? extends K> k, java.util.function.Function<? super T, ? extends U> v, java.util.function.BinaryOperator<U> merge) {
    return java.util.stream.Collectors.toMap(k, v, merge, java.util.concurrent.ConcurrentHashMap::new);
  }
  public static <T, K, U, M extends java.util.concurrent.ConcurrentMap<K, U>> java.util.stream.Collector<T, ?, M> Collectors_toConcurrentMap(java.util.function.Function<? super T, ? extends K> k, java.util.function.Function<? super T, ? extends U> v, java.util.function.BinaryOperator<U> merge, java.util.function.Supplier<M> factory) {
    return java.util.stream.Collectors.toMap(k, v, merge, factory);
  }

  // ---- java.util.Base64 buffer and stream forms
  static byte[] remaining(java.nio.ByteBuffer b) { byte[] a = new byte[b.remaining()]; b.get(a); return a; }
  public static java.nio.ByteBuffer Base64_Encoder_encode(java.util.Base64.Encoder e, java.nio.ByteBuffer b) { return java.nio.ByteBuffer.wrap(e.encode(remaining(b))); }
  public static java.nio.ByteBuffer Base64_Decoder_decode(java.util.Base64.Decoder d, java.nio.ByteBuffer b) {
    int p = b.position();
    try { return java.nio.ByteBuffer.wrap(d.decode(remaining(b))); } catch (IllegalArgumentException x) { b.position(p); throw x; }
  }
  public static int Base64_Encoder_encode(java.util.Base64.Encoder e, byte[] src, byte[] dst) {
    byte[] r = e.encode(src);
    if (dst.length < r.length) throw new IllegalArgumentException("Output byte array is too small for encoding all input bytes");
    System.arraycopy(r, 0, dst, 0, r.length);
    return r.length;
  }
  public static int Base64_Decoder_decode(java.util.Base64.Decoder d, byte[] src, byte[] dst) {
    byte[] r = d.decode(src);
    if (dst.length < r.length) throw new IllegalArgumentException("Output byte array is too small for decoding all input bytes");
    System.arraycopy(r, 0, dst, 0, r.length);
    return r.length;
  }
  public static java.io.OutputStream Base64_Encoder_wrap(java.util.Base64.Encoder e, java.io.OutputStream out) {
    java.util.Objects.requireNonNull(out);
    return new java.io.OutputStream() {
      final byte[] rest = new byte[3]; int n; boolean closed;
      public void write(int b) throws java.io.IOException { write(new byte[] {(byte) b}, 0, 1); }
      public void write(byte[] b, int off, int len) throws java.io.IOException {
        if (closed) throw new java.io.IOException("Stream is closed");
        java.io.ByteArrayOutputStream whole = new java.io.ByteArrayOutputStream();
        whole.write(rest, 0, n); whole.write(b, off, len);
        byte[] all = whole.toByteArray();
        int full = all.length / 3 * 3;
        if (full > 0) out.write(e.encode(java.util.Arrays.copyOf(all, full)));
        n = all.length - full;
        System.arraycopy(all, full, rest, 0, n);
      }
      public void flush() throws java.io.IOException { out.flush(); }
      public void close() throws java.io.IOException {
        if (closed) return;
        closed = true;
        if (n > 0) out.write(e.encode(java.util.Arrays.copyOf(rest, n)));
        out.close();
      }
    };
  }
  public static java.io.InputStream Base64_Decoder_wrap(java.util.Base64.Decoder d, java.io.InputStream in) {
    java.util.Objects.requireNonNull(in);
    return new java.io.InputStream() {
      java.io.InputStream decoded;
      java.io.InputStream src() throws java.io.IOException {
        if (decoded == null) {
          java.io.ByteArrayOutputStream all = new java.io.ByteArrayOutputStream();
          byte[] b = new byte[4096];
          for (int k; (k = in.read(b)) > 0; ) all.write(b, 0, k);
          try { decoded = new java.io.ByteArrayInputStream(d.decode(all.toByteArray())); } catch (IllegalArgumentException x) { throw new java.io.IOException(x.getMessage()); }
        }
        return decoded;
      }
      public int read() throws java.io.IOException { return src().read(); }
      public int read(byte[] b, int off, int len) throws java.io.IOException { return src().read(b, off, len); }
      public int available() throws java.io.IOException { return decoded == null ? 0 : decoded.available(); }
      public void close() throws java.io.IOException { in.close(); }
    };
  }

  // ---- java.util.Locale: ISO tables (a2w.LocaleData); TeaVM's locales carry no script or extensions
  public static String Locale_getISO3Language(java.util.Locale l) {
    String lang = l.getLanguage();
    if (lang.length() == 3 || lang.isEmpty()) return lang;
    String r = a2w.LocaleData.languages().get(lang);
    if (r == null) throw new java.util.MissingResourceException("Couldn't find 3-letter language code for " + lang, "FormatData_" + l, "ShortLanguage");
    return r;
  }
  public static String Locale_getISO3Country(java.util.Locale l) {
    String c = l.getCountry();
    if (c.length() == 3 || c.isEmpty()) return c;
    String r = a2w.LocaleData.countries().get(c);
    if (r == null) throw new java.util.MissingResourceException("Couldn't find 3-letter country code for " + c, "FormatData_" + l, "ShortCountry");
    return r;
  }
  public static String[] Locale_getISOLanguages() { return a2w.LocaleData.languages().keySet().toArray(new String[0]); }
  public static String[] Locale_getISOCountries() { return a2w.LocaleData.countries().keySet().toArray(new String[0]); }
  public static java.util.Set<String> Locale_getISOCountries(a2w.jdk.util.Locale$IsoCountryCode type) {
    java.util.Objects.requireNonNull(type);
    switch (type.name()) {
      case "PART1_ALPHA2": return java.util.Collections.unmodifiableSet(new java.util.TreeSet<>(a2w.LocaleData.countries().keySet()));
      case "PART1_ALPHA3": return java.util.Collections.unmodifiableSet(new java.util.TreeSet<>(a2w.LocaleData.countries().values()));
      default: return java.util.Collections.unmodifiableSet(new java.util.TreeSet<>(java.util.Arrays.asList(a2w.LocaleData.PART3.split(","))));
    }
  }
  public static java.util.stream.Stream<java.util.Locale> Locale_availableLocales() { return java.util.Arrays.stream(java.util.Locale.getAvailableLocales()); }
  static final java.util.Map<String, java.util.Locale> categoryDefaults = new java.util.HashMap<>();
  public static java.util.Locale Locale_getDefault(a2w.jdk.util.Locale$Category c) { java.util.Locale l = categoryDefaults.get(c.name()); return l != null ? l : java.util.Locale.getDefault(); }
  public static void Locale_setDefault(a2w.jdk.util.Locale$Category c, java.util.Locale l) { java.util.Objects.requireNonNull(c); categoryDefaults.put(c.name(), java.util.Objects.requireNonNull(l)); }
  public static String Locale_getDisplayScript(java.util.Locale l) { return ""; }
  public static String Locale_getDisplayScript(java.util.Locale l, java.util.Locale in) { return ""; }
  public static String Locale_getExtension(java.util.Locale l, char key) { return null; }
  public static java.util.Set<Character> Locale_getExtensionKeys(java.util.Locale l) { return java.util.Collections.emptySet(); }
  public static java.util.Set<String> Locale_getUnicodeLocaleAttributes(java.util.Locale l) { return java.util.Collections.emptySet(); }
  public static java.util.Set<String> Locale_getUnicodeLocaleKeys(java.util.Locale l) { return java.util.Collections.emptySet(); }
  public static boolean Locale_hasExtensions(java.util.Locale l) { return false; }
  public static java.util.Locale Locale_stripExtensions(java.util.Locale l) { return l; }

  // ---- java.util.Calendar
  static String[] calendarNames(int field, int style, java.util.Locale locale) {
    java.text.DateFormatSymbols s = java.text.DateFormatSymbols.getInstance(locale);
    boolean longStyle = (style & 0x7fff) == 2;
    switch (field) {
      case java.util.Calendar.ERA: return s.getEras();
      case java.util.Calendar.MONTH: return longStyle ? s.getMonths() : s.getShortMonths();
      case java.util.Calendar.DAY_OF_WEEK: return longStyle ? s.getWeekdays() : s.getShortWeekdays();
      case java.util.Calendar.AM_PM: return s.getAmPmStrings();
      default: return null;
    }
  }
  static void checkCalendarStyle(int field, int style, java.util.Locale locale) {
    java.util.Objects.requireNonNull(locale);
    if (field < 0 || field >= java.util.Calendar.FIELD_COUNT) throw new IllegalArgumentException("field " + field);
    int base = style & 0x7fff;
    if (style != 0 && (base < 1 || base > 4 || (style & ~0x8007) != 0)) throw new IllegalArgumentException("style " + style);
  }
  public static String Calendar_getDisplayName(java.util.Calendar c, int field, int style, java.util.Locale locale) {
    checkCalendarStyle(field, style, locale);
    if (style == 0) throw new IllegalArgumentException("style ALL_STYLES");
    String[] names = calendarNames(field, style, locale);
    if (names == null) return null;
    int v = c.get(field);
    if (v < 0 || v >= names.length || names[v] == null || names[v].isEmpty()) return null;
    String n = names[v];
    return (style & 0x7fff) == 4 ? n.substring(0, n.offsetByCodePoints(0, 1)) : n;
  }
  public static java.util.Map<String, Integer> Calendar_getDisplayNames(java.util.Calendar c, int field, int style, java.util.Locale locale) {
    checkCalendarStyle(field, style, locale);
    int[] styles = style == 0 ? new int[] {1, 2} : new int[] {style};
    java.util.Map<String, Integer> m = null;
    for (int st : styles) {
      String[] names = calendarNames(field, st, locale);
      if (names == null) continue;
      if (m == null) m = new java.util.HashMap<>();
      for (int i = 0; i < names.length; i++) {
        if (names[i] == null || names[i].isEmpty()) continue;
        m.put((st & 0x7fff) == 4 ? names[i].substring(0, names[i].offsetByCodePoints(0, 1)) : names[i], i);
      }
    }
    return m;
  }
  public static java.util.Set<String> Calendar_getAvailableCalendarTypes() { return java.util.Collections.singleton("gregory"); }
  public static String Calendar_getCalendarType(java.util.Calendar c) { return c instanceof java.util.GregorianCalendar ? "gregory" : c.getClass().getName(); }
  public static String GregorianCalendar_getCalendarType(java.util.GregorianCalendar c) { return "gregory"; }
  public static boolean Calendar_isWeekDateSupported(java.util.Calendar c) { return c instanceof java.util.GregorianCalendar; }
  public static boolean GregorianCalendar_isWeekDateSupported(java.util.GregorianCalendar c) { return true; }
  public static int Calendar_getWeekYear(java.util.Calendar c) {
    if (!(c instanceof java.util.GregorianCalendar)) throw new UnsupportedOperationException();
    return GregorianCalendar_getWeekYear((java.util.GregorianCalendar) c);
  }
  public static int GregorianCalendar_getWeekYear(java.util.GregorianCalendar c) {
    int y = c.get(java.util.Calendar.YEAR), w = c.get(java.util.Calendar.WEEK_OF_YEAR), m = c.get(java.util.Calendar.MONTH);
    if (c.get(java.util.Calendar.ERA) == java.util.GregorianCalendar.BC) y = 1 - y;
    if (m == java.util.Calendar.JANUARY && w >= 52) return y - 1;
    if (m == java.util.Calendar.DECEMBER && w == 1) return y + 1;
    return y;
  }
  public static int Calendar_getWeeksInWeekYear(java.util.Calendar c) {
    if (!(c instanceof java.util.GregorianCalendar)) throw new UnsupportedOperationException();
    return GregorianCalendar_getWeeksInWeekYear((java.util.GregorianCalendar) c);
  }
  public static int GregorianCalendar_getWeeksInWeekYear(java.util.GregorianCalendar c) {
    java.util.GregorianCalendar g = (java.util.GregorianCalendar) c.clone();
    g.setLenient(true);
    int wy = GregorianCalendar_getWeekYear(c);
    // the week of the year of the day before week 1 of the next week year
    g.clear();
    g.setFirstDayOfWeek(c.getFirstDayOfWeek()); g.setMinimalDaysInFirstWeek(c.getMinimalDaysInFirstWeek());
    g.set(java.util.Calendar.YEAR, wy + 1); g.set(java.util.Calendar.WEEK_OF_YEAR, 1); g.set(java.util.Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
    g.getTimeInMillis();
    g.add(java.util.Calendar.DAY_OF_MONTH, -1);
    return g.get(java.util.Calendar.WEEK_OF_YEAR);
  }
  public static void Calendar_setWeekDate(java.util.Calendar c, int weekYear, int weekOfYear, int dayOfWeek) {
    if (!(c instanceof java.util.GregorianCalendar)) throw new UnsupportedOperationException();
    GregorianCalendar_setWeekDate((java.util.GregorianCalendar) c, weekYear, weekOfYear, dayOfWeek);
  }
  public static void GregorianCalendar_setWeekDate(java.util.GregorianCalendar c, int weekYear, int weekOfYear, int dayOfWeek) {
    if (dayOfWeek < java.util.Calendar.SUNDAY || dayOfWeek > java.util.Calendar.SATURDAY) throw new IllegalArgumentException("invalid dayOfWeek: " + dayOfWeek);
    java.util.GregorianCalendar g = (java.util.GregorianCalendar) c.clone();
    g.setLenient(true);
    int era = weekYear > 0 ? java.util.GregorianCalendar.AD : java.util.GregorianCalendar.BC;
    g.clear();
    g.setTimeZone(c.getTimeZone()); g.setFirstDayOfWeek(c.getFirstDayOfWeek()); g.setMinimalDaysInFirstWeek(c.getMinimalDaysInFirstWeek());
    g.set(java.util.Calendar.ERA, era); g.set(java.util.Calendar.YEAR, weekYear > 0 ? weekYear : 1 - weekYear);
    g.set(java.util.Calendar.WEEK_OF_YEAR, 1); g.set(java.util.Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
    g.getTimeInMillis();
    int days = (dayOfWeek - c.getFirstDayOfWeek() + 7) % 7 + (weekOfYear - 1) * 7;
    g.add(java.util.Calendar.DAY_OF_MONTH, days);
    if (!c.isLenient() && (GregorianCalendar_getWeekYear(g) != weekYear || g.get(java.util.Calendar.WEEK_OF_YEAR) != weekOfYear)) throw new IllegalArgumentException();
    int h = c.get(java.util.Calendar.HOUR_OF_DAY), mi = c.get(java.util.Calendar.MINUTE), s = c.get(java.util.Calendar.SECOND), ms = c.get(java.util.Calendar.MILLISECOND);
    c.set(g.get(java.util.Calendar.YEAR), g.get(java.util.Calendar.MONTH), g.get(java.util.Calendar.DAY_OF_MONTH), h, mi, s);
    c.set(java.util.Calendar.ERA, g.get(java.util.Calendar.ERA));
    c.set(java.util.Calendar.MILLISECOND, ms);
    c.getTimeInMillis();
  }
  public static boolean TimeZone_observesDaylightTime(java.util.TimeZone tz) { return tz.useDaylightTime() || tz.inDaylightTime(new java.util.Date()); }

  // ---- java.text.SimpleDateFormat: Android's localized pattern characters are the unlocalized ones
  public static String SimpleDateFormat_toLocalizedPattern(java.text.SimpleDateFormat f) { return f.toPattern(); }
  public static void SimpleDateFormat_applyLocalizedPattern(java.text.SimpleDateFormat f, String p) { f.applyPattern(p); }

  // ---- java.util.Properties
  public static void Properties_list(java.util.Properties p, java.io.PrintWriter out) {
    out.println("-- listing properties --");
    for (String k : p.stringPropertyNames()) {
      String v = p.getProperty(k);
      if (v.length() > 40) v = v.substring(0, 37) + "...";
      out.println(k + "=" + v);
    }
  }
  static String propEscape(String s, boolean key) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
        case ' ': b.append(i == 0 || key ? "\\ " : " "); break;
        case '\t': b.append("\\t"); break;
        case '\n': b.append("\\n"); break;
        case '\r': b.append("\\r"); break;
        case '\f': b.append("\\f"); break;
        case '=': case ':': case '#': case '!': case '\\': b.append('\\').append(c); break;
        default: b.append(c);
      }
    }
    return b.toString();
  }
  public static void Properties_store(java.util.Properties p, java.io.Writer w, String comments) throws java.io.IOException {
    java.io.BufferedWriter out = w instanceof java.io.BufferedWriter ? (java.io.BufferedWriter) w : new java.io.BufferedWriter(w);
    String nl = System.lineSeparator();
    if (comments != null) for (String line : comments.split("\r\n|\r|\n", -1)) out.write("#" + line + nl);
    out.write("#" + new java.util.Date() + nl);
    for (java.util.Map.Entry<Object, Object> e : new java.util.ArrayList<>(p.entrySet())) out.write(propEscape((String) e.getKey(), true) + "=" + propEscape((String) e.getValue(), false) + nl);
    out.flush();
  }
  static String xmlEscape(String s) { return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;"); }
  public static void Properties_storeToXML(java.util.Properties p, java.io.OutputStream os, String comment, String encoding) throws java.io.IOException {
    java.io.Writer w = new java.io.OutputStreamWriter(os, encoding);
    w.write("<?xml version=\"1.0\" encoding=\"" + encoding + "\" standalone=\"no\"?>\n<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">\n<properties>\n");
    if (comment != null) w.write("<comment>" + xmlEscape(comment) + "</comment>\n");
    for (String k : new java.util.TreeSet<>(p.stringPropertyNames())) w.write("<entry key=\"" + xmlEscape(k) + "\">" + xmlEscape(p.getProperty(k)) + "</entry>\n");
    w.write("</properties>\n");
    w.flush();
  }
  public static void Properties_storeToXML(java.util.Properties p, java.io.OutputStream os, String comment) throws java.io.IOException { Properties_storeToXML(p, os, comment, "UTF-8"); }
  public static void Properties_storeToXML(java.util.Properties p, java.io.OutputStream os, String comment, java.nio.charset.Charset cs) throws java.io.IOException { Properties_storeToXML(p, os, comment, cs.name()); }
  public static void Properties_loadFromXML(java.util.Properties p, java.io.InputStream in) throws java.io.IOException {
    try {
      org.xmlpull.v1.XmlPullParser x = org.xmlpull.v1.XmlPullParserFactory.newInstance().newPullParser();
      x.setInput(in, null);
      boolean seenRoot = false;
      for (int ev = x.getEventType(); ev != org.xmlpull.v1.XmlPullParser.END_DOCUMENT; ev = x.next()) {
        if (ev != org.xmlpull.v1.XmlPullParser.START_TAG) continue;
        if (!seenRoot) { if (!x.getName().equals("properties")) throw new a2w.jdk.util.InvalidPropertiesFormatException("root element is not <properties>"); seenRoot = true; continue; }
        if (x.getName().equals("entry")) {
          String k = x.getAttributeValue(null, "key");
          if (k == null) throw new a2w.jdk.util.InvalidPropertiesFormatException("<entry> without key");
          p.setProperty(k, x.nextText());
        }
      }
      in.close();
    } catch (org.xmlpull.v1.XmlPullParserException e) { throw new a2w.jdk.util.InvalidPropertiesFormatException(e); }
  }

  // ---- java.nio.ByteBuffer
  public static java.nio.ByteBuffer ByteBuffer_slice(java.nio.ByteBuffer b, int index, int length) {
    java.util.Objects.checkFromIndexSize(index, length, b.limit());
    java.nio.ByteBuffer d = b.duplicate();
    d.limit(index + length); d.position(index);
    return d.slice();
  }
  public static int ByteBuffer_mismatch(java.nio.ByteBuffer a, java.nio.ByteBuffer b) {
    int n = Math.min(a.remaining(), b.remaining()), pa = a.position(), pb = b.position();
    for (int i = 0; i < n; i++) if (a.get(pa + i) != b.get(pb + i)) return i;
    return a.remaining() == b.remaining() ? -1 : n;
  }

  // ---- java.io
  public static boolean File_canExecute(java.io.File f) { return f.isDirectory(); }
  public static boolean File_setExecutable(java.io.File f, boolean executable) { return f.exists() && !executable == !f.isDirectory(); }
  public static boolean File_setExecutable(java.io.File f, boolean executable, boolean ownerOnly) { return File_setExecutable(f, executable); }
  public static boolean File_setReadable(java.io.File f, boolean readable) { return f.exists() && readable; }
  public static boolean File_setReadable(java.io.File f, boolean readable, boolean ownerOnly) { return File_setReadable(f, readable); }
  public static boolean File_setWritable(java.io.File f, boolean writable, boolean ownerOnly) { return f.setWritable(writable); }
  public static long File_getTotalSpace(java.io.File f) { return f.exists() ? 4096L << 20 : 0; }
  public static long File_getFreeSpace(java.io.File f) { return f.exists() ? 2048L << 20 : 0; }
  public static long File_getUsableSpace(java.io.File f) { return f.exists() ? 2048L << 20 : 0; }
  public static java.net.URL File_toURL(java.io.File f) throws java.net.MalformedURLException { return f.toURI().toURL(); }
  public static java.io.Reader Reader_nullReader() {
    return new java.io.Reader() {
      boolean closed;
      public int read(char[] b, int off, int len) throws java.io.IOException { if (closed) throw new java.io.IOException("Stream closed"); java.util.Objects.checkFromIndexSize(off, len, b.length); return len == 0 ? 0 : -1; }
      public void close() { closed = true; }
    };
  }
  public static long Reader_transferTo(java.io.Reader r, java.io.Writer w) throws java.io.IOException {
    java.util.Objects.requireNonNull(w);
    long n = 0; char[] b = new char[8192];
    for (int k; (k = r.read(b)) >= 0; ) { w.write(b, 0, k); n += k; }
    return n;
  }
  public static java.io.Writer Writer_nullWriter() {
    return new java.io.Writer() {
      boolean closed;
      void open() throws java.io.IOException { if (closed) throw new java.io.IOException("Stream closed"); }
      public void write(char[] b, int off, int len) throws java.io.IOException { java.util.Objects.checkFromIndexSize(off, len, b.length); open(); }
      public void flush() throws java.io.IOException { open(); }
      public void close() { closed = true; }
    };
  }
  public static void ByteArrayOutputStream_writeBytes(java.io.ByteArrayOutputStream o, byte[] b) { o.write(b, 0, b.length); }
  public static String ByteArrayOutputStream_toString(java.io.ByteArrayOutputStream o, int hibyte) {
    byte[] b = o.toByteArray(); char[] c = new char[b.length];
    for (int i = 0; i < b.length; i++) c[i] = (char) (((hibyte & 0xff) << 8) | (b[i] & 0xff));
    return new String(c);
  }
  public static void PrintStream_writeBytes(java.io.PrintStream p, byte[] b) { p.write(b, 0, b.length); }

  // ---- java.lang
  public static int CharSequence_compare(CharSequence a, CharSequence b) {
    if (java.util.Objects.requireNonNull(a) == java.util.Objects.requireNonNull(b)) return 0;
    int n = Math.min(a.length(), b.length());
    for (int i = 0; i < n; i++) { int d = a.charAt(i) - b.charAt(i); if (d != 0) return d; }
    return a.length() - b.length();
  }
  public static int StringBuilder_compareTo(StringBuilder a, StringBuilder b) { return CharSequence_compare(a, b); }
  public static int StringBuffer_compareTo(StringBuffer a, StringBuffer b) { return CharSequence_compare(a, b); }
  static a2w.jdk.lang.ThreadGroup mainGroup;
  public static a2w.jdk.lang.ThreadGroup Thread_getThreadGroup(Thread t) {
    if (!t.isAlive()) return null;
    if (mainGroup == null) mainGroup = new a2w.jdk.lang.ThreadGroup("main");
    return mainGroup;
  }
}

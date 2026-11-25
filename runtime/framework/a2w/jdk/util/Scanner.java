package a2w.jdk.util;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * java.util.Scanner: tokens separated by a delimiter pattern, typed values, lines and pattern searches. The source is
 * read to its end at the first operation that needs input (a stream that never ends blocks that operation). Numbers
 * accept the locale-independent forms and ',' grouping in radix 10.
 */
public final class Scanner implements Closeable, Iterator<String> {
  private static final Pattern WHITESPACE = compile("\\p{javaWhitespace}+", "[\\s\\u0085\\u00a0\\u1680\\u2000-\\u200a\\u2028\\u2029\\u202f\\u205f\\u3000]+");
  private static final Pattern LINE_SEPARATOR = Pattern.compile("\r\n|[\n\r\u2028\u2029\u0085]");
  private static final Pattern BOOLEAN = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);
  private static final Pattern GROUPED = Pattern.compile("[-+]?\\d{1,3}(,\\d{3})+(\\.\\d*)?([eE][-+]?\\d+)?");

  private static Pattern compile(String p, String fallback) {
    try { return Pattern.compile(p); } catch (RuntimeException e) { return Pattern.compile(fallback); }
  }

  private Readable source;
  private String buf;          // the whole input, once read
  private int pos;
  private Pattern delim = WHITESPACE;
  private Locale locale = Locale.getDefault();
  private int radix = 10;
  private boolean closed;
  private IOException lastException;
  private MatchResult last;

  public Scanner(Readable source) { this.source = Objects.requireNonNull(source, "source"); }
  public Scanner(String source) { this.buf = Objects.requireNonNull(source, "source"); }
  public Scanner(java.io.InputStream source) { this(new java.io.InputStreamReader(Objects.requireNonNull(source, "source"))); }
  public Scanner(java.io.InputStream source, String charsetName) { this(reader(source, charsetName)); }
  public Scanner(java.io.InputStream source, java.nio.charset.Charset charset) { this(new java.io.InputStreamReader(Objects.requireNonNull(source, "source"), Objects.requireNonNull(charset, "charset"))); }
  public Scanner(java.io.File source) throws java.io.FileNotFoundException { this(new java.io.FileInputStream(source)); }
  public Scanner(java.io.File source, String charsetName) throws java.io.FileNotFoundException { this(new java.io.FileInputStream(source), charsetName); }
  public Scanner(java.io.File source, java.nio.charset.Charset charset) throws IOException { this(new java.io.FileInputStream(source), charset); }
  public Scanner(java.nio.file.Path source) throws IOException { this(source.toFile()); }
  public Scanner(java.nio.file.Path source, String charsetName) throws IOException { this(source.toFile(), charsetName); }
  public Scanner(java.nio.file.Path source, java.nio.charset.Charset charset) throws IOException { this(source.toFile(), charset); }
  public Scanner(java.nio.channels.ReadableByteChannel source) { this(in(source)); }
  public Scanner(java.nio.channels.ReadableByteChannel source, String charsetName) { this(in(source), charsetName); }
  public Scanner(java.nio.channels.ReadableByteChannel source, java.nio.charset.Charset charset) { this(in(source), charset); }

  private static java.io.InputStream in(java.nio.channels.ReadableByteChannel ch) {
    Objects.requireNonNull(ch, "source");
    return new java.io.InputStream() {
      public int read() throws IOException { byte[] b = new byte[1]; int n; while ((n = read(b, 0, 1)) == 0) { } return n < 0 ? -1 : b[0] & 0xff; }
      public int read(byte[] b, int off, int len) throws IOException { return len == 0 ? 0 : ch.read(java.nio.ByteBuffer.wrap(b, off, len)); }
      public void close() throws IOException { ch.close(); }
    };
  }
  private static java.io.Reader reader(java.io.InputStream in, String charsetName) {
    Objects.requireNonNull(in, "source");
    try { return new java.io.InputStreamReader(in, Objects.requireNonNull(charsetName, "charsetName")); }
    catch (java.io.UnsupportedEncodingException e) { throw new IllegalArgumentException(e); }
  }

  private void open() { if (closed) throw new IllegalStateException("Scanner closed"); }
  private String input() {
    open();
    if (buf == null) {
      StringBuilder b = new StringBuilder();
      CharBuffer cb = CharBuffer.allocate(4096);
      try {
        while (source.read(cb) >= 0) { cb.flip(); b.append(cb); cb.clear(); }
      } catch (IOException e) { lastException = e; }
      buf = b.toString();
    }
    return buf;
  }

  /** the position after the delimiters at pos */
  private int tokenStart() {
    String s = input();
    Matcher m = delim.matcher(s);
    m.region(pos, s.length());
    return m.lookingAt() ? m.end() : pos;
  }
  /** [start, end) of the next token, or null */
  private int[] token() {
    String s = input();
    int st = tokenStart();
    if (st >= s.length()) return null;
    Matcher m = delim.matcher(s);
    m.region(st, s.length());
    int en = s.length();
    while (m.find()) { if (m.end() > m.start() || m.start() > st) { en = m.start(); break; } }
    if (en == st) return null;
    return new int[] {st, en};
  }
  private String peek() { int[] t = token(); return t == null ? null : buf.substring(t[0], t[1]); }
  private void consume(int[] t) {
    pos = t[1];
    Matcher m = Pattern.compile(".*", Pattern.DOTALL).matcher(buf);
    m.region(t[0], t[1]);
    m.matches();
    last = m.toMatchResult();
  }

  public boolean hasNext() { return token() != null; }
  public String next() {
    int[] t = token();
    if (t == null) throw new NoSuchElementException();
    consume(t);
    return buf.substring(t[0], t[1]);
  }
  public boolean hasNext(String pattern) { return hasNext(Pattern.compile(pattern)); }
  public boolean hasNext(Pattern pattern) { String t = peek(); return t != null && pattern.matcher(t).matches(); }
  public String next(String pattern) { return next(Pattern.compile(pattern)); }
  public String next(Pattern pattern) {
    int[] t = token();
    if (t == null) throw new NoSuchElementException();
    Matcher m = pattern.matcher(buf);
    m.region(t[0], t[1]);
    if (!m.matches()) throw new InputMismatchException(buf.substring(t[0], t[1]));
    pos = t[1];
    last = m.toMatchResult();
    return buf.substring(t[0], t[1]);
  }

  private static String ungroup(String t, int radix) { return radix == 10 && GROUPED.matcher(t).matches() ? t.replace(",", "") : t; }
  private static String plus(String t) { return t.startsWith("+") ? t.substring(1) : t; }
  private <T> T typed(int radix, java.util.function.Function<String, T> parse, boolean consume) {
    int[] t = token();
    if (t == null) throw new NoSuchElementException();
    String s = buf.substring(t[0], t[1]);
    T v;
    try { v = parse.apply(plus(ungroup(s, radix))); }
    catch (NumberFormatException e) { throw new InputMismatchException(e.getMessage() != null ? e.getMessage() : s); }
    if (v == null) throw new InputMismatchException(s);
    if (consume) consume(t);
    return v;
  }
  private boolean can(int radix, java.util.function.Function<String, ?> parse) {
    try { typed(radix, parse, false); return true; } catch (NoSuchElementException e) { return false; }
  }
  private static void checkRadix(int radix) { if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) throw new IllegalArgumentException("radix:" + radix); }

  public boolean hasNextBoolean() { return can(10, Scanner::bool); }
  public boolean nextBoolean() { return typed(10, Scanner::bool, true); }
  private static Boolean bool(String s) { return BOOLEAN.matcher(s).matches() ? Boolean.valueOf(s.equalsIgnoreCase("true")) : null; }
  public boolean hasNextByte() { return hasNextByte(radix); }
  public boolean hasNextByte(int radix) { checkRadix(radix); return can(radix, s -> Byte.parseByte(s, radix)); }
  public byte nextByte() { return nextByte(radix); }
  public byte nextByte(int radix) { checkRadix(radix); return typed(radix, s -> Byte.parseByte(s, radix), true); }
  public boolean hasNextShort() { return hasNextShort(radix); }
  public boolean hasNextShort(int radix) { checkRadix(radix); return can(radix, s -> Short.parseShort(s, radix)); }
  public short nextShort() { return nextShort(radix); }
  public short nextShort(int radix) { checkRadix(radix); return typed(radix, s -> Short.parseShort(s, radix), true); }
  public boolean hasNextInt() { return hasNextInt(radix); }
  public boolean hasNextInt(int radix) { checkRadix(radix); return can(radix, s -> Integer.parseInt(s, radix)); }
  public int nextInt() { return nextInt(radix); }
  public int nextInt(int radix) { checkRadix(radix); return typed(radix, s -> Integer.parseInt(s, radix), true); }
  public boolean hasNextLong() { return hasNextLong(radix); }
  public boolean hasNextLong(int radix) { checkRadix(radix); return can(radix, s -> Long.parseLong(s, radix)); }
  public long nextLong() { return nextLong(radix); }
  public long nextLong(int radix) { checkRadix(radix); return typed(radix, s -> Long.parseLong(s, radix), true); }
  public boolean hasNextBigInteger() { return hasNextBigInteger(radix); }
  public boolean hasNextBigInteger(int radix) { checkRadix(radix); return can(radix, s -> new BigInteger(s, radix)); }
  public BigInteger nextBigInteger() { return nextBigInteger(radix); }
  public BigInteger nextBigInteger(int radix) { checkRadix(radix); return typed(radix, s -> new BigInteger(s, radix), true); }
  public boolean hasNextFloat() { return can(10, Scanner::decimal); }
  public float nextFloat() { return (float) (double) typed(10, Scanner::decimal, true); }
  public boolean hasNextDouble() { return can(10, Scanner::decimal); }
  public double nextDouble() { return typed(10, Scanner::decimal, true); }
  public boolean hasNextBigDecimal() { return can(10, s -> new BigDecimal(s)); }
  public BigDecimal nextBigDecimal() { return typed(10, s -> new BigDecimal(s), true); }
  /** a decimal, hexadecimal floating point, NaN or Infinity (not Java's type suffixes) */
  private static Double decimal(String s) {
    if (s.isEmpty()) return null;
    char c = s.charAt(s.length() - 1);
    if (c == 'd' || c == 'D' || c == 'f' || c == 'F') { if (!s.matches("(?i)[-+]?0x.*p[-+]?\\d+[df]")) return null; }
    return Double.parseDouble(s);
  }

  public boolean hasNextLine() { open(); return pos < input().length(); }
  public String nextLine() {
    String s = input();
    if (pos >= s.length()) throw new NoSuchElementException("No line found");
    Matcher m = LINE_SEPARATOR.matcher(s);
    m.region(pos, s.length());
    int st = pos, en, next;
    if (m.find()) { en = m.start(); next = m.end(); } else { en = next = s.length(); }
    Matcher r = Pattern.compile(".*", Pattern.DOTALL).matcher(s);
    r.region(st, next);
    r.matches();
    last = r.toMatchResult();
    pos = next;
    return s.substring(st, en);
  }

  public String findInLine(String pattern) { return findInLine(Pattern.compile(pattern)); }
  public String findInLine(Pattern pattern) {
    String s = input();
    Matcher l = LINE_SEPARATOR.matcher(s);
    l.region(pos, s.length());
    int end = l.find() ? l.start() : s.length();
    return find(pattern, end);
  }
  public String findWithinHorizon(String pattern, int horizon) { return findWithinHorizon(Pattern.compile(pattern), horizon); }
  public String findWithinHorizon(Pattern pattern, int horizon) {
    if (horizon < 0) throw new IllegalArgumentException("horizon < 0");
    String s = input();
    return find(pattern, horizon == 0 ? s.length() : (int) Math.min(s.length(), (long) pos + horizon));
  }
  private String find(Pattern pattern, int end) {
    Objects.requireNonNull(pattern);
    Matcher m = pattern.matcher(buf);
    m.region(pos, end);
    if (!m.find()) return null;
    last = m.toMatchResult();
    pos = m.end();
    return m.group();
  }
  public Scanner skip(String pattern) { return skip(Pattern.compile(pattern)); }
  public Scanner skip(Pattern pattern) {
    String s = input();
    Matcher m = pattern.matcher(s);
    m.region(pos, s.length());
    if (!m.lookingAt()) throw new NoSuchElementException();
    last = m.toMatchResult();
    pos = m.end();
    return this;
  }
  public MatchResult match() { if (last == null) throw new IllegalStateException("No match result available"); return last; }

  public Stream<String> tokens() { open(); return StreamSupport.stream(java.util.Spliterators.spliteratorUnknownSize(this, java.util.Spliterator.ORDERED | java.util.Spliterator.NONNULL), false); }
  public Stream<MatchResult> findAll(String patString) { return findAll(Pattern.compile(patString)); }
  public Stream<MatchResult> findAll(Pattern pattern) {
    Objects.requireNonNull(pattern);
    open();
    Iterator<MatchResult> it = new Iterator<MatchResult>() {
      MatchResult next; boolean done;
      public boolean hasNext() {
        if (next == null && !done) {
          if (findWithinHorizon(pattern, 0) != null) { next = last; if (next.end() == next.start()) pos = Math.min(buf.length(), pos + 1); }
          else done = true;
        }
        return next != null;
      }
      public MatchResult next() { if (!hasNext()) throw new NoSuchElementException(); MatchResult r = next; next = null; return r; }
    };
    return StreamSupport.stream(java.util.Spliterators.spliteratorUnknownSize(it, java.util.Spliterator.ORDERED | java.util.Spliterator.NONNULL), false);
  }

  public Pattern delimiter() { return delim; }
  public Scanner useDelimiter(String pattern) { delim = Pattern.compile(pattern); return this; }
  public Scanner useDelimiter(Pattern pattern) { delim = Objects.requireNonNull(pattern); return this; }
  public Locale locale() { return locale; }
  public Scanner useLocale(Locale locale) { this.locale = Objects.requireNonNull(locale); return this; }
  public int radix() { return radix; }
  public Scanner useRadix(int radix) { checkRadix(radix); this.radix = radix; return this; }
  public Scanner reset() { delim = WHITESPACE; locale = Locale.getDefault(); radix = 10; return this; }
  public IOException ioException() { return lastException; }
  public void remove() { throw new UnsupportedOperationException(); }
  public void close() {
    if (closed) return;
    closed = true;
    if (source instanceof Closeable) { try { ((Closeable) source).close(); } catch (IOException e) { lastException = e; } }
  }
  public String toString() {
    return "java.util.Scanner[delimiters=" + delim + "][position=" + pos + "][match valid=" + (last != null) + "][need input=false][source closed=" + closed
        + "][skipped=false][group separator=\\,][decimal separator=\\.][positive prefix=][negative prefix=\\Q-\\E][positive suffix=][negative suffix=][NaN string=\\QNaN\\E][infinity string=\\Q\u221e\\E]";
  }
}

package android.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/** android.util.JsonReader: a streaming reader of RFC 4627 JSON (lenient mode accepts what Android's does). */
public final class JsonReader implements java.io.Closeable {
  private final Reader in;
  private boolean lenient;
  private final char[] buf = new char[1024];
  private int pos, limit;
  private final ArrayList<Integer> stack = new ArrayList<>();   // 1 empty array, 2 nonempty array, 3 empty object, 4 dangling name, 5 nonempty object, 6 empty doc, 7 nonempty doc
  private JsonToken token;
  private String name, value;
  private boolean skipping;
  public JsonReader(Reader in) { if (in == null) throw new NullPointerException("in == null"); this.in = in; stack.add(6); }
  public void setLenient(boolean l) { lenient = l; }
  public boolean isLenient() { return lenient; }
  private int top() { return stack.get(stack.size() - 1); }
  private void setTop(int v) { stack.set(stack.size() - 1, v); }
  public void beginArray() throws IOException { expect(JsonToken.BEGIN_ARRAY); }
  public void endArray() throws IOException { expect(JsonToken.END_ARRAY); }
  public void beginObject() throws IOException { expect(JsonToken.BEGIN_OBJECT); }
  public void endObject() throws IOException { expect(JsonToken.END_OBJECT); }
  private void expect(JsonToken t) throws IOException {
    peek();
    if (token != t) throw new IllegalStateException("Expected " + t + " but was " + peek());
    advance();
  }
  public boolean hasNext() throws IOException { peek(); return token != JsonToken.END_OBJECT && token != JsonToken.END_ARRAY && token != JsonToken.END_DOCUMENT; }
  public JsonToken peek() throws IOException {
    if (token != null) return token;
    switch (top()) {
      case 6: if (lenient) consumeNonExecutePrefix(); setTop(7); JsonToken first = nextValue(); if (!lenient && token != JsonToken.BEGIN_ARRAY && token != JsonToken.BEGIN_OBJECT) throw syntax("Expected JSON document to start with '[' or '{'"); return first;
      case 1: return nextInArray(true);
      case 2: return nextInArray(false);
      case 3: return nextInObject(true);
      case 4: return objectValue();
      case 5: return nextInObject(false);
      case 7: {
        int c = nextNonWhitespace(false);
        if (c == -1) return token = JsonToken.END_DOCUMENT;
        checkLenient(); pos--; return nextValue();
      }
      default: throw new IllegalStateException();
    }
  }
  private void consumeNonExecutePrefix() throws IOException {
    nextNonWhitespace(true); pos--;
    if (pos + 5 > limit && !fill(5)) return;
    if (buf[pos] != ')' || buf[pos + 1] != ']' || buf[pos + 2] != '}' || buf[pos + 3] != '\'' || buf[pos + 4] != '\n') return;
    pos += 5;
  }
  private JsonToken advance() throws IOException { peek(); JsonToken r = token; token = null; value = null; name = null; return r; }
  public String nextName() throws IOException {
    peek();
    if (token != JsonToken.NAME) throw new IllegalStateException("Expected a name but was " + peek());
    String r = name; advance(); return r;
  }
  public String nextString() throws IOException {
    peek();
    if (token != JsonToken.STRING && token != JsonToken.NUMBER) throw new IllegalStateException("Expected a string but was " + peek());
    String r = value; advance(); return r;
  }
  public boolean nextBoolean() throws IOException {
    peek();
    if (token != JsonToken.BOOLEAN) throw new IllegalStateException("Expected a boolean but was " + token);
    boolean r = "true".equals(value); advance(); return r;
  }
  public void nextNull() throws IOException {
    peek();
    if (token != JsonToken.NULL) throw new IllegalStateException("Expected null but was " + token);
    advance();
  }
  public double nextDouble() throws IOException {
    peek();
    if (token != JsonToken.STRING && token != JsonToken.NUMBER) throw new IllegalStateException("Expected a double but was " + token);
    double r = Double.parseDouble(value); advance(); return r;
  }
  public long nextLong() throws IOException {
    peek();
    if (token != JsonToken.STRING && token != JsonToken.NUMBER) throw new IllegalStateException("Expected a long but was " + token);
    long r;
    try { r = Long.parseLong(value); }
    catch (NumberFormatException e) { double d = Double.parseDouble(value); r = (long) d; if (r != d) throw new NumberFormatException(value); }
    advance(); return r;
  }
  public int nextInt() throws IOException {
    peek();
    if (token != JsonToken.STRING && token != JsonToken.NUMBER) throw new IllegalStateException("Expected an int but was " + token);
    int r;
    try { r = Integer.parseInt(value); }
    catch (NumberFormatException e) { double d = Double.parseDouble(value); r = (int) d; if (r != d) throw new NumberFormatException(value); }
    advance(); return r;
  }
  @Override public void close() throws IOException { value = null; token = null; stack.clear(); stack.add(8); in.close(); }
  public void skipValue() throws IOException {
    skipping = true;
    try {
      int count = 0;
      do {
        JsonToken t = advance();
        if (t == JsonToken.BEGIN_ARRAY || t == JsonToken.BEGIN_OBJECT) count++;
        else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) count--;
      } while (count != 0);
    } finally { skipping = false; }
  }
  private JsonToken nextInArray(boolean first) throws IOException {
    if (first) setTop(2);
    else {
      int c = nextNonWhitespace(true);
      if (c == ']') { stack.remove(stack.size() - 1); return token = JsonToken.END_ARRAY; }
      if (c == ';') checkLenient();
      else if (c != ',') throw syntax("Unterminated array");
    }
    int c = nextNonWhitespace(true);
    if (c == ']') { if (first) { stack.remove(stack.size() - 1); return token = JsonToken.END_ARRAY; } }
    if (c == ']' || c == ';' || c == ',') { checkLenient(); pos--; value = "null"; return token = JsonToken.NULL; }
    pos--;
    return nextValue();
  }
  private JsonToken nextInObject(boolean first) throws IOException {
    if (first) {
      int c = nextNonWhitespace(true);
      if (c == '}') { stack.remove(stack.size() - 1); return token = JsonToken.END_OBJECT; }
      pos--;
    } else {
      int c = nextNonWhitespace(true);
      if (c == '}') { stack.remove(stack.size() - 1); return token = JsonToken.END_OBJECT; }
      if (c != ';' && c != ',') throw syntax("Unterminated object");
    }
    int q = nextNonWhitespace(true);
    if (q == '\'') { checkLenient(); name = nextQuoted('\''); }
    else if (q == '"') name = nextQuoted('"');
    else { checkLenient(); pos--; name = nextLiteral(false); if (name.isEmpty()) throw syntax("Expected name"); }
    setTop(4);
    return token = JsonToken.NAME;
  }
  private JsonToken objectValue() throws IOException {
    int c = nextNonWhitespace(true);
    if (c == ':') { /* ok */ }
    else if (c == '=') { checkLenient(); if ((pos < limit || fill(1)) && buf[pos] == '>') pos++; }
    else throw syntax("Expected ':'");
    setTop(5);
    return nextValue();
  }
  private JsonToken nextValue() throws IOException {
    int c = nextNonWhitespace(true);
    switch (c) {
      case '{': stack.add(3); return token = JsonToken.BEGIN_OBJECT;
      case '[': stack.add(1); return token = JsonToken.BEGIN_ARRAY;
      case '\'': checkLenient(); value = nextQuoted('\''); return token = JsonToken.STRING;
      case '"': value = nextQuoted('"'); return token = JsonToken.STRING;
      default: pos--; return readLiteral();
    }
  }
  private JsonToken readLiteral() throws IOException {
    value = nextLiteral(true);
    if (value.isEmpty()) throw syntax("Expected literal value");
    if (value.equals("null") || lenient && value.equalsIgnoreCase("null")) return token = JsonToken.NULL;
    if (value.equals("true") || value.equals("false") || lenient && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))) { value = value.toLowerCase(java.util.Locale.ROOT); return token = JsonToken.BOOLEAN; }
    if (isNumber(value)) return token = JsonToken.NUMBER;
    checkLenient();
    return token = JsonToken.STRING;
  }
  private static boolean isNumber(String s) {
    int i = 0, n = s.length();
    if (i < n && s.charAt(i) == '-') i++;
    if (i >= n || !Character.isDigit(s.charAt(i))) return false;
    if (s.charAt(i) == '0' && i + 1 < n && Character.isDigit(s.charAt(i + 1))) return false;
    while (i < n && Character.isDigit(s.charAt(i))) i++;
    if (i < n && s.charAt(i) == '.') { i++; if (i >= n || !Character.isDigit(s.charAt(i))) return false; while (i < n && Character.isDigit(s.charAt(i))) i++; }
    if (i < n && (s.charAt(i) == 'e' || s.charAt(i) == 'E')) { i++; if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++; if (i >= n || !Character.isDigit(s.charAt(i))) return false; while (i < n && Character.isDigit(s.charAt(i))) i++; }
    return i == n;
  }
  private boolean fill(int min) throws IOException {
    if (limit != pos) { limit -= pos; System.arraycopy(buf, pos, buf, 0, limit); } else limit = 0;
    pos = 0;
    int t;
    while ((t = in.read(buf, limit, buf.length - limit)) != -1) { limit += t; if (limit >= min) return true; }
    return false;
  }
  private int nextNonWhitespace(boolean throwOnEof) throws IOException {
    while (pos < limit || fill(1)) {
      int c = buf[pos++];
      switch (c) {
        case '\t': case ' ': case '\n': case '\r': continue;
        case '/':
          if (pos == limit && !fill(1)) return c;
          checkLenient();
          char p = buf[pos];
          if (p == '*') { pos++; if (!skipTo("*/")) throw syntax("Unterminated comment"); pos += 2; continue; }
          if (p == '/') { pos++; skipToEndOfLine(); continue; }
          return c;
        case '#': checkLenient(); skipToEndOfLine(); continue;
        default: return c;
      }
    }
    if (throwOnEof) throw new java.io.EOFException("End of input");
    return -1;
  }
  private void checkLenient() throws IOException { if (!lenient) throw syntax("Use JsonReader.setLenient(true) to accept malformed JSON"); }
  private void skipToEndOfLine() throws IOException { while (pos < limit || fill(1)) { char c = buf[pos++]; if (c == '\r' || c == '\n') break; } }
  private boolean skipTo(String toFind) throws IOException {
    outer:
    for (; pos + toFind.length() <= limit || fill(toFind.length()); pos++) {
      for (int c = 0; c < toFind.length(); c++) if (buf[pos + c] != toFind.charAt(c)) continue outer;
      return true;
    }
    return false;
  }
  private String nextQuoted(char quote) throws IOException {
    StringBuilder b = new StringBuilder();
    while (true) {
      if (pos >= limit && !fill(1)) throw syntax("Unterminated string");
      char c = buf[pos++];
      if (c == quote) return b.toString();
      if (c == '\\') {
        if (pos >= limit && !fill(1)) throw syntax("Unterminated escape sequence");
        char e = buf[pos++];
        switch (e) {
          case 'u': {
            if (pos + 4 > limit && !fill(4)) throw syntax("Unterminated escape sequence");
            b.append((char) Integer.parseInt(new String(buf, pos, 4), 16)); pos += 4; break;
          }
          case 't': b.append('\t'); break;
          case 'b': b.append('\b'); break;
          case 'n': b.append('\n'); break;
          case 'r': b.append('\r'); break;
          case 'f': b.append('\f'); break;
          default: b.append(e);
        }
      } else b.append(c);
    }
  }
  private String nextLiteral(boolean allowColonsAndSpaces) throws IOException {
    StringBuilder b = new StringBuilder();
    while (pos < limit || fill(1)) {
      char c = buf[pos];
      switch (c) {
        case '/': case '\\': case ';': case '#': case '=': checkLenient();
        // fall through
        case '{': case '}': case '[': case ']': case ':': case ',': case ' ': case '\t': case '\f': case '\r': case '\n':
          return b.toString();
        default: b.append(c); pos++;
      }
    }
    return b.toString();
  }
  private IOException syntax(String m) { return new MalformedJsonException(m + " at position " + pos); }
  @Override public String toString() { return getClass().getSimpleName() + " near " + new String(buf, pos, Math.min(limit - pos, 20)); }
}

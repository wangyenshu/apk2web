package a2w.jdk.util;

/** java.util.StringJoiner. */
public final class StringJoiner {
  private final String prefix, delimiter, suffix;
  private StringBuilder value;
  private String emptyValue;
  public StringJoiner(CharSequence delimiter) { this(delimiter, "", ""); }
  public StringJoiner(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
    java.util.Objects.requireNonNull(prefix, "The prefix must not be null");
    java.util.Objects.requireNonNull(delimiter, "The delimiter must not be null");
    java.util.Objects.requireNonNull(suffix, "The suffix must not be null");
    this.prefix = prefix.toString(); this.delimiter = delimiter.toString(); this.suffix = suffix.toString();
  }
  public StringJoiner setEmptyValue(CharSequence emptyValue) {
    this.emptyValue = java.util.Objects.requireNonNull(emptyValue, "The empty value must not be null").toString();
    return this;
  }
  public StringJoiner add(CharSequence newElement) {
    if (value == null) value = new StringBuilder(); else value.append(delimiter);
    value.append(newElement);
    return this;
  }
  public StringJoiner merge(StringJoiner other) {
    java.util.Objects.requireNonNull(other);
    if (other.value != null) {
      String o = other.value.toString();   // (other may be this)
      if (value == null) value = new StringBuilder(); else value.append(delimiter);
      value.append(o);
    }
    return this;
  }
  public int length() { return value == null ? (emptyValue != null ? emptyValue.length() : prefix.length() + suffix.length()) : prefix.length() + value.length() + suffix.length(); }
  public String toString() { return value == null ? (emptyValue != null ? emptyValue : prefix + suffix) : prefix + value + suffix; }
}

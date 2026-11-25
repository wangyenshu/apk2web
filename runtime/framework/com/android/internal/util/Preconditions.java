package com.android.internal.util;

import java.util.Collection;
import java.util.Objects;

/** The argument checks AOSP's framework code uses (com.android.internal.util.Preconditions, not public API). */
public final class Preconditions {
  private Preconditions() {}
  public static void checkArgument(boolean expression) { if (!expression) throw new IllegalArgumentException(); }
  public static void checkArgument(boolean expression, Object errorMessage) { if (!expression) throw new IllegalArgumentException(String.valueOf(errorMessage)); }
  public static void checkArgument(boolean expression, String messageTemplate, Object... args) { if (!expression) throw new IllegalArgumentException(String.format(messageTemplate, args)); }
  public static <T extends CharSequence> T checkStringNotEmpty(T s) { if (s == null || s.length() == 0) throw new IllegalArgumentException(); return s; }
  public static <T extends CharSequence> T checkStringNotEmpty(T s, Object errorMessage) { if (s == null || s.length() == 0) throw new IllegalArgumentException(String.valueOf(errorMessage)); return s; }
  public static <T> T checkNotNull(T reference) { if (reference == null) throw new NullPointerException(); return reference; }
  public static <T> T checkNotNull(T reference, Object errorMessage) { if (reference == null) throw new NullPointerException(String.valueOf(errorMessage)); return reference; }
  public static <T> T checkNotNull(T reference, String messageTemplate, Object... args) { if (reference == null) throw new NullPointerException(String.format(messageTemplate, args)); return reference; }
  public static void checkState(boolean expression) { if (!expression) throw new IllegalStateException(); }
  public static void checkState(boolean expression, String errorMessage) { if (!expression) throw new IllegalStateException(errorMessage); }
  public static void checkState(boolean expression, String messageTemplate, Object... args) { if (!expression) throw new IllegalStateException(String.format(messageTemplate, args)); }
  public static int checkFlagsArgument(int requestedFlags, int allowedFlags) {
    if ((requestedFlags & allowedFlags) != requestedFlags) throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(requestedFlags) + ", but only 0x" + Integer.toHexString(allowedFlags) + " are allowed");
    return requestedFlags;
  }
  public static int checkArgumentNonnegative(int value, String errorMessage) { if (value < 0) throw new IllegalArgumentException(errorMessage); return value; }
  public static int checkArgumentNonnegative(int value) { if (value < 0) throw new IllegalArgumentException(); return value; }
  public static long checkArgumentNonnegative(long value) { if (value < 0) throw new IllegalArgumentException(); return value; }
  public static long checkArgumentNonnegative(long value, String errorMessage) { if (value < 0) throw new IllegalArgumentException(errorMessage); return value; }
  public static int checkArgumentPositive(int value, String errorMessage) { if (value <= 0) throw new IllegalArgumentException(errorMessage); return value; }
  public static long checkArgumentPositive(long value, String errorMessage) { if (value <= 0) throw new IllegalArgumentException(errorMessage); return value; }
  public static float checkArgumentFinite(float value, String valueName) {
    if (Float.isNaN(value)) throw new IllegalArgumentException(valueName + " must not be NaN");
    if (Float.isInfinite(value)) throw new IllegalArgumentException(valueName + " must not be infinite");
    return value;
  }
  public static float checkArgumentInRange(float value, float lower, float upper, String valueName) {
    if (Float.isNaN(value)) throw new IllegalArgumentException(valueName + " must not be NaN");
    if (value < lower || value > upper) throw new IllegalArgumentException(String.format("%s is out of range of [%f, %f] (too %s)", valueName, lower, upper, value < lower ? "low" : "high"));
    return value;
  }
  public static double checkArgumentInRange(double value, double lower, double upper, String valueName) {
    if (Double.isNaN(value)) throw new IllegalArgumentException(valueName + " must not be NaN");
    if (value < lower || value > upper) throw new IllegalArgumentException(String.format("%s is out of range of [%f, %f] (too %s)", valueName, lower, upper, value < lower ? "low" : "high"));
    return value;
  }
  public static int checkArgumentInRange(int value, int lower, int upper, String valueName) {
    if (value < lower || value > upper) throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too %s)", valueName, lower, upper, value < lower ? "low" : "high"));
    return value;
  }
  public static long checkArgumentInRange(long value, long lower, long upper, String valueName) {
    if (value < lower || value > upper) throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too %s)", valueName, lower, upper, value < lower ? "low" : "high"));
    return value;
  }
  public static <T> T[] checkArrayElementsNotNull(T[] value, String valueName) {
    if (value == null) throw new NullPointerException(valueName + " must not be null");
    for (int i = 0; i < value.length; i++) if (value[i] == null) throw new NullPointerException(String.format("%s[%d] must not be null", valueName, i));
    return value;
  }
  public static <C extends Collection<T>, T> C checkCollectionElementsNotNull(C value, String valueName) {
    if (value == null) throw new NullPointerException(valueName + " must not be null");
    long i = 0;
    for (T e : value) { if (e == null) throw new NullPointerException(String.format("%s[%d] must not be null", valueName, i)); i++; }
    return value;
  }
  public static <T> Collection<T> checkCollectionNotEmpty(Collection<T> value, String valueName) {
    if (value == null) throw new NullPointerException(valueName + " must not be null");
    if (value.isEmpty()) throw new IllegalArgumentException(valueName + " is empty");
    return value;
  }
  public static float[] checkArrayElementsInRange(float[] value, float lower, float upper, String valueName) {
    Objects.requireNonNull(value, valueName + " must not be null");
    for (int i = 0; i < value.length; i++) checkArgumentInRange(value[i], lower, upper, valueName + "[" + i + "]");
    return value;
  }
}

package android.text.style;

import android.text.TextPaint;

public class SuggestionSpan extends CharacterStyle implements android.text.ParcelableSpan {
  public static final int FLAG_EASY_CORRECT = 1, FLAG_MISSPELLED = 2, FLAG_AUTO_CORRECTION = 4, FLAG_GRAMMAR_ERROR = 8, SUGGESTIONS_MAX_SIZE = 5;
  public static final String ACTION_SUGGESTION_PICKED = "android.text.style.SUGGESTION_PICKED";
  private final String[] suggestions;
  private int flags;
  public SuggestionSpan(android.content.Context c, String[] suggestions, int flags) { this.suggestions = suggestions; this.flags = flags; }
  public SuggestionSpan(java.util.Locale l, String[] suggestions, int flags) { this.suggestions = suggestions; this.flags = flags; }
  public String[] getSuggestions() { return suggestions; }
  public int getFlags() { return flags; }
  public void setFlags(int f) { flags = f; }
  public java.util.Locale getLocaleObject() { return null; }
  @Override public void updateDrawState(TextPaint tp) {}
  @Override public int getSpanTypeId() { return 19; }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeStringArray(suggestions); dest.writeInt(this.flags); }
}

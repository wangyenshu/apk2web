package android.view.inputmethod;

/** Attributes an input method attaches to committed or composing text: conversion suggestions and extras. */
public final class TextAttribute implements android.os.Parcelable {
  private final java.util.List<String> mSuggestions;
  private final android.os.PersistableBundle mExtras;
  TextAttribute(java.util.List<String> suggestions, android.os.PersistableBundle extras) { mSuggestions = suggestions; mExtras = extras; }
  public java.util.List<String> getTextConversionSuggestions() { return mSuggestions; }
  public android.os.PersistableBundle getExtras() { return mExtras; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeStringList(mSuggestions); dest.writePersistableBundle(mExtras); }
  public static final android.os.Parcelable.Creator<TextAttribute> CREATOR = new Creator();
  static final class Creator implements android.os.Parcelable.Creator<TextAttribute> {
    public TextAttribute createFromParcel(android.os.Parcel in) {
      java.util.ArrayList<String> l = new java.util.ArrayList<>(); in.readStringList(l);
      return new TextAttribute(l, in.readPersistableBundle());
    }
    public TextAttribute[] newArray(int size) { return new TextAttribute[size]; }
  }
  public static final class Builder {
    private java.util.List<String> mSuggestions = new java.util.ArrayList<>();
    private android.os.PersistableBundle mExtras = new android.os.PersistableBundle();
    public Builder() {}
    public Builder setTextConversionSuggestions(java.util.List<String> textConversionSuggestions) { mSuggestions = java.util.Collections.unmodifiableList(textConversionSuggestions); return this; }
    public Builder setExtras(android.os.PersistableBundle extras) { mExtras = extras; return this; }
    public TextAttribute build() { return new TextAttribute(mSuggestions, mExtras); }
  }
}

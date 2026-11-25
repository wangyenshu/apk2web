package android.view.autofill;

import android.os.Parcel;
import android.text.TextUtils;

/** A value for autofill: text, a toggle, a list index or a date (ms). */
public final class AutofillValue implements android.os.Parcelable {
  private static final int TEXT = 1, TOGGLE = 2, LIST = 3, DATE = 4;
  private final int mType;
  private final Object mValue;
  private AutofillValue(int type, Object value) { mType = type; mValue = value; }
  public static AutofillValue forText(CharSequence value) { return value == null ? null : new AutofillValue(TEXT, TextUtils.stringOrSpannedString(value)); }
  public static AutofillValue forToggle(boolean value) { return new AutofillValue(TOGGLE, value); }
  public static AutofillValue forList(int value) { return new AutofillValue(LIST, value); }
  public static AutofillValue forDate(long value) { return new AutofillValue(DATE, value); }
  public boolean isText() { return mType == TEXT; }
  public boolean isToggle() { return mType == TOGGLE; }
  public boolean isList() { return mType == LIST; }
  public boolean isDate() { return mType == DATE; }
  public CharSequence getTextValue() { if (!isText()) throw new IllegalStateException("value must be a text value, not type=" + mType); return (CharSequence) mValue; }
  public boolean getToggleValue() { if (!isToggle()) throw new IllegalStateException("value must be a toggle value, not type=" + mType); return (Boolean) mValue; }
  public int getListValue() { if (!isList()) throw new IllegalStateException("value must be a list value, not type=" + mType); return (Integer) mValue; }
  public long getDateValue() { if (!isDate()) throw new IllegalStateException("value must be a date value, not type=" + mType); return (Long) mValue; }
  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AutofillValue)) return false;
    AutofillValue v = (AutofillValue) o;
    if (mType != v.mType) return false;
    return isText() ? mValue.toString().equals(v.mValue.toString()) : mValue.equals(v.mValue);
  }
  @Override public int hashCode() { return mType + (isText() ? mValue.toString().hashCode() : mValue.hashCode()); }
  @Override public String toString() {
    String v = isText() ? "length=" + ((CharSequence) mValue).length() : String.valueOf(mValue);
    return "[type=" + mType + ", value=" + v + "]";
  }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel p, int flags) {
    p.writeInt(mType);
    switch (mType) {
      case TEXT: TextUtils.writeToParcel((CharSequence) mValue, p, flags); break;
      case TOGGLE: p.writeInt((Boolean) mValue ? 1 : 0); break;
      case LIST: p.writeInt((Integer) mValue); break;
      case DATE: p.writeLong((Long) mValue); break;
    }
  }
  public static final android.os.Parcelable.Creator<AutofillValue> CREATOR = new android.os.Parcelable.Creator<AutofillValue>() {
    public AutofillValue createFromParcel(Parcel p) {
      int type = p.readInt();
      switch (type) {
        case TEXT: return new AutofillValue(TEXT, TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(p));
        case TOGGLE: return new AutofillValue(TOGGLE, p.readInt() != 0);
        case LIST: return new AutofillValue(LIST, p.readInt());
        case DATE: return new AutofillValue(DATE, p.readLong());
        default: throw new IllegalArgumentException("type=" + type + " not valid");
      }
    }
    public AutofillValue[] newArray(int size) { return new AutofillValue[size]; }
  };
}

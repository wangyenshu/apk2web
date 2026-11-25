package android.text.style;

import android.text.Layout;

public interface AlignmentSpan extends ParagraphStyle {
  Layout.Alignment getAlignment();
  class Standard implements AlignmentSpan, android.text.ParcelableSpan {
    private final Layout.Alignment alignment;
    public Standard(Layout.Alignment align) { alignment = align; }
    public Standard(android.os.Parcel src) { alignment = Layout.Alignment.valueOf(src.readString()); }
    @Override public Layout.Alignment getAlignment() { return alignment; }
    @Override public int getSpanTypeId() { return 1; }
    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeString(alignment.name()); }
  }
}

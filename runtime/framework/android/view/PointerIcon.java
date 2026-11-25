package android.view;

import android.content.Context;

/** A mouse pointer shape (the page keeps its own cursor). */
public final class PointerIcon implements android.os.Parcelable {
  public static final int TYPE_NULL = 0, TYPE_ARROW = 1000, TYPE_CONTEXT_MENU = 1001, TYPE_HAND = 1002, TYPE_HELP = 1003, TYPE_WAIT = 1004,
      TYPE_CELL = 1006, TYPE_CROSSHAIR = 1007, TYPE_TEXT = 1008, TYPE_VERTICAL_TEXT = 1009, TYPE_ALIAS = 1010, TYPE_COPY = 1011,
      TYPE_NO_DROP = 1012, TYPE_ALL_SCROLL = 1013, TYPE_HORIZONTAL_DOUBLE_ARROW = 1014, TYPE_VERTICAL_DOUBLE_ARROW = 1015,
      TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW = 1016, TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW = 1017, TYPE_ZOOM_IN = 1018, TYPE_ZOOM_OUT = 1019,
      TYPE_GRAB = 1020, TYPE_GRABBING = 1021, TYPE_HANDWRITING = 1022;
  private final int mType;
  private PointerIcon(int type) { mType = type; }
  public static PointerIcon getSystemIcon(Context context, int type) { return new PointerIcon(type); }
  public static PointerIcon create(android.graphics.Bitmap bitmap, float hotSpotX, float hotSpotY) { return new PointerIcon(-1); }
  public static PointerIcon load(android.content.res.Resources resources, int resourceId) { return new PointerIcon(-1); }
  public int getType() { return mType; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) { p.writeInt(mType); }
  public static final Creator<PointerIcon> CREATOR = new Creator<PointerIcon>() {
    public PointerIcon createFromParcel(android.os.Parcel p) { return new PointerIcon(p.readInt()); }
    public PointerIcon[] newArray(int n) { return new PointerIcon[n]; }
  };
}

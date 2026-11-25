package android.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.*;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;

/** android.graphics.drawable.Icon (API 23): a resource, bitmap, encoded image or URI, loaded as a drawable. */
public final class Icon implements android.os.Parcelable {
  public static final int TYPE_BITMAP = 1, TYPE_RESOURCE = 2, TYPE_DATA = 3, TYPE_URI = 4, TYPE_ADAPTIVE_BITMAP = 5, TYPE_URI_ADAPTIVE_BITMAP = 6;
  public interface OnDrawableLoadedListener { void onDrawableLoaded(Drawable d); }

  private final int mType;
  private Bitmap mBitmap;
  private String mString;   // resource package, URI or file path
  private int mInt1, mInt2;  // resource id; data offset, length
  private byte[] mData;
  private ColorStateList mTintList;
  private BlendMode mBlendMode = BlendMode.SRC_IN;

  private Icon(int type) { mType = type; }

  public static Icon createWithResource(Context context, int resId) {
    if (context == null) throw new IllegalArgumentException("Context must not be null.");
    Icon i = new Icon(TYPE_RESOURCE); i.mInt1 = resId; i.mString = context.getPackageName(); return i;
  }
  public static Icon createWithResource(String resPackage, int resId) {
    if (resPackage == null) throw new IllegalArgumentException("Resource package name must not be null.");
    Icon i = new Icon(TYPE_RESOURCE); i.mInt1 = resId; i.mString = resPackage; return i;
  }
  public static Icon createWithBitmap(Bitmap bits) {
    if (bits == null) throw new IllegalArgumentException("Bitmap must not be null.");
    Icon i = new Icon(TYPE_BITMAP); i.mBitmap = bits; return i;
  }
  public static Icon createWithAdaptiveBitmap(Bitmap bits) {
    if (bits == null) throw new IllegalArgumentException("Bitmap must not be null.");
    Icon i = new Icon(TYPE_ADAPTIVE_BITMAP); i.mBitmap = bits; return i;
  }
  public static Icon createWithData(byte[] data, int offset, int length) {
    if (data == null) throw new IllegalArgumentException("Data must not be null.");
    Icon i = new Icon(TYPE_DATA); i.mData = data; i.mInt1 = offset; i.mInt2 = length; return i;
  }
  public static Icon createWithContentUri(String uri) {
    if (uri == null) throw new IllegalArgumentException("Uri must not be null.");
    Icon i = new Icon(TYPE_URI); i.mString = uri; return i;
  }
  public static Icon createWithContentUri(Uri uri) {
    if (uri == null) throw new IllegalArgumentException("Uri must not be null.");
    return createWithContentUri(uri.toString());
  }
  public static Icon createWithAdaptiveBitmapContentUri(String uri) {
    if (uri == null) throw new IllegalArgumentException("Uri must not be null.");
    Icon i = new Icon(TYPE_URI_ADAPTIVE_BITMAP); i.mString = uri; return i;
  }
  public static Icon createWithAdaptiveBitmapContentUri(Uri uri) {
    if (uri == null) throw new IllegalArgumentException("Uri must not be null.");
    return createWithAdaptiveBitmapContentUri(uri.toString());
  }
  public static Icon createWithFilePath(String path) {
    if (path == null) throw new IllegalArgumentException("Path must not be null.");
    Icon i = new Icon(TYPE_URI); i.mString = path; return i;
  }

  public int getType() { return mType; }
  private void need(int... types) {
    for (int t : types) if (mType == t) return;
    throw new IllegalStateException("called on the wrong kind of Icon (type " + mType + ")");
  }
  public int getResId() { need(TYPE_RESOURCE); return mInt1; }
  public String getResPackage() { need(TYPE_RESOURCE); return mString; }
  public Uri getUri() { need(TYPE_URI, TYPE_URI_ADAPTIVE_BITMAP); return Uri.parse(mString); }
  public Icon setTint(int tint) { return setTintList(ColorStateList.valueOf(tint)); }
  public Icon setTintList(ColorStateList tintList) { mTintList = tintList; return this; }
  public Icon setTintMode(PorterDuff.Mode mode) {
    String n = mode == null ? "SRC_IN" : mode.name();
    if (n.equals("ADD")) n = "PLUS";
    mBlendMode = BlendMode.SRC_IN;
    for (BlendMode b : BlendMode.values()) if (b.name().equals(n)) mBlendMode = b;
    return this;
  }
  public Icon setTintBlendMode(BlendMode mode) { mBlendMode = mode; return this; }

  /** the drawable this icon describes, tinted; null when it cannot be loaded */
  public Drawable loadDrawable(Context context) {
    Drawable d = loadPlain(context);
    if (d != null && (mTintList != null || mBlendMode != BlendMode.SRC_IN)) {
      d = d.mutate();
      d.setTintList(mTintList);
      d.setTintBlendMode(mBlendMode);
    }
    return d;
  }
  private Drawable loadPlain(Context context) {
    android.content.res.Resources res = context.getResources();
    switch (mType) {
      case TYPE_BITMAP: return new BitmapDrawable(res, mBitmap);
      case TYPE_ADAPTIVE_BITMAP: return new AdaptiveIconDrawable(null, new BitmapDrawable(res, mBitmap));
      case TYPE_RESOURCE:
        try { return res.getDrawable(mInt1, context.getTheme()); } catch (RuntimeException e) { return null; }
      case TYPE_DATA: {
        Bitmap b = BitmapFactory.decodeByteArray(mData, mInt1, mInt2);
        return b == null ? null : new BitmapDrawable(res, b);
      }
      case TYPE_URI: case TYPE_URI_ADAPTIVE_BITMAP: {
        java.io.InputStream is = null;
        try {
          Uri u = Uri.parse(mString);
          String scheme = u.getScheme();
          if (scheme == null || "file".equals(scheme)) is = new a2w.jdk.FileInputStream(scheme == null ? mString : u.getPath());
          else is = context.getContentResolver().openInputStream(u);
          Bitmap b = BitmapFactory.decodeStream(is);
          if (b == null) return null;
          return mType == TYPE_URI ? new BitmapDrawable(res, b) : new AdaptiveIconDrawable(null, new BitmapDrawable(res, b));
        } catch (Exception e) {
          return null;
        } finally {
          if (is != null) try { is.close(); } catch (java.io.IOException ignored) {}
        }
      }
      default: return null;
    }
  }
  /** loads, then sends andThen with the drawable as its obj */
  public void loadDrawableAsync(Context context, Message andThen) {
    andThen.obj = loadDrawable(context);
    andThen.sendToTarget();
  }
  public void loadDrawableAsync(Context context, final OnDrawableLoadedListener listener, Handler handler) {
    final Drawable d = loadDrawable(context);
    handler.post(() -> listener.onDrawableLoaded(d));
  }

  public int describeContents() { return mType == TYPE_BITMAP || mType == TYPE_ADAPTIVE_BITMAP || mType == TYPE_DATA ? android.os.Parcelable.CONTENTS_FILE_DESCRIPTOR : 0; }
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(mType);
    switch (mType) {
      case TYPE_BITMAP: case TYPE_ADAPTIVE_BITMAP: mBitmap.writeToParcel(dest, flags); break;
      case TYPE_RESOURCE: dest.writeString(mString); dest.writeInt(mInt1); break;
      case TYPE_DATA: dest.writeInt(mInt2); dest.writeByteArray(mData, mInt1, mInt2); break;
      default: dest.writeString(mString); break;
    }
    if (mTintList == null) dest.writeInt(0); else { dest.writeInt(1); mTintList.writeToParcel(dest, flags); }
    dest.writeString(mBlendMode.name());
  }
  public static final android.os.Parcelable.Creator<Icon> CREATOR = new android.os.Parcelable.Creator<Icon>() {
    public Icon createFromParcel(Parcel in) {
      Icon i = new Icon(in.readInt());
      switch (i.mType) {
        case TYPE_BITMAP: case TYPE_ADAPTIVE_BITMAP: i.mBitmap = Bitmap.CREATOR.createFromParcel(in); break;
        case TYPE_RESOURCE: i.mString = in.readString(); i.mInt1 = in.readInt(); break;
        case TYPE_DATA: { int len = in.readInt(); byte[] a = in.createByteArray(); i.mData = a; i.mInt1 = 0; i.mInt2 = a == null ? len : a.length; break; }
        default: i.mString = in.readString(); break;
      }
      if (in.readInt() == 1) i.mTintList = ColorStateList.CREATOR.createFromParcel(in);
      i.mBlendMode = BlendMode.valueOf(in.readString());
      return i;
    }
    public Icon[] newArray(int size) { return new Icon[size]; }
  };
  @Override public String toString() {
    StringBuilder b = new StringBuilder("Icon(typ=");
    switch (mType) {
      case TYPE_BITMAP: b.append("BITMAP size=").append(mBitmap.getWidth()).append('x').append(mBitmap.getHeight()); break;
      case TYPE_ADAPTIVE_BITMAP: b.append("BITMAP_MASKABLE size=").append(mBitmap.getWidth()).append('x').append(mBitmap.getHeight()); break;
      case TYPE_RESOURCE: b.append("RESOURCE pkg=").append(mString).append(" id=").append(String.format("0x%08x", mInt1)); break;
      case TYPE_DATA: b.append("DATA len=").append(mInt2); if (mInt1 != 0) b.append(" off=").append(mInt1); break;
      case TYPE_URI: b.append("URI uri=").append(mString); break;
      default: b.append("URI_MASKABLE uri=").append(mString); break;
    }
    if (mTintList != null) b.append(" tint=").append(String.format("0x%08x", mTintList.getDefaultColor()));
    if (mBlendMode != BlendMode.SRC_IN) b.append(" mode=").append(mBlendMode);
    return b.append(')').toString();
  }
}

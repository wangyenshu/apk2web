package android.graphics;

import org.teavm.jso.JSObject;

/** the bitmap as a canvas pattern (MIRROR tiles a mirrored copy; CLAMP leaves the outside empty) */
public class BitmapShader extends Shader {
  public static final int FILTER_MODE_DEFAULT = 0, FILTER_MODE_NEAREST = 1, FILTER_MODE_LINEAR = 2;
  public final Bitmap bitmap;
  public final TileMode tx, ty;
  private int filterMode = FILTER_MODE_DEFAULT, maxAnisotropy;
  private Gainmap overrideGainmap;
  public BitmapShader(Bitmap bitmap, TileMode tileX, TileMode tileY) {
    if (bitmap == null) throw new IllegalArgumentException("Bitmap must be non-null");
    this.bitmap = bitmap; tx = tileX; ty = tileY;
  }
  public int getFilterMode() { return filterMode; }
  public void setFilterMode(int mode) { filterMode = mode; maxAnisotropy = 0; gen++; }
  public int getMaxAnisotropy() { return maxAnisotropy; }
  public void setMaxAnisotropy(int maxAnisotropy) {
    if (maxAnisotropy < 1) throw new IllegalArgumentException("maxAnisotropy must be > 0");
    this.maxAnisotropy = maxAnisotropy; filterMode = FILTER_MODE_DEFAULT; gen++;
  }
  public void setOverrideGainmap(Gainmap gainmap) { overrideGainmap = gainmap; }
  @Override int version() { return gen * 31 + bitmap.imgVersion; }
  @Override JSObject js() {
    return make(4, localJs(), null, null, bitmap.img, null, null, tx == null ? 0 : tx.ordinal(), ty == null ? 0 : ty.ordinal(), null);
  }
}

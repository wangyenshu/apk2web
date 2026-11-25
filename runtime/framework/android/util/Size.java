package android.util;

public final class Size {
  private final int mWidth, mHeight;
  public Size(int width, int height) { mWidth = width; mHeight = height; }
  public int getWidth() { return mWidth; }
  public int getHeight() { return mHeight; }
  @Override public boolean equals(Object o) { return o instanceof Size && ((Size) o).mWidth == mWidth && ((Size) o).mHeight == mHeight; }
  @Override public int hashCode() { return mHeight ^ ((mWidth << (Integer.SIZE / 2)) | (mWidth >>> (Integer.SIZE / 2))); }
  @Override public String toString() { return mWidth + "x" + mHeight; }
  public static Size parseSize(String s) throws NumberFormatException {
    int sep = s.indexOf('*');
    if (sep < 0) sep = s.indexOf('x');
    if (sep < 0) throw new NumberFormatException("Invalid Size: \"" + s + "\"");
    try { return new Size(Integer.parseInt(s.substring(0, sep)), Integer.parseInt(s.substring(sep + 1))); }
    catch (NumberFormatException e) { throw new NumberFormatException("Invalid Size: \"" + s + "\""); }
  }
}

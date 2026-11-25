package android.graphics;

/** android.graphics.Picture: the recording is rasterized into a bitmap, which draw() paints at the origin. */
public class Picture {
  private Bitmap mBitmap;
  private int mWidth, mHeight;
  private Canvas mRecording;
  public Picture() {}
  public Picture(Picture src) { if (src != null) { mWidth = src.mWidth; mHeight = src.mHeight; mBitmap = src.mBitmap == null ? null : src.mBitmap.copy(Bitmap.Config.ARGB_8888, false); } }
  public Canvas beginRecording(int width, int height) {
    if (mRecording != null) throw new IllegalStateException("Picture already recording, must call #endRecording()");
    mWidth = width; mHeight = height;
    mBitmap = Bitmap.createBitmap(Math.max(1, width), Math.max(1, height), Bitmap.Config.ARGB_8888);
    mRecording = new Canvas(mBitmap);
    return mRecording;
  }
  public void endRecording() { mRecording = null; }
  public int getWidth() { return mWidth; }
  public int getHeight() { return mHeight; }
  public boolean requiresHardwareAcceleration() { return false; }
  public void draw(Canvas canvas) {
    if (mRecording != null) endRecording();
    if (mBitmap != null && mWidth > 0 && mHeight > 0) canvas.drawBitmap(mBitmap, new Rect(0, 0, mWidth, mHeight), new Rect(0, 0, mWidth, mHeight), null);
  }
  /** (removed in API 29) the picture as "a2wP", width, height and a PNG of the recording (not Skia's SKP format) */
  @Deprecated public void writeToStream(java.io.OutputStream stream) {
    if (stream == null) throw new IllegalArgumentException("stream cannot be null");
    try {
      java.io.ByteArrayOutputStream png = new java.io.ByteArrayOutputStream();
      if (mBitmap != null) mBitmap.compress(Bitmap.CompressFormat.PNG, 100, png);
      java.io.DataOutputStream d = new java.io.DataOutputStream(stream);
      d.writeInt(0x61327750); d.writeInt(mWidth); d.writeInt(mHeight); d.writeInt(png.size());
      png.writeTo(d);
      d.flush();
    } catch (java.io.IOException e) {
      throw new RuntimeException(e);
    }
  }
  /** (removed in API 29) reads what writeToStream wrote */
  @Deprecated public static Picture createFromStream(java.io.InputStream stream) {
    try {
      java.io.DataInputStream d = new java.io.DataInputStream(stream);
      if (d.readInt() != 0x61327750) throw new IllegalArgumentException("not a picture stream");
      Picture p = new Picture();
      p.mWidth = d.readInt(); p.mHeight = d.readInt();
      byte[] png = new byte[d.readInt()];
      d.readFully(png);
      Bitmap b = png.length == 0 ? null : BitmapFactory.decodeByteArray(png, 0, png.length);
      if (b != null) p.mBitmap = b.copy(Bitmap.Config.ARGB_8888, true);
      return p;
    } catch (java.io.IOException e) {
      throw new RuntimeException(e);
    }
  }
}

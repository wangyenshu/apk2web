package android.view;

/** A SurfaceView's surface (GL draws through the page's canvas; lockCanvas draws through the view's bitmap). */
public class Surface {
  public static final int ROTATION_0 = 0, ROTATION_90 = 1, ROTATION_180 = 2, ROTATION_270 = 3;
  SurfaceView owner;
  public Surface() {}
  public boolean isValid() { return owner == null || owner.mSurfaceExists; }
  public android.graphics.Canvas lockCanvas(android.graphics.Rect dirty) { return owner == null ? null : owner.lock(); }
  public void unlockCanvasAndPost(android.graphics.Canvas c) { if (owner != null) owner.post(c); }
  public void release() {}
}

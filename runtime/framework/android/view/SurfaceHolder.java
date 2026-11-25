package android.view;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface SurfaceHolder {
  int SURFACE_TYPE_NORMAL = 0, SURFACE_TYPE_HARDWARE = 1, SURFACE_TYPE_GPU = 2, SURFACE_TYPE_PUSH_BUFFERS = 3;
  interface Callback {
    void surfaceCreated(SurfaceHolder holder);
    void surfaceChanged(SurfaceHolder holder, int format, int width, int height);
    void surfaceDestroyed(SurfaceHolder holder);
  }
  void addCallback(Callback c);
  void removeCallback(Callback c);
  boolean isCreating();
  void setType(int t);
  void setFixedSize(int w, int h);
  void setSizeFromLayout();
  void setFormat(int f);
  void setKeepScreenOn(boolean k);
  Canvas lockCanvas();
  Canvas lockCanvas(Rect dirty);
  void unlockCanvasAndPost(Canvas c);
  Rect getSurfaceFrame();
  Surface getSurface();
}

package android.view;

import android.content.Context;

/** Device orientation changes in degrees: a page has no orientation sensor, so none are reported. */
public abstract class OrientationEventListener {
  public static final int ORIENTATION_UNKNOWN = -1;
  private boolean mEnabled;
  public OrientationEventListener(Context context) {}
  public OrientationEventListener(Context context, int rate) {}
  public void enable() { mEnabled = true; }
  public void disable() { mEnabled = false; }
  public boolean canDetectOrientation() { return false; }
  public abstract void onOrientationChanged(int orientation);
}

package android.view;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.List;

/** A display cutout (the page has none). */
public final class DisplayCutout {
  private final Rect mSafe;
  public DisplayCutout(Rect safeInsets, List<Rect> boundingRects) { mSafe = safeInsets == null ? new Rect() : new Rect(safeInsets); }
  public int getSafeInsetLeft() { return mSafe.left; }
  public int getSafeInsetTop() { return mSafe.top; }
  public int getSafeInsetRight() { return mSafe.right; }
  public int getSafeInsetBottom() { return mSafe.bottom; }
  public List<Rect> getBoundingRects() { return new ArrayList<>(); }
  public Rect getBoundingRectTop() { return new Rect(); }
  public Rect getBoundingRectBottom() { return new Rect(); }
  public Rect getBoundingRectLeft() { return new Rect(); }
  public Rect getBoundingRectRight() { return new Rect(); }
}

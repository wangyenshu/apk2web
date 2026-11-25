package android.view;

import android.graphics.Rect;

public class Gravity {
  public static final int NO_GRAVITY = 0, AXIS_SPECIFIED = 1, AXIS_PULL_BEFORE = 2, AXIS_PULL_AFTER = 4, AXIS_CLIP = 8,
      AXIS_X_SHIFT = 0, AXIS_Y_SHIFT = 4,
      TOP = 0x30, BOTTOM = 0x50, LEFT = 0x03, RIGHT = 0x05, CENTER_VERTICAL = 0x10, FILL_VERTICAL = 0x70,
      CENTER_HORIZONTAL = 0x01, FILL_HORIZONTAL = 0x07, CENTER = 0x11, FILL = 0x77, CLIP_VERTICAL = 0x80, CLIP_HORIZONTAL = 0x08,
      HORIZONTAL_GRAVITY_MASK = 0x07, VERTICAL_GRAVITY_MASK = 0x70, DISPLAY_CLIP_VERTICAL = 0x10000000, DISPLAY_CLIP_HORIZONTAL = 0x01000000,
      RELATIVE_LAYOUT_DIRECTION = 0x00800000, START = 0x00800003, END = 0x00800005, RELATIVE_HORIZONTAL_GRAVITY_MASK = 0x00800007;

  public static void apply(int gravity, int w, int h, Rect container, Rect outRect) { apply(gravity, w, h, container, 0, 0, outRect); }
  public static void apply(int gravity, int w, int h, Rect container, Rect outRect, int layoutDirection) { apply(getAbsoluteGravity(gravity, layoutDirection), w, h, container, 0, 0, outRect); }
  public static void apply(int gravity, int w, int h, Rect container, int xAdj, int yAdj, Rect outRect, int layoutDirection) { apply(getAbsoluteGravity(gravity, layoutDirection), w, h, container, xAdj, yAdj, outRect); }
  public static void apply(int gravity, int w, int h, Rect container, int xAdj, int yAdj, Rect outRect) {
    gravity = getAbsoluteGravity(gravity, 0);
    switch (gravity & ((AXIS_PULL_BEFORE | AXIS_PULL_AFTER) << AXIS_X_SHIFT)) {
      case 0:
        outRect.left = container.left + ((container.right - container.left - w) / 2) + xAdj;
        outRect.right = outRect.left + w;
        if ((gravity & (AXIS_CLIP << AXIS_X_SHIFT)) == (AXIS_CLIP << AXIS_X_SHIFT)) {
          if (outRect.left < container.left) outRect.left = container.left;
          if (outRect.right > container.right) outRect.right = container.right;
        }
        break;
      case AXIS_PULL_BEFORE << AXIS_X_SHIFT:
        outRect.left = container.left + xAdj;
        outRect.right = outRect.left + w;
        if ((gravity & (AXIS_CLIP << AXIS_X_SHIFT)) == (AXIS_CLIP << AXIS_X_SHIFT)) { if (outRect.right > container.right) outRect.right = container.right; }
        break;
      case AXIS_PULL_AFTER << AXIS_X_SHIFT:
        outRect.right = container.right - xAdj;
        outRect.left = outRect.right - w;
        if ((gravity & (AXIS_CLIP << AXIS_X_SHIFT)) == (AXIS_CLIP << AXIS_X_SHIFT)) { if (outRect.left < container.left) outRect.left = container.left; }
        break;
      default:
        outRect.left = container.left + xAdj;
        outRect.right = container.right + xAdj;
        break;
    }
    switch (gravity & ((AXIS_PULL_BEFORE | AXIS_PULL_AFTER) << AXIS_Y_SHIFT)) {
      case 0:
        outRect.top = container.top + ((container.bottom - container.top - h) / 2) + yAdj;
        outRect.bottom = outRect.top + h;
        if ((gravity & (AXIS_CLIP << AXIS_Y_SHIFT)) == (AXIS_CLIP << AXIS_Y_SHIFT)) {
          if (outRect.top < container.top) outRect.top = container.top;
          if (outRect.bottom > container.bottom) outRect.bottom = container.bottom;
        }
        break;
      case AXIS_PULL_BEFORE << AXIS_Y_SHIFT:
        outRect.top = container.top + yAdj;
        outRect.bottom = outRect.top + h;
        if ((gravity & (AXIS_CLIP << AXIS_Y_SHIFT)) == (AXIS_CLIP << AXIS_Y_SHIFT)) { if (outRect.bottom > container.bottom) outRect.bottom = container.bottom; }
        break;
      case AXIS_PULL_AFTER << AXIS_Y_SHIFT:
        outRect.bottom = container.bottom - yAdj;
        outRect.top = outRect.bottom - h;
        if ((gravity & (AXIS_CLIP << AXIS_Y_SHIFT)) == (AXIS_CLIP << AXIS_Y_SHIFT)) { if (outRect.top < container.top) outRect.top = container.top; }
        break;
      default:
        outRect.top = container.top + yAdj;
        outRect.bottom = container.bottom + yAdj;
        break;
    }
  }
  public static boolean isVertical(int g) { return g > 0 && (g & VERTICAL_GRAVITY_MASK) != 0; }
  public static boolean isHorizontal(int g) { return g > 0 && (g & RELATIVE_HORIZONTAL_GRAVITY_MASK) != 0; }
  public static int getAbsoluteGravity(int gravity, int layoutDirection) {
    int r = gravity;
    if ((r & RELATIVE_LAYOUT_DIRECTION) > 0) {
      if ((r & START) == START) { r &= ~START; r |= LEFT; }
      else if ((r & END) == END) { r &= ~END; r |= RIGHT; }
      r &= ~RELATIVE_LAYOUT_DIRECTION;
    }
    return r;
  }
}

package android.view.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.Random;

/** android.view.animation.GridLayoutAnimationController: layout animation delays by column and row. */
public class GridLayoutAnimationController extends LayoutAnimationController {
  public static final int DIRECTION_LEFT_TO_RIGHT = 0x0, DIRECTION_RIGHT_TO_LEFT = 0x1, DIRECTION_TOP_TO_BOTTOM = 0x0, DIRECTION_BOTTOM_TO_TOP = 0x2;
  public static final int DIRECTION_HORIZONTAL_MASK = 0x1, DIRECTION_VERTICAL_MASK = 0x2;
  public static final int PRIORITY_NONE = 0, PRIORITY_COLUMN = 1, PRIORITY_ROW = 2;
  private float mColumnDelay, mRowDelay;
  private int mDirection, mDirectionPriority;

  /** <gridLayoutAnimation android:columnDelay android:rowDelay android:direction android:directionPriority> */
  public GridLayoutAnimationController(Context context, AttributeSet attrs) {
    super(context, attrs);
    mColumnDelay = fraction(attr(attrs, "columnDelay"), 0.5f);
    mRowDelay = fraction(attr(attrs, "rowDelay"), 0.5f);
    String d = attr(attrs, "direction");
    if (d != null) {
      if (d.matches("\\d+")) mDirection = Integer.parseInt(d);
      else { if (d.contains("right_to_left")) mDirection |= DIRECTION_RIGHT_TO_LEFT; if (d.contains("bottom_to_top")) mDirection |= DIRECTION_BOTTOM_TO_TOP; }
    }
    String p = attr(attrs, "directionPriority");
    mDirectionPriority = "column".equals(p) || "1".equals(p) ? PRIORITY_COLUMN : "row".equals(p) || "2".equals(p) ? PRIORITY_ROW : PRIORITY_NONE;
  }
  private static float fraction(String v, float def) {
    if (v == null) return def;
    return v.endsWith("%") ? a2w.Values.parseFloat(v.substring(0, v.length() - 1), def * 100) / 100f : a2w.Values.parseFloat(v, def);
  }
  public GridLayoutAnimationController(Animation animation) { this(animation, 0.5f, 0.5f); }
  public GridLayoutAnimationController(Animation animation, float columnDelay, float rowDelay) { super(animation); mColumnDelay = columnDelay; mRowDelay = rowDelay; }
  public float getColumnDelay() { return mColumnDelay; }
  public void setColumnDelay(float columnDelay) { mColumnDelay = columnDelay; }
  public float getRowDelay() { return mRowDelay; }
  public void setRowDelay(float rowDelay) { mRowDelay = rowDelay; }
  public int getDirection() { return mDirection; }
  public void setDirection(int direction) { mDirection = direction; }
  public int getDirectionPriority() { return mDirectionPriority; }
  public void setDirectionPriority(int directionPriority) { mDirectionPriority = directionPriority; }
  @Override public boolean willOverlap() { return mColumnDelay < 1.0f || mRowDelay < 1.0f; }
  @Override protected long getDelayForView(View view) {
    ViewGroup.LayoutParams lp = view.getLayoutParams();
    if (lp == null || !(lp.layoutAnimationParameters instanceof AnimationParameters)) return 0;
    AnimationParameters params = (AnimationParameters) lp.layoutAnimationParameters;
    int column = transformedColumn(params), row = transformedRow(params);
    int rows = params.rowsCount, columns = params.columnsCount;
    long duration = mAnimation.getDuration();
    float columnDelay = mColumnDelay * duration, rowDelay = mRowDelay * duration;
    float totalDelay;
    long viewDelay;
    if (mInterpolator == null) mInterpolator = new LinearInterpolator();
    switch (mDirectionPriority) {
      case PRIORITY_COLUMN: viewDelay = (long) (row * rowDelay + column * rows * rowDelay); totalDelay = rows * rowDelay + columns * rows * rowDelay; break;
      case PRIORITY_ROW: viewDelay = (long) (column * columnDelay + row * columns * columnDelay); totalDelay = columns * columnDelay + rows * columns * columnDelay; break;
      default: viewDelay = (long) (column * columnDelay + row * rowDelay); totalDelay = columns * columnDelay + rows * rowDelay; break;
    }
    if (totalDelay == 0) return 0;
    float normalized = mInterpolator.getInterpolation(viewDelay / totalDelay);
    return (long) (normalized * totalDelay);
  }
  private int transformedColumn(AnimationParameters p) {
    int index;
    switch (getOrder()) {
      case ORDER_REVERSE: index = p.columnsCount - 1 - p.column; break;
      case ORDER_RANDOM: if (mRandomizer == null) mRandomizer = new Random(); index = (int) (p.columnsCount * mRandomizer.nextFloat()); break;
      default: index = p.column;
    }
    return (mDirection & DIRECTION_HORIZONTAL_MASK) == DIRECTION_RIGHT_TO_LEFT ? p.columnsCount - 1 - index : index;
  }
  private int transformedRow(AnimationParameters p) {
    int index;
    switch (getOrder()) {
      case ORDER_REVERSE: index = p.rowsCount - 1 - p.row; break;
      case ORDER_RANDOM: if (mRandomizer == null) mRandomizer = new Random(); index = (int) (p.rowsCount * mRandomizer.nextFloat()); break;
      default: index = p.row;
    }
    return (mDirection & DIRECTION_VERTICAL_MASK) == DIRECTION_BOTTOM_TO_TOP ? p.rowsCount - 1 - index : index;
  }
  public static class AnimationParameters extends LayoutAnimationController.AnimationParameters {
    public int column, row, columnsCount, rowsCount;
    public AnimationParameters() {}
  }
}

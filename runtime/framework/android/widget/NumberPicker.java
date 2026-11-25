package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/** Picks a number (or one of displayed values) from a range: the value with its neighbours, scrolled by dragging or tapping. */
public class NumberPicker extends LinearLayout {
  public interface OnValueChangeListener { void onValueChange(NumberPicker picker, int oldVal, int newVal); }
  public interface OnScrollListener {
    int SCROLL_STATE_IDLE = 0, SCROLL_STATE_TOUCH_SCROLL = 1, SCROLL_STATE_FLING = 2;
    void onScrollStateChange(NumberPicker view, int scrollState);
  }
  public interface Formatter { String format(int value); }

  private int mMin, mMax, mValue;
  private boolean mWrap = true;
  private String[] mDisplayed;
  private Formatter mFormatter;
  private OnValueChangeListener mListener;
  private OnScrollListener mScrollListener;
  private final TextView mPrev, mCur, mNext;
  private float mDownY;
  private int mDragSteps;

  public NumberPicker(Context c) { this(c, null); }
  public NumberPicker(Context c, AttributeSet a) { this(c, a, 0); }
  public NumberPicker(Context c, AttributeSet a, int d) { this(c, a, d, 0); }
  public NumberPicker(Context c, AttributeSet a, int d, int r) {
    super(c, a, d);
    setOrientation(VERTICAL);
    setGravity(Gravity.CENTER_HORIZONTAL);
    mPrev = cell(c, false); mCur = cell(c, true); mNext = cell(c, false);
    mPrev.setOnClickListener(v -> step(-1));
    mNext.setOnClickListener(v -> step(1));
    // one focus stop: the picker itself, whose up/down keys change the value
    mPrev.setFocusable(false);
    mNext.setFocusable(false);
    setFocusable(true);
    int w = Math.round(64 * a2w.Screen.DENSITY), h = Math.round(40 * a2w.Screen.DENSITY);
    addView(mPrev, new LinearLayout.LayoutParams(w, h));
    addView(mCur, new LinearLayout.LayoutParams(w, h));
    addView(mNext, new LinearLayout.LayoutParams(w, h));
    update();
  }
  private TextView cell(Context c, boolean current) {
    TextView t = new TextView(c);
    t.setGravity(Gravity.CENTER);
    t.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, current ? 20 : 16);
    if (!current) t.setAlpha(0.5f);
    return t;
  }
  private String label(int v) {
    if (mDisplayed != null && v - mMin >= 0 && v - mMin < mDisplayed.length) return mDisplayed[v - mMin];
    return mFormatter != null ? mFormatter.format(v) : String.valueOf(v);
  }
  private int wrapped(int v) {
    if (mMax < mMin) return mMin;
    if (v > mMax) return mWrap ? mMin + (v - mMin) % (mMax - mMin + 1) : mMax;
    if (v < mMin) return mWrap ? mMax - (mMin - v - 1) % (mMax - mMin + 1) : mMin;
    return v;
  }
  private void update() {
    boolean wrap = getWrapSelectorWheel();
    mCur.setText(label(mValue));
    mPrev.setText(mValue > mMin || wrap ? label(wrapped(mValue - 1)) : "");
    mNext.setText(mValue < mMax || wrap ? label(wrapped(mValue + 1)) : "");
    invalidate();
  }
  private void step(int d) { setValueInternal(wrapped(mValue + d), true); }
  private void setValueInternal(int v, boolean notify) {
    v = Math.max(mMin, Math.min(mMax, v));
    if (v == mValue) { update(); return; }
    int old = mValue;
    mValue = v;
    update();
    if (notify && mListener != null) mListener.onValueChange(this, old, v);
  }
  @Override public boolean onInterceptTouchEvent(MotionEvent e) {
    if (e.getActionMasked() == MotionEvent.ACTION_DOWN) { mDownY = e.getY(); mDragSteps = 0; return false; }
    return e.getActionMasked() == MotionEvent.ACTION_MOVE && Math.abs(e.getY() - mDownY) > 8 * a2w.Screen.DENSITY;
  }
  @Override public boolean onTouchEvent(MotionEvent e) {
    int cellH = Math.max(1, mCur.getHeight());
    switch (e.getActionMasked()) {
      case MotionEvent.ACTION_DOWN: mDownY = e.getY(); mDragSteps = 0; return true;
      case MotionEvent.ACTION_MOVE: {
        int steps = (int) ((mDownY - e.getY()) / cellH);
        if (steps != mDragSteps) {
          if (mScrollListener != null && mDragSteps == 0) mScrollListener.onScrollStateChange(this, OnScrollListener.SCROLL_STATE_TOUCH_SCROLL);
          step(steps - mDragSteps);
          mDragSteps = steps;
        }
        return true;
      }
      case MotionEvent.ACTION_UP: case MotionEvent.ACTION_CANCEL:
        if (mScrollListener != null && mDragSteps != 0) mScrollListener.onScrollStateChange(this, OnScrollListener.SCROLL_STATE_IDLE);
        return true;
      default: return super.onTouchEvent(e);
    }
  }
  @Override protected void dispatchDraw(Canvas c) {
    super.dispatchDraw(c);
    Paint p = new Paint();
    p.setColor(0xff33b5e5);
    float t = Math.max(1, a2w.Screen.DENSITY);
    c.drawRect(mCur.getLeft(), mCur.getTop(), mCur.getRight(), mCur.getTop() + t, p);
    c.drawRect(mCur.getLeft(), mCur.getBottom() - t, mCur.getRight(), mCur.getBottom(), p);
  }

  private int mLastDpad = -1;
  /** up/down: the previous/next value (as the wheel's arrows), while there is one */
  @Override public boolean dispatchKeyEvent(android.view.KeyEvent event) {
    int k = event.getKeyCode();
    if ((k == android.view.KeyEvent.KEYCODE_DPAD_DOWN || k == android.view.KeyEvent.KEYCODE_DPAD_UP) && isEnabled()) {
      boolean inc = k == android.view.KeyEvent.KEYCODE_DPAD_DOWN;
      if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
        if (getWrapSelectorWheel() || (inc ? mValue < mMax : mValue > mMin)) { requestFocus(); mLastDpad = k; step(inc ? 1 : -1); return true; }
      } else if (event.getAction() == android.view.KeyEvent.ACTION_UP && mLastDpad == k) { mLastDpad = -1; return true; }
    }
    return super.dispatchKeyEvent(event);
  }
  public void setOnValueChangedListener(OnValueChangeListener l) { mListener = l; }
  public void setOnScrollListener(OnScrollListener l) { mScrollListener = l; }
  public void setFormatter(Formatter f) { mFormatter = f; update(); }
  public void setValue(int value) { setValueInternal(value, false); }
  public int getValue() { return mValue; }
  public int getMinValue() { return mMin; }
  public void setMinValue(int min) { mMin = min; if (mValue < min) mValue = min; if (mMax < min) mMax = min; update(); }
  public int getMaxValue() { return mMax; }
  public void setMaxValue(int max) { mMax = max; if (mValue > max) mValue = max; if (mMin > max) mMin = max; update(); }
  public String[] getDisplayedValues() { return mDisplayed; }
  public void setDisplayedValues(String[] values) { mDisplayed = values; update(); }
  public boolean getWrapSelectorWheel() { return mWrap && mMax - mMin + 1 >= 3; }
  public void setWrapSelectorWheel(boolean wrap) { mWrap = wrap; update(); }
  public void setOnLongPressUpdateInterval(long ms) {}
  public void setSelectionDividerHeight(int h) {}
  public int getSelectionDividerHeight() { return 1; }
  public void setTextColor(int color) { mCur.setTextColor(color); mPrev.setTextColor(color); mNext.setTextColor(color); }
  public int getTextColor() { return mCur.getCurrentTextColor(); }
  public void setTextSize(float size) { mCur.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, size); }
  public float getTextSize() { return mCur.getTextSize(); }
  public void setDescendantFocusability(int f) { super.setDescendantFocusability(f); }
}

package android.widget;

import android.content.Context;
import android.util.AttributeSet;

public class SeekBar extends AbsSeekBar {
  public interface OnSeekBarChangeListener {
    void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);
    void onStartTrackingTouch(SeekBar seekBar);
    void onStopTrackingTouch(SeekBar seekBar);
  }
  private OnSeekBarChangeListener mOnSeekBarChangeListener;
  private boolean mFromUser;
  public SeekBar(Context c) { this(c, null); }
  public SeekBar(Context c, AttributeSet a) { this(c, a, 0); }
  public SeekBar(Context c, AttributeSet a, int d) { super(c, a, d); }
  @Override synchronized void setProgress(int progress, boolean fromUser) { mFromUser = fromUser; super.setProgress(progress, fromUser); mFromUser = false; }
  @Override void onProgressRefresh(float scale, boolean fromUser) {
    super.onProgressRefresh(scale, fromUser);
    if (mOnSeekBarChangeListener != null) mOnSeekBarChangeListener.onProgressChanged(this, getProgress(), mFromUser);
  }
  public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) { mOnSeekBarChangeListener = l; }
  @Override void onStartTrackingTouch() { super.onStartTrackingTouch(); if (mOnSeekBarChangeListener != null) mOnSeekBarChangeListener.onStartTrackingTouch(this); }
  @Override void onStopTrackingTouch() { super.onStopTrackingTouch(); if (mOnSeekBarChangeListener != null) mOnSeekBarChangeListener.onStopTrackingTouch(this); }
}

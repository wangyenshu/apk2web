package android.app;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.*;

public class ProgressDialog extends AlertDialog {
  public static final int STYLE_SPINNER = 0, STYLE_HORIZONTAL = 1;
  ProgressBar mProgress;
  TextView mMsg;
  int mStyle, mMax = 100, mValue;
  boolean mIndeterminate = true;
  CharSequence mMessageText;
  public ProgressDialog(Context c) { super(c); }
  public ProgressDialog(Context c, int theme) { super(c, theme); }
  public static ProgressDialog show(Context c, CharSequence title, CharSequence message) { return show(c, title, message, false); }
  public static ProgressDialog show(Context c, CharSequence title, CharSequence message, boolean indeterminate) { return show(c, title, message, indeterminate, false, null); }
  public static ProgressDialog show(Context c, CharSequence title, CharSequence message, boolean indeterminate, boolean cancelable) { return show(c, title, message, indeterminate, cancelable, null); }
  public static ProgressDialog show(Context c, CharSequence title, CharSequence message, boolean indeterminate, boolean cancelable, OnCancelListener l) {
    ProgressDialog d = new ProgressDialog(c);
    d.setTitle(title); d.setMessage(message); d.setIndeterminate(indeterminate); d.setCancelable(cancelable); d.setOnCancelListener(l);
    d.show();
    return d;
  }
  @Override protected void onCreate(android.os.Bundle b) {
    Context c = getContext();
    LinearLayout row = new LinearLayout(c);
    row.setOrientation(LinearLayout.HORIZONTAL);
    row.setGravity(Gravity.CENTER_VERTICAL);
    int p = (int) dp(10);
    row.setPadding(p, p, p, p);
    if (mStyle == STYLE_SPINNER) { mProgress = new ProgressBar(c); row.addView(mProgress); }
    else { mProgress = new ProgressBar(c, null, 0); }
    mMsg = new TextView(c);
    mMsg.setTextColor(0xffffffff);
    mMsg.setTextSize(16);
    mMsg.setPadding(p, 0, 0, 0);
    mMsg.setText(mMessageText);
    row.addView(mMsg, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    setView(row);
    super.onCreate(b);
  }
  @Override public void setMessage(CharSequence m) { mMessageText = m; if (mMsg != null) mMsg.setText(m); }
  public void setProgressStyle(int s) { mStyle = s; }
  public void setIndeterminate(boolean i) { mIndeterminate = i; }
  public boolean isIndeterminate() { return mIndeterminate; }
  public void setMax(int m) { mMax = m; if (mProgress != null) mProgress.setMax(m); }
  public int getMax() { return mMax; }
  public void setProgress(int v) { mValue = v; if (mProgress != null) mProgress.setProgress(v); }
  public int getProgress() { return mValue; }
  public void incrementProgressBy(int d) { setProgress(mValue + d); }
  public void setSecondaryProgress(int v) {}
  public void setProgressDrawable(android.graphics.drawable.Drawable d) {}
  public void setIndeterminateDrawable(android.graphics.drawable.Drawable d) {}
}

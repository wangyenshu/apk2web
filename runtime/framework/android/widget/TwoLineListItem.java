package android.widget;

import android.content.Context;
import android.util.AttributeSet;

public class TwoLineListItem extends RelativeLayout {
  private TextView mText1, mText2;
  public TwoLineListItem(Context c) { this(c, null); }
  public TwoLineListItem(Context c, AttributeSet a) { this(c, a, 0); }
  public TwoLineListItem(Context c, AttributeSet a, int d) { super(c, a, d); }
  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    mText1 = (TextView) findViewById(android.R.id.text1);
    mText2 = (TextView) findViewById(android.R.id.text2);
  }
  public TextView getText1() { if (mText1 == null) mText1 = (TextView) findViewById(android.R.id.text1); return mText1; }
  public TextView getText2() { if (mText2 == null) mText2 = (TextView) findViewById(android.R.id.text2); return mText2; }
}

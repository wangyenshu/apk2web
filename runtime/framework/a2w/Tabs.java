package a2w;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/** The framework's tab layouts (TabActivity's default content, tab indicators), built in code. */
public final class Tabs {
  private Tabs() {}
  static float dp(Context c, float v) { return v * c.getResources().getDisplayMetrics().density; }

  /** what com.android.internal.R.layout.tab_content holds: TabHost > (TabWidget, FrameLayout tabcontent) */
  public static View defaultLayout(Context c) {
    TabHost host = new TabHost(c);
    host.setId(android.R.id.tabhost);
    LinearLayout col = new LinearLayout(c);
    col.setOrientation(LinearLayout.VERTICAL);
    TabWidget tabs = new TabWidget(c);
    tabs.setId(android.R.id.tabs);
    col.addView(tabs, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    FrameLayout content = new FrameLayout(c);
    content.setId(android.R.id.tabcontent);
    col.addView(content, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));
    host.addView(col, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    return host;
  }

  /** a tab indicator: icon above label, underlined when selected */
  public static View indicator(Context c, TabWidget parent, CharSequence label, Drawable icon) {
    LinearLayout v = new LinearLayout(c) {
      final Paint p = new Paint();
      @Override protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        p.setColor(isSelected() ? 0xff33b5e5 : 0x40888888);
        float h = dp(getContext(), isSelected() ? 4 : 1);
        canvas.drawRect(0, getHeight() - h, getWidth(), getHeight(), p);
      }
      @Override public void setSelected(boolean s) { super.setSelected(s); invalidate(); }
    };
    v.setOrientation(LinearLayout.VERTICAL);
    v.setGravity(Gravity.CENTER);
    v.setMinimumHeight((int) dp(c, 48));
    int pad = (int) dp(c, 6);
    v.setPadding(pad, pad, pad, pad);
    if (icon != null) {
      ImageView iv = new ImageView(c);
      iv.setId(android.R.id.icon);
      iv.setImageDrawable(icon);
      v.addView(iv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    TextView t = new TextView(c);
    t.setId(android.R.id.title);
    t.setText(label);
    t.setGravity(Gravity.CENTER);
    t.setSingleLine(true);
    t.setTextSize(14);
    v.addView(t, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    return v;
  }
}

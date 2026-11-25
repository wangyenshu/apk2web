package android.widget;

import android.content.Context;
import android.util.AttributeSet;

public final class Space extends android.view.View {
  public Space(Context c) { super(c); setVisibility(INVISIBLE); }
  public Space(Context c, AttributeSet a) { super(c, a); if (getVisibility() == VISIBLE) setVisibility(INVISIBLE); }
  public Space(Context c, AttributeSet a, int d) { this(c, a); }
  @Override public void draw(android.graphics.Canvas c) {}
}

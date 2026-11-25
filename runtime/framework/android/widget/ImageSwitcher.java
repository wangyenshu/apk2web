package android.widget;

import android.content.Context;
import android.util.AttributeSet;

public class ImageSwitcher extends ViewSwitcher {
  public ImageSwitcher(Context c) { super(c); }
  public ImageSwitcher(Context c, AttributeSet a) { super(c, a); }
  public void setImageResource(int id) { ImageView v = (ImageView) getNextView(); if (v != null) { v.setImageResource(id); showNext(); } }
  public void setImageDrawable(android.graphics.drawable.Drawable d) { ImageView v = (ImageView) getNextView(); if (v != null) { v.setImageDrawable(d); showNext(); } }
  public void setImageURI(android.net.Uri u) { ImageView v = (ImageView) getNextView(); if (v != null) { v.setImageURI(u); showNext(); } }
}

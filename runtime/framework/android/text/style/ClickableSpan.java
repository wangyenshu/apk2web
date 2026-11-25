package android.text.style;

import android.text.TextPaint;
import android.view.View;

/** Text that reacts to a tap (with LinkMovementMethod): drawn underlined in the link colour. */
public abstract class ClickableSpan extends CharacterStyle implements UpdateAppearance {
  public abstract void onClick(View widget);
  @Override public void updateDrawState(TextPaint ds) {
    ds.setColor(ds.linkColor != 0 ? ds.linkColor : 0xff33b5e5);
    ds.setUnderlineText(true);
  }
}

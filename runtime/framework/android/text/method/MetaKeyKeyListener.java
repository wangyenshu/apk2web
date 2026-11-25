package android.text.method;

import android.text.Editable;
import android.text.Spannable;
import android.view.KeyEvent;
import android.view.View;

public abstract class MetaKeyKeyListener {
  public static final int META_SHIFT_ON = KeyEvent.META_SHIFT_ON, META_ALT_ON = KeyEvent.META_ALT_ON, META_SYM_ON = KeyEvent.META_SYM_ON,
      META_CAP_LOCKED = 0x100, META_ALT_LOCKED = 0x200, META_SYM_LOCKED = 0x400, META_SELECTING = 0x800;
  public MetaKeyKeyListener() {}
  public static void resetMetaState(Spannable text) {}
  public static final int getMetaState(CharSequence text) { return 0; }
  public static final int getMetaState(CharSequence text, int meta) { return 0; }
  public static final int getMetaState(long state) { return 0; }
  public static final int getMetaState(long state, int meta) { return 0; }
  public static long resetLockedMeta(long state) { return state; }
  public static void adjustMetaAfterKeypress(Spannable content) {}
  public static boolean isMetaTracker(CharSequence text, Object what) { return false; }
  public static boolean isSelectingMetaTracker(CharSequence text, Object what) { return false; }
  protected static void resetLockedMeta(Spannable content) {}
  public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) { return false; }
  public boolean onKeyUp(View view, Editable content, int keyCode, KeyEvent event) { return false; }
  public void clearMetaKeyState(View view, Editable content, int states) {}
  public static void clearMetaKeyState(Editable content, int states) {}
  public long clearMetaKeyState(long state, int which) { return state; }
  public static long adjustMetaAfterKeypress(long state) { return state; }
  public static long handleKeyDown(long state, int keyCode, KeyEvent event) { return state; }
  public static long handleKeyUp(long state, int keyCode, KeyEvent event) { return state; }
}

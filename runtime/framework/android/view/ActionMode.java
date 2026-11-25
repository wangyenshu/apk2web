package android.view;

/** A contextual mode of the UI (a2w.ActionModeImpl shows the window's); the base class keeps tag, type and title hint. */
public abstract class ActionMode {
  public static final int DEFAULT_HIDE_DURATION = -1;
  public static final int TYPE_FLOATING = 1;
  public static final int TYPE_PRIMARY = 0;
  private Object mTag;
  private boolean mTitleOptionalHint;
  private int mType = TYPE_PRIMARY;
  public ActionMode() { }
  public abstract void finish();
  public abstract android.view.View getCustomView();
  public abstract android.view.Menu getMenu();
  public abstract android.view.MenuInflater getMenuInflater();
  public abstract java.lang.CharSequence getSubtitle();
  public java.lang.Object getTag() { return mTag; }
  public abstract java.lang.CharSequence getTitle();
  public boolean getTitleOptionalHint() { return mTitleOptionalHint; }
  public int getType() { return mType; }
  /** floating modes hide for a while; others ignore it */
  public void hide(long duration) { }
  public abstract void invalidate();
  public void invalidateContentRect() { }
  public boolean isTitleOptional() { return false; }
  public void onWindowFocusChanged(boolean hasWindowFocus) { }
  public abstract void setCustomView(android.view.View p0);
  public abstract void setSubtitle(int p0);
  public abstract void setSubtitle(java.lang.CharSequence p0);
  public void setTag(java.lang.Object tag) { mTag = tag; }
  public abstract void setTitle(int p0);
  public abstract void setTitle(java.lang.CharSequence p0);
  public void setTitleOptionalHint(boolean titleOptional) { mTitleOptionalHint = titleOptional; }
  public void setType(int type) { mType = type; }

  public interface Callback {
    boolean onActionItemClicked(android.view.ActionMode p0, android.view.MenuItem p1);
    boolean onCreateActionMode(android.view.ActionMode p0, android.view.Menu p1);
    void onDestroyActionMode(android.view.ActionMode p0);
    boolean onPrepareActionMode(android.view.ActionMode p0, android.view.Menu p1);
  }

  public static abstract class Callback2 implements android.view.ActionMode.Callback {
    public Callback2() { }
    /** the content the floating toolbar avoids: the whole view by default */
    public void onGetContentRect(android.view.ActionMode mode, android.view.View view, android.graphics.Rect outRect) {
      if (view != null) outRect.set(0, 0, view.getWidth(), view.getHeight()); else outRect.set(0, 0, 0, 0);
    }
  }
}

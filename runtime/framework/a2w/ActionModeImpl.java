package a2w;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * An action mode shown by the window: TYPE_PRIMARY as a contextual bar over the top of the window (over the action
 * bar when there is one): done button, title or custom view, action items, overflow; TYPE_FLOATING as a small bar of
 * item labels above the content rect its Callback2 reports.
 */
public final class ActionModeImpl extends ActionMode {
  private final Context mContext;
  private final ActionMode.Callback mCallback;
  private final MenuBuilder mMenu;
  private final PhoneWindow mWindow;
  private final View mOriginalView;
  private CharSequence mTitle, mSubtitle;
  private View mCustomView;
  private boolean mFinished;
  private LinearLayout mBar;
  private TextView mTitleView, mSubtitleView;
  private LinearLayout mActions, mTitles;
  private FrameLayout mCustomFrame;
  private ImageView mOverflow;
  private final int mHeight, mTextColor;

  public ActionModeImpl(PhoneWindow window, View originalView, ActionMode.Callback callback, int type) {
    mWindow = window;
    mContext = window.getContext();
    mOriginalView = originalView;
    mCallback = callback;
    setType(type);
    mMenu = new MenuBuilder(mContext);
    mMenu.setCallback((m, item) -> mCallback.onActionItemClicked(this, item));
    TypedValue tv = new TypedValue();
    int h = mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true) ? TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics()) : 0;
    if (h <= 0) h = Math.round(48 * Screen.DENSITY);
    ActionBarImpl ab = window.peekActionBar();
    if (ab != null && ab.isShowing() && ab.view().getHeight() > 0) h = ab.getHeight();
    mHeight = h;
    mTextColor = WidgetDrawables.lightTheme(mContext) && ab == null ? 0xff000000 : 0xffffffff;
  }

  /** onCreateActionMode, then the bar; false when the callback declined */
  public boolean start() {
    if (!mCallback.onCreateActionMode(this, mMenu)) return false;
    mCallback.onPrepareActionMode(this, mMenu);
    if (getType() == TYPE_FLOATING) showFloating(); else showBar();
    return true;
  }
  public boolean isFinished() { return mFinished; }

  // ---------------------------------------------------------------- the primary bar
  private void showBar() {
    Context c = mContext;
    mBar = new LinearLayout(c);
    mBar.setOrientation(LinearLayout.HORIZONTAL);
    mBar.setGravity(Gravity.CENTER_VERTICAL);
    mBar.setClickable(true);   // touches on the bar don't reach the content under it
    mBar.setBackgroundDrawable(background());
    ImageView done = new ImageView(c);
    done.setScaleType(ImageView.ScaleType.CENTER);
    done.setImageDrawable(new Done(mTextColor));
    done.setOnClickListener(v -> finish());
    done.setContentDescription("Done");
    mBar.addView(done, new LinearLayout.LayoutParams(mHeight, ViewGroup.LayoutParams.MATCH_PARENT));
    mTitles = new LinearLayout(c);
    mTitles.setOrientation(LinearLayout.VERTICAL);
    mTitles.setGravity(Gravity.CENTER_VERTICAL);
    int pad = Math.round(8 * Screen.DENSITY);
    mTitles.setPadding(pad, 0, pad, 0);
    mTitleView = new TextView(c);
    mTitleView.setSingleLine(true);
    mTitleView.setEllipsize(TextUtils.TruncateAt.END);
    mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
    mTitleView.setTextColor(mTextColor);
    mSubtitleView = new TextView(c);
    mSubtitleView.setSingleLine(true);
    mSubtitleView.setEllipsize(TextUtils.TruncateAt.END);
    mSubtitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
    mSubtitleView.setTextColor((mTextColor & 0x00ffffff) | 0xb3000000);
    mTitles.addView(mTitleView);
    mTitles.addView(mSubtitleView);
    mBar.addView(mTitles, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
    mCustomFrame = new FrameLayout(c);
    mBar.addView(mCustomFrame, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
    mActions = new LinearLayout(c);
    mActions.setOrientation(LinearLayout.HORIZONTAL);
    mBar.addView(mActions, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
    mOverflow = new ImageView(c);
    mOverflow.setScaleType(ImageView.ScaleType.CENTER);
    mOverflow.setImageDrawable(new ActionBarImpl.Glyph(true, mTextColor));
    mOverflow.setOnClickListener(v -> MenuPopup.show(overflowMenu()));
    mBar.addView(mOverflow, new LinearLayout.LayoutParams(Math.round(40 * Screen.DENSITY), ViewGroup.LayoutParams.MATCH_PARENT));
    refresh();
    mWindow.showActionModeBar(mBar, mHeight);
  }
  private Drawable background() {
    TypedValue tv = new TypedValue();
    try {
      if (mContext.getTheme().resolveAttribute(android.R.attr.actionModeBackground, tv, true) && tv.resourceId != 0) {
        Drawable d = mContext.getResources().getDrawable(tv.resourceId);
        if (d != null) return d;
      }
    } catch (RuntimeException e) { /* default */ }
    return new android.graphics.drawable.ColorDrawable(mTextColor == 0xff000000 ? 0xffe0e0e0 : 0xff2b2b2b);
  }
  /** the items that did not fit on the bar */
  private MenuBuilder overflowMenu() {
    MenuBuilder m = new MenuBuilder(mContext);
    m.setCallback((mm, item) -> mCallback.onActionItemClicked(this, item));
    int shown = mActions.getChildCount();
    int i = 0;
    for (MenuItemImpl it : mMenu.visibleItems()) {
      if (onBar(it) && i < shown) { i++; continue; }
      MenuItem copy = m.add(it.getGroupId(), it.getItemId(), it.getOrder(), it.getTitle());
      copy.setEnabled(it.isEnabled());
      copy.setCheckable(it.isCheckable());
      copy.setChecked(it.isChecked());
      final MenuItemImpl orig = it;
      copy.setOnMenuItemClickListener(x -> { mMenu.performIdentifierAction(orig.getItemId(), 0); return true; });
    }
    return m;
  }
  private static boolean onBar(MenuItemImpl it) { return (it.showAsAction() & (MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_IF_ROOM)) != 0; }
  private void refresh() {
    if (mBar == null) return;
    boolean custom = mCustomView != null;
    mCustomFrame.removeAllViews();
    if (custom) { if (mCustomView.getParent() instanceof ViewGroup) ((ViewGroup) mCustomView.getParent()).removeView(mCustomView); mCustomFrame.addView(mCustomView); }
    mCustomFrame.setVisibility(custom ? View.VISIBLE : View.GONE);
    mTitles.setVisibility(custom ? View.GONE : View.VISIBLE);
    mTitleView.setText(mTitle);
    mSubtitleView.setText(mSubtitle);
    mSubtitleView.setVisibility(TextUtils.isEmpty(mSubtitle) ? View.GONE : View.VISIBLE);
    mActions.removeAllViews();
    boolean overflow = false;
    int room = Math.max(1, (Screen.W / 2) / mHeight);
    for (MenuItemImpl it : mMenu.visibleItems()) {
      int s = it.showAsAction();
      boolean always = (s & MenuItem.SHOW_AS_ACTION_ALWAYS) != 0, ifRoom = (s & MenuItem.SHOW_AS_ACTION_IF_ROOM) != 0;
      if ((always || ifRoom && room > 0) && mActions.getChildCount() < 6) { mActions.addView(itemView(it, false), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)); room--; }
      else overflow = true;
    }
    mOverflow.setVisibility(overflow ? View.VISIBLE : View.GONE);
  }
  private View itemView(final MenuItemImpl it, boolean labelOnly) {
    View av = labelOnly ? null : it.getActionView();
    if (av != null) { if (av.getParent() instanceof ViewGroup) ((ViewGroup) av.getParent()).removeView(av); return av; }
    Drawable icon = labelOnly ? null : it.getIcon();
    View v;
    if (icon != null && (it.showAsAction() & MenuItem.SHOW_AS_ACTION_WITH_TEXT) == 0) {
      ImageView iv = new ImageView(mContext);
      iv.setImageDrawable(icon);
      iv.setScaleType(ImageView.ScaleType.CENTER);
      iv.setMinimumWidth(mHeight);
      v = iv;
    } else {
      TextView t = new TextView(mContext);
      CharSequence title = it.getTitleCondensed() != null ? it.getTitleCondensed() : it.getTitle();
      t.setText(title == null ? "" : labelOnly ? title.toString() : title.toString().toUpperCase());
      t.setTextColor(labelOnly ? 0xffffffff : mTextColor);
      t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
      t.setGravity(Gravity.CENTER);
      int p = Math.round(12 * Screen.DENSITY);
      t.setPadding(p, 0, p, 0);
      if (icon != null) t.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
      v = t;
    }
    v.setEnabled(it.isEnabled());
    v.setOnClickListener(x -> mMenu.performIdentifierAction(it.getItemId(), 0));
    v.setOnLongClickListener(x -> { android.widget.Toast.makeText(mContext, it.getTitle(), android.widget.Toast.LENGTH_SHORT).show(); return true; });
    return v;
  }

  // ---------------------------------------------------------------- the floating toolbar
  private View mFloating;
  private void showFloating() {
    LinearLayout bar = new LinearLayout(mContext);
    bar.setOrientation(LinearLayout.HORIZONTAL);
    GradientDrawable bg = new GradientDrawable();
    bg.setColor(0xf0303030);
    bg.setCornerRadius(4 * Screen.DENSITY);
    bar.setBackgroundDrawable(bg);
    int h = Math.round(44 * Screen.DENSITY);
    for (MenuItemImpl it : mMenu.visibleItems()) bar.addView(itemView(it, true), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, h));
    if (bar.getChildCount() == 0) return;
    Rect content = new Rect();
    if (mOriginalView != null) {
      if (mCallback instanceof ActionMode.Callback2) ((ActionMode.Callback2) mCallback).onGetContentRect(this, mOriginalView, content);
      else content.set(0, 0, mOriginalView.getWidth(), mOriginalView.getHeight());
      int[] loc = new int[2];
      mOriginalView.getLocationOnScreen(loc);
      content.offset(loc[0], loc[1]);
    }
    WindowManager.LayoutParams lp = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.TYPE_APPLICATION_PANEL, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
    lp.gravity = Gravity.TOP | Gravity.LEFT;
    bar.measure(View.MeasureSpec.makeMeasureSpec(Screen.W, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY));
    int w = bar.getMeasuredWidth(), margin = Math.round(8 * Screen.DENSITY);
    lp.x = Math.max(0, Math.min(Screen.W - w, content.centerX() - w / 2));
    lp.y = content.top - h - margin >= 0 ? content.top - h - margin : Math.min(Screen.H - h, content.bottom + margin);
    Windows.add(bar, lp, mWindow.root());
    mFloating = bar;
  }
  @Override public void invalidateContentRect() {
    if (mFloating == null || mFinished) return;
    Windows.remove(mFloating);
    mFloating = null;
    showFloating();
  }
  @Override public void hide(long duration) {
    if (mFloating == null) return;
    mFloating.setVisibility(View.INVISIBLE);
    long d = duration == DEFAULT_HIDE_DURATION ? 2000 : Math.min(duration, 3000);
    mFloating.postDelayed(() -> { if (mFloating != null) mFloating.setVisibility(View.VISIBLE); }, d);
  }
  @Override public boolean isTitleOptional() { return false; }

  // ---------------------------------------------------------------- ActionMode
  @Override public void finish() {
    if (mFinished) return;
    mFinished = true;
    mCallback.onDestroyActionMode(this);
    if (mBar != null) mWindow.hideActionModeBar(mBar);
    if (mFloating != null) { Windows.remove(mFloating); mFloating = null; }
    mWindow.actionModeFinished(this);
  }
  @Override public void invalidate() {
    if (mFinished) return;
    if (mCallback.onPrepareActionMode(this, mMenu)) {
      refresh();
      if (mFloating != null) invalidateContentRect();
    }
  }
  @Override public Menu getMenu() { return mMenu; }
  @Override public MenuInflater getMenuInflater() { return new MenuInflater(mContext); }
  @Override public void setTitle(CharSequence title) { mTitle = title; refresh(); }
  @Override public void setTitle(int resId) { setTitle(mContext.getText(resId)); }
  @Override public void setSubtitle(CharSequence subtitle) { mSubtitle = subtitle; refresh(); }
  @Override public void setSubtitle(int resId) { setSubtitle(mContext.getText(resId)); }
  @Override public CharSequence getTitle() { return mTitle; }
  @Override public CharSequence getSubtitle() { return mSubtitle; }
  @Override public void setCustomView(View view) { mCustomView = view; refresh(); }
  @Override public View getCustomView() { return mCustomView; }

  /** the done button: a check mark */
  static final class Done extends Drawable {
    final int color, size = Math.round(24 * Screen.DENSITY);
    Done(int color) { this.color = color; }
    @Override public void draw(Canvas c) {
      Rect b = getBounds();
      Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
      p.setColor(color);
      p.setStyle(Paint.Style.STROKE);
      p.setStrokeWidth(2.5f * Screen.DENSITY);
      float cx = b.exactCenterX(), cy = b.exactCenterY(), u = size / 24f;
      android.graphics.Path m = new android.graphics.Path();
      m.moveTo(cx - 7 * u, cy);
      m.lineTo(cx - 2 * u, cy + 5 * u);
      m.lineTo(cx + 8 * u, cy - 5 * u);
      c.drawPath(m, p);
    }
    @Override public void setAlpha(int a) {}
    @Override public void setColorFilter(ColorFilter cf) {}
    @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
    @Override public int getIntrinsicWidth() { return size; }
    @Override public int getIntrinsicHeight() { return size; }
  }
}

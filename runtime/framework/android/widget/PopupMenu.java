package android.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/** A menu in a popup anchored to a view. */
public class PopupMenu {
  public interface OnMenuItemClickListener { boolean onMenuItemClick(MenuItem item); }
  public interface OnDismissListener { void onDismiss(PopupMenu menu); }
  private final Context mContext;
  private final View mAnchor;
  private final a2w.MenuBuilder mMenu;
  private int mGravity;
  private OnMenuItemClickListener mClick;
  private OnDismissListener mDismiss;
  private ListPopupWindow mPopup;

  public PopupMenu(Context context, View anchor) { this(context, anchor, Gravity.NO_GRAVITY); }
  public PopupMenu(Context context, View anchor, int gravity) { this(context, anchor, gravity, 0, 0); }
  public PopupMenu(Context context, View anchor, int gravity, int popupStyleAttr, int popupStyleRes) {
    mContext = context; mAnchor = anchor; mGravity = gravity;
    mMenu = new a2w.MenuBuilder(context);
    mMenu.setCallback((menu, item) -> mClick != null && mClick.onMenuItemClick(item));
  }
  public void setGravity(int gravity) { mGravity = gravity; }
  public int getGravity() { return mGravity; }
  public View.OnTouchListener getDragToOpenListener() { return null; }
  public Menu getMenu() { return mMenu; }
  public MenuInflater getMenuInflater() { return new MenuInflater(mContext); }
  public void inflate(int menuRes) { getMenuInflater().inflate(menuRes, mMenu); }
  public void setForceShowIcon(boolean force) {}
  public void show() {
    final ArrayList<a2w.MenuItemImpl> items = mMenu.visibleItems();
    final ArrayList<CharSequence> titles = new ArrayList<>();
    for (a2w.MenuItemImpl it : items) titles.add(it.getTitle());
    mPopup = new ListPopupWindow(mContext);
    mPopup.setAnchorView(mAnchor);
    mPopup.setModal(true);
    mPopup.setDropDownGravity(mGravity);
    int w = Math.round(196 * a2w.Screen.DENSITY);
    mPopup.setWidth(Math.max(w, mAnchor != null ? mAnchor.getWidth() / 2 : w));
    mPopup.setAdapter(new ArrayAdapter<CharSequence>(mContext, android.R.layout.simple_list_item_1, titles));
    mPopup.setOnItemClickListener((p, v, pos, id) -> {
      a2w.MenuItemImpl it = items.get(pos);
      mPopup.dismiss();
      if (it.hasSubMenu()) { a2w.MenuPopup.show((a2w.MenuBuilder) it.getSubMenu()); return; }
      mMenu.performItemAction(it, 0);
    });
    mPopup.setOnDismissListener(() -> { if (mDismiss != null) mDismiss.onDismiss(this); });
    mPopup.show();
  }
  public void dismiss() { if (mPopup != null) mPopup.dismiss(); }
  public void setOnMenuItemClickListener(OnMenuItemClickListener l) { mClick = l; }
  public void setOnDismissListener(OnDismissListener l) { mDismiss = l; }
}

package a2w;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

/** Shows a menu: a list of its visible items in a dialog (the options menu, context menus, submenus). */
public final class MenuPopup {
  private MenuPopup() {}
  static final ArrayList<Dialog> open = new ArrayList<>();
  public static boolean isOpen() { return !open.isEmpty(); }
  public static void closeAll() { for (Dialog d : new ArrayList<>(open)) d.dismiss(); open.clear(); }
  public static Dialog show(MenuBuilder menu) { return show(menu, null); }
  public static Dialog show(MenuBuilder menu, Runnable onClose) {
    Context c = menu.getContext();
    final ArrayList<MenuItemImpl> items = menu.visibleItems();
    if (items.isEmpty()) return null;
    float d = c.getResources().getDisplayMetrics().density;
    BaseAdapter adapter = new BaseAdapter() {
      public int getCount() { return items.size(); }
      public Object getItem(int p) { return items.get(p); }
      public long getItemId(int p) { return items.get(p).getItemId(); }
      @Override public boolean isEnabled(int p) { return items.get(p).isEnabled(); }
      @Override public boolean areAllItemsEnabled() { return false; }
      public View getView(int p, View convert, ViewGroup parent) {
        MenuItemImpl it = items.get(p);
        LinearLayout row = new LinearLayout(c);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setMinimumHeight((int) (48 * d));
        row.setPadding((int) (8 * d), 0, (int) (8 * d), 0);
        android.graphics.drawable.Drawable icon = it.getIcon();
        if (icon != null) {
          ImageView iv = new ImageView(c);
          iv.setImageDrawable(icon);
          LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) (32 * d), (int) (32 * d));
          lp.rightMargin = (int) (12 * d);
          row.addView(iv, lp);
        }
        TextView t = new TextView(c);
        t.setText(it.getTitle() + (it.hasSubMenu() ? "  ›" : ""));
        t.setTextSize(18);
        t.setTextColor(it.isEnabled() ? 0xffffffff : 0xff888888);
        row.addView(t, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        if (it.isCheckable()) {
          CheckBox cb = new CheckBox(c);
          cb.setChecked(it.isChecked());
          cb.setClickable(false);
          cb.setFocusable(false);
          row.addView(cb);
        }
        return row;
      }
    };
    AlertDialog.Builder b = new AlertDialog.Builder(c);
    CharSequence title = menu.getHeaderTitle();
    if (!TextUtils.isEmpty(title)) b.setTitle(title);
    if (menu.mHeaderIcon != null) b.setIcon(menu.mHeaderIcon);
    if (menu.mHeaderView != null) b.setCustomTitle(menu.mHeaderView);
    final Dialog[] self = new Dialog[1];
    b.setAdapter(adapter, (dialog, which) -> {
      MenuItemImpl it = items.get(which);
      open.remove(self[0]);
      menu.performItemAction(it, 0);
      if (!it.hasSubMenu()) closeAll();
    });
    AlertDialog dlg = b.create();
    self[0] = dlg;
    dlg.setOnDismissListener(x -> { open.remove(dlg); if (onClose != null) onClose.run(); });
    open.add(dlg);
    dlg.show();
    return dlg;
  }
}

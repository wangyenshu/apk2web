package android.view;

import android.content.Context;
import a2w.Values;
import a2w.XmlNode;

/** Fills a Menu from a compiled menu XML (items, groups, submenus). */
public class MenuInflater {
  private final Context mContext;
  private final Object mRealOwner;
  public MenuInflater(Context context) { mContext = context; mRealOwner = context; }
  public void inflate(int menuRes, Menu menu) {
    XmlNode root = XmlNode.of(a2w.Res.xml(menuRes));
    if (root == null) throw new InflateException("Error inflating menu XML #0x" + Integer.toHexString(menuRes));
    parse(root, menu, 0, Menu.NONE, true, true, 0);
  }
  private void parse(XmlNode n, Menu menu, int groupId, int groupCategory, boolean groupVisible, boolean groupEnabled, int groupCheckable) {
    for (XmlNode c : n.children()) {
      switch (c.tag) {
        case "group": {
          int id = ref(c.a("id"));
          int cat = category(c.a("menuCategory"));
          String ch = c.a("checkableBehavior");
          int checkable = "all".equals(ch) ? 1 : "single".equals(ch) ? 2 : 0;
          parse(c, menu, id, cat, bool(c.a("visible"), true), bool(c.a("enabled"), true), checkable);
          if (checkable == 2) menu.setGroupCheckable(id, true, true);
          break;
        }
        case "item": {
          int id = ref(c.a("id"));
          int order = (category(c.a("menuCategory")) | groupCategory) & Menu.CATEGORY_MASK | (int2(c.a("orderInCategory"), 0) & Menu.USER_MASK);
          CharSequence title = text(c.a("title"));
          XmlNode sub = null;
          for (XmlNode k : c.children()) if (k.tag.equals("menu")) sub = k;
          MenuItem item;
          if (sub != null) {
            SubMenu sm = menu.addSubMenu(groupId, id, order, title);
            item = sm.getItem();
            parse(sub, sm, 0, Menu.NONE, true, true, 0);
          } else item = menu.add(groupId, id, order, title);
          String v;
          if ((v = c.a("titleCondensed")) != null) item.setTitleCondensed(text(v));
          if ((v = c.a("icon")) != null && Values.isRef(v)) { try { item.setIcon(Values.refId(v)); } catch (RuntimeException e) {} }
          if ((v = c.a("alphabeticShortcut")) != null && !v.isEmpty()) item.setAlphabeticShortcut(v.charAt(0));
          if ((v = c.a("numericShortcut")) != null && !v.isEmpty()) item.setNumericShortcut(v.charAt(0));
          String checkable = c.a("checkable");
          if (checkable != null ? bool(checkable, false) : groupCheckable != 0) item.setCheckable(true);
          if ((v = c.a("checked")) != null) item.setChecked(bool(v, false));
          item.setVisible(bool(c.a("visible"), groupVisible));
          item.setEnabled(bool(c.a("enabled"), groupEnabled));
          String sa = c.a("showAsAction");
          if (sa == null) sa = c.get("x", "showAsAction");
          if (sa != null) item.setShowAsAction(showAsAction(sa));
          if ((v = c.a("onClick")) != null && item instanceof a2w.MenuItemImpl) ((a2w.MenuItemImpl) item).setOnClickMethod(mRealOwner, v);
          if ((v = c.a("actionLayout")) != null && Values.isRef(v)) item.setActionView(Values.refId(v));
          break;
        }
        default: break;
      }
    }
  }
  private CharSequence text(String v) { return v == null ? null : Values.isRef(v) ? mContext.getResources().getText(Values.refId(v)) : v; }
  private static int ref(String v) { return v == null ? Menu.NONE : Values.refId(v); }
  private static boolean bool(String v, boolean d) { return v == null ? d : Values.parseBool(v, d); }
  private static int int2(String v, int d) { return Values.parseInt(v, d); }
  private static int category(String v) {
    if (v == null) return 0;
    switch (v) { case "container": return Menu.CATEGORY_CONTAINER; case "system": return Menu.CATEGORY_SYSTEM; case "secondary": return Menu.CATEGORY_SECONDARY; case "alternative": return Menu.CATEGORY_ALTERNATIVE; default: return 0; }
  }
  private static int showAsAction(String v) {
    try { return Integer.decode(v); } catch (NumberFormatException e) {}
    int f = 0;
    for (String s : v.split("\\|")) {
      switch (s.trim()) {
        case "ifRoom": f |= MenuItem.SHOW_AS_ACTION_IF_ROOM; break;
        case "always": f |= MenuItem.SHOW_AS_ACTION_ALWAYS; break;
        case "withText": f |= MenuItem.SHOW_AS_ACTION_WITH_TEXT; break;
        case "collapseActionView": f |= MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW; break;
      }
    }
    return f;
  }
}

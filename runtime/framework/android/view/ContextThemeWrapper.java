package android.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

public class ContextThemeWrapper extends ContextWrapper {
  int mThemeResource;
  Resources.Theme mTheme;
  LayoutInflater mInflater;
  public ContextThemeWrapper() { super(null); }
  public ContextThemeWrapper(Context base, int themeres) { super(base); mThemeResource = themeres; }
  @Override protected void attachBaseContext(Context b) { super.attachBaseContext(b); }
  @Override public void setTheme(int resid) { mThemeResource = resid; mTheme = null; }
  public int getThemeResId() { return mThemeResource; }
  @Override public Resources.Theme getTheme() {
    if (mTheme == null) {
      if (mThemeResource == 0) mThemeResource = a2w.Styles.defaultTheme();
      mTheme = getResources().newTheme();
      Context b = getBaseContext();
      Resources.Theme bt = b != null ? b.getTheme() : null;
      if (bt != null) mTheme.setTo(bt);
      onApplyThemeResource(mTheme, mThemeResource, true);
    }
    return mTheme;
  }
  public ContextThemeWrapper(Context base, Resources.Theme theme) { super(base); mTheme = getResources().newTheme(); if (theme != null) mTheme.setTo(theme); }
  protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) { theme.applyStyle(resid, true); }
  public void applyOverrideConfiguration(android.content.res.Configuration c) {}
  public android.content.res.Configuration getOverrideConfiguration() { return null; }
  @Override public Object getSystemService(String name) {
    if (LAYOUT_INFLATER_SERVICE.equals(name)) {
      if (mInflater == null) mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
      return mInflater;
    }
    return getBaseContext().getSystemService(name);
  }
}

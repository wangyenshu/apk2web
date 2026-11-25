package android.widget;
public interface ThemedSpinnerAdapter extends SpinnerAdapter {
  default void setDropDownViewTheme(android.content.res.Resources.Theme theme) {}
  default android.content.res.Resources.Theme getDropDownViewTheme() { return null; }
}

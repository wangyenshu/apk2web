package android.app;

public class UiModeManager {
  public static final int MODE_NIGHT_AUTO = 0, MODE_NIGHT_NO = 1, MODE_NIGHT_YES = 2, DISABLE_CAR_MODE_GO_HOME = 1, ENABLE_CAR_MODE_GO_CAR_HOME = 1;
  public static final String ACTION_ENTER_CAR_MODE = "android.app.action.ENTER_CAR_MODE", ACTION_EXIT_CAR_MODE = "android.app.action.EXIT_CAR_MODE";
  private int night = MODE_NIGHT_NO;
  public int getCurrentModeType() { return android.content.res.Configuration.UI_MODE_TYPE_NORMAL; }
  public int getNightMode() { return night; }
  public void setNightMode(int mode) { night = mode; }
  public void enableCarMode(int flags) {}
  public void disableCarMode(int flags) {}
}

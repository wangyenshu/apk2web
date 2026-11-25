package android.content.pm;
public class ActivityInfo extends PackageItemInfo {
  public static final int SCREEN_ORIENTATION_UNSPECIFIED = -1, SCREEN_ORIENTATION_LANDSCAPE = 0, SCREEN_ORIENTATION_PORTRAIT = 1,
      SCREEN_ORIENTATION_USER = 2, SCREEN_ORIENTATION_BEHIND = 3, SCREEN_ORIENTATION_SENSOR = 4, SCREEN_ORIENTATION_NOSENSOR = 5,
      SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6, SCREEN_ORIENTATION_SENSOR_PORTRAIT = 7, CONFIG_ORIENTATION = 128, CONFIG_KEYBOARD_HIDDEN = 32;
  public int theme, launchMode, screenOrientation = -1, configChanges, flags;
  public ApplicationInfo applicationInfo;
  public String targetActivity, taskAffinity, permission;
}

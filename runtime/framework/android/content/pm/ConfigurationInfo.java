package android.content.pm;
public class ConfigurationInfo {
  public static final int GL_ES_VERSION_UNDEFINED = 0, INPUT_FEATURE_HARD_KEYBOARD = 1, INPUT_FEATURE_FIVE_WAY_NAV = 2;
  public int reqGlEsVersion = 0x20000, reqTouchScreen = 3, reqKeyboardType, reqNavigation, reqInputFeatures;
  public String getGlEsVersion() { return "2.0"; }
}

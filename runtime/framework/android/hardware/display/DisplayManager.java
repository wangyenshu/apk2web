// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.hardware.display;

public final class DisplayManager {
  public static final java.lang.String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
  public static final int MATCH_CONTENT_FRAMERATE_ALWAYS = 2;
  public static final int MATCH_CONTENT_FRAMERATE_NEVER = 0;
  public static final int MATCH_CONTENT_FRAMERATE_SEAMLESSS_ONLY = 1;
  public static final int MATCH_CONTENT_FRAMERATE_UNKNOWN = -1;
  public static final int VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR = 16;
  public static final int VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY = 8;
  public static final int VIRTUAL_DISPLAY_FLAG_PRESENTATION = 2;
  public static final int VIRTUAL_DISPLAY_FLAG_PUBLIC = 1;
  public static final int VIRTUAL_DISPLAY_FLAG_SECURE = 4;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public DisplayManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.hardware.display.VirtualDisplay createVirtualDisplay(android.hardware.display.VirtualDisplayConfig config) { a2w.Stubs.hit("android.hardware.display.DisplayManager.createVirtualDisplay"); return null; }
  @a2w.NotImplemented public android.hardware.display.VirtualDisplay createVirtualDisplay(android.hardware.display.VirtualDisplayConfig config, android.os.Handler handler, android.hardware.display.VirtualDisplay.Callback callback) { a2w.Stubs.hit("android.hardware.display.DisplayManager.createVirtualDisplay"); return null; }
  @a2w.NotImplemented public android.hardware.display.VirtualDisplay createVirtualDisplay(java.lang.String name, int width, int height, int densityDpi, android.view.Surface surface, int flags) { a2w.Stubs.hit("android.hardware.display.DisplayManager.createVirtualDisplay"); return null; }
  @a2w.NotImplemented public android.hardware.display.VirtualDisplay createVirtualDisplay(java.lang.String name, int width, int height, int densityDpi, android.view.Surface surface, int flags, android.hardware.display.VirtualDisplay.Callback callback, android.os.Handler handler) { a2w.Stubs.hit("android.hardware.display.DisplayManager.createVirtualDisplay"); return null; }
  @a2w.NotImplemented public android.view.Display getDisplay(int displayId) { a2w.Stubs.hit("android.hardware.display.DisplayManager.getDisplay"); return null; }
  @a2w.NotImplemented public android.view.Display[] getDisplays() { a2w.Stubs.hit("android.hardware.display.DisplayManager.getDisplays"); return null; }
  @a2w.NotImplemented public android.view.Display[] getDisplays(java.lang.String category) { a2w.Stubs.hit("android.hardware.display.DisplayManager.getDisplays"); return null; }
  @a2w.NotImplemented public android.hardware.display.HdrConversionMode getHdrConversionMode() { a2w.Stubs.hit("android.hardware.display.DisplayManager.getHdrConversionMode"); return null; }
  @a2w.NotImplemented public int getMatchContentFrameRateUserPreference() { a2w.Stubs.hit("android.hardware.display.DisplayManager.getMatchContentFrameRateUserPreference"); return 0; }
  @a2w.NotImplemented public void registerDisplayListener(android.hardware.display.DisplayManager.DisplayListener listener, android.os.Handler handler) { a2w.Stubs.hit("android.hardware.display.DisplayManager.registerDisplayListener"); }
  @a2w.NotImplemented public void unregisterDisplayListener(android.hardware.display.DisplayManager.DisplayListener listener) { a2w.Stubs.hit("android.hardware.display.DisplayManager.unregisterDisplayListener"); }

    public interface DisplayListener {
    void onDisplayAdded(int p0);
    void onDisplayChanged(int p0);
    void onDisplayRemoved(int p0);
  }
}

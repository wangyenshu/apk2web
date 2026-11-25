// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.view.accessibility;

public class CaptioningManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public CaptioningManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void addCaptioningChangeListener(android.view.accessibility.CaptioningManager.CaptioningChangeListener listener) { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.addCaptioningChangeListener"); }
  @a2w.NotImplemented public final float getFontScale() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.getFontScale"); return 0f; }
  @a2w.NotImplemented public final java.util.Locale getLocale() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.getLocale"); return null; }
  @a2w.NotImplemented public android.view.accessibility.CaptioningManager.CaptionStyle getUserStyle() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.getUserStyle"); return null; }
  @a2w.NotImplemented public boolean isCallCaptioningEnabled() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.isCallCaptioningEnabled"); return false; }
  @a2w.NotImplemented public final boolean isEnabled() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.isEnabled"); return false; }
  @a2w.NotImplemented public final boolean isSystemAudioCaptioningEnabled() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.isSystemAudioCaptioningEnabled"); return false; }
  @a2w.NotImplemented public final boolean isSystemAudioCaptioningUiEnabled() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.isSystemAudioCaptioningUiEnabled"); return false; }
  @a2w.NotImplemented public void removeCaptioningChangeListener(android.view.accessibility.CaptioningManager.CaptioningChangeListener listener) { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.removeCaptioningChangeListener"); }

    public static final class CaptionStyle {
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int EDGE_TYPE_UNSPECIFIED = -1;
    @a2w.NotImplemented public final int backgroundColor = 0;
    @a2w.NotImplemented public final int edgeColor = 0;
    @a2w.NotImplemented public final int edgeType = 0;
    @a2w.NotImplemented public final int foregroundColor = 0;
    @a2w.NotImplemented public final int windowColor = 0;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public CaptionStyle(a2w.Hidden hidden) { }
    @a2w.NotImplemented public android.graphics.Typeface getTypeface() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.CaptionStyle.getTypeface"); return null; }
    @a2w.NotImplemented public boolean hasBackgroundColor() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.CaptionStyle.hasBackgroundColor"); return false; }
    @a2w.NotImplemented public boolean hasEdgeColor() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.CaptionStyle.hasEdgeColor"); return false; }
    @a2w.NotImplemented public boolean hasEdgeType() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.CaptionStyle.hasEdgeType"); return false; }
    @a2w.NotImplemented public boolean hasForegroundColor() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.CaptionStyle.hasForegroundColor"); return false; }
    @a2w.NotImplemented public boolean hasWindowColor() { a2w.Stubs.hit("android.view.accessibility.CaptioningManager.CaptionStyle.hasWindowColor"); return false; }
  }

    public static abstract class CaptioningChangeListener {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public CaptioningChangeListener(a2w.Hidden hidden) { }
    public CaptioningChangeListener() { this((a2w.Hidden) null); }
    public void onEnabledChanged(boolean enabled) { }
    public void onFontScaleChanged(float fontScale) { }
    public void onLocaleChanged(java.util.Locale locale) { }
    public void onSystemAudioCaptioningChanged(boolean enabled) { }
    public void onSystemAudioCaptioningUiChanged(boolean enabled) { }
    public void onUserStyleChanged(android.view.accessibility.CaptioningManager.CaptionStyle userStyle) { }
  }
}

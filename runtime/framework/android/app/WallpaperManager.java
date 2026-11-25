// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app;

public class WallpaperManager {
  public static final java.lang.String ACTION_CHANGE_LIVE_WALLPAPER = "android.service.wallpaper.CHANGE_LIVE_WALLPAPER";
  public static final java.lang.String ACTION_CROP_AND_SET_WALLPAPER = "android.service.wallpaper.CROP_AND_SET_WALLPAPER";
  public static final java.lang.String ACTION_LIVE_WALLPAPER_CHOOSER = "android.service.wallpaper.LIVE_WALLPAPER_CHOOSER";
  public static final java.lang.String COMMAND_DROP = "android.home.drop";
  public static final java.lang.String COMMAND_SECONDARY_TAP = "android.wallpaper.secondaryTap";
  public static final java.lang.String COMMAND_TAP = "android.wallpaper.tap";
  public static final java.lang.String EXTRA_LIVE_WALLPAPER_COMPONENT = "android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT";
  public static final int FLAG_LOCK = 2;
  public static final int FLAG_SYSTEM = 1;
  public static final java.lang.String WALLPAPER_PREVIEW_META_DATA = "android.wallpaper.preview";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public WallpaperManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void addOnColorsChangedListener(android.app.WallpaperManager.OnColorsChangedListener listener, android.os.Handler handler) { a2w.Stubs.hit("android.app.WallpaperManager.addOnColorsChangedListener"); }
  @a2w.NotImplemented public void clear() throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.clear"); }
  @a2w.NotImplemented public void clear(int which) throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.clear"); }
  @a2w.NotImplemented public void clearWallpaper() { a2w.Stubs.hit("android.app.WallpaperManager.clearWallpaper"); }
  @a2w.NotImplemented public void clearWallpaperOffsets(android.os.IBinder windowToken) { a2w.Stubs.hit("android.app.WallpaperManager.clearWallpaperOffsets"); }
  @a2w.NotImplemented public void forgetLoadedWallpaper() { a2w.Stubs.hit("android.app.WallpaperManager.forgetLoadedWallpaper"); }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getBuiltInDrawable() { a2w.Stubs.hit("android.app.WallpaperManager.getBuiltInDrawable"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getBuiltInDrawable(int which) { a2w.Stubs.hit("android.app.WallpaperManager.getBuiltInDrawable"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getBuiltInDrawable(int outWidth, int outHeight, boolean scaleToFit, float horizontalAlignment, float verticalAlignment) { a2w.Stubs.hit("android.app.WallpaperManager.getBuiltInDrawable"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getBuiltInDrawable(int outWidth, int outHeight, boolean scaleToFit, float horizontalAlignment, float verticalAlignment, int which) { a2w.Stubs.hit("android.app.WallpaperManager.getBuiltInDrawable"); return null; }
  @a2w.NotImplemented public android.content.Intent getCropAndSetWallpaperIntent(android.net.Uri imageUri) { a2w.Stubs.hit("android.app.WallpaperManager.getCropAndSetWallpaperIntent"); return null; }
  @a2w.NotImplemented public int getDesiredMinimumHeight() { a2w.Stubs.hit("android.app.WallpaperManager.getDesiredMinimumHeight"); return 0; }
  @a2w.NotImplemented public int getDesiredMinimumWidth() { a2w.Stubs.hit("android.app.WallpaperManager.getDesiredMinimumWidth"); return 0; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getDrawable() { a2w.Stubs.hit("android.app.WallpaperManager.getDrawable"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getDrawable(int which) { a2w.Stubs.hit("android.app.WallpaperManager.getDrawable"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getFastDrawable() { a2w.Stubs.hit("android.app.WallpaperManager.getFastDrawable"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getFastDrawable(int which) { a2w.Stubs.hit("android.app.WallpaperManager.getFastDrawable"); return null; }
  @a2w.NotImplemented public static android.app.WallpaperManager getInstance(android.content.Context context) { a2w.Stubs.hit("android.app.WallpaperManager.getInstance"); return null; }
  @a2w.NotImplemented public android.app.WallpaperColors getWallpaperColors(int which) { a2w.Stubs.hit("android.app.WallpaperManager.getWallpaperColors"); return null; }
  @a2w.NotImplemented public android.os.ParcelFileDescriptor getWallpaperFile(int which) { a2w.Stubs.hit("android.app.WallpaperManager.getWallpaperFile"); return null; }
  @a2w.NotImplemented public int getWallpaperId(int which) { a2w.Stubs.hit("android.app.WallpaperManager.getWallpaperId"); return 0; }
  @a2w.NotImplemented public android.app.WallpaperInfo getWallpaperInfo() { a2w.Stubs.hit("android.app.WallpaperManager.getWallpaperInfo"); return null; }
  @a2w.NotImplemented public android.app.WallpaperInfo getWallpaperInfo(int which) { a2w.Stubs.hit("android.app.WallpaperManager.getWallpaperInfo"); return null; }
  @a2w.NotImplemented public boolean hasResourceWallpaper(int resid) { a2w.Stubs.hit("android.app.WallpaperManager.hasResourceWallpaper"); return false; }
  @a2w.NotImplemented public boolean isSetWallpaperAllowed() { a2w.Stubs.hit("android.app.WallpaperManager.isSetWallpaperAllowed"); return false; }
  @a2w.NotImplemented public boolean isWallpaperSupported() { a2w.Stubs.hit("android.app.WallpaperManager.isWallpaperSupported"); return false; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable peekDrawable() { a2w.Stubs.hit("android.app.WallpaperManager.peekDrawable"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable peekDrawable(int which) { a2w.Stubs.hit("android.app.WallpaperManager.peekDrawable"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable peekFastDrawable() { a2w.Stubs.hit("android.app.WallpaperManager.peekFastDrawable"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable peekFastDrawable(int which) { a2w.Stubs.hit("android.app.WallpaperManager.peekFastDrawable"); return null; }
  @a2w.NotImplemented public void removeOnColorsChangedListener(android.app.WallpaperManager.OnColorsChangedListener callback) { a2w.Stubs.hit("android.app.WallpaperManager.removeOnColorsChangedListener"); }
  @a2w.NotImplemented public void sendWallpaperCommand(android.os.IBinder windowToken, java.lang.String action, int x, int y, int z, android.os.Bundle extras) { a2w.Stubs.hit("android.app.WallpaperManager.sendWallpaperCommand"); }
  @a2w.NotImplemented public void setBitmap(android.graphics.Bitmap bitmap) throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.setBitmap"); }
  @a2w.NotImplemented public int setBitmap(android.graphics.Bitmap fullImage, android.graphics.Rect visibleCropHint, boolean allowBackup) throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.setBitmap"); return 0; }
  @a2w.NotImplemented public int setBitmap(android.graphics.Bitmap fullImage, android.graphics.Rect visibleCropHint, boolean allowBackup, int which) throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.setBitmap"); return 0; }
  @a2w.NotImplemented public void setDisplayPadding(android.graphics.Rect padding) { a2w.Stubs.hit("android.app.WallpaperManager.setDisplayPadding"); }
  @a2w.NotImplemented public void setResource(int resid) throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.setResource"); }
  @a2w.NotImplemented public int setResource(int resid, int which) throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.setResource"); return 0; }
  @a2w.NotImplemented public void setStream(java.io.InputStream bitmapData) throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.setStream"); }
  @a2w.NotImplemented public int setStream(java.io.InputStream bitmapData, android.graphics.Rect visibleCropHint, boolean allowBackup) throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.setStream"); return 0; }
  @a2w.NotImplemented public int setStream(java.io.InputStream bitmapData, android.graphics.Rect visibleCropHint, boolean allowBackup, int which) throws java.io.IOException { a2w.Stubs.hit("android.app.WallpaperManager.setStream"); return 0; }
  @a2w.NotImplemented public void setWallpaperOffsetSteps(float xStep, float yStep) { a2w.Stubs.hit("android.app.WallpaperManager.setWallpaperOffsetSteps"); }
  @a2w.NotImplemented public void setWallpaperOffsets(android.os.IBinder windowToken, float xOffset, float yOffset) { a2w.Stubs.hit("android.app.WallpaperManager.setWallpaperOffsets"); }
  @a2w.NotImplemented public void suggestDesiredDimensions(int minimumWidth, int minimumHeight) { a2w.Stubs.hit("android.app.WallpaperManager.suggestDesiredDimensions"); }

    public interface OnColorsChangedListener {
    void onColorsChanged(android.app.WallpaperColors p0, int p1);
  }
}

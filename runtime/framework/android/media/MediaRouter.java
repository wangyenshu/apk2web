// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.media;

public class MediaRouter {
  public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
  public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
  public static final int ROUTE_TYPE_LIVE_AUDIO = 1;
  public static final int ROUTE_TYPE_LIVE_VIDEO = 2;
  public static final int ROUTE_TYPE_USER = 8388608;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public MediaRouter(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void addCallback(int types, android.media.MediaRouter.Callback cb) { a2w.Stubs.hit("android.media.MediaRouter.addCallback"); }
  @a2w.NotImplemented public void addCallback(int types, android.media.MediaRouter.Callback cb, int flags) { a2w.Stubs.hit("android.media.MediaRouter.addCallback"); }
  @a2w.NotImplemented public void addUserRoute(android.media.MediaRouter.UserRouteInfo info) { a2w.Stubs.hit("android.media.MediaRouter.addUserRoute"); }
  @a2w.NotImplemented public void clearUserRoutes() { a2w.Stubs.hit("android.media.MediaRouter.clearUserRoutes"); }
  @a2w.NotImplemented public android.media.MediaRouter.RouteCategory createRouteCategory(int nameResId, boolean isGroupable) { a2w.Stubs.hit("android.media.MediaRouter.createRouteCategory"); return null; }
  @a2w.NotImplemented public android.media.MediaRouter.RouteCategory createRouteCategory(java.lang.CharSequence name, boolean isGroupable) { a2w.Stubs.hit("android.media.MediaRouter.createRouteCategory"); return null; }
  @a2w.NotImplemented public android.media.MediaRouter.UserRouteInfo createUserRoute(android.media.MediaRouter.RouteCategory category) { a2w.Stubs.hit("android.media.MediaRouter.createUserRoute"); return null; }
  @a2w.NotImplemented public android.media.MediaRouter.RouteCategory getCategoryAt(int index) { a2w.Stubs.hit("android.media.MediaRouter.getCategoryAt"); return null; }
  @a2w.NotImplemented public int getCategoryCount() { a2w.Stubs.hit("android.media.MediaRouter.getCategoryCount"); return 0; }
  @a2w.NotImplemented public android.media.MediaRouter.RouteInfo getDefaultRoute() { a2w.Stubs.hit("android.media.MediaRouter.getDefaultRoute"); return null; }
  @a2w.NotImplemented public android.media.MediaRouter.RouteInfo getRouteAt(int index) { a2w.Stubs.hit("android.media.MediaRouter.getRouteAt"); return null; }
  @a2w.NotImplemented public int getRouteCount() { a2w.Stubs.hit("android.media.MediaRouter.getRouteCount"); return 0; }
  @a2w.NotImplemented public android.media.MediaRouter.RouteInfo getSelectedRoute(int type) { a2w.Stubs.hit("android.media.MediaRouter.getSelectedRoute"); return null; }
  @a2w.NotImplemented public void removeCallback(android.media.MediaRouter.Callback cb) { a2w.Stubs.hit("android.media.MediaRouter.removeCallback"); }
  @a2w.NotImplemented public void removeUserRoute(android.media.MediaRouter.UserRouteInfo info) { a2w.Stubs.hit("android.media.MediaRouter.removeUserRoute"); }
  @a2w.NotImplemented public void selectRoute(int types, android.media.MediaRouter.RouteInfo route) { a2w.Stubs.hit("android.media.MediaRouter.selectRoute"); }

    public static abstract class Callback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public Callback(a2w.Hidden hidden) { }
    public Callback() { this((a2w.Hidden) null); }
    public abstract void onRouteAdded(android.media.MediaRouter p0, android.media.MediaRouter.RouteInfo p1);
    public abstract void onRouteChanged(android.media.MediaRouter p0, android.media.MediaRouter.RouteInfo p1);
    public abstract void onRouteGrouped(android.media.MediaRouter p0, android.media.MediaRouter.RouteInfo p1, android.media.MediaRouter.RouteGroup p2, int p3);
    public void onRoutePresentationDisplayChanged(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo info) { }
    public abstract void onRouteRemoved(android.media.MediaRouter p0, android.media.MediaRouter.RouteInfo p1);
    public abstract void onRouteSelected(android.media.MediaRouter p0, int p1, android.media.MediaRouter.RouteInfo p2);
    public abstract void onRouteUngrouped(android.media.MediaRouter p0, android.media.MediaRouter.RouteInfo p1, android.media.MediaRouter.RouteGroup p2);
    public abstract void onRouteUnselected(android.media.MediaRouter p0, int p1, android.media.MediaRouter.RouteInfo p2);
    public abstract void onRouteVolumeChanged(android.media.MediaRouter p0, android.media.MediaRouter.RouteInfo p1);
  }

    public static class RouteCategory {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public RouteCategory(a2w.Hidden hidden) { }
    @a2w.NotImplemented public java.lang.CharSequence getName() { a2w.Stubs.hit("android.media.MediaRouter.RouteCategory.getName"); return null; }
    @a2w.NotImplemented public java.lang.CharSequence getName(android.content.Context context) { a2w.Stubs.hit("android.media.MediaRouter.RouteCategory.getName"); return null; }
    @a2w.NotImplemented public java.util.List<android.media.MediaRouter.RouteInfo> getRoutes(java.util.List<android.media.MediaRouter.RouteInfo> out) { a2w.Stubs.hit("android.media.MediaRouter.RouteCategory.getRoutes"); return null; }
    @a2w.NotImplemented public int getSupportedTypes() { a2w.Stubs.hit("android.media.MediaRouter.RouteCategory.getSupportedTypes"); return 0; }
    @a2w.NotImplemented public boolean isGroupable() { a2w.Stubs.hit("android.media.MediaRouter.RouteCategory.isGroupable"); return false; }
    @a2w.NotImplemented public java.lang.String toString() { a2w.Stubs.hit("android.media.MediaRouter.RouteCategory.toString"); return super.toString(); }
  }

    public static class RouteGroup extends android.media.MediaRouter.RouteInfo {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public RouteGroup(a2w.Hidden hidden) { super((a2w.Hidden) null); }
    @a2w.NotImplemented public void addRoute(android.media.MediaRouter.RouteInfo route) { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.addRoute"); }
    @a2w.NotImplemented public void addRoute(android.media.MediaRouter.RouteInfo route, int insertAt) { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.addRoute"); }
    @a2w.NotImplemented public android.media.MediaRouter.RouteInfo getRouteAt(int index) { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.getRouteAt"); return null; }
    @a2w.NotImplemented public int getRouteCount() { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.getRouteCount"); return 0; }
    @a2w.NotImplemented public void removeRoute(int index) { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.removeRoute"); }
    @a2w.NotImplemented public void removeRoute(android.media.MediaRouter.RouteInfo route) { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.removeRoute"); }
    @a2w.NotImplemented public void requestSetVolume(int volume) { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.requestSetVolume"); super.requestSetVolume(volume); }
    @a2w.NotImplemented public void requestUpdateVolume(int direction) { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.requestUpdateVolume"); super.requestUpdateVolume(direction); }
    @a2w.Derived public void setIconDrawable(android.graphics.drawable.Drawable icon) { this.a2w$iconDrawable = icon; }
    @a2w.NotImplemented public void setIconResource(int resId) { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.setIconResource"); }
    @a2w.NotImplemented public java.lang.String toString() { a2w.Stubs.hit("android.media.MediaRouter.RouteGroup.toString"); return super.toString(); }
  }

    public static class RouteInfo {
    public static final int DEVICE_TYPE_BLUETOOTH = 3;
    public static final int DEVICE_TYPE_SPEAKER = 2;
    public static final int DEVICE_TYPE_TV = 1;
    public static final int DEVICE_TYPE_UNKNOWN = 0;
    public static final int PLAYBACK_TYPE_LOCAL = 0;
    public static final int PLAYBACK_TYPE_REMOTE = 1;
    public static final int PLAYBACK_VOLUME_FIXED = 0;
    public static final int PLAYBACK_VOLUME_VARIABLE = 1;
    /** (state of the property description, not part of the API) */ public java.lang.CharSequence a2w$description;
    /** (state of the property iconDrawable, not part of the API) */ public android.graphics.drawable.Drawable a2w$iconDrawable;
    /** (state of the property name, not part of the API) */ public java.lang.CharSequence a2w$name;
    /** (state of the property playbackStream, not part of the API) */ public int a2w$playbackStream;
    /** (state of the property playbackType, not part of the API) */ public int a2w$playbackType;
    /** (state of the property status, not part of the API) */ public java.lang.CharSequence a2w$status;
    /** (state of the property tag, not part of the API) */ public java.lang.Object a2w$tag;
    /** (state of the property volume, not part of the API) */ public int a2w$volume;
    /** (state of the property volumeHandling, not part of the API) */ public int a2w$volumeHandling;
    /** (state of the property volumeMax, not part of the API) */ public int a2w$volumeMax;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public RouteInfo(a2w.Hidden hidden) { }
    @a2w.NotImplemented public android.media.MediaRouter.RouteCategory getCategory() { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.getCategory"); return null; }
    @a2w.Derived public java.lang.CharSequence getDescription() { return this.a2w$description; }
    @a2w.NotImplemented public int getDeviceType() { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.getDeviceType"); return 0; }
    @a2w.NotImplemented public android.media.MediaRouter.RouteGroup getGroup() { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.getGroup"); return null; }
    @a2w.Derived public android.graphics.drawable.Drawable getIconDrawable() { return this.a2w$iconDrawable; }
    @a2w.Derived public java.lang.CharSequence getName() { return this.a2w$name; }
    @a2w.NotImplemented public java.lang.CharSequence getName(android.content.Context context) { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.getName"); return null; }
    @a2w.Derived public int getPlaybackStream() { return this.a2w$playbackStream; }
    @a2w.Derived public int getPlaybackType() { return this.a2w$playbackType; }
    @a2w.NotImplemented public android.view.Display getPresentationDisplay() { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.getPresentationDisplay"); return null; }
    @a2w.Derived public java.lang.CharSequence getStatus() { return this.a2w$status; }
    @a2w.NotImplemented public int getSupportedTypes() { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.getSupportedTypes"); return 0; }
    @a2w.Derived public java.lang.Object getTag() { return this.a2w$tag; }
    @a2w.Derived public int getVolume() { return this.a2w$volume; }
    @a2w.Derived public int getVolumeHandling() { return this.a2w$volumeHandling; }
    @a2w.Derived public int getVolumeMax() { return this.a2w$volumeMax; }
    @a2w.NotImplemented public boolean isConnecting() { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.isConnecting"); return false; }
    @a2w.NotImplemented public boolean isEnabled() { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.isEnabled"); return false; }
    @a2w.NotImplemented public void requestSetVolume(int volume) { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.requestSetVolume"); }
    @a2w.NotImplemented public void requestUpdateVolume(int direction) { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.requestUpdateVolume"); }
    @a2w.Derived public void setTag(java.lang.Object tag) { this.a2w$tag = tag; }
    @a2w.NotImplemented public java.lang.String toString() { a2w.Stubs.hit("android.media.MediaRouter.RouteInfo.toString"); return super.toString(); }
  }

    public static class SimpleCallback extends android.media.MediaRouter.Callback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public SimpleCallback(a2w.Hidden hidden) { super((a2w.Hidden) null); }
    public SimpleCallback() { this((a2w.Hidden) null); }
    public void onRouteAdded(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo info) { }
    public void onRouteChanged(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo info) { }
    public void onRouteGrouped(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo info, android.media.MediaRouter.RouteGroup group, int index) { }
    public void onRouteRemoved(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo info) { }
    public void onRouteSelected(android.media.MediaRouter router, int type, android.media.MediaRouter.RouteInfo info) { }
    public void onRouteUngrouped(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo info, android.media.MediaRouter.RouteGroup group) { }
    public void onRouteUnselected(android.media.MediaRouter router, int type, android.media.MediaRouter.RouteInfo info) { }
    public void onRouteVolumeChanged(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo info) { }
  }

    public static class UserRouteInfo extends android.media.MediaRouter.RouteInfo {
    /** (state of the property remoteControlClient, not part of the API) */ public android.media.RemoteControlClient a2w$remoteControlClient;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public UserRouteInfo(a2w.Hidden hidden) { super((a2w.Hidden) null); }
    @a2w.Derived public android.media.RemoteControlClient getRemoteControlClient() { return this.a2w$remoteControlClient; }
    @a2w.NotImplemented public void requestSetVolume(int volume) { a2w.Stubs.hit("android.media.MediaRouter.UserRouteInfo.requestSetVolume"); super.requestSetVolume(volume); }
    @a2w.NotImplemented public void requestUpdateVolume(int direction) { a2w.Stubs.hit("android.media.MediaRouter.UserRouteInfo.requestUpdateVolume"); super.requestUpdateVolume(direction); }
    @a2w.Derived public void setDescription(java.lang.CharSequence description) { this.a2w$description = description; }
    @a2w.Derived public void setIconDrawable(android.graphics.drawable.Drawable icon) { this.a2w$iconDrawable = icon; }
    @a2w.NotImplemented public void setIconResource(int resId) { a2w.Stubs.hit("android.media.MediaRouter.UserRouteInfo.setIconResource"); }
    @a2w.NotImplemented public void setName(int resId) { a2w.Stubs.hit("android.media.MediaRouter.UserRouteInfo.setName"); }
    @a2w.Derived public void setName(java.lang.CharSequence name) { this.a2w$name = name; }
    @a2w.Derived public void setPlaybackStream(int stream) { this.a2w$playbackStream = stream; }
    @a2w.Derived public void setPlaybackType(int type) { this.a2w$playbackType = type; }
    @a2w.Derived public void setRemoteControlClient(android.media.RemoteControlClient rcc) { this.a2w$remoteControlClient = rcc; }
    @a2w.Derived public void setStatus(java.lang.CharSequence status) { this.a2w$status = status; }
    @a2w.Derived public void setVolume(int volume) { this.a2w$volume = volume; }
    @a2w.NotImplemented public void setVolumeCallback(android.media.MediaRouter.VolumeCallback vcb) { a2w.Stubs.hit("android.media.MediaRouter.UserRouteInfo.setVolumeCallback"); }
    @a2w.Derived public void setVolumeHandling(int volumeHandling) { this.a2w$volumeHandling = volumeHandling; }
    @a2w.Derived public void setVolumeMax(int volumeMax) { this.a2w$volumeMax = volumeMax; }
  }

    public static abstract class VolumeCallback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public VolumeCallback(a2w.Hidden hidden) { }
    public VolumeCallback() { this((a2w.Hidden) null); }
    public abstract void onVolumeSetRequest(android.media.MediaRouter.RouteInfo p0, int p1);
    public abstract void onVolumeUpdateRequest(android.media.MediaRouter.RouteInfo p0, int p1);
  }
}

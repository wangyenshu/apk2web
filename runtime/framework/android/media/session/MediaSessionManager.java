// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.media.session;

public final class MediaSessionManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public MediaSessionManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void addOnActiveSessionsChangedListener(android.media.session.MediaSessionManager.OnActiveSessionsChangedListener sessionListener, android.content.ComponentName notificationListener) { a2w.Stubs.hit("android.media.session.MediaSessionManager.addOnActiveSessionsChangedListener"); }
  @a2w.NotImplemented public void addOnActiveSessionsChangedListener(android.media.session.MediaSessionManager.OnActiveSessionsChangedListener sessionListener, android.content.ComponentName notificationListener, android.os.Handler handler) { a2w.Stubs.hit("android.media.session.MediaSessionManager.addOnActiveSessionsChangedListener"); }
  @a2w.NotImplemented public void addOnMediaKeyEventSessionChangedListener(java.util.concurrent.Executor executor, android.media.session.MediaSessionManager.OnMediaKeyEventSessionChangedListener listener) { a2w.Stubs.hit("android.media.session.MediaSessionManager.addOnMediaKeyEventSessionChangedListener"); }
  @a2w.NotImplemented public void addOnSession2TokensChangedListener(android.media.session.MediaSessionManager.OnSession2TokensChangedListener listener) { a2w.Stubs.hit("android.media.session.MediaSessionManager.addOnSession2TokensChangedListener"); }
  @a2w.NotImplemented public void addOnSession2TokensChangedListener(android.media.session.MediaSessionManager.OnSession2TokensChangedListener listener, android.os.Handler handler) { a2w.Stubs.hit("android.media.session.MediaSessionManager.addOnSession2TokensChangedListener"); }
  @a2w.NotImplemented public java.util.List<android.media.session.MediaController> getActiveSessions(android.content.ComponentName notificationListener) { a2w.Stubs.hit("android.media.session.MediaSessionManager.getActiveSessions"); return null; }
  @a2w.NotImplemented public android.media.session.MediaSession.Token getMediaKeyEventSession() { a2w.Stubs.hit("android.media.session.MediaSessionManager.getMediaKeyEventSession"); return null; }
  @a2w.NotImplemented public java.lang.String getMediaKeyEventSessionPackageName() { a2w.Stubs.hit("android.media.session.MediaSessionManager.getMediaKeyEventSessionPackageName"); return null; }
  @a2w.NotImplemented public java.util.List<android.media.Session2Token> getSession2Tokens() { a2w.Stubs.hit("android.media.session.MediaSessionManager.getSession2Tokens"); return null; }
  @a2w.NotImplemented public boolean isTrustedForMediaControl(android.media.session.MediaSessionManager.RemoteUserInfo userInfo) { a2w.Stubs.hit("android.media.session.MediaSessionManager.isTrustedForMediaControl"); return false; }
  @a2w.NotImplemented public void notifySession2Created(android.media.Session2Token token) { a2w.Stubs.hit("android.media.session.MediaSessionManager.notifySession2Created"); }
  @a2w.NotImplemented public void removeOnActiveSessionsChangedListener(android.media.session.MediaSessionManager.OnActiveSessionsChangedListener sessionListener) { a2w.Stubs.hit("android.media.session.MediaSessionManager.removeOnActiveSessionsChangedListener"); }
  @a2w.NotImplemented public void removeOnMediaKeyEventSessionChangedListener(android.media.session.MediaSessionManager.OnMediaKeyEventSessionChangedListener listener) { a2w.Stubs.hit("android.media.session.MediaSessionManager.removeOnMediaKeyEventSessionChangedListener"); }
  @a2w.NotImplemented public void removeOnSession2TokensChangedListener(android.media.session.MediaSessionManager.OnSession2TokensChangedListener listener) { a2w.Stubs.hit("android.media.session.MediaSessionManager.removeOnSession2TokensChangedListener"); }

    public interface OnActiveSessionsChangedListener {
    void onActiveSessionsChanged(java.util.List<android.media.session.MediaController> p0);
  }

    public interface OnMediaKeyEventSessionChangedListener {
    void onMediaKeyEventSessionChanged(java.lang.String p0, android.media.session.MediaSession.Token p1);
  }

    public interface OnSession2TokensChangedListener {
    void onSession2TokensChanged(java.util.List<android.media.Session2Token> p0);
  }

    public static final class RemoteUserInfo {
    /** (state of the property packageName, not part of the API) */ public java.lang.String a2w$packageName;
    /** (state of the property pid, not part of the API) */ public int a2w$pid;
    /** (state of the property uid, not part of the API) */ public int a2w$uid;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public RemoteUserInfo(a2w.Hidden hidden) { }
    @a2w.Derived public RemoteUserInfo(java.lang.String packageName, int pid, int uid) { this((a2w.Hidden) null); this.a2w$packageName = packageName; this.a2w$pid = pid; this.a2w$uid = uid; }
    @a2w.Derived public boolean equals(java.lang.Object obj) { if (this == obj) return true; if (!(obj instanceof android.media.session.MediaSessionManager.RemoteUserInfo)) return false; android.media.session.MediaSessionManager.RemoteUserInfo x = (android.media.session.MediaSessionManager.RemoteUserInfo) obj; return true && java.util.Objects.equals(a2w$packageName, x.a2w$packageName) && java.util.Objects.equals(a2w$pid, x.a2w$pid) && java.util.Objects.equals(a2w$uid, x.a2w$uid); }
    @a2w.Derived public java.lang.String getPackageName() { return this.a2w$packageName; }
    @a2w.Derived public int getPid() { return this.a2w$pid; }
    @a2w.Derived public int getUid() { return this.a2w$uid; }
    @a2w.Derived public int hashCode() { return java.util.Arrays.deepHashCode(new Object[]{a2w$packageName, a2w$pid, a2w$uid}); }
  }
}

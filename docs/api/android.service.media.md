# android.service.media

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CameraPrewarmService — declaration; API 23+ (2 +, 0 *, 0 ~, 3 -)

```
- CameraPrewarmService()  [API 23+]
- onBind(Intent): IBinder  [API 23+]
+ onCooldown(boolean): void  [API 23+]
+ onPrewarm(): void  [API 23+]
- onUnbind(Intent): boolean  [API 23+]
```

## MediaBrowserService — declaration; API 21+ (5 +, 0 *, 0 ~, 9 -)

```
- MediaBrowserService()  [API 21+]
- dump(FileDescriptor, PrintWriter, String[]): void  [API 21+]
- getBrowserRootHints(): Bundle  [API 24+]
- getCurrentBrowserInfo(): MediaSessionManager.RemoteUserInfo  [API 28+]
- getSessionToken(): MediaSession.Token  [API 21+]
- notifyChildrenChanged(String): void  [API 21+]
- notifyChildrenChanged(String, Bundle): void  [API 24+]
- onBind(Intent): IBinder  [API 21+]
+ onCreate(): void  [API 21+]
+ onGetRoot(String, int, Bundle): MediaBrowserService.BrowserRoot  [API 21+]
+ onLoadChildren(String, MediaBrowserService.Result): void  [API 21+]
+ onLoadChildren(String, MediaBrowserService.Result, Bundle): void  [API 24+]
+ onLoadItem(String, MediaBrowserService.Result): void  [API 23+]
- setSessionToken(MediaSession.Token): void  [API 21+]
```

## MediaBrowserService.BrowserRoot — declaration; API 21+ (0 +, 3 *, 0 ~, 0 -)

```
* BrowserRoot(String, Bundle)  [API 21+]
* getExtras(): Bundle  [API 21+]
* getRootId(): String  [API 21+]
```

## MediaBrowserService.Result — declaration; API 21+ (0 +, 0 *, 0 ~, 2 -)

```
- detach(): void  [API 21+]
- sendResult(Object): void  [API 21+]
```


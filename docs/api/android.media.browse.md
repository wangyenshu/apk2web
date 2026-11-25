# android.media.browse

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## MediaBrowser — declaration; API 21+ (0 +, 0 *, 0 ~, 13 -)

```
- MediaBrowser(Context, ComponentName, MediaBrowser.ConnectionCallback, Bundle)  [API 21+]
- connect(): void  [API 21+]
- disconnect(): void  [API 21+]
- getExtras(): Bundle  [API 21+]
- getItem(String, MediaBrowser.ItemCallback): void  [API 23+]
- getRoot(): String  [API 21+]
- getServiceComponent(): ComponentName  [API 21+]
- getSessionToken(): MediaSession.Token  [API 21+]
- isConnected(): boolean  [API 21+]
- subscribe(String, Bundle, MediaBrowser.SubscriptionCallback): void  [API 24+]
- subscribe(String, MediaBrowser.SubscriptionCallback): void  [API 21+]
- unsubscribe(String): void  [API 21+]
- unsubscribe(String, MediaBrowser.SubscriptionCallback): void  [API 24+]
```

## MediaBrowser.ConnectionCallback — declaration; API 21+ (4 +, 0 *, 0 ~, 0 -)

```
+ ConnectionCallback()  [API 21+]
+ onConnected(): void  [API 21+]
+ onConnectionFailed(): void  [API 21+]
+ onConnectionSuspended(): void  [API 21+]
```

## MediaBrowser.ItemCallback — declaration; API 23+ (3 +, 0 *, 0 ~, 0 -)

```
+ ItemCallback()  [API 23+]
+ onError(String): void  [API 23+]
+ onItemLoaded(MediaBrowser.MediaItem): void  [API 23+]
```

## MediaBrowser.MediaItem — declaration; API 21+ (0 +, 3 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 21+]
* MediaItem(MediaDescription, int)  [API 21+]
- describeContents(): int  [API 21+]
* getDescription(): MediaDescription  [API 21+]
* getFlags(): int  [API 21+]
- getMediaId(): String  [API 21+]
- isBrowsable(): boolean  [API 21+]
- isPlayable(): boolean  [API 21+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## MediaBrowser.SubscriptionCallback — declaration; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ SubscriptionCallback()  [API 21+]
+ onChildrenLoaded(String, List): void  [API 21+]
+ onChildrenLoaded(String, List, Bundle): void  [API 24+]
+ onError(String): void  [API 21+]
+ onError(String, Bundle): void  [API 24+]
```


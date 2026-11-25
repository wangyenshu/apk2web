# android.service.notification

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Condition — declaration; API 24+ (0 +, 0 *, 0 ~, 15 -)

```
- CREATOR [field]  [API 24+]
- Condition(Parcel)  [API 24+]
- Condition(Uri, String, String, String, int, int, int)  [API 24+]
- Condition(Uri, String, String, String, int, int, int, int)  [API 35+]
- Condition(Uri, String, int)  [API 24+]
- Condition(Uri, String, int, int)  [API 35+]
- copy(): Condition  [API 24+]
- describeContents(): int  [API 24+]
- equals(Object): boolean  [API 24+]
- flags [field]  [API 24+]
- hashCode(): int  [API 24+]
- icon [field]  [API 24+]
- id [field]  [API 24+]
- isValidId(Uri, String): boolean [static]  [API 24+]
- line1 [field]  [API 24+]
- line2 [field]  [API 24+]
- newId(Context): Uri.Builder [static]  [API 24+]
- relevanceToString(int): String [static]  [API 24+]
- source [field]  [API 35+]
- state [field]  [API 24+]
- stateToString(int): String [static]  [API 24+]
- summary [field]  [API 24+]
- toString(): String  [API 24+]
- writeToParcel(Parcel, int): void  [API 24+]
```

## ConditionProviderService — declaration; API 24+, deprecated in 29 (4 +, 0 *, 0 ~, 6 -)

```
- ConditionProviderService()  [API 24+]
- notifyCondition(Condition): void  [API 24+]
- notifyConditions(Condition[]): void  [API 24+]
- onBind(Intent): IBinder  [API 24+]
+ onConnected(): void  [API 24+]
+ onRequestConditions(int): void  [API 24+]
+ onSubscribe(Uri): void  [API 24+]
+ onUnsubscribe(Uri): void  [API 24+]
- requestRebind(ComponentName): void [static]  [API 26+]
- requestUnbind(): void  [API 26+]
```

## NotificationListenerService — declaration; API 18+ (14 +, 0 *, 0 ~, 25 -)

```
- NotificationListenerService()  [API 18+]
- attachBaseContext(Context): void  [API 18+]
- cancelAllNotifications(): void  [API 18+]
- cancelNotification(String): void  [API 21+]
- cancelNotification(String, String, int): void  [API 18+, deprecated in 21]
- cancelNotifications(String[]): void  [API 21+]
- clearRequestedListenerHints(): void  [API 29+]
- getActiveNotifications(): StatusBarNotification[]  [API 18+]
- getActiveNotifications(String[]): StatusBarNotification[]  [API 21+]
- getCurrentInterruptionFilter(): int  [API 21+]
- getCurrentListenerHints(): int  [API 21+]
- getCurrentRanking(): NotificationListenerService.RankingMap  [API 21+]
- getNotificationChannelGroups(String, UserHandle): List  [API 26+]
- getNotificationChannels(String, UserHandle): List  [API 26+]
- getSnoozedNotifications(): StatusBarNotification[]  [API 26+]
- migrateNotificationFilter(int, List): void  [API 31+]
- onBind(Intent): IBinder  [API 18+]
+ onDestroy(): void  [API 18+]
+ onInterruptionFilterChanged(int): void  [API 21+]
+ onListenerConnected(): void  [API 21+]
+ onListenerDisconnected(): void  [API 24+]
+ onListenerHintsChanged(int): void  [API 21+]
+ onNotificationChannelGroupModified(String, UserHandle, NotificationChannelGroup, int): void  [API 26+]
+ onNotificationChannelModified(String, UserHandle, NotificationChannel, int): void  [API 26+]
+ onNotificationPosted(StatusBarNotification): void  [API 18+]
+ onNotificationPosted(StatusBarNotification, NotificationListenerService.RankingMap): void  [API 21+]
+ onNotificationRankingUpdate(NotificationListenerService.RankingMap): void  [API 21+]
+ onNotificationRemoved(StatusBarNotification): void  [API 18+]
+ onNotificationRemoved(StatusBarNotification, NotificationListenerService.RankingMap): void  [API 21+]
+ onNotificationRemoved(StatusBarNotification, NotificationListenerService.RankingMap, int): void  [API 26+]
+ onSilentStatusBarIconsVisibilityChanged(boolean): void  [API 29+]
- requestInterruptionFilter(int): void  [API 21+]
- requestListenerHints(int): void  [API 21+]
- requestRebind(ComponentName): void [static]  [API 24+]
- requestUnbind(): void  [API 24+]
- requestUnbind(ComponentName): void [static]  [API 34+]
- setNotificationsShown(String[]): void  [API 23+]
- snoozeNotification(String, long): void  [API 26+]
- updateNotificationChannel(String, UserHandle, NotificationChannel): void  [API 26+]
```

## NotificationListenerService.Ranking — declaration; API 21+ (0 +, 0 *, 0 ~, 21 -)

```
- Ranking()  [API 21+]
- canBubble(): boolean  [API 29+]
- canShowBadge(): boolean  [API 26+]
- equals(Object): boolean  [API 21+]
- getChannel(): NotificationChannel  [API 26+]
- getConversationShortcutInfo(): ShortcutInfo  [API 31+]
- getImportance(): int  [API 24+]
- getImportanceExplanation(): CharSequence  [API 24+]
- getKey(): String  [API 21+]
- getLastAudiblyAlertedMillis(): long  [API 29+]
- getLockscreenVisibilityOverride(): int  [API 31+]
- getOverrideGroupKey(): String  [API 24+]
- getRank(): int  [API 21+]
- getSmartActions(): List  [API 29+]
- getSmartReplies(): List  [API 29+]
- getSuppressedVisualEffects(): int  [API 24+]
- getUserSentiment(): int  [API 28+]
- isAmbient(): boolean  [API 21+]
- isConversation(): boolean  [API 31+]
- isSuspended(): boolean  [API 28+]
- matchesInterruptionFilter(): boolean  [API 21+]
```

## NotificationListenerService.RankingMap — declaration; API 21+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 21+]
- describeContents(): int  [API 21+]
- equals(Object): boolean  [API 21+]
- getOrderedKeys(): String[]  [API 21+]
- getRanking(String, NotificationListenerService.Ranking): boolean  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## StatusBarNotification — declaration; API 18+ (0 +, 3 *, 0 ~, 20 -)

```
- CREATOR [field]  [API 18+]
- StatusBarNotification(Parcel)  [API 18+]
- StatusBarNotification(String, String, int, String, int, int, int, Notification, UserHandle, long)  [API 18+, deprecated in 26]
* clone(): StatusBarNotification  [API 18+]
- describeContents(): int  [API 18+]
- getGroupKey(): String  [API 21+]
- getId(): int  [API 18+]
- getKey(): String  [API 20+]
- getNotification(): Notification  [API 18+]
- getOpPkg(): String  [API 29+]
* getOverrideGroupKey(): String  [API 24+]
- getPackageName(): String  [API 18+]
- getPostTime(): long  [API 18+]
- getTag(): String  [API 18+]
- getUid(): int  [API 29+]
- getUser(): UserHandle  [API 21+]
- getUserId(): int  [API 18+, deprecated in 21]
- isAppGroup(): boolean  [API 30+]
- isClearable(): boolean  [API 18+]
- isGroup(): boolean  [API 24+]
- isOngoing(): boolean  [API 18+]
* setOverrideGroupKey(String): void  [API 24+]
- toString(): String  [API 18+]
- writeToParcel(Parcel, int): void  [API 18+]
```

## ZenDeviceEffects — declaration; API 35+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
- hashCode(): int  [API 35+]
- shouldDimWallpaper(): boolean  [API 35+]
- shouldDisplayGrayscale(): boolean  [API 35+]
- shouldSuppressAmbientDisplay(): boolean  [API 35+]
- shouldUseNightMode(): boolean  [API 35+]
- toString(): String  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## ZenDeviceEffects.Builder — declaration; API 35+ (0 +, 0 *, 0 ~, 7 -)

```
- Builder()  [API 35+]
- Builder(ZenDeviceEffects)  [API 35+]
- build(): ZenDeviceEffects  [API 35+]
- setShouldDimWallpaper(boolean): ZenDeviceEffects.Builder  [API 35+]
- setShouldDisplayGrayscale(boolean): ZenDeviceEffects.Builder  [API 35+]
- setShouldSuppressAmbientDisplay(boolean): ZenDeviceEffects.Builder  [API 35+]
- setShouldUseNightMode(boolean): ZenDeviceEffects.Builder  [API 35+]
```

## ZenPolicy — declaration; API 29+ (0 +, 0 *, 0 ~, 25 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- equals(Object): boolean  [API 29+]
- getPriorityCallSenders(): int  [API 29+]
- getPriorityCategoryAlarms(): int  [API 29+]
- getPriorityCategoryCalls(): int  [API 29+]
- getPriorityCategoryConversations(): int  [API 30+]
- getPriorityCategoryEvents(): int  [API 29+]
- getPriorityCategoryMedia(): int  [API 29+]
- getPriorityCategoryMessages(): int  [API 29+]
- getPriorityCategoryReminders(): int  [API 29+]
- getPriorityCategoryRepeatCallers(): int  [API 29+]
- getPriorityCategorySystem(): int  [API 29+]
- getPriorityChannelsAllowed(): int  [API 35+]
- getPriorityConversationSenders(): int  [API 30+]
- getPriorityMessageSenders(): int  [API 29+]
- getVisualEffectAmbient(): int  [API 29+]
- getVisualEffectBadge(): int  [API 29+]
- getVisualEffectFullScreenIntent(): int  [API 29+]
- getVisualEffectLights(): int  [API 29+]
- getVisualEffectNotificationList(): int  [API 29+]
- getVisualEffectPeek(): int  [API 29+]
- getVisualEffectStatusBar(): int  [API 29+]
- hashCode(): int  [API 29+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## ZenPolicy.Builder — declaration; API 29+ (0 +, 0 *, 0 ~, 23 -)

```
- Builder()  [API 29+]
- allowAlarms(boolean): ZenPolicy.Builder  [API 29+]
- allowAllSounds(): ZenPolicy.Builder  [API 29+]
- allowCalls(int): ZenPolicy.Builder  [API 29+]
- allowConversations(int): ZenPolicy.Builder  [API 30+]
- allowEvents(boolean): ZenPolicy.Builder  [API 29+]
- allowMedia(boolean): ZenPolicy.Builder  [API 29+]
- allowMessages(int): ZenPolicy.Builder  [API 29+]
- allowPriorityChannels(boolean): ZenPolicy.Builder  [API 35+]
- allowReminders(boolean): ZenPolicy.Builder  [API 29+]
- allowRepeatCallers(boolean): ZenPolicy.Builder  [API 29+]
- allowSystem(boolean): ZenPolicy.Builder  [API 29+]
- build(): ZenPolicy  [API 29+]
- disallowAllSounds(): ZenPolicy.Builder  [API 29+]
- hideAllVisualEffects(): ZenPolicy.Builder  [API 29+]
- showAllVisualEffects(): ZenPolicy.Builder  [API 29+]
- showBadges(boolean): ZenPolicy.Builder  [API 29+]
- showFullScreenIntent(boolean): ZenPolicy.Builder  [API 29+]
- showInAmbientDisplay(boolean): ZenPolicy.Builder  [API 29+]
- showInNotificationList(boolean): ZenPolicy.Builder  [API 29+]
- showLights(boolean): ZenPolicy.Builder  [API 29+]
- showPeeking(boolean): ZenPolicy.Builder  [API 29+]
- showStatusBarIcons(boolean): ZenPolicy.Builder  [API 29+]
```


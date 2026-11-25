# android.provider

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AlarmClock — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ AlarmClock()  [API 9+]
```

## BaseColumns — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## BlockedNumberContract — declaration; API 24+ (0 +, 0 *, 0 ~, 3 -)

```
- AUTHORITY_URI [field]  [API 24+]
- canCurrentUserBlockNumbers(Context): boolean [static]  [API 24+]
- isBlocked(Context, String): boolean [static]  [API 24+]
- unblock(Context, String): int [static]  [API 24+]
```

## BlockedNumberContract.BlockedNumbers — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 24+]
```

## Browser — declaration; API 1+ (0 +, 0 *, 0 ~, 14 -)

```
- Browser()  [API 1+]
- addSearchUrl(ContentResolver, String): void  [API 1-22, removed in 23]
- canClearHistory(ContentResolver): boolean  [API 1-22, removed in 23]
- clearHistory(ContentResolver): void  [API 1-22, removed in 23]
- clearSearches(ContentResolver): void  [API 1-22, removed in 23]
- deleteFromHistory(ContentResolver, String): void  [API 1-22, removed in 23]
- deleteHistoryTimeFrame(ContentResolver, long, long): void  [API 1-22, removed in 23]
- getAllBookmarks(ContentResolver): Cursor  [API 1-22, removed in 23]
- getAllVisitedUrls(ContentResolver): Cursor  [API 1-22, removed in 23]
- requestAllIcons(ContentResolver, String, WebIconDatabase.IconListener): void  [API 1-22, removed in 23]
- saveBookmark(Context, String, String): void  [API 1-22, removed in 23]
- sendString(Context, String): void [static]  [API 1+]
- truncateHistory(ContentResolver): void  [API 1-22, removed in 23]
- updateVisitedHistory(ContentResolver, String, boolean): void  [API 1-22, removed in 23]
```

## Browser.BookmarkColumns — declaration; API 1-22, removed in 23 (1 +, 0 *, 0 ~, 0 -)

```
+ BookmarkColumns()  [API 1-22, removed in 23]
```

## Browser.SearchColumns — declaration; API 1-22, removed in 23 (1 +, 0 *, 0 ~, 0 -)

```
+ SearchColumns()  [API 1-22, removed in 23]
```

## CalendarContract — declaration; API 14+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 14+]
- startViewCalendarEventInManagedProfile(Context, long, long, long, boolean, int): boolean [static]  [API 29+]
```

## CalendarContract.Attendees — declaration; API 14+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 14+]
- query(ContentResolver, long, String[]): Cursor [static]  [API 14+]
```

## CalendarContract.AttendeesColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.CalendarAlerts — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 14+]
- CONTENT_URI_BY_INSTANCE [field]  [API 14+]
```

## CalendarContract.CalendarAlertsColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.CalendarCache — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

```
- URI [field]  [API 14+]
```

## CalendarContract.CalendarCacheColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.CalendarColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.CalendarEntity — declaration; API 14+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 14+]
- newEntityIterator(Cursor): EntityIterator [static]  [API 14+]
```

## CalendarContract.CalendarSyncColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.Calendars — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 14+]
- ENTERPRISE_CONTENT_URI [field]  [API 29+]
```

## CalendarContract.Colors — declaration; API 15+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 15+]
```

## CalendarContract.ColorsColumns — declaration; API 15+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.EventDays — declaration; API 14+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 14+]
- query(ContentResolver, int, int, String[]): Cursor [static]  [API 14+]
```

## CalendarContract.EventDaysColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.Events — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_EXCEPTION_URI [field]  [API 14+]
- CONTENT_URI [field]  [API 14+]
- ENTERPRISE_CONTENT_URI [field]  [API 29+]
```

## CalendarContract.EventsColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.EventsEntity — declaration; API 14+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_URI [field]  [API 14+]
- newEntityIterator(Cursor, ContentProviderClient): EntityIterator [static]  [API 14+]
- newEntityIterator(Cursor, ContentResolver): EntityIterator [static]  [API 14+]
```

## CalendarContract.ExtendedProperties — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 14+]
```

## CalendarContract.ExtendedPropertiesColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.Instances — declaration; API 14+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_BY_DAY_URI [field]  [API 14+]
- CONTENT_SEARCH_BY_DAY_URI [field]  [API 14+]
- CONTENT_SEARCH_URI [field]  [API 14+]
- CONTENT_URI [field]  [API 14+]
- ENTERPRISE_CONTENT_BY_DAY_URI [field]  [API 29+]
- ENTERPRISE_CONTENT_SEARCH_BY_DAY_URI [field]  [API 29+]
- ENTERPRISE_CONTENT_SEARCH_URI [field]  [API 29+]
- ENTERPRISE_CONTENT_URI [field]  [API 29+]
- query(ContentResolver, String[], long, long): Cursor [static]  [API 14+]
- query(ContentResolver, String[], long, long, String): Cursor [static]  [API 14+]
```

## CalendarContract.Reminders — declaration; API 14+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 14+]
- query(ContentResolver, long, String[]): Cursor [static]  [API 14+]
```

## CalendarContract.RemindersColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.SyncColumns — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## CalendarContract.SyncState — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 14+]
```

## CallLog — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 1+]
+ CallLog()  [API 1+]
```

## CallLog.Calls — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_FILTER_URI [field]  [API 1+]
- CONTENT_URI [field]  [API 1+]
- CONTENT_URI_WITH_VOICEMAIL [field]  [API 21+]
- Calls()  [API 1+]
- getLastOutgoingCall(Context): String [static]  [API 8+]
```

## CallLog.Locations — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 31+]
```

## CloudMediaProvider — declaration; API 33+ (5 +, 0 *, 0 ~, 17 -)

```
- CloudMediaProvider()  [API 33+]
- attachInfo(Context, ProviderInfo): void  [API 33+]
- call(String, String, Bundle): Bundle  [API 33+]
- canonicalize(Uri): Uri  [API 33+]
- delete(Uri, String, String[]): int  [API 33+]
- getType(Uri): String  [API 33+]
- insert(Uri, ContentValues): Uri  [API 33+]
- onCreateCloudMediaSurfaceController(Bundle, CloudMediaProvider.CloudMediaSurfaceStateChangedCallback): CloudMediaProvider.CloudMediaSurfaceController  [API 33+]
+ onGetMediaCollectionInfo(Bundle): Bundle  [API 33+]
+ onOpenMedia(String, Bundle, CancellationSignal): ParcelFileDescriptor  [API 33+]
+ onOpenPreview(String, Point, Bundle, CancellationSignal): AssetFileDescriptor  [API 33+]
- onQueryAlbums(Bundle): Cursor  [API 33+]
+ onQueryDeletedMedia(Bundle): Cursor  [API 33+]
+ onQueryMedia(Bundle): Cursor  [API 33+]
- openFile(Uri, String): ParcelFileDescriptor  [API 33+]
- openFile(Uri, String, CancellationSignal): ParcelFileDescriptor  [API 33+]
- openTypedAssetFile(Uri, String, Bundle): AssetFileDescriptor  [API 33+]
- openTypedAssetFile(Uri, String, Bundle, CancellationSignal): AssetFileDescriptor  [API 33+]
- query(Uri, String[], Bundle, CancellationSignal): Cursor  [API 33+]
- query(Uri, String[], String, String[], String): Cursor  [API 33+]
- query(Uri, String[], String, String[], String, CancellationSignal): Cursor  [API 33+]
- update(Uri, ContentValues, String, String[]): int  [API 33+]
```

## CloudMediaProvider.CloudMediaSurfaceController — declaration; API 33+ (11 +, 0 *, 0 ~, 0 -)

```
+ CloudMediaSurfaceController()  [API 33+]
+ onConfigChange(Bundle): void  [API 33+]
+ onDestroy(): void  [API 33+]
+ onMediaPause(int): void  [API 33+]
+ onMediaPlay(int): void  [API 33+]
+ onMediaSeekTo(int, long): void  [API 33+]
+ onPlayerCreate(): void  [API 33+]
+ onPlayerRelease(): void  [API 33+]
+ onSurfaceChanged(int, int, int, int): void  [API 33+]
+ onSurfaceCreated(int, Surface, String): void  [API 33+]
+ onSurfaceDestroyed(int): void  [API 33+]
```

## CloudMediaProvider.CloudMediaSurfaceStateChangedCallback — declaration; API 33+ (0 +, 0 *, 0 ~, 1 -)

```
- setPlaybackState(int, int, Bundle): void  [API 33+]
```

## CloudMediaProviderContract — declaration; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CloudMediaProviderContract.AlbumColumns — declaration; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CloudMediaProviderContract.MediaCollectionInfo — declaration; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CloudMediaProviderContract.MediaColumns — declaration; API 33+ (0 +, 0 *, 0 ~, 0 -)

## Contacts — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 1+]
```

## Contacts.ContactMethods — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 5 -)

```
- CONTENT_EMAIL_URI [field]  [API 1+]
- CONTENT_URI [field]  [API 1+]
- addPostalLocation(Context, long, double, double): void  [API 1+]
- decodeImProtocol(String): Object [static]  [API 1+]
- encodeCustomImProtocol(String): String [static]  [API 1+]
- encodePredefinedImProtocol(int): String [static]  [API 1+]
- getDisplayLabel(Context, int, int, CharSequence): CharSequence [static]  [API 1+]
```

## Contacts.ContactMethodsColumns — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.Extensions — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 1+]
```

## Contacts.ExtensionsColumns — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.GroupMembership — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 1+]
- RAW_CONTENT_URI [field]  [API 1+]
```

## Contacts.Groups — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 1+]
- DELETED_CONTENT_URI [field]  [API 1+]
```

## Contacts.GroupsColumns — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.Intents — declaration; API 1+, deprecated in 5 (1 +, 0 *, 0 ~, 0 -)

```
+ Intents()  [API 1+]
```

## Contacts.Intents.Insert — declaration; API 1+, deprecated in 5 (1 +, 0 *, 0 ~, 0 -)

```
+ Insert()  [API 1+]
```

## Contacts.Intents.UI — declaration; API 1+, deprecated in 5 (1 +, 0 *, 0 ~, 0 -)

```
+ UI()  [API 1+]
```

## Contacts.OrganizationColumns — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.Organizations — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 1+]
- getDisplayLabel(Context, int, CharSequence): CharSequence [static]  [API 1+]
```

## Contacts.People — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 9 -)

```
- CONTENT_FILTER_URI [field]  [API 1+]
- CONTENT_URI [field]  [API 1+]
- DELETED_CONTENT_URI [field]  [API 1+]
- addToGroup(ContentResolver, long, String): Uri [static]  [API 1+]
- addToGroup(ContentResolver, long, long): Uri [static]  [API 1+]
- addToMyContactsGroup(ContentResolver, long): Uri [static]  [API 1+]
- createPersonInMyContactsGroup(ContentResolver, ContentValues): Uri [static]  [API 1+]
- loadContactPhoto(Context, Uri, int, BitmapFactory.Options): Bitmap [static]  [API 1+]
- markAsContacted(ContentResolver, long): void [static]  [API 1+]
- openContactPhotoInputStream(ContentResolver, Uri): InputStream [static]  [API 1+]
- queryGroups(ContentResolver, long): Cursor [static]  [API 1+]
- setPhotoData(ContentResolver, Uri, byte[]): void [static]  [API 1+]
```

## Contacts.People.ContactMethods — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.People.Extensions — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.People.Phones — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.PeopleColumns — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.Phones — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_FILTER_URL [field]  [API 1+]
- CONTENT_URI [field]  [API 1+]
- getDisplayLabel(Context, int, CharSequence): CharSequence [static]  [API 1+]
- getDisplayLabel(Context, int, CharSequence, CharSequence[]): CharSequence [static]  [API 1+]
```

## Contacts.PhonesColumns — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.Photos — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 1+]
```

## Contacts.PhotosColumns — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.PresenceColumns — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## Contacts.Settings — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_URI [field]  [API 1+]
- getSetting(ContentResolver, String, String): String [static]  [API 1+]
- setSetting(ContentResolver, String, String, String): void [static]  [API 1+]
```

## Contacts.SettingsColumns — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 0 -)

## ContactsContract — declaration; API 5+ (0 +, 0 *, 0 ~, 2 -)

```
- AUTHORITY_URI [field]  [API 5+]
- ContactsContract()  [API 5+]
- isProfileId(long): boolean [static]  [API 14+]
```

## ContactsContract.AggregationExceptions — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 5+]
```

## ContactsContract.BaseSyncColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds.BaseTypes — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds.Callable — declaration; API 21+ (1 +, 0 *, 0 ~, 0 -)

```
- CONTENT_FILTER_URI [field]  [API 21+]
- CONTENT_URI [field]  [API 21+]
+ Callable()  [API 21+]
- ENTERPRISE_CONTENT_FILTER_URI [field]  [API 24+]
```

## ContactsContract.CommonDataKinds.CommonColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds.Contactables — declaration; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
- CONTENT_FILTER_URI [field]  [API 18+]
- CONTENT_URI [field]  [API 18+]
+ Contactables()  [API 18+]
```

## ContactsContract.CommonDataKinds.Email — declaration; API 5+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_FILTER_URI [field]  [API 5+]
- CONTENT_LOOKUP_URI [field]  [API 5+]
- CONTENT_URI [field]  [API 5+]
- ENTERPRISE_CONTENT_FILTER_URI [field]  [API 24+]
- ENTERPRISE_CONTENT_LOOKUP_URI [field]  [API 23+]
- getTypeLabel(Resources, int, CharSequence): CharSequence [static]  [API 5+]
- getTypeLabelResource(int): int [static]  [API 5+]
```

## ContactsContract.CommonDataKinds.Event — declaration; API 5+ (0 +, 0 *, 0 ~, 2 -)

```
- getTypeLabel(Resources, int, CharSequence): CharSequence [static]  [API 21+]
- getTypeResource(Integer): int [static]  [API 5+]
```

## ContactsContract.CommonDataKinds.GroupMembership — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds.Identity — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds.Im — declaration; API 5+, deprecated in 35 (0 +, 0 *, 0 ~, 4 -)

```
- getProtocolLabel(Resources, int, CharSequence): CharSequence [static]  [API 5+]
- getProtocolLabelResource(int): int [static]  [API 5+]
- getTypeLabel(Resources, int, CharSequence): CharSequence [static]  [API 5+]
- getTypeLabelResource(int): int [static]  [API 5+]
```

## ContactsContract.CommonDataKinds.Nickname — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds.Note — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds.Organization — declaration; API 5+ (0 +, 0 *, 0 ~, 2 -)

```
- getTypeLabel(Resources, int, CharSequence): CharSequence [static]  [API 5+]
- getTypeLabelResource(int): int [static]  [API 5+]
```

## ContactsContract.CommonDataKinds.Phone — declaration; API 5+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_FILTER_URI [field]  [API 5+]
- CONTENT_URI [field]  [API 5+]
- ENTERPRISE_CONTENT_FILTER_URI [field]  [API 24+]
- ENTERPRISE_CONTENT_URI [field]  [API 34+]
- getTypeLabel(Resources, int, CharSequence): CharSequence [static]  [API 5+]
- getTypeLabelResource(int): int [static]  [API 5+]
```

## ContactsContract.CommonDataKinds.Photo — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds.Relation — declaration; API 5+ (0 +, 0 *, 0 ~, 2 -)

```
- getTypeLabel(Resources, int, CharSequence): CharSequence [static]  [API 11+]
- getTypeLabelResource(int): int [static]  [API 11+]
```

## ContactsContract.CommonDataKinds.SipAddress — declaration; API 9+, deprecated in 35 (0 +, 0 *, 0 ~, 2 -)

```
- getTypeLabel(Resources, int, CharSequence): CharSequence [static]  [API 9+]
- getTypeLabelResource(int): int [static]  [API 9+]
```

## ContactsContract.CommonDataKinds.StructuredName — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.CommonDataKinds.StructuredPostal — declaration; API 5+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_URI [field]  [API 5+]
- getTypeLabel(Resources, int, CharSequence): CharSequence [static]  [API 5+]
- getTypeLabelResource(int): int [static]  [API 5+]
```

## ContactsContract.CommonDataKinds.Website — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.ContactNameColumns — declaration; API 11+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.ContactOptionsColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.ContactStatusColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Contacts — declaration; API 5+ (0 +, 0 *, 0 ~, 7 -)

```
- CONTENT_FILTER_URI [field]  [API 5+]
- CONTENT_FREQUENT_URI [field]  [API 21+, deprecated in 29]
- CONTENT_GROUP_URI [field]  [API 5+]
- CONTENT_LOOKUP_URI [field]  [API 5+]
- CONTENT_MULTI_VCARD_URI [field]  [API 21+]
- CONTENT_STREQUENT_FILTER_URI [field]  [API 5+]
- CONTENT_STREQUENT_URI [field]  [API 5+]
- CONTENT_URI [field]  [API 5+]
- CONTENT_VCARD_URI [field]  [API 5+]
- ENTERPRISE_CONTENT_FILTER_URI [field]  [API 24+]
- ENTERPRISE_CONTENT_URI [field]  [API 34+]
- getLookupUri(ContentResolver, Uri): Uri [static]  [API 5+]
- getLookupUri(long, String): Uri [static]  [API 5+]
- isEnterpriseContactId(long): boolean [static]  [API 21+]
- lookupContact(ContentResolver, Uri): Uri [static]  [API 5+]
- markAsContacted(ContentResolver, long): void [static]  [API 5+, deprecated in 16]
- openContactPhotoInputStream(ContentResolver, Uri): InputStream [static]  [API 5+]
- openContactPhotoInputStream(ContentResolver, Uri, boolean): InputStream [static]  [API 14+]
```

## ContactsContract.Contacts.AggregationSuggestions — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Contacts.AggregationSuggestions.Builder — declaration; API 23+ (0 +, 0 *, 0 ~, 5 -)

```
- Builder()  [API 23+]
- addNameParameter(String): ContactsContract.Contacts.AggregationSuggestions.Builder  [API 23+]
- build(): Uri  [API 23+]
- setContactId(long): ContactsContract.Contacts.AggregationSuggestions.Builder  [API 23+]
- setLimit(int): ContactsContract.Contacts.AggregationSuggestions.Builder  [API 23+]
```

## ContactsContract.Contacts.Data — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Contacts.Entity — declaration; API 11+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Contacts.Photo — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Contacts.StreamItems — declaration; API 15-22, removed in 23, deprecated in 21 (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.ContactsColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Data — declaration; API 5+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 5+]
- getContactLookupUri(ContentResolver, Uri): Uri [static]  [API 5+]
```

## ContactsContract.DataColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.DataColumnsWithJoins — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.DataUsageFeedback — declaration; API 14+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
- DELETE_USAGE_URI [field]  [API 16+]
+ DataUsageFeedback()  [API 14+]
- FEEDBACK_URI [field]  [API 14+]
```

## ContactsContract.DataUsageStatColumns — declaration; API 18+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.DeletedContacts — declaration; API 18+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 18+]
```

## ContactsContract.DeletedContactsColumns — declaration; API 18+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Directory — declaration; API 11+ (0 +, 0 *, 0 ~, 3 -)

```
- CONTENT_URI [field]  [API 11+]
- ENTERPRISE_CONTENT_URI [field]  [API 24+]
- isEnterpriseDirectoryId(long): boolean [static]  [API 24+]
- isRemoteDirectoryId(long): boolean [static]  [API 24+]
- notifyDirectoryChange(ContentResolver): void [static]  [API 11+]
```

## ContactsContract.DisplayNameSources — declaration; API 11+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.DisplayPhoto — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_MAX_DIMENSIONS_URI [field]  [API 14+]
- CONTENT_URI [field]  [API 14+]
```

## ContactsContract.FullNameStyle — declaration; API 11+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Groups — declaration; API 5+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_SUMMARY_URI [field]  [API 5+]
- CONTENT_URI [field]  [API 5+]
- newEntityIterator(Cursor): EntityIterator [static]  [API 8+]
```

## ContactsContract.GroupsColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Intents — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ Intents()  [API 5+]
```

## ContactsContract.Intents.Insert — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ Insert()  [API 5+]
```

## ContactsContract.PhoneLookup — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_FILTER_URI [field]  [API 5+]
- ENTERPRISE_CONTENT_FILTER_URI [field]  [API 21+]
```

## ContactsContract.PhoneLookupColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.PhoneticNameStyle — declaration; API 11+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.PinnedPositions — declaration; API 21+ (0 +, 0 *, 0 ~, 3 -)

```
- PinnedPositions()  [API 21+]
- pin(ContentResolver, long, int): void [static]  [API 21+]
- undemote(ContentResolver, long): void [static]  [API 21+]
```

## ContactsContract.Presence — declaration; API 5+, deprecated in 5 (1 +, 0 *, 0 ~, 0 -)

```
+ Presence()  [API 5+]
```

## ContactsContract.PresenceColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.Profile — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_RAW_CONTACTS_URI [field]  [API 14+]
- CONTENT_URI [field]  [API 14+]
- CONTENT_VCARD_URI [field]  [API 14+]
```

## ContactsContract.ProfileSyncState — declaration; API 14+ (0 +, 0 *, 0 ~, 4 -)

```
- CONTENT_URI [field]  [API 14+]
- get(ContentProviderClient, Account): byte[] [static]  [API 14+]
- getWithUri(ContentProviderClient, Account): Pair [static]  [API 14+]
- newSetOperation(Account, byte[]): ContentProviderOperation [static]  [API 14+]
- set(ContentProviderClient, Account, byte[]): void [static]  [API 14+]
```

## ContactsContract.ProviderStatus — declaration; API 23+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 23+]
```

## ContactsContract.QuickContact — declaration; API 5+ (0 +, 0 *, 0 ~, 5 -)

```
- QuickContact()  [API 5+]
- showQuickContact(Context, Rect, Uri, String[], String): void [static]  [API 23+]
- showQuickContact(Context, Rect, Uri, int, String[]): void [static]  [API 5+]
- showQuickContact(Context, View, Uri, String[], String): void [static]  [API 23+]
- showQuickContact(Context, View, Uri, int, String[]): void [static]  [API 5+]
```

## ContactsContract.RawContacts — declaration; API 5+ (0 +, 0 *, 0 ~, 4 -)

```
- CONTENT_URI [field]  [API 5+]
- getContactLookupUri(ContentResolver, Uri): Uri [static]  [API 5+]
- getLocalAccountName(Context): String [static]  [API 30+]
- getLocalAccountType(Context): String [static]  [API 30+]
- newEntityIterator(Cursor): EntityIterator [static]  [API 8+]
```

## ContactsContract.RawContacts.Data — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.RawContacts.DisplayPhoto — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.RawContacts.Entity — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.RawContacts.StreamItems — declaration; API 15-22, removed in 23, deprecated in 21 (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.RawContactsColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.RawContactsEntity — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 5+]
- PROFILE_CONTENT_URI [field]  [API 14+]
```

## ContactsContract.SearchSnippets — declaration; API 21+ (1 +, 0 *, 0 ~, 0 -)

```
+ SearchSnippets()  [API 21+]
```

## ContactsContract.Settings — declaration; API 5+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 5+]
- getDefaultAccount(ContentResolver): Account [static]  [API 33+]
```

## ContactsContract.SettingsColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.SimAccount — declaration; API 31+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
- getAccountName(): String  [API 31+]
- getAccountType(): String  [API 31+]
- getEfType(): int  [API 31+]
- getSimSlotIndex(): int  [API 31+]
- hashCode(): int  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## ContactsContract.SimContacts — declaration; API 31+ (0 +, 0 *, 0 ~, 1 -)

```
- getSimAccounts(ContentResolver): List [static]  [API 31+]
```

## ContactsContract.StatusColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.StatusUpdates — declaration; API 5+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_URI [field]  [API 5+]
- PROFILE_CONTENT_URI [field]  [API 14+]
- getPresenceIconResourceId(int): int [static]  [API 5+]
- getPresencePrecedence(int): int [static]  [API 5+]
```

## ContactsContract.StreamItemPhotos — declaration; API 15-22, removed in 23, deprecated in 21 (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.StreamItemPhotosColumns — declaration; API 15-22, removed in 23, deprecated in 21 (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.StreamItems — declaration; API 15-22, removed in 23, deprecated in 21 (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.StreamItems.StreamItemPhotos — declaration; API 15-22, removed in 23, deprecated in 21 (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.StreamItemsColumns — declaration; API 15-22, removed in 23, deprecated in 21 (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.SyncColumns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## ContactsContract.SyncState — declaration; API 5+ (0 +, 0 *, 0 ~, 4 -)

```
- CONTENT_URI [field]  [API 5+]
- get(ContentProviderClient, Account): byte[] [static]  [API 5+]
- getWithUri(ContentProviderClient, Account): Pair [static]  [API 5+]
- newSetOperation(Account, byte[]): ContentProviderOperation [static]  [API 5+]
- set(ContentProviderClient, Account, byte[]): void [static]  [API 5+]
```

## DocumentsContract — declaration; API 19+ (0 +, 0 *, 0 ~, 29 -)

```
- buildChildDocumentsUri(String, String): Uri [static]  [API 19+]
- buildChildDocumentsUriUsingTree(Uri, String): Uri [static]  [API 21+]
- buildDocumentUri(String, String): Uri [static]  [API 19+]
- buildDocumentUriUsingTree(Uri, String): Uri [static]  [API 21+]
- buildRecentDocumentsUri(String, String): Uri [static]  [API 19+]
- buildRootUri(String, String): Uri [static]  [API 19+]
- buildRootsUri(String): Uri [static]  [API 19+]
- buildSearchDocumentsUri(String, String, String): Uri [static]  [API 19+]
- buildTreeDocumentUri(String, String): Uri [static]  [API 21+]
- copyDocument(ContentResolver, Uri, Uri): Uri [static]  [API 24+]
- createDocument(ContentResolver, Uri, String, String): Uri [static]  [API 21+]
- createWebLinkIntent(ContentResolver, Uri, Bundle): IntentSender [static]  [API 26+]
- deleteDocument(ContentResolver, Uri): boolean [static]  [API 19+]
- ejectRoot(ContentResolver, Uri): void [static]  [API 26+]
- findDocumentPath(ContentResolver, Uri): DocumentsContract.Path [static]  [API 26+]
- getDocumentId(Uri): String [static]  [API 19+]
- getDocumentMetadata(ContentResolver, Uri): Bundle [static]  [API 29+]
- getDocumentThumbnail(ContentResolver, Uri, Point, CancellationSignal): Bitmap [static]  [API 19+]
- getRootId(Uri): String [static]  [API 19+]
- getSearchDocumentsQuery(Uri): String [static]  [API 19+]
- getTreeDocumentId(Uri): String [static]  [API 21+]
- isChildDocument(ContentResolver, Uri, Uri): boolean [static]  [API 29+]
- isDocumentUri(Context, Uri): boolean [static]  [API 19+]
- isRootUri(Context, Uri): boolean [static]  [API 29+]
- isRootsUri(Context, Uri): boolean [static]  [API 29+]
- isTreeUri(Uri): boolean [static]  [API 24+]
- moveDocument(ContentResolver, Uri, Uri, Uri): Uri [static]  [API 24+]
- removeDocument(ContentResolver, Uri, Uri): boolean [static]  [API 24+]
- renameDocument(ContentResolver, Uri, String): Uri [static]  [API 21+]
```

## DocumentsContract.Document — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

## DocumentsContract.Path — declaration; API 26+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 26+]
* Path(String, List)  [API 26+]
* describeContents(): int  [API 26+]
* equals(Object): boolean  [API 26+]
* getPath(): List  [API 26+]
* getRootId(): String  [API 26+]
* hashCode(): int  [API 26+]
* toString(): String  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## DocumentsContract.Root — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

## DocumentsProvider — declaration; API 19+ (4 +, 0 *, 0 ~, 40 -)

```
- DocumentsProvider()  [API 19+]
- attachInfo(Context, ProviderInfo): void  [API 19+]
- call(String, String, Bundle): Bundle  [API 19+]
- canonicalize(Uri): Uri  [API 19+]
- copyDocument(String, String): String  [API 24+]
- createDocument(String, String, String): String  [API 19+]
- createWebLinkIntent(String, Bundle): IntentSender  [API 26+]
- delete(Uri, String, String[]): int  [API 19+]
- deleteDocument(String): void  [API 19+]
- ejectRoot(String): void  [API 26+]
- findDocumentPath(String, String): DocumentsContract.Path  [API 26+]
- getDocumentMetadata(String): Bundle  [API 29+]
- getDocumentStreamTypes(String, String): String[]  [API 24+]
- getDocumentType(String): String  [API 19+]
- getStreamTypes(Uri, String): String[]  [API 19+]
- getType(Uri): String  [API 19+]
- getTypeAnonymous(Uri): String  [API 19+]
- insert(Uri, ContentValues): Uri  [API 19+]
- isChildDocument(String, String): boolean  [API 21+]
- moveDocument(String, String, String): String  [API 24+]
- openAssetFile(Uri, String): AssetFileDescriptor  [API 19+]
- openAssetFile(Uri, String, CancellationSignal): AssetFileDescriptor  [API 19+]
+ openDocument(String, String, CancellationSignal): ParcelFileDescriptor  [API 19+]
- openDocumentThumbnail(String, Point, CancellationSignal): AssetFileDescriptor  [API 19+]
- openFile(Uri, String): ParcelFileDescriptor  [API 19+]
- openFile(Uri, String, CancellationSignal): ParcelFileDescriptor  [API 19+]
- openTypedAssetFile(Uri, String, Bundle): AssetFileDescriptor  [API 19+]
- openTypedAssetFile(Uri, String, Bundle, CancellationSignal): AssetFileDescriptor  [API 19+]
- openTypedDocument(String, String, Bundle, CancellationSignal): AssetFileDescriptor  [API 24+]
- query(Uri, String[], Bundle, CancellationSignal): Cursor  [API 19+]
- query(Uri, String[], String, String[], String): Cursor  [API 19+]
- query(Uri, String[], String, String[], String, CancellationSignal): Cursor  [API 19+]
- queryChildDocuments(String, String[], Bundle): Cursor  [API 26+]
+ queryChildDocuments(String, String[], String): Cursor  [API 19+]
+ queryDocument(String, String[]): Cursor  [API 19+]
- queryRecentDocuments(String, String[]): Cursor  [API 19+]
- queryRecentDocuments(String, String[], Bundle, CancellationSignal): Cursor  [API 29+]
+ queryRoots(String[]): Cursor  [API 19+]
- querySearchDocuments(String, String, String[]): Cursor  [API 19+]
- querySearchDocuments(String, String[], Bundle): Cursor  [API 29+]
- removeDocument(String, String): void  [API 24+]
- renameDocument(String, String): String  [API 21+]
- revokeDocumentPermission(String): void  [API 21+]
- update(Uri, ContentValues, String, String[]): int  [API 19+]
```

## E2eeContactKeysManager — framework; API 35+ (0 +, 0 *, 0 ~, 14 -)

```
- getAllE2eeContactKeys(String): List  [API 35+]
- getAllE2eeSelfKeys(): List  [API 35+]
- getE2eeContactKey(String, String, String): E2eeContactKeysManager.E2eeContactKey  [API 35+]
- getE2eeSelfKey(String, String): E2eeContactKeysManager.E2eeSelfKey  [API 35+]
- getMaxKeySizeBytes(): int [static]  [API 35+]
- getOwnerE2eeContactKeys(String): List  [API 35+]
- getOwnerE2eeSelfKeys(): List  [API 35+]
- removeE2eeContactKey(String, String, String): boolean  [API 35+]
- removeE2eeSelfKey(String, String): boolean  [API 35+]
- updateE2eeContactKeyLocalVerificationState(String, String, String, int): boolean  [API 35+]
- updateE2eeContactKeyRemoteVerificationState(String, String, String, int): boolean  [API 35+]
- updateE2eeSelfKeyRemoteVerificationState(String, String, int): boolean  [API 35+]
- updateOrInsertE2eeContactKey(String, String, String, byte[]): void  [API 35+]
- updateOrInsertE2eeSelfKey(String, String, byte[]): boolean  [API 35+]
```

## E2eeContactKeysManager.E2eeContactKey — framework; API 35+ (0 +, 0 *, 0 ~, 14 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
- getAccountId(): String  [API 35+]
- getDeviceId(): String  [API 35+]
- getDisplayName(): String  [API 35+]
- getEmailAddress(): String  [API 35+]
- getKeyValue(): byte[]  [API 35+]
- getLocalVerificationState(): int  [API 35+]
- getOwnerPackageName(): String  [API 35+]
- getPhoneNumber(): String  [API 35+]
- getRemoteVerificationState(): int  [API 35+]
- getTimeUpdated(): long  [API 35+]
- hashCode(): int  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## E2eeContactKeysManager.E2eeSelfKey — framework; API 35+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
- getAccountId(): String  [API 35+]
- getDeviceId(): String  [API 35+]
- getKeyValue(): byte[]  [API 35+]
- getOwnerPackageName(): String  [API 35+]
- getRemoteVerificationState(): int  [API 35+]
- getTimeUpdated(): long  [API 35+]
- hashCode(): int  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## FontRequest — declaration; API 26+, deprecated in 31 (0 +, 7 *, 0 ~, 0 -)

```
* FontRequest(String, String, String)  [API 26+]
* FontRequest(String, String, String, List)  [API 26+]
* getCertificates(): List  [API 26+]
* getProviderAuthority(): String  [API 26+]
* getProviderPackage(): String  [API 26+]
* getQuery(): String  [API 26+]
* toString(): String  [API 26+]
```

## FontsContract — declaration; API 26+, deprecated in 31 (0 +, 0 *, 0 ~, 3 -)

```
- buildTypeface(Context, CancellationSignal, FontsContract.FontInfo[]): Typeface [static]  [API 26+]
- fetchFonts(Context, CancellationSignal, FontRequest): FontsContract.FontFamilyResult [static]  [API 26+]
- requestFonts(Context, FontRequest, Handler, CancellationSignal, FontsContract.FontRequestCallback): void [static]  [API 26+]
```

## FontsContract.Columns — declaration; API 26+, deprecated in 31 (0 +, 0 *, 0 ~, 0 -)

## FontsContract.FontFamilyResult — declaration; API 26+, deprecated in 31 (0 +, 0 *, 0 ~, 2 -)

```
- getFonts(): FontsContract.FontInfo[]  [API 26+]
- getStatusCode(): int  [API 26+]
```

## FontsContract.FontInfo — declaration; API 26+, deprecated in 31 (0 +, 0 *, 0 ~, 6 -)

```
- getAxes(): FontVariationAxis[]  [API 26+]
- getResultCode(): int  [API 26+]
- getTtcIndex(): int  [API 26+]
- getUri(): Uri  [API 26+]
- getWeight(): int  [API 26+]
- isItalic(): boolean  [API 26+]
```

## FontsContract.FontRequestCallback — declaration; API 26+, deprecated in 31 (3 +, 0 *, 0 ~, 0 -)

```
+ FontRequestCallback()  [API 26+]
+ onTypefaceRequestFailed(int): void  [API 26+]
+ onTypefaceRetrieved(Typeface): void  [API 26+]
```

## LiveFolders — declaration; API 3+, deprecated in 14 (0 +, 0 *, 0 ~, 0 -)

## MediaStore — declaration; API 1+ (0 +, 0 *, 0 ~, 29 -)

```
- AUTHORITY_URI [field]  [API 29+]
- MediaStore()  [API 1+]
- canManageMedia(Context): boolean [static]  [API 31+]
- createDeleteRequest(ContentResolver, Collection): PendingIntent [static]  [API 30+]
- createFavoriteRequest(ContentResolver, Collection, boolean): PendingIntent [static]  [API 30+]
- createTrashRequest(ContentResolver, Collection, boolean): PendingIntent [static]  [API 30+]
- createWriteRequest(ContentResolver, Collection): PendingIntent [static]  [API 30+]
- getDocumentUri(Context, Uri): Uri [static]  [API 26+]
- getExternalVolumeNames(Context): Set [static]  [API 29+]
- getGeneration(Context, String): long [static]  [API 30+]
- getMediaScannerUri(): Uri [static]  [API 1+]
- getMediaUri(Context, Uri): Uri [static]  [API 29+]
- getOriginalMediaFormatFileDescriptor(Context, ParcelFileDescriptor): ParcelFileDescriptor [static]  [API 31+]
- getPickImagesMaxLimit(): int [static]  [API 33+]
- getRecentExternalVolumeNames(Context): Set [static]  [API 30+]
- getRedactedUri(ContentResolver, List): List [static]  [API 31+]
- getRedactedUri(ContentResolver, Uri): Uri [static]  [API 31+]
- getRequireOriginal(Uri): boolean [static]  [API 30+]
- getVersion(Context): String [static]  [API 12+]
- getVersion(Context, String): String [static]  [API 29+]
- getVolumeName(Uri): String [static]  [API 29+]
- isCurrentCloudMediaProviderAuthority(ContentResolver, String): boolean [static]  [API 33+]
- isCurrentSystemGallery(ContentResolver, int, String): boolean [static]  [API 31+]
- isSupportedCloudMediaProviderAuthority(ContentResolver, String): boolean [static]  [API 33+]
- notifyCloudMediaChangedEvent(ContentResolver, String, String): void [static]  [API 33+]
- openAssetFileDescriptor(ContentResolver, Uri, String, CancellationSignal): AssetFileDescriptor  [API 36+]
- openFileDescriptor(ContentResolver, Uri, String, CancellationSignal): ParcelFileDescriptor  [API 36+]
- openTypedAssetFileDescriptor(ContentResolver, Uri, String, Bundle, CancellationSignal): AssetFileDescriptor  [API 36+]
- setIncludePending(Uri): Uri [static]  [API 29+, deprecated in 30]
- setRequireOriginal(Uri): Uri [static]  [API 29+]
```

## MediaStore.Audio — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- Audio()  [API 1+]
- keyFor(String): String [static]  [API 1+, deprecated in 30]
```

## MediaStore.Audio.AlbumColumns — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.Audio.Albums — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- Albums()  [API 1+]
- EXTERNAL_CONTENT_URI [field]  [API 1+]
- INTERNAL_CONTENT_URI [field]  [API 1+]
- getContentUri(String): Uri [static]  [API 1+]
```

## MediaStore.Audio.ArtistColumns — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.Audio.Artists — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- Artists()  [API 1+]
- EXTERNAL_CONTENT_URI [field]  [API 1+]
- INTERNAL_CONTENT_URI [field]  [API 1+]
- getContentUri(String): Uri [static]  [API 1+]
```

## MediaStore.Audio.Artists.Albums — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- Albums()  [API 1+]
- getContentUri(String, long): Uri [static]  [API 1+]
```

## MediaStore.Audio.AudioColumns — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.Audio.Genres — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- EXTERNAL_CONTENT_URI [field]  [API 1+]
- Genres()  [API 1+]
- INTERNAL_CONTENT_URI [field]  [API 1+]
- getContentUri(String): Uri [static]  [API 1+]
- getContentUriForAudioId(String, int): Uri [static]  [API 11+]
```

## MediaStore.Audio.Genres.Members — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- Members()  [API 1+]
- getContentUri(String, long): Uri [static]  [API 1+]
```

## MediaStore.Audio.GenresColumns — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.Audio.Media — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- EXTERNAL_CONTENT_URI [field]  [API 1+]
- INTERNAL_CONTENT_URI [field]  [API 1+]
- Media()  [API 1+]
- getContentUri(String): Uri [static]  [API 1+]
- getContentUri(String, long): Uri [static]  [API 30+]
- getContentUriForPath(String): Uri [static]  [API 1+, deprecated in 29]
```

## MediaStore.Audio.Playlists — declaration; API 1+, deprecated in 31 (0 +, 0 *, 0 ~, 2 -)

```
- EXTERNAL_CONTENT_URI [field]  [API 1+]
- INTERNAL_CONTENT_URI [field]  [API 1+]
- Playlists()  [API 1+]
- getContentUri(String): Uri [static]  [API 1+]
```

## MediaStore.Audio.Playlists.Members — declaration; API 1+, deprecated in 31 (0 +, 0 *, 0 ~, 3 -)

```
- Members()  [API 1+]
- getContentUri(String, long): Uri [static]  [API 1+]
- moveItem(ContentResolver, long, int, int): boolean [static]  [API 8+]
```

## MediaStore.Audio.PlaylistsColumns — declaration; API 1+, deprecated in 31 (0 +, 0 *, 0 ~, 0 -)

## MediaStore.Audio.Radio — declaration; API 21+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.DownloadColumns — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.Downloads — declaration; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
- EXTERNAL_CONTENT_URI [field]  [API 29+]
- INTERNAL_CONTENT_URI [field]  [API 29+]
- getContentUri(String): Uri [static]  [API 29+]
- getContentUri(String, long): Uri [static]  [API 30+]
```

## MediaStore.Files — declaration; API 11+ (0 +, 0 *, 0 ~, 3 -)

```
- Files()  [API 11+]
- getContentUri(String): Uri [static]  [API 11+]
- getContentUri(String, long): Uri [static]  [API 11+]
```

## MediaStore.Files.FileColumns — declaration; API 11+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.Images — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ Images()  [API 1+]
```

## MediaStore.Images.ImageColumns — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.Images.Media — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- EXTERNAL_CONTENT_URI [field]  [API 1+]
- INTERNAL_CONTENT_URI [field]  [API 1+]
- Media()  [API 1+]
- getBitmap(ContentResolver, Uri): Bitmap [static]  [API 1+, deprecated in 29]
- getContentUri(String): Uri [static]  [API 1+]
- getContentUri(String, long): Uri [static]  [API 30+]
- insertImage(ContentResolver, Bitmap, String, String): String [static]  [API 1+, deprecated in 29]
- insertImage(ContentResolver, String, String, String): String [static]  [API 1+, deprecated in 29]
- query(ContentResolver, Uri, String[]): Cursor [static]  [API 1+, deprecated in 29]
- query(ContentResolver, Uri, String[], String, String): Cursor [static]  [API 1+, deprecated in 29]
- query(ContentResolver, Uri, String[], String, String[], String): Cursor [static]  [API 1+, deprecated in 29]
```

## MediaStore.Images.Thumbnails — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 10 -)

```
- EXTERNAL_CONTENT_URI [field]  [API 1+]
- INTERNAL_CONTENT_URI [field]  [API 1+]
- Thumbnails()  [API 1+]
- cancelThumbnailRequest(ContentResolver, long): void [static]  [API 5+]
- cancelThumbnailRequest(ContentResolver, long, long): void [static]  [API 8+]
- getContentUri(String): Uri [static]  [API 1+]
- getKindSize(int): Size [static]  [API 30+, deprecated in 30]
- getThumbnail(ContentResolver, long, int, BitmapFactory.Options): Bitmap [static]  [API 5+]
- getThumbnail(ContentResolver, long, long, int, BitmapFactory.Options): Bitmap [static]  [API 8+]
- query(ContentResolver, Uri, String[]): Cursor [static]  [API 1+]
- queryMiniThumbnail(ContentResolver, long, int, String[]): Cursor [static]  [API 1+]
- queryMiniThumbnails(ContentResolver, Uri, int, String[]): Cursor [static]  [API 1+]
```

## MediaStore.MediaColumns — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.PickerMediaColumns — declaration; API 33+ (0 +, 0 *, 0 ~, 0 -)

## MediaStore.Video — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- Video()  [API 1+]
- query(ContentResolver, Uri, String[]): Cursor [static]  [API 1+, deprecated in 29]
```

## MediaStore.Video.Media — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- EXTERNAL_CONTENT_URI [field]  [API 1+]
- INTERNAL_CONTENT_URI [field]  [API 1+]
- Media()  [API 1+]
- getContentUri(String): Uri [static]  [API 1+]
- getContentUri(String, long): Uri [static]  [API 30+]
```

## MediaStore.Video.Thumbnails — declaration; API 5+, deprecated in 29 (0 +, 0 *, 0 ~, 7 -)

```
- EXTERNAL_CONTENT_URI [field]  [API 5+]
- INTERNAL_CONTENT_URI [field]  [API 5+]
- Thumbnails()  [API 5+]
- cancelThumbnailRequest(ContentResolver, long): void [static]  [API 5+]
- cancelThumbnailRequest(ContentResolver, long, long): void [static]  [API 8+]
- getContentUri(String): Uri [static]  [API 5+]
- getKindSize(int): Size [static]  [API 30+, deprecated in 30]
- getThumbnail(ContentResolver, long, int, BitmapFactory.Options): Bitmap [static]  [API 5+]
- getThumbnail(ContentResolver, long, long, int, BitmapFactory.Options): Bitmap [static]  [API 8+]
```

## MediaStore.Video.VideoColumns — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## OpenableColumns — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## SearchRecentSuggestions — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ QUERIES_PROJECTION_1LINE [field]  [API 1+]
+ QUERIES_PROJECTION_2LINE [field]  [API 1+]
+ SearchRecentSuggestions(Context, String, int)  [API 1+]
+ clearHistory(): void  [API 1+]
+ saveRecentQuery(String, String): void  [API 1+]
+ truncateHistory(ContentResolver, int): void  [API 1+]
```

## Settings — framework; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
+ Settings()  [API 1+]
- canDrawOverlays(Context): boolean [static]  [API 23+]
```

## Settings.Global — declaration; API 17+ (0 +, 0 *, 0 ~, 13 -)

```
- CONTENT_URI [field]  [API 17+]
- Global()  [API 17+]
- getFloat(ContentResolver, String): float [static]  [API 17+]
- getFloat(ContentResolver, String, float): float [static]  [API 17+]
- getInt(ContentResolver, String): int [static]  [API 17+]
- getInt(ContentResolver, String, int): int [static]  [API 17+]
- getLong(ContentResolver, String): long [static]  [API 17+]
- getLong(ContentResolver, String, long): long [static]  [API 17+]
- getString(ContentResolver, String): String [static]  [API 17+]
- getUriFor(String): Uri [static]  [API 17+]
- putFloat(ContentResolver, String, float): boolean [static]  [API 17+]
- putInt(ContentResolver, String, int): boolean [static]  [API 17+]
- putLong(ContentResolver, String, long): boolean [static]  [API 17+]
- putString(ContentResolver, String, String): boolean [static]  [API 17+]
```

## Settings.NameValueTable — framework; API 1+ (1 +, 0 *, 0 ~, 2 -)

```
+ NameValueTable()  [API 1+]
- getUriFor(Uri, String): Uri [static]  [API 1+]
- putString(ContentResolver, Uri, String, String): boolean [static]  [API 1+]
```

## Settings.Panel — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

## Settings.Secure — framework; API 3+ (3 +, 0 *, 0 ~, 12 -)

```
+ CONTENT_URI [field]  [API 3+]
+ Secure()  [API 3+]
- getFloat(ContentResolver, String): float [static]  [API 3+]
- getFloat(ContentResolver, String, float): float [static]  [API 3+]
- getInt(ContentResolver, String): int [static]  [API 3+]
+ getInt(ContentResolver, String, int): int [static]  [API 3+]
- getLong(ContentResolver, String): long [static]  [API 3+]
- getLong(ContentResolver, String, long): long [static]  [API 3+]
+ getString(ContentResolver, String): String [static]  [API 3+]
- getUriFor(String): Uri [static]  [API 3+]
- isLocationProviderEnabled(ContentResolver, String): boolean [static]  [API 8+, deprecated in 19]
- putFloat(ContentResolver, String, float): boolean [static]  [API 3+]
- putInt(ContentResolver, String, int): boolean [static]  [API 3+]
- putLong(ContentResolver, String, long): boolean [static]  [API 3+]
- putString(ContentResolver, String, String): boolean [static]  [API 3+]
- setLocationProviderEnabled(ContentResolver, String, boolean): void [static]  [API 8+, deprecated in 19]
```

## Settings.SettingNotFoundException — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ SettingNotFoundException(String)  [API 1+]
```

## Settings.System — framework; API 1+ (3 +, 0 *, 2 ~, 13 -)

```
+ CONTENT_URI [field]  [API 1+]
+ DEFAULT_ALARM_ALERT_URI [field]  [API 5+]
+ DEFAULT_NOTIFICATION_URI [field]  [API 1+]
+ DEFAULT_RINGTONE_URI [field]  [API 1+]
+ System()  [API 1+]
- canWrite(Context): boolean [static]  [API 23+]
- getConfiguration(ContentResolver, Configuration): void [static]  [API 1+]
- getFloat(ContentResolver, String): float [static]  [API 1+]
- getFloat(ContentResolver, String, float): float [static]  [API 1+]
~ getInt(ContentResolver, String): int [static]  [API 1+]
+ getInt(ContentResolver, String, int): int [static]  [API 1+]
- getLong(ContentResolver, String): long [static]  [API 3+]
- getLong(ContentResolver, String, long): long [static]  [API 3+]
- getShowGTalkServiceStatus(ContentResolver): boolean [static]  [API 1+, deprecated in 17]
+ getString(ContentResolver, String): String [static]  [API 1+]
- getUriFor(String): Uri [static]  [API 1+]
- putConfiguration(ContentResolver, Configuration): boolean [static]  [API 1+]
- putFloat(ContentResolver, String, float): boolean [static]  [API 1+]
~ putInt(ContentResolver, String, int): boolean [static]  [API 1+]
- putLong(ContentResolver, String, long): boolean [static]  [API 3+]
- putString(ContentResolver, String, String): boolean [static]  [API 1+]
- setShowGTalkServiceStatus(ContentResolver, boolean): void [static]  [API 1+, deprecated in 17]
```

## SettingsSlicesContract — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

```
- BASE_URI [field]  [API 28+]
```

## SimPhonebookContract — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

```
- AUTHORITY_URI [field]  [API 31+]
```

## SimPhonebookContract.ElementaryFiles — declaration; API 31+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 31+]
- getItemUri(int, int): Uri [static]  [API 31+]
```

## SimPhonebookContract.SimRecords — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- getContentUri(int, int): Uri [static]  [API 31+]
- getEncodedNameLength(ContentResolver, String): int [static]  [API 31+]
- getItemUri(int, int, int): Uri [static]  [API 31+]
```

## SyncStateContract — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ SyncStateContract()  [API 5+]
```

## SyncStateContract.Columns — declaration; API 5+ (0 +, 0 *, 0 ~, 0 -)

## SyncStateContract.Constants — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ Constants()  [API 5+]
```

## SyncStateContract.Helpers — declaration; API 5+ (0 +, 0 *, 0 ~, 8 -)

```
- Helpers()  [API 5+]
- get(ContentProviderClient, Uri, Account): byte[] [static]  [API 5+]
- getWithUri(ContentProviderClient, Uri, Account): Pair [static]  [API 5+]
- insert(ContentProviderClient, Uri, Account, byte[]): Uri [static]  [API 5+]
- newSetOperation(Uri, Account, byte[]): ContentProviderOperation [static]  [API 5+]
- newUpdateOperation(Uri, byte[]): ContentProviderOperation [static]  [API 5+]
- set(ContentProviderClient, Uri, Account, byte[]): void [static]  [API 5+]
- update(ContentProviderClient, Uri, byte[]): void [static]  [API 5+]
```

## Telephony — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

## Telephony.BaseMmsColumns — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

## Telephony.CanonicalAddressesColumns — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

## Telephony.CarrierId — declaration; API 28+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_URI [field]  [API 28+]
- getSpecificCarrierIdUriForSubscriptionId(int): Uri [static]  [API 29+]
- getUriForSubscriptionId(int): Uri [static]  [API 28+]
```

## Telephony.Carriers — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
- SIM_APN_URI [field]  [API 29+]
```

## Telephony.Mms — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
- REPORT_REQUEST_URI [field]  [API 19+]
- REPORT_STATUS_URI [field]  [API 19+]
```

## Telephony.Mms.Addr — declaration; API 19+ (0 +, 0 *, 0 ~, 1 -)

```
- getAddrUriForMessage(String): Uri [static]  [API 30+]
```

## Telephony.Mms.Draft — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.Mms.Inbox — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.Mms.Intents — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

## Telephony.Mms.Outbox — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.Mms.Part — declaration; API 19+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 29+]
- getPartUriForMessage(String): Uri [static]  [API 30+]
```

## Telephony.Mms.Rate — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.Mms.Sent — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.MmsSms — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_CONVERSATIONS_URI [field]  [API 19+]
- CONTENT_DRAFT_URI [field]  [API 19+]
- CONTENT_FILTER_BYPHONE_URI [field]  [API 19+]
- CONTENT_LOCKED_URI [field]  [API 19+]
- CONTENT_UNDELIVERED_URI [field]  [API 19+]
- CONTENT_URI [field]  [API 19+]
- SEARCH_URI [field]  [API 19+]
```

## Telephony.MmsSms.PendingMessages — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.ServiceStateTable — declaration; API 26+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_URI [field]  [API 26+]
- getUriForSubscriptionId(int): Uri [static]  [API 26+]
- getUriForSubscriptionIdAndField(int, String): Uri [static]  [API 26+]
```

## Telephony.Sms — declaration; API 19+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 19+]
- getDefaultSmsPackage(Context): String [static]  [API 19+]
```

## Telephony.Sms.Conversations — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.Sms.Draft — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.Sms.Inbox — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.Sms.Intents — declaration; API 19+ (0 +, 0 *, 0 ~, 1 -)

```
- getMessagesFromIntent(Intent): SmsMessage[] [static]  [API 19+]
```

## Telephony.Sms.Outbox — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.Sms.Sent — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 19+]
```

## Telephony.TextBasedSmsColumns — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

## Telephony.Threads — declaration; API 19+ (0 +, 0 *, 0 ~, 2 -)

```
- CONTENT_URI [field]  [API 19+]
- OBSOLETE_THREADS_URI [field]  [API 19+]
- getOrCreateThreadId(Context, Set): long [static]  [API 23+]
- getOrCreateThreadId(Context, String): long [static]  [API 23+]
```

## Telephony.ThreadsColumns — declaration; API 19+ (0 +, 0 *, 0 ~, 0 -)

## UserDictionary — declaration; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
- CONTENT_URI [field]  [API 3+]
+ UserDictionary()  [API 3+]
```

## UserDictionary.Words — declaration; API 3+ (0 +, 0 *, 0 ~, 3 -)

```
- CONTENT_URI [field]  [API 3+]
- Words()  [API 3+]
- addWord(Context, String, int, String, Locale): void [static]  [API 16+]
- addWord(Context, String, int, int): void [static]  [API 3+, deprecated in 16]
```

## VoicemailContract — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

## VoicemailContract.Status — declaration; API 14+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 14+]
- buildSourceUri(String): Uri [static]  [API 14+]
```

## VoicemailContract.Voicemails — declaration; API 14+ (0 +, 0 *, 0 ~, 1 -)

```
- CONTENT_URI [field]  [API 14+]
- buildSourceUri(String): Uri [static]  [API 14+]
```


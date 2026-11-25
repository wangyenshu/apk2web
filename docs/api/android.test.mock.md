# android.test.mock

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## MockAccountManager — declaration; API 36+, deprecated in 36 (0 +, 0 *, 0 ~, 1 -)

```
- newMockAccountManager(Context): AccountManager  [API 36+]
```

## MockApplication — declaration; API 1+, deprecated in 24 (1 +, 0 *, 0 ~, 0 -)

```
+ MockApplication()  [API 1+]
```

## MockContentProvider — declaration; API 8+ (0 +, 0 *, 0 ~, 4 -)

```
- MockContentProvider()  [API 8+]
- MockContentProvider(Context)  [API 8+]
- MockContentProvider(Context, String, String, PathPermission[])  [API 8+]
- attachInfoForTesting(ContentProvider, Context, ProviderInfo): void  [API 36+, deprecated in 36]
```

## MockContentResolver — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- MockContentResolver()  [API 1+]
- MockContentResolver(Context)  [API 18+]
- addProvider(String, ContentProvider): void  [API 1+]
```

## MockContext — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- MockContext()  [API 1+]
- getPackageCodePath(): String  [API 1+]
- getPackageResourcePath(): String  [API 1+]
```

## MockCursor — declaration; API 8+, deprecated in 24 (0 +, 0 *, 0 ~, 15 -)

```
- MockCursor()  [API 8+]
- abortUpdates(): void  [API 8-10, removed in 11]
- commitUpdates(): boolean  [API 8-10, removed in 11]
- commitUpdates(Map): boolean  [API 8-10, removed in 11]
- deleteRow(): boolean  [API 8-10, removed in 11]
- hasUpdates(): boolean  [API 8-10, removed in 11]
- supportsUpdates(): boolean  [API 8-10, removed in 11]
- updateBlob(int, byte[]): boolean  [API 8-10, removed in 11]
- updateDouble(int, double): boolean  [API 8-10, removed in 11]
- updateFloat(int, float): boolean  [API 8-10, removed in 11]
- updateInt(int, int): boolean  [API 8-10, removed in 11]
- updateLong(int, long): boolean  [API 8-10, removed in 11]
- updateShort(int, short): boolean  [API 8-10, removed in 11]
- updateString(int, String): boolean  [API 8-10, removed in 11]
- updateToNull(int): boolean  [API 8-10, removed in 11]
```

## MockDialogInterface — declaration; API 1+, deprecated in 24 (1 +, 0 *, 0 ~, 0 -)

```
+ MockDialogInterface()  [API 1+]
```

## MockPackageManager — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 7 -)

```
- MockPackageManager()  [API 1+]
- getAllIntentFilters(String): List  [API 23+]
- getDefaultBrowserPackageName(int): String  [API 23-23, removed in 24]
- resolveServiceAsUser(Intent, int, int): ResolveInfo  [API 36+, deprecated in 36]
- setDefaultBrowserPackageName(String, int): boolean  [API 23-23, removed in 24]
- setInstantAppCookie(byte[]): boolean  [API 36+, deprecated in 36]
- setPackageObbPath(String, String): void  [API 9-9, removed in 10]
```

## MockResources — declaration; API 1+, deprecated in 24 (1 +, 0 *, 0 ~, 0 -)

```
+ MockResources()  [API 1+]
```

## MockService — declaration; API 36+, deprecated in 36 (0 +, 0 *, 0 ~, 1 -)

```
- attachForTesting(Service, Context, String, Application): void  [API 36+]
```


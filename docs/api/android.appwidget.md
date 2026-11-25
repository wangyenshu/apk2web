# android.appwidget

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AppWidgetHost — declaration; API 3+ (0 +, 0 *, 0 ~, 15 -)

```
- AppWidgetHost(Context, int)  [API 3+]
- allocateAppWidgetId(): int  [API 3+]
- clearViews(): void  [API 11+]
- createView(Context, int, AppWidgetProviderInfo): AppWidgetHostView  [API 3+]
- deleteAllHosts(): void [static]  [API 3+]
- deleteAppWidgetId(int): void  [API 3+]
- deleteHost(): void  [API 3+]
- getAppWidgetIds(): int[]  [API 26+]
- onAppWidgetRemoved(int): void  [API 30+]
- onCreateView(Context, int, AppWidgetProviderInfo): AppWidgetHostView  [API 3+]
- onProviderChanged(int, AppWidgetProviderInfo): void  [API 3+]
- onProvidersChanged(): void  [API 17+]
- startAppWidgetConfigureActivityForResult(Activity, int, int, int, Bundle): void  [API 21+]
- startListening(): void  [API 3+]
- stopListening(): void  [API 3+]
```

## AppWidgetHostView — declaration; API 3+ (0 +, 0 *, 0 ~, 22 -)

```
- AppWidgetHostView(Context)  [API 3+]
- AppWidgetHostView(Context, int, int)  [API 3+]
- dispatchDraw(Canvas): void  [API 3+]
- dispatchRestoreInstanceState(SparseArray): void  [API 3+]
- dispatchSaveInstanceState(SparseArray): void  [API 3+]
- generateLayoutParams(AttributeSet): FrameLayout.LayoutParams  [API 3+]
- getAppWidgetId(): int  [API 3+]
- getAppWidgetInfo(): AppWidgetProviderInfo  [API 3+]
- getDefaultPaddingForWidget(Context, ComponentName, Rect): Rect [static]  [API 15+]
- getDefaultView(): View  [API 3+]
- getErrorView(): View  [API 3+]
- onLayout(boolean, int, int, int, int): void  [API 3+]
- prepareView(View): void  [API 3+]
- resetColorResources(): void  [API 31+]
- setAppWidget(int, AppWidgetProviderInfo): void  [API 3+]
- setColorResources(SparseIntArray): void  [API 31+]
- setExecutor(Executor): void  [API 26+]
- setOnLightBackground(boolean): void  [API 29+]
- updateAppWidget(RemoteViews): void  [API 3+]
- updateAppWidgetOptions(Bundle): void  [API 16+]
- updateAppWidgetSize(Bundle, List): void  [API 31+]
- updateAppWidgetSize(Bundle, int, int, int, int): void  [API 16+, deprecated in 31]
```

## AppWidgetManager — framework; API 3+ (0 +, 0 *, 0 ~, 25 -)

```
- bindAppWidgetId(int, ComponentName): void  [API 3-15, removed in 16]
- bindAppWidgetIdIfAllowed(int, ComponentName): boolean  [API 16+]
- bindAppWidgetIdIfAllowed(int, ComponentName, Bundle): boolean  [API 17+]
- bindAppWidgetIdIfAllowed(int, UserHandle, ComponentName, Bundle): boolean  [API 21+]
- getAppWidgetIds(ComponentName): int[]  [API 3+]
- getAppWidgetInfo(int): AppWidgetProviderInfo  [API 3+]
- getAppWidgetOptions(int): Bundle  [API 16+]
- getInstalledProviders(): List  [API 3+]
- getInstalledProvidersForPackage(String, UserHandle): List  [API 26+]
- getInstalledProvidersForProfile(UserHandle): List  [API 21+]
- getInstance(Context): AppWidgetManager [static]  [API 3+]
- getWidgetPreview(ComponentName, UserHandle, int): RemoteViews  [API 35+]
- isRequestPinAppWidgetSupported(): boolean  [API 26+]
- notifyAppWidgetViewDataChanged(int, int): void  [API 11+, deprecated in 35]
- notifyAppWidgetViewDataChanged(int[], int): void  [API 11+, deprecated in 35]
- partiallyUpdateAppWidget(int, RemoteViews): void  [API 11+]
- partiallyUpdateAppWidget(int[], RemoteViews): void  [API 11+]
- removeWidgetPreview(ComponentName, int): void  [API 35+]
- requestPinAppWidget(ComponentName, Bundle, PendingIntent): boolean  [API 26+]
- setWidgetPreview(ComponentName, int, RemoteViews): boolean  [API 35+]
- updateAppWidget(ComponentName, RemoteViews): void  [API 3+]
- updateAppWidget(int, RemoteViews): void  [API 3+]
- updateAppWidget(int[], RemoteViews): void  [API 3+]
- updateAppWidgetOptions(int, Bundle): void  [API 16+]
- updateAppWidgetProviderInfo(ComponentName, String): void  [API 28+]
```

## AppWidgetProvider — declaration; API 3+ (0 +, 0 *, 0 ~, 8 -)

```
- AppWidgetProvider()  [API 3+]
- onAppWidgetOptionsChanged(Context, AppWidgetManager, int, Bundle): void  [API 16+]
- onDeleted(Context, int[]): void  [API 3+]
- onDisabled(Context): void  [API 3+]
- onEnabled(Context): void  [API 3+]
- onReceive(Context, Intent): void  [API 3+]
- onRestored(Context, int[], int[]): void  [API 21+]
- onUpdate(Context, AppWidgetManager, int[]): void  [API 3+]
```

## AppWidgetProviderInfo — declaration; API 3+ (0 +, 1 *, 0 ~, 11 -)

```
- AppWidgetProviderInfo()  [API 3+]
- AppWidgetProviderInfo(Parcel)  [API 3+]
- CREATOR [field]  [API 3+]
+ autoAdvanceViewId [field]  [API 11+]
* clone(): AppWidgetProviderInfo  [API 17+]
+ configure [field]  [API 3+]
- describeContents(): int  [API 3+]
+ descriptionRes [field]  [API 31+]
+ generatedPreviewCategories [field]  [API 35+]
- getActivityInfo(): ActivityInfo  [API 31+]
- getProfile(): UserHandle  [API 21+]
+ icon [field]  [API 3+]
+ initialKeyguardLayout [field]  [API 17+]
+ initialLayout [field]  [API 3+]
+ label [field]  [API 3+, deprecated in 21]
- loadDescription(Context): CharSequence  [API 31+]
- loadIcon(Context, int): Drawable  [API 21+]
- loadLabel(PackageManager): String  [API 21+]
- loadPreviewImage(Context, int): Drawable  [API 21+]
+ maxResizeHeight [field]  [API 31+]
+ maxResizeWidth [field]  [API 31+]
+ minHeight [field]  [API 3+]
+ minResizeHeight [field]  [API 14+]
+ minResizeWidth [field]  [API 14+]
+ minWidth [field]  [API 3+]
+ previewImage [field]  [API 11+]
+ previewLayout [field]  [API 31+]
+ provider [field]  [API 3+]
+ resizeMode [field]  [API 12+]
+ targetCellHeight [field]  [API 31+]
+ targetCellWidth [field]  [API 31+]
- toString(): String  [API 3+]
+ updatePeriodMillis [field]  [API 3+]
+ widgetCategory [field]  [API 17+]
+ widgetFeatures [field]  [API 28+]
- writeToParcel(Parcel, int): void  [API 3+]
```


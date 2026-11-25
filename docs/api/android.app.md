# android.app

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ActionBar — framework; API 11+ (48 +, 0 *, 14 ~, 0 -)

```
+ ActionBar()  [API 11+]
+ addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener): void  [API 11+]
+ addTab(ActionBar.Tab): void  [API 11+, deprecated in 21]
+ addTab(ActionBar.Tab, boolean): void  [API 11+, deprecated in 21]
+ addTab(ActionBar.Tab, int): void  [API 11+, deprecated in 21]
+ addTab(ActionBar.Tab, int, boolean): void  [API 11+, deprecated in 21]
+ getCustomView(): View  [API 11+]
+ getDisplayOptions(): int  [API 11+]
~ getElevation(): float  [API 21+]
+ getHeight(): int  [API 11+]
~ getHideOffset(): int  [API 21+]
+ getNavigationItemCount(): int  [API 11+, deprecated in 21]
+ getNavigationMode(): int  [API 11+, deprecated in 21]
+ getSelectedNavigationIndex(): int  [API 11+, deprecated in 21]
+ getSelectedTab(): ActionBar.Tab  [API 11+, deprecated in 21]
+ getSubtitle(): CharSequence  [API 11+]
+ getTabAt(int): ActionBar.Tab  [API 11+, deprecated in 21]
+ getTabCount(): int  [API 11+, deprecated in 21]
~ getThemedContext(): Context  [API 14+]
+ getTitle(): CharSequence  [API 11+]
+ hide(): void  [API 11+]
~ isHideOnContentScrollEnabled(): boolean  [API 21+]
+ isShowing(): boolean  [API 11+]
+ newTab(): ActionBar.Tab  [API 11+, deprecated in 21]
+ removeAllTabs(): void  [API 11+, deprecated in 21]
+ removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener): void  [API 11+]
+ removeTab(ActionBar.Tab): void  [API 11+, deprecated in 21]
+ removeTabAt(int): void  [API 11+, deprecated in 21]
+ selectTab(ActionBar.Tab): void  [API 11+, deprecated in 21]
+ setBackgroundDrawable(Drawable): void  [API 11+]
+ setCustomView(View): void  [API 11+]
+ setCustomView(View, ActionBar.LayoutParams): void  [API 11+]
+ setCustomView(int): void  [API 11+]
+ setDisplayHomeAsUpEnabled(boolean): void  [API 11+]
+ setDisplayOptions(int): void  [API 11+]
+ setDisplayOptions(int, int): void  [API 11+]
+ setDisplayShowCustomEnabled(boolean): void  [API 11+]
+ setDisplayShowHomeEnabled(boolean): void  [API 11+]
+ setDisplayShowTitleEnabled(boolean): void  [API 11+]
+ setDisplayUseLogoEnabled(boolean): void  [API 11+]
~ setElevation(float): void  [API 21+]
~ setHideOffset(int): void  [API 21+]
~ setHideOnContentScrollEnabled(boolean): void  [API 21+]
~ setHomeActionContentDescription(CharSequence): void  [API 18+]
~ setHomeActionContentDescription(int): void  [API 18+]
~ setHomeAsUpIndicator(Drawable): void  [API 18+]
~ setHomeAsUpIndicator(int): void  [API 18+]
~ setHomeButtonEnabled(boolean): void  [API 14+]
+ setIcon(Drawable): void  [API 14+]
+ setIcon(int): void  [API 14+]
+ setListNavigationCallbacks(SpinnerAdapter, ActionBar.OnNavigationListener): void  [API 11+, deprecated in 21]
+ setLogo(Drawable): void  [API 14+]
+ setLogo(int): void  [API 14+]
+ setNavigationMode(int): void  [API 11+, deprecated in 21]
+ setSelectedNavigationItem(int): void  [API 11+, deprecated in 21]
~ setSplitBackgroundDrawable(Drawable): void  [API 14+]
~ setStackedBackgroundDrawable(Drawable): void  [API 14+]
+ setSubtitle(CharSequence): void  [API 11+]
+ setSubtitle(int): void  [API 11+]
+ setTitle(CharSequence): void  [API 11+]
+ setTitle(int): void  [API 11+]
+ show(): void  [API 11+]
```

## ActionBar.LayoutParams — framework; API 11+ (6 +, 0 *, 0 ~, 0 -)

```
+ LayoutParams(ActionBar.LayoutParams)  [API 11+]
+ LayoutParams(Context, AttributeSet)  [API 11+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 11+]
+ LayoutParams(int)  [API 11+]
+ LayoutParams(int, int)  [API 11+]
+ LayoutParams(int, int, int)  [API 11+]
+ gravity [field]  [API 11+]
```

## ActionBar.OnMenuVisibilityListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onMenuVisibilityChanged(boolean): void  [API 11+]
```

## ActionBar.OnNavigationListener — framework; API 11+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ onNavigationItemSelected(int, long): boolean  [API 11+]
```

## ActionBar.Tab — framework; API 11+, deprecated in 21 (18 +, 0 *, 0 ~, 0 -)

```
+ Tab()  [API 11+]
+ getContentDescription(): CharSequence  [API 14+]
+ getCustomView(): View  [API 11+]
+ getIcon(): Drawable  [API 11+]
+ getPosition(): int  [API 11+]
+ getTag(): Object  [API 11+]
+ getText(): CharSequence  [API 11+]
+ select(): void  [API 11+]
+ setContentDescription(CharSequence): ActionBar.Tab  [API 14+]
+ setContentDescription(int): ActionBar.Tab  [API 14+]
+ setCustomView(View): ActionBar.Tab  [API 11+]
+ setCustomView(int): ActionBar.Tab  [API 11+]
+ setIcon(Drawable): ActionBar.Tab  [API 11+]
+ setIcon(int): ActionBar.Tab  [API 11+]
+ setTabListener(ActionBar.TabListener): ActionBar.Tab  [API 11+]
+ setTag(Object): ActionBar.Tab  [API 11+]
+ setText(CharSequence): ActionBar.Tab  [API 11+]
+ setText(int): ActionBar.Tab  [API 11+]
```

## ActionBar.TabListener — framework; API 11+, deprecated in 21 (3 +, 0 *, 0 ~, 0 -)

```
+ onTabReselected(ActionBar.Tab, FragmentTransaction): void  [API 11+]
+ onTabSelected(ActionBar.Tab, FragmentTransaction): void  [API 11+]
+ onTabUnselected(ActionBar.Tab, FragmentTransaction): void  [API 11+]
```

## Activity — framework; API 1+ (233 +, 0 *, 54 ~, 4 -)

```
+ Activity()  [API 1+]
+ FOCUSED_STATE_SET [field]  [API 1+]
+ addContentView(View, ViewGroup.LayoutParams): void  [API 1+]
+ attachBaseContext(Context): void  [API 1+]
~ clearOverrideActivityTransition(int): void  [API 34+]
+ closeContextMenu(): void  [API 3+]
+ closeOptionsMenu(): void  [API 1+]
+ createPendingResult(int, Intent, int): PendingIntent  [API 1+]
+ dismissDialog(int): void  [API 1+, deprecated in 15]
~ dismissKeyboardShortcutsHelper(): void  [API 24+]
+ dispatchGenericMotionEvent(MotionEvent): boolean  [API 1+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 1+]
~ dispatchKeyShortcutEvent(KeyEvent): boolean  [API 1+]
+ dispatchPopulateAccessibilityEvent(AccessibilityEvent): boolean  [API 1+]
+ dispatchTouchEvent(MotionEvent): boolean  [API 1+]
+ dispatchTrackballEvent(MotionEvent): boolean  [API 1+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 11+]
~ enterPictureInPictureMode(): void  [API 24+, deprecated in 26]
~ enterPictureInPictureMode(PictureInPictureParams): boolean  [API 26+]
+ findViewById(int): View  [API 1+]
+ finish(): void  [API 1+]
+ finishActivity(int): void  [API 1+]
~ finishActivityFromChild(Activity, int): void  [API 1+, deprecated in 30]
+ finishAffinity(): void  [API 16+]
+ finishAfterTransition(): void  [API 21+]
+ finishAndRemoveTask(): void  [API 21+]
+ finishFromChild(Activity): void  [API 1+, deprecated in 30]
+ getActionBar(): ActionBar  [API 11+]
+ getApplication(): Application  [API 1+]
~ getCaller(): ComponentCaller  [API 35+]
+ getCallingActivity(): ComponentName  [API 1+]
+ getCallingPackage(): String  [API 1+]
~ getChangingConfigurations(): int  [API 1+]
+ getComponentName(): ComponentName  [API 1+]
+ getContentScene(): Scene  [API 21+]
+ getContentTransitionManager(): TransitionManager  [API 21+]
~ getCurrentCaller(): ComponentCaller  [API 35+]
+ getCurrentFocus(): View  [API 1+]
+ getFragmentManager(): FragmentManager  [API 11+, deprecated in 28]
~ getInitialCaller(): ComponentCaller  [API 35+]
- getInstanceCount(): long  [API 1-10, removed in 11]
+ getIntent(): Intent  [API 1+]
+ getLastNonConfigurationInstance(): Object  [API 1+]
~ getLaunchedFromPackage(): String  [API 34+]
~ getLaunchedFromUid(): int  [API 34+]
+ getLayoutInflater(): LayoutInflater  [API 1+]
+ getLoaderManager(): LoaderManager  [API 11+, deprecated in 28]
+ getLocalClassName(): String  [API 1+]
~ getMaxNumPictureInPictureActions(): int  [API 26+]
+ getMediaController(): MediaController  [API 21+]
+ getMenuInflater(): MenuInflater  [API 1+]
+ getOnBackInvokedDispatcher(): OnBackInvokedDispatcher  [API 33+]
+ getParent(): Activity  [API 1+, deprecated in 35]
+ getParentActivityIntent(): Intent  [API 16+]
+ getPreferences(int): SharedPreferences  [API 1+]
~ getReferrer(): Uri  [API 22+]
~ getRequestedOrientation(): int  [API 1+]
~ getSearchEvent(): SearchEvent  [API 23+]
~ getSplashScreen(): SplashScreen  [API 31+]
+ getSystemService(String): Object  [API 1+]
~ getTaskId(): int  [API 1+]
+ getTitle(): CharSequence  [API 1+]
+ getTitleColor(): int  [API 1+]
~ getVoiceInteractor(): VoiceInteractor  [API 23+]
+ getVolumeControlStream(): int  [API 1+]
+ getWindow(): Window  [API 1+]
+ getWindowManager(): WindowManager  [API 1+]
+ hasWindowFocus(): boolean  [API 3+]
+ invalidateOptionsMenu(): void  [API 11+]
~ isActivityTransitionRunning(): boolean  [API 26+]
+ isChangingConfigurations(): boolean  [API 11+]
+ isChild(): boolean  [API 1+, deprecated in 35]
+ isDestroyed(): boolean  [API 17+]
+ isFinishing(): boolean  [API 1+]
+ isImmersive(): boolean  [API 18+]
~ isInMultiWindowMode(): boolean  [API 24+]
~ isInPictureInPictureMode(): boolean  [API 24+]
~ isLaunchedFromBubble(): boolean  [API 31+]
~ isLocalVoiceInteractionSupported(): boolean  [API 24+]
+ isTaskRoot(): boolean  [API 1+]
~ isVoiceInteraction(): boolean  [API 23+]
~ isVoiceInteractionRoot(): boolean  [API 23+]
+ managedQuery(Uri, String[], String, String[], String): Cursor  [API 1+, deprecated in 15]
~ moveTaskToBack(boolean): boolean  [API 1+]
+ navigateUpTo(Intent): boolean  [API 16+]
+ navigateUpToFromChild(Activity, Intent): boolean  [API 16+, deprecated in 30]
+ onActionModeFinished(ActionMode): void  [API 1+]
+ onActionModeStarted(ActionMode): void  [API 1+]
+ onActivityReenter(int, Intent): void  [API 21+]
+ onActivityResult(int, int, Intent): void  [API 1+]
+ onActivityResult(int, int, Intent, ComponentCaller): void  [API 35+]
+ onApplyThemeResource(Resources.Theme, int, boolean): void  [API 1+]
+ onAttachFragment(Fragment): void  [API 11+, deprecated in 28]
+ onAttachedToWindow(): void  [API 1+]
+ onBackPressed(): void  [API 5+, deprecated in 33]
+ onChildTitleChanged(Activity, CharSequence): void  [API 1+]
+ onConfigurationChanged(Configuration): void  [API 1+]
+ onContentChanged(): void  [API 1+]
+ onContextItemSelected(MenuItem): boolean  [API 1+]
+ onContextMenuClosed(Menu): void  [API 1+]
+ onCreate(Bundle): void  [API 1+]
+ onCreate(Bundle, PersistableBundle): void  [API 21+]
+ onCreateContextMenu(ContextMenu, View, ContextMenu.ContextMenuInfo): void  [API 1+]
+ onCreateDescription(): CharSequence  [API 1+]
+ onCreateDialog(int): Dialog  [API 1+, deprecated in 15]
+ onCreateDialog(int, Bundle): Dialog  [API 8+, deprecated in 15]
+ onCreateNavigateUpTaskStack(TaskStackBuilder): void  [API 16+]
+ onCreateOptionsMenu(Menu): boolean  [API 1+]
+ onCreatePanelMenu(int, Menu): boolean  [API 1+]
+ onCreatePanelView(int): View  [API 1+]
+ onCreateThumbnail(Bitmap, Canvas): boolean  [API 1+, deprecated in 28]
+ onCreateView(String, Context, AttributeSet): View  [API 1+]
+ onCreateView(View, String, Context, AttributeSet): View  [API 1+]
+ onDestroy(): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onEnterAnimationComplete(): void  [API 21+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 12+]
+ onGetDirectActions(CancellationSignal, Consumer): void  [API 29+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyLongPress(int, KeyEvent): boolean  [API 1+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 1+]
+ onKeyShortcut(int, KeyEvent): boolean  [API 11+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onLocalVoiceInteractionStarted(): void  [API 24+]
+ onLocalVoiceInteractionStopped(): void  [API 24+]
+ onLowMemory(): void  [API 1+]
+ onMenuItemSelected(int, MenuItem): boolean  [API 1+]
+ onMenuOpened(int, Menu): boolean  [API 1+]
+ onMultiWindowModeChanged(boolean): void  [API 24+, deprecated in 26]
+ onMultiWindowModeChanged(boolean, Configuration): void  [API 26+]
+ onNavigateUp(): boolean  [API 16+]
+ onNavigateUpFromChild(Activity): boolean  [API 16+, deprecated in 30]
+ onNewIntent(Intent): void  [API 1+]
+ onNewIntent(Intent, ComponentCaller): void  [API 35+]
+ onOptionsItemSelected(MenuItem): boolean  [API 1+]
+ onOptionsMenuClosed(Menu): void  [API 1+]
+ onPanelClosed(int, Menu): void  [API 1+]
+ onPause(): void  [API 1+]
+ onPerformDirectAction(String, Bundle, CancellationSignal, Consumer): void  [API 29+]
+ onPictureInPictureModeChanged(boolean): void  [API 24+, deprecated in 26]
+ onPictureInPictureModeChanged(boolean, Configuration): void  [API 26+]
+ onPictureInPictureRequested(): boolean  [API 30+]
+ onPictureInPictureUiStateChanged(PictureInPictureUiState): void  [API 31+]
+ onPostCreate(Bundle): void  [API 1+]
+ onPostCreate(Bundle, PersistableBundle): void  [API 21+]
+ onPostResume(): void  [API 1+]
+ onPrepareDialog(int, Dialog): void  [API 1+, deprecated in 15]
+ onPrepareDialog(int, Dialog, Bundle): void  [API 8+, deprecated in 15]
+ onPrepareNavigateUpTaskStack(TaskStackBuilder): void  [API 16+]
+ onPrepareOptionsMenu(Menu): boolean  [API 1+]
+ onPreparePanel(int, View, Menu): boolean  [API 1+]
+ onProvideAssistContent(AssistContent): void  [API 23+]
+ onProvideAssistData(Bundle): void  [API 18+]
+ onProvideKeyboardShortcuts(List, Menu, int): void  [API 1+]
+ onProvideReferrer(): Uri  [API 23+]
+ onRequestPermissionsResult(int, String[], int[]): void  [API 23+]
+ onRequestPermissionsResult(int, String[], int[], int): void  [API 35+]
+ onRestart(): void  [API 1+]
+ onRestoreInstanceState(Bundle): void  [API 1+]
+ onRestoreInstanceState(Bundle, PersistableBundle): void  [API 21+]
+ onResume(): void  [API 1+]
+ onRetainNonConfigurationInstance(): Object  [API 1+]
+ onSaveInstanceState(Bundle): void  [API 1+]
+ onSaveInstanceState(Bundle, PersistableBundle): void  [API 21+]
+ onSearchRequested(): boolean  [API 1+]
+ onSearchRequested(SearchEvent): boolean  [API 1+]
+ onStart(): void  [API 1+]
+ onStateNotSaved(): void  [API 23+, deprecated in 29]
+ onStop(): void  [API 1+]
+ onTitleChanged(CharSequence, int): void  [API 1+]
+ onTopResumedActivityChanged(boolean): void  [API 29+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ onTrackballEvent(MotionEvent): boolean  [API 1+]
+ onTrimMemory(int): void  [API 1+]
+ onUserInteraction(): void  [API 3+]
+ onUserLeaveHint(): void  [API 3+]
+ onVisibleBehindCanceled(): void  [API 21+, deprecated in 26]
+ onWindowAttributesChanged(WindowManager.LayoutParams): void  [API 1+]
+ onWindowFocusChanged(boolean): void  [API 1+]
+ onWindowStartingActionMode(ActionMode.Callback): ActionMode  [API 1+]
+ onWindowStartingActionMode(ActionMode.Callback, int): ActionMode  [API 1+]
+ openContextMenu(View): void  [API 1+]
+ openOptionsMenu(): void  [API 1+]
+ overrideActivityTransition(int, int, int): void  [API 34+]
~ overrideActivityTransition(int, int, int, int): void  [API 34+]
~ overridePendingTransition(int, int): void  [API 5+, deprecated in 34]
+ overridePendingTransition(int, int, int): void  [API 33+, deprecated in 34]
~ postponeEnterTransition(): void  [API 21+]
+ recreate(): void  [API 11+]
+ registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks): void  [API 29+]
~ registerComponentCallbacks(ComponentCallbacks): void  [API 1+]
+ registerForContextMenu(View): void  [API 1+]
+ registerScreenCaptureCallback(Executor, Activity.ScreenCaptureCallback): void  [API 34+]
~ releaseInstance(): boolean  [API 21+]
+ removeDialog(int): void  [API 1+, deprecated in 15]
~ reportFullyDrawn(): void  [API 19+]
~ requestDragAndDropPermissions(DragEvent): DragAndDropPermissions  [API 24+]
+ requestFullscreenMode(int, OutcomeReceiver): void  [API 34+]
+ requestPermissions(String[], int): void  [API 23+]
+ requestPermissions(String[], int, int): void  [API 35+]
~ requestShowKeyboardShortcuts(): void  [API 24+]
~ requestVisibleBehind(boolean): boolean  [API 21+, deprecated in 26]
+ requestWindowFeature(int): boolean  [API 1+]
+ requireViewById(int): View  [API 28+]
+ runOnUiThread(Runnable): void  [API 1+]
+ setActionBar(Toolbar): void  [API 21+]
~ setAllowCrossUidActivitySwitchFromBelow(boolean): void  [API 35+]
+ setContentTransitionManager(TransitionManager): void  [API 21+]
+ setContentView(View): void  [API 1+]
+ setContentView(View, ViewGroup.LayoutParams): void  [API 1+]
+ setContentView(int): void  [API 1+]
~ setDefaultKeyMode(int): void  [API 1+]
+ setEnterSharedElementCallback(SharedElementCallback): void  [API 21+]
+ setExitSharedElementCallback(SharedElementCallback): void  [API 21+]
+ setFeatureDrawable(int, Drawable): void  [API 1+]
+ setFeatureDrawableAlpha(int, int): void  [API 1+]
+ setFeatureDrawableResource(int, int): void  [API 1+]
+ setFeatureDrawableUri(int, Uri): void  [API 1+]
+ setFinishOnTouchOutside(boolean): void  [API 11+]
+ setImmersive(boolean): void  [API 18+]
+ setInheritShowWhenLocked(boolean): void  [API 29+]
+ setIntent(Intent): void  [API 1+]
+ setIntent(Intent, ComponentCaller): void  [API 35+]
~ setLocusContext(LocusId, Bundle): void  [API 30+]
+ setMediaController(MediaController): void  [API 21+]
- setPersistent(boolean): void  [API 1-10, removed in 11]
+ setPictureInPictureParams(PictureInPictureParams): void  [API 26+]
+ setProgress(int): void  [API 1+, deprecated in 24]
+ setProgressBarIndeterminate(boolean): void  [API 1+, deprecated in 24]
+ setProgressBarIndeterminateVisibility(boolean): void  [API 1+, deprecated in 24]
+ setProgressBarVisibility(boolean): void  [API 1+, deprecated in 24]
+ setRecentsScreenshotEnabled(boolean): void  [API 33+]
~ setRequestedOrientation(int): void  [API 1+]
+ setResult(int): void  [API 1+]
+ setResult(int, Intent): void  [API 1+]
+ setSecondaryProgress(int): void  [API 1+, deprecated in 24]
+ setShouldDockBigOverlays(boolean): void  [API 33+]
+ setShowWhenLocked(boolean): void  [API 27+]
- setTaskDescription(ActivityManager.TaskDescription): void  [API 21+]
+ setTheme(int): void  [API 1+]
+ setTitle(CharSequence): void  [API 1+]
+ setTitle(int): void  [API 1+]
+ setTitleColor(int): void  [API 1+, deprecated in 21]
+ setTranslucent(boolean): boolean  [API 30+]
+ setTurnScreenOn(boolean): void  [API 27+]
+ setVisible(boolean): void  [API 3+]
+ setVolumeControlStream(int): void  [API 1+]
~ setVrModeEnabled(boolean, ComponentName): void  [API 24+]
+ shouldDockBigOverlays(): boolean  [API 33+]
~ shouldShowRequestPermissionRationale(String): boolean  [API 23+]
+ shouldShowRequestPermissionRationale(String, int): boolean  [API 35+]
~ shouldUpRecreateTask(Intent): boolean  [API 16+]
~ showAssist(Bundle): boolean  [API 23+]
+ showDialog(int): void  [API 1+, deprecated in 15]
+ showDialog(int, Bundle): boolean  [API 8+, deprecated in 15]
~ showLockTaskEscapeMessage(): void  [API 23+]
+ startActionMode(ActionMode.Callback): ActionMode  [API 11+]
+ startActionMode(ActionMode.Callback, int): ActionMode  [API 23+]
+ startActivities(Intent[]): void  [API 1+]
+ startActivities(Intent[], Bundle): void  [API 1+]
+ startActivity(Intent): void  [API 1+]
+ startActivity(Intent, Bundle): void  [API 1+]
+ startActivityForResult(Intent, int): void  [API 1+]
+ startActivityForResult(Intent, int, Bundle): void  [API 16+]
+ startActivityFromChild(Activity, Intent, int): void  [API 1+, deprecated in 30]
+ startActivityFromChild(Activity, Intent, int, Bundle): void  [API 16+, deprecated in 30]
+ startActivityFromFragment(Fragment, Intent, int): void  [API 11+, deprecated in 28]
+ startActivityFromFragment(Fragment, Intent, int, Bundle): void  [API 16+, deprecated in 28]
+ startActivityIfNeeded(Intent, int): boolean  [API 1+]
+ startActivityIfNeeded(Intent, int, Bundle): boolean  [API 16+]
+ startIntentSender(IntentSender, Intent, int, int, int): void  [API 1+]
+ startIntentSender(IntentSender, Intent, int, int, int, Bundle): void  [API 1+]
+ startIntentSenderForResult(IntentSender, int, Intent, int, int, int): void  [API 5+]
+ startIntentSenderForResult(IntentSender, int, Intent, int, int, int, Bundle): void  [API 16+]
+ startIntentSenderFromChild(Activity, IntentSender, int, Intent, int, int, int): void  [API 5+, deprecated in 30]
+ startIntentSenderFromChild(Activity, IntentSender, int, Intent, int, int, int, Bundle): void  [API 16+, deprecated in 30]
~ startLocalVoiceInteraction(Bundle): void  [API 24+]
~ startLockTask(): void  [API 21+]
+ startManagingCursor(Cursor): void  [API 1+, deprecated in 15]
~ startNextMatchingActivity(Intent): boolean  [API 1+]
~ startNextMatchingActivity(Intent, Bundle): boolean  [API 16+]
~ startPostponedEnterTransition(): void  [API 21+]
+ startSearch(String, boolean, Bundle, boolean): void  [API 1+]
~ stopLocalVoiceInteraction(): void  [API 24+]
~ stopLockTask(): void  [API 21+]
+ stopManagingCursor(Cursor): void  [API 1+, deprecated in 15]
~ takeKeyEvents(boolean): void  [API 1+]
+ triggerSearch(String, Bundle): void  [API 5+]
+ unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks): void  [API 29+]
~ unregisterComponentCallbacks(ComponentCallbacks): void  [API 1+]
+ unregisterForContextMenu(View): void  [API 1+]
+ unregisterScreenCaptureCallback(Activity.ScreenCaptureCallback): void  [API 34+]
```

## Activity.ScreenCaptureCallback — framework; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onScreenCaptured(): void  [API 34+]
```

## ActivityGroup — framework; API 1+, deprecated in 13 (10 +, 0 *, 0 ~, 0 -)

```
+ ActivityGroup()  [API 1+]
+ ActivityGroup(boolean)  [API 1+]
+ getCurrentActivity(): Activity  [API 1+]
+ getLocalActivityManager(): LocalActivityManager  [API 1+]
+ onCreate(Bundle): void  [API 1+]
+ onDestroy(): void  [API 1+]
+ onPause(): void  [API 1+]
+ onResume(): void  [API 1+]
+ onSaveInstanceState(Bundle): void  [API 1+]
+ onStop(): void  [API 1+]
```

## ActivityManager — framework; API 1+ (6 +, 0 *, 7 ~, 29 -)

```
- addAppTask(Activity, Intent, ActivityManager.TaskDescription, Bitmap): int  [API 21+]
- addApplicationStartInfoCompletionListener(Executor, Consumer): void  [API 35+]
- addStartInfoTimestamp(int, long): void  [API 35+]
- appNotResponding(String): void  [API 30+]
- clearApplicationUserData(): boolean  [API 19+]
- clearWatchHeapLimit(): void  [API 23+]
- dumpPackageState(FileDescriptor, String): void  [API 19+]
- getAppTaskThumbnailSize(): Size  [API 21+]
- getAppTasks(): List  [API 21+]
+ getDeviceConfigurationInfo(): ConfigurationInfo  [API 3+]
- getHistoricalProcessExitReasons(String, int, int): List  [API 30+]
- getHistoricalProcessStartReasons(int): List  [API 35+]
~ getLargeMemoryClass(): int  [API 11+]
- getLauncherLargeIconDensity(): int  [API 11+]
- getLauncherLargeIconSize(): int  [API 11+]
- getLockTaskModeState(): int  [API 23+]
~ getMemoryClass(): int  [API 5+]
+ getMemoryInfo(ActivityManager.MemoryInfo): void  [API 1+]
- getMyMemoryState(ActivityManager.RunningAppProcessInfo): void [static]  [API 16+]
+ getProcessMemoryInfo(int[]): Debug.MemoryInfo[]  [API 5+]
- getProcessesInErrorState(): List  [API 1+]
- getRecentTasks(int, int): List  [API 1+, deprecated in 21]
+ getRunningAppProcesses(): List  [API 3+]
- getRunningServiceControlPanel(ComponentName): PendingIntent  [API 5+]
+ getRunningServices(int): List  [API 1+, deprecated in 26]
+ getRunningTasks(int): List  [API 1+, deprecated in 21]
- isActivityStartAllowedOnDisplay(Context, int, Intent): boolean  [API 29+]
- isBackgroundRestricted(): boolean  [API 28+]
- isInLockTaskMode(): boolean  [API 21+, deprecated in 23]
- isLowMemoryKillReportSupported(): boolean [static]  [API 30+]
~ isLowRamDevice(): boolean  [API 19+]
~ isRunningInTestHarness(): boolean [static]  [API 11+, deprecated in 29]
- isRunningInUserTestHarness(): boolean [static]  [API 29+]
~ isUserAMonkey(): boolean [static]  [API 8+]
~ killBackgroundProcesses(String): void  [API 8+]
- moveTaskToFront(int, int): void  [API 11+]
- moveTaskToFront(int, int, Bundle): void  [API 16+]
- removeApplicationStartInfoCompletionListener(Consumer): void  [API 35+]
~ restartPackage(String): void  [API 3+, deprecated in 15]
- setProcessStateSummary(byte[]): void  [API 30+]
- setVrThread(int): void [static]  [API 25+]
- setWatchHeapLimit(long): void  [API 23+]
```

## ActivityManager.AppTask — declaration; API 21+ (0 +, 0 *, 0 ~, 5 -)

```
- finishAndRemoveTask(): void  [API 21+]
- getTaskInfo(): ActivityManager.RecentTaskInfo  [API 21+]
- moveToFront(): void  [API 21+]
- setExcludeFromRecents(boolean): void  [API 21+]
- startActivity(Context, Intent, Bundle): void  [API 21+]
```

## ActivityManager.MemoryInfo — framework; API 1+ (1 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 1+]
+ MemoryInfo()  [API 1+]
- advertisedMem [field]  [API 34+]
+ availMem [field]  [API 1+]
- describeContents(): int  [API 1+]
+ lowMemory [field]  [API 1+]
- readFromParcel(Parcel): void  [API 1+]
+ threshold [field]  [API 1+]
+ totalMem [field]  [API 16+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ActivityManager.ProcessErrorStateInfo — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 1+]
- ProcessErrorStateInfo()  [API 1+]
+ condition [field]  [API 1+]
+ crashData [field]  [API 1+]
- describeContents(): int  [API 1+]
+ longMsg [field]  [API 1+]
+ pid [field]  [API 1+]
+ processName [field]  [API 1+]
- readFromParcel(Parcel): void  [API 1+]
+ shortMsg [field]  [API 1+]
+ stackTrace [field]  [API 8+]
+ tag [field]  [API 1+]
+ uid [field]  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ActivityManager.RecentTaskInfo — framework; API 1+ (1 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 1+]
+ RecentTaskInfo()  [API 1+]
- affiliatedTaskId [field]  [API 21+, deprecated in 29]
- baseActivity [field]  [API 23-28, removed in 29]
- baseIntent [field]  [API 1-28, removed in 29]
- describeContents(): int  [API 1+]
- description [field]  [API 11+, deprecated in 29]
+ id [field]  [API 1+, deprecated in 29]
- numActivities [field]  [API 23-28, removed in 29]
- origActivity [field]  [API 1-28, removed in 29]
- persistentId [field]  [API 12+, deprecated in 29]
- readFromParcel(Parcel): void  [API 1+]
- taskDescription [field]  [API 21-28, removed in 29]
- topActivity [field]  [API 23-28, removed in 29]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ActivityManager.RunningAppProcessInfo — framework; API 3+ (1 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 3+]
+ RunningAppProcessInfo()  [API 3+]
- RunningAppProcessInfo(String, int, String[])  [API 3+]
- describeContents(): int  [API 3+]
+ importance [field]  [API 3+]
- importanceReasonCode [field]  [API 5+]
- importanceReasonComponent [field]  [API 5+]
- importanceReasonPid [field]  [API 5+]
- lastTrimLevel [field]  [API 16+]
- lru [field]  [API 3+]
+ pid [field]  [API 3+]
+ pkgList [field]  [API 3+]
+ processName [field]  [API 3+]
- readFromParcel(Parcel): void  [API 3+]
- uid [field]  [API 5+]
- writeToParcel(Parcel, int): void  [API 3+]
```

## ActivityManager.RunningServiceInfo — framework; API 1+ (1 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 1+]
+ RunningServiceInfo()  [API 1+]
- activeSince [field]  [API 1+]
- clientCount [field]  [API 1+]
- clientLabel [field]  [API 5+]
- clientPackage [field]  [API 5+]
- crashCount [field]  [API 1+]
- describeContents(): int  [API 1+]
- flags [field]  [API 5+]
- foreground [field]  [API 1+]
- lastActivityTime [field]  [API 1+]
+ pid [field]  [API 1+]
- process [field]  [API 1+]
- readFromParcel(Parcel): void  [API 1+]
- restarting [field]  [API 1+]
+ service [field]  [API 1+]
- started [field]  [API 1+]
- uid [field]  [API 5+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ActivityManager.RunningTaskInfo — framework; API 1+ (1 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 1+]
+ RunningTaskInfo()  [API 1+]
+ baseActivity [field]  [API 1-28, removed in 29]
- describeContents(): int  [API 1+]
+ description [field]  [API 1+, deprecated in 29]
+ id [field]  [API 1+, deprecated in 29]
+ numActivities [field]  [API 1-28, removed in 29]
+ numRunning [field]  [API 1+, deprecated in 29]
- readFromParcel(Parcel): void  [API 1+]
- thumbnail [field]  [API 1+, deprecated in 29]
+ topActivity [field]  [API 1-28, removed in 29]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ActivityManager.TaskDescription — declaration; API 21+ (0 +, 13 *, 0 ~, 6 -)

```
+ CREATOR [field]  [API 21+]
- TaskDescription()  [API 21+, deprecated in 33]
- TaskDescription(ActivityManager.TaskDescription)  [API 21+]
* TaskDescription(String)  [API 21+, deprecated in 33]
* TaskDescription(String, Bitmap)  [API 21+, deprecated in 28]
- TaskDescription(String, Bitmap, int)  [API 21+, deprecated in 28]
- TaskDescription(String, int)  [API 28+, deprecated in 33]
- TaskDescription(String, int, int)  [API 28+, deprecated in 33]
* describeContents(): int  [API 21+]
* equals(Object): boolean  [API 21+]
* getBackgroundColor(): int  [API 33+]
* getIcon(): Bitmap  [API 21+, deprecated in 30]
* getLabel(): String  [API 21+]
* getNavigationBarColor(): int  [API 33+]
* getPrimaryColor(): int  [API 21+]
* getStatusBarColor(): int  [API 33+]
* hashCode(): int  [API 21+]
- readFromParcel(Parcel): void  [API 21+]
* toString(): String  [API 21+]
* writeToParcel(Parcel, int): void  [API 21+]
```

## ActivityManager.TaskDescription.Builder — declaration; API 33+ (0 +, 7 *, 0 ~, 1 -)

```
* Builder()  [API 33+]
* build(): ActivityManager.TaskDescription  [API 33+]
* setBackgroundColor(int): ActivityManager.TaskDescription.Builder  [API 33+]
- setIcon(int): ActivityManager.TaskDescription.Builder  [API 33+]
* setLabel(String): ActivityManager.TaskDescription.Builder  [API 33+]
* setNavigationBarColor(int): ActivityManager.TaskDescription.Builder  [API 33+]
* setPrimaryColor(int): ActivityManager.TaskDescription.Builder  [API 33+]
* setStatusBarColor(int): ActivityManager.TaskDescription.Builder  [API 33+]
```

## ActivityOptions — declaration; API 16+ (0 +, 14 *, 0 ~, 17 -)

```
* getLaunchBounds(): Rect  [API 24+]
* getLaunchDisplayId(): int  [API 26+]
- getLockTaskMode(): boolean  [API 28+]
* getPendingIntentBackgroundActivityStartMode(): int  [API 34+]
* getPendingIntentCreatorBackgroundActivityStartMode(): int  [API 34+]
* getSplashScreenStyle(): int  [API 33+]
* isPendingIntentBackgroundActivityLaunchAllowed(): boolean  [API 33+, deprecated in 34]
* isShareIdentityEnabled(): boolean  [API 34+]
- makeBasic(): ActivityOptions [static]  [API 23+]
- makeClipRevealAnimation(View, int, int, int, int): ActivityOptions [static]  [API 23+]
- makeCustomAnimation(Context, int, int): ActivityOptions [static]  [API 16+]
- makeCustomAnimation(Context, int, int, int): ActivityOptions [static]  [API 33+]
- makeLaunchIntoPip(PictureInPictureParams): ActivityOptions [static]  [API 33+]
- makeScaleUpAnimation(View, int, int, int, int): ActivityOptions [static]  [API 16+]
- makeSceneTransitionAnimation(Activity, Pair[]): ActivityOptions [static]  [API 21+]
- makeSceneTransitionAnimation(Activity, View, String): ActivityOptions [static]  [API 21+]
- makeTaskLaunchBehind(): ActivityOptions [static]  [API 21+]
- makeThumbnailScaleUpAnimation(View, Bitmap, int, int): ActivityOptions [static]  [API 16+]
- requestUsageTimeReport(PendingIntent): void  [API 23+]
- setAppVerificationBundle(Bundle): ActivityOptions  [API 26+]
* setLaunchBounds(Rect): ActivityOptions  [API 24+]
* setLaunchDisplayId(int): ActivityOptions  [API 26+]
- setLockTaskEnabled(boolean): ActivityOptions  [API 28+]
* setPendingIntentBackgroundActivityLaunchAllowed(boolean): void  [API 33+, deprecated in 34]
* setPendingIntentBackgroundActivityStartMode(int): ActivityOptions  [API 34+]
* setPendingIntentCreatorBackgroundActivityStartMode(int): ActivityOptions  [API 34+]
* setShareIdentityEnabled(boolean): ActivityOptions  [API 34+]
* setSplashScreenStyle(int): ActivityOptions  [API 33+]
- toBundle(): Bundle  [API 16+]
- toString(): String  [API 16+]
- update(ActivityOptions): void  [API 16+]
```

## AlarmManager — framework; API 1+ (12 +, 0 *, 4 ~, 3 -)

```
~ canScheduleExactAlarms(): boolean  [API 31+]
+ cancel(AlarmManager.OnAlarmListener): void  [API 24+]
+ cancel(PendingIntent): void  [API 1+]
- cancelAll(): void  [API 34+]
~ getNextAlarmClock(): AlarmManager.AlarmClockInfo  [API 21+]
+ set(int, long, PendingIntent): void  [API 1+]
+ set(int, long, String, AlarmManager.OnAlarmListener, Handler): void  [API 24+]
+ setAlarmClock(AlarmManager.AlarmClockInfo, PendingIntent): void  [API 21+]
+ setAndAllowWhileIdle(int, long, PendingIntent): void  [API 23+]
+ setExact(int, long, PendingIntent): void  [API 19+]
+ setExact(int, long, String, AlarmManager.OnAlarmListener, Handler): void  [API 24+]
+ setExactAndAllowWhileIdle(int, long, PendingIntent): void  [API 23+]
+ setInexactRepeating(int, long, long, PendingIntent): void  [API 3+]
+ setRepeating(int, long, long, PendingIntent): void  [API 1+]
~ setTime(long): void  [API 8+]
~ setTimeZone(String): void  [API 1+]
+ setWindow(int, long, long, PendingIntent): void  [API 19+]
- setWindow(int, long, long, String, AlarmManager.OnAlarmListener, Handler): void  [API 24+]
- setWindow(int, long, long, String, Executor, AlarmManager.OnAlarmListener): void  [API 34+]
```

## AlarmManager.AlarmClockInfo — framework; API 21+ (3 +, 0 *, 2 ~, 0 -)

```
+ AlarmClockInfo(long, PendingIntent)  [API 21+]
- CREATOR [field]  [API 21+]
~ describeContents(): int  [API 21+]
+ getShowIntent(): PendingIntent  [API 21+]
+ getTriggerTime(): long  [API 21+]
~ writeToParcel(Parcel, int): void  [API 21+]
```

## AlarmManager.OnAlarmListener — framework; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ onAlarm(): void  [API 24+]
```

## AlertDialog — framework; API 1+ (23 +, 0 *, 2 ~, 0 -)

```
+ AlertDialog(Context)  [API 1+]
+ AlertDialog(Context, boolean, DialogInterface.OnCancelListener)  [API 1+]
+ AlertDialog(Context, int)  [API 1+]
+ getButton(int): Button  [API 3+]
+ getListView(): ListView  [API 3+]
+ onCreate(Bundle): void  [API 1+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ setButton(CharSequence, DialogInterface.OnClickListener): void  [API 1+, deprecated in 15]
+ setButton(CharSequence, Message): void  [API 1+, deprecated in 15]
+ setButton(int, CharSequence, DialogInterface.OnClickListener): void  [API 3+]
+ setButton(int, CharSequence, Message): void  [API 3+]
+ setButton2(CharSequence, DialogInterface.OnClickListener): void  [API 1+, deprecated in 15]
+ setButton2(CharSequence, Message): void  [API 1+, deprecated in 15]
+ setButton3(CharSequence, DialogInterface.OnClickListener): void  [API 1+, deprecated in 15]
+ setButton3(CharSequence, Message): void  [API 1+, deprecated in 15]
+ setCustomTitle(View): void  [API 1+]
+ setIcon(Drawable): void  [API 1+]
+ setIcon(int): void  [API 1+]
~ setIconAttribute(int): void  [API 11+]
~ setInverseBackgroundForced(boolean): void  [API 1+]
+ setMessage(CharSequence): void  [API 1+]
+ setTitle(CharSequence): void  [API 1+]
+ setView(View): void  [API 1+]
+ setView(View, int, int, int, int): void  [API 3+]
```

## AlertDialog.Builder — framework; API 1+ (36 +, 0 *, 2 ~, 0 -)

```
+ Builder(Context)  [API 1+]
+ Builder(Context, int)  [API 11+]
+ create(): AlertDialog  [API 1+]
+ getContext(): Context  [API 11+]
+ setAdapter(ListAdapter, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setCancelable(boolean): AlertDialog.Builder  [API 1+]
+ setCursor(Cursor, DialogInterface.OnClickListener, String): AlertDialog.Builder  [API 1+]
+ setCustomTitle(View): AlertDialog.Builder  [API 1+]
+ setIcon(Drawable): AlertDialog.Builder  [API 1+]
+ setIcon(int): AlertDialog.Builder  [API 1+]
~ setIconAttribute(int): AlertDialog.Builder  [API 11+]
~ setInverseBackgroundForced(boolean): AlertDialog.Builder  [API 1+, deprecated in 23]
+ setItems(CharSequence[], DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setItems(int, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setMessage(CharSequence): AlertDialog.Builder  [API 1+]
+ setMessage(int): AlertDialog.Builder  [API 1+]
+ setMultiChoiceItems(CharSequence[], boolean[], DialogInterface.OnMultiChoiceClickListener): AlertDialog.Builder  [API 1+]
+ setMultiChoiceItems(Cursor, String, String, DialogInterface.OnMultiChoiceClickListener): AlertDialog.Builder  [API 1+]
+ setMultiChoiceItems(int, boolean[], DialogInterface.OnMultiChoiceClickListener): AlertDialog.Builder  [API 1+]
+ setNegativeButton(CharSequence, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setNegativeButton(int, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setNeutralButton(CharSequence, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setNeutralButton(int, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setOnCancelListener(DialogInterface.OnCancelListener): AlertDialog.Builder  [API 1+]
+ setOnDismissListener(DialogInterface.OnDismissListener): AlertDialog.Builder  [API 17+]
+ setOnItemSelectedListener(AdapterView.OnItemSelectedListener): AlertDialog.Builder  [API 1+]
+ setOnKeyListener(DialogInterface.OnKeyListener): AlertDialog.Builder  [API 1+]
+ setPositiveButton(CharSequence, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setPositiveButton(int, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setSingleChoiceItems(CharSequence[], int, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setSingleChoiceItems(Cursor, int, String, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setSingleChoiceItems(ListAdapter, int, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setSingleChoiceItems(int, int, DialogInterface.OnClickListener): AlertDialog.Builder  [API 1+]
+ setTitle(CharSequence): AlertDialog.Builder  [API 1+]
+ setTitle(int): AlertDialog.Builder  [API 1+]
+ setView(View): AlertDialog.Builder  [API 1+]
+ setView(int): AlertDialog.Builder  [API 21+]
+ show(): AlertDialog  [API 1+]
```

## AliasActivity — declaration; API 1+, deprecated in 30 (0 +, 0 *, 0 ~, 2 -)

```
- AliasActivity()  [API 1+]
- onCreate(Bundle): void  [API 1+]
```

## AppComponentFactory — declaration; API 28+ (0 +, 0 *, 0 ~, 7 -)

```
- AppComponentFactory()  [API 28+]
- instantiateActivity(ClassLoader, String, Intent): Activity  [API 28+]
- instantiateApplication(ClassLoader, String): Application  [API 28+]
- instantiateClassLoader(ClassLoader, ApplicationInfo): ClassLoader  [API 29+]
- instantiateProvider(ClassLoader, String): ContentProvider  [API 28+]
- instantiateReceiver(ClassLoader, String, Intent): BroadcastReceiver  [API 28+]
- instantiateService(ClassLoader, String, Intent): Service  [API 28+]
```

## AppOpsManager — framework; API 19+ (0 +, 0 *, 0 ~, 33 -)

```
- checkOp(String, int, String): int  [API 19+, deprecated in 29]
- checkOpNoThrow(String, int, String): int  [API 19+, deprecated in 29]
- checkPackage(int, String): void  [API 19+, deprecated in 30]
- finishOp(String, int, String): void  [API 19+, deprecated in 30]
- finishOp(String, int, String, String): void  [API 30+]
- finishProxyOp(String, int, String, String): void  [API 31+]
- isOpActive(String, int, String): boolean  [API 30+]
- noteOp(String, int, String): int  [API 19+, deprecated in 30]
- noteOp(String, int, String, String, String): int  [API 30+]
- noteOpNoThrow(String, int, String): int  [API 19+, deprecated in 30]
- noteOpNoThrow(String, int, String, String, String): int  [API 30+]
- noteProxyOp(String, String): int  [API 23+, deprecated in 30]
- noteProxyOp(String, String, int, String, String): int  [API 30+]
- noteProxyOpNoThrow(String, String): int  [API 23+, deprecated in 30]
- noteProxyOpNoThrow(String, String, int): int  [API 29+, deprecated in 30]
- noteProxyOpNoThrow(String, String, int, String, String): int  [API 30+]
- permissionToOp(String): String [static]  [API 23+]
- setOnOpNotedCallback(Executor, AppOpsManager.OnOpNotedCallback): void  [API 30+]
- startOp(String, int, String): int  [API 19+, deprecated in 30]
- startOp(String, int, String, String, String): int  [API 30+]
- startOpNoThrow(String, int, String): int  [API 19+, deprecated in 30]
- startOpNoThrow(String, int, String, String, String): int  [API 30+]
- startProxyOp(String, int, String, String, String): int  [API 31+]
- startProxyOpNoThrow(String, int, String, String, String): int  [API 31+]
- startWatchingActive(String[], Executor, AppOpsManager.OnOpActiveChangedListener): void  [API 30+]
- startWatchingMode(String, String, AppOpsManager.OnOpChangedListener): void  [API 19+]
- startWatchingMode(String, String, int, AppOpsManager.OnOpChangedListener): void  [API 29+]
- stopWatchingActive(AppOpsManager.OnOpActiveChangedListener): void  [API 30+]
- stopWatchingMode(AppOpsManager.OnOpChangedListener): void  [API 19+]
- unsafeCheckOp(String, int, String): int  [API 29+]
- unsafeCheckOpNoThrow(String, int, String): int  [API 29+]
- unsafeCheckOpRaw(String, int, String): int  [API 29+]
- unsafeCheckOpRawNoThrow(String, int, String): int  [API 29+]
```

## AppOpsManager.OnOpActiveChangedListener — framework; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ onOpActiveChanged(String, int, String, String, int, boolean, int, int): void  [API 35+]
+ onOpActiveChanged(String, int, String, boolean): void  [API 30+]
```

## AppOpsManager.OnOpChangedListener — framework; API 19+ (2 +, 0 *, 0 ~, 0 -)

```
+ onOpChanged(String, String): void  [API 19+]
+ onOpChanged(String, String, int, String): void  [API 35+]
```

## AppOpsManager.OnOpNotedCallback — framework; API 30+ (4 +, 0 *, 0 ~, 0 -)

```
+ OnOpNotedCallback()  [API 30+]
+ onAsyncNoted(AsyncNotedAppOp): void  [API 30+]
+ onNoted(SyncNotedAppOp): void  [API 30+]
+ onSelfNoted(SyncNotedAppOp): void  [API 30+]
```

## Application — framework; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ Application()  [API 1+]
+ getProcessName(): String [static]  [API 28+]
+ onConfigurationChanged(Configuration): void  [API 1+]
+ onCreate(): void  [API 1+]
+ onLowMemory(): void  [API 1+]
+ onTerminate(): void  [API 1+]
+ onTrimMemory(int): void  [API 1+]
+ registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks): void  [API 14+]
+ registerComponentCallbacks(ComponentCallbacks): void  [API 1+]
+ registerOnProvideAssistDataListener(Application.OnProvideAssistDataListener): void  [API 18+]
+ unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks): void  [API 14+]
+ unregisterComponentCallbacks(ComponentCallbacks): void  [API 1+]
+ unregisterOnProvideAssistDataListener(Application.OnProvideAssistDataListener): void  [API 18+]
```

## Application.ActivityLifecycleCallbacks — framework; API 14+ (21 +, 0 *, 0 ~, 0 -)

```
+ onActivityCreated(Activity, Bundle): void  [API 14+]
+ onActivityDestroyed(Activity): void  [API 14+]
+ onActivityPaused(Activity): void  [API 14+]
+ onActivityPostCreated(Activity, Bundle): void  [API 29+]
+ onActivityPostDestroyed(Activity): void  [API 29+]
+ onActivityPostPaused(Activity): void  [API 29+]
+ onActivityPostResumed(Activity): void  [API 29+]
+ onActivityPostSaveInstanceState(Activity, Bundle): void  [API 29+]
+ onActivityPostStarted(Activity): void  [API 29+]
+ onActivityPostStopped(Activity): void  [API 29+]
+ onActivityPreCreated(Activity, Bundle): void  [API 29+]
+ onActivityPreDestroyed(Activity): void  [API 29+]
+ onActivityPrePaused(Activity): void  [API 29+]
+ onActivityPreResumed(Activity): void  [API 29+]
+ onActivityPreSaveInstanceState(Activity, Bundle): void  [API 29+]
+ onActivityPreStarted(Activity): void  [API 29+]
+ onActivityPreStopped(Activity): void  [API 29+]
+ onActivityResumed(Activity): void  [API 14+]
+ onActivitySaveInstanceState(Activity, Bundle): void  [API 14+]
+ onActivityStarted(Activity): void  [API 14+]
+ onActivityStopped(Activity): void  [API 14+]
```

## Application.OnProvideAssistDataListener — framework; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ onProvideAssistData(Activity, Bundle): void  [API 18+]
```

## ApplicationErrorReport — declaration; API 14+ (0 +, 0 *, 0 ~, 6 -)

```
- ApplicationErrorReport()  [API 14+]
- CREATOR [field]  [API 14+]
+ anrInfo [field]  [API 14+]
+ batteryInfo [field]  [API 14+]
+ crashInfo [field]  [API 14+]
- describeContents(): int  [API 14+]
- dump(Printer, String): void  [API 14+]
- getErrorReportReceiver(Context, String, int): ComponentName [static]  [API 14+]
+ installerPackageName [field]  [API 14+]
+ packageName [field]  [API 14+]
+ processName [field]  [API 14+]
- readFromParcel(Parcel): void  [API 14+]
+ runningServiceInfo [field]  [API 14+]
+ systemApp [field]  [API 14+]
+ time [field]  [API 14+]
+ type [field]  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```

## ApplicationErrorReport.AnrInfo — declaration; API 14+ (0 +, 0 *, 0 ~, 4 -)

```
- AnrInfo()  [API 14+]
- AnrInfo(Parcel)  [API 14+]
+ activity [field]  [API 14+]
+ cause [field]  [API 14+]
- dump(Printer, String): void  [API 14+]
+ info [field]  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```

## ApplicationErrorReport.BatteryInfo — declaration; API 14+ (0 +, 0 *, 0 ~, 4 -)

```
- BatteryInfo()  [API 14+]
- BatteryInfo(Parcel)  [API 14+]
+ checkinDetails [field]  [API 14+]
- dump(Printer, String): void  [API 14+]
+ durationMicros [field]  [API 14+]
+ usageDetails [field]  [API 14+]
+ usagePercent [field]  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```

## ApplicationErrorReport.CrashInfo — declaration; API 14+ (0 +, 0 *, 0 ~, 5 -)

```
- CrashInfo()  [API 14+]
- CrashInfo(Parcel)  [API 14+]
- CrashInfo(Throwable)  [API 14+]
- dump(Printer, String): void  [API 14+]
+ exceptionClassName [field]  [API 14+]
+ exceptionMessage [field]  [API 14+]
+ stackTrace [field]  [API 14+]
+ throwClassName [field]  [API 14+]
+ throwFileName [field]  [API 14+]
+ throwLineNumber [field]  [API 14+]
+ throwMethodName [field]  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```

## ApplicationErrorReport.RunningServiceInfo — declaration; API 14+ (0 +, 0 *, 0 ~, 4 -)

```
- RunningServiceInfo()  [API 14+]
- RunningServiceInfo(Parcel)  [API 14+]
- dump(Printer, String): void  [API 14+]
+ durationMillis [field]  [API 14+]
+ serviceDetails [field]  [API 14+]
- writeToParcel(Parcel, int): void  [API 14+]
```

## ApplicationExitInfo — declaration; API 30+ (0 +, 0 *, 0 ~, 20 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getDefiningUid(): int  [API 30+]
- getDescription(): String  [API 30+]
- getImportance(): int  [API 30+]
- getPackageUid(): int  [API 30+]
- getPid(): int  [API 30+]
- getProcessName(): String  [API 30+]
- getProcessStateSummary(): byte[]  [API 30+]
- getPss(): long  [API 30+]
- getRealUid(): int  [API 30+]
- getReason(): int  [API 30+]
- getRss(): long  [API 30+]
- getStatus(): int  [API 30+]
- getTimestamp(): long  [API 30+]
- getTraceInputStream(): InputStream  [API 30+]
- getUserHandle(): UserHandle  [API 30+]
- hashCode(): int  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## ApplicationStartInfo — declaration; API 35+ (0 +, 0 *, 0 ~, 16 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
- getDefiningUid(): int  [API 35+]
- getIntent(): Intent  [API 35+]
- getLaunchMode(): int  [API 35+]
- getPackageUid(): int  [API 35+]
- getPid(): int  [API 35+]
- getProcessName(): String  [API 35+]
- getRealUid(): int  [API 35+]
- getReason(): int  [API 35+]
- getStartType(): int  [API 35+]
- getStartupState(): int  [API 35+]
- getStartupTimestamps(): Map  [API 35+]
- hashCode(): int  [API 35+]
- wasForceStopped(): boolean  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## AsyncNotedAppOp — declaration; API 30+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getAttributionTag(): String  [API 30+]
- getMessage(): String  [API 30+]
- getNotingUid(): int  [API 30+]
- getOp(): String  [API 30+]
- getTime(): long  [API 30+]
- hashCode(): int  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## AuthenticationRequiredException — declaration; API 26+ (0 +, 0 *, 0 ~, 4 -)

```
- AuthenticationRequiredException(Throwable, PendingIntent)  [API 26+]
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getUserAction(): PendingIntent  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## AutomaticZenRule — declaration; API 24+ (0 +, 26 *, 0 ~, 2 -)

```
- AutomaticZenRule(Parcel)  [API 24+]
- AutomaticZenRule(String, ComponentName, ComponentName, Uri, ZenPolicy, int, boolean)  [API 29+]
* AutomaticZenRule(String, ComponentName, Uri, int, boolean)  [API 24+, deprecated in 29]
+ CREATOR [field]  [API 24+]
* describeContents(): int  [API 24+]
* equals(Object): boolean  [API 24+]
* getConditionId(): Uri  [API 24+]
* getConfigurationActivity(): ComponentName  [API 29+]
* getCreationTime(): long  [API 24+]
* getDeviceEffects(): ZenDeviceEffects  [API 35+]
* getIconResId(): int  [API 35+]
* getInterruptionFilter(): int  [API 24+]
* getName(): String  [API 24+]
* getOwner(): ComponentName  [API 24+]
* getTriggerDescription(): String  [API 35+]
* getType(): int  [API 35+]
* getZenPolicy(): ZenPolicy  [API 29+]
* hashCode(): int  [API 24+]
* isEnabled(): boolean  [API 24+]
* isManualInvocationAllowed(): boolean  [API 35+]
* setConditionId(Uri): void  [API 24+]
* setConfigurationActivity(ComponentName): void  [API 29+]
* setDeviceEffects(ZenDeviceEffects): void  [API 35+]
* setEnabled(boolean): void  [API 24+]
* setInterruptionFilter(int): void  [API 24+]
* setName(String): void  [API 24+]
* setZenPolicy(ZenPolicy): void  [API 29+]
* toString(): String  [API 24+]
* writeToParcel(Parcel, int): void  [API 24+]
```

## AutomaticZenRule.Builder — declaration; API 35+ (0 +, 15 *, 0 ~, 0 -)

```
* Builder(AutomaticZenRule)  [API 35+]
* Builder(String, Uri)  [API 35+]
* build(): AutomaticZenRule  [API 35+]
* setConditionId(Uri): AutomaticZenRule.Builder  [API 35+]
* setConfigurationActivity(ComponentName): AutomaticZenRule.Builder  [API 35+]
* setDeviceEffects(ZenDeviceEffects): AutomaticZenRule.Builder  [API 35+]
* setEnabled(boolean): AutomaticZenRule.Builder  [API 35+]
* setIconResId(int): AutomaticZenRule.Builder  [API 35+]
* setInterruptionFilter(int): AutomaticZenRule.Builder  [API 35+]
* setManualInvocationAllowed(boolean): AutomaticZenRule.Builder  [API 35+]
* setName(String): AutomaticZenRule.Builder  [API 35+]
* setOwner(ComponentName): AutomaticZenRule.Builder  [API 35+]
* setTriggerDescription(String): AutomaticZenRule.Builder  [API 35+]
* setType(int): AutomaticZenRule.Builder  [API 35+]
* setZenPolicy(ZenPolicy): AutomaticZenRule.Builder  [API 35+]
```

## BackgroundServiceStartNotAllowedException — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- BackgroundServiceStartNotAllowedException(String)  [API 31+]
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## BroadcastOptions — declaration; API 34+ (0 +, 6 *, 0 ~, 8 -)

```
- clearDeferralPolicy(): void  [API 34+]
- clearDeliveryGroupMatchingKey(): void  [API 34+]
- clearDeliveryGroupPolicy(): void  [API 34+]
- fromBundle(Bundle): BroadcastOptions [static]  [API 34+]
* getDeferralPolicy(): int  [API 34+]
- getDeliveryGroupMatchingKey(): String  [API 34+]
* getDeliveryGroupPolicy(): int  [API 34+]
* isShareIdentityEnabled(): boolean  [API 34+]
- makeBasic(): BroadcastOptions [static]  [API 34+]
* setDeferralPolicy(int): BroadcastOptions  [API 34+]
- setDeliveryGroupMatchingKey(String, String): BroadcastOptions  [API 34+]
* setDeliveryGroupPolicy(int): BroadcastOptions  [API 34+]
* setShareIdentityEnabled(boolean): BroadcastOptions  [API 34+]
- toBundle(): Bundle  [API 34+]
```

## ComponentCaller — declaration; API 35+ (0 +, 0 *, 0 ~, 5 -)

```
- checkContentUriPermission(Uri, int): int  [API 35+]
- equals(Object): boolean  [API 35+]
- getPackage(): String  [API 35+]
- getUid(): int  [API 35+]
- hashCode(): int  [API 35+]
```

## DatePickerDialog — framework; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ DatePickerDialog(Context)  [API 24+]
+ DatePickerDialog(Context, DatePickerDialog.OnDateSetListener, int, int, int)  [API 1+]
+ DatePickerDialog(Context, int)  [API 24+]
+ DatePickerDialog(Context, int, DatePickerDialog.OnDateSetListener, int, int, int)  [API 1+]
+ getDatePicker(): DatePicker  [API 11+]
+ onClick(DialogInterface, int): void  [API 1+]
+ onDateChanged(DatePicker, int, int, int): void  [API 1+]
+ onRestoreInstanceState(Bundle): void  [API 1+]
+ onSaveInstanceState(): Bundle  [API 1+]
+ setOnDateSetListener(DatePickerDialog.OnDateSetListener): void  [API 24+]
+ updateDate(int, int, int): void  [API 1+]
```

## DatePickerDialog.OnDateSetListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDateSet(DatePicker, int, int, int): void  [API 1+]
```

## Dialog — framework; API 1+ (85 +, 0 *, 7 ~, 0 -)

```
+ Dialog(Context)  [API 1+]
+ Dialog(Context, boolean, DialogInterface.OnCancelListener)  [API 1+]
+ Dialog(Context, int)  [API 1+]
+ addContentView(View, ViewGroup.LayoutParams): void  [API 1+]
+ cancel(): void  [API 1+]
+ closeOptionsMenu(): void  [API 1+]
+ create(): void  [API 21+]
+ dismiss(): void  [API 1+]
+ dispatchGenericMotionEvent(MotionEvent): boolean  [API 1+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 1+]
~ dispatchKeyShortcutEvent(KeyEvent): boolean  [API 1+]
~ dispatchPopulateAccessibilityEvent(AccessibilityEvent): boolean  [API 1+]
+ dispatchTouchEvent(MotionEvent): boolean  [API 1+]
~ dispatchTrackballEvent(MotionEvent): boolean  [API 1+]
+ findViewById(int): View  [API 1+]
+ getActionBar(): ActionBar  [API 11+]
+ getContext(): Context  [API 1+]
+ getCurrentFocus(): View  [API 1+]
+ getLayoutInflater(): LayoutInflater  [API 1+]
+ getOnBackInvokedDispatcher(): OnBackInvokedDispatcher  [API 33+]
+ getOwnerActivity(): Activity  [API 1+]
~ getSearchEvent(): SearchEvent  [API 23+]
+ getVolumeControlStream(): int  [API 1+]
+ getWindow(): Window  [API 1+]
+ hide(): void  [API 1+]
+ invalidateOptionsMenu(): void  [API 11+]
+ isShowing(): boolean  [API 1+]
+ onActionModeFinished(ActionMode): void  [API 1+]
+ onActionModeStarted(ActionMode): void  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onBackPressed(): void  [API 5+, deprecated in 33]
+ onContentChanged(): void  [API 1+]
+ onContextItemSelected(MenuItem): boolean  [API 1+]
+ onContextMenuClosed(Menu): void  [API 1+]
+ onCreate(Bundle): void  [API 1+]
+ onCreateContextMenu(ContextMenu, View, ContextMenu.ContextMenuInfo): void  [API 1+]
+ onCreateOptionsMenu(Menu): boolean  [API 1+]
+ onCreatePanelMenu(int, Menu): boolean  [API 1+]
+ onCreatePanelView(int): View  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 12+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyLongPress(int, KeyEvent): boolean  [API 1+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 1+]
+ onKeyShortcut(int, KeyEvent): boolean  [API 11+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onMenuItemSelected(int, MenuItem): boolean  [API 1+]
+ onMenuOpened(int, Menu): boolean  [API 1+]
+ onOptionsItemSelected(MenuItem): boolean  [API 1+]
+ onOptionsMenuClosed(Menu): void  [API 1+]
+ onPanelClosed(int, Menu): void  [API 1+]
+ onPrepareOptionsMenu(Menu): boolean  [API 1+]
+ onPreparePanel(int, View, Menu): boolean  [API 1+]
+ onRestoreInstanceState(Bundle): void  [API 1+]
+ onSaveInstanceState(): Bundle  [API 1+]
+ onSearchRequested(): boolean  [API 1+]
+ onSearchRequested(SearchEvent): boolean  [API 1+]
+ onStart(): void  [API 1+]
+ onStop(): void  [API 1+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ onTrackballEvent(MotionEvent): boolean  [API 1+]
+ onWindowAttributesChanged(WindowManager.LayoutParams): void  [API 1+]
+ onWindowFocusChanged(boolean): void  [API 1+]
+ onWindowStartingActionMode(ActionMode.Callback): ActionMode  [API 1+]
+ onWindowStartingActionMode(ActionMode.Callback, int): ActionMode  [API 1+]
+ openContextMenu(View): void  [API 1+]
+ openOptionsMenu(): void  [API 1+]
+ registerForContextMenu(View): void  [API 1+]
+ requestWindowFeature(int): boolean  [API 1+]
+ requireViewById(int): View  [API 28+]
~ setCancelMessage(Message): void  [API 1+]
+ setCancelable(boolean): void  [API 1+]
+ setCanceledOnTouchOutside(boolean): void  [API 1+]
+ setContentView(View): void  [API 1+]
+ setContentView(View, ViewGroup.LayoutParams): void  [API 1+]
+ setContentView(int): void  [API 1+]
~ setDismissMessage(Message): void  [API 1+]
+ setFeatureDrawable(int, Drawable): void  [API 1+]
+ setFeatureDrawableAlpha(int, int): void  [API 1+]
+ setFeatureDrawableResource(int, int): void  [API 1+]
+ setFeatureDrawableUri(int, Uri): void  [API 1+]
+ setOnCancelListener(DialogInterface.OnCancelListener): void  [API 1+]
+ setOnDismissListener(DialogInterface.OnDismissListener): void  [API 1+]
+ setOnKeyListener(DialogInterface.OnKeyListener): void  [API 1+]
+ setOnShowListener(DialogInterface.OnShowListener): void  [API 8+]
+ setOwnerActivity(Activity): void  [API 1+]
+ setTitle(CharSequence): void  [API 1+]
+ setTitle(int): void  [API 1+]
+ setVolumeControlStream(int): void  [API 1+]
+ show(): void  [API 1+]
~ takeKeyEvents(boolean): void  [API 1+]
+ unregisterForContextMenu(View): void  [API 1+]
```

## DialogFragment — framework; API 11+, deprecated in 28 (24 +, 0 *, 0 ~, 1 -)

```
+ DialogFragment()  [API 11+]
+ dismiss(): void  [API 11+]
+ dismissAllowingStateLoss(): void  [API 12+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 11+]
+ getDialog(): Dialog  [API 11+]
+ getShowsDialog(): boolean  [API 11+]
+ getTheme(): int  [API 11+]
+ isCancelable(): boolean  [API 11+]
+ onActivityCreated(Bundle): void  [API 11+]
+ onAttach(Context): void  [API 11+]
+ onCancel(DialogInterface): void  [API 11+]
+ onCreate(Bundle): void  [API 11+]
+ onCreateDialog(Bundle): Dialog  [API 11+]
+ onDestroyView(): void  [API 11+]
+ onDetach(): void  [API 11+]
+ onDismiss(DialogInterface): void  [API 11+]
+ onGetLayoutInflater(Bundle): LayoutInflater  [API 11+]
+ onSaveInstanceState(Bundle): void  [API 11+]
+ onStart(): void  [API 11+]
+ onStop(): void  [API 11+]
+ setCancelable(boolean): void  [API 11+]
+ setShowsDialog(boolean): void  [API 11+]
+ setStyle(int, int): void  [API 11+]
+ show(FragmentManager, String): void  [API 11+]
+ show(FragmentTransaction, String): int  [API 11+]
```

## DirectAction — declaration; API 29+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 29+]
* describeContents(): int  [API 29+]
* equals(Object): boolean  [API 29+]
* getExtras(): Bundle  [API 29+]
* getId(): String  [API 29+]
* getLocusId(): LocusId  [API 29+]
* hashCode(): int  [API 29+]
* writeToParcel(Parcel, int): void  [API 29+]
```

## DirectAction.Builder — declaration; API 29+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder(String)  [API 29+]
* build(): DirectAction  [API 29+]
* setExtras(Bundle): DirectAction.Builder  [API 29+]
* setLocusId(LocusId): DirectAction.Builder  [API 29+]
```

## DownloadManager — framework; API 9+ (2 +, 0 *, 1 ~, 7 -)

```
- addCompletedDownload(String, String, boolean, String, String, long, boolean): long  [API 12+, deprecated in 29]
- addCompletedDownload(String, String, boolean, String, String, long, boolean, Uri, Uri): long  [API 24+, deprecated in 29]
+ enqueue(DownloadManager.Request): long  [API 9+]
- getMaxBytesOverMobile(Context): Long [static]  [API 11+]
- getMimeTypeForDownloadedFile(long): String  [API 11+]
- getRecommendedMaxBytesOverMobile(Context): Long [static]  [API 11+]
- getUriForDownloadedFile(long): Uri  [API 11+]
- openDownloadedFile(long): ParcelFileDescriptor  [API 9+]
+ query(DownloadManager.Query): Cursor  [API 9+]
~ remove(long[]): int  [API 9+]
```

## DownloadManager.Query — framework; API 9+ (1 +, 0 *, 2 ~, 0 -)

```
+ Query()  [API 9+]
~ setFilterById(long[]): DownloadManager.Query  [API 9+]
~ setFilterByStatus(int): DownloadManager.Query  [API 9+]
```

## DownloadManager.Request — framework; API 9+ (1 +, 0 *, 11 ~, 5 -)

```
+ Request(Uri)  [API 9+]
~ addRequestHeader(String, String): DownloadManager.Request  [API 9+]
- allowScanningByMediaScanner(): void  [API 11+, deprecated in 29]
~ setAllowedNetworkTypes(int): DownloadManager.Request  [API 9+]
- setAllowedOverMetered(boolean): DownloadManager.Request  [API 16+]
~ setAllowedOverRoaming(boolean): DownloadManager.Request  [API 9+]
~ setDescription(CharSequence): DownloadManager.Request  [API 9+]
- setDestinationInExternalFilesDir(Context, String, String): DownloadManager.Request  [API 9+]
~ setDestinationInExternalPublicDir(String, String): DownloadManager.Request  [API 9+]
~ setDestinationUri(Uri): DownloadManager.Request  [API 9+]
~ setMimeType(String): DownloadManager.Request  [API 9+]
~ setNotificationVisibility(int): DownloadManager.Request  [API 11+]
- setRequiresCharging(boolean): DownloadManager.Request  [API 24+]
- setRequiresDeviceIdle(boolean): DownloadManager.Request  [API 24+]
~ setShowRunningNotification(boolean): DownloadManager.Request  [API 9+, deprecated in 15]
~ setTitle(CharSequence): DownloadManager.Request  [API 9+]
~ setVisibleInDownloadsUi(boolean): DownloadManager.Request  [API 9+, deprecated in 29]
```

## ExpandableListActivity — framework; API 1+, deprecated in 30 (14 +, 0 *, 0 ~, 0 -)

```
+ ExpandableListActivity()  [API 1+]
+ getExpandableListAdapter(): ExpandableListAdapter  [API 1+]
+ getExpandableListView(): ExpandableListView  [API 1+]
+ getSelectedId(): long  [API 1+]
+ getSelectedPosition(): long  [API 1+]
+ onChildClick(ExpandableListView, View, int, int, long): boolean  [API 1+]
+ onContentChanged(): void  [API 1+]
+ onCreateContextMenu(ContextMenu, View, ContextMenu.ContextMenuInfo): void  [API 1+]
+ onGroupCollapse(int): void  [API 1+]
+ onGroupExpand(int): void  [API 1+]
+ onRestoreInstanceState(Bundle): void  [API 1+]
+ setListAdapter(ExpandableListAdapter): void  [API 1+]
+ setSelectedChild(int, int, boolean): boolean  [API 1+]
+ setSelectedGroup(int): void  [API 1+]
```

## ForegroundServiceStartNotAllowedException — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 31+]
- ForegroundServiceStartNotAllowedException(String)  [API 31+]
- describeContents(): int  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## ForegroundServiceTypeException — declaration; API 34+ (0 +, 0 *, 0 ~, 1 -)

```
- ForegroundServiceTypeException(String)  [API 34+]
```

## Fragment — framework; API 11+, deprecated in 28 (83 +, 0 *, 15 ~, 11 -)

```
+ Fragment()  [API 11+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 11+]
+ equals(Object): boolean  [API 11+]
+ getActivity(): Activity  [API 11+]
- getAllowEnterTransitionOverlap(): boolean  [API 21+]
- getAllowReturnTransitionOverlap(): boolean  [API 21+]
+ getArguments(): Bundle  [API 11+]
+ getChildFragmentManager(): FragmentManager  [API 17+]
+ getContext(): Context  [API 23+]
- getEnterTransition(): Transition  [API 21+]
- getExitTransition(): Transition  [API 21+]
+ getFragmentManager(): FragmentManager  [API 11+]
+ getHost(): Object  [API 23+]
+ getId(): int  [API 11+]
+ getLayoutInflater(): LayoutInflater  [API 26+]
+ getLoaderManager(): LoaderManager  [API 11+]
+ getParentFragment(): Fragment  [API 17+]
- getReenterTransition(): Transition  [API 21+]
+ getResources(): Resources  [API 11+]
+ getRetainInstance(): boolean  [API 11+]
- getReturnTransition(): Transition  [API 21+]
- getSharedElementEnterTransition(): Transition  [API 21+]
- getSharedElementReturnTransition(): Transition  [API 21+]
+ getString(int): String  [API 11+]
+ getString(int, Object[]): String  [API 11+]
+ getTag(): String  [API 11+]
+ getTargetFragment(): Fragment  [API 11+]
+ getTargetRequestCode(): int  [API 11+]
+ getText(int): CharSequence  [API 11+]
+ getUserVisibleHint(): boolean  [API 15+]
+ getView(): View  [API 11+]
+ hashCode(): int  [API 11+]
+ instantiate(Context, String): Fragment [static]  [API 11+]
+ instantiate(Context, String, Bundle): Fragment [static]  [API 11+]
+ isAdded(): boolean  [API 11+]
+ isDetached(): boolean  [API 13+]
+ isHidden(): boolean  [API 11+]
+ isInLayout(): boolean  [API 11+]
+ isRemoving(): boolean  [API 11+]
+ isResumed(): boolean  [API 11+]
~ isStateSaved(): boolean  [API 26+]
+ isVisible(): boolean  [API 11+]
+ onActivityCreated(Bundle): void  [API 11+]
+ onActivityResult(int, int, Intent): void  [API 11+]
+ onAttach(Activity): void  [API 11+, deprecated in 23]
+ onAttach(Context): void  [API 23+]
+ onAttachFragment(Fragment): void  [API 24+]
+ onConfigurationChanged(Configuration): void  [API 11+]
+ onContextItemSelected(MenuItem): boolean  [API 11+]
+ onCreate(Bundle): void  [API 11+]
+ onCreateAnimator(int, boolean, int): Animator  [API 11+]
+ onCreateContextMenu(ContextMenu, View, ContextMenu.ContextMenuInfo): void  [API 11+]
+ onCreateOptionsMenu(Menu, MenuInflater): void  [API 11+]
+ onCreateView(LayoutInflater, ViewGroup, Bundle): View  [API 11+]
+ onDestroy(): void  [API 11+]
+ onDestroyOptionsMenu(): void  [API 11+]
+ onDestroyView(): void  [API 11+]
+ onDetach(): void  [API 11+]
+ onGetLayoutInflater(Bundle): LayoutInflater  [API 26+]
+ onHiddenChanged(boolean): void  [API 11+]
+ onInflate(Activity, AttributeSet, Bundle): void  [API 12+, deprecated in 23]
+ onInflate(AttributeSet, Bundle): void  [API 11+, deprecated in 15]
+ onInflate(Context, AttributeSet, Bundle): void  [API 23+]
+ onLowMemory(): void  [API 11+]
+ onMultiWindowModeChanged(boolean): void  [API 24+, deprecated in 26]
- onMultiWindowModeChanged(boolean, Configuration): void  [API 26+]
+ onOptionsItemSelected(MenuItem): boolean  [API 11+]
+ onOptionsMenuClosed(Menu): void  [API 11+]
+ onPause(): void  [API 11+]
+ onPictureInPictureModeChanged(boolean): void  [API 24+, deprecated in 26]
- onPictureInPictureModeChanged(boolean, Configuration): void  [API 26+]
+ onPrepareOptionsMenu(Menu): void  [API 11+]
+ onRequestPermissionsResult(int, String[], int[]): void  [API 23+]
+ onResume(): void  [API 11+]
+ onSaveInstanceState(Bundle): void  [API 11+]
+ onStart(): void  [API 11+]
+ onStop(): void  [API 11+]
+ onTrimMemory(int): void  [API 11+]
+ onViewCreated(View, Bundle): void  [API 13+]
+ onViewStateRestored(Bundle): void  [API 17+]
~ postponeEnterTransition(): void  [API 26+]
+ registerForContextMenu(View): void  [API 11+]
+ requestPermissions(String[], int): void  [API 23+]
~ setAllowEnterTransitionOverlap(boolean): void  [API 21+]
~ setAllowReturnTransitionOverlap(boolean): void  [API 21+]
+ setArguments(Bundle): void  [API 11+]
~ setEnterSharedElementCallback(SharedElementCallback): void  [API 21+]
~ setEnterTransition(Transition): void  [API 21+]
~ setExitSharedElementCallback(SharedElementCallback): void  [API 21+]
~ setExitTransition(Transition): void  [API 21+]
+ setHasOptionsMenu(boolean): void  [API 11+]
+ setInitialSavedState(Fragment.SavedState): void  [API 13+]
+ setMenuVisibility(boolean): void  [API 14+]
~ setReenterTransition(Transition): void  [API 21+]
+ setRetainInstance(boolean): void  [API 11+]
~ setReturnTransition(Transition): void  [API 21+]
~ setSharedElementEnterTransition(Transition): void  [API 21+]
~ setSharedElementReturnTransition(Transition): void  [API 21+]
+ setTargetFragment(Fragment, int): void  [API 11+]
+ setUserVisibleHint(boolean): void  [API 15+]
~ shouldShowRequestPermissionRationale(String): boolean  [API 23+]
+ startActivity(Intent): void  [API 11+]
+ startActivity(Intent, Bundle): void  [API 16+]
+ startActivityForResult(Intent, int): void  [API 11+]
+ startActivityForResult(Intent, int, Bundle): void  [API 16+]
~ startIntentSenderForResult(IntentSender, int, Intent, int, int, int, Bundle): void  [API 24+]
~ startPostponedEnterTransition(): void  [API 26+]
+ toString(): String  [API 11+]
+ unregisterForContextMenu(View): void  [API 11+]
```

## Fragment.InstantiationException — framework; API 11+, deprecated in 28 (1 +, 0 *, 0 ~, 0 -)

```
+ InstantiationException(String, Exception)  [API 11+]
```

## Fragment.SavedState — framework; API 13+, deprecated in 28 (0 +, 0 *, 2 ~, 0 -)

```
- CREATOR [field]  [API 13+]
~ describeContents(): int  [API 13+]
~ writeToParcel(Parcel, int): void  [API 13+]
```

## FragmentBreadCrumbs — declaration; API 11+, deprecated in 21 (0 +, 0 *, 0 ~, 11 -)

```
- FragmentBreadCrumbs(Context)  [API 11+]
- FragmentBreadCrumbs(Context, AttributeSet)  [API 11+]
- FragmentBreadCrumbs(Context, AttributeSet, int)  [API 11+]
- onBackStackChanged(): void  [API 11+]
- onLayout(boolean, int, int, int, int): void  [API 11+]
- onMeasure(int, int): void  [API 11+]
- setActivity(Activity): void  [API 11+]
- setMaxVisible(int): void  [API 11+]
- setOnBreadCrumbClickListener(FragmentBreadCrumbs.OnBreadCrumbClickListener): void  [API 12+]
- setParentTitle(CharSequence, CharSequence, View.OnClickListener): void  [API 11+]
- setTitle(CharSequence, CharSequence): void  [API 11+]
```

## FragmentBreadCrumbs.OnBreadCrumbClickListener — declaration; API 12+, deprecated in 28 (1 +, 0 *, 0 ~, 0 -)

```
+ onBreadCrumbClick(FragmentManager.BackStackEntry, int): boolean  [API 12+]
```

## FragmentContainer — declaration; API 23+, deprecated in 28 (3 +, 0 *, 0 ~, 0 -)

```
+ FragmentContainer()  [API 23+]
+ onFindViewById(int): View  [API 23+]
+ onHasView(): boolean  [API 23+]
```

## FragmentController — declaration; API 23+, deprecated in 28 (0 +, 0 *, 0 ~, 40 -)

```
- attachHost(Fragment): void  [API 23+]
- createController(FragmentHostCallback): FragmentController [static]  [API 23+]
- dispatchActivityCreated(): void  [API 23+]
- dispatchConfigurationChanged(Configuration): void  [API 23+]
- dispatchContextItemSelected(MenuItem): boolean  [API 23+]
- dispatchCreate(): void  [API 23+]
- dispatchCreateOptionsMenu(Menu, MenuInflater): boolean  [API 23+]
- dispatchDestroy(): void  [API 23+]
- dispatchDestroyView(): void  [API 23+]
- dispatchLowMemory(): void  [API 23+]
- dispatchMultiWindowModeChanged(boolean): void  [API 24+, deprecated in 26]
- dispatchMultiWindowModeChanged(boolean, Configuration): void  [API 26+]
- dispatchOptionsItemSelected(MenuItem): boolean  [API 23+]
- dispatchOptionsMenuClosed(Menu): void  [API 23+]
- dispatchPause(): void  [API 23+]
- dispatchPictureInPictureModeChanged(boolean): void  [API 24+, deprecated in 26]
- dispatchPictureInPictureModeChanged(boolean, Configuration): void  [API 26+]
- dispatchPrepareOptionsMenu(Menu): boolean  [API 23+]
- dispatchResume(): void  [API 23+]
- dispatchStart(): void  [API 23+]
- dispatchStop(): void  [API 23+]
- dispatchTrimMemory(int): void  [API 23+]
- doLoaderDestroy(): void  [API 23+]
- doLoaderStart(): void  [API 23+]
- doLoaderStop(boolean): void  [API 23+]
- dumpLoaders(String, FileDescriptor, PrintWriter, String[]): void  [API 23+]
- execPendingActions(): boolean  [API 23+]
- findFragmentByWho(String): Fragment  [API 23+]
- getFragmentManager(): FragmentManager  [API 23+]
- getLoaderManager(): LoaderManager  [API 23+]
- noteStateNotSaved(): void  [API 23+]
- onCreateView(View, String, Context, AttributeSet): View  [API 23+]
- reportLoaderStart(): void  [API 23+]
- restoreAllState(Parcelable, FragmentManagerNonConfig): void  [API 24+]
- restoreAllState(Parcelable, List): void  [API 23+, deprecated in 24]
- restoreLoaderNonConfig(ArrayMap): void  [API 23+]
- retainLoaderNonConfig(): ArrayMap  [API 23+]
- retainNestedNonConfig(): FragmentManagerNonConfig  [API 24+]
- retainNonConfig(): List  [API 23+, deprecated in 24]
- saveAllState(): Parcelable  [API 23+]
```

## FragmentHostCallback — declaration; API 23+, deprecated in 28 (7 +, 0 *, 0 ~, 8 -)

```
- FragmentHostCallback(Context, Handler, int)  [API 23+]
+ onAttachFragment(Fragment): void  [API 23+]
+ onDump(String, FileDescriptor, PrintWriter, String[]): void  [API 23+]
- onFindViewById(int): View  [API 23+]
+ onGetHost(): Object  [API 23+]
- onGetLayoutInflater(): LayoutInflater  [API 23+]
- onGetWindowAnimations(): int  [API 23+]
- onHasView(): boolean  [API 23+]
- onHasWindowAnimations(): boolean  [API 23+]
+ onInvalidateOptionsMenu(): void  [API 23+]
+ onRequestPermissionsFromFragment(Fragment, String[], int): void  [API 23+]
- onShouldSaveFragmentState(Fragment): boolean  [API 23+]
+ onStartActivityFromFragment(Fragment, Intent, int, Bundle): void  [API 23+]
+ onStartIntentSenderFromFragment(Fragment, IntentSender, int, Intent, int, int, int, Bundle): void  [API 24+]
- onUseFragmentManagerInflaterFactory(): boolean  [API 23+]
```

## FragmentManager — framework; API 11+, deprecated in 28 (23 +, 0 *, 3 ~, 1 -)

```
+ FragmentManager()  [API 11+]
+ addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener): void  [API 11+]
+ beginTransaction(): FragmentTransaction  [API 11+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 11+]
~ enableDebugLogging(boolean): void [static]  [API 11+]
+ executePendingTransactions(): boolean  [API 11+]
+ findFragmentById(int): Fragment  [API 11+]
+ findFragmentByTag(String): Fragment  [API 11+]
+ getBackStackEntryAt(int): FragmentManager.BackStackEntry  [API 11+]
+ getBackStackEntryCount(): int  [API 11+]
+ getFragment(Bundle, String): Fragment  [API 11+]
+ getFragments(): List  [API 26+]
+ getPrimaryNavigationFragment(): Fragment  [API 26+]
~ invalidateOptionsMenu(): void  [API 14+]
+ isDestroyed(): boolean  [API 17+]
~ isStateSaved(): boolean  [API 26+]
+ popBackStack(): void  [API 11+]
+ popBackStack(String, int): void  [API 11+]
+ popBackStack(int, int): void  [API 11+]
+ popBackStackImmediate(): boolean  [API 11+]
+ popBackStackImmediate(String, int): boolean  [API 11+]
+ popBackStackImmediate(int, int): boolean  [API 11+]
+ putFragment(Bundle, String, Fragment): void  [API 11+]
+ registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks, boolean): void  [API 26+]
+ removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener): void  [API 11+]
+ saveFragmentInstanceState(Fragment): Fragment.SavedState  [API 13+]
+ unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks): void  [API 26+]
```

## FragmentManager.BackStackEntry — framework; API 11+, deprecated in 28 (6 +, 0 *, 0 ~, 0 -)

```
+ getBreadCrumbShortTitle(): CharSequence  [API 11+]
+ getBreadCrumbShortTitleRes(): int  [API 11+]
+ getBreadCrumbTitle(): CharSequence  [API 11+]
+ getBreadCrumbTitleRes(): int  [API 11+]
+ getId(): int  [API 11+]
+ getName(): String  [API 14+]
```

## FragmentManager.FragmentLifecycleCallbacks — framework; API 26+, deprecated in 28 (14 +, 0 *, 0 ~, 1 -)

```
+ FragmentLifecycleCallbacks()  [API 26+]
+ onFragmentActivityCreated(FragmentManager, Fragment, Bundle): void  [API 26+]
+ onFragmentAttached(FragmentManager, Fragment, Context): void  [API 26+]
+ onFragmentCreated(FragmentManager, Fragment, Bundle): void  [API 26+]
+ onFragmentDestroyed(FragmentManager, Fragment): void  [API 26+]
+ onFragmentDetached(FragmentManager, Fragment): void  [API 26+]
+ onFragmentPaused(FragmentManager, Fragment): void  [API 26+]
+ onFragmentPreAttached(FragmentManager, Fragment, Context): void  [API 26+]
- onFragmentPreCreated(FragmentManager, Fragment, Bundle): void  [API 26+]
+ onFragmentResumed(FragmentManager, Fragment): void  [API 26+]
+ onFragmentSaveInstanceState(FragmentManager, Fragment, Bundle): void  [API 26+]
+ onFragmentStarted(FragmentManager, Fragment): void  [API 26+]
+ onFragmentStopped(FragmentManager, Fragment): void  [API 26+]
+ onFragmentViewCreated(FragmentManager, Fragment, View, Bundle): void  [API 26+]
+ onFragmentViewDestroyed(FragmentManager, Fragment): void  [API 26+]
```

## FragmentManager.OnBackStackChangedListener — framework; API 11+, deprecated in 28 (1 +, 0 *, 0 ~, 0 -)

```
+ onBackStackChanged(): void  [API 11+]
```

## FragmentManagerNonConfig — declaration; API 24+, deprecated in 28 (0 +, 0 *, 0 ~, 0 -)

## FragmentTransaction — framework; API 11+, deprecated in 28 (31 +, 0 *, 0 ~, 0 -)

```
+ FragmentTransaction()  [API 11+]
+ add(Fragment, String): FragmentTransaction  [API 11+]
+ add(int, Fragment): FragmentTransaction  [API 11+]
+ add(int, Fragment, String): FragmentTransaction  [API 11+]
+ addSharedElement(View, String): FragmentTransaction  [API 21+]
+ addToBackStack(String): FragmentTransaction  [API 11+]
+ attach(Fragment): FragmentTransaction  [API 13+]
+ commit(): int  [API 11+]
+ commitAllowingStateLoss(): int  [API 11+]
+ commitNow(): void  [API 24+]
+ commitNowAllowingStateLoss(): void  [API 24+]
+ detach(Fragment): FragmentTransaction  [API 13+]
+ disallowAddToBackStack(): FragmentTransaction  [API 11+]
+ hide(Fragment): FragmentTransaction  [API 11+]
+ isAddToBackStackAllowed(): boolean  [API 11+]
+ isEmpty(): boolean  [API 11+]
+ remove(Fragment): FragmentTransaction  [API 11+]
+ replace(int, Fragment): FragmentTransaction  [API 11+]
+ replace(int, Fragment, String): FragmentTransaction  [API 11+]
+ runOnCommit(Runnable): FragmentTransaction  [API 26+]
+ setBreadCrumbShortTitle(CharSequence): FragmentTransaction  [API 11+]
+ setBreadCrumbShortTitle(int): FragmentTransaction  [API 11+]
+ setBreadCrumbTitle(CharSequence): FragmentTransaction  [API 11+]
+ setBreadCrumbTitle(int): FragmentTransaction  [API 11+]
+ setCustomAnimations(int, int): FragmentTransaction  [API 11+]
+ setCustomAnimations(int, int, int, int): FragmentTransaction  [API 13+]
+ setPrimaryNavigationFragment(Fragment): FragmentTransaction  [API 26+]
+ setReorderingAllowed(boolean): FragmentTransaction  [API 26+]
+ setTransition(int): FragmentTransaction  [API 11+]
+ setTransitionStyle(int): FragmentTransaction  [API 11+]
+ show(Fragment): FragmentTransaction  [API 11+]
```

## GameManager — framework; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- getGameMode(): int  [API 31+]
- setGameState(GameState): void  [API 33+]
```

## GameState — declaration; API 33+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 33+]
- GameState(boolean, int)  [API 33+]
- GameState(boolean, int, int, int)  [API 33+]
- describeContents(): int  [API 33+]
- getLabel(): int  [API 33+]
- getMode(): int  [API 33+]
- getQuality(): int  [API 33+]
- isLoading(): boolean  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## GrammaticalInflectionManager — framework; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- getApplicationGrammaticalGender(): int  [API 34+]
- getSystemGrammaticalGender(): int  [API 35+]
- setRequestedApplicationGrammaticalGender(int): void  [API 34+]
```

## Instrumentation — framework; API 1+ (30 +, 0 *, 9 ~, 32 -)

```
+ Instrumentation()  [API 1+]
- acquireLooperManager(Looper): TestLooperManager  [API 26+]
- addMonitor(Instrumentation.ActivityMonitor): void  [API 1+]
- addMonitor(IntentFilter, Instrumentation.ActivityResult, boolean): Instrumentation.ActivityMonitor  [API 1+]
- addMonitor(String, Instrumentation.ActivityResult, boolean): Instrumentation.ActivityMonitor  [API 1+]
- addResults(Bundle): void  [API 26+]
+ callActivityOnCreate(Activity, Bundle): void  [API 1+]
- callActivityOnCreate(Activity, Bundle, PersistableBundle): void  [API 21+]
~ callActivityOnDestroy(Activity): void  [API 1+]
+ callActivityOnNewIntent(Activity, Intent): void  [API 1+]
- callActivityOnNewIntent(Activity, Intent, ComponentCaller): void  [API 35+]
+ callActivityOnPause(Activity): void  [API 1+]
- callActivityOnPictureInPictureRequested(Activity): void  [API 30+]
+ callActivityOnPostCreate(Activity, Bundle): void  [API 1+]
- callActivityOnPostCreate(Activity, Bundle, PersistableBundle): void  [API 21+]
+ callActivityOnRestart(Activity): void  [API 1+]
+ callActivityOnRestoreInstanceState(Activity, Bundle): void  [API 1+]
- callActivityOnRestoreInstanceState(Activity, Bundle, PersistableBundle): void  [API 21+]
+ callActivityOnResume(Activity): void  [API 1+]
+ callActivityOnSaveInstanceState(Activity, Bundle): void  [API 1+]
- callActivityOnSaveInstanceState(Activity, Bundle, PersistableBundle): void  [API 21+]
+ callActivityOnStart(Activity): void  [API 1+]
+ callActivityOnStop(Activity): void  [API 1+]
+ callActivityOnUserLeaving(Activity): void  [API 3+]
+ callApplicationOnCreate(Application): void  [API 1+]
- checkMonitorHit(Instrumentation.ActivityMonitor, int): boolean  [API 1+]
- endPerformanceSnapshot(): void  [API 1+]
~ finish(int, Bundle): void  [API 1+]
- getAllocCounts(): Bundle  [API 1+]
- getBinderCounts(): Bundle  [API 1+]
- getComponentName(): ComponentName  [API 1+]
+ getContext(): Context  [API 1+]
+ getProcessName(): String  [API 26+]
+ getTargetContext(): Context  [API 1+]
- getUiAutomation(): UiAutomation  [API 18+]
- getUiAutomation(int): UiAutomation  [API 24+]
- invokeContextMenuAction(Activity, int, int): boolean  [API 1+]
- invokeMenuActionSync(Activity, int, int): boolean  [API 1+]
~ isProfiling(): boolean  [API 1+]
- newActivity(Class, Context, IBinder, Application, Intent, ActivityInfo, CharSequence, Activity, String, Object): Activity  [API 1+]
+ newActivity(ClassLoader, String, Intent): Activity  [API 1+]
+ newApplication(Class, Context): Application [static]  [API 1+]
+ newApplication(ClassLoader, String, Context): Application  [API 1+]
+ onCreate(Bundle): void  [API 1+]
- onDestroy(): void  [API 1+]
+ onException(Object, Throwable): boolean  [API 1+]
+ onStart(): void  [API 1+]
- removeMonitor(Instrumentation.ActivityMonitor): void  [API 1+]
- resetInTouchMode(): void  [API 33+]
+ runOnMainSync(Runnable): void  [API 1+]
+ sendCharacterSync(int): void  [API 1+]
+ sendKeyDownUpSync(int): void  [API 1+]
+ sendKeySync(KeyEvent): void  [API 1+]
+ sendPointerSync(MotionEvent): void  [API 1+]
- sendStatus(int, Bundle): void  [API 1+]
+ sendStringSync(String): void  [API 1+]
~ sendTrackballEventSync(MotionEvent): void  [API 1+]
- setAutomaticPerformanceSnapshots(): void  [API 1+]
~ setInTouchMode(boolean): void  [API 1+]
~ start(): void  [API 1+]
+ startActivitySync(Intent): Activity  [API 1+]
- startActivitySync(Intent, Bundle): Activity  [API 28+]
- startAllocCounting(): void  [API 1+, deprecated in 23]
- startPerformanceSnapshot(): void  [API 1+]
~ startProfiling(): void  [API 1+]
- stopAllocCounting(): void  [API 1+, deprecated in 23]
~ stopProfiling(): void  [API 1+]
+ waitForIdle(Runnable): void  [API 1+]
~ waitForIdleSync(): void  [API 1+]
- waitForMonitor(Instrumentation.ActivityMonitor): Activity  [API 1+]
- waitForMonitorWithTimeout(Instrumentation.ActivityMonitor, long): Activity  [API 1+]
```

## Instrumentation.ActivityMonitor — framework; API 1+ (1 +, 0 *, 2 ~, 8 -)

```
- ActivityMonitor()  [API 26+]
- ActivityMonitor(IntentFilter, Instrumentation.ActivityResult, boolean)  [API 1+]
+ ActivityMonitor(String, Instrumentation.ActivityResult, boolean)  [API 1+]
- getFilter(): IntentFilter  [API 1+]
~ getHits(): int  [API 1+]
~ getLastActivity(): Activity  [API 1+]
- getResult(): Instrumentation.ActivityResult  [API 1+]
- isBlocking(): boolean  [API 1+]
- onStartActivity(Intent): Instrumentation.ActivityResult  [API 26+]
- waitForActivity(): Activity  [API 1+]
- waitForActivityWithTimeout(long): Activity  [API 1+]
```

## Instrumentation.ActivityResult — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ ActivityResult(int, Intent)  [API 1+]
+ getResultCode(): int  [API 1+]
+ getResultData(): Intent  [API 1+]
```

## IntentService — framework; API 3+, deprecated in 30 (8 +, 0 *, 0 ~, 0 -)

```
+ IntentService(String)  [API 3+]
+ onBind(Intent): IBinder  [API 3+]
+ onCreate(): void  [API 3+]
+ onDestroy(): void  [API 3+]
+ onHandleIntent(Intent): void  [API 3+]
+ onStart(Intent, int): void  [API 3+]
+ onStartCommand(Intent, int, int): int  [API 3+]
+ setIntentRedelivery(boolean): void  [API 5+]
```

## InvalidForegroundServiceTypeException — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 34+]
- InvalidForegroundServiceTypeException(String)  [API 34+]
- describeContents(): int  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## KeyguardManager — framework; API 1+ (1 +, 0 *, 3 ~, 7 -)

```
- addKeyguardLockedStateListener(Executor, KeyguardManager.KeyguardLockedStateListener): void  [API 33+]
- createConfirmDeviceCredentialIntent(CharSequence, CharSequence): Intent  [API 21+, deprecated in 29]
- exitKeyguardSecurely(KeyguardManager.OnKeyguardExitResult): void  [API 1+, deprecated in 15]
~ inKeyguardRestrictedInputMode(): boolean  [API 1+, deprecated in 28]
- isDeviceLocked(): boolean  [API 22+]
- isDeviceSecure(): boolean  [API 23+]
~ isKeyguardLocked(): boolean  [API 16+]
~ isKeyguardSecure(): boolean  [API 16+]
+ newKeyguardLock(String): KeyguardManager.KeyguardLock  [API 1+, deprecated in 15]
- removeKeyguardLockedStateListener(KeyguardManager.KeyguardLockedStateListener): void  [API 33+]
- requestDismissKeyguard(Activity, KeyguardManager.KeyguardDismissCallback): void  [API 26+]
```

## KeyguardManager.KeyguardDismissCallback — declaration; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ KeyguardDismissCallback()  [API 26+]
+ onDismissCancelled(): void  [API 26+]
+ onDismissError(): void  [API 26+]
+ onDismissSucceeded(): void  [API 26+]
```

## KeyguardManager.KeyguardLock — framework; API 1+, deprecated in 15 (0 +, 0 *, 2 ~, 0 -)

```
~ disableKeyguard(): void  [API 1+]
~ reenableKeyguard(): void  [API 1+]
```

## KeyguardManager.KeyguardLockedStateListener — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ onKeyguardLockedStateChanged(boolean): void  [API 33+]
```

## KeyguardManager.OnKeyguardExitResult — declaration; API 1+, deprecated in 26 (1 +, 0 *, 0 ~, 0 -)

```
+ onKeyguardExitResult(boolean): void  [API 1+]
```

## LauncherActivity — declaration; API 1+, deprecated in 30 (3 +, 0 *, 0 ~, 8 -)

```
- LauncherActivity()  [API 1+]
- getTargetIntent(): Intent  [API 1+]
- intentForPosition(int): Intent  [API 3+]
- itemForPosition(int): LauncherActivity.ListItem  [API 4+]
- makeListItems(): List  [API 3+]
+ onCreate(Bundle): void  [API 1+]
+ onListItemClick(ListView, View, int, long): void  [API 1+]
- onQueryPackageManager(Intent): List  [API 5+]
+ onSetContentView(): void  [API 5+]
- setTitle(CharSequence): void  [API 1+]
- setTitle(int): void  [API 1+]
```

## LauncherActivity.IconResizer — declaration; API 3+, deprecated in 30 (0 +, 0 *, 0 ~, 2 -)

```
- IconResizer(LauncherActivity)  [API 3+]
- createIconThumbnail(Drawable): Drawable  [API 3+]
```

## LauncherActivity.ListItem — declaration; API 3+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ ListItem()  [API 3+]
+ className [field]  [API 3+]
+ extras [field]  [API 3+]
+ icon [field]  [API 3+]
+ label [field]  [API 3+]
+ packageName [field]  [API 3+]
+ resolveInfo [field]  [API 4+]
```

## ListActivity — framework; API 1+, deprecated in 30 (11 +, 0 *, 0 ~, 0 -)

```
+ ListActivity()  [API 1+]
+ getListAdapter(): ListAdapter  [API 1+]
+ getListView(): ListView  [API 1+]
+ getSelectedItemId(): long  [API 1+]
+ getSelectedItemPosition(): int  [API 1+]
+ onContentChanged(): void  [API 1+]
+ onDestroy(): void  [API 1+]
+ onListItemClick(ListView, View, int, long): void  [API 1+]
+ onRestoreInstanceState(Bundle): void  [API 1+]
+ setListAdapter(ListAdapter): void  [API 1+]
+ setSelection(int): void  [API 1+]
```

## ListFragment — framework; API 11+, deprecated in 28 (14 +, 0 *, 0 ~, 0 -)

```
+ ListFragment()  [API 11+]
+ getListAdapter(): ListAdapter  [API 11+]
+ getListView(): ListView  [API 11+]
+ getSelectedItemId(): long  [API 11+]
+ getSelectedItemPosition(): int  [API 11+]
+ onCreateView(LayoutInflater, ViewGroup, Bundle): View  [API 11+]
+ onDestroyView(): void  [API 11+]
+ onListItemClick(ListView, View, int, long): void  [API 11+]
+ onViewCreated(View, Bundle): void  [API 11+]
+ setEmptyText(CharSequence): void  [API 11+]
+ setListAdapter(ListAdapter): void  [API 11+]
+ setListShown(boolean): void  [API 11+]
+ setListShownNoAnimation(boolean): void  [API 11+]
+ setSelection(int): void  [API 11+]
```

## LoaderManager — framework; API 11+, deprecated in 28 (5 +, 0 *, 1 ~, 1 -)

```
+ LoaderManager()  [API 11+]
+ destroyLoader(int): void  [API 11+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 11+]
~ enableDebugLogging(boolean): void [static]  [API 11+]
+ getLoader(int): Loader  [API 11+]
+ initLoader(int, Bundle, LoaderManager.LoaderCallbacks): Loader  [API 11+]
+ restartLoader(int, Bundle, LoaderManager.LoaderCallbacks): Loader  [API 11+]
```

## LoaderManager.LoaderCallbacks — framework; API 11+, deprecated in 28 (3 +, 0 *, 0 ~, 0 -)

```
+ onCreateLoader(int, Bundle): Loader  [API 11+]
+ onLoadFinished(Loader, Object): void  [API 11+]
+ onLoaderReset(Loader): void  [API 11+]
```

## LocalActivityManager — framework; API 1+, deprecated in 13 (11 +, 0 *, 2 ~, 0 -)

```
+ LocalActivityManager(Activity, boolean)  [API 1+]
+ destroyActivity(String, boolean): Window  [API 1+]
~ dispatchCreate(Bundle): void  [API 1+]
+ dispatchDestroy(boolean): void  [API 1+]
+ dispatchPause(boolean): void  [API 1+]
+ dispatchResume(): void  [API 1+]
+ dispatchStop(): void  [API 1+]
+ getActivity(String): Activity  [API 1+]
+ getCurrentActivity(): Activity  [API 1+]
+ getCurrentId(): String  [API 1+]
+ removeAllActivities(): void  [API 1+]
~ saveInstanceState(): Bundle  [API 1+]
+ startActivity(String, Intent): Window  [API 1+]
```

## LocaleConfig — declaration; API 33+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 34+]
- LocaleConfig(Context)  [API 33+]
- LocaleConfig(LocaleList)  [API 34+]
- describeContents(): int  [API 33+]
- fromContextIgnoringOverride(Context): LocaleConfig [static]  [API 34+]
- getDefaultLocale(): Locale  [API 35+]
- getStatus(): int  [API 33+]
- getSupportedLocales(): LocaleList  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## LocaleManager — framework; API 33+ (0 +, 0 *, 0 ~, 6 -)

```
- getApplicationLocales(): LocaleList  [API 33+]
- getApplicationLocales(String): LocaleList  [API 33+]
- getOverrideLocaleConfig(): LocaleConfig  [API 34+]
- getSystemLocales(): LocaleList  [API 33+]
- setApplicationLocales(LocaleList): void  [API 33+]
- setOverrideLocaleConfig(LocaleConfig): void  [API 34+]
```

## MediaRouteActionProvider — declaration; API 16+ (0 +, 0 *, 0 ~, 8 -)

```
- MediaRouteActionProvider(Context)  [API 16+]
- isVisible(): boolean  [API 16+]
- onCreateActionView(): View  [API 16+]
- onCreateActionView(MenuItem): View  [API 16+]
- onPerformDefaultAction(): boolean  [API 16+]
- overridesItemVisibility(): boolean  [API 16+]
- setExtendedSettingsClickListener(View.OnClickListener): void  [API 16+]
- setRouteTypes(int): void  [API 16+]
```

## MediaRouteButton — declaration; API 16+ (0 +, 0 *, 0 ~, 19 -)

```
- MediaRouteButton(Context)  [API 16+]
- MediaRouteButton(Context, AttributeSet)  [API 16+]
- MediaRouteButton(Context, AttributeSet, int)  [API 16+]
- MediaRouteButton(Context, AttributeSet, int, int)  [API 21+]
- drawableStateChanged(): void  [API 16+]
- getRouteTypes(): int  [API 16+]
- jumpDrawablesToCurrentState(): void  [API 16+]
- onAttachedToWindow(): void  [API 16+]
- onCreateDrawableState(int): int[]  [API 16+]
- onDetachedFromWindow(): void  [API 16+]
- onDraw(Canvas): void  [API 16+]
- onMeasure(int, int): void  [API 16+]
- performClick(): boolean  [API 16+]
- setContentDescription(CharSequence): void  [API 16+]
- setExtendedSettingsClickListener(View.OnClickListener): void  [API 16+]
- setRouteTypes(int): void  [API 16+]
- setVisibility(int): void  [API 16+]
- showDialog(): void  [API 16+]
- verifyDrawable(Drawable): boolean  [API 16+]
```

## MissingForegroundServiceTypeException — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 34+]
- MissingForegroundServiceTypeException(String)  [API 34+]
- describeContents(): int  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## NativeActivity — declaration; API 9+ (0 +, 0 *, 0 ~, 18 -)

```
- NativeActivity()  [API 9+]
- onConfigurationChanged(Configuration): void  [API 9+]
- onCreate(Bundle): void  [API 9+]
- onDestroy(): void  [API 9+]
- onGlobalLayout(): void  [API 9+]
- onInputQueueCreated(InputQueue): void  [API 9+]
- onInputQueueDestroyed(InputQueue): void  [API 9+]
- onLowMemory(): void  [API 9+]
- onPause(): void  [API 9+]
- onResume(): void  [API 9+]
- onSaveInstanceState(Bundle): void  [API 9+]
- onStart(): void  [API 9+]
- onStop(): void  [API 9+]
- onWindowFocusChanged(boolean): void  [API 9+]
- surfaceChanged(SurfaceHolder, int, int, int): void  [API 9+]
- surfaceCreated(SurfaceHolder): void  [API 9+]
- surfaceDestroyed(SurfaceHolder): void  [API 9+]
- surfaceRedrawNeeded(SurfaceHolder): void  [API 9+]
```

## Notification — framework; API 1+ (5 +, 0 *, 2 ~, 17 -)

```
- AUDIO_ATTRIBUTES_DEFAULT [field]  [API 21+]
- CREATOR [field]  [API 1+]
+ Notification()  [API 1+]
- Notification(Parcel)  [API 1+]
+ Notification(int, CharSequence, long)  [API 1+, deprecated in 15]
- actions [field]  [API 19+]
- audioAttributes [field]  [API 21+, deprecated in 26]
+ audioStreamType [field]  [API 1+, deprecated in 21]
- bigContentView [field]  [API 16+, deprecated in 24]
- category [field]  [API 21+]
+ clone(): Notification  [API 9+]
- color [field]  [API 21+]
+ contentIntent [field]  [API 1+]
+ contentView [field]  [API 1+, deprecated in 24]
+ defaults [field]  [API 1+, deprecated in 26]
+ deleteIntent [field]  [API 1+]
~ describeContents(): int  [API 1+]
- extras [field]  [API 19+]
- findRemoteInputActionPair(boolean): Pair  [API 30+]
+ flags [field]  [API 1+]
+ fullScreenIntent [field]  [API 9+]
- getAllowSystemGeneratedContextualActions(): boolean  [API 29+]
- getBadgeIconType(): int  [API 26+]
- getBubbleMetadata(): Notification.BubbleMetadata  [API 29+]
- getChannelId(): String  [API 26+]
- getContextualActions(): List  [API 30+]
- getGroup(): String  [API 20+]
- getGroupAlertBehavior(): int  [API 26+]
- getLargeIcon(): Icon  [API 23+]
- getLocusId(): LocusId  [API 29+]
- getSettingsText(): CharSequence  [API 26+]
- getShortcutId(): String  [API 26+]
- getSmallIcon(): Icon  [API 23+]
- getSortKey(): String  [API 20+]
- getTimeoutAfter(): long  [API 26+]
- hasImage(): boolean  [API 31+]
- headsUpContentView [field]  [API 21+, deprecated in 24]
+ icon [field]  [API 1+, deprecated in 23]
+ iconLevel [field]  [API 1+]
- largeIcon [field]  [API 11+, deprecated in 23]
+ ledARGB [field]  [API 1+, deprecated in 26]
+ ledOffMS [field]  [API 1+, deprecated in 26]
+ ledOnMS [field]  [API 1+, deprecated in 26]
+ number [field]  [API 1+]
+ priority [field]  [API 16+, deprecated in 26]
- publicVersion [field]  [API 21+]
+ setLatestEventInfo(Context, CharSequence, CharSequence, PendingIntent): void  [API 1-22, removed in 23, deprecated in 15]
+ sound [field]  [API 1+, deprecated in 26]
+ tickerText [field]  [API 1+]
- tickerView [field]  [API 11+, deprecated in 21]
+ toString(): String  [API 1+]
+ vibrate [field]  [API 1+, deprecated in 26]
- visibility [field]  [API 21+]
+ when [field]  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## Notification.Action — declaration; API 19+ (0 +, 11 *, 0 ~, 1 -)

```
- Action(int, CharSequence, PendingIntent)  [API 19+, deprecated in 23]
+ CREATOR [field]  [API 19+]
+ actionIntent [field]  [API 19+]
* clone(): Notification.Action  [API 19+]
* describeContents(): int  [API 19+]
* getAllowGeneratedReplies(): boolean  [API 24+]
* getDataOnlyRemoteInputs(): RemoteInput[]  [API 26+]
* getExtras(): Bundle  [API 20+]
* getIcon(): Icon  [API 23+]
* getRemoteInputs(): RemoteInput[]  [API 20+]
* getSemanticAction(): int  [API 28+]
+ icon [field]  [API 19+, deprecated in 23]
* isAuthenticationRequired(): boolean  [API 31+]
* isContextual(): boolean  [API 29+]
+ title [field]  [API 19+]
* writeToParcel(Parcel, int): void  [API 19+]
```

## Notification.Action.Builder — declaration; API 20+ (0 +, 6 *, 0 ~, 6 -)

```
- Builder(Icon, CharSequence, PendingIntent)  [API 23+]
* Builder(Notification.Action)  [API 20+]
- Builder(int, CharSequence, PendingIntent)  [API 20+, deprecated in 23]
- addExtras(Bundle): Notification.Action.Builder  [API 20+]
- addRemoteInput(RemoteInput): Notification.Action.Builder  [API 20+]
* build(): Notification.Action  [API 20+]
- extend(Notification.Action.Extender): Notification.Action.Builder  [API 20+]
- getExtras(): Bundle  [API 20+]
* setAllowGeneratedReplies(boolean): Notification.Action.Builder  [API 24+]
* setAuthenticationRequired(boolean): Notification.Action.Builder  [API 31+]
* setContextual(boolean): Notification.Action.Builder  [API 29+]
* setSemanticAction(int): Notification.Action.Builder  [API 28+]
```

## Notification.Action.Extender — declaration; API 20+ (1 +, 0 *, 0 ~, 0 -)

```
+ extend(Notification.Action.Builder): Notification.Action.Builder  [API 20+]
```

## Notification.Action.WearableExtender — declaration; API 20+ (0 +, 14 *, 0 ~, 2 -)

```
* WearableExtender()  [API 20+]
- WearableExtender(Notification.Action)  [API 20+]
* clone(): Notification.Action.WearableExtender  [API 20+]
- extend(Notification.Action.Builder): Notification.Action.Builder  [API 20+]
* getCancelLabel(): CharSequence  [API 22+, deprecated in 28]
* getConfirmLabel(): CharSequence  [API 22+, deprecated in 28]
* getHintDisplayActionInline(): boolean  [API 25+]
* getHintLaunchesActivity(): boolean  [API 24+]
* getInProgressLabel(): CharSequence  [API 22+, deprecated in 28]
* isAvailableOffline(): boolean  [API 20+]
* setAvailableOffline(boolean): Notification.Action.WearableExtender  [API 20+]
* setCancelLabel(CharSequence): Notification.Action.WearableExtender  [API 22+, deprecated in 28]
* setConfirmLabel(CharSequence): Notification.Action.WearableExtender  [API 22+, deprecated in 28]
* setHintDisplayActionInline(boolean): Notification.Action.WearableExtender  [API 25+]
* setHintLaunchesActivity(boolean): Notification.Action.WearableExtender  [API 24+]
* setInProgressLabel(CharSequence): Notification.Action.WearableExtender  [API 22+, deprecated in 28]
```

## Notification.BigPictureStyle — declaration; API 16+ (0 +, 0 *, 0 ~, 10 -)

```
- BigPictureStyle()  [API 16+]
- BigPictureStyle(Notification.Builder)  [API 16+, deprecated in 24]
- bigLargeIcon(Bitmap): Notification.BigPictureStyle  [API 16+]
- bigLargeIcon(Icon): Notification.BigPictureStyle  [API 23+]
- bigPicture(Bitmap): Notification.BigPictureStyle  [API 16+]
- bigPicture(Icon): Notification.BigPictureStyle  [API 31+]
- setBigContentTitle(CharSequence): Notification.BigPictureStyle  [API 16+]
- setContentDescription(CharSequence): Notification.BigPictureStyle  [API 31+]
- setSummaryText(CharSequence): Notification.BigPictureStyle  [API 16+]
- showBigPictureWhenCollapsed(boolean): Notification.BigPictureStyle  [API 31+]
```

## Notification.BigTextStyle — declaration; API 16+ (0 +, 0 *, 0 ~, 5 -)

```
- BigTextStyle()  [API 16+]
- BigTextStyle(Notification.Builder)  [API 16+, deprecated in 24]
- bigText(CharSequence): Notification.BigTextStyle  [API 16+]
- setBigContentTitle(CharSequence): Notification.BigTextStyle  [API 16+]
- setSummaryText(CharSequence): Notification.BigTextStyle  [API 16+]
```

## Notification.BubbleMetadata — declaration; API 29+ (0 +, 7 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
* getAutoExpandBubble(): boolean  [API 29+]
* getDeleteIntent(): PendingIntent  [API 29+]
* getDesiredHeight(): int  [API 29+]
* getDesiredHeightResId(): int  [API 29+]
* getIcon(): Icon  [API 29+]
* getIntent(): PendingIntent  [API 29+]
* getShortcutId(): String  [API 30+]
- isBubbleSuppressable(): boolean  [API 31+]
- isBubbleSuppressed(): boolean  [API 31+]
- isNotificationSuppressed(): boolean  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## Notification.BubbleMetadata.Builder — declaration; API 29+ (0 +, 10 *, 0 ~, 2 -)

```
* Builder()  [API 29+, deprecated in 30]
* Builder(PendingIntent, Icon)  [API 30+]
* Builder(String)  [API 30+]
* build(): Notification.BubbleMetadata  [API 29+]
* setAutoExpandBubble(boolean): Notification.BubbleMetadata.Builder  [API 29+]
* setDeleteIntent(PendingIntent): Notification.BubbleMetadata.Builder  [API 29+]
* setDesiredHeight(int): Notification.BubbleMetadata.Builder  [API 29+]
* setDesiredHeightResId(int): Notification.BubbleMetadata.Builder  [API 29+]
* setIcon(Icon): Notification.BubbleMetadata.Builder  [API 29+]
* setIntent(PendingIntent): Notification.BubbleMetadata.Builder  [API 29+]
- setSuppressNotification(boolean): Notification.BubbleMetadata.Builder  [API 29+]
- setSuppressableBubble(boolean): Notification.BubbleMetadata.Builder  [API 31+]
```

## Notification.Builder — framework; API 11+ (8 +, 0 *, 5 ~, 61 -)

```
+ Builder(Context)  [API 11+, deprecated in 26]
- Builder(Context, String)  [API 26+]
- addAction(Notification.Action): Notification.Builder  [API 20+]
- addAction(int, CharSequence, PendingIntent): Notification.Builder  [API 16+, deprecated in 23]
- addExtras(Bundle): Notification.Builder  [API 20+]
- addPerson(Person): Notification.Builder  [API 28+]
- addPerson(String): Notification.Builder  [API 21+, deprecated in 28]
+ build(): Notification  [API 16+]
- createBigContentView(): RemoteViews  [API 24+, deprecated in 35]
- createContentView(): RemoteViews  [API 24+, deprecated in 35]
- createHeadsUpContentView(): RemoteViews  [API 24+, deprecated in 35]
- extend(Notification.Extender): Notification.Builder  [API 20+]
- getExtras(): Bundle  [API 20+]
+ getNotification(): Notification  [API 11+, deprecated in 16]
- getStyle(): Notification.Style  [API 28+]
- recoverBuilder(Context, Notification): Notification.Builder [static]  [API 24+]
- setActions(Notification.Action[]): Notification.Builder  [API 24+]
- setAllowSystemGeneratedContextualActions(boolean): Notification.Builder  [API 29+]
~ setAutoCancel(boolean): Notification.Builder  [API 11+]
- setBadgeIconType(int): Notification.Builder  [API 26+]
- setBubbleMetadata(Notification.BubbleMetadata): Notification.Builder  [API 29+]
- setCategory(String): Notification.Builder  [API 21+]
- setChannelId(String): Notification.Builder  [API 26+]
- setChronometerCountDown(boolean): Notification.Builder  [API 24+]
- setColor(int): Notification.Builder  [API 21+]
- setColorized(boolean): Notification.Builder  [API 26+]
- setContent(RemoteViews): Notification.Builder  [API 11+, deprecated in 24]
- setContentInfo(CharSequence): Notification.Builder  [API 11+, deprecated in 24]
+ setContentIntent(PendingIntent): Notification.Builder  [API 11+]
~ setContentText(CharSequence): Notification.Builder  [API 11+]
~ setContentTitle(CharSequence): Notification.Builder  [API 11+]
- setCustomBigContentView(RemoteViews): Notification.Builder  [API 24+]
- setCustomContentView(RemoteViews): Notification.Builder  [API 24+]
- setCustomHeadsUpContentView(RemoteViews): Notification.Builder  [API 24+]
+ setDefaults(int): Notification.Builder  [API 11+, deprecated in 26]
- setDeleteIntent(PendingIntent): Notification.Builder  [API 11+]
- setExtras(Bundle): Notification.Builder  [API 19+]
- setFlag(int, boolean): Notification.Builder  [API 30+]
- setForegroundServiceBehavior(int): Notification.Builder  [API 31+]
- setFullScreenIntent(PendingIntent, boolean): Notification.Builder  [API 11+]
- setGroup(String): Notification.Builder  [API 20+]
- setGroupAlertBehavior(int): Notification.Builder  [API 26+]
- setGroupSummary(boolean): Notification.Builder  [API 20+]
- setLargeIcon(Bitmap): Notification.Builder  [API 11+]
- setLargeIcon(Icon): Notification.Builder  [API 23+]
- setLights(int, int, int): Notification.Builder  [API 11+, deprecated in 26]
- setLocalOnly(boolean): Notification.Builder  [API 20+]
- setLocusId(LocusId): Notification.Builder  [API 29+]
- setNumber(int): Notification.Builder  [API 11+]
~ setOngoing(boolean): Notification.Builder  [API 11+]
- setOnlyAlertOnce(boolean): Notification.Builder  [API 11+]
- setPriority(int): Notification.Builder  [API 16+, deprecated in 26]
~ setProgress(int, int, boolean): Notification.Builder  [API 14+]
- setPublicVersion(Notification): Notification.Builder  [API 21+]
- setRemoteInputHistory(CharSequence[]): Notification.Builder  [API 24+]
- setSettingsText(CharSequence): Notification.Builder  [API 26+]
- setShortcutId(String): Notification.Builder  [API 26+]
- setShowWhen(boolean): Notification.Builder  [API 17+]
- setSmallIcon(Icon): Notification.Builder  [API 23+]
+ setSmallIcon(int): Notification.Builder  [API 11+]
- setSmallIcon(int, int): Notification.Builder  [API 11+]
- setSortKey(String): Notification.Builder  [API 20+]
- setSound(Uri): Notification.Builder  [API 11+, deprecated in 26]
- setSound(Uri, AudioAttributes): Notification.Builder  [API 21+, deprecated in 26]
- setSound(Uri, int): Notification.Builder  [API 11+, deprecated in 21]
- setStyle(Notification.Style): Notification.Builder  [API 16+]
- setSubText(CharSequence): Notification.Builder  [API 16+]
+ setTicker(CharSequence): Notification.Builder  [API 11+]
- setTicker(CharSequence, RemoteViews): Notification.Builder  [API 11+, deprecated in 21]
- setTimeoutAfter(long): Notification.Builder  [API 26+]
- setUsesChronometer(boolean): Notification.Builder  [API 16+]
- setVibrate(long[]): Notification.Builder  [API 11+, deprecated in 26]
- setVisibility(int): Notification.Builder  [API 21+]
+ setWhen(long): Notification.Builder  [API 11+]
```

## Notification.CallStyle — declaration; API 31+ (0 +, 0 *, 0 ~, 8 -)

```
- forIncomingCall(Person, PendingIntent, PendingIntent): Notification.CallStyle [static]  [API 31+]
- forOngoingCall(Person, PendingIntent): Notification.CallStyle [static]  [API 31+]
- forScreeningCall(Person, PendingIntent, PendingIntent): Notification.CallStyle [static]  [API 31+]
- setAnswerButtonColorHint(int): Notification.CallStyle  [API 31+]
- setDeclineButtonColorHint(int): Notification.CallStyle  [API 31+]
- setIsVideo(boolean): Notification.CallStyle  [API 31+]
- setVerificationIcon(Icon): Notification.CallStyle  [API 31+]
- setVerificationText(CharSequence): Notification.CallStyle  [API 31+]
```

## Notification.CarExtender — declaration; API 23+ (0 +, 7 *, 0 ~, 2 -)

```
* CarExtender()  [API 23+]
- CarExtender(Notification)  [API 23+]
- extend(Notification.Builder): Notification.Builder  [API 23+]
* getColor(): int  [API 23+]
* getLargeIcon(): Bitmap  [API 23+]
* getUnreadConversation(): Notification.CarExtender.UnreadConversation  [API 23+]
* setColor(int): Notification.CarExtender  [API 23+]
* setLargeIcon(Bitmap): Notification.CarExtender  [API 23+]
* setUnreadConversation(Notification.CarExtender.UnreadConversation): Notification.CarExtender  [API 23+]
```

## Notification.CarExtender.Builder — declaration; API 23+ (0 +, 0 *, 0 ~, 6 -)

```
- Builder(String)  [API 23+]
- addMessage(String): Notification.CarExtender.Builder  [API 23+]
- build(): Notification.CarExtender.UnreadConversation  [API 23+]
- setLatestTimestamp(long): Notification.CarExtender.Builder  [API 23+]
- setReadPendingIntent(PendingIntent): Notification.CarExtender.Builder  [API 23+]
- setReplyAction(PendingIntent, RemoteInput): Notification.CarExtender.Builder  [API 23+]
```

## Notification.CarExtender.UnreadConversation — declaration; API 23+ (0 +, 0 *, 0 ~, 7 -)

```
- getLatestTimestamp(): long  [API 23+]
- getMessages(): String[]  [API 23+]
- getParticipant(): String  [API 23+]
- getParticipants(): String[]  [API 23+]
- getReadPendingIntent(): PendingIntent  [API 23+]
- getRemoteInput(): RemoteInput  [API 23+]
- getReplyPendingIntent(): PendingIntent  [API 23+]
```

## Notification.DecoratedCustomViewStyle — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ DecoratedCustomViewStyle()  [API 24+]
```

## Notification.DecoratedMediaCustomViewStyle — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ DecoratedMediaCustomViewStyle()  [API 24+]
```

## Notification.Extender — declaration; API 20+ (1 +, 0 *, 0 ~, 0 -)

```
+ extend(Notification.Builder): Notification.Builder  [API 20+]
```

## Notification.InboxStyle — declaration; API 16+ (0 +, 0 *, 0 ~, 5 -)

```
- InboxStyle()  [API 16+]
- InboxStyle(Notification.Builder)  [API 16+, deprecated in 24]
- addLine(CharSequence): Notification.InboxStyle  [API 16+]
- setBigContentTitle(CharSequence): Notification.InboxStyle  [API 16+]
- setSummaryText(CharSequence): Notification.InboxStyle  [API 16+]
```

## Notification.MediaStyle — declaration; API 21+ (0 +, 0 *, 0 ~, 5 -)

```
- MediaStyle()  [API 21+]
- MediaStyle(Notification.Builder)  [API 21+, deprecated in 24]
- setMediaSession(MediaSession.Token): Notification.MediaStyle  [API 21+]
- setRemotePlaybackInfo(CharSequence, int, PendingIntent): Notification.MediaStyle  [API 34+]
- setShowActionsInCompactView(int[]): Notification.MediaStyle  [API 21+]
```

## Notification.MessagingStyle — declaration; API 24+ (0 +, 8 *, 0 ~, 6 -)

```
* MessagingStyle(CharSequence)  [API 24+, deprecated in 28]
* MessagingStyle(Person)  [API 28+]
- addHistoricMessage(Notification.MessagingStyle.Message): Notification.MessagingStyle  [API 26+]
- addMessage(CharSequence, long, CharSequence): Notification.MessagingStyle  [API 24+, deprecated in 28]
- addMessage(CharSequence, long, Person): Notification.MessagingStyle  [API 28+]
- addMessage(Notification.MessagingStyle.Message): Notification.MessagingStyle  [API 24+]
* getConversationTitle(): CharSequence  [API 24+]
- getHistoricMessages(): List  [API 26+]
- getMessages(): List  [API 24+]
* getUser(): Person  [API 28+]
* getUserDisplayName(): CharSequence  [API 24+, deprecated in 28]
* isGroupConversation(): boolean  [API 28+]
* setConversationTitle(CharSequence): Notification.MessagingStyle  [API 24+]
* setGroupConversation(boolean): Notification.MessagingStyle  [API 28+]
```

## Notification.MessagingStyle.Message — declaration; API 24+ (0 +, 4 *, 0 ~, 7 -)

```
* Message(CharSequence, long, CharSequence)  [API 24+, deprecated in 28]
- Message(CharSequence, long, Person)  [API 28+]
- getDataMimeType(): String  [API 24+]
- getDataUri(): Uri  [API 24+]
- getExtras(): Bundle  [API 26+]
- getMessagesFromBundleArray(Parcelable[]): List [static]  [API 30+]
* getSender(): CharSequence  [API 24+, deprecated in 28]
- getSenderPerson(): Person  [API 28+]
* getText(): CharSequence  [API 24+]
* getTimestamp(): long  [API 24+]
- setData(String, Uri): Notification.MessagingStyle.Message  [API 24+]
```

## Notification.Style — declaration; API 16+ (0 +, 0 *, 0 ~, 7 -)

```
- Style()  [API 16+, deprecated in 35]
- build(): Notification  [API 16+]
- checkBuilder(): void  [API 16+]
- getStandardView(int): RemoteViews  [API 16+]
- internalSetBigContentTitle(CharSequence): void  [API 16+]
- internalSetSummaryText(CharSequence): void  [API 16+]
+ mBuilder [field]  [API 16+]
- setBuilder(Notification.Builder): void  [API 16+]
```

## Notification.TvExtender — declaration; API 35+ (0 +, 9 *, 0 ~, 3 -)

```
* TvExtender()  [API 35+]
- TvExtender(Notification)  [API 35+]
- extend(Notification.Builder): Notification.Builder  [API 35+]
* getChannelId(): String  [API 35+]
* getContentIntent(): PendingIntent  [API 35+]
* getDeleteIntent(): PendingIntent  [API 35+]
- isAvailableOnTv(): boolean  [API 35+]
* isSuppressShowOverApps(): boolean  [API 35+]
* setChannelId(String): Notification.TvExtender  [API 35+]
* setContentIntent(PendingIntent): Notification.TvExtender  [API 35+]
* setDeleteIntent(PendingIntent): Notification.TvExtender  [API 35+]
* setSuppressShowOverApps(boolean): Notification.TvExtender  [API 35+]
```

## Notification.WearableExtender — declaration; API 20+ (0 +, 38 *, 0 ~, 10 -)

```
* WearableExtender()  [API 20+]
- WearableExtender(Notification)  [API 20+]
- addAction(Notification.Action): Notification.WearableExtender  [API 20+]
- addActions(List): Notification.WearableExtender  [API 20+]
- addPage(Notification): Notification.WearableExtender  [API 20+, deprecated in 29]
- addPages(List): Notification.WearableExtender  [API 20+, deprecated in 29]
- clearActions(): Notification.WearableExtender  [API 20+]
- clearPages(): Notification.WearableExtender  [API 20+, deprecated in 29]
* clone(): Notification.WearableExtender  [API 20+]
- extend(Notification.Builder): Notification.Builder  [API 20+]
- getActions(): List  [API 20+]
* getBackground(): Bitmap  [API 20-34, removed in 35, deprecated in 29]
* getBridgeTag(): String  [API 26+]
* getContentAction(): int  [API 20+]
* getContentIcon(): int  [API 20+, deprecated in 28]
* getContentIconGravity(): int  [API 20+, deprecated in 28]
* getContentIntentAvailableOffline(): boolean  [API 20+]
* getCustomContentHeight(): int  [API 20+, deprecated in 28]
* getCustomSizePreset(): int  [API 20+, deprecated in 28]
* getDismissalId(): String  [API 24+]
* getDisplayIntent(): PendingIntent  [API 20+, deprecated in 29]
* getGravity(): int  [API 20+, deprecated in 28]
* getHintAmbientBigPicture(): boolean  [API 24+, deprecated in 29]
* getHintAvoidBackgroundClipping(): boolean  [API 22+, deprecated in 28]
* getHintContentIntentLaunchesActivity(): boolean  [API 24+]
* getHintHideIcon(): boolean  [API 20+, deprecated in 28]
* getHintScreenTimeout(): int  [API 22+, deprecated in 28]
* getHintShowBackgroundOnly(): boolean  [API 20+, deprecated in 28]
- getPages(): List  [API 20+, deprecated in 29]
* getStartScrollBottom(): boolean  [API 20+]
* setBackground(Bitmap): Notification.WearableExtender  [API 20-34, removed in 35, deprecated in 29]
* setBridgeTag(String): Notification.WearableExtender  [API 26+]
* setContentAction(int): Notification.WearableExtender  [API 20+]
* setContentIcon(int): Notification.WearableExtender  [API 20+, deprecated in 28]
* setContentIconGravity(int): Notification.WearableExtender  [API 20+, deprecated in 28]
* setContentIntentAvailableOffline(boolean): Notification.WearableExtender  [API 20+]
* setCustomContentHeight(int): Notification.WearableExtender  [API 20+, deprecated in 28]
* setCustomSizePreset(int): Notification.WearableExtender  [API 20+, deprecated in 28]
* setDismissalId(String): Notification.WearableExtender  [API 24+]
* setDisplayIntent(PendingIntent): Notification.WearableExtender  [API 20+, deprecated in 29]
* setGravity(int): Notification.WearableExtender  [API 20+, deprecated in 28]
* setHintAmbientBigPicture(boolean): Notification.WearableExtender  [API 24+, deprecated in 29]
* setHintAvoidBackgroundClipping(boolean): Notification.WearableExtender  [API 22+, deprecated in 28]
* setHintContentIntentLaunchesActivity(boolean): Notification.WearableExtender  [API 24+]
* setHintHideIcon(boolean): Notification.WearableExtender  [API 20+, deprecated in 28]
* setHintScreenTimeout(int): Notification.WearableExtender  [API 22+, deprecated in 28]
* setHintShowBackgroundOnly(boolean): Notification.WearableExtender  [API 20+, deprecated in 28]
* setStartScrollBottom(boolean): Notification.WearableExtender  [API 20+]
```

## NotificationChannel — declaration; API 26+ (0 +, 20 *, 0 ~, 26 -)

```
- CREATOR [field]  [API 26+]
* NotificationChannel(String, CharSequence, int)  [API 26+]
- canBubble(): boolean  [API 29+]
- canBypassDnd(): boolean  [API 26+]
- canShowBadge(): boolean  [API 26+]
- describeContents(): int  [API 26+]
- enableLights(boolean): void  [API 26+]
- enableVibration(boolean): void  [API 26+]
- equals(Object): boolean  [API 26+]
- getAudioAttributes(): AudioAttributes  [API 26+]
- getConversationId(): String  [API 30+]
* getDescription(): String  [API 26+]
* getGroup(): String  [API 26+]
* getId(): String  [API 26+]
* getImportance(): int  [API 26+]
* getLightColor(): int  [API 26+]
* getLockscreenVisibility(): int  [API 26+]
* getName(): CharSequence  [API 26+]
- getParentChannelId(): String  [API 30+]
- getSound(): Uri  [API 26+]
* getVibrationEffect(): VibrationEffect  [API 35+]
* getVibrationPattern(): long[]  [API 26+]
- hasUserSetImportance(): boolean  [API 29+]
- hasUserSetSound(): boolean  [API 30+]
- hashCode(): int  [API 26+]
* isBlockable(): boolean  [API 33+]
- isConversation(): boolean  [API 31+]
- isDemoted(): boolean  [API 31+]
- isImportantConversation(): boolean  [API 30+]
- setAllowBubbles(boolean): void  [API 29+]
* setBlockable(boolean): void  [API 33+]
- setBypassDnd(boolean): void  [API 26+]
- setConversationId(String, String): void  [API 30+]
* setDescription(String): void  [API 26+]
* setGroup(String): void  [API 26+]
* setImportance(int): void  [API 26+]
* setLightColor(int): void  [API 26+]
* setLockscreenVisibility(int): void  [API 26+]
* setName(CharSequence): void  [API 26+]
- setShowBadge(boolean): void  [API 26+]
- setSound(Uri, AudioAttributes): void  [API 26+]
* setVibrationEffect(VibrationEffect): void  [API 35+]
* setVibrationPattern(long[]): void  [API 26+]
- shouldShowLights(): boolean  [API 26+]
- shouldVibrate(): boolean  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## NotificationChannelGroup — declaration; API 26+ (0 +, 6 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 26+]
* NotificationChannelGroup(String, CharSequence)  [API 26+]
* clone(): NotificationChannelGroup  [API 26+]
- describeContents(): int  [API 26+]
- equals(Object): boolean  [API 26+]
- getChannels(): List  [API 26+]
* getDescription(): String  [API 28+]
* getId(): String  [API 26+]
* getName(): CharSequence  [API 26+]
- hashCode(): int  [API 26+]
- isBlocked(): boolean  [API 28+]
* setDescription(String): void  [API 28+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## NotificationManager — framework; API 1+ (2 +, 0 *, 3 ~, 41 -)

```
- addAutomaticZenRule(AutomaticZenRule): String  [API 24+]
- areAutomaticZenRulesUserManaged(): boolean  [API 35+]
- areBubblesAllowed(): boolean  [API 29+, deprecated in 31]
- areBubblesEnabled(): boolean  [API 31+]
- areNotificationsEnabled(): boolean  [API 24+]
- areNotificationsPaused(): boolean  [API 29+]
- canNotifyAsPackage(String): boolean  [API 29+]
- canUseFullScreenIntent(): boolean  [API 34+]
~ cancel(String, int): void  [API 5+]
~ cancel(int): void  [API 1+]
~ cancelAll(): void  [API 1+]
- cancelAsPackage(String, String, int): void  [API 30+]
- createNotificationChannel(NotificationChannel): void  [API 26+]
- createNotificationChannelGroup(NotificationChannelGroup): void  [API 26+]
- createNotificationChannelGroups(List): void  [API 26+]
- createNotificationChannels(List): void  [API 26+]
- deleteNotificationChannel(String): void  [API 26+]
- deleteNotificationChannelGroup(String): void  [API 26+]
- getActiveNotifications(): StatusBarNotification[]  [API 23+]
- getAutomaticZenRule(String): AutomaticZenRule  [API 24+]
- getAutomaticZenRuleState(String): int  [API 35+]
- getAutomaticZenRules(): Map  [API 24+]
- getBubblePreference(): int  [API 31+]
- getConsolidatedNotificationPolicy(): NotificationManager.Policy  [API 30+]
- getCurrentInterruptionFilter(): int  [API 23+]
- getImportance(): int  [API 24+]
- getNotificationChannel(String): NotificationChannel  [API 26+]
- getNotificationChannel(String, String): NotificationChannel  [API 30+]
- getNotificationChannelGroup(String): NotificationChannelGroup  [API 28+]
- getNotificationChannelGroups(): List  [API 26+]
- getNotificationChannels(): List  [API 26+]
- getNotificationDelegate(): String  [API 29+]
- getNotificationPolicy(): NotificationManager.Policy  [API 23+]
- isNotificationListenerAccessGranted(ComponentName): boolean  [API 27+]
- isNotificationPolicyAccessGranted(): boolean  [API 23+]
- matchesCallFilter(Uri): boolean  [API 33+]
+ notify(String, int, Notification): void  [API 5+]
+ notify(int, Notification): void  [API 1+]
- notifyAsPackage(String, String, int, Notification): void  [API 29+]
- removeAutomaticZenRule(String): boolean  [API 24+]
- setAutomaticZenRuleState(String, Condition): void  [API 29+]
- setInterruptionFilter(int): void  [API 23+]
- setNotificationDelegate(String): void  [API 29+]
- setNotificationPolicy(NotificationManager.Policy): void  [API 23+]
- shouldHideSilentStatusBarIcons(): boolean  [API 29+]
- updateAutomaticZenRule(String, AutomaticZenRule): boolean  [API 24+]
```

## NotificationManager.Policy — declaration; API 23+ (0 +, 0 *, 0 ~, 11 -)

```
- CREATOR [field]  [API 23+]
- Policy(int, int, int)  [API 23+]
- Policy(int, int, int, int)  [API 24+]
- Policy(int, int, int, int, int)  [API 30+]
- describeContents(): int  [API 23+]
- equals(Object): boolean  [API 23+]
- hashCode(): int  [API 23+]
- priorityCallSenders [field]  [API 23+]
- priorityCategories [field]  [API 23+]
- priorityCategoriesToString(int): String [static]  [API 23+]
- priorityConversationSenders [field]  [API 30+]
- priorityMessageSenders [field]  [API 23+]
- prioritySendersToString(int): String [static]  [API 23+]
- suppressedEffectsToString(int): String [static]  [API 24+]
- suppressedVisualEffects [field]  [API 24+]
- toString(): String  [API 23+]
- writeToParcel(Parcel, int): void  [API 23+]
```

## PendingIntent — framework; API 1+ (32 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ cancel(): void  [API 1+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getActivities(Context, int, Intent[], int): PendingIntent [static]  [API 11+]
+ getActivities(Context, int, Intent[], int, Bundle): PendingIntent [static]  [API 16+]
+ getActivity(Context, int, Intent, int): PendingIntent [static]  [API 1+]
+ getActivity(Context, int, Intent, int, Bundle): PendingIntent [static]  [API 16+]
+ getBroadcast(Context, int, Intent, int): PendingIntent [static]  [API 1+]
+ getCreatorPackage(): String  [API 17+]
+ getCreatorUid(): int  [API 17+]
+ getCreatorUserHandle(): UserHandle  [API 17+]
+ getForegroundService(Context, int, Intent, int): PendingIntent [static]  [API 26+]
+ getIntentSender(): IntentSender  [API 4+]
+ getService(Context, int, Intent, int): PendingIntent [static]  [API 1+]
+ getTargetPackage(): String  [API 1+, deprecated in 17]
+ hashCode(): int  [API 1+]
+ isActivity(): boolean  [API 31+]
+ isBroadcast(): boolean  [API 31+]
+ isForegroundService(): boolean  [API 31+]
+ isImmutable(): boolean  [API 31+]
+ isService(): boolean  [API 31+]
+ readPendingIntentOrNullFromParcel(Parcel): PendingIntent [static]  [API 1+]
+ send(): void  [API 1+]
+ send(Bundle): void  [API 34+]
+ send(Context, int, Intent): void  [API 1+]
+ send(Context, int, Intent, PendingIntent.OnFinished, Handler): void  [API 1+]
+ send(Context, int, Intent, PendingIntent.OnFinished, Handler, String): void  [API 14+]
+ send(Context, int, Intent, PendingIntent.OnFinished, Handler, String, Bundle): void  [API 23+]
+ send(int): void  [API 1+]
+ send(int, PendingIntent.OnFinished, Handler): void  [API 1+]
+ toString(): String  [API 1+]
+ writePendingIntentOrNullToParcel(PendingIntent, Parcel): void [static]  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## PendingIntent.CanceledException — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ CanceledException()  [API 1+]
+ CanceledException(Exception)  [API 1+]
+ CanceledException(String)  [API 1+]
```

## PendingIntent.OnFinished — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSendFinished(PendingIntent, Intent, int, String, Bundle): void  [API 1+]
```

## Person — declaration; API 28+ (0 +, 10 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 28+]
* describeContents(): int  [API 28+]
* equals(Object): boolean  [API 28+]
* getIcon(): Icon  [API 28+]
* getKey(): String  [API 28+]
* getName(): CharSequence  [API 28+]
* getUri(): String  [API 28+]
* hashCode(): int  [API 28+]
* isBot(): boolean  [API 28+]
* isImportant(): boolean  [API 28+]
- toBuilder(): Person.Builder  [API 28+]
* writeToParcel(Parcel, int): void  [API 28+]
```

## Person.Builder — declaration; API 28+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder()  [API 28+]
* build(): Person  [API 28+]
* setBot(boolean): Person.Builder  [API 28+]
* setIcon(Icon): Person.Builder  [API 28+]
* setImportant(boolean): Person.Builder  [API 28+]
* setKey(String): Person.Builder  [API 28+]
* setName(CharSequence): Person.Builder  [API 28+]
* setUri(String): Person.Builder  [API 28+]
```

## PictureInPictureParams — declaration; API 26+ (0 +, 14 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 26+]
* describeContents(): int  [API 26+]
* equals(Object): boolean  [API 26+]
* getActions(): List  [API 33+]
* getAspectRatio(): Rational  [API 33+]
* getCloseAction(): RemoteAction  [API 33+]
* getExpandedAspectRatio(): Rational  [API 33+]
* getSourceRectHint(): Rect  [API 33+]
* getSubtitle(): CharSequence  [API 33+]
* getTitle(): CharSequence  [API 33+]
* hashCode(): int  [API 26+]
* isAutoEnterEnabled(): boolean  [API 33+]
* isSeamlessResizeEnabled(): boolean  [API 33+]
* toString(): String  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## PictureInPictureParams.Builder — declaration; API 26+ (0 +, 12 *, 0 ~, 0 -)

```
* Builder()  [API 26+]
* Builder(PictureInPictureParams)  [API 33+]
* build(): PictureInPictureParams  [API 26+]
* setActions(List): PictureInPictureParams.Builder  [API 26+]
* setAspectRatio(Rational): PictureInPictureParams.Builder  [API 26+]
* setAutoEnterEnabled(boolean): PictureInPictureParams.Builder  [API 31+]
* setCloseAction(RemoteAction): PictureInPictureParams.Builder  [API 33+]
* setExpandedAspectRatio(Rational): PictureInPictureParams.Builder  [API 33+]
* setSeamlessResizeEnabled(boolean): PictureInPictureParams.Builder  [API 31+]
* setSourceRectHint(Rect): PictureInPictureParams.Builder  [API 26+]
* setSubtitle(CharSequence): PictureInPictureParams.Builder  [API 33+]
* setTitle(CharSequence): PictureInPictureParams.Builder  [API 33+]
```

## PictureInPictureUiState — declaration; API 31+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- equals(Object): boolean  [API 31+]
- hashCode(): int  [API 31+]
- isStashed(): boolean  [API 31+]
- isTransitioningToPip(): boolean  [API 35+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## Presentation — declaration; API 17+ (0 +, 0 *, 0 ~, 9 -)

```
- Presentation(Context, Display)  [API 17+]
- Presentation(Context, Display, int)  [API 17+]
- getDisplay(): Display  [API 17+]
- getResources(): Resources  [API 17+]
- onDisplayChanged(): void  [API 17+]
- onDisplayRemoved(): void  [API 17+]
- onStart(): void  [API 17+]
- onStop(): void  [API 17+]
- show(): void  [API 17+]
```

## ProgressDialog — framework; API 1+, deprecated in 26 (18 +, 0 *, 3 ~, 4 -)

```
+ ProgressDialog(Context)  [API 1+]
+ ProgressDialog(Context, int)  [API 1+]
+ getMax(): int  [API 1+]
+ getProgress(): int  [API 1+]
- getSecondaryProgress(): int  [API 1+]
+ incrementProgressBy(int): void  [API 1+]
- incrementSecondaryProgressBy(int): void  [API 1+]
+ isIndeterminate(): boolean  [API 1+]
+ onCreate(Bundle): void  [API 1+]
+ onStart(): void  [API 1+]
+ onStop(): void  [API 1+]
+ setIndeterminate(boolean): void  [API 1+]
~ setIndeterminateDrawable(Drawable): void  [API 1+]
+ setMax(int): void  [API 1+]
+ setMessage(CharSequence): void  [API 1+]
+ setProgress(int): void  [API 1+]
~ setProgressDrawable(Drawable): void  [API 1+]
- setProgressNumberFormat(String): void  [API 11+]
- setProgressPercentFormat(NumberFormat): void  [API 11+]
+ setProgressStyle(int): void  [API 1+]
~ setSecondaryProgress(int): void  [API 1+]
+ show(Context, CharSequence, CharSequence): ProgressDialog [static]  [API 1+]
+ show(Context, CharSequence, CharSequence, boolean): ProgressDialog [static]  [API 1+]
+ show(Context, CharSequence, CharSequence, boolean, boolean): ProgressDialog [static]  [API 1+]
+ show(Context, CharSequence, CharSequence, boolean, boolean, DialogInterface.OnCancelListener): ProgressDialog [static]  [API 1+]
```

## RecoverableSecurityException — declaration; API 29+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 29+]
- RecoverableSecurityException(Throwable, CharSequence, RemoteAction)  [API 29+]
- describeContents(): int  [API 29+]
- getUserAction(): RemoteAction  [API 29+]
- getUserMessage(): CharSequence  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## RemoteAction — declaration; API 26+ (0 +, 3 *, 0 ~, 12 -)

```
- CREATOR [field]  [API 26+]
- RemoteAction(Icon, CharSequence, CharSequence, PendingIntent)  [API 26+]
* clone(): RemoteAction  [API 26+]
- describeContents(): int  [API 26+]
- dump(String, PrintWriter): void  [API 26+]
- equals(Object): boolean  [API 26+]
- getActionIntent(): PendingIntent  [API 26+]
- getContentDescription(): CharSequence  [API 26+]
- getIcon(): Icon  [API 26+]
- getTitle(): CharSequence  [API 26+]
- hashCode(): int  [API 26+]
* isEnabled(): boolean  [API 26+]
* setEnabled(boolean): void  [API 26+]
- setShouldShowIcon(boolean): void  [API 28+]
- shouldShowIcon(): boolean  [API 28+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## RemoteInput — declaration; API 20+ (0 +, 5 *, 0 ~, 11 -)

```
- CREATOR [field]  [API 20+]
- addDataResultToIntent(RemoteInput, Intent, Map): void [static]  [API 26+]
- addResultsToIntent(RemoteInput[], Intent, Bundle): void [static]  [API 20+]
- describeContents(): int  [API 20+]
* getAllowFreeFormInput(): boolean  [API 20+]
- getAllowedDataTypes(): Set  [API 26+]
* getChoices(): CharSequence[]  [API 20+]
- getDataResultsFromIntent(Intent, String): Map [static]  [API 26+]
* getEditChoicesBeforeSending(): int  [API 29+]
- getExtras(): Bundle  [API 20+]
* getLabel(): CharSequence  [API 20+]
* getResultKey(): String  [API 20+]
- getResultsFromIntent(Intent): Bundle [static]  [API 20+]
- getResultsSource(Intent): int [static]  [API 28+]
- isDataOnly(): boolean  [API 26+]
- setResultsSource(Intent, int): void [static]  [API 28+]
- writeToParcel(Parcel, int): void  [API 20+]
```

## RemoteInput.Builder — declaration; API 20+ (0 +, 6 *, 0 ~, 3 -)

```
* Builder(String)  [API 20+]
- addExtras(Bundle): RemoteInput.Builder  [API 20+]
* build(): RemoteInput  [API 20+]
- getExtras(): Bundle  [API 20+]
- setAllowDataType(String, boolean): RemoteInput.Builder  [API 26+]
* setAllowFreeFormInput(boolean): RemoteInput.Builder  [API 20+]
* setChoices(CharSequence[]): RemoteInput.Builder  [API 20+]
* setEditChoicesBeforeSending(int): RemoteInput.Builder  [API 29+]
* setLabel(CharSequence): RemoteInput.Builder  [API 20+]
```

## SearchManager — framework; API 1+ (9 +, 0 *, 1 ~, 0 -)

```
~ getGlobalSearchActivity(): ComponentName  [API 16+]
+ getSearchableInfo(ComponentName): SearchableInfo  [API 8+]
+ getSearchablesInGlobalSearch(): List  [API 8+]
+ onCancel(DialogInterface): void  [API 1+]
+ onDismiss(DialogInterface): void  [API 1+]
+ setOnCancelListener(SearchManager.OnCancelListener): void  [API 1+]
+ setOnDismissListener(SearchManager.OnDismissListener): void  [API 1+]
+ startSearch(String, boolean, ComponentName, Bundle, boolean): void  [API 1+]
+ stopSearch(): void  [API 1+]
+ triggerSearch(String, ComponentName, Bundle): void  [API 5+]
```

## SearchManager.OnCancelListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCancel(): void  [API 1+]
```

## SearchManager.OnDismissListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDismiss(): void  [API 1+]
```

## SearchableInfo — framework; API 8+ (25 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 8+]
+ autoUrlDetect(): boolean  [API 8+]
~ describeContents(): int  [API 8+]
+ getHintId(): int  [API 8+]
+ getImeOptions(): int  [API 8+]
+ getInputType(): int  [API 8+]
+ getSearchActivity(): ComponentName  [API 8+]
+ getSettingsDescriptionId(): int  [API 8+]
+ getSuggestAuthority(): String  [API 8+]
+ getSuggestIntentAction(): String  [API 8+]
+ getSuggestIntentData(): String  [API 8+]
+ getSuggestPackage(): String  [API 8+]
+ getSuggestPath(): String  [API 8+]
+ getSuggestSelection(): String  [API 8+]
+ getSuggestThreshold(): int  [API 8+]
+ getVoiceLanguageId(): int  [API 8+]
+ getVoiceLanguageModeId(): int  [API 8+]
+ getVoiceMaxResults(): int  [API 8+]
+ getVoicePromptTextId(): int  [API 8+]
+ getVoiceSearchEnabled(): boolean  [API 8+]
+ getVoiceSearchLaunchRecognizer(): boolean  [API 8+]
+ getVoiceSearchLaunchWebSearch(): boolean  [API 8+]
+ queryAfterZeroResults(): boolean  [API 8+]
+ shouldIncludeInGlobalSearch(): boolean  [API 8+]
+ shouldRewriteQueryFromData(): boolean  [API 8+]
+ shouldRewriteQueryFromText(): boolean  [API 8+]
+ writeToParcel(Parcel, int): void  [API 8+]
```

## Service — framework; API 1+ (17 +, 0 *, 3 ~, 6 -)

```
+ Service()  [API 1+]
+ attachBaseContext(Context): void  [API 1+]
- dump(FileDescriptor, PrintWriter, String[]): void  [API 1+]
+ getApplication(): Application  [API 1+]
- getForegroundServiceType(): int  [API 29+]
+ onBind(Intent): IBinder  [API 1+]
+ onConfigurationChanged(Configuration): void  [API 1+]
+ onCreate(): void  [API 1+]
+ onDestroy(): void  [API 1+]
+ onLowMemory(): void  [API 1+]
+ onRebind(Intent): void  [API 1+]
+ onStart(Intent, int): void  [API 1+, deprecated in 15]
+ onStartCommand(Intent, int, int): int  [API 5+]
+ onTaskRemoved(Intent): void  [API 14+]
- onTimeout(int): void  [API 34+]
- onTimeout(int, int): void  [API 35+]
+ onTrimMemory(int): void  [API 1+]
+ onUnbind(Intent): boolean  [API 1+]
~ setForeground(boolean): void  [API 1-10, removed in 11]
~ startForeground(int, Notification): void  [API 5+]
- startForeground(int, Notification, int): void  [API 29+]
~ stopForeground(boolean): void  [API 5+, deprecated in 33]
- stopForeground(int): void  [API 24+]
+ stopSelf(): void  [API 1+]
+ stopSelf(int): void  [API 1+]
+ stopSelfResult(int): boolean  [API 1+]
```

## ServiceStartNotAllowedException — declaration; API 31+ (0 +, 0 *, 0 ~, 1 -)

```
- getCause(): Throwable  [API 31+]
```

## SharedElementCallback — framework; API 21+ (1 +, 0 *, 0 ~, 7 -)

```
+ SharedElementCallback()  [API 21+]
- onCaptureSharedElementSnapshot(View, Matrix, RectF): Parcelable  [API 21+]
- onCreateSnapshotView(Context, Parcelable): View  [API 21+]
- onMapSharedElements(List, Map): void  [API 21+]
- onRejectSharedElements(List): void  [API 21+]
- onSharedElementEnd(List, List, List): void  [API 21+]
- onSharedElementStart(List, List, List): void  [API 21+]
- onSharedElementsArrived(List, List, SharedElementCallback.OnSharedElementsReadyListener): void  [API 23+]
```

## SharedElementCallback.OnSharedElementsReadyListener — declaration; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSharedElementsReady(): void  [API 23+]
```

## StartForegroundCalledOnStoppedServiceException — declaration; API 34+ (1 +, 0 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 34+]
+ StartForegroundCalledOnStoppedServiceException(String)  [API 34+]
- describeContents(): int  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## StatusBarManager — framework; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
- canLaunchCaptureContentActivityForNote(Activity): boolean  [API 34+]
- requestAddTileService(ComponentName, CharSequence, Icon, Executor, Consumer): void  [API 33+]
```

## SyncNotedAppOp — declaration; API 30+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 30+]
- SyncNotedAppOp(int, String)  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getAttributionTag(): String  [API 30+]
- getOp(): String  [API 30+]
- hashCode(): int  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## TabActivity — framework; API 1+, deprecated in 13 (10 +, 0 *, 0 ~, 0 -)

```
+ TabActivity()  [API 1+]
+ getTabHost(): TabHost  [API 1+]
+ getTabWidget(): TabWidget  [API 1+]
+ onChildTitleChanged(Activity, CharSequence): void  [API 1+]
+ onContentChanged(): void  [API 1+]
+ onPostCreate(Bundle): void  [API 1+]
+ onRestoreInstanceState(Bundle): void  [API 1+]
+ onSaveInstanceState(Bundle): void  [API 1+]
+ setDefaultTab(String): void  [API 1+]
+ setDefaultTab(int): void  [API 1+]
```

## TaskInfo — declaration; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
+ baseActivity [field]  [API 29+]
+ baseIntent [field]  [API 29+]
+ isRunning [field]  [API 29+]
- isVisible(): boolean  [API 32+]
+ numActivities [field]  [API 29+]
+ origActivity [field]  [API 29+]
+ taskDescription [field]  [API 29+]
+ taskId [field]  [API 29+]
- toString(): String  [API 29+]
+ topActivity [field]  [API 29+]
```

## TaskStackBuilder — declaration; API 16+ (0 +, 0 *, 0 ~, 13 -)

```
- addNextIntent(Intent): TaskStackBuilder  [API 16+]
- addNextIntentWithParentStack(Intent): TaskStackBuilder  [API 16+]
- addParentStack(Activity): TaskStackBuilder  [API 16+]
- addParentStack(Class): TaskStackBuilder  [API 16+]
- addParentStack(ComponentName): TaskStackBuilder  [API 16+]
- create(Context): TaskStackBuilder [static]  [API 16+]
- editIntentAt(int): Intent  [API 16+]
- getIntentCount(): int  [API 16+]
- getIntents(): Intent[]  [API 16+]
- getPendingIntent(int, int): PendingIntent  [API 16+]
- getPendingIntent(int, int, Bundle): PendingIntent  [API 16+]
- startActivities(): void  [API 16+]
- startActivities(Bundle): void  [API 16+]
```

## TimePickerDialog — framework; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ TimePickerDialog(Context, TimePickerDialog.OnTimeSetListener, int, int, boolean)  [API 1+]
+ TimePickerDialog(Context, int, TimePickerDialog.OnTimeSetListener, int, int, boolean)  [API 1+]
+ onClick(DialogInterface, int): void  [API 1+]
+ onRestoreInstanceState(Bundle): void  [API 1+]
+ onSaveInstanceState(): Bundle  [API 1+]
+ onTimeChanged(TimePicker, int, int): void  [API 1+]
+ show(): void  [API 1+]
+ updateTime(int, int): void  [API 1+]
```

## TimePickerDialog.OnTimeSetListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTimeSet(TimePicker, int, int): void  [API 1+]
```

## UiAutomation — declaration; API 18+ (0 +, 2 *, 0 ~, 30 -)

```
- adoptShellPermissionIdentity(): void  [API 29+]
- adoptShellPermissionIdentity(String[]): void  [API 29+]
- clearCache(): boolean  [API 34+]
- clearWindowAnimationFrameStats(): void  [API 21+, deprecated in 33]
- clearWindowContentFrameStats(int): boolean  [API 21+]
- dropShellPermissionIdentity(): void  [API 29+]
- executeAndWaitForEvent(Runnable, UiAutomation.AccessibilityEventFilter, long): AccessibilityEvent  [API 18+]
- executeShellCommand(String): ParcelFileDescriptor  [API 21+]
- executeShellCommandRw(String): ParcelFileDescriptor[]  [API 31+]
- executeShellCommandRwe(String): ParcelFileDescriptor[]  [API 34+]
- findFocus(int): AccessibilityNodeInfo  [API 21+]
- getRootInActiveWindow(): AccessibilityNodeInfo  [API 18+]
* getServiceInfo(): AccessibilityServiceInfo  [API 18+]
- getWindowAnimationFrameStats(): WindowAnimationFrameStats  [API 21+, deprecated in 33]
- getWindowContentFrameStats(int): WindowContentFrameStats  [API 21+]
- getWindows(): List  [API 21+]
- getWindowsOnAllDisplays(): SparseArray  [API 30+]
- grantRuntimePermission(String, String): void  [API 28+]
- grantRuntimePermissionAsUser(String, String, UserHandle): void  [API 28+]
- injectInputEvent(InputEvent, boolean): boolean  [API 18+]
- performGlobalAction(int): boolean  [API 18+]
- revokeRuntimePermission(String, String): void  [API 28+]
- revokeRuntimePermissionAsUser(String, String, UserHandle): void  [API 28+]
- setAnimationScale(float): void  [API 33+]
- setOnAccessibilityEventListener(UiAutomation.OnAccessibilityEventListener): void  [API 18+]
- setRotation(int): boolean  [API 18+]
- setRunAsMonkey(boolean): void  [API 18+]
* setServiceInfo(AccessibilityServiceInfo): void  [API 18+]
- takeScreenshot(): Bitmap  [API 18+]
- takeScreenshot(Window): Bitmap  [API 34+]
- toString(): String  [API 18+]
- waitForIdle(long, long): void  [API 18+]
```

## UiAutomation.AccessibilityEventFilter — declaration; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(AccessibilityEvent): boolean  [API 18+]
```

## UiAutomation.OnAccessibilityEventListener — declaration; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ onAccessibilityEvent(AccessibilityEvent): void  [API 18+]
```

## UiModeManager — framework; API 8+ (2 +, 0 *, 3 ~, 8 -)

```
+ ACTION_ENTER_CAR_MODE [field]  [API 8+]
- ACTION_ENTER_DESK_MODE [field]  [API 8+]
+ ACTION_EXIT_CAR_MODE [field]  [API 8+]
- ACTION_EXIT_DESK_MODE [field]  [API 8+]
- addContrastChangeListener(Executor, UiModeManager.ContrastChangeListener): void  [API 34+]
~ disableCarMode(int): void  [API 8+]
~ enableCarMode(int): void  [API 8+]
- getContrast(): float  [API 34+]
~ getCurrentModeType(): int  [API 8+]
- getCustomNightModeEnd(): LocalTime  [API 30+]
- getCustomNightModeStart(): LocalTime  [API 30+]
+ getNightMode(): int  [API 8+]
- removeContrastChangeListener(UiModeManager.ContrastChangeListener): void  [API 34+]
- setApplicationNightMode(int): void  [API 31+]
- setCustomNightModeEnd(LocalTime): void  [API 30+]
- setCustomNightModeStart(LocalTime): void  [API 30+]
+ setNightMode(int): void  [API 8+]
```

## UiModeManager.ContrastChangeListener — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onContrastChanged(float): void  [API 34+]
```

## VoiceInteractor — declaration; API 23+ (0 +, 0 *, 0 ~, 10 -)

```
- getActiveRequest(String): VoiceInteractor.Request  [API 23+]
- getActiveRequests(): VoiceInteractor.Request[]  [API 23+]
- getPackageName(): String  [API 33+]
- isDestroyed(): boolean  [API 29+]
- notifyDirectActionsChanged(): void  [API 29+]
- registerOnDestroyedCallback(Executor, Runnable): boolean  [API 29+]
- submitRequest(VoiceInteractor.Request): boolean  [API 23+]
- submitRequest(VoiceInteractor.Request, String): boolean  [API 23+]
- supportsCommands(String[]): boolean[]  [API 23+]
- unregisterOnDestroyedCallback(Runnable): boolean  [API 29+]
```

## VoiceInteractor.AbortVoiceRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 2 -)

```
- AbortVoiceRequest(VoiceInteractor.Prompt, Bundle)  [API 23+]
- onAbortResult(Bundle): void  [API 23+]
```

## VoiceInteractor.CommandRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 2 -)

```
- CommandRequest(String, Bundle)  [API 23+]
- onCommandResult(boolean, Bundle): void  [API 23+]
```

## VoiceInteractor.CompleteVoiceRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 2 -)

```
- CompleteVoiceRequest(VoiceInteractor.Prompt, Bundle)  [API 23+]
- onCompleteResult(Bundle): void  [API 23+]
```

## VoiceInteractor.ConfirmationRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 2 -)

```
- ConfirmationRequest(VoiceInteractor.Prompt, Bundle)  [API 23+]
- onConfirmationResult(boolean, Bundle): void  [API 23+]
```

## VoiceInteractor.PickOptionRequest — declaration; API 23+ (0 +, 0 *, 0 ~, 2 -)

```
- PickOptionRequest(VoiceInteractor.Prompt, VoiceInteractor.PickOptionRequest.Option[], Bundle)  [API 23+]
- onPickOptionResult(boolean, VoiceInteractor.PickOptionRequest.Option[], Bundle): void  [API 23+]
```

## VoiceInteractor.PickOptionRequest.Option — declaration; API 23+ (0 +, 7 *, 0 ~, 3 -)

```
+ CREATOR [field]  [API 23+]
* Option(CharSequence, int)  [API 23+]
- addSynonym(CharSequence): VoiceInteractor.PickOptionRequest.Option  [API 23+]
- countSynonyms(): int  [API 23+]
* describeContents(): int  [API 23+]
* getExtras(): Bundle  [API 23+]
* getIndex(): int  [API 23+]
* getLabel(): CharSequence  [API 23+]
- getSynonymAt(int): CharSequence  [API 23+]
* setExtras(Bundle): void  [API 23+]
* writeToParcel(Parcel, int): void  [API 23+]
```

## VoiceInteractor.Prompt — declaration; API 23+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 23+]
- Prompt(CharSequence)  [API 23+]
- Prompt(CharSequence[], CharSequence)  [API 23+]
- countVoicePrompts(): int  [API 23+]
- describeContents(): int  [API 23+]
- getVisualPrompt(): CharSequence  [API 23+]
- getVoicePromptAt(int): CharSequence  [API 23+]
- toString(): String  [API 23+]
- writeToParcel(Parcel, int): void  [API 23+]
```

## VoiceInteractor.Request — declaration; API 23+ (3 +, 0 *, 0 ~, 5 -)

```
- cancel(): void  [API 23+]
- getActivity(): Activity  [API 23+]
- getContext(): Context  [API 23+]
- getName(): String  [API 23+]
+ onAttached(Activity): void  [API 23+]
+ onCancel(): void  [API 23+]
+ onDetached(): void  [API 23+]
- toString(): String  [API 23+]
```

## WallpaperColors — declaration; API 27+ (0 +, 11 *, 0 ~, 3 -)

```
+ CREATOR [field]  [API 27+]
* WallpaperColors(Color, Color, Color)  [API 27+]
* WallpaperColors(Color, Color, Color, int)  [API 31+]
- WallpaperColors(Parcel)  [API 27+]
* describeContents(): int  [API 27+]
* equals(Object): boolean  [API 27+]
- fromBitmap(Bitmap): WallpaperColors [static]  [API 27+]
- fromDrawable(Drawable): WallpaperColors [static]  [API 27+]
* getColorHints(): int  [API 31+]
* getPrimaryColor(): Color  [API 27+]
* getSecondaryColor(): Color  [API 27+]
* getTertiaryColor(): Color  [API 27+]
* hashCode(): int  [API 27+]
* toString(): String  [API 27+]
* writeToParcel(Parcel, int): void  [API 27+]
```

## WallpaperInfo — declaration; API 7+ (0 +, 0 *, 0 ~, 21 -)

```
- CREATOR [field]  [API 7+]
- WallpaperInfo(Context, ResolveInfo)  [API 7+]
- describeContents(): int  [API 7+]
- dump(Printer, String): void  [API 7+]
- getComponent(): ComponentName  [API 7+]
- getPackageName(): String  [API 7+]
- getServiceInfo(): ServiceInfo  [API 7+]
- getServiceName(): String  [API 7+]
- getSettingsActivity(): String  [API 7+]
- getSettingsSliceUri(): Uri  [API 29+]
- getShowMetadataInPreview(): boolean  [API 25+]
- loadAuthor(PackageManager): CharSequence  [API 7+]
- loadContextDescription(PackageManager): CharSequence  [API 25+]
- loadContextUri(PackageManager): Uri  [API 25+]
- loadDescription(PackageManager): CharSequence  [API 7+]
- loadIcon(PackageManager): Drawable  [API 7+]
- loadLabel(PackageManager): CharSequence  [API 7+]
- loadThumbnail(PackageManager): Drawable  [API 7+]
- shouldUseDefaultUnfoldTransition(): boolean  [API 32+]
- supportsMultipleDisplays(): boolean  [API 29+]
- toString(): String  [API 7+]
- writeToParcel(Parcel, int): void  [API 7+]
```

## WallpaperManager — framework; API 5+ (0 +, 0 *, 0 ~, 44 -)

```
- addOnColorsChangedListener(WallpaperManager.OnColorsChangedListener, Handler): void  [API 27+]
- clear(): void  [API 5+]
- clear(int): void  [API 24+]
- clearWallpaper(): void  [API 28+]
- clearWallpaperOffsets(IBinder): void  [API 5+]
- forgetLoadedWallpaper(): void  [API 14+]
- getBuiltInDrawable(): Drawable  [API 19+]
- getBuiltInDrawable(int): Drawable  [API 24+]
- getBuiltInDrawable(int, int, boolean, float, float): Drawable  [API 19+]
- getBuiltInDrawable(int, int, boolean, float, float, int): Drawable  [API 24+]
- getCropAndSetWallpaperIntent(Uri): Intent  [API 19+]
- getDesiredMinimumHeight(): int  [API 5+]
- getDesiredMinimumWidth(): int  [API 5+]
- getDrawable(): Drawable  [API 5+]
- getDrawable(int): Drawable  [API 34+]
- getFastDrawable(): Drawable  [API 5+]
- getFastDrawable(int): Drawable  [API 34+]
- getInstance(Context): WallpaperManager [static]  [API 5+]
- getWallpaperColors(int): WallpaperColors  [API 27+]
- getWallpaperFile(int): ParcelFileDescriptor  [API 24+]
- getWallpaperId(int): int  [API 24+]
- getWallpaperInfo(): WallpaperInfo  [API 7+]
- getWallpaperInfo(int): WallpaperInfo  [API 34+]
- hasResourceWallpaper(int): boolean  [API 17+]
- isSetWallpaperAllowed(): boolean  [API 24+]
- isWallpaperSupported(): boolean  [API 23+]
- peekDrawable(): Drawable  [API 5+]
- peekDrawable(int): Drawable  [API 34+]
- peekFastDrawable(): Drawable  [API 5+]
- peekFastDrawable(int): Drawable  [API 34+]
- removeOnColorsChangedListener(WallpaperManager.OnColorsChangedListener): void  [API 27+]
- sendWallpaperCommand(IBinder, String, int, int, int, Bundle): void  [API 7+]
- setBitmap(Bitmap): void  [API 5+]
- setBitmap(Bitmap, Rect, boolean): int  [API 24+]
- setBitmap(Bitmap, Rect, boolean, int): int  [API 24+]
- setDisplayPadding(Rect): void  [API 28+]
- setResource(int): void  [API 5+]
- setResource(int, int): int  [API 24+]
- setStream(InputStream): void  [API 5+]
- setStream(InputStream, Rect, boolean): int  [API 24+]
- setStream(InputStream, Rect, boolean, int): int  [API 24+]
- setWallpaperOffsetSteps(float, float): void  [API 7+]
- setWallpaperOffsets(IBinder, float, float): void  [API 5+]
- suggestDesiredDimensions(int, int): void  [API 5+]
```

## WallpaperManager.OnColorsChangedListener — framework; API 27+ (1 +, 0 *, 0 ~, 0 -)

```
+ onColorsChanged(WallpaperColors, int): void  [API 27+]
```

## ZygotePreload — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ doPreload(ApplicationInfo): void  [API 29+]
```


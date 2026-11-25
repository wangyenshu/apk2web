# android.view

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbsSavedState — framework; API 1+ (5 +, 0 *, 1 ~, 0 -)

```
+ AbsSavedState(Parcel)  [API 1+]
+ AbsSavedState(Parcel, ClassLoader)  [API 24+]
+ AbsSavedState(Parcelable)  [API 1+]
+ CREATOR [field]  [API 1+]
+ EMPTY_STATE [field]  [API 1+]
~ describeContents(): int  [API 1+]
+ getSuperState(): Parcelable  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## ActionMode — framework; API 11+ (20 +, 0 *, 3 ~, 0 -)

```
+ ActionMode()  [API 11+]
+ finish(): void  [API 11+]
+ getCustomView(): View  [API 11+]
+ getMenu(): Menu  [API 11+]
+ getMenuInflater(): MenuInflater  [API 11+]
+ getSubtitle(): CharSequence  [API 11+]
+ getTag(): Object  [API 14+]
+ getTitle(): CharSequence  [API 11+]
+ getTitleOptionalHint(): boolean  [API 16+]
+ getType(): int  [API 23+]
~ hide(long): void  [API 23+]
+ invalidate(): void  [API 11+]
~ invalidateContentRect(): void  [API 23+]
~ isTitleOptional(): boolean  [API 16+]
+ onWindowFocusChanged(boolean): void  [API 23+]
+ setCustomView(View): void  [API 11+]
+ setSubtitle(CharSequence): void  [API 11+]
+ setSubtitle(int): void  [API 11+]
+ setTag(Object): void  [API 14+]
+ setTitle(CharSequence): void  [API 11+]
+ setTitle(int): void  [API 11+]
+ setTitleOptionalHint(boolean): void  [API 16+]
+ setType(int): void  [API 23+]
```

## ActionMode.Callback — framework; API 11+ (4 +, 0 *, 0 ~, 0 -)

```
+ onActionItemClicked(ActionMode, MenuItem): boolean  [API 11+]
+ onCreateActionMode(ActionMode, Menu): boolean  [API 11+]
+ onDestroyActionMode(ActionMode): void  [API 11+]
+ onPrepareActionMode(ActionMode, Menu): boolean  [API 11+]
```

## ActionMode.Callback2 — framework; API 23+ (2 +, 0 *, 0 ~, 0 -)

```
+ Callback2()  [API 23+]
+ onGetContentRect(ActionMode, View, Rect): void  [API 23+]
```

## ActionProvider — framework; API 14+ (5 +, 0 *, 5 ~, 0 -)

```
+ ActionProvider(Context)  [API 14+]
~ hasSubMenu(): boolean  [API 14+]
~ isVisible(): boolean  [API 16+]
+ onCreateActionView(): View  [API 14+, deprecated in 16]
+ onCreateActionView(MenuItem): View  [API 16+]
+ onPerformDefaultAction(): boolean  [API 14+]
+ onPrepareSubMenu(SubMenu): void  [API 14+]
~ overridesItemVisibility(): boolean  [API 16+]
~ refreshVisibility(): void  [API 16+]
~ setVisibilityListener(ActionProvider.VisibilityListener): void  [API 16+]
```

## ActionProvider.VisibilityListener — framework; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onActionProviderVisibilityChanged(boolean): void  [API 16+]
```

## AttachedSurfaceControl — declaration; API 31+ (2 +, 0 *, 0 ~, 6 -)

```
- addOnBufferTransformHintChangedListener(AttachedSurfaceControl.OnBufferTransformHintChangedListener): void  [API 32+]
+ applyTransactionOnDraw(SurfaceControl.Transaction): boolean  [API 31+]
+ buildReparentTransaction(SurfaceControl): SurfaceControl.Transaction  [API 31+]
- getBufferTransformHint(): int  [API 32+]
- getInputTransferToken(): InputTransferToken  [API 35+]
- removeOnBufferTransformHintChangedListener(AttachedSurfaceControl.OnBufferTransformHintChangedListener): void  [API 32+]
- setChildBoundingInsets(Rect): void  [API 34+]
- setTouchableRegion(Region): void  [API 33+]
```

## AttachedSurfaceControl.OnBufferTransformHintChangedListener — declaration; API 32+ (1 +, 0 *, 0 ~, 0 -)

```
+ onBufferTransformHintChanged(int): void  [API 32+]
```

## Choreographer — framework; API 16+ (6 +, 0 *, 0 ~, 0 -)

```
+ getInstance(): Choreographer [static]  [API 16+]
+ postFrameCallback(Choreographer.FrameCallback): void  [API 16+]
+ postFrameCallbackDelayed(Choreographer.FrameCallback, long): void  [API 16+]
+ postVsyncCallback(Choreographer.VsyncCallback): void  [API 33+]
+ removeFrameCallback(Choreographer.FrameCallback): void  [API 16+]
+ removeVsyncCallback(Choreographer.VsyncCallback): void  [API 33+]
```

## Choreographer.FrameCallback — framework; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ doFrame(long): void  [API 16+]
```

## Choreographer.FrameData — framework; API 33+ (1 +, 0 *, 0 ~, 2 -)

```
+ getFrameTimeNanos(): long  [API 33+]
- getFrameTimelines(): Choreographer.FrameTimeline[]  [API 33+]
- getPreferredFrameTimeline(): Choreographer.FrameTimeline  [API 33+]
```

## Choreographer.FrameTimeline — declaration; API 33+ (0 +, 0 *, 0 ~, 3 -)

```
- getDeadlineNanos(): long  [API 33+]
- getExpectedPresentationTimeNanos(): long  [API 33+]
- getVsyncId(): long  [API 33+]
```

## Choreographer.VsyncCallback — framework; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ onVsync(Choreographer.FrameData): void  [API 33+]
```

## CollapsibleActionView — declaration; API 14+ (2 +, 0 *, 0 ~, 0 -)

```
+ onActionViewCollapsed(): void  [API 14+]
+ onActionViewExpanded(): void  [API 14+]
```

## ContentInfo — declaration; API 31+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* getClip(): ClipData  [API 31+]
* getExtras(): Bundle  [API 31+]
* getFlags(): int  [API 31+]
* getLinkUri(): Uri  [API 31+]
* getSource(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## ContentInfo.Builder — declaration; API 31+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder(ClipData, int)  [API 31+]
* Builder(ContentInfo)  [API 31+]
* build(): ContentInfo  [API 31+]
* setClip(ClipData): ContentInfo.Builder  [API 31+]
* setExtras(Bundle): ContentInfo.Builder  [API 31+]
* setFlags(int): ContentInfo.Builder  [API 31+]
* setLinkUri(Uri): ContentInfo.Builder  [API 31+]
* setSource(int): ContentInfo.Builder  [API 31+]
```

## ContextMenu — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ clearHeader(): void  [API 1+]
+ setHeaderIcon(Drawable): ContextMenu  [API 1+]
+ setHeaderIcon(int): ContextMenu  [API 1+]
+ setHeaderTitle(CharSequence): ContextMenu  [API 1+]
+ setHeaderTitle(int): ContextMenu  [API 1+]
+ setHeaderView(View): ContextMenu  [API 1+]
```

## ContextMenu.ContextMenuInfo — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)

## ContextThemeWrapper — framework; API 1+ (10 +, 0 *, 1 ~, 1 -)

```
+ ContextThemeWrapper()  [API 1+]
+ ContextThemeWrapper(Context, Resources.Theme)  [API 23+]
+ ContextThemeWrapper(Context, int)  [API 1+]
~ applyOverrideConfiguration(Configuration): void  [API 17+]
+ attachBaseContext(Context): void  [API 1+]
+ getAssets(): AssetManager  [API 1+]
+ getResources(): Resources  [API 1+]
+ getSystemService(String): Object  [API 1+]
+ getTheme(): Resources.Theme  [API 1+]
+ onApplyThemeResource(Resources.Theme, int, boolean): void  [API 1+]
- setTheme(Resources.Theme): void  [API 29+]
+ setTheme(int): void  [API 1+]
```

## Display — framework; API 1+ (17 +, 0 *, 21 ~, 0 -)

```
~ getAppVsyncOffsetNanos(): long  [API 21+]
+ getCurrentSizeRange(Point, Point): void  [API 16+]
~ getCutout(): DisplayCutout  [API 29+]
~ getDeviceProductInfo(): DeviceProductInfo  [API 31+]
~ getDisplayId(): int  [API 1+]
~ getFlags(): int  [API 17+]
+ getHdrCapabilities(): Display.HdrCapabilities  [API 24+]
~ getHdrSdrRatio(): float  [API 34+]
+ getHeight(): int  [API 1+, deprecated in 15]
~ getHighestHdrSdrRatio(): float  [API 36+]
+ getMetrics(DisplayMetrics): void  [API 1+, deprecated in 30]
+ getMode(): Display.Mode  [API 23+]
~ getName(): String  [API 17+]
+ getOrientation(): int  [API 1+, deprecated in 15]
+ getOverlaySupport(): OverlayProperties  [API 35+]
~ getPixelFormat(): int  [API 1+, deprecated in 17]
~ getPreferredWideGamutColorSpace(): ColorSpace  [API 29+]
~ getPresentationDeadlineNanos(): long  [API 21+]
+ getRealMetrics(DisplayMetrics): void  [API 17+, deprecated in 31]
+ getRealSize(Point): void  [API 17+, deprecated in 31]
+ getRectSize(Rect): void  [API 13+, deprecated in 30]
~ getRefreshRate(): float  [API 1+]
+ getRotation(): int  [API 8+]
~ getRoundedCorner(int): RoundedCorner  [API 31+]
+ getShape(): DisplayShape  [API 34+]
+ getSize(Point): void  [API 13+, deprecated in 30]
~ getState(): int  [API 20+]
+ getSupportedModes(): Display.Mode[]  [API 23+]
+ getSupportedRefreshRates(): float[]  [API 21+, deprecated in 23]
+ getWidth(): int  [API 1+, deprecated in 15]
~ isHdr(): boolean  [API 26+]
~ isHdrSdrRatioAvailable(): boolean  [API 34+]
~ isMinimalPostProcessingSupported(): boolean  [API 30+]
~ isValid(): boolean  [API 17+]
~ isWideColorGamut(): boolean  [API 26+]
~ registerHdrSdrRatioChangedListener(Executor, Consumer): void  [API 34+]
+ toString(): String  [API 1+]
~ unregisterHdrSdrRatioChangedListener(Consumer): void  [API 34+]
```

## Display.HdrCapabilities — framework; API 24+ (8 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 24+]
~ describeContents(): int  [API 24+]
+ equals(Object): boolean  [API 24+]
+ getDesiredMaxAverageLuminance(): float  [API 24+]
+ getDesiredMaxLuminance(): float  [API 24+]
+ getDesiredMinLuminance(): float  [API 24+]
+ getSupportedHdrTypes(): int[]  [API 24+, deprecated in 34]
+ hashCode(): int  [API 24+]
+ toString(): String  [API 24+]
+ writeToParcel(Parcel, int): void  [API 24+]
```

## Display.Mode — framework; API 23+ (10 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 23+]
~ describeContents(): int  [API 23+]
+ equals(Object): boolean  [API 23+]
+ getAlternativeRefreshRates(): float[]  [API 31+]
+ getModeId(): int  [API 23+]
+ getPhysicalHeight(): int  [API 23+]
+ getPhysicalWidth(): int  [API 23+]
+ getRefreshRate(): float  [API 23+]
+ getSupportedHdrTypes(): int[]  [API 34+]
+ hashCode(): int  [API 23+]
+ toString(): String  [API 23+]
+ writeToParcel(Parcel, int): void  [API 23+]
```

## DisplayCutout — framework; API 28+ (13 +, 0 *, 0 ~, 4 -)

```
- DisplayCutout(Insets, Rect, Rect, Rect, Rect)  [API 29+]
- DisplayCutout(Insets, Rect, Rect, Rect, Rect, Insets)  [API 30+]
+ DisplayCutout(Rect, List)  [API 28+, deprecated in 29]
+ equals(Object): boolean  [API 28+]
+ getBoundingRectBottom(): Rect  [API 29+]
+ getBoundingRectLeft(): Rect  [API 29+]
+ getBoundingRectRight(): Rect  [API 29+]
+ getBoundingRectTop(): Rect  [API 29+]
+ getBoundingRects(): List  [API 28+]
- getCutoutPath(): Path  [API 31+]
+ getSafeInsetBottom(): int  [API 28+]
+ getSafeInsetLeft(): int  [API 28+]
+ getSafeInsetRight(): int  [API 28+]
+ getSafeInsetTop(): int  [API 28+]
- getWaterfallInsets(): Insets  [API 30+]
+ hashCode(): int  [API 28+]
+ toString(): String  [API 28+]
```

## DisplayCutout.Builder — declaration; API 33+ (0 +, 0 *, 0 ~, 9 -)

```
- Builder()  [API 33+]
- build(): DisplayCutout  [API 33+]
- setBoundingRectBottom(Rect): DisplayCutout.Builder  [API 33+]
- setBoundingRectLeft(Rect): DisplayCutout.Builder  [API 33+]
- setBoundingRectRight(Rect): DisplayCutout.Builder  [API 33+]
- setBoundingRectTop(Rect): DisplayCutout.Builder  [API 33+]
- setCutoutPath(Path): DisplayCutout.Builder  [API 33+]
- setSafeInsets(Insets): DisplayCutout.Builder  [API 33+]
- setWaterfallInsets(Insets): DisplayCutout.Builder  [API 33+]
```

## DisplayShape — framework; API 34+ (5 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
~ describeContents(): int  [API 34+]
+ equals(Object): boolean  [API 34+]
+ getPath(): Path  [API 34+]
+ hashCode(): int  [API 34+]
+ toString(): String  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## DragAndDropPermissions — declaration; API 24+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 24+]
- describeContents(): int  [API 24+]
- release(): void  [API 24+]
- writeToParcel(Parcel, int): void  [API 24+]
```

## DragEvent — declaration; API 11+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 11+]
- describeContents(): int  [API 11+]
- getAction(): int  [API 11+]
- getClipData(): ClipData  [API 11+]
- getClipDescription(): ClipDescription  [API 11+]
- getLocalState(): Object  [API 11+]
- getResult(): boolean  [API 11+]
- getX(): float  [API 11+]
- getY(): float  [API 11+]
- toString(): String  [API 11+]
- writeToParcel(Parcel, int): void  [API 11+]
```

## FocusFinder — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ findNearestTouchable(ViewGroup, int, int, int, int[]): View  [API 1+]
+ findNextFocus(ViewGroup, View, int): View  [API 1+]
+ findNextFocusFromRect(ViewGroup, Rect, int): View  [API 1+]
+ findNextKeyboardNavigationCluster(View, View, int): View  [API 26+]
+ getInstance(): FocusFinder [static]  [API 1+]
```

## FrameMetrics — declaration; API 24+ (0 +, 0 *, 0 ~, 2 -)

```
- FrameMetrics(FrameMetrics)  [API 24+]
- getMetric(int): long  [API 24+]
```

## FrameStats — declaration; API 21+ (0 +, 0 *, 0 ~, 6 -)

```
- FrameStats()  [API 21+]
- getEndTimeNano(): long  [API 21+]
- getFrameCount(): int  [API 21+]
- getFramePresentedTimeNano(int): long  [API 21+]
- getRefreshPeriodNano(): long  [API 21+]
- getStartTimeNano(): long  [API 21+]
```

## GestureDetector — framework; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ GestureDetector(Context, GestureDetector.OnGestureListener)  [API 3+]
+ GestureDetector(Context, GestureDetector.OnGestureListener, Handler)  [API 3+]
+ GestureDetector(Context, GestureDetector.OnGestureListener, Handler, boolean)  [API 8+]
+ GestureDetector(GestureDetector.OnGestureListener)  [API 1+, deprecated in 15]
+ GestureDetector(GestureDetector.OnGestureListener, Handler)  [API 1+, deprecated in 15]
+ isLongpressEnabled(): boolean  [API 1+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 23+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ setContextClickListener(GestureDetector.OnContextClickListener): void  [API 23+]
+ setIsLongpressEnabled(boolean): void  [API 1+]
+ setOnDoubleTapListener(GestureDetector.OnDoubleTapListener): void  [API 3+]
```

## GestureDetector.OnContextClickListener — framework; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onContextClick(MotionEvent): boolean  [API 23+]
```

## GestureDetector.OnDoubleTapListener — framework; API 3+ (3 +, 0 *, 0 ~, 0 -)

```
+ onDoubleTap(MotionEvent): boolean  [API 3+]
+ onDoubleTapEvent(MotionEvent): boolean  [API 3+]
+ onSingleTapConfirmed(MotionEvent): boolean  [API 3+]
```

## GestureDetector.OnGestureListener — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ onDown(MotionEvent): boolean  [API 1+]
+ onFling(MotionEvent, MotionEvent, float, float): boolean  [API 1+]
+ onLongPress(MotionEvent): void  [API 1+]
+ onScroll(MotionEvent, MotionEvent, float, float): boolean  [API 1+]
+ onShowPress(MotionEvent): void  [API 1+]
+ onSingleTapUp(MotionEvent): boolean  [API 1+]
```

## GestureDetector.SimpleOnGestureListener — framework; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ SimpleOnGestureListener()  [API 1+]
+ onContextClick(MotionEvent): boolean  [API 1+]
+ onDoubleTap(MotionEvent): boolean  [API 1+]
+ onDoubleTapEvent(MotionEvent): boolean  [API 1+]
+ onDown(MotionEvent): boolean  [API 1+]
+ onFling(MotionEvent, MotionEvent, float, float): boolean  [API 1+]
+ onLongPress(MotionEvent): void  [API 1+]
+ onScroll(MotionEvent, MotionEvent, float, float): boolean  [API 1+]
+ onShowPress(MotionEvent): void  [API 1+]
+ onSingleTapConfirmed(MotionEvent): boolean  [API 1+]
+ onSingleTapUp(MotionEvent): boolean  [API 1+]
```

## Gravity — framework; API 1+ (8 +, 0 *, 0 ~, 2 -)

```
+ Gravity()  [API 1+]
+ apply(int, int, int, Rect, Rect): void [static]  [API 1+]
+ apply(int, int, int, Rect, Rect, int): void [static]  [API 17+]
+ apply(int, int, int, Rect, int, int, Rect): void [static]  [API 1+]
+ apply(int, int, int, Rect, int, int, Rect, int): void [static]  [API 17+]
- applyDisplay(int, Rect, Rect): void [static]  [API 3+]
- applyDisplay(int, Rect, Rect, int): void [static]  [API 17+]
+ getAbsoluteGravity(int, int): int [static]  [API 14+]
+ isHorizontal(int): boolean [static]  [API 1+]
+ isVertical(int): boolean [static]  [API 1+]
```

## HapticFeedbackConstants — framework; API 3+ (0 +, 0 *, 0 ~, 0 -)

## InflateException — framework; API 1+ (3 +, 0 *, 0 ~, 1 -)

```
+ InflateException()  [API 1+]
+ InflateException(String)  [API 1+]
+ InflateException(String, Throwable)  [API 1+]
- InflateException(Throwable)  [API 1+]
```

## InputDevice — framework; API 9+ (16 +, 0 *, 8 ~, 5 -)

```
+ CREATOR [field]  [API 9+]
~ describeContents(): int  [API 9+]
- getBatteryState(): BatteryState  [API 31+]
~ getControllerNumber(): int  [API 19+]
+ getDescriptor(): String  [API 16+]
+ getDevice(int): InputDevice [static]  [API 9+]
+ getDeviceIds(): int[] [static]  [API 9+]
+ getId(): int  [API 9+]
+ getKeyCharacterMap(): KeyCharacterMap  [API 9+]
- getKeyCodeForKeyLocation(int): int  [API 33+]
+ getKeyboardType(): int  [API 9+]
- getLightsManager(): LightsManager  [API 31+]
+ getMotionRange(int): InputDevice.MotionRange  [API 9+]
+ getMotionRange(int, int): InputDevice.MotionRange  [API 12+]
+ getMotionRanges(): List  [API 12+]
+ getName(): String  [API 9+]
~ getProductId(): int  [API 19+]
- getSensorManager(): SensorManager  [API 31+]
+ getSources(): int  [API 9+]
~ getVendorId(): int  [API 19+]
~ getVibrator(): Vibrator  [API 16+, deprecated in 31]
- getVibratorManager(): VibratorManager  [API 31+]
+ hasKeys(int[]): boolean[]  [API 19+]
~ hasMicrophone(): boolean  [API 23+]
~ isEnabled(): boolean  [API 27+]
~ isExternal(): boolean  [API 29+]
+ isVirtual(): boolean  [API 16+]
+ supportsSource(int): boolean  [API 21+]
+ toString(): String  [API 9+]
+ writeToParcel(Parcel, int): void  [API 9+]
```

## InputDevice.MotionRange — framework; API 9+ (9 +, 0 *, 0 ~, 0 -)

```
+ getAxis(): int  [API 12+]
+ getFlat(): float  [API 9+]
+ getFuzz(): float  [API 9+]
+ getMax(): float  [API 9+]
+ getMin(): float  [API 9+]
+ getRange(): float  [API 9+]
+ getResolution(): float  [API 18+]
+ getSource(): int  [API 12+]
+ isFromSource(int): boolean  [API 18+]
```

## InputDevice.ViewBehavior — declaration; API 35+ (0 +, 0 *, 0 ~, 1 -)

```
- shouldSmoothScroll(int, int): boolean  [API 35+]
```

## InputEvent — framework; API 9+ (5 +, 0 *, 1 ~, 0 -)

```
- CREATOR [field]  [API 9+]
~ describeContents(): int  [API 9+]
+ getDevice(): InputDevice  [API 9+]
+ getDeviceId(): int  [API 9+]
+ getEventTime(): long  [API 16+]
+ getSource(): int  [API 9+]
+ isFromSource(int): boolean  [API 18+]
```

## InputQueue — declaration; API 9+ (0 +, 0 *, 0 ~, 1 -)

```
- finalize(): void  [API 9+]
```

## InputQueue.Callback — declaration; API 9+ (2 +, 0 *, 0 ~, 0 -)

```
+ onInputQueueCreated(InputQueue): void  [API 9+]
+ onInputQueueDestroyed(InputQueue): void  [API 9+]
```

## KeyCharacterMap — framework; API 1+ (15 +, 0 *, 3 ~, 0 -)

```
+ CREATOR [field]  [API 16+]
~ describeContents(): int  [API 1+]
+ deviceHasKey(int): boolean [static]  [API 3+]
+ deviceHasKeys(int[]): boolean[] [static]  [API 3+]
+ equals(Object): boolean  [API 1+]
+ finalize(): void  [API 1+]
+ get(int, int): int  [API 1+]
+ getDeadChar(int, int): int [static]  [API 1+]
+ getDisplayLabel(int): char  [API 1+]
+ getEvents(char[]): KeyEvent[]  [API 1+]
+ getKeyData(int, KeyCharacterMap.KeyData): boolean  [API 1+, deprecated in 15]
~ getKeyboardType(): int  [API 1+]
+ getMatch(int, char[]): char  [API 1+]
+ getMatch(int, char[], int): char  [API 1+]
~ getModifierBehavior(): int  [API 11+]
+ getNumber(int): char  [API 1+]
+ isPrintingKey(int): boolean  [API 1+]
+ load(int): KeyCharacterMap [static]  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## KeyCharacterMap.KeyData — framework; API 1+, deprecated in 11 (1 +, 0 *, 0 ~, 0 -)

```
+ KeyData()  [API 1+]
+ displayLabel [field]  [API 1+]
+ meta [field]  [API 1+]
+ number [field]  [API 1+]
```

## KeyCharacterMap.UnavailableException — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ UnavailableException(String)  [API 11+]
```

## KeyEvent — framework; API 1+ (65 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ KeyEvent(KeyEvent)  [API 3+]
+ KeyEvent(KeyEvent, long, int)  [API 1+, deprecated in 15]
+ KeyEvent(int, int)  [API 1+]
+ KeyEvent(long, String, int, int)  [API 3+]
+ KeyEvent(long, long, int, int, int)  [API 1+]
+ KeyEvent(long, long, int, int, int, int)  [API 1+]
+ KeyEvent(long, long, int, int, int, int, int, int)  [API 1+]
+ KeyEvent(long, long, int, int, int, int, int, int, int)  [API 1+]
+ KeyEvent(long, long, int, int, int, int, int, int, int, int)  [API 9+]
+ changeAction(KeyEvent, int): KeyEvent [static]  [API 3+]
+ changeFlags(KeyEvent, int): KeyEvent [static]  [API 3+]
+ changeTimeRepeat(KeyEvent, long, int): KeyEvent [static]  [API 3+]
+ changeTimeRepeat(KeyEvent, long, int, int): KeyEvent [static]  [API 5+]
+ dispatch(KeyEvent.Callback): boolean  [API 1+, deprecated in 15]
+ dispatch(KeyEvent.Callback, KeyEvent.DispatcherState, Object): boolean  [API 5+]
+ getAction(): int  [API 1+]
+ getCharacters(): String  [API 3+, deprecated in 29]
+ getDeadChar(int, int): int [static]  [API 1+]
+ getDeviceId(): int  [API 1+]
+ getDisplayLabel(): char  [API 1+]
+ getDownTime(): long  [API 1+]
+ getEventTime(): long  [API 1+]
+ getFlags(): int  [API 1+]
+ getKeyCharacterMap(): KeyCharacterMap  [API 11+]
+ getKeyCode(): int  [API 1+]
+ getKeyData(KeyCharacterMap.KeyData): boolean  [API 1+, deprecated in 15]
+ getMatch(char[]): char  [API 1+]
+ getMatch(char[], int): char  [API 1+]
+ getMaxKeyCode(): int [static]  [API 3+]
+ getMetaState(): int  [API 1+]
~ getModifierMetaStateMask(): int [static]  [API 11+]
+ getModifiers(): int  [API 13+]
+ getNumber(): char  [API 1+]
+ getRepeatCount(): int  [API 1+]
+ getScanCode(): int  [API 1+]
+ getSource(): int  [API 1+]
+ getUnicodeChar(): int  [API 1+]
+ getUnicodeChar(int): int  [API 1+]
+ hasModifiers(int): boolean  [API 11+]
+ hasNoModifiers(): boolean  [API 11+]
+ isAltPressed(): boolean  [API 1+]
+ isCanceled(): boolean  [API 5+]
+ isCapsLockOn(): boolean  [API 11+]
+ isCtrlPressed(): boolean  [API 11+]
+ isFunctionPressed(): boolean  [API 11+]
+ isGamepadButton(int): boolean [static]  [API 12+]
+ isLongPress(): boolean  [API 5+]
+ isMediaSessionKey(int): boolean [static]  [API 31+]
+ isMetaPressed(): boolean  [API 11+]
+ isModifierKey(int): boolean [static]  [API 1+]
+ isNumLockOn(): boolean  [API 11+]
+ isPrintingKey(): boolean  [API 1+]
+ isScrollLockOn(): boolean  [API 11+]
+ isShiftPressed(): boolean  [API 1+]
+ isSymPressed(): boolean  [API 1+]
+ isSystem(): boolean  [API 1+]
+ isTracking(): boolean  [API 5+]
+ keyCodeFromString(String): int [static]  [API 12+]
+ keyCodeToString(int): String [static]  [API 12+]
+ metaStateHasModifiers(int, int): boolean [static]  [API 11+]
+ metaStateHasNoModifiers(int): boolean [static]  [API 11+]
+ normalizeMetaState(int): int [static]  [API 11+]
+ setSource(int): void  [API 12+]
+ startTracking(): void  [API 5+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## KeyEvent.Callback — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyLongPress(int, KeyEvent): boolean  [API 5+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
```

## KeyEvent.DispatcherState — framework; API 5+ (6 +, 0 *, 1 ~, 0 -)

```
+ DispatcherState()  [API 5+]
+ handleUpEvent(KeyEvent): void  [API 5+]
+ isTracking(KeyEvent): boolean  [API 5+]
~ performedLongPress(KeyEvent): void  [API 5+]
+ reset(): void  [API 5+]
+ reset(Object): void  [API 5+]
+ startTracking(KeyEvent, Object): void  [API 5+]
```

## KeyboardShortcutGroup — declaration; API 24+ (0 +, 6 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 24+]
* KeyboardShortcutGroup(CharSequence)  [API 24+]
* KeyboardShortcutGroup(CharSequence, List)  [API 24+]
- addItem(KeyboardShortcutInfo): void  [API 24+]
* describeContents(): int  [API 24+]
* getItems(): List  [API 24+]
* getLabel(): CharSequence  [API 24+]
* writeToParcel(Parcel, int): void  [API 24+]
```

## KeyboardShortcutInfo — declaration; API 24+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 24+]
* KeyboardShortcutInfo(CharSequence, char, int)  [API 24+]
* KeyboardShortcutInfo(CharSequence, int, int)  [API 24+]
* describeContents(): int  [API 24+]
* getBaseCharacter(): char  [API 24+]
* getKeycode(): int  [API 24+]
* getLabel(): CharSequence  [API 24+]
* getModifiers(): int  [API 24+]
* writeToParcel(Parcel, int): void  [API 24+]
```

## LayoutInflater — framework; API 1+ (15 +, 0 *, 0 ~, 5 -)

```
+ LayoutInflater(Context)  [API 1+]
+ LayoutInflater(LayoutInflater, Context)  [API 1+]
+ cloneInContext(Context): LayoutInflater  [API 1+]
- createView(Context, String, String, AttributeSet): View  [API 29+]
+ createView(String, String, AttributeSet): View  [API 1+]
+ from(Context): LayoutInflater [static]  [API 1+]
+ getContext(): Context  [API 1+]
+ getFactory(): LayoutInflater.Factory  [API 1+]
+ getFactory2(): LayoutInflater.Factory2  [API 11+]
+ getFilter(): LayoutInflater.Filter  [API 1+]
- inflate(XmlPullParser, ViewGroup): View  [API 1+]
- inflate(XmlPullParser, ViewGroup, boolean): View  [API 1+]
+ inflate(int, ViewGroup): View  [API 1+]
+ inflate(int, ViewGroup, boolean): View  [API 1+]
- onCreateView(Context, View, String, AttributeSet): View  [API 29+]
+ onCreateView(String, AttributeSet): View  [API 1+]
- onCreateView(View, String, AttributeSet): View  [API 11+]
+ setFactory(LayoutInflater.Factory): void  [API 1+]
+ setFactory2(LayoutInflater.Factory2): void  [API 11+]
+ setFilter(LayoutInflater.Filter): void  [API 1+]
```

## LayoutInflater.Factory — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCreateView(String, Context, AttributeSet): View  [API 1+]
```

## LayoutInflater.Factory2 — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCreateView(View, String, Context, AttributeSet): View  [API 11+]
```

## LayoutInflater.Filter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onLoadClass(Class): boolean  [API 1+]
```

## Menu — framework; API 1+ (24 +, 0 *, 1 ~, 0 -)

```
+ add(CharSequence): MenuItem  [API 1+]
+ add(int): MenuItem  [API 1+]
+ add(int, int, int, CharSequence): MenuItem  [API 1+]
+ add(int, int, int, int): MenuItem  [API 1+]
+ addIntentOptions(int, int, int, ComponentName, Intent[], Intent, int, MenuItem[]): int  [API 1+]
+ addSubMenu(CharSequence): SubMenu  [API 1+]
+ addSubMenu(int): SubMenu  [API 1+]
+ addSubMenu(int, int, int, CharSequence): SubMenu  [API 1+]
+ addSubMenu(int, int, int, int): SubMenu  [API 1+]
+ clear(): void  [API 1+]
+ close(): void  [API 3+]
+ findItem(int): MenuItem  [API 1+]
+ getItem(int): MenuItem  [API 3+]
+ hasVisibleItems(): boolean  [API 1+]
+ isShortcutKey(int, KeyEvent): boolean  [API 1+]
+ performIdentifierAction(int, int): boolean  [API 1+]
+ performShortcut(int, KeyEvent, int): boolean  [API 1+]
+ removeGroup(int): void  [API 1+]
+ removeItem(int): void  [API 1+]
+ setGroupCheckable(int, boolean, boolean): void  [API 1+]
~ setGroupDividerEnabled(boolean): void  [API 28+]
+ setGroupEnabled(int, boolean): void  [API 1+]
+ setGroupVisible(int, boolean): void  [API 1+]
+ setQwertyMode(boolean): void  [API 1+]
+ size(): int  [API 1+]
```

## MenuInflater — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ MenuInflater(Context)  [API 1+]
+ inflate(int, Menu): void  [API 1+]
```

## MenuItem — framework; API 1+ (41 +, 0 *, 6 ~, 9 -)

```
+ collapseActionView(): boolean  [API 14+]
+ expandActionView(): boolean  [API 14+]
+ getActionProvider(): ActionProvider  [API 14+]
+ getActionView(): View  [API 11+]
- getAlphabeticModifiers(): int  [API 26+]
+ getAlphabeticShortcut(): char  [API 1+]
~ getContentDescription(): CharSequence  [API 26+]
+ getGroupId(): int  [API 1+]
+ getIcon(): Drawable  [API 1+]
- getIconTintBlendMode(): BlendMode  [API 29+]
- getIconTintList(): ColorStateList  [API 26+]
- getIconTintMode(): PorterDuff.Mode  [API 26+]
+ getIntent(): Intent  [API 1+]
+ getItemId(): int  [API 1+]
+ getMenuInfo(): ContextMenu.ContextMenuInfo  [API 1+]
- getNumericModifiers(): int  [API 26+]
+ getNumericShortcut(): char  [API 1+]
+ getOrder(): int  [API 1+]
+ getSubMenu(): SubMenu  [API 1+]
+ getTitle(): CharSequence  [API 1+]
+ getTitleCondensed(): CharSequence  [API 1+]
~ getTooltipText(): CharSequence  [API 26+]
+ hasSubMenu(): boolean  [API 1+]
+ isActionViewExpanded(): boolean  [API 14+]
+ isCheckable(): boolean  [API 1+]
+ isChecked(): boolean  [API 1+]
+ isEnabled(): boolean  [API 1+]
+ isVisible(): boolean  [API 1+]
+ setActionProvider(ActionProvider): MenuItem  [API 14+]
+ setActionView(View): MenuItem  [API 11+]
+ setActionView(int): MenuItem  [API 11+]
+ setAlphabeticShortcut(char): MenuItem  [API 1+]
- setAlphabeticShortcut(char, int): MenuItem  [API 26+]
+ setCheckable(boolean): MenuItem  [API 1+]
+ setChecked(boolean): MenuItem  [API 1+]
~ setContentDescription(CharSequence): MenuItem  [API 26+]
+ setEnabled(boolean): MenuItem  [API 1+]
+ setIcon(Drawable): MenuItem  [API 1+]
+ setIcon(int): MenuItem  [API 1+]
- setIconTintBlendMode(BlendMode): MenuItem  [API 29+]
~ setIconTintList(ColorStateList): MenuItem  [API 26+]
~ setIconTintMode(PorterDuff.Mode): MenuItem  [API 26+]
+ setIntent(Intent): MenuItem  [API 1+]
+ setNumericShortcut(char): MenuItem  [API 1+]
- setNumericShortcut(char, int): MenuItem  [API 26+]
+ setOnActionExpandListener(MenuItem.OnActionExpandListener): MenuItem  [API 14+]
+ setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener): MenuItem  [API 1+]
+ setShortcut(char, char): MenuItem  [API 1+]
- setShortcut(char, char, int, int): MenuItem  [API 26+]
+ setShowAsAction(int): void  [API 11+]
+ setShowAsActionFlags(int): MenuItem  [API 14+]
+ setTitle(CharSequence): MenuItem  [API 1+]
+ setTitle(int): MenuItem  [API 1+]
+ setTitleCondensed(CharSequence): MenuItem  [API 1+]
~ setTooltipText(CharSequence): MenuItem  [API 26+]
+ setVisible(boolean): MenuItem  [API 1+]
```

## MenuItem.OnActionExpandListener — framework; API 14+ (2 +, 0 *, 0 ~, 0 -)

```
+ onMenuItemActionCollapse(MenuItem): boolean  [API 14+]
+ onMenuItemActionExpand(MenuItem): boolean  [API 14+]
```

## MenuItem.OnMenuItemClickListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onMenuItemClick(MenuItem): boolean  [API 1+]
```

## MotionEvent — framework; API 1+ (93 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ actionToString(int): String [static]  [API 19+]
+ addBatch(long, MotionEvent.PointerCoords[], int): void  [API 9+]
+ addBatch(long, float, float, float, float, int): void  [API 1+]
+ axisFromString(String): int [static]  [API 12+]
+ axisToString(int): String [static]  [API 12+]
+ finalize(): void  [API 1+]
+ findPointerIndex(int): int  [API 5+]
+ getAction(): int  [API 1+]
+ getActionButton(): int  [API 23+]
+ getActionIndex(): int  [API 8+]
+ getActionMasked(): int  [API 8+]
+ getAxisValue(int): float  [API 12+]
+ getAxisValue(int, int): float  [API 12+]
+ getButtonState(): int  [API 14+]
+ getClassification(): int  [API 29+]
+ getDeviceId(): int  [API 1+]
+ getDownTime(): long  [API 1+]
+ getEdgeFlags(): int  [API 1+]
+ getEventTime(): long  [API 1+]
+ getEventTimeNanos(): long  [API 34+]
+ getFlags(): int  [API 9+]
+ getHistoricalAxisValue(int, int): float  [API 12+]
+ getHistoricalAxisValue(int, int, int): float  [API 12+]
+ getHistoricalEventTime(int): long  [API 1+]
+ getHistoricalEventTimeNanos(int): long  [API 34+]
+ getHistoricalOrientation(int): float  [API 9+]
+ getHistoricalOrientation(int, int): float  [API 9+]
+ getHistoricalPointerCoords(int, int, MotionEvent.PointerCoords): void  [API 9+]
+ getHistoricalPressure(int): float  [API 1+]
+ getHistoricalPressure(int, int): float  [API 5+]
+ getHistoricalSize(int): float  [API 1+]
+ getHistoricalSize(int, int): float  [API 5+]
+ getHistoricalToolMajor(int): float  [API 9+]
+ getHistoricalToolMajor(int, int): float  [API 9+]
+ getHistoricalToolMinor(int): float  [API 9+]
+ getHistoricalToolMinor(int, int): float  [API 9+]
+ getHistoricalTouchMajor(int): float  [API 9+]
+ getHistoricalTouchMajor(int, int): float  [API 9+]
+ getHistoricalTouchMinor(int): float  [API 9+]
+ getHistoricalTouchMinor(int, int): float  [API 9+]
+ getHistoricalX(int): float  [API 1+]
+ getHistoricalX(int, int): float  [API 5+]
+ getHistoricalY(int): float  [API 1+]
+ getHistoricalY(int, int): float  [API 5+]
+ getHistorySize(): int  [API 1+]
+ getMetaState(): int  [API 1+]
+ getOrientation(): float  [API 9+]
+ getOrientation(int): float  [API 9+]
+ getPointerCoords(int, MotionEvent.PointerCoords): void  [API 9+]
+ getPointerCount(): int  [API 5+]
+ getPointerId(int): int  [API 5+]
+ getPointerProperties(int, MotionEvent.PointerProperties): void  [API 14+]
+ getPressure(): float  [API 1+]
+ getPressure(int): float  [API 5+]
+ getRawX(): float  [API 1+]
+ getRawX(int): float  [API 29+]
+ getRawY(): float  [API 1+]
+ getRawY(int): float  [API 29+]
+ getSize(): float  [API 1+]
+ getSize(int): float  [API 5+]
+ getSource(): int  [API 1+]
+ getToolMajor(): float  [API 9+]
+ getToolMajor(int): float  [API 9+]
+ getToolMinor(): float  [API 9+]
+ getToolMinor(int): float  [API 9+]
+ getToolType(int): int  [API 14+]
+ getTouchMajor(): float  [API 9+]
+ getTouchMajor(int): float  [API 9+]
+ getTouchMinor(): float  [API 9+]
+ getTouchMinor(int): float  [API 9+]
+ getX(): float  [API 1+]
+ getX(int): float  [API 5+]
+ getXPrecision(): float  [API 1+]
+ getY(): float  [API 1+]
+ getY(int): float  [API 5+]
+ getYPrecision(): float  [API 1+]
+ isButtonPressed(int): boolean  [API 21+]
+ obtain(MotionEvent): MotionEvent [static]  [API 1+]
+ obtain(long, long, int, float, float, float, float, int, float, float, int, int): MotionEvent [static]  [API 1+]
+ obtain(long, long, int, float, float, int): MotionEvent [static]  [API 1+]
+ obtain(long, long, int, int, MotionEvent.PointerProperties[], MotionEvent.PointerCoords[], int, int, float, float, int, int, int, int): MotionEvent [static]  [API 14+]
+ obtain(long, long, int, int, MotionEvent.PointerProperties[], MotionEvent.PointerCoords[], int, int, float, float, int, int, int, int, int, int): MotionEvent [static]  [API 34+]
+ obtain(long, long, int, int, float, float, float, float, int, float, float, int, int): MotionEvent [static]  [API 5+, deprecated in 15]
+ obtain(long, long, int, int, int[], MotionEvent.PointerCoords[], int, float, float, int, int, int, int): MotionEvent [static]  [API 9+, deprecated in 15]
+ obtainNoHistory(MotionEvent): MotionEvent [static]  [API 5+]
+ offsetLocation(float, float): void  [API 1+]
~ recycle(): void  [API 1+]
+ setAction(int): void  [API 1+]
+ setEdgeFlags(int): void  [API 1+]
+ setLocation(float, float): void  [API 1+]
+ setSource(int): void  [API 12+]
+ toString(): String  [API 1+]
+ transform(Matrix): void  [API 11+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## MotionEvent.PointerCoords — framework; API 9+ (6 +, 0 *, 0 ~, 1 -)

```
+ PointerCoords()  [API 9+]
+ PointerCoords(MotionEvent.PointerCoords)  [API 12+]
+ clear(): void  [API 12+]
+ copyFrom(MotionEvent.PointerCoords): void  [API 12+]
+ getAxisValue(int): float  [API 12+]
- isResampled(): boolean  [API 35+]
+ orientation [field]  [API 9+]
+ pressure [field]  [API 9+]
+ setAxisValue(int, float): void  [API 12+]
+ size [field]  [API 9+]
+ toolMajor [field]  [API 9+]
+ toolMinor [field]  [API 9+]
+ touchMajor [field]  [API 9+]
+ touchMinor [field]  [API 9+]
+ x [field]  [API 9+]
+ y [field]  [API 9+]
```

## MotionEvent.PointerProperties — framework; API 14+ (6 +, 0 *, 0 ~, 0 -)

```
+ PointerProperties()  [API 14+]
+ PointerProperties(MotionEvent.PointerProperties)  [API 14+]
+ clear(): void  [API 14+]
+ copyFrom(MotionEvent.PointerProperties): void  [API 14+]
+ equals(Object): boolean  [API 14+]
+ hashCode(): int  [API 14+]
+ id [field]  [API 14+]
+ toolType [field]  [API 14+]
```

## MotionPredictor — declaration; API 34+ (0 +, 0 *, 0 ~, 4 -)

```
- MotionPredictor(Context)  [API 34+]
- isPredictionAvailable(int, int): boolean  [API 34+]
- predict(long): MotionEvent  [API 34+]
- record(MotionEvent): void  [API 34+]
```

## OnReceiveContentListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onReceiveContent(View, ContentInfo): ContentInfo  [API 31+]
```

## OrientationEventListener — framework; API 3+ (5 +, 0 *, 1 ~, 0 -)

```
+ OrientationEventListener(Context)  [API 3+]
+ OrientationEventListener(Context, int)  [API 3+]
~ canDetectOrientation(): boolean  [API 3+]
+ disable(): void  [API 3+]
+ enable(): void  [API 3+]
+ onOrientationChanged(int): void  [API 3+]
```

## OrientationListener — declaration; API 1+, deprecated in 3 (3 +, 0 *, 0 ~, 4 -)

```
- OrientationListener(Context)  [API 1+]
- OrientationListener(Context, int)  [API 3+]
- disable(): void  [API 1+]
- enable(): void  [API 1+]
+ onAccuracyChanged(int, int): void  [API 1+]
+ onOrientationChanged(int): void  [API 1+]
+ onSensorChanged(int, float[]): void  [API 1+]
```

## PixelCopy — declaration; API 24+ (0 +, 0 *, 0 ~, 7 -)

```
- request(PixelCopy.Request, Executor, Consumer): void [static]  [API 34+]
- request(Surface, Bitmap, PixelCopy.OnPixelCopyFinishedListener, Handler): void [static]  [API 24+]
- request(Surface, Rect, Bitmap, PixelCopy.OnPixelCopyFinishedListener, Handler): void [static]  [API 26+]
- request(SurfaceView, Bitmap, PixelCopy.OnPixelCopyFinishedListener, Handler): void [static]  [API 24+]
- request(SurfaceView, Rect, Bitmap, PixelCopy.OnPixelCopyFinishedListener, Handler): void [static]  [API 26+]
- request(Window, Bitmap, PixelCopy.OnPixelCopyFinishedListener, Handler): void [static]  [API 26+]
- request(Window, Rect, Bitmap, PixelCopy.OnPixelCopyFinishedListener, Handler): void [static]  [API 26+]
```

## PixelCopy.OnPixelCopyFinishedListener — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPixelCopyFinished(int): void  [API 24+]
```

## PixelCopy.Request — declaration; API 34+ (0 +, 2 *, 0 ~, 0 -)

```
* getDestinationBitmap(): Bitmap  [API 34+]
* getSourceRect(): Rect  [API 34+]
```

## PixelCopy.Request.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 4 -)

```
* build(): PixelCopy.Request  [API 34+]
- ofSurface(Surface): PixelCopy.Request.Builder [static]  [API 34+]
- ofSurface(SurfaceView): PixelCopy.Request.Builder [static]  [API 34+]
- ofWindow(View): PixelCopy.Request.Builder [static]  [API 34+]
- ofWindow(Window): PixelCopy.Request.Builder [static]  [API 34+]
* setDestinationBitmap(Bitmap): PixelCopy.Request.Builder  [API 34+]
* setSourceRect(Rect): PixelCopy.Request.Builder  [API 34+]
```

## PixelCopy.Result — declaration; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- getBitmap(): Bitmap  [API 34+]
- getStatus(): int  [API 34+]
```

## PointerIcon — framework; API 24+ (6 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 24+]
+ create(Bitmap, float, float): PointerIcon [static]  [API 24+]
~ describeContents(): int  [API 24+]
+ equals(Object): boolean  [API 24+]
+ getSystemIcon(Context, int): PointerIcon [static]  [API 24+]
+ load(Resources, int): PointerIcon [static]  [API 24+]
+ toString(): String  [API 24+]
+ writeToParcel(Parcel, int): void  [API 24+]
```

## RoundedCorner — framework; API 31+ (8 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
+ RoundedCorner(int, int, int, int)  [API 31+]
~ describeContents(): int  [API 31+]
+ equals(Object): boolean  [API 31+]
+ getCenter(): Point  [API 31+]
+ getPosition(): int  [API 31+]
+ getRadius(): int  [API 31+]
+ hashCode(): int  [API 31+]
+ toString(): String  [API 31+]
+ writeToParcel(Parcel, int): void  [API 31+]
```

## ScaleGestureDetector — framework; API 8+ (19 +, 0 *, 0 ~, 0 -)

```
+ ScaleGestureDetector(Context, ScaleGestureDetector.OnScaleGestureListener)  [API 8+]
+ ScaleGestureDetector(Context, ScaleGestureDetector.OnScaleGestureListener, Handler)  [API 19+]
+ getCurrentSpan(): float  [API 8+]
+ getCurrentSpanX(): float  [API 11+]
+ getCurrentSpanY(): float  [API 11+]
+ getEventTime(): long  [API 8+]
+ getFocusX(): float  [API 8+]
+ getFocusY(): float  [API 8+]
+ getPreviousSpan(): float  [API 8+]
+ getPreviousSpanX(): float  [API 11+]
+ getPreviousSpanY(): float  [API 11+]
+ getScaleFactor(): float  [API 8+]
+ getTimeDelta(): long  [API 8+]
+ isInProgress(): boolean  [API 8+]
+ isQuickScaleEnabled(): boolean  [API 19+]
+ isStylusScaleEnabled(): boolean  [API 23+]
+ onTouchEvent(MotionEvent): boolean  [API 8+]
+ setQuickScaleEnabled(boolean): void  [API 19+]
+ setStylusScaleEnabled(boolean): void  [API 23+]
```

## ScaleGestureDetector.OnScaleGestureListener — framework; API 8+ (3 +, 0 *, 0 ~, 0 -)

```
+ onScale(ScaleGestureDetector): boolean  [API 8+]
+ onScaleBegin(ScaleGestureDetector): boolean  [API 8+]
+ onScaleEnd(ScaleGestureDetector): void  [API 8+]
```

## ScaleGestureDetector.SimpleOnScaleGestureListener — framework; API 8+ (4 +, 0 *, 0 ~, 0 -)

```
+ SimpleOnScaleGestureListener()  [API 8+]
+ onScale(ScaleGestureDetector): boolean  [API 8+]
+ onScaleBegin(ScaleGestureDetector): boolean  [API 8+]
+ onScaleEnd(ScaleGestureDetector): void  [API 8+]
```

## ScrollCaptureCallback — declaration; API 31+ (4 +, 0 *, 0 ~, 0 -)

```
+ onScrollCaptureEnd(Runnable): void  [API 31+]
+ onScrollCaptureImageRequest(ScrollCaptureSession, CancellationSignal, Rect, Consumer): void  [API 31+]
+ onScrollCaptureSearch(CancellationSignal, Consumer): void  [API 31+]
+ onScrollCaptureStart(ScrollCaptureSession, CancellationSignal, Runnable): void  [API 31+]
```

## ScrollCaptureSession — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- ScrollCaptureSession(Surface, Rect, Point)  [API 31+]
- getPositionInWindow(): Point  [API 31+]
- getScrollBounds(): Rect  [API 31+]
- getSurface(): Surface  [API 31+]
```

## ScrollCaptureTarget — declaration; API 31+ (0 +, 2 *, 0 ~, 8 -)

```
- ScrollCaptureTarget(View, Rect, Point, ScrollCaptureCallback)  [API 31+]
- getCallback(): ScrollCaptureCallback  [API 31+]
- getContainingView(): View  [API 31+]
- getHint(): int  [API 31+]
- getLocalVisibleRect(): Rect  [API 31+]
- getPositionInWindow(): Point  [API 31+]
* getScrollBounds(): Rect  [API 31+]
* setScrollBounds(Rect): void  [API 31+]
- toString(): String  [API 31+]
- updatePositionInWindow(): void  [API 31+]
```

## ScrollFeedbackProvider — declaration; API 35+ (3 +, 0 *, 0 ~, 1 -)

```
- createProvider(View): ScrollFeedbackProvider [static]  [API 35+]
+ onScrollLimit(int, int, int, boolean): void  [API 35+]
+ onScrollProgress(int, int, int, int): void  [API 35+]
+ onSnapToItem(int, int, int): void  [API 35+]
```

## SearchEvent — declaration; API 23+ (0 +, 2 *, 0 ~, 0 -)

```
* SearchEvent(InputDevice)  [API 23+]
* getInputDevice(): InputDevice  [API 23+]
```

## SoundEffectConstants — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ getConstantForFocusDirection(int, boolean): int [static]  [API 31+]
+ getContantForFocusDirection(int): int [static]  [API 1+]
```

## SubMenu — framework; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ clearHeader(): void  [API 1+]
+ getItem(): MenuItem  [API 1+]
+ setHeaderIcon(Drawable): SubMenu  [API 1+]
+ setHeaderIcon(int): SubMenu  [API 1+]
+ setHeaderTitle(CharSequence): SubMenu  [API 1+]
+ setHeaderTitle(int): SubMenu  [API 1+]
+ setHeaderView(View): SubMenu  [API 1+]
+ setIcon(Drawable): SubMenu  [API 1+]
+ setIcon(int): SubMenu  [API 1+]
```

## Surface — framework; API 1+ (5 +, 0 *, 1 ~, 23 -)

```
- CREATOR [field]  [API 1+]
- Surface(SurfaceControl)  [API 29+]
- Surface(SurfaceTexture)  [API 14+]
- clearFrameRate(): void  [API 34+]
- describeContents(): int  [API 1+]
+ finalize(): void  [API 1+]
- freeze(): void  [API 1-13, removed in 14]
- hide(): void  [API 1-13, removed in 14]
+ isValid(): boolean  [API 1+]
+ lockCanvas(Rect): Canvas  [API 1+]
- lockHardwareCanvas(): Canvas  [API 23+]
- readFromParcel(Parcel): void  [API 1+]
~ release(): void  [API 14+]
- setAlpha(float): void  [API 1-13, removed in 14]
- setFlags(int, int): void  [API 1-13, removed in 14]
- setFrameRate(float, int): void  [API 30+]
- setFrameRate(float, int, int): void  [API 31+]
- setFreezeTint(int): void  [API 1-13, removed in 14]
- setLayer(int): void  [API 1-13, removed in 14]
- setMatrix(float, float, float, float): void  [API 1-13, removed in 14]
- setOrientation(int, int): void  [API 1-13, removed in 14]
- setPosition(int, int): void  [API 1-13, removed in 14]
- setSize(int, int): void  [API 1-13, removed in 14]
- setTransparentRegionHint(Region): void  [API 1-13, removed in 14]
- show(): void  [API 1-13, removed in 14]
+ toString(): String  [API 1+]
- unfreeze(): void  [API 1-13, removed in 14]
- unlockCanvas(Canvas): void  [API 1+, deprecated in 17]
+ unlockCanvasAndPost(Canvas): void  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## Surface.OutOfResourcesException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ OutOfResourcesException()  [API 1+]
+ OutOfResourcesException(String)  [API 1+]
```

## SurfaceControl — declaration; API 29+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- finalize(): void  [API 29+]
- isValid(): boolean  [API 29+]
- readFromParcel(Parcel): void  [API 29+]
- release(): void  [API 29+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## SurfaceControl.Builder — declaration; API 29+ (0 +, 0 *, 0 ~, 8 -)

```
- Builder()  [API 29+]
- build(): SurfaceControl  [API 29+]
- setBufferSize(int, int): SurfaceControl.Builder  [API 29+]
- setFormat(int): SurfaceControl.Builder  [API 29+]
- setHidden(boolean): SurfaceControl.Builder  [API 33+]
- setName(String): SurfaceControl.Builder  [API 29+]
- setOpaque(boolean): SurfaceControl.Builder  [API 29+]
- setParent(SurfaceControl): SurfaceControl.Builder  [API 29+]
```

## SurfaceControl.Transaction — declaration; API 29+ (0 +, 0 *, 0 ~, 33 -)

```
- CREATOR [field]  [API 30+]
- Transaction()  [API 29+]
- addTransactionCommittedListener(Executor, SurfaceControl.TransactionCommittedListener): SurfaceControl.Transaction  [API 33+]
- addTransactionCompletedListener(Executor, Consumer): SurfaceControl.Transaction  [API 35+]
- apply(): void  [API 29+]
- clearFrameRate(SurfaceControl): SurfaceControl.Transaction  [API 34+]
- clearTrustedPresentationCallback(SurfaceControl): SurfaceControl.Transaction  [API 34+, deprecated in 35]
- close(): void  [API 29+]
- describeContents(): int  [API 29+]
- merge(SurfaceControl.Transaction): SurfaceControl.Transaction  [API 29+]
- reparent(SurfaceControl, SurfaceControl): SurfaceControl.Transaction  [API 29+]
- setAlpha(SurfaceControl, float): SurfaceControl.Transaction  [API 29+]
- setBuffer(SurfaceControl, HardwareBuffer): SurfaceControl.Transaction  [API 33+]
- setBuffer(SurfaceControl, HardwareBuffer, SyncFence): SurfaceControl.Transaction  [API 33+]
- setBuffer(SurfaceControl, HardwareBuffer, SyncFence, Consumer): SurfaceControl.Transaction  [API 33+]
- setBufferSize(SurfaceControl, int, int): SurfaceControl.Transaction  [API 29+]
- setBufferTransform(SurfaceControl, int): SurfaceControl.Transaction  [API 33+]
- setCrop(SurfaceControl, Rect): SurfaceControl.Transaction  [API 33+]
- setDamageRegion(SurfaceControl, Region): SurfaceControl.Transaction  [API 33+]
- setDataSpace(SurfaceControl, int): SurfaceControl.Transaction  [API 33+]
- setDesiredHdrHeadroom(SurfaceControl, float): SurfaceControl.Transaction  [API 35+]
- setDesiredPresentTimeNanos(long): SurfaceControl.Transaction  [API 35+]
- setExtendedRangeBrightness(SurfaceControl, float, float): SurfaceControl.Transaction  [API 34+]
- setFrameRate(SurfaceControl, float, int): SurfaceControl.Transaction  [API 30+]
- setFrameRate(SurfaceControl, float, int, int): SurfaceControl.Transaction  [API 31+]
- setFrameTimeline(long): SurfaceControl.Transaction  [API 35+]
- setGeometry(SurfaceControl, Rect, Rect, int): SurfaceControl.Transaction  [API 29+, deprecated in 33]
- setLayer(SurfaceControl, int): SurfaceControl.Transaction  [API 29+]
- setOpaque(SurfaceControl, boolean): SurfaceControl.Transaction  [API 33+]
- setPosition(SurfaceControl, float, float): SurfaceControl.Transaction  [API 33+]
- setScale(SurfaceControl, float, float): SurfaceControl.Transaction  [API 33+]
- setTrustedPresentationCallback(SurfaceControl, SurfaceControl.TrustedPresentationThresholds, Executor, Consumer): SurfaceControl.Transaction  [API 34+, deprecated in 35]
- setVisibility(SurfaceControl, boolean): SurfaceControl.Transaction  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## SurfaceControl.TransactionCommittedListener — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTransactionCommitted(): void  [API 33+]
```

## SurfaceControl.TransactionStats — declaration; API 35+ (0 +, 0 *, 0 ~, 2 -)

```
- getLatchTimeNanos(): long  [API 35+]
- getPresentFence(): SyncFence  [API 35+]
```

## SurfaceControl.TrustedPresentationThresholds — declaration; API 34+, deprecated in 35 (0 +, 0 *, 0 ~, 1 -)

```
- TrustedPresentationThresholds(float, float, int)  [API 34+]
```

## SurfaceControlInputReceiver — declaration; API 35+ (1 +, 0 *, 0 ~, 0 -)

```
+ onInputEvent(InputEvent): boolean  [API 35+]
```

## SurfaceControlViewHost — declaration; API 30+ (0 +, 0 *, 0 ~, 9 -)

```
- SurfaceControlViewHost(Context, Display, IBinder)  [API 30+]
- SurfaceControlViewHost(Context, Display, InputTransferToken)  [API 35+]
- finalize(): void  [API 30+]
- getSurfacePackage(): SurfaceControlViewHost.SurfacePackage  [API 30+]
- getView(): View  [API 30+]
- relayout(int, int): void  [API 30+]
- release(): void  [API 30+]
- setView(View, int, int): void  [API 30+]
- transferTouchGestureToHost(): boolean  [API 34+, deprecated in 35]
```

## SurfaceControlViewHost.SurfacePackage — declaration; API 30+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 30+]
- SurfacePackage(SurfaceControlViewHost.SurfacePackage)  [API 31+]
- describeContents(): int  [API 30+]
- getInputTransferToken(): InputTransferToken  [API 35+]
- getSurfaceControl(): SurfaceControl  [API 34+]
- notifyConfigurationChanged(Configuration): void  [API 33+]
- notifyDetachedFromWindow(): void  [API 33+]
- release(): void  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## SurfaceHolder — framework; API 1+ (13 +, 0 *, 0 ~, 1 -)

```
+ addCallback(SurfaceHolder.Callback): void  [API 1+]
+ getSurface(): Surface  [API 1+]
+ getSurfaceFrame(): Rect  [API 1+]
+ isCreating(): boolean  [API 1+]
+ lockCanvas(): Canvas  [API 1+]
+ lockCanvas(Rect): Canvas  [API 1+]
- lockHardwareCanvas(): Canvas  [API 26+]
+ removeCallback(SurfaceHolder.Callback): void  [API 1+]
+ setFixedSize(int, int): void  [API 1+]
+ setFormat(int): void  [API 1+]
+ setKeepScreenOn(boolean): void  [API 1+]
+ setSizeFromLayout(): void  [API 1+]
+ setType(int): void  [API 1+, deprecated in 15]
+ unlockCanvasAndPost(Canvas): void  [API 1+]
```

## SurfaceHolder.BadSurfaceTypeException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BadSurfaceTypeException()  [API 1+]
+ BadSurfaceTypeException(String)  [API 1+]
```

## SurfaceHolder.Callback — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ surfaceChanged(SurfaceHolder, int, int, int): void  [API 1+]
+ surfaceCreated(SurfaceHolder): void  [API 1+]
+ surfaceDestroyed(SurfaceHolder): void  [API 1+]
```

## SurfaceHolder.Callback2 — declaration; API 9+ (1 +, 0 *, 0 ~, 1 -)

```
+ surfaceRedrawNeeded(SurfaceHolder): void  [API 9+]
- surfaceRedrawNeededAsync(SurfaceHolder, Runnable): void  [API 26+]
```

## SurfaceView — framework; API 1+ (16 +, 0 *, 4 ~, 8 -)

```
+ SurfaceView(Context)  [API 1+]
+ SurfaceView(Context, AttributeSet)  [API 1+]
+ SurfaceView(Context, AttributeSet, int)  [API 1+]
- SurfaceView(Context, AttributeSet, int, int)  [API 21+]
- applyTransactionToFrame(SurfaceControl.Transaction): void  [API 34+]
~ dispatchDraw(Canvas): void  [API 1+]
+ draw(Canvas): void  [API 1+]
+ gatherTransparentRegion(Region): boolean  [API 1+]
+ getHolder(): SurfaceHolder  [API 1+]
- getHostToken(): IBinder  [API 30+, deprecated in 35]
+ getImportantForAccessibility(): int  [API 1+]
- getSurfaceControl(): SurfaceControl  [API 29+]
~ hasOverlappingRendering(): boolean  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onFocusChanged(boolean, int, Rect): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onSetAlpha(int): boolean  [API 1+]
+ onWindowVisibilityChanged(int): void  [API 1+]
+ setAlpha(float): void  [API 1+]
- setChildSurfacePackage(SurfaceControlViewHost.SurfacePackage): void  [API 30+]
+ setClipBounds(Rect): void  [API 1+]
- setDesiredHdrHeadroom(float): void  [API 35+]
- setSecure(boolean): void  [API 17+]
- setSurfaceLifecycle(int): void  [API 34+]
+ setVisibility(int): void  [API 1+]
~ setZOrderMediaOverlay(boolean): void  [API 5+]
~ setZOrderOnTop(boolean): void  [API 5+]
```

## TextureView — declaration; API 14+ (0 +, 0 *, 0 ~, 30 -)

```
- TextureView(Context)  [API 14+]
- TextureView(Context, AttributeSet)  [API 14+]
- TextureView(Context, AttributeSet, int)  [API 14+]
- TextureView(Context, AttributeSet, int, int)  [API 21+]
- buildLayer(): void  [API 14+]
- draw(Canvas): void  [API 14+]
- getBitmap(): Bitmap  [API 14+]
- getBitmap(Bitmap): Bitmap  [API 14+]
- getBitmap(int, int): Bitmap  [API 14+]
- getLayerType(): int  [API 14+]
- getSurfaceTexture(): SurfaceTexture  [API 14+]
- getSurfaceTextureListener(): TextureView.SurfaceTextureListener  [API 14+]
- getTransform(Matrix): Matrix  [API 14+]
- isAvailable(): boolean  [API 14+]
- isOpaque(): boolean  [API 14+]
- lockCanvas(): Canvas  [API 14+]
- lockCanvas(Rect): Canvas  [API 14+]
- onAttachedToWindow(): void  [API 14+]
- onDraw(Canvas): void  [API 14+]
- onSizeChanged(int, int, int, int): void  [API 14+]
- onVisibilityChanged(View, int): void  [API 14+]
- setBackgroundDrawable(Drawable): void  [API 14+]
- setForeground(Drawable): void  [API 14+]
- setLayerPaint(Paint): void  [API 14+]
- setLayerType(int, Paint): void  [API 14+]
- setOpaque(boolean): void  [API 14+]
- setSurfaceTexture(SurfaceTexture): void  [API 16+]
- setSurfaceTextureListener(TextureView.SurfaceTextureListener): void  [API 14+]
- setTransform(Matrix): void  [API 14+]
- unlockCanvasAndPost(Canvas): void  [API 14+]
```

## TextureView.SurfaceTextureListener — declaration; API 14+ (4 +, 0 *, 0 ~, 0 -)

```
+ onSurfaceTextureAvailable(SurfaceTexture, int, int): void  [API 14+]
+ onSurfaceTextureDestroyed(SurfaceTexture): boolean  [API 14+]
+ onSurfaceTextureSizeChanged(SurfaceTexture, int, int): void  [API 14+]
+ onSurfaceTextureUpdated(SurfaceTexture): void  [API 14+]
```

## TouchDelegate — framework; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
+ TouchDelegate(Rect, View)  [API 1+]
- getTouchDelegateInfo(): AccessibilityNodeInfo.TouchDelegateInfo  [API 29+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
- onTouchExplorationHoverEvent(MotionEvent): boolean  [API 29+]
```

## VelocityTracker — framework; API 1+ (11 +, 0 *, 0 ~, 3 -)

```
+ addMovement(MotionEvent): void  [API 1+]
+ clear(): void  [API 1+]
+ computeCurrentVelocity(int): void  [API 1+]
+ computeCurrentVelocity(int, float): void  [API 4+]
+ finalize(): void  [API 1+]
- getAxisVelocity(int): float  [API 34+]
- getAxisVelocity(int, int): float  [API 34+]
+ getXVelocity(): float  [API 1+]
+ getXVelocity(int): float  [API 8+]
+ getYVelocity(): float  [API 1+]
+ getYVelocity(int): float  [API 8+]
- isAxisSupported(int): boolean  [API 34+]
+ obtain(): VelocityTracker [static]  [API 1+]
+ recycle(): void  [API 1+]
```

## VerifiedInputEvent — declaration; API 30+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getDeviceId(): int  [API 30+]
- getDisplayId(): int  [API 30+]
- getEventTimeNanos(): long  [API 30+]
- getSource(): int  [API 30+]
- hashCode(): int  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## VerifiedKeyEvent — declaration; API 30+ (0 +, 0 *, 0 ~, 11 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getAction(): int  [API 30+]
- getDownTimeNanos(): long  [API 30+]
- getFlag(int): Boolean  [API 30+]
- getKeyCode(): int  [API 30+]
- getMetaState(): int  [API 30+]
- getRepeatCount(): int  [API 30+]
- getScanCode(): int  [API 30+]
- hashCode(): int  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## VerifiedMotionEvent — declaration; API 30+ (0 +, 0 *, 0 ~, 11 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getActionMasked(): int  [API 30+]
- getButtonState(): int  [API 30+]
- getDownTimeNanos(): long  [API 30+]
- getFlag(int): Boolean  [API 30+]
- getMetaState(): int  [API 30+]
- getRawX(): float  [API 30+]
- getRawY(): float  [API 30+]
- hashCode(): int  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## View — framework; API 1+ (577 +, 0 *, 109 ~, 7 -)

```
+ ALPHA [field]  [API 14+]
+ EMPTY_STATE_SET [field]  [API 1+]
+ ENABLED_FOCUSED_SELECTED_STATE_SET [field]  [API 1+]
+ ENABLED_FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ ENABLED_FOCUSED_STATE_SET [field]  [API 1+]
+ ENABLED_FOCUSED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ ENABLED_SELECTED_STATE_SET [field]  [API 1+]
+ ENABLED_SELECTED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ ENABLED_STATE_SET [field]  [API 1+]
+ ENABLED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ FOCUSED_SELECTED_STATE_SET [field]  [API 1+]
+ FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ FOCUSED_STATE_SET [field]  [API 1+]
+ FOCUSED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_ENABLED_FOCUSED_SELECTED_STATE_SET [field]  [API 1+]
+ PRESSED_ENABLED_FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_ENABLED_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_ENABLED_FOCUSED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_ENABLED_SELECTED_STATE_SET [field]  [API 1+]
+ PRESSED_ENABLED_SELECTED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_ENABLED_STATE_SET [field]  [API 1+]
+ PRESSED_ENABLED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_FOCUSED_SELECTED_STATE_SET [field]  [API 1+]
+ PRESSED_FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_FOCUSED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_SELECTED_STATE_SET [field]  [API 1+]
+ PRESSED_SELECTED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ PRESSED_STATE_SET [field]  [API 19+]
+ PRESSED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ ROTATION [field]  [API 14+]
+ ROTATION_X [field]  [API 14+]
+ ROTATION_Y [field]  [API 14+]
+ SCALE_X [field]  [API 14+]
+ SCALE_Y [field]  [API 14+]
+ SELECTED_STATE_SET [field]  [API 1+]
+ SELECTED_WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ TRANSLATION_X [field]  [API 14+]
+ TRANSLATION_Y [field]  [API 14+]
+ TRANSLATION_Z [field]  [API 21+]
+ View(Context)  [API 1+]
+ View(Context, AttributeSet)  [API 1+]
+ View(Context, AttributeSet, int)  [API 1+]
+ View(Context, AttributeSet, int, int)  [API 21+]
+ WINDOW_FOCUSED_STATE_SET [field]  [API 1+]
+ X [field]  [API 14+]
+ Y [field]  [API 14+]
+ Z [field]  [API 21+]
~ addChildrenForAccessibility(ArrayList): void  [API 16+]
~ addExtraDataToAccessibilityNodeInfo(AccessibilityNodeInfo, String, Bundle): void  [API 26+]
+ addFocusables(ArrayList, int): void  [API 1+]
+ addFocusables(ArrayList, int, int): void  [API 4+]
+ addKeyboardNavigationClusters(Collection, int): void  [API 26+]
+ addOnAttachStateChangeListener(View.OnAttachStateChangeListener): void  [API 12+]
+ addOnLayoutChangeListener(View.OnLayoutChangeListener): void  [API 11+]
+ addOnUnhandledKeyEventListener(View.OnUnhandledKeyEventListener): void  [API 28+]
+ addTouchables(ArrayList): void  [API 1+]
+ animate(): ViewPropertyAnimator  [API 12+]
~ announceForAccessibility(CharSequence): void  [API 16+]
~ autofill(AutofillValue): void  [API 26+]
~ autofill(SparseArray): void  [API 26+]
~ awakenScrollBars(): boolean  [API 5+]
~ awakenScrollBars(int): boolean  [API 5+]
+ awakenScrollBars(int, boolean): boolean  [API 5+]
+ bringToFront(): void  [API 1+]
~ buildDrawingCache(): void  [API 1+, deprecated in 28]
~ buildDrawingCache(boolean): void  [API 4+, deprecated in 28]
+ buildLayer(): void  [API 12+]
+ callOnClick(): boolean  [API 15+]
~ canResolveLayoutDirection(): boolean  [API 19+]
~ canResolveTextAlignment(): boolean  [API 19+]
~ canResolveTextDirection(): boolean  [API 19+]
~ canScrollHorizontally(int): boolean  [API 14+]
~ canScrollVertically(int): boolean  [API 14+]
~ cancelDragAndDrop(): void  [API 24+]
+ cancelLongPress(): void  [API 1+]
+ cancelPendingInputEvents(): void  [API 19+]
~ checkInputConnectionProxy(View): boolean  [API 3+]
+ clearAnimation(): void  [API 1+]
+ clearFocus(): void  [API 1+]
+ clearPendingCredentialRequest(): void  [API 35+]
+ clearViewTranslationCallback(): void  [API 31+]
+ combineMeasuredStates(int, int): int [static]  [API 11+]
+ computeHorizontalScrollExtent(): int  [API 1+]
+ computeHorizontalScrollOffset(): int  [API 1+]
+ computeHorizontalScrollRange(): int  [API 1+]
~ computeScroll(): void  [API 1+]
+ computeSystemWindowInsets(WindowInsets, Rect): WindowInsets  [API 21+]
+ computeVerticalScrollExtent(): int  [API 1+]
+ computeVerticalScrollOffset(): int  [API 1+]
+ computeVerticalScrollRange(): int  [API 1+]
+ createAccessibilityNodeInfo(): AccessibilityNodeInfo  [API 14+]
+ createContextMenu(ContextMenu): void  [API 1+]
~ destroyDrawingCache(): void  [API 1+, deprecated in 28]
+ dispatchApplyWindowInsets(WindowInsets): WindowInsets  [API 20+]
+ dispatchCapturedPointerEvent(MotionEvent): boolean  [API 26+]
+ dispatchConfigurationChanged(Configuration): void  [API 8+]
+ dispatchCreateViewTranslationRequest(Map, int[], TranslationCapability, List): void  [API 31+]
+ dispatchDisplayHint(int): void  [API 8+]
+ dispatchDragEvent(DragEvent): boolean  [API 11+]
~ dispatchDraw(Canvas): void  [API 1+]
~ dispatchDrawableHotspotChanged(float, float): void  [API 22+]
+ dispatchFinishTemporaryDetach(): void  [API 24+]
~ dispatchGenericFocusedEvent(MotionEvent): boolean  [API 14+]
+ dispatchGenericMotionEvent(MotionEvent): boolean  [API 12+]
~ dispatchGenericPointerEvent(MotionEvent): boolean  [API 14+]
+ dispatchHoverEvent(MotionEvent): boolean  [API 14+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 1+]
+ dispatchKeyEventPreIme(KeyEvent): boolean  [API 3+]
+ dispatchKeyShortcutEvent(KeyEvent): boolean  [API 1+]
+ dispatchNestedFling(float, float, boolean): boolean  [API 21+]
+ dispatchNestedPreFling(float, float): boolean  [API 21+]
+ dispatchNestedPrePerformAccessibilityAction(int, Bundle): boolean  [API 22+]
+ dispatchNestedPreScroll(int, int, int[], int[]): boolean  [API 21+]
+ dispatchNestedScroll(int, int, int, int, int[]): boolean  [API 21+]
+ dispatchPointerCaptureChanged(boolean): void  [API 26+]
+ dispatchPopulateAccessibilityEvent(AccessibilityEvent): boolean  [API 4+]
+ dispatchProvideAutofillStructure(ViewStructure, int): void  [API 26+]
+ dispatchProvideStructure(ViewStructure): void  [API 23+]
+ dispatchRestoreInstanceState(SparseArray): void  [API 1+]
+ dispatchSaveInstanceState(SparseArray): void  [API 1+]
+ dispatchScrollCaptureSearch(Rect, Point, Consumer): void  [API 31+]
~ dispatchSetActivated(boolean): void  [API 11+]
~ dispatchSetPressed(boolean): void  [API 1+]
~ dispatchSetSelected(boolean): void  [API 1+]
+ dispatchStartTemporaryDetach(): void  [API 24+]
~ dispatchSystemUiVisibilityChanged(int): void  [API 11+, deprecated in 30]
+ dispatchTouchEvent(MotionEvent): boolean  [API 1+]
+ dispatchTrackballEvent(MotionEvent): boolean  [API 1+]
~ dispatchUnhandledMove(View, int): boolean  [API 1+]
+ dispatchVisibilityChanged(View, int): void  [API 8+]
+ dispatchWindowFocusChanged(boolean): void  [API 1+]
+ dispatchWindowInsetsAnimationEnd(WindowInsetsAnimation): void  [API 30+]
+ dispatchWindowInsetsAnimationPrepare(WindowInsetsAnimation): void  [API 30+]
+ dispatchWindowInsetsAnimationProgress(WindowInsets, List): WindowInsets  [API 30+]
+ dispatchWindowInsetsAnimationStart(WindowInsetsAnimation, WindowInsetsAnimation.Bounds): WindowInsetsAnimation.Bounds  [API 30+]
+ dispatchWindowSystemUiVisiblityChanged(int): void  [API 16+, deprecated in 30]
+ dispatchWindowVisibilityChanged(int): void  [API 1+]
+ draw(Canvas): void  [API 1+]
+ drawableHotspotChanged(float, float): void  [API 21+]
+ drawableStateChanged(): void  [API 1+]
+ findFocus(): View  [API 1+]
+ findOnBackInvokedDispatcher(): OnBackInvokedDispatcher  [API 33+]
+ findViewById(int): View  [API 1+]
+ findViewWithTag(Object): View  [API 1+]
~ findViewsWithText(ArrayList, CharSequence, int): void  [API 14+]
~ fitSystemWindows(Rect): boolean  [API 1+, deprecated in 20]
+ fitsSystemWindows(): boolean  [API 14-15, removed in 16]
+ focusSearch(int): View  [API 1+]
~ forceHasOverlappingRendering(boolean): void  [API 24+]
+ forceLayout(): void  [API 1+]
+ gatherTransparentRegion(Region): boolean  [API 31+]
- generateDisplayHash(String, Rect, Executor, DisplayHashResultCallback): void  [API 31+]
+ generateViewId(): int [static]  [API 17+]
+ getAccessibilityClassName(): CharSequence  [API 23+]
+ getAccessibilityDelegate(): View.AccessibilityDelegate  [API 29+]
+ getAccessibilityLiveRegion(): int  [API 19+]
+ getAccessibilityNodeProvider(): AccessibilityNodeProvider  [API 16+]
+ getAccessibilityPaneTitle(): CharSequence  [API 28+]
+ getAccessibilityTraversalAfter(): int  [API 22+]
+ getAccessibilityTraversalBefore(): int  [API 22+]
+ getAllowedHandwritingDelegatePackageName(): String  [API 34+]
+ getAllowedHandwritingDelegatorPackageName(): String  [API 34+]
+ getAlpha(): float  [API 11+]
+ getAnimation(): Animation  [API 1+]
+ getAnimationMatrix(): Matrix  [API 29+]
+ getApplicationWindowToken(): IBinder  [API 1+]
+ getAttributeResolutionStack(int): int[]  [API 29+]
+ getAttributeSourceResourceMap(): Map  [API 29+]
+ getAutofillHints(): String[]  [API 26+]
+ getAutofillId(): AutofillId  [API 26+]
~ getAutofillType(): int  [API 26+]
~ getAutofillValue(): AutofillValue  [API 26+]
+ getBackground(): Drawable  [API 1+]
+ getBackgroundTintBlendMode(): BlendMode  [API 29+]
+ getBackgroundTintList(): ColorStateList  [API 21+]
+ getBackgroundTintMode(): PorterDuff.Mode  [API 21+]
~ getBaseline(): int  [API 1+]
+ getBottom(): int  [API 1+]
~ getBottomFadingEdgeStrength(): float  [API 1+]
~ getBottomPaddingOffset(): int  [API 2+]
+ getCameraDistance(): float  [API 16+]
+ getClipBounds(): Rect  [API 18+]
+ getClipBounds(Rect): boolean  [API 23+]
~ getClipToOutline(): boolean  [API 21+]
+ getContentCaptureSession(): ContentCaptureSession  [API 29+]
~ getContentDescription(): CharSequence  [API 4+]
+ getContentSensitivity(): int  [API 35+]
+ getContext(): Context  [API 1+]
~ getContextMenuInfo(): ContextMenu.ContextMenuInfo  [API 1+]
+ getDefaultFocusHighlightEnabled(): boolean  [API 26+]
+ getDefaultSize(int, int): int [static]  [API 1+]
+ getDisplay(): Display  [API 17+]
+ getDrawableState(): int[]  [API 1+]
+ getDrawingCache(): Bitmap  [API 1+, deprecated in 28]
+ getDrawingCache(boolean): Bitmap  [API 4+, deprecated in 28]
+ getDrawingCacheBackgroundColor(): int  [API 1+, deprecated in 28]
+ getDrawingCacheQuality(): int  [API 1+, deprecated in 28]
+ getDrawingRect(Rect): void  [API 1+]
+ getDrawingTime(): long  [API 1+]
+ getElevation(): float  [API 21+]
~ getExplicitStyle(): int  [API 29+]
~ getFilterTouchesWhenObscured(): boolean  [API 9+]
+ getFitsSystemWindows(): boolean  [API 16+]
+ getFocusable(): int  [API 26+]
+ getFocusables(int): ArrayList  [API 1+]
+ getFocusedRect(Rect): void  [API 1+]
+ getForeground(): Drawable  [API 23+]
+ getForegroundGravity(): int  [API 23+]
+ getForegroundTintBlendMode(): BlendMode  [API 29+]
+ getForegroundTintList(): ColorStateList  [API 23+]
+ getForegroundTintMode(): PorterDuff.Mode  [API 23+]
+ getFrameContentVelocity(): float  [API 35+]
+ getGlobalVisibleRect(Rect): boolean  [API 1+]
+ getGlobalVisibleRect(Rect, Point): boolean  [API 1+]
+ getHandler(): Handler  [API 1+]
+ getHandwritingBoundsOffsetBottom(): float  [API 34+]
+ getHandwritingBoundsOffsetLeft(): float  [API 34+]
+ getHandwritingBoundsOffsetRight(): float  [API 34+]
+ getHandwritingBoundsOffsetTop(): float  [API 34+]
+ getHandwritingDelegateFlags(): int  [API 35+]
+ getHandwritingDelegatorCallback(): Runnable  [API 34+]
~ getHasOverlappingRendering(): boolean  [API 24+]
+ getHeight(): int  [API 1+]
+ getHitRect(Rect): void  [API 1+]
~ getHorizontalFadingEdgeLength(): int  [API 1+]
+ getHorizontalScrollbarHeight(): int  [API 1+]
+ getHorizontalScrollbarThumbDrawable(): Drawable  [API 29+]
+ getHorizontalScrollbarTrackDrawable(): Drawable  [API 29+]
+ getId(): int  [API 1+]
+ getImportantForAccessibility(): int  [API 16+]
+ getImportantForAutofill(): int  [API 26+]
+ getImportantForContentCapture(): int  [API 30+]
+ getKeepScreenOn(): boolean  [API 1+]
+ getKeyDispatcherState(): KeyEvent.DispatcherState  [API 5+]
+ getLabelFor(): int  [API 17+]
~ getLayerType(): int  [API 11+]
~ getLayoutDirection(): int  [API 17+]
+ getLayoutParams(): ViewGroup.LayoutParams  [API 1+]
+ getLeft(): int  [API 1+]
+ getLeftFadingEdgeStrength(): float  [API 1+]
~ getLeftPaddingOffset(): int  [API 2+]
+ getLocalVisibleRect(Rect): boolean  [API 1+]
+ getLocationInSurface(int[]): void  [API 29+]
+ getLocationInWindow(int[]): void  [API 1+]
+ getLocationOnScreen(int[]): void  [API 1+]
+ getMatrix(): Matrix  [API 11+]
+ getMeasuredHeight(): int  [API 1+]
+ getMeasuredHeightAndState(): int  [API 11+]
+ getMeasuredState(): int  [API 11+]
+ getMeasuredWidth(): int  [API 1+]
+ getMeasuredWidthAndState(): int  [API 11+]
+ getMinimumHeight(): int  [API 16+]
+ getMinimumWidth(): int  [API 16+]
+ getNextClusterForwardId(): int  [API 26+]
+ getNextFocusDownId(): int  [API 1+]
+ getNextFocusForwardId(): int  [API 11+]
+ getNextFocusLeftId(): int  [API 1+]
+ getNextFocusRightId(): int  [API 1+]
+ getNextFocusUpId(): int  [API 1+]
+ getOnFocusChangeListener(): View.OnFocusChangeListener  [API 1+]
+ getOutlineAmbientShadowColor(): int  [API 28+]
+ getOutlineProvider(): ViewOutlineProvider  [API 21+]
+ getOutlineSpotShadowColor(): int  [API 28+]
+ getOverScrollMode(): int  [API 9+]
- getOverlay(): ViewOverlay  [API 18+]
+ getPaddingBottom(): int  [API 1+]
+ getPaddingEnd(): int  [API 17+]
+ getPaddingLeft(): int  [API 1+]
+ getPaddingRight(): int  [API 1+]
+ getPaddingStart(): int  [API 17+]
+ getPaddingTop(): int  [API 1+]
+ getParent(): ViewParent  [API 1+]
+ getParentForAccessibility(): ViewParent  [API 16+]
+ getPendingCredentialCallback(): OutcomeReceiver  [API 35+]
+ getPendingCredentialRequest(): GetCredentialRequest  [API 35+]
+ getPivotX(): float  [API 11+]
+ getPivotY(): float  [API 11+]
+ getPointerIcon(): PointerIcon  [API 24+]
+ getPreferKeepClearRects(): List  [API 33+]
+ getReceiveContentMimeTypes(): String[]  [API 31+]
+ getRequestedFrameRate(): float  [API 35+]
+ getResources(): Resources  [API 1+]
+ getRevealOnFocusHint(): boolean  [API 25+]
+ getRight(): int  [API 1+]
+ getRightFadingEdgeStrength(): float  [API 1+]
~ getRightPaddingOffset(): int  [API 2+]
- getRootSurfaceControl(): AttachedSurfaceControl  [API 31+]
+ getRootView(): View  [API 1+]
+ getRootWindowInsets(): WindowInsets  [API 23+]
+ getRotation(): float  [API 11+]
+ getRotationX(): float  [API 11+]
+ getRotationY(): float  [API 11+]
+ getScaleX(): float  [API 11+]
+ getScaleY(): float  [API 11+]
~ getScrollBarDefaultDelayBeforeFade(): int  [API 16+]
~ getScrollBarFadeDuration(): int  [API 16+]
+ getScrollBarSize(): int  [API 16+]
+ getScrollBarStyle(): int  [API 1+]
+ getScrollCaptureHint(): int  [API 31+]
+ getScrollIndicators(): int  [API 23+]
+ getScrollX(): int  [API 1+]
+ getScrollY(): int  [API 1+]
~ getSolidColor(): int  [API 1+]
+ getSourceLayoutResId(): int  [API 29+]
+ getStateDescription(): CharSequence  [API 30+]
~ getStateListAnimator(): StateListAnimator  [API 21+]
+ getSuggestedMinimumHeight(): int  [API 1+]
+ getSuggestedMinimumWidth(): int  [API 1+]
+ getSystemGestureExclusionRects(): List  [API 29+]
~ getSystemUiVisibility(): int  [API 11+, deprecated in 30]
+ getTag(): Object  [API 1+]
+ getTag(int): Object  [API 4+]
+ getTextAlignment(): int  [API 17+]
+ getTextDirection(): int  [API 17+]
+ getTooltipText(): CharSequence  [API 26+]
+ getTop(): int  [API 1+]
~ getTopFadingEdgeStrength(): float  [API 1+]
~ getTopPaddingOffset(): int  [API 2+]
+ getTouchDelegate(): TouchDelegate  [API 1+]
+ getTouchables(): ArrayList  [API 1+]
~ getTransitionAlpha(): float  [API 29+]
+ getTransitionName(): String  [API 21+]
+ getTranslationX(): float  [API 11+]
+ getTranslationY(): float  [API 11+]
+ getTranslationZ(): float  [API 21+]
+ getUniqueDrawingId(): long  [API 29+]
~ getVerticalFadingEdgeLength(): int  [API 1+]
+ getVerticalScrollbarPosition(): int  [API 11+]
+ getVerticalScrollbarThumbDrawable(): Drawable  [API 29+]
+ getVerticalScrollbarTrackDrawable(): Drawable  [API 29+]
+ getVerticalScrollbarWidth(): int  [API 1+]
+ getViewTranslationResponse(): ViewTranslationResponse  [API 31+]
+ getViewTreeObserver(): ViewTreeObserver  [API 1+]
+ getVisibility(): int  [API 1+]
+ getWidth(): int  [API 1+]
+ getWindowAttachCount(): int  [API 1+]
- getWindowId(): WindowId  [API 18+]
- getWindowInsetsController(): WindowInsetsController  [API 30+]
~ getWindowSystemUiVisibility(): int  [API 16+, deprecated in 30]
+ getWindowToken(): IBinder  [API 1+]
+ getWindowVisibility(): int  [API 1+]
+ getWindowVisibleDisplayFrame(Rect): void  [API 3+]
+ getX(): float  [API 11+]
+ getY(): float  [API 11+]
+ getZ(): float  [API 21+]
+ hasExplicitFocusable(): boolean  [API 26+]
+ hasFocus(): boolean  [API 1+]
+ hasFocusable(): boolean  [API 1+]
+ hasNestedScrollingParent(): boolean  [API 21+]
+ hasOnClickListeners(): boolean  [API 15+]
+ hasOnLongClickListeners(): boolean  [API 30+]
~ hasOverlappingRendering(): boolean  [API 16+]
+ hasPointerCapture(): boolean  [API 26+]
~ hasTransientState(): boolean  [API 16+]
+ hasWindowFocus(): boolean  [API 1+]
+ inflate(Context, int, ViewGroup): View [static]  [API 1+]
- initializeFadingEdge(TypedArray): void  [API 1-20, removed in 21]
- initializeScrollbars(TypedArray): void  [API 1-20, removed in 21]
+ invalidate(): void  [API 1+]
+ invalidate(Rect): void  [API 1+, deprecated in 28]
+ invalidate(int, int, int, int): void  [API 1+, deprecated in 28]
+ invalidateDrawable(Drawable): void  [API 1+]
~ invalidateOutline(): void  [API 21+]
+ isAccessibilityDataSensitive(): boolean  [API 34+]
~ isAccessibilityFocused(): boolean  [API 21+]
+ isAccessibilityHeading(): boolean  [API 28+]
+ isActivated(): boolean  [API 11+]
+ isAttachedToWindow(): boolean  [API 19+]
+ isAutoHandwritingEnabled(): boolean  [API 33+]
+ isClickable(): boolean  [API 1+]
+ isContentSensitive(): boolean  [API 35+]
+ isContextClickable(): boolean  [API 23+]
+ isCredential(): boolean  [API 34+]
~ isDirty(): boolean  [API 11+]
+ isDrawingCacheEnabled(): boolean  [API 1+, deprecated in 28]
+ isDuplicateParentStateEnabled(): boolean  [API 1+]
+ isEnabled(): boolean  [API 1+]
+ isFocusable(): boolean  [API 1+]
+ isFocusableInTouchMode(): boolean  [API 1+]
+ isFocused(): boolean  [API 1+]
+ isFocusedByDefault(): boolean  [API 26+]
+ isForceDarkAllowed(): boolean  [API 29+]
+ isHandwritingDelegate(): boolean  [API 34+]
+ isHapticFeedbackEnabled(): boolean  [API 3+]
~ isHardwareAccelerated(): boolean  [API 11+]
~ isHorizontalFadingEdgeEnabled(): boolean  [API 1+]
+ isHorizontalScrollBarEnabled(): boolean  [API 1+]
+ isHovered(): boolean  [API 14+]
+ isImportantForAccessibility(): boolean  [API 21+]
+ isImportantForAutofill(): boolean  [API 26+]
+ isImportantForContentCapture(): boolean  [API 30+]
~ isInEditMode(): boolean  [API 3+]
~ isInLayout(): boolean  [API 18+]
+ isInTouchMode(): boolean  [API 1+]
+ isKeyboardNavigationCluster(): boolean  [API 26+]
+ isLaidOut(): boolean  [API 19+]
~ isLayoutDirectionResolved(): boolean  [API 19+]
+ isLayoutRequested(): boolean  [API 1+]
+ isLongClickable(): boolean  [API 1+]
+ isNestedScrollingEnabled(): boolean  [API 21+]
+ isOpaque(): boolean  [API 7+]
~ isPaddingOffsetRequired(): boolean  [API 2+]
~ isPaddingRelative(): boolean  [API 17+]
+ isPivotSet(): boolean  [API 28+]
+ isPreferKeepClear(): boolean  [API 33+]
+ isPressed(): boolean  [API 1+]
+ isSaveEnabled(): boolean  [API 1+]
+ isSaveFromParentEnabled(): boolean  [API 11+]
+ isScreenReaderFocusable(): boolean  [API 28+]
~ isScrollContainer(): boolean  [API 16+]
~ isScrollbarFadingEnabled(): boolean  [API 5+]
+ isSelected(): boolean  [API 1+]
~ isShowingLayoutBounds(): boolean  [API 30+]
+ isShown(): boolean  [API 1+]
+ isSoundEffectsEnabled(): boolean  [API 1+]
~ isTemporarilyDetached(): boolean  [API 24+]
~ isTextAlignmentResolved(): boolean  [API 19+]
~ isTextDirectionResolved(): boolean  [API 19+]
~ isVerticalFadingEdgeEnabled(): boolean  [API 1+]
+ isVerticalScrollBarEnabled(): boolean  [API 1+]
+ isVisibleToUserForAutofill(int): boolean  [API 28+]
~ jumpDrawablesToCurrentState(): void  [API 11+]
+ keyboardNavigationClusterSearch(View, int): View  [API 26+]
+ layout(int, int, int, int): void  [API 1+]
+ measure(int, int): void  [API 1+]
+ mergeDrawableStates(int[], int[]): int[] [static]  [API 1+]
+ offsetLeftAndRight(int): void  [API 1+]
+ offsetTopAndBottom(int): void  [API 1+]
+ onAnimationEnd(): void  [API 1+]
+ onAnimationStart(): void  [API 1+]
+ onApplyWindowInsets(WindowInsets): WindowInsets  [API 20+]
+ onAttachedToWindow(): void  [API 1+]
+ onCancelPendingInputEvents(): void  [API 19+]
+ onCapturedPointerEvent(MotionEvent): boolean  [API 26+]
+ onCheckIsTextEditor(): boolean  [API 3+]
+ onConfigurationChanged(Configuration): void  [API 8+]
+ onCreateContextMenu(ContextMenu): void  [API 1+]
+ onCreateDrawableState(int): int[]  [API 1+]
+ onCreateInputConnection(EditorInfo): InputConnection  [API 3+]
+ onCreateViewTranslationRequest(int[], Consumer): void  [API 31+]
+ onCreateVirtualViewTranslationRequests(long[], int[], Consumer): void  [API 31+]
+ onDetachedFromWindow(): void  [API 1+]
+ onDisplayHint(int): void  [API 8+]
+ onDragEvent(DragEvent): boolean  [API 11+]
+ onDraw(Canvas): void  [API 1+]
+ onDrawForeground(Canvas): void  [API 23+]
+ onDrawScrollBars(Canvas): void  [API 7+]
+ onFilterTouchEventForSecurity(MotionEvent): boolean  [API 9+]
+ onFinishInflate(): void  [API 1+]
+ onFinishTemporaryDetach(): void  [API 3+]
+ onFocusChanged(boolean, int, Rect): void  [API 1+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 12+]
+ onHoverChanged(boolean): void  [API 14+]
+ onHoverEvent(MotionEvent): boolean  [API 14+]
+ onInitializeAccessibilityEvent(AccessibilityEvent): void  [API 14+]
+ onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo): void  [API 14+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyLongPress(int, KeyEvent): boolean  [API 1+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 1+]
+ onKeyPreIme(int, KeyEvent): boolean  [API 3+]
+ onKeyShortcut(int, KeyEvent): boolean  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onOverScrolled(int, int, boolean, boolean): void  [API 9+]
+ onPointerCaptureChange(boolean): void  [API 26+]
+ onPopulateAccessibilityEvent(AccessibilityEvent): void  [API 14+]
+ onProvideAutofillStructure(ViewStructure, int): void  [API 26+]
+ onProvideAutofillVirtualStructure(ViewStructure, int): void  [API 26+]
+ onProvideContentCaptureStructure(ViewStructure, int): void  [API 30+]
+ onProvideStructure(ViewStructure): void  [API 23+]
+ onProvideVirtualStructure(ViewStructure): void  [API 23+]
+ onReceiveContent(ContentInfo): ContentInfo  [API 31+]
+ onResolvePointerIcon(MotionEvent, int): PointerIcon  [API 24+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onRtlPropertiesChanged(int): void  [API 17+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ onScreenStateChanged(int): void  [API 16+]
+ onScrollCaptureSearch(Rect, Point, Consumer): void  [API 31+]
+ onScrollChanged(int, int, int, int): void  [API 1+]
+ onSetAlpha(int): boolean  [API 1+]
+ onSizeChanged(int, int, int, int): void  [API 1+]
+ onStartTemporaryDetach(): void  [API 3+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ onTrackballEvent(MotionEvent): boolean  [API 1+]
+ onViewTranslationResponse(ViewTranslationResponse): void  [API 31+]
+ onVirtualViewTranslationResponses(LongSparseArray): void  [API 31+]
+ onVisibilityAggregated(boolean): void  [API 24+]
+ onVisibilityChanged(View, int): void  [API 8+]
+ onWindowFocusChanged(boolean): void  [API 1+]
+ onWindowSystemUiVisibilityChanged(int): void  [API 16+, deprecated in 30]
+ onWindowVisibilityChanged(int): void  [API 1+]
+ overScrollBy(int, int, int, int, int, int, int, int, boolean): boolean  [API 9+]
+ performAccessibilityAction(int, Bundle): boolean  [API 16+]
+ performClick(): boolean  [API 1+]
+ performContextClick(): boolean  [API 23+]
+ performContextClick(float, float): boolean  [API 24+]
~ performHapticFeedback(int): boolean  [API 3+]
~ performHapticFeedback(int, int): boolean  [API 3+]
+ performLongClick(): boolean  [API 1+]
+ performLongClick(float, float): boolean  [API 24+]
+ performReceiveContent(ContentInfo): ContentInfo  [API 31+]
~ playSoundEffect(int): void  [API 1+]
+ post(Runnable): boolean  [API 1+]
+ postDelayed(Runnable, long): boolean  [API 1+]
+ postInvalidate(): void  [API 1+]
+ postInvalidate(int, int, int, int): void  [API 1+]
+ postInvalidateDelayed(long): void  [API 1+]
+ postInvalidateDelayed(long, int, int, int, int): void  [API 1+]
+ postInvalidateOnAnimation(): void  [API 16+]
+ postInvalidateOnAnimation(int, int, int, int): void  [API 16+]
+ postOnAnimation(Runnable): void  [API 16+]
+ postOnAnimationDelayed(Runnable, long): void  [API 16+]
+ refreshDrawableState(): void  [API 1+]
+ releasePointerCapture(): void  [API 26+]
+ removeCallbacks(Runnable): boolean  [API 1+]
+ removeOnAttachStateChangeListener(View.OnAttachStateChangeListener): void  [API 12+]
+ removeOnLayoutChangeListener(View.OnLayoutChangeListener): void  [API 11+]
+ removeOnUnhandledKeyEventListener(View.OnUnhandledKeyEventListener): void  [API 28+]
+ requestApplyInsets(): void  [API 20+]
~ requestFitSystemWindows(): void  [API 16+, deprecated in 20]
+ requestFocus(): boolean  [API 1+]
+ requestFocus(int): boolean  [API 1+]
+ requestFocus(int, Rect): boolean  [API 1+]
+ requestFocusFromTouch(): boolean  [API 1+]
+ requestLayout(): void  [API 1+]
~ requestPointerCapture(): void  [API 26+]
+ requestRectangleOnScreen(Rect): boolean  [API 1+]
+ requestRectangleOnScreen(Rect, boolean): boolean  [API 1+]
~ requestUnbufferedDispatch(MotionEvent): void  [API 21+]
~ requestUnbufferedDispatch(int): void  [API 30+]
+ requireViewById(int): View  [API 28+]
+ resetPivot(): void  [API 28+]
+ resolveSize(int, int): int [static]  [API 1+]
+ resolveSizeAndState(int, int, int): int [static]  [API 11+]
+ restoreDefaultFocus(): boolean  [API 26+]
+ restoreHierarchyState(SparseArray): void  [API 1+]
~ saveAttributeDataForStyleable(Context, int[], AttributeSet, TypedArray, int, int): void  [API 29+]
+ saveHierarchyState(SparseArray): void  [API 1+]
+ scheduleDrawable(Drawable, Runnable, long): void  [API 1+]
+ scrollBy(int, int): void  [API 1+]
+ scrollTo(int, int): void  [API 1+]
~ sendAccessibilityEvent(int): void  [API 1+]
+ sendAccessibilityEventUnchecked(AccessibilityEvent): void  [API 1+]
+ setAccessibilityDataSensitive(int): void  [API 34+]
+ setAccessibilityDelegate(View.AccessibilityDelegate): void  [API 14+]
+ setAccessibilityHeading(boolean): void  [API 28+]
+ setAccessibilityLiveRegion(int): void  [API 19+]
+ setAccessibilityPaneTitle(CharSequence): void  [API 28+]
+ setAccessibilityTraversalAfter(int): void  [API 22+]
+ setAccessibilityTraversalBefore(int): void  [API 22+]
+ setActivated(boolean): void  [API 11+]
+ setAllowClickWhenDisabled(boolean): void  [API 31+]
+ setAllowedHandwritingDelegatePackage(String): void  [API 34+]
+ setAllowedHandwritingDelegatorPackage(String): void  [API 34+]
+ setAlpha(float): void  [API 11+]
+ setAnimation(Animation): void  [API 1+]
+ setAnimationMatrix(Matrix): void  [API 29+]
+ setAutoHandwritingEnabled(boolean): void  [API 33+]
+ setAutofillHints(String[]): void  [API 26+]
+ setAutofillId(AutofillId): void  [API 28+]
+ setBackground(Drawable): void  [API 16+]
+ setBackgroundColor(int): void  [API 1+]
+ setBackgroundDrawable(Drawable): void  [API 1+, deprecated in 16]
+ setBackgroundResource(int): void  [API 1+]
+ setBackgroundTintBlendMode(BlendMode): void  [API 29+]
+ setBackgroundTintList(ColorStateList): void  [API 21+]
+ setBackgroundTintMode(PorterDuff.Mode): void  [API 21+]
+ setBottom(int): void  [API 11+]
+ setCameraDistance(float): void  [API 12+]
+ setClickable(boolean): void  [API 1+]
+ setClipBounds(Rect): void  [API 18+]
~ setClipToOutline(boolean): void  [API 21+]
+ setContentCaptureSession(ContentCaptureSession): void  [API 29+]
~ setContentDescription(CharSequence): void  [API 4+]
+ setContentSensitivity(int): void  [API 35+]
+ setContextClickable(boolean): void  [API 23+]
+ setDefaultFocusHighlightEnabled(boolean): void  [API 26+]
+ setDrawingCacheBackgroundColor(int): void  [API 1+, deprecated in 28]
+ setDrawingCacheEnabled(boolean): void  [API 1+, deprecated in 28]
~ setDrawingCacheQuality(int): void  [API 1+, deprecated in 28]
+ setDuplicateParentStateEnabled(boolean): void  [API 1+]
+ setElevation(float): void  [API 21+]
+ setEnabled(boolean): void  [API 1+]
~ setFadingEdgeLength(int): void  [API 1+]
~ setFilterTouchesWhenObscured(boolean): void  [API 9+]
+ setFitsSystemWindows(boolean): void  [API 14+]
+ setFocusable(boolean): void  [API 1+]
+ setFocusable(int): void  [API 26+]
+ setFocusableInTouchMode(boolean): void  [API 1+]
+ setFocusedByDefault(boolean): void  [API 26+]
+ setForceDarkAllowed(boolean): void  [API 29+]
+ setForeground(Drawable): void  [API 23+]
+ setForegroundGravity(int): void  [API 23+]
+ setForegroundTintBlendMode(BlendMode): void  [API 29+]
+ setForegroundTintList(ColorStateList): void  [API 23+]
+ setForegroundTintMode(PorterDuff.Mode): void  [API 23+]
+ setFrameContentVelocity(float): void  [API 35+]
+ setHandwritingBoundsOffsets(float, float, float, float): void  [API 34+]
+ setHandwritingDelegateFlags(int): void  [API 35+]
+ setHandwritingDelegatorCallback(Runnable): void  [API 34+]
+ setHapticFeedbackEnabled(boolean): void  [API 3+]
+ setHasTransientState(boolean): void  [API 16+]
~ setHorizontalFadingEdgeEnabled(boolean): void  [API 1+]
+ setHorizontalScrollBarEnabled(boolean): void  [API 1+]
+ setHorizontalScrollbarThumbDrawable(Drawable): void  [API 29+]
+ setHorizontalScrollbarTrackDrawable(Drawable): void  [API 29+]
+ setHovered(boolean): void  [API 14+]
+ setId(int): void  [API 1+]
+ setImportantForAccessibility(int): void  [API 16+]
+ setImportantForAutofill(int): void  [API 26+]
+ setImportantForContentCapture(int): void  [API 30+]
+ setIsCredential(boolean): void  [API 34+]
+ setIsHandwritingDelegate(boolean): void  [API 34+]
+ setKeepScreenOn(boolean): void  [API 1+]
+ setKeyboardNavigationCluster(boolean): void  [API 26+]
+ setLabelFor(int): void  [API 17+]
+ setLayerPaint(Paint): void  [API 17+]
~ setLayerType(int, Paint): void  [API 11+]
~ setLayoutDirection(int): void  [API 17+]
+ setLayoutParams(ViewGroup.LayoutParams): void  [API 1+]
+ setLeft(int): void  [API 11+]
+ setLeftTopRightBottom(int, int, int, int): void  [API 29+]
+ setLongClickable(boolean): void  [API 1+]
+ setMeasuredDimension(int, int): void  [API 1+]
+ setMinimumHeight(int): void  [API 1+]
+ setMinimumWidth(int): void  [API 1+]
+ setNestedScrollingEnabled(boolean): void  [API 21+]
+ setNextClusterForwardId(int): void  [API 26+]
+ setNextFocusDownId(int): void  [API 1+]
+ setNextFocusForwardId(int): void  [API 11+]
+ setNextFocusLeftId(int): void  [API 1+]
+ setNextFocusRightId(int): void  [API 1+]
+ setNextFocusUpId(int): void  [API 1+]
+ setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener): void  [API 20+]
+ setOnCapturedPointerListener(View.OnCapturedPointerListener): void  [API 26+]
+ setOnClickListener(View.OnClickListener): void  [API 1+]
+ setOnContextClickListener(View.OnContextClickListener): void  [API 23+]
+ setOnCreateContextMenuListener(View.OnCreateContextMenuListener): void  [API 1+]
~ setOnDragListener(View.OnDragListener): void  [API 11+]
+ setOnFocusChangeListener(View.OnFocusChangeListener): void  [API 1+]
+ setOnGenericMotionListener(View.OnGenericMotionListener): void  [API 12+]
+ setOnHoverListener(View.OnHoverListener): void  [API 14+]
+ setOnKeyListener(View.OnKeyListener): void  [API 1+]
+ setOnLongClickListener(View.OnLongClickListener): void  [API 1+]
+ setOnReceiveContentListener(String[], OnReceiveContentListener): void  [API 31+]
+ setOnScrollChangeListener(View.OnScrollChangeListener): void  [API 23+]
~ setOnSystemUiVisibilityChangeListener(View.OnSystemUiVisibilityChangeListener): void  [API 11+, deprecated in 30]
+ setOnTouchListener(View.OnTouchListener): void  [API 1+]
+ setOutlineAmbientShadowColor(int): void  [API 28+]
~ setOutlineProvider(ViewOutlineProvider): void  [API 21+]
+ setOutlineSpotShadowColor(int): void  [API 28+]
+ setOverScrollMode(int): void  [API 9+]
+ setPadding(int, int, int, int): void  [API 1+]
+ setPaddingRelative(int, int, int, int): void  [API 17+]
+ setPendingCredentialRequest(GetCredentialRequest, OutcomeReceiver): void  [API 35+]
+ setPivotX(float): void  [API 11+]
+ setPivotY(float): void  [API 11+]
+ setPointerIcon(PointerIcon): void  [API 24+]
+ setPreferKeepClear(boolean): void  [API 33+]
+ setPreferKeepClearRects(List): void  [API 33+]
+ setPressed(boolean): void  [API 1+]
+ setRenderEffect(RenderEffect): void  [API 31+]
+ setRequestedFrameRate(float): void  [API 35+]
+ setRevealOnFocusHint(boolean): void  [API 25+]
+ setRight(int): void  [API 11+]
+ setRotation(float): void  [API 11+]
+ setRotationX(float): void  [API 11+]
+ setRotationY(float): void  [API 11+]
+ setSaveEnabled(boolean): void  [API 1+]
+ setSaveFromParentEnabled(boolean): void  [API 11+]
+ setScaleX(float): void  [API 11+]
+ setScaleY(float): void  [API 11+]
+ setScreenReaderFocusable(boolean): void  [API 28+]
~ setScrollBarDefaultDelayBeforeFade(int): void  [API 16+]
~ setScrollBarFadeDuration(int): void  [API 16+]
~ setScrollBarSize(int): void  [API 16+]
+ setScrollBarStyle(int): void  [API 1+]
+ setScrollCaptureCallback(ScrollCaptureCallback): void  [API 31+]
+ setScrollCaptureHint(int): void  [API 31+]
~ setScrollContainer(boolean): void  [API 3+]
+ setScrollIndicators(int): void  [API 23+]
+ setScrollIndicators(int, int): void  [API 23+]
+ setScrollX(int): void  [API 14+]
+ setScrollY(int): void  [API 14+]
~ setScrollbarFadingEnabled(boolean): void  [API 5+]
+ setSelected(boolean): void  [API 1+]
+ setSoundEffectsEnabled(boolean): void  [API 1+]
+ setStateDescription(CharSequence): void  [API 30+]
~ setStateListAnimator(StateListAnimator): void  [API 21+]
+ setSystemGestureExclusionRects(List): void  [API 29+]
~ setSystemUiVisibility(int): void  [API 11+, deprecated in 30]
+ setTag(Object): void  [API 1+]
+ setTag(int, Object): void  [API 4+]
+ setTextAlignment(int): void  [API 17+]
+ setTextDirection(int): void  [API 17+]
+ setTooltipText(CharSequence): void  [API 26+]
+ setTop(int): void  [API 11+]
+ setTouchDelegate(TouchDelegate): void  [API 1+]
~ setTransitionAlpha(float): void  [API 29+]
+ setTransitionName(String): void  [API 21+]
+ setTransitionVisibility(int): void  [API 29+]
+ setTranslationX(float): void  [API 11+]
+ setTranslationY(float): void  [API 11+]
+ setTranslationZ(float): void  [API 21+]
~ setVerticalFadingEdgeEnabled(boolean): void  [API 1+]
+ setVerticalScrollBarEnabled(boolean): void  [API 1+]
+ setVerticalScrollbarPosition(int): void  [API 11+]
+ setVerticalScrollbarThumbDrawable(Drawable): void  [API 29+]
+ setVerticalScrollbarTrackDrawable(Drawable): void  [API 29+]
+ setViewTranslationCallback(ViewTranslationCallback): void  [API 31+]
+ setVisibility(int): void  [API 1+]
~ setWillNotCacheDrawing(boolean): void  [API 1+, deprecated in 28]
+ setWillNotDraw(boolean): void  [API 1+]
+ setWindowInsetsAnimationCallback(WindowInsetsAnimation.Callback): void  [API 30+]
+ setX(float): void  [API 11+]
+ setY(float): void  [API 11+]
+ setZ(float): void  [API 21+]
+ showContextMenu(): boolean  [API 1+]
+ showContextMenu(float, float): boolean  [API 24+]
+ startActionMode(ActionMode.Callback): ActionMode  [API 11+]
+ startActionMode(ActionMode.Callback, int): ActionMode  [API 23+]
+ startAnimation(Animation): void  [API 1+]
+ startDrag(ClipData, View.DragShadowBuilder, Object, int): boolean  [API 11+, deprecated in 24]
~ startDragAndDrop(ClipData, View.DragShadowBuilder, Object, int): boolean  [API 24+]
+ startNestedScroll(int): boolean  [API 21+]
+ stopNestedScroll(): void  [API 21+]
+ toString(): String  [API 1+]
+ transformMatrixToGlobal(Matrix): void  [API 29+]
+ transformMatrixToLocal(Matrix): void  [API 29+]
+ unscheduleDrawable(Drawable): void  [API 1+]
+ unscheduleDrawable(Drawable, Runnable): void  [API 1+]
~ updateDragShadow(View.DragShadowBuilder): void  [API 24+]
+ verifyDrawable(Drawable): boolean  [API 1+]
~ willNotCacheDrawing(): boolean  [API 1+, deprecated in 28]
+ willNotDraw(): boolean  [API 1+]
```

## View.AccessibilityDelegate — framework; API 14+ (5 +, 0 *, 5 ~, 1 -)

```
+ AccessibilityDelegate()  [API 14+]
- addExtraDataToAccessibilityNodeInfo(View, AccessibilityNodeInfo, String, Bundle): void  [API 26+]
~ dispatchPopulateAccessibilityEvent(View, AccessibilityEvent): boolean  [API 14+]
~ getAccessibilityNodeProvider(View): AccessibilityNodeProvider  [API 16+]
+ onInitializeAccessibilityEvent(View, AccessibilityEvent): void  [API 14+]
+ onInitializeAccessibilityNodeInfo(View, AccessibilityNodeInfo): void  [API 14+]
+ onPopulateAccessibilityEvent(View, AccessibilityEvent): void  [API 14+]
+ onRequestSendAccessibilityEvent(ViewGroup, View, AccessibilityEvent): boolean  [API 14+]
~ performAccessibilityAction(View, int, Bundle): boolean  [API 16+]
~ sendAccessibilityEvent(View, int): void  [API 14+]
~ sendAccessibilityEventUnchecked(View, AccessibilityEvent): void  [API 14+]
```

## View.BaseSavedState — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ BaseSavedState(Parcel)  [API 1+]
+ BaseSavedState(Parcel, ClassLoader)  [API 24+]
+ BaseSavedState(Parcelable)  [API 1+]
+ CREATOR [field]  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## View.DragShadowBuilder — framework; API 11+ (5 +, 0 *, 0 ~, 0 -)

```
+ DragShadowBuilder()  [API 11+]
+ DragShadowBuilder(View)  [API 11+]
+ getView(): View  [API 11+]
+ onDrawShadow(Canvas): void  [API 11+]
+ onProvideShadowMetrics(Point, Point): void  [API 11+]
```

## View.MeasureSpec — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ MeasureSpec()  [API 1+]
+ getMode(int): int [static]  [API 1+]
+ getSize(int): int [static]  [API 1+]
+ makeMeasureSpec(int, int): int [static]  [API 1+]
+ toString(int): String [static]  [API 1+]
```

## View.OnApplyWindowInsetsListener — framework; API 20+ (1 +, 0 *, 0 ~, 0 -)

```
+ onApplyWindowInsets(View, WindowInsets): WindowInsets  [API 20+]
```

## View.OnAttachStateChangeListener — framework; API 12+ (2 +, 0 *, 0 ~, 0 -)

```
+ onViewAttachedToWindow(View): void  [API 12+]
+ onViewDetachedFromWindow(View): void  [API 12+]
```

## View.OnCapturedPointerListener — framework; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCapturedPointer(View, MotionEvent): boolean  [API 26+]
```

## View.OnClickListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onClick(View): void  [API 1+]
```

## View.OnContextClickListener — framework; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onContextClick(View): boolean  [API 23+]
```

## View.OnCreateContextMenuListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCreateContextMenu(ContextMenu, View, ContextMenu.ContextMenuInfo): void  [API 1+]
```

## View.OnDragListener — framework; API 11+ (0 +, 0 *, 0 ~, 1 -)

```
- onDrag(View, DragEvent): boolean  [API 11+]
```

## View.OnFocusChangeListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onFocusChange(View, boolean): void  [API 1+]
```

## View.OnGenericMotionListener — framework; API 12+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGenericMotion(View, MotionEvent): boolean  [API 12+]
```

## View.OnHoverListener — framework; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ onHover(View, MotionEvent): boolean  [API 14+]
```

## View.OnKeyListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onKey(View, int, KeyEvent): boolean  [API 1+]
```

## View.OnLayoutChangeListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onLayoutChange(View, int, int, int, int, int, int, int, int): void  [API 11+]
```

## View.OnLongClickListener — framework; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
+ onLongClick(View): boolean  [API 1+]
- onLongClickUseDefaultHapticFeedback(View): boolean  [API 34+]
```

## View.OnScrollChangeListener — framework; API 23+ (1 +, 0 *, 0 ~, 0 -)

```
+ onScrollChange(View, int, int, int, int): void  [API 23+]
```

## View.OnSystemUiVisibilityChangeListener — framework; API 11+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ onSystemUiVisibilityChange(int): void  [API 11+]
```

## View.OnTouchListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTouch(View, MotionEvent): boolean  [API 1+]
```

## View.OnUnhandledKeyEventListener — framework; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onUnhandledKeyEvent(View, KeyEvent): boolean  [API 28+]
```

## ViewAnimationUtils — declaration; API 21+ (0 +, 0 *, 0 ~, 1 -)

```
- createCircularReveal(View, int, int, float, float): Animator [static]  [API 21+]
```

## ViewConfiguration — framework; API 1+ (23 +, 0 *, 25 ~, 0 -)

```
+ ViewConfiguration()  [API 1+, deprecated in 15]
+ get(Context): ViewConfiguration [static]  [API 3+]
~ getAmbiguousGestureMultiplier(): float [static]  [API 29+, deprecated in 30]
~ getDefaultActionModeHideDuration(): long [static]  [API 23+]
~ getDoubleTapTimeout(): int [static]  [API 3+]
~ getEdgeSlop(): int [static]  [API 1+, deprecated in 15]
~ getFadingEdgeLength(): int [static]  [API 1+, deprecated in 15]
~ getGlobalActionKeyTimeout(): long [static]  [API 1+, deprecated in 20]
~ getJumpTapTimeout(): int [static]  [API 1+]
~ getKeyRepeatDelay(): int [static]  [API 12+]
~ getKeyRepeatTimeout(): int [static]  [API 12+]
~ getLongPressTimeout(): int [static]  [API 1+]
+ getMaximumDrawingCacheSize(): int [static]  [API 1+, deprecated in 15]
~ getMaximumFlingVelocity(): int [static]  [API 4+, deprecated in 15]
~ getMinimumFlingVelocity(): int [static]  [API 1+, deprecated in 15]
~ getMultiPressTimeout(): int [static]  [API 31+]
~ getPressedStateDuration(): int [static]  [API 1+]
~ getScaledAmbiguousGestureMultiplier(): float  [API 30+]
+ getScaledDoubleTapSlop(): int  [API 3+]
+ getScaledEdgeSlop(): int  [API 3+]
+ getScaledFadingEdgeLength(): int  [API 3+]
+ getScaledHandwritingGestureLineMargin(): int  [API 34+]
+ getScaledHandwritingSlop(): int  [API 34+]
+ getScaledHorizontalScrollFactor(): float  [API 26+]
+ getScaledHoverSlop(): int  [API 28+]
+ getScaledMaximumDrawingCacheSize(): int  [API 3+]
+ getScaledMaximumFlingVelocity(): int  [API 4+]
+ getScaledMaximumFlingVelocity(int, int, int): int  [API 34+]
+ getScaledMinimumFlingVelocity(): int  [API 3+]
+ getScaledMinimumFlingVelocity(int, int, int): int  [API 34+]
+ getScaledMinimumScalingSpan(): int  [API 29+]
+ getScaledOverflingDistance(): int  [API 9+]
~ getScaledOverscrollDistance(): int  [API 9+]
+ getScaledPagingTouchSlop(): int  [API 8+]
+ getScaledScrollBarSize(): int  [API 3+]
+ getScaledTouchSlop(): int  [API 3+]
+ getScaledVerticalScrollFactor(): float  [API 26+]
+ getScaledWindowTouchSlop(): int  [API 3+]
~ getScrollBarFadeDuration(): int [static]  [API 5+]
~ getScrollBarSize(): int [static]  [API 1+, deprecated in 15]
~ getScrollDefaultDelay(): int [static]  [API 5+]
~ getScrollFriction(): float [static]  [API 1+]
~ getTapTimeout(): int [static]  [API 1+]
~ getTouchSlop(): int [static]  [API 1+, deprecated in 15]
~ getWindowTouchSlop(): int [static]  [API 1+, deprecated in 15]
~ getZoomControlsTimeout(): long [static]  [API 1+]
+ hasPermanentMenuKey(): boolean  [API 14+]
~ shouldShowMenuShortcutsWhenKeyboardPresent(): boolean  [API 28+]
```

## ViewDebug — declaration; API 1+ (0 +, 0 *, 0 ~, 8 -)

```
- ViewDebug()  [API 1+]
- dumpCapturedView(String, Object): void [static]  [API 3+]
- startHierarchyTracing(String, View): void [static]  [API 1+, deprecated in 16]
- startRecyclerTracing(String, View): void [static]  [API 1+, deprecated in 16]
- stopHierarchyTracing(): void [static]  [API 1+, deprecated in 16]
- stopRecyclerTracing(): void [static]  [API 1+, deprecated in 16]
- trace(View, ViewDebug.HierarchyTraceType): void [static]  [API 1+, deprecated in 16]
- trace(View, ViewDebug.RecyclerTraceType, int[]): void [static]  [API 1+, deprecated in 16]
```

## ViewDebug.CapturedViewProperty — declaration; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ retrieveReturn(): boolean  [API 3+]
```

## ViewDebug.ExportedProperty — declaration; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ category(): String  [API 9+]
+ deepExport(): boolean  [API 1+]
+ flagMapping(): ViewDebug.FlagToString[]  [API 4+]
+ formatToHexString(): boolean  [API 21+]
+ hasAdjacentMapping(): boolean  [API 21+]
+ indexMapping(): ViewDebug.IntToString[]  [API 3+]
+ mapping(): ViewDebug.IntToString[]  [API 1+]
+ prefix(): String  [API 1+]
+ resolveId(): boolean  [API 1+]
```

## ViewDebug.FlagToString — declaration; API 4+ (4 +, 0 *, 0 ~, 0 -)

```
+ equals(): int  [API 4+]
+ mask(): int  [API 4+]
+ name(): String  [API 4+]
+ outputIf(): boolean  [API 4+]
```

## ViewDebug.HierarchyTraceType — declaration; API 1+, deprecated in 16 (2 +, 0 *, 0 ~, 0 -)

```
+ BUILD_CACHE [field]  [API 1+]
+ DRAW [field]  [API 1+]
+ INVALIDATE [field]  [API 1+]
+ INVALIDATE_CHILD [field]  [API 1+]
+ INVALIDATE_CHILD_IN_PARENT [field]  [API 1+]
+ ON_LAYOUT [field]  [API 1+]
+ ON_MEASURE [field]  [API 1+]
+ REQUEST_LAYOUT [field]  [API 1+]
+ valueOf(String): ViewDebug.HierarchyTraceType [static]  [API 1+]
+ values(): ViewDebug.HierarchyTraceType[] [static]  [API 1+]
```

## ViewDebug.IntToString — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ from(): int  [API 1+]
+ to(): String  [API 1+]
```

## ViewDebug.RecyclerTraceType — declaration; API 1+, deprecated in 16 (2 +, 0 *, 0 ~, 0 -)

```
+ BIND_VIEW [field]  [API 1+]
+ MOVE_FROM_ACTIVE_TO_SCRAP_HEAP [field]  [API 1+]
+ MOVE_TO_SCRAP_HEAP [field]  [API 1+]
+ NEW_VIEW [field]  [API 1+]
+ RECYCLE_FROM_ACTIVE_HEAP [field]  [API 1+]
+ RECYCLE_FROM_SCRAP_HEAP [field]  [API 1+]
+ valueOf(String): ViewDebug.RecyclerTraceType [static]  [API 1+]
+ values(): ViewDebug.RecyclerTraceType[] [static]  [API 1+]
```

## ViewGroup — framework; API 1+ (167 +, 0 *, 22 ~, 3 -)

```
+ ViewGroup(Context)  [API 1+]
+ ViewGroup(Context, AttributeSet)  [API 1+]
+ ViewGroup(Context, AttributeSet, int)  [API 1+]
+ ViewGroup(Context, AttributeSet, int, int)  [API 21+]
~ addChildrenForAccessibility(ArrayList): void  [API 1+]
~ addExtraDataToAccessibilityNodeInfo(AccessibilityNodeInfo, String, Bundle): void  [API 1+]
+ addFocusables(ArrayList, int, int): void  [API 1+]
+ addKeyboardNavigationClusters(Collection, int): void  [API 1+]
+ addStatesFromChildren(): boolean  [API 1+]
+ addTouchables(ArrayList): void  [API 1+]
+ addView(View): void  [API 1+]
+ addView(View, ViewGroup.LayoutParams): void  [API 1+]
+ addView(View, int): void  [API 1+]
+ addView(View, int, ViewGroup.LayoutParams): void  [API 1+]
+ addView(View, int, int): void  [API 1+]
+ addViewInLayout(View, int, ViewGroup.LayoutParams): boolean  [API 1+]
+ addViewInLayout(View, int, ViewGroup.LayoutParams, boolean): boolean  [API 1+]
+ attachLayoutAnimationParameters(View, ViewGroup.LayoutParams, int, int): void  [API 1+]
+ attachViewToParent(View, int, ViewGroup.LayoutParams): void  [API 1+]
+ bringChildToFront(View): void  [API 1+]
+ canAnimate(): boolean  [API 1+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
+ childDrawableStateChanged(View): void  [API 1+]
~ childHasTransientStateChanged(View, boolean): void  [API 1+]
+ cleanupLayoutState(View): void  [API 1+]
+ clearChildFocus(View): void  [API 1+]
+ clearDisappearingChildren(): void  [API 1+]
+ clearFocus(): void  [API 1+]
+ debug(int): void  [API 1+]
+ detachAllViewsFromParent(): void  [API 1+]
+ detachViewFromParent(View): void  [API 1+]
+ detachViewFromParent(int): void  [API 1+]
+ detachViewsFromParent(int, int): void  [API 1+]
+ dispatchApplyWindowInsets(WindowInsets): WindowInsets  [API 1+]
+ dispatchCapturedPointerEvent(MotionEvent): boolean  [API 1+]
+ dispatchConfigurationChanged(Configuration): void  [API 1+]
+ dispatchCreateViewTranslationRequest(Map, int[], TranslationCapability, List): void  [API 1+]
+ dispatchDisplayHint(int): void  [API 1+]
+ dispatchDragEvent(DragEvent): boolean  [API 1+]
+ dispatchDraw(Canvas): void  [API 1+]
~ dispatchDrawableHotspotChanged(float, float): void  [API 1+]
+ dispatchFinishTemporaryDetach(): void  [API 1+]
+ dispatchFreezeSelfOnly(SparseArray): void  [API 1+]
+ dispatchGenericFocusedEvent(MotionEvent): boolean  [API 1+]
+ dispatchGenericPointerEvent(MotionEvent): boolean  [API 1+]
+ dispatchHoverEvent(MotionEvent): boolean  [API 1+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 1+]
+ dispatchKeyEventPreIme(KeyEvent): boolean  [API 1+]
+ dispatchKeyShortcutEvent(KeyEvent): boolean  [API 1+]
+ dispatchPointerCaptureChanged(boolean): void  [API 1+]
+ dispatchProvideAutofillStructure(ViewStructure, int): void  [API 1+]
+ dispatchProvideStructure(ViewStructure): void  [API 1+]
+ dispatchRestoreInstanceState(SparseArray): void  [API 1+]
+ dispatchSaveInstanceState(SparseArray): void  [API 1+]
+ dispatchScrollCaptureSearch(Rect, Point, Consumer): void  [API 1+]
+ dispatchSetActivated(boolean): void  [API 1+]
+ dispatchSetPressed(boolean): void  [API 1+]
+ dispatchSetSelected(boolean): void  [API 1+]
+ dispatchStartTemporaryDetach(): void  [API 1+]
~ dispatchSystemUiVisibilityChanged(int): void  [API 1+]
+ dispatchThawSelfOnly(SparseArray): void  [API 1+]
+ dispatchTouchEvent(MotionEvent): boolean  [API 1+]
+ dispatchTrackballEvent(MotionEvent): boolean  [API 1+]
+ dispatchUnhandledMove(View, int): boolean  [API 1+]
+ dispatchVisibilityChanged(View, int): void  [API 1+]
+ dispatchWindowFocusChanged(boolean): void  [API 1+]
+ dispatchWindowInsetsAnimationEnd(WindowInsetsAnimation): void  [API 1+]
+ dispatchWindowInsetsAnimationPrepare(WindowInsetsAnimation): void  [API 1+]
+ dispatchWindowInsetsAnimationProgress(WindowInsets, List): WindowInsets  [API 1+]
+ dispatchWindowInsetsAnimationStart(WindowInsetsAnimation, WindowInsetsAnimation.Bounds): WindowInsetsAnimation.Bounds  [API 1+]
+ dispatchWindowSystemUiVisiblityChanged(int): void  [API 1+]
+ dispatchWindowVisibilityChanged(int): void  [API 1+]
+ drawChild(Canvas, View, long): boolean  [API 1+]
+ drawableStateChanged(): void  [API 1+]
~ endViewTransition(View): void  [API 11+]
+ findFocus(): View  [API 1+]
+ findOnBackInvokedDispatcherForChild(View, View): OnBackInvokedDispatcher  [API 1+]
~ findViewsWithText(ArrayList, CharSequence, int): void  [API 1+]
+ focusSearch(View, int): View  [API 1+]
+ focusableViewAvailable(View): void  [API 1+]
+ gatherTransparentRegion(Region): boolean  [API 1+]
+ generateDefaultLayoutParams(): ViewGroup.LayoutParams  [API 1+]
+ generateLayoutParams(AttributeSet): ViewGroup.LayoutParams  [API 1+]
+ generateLayoutParams(ViewGroup.LayoutParams): ViewGroup.LayoutParams  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getChildAt(int): View  [API 1+]
+ getChildCount(): int  [API 1+]
+ getChildDrawingOrder(int): int  [API 29+]
+ getChildDrawingOrder(int, int): int  [API 1+]
+ getChildMeasureSpec(int, int, int): int [static]  [API 1+]
~ getChildStaticTransformation(View, Transformation): boolean  [API 1+]
+ getChildVisibleRect(View, Rect, Point): boolean  [API 1+]
+ getClipChildren(): boolean  [API 18+]
+ getClipToPadding(): boolean  [API 21+]
+ getDescendantFocusability(): int  [API 1+]
+ getFocusedChild(): View  [API 1+]
+ getLayoutAnimation(): LayoutAnimationController  [API 1+]
+ getLayoutAnimationListener(): Animation.AnimationListener  [API 1+]
+ getLayoutMode(): int  [API 18+]
+ getLayoutTransition(): LayoutTransition  [API 11+]
+ getNestedScrollAxes(): int  [API 21+]
- getOverlay(): ViewGroupOverlay  [API 18+]
~ getPersistentDrawingCache(): int  [API 1+, deprecated in 28]
+ getTouchscreenBlocksFocus(): boolean  [API 21+]
+ hasFocus(): boolean  [API 1+]
~ hasTransientState(): boolean  [API 1+]
+ indexOfChild(View): int  [API 1+]
+ invalidateChild(View, Rect): void  [API 1+]
+ invalidateChildInParent(int[], Rect): ViewParent  [API 1+]
~ isAlwaysDrawnWithCacheEnabled(): boolean  [API 1+, deprecated in 23]
~ isAnimationCacheEnabled(): boolean  [API 1+, deprecated in 23]
+ isChildrenDrawingOrderEnabled(): boolean  [API 7+]
+ isChildrenDrawnWithCacheEnabled(): boolean  [API 1+, deprecated in 23]
+ isLayoutSuppressed(): boolean  [API 29+]
+ isMotionEventSplittingEnabled(): boolean  [API 11+]
+ isTransitionGroup(): boolean  [API 21+]
~ jumpDrawablesToCurrentState(): void  [API 1+]
+ layout(int, int, int, int): void  [API 1+]
+ measureChild(View, int, int): void  [API 1+]
+ measureChildWithMargins(View, int, int, int, int): void  [API 1+]
+ measureChildren(int, int): void  [API 1+]
~ notifySubtreeAccessibilityStateChanged(View, View, int): void  [API 1+]
+ offsetDescendantRectToMyCoords(View, Rect): void  [API 1+]
+ offsetRectIntoDescendantCoords(View, Rect): void  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onCreateDrawableState(int): int[]  [API 1+]
+ onDescendantInvalidated(View, View): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onInterceptHoverEvent(MotionEvent): boolean  [API 14+]
+ onInterceptTouchEvent(MotionEvent): boolean  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onNestedFling(View, float, float, boolean): boolean  [API 1+]
+ onNestedPreFling(View, float, float): boolean  [API 1+]
+ onNestedPrePerformAccessibilityAction(View, int, Bundle): boolean  [API 1+]
+ onNestedPreScroll(View, int, int, int[]): void  [API 1+]
+ onNestedScroll(View, int, int, int, int): void  [API 1+]
+ onNestedScrollAccepted(View, View, int): void  [API 1+]
+ onRequestFocusInDescendants(int, Rect): boolean  [API 1+]
+ onRequestSendAccessibilityEvent(View, AccessibilityEvent): boolean  [API 14+]
+ onResolvePointerIcon(MotionEvent, int): PointerIcon  [API 1+]
+ onStartNestedScroll(View, View, int): boolean  [API 1+]
+ onStopNestedScroll(View): void  [API 1+]
+ onViewAdded(View): void  [API 23+]
+ onViewRemoved(View): void  [API 23+]
+ recomputeViewAttributes(View): void  [API 1+]
+ removeAllViews(): void  [API 1+]
+ removeAllViewsInLayout(): void  [API 1+]
+ removeDetachedView(View, boolean): void  [API 1+]
+ removeView(View): void  [API 1+]
+ removeViewAt(int): void  [API 1+]
+ removeViewInLayout(View): void  [API 1+]
+ removeViews(int, int): void  [API 1+]
+ removeViewsInLayout(int, int): void  [API 1+]
+ requestChildFocus(View, View): void  [API 1+]
~ requestChildRectangleOnScreen(View, Rect, boolean): boolean  [API 1+]
+ requestDisallowInterceptTouchEvent(boolean): void  [API 1+]
+ requestFocus(int, Rect): boolean  [API 1+]
+ requestSendAccessibilityEvent(View, AccessibilityEvent): boolean  [API 1+]
~ requestTransparentRegion(View): void  [API 1+]
- resetResolvedLayoutDirection(): void  [API 14-15, removed in 16]
- resetResolvedTextDirection(): void  [API 14-15, removed in 16]
+ restoreDefaultFocus(): boolean  [API 1+]
+ scheduleLayoutAnimation(): void  [API 1+]
+ setAddStatesFromChildren(boolean): void  [API 1+]
~ setAlwaysDrawnWithCacheEnabled(boolean): void  [API 1+, deprecated in 23]
~ setAnimationCacheEnabled(boolean): void  [API 1+, deprecated in 23]
~ setChildrenDrawingCacheEnabled(boolean): void  [API 1+, deprecated in 28]
+ setChildrenDrawingOrderEnabled(boolean): void  [API 7+]
+ setChildrenDrawnWithCacheEnabled(boolean): void  [API 1+, deprecated in 23]
+ setClipChildren(boolean): void  [API 1+]
+ setClipToPadding(boolean): void  [API 1+]
+ setDescendantFocusability(int): void  [API 1+]
+ setLayoutAnimation(LayoutAnimationController): void  [API 1+]
+ setLayoutAnimationListener(Animation.AnimationListener): void  [API 1+]
+ setLayoutMode(int): void  [API 18+]
+ setLayoutTransition(LayoutTransition): void  [API 11+]
+ setMotionEventSplittingEnabled(boolean): void  [API 11+]
+ setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener): void  [API 1+]
~ setPersistentDrawingCache(int): void  [API 1+, deprecated in 28]
+ setStaticTransformationsEnabled(boolean): void  [API 3+]
+ setTouchscreenBlocksFocus(boolean): void  [API 21+]
+ setTransitionGroup(boolean): void  [API 21+]
+ setWindowInsetsAnimationCallback(WindowInsetsAnimation.Callback): void  [API 1+]
~ shouldDelayChildPressedState(): boolean  [API 14+]
+ showContextMenuForChild(View): boolean  [API 1+]
+ showContextMenuForChild(View, float, float): boolean  [API 1+]
+ startActionModeForChild(View, ActionMode.Callback): ActionMode  [API 1+]
+ startActionModeForChild(View, ActionMode.Callback, int): ActionMode  [API 1+]
+ startLayoutAnimation(): void  [API 1+]
~ startViewTransition(View): void  [API 11+]
+ suppressLayout(boolean): void  [API 29+]
+ updateViewLayout(View, ViewGroup.LayoutParams): void  [API 1+]
```

## ViewGroup.LayoutParams — framework; API 1+ (4 +, 0 *, 1 ~, 0 -)

```
+ LayoutParams(Context, AttributeSet)  [API 1+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 1+]
+ LayoutParams(int, int)  [API 1+]
+ height [field]  [API 1+]
+ layoutAnimationParameters [field]  [API 1+]
~ resolveLayoutDirection(int): void  [API 17+]
+ setBaseAttributes(TypedArray, int, int): void  [API 1+]
+ width [field]  [API 1+]
```

## ViewGroup.MarginLayoutParams — framework; API 1+ (9 +, 0 *, 1 ~, 3 -)

```
+ MarginLayoutParams(Context, AttributeSet)  [API 1+]
+ MarginLayoutParams(ViewGroup.LayoutParams)  [API 1+]
+ MarginLayoutParams(ViewGroup.MarginLayoutParams)  [API 1+]
+ MarginLayoutParams(int, int)  [API 1+]
+ bottomMargin [field]  [API 1+]
- getLayoutDirection(): int  [API 17+]
+ getMarginEnd(): int  [API 17+]
+ getMarginStart(): int  [API 17+]
- isMarginRelative(): boolean  [API 17+]
+ leftMargin [field]  [API 1+]
~ resolveLayoutDirection(int): void  [API 1+]
+ rightMargin [field]  [API 1+]
- setLayoutDirection(int): void  [API 17+]
+ setMarginEnd(int): void  [API 17+]
+ setMarginStart(int): void  [API 17+]
+ setMargins(int, int, int, int): void  [API 1+]
+ topMargin [field]  [API 1+]
```

## ViewGroup.OnHierarchyChangeListener — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ onChildViewAdded(View, View): void  [API 1+]
+ onChildViewRemoved(View, View): void  [API 1+]
```

## ViewGroupOverlay — declaration; API 18+ (0 +, 0 *, 0 ~, 2 -)

```
- add(View): void  [API 18+]
- remove(View): void  [API 18+]
```

## ViewManager — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ addView(View, ViewGroup.LayoutParams): void  [API 1+]
+ removeView(View): void  [API 1+]
+ updateViewLayout(View, ViewGroup.LayoutParams): void  [API 1+]
```

## ViewOutlineProvider — framework; API 21+ (2 +, 0 *, 0 ~, 0 -)

```
+ BACKGROUND [field]  [API 21+]
+ BOUNDS [field]  [API 21+]
+ PADDED_BOUNDS [field]  [API 21+]
+ ViewOutlineProvider()  [API 21+]
+ getOutline(View, Outline): void  [API 21+]
```

## ViewOverlay — declaration; API 18+ (0 +, 0 *, 0 ~, 3 -)

```
- add(Drawable): void  [API 18+]
- clear(): void  [API 18+]
- remove(Drawable): void  [API 18+]
```

## ViewParent — framework; API 1+ (28 +, 0 *, 18 ~, 0 -)

```
+ bringChildToFront(View): void  [API 1+]
~ canResolveLayoutDirection(): boolean  [API 19+]
~ canResolveTextAlignment(): boolean  [API 19+]
~ canResolveTextDirection(): boolean  [API 19+]
+ childDrawableStateChanged(View): void  [API 1+]
~ childHasTransientStateChanged(View, boolean): void  [API 19+]
+ clearChildFocus(View): void  [API 1+]
~ createContextMenu(ContextMenu): void  [API 1+]
~ findOnBackInvokedDispatcherForChild(View, View): OnBackInvokedDispatcher  [API 33+]
+ focusSearch(View, int): View  [API 1+]
+ focusableViewAvailable(View): void  [API 1+]
+ getChildVisibleRect(View, Rect, Point): boolean  [API 1+]
~ getLayoutDirection(): int  [API 19+]
+ getParent(): ViewParent  [API 1+]
~ getParentForAccessibility(): ViewParent  [API 16+]
~ getTextAlignment(): int  [API 19+]
~ getTextDirection(): int  [API 19+]
+ invalidateChild(View, Rect): void  [API 1+, deprecated in 26]
+ invalidateChildInParent(int[], Rect): ViewParent  [API 1+, deprecated in 26]
~ isLayoutDirectionResolved(): boolean  [API 19+]
+ isLayoutRequested(): boolean  [API 1+]
~ isTextAlignmentResolved(): boolean  [API 19+]
~ isTextDirectionResolved(): boolean  [API 19+]
~ keyboardNavigationClusterSearch(View, int): View  [API 26+]
~ notifySubtreeAccessibilityStateChanged(View, View, int): void  [API 19+]
+ onDescendantInvalidated(View, View): void  [API 26+]
+ onNestedFling(View, float, float, boolean): boolean  [API 21+]
+ onNestedPreFling(View, float, float): boolean  [API 21+]
+ onNestedPrePerformAccessibilityAction(View, int, Bundle): boolean  [API 22+]
+ onNestedPreScroll(View, int, int, int[]): void  [API 21+]
+ onNestedScroll(View, int, int, int, int): void  [API 21+]
+ onNestedScrollAccepted(View, View, int): void  [API 21+]
+ onStartNestedScroll(View, View, int): boolean  [API 21+]
+ onStopNestedScroll(View): void  [API 21+]
+ recomputeViewAttributes(View): void  [API 1+]
+ requestChildFocus(View, View): void  [API 1+]
+ requestChildRectangleOnScreen(View, Rect, boolean): boolean  [API 3+]
+ requestDisallowInterceptTouchEvent(boolean): void  [API 1+]
~ requestFitSystemWindows(): void  [API 16+]
+ requestLayout(): void  [API 1+]
~ requestSendAccessibilityEvent(View, AccessibilityEvent): boolean  [API 14+]
+ requestTransparentRegion(View): void  [API 1+]
+ showContextMenuForChild(View): boolean  [API 1+]
+ showContextMenuForChild(View, float, float): boolean  [API 24+]
+ startActionModeForChild(View, ActionMode.Callback): ActionMode  [API 11+]
~ startActionModeForChild(View, ActionMode.Callback, int): ActionMode  [API 23+]
```

## ViewPropertyAnimator — framework; API 12+ (36 +, 0 *, 1 ~, 0 -)

```
+ alpha(float): ViewPropertyAnimator  [API 12+]
+ alphaBy(float): ViewPropertyAnimator  [API 12+]
+ cancel(): void  [API 14+]
+ getDuration(): long  [API 14+]
+ getInterpolator(): TimeInterpolator  [API 18+]
+ getStartDelay(): long  [API 14+]
+ rotation(float): ViewPropertyAnimator  [API 12+]
+ rotationBy(float): ViewPropertyAnimator  [API 12+]
+ rotationX(float): ViewPropertyAnimator  [API 12+]
+ rotationXBy(float): ViewPropertyAnimator  [API 12+]
+ rotationY(float): ViewPropertyAnimator  [API 12+]
+ rotationYBy(float): ViewPropertyAnimator  [API 12+]
+ scaleX(float): ViewPropertyAnimator  [API 12+]
+ scaleXBy(float): ViewPropertyAnimator  [API 12+]
+ scaleY(float): ViewPropertyAnimator  [API 12+]
+ scaleYBy(float): ViewPropertyAnimator  [API 12+]
+ setDuration(long): ViewPropertyAnimator  [API 12+]
+ setInterpolator(TimeInterpolator): ViewPropertyAnimator  [API 12+]
+ setListener(Animator.AnimatorListener): ViewPropertyAnimator  [API 12+]
+ setStartDelay(long): ViewPropertyAnimator  [API 14+]
+ setUpdateListener(ValueAnimator.AnimatorUpdateListener): ViewPropertyAnimator  [API 19+]
+ start(): void  [API 14+]
+ translationX(float): ViewPropertyAnimator  [API 12+]
+ translationXBy(float): ViewPropertyAnimator  [API 12+]
+ translationY(float): ViewPropertyAnimator  [API 12+]
+ translationYBy(float): ViewPropertyAnimator  [API 12+]
+ translationZ(float): ViewPropertyAnimator  [API 21+]
+ translationZBy(float): ViewPropertyAnimator  [API 21+]
+ withEndAction(Runnable): ViewPropertyAnimator  [API 16+]
~ withLayer(): ViewPropertyAnimator  [API 16+]
+ withStartAction(Runnable): ViewPropertyAnimator  [API 16+]
+ x(float): ViewPropertyAnimator  [API 12+]
+ xBy(float): ViewPropertyAnimator  [API 12+]
+ y(float): ViewPropertyAnimator  [API 12+]
+ yBy(float): ViewPropertyAnimator  [API 12+]
+ z(float): ViewPropertyAnimator  [API 21+]
+ zBy(float): ViewPropertyAnimator  [API 21+]
```

## ViewStructure — declaration; API 23+ (50 +, 0 *, 0 ~, 12 -)

```
- ViewStructure()  [API 23+]
+ addChildCount(int): int  [API 23+]
+ asyncCommit(): void  [API 23+]
+ asyncNewChild(int): ViewStructure  [API 23+]
- clearCredentialManagerRequest(): void  [API 35+]
+ getAutofillId(): AutofillId  [API 26+]
+ getChildCount(): int  [API 23+]
+ getExtras(): Bundle  [API 23+]
+ getHint(): CharSequence  [API 23+]
- getPendingCredentialCallback(): OutcomeReceiver  [API 35+]
- getPendingCredentialRequest(): GetCredentialRequest  [API 35+]
+ getText(): CharSequence  [API 23+]
+ getTextSelectionEnd(): int  [API 23+]
+ getTextSelectionStart(): int  [API 23+]
+ hasExtras(): boolean  [API 23+]
+ newChild(int): ViewStructure  [API 23+]
+ newHtmlInfoBuilder(String): ViewStructure.HtmlInfo.Builder  [API 26+]
+ setAccessibilityFocused(boolean): void  [API 23+]
+ setActivated(boolean): void  [API 23+]
+ setAlpha(float): void  [API 23+]
+ setAutofillHints(String[]): void  [API 26+]
+ setAutofillId(AutofillId): void  [API 26+]
+ setAutofillId(AutofillId, int): void  [API 26+]
+ setAutofillOptions(CharSequence[]): void  [API 26+]
+ setAutofillType(int): void  [API 26+]
+ setAutofillValue(AutofillValue): void  [API 26+]
+ setCheckable(boolean): void  [API 23+]
+ setChecked(boolean): void  [API 23+]
+ setChildCount(int): void  [API 23+]
+ setClassName(String): void  [API 23+]
+ setClickable(boolean): void  [API 23+]
+ setContentDescription(CharSequence): void  [API 23+]
+ setContextClickable(boolean): void  [API 23+]
+ setDataIsSensitive(boolean): void  [API 26+]
+ setDimens(int, int, int, int, int, int): void  [API 23+]
+ setElevation(float): void  [API 23+]
+ setEnabled(boolean): void  [API 23+]
+ setFocusable(boolean): void  [API 23+]
+ setFocused(boolean): void  [API 23+]
+ setHint(CharSequence): void  [API 23+]
- setHintIdEntry(String): void  [API 30+]
+ setHtmlInfo(ViewStructure.HtmlInfo): void  [API 26+]
+ setId(int, String, String, String): void  [API 23+]
- setImportantForAutofill(int): void  [API 28+]
+ setInputType(int): void  [API 26+]
+ setLocaleList(LocaleList): void  [API 26+]
+ setLongClickable(boolean): void  [API 23+]
- setMaxTextEms(int): void  [API 28+]
- setMaxTextLength(int): void  [API 28+]
- setMinTextEms(int): void  [API 28+]
+ setOpaque(boolean): void  [API 26+]
- setPendingCredentialRequest(GetCredentialRequest, OutcomeReceiver): void  [API 35+]
- setReceiveContentMimeTypes(String[]): void  [API 31+]
+ setSelected(boolean): void  [API 23+]
+ setText(CharSequence): void  [API 23+]
+ setText(CharSequence, int, int): void  [API 23+]
- setTextIdEntry(String): void  [API 28+]
+ setTextLines(int[], int[]): void  [API 23+]
+ setTextStyle(float, int, int, int): void  [API 23+]
+ setTransformation(Matrix): void  [API 23+]
+ setVisibility(int): void  [API 23+]
+ setWebDomain(String): void  [API 26+]
```

## ViewStructure.HtmlInfo — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ HtmlInfo()  [API 26+]
+ getAttributes(): List  [API 26+]
+ getTag(): String  [API 26+]
```

## ViewStructure.HtmlInfo.Builder — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 26+]
+ addAttribute(String, String): ViewStructure.HtmlInfo.Builder  [API 26+]
+ build(): ViewStructure.HtmlInfo  [API 26+]
```

## ViewStub — framework; API 1+ (14 +, 0 *, 2 ~, 1 -)

```
+ ViewStub(Context)  [API 1+]
+ ViewStub(Context, AttributeSet)  [API 1+]
+ ViewStub(Context, AttributeSet, int)  [API 1+]
- ViewStub(Context, AttributeSet, int, int)  [API 21+]
+ ViewStub(Context, int)  [API 1+]
~ dispatchDraw(Canvas): void  [API 1+]
~ draw(Canvas): void  [API 1+]
+ getInflatedId(): int  [API 1+]
+ getLayoutInflater(): LayoutInflater  [API 16+]
+ getLayoutResource(): int  [API 1+]
+ inflate(): View  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ setInflatedId(int): void  [API 1+]
+ setLayoutInflater(LayoutInflater): void  [API 16+]
+ setLayoutResource(int): void  [API 1+]
+ setOnInflateListener(ViewStub.OnInflateListener): void  [API 1+]
+ setVisibility(int): void  [API 1+]
```

## ViewStub.OnInflateListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onInflate(ViewStub, View): void  [API 1+]
```

## ViewTreeObserver — framework; API 1+ (19 +, 0 *, 0 ~, 8 -)

```
+ addOnDrawListener(ViewTreeObserver.OnDrawListener): void  [API 16+]
+ addOnGlobalFocusChangeListener(ViewTreeObserver.OnGlobalFocusChangeListener): void  [API 1+]
+ addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener): void  [API 1+]
+ addOnPreDrawListener(ViewTreeObserver.OnPreDrawListener): void  [API 1+]
+ addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener): void  [API 3+]
- addOnSystemGestureExclusionRectsChangedListener(Consumer): void  [API 29+]
+ addOnTouchModeChangeListener(ViewTreeObserver.OnTouchModeChangeListener): void  [API 1+]
- addOnWindowAttachListener(ViewTreeObserver.OnWindowAttachListener): void  [API 18+]
+ addOnWindowFocusChangeListener(ViewTreeObserver.OnWindowFocusChangeListener): void  [API 18+]
- addOnWindowVisibilityChangeListener(ViewTreeObserver.OnWindowVisibilityChangeListener): void  [API 34+]
+ dispatchOnDraw(): void  [API 16+]
+ dispatchOnGlobalLayout(): void  [API 1+]
+ dispatchOnPreDraw(): boolean  [API 1+]
+ isAlive(): boolean  [API 1+]
- registerFrameCommitCallback(Runnable): void  [API 29+]
+ removeGlobalOnLayoutListener(ViewTreeObserver.OnGlobalLayoutListener): void  [API 1+, deprecated in 16]
+ removeOnDrawListener(ViewTreeObserver.OnDrawListener): void  [API 16+]
+ removeOnGlobalFocusChangeListener(ViewTreeObserver.OnGlobalFocusChangeListener): void  [API 1+]
+ removeOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener): void  [API 16+]
+ removeOnPreDrawListener(ViewTreeObserver.OnPreDrawListener): void  [API 1+]
+ removeOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener): void  [API 3+]
- removeOnSystemGestureExclusionRectsChangedListener(Consumer): void  [API 29+]
+ removeOnTouchModeChangeListener(ViewTreeObserver.OnTouchModeChangeListener): void  [API 1+]
- removeOnWindowAttachListener(ViewTreeObserver.OnWindowAttachListener): void  [API 18+]
+ removeOnWindowFocusChangeListener(ViewTreeObserver.OnWindowFocusChangeListener): void  [API 18+]
- removeOnWindowVisibilityChangeListener(ViewTreeObserver.OnWindowVisibilityChangeListener): void  [API 34+]
- unregisterFrameCommitCallback(Runnable): boolean  [API 29+]
```

## ViewTreeObserver.OnDrawListener — framework; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDraw(): void  [API 16+]
```

## ViewTreeObserver.OnGlobalFocusChangeListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGlobalFocusChanged(View, View): void  [API 1+]
```

## ViewTreeObserver.OnGlobalLayoutListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGlobalLayout(): void  [API 1+]
```

## ViewTreeObserver.OnPreDrawListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPreDraw(): boolean  [API 1+]
```

## ViewTreeObserver.OnScrollChangedListener — framework; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ onScrollChanged(): void  [API 3+]
```

## ViewTreeObserver.OnTouchModeChangeListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTouchModeChanged(boolean): void  [API 1+]
```

## ViewTreeObserver.OnWindowAttachListener — framework; API 18+ (2 +, 0 *, 0 ~, 0 -)

```
+ onWindowAttached(): void  [API 18+]
+ onWindowDetached(): void  [API 18+]
```

## ViewTreeObserver.OnWindowFocusChangeListener — framework; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ onWindowFocusChanged(boolean): void  [API 18+]
```

## ViewTreeObserver.OnWindowVisibilityChangeListener — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onWindowVisibilityChanged(int): void  [API 34+]
```

## Window — framework; API 1+ (118 +, 0 *, 33 ~, 0 -)

```
+ Window(Context)  [API 1+]
+ addContentView(View, ViewGroup.LayoutParams): void  [API 1+]
+ addFlags(int): void  [API 1+]
+ addOnFrameMetricsAvailableListener(Window.OnFrameMetricsAvailableListener, Handler): void  [API 24+]
+ clearFlags(int): void  [API 1+]
~ closeAllPanels(): void  [API 1+]
~ closePanel(int): void  [API 1+]
+ findViewById(int): View  [API 1+]
+ getAllowEnterTransitionOverlap(): boolean  [API 21+]
+ getAllowReturnTransitionOverlap(): boolean  [API 21+]
+ getAttributes(): WindowManager.LayoutParams  [API 1+]
+ getCallback(): Window.Callback  [API 1+]
+ getColorMode(): int  [API 26+]
+ getContainer(): Window  [API 1+]
~ getContentScene(): Scene  [API 21+]
+ getContext(): Context  [API 1+]
+ getCurrentFocus(): View  [API 1+]
+ getDecorView(): View  [API 1+]
~ getDefaultFeatures(Context): int [static]  [API 22+]
+ getDesiredHdrHeadroom(): float  [API 35+]
+ getEnterTransition(): Transition  [API 21+]
+ getExitTransition(): Transition  [API 21+]
+ getFeatures(): int  [API 1+]
~ getForcedWindowFlags(): int  [API 1+]
+ getFrameRateBoostOnTouchEnabled(): boolean  [API 35+]
~ getInsetsController(): WindowInsetsController  [API 30+]
+ getLayoutInflater(): LayoutInflater  [API 1+]
+ getLocalFeatures(): int  [API 1+]
+ getMediaController(): MediaController  [API 21+]
+ getNavigationBarColor(): int  [API 21+, deprecated in 35]
+ getNavigationBarDividerColor(): int  [API 28+, deprecated in 35]
+ getOnBackInvokedDispatcher(): OnBackInvokedDispatcher  [API 33+]
+ getReenterTransition(): Transition  [API 21+]
+ getReturnTransition(): Transition  [API 21+]
~ getRootSurfaceControl(): AttachedSurfaceControl  [API 31+]
+ getSharedElementEnterTransition(): Transition  [API 21+]
+ getSharedElementExitTransition(): Transition  [API 21+]
+ getSharedElementReenterTransition(): Transition  [API 21+]
+ getSharedElementReturnTransition(): Transition  [API 21+]
+ getSharedElementsUseOverlay(): boolean  [API 21+]
+ getStatusBarColor(): int  [API 21+, deprecated in 35]
+ getSystemGestureExclusionRects(): List  [API 29+]
+ getTransitionBackgroundFadeDuration(): long  [API 21+]
+ getTransitionManager(): TransitionManager  [API 21+]
+ getVolumeControlStream(): int  [API 1+]
+ getWindowManager(): WindowManager  [API 1+]
+ getWindowStyle(): TypedArray  [API 1+]
+ hasChildren(): boolean  [API 1+]
+ hasFeature(int): boolean  [API 11+]
+ hasSoftInputMode(): boolean  [API 3+]
+ injectInputEvent(InputEvent): void  [API 19+]
+ invalidatePanelMenu(int): void  [API 11+]
~ isActive(): boolean  [API 1+]
+ isFloating(): boolean  [API 1+]
+ isFrameRatePowerSavingsBalanced(): boolean  [API 35+]
+ isNavigationBarContrastEnforced(): boolean  [API 29+]
+ isShortcutKey(int, KeyEvent): boolean  [API 1+]
+ isStatusBarContrastEnforced(): boolean  [API 29+, deprecated in 35]
~ isWideColorGamut(): boolean  [API 27+]
~ makeActive(): void  [API 1+]
+ onActive(): void  [API 1+]
+ onConfigurationChanged(Configuration): void  [API 1+]
~ openPanel(int, KeyEvent): void  [API 1+]
+ peekDecorView(): View  [API 1+]
~ performContextMenuIdentifierAction(int, int): boolean  [API 1+]
+ performPanelIdentifierAction(int, int, int): boolean  [API 1+]
~ performPanelShortcut(int, int, KeyEvent, int): boolean  [API 1+]
~ registerScrollCaptureCallback(ScrollCaptureCallback): void  [API 31+]
~ removeOnFrameMetricsAvailableListener(Window.OnFrameMetricsAvailableListener): void  [API 24+]
+ requestFeature(int): boolean  [API 1+]
+ requireViewById(int): View  [API 28+]
+ restoreHierarchyState(Bundle): void  [API 1+]
+ saveHierarchyState(): Bundle  [API 1+]
+ setAllowEnterTransitionOverlap(boolean): void  [API 21+]
+ setAllowReturnTransitionOverlap(boolean): void  [API 21+]
+ setAttributes(WindowManager.LayoutParams): void  [API 1+]
~ setBackgroundBlurRadius(int): void  [API 31+]
+ setBackgroundDrawable(Drawable): void  [API 1+]
+ setBackgroundDrawableResource(int): void  [API 1+]
+ setCallback(Window.Callback): void  [API 1+]
+ setChildDrawable(int, Drawable): void  [API 1+]
+ setChildInt(int, int): void  [API 1+]
+ setClipToOutline(boolean): void  [API 22+]
+ setColorMode(int): void  [API 26+]
+ setContainer(Window): void  [API 1+]
+ setContentView(View): void  [API 1+]
+ setContentView(View, ViewGroup.LayoutParams): void  [API 1+]
+ setContentView(int): void  [API 1+]
~ setDecorCaptionShade(int): void  [API 24+]
+ setDecorFitsSystemWindows(boolean): void  [API 30+, deprecated in 35]
+ setDefaultWindowFormat(int): void  [API 1+]
+ setDesiredHdrHeadroom(float): void  [API 35+]
+ setDimAmount(float): void  [API 14+]
+ setElevation(float): void  [API 22+]
+ setEnterTransition(Transition): void  [API 21+]
+ setExitTransition(Transition): void  [API 21+]
+ setFeatureDrawable(int, Drawable): void  [API 1+]
+ setFeatureDrawableAlpha(int, int): void  [API 1+]
~ setFeatureDrawableResource(int, int): void  [API 1+]
+ setFeatureDrawableUri(int, Uri): void  [API 1+]
~ setFeatureInt(int, int): void  [API 1+]
+ setFlags(int, int): void  [API 1+]
+ setFormat(int): void  [API 1+]
+ setFrameRateBoostOnTouchEnabled(boolean): void  [API 35+]
+ setFrameRatePowerSavingsBalanced(boolean): void  [API 35+]
+ setGravity(int): void  [API 1+]
~ setHideOverlayWindows(boolean): void  [API 31+]
+ setIcon(int): void  [API 19+]
+ setLayout(int, int): void  [API 1+]
~ setLocalFocus(boolean, boolean): void  [API 19+]
+ setLogo(int): void  [API 19+]
+ setMediaController(MediaController): void  [API 21+]
+ setNavigationBarColor(int): void  [API 21+, deprecated in 35]
+ setNavigationBarContrastEnforced(boolean): void  [API 29+]
+ setNavigationBarDividerColor(int): void  [API 28+, deprecated in 35]
~ setPreferMinimalPostProcessing(boolean): void  [API 30+]
+ setReenterTransition(Transition): void  [API 21+]
~ setResizingCaptionDrawable(Drawable): void  [API 24+]
~ setRestrictedCaptionAreaListener(Window.OnRestrictedCaptionAreaChangedListener): void  [API 24+]
+ setReturnTransition(Transition): void  [API 21+]
+ setSharedElementEnterTransition(Transition): void  [API 21+]
+ setSharedElementExitTransition(Transition): void  [API 21+]
+ setSharedElementReenterTransition(Transition): void  [API 21+]
+ setSharedElementReturnTransition(Transition): void  [API 21+]
+ setSharedElementsUseOverlay(boolean): void  [API 21+]
+ setSoftInputMode(int): void  [API 3+]
+ setStatusBarColor(int): void  [API 21+, deprecated in 35]
+ setStatusBarContrastEnforced(boolean): void  [API 29+, deprecated in 35]
~ setSustainedPerformanceMode(boolean): void  [API 24+]
+ setSystemGestureExclusionRects(List): void  [API 29+]
+ setTitle(CharSequence): void  [API 1+]
+ setTitleColor(int): void  [API 1+, deprecated in 21]
+ setTransitionBackgroundFadeDuration(long): void  [API 21+]
+ setTransitionManager(TransitionManager): void  [API 21+]
+ setType(int): void  [API 1+]
+ setUiOptions(int): void  [API 14+]
+ setUiOptions(int, int): void  [API 14+]
+ setVolumeControlStream(int): void  [API 1+]
+ setWindowAnimations(int): void  [API 3+]
~ setWindowManager(WindowManager, IBinder, String): void  [API 1+]
+ setWindowManager(WindowManager, IBinder, String, boolean): void  [API 11+]
~ superDispatchGenericMotionEvent(MotionEvent): boolean  [API 12+]
+ superDispatchKeyEvent(KeyEvent): boolean  [API 1+]
~ superDispatchKeyShortcutEvent(KeyEvent): boolean  [API 11+]
+ superDispatchTouchEvent(MotionEvent): boolean  [API 1+]
~ superDispatchTrackballEvent(MotionEvent): boolean  [API 1+]
~ takeInputQueue(InputQueue.Callback): void  [API 9+]
~ takeKeyEvents(boolean): void  [API 1+]
~ takeSurface(SurfaceHolder.Callback2): void  [API 9+]
+ togglePanel(int, KeyEvent): void  [API 1+]
~ unregisterScrollCaptureCallback(ScrollCaptureCallback): void  [API 31+]
```

## Window.Callback — framework; API 1+ (22 +, 0 *, 3 ~, 0 -)

```
~ dispatchGenericMotionEvent(MotionEvent): boolean  [API 12+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 1+]
~ dispatchKeyShortcutEvent(KeyEvent): boolean  [API 11+]
~ dispatchPopulateAccessibilityEvent(AccessibilityEvent): boolean  [API 4+]
+ dispatchTouchEvent(MotionEvent): boolean  [API 1+]
+ dispatchTrackballEvent(MotionEvent): boolean  [API 1+]
+ onActionModeFinished(ActionMode): void  [API 11+]
+ onActionModeStarted(ActionMode): void  [API 11+]
+ onAttachedToWindow(): void  [API 5+]
+ onContentChanged(): void  [API 1+]
+ onCreatePanelMenu(int, Menu): boolean  [API 1+]
+ onCreatePanelView(int): View  [API 1+]
+ onDetachedFromWindow(): void  [API 5+]
+ onMenuItemSelected(int, MenuItem): boolean  [API 1+]
+ onMenuOpened(int, Menu): boolean  [API 1+]
+ onPanelClosed(int, Menu): void  [API 1+]
+ onPointerCaptureChanged(boolean): void  [API 26+]
+ onPreparePanel(int, View, Menu): boolean  [API 1+]
+ onProvideKeyboardShortcuts(List, Menu, int): void  [API 24+]
+ onSearchRequested(): boolean  [API 1+]
+ onSearchRequested(SearchEvent): boolean  [API 23+]
+ onWindowAttributesChanged(WindowManager.LayoutParams): void  [API 1+]
+ onWindowFocusChanged(boolean): void  [API 1+]
+ onWindowStartingActionMode(ActionMode.Callback): ActionMode  [API 11+]
+ onWindowStartingActionMode(ActionMode.Callback, int): ActionMode  [API 23+]
```

## Window.OnFrameMetricsAvailableListener — framework; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ onFrameMetricsAvailable(Window, FrameMetrics, int): void  [API 24+]
```

## Window.OnRestrictedCaptionAreaChangedListener — framework; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ onRestrictedCaptionAreaChanged(Rect): void  [API 24+]
```

## WindowAnimationFrameStats — declaration; API 21+, deprecated in 34 (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 21+]
- describeContents(): int  [API 21+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## WindowContentFrameStats — declaration; API 21+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 21+]
- describeContents(): int  [API 21+]
- getFramePostedTimeNano(int): long  [API 21+]
- getFrameReadyTimeNano(int): long  [API 21+]
- toString(): String  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## WindowId — declaration; API 18+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 18+]
- describeContents(): int  [API 18+]
- equals(Object): boolean  [API 18+]
- hashCode(): int  [API 18+]
- isFocused(): boolean  [API 18+]
- registerFocusObserver(WindowId.FocusObserver): void  [API 18+]
- toString(): String  [API 18+]
- unregisterFocusObserver(WindowId.FocusObserver): void  [API 18+]
- writeToParcel(Parcel, int): void  [API 18+]
```

## WindowId.FocusObserver — declaration; API 18+ (3 +, 0 *, 0 ~, 0 -)

```
+ FocusObserver()  [API 18+]
+ onFocusGained(WindowId): void  [API 18+]
+ onFocusLost(WindowId): void  [API 18+]
```

## WindowInsets — framework; API 20+ (29 +, 0 *, 4 ~, 6 -)

```
+ CONSUMED [field]  [API 30+]
+ WindowInsets(WindowInsets)  [API 20+]
~ consumeDisplayCutout(): WindowInsets  [API 28+, deprecated in 30]
+ consumeStableInsets(): WindowInsets  [API 21+, deprecated in 30]
+ consumeSystemWindowInsets(): WindowInsets  [API 20+, deprecated in 30]
+ equals(Object): boolean  [API 20+]
- getBoundingRects(int): List  [API 35+]
- getBoundingRectsIgnoringVisibility(int): List  [API 35+]
~ getDisplayCutout(): DisplayCutout  [API 28+]
- getDisplayShape(): DisplayShape  [API 34+]
- getFrame(): Size  [API 35+]
+ getInsets(int): Insets  [API 30+]
+ getInsetsIgnoringVisibility(int): Insets  [API 30+]
+ getMandatorySystemGestureInsets(): Insets  [API 29+, deprecated in 30]
- getPrivacyIndicatorBounds(): Rect  [API 31+]
- getRoundedCorner(int): RoundedCorner  [API 31+]
+ getStableInsetBottom(): int  [API 21+, deprecated in 30]
+ getStableInsetLeft(): int  [API 21+, deprecated in 30]
+ getStableInsetRight(): int  [API 21+, deprecated in 30]
+ getStableInsetTop(): int  [API 21+, deprecated in 30]
+ getStableInsets(): Insets  [API 29+, deprecated in 30]
+ getSystemGestureInsets(): Insets  [API 29+, deprecated in 30]
+ getSystemWindowInsetBottom(): int  [API 20+, deprecated in 30]
+ getSystemWindowInsetLeft(): int  [API 20+, deprecated in 30]
+ getSystemWindowInsetRight(): int  [API 20+, deprecated in 30]
+ getSystemWindowInsetTop(): int  [API 20+, deprecated in 30]
+ getSystemWindowInsets(): Insets  [API 29+, deprecated in 30]
+ getTappableElementInsets(): Insets  [API 29+, deprecated in 30]
+ hasInsets(): boolean  [API 20+]
+ hasStableInsets(): boolean  [API 21+, deprecated in 30]
+ hasSystemWindowInsets(): boolean  [API 20+, deprecated in 30]
+ hashCode(): int  [API 20+]
+ inset(Insets): WindowInsets  [API 30+]
+ inset(int, int, int, int): WindowInsets  [API 29+]
+ isConsumed(): boolean  [API 21+]
~ isRound(): boolean  [API 20+]
~ isVisible(int): boolean  [API 30+]
+ replaceSystemWindowInsets(Rect): WindowInsets  [API 21+, deprecated in 29]
+ replaceSystemWindowInsets(int, int, int, int): WindowInsets  [API 20+, deprecated in 29]
+ toString(): String  [API 20+]
```

## WindowInsets.Builder — framework; API 29+ (7 +, 0 *, 5 ~, 6 -)

```
+ Builder()  [API 29+]
+ Builder(WindowInsets)  [API 29+]
+ build(): WindowInsets  [API 29+]
- setBoundingRects(int, List): WindowInsets.Builder  [API 35+]
- setBoundingRectsIgnoringVisibility(int, List): WindowInsets.Builder  [API 35+]
~ setDisplayCutout(DisplayCutout): WindowInsets.Builder  [API 29+]
- setDisplayShape(DisplayShape): WindowInsets.Builder  [API 34+]
- setFrame(int, int): WindowInsets.Builder  [API 35+]
+ setInsets(int, Insets): WindowInsets.Builder  [API 30+]
+ setInsetsIgnoringVisibility(int, Insets): WindowInsets.Builder  [API 30+]
~ setMandatorySystemGestureInsets(Insets): WindowInsets.Builder  [API 29+, deprecated in 30]
- setPrivacyIndicatorBounds(Rect): WindowInsets.Builder  [API 31+]
- setRoundedCorner(int, RoundedCorner): WindowInsets.Builder  [API 31+]
+ setStableInsets(Insets): WindowInsets.Builder  [API 29+, deprecated in 30]
~ setSystemGestureInsets(Insets): WindowInsets.Builder  [API 29+, deprecated in 30]
+ setSystemWindowInsets(Insets): WindowInsets.Builder  [API 29+, deprecated in 30]
~ setTappableElementInsets(Insets): WindowInsets.Builder  [API 29+, deprecated in 30]
~ setVisible(int, boolean): WindowInsets.Builder  [API 30+]
```

## WindowInsets.Side — framework; API 30+ (0 +, 0 *, 1 ~, 0 -)

```
~ all(): int [static]  [API 30+]
```

## WindowInsets.Type — framework; API 30+ (0 +, 0 *, 10 ~, 0 -)

```
~ captionBar(): int [static]  [API 30+]
~ displayCutout(): int [static]  [API 30+]
~ ime(): int [static]  [API 30+]
~ mandatorySystemGestures(): int [static]  [API 30+]
~ navigationBars(): int [static]  [API 30+]
~ statusBars(): int [static]  [API 30+]
~ systemBars(): int [static]  [API 30+]
~ systemGestures(): int [static]  [API 30+]
~ systemOverlays(): int [static]  [API 34+]
~ tappableElement(): int [static]  [API 30+]
```

## WindowInsetsAnimation — declaration; API 30+ (0 +, 8 *, 0 ~, 1 -)

```
* WindowInsetsAnimation(int, Interpolator, long)  [API 30+]
* getAlpha(): float  [API 30+]
* getDurationMillis(): long  [API 30+]
* getFraction(): float  [API 30+]
- getInterpolatedFraction(): float  [API 30+]
* getInterpolator(): Interpolator  [API 30+]
* getTypeMask(): int  [API 30+]
* setAlpha(float): void  [API 30+]
* setFraction(float): void  [API 30+]
```

## WindowInsetsAnimation.Bounds — declaration; API 30+ (0 +, 4 *, 0 ~, 1 -)

```
* Bounds(Insets, Insets)  [API 30+]
* getLowerBound(): Insets  [API 30+]
* getUpperBound(): Insets  [API 30+]
- inset(Insets): WindowInsetsAnimation.Bounds  [API 30+]
* toString(): String  [API 30+]
```

## WindowInsetsAnimation.Callback — declaration; API 30+ (3 +, 0 *, 0 ~, 3 -)

```
- Callback(int)  [API 30+]
- getDispatchMode(): int  [API 30+]
+ onEnd(WindowInsetsAnimation): void  [API 30+]
+ onPrepare(WindowInsetsAnimation): void  [API 30+]
+ onProgress(WindowInsets, List): WindowInsets  [API 30+]
- onStart(WindowInsetsAnimation, WindowInsetsAnimation.Bounds): WindowInsetsAnimation.Bounds  [API 30+]
```

## WindowInsetsAnimationControlListener — declaration; API 30+ (3 +, 0 *, 0 ~, 0 -)

```
+ onCancelled(WindowInsetsAnimationController): void  [API 30+]
+ onFinished(WindowInsetsAnimationController): void  [API 30+]
+ onReady(WindowInsetsAnimationController, int): void  [API 30+]
```

## WindowInsetsAnimationController — declaration; API 30+ (10 +, 0 *, 0 ~, 1 -)

```
+ finish(boolean): void  [API 30+]
+ getCurrentAlpha(): float  [API 30+]
+ getCurrentFraction(): float  [API 30+]
+ getCurrentInsets(): Insets  [API 30+]
+ getHiddenStateInsets(): Insets  [API 30+]
+ getShownStateInsets(): Insets  [API 30+]
+ getTypes(): int  [API 30+]
+ isCancelled(): boolean  [API 30+]
+ isFinished(): boolean  [API 30+]
- isReady(): boolean  [API 30+]
+ setInsetsAndAlpha(Insets, float, float): void  [API 30+]
```

## WindowInsetsController — declaration; API 30+ (9 +, 0 *, 0 ~, 0 -)

```
+ addOnControllableInsetsChangedListener(WindowInsetsController.OnControllableInsetsChangedListener): void  [API 30+]
+ controlWindowInsetsAnimation(int, long, Interpolator, CancellationSignal, WindowInsetsAnimationControlListener): void  [API 30+]
+ getSystemBarsAppearance(): int  [API 30+]
+ getSystemBarsBehavior(): int  [API 30+]
+ hide(int): void  [API 30+]
+ removeOnControllableInsetsChangedListener(WindowInsetsController.OnControllableInsetsChangedListener): void  [API 30+]
+ setSystemBarsAppearance(int, int): void  [API 30+]
+ setSystemBarsBehavior(int): void  [API 30+]
+ show(int): void  [API 30+]
```

## WindowInsetsController.OnControllableInsetsChangedListener — declaration; API 30+ (1 +, 0 *, 0 ~, 0 -)

```
+ onControllableInsetsChanged(WindowInsetsController, int): void  [API 30+]
```

## WindowManager — framework; API 1+ (6 +, 0 *, 10 ~, 2 -)

```
+ addCrossWindowBlurEnabledListener(Consumer): void  [API 31+]
+ addCrossWindowBlurEnabledListener(Executor, Consumer): void  [API 31+]
~ addProposedRotationListener(Executor, IntConsumer): void  [API 34+]
~ addScreenRecordingCallback(Executor, Consumer): int  [API 35+]
+ getCurrentWindowMetrics(): WindowMetrics  [API 30+]
+ getDefaultDisplay(): Display  [API 1+, deprecated in 30]
+ getMaximumWindowMetrics(): WindowMetrics  [API 30+]
~ isCrossWindowBlurEnabled(): boolean  [API 31+]
- registerBatchedSurfaceControlInputReceiver(InputTransferToken, SurfaceControl, Choreographer, SurfaceControlInputReceiver): InputTransferToken  [API 35+]
~ registerTrustedPresentationListener(IBinder, TrustedPresentationThresholds, Executor, Consumer): void  [API 35+]
- registerUnbatchedSurfaceControlInputReceiver(InputTransferToken, SurfaceControl, Looper, SurfaceControlInputReceiver): InputTransferToken  [API 35+]
~ removeCrossWindowBlurEnabledListener(Consumer): void  [API 31+]
~ removeProposedRotationListener(IntConsumer): void  [API 34+]
~ removeScreenRecordingCallback(Consumer): void  [API 35+]
+ removeViewImmediate(View): void  [API 1+]
~ transferTouchGesture(InputTransferToken, InputTransferToken): boolean  [API 35+]
~ unregisterSurfaceControlInputReceiver(SurfaceControl): void  [API 35+]
~ unregisterTrustedPresentationListener(Consumer): void  [API 35+]
```

## WindowManager.BadTokenException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BadTokenException()  [API 1+]
+ BadTokenException(String)  [API 1+]
```

## WindowManager.InvalidDisplayException — framework; API 17+ (2 +, 0 *, 0 ~, 0 -)

```
+ InvalidDisplayException()  [API 17+]
+ InvalidDisplayException(String)  [API 17+]
```

## WindowManager.LayoutParams — framework; API 1+ (36 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ LayoutParams()  [API 1+]
+ LayoutParams(Parcel)  [API 1+]
+ LayoutParams(int)  [API 1+]
+ LayoutParams(int, int)  [API 1+]
+ LayoutParams(int, int, int)  [API 1+]
+ LayoutParams(int, int, int, int, int)  [API 1+]
+ LayoutParams(int, int, int, int, int, int, int)  [API 1+]
+ alpha [field]  [API 1+]
+ areWallpaperTouchEventsEnabled(): boolean  [API 34+]
+ buttonBrightness [field]  [API 8+]
+ canPlayMoveAnimation(): boolean  [API 34+]
+ copyFrom(WindowManager.LayoutParams): int  [API 1+]
+ debug(String): String  [API 1+]
~ describeContents(): int  [API 1+]
+ dimAmount [field]  [API 1+]
+ flags [field]  [API 1+]
+ format [field]  [API 1+]
+ getBlurBehindRadius(): int  [API 31+]
+ getColorMode(): int  [API 26+]
+ getDesiredHdrHeadroom(): float  [API 35+]
+ getFitInsetsSides(): int  [API 30+]
+ getFitInsetsTypes(): int  [API 30+]
+ getFrameRateBoostOnTouchEnabled(): boolean  [API 35+]
+ getTitle(): CharSequence  [API 1+]
+ gravity [field]  [API 1+]
+ horizontalMargin [field]  [API 1+]
+ horizontalWeight [field]  [API 1+]
+ isFitInsetsIgnoringVisibility(): boolean  [API 30+]
+ isFrameRatePowerSavingsBalanced(): boolean  [API 35+]
+ isHdrConversionEnabled(): boolean  [API 34+]
+ layoutInDisplayCutoutMode [field]  [API 28+]
+ mayUseInputMethod(int): boolean [static]  [API 3+]
+ memoryType [field]  [API 1+, deprecated in 15]
+ packageName [field]  [API 1+]
+ preferMinimalPostProcessing [field]  [API 30+]
+ preferredDisplayModeId [field]  [API 23+]
+ preferredRefreshRate [field]  [API 21+]
+ rotationAnimation [field]  [API 18+]
+ screenBrightness [field]  [API 3+]
+ screenOrientation [field]  [API 3+]
+ setBlurBehindRadius(int): void  [API 31+]
+ setCanPlayMoveAnimation(boolean): void  [API 34+]
+ setColorMode(int): void  [API 26+]
+ setDesiredHdrHeadroom(float): void  [API 35+]
+ setFitInsetsIgnoringVisibility(boolean): void  [API 30+]
+ setFitInsetsSides(int): void  [API 30+]
+ setFitInsetsTypes(int): void  [API 30+]
+ setFrameRateBoostOnTouchEnabled(boolean): void  [API 35+]
+ setFrameRatePowerSavingsBalanced(boolean): void  [API 35+]
+ setHdrConversionEnabled(boolean): void  [API 34+]
+ setTitle(CharSequence): void  [API 1+]
+ setWallpaperTouchEventsEnabled(boolean): void  [API 34+]
+ softInputMode [field]  [API 3+]
+ systemUiVisibility [field]  [API 11+, deprecated in 30]
+ toString(): String  [API 1+]
+ token [field]  [API 1+]
+ type [field]  [API 1+]
+ verticalMargin [field]  [API 1+]
+ verticalWeight [field]  [API 1+]
+ windowAnimations [field]  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
+ x [field]  [API 1+]
+ y [field]  [API 1+]
```

## WindowMetrics — declaration; API 30+ (0 +, 6 *, 0 ~, 0 -)

```
* WindowMetrics(Rect, WindowInsets)  [API 30+, deprecated in 34]
* WindowMetrics(Rect, WindowInsets, float)  [API 34+]
* getBounds(): Rect  [API 30+]
* getDensity(): float  [API 34+]
* getWindowInsets(): WindowInsets  [API 30+]
* toString(): String  [API 30+]
```


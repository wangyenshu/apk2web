# android.accessibilityservice

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AccessibilityButtonController — declaration; API 26+ (0 +, 0 *, 0 ~, 4 -)

```
- isAccessibilityButtonAvailable(): boolean  [API 26+]
- registerAccessibilityButtonCallback(AccessibilityButtonController.AccessibilityButtonCallback): void  [API 26+]
- registerAccessibilityButtonCallback(AccessibilityButtonController.AccessibilityButtonCallback, Handler): void  [API 26+]
- unregisterAccessibilityButtonCallback(AccessibilityButtonController.AccessibilityButtonCallback): void  [API 26+]
```

## AccessibilityButtonController.AccessibilityButtonCallback — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ AccessibilityButtonCallback()  [API 26+]
+ onAvailabilityChanged(AccessibilityButtonController, boolean): void  [API 26+]
+ onClicked(AccessibilityButtonController): void  [API 26+]
```

## AccessibilityGestureEvent — declaration; API 30+ (0 +, 7 *, 0 ~, 1 -)

```
* AccessibilityGestureEvent(int, int, List)  [API 33+]
+ CREATOR [field]  [API 30+]
* describeContents(): int  [API 30+]
- gestureIdToString(int): String [static]  [API 31+]
* getDisplayId(): int  [API 30+]
* getGestureId(): int  [API 30+]
* getMotionEvents(): List  [API 31+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## AccessibilityService — declaration; API 4+ (5 +, 0 *, 0 ~, 42 -)

```
- AccessibilityService()  [API 4+]
- attachAccessibilityOverlayToDisplay(int, SurfaceControl): void  [API 34+]
- attachAccessibilityOverlayToWindow(int, SurfaceControl): void  [API 34+]
- clearCache(): boolean  [API 33+]
- clearCachedSubtree(AccessibilityNodeInfo): boolean  [API 33+]
- createDisplayContext(Display): Context  [API 4+]
- createWindowContext(Display, int, Bundle): Context  [API 4+]
- createWindowContext(int, Bundle): Context  [API 4+]
- disableSelf(): void  [API 24+]
- dispatchGesture(GestureDescription, AccessibilityService.GestureResultCallback, Handler): boolean  [API 24+]
- findFocus(int): AccessibilityNodeInfo  [API 21+]
- getAccessibilityButtonController(): AccessibilityButtonController  [API 26+]
- getAccessibilityButtonController(int): AccessibilityButtonController  [API 30+]
- getBrailleDisplayController(): BrailleDisplayController  [API 35+]
- getFingerprintGestureController(): FingerprintGestureController  [API 26+]
- getInputMethod(): InputMethod  [API 33+]
- getMagnificationController(): AccessibilityService.MagnificationController  [API 24+]
- getRootInActiveWindow(): AccessibilityNodeInfo  [API 16+]
- getRootInActiveWindow(int): AccessibilityNodeInfo  [API 33+]
- getServiceInfo(): AccessibilityServiceInfo  [API 16+]
- getSoftKeyboardController(): AccessibilityService.SoftKeyboardController  [API 24+]
- getSystemActions(): List  [API 30+]
- getSystemService(String): Object  [API 4+]
- getTouchInteractionController(int): TouchInteractionController  [API 33+]
- getWindows(): List  [API 21+]
- getWindowsOnAllDisplays(): SparseArray  [API 30+]
- isCacheEnabled(): boolean  [API 33+]
- isNodeInCache(AccessibilityNodeInfo): boolean  [API 33+]
+ onAccessibilityEvent(AccessibilityEvent): void  [API 4+]
- onBind(Intent): IBinder  [API 4+]
- onCreateInputMethod(): InputMethod  [API 33+]
- onGesture(AccessibilityGestureEvent): boolean  [API 30+]
- onGesture(int): boolean  [API 16+, deprecated in 30]
+ onInterrupt(): void  [API 4+]
- onKeyEvent(KeyEvent): boolean  [API 18+]
+ onMotionEvent(MotionEvent): void  [API 34+]
+ onServiceConnected(): void  [API 4+]
+ onSystemActionsChanged(): void  [API 30+]
- performGlobalAction(int): boolean  [API 16+]
- setAccessibilityFocusAppearance(int, int): void  [API 31+]
- setAnimationScale(float): void  [API 33+]
- setCacheEnabled(boolean): boolean  [API 33+]
- setGestureDetectionPassthroughRegion(int, Region): void  [API 30+]
- setServiceInfo(AccessibilityServiceInfo): void  [API 4+]
- setTouchExplorationPassthroughRegion(int, Region): void  [API 30+]
- takeScreenshot(int, Executor, AccessibilityService.TakeScreenshotCallback): void  [API 30+]
- takeScreenshotOfWindow(int, Executor, AccessibilityService.TakeScreenshotCallback): void  [API 34+]
```

## AccessibilityService.GestureResultCallback — declaration; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ GestureResultCallback()  [API 24+]
+ onCancelled(GestureDescription): void  [API 24+]
+ onCompleted(GestureDescription): void  [API 24+]
```

## AccessibilityService.MagnificationController — declaration; API 24+ (0 +, 0 *, 0 ~, 14 -)

```
- addListener(AccessibilityService.MagnificationController.OnMagnificationChangedListener): void  [API 24+]
- addListener(AccessibilityService.MagnificationController.OnMagnificationChangedListener, Handler): void  [API 24+]
- getCenterX(): float  [API 24+, deprecated in 33]
- getCenterY(): float  [API 24+, deprecated in 33]
- getCurrentMagnificationRegion(): Region  [API 33+]
- getMagnificationConfig(): MagnificationConfig  [API 33+]
- getMagnificationRegion(): Region  [API 24+, deprecated in 33]
- getScale(): float  [API 24+, deprecated in 33]
- removeListener(AccessibilityService.MagnificationController.OnMagnificationChangedListener): boolean  [API 24+]
- reset(boolean): boolean  [API 24+]
- resetCurrentMagnification(boolean): boolean  [API 33+]
- setCenter(float, float, boolean): boolean  [API 24+, deprecated in 33]
- setMagnificationConfig(MagnificationConfig, boolean): boolean  [API 33+]
- setScale(float, boolean): boolean  [API 24+, deprecated in 33]
```

## AccessibilityService.MagnificationController.OnMagnificationChangedListener — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ onMagnificationChanged(AccessibilityService.MagnificationController, Region, MagnificationConfig): void  [API 33+]
+ onMagnificationChanged(AccessibilityService.MagnificationController, Region, float, float, float): void  [API 24+, deprecated in 33]
```

## AccessibilityService.ScreenshotResult — declaration; API 30+ (0 +, 0 *, 0 ~, 3 -)

```
- getColorSpace(): ColorSpace  [API 30+]
- getHardwareBuffer(): HardwareBuffer  [API 30+]
- getTimestamp(): long  [API 30+]
```

## AccessibilityService.SoftKeyboardController — declaration; API 24+ (0 +, 0 *, 0 ~, 7 -)

```
- addOnShowModeChangedListener(AccessibilityService.SoftKeyboardController.OnShowModeChangedListener): void  [API 24+]
- addOnShowModeChangedListener(AccessibilityService.SoftKeyboardController.OnShowModeChangedListener, Handler): void  [API 24+]
- getShowMode(): int  [API 24+]
- removeOnShowModeChangedListener(AccessibilityService.SoftKeyboardController.OnShowModeChangedListener): boolean  [API 24+]
- setInputMethodEnabled(String, boolean): int  [API 33+]
- setShowMode(int): boolean  [API 24+]
- switchToInputMethod(String): boolean  [API 30+]
```

## AccessibilityService.SoftKeyboardController.OnShowModeChangedListener — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ onShowModeChanged(AccessibilityService.SoftKeyboardController, int): void  [API 24+]
```

## AccessibilityService.TakeScreenshotCallback — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ onFailure(int): void  [API 30+]
+ onSuccess(AccessibilityService.ScreenshotResult): void  [API 30+]
```

## AccessibilityServiceInfo — declaration; API 4+ (0 +, 7 *, 0 ~, 19 -)

```
* AccessibilityServiceInfo()  [API 4+]
- CREATOR [field]  [API 4+]
- capabilityToString(int): String [static]  [API 18+]
- describeContents(): int  [API 4+]
- equals(Object): boolean  [API 4+]
+ eventTypes [field]  [API 4+]
+ feedbackType [field]  [API 4+]
- feedbackTypeToString(int): String [static]  [API 14+]
- flagToString(int): String [static]  [API 14+]
+ flags [field]  [API 4+]
- getCanRetrieveWindowContent(): boolean  [API 14+, deprecated in 18]
- getCapabilities(): int  [API 18+]
- getDescription(): String  [API 14+, deprecated in 16]
- getId(): String  [API 14+]
* getInteractiveUiTimeoutMillis(): int  [API 29+]
* getMotionEventSources(): int  [API 34+]
* getNonInteractiveUiTimeoutMillis(): int  [API 29+]
- getResolveInfo(): ResolveInfo  [API 14+]
- getSettingsActivityName(): String  [API 14+]
- getTileServiceName(): String  [API 33+]
- hashCode(): int  [API 4+]
- isAccessibilityTool(): boolean  [API 31+]
- loadDescription(PackageManager): String  [API 16+]
- loadIntro(PackageManager): CharSequence  [API 33+]
- loadSummary(PackageManager): CharSequence  [API 26+]
+ notificationTimeout [field]  [API 4+]
+ packageNames [field]  [API 4+]
* setInteractiveUiTimeoutMillis(int): void  [API 29+]
* setMotionEventSources(int): void  [API 34+]
* setNonInteractiveUiTimeoutMillis(int): void  [API 29+]
- toString(): String  [API 4+]
- writeToParcel(Parcel, int): void  [API 4+]
```

## BrailleDisplayController — declaration; API 35+ (7 +, 0 *, 0 ~, 0 -)

```
+ connect(BluetoothDevice, BrailleDisplayController.BrailleDisplayCallback): void  [API 35+]
+ connect(BluetoothDevice, Executor, BrailleDisplayController.BrailleDisplayCallback): void  [API 35+]
+ connect(UsbDevice, BrailleDisplayController.BrailleDisplayCallback): void  [API 35+]
+ connect(UsbDevice, Executor, BrailleDisplayController.BrailleDisplayCallback): void  [API 35+]
+ disconnect(): void  [API 35+]
+ isConnected(): boolean  [API 35+]
+ write(byte[]): void  [API 35+]
```

## BrailleDisplayController.BrailleDisplayCallback — declaration; API 35+ (4 +, 0 *, 0 ~, 0 -)

```
+ onConnected(byte[]): void  [API 35+]
+ onConnectionFailed(int): void  [API 35+]
+ onDisconnected(): void  [API 35+]
+ onInput(byte[]): void  [API 35+]
```

## FingerprintGestureController — declaration; API 26+ (0 +, 0 *, 0 ~, 3 -)

```
- isGestureDetectionAvailable(): boolean  [API 26+]
- registerFingerprintGestureCallback(FingerprintGestureController.FingerprintGestureCallback, Handler): void  [API 26+]
- unregisterFingerprintGestureCallback(FingerprintGestureController.FingerprintGestureCallback): void  [API 26+]
```

## FingerprintGestureController.FingerprintGestureCallback — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ FingerprintGestureCallback()  [API 26+]
+ onGestureDetected(int): void  [API 26+]
+ onGestureDetectionAvailabilityChanged(boolean): void  [API 26+]
```

## GestureDescription — declaration; API 24+ (0 +, 1 *, 0 ~, 4 -)

```
* getDisplayId(): int  [API 30+]
- getMaxGestureDuration(): long [static]  [API 24+]
- getMaxStrokeCount(): int [static]  [API 24+]
- getStroke(int): GestureDescription.StrokeDescription  [API 24+]
- getStrokeCount(): int  [API 24+]
```

## GestureDescription.Builder — declaration; API 24+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder()  [API 24+]
- addStroke(GestureDescription.StrokeDescription): GestureDescription.Builder  [API 24+]
* build(): GestureDescription  [API 24+]
* setDisplayId(int): GestureDescription.Builder  [API 30+]
```

## GestureDescription.StrokeDescription — declaration; API 24+ (0 +, 4 *, 0 ~, 3 -)

```
* StrokeDescription(Path, long, long)  [API 24+]
- StrokeDescription(Path, long, long, boolean)  [API 26+]
- continueStroke(Path, long, long, boolean): GestureDescription.StrokeDescription  [API 26+]
* getDuration(): long  [API 24+]
* getPath(): Path  [API 24+]
* getStartTime(): long  [API 24+]
- willContinue(): boolean  [API 26+]
```

## InputMethod — declaration; API 33+ (0 +, 0 *, 0 ~, 7 -)

```
- InputMethod(AccessibilityService)  [API 33+]
- getCurrentInputConnection(): InputMethod.AccessibilityInputConnection  [API 33+]
- getCurrentInputEditorInfo(): EditorInfo  [API 33+]
- getCurrentInputStarted(): boolean  [API 33+]
- onFinishInput(): void  [API 33+]
- onStartInput(EditorInfo, boolean): void  [API 33+]
- onUpdateSelection(int, int, int, int, int, int): void  [API 33+]
```

## InputMethod.AccessibilityInputConnection — declaration; API 33+ (0 +, 0 *, 0 ~, 9 -)

```
- clearMetaKeyStates(int): void  [API 33+]
- commitText(CharSequence, int, TextAttribute): void  [API 33+]
- deleteSurroundingText(int, int): void  [API 33+]
- getCursorCapsMode(int): int  [API 33+]
- getSurroundingText(int, int, int): SurroundingText  [API 33+]
- performContextMenuAction(int): void  [API 33+]
- performEditorAction(int): void  [API 33+]
- sendKeyEvent(KeyEvent): void  [API 33+]
- setSelection(int, int): void  [API 33+]
```

## MagnificationConfig — declaration; API 33+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* getCenterX(): float  [API 33+]
* getCenterY(): float  [API 33+]
* getMode(): int  [API 33+]
* getScale(): float  [API 33+]
* isActivated(): boolean  [API 34+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## MagnificationConfig.Builder — declaration; API 33+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): MagnificationConfig  [API 33+]
* setActivated(boolean): MagnificationConfig.Builder  [API 34+]
* setCenterX(float): MagnificationConfig.Builder  [API 33+]
* setCenterY(float): MagnificationConfig.Builder  [API 33+]
* setMode(int): MagnificationConfig.Builder  [API 33+]
* setScale(float): MagnificationConfig.Builder  [API 33+]
```

## TouchInteractionController — declaration; API 33+ (0 +, 0 *, 0 ~, 12 -)

```
- getDisplayId(): int  [API 33+]
- getMaxPointerCount(): int  [API 33+]
- getState(): int  [API 33+]
- performClick(): void  [API 33+]
- performLongClickAndStartDrag(): void  [API 33+]
- registerCallback(Executor, TouchInteractionController.Callback): void  [API 33+]
- requestDelegating(): void  [API 33+]
- requestDragging(int): void  [API 33+]
- requestTouchExploration(): void  [API 33+]
- stateToString(int): String [static]  [API 33+]
- unregisterAllCallbacks(): void  [API 33+]
- unregisterCallback(TouchInteractionController.Callback): boolean  [API 33+]
```

## TouchInteractionController.Callback — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ onMotionEvent(MotionEvent): void  [API 33+]
+ onStateChanged(int): void  [API 33+]
```


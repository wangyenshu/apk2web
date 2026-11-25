# android.gesture

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Gesture — framework; API 4+ (15 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 4+]
+ Gesture()  [API 4+]
+ addStroke(GestureStroke): void  [API 4+]
+ clone(): Object  [API 4+]
~ describeContents(): int  [API 4+]
+ getBoundingBox(): RectF  [API 4+]
+ getID(): long  [API 4+]
+ getLength(): float  [API 4+]
+ getStrokes(): ArrayList  [API 4+]
+ getStrokesCount(): int  [API 4+]
+ toBitmap(int, int, int, int): Bitmap  [API 4+]
+ toBitmap(int, int, int, int, int): Bitmap  [API 4+]
+ toPath(): Path  [API 4+]
+ toPath(Path): Path  [API 4+]
+ toPath(Path, int, int, int, int): Path  [API 4+]
+ toPath(int, int, int, int): Path  [API 4+]
+ writeToParcel(Parcel, int): void  [API 4+]
```

## GestureLibraries — framework; API 4+ (5 +, 0 *, 0 ~, 0 -)

```
+ fromFile(File): GestureLibrary [static]  [API 4+]
+ fromFile(String): GestureLibrary [static]  [API 4+]
+ fromFileDescriptor(ParcelFileDescriptor): GestureLibrary [static]  [API 30+]
+ fromPrivateFile(Context, String): GestureLibrary [static]  [API 4+]
+ fromRawResource(Context, int): GestureLibrary [static]  [API 4+]
```

## GestureLibrary — framework; API 4+ (13 +, 0 *, 1 ~, 0 -)

```
+ GestureLibrary()  [API 4+]
+ addGesture(String, Gesture): void  [API 4+]
+ getGestureEntries(): Set  [API 4+]
+ getGestures(String): ArrayList  [API 4+]
+ getOrientationStyle(): int  [API 4+]
+ getSequenceType(): int  [API 4+]
~ isReadOnly(): boolean  [API 4+]
+ load(): boolean  [API 4+]
+ mStore [field]  [API 4+]
+ recognize(Gesture): ArrayList  [API 4+]
+ removeEntry(String): void  [API 4+]
+ removeGesture(String, Gesture): void  [API 4+]
+ save(): boolean  [API 4+]
+ setOrientationStyle(int): void  [API 4+]
+ setSequenceType(int): void  [API 4+]
```

## GestureOverlayView — framework; API 4+ (49 +, 0 *, 0 ~, 0 -)

```
+ GestureOverlayView(Context)  [API 4+]
+ GestureOverlayView(Context, AttributeSet)  [API 4+]
+ GestureOverlayView(Context, AttributeSet, int)  [API 4+]
+ GestureOverlayView(Context, AttributeSet, int, int)  [API 21+]
+ addOnGestureListener(GestureOverlayView.OnGestureListener): void  [API 4+]
+ addOnGesturePerformedListener(GestureOverlayView.OnGesturePerformedListener): void  [API 4+]
+ addOnGesturingListener(GestureOverlayView.OnGesturingListener): void  [API 4+]
+ cancelClearAnimation(): void  [API 4+]
+ cancelGesture(): void  [API 4+]
+ clear(boolean): void  [API 4+]
+ dispatchTouchEvent(MotionEvent): boolean  [API 4+]
+ draw(Canvas): void  [API 4+]
+ getCurrentStroke(): ArrayList  [API 4+]
+ getFadeOffset(): long  [API 4+]
+ getGesture(): Gesture  [API 4+]
+ getGestureColor(): int  [API 4+]
+ getGesturePath(): Path  [API 4+]
+ getGesturePath(Path): Path  [API 4+]
+ getGestureStrokeAngleThreshold(): float  [API 4+]
+ getGestureStrokeLengthThreshold(): float  [API 4+]
+ getGestureStrokeSquarenessTreshold(): float  [API 4+]
+ getGestureStrokeType(): int  [API 4+]
+ getGestureStrokeWidth(): float  [API 4+]
+ getOrientation(): int  [API 4+]
+ getUncertainGestureColor(): int  [API 4+]
+ isEventsInterceptionEnabled(): boolean  [API 4+]
+ isFadeEnabled(): boolean  [API 4+]
+ isGestureVisible(): boolean  [API 4+]
+ isGesturing(): boolean  [API 4+]
+ onDetachedFromWindow(): void  [API 4+]
+ removeAllOnGestureListeners(): void  [API 4+]
+ removeAllOnGesturePerformedListeners(): void  [API 4+]
+ removeAllOnGesturingListeners(): void  [API 4+]
+ removeOnGestureListener(GestureOverlayView.OnGestureListener): void  [API 4+]
+ removeOnGesturePerformedListener(GestureOverlayView.OnGesturePerformedListener): void  [API 4+]
+ removeOnGesturingListener(GestureOverlayView.OnGesturingListener): void  [API 4+]
+ setEventsInterceptionEnabled(boolean): void  [API 4+]
+ setFadeEnabled(boolean): void  [API 4+]
+ setFadeOffset(long): void  [API 4+]
+ setGesture(Gesture): void  [API 4+]
+ setGestureColor(int): void  [API 4+]
+ setGestureStrokeAngleThreshold(float): void  [API 4+]
+ setGestureStrokeLengthThreshold(float): void  [API 4+]
+ setGestureStrokeSquarenessTreshold(float): void  [API 4+]
+ setGestureStrokeType(int): void  [API 4+]
+ setGestureStrokeWidth(float): void  [API 4+]
+ setGestureVisible(boolean): void  [API 4+]
+ setOrientation(int): void  [API 4+]
+ setUncertainGestureColor(int): void  [API 4+]
```

## GestureOverlayView.OnGestureListener — framework; API 4+ (4 +, 0 *, 0 ~, 0 -)

```
+ onGesture(GestureOverlayView, MotionEvent): void  [API 4+]
+ onGestureCancelled(GestureOverlayView, MotionEvent): void  [API 4+]
+ onGestureEnded(GestureOverlayView, MotionEvent): void  [API 4+]
+ onGestureStarted(GestureOverlayView, MotionEvent): void  [API 4+]
```

## GestureOverlayView.OnGesturePerformedListener — framework; API 4+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGesturePerformed(GestureOverlayView, Gesture): void  [API 4+]
```

## GestureOverlayView.OnGesturingListener — framework; API 4+ (2 +, 0 *, 0 ~, 0 -)

```
+ onGesturingEnded(GestureOverlayView): void  [API 4+]
+ onGesturingStarted(GestureOverlayView): void  [API 4+]
```

## GesturePoint — framework; API 4+ (2 +, 0 *, 0 ~, 0 -)

```
+ GesturePoint(float, float, long)  [API 4+]
+ clone(): Object  [API 4+]
+ timestamp [field]  [API 4+]
+ x [field]  [API 4+]
+ y [field]  [API 4+]
```

## GestureStore — framework; API 4+ (16 +, 0 *, 0 ~, 0 -)

```
+ GestureStore()  [API 4+]
+ addGesture(String, Gesture): void  [API 4+]
+ getGestureEntries(): Set  [API 4+]
+ getGestures(String): ArrayList  [API 4+]
+ getOrientationStyle(): int  [API 4+]
+ getSequenceType(): int  [API 4+]
+ hasChanged(): boolean  [API 4+]
+ load(InputStream): void  [API 4+]
+ load(InputStream, boolean): void  [API 4+]
+ recognize(Gesture): ArrayList  [API 4+]
+ removeEntry(String): void  [API 4+]
+ removeGesture(String, Gesture): void  [API 4+]
+ save(OutputStream): void  [API 4+]
+ save(OutputStream, boolean): void  [API 4+]
+ setOrientationStyle(int): void  [API 4+]
+ setSequenceType(int): void  [API 4+]
```

## GestureStroke — framework; API 4+ (6 +, 0 *, 0 ~, 0 -)

```
+ GestureStroke(ArrayList)  [API 4+]
+ boundingBox [field]  [API 4+]
+ clearPath(): void  [API 4+]
+ clone(): Object  [API 4+]
+ computeOrientedBoundingBox(): OrientedBoundingBox  [API 4+]
+ getPath(): Path  [API 4+]
+ length [field]  [API 4+]
+ points [field]  [API 4+]
+ toPath(float, float, int): Path  [API 4+]
```

## GestureUtils — framework; API 8+ (5 +, 0 *, 0 ~, 0 -)

```
+ computeOrientedBoundingBox(ArrayList): OrientedBoundingBox [static]  [API 8+]
+ computeOrientedBoundingBox(float[]): OrientedBoundingBox [static]  [API 8+]
+ spatialSampling(Gesture, int): float[] [static]  [API 8+]
+ spatialSampling(Gesture, int, boolean): float[] [static]  [API 8+]
+ temporalSampling(GestureStroke, int): float[] [static]  [API 8+]
```

## OrientedBoundingBox — framework; API 4+ (0 +, 0 *, 0 ~, 0 -)

```
+ centerX [field]  [API 4+]
+ centerY [field]  [API 4+]
+ height [field]  [API 4+]
+ orientation [field]  [API 4+]
+ squareness [field]  [API 4+]
+ width [field]  [API 4+]
```

## Prediction — framework; API 4+ (1 +, 0 *, 0 ~, 0 -)

```
+ name [field]  [API 4+]
+ score [field]  [API 4+]
+ toString(): String  [API 4+]
```


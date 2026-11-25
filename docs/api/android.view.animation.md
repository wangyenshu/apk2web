# android.view.animation

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AccelerateDecelerateInterpolator — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ AccelerateDecelerateInterpolator()  [API 1+]
+ AccelerateDecelerateInterpolator(Context, AttributeSet)  [API 1+]
+ getInterpolation(float): float  [API 1+]
```

## AccelerateInterpolator — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ AccelerateInterpolator()  [API 1+]
+ AccelerateInterpolator(Context, AttributeSet)  [API 1+]
+ AccelerateInterpolator(float)  [API 1+]
+ getInterpolation(float): float  [API 1+]
```

## AlphaAnimation — framework; API 1+ (2 +, 0 *, 2 ~, 1 -)

```
- AlphaAnimation(Context, AttributeSet)  [API 1+]
+ AlphaAnimation(float, float)  [API 1+]
+ applyTransformation(float, Transformation): void  [API 1+]
~ willChangeBounds(): boolean  [API 1+]
~ willChangeTransformationMatrix(): boolean  [API 1+]
```

## Animation — framework; API 1+ (42 +, 0 *, 7 ~, 4 -)

```
+ Animation()  [API 1+]
+ Animation(Context, AttributeSet)  [API 1+]
~ applyTransformation(float, Transformation): void  [API 1+]
+ cancel(): void  [API 8+]
+ clone(): Animation  [API 3+]
+ computeDurationHint(): long  [API 3+]
+ ensureInterpolator(): void  [API 1+]
+ finalize(): void  [API 1+]
- getBackdropColor(): int  [API 33+]
~ getBackgroundColor(): int  [API 12+, deprecated in 30]
~ getDetachWallpaper(): boolean  [API 5+, deprecated in 29]
+ getDuration(): long  [API 1+]
+ getFillAfter(): boolean  [API 1+]
+ getFillBefore(): boolean  [API 1+]
+ getInterpolator(): Interpolator  [API 1+]
+ getRepeatCount(): int  [API 1+]
+ getRepeatMode(): int  [API 1+]
+ getScaleFactor(): float  [API 11+]
- getShowBackdrop(): boolean  [API 33+]
+ getStartOffset(): long  [API 1+]
+ getStartTime(): long  [API 1+]
+ getTransformation(long, Transformation): boolean  [API 1+]
+ getTransformation(long, Transformation, float): boolean  [API 11+]
+ getZAdjustment(): int  [API 1+]
+ hasEnded(): boolean  [API 1+]
+ hasStarted(): boolean  [API 1+]
+ initialize(int, int, int, int): void  [API 1+]
+ isFillEnabled(): boolean  [API 3+]
+ isInitialized(): boolean  [API 1+]
+ reset(): void  [API 1+]
+ resolveSize(int, float, int, int): float  [API 1+]
+ restrictDuration(long): void  [API 1+]
+ scaleCurrentDuration(float): void  [API 1+]
+ setAnimationListener(Animation.AnimationListener): void  [API 1+]
- setBackdropColor(int): void  [API 33+]
~ setBackgroundColor(int): void  [API 12+, deprecated in 30]
~ setDetachWallpaper(boolean): void  [API 5+, deprecated in 29]
+ setDuration(long): void  [API 1+]
+ setFillAfter(boolean): void  [API 1+]
+ setFillBefore(boolean): void  [API 1+]
+ setFillEnabled(boolean): void  [API 3+]
+ setInterpolator(Context, int): void  [API 1+]
+ setInterpolator(Interpolator): void  [API 1+]
+ setRepeatCount(int): void  [API 1+]
+ setRepeatMode(int): void  [API 1+]
- setShowBackdrop(boolean): void  [API 33+]
+ setStartOffset(long): void  [API 1+]
+ setStartTime(long): void  [API 1+]
+ setZAdjustment(int): void  [API 1+]
+ start(): void  [API 1+]
+ startNow(): void  [API 1+]
~ willChangeBounds(): boolean  [API 1+]
~ willChangeTransformationMatrix(): boolean  [API 1+]
```

## Animation.AnimationListener — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ onAnimationEnd(Animation): void  [API 1+]
+ onAnimationRepeat(Animation): void  [API 1+]
+ onAnimationStart(Animation): void  [API 1+]
```

## Animation.Description — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ Description()  [API 1+]
+ type [field]  [API 1+]
+ value [field]  [API 1+]
```

## AnimationSet — framework; API 1+ (18 +, 0 *, 2 ~, 1 -)

```
- AnimationSet(Context, AttributeSet)  [API 1+]
+ AnimationSet(boolean)  [API 1+]
+ addAnimation(Animation): void  [API 1+]
+ clone(): AnimationSet  [API 3+]
+ computeDurationHint(): long  [API 1+]
+ getAnimations(): List  [API 1+]
+ getDuration(): long  [API 1+]
+ getStartTime(): long  [API 1+]
+ getTransformation(long, Transformation): boolean  [API 1+]
+ initialize(int, int, int, int): void  [API 1+]
+ reset(): void  [API 1+]
+ restrictDuration(long): void  [API 1+]
+ scaleCurrentDuration(float): void  [API 1+]
+ setDuration(long): void  [API 1+]
+ setFillAfter(boolean): void  [API 1+]
+ setFillBefore(boolean): void  [API 1+]
+ setRepeatMode(int): void  [API 1+]
+ setStartOffset(long): void  [API 1+]
+ setStartTime(long): void  [API 1+]
~ willChangeBounds(): boolean  [API 1+]
~ willChangeTransformationMatrix(): boolean  [API 1+]
```

## AnimationUtils — framework; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ AnimationUtils()  [API 1+]
+ currentAnimationTimeMillis(): long [static]  [API 1+]
+ loadAnimation(Context, int): Animation [static]  [API 1+]
+ loadInterpolator(Context, int): Interpolator [static]  [API 1+]
+ loadLayoutAnimation(Context, int): LayoutAnimationController [static]  [API 1+]
+ makeInAnimation(Context, boolean): Animation [static]  [API 1+]
+ makeInChildBottomAnimation(Context): Animation [static]  [API 1+]
+ makeOutAnimation(Context, boolean): Animation [static]  [API 1+]
```

## AnticipateInterpolator — framework; API 4+ (3 +, 0 *, 0 ~, 1 -)

```
+ AnticipateInterpolator()  [API 4+]
- AnticipateInterpolator(Context, AttributeSet)  [API 4+]
+ AnticipateInterpolator(float)  [API 4+]
+ getInterpolation(float): float  [API 4+]
```

## AnticipateOvershootInterpolator — framework; API 4+ (5 +, 0 *, 0 ~, 0 -)

```
+ AnticipateOvershootInterpolator()  [API 4+]
+ AnticipateOvershootInterpolator(Context, AttributeSet)  [API 4+]
+ AnticipateOvershootInterpolator(float)  [API 4+]
+ AnticipateOvershootInterpolator(float, float)  [API 4+]
+ getInterpolation(float): float  [API 4+]
```

## BaseInterpolator — framework; API 22+ (1 +, 0 *, 0 ~, 0 -)

```
+ BaseInterpolator()  [API 22+]
```

## BounceInterpolator — framework; API 4+ (2 +, 0 *, 0 ~, 1 -)

```
+ BounceInterpolator()  [API 4+]
- BounceInterpolator(Context, AttributeSet)  [API 4+]
+ getInterpolation(float): float  [API 4+]
```

## CycleInterpolator — framework; API 1+ (2 +, 0 *, 0 ~, 1 -)

```
- CycleInterpolator(Context, AttributeSet)  [API 1+]
+ CycleInterpolator(float)  [API 1+]
+ getInterpolation(float): float  [API 1+]
```

## DecelerateInterpolator — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ DecelerateInterpolator()  [API 1+]
+ DecelerateInterpolator(Context, AttributeSet)  [API 1+]
+ DecelerateInterpolator(float)  [API 1+]
+ getInterpolation(float): float  [API 1+]
```

## GridLayoutAnimationController — framework; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ GridLayoutAnimationController(Animation)  [API 1+]
+ GridLayoutAnimationController(Animation, float, float)  [API 1+]
+ GridLayoutAnimationController(Context, AttributeSet)  [API 1+]
+ getColumnDelay(): float  [API 1+]
+ getDelayForView(View): long  [API 1+]
+ getDirection(): int  [API 1+]
+ getDirectionPriority(): int  [API 1+]
+ getRowDelay(): float  [API 1+]
+ setColumnDelay(float): void  [API 1+]
+ setDirection(int): void  [API 1+]
+ setDirectionPriority(int): void  [API 1+]
+ setRowDelay(float): void  [API 1+]
+ willOverlap(): boolean  [API 1+]
```

## GridLayoutAnimationController.AnimationParameters — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ AnimationParameters()  [API 1+]
+ column [field]  [API 1+]
+ columnsCount [field]  [API 1+]
+ row [field]  [API 1+]
+ rowsCount [field]  [API 1+]
```

## Interpolator — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getInterpolation(float): float  [API 1-10, removed in 11]
```

## LayoutAnimationController — framework; API 1+ (19 +, 0 *, 0 ~, 0 -)

```
+ LayoutAnimationController(Animation)  [API 1+]
+ LayoutAnimationController(Animation, float)  [API 1+]
+ LayoutAnimationController(Context, AttributeSet)  [API 1+]
+ getAnimation(): Animation  [API 1+]
+ getAnimationForView(View): Animation  [API 1+]
+ getDelay(): float  [API 1+]
+ getDelayForView(View): long  [API 1+]
+ getInterpolator(): Interpolator  [API 1+]
+ getOrder(): int  [API 1+]
+ getTransformedIndex(LayoutAnimationController.AnimationParameters): int  [API 1+]
+ isDone(): boolean  [API 1+]
+ mAnimation [field]  [API 1+]
+ mInterpolator [field]  [API 1+]
+ mRandomizer [field]  [API 1+]
+ setAnimation(Animation): void  [API 1+]
+ setAnimation(Context, int): void  [API 1+]
+ setDelay(float): void  [API 1+]
+ setInterpolator(Context, int): void  [API 1+]
+ setInterpolator(Interpolator): void  [API 1+]
+ setOrder(int): void  [API 1+]
+ start(): void  [API 1+]
+ willOverlap(): boolean  [API 1+]
```

## LayoutAnimationController.AnimationParameters — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ AnimationParameters()  [API 1+]
+ count [field]  [API 1+]
+ index [field]  [API 1+]
```

## LinearInterpolator — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ LinearInterpolator()  [API 1+]
+ LinearInterpolator(Context, AttributeSet)  [API 1+]
+ getInterpolation(float): float  [API 1+]
```

## OvershootInterpolator — framework; API 4+ (4 +, 0 *, 0 ~, 0 -)

```
+ OvershootInterpolator()  [API 4+]
+ OvershootInterpolator(Context, AttributeSet)  [API 4+]
+ OvershootInterpolator(float)  [API 4+]
+ getInterpolation(float): float  [API 4+]
```

## PathInterpolator — framework; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ PathInterpolator(Context, AttributeSet)  [API 21+]
+ PathInterpolator(Path)  [API 21+]
+ PathInterpolator(float, float)  [API 21+]
+ PathInterpolator(float, float, float, float)  [API 21+]
+ getInterpolation(float): float  [API 21+]
```

## RotateAnimation — framework; API 1+ (5 +, 0 *, 0 ~, 1 -)

```
- RotateAnimation(Context, AttributeSet)  [API 1+]
+ RotateAnimation(float, float)  [API 1+]
+ RotateAnimation(float, float, float, float)  [API 1+]
+ RotateAnimation(float, float, int, float, int, float)  [API 1+]
+ applyTransformation(float, Transformation): void  [API 1+]
+ initialize(int, int, int, int): void  [API 1+]
```

## ScaleAnimation — framework; API 1+ (5 +, 0 *, 0 ~, 1 -)

```
- ScaleAnimation(Context, AttributeSet)  [API 1+]
+ ScaleAnimation(float, float, float, float)  [API 1+]
+ ScaleAnimation(float, float, float, float, float, float)  [API 1+]
+ ScaleAnimation(float, float, float, float, int, float, int, float)  [API 1+]
+ applyTransformation(float, Transformation): void  [API 1+]
+ initialize(int, int, int, int): void  [API 1+]
```

## Transformation — framework; API 1+ (10 +, 0 *, 0 ~, 1 -)

```
+ Transformation()  [API 1+]
+ clear(): void  [API 1+]
+ compose(Transformation): void  [API 1+]
+ getAlpha(): float  [API 1+]
+ getMatrix(): Matrix  [API 1+]
+ getTransformationType(): int  [API 1+]
+ mAlpha [field]  [API 1+]
+ mMatrix [field]  [API 1+]
+ mTransformationType [field]  [API 1+]
+ set(Transformation): void  [API 1+]
+ setAlpha(float): void  [API 1+]
+ setTransformationType(int): void  [API 1+]
- toShortString(): String  [API 3+]
+ toString(): String  [API 1+]
```

## TranslateAnimation — framework; API 1+ (4 +, 0 *, 0 ~, 1 -)

```
- TranslateAnimation(Context, AttributeSet)  [API 1+]
+ TranslateAnimation(float, float, float, float)  [API 1+]
+ TranslateAnimation(int, float, int, float, int, float, int, float)  [API 1+]
+ applyTransformation(float, Transformation): void  [API 1+]
+ initialize(int, int, int, int): void  [API 1+]
```


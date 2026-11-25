# android.animation

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Animator — framework; API 11+ (19 +, 0 *, 7 ~, 0 -)

```
+ Animator()  [API 11+]
+ addListener(Animator.AnimatorListener): void  [API 11+]
+ addPauseListener(Animator.AnimatorPauseListener): void  [API 19+]
~ cancel(): void  [API 11+]
+ clone(): Animator  [API 11+]
~ end(): void  [API 11+]
+ getDuration(): long  [API 11+]
~ getInterpolator(): TimeInterpolator  [API 18+]
+ getListeners(): ArrayList  [API 11+]
+ getStartDelay(): long  [API 11+]
+ getTotalDuration(): long  [API 24+]
+ isPaused(): boolean  [API 19+]
+ isRunning(): boolean  [API 11+]
+ isStarted(): boolean  [API 14+]
+ pause(): void  [API 19+]
+ removeAllListeners(): void  [API 11+]
+ removeListener(Animator.AnimatorListener): void  [API 11+]
+ removePauseListener(Animator.AnimatorPauseListener): void  [API 19+]
+ resume(): void  [API 19+]
+ setDuration(long): Animator  [API 11+]
+ setInterpolator(TimeInterpolator): void  [API 11+]
+ setStartDelay(long): void  [API 11+]
~ setTarget(Object): void  [API 11+]
~ setupEndValues(): void  [API 11+]
~ setupStartValues(): void  [API 11+]
~ start(): void  [API 11+]
```

## Animator.AnimatorListener — framework; API 11+ (6 +, 0 *, 0 ~, 0 -)

```
+ onAnimationCancel(Animator): void  [API 11+]
+ onAnimationEnd(Animator): void  [API 11+]
+ onAnimationEnd(Animator, boolean): void  [API 26+]
+ onAnimationRepeat(Animator): void  [API 11+]
+ onAnimationStart(Animator): void  [API 11+]
+ onAnimationStart(Animator, boolean): void  [API 26+]
```

## Animator.AnimatorPauseListener — framework; API 19+ (2 +, 0 *, 0 ~, 0 -)

```
+ onAnimationPause(Animator): void  [API 19+]
+ onAnimationResume(Animator): void  [API 19+]
```

## AnimatorInflater — framework; API 11+ (3 +, 0 *, 0 ~, 0 -)

```
+ AnimatorInflater()  [API 11+]
+ loadAnimator(Context, int): Animator [static]  [API 11+]
+ loadStateListAnimator(Context, int): StateListAnimator [static]  [API 21+]
```

## AnimatorListenerAdapter — framework; API 11+ (7 +, 0 *, 0 ~, 0 -)

```
+ AnimatorListenerAdapter()  [API 11+]
+ onAnimationCancel(Animator): void  [API 11+]
+ onAnimationEnd(Animator): void  [API 11+]
+ onAnimationPause(Animator): void  [API 11+]
+ onAnimationRepeat(Animator): void  [API 11+]
+ onAnimationResume(Animator): void  [API 11+]
+ onAnimationStart(Animator): void  [API 11+]
```

## AnimatorSet — framework; API 11+ (26 +, 0 *, 1 ~, 2 -)

```
+ AnimatorSet()  [API 11+]
+ cancel(): void  [API 11+]
+ clone(): AnimatorSet  [API 11+]
+ end(): void  [API 11+]
+ getChildAnimations(): ArrayList  [API 11+]
- getCurrentPlayTime(): long  [API 26+]
+ getDuration(): long  [API 11+]
+ getInterpolator(): TimeInterpolator  [API 11+]
+ getStartDelay(): long  [API 11+]
+ getTotalDuration(): long  [API 11+]
+ isRunning(): boolean  [API 11+]
+ isStarted(): boolean  [API 11+]
+ pause(): void  [API 11+]
+ play(Animator): AnimatorSet.Builder  [API 11+]
+ playSequentially(Animator[]): void  [API 11+]
+ playSequentially(List): void  [API 11+]
+ playTogether(Animator[]): void  [API 11+]
+ playTogether(Collection): void  [API 11+]
+ resume(): void  [API 11+]
~ reverse(): void  [API 26+]
- setCurrentPlayTime(long): void  [API 26+]
+ setDuration(long): AnimatorSet  [API 11+]
+ setInterpolator(TimeInterpolator): void  [API 11+]
+ setStartDelay(long): void  [API 11+]
+ setTarget(Object): void  [API 11+]
+ setupEndValues(): void  [API 11+]
+ setupStartValues(): void  [API 11+]
+ start(): void  [API 11+]
+ toString(): String  [API 11+]
```

## AnimatorSet.Builder — framework; API 11+ (4 +, 0 *, 0 ~, 0 -)

```
+ after(Animator): AnimatorSet.Builder  [API 11+]
+ after(long): AnimatorSet.Builder  [API 11+]
+ before(Animator): AnimatorSet.Builder  [API 11+]
+ with(Animator): AnimatorSet.Builder  [API 11+]
```

## ArgbEvaluator — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ ArgbEvaluator()  [API 11+]
+ evaluate(float, Object, Object): Object  [API 11+]
```

## BidirectionalTypeConverter — framework; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ BidirectionalTypeConverter(Class, Class)  [API 21+]
+ convertBack(Object): Object  [API 21+]
+ invert(): BidirectionalTypeConverter  [API 21+]
```

## FloatArrayEvaluator — framework; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ FloatArrayEvaluator()  [API 21+]
+ FloatArrayEvaluator(float[])  [API 21+]
+ evaluate(float, float[], float[]): float[]  [API 21+]
```

## FloatEvaluator — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ FloatEvaluator()  [API 11+]
+ evaluate(float, Number, Number): Float  [API 14+]
```

## IntArrayEvaluator — framework; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ IntArrayEvaluator()  [API 21+]
+ IntArrayEvaluator(int[])  [API 21+]
+ evaluate(float, int[], int[]): int[]  [API 21+]
```

## IntEvaluator — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ IntEvaluator()  [API 11+]
+ evaluate(float, Integer, Integer): Integer  [API 14+]
```

## Keyframe — framework; API 11+ (16 +, 0 *, 0 ~, 0 -)

```
+ Keyframe()  [API 11+]
+ clone(): Keyframe  [API 11+]
+ getFraction(): float  [API 11+]
+ getInterpolator(): TimeInterpolator  [API 11+]
+ getType(): Class  [API 11+]
+ getValue(): Object  [API 11+]
+ hasValue(): boolean  [API 11+]
+ ofFloat(float): Keyframe [static]  [API 11+]
+ ofFloat(float, float): Keyframe [static]  [API 11+]
+ ofInt(float): Keyframe [static]  [API 11+]
+ ofInt(float, int): Keyframe [static]  [API 11+]
+ ofObject(float): Keyframe [static]  [API 11+]
+ ofObject(float, Object): Keyframe [static]  [API 11+]
+ setFraction(float): void  [API 11+]
+ setInterpolator(TimeInterpolator): void  [API 11+]
+ setValue(Object): void  [API 11+]
```

## LayoutTransition — framework; API 11+ (9 +, 0 *, 16 ~, 2 -)

```
+ LayoutTransition()  [API 11+]
~ addChild(ViewGroup, View): void  [API 11+]
+ addTransitionListener(LayoutTransition.TransitionListener): void  [API 11+]
~ disableTransitionType(int): void  [API 16+]
~ enableTransitionType(int): void  [API 16+]
+ getAnimator(int): Animator  [API 11+]
+ getDuration(int): long  [API 11+]
~ getInterpolator(int): TimeInterpolator  [API 11+]
~ getStagger(int): long  [API 11+]
~ getStartDelay(int): long  [API 11+]
+ getTransitionListeners(): List  [API 11+]
~ hideChild(ViewGroup, View): void  [API 11+, deprecated in 16]
- hideChild(ViewGroup, View, int): void  [API 16+]
~ isChangingLayout(): boolean  [API 11+]
~ isRunning(): boolean  [API 11+]
~ isTransitionTypeEnabled(int): boolean  [API 16+]
~ removeChild(ViewGroup, View): void  [API 11+]
+ removeTransitionListener(LayoutTransition.TransitionListener): void  [API 11+]
~ setAnimateParentHierarchy(boolean): void  [API 14+]
+ setAnimator(int, Animator): void  [API 11+]
+ setDuration(int, long): void  [API 11+]
+ setDuration(long): void  [API 11+]
~ setInterpolator(int, TimeInterpolator): void  [API 11+]
~ setStagger(int, long): void  [API 11+]
~ setStartDelay(int, long): void  [API 11+]
~ showChild(ViewGroup, View): void  [API 11+, deprecated in 16]
- showChild(ViewGroup, View, int): void  [API 16+]
```

## LayoutTransition.TransitionListener — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ endTransition(LayoutTransition, ViewGroup, View, int): void  [API 11+]
+ startTransition(LayoutTransition, ViewGroup, View, int): void  [API 11+]
```

## ObjectAnimator — framework; API 11+ (25 +, 0 *, 1 ~, 12 -)

```
+ ObjectAnimator()  [API 11+]
+ clone(): ObjectAnimator  [API 11+]
+ getPropertyName(): String  [API 11+]
+ getTarget(): Object  [API 11+]
+ ofArgb(Object, Property, int[]): ObjectAnimator [static]  [API 21+]
+ ofArgb(Object, String, int[]): ObjectAnimator [static]  [API 21+]
- ofFloat(Object, Property, Property, Path): ObjectAnimator [static]  [API 21+]
+ ofFloat(Object, Property, float[]): ObjectAnimator [static]  [API 14+]
- ofFloat(Object, String, String, Path): ObjectAnimator [static]  [API 21+]
+ ofFloat(Object, String, float[]): ObjectAnimator [static]  [API 11+]
- ofInt(Object, Property, Property, Path): ObjectAnimator [static]  [API 21+]
+ ofInt(Object, Property, int[]): ObjectAnimator [static]  [API 14+]
+ ofInt(Object, String, String, Path): ObjectAnimator [static]  [API 21+]
+ ofInt(Object, String, int[]): ObjectAnimator [static]  [API 11+]
- ofMultiFloat(Object, String, Path): ObjectAnimator [static]  [API 21+]
- ofMultiFloat(Object, String, TypeConverter, TypeEvaluator, Object[]): ObjectAnimator [static]  [API 21+]
- ofMultiFloat(Object, String, float[][]): ObjectAnimator [static]  [API 21+]
- ofMultiInt(Object, String, Path): ObjectAnimator [static]  [API 21+]
- ofMultiInt(Object, String, TypeConverter, TypeEvaluator, Object[]): ObjectAnimator [static]  [API 21+]
- ofMultiInt(Object, String, int[][]): ObjectAnimator [static]  [API 21+]
- ofObject(Object, Property, TypeConverter, Path): ObjectAnimator [static]  [API 21+]
- ofObject(Object, Property, TypeConverter, TypeEvaluator, Object[]): ObjectAnimator [static]  [API 21+]
+ ofObject(Object, Property, TypeEvaluator, Object[]): ObjectAnimator [static]  [API 14+]
- ofObject(Object, String, TypeConverter, Path): ObjectAnimator [static]  [API 21+]
+ ofObject(Object, String, TypeEvaluator, Object[]): ObjectAnimator [static]  [API 11+]
+ ofPropertyValuesHolder(Object, PropertyValuesHolder[]): ObjectAnimator [static]  [API 11+]
+ setAutoCancel(boolean): void  [API 18+]
+ setDuration(long): ObjectAnimator  [API 11+]
+ setFloatValues(float[]): void  [API 11+]
+ setIntValues(int[]): void  [API 11+]
+ setObjectValues(Object[]): void  [API 11+]
+ setProperty(Property): void  [API 14+]
+ setPropertyName(String): void  [API 11+]
+ setTarget(Object): void  [API 11+]
~ setupEndValues(): void  [API 11+]
+ setupStartValues(): void  [API 11+]
+ start(): void  [API 11+]
+ toString(): String  [API 11+]
```

## PointFEvaluator — framework; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ PointFEvaluator()  [API 21+]
+ PointFEvaluator(PointF)  [API 21+]
+ evaluate(float, PointF, PointF): PointF  [API 21+]
```

## PropertyValuesHolder — framework; API 11+ (18 +, 0 *, 0 ~, 12 -)

```
+ clone(): PropertyValuesHolder  [API 11+]
+ getPropertyName(): String  [API 11+]
+ ofFloat(Property, float[]): PropertyValuesHolder [static]  [API 14+]
+ ofFloat(String, float[]): PropertyValuesHolder [static]  [API 11+]
+ ofInt(Property, int[]): PropertyValuesHolder [static]  [API 14+]
+ ofInt(String, int[]): PropertyValuesHolder [static]  [API 11+]
- ofKeyframe(Property, Keyframe[]): PropertyValuesHolder [static]  [API 14+]
+ ofKeyframe(String, Keyframe[]): PropertyValuesHolder [static]  [API 11+]
- ofMultiFloat(String, Path): PropertyValuesHolder [static]  [API 21+]
- ofMultiFloat(String, TypeConverter, TypeEvaluator, Keyframe[]): PropertyValuesHolder [static]  [API 21+]
- ofMultiFloat(String, TypeConverter, TypeEvaluator, Object[]): PropertyValuesHolder [static]  [API 21+]
+ ofMultiFloat(String, float[][]): PropertyValuesHolder [static]  [API 21+]
- ofMultiInt(String, Path): PropertyValuesHolder [static]  [API 21+]
- ofMultiInt(String, TypeConverter, TypeEvaluator, Keyframe[]): PropertyValuesHolder [static]  [API 21+]
- ofMultiInt(String, TypeConverter, TypeEvaluator, Object[]): PropertyValuesHolder [static]  [API 21+]
+ ofMultiInt(String, int[][]): PropertyValuesHolder [static]  [API 21+]
- ofObject(Property, TypeConverter, Path): PropertyValuesHolder [static]  [API 21+]
- ofObject(Property, TypeConverter, TypeEvaluator, Object[]): PropertyValuesHolder [static]  [API 21+]
+ ofObject(Property, TypeEvaluator, Object[]): PropertyValuesHolder [static]  [API 14+]
- ofObject(String, TypeConverter, Path): PropertyValuesHolder [static]  [API 21+]
+ ofObject(String, TypeEvaluator, Object[]): PropertyValuesHolder [static]  [API 11+]
- setConverter(TypeConverter): void  [API 21+]
+ setEvaluator(TypeEvaluator): void  [API 11+]
+ setFloatValues(float[]): void  [API 11+]
+ setIntValues(int[]): void  [API 11+]
- setKeyframes(Keyframe[]): void  [API 11+]
+ setObjectValues(Object[]): void  [API 11+]
+ setProperty(Property): void  [API 14+]
+ setPropertyName(String): void  [API 11+]
+ toString(): String  [API 11+]
```

## RectEvaluator — framework; API 18+ (3 +, 0 *, 0 ~, 0 -)

```
+ RectEvaluator()  [API 18+]
+ RectEvaluator(Rect)  [API 21+]
+ evaluate(float, Rect, Rect): Rect  [API 18+]
```

## StateListAnimator — framework; API 21+ (2 +, 0 *, 2 ~, 0 -)

```
+ StateListAnimator()  [API 21+]
~ addState(int[], Animator): void  [API 21+]
+ clone(): StateListAnimator  [API 22+]
~ jumpToCurrentState(): void  [API 21+]
```

## TimeAnimator — framework; API 16+ (4 +, 0 *, 0 ~, 0 -)

```
+ TimeAnimator()  [API 16+]
+ setCurrentPlayTime(long): void  [API 16+]
+ setTimeListener(TimeAnimator.TimeListener): void  [API 16+]
+ start(): void  [API 16+]
```

## TimeAnimator.TimeListener — framework; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTimeUpdate(TimeAnimator, long, long): void  [API 16+]
```

## TimeInterpolator — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ getInterpolation(float): float  [API 11+]
```

## TypeConverter — framework; API 21+ (2 +, 0 *, 0 ~, 0 -)

```
+ TypeConverter(Class, Class)  [API 21+]
+ convert(Object): Object  [API 21+]
```

## TypeEvaluator — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ evaluate(float, Object, Object): Object  [API 11+]
```

## ValueAnimator — framework; API 11+ (46 +, 0 *, 0 ~, 2 -)

```
+ ValueAnimator()  [API 11+]
+ addUpdateListener(ValueAnimator.AnimatorUpdateListener): void  [API 11+]
+ areAnimatorsEnabled(): boolean [static]  [API 26+]
+ cancel(): void  [API 11+]
+ clone(): ValueAnimator  [API 11+]
+ end(): void  [API 11+]
+ getAnimatedFraction(): float  [API 12+]
+ getAnimatedValue(): Object  [API 11+]
+ getAnimatedValue(String): Object  [API 11+]
+ getCurrentPlayTime(): long  [API 11+]
+ getDuration(): long  [API 11+]
+ getDurationScale(): float [static]  [API 33+]
+ getFrameDelay(): long [static]  [API 11+]
+ getInterpolator(): TimeInterpolator  [API 11+]
+ getRepeatCount(): int  [API 11+]
+ getRepeatMode(): int  [API 11+]
+ getStartDelay(): long  [API 11+]
+ getTotalDuration(): long  [API 11+]
+ getValues(): PropertyValuesHolder[]  [API 11+]
+ isRunning(): boolean  [API 11+]
+ isStarted(): boolean  [API 11+]
+ ofArgb(int[]): ValueAnimator [static]  [API 21+]
+ ofFloat(float[]): ValueAnimator [static]  [API 11+]
+ ofInt(int[]): ValueAnimator [static]  [API 11+]
+ ofObject(TypeEvaluator, Object[]): ValueAnimator [static]  [API 11+]
+ ofPropertyValuesHolder(PropertyValuesHolder[]): ValueAnimator [static]  [API 11+]
+ pause(): void  [API 11+]
- registerDurationScaleChangeListener(ValueAnimator.DurationScaleChangeListener): boolean [static]  [API 33+]
+ removeAllUpdateListeners(): void  [API 11+]
+ removeUpdateListener(ValueAnimator.AnimatorUpdateListener): void  [API 11+]
+ resume(): void  [API 11+]
+ reverse(): void  [API 11+]
+ setCurrentFraction(float): void  [API 22+]
+ setCurrentPlayTime(long): void  [API 11+]
+ setDuration(long): ValueAnimator  [API 11+]
+ setEvaluator(TypeEvaluator): void  [API 11+]
+ setFloatValues(float[]): void  [API 11+]
+ setFrameDelay(long): void [static]  [API 11+]
+ setIntValues(int[]): void  [API 11+]
+ setInterpolator(TimeInterpolator): void  [API 11+]
+ setObjectValues(Object[]): void  [API 11+]
+ setRepeatCount(int): void  [API 11+]
+ setRepeatMode(int): void  [API 11+]
+ setStartDelay(long): void  [API 11+]
+ setValues(PropertyValuesHolder[]): void  [API 11+]
+ start(): void  [API 11+]
+ toString(): String  [API 11+]
- unregisterDurationScaleChangeListener(ValueAnimator.DurationScaleChangeListener): boolean [static]  [API 33+]
```

## ValueAnimator.AnimatorUpdateListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onAnimationUpdate(ValueAnimator): void  [API 11+]
```

## ValueAnimator.DurationScaleChangeListener — declaration; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ onChanged(float): void  [API 33+]
```


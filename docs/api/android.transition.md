# android.transition

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ArcMotion — declaration; API 21+ (0 +, 7 *, 0 ~, 2 -)

```
* ArcMotion()  [API 21+]
- ArcMotion(Context, AttributeSet)  [API 21+]
* getMaximumAngle(): float  [API 21+]
* getMinimumHorizontalAngle(): float  [API 21+]
* getMinimumVerticalAngle(): float  [API 21+]
- getPath(float, float, float, float): Path  [API 21+]
* setMaximumAngle(float): void  [API 21+]
* setMinimumHorizontalAngle(float): void  [API 21+]
* setMinimumVerticalAngle(float): void  [API 21+]
```

## AutoTransition — declaration; API 19+ (1 +, 0 *, 0 ~, 1 -)

```
+ AutoTransition()  [API 19+]
- AutoTransition(Context, AttributeSet)  [API 21+]
```

## ChangeBounds — declaration; API 19+ (0 +, 0 *, 0 ~, 9 -)

```
- ChangeBounds()  [API 19+]
- ChangeBounds(Context, AttributeSet)  [API 21+]
- captureEndValues(TransitionValues): void  [API 19+]
- captureStartValues(TransitionValues): void  [API 19+]
- createAnimator(ViewGroup, TransitionValues, TransitionValues): Animator  [API 19+]
- getResizeClip(): boolean  [API 22+]
- getTransitionProperties(): String[]  [API 19+]
- setReparent(boolean): void  [API 19+, deprecated in 21]
- setResizeClip(boolean): void  [API 19+]
```

## ChangeClipBounds — declaration; API 21+ (0 +, 0 *, 0 ~, 6 -)

```
- ChangeClipBounds()  [API 21+]
- ChangeClipBounds(Context, AttributeSet)  [API 21+]
- captureEndValues(TransitionValues): void  [API 21+]
- captureStartValues(TransitionValues): void  [API 21+]
- createAnimator(ViewGroup, TransitionValues, TransitionValues): Animator  [API 21+]
- getTransitionProperties(): String[]  [API 21+]
```

## ChangeImageTransform — declaration; API 21+ (0 +, 0 *, 0 ~, 6 -)

```
- ChangeImageTransform()  [API 21+]
- ChangeImageTransform(Context, AttributeSet)  [API 21+]
- captureEndValues(TransitionValues): void  [API 21+]
- captureStartValues(TransitionValues): void  [API 21+]
- createAnimator(ViewGroup, TransitionValues, TransitionValues): Animator  [API 21+]
- getTransitionProperties(): String[]  [API 21+]
```

## ChangeScroll — declaration; API 23+ (0 +, 0 *, 0 ~, 6 -)

```
- ChangeScroll()  [API 23+]
- ChangeScroll(Context, AttributeSet)  [API 23+]
- captureEndValues(TransitionValues): void  [API 23+]
- captureStartValues(TransitionValues): void  [API 23+]
- createAnimator(ViewGroup, TransitionValues, TransitionValues): Animator  [API 23+]
- getTransitionProperties(): String[]  [API 23+]
```

## ChangeTransform — declaration; API 21+ (0 +, 0 *, 0 ~, 10 -)

```
- ChangeTransform()  [API 21+]
- ChangeTransform(Context, AttributeSet)  [API 21+]
- captureEndValues(TransitionValues): void  [API 21+]
- captureStartValues(TransitionValues): void  [API 21+]
- createAnimator(ViewGroup, TransitionValues, TransitionValues): Animator  [API 21+]
- getReparent(): boolean  [API 21+]
- getReparentWithOverlay(): boolean  [API 21+]
- getTransitionProperties(): String[]  [API 21+]
- setReparent(boolean): void  [API 21+]
- setReparentWithOverlay(boolean): void  [API 21+]
```

## CircularPropagation — declaration; API 21+ (0 +, 0 *, 0 ~, 3 -)

```
- CircularPropagation()  [API 21+]
- getStartDelay(ViewGroup, Transition, TransitionValues, TransitionValues): long  [API 21+]
- setPropagationSpeed(float): void  [API 21+]
```

## Explode — declaration; API 21+ (0 +, 0 *, 0 ~, 6 -)

```
- Explode()  [API 21+]
- Explode(Context, AttributeSet)  [API 21+]
- captureEndValues(TransitionValues): void  [API 21+]
- captureStartValues(TransitionValues): void  [API 21+]
- onAppear(ViewGroup, View, TransitionValues, TransitionValues): Animator  [API 21+]
- onDisappear(ViewGroup, View, TransitionValues, TransitionValues): Animator  [API 21+]
```

## Fade — declaration; API 19+ (0 +, 0 *, 0 ~, 6 -)

```
- Fade()  [API 19+]
- Fade(Context, AttributeSet)  [API 21+]
- Fade(int)  [API 19+]
- captureStartValues(TransitionValues): void  [API 19+]
- onAppear(ViewGroup, View, TransitionValues, TransitionValues): Animator  [API 19+]
- onDisappear(ViewGroup, View, TransitionValues, TransitionValues): Animator  [API 19+]
```

## PathMotion — declaration; API 21+ (2 +, 0 *, 0 ~, 1 -)

```
+ PathMotion()  [API 21+]
- PathMotion(Context, AttributeSet)  [API 21+]
+ getPath(float, float, float, float): Path  [API 21+]
```

## PatternPathMotion — declaration; API 21+ (0 +, 4 *, 0 ~, 2 -)

```
* PatternPathMotion()  [API 21+]
- PatternPathMotion(Context, AttributeSet)  [API 21+]
* PatternPathMotion(Path)  [API 21+]
- getPath(float, float, float, float): Path  [API 21+]
* getPatternPath(): Path  [API 21+]
* setPatternPath(Path): void  [API 21+]
```

## Scene — declaration; API 19+ (0 +, 2 *, 0 ~, 8 -)

```
* Scene(ViewGroup)  [API 19+]
- Scene(ViewGroup, View)  [API 21+]
- Scene(ViewGroup, ViewGroup)  [API 19+, deprecated in 21]
- enter(): void  [API 19+]
- exit(): void  [API 19+]
- getCurrentScene(ViewGroup): Scene [static]  [API 29+]
- getSceneForLayout(ViewGroup, int, Context): Scene [static]  [API 19+]
* getSceneRoot(): ViewGroup  [API 19+]
- setEnterAction(Runnable): void  [API 19+]
- setExitAction(Runnable): void  [API 19+]
```

## SidePropagation — declaration; API 21+ (0 +, 0 *, 0 ~, 4 -)

```
- SidePropagation()  [API 21+]
- getStartDelay(ViewGroup, Transition, TransitionValues, TransitionValues): long  [API 21+]
- setPropagationSpeed(float): void  [API 21+]
- setSide(int): void  [API 21+]
```

## Slide — declaration; API 21+ (0 +, 0 *, 0 ~, 9 -)

```
- Slide()  [API 21+]
- Slide(Context, AttributeSet)  [API 21+]
- Slide(int)  [API 21+]
- captureEndValues(TransitionValues): void  [API 21+]
- captureStartValues(TransitionValues): void  [API 21+]
- getSlideEdge(): int  [API 21+]
- onAppear(ViewGroup, View, TransitionValues, TransitionValues): Animator  [API 21+]
- onDisappear(ViewGroup, View, TransitionValues, TransitionValues): Animator  [API 21+]
- setSlideEdge(int): void  [API 21+]
```

## Transition — framework; API 19+ (3 +, 0 *, 0 ~, 44 -)

```
+ Transition()  [API 19+]
- Transition(Context, AttributeSet)  [API 21+]
- addListener(Transition.TransitionListener): Transition  [API 19+]
- addTarget(Class): Transition  [API 21+]
- addTarget(String): Transition  [API 21+]
- addTarget(View): Transition  [API 19+]
- addTarget(int): Transition  [API 19+]
- canRemoveViews(): boolean  [API 21+]
- captureEndValues(TransitionValues): void  [API 19+]
- captureStartValues(TransitionValues): void  [API 19+]
+ clone(): Transition  [API 19+]
- createAnimator(ViewGroup, TransitionValues, TransitionValues): Animator  [API 19+]
- excludeChildren(Class, boolean): Transition  [API 19+]
- excludeChildren(View, boolean): Transition  [API 19+]
- excludeChildren(int, boolean): Transition  [API 19+]
- excludeTarget(Class, boolean): Transition  [API 19+]
- excludeTarget(String, boolean): Transition  [API 21+]
- excludeTarget(View, boolean): Transition  [API 19+]
- excludeTarget(int, boolean): Transition  [API 19+]
- getDuration(): long  [API 19+]
- getEpicenter(): Rect  [API 21+]
- getEpicenterCallback(): Transition.EpicenterCallback  [API 21+]
- getInterpolator(): TimeInterpolator  [API 19+]
- getName(): String  [API 19+]
- getPathMotion(): PathMotion  [API 21+]
- getPropagation(): TransitionPropagation  [API 21+]
- getStartDelay(): long  [API 19+]
- getTargetIds(): List  [API 19+]
- getTargetNames(): List  [API 21+]
- getTargetTypes(): List  [API 21+]
- getTargets(): List  [API 19+]
- getTransitionProperties(): String[]  [API 19+]
- getTransitionValues(View, boolean): TransitionValues  [API 19+]
- isTransitionRequired(TransitionValues, TransitionValues): boolean  [API 23+]
- removeListener(Transition.TransitionListener): Transition  [API 19+]
- removeTarget(Class): Transition  [API 21+]
- removeTarget(String): Transition  [API 21+]
- removeTarget(View): Transition  [API 19+]
- removeTarget(int): Transition  [API 19+]
- setDuration(long): Transition  [API 19+]
- setEpicenterCallback(Transition.EpicenterCallback): void  [API 21+]
- setInterpolator(TimeInterpolator): Transition  [API 19+]
- setMatchOrder(int[]): void  [API 21+]
- setPathMotion(PathMotion): void  [API 21+]
- setPropagation(TransitionPropagation): void  [API 21+]
- setStartDelay(long): Transition  [API 19+]
+ toString(): String  [API 19+]
```

## Transition.EpicenterCallback — declaration; API 21+ (2 +, 0 *, 0 ~, 0 -)

```
+ EpicenterCallback()  [API 21+]
+ onGetEpicenter(Transition): Rect  [API 21+]
```

## Transition.TransitionListener — declaration; API 19+ (5 +, 0 *, 0 ~, 0 -)

```
+ onTransitionCancel(Transition): void  [API 19+]
+ onTransitionEnd(Transition): void  [API 19+]
+ onTransitionPause(Transition): void  [API 19+]
+ onTransitionResume(Transition): void  [API 19+]
+ onTransitionStart(Transition): void  [API 19+]
```

## TransitionInflater — declaration; API 19+ (0 +, 0 *, 0 ~, 3 -)

```
- from(Context): TransitionInflater [static]  [API 19+]
- inflateTransition(int): Transition  [API 19+]
- inflateTransitionManager(int, ViewGroup): TransitionManager  [API 19+]
```

## TransitionListenerAdapter — declaration; API 26+ (6 +, 0 *, 0 ~, 0 -)

```
+ TransitionListenerAdapter()  [API 26+]
+ onTransitionCancel(Transition): void  [API 26+]
+ onTransitionEnd(Transition): void  [API 26+]
+ onTransitionPause(Transition): void  [API 26+]
+ onTransitionResume(Transition): void  [API 26+]
+ onTransitionStart(Transition): void  [API 26+]
```

## TransitionManager — declaration; API 19+ (0 +, 0 *, 0 ~, 9 -)

```
- TransitionManager()  [API 19+]
- beginDelayedTransition(ViewGroup): void [static]  [API 19+]
- beginDelayedTransition(ViewGroup, Transition): void [static]  [API 19+]
- endTransitions(ViewGroup): void [static]  [API 23+]
- go(Scene): void [static]  [API 19+]
- go(Scene, Transition): void [static]  [API 19+]
- setTransition(Scene, Scene, Transition): void  [API 19+]
- setTransition(Scene, Transition): void  [API 19+]
- transitionTo(Scene): void  [API 19+]
```

## TransitionPropagation — declaration; API 21+ (4 +, 0 *, 0 ~, 0 -)

```
+ TransitionPropagation()  [API 21+]
+ captureValues(TransitionValues): void  [API 21+]
+ getPropagationProperties(): String[]  [API 21+]
+ getStartDelay(ViewGroup, Transition, TransitionValues, TransitionValues): long  [API 21+]
```

## TransitionSet — declaration; API 19+ (0 +, 0 *, 0 ~, 31 -)

```
- TransitionSet()  [API 19+]
- TransitionSet(Context, AttributeSet)  [API 21+]
- addListener(Transition.TransitionListener): TransitionSet  [API 19+]
- addTarget(Class): TransitionSet  [API 21+]
- addTarget(String): TransitionSet  [API 21+]
- addTarget(View): TransitionSet  [API 19+]
- addTarget(int): TransitionSet  [API 19+]
- addTransition(Transition): TransitionSet  [API 19+]
- captureEndValues(TransitionValues): void  [API 19+]
- captureStartValues(TransitionValues): void  [API 19+]
- clone(): TransitionSet  [API 19+]
- excludeTarget(Class, boolean): Transition  [API 19+]
- excludeTarget(String, boolean): Transition  [API 19+]
- excludeTarget(View, boolean): Transition  [API 19+]
- excludeTarget(int, boolean): Transition  [API 19+]
- getOrdering(): int  [API 19+]
- getTransitionAt(int): Transition  [API 21+]
- getTransitionCount(): int  [API 21+]
- removeListener(Transition.TransitionListener): TransitionSet  [API 19+]
- removeTarget(Class): TransitionSet  [API 21+]
- removeTarget(String): TransitionSet  [API 21+]
- removeTarget(View): TransitionSet  [API 19+]
- removeTarget(int): TransitionSet  [API 19+]
- removeTransition(Transition): TransitionSet  [API 19+]
- setDuration(long): TransitionSet  [API 19+]
- setEpicenterCallback(Transition.EpicenterCallback): void  [API 19+]
- setInterpolator(TimeInterpolator): TransitionSet  [API 19+]
- setOrdering(int): TransitionSet  [API 19+]
- setPathMotion(PathMotion): void  [API 19+]
- setPropagation(TransitionPropagation): void  [API 19+]
- setStartDelay(long): TransitionSet  [API 19+]
```

## TransitionValues — declaration; API 19+ (0 +, 1 *, 0 ~, 4 -)

```
- TransitionValues()  [API 19+, deprecated in 29]
* TransitionValues(View)  [API 29+]
- equals(Object): boolean  [API 19+]
- hashCode(): int  [API 19+]
- toString(): String  [API 19+]
- values [field]  [API 19+]
+ view [field]  [API 19+]
```

## Visibility — declaration; API 19+ (0 +, 0 *, 0 ~, 14 -)

```
- Visibility()  [API 19+]
- Visibility(Context, AttributeSet)  [API 21+]
- captureEndValues(TransitionValues): void  [API 19+]
- captureStartValues(TransitionValues): void  [API 19+]
- createAnimator(ViewGroup, TransitionValues, TransitionValues): Animator  [API 19+]
- getMode(): int  [API 21+]
- getTransitionProperties(): String[]  [API 19+]
- isTransitionRequired(TransitionValues, TransitionValues): boolean  [API 19+]
- isVisible(TransitionValues): boolean  [API 19+]
- onAppear(ViewGroup, TransitionValues, int, TransitionValues, int): Animator  [API 19+]
- onAppear(ViewGroup, View, TransitionValues, TransitionValues): Animator  [API 21+]
- onDisappear(ViewGroup, TransitionValues, int, TransitionValues, int): Animator  [API 19+]
- onDisappear(ViewGroup, View, TransitionValues, TransitionValues): Animator  [API 21+]
- setMode(int): void  [API 21+]
```

## VisibilityPropagation — declaration; API 21+ (0 +, 0 *, 0 ~, 6 -)

```
- VisibilityPropagation()  [API 21+]
- captureValues(TransitionValues): void  [API 21+]
- getPropagationProperties(): String[]  [API 21+]
- getViewVisibility(TransitionValues): int  [API 21+]
- getViewX(TransitionValues): int  [API 21+]
- getViewY(TransitionValues): int  [API 21+]
```


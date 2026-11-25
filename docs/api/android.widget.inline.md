# android.widget.inline

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## InlineContentView — declaration; API 30+ (0 +, 0 *, 0 ~, 8 -)

```
- getSurfaceControl(): SurfaceControl  [API 30+]
- isZOrderedOnTop(): boolean  [API 30+]
- onAttachedToWindow(): void  [API 30+]
- onDetachedFromWindow(): void  [API 30+]
- onLayout(boolean, int, int, int, int): void  [API 30+]
- setClipBounds(Rect): void  [API 30+]
- setSurfaceControlCallback(InlineContentView.SurfaceControlCallback): void  [API 30+]
- setZOrderedOnTop(boolean): boolean  [API 30+]
```

## InlineContentView.SurfaceControlCallback — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ onCreated(SurfaceControl): void  [API 30+]
+ onDestroyed(SurfaceControl): void  [API 30+]
```

## InlinePresentationSpec — declaration; API 30+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getMaxSize(): Size  [API 30+]
* getMinSize(): Size  [API 30+]
* getStyle(): Bundle  [API 30+]
* hashCode(): int  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## InlinePresentationSpec.Builder — declaration; API 30+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(Size, Size)  [API 30+]
* build(): InlinePresentationSpec  [API 30+]
* setStyle(Bundle): InlinePresentationSpec.Builder  [API 30+]
```


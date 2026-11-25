# android.graphics.drawable.shapes

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ArcShape — framework; API 1+ (7 +, 0 *, 1 ~, 0 -)

```
+ ArcShape(float, float)  [API 1+]
+ clone(): ArcShape  [API 26+]
+ draw(Canvas, Paint): void  [API 1+]
+ equals(Object): boolean  [API 1+]
~ getOutline(Outline): void  [API 1+]
+ getStartAngle(): float  [API 26+]
+ getSweepAngle(): float  [API 26+]
+ hashCode(): int  [API 1+]
```

## OvalShape — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ OvalShape()  [API 1+]
+ clone(): OvalShape  [API 26+]
+ draw(Canvas, Paint): void  [API 1+]
+ getOutline(Outline): void  [API 1+]
```

## PathShape — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ PathShape(Path, float, float)  [API 1+]
+ clone(): PathShape  [API 3+]
+ draw(Canvas, Paint): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ onResize(float, float): void  [API 1+]
```

## RectShape — framework; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ RectShape()  [API 1+]
+ clone(): RectShape  [API 3+]
+ draw(Canvas, Paint): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getOutline(Outline): void  [API 1+]
+ hashCode(): int  [API 1+]
+ onResize(float, float): void  [API 1+]
+ rect(): RectF  [API 1+]
```

## RoundRectShape — framework; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ RoundRectShape(float[], RectF, float[])  [API 1+]
+ clone(): RoundRectShape  [API 3+]
+ draw(Canvas, Paint): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getOutline(Outline): void  [API 1+]
+ hashCode(): int  [API 1+]
+ onResize(float, float): void  [API 1+]
```

## Shape — framework; API 1+ (9 +, 0 *, 2 ~, 0 -)

```
+ Shape()  [API 1+]
+ clone(): Shape  [API 3+]
+ draw(Canvas, Paint): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getHeight(): float  [API 1+]
~ getOutline(Outline): void  [API 21+]
+ getWidth(): float  [API 1+]
~ hasAlpha(): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ onResize(float, float): void  [API 1+]
+ resize(float, float): void  [API 1+]
```


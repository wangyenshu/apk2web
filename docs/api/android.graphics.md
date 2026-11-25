# android.graphics

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AvoidXfermode — declaration; API 1-23, removed in 24, deprecated in 16 (0 +, 0 *, 0 ~, 1 -)

```
- AvoidXfermode(int, int, AvoidXfermode.Mode)  [API 1-23, removed in 24]
```

## AvoidXfermode.Mode — declaration; API 1-23, removed in 24 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): AvoidXfermode.Mode  [API 1-23, removed in 24]
+ values(): AvoidXfermode.Mode[]  [API 1-23, removed in 24]
```

## Bitmap — framework; API 1+ (66 +, 0 *, 2 ~, 2 -)

```
+ CREATOR [field]  [API 1+]
+ asShared(): Bitmap  [API 31+]
+ compress(Bitmap.CompressFormat, int, OutputStream): boolean  [API 1+]
+ copy(Bitmap.Config, boolean): Bitmap  [API 1+]
+ copyPixelsFromBuffer(Buffer): void  [API 3+]
+ copyPixelsToBuffer(Buffer): void  [API 1+]
+ createBitmap(Bitmap): Bitmap [static]  [API 1+]
+ createBitmap(Bitmap, int, int, int, int): Bitmap [static]  [API 1+]
+ createBitmap(Bitmap, int, int, int, int, Matrix, boolean): Bitmap [static]  [API 1+]
+ createBitmap(DisplayMetrics, int, int, Bitmap.Config): Bitmap [static]  [API 17+]
+ createBitmap(DisplayMetrics, int, int, Bitmap.Config, boolean): Bitmap [static]  [API 26+]
+ createBitmap(DisplayMetrics, int, int, Bitmap.Config, boolean, ColorSpace): Bitmap [static]  [API 26+]
+ createBitmap(DisplayMetrics, int[], int, int, Bitmap.Config): Bitmap [static]  [API 17+]
+ createBitmap(DisplayMetrics, int[], int, int, int, int, Bitmap.Config): Bitmap [static]  [API 17+]
+ createBitmap(Picture): Bitmap [static]  [API 28+]
+ createBitmap(Picture, int, int, Bitmap.Config): Bitmap [static]  [API 28+]
+ createBitmap(int, int, Bitmap.Config): Bitmap [static]  [API 1+]
+ createBitmap(int, int, Bitmap.Config, boolean): Bitmap [static]  [API 26+]
+ createBitmap(int, int, Bitmap.Config, boolean, ColorSpace): Bitmap [static]  [API 26+]
+ createBitmap(int[], int, int, Bitmap.Config): Bitmap [static]  [API 1+]
+ createBitmap(int[], int, int, int, int, Bitmap.Config): Bitmap [static]  [API 1+]
+ createScaledBitmap(Bitmap, int, int, boolean): Bitmap [static]  [API 1+]
~ describeContents(): int  [API 1+]
+ eraseColor(int): void  [API 1+]
+ eraseColor(long): void  [API 29+]
+ extractAlpha(): Bitmap  [API 1+]
+ extractAlpha(Paint, int[]): Bitmap  [API 1+]
+ getAllocationByteCount(): int  [API 19+]
+ getByteCount(): int  [API 12+]
+ getColor(int, int): Color  [API 29+]
+ getColorSpace(): ColorSpace  [API 26+]
+ getConfig(): Bitmap.Config  [API 1+]
+ getDensity(): int  [API 4+]
+ getGainmap(): Gainmap  [API 34+]
+ getGenerationId(): int  [API 12+]
- getHardwareBuffer(): HardwareBuffer  [API 31+]
+ getHeight(): int  [API 1+]
+ getNinePatchChunk(): byte[]  [API 1+]
+ getPixel(int, int): int  [API 1+]
+ getPixels(int[], int, int, int, int, int, int): void  [API 1+]
+ getRowBytes(): int  [API 1+]
+ getScaledHeight(Canvas): int  [API 4+]
+ getScaledHeight(DisplayMetrics): int  [API 4+]
+ getScaledHeight(int): int  [API 4+]
+ getScaledWidth(Canvas): int  [API 4+]
+ getScaledWidth(DisplayMetrics): int  [API 4+]
+ getScaledWidth(int): int  [API 4+]
+ getWidth(): int  [API 1+]
+ hasAlpha(): boolean  [API 1+]
+ hasGainmap(): boolean  [API 34+]
+ hasMipMap(): boolean  [API 17+]
+ isMutable(): boolean  [API 1+]
+ isPremultiplied(): boolean  [API 17+]
+ isRecycled(): boolean  [API 1+]
~ prepareToDraw(): void  [API 4+]
+ reconfigure(int, int, Bitmap.Config): void  [API 19+]
+ recycle(): void  [API 1+]
+ sameAs(Bitmap): boolean  [API 12+]
+ setColorSpace(ColorSpace): void  [API 29+]
+ setConfig(Bitmap.Config): void  [API 19+]
+ setDensity(int): void  [API 4+]
+ setGainmap(Gainmap): void  [API 34+]
+ setHasAlpha(boolean): void  [API 12+]
+ setHasMipMap(boolean): void  [API 17+]
+ setHeight(int): void  [API 19+]
+ setPixel(int, int, int): void  [API 1+]
+ setPixels(int[], int, int, int, int, int, int): void  [API 1+]
+ setPremultiplied(boolean): void  [API 19+]
+ setWidth(int): void  [API 19+]
- wrapHardwareBuffer(HardwareBuffer, ColorSpace): Bitmap [static]  [API 29+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## Bitmap.CompressFormat — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ JPEG [field]  [API 1+]
+ PNG [field]  [API 1+]
+ WEBP [field]  [API 14+, deprecated in 30]
+ WEBP_LOSSLESS [field]  [API 30+]
+ WEBP_LOSSY [field]  [API 30+]
+ valueOf(String): Bitmap.CompressFormat [static]  [API 1+]
+ values(): Bitmap.CompressFormat[] [static]  [API 1+]
```

## Bitmap.Config — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ALPHA_8 [field]  [API 1+]
+ ARGB_4444 [field]  [API 1+, deprecated in 29]
+ ARGB_8888 [field]  [API 1+]
+ HARDWARE [field]  [API 26+]
+ RGBA_1010102 [field]  [API 33+]
+ RGBA_F16 [field]  [API 26+]
+ RGB_565 [field]  [API 1+]
+ valueOf(String): Bitmap.Config [static]  [API 1+]
+ values(): Bitmap.Config[] [static]  [API 1+]
```

## BitmapFactory — framework; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ BitmapFactory()  [API 1+]
+ decodeByteArray(byte[], int, int): Bitmap [static]  [API 1+]
+ decodeByteArray(byte[], int, int, BitmapFactory.Options): Bitmap [static]  [API 1+]
+ decodeFile(String): Bitmap [static]  [API 1+]
+ decodeFile(String, BitmapFactory.Options): Bitmap [static]  [API 1+]
+ decodeFileDescriptor(FileDescriptor): Bitmap [static]  [API 1+]
+ decodeFileDescriptor(FileDescriptor, Rect, BitmapFactory.Options): Bitmap [static]  [API 1+]
+ decodeResource(Resources, int): Bitmap [static]  [API 1+]
+ decodeResource(Resources, int, BitmapFactory.Options): Bitmap [static]  [API 1+]
+ decodeResourceStream(Resources, TypedValue, InputStream, Rect, BitmapFactory.Options): Bitmap [static]  [API 4+]
+ decodeStream(InputStream): Bitmap [static]  [API 1+]
+ decodeStream(InputStream, Rect, BitmapFactory.Options): Bitmap [static]  [API 1+]
```

## BitmapFactory.Options — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ Options()  [API 1+]
+ inBitmap [field]  [API 11+]
+ inDensity [field]  [API 4+]
+ inDither [field]  [API 1+, deprecated in 24]
+ inInputShareable [field]  [API 4+, deprecated in 21]
+ inJustDecodeBounds [field]  [API 1+]
+ inMutable [field]  [API 11+]
+ inPreferQualityOverSpeed [field]  [API 10+, deprecated in 24]
- inPreferredColorSpace [field]  [API 26+]
+ inPreferredConfig [field]  [API 1+]
+ inPremultiplied [field]  [API 19+]
+ inPurgeable [field]  [API 4+, deprecated in 21]
+ inSampleSize [field]  [API 1+]
+ inScaled [field]  [API 4+]
+ inScreenDensity [field]  [API 4+]
+ inTargetDensity [field]  [API 4+]
+ inTempStorage [field]  [API 1+]
+ mCancel [field]  [API 1+, deprecated in 24]
- outColorSpace [field]  [API 26+]
- outConfig [field]  [API 26+]
+ outHeight [field]  [API 1+]
+ outMimeType [field]  [API 1+]
+ outWidth [field]  [API 1+]
+ requestCancelDecode(): void  [API 1+, deprecated in 24]
```

## BitmapRegionDecoder — declaration; API 10+ (0 +, 0 *, 0 ~, 14 -)

```
- decodeRegion(Rect, BitmapFactory.Options): Bitmap  [API 10+]
- finalize(): void  [API 10+]
- getHeight(): int  [API 10+]
- getWidth(): int  [API 10+]
- isRecycled(): boolean  [API 10+]
- newInstance(FileDescriptor, boolean): BitmapRegionDecoder [static]  [API 10+, deprecated in 31]
- newInstance(InputStream): BitmapRegionDecoder [static]  [API 31+]
- newInstance(InputStream, boolean): BitmapRegionDecoder [static]  [API 10+, deprecated in 31]
- newInstance(ParcelFileDescriptor): BitmapRegionDecoder [static]  [API 31+]
- newInstance(String): BitmapRegionDecoder [static]  [API 31+]
- newInstance(String, boolean): BitmapRegionDecoder [static]  [API 10+, deprecated in 31]
- newInstance(byte[], int, int): BitmapRegionDecoder [static]  [API 31+]
- newInstance(byte[], int, int, boolean): BitmapRegionDecoder [static]  [API 10+, deprecated in 31]
- recycle(): void  [API 10+]
```

## BitmapShader — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ BitmapShader(Bitmap, Shader.TileMode, Shader.TileMode)  [API 1+]
+ getFilterMode(): int  [API 33+]
+ getMaxAnisotropy(): int  [API 34+]
+ setFilterMode(int): void  [API 33+]
+ setMaxAnisotropy(int): void  [API 34+]
+ setOverrideGainmap(Gainmap): void  [API 35+]
```

## BlendMode — declaration; API 29+ (2 +, 0 *, 0 ~, 0 -)

```
+ CLEAR [field]  [API 29+]
+ COLOR [field]  [API 29+]
+ COLOR_BURN [field]  [API 29+]
+ COLOR_DODGE [field]  [API 29+]
+ DARKEN [field]  [API 29+]
+ DIFFERENCE [field]  [API 29+]
+ DST [field]  [API 29+]
+ DST_ATOP [field]  [API 29+]
+ DST_IN [field]  [API 29+]
+ DST_OUT [field]  [API 29+]
+ DST_OVER [field]  [API 29+]
+ EXCLUSION [field]  [API 29+]
+ HARD_LIGHT [field]  [API 29+]
+ HUE [field]  [API 29+]
+ LIGHTEN [field]  [API 29+]
+ LUMINOSITY [field]  [API 29+]
+ MODULATE [field]  [API 29+]
+ MULTIPLY [field]  [API 29+]
+ OVERLAY [field]  [API 29+]
+ PLUS [field]  [API 29+]
+ SATURATION [field]  [API 29+]
+ SCREEN [field]  [API 29+]
+ SOFT_LIGHT [field]  [API 29+]
+ SRC [field]  [API 29+]
+ SRC_ATOP [field]  [API 29+]
+ SRC_IN [field]  [API 29+]
+ SRC_OUT [field]  [API 29+]
+ SRC_OVER [field]  [API 29+]
+ XOR [field]  [API 29+]
+ valueOf(String): BlendMode [static]  [API 29+]
+ values(): BlendMode[] [static]  [API 29+]
```

## BlendModeColorFilter — declaration; API 29+ (0 +, 0 *, 0 ~, 5 -)

```
- BlendModeColorFilter(int, BlendMode)  [API 29+]
- equals(Object): boolean  [API 29+]
- getColor(): int  [API 29+]
- getMode(): BlendMode  [API 29+]
- hashCode(): int  [API 29+]
```

## BlurMaskFilter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ BlurMaskFilter(float, BlurMaskFilter.Blur)  [API 1+]
```

## BlurMaskFilter.Blur — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ INNER [field]  [API 1+]
+ NORMAL [field]  [API 1+]
+ OUTER [field]  [API 1+]
+ SOLID [field]  [API 1+]
+ valueOf(String): BlurMaskFilter.Blur [static]  [API 1+]
+ values(): BlurMaskFilter.Blur[] [static]  [API 1+]
```

## Camera — framework; API 1+ (15 +, 0 *, 1 ~, 0 -)

```
+ Camera()  [API 1+]
+ applyToCanvas(Canvas): void  [API 1+]
+ dotWithNormal(float, float, float): float  [API 1+]
~ finalize(): void  [API 1+]
+ getLocationX(): float  [API 16+]
+ getLocationY(): float  [API 16+]
+ getLocationZ(): float  [API 16+]
+ getMatrix(Matrix): void  [API 1+]
+ restore(): void  [API 1+]
+ rotate(float, float, float): void  [API 12+]
+ rotateX(float): void  [API 1+]
+ rotateY(float): void  [API 1+]
+ rotateZ(float): void  [API 1+]
+ save(): void  [API 1+]
+ setLocation(float, float, float): void  [API 12+]
+ translate(float, float, float): void  [API 1+]
```

## Canvas — framework; API 1+ (104 +, 0 *, 8 ~, 11 -)

```
+ Canvas()  [API 1+]
+ Canvas(Bitmap)  [API 1+]
- Canvas(GL)  [API 1-10, removed in 11]
+ clipOutPath(Path): boolean  [API 26+]
+ clipOutRect(Rect): boolean  [API 26+]
+ clipOutRect(RectF): boolean  [API 26+]
+ clipOutRect(float, float, float, float): boolean  [API 26+]
+ clipOutRect(int, int, int, int): boolean  [API 26+]
- clipOutShader(Shader): void  [API 35+]
+ clipPath(Path): boolean  [API 1+]
+ clipPath(Path, Region.Op): boolean  [API 1+, deprecated in 26]
+ clipRect(Rect): boolean  [API 1+]
+ clipRect(Rect, Region.Op): boolean  [API 1+, deprecated in 26]
+ clipRect(RectF): boolean  [API 1+]
+ clipRect(RectF, Region.Op): boolean  [API 1+, deprecated in 26]
+ clipRect(float, float, float, float): boolean  [API 1+]
+ clipRect(float, float, float, float, Region.Op): boolean  [API 1+, deprecated in 26]
+ clipRect(int, int, int, int): boolean  [API 1+]
+ clipRegion(Region): boolean  [API 1-25, removed in 26, deprecated in 21]
+ clipRegion(Region, Region.Op): boolean  [API 1-25, removed in 26, deprecated in 21]
- clipShader(Shader): void  [API 35+]
+ concat(Matrix): void  [API 1+]
- concat(Matrix44): void  [API 35+]
~ disableZ(): void  [API 29+]
+ drawARGB(int, int, int, int): void  [API 1+]
+ drawArc(RectF, float, float, boolean, Paint): void  [API 1+]
+ drawArc(float, float, float, float, float, float, boolean, Paint): void  [API 21+]
+ drawBitmap(Bitmap, Matrix, Paint): void  [API 1+]
+ drawBitmap(Bitmap, Rect, Rect, Paint): void  [API 1+]
+ drawBitmap(Bitmap, Rect, RectF, Paint): void  [API 1+]
+ drawBitmap(Bitmap, float, float, Paint): void  [API 1+]
+ drawBitmap(int[], int, int, float, float, int, int, boolean, Paint): void  [API 3+, deprecated in 21]
+ drawBitmap(int[], int, int, int, int, int, int, boolean, Paint): void  [API 1+, deprecated in 21]
+ drawBitmapMesh(Bitmap, int, int, float[], int, int[], int, Paint): void  [API 1+]
+ drawCircle(float, float, float, Paint): void  [API 1+]
+ drawColor(int): void  [API 1+]
+ drawColor(int, BlendMode): void  [API 29+]
+ drawColor(int, PorterDuff.Mode): void  [API 1+]
+ drawColor(long): void  [API 29+]
+ drawColor(long, BlendMode): void  [API 29+]
+ drawDoubleRoundRect(RectF, float, float, RectF, float, float, Paint): void  [API 29+]
+ drawDoubleRoundRect(RectF, float[], RectF, float[], Paint): void  [API 29+]
- drawGlyphs(int[], int, float[], int, int, Font, Paint): void  [API 31+]
+ drawLine(float, float, float, float, Paint): void  [API 1+]
+ drawLines(float[], Paint): void  [API 1+]
+ drawLines(float[], int, int, Paint): void  [API 1+]
- drawMesh(Mesh, BlendMode, Paint): void  [API 34+]
+ drawOval(RectF, Paint): void  [API 1+]
+ drawOval(float, float, float, float, Paint): void  [API 21+]
+ drawPaint(Paint): void  [API 1+]
+ drawPatch(NinePatch, Rect, Paint): void  [API 31+]
+ drawPatch(NinePatch, RectF, Paint): void  [API 31+]
+ drawPath(Path, Paint): void  [API 1+]
+ drawPicture(Picture): void  [API 1+]
+ drawPicture(Picture, Rect): void  [API 1+]
+ drawPicture(Picture, RectF): void  [API 1+]
+ drawPoint(float, float, Paint): void  [API 1+]
+ drawPoints(float[], Paint): void  [API 1+]
+ drawPoints(float[], int, int, Paint): void  [API 1+]
+ drawPosText(String, float[], Paint): void  [API 1+, deprecated in 16]
+ drawPosText(char[], int, int, float[], Paint): void  [API 1+, deprecated in 16]
+ drawRGB(int, int, int): void  [API 1+]
+ drawRect(Rect, Paint): void  [API 1+]
+ drawRect(RectF, Paint): void  [API 1+]
+ drawRect(float, float, float, float, Paint): void  [API 1+]
- drawRenderNode(RenderNode): void  [API 29+]
+ drawRoundRect(RectF, float, float, Paint): void  [API 1+]
+ drawRoundRect(float, float, float, float, float, float, Paint): void  [API 21+]
+ drawText(CharSequence, int, int, float, float, Paint): void  [API 1+]
+ drawText(String, float, float, Paint): void  [API 1+]
+ drawText(String, int, int, float, float, Paint): void  [API 1+]
+ drawText(char[], int, int, float, float, Paint): void  [API 1+]
+ drawTextOnPath(String, Path, float, float, Paint): void  [API 1+]
+ drawTextOnPath(char[], int, int, Path, float, float, Paint): void  [API 1+]
+ drawTextRun(CharSequence, int, int, int, int, float, float, boolean, Paint): void  [API 23+]
- drawTextRun(MeasuredText, int, int, int, int, float, float, boolean, Paint): void  [API 29+]
+ drawTextRun(char[], int, int, int, int, float, float, boolean, Paint): void  [API 23+]
+ drawVertices(Canvas.VertexMode, int, float[], int, float[], int, int[], int, short[], int, int, Paint): void  [API 1+]
~ enableZ(): void  [API 29+]
- freeGlCaches(): void  [API 1-10, removed in 11]
+ getClipBounds(): Rect  [API 1+]
+ getClipBounds(Rect): boolean  [API 1+]
+ getDensity(): int  [API 4+]
~ getDrawFilter(): DrawFilter  [API 1+]
- getGL(): GL  [API 1-10, removed in 11]
+ getHeight(): int  [API 1+]
+ getMatrix(): Matrix  [API 1+, deprecated in 16]
+ getMatrix(Matrix): void  [API 1+, deprecated in 16]
~ getMaximumBitmapHeight(): int  [API 14+]
~ getMaximumBitmapWidth(): int  [API 14+]
+ getSaveCount(): int  [API 1+]
+ getWidth(): int  [API 1+]
~ isHardwareAccelerated(): boolean  [API 11+]
~ isOpaque(): boolean  [API 1+]
+ quickReject(Path): boolean  [API 30+]
+ quickReject(Path, Canvas.EdgeType): boolean  [API 1+, deprecated in 30]
+ quickReject(RectF): boolean  [API 30+]
+ quickReject(RectF, Canvas.EdgeType): boolean  [API 1+, deprecated in 30]
+ quickReject(float, float, float, float): boolean  [API 30+]
+ quickReject(float, float, float, float, Canvas.EdgeType): boolean  [API 1+, deprecated in 30]
+ restore(): void  [API 1+]
+ restoreToCount(int): void  [API 1+]
+ rotate(float): void  [API 1+]
+ rotate(float, float, float): void  [API 1+]
+ save(): int  [API 1+]
+ save(int): int  [API 1-27, removed in 28, deprecated in 26]
+ saveLayer(RectF, Paint): int  [API 21+]
+ saveLayer(RectF, Paint, int): int  [API 1+, deprecated in 26]
+ saveLayer(float, float, float, float, Paint): int  [API 21+]
+ saveLayer(float, float, float, float, Paint, int): int  [API 1+, deprecated in 26]
+ saveLayerAlpha(RectF, int): int  [API 21+]
+ saveLayerAlpha(RectF, int, int): int  [API 1+, deprecated in 26]
+ saveLayerAlpha(float, float, float, float, int): int  [API 21+]
+ saveLayerAlpha(float, float, float, float, int, int): int  [API 1+, deprecated in 26]
+ scale(float, float): void  [API 1+]
+ scale(float, float, float, float): void  [API 1+]
+ setBitmap(Bitmap): void  [API 1+]
+ setDensity(int): void  [API 4+]
~ setDrawFilter(DrawFilter): void  [API 1+]
+ setMatrix(Matrix): void  [API 1+]
- setViewport(int, int): void  [API 1-10, removed in 11]
+ skew(float, float): void  [API 1+]
+ translate(float, float): void  [API 1+]
```

## Canvas.EdgeType — framework; API 1+, deprecated in 30 (2 +, 0 *, 0 ~, 0 -)

```
+ AA [field]  [API 1+]
+ BW [field]  [API 1+]
+ valueOf(String): Canvas.EdgeType [static]  [API 1+]
+ values(): Canvas.EdgeType[] [static]  [API 1+]
```

## Canvas.VertexMode — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ TRIANGLES [field]  [API 1+]
+ TRIANGLE_FAN [field]  [API 1+]
+ TRIANGLE_STRIP [field]  [API 1+]
+ valueOf(String): Canvas.VertexMode [static]  [API 1+]
+ values(): Canvas.VertexMode[] [static]  [API 1+]
```

## Color — framework; API 1+ (59 +, 0 *, 0 ~, 0 -)

```
+ Color()  [API 1+]
+ HSVToColor(float[]): int [static]  [API 1+]
+ HSVToColor(int, float[]): int [static]  [API 1+]
+ RGBToHSV(int, int, int, float[]): void [static]  [API 1+]
+ alpha(): float  [API 26+]
+ alpha(int): int [static]  [API 1+]
+ alpha(long): float [static]  [API 26+]
+ argb(float, float, float, float): int [static]  [API 26+]
+ argb(int, int, int, int): int [static]  [API 1+]
+ blue(): float  [API 26+]
+ blue(int): int [static]  [API 1+]
+ blue(long): float [static]  [API 26+]
+ colorSpace(long): ColorSpace [static]  [API 26+]
+ colorToHSV(int, float[]): void [static]  [API 1+]
+ convert(ColorSpace): Color  [API 26+]
+ convert(float, float, float, float, ColorSpace, ColorSpace): long [static]  [API 26+]
+ convert(float, float, float, float, ColorSpace.Connector): long [static]  [API 26+]
+ convert(int, ColorSpace): long [static]  [API 26+]
+ convert(long, ColorSpace): long [static]  [API 26+]
+ convert(long, ColorSpace.Connector): long [static]  [API 26+]
+ equals(Object): boolean  [API 1+]
+ getColorSpace(): ColorSpace  [API 26+]
+ getComponent(int): float  [API 26+]
+ getComponentCount(): int  [API 26+]
+ getComponents(): float[]  [API 26+]
+ getComponents(float[]): float[]  [API 26+]
+ getModel(): ColorSpace.Model  [API 26+]
+ green(): float  [API 26+]
+ green(int): int [static]  [API 1+]
+ green(long): float [static]  [API 26+]
+ hashCode(): int  [API 1+]
+ isInColorSpace(long, ColorSpace): boolean [static]  [API 26+]
+ isSrgb(): boolean  [API 26+]
+ isSrgb(long): boolean [static]  [API 26+]
+ isWideGamut(): boolean  [API 26+]
+ isWideGamut(long): boolean [static]  [API 26+]
+ luminance(): float  [API 26+]
+ luminance(int): float [static]  [API 24+]
+ luminance(long): float [static]  [API 26+]
+ pack(): long  [API 26+]
+ pack(float, float, float): long [static]  [API 26+]
+ pack(float, float, float, float): long [static]  [API 26+]
+ pack(float, float, float, float, ColorSpace): long [static]  [API 26+]
+ pack(int): long [static]  [API 26+]
+ parseColor(String): int [static]  [API 1+]
+ red(): float  [API 26+]
+ red(int): int [static]  [API 1+]
+ red(long): float [static]  [API 26+]
+ rgb(float, float, float): int [static]  [API 26+]
+ rgb(int, int, int): int [static]  [API 1+]
+ toArgb(): int  [API 26+]
+ toArgb(long): int [static]  [API 26+]
+ toString(): String  [API 1+]
+ valueOf(float, float, float): Color [static]  [API 26+]
+ valueOf(float, float, float, float): Color [static]  [API 26+]
+ valueOf(float, float, float, float, ColorSpace): Color [static]  [API 26+]
+ valueOf(float[], ColorSpace): Color [static]  [API 26+]
+ valueOf(int): Color [static]  [API 26+]
+ valueOf(long): Color [static]  [API 26+]
```

## ColorFilter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ColorFilter()  [API 1+, deprecated in 26]
```

## ColorMatrix — framework; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ ColorMatrix()  [API 1+]
+ ColorMatrix(ColorMatrix)  [API 1+]
+ ColorMatrix(float[])  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getArray(): float[]  [API 1+]
+ postConcat(ColorMatrix): void  [API 1+]
+ preConcat(ColorMatrix): void  [API 1+]
+ reset(): void  [API 1+]
+ set(ColorMatrix): void  [API 1+]
+ set(float[]): void  [API 1+]
+ setConcat(ColorMatrix, ColorMatrix): void  [API 1+]
+ setRGB2YUV(): void  [API 1+]
+ setRotate(int, float): void  [API 1+]
+ setSaturation(float): void  [API 1+]
+ setScale(float, float, float, float): void  [API 1+]
+ setYUV2RGB(): void  [API 1+]
```

## ColorMatrixColorFilter — framework; API 1+ (2 +, 0 *, 0 ~, 1 -)

```
+ ColorMatrixColorFilter(ColorMatrix)  [API 1+]
+ ColorMatrixColorFilter(float[])  [API 1+]
- getColorMatrix(ColorMatrix): void  [API 26+]
```

## ColorSpace — framework; API 26+ (26 +, 0 *, 1 ~, 0 -)

```
+ ILLUMINANT_A [field]  [API 26+]
+ ILLUMINANT_B [field]  [API 26+]
+ ILLUMINANT_C [field]  [API 26+]
+ ILLUMINANT_D50 [field]  [API 26+]
+ ILLUMINANT_D55 [field]  [API 26+]
+ ILLUMINANT_D60 [field]  [API 26+]
+ ILLUMINANT_D65 [field]  [API 26+]
+ ILLUMINANT_D75 [field]  [API 26+]
+ ILLUMINANT_E [field]  [API 26+]
+ adapt(ColorSpace, float[]): ColorSpace [static]  [API 26+]
+ adapt(ColorSpace, float[], ColorSpace.Adaptation): ColorSpace [static]  [API 26+]
+ cctToXyz(int): float[] [static]  [API 31+]
+ chromaticAdaptation(ColorSpace.Adaptation, float[], float[]): float[] [static]  [API 31+]
+ connect(ColorSpace): ColorSpace.Connector [static]  [API 26+]
+ connect(ColorSpace, ColorSpace): ColorSpace.Connector [static]  [API 26+]
+ connect(ColorSpace, ColorSpace, ColorSpace.RenderIntent): ColorSpace.Connector [static]  [API 26+]
+ connect(ColorSpace, ColorSpace.RenderIntent): ColorSpace.Connector [static]  [API 26+]
+ equals(Object): boolean  [API 26+]
+ fromXyz(float, float, float): float[]  [API 26+]
+ fromXyz(float[]): float[]  [API 26+]
+ get(ColorSpace.Named): ColorSpace [static]  [API 26+]
+ getComponentCount(): int  [API 26+]
+ getDataSpace(): int  [API 33+]
+ getFromDataSpace(int): ColorSpace [static]  [API 33+]
+ getId(): int  [API 26+]
+ getMaxValue(int): float  [API 26+]
+ getMinValue(int): float  [API 26+]
+ getModel(): ColorSpace.Model  [API 26+]
+ getName(): String  [API 26+]
+ hashCode(): int  [API 26+]
~ isSrgb(): boolean  [API 26+]
+ isWideGamut(): boolean  [API 26+]
+ match(float[], ColorSpace.Rgb.TransferParameters): ColorSpace [static]  [API 26+]
+ toString(): String  [API 26+]
+ toXyz(float, float, float): float[]  [API 26+]
+ toXyz(float[]): float[]  [API 26+]
```

## ColorSpace.Adaptation — framework; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ BRADFORD [field]  [API 26+]
+ CIECAT02 [field]  [API 26+]
+ VON_KRIES [field]  [API 26+]
+ valueOf(String): ColorSpace.Adaptation [static]  [API 26+]
+ values(): ColorSpace.Adaptation[] [static]  [API 26+]
```

## ColorSpace.Connector — framework; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
+ getDestination(): ColorSpace  [API 26+]
+ getRenderIntent(): ColorSpace.RenderIntent  [API 26+]
+ getSource(): ColorSpace  [API 26+]
+ transform(float, float, float): float[]  [API 26+]
+ transform(float[]): float[]  [API 26+]
```

## ColorSpace.Model — framework; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ CMYK [field]  [API 26+]
+ LAB [field]  [API 26+]
+ RGB [field]  [API 26+]
+ XYZ [field]  [API 26+]
+ getComponentCount(): int  [API 26+]
+ valueOf(String): ColorSpace.Model [static]  [API 26+]
+ values(): ColorSpace.Model[] [static]  [API 26+]
```

## ColorSpace.Named — framework; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ ACES [field]  [API 26+]
+ ACESCG [field]  [API 26+]
+ ADOBE_RGB [field]  [API 26+]
+ BT2020 [field]  [API 26+]
+ BT2020_HLG [field]  [API 34+]
+ BT2020_PQ [field]  [API 34+]
+ BT709 [field]  [API 26+]
+ CIE_LAB [field]  [API 26+]
+ CIE_XYZ [field]  [API 26+]
+ DCI_P3 [field]  [API 26+]
+ DISPLAY_P3 [field]  [API 26+]
+ EXTENDED_SRGB [field]  [API 26+]
+ LINEAR_EXTENDED_SRGB [field]  [API 26+]
+ LINEAR_SRGB [field]  [API 26+]
+ NTSC_1953 [field]  [API 26+]
+ PRO_PHOTO_RGB [field]  [API 26+]
+ SMPTE_C [field]  [API 26+]
+ SRGB [field]  [API 26+]
+ valueOf(String): ColorSpace.Named [static]  [API 26+]
+ values(): ColorSpace.Named[] [static]  [API 26+]
```

## ColorSpace.RenderIntent — framework; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ ABSOLUTE [field]  [API 26+]
+ PERCEPTUAL [field]  [API 26+]
+ RELATIVE [field]  [API 26+]
+ SATURATION [field]  [API 26+]
+ valueOf(String): ColorSpace.RenderIntent [static]  [API 26+]
+ values(): ColorSpace.RenderIntent[] [static]  [API 26+]
```

## ColorSpace.Rgb — framework; API 26+ (29 +, 0 *, 0 ~, 0 -)

```
+ Rgb(String, float[], ColorSpace.Rgb.TransferParameters)  [API 26+]
+ Rgb(String, float[], DoubleUnaryOperator, DoubleUnaryOperator)  [API 26+]
+ Rgb(String, float[], double)  [API 26+]
+ Rgb(String, float[], float[], ColorSpace.Rgb.TransferParameters)  [API 26+]
+ Rgb(String, float[], float[], DoubleUnaryOperator, DoubleUnaryOperator, float, float)  [API 26+]
+ Rgb(String, float[], float[], double)  [API 26+]
+ equals(Object): boolean  [API 26+]
+ fromLinear(float, float, float): float[]  [API 26+]
+ fromLinear(float[]): float[]  [API 26+]
+ fromXyz(float[]): float[]  [API 26+]
+ getEotf(): DoubleUnaryOperator  [API 26+]
+ getInverseTransform(): float[]  [API 26+]
+ getInverseTransform(float[]): float[]  [API 26+]
+ getMaxValue(int): float  [API 26+]
+ getMinValue(int): float  [API 26+]
+ getOetf(): DoubleUnaryOperator  [API 26+]
+ getPrimaries(): float[]  [API 26+]
+ getPrimaries(float[]): float[]  [API 26+]
+ getTransferParameters(): ColorSpace.Rgb.TransferParameters  [API 26+]
+ getTransform(): float[]  [API 26+]
+ getTransform(float[]): float[]  [API 26+]
+ getWhitePoint(): float[]  [API 26+]
+ getWhitePoint(float[]): float[]  [API 26+]
+ hashCode(): int  [API 26+]
+ isSrgb(): boolean  [API 26+]
+ isWideGamut(): boolean  [API 26+]
+ toLinear(float, float, float): float[]  [API 26+]
+ toLinear(float[]): float[]  [API 26+]
+ toXyz(float[]): float[]  [API 26+]
```

## ColorSpace.Rgb.TransferParameters — framework; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ TransferParameters(double, double, double, double, double)  [API 26+]
+ TransferParameters(double, double, double, double, double, double, double)  [API 26+]
+ a [field]  [API 26+]
+ b [field]  [API 26+]
+ c [field]  [API 26+]
+ d [field]  [API 26+]
+ e [field]  [API 26+]
+ equals(Object): boolean  [API 26+]
+ f [field]  [API 26+]
+ g [field]  [API 26+]
+ hashCode(): int  [API 26+]
```

## ComposePathEffect — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- ComposePathEffect(PathEffect, PathEffect)  [API 1+]
```

## ComposeShader — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ ComposeShader(Shader, Shader, BlendMode)  [API 29+]
+ ComposeShader(Shader, Shader, PorterDuff.Mode)  [API 1+]
+ ComposeShader(Shader, Shader, Xfermode)  [API 1+, deprecated in 29]
```

## CornerPathEffect — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- CornerPathEffect(float)  [API 1+]
```

## DashPathEffect — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ DashPathEffect(float[], float)  [API 1+]
```

## DiscretePathEffect — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- DiscretePathEffect(float, float)  [API 1+]
```

## DrawFilter — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ DrawFilter()  [API 1+]
+ finalize(): void  [API 1+]
```

## EmbossMaskFilter — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- EmbossMaskFilter(float[], float, float, float)  [API 1+, deprecated in 28]
```

## Gainmap — declaration; API 34+ (0 +, 7 *, 0 ~, 13 -)

```
- CREATOR [field]  [API 34+]
* Gainmap(Bitmap)  [API 34+]
- Gainmap(Gainmap, Bitmap)  [API 35+]
- describeContents(): int  [API 34+]
* getDisplayRatioForFullHdr(): float  [API 34+]
- getEpsilonHdr(): float[]  [API 34+]
- getEpsilonSdr(): float[]  [API 34+]
* getGainmapContents(): Bitmap  [API 34+]
- getGamma(): float[]  [API 34+]
* getMinDisplayRatioForHdrTransition(): float  [API 34+]
- getRatioMax(): float[]  [API 34+]
- getRatioMin(): float[]  [API 34+]
* setDisplayRatioForFullHdr(float): void  [API 34+]
- setEpsilonHdr(float, float, float): void  [API 34+]
- setEpsilonSdr(float, float, float): void  [API 34+]
* setGainmapContents(Bitmap): void  [API 34+]
- setGamma(float, float, float): void  [API 34+]
* setMinDisplayRatioForHdrTransition(float): void  [API 34+]
- setRatioMax(float, float, float): void  [API 34+]
- setRatioMin(float, float, float): void  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## HardwareBufferRenderer — declaration; API 34+ (0 +, 0 *, 0 ~, 7 -)

```
- HardwareBufferRenderer(HardwareBuffer)  [API 34+]
- close(): void  [API 34+]
- isClosed(): boolean  [API 34+]
- obtainRenderRequest(): HardwareBufferRenderer.RenderRequest  [API 34+]
- setContentRoot(RenderNode): void  [API 34+]
- setLightSourceAlpha(float, float): void  [API 34+]
- setLightSourceGeometry(float, float, float, float): void  [API 34+]
```

## HardwareBufferRenderer.RenderRequest — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- draw(Executor, Consumer): void  [API 34+]
- setBufferTransform(int): HardwareBufferRenderer.RenderRequest  [API 34+]
- setColorSpace(ColorSpace): HardwareBufferRenderer.RenderRequest  [API 34+]
```

## HardwareBufferRenderer.RenderResult — declaration; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- getFence(): SyncFence  [API 34+]
- getStatus(): int  [API 34+]
```

## HardwareRenderer — declaration; API 29+ (0 +, 3 *, 0 ~, 13 -)

```
* HardwareRenderer()  [API 29+]
- clearContent(): void  [API 29+]
- createRenderRequest(): HardwareRenderer.FrameRenderRequest  [API 29+]
- destroy(): void  [API 29+]
- isDrawingEnabled(): boolean [static]  [API 33+]
* isOpaque(): boolean  [API 29+]
- notifyFramePending(): void  [API 29+]
- setContentRoot(RenderNode): void  [API 29+]
- setDrawingEnabled(boolean): void [static]  [API 33+]
- setLightSourceAlpha(float, float): void  [API 29+]
- setLightSourceGeometry(float, float, float, float): void  [API 29+]
- setName(String): void  [API 29+]
* setOpaque(boolean): void  [API 29+]
- setSurface(Surface): void  [API 29+]
- start(): void  [API 29+]
- stop(): void  [API 29+]
```

## HardwareRenderer.FrameRenderRequest — declaration; API 29+ (0 +, 0 *, 0 ~, 4 -)

```
- setFrameCommitCallback(Executor, Runnable): HardwareRenderer.FrameRenderRequest  [API 29+]
- setVsyncTime(long): HardwareRenderer.FrameRenderRequest  [API 29+]
- setWaitForPresent(boolean): HardwareRenderer.FrameRenderRequest  [API 29+]
- syncAndDraw(): int  [API 29+]
```

## ImageDecoder — declaration; API 28+ (0 +, 16 *, 0 ~, 18 -)

```
- close(): void  [API 28+]
- createSource(AssetManager, String): ImageDecoder.Source [static]  [API 28+]
- createSource(ByteBuffer): ImageDecoder.Source [static]  [API 28+]
- createSource(Callable): ImageDecoder.Source [static]  [API 29+]
- createSource(ContentResolver, Uri): ImageDecoder.Source [static]  [API 28+]
- createSource(File): ImageDecoder.Source [static]  [API 28+]
- createSource(Resources, int): ImageDecoder.Source [static]  [API 28+]
- createSource(byte[]): ImageDecoder.Source [static]  [API 31+]
- createSource(byte[], int, int): ImageDecoder.Source [static]  [API 31+]
- decodeBitmap(ImageDecoder.Source): Bitmap [static]  [API 28+]
- decodeBitmap(ImageDecoder.Source, ImageDecoder.OnHeaderDecodedListener): Bitmap [static]  [API 28+]
- decodeDrawable(ImageDecoder.Source): Drawable [static]  [API 28+]
- decodeDrawable(ImageDecoder.Source, ImageDecoder.OnHeaderDecodedListener): Drawable [static]  [API 28+]
- finalize(): void  [API 28+]
* getAllocator(): int  [API 28+]
* getCrop(): Rect  [API 28+]
* getMemorySizePolicy(): int  [API 28+]
* getOnPartialImageListener(): ImageDecoder.OnPartialImageListener  [API 28+]
* getPostProcessor(): PostProcessor  [API 28+]
* isDecodeAsAlphaMaskEnabled(): boolean  [API 28+]
- isMimeTypeSupported(String): boolean [static]  [API 29+]
* isMutableRequired(): boolean  [API 28+]
* isUnpremultipliedRequired(): boolean  [API 28+]
* setAllocator(int): void  [API 28+]
* setCrop(Rect): void  [API 28+]
* setDecodeAsAlphaMaskEnabled(boolean): void  [API 28+]
* setMemorySizePolicy(int): void  [API 28+]
* setMutableRequired(boolean): void  [API 28+]
* setOnPartialImageListener(ImageDecoder.OnPartialImageListener): void  [API 28+]
* setPostProcessor(PostProcessor): void  [API 28+]
- setTargetColorSpace(ColorSpace): void  [API 28+]
- setTargetSampleSize(int): void  [API 28+]
- setTargetSize(int, int): void  [API 28+]
* setUnpremultipliedRequired(boolean): void  [API 28+]
```

## ImageDecoder.DecodeException — declaration; API 28+ (0 +, 0 *, 0 ~, 2 -)

```
- getError(): int  [API 28+]
- getSource(): ImageDecoder.Source  [API 28+]
```

## ImageDecoder.ImageInfo — declaration; API 28+ (0 +, 0 *, 0 ~, 4 -)

```
- getColorSpace(): ColorSpace  [API 28+]
- getMimeType(): String  [API 28+]
- getSize(): Size  [API 28+]
- isAnimated(): boolean  [API 28+]
```

## ImageDecoder.OnHeaderDecodedListener — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onHeaderDecoded(ImageDecoder, ImageDecoder.ImageInfo, ImageDecoder.Source): void  [API 28+]
```

## ImageDecoder.OnPartialImageListener — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPartialImage(ImageDecoder.DecodeException): boolean  [API 28+]
```

## ImageDecoder.Source — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## ImageFormat — declaration; API 8+ (0 +, 0 *, 0 ~, 2 -)

```
- ImageFormat()  [API 8+]
- getBitsPerPixel(int): int [static]  [API 8+]
```

## Insets — framework; API 29+ (10 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 29+]
+ NONE [field]  [API 29+]
+ add(Insets, Insets): Insets [static]  [API 29+]
+ bottom [field]  [API 29+]
~ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ hashCode(): int  [API 29+]
+ left [field]  [API 29+]
+ max(Insets, Insets): Insets [static]  [API 29+]
+ min(Insets, Insets): Insets [static]  [API 29+]
+ of(Rect): Insets [static]  [API 29+]
+ of(int, int, int, int): Insets [static]  [API 29+]
+ right [field]  [API 29+]
+ subtract(Insets, Insets): Insets [static]  [API 29+]
+ toString(): String  [API 29+]
+ top [field]  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## Interpolator — declaration; API 1+ (0 +, 2 *, 0 ~, 10 -)

```
* Interpolator(int)  [API 1+]
- Interpolator(int, int)  [API 1+]
- finalize(): void  [API 1+]
- getKeyFrameCount(): int  [API 1+]
* getValueCount(): int  [API 1+]
- reset(int): void  [API 1+]
- reset(int, int): void  [API 1+]
- setKeyFrame(int, int, float[]): void  [API 1+]
- setKeyFrame(int, int, float[], float[]): void  [API 1+]
- setRepeatMirror(float, boolean): void  [API 1+]
- timeToValues(float[]): Interpolator.Result  [API 1+]
- timeToValues(int, float[]): Interpolator.Result  [API 1+]
```

## Interpolator.Result — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ FREEZE_END [field]  [API 1+]
+ FREEZE_START [field]  [API 1+]
+ NORMAL [field]  [API 1+]
+ valueOf(String): Interpolator.Result [static]  [API 1+]
+ values(): Interpolator.Result[] [static]  [API 1+]
```

## LayerRasterizer — declaration; API 1-25, removed in 26, deprecated in 21 (0 +, 0 *, 0 ~, 3 -)

```
- LayerRasterizer()  [API 1-25, removed in 26]
- addLayer(Paint): void  [API 1-25, removed in 26]
- addLayer(Paint, float, float): void  [API 1-25, removed in 26]
```

## LightingColorFilter — framework; API 1+ (1 +, 0 *, 0 ~, 2 -)

```
+ LightingColorFilter(int, int)  [API 1+]
- getColorAdd(): int  [API 26+]
- getColorMultiply(): int  [API 26+]
```

## LinearGradient — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ LinearGradient(float, float, float, float, int, int, Shader.TileMode)  [API 1+]
+ LinearGradient(float, float, float, float, int[], float[], Shader.TileMode)  [API 1+]
+ LinearGradient(float, float, float, float, long, long, Shader.TileMode)  [API 29+]
+ LinearGradient(float, float, float, float, long[], float[], Shader.TileMode)  [API 29+]
```

## MaskFilter — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ MaskFilter()  [API 1+]
+ finalize(): void  [API 1+]
```

## Matrix — framework; API 1+ (52 +, 0 *, 0 ~, 0 -)

```
+ IDENTITY_MATRIX [field]  [API 31+]
+ Matrix()  [API 1+]
+ Matrix(Matrix)  [API 1+]
+ dump(PrintWriter): void  [API 31+]
+ equals(Object): boolean  [API 1+]
+ getValues(float[]): void  [API 1+]
+ hashCode(): int  [API 1+]
+ invert(Matrix): boolean  [API 1+]
+ isAffine(): boolean  [API 21+]
+ isIdentity(): boolean  [API 1+]
+ mapPoints(float[]): void  [API 1+]
+ mapPoints(float[], float[]): void  [API 1+]
+ mapPoints(float[], int, float[], int, int): void  [API 1+]
+ mapRadius(float): float  [API 1+]
+ mapRect(RectF): boolean  [API 1+]
+ mapRect(RectF, RectF): boolean  [API 1+]
+ mapVectors(float[]): void  [API 1+]
+ mapVectors(float[], float[]): void  [API 1+]
+ mapVectors(float[], int, float[], int, int): void  [API 1+]
+ postConcat(Matrix): boolean  [API 1+]
+ postRotate(float): boolean  [API 1+]
+ postRotate(float, float, float): boolean  [API 1+]
+ postScale(float, float): boolean  [API 1+]
+ postScale(float, float, float, float): boolean  [API 1+]
+ postSkew(float, float): boolean  [API 1+]
+ postSkew(float, float, float, float): boolean  [API 1+]
+ postTranslate(float, float): boolean  [API 1+]
+ preConcat(Matrix): boolean  [API 1+]
+ preRotate(float): boolean  [API 1+]
+ preRotate(float, float, float): boolean  [API 1+]
+ preScale(float, float): boolean  [API 1+]
+ preScale(float, float, float, float): boolean  [API 1+]
+ preSkew(float, float): boolean  [API 1+]
+ preSkew(float, float, float, float): boolean  [API 1+]
+ preTranslate(float, float): boolean  [API 1+]
+ rectStaysRect(): boolean  [API 1+]
+ reset(): void  [API 1+]
+ set(Matrix): void  [API 1+]
+ setConcat(Matrix, Matrix): boolean  [API 1+]
+ setPolyToPoly(float[], int, float[], int, int): boolean  [API 1+]
+ setRectToRect(RectF, RectF, Matrix.ScaleToFit): boolean  [API 1+]
+ setRotate(float): void  [API 1+]
+ setRotate(float, float, float): void  [API 1+]
+ setScale(float, float): void  [API 1+]
+ setScale(float, float, float, float): void  [API 1+]
+ setSinCos(float, float): void  [API 1+]
+ setSinCos(float, float, float, float): void  [API 1+]
+ setSkew(float, float): void  [API 1+]
+ setSkew(float, float, float, float): void  [API 1+]
+ setTranslate(float, float): void  [API 1+]
+ setValues(float[]): void  [API 1+]
+ toShortString(): String  [API 1+]
+ toString(): String  [API 1+]
```

## Matrix.ScaleToFit — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CENTER [field]  [API 1+]
+ END [field]  [API 1+]
+ FILL [field]  [API 1+]
+ START [field]  [API 1+]
+ valueOf(String): Matrix.ScaleToFit [static]  [API 1+]
+ values(): Matrix.ScaleToFit[] [static]  [API 1+]
```

## Matrix44 — declaration; API 35+ (0 +, 0 *, 0 ~, 18 -)

```
- Matrix44()  [API 35+]
- Matrix44(Matrix)  [API 35+]
- concat(Matrix44): Matrix44  [API 35+]
- equals(Object): boolean  [API 35+]
- get(int, int): float  [API 35+]
- getValues(float[]): void  [API 35+]
- hashCode(): int  [API 35+]
- invert(): boolean  [API 35+]
- isIdentity(): boolean  [API 35+]
- map(float, float, float, float): float[]  [API 35+]
- map(float, float, float, float, float[]): void  [API 35+]
- reset(): void  [API 35+]
- rotate(float, float, float, float): Matrix44  [API 35+]
- scale(float, float, float): Matrix44  [API 35+]
- set(int, int, float): void  [API 35+]
- setValues(float[]): void  [API 35+]
- toString(): String  [API 35+]
- translate(float, float, float): Matrix44  [API 35+]
```

## Mesh — declaration; API 34+ (0 +, 0 *, 0 ~, 15 -)

```
- Mesh(MeshSpecification, int, Buffer, int, RectF)  [API 34+]
- Mesh(MeshSpecification, int, Buffer, int, ShortBuffer, RectF)  [API 34+]
- setColorUniform(String, Color): void  [API 34+]
- setColorUniform(String, int): void  [API 34+]
- setColorUniform(String, long): void  [API 34+]
- setFloatUniform(String, float): void  [API 34+]
- setFloatUniform(String, float, float): void  [API 34+]
- setFloatUniform(String, float, float, float): void  [API 34+]
- setFloatUniform(String, float, float, float, float): void  [API 34+]
- setFloatUniform(String, float[]): void  [API 34+]
- setIntUniform(String, int): void  [API 34+]
- setIntUniform(String, int, int): void  [API 34+]
- setIntUniform(String, int, int, int): void  [API 34+]
- setIntUniform(String, int, int, int, int): void  [API 34+]
- setIntUniform(String, int[]): void  [API 34+]
```

## MeshSpecification — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- make(MeshSpecification.Attribute[], int, MeshSpecification.Varying[], String, String): MeshSpecification [static]  [API 34+]
- make(MeshSpecification.Attribute[], int, MeshSpecification.Varying[], String, String, ColorSpace): MeshSpecification [static]  [API 34+]
- make(MeshSpecification.Attribute[], int, MeshSpecification.Varying[], String, String, ColorSpace, int): MeshSpecification [static]  [API 34+]
```

## MeshSpecification.Attribute — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Attribute(int, int, String)  [API 34+]
* getName(): String  [API 34+]
* getOffset(): int  [API 34+]
* getType(): int  [API 34+]
* toString(): String  [API 34+]
```

## MeshSpecification.Varying — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* Varying(int, String)  [API 34+]
* getName(): String  [API 34+]
* getType(): int  [API 34+]
* toString(): String  [API 34+]
```

## Movie — framework; API 1+, deprecated in 28 (10 +, 0 *, 1 ~, 0 -)

```
+ decodeByteArray(byte[], int, int): Movie [static]  [API 1+]
+ decodeFile(String): Movie [static]  [API 1+]
+ decodeStream(InputStream): Movie [static]  [API 1+]
+ draw(Canvas, float, float): void  [API 1+]
+ draw(Canvas, float, float, Paint): void  [API 1+]
+ duration(): int  [API 1+]
+ finalize(): void  [API 1+]
+ height(): int  [API 1+]
~ isOpaque(): boolean  [API 1+]
+ setTime(int): boolean  [API 1+]
+ width(): int  [API 1+]
```

## NinePatch — framework; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ NinePatch(Bitmap, byte[])  [API 19+]
+ NinePatch(Bitmap, byte[], String)  [API 1+]
+ draw(Canvas, Rect): void  [API 1+]
+ draw(Canvas, Rect, Paint): void  [API 1+]
+ draw(Canvas, RectF): void  [API 1+]
+ finalize(): void  [API 1+]
+ getBitmap(): Bitmap  [API 19+]
+ getDensity(): int  [API 4+]
+ getHeight(): int  [API 1+]
+ getName(): String  [API 19+]
+ getPaint(): Paint  [API 19+]
+ getTransparentRegion(Rect): Region  [API 1+]
+ getWidth(): int  [API 1+]
+ hasAlpha(): boolean  [API 1+]
+ isNinePatchChunk(byte[]): boolean [static]  [API 1+]
+ setPaint(Paint): void  [API 1+]
```

## Outline — framework; API 21+ (16 +, 0 *, 3 ~, 0 -)

```
+ Outline()  [API 21+]
+ Outline(Outline)  [API 21+]
~ canClip(): boolean  [API 21+]
+ getAlpha(): float  [API 21+]
+ getRadius(): float  [API 24+]
+ getRect(Rect): boolean  [API 24+]
+ isEmpty(): boolean  [API 21+]
+ offset(int, int): void  [API 22+]
+ set(Outline): void  [API 21+]
+ setAlpha(float): void  [API 21+]
~ setConvexPath(Path): void  [API 21+, deprecated in 30]
+ setEmpty(): void  [API 21+]
+ setOval(Rect): void  [API 21+]
+ setOval(int, int, int, int): void  [API 21+]
~ setPath(Path): void  [API 30+]
+ setRect(Rect): void  [API 21+]
+ setRect(int, int, int, int): void  [API 21+]
+ setRoundRect(Rect, float): void  [API 21+]
+ setRoundRect(int, int, int, int, float): void  [API 21+]
```

## Paint — framework; API 1+ (131 +, 0 *, 2 ~, 2 -)

```
+ Paint()  [API 1+]
+ Paint(Paint)  [API 1+]
+ Paint(int)  [API 1+]
+ ascent(): float  [API 1+]
+ breakText(CharSequence, int, int, boolean, float, float[]): int  [API 1+]
+ breakText(String, boolean, float, float[]): int  [API 1+]
+ breakText(char[], int, int, float, float[]): int  [API 1+]
+ clearShadowLayer(): void  [API 1+]
+ descent(): float  [API 1+]
+ equalsForTextMeasurement(Paint): boolean  [API 28+]
+ getAlpha(): int  [API 1+]
+ getBlendMode(): BlendMode  [API 29+]
+ getColor(): int  [API 1+]
+ getColorFilter(): ColorFilter  [API 1+]
+ getColorLong(): long  [API 29+]
+ getEndHyphenEdit(): int  [API 29+]
+ getFillPath(Path, Path): boolean  [API 1+]
+ getFlags(): int  [API 1+]
+ getFontFeatureSettings(): String  [API 21+]
+ getFontMetrics(): Paint.FontMetrics  [API 1+]
+ getFontMetrics(Paint.FontMetrics): float  [API 1+]
+ getFontMetricsForLocale(Paint.FontMetrics): void  [API 35+]
+ getFontMetricsInt(): Paint.FontMetricsInt  [API 1+]
+ getFontMetricsInt(CharSequence, int, int, int, int, boolean, Paint.FontMetricsInt): void  [API 33+]
+ getFontMetricsInt(Paint.FontMetricsInt): int  [API 1+]
+ getFontMetricsInt(char[], int, int, int, int, boolean, Paint.FontMetricsInt): void  [API 33+]
+ getFontMetricsIntForLocale(Paint.FontMetricsInt): void  [API 35+]
+ getFontSpacing(): float  [API 1+]
+ getFontVariationSettings(): String  [API 26+]
+ getHinting(): int  [API 14+]
+ getLetterSpacing(): float  [API 21+]
+ getMaskFilter(): MaskFilter  [API 1+]
+ getOffsetForAdvance(CharSequence, int, int, int, int, boolean, float): int  [API 23+]
+ getOffsetForAdvance(char[], int, int, int, int, boolean, float): int  [API 23+]
+ getPathEffect(): PathEffect  [API 1+]
- getRasterizer(): Rasterizer  [API 1-25, removed in 26, deprecated in 21]
+ getRunAdvance(CharSequence, int, int, int, int, boolean, int): float  [API 23+]
+ getRunAdvance(char[], int, int, int, int, boolean, int): float  [API 23+]
+ getRunCharacterAdvance(CharSequence, int, int, int, int, boolean, int, float[], int): float  [API 34+]
+ getRunCharacterAdvance(char[], int, int, int, int, boolean, int, float[], int): float  [API 34+]
+ getShader(): Shader  [API 1+]
+ getShadowLayerColor(): int  [API 29+]
+ getShadowLayerColorLong(): long  [API 29+]
+ getShadowLayerDx(): float  [API 29+]
+ getShadowLayerDy(): float  [API 29+]
+ getShadowLayerRadius(): float  [API 29+]
+ getStartHyphenEdit(): int  [API 29+]
+ getStrikeThruPosition(): float  [API 29+]
+ getStrikeThruThickness(): float  [API 29+]
+ getStrokeCap(): Paint.Cap  [API 1+]
+ getStrokeJoin(): Paint.Join  [API 1+]
+ getStrokeMiter(): float  [API 1+]
+ getStrokeWidth(): float  [API 1+]
+ getStyle(): Paint.Style  [API 1+]
+ getTextAlign(): Paint.Align  [API 1+]
+ getTextBounds(CharSequence, int, int, Rect): void  [API 29+]
+ getTextBounds(String, int, int, Rect): void  [API 1+]
+ getTextBounds(char[], int, int, Rect): void  [API 1+]
+ getTextLocale(): Locale  [API 17+]
+ getTextLocales(): LocaleList  [API 24+]
~ getTextPath(String, int, int, float, float, Path): void  [API 1+]
~ getTextPath(char[], int, int, float, float, Path): void  [API 1+]
+ getTextRunAdvances(char[], int, int, int, int, boolean, float[], int): float  [API 29+]
+ getTextRunCursor(CharSequence, int, int, boolean, int, int): int  [API 29+]
+ getTextRunCursor(char[], int, int, boolean, int, int): int  [API 29+]
+ getTextScaleX(): float  [API 1+]
+ getTextSize(): float  [API 1+]
+ getTextSkewX(): float  [API 1+]
+ getTextWidths(CharSequence, int, int, float[]): int  [API 1+]
+ getTextWidths(String, float[]): int  [API 1+]
+ getTextWidths(String, int, int, float[]): int  [API 1+]
+ getTextWidths(char[], int, int, float[]): int  [API 1+]
+ getTypeface(): Typeface  [API 1+]
+ getUnderlinePosition(): float  [API 29+]
+ getUnderlineThickness(): float  [API 29+]
+ getWordSpacing(): float  [API 29+]
+ getXfermode(): Xfermode  [API 1+]
+ hasGlyph(String): boolean  [API 23+]
+ isAntiAlias(): boolean  [API 1+]
+ isDither(): boolean  [API 1+]
+ isElegantTextHeight(): boolean  [API 21+]
+ isFakeBoldText(): boolean  [API 1+]
+ isFilterBitmap(): boolean  [API 1+]
+ isLinearText(): boolean  [API 1+]
+ isStrikeThruText(): boolean  [API 1+]
+ isSubpixelText(): boolean  [API 1+]
+ isUnderlineText(): boolean  [API 1+]
+ measureText(CharSequence, int, int): float  [API 1+]
+ measureText(String): float  [API 1+]
+ measureText(String, int, int): float  [API 1+]
+ measureText(char[], int, int): float  [API 1+]
+ reset(): void  [API 1+]
+ set(Paint): void  [API 1+]
+ setARGB(int, int, int, int): void  [API 1+]
+ setAlpha(int): void  [API 1+]
+ setAntiAlias(boolean): void  [API 1+]
+ setBlendMode(BlendMode): void  [API 29+]
+ setColor(int): void  [API 1+]
+ setColor(long): void  [API 29+]
+ setColorFilter(ColorFilter): ColorFilter  [API 1+]
+ setDither(boolean): void  [API 1+]
+ setElegantTextHeight(boolean): void  [API 21+]
+ setEndHyphenEdit(int): void  [API 29+]
+ setFakeBoldText(boolean): void  [API 1+]
+ setFilterBitmap(boolean): void  [API 1+]
+ setFlags(int): void  [API 1+]
+ setFontFeatureSettings(String): void  [API 21+]
+ setFontVariationSettings(String): boolean  [API 26+]
+ setHinting(int): void  [API 14+]
+ setLetterSpacing(float): void  [API 21+]
+ setLinearText(boolean): void  [API 1+]
+ setMaskFilter(MaskFilter): MaskFilter  [API 1+]
+ setPathEffect(PathEffect): PathEffect  [API 1+]
- setRasterizer(Rasterizer): Rasterizer  [API 1-25, removed in 26, deprecated in 21]
+ setShader(Shader): Shader  [API 1+]
+ setShadowLayer(float, float, float, int): void  [API 1+]
+ setShadowLayer(float, float, float, long): void  [API 29+]
+ setStartHyphenEdit(int): void  [API 29+]
+ setStrikeThruText(boolean): void  [API 1+]
+ setStrokeCap(Paint.Cap): void  [API 1+]
+ setStrokeJoin(Paint.Join): void  [API 1+]
+ setStrokeMiter(float): void  [API 1+]
+ setStrokeWidth(float): void  [API 1+]
+ setStyle(Paint.Style): void  [API 1+]
+ setSubpixelText(boolean): void  [API 1+]
+ setTextAlign(Paint.Align): void  [API 1+]
+ setTextLocale(Locale): void  [API 17+]
+ setTextLocales(LocaleList): void  [API 24+]
+ setTextScaleX(float): void  [API 1+]
+ setTextSize(float): void  [API 1+]
+ setTextSkewX(float): void  [API 1+]
+ setTypeface(Typeface): Typeface  [API 1+]
+ setUnderlineText(boolean): void  [API 1+]
+ setWordSpacing(float): void  [API 29+]
+ setXfermode(Xfermode): Xfermode  [API 1+]
```

## Paint.Align — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CENTER [field]  [API 1+]
+ LEFT [field]  [API 1+]
+ RIGHT [field]  [API 1+]
+ valueOf(String): Paint.Align [static]  [API 1+]
+ values(): Paint.Align[] [static]  [API 1+]
```

## Paint.Cap — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BUTT [field]  [API 1+]
+ ROUND [field]  [API 1+]
+ SQUARE [field]  [API 1+]
+ valueOf(String): Paint.Cap [static]  [API 1+]
+ values(): Paint.Cap[] [static]  [API 1+]
```

## Paint.FontMetrics — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ FontMetrics()  [API 1+]
+ ascent [field]  [API 1+]
+ bottom [field]  [API 1+]
+ descent [field]  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ leading [field]  [API 1+]
+ toString(): String  [API 1+]
+ top [field]  [API 1+]
```

## Paint.FontMetricsInt — framework; API 1+ (4 +, 0 *, 0 ~, 2 -)

```
+ FontMetricsInt()  [API 1+]
+ ascent [field]  [API 1+]
+ bottom [field]  [API 1+]
+ descent [field]  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ leading [field]  [API 1+]
- set(Paint.FontMetrics): void  [API 35+]
- set(Paint.FontMetricsInt): void  [API 35+]
+ toString(): String  [API 1+]
+ top [field]  [API 1+]
```

## Paint.Join — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BEVEL [field]  [API 1+]
+ MITER [field]  [API 1+]
+ ROUND [field]  [API 1+]
+ valueOf(String): Paint.Join [static]  [API 1+]
+ values(): Paint.Join[] [static]  [API 1+]
```

## Paint.Style — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ FILL [field]  [API 1+]
+ FILL_AND_STROKE [field]  [API 1+]
+ STROKE [field]  [API 1+]
+ valueOf(String): Paint.Style [static]  [API 1+]
+ values(): Paint.Style[] [static]  [API 1+]
```

## PaintFlagsDrawFilter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ PaintFlagsDrawFilter(int, int)  [API 1+]
```

## ParcelableColorSpace — declaration; API 31+ (0 +, 6 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 31+]
* ParcelableColorSpace(ColorSpace)  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getColorSpace(): ColorSpace  [API 31+]
* hashCode(): int  [API 31+]
- isParcelable(ColorSpace): boolean [static]  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## Path — framework; API 1+ (53 +, 0 *, 1 ~, 0 -)

```
+ Path()  [API 1+]
+ Path(Path)  [API 1+]
+ addArc(RectF, float, float): void  [API 1+]
+ addArc(float, float, float, float, float, float): void  [API 21+]
+ addCircle(float, float, float, Path.Direction): void  [API 1+]
+ addOval(RectF, Path.Direction): void  [API 1+]
+ addOval(float, float, float, float, Path.Direction): void  [API 21+]
+ addPath(Path): void  [API 1+]
+ addPath(Path, Matrix): void  [API 1+]
+ addPath(Path, float, float): void  [API 1+]
+ addRect(RectF, Path.Direction): void  [API 1+]
+ addRect(float, float, float, float, Path.Direction): void  [API 1+]
+ addRoundRect(RectF, float, float, Path.Direction): void  [API 1+]
+ addRoundRect(RectF, float[], Path.Direction): void  [API 1+]
+ addRoundRect(float, float, float, float, float, float, Path.Direction): void  [API 21+]
+ addRoundRect(float, float, float, float, float[], Path.Direction): void  [API 21+]
+ approximate(float): float[]  [API 26+]
+ arcTo(RectF, float, float): void  [API 1+]
+ arcTo(RectF, float, float, boolean): void  [API 1+]
+ arcTo(float, float, float, float, float, float, boolean): void  [API 21+]
+ close(): void  [API 1+]
+ computeBounds(RectF, boolean): void  [API 1+]
+ conicTo(float, float, float, float, float): void  [API 34+]
+ cubicTo(float, float, float, float, float, float): void  [API 1+]
+ getFillType(): Path.FillType  [API 1+]
+ getGenerationId(): int  [API 34+]
+ getPathIterator(): PathIterator  [API 34+]
~ incReserve(int): void  [API 1+]
+ interpolate(Path, float, Path): boolean  [API 34+]
+ isConvex(): boolean  [API 21+, deprecated in 30]
+ isEmpty(): boolean  [API 1+]
+ isInterpolatable(Path): boolean  [API 34+]
+ isInverseFillType(): boolean  [API 1+]
+ isRect(RectF): boolean  [API 1+]
+ lineTo(float, float): void  [API 1+]
+ moveTo(float, float): void  [API 1+]
+ offset(float, float): void  [API 1+]
+ offset(float, float, Path): void  [API 1+]
+ op(Path, Path, Path.Op): boolean  [API 19+]
+ op(Path, Path.Op): boolean  [API 19+]
+ quadTo(float, float, float, float): void  [API 1+]
+ rConicTo(float, float, float, float, float): void  [API 34+]
+ rCubicTo(float, float, float, float, float, float): void  [API 1+]
+ rLineTo(float, float): void  [API 1+]
+ rMoveTo(float, float): void  [API 1+]
+ rQuadTo(float, float, float, float): void  [API 1+]
+ reset(): void  [API 1+]
+ rewind(): void  [API 1+]
+ set(Path): void  [API 1+]
+ setFillType(Path.FillType): void  [API 1+]
+ setLastPoint(float, float): void  [API 1+]
+ toggleInverseFillType(): void  [API 1+]
+ transform(Matrix): void  [API 1+]
+ transform(Matrix, Path): void  [API 1+]
```

## Path.Direction — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CCW [field]  [API 1+]
+ CW [field]  [API 1+]
+ valueOf(String): Path.Direction [static]  [API 1+]
+ values(): Path.Direction[] [static]  [API 1+]
```

## Path.FillType — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ EVEN_ODD [field]  [API 1+]
+ INVERSE_EVEN_ODD [field]  [API 1+]
+ INVERSE_WINDING [field]  [API 1+]
+ WINDING [field]  [API 1+]
+ valueOf(String): Path.FillType [static]  [API 1+]
+ values(): Path.FillType[] [static]  [API 1+]
```

## Path.Op — framework; API 19+ (2 +, 0 *, 0 ~, 0 -)

```
+ DIFFERENCE [field]  [API 19+]
+ INTERSECT [field]  [API 19+]
+ REVERSE_DIFFERENCE [field]  [API 19+]
+ UNION [field]  [API 19+]
+ XOR [field]  [API 19+]
+ valueOf(String): Path.Op [static]  [API 19+]
+ values(): Path.Op[] [static]  [API 19+]
```

## PathDashPathEffect — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- PathDashPathEffect(Path, float, float, PathDashPathEffect.Style)  [API 1+]
```

## PathDashPathEffect.Style — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ MORPH [field]  [API 1+]
+ ROTATE [field]  [API 1+]
+ TRANSLATE [field]  [API 1+]
+ valueOf(String): PathDashPathEffect.Style [static]  [API 1+]
+ values(): PathDashPathEffect.Style[] [static]  [API 1+]
```

## PathEffect — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ PathEffect()  [API 1+]
+ finalize(): void  [API 1+]
```

## PathIterator — framework; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ hasNext(): boolean  [API 34+]
+ next(): PathIterator.Segment  [API 34+]
+ next(float[], int): int  [API 34+]
+ peek(): int  [API 34+]
```

## PathIterator.Segment — framework; API 34+ (3 +, 0 *, 0 ~, 0 -)

```
+ getConicWeight(): float  [API 34+]
+ getPoints(): float[]  [API 34+]
+ getVerb(): int  [API 34+]
```

## PathMeasure — framework; API 1+ (9 +, 0 *, 1 ~, 0 -)

```
+ PathMeasure()  [API 1+]
+ PathMeasure(Path, boolean)  [API 1+]
~ finalize(): void  [API 1+]
+ getLength(): float  [API 1+]
+ getMatrix(float, Matrix, int): boolean  [API 1+]
+ getPosTan(float, float[], float[]): boolean  [API 1+]
+ getSegment(float, float, Path, boolean): boolean  [API 1+]
+ isClosed(): boolean  [API 1+]
+ nextContour(): boolean  [API 1+]
+ setPath(Path, boolean): void  [API 1+]
```

## Picture — framework; API 1+ (10 +, 0 *, 1 ~, 0 -)

```
+ Picture()  [API 1+]
+ Picture(Picture)  [API 1+]
+ beginRecording(int, int): Canvas  [API 1+]
+ createFromStream(InputStream): Picture  [API 1-28, removed in 29, deprecated in 18]
+ draw(Canvas): void  [API 1+]
+ endRecording(): void  [API 1+]
+ finalize(): void  [API 1+]
+ getHeight(): int  [API 1+]
+ getWidth(): int  [API 1+]
~ requiresHardwareAcceleration(): boolean  [API 28+]
+ writeToStream(OutputStream): void  [API 1-28, removed in 29, deprecated in 18]
```

## PixelFormat — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ PixelFormat()  [API 1+]
+ bitsPerPixel [field]  [API 1+]
+ bytesPerPixel [field]  [API 1+]
+ formatHasAlpha(int): boolean [static]  [API 1+]
+ getPixelFormatInfo(int, PixelFormat): void [static]  [API 1+]
```

## PixelXorXfermode — declaration; API 1-23, removed in 24, deprecated in 16 (0 +, 0 *, 0 ~, 1 -)

```
- PixelXorXfermode(int)  [API 1-23, removed in 24]
```

## Point — framework; API 1+ (12 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 13+]
+ Point()  [API 1+]
+ Point(Point)  [API 1+]
+ Point(int, int)  [API 1+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ equals(int, int): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ negate(): void  [API 1+]
+ offset(int, int): void  [API 1+]
+ readFromParcel(Parcel): void  [API 13+]
+ set(int, int): void  [API 1+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
+ x [field]  [API 1+]
+ y [field]  [API 1+]
```

## PointF — framework; API 1+ (16 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 13+]
+ PointF()  [API 1+]
+ PointF(Point)  [API 1+]
+ PointF(PointF)  [API 30+]
+ PointF(float, float)  [API 1+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ equals(float, float): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ length(): float  [API 1+]
+ length(float, float): float [static]  [API 1+]
+ negate(): void  [API 1+]
+ offset(float, float): void  [API 1+]
+ readFromParcel(Parcel): void  [API 13+]
+ set(PointF): void  [API 1+]
+ set(float, float): void  [API 1+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
+ x [field]  [API 1+]
+ y [field]  [API 1+]
```

## PorterDuff — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ PorterDuff()  [API 1+]
```

## PorterDuff.Mode — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ADD [field]  [API 11+]
+ CLEAR [field]  [API 1+]
+ DARKEN [field]  [API 1+]
+ DST [field]  [API 1+]
+ DST_ATOP [field]  [API 1+]
+ DST_IN [field]  [API 1+]
+ DST_OUT [field]  [API 1+]
+ DST_OVER [field]  [API 1+]
+ LIGHTEN [field]  [API 1+]
+ MULTIPLY [field]  [API 1+]
+ OVERLAY [field]  [API 11+]
+ SCREEN [field]  [API 1+]
+ SRC [field]  [API 1+]
+ SRC_ATOP [field]  [API 1+]
+ SRC_IN [field]  [API 1+]
+ SRC_OUT [field]  [API 1+]
+ SRC_OVER [field]  [API 1+]
+ XOR [field]  [API 1+]
+ valueOf(String): PorterDuff.Mode [static]  [API 1+]
+ values(): PorterDuff.Mode[] [static]  [API 1+]
```

## PorterDuffColorFilter — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ PorterDuffColorFilter(int, PorterDuff.Mode)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
```

## PorterDuffXfermode — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ PorterDuffXfermode(PorterDuff.Mode)  [API 1+]
```

## PostProcessor — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPostProcess(Canvas): int  [API 28+]
```

## RadialGradient — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ RadialGradient(float, float, float, float, float, float, long[], float[], Shader.TileMode)  [API 31+]
+ RadialGradient(float, float, float, int, int, Shader.TileMode)  [API 1+]
+ RadialGradient(float, float, float, int[], float[], Shader.TileMode)  [API 1+]
+ RadialGradient(float, float, float, long, long, Shader.TileMode)  [API 29+]
+ RadialGradient(float, float, float, long[], float[], Shader.TileMode)  [API 29+]
```

## Rasterizer — declaration; API 1-25, removed in 26, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ Rasterizer()  [API 1-25, removed in 26]
```

## RecordingCanvas — declaration; API 29+ (0 +, 0 *, 0 ~, 64 -)

```
- disableZ(): void  [API 29+]
- drawARGB(int, int, int, int): void  [API 29+]
- drawArc(RectF, float, float, boolean, Paint): void  [API 29+]
- drawArc(float, float, float, float, float, float, boolean, Paint): void  [API 29+]
- drawBitmap(Bitmap, Matrix, Paint): void  [API 29+]
- drawBitmap(Bitmap, Rect, Rect, Paint): void  [API 29+]
- drawBitmap(Bitmap, Rect, RectF, Paint): void  [API 29+]
- drawBitmap(Bitmap, float, float, Paint): void  [API 29+]
- drawBitmap(int[], int, int, float, float, int, int, boolean, Paint): void  [API 29+]
- drawBitmap(int[], int, int, int, int, int, int, boolean, Paint): void  [API 29+]
- drawBitmapMesh(Bitmap, int, int, float[], int, int[], int, Paint): void  [API 29+]
- drawCircle(float, float, float, Paint): void  [API 29+]
- drawColor(int): void  [API 29+]
- drawColor(int, BlendMode): void  [API 29+]
- drawColor(int, PorterDuff.Mode): void  [API 29+]
- drawColor(long, BlendMode): void  [API 29+]
- drawDoubleRoundRect(RectF, float, float, RectF, float, float, Paint): void  [API 29+]
- drawDoubleRoundRect(RectF, float[], RectF, float[], Paint): void  [API 29+]
- drawGlyphs(int[], int, float[], int, int, Font, Paint): void  [API 29+]
- drawLine(float, float, float, float, Paint): void  [API 29+]
- drawLines(float[], Paint): void  [API 29+]
- drawLines(float[], int, int, Paint): void  [API 29+]
- drawMesh(Mesh, BlendMode, Paint): void  [API 29+]
- drawOval(RectF, Paint): void  [API 29+]
- drawOval(float, float, float, float, Paint): void  [API 29+]
- drawPaint(Paint): void  [API 29+]
- drawPatch(NinePatch, Rect, Paint): void  [API 29+]
- drawPatch(NinePatch, RectF, Paint): void  [API 29+]
- drawPath(Path, Paint): void  [API 29+]
- drawPicture(Picture): void  [API 29+]
- drawPicture(Picture, Rect): void  [API 29+]
- drawPicture(Picture, RectF): void  [API 29+]
- drawPoint(float, float, Paint): void  [API 29+]
- drawPoints(float[], Paint): void  [API 29+]
- drawPoints(float[], int, int, Paint): void  [API 29+]
- drawPosText(String, float[], Paint): void  [API 29+]
- drawPosText(char[], int, int, float[], Paint): void  [API 29+]
- drawRGB(int, int, int): void  [API 29+]
- drawRect(Rect, Paint): void  [API 29+]
- drawRect(RectF, Paint): void  [API 29+]
- drawRect(float, float, float, float, Paint): void  [API 29+]
- drawRegion(Region, Paint): void  [API 35+]
- drawRenderNode(RenderNode): void  [API 29+]
- drawRoundRect(RectF, float, float, Paint): void  [API 29+]
- drawRoundRect(float, float, float, float, float, float, Paint): void  [API 29+]
- drawText(CharSequence, int, int, float, float, Paint): void  [API 29+]
- drawText(String, float, float, Paint): void  [API 29+]
- drawText(String, int, int, float, float, Paint): void  [API 29+]
- drawText(char[], int, int, float, float, Paint): void  [API 29+]
- drawTextOnPath(String, Path, float, float, Paint): void  [API 29+]
- drawTextOnPath(char[], int, int, Path, float, float, Paint): void  [API 29+]
- drawTextRun(CharSequence, int, int, int, int, float, float, boolean, Paint): void  [API 29+]
- drawTextRun(MeasuredText, int, int, int, int, float, float, boolean, Paint): void  [API 29+]
- drawTextRun(char[], int, int, int, int, float, float, boolean, Paint): void  [API 29+]
- drawVertices(Canvas.VertexMode, int, float[], int, float[], int, int[], int, short[], int, int, Paint): void  [API 29+]
- enableZ(): void  [API 29+]
- getHeight(): int  [API 29+]
- getMaximumBitmapHeight(): int  [API 29+]
- getMaximumBitmapWidth(): int  [API 29+]
- getWidth(): int  [API 29+]
- isHardwareAccelerated(): boolean  [API 29+]
- isOpaque(): boolean  [API 29+]
- setBitmap(Bitmap): void  [API 29+]
- setDensity(int): void  [API 29+]
```

## Rect — framework; API 1+ (38 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ Rect()  [API 1+]
+ Rect(Rect)  [API 1+]
+ Rect(int, int, int, int)  [API 1+]
+ bottom [field]  [API 1+]
+ centerX(): int  [API 1+]
+ centerY(): int  [API 1+]
+ contains(Rect): boolean  [API 1+]
+ contains(int, int): boolean  [API 1+]
+ contains(int, int, int, int): boolean  [API 1+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ exactCenterX(): float  [API 1+]
+ exactCenterY(): float  [API 1+]
+ flattenToString(): String  [API 7+]
+ hashCode(): int  [API 1+]
+ height(): int  [API 1+]
+ inset(Insets): void  [API 31+]
+ inset(int, int): void  [API 1+]
+ inset(int, int, int, int): void  [API 31+]
+ intersect(Rect): boolean  [API 1+]
+ intersect(int, int, int, int): boolean  [API 1+]
+ intersects(Rect, Rect): boolean [static]  [API 1+]
+ intersects(int, int, int, int): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ left [field]  [API 1+]
+ offset(int, int): void  [API 1+]
+ offsetTo(int, int): void  [API 1+]
+ readFromParcel(Parcel): void  [API 1+]
+ right [field]  [API 1+]
+ set(Rect): void  [API 1+]
+ set(int, int, int, int): void  [API 1+]
+ setEmpty(): void  [API 1+]
+ setIntersect(Rect, Rect): boolean  [API 1+]
+ sort(): void  [API 1+]
+ toShortString(): String  [API 3+]
+ toString(): String  [API 1+]
+ top [field]  [API 1+]
+ unflattenFromString(String): Rect [static]  [API 7+]
+ union(Rect): void  [API 1+]
+ union(int, int): void  [API 1+]
+ union(int, int, int, int): void  [API 1+]
+ width(): int  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## RectF — framework; API 1+ (36 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 3+]
+ RectF()  [API 1+]
+ RectF(Rect)  [API 1+]
+ RectF(RectF)  [API 1+]
+ RectF(float, float, float, float)  [API 1+]
+ bottom [field]  [API 1+]
+ centerX(): float  [API 1+]
+ centerY(): float  [API 1+]
+ contains(RectF): boolean  [API 1+]
+ contains(float, float): boolean  [API 1+]
+ contains(float, float, float, float): boolean  [API 1+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ height(): float  [API 1+]
+ inset(float, float): void  [API 1+]
+ intersect(RectF): boolean  [API 1+]
+ intersect(float, float, float, float): boolean  [API 1+]
+ intersects(RectF, RectF): boolean [static]  [API 1+]
+ intersects(float, float, float, float): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ left [field]  [API 1+]
+ offset(float, float): void  [API 1+]
+ offsetTo(float, float): void  [API 1+]
+ readFromParcel(Parcel): void  [API 3+]
+ right [field]  [API 1+]
+ round(Rect): void  [API 1+]
+ roundOut(Rect): void  [API 1+]
+ set(Rect): void  [API 1+]
+ set(RectF): void  [API 1+]
+ set(float, float, float, float): void  [API 1+]
+ setEmpty(): void  [API 1+]
+ setIntersect(RectF, RectF): boolean  [API 1+]
+ sort(): void  [API 1+]
+ toShortString(): String  [API 14+]
+ toString(): String  [API 1+]
+ top [field]  [API 1+]
+ union(RectF): void  [API 1+]
+ union(float, float): void  [API 1+]
+ union(float, float, float, float): void  [API 1+]
+ width(): float  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## Region — framework; API 1+ (34 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ Region()  [API 1+]
+ Region(Rect)  [API 1+]
+ Region(Region)  [API 1+]
+ Region(int, int, int, int)  [API 1+]
+ contains(int, int): boolean  [API 1+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ finalize(): void  [API 1+]
+ getBoundaryPath(): Path  [API 1+]
+ getBoundaryPath(Path): boolean  [API 1+]
+ getBounds(): Rect  [API 1+]
+ getBounds(Rect): boolean  [API 1+]
+ isComplex(): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ isRect(): boolean  [API 1+]
+ op(Rect, Region, Region.Op): boolean  [API 1+]
+ op(Rect, Region.Op): boolean  [API 1+]
+ op(Region, Region, Region.Op): boolean  [API 1+]
+ op(Region, Region.Op): boolean  [API 1+]
+ op(int, int, int, int, Region.Op): boolean  [API 1+]
+ quickContains(Rect): boolean  [API 1+]
+ quickContains(int, int, int, int): boolean  [API 1+]
+ quickReject(Rect): boolean  [API 1+]
+ quickReject(Region): boolean  [API 1+]
+ quickReject(int, int, int, int): boolean  [API 1+]
+ set(Rect): boolean  [API 1+]
+ set(Region): boolean  [API 1+]
+ set(int, int, int, int): boolean  [API 1+]
+ setEmpty(): void  [API 1+]
+ setPath(Path, Region): boolean  [API 1+]
+ toString(): String  [API 1+]
+ translate(int, int): void  [API 1+]
+ translate(int, int, Region): void  [API 1+]
+ union(Rect): boolean  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## Region.Op — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ DIFFERENCE [field]  [API 1+]
+ INTERSECT [field]  [API 1+]
+ REPLACE [field]  [API 1+]
+ REVERSE_DIFFERENCE [field]  [API 1+]
+ UNION [field]  [API 1+]
+ XOR [field]  [API 1+]
+ valueOf(String): Region.Op [static]  [API 1+]
+ values(): Region.Op[] [static]  [API 1+]
```

## RegionIterator — framework; API 1+ (2 +, 0 *, 1 ~, 0 -)

```
+ RegionIterator(Region)  [API 1+]
~ finalize(): void  [API 1+]
+ next(Rect): boolean  [API 1+]
```

## RenderEffect — declaration; API 31+ (0 +, 0 *, 0 ~, 12 -)

```
- createBitmapEffect(Bitmap): RenderEffect [static]  [API 31+]
- createBitmapEffect(Bitmap, Rect, Rect): RenderEffect [static]  [API 31+]
- createBlendModeEffect(RenderEffect, RenderEffect, BlendMode): RenderEffect [static]  [API 31+]
- createBlurEffect(float, float, RenderEffect, Shader.TileMode): RenderEffect [static]  [API 31+]
- createBlurEffect(float, float, Shader.TileMode): RenderEffect [static]  [API 31+]
- createChainEffect(RenderEffect, RenderEffect): RenderEffect [static]  [API 31+]
- createColorFilterEffect(ColorFilter): RenderEffect [static]  [API 31+]
- createColorFilterEffect(ColorFilter, RenderEffect): RenderEffect [static]  [API 31+]
- createOffsetEffect(float, float): RenderEffect [static]  [API 31+]
- createOffsetEffect(float, float, RenderEffect): RenderEffect [static]  [API 31+]
- createRuntimeShaderEffect(RuntimeShader, String): RenderEffect [static]  [API 33+]
- createShaderEffect(Shader): RenderEffect [static]  [API 31+]
```

## RenderNode — declaration; API 29+ (0 +, 0 *, 0 ~, 69 -)

```
- RenderNode(String)  [API 29+]
- beginRecording(): RecordingCanvas  [API 29+]
- beginRecording(int, int): RecordingCanvas  [API 29+]
- computeApproximateMemoryUsage(): long  [API 29+]
- discardDisplayList(): void  [API 29+]
- endRecording(): void  [API 29+]
- getAlpha(): float  [API 29+]
- getAmbientShadowColor(): int  [API 29+]
- getBottom(): int  [API 29+]
- getCameraDistance(): float  [API 29+]
- getClipToBounds(): boolean  [API 29+]
- getClipToOutline(): boolean  [API 29+]
- getElevation(): float  [API 29+]
- getHeight(): int  [API 29+]
- getInverseMatrix(Matrix): void  [API 29+]
- getLeft(): int  [API 29+]
- getMatrix(Matrix): void  [API 29+]
- getPivotX(): float  [API 29+]
- getPivotY(): float  [API 29+]
- getRight(): int  [API 29+]
- getRotationX(): float  [API 29+]
- getRotationY(): float  [API 29+]
- getRotationZ(): float  [API 29+]
- getScaleX(): float  [API 29+]
- getScaleY(): float  [API 29+]
- getSpotShadowColor(): int  [API 29+]
- getTop(): int  [API 29+]
- getTranslationX(): float  [API 29+]
- getTranslationY(): float  [API 29+]
- getTranslationZ(): float  [API 29+]
- getUniqueId(): long  [API 29+]
- getUseCompositingLayer(): boolean  [API 29+]
- getWidth(): int  [API 29+]
- hasDisplayList(): boolean  [API 29+]
- hasIdentityMatrix(): boolean  [API 29+]
- hasOverlappingRendering(): boolean  [API 29+]
- hasShadow(): boolean  [API 29+]
- isForceDarkAllowed(): boolean  [API 29+]
- isPivotExplicitlySet(): boolean  [API 29+]
- offsetLeftAndRight(int): boolean  [API 29+]
- offsetTopAndBottom(int): boolean  [API 29+]
- resetPivot(): boolean  [API 29+]
- setAlpha(float): boolean  [API 29+]
- setAmbientShadowColor(int): boolean  [API 29+]
- setCameraDistance(float): boolean  [API 29+]
- setClipRect(Rect): boolean  [API 29+]
- setClipToBounds(boolean): boolean  [API 29+]
- setClipToOutline(boolean): boolean  [API 29+]
- setElevation(float): boolean  [API 29+]
- setForceDarkAllowed(boolean): boolean  [API 29+]
- setHasOverlappingRendering(boolean): boolean  [API 29+]
- setOutline(Outline): boolean  [API 29+]
- setPivotX(float): boolean  [API 29+]
- setPivotY(float): boolean  [API 29+]
- setPosition(Rect): boolean  [API 29+]
- setPosition(int, int, int, int): boolean  [API 29+]
- setProjectBackwards(boolean): boolean  [API 29+]
- setProjectionReceiver(boolean): boolean  [API 29+]
- setRenderEffect(RenderEffect): boolean  [API 31+]
- setRotationX(float): boolean  [API 29+]
- setRotationY(float): boolean  [API 29+]
- setRotationZ(float): boolean  [API 29+]
- setScaleX(float): boolean  [API 29+]
- setScaleY(float): boolean  [API 29+]
- setSpotShadowColor(int): boolean  [API 29+]
- setTranslationX(float): boolean  [API 29+]
- setTranslationY(float): boolean  [API 29+]
- setTranslationZ(float): boolean  [API 29+]
- setUseCompositingLayer(boolean, Paint): boolean  [API 29+]
```

## RuntimeShader — declaration; API 33+ (0 +, 0 *, 0 ~, 16 -)

```
- RuntimeShader(String)  [API 33+]
- setColorUniform(String, Color): void  [API 33+]
- setColorUniform(String, int): void  [API 33+]
- setColorUniform(String, long): void  [API 33+]
- setFloatUniform(String, float): void  [API 33+]
- setFloatUniform(String, float, float): void  [API 33+]
- setFloatUniform(String, float, float, float): void  [API 33+]
- setFloatUniform(String, float, float, float, float): void  [API 33+]
- setFloatUniform(String, float[]): void  [API 33+]
- setInputBuffer(String, BitmapShader): void  [API 33+]
- setInputShader(String, Shader): void  [API 33+]
- setIntUniform(String, int): void  [API 33+]
- setIntUniform(String, int, int): void  [API 33+]
- setIntUniform(String, int, int, int): void  [API 33+]
- setIntUniform(String, int, int, int, int): void  [API 33+]
- setIntUniform(String, int[]): void  [API 33+]
```

## Shader — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ Shader()  [API 1+, deprecated in 26]
+ getLocalMatrix(Matrix): boolean  [API 1+]
+ setLocalMatrix(Matrix): void  [API 1+]
```

## Shader.TileMode — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CLAMP [field]  [API 1+]
+ DECAL [field]  [API 31+]
+ MIRROR [field]  [API 1+]
+ REPEAT [field]  [API 1+]
+ valueOf(String): Shader.TileMode [static]  [API 1+]
+ values(): Shader.TileMode[] [static]  [API 1+]
```

## SumPathEffect — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- SumPathEffect(PathEffect, PathEffect)  [API 1+]
```

## SurfaceTexture — declaration; API 11+ (0 +, 0 *, 0 ~, 16 -)

```
- SurfaceTexture(boolean)  [API 26+]
- SurfaceTexture(int)  [API 11+]
- SurfaceTexture(int, boolean)  [API 19+]
- attachToGLContext(int): void  [API 16+]
- detachFromGLContext(): void  [API 16+]
- finalize(): void  [API 11+]
- getDataSpace(): int  [API 33+]
- getTimestamp(): long  [API 14+]
- getTransformMatrix(float[]): void  [API 11+]
- isReleased(): boolean  [API 26+]
- release(): void  [API 14+]
- releaseTexImage(): void  [API 19+]
- setDefaultBufferSize(int, int): void  [API 15+]
- setOnFrameAvailableListener(SurfaceTexture.OnFrameAvailableListener): void  [API 11+]
- setOnFrameAvailableListener(SurfaceTexture.OnFrameAvailableListener, Handler): void  [API 21+]
- updateTexImage(): void  [API 11+]
```

## SurfaceTexture.OnFrameAvailableListener — declaration; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onFrameAvailable(SurfaceTexture): void  [API 11+]
```

## SurfaceTexture.OutOfResourcesException — declaration; API 11+, deprecated in 19 (2 +, 0 *, 0 ~, 0 -)

```
+ OutOfResourcesException()  [API 11+]
+ OutOfResourcesException(String)  [API 11+]
```

## SweepGradient — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ SweepGradient(float, float, int, int)  [API 1+]
+ SweepGradient(float, float, int[], float[])  [API 1+]
+ SweepGradient(float, float, long, long)  [API 29+]
+ SweepGradient(float, float, long[], float[])  [API 29+]
```

## Typeface — framework; API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ DEFAULT [field]  [API 1+]
+ DEFAULT_BOLD [field]  [API 1+]
+ MONOSPACE [field]  [API 1+]
+ SANS_SERIF [field]  [API 1+]
+ SERIF [field]  [API 1+]
+ create(String, int): Typeface [static]  [API 1+]
+ create(Typeface, int): Typeface [static]  [API 1+]
+ create(Typeface, int, boolean): Typeface [static]  [API 28+]
+ createFromAsset(AssetManager, String): Typeface [static]  [API 1+]
+ createFromFile(File): Typeface [static]  [API 4+]
+ createFromFile(String): Typeface [static]  [API 4+]
+ defaultFromStyle(int): Typeface [static]  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getStyle(): int  [API 1+]
+ getSystemFontFamilyName(): String  [API 34+]
+ getWeight(): int  [API 28+]
+ hashCode(): int  [API 1+]
+ isBold(): boolean  [API 1+]
+ isItalic(): boolean  [API 1+]
```

## Typeface.Builder — declaration; API 26+ (0 +, 0 *, 0 ~, 11 -)

```
- Builder(AssetManager, String)  [API 26+]
- Builder(File)  [API 26+]
- Builder(FileDescriptor)  [API 26+]
- Builder(String)  [API 26+]
- build(): Typeface  [API 26+]
- setFallback(String): Typeface.Builder  [API 26+]
- setFontVariationSettings(FontVariationAxis[]): Typeface.Builder  [API 26+]
- setFontVariationSettings(String): Typeface.Builder  [API 26+]
- setItalic(boolean): Typeface.Builder  [API 26+]
- setTtcIndex(int): Typeface.Builder  [API 26+]
- setWeight(int): Typeface.Builder  [API 26+]
```

## Typeface.CustomFallbackBuilder — declaration; API 29+ (0 +, 0 *, 0 ~, 6 -)

```
- CustomFallbackBuilder(FontFamily)  [API 29+]
- addCustomFallback(FontFamily): Typeface.CustomFallbackBuilder  [API 29+]
- build(): Typeface  [API 29+]
- getMaxCustomFallbackCount(): int [static]  [API 29+]
- setStyle(FontStyle): Typeface.CustomFallbackBuilder  [API 29+]
- setSystemFallback(String): Typeface.CustomFallbackBuilder  [API 29+]
```

## Xfermode — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ Xfermode()  [API 1+]
```

## YuvImage — declaration; API 8+ (0 +, 0 *, 0 ~, 10 -)

```
- YuvImage(byte[], int, int, int, int[])  [API 8+]
- YuvImage(byte[], int, int, int, int[], ColorSpace)  [API 34+]
- compressToJpeg(Rect, int, OutputStream): boolean  [API 8+]
- compressToJpegR(YuvImage, int, OutputStream): boolean  [API 34+]
- getColorSpace(): ColorSpace  [API 34+]
- getHeight(): int  [API 8+]
- getStrides(): int[]  [API 8+]
- getWidth(): int  [API 8+]
- getYuvData(): byte[]  [API 8+]
- getYuvFormat(): int  [API 8+]
```


# android.hardware.camera2.params

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BlackLevelPattern — declaration; API 21+ (5 +, 0 *, 0 ~, 1 -)

```
- BlackLevelPattern(int[])  [API 34+]
+ copyTo(int[], int): void  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getOffsetForIndex(int, int): int  [API 21+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
```

## Capability — declaration; API 30+ (0 +, 7 *, 0 ~, 0 -)

```
* Capability(int, Size, Range)  [API 34+]
* equals(Object): boolean  [API 30+]
* getMaxStreamingSize(): Size  [API 30+]
* getMode(): int  [API 30+]
* getZoomRatioRange(): Range  [API 30+]
* hashCode(): int  [API 30+]
* toString(): String  [API 30+]
```

## ColorSpaceProfiles — declaration; API 34+ (4 +, 0 *, 0 ~, 1 -)

```
- ColorSpaceProfiles(long[])  [API 34+]
+ getSupportedColorSpaces(int): Set  [API 34+]
+ getSupportedColorSpacesForDynamicRange(int, long): Set  [API 34+]
+ getSupportedDynamicRangeProfiles(ColorSpace.Named, int): Set  [API 34+]
+ getSupportedImageFormatsForColorSpace(ColorSpace.Named): Set  [API 34+]
```

## ColorSpaceTransform — declaration; API 21+ (6 +, 0 *, 0 ~, 2 -)

```
- ColorSpaceTransform(Rational[])  [API 21+]
- ColorSpaceTransform(int[])  [API 21+]
+ copyElements(Rational[], int): void  [API 21+]
+ copyElements(int[], int): void  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getElement(int, int): Rational  [API 21+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
```

## DeviceStateSensorOrientationMap — declaration; API 32+ (3 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 32+]
+ getSensorOrientation(long): int  [API 32+]
+ hashCode(): int  [API 32+]
```

## DeviceStateSensorOrientationMap.Builder — declaration; API 34+ (2 +, 0 *, 0 ~, 1 -)

```
- Builder()  [API 34+]
+ addOrientationForState(long, long): DeviceStateSensorOrientationMap.Builder  [API 34+]
+ build(): DeviceStateSensorOrientationMap  [API 34+]
```

## DynamicRangeProfiles — declaration; API 33+ (3 +, 0 *, 0 ~, 1 -)

```
- DynamicRangeProfiles(long[])  [API 33+]
+ getProfileCaptureRequestConstraints(long): Set  [API 33+]
+ getSupportedProfiles(): Set  [API 33+]
+ isExtraLatencyPresent(long): boolean  [API 33+]
```

## ExtensionSessionConfiguration — declaration; API 31+ (7 +, 2 *, 0 ~, 1 -)

```
- ExtensionSessionConfiguration(int, List, Executor, CameraExtensionSession.StateCallback)  [API 31+]
+ clearColorSpace(): void  [API 35+]
+ getColorSpace(): ColorSpace  [API 35+]
+ getExecutor(): Executor  [API 31+]
+ getExtension(): int  [API 31+]
+ getOutputConfigurations(): List  [API 31+]
* getPostviewOutputConfiguration(): OutputConfiguration  [API 34+]
+ getStateCallback(): CameraExtensionSession.StateCallback  [API 31+]
+ setColorSpace(ColorSpace.Named): void  [API 35+]
* setPostviewOutputConfiguration(OutputConfiguration): void  [API 34+]
```

## Face — declaration; API 21+ (0 +, 7 *, 0 ~, 0 -)

```
* getBounds(): Rect  [API 21+]
* getId(): int  [API 21+]
* getLeftEyePosition(): Point  [API 21+]
* getMouthPosition(): Point  [API 21+]
* getRightEyePosition(): Point  [API 21+]
* getScore(): int  [API 21+]
* toString(): String  [API 21+]
```

## Face.Builder — declaration; API 34+ (0 +, 9 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* Builder(Face)  [API 34+]
* build(): Face  [API 34+]
* setBounds(Rect): Face.Builder  [API 34+]
* setId(int): Face.Builder  [API 34+]
* setLeftEyePosition(Point): Face.Builder  [API 34+]
* setMouthPosition(Point): Face.Builder  [API 34+]
* setRightEyePosition(Point): Face.Builder  [API 34+]
* setScore(int): Face.Builder  [API 34+]
```

## InputConfiguration — declaration; API 23+ (4 +, 4 *, 0 ~, 1 -)

```
- InputConfiguration(Collection, int)  [API 31+]
* InputConfiguration(int, int, int)  [API 23+]
+ equals(Object): boolean  [API 23+]
* getFormat(): int  [API 23+]
* getHeight(): int  [API 23+]
* getWidth(): int  [API 23+]
+ hashCode(): int  [API 23+]
+ isMultiResolution(): boolean  [API 31+]
+ toString(): String  [API 23+]
```

## LensIntrinsicsSample — declaration; API 35+ (5 +, 0 *, 0 ~, 1 -)

```
- LensIntrinsicsSample(long, float[])  [API 35+]
+ equals(Object): boolean  [API 35+]
+ getLensIntrinsics(): float[]  [API 35+]
+ getTimestampNanos(): long  [API 35+]
+ hashCode(): int  [API 35+]
+ toString(): String  [API 35+]
```

## LensShadingMap — declaration; API 21+ (9 +, 0 *, 0 ~, 0 -)

```
+ copyGainFactors(float[], int): void  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getColumnCount(): int  [API 21+]
+ getGainFactor(int, int, int): float  [API 21+]
+ getGainFactorCount(): int  [API 21+]
+ getGainFactorVector(int, int): RggbChannelVector  [API 21+]
+ getRowCount(): int  [API 21+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
```

## MandatoryStreamCombination — declaration; API 29+ (5 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 29+]
+ getDescription(): CharSequence  [API 29+]
+ getStreamsInformation(): List  [API 29+]
+ hashCode(): int  [API 29+]
+ isReprocessable(): boolean  [API 29+]
```

## MandatoryStreamCombination.MandatoryStreamInformation — declaration; API 29+ (10 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 29+]
+ get10BitFormat(): int  [API 33+]
+ getAvailableSizes(): List  [API 29+]
+ getFormat(): int  [API 29+]
+ getStreamUseCase(): long  [API 33+]
+ hashCode(): int  [API 29+]
+ is10BitCapable(): boolean  [API 33+]
+ isInput(): boolean  [API 29+]
+ isMaximumSize(): boolean  [API 31+]
+ isUltraHighResolution(): boolean  [API 31+]
```

## MeteringRectangle — declaration; API 21+ (6 +, 8 *, 0 ~, 1 -)

```
- MeteringRectangle(Point, Size, int)  [API 21+]
* MeteringRectangle(Rect, int)  [API 21+]
* MeteringRectangle(int, int, int, int, int)  [API 21+]
+ equals(MeteringRectangle): boolean  [API 21+]
+ equals(Object): boolean  [API 21+]
* getHeight(): int  [API 21+]
* getMeteringWeight(): int  [API 21+]
* getRect(): Rect  [API 21+]
+ getSize(): Size  [API 21+]
+ getUpperLeftPoint(): Point  [API 21+]
* getWidth(): int  [API 21+]
* getX(): int  [API 21+]
* getY(): int  [API 21+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
```

## MultiResolutionStreamConfigurationMap — declaration; API 31+ (7 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 31+]
+ getInputFormats(): int[]  [API 31+]
+ getInputInfo(int): Collection  [API 31+]
+ getOutputFormats(): int[]  [API 31+]
+ getOutputInfo(int): Collection  [API 31+]
+ hashCode(): int  [API 31+]
+ toString(): String  [API 31+]
```

## MultiResolutionStreamInfo — declaration; API 31+ (5 +, 0 *, 0 ~, 1 -)

```
- MultiResolutionStreamInfo(int, int, String)  [API 31+]
+ equals(Object): boolean  [API 31+]
+ getHeight(): int  [API 31+]
+ getPhysicalCameraId(): String  [API 31+]
+ getWidth(): int  [API 31+]
+ hashCode(): int  [API 31+]
```

## OisSample — declaration; API 28+ (0 +, 7 *, 0 ~, 0 -)

```
* OisSample(long, float, float)  [API 28+]
* equals(Object): boolean  [API 28+]
* getTimestamp(): long  [API 28+]
* getXshift(): float  [API 28+]
* getYshift(): float  [API 28+]
* hashCode(): int  [API 28+]
* toString(): String  [API 28+]
```

## OutputConfiguration — declaration; API 24+ (15 +, 14 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 24+]
- OutputConfiguration(Size, Class)  [API 26+]
* OutputConfiguration(Surface)  [API 24+]
- OutputConfiguration(int, Size)  [API 35+]
- OutputConfiguration(int, Size, long)  [API 35+]
* OutputConfiguration(int, Surface)  [API 24+]
- OutputConfiguration(int, int, Size)  [API 35+]
- OutputConfiguration(int, int, Size, long)  [API 35+]
+ addSensorPixelModeUsed(int): void  [API 31+]
+ addSurface(Surface): void  [API 26+]
+ createInstancesForMultiResolutionOutput(Collection, int): List [static]  [API 35+]
+ createInstancesForMultiResolutionOutput(MultiResolutionImageReader): Collection [static]  [API 31+]
+ describeContents(): int  [API 24+]
+ enableSurfaceSharing(): void  [API 26+]
+ equals(Object): boolean  [API 24+]
* getDynamicRangeProfile(): long  [API 33+]
+ getMaxSharedSurfaceCount(): int  [API 28+]
* getMirrorMode(): int  [API 33+]
* getStreamUseCase(): long  [API 33+]
* getSurface(): Surface  [API 24+]
* getSurfaceGroupId(): int  [API 24+]
+ getSurfaces(): List  [API 26+]
* getTimestampBase(): int  [API 33+]
+ hashCode(): int  [API 24+]
* isReadoutTimestampEnabled(): boolean  [API 34+]
+ removeSensorPixelModeUsed(int): void  [API 31+]
+ removeSurface(Surface): void  [API 28+]
* setDynamicRangeProfile(long): void  [API 33+]
* setMirrorMode(int): void  [API 33+]
+ setPhysicalCameraId(String): void  [API 28+]
* setReadoutTimestampEnabled(boolean): void  [API 34+]
* setStreamUseCase(long): void  [API 33+]
+ setSurfacesForMultiResolutionOutput(Collection, MultiResolutionImageReader): void [static]  [API 35+]
* setTimestampBase(int): void  [API 33+]
+ writeToParcel(Parcel, int): void  [API 24+]
```

## RecommendedStreamConfigurationMap — declaration; API 29+ (18 +, 0 *, 0 ~, 0 -)

```
+ getHighResolutionOutputSizes(int): Set  [API 29+]
+ getHighSpeedVideoFpsRanges(): Set  [API 29+]
+ getHighSpeedVideoFpsRangesFor(Size): Set  [API 29+]
+ getHighSpeedVideoSizes(): Set  [API 29+]
+ getHighSpeedVideoSizesFor(Range): Set  [API 29+]
+ getInputFormats(): Set  [API 29+]
+ getInputSizes(int): Set  [API 29+]
+ getOutputFormats(): Set  [API 29+]
+ getOutputMinFrameDuration(Class, Size): long  [API 29+]
+ getOutputMinFrameDuration(int, Size): long  [API 29+]
+ getOutputSizes(Class): Set  [API 29+]
+ getOutputSizes(int): Set  [API 29+]
+ getOutputStallDuration(Class, Size): long  [API 29+]
+ getOutputStallDuration(int, Size): long  [API 29+]
+ getRecommendedUseCase(): int  [API 29+]
+ getValidOutputFormatsForInput(int): Set  [API 29+]
+ isOutputSupportedFor(Surface): boolean  [API 29+]
+ isOutputSupportedFor(int): boolean  [API 29+]
```

## RggbChannelVector — declaration; API 21+ (2 +, 8 *, 0 ~, 0 -)

```
* RggbChannelVector(float, float, float, float)  [API 21+]
+ copyTo(float[], int): void  [API 21+]
* equals(Object): boolean  [API 21+]
* getBlue(): float  [API 21+]
+ getComponent(int): float  [API 21+]
* getGreenEven(): float  [API 21+]
* getGreenOdd(): float  [API 21+]
* getRed(): float  [API 21+]
* hashCode(): int  [API 21+]
* toString(): String  [API 21+]
```

## SessionConfiguration — declaration; API 28+ (12 +, 4 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 29+]
- SessionConfiguration(int, List)  [API 35+]
- SessionConfiguration(int, List, Executor, CameraCaptureSession.StateCallback)  [API 28+]
+ clearColorSpace(): void  [API 34+]
+ describeContents(): int  [API 28+]
+ equals(Object): boolean  [API 28+]
+ getColorSpace(): ColorSpace  [API 34+]
+ getExecutor(): Executor  [API 28+]
* getInputConfiguration(): InputConfiguration  [API 28+]
+ getOutputConfigurations(): List  [API 28+]
* getSessionParameters(): CaptureRequest  [API 28+]
+ getSessionType(): int  [API 28+]
+ getStateCallback(): CameraCaptureSession.StateCallback  [API 28+]
+ hashCode(): int  [API 28+]
+ setColorSpace(ColorSpace.Named): void  [API 34+]
* setInputConfiguration(InputConfiguration): void  [API 28+]
* setSessionParameters(CaptureRequest): void  [API 28+]
+ setStateCallback(Executor, CameraCaptureSession.StateCallback): void  [API 35+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## StreamConfigurationMap — declaration; API 21+ (21 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 21+]
+ getHighResolutionOutputSizes(int): Size[]  [API 23+]
+ getHighSpeedVideoFpsRanges(): Range[]  [API 21+]
+ getHighSpeedVideoFpsRangesFor(Size): Range[]  [API 21+]
+ getHighSpeedVideoSizes(): Size[]  [API 21+]
+ getHighSpeedVideoSizesFor(Range): Size[]  [API 21+]
+ getInputFormats(): int[]  [API 23+]
+ getInputSizes(int): Size[]  [API 23+]
+ getOutputFormats(): int[]  [API 21+]
+ getOutputMinFrameDuration(Class, Size): long  [API 21+]
+ getOutputMinFrameDuration(int, Size): long  [API 21+]
+ getOutputSizes(Class): Size[]  [API 21+]
+ getOutputSizes(int): Size[]  [API 21+]
+ getOutputStallDuration(Class, Size): long  [API 21+]
+ getOutputStallDuration(int, Size): long  [API 21+]
+ getValidOutputFormatsForInput(int): int[]  [API 23+]
+ hashCode(): int  [API 21+]
+ isOutputSupportedFor(Class): boolean [static]  [API 21+]
+ isOutputSupportedFor(Surface): boolean  [API 21+]
+ isOutputSupportedFor(int): boolean  [API 21+]
+ toString(): String  [API 21+]
```

## TonemapCurve — declaration; API 21+ (6 +, 0 *, 0 ~, 1 -)

```
- TonemapCurve(float[], float[], float[])  [API 21+]
+ copyColorCurve(int, float[], int): void  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getPoint(int, int): PointF  [API 21+]
+ getPointCount(int): int  [API 21+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
```


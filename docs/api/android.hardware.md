# android.hardware

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BatteryState — declaration; API 31+ (4 +, 0 *, 0 ~, 0 -)

```
+ BatteryState()  [API 31+]
+ getCapacity(): float  [API 31+]
+ getStatus(): int  [API 31+]
+ isPresent(): boolean  [API 31+]
```

## Camera — declaration; API 1+, deprecated in 21 (31 +, 2 *, 0 ~, 0 -)

```
+ addCallbackBuffer(byte[]): void  [API 8+]
+ autoFocus(Camera.AutoFocusCallback): void  [API 1+]
+ cancelAutoFocus(): void  [API 5+]
+ enableShutterSound(boolean): boolean  [API 17+]
+ finalize(): void  [API 1+]
+ getCameraInfo(int, Camera.CameraInfo): void [static]  [API 9+]
+ getNumberOfCameras(): int [static]  [API 9+]
* getParameters(): Camera.Parameters  [API 1+]
+ lock(): void  [API 5+]
+ open(): Camera [static]  [API 1+]
+ open(int): Camera [static]  [API 9+]
+ reconnect(): void  [API 8+]
+ release(): void  [API 1+]
+ setAutoFocusMoveCallback(Camera.AutoFocusMoveCallback): void  [API 16+]
+ setDisplayOrientation(int): void  [API 8+]
+ setErrorCallback(Camera.ErrorCallback): void  [API 1+]
+ setFaceDetectionListener(Camera.FaceDetectionListener): void  [API 14+]
+ setOneShotPreviewCallback(Camera.PreviewCallback): void  [API 3+]
* setParameters(Camera.Parameters): void  [API 1+]
+ setPreviewCallback(Camera.PreviewCallback): void  [API 1+]
+ setPreviewCallbackWithBuffer(Camera.PreviewCallback): void  [API 8+]
+ setPreviewDisplay(SurfaceHolder): void  [API 1+]
+ setPreviewTexture(SurfaceTexture): void  [API 11+]
+ setZoomChangeListener(Camera.OnZoomChangeListener): void  [API 8+]
+ startFaceDetection(): void  [API 14+]
+ startPreview(): void  [API 1+]
+ startSmoothZoom(int): void  [API 8+]
+ stopFaceDetection(): void  [API 14+]
+ stopPreview(): void  [API 1+]
+ stopSmoothZoom(): void  [API 8+]
+ takePicture(Camera.ShutterCallback, Camera.PictureCallback, Camera.PictureCallback): void  [API 1+]
+ takePicture(Camera.ShutterCallback, Camera.PictureCallback, Camera.PictureCallback, Camera.PictureCallback): void  [API 5+]
+ unlock(): void  [API 5+]
```

## Camera.Area — declaration; API 14+, deprecated in 21 (1 +, 1 *, 0 ~, 0 -)

```
* Area(Rect, int)  [API 14+]
+ equals(Object): boolean  [API 14+]
+ rect [field]  [API 14+]
+ weight [field]  [API 14+]
```

## Camera.AutoFocusCallback — declaration; API 1+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ onAutoFocus(boolean, Camera): void  [API 1+]
```

## Camera.AutoFocusMoveCallback — declaration; API 16+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ onAutoFocusMoving(boolean, Camera): void  [API 16+]
```

## Camera.CameraInfo — declaration; API 9+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ CameraInfo()  [API 9+]
+ canDisableShutterSound [field]  [API 17+]
+ facing [field]  [API 9+]
+ orientation [field]  [API 9+]
```

## Camera.ErrorCallback — declaration; API 1+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ onError(int, Camera): void  [API 1+]
```

## Camera.Face — declaration; API 14+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ Face()  [API 14+]
+ id [field]  [API 14+]
+ leftEye [field]  [API 14+]
+ mouth [field]  [API 14+]
+ rect [field]  [API 14+]
+ rightEye [field]  [API 14+]
+ score [field]  [API 14+]
```

## Camera.FaceDetectionListener — declaration; API 14+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ onFaceDetection(Camera.Face[], Camera): void  [API 14+]
```

## Camera.OnZoomChangeListener — declaration; API 8+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ onZoomChange(int, boolean, Camera): void  [API 8+]
```

## Camera.Parameters — declaration; API 1+, deprecated in 21 (56 +, 36 *, 0 ~, 0 -)

```
+ flatten(): String  [API 1+]
+ get(String): String  [API 1+]
* getAntibanding(): String  [API 5+]
* getAutoExposureLock(): boolean  [API 14+]
* getAutoWhiteBalanceLock(): boolean  [API 14+]
* getColorEffect(): String  [API 5+]
* getExposureCompensation(): int  [API 8+]
+ getExposureCompensationStep(): float  [API 8+]
* getFlashMode(): String  [API 5+]
+ getFocalLength(): float  [API 8+]
* getFocusAreas(): List  [API 14+]
+ getFocusDistances(float[]): void  [API 9+]
* getFocusMode(): String  [API 5+]
+ getHorizontalViewAngle(): float  [API 8+]
+ getInt(String): int  [API 1+]
* getJpegQuality(): int  [API 5+]
* getJpegThumbnailQuality(): int  [API 5+]
+ getJpegThumbnailSize(): Camera.Size  [API 5+]
+ getMaxExposureCompensation(): int  [API 8+]
+ getMaxNumDetectedFaces(): int  [API 14+]
+ getMaxNumFocusAreas(): int  [API 14+]
+ getMaxNumMeteringAreas(): int  [API 14+]
+ getMaxZoom(): int  [API 8+]
* getMeteringAreas(): List  [API 14+]
+ getMinExposureCompensation(): int  [API 8+]
* getPictureFormat(): int  [API 1+]
+ getPictureSize(): Camera.Size  [API 1+]
+ getPreferredPreviewSizeForVideo(): Camera.Size  [API 11+]
* getPreviewFormat(): int  [API 1+]
+ getPreviewFpsRange(int[]): void  [API 9+]
* getPreviewFrameRate(): int  [API 1+, deprecated in 15]
+ getPreviewSize(): Camera.Size  [API 1+]
* getSceneMode(): String  [API 5+]
+ getSupportedAntibanding(): List  [API 5+]
+ getSupportedColorEffects(): List  [API 5+]
+ getSupportedFlashModes(): List  [API 5+]
+ getSupportedFocusModes(): List  [API 5+]
+ getSupportedJpegThumbnailSizes(): List  [API 8+]
+ getSupportedPictureFormats(): List  [API 5+]
+ getSupportedPictureSizes(): List  [API 5+]
+ getSupportedPreviewFormats(): List  [API 5+]
+ getSupportedPreviewFpsRange(): List  [API 9+]
+ getSupportedPreviewFrameRates(): List  [API 5+, deprecated in 15]
+ getSupportedPreviewSizes(): List  [API 5+]
+ getSupportedSceneModes(): List  [API 5+]
+ getSupportedVideoSizes(): List  [API 11+]
+ getSupportedWhiteBalance(): List  [API 5+]
+ getVerticalViewAngle(): float  [API 8+]
* getVideoStabilization(): boolean  [API 15+]
* getWhiteBalance(): String  [API 5+]
* getZoom(): int  [API 8+]
+ getZoomRatios(): List  [API 8+]
+ isAutoExposureLockSupported(): boolean  [API 14+]
+ isAutoWhiteBalanceLockSupported(): boolean  [API 14+]
+ isSmoothZoomSupported(): boolean  [API 8+]
+ isVideoSnapshotSupported(): boolean  [API 14+]
+ isVideoStabilizationSupported(): boolean  [API 15+]
+ isZoomSupported(): boolean  [API 8+]
+ remove(String): void  [API 1+]
+ removeGpsData(): void  [API 5+]
+ set(String, String): void  [API 1+]
+ set(String, int): void  [API 1+]
* setAntibanding(String): void  [API 5+]
* setAutoExposureLock(boolean): void  [API 14+]
* setAutoWhiteBalanceLock(boolean): void  [API 14+]
* setColorEffect(String): void  [API 5+]
* setExposureCompensation(int): void  [API 8+]
* setFlashMode(String): void  [API 5+]
* setFocusAreas(List): void  [API 14+]
* setFocusMode(String): void  [API 5+]
+ setGpsAltitude(double): void  [API 5+]
+ setGpsLatitude(double): void  [API 5+]
+ setGpsLongitude(double): void  [API 5+]
+ setGpsProcessingMethod(String): void  [API 8+]
+ setGpsTimestamp(long): void  [API 5+]
* setJpegQuality(int): void  [API 5+]
* setJpegThumbnailQuality(int): void  [API 5+]
+ setJpegThumbnailSize(int, int): void  [API 5+]
* setMeteringAreas(List): void  [API 14+]
* setPictureFormat(int): void  [API 1+]
+ setPictureSize(int, int): void  [API 1+]
* setPreviewFormat(int): void  [API 1+]
+ setPreviewFpsRange(int, int): void  [API 9+]
* setPreviewFrameRate(int): void  [API 1+, deprecated in 15]
+ setPreviewSize(int, int): void  [API 1+]
+ setRecordingHint(boolean): void  [API 14+]
+ setRotation(int): void  [API 5+]
* setSceneMode(String): void  [API 5+]
* setVideoStabilization(boolean): void  [API 15+]
* setWhiteBalance(String): void  [API 5+]
* setZoom(int): void  [API 8+]
+ unflatten(String): void  [API 1+]
```

## Camera.PictureCallback — declaration; API 1+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ onPictureTaken(byte[], Camera): void  [API 1+]
```

## Camera.PreviewCallback — declaration; API 1+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ onPreviewFrame(byte[], Camera): void  [API 1+]
```

## Camera.ShutterCallback — declaration; API 1+, deprecated in 21 (1 +, 0 *, 0 ~, 0 -)

```
+ onShutter(): void  [API 1+]
```

## Camera.Size — declaration; API 1+, deprecated in 21 (2 +, 0 *, 0 ~, 1 -)

```
- Size(Camera, int, int)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ height [field]  [API 1+]
+ width [field]  [API 1+]
```

## ConsumerIrManager — framework; API 19+ (3 +, 0 *, 0 ~, 0 -)

```
+ getCarrierFrequencies(): ConsumerIrManager.CarrierFrequencyRange[]  [API 19+]
+ hasIrEmitter(): boolean  [API 19+]
+ transmit(int, int[]): void  [API 19+]
```

## ConsumerIrManager.CarrierFrequencyRange — framework; API 19+ (2 +, 0 *, 0 ~, 1 -)

```
- CarrierFrequencyRange(ConsumerIrManager, int, int)  [API 19+]
+ getMaxFrequency(): int  [API 19+]
+ getMinFrequency(): int  [API 19+]
```

## DataSpace — declaration; API 33+ (0 +, 0 *, 0 ~, 4 -)

```
- getRange(int): int [static]  [API 33+]
- getStandard(int): int [static]  [API 33+]
- getTransfer(int): int [static]  [API 33+]
- pack(int, int, int): int [static]  [API 33+]
```

## GeomagneticField — declaration; API 3+ (0 +, 0 *, 0 ~, 8 -)

```
- GeomagneticField(float, float, float, long)  [API 3+]
- getDeclination(): float  [API 3+]
- getFieldStrength(): float  [API 3+]
- getHorizontalStrength(): float  [API 3+]
- getInclination(): float  [API 3+]
- getX(): float  [API 3+]
- getY(): float  [API 3+]
- getZ(): float  [API 3+]
```

## HardwareBuffer — declaration; API 26+ (0 +, 0 *, 0 ~, 13 -)

```
- CREATOR [field]  [API 26+]
- close(): void  [API 26+]
- create(int, int, int, int, long): HardwareBuffer [static]  [API 26+]
- describeContents(): int  [API 26+]
- finalize(): void  [API 26+]
- getFormat(): int  [API 26+]
- getHeight(): int  [API 26+]
- getId(): long  [API 34+]
- getLayers(): int  [API 26+]
- getUsage(): long  [API 26+]
- getWidth(): int  [API 26+]
- isClosed(): boolean  [API 26+]
- isSupported(int, int, int, int, long): boolean [static]  [API 29+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## OverlayProperties — framework; API 35+ (0 +, 0 *, 4 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
~ describeContents(): int  [API 35+]
~ isCombinationSupported(int, int): boolean  [API 35+]
~ isMixedColorSpacesSupported(): boolean  [API 35+]
~ writeToParcel(Parcel, int): void  [API 35+]
```

## Sensor — framework; API 3+ (3 +, 0 *, 6 ~, 11 -)

```
- getFifoMaxEventCount(): int  [API 19+]
- getFifoReservedEventCount(): int  [API 19+]
- getHighestDirectReportRateLevel(): int  [API 26+]
- getId(): int  [API 24+]
- getMaxDelay(): int  [API 21+]
~ getMaximumRange(): float  [API 3+]
~ getMinDelay(): int  [API 9+]
+ getName(): String  [API 3+]
~ getPower(): float  [API 3+]
- getReportingMode(): int  [API 21+]
~ getResolution(): float  [API 3+]
- getStringType(): String  [API 20+]
+ getType(): int  [API 3+]
~ getVendor(): String  [API 3+]
~ getVersion(): int  [API 3+]
- isAdditionalInfoSupported(): boolean  [API 24+]
- isDirectChannelTypeSupported(int): boolean  [API 26+]
- isDynamicSensor(): boolean  [API 24+]
- isWakeUpSensor(): boolean  [API 21+]
+ toString(): String  [API 3+]
```

## SensorAdditionalInfo — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

```
- floatValues [field]  [API 24+]
- intValues [field]  [API 24+]
- sensor [field]  [API 24+]
- serial [field]  [API 24+]
- type [field]  [API 24+]
```

## SensorDirectChannel — declaration; API 26+ (0 +, 0 *, 0 ~, 4 -)

```
- close(): void  [API 26+]
- configure(Sensor, int): int  [API 26+]
- finalize(): void  [API 26+]
- isOpen(): boolean  [API 26+]
```

## SensorEvent — framework; API 3+ (0 +, 0 *, 0 ~, 0 -)

```
+ accuracy [field]  [API 3+]
- firstEventAfterDiscontinuity [field]  [API 33+]
+ sensor [field]  [API 3+]
+ timestamp [field]  [API 3+]
+ values [field]  [API 3+]
```

## SensorEventCallback — declaration; API 24+ (5 +, 0 *, 0 ~, 0 -)

```
+ SensorEventCallback()  [API 24+]
+ onAccuracyChanged(Sensor, int): void  [API 24+]
+ onFlushCompleted(Sensor): void  [API 24+]
+ onSensorAdditionalInfo(SensorAdditionalInfo): void  [API 24+]
+ onSensorChanged(SensorEvent): void  [API 24+]
```

## SensorEventListener — framework; API 3+ (2 +, 0 *, 0 ~, 0 -)

```
+ onAccuracyChanged(Sensor, int): void  [API 3+]
+ onSensorChanged(SensorEvent): void  [API 3+]
```

## SensorEventListener2 — declaration; API 19+ (1 +, 0 *, 0 ~, 0 -)

```
+ onFlushCompleted(Sensor): void  [API 19+]
```

## SensorListener — framework; API 1+, deprecated in 3 (2 +, 0 *, 0 ~, 0 -)

```
+ onAccuracyChanged(int, int): void  [API 1+]
+ onSensorChanged(int, float[]): void  [API 1+]
```

## SensorManager — framework; API 1+ (3 +, 0 *, 8 ~, 21 -)

```
- cancelTriggerSensor(TriggerEventListener, Sensor): boolean  [API 18+]
- createDirectChannel(HardwareBuffer): SensorDirectChannel  [API 26+]
- createDirectChannel(MemoryFile): SensorDirectChannel  [API 26+]
- flush(SensorEventListener): boolean  [API 19+]
- getAltitude(float, float): float [static]  [API 9+]
- getAngleChange(float[], float[], float[]): void [static]  [API 9+]
+ getDefaultSensor(int): Sensor  [API 3+]
- getDefaultSensor(int, boolean): Sensor  [API 21+]
- getDynamicSensorList(int): List  [API 24+]
- getInclination(float[]): float [static]  [API 3+]
+ getOrientation(float[], float[]): float[] [static]  [API 3+]
- getQuaternionFromVector(float[], float[]): void [static]  [API 9+]
~ getRotationMatrix(float[], float[], float[], float[]): boolean [static]  [API 3+]
- getRotationMatrixFromVector(float[], float[]): void [static]  [API 9+]
+ getSensorList(int): List  [API 3+]
- getSensors(): int  [API 1+, deprecated in 15]
- isDynamicSensorDiscoverySupported(): boolean  [API 24+]
- registerDynamicSensorCallback(SensorManager.DynamicSensorCallback): void  [API 24+]
- registerDynamicSensorCallback(SensorManager.DynamicSensorCallback, Handler): void  [API 24+]
~ registerListener(SensorEventListener, Sensor, int): boolean  [API 3+]
~ registerListener(SensorEventListener, Sensor, int, Handler): boolean  [API 3+]
- registerListener(SensorEventListener, Sensor, int, int): boolean  [API 19+]
- registerListener(SensorEventListener, Sensor, int, int, Handler): boolean  [API 19+]
~ registerListener(SensorListener, int): boolean  [API 1+, deprecated in 15]
~ registerListener(SensorListener, int, int): boolean  [API 1+, deprecated in 15]
- remapCoordinateSystem(float[], int, int, float[]): boolean [static]  [API 3+]
- requestTriggerSensor(TriggerEventListener, Sensor): boolean  [API 18+]
- unregisterDynamicSensorCallback(SensorManager.DynamicSensorCallback): void  [API 24+]
~ unregisterListener(SensorEventListener): void  [API 3+]
~ unregisterListener(SensorEventListener, Sensor): void  [API 3+]
~ unregisterListener(SensorListener): void  [API 1+, deprecated in 15]
- unregisterListener(SensorListener, int): void  [API 1+, deprecated in 15]
```

## SensorManager.DynamicSensorCallback — declaration; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ DynamicSensorCallback()  [API 24+]
+ onDynamicSensorConnected(Sensor): void  [API 24+]
+ onDynamicSensorDisconnected(Sensor): void  [API 24+]
```

## SensorPrivacyManager — declaration; API 31+ (2 +, 0 *, 0 ~, 0 -)

```
+ supportsSensorToggle(int): boolean  [API 31+]
+ supportsSensorToggle(int, int): boolean  [API 33+]
```

## SensorPrivacyManager.Sensors — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## SyncFence — declaration; API 33+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 33+]
- SyncFence(SyncFence)  [API 35+]
- await(Duration): boolean  [API 33+]
- awaitForever(): boolean  [API 33+]
- close(): void  [API 33+]
- describeContents(): int  [API 33+]
- getSignalTime(): long  [API 33+]
- isValid(): boolean  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## TriggerEvent — declaration; API 18+ (0 +, 0 *, 0 ~, 0 -)

```
+ sensor [field]  [API 18+]
+ timestamp [field]  [API 18+]
- values [field]  [API 18+]
```

## TriggerEventListener — declaration; API 18+ (2 +, 0 *, 0 ~, 0 -)

```
+ TriggerEventListener()  [API 18+]
+ onTrigger(TriggerEvent): void  [API 18+]
```


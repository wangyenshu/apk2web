# android.hardware.camera2

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CameraAccessException — declaration; API 21+ (1 +, 0 *, 0 ~, 4 -)

```
- CameraAccessException(int)  [API 21+]
- CameraAccessException(int, String)  [API 21+]
- CameraAccessException(int, String, Throwable)  [API 21+]
- CameraAccessException(int, Throwable)  [API 21+]
+ getReason(): int  [API 21+]
```

## CameraCaptureSession — declaration; API 21+ (19 +, 0 *, 0 ~, 1 -)

```
- CameraCaptureSession()  [API 21+]
+ abortCaptures(): void  [API 21+]
+ capture(CaptureRequest, CameraCaptureSession.CaptureCallback, Handler): int  [API 21+]
+ captureBurst(List, CameraCaptureSession.CaptureCallback, Handler): int  [API 21+]
+ captureBurstRequests(List, Executor, CameraCaptureSession.CaptureCallback): int  [API 28+]
+ captureSingleRequest(CaptureRequest, Executor, CameraCaptureSession.CaptureCallback): int  [API 28+]
+ close(): void  [API 21+]
+ finalizeOutputConfigurations(List): void  [API 26+]
+ getDevice(): CameraDevice  [API 21+]
+ getInputSurface(): Surface  [API 23+]
+ isReprocessable(): boolean  [API 23+]
+ prepare(Surface): void  [API 23+]
+ setRepeatingBurst(List, CameraCaptureSession.CaptureCallback, Handler): int  [API 21+]
+ setRepeatingBurstRequests(List, Executor, CameraCaptureSession.CaptureCallback): int  [API 28+]
+ setRepeatingRequest(CaptureRequest, CameraCaptureSession.CaptureCallback, Handler): int  [API 21+]
+ setSingleRepeatingRequest(CaptureRequest, Executor, CameraCaptureSession.CaptureCallback): int  [API 28+]
+ stopRepeating(): void  [API 21+]
+ supportsOfflineProcessing(Surface): boolean  [API 30+]
+ switchToOffline(Collection, Executor, CameraOfflineSession.CameraOfflineSessionCallback): CameraOfflineSession  [API 30+]
+ updateOutputConfiguration(OutputConfiguration): void  [API 28+]
```

## CameraCaptureSession.CaptureCallback — declaration; API 21+ (9 +, 0 *, 0 ~, 0 -)

```
+ CaptureCallback()  [API 21+]
+ onCaptureBufferLost(CameraCaptureSession, CaptureRequest, Surface, long): void  [API 24+]
+ onCaptureCompleted(CameraCaptureSession, CaptureRequest, TotalCaptureResult): void  [API 21+]
+ onCaptureFailed(CameraCaptureSession, CaptureRequest, CaptureFailure): void  [API 21+]
+ onCaptureProgressed(CameraCaptureSession, CaptureRequest, CaptureResult): void  [API 21+]
+ onCaptureSequenceAborted(CameraCaptureSession, int): void  [API 21+]
+ onCaptureSequenceCompleted(CameraCaptureSession, int, long): void  [API 21+]
+ onCaptureStarted(CameraCaptureSession, CaptureRequest, long, long): void  [API 21+]
+ onReadoutStarted(CameraCaptureSession, CaptureRequest, long, long): void  [API 34+]
```

## CameraCaptureSession.StateCallback — declaration; API 21+ (8 +, 0 *, 0 ~, 0 -)

```
+ StateCallback()  [API 21+]
+ onActive(CameraCaptureSession): void  [API 21+]
+ onCaptureQueueEmpty(CameraCaptureSession): void  [API 26+]
+ onClosed(CameraCaptureSession): void  [API 21+]
+ onConfigureFailed(CameraCaptureSession): void  [API 21+]
+ onConfigured(CameraCaptureSession): void  [API 21+]
+ onReady(CameraCaptureSession): void  [API 21+]
+ onSurfacePrepared(CameraCaptureSession, Surface): void  [API 23+]
```

## CameraCharacteristics — declaration; API 21+ (10 +, 0 *, 0 ~, 0 -)

```
- AUTOMOTIVE_LENS_FACING [field]  [API 33+]
- AUTOMOTIVE_LOCATION [field]  [API 33+]
- COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES [field]  [API 21+]
- CONTROL_AE_AVAILABLE_ANTIBANDING_MODES [field]  [API 21+]
- CONTROL_AE_AVAILABLE_MODES [field]  [API 21+]
- CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES [field]  [API 21+]
- CONTROL_AE_COMPENSATION_RANGE [field]  [API 21+]
- CONTROL_AE_COMPENSATION_STEP [field]  [API 21+]
- CONTROL_AE_LOCK_AVAILABLE [field]  [API 23+]
- CONTROL_AF_AVAILABLE_MODES [field]  [API 21+]
- CONTROL_AUTOFRAMING_AVAILABLE [field]  [API 34+]
- CONTROL_AVAILABLE_EFFECTS [field]  [API 21+]
- CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_CAPABILITIES [field]  [API 30+]
- CONTROL_AVAILABLE_MODES [field]  [API 23+]
- CONTROL_AVAILABLE_SCENE_MODES [field]  [API 21+]
- CONTROL_AVAILABLE_SETTINGS_OVERRIDES [field]  [API 34+]
- CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES [field]  [API 21+]
- CONTROL_AWB_AVAILABLE_MODES [field]  [API 21+]
- CONTROL_AWB_LOCK_AVAILABLE [field]  [API 23+]
- CONTROL_LOW_LIGHT_BOOST_INFO_LUMINANCE_RANGE [field]  [API 35+]
- CONTROL_MAX_REGIONS_AE [field]  [API 21+]
- CONTROL_MAX_REGIONS_AF [field]  [API 21+]
- CONTROL_MAX_REGIONS_AWB [field]  [API 21+]
- CONTROL_POST_RAW_SENSITIVITY_BOOST_RANGE [field]  [API 24+]
- CONTROL_ZOOM_RATIO_RANGE [field]  [API 30+]
- DEPTH_DEPTH_IS_EXCLUSIVE [field]  [API 23+]
- DISTORTION_CORRECTION_AVAILABLE_MODES [field]  [API 28+]
- EDGE_AVAILABLE_EDGE_MODES [field]  [API 21+]
- FLASH_INFO_AVAILABLE [field]  [API 21+]
- FLASH_INFO_STRENGTH_DEFAULT_LEVEL [field]  [API 33+]
- FLASH_INFO_STRENGTH_MAXIMUM_LEVEL [field]  [API 33+]
- FLASH_SINGLE_STRENGTH_DEFAULT_LEVEL [field]  [API 35+]
- FLASH_SINGLE_STRENGTH_MAX_LEVEL [field]  [API 35+]
- FLASH_TORCH_STRENGTH_DEFAULT_LEVEL [field]  [API 35+]
- FLASH_TORCH_STRENGTH_MAX_LEVEL [field]  [API 35+]
- HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES [field]  [API 21+]
- INFO_DEVICE_STATE_SENSOR_ORIENTATION_MAP [field]  [API 32+]
- INFO_SESSION_CONFIGURATION_QUERY_VERSION [field]  [API 35+]
- INFO_SUPPORTED_HARDWARE_LEVEL [field]  [API 21+]
- INFO_VERSION [field]  [API 28+]
- JPEG_AVAILABLE_THUMBNAIL_SIZES [field]  [API 21+]
- LENS_DISTORTION [field]  [API 28+]
- LENS_DISTORTION_MAXIMUM_RESOLUTION [field]  [API 31+]
- LENS_FACING [field]  [API 21+]
- LENS_INFO_AVAILABLE_APERTURES [field]  [API 21+]
- LENS_INFO_AVAILABLE_FILTER_DENSITIES [field]  [API 21+]
- LENS_INFO_AVAILABLE_FOCAL_LENGTHS [field]  [API 21+]
- LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION [field]  [API 21+]
- LENS_INFO_FOCUS_DISTANCE_CALIBRATION [field]  [API 21+]
- LENS_INFO_HYPERFOCAL_DISTANCE [field]  [API 21+]
- LENS_INFO_MINIMUM_FOCUS_DISTANCE [field]  [API 21+]
- LENS_INTRINSIC_CALIBRATION [field]  [API 23+]
- LENS_INTRINSIC_CALIBRATION_MAXIMUM_RESOLUTION [field]  [API 31+]
- LENS_POSE_REFERENCE [field]  [API 28+]
- LENS_POSE_ROTATION [field]  [API 23+]
- LENS_POSE_TRANSLATION [field]  [API 23+]
- LENS_RADIAL_DISTORTION [field]  [API 23+, deprecated in 28]
- LOGICAL_MULTI_CAMERA_SENSOR_SYNC_TYPE [field]  [API 28+]
- NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES [field]  [API 21+]
- REPROCESS_MAX_CAPTURE_STALL [field]  [API 23+]
- REQUEST_AVAILABLE_CAPABILITIES [field]  [API 21+]
- REQUEST_AVAILABLE_COLOR_SPACE_PROFILES [field]  [API 34+]
- REQUEST_AVAILABLE_DYNAMIC_RANGE_PROFILES [field]  [API 33+]
- REQUEST_MAX_NUM_INPUT_STREAMS [field]  [API 23+]
- REQUEST_MAX_NUM_OUTPUT_PROC [field]  [API 21+]
- REQUEST_MAX_NUM_OUTPUT_PROC_STALLING [field]  [API 21+]
- REQUEST_MAX_NUM_OUTPUT_RAW [field]  [API 21+]
- REQUEST_PARTIAL_RESULT_COUNT [field]  [API 21+]
- REQUEST_PIPELINE_MAX_DEPTH [field]  [API 21+]
- REQUEST_RECOMMENDED_TEN_BIT_DYNAMIC_RANGE_PROFILE [field]  [API 33+]
- SCALER_AVAILABLE_MAX_DIGITAL_ZOOM [field]  [API 21+]
- SCALER_AVAILABLE_ROTATE_AND_CROP_MODES [field]  [API 31+]
- SCALER_AVAILABLE_STREAM_USE_CASES [field]  [API 33+]
- SCALER_CROPPING_TYPE [field]  [API 21+]
- SCALER_DEFAULT_SECURE_IMAGE_SIZE [field]  [API 31+]
- SCALER_MANDATORY_CONCURRENT_STREAM_COMBINATIONS [field]  [API 30+]
- SCALER_MANDATORY_MAXIMUM_RESOLUTION_STREAM_COMBINATIONS [field]  [API 31+]
- SCALER_MANDATORY_PREVIEW_STABILIZATION_OUTPUT_STREAM_COMBINATIONS [field]  [API 33+]
- SCALER_MANDATORY_STREAM_COMBINATIONS [field]  [API 29+]
- SCALER_MANDATORY_TEN_BIT_OUTPUT_STREAM_COMBINATIONS [field]  [API 33+]
- SCALER_MANDATORY_USE_CASE_STREAM_COMBINATIONS [field]  [API 33+]
- SCALER_MULTI_RESOLUTION_STREAM_CONFIGURATION_MAP [field]  [API 31+]
- SCALER_STREAM_CONFIGURATION_MAP [field]  [API 21+]
- SCALER_STREAM_CONFIGURATION_MAP_MAXIMUM_RESOLUTION [field]  [API 31+]
- SENSOR_AVAILABLE_TEST_PATTERN_MODES [field]  [API 21+]
- SENSOR_BLACK_LEVEL_PATTERN [field]  [API 21+]
- SENSOR_CALIBRATION_TRANSFORM1 [field]  [API 21+]
- SENSOR_CALIBRATION_TRANSFORM2 [field]  [API 21+]
- SENSOR_COLOR_TRANSFORM1 [field]  [API 21+]
- SENSOR_COLOR_TRANSFORM2 [field]  [API 21+]
- SENSOR_FORWARD_MATRIX1 [field]  [API 21+]
- SENSOR_FORWARD_MATRIX2 [field]  [API 21+]
- SENSOR_INFO_ACTIVE_ARRAY_SIZE [field]  [API 21+]
- SENSOR_INFO_ACTIVE_ARRAY_SIZE_MAXIMUM_RESOLUTION [field]  [API 31+]
- SENSOR_INFO_BINNING_FACTOR [field]  [API 31+]
- SENSOR_INFO_COLOR_FILTER_ARRANGEMENT [field]  [API 21+]
- SENSOR_INFO_EXPOSURE_TIME_RANGE [field]  [API 21+]
- SENSOR_INFO_LENS_SHADING_APPLIED [field]  [API 23+]
- SENSOR_INFO_MAX_FRAME_DURATION [field]  [API 21+]
- SENSOR_INFO_PHYSICAL_SIZE [field]  [API 21+]
- SENSOR_INFO_PIXEL_ARRAY_SIZE [field]  [API 21+]
- SENSOR_INFO_PIXEL_ARRAY_SIZE_MAXIMUM_RESOLUTION [field]  [API 31+]
- SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE [field]  [API 23+]
- SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE_MAXIMUM_RESOLUTION [field]  [API 31+]
- SENSOR_INFO_SENSITIVITY_RANGE [field]  [API 21+]
- SENSOR_INFO_TIMESTAMP_SOURCE [field]  [API 21+]
- SENSOR_INFO_WHITE_LEVEL [field]  [API 21+]
- SENSOR_MAX_ANALOG_SENSITIVITY [field]  [API 21+]
- SENSOR_OPTICAL_BLACK_REGIONS [field]  [API 24+]
- SENSOR_ORIENTATION [field]  [API 21+]
- SENSOR_READOUT_TIMESTAMP [field]  [API 34+]
- SENSOR_REFERENCE_ILLUMINANT1 [field]  [API 21+]
- SENSOR_REFERENCE_ILLUMINANT2 [field]  [API 21+]
- SHADING_AVAILABLE_MODES [field]  [API 23+]
- STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES [field]  [API 21+]
- STATISTICS_INFO_AVAILABLE_HOT_PIXEL_MAP_MODES [field]  [API 21+]
- STATISTICS_INFO_AVAILABLE_LENS_SHADING_MAP_MODES [field]  [API 23+]
- STATISTICS_INFO_AVAILABLE_OIS_DATA_MODES [field]  [API 28+]
- STATISTICS_INFO_MAX_FACE_COUNT [field]  [API 21+]
- SYNC_MAX_LATENCY [field]  [API 21+]
- TONEMAP_AVAILABLE_TONE_MAP_MODES [field]  [API 21+]
- TONEMAP_MAX_CURVE_POINTS [field]  [API 21+]
+ get(CameraCharacteristics.Key): Object  [API 21+]
+ getAvailableCaptureRequestKeys(): List  [API 21+]
+ getAvailableCaptureResultKeys(): List  [API 21+]
+ getAvailablePhysicalCameraRequestKeys(): List  [API 28+]
+ getAvailableSessionCharacteristicsKeys(): List  [API 35+]
+ getAvailableSessionKeys(): List  [API 28+]
+ getKeys(): List  [API 21+]
+ getKeysNeedingPermission(): List  [API 29+]
+ getPhysicalCameraIds(): Set  [API 28+]
+ getRecommendedStreamConfigurationMap(int): RecommendedStreamConfigurationMap  [API 29+]
```

## CameraCharacteristics.Key — declaration; API 21+ (4 +, 0 *, 0 ~, 1 -)

```
- Key(String, Class)  [API 29+]
+ equals(Object): boolean  [API 21+]
+ getName(): String  [API 21+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
```

## CameraConstrainedHighSpeedCaptureSession — declaration; API 23+ (2 +, 0 *, 0 ~, 0 -)

```
+ CameraConstrainedHighSpeedCaptureSession()  [API 23+]
+ createHighSpeedRequestList(CaptureRequest): List  [API 23+]
```

## CameraDevice — declaration; API 21+ (13 +, 2 *, 0 ~, 0 -)

```
+ close(): void  [API 21+]
+ createCaptureRequest(int): CaptureRequest.Builder  [API 21+]
+ createCaptureRequest(int, Set): CaptureRequest.Builder  [API 28+]
+ createCaptureSession(List, CameraCaptureSession.StateCallback, Handler): void  [API 21+, deprecated in 30]
+ createCaptureSession(SessionConfiguration): void  [API 28+]
+ createCaptureSessionByOutputConfigurations(List, CameraCaptureSession.StateCallback, Handler): void  [API 24+, deprecated in 30]
+ createConstrainedHighSpeedCaptureSession(List, CameraCaptureSession.StateCallback, Handler): void  [API 23+, deprecated in 30]
+ createExtensionSession(ExtensionSessionConfiguration): void  [API 31+]
+ createReprocessCaptureRequest(TotalCaptureResult): CaptureRequest.Builder  [API 23+]
+ createReprocessableCaptureSession(InputConfiguration, List, CameraCaptureSession.StateCallback, Handler): void  [API 23+, deprecated in 30]
+ createReprocessableCaptureSessionByConfigurations(InputConfiguration, List, CameraCaptureSession.StateCallback, Handler): void  [API 24+, deprecated in 30]
* getCameraAudioRestriction(): int  [API 30+]
+ getId(): String  [API 21+]
+ isSessionConfigurationSupported(SessionConfiguration): boolean  [API 29+]
* setCameraAudioRestriction(int): void  [API 30+]
```

## CameraDevice.CameraDeviceSetup — declaration; API 35+ (5 +, 0 *, 0 ~, 0 -)

```
+ createCaptureRequest(int): CaptureRequest.Builder  [API 35+]
+ getId(): String  [API 35+]
+ getSessionCharacteristics(SessionConfiguration): CameraCharacteristics  [API 35+]
+ isSessionConfigurationSupported(SessionConfiguration): boolean  [API 35+]
+ openCamera(Executor, CameraDevice.StateCallback): void  [API 35+]
```

## CameraDevice.StateCallback — declaration; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ StateCallback()  [API 21+]
+ onClosed(CameraDevice): void  [API 21+]
+ onDisconnected(CameraDevice): void  [API 21+]
+ onError(CameraDevice, int): void  [API 21+]
+ onOpened(CameraDevice): void  [API 21+]
```

## CameraExtensionCharacteristics — declaration; API 31+ (11 +, 0 *, 0 ~, 0 -)

```
+ get(int, CameraCharacteristics.Key): Object  [API 35+]
+ getAvailableCaptureRequestKeys(int): Set  [API 33+]
+ getAvailableCaptureResultKeys(int): Set  [API 33+]
+ getEstimatedCaptureLatencyRangeMillis(int, Size, int): Range  [API 31+]
+ getExtensionSupportedSizes(int, Class): List  [API 31+]
+ getExtensionSupportedSizes(int, int): List  [API 31+]
+ getKeys(int): Set  [API 35+]
+ getPostviewSupportedSizes(int, Size, int): List  [API 34+]
+ getSupportedExtensions(): List  [API 31+]
+ isCaptureProcessProgressAvailable(int): boolean  [API 34+]
+ isPostviewAvailable(int): boolean  [API 34+]
```

## CameraExtensionSession — declaration; API 31+ (6 +, 0 *, 0 ~, 0 -)

```
+ capture(CaptureRequest, Executor, CameraExtensionSession.ExtensionCaptureCallback): int  [API 31+]
+ close(): void  [API 31+]
+ getDevice(): CameraDevice  [API 31+]
+ getRealtimeStillCaptureLatency(): CameraExtensionSession.StillCaptureLatency  [API 34+]
+ setRepeatingRequest(CaptureRequest, Executor, CameraExtensionSession.ExtensionCaptureCallback): int  [API 31+]
+ stopRepeating(): void  [API 31+]
```

## CameraExtensionSession.ExtensionCaptureCallback — declaration; API 31+ (9 +, 0 *, 0 ~, 0 -)

```
+ ExtensionCaptureCallback()  [API 31+]
+ onCaptureFailed(CameraExtensionSession, CaptureRequest): void  [API 31+]
+ onCaptureFailed(CameraExtensionSession, CaptureRequest, int): void  [API 35+]
+ onCaptureProcessProgressed(CameraExtensionSession, CaptureRequest, int): void  [API 34+]
+ onCaptureProcessStarted(CameraExtensionSession, CaptureRequest): void  [API 31+]
+ onCaptureResultAvailable(CameraExtensionSession, CaptureRequest, TotalCaptureResult): void  [API 33+]
+ onCaptureSequenceAborted(CameraExtensionSession, int): void  [API 31+]
+ onCaptureSequenceCompleted(CameraExtensionSession, int): void  [API 31+]
+ onCaptureStarted(CameraExtensionSession, CaptureRequest, long): void  [API 31+]
```

## CameraExtensionSession.StateCallback — declaration; API 31+ (4 +, 0 *, 0 ~, 0 -)

```
+ StateCallback()  [API 31+]
+ onClosed(CameraExtensionSession): void  [API 31+]
+ onConfigureFailed(CameraExtensionSession): void  [API 31+]
+ onConfigured(CameraExtensionSession): void  [API 31+]
```

## CameraExtensionSession.StillCaptureLatency — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* StillCaptureLatency(long, long)  [API 34+]
* equals(Object): boolean  [API 34+]
* getCaptureLatency(): long  [API 34+]
* getProcessingLatency(): long  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
```

## CameraManager — framework; API 21+ (18 +, 0 *, 0 ~, 0 -)

```
+ getCameraCharacteristics(String): CameraCharacteristics  [API 21+]
+ getCameraDeviceSetup(String): CameraDevice.CameraDeviceSetup  [API 35+]
+ getCameraExtensionCharacteristics(String): CameraExtensionCharacteristics  [API 31+]
+ getCameraIdList(): String[]  [API 21+]
+ getConcurrentCameraIds(): Set  [API 30+]
+ getTorchStrengthLevel(String): int  [API 33+]
+ isCameraDeviceSetupSupported(String): boolean  [API 35+]
+ isConcurrentSessionConfigurationSupported(Map): boolean  [API 30+]
+ openCamera(String, CameraDevice.StateCallback, Handler): void  [API 21+]
+ openCamera(String, Executor, CameraDevice.StateCallback): void  [API 28+]
+ registerAvailabilityCallback(CameraManager.AvailabilityCallback, Handler): void  [API 21+]
+ registerAvailabilityCallback(Executor, CameraManager.AvailabilityCallback): void  [API 28+]
+ registerTorchCallback(CameraManager.TorchCallback, Handler): void  [API 23+]
+ registerTorchCallback(Executor, CameraManager.TorchCallback): void  [API 28+]
+ setTorchMode(String, boolean): void  [API 23+]
+ turnOnTorchWithStrengthLevel(String, int): void  [API 33+]
+ unregisterAvailabilityCallback(CameraManager.AvailabilityCallback): void  [API 21+]
+ unregisterTorchCallback(CameraManager.TorchCallback): void  [API 23+]
```

## CameraManager.AvailabilityCallback — framework; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
+ AvailabilityCallback()  [API 21+]
+ onCameraAccessPrioritiesChanged(): void  [API 29+]
+ onCameraAvailable(String): void  [API 21+]
+ onCameraUnavailable(String): void  [API 21+]
+ onPhysicalCameraAvailable(String, String): void  [API 30+]
+ onPhysicalCameraUnavailable(String, String): void  [API 30+]
```

## CameraManager.TorchCallback — framework; API 23+ (4 +, 0 *, 0 ~, 0 -)

```
+ TorchCallback()  [API 23+]
+ onTorchModeChanged(String, boolean): void  [API 23+]
+ onTorchModeUnavailable(String): void  [API 23+]
+ onTorchStrengthLevelChanged(String, int): void  [API 33+]
```

## CameraMetadata — declaration; API 21+ (1 +, 0 *, 0 ~, 0 -)

```
+ getKeys(): List  [API 21+]
```

## CameraOfflineSession — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ CameraOfflineSession()  [API 30+]
+ close(): void  [API 30+]
```

## CameraOfflineSession.CameraOfflineSessionCallback — declaration; API 30+ (6 +, 0 *, 0 ~, 0 -)

```
+ CameraOfflineSessionCallback()  [API 30+]
+ onClosed(CameraOfflineSession): void  [API 30+]
+ onError(CameraOfflineSession, int): void  [API 30+]
+ onIdle(CameraOfflineSession): void  [API 30+]
+ onReady(CameraOfflineSession): void  [API 30+]
+ onSwitchFailed(CameraOfflineSession): void  [API 30+]
```

## CaptureFailure — declaration; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
+ getFrameNumber(): long  [API 21+]
+ getPhysicalCameraId(): String  [API 29+]
+ getReason(): int  [API 21+]
+ getRequest(): CaptureRequest  [API 21+]
+ getSequenceId(): int  [API 21+]
+ wasImageCaptured(): boolean  [API 21+]
```

## CaptureRequest — declaration; API 21+ (9 +, 0 *, 0 ~, 0 -)

```
- BLACK_LEVEL_LOCK [field]  [API 21+]
- COLOR_CORRECTION_ABERRATION_MODE [field]  [API 21+]
- COLOR_CORRECTION_GAINS [field]  [API 21+]
- COLOR_CORRECTION_MODE [field]  [API 21+]
- COLOR_CORRECTION_TRANSFORM [field]  [API 21+]
- CONTROL_AE_ANTIBANDING_MODE [field]  [API 21+]
- CONTROL_AE_EXPOSURE_COMPENSATION [field]  [API 21+]
- CONTROL_AE_LOCK [field]  [API 21+]
- CONTROL_AE_MODE [field]  [API 21+]
- CONTROL_AE_PRECAPTURE_TRIGGER [field]  [API 21+]
- CONTROL_AE_REGIONS [field]  [API 21+]
- CONTROL_AE_TARGET_FPS_RANGE [field]  [API 21+]
- CONTROL_AF_MODE [field]  [API 21+]
- CONTROL_AF_REGIONS [field]  [API 21+]
- CONTROL_AF_TRIGGER [field]  [API 21+]
- CONTROL_AUTOFRAMING [field]  [API 34+]
- CONTROL_AWB_LOCK [field]  [API 21+]
- CONTROL_AWB_MODE [field]  [API 21+]
- CONTROL_AWB_REGIONS [field]  [API 21+]
- CONTROL_CAPTURE_INTENT [field]  [API 21+]
- CONTROL_EFFECT_MODE [field]  [API 21+]
- CONTROL_ENABLE_ZSL [field]  [API 26+]
- CONTROL_EXTENDED_SCENE_MODE [field]  [API 30+]
- CONTROL_MODE [field]  [API 21+]
- CONTROL_POST_RAW_SENSITIVITY_BOOST [field]  [API 24+]
- CONTROL_SCENE_MODE [field]  [API 21+]
- CONTROL_SETTINGS_OVERRIDE [field]  [API 34+]
- CONTROL_VIDEO_STABILIZATION_MODE [field]  [API 21+]
- CONTROL_ZOOM_RATIO [field]  [API 30+]
- CREATOR [field]  [API 21+]
- DISTORTION_CORRECTION_MODE [field]  [API 28+]
- EDGE_MODE [field]  [API 21+]
- EXTENSION_STRENGTH [field]  [API 34+]
- FLASH_MODE [field]  [API 21+]
- FLASH_STRENGTH_LEVEL [field]  [API 35+]
- HOT_PIXEL_MODE [field]  [API 21+]
- JPEG_GPS_LOCATION [field]  [API 21+]
- JPEG_ORIENTATION [field]  [API 21+]
- JPEG_QUALITY [field]  [API 21+]
- JPEG_THUMBNAIL_QUALITY [field]  [API 21+]
- JPEG_THUMBNAIL_SIZE [field]  [API 21+]
- LENS_APERTURE [field]  [API 21+]
- LENS_FILTER_DENSITY [field]  [API 21+]
- LENS_FOCAL_LENGTH [field]  [API 21+]
- LENS_FOCUS_DISTANCE [field]  [API 21+]
- LENS_OPTICAL_STABILIZATION_MODE [field]  [API 21+]
- NOISE_REDUCTION_MODE [field]  [API 21+]
- REPROCESS_EFFECTIVE_EXPOSURE_FACTOR [field]  [API 23+]
- SCALER_CROP_REGION [field]  [API 21+]
- SCALER_ROTATE_AND_CROP [field]  [API 31+]
- SENSOR_EXPOSURE_TIME [field]  [API 21+]
- SENSOR_FRAME_DURATION [field]  [API 21+]
- SENSOR_PIXEL_MODE [field]  [API 31+]
- SENSOR_SENSITIVITY [field]  [API 21+]
- SENSOR_TEST_PATTERN_DATA [field]  [API 21+]
- SENSOR_TEST_PATTERN_MODE [field]  [API 21+]
- SHADING_MODE [field]  [API 21+]
- STATISTICS_FACE_DETECT_MODE [field]  [API 21+]
- STATISTICS_HOT_PIXEL_MAP_MODE [field]  [API 21+]
- STATISTICS_LENS_SHADING_MAP_MODE [field]  [API 21+]
- STATISTICS_OIS_DATA_MODE [field]  [API 28+]
- TONEMAP_CURVE [field]  [API 21+]
- TONEMAP_GAMMA [field]  [API 23+]
- TONEMAP_MODE [field]  [API 21+]
- TONEMAP_PRESET_CURVE [field]  [API 23+]
+ describeContents(): int  [API 21+]
+ equals(Object): boolean  [API 21+]
+ finalize(): void  [API 21+]
+ get(CaptureRequest.Key): Object  [API 21+]
+ getKeys(): List  [API 21+]
+ getTag(): Object  [API 21+]
+ hashCode(): int  [API 21+]
+ isReprocess(): boolean  [API 23+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## CaptureRequest.Builder — declaration; API 21+ (8 +, 0 *, 0 ~, 0 -)

```
+ addTarget(Surface): void  [API 21+]
+ build(): CaptureRequest  [API 21+]
+ get(CaptureRequest.Key): Object  [API 21+]
+ getPhysicalCameraKey(CaptureRequest.Key, String): Object  [API 28+]
+ removeTarget(Surface): void  [API 21+]
+ set(CaptureRequest.Key, Object): void  [API 21+]
+ setPhysicalCameraKey(CaptureRequest.Key, Object, String): CaptureRequest.Builder  [API 28+]
+ setTag(Object): void  [API 21+]
```

## CaptureRequest.Key — declaration; API 21+ (4 +, 0 *, 0 ~, 1 -)

```
- Key(String, Class)  [API 29+]
+ equals(Object): boolean  [API 21+]
+ getName(): String  [API 21+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
```

## CaptureResult — declaration; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
- BLACK_LEVEL_LOCK [field]  [API 21+]
- COLOR_CORRECTION_ABERRATION_MODE [field]  [API 21+]
- COLOR_CORRECTION_GAINS [field]  [API 21+]
- COLOR_CORRECTION_MODE [field]  [API 21+]
- COLOR_CORRECTION_TRANSFORM [field]  [API 21+]
- CONTROL_AE_ANTIBANDING_MODE [field]  [API 21+]
- CONTROL_AE_EXPOSURE_COMPENSATION [field]  [API 21+]
- CONTROL_AE_LOCK [field]  [API 21+]
- CONTROL_AE_MODE [field]  [API 21+]
- CONTROL_AE_PRECAPTURE_TRIGGER [field]  [API 21+]
- CONTROL_AE_REGIONS [field]  [API 21+]
- CONTROL_AE_STATE [field]  [API 21+]
- CONTROL_AE_TARGET_FPS_RANGE [field]  [API 21+]
- CONTROL_AF_MODE [field]  [API 21+]
- CONTROL_AF_REGIONS [field]  [API 21+]
- CONTROL_AF_SCENE_CHANGE [field]  [API 28+]
- CONTROL_AF_STATE [field]  [API 21+]
- CONTROL_AF_TRIGGER [field]  [API 21+]
- CONTROL_AUTOFRAMING [field]  [API 34+]
- CONTROL_AUTOFRAMING_STATE [field]  [API 34+]
- CONTROL_AWB_LOCK [field]  [API 21+]
- CONTROL_AWB_MODE [field]  [API 21+]
- CONTROL_AWB_REGIONS [field]  [API 21+]
- CONTROL_AWB_STATE [field]  [API 21+]
- CONTROL_CAPTURE_INTENT [field]  [API 21+]
- CONTROL_EFFECT_MODE [field]  [API 21+]
- CONTROL_ENABLE_ZSL [field]  [API 26+]
- CONTROL_EXTENDED_SCENE_MODE [field]  [API 30+]
- CONTROL_LOW_LIGHT_BOOST_STATE [field]  [API 35+]
- CONTROL_MODE [field]  [API 21+]
- CONTROL_POST_RAW_SENSITIVITY_BOOST [field]  [API 24+]
- CONTROL_SCENE_MODE [field]  [API 21+]
- CONTROL_SETTINGS_OVERRIDE [field]  [API 34+]
- CONTROL_VIDEO_STABILIZATION_MODE [field]  [API 21+]
- CONTROL_ZOOM_RATIO [field]  [API 30+]
- DISTORTION_CORRECTION_MODE [field]  [API 28+]
- EDGE_MODE [field]  [API 21+]
- EXTENSION_CURRENT_TYPE [field]  [API 34+]
- EXTENSION_STRENGTH [field]  [API 34+]
- FLASH_MODE [field]  [API 21+]
- FLASH_STATE [field]  [API 21+]
- FLASH_STRENGTH_LEVEL [field]  [API 35+]
- HOT_PIXEL_MODE [field]  [API 21+]
- JPEG_GPS_LOCATION [field]  [API 21+]
- JPEG_ORIENTATION [field]  [API 21+]
- JPEG_QUALITY [field]  [API 21+]
- JPEG_THUMBNAIL_QUALITY [field]  [API 21+]
- JPEG_THUMBNAIL_SIZE [field]  [API 21+]
- LENS_APERTURE [field]  [API 21+]
- LENS_DISTORTION [field]  [API 28+]
- LENS_FILTER_DENSITY [field]  [API 21+]
- LENS_FOCAL_LENGTH [field]  [API 21+]
- LENS_FOCUS_DISTANCE [field]  [API 21+]
- LENS_FOCUS_RANGE [field]  [API 21+]
- LENS_INTRINSIC_CALIBRATION [field]  [API 23+]
- LENS_OPTICAL_STABILIZATION_MODE [field]  [API 21+]
- LENS_POSE_ROTATION [field]  [API 23+]
- LENS_POSE_TRANSLATION [field]  [API 23+]
- LENS_RADIAL_DISTORTION [field]  [API 23+, deprecated in 28]
- LENS_STATE [field]  [API 21+]
- LOGICAL_MULTI_CAMERA_ACTIVE_PHYSICAL_ID [field]  [API 29+]
- LOGICAL_MULTI_CAMERA_ACTIVE_PHYSICAL_SENSOR_CROP_REGION [field]  [API 35+]
- NOISE_REDUCTION_MODE [field]  [API 21+]
- REPROCESS_EFFECTIVE_EXPOSURE_FACTOR [field]  [API 23+]
- REQUEST_PIPELINE_DEPTH [field]  [API 21+]
- SCALER_CROP_REGION [field]  [API 21+]
- SCALER_RAW_CROP_REGION [field]  [API 34+]
- SCALER_ROTATE_AND_CROP [field]  [API 31+]
- SENSOR_DYNAMIC_BLACK_LEVEL [field]  [API 24+]
- SENSOR_DYNAMIC_WHITE_LEVEL [field]  [API 24+]
- SENSOR_EXPOSURE_TIME [field]  [API 21+]
- SENSOR_FRAME_DURATION [field]  [API 21+]
- SENSOR_GREEN_SPLIT [field]  [API 21+]
- SENSOR_NEUTRAL_COLOR_POINT [field]  [API 21+]
- SENSOR_NOISE_PROFILE [field]  [API 21+]
- SENSOR_PIXEL_MODE [field]  [API 31+]
- SENSOR_RAW_BINNING_FACTOR_USED [field]  [API 31+]
- SENSOR_ROLLING_SHUTTER_SKEW [field]  [API 21+]
- SENSOR_SENSITIVITY [field]  [API 21+]
- SENSOR_TEST_PATTERN_DATA [field]  [API 21+]
- SENSOR_TEST_PATTERN_MODE [field]  [API 21+]
- SENSOR_TIMESTAMP [field]  [API 21+]
- SHADING_MODE [field]  [API 21+]
- STATISTICS_FACES [field]  [API 21+]
- STATISTICS_FACE_DETECT_MODE [field]  [API 21+]
- STATISTICS_HOT_PIXEL_MAP [field]  [API 21+]
- STATISTICS_HOT_PIXEL_MAP_MODE [field]  [API 21+]
- STATISTICS_LENS_INTRINSICS_SAMPLES [field]  [API 35+]
- STATISTICS_LENS_SHADING_CORRECTION_MAP [field]  [API 21+]
- STATISTICS_LENS_SHADING_MAP_MODE [field]  [API 21+]
- STATISTICS_OIS_DATA_MODE [field]  [API 28+]
- STATISTICS_OIS_SAMPLES [field]  [API 28+]
- STATISTICS_SCENE_FLICKER [field]  [API 21+]
- TONEMAP_CURVE [field]  [API 21+]
- TONEMAP_GAMMA [field]  [API 23+]
- TONEMAP_MODE [field]  [API 21+]
- TONEMAP_PRESET_CURVE [field]  [API 23+]
+ get(CaptureResult.Key): Object  [API 21+]
+ getCameraId(): String  [API 31+]
+ getFrameNumber(): long  [API 21+]
+ getKeys(): List  [API 21+]
+ getRequest(): CaptureRequest  [API 21+]
+ getSequenceId(): int  [API 21+]
```

## CaptureResult.Key — declaration; API 21+ (4 +, 0 *, 0 ~, 1 -)

```
- Key(String, Class)  [API 29+]
+ equals(Object): boolean  [API 21+]
+ getName(): String  [API 21+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
```

## DngCreator — declaration; API 21+ (10 +, 0 *, 0 ~, 1 -)

```
- DngCreator(CameraCharacteristics, CaptureResult)  [API 21+]
+ close(): void  [API 21+]
+ finalize(): void  [API 21+]
+ setDescription(String): DngCreator  [API 21+]
+ setLocation(Location): DngCreator  [API 21+]
+ setOrientation(int): DngCreator  [API 21+]
+ setThumbnail(Bitmap): DngCreator  [API 21+]
+ setThumbnail(Image): DngCreator  [API 21+]
+ writeByteBuffer(OutputStream, Size, ByteBuffer, long): void  [API 21+]
+ writeImage(OutputStream, Image): void  [API 21+]
+ writeInputStream(OutputStream, Size, InputStream, long): void  [API 21+]
```

## MultiResolutionImageReader — declaration; API 31+ (6 +, 0 *, 0 ~, 1 -)

```
- MultiResolutionImageReader(Collection, int, int)  [API 31+]
+ close(): void  [API 31+]
+ finalize(): void  [API 31+]
+ flush(): void  [API 31+]
+ getStreamInfoForImageReader(ImageReader): MultiResolutionStreamInfo  [API 31+]
+ getSurface(): Surface  [API 31+]
+ setOnImageAvailableListener(ImageReader.OnImageAvailableListener, Executor): void  [API 31+]
```

## TotalCaptureResult — declaration; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ getPartialResults(): List  [API 21+]
+ getPhysicalCameraResults(): Map  [API 28+, deprecated in 31]
+ getPhysicalCameraTotalResults(): Map  [API 31+]
```


// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.hardware.camera2;

public final class CameraManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public CameraManager(a2w.Hidden hidden) { }
  @a2w.Absent public android.hardware.camera2.CameraCharacteristics getCameraCharacteristics(java.lang.String cameraId) throws android.hardware.camera2.CameraAccessException { throw new java.lang.IllegalArgumentException("No camera " + cameraId); }
  @a2w.Absent public android.hardware.camera2.CameraDevice.CameraDeviceSetup getCameraDeviceSetup(java.lang.String cameraId) throws android.hardware.camera2.CameraAccessException { return null; }
  @a2w.Absent public android.hardware.camera2.CameraExtensionCharacteristics getCameraExtensionCharacteristics(java.lang.String cameraId) throws android.hardware.camera2.CameraAccessException { return null; }
  @a2w.Absent public java.lang.String[] getCameraIdList() throws android.hardware.camera2.CameraAccessException { return new java.lang.String[0]; }
  @a2w.Absent public java.util.Set<java.util.Set<java.lang.String>> getConcurrentCameraIds() throws android.hardware.camera2.CameraAccessException { return java.util.Collections.emptySet(); }
  @a2w.Absent public int getTorchStrengthLevel(java.lang.String cameraId) throws android.hardware.camera2.CameraAccessException { return 0; }
  @a2w.Absent public boolean isCameraDeviceSetupSupported(java.lang.String cameraId) throws android.hardware.camera2.CameraAccessException { return false; }
  @a2w.Absent public boolean isConcurrentSessionConfigurationSupported(java.util.Map<java.lang.String, android.hardware.camera2.params.SessionConfiguration> cameraIdAndSessionConfig) throws android.hardware.camera2.CameraAccessException { return false; }
  @a2w.Absent public void openCamera(java.lang.String cameraId, android.hardware.camera2.CameraDevice.StateCallback callback, android.os.Handler handler) throws android.hardware.camera2.CameraAccessException { throw new java.lang.IllegalArgumentException("No camera " + cameraId); }
  @a2w.Absent public void openCamera(java.lang.String cameraId, java.util.concurrent.Executor executor, android.hardware.camera2.CameraDevice.StateCallback callback) throws android.hardware.camera2.CameraAccessException { throw new java.lang.IllegalArgumentException("No camera " + cameraId); }
  @a2w.Absent public void registerAvailabilityCallback(android.hardware.camera2.CameraManager.AvailabilityCallback callback, android.os.Handler handler) {  }
  @a2w.Absent public void registerAvailabilityCallback(java.util.concurrent.Executor executor, android.hardware.camera2.CameraManager.AvailabilityCallback callback) {  }
  @a2w.Absent public void registerTorchCallback(android.hardware.camera2.CameraManager.TorchCallback callback, android.os.Handler handler) {  }
  @a2w.Absent public void registerTorchCallback(java.util.concurrent.Executor executor, android.hardware.camera2.CameraManager.TorchCallback callback) {  }
  @a2w.Absent public void setTorchMode(java.lang.String cameraId, boolean enabled) throws android.hardware.camera2.CameraAccessException {  }
  @a2w.Absent public void turnOnTorchWithStrengthLevel(java.lang.String cameraId, int torchStrength) throws android.hardware.camera2.CameraAccessException {  }
  @a2w.Absent public void unregisterAvailabilityCallback(android.hardware.camera2.CameraManager.AvailabilityCallback callback) {  }
  @a2w.Absent public void unregisterTorchCallback(android.hardware.camera2.CameraManager.TorchCallback callback) {  }

    public static abstract class AvailabilityCallback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public AvailabilityCallback(a2w.Hidden hidden) { }
    public AvailabilityCallback() { this((a2w.Hidden) null); }
    public void onCameraAccessPrioritiesChanged() { }
    public void onCameraAvailable(java.lang.String cameraId) { }
    public void onCameraUnavailable(java.lang.String cameraId) { }
    public void onPhysicalCameraAvailable(java.lang.String cameraId, java.lang.String physicalCameraId) { }
    public void onPhysicalCameraUnavailable(java.lang.String cameraId, java.lang.String physicalCameraId) { }
  }

    public static abstract class TorchCallback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public TorchCallback(a2w.Hidden hidden) { }
    public TorchCallback() { this((a2w.Hidden) null); }
    public void onTorchModeChanged(java.lang.String cameraId, boolean enabled) { }
    public void onTorchModeUnavailable(java.lang.String cameraId) { }
    public void onTorchStrengthLevelChanged(java.lang.String cameraId, int newStrengthLevel) { }
  }
}

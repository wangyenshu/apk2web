// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.hardware.usb;

public class UsbManager {
  public static final java.lang.String ACTION_USB_ACCESSORY_ATTACHED = "android.hardware.usb.action.USB_ACCESSORY_ATTACHED";
  public static final java.lang.String ACTION_USB_ACCESSORY_DETACHED = "android.hardware.usb.action.USB_ACCESSORY_DETACHED";
  public static final java.lang.String ACTION_USB_DEVICE_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
  public static final java.lang.String ACTION_USB_DEVICE_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
  public static final java.lang.String EXTRA_ACCESSORY = "accessory";
  public static final java.lang.String EXTRA_DEVICE = "device";
  public static final java.lang.String EXTRA_PERMISSION_GRANTED = "permission";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public UsbManager(a2w.Hidden hidden) { }
  @a2w.Absent public android.hardware.usb.UsbAccessory[] getAccessoryList() { return new android.hardware.usb.UsbAccessory[0]; }
  @a2w.Absent public java.util.HashMap<java.lang.String, android.hardware.usb.UsbDevice> getDeviceList() { return null; }
  @a2w.Absent public boolean hasPermission(android.hardware.usb.UsbAccessory accessory) { return false; }
  @a2w.Absent public boolean hasPermission(android.hardware.usb.UsbDevice device) { return false; }
  @a2w.Absent public android.os.ParcelFileDescriptor openAccessory(android.hardware.usb.UsbAccessory accessory) { return null; }
  @a2w.Absent public android.hardware.usb.UsbDeviceConnection openDevice(android.hardware.usb.UsbDevice device) { return null; }
  @a2w.Absent public void requestPermission(android.hardware.usb.UsbAccessory accessory, android.app.PendingIntent pi) {  }
  @a2w.Absent public void requestPermission(android.hardware.usb.UsbDevice device, android.app.PendingIntent pi) {  }
}

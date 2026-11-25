// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os;

public abstract class VibratorManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public VibratorManager(a2w.Hidden hidden) { }
  public abstract void cancel();
  public abstract android.os.Vibrator getDefaultVibrator();
  public abstract android.os.Vibrator getVibrator(int p0);
  public abstract int[] getVibratorIds();
  @a2w.NotImplemented public final void vibrate(android.os.CombinedVibration effect) { a2w.Stubs.hit("android.os.VibratorManager.vibrate"); }
  @a2w.NotImplemented public final void vibrate(android.os.CombinedVibration effect, android.os.VibrationAttributes attributes) { a2w.Stubs.hit("android.os.VibratorManager.vibrate"); }
}

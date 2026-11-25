package a2w;

/** The vibrators of a device without one: no ids; the default Vibrator has no vibrator (hasVibrator false). */
public final class VibratorManagerImpl extends android.os.VibratorManager {
  private android.os.Vibrator mVibrator;
  public VibratorManagerImpl() { super((Hidden) null); }
  private android.os.Vibrator vib() { if (mVibrator == null) mVibrator = new android.os.Vibrator(); return mVibrator; }
  @Override public int[] getVibratorIds() { return new int[0]; }
  @Override public android.os.Vibrator getVibrator(int vibratorId) { return vib(); }
  @Override public android.os.Vibrator getDefaultVibrator() { return vib(); }
  @Override public void cancel() { vib().cancel(); }
}

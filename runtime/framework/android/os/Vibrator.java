package android.os;
public class Vibrator {
  public void vibrate(long ms) {}
  public void vibrate(long[] pattern, int repeat) {}
  public void cancel() {}
  public boolean hasVibrator() { return false; }
}

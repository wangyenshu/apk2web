package android.app;
public class KeyguardManager {
  public class KeyguardLock { public void disableKeyguard() {} public void reenableKeyguard() {} }
  public KeyguardLock newKeyguardLock(String tag) { return new KeyguardLock(); }
  public boolean inKeyguardRestrictedInputMode() { return false; }
  public boolean isKeyguardLocked() { return false; }
  public boolean isKeyguardSecure() { return false; }
}

// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.hardware.biometrics;

public class BiometricManager {
  public static final int BIOMETRIC_ERROR_HW_UNAVAILABLE = 1;
  public static final int BIOMETRIC_ERROR_NONE_ENROLLED = 11;
  public static final int BIOMETRIC_ERROR_NO_HARDWARE = 12;
  public static final int BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED = 15;
  public static final long BIOMETRIC_NO_AUTHENTICATION = -1L;
  public static final int BIOMETRIC_SUCCESS = 0;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public BiometricManager(a2w.Hidden hidden) { }
  @a2w.Absent public int canAuthenticate() { return android.hardware.biometrics.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE; }
  @a2w.Absent public int canAuthenticate(int authenticators) { return android.hardware.biometrics.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE; }
  @a2w.Absent public long getLastAuthenticationTime(int authenticators) { return 0L; }
  @a2w.Absent public android.hardware.biometrics.BiometricManager.Strings getStrings(int authenticators) { return null; }

    public interface Authenticators {
    int BIOMETRIC_STRONG = 15;
    int BIOMETRIC_WEAK = 255;
    int DEVICE_CREDENTIAL = 32768;
  }

    public static class Strings {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public Strings(a2w.Hidden hidden) { }
    @a2w.Absent public java.lang.CharSequence getButtonLabel() { return null; }
    @a2w.Absent public java.lang.CharSequence getPromptMessage() { return null; }
    @a2w.Absent public java.lang.CharSequence getSettingName() { return null; }
  }
}

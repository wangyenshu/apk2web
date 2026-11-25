package a2w.jdk.crypto;

import a2w.jdk.NoSuchAlgorithmException;
import a2w.jdk.crypto.spec.DESKeySpec;
import a2w.jdk.crypto.spec.SecretKeySpec;
import a2w.jdk.sec.spec.InvalidKeySpecException;
import a2w.jdk.sec.spec.KeySpec;

public class SecretKeyFactory {
  final String alg;
  SecretKeyFactory(String a) { alg = a; }
  public static SecretKeyFactory getInstance(String a) throws NoSuchAlgorithmException {
    if (!"DES".equalsIgnoreCase(a)) throw new NoSuchAlgorithmException(a + " SecretKeyFactory not available");
    return new SecretKeyFactory("DES");
  }
  public final String getAlgorithm() { return alg; }
  public final SecretKey generateSecret(KeySpec s) throws InvalidKeySpecException {
    if (s instanceof DESKeySpec) return new SecretKeySpec(((DESKeySpec) s).getKey(), "DES");
    if (s instanceof SecretKeySpec) return (SecretKeySpec) s;
    throw new InvalidKeySpecException("Inappropriate key specification");
  }
}

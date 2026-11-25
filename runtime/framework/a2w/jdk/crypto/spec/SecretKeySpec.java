package a2w.jdk.crypto.spec;
public class SecretKeySpec implements a2w.jdk.sec.spec.KeySpec, a2w.jdk.crypto.SecretKey {
  final byte[] key; final String alg;
  public SecretKeySpec(byte[] k, String alg) { key = k.clone(); this.alg = alg; }
  public String getAlgorithm() { return alg; }
  public String getFormat() { return "RAW"; }
  public byte[] getEncoded() { return key.clone(); }
}

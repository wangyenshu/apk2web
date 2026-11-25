package a2w.jdk.crypto.spec;
public class IvParameterSpec implements a2w.jdk.sec.spec.AlgorithmParameterSpec {
  final byte[] iv;
  public IvParameterSpec(byte[] iv) { this(iv, 0, iv.length); }
  public IvParameterSpec(byte[] iv, int off, int len) { this.iv = new byte[len]; System.arraycopy(iv, off, this.iv, 0, len); }
  public byte[] getIV() { return iv.clone(); }
}

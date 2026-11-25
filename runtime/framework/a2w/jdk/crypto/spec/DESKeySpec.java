package a2w.jdk.crypto.spec;
public class DESKeySpec implements a2w.jdk.sec.spec.KeySpec {
  public static final int DES_KEY_LEN = 8;
  final byte[] key;
  public DESKeySpec(byte[] k) throws a2w.jdk.sec.InvalidKeyException { this(k, 0); }
  public DESKeySpec(byte[] k, int off) throws a2w.jdk.sec.InvalidKeyException {
    if (k == null || k.length - off < 8) throw new a2w.jdk.sec.InvalidKeyException("Wrong key size");
    key = new byte[8];
    System.arraycopy(k, off, key, 0, 8);
  }
  public byte[] getKey() { return key.clone(); }
}

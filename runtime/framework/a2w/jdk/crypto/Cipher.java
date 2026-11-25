package a2w.jdk.crypto;

import a2w.jdk.NoSuchAlgorithmException;
import a2w.jdk.crypto.spec.IvParameterSpec;
import a2w.jdk.sec.InvalidAlgorithmParameterException;
import a2w.jdk.sec.InvalidKeyException;
import a2w.jdk.sec.Key;
import a2w.jdk.sec.spec.AlgorithmParameterSpec;
import java.io.ByteArrayOutputStream;

/** DES in ECB or CBC mode with PKCS5 padding or none: what the game's login code uses. */
public class Cipher {
  public static final int ENCRYPT_MODE = 1, DECRYPT_MODE = 2, WRAP_MODE = 3, UNWRAP_MODE = 4;
  final String transformation;
  final boolean cbc, pad;
  int mode;
  long[] subkeys;
  byte[] iv = new byte[8], chain = new byte[8];
  final ByteArrayOutputStream buf = new ByteArrayOutputStream();

  Cipher(String t, boolean cbc, boolean pad) { transformation = t; this.cbc = cbc; this.pad = pad; }

  public static Cipher getInstance(String t) throws NoSuchAlgorithmException, NoSuchPaddingException {
    String[] p = t.split("/");
    if (!p[0].equalsIgnoreCase("DES")) throw new NoSuchAlgorithmException("Cannot find any provider supporting " + t);
    String m = p.length > 1 ? p[1].toUpperCase() : "ECB";
    String pd = p.length > 2 ? p[2].toUpperCase() : "PKCS5PADDING";
    if (!m.equals("ECB") && !m.equals("CBC")) throw new NoSuchAlgorithmException("Cannot find any provider supporting " + t);
    if (!pd.equals("PKCS5PADDING") && !pd.equals("PKCS7PADDING") && !pd.equals("NOPADDING")) throw new NoSuchPaddingException(t);
    return new Cipher(t, m.equals("CBC"), !pd.equals("NOPADDING"));
  }
  public final String getAlgorithm() { return transformation; }
  public final int getBlockSize() { return 8; }
  public final byte[] getIV() { return cbc ? iv.clone() : null; }
  public final void init(int mode, Key key) throws InvalidKeyException {
    try { init(mode, key, (AlgorithmParameterSpec) null); } catch (InvalidAlgorithmParameterException e) { throw new InvalidKeyException(e.getMessage()); }
  }
  public final void init(int mode, Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException {
    byte[] k = key == null ? null : key.getEncoded();
    if (k == null || k.length < 8) throw new InvalidKeyException("Invalid key length");
    this.mode = mode;
    subkeys = keySchedule(k);
    if (cbc) {
      if (params instanceof IvParameterSpec) iv = ((IvParameterSpec) params).getIV();
      else if (mode == DECRYPT_MODE) throw new InvalidAlgorithmParameterException("Parameters missing");
      else iv = new byte[8];
      if (iv.length != 8) throw new InvalidAlgorithmParameterException("Wrong IV length: must be 8 bytes long");
    }
    reset();
  }
  void reset() { buf.reset(); chain = iv.clone(); }
  public final byte[] update(byte[] in) { buf.write(in, 0, in.length); return new byte[0]; }
  public final byte[] update(byte[] in, int off, int len) { buf.write(in, off, len); return new byte[0]; }
  public final byte[] doFinal(byte[] in) throws IllegalBlockSizeException, BadPaddingException { if (in != null) buf.write(in, 0, in.length); return doFinal(); }
  public final byte[] doFinal(byte[] in, int off, int len) throws IllegalBlockSizeException, BadPaddingException { buf.write(in, off, len); return doFinal(); }
  public final byte[] doFinal() throws IllegalBlockSizeException, BadPaddingException {
    if (subkeys == null) throw new IllegalStateException("Cipher not initialized");
    byte[] data = buf.toByteArray();
    try {
      if (mode == ENCRYPT_MODE || mode == WRAP_MODE) {
        if (pad) {
          int n = 8 - data.length % 8;
          byte[] p = new byte[data.length + n];
          System.arraycopy(data, 0, p, 0, data.length);
          for (int i = data.length; i < p.length; i++) p[i] = (byte) n;
          data = p;
        } else if (data.length % 8 != 0) throw new IllegalBlockSizeException("Input length not multiple of 8 bytes");
        byte[] out = new byte[data.length];
        for (int o = 0; o < data.length; o += 8) {
          long b = load(data, o);
          if (cbc) b ^= load(chain, 0);
          b = crypt(b, false);
          store(out, o, b);
          if (cbc) store(chain, 0, b);
        }
        return out;
      } else {
        if (data.length % 8 != 0) throw new IllegalBlockSizeException("Input length must be multiple of 8 when decrypting with padded cipher");
        byte[] out = new byte[data.length];
        for (int o = 0; o < data.length; o += 8) {
          long c = load(data, o);
          long b = crypt(c, true);
          if (cbc) { b ^= load(chain, 0); store(chain, 0, c); }
          store(out, o, b);
        }
        if (!pad) return out;
        if (out.length == 0) throw new BadPaddingException("Given final block not properly padded");
        int n = out[out.length - 1] & 0xff;
        if (n < 1 || n > 8) throw new BadPaddingException("Given final block not properly padded");
        for (int i = out.length - n; i < out.length; i++) if ((out[i] & 0xff) != n) throw new BadPaddingException("Given final block not properly padded");
        byte[] r = new byte[out.length - n];
        System.arraycopy(out, 0, r, 0, r.length);
        return r;
      }
    } finally { reset(); }
  }

  static long load(byte[] b, int o) { long v = 0; for (int i = 0; i < 8; i++) v = (v << 8) | (b[o + i] & 0xff); return v; }
  static void store(byte[] b, int o, long v) { for (int i = 7; i >= 0; i--) { b[o + i] = (byte) v; v >>>= 8; } }

  // ---------------------------------------------------------------- DES (FIPS 46-3)
  static final int[] IP = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8,
      57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};
  static final int[] FP = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29,
      36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};
  static final int[] E = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21,
      22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};
  static final int[] P = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};
  static final int[] PC1 = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36,
      63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};
  static final int[] PC2 = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2,
      41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
  static final int[] SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
  static final int[][] S = {
      {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
       4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13},
      {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
       0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9},
      {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
       13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12},
      {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
       10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14},
      {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
       4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3},
      {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
       9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13},
      {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
       1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12},
      {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
       7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};

  /** permute: table entries are 1-based bit positions counted from the MSB of an inBits-wide value */
  static long permute(long v, int[] table, int inBits) {
    long r = 0;
    for (int t : table) r = (r << 1) | ((v >>> (inBits - t)) & 1);
    return r;
  }
  static long[] keySchedule(byte[] key) {
    long k = permute(load(key, 0), PC1, 64);
    int c = (int) (k >>> 28) & 0xfffffff, d = (int) k & 0xfffffff;
    long[] sk = new long[16];
    for (int i = 0; i < 16; i++) {
      for (int s = 0; s < SHIFTS[i]; s++) { c = ((c << 1) | (c >>> 27)) & 0xfffffff; d = ((d << 1) | (d >>> 27)) & 0xfffffff; }
      sk[i] = permute(((long) c << 28) | d, PC2, 56);
    }
    return sk;
  }
  static int f(int r, long k) {
    long x = permute(r & 0xffffffffL, E, 32) ^ k;
    int o = 0;
    for (int i = 0; i < 8; i++) {
      int six = (int) (x >>> (42 - 6 * i)) & 0x3f;
      int row = ((six & 0x20) >>> 4) | (six & 1), col = (six >>> 1) & 0xf;
      o = (o << 4) | S[i][row * 16 + col];
    }
    return (int) permute(o & 0xffffffffL, P, 32);
  }
  long crypt(long block, boolean decrypt) {
    long v = permute(block, IP, 64);
    int l = (int) (v >>> 32), r = (int) v;
    for (int i = 0; i < 16; i++) {
      int t = r;
      r = l ^ f(r, subkeys[decrypt ? 15 - i : i]);
      l = t;
    }
    return permute(((r & 0xffffffffL) << 32) | (l & 0xffffffffL), FP, 64);
  }
}

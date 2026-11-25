package a2w.jdk;

/** java.security.MessageDigest: MD5, SHA-1, SHA-224, SHA-256, SHA-384 and SHA-512. */
public abstract class MessageDigest implements Cloneable {
  private final String alg;
  protected MessageDigest(String a) { alg = a; }
  public static MessageDigest getInstance(String a) throws NoSuchAlgorithmException {
    String u = a.toUpperCase();
    if (u.equals("MD5")) return new Md5();
    if (u.equals("SHA-1") || u.equals("SHA1") || u.equals("SHA")) return new Sha1();
    if (u.equals("SHA-256") || u.equals("SHA256")) return new Sha256(false);
    if (u.equals("SHA-224") || u.equals("SHA224")) return new Sha256(true);
    if (u.equals("SHA-512") || u.equals("SHA512")) return new Sha512(false);
    if (u.equals("SHA-384") || u.equals("SHA384")) return new Sha512(true);
    throw new NoSuchAlgorithmException(a);
  }
  public static MessageDigest getInstance(String a, String provider) throws NoSuchAlgorithmException { return getInstance(a); }
  public final String getAlgorithm() { return alg; }
  public void update(byte b) { update(new byte[] { b }, 0, 1); }
  public void update(byte[] b) { update(b, 0, b.length); }
  public abstract void update(byte[] b, int off, int len);
  public abstract byte[] digest();
  public byte[] digest(byte[] b) { update(b); return digest(); }
  public int digest(byte[] buf, int off, int len) { byte[] d = digest(); System.arraycopy(d, 0, buf, off, d.length); return d.length; }
  public abstract void reset();
  public int getDigestLength() {
    try { return ((MessageDigest) clone()).digest().length; } catch (CloneNotSupportedException e) { return 0; }
  }
  @Override public Object clone() throws CloneNotSupportedException { return copy(); }
  abstract MessageDigest copy();
  @Override public String toString() { return alg + " Message Digest from apk2web"; }
  public static boolean isEqual(byte[] a, byte[] b) { return java.util.Arrays.equals(a, b); }

  abstract static class Block extends MessageDigest {
    byte[] buf = new byte[64];
    int n; long total;
    Block(String a) { super(a); }
    public void update(byte[] b, int off, int len) {
      total += len;
      while (len > 0) {
        int k = Math.min(64 - n, len);
        System.arraycopy(b, off, buf, n, k);
        n += k; off += k; len -= k;
        if (n == 64) { block(buf); n = 0; }
      }
    }
    Block copyBlock(Block to) { to.buf = buf.clone(); to.n = n; to.total = total; return to; }
    abstract void block(byte[] b);
    byte[] pad(boolean bigEndian) {
      long bits = total * 8;
      byte[] p = new byte[((n < 56) ? 56 - n : 120 - n) + 8];
      p[0] = (byte) 0x80;
      for (int i = 0; i < 8; i++) p[p.length - 8 + i] = (byte) (bigEndian ? bits >>> (56 - 8 * i) : bits >>> (8 * i));
      long t = total;
      update(p, 0, p.length);
      total = t;
      return null;
    }
  }

  static final class Md5 extends Block {
    int a, b, c, d;
    static final int[] S = {7,12,17,22,7,12,17,22,7,12,17,22,7,12,17,22,5,9,14,20,5,9,14,20,5,9,14,20,5,9,14,20,4,11,16,23,4,11,16,23,4,11,16,23,4,11,16,23,6,10,15,21,6,10,15,21,6,10,15,21,6,10,15,21};
    static final int[] K = new int[64];
    static { for (int i = 0; i < 64; i++) K[i] = (int) (long) Math.floor(Math.abs(Math.sin(i + 1)) * 4294967296.0); }
    Md5() { super("MD5"); reset(); }
    MessageDigest copy() { Md5 c = (Md5) copyBlock(new Md5()); c.a = a; c.b = b; c.c = this.c; c.d = d; return c; }
    public void reset() { a = 0x67452301; b = 0xefcdab89; c = 0x98badcfe; d = 0x10325476; n = 0; total = 0; }
    void block(byte[] x) {
      int[] m = new int[16];
      for (int i = 0; i < 16; i++) m[i] = (x[i*4] & 0xff) | (x[i*4+1] & 0xff) << 8 | (x[i*4+2] & 0xff) << 16 | (x[i*4+3] & 0xff) << 24;
      int A = a, B = b, C = c, D = d;
      for (int i = 0; i < 64; i++) {
        int f, g;
        if (i < 16) { f = (B & C) | (~B & D); g = i; }
        else if (i < 32) { f = (D & B) | (~D & C); g = (5 * i + 1) & 15; }
        else if (i < 48) { f = B ^ C ^ D; g = (3 * i + 5) & 15; }
        else { f = C ^ (B | ~D); g = (7 * i) & 15; }
        int t = D; D = C; C = B;
        B = B + Integer.rotateLeft(A + f + K[i] + m[g], S[i]);
        A = t;
      }
      a += A; b += B; c += C; d += D;
    }
    public byte[] digest() {
      pad(false);
      byte[] r = new byte[16];
      int[] v = {a, b, c, d};
      for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) r[i * 4 + j] = (byte) (v[i] >>> (8 * j));
      reset();
      return r;
    }
  }

  static final class Sha1 extends Block {
    int h0, h1, h2, h3, h4;
    Sha1() { super("SHA-1"); reset(); }
    MessageDigest copy() { Sha1 c = (Sha1) copyBlock(new Sha1()); c.h0 = h0; c.h1 = h1; c.h2 = h2; c.h3 = h3; c.h4 = h4; return c; }
    public void reset() { h0 = 0x67452301; h1 = 0xEFCDAB89; h2 = 0x98BADCFE; h3 = 0x10325476; h4 = 0xC3D2E1F0; n = 0; total = 0; }
    void block(byte[] x) {
      int[] w = new int[80];
      for (int i = 0; i < 16; i++) w[i] = (x[i*4] & 0xff) << 24 | (x[i*4+1] & 0xff) << 16 | (x[i*4+2] & 0xff) << 8 | (x[i*4+3] & 0xff);
      for (int i = 16; i < 80; i++) w[i] = Integer.rotateLeft(w[i-3] ^ w[i-8] ^ w[i-14] ^ w[i-16], 1);
      int a = h0, b = h1, c = h2, d = h3, e = h4;
      for (int i = 0; i < 80; i++) {
        int f, k;
        if (i < 20) { f = (b & c) | (~b & d); k = 0x5A827999; }
        else if (i < 40) { f = b ^ c ^ d; k = 0x6ED9EBA1; }
        else if (i < 60) { f = (b & c) | (b & d) | (c & d); k = 0x8F1BBCDC; }
        else { f = b ^ c ^ d; k = 0xCA62C1D6; }
        int t = Integer.rotateLeft(a, 5) + f + e + k + w[i];
        e = d; d = c; c = Integer.rotateLeft(b, 30); b = a; a = t;
      }
      h0 += a; h1 += b; h2 += c; h3 += d; h4 += e;
    }
    public byte[] digest() {
      pad(true);
      byte[] r = new byte[20];
      int[] v = {h0, h1, h2, h3, h4};
      for (int i = 0; i < 5; i++) for (int j = 0; j < 4; j++) r[i * 4 + j] = (byte) (v[i] >>> (24 - 8 * j));
      reset();
      return r;
    }
  }

  static final class Sha256 extends Block {
    static final int[] K = {
      0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5, 0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3,
      0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174, 0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
      0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967, 0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13,
      0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85, 0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
      0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3, 0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208,
      0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2};
    final boolean is224;
    int[] h = new int[8];
    Sha256(boolean is224) { super(is224 ? "SHA-224" : "SHA-256"); this.is224 = is224; reset(); }
    MessageDigest copy() { Sha256 c = (Sha256) copyBlock(new Sha256(is224)); c.h = h.clone(); return c; }
    public void reset() {
      h = is224 ? new int[] {0xc1059ed8, 0x367cd507, 0x3070dd17, 0xf70e5939, 0xffc00b31, 0x68581511, 0x64f98fa7, 0xbefa4fa4}
                : new int[] {0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19};
      n = 0; total = 0;
    }
    void block(byte[] x) {
      int[] w = new int[64];
      for (int i = 0; i < 16; i++) w[i] = (x[i*4] & 0xff) << 24 | (x[i*4+1] & 0xff) << 16 | (x[i*4+2] & 0xff) << 8 | (x[i*4+3] & 0xff);
      for (int i = 16; i < 64; i++) {
        int s0 = Integer.rotateRight(w[i-15], 7) ^ Integer.rotateRight(w[i-15], 18) ^ (w[i-15] >>> 3);
        int s1 = Integer.rotateRight(w[i-2], 17) ^ Integer.rotateRight(w[i-2], 19) ^ (w[i-2] >>> 10);
        w[i] = w[i-16] + s0 + w[i-7] + s1;
      }
      int a = h[0], b = h[1], c = h[2], d = h[3], e = h[4], f = h[5], g = h[6], hh = h[7];
      for (int i = 0; i < 64; i++) {
        int S1 = Integer.rotateRight(e, 6) ^ Integer.rotateRight(e, 11) ^ Integer.rotateRight(e, 25);
        int ch = (e & f) ^ (~e & g);
        int t1 = hh + S1 + ch + K[i] + w[i];
        int S0 = Integer.rotateRight(a, 2) ^ Integer.rotateRight(a, 13) ^ Integer.rotateRight(a, 22);
        int maj = (a & b) ^ (a & c) ^ (b & c);
        int t2 = S0 + maj;
        hh = g; g = f; f = e; e = d + t1; d = c; c = b; b = a; a = t1 + t2;
      }
      h[0] += a; h[1] += b; h[2] += c; h[3] += d; h[4] += e; h[5] += f; h[6] += g; h[7] += hh;
    }
    public byte[] digest() {
      pad(true);
      int words = is224 ? 7 : 8;
      byte[] r = new byte[words * 4];
      for (int i = 0; i < words; i++) for (int j = 0; j < 4; j++) r[i * 4 + j] = (byte) (h[i] >>> (24 - 8 * j));
      reset();
      return r;
    }
  }

  static final class Sha512 extends MessageDigest {
    static final long[] K = {
      0x428a2f98d728ae22L, 0x7137449123ef65cdL, 0xb5c0fbcfec4d3b2fL, 0xe9b5dba58189dbbcL, 0x3956c25bf348b538L, 0x59f111f1b605d019L, 0x923f82a4af194f9bL, 0xab1c5ed5da6d8118L,
      0xd807aa98a3030242L, 0x12835b0145706fbeL, 0x243185be4ee4b28cL, 0x550c7dc3d5ffb4e2L, 0x72be5d74f27b896fL, 0x80deb1fe3b1696b1L, 0x9bdc06a725c71235L, 0xc19bf174cf692694L,
      0xe49b69c19ef14ad2L, 0xefbe4786384f25e3L, 0x0fc19dc68b8cd5b5L, 0x240ca1cc77ac9c65L, 0x2de92c6f592b0275L, 0x4a7484aa6ea6e483L, 0x5cb0a9dcbd41fbd4L, 0x76f988da831153b5L,
      0x983e5152ee66dfabL, 0xa831c66d2db43210L, 0xb00327c898fb213fL, 0xbf597fc7beef0ee4L, 0xc6e00bf33da88fc2L, 0xd5a79147930aa725L, 0x06ca6351e003826fL, 0x142929670a0e6e70L,
      0x27b70a8546d22ffcL, 0x2e1b21385c26c926L, 0x4d2c6dfc5ac42aedL, 0x53380d139d95b3dfL, 0x650a73548baf63deL, 0x766a0abb3c77b2a8L, 0x81c2c92e47edaee6L, 0x92722c851482353bL,
      0xa2bfe8a14cf10364L, 0xa81a664bbc423001L, 0xc24b8b70d0f89791L, 0xc76c51a30654be30L, 0xd192e819d6ef5218L, 0xd69906245565a910L, 0xf40e35855771202aL, 0x106aa07032bbd1b8L,
      0x19a4c116b8d2d0c8L, 0x1e376c085141ab53L, 0x2748774cdf8eeb99L, 0x34b0bcb5e19b48a8L, 0x391c0cb3c5c95a63L, 0x4ed8aa4ae3418acbL, 0x5b9cca4f7763e373L, 0x682e6ff3d6b2b8a3L,
      0x748f82ee5defb2fcL, 0x78a5636f43172f60L, 0x84c87814a1f0ab72L, 0x8cc702081a6439ecL, 0x90befffa23631e28L, 0xa4506cebde82bde9L, 0xbef9a3f7b2c67915L, 0xc67178f2e372532bL,
      0xca273eceea26619cL, 0xd186b8c721c0c207L, 0xeada7dd6cde0eb1eL, 0xf57d4f7fee6ed178L, 0x06f067aa72176fbaL, 0x0a637dc5a2c898a6L, 0x113f9804bef90daeL, 0x1b710b35131c471bL,
      0x28db77f523047d84L, 0x32caab7b40c72493L, 0x3c9ebe0a15c9bebcL, 0x431d67c49c100d4cL, 0x4cc5d4becb3e42b6L, 0x597f299cfc657e2aL, 0x5fcb6fab3ad6faecL, 0x6c44198c4a475817L};
    final boolean is384;
    long[] h = new long[8];
    byte[] buf = new byte[128];
    int n; long total;
    Sha512(boolean is384) { super(is384 ? "SHA-384" : "SHA-512"); this.is384 = is384; reset(); }
    MessageDigest copy() { Sha512 c = new Sha512(is384); c.h = h.clone(); c.buf = buf.clone(); c.n = n; c.total = total; return c; }
    public void reset() {
      h = is384 ? new long[] {0xcbbb9d5dc1059ed8L, 0x629a292a367cd507L, 0x9159015a3070dd17L, 0x152fecd8f70e5939L, 0x67332667ffc00b31L, 0x8eb44a8768581511L, 0xdb0c2e0d64f98fa7L, 0x47b5481dbefa4fa4L}
                : new long[] {0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL, 0x3c6ef372fe94f82bL, 0xa54ff53a5f1d36f1L, 0x510e527fade682d1L, 0x9b05688c2b3e6c1fL, 0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L};
      n = 0; total = 0;
    }
    public void update(byte[] b, int off, int len) {
      total += len;
      while (len > 0) {
        int k = Math.min(128 - n, len);
        System.arraycopy(b, off, buf, n, k);
        n += k; off += k; len -= k;
        if (n == 128) { block(buf); n = 0; }
      }
    }
    void block(byte[] x) {
      long[] w = new long[80];
      for (int i = 0; i < 16; i++) { long v = 0; for (int j = 0; j < 8; j++) v = v << 8 | (x[i * 8 + j] & 0xff); w[i] = v; }
      for (int i = 16; i < 80; i++) {
        long s0 = Long.rotateRight(w[i-15], 1) ^ Long.rotateRight(w[i-15], 8) ^ (w[i-15] >>> 7);
        long s1 = Long.rotateRight(w[i-2], 19) ^ Long.rotateRight(w[i-2], 61) ^ (w[i-2] >>> 6);
        w[i] = w[i-16] + s0 + w[i-7] + s1;
      }
      long a = h[0], b = h[1], c = h[2], d = h[3], e = h[4], f = h[5], g = h[6], hh = h[7];
      for (int i = 0; i < 80; i++) {
        long S1 = Long.rotateRight(e, 14) ^ Long.rotateRight(e, 18) ^ Long.rotateRight(e, 41);
        long ch = (e & f) ^ (~e & g);
        long t1 = hh + S1 + ch + K[i] + w[i];
        long S0 = Long.rotateRight(a, 28) ^ Long.rotateRight(a, 34) ^ Long.rotateRight(a, 39);
        long maj = (a & b) ^ (a & c) ^ (b & c);
        long t2 = S0 + maj;
        hh = g; g = f; f = e; e = d + t1; d = c; c = b; b = a; a = t1 + t2;
      }
      h[0] += a; h[1] += b; h[2] += c; h[3] += d; h[4] += e; h[5] += f; h[6] += g; h[7] += hh;
    }
    public byte[] digest() {
      long bits = total * 8;
      byte[] p = new byte[((n < 112) ? 112 - n : 240 - n) + 16];
      p[0] = (byte) 0x80;
      for (int i = 0; i < 8; i++) p[p.length - 8 + i] = (byte) (bits >>> (56 - 8 * i));
      update(p, 0, p.length);
      int words = is384 ? 6 : 8;
      byte[] r = new byte[words * 8];
      for (int i = 0; i < words; i++) for (int j = 0; j < 8; j++) r[i * 8 + j] = (byte) (h[i] >>> (56 - 8 * j));
      reset();
      return r;
    }
  }
}

package android.content.pm;
public class Signature implements android.os.Parcelable {
  final String hex;
  public Signature(String h) { hex = h; }
  public Signature(byte[] b) { StringBuilder s = new StringBuilder(); for (byte x : b) s.append(String.format("%02x", x & 0xff)); hex = s.toString(); }
  public String toCharsString() { return hex; }
  public byte[] toByteArray() { byte[] b = new byte[hex.length() / 2]; for (int i = 0; i < b.length; i++) b[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16); return b; }
  public int hashCode() { return hex.hashCode(); }
  public boolean equals(Object o) { return o instanceof Signature && ((Signature) o).hex.equals(hex); }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
}

package android.system;

/** struct cmsghdr: a control message (sendmsg/recvmsg). */
public final class StructCmsghdr {
  public final int cmsg_level, cmsg_type;
  public final byte[] cmsg_data;
  public StructCmsghdr(int cmsg_level, int cmsg_type, short value) {
    this(cmsg_level, cmsg_type, new byte[] {(byte) value, (byte) (value >> 8)});   // host (little-endian) order
  }
  public StructCmsghdr(int cmsg_level, int cmsg_type, byte[] value) { this.cmsg_level = cmsg_level; this.cmsg_type = cmsg_type; this.cmsg_data = value; }
}

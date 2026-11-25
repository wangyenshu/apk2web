package android.system;

/** struct msghdr: a message for sendmsg/recvmsg. */
public final class StructMsghdr {
  public a2w.jdk.net.SocketAddress msg_name;
  public final java.nio.ByteBuffer[] msg_iov;
  public StructCmsghdr[] msg_control;
  public int msg_flags;
  public StructMsghdr(a2w.jdk.net.SocketAddress msgName, java.nio.ByteBuffer[] msgIov, StructCmsghdr[] msgControl, int msgFlags) {
    msg_name = msgName; msg_iov = msgIov; msg_control = msgControl; msg_flags = msgFlags;
  }
}

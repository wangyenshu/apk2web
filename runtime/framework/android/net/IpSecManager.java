// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.net;

public class IpSecManager {
  public static final int DIRECTION_IN = 0;
  public static final int DIRECTION_OUT = 1;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public IpSecManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.net.IpSecManager.SecurityParameterIndex allocateSecurityParameterIndex(a2w.jdk.net.InetAddress destinationAddress) throws android.net.IpSecManager.ResourceUnavailableException { a2w.Stubs.hit("android.net.IpSecManager.allocateSecurityParameterIndex"); return null; }
  @a2w.NotImplemented public android.net.IpSecManager.SecurityParameterIndex allocateSecurityParameterIndex(a2w.jdk.net.InetAddress destinationAddress, int requestedSpi) throws android.net.IpSecManager.ResourceUnavailableException, android.net.IpSecManager.SpiUnavailableException { a2w.Stubs.hit("android.net.IpSecManager.allocateSecurityParameterIndex"); return null; }
  @a2w.NotImplemented public void applyTransportModeTransform(a2w.jdk.FileDescriptor socket, int direction, android.net.IpSecTransform transform) throws java.io.IOException { a2w.Stubs.hit("android.net.IpSecManager.applyTransportModeTransform"); }
  @a2w.NotImplemented public void applyTransportModeTransform(a2w.jdk.net.DatagramSocket socket, int direction, android.net.IpSecTransform transform) throws java.io.IOException { a2w.Stubs.hit("android.net.IpSecManager.applyTransportModeTransform"); }
  @a2w.NotImplemented public void applyTransportModeTransform(a2w.jdk.net.Socket socket, int direction, android.net.IpSecTransform transform) throws java.io.IOException { a2w.Stubs.hit("android.net.IpSecManager.applyTransportModeTransform"); }
  @a2w.NotImplemented public android.net.IpSecManager.UdpEncapsulationSocket openUdpEncapsulationSocket() throws java.io.IOException, android.net.IpSecManager.ResourceUnavailableException { a2w.Stubs.hit("android.net.IpSecManager.openUdpEncapsulationSocket"); return null; }
  @a2w.NotImplemented public android.net.IpSecManager.UdpEncapsulationSocket openUdpEncapsulationSocket(int port) throws java.io.IOException, android.net.IpSecManager.ResourceUnavailableException { a2w.Stubs.hit("android.net.IpSecManager.openUdpEncapsulationSocket"); return null; }
  @a2w.NotImplemented public void removeTransportModeTransforms(a2w.jdk.FileDescriptor socket) throws java.io.IOException { a2w.Stubs.hit("android.net.IpSecManager.removeTransportModeTransforms"); }
  @a2w.NotImplemented public void removeTransportModeTransforms(a2w.jdk.net.DatagramSocket socket) throws java.io.IOException { a2w.Stubs.hit("android.net.IpSecManager.removeTransportModeTransforms"); }
  @a2w.NotImplemented public void removeTransportModeTransforms(a2w.jdk.net.Socket socket) throws java.io.IOException { a2w.Stubs.hit("android.net.IpSecManager.removeTransportModeTransforms"); }

    public static final class ResourceUnavailableException extends android.util.AndroidException {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public ResourceUnavailableException(a2w.Hidden hidden) { super(); }
  }

    public static final class SecurityParameterIndex implements java.lang.AutoCloseable {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public SecurityParameterIndex(a2w.Hidden hidden) { }
    @a2w.NotImplemented public void close() { a2w.Stubs.hit("android.net.IpSecManager.SecurityParameterIndex.close"); }
    @a2w.NotImplemented protected void finalize() throws java.lang.Throwable { a2w.Stubs.hit("android.net.IpSecManager.SecurityParameterIndex.finalize"); }
    @a2w.NotImplemented public int getSpi() { a2w.Stubs.hit("android.net.IpSecManager.SecurityParameterIndex.getSpi"); return 0; }
    @a2w.NotImplemented public java.lang.String toString() { a2w.Stubs.hit("android.net.IpSecManager.SecurityParameterIndex.toString"); return super.toString(); }
  }

    public static final class SpiUnavailableException extends android.util.AndroidException {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public SpiUnavailableException(a2w.Hidden hidden) { super(); }
    @a2w.NotImplemented public int getSpi() { a2w.Stubs.hit("android.net.IpSecManager.SpiUnavailableException.getSpi"); return 0; }
  }

    public static final class UdpEncapsulationSocket implements java.lang.AutoCloseable {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public UdpEncapsulationSocket(a2w.Hidden hidden) { }
    @a2w.NotImplemented public void close() throws java.io.IOException { a2w.Stubs.hit("android.net.IpSecManager.UdpEncapsulationSocket.close"); }
    @a2w.NotImplemented protected void finalize() throws java.lang.Throwable { a2w.Stubs.hit("android.net.IpSecManager.UdpEncapsulationSocket.finalize"); }
    @a2w.NotImplemented public a2w.jdk.FileDescriptor getFileDescriptor() { a2w.Stubs.hit("android.net.IpSecManager.UdpEncapsulationSocket.getFileDescriptor"); return null; }
    @a2w.NotImplemented public int getPort() { a2w.Stubs.hit("android.net.IpSecManager.UdpEncapsulationSocket.getPort"); return 0; }
    @a2w.NotImplemented public java.lang.String toString() { a2w.Stubs.hit("android.net.IpSecManager.UdpEncapsulationSocket.toString"); return super.toString(); }
  }
}

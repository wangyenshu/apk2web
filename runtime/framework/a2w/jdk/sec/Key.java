package a2w.jdk.sec;
public interface Key extends java.io.Serializable { String getAlgorithm(); String getFormat(); byte[] getEncoded(); }

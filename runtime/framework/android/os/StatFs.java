package android.os;
public class StatFs {
  public StatFs(String path) {}
  public void restat(String path) {}
  public int getBlockSize() { return 4096; }
  public int getBlockCount() { return 1 << 20; }
  public int getFreeBlocks() { return 1 << 19; }
  public int getAvailableBlocks() { return 1 << 19; }
}

package android.system;

public final class StructStat {
  public final long st_dev, st_ino, st_nlink, st_rdev, st_size, st_blksize, st_blocks, st_atime, st_mtime, st_ctime;
  public final int st_mode, st_uid, st_gid;
  public final StructTimespec st_atim, st_mtim, st_ctim;
  public StructStat(long dev, long ino, int mode, long nlink, int uid, int gid, long rdev, long size, long atime, long mtime, long ctime, long blksize, long blocks) {
    this(dev, ino, mode, nlink, uid, gid, rdev, size, new StructTimespec(atime, 0), new StructTimespec(mtime, 0), new StructTimespec(ctime, 0), blksize, blocks);
  }
  public StructStat(long dev, long ino, int mode, long nlink, int uid, int gid, long rdev, long size, StructTimespec atim, StructTimespec mtim, StructTimespec ctim, long blksize, long blocks) {
    st_dev = dev; st_ino = ino; st_mode = mode; st_nlink = nlink; st_uid = uid; st_gid = gid; st_rdev = rdev; st_size = size;
    st_atim = atim; st_mtim = mtim; st_ctim = ctim; st_atime = atim.tv_sec; st_mtime = mtim.tv_sec; st_ctime = ctim.tv_sec; st_blksize = blksize; st_blocks = blocks;
  }
  @Override public String toString() { return "StructStat{st_mode=" + Integer.toOctalString(st_mode) + ", st_size=" + st_size + ", st_mtime=" + st_mtime + "}"; }
}

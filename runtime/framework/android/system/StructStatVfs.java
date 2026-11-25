package android.system;

/** struct statvfs: file system statistics. */
public final class StructStatVfs {
  public final long f_bsize, f_frsize, f_blocks, f_bfree, f_bavail, f_files, f_ffree, f_favail, f_fsid, f_flag, f_namemax;
  public StructStatVfs(long f_bsize, long f_frsize, long f_blocks, long f_bfree, long f_bavail, long f_files, long f_ffree, long f_favail, long f_fsid, long f_flag, long f_namemax) {
    this.f_bsize = f_bsize; this.f_frsize = f_frsize; this.f_blocks = f_blocks; this.f_bfree = f_bfree; this.f_bavail = f_bavail; this.f_files = f_files;
    this.f_ffree = f_ffree; this.f_favail = f_favail; this.f_fsid = f_fsid; this.f_flag = f_flag; this.f_namemax = f_namemax;
  }
  @Override public String toString() {
    return "StructStatVfs{f_bsize=" + f_bsize + ", f_frsize=" + f_frsize + ", f_blocks=" + f_blocks + ", f_bfree=" + f_bfree + ", f_bavail=" + f_bavail + ", f_files=" + f_files
        + ", f_ffree=" + f_ffree + ", f_favail=" + f_favail + ", f_fsid=" + f_fsid + ", f_flag=" + f_flag + ", f_namemax=" + f_namemax + "}";
  }
}

package android.content.pm;
public class PathPermission extends android.os.PatternMatcher {
  private final String mReadPermission, mWritePermission;
  public PathPermission(String pattern, int type, String readPermission, String writePermission) { super(pattern, type); mReadPermission = readPermission; mWritePermission = writePermission; }
  public String getReadPermission() { return mReadPermission; }
  public String getWritePermission() { return mWritePermission; }
}

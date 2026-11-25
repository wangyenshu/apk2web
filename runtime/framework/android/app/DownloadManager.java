package android.app;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class DownloadManager {
  public static final String COLUMN_ID = "_id", COLUMN_STATUS = "status", COLUMN_REASON = "reason", COLUMN_LOCAL_URI = "local_uri",
      COLUMN_TOTAL_SIZE_BYTES = "total_size", COLUMN_BYTES_DOWNLOADED_SO_FAR = "bytes_so_far", COLUMN_URI = "uri", COLUMN_TITLE = "title", COLUMN_LOCAL_FILENAME = "local_filename";
  public static final int STATUS_PENDING = 1, STATUS_RUNNING = 2, STATUS_PAUSED = 4, STATUS_SUCCESSFUL = 8, STATUS_FAILED = 16, ERROR_UNKNOWN = 1000;
  public static final String ACTION_DOWNLOAD_COMPLETE = "android.intent.action.DOWNLOAD_COMPLETE", EXTRA_DOWNLOAD_ID = "extra_download_id", ACTION_NOTIFICATION_CLICKED = "android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED";
  public static class Request {
    public static final int NETWORK_MOBILE = 1, NETWORK_WIFI = 2, VISIBILITY_VISIBLE = 0, VISIBILITY_VISIBLE_NOTIFY_COMPLETED = 1, VISIBILITY_HIDDEN = 2;
    final Uri uri;
    public Request(Uri u) { uri = u; }
    public Request setAllowedNetworkTypes(int f) { return this; }
    public Request setAllowedOverRoaming(boolean a) { return this; }
    public Request setDestinationUri(Uri u) { return this; }
    public Request setDestinationInExternalPublicDir(String t, String p) { return this; }
    public Request setShowRunningNotification(boolean s) { return this; }
    public Request setNotificationVisibility(int v) { return this; }
    public Request setVisibleInDownloadsUi(boolean v) { return this; }
    public Request setTitle(CharSequence t) { return this; }
    public Request setDescription(CharSequence d) { return this; }
    public Request setMimeType(String m) { return this; }
    public Request addRequestHeader(String h, String v) { return this; }
  }
  public static class Query {
    public Query setFilterById(long... ids) { return this; }
    public Query setFilterByStatus(int flags) { return this; }
  }
  public long enqueue(Request r) { android.util.Log.w("DownloadManager", "download not supported: " + r.uri); return 1; }
  public int remove(long... ids) { return 0; }
  public Cursor query(Query q) {
    MatrixCursor c = new MatrixCursor(new String[] {COLUMN_ID, COLUMN_STATUS, COLUMN_REASON, COLUMN_LOCAL_URI, COLUMN_TOTAL_SIZE_BYTES, COLUMN_BYTES_DOWNLOADED_SO_FAR});
    c.addRow(new Object[] {1L, STATUS_FAILED, ERROR_UNKNOWN, null, 0L, 0L});
    return c;
  }
}

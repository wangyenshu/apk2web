package android.content;

import android.accounts.Account;
import android.content.res.AssetFileDescriptor;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/** Content URIs: this app's own providers (called in-process), files and android.resource; other apps' providers are absent. */
public class ContentResolver {
  private static final String TAG = "ContentResolver";
  public static final String SCHEME_CONTENT = "content", SCHEME_ANDROID_RESOURCE = "android.resource", SCHEME_FILE = "file";
  public static final String CURSOR_ITEM_BASE_TYPE = "vnd.android.cursor.item", CURSOR_DIR_BASE_TYPE = "vnd.android.cursor.dir", ANY_CURSOR_ITEM_TYPE = "vnd.android.cursor.item/*";
  public static final String QUERY_ARG_SQL_SELECTION = "android:query-arg-sql-selection", QUERY_ARG_SQL_SELECTION_ARGS = "android:query-arg-sql-selection-args",
      QUERY_ARG_SQL_SORT_ORDER = "android:query-arg-sql-sort-order", QUERY_ARG_SQL_GROUP_BY = "android:query-arg-sql-group-by",
      QUERY_ARG_SQL_HAVING = "android:query-arg-sql-having", QUERY_ARG_SQL_LIMIT = "android:query-arg-sql-limit",
      QUERY_ARG_LIMIT = "android:query-arg-limit", QUERY_ARG_OFFSET = "android:query-arg-offset", QUERY_ARG_SORT_COLUMNS = "android:query-arg-sort-columns";
  public static final String SYNC_EXTRAS_MANUAL = "force", SYNC_EXTRAS_EXPEDITED = "expedited", SYNC_EXTRAS_UPLOAD = "upload";
  public static final int SYNC_OBSERVER_TYPE_SETTINGS = 1, SYNC_OBSERVER_TYPE_PENDING = 2, SYNC_OBSERVER_TYPE_ACTIVE = 4;
  public static final int NOTIFY_SYNC_TO_NETWORK = 1, NOTIFY_SKIP_NOTIFY_FOR_DESCENDANTS = 2, NOTIFY_INSERT = 4, NOTIFY_UPDATE = 8, NOTIFY_DELETE = 16;

  private static final ArrayList<ContentProvider> providers = new ArrayList<>();
  private static final ArrayList<Object[]> observers = new ArrayList<>();   // {Uri, Boolean descendants, ContentObserver}
  private final Context mContext;
  public ContentResolver(Context context) { mContext = context; }

  /** web: a provider of this app, created at startup (ActivityThread installs them before Application.onCreate) */
  public static void installProvider(ContentProvider p) { providers.add(p); }
  static ContentProvider provider(String authority) {
    if (authority == null) return null;
    for (ContentProvider p : providers) {
      String[] a = p.authorities();
      if (a != null) for (String s : a) if (s.equals(authority)) return p;
    }
    return null;
  }
  private static ContentProvider provider(Uri uri) {
    if (!SCHEME_CONTENT.equals(uri.getScheme())) return null;
    ContentProvider p = provider(uri.getAuthority());
    if (p == null) Log.w(TAG, "Failed to find provider info for " + uri.getAuthority());
    return p;
  }

  public final String getType(Uri url) { ContentProvider p = provider(url); return p == null ? null : p.getType(url); }
  public String[] getStreamTypes(Uri url, String mimeTypeFilter) { ContentProvider p = provider(url); return p == null ? null : p.getStreamTypes(url, mimeTypeFilter); }
  public final Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    ContentProvider p = provider(uri);
    return p == null ? null : p.query(uri, projection, selection, selectionArgs, sortOrder);
  }
  public final Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, android.os.CancellationSignal cs) {
    ContentProvider p = provider(uri);
    return p == null ? null : p.query(uri, projection, selection, selectionArgs, sortOrder, cs);
  }
  public final Cursor query(Uri uri, String[] projection, Bundle queryArgs, android.os.CancellationSignal cs) {
    ContentProvider p = provider(uri);
    return p == null ? null : p.query(uri, projection, queryArgs, cs);
  }
  public final Uri canonicalize(Uri url) { ContentProvider p = provider(url); return p == null ? null : p.canonicalize(url); }
  public final Uri uncanonicalize(Uri url) { ContentProvider p = provider(url); return p == null ? null : p.uncanonicalize(url); }
  public final boolean refresh(Uri url, Bundle args, android.os.CancellationSignal cs) { ContentProvider p = provider(url); return p != null && p.refresh(url, args, cs); }
  public final Uri insert(Uri url, ContentValues values) {
    ContentProvider p = provider(url);
    if (p == null) throw new IllegalArgumentException("Unknown URL " + url);
    return p.insert(url, values);
  }
  public final Uri insert(Uri url, ContentValues values, Bundle extras) { return insert(url, values); }
  public ContentProviderResult[] applyBatch(String authority, ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
    ContentProvider p = provider(authority);
    if (p == null) throw new IllegalArgumentException("Unknown authority " + authority);
    return p.applyBatch(operations);
  }
  public final int bulkInsert(Uri url, ContentValues[] values) {
    ContentProvider p = provider(url);
    if (p == null) throw new IllegalArgumentException("Unknown URL " + url);
    return p.bulkInsert(url, values);
  }
  public final int delete(Uri url, String where, String[] selectionArgs) {
    ContentProvider p = provider(url);
    if (p == null) throw new IllegalArgumentException("Unknown URL " + url);
    return p.delete(url, where, selectionArgs);
  }
  public final int delete(Uri url, Bundle extras) { ContentProvider p = provider(url); if (p == null) throw new IllegalArgumentException("Unknown URL " + url); return p.delete(url, extras); }
  public final int update(Uri uri, ContentValues values, String where, String[] selectionArgs) {
    ContentProvider p = provider(uri);
    if (p == null) throw new IllegalArgumentException("Unknown URI " + uri);
    return p.update(uri, values, where, selectionArgs);
  }
  public final int update(Uri uri, ContentValues values, Bundle extras) { ContentProvider p = provider(uri); if (p == null) throw new IllegalArgumentException("Unknown URI " + uri); return p.update(uri, values, extras); }
  public final Bundle call(Uri uri, String method, String arg, Bundle extras) { ContentProvider p = provider(uri); if (p == null) throw new IllegalArgumentException("Unknown URI " + uri); return p.call(method, arg, extras); }
  public final Bundle call(String authority, String method, String arg, Bundle extras) { ContentProvider p = provider(authority); if (p == null) throw new IllegalArgumentException("Unknown authority " + authority); return p.call(method, arg, extras); }

  // ---------------------------------------------------------------- streams
  public final InputStream openInputStream(Uri uri) throws FileNotFoundException {
    String scheme = uri.getScheme();
    if (SCHEME_ANDROID_RESOURCE.equals(scheme)) {
      int id = resourceId(uri);
      try { return mContext.getResources().openRawResource(id); } catch (RuntimeException e) { throw new FileNotFoundException("Resource does not exist: " + uri); }
    }
    if (SCHEME_FILE.equals(scheme) || scheme == null) {
      String path = uri.getPath();
      if (path != null && path.startsWith("/android_asset/")) {
        try { return mContext.getAssets().open(path.substring("/android_asset/".length())); } catch (java.io.IOException e) { throw new FileNotFoundException(e.getMessage()); }
      }
      return new FileInputStream(new File(path));
    }
    AssetFileDescriptor fd = openAssetFileDescriptor(uri, "r");
    if (fd == null) throw new FileNotFoundException("No content provider: " + uri);
    return fd.createInputStream();
  }
  public final OutputStream openOutputStream(Uri uri) throws FileNotFoundException { return openOutputStream(uri, "w"); }
  public final OutputStream openOutputStream(Uri uri, String mode) throws FileNotFoundException {
    if (SCHEME_FILE.equals(uri.getScheme())) return new FileOutputStream(new File(uri.getPath()), mode.contains("a"));
    AssetFileDescriptor fd = openAssetFileDescriptor(uri, mode);
    if (fd == null) throw new FileNotFoundException("No content provider: " + uri);
    try { return fd.createOutputStream(); } catch (java.io.IOException e) { throw new FileNotFoundException(e.getMessage()); }
  }
  public final ParcelFileDescriptor openFileDescriptor(Uri uri, String mode) throws FileNotFoundException {
    if (SCHEME_FILE.equals(uri.getScheme())) return ParcelFileDescriptor.open(new File(uri.getPath()), ParcelFileDescriptor.parseMode(mode));
    ContentProvider p = provider(uri);
    if (p == null) throw new FileNotFoundException("No content provider: " + uri);
    return p.openFile(uri, mode);
  }
  public final ParcelFileDescriptor openFileDescriptor(Uri uri, String mode, android.os.CancellationSignal cs) throws FileNotFoundException { return openFileDescriptor(uri, mode); }
  public final AssetFileDescriptor openAssetFileDescriptor(Uri uri, String mode) throws FileNotFoundException {
    if (SCHEME_FILE.equals(uri.getScheme())) return new AssetFileDescriptor(ParcelFileDescriptor.open(new File(uri.getPath()), ParcelFileDescriptor.parseMode(mode)), 0, -1);
    ContentProvider p = provider(uri);
    if (p == null) throw new FileNotFoundException("No content provider: " + uri);
    return p.openAssetFile(uri, mode);
  }
  public final AssetFileDescriptor openAssetFileDescriptor(Uri uri, String mode, android.os.CancellationSignal cs) throws FileNotFoundException { return openAssetFileDescriptor(uri, mode); }
  public final AssetFileDescriptor openTypedAssetFileDescriptor(Uri uri, String mimeType, Bundle opts) throws FileNotFoundException { return openAssetFileDescriptor(uri, "r"); }
  private int resourceId(Uri uri) throws FileNotFoundException {
    List<String> path = uri.getPathSegments();
    int len = path.size();
    if (len == 1) { try { return Integer.parseInt(path.get(0)); } catch (NumberFormatException e) { throw new FileNotFoundException("Single path segment is not a resource ID: " + uri); } }
    if (len == 2) {
      int id = mContext.getResources().getIdentifier(path.get(1), path.get(0), uri.getAuthority());
      if (id == 0) throw new FileNotFoundException("No resource found for: " + uri);
      return id;
    }
    throw new FileNotFoundException("More than two path segments: " + uri);
  }

  // ---------------------------------------------------------------- change notification
  public final void registerContentObserver(Uri uri, boolean notifyForDescendants, ContentObserver observer) {
    if (observer == null) throw new NullPointerException("observer");
    synchronized (observers) { observers.add(new Object[] {uri, notifyForDescendants, observer}); }
  }
  public final void unregisterContentObserver(ContentObserver observer) {
    synchronized (observers) { observers.removeIf(o -> o[2] == observer); }
  }
  public void notifyChange(Uri uri, ContentObserver observer) { notifyChange(uri, observer, true); }
  public void notifyChange(Uri uri, ContentObserver observer, boolean syncToNetwork) {
    String target = uri.toString();
    ArrayList<ContentObserver> hit = new ArrayList<>();
    synchronized (observers) {
      for (Object[] o : observers) {
        String reg = o[0].toString();
        boolean descendants = (Boolean) o[1];
        // an observer of a URI hears about it, its ancestors' observers with notifyForDescendants, and it hears about its descendants
        if (target.equals(reg) || descendants && target.startsWith(reg.endsWith("/") ? reg : reg + "/") || reg.startsWith(target.endsWith("/") ? target : target + "/")) hit.add((ContentObserver) o[2]);
      }
    }
    for (ContentObserver o : hit) {
      boolean self = o == observer;
      if (self && !o.deliverSelfNotifications()) continue;
      o.dispatchChange(self, uri);
    }
  }
  public void notifyChange(Uri uri, ContentObserver observer, int flags) { notifyChange(uri, observer, true); }
  public void notifyChange(java.util.Collection<Uri> uris, ContentObserver observer, int flags) { for (Uri u : uris) notifyChange(u, observer, flags); }

  // ---------------------------------------------------------------- providers, sync (no accounts here)
  public final ContentProviderClient acquireContentProviderClient(Uri uri) { ContentProvider p = provider(uri); return p == null ? null : new ContentProviderClient(this, p); }
  public final ContentProviderClient acquireContentProviderClient(String name) { ContentProvider p = provider(name); return p == null ? null : new ContentProviderClient(this, p); }
  public final ContentProviderClient acquireUnstableContentProviderClient(Uri uri) { return acquireContentProviderClient(uri); }
  public final ContentProviderClient acquireUnstableContentProviderClient(String name) { return acquireContentProviderClient(name); }
  public void takePersistableUriPermission(Uri uri, int modeFlags) {}
  public void releasePersistableUriPermission(Uri uri, int modeFlags) {}
  public List<UriPermission> getPersistedUriPermissions() { return new ArrayList<>(); }
  public static void requestSync(Account account, String authority, Bundle extras) {}
  public void startSync(Uri uri, Bundle extras) {}
  public static void cancelSync(Account account, String authority) {}
  public void cancelSync(Uri uri) {}
  public static boolean getSyncAutomatically(Account account, String authority) { return false; }
  public static void setSyncAutomatically(Account account, String authority, boolean sync) {}
  public static void addPeriodicSync(Account account, String authority, Bundle extras, long pollFrequency) {}
  public static void removePeriodicSync(Account account, String authority, Bundle extras) {}
  public static int getIsSyncable(Account account, String authority) { return 0; }
  public static void setIsSyncable(Account account, String authority, int syncable) {}
  public static boolean getMasterSyncAutomatically() { return false; }
  public static void setMasterSyncAutomatically(boolean sync) {}
  public static boolean isSyncActive(Account account, String authority) { return false; }
  public static boolean isSyncPending(Account account, String authority) { return false; }
  public static Object addStatusChangeListener(int mask, SyncStatusObserver callback) { return new Object(); }
  public static void removeStatusChangeListener(Object handle) {}
}

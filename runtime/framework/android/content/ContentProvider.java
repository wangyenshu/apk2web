package android.content;

import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/** A content provider of this app (manifest <provider>), called directly by the ContentResolver. */
public abstract class ContentProvider implements ComponentCallbacks2 {
  private Context mContext;
  private String mReadPermission, mWritePermission;
  private String[] mAuthorities;
  private boolean mExported;
  public ContentProvider() {}
  public final Context getContext() { return mContext; }
  public final Context requireContext() { if (mContext == null) throw new IllegalStateException("Cannot find context from the provider."); return mContext; }
  public final String getCallingPackage() { return mContext == null ? null : mContext.getPackageName(); }
  public final String getCallingAttributionTag() { return null; }
  protected final void setAuthorities(String authorities) { mAuthorities = authorities == null ? null : authorities.split(";"); }
  protected final void setReadPermission(String permission) { mReadPermission = permission; }
  public final String getReadPermission() { return mReadPermission; }
  protected final void setWritePermission(String permission) { mWritePermission = permission; }
  public final String getWritePermission() { return mWritePermission; }
  protected final void setPathPermissions(android.content.pm.PathPermission[] permissions) {}
  public abstract boolean onCreate();
  public void onConfigurationChanged(android.content.res.Configuration newConfig) {}
  public void onLowMemory() {}
  public void onTrimMemory(int level) {}
  public abstract Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder);
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, android.os.CancellationSignal cancellationSignal) { return query(uri, projection, selection, selectionArgs, sortOrder); }
  public Cursor query(Uri uri, String[] projection, Bundle queryArgs, android.os.CancellationSignal cancellationSignal) {
    if (queryArgs == null) queryArgs = Bundle.EMPTY;
    String sortOrder = queryArgs.getString(ContentResolver.QUERY_ARG_SQL_SORT_ORDER);
    return query(uri, projection, queryArgs.getString(ContentResolver.QUERY_ARG_SQL_SELECTION), queryArgs.getStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS), sortOrder, cancellationSignal);
  }
  public abstract String getType(Uri uri);
  public Uri canonicalize(Uri url) { return null; }
  public Uri uncanonicalize(Uri url) { return url; }
  public boolean refresh(Uri uri, Bundle args, android.os.CancellationSignal cancellationSignal) { return false; }
  public abstract Uri insert(Uri uri, ContentValues values);
  public Uri insert(Uri uri, ContentValues values, Bundle extras) { return insert(uri, values); }
  public int bulkInsert(Uri uri, ContentValues[] values) {
    int numValues = values.length;
    for (int i = 0; i < numValues; i++) insert(uri, values[i]);
    return numValues;
  }
  public abstract int delete(Uri uri, String selection, String[] selectionArgs);
  public int delete(Uri uri, Bundle extras) { extras = extras == null ? Bundle.EMPTY : extras; return delete(uri, extras.getString(ContentResolver.QUERY_ARG_SQL_SELECTION), extras.getStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS)); }
  public abstract int update(Uri uri, ContentValues values, String selection, String[] selectionArgs);
  public int update(Uri uri, ContentValues values, Bundle extras) { extras = extras == null ? Bundle.EMPTY : extras; return update(uri, values, extras.getString(ContentResolver.QUERY_ARG_SQL_SELECTION), extras.getStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS)); }
  public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException { throw new FileNotFoundException("No files supported by provider at " + uri); }
  public ParcelFileDescriptor openFile(Uri uri, String mode, android.os.CancellationSignal signal) throws FileNotFoundException { return openFile(uri, mode); }
  public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
    ParcelFileDescriptor fd = openFile(uri, mode);
    return fd != null ? new AssetFileDescriptor(fd, 0, -1) : null;
  }
  public AssetFileDescriptor openAssetFile(Uri uri, String mode, android.os.CancellationSignal signal) throws FileNotFoundException { return openAssetFile(uri, mode); }
  protected final ParcelFileDescriptor openFileHelper(Uri uri, String mode) throws FileNotFoundException {
    Cursor c = query(uri, new String[] {"_data"}, null, null, null);
    int count = (c != null) ? c.getCount() : 0;
    if (count != 1) {
      if (c != null) c.close();
      if (count == 0) throw new FileNotFoundException("No entry for " + uri);
      throw new FileNotFoundException("Multiple items at " + uri);
    }
    c.moveToFirst();
    int i = c.getColumnIndex("_data");
    String path = (i >= 0 ? c.getString(i) : null);
    c.close();
    if (path == null) throw new FileNotFoundException("Column _data not found.");
    return ParcelFileDescriptor.open(new File(path), ParcelFileDescriptor.parseMode(mode));
  }
  public String[] getStreamTypes(Uri uri, String mimeTypeFilter) { return null; }
  public AssetFileDescriptor openTypedAssetFile(Uri uri, String mimeTypeFilter, Bundle opts) throws FileNotFoundException { return openAssetFile(uri, "r"); }
  public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
    final int numOperations = operations.size();
    final ContentProviderResult[] results = new ContentProviderResult[numOperations];
    for (int i = 0; i < numOperations; i++) results[i] = operations.get(i).apply(this, results, i);
    return results;
  }
  public ContentProviderResult[] applyBatch(String authority, ArrayList<ContentProviderOperation> operations) throws OperationApplicationException { return applyBatch(operations); }
  public Bundle call(String method, String arg, Bundle extras) { return null; }
  public Bundle call(String authority, String method, String arg, Bundle extras) { return call(method, arg, extras); }
  public void shutdown() {}
  public void dump(java.io.FileDescriptor fd, java.io.PrintWriter writer, String[] args) {}
  public final boolean isTemporary() { return false; }
  public void attachInfo(Context context, ProviderInfo info) {
    if (mContext == null) {
      mContext = context;
      if (info != null) {
        setReadPermission(info.readPermission);
        setWritePermission(info.writePermission);
        setAuthorities(info.authority);
        mExported = info.exported;
      }
      ContentProvider.this.onCreate();
    }
  }
  public final String[] authorities() { return mAuthorities; }
  public final boolean isExported() { return mExported; }
}

package android.content;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ContentProviderClient implements AutoCloseable {
  private final ContentProvider mProvider;
  ContentProviderClient(ContentResolver r, ContentProvider p) { mProvider = p; }
  public Cursor query(Uri url, String[] projection, String selection, String[] selectionArgs, String sortOrder) { return mProvider.query(url, projection, selection, selectionArgs, sortOrder); }
  public Cursor query(Uri url, String[] projection, String selection, String[] selectionArgs, String sortOrder, android.os.CancellationSignal cs) { return mProvider.query(url, projection, selection, selectionArgs, sortOrder, cs); }
  public String getType(Uri url) { return mProvider.getType(url); }
  public Uri insert(Uri url, ContentValues initialValues) { return mProvider.insert(url, initialValues); }
  public int bulkInsert(Uri url, ContentValues[] initialValues) { return mProvider.bulkInsert(url, initialValues); }
  public int delete(Uri url, String selection, String[] selectionArgs) { return mProvider.delete(url, selection, selectionArgs); }
  public int update(Uri url, ContentValues values, String selection, String[] selectionArgs) { return mProvider.update(url, values, selection, selectionArgs); }
  public android.os.ParcelFileDescriptor openFile(Uri url, String mode) throws FileNotFoundException { return mProvider.openFile(url, mode); }
  public android.content.res.AssetFileDescriptor openAssetFile(Uri url, String mode) throws FileNotFoundException { return mProvider.openAssetFile(url, mode); }
  public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException { return mProvider.applyBatch(operations); }
  public Bundle call(String method, String arg, Bundle extras) { return mProvider.call(method, arg, extras); }
  public ContentProvider getLocalContentProvider() { return mProvider; }
  public void close() {}
  @Deprecated public boolean release() { return true; }
}

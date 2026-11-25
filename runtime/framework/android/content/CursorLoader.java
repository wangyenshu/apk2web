package android.content;

import android.database.Cursor;
import android.net.Uri;

public class CursorLoader extends AsyncTaskLoader<Cursor> {
  final ForceLoadContentObserver mObserver;
  Uri mUri;
  String[] mProjection, mSelectionArgs;
  String mSelection, mSortOrder;
  Cursor mCursor;
  public CursorLoader(Context context) { super(context); mObserver = new ForceLoadContentObserver(); }
  public CursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    super(context);
    mObserver = new ForceLoadContentObserver();
    mUri = uri; mProjection = projection; mSelection = selection; mSelectionArgs = selectionArgs; mSortOrder = sortOrder;
  }
  @Override public Cursor loadInBackground() {
    Cursor cursor = getContext().getContentResolver().query(mUri, mProjection, mSelection, mSelectionArgs, mSortOrder);
    if (cursor != null) {
      try { cursor.getCount(); cursor.registerContentObserver(mObserver); }
      catch (RuntimeException ex) { cursor.close(); throw ex; }
    }
    return cursor;
  }
  @Override public void deliverResult(Cursor cursor) {
    if (isReset()) { if (cursor != null) cursor.close(); return; }
    Cursor oldCursor = mCursor;
    mCursor = cursor;
    if (isStarted()) super.deliverResult(cursor);
    if (oldCursor != null && oldCursor != cursor && !oldCursor.isClosed()) oldCursor.close();
  }
  @Override protected void onStartLoading() {
    if (mCursor != null) deliverResult(mCursor);
    if (takeContentChanged() || mCursor == null) forceLoad();
  }
  @Override protected void onStopLoading() { cancelLoad(); }
  @Override public void onCanceled(Cursor cursor) { if (cursor != null && !cursor.isClosed()) cursor.close(); }
  @Override protected void onReset() {
    super.onReset();
    onStopLoading();
    if (mCursor != null && !mCursor.isClosed()) mCursor.close();
    mCursor = null;
  }
  public Uri getUri() { return mUri; }
  public void setUri(Uri uri) { mUri = uri; }
  public String[] getProjection() { return mProjection; }
  public void setProjection(String[] projection) { mProjection = projection; }
  public String getSelection() { return mSelection; }
  public void setSelection(String selection) { mSelection = selection; }
  public String[] getSelectionArgs() { return mSelectionArgs; }
  public void setSelectionArgs(String[] selectionArgs) { mSelectionArgs = selectionArgs; }
  public String getSortOrder() { return mSortOrder; }
  public void setSortOrder(String sortOrder) { mSortOrder = sortOrder; }
}

package android.content;

import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class ContentProviderOperation implements android.os.Parcelable {
  public static final int TYPE_INSERT = 1, TYPE_UPDATE = 2, TYPE_DELETE = 3, TYPE_ASSERT = 4, TYPE_CALL = 5;
  private final int mType;
  private final Uri mUri;
  private final String mSelection;
  private final String[] mSelectionArgs;
  private final ContentValues mValues;
  private final Integer mExpectedCount;
  private final ContentValues mValuesBackReferences;
  private final Map<Integer, Integer> mSelectionArgsBackReferences;
  private final boolean mYieldAllowed;
  private ContentProviderOperation(Builder b) {
    mType = b.mType; mUri = b.mUri; mValues = b.mValues; mSelection = b.mSelection; mSelectionArgs = b.mSelectionArgs; mExpectedCount = b.mExpectedCount;
    mSelectionArgsBackReferences = b.mSelectionArgsBackReferences; mValuesBackReferences = b.mValuesBackReferences; mYieldAllowed = b.mYieldAllowed;
  }
  public Uri getUri() { return mUri; }
  public boolean isYieldAllowed() { return mYieldAllowed; }
  public int getType() { return mType; }
  public boolean isInsert() { return mType == TYPE_INSERT; }
  public boolean isDelete() { return mType == TYPE_DELETE; }
  public boolean isUpdate() { return mType == TYPE_UPDATE; }
  public boolean isAssertQuery() { return mType == TYPE_ASSERT; }
  public boolean isWriteOperation() { return mType == TYPE_DELETE || mType == TYPE_INSERT || mType == TYPE_UPDATE; }
  public boolean isReadOperation() { return mType == TYPE_ASSERT; }
  public static Builder newInsert(Uri uri) { return new Builder(TYPE_INSERT, uri); }
  public static Builder newUpdate(Uri uri) { return new Builder(TYPE_UPDATE, uri); }
  public static Builder newDelete(Uri uri) { return new Builder(TYPE_DELETE, uri); }
  public static Builder newAssertQuery(Uri uri) { return new Builder(TYPE_ASSERT, uri); }
  public ContentProviderResult apply(ContentProvider provider, ContentProviderResult[] backRefs, int numBackRefs) throws OperationApplicationException {
    ContentValues values = resolveValueBackReferences(backRefs, numBackRefs);
    String[] selectionArgs = resolveSelectionArgsBackReferences(backRefs, numBackRefs);
    if (mType == TYPE_INSERT) {
      Uri newUri = provider.insert(mUri, values);
      if (newUri == null) throw new OperationApplicationException("insert failed");
      return new ContentProviderResult(newUri);
    }
    int numRows;
    if (mType == TYPE_DELETE) numRows = provider.delete(mUri, mSelection, selectionArgs);
    else if (mType == TYPE_UPDATE) numRows = provider.update(mUri, values, mSelection, selectionArgs);
    else if (mType == TYPE_ASSERT) {
      String[] projection = null;
      if (values != null) projection = values.keySet().toArray(new String[0]);
      Cursor cursor = provider.query(mUri, projection, mSelection, selectionArgs, null);
      try {
        numRows = cursor.getCount();
        if (projection != null) {
          while (cursor.moveToNext()) {
            for (int i = 0; i < projection.length; i++) {
              String cursorValue = cursor.getString(i);
              String expectedValue = values.getAsString(projection[i]);
              if (cursorValue == null ? expectedValue != null : !cursorValue.equals(expectedValue))
                throw new OperationApplicationException("Found value " + cursorValue + " when expected " + expectedValue + " for column " + projection[i]);
            }
          }
        }
      } finally { cursor.close(); }
    } else throw new IllegalStateException("bad type, " + mType);
    if (mExpectedCount != null && mExpectedCount != numRows) throw new OperationApplicationException("wrong number of rows: " + numRows);
    return new ContentProviderResult(numRows);
  }
  public ContentValues resolveValueBackReferences(ContentProviderResult[] backRefs, int numBackRefs) {
    if (mValuesBackReferences == null) return mValues;
    final ContentValues values = mValues == null ? new ContentValues() : new ContentValues(mValues);
    for (Map.Entry<String, Object> entry : mValuesBackReferences.valueSet()) {
      String key = entry.getKey();
      Integer backRefIndex = mValuesBackReferences.getAsInteger(key);
      if (backRefIndex == null) throw new IllegalArgumentException("values backref " + key + " is not an integer");
      values.put(key, backRefToValue(backRefs, numBackRefs, backRefIndex));
    }
    return values;
  }
  public String[] resolveSelectionArgsBackReferences(ContentProviderResult[] backRefs, int numBackRefs) {
    if (mSelectionArgsBackReferences == null) return mSelectionArgs;
    String[] newArgs = new String[mSelectionArgs.length];
    System.arraycopy(mSelectionArgs, 0, newArgs, 0, mSelectionArgs.length);
    for (Map.Entry<Integer, Integer> e : mSelectionArgsBackReferences.entrySet()) newArgs[e.getKey()] = String.valueOf(backRefToValue(backRefs, numBackRefs, e.getValue()));
    return newArgs;
  }
  private long backRefToValue(ContentProviderResult[] backRefs, int numBackRefs, Integer backRefIndex) {
    if (backRefIndex >= numBackRefs) throw new ArrayIndexOutOfBoundsException("asked for back ref " + backRefIndex + " but there are only " + numBackRefs + " back refs");
    ContentProviderResult backRef = backRefs[backRefIndex];
    return backRef.uri != null ? ContentUris.parseId(backRef.uri) : backRef.count;
  }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel dest, int flags) {}
  @Override public String toString() { return "mType: " + mType + ", mUri: " + mUri; }

  public static class Builder {
    private final int mType;
    private final Uri mUri;
    private String mSelection;
    private String[] mSelectionArgs;
    private ContentValues mValues;
    private Integer mExpectedCount;
    private ContentValues mValuesBackReferences;
    private Map<Integer, Integer> mSelectionArgsBackReferences;
    private boolean mYieldAllowed;
    private Builder(int type, Uri uri) { mType = type; mUri = uri; }
    public ContentProviderOperation build() {
      if (mType == TYPE_UPDATE && (mValues == null || mValues.isEmpty()) && (mValuesBackReferences == null || mValuesBackReferences.isEmpty()))
        throw new IllegalArgumentException("Empty values");
      if (mType == TYPE_ASSERT && (mValues == null || mValues.isEmpty()) && (mValuesBackReferences == null || mValuesBackReferences.isEmpty()) && mExpectedCount == null)
        throw new IllegalArgumentException("Empty values");
      return new ContentProviderOperation(this);
    }
    public Builder withValueBackReferences(ContentValues backReferences) { mValuesBackReferences = backReferences; return this; }
    public Builder withValueBackReference(String key, int previousResult) {
      if (mValuesBackReferences == null) mValuesBackReferences = new ContentValues();
      mValuesBackReferences.put(key, previousResult);
      return this;
    }
    public Builder withSelectionBackReference(int selectionArgIndex, int previousResult) {
      if (mSelectionArgsBackReferences == null) mSelectionArgsBackReferences = new HashMap<>();
      mSelectionArgsBackReferences.put(selectionArgIndex, previousResult);
      return this;
    }
    public Builder withValues(ContentValues values) { if (mValues == null) mValues = new ContentValues(); mValues.putAll(values); return this; }
    public Builder withValue(String key, Object value) {
      if (mValues == null) mValues = new ContentValues();
      mValues.putObject(key, value);
      return this;
    }
    public Builder withSelection(String selection, String[] selectionArgs) {
      mSelection = selection;
      mSelectionArgs = selectionArgs == null ? null : java.util.Arrays.copyOf(selectionArgs, selectionArgs.length);
      return this;
    }
    public Builder withExpectedCount(int count) { mExpectedCount = count; return this; }
    public Builder withYieldAllowed(boolean yieldAllowed) { mYieldAllowed = yieldAllowed; return this; }
  }
}

# android.database

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractCursor — framework; API 1+ (44 +, 0 *, 4 ~, 4 -)

```
+ AbstractCursor()  [API 1+]
+ checkPosition(): void  [API 1+]
+ close(): void  [API 1+]
+ copyStringToBuffer(int, CharArrayBuffer): void  [API 1+]
+ deactivate(): void  [API 1+]
+ fillWindow(int, CursorWindow): void  [API 1+]
+ finalize(): void  [API 1+]
~ getBlob(int): byte[]  [API 1+]
+ getColumnCount(): int  [API 1+]
+ getColumnIndex(String): int  [API 1+]
+ getColumnIndexOrThrow(String): int  [API 1+]
+ getColumnName(int): String  [API 1+]
+ getColumnNames(): String[]  [API 1+]
+ getCount(): int  [API 1+]
+ getDouble(int): double  [API 1+]
+ getExtras(): Bundle  [API 1+]
+ getFloat(int): float  [API 1+]
+ getInt(int): int  [API 1+]
+ getLong(int): long  [API 1+]
+ getNotificationUri(): Uri  [API 11+]
- getNotificationUris(): List  [API 1+]
+ getPosition(): int  [API 1+]
+ getShort(int): short  [API 1+]
+ getString(int): String  [API 1+]
~ getType(int): int  [API 1+]
- getUpdatedField(int): Object  [API 1+, deprecated in 15]
~ getWantsAllOnMoveCalls(): boolean  [API 1+]
~ getWindow(): CursorWindow  [API 1+]
+ isAfterLast(): boolean  [API 1+]
+ isBeforeFirst(): boolean  [API 1+]
+ isClosed(): boolean  [API 1+]
- isFieldUpdated(int): boolean  [API 1+, deprecated in 15]
+ isFirst(): boolean  [API 1+]
+ isLast(): boolean  [API 1+]
+ isNull(int): boolean  [API 1+]
+ mClosed [field]  [API 1+, deprecated in 23]
+ mContentResolver [field]  [API 1+, deprecated in 23]
+ mCurrentRowID [field]  [API 1-22, removed in 23, deprecated in 16]
+ mPos [field]  [API 1+, deprecated in 23]
+ mRowIdColumnIndex [field]  [API 1-22, removed in 23, deprecated in 16]
- mUpdatedRows [field]  [API 1-22, removed in 23, deprecated in 15]
+ move(int): boolean  [API 1+]
+ moveToFirst(): boolean  [API 1+]
+ moveToLast(): boolean  [API 1+]
+ moveToNext(): boolean  [API 1+]
+ moveToPosition(int): boolean  [API 1+]
+ moveToPrevious(): boolean  [API 1+]
+ onChange(boolean): void  [API 1+]
+ onMove(int, int): boolean  [API 1+]
+ registerContentObserver(ContentObserver): void  [API 1+]
+ registerDataSetObserver(DataSetObserver): void  [API 1+]
+ requery(): boolean  [API 1+]
+ respond(Bundle): Bundle  [API 1+]
+ setExtras(Bundle): void  [API 1+]
+ setNotificationUri(ContentResolver, Uri): void  [API 1+]
- setNotificationUris(ContentResolver, List): void  [API 1+]
+ unregisterContentObserver(ContentObserver): void  [API 1+]
+ unregisterDataSetObserver(DataSetObserver): void  [API 1+]
```

## AbstractCursor.SelfContentObserver — declaration; API 1+ (1 +, 0 *, 0 ~, 2 -)

```
- SelfContentObserver(AbstractCursor)  [API 1+]
- deliverSelfNotifications(): boolean  [API 1+]
+ onChange(boolean): void  [API 1+]
```

## AbstractWindowedCursor — framework; API 1+ (19 +, 0 *, 0 ~, 0 -)

```
+ AbstractWindowedCursor()  [API 1+]
+ checkPosition(): void  [API 1+]
+ copyStringToBuffer(int, CharArrayBuffer): void  [API 1+]
+ getBlob(int): byte[]  [API 1+]
+ getDouble(int): double  [API 1+]
+ getFloat(int): float  [API 1+]
+ getInt(int): int  [API 1+]
+ getLong(int): long  [API 1+]
+ getShort(int): short  [API 1+]
+ getString(int): String  [API 1+]
+ getType(int): int  [API 1+]
+ getWindow(): CursorWindow  [API 1+]
+ hasWindow(): boolean  [API 1+]
+ isBlob(int): boolean  [API 1+, deprecated in 15]
+ isFloat(int): boolean  [API 5+, deprecated in 15]
+ isLong(int): boolean  [API 5+, deprecated in 15]
+ isNull(int): boolean  [API 1+]
+ isString(int): boolean  [API 5+, deprecated in 15]
+ mWindow [field]  [API 1+]
+ setWindow(CursorWindow): void  [API 1+]
```

## CharArrayBuffer — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CharArrayBuffer(char[])  [API 1+]
+ CharArrayBuffer(int)  [API 1+]
+ data [field]  [API 1+]
+ sizeCopied [field]  [API 1+]
```

## ContentObservable — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ ContentObservable()  [API 1+]
+ dispatchChange(boolean): void  [API 1+, deprecated in 16]
+ dispatchChange(boolean, Uri): void  [API 16+]
+ notifyChange(boolean): void  [API 1+, deprecated in 16]
+ registerObserver(ContentObserver): void  [API 1+]
```

## ContentObserver — framework; API 1+ (6 +, 0 *, 1 ~, 3 -)

```
+ ContentObserver(Handler)  [API 1+]
~ deliverSelfNotifications(): boolean  [API 1+]
+ dispatchChange(boolean): void  [API 1+, deprecated in 16]
- dispatchChange(boolean, Collection, int): void  [API 30+]
+ dispatchChange(boolean, Uri): void  [API 16+]
- dispatchChange(boolean, Uri, int): void  [API 30+]
+ onChange(boolean): void  [API 1+]
- onChange(boolean, Collection, int): void  [API 30+]
+ onChange(boolean, Uri): void  [API 16+]
+ onChange(boolean, Uri, int): void  [API 30+]
```

## CrossProcessCursor — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ fillWindow(int, CursorWindow): void  [API 1+]
+ getWindow(): CursorWindow  [API 1+]
+ onMove(int, int): boolean  [API 1+]
```

## CrossProcessCursorWrapper — framework; API 15+ (4 +, 0 *, 0 ~, 0 -)

```
+ CrossProcessCursorWrapper(Cursor)  [API 15+]
+ fillWindow(int, CursorWindow): void  [API 15+]
+ getWindow(): CursorWindow  [API 15+]
+ onMove(int, int): boolean  [API 15+]
```

## Cursor — framework; API 1+ (41 +, 0 *, 0 ~, 2 -)

```
+ close(): void  [API 1+]
+ copyStringToBuffer(int, CharArrayBuffer): void  [API 1+]
+ deactivate(): void  [API 1+, deprecated in 16]
+ getBlob(int): byte[]  [API 1+]
+ getColumnCount(): int  [API 1+]
+ getColumnIndex(String): int  [API 1+]
+ getColumnIndexOrThrow(String): int  [API 1+]
+ getColumnName(int): String  [API 1+]
+ getColumnNames(): String[]  [API 1+]
+ getCount(): int  [API 1+]
+ getDouble(int): double  [API 1+]
+ getExtras(): Bundle  [API 1+]
+ getFloat(int): float  [API 1+]
+ getInt(int): int  [API 1+]
+ getLong(int): long  [API 1+]
+ getNotificationUri(): Uri  [API 19+]
- getNotificationUris(): List  [API 29+]
+ getPosition(): int  [API 1+]
+ getShort(int): short  [API 1+]
+ getString(int): String  [API 1+]
+ getType(int): int  [API 11+]
+ getWantsAllOnMoveCalls(): boolean  [API 1+]
+ isAfterLast(): boolean  [API 1+]
+ isBeforeFirst(): boolean  [API 1+]
+ isClosed(): boolean  [API 1+]
+ isFirst(): boolean  [API 1+]
+ isLast(): boolean  [API 1+]
+ isNull(int): boolean  [API 1+]
+ move(int): boolean  [API 1+]
+ moveToFirst(): boolean  [API 1+]
+ moveToLast(): boolean  [API 1+]
+ moveToNext(): boolean  [API 1+]
+ moveToPosition(int): boolean  [API 1+]
+ moveToPrevious(): boolean  [API 1+]
+ registerContentObserver(ContentObserver): void  [API 1+]
+ registerDataSetObserver(DataSetObserver): void  [API 1+]
+ requery(): boolean  [API 1+, deprecated in 15]
+ respond(Bundle): Bundle  [API 1+]
+ setExtras(Bundle): void  [API 23+]
+ setNotificationUri(ContentResolver, Uri): void  [API 1+]
- setNotificationUris(ContentResolver, List): void  [API 29+]
+ unregisterContentObserver(ContentObserver): void  [API 1+]
+ unregisterDataSetObserver(DataSetObserver): void  [API 1+]
```

## CursorIndexOutOfBoundsException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CursorIndexOutOfBoundsException(String)  [API 1+]
+ CursorIndexOutOfBoundsException(int, int)  [API 1+]
```

## CursorJoiner — framework; API 1+ (3 +, 0 *, 2 ~, 0 -)

```
+ CursorJoiner(Cursor, String[], Cursor, String[])  [API 1+]
+ hasNext(): boolean  [API 1+]
~ iterator(): Iterator  [API 1+]
+ next(): CursorJoiner.Result  [API 1+]
~ remove(): void  [API 1+]
```

## CursorJoiner.Result — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ BOTH [field]  [API 1+]
+ LEFT [field]  [API 1+]
+ RIGHT [field]  [API 1+]
+ valueOf(String): CursorJoiner.Result [static]  [API 1+]
+ values(): CursorJoiner.Result[] [static]  [API 1+]
```

## CursorWindow — framework; API 1+ (26 +, 0 *, 1 ~, 9 -)

```
- CREATOR [field]  [API 1+]
+ CursorWindow(String)  [API 15+]
+ CursorWindow(String, long)  [API 28+]
+ CursorWindow(boolean)  [API 1+, deprecated in 15]
+ allocRow(): boolean  [API 1+]
+ clear(): void  [API 1+]
~ close(): void  [API 1-15, removed in 16]
+ copyStringToBuffer(int, int, CharArrayBuffer): void  [API 1+]
- describeContents(): int  [API 1+]
+ finalize(): void  [API 1+]
+ freeLastRow(): void  [API 1+]
+ getBlob(int, int): byte[]  [API 1+]
+ getDouble(int, int): double  [API 1+]
+ getFloat(int, int): float  [API 1+]
+ getInt(int, int): int  [API 1+]
+ getLong(int, int): long  [API 1+]
+ getNumRows(): int  [API 1+]
+ getShort(int, int): short  [API 1+]
+ getStartPosition(): int  [API 1+]
+ getString(int, int): String  [API 1+]
+ getType(int, int): int  [API 11+]
- isBlob(int, int): boolean  [API 1+, deprecated in 15]
- isFloat(int, int): boolean  [API 5+, deprecated in 15]
- isLong(int, int): boolean  [API 5+, deprecated in 15]
- isNull(int, int): boolean  [API 1+, deprecated in 15]
- isString(int, int): boolean  [API 5+, deprecated in 15]
- newFromParcel(Parcel): CursorWindow [static]  [API 1+]
- onAllReferencesReleased(): void  [API 1+]
+ putBlob(byte[], int, int): boolean  [API 1+]
+ putDouble(double, int, int): boolean  [API 1+]
+ putLong(long, int, int): boolean  [API 1+]
+ putNull(int, int): boolean  [API 1+]
+ putString(String, int, int): boolean  [API 1+]
+ setNumColumns(int): boolean  [API 1+]
+ setStartPosition(int): void  [API 1+]
+ toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## CursorWindowAllocationException — framework; API 33+ (1 +, 0 *, 0 ~, 0 -)

```
+ CursorWindowAllocationException(String)  [API 33+]
```

## CursorWrapper — framework; API 1+ (43 +, 0 *, 0 ~, 2 -)

```
+ CursorWrapper(Cursor)  [API 1+]
+ close(): void  [API 1+]
+ copyStringToBuffer(int, CharArrayBuffer): void  [API 1+]
+ deactivate(): void  [API 1+]
+ getBlob(int): byte[]  [API 1+]
+ getColumnCount(): int  [API 1+]
+ getColumnIndex(String): int  [API 1+]
+ getColumnIndexOrThrow(String): int  [API 1+]
+ getColumnName(int): String  [API 1+]
+ getColumnNames(): String[]  [API 1+]
+ getCount(): int  [API 1+]
+ getDouble(int): double  [API 1+]
+ getExtras(): Bundle  [API 1+]
+ getFloat(int): float  [API 1+]
+ getInt(int): int  [API 1+]
+ getLong(int): long  [API 1+]
+ getNotificationUri(): Uri  [API 1+]
- getNotificationUris(): List  [API 1+]
+ getPosition(): int  [API 1+]
+ getShort(int): short  [API 1+]
+ getString(int): String  [API 1+]
+ getType(int): int  [API 1+]
+ getWantsAllOnMoveCalls(): boolean  [API 1+]
+ getWrappedCursor(): Cursor  [API 11+]
+ isAfterLast(): boolean  [API 1+]
+ isBeforeFirst(): boolean  [API 1+]
+ isClosed(): boolean  [API 1+]
+ isFirst(): boolean  [API 1+]
+ isLast(): boolean  [API 1+]
+ isNull(int): boolean  [API 1+]
+ move(int): boolean  [API 1+]
+ moveToFirst(): boolean  [API 1+]
+ moveToLast(): boolean  [API 1+]
+ moveToNext(): boolean  [API 1+]
+ moveToPosition(int): boolean  [API 1+]
+ moveToPrevious(): boolean  [API 1+]
+ registerContentObserver(ContentObserver): void  [API 1+]
+ registerDataSetObserver(DataSetObserver): void  [API 1+]
+ requery(): boolean  [API 1+]
+ respond(Bundle): Bundle  [API 1+]
+ setExtras(Bundle): void  [API 1+]
+ setNotificationUri(ContentResolver, Uri): void  [API 1+]
- setNotificationUris(ContentResolver, List): void  [API 1+]
+ unregisterContentObserver(ContentObserver): void  [API 1+]
+ unregisterDataSetObserver(DataSetObserver): void  [API 1+]
```

## DataSetObservable — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ DataSetObservable()  [API 1+]
+ notifyChanged(): void  [API 1+]
+ notifyInvalidated(): void  [API 1+]
```

## DataSetObserver — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ DataSetObserver()  [API 1+]
+ onChanged(): void  [API 1+]
+ onInvalidated(): void  [API 1+]
```

## DatabaseErrorHandler — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCorruption(SQLiteDatabase): void  [API 11+]
```

## DatabaseUtils — framework; API 1+ (34 +, 0 *, 0 ~, 14 -)

```
+ DatabaseUtils()  [API 1+]
+ appendEscapedSQLString(StringBuilder, String): void [static]  [API 1+]
+ appendSelectionArgs(String[], String[]): String[] [static]  [API 11+]
+ appendValueToSql(StringBuilder, Object): void [static]  [API 1+]
- bindObjectToProgram(SQLiteProgram, int, Object): void [static]  [API 1+]
- blobFileDescriptorForQuery(SQLiteDatabase, String, String[]): ParcelFileDescriptor [static]  [API 11+]
- blobFileDescriptorForQuery(SQLiteStatement, String[]): ParcelFileDescriptor [static]  [API 11+]
+ concatenateWhere(String, String): String [static]  [API 11+]
+ createDbFromSqlStatements(Context, String, int, String): void [static]  [API 1+]
+ cursorDoubleToContentValues(Cursor, String, ContentValues, String): void [static]  [API 1+]
- cursorDoubleToContentValuesIfPresent(Cursor, ContentValues, String): void [static]  [API 8+]
+ cursorDoubleToCursorValues(Cursor, String, ContentValues): void [static]  [API 1+]
- cursorFloatToContentValuesIfPresent(Cursor, ContentValues, String): void [static]  [API 8+]
+ cursorIntToContentValues(Cursor, String, ContentValues): void [static]  [API 1+]
+ cursorIntToContentValues(Cursor, String, ContentValues, String): void [static]  [API 1+]
- cursorIntToContentValuesIfPresent(Cursor, ContentValues, String): void [static]  [API 8+]
+ cursorLongToContentValues(Cursor, String, ContentValues): void [static]  [API 1+]
+ cursorLongToContentValues(Cursor, String, ContentValues, String): void [static]  [API 1+]
- cursorLongToContentValuesIfPresent(Cursor, ContentValues, String): void [static]  [API 8+]
+ cursorRowToContentValues(Cursor, ContentValues): void [static]  [API 1+]
- cursorShortToContentValuesIfPresent(Cursor, ContentValues, String): void [static]  [API 8+]
+ cursorStringToContentValues(Cursor, String, ContentValues): void [static]  [API 1+]
+ cursorStringToContentValues(Cursor, String, ContentValues, String): void [static]  [API 1+]
- cursorStringToContentValuesIfPresent(Cursor, ContentValues, String): void [static]  [API 8+]
- cursorStringToInsertHelper(Cursor, String, DatabaseUtils.InsertHelper, int): void [static]  [API 1+]
+ dumpCurrentRow(Cursor): void [static]  [API 1+]
+ dumpCurrentRow(Cursor, PrintStream): void [static]  [API 1+]
+ dumpCurrentRow(Cursor, StringBuilder): void [static]  [API 1+]
+ dumpCurrentRowToString(Cursor): String [static]  [API 1+]
+ dumpCursor(Cursor): void [static]  [API 1+]
+ dumpCursor(Cursor, PrintStream): void [static]  [API 1+]
+ dumpCursor(Cursor, StringBuilder): void [static]  [API 1+]
+ dumpCursorToString(Cursor): String [static]  [API 1+]
+ getCollationKey(String): String [static]  [API 1+]
+ getHexCollationKey(String): String [static]  [API 1+]
+ getSqlStatementType(String): int [static]  [API 11+]
+ longForQuery(SQLiteDatabase, String, String[]): long [static]  [API 1+]
+ longForQuery(SQLiteStatement, String[]): long [static]  [API 1+]
+ queryNumEntries(SQLiteDatabase, String): long [static]  [API 1+]
+ queryNumEntries(SQLiteDatabase, String, String): long [static]  [API 11+]
+ queryNumEntries(SQLiteDatabase, String, String, String[]): long [static]  [API 11+]
- readExceptionFromParcel(Parcel): void [static]  [API 1+]
- readExceptionWithFileNotFoundExceptionFromParcel(Parcel): void [static]  [API 1+]
- readExceptionWithOperationApplicationExceptionFromParcel(Parcel): void [static]  [API 5+]
+ sqlEscapeString(String): String [static]  [API 1+]
+ stringForQuery(SQLiteDatabase, String, String[]): String [static]  [API 1+]
+ stringForQuery(SQLiteStatement, String[]): String [static]  [API 1+]
- writeExceptionToParcel(Parcel, Exception): void [static]  [API 1+]
```

## DatabaseUtils.InsertHelper — declaration; API 1+, deprecated in 17 (0 +, 0 *, 0 ~, 16 -)

```
- InsertHelper(SQLiteDatabase, String)  [API 1+]
- bind(int, String): void  [API 1+]
- bind(int, boolean): void  [API 1+]
- bind(int, byte[]): void  [API 1+]
- bind(int, double): void  [API 1+]
- bind(int, float): void  [API 1+]
- bind(int, int): void  [API 1+]
- bind(int, long): void  [API 1+]
- bindNull(int): void  [API 1+]
- close(): void  [API 1+]
- execute(): long  [API 1+]
- getColumnIndex(String): int  [API 1+]
- insert(ContentValues): long  [API 1+]
- prepareForInsert(): void  [API 1+]
- prepareForReplace(): void  [API 1+]
- replace(ContentValues): long  [API 1+]
```

## DefaultDatabaseErrorHandler — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ DefaultDatabaseErrorHandler()  [API 11+]
+ onCorruption(SQLiteDatabase): void  [API 11+]
```

## MatrixCursor — framework; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ MatrixCursor(String[])  [API 1+]
+ MatrixCursor(String[], int)  [API 1+]
+ addRow(Iterable): void  [API 1+]
+ addRow(Object[]): void  [API 1+]
+ getBlob(int): byte[]  [API 1+]
+ getColumnNames(): String[]  [API 1+]
+ getCount(): int  [API 1+]
+ getDouble(int): double  [API 1+]
+ getFloat(int): float  [API 1+]
+ getInt(int): int  [API 1+]
+ getLong(int): long  [API 1+]
+ getShort(int): short  [API 1+]
+ getString(int): String  [API 1+]
+ getType(int): int  [API 1+]
+ isNull(int): boolean  [API 1+]
+ newRow(): MatrixCursor.RowBuilder  [API 1+]
```

## MatrixCursor.RowBuilder — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ add(Object): MatrixCursor.RowBuilder  [API 1+]
+ add(String, Object): MatrixCursor.RowBuilder  [API 19+]
```

## MergeCursor — framework; API 1+ (20 +, 0 *, 0 ~, 0 -)

```
+ MergeCursor(Cursor[])  [API 1+]
+ close(): void  [API 1+]
+ deactivate(): void  [API 1+]
+ getBlob(int): byte[]  [API 1+]
+ getColumnNames(): String[]  [API 1+]
+ getCount(): int  [API 1+]
+ getDouble(int): double  [API 1+]
+ getFloat(int): float  [API 1+]
+ getInt(int): int  [API 1+]
+ getLong(int): long  [API 1+]
+ getShort(int): short  [API 1+]
+ getString(int): String  [API 1+]
+ getType(int): int  [API 1+]
+ isNull(int): boolean  [API 1+]
+ onMove(int, int): boolean  [API 1+]
+ registerContentObserver(ContentObserver): void  [API 1+]
+ registerDataSetObserver(DataSetObserver): void  [API 1+]
+ requery(): boolean  [API 1+]
+ unregisterContentObserver(ContentObserver): void  [API 1+]
+ unregisterDataSetObserver(DataSetObserver): void  [API 1+]
```

## Observable — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ Observable()  [API 1+]
+ mObservers [field]  [API 1+]
+ registerObserver(Object): void  [API 1+]
+ unregisterAll(): void  [API 1+]
+ unregisterObserver(Object): void  [API 1+]
```

## SQLException — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ SQLException()  [API 1+]
+ SQLException(String)  [API 1+]
+ SQLException(String, Throwable)  [API 16+]
```

## StaleDataException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ StaleDataException()  [API 1+]
+ StaleDataException(String)  [API 1+]
```


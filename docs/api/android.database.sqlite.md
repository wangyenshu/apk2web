# android.database.sqlite

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## SQLiteAbortException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteAbortException()  [API 1+]
+ SQLiteAbortException(String)  [API 1+]
```

## SQLiteAccessPermException — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteAccessPermException()  [API 11+]
+ SQLiteAccessPermException(String)  [API 11+]
```

## SQLiteBindOrColumnIndexOutOfRangeException — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteBindOrColumnIndexOutOfRangeException()  [API 11+]
+ SQLiteBindOrColumnIndexOutOfRangeException(String)  [API 11+]
```

## SQLiteBlobTooBigException — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteBlobTooBigException()  [API 11+]
+ SQLiteBlobTooBigException(String)  [API 11+]
```

## SQLiteCantOpenDatabaseException — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteCantOpenDatabaseException()  [API 11+]
+ SQLiteCantOpenDatabaseException(String)  [API 11+]
```

## SQLiteClosable — framework; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ SQLiteClosable()  [API 1+]
+ acquireReference(): void  [API 1+]
+ close(): void  [API 1+]
+ onAllReferencesReleased(): void  [API 1+]
+ onAllReferencesReleasedFromContainer(): void  [API 1+, deprecated in 16]
+ releaseReference(): void  [API 1+]
+ releaseReferenceFromContainer(): void  [API 1+, deprecated in 16]
```

## SQLiteConstraintException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteConstraintException()  [API 1+]
+ SQLiteConstraintException(String)  [API 1+]
```

## SQLiteCursor — framework; API 1+ (13 +, 0 *, 1 ~, 0 -)

```
+ SQLiteCursor(SQLiteCursorDriver, String, SQLiteQuery)  [API 11+]
+ SQLiteCursor(SQLiteDatabase, SQLiteCursorDriver, String, SQLiteQuery)  [API 1+, deprecated in 15]
+ close(): void  [API 1+]
+ deactivate(): void  [API 1+]
+ finalize(): void  [API 1+]
+ getColumnIndex(String): int  [API 1+]
+ getColumnNames(): String[]  [API 1+]
+ getCount(): int  [API 1+]
+ getDatabase(): SQLiteDatabase  [API 1+]
+ onMove(int, int): boolean  [API 1+]
+ requery(): boolean  [API 1+]
~ setFillWindowForwardOnly(boolean): void  [API 28+]
+ setSelectionArguments(String[]): void  [API 1+]
+ setWindow(CursorWindow): void  [API 1+]
```

## SQLiteCursorDriver — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ cursorClosed(): void  [API 1+]
+ cursorDeactivated(): void  [API 1+]
+ cursorRequeried(Cursor): void  [API 1+]
+ query(SQLiteDatabase.CursorFactory, String[]): Cursor  [API 1+]
+ setBindArguments(String[]): void  [API 1+]
```

## SQLiteDatabase — framework; API 1+ (60 +, 0 *, 14 ~, 6 -)

```
+ beginTransaction(): void  [API 1+]
+ beginTransactionNonExclusive(): void  [API 11+]
+ beginTransactionReadOnly(): void  [API 35+]
+ beginTransactionWithListener(SQLiteTransactionListener): void  [API 5+]
+ beginTransactionWithListenerNonExclusive(SQLiteTransactionListener): void  [API 11+]
+ beginTransactionWithListenerReadOnly(SQLiteTransactionListener): void  [API 35+]
+ close(): void  [API 1-15, removed in 16]
+ compileStatement(String): SQLiteStatement  [API 1+]
+ create(SQLiteDatabase.CursorFactory): SQLiteDatabase [static]  [API 1+]
+ createInMemory(SQLiteDatabase.OpenParams): SQLiteDatabase [static]  [API 27+]
- createRawStatement(String): SQLiteRawStatement  [API 35+]
+ delete(String, String, String[]): int  [API 1+]
+ deleteDatabase(File): boolean [static]  [API 16+]
~ disableWriteAheadLogging(): void  [API 16+]
~ enableWriteAheadLogging(): boolean  [API 11+]
+ endTransaction(): void  [API 1+]
+ execPerConnectionSQL(String, Object[]): void  [API 30+]
+ execSQL(String): void  [API 1+]
+ execSQL(String, Object[]): void  [API 1+]
+ finalize(): void  [API 1+]
+ findEditTable(String): String [static]  [API 1+]
+ getAttachedDbs(): List  [API 11+]
- getLastChangedRowCount(): long  [API 35+]
- getLastInsertRowId(): long  [API 35+]
+ getMaximumSize(): long  [API 1+]
+ getPageSize(): long  [API 1+]
+ getPath(): String  [API 1+]
+ getSyncedTables(): Map  [API 1+, deprecated in 15]
- getTotalChangedRowCount(): long  [API 35+]
+ getVersion(): int  [API 1+]
+ inTransaction(): boolean  [API 1+]
+ insert(String, String, ContentValues): long  [API 1+]
+ insertOrThrow(String, String, ContentValues): long  [API 1+]
+ insertWithOnConflict(String, String, ContentValues, int): long  [API 8+]
+ isDatabaseIntegrityOk(): boolean  [API 11+]
+ isDbLockedByCurrentThread(): boolean  [API 1+]
~ isDbLockedByOtherThreads(): boolean  [API 1+, deprecated in 16]
+ isOpen(): boolean  [API 1+]
+ isReadOnly(): boolean  [API 1+]
~ isWriteAheadLoggingEnabled(): boolean  [API 16+]
~ markTableSyncable(String, String): void  [API 1+, deprecated in 15]
~ markTableSyncable(String, String, String): void  [API 1+, deprecated in 15]
+ needUpgrade(int): boolean  [API 1+]
+ onAllReferencesReleased(): void  [API 1+]
+ openDatabase(File, SQLiteDatabase.OpenParams): SQLiteDatabase [static]  [API 27+]
+ openDatabase(String, SQLiteDatabase.CursorFactory, int): SQLiteDatabase [static]  [API 1+]
+ openDatabase(String, SQLiteDatabase.CursorFactory, int, DatabaseErrorHandler): SQLiteDatabase [static]  [API 11+]
+ openOrCreateDatabase(File, SQLiteDatabase.CursorFactory): SQLiteDatabase [static]  [API 1+]
+ openOrCreateDatabase(String, SQLiteDatabase.CursorFactory): SQLiteDatabase [static]  [API 1+]
+ openOrCreateDatabase(String, SQLiteDatabase.CursorFactory, DatabaseErrorHandler): SQLiteDatabase [static]  [API 11+]
+ query(String, String[], String, String[], String, String, String): Cursor  [API 1+]
+ query(String, String[], String, String[], String, String, String, String): Cursor  [API 1+]
+ query(boolean, String, String[], String, String[], String, String, String, String): Cursor  [API 1+]
+ query(boolean, String, String[], String, String[], String, String, String, String, CancellationSignal): Cursor  [API 16+]
+ queryWithFactory(SQLiteDatabase.CursorFactory, boolean, String, String[], String, String[], String, String, String, String): Cursor  [API 1+]
+ queryWithFactory(SQLiteDatabase.CursorFactory, boolean, String, String[], String, String[], String, String, String, String, CancellationSignal): Cursor  [API 16+]
+ rawQuery(String, String[]): Cursor  [API 1+]
+ rawQuery(String, String[], CancellationSignal): Cursor  [API 16+]
+ rawQueryWithFactory(SQLiteDatabase.CursorFactory, String, String[], String): Cursor  [API 1+]
+ rawQueryWithFactory(SQLiteDatabase.CursorFactory, String, String[], String, CancellationSignal): Cursor  [API 16+]
~ releaseMemory(): int [static]  [API 1+]
+ replace(String, String, ContentValues): long  [API 1+]
+ replaceOrThrow(String, String, ContentValues): long  [API 1+]
- setCustomAggregateFunction(String, BinaryOperator): void  [API 30+]
- setCustomScalarFunction(String, UnaryOperator): void  [API 30+]
+ setForeignKeyConstraintsEnabled(boolean): void  [API 16+]
~ setLocale(Locale): void  [API 1+]
~ setLockingEnabled(boolean): void  [API 1+, deprecated in 16]
~ setMaxSqlCacheSize(int): void  [API 11+]
+ setMaximumSize(long): long  [API 1+]
+ setPageSize(long): void  [API 1+]
+ setTransactionSuccessful(): void  [API 1+]
+ setVersion(int): void  [API 1+]
+ toString(): String  [API 1+]
+ update(String, ContentValues, String, String[]): int  [API 1+]
+ updateWithOnConflict(String, ContentValues, String, String[], int): int  [API 8+]
~ validateSql(String, CancellationSignal): void  [API 24+]
~ yieldIfContended(): boolean  [API 1+, deprecated in 15]
~ yieldIfContendedSafely(): boolean  [API 3+]
~ yieldIfContendedSafely(long): boolean  [API 5+]
```

## SQLiteDatabase.CursorFactory — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ newCursor(SQLiteDatabase, SQLiteCursorDriver, String, SQLiteQuery): Cursor  [API 1+]
```

## SQLiteDatabase.OpenParams — framework; API 27+ (3 +, 0 *, 0 ~, 5 -)

```
+ getCursorFactory(): SQLiteDatabase.CursorFactory  [API 27+]
+ getErrorHandler(): DatabaseErrorHandler  [API 27+]
- getIdleConnectionTimeout(): long  [API 27+]
- getJournalMode(): String  [API 28+]
- getLookasideSlotCount(): int  [API 27+]
- getLookasideSlotSize(): int  [API 27+]
+ getOpenFlags(): int  [API 27+]
- getSynchronousMode(): String  [API 28+]
```

## SQLiteDatabase.OpenParams.Builder — framework; API 27+ (8 +, 0 *, 4 ~, 0 -)

```
+ Builder()  [API 27+]
+ Builder(SQLiteDatabase.OpenParams)  [API 27+]
+ addOpenFlags(int): SQLiteDatabase.OpenParams.Builder  [API 27+]
+ build(): SQLiteDatabase.OpenParams  [API 27+]
+ removeOpenFlags(int): SQLiteDatabase.OpenParams.Builder  [API 27+]
+ setCursorFactory(SQLiteDatabase.CursorFactory): SQLiteDatabase.OpenParams.Builder  [API 27+]
+ setErrorHandler(DatabaseErrorHandler): SQLiteDatabase.OpenParams.Builder  [API 27+]
~ setIdleConnectionTimeout(long): SQLiteDatabase.OpenParams.Builder  [API 27+, deprecated in 29]
~ setJournalMode(String): SQLiteDatabase.OpenParams.Builder  [API 28+]
~ setLookasideConfig(int, int): SQLiteDatabase.OpenParams.Builder  [API 27+]
+ setOpenFlags(int): SQLiteDatabase.OpenParams.Builder  [API 27+]
~ setSynchronousMode(String): SQLiteDatabase.OpenParams.Builder  [API 28+]
```

## SQLiteDatabaseCorruptException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteDatabaseCorruptException()  [API 1+]
+ SQLiteDatabaseCorruptException(String)  [API 1+]
```

## SQLiteDatabaseLockedException — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteDatabaseLockedException()  [API 11+]
+ SQLiteDatabaseLockedException(String)  [API 11+]
```

## SQLiteDatatypeMismatchException — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteDatatypeMismatchException()  [API 11+]
+ SQLiteDatatypeMismatchException(String)  [API 11+]
```

## SQLiteDiskIOException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteDiskIOException()  [API 1+]
+ SQLiteDiskIOException(String)  [API 1+]
```

## SQLiteDoneException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteDoneException()  [API 1+]
+ SQLiteDoneException(String)  [API 1+]
```

## SQLiteException — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ SQLiteException()  [API 1+]
+ SQLiteException(String)  [API 1+]
+ SQLiteException(String, Throwable)  [API 16+]
```

## SQLiteFullException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteFullException()  [API 1+]
+ SQLiteFullException(String)  [API 1+]
```

## SQLiteMisuseException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteMisuseException()  [API 1+]
+ SQLiteMisuseException(String)  [API 1+]
```

## SQLiteOpenHelper — framework; API 1+ (12 +, 0 *, 4 ~, 0 -)

```
+ SQLiteOpenHelper(Context, String, SQLiteDatabase.CursorFactory, int)  [API 1+]
+ SQLiteOpenHelper(Context, String, SQLiteDatabase.CursorFactory, int, DatabaseErrorHandler)  [API 11+]
+ SQLiteOpenHelper(Context, String, int, SQLiteDatabase.OpenParams)  [API 28+]
+ close(): void  [API 1+]
+ getDatabaseName(): String  [API 14+]
+ getReadableDatabase(): SQLiteDatabase  [API 1+]
+ getWritableDatabase(): SQLiteDatabase  [API 1+]
+ onConfigure(SQLiteDatabase): void  [API 16+]
+ onCreate(SQLiteDatabase): void  [API 1+]
+ onDowngrade(SQLiteDatabase, int, int): void  [API 11+]
+ onOpen(SQLiteDatabase): void  [API 1+]
+ onUpgrade(SQLiteDatabase, int, int): void  [API 1+]
~ setIdleConnectionTimeout(long): void  [API 27+, deprecated in 29]
~ setLookasideConfig(int, int): void  [API 27+]
~ setOpenParams(SQLiteDatabase.OpenParams): void  [API 28+]
~ setWriteAheadLoggingEnabled(boolean): void  [API 16+]
```

## SQLiteOutOfMemoryException — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteOutOfMemoryException()  [API 11+]
+ SQLiteOutOfMemoryException(String)  [API 11+]
```

## SQLiteProgram — framework; API 1+ (9 +, 0 *, 1 ~, 8 -)

```
+ bindAllArgsAsStrings(String[]): void  [API 11+]
+ bindBlob(int, byte[]): void  [API 1+]
+ bindDouble(int, double): void  [API 1+]
+ bindLong(int, long): void  [API 1+]
+ bindNull(int): void  [API 1+]
+ bindString(int, String): void  [API 1+]
+ clearBindings(): void  [API 1+]
+ close(): void  [API 1-15, removed in 16]
- compile(String, boolean): void  [API 1-15, removed in 16, deprecated in 15]
~ getUniqueId(): int  [API 1+, deprecated in 15]
+ mDatabase [field]  [API 1-15, removed in 16, deprecated in 15]
- nHandle [field]  [API 1-15, removed in 16, deprecated in 15]
- nStatement [field]  [API 1-15, removed in 16, deprecated in 15]
- native_bind_blob(int, byte[]): void  [API 1-15, removed in 16]
- native_bind_double(int, double): void  [API 1-15, removed in 16]
- native_bind_long(int, long): void  [API 1-15, removed in 16]
- native_bind_null(int): void  [API 1-15, removed in 16]
- native_bind_string(int, String): void  [API 1-15, removed in 16]
- native_compile(String): void  [API 1-15, removed in 16, deprecated in 15]
- native_finalize(): void  [API 1-15, removed in 16, deprecated in 15]
+ onAllReferencesReleased(): void  [API 1+]
```

## SQLiteQuery — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ toString(): String  [API 1+]
```

## SQLiteQueryBuilder — framework; API 1+ (27 +, 0 *, 2 ~, 4 -)

```
+ SQLiteQueryBuilder()  [API 1+]
+ appendColumns(StringBuilder, String[]): void [static]  [API 1+]
+ appendWhere(CharSequence): void  [API 1+]
+ appendWhereEscapeString(String): void  [API 1+]
+ appendWhereStandalone(CharSequence): void  [API 29+]
+ buildQuery(String[], String, String, String, String, String): String  [API 11+]
+ buildQuery(String[], String, String[], String, String, String, String): String  [API 1+, deprecated in 15]
+ buildQueryString(boolean, String, String[], String, String, String, String, String): String [static]  [API 1+]
+ buildUnionQuery(String[], String, String): String  [API 1+]
+ buildUnionSubQuery(String, String[], Set, int, String, String, String, String): String  [API 11+]
+ buildUnionSubQuery(String, String[], Set, int, String, String, String[], String, String): String  [API 1+, deprecated in 15]
+ delete(SQLiteDatabase, String, String[]): int  [API 29+]
+ getCursorFactory(): SQLiteDatabase.CursorFactory  [API 29+]
- getProjectionGreylist(): Collection  [API 30+]
+ getProjectionMap(): Map  [API 29+]
+ getTables(): String  [API 1+]
+ insert(SQLiteDatabase, ContentValues): long  [API 30+]
+ isDistinct(): boolean  [API 29+]
+ isStrict(): boolean  [API 29+]
- isStrictColumns(): boolean  [API 30+]
- isStrictGrammar(): boolean  [API 30+]
+ query(SQLiteDatabase, String[], String, String[], String, String, String): Cursor  [API 1+]
+ query(SQLiteDatabase, String[], String, String[], String, String, String, String): Cursor  [API 1+]
+ query(SQLiteDatabase, String[], String, String[], String, String, String, String, CancellationSignal): Cursor  [API 16+]
+ setCursorFactory(SQLiteDatabase.CursorFactory): void  [API 1+]
+ setDistinct(boolean): void  [API 1+]
- setProjectionGreylist(Collection): void  [API 30+]
+ setProjectionMap(Map): void  [API 1+]
+ setStrict(boolean): void  [API 14+]
~ setStrictColumns(boolean): void  [API 30+]
~ setStrictGrammar(boolean): void  [API 30+]
+ setTables(String): void  [API 1+]
+ update(SQLiteDatabase, ContentValues, String, String[]): int  [API 29+]
```

## SQLiteRawStatement — declaration; API 35+ (0 +, 0 *, 0 ~, 26 -)

```
- bindBlob(int, byte[]): void  [API 35+]
- bindBlob(int, byte[], int, int): void  [API 35+]
- bindDouble(int, double): void  [API 35+]
- bindInt(int, int): void  [API 35+]
- bindLong(int, long): void  [API 35+]
- bindNull(int): void  [API 35+]
- bindText(int, String): void  [API 35+]
- clearBindings(): void  [API 35+]
- close(): void  [API 35+]
- getColumnBlob(int): byte[]  [API 35+]
- getColumnDouble(int): double  [API 35+]
- getColumnInt(int): int  [API 35+]
- getColumnLength(int): int  [API 35+]
- getColumnLong(int): long  [API 35+]
- getColumnName(int): String  [API 35+]
- getColumnText(int): String  [API 35+]
- getColumnType(int): int  [API 35+]
- getParameterCount(): int  [API 35+]
- getParameterIndex(String): int  [API 35+]
- getParameterName(int): String  [API 35+]
- getResultColumnCount(): int  [API 35+]
- isOpen(): boolean  [API 35+]
- readColumnBlob(int, byte[], int, int, int): int  [API 35+]
- reset(): void  [API 35+]
- step(): boolean  [API 35+]
- toString(): String  [API 35+]
```

## SQLiteReadOnlyDatabaseException — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteReadOnlyDatabaseException()  [API 11+]
+ SQLiteReadOnlyDatabaseException(String)  [API 11+]
```

## SQLiteStatement — framework; API 1+ (6 +, 0 *, 1 ~, 0 -)

```
+ execute(): void  [API 1+]
+ executeInsert(): long  [API 1+]
+ executeUpdateDelete(): int  [API 11+]
~ simpleQueryForBlobFileDescriptor(): ParcelFileDescriptor  [API 11+]
+ simpleQueryForLong(): long  [API 1+]
+ simpleQueryForString(): String  [API 1+]
+ toString(): String  [API 1+]
```

## SQLiteTableLockedException — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ SQLiteTableLockedException()  [API 11+]
+ SQLiteTableLockedException(String)  [API 11+]
```

## SQLiteTransactionListener — framework; API 5+ (3 +, 0 *, 0 ~, 0 -)

```
+ onBegin(): void  [API 5+]
+ onCommit(): void  [API 5+]
+ onRollback(): void  [API 5+]
```


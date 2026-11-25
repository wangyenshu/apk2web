# javax.sql

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CommonDataSource — declaration; API 9+ (5 +, 0 *, 0 ~, 0 -)

```
+ getLogWriter(): PrintWriter  [API 9+]
+ getLoginTimeout(): int  [API 9+]
+ getParentLogger(): Logger  [API 24+]
+ setLogWriter(PrintWriter): void  [API 9+]
+ setLoginTimeout(int): void  [API 9+]
```

## ConnectionEvent — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- ConnectionEvent(PooledConnection)  [API 1+]
- ConnectionEvent(PooledConnection, SQLException)  [API 1+]
- getSQLException(): SQLException  [API 1+]
```

## ConnectionEventListener — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ connectionClosed(ConnectionEvent): void  [API 1+]
+ connectionErrorOccurred(ConnectionEvent): void  [API 1+]
```

## ConnectionPoolDataSource — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ getLogWriter(): PrintWriter  [API 1-8, removed in 9]
+ getLoginTimeout(): int  [API 1-8, removed in 9]
+ getPooledConnection(): PooledConnection  [API 1+]
+ getPooledConnection(String, String): PooledConnection  [API 1+]
+ setLogWriter(PrintWriter): void  [API 1-8, removed in 9]
+ setLoginTimeout(int): void  [API 1-8, removed in 9]
```

## DataSource — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ getConnection(): Connection  [API 1+]
+ getConnection(String, String): Connection  [API 1+]
+ getLogWriter(): PrintWriter  [API 1-8, removed in 9]
+ getLoginTimeout(): int  [API 1-8, removed in 9]
+ setLogWriter(PrintWriter): void  [API 1-8, removed in 9]
+ setLoginTimeout(int): void  [API 1-8, removed in 9]
```

## PooledConnection — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ addConnectionEventListener(ConnectionEventListener): void  [API 1+]
+ addStatementEventListener(StatementEventListener): void  [API 9+]
+ close(): void  [API 1+]
+ getConnection(): Connection  [API 1+]
+ removeConnectionEventListener(ConnectionEventListener): void  [API 1+]
+ removeStatementEventListener(StatementEventListener): void  [API 9+]
```

## RowSet — declaration; API 1+ (115 +, 0 *, 0 ~, 0 -)

```
+ addRowSetListener(RowSetListener): void  [API 1+]
+ clearParameters(): void  [API 1+]
+ execute(): void  [API 1+]
+ getCommand(): String  [API 1+]
+ getDataSourceName(): String  [API 1+]
+ getEscapeProcessing(): boolean  [API 1+]
+ getMaxFieldSize(): int  [API 1+]
+ getMaxRows(): int  [API 1+]
+ getPassword(): String  [API 1+]
+ getQueryTimeout(): int  [API 1+]
+ getTransactionIsolation(): int  [API 1+]
+ getTypeMap(): Map  [API 1+]
+ getUrl(): String  [API 1+]
+ getUsername(): String  [API 1+]
+ isReadOnly(): boolean  [API 1+]
+ removeRowSetListener(RowSetListener): void  [API 1+]
+ setArray(int, Array): void  [API 1+]
+ setAsciiStream(String, InputStream): void  [API 9+]
+ setAsciiStream(String, InputStream, int): void  [API 9+]
+ setAsciiStream(int, InputStream): void  [API 9+]
+ setAsciiStream(int, InputStream, int): void  [API 1+]
+ setBigDecimal(String, BigDecimal): void  [API 9+]
+ setBigDecimal(int, BigDecimal): void  [API 1+]
+ setBinaryStream(String, InputStream): void  [API 9+]
+ setBinaryStream(String, InputStream, int): void  [API 9+]
+ setBinaryStream(int, InputStream): void  [API 9+]
+ setBinaryStream(int, InputStream, int): void  [API 1+]
+ setBlob(String, Blob): void  [API 9+]
+ setBlob(String, InputStream): void  [API 9+]
+ setBlob(String, InputStream, long): void  [API 9+]
+ setBlob(int, Blob): void  [API 1+]
+ setBlob(int, InputStream): void  [API 9+]
+ setBlob(int, InputStream, long): void  [API 9+]
+ setBoolean(String, boolean): void  [API 9+]
+ setBoolean(int, boolean): void  [API 1+]
+ setByte(String, byte): void  [API 9+]
+ setByte(int, byte): void  [API 1+]
+ setBytes(String, byte[]): void  [API 9+]
+ setBytes(int, byte[]): void  [API 1+]
+ setCharacterStream(String, Reader): void  [API 9+]
+ setCharacterStream(String, Reader, int): void  [API 9+]
+ setCharacterStream(int, Reader): void  [API 9+]
+ setCharacterStream(int, Reader, int): void  [API 1+]
+ setClob(String, Clob): void  [API 9+]
+ setClob(String, Reader): void  [API 9+]
+ setClob(String, Reader, long): void  [API 9+]
+ setClob(int, Clob): void  [API 1+]
+ setClob(int, Reader): void  [API 9+]
+ setClob(int, Reader, long): void  [API 9+]
+ setCommand(String): void  [API 1+]
+ setConcurrency(int): void  [API 1+]
+ setDataSourceName(String): void  [API 1+]
+ setDate(String, Date): void  [API 9+]
+ setDate(String, Date, Calendar): void  [API 9+]
+ setDate(int, Date): void  [API 1+]
+ setDate(int, Date, Calendar): void  [API 1+]
+ setDouble(String, double): void  [API 9+]
+ setDouble(int, double): void  [API 1+]
+ setEscapeProcessing(boolean): void  [API 1+]
+ setFloat(String, float): void  [API 9+]
+ setFloat(int, float): void  [API 1+]
+ setInt(String, int): void  [API 9+]
+ setInt(int, int): void  [API 1+]
+ setLong(String, long): void  [API 9+]
+ setLong(int, long): void  [API 1+]
+ setMaxFieldSize(int): void  [API 1+]
+ setMaxRows(int): void  [API 1+]
+ setNCharacterStream(String, Reader): void  [API 9+]
+ setNCharacterStream(String, Reader, long): void  [API 9+]
+ setNCharacterStream(int, Reader): void  [API 9+]
+ setNCharacterStream(int, Reader, long): void  [API 9+]
+ setNClob(String, NClob): void  [API 9+]
+ setNClob(String, Reader): void  [API 9+]
+ setNClob(String, Reader, long): void  [API 9+]
+ setNClob(int, NClob): void  [API 9+]
+ setNClob(int, Reader): void  [API 9+]
+ setNClob(int, Reader, long): void  [API 9+]
+ setNString(String, String): void  [API 9+]
+ setNString(int, String): void  [API 9+]
+ setNull(String, int): void  [API 9+]
+ setNull(String, int, String): void  [API 9+]
+ setNull(int, int): void  [API 1+]
+ setNull(int, int, String): void  [API 1+]
+ setObject(String, Object): void  [API 9+]
+ setObject(String, Object, int): void  [API 9+]
+ setObject(String, Object, int, int): void  [API 9+]
+ setObject(int, Object): void  [API 1+]
+ setObject(int, Object, int): void  [API 1+]
+ setObject(int, Object, int, int): void  [API 1+]
+ setPassword(String): void  [API 1+]
+ setQueryTimeout(int): void  [API 1+]
+ setReadOnly(boolean): void  [API 1+]
+ setRef(int, Ref): void  [API 1+]
+ setRowId(String, RowId): void  [API 9+]
+ setRowId(int, RowId): void  [API 9+]
+ setSQLXML(String, SQLXML): void  [API 9+]
+ setSQLXML(int, SQLXML): void  [API 9+]
+ setShort(String, short): void  [API 9+]
+ setShort(int, short): void  [API 1+]
+ setString(String, String): void  [API 9+]
+ setString(int, String): void  [API 1+]
+ setTime(String, Time): void  [API 9+]
+ setTime(String, Time, Calendar): void  [API 9+]
+ setTime(int, Time): void  [API 1+]
+ setTime(int, Time, Calendar): void  [API 1+]
+ setTimestamp(String, Timestamp): void  [API 9+]
+ setTimestamp(String, Timestamp, Calendar): void  [API 9+]
+ setTimestamp(int, Timestamp): void  [API 1+]
+ setTimestamp(int, Timestamp, Calendar): void  [API 1+]
+ setTransactionIsolation(int): void  [API 1+]
+ setType(int): void  [API 1+]
+ setTypeMap(Map): void  [API 1+]
+ setURL(int, URL): void  [API 9+]
+ setUrl(String): void  [API 1+]
+ setUsername(String): void  [API 1+]
```

## RowSetEvent — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- RowSetEvent(RowSet)  [API 1+]
```

## RowSetInternal — declaration; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ getConnection(): Connection  [API 1+]
+ getOriginal(): ResultSet  [API 1+]
+ getOriginalRow(): ResultSet  [API 1+]
+ getParams(): Object[]  [API 1+]
+ setMetaData(RowSetMetaData): void  [API 1+]
```

## RowSetListener — declaration; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ cursorMoved(RowSetEvent): void  [API 1+]
+ rowChanged(RowSetEvent): void  [API 1+]
+ rowSetChanged(RowSetEvent): void  [API 1+]
```

## RowSetMetaData — declaration; API 1+ (17 +, 0 *, 0 ~, 0 -)

```
+ setAutoIncrement(int, boolean): void  [API 1+]
+ setCaseSensitive(int, boolean): void  [API 1+]
+ setCatalogName(int, String): void  [API 1+]
+ setColumnCount(int): void  [API 1+]
+ setColumnDisplaySize(int, int): void  [API 1+]
+ setColumnLabel(int, String): void  [API 1+]
+ setColumnName(int, String): void  [API 1+]
+ setColumnType(int, int): void  [API 1+]
+ setColumnTypeName(int, String): void  [API 1+]
+ setCurrency(int, boolean): void  [API 1+]
+ setNullable(int, int): void  [API 1+]
+ setPrecision(int, int): void  [API 1+]
+ setScale(int, int): void  [API 1+]
+ setSchemaName(int, String): void  [API 1+]
+ setSearchable(int, boolean): void  [API 1+]
+ setSigned(int, boolean): void  [API 1+]
+ setTableName(int, String): void  [API 1+]
```

## RowSetReader — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ readData(RowSetInternal): void  [API 1+]
```

## RowSetWriter — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ writeData(RowSetInternal): boolean  [API 1+]
```

## StatementEvent — declaration; API 9+ (0 +, 0 *, 0 ~, 4 -)

```
- StatementEvent(PooledConnection, PreparedStatement)  [API 9+]
- StatementEvent(PooledConnection, PreparedStatement, SQLException)  [API 9+]
- getSQLException(): SQLException  [API 9+]
- getStatement(): PreparedStatement  [API 9+]
```

## StatementEventListener — declaration; API 9+ (2 +, 0 *, 0 ~, 0 -)

```
+ statementClosed(StatementEvent): void  [API 9+]
+ statementErrorOccurred(StatementEvent): void  [API 9+]
```


# org.apache.http.util

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ByteArrayBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (14 +, 0 *, 0 ~, 0 -)

```
+ ByteArrayBuffer(int)  [API 1-22, removed in 23]
+ append(CharArrayBuffer, int, int): void  [API 1-22, removed in 23]
+ append(byte[], int, int): void  [API 1-22, removed in 23]
+ append(char[], int, int): void  [API 1-22, removed in 23]
+ append(int): void  [API 1-22, removed in 23]
+ buffer(): byte[]  [API 1-22, removed in 23]
+ byteAt(int): int  [API 1-22, removed in 23]
+ capacity(): int  [API 1-22, removed in 23]
+ clear(): void  [API 1-22, removed in 23]
+ isEmpty(): boolean  [API 1-22, removed in 23]
+ isFull(): boolean  [API 1-22, removed in 23]
+ length(): int  [API 1-22, removed in 23]
+ setLength(int): void  [API 1-22, removed in 23]
+ toByteArray(): byte[]  [API 1-22, removed in 23]
```

## CharArrayBuffer — bundled library; API 1-22, removed in 23, deprecated in 22 (23 +, 0 *, 0 ~, 0 -)

```
+ CharArrayBuffer(int)  [API 1-22, removed in 23]
+ append(ByteArrayBuffer, int, int): void  [API 1-22, removed in 23]
+ append(CharArrayBuffer): void  [API 1-22, removed in 23]
+ append(CharArrayBuffer, int, int): void  [API 1-22, removed in 23]
+ append(Object): void  [API 1-22, removed in 23]
+ append(String): void  [API 1-22, removed in 23]
+ append(byte[], int, int): void  [API 1-22, removed in 23]
+ append(char): void  [API 1-22, removed in 23]
+ append(char[], int, int): void  [API 1-22, removed in 23]
+ buffer(): char[]  [API 1-22, removed in 23]
+ capacity(): int  [API 1-22, removed in 23]
+ charAt(int): char  [API 1-22, removed in 23]
+ clear(): void  [API 1-22, removed in 23]
+ ensureCapacity(int): void  [API 1-22, removed in 23]
+ indexOf(int): int  [API 1-22, removed in 23]
+ indexOf(int, int, int): int  [API 1-22, removed in 23]
+ isEmpty(): boolean  [API 1-22, removed in 23]
+ isFull(): boolean  [API 1-22, removed in 23]
+ length(): int  [API 1-22, removed in 23]
+ setLength(int): void  [API 1-22, removed in 23]
+ substring(int, int): String  [API 1-22, removed in 23]
+ substringTrimmed(int, int): String  [API 1-22, removed in 23]
+ toCharArray(): char[]  [API 1-22, removed in 23]
```

## EncodingUtils — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ getAsciiBytes(String): byte[]  [API 1-22, removed in 23]
+ getAsciiString(byte[]): String  [API 1-22, removed in 23]
+ getAsciiString(byte[], int, int): String  [API 1-22, removed in 23]
+ getBytes(String, String): byte[]  [API 1-22, removed in 23]
+ getString(byte[], String): String  [API 1-22, removed in 23]
+ getString(byte[], int, int, String): String  [API 1-22, removed in 23]
```

## EntityUtils — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ getContentCharSet(HttpEntity): String  [API 1-22, removed in 23]
+ toByteArray(HttpEntity): byte[]  [API 1-22, removed in 23]
+ toString(HttpEntity): String  [API 1-22, removed in 23]
+ toString(HttpEntity, String): String  [API 1-22, removed in 23]
```

## ExceptionUtils — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ initCause(Throwable, Throwable): void  [API 1-22, removed in 23]
```

## LangUtils — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ equals(Object, Object): boolean  [API 1-22, removed in 23]
+ equals(Object[], Object[]): boolean  [API 1-22, removed in 23]
+ hashCode(int, Object): int  [API 1-22, removed in 23]
+ hashCode(int, boolean): int  [API 1-22, removed in 23]
+ hashCode(int, int): int  [API 1-22, removed in 23]
```

## VersionInfo — bundled library; API 1-22, removed in 23, deprecated in 22 (9 +, 0 *, 0 ~, 0 -)

```
+ VersionInfo(String, String, String, String, String)  [API 1-22, removed in 23]
+ fromMap(String, Map, ClassLoader): VersionInfo  [API 1-22, removed in 23]
+ getClassloader(): String  [API 1-22, removed in 23]
+ getModule(): String  [API 1-22, removed in 23]
+ getPackage(): String  [API 1-22, removed in 23]
+ getRelease(): String  [API 1-22, removed in 23]
+ getTimestamp(): String  [API 1-22, removed in 23]
+ loadVersionInfo(String, ClassLoader): VersionInfo  [API 1-22, removed in 23]
+ loadVersionInfo(String[], ClassLoader): VersionInfo[]  [API 1-22, removed in 23]
```


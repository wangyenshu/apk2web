# java.util.zip

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Adler32 — TeaVM class library; API 1+ (6 +, 0 *, 0 ~, 1 -)

```
+ Adler32()  [API 1+]
+ getValue(): long  [API 1+]
+ reset(): void  [API 1+]
- update(ByteBuffer): void  [API 26+]
+ update(byte[]): void  [API 1+]
+ update(byte[], int, int): void  [API 1+]
+ update(int): void  [API 1+]
```

## CRC32 — TeaVM class library; API 1+ (6 +, 0 *, 0 ~, 1 -)

```
+ CRC32()  [API 1+]
+ getValue(): long  [API 1+]
+ reset(): void  [API 1+]
- update(ByteBuffer): void  [API 26+]
+ update(byte[]): void  [API 1+]
+ update(byte[], int, int): void  [API 1+]
+ update(int): void  [API 1+]
```

## CRC32C — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- CRC32C()  [API 34+]
- getValue(): long  [API 34+]
- reset(): void  [API 34+]
- update(ByteBuffer): void  [API 34+]
- update(byte[], int, int): void  [API 34+]
- update(int): void  [API 34+]
```

## CheckedInputStream — TeaVM class library; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ CheckedInputStream(InputStream, Checksum)  [API 1+]
+ getChecksum(): Checksum  [API 1+]
+ read(): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ skip(long): long  [API 1+]
```

## CheckedOutputStream — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CheckedOutputStream(OutputStream, Checksum)  [API 1+]
+ getChecksum(): Checksum  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## Checksum — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 2 -)

```
+ getValue(): long  [API 1+]
+ reset(): void  [API 1+]
- update(ByteBuffer): void  [API 34+]
- update(byte[]): void  [API 34+]
+ update(byte[], int, int): void  [API 1+]
+ update(int): void  [API 1+]
```

## DataFormatException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ DataFormatException()  [API 1+]
+ DataFormatException(String)  [API 1+]
```

## Deflater — TeaVM class library; API 1+ (21 +, 0 *, 0 ~, 5 -)

```
+ Deflater()  [API 1+]
+ Deflater(int)  [API 1+]
+ Deflater(int, boolean)  [API 1+]
- deflate(ByteBuffer): int  [API 35+]
- deflate(ByteBuffer, int): int  [API 35+]
+ deflate(byte[]): int  [API 1+]
+ deflate(byte[], int, int): int  [API 1+]
- deflate(byte[], int, int, int): int  [API 19+]
+ end(): void  [API 1+]
+ finish(): void  [API 1+]
+ finished(): boolean  [API 1+]
+ getAdler(): int  [API 1+]
+ getBytesRead(): long  [API 1+]
+ getBytesWritten(): long  [API 1+]
+ getTotalIn(): int  [API 1+]
+ getTotalOut(): int  [API 1+]
+ needsInput(): boolean  [API 1+]
+ reset(): void  [API 1+]
- setDictionary(ByteBuffer): void  [API 35+]
+ setDictionary(byte[]): void  [API 1+]
+ setDictionary(byte[], int, int): void  [API 1+]
- setInput(ByteBuffer): void  [API 35+]
+ setInput(byte[]): void  [API 1+]
+ setInput(byte[], int, int): void  [API 1+]
+ setLevel(int): void  [API 1+]
+ setStrategy(int): void  [API 1+]
```

## DeflaterInputStream — declaration; API 9+ (0 +, 0 *, 0 ~, 11 -)

```
- DeflaterInputStream(InputStream)  [API 9+]
- DeflaterInputStream(InputStream, Deflater)  [API 9+]
- DeflaterInputStream(InputStream, Deflater, int)  [API 9+]
- available(): int  [API 9+]
- buf [field]  [API 9+]
- close(): void  [API 9+]
- def [field]  [API 9+]
- mark(int): void  [API 9+]
- markSupported(): boolean  [API 9+]
- read(): int  [API 9+]
- read(byte[], int, int): int  [API 9+]
- reset(): void  [API 9+]
- skip(long): long  [API 9+]
```

## DeflaterOutputStream — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 3 -)

```
+ DeflaterOutputStream(OutputStream)  [API 1+]
+ DeflaterOutputStream(OutputStream, Deflater)  [API 1+]
- DeflaterOutputStream(OutputStream, Deflater, boolean)  [API 19+]
+ DeflaterOutputStream(OutputStream, Deflater, int)  [API 1+]
- DeflaterOutputStream(OutputStream, Deflater, int, boolean)  [API 19+]
- DeflaterOutputStream(OutputStream, boolean)  [API 19+]
+ buf [field]  [API 1+]
+ close(): void  [API 1+]
+ def [field]  [API 1+]
+ deflate(): void  [API 1+]
+ finish(): void  [API 1+]
+ flush(): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## GZIPInputStream — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ GZIPInputStream(InputStream)  [API 1+]
+ GZIPInputStream(InputStream, int)  [API 1+]
+ close(): void  [API 1+]
+ crc [field]  [API 1+]
+ eos [field]  [API 1+]
+ read(byte[], int, int): int  [API 1+]
```

## GZIPOutputStream — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 2 -)

```
+ GZIPOutputStream(OutputStream)  [API 1+]
- GZIPOutputStream(OutputStream, boolean)  [API 19+]
+ GZIPOutputStream(OutputStream, int)  [API 1+]
- GZIPOutputStream(OutputStream, int, boolean)  [API 19+]
+ crc [field]  [API 1+]
+ finish(): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
```

## Inflater — TeaVM class library; API 1+ (19 +, 0 *, 0 ~, 3 -)

```
+ Inflater()  [API 1+]
+ Inflater(boolean)  [API 1+]
+ end(): void  [API 1+]
+ finished(): boolean  [API 1+]
+ getAdler(): int  [API 1+]
+ getBytesRead(): long  [API 1+]
+ getBytesWritten(): long  [API 1+]
+ getRemaining(): int  [API 1+]
+ getTotalIn(): int  [API 1+]
+ getTotalOut(): int  [API 1+]
- inflate(ByteBuffer): int  [API 35+]
+ inflate(byte[]): int  [API 1+]
+ inflate(byte[], int, int): int  [API 1+]
+ needsDictionary(): boolean  [API 1+]
+ needsInput(): boolean  [API 1+]
+ reset(): void  [API 1+]
- setDictionary(ByteBuffer): void  [API 35+]
+ setDictionary(byte[]): void  [API 1+]
+ setDictionary(byte[], int, int): void  [API 1+]
- setInput(ByteBuffer): void  [API 35+]
+ setInput(byte[]): void  [API 1+]
+ setInput(byte[], int, int): void  [API 1+]
```

## InflaterInputStream — TeaVM class library; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ InflaterInputStream(InputStream)  [API 1+]
+ InflaterInputStream(InputStream, Inflater)  [API 1+]
+ InflaterInputStream(InputStream, Inflater, int)  [API 1+]
+ available(): int  [API 1+]
+ buf [field]  [API 1+]
+ close(): void  [API 1+]
+ closed [field]  [API 24+, deprecated in 29]
+ fill(): void  [API 1+]
+ inf [field]  [API 1+]
+ len [field]  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ read(): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
```

## InflaterOutputStream — declaration; API 9+ (0 +, 0 *, 0 ~, 8 -)

```
- InflaterOutputStream(OutputStream)  [API 9+]
- InflaterOutputStream(OutputStream, Inflater)  [API 9+]
- InflaterOutputStream(OutputStream, Inflater, int)  [API 9+]
- buf [field]  [API 9+]
- close(): void  [API 9+]
- finish(): void  [API 9+]
- flush(): void  [API 9+]
- inf [field]  [API 9+]
- write(byte[], int, int): void  [API 9+]
- write(int): void  [API 9+]
```

## ZipEntry — TeaVM class library; API 1+ (23 +, 0 *, 0 ~, 6 -)

```
+ ZipEntry(String)  [API 1+]
+ ZipEntry(ZipEntry)  [API 1+]
+ clone(): Object  [API 1+]
+ getComment(): String  [API 1+]
+ getCompressedSize(): long  [API 1+]
+ getCrc(): long  [API 1+]
- getCreationTime(): FileTime  [API 26+]
+ getExtra(): byte[]  [API 1+]
- getLastAccessTime(): FileTime  [API 26+]
- getLastModifiedTime(): FileTime  [API 26+]
+ getMethod(): int  [API 1+]
+ getName(): String  [API 1+]
+ getSize(): long  [API 1+]
+ getTime(): long  [API 1+]
+ getTimeLocal(): LocalDateTime  [API 35+]
+ hashCode(): int  [API 1+]
+ isDirectory(): boolean  [API 1+]
+ setComment(String): void  [API 1+]
+ setCompressedSize(long): void  [API 1+]
+ setCrc(long): void  [API 1+]
- setCreationTime(FileTime): ZipEntry  [API 26+]
+ setExtra(byte[]): void  [API 1+]
- setLastAccessTime(FileTime): ZipEntry  [API 26+]
- setLastModifiedTime(FileTime): ZipEntry  [API 26+]
+ setMethod(int): void  [API 1+]
+ setSize(long): void  [API 1+]
+ setTime(long): void  [API 1+]
+ setTimeLocal(LocalDateTime): void  [API 35+]
+ toString(): String  [API 1+]
```

## ZipError — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ ZipError(String)  [API 9+]
```

## ZipException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ZipException()  [API 1+]
+ ZipException(String)  [API 1+]
```

## ZipFile — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 5 -)

```
+ ZipFile(File)  [API 1+]
- ZipFile(File, Charset)  [API 24+]
+ ZipFile(File, int)  [API 1+]
- ZipFile(File, int, Charset)  [API 24+]
+ ZipFile(String)  [API 1+]
- ZipFile(String, Charset)  [API 24+]
+ close(): void  [API 1+]
+ entries(): Enumeration  [API 1+]
- getComment(): String  [API 19+]
+ getEntry(String): ZipEntry  [API 1+]
+ getInputStream(ZipEntry): InputStream  [API 1+]
+ getName(): String  [API 1+]
+ size(): int  [API 1+]
- stream(): Stream  [API 26+]
```

## ZipInputStream — TeaVM class library; API 1+ (8 +, 0 *, 0 ~, 1 -)

```
+ ZipInputStream(InputStream)  [API 1+]
- ZipInputStream(InputStream, Charset)  [API 24+]
+ available(): int  [API 1+]
+ close(): void  [API 1+]
+ closeEntry(): void  [API 1+]
+ createZipEntry(String): ZipEntry  [API 1+]
+ getNextEntry(): ZipEntry  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ skip(long): long  [API 1+]
```

## ZipOutputStream — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 1 -)

```
+ ZipOutputStream(OutputStream)  [API 1+]
- ZipOutputStream(OutputStream, Charset)  [API 24+]
+ close(): void  [API 1+]
+ closeEntry(): void  [API 1+]
+ finish(): void  [API 1+]
+ putNextEntry(ZipEntry): void  [API 1+]
+ setComment(String): void  [API 1+]
+ setLevel(int): void  [API 1+]
+ setMethod(int): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
```


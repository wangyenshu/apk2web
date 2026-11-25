# java.io

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BufferedInputStream — TeaVM class library; API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ BufferedInputStream(InputStream)  [API 1+]
+ BufferedInputStream(InputStream, int)  [API 1+]
+ available(): int  [API 1+]
+ buf [field]  [API 1+]
+ close(): void  [API 1+]
+ count [field]  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ marklimit [field]  [API 1+]
+ markpos [field]  [API 1+]
+ pos [field]  [API 1+]
+ read(): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
```

## BufferedOutputStream — TeaVM class library; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ BufferedOutputStream(OutputStream)  [API 1+]
+ BufferedOutputStream(OutputStream, int)  [API 1+]
+ buf [field]  [API 1+]
+ count [field]  [API 1+]
+ flush(): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## BufferedReader — TeaVM class library; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ BufferedReader(Reader)  [API 1+]
+ BufferedReader(Reader, int)  [API 1+]
+ close(): void  [API 1+]
+ lines(): Stream  [API 24+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ read(): int  [API 1+]
+ read(char[], int, int): int  [API 1+]
+ readLine(): String  [API 1+]
+ ready(): boolean  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
```

## BufferedWriter — TeaVM class library; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ BufferedWriter(Writer)  [API 1+]
+ BufferedWriter(Writer, int)  [API 1+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ newLine(): void  [API 1+]
+ write(String, int, int): void  [API 1+]
+ write(char[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## ByteArrayInputStream — TeaVM class library; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ ByteArrayInputStream(byte[])  [API 1+]
+ ByteArrayInputStream(byte[], int, int)  [API 1+]
+ available(): int  [API 1+]
+ buf [field]  [API 1+]
+ close(): void  [API 1+]
+ count [field]  [API 1+]
+ mark [field]  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ pos [field]  [API 1+]
+ read(): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ readAllBytes(): byte[]  [API 1+]
+ readNBytes(byte[], int, int): int  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
+ transferTo(OutputStream): long  [API 1+]
```

## ByteArrayOutputStream — TeaVM class library; API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ ByteArrayOutputStream()  [API 1+]
+ ByteArrayOutputStream(int)  [API 1+]
+ buf [field]  [API 1+]
+ close(): void  [API 1+]
+ count [field]  [API 1+]
+ reset(): void  [API 1+]
+ size(): int  [API 1+]
+ toByteArray(): byte[]  [API 1+]
+ toString(): String  [API 1+]
+ toString(Charset): String  [API 33+]
+ toString(String): String  [API 1+]
+ toString(int): String  [API 1+, deprecated in 15]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
+ writeBytes(byte[]): void  [API 33+]
+ writeTo(OutputStream): void  [API 1+]
```

## CharArrayReader — TeaVM class library; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ CharArrayReader(char[])  [API 1+]
+ CharArrayReader(char[], int, int)  [API 1+]
+ buf [field]  [API 1+]
+ close(): void  [API 1+]
+ count [field]  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ markedPos [field]  [API 1+]
+ pos [field]  [API 1+]
+ read(): int  [API 1+]
+ read(CharBuffer): int  [API 1+]
+ read(char[], int, int): int  [API 1+]
+ ready(): boolean  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
```

## CharArrayWriter — TeaVM class library; API 1+ (15 +, 0 *, 0 ~, 0 -)

```
+ CharArrayWriter()  [API 1+]
+ CharArrayWriter(int)  [API 1+]
+ append(CharSequence): CharArrayWriter  [API 1+]
+ append(CharSequence, int, int): CharArrayWriter  [API 1+]
+ append(char): CharArrayWriter  [API 1+]
+ buf [field]  [API 1+]
+ close(): void  [API 1+]
+ count [field]  [API 1+]
+ flush(): void  [API 1+]
+ reset(): void  [API 1+]
+ size(): int  [API 1+]
+ toCharArray(): char[]  [API 1+]
+ toString(): String  [API 1+]
+ write(String, int, int): void  [API 1+]
+ write(char[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
+ writeTo(Writer): void  [API 1+]
```

## CharConversionException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CharConversionException()  [API 1+]
+ CharConversionException(String)  [API 1+]
```

## Closeable — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 1+]
```

## Console — TeaVM class library; API 9+ (0 +, 0 *, 0 ~, 10 -)

```
- charset(): Charset  [API 35+]
- flush(): void  [API 9+]
- format(String, Object[]): Console  [API 9+]
- printf(String, Object[]): Console  [API 9+]
- readLine(): String  [API 9+]
- readLine(String, Object[]): String  [API 9+]
- readPassword(): char[]  [API 9+]
- readPassword(String, Object[]): char[]  [API 9+]
- reader(): Reader  [API 9+]
- writer(): PrintWriter  [API 9+]
```

## DataInput — TeaVM class library; API 1+ (15 +, 0 *, 0 ~, 0 -)

```
+ readBoolean(): boolean  [API 1+]
+ readByte(): byte  [API 1+]
+ readChar(): char  [API 1+]
+ readDouble(): double  [API 1+]
+ readFloat(): float  [API 1+]
+ readFully(byte[]): void  [API 1+]
+ readFully(byte[], int, int): void  [API 1+]
+ readInt(): int  [API 1+]
+ readLine(): String  [API 1+]
+ readLong(): long  [API 1+]
+ readShort(): short  [API 1+]
+ readUTF(): String  [API 1+]
+ readUnsignedByte(): int  [API 1+]
+ readUnsignedShort(): int  [API 1+]
+ skipBytes(int): int  [API 1+]
```

## DataInputStream — TeaVM class library; API 1+ (19 +, 0 *, 0 ~, 0 -)

```
+ DataInputStream(InputStream)  [API 1+]
+ read(byte[]): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ readBoolean(): boolean  [API 1+]
+ readByte(): byte  [API 1+]
+ readChar(): char  [API 1+]
+ readDouble(): double  [API 1+]
+ readFloat(): float  [API 1+]
+ readFully(byte[]): void  [API 1+]
+ readFully(byte[], int, int): void  [API 1+]
+ readInt(): int  [API 1+]
+ readLine(): String  [API 1+]
+ readLong(): long  [API 1+]
+ readShort(): short  [API 1+]
+ readUTF(): String  [API 1+]
+ readUTF(DataInput): String [static]  [API 1+]
+ readUnsignedByte(): int  [API 1+]
+ readUnsignedShort(): int  [API 1+]
+ skipBytes(int): int  [API 1+]
```

## DataOutput — TeaVM class library; API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ write(byte[]): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
+ writeBoolean(boolean): void  [API 1+]
+ writeByte(int): void  [API 1+]
+ writeBytes(String): void  [API 1+]
+ writeChar(int): void  [API 1+]
+ writeChars(String): void  [API 1+]
+ writeDouble(double): void  [API 1+]
+ writeFloat(float): void  [API 1+]
+ writeInt(int): void  [API 1+]
+ writeLong(long): void  [API 1+]
+ writeShort(int): void  [API 1+]
+ writeUTF(String): void  [API 1+]
```

## DataOutputStream — TeaVM class library; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ DataOutputStream(OutputStream)  [API 1+]
+ flush(): void  [API 1+]
+ size(): int  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
+ writeBoolean(boolean): void  [API 1+]
+ writeByte(int): void  [API 1+]
+ writeBytes(String): void  [API 1+]
+ writeChar(int): void  [API 1+]
+ writeChars(String): void  [API 1+]
+ writeDouble(double): void  [API 1+]
+ writeFloat(float): void  [API 1+]
+ writeInt(int): void  [API 1+]
+ writeLong(long): void  [API 1+]
+ writeShort(int): void  [API 1+]
+ writeUTF(String): void  [API 1+]
+ written [field]  [API 1+]
```

## EOFException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ EOFException()  [API 1+]
+ EOFException(String)  [API 1+]
```

## Externalizable — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- readExternal(ObjectInput): void  [API 1+]
- writeExternal(ObjectOutput): void  [API 1+]
```

## File — TeaVM class library; API 1+ (53 +, 0 *, 0 ~, 1 -)

```
+ File(File, String)  [API 1+]
+ File(String)  [API 1+]
+ File(String, String)  [API 1+]
+ File(URI)  [API 1+]
+ canExecute(): boolean  [API 9+]
+ canRead(): boolean  [API 1+]
+ canWrite(): boolean  [API 1+]
+ compareTo(File): int  [API 1+]
+ createNewFile(): boolean  [API 1+]
+ createTempFile(String, String): File [static]  [API 1+]
+ createTempFile(String, String, File): File [static]  [API 1+]
+ delete(): boolean  [API 1+]
+ deleteOnExit(): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ exists(): boolean  [API 1+]
+ getAbsoluteFile(): File  [API 1+]
+ getAbsolutePath(): String  [API 1+]
+ getCanonicalFile(): File  [API 1+]
+ getCanonicalPath(): String  [API 1+]
+ getFreeSpace(): long  [API 9+]
+ getName(): String  [API 1+]
+ getParent(): String  [API 1+]
+ getParentFile(): File  [API 1+]
+ getPath(): String  [API 1+]
+ getTotalSpace(): long  [API 9+]
+ getUsableSpace(): long  [API 9+]
+ hashCode(): int  [API 1+]
+ isAbsolute(): boolean  [API 1+]
+ isDirectory(): boolean  [API 1+]
+ isFile(): boolean  [API 1+]
+ isHidden(): boolean  [API 1+]
+ lastModified(): long  [API 1+]
+ length(): long  [API 1+]
+ list(): String[]  [API 1+]
+ list(FilenameFilter): String[]  [API 1+]
+ listFiles(): File[]  [API 1+]
+ listFiles(FileFilter): File[]  [API 1+]
+ listFiles(FilenameFilter): File[]  [API 1+]
+ listRoots(): File[] [static]  [API 1+]
+ mkdir(): boolean  [API 1+]
+ mkdirs(): boolean  [API 1+]
+ pathSeparator [field]  [API 1+]
+ pathSeparatorChar [field]  [API 1+]
+ renameTo(File): boolean  [API 1+]
+ separator [field]  [API 1+]
+ separatorChar [field]  [API 1+]
+ setExecutable(boolean): boolean  [API 9+]
+ setExecutable(boolean, boolean): boolean  [API 9+]
+ setLastModified(long): boolean  [API 1+]
+ setReadOnly(): boolean  [API 1+]
+ setReadable(boolean): boolean  [API 9+]
+ setReadable(boolean, boolean): boolean  [API 9+]
+ setWritable(boolean): boolean  [API 9+]
+ setWritable(boolean, boolean): boolean  [API 9+]
- toPath(): Path  [API 26+]
+ toString(): String  [API 1+]
+ toURI(): URI  [API 1+]
+ toURL(): URL  [API 1+, deprecated in 15]
```

## FileDescriptor — framework (a2w.jdk.FileDescriptor); API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ FileDescriptor()  [API 1+]
+ err [field]  [API 1+]
+ in [field]  [API 1+]
+ out [field]  [API 1+]
+ sync(): void  [API 1+]
+ valid(): boolean  [API 1+]
```

## FileFilter — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(File): boolean  [API 1+]
```

## FileInputStream — framework (a2w.jdk.FileInputStream); API 1+ (10 +, 0 *, 1 ~, 1 -)

```
+ FileInputStream(File)  [API 1+]
+ FileInputStream(FileDescriptor)  [API 1+]
+ FileInputStream(String)  [API 1+]
+ available(): int  [API 1+]
~ close(): void  [API 1+]
+ finalize(): void  [API 1+]
- getChannel(): FileChannel  [API 1+]
+ getFD(): FileDescriptor  [API 1+]
+ read(): int  [API 1+]
+ read(byte[]): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ skip(long): long  [API 1+]
```

## FileNotFoundException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ FileNotFoundException()  [API 1+]
+ FileNotFoundException(String)  [API 1+]
```

## FileOutputStream — TeaVM class library; API 1+ (10 +, 0 *, 0 ~, 2 -)

```
+ FileOutputStream(File)  [API 1+]
+ FileOutputStream(File, boolean)  [API 1+]
+ FileOutputStream(FileDescriptor)  [API 1+]
+ FileOutputStream(String)  [API 1+]
+ FileOutputStream(String, boolean)  [API 1+]
+ close(): void  [API 1+]
+ finalize(): void  [API 1+]
- getChannel(): FileChannel  [API 1+]
- getFD(): FileDescriptor  [API 1+]
+ write(byte[]): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## FilePermission — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- FilePermission(String, String)  [API 1+]
- getActions(): String  [API 1+]
- implies(Permission): boolean  [API 1+]
```

## FileReader — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 3 -)

```
+ FileReader(File)  [API 1+]
- FileReader(File, Charset)  [API 33+]
- FileReader(FileDescriptor)  [API 1+]
+ FileReader(String)  [API 1+]
- FileReader(String, Charset)  [API 33+]
```

## FileWriter — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 5 -)

```
+ FileWriter(File)  [API 1+]
- FileWriter(File, Charset)  [API 33+]
- FileWriter(File, Charset, boolean)  [API 33+]
+ FileWriter(File, boolean)  [API 1+]
- FileWriter(FileDescriptor)  [API 1+]
+ FileWriter(String)  [API 1+]
- FileWriter(String, Charset)  [API 33+]
- FileWriter(String, Charset, boolean)  [API 33+]
+ FileWriter(String, boolean)  [API 1+]
```

## FilenameFilter — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(File, String): boolean  [API 1+]
```

## FilterInputStream — TeaVM class library; API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ FilterInputStream(InputStream)  [API 1+]
+ available(): int  [API 1+]
+ close(): void  [API 1+]
+ in [field]  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ read(): int  [API 1+]
+ read(byte[]): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
```

## FilterOutputStream — TeaVM class library; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ FilterOutputStream(OutputStream)  [API 1+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ out [field]  [API 1+]
+ write(byte[]): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## FilterReader — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ FilterReader(Reader)  [API 1+]
+ close(): void  [API 1+]
+ in [field]  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ read(): int  [API 1+]
+ read(char[], int, int): int  [API 1+]
+ ready(): boolean  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
```

## FilterWriter — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- FilterWriter(Writer)  [API 1+]
- close(): void  [API 1+]
- flush(): void  [API 1+]
+ out [field]  [API 1+]
- write(String, int, int): void  [API 1+]
- write(char[], int, int): void  [API 1+]
- write(int): void  [API 1+]
```

## Flushable — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ flush(): void  [API 1+]
```

## IOError — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ IOError(Throwable)  [API 9+]
```

## IOException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ IOException()  [API 1+]
+ IOException(String)  [API 1+]
+ IOException(String, Throwable)  [API 9+]
+ IOException(Throwable)  [API 9+]
```

## InputStream — TeaVM class library; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ InputStream()  [API 1+]
+ available(): int  [API 1+]
+ close(): void  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ nullInputStream(): InputStream [static]  [API 33+]
+ read(): int  [API 1+]
+ read(byte[]): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ readAllBytes(): byte[]  [API 33+]
+ readNBytes(byte[], int, int): int  [API 33+]
+ readNBytes(int): byte[]  [API 33+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
+ skipNBytes(long): void  [API 34+]
+ transferTo(OutputStream): long  [API 33+]
```

## InputStreamReader — TeaVM class library; API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ InputStreamReader(InputStream)  [API 1+]
+ InputStreamReader(InputStream, Charset)  [API 1+]
+ InputStreamReader(InputStream, CharsetDecoder)  [API 1+]
+ InputStreamReader(InputStream, String)  [API 1+]
+ close(): void  [API 1+]
+ getEncoding(): String  [API 1+]
+ read(): int  [API 1+]
+ read(CharBuffer): int  [API 1+]
+ read(char[], int, int): int  [API 1+]
+ ready(): boolean  [API 1+]
```

## InterruptedIOException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ InterruptedIOException()  [API 1+]
+ InterruptedIOException(String)  [API 1+]
+ bytesTransferred [field]  [API 1+]
```

## InvalidClassException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ InvalidClassException(String)  [API 1+]
+ InvalidClassException(String, String)  [API 1+]
+ classname [field]  [API 1+]
+ getMessage(): String  [API 1+]
```

## InvalidObjectException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ InvalidObjectException(String)  [API 1+]
```

## LineNumberInputStream — declaration; API 1+, deprecated in 1 (0 +, 0 *, 0 ~, 9 -)

```
- LineNumberInputStream(InputStream)  [API 1+]
- available(): int  [API 1+]
- getLineNumber(): int  [API 1+]
- mark(int): void  [API 1+]
- read(): int  [API 1+]
- read(byte[], int, int): int  [API 1+]
- reset(): void  [API 1+]
- setLineNumber(int): void  [API 1+]
- skip(long): long  [API 1+]
```

## LineNumberReader — declaration; API 1+ (0 +, 0 *, 0 ~, 10 -)

```
- LineNumberReader(Reader)  [API 1+]
- LineNumberReader(Reader, int)  [API 1+]
- getLineNumber(): int  [API 1+]
- mark(int): void  [API 1+]
- read(): int  [API 1+]
- read(char[], int, int): int  [API 1+]
- readLine(): String  [API 1+]
- reset(): void  [API 1+]
- setLineNumber(int): void  [API 1+]
- skip(long): long  [API 1+]
```

## NotActiveException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NotActiveException()  [API 1+]
+ NotActiveException(String)  [API 1+]
```

## NotSerializableException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NotSerializableException()  [API 1+]
+ NotSerializableException(String)  [API 1+]
```

## ObjectInput — declaration; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ available(): int  [API 1+]
+ close(): void  [API 1+]
+ read(): int  [API 1+]
+ read(byte[]): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ readObject(): Object  [API 1+]
+ skip(long): long  [API 1+]
```

## ObjectInputStream — declaration; API 1+ (0 +, 0 *, 0 ~, 33 -)

```
- ObjectInputStream()  [API 1+]
- ObjectInputStream(InputStream)  [API 1+]
- available(): int  [API 1+]
- close(): void  [API 1+]
- defaultReadObject(): void  [API 1+]
- enableResolveObject(boolean): boolean  [API 1+]
- read(): int  [API 1+]
- read(byte[], int, int): int  [API 1+]
- readBoolean(): boolean  [API 1+]
- readByte(): byte  [API 1+]
- readChar(): char  [API 1+]
- readClassDescriptor(): ObjectStreamClass  [API 1+]
- readDouble(): double  [API 1+]
- readFields(): ObjectInputStream.GetField  [API 1+]
- readFloat(): float  [API 1+]
- readFully(byte[]): void  [API 1+]
- readFully(byte[], int, int): void  [API 1+]
- readInt(): int  [API 1+]
- readLine(): String  [API 1+]
- readLong(): long  [API 1+]
- readObject(): Object  [API 1+]
- readObjectOverride(): Object  [API 1+]
- readShort(): short  [API 1+]
- readStreamHeader(): void  [API 1+]
- readUTF(): String  [API 1+]
- readUnshared(): Object  [API 1+]
- readUnsignedByte(): int  [API 1+]
- readUnsignedShort(): int  [API 1+]
- registerValidation(ObjectInputValidation, int): void  [API 1+]
- resolveClass(ObjectStreamClass): Class  [API 1+]
- resolveObject(Object): Object  [API 1+]
- resolveProxyClass(String[]): Class  [API 1+]
- skipBytes(int): int  [API 1+]
```

## ObjectInputStream.GetField — declaration; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ GetField()  [API 1+]
+ defaulted(String): boolean  [API 1+]
+ get(String, Object): Object  [API 1+]
+ get(String, boolean): boolean  [API 1+]
+ get(String, byte): byte  [API 1+]
+ get(String, char): char  [API 1+]
+ get(String, double): double  [API 1+]
+ get(String, float): float  [API 1+]
+ get(String, int): int  [API 1+]
+ get(String, long): long  [API 1+]
+ get(String, short): short  [API 1+]
+ getObjectStreamClass(): ObjectStreamClass  [API 1+]
```

## ObjectInputValidation — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ validateObject(): void  [API 1+]
```

## ObjectOutput — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ write(byte[]): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
+ writeObject(Object): void  [API 1+]
```

## ObjectOutputStream — declaration; API 1+ (0 +, 0 *, 0 ~, 33 -)

```
- ObjectOutputStream()  [API 1+]
- ObjectOutputStream(OutputStream)  [API 1+]
- annotateClass(Class): void  [API 1+]
- annotateProxyClass(Class): void  [API 1+]
- close(): void  [API 1+]
- defaultWriteObject(): void  [API 1+]
- drain(): void  [API 1+]
- enableReplaceObject(boolean): boolean  [API 1+]
- flush(): void  [API 1+]
- putFields(): ObjectOutputStream.PutField  [API 1+]
- replaceObject(Object): Object  [API 1+]
- reset(): void  [API 1+]
- useProtocolVersion(int): void  [API 1+]
- write(byte[]): void  [API 1+]
- write(byte[], int, int): void  [API 1+]
- write(int): void  [API 1+]
- writeBoolean(boolean): void  [API 1+]
- writeByte(int): void  [API 1+]
- writeBytes(String): void  [API 1+]
- writeChar(int): void  [API 1+]
- writeChars(String): void  [API 1+]
- writeClassDescriptor(ObjectStreamClass): void  [API 1+]
- writeDouble(double): void  [API 1+]
- writeFields(): void  [API 1+]
- writeFloat(float): void  [API 1+]
- writeInt(int): void  [API 1+]
- writeLong(long): void  [API 1+]
- writeObject(Object): void  [API 1+]
- writeObjectOverride(Object): void  [API 1+]
- writeShort(int): void  [API 1+]
- writeStreamHeader(): void  [API 1+]
- writeUTF(String): void  [API 1+]
- writeUnshared(Object): void  [API 1+]
```

## ObjectOutputStream.PutField — declaration; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ PutField()  [API 1+]
+ put(String, Object): void  [API 1+]
+ put(String, boolean): void  [API 1+]
+ put(String, byte): void  [API 1+]
+ put(String, char): void  [API 1+]
+ put(String, double): void  [API 1+]
+ put(String, float): void  [API 1+]
+ put(String, int): void  [API 1+]
+ put(String, long): void  [API 1+]
+ put(String, short): void  [API 1+]
+ write(ObjectOutput): void  [API 1+, deprecated in 15]
```

## ObjectStreamClass — declaration; API 1+ (0 +, 0 *, 0 ~, 8 -)

```
- NO_FIELDS [field]  [API 1+]
- forClass(): Class  [API 1+]
- getField(String): ObjectStreamField  [API 1+]
- getFields(): ObjectStreamField[]  [API 1+]
- getName(): String  [API 1+]
- getSerialVersionUID(): long  [API 1+]
- lookup(Class): ObjectStreamClass [static]  [API 1+]
- lookupAny(Class): ObjectStreamClass [static]  [API 9+]
- toString(): String  [API 1+]
```

## ObjectStreamConstants — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

```
- SUBCLASS_IMPLEMENTATION_PERMISSION [field]  [API 1+]
- SUBSTITUTION_PERMISSION [field]  [API 1+]
```

## ObjectStreamException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ObjectStreamException()  [API 1+]
+ ObjectStreamException(String)  [API 1+]
```

## ObjectStreamField — declaration; API 1+ (0 +, 7 *, 0 ~, 5 -)

```
* ObjectStreamField(String, Class)  [API 1+]
* ObjectStreamField(String, Class, boolean)  [API 1+]
- compareTo(Object): int  [API 1+]
* getName(): String  [API 1+]
* getOffset(): int  [API 1+]
* getType(): Class  [API 1+]
- getTypeCode(): char  [API 1+]
- getTypeString(): String  [API 1+]
- isPrimitive(): boolean  [API 1+]
* isUnshared(): boolean  [API 1+]
* setOffset(int): void  [API 1+]
- toString(): String  [API 1+]
```

## OptionalDataException — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

```
+ eof [field]  [API 1+]
+ length [field]  [API 1+]
```

## OutputStream — TeaVM class library; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ OutputStream()  [API 1+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ nullOutputStream(): OutputStream [static]  [API 33+]
+ write(byte[]): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## OutputStreamWriter — TeaVM class library; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ OutputStreamWriter(OutputStream)  [API 1+]
+ OutputStreamWriter(OutputStream, Charset)  [API 1+]
+ OutputStreamWriter(OutputStream, CharsetEncoder)  [API 1+]
+ OutputStreamWriter(OutputStream, String)  [API 1+]
+ append(CharSequence): Writer  [API 1+]
+ append(CharSequence, int, int): Writer  [API 1+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ getEncoding(): String  [API 1+]
+ write(String, int, int): void  [API 1+]
+ write(char[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## PipedInputStream — TeaVM class library; API 1+ (8 +, 0 *, 0 ~, 2 -)

```
+ PipedInputStream()  [API 1+]
+ PipedInputStream(PipedOutputStream)  [API 1+]
- PipedInputStream(PipedOutputStream, int)  [API 9+]
- PipedInputStream(int)  [API 9+]
+ available(): int  [API 1+]
+ buffer [field]  [API 1+]
+ close(): void  [API 1+]
+ connect(PipedOutputStream): void  [API 1+]
+ in [field]  [API 1+]
+ out [field]  [API 1+]
+ read(): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ receive(int): void  [API 1+]
```

## PipedOutputStream — TeaVM class library; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ PipedOutputStream()  [API 1+]
+ PipedOutputStream(PipedInputStream)  [API 1+]
+ close(): void  [API 1+]
+ connect(PipedInputStream): void  [API 1+]
+ flush(): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## PipedReader — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- PipedReader()  [API 1+]
- PipedReader(PipedWriter)  [API 1+]
- PipedReader(PipedWriter, int)  [API 9+]
- PipedReader(int)  [API 9+]
- close(): void  [API 1+]
- connect(PipedWriter): void  [API 1+]
- read(): int  [API 1+]
- read(char[], int, int): int  [API 1+]
- ready(): boolean  [API 1+]
```

## PipedWriter — declaration; API 1+ (0 +, 0 *, 0 ~, 7 -)

```
- PipedWriter()  [API 1+]
- PipedWriter(PipedReader)  [API 1+]
- close(): void  [API 1+]
- connect(PipedReader): void  [API 1+]
- flush(): void  [API 1+]
- write(char[], int, int): void  [API 1+]
- write(int): void  [API 1+]
```

## PrintStream — TeaVM class library; API 1+ (43 +, 0 *, 0 ~, 2 -)

```
+ PrintStream(File)  [API 1+]
- PrintStream(File, Charset)  [API 33+]
+ PrintStream(File, String)  [API 1+]
+ PrintStream(OutputStream)  [API 1+]
+ PrintStream(OutputStream, boolean)  [API 1+]
+ PrintStream(OutputStream, boolean, Charset)  [API 33+]
+ PrintStream(OutputStream, boolean, String)  [API 1+]
+ PrintStream(String)  [API 1+]
- PrintStream(String, Charset)  [API 33+]
+ PrintStream(String, String)  [API 1+]
+ append(CharSequence): PrintStream  [API 1+]
+ append(CharSequence, int, int): PrintStream  [API 1+]
+ append(char): PrintStream  [API 1+]
+ checkError(): boolean  [API 1+]
+ clearError(): void  [API 9+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ format(Locale, String, Object[]): PrintStream  [API 1+]
+ format(String, Object[]): PrintStream  [API 1+]
+ print(Object): void  [API 1+]
+ print(String): void  [API 1+]
+ print(boolean): void  [API 1+]
+ print(char): void  [API 1+]
+ print(char[]): void  [API 1+]
+ print(double): void  [API 1+]
+ print(float): void  [API 1+]
+ print(int): void  [API 1+]
+ print(long): void  [API 1+]
+ printf(Locale, String, Object[]): PrintStream  [API 1+]
+ printf(String, Object[]): PrintStream  [API 1+]
+ println(): void  [API 1+]
+ println(Object): void  [API 1+]
+ println(String): void  [API 1+]
+ println(boolean): void  [API 1+]
+ println(char): void  [API 1+]
+ println(char[]): void  [API 1+]
+ println(double): void  [API 1+]
+ println(float): void  [API 1+]
+ println(int): void  [API 1+]
+ println(long): void  [API 1+]
+ setError(): void  [API 1+]
+ write(byte[]): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
+ writeBytes(byte[]): void  [API 34+]
```

## PrintWriter — TeaVM class library; API 1+ (43 +, 0 *, 0 ~, 4 -)

```
+ PrintWriter(File)  [API 1+]
- PrintWriter(File, Charset)  [API 33+]
+ PrintWriter(File, String)  [API 1+]
+ PrintWriter(OutputStream)  [API 1+]
+ PrintWriter(OutputStream, boolean)  [API 1+]
- PrintWriter(OutputStream, boolean, Charset)  [API 33+]
+ PrintWriter(String)  [API 1+]
- PrintWriter(String, Charset)  [API 33+]
+ PrintWriter(String, String)  [API 1+]
+ PrintWriter(Writer)  [API 1+]
+ PrintWriter(Writer, boolean)  [API 1+]
+ append(CharSequence): PrintWriter  [API 1+]
+ append(CharSequence, int, int): PrintWriter  [API 1+]
+ append(char): PrintWriter  [API 1+]
+ checkError(): boolean  [API 1+]
- clearError(): void  [API 9+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ format(Locale, String, Object[]): PrintWriter  [API 1+]
+ format(String, Object[]): PrintWriter  [API 1+]
+ out [field]  [API 1+]
+ print(Object): void  [API 1+]
+ print(String): void  [API 1+]
+ print(boolean): void  [API 1+]
+ print(char): void  [API 1+]
+ print(char[]): void  [API 1+]
+ print(double): void  [API 1+]
+ print(float): void  [API 1+]
+ print(int): void  [API 1+]
+ print(long): void  [API 1+]
+ printf(Locale, String, Object[]): PrintWriter  [API 1+]
+ printf(String, Object[]): PrintWriter  [API 1+]
+ println(): void  [API 1+]
+ println(Object): void  [API 1+]
+ println(String): void  [API 1+]
+ println(boolean): void  [API 1+]
+ println(char): void  [API 1+]
+ println(char[]): void  [API 1+]
+ println(double): void  [API 1+]
+ println(float): void  [API 1+]
+ println(int): void  [API 1+]
+ println(long): void  [API 1+]
+ setError(): void  [API 1+]
+ write(String): void  [API 1+]
+ write(String, int, int): void  [API 1+]
+ write(char[]): void  [API 1+]
+ write(char[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## PushbackInputStream — TeaVM class library; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ PushbackInputStream(InputStream)  [API 1+]
+ PushbackInputStream(InputStream, int)  [API 1+]
+ available(): int  [API 1+]
+ buf [field]  [API 1+]
+ close(): void  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ pos [field]  [API 1+]
+ read(): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
+ unread(byte[]): void  [API 1+]
+ unread(byte[], int, int): void  [API 1+]
+ unread(int): void  [API 1+]
```

## PushbackReader — TeaVM class library; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ PushbackReader(Reader)  [API 1+]
+ PushbackReader(Reader, int)  [API 1+]
+ close(): void  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ read(): int  [API 1+]
+ read(char[], int, int): int  [API 1+]
+ ready(): boolean  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
+ unread(char[]): void  [API 1+]
+ unread(char[], int, int): void  [API 1+]
+ unread(int): void  [API 1+]
```

## RandomAccessFile — TeaVM class library; API 1+ (40 +, 0 *, 0 ~, 2 -)

```
+ RandomAccessFile(File, String)  [API 1+]
+ RandomAccessFile(String, String)  [API 1+]
+ close(): void  [API 1+]
+ finalize(): void  [API 1+]
- getChannel(): FileChannel  [API 1+]
- getFD(): FileDescriptor  [API 1+]
+ getFilePointer(): long  [API 1+]
+ length(): long  [API 1+]
+ read(): int  [API 1+]
+ read(byte[]): int  [API 1+]
+ read(byte[], int, int): int  [API 1+]
+ readBoolean(): boolean  [API 1+]
+ readByte(): byte  [API 1+]
+ readChar(): char  [API 1+]
+ readDouble(): double  [API 1+]
+ readFloat(): float  [API 1+]
+ readFully(byte[]): void  [API 1+]
+ readFully(byte[], int, int): void  [API 1+]
+ readInt(): int  [API 1+]
+ readLine(): String  [API 1+]
+ readLong(): long  [API 1+]
+ readShort(): short  [API 1+]
+ readUTF(): String  [API 1+]
+ readUnsignedByte(): int  [API 1+]
+ readUnsignedShort(): int  [API 1+]
+ seek(long): void  [API 1+]
+ setLength(long): void  [API 1+]
+ skipBytes(int): int  [API 1+]
+ write(byte[]): void  [API 1+]
+ write(byte[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
+ writeBoolean(boolean): void  [API 1+]
+ writeByte(int): void  [API 1+]
+ writeBytes(String): void  [API 1+]
+ writeChar(int): void  [API 1+]
+ writeChars(String): void  [API 1+]
+ writeDouble(double): void  [API 1+]
+ writeFloat(float): void  [API 1+]
+ writeInt(int): void  [API 1+]
+ writeLong(long): void  [API 1+]
+ writeShort(int): void  [API 1+]
+ writeUTF(String): void  [API 1+]
```

## Reader — TeaVM class library; API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ Reader()  [API 1+]
+ Reader(Object)  [API 1+]
+ close(): void  [API 1+]
+ lock [field]  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ nullReader(): Reader [static]  [API 33+]
+ read(): int  [API 1+]
+ read(CharBuffer): int  [API 1+]
+ read(char[]): int  [API 1+]
+ read(char[], int, int): int  [API 1+]
+ ready(): boolean  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
+ transferTo(Writer): long  [API 33+]
```

## SequenceInputStream — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- SequenceInputStream(Enumeration)  [API 1+]
- SequenceInputStream(InputStream, InputStream)  [API 1+]
- available(): int  [API 1+]
- close(): void  [API 1+]
- read(): int  [API 1+]
- read(byte[], int, int): int  [API 1+]
```

## Serial — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## Serializable — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## SerializablePermission — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- SerializablePermission(String)  [API 1+]
- SerializablePermission(String, String)  [API 1+]
```

## StreamCorruptedException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ StreamCorruptedException()  [API 1+]
+ StreamCorruptedException(String)  [API 1+]
```

## StreamTokenizer — TeaVM class library; API 1+ (18 +, 0 *, 0 ~, 0 -)

```
+ StreamTokenizer(InputStream)  [API 1+, deprecated in 15]
+ StreamTokenizer(Reader)  [API 1+]
+ commentChar(int): void  [API 1+]
+ eolIsSignificant(boolean): void  [API 1+]
+ lineno(): int  [API 1+]
+ lowerCaseMode(boolean): void  [API 1+]
+ nextToken(): int  [API 1+]
+ nval [field]  [API 1+]
+ ordinaryChar(int): void  [API 1+]
+ ordinaryChars(int, int): void  [API 1+]
+ parseNumbers(): void  [API 1+]
+ pushBack(): void  [API 1+]
+ quoteChar(int): void  [API 1+]
+ resetSyntax(): void  [API 1+]
+ slashSlashComments(boolean): void  [API 1+]
+ slashStarComments(boolean): void  [API 1+]
+ sval [field]  [API 1+]
+ toString(): String  [API 1+]
+ ttype [field]  [API 1+]
+ whitespaceChars(int, int): void  [API 1+]
+ wordChars(int, int): void  [API 1+]
```

## StringBufferInputStream — declaration; API 1+, deprecated in 1 (0 +, 0 *, 0 ~, 6 -)

```
- StringBufferInputStream(String)  [API 1+]
- available(): int  [API 1+]
+ buffer [field]  [API 1+]
+ count [field]  [API 1+]
+ pos [field]  [API 1+]
- read(): int  [API 1+]
- read(byte[], int, int): int  [API 1+]
- reset(): void  [API 1+]
- skip(long): long  [API 1+]
```

## StringReader — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ StringReader(String)  [API 1+]
+ close(): void  [API 1+]
+ mark(int): void  [API 1+]
+ markSupported(): boolean  [API 1+]
+ read(): int  [API 1+]
+ read(char[], int, int): int  [API 1+]
+ ready(): boolean  [API 1+]
+ reset(): void  [API 1+]
+ skip(long): long  [API 1+]
```

## StringWriter — TeaVM class library; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ StringWriter()  [API 1+]
+ StringWriter(int)  [API 1+]
+ append(CharSequence): StringWriter  [API 1+]
+ append(CharSequence, int, int): StringWriter  [API 1+]
+ append(char): StringWriter  [API 1+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ getBuffer(): StringBuffer  [API 1+]
+ toString(): String  [API 1+]
+ write(String): void  [API 1+]
+ write(String, int, int): void  [API 1+]
+ write(char[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```

## SyncFailedException — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ SyncFailedException(String)  [API 1+]
```

## UTFDataFormatException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UTFDataFormatException()  [API 1+]
+ UTFDataFormatException(String)  [API 1+]
```

## UncheckedIOException — TeaVM class library; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ UncheckedIOException(IOException)  [API 24+]
+ UncheckedIOException(String, IOException)  [API 24+]
+ getCause(): IOException  [API 24+]
```

## UnsupportedEncodingException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnsupportedEncodingException()  [API 1+]
+ UnsupportedEncodingException(String)  [API 1+]
```

## WriteAbortedException — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- WriteAbortedException(String, Exception)  [API 1+]
+ detail [field]  [API 1+, deprecated in 34]
- getCause(): Throwable  [API 1+]
- getMessage(): String  [API 1+]
```

## Writer — TeaVM class library; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ Writer()  [API 1+]
+ Writer(Object)  [API 1+]
+ append(CharSequence): Writer  [API 1+]
+ append(CharSequence, int, int): Writer  [API 1+]
+ append(char): Writer  [API 1+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ lock [field]  [API 1+]
+ nullWriter(): Writer [static]  [API 33+]
+ write(String): void  [API 1+]
+ write(String, int, int): void  [API 1+]
+ write(char[]): void  [API 1+]
+ write(char[], int, int): void  [API 1+]
+ write(int): void  [API 1+]
```


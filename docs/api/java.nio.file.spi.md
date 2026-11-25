# java.nio.file.spi

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## FileSystemProvider — TeaVM class library; API 26+ (20 +, 0 *, 0 ~, 9 -)

```
+ FileSystemProvider()  [API 26+]
+ checkAccess(Path, AccessMode[]): void  [API 26+]
+ copy(Path, Path, CopyOption[]): void  [API 26+]
+ createDirectory(Path, FileAttribute[]): void  [API 26+]
- createLink(Path, Path): void  [API 26+]
- createSymbolicLink(Path, Path, FileAttribute[]): void  [API 26+]
+ delete(Path): void  [API 26+]
+ deleteIfExists(Path): boolean  [API 26+]
- getFileAttributeView(Path, Class, LinkOption[]): FileAttributeView  [API 26+]
- getFileStore(Path): FileStore  [API 26+]
+ getFileSystem(URI): FileSystem  [API 26+]
+ getPath(URI): Path  [API 26+]
+ getScheme(): String  [API 26+]
+ installedProviders(): List [static]  [API 26+]
+ isHidden(Path): boolean  [API 26+]
+ isSameFile(Path, Path): boolean  [API 26+]
+ move(Path, Path, CopyOption[]): void  [API 26+]
- newAsynchronousFileChannel(Path, Set, ExecutorService, FileAttribute[]): AsynchronousFileChannel  [API 26+]
- newByteChannel(Path, Set, FileAttribute[]): SeekableByteChannel  [API 26+]
+ newDirectoryStream(Path, DirectoryStream.Filter): DirectoryStream  [API 26+]
- newFileChannel(Path, Set, FileAttribute[]): FileChannel  [API 26+]
+ newFileSystem(Path, Map): FileSystem  [API 26+]
+ newFileSystem(URI, Map): FileSystem  [API 26+]
+ newInputStream(Path, OpenOption[]): InputStream  [API 26+]
+ newOutputStream(Path, OpenOption[]): OutputStream  [API 26+]
+ readAttributes(Path, Class, LinkOption[]): BasicFileAttributes  [API 26+]
- readAttributes(Path, String, LinkOption[]): Map  [API 26+]
+ readSymbolicLink(Path): Path  [API 26+]
- setAttribute(Path, String, Object, LinkOption[]): void  [API 26+]
```

## FileTypeDetector — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ FileTypeDetector()  [API 26+]
+ probeContentType(Path): String  [API 26+]
```


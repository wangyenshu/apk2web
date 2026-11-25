# java.nio.file

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AccessDeniedException — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ AccessDeniedException(String)  [API 26+]
+ AccessDeniedException(String, String, String)  [API 26+]
```

## AccessMode — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ EXECUTE [field]  [API 26+]
+ READ [field]  [API 26+]
+ WRITE [field]  [API 26+]
+ valueOf(String): AccessMode [static]  [API 26+]
+ values(): AccessMode[] [static]  [API 26+]
```

## AtomicMoveNotSupportedException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ AtomicMoveNotSupportedException(String, String, String)  [API 26+]
```

## ClosedDirectoryStreamException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ ClosedDirectoryStreamException()  [API 26+]
```

## ClosedFileSystemException — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ ClosedFileSystemException()  [API 26+]
```

## ClosedWatchServiceException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ ClosedWatchServiceException()  [API 26+]
```

## CopyOption — TeaVM class library; API 26+ (0 +, 0 *, 0 ~, 0 -)

## DirectoryIteratorException — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ DirectoryIteratorException(IOException)  [API 26+]
+ getCause(): IOException  [API 26+]
```

## DirectoryNotEmptyException — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ DirectoryNotEmptyException(String)  [API 26+]
```

## DirectoryStream — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ iterator(): Iterator  [API 26+]
```

## DirectoryStream.Filter — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(Object): boolean  [API 26+]
```

## FileAlreadyExistsException — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ FileAlreadyExistsException(String)  [API 26+]
+ FileAlreadyExistsException(String, String, String)  [API 26+]
```

## FileStore — declaration; API 26+ (10 +, 0 *, 0 ~, 2 -)

```
- FileStore()  [API 26+]
+ getAttribute(String): Object  [API 26+]
- getBlockSize(): long  [API 33+]
+ getFileStoreAttributeView(Class): FileStoreAttributeView  [API 26+]
+ getTotalSpace(): long  [API 26+]
+ getUnallocatedSpace(): long  [API 26+]
+ getUsableSpace(): long  [API 26+]
+ isReadOnly(): boolean  [API 26+]
+ name(): String  [API 26+]
+ supportsFileAttributeView(Class): boolean  [API 26+]
+ supportsFileAttributeView(String): boolean  [API 26+]
+ type(): String  [API 26+]
```

## FileSystem — TeaVM class library; API 26+ (9 +, 0 *, 0 ~, 4 -)

```
+ FileSystem()  [API 26+]
+ close(): void  [API 26+]
- getFileStores(): Iterable  [API 26+]
+ getPath(String, String[]): Path  [API 26+]
- getPathMatcher(String): PathMatcher  [API 26+]
+ getRootDirectories(): Iterable  [API 26+]
+ getSeparator(): String  [API 26+]
- getUserPrincipalLookupService(): UserPrincipalLookupService  [API 26+]
+ isOpen(): boolean  [API 26+]
+ isReadOnly(): boolean  [API 26+]
- newWatchService(): WatchService  [API 26+]
+ provider(): FileSystemProvider  [API 26+]
+ supportedFileAttributeViews(): Set  [API 26+]
```

## FileSystemAlreadyExistsException — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ FileSystemAlreadyExistsException()  [API 26+]
+ FileSystemAlreadyExistsException(String)  [API 26+]
```

## FileSystemException — TeaVM class library; API 26+ (6 +, 0 *, 0 ~, 0 -)

```
+ FileSystemException(String)  [API 26+]
+ FileSystemException(String, String, String)  [API 26+]
+ getFile(): String  [API 26+]
+ getMessage(): String  [API 26+]
+ getOtherFile(): String  [API 26+]
+ getReason(): String  [API 26+]
```

## FileSystemLoopException — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ FileSystemLoopException(String)  [API 26+]
```

## FileSystemNotFoundException — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ FileSystemNotFoundException()  [API 26+]
+ FileSystemNotFoundException(String)  [API 26+]
```

## FileSystems — TeaVM class library; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
+ getDefault(): FileSystem [static]  [API 26+]
+ getFileSystem(URI): FileSystem [static]  [API 26+]
+ newFileSystem(Path, ClassLoader): FileSystem [static]  [API 26+]
+ newFileSystem(URI, Map): FileSystem [static]  [API 26+]
+ newFileSystem(URI, Map, ClassLoader): FileSystem [static]  [API 26+]
```

## FileVisitOption — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ FOLLOW_LINKS [field]  [API 26+]
+ valueOf(String): FileVisitOption [static]  [API 26+]
+ values(): FileVisitOption[] [static]  [API 26+]
```

## FileVisitResult — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ CONTINUE [field]  [API 26+]
+ SKIP_SIBLINGS [field]  [API 26+]
+ SKIP_SUBTREE [field]  [API 26+]
+ TERMINATE [field]  [API 26+]
+ valueOf(String): FileVisitResult [static]  [API 26+]
+ values(): FileVisitResult[] [static]  [API 26+]
```

## FileVisitor — TeaVM class library; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ postVisitDirectory(Object, IOException): FileVisitResult  [API 26+]
+ preVisitDirectory(Object, BasicFileAttributes): FileVisitResult  [API 26+]
+ visitFile(Object, BasicFileAttributes): FileVisitResult  [API 26+]
+ visitFileFailed(Object, IOException): FileVisitResult  [API 26+]
```

## Files — TeaVM class library; API 26+ (49 +, 0 *, 0 ~, 16 -)

```
+ copy(InputStream, Path, CopyOption[]): long [static]  [API 26+]
+ copy(Path, OutputStream): long [static]  [API 26+]
+ copy(Path, Path, CopyOption[]): Path [static]  [API 26+]
+ createDirectories(Path, FileAttribute[]): Path [static]  [API 26+]
+ createDirectory(Path, FileAttribute[]): Path [static]  [API 26+]
+ createFile(Path, FileAttribute[]): Path [static]  [API 26+]
- createLink(Path, Path): Path [static]  [API 26+]
- createSymbolicLink(Path, Path, FileAttribute[]): Path [static]  [API 26+]
+ createTempDirectory(Path, String, FileAttribute[]): Path [static]  [API 26+]
+ createTempDirectory(String, FileAttribute[]): Path [static]  [API 26+]
+ createTempFile(Path, String, String, FileAttribute[]): Path [static]  [API 26+]
+ createTempFile(String, String, FileAttribute[]): Path [static]  [API 26+]
+ delete(Path): void [static]  [API 26+]
+ deleteIfExists(Path): boolean [static]  [API 26+]
+ exists(Path, LinkOption[]): boolean [static]  [API 26+]
+ find(Path, int, BiPredicate, FileVisitOption[]): Stream [static]  [API 26+]
- getAttribute(Path, String, LinkOption[]): Object [static]  [API 26+]
- getFileAttributeView(Path, Class, LinkOption[]): FileAttributeView [static]  [API 26+]
- getFileStore(Path): FileStore [static]  [API 26+]
+ getLastModifiedTime(Path, LinkOption[]): FileTime [static]  [API 26+]
- getOwner(Path, LinkOption[]): UserPrincipal [static]  [API 26+]
- getPosixFilePermissions(Path, LinkOption[]): Set [static]  [API 26+]
+ isDirectory(Path, LinkOption[]): boolean [static]  [API 26+]
+ isExecutable(Path): boolean [static]  [API 26+]
+ isHidden(Path): boolean [static]  [API 26+]
+ isReadable(Path): boolean [static]  [API 26+]
+ isRegularFile(Path, LinkOption[]): boolean [static]  [API 26+]
+ isSameFile(Path, Path): boolean [static]  [API 26+]
+ isSymbolicLink(Path): boolean [static]  [API 26+]
+ isWritable(Path): boolean [static]  [API 26+]
+ lines(Path): Stream [static]  [API 26+]
+ lines(Path, Charset): Stream [static]  [API 26+]
+ list(Path): Stream [static]  [API 26+]
+ move(Path, Path, CopyOption[]): Path [static]  [API 26+]
+ newBufferedReader(Path): BufferedReader [static]  [API 26+]
+ newBufferedReader(Path, Charset): BufferedReader [static]  [API 26+]
+ newBufferedWriter(Path, Charset, OpenOption[]): BufferedWriter [static]  [API 26+]
+ newBufferedWriter(Path, OpenOption[]): BufferedWriter [static]  [API 26+]
- newByteChannel(Path, OpenOption[]): SeekableByteChannel [static]  [API 26+]
- newByteChannel(Path, Set, FileAttribute[]): SeekableByteChannel [static]  [API 26+]
+ newDirectoryStream(Path): DirectoryStream [static]  [API 26+]
+ newDirectoryStream(Path, DirectoryStream.Filter): DirectoryStream [static]  [API 26+]
- newDirectoryStream(Path, String): DirectoryStream [static]  [API 26+]
+ newInputStream(Path, OpenOption[]): InputStream [static]  [API 26+]
+ newOutputStream(Path, OpenOption[]): OutputStream [static]  [API 26+]
+ notExists(Path, LinkOption[]): boolean [static]  [API 26+]
- probeContentType(Path): String [static]  [API 26+]
+ readAllBytes(Path): byte[] [static]  [API 26+]
+ readAllLines(Path): List [static]  [API 26+]
+ readAllLines(Path, Charset): List [static]  [API 26+]
+ readAttributes(Path, Class, LinkOption[]): BasicFileAttributes [static]  [API 26+]
- readAttributes(Path, String, LinkOption[]): Map [static]  [API 26+]
+ readSymbolicLink(Path): Path [static]  [API 26+]
- setAttribute(Path, String, Object, LinkOption[]): Path [static]  [API 26+]
- setLastModifiedTime(Path, FileTime): Path [static]  [API 26+]
- setOwner(Path, UserPrincipal): Path [static]  [API 26+]
- setPosixFilePermissions(Path, Set): Path [static]  [API 26+]
+ size(Path): long [static]  [API 26+]
+ walk(Path, FileVisitOption[]): Stream [static]  [API 26+]
+ walk(Path, int, FileVisitOption[]): Stream [static]  [API 26+]
+ walkFileTree(Path, FileVisitor): Path [static]  [API 26+]
+ walkFileTree(Path, Set, int, FileVisitor): Path [static]  [API 26+]
+ write(Path, Iterable, Charset, OpenOption[]): Path [static]  [API 26+]
+ write(Path, Iterable, OpenOption[]): Path [static]  [API 26+]
+ write(Path, byte[], OpenOption[]): Path [static]  [API 26+]
```

## InvalidPathException — TeaVM class library; API 26+ (6 +, 0 *, 0 ~, 0 -)

```
+ InvalidPathException(String, String)  [API 26+]
+ InvalidPathException(String, String, int)  [API 26+]
+ getIndex(): int  [API 26+]
+ getInput(): String  [API 26+]
+ getMessage(): String  [API 26+]
+ getReason(): String  [API 26+]
```

## LinkOption — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ NOFOLLOW_LINKS [field]  [API 26+]
+ valueOf(String): LinkOption [static]  [API 26+]
+ values(): LinkOption[] [static]  [API 26+]
```

## LinkPermission — declaration; API 26+ (0 +, 0 *, 0 ~, 2 -)

```
- LinkPermission(String)  [API 26+]
- LinkPermission(String, String)  [API 26+]
```

## NoSuchFileException — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoSuchFileException(String)  [API 26+]
+ NoSuchFileException(String, String, String)  [API 26+]
```

## NotDirectoryException — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ NotDirectoryException(String)  [API 26+]
```

## NotLinkException — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ NotLinkException(String)  [API 26+]
+ NotLinkException(String, String, String)  [API 26+]
```

## OpenOption — TeaVM class library; API 26+ (0 +, 0 *, 0 ~, 0 -)

## Path — TeaVM class library; API 26+ (29 +, 0 *, 0 ~, 2 -)

```
+ compareTo(Path): int  [API 26+]
+ endsWith(Path): boolean  [API 26+]
+ endsWith(String): boolean  [API 26+]
+ equals(Object): boolean  [API 26+]
+ getFileName(): Path  [API 26+]
+ getFileSystem(): FileSystem  [API 26+]
+ getName(int): Path  [API 26+]
+ getNameCount(): int  [API 26+]
+ getParent(): Path  [API 26+]
+ getRoot(): Path  [API 26+]
+ hashCode(): int  [API 26+]
+ isAbsolute(): boolean  [API 26+]
+ iterator(): Iterator  [API 26+]
+ normalize(): Path  [API 26+]
+ of(String, String[]): Path [static]  [API 34+]
+ of(URI): Path [static]  [API 34+]
- register(WatchService, WatchEvent.Kind[]): WatchKey  [API 26+]
- register(WatchService, WatchEvent.Kind[], WatchEvent.Modifier[]): WatchKey  [API 26+]
+ relativize(Path): Path  [API 26+]
+ resolve(Path): Path  [API 26+]
+ resolve(String): Path  [API 26+]
+ resolveSibling(Path): Path  [API 26+]
+ resolveSibling(String): Path  [API 26+]
+ startsWith(Path): boolean  [API 26+]
+ startsWith(String): boolean  [API 26+]
+ subpath(int, int): Path  [API 26+]
+ toAbsolutePath(): Path  [API 26+]
+ toFile(): File  [API 26+]
+ toRealPath(LinkOption[]): Path  [API 26+]
+ toString(): String  [API 26+]
+ toUri(): URI  [API 26+]
```

## PathMatcher — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ matches(Path): boolean  [API 26+]
```

## Paths — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ get(String, String[]): Path [static]  [API 26+]
+ get(URI): Path [static]  [API 26+]
```

## ProviderMismatchException — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ ProviderMismatchException()  [API 26+]
+ ProviderMismatchException(String)  [API 26+]
```

## ProviderNotFoundException — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ ProviderNotFoundException()  [API 26+]
+ ProviderNotFoundException(String)  [API 26+]
```

## ReadOnlyFileSystemException — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ ReadOnlyFileSystemException()  [API 26+]
```

## SecureDirectoryStream — declaration; API 26+ (7 +, 0 *, 0 ~, 0 -)

```
+ deleteDirectory(Object): void  [API 26+]
+ deleteFile(Object): void  [API 26+]
+ getFileAttributeView(Class): FileAttributeView  [API 26+]
+ getFileAttributeView(Object, Class, LinkOption[]): FileAttributeView  [API 26+]
+ move(Object, SecureDirectoryStream, Object): void  [API 26+]
+ newByteChannel(Object, Set, FileAttribute[]): SeekableByteChannel  [API 26+]
+ newDirectoryStream(Object, LinkOption[]): SecureDirectoryStream  [API 26+]
```

## SimpleFileVisitor — TeaVM class library; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
+ SimpleFileVisitor()  [API 26+]
+ postVisitDirectory(Object, IOException): FileVisitResult  [API 26+]
+ preVisitDirectory(Object, BasicFileAttributes): FileVisitResult  [API 26+]
+ visitFile(Object, BasicFileAttributes): FileVisitResult  [API 26+]
+ visitFileFailed(Object, IOException): FileVisitResult  [API 26+]
```

## StandardCopyOption — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ ATOMIC_MOVE [field]  [API 26+]
+ COPY_ATTRIBUTES [field]  [API 26+]
+ REPLACE_EXISTING [field]  [API 26+]
+ valueOf(String): StandardCopyOption [static]  [API 26+]
+ values(): StandardCopyOption[] [static]  [API 26+]
```

## StandardOpenOption — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ APPEND [field]  [API 26+]
+ CREATE [field]  [API 26+]
+ CREATE_NEW [field]  [API 26+]
+ DELETE_ON_CLOSE [field]  [API 26+]
+ DSYNC [field]  [API 26+]
+ READ [field]  [API 26+]
+ SPARSE [field]  [API 26+]
+ SYNC [field]  [API 26+]
+ TRUNCATE_EXISTING [field]  [API 26+]
+ WRITE [field]  [API 26+]
+ valueOf(String): StandardOpenOption [static]  [API 26+]
+ values(): StandardOpenOption[] [static]  [API 26+]
```

## StandardWatchEventKinds — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

```
- ENTRY_CREATE [field]  [API 26+]
- ENTRY_DELETE [field]  [API 26+]
- ENTRY_MODIFY [field]  [API 26+]
- OVERFLOW [field]  [API 26+]
```

## WatchEvent — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ context(): Object  [API 26+]
+ count(): int  [API 26+]
+ kind(): WatchEvent.Kind  [API 26+]
```

## WatchEvent.Kind — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ name(): String  [API 26+]
+ type(): Class  [API 26+]
```

## WatchEvent.Modifier — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ name(): String  [API 26+]
```

## WatchKey — declaration; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
+ cancel(): void  [API 26+]
+ isValid(): boolean  [API 26+]
+ pollEvents(): List  [API 26+]
+ reset(): boolean  [API 26+]
+ watchable(): Watchable  [API 26+]
```

## WatchService — declaration; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 26+]
+ poll(): WatchKey  [API 26+]
+ poll(long, TimeUnit): WatchKey  [API 26+]
+ take(): WatchKey  [API 26+]
```

## Watchable — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ register(WatchService, WatchEvent.Kind[]): WatchKey  [API 26+]
+ register(WatchService, WatchEvent.Kind[], WatchEvent.Modifier[]): WatchKey  [API 26+]
```


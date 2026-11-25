# java.nio.file.attribute

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AclEntry — declaration; API 26+ (0 +, 0 *, 0 ~, 9 -)

```
- equals(Object): boolean  [API 26+]
- flags(): Set  [API 26+]
- hashCode(): int  [API 26+]
- newBuilder(): AclEntry.Builder [static]  [API 26+]
- newBuilder(AclEntry): AclEntry.Builder [static]  [API 26+]
- permissions(): Set  [API 26+]
- principal(): UserPrincipal  [API 26+]
- toString(): String  [API 26+]
- type(): AclEntryType  [API 26+]
```

## AclEntry.Builder — declaration; API 26+ (0 +, 0 *, 0 ~, 7 -)

```
- build(): AclEntry  [API 26+]
- setFlags(AclEntryFlag[]): AclEntry.Builder  [API 26+]
- setFlags(Set): AclEntry.Builder  [API 26+]
- setPermissions(AclEntryPermission[]): AclEntry.Builder  [API 26+]
- setPermissions(Set): AclEntry.Builder  [API 26+]
- setPrincipal(UserPrincipal): AclEntry.Builder  [API 26+]
- setType(AclEntryType): AclEntry.Builder  [API 26+]
```

## AclEntryFlag — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ DIRECTORY_INHERIT [field]  [API 26+]
+ FILE_INHERIT [field]  [API 26+]
+ INHERIT_ONLY [field]  [API 26+]
+ NO_PROPAGATE_INHERIT [field]  [API 26+]
+ valueOf(String): AclEntryFlag [static]  [API 26+]
+ values(): AclEntryFlag[] [static]  [API 26+]
```

## AclEntryPermission — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
- ADD_FILE [field]  [API 26+]
- ADD_SUBDIRECTORY [field]  [API 26+]
+ APPEND_DATA [field]  [API 26+]
+ DELETE [field]  [API 26+]
+ DELETE_CHILD [field]  [API 26+]
+ EXECUTE [field]  [API 26+]
- LIST_DIRECTORY [field]  [API 26+]
+ READ_ACL [field]  [API 26+]
+ READ_ATTRIBUTES [field]  [API 26+]
+ READ_DATA [field]  [API 26+]
+ READ_NAMED_ATTRS [field]  [API 26+]
+ SYNCHRONIZE [field]  [API 26+]
+ WRITE_ACL [field]  [API 26+]
+ WRITE_ATTRIBUTES [field]  [API 26+]
+ WRITE_DATA [field]  [API 26+]
+ WRITE_NAMED_ATTRS [field]  [API 26+]
+ WRITE_OWNER [field]  [API 26+]
+ valueOf(String): AclEntryPermission [static]  [API 26+]
+ values(): AclEntryPermission[] [static]  [API 26+]
```

## AclEntryType — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ ALARM [field]  [API 26+]
+ ALLOW [field]  [API 26+]
+ AUDIT [field]  [API 26+]
+ DENY [field]  [API 26+]
+ valueOf(String): AclEntryType [static]  [API 26+]
+ values(): AclEntryType[] [static]  [API 26+]
```

## AclFileAttributeView — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ getAcl(): List  [API 26+]
+ name(): String  [API 26+]
+ setAcl(List): void  [API 26+]
```

## AttributeView — declaration; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ name(): String  [API 26+]
```

## BasicFileAttributeView — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ name(): String  [API 26+]
+ readAttributes(): BasicFileAttributes  [API 26+]
+ setTimes(FileTime, FileTime, FileTime): void  [API 26+]
```

## BasicFileAttributes — TeaVM class library; API 26+ (9 +, 0 *, 0 ~, 0 -)

```
+ creationTime(): FileTime  [API 26+]
+ fileKey(): Object  [API 26+]
+ isDirectory(): boolean  [API 26+]
+ isOther(): boolean  [API 26+]
+ isRegularFile(): boolean  [API 26+]
+ isSymbolicLink(): boolean  [API 26+]
+ lastAccessTime(): FileTime  [API 26+]
+ lastModifiedTime(): FileTime  [API 26+]
+ size(): long  [API 26+]
```

## DosFileAttributeView — declaration; API 26+ (6 +, 0 *, 0 ~, 0 -)

```
+ name(): String  [API 26+]
+ readAttributes(): DosFileAttributes  [API 26+]
+ setArchive(boolean): void  [API 26+]
+ setHidden(boolean): void  [API 26+]
+ setReadOnly(boolean): void  [API 26+]
+ setSystem(boolean): void  [API 26+]
```

## DosFileAttributes — declaration; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ isArchive(): boolean  [API 26+]
+ isHidden(): boolean  [API 26+]
+ isReadOnly(): boolean  [API 26+]
+ isSystem(): boolean  [API 26+]
```

## FileAttribute — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ name(): String  [API 26+]
+ value(): Object  [API 26+]
```

## FileAttributeView — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## FileOwnerAttributeView — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ getOwner(): UserPrincipal  [API 26+]
+ name(): String  [API 26+]
+ setOwner(UserPrincipal): void  [API 26+]
```

## FileStoreAttributeView — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## FileTime — TeaVM class library; API 26+ (10 +, 0 *, 0 ~, 0 -)

```
+ compareTo(FileTime): int  [API 26+]
+ equals(Object): boolean  [API 26+]
+ from(Instant): FileTime [static]  [API 26+]
+ from(long, TimeUnit): FileTime [static]  [API 26+]
+ fromMillis(long): FileTime [static]  [API 26+]
+ hashCode(): int  [API 26+]
+ to(TimeUnit): long  [API 26+]
+ toInstant(): Instant  [API 26+]
+ toMillis(): long  [API 26+]
+ toString(): String  [API 26+]
```

## GroupPrincipal — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## PosixFileAttributeView — declaration; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ name(): String  [API 26+]
+ readAttributes(): PosixFileAttributes  [API 26+]
+ setGroup(GroupPrincipal): void  [API 26+]
+ setPermissions(Set): void  [API 26+]
```

## PosixFileAttributes — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ group(): GroupPrincipal  [API 26+]
+ owner(): UserPrincipal  [API 26+]
+ permissions(): Set  [API 26+]
```

## PosixFilePermission — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ GROUP_EXECUTE [field]  [API 26+]
+ GROUP_READ [field]  [API 26+]
+ GROUP_WRITE [field]  [API 26+]
+ OTHERS_EXECUTE [field]  [API 26+]
+ OTHERS_READ [field]  [API 26+]
+ OTHERS_WRITE [field]  [API 26+]
+ OWNER_EXECUTE [field]  [API 26+]
+ OWNER_READ [field]  [API 26+]
+ OWNER_WRITE [field]  [API 26+]
+ valueOf(String): PosixFilePermission [static]  [API 26+]
+ values(): PosixFilePermission[] [static]  [API 26+]
```

## PosixFilePermissions — declaration; API 26+ (0 +, 0 *, 0 ~, 3 -)

```
- asFileAttribute(Set): FileAttribute [static]  [API 26+]
- fromString(String): Set [static]  [API 26+]
- toString(Set): String [static]  [API 26+]
```

## UserDefinedFileAttributeView — declaration; API 26+ (6 +, 0 *, 0 ~, 0 -)

```
+ delete(String): void  [API 26+]
+ list(): List  [API 26+]
+ name(): String  [API 26+]
+ read(String, ByteBuffer): int  [API 26+]
+ size(String): int  [API 26+]
+ write(String, ByteBuffer): int  [API 26+]
```

## UserPrincipal — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## UserPrincipalLookupService — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ UserPrincipalLookupService()  [API 26+]
+ lookupPrincipalByGroupName(String): GroupPrincipal  [API 26+]
+ lookupPrincipalByName(String): UserPrincipal  [API 26+]
```

## UserPrincipalNotFoundException — declaration; API 26+ (1 +, 0 *, 0 ~, 1 -)

```
+ UserPrincipalNotFoundException(String)  [API 26+]
- getName(): String  [API 26+]
```


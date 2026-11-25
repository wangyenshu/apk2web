# android.app.backup

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BackupAgent — declaration; API 8+ (9 +, 0 *, 0 ~, 2 -)

```
- BackupAgent()  [API 8+]
- fullBackupFile(File, FullBackupDataOutput): void  [API 14+]
+ onBackup(ParcelFileDescriptor, BackupDataOutput, ParcelFileDescriptor): void  [API 8+]
+ onCreate(): void  [API 8+]
+ onDestroy(): void  [API 8+]
+ onFullBackup(FullBackupDataOutput): void  [API 14+]
+ onQuotaExceeded(long, long): void  [API 24+]
+ onRestore(BackupDataInput, int, ParcelFileDescriptor): void  [API 8+]
+ onRestore(BackupDataInput, long, ParcelFileDescriptor): void  [API 28+]
+ onRestoreFile(ParcelFileDescriptor, long, File, int, long, long): void  [API 14+]
+ onRestoreFinished(): void  [API 21+]
```

## BackupAgentHelper — declaration; API 8+ (0 +, 0 *, 0 ~, 4 -)

```
- BackupAgentHelper()  [API 8+]
- addHelper(String, BackupHelper): void  [API 8+]
- onBackup(ParcelFileDescriptor, BackupDataOutput, ParcelFileDescriptor): void  [API 8+]
- onRestore(BackupDataInput, int, ParcelFileDescriptor): void  [API 8+]
```

## BackupDataInput — declaration; API 8+ (0 +, 0 *, 0 ~, 6 -)

```
- finalize(): void  [API 8+]
- getDataSize(): int  [API 8+]
- getKey(): String  [API 8+]
- readEntityData(byte[], int, int): int  [API 8+]
- readNextHeader(): boolean  [API 8+]
- skipEntityData(): void  [API 8+]
```

## BackupDataInputStream — declaration; API 8+ (0 +, 0 *, 0 ~, 5 -)

```
- getKey(): String  [API 8+]
- read(): int  [API 8+]
- read(byte[]): int  [API 8+]
- read(byte[], int, int): int  [API 8+]
- size(): int  [API 8+]
```

## BackupDataOutput — declaration; API 8+ (0 +, 0 *, 0 ~, 5 -)

```
- finalize(): void  [API 8+]
- getQuota(): long  [API 26+]
- getTransportFlags(): int  [API 28+]
- writeEntityData(byte[], int): int  [API 8+]
- writeEntityHeader(String, int): int  [API 8+]
```

## BackupHelper — declaration; API 8+ (3 +, 0 *, 0 ~, 0 -)

```
+ performBackup(ParcelFileDescriptor, BackupDataOutput, ParcelFileDescriptor): void  [API 8+]
+ restoreEntity(BackupDataInputStream): void  [API 8+]
+ writeNewStateDescription(ParcelFileDescriptor): void  [API 8+]
```

## BackupManager — declaration; API 8+ (0 +, 0 *, 0 ~, 5 -)

```
- BackupManager(Context)  [API 8+]
- dataChanged(): void  [API 8+]
- dataChanged(String): void [static]  [API 8+]
- getUserForAncestralSerialNumber(long): UserHandle  [API 29+]
- requestRestore(RestoreObserver): int  [API 8+, deprecated in 28]
```

## FileBackupHelper — declaration; API 8+ (0 +, 0 *, 0 ~, 4 -)

```
- FileBackupHelper(Context, String[])  [API 8+]
- performBackup(ParcelFileDescriptor, BackupDataOutput, ParcelFileDescriptor): void  [API 8+]
- restoreEntity(BackupDataInputStream): void  [API 8+]
- writeNewStateDescription(ParcelFileDescriptor): void  [API 8+]
```

## FullBackupDataOutput — declaration; API 14+ (0 +, 0 *, 0 ~, 2 -)

```
- getQuota(): long  [API 26+]
- getTransportFlags(): int  [API 28+]
```

## RestoreObserver — declaration; API 8+ (1 +, 0 *, 0 ~, 3 -)

```
- RestoreObserver()  [API 8+]
+ onUpdate(int, String): void  [API 8+]
- restoreFinished(int): void  [API 8+]
- restoreStarting(int): void  [API 8+]
```

## SharedPreferencesBackupHelper — declaration; API 8+ (0 +, 0 *, 0 ~, 4 -)

```
- SharedPreferencesBackupHelper(Context, String[])  [API 8+]
- performBackup(ParcelFileDescriptor, BackupDataOutput, ParcelFileDescriptor): void  [API 8+]
- restoreEntity(BackupDataInputStream): void  [API 8+]
- writeNewStateDescription(ParcelFileDescriptor): void  [API 8+]
```


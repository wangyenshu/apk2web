# android.app.appsearch.observer

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DocumentChangeInfo — declaration; API 33+ (0 +, 0 *, 0 ~, 9 -)

```
- DocumentChangeInfo(String, String, String, String, Set)  [API 33+]
- equals(Object): boolean  [API 33+]
- getChangedDocumentIds(): Set  [API 33+]
- getDatabaseName(): String  [API 33+]
- getNamespace(): String  [API 33+]
- getPackageName(): String  [API 33+]
- getSchemaName(): String  [API 33+]
- hashCode(): int  [API 33+]
- toString(): String  [API 33+]
```

## ObserverCallback — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ onDocumentChanged(DocumentChangeInfo): void  [API 33+]
+ onSchemaChanged(SchemaChangeInfo): void  [API 33+]
```

## ObserverSpec — declaration; API 33+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 33+]
- getFilterSchemas(): Set  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## ObserverSpec.Builder — declaration; API 33+ (0 +, 0 *, 0 ~, 4 -)

```
- Builder()  [API 33+]
- addFilterSchemas(Collection): ObserverSpec.Builder  [API 33+]
- addFilterSchemas(String[]): ObserverSpec.Builder  [API 33+]
- build(): ObserverSpec  [API 33+]
```

## SchemaChangeInfo — declaration; API 33+ (0 +, 7 *, 0 ~, 0 -)

```
* SchemaChangeInfo(String, String, Set)  [API 33+]
* equals(Object): boolean  [API 33+]
* getChangedSchemaNames(): Set  [API 33+]
* getDatabaseName(): String  [API 33+]
* getPackageName(): String  [API 33+]
* hashCode(): int  [API 33+]
* toString(): String  [API 33+]
```


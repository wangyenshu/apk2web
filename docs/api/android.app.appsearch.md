# android.app.appsearch

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AppSearchBatchResult — declaration; API 31+ (0 +, 0 *, 0 ~, 5 -)

```
- getAll(): Map  [API 31+]
- getFailures(): Map  [API 31+]
- getSuccesses(): Map  [API 31+]
- isSuccess(): boolean  [API 31+]
- toString(): String  [API 31+]
```

## AppSearchBatchResult.Builder — declaration; API 31+ (0 +, 0 *, 0 ~, 5 -)

```
- Builder()  [API 31+]
- build(): AppSearchBatchResult  [API 31+]
- setFailure(Object, int, String): AppSearchBatchResult.Builder  [API 31+]
- setResult(Object, AppSearchResult): AppSearchBatchResult.Builder  [API 31+]
- setSuccess(Object, Object): AppSearchBatchResult.Builder  [API 31+]
```

## AppSearchManager — framework; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- createEnterpriseGlobalSearchSession(Executor, Consumer): void  [API 35+]
- createGlobalSearchSession(Executor, Consumer): void  [API 31+]
- createSearchSession(AppSearchManager.SearchContext, Executor, Consumer): void  [API 31+]
- getAppFunctionManager(): AppFunctionManager  [API 36+]
```

## AppSearchManager.SearchContext — framework; API 31+ (0 +, 1 *, 0 ~, 0 -)

```
* getDatabaseName(): String  [API 31+]
```

## AppSearchManager.SearchContext.Builder — framework; API 31+ (0 +, 2 *, 0 ~, 0 -)

```
* Builder(String)  [API 31+]
* build(): AppSearchManager.SearchContext  [API 31+]
```

## AppSearchResult — declaration; API 31+ (0 +, 0 *, 0 ~, 9 -)

```
- equals(Object): boolean  [API 31+]
- getErrorMessage(): String  [API 31+]
- getResultCode(): int  [API 31+]
- getResultValue(): Object  [API 31+]
- hashCode(): int  [API 31+]
- isSuccess(): boolean  [API 31+]
- newFailedResult(int, String): AppSearchResult [static]  [API 31+]
- newSuccessfulResult(Object): AppSearchResult [static]  [API 31+]
- toString(): String  [API 31+]
```

## AppSearchSchema — declaration; API 31+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getDescription(): String  [API 36+]
* getParentTypes(): List  [API 35+]
* getProperties(): List  [API 31+]
* getSchemaType(): String  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## AppSearchSchema.BooleanPropertyConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## AppSearchSchema.BooleanPropertyConfig.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 1 -)

```
- Builder(String)  [API 31+]
* build(): AppSearchSchema.BooleanPropertyConfig  [API 31+]
* setCardinality(int): AppSearchSchema.BooleanPropertyConfig.Builder  [API 31+]
* setDescription(String): AppSearchSchema.BooleanPropertyConfig.Builder  [API 36+]
```

## AppSearchSchema.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(String)  [API 31+]
* addParentType(String): AppSearchSchema.Builder  [API 35+]
* addProperty(AppSearchSchema.PropertyConfig): AppSearchSchema.Builder  [API 31+]
* build(): AppSearchSchema  [API 31+]
* setDescription(String): AppSearchSchema.Builder  [API 36+]
```

## AppSearchSchema.BytesPropertyConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## AppSearchSchema.BytesPropertyConfig.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 1 -)

```
- Builder(String)  [API 31+]
* build(): AppSearchSchema.BytesPropertyConfig  [API 31+]
* setCardinality(int): AppSearchSchema.BytesPropertyConfig.Builder  [API 31+]
* setDescription(String): AppSearchSchema.BytesPropertyConfig.Builder  [API 36+]
```

## AppSearchSchema.DocumentPropertyConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- getIndexableNestedProperties(): List  [API 35+]
- getSchemaType(): String  [API 31+]
- shouldIndexNestedProperties(): boolean  [API 31+]
```

## AppSearchSchema.DocumentPropertyConfig.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 6 -)

```
- Builder(String, String)  [API 31+]
- addIndexableNestedProperties(Collection): AppSearchSchema.DocumentPropertyConfig.Builder  [API 35+]
- addIndexableNestedProperties(String[]): AppSearchSchema.DocumentPropertyConfig.Builder  [API 35+]
- addIndexableNestedPropertyPaths(Collection): AppSearchSchema.DocumentPropertyConfig.Builder  [API 35+]
- addIndexableNestedPropertyPaths(PropertyPath[]): AppSearchSchema.DocumentPropertyConfig.Builder  [API 35+]
* build(): AppSearchSchema.DocumentPropertyConfig  [API 31+]
* setCardinality(int): AppSearchSchema.DocumentPropertyConfig.Builder  [API 31+]
* setDescription(String): AppSearchSchema.DocumentPropertyConfig.Builder  [API 36+]
- setShouldIndexNestedProperties(boolean): AppSearchSchema.DocumentPropertyConfig.Builder  [API 31+]
```

## AppSearchSchema.DoublePropertyConfig — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## AppSearchSchema.DoublePropertyConfig.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 1 -)

```
- Builder(String)  [API 31+]
* build(): AppSearchSchema.DoublePropertyConfig  [API 31+]
* setCardinality(int): AppSearchSchema.DoublePropertyConfig.Builder  [API 31+]
* setDescription(String): AppSearchSchema.DoublePropertyConfig.Builder  [API 36+]
```

## AppSearchSchema.EmbeddingPropertyConfig — declaration; API 36+ (0 +, 1 *, 0 ~, 0 -)

```
* getIndexingType(): int  [API 36+]
```

## AppSearchSchema.EmbeddingPropertyConfig.Builder — declaration; API 36+ (0 +, 4 *, 0 ~, 1 -)

```
- Builder(String)  [API 36+]
* build(): AppSearchSchema.EmbeddingPropertyConfig  [API 36+]
* setCardinality(int): AppSearchSchema.EmbeddingPropertyConfig.Builder  [API 36+]
* setDescription(String): AppSearchSchema.EmbeddingPropertyConfig.Builder  [API 36+]
* setIndexingType(int): AppSearchSchema.EmbeddingPropertyConfig.Builder  [API 36+]
```

## AppSearchSchema.LongPropertyConfig — declaration; API 31+ (0 +, 1 *, 0 ~, 0 -)

```
* getIndexingType(): int  [API 34+]
```

## AppSearchSchema.LongPropertyConfig.Builder — declaration; API 31+ (0 +, 4 *, 0 ~, 1 -)

```
- Builder(String)  [API 31+]
* build(): AppSearchSchema.LongPropertyConfig  [API 31+]
* setCardinality(int): AppSearchSchema.LongPropertyConfig.Builder  [API 31+]
* setDescription(String): AppSearchSchema.LongPropertyConfig.Builder  [API 36+]
* setIndexingType(int): AppSearchSchema.LongPropertyConfig.Builder  [API 34+]
```

## AppSearchSchema.PropertyConfig — declaration; API 31+ (0 +, 2 *, 0 ~, 4 -)

```
- equals(Object): boolean  [API 31+]
* getCardinality(): int  [API 31+]
* getDescription(): String  [API 36+]
- getName(): String  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
```

## AppSearchSchema.StringPropertyConfig — declaration; API 31+ (0 +, 3 *, 0 ~, 0 -)

```
* getIndexingType(): int  [API 31+]
* getJoinableValueType(): int  [API 34+]
* getTokenizerType(): int  [API 31+]
```

## AppSearchSchema.StringPropertyConfig.Builder — declaration; API 31+ (0 +, 6 *, 0 ~, 1 -)

```
- Builder(String)  [API 31+]
* build(): AppSearchSchema.StringPropertyConfig  [API 31+]
* setCardinality(int): AppSearchSchema.StringPropertyConfig.Builder  [API 31+]
* setDescription(String): AppSearchSchema.StringPropertyConfig.Builder  [API 36+]
* setIndexingType(int): AppSearchSchema.StringPropertyConfig.Builder  [API 31+]
* setJoinableValueType(int): AppSearchSchema.StringPropertyConfig.Builder  [API 34+]
* setTokenizerType(int): AppSearchSchema.StringPropertyConfig.Builder  [API 31+]
```

## AppSearchSession — declaration; API 31+ (0 +, 0 *, 0 ~, 12 -)

```
- close(): void  [API 31+]
- getByDocumentId(GetByDocumentIdRequest, Executor, BatchResultCallback): void  [API 31+]
- getNamespaces(Executor, Consumer): void  [API 31+]
- getSchema(Executor, Consumer): void  [API 31+]
- getStorageInfo(Executor, Consumer): void  [API 31+]
- put(PutDocumentsRequest, Executor, BatchResultCallback): void  [API 31+]
- remove(RemoveByDocumentIdRequest, Executor, BatchResultCallback): void  [API 31+]
- remove(String, SearchSpec, Executor, Consumer): void  [API 31+]
- reportUsage(ReportUsageRequest, Executor, Consumer): void  [API 31+]
- search(String, SearchSpec): SearchResults  [API 31+]
- searchSuggestion(String, SearchSuggestionSpec, Executor, Consumer): void  [API 34+]
- setSchema(SetSchemaRequest, Executor, Executor, Consumer): void  [API 31+]
```

## BatchResultCallback — declaration; API 31+ (2 +, 0 *, 0 ~, 0 -)

```
+ onResult(AppSearchBatchResult): void  [API 31+]
+ onSystemError(Throwable): void  [API 31+]
```

## EmbeddingVector — declaration; API 36+ (0 +, 3 *, 0 ~, 0 -)

```
* EmbeddingVector(float[], String)  [API 36+]
* getModelSignature(): String  [API 36+]
* getValues(): float[]  [API 36+]
```

## EnterpriseGlobalSearchSession — declaration; API 35+ (0 +, 0 *, 0 ~, 3 -)

```
- getByDocumentId(String, String, GetByDocumentIdRequest, Executor, BatchResultCallback): void  [API 35+]
- getSchema(String, String, Executor, Consumer): void  [API 35+]
- search(String, SearchSpec): SearchResults  [API 35+]
```

## GenericDocument — declaration; API 31+ (0 +, 10 *, 0 ~, 17 -)

```
- GenericDocument(GenericDocument)  [API 31+]
* equals(Object): boolean  [API 31+]
* getCreationTimestampMillis(): long  [API 31+]
* getId(): String  [API 31+]
- getMaxIndexedProperties(): int [static]  [API 31+]
* getNamespace(): String  [API 31+]
- getProperty(String): Object  [API 31+]
- getPropertyBoolean(String): boolean  [API 31+]
- getPropertyBooleanArray(String): boolean[]  [API 31+]
- getPropertyBytes(String): byte[]  [API 31+]
- getPropertyBytesArray(String): byte[][]  [API 31+]
- getPropertyDocument(String): GenericDocument  [API 31+]
- getPropertyDocumentArray(String): GenericDocument[]  [API 31+]
- getPropertyDouble(String): double  [API 31+]
- getPropertyDoubleArray(String): double[]  [API 31+]
- getPropertyEmbedding(String): EmbeddingVector  [API 36+]
- getPropertyEmbeddingArray(String): EmbeddingVector[]  [API 36+]
- getPropertyLong(String): long  [API 31+]
- getPropertyLongArray(String): long[]  [API 31+]
* getPropertyNames(): Set  [API 31+]
- getPropertyString(String): String  [API 31+]
- getPropertyStringArray(String): String[]  [API 31+]
* getSchemaType(): String  [API 31+]
* getScore(): int  [API 31+]
* getTtlMillis(): long  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
```

## GenericDocument.Builder — declaration; API 31+ (0 +, 9 *, 0 ~, 8 -)

```
* Builder(GenericDocument)  [API 35+]
* Builder(String, String, String)  [API 31+]
* build(): GenericDocument  [API 31+]
- clearProperty(String): GenericDocument.Builder  [API 35+]
* setCreationTimestampMillis(long): GenericDocument.Builder  [API 31+]
* setId(String): GenericDocument.Builder  [API 35+]
* setNamespace(String): GenericDocument.Builder  [API 35+]
- setPropertyBoolean(String, boolean[]): GenericDocument.Builder  [API 31+]
- setPropertyBytes(String, byte[][]): GenericDocument.Builder  [API 31+]
- setPropertyDocument(String, GenericDocument[]): GenericDocument.Builder  [API 31+]
- setPropertyDouble(String, double[]): GenericDocument.Builder  [API 31+]
- setPropertyEmbedding(String, EmbeddingVector[]): GenericDocument.Builder  [API 36+]
- setPropertyLong(String, long[]): GenericDocument.Builder  [API 31+]
- setPropertyString(String, String[]): GenericDocument.Builder  [API 31+]
* setSchemaType(String): GenericDocument.Builder  [API 35+]
* setScore(int): GenericDocument.Builder  [API 31+]
* setTtlMillis(long): GenericDocument.Builder  [API 31+]
```

## GetByDocumentIdRequest — declaration; API 31+ (0 +, 1 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 31+]
- getIds(): Set  [API 31+]
* getNamespace(): String  [API 31+]
- getProjectionPaths(): Map  [API 34+]
- getProjections(): Map  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## GetByDocumentIdRequest.Builder — declaration; API 31+ (0 +, 2 *, 0 ~, 4 -)

```
* Builder(String)  [API 31+]
- addIds(Collection): GetByDocumentIdRequest.Builder  [API 31+]
- addIds(String[]): GetByDocumentIdRequest.Builder  [API 31+]
- addProjection(String, Collection): GetByDocumentIdRequest.Builder  [API 31+]
- addProjectionPaths(String, Collection): GetByDocumentIdRequest.Builder  [API 34+]
* build(): GetByDocumentIdRequest  [API 31+]
```

## GetSchemaResponse — declaration; API 31+ (0 +, 1 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 31+]
- getPubliclyVisibleSchemas(): Map  [API 35+]
- getRequiredPermissionsForSchemaTypeVisibility(): Map  [API 33+]
- getSchemaTypesNotDisplayedBySystem(): Set  [API 33+]
- getSchemaTypesVisibleToConfigs(): Map  [API 35+]
- getSchemaTypesVisibleToPackages(): Map  [API 33+]
- getSchemas(): Set  [API 31+]
* getVersion(): int  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## GetSchemaResponse.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 6 -)

```
* Builder()  [API 31+]
- addSchema(AppSearchSchema): GetSchemaResponse.Builder  [API 31+]
- addSchemaTypeNotDisplayedBySystem(String): GetSchemaResponse.Builder  [API 33+]
* build(): GetSchemaResponse  [API 31+]
- setPubliclyVisibleSchema(String, PackageIdentifier): GetSchemaResponse.Builder  [API 35+]
- setRequiredPermissionsForSchemaTypeVisibility(String, Set): GetSchemaResponse.Builder  [API 33+]
- setSchemaTypeVisibleToConfigs(String, Set): GetSchemaResponse.Builder  [API 35+]
- setSchemaTypeVisibleToPackages(String, Set): GetSchemaResponse.Builder  [API 33+]
* setVersion(int): GetSchemaResponse.Builder  [API 31+]
```

## GlobalSearchSession — declaration; API 31+ (0 +, 0 *, 0 ~, 7 -)

```
- close(): void  [API 31+]
- getByDocumentId(String, String, GetByDocumentIdRequest, Executor, BatchResultCallback): void  [API 33+]
- getSchema(String, String, Executor, Consumer): void  [API 33+]
- registerObserverCallback(String, ObserverSpec, Executor, ObserverCallback): void  [API 33+]
- reportSystemUsage(ReportSystemUsageRequest, Executor, Consumer): void  [API 31+]
- search(String, SearchSpec): SearchResults  [API 31+]
- unregisterObserverCallback(String, ObserverCallback): void  [API 33+]
```

## JoinSpec — declaration; API 34+ (0 +, 3 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 34+]
* getAggregationScoringStrategy(): int  [API 34+]
* getChildPropertyExpression(): String  [API 34+]
* getMaxJoinedResultCount(): int  [API 34+]
- getNestedQuery(): String  [API 34+]
- getNestedSearchSpec(): SearchSpec  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## JoinSpec.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 1 -)

```
* Builder(String)  [API 34+]
* build(): JoinSpec  [API 34+]
* setAggregationScoringStrategy(int): JoinSpec.Builder  [API 34+]
* setMaxJoinedResultCount(int): JoinSpec.Builder  [API 34+]
- setNestedSearch(String, SearchSpec): JoinSpec.Builder  [API 34+]
```

## Migrator — declaration; API 31+ (4 +, 0 *, 0 ~, 0 -)

```
+ Migrator()  [API 31+]
+ onDowngrade(int, int, GenericDocument): GenericDocument  [API 31+]
+ onUpgrade(int, int, GenericDocument): GenericDocument  [API 31+]
+ shouldMigrate(int, int): boolean  [API 31+]
```

## PackageIdentifier — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* PackageIdentifier(String, byte[])  [API 31+]
* equals(Object): boolean  [API 31+]
* getPackageName(): String  [API 31+]
* getSha256Certificate(): byte[]  [API 31+]
* hashCode(): int  [API 31+]
```

## PropertyPath — declaration; API 34+ (0 +, 0 *, 0 ~, 8 -)

```
- PropertyPath(List)  [API 34+]
- PropertyPath(String)  [API 34+]
- equals(Object): boolean  [API 34+]
- get(int): PropertyPath.PathSegment  [API 34+]
- hashCode(): int  [API 34+]
- iterator(): Iterator  [API 34+]
- size(): int  [API 34+]
- toString(): String  [API 34+]
```

## PropertyPath.PathSegment — declaration; API 34+ (0 +, 0 *, 0 ~, 7 -)

```
- create(String): PropertyPath.PathSegment [static]  [API 34+]
- create(String, int): PropertyPath.PathSegment [static]  [API 34+]
- equals(Object): boolean  [API 34+]
- getPropertyIndex(): int  [API 34+]
- getPropertyName(): String  [API 34+]
- hashCode(): int  [API 34+]
- toString(): String  [API 34+]
```

## PutDocumentsRequest — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- getGenericDocuments(): List  [API 31+]
- getTakenActionGenericDocuments(): List  [API 35+]
```

## PutDocumentsRequest.Builder — declaration; API 31+ (0 +, 0 *, 0 ~, 6 -)

```
- Builder()  [API 31+]
- addGenericDocuments(Collection): PutDocumentsRequest.Builder  [API 31+]
- addGenericDocuments(GenericDocument[]): PutDocumentsRequest.Builder  [API 31+]
- addTakenActionGenericDocuments(Collection): PutDocumentsRequest.Builder  [API 35+]
- addTakenActionGenericDocuments(GenericDocument[]): PutDocumentsRequest.Builder  [API 35+]
- build(): PutDocumentsRequest  [API 31+]
```

## RemoveByDocumentIdRequest — declaration; API 31+ (0 +, 1 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 31+]
- getIds(): Set  [API 31+]
* getNamespace(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## RemoveByDocumentIdRequest.Builder — declaration; API 31+ (0 +, 2 *, 0 ~, 2 -)

```
* Builder(String)  [API 31+]
- addIds(Collection): RemoveByDocumentIdRequest.Builder  [API 31+]
- addIds(String[]): RemoveByDocumentIdRequest.Builder  [API 31+]
* build(): RemoveByDocumentIdRequest  [API 31+]
```

## ReportSystemUsageRequest — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* getDatabaseName(): String  [API 31+]
* getDocumentId(): String  [API 31+]
* getNamespace(): String  [API 31+]
* getPackageName(): String  [API 31+]
* getUsageTimestampMillis(): long  [API 31+]
```

## ReportSystemUsageRequest.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(String, String, String, String)  [API 31+]
* build(): ReportSystemUsageRequest  [API 31+]
* setUsageTimestampMillis(long): ReportSystemUsageRequest.Builder  [API 31+]
```

## ReportUsageRequest — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 31+]
* getDocumentId(): String  [API 31+]
* getNamespace(): String  [API 31+]
* getUsageTimestampMillis(): long  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## ReportUsageRequest.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(String, String)  [API 31+]
* build(): ReportUsageRequest  [API 31+]
* setUsageTimestampMillis(long): ReportUsageRequest.Builder  [API 31+]
```

## SchemaVisibilityConfig — declaration; API 35+ (0 +, 2 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
* getAllowedPackages(): List  [API 35+]
* getPubliclyVisibleTargetPackage(): PackageIdentifier  [API 35+]
- getRequiredPermissions(): Set  [API 35+]
- hashCode(): int  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## SchemaVisibilityConfig.Builder — declaration; API 35+ (0 +, 5 *, 0 ~, 2 -)

```
* Builder()  [API 35+]
* addAllowedPackage(PackageIdentifier): SchemaVisibilityConfig.Builder  [API 35+]
- addRequiredPermissions(Set): SchemaVisibilityConfig.Builder  [API 35+]
* build(): SchemaVisibilityConfig  [API 35+]
* clearAllowedPackages(): SchemaVisibilityConfig.Builder  [API 35+]
- clearRequiredPermissions(): SchemaVisibilityConfig.Builder  [API 35+]
* setPubliclyVisibleTargetPackage(PackageIdentifier): SchemaVisibilityConfig.Builder  [API 35+]
```

## SearchResult — declaration; API 31+ (0 +, 6 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 31+]
* getDatabaseName(): String  [API 31+]
* getGenericDocument(): GenericDocument  [API 31+]
- getInformationalRankingSignals(): List  [API 36+]
* getJoinedResults(): List  [API 34+]
* getMatchInfos(): List  [API 31+]
* getPackageName(): String  [API 31+]
* getRankingSignal(): double  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## SearchResult.Builder — declaration; API 31+ (0 +, 6 *, 0 ~, 1 -)

```
* Builder(String, String)  [API 31+]
- addInformationalRankingSignal(double): SearchResult.Builder  [API 36+]
* addJoinedResult(SearchResult): SearchResult.Builder  [API 34+]
* addMatchInfo(SearchResult.MatchInfo): SearchResult.Builder  [API 31+]
* build(): SearchResult  [API 31+]
* setGenericDocument(GenericDocument): SearchResult.Builder  [API 31+]
* setRankingSignal(double): SearchResult.Builder  [API 31+]
```

## SearchResult.MatchInfo — declaration; API 31+ (0 +, 4 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 31+]
- getExactMatch(): CharSequence  [API 31+]
* getExactMatchRange(): SearchResult.MatchRange  [API 31+]
- getFullText(): String  [API 31+]
* getPropertyPath(): String  [API 31+]
- getPropertyPathObject(): PropertyPath  [API 34+]
- getSnippet(): CharSequence  [API 31+]
* getSnippetRange(): SearchResult.MatchRange  [API 31+]
- getSubmatch(): CharSequence  [API 33+]
* getSubmatchRange(): SearchResult.MatchRange  [API 33+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## SearchResult.MatchInfo.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(String)  [API 31+]
* build(): SearchResult.MatchInfo  [API 31+]
* setExactMatchRange(SearchResult.MatchRange): SearchResult.MatchInfo.Builder  [API 31+]
* setSnippetRange(SearchResult.MatchRange): SearchResult.MatchInfo.Builder  [API 31+]
* setSubmatchRange(SearchResult.MatchRange): SearchResult.MatchInfo.Builder  [API 33+]
```

## SearchResult.MatchRange — declaration; API 31+ (0 +, 6 *, 0 ~, 0 -)

```
* MatchRange(int, int)  [API 31+]
* equals(Object): boolean  [API 31+]
* getEnd(): int  [API 31+]
* getStart(): int  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
```

## SearchResults — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- close(): void  [API 31+]
- getNextPage(Executor, Consumer): void  [API 31+]
```

## SearchSpec — declaration; API 31+ (0 +, 14 *, 0 ~, 16 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 31+]
- getAdvancedRankingExpression(): String  [API 34+]
* getDefaultEmbeddingSearchMetricType(): int  [API 36+]
- getEmbeddingParameters(): List  [API 36+]
- getFilterNamespaces(): List  [API 31+]
- getFilterPackageNames(): List  [API 31+]
- getFilterProperties(): Map  [API 35+]
- getFilterSchemas(): List  [API 31+]
- getInformationalRankingExpressions(): List  [API 36+]
* getJoinSpec(): JoinSpec  [API 34+]
* getMaxSnippetSize(): int  [API 31+]
* getOrder(): int  [API 31+]
- getProjectionPaths(): Map  [API 34+]
- getProjections(): Map  [API 31+]
- getPropertyWeightPaths(): Map  [API 34+]
- getPropertyWeights(): Map  [API 34+]
* getRankingStrategy(): int  [API 31+]
* getResultCountPerPage(): int  [API 31+]
- getResultGroupingLimit(): int  [API 31+]
- getResultGroupingTypeFlags(): int  [API 31+]
* getSearchSourceLogTag(): String  [API 35+]
- getSearchStringParameters(): List  [API 36+]
* getSnippetCount(): int  [API 31+]
* getSnippetCountPerProperty(): int  [API 31+]
* getTermMatch(): int  [API 31+]
* isListFilterHasPropertyFunctionEnabled(): boolean  [API 35+]
* isListFilterQueryLanguageEnabled(): boolean  [API 34+]
* isNumericSearchEnabled(): boolean  [API 34+]
* isVerbatimSearchEnabled(): boolean  [API 34+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## SearchSpec.Builder — declaration; API 31+ (0 +, 16 *, 0 ~, 20 -)

```
* Builder()  [API 31+]
- addEmbeddingParameters(Collection): SearchSpec.Builder  [API 36+]
- addEmbeddingParameters(EmbeddingVector[]): SearchSpec.Builder  [API 36+]
- addFilterNamespaces(Collection): SearchSpec.Builder  [API 31+]
- addFilterNamespaces(String[]): SearchSpec.Builder  [API 31+]
- addFilterPackageNames(Collection): SearchSpec.Builder  [API 31+]
- addFilterPackageNames(String[]): SearchSpec.Builder  [API 31+]
- addFilterProperties(String, Collection): SearchSpec.Builder  [API 35+]
- addFilterPropertyPaths(String, Collection): SearchSpec.Builder  [API 35+]
- addFilterSchemas(Collection): SearchSpec.Builder  [API 31+]
- addFilterSchemas(String[]): SearchSpec.Builder  [API 31+]
- addInformationalRankingExpressions(Collection): SearchSpec.Builder  [API 36+]
- addInformationalRankingExpressions(String[]): SearchSpec.Builder  [API 36+]
- addProjection(String, Collection): SearchSpec.Builder  [API 31+]
- addProjectionPaths(String, Collection): SearchSpec.Builder  [API 34+]
- addSearchStringParameters(List): SearchSpec.Builder  [API 36+]
- addSearchStringParameters(String[]): SearchSpec.Builder  [API 36+]
* build(): SearchSpec  [API 31+]
* setDefaultEmbeddingSearchMetricType(int): SearchSpec.Builder  [API 36+]
* setJoinSpec(JoinSpec): SearchSpec.Builder  [API 34+]
* setListFilterHasPropertyFunctionEnabled(boolean): SearchSpec.Builder  [API 35+]
* setListFilterQueryLanguageEnabled(boolean): SearchSpec.Builder  [API 34+]
* setMaxSnippetSize(int): SearchSpec.Builder  [API 31+]
* setNumericSearchEnabled(boolean): SearchSpec.Builder  [API 34+]
* setOrder(int): SearchSpec.Builder  [API 31+]
- setPropertyWeightPaths(String, Map): SearchSpec.Builder  [API 34+]
- setPropertyWeights(String, Map): SearchSpec.Builder  [API 34+]
- setRankingStrategy(String): SearchSpec.Builder  [API 34+]
* setRankingStrategy(int): SearchSpec.Builder  [API 31+]
* setResultCountPerPage(int): SearchSpec.Builder  [API 31+]
- setResultGrouping(int, int): SearchSpec.Builder  [API 31+]
* setSearchSourceLogTag(String): SearchSpec.Builder  [API 35+]
* setSnippetCount(int): SearchSpec.Builder  [API 31+]
* setSnippetCountPerProperty(int): SearchSpec.Builder  [API 31+]
* setTermMatch(int): SearchSpec.Builder  [API 31+]
* setVerbatimSearchEnabled(boolean): SearchSpec.Builder  [API 34+]
```

## SearchSuggestionResult — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getSuggestedResult(): String  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## SearchSuggestionResult.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): SearchSuggestionResult  [API 34+]
* setSuggestedResult(String): SearchSuggestionResult.Builder  [API 34+]
```

## SearchSuggestionSpec — declaration; API 34+ (0 +, 2 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 34+]
- getFilterDocumentIds(): Map  [API 34+]
- getFilterNamespaces(): List  [API 34+]
- getFilterProperties(): Map  [API 35+]
- getFilterSchemas(): List  [API 34+]
* getMaximumResultCount(): int  [API 34+]
* getRankingStrategy(): int  [API 34+]
- getSearchStringParameters(): List  [API 36+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## SearchSuggestionSpec.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 10 -)

```
* Builder(int)  [API 34+]
- addFilterDocumentIds(String, Collection): SearchSuggestionSpec.Builder  [API 34+]
- addFilterDocumentIds(String, String[]): SearchSuggestionSpec.Builder  [API 34+]
- addFilterNamespaces(Collection): SearchSuggestionSpec.Builder  [API 34+]
- addFilterNamespaces(String[]): SearchSuggestionSpec.Builder  [API 34+]
- addFilterProperties(String, Collection): SearchSuggestionSpec.Builder  [API 35+]
- addFilterPropertyPaths(String, Collection): SearchSuggestionSpec.Builder  [API 35+]
- addFilterSchemas(Collection): SearchSuggestionSpec.Builder  [API 34+]
- addFilterSchemas(String[]): SearchSuggestionSpec.Builder  [API 34+]
- addSearchStringParameters(List): SearchSuggestionSpec.Builder  [API 36+]
- addSearchStringParameters(String[]): SearchSuggestionSpec.Builder  [API 36+]
* build(): SearchSuggestionSpec  [API 34+]
* setRankingStrategy(int): SearchSuggestionSpec.Builder  [API 34+]
```

## SetSchemaRequest — declaration; API 31+ (0 +, 3 *, 0 ~, 6 -)

```
* getMigrators(): Map  [API 31+]
- getPubliclyVisibleSchemas(): Map  [API 35+]
- getRequiredPermissionsForSchemaTypeVisibility(): Map  [API 33+]
- getSchemas(): Set  [API 31+]
- getSchemasNotDisplayedBySystem(): Set  [API 31+]
- getSchemasVisibleToConfigs(): Map  [API 35+]
- getSchemasVisibleToPackages(): Map  [API 31+]
* getVersion(): int  [API 31+]
* isForceOverride(): boolean  [API 31+]
```

## SetSchemaRequest.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 10 -)

```
* Builder()  [API 31+]
- addRequiredPermissionsForSchemaTypeVisibility(String, Set): SetSchemaRequest.Builder  [API 33+]
- addSchemaTypeVisibleToConfig(String, SchemaVisibilityConfig): SetSchemaRequest.Builder  [API 35+]
- addSchemas(AppSearchSchema[]): SetSchemaRequest.Builder  [API 31+]
- addSchemas(Collection): SetSchemaRequest.Builder  [API 31+]
* build(): SetSchemaRequest  [API 31+]
- clearRequiredPermissionsForSchemaTypeVisibility(String): SetSchemaRequest.Builder  [API 33+]
- clearSchemaTypeVisibleToConfigs(String): SetSchemaRequest.Builder  [API 35+]
* setForceOverride(boolean): SetSchemaRequest.Builder  [API 31+]
- setMigrator(String, Migrator): SetSchemaRequest.Builder  [API 31+]
* setMigrators(Map): SetSchemaRequest.Builder  [API 31+]
- setPubliclyVisibleSchema(String, PackageIdentifier): SetSchemaRequest.Builder  [API 35+]
- setSchemaTypeDisplayedBySystem(String, boolean): SetSchemaRequest.Builder  [API 31+]
- setSchemaTypeVisibilityForPackage(String, boolean, PackageIdentifier): SetSchemaRequest.Builder  [API 31+]
* setVersion(int): SetSchemaRequest.Builder  [API 31+]
```

## SetSchemaResponse — declaration; API 31+ (0 +, 1 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 31+]
- getDeletedTypes(): Set  [API 31+]
- getIncompatibleTypes(): Set  [API 31+]
- getMigratedTypes(): Set  [API 31+]
* getMigrationFailures(): List  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## SetSchemaResponse.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 7 -)

```
* Builder()  [API 31+]
- addDeletedType(String): SetSchemaResponse.Builder  [API 31+]
- addDeletedTypes(Collection): SetSchemaResponse.Builder  [API 31+]
- addIncompatibleType(String): SetSchemaResponse.Builder  [API 31+]
- addIncompatibleTypes(Collection): SetSchemaResponse.Builder  [API 31+]
- addMigratedType(String): SetSchemaResponse.Builder  [API 31+]
- addMigratedTypes(Collection): SetSchemaResponse.Builder  [API 31+]
* addMigrationFailure(SetSchemaResponse.MigrationFailure): SetSchemaResponse.Builder  [API 31+]
- addMigrationFailures(Collection): SetSchemaResponse.Builder  [API 31+]
* build(): SetSchemaResponse  [API 31+]
```

## SetSchemaResponse.MigrationFailure — declaration; API 31+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 35+]
- MigrationFailure(String, String, String, AppSearchResult)  [API 31+]
- describeContents(): int  [API 31+]
- getAppSearchResult(): AppSearchResult  [API 31+]
- getDocumentId(): String  [API 31+]
- getNamespace(): String  [API 31+]
- getSchemaType(): String  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## StorageInfo — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 31+]
* getAliveDocumentsCount(): int  [API 31+]
* getAliveNamespacesCount(): int  [API 31+]
* getSizeBytes(): long  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## StorageInfo.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* build(): StorageInfo  [API 31+]
* setAliveDocumentsCount(int): StorageInfo.Builder  [API 31+]
* setAliveNamespacesCount(int): StorageInfo.Builder  [API 31+]
* setSizeBytes(long): StorageInfo.Builder  [API 31+]
```


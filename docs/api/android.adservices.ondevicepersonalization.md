# android.adservices.ondevicepersonalization

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AppInfo — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* AppInfo(boolean)  [API 36+]
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* hashCode(): int  [API 35+]
* isInstalled(): boolean  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## DownloadCompletedInput — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* DownloadCompletedInput(KeyValueStore)  [API 36+]
* equals(Object): boolean  [API 35+]
* getDownloadedContents(): KeyValueStore  [API 35+]
* hashCode(): int  [API 35+]
```

## DownloadCompletedOutput — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getRetainedKeys(): List  [API 35+]
* hashCode(): int  [API 35+]
```

## DownloadCompletedOutput.Builder — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* addRetainedKey(String): DownloadCompletedOutput.Builder  [API 35+]
* build(): DownloadCompletedOutput  [API 35+]
* setRetainedKeys(List): DownloadCompletedOutput.Builder  [API 35+]
```

## EventInput — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* EventInput(RequestLogRecord, PersistableBundle)  [API 36+]
* equals(Object): boolean  [API 35+]
* getParameters(): PersistableBundle  [API 35+]
* getRequestLogRecord(): RequestLogRecord  [API 35+]
* hashCode(): int  [API 35+]
```

## EventLogRecord — declaration; API 35+ (0 +, 4 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
* getData(): ContentValues  [API 35+]
* getRequestLogRecord(): RequestLogRecord  [API 35+]
* getRowIndex(): int  [API 35+]
- getTime(): Instant  [API 35+]
* getType(): int  [API 35+]
- hashCode(): int  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## EventLogRecord.Builder — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): EventLogRecord  [API 35+]
* setData(ContentValues): EventLogRecord.Builder  [API 35+]
* setRequestLogRecord(RequestLogRecord): EventLogRecord.Builder  [API 35+]
* setRowIndex(int): EventLogRecord.Builder  [API 35+]
* setType(int): EventLogRecord.Builder  [API 35+]
```

## EventOutput — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getEventLogRecord(): EventLogRecord  [API 35+]
* hashCode(): int  [API 35+]
```

## EventOutput.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): EventOutput  [API 35+]
* setEventLogRecord(EventLogRecord): EventOutput.Builder  [API 35+]
```

## EventUrlProvider — declaration; API 35+ (0 +, 0 *, 0 ~, 2 -)

```
- createEventTrackingUrlWithRedirect(PersistableBundle, Uri): Uri  [API 35+]
- createEventTrackingUrlWithResponse(PersistableBundle, byte[], String): Uri  [API 35+]
```

## ExecuteInIsolatedServiceRequest — declaration; API 36+ (0 +, 3 *, 0 ~, 0 -)

```
* getAppParams(): PersistableBundle  [API 36+]
* getOutputSpec(): ExecuteInIsolatedServiceRequest.OutputSpec  [API 36+]
* getService(): ComponentName  [API 36+]
```

## ExecuteInIsolatedServiceRequest.Builder — declaration; API 36+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder(ComponentName)  [API 36+]
* build(): ExecuteInIsolatedServiceRequest  [API 36+]
* setAppParams(PersistableBundle): ExecuteInIsolatedServiceRequest.Builder  [API 36+]
* setOutputSpec(ExecuteInIsolatedServiceRequest.OutputSpec): ExecuteInIsolatedServiceRequest.Builder  [API 36+]
```

## ExecuteInIsolatedServiceRequest.OutputSpec — declaration; API 36+ (0 +, 0 *, 0 ~, 3 -)

```
- buildBestValueSpec(int): ExecuteInIsolatedServiceRequest.OutputSpec  [API 36+]
- getMaxIntValue(): int  [API 36+]
- getOutputType(): int  [API 36+]
```

## ExecuteInIsolatedServiceResponse — declaration; API 36+ (0 +, 3 *, 0 ~, 0 -)

```
* ExecuteInIsolatedServiceResponse(SurfacePackageToken, int)  [API 36+]
* getBestValue(): int  [API 36+]
* getSurfacePackageToken(): SurfacePackageToken  [API 36+]
```

## ExecuteInput — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* ExecuteInput(String, PersistableBundle)  [API 36+]
* getAppPackageName(): String  [API 35+]
* getAppParams(): PersistableBundle  [API 35+]
```

## ExecuteOutput — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getBestValue(): int  [API 36+]
* getEventLogRecords(): List  [API 35+]
* getOutputData(): byte[]  [API 35+]
* getRenderingConfig(): RenderingConfig  [API 35+]
* getRequestLogRecord(): RequestLogRecord  [API 35+]
* hashCode(): int  [API 35+]
```

## ExecuteOutput.Builder — declaration; API 35+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* addEventLogRecord(EventLogRecord): ExecuteOutput.Builder  [API 35+]
* build(): ExecuteOutput  [API 35+]
* setBestValue(int): ExecuteOutput.Builder  [API 36+]
* setEventLogRecords(List): ExecuteOutput.Builder  [API 35+]
* setOutputData(byte[]): ExecuteOutput.Builder  [API 35+]
* setRenderingConfig(RenderingConfig): ExecuteOutput.Builder  [API 35+]
* setRequestLogRecord(RequestLogRecord): ExecuteOutput.Builder  [API 35+]
```

## FederatedComputeInput — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getPopulationName(): String  [API 35+]
* hashCode(): int  [API 35+]
```

## FederatedComputeInput.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): FederatedComputeInput  [API 35+]
* setPopulationName(String): FederatedComputeInput.Builder  [API 35+]
```

## FederatedComputeScheduler — declaration; API 35+ (0 +, 0 *, 0 ~, 2 -)

```
- cancel(FederatedComputeInput): void  [API 35+]
- schedule(FederatedComputeScheduler.Params, FederatedComputeInput): void  [API 35+]
```

## FederatedComputeScheduler.Params — declaration; API 35+ (0 +, 2 *, 0 ~, 0 -)

```
* Params(TrainingInterval)  [API 35+]
* getTrainingInterval(): TrainingInterval  [API 35+]
```

## InferenceInput — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getBatchSize(): int  [API 35+]
* getExpectedOutputStructure(): InferenceOutput  [API 35+]
* getInputData(): Object[]  [API 35+]
* getParams(): InferenceInput.Params  [API 35+]
* hashCode(): int  [API 35+]
```

## InferenceInput.Builder — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder(InferenceInput.Params, Object[], InferenceOutput)  [API 35+]
* build(): InferenceInput  [API 35+]
* setBatchSize(int): InferenceInput.Builder  [API 35+]
* setExpectedOutputStructure(InferenceOutput): InferenceInput.Builder  [API 35+]
* setInputData(Object[]): InferenceInput.Builder  [API 35+]
* setParams(InferenceInput.Params): InferenceInput.Builder  [API 35+]
```

## InferenceInput.Params — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getDelegateType(): int  [API 35+]
* getKeyValueStore(): KeyValueStore  [API 35+]
* getModelKey(): String  [API 35+]
* getModelType(): int  [API 35+]
* getRecommendedNumThreads(): int  [API 35+]
* hashCode(): int  [API 35+]
```

## InferenceInput.Params.Builder — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder(KeyValueStore, String)  [API 35+]
* build(): InferenceInput.Params  [API 35+]
* setDelegateType(int): InferenceInput.Params.Builder  [API 35+]
* setKeyValueStore(KeyValueStore): InferenceInput.Params.Builder  [API 35+]
* setModelKey(String): InferenceInput.Params.Builder  [API 35+]
* setModelType(int): InferenceInput.Params.Builder  [API 35+]
* setRecommendedNumThreads(int): InferenceInput.Params.Builder  [API 35+]
```

## InferenceOutput — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getDataOutputs(): Map  [API 35+]
* hashCode(): int  [API 35+]
```

## InferenceOutput.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder()  [API 35+]
- addDataOutput(int, Object): InferenceOutput.Builder  [API 35+]
* build(): InferenceOutput  [API 35+]
* setDataOutputs(Map): InferenceOutput.Builder  [API 35+]
```

## IsolatedService — declaration; API 35+ (2 +, 0 *, 0 ~, 9 -)

```
- IsolatedService()  [API 35+]
- getEventUrlProvider(RequestToken): EventUrlProvider  [API 35+]
- getFederatedComputeScheduler(RequestToken): FederatedComputeScheduler  [API 35+]
- getLocalData(RequestToken): MutableKeyValueStore  [API 35+]
- getLogReader(RequestToken): LogReader  [API 35+]
- getModelManager(RequestToken): ModelManager  [API 35+]
- getRemoteData(RequestToken): KeyValueStore  [API 35+]
- getUserData(RequestToken): UserData  [API 35+]
- onBind(Intent): IBinder  [API 35+]
+ onCreate(): void  [API 35+]
+ onRequest(RequestToken): IsolatedWorker  [API 35+]
```

## IsolatedServiceException — declaration; API 35+ (0 +, 0 *, 0 ~, 4 -)

```
- IsolatedServiceException(int)  [API 35+]
- IsolatedServiceException(int, String, Throwable)  [API 36+]
- IsolatedServiceException(int, Throwable)  [API 36+]
- getErrorCode(): int  [API 36+]
```

## IsolatedWorker — declaration; API 35+ (6 +, 0 *, 0 ~, 0 -)

```
+ onDownloadCompleted(DownloadCompletedInput, OutcomeReceiver): void  [API 35+]
+ onEvent(EventInput, OutcomeReceiver): void  [API 35+]
+ onExecute(ExecuteInput, OutcomeReceiver): void  [API 35+]
+ onRender(RenderInput, OutcomeReceiver): void  [API 35+]
+ onTrainingExamples(TrainingExamplesInput, OutcomeReceiver): void  [API 35+]
+ onWebTrigger(WebTriggerInput, OutcomeReceiver): void  [API 35+]
```

## KeyValueStore — declaration; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ get(String): byte[]  [API 35+]
+ keySet(): Set  [API 35+]
```

## LogReader — declaration; API 35+ (0 +, 0 *, 0 ~, 2 -)

```
- getJoinedEvents(Instant, Instant): List  [API 35+]
- getRequests(Instant, Instant): List  [API 35+]
```

## ModelManager — declaration; API 35+ (0 +, 0 *, 0 ~, 1 -)

```
- run(InferenceInput, Executor, OutcomeReceiver): void  [API 35+]
```

## MutableKeyValueStore — declaration; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ put(String, byte[]): byte[]  [API 35+]
+ remove(String): byte[]  [API 35+]
```

## OnDevicePersonalizationException — declaration; API 35+ (0 +, 0 *, 0 ~, 1 -)

```
- getErrorCode(): int  [API 35+]
```

## OnDevicePersonalizationManager — declaration; API 35+ (0 +, 0 *, 0 ~, 3 -)

```
- execute(ComponentName, PersistableBundle, Executor, OutcomeReceiver): void  [API 35+]
- executeInIsolatedService(ExecuteInIsolatedServiceRequest, Executor, OutcomeReceiver): void  [API 36+]
- requestSurfacePackage(SurfacePackageToken, IBinder, int, int, int, Executor, OutcomeReceiver): void  [API 35+]
```

## OnDevicePersonalizationManager.ExecuteResult — declaration; API 35+ (0 +, 0 *, 0 ~, 2 -)

```
- getOutputData(): byte[]  [API 35+]
- getSurfacePackageToken(): SurfacePackageToken  [API 35+]
```

## RenderInput — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* RenderInput(int, int, RenderingConfig)  [API 36+]
* equals(Object): boolean  [API 35+]
* getHeight(): int  [API 35+]
* getRenderingConfig(): RenderingConfig  [API 35+]
* getWidth(): int  [API 35+]
* hashCode(): int  [API 35+]
```

## RenderOutput — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getContent(): String  [API 35+]
* getTemplateId(): String  [API 35+]
* getTemplateParams(): PersistableBundle  [API 35+]
* hashCode(): int  [API 35+]
```

## RenderOutput.Builder — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): RenderOutput  [API 35+]
* setContent(String): RenderOutput.Builder  [API 35+]
* setTemplateId(String): RenderOutput.Builder  [API 35+]
* setTemplateParams(PersistableBundle): RenderOutput.Builder  [API 35+]
```

## RenderingConfig — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getKeys(): List  [API 35+]
* hashCode(): int  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## RenderingConfig.Builder — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* addKey(String): RenderingConfig.Builder  [API 35+]
* build(): RenderingConfig  [API 35+]
* setKeys(List): RenderingConfig.Builder  [API 35+]
```

## RequestLogRecord — declaration; API 35+ (0 +, 1 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
* getRows(): List  [API 35+]
- getTime(): Instant  [API 35+]
- hashCode(): int  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## RequestLogRecord.Builder — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* addRow(ContentValues): RequestLogRecord.Builder  [API 35+]
* build(): RequestLogRecord  [API 35+]
* setRows(List): RequestLogRecord.Builder  [API 35+]
```

## RequestToken — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## SurfacePackageToken — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## TrainingExampleRecord — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* getResumptionToken(): byte[]  [API 35+]
* getTrainingExample(): byte[]  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## TrainingExampleRecord.Builder — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): TrainingExampleRecord  [API 35+]
* setResumptionToken(byte[]): TrainingExampleRecord.Builder  [API 35+]
* setTrainingExample(byte[]): TrainingExampleRecord.Builder  [API 35+]
```

## TrainingExamplesInput — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* TrainingExamplesInput(String, String, byte[], String)  [API 36+]
* equals(Object): boolean  [API 35+]
* getCollectionName(): String  [API 36+]
* getPopulationName(): String  [API 35+]
* getResumptionToken(): byte[]  [API 35+]
* getTaskName(): String  [API 35+]
* hashCode(): int  [API 35+]
```

## TrainingExamplesOutput — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getTrainingExampleRecords(): List  [API 35+]
* hashCode(): int  [API 35+]
```

## TrainingExamplesOutput.Builder — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* addTrainingExampleRecord(TrainingExampleRecord): TrainingExamplesOutput.Builder  [API 35+]
* build(): TrainingExamplesOutput  [API 35+]
* setTrainingExampleRecords(List): TrainingExamplesOutput.Builder  [API 35+]
```

## TrainingInterval — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getMinimumInterval(): Duration  [API 35+]
* getSchedulingMode(): int  [API 35+]
* hashCode(): int  [API 35+]
```

## TrainingInterval.Builder — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): TrainingInterval  [API 35+]
* setMinimumInterval(Duration): TrainingInterval.Builder  [API 35+]
* setSchedulingMode(int): TrainingInterval.Builder  [API 35+]
```

## UserData — declaration; API 35+ (0 +, 0 *, 0 ~, 12 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
- getAppInfos(): Map  [API 35+]
- getAvailableStorageBytes(): long  [API 35+]
- getBatteryPercentage(): int  [API 35+]
- getCarrier(): String  [API 35+]
- getDataNetworkType(): int  [API 35+]
- getNetworkCapabilities(): NetworkCapabilities  [API 35+]
- getOrientation(): int  [API 35+]
- getTimezoneUtcOffset(): Duration  [API 35+]
- hashCode(): int  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## WebTriggerInput — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
* WebTriggerInput(Uri, String, byte[])  [API 36+]
* equals(Object): boolean  [API 35+]
* getAppPackageName(): String  [API 35+]
* getData(): byte[]  [API 35+]
* getDestinationUrl(): Uri  [API 35+]
* hashCode(): int  [API 35+]
```

## WebTriggerOutput — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getEventLogRecords(): List  [API 35+]
* getRequestLogRecord(): RequestLogRecord  [API 35+]
* hashCode(): int  [API 35+]
```

## WebTriggerOutput.Builder — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* addEventLogRecord(EventLogRecord): WebTriggerOutput.Builder  [API 35+]
* build(): WebTriggerOutput  [API 35+]
* setEventLogRecords(List): WebTriggerOutput.Builder  [API 35+]
* setRequestLogRecord(RequestLogRecord): WebTriggerOutput.Builder  [API 35+]
```


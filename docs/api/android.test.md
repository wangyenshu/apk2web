# android.test

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ActivityInstrumentationTestCase — declaration; API 1+, deprecated in 3 (0 +, 0 *, 0 ~, 3 -)

```
- ActivityInstrumentationTestCase(String, Class)  [API 1+]
- ActivityInstrumentationTestCase(String, Class, boolean)  [API 1+]
- testActivityTestCaseSetUpProperly(): void  [API 1+]
```

## ActivityInstrumentationTestCase2 — declaration; API 3+, deprecated in 24 (0 +, 0 *, 0 ~, 4 -)

```
- ActivityInstrumentationTestCase2(Class)  [API 8+]
- ActivityInstrumentationTestCase2(String, Class)  [API 3+, deprecated in 15]
- setActivityInitialTouchMode(boolean): void  [API 3+]
- setActivityIntent(Intent): void  [API 3+]
```

## ActivityTestCase — declaration; API 1+, deprecated in 24 (0 +, 3 *, 0 ~, 1 -)

```
* ActivityTestCase()  [API 1+]
* getActivity(): Activity  [API 1+]
- scrubClass(Class): void  [API 1+]
* setActivity(Activity): void  [API 1+]
```

## ActivityUnitTestCase — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 9 -)

```
- ActivityUnitTestCase(Class)  [API 1+]
- getFinishedActivityRequest(): int  [API 1+]
- getRequestedOrientation(): int  [API 1+]
- getStartedActivityIntent(): Intent  [API 1+]
- getStartedActivityRequest(): int  [API 1+]
- isFinishCalled(): boolean  [API 1+]
- setActivityContext(Context): void  [API 1+]
- setApplication(Application): void  [API 1+]
- startActivity(Intent, Bundle, Object): Activity  [API 1+]
```

## AndroidTestCase — declaration; API 1+, deprecated in 24 (0 +, 5 *, 0 ~, 5 -)

```
* AndroidTestCase()  [API 1+]
- assertActivityRequiresPermission(String, String, String): void  [API 4+]
- assertReadingContentUriRequiresPermission(Uri, String): void  [API 4+]
- assertWritingContentUriRequiresPermission(Uri, String): void  [API 4+]
* getContext(): Context  [API 1+]
* getTestContext(): Context  [API 36+, deprecated in 36]
+ mContext [field]  [API 1+]
- scrubClass(Class): void  [API 1+]
* setContext(Context): void  [API 1+]
* setTestContext(Context): void  [API 36+, deprecated in 36]
- testAndroidTestCaseSetupProperly(): void  [API 1+]
```

## AndroidTestRunner — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 14 -)

```
- AndroidTestRunner()  [API 1+]
- addTestListener(TestListener): void  [API 1+]
- clearTestListeners(): void  [API 1+]
- createTestResult(): TestResult  [API 1+]
- getTestCases(): List  [API 1+]
- getTestClassName(): String  [API 1+]
- getTestResult(): TestResult  [API 1+]
- runTest(): void  [API 1+]
- runTest(TestResult): void  [API 1+]
- setContext(Context): void  [API 1+]
- setInstrumentaiton(Instrumentation): void  [API 1+, deprecated in 15]
- setInstrumentation(Instrumentation): void  [API 5+]
- setTest(Test): void  [API 1+]
- setTestClassName(String, String): void  [API 1+]
```

## ApplicationTestCase — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 6 -)

```
- ApplicationTestCase(Class)  [API 1+]
- createApplication(): void  [API 1+]
- getApplication(): Application  [API 1+]
- getSystemContext(): Context  [API 1+]
- terminateApplication(): void  [API 1+]
- testApplicationTestCaseSetUpProperly(): void  [API 1+]
```

## AssertionFailedError — declaration; API 1+, deprecated in 16 (2 +, 0 *, 0 ~, 0 -)

```
+ AssertionFailedError()  [API 1+]
+ AssertionFailedError(String)  [API 1+]
```

## ComparisonFailure — declaration; API 1+, deprecated in 16 (0 +, 0 *, 0 ~, 1 -)

```
- ComparisonFailure(String, String, String)  [API 1+]
```

## FlakyTest — declaration; API 1+, deprecated in 24 (1 +, 0 *, 0 ~, 0 -)

```
+ tolerance(): int  [API 1+]
```

## InstrumentationTestCase — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 10 -)

```
- InstrumentationTestCase()  [API 1+]
- getInstrumentation(): Instrumentation  [API 1+]
- injectInsrumentation(Instrumentation): void  [API 1+, deprecated in 15]
- injectInstrumentation(Instrumentation): void  [API 5+]
- launchActivity(String, Class, Bundle): Activity  [API 1+]
- launchActivityWithIntent(String, Class, Intent): Activity  [API 3+]
- runTestOnUiThread(Runnable): void  [API 3+]
- sendKeys(String): void  [API 1+]
- sendKeys(int[]): void  [API 1+]
- sendRepeatedKeys(int[]): void  [API 1+]
```

## InstrumentationTestRunner — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 6 -)

```
- InstrumentationTestRunner()  [API 1+]
- addTestListener(TestListener): void  [API 36+, deprecated in 36]
- getAllTests(): TestSuite  [API 1+]
- getAndroidTestRunner(): AndroidTestRunner  [API 1+]
- getArguments(): Bundle  [API 18+]
- getLoader(): ClassLoader  [API 1+]
```

## InstrumentationTestSuite — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 3 -)

```
- InstrumentationTestSuite(Class, Instrumentation)  [API 1+]
- InstrumentationTestSuite(Instrumentation)  [API 1+]
- InstrumentationTestSuite(String, Instrumentation)  [API 1+]
```

## IsolatedContext — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 2 -)

```
- IsolatedContext(ContentResolver, Context)  [API 1+]
- getAndClearBroadcastIntents(): List  [API 1+]
```

## LaunchPerformanceBase — declaration; API 36+, deprecated in 36 (0 +, 0 *, 0 ~, 2 -)

```
- LaunchApp(): void  [API 36+]
- LaunchPerformanceBase()  [API 36+]
+ mIntent [field]  [API 36+]
+ mResults [field]  [API 36+]
```

## LoaderTestCase — declaration; API 11+ (0 +, 0 *, 0 ~, 2 -)

```
- LoaderTestCase()  [API 11+]
- getLoaderResultSynchronously(Loader): Object  [API 11+]
```

## MoreAsserts — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 36 -)

```
- assertAssignableFrom(Class, Class): void  [API 1+]
- assertAssignableFrom(Class, Object): void  [API 1+]
- assertContainsRegex(String, String): MatchResult  [API 1+]
- assertContainsRegex(String, String, String): MatchResult  [API 1+]
- assertContentsInAnyOrder(Iterable, Object[]): void  [API 1+]
- assertContentsInAnyOrder(String, Iterable, Object[]): void  [API 1+]
- assertContentsInOrder(Iterable, Object[]): void  [API 1+]
- assertContentsInOrder(String, Iterable, Object[]): void  [API 1+]
- assertEmpty(Iterable): void  [API 1+]
- assertEmpty(Map): void  [API 1+]
- assertEmpty(String, Iterable): void  [API 1+]
- assertEmpty(String, Map): void  [API 1+]
- assertEquals(Object[], Object[]): void  [API 1+]
- assertEquals(Set, Set): void  [API 1+]
- assertEquals(String, Object[], Object[]): void  [API 1+]
- assertEquals(String, Set, Set): void  [API 1+]
- assertEquals(String, byte[], byte[]): void  [API 1+]
- assertEquals(String, double[], double[]): void  [API 1+]
- assertEquals(String, int[], int[]): void  [API 1+]
- assertEquals(byte[], byte[]): void  [API 1+]
- assertEquals(double[], double[]): void  [API 1+]
- assertEquals(int[], int[]): void  [API 1+]
- assertMatchesRegex(String, String): MatchResult  [API 1+]
- assertMatchesRegex(String, String, String): MatchResult  [API 1+]
- assertNotContainsRegex(String, String): void  [API 1+]
- assertNotContainsRegex(String, String, String): void  [API 1+]
- assertNotEmpty(Iterable): void  [API 1+]
- assertNotEmpty(Map): void  [API 1+]
- assertNotEmpty(String, Iterable): void  [API 1+]
- assertNotEmpty(String, Map): void  [API 1+]
- assertNotEqual(Object, Object): void  [API 1+]
- assertNotEqual(String, Object, Object): void  [API 1+]
- assertNotMatchesRegex(String, String): void  [API 1+]
- assertNotMatchesRegex(String, String, String): void  [API 1+]
- checkEqualsAndHashCodeMethods(Object, Object, boolean): void  [API 1+]
- checkEqualsAndHashCodeMethods(String, Object, Object, boolean): void  [API 1+]
```

## PerformanceTestCase — declaration; API 1+, deprecated in 24 (2 +, 0 *, 0 ~, 0 -)

```
+ isPerformanceOnly(): boolean  [API 1+]
+ startPerformance(PerformanceTestCase.Intermediates): int  [API 1+]
```

## PerformanceTestCase.Intermediates — declaration; API 1+, deprecated in 36 (5 +, 0 *, 0 ~, 0 -)

```
+ addIntermediate(String): void  [API 1+]
+ addIntermediate(String, long): void  [API 1+]
+ finishTiming(boolean): void  [API 1+]
+ setInternalIterations(int): void  [API 1+]
+ startTiming(boolean): void  [API 1+]
```

## ProviderTestCase — declaration; API 1+, deprecated in 5 (0 +, 0 *, 0 ~, 5 -)

```
- ProviderTestCase(Class, String)  [API 1+]
- getMockContentResolver(): MockContentResolver  [API 1+]
- getMockContext(): IsolatedContext  [API 1+]
- getProvider(): ContentProvider  [API 1+]
- newResolverWithContentProviderFromSql(Context, Class, String, String, int, String): ContentResolver  [API 1+]
```

## ProviderTestCase2 — declaration; API 3+ (0 +, 0 *, 0 ~, 5 -)

```
- ProviderTestCase2(Class, String)  [API 3+]
- getMockContentResolver(): MockContentResolver  [API 3+]
- getMockContext(): IsolatedContext  [API 3+]
- getProvider(): ContentProvider  [API 3+]
- newResolverWithContentProviderFromSql(Context, String, Class, String, String, int, String): ContentResolver  [API 3+]
```

## RenamingDelegatingContext — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 6 -)

```
- RenamingDelegatingContext(Context, Context, String)  [API 1+]
- RenamingDelegatingContext(Context, String)  [API 1+]
- getDatabasePrefix(): String  [API 1+]
- makeExistingFilesAndDbsAccessible(): void  [API 1+]
- providerWithRenamedContext(Class, Context, String): ContentProvider  [API 1+]
- providerWithRenamedContext(Class, Context, String, boolean): ContentProvider  [API 1+]
```

## RepetitiveTest — declaration; API 36+, deprecated in 36 (1 +, 0 *, 0 ~, 0 -)

```
+ numIterations(): int  [API 36+]
```

## ServiceTestCase — declaration; API 1+, deprecated in 24 (0 +, 2 *, 0 ~, 8 -)

```
- ServiceTestCase(Class)  [API 1+]
- bindService(Intent): IBinder  [API 1+]
* getApplication(): Application  [API 1+]
- getService(): Service  [API 1+]
- getSystemContext(): Context  [API 1+]
* setApplication(Application): void  [API 1+]
- setupService(): void  [API 1+]
- shutdownService(): void  [API 1+]
- startService(Intent): void  [API 1+]
- testServiceTestCaseSetUpProperly(): void  [API 1+]
```

## SingleLaunchActivityTestCase — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 3 -)

```
- SingleLaunchActivityTestCase(String, Class)  [API 1+]
- getActivity(): Activity  [API 1+]
- testActivityTestCaseSetUpProperly(): void  [API 1+]
```

## SyncBaseInstrumentation — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 3 -)

```
- SyncBaseInstrumentation()  [API 1+]
- cancelSyncsandDisableAutoSync(): void  [API 1+]
- syncProvider(Uri, String, String): void  [API 1+]
```

## TestSuiteProvider — declaration; API 1+, deprecated in 24 (1 +, 0 *, 0 ~, 0 -)

```
+ getTestSuite(): TestSuite  [API 1+]
```

## TouchUtils — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 32 -)

```
- TouchUtils()  [API 1+]
- clickView(InstrumentationTestCase, View): void  [API 1+]
- drag(ActivityInstrumentationTestCase, float, float, float, float, int): void  [API 1+, deprecated in 15]
- drag(InstrumentationTestCase, float, float, float, float, int): void  [API 3+]
- dragQuarterScreenDown(ActivityInstrumentationTestCase): void  [API 1+, deprecated in 15]
- dragQuarterScreenDown(InstrumentationTestCase, Activity): void  [API 3+]
- dragQuarterScreenUp(ActivityInstrumentationTestCase): void  [API 1+, deprecated in 15]
- dragQuarterScreenUp(InstrumentationTestCase, Activity): void  [API 3+]
- dragViewBy(ActivityInstrumentationTestCase, View, int, int, int): int  [API 1+, deprecated in 15]
- dragViewBy(InstrumentationTestCase, View, int, int, int): int  [API 3+, deprecated in 15]
- dragViewTo(ActivityInstrumentationTestCase, View, int, int, int): int  [API 1+, deprecated in 15]
- dragViewTo(InstrumentationTestCase, View, int, int, int): int  [API 3+]
- dragViewToBottom(ActivityInstrumentationTestCase, View): void  [API 1+, deprecated in 15]
- dragViewToBottom(ActivityInstrumentationTestCase, View, int): void  [API 1+, deprecated in 15]
- dragViewToBottom(InstrumentationTestCase, Activity, View): void  [API 3+]
- dragViewToBottom(InstrumentationTestCase, Activity, View, int): void  [API 3+]
- dragViewToTop(ActivityInstrumentationTestCase, View): void  [API 1+, deprecated in 15]
- dragViewToTop(ActivityInstrumentationTestCase, View, int): void  [API 1+, deprecated in 15]
- dragViewToTop(InstrumentationTestCase, View): void  [API 3+]
- dragViewToTop(InstrumentationTestCase, View, int): void  [API 3+]
- dragViewToX(ActivityInstrumentationTestCase, View, int, int): int  [API 1+, deprecated in 15]
- dragViewToX(InstrumentationTestCase, View, int, int): int  [API 3+]
- dragViewToY(ActivityInstrumentationTestCase, View, int, int): int  [API 1+, deprecated in 15]
- dragViewToY(InstrumentationTestCase, View, int, int): int  [API 3+]
- longClickView(ActivityInstrumentationTestCase, View): void  [API 1+, deprecated in 15]
- longClickView(InstrumentationTestCase, View): void  [API 3+]
- scrollToBottom(ActivityInstrumentationTestCase, ViewGroup): void  [API 1+, deprecated in 15]
- scrollToBottom(InstrumentationTestCase, Activity, ViewGroup): void  [API 3+]
- scrollToTop(ActivityInstrumentationTestCase, ViewGroup): void  [API 1+, deprecated in 15]
- scrollToTop(InstrumentationTestCase, Activity, ViewGroup): void  [API 3+]
- tapView(InstrumentationTestCase, View): void  [API 1+]
- touchAndCancelView(InstrumentationTestCase, View): void  [API 1+]
```

## UiThreadTest — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 0 -)

## ViewAsserts — declaration; API 1+, deprecated in 24 (0 +, 0 *, 0 ~, 18 -)

```
- assertBaselineAligned(View, View): void  [API 1+]
- assertBottomAligned(View, View): void  [API 1+]
- assertBottomAligned(View, View, int): void  [API 1+]
- assertGroupContains(ViewGroup, View): void  [API 1+]
- assertGroupIntegrity(ViewGroup): void  [API 1+]
- assertGroupNotContains(ViewGroup, View): void  [API 1+]
- assertHasScreenCoordinates(View, View, int, int): void  [API 1+]
- assertHorizontalCenterAligned(View, View): void  [API 1+]
- assertLeftAligned(View, View): void  [API 1+]
- assertLeftAligned(View, View, int): void  [API 1+]
- assertOffScreenAbove(View, View): void  [API 1+]
- assertOffScreenBelow(View, View): void  [API 1+]
- assertOnScreen(View, View): void  [API 1+]
- assertRightAligned(View, View): void  [API 1+]
- assertRightAligned(View, View, int): void  [API 1+]
- assertTopAligned(View, View): void  [API 1+]
- assertTopAligned(View, View, int): void  [API 1+]
- assertVerticalCenterAligned(View, View): void  [API 1+]
```


# junit.framework

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Assert — declaration; API 1+ (0 +, 0 *, 0 ~, 39 -)

```
- Assert()  [API 1+]
- assertEquals(Object, Object): void  [API 1+]
- assertEquals(String, Object, Object): void  [API 1+]
- assertEquals(String, String): void  [API 1+]
- assertEquals(String, String, String): void  [API 1+]
- assertEquals(String, boolean, boolean): void  [API 1+]
- assertEquals(String, byte, byte): void  [API 1+]
- assertEquals(String, char, char): void  [API 1+]
- assertEquals(String, double, double, double): void  [API 1+]
- assertEquals(String, float, float, float): void  [API 1+]
- assertEquals(String, int, int): void  [API 1+]
- assertEquals(String, long, long): void  [API 1+]
- assertEquals(String, short, short): void  [API 1+]
- assertEquals(boolean, boolean): void  [API 1+]
- assertEquals(byte, byte): void  [API 1+]
- assertEquals(char, char): void  [API 1+]
- assertEquals(double, double, double): void  [API 1+]
- assertEquals(float, float, float): void  [API 1+]
- assertEquals(int, int): void  [API 1+]
- assertEquals(long, long): void  [API 1+]
- assertEquals(short, short): void  [API 1+]
- assertFalse(String, boolean): void  [API 1+]
- assertFalse(boolean): void  [API 1+]
- assertNotNull(Object): void  [API 1+]
- assertNotNull(String, Object): void  [API 1+]
- assertNotSame(Object, Object): void  [API 1+]
- assertNotSame(String, Object, Object): void  [API 1+]
- assertNull(Object): void  [API 1+]
- assertNull(String, Object): void  [API 1+]
- assertSame(Object, Object): void  [API 1+]
- assertSame(String, Object, Object): void  [API 1+]
- assertTrue(String, boolean): void  [API 1+]
- assertTrue(boolean): void  [API 1+]
- fail(): void  [API 1+]
- fail(String): void  [API 1+]
- failNotEquals(String, Object, Object): void  [API 16+]
- failNotSame(String, Object, Object): void  [API 16+]
- failSame(String): void  [API 16+]
- format(String, Object, Object): String  [API 16+]
```

## AssertionFailedError — declaration; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
+ AssertionFailedError()  [API 1+]
- AssertionFailedError(String)  [API 1+]
```

## ComparisonFailure — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- ComparisonFailure(String, String, String)  [API 1+]
- getActual(): String  [API 16+]
- getExpected(): String  [API 16+]
```

## Protectable — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ protect(): void  [API 1+]
```

## Test — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ countTestCases(): int  [API 1+]
+ run(TestResult): void  [API 1+]
```

## TestCase — declaration; API 1+ (0 +, 4 *, 0 ~, 6 -)

```
* TestCase()  [API 1+]
* TestCase(String)  [API 1+]
- createResult(): TestResult  [API 1+]
* getName(): String  [API 1+]
- run(): TestResult  [API 1+]
- runBare(): void  [API 1+]
- runTest(): void  [API 1+]
* setName(String): void  [API 1+]
- setUp(): void  [API 1+]
- tearDown(): void  [API 1+]
```

## TestFailure — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- TestFailure(Test, Throwable)  [API 1+]
- exceptionMessage(): String  [API 1+]
+ fFailedTest [field]  [API 1+]
+ fThrownException [field]  [API 1+]
- failedTest(): Test  [API 1+]
- isFailure(): boolean  [API 1+]
- thrownException(): Throwable  [API 1+]
- trace(): String  [API 1+]
```

## TestListener — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ addError(Test, Throwable): void  [API 1+]
+ addFailure(Test, AssertionFailedError): void  [API 1+]
+ endTest(Test): void  [API 1+]
+ startTest(Test): void  [API 1+]
```

## TestResult — declaration; API 1+ (0 +, 0 *, 0 ~, 17 -)

```
- TestResult()  [API 1+]
- addError(Test, Throwable): void  [API 1+]
- addFailure(Test, AssertionFailedError): void  [API 1+]
- addListener(TestListener): void  [API 1+]
- endTest(Test): void  [API 1+]
- errorCount(): int  [API 1+]
- errors(): Enumeration  [API 1+]
+ fErrors [field]  [API 1+]
+ fFailures [field]  [API 1+]
+ fListeners [field]  [API 1+]
+ fRunTests [field]  [API 1+]
- failureCount(): int  [API 1+]
- failures(): Enumeration  [API 1+]
- removeListener(TestListener): void  [API 1+]
- run(TestCase): void  [API 1+]
- runCount(): int  [API 1+]
- runProtected(Test, Protectable): void  [API 1+]
- shouldStop(): boolean  [API 1+]
- startTest(Test): void  [API 1+]
- stop(): void  [API 1+]
- wasSuccessful(): boolean  [API 1+]
```

## TestSuite — declaration; API 1+ (0 +, 4 *, 0 ~, 13 -)

```
* TestSuite()  [API 1+]
- TestSuite(Class)  [API 1+]
- TestSuite(Class, String)  [API 1+]
- TestSuite(Class[])  [API 16+]
- TestSuite(Class[], String)  [API 16+]
* TestSuite(String)  [API 1+]
- addTest(Test): void  [API 1+]
- addTestSuite(Class): void  [API 1+]
- createTest(Class, String): Test  [API 1+]
* getName(): String  [API 1+]
- getTestConstructor(Class): Constructor  [API 1+]
- runTest(Test, TestResult): void  [API 1+]
* setName(String): void  [API 1+]
- testAt(int): Test  [API 1+]
- testCount(): int  [API 1+]
- tests(): Enumeration  [API 1+]
- warning(String): Test  [API 16+]
```


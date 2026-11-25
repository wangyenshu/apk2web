# android.test.suitebuilder

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## TestMethod — declaration; API 1+, deprecated in 26 (0 +, 0 *, 0 ~, 8 -)

```
- TestMethod(Method, Class)  [API 1+]
- TestMethod(String, Class)  [API 3+]
- TestMethod(TestCase)  [API 3+]
- createTest(): TestCase  [API 1+]
- getAnnotation(Class): Annotation  [API 1+]
- getEnclosingClass(): Class  [API 1+]
- getEnclosingClassname(): String  [API 1+]
- getName(): String  [API 1+]
```

## TestSuiteBuilder — missing; API 1+, deprecated in 26 (0 +, 0 *, 0 ~, 10 -)

```
- TestSuiteBuilder(Class)  [API 1+]
- TestSuiteBuilder(String, ClassLoader)  [API 1+]
- addRequirements(List): TestSuiteBuilder  [API 1-27, removed in 28]
- addRequirements(Predicate[]): TestSuiteBuilder  [API 1-27, removed in 28]
- build(): TestSuite  [API 1+]
- excludePackages(String[]): TestSuiteBuilder  [API 1+]
- getSuiteName(): String  [API 1+]
- includeAllPackagesUnderHere(): TestSuiteBuilder  [API 1+]
- includePackages(String[]): TestSuiteBuilder  [API 1+]
- named(String): TestSuiteBuilder  [API 1+]
```

## TestSuiteBuilder.FailedToCreateTests — declaration; API 1+, deprecated in 26 (0 +, 0 *, 0 ~, 2 -)

```
- FailedToCreateTests(Exception)  [API 1+]
- testSuiteConstructionFailed(): void  [API 1+]
```

## UnitTestSuiteBuilder — missing; API 36+, deprecated in 36 (0 +, 0 *, 0 ~, 2 -)

```
- UnitTestSuiteBuilder(Class)  [API 36+]
- UnitTestSuiteBuilder(String, ClassLoader)  [API 36+]
```


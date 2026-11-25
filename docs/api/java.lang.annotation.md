# java.lang.annotation

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Annotation — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ annotationType(): Class  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ toString(): String  [API 1+]
```

## AnnotationFormatError — declaration; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ AnnotationFormatError(String)  [API 1+]
+ AnnotationFormatError(String, Throwable)  [API 1+]
+ AnnotationFormatError(Throwable)  [API 1+]
```

## AnnotationTypeMismatchException — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- AnnotationTypeMismatchException(Method, String)  [API 1+]
- element(): Method  [API 1+]
- foundType(): String  [API 1+]
```

## Documented — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## ElementType — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ANNOTATION_TYPE [field]  [API 1+]
+ CONSTRUCTOR [field]  [API 1+]
+ FIELD [field]  [API 1+]
+ LOCAL_VARIABLE [field]  [API 1+]
+ METHOD [field]  [API 1+]
+ MODULE [field]  [API 31+]
+ PACKAGE [field]  [API 1+]
+ PARAMETER [field]  [API 1+]
+ RECORD_COMPONENT [field]  [API 34+]
+ TYPE [field]  [API 1+]
+ TYPE_PARAMETER [field]  [API 26+]
+ TYPE_USE [field]  [API 26+]
+ valueOf(String): ElementType [static]  [API 1+]
+ values(): ElementType[] [static]  [API 1+]
```

## IncompleteAnnotationException — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- IncompleteAnnotationException(Class, String)  [API 1+]
- annotationType(): Class  [API 1+]
- elementName(): String  [API 1+]
```

## Inherited — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## Native — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## Repeatable — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ value(): Class  [API 24+]
```

## Retention — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ value(): RetentionPolicy  [API 1+]
```

## RetentionPolicy — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CLASS [field]  [API 1+]
+ RUNTIME [field]  [API 1+]
+ SOURCE [field]  [API 1+]
+ valueOf(String): RetentionPolicy [static]  [API 1+]
+ values(): RetentionPolicy[] [static]  [API 1+]
```

## Target — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ value(): ElementType[]  [API 1+]
```


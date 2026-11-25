# java.lang.reflect

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AccessibleObject — framework (a2w.jdk.reflect.AccessibleObject); API 1+ (5 +, 0 *, 2 ~, 4 -)

```
+ AccessibleObject()  [API 1+]
~ getAnnotation(Class): Annotation  [API 1+]
+ getAnnotations(): Annotation[]  [API 1+]
- getAnnotationsByType(Class): Annotation[]  [API 1+]
- getDeclaredAnnotation(Class): Annotation  [API 1+]
+ getDeclaredAnnotations(): Annotation[]  [API 1+]
- getDeclaredAnnotationsByType(Class): Annotation[]  [API 1+]
+ isAccessible(): boolean  [API 1+]
~ isAnnotationPresent(Class): boolean  [API 1+]
- setAccessible(AccessibleObject[], boolean): void [static]  [API 1+]
+ setAccessible(boolean): void  [API 1+]
```

## AnnotatedElement — TeaVM class library; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ getAnnotation(Class): Annotation  [API 1+]
+ getAnnotations(): Annotation[]  [API 1+]
+ getAnnotationsByType(Class): Annotation[]  [API 24+]
+ getDeclaredAnnotation(Class): Annotation  [API 24+]
+ getDeclaredAnnotations(): Annotation[]  [API 1+]
+ getDeclaredAnnotationsByType(Class): Annotation[]  [API 24+]
+ isAnnotationPresent(Class): boolean  [API 1+]
```

## Array — TeaVM class library; API 1+ (21 +, 0 *, 0 ~, 0 -)

```
+ get(Object, int): Object [static]  [API 1+]
+ getBoolean(Object, int): boolean [static]  [API 1+]
+ getByte(Object, int): byte [static]  [API 1+]
+ getChar(Object, int): char [static]  [API 1+]
+ getDouble(Object, int): double [static]  [API 1+]
+ getFloat(Object, int): float [static]  [API 1+]
+ getInt(Object, int): int [static]  [API 1+]
+ getLength(Object): int [static]  [API 1+]
+ getLong(Object, int): long [static]  [API 1+]
+ getShort(Object, int): short [static]  [API 1+]
+ newInstance(Class, int): Object [static]  [API 1+]
+ newInstance(Class, int[]): Object [static]  [API 1+]
+ set(Object, int, Object): void [static]  [API 1+]
+ setBoolean(Object, int, boolean): void [static]  [API 1+]
+ setByte(Object, int, byte): void [static]  [API 1+]
+ setChar(Object, int, char): void [static]  [API 1+]
+ setDouble(Object, int, double): void [static]  [API 1+]
+ setFloat(Object, int, float): void [static]  [API 1+]
+ setInt(Object, int, int): void [static]  [API 1+]
+ setLong(Object, int, long): void [static]  [API 1+]
+ setShort(Object, int, short): void [static]  [API 1+]
```

## Constructor — framework (a2w.jdk.reflect.Constructor); API 1+ (9 +, 0 *, 2 ~, 8 -)

```
+ equals(Object): boolean  [API 1+]
~ getAnnotation(Class): Annotation  [API 1+]
+ getDeclaredAnnotations(): Annotation[]  [API 1+]
+ getDeclaringClass(): Class  [API 1+]
- getExceptionTypes(): Class[]  [API 1+]
- getGenericExceptionTypes(): Type[]  [API 1+]
- getGenericParameterTypes(): Type[]  [API 1+]
~ getModifiers(): int  [API 1+]
+ getName(): String  [API 1+]
- getParameterAnnotations(): Annotation[][]  [API 1+]
+ getParameterCount(): int  [API 1+]
+ getParameterTypes(): Class[]  [API 1+]
- getTypeParameters(): TypeVariable[]  [API 1+]
+ hashCode(): int  [API 1+]
- isSynthetic(): boolean  [API 1+]
- isVarArgs(): boolean  [API 1+]
+ newInstance(Object[]): Object  [API 1+]
- toGenericString(): String  [API 1+]
+ toString(): String  [API 1+]
```

## Executable — TeaVM class library; API 26+ (15 +, 0 *, 0 ~, 3 -)

```
+ getAnnotation(Class): Annotation  [API 26+]
+ getAnnotationsByType(Class): Annotation[]  [API 26+]
+ getDeclaredAnnotations(): Annotation[]  [API 26+]
+ getDeclaringClass(): Class  [API 26+]
+ getExceptionTypes(): Class[]  [API 26+]
- getGenericExceptionTypes(): Type[]  [API 26+]
+ getGenericParameterTypes(): Type[]  [API 26+]
+ getModifiers(): int  [API 26+]
+ getName(): String  [API 26+]
+ getParameterAnnotations(): Annotation[][]  [API 26+]
+ getParameterCount(): int  [API 26+]
+ getParameterTypes(): Class[]  [API 26+]
- getParameters(): Parameter[]  [API 26+]
+ getTypeParameters(): TypeVariable[]  [API 26+]
+ isAnnotationPresent(Class): boolean  [API 26+]
+ isSynthetic(): boolean  [API 26+]
+ isVarArgs(): boolean  [API 26+]
- toGenericString(): String  [API 26+]
```

## Field — framework (a2w.jdk.reflect.Field); API 1+ (30 +, 0 *, 1 ~, 2 -)

```
+ equals(Object): boolean  [API 1+]
+ get(Object): Object  [API 1+]
+ getAnnotation(Class): Annotation  [API 1+]
- getAnnotationsByType(Class): Annotation[]  [API 1+]
+ getBoolean(Object): boolean  [API 1+]
+ getByte(Object): byte  [API 1+]
+ getChar(Object): char  [API 1+]
+ getDeclaredAnnotations(): Annotation[]  [API 1+]
+ getDeclaringClass(): Class  [API 1+]
+ getDouble(Object): double  [API 1+]
+ getFloat(Object): float  [API 1+]
+ getGenericType(): Type  [API 1+]
+ getInt(Object): int  [API 1+]
+ getLong(Object): long  [API 1+]
+ getModifiers(): int  [API 1+]
+ getName(): String  [API 1+]
+ getShort(Object): short  [API 1+]
+ getType(): Class  [API 1+]
+ hashCode(): int  [API 1+]
+ isAnnotationPresent(Class): boolean  [API 1+]
+ isEnumConstant(): boolean  [API 1+]
~ isSynthetic(): boolean  [API 1+]
+ set(Object, Object): void  [API 1+]
+ setBoolean(Object, boolean): void  [API 1+]
+ setByte(Object, byte): void  [API 1+]
+ setChar(Object, char): void  [API 1+]
+ setDouble(Object, double): void  [API 1+]
+ setFloat(Object, float): void  [API 1+]
+ setInt(Object, int): void  [API 1+]
+ setLong(Object, long): void  [API 1+]
+ setShort(Object, short): void  [API 1+]
- toGenericString(): String  [API 1+]
+ toString(): String  [API 1+]
```

## GenericArrayType — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getGenericComponentType(): Type  [API 1+]
```

## GenericDeclaration — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getTypeParameters(): TypeVariable[]  [API 1+]
```

## GenericSignatureFormatError — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ GenericSignatureFormatError()  [API 1+]
+ GenericSignatureFormatError(String)  [API 26+]
```

## InvocationHandler — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- invoke(Object, Method, Object[]): Object  [API 1+]
```

## InvocationTargetException — framework (a2w.jdk.reflect.InvocationTargetException); API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ InvocationTargetException()  [API 1+]
+ InvocationTargetException(Throwable)  [API 1+]
+ InvocationTargetException(Throwable, String)  [API 1+]
+ getCause(): Throwable  [API 1+]
+ getTargetException(): Throwable  [API 1+]
```

## MalformedParameterizedTypeException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ MalformedParameterizedTypeException()  [API 1+]
+ MalformedParameterizedTypeException(String)  [API 33+]
```

## MalformedParametersException — declaration; API 28+ (2 +, 0 *, 0 ~, 0 -)

```
+ MalformedParametersException()  [API 28+]
+ MalformedParametersException(String)  [API 28+]
```

## Member — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ getDeclaringClass(): Class  [API 1+]
+ getModifiers(): int  [API 1+]
+ getName(): String  [API 1+]
+ isSynthetic(): boolean  [API 1+]
```

## Method — framework (a2w.jdk.reflect.Method); API 1+ (14 +, 0 *, 6 ~, 4 -)

```
+ equals(Object): boolean  [API 1+]
+ getAnnotation(Class): Annotation  [API 1+]
+ getDeclaredAnnotations(): Annotation[]  [API 1+]
+ getDeclaringClass(): Class  [API 1+]
- getDefaultValue(): Object  [API 1+]
+ getExceptionTypes(): Class[]  [API 1+]
- getGenericExceptionTypes(): Type[]  [API 1+]
+ getGenericParameterTypes(): Type[]  [API 1+]
+ getGenericReturnType(): Type  [API 1+]
~ getModifiers(): int  [API 1+]
+ getName(): String  [API 1+]
+ getParameterAnnotations(): Annotation[][]  [API 1+]
+ getParameterCount(): int  [API 1+]
+ getParameterTypes(): Class[]  [API 1+]
~ getReturnType(): Class  [API 1+]
- getTypeParameters(): TypeVariable[]  [API 1+]
+ hashCode(): int  [API 1+]
+ invoke(Object, Object[]): Object  [API 1+]
~ isBridge(): boolean  [API 1+]
~ isDefault(): boolean  [API 24+]
~ isSynthetic(): boolean  [API 1+]
~ isVarArgs(): boolean  [API 1+]
- toGenericString(): String  [API 1+]
+ toString(): String  [API 1+]
```

## Modifier — TeaVM class library; API 1+ (19 +, 0 *, 0 ~, 1 -)

```
- Modifier()  [API 1+]
+ classModifiers(): int [static]  [API 19+]
+ constructorModifiers(): int [static]  [API 19+]
+ fieldModifiers(): int [static]  [API 19+]
+ interfaceModifiers(): int [static]  [API 19+]
+ isAbstract(int): boolean [static]  [API 1+]
+ isFinal(int): boolean [static]  [API 1+]
+ isInterface(int): boolean [static]  [API 1+]
+ isNative(int): boolean [static]  [API 1+]
+ isPrivate(int): boolean [static]  [API 1+]
+ isProtected(int): boolean [static]  [API 1+]
+ isPublic(int): boolean [static]  [API 1+]
+ isStatic(int): boolean [static]  [API 1+]
+ isStrict(int): boolean [static]  [API 1+]
+ isSynchronized(int): boolean [static]  [API 1+]
+ isTransient(int): boolean [static]  [API 1+]
+ isVolatile(int): boolean [static]  [API 1+]
+ methodModifiers(): int [static]  [API 19+]
+ parameterModifiers(): int [static]  [API 26+]
+ toString(int): String [static]  [API 1+]
```

## Parameter — declaration; API 26+ (0 +, 0 *, 0 ~, 18 -)

```
- equals(Object): boolean  [API 26+]
- getAnnotation(Class): Annotation  [API 26+]
- getAnnotations(): Annotation[]  [API 26+]
- getAnnotationsByType(Class): Annotation[]  [API 26+]
- getDeclaredAnnotation(Class): Annotation  [API 26+]
- getDeclaredAnnotations(): Annotation[]  [API 26+]
- getDeclaredAnnotationsByType(Class): Annotation[]  [API 26+]
- getDeclaringExecutable(): Executable  [API 26+]
- getModifiers(): int  [API 26+]
- getName(): String  [API 26+]
- getParameterizedType(): Type  [API 26+]
- getType(): Class  [API 26+]
- hashCode(): int  [API 26+]
- isImplicit(): boolean  [API 26+]
- isNamePresent(): boolean  [API 26+]
- isSynthetic(): boolean  [API 26+]
- isVarArgs(): boolean  [API 26+]
- toString(): String  [API 26+]
```

## ParameterizedType — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ getActualTypeArguments(): Type[]  [API 1+]
+ getOwnerType(): Type  [API 1+]
+ getRawType(): Type  [API 1+]
```

## Proxy — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 1 -)

```
+ Proxy(InvocationHandler)  [API 1+]
+ getInvocationHandler(Object): InvocationHandler [static]  [API 1+]
- getProxyClass(ClassLoader, Class[]): Class [static]  [API 1+]
+ h [field]  [API 1+]
+ isProxyClass(Class): boolean [static]  [API 1+]
+ newProxyInstance(ClassLoader, Class[], InvocationHandler): Object [static]  [API 1+]
```

## RecordComponent — declaration; API 34+ (0 +, 0 *, 0 ~, 10 -)

```
- getAccessor(): Method  [API 34+]
- getAnnotation(Class): Annotation  [API 34+]
- getAnnotations(): Annotation[]  [API 34+]
- getDeclaredAnnotations(): Annotation[]  [API 34+]
- getDeclaringRecord(): Class  [API 34+]
- getGenericSignature(): String  [API 34+]
- getGenericType(): Type  [API 34+]
- getName(): String  [API 34+]
- getType(): Class  [API 34+]
- toString(): String  [API 34+]
```

## ReflectPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- ReflectPermission(String)  [API 1+]
- ReflectPermission(String, String)  [API 1+]
```

## Type — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getTypeName(): String  [API 28+]
```

## TypeVariable — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ getBounds(): Type[]  [API 1+]
+ getGenericDeclaration(): GenericDeclaration  [API 1+]
+ getName(): String  [API 1+]
```

## UndeclaredThrowableException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ UndeclaredThrowableException(Throwable)  [API 1+]
+ UndeclaredThrowableException(Throwable, String)  [API 1+]
+ getCause(): Throwable  [API 1+]
+ getUndeclaredThrowable(): Throwable  [API 1+]
```

## WildcardType — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ getLowerBounds(): Type[]  [API 1+]
+ getUpperBounds(): Type[]  [API 1+]
```


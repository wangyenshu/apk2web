# java.lang.invoke

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CallSite — TeaVM class library; API 26+ (0 +, 0 *, 0 ~, 4 -)

```
- dynamicInvoker(): MethodHandle  [API 26+]
- getTarget(): MethodHandle  [API 26+]
- setTarget(MethodHandle): void  [API 26+]
- type(): MethodType  [API 26+]
```

## ConstantCallSite — missing; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- ConstantCallSite(MethodHandle)  [API 26+]
- ConstantCallSite(MethodType, MethodHandle)  [API 26+]
- dynamicInvoker(): MethodHandle  [API 26+]
- getTarget(): MethodHandle  [API 26+]
- setTarget(MethodHandle): void  [API 26+]
```

## LambdaConversionException — TeaVM class library; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
+ LambdaConversionException()  [API 26+]
+ LambdaConversionException(String)  [API 26+]
+ LambdaConversionException(String, Throwable)  [API 26+]
+ LambdaConversionException(String, Throwable, boolean, boolean)  [API 26+]
+ LambdaConversionException(Throwable)  [API 26+]
```

## MethodHandle — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 15 -)

```
- asCollector(Class, int): MethodHandle  [API 26+]
- asCollector(int, Class, int): MethodHandle  [API 33+]
- asFixedArity(): MethodHandle  [API 26+]
- asSpreader(Class, int): MethodHandle  [API 26+]
- asSpreader(int, Class, int): MethodHandle  [API 33+]
- asType(MethodType): MethodHandle  [API 26+]
- asVarargsCollector(Class): MethodHandle  [API 26+]
- bindTo(Object): MethodHandle  [API 26+]
- invoke(Object[]): Object  [API 26+]
- invokeExact(Object[]): Object  [API 26+]
- invokeWithArguments(List): Object  [API 26+]
- invokeWithArguments(Object[]): Object  [API 26+]
- isVarargsCollector(): boolean  [API 26+]
+ toString(): String  [API 26+]
- type(): MethodType  [API 26+]
- withVarargs(boolean): MethodHandle  [API 33+]
```

## MethodHandleInfo — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 11 -)

```
- getDeclaringClass(): Class  [API 26+]
- getMethodType(): MethodType  [API 26+]
- getModifiers(): int  [API 26+]
- getName(): String  [API 26+]
- getReferenceKind(): int  [API 26+]
- isVarArgs(): boolean  [API 26+]
- refKindIsField(int): boolean [static]  [API 26+, deprecated in 29]
- refKindIsValid(int): boolean [static]  [API 26+, deprecated in 29]
- refKindName(int): String [static]  [API 26+, deprecated in 29]
+ referenceKindToString(int): String [static]  [API 26+]
- reflectAs(Class, MethodHandles.Lookup): Member  [API 26+]
- toString(int, Class, String, MethodType): String [static]  [API 26+]
```

## MethodHandles — TeaVM class library; API 26+ (0 +, 0 *, 0 ~, 43 -)

```
- arrayConstructor(Class): MethodHandle [static]  [API 33+]
- arrayElementGetter(Class): MethodHandle [static]  [API 26+]
- arrayElementSetter(Class): MethodHandle [static]  [API 26+]
- arrayElementVarHandle(Class): VarHandle [static]  [API 33+]
- arrayLength(Class): MethodHandle [static]  [API 33+]
- byteArrayViewVarHandle(Class, ByteOrder): VarHandle [static]  [API 33+]
- byteBufferViewVarHandle(Class, ByteOrder): VarHandle [static]  [API 33+]
- catchException(MethodHandle, Class, MethodHandle): MethodHandle [static]  [API 26+]
- collectArguments(MethodHandle, int, MethodHandle): MethodHandle [static]  [API 26+]
- constant(Class, Object): MethodHandle [static]  [API 26+]
- countedLoop(MethodHandle, MethodHandle, MethodHandle): MethodHandle [static]  [API 33+]
- countedLoop(MethodHandle, MethodHandle, MethodHandle, MethodHandle): MethodHandle [static]  [API 33+]
- doWhileLoop(MethodHandle, MethodHandle, MethodHandle): MethodHandle [static]  [API 33+]
- dropArguments(MethodHandle, int, Class[]): MethodHandle [static]  [API 26+]
- dropArguments(MethodHandle, int, List): MethodHandle [static]  [API 26+]
- dropArgumentsToMatch(MethodHandle, int, List, int): MethodHandle [static]  [API 33+]
- dropReturn(MethodHandle): MethodHandle [static]  [API 34+]
- empty(MethodType): MethodHandle [static]  [API 33+]
- exactInvoker(MethodType): MethodHandle [static]  [API 26+]
- explicitCastArguments(MethodHandle, MethodType): MethodHandle [static]  [API 26+]
- filterArguments(MethodHandle, int, MethodHandle[]): MethodHandle [static]  [API 26+]
- filterReturnValue(MethodHandle, MethodHandle): MethodHandle [static]  [API 26+]
- foldArguments(MethodHandle, MethodHandle): MethodHandle [static]  [API 26+]
- foldArguments(MethodHandle, int, MethodHandle): MethodHandle [static]  [API 33+]
- guardWithTest(MethodHandle, MethodHandle, MethodHandle): MethodHandle [static]  [API 26+]
- identity(Class): MethodHandle [static]  [API 26+]
- insertArguments(MethodHandle, int, Object[]): MethodHandle [static]  [API 26+]
- invoker(MethodType): MethodHandle [static]  [API 26+]
- iteratedLoop(MethodHandle, MethodHandle, MethodHandle): MethodHandle [static]  [API 33+]
- lookup(): MethodHandles.Lookup [static]  [API 26+]
- loop(MethodHandle[][]): MethodHandle [static]  [API 33+]
- permuteArguments(MethodHandle, MethodType, int[]): MethodHandle [static]  [API 26+]
- privateLookupIn(Class, MethodHandles.Lookup): MethodHandles.Lookup [static]  [API 33+]
- publicLookup(): MethodHandles.Lookup [static]  [API 26+]
- reflectAs(Class, MethodHandle): Member [static]  [API 26+]
- spreadInvoker(MethodType, int): MethodHandle [static]  [API 26+]
- tableSwitch(MethodHandle, MethodHandle[]): MethodHandle [static]  [API 34+]
- throwException(Class, Class): MethodHandle [static]  [API 26+]
- tryFinally(MethodHandle, MethodHandle): MethodHandle [static]  [API 33+]
- varHandleExactInvoker(VarHandle.AccessMode, MethodType): MethodHandle [static]  [API 33+]
- varHandleInvoker(VarHandle.AccessMode, MethodType): MethodHandle [static]  [API 33+]
- whileLoop(MethodHandle, MethodHandle, MethodHandle): MethodHandle [static]  [API 33+]
- zero(Class): MethodHandle [static]  [API 33+]
```

## MethodHandles.Lookup — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 21 -)

```
- bind(Object, String, MethodType): MethodHandle  [API 26+]
- findConstructor(Class, MethodType): MethodHandle  [API 26+]
- findGetter(Class, String, Class): MethodHandle  [API 26+]
- findSetter(Class, String, Class): MethodHandle  [API 26+]
- findSpecial(Class, String, MethodType, Class): MethodHandle  [API 26+]
- findStatic(Class, String, MethodType): MethodHandle  [API 26+]
- findStaticGetter(Class, String, Class): MethodHandle  [API 26+]
- findStaticSetter(Class, String, Class): MethodHandle  [API 26+]
- findStaticVarHandle(Class, String, Class): VarHandle  [API 33+]
- findVarHandle(Class, String, Class): VarHandle  [API 33+]
- findVirtual(Class, String, MethodType): MethodHandle  [API 26+]
- in(Class): MethodHandles.Lookup  [API 26+]
- lookupClass(): Class  [API 26+]
- lookupModes(): int  [API 26+]
- revealDirect(MethodHandle): MethodHandleInfo  [API 26+]
+ toString(): String  [API 26+]
- unreflect(Method): MethodHandle  [API 26+]
- unreflectConstructor(Constructor): MethodHandle  [API 26+]
- unreflectGetter(Field): MethodHandle  [API 26+]
- unreflectSetter(Field): MethodHandle  [API 26+]
- unreflectSpecial(Method, Class): MethodHandle  [API 26+]
- unreflectVarHandle(Field): VarHandle  [API 33+]
```

## MethodType — TeaVM class library; API 26+ (3 +, 0 *, 0 ~, 30 -)

```
- appendParameterTypes(Class[]): MethodType  [API 26+]
- appendParameterTypes(List): MethodType  [API 26+]
- changeParameterType(int, Class): MethodType  [API 26+]
- changeReturnType(Class): MethodType  [API 26+]
- descriptorString(): String  [API 26+]
- dropParameterTypes(int, int): MethodType  [API 26+]
+ equals(Object): boolean  [API 26+]
- erase(): MethodType  [API 26+]
- fromMethodDescriptorString(String, ClassLoader): MethodType [static]  [API 26+]
- generic(): MethodType  [API 26+]
- genericMethodType(int): MethodType [static]  [API 26+]
- genericMethodType(int, boolean): MethodType [static]  [API 26+]
- hasPrimitives(): boolean  [API 26+]
- hasWrappers(): boolean  [API 26+]
+ hashCode(): int  [API 26+]
- insertParameterTypes(int, Class[]): MethodType  [API 26+]
- insertParameterTypes(int, List): MethodType  [API 26+]
- lastParameterType(): Class  [API 33+]
- methodType(Class): MethodType [static]  [API 26+]
- methodType(Class, Class): MethodType [static]  [API 26+]
- methodType(Class, Class, Class[]): MethodType [static]  [API 26+]
- methodType(Class, Class[]): MethodType [static]  [API 26+]
- methodType(Class, List): MethodType [static]  [API 26+]
- methodType(Class, MethodType): MethodType [static]  [API 26+]
- parameterArray(): Class[]  [API 26+]
- parameterCount(): int  [API 26+]
- parameterList(): List  [API 26+]
- parameterType(int): Class  [API 26+]
- returnType(): Class  [API 26+]
- toMethodDescriptorString(): String  [API 26+]
+ toString(): String  [API 26+]
- unwrap(): MethodType  [API 26+]
- wrap(): MethodType  [API 26+]
```

## MutableCallSite — missing; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- MutableCallSite(MethodHandle)  [API 26+]
- MutableCallSite(MethodType)  [API 26+]
- dynamicInvoker(): MethodHandle  [API 26+]
- getTarget(): MethodHandle  [API 26+]
- setTarget(MethodHandle): void  [API 26+]
```

## TypeDescriptor — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ descriptorString(): String  [API 34+]
```

## TypeDescriptor.OfField — declaration; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ arrayType(): TypeDescriptor.OfField  [API 34+]
+ componentType(): TypeDescriptor.OfField  [API 34+]
+ isArray(): boolean  [API 34+]
+ isPrimitive(): boolean  [API 34+]
```

## TypeDescriptor.OfMethod — declaration; API 34+ (9 +, 0 *, 0 ~, 0 -)

```
+ changeParameterType(int, TypeDescriptor.OfField): TypeDescriptor.OfMethod  [API 34+]
+ changeReturnType(TypeDescriptor.OfField): TypeDescriptor.OfMethod  [API 34+]
+ dropParameterTypes(int, int): TypeDescriptor.OfMethod  [API 34+]
+ insertParameterTypes(int, TypeDescriptor.OfField[]): TypeDescriptor.OfMethod  [API 34+]
+ parameterArray(): TypeDescriptor.OfField[]  [API 34+]
+ parameterCount(): int  [API 34+]
+ parameterList(): List  [API 34+]
+ parameterType(int): TypeDescriptor.OfField  [API 34+]
+ returnType(): TypeDescriptor.OfField  [API 34+]
```

## VarHandle — declaration; API 33+ (0 +, 0 *, 0 ~, 42 -)

```
- accessModeType(VarHandle.AccessMode): MethodType  [API 33+]
- acquireFence(): void [static]  [API 33+]
- compareAndExchange(Object[]): Object  [API 33+]
- compareAndExchangeAcquire(Object[]): Object  [API 33+]
- compareAndExchangeRelease(Object[]): Object  [API 33+]
- compareAndSet(Object[]): boolean  [API 33+]
- coordinateTypes(): List  [API 33+]
- fullFence(): void [static]  [API 33+]
- get(Object[]): Object  [API 33+]
- getAcquire(Object[]): Object  [API 33+]
- getAndAdd(Object[]): Object  [API 33+]
- getAndAddAcquire(Object[]): Object  [API 33+]
- getAndAddRelease(Object[]): Object  [API 33+]
- getAndBitwiseAnd(Object[]): Object  [API 33+]
- getAndBitwiseAndAcquire(Object[]): Object  [API 33+]
- getAndBitwiseAndRelease(Object[]): Object  [API 33+]
- getAndBitwiseOr(Object[]): Object  [API 33+]
- getAndBitwiseOrAcquire(Object[]): Object  [API 33+]
- getAndBitwiseOrRelease(Object[]): Object  [API 33+]
- getAndBitwiseXor(Object[]): Object  [API 33+]
- getAndBitwiseXorAcquire(Object[]): Object  [API 33+]
- getAndBitwiseXorRelease(Object[]): Object  [API 33+]
- getAndSet(Object[]): Object  [API 33+]
- getAndSetAcquire(Object[]): Object  [API 33+]
- getAndSetRelease(Object[]): Object  [API 33+]
- getOpaque(Object[]): Object  [API 33+]
- getVolatile(Object[]): Object  [API 33+]
- isAccessModeSupported(VarHandle.AccessMode): boolean  [API 33+]
- loadLoadFence(): void [static]  [API 33+]
- releaseFence(): void [static]  [API 33+]
- set(Object[]): void  [API 33+]
- setOpaque(Object[]): void  [API 33+]
- setRelease(Object[]): void  [API 33+]
- setVolatile(Object[]): void  [API 33+]
- storeStoreFence(): void [static]  [API 33+]
- toMethodHandle(VarHandle.AccessMode): MethodHandle  [API 33+]
- toString(): String  [API 33+]
- varType(): Class  [API 33+]
- weakCompareAndSet(Object[]): boolean  [API 33+]
- weakCompareAndSetAcquire(Object[]): boolean  [API 33+]
- weakCompareAndSetPlain(Object[]): boolean  [API 33+]
- weakCompareAndSetRelease(Object[]): boolean  [API 33+]
```

## VarHandle.AccessMode — declaration; API 33+ (2 +, 0 *, 0 ~, 2 -)

```
+ COMPARE_AND_EXCHANGE [field]  [API 33+]
+ COMPARE_AND_EXCHANGE_ACQUIRE [field]  [API 33+]
+ COMPARE_AND_EXCHANGE_RELEASE [field]  [API 33+]
+ COMPARE_AND_SET [field]  [API 33+]
+ GET [field]  [API 33+]
+ GET_ACQUIRE [field]  [API 33+]
+ GET_AND_ADD [field]  [API 33+]
+ GET_AND_ADD_ACQUIRE [field]  [API 33+]
+ GET_AND_ADD_RELEASE [field]  [API 33+]
+ GET_AND_BITWISE_AND [field]  [API 33+]
+ GET_AND_BITWISE_AND_ACQUIRE [field]  [API 33+]
+ GET_AND_BITWISE_AND_RELEASE [field]  [API 33+]
+ GET_AND_BITWISE_OR [field]  [API 33+]
+ GET_AND_BITWISE_OR_ACQUIRE [field]  [API 33+]
+ GET_AND_BITWISE_OR_RELEASE [field]  [API 33+]
+ GET_AND_BITWISE_XOR [field]  [API 33+]
+ GET_AND_BITWISE_XOR_ACQUIRE [field]  [API 33+]
+ GET_AND_BITWISE_XOR_RELEASE [field]  [API 33+]
+ GET_AND_SET [field]  [API 33+]
+ GET_AND_SET_ACQUIRE [field]  [API 33+]
+ GET_AND_SET_RELEASE [field]  [API 33+]
+ GET_OPAQUE [field]  [API 33+]
+ GET_VOLATILE [field]  [API 33+]
+ SET [field]  [API 33+]
+ SET_OPAQUE [field]  [API 33+]
+ SET_RELEASE [field]  [API 33+]
+ SET_VOLATILE [field]  [API 33+]
+ WEAK_COMPARE_AND_SET [field]  [API 33+]
+ WEAK_COMPARE_AND_SET_ACQUIRE [field]  [API 33+]
+ WEAK_COMPARE_AND_SET_PLAIN [field]  [API 33+]
+ WEAK_COMPARE_AND_SET_RELEASE [field]  [API 33+]
- methodName(): String  [API 33+]
- valueFromMethodName(String): VarHandle.AccessMode [static]  [API 33+]
+ valueOf(String): VarHandle.AccessMode [static]  [API 33+]
+ values(): VarHandle.AccessMode[] [static]  [API 33+]
```

## VolatileCallSite — missing; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- VolatileCallSite(MethodHandle)  [API 26+]
- VolatileCallSite(MethodType)  [API 26+]
- dynamicInvoker(): MethodHandle  [API 26+]
- getTarget(): MethodHandle  [API 26+]
- setTarget(MethodHandle): void  [API 26+]
```

## WrongMethodTypeException — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ WrongMethodTypeException()  [API 26+]
+ WrongMethodTypeException(String)  [API 26+]
```


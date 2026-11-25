# android.view.inspector

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## InspectionCompanion — declaration; API 29+ (2 +, 0 *, 0 ~, 0 -)

```
+ mapProperties(PropertyMapper): void  [API 29+]
+ readProperties(Object, PropertyReader): void  [API 29+]
```

## InspectionCompanion.UninitializedPropertyMapException — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ UninitializedPropertyMapException()  [API 29+]
```

## InspectionCompanionProvider — declaration; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ provide(Class): InspectionCompanion  [API 29+]
```

## IntFlagMapping — declaration; API 29+ (0 +, 0 *, 0 ~, 3 -)

```
- IntFlagMapping()  [API 29+]
- add(int, int, String): void  [API 29+]
- get(int): Set  [API 29+]
```

## PropertyMapper — declaration; API 29+ (14 +, 0 *, 0 ~, 0 -)

```
+ mapBoolean(String, int): int  [API 29+]
+ mapByte(String, int): int  [API 29+]
+ mapChar(String, int): int  [API 29+]
+ mapColor(String, int): int  [API 29+]
+ mapDouble(String, int): int  [API 29+]
+ mapFloat(String, int): int  [API 29+]
+ mapGravity(String, int): int  [API 29+]
+ mapInt(String, int): int  [API 29+]
+ mapIntEnum(String, int, IntFunction): int  [API 29+]
+ mapIntFlag(String, int, IntFunction): int  [API 29+]
+ mapLong(String, int): int  [API 29+]
+ mapObject(String, int): int  [API 29+]
+ mapResourceId(String, int): int  [API 29+]
+ mapShort(String, int): int  [API 29+]
```

## PropertyMapper.PropertyConflictException — declaration; API 29+ (0 +, 0 *, 0 ~, 1 -)

```
- PropertyConflictException(String, String, String)  [API 29+]
```

## PropertyReader — declaration; API 29+ (16 +, 0 *, 0 ~, 0 -)

```
+ readBoolean(int, boolean): void  [API 29+]
+ readByte(int, byte): void  [API 29+]
+ readChar(int, char): void  [API 29+]
+ readColor(int, Color): void  [API 29+]
+ readColor(int, int): void  [API 29+]
+ readColor(int, long): void  [API 29+]
+ readDouble(int, double): void  [API 29+]
+ readFloat(int, float): void  [API 29+]
+ readGravity(int, int): void  [API 29+]
+ readInt(int, int): void  [API 29+]
+ readIntEnum(int, int): void  [API 29+]
+ readIntFlag(int, int): void  [API 29+]
+ readLong(int, long): void  [API 29+]
+ readObject(int, Object): void  [API 29+]
+ readResourceId(int, int): void  [API 29+]
+ readShort(int, short): void  [API 29+]
```

## PropertyReader.PropertyTypeMismatchException — declaration; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
- PropertyTypeMismatchException(int, String, String)  [API 29+]
- PropertyTypeMismatchException(int, String, String, String)  [API 29+]
```

## StaticInspectionCompanionProvider — declaration; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
- StaticInspectionCompanionProvider()  [API 29+]
- provide(Class): InspectionCompanion  [API 29+]
```

## WindowInspector — declaration; API 29+ (0 +, 0 *, 0 ~, 1 -)

```
- getGlobalWindowViews(): List [static]  [API 29+]
```


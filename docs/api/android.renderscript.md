# android.renderscript

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Allocation — declaration; API 11+, deprecated in 31 (0 +, 2 *, 0 ~, 89 -)

```
- copy1DRangeFrom(int, int, Allocation, int): void  [API 14+]
- copy1DRangeFrom(int, int, Object): void  [API 21+]
- copy1DRangeFrom(int, int, byte[]): void  [API 11+]
- copy1DRangeFrom(int, int, float[]): void  [API 11+]
- copy1DRangeFrom(int, int, int[]): void  [API 11+]
- copy1DRangeFrom(int, int, short[]): void  [API 11+]
- copy1DRangeFromUnchecked(int, int, Object): void  [API 21+]
- copy1DRangeFromUnchecked(int, int, byte[]): void  [API 11+]
- copy1DRangeFromUnchecked(int, int, float[]): void  [API 11+]
- copy1DRangeFromUnchecked(int, int, int[]): void  [API 11+]
- copy1DRangeFromUnchecked(int, int, short[]): void  [API 11+]
- copy1DRangeTo(int, int, Object): void  [API 23+]
- copy1DRangeTo(int, int, byte[]): void  [API 23+]
- copy1DRangeTo(int, int, float[]): void  [API 23+]
- copy1DRangeTo(int, int, int[]): void  [API 23+]
- copy1DRangeTo(int, int, short[]): void  [API 23+]
- copy1DRangeToUnchecked(int, int, Object): void  [API 23+]
- copy1DRangeToUnchecked(int, int, byte[]): void  [API 23+]
- copy1DRangeToUnchecked(int, int, float[]): void  [API 23+]
- copy1DRangeToUnchecked(int, int, int[]): void  [API 23+]
- copy1DRangeToUnchecked(int, int, short[]): void  [API 23+]
- copy2DRangeFrom(int, int, Bitmap): void  [API 11+]
- copy2DRangeFrom(int, int, int, int, Allocation, int, int): void  [API 14+]
- copy2DRangeFrom(int, int, int, int, Object): void  [API 21+]
- copy2DRangeFrom(int, int, int, int, byte[]): void  [API 11+]
- copy2DRangeFrom(int, int, int, int, float[]): void  [API 11+]
- copy2DRangeFrom(int, int, int, int, int[]): void  [API 11+]
- copy2DRangeFrom(int, int, int, int, short[]): void  [API 11+]
- copy2DRangeTo(int, int, int, int, Object): void  [API 23+]
- copy2DRangeTo(int, int, int, int, byte[]): void  [API 23+]
- copy2DRangeTo(int, int, int, int, float[]): void  [API 23+]
- copy2DRangeTo(int, int, int, int, int[]): void  [API 23+]
- copy2DRangeTo(int, int, int, int, short[]): void  [API 23+]
- copy3DRangeFrom(int, int, int, int, int, int, Allocation, int, int, int): void  [API 23+]
- copy3DRangeFrom(int, int, int, int, int, int, Object): void  [API 23+]
- copy3DRangeTo(int, int, int, int, int, int, Object): void  [API 23+]
- copyFrom(Allocation): void  [API 18+]
- copyFrom(BaseObj[]): void  [API 11+]
- copyFrom(Bitmap): void  [API 11+]
- copyFrom(Object): void  [API 21+]
- copyFrom(byte[]): void  [API 11+]
- copyFrom(float[]): void  [API 11+]
- copyFrom(int[]): void  [API 11+]
- copyFrom(short[]): void  [API 11+]
- copyFromUnchecked(Object): void  [API 21+]
- copyFromUnchecked(byte[]): void  [API 11+]
- copyFromUnchecked(float[]): void  [API 11+]
- copyFromUnchecked(int[]): void  [API 11+]
- copyFromUnchecked(short[]): void  [API 11+]
- copyTo(Bitmap): void  [API 11+]
- copyTo(Object): void  [API 21+]
- copyTo(byte[]): void  [API 11+]
- copyTo(float[]): void  [API 11+]
- copyTo(int[]): void  [API 11+]
- copyTo(short[]): void  [API 11+]
- createAllocations(RenderScript, Type, int, int): Allocation[] [static]  [API 24+]
- createCubemapFromBitmap(RenderScript, Bitmap): Allocation [static]  [API 11+]
- createCubemapFromBitmap(RenderScript, Bitmap, Allocation.MipmapControl, int): Allocation [static]  [API 11+]
- createCubemapFromCubeFaces(RenderScript, Bitmap, Bitmap, Bitmap, Bitmap, Bitmap, Bitmap): Allocation [static]  [API 11+]
- createCubemapFromCubeFaces(RenderScript, Bitmap, Bitmap, Bitmap, Bitmap, Bitmap, Bitmap, Allocation.MipmapControl, int): Allocation [static]  [API 11+]
- createFromBitmap(RenderScript, Bitmap): Allocation [static]  [API 11+]
- createFromBitmap(RenderScript, Bitmap, Allocation.MipmapControl, int): Allocation [static]  [API 11+]
- createFromBitmapResource(RenderScript, Resources, int): Allocation [static]  [API 11+]
- createFromBitmapResource(RenderScript, Resources, int, Allocation.MipmapControl, int): Allocation [static]  [API 11+]
- createFromString(RenderScript, String, int): Allocation [static]  [API 11+]
- createSized(RenderScript, Element, int): Allocation [static]  [API 11+]
- createSized(RenderScript, Element, int, int): Allocation [static]  [API 11+]
- createTyped(RenderScript, Type): Allocation [static]  [API 11+]
- createTyped(RenderScript, Type, Allocation.MipmapControl, int): Allocation [static]  [API 11+]
- createTyped(RenderScript, Type, int): Allocation [static]  [API 11+]
- destroy(): void  [API 11+]
- finalize(): void  [API 11+]
- generateMipmaps(): void  [API 11+]
- getByteBuffer(): ByteBuffer  [API 24+]
- getBytesSize(): int  [API 16+]
- getElement(): Element  [API 16+]
- getStride(): long  [API 24+]
* getSurface(): Surface  [API 16+]
- getTimeStamp(): long  [API 24+]
- getType(): Type  [API 11+]
- getUsage(): int  [API 16+]
- ioReceive(): void  [API 16+]
- ioSend(): void  [API 16+]
- resize(int): void  [API 11+, deprecated in 18]
- setAutoPadding(boolean): void  [API 23+]
- setFromFieldPacker(int, FieldPacker): void  [API 11+]
- setFromFieldPacker(int, int, FieldPacker): void  [API 11+]
- setFromFieldPacker(int, int, int, int, FieldPacker): void  [API 23+]
- setOnBufferAvailableListener(Allocation.OnBufferAvailableListener): void  [API 19+]
* setSurface(Surface): void  [API 16+]
- syncAll(int): void  [API 11+]
```

## Allocation.MipmapControl — declaration; API 11+, deprecated in 31 (2 +, 0 *, 0 ~, 0 -)

```
+ MIPMAP_FULL [field]  [API 11+]
+ MIPMAP_NONE [field]  [API 11+]
+ MIPMAP_ON_SYNC_TO_TEXTURE [field]  [API 11+]
+ valueOf(String): Allocation.MipmapControl [static]  [API 11+]
+ values(): Allocation.MipmapControl[] [static]  [API 11+]
```

## Allocation.OnBufferAvailableListener — declaration; API 19+, deprecated in 31 (1 +, 0 *, 0 ~, 0 -)

```
+ onBufferAvailable(Allocation): void  [API 19+]
```

## AllocationAdapter — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 19 -)

```
- create1D(RenderScript, Allocation): AllocationAdapter [static]  [API 14+]
- create2D(RenderScript, Allocation): AllocationAdapter [static]  [API 11+]
- createTyped(RenderScript, Allocation, Type): AllocationAdapter [static]  [API 23+]
- readData(float[]): void  [API 11-13, removed in 14]
- readData(int[]): void  [API 11-13, removed in 14]
- resize(int): void  [API 11+]
- setFace(Type.CubemapFace): void  [API 11+]
- setLOD(int): void  [API 11+]
- setX(int): void  [API 23+]
- setY(int): void  [API 11+]
- setZ(int): void  [API 11+]
- subData(int, FieldPacker): void  [API 11-13, removed in 14]
- subData1D(int, int, byte[]): void  [API 11-13, removed in 14]
- subData1D(int, int, float[]): void  [API 11-13, removed in 14]
- subData1D(int, int, int[]): void  [API 11-13, removed in 14]
- subData1D(int, int, short[]): void  [API 11-13, removed in 14]
- subData2D(int, int, int, int, float[]): void  [API 11-13, removed in 14]
- subData2D(int, int, int, int, int[]): void  [API 11-13, removed in 14]
- subElementData(int, int, FieldPacker): void  [API 11-13, removed in 14]
```

## BaseObj — declaration; API 11+, deprecated in 31 (0 +, 4 *, 0 ~, 2 -)

```
- destroy(): void  [API 11+]
* equals(Object): boolean  [API 11+]
- finalize(): void  [API 11+]
* getName(): String  [API 14+]
* hashCode(): int  [API 11+]
* setName(String): void  [API 11+]
```

## Byte2 — declaration; API 11+, deprecated in 31 (1 +, 0 *, 0 ~, 1 -)

```
+ Byte2()  [API 11+]
- Byte2(byte, byte)  [API 14+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
```

## Byte3 — declaration; API 11+, deprecated in 31 (1 +, 0 *, 0 ~, 1 -)

```
+ Byte3()  [API 11+]
- Byte3(byte, byte, byte)  [API 14+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Byte4 — declaration; API 11+, deprecated in 31 (1 +, 0 *, 0 ~, 1 -)

```
+ Byte4()  [API 11+]
- Byte4(byte, byte, byte, byte)  [API 14+]
+ w [field]  [API 11+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Double2 — declaration; API 14+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Double2()  [API 14+]
* Double2(double, double)  [API 14+]
+ x [field]  [API 14+]
+ y [field]  [API 14+]
```

## Double3 — declaration; API 14+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Double3()  [API 14+]
* Double3(double, double, double)  [API 14+]
+ x [field]  [API 14+]
+ y [field]  [API 14+]
+ z [field]  [API 14+]
```

## Double4 — declaration; API 14+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Double4()  [API 14+]
* Double4(double, double, double, double)  [API 14+]
+ w [field]  [API 14+]
+ x [field]  [API 14+]
+ y [field]  [API 14+]
+ z [field]  [API 14+]
```

## Element — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 80 -)

```
- ALLOCATION(RenderScript): Element [static]  [API 11+]
- A_8(RenderScript): Element [static]  [API 11+]
- BOOLEAN(RenderScript): Element [static]  [API 11+]
- ELEMENT(RenderScript): Element [static]  [API 11+]
- F16(RenderScript): Element [static]  [API 23+]
- F16_2(RenderScript): Element [static]  [API 23+]
- F16_3(RenderScript): Element [static]  [API 23+]
- F16_4(RenderScript): Element [static]  [API 23+]
- F32(RenderScript): Element [static]  [API 11+]
- F32_2(RenderScript): Element [static]  [API 11+]
- F32_3(RenderScript): Element [static]  [API 11+]
- F32_4(RenderScript): Element [static]  [API 11+]
- F64(RenderScript): Element [static]  [API 11+]
- F64_2(RenderScript): Element [static]  [API 14+]
- F64_3(RenderScript): Element [static]  [API 14+]
- F64_4(RenderScript): Element [static]  [API 14+]
- FONT(RenderScript): Element [static]  [API 16+]
- I16(RenderScript): Element [static]  [API 11+]
- I16_2(RenderScript): Element [static]  [API 14+]
- I16_3(RenderScript): Element [static]  [API 14+]
- I16_4(RenderScript): Element [static]  [API 14+]
- I32(RenderScript): Element [static]  [API 11+]
- I32_2(RenderScript): Element [static]  [API 14+]
- I32_3(RenderScript): Element [static]  [API 14+]
- I32_4(RenderScript): Element [static]  [API 14+]
- I64(RenderScript): Element [static]  [API 11+]
- I64_2(RenderScript): Element [static]  [API 14+]
- I64_3(RenderScript): Element [static]  [API 14+]
- I64_4(RenderScript): Element [static]  [API 14+]
- I8(RenderScript): Element [static]  [API 11+]
- I8_2(RenderScript): Element [static]  [API 14+]
- I8_3(RenderScript): Element [static]  [API 14+]
- I8_4(RenderScript): Element [static]  [API 14+]
- MATRIX4X4(RenderScript): Element [static]  [API 11+, deprecated in 16]
- MATRIX_2X2(RenderScript): Element [static]  [API 11+]
- MATRIX_3X3(RenderScript): Element [static]  [API 11+]
- MATRIX_4X4(RenderScript): Element [static]  [API 11+]
- MESH(RenderScript): Element [static]  [API 11+]
- PROGRAM_FRAGMENT(RenderScript): Element [static]  [API 11+]
- PROGRAM_RASTER(RenderScript): Element [static]  [API 11+]
- PROGRAM_STORE(RenderScript): Element [static]  [API 11+]
- PROGRAM_VERTEX(RenderScript): Element [static]  [API 11+]
- RGBA_4444(RenderScript): Element [static]  [API 11+]
- RGBA_5551(RenderScript): Element [static]  [API 11+]
- RGBA_8888(RenderScript): Element [static]  [API 11+]
- RGB_565(RenderScript): Element [static]  [API 11+]
- RGB_888(RenderScript): Element [static]  [API 11+]
- SAMPLER(RenderScript): Element [static]  [API 11+]
- SCRIPT(RenderScript): Element [static]  [API 11+]
- TYPE(RenderScript): Element [static]  [API 11+]
- U16(RenderScript): Element [static]  [API 11+]
- U16_2(RenderScript): Element [static]  [API 14+]
- U16_3(RenderScript): Element [static]  [API 14+]
- U16_4(RenderScript): Element [static]  [API 14+]
- U32(RenderScript): Element [static]  [API 11+]
- U32_2(RenderScript): Element [static]  [API 14+]
- U32_3(RenderScript): Element [static]  [API 14+]
- U32_4(RenderScript): Element [static]  [API 14+]
- U64(RenderScript): Element [static]  [API 11+]
- U64_2(RenderScript): Element [static]  [API 14+]
- U64_3(RenderScript): Element [static]  [API 14+]
- U64_4(RenderScript): Element [static]  [API 14+]
- U8(RenderScript): Element [static]  [API 11+]
- U8_2(RenderScript): Element [static]  [API 14+]
- U8_3(RenderScript): Element [static]  [API 14+]
- U8_4(RenderScript): Element [static]  [API 11+]
- YUV(RenderScript): Element [static]  [API 19+]
- createPixel(RenderScript, Element.DataType, Element.DataKind): Element [static]  [API 11+]
- createVector(RenderScript, Element.DataType, int): Element [static]  [API 11+]
- getBytesSize(): int  [API 16+]
- getDataKind(): Element.DataKind  [API 16+]
- getDataType(): Element.DataType  [API 16+]
- getSubElement(int): Element  [API 16+]
- getSubElementArraySize(int): int  [API 16+]
- getSubElementCount(): int  [API 16+]
- getSubElementName(int): String  [API 16+]
- getSubElementOffsetBytes(int): int  [API 16+]
- getVectorSize(): int  [API 16+]
- isCompatible(Element): boolean  [API 14+]
- isComplex(): boolean  [API 11+]
```

## Element.Builder — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 4 -)

```
- Builder(RenderScript)  [API 11+]
- add(Element, String): Element.Builder  [API 11+]
- add(Element, String, int): Element.Builder  [API 11+]
- create(): Element  [API 11+]
```

## Element.DataKind — declaration; API 11+, deprecated in 31 (2 +, 0 *, 0 ~, 0 -)

```
+ PIXEL_A [field]  [API 11+]
+ PIXEL_DEPTH [field]  [API 14+]
+ PIXEL_L [field]  [API 11+]
+ PIXEL_LA [field]  [API 11+]
+ PIXEL_RGB [field]  [API 11+]
+ PIXEL_RGBA [field]  [API 11+]
+ PIXEL_YUV [field]  [API 18+]
+ USER [field]  [API 11+]
+ valueOf(String): Element.DataKind [static]  [API 11+]
+ values(): Element.DataKind[] [static]  [API 11+]
```

## Element.DataType — declaration; API 11+, deprecated in 31 (2 +, 0 *, 0 ~, 0 -)

```
+ BOOLEAN [field]  [API 11+]
+ FLOAT_16 [field]  [API 23+]
+ FLOAT_32 [field]  [API 11+]
+ FLOAT_64 [field]  [API 11+]
+ MATRIX_2X2 [field]  [API 11+]
+ MATRIX_3X3 [field]  [API 11+]
+ MATRIX_4X4 [field]  [API 11+]
+ NONE [field]  [API 16+]
+ RS_ALLOCATION [field]  [API 11+]
+ RS_ELEMENT [field]  [API 11+]
+ RS_FONT [field]  [API 16+]
+ RS_MESH [field]  [API 11+]
+ RS_PROGRAM_FRAGMENT [field]  [API 11+]
+ RS_PROGRAM_RASTER [field]  [API 11+]
+ RS_PROGRAM_STORE [field]  [API 11+]
+ RS_PROGRAM_VERTEX [field]  [API 11+]
+ RS_SAMPLER [field]  [API 11+]
+ RS_SCRIPT [field]  [API 11+]
+ RS_TYPE [field]  [API 11+]
+ SIGNED_16 [field]  [API 11+]
+ SIGNED_32 [field]  [API 11+]
+ SIGNED_64 [field]  [API 11+]
+ SIGNED_8 [field]  [API 11+]
+ UNSIGNED_16 [field]  [API 11+]
+ UNSIGNED_32 [field]  [API 11+]
+ UNSIGNED_4_4_4_4 [field]  [API 11+]
+ UNSIGNED_5_5_5_1 [field]  [API 11+]
+ UNSIGNED_5_6_5 [field]  [API 11+]
+ UNSIGNED_64 [field]  [API 11+]
+ UNSIGNED_8 [field]  [API 11+]
+ valueOf(String): Element.DataType [static]  [API 11+]
+ values(): Element.DataType[] [static]  [API 11+]
```

## FieldPacker — declaration; API 11+, deprecated in 31 (0 +, 2 *, 0 ~, 79 -)

```
* FieldPacker(byte[])  [API 18+]
- FieldPacker(int)  [API 11+]
- addBoolean(boolean): void  [API 11+]
- addF32(Float2): void  [API 11+]
- addF32(Float3): void  [API 11+]
- addF32(Float4): void  [API 11+]
- addF32(float): void  [API 11+]
- addF64(Double2): void  [API 14+]
- addF64(Double3): void  [API 14+]
- addF64(Double4): void  [API 14+]
- addF64(double): void  [API 11+]
- addI16(Short2): void  [API 11+]
- addI16(Short3): void  [API 11+]
- addI16(Short4): void  [API 11+]
- addI16(short): void  [API 11+]
- addI32(Int2): void  [API 11+]
- addI32(Int3): void  [API 11+]
- addI32(Int4): void  [API 11+]
- addI32(int): void  [API 11+]
- addI64(Long2): void  [API 14+]
- addI64(Long3): void  [API 14+]
- addI64(Long4): void  [API 14+]
- addI64(long): void  [API 11+]
- addI8(Byte2): void  [API 11+]
- addI8(Byte3): void  [API 11+]
- addI8(Byte4): void  [API 11+]
- addI8(byte): void  [API 11+]
- addMatrix(Matrix2f): void  [API 11+]
- addMatrix(Matrix3f): void  [API 11+]
- addMatrix(Matrix4f): void  [API 11+]
- addObj(BaseObj): void  [API 11+]
- addU16(Int2): void  [API 11+]
- addU16(Int3): void  [API 11+]
- addU16(Int4): void  [API 11+]
- addU16(int): void  [API 11+]
- addU32(Long2): void  [API 11+]
- addU32(Long3): void  [API 11+]
- addU32(Long4): void  [API 11+]
- addU32(long): void  [API 11+]
- addU64(Long2): void  [API 14+]
- addU64(Long3): void  [API 14+]
- addU64(Long4): void  [API 14+]
- addU64(long): void  [API 11+]
- addU8(Short2): void  [API 11+]
- addU8(Short3): void  [API 11+]
- addU8(Short4): void  [API 11+]
- addU8(short): void  [API 11+]
- align(int): void  [API 11+]
* getData(): byte[]  [API 11+]
- reset(): void  [API 11+]
- reset(int): void  [API 11+]
- skip(int): void  [API 11+]
- subBoolean(): boolean  [API 18+]
- subByte2(): Byte2  [API 18+]
- subByte3(): Byte3  [API 18+]
- subByte4(): Byte4  [API 18+]
- subDouble2(): Double2  [API 18+]
- subDouble3(): Double3  [API 18+]
- subDouble4(): Double4  [API 18+]
- subF32(): float  [API 18+]
- subF64(): double  [API 18+]
- subFloat2(): Float2  [API 18+]
- subFloat3(): Float3  [API 18+]
- subFloat4(): Float4  [API 18+]
- subI16(): short  [API 18+]
- subI32(): int  [API 18+]
- subI64(): long  [API 18+]
- subI8(): byte  [API 18+]
- subInt2(): Int2  [API 18+]
- subInt3(): Int3  [API 18+]
- subInt4(): Int4  [API 18+]
- subLong2(): Long2  [API 18+]
- subLong3(): Long3  [API 18+]
- subLong4(): Long4  [API 18+]
- subMatrix2f(): Matrix2f  [API 18+]
- subMatrix3f(): Matrix3f  [API 18+]
- subMatrix4f(): Matrix4f  [API 18+]
- subShort2(): Short2  [API 18+]
- subShort3(): Short3  [API 18+]
- subShort4(): Short4  [API 18+]
- subalign(int): void  [API 18+]
```

## FileA3D — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 6 -)

```
- createFromAsset(RenderScript, AssetManager, String): FileA3D  [API 11-17, removed in 18]
- createFromFile(RenderScript, File): FileA3D  [API 11-17, removed in 18]
- createFromFile(RenderScript, String): FileA3D  [API 11-17, removed in 18]
- createFromResource(RenderScript, Resources, int): FileA3D  [API 11-17, removed in 18]
- getIndexEntry(int): FileA3D.IndexEntry  [API 11-17, removed in 18]
- getIndexEntryCount(): int  [API 11-17, removed in 18]
```

## FileA3D.EntryType — declaration; API 11-17, removed in 18, deprecated in 16 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): FileA3D.EntryType  [API 11-17, removed in 18]
+ values(): FileA3D.EntryType[]  [API 11-17, removed in 18]
```

## FileA3D.IndexEntry — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 4 -)

```
- getEntryType(): FileA3D.EntryType  [API 11-17, removed in 18]
- getMesh(): Mesh  [API 11-17, removed in 18]
- getName(): String  [API 11-17, removed in 18]
- getObject(): BaseObj  [API 11-17, removed in 18]
```

## Float2 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Float2()  [API 11+]
* Float2(float, float)  [API 11+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
```

## Float3 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Float3()  [API 11+]
* Float3(float, float, float)  [API 11+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Float4 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Float4()  [API 11+]
* Float4(float, float, float, float)  [API 11+]
+ w [field]  [API 11+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Font — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 5 -)

```
- create(RenderScript, Resources, String, Font.Style, float): Font  [API 11-17, removed in 18]
- createFromAsset(RenderScript, Resources, String, float): Font  [API 11-17, removed in 18]
- createFromFile(RenderScript, Resources, File, float): Font  [API 11-17, removed in 18]
- createFromFile(RenderScript, Resources, String, float): Font  [API 11-17, removed in 18]
- createFromResource(RenderScript, Resources, int, float): Font  [API 11-17, removed in 18]
```

## Font.Style — declaration; API 11-17, removed in 18, deprecated in 16 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): Font.Style  [API 11-17, removed in 18]
+ values(): Font.Style[]  [API 11-17, removed in 18]
```

## Int2 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Int2()  [API 11+]
* Int2(int, int)  [API 14+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
```

## Int3 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Int3()  [API 11+]
* Int3(int, int, int)  [API 14+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Int4 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Int4()  [API 11+]
* Int4(int, int, int, int)  [API 14+]
+ w [field]  [API 11+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Long2 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Long2()  [API 11+]
* Long2(long, long)  [API 14+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
```

## Long3 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Long3()  [API 11+]
* Long3(long, long, long)  [API 14+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Long4 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Long4()  [API 11+]
* Long4(long, long, long, long)  [API 14+]
+ w [field]  [API 11+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Matrix2f — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 14 -)

```
- Matrix2f()  [API 11+]
- Matrix2f(float[])  [API 11+]
- get(int, int): float  [API 11+]
- getArray(): float[]  [API 11+]
- load(Matrix2f): void  [API 11+]
- loadIdentity(): void  [API 11+]
- loadMultiply(Matrix2f, Matrix2f): void  [API 11+]
- loadRotate(float): void  [API 11+]
- loadScale(float, float): void  [API 11+]
- multiply(Matrix2f): void  [API 11+]
- rotate(float): void  [API 11+]
- scale(float, float): void  [API 11+]
- set(int, int, float): void  [API 11+]
- transpose(): void  [API 11+]
```

## Matrix3f — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 20 -)

```
- Matrix3f()  [API 11+]
- Matrix3f(float[])  [API 11+]
- get(int, int): float  [API 11+]
- getArray(): float[]  [API 11+]
- load(Matrix3f): void  [API 11+]
- loadIdentity(): void  [API 11+]
- loadMultiply(Matrix3f, Matrix3f): void  [API 11+]
- loadRotate(float): void  [API 11+]
- loadRotate(float, float, float, float): void  [API 11+]
- loadScale(float, float): void  [API 11+]
- loadScale(float, float, float): void  [API 11+]
- loadTranslate(float, float): void  [API 11+]
- multiply(Matrix3f): void  [API 11+]
- rotate(float): void  [API 11+]
- rotate(float, float, float, float): void  [API 11+]
- scale(float, float): void  [API 11+]
- scale(float, float, float): void  [API 11+]
- set(int, int, float): void  [API 11+]
- translate(float, float): void  [API 11+]
- transpose(): void  [API 11+]
```

## Matrix4f — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 23 -)

```
- Matrix4f()  [API 11+]
- Matrix4f(float[])  [API 11+]
- get(int, int): float  [API 11+]
- getArray(): float[]  [API 11+]
- inverse(): boolean  [API 11+]
- inverseTranspose(): boolean  [API 11+]
- load(Matrix4f): void  [API 11+]
- loadFrustum(float, float, float, float, float, float): void  [API 11+]
- loadIdentity(): void  [API 11+]
- loadMultiply(Matrix4f, Matrix4f): void  [API 11+]
- loadOrtho(float, float, float, float, float, float): void  [API 11+]
- loadOrthoWindow(int, int): void  [API 11+]
- loadPerspective(float, float, float, float): void  [API 11+]
- loadProjectionNormalized(int, int): void  [API 11+]
- loadRotate(float, float, float, float): void  [API 11+]
- loadScale(float, float, float): void  [API 11+]
- loadTranslate(float, float, float): void  [API 11+]
- multiply(Matrix4f): void  [API 11+]
- rotate(float, float, float, float): void  [API 11+]
- scale(float, float, float): void  [API 11+]
- set(int, int, float): void  [API 11+]
- translate(float, float, float): void  [API 11+]
- transpose(): void  [API 11+]
```

## Mesh — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 5 -)

```
- getIndexSetAllocation(int): Allocation  [API 11-17, removed in 18]
- getPrimitive(int): Mesh.Primitive  [API 11-17, removed in 18]
- getPrimitiveCount(): int  [API 11-17, removed in 18]
- getVertexAllocation(int): Allocation  [API 11-17, removed in 18]
- getVertexAllocationCount(): int  [API 11-17, removed in 18]
```

## Mesh.AllocationBuilder — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 7 -)

```
- AllocationBuilder(RenderScript)  [API 11-17, removed in 18]
- addIndexSetAllocation(Allocation, Mesh.Primitive): Mesh.AllocationBuilder  [API 11-17, removed in 18]
- addIndexSetType(Mesh.Primitive): Mesh.AllocationBuilder  [API 11-17, removed in 18]
- addVertexAllocation(Allocation): Mesh.AllocationBuilder  [API 11-17, removed in 18]
- create(): Mesh  [API 11-17, removed in 18]
- getCurrentIndexSetIndex(): int  [API 11-17, removed in 18]
- getCurrentVertexTypeIndex(): int  [API 11-17, removed in 18]
```

## Mesh.Builder — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 9 -)

```
- Builder(RenderScript, int)  [API 11-17, removed in 18]
- addIndexSetType(Element, int, Mesh.Primitive): Mesh.Builder  [API 11-17, removed in 18]
- addIndexSetType(Mesh.Primitive): Mesh.Builder  [API 11-17, removed in 18]
- addIndexSetType(Type, Mesh.Primitive): Mesh.Builder  [API 11-17, removed in 18]
- addVertexType(Element, int): Mesh.Builder  [API 11-17, removed in 18]
- addVertexType(Type): Mesh.Builder  [API 11-17, removed in 18]
- create(): Mesh  [API 11-17, removed in 18]
- getCurrentIndexSetIndex(): int  [API 11-17, removed in 18]
- getCurrentVertexTypeIndex(): int  [API 11-17, removed in 18]
```

## Mesh.Primitive — declaration; API 11-17, removed in 18, deprecated in 16 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): Mesh.Primitive  [API 11-17, removed in 18]
+ values(): Mesh.Primitive[]  [API 11-17, removed in 18]
```

## Mesh.TriangleMeshBuilder — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 8 -)

```
- TriangleMeshBuilder(RenderScript, int, int)  [API 11-17, removed in 18]
- addTriangle(int, int, int): Mesh.TriangleMeshBuilder  [API 11-17, removed in 18]
- addVertex(float, float): Mesh.TriangleMeshBuilder  [API 11-17, removed in 18]
- addVertex(float, float, float): Mesh.TriangleMeshBuilder  [API 11-17, removed in 18]
- create(boolean): Mesh  [API 11-17, removed in 18]
- setColor(float, float, float, float): Mesh.TriangleMeshBuilder  [API 11-17, removed in 18]
- setNormal(float, float, float): Mesh.TriangleMeshBuilder  [API 11-17, removed in 18]
- setTexture(float, float): Mesh.TriangleMeshBuilder  [API 11-17, removed in 18]
```

## Program — declaration; API 11-17, removed in 18 (0 +, 0 *, 0 ~, 8 -)

```
- bindConstants(Allocation, int): void  [API 11-17, removed in 18]
- bindSampler(Sampler, int): void  [API 11-17, removed in 18]
- bindTexture(Allocation, int): void  [API 11-17, removed in 18]
- getConstant(int): Type  [API 16-17, removed in 18]
- getConstantCount(): int  [API 16-17, removed in 18]
- getTextureCount(): int  [API 16-17, removed in 18]
- getTextureName(int): String  [API 16-17, removed in 18]
- getTextureType(int): Program.TextureType  [API 16-17, removed in 18]
```

## Program.BaseProgramBuilder — declaration; API 11-17, removed in 18 (0 +, 0 *, 0 ~, 9 -)

```
- BaseProgramBuilder(RenderScript)  [API 11-17, removed in 18]
- addConstant(Type): Program.BaseProgramBuilder  [API 11-17, removed in 18]
- addTexture(Program.TextureType): Program.BaseProgramBuilder  [API 11-17, removed in 18]
- addTexture(Program.TextureType, String): Program.BaseProgramBuilder  [API 16-17, removed in 18]
- getCurrentConstantIndex(): int  [API 11-17, removed in 18]
- getCurrentTextureIndex(): int  [API 11-17, removed in 18]
- initProgram(Program): void  [API 11-17, removed in 18]
- setShader(Resources, int): Program.BaseProgramBuilder  [API 11-17, removed in 18]
- setShader(String): Program.BaseProgramBuilder  [API 11-17, removed in 18]
```

## Program.TextureType — declaration; API 11-17, removed in 18 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): Program.TextureType  [API 11-17, removed in 18]
+ values(): Program.TextureType[]  [API 11-17, removed in 18]
```

## ProgramFragment — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 0 -)

## ProgramFragment.Builder — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 2 -)

```
- Builder(RenderScript)  [API 11-17, removed in 18]
- create(): ProgramFragment  [API 11-17, removed in 18]
```

## ProgramFragmentFixedFunction — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 0 -)

## ProgramFragmentFixedFunction.Builder — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 5 -)

```
- Builder(RenderScript)  [API 11-17, removed in 18]
- create(): ProgramFragmentFixedFunction  [API 11-17, removed in 18]
- setPointSpriteTexCoordinateReplacement(boolean): ProgramFragmentFixedFunction.Builder  [API 11-17, removed in 18]
- setTexture(ProgramFragmentFixedFunction.Builder.EnvMode, ProgramFragmentFixedFunction.Builder.Format, int): ProgramFragmentFixedFunction.Builder  [API 11-17, removed in 18]
- setVaryingColor(boolean): ProgramFragmentFixedFunction.Builder  [API 11-17, removed in 18]
```

## ProgramFragmentFixedFunction.Builder.EnvMode — declaration; API 11-17, removed in 18, deprecated in 16 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): ProgramFragmentFixedFunction.Builder.EnvMode  [API 11-17, removed in 18]
+ values(): ProgramFragmentFixedFunction.Builder.EnvMode[]  [API 11-17, removed in 18]
```

## ProgramFragmentFixedFunction.Builder.Format — declaration; API 11-17, removed in 18, deprecated in 16 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): ProgramFragmentFixedFunction.Builder.Format  [API 11-17, removed in 18]
+ values(): ProgramFragmentFixedFunction.Builder.Format[]  [API 11-17, removed in 18]
```

## ProgramRaster — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 5 -)

```
- CULL_BACK(RenderScript): ProgramRaster  [API 11-17, removed in 18]
- CULL_FRONT(RenderScript): ProgramRaster  [API 11-17, removed in 18]
- CULL_NONE(RenderScript): ProgramRaster  [API 11-17, removed in 18]
- getCullMode(): ProgramRaster.CullMode  [API 16-17, removed in 18]
- isPointSpriteEnabled(): boolean  [API 16-17, removed in 18]
```

## ProgramRaster.Builder — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 4 -)

```
- Builder(RenderScript)  [API 11-17, removed in 18]
- create(): ProgramRaster  [API 11-17, removed in 18]
- setCullMode(ProgramRaster.CullMode): ProgramRaster.Builder  [API 11-17, removed in 18]
- setPointSpriteEnabled(boolean): ProgramRaster.Builder  [API 11-17, removed in 18]
```

## ProgramRaster.CullMode — declaration; API 11-17, removed in 18, deprecated in 16 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): ProgramRaster.CullMode  [API 11-17, removed in 18]
+ values(): ProgramRaster.CullMode[]  [API 11-17, removed in 18]
```

## ProgramStore — declaration; API 11-17, removed in 18 (0 +, 0 *, 0 ~, 13 -)

```
- BLEND_ALPHA_DEPTH_NONE(RenderScript): ProgramStore  [API 11-17, removed in 18]
- BLEND_ALPHA_DEPTH_TEST(RenderScript): ProgramStore  [API 11-17, removed in 18]
- BLEND_NONE_DEPTH_NONE(RenderScript): ProgramStore  [API 11-17, removed in 18]
- BLEND_NONE_DEPTH_TEST(RenderScript): ProgramStore  [API 11-17, removed in 18]
- getBlendDstFunc(): ProgramStore.BlendDstFunc  [API 16-17, removed in 18]
- getBlendSrcFunc(): ProgramStore.BlendSrcFunc  [API 16-17, removed in 18]
- getDepthFunc(): ProgramStore.DepthFunc  [API 16-17, removed in 18]
- isColorMaskAlphaEnabled(): boolean  [API 16-17, removed in 18]
- isColorMaskBlueEnabled(): boolean  [API 16-17, removed in 18]
- isColorMaskGreenEnabled(): boolean  [API 16-17, removed in 18]
- isColorMaskRedEnabled(): boolean  [API 16-17, removed in 18]
- isDepthMaskEnabled(): boolean  [API 16-17, removed in 18]
- isDitherEnabled(): boolean  [API 16-17, removed in 18]
```

## ProgramStore.BlendDstFunc — declaration; API 11-17, removed in 18 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): ProgramStore.BlendDstFunc  [API 11-17, removed in 18]
+ values(): ProgramStore.BlendDstFunc[]  [API 11-17, removed in 18]
```

## ProgramStore.BlendSrcFunc — declaration; API 11-17, removed in 18 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): ProgramStore.BlendSrcFunc  [API 11-17, removed in 18]
+ values(): ProgramStore.BlendSrcFunc[]  [API 11-17, removed in 18]
```

## ProgramStore.Builder — declaration; API 11-17, removed in 18 (0 +, 0 *, 0 ~, 7 -)

```
- Builder(RenderScript)  [API 11-17, removed in 18]
- create(): ProgramStore  [API 11-17, removed in 18]
- setBlendFunc(ProgramStore.BlendSrcFunc, ProgramStore.BlendDstFunc): ProgramStore.Builder  [API 11-17, removed in 18]
- setColorMaskEnabled(boolean, boolean, boolean, boolean): ProgramStore.Builder  [API 11-17, removed in 18]
- setDepthFunc(ProgramStore.DepthFunc): ProgramStore.Builder  [API 11-17, removed in 18]
- setDepthMaskEnabled(boolean): ProgramStore.Builder  [API 11-17, removed in 18]
- setDitherEnabled(boolean): ProgramStore.Builder  [API 11-17, removed in 18]
```

## ProgramStore.DepthFunc — declaration; API 11-17, removed in 18 (2 +, 0 *, 0 ~, 0 -)

```
+ valueOf(String): ProgramStore.DepthFunc  [API 11-17, removed in 18]
+ values(): ProgramStore.DepthFunc[]  [API 11-17, removed in 18]
```

## ProgramVertex — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 2 -)

```
- getInput(int): Element  [API 16-17, removed in 18]
- getInputCount(): int  [API 16-17, removed in 18]
```

## ProgramVertex.Builder — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 3 -)

```
- Builder(RenderScript)  [API 11-17, removed in 18]
- addInput(Element): ProgramVertex.Builder  [API 11-17, removed in 18]
- create(): ProgramVertex  [API 11-17, removed in 18]
```

## ProgramVertexFixedFunction — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 1 -)

```
- bindConstants(ProgramVertexFixedFunction.Constants): void  [API 11-17, removed in 18]
```

## ProgramVertexFixedFunction.Builder — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 3 -)

```
- Builder(RenderScript)  [API 11-17, removed in 18]
- create(): ProgramVertexFixedFunction  [API 11-17, removed in 18]
- setTextureMatrixEnable(boolean): ProgramVertexFixedFunction.Builder  [API 11-17, removed in 18]
```

## ProgramVertexFixedFunction.Constants — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 5 -)

```
- Constants(RenderScript)  [API 11-17, removed in 18]
- destroy(): void  [API 11-17, removed in 18]
- setModelview(Matrix4f): void  [API 11-17, removed in 18]
- setProjection(Matrix4f): void  [API 11-17, removed in 18]
- setTexture(Matrix4f): void  [API 11-17, removed in 18]
```

## RSDriverException — declaration; API 11+, deprecated in 31 (1 +, 0 *, 0 ~, 0 -)

```
+ RSDriverException(String)  [API 11+]
```

## RSIllegalArgumentException — declaration; API 11+, deprecated in 31 (1 +, 0 *, 0 ~, 0 -)

```
+ RSIllegalArgumentException(String)  [API 11+]
```

## RSInvalidStateException — declaration; API 11+, deprecated in 31 (1 +, 0 *, 0 ~, 0 -)

```
+ RSInvalidStateException(String)  [API 11+]
```

## RSRuntimeException — declaration; API 11+, deprecated in 31 (1 +, 0 *, 0 ~, 0 -)

```
+ RSRuntimeException(String)  [API 11+]
```

## RSSurfaceView — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 8 -)

```
- RSSurfaceView(Context)  [API 11-17, removed in 18]
- RSSurfaceView(Context, AttributeSet)  [API 11-17, removed in 18]
- createRenderScriptGL(RenderScriptGL.SurfaceConfig): RenderScriptGL  [API 11-17, removed in 18]
- destroyRenderScriptGL(): void  [API 11-17, removed in 18]
- getRenderScriptGL(): RenderScriptGL  [API 11-17, removed in 18]
- pause(): void  [API 11-17, removed in 18]
- resume(): void  [API 11-17, removed in 18]
- setRenderScriptGL(RenderScriptGL): void  [API 11-17, removed in 18]
```

## RSTextureView — declaration; API 14-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 8 -)

```
- RSTextureView(Context)  [API 14-17, removed in 18]
- RSTextureView(Context, AttributeSet)  [API 14-17, removed in 18]
- createRenderScriptGL(RenderScriptGL.SurfaceConfig): RenderScriptGL  [API 14-17, removed in 18]
- destroyRenderScriptGL(): void  [API 14-17, removed in 18]
- getRenderScriptGL(): RenderScriptGL  [API 14-17, removed in 18]
- pause(): void  [API 14-17, removed in 18]
- resume(): void  [API 14-17, removed in 18]
- setRenderScriptGL(RenderScriptGL): void  [API 14-17, removed in 18]
```

## RenderScript — declaration; API 11+, deprecated in 31 (0 +, 4 *, 0 ~, 13 -)

```
- contextDump(): void  [API 11+]
- create(Context): RenderScript [static]  [API 11+]
- create(Context, RenderScript.ContextType): RenderScript [static]  [API 18+]
- create(Context, RenderScript.ContextType, int): RenderScript [static]  [API 21+]
- createMultiContext(Context, RenderScript.ContextType, int, int): RenderScript [static]  [API 23+]
- destroy(): void  [API 11+]
- finalize(): void  [API 11+]
- finish(): void  [API 11+]
- getApplicationContext(): Context  [API 11+]
* getErrorHandler(): RenderScript.RSErrorHandler  [API 11+]
* getMessageHandler(): RenderScript.RSMessageHandler  [API 11+]
- getMinorVersion(): long [static]  [API 23+]
- releaseAllContexts(): void [static]  [API 23+]
- sendMessage(int, int[]): void  [API 18+]
* setErrorHandler(RenderScript.RSErrorHandler): void  [API 11+]
* setMessageHandler(RenderScript.RSMessageHandler): void  [API 11+]
- setPriority(RenderScript.Priority): void  [API 11+]
```

## RenderScript.ContextType — declaration; API 18+, deprecated in 31 (2 +, 0 *, 0 ~, 0 -)

```
+ DEBUG [field]  [API 18+]
+ NORMAL [field]  [API 18+]
+ PROFILE [field]  [API 18+]
+ valueOf(String): RenderScript.ContextType [static]  [API 18+]
+ values(): RenderScript.ContextType[] [static]  [API 18+]
```

## RenderScript.Priority — declaration; API 11+, deprecated in 31 (2 +, 0 *, 0 ~, 0 -)

```
+ LOW [field]  [API 11+]
+ NORMAL [field]  [API 11+]
+ valueOf(String): RenderScript.Priority [static]  [API 11+]
+ values(): RenderScript.Priority[] [static]  [API 11+]
```

## RenderScript.RSErrorHandler — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 2 -)

```
- RSErrorHandler()  [API 11+]
+ mErrorMessage [field]  [API 11+]
+ mErrorNum [field]  [API 11+]
- run(): void  [API 11+]
```

## RenderScript.RSMessageHandler — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 2 -)

```
- RSMessageHandler()  [API 11+]
+ mData [field]  [API 11+]
+ mID [field]  [API 11+]
+ mLength [field]  [API 11+]
- run(): void  [API 11+]
```

## RenderScriptGL — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 12 -)

```
- RenderScriptGL(Context, RenderScriptGL.SurfaceConfig)  [API 11-17, removed in 18]
- bindProgramFragment(ProgramFragment): void  [API 11-17, removed in 18]
- bindProgramRaster(ProgramRaster): void  [API 11-17, removed in 18]
- bindProgramStore(ProgramStore): void  [API 11-17, removed in 18]
- bindProgramVertex(ProgramVertex): void  [API 11-17, removed in 18]
- bindRootScript(Script): void  [API 11-17, removed in 18]
- getHeight(): int  [API 11-17, removed in 18]
- getWidth(): int  [API 11-17, removed in 18]
- pause(): void  [API 11-17, removed in 18]
- resume(): void  [API 11-17, removed in 18]
- setSurface(SurfaceHolder, int, int): void  [API 11-17, removed in 18]
- setSurfaceTexture(SurfaceTexture, int, int): void  [API 14-17, removed in 18]
```

## RenderScriptGL.SurfaceConfig — declaration; API 11-17, removed in 18, deprecated in 16 (0 +, 0 *, 0 ~, 6 -)

```
- SurfaceConfig()  [API 11-17, removed in 18]
- SurfaceConfig(RenderScriptGL.SurfaceConfig)  [API 11-17, removed in 18]
- setAlpha(int, int): void  [API 11-17, removed in 18]
- setColor(int, int): void  [API 11-17, removed in 18]
- setDepth(int, int): void  [API 11-17, removed in 18]
- setSamples(int, int, float): void  [API 11-17, removed in 18]
```

## Sampler — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 14 -)

```
- CLAMP_LINEAR(RenderScript): Sampler [static]  [API 11+]
- CLAMP_LINEAR_MIP_LINEAR(RenderScript): Sampler [static]  [API 11+]
- CLAMP_NEAREST(RenderScript): Sampler [static]  [API 11+]
- MIRRORED_REPEAT_LINEAR(RenderScript): Sampler [static]  [API 18+]
- MIRRORED_REPEAT_LINEAR_MIP_LINEAR(RenderScript): Sampler [static]  [API 18+]
- MIRRORED_REPEAT_NEAREST(RenderScript): Sampler [static]  [API 18+]
- WRAP_LINEAR(RenderScript): Sampler [static]  [API 11+]
- WRAP_LINEAR_MIP_LINEAR(RenderScript): Sampler [static]  [API 11+]
- WRAP_NEAREST(RenderScript): Sampler [static]  [API 11+]
- getAnisotropy(): float  [API 16+]
- getMagnification(): Sampler.Value  [API 16+]
- getMinification(): Sampler.Value  [API 16+]
- getWrapS(): Sampler.Value  [API 16+]
- getWrapT(): Sampler.Value  [API 16+]
```

## Sampler.Builder — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 7 -)

```
- Builder(RenderScript)  [API 11+]
- create(): Sampler  [API 11+]
- setAnisotropy(float): void  [API 11+]
- setMagnification(Sampler.Value): void  [API 11+]
- setMinification(Sampler.Value): void  [API 11+]
- setWrapS(Sampler.Value): void  [API 11+]
- setWrapT(Sampler.Value): void  [API 11+]
```

## Sampler.Value — declaration; API 11+, deprecated in 31 (2 +, 0 *, 0 ~, 0 -)

```
+ CLAMP [field]  [API 11+]
+ LINEAR [field]  [API 11+]
+ LINEAR_MIP_LINEAR [field]  [API 11+]
+ LINEAR_MIP_NEAREST [field]  [API 11+]
+ MIRRORED_REPEAT [field]  [API 18+]
+ NEAREST [field]  [API 11+]
+ WRAP [field]  [API 11+]
+ valueOf(String): Sampler.Value [static]  [API 11+]
+ values(): Sampler.Value[] [static]  [API 11+]
```

## Script — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 26 -)

```
- bindAllocation(Allocation, int): void  [API 11+]
- createFieldID(int, Element): Script.FieldID  [API 17+]
- createInvokeID(int): Script.InvokeID  [API 23+]
- createKernelID(int, int, Element, Element): Script.KernelID  [API 17+]
- forEach(int, Allocation, Allocation, FieldPacker): void  [API 14+]
- forEach(int, Allocation, Allocation, FieldPacker, Script.LaunchOptions): void  [API 18+]
- forEach(int, Allocation[], Allocation, FieldPacker): void  [API 23+]
- forEach(int, Allocation[], Allocation, FieldPacker, Script.LaunchOptions): void  [API 23+]
- getVarB(int): boolean  [API 18+]
- getVarD(int): double  [API 18+]
- getVarF(int): float  [API 18+]
- getVarI(int): int  [API 18+]
- getVarJ(int): long  [API 18+]
- getVarV(int, FieldPacker): void  [API 18+]
- invoke(int): void  [API 11+]
- invoke(int, FieldPacker): void  [API 11+]
- reduce(int, Allocation[], Allocation, Script.LaunchOptions): void  [API 24+]
- setTimeZone(String): void  [API 11+]
- setVar(int, BaseObj): void  [API 11+]
- setVar(int, FieldPacker): void  [API 11+]
- setVar(int, FieldPacker, Element, int[]): void  [API 16+]
- setVar(int, boolean): void  [API 11+]
- setVar(int, double): void  [API 11+]
- setVar(int, float): void  [API 11+]
- setVar(int, int): void  [API 11+]
- setVar(int, long): void  [API 11+]
```

## Script.Builder — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 0 -)

## Script.FieldBase — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 7 -)

```
- FieldBase()  [API 11+]
- getAllocation(): Allocation  [API 11+]
- getElement(): Element  [API 11+]
- getType(): Type  [API 11+]
- init(RenderScript, int): void  [API 11+]
- init(RenderScript, int, int): void  [API 11+]
+ mAllocation [field]  [API 11+]
+ mElement [field]  [API 11+]
- updateAllocation(): void  [API 11+]
```

## Script.FieldID — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 0 -)

## Script.InvokeID — declaration; API 23+, deprecated in 31 (0 +, 0 *, 0 ~, 0 -)

## Script.KernelID — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 0 -)

## Script.LaunchOptions — declaration; API 18+, deprecated in 31 (0 +, 0 *, 0 ~, 10 -)

```
- LaunchOptions()  [API 18+]
- getXEnd(): int  [API 18+]
- getXStart(): int  [API 18+]
- getYEnd(): int  [API 18+]
- getYStart(): int  [API 18+]
- getZEnd(): int  [API 18+]
- getZStart(): int  [API 18+]
- setX(int, int): Script.LaunchOptions  [API 18+]
- setY(int, int): Script.LaunchOptions  [API 18+]
- setZ(int, int): Script.LaunchOptions  [API 18+]
```

## ScriptC — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 4 -)

```
- ScriptC(RenderScript, Resources, int)  [API 11+]
- ScriptC(RenderScript, String, byte[], byte[])  [API 21+]
- ScriptC(int, RenderScript)  [API 11+]
- ScriptC(long, RenderScript)  [API 21+]
```

## ScriptGroup — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 5 -)

```
- destroy(): void  [API 17+]
- execute(): void  [API 17+, deprecated in 23]
- execute(Object[]): Object[]  [API 23+]
- setInput(Script.KernelID, Allocation): void  [API 17+, deprecated in 23]
- setOutput(Script.KernelID, Allocation): void  [API 17+, deprecated in 23]
```

## ScriptGroup.Binding — declaration; API 23+, deprecated in 31 (0 +, 0 *, 0 ~, 1 -)

```
- Binding(Script.FieldID, Object)  [API 23+]
```

## ScriptGroup.Builder — declaration; API 17+, deprecated in 23 (0 +, 0 *, 0 ~, 5 -)

```
- Builder(RenderScript)  [API 17+]
- addConnection(Type, Script.KernelID, Script.FieldID): ScriptGroup.Builder  [API 17+]
- addConnection(Type, Script.KernelID, Script.KernelID): ScriptGroup.Builder  [API 17+]
- addKernel(Script.KernelID): ScriptGroup.Builder  [API 17+]
- create(): ScriptGroup  [API 17+]
```

## ScriptGroup.Builder2 — declaration; API 23+, deprecated in 31 (0 +, 0 *, 0 ~, 5 -)

```
- Builder2(RenderScript)  [API 23+]
- addInput(): ScriptGroup.Input  [API 23+]
- addInvoke(Script.InvokeID, Object[]): ScriptGroup.Closure  [API 23+]
- addKernel(Script.KernelID, Type, Object[]): ScriptGroup.Closure  [API 23+]
- create(String, ScriptGroup.Future[]): ScriptGroup  [API 23+]
```

## ScriptGroup.Closure — declaration; API 23+, deprecated in 31 (0 +, 0 *, 0 ~, 4 -)

```
- destroy(): void  [API 23+]
- finalize(): void  [API 23+]
- getGlobal(Script.FieldID): ScriptGroup.Future  [API 23+]
- getReturn(): ScriptGroup.Future  [API 23+]
```

## ScriptGroup.Future — declaration; API 23+, deprecated in 31 (0 +, 0 *, 0 ~, 0 -)

## ScriptGroup.Input — declaration; API 23+, deprecated in 31 (0 +, 0 *, 0 ~, 0 -)

## ScriptIntrinsic — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 0 -)

## ScriptIntrinsic3DLUT — declaration; API 18+, deprecated in 31 (0 +, 0 *, 0 ~, 5 -)

```
- create(RenderScript, Element): ScriptIntrinsic3DLUT [static]  [API 18+]
- forEach(Allocation, Allocation): void  [API 18+]
- forEach(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- getKernelID(): Script.KernelID  [API 18+]
- setLUT(Allocation): void  [API 18+]
```

## ScriptIntrinsicBLAS — declaration; API 23+, deprecated in 31 (0 +, 0 *, 0 ~, 98 -)

```
- BNNM(Allocation, int, Allocation, int, Allocation, int, int): void  [API 23+]
- CGBMV(int, int, int, Float2, Allocation, Allocation, int, Float2, Allocation, int): void  [API 23+]
- CGEMM(int, int, Float2, Allocation, Allocation, Float2, Allocation): void  [API 23+]
- CGEMV(int, Float2, Allocation, Allocation, int, Float2, Allocation, int): void  [API 23+]
- CGERC(Float2, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- CGERU(Float2, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- CHBMV(int, int, Float2, Allocation, Allocation, int, Float2, Allocation, int): void  [API 23+]
- CHEMM(int, int, Float2, Allocation, Allocation, Float2, Allocation): void  [API 23+]
- CHEMV(int, Float2, Allocation, Allocation, int, Float2, Allocation, int): void  [API 23+]
- CHER(int, float, Allocation, int, Allocation): void  [API 23+]
- CHER2(int, Float2, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- CHER2K(int, int, Float2, Allocation, Allocation, float, Allocation): void  [API 23+]
- CHERK(int, int, float, Allocation, float, Allocation): void  [API 23+]
- CHPMV(int, Float2, Allocation, Allocation, int, Float2, Allocation, int): void  [API 23+]
- CHPR(int, float, Allocation, int, Allocation): void  [API 23+]
- CHPR2(int, Float2, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- CSYMM(int, int, Float2, Allocation, Allocation, Float2, Allocation): void  [API 23+]
- CSYR2K(int, int, Float2, Allocation, Allocation, Float2, Allocation): void  [API 23+]
- CSYRK(int, int, Float2, Allocation, Float2, Allocation): void  [API 23+]
- CTBMV(int, int, int, int, Allocation, Allocation, int): void  [API 23+]
- CTBSV(int, int, int, int, Allocation, Allocation, int): void  [API 23+]
- CTPMV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- CTPSV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- CTRMM(int, int, int, int, Float2, Allocation, Allocation): void  [API 23+]
- CTRMV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- CTRSM(int, int, int, int, Float2, Allocation, Allocation): void  [API 23+]
- CTRSV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- DGBMV(int, int, int, double, Allocation, Allocation, int, double, Allocation, int): void  [API 23+]
- DGEMM(int, int, double, Allocation, Allocation, double, Allocation): void  [API 23+]
- DGEMV(int, double, Allocation, Allocation, int, double, Allocation, int): void  [API 23+]
- DGER(double, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- DSBMV(int, int, double, Allocation, Allocation, int, double, Allocation, int): void  [API 23+]
- DSPMV(int, double, Allocation, Allocation, int, double, Allocation, int): void  [API 23+]
- DSPR(int, double, Allocation, int, Allocation): void  [API 23+]
- DSPR2(int, double, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- DSYMM(int, int, double, Allocation, Allocation, double, Allocation): void  [API 23+]
- DSYMV(int, double, Allocation, Allocation, int, double, Allocation, int): void  [API 23+]
- DSYR(int, double, Allocation, int, Allocation): void  [API 23+]
- DSYR2(int, double, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- DSYR2K(int, int, double, Allocation, Allocation, double, Allocation): void  [API 23+]
- DSYRK(int, int, double, Allocation, double, Allocation): void  [API 23+]
- DTBMV(int, int, int, int, Allocation, Allocation, int): void  [API 23+]
- DTBSV(int, int, int, int, Allocation, Allocation, int): void  [API 23+]
- DTPMV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- DTPSV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- DTRMM(int, int, int, int, double, Allocation, Allocation): void  [API 23+]
- DTRMV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- DTRSM(int, int, int, int, double, Allocation, Allocation): void  [API 23+]
- DTRSV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- SGBMV(int, int, int, float, Allocation, Allocation, int, float, Allocation, int): void  [API 23+]
- SGEMM(int, int, float, Allocation, Allocation, float, Allocation): void  [API 23+]
- SGEMV(int, float, Allocation, Allocation, int, float, Allocation, int): void  [API 23+]
- SGER(float, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- SSBMV(int, int, float, Allocation, Allocation, int, float, Allocation, int): void  [API 23+]
- SSPMV(int, float, Allocation, Allocation, int, float, Allocation, int): void  [API 23+]
- SSPR(int, float, Allocation, int, Allocation): void  [API 23+]
- SSPR2(int, float, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- SSYMM(int, int, float, Allocation, Allocation, float, Allocation): void  [API 23+]
- SSYMV(int, float, Allocation, Allocation, int, float, Allocation, int): void  [API 23+]
- SSYR(int, float, Allocation, int, Allocation): void  [API 23+]
- SSYR2(int, float, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- SSYR2K(int, int, float, Allocation, Allocation, float, Allocation): void  [API 23+]
- SSYRK(int, int, float, Allocation, float, Allocation): void  [API 23+]
- STBMV(int, int, int, int, Allocation, Allocation, int): void  [API 23+]
- STBSV(int, int, int, int, Allocation, Allocation, int): void  [API 23+]
- STPMV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- STPSV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- STRMM(int, int, int, int, float, Allocation, Allocation): void  [API 23+]
- STRMV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- STRSM(int, int, int, int, float, Allocation, Allocation): void  [API 23+]
- STRSV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- ZGBMV(int, int, int, Double2, Allocation, Allocation, int, Double2, Allocation, int): void  [API 23+]
- ZGEMM(int, int, Double2, Allocation, Allocation, Double2, Allocation): void  [API 23+]
- ZGEMV(int, Double2, Allocation, Allocation, int, Double2, Allocation, int): void  [API 23+]
- ZGERC(Double2, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- ZGERU(Double2, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- ZHBMV(int, int, Double2, Allocation, Allocation, int, Double2, Allocation, int): void  [API 23+]
- ZHEMM(int, int, Double2, Allocation, Allocation, Double2, Allocation): void  [API 23+]
- ZHEMV(int, Double2, Allocation, Allocation, int, Double2, Allocation, int): void  [API 23+]
- ZHER(int, double, Allocation, int, Allocation): void  [API 23+]
- ZHER2(int, Double2, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- ZHER2K(int, int, Double2, Allocation, Allocation, double, Allocation): void  [API 23+]
- ZHERK(int, int, double, Allocation, double, Allocation): void  [API 23+]
- ZHPMV(int, Double2, Allocation, Allocation, int, Double2, Allocation, int): void  [API 23+]
- ZHPR(int, double, Allocation, int, Allocation): void  [API 23+]
- ZHPR2(int, Double2, Allocation, int, Allocation, int, Allocation): void  [API 23+]
- ZSYMM(int, int, Double2, Allocation, Allocation, Double2, Allocation): void  [API 23+]
- ZSYR2K(int, int, Double2, Allocation, Allocation, Double2, Allocation): void  [API 23+]
- ZSYRK(int, int, Double2, Allocation, Double2, Allocation): void  [API 23+]
- ZTBMV(int, int, int, int, Allocation, Allocation, int): void  [API 23+]
- ZTBSV(int, int, int, int, Allocation, Allocation, int): void  [API 23+]
- ZTPMV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- ZTPSV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- ZTRMM(int, int, int, int, Double2, Allocation, Allocation): void  [API 23+]
- ZTRMV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- ZTRSM(int, int, int, int, Double2, Allocation, Allocation): void  [API 23+]
- ZTRSV(int, int, int, Allocation, Allocation, int): void  [API 23+]
- create(RenderScript): ScriptIntrinsicBLAS [static]  [API 23+]
```

## ScriptIntrinsicBlend — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 46 -)

```
- create(RenderScript, Element): ScriptIntrinsicBlend [static]  [API 17+]
- forEachAdd(Allocation, Allocation): void  [API 17+]
- forEachAdd(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachClear(Allocation, Allocation): void  [API 17+]
- forEachClear(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachDst(Allocation, Allocation): void  [API 17+]
- forEachDst(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachDstAtop(Allocation, Allocation): void  [API 17+]
- forEachDstAtop(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachDstIn(Allocation, Allocation): void  [API 17+]
- forEachDstIn(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachDstOut(Allocation, Allocation): void  [API 17+]
- forEachDstOut(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachDstOver(Allocation, Allocation): void  [API 17+]
- forEachDstOver(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachMultiply(Allocation, Allocation): void  [API 17+]
- forEachMultiply(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachSrc(Allocation, Allocation): void  [API 17+]
- forEachSrc(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachSrcAtop(Allocation, Allocation): void  [API 17+]
- forEachSrcAtop(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachSrcIn(Allocation, Allocation): void  [API 17+]
- forEachSrcIn(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachSrcOut(Allocation, Allocation): void  [API 17+]
- forEachSrcOut(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachSrcOver(Allocation, Allocation): void  [API 17+]
- forEachSrcOver(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachSubtract(Allocation, Allocation): void  [API 17+]
- forEachSubtract(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- forEachXor(Allocation, Allocation): void  [API 17+]
- forEachXor(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- getKernelIDAdd(): Script.KernelID  [API 17+]
- getKernelIDClear(): Script.KernelID  [API 17+]
- getKernelIDDst(): Script.KernelID  [API 17+]
- getKernelIDDstAtop(): Script.KernelID  [API 17+]
- getKernelIDDstIn(): Script.KernelID  [API 17+]
- getKernelIDDstOut(): Script.KernelID  [API 17+]
- getKernelIDDstOver(): Script.KernelID  [API 17+]
- getKernelIDMultiply(): Script.KernelID  [API 17+]
- getKernelIDSrc(): Script.KernelID  [API 17+]
- getKernelIDSrcAtop(): Script.KernelID  [API 17+]
- getKernelIDSrcIn(): Script.KernelID  [API 17+]
- getKernelIDSrcOut(): Script.KernelID  [API 17+]
- getKernelIDSrcOver(): Script.KernelID  [API 17+]
- getKernelIDSubtract(): Script.KernelID  [API 17+]
- getKernelIDXor(): Script.KernelID  [API 17+]
```

## ScriptIntrinsicBlur — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 7 -)

```
- create(RenderScript, Element): ScriptIntrinsicBlur [static]  [API 17+]
- forEach(Allocation): void  [API 17+]
- forEach(Allocation, Script.LaunchOptions): void  [API 21+]
- getFieldID_Input(): Script.FieldID  [API 17+]
- getKernelID(): Script.KernelID  [API 17+]
- setInput(Allocation): void  [API 17+]
- setRadius(float): void  [API 17+]
```

## ScriptIntrinsicColorMatrix — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 12 -)

```
- create(RenderScript): ScriptIntrinsicColorMatrix [static]  [API 19+]
- create(RenderScript, Element): ScriptIntrinsicColorMatrix [static]  [API 17+, deprecated in 19]
- forEach(Allocation, Allocation): void  [API 17+]
- forEach(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- getKernelID(): Script.KernelID  [API 17+]
- setAdd(Float4): void  [API 19+]
- setAdd(float, float, float, float): void  [API 19+]
- setColorMatrix(Matrix3f): void  [API 17+]
- setColorMatrix(Matrix4f): void  [API 17+]
- setGreyscale(): void  [API 17+]
- setRGBtoYUV(): void  [API 17+]
- setYUVtoRGB(): void  [API 17+]
```

## ScriptIntrinsicConvolve3x3 — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 7 -)

```
- create(RenderScript, Element): ScriptIntrinsicConvolve3x3 [static]  [API 17+]
- forEach(Allocation): void  [API 17+]
- forEach(Allocation, Script.LaunchOptions): void  [API 21+]
- getFieldID_Input(): Script.FieldID  [API 17+]
- getKernelID(): Script.KernelID  [API 17+]
- setCoefficients(float[]): void  [API 17+]
- setInput(Allocation): void  [API 17+]
```

## ScriptIntrinsicConvolve5x5 — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 7 -)

```
- create(RenderScript, Element): ScriptIntrinsicConvolve5x5 [static]  [API 17+]
- forEach(Allocation): void  [API 17+]
- forEach(Allocation, Script.LaunchOptions): void  [API 21+]
- getFieldID_Input(): Script.FieldID  [API 17+]
- getKernelID(): Script.KernelID  [API 17+]
- setCoefficients(float[]): void  [API 17+]
- setInput(Allocation): void  [API 17+]
```

## ScriptIntrinsicHistogram — declaration; API 19+, deprecated in 31 (0 +, 0 *, 0 ~, 9 -)

```
- create(RenderScript, Element): ScriptIntrinsicHistogram [static]  [API 19+]
- forEach(Allocation): void  [API 19+]
- forEach(Allocation, Script.LaunchOptions): void  [API 21+]
- forEach_Dot(Allocation): void  [API 19+]
- forEach_Dot(Allocation, Script.LaunchOptions): void  [API 21+]
- getFieldID_Input(): Script.FieldID  [API 19+]
- getKernelID_Separate(): Script.KernelID  [API 19+]
- setDotCoefficients(float, float, float, float): void  [API 19+]
- setOutput(Allocation): void  [API 19+]
```

## ScriptIntrinsicLUT — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 9 -)

```
- create(RenderScript, Element): ScriptIntrinsicLUT [static]  [API 17+]
- destroy(): void  [API 17+]
- forEach(Allocation, Allocation): void  [API 17+]
- forEach(Allocation, Allocation, Script.LaunchOptions): void  [API 21+]
- getKernelID(): Script.KernelID  [API 17+]
- setAlpha(int, int): void  [API 17+]
- setBlue(int, int): void  [API 17+]
- setGreen(int, int): void  [API 17+]
- setRed(int, int): void  [API 17+]
```

## ScriptIntrinsicResize — declaration; API 20+, deprecated in 31 (0 +, 0 *, 0 ~, 6 -)

```
- create(RenderScript): ScriptIntrinsicResize [static]  [API 20+]
- forEach_bicubic(Allocation): void  [API 20+]
- forEach_bicubic(Allocation, Script.LaunchOptions): void  [API 20+]
- getFieldID_Input(): Script.FieldID  [API 20+]
- getKernelID_bicubic(): Script.KernelID  [API 20+]
- setInput(Allocation): void  [API 20+]
```

## ScriptIntrinsicYuvToRGB — declaration; API 17+, deprecated in 31 (0 +, 0 *, 0 ~, 5 -)

```
- create(RenderScript, Element): ScriptIntrinsicYuvToRGB [static]  [API 17+]
- forEach(Allocation): void  [API 17+]
- getFieldID_Input(): Script.FieldID  [API 17+]
- getKernelID(): Script.KernelID  [API 17+]
- setInput(Allocation): void  [API 17+]
```

## Short2 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Short2()  [API 11+]
* Short2(short, short)  [API 14+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
```

## Short3 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Short3()  [API 11+]
* Short3(short, short, short)  [API 14+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Short4 — declaration; API 11+, deprecated in 31 (1 +, 1 *, 0 ~, 0 -)

```
+ Short4()  [API 11+]
* Short4(short, short, short, short)  [API 14+]
+ w [field]  [API 11+]
+ x [field]  [API 11+]
+ y [field]  [API 11+]
+ z [field]  [API 11+]
```

## Type — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 11 -)

```
- createX(RenderScript, Element, int): Type [static]  [API 21+]
- createXY(RenderScript, Element, int, int): Type [static]  [API 21+]
- createXYZ(RenderScript, Element, int, int, int): Type [static]  [API 21+]
- getCount(): int  [API 11+]
- getElement(): Element  [API 11+]
- getX(): int  [API 11+]
- getY(): int  [API 11+]
- getYuv(): int  [API 18+]
- getZ(): int  [API 11+]
- hasFaces(): boolean  [API 11+]
- hasMipmaps(): boolean  [API 11+]
```

## Type.Builder — declaration; API 11+, deprecated in 31 (0 +, 0 *, 0 ~, 8 -)

```
- Builder(RenderScript, Element)  [API 11+]
- create(): Type  [API 11+]
- setFaces(boolean): Type.Builder  [API 11+]
- setMipmaps(boolean): Type.Builder  [API 11+]
- setX(int): Type.Builder  [API 11+]
- setY(int): Type.Builder  [API 11+]
- setYuvFormat(int): Type.Builder  [API 18+]
- setZ(int): Type.Builder  [API 18+]
```

## Type.CubemapFace — declaration; API 11+, deprecated in 31 (2 +, 0 *, 0 ~, 0 -)

```
+ NEGATIVE_X [field]  [API 11+]
+ NEGATIVE_Y [field]  [API 11+]
+ NEGATIVE_Z [field]  [API 11+]
+ POSITIVE_X [field]  [API 14+]
+ POSITIVE_Y [field]  [API 14+]
+ POSITIVE_Z [field]  [API 14+]
+ POSITVE_X [field]  [API 11+, deprecated in 29]
+ POSITVE_Y [field]  [API 11+, deprecated in 29]
+ POSITVE_Z [field]  [API 11+, deprecated in 29]
+ valueOf(String): Type.CubemapFace [static]  [API 11+]
+ values(): Type.CubemapFace[] [static]  [API 11+]
```


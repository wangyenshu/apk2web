# java.util.jar

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Attributes — TeaVM class library; API 1+ (21 +, 0 *, 0 ~, 0 -)

```
+ Attributes()  [API 1+]
+ Attributes(Attributes)  [API 1+]
+ Attributes(int)  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ entrySet(): Set  [API 1+]
+ equals(Object): boolean  [API 1+]
+ get(Object): Object  [API 1+]
+ getValue(Attributes.Name): String  [API 1+]
+ getValue(String): String  [API 1+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keySet(): Set  [API 1+]
+ map [field]  [API 1+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ putValue(String, String): String  [API 1+]
+ remove(Object): Object  [API 1+]
+ size(): int  [API 1+]
+ values(): Collection  [API 1+]
```

## Attributes.Name — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CLASS_PATH [field]  [API 1+]
+ CONTENT_TYPE [field]  [API 1+]
+ EXTENSION_INSTALLATION [field]  [API 1+, deprecated in 26]
+ EXTENSION_LIST [field]  [API 1+]
+ EXTENSION_NAME [field]  [API 1+]
+ IMPLEMENTATION_TITLE [field]  [API 1+]
+ IMPLEMENTATION_URL [field]  [API 1+, deprecated in 26]
+ IMPLEMENTATION_VENDOR [field]  [API 1+]
+ IMPLEMENTATION_VENDOR_ID [field]  [API 1+, deprecated in 26]
+ IMPLEMENTATION_VERSION [field]  [API 1+]
+ MAIN_CLASS [field]  [API 1+]
+ MANIFEST_VERSION [field]  [API 1+]
+ Name(String)  [API 1+]
+ SEALED [field]  [API 1+]
+ SIGNATURE_VERSION [field]  [API 1+]
+ SPECIFICATION_TITLE [field]  [API 1+]
+ SPECIFICATION_VENDOR [field]  [API 1+]
+ SPECIFICATION_VERSION [field]  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ toString(): String  [API 1+]
```

## JarEntry — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 3 -)

```
+ JarEntry(JarEntry)  [API 1+]
+ JarEntry(String)  [API 1+]
+ JarEntry(ZipEntry)  [API 1+]
+ getAttributes(): Attributes  [API 1+]
- getCertificates(): Certificate[]  [API 1+]
- getCodeSigners(): CodeSigner[]  [API 1+]
- getRealName(): String  [API 33+]
```

## JarException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ JarException()  [API 1+]
+ JarException(String)  [API 1+]
```

## JarFile — TeaVM class library; API 1+ (10 +, 0 *, 0 ~, 1 -)

```
+ JarFile(File)  [API 1+]
+ JarFile(File, boolean)  [API 1+]
+ JarFile(File, boolean, int)  [API 1+]
+ JarFile(String)  [API 1+]
+ JarFile(String, boolean)  [API 1+]
+ entries(): Enumeration  [API 1+]
+ getEntry(String): ZipEntry  [API 1+]
+ getInputStream(ZipEntry): InputStream  [API 1+]
+ getJarEntry(String): JarEntry  [API 1+]
+ getManifest(): Manifest  [API 1+]
- stream(): Stream  [API 1+]
```

## JarInputStream — TeaVM class library; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ JarInputStream(InputStream)  [API 1+]
+ JarInputStream(InputStream, boolean)  [API 1+]
+ createZipEntry(String): ZipEntry  [API 1+]
+ getManifest(): Manifest  [API 1+]
+ getNextEntry(): ZipEntry  [API 1+]
+ getNextJarEntry(): JarEntry  [API 1+]
+ read(byte[], int, int): int  [API 1+]
```

## JarOutputStream — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ JarOutputStream(OutputStream)  [API 1+]
+ JarOutputStream(OutputStream, Manifest)  [API 1+]
+ putNextEntry(ZipEntry): void  [API 1+]
```

## Manifest — TeaVM class library; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ Manifest()  [API 1+]
+ Manifest(InputStream)  [API 1+]
+ Manifest(Manifest)  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getAttributes(String): Attributes  [API 1+]
+ getEntries(): Map  [API 1+]
+ getMainAttributes(): Attributes  [API 1+]
+ hashCode(): int  [API 1+]
+ read(InputStream): void  [API 1+]
+ write(OutputStream): void  [API 1+]
```

## Pack200 — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- newPacker(): Pack200.Packer [static]  [API 1+]
- newUnpacker(): Pack200.Unpacker [static]  [API 1+]
```

## Pack200.Packer — declaration; API 1+ (3 +, 0 *, 0 ~, 2 -)

```
- addPropertyChangeListener(PropertyChangeListener): void  [API 3+, deprecated in 26]
+ pack(JarFile, OutputStream): void  [API 1+]
+ pack(JarInputStream, OutputStream): void  [API 1+]
+ properties(): SortedMap  [API 1+]
- removePropertyChangeListener(PropertyChangeListener): void  [API 3+, deprecated in 26]
```

## Pack200.Unpacker — declaration; API 1+ (3 +, 0 *, 0 ~, 2 -)

```
- addPropertyChangeListener(PropertyChangeListener): void  [API 3+, deprecated in 26]
+ properties(): SortedMap  [API 1+]
- removePropertyChangeListener(PropertyChangeListener): void  [API 3+, deprecated in 26]
+ unpack(File, JarOutputStream): void  [API 1+]
+ unpack(InputStream, JarOutputStream): void  [API 1+]
```


# java.nio.charset

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CharacterCodingException — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ CharacterCodingException()  [API 1+]
```

## Charset — TeaVM class library; API 1+ (17 +, 0 *, 0 ~, 4 -)

```
+ Charset(String, String[])  [API 1+]
+ aliases(): Set  [API 1+]
- availableCharsets(): SortedMap [static]  [API 1+]
+ canEncode(): boolean  [API 1+]
+ compareTo(Charset): int  [API 1+]
+ contains(Charset): boolean  [API 1+]
+ decode(ByteBuffer): CharBuffer  [API 1+]
+ defaultCharset(): Charset [static]  [API 1+]
+ displayName(): String  [API 1+]
- displayName(Locale): String  [API 1+]
+ encode(CharBuffer): ByteBuffer  [API 1+]
+ encode(String): ByteBuffer  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forName(String): Charset [static]  [API 1+]
+ hashCode(): int  [API 1+]
- isRegistered(): boolean  [API 1+]
- isSupported(String): boolean [static]  [API 1+]
+ name(): String  [API 1+]
+ newDecoder(): CharsetDecoder  [API 1+]
+ newEncoder(): CharsetEncoder  [API 1+]
+ toString(): String  [API 1+]
```

## CharsetDecoder — TeaVM class library; API 1+ (23 +, 0 *, 0 ~, 0 -)

```
+ CharsetDecoder(Charset, float, float)  [API 1+]
+ averageCharsPerByte(): float  [API 1+]
+ charset(): Charset  [API 1+]
+ decode(ByteBuffer): CharBuffer  [API 1+]
+ decode(ByteBuffer, CharBuffer, boolean): CoderResult  [API 1+]
+ decodeLoop(ByteBuffer, CharBuffer): CoderResult  [API 1+]
+ detectedCharset(): Charset  [API 1+]
+ flush(CharBuffer): CoderResult  [API 1+]
+ implFlush(CharBuffer): CoderResult  [API 1+]
+ implOnMalformedInput(CodingErrorAction): void  [API 1+]
+ implOnUnmappableCharacter(CodingErrorAction): void  [API 1+]
+ implReplaceWith(String): void  [API 1+]
+ implReset(): void  [API 1+]
+ isAutoDetecting(): boolean  [API 1+]
+ isCharsetDetected(): boolean  [API 1+]
+ malformedInputAction(): CodingErrorAction  [API 1+]
+ maxCharsPerByte(): float  [API 1+]
+ onMalformedInput(CodingErrorAction): CharsetDecoder  [API 1+]
+ onUnmappableCharacter(CodingErrorAction): CharsetDecoder  [API 1+]
+ replaceWith(String): CharsetDecoder  [API 1+]
+ replacement(): String  [API 1+]
+ reset(): CharsetDecoder  [API 1+]
+ unmappableCharacterAction(): CodingErrorAction  [API 1+]
```

## CharsetEncoder — TeaVM class library; API 1+ (23 +, 0 *, 0 ~, 1 -)

```
+ CharsetEncoder(Charset, float, float)  [API 1+]
+ CharsetEncoder(Charset, float, float, byte[])  [API 1+]
+ averageBytesPerChar(): float  [API 1+]
+ canEncode(CharSequence): boolean  [API 1+]
+ canEncode(char): boolean  [API 1+]
+ charset(): Charset  [API 1+]
+ encode(CharBuffer): ByteBuffer  [API 1+]
+ encode(CharBuffer, ByteBuffer, boolean): CoderResult  [API 1+]
+ encodeLoop(CharBuffer, ByteBuffer): CoderResult  [API 1+]
+ flush(ByteBuffer): CoderResult  [API 1+]
+ implFlush(ByteBuffer): CoderResult  [API 1+]
+ implOnMalformedInput(CodingErrorAction): void  [API 1+]
+ implOnUnmappableCharacter(CodingErrorAction): void  [API 1+]
+ implReplaceWith(byte[]): void  [API 1+]
+ implReset(): void  [API 1+]
- isLegalReplacement(byte[]): boolean  [API 1+]
+ malformedInputAction(): CodingErrorAction  [API 1+]
+ maxBytesPerChar(): float  [API 1+]
+ onMalformedInput(CodingErrorAction): CharsetEncoder  [API 1+]
+ onUnmappableCharacter(CodingErrorAction): CharsetEncoder  [API 1+]
+ replaceWith(byte[]): CharsetEncoder  [API 1+]
+ replacement(): byte[]  [API 1+]
+ reset(): CharsetEncoder  [API 1+]
+ unmappableCharacterAction(): CodingErrorAction  [API 1+]
```

## CoderMalfunctionError — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- CoderMalfunctionError(Exception)  [API 1+]
```

## CoderResult — TeaVM class library; API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ OVERFLOW [field]  [API 1+]
+ UNDERFLOW [field]  [API 1+]
+ isError(): boolean  [API 1+]
+ isMalformed(): boolean  [API 1+]
+ isOverflow(): boolean  [API 1+]
+ isUnderflow(): boolean  [API 1+]
+ isUnmappable(): boolean  [API 1+]
+ length(): int  [API 1+]
+ malformedForLength(int): CoderResult [static]  [API 1+]
+ throwException(): void  [API 1+]
+ toString(): String  [API 1+]
+ unmappableForLength(int): CoderResult [static]  [API 1+]
```

## CodingErrorAction — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ IGNORE [field]  [API 1+]
+ REPLACE [field]  [API 1+]
+ REPORT [field]  [API 1+]
+ toString(): String  [API 1+]
```

## IllegalCharsetNameException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ IllegalCharsetNameException(String)  [API 1+]
+ getCharsetName(): String  [API 1+]
```

## MalformedInputException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 1 -)

```
+ MalformedInputException(int)  [API 1+]
- getInputLength(): int  [API 1+]
+ getMessage(): String  [API 1+]
```

## StandardCharsets — TeaVM class library; API 19+ (0 +, 0 *, 0 ~, 0 -)

```
+ ISO_8859_1 [field]  [API 19+]
+ US_ASCII [field]  [API 19+]
+ UTF_16 [field]  [API 19+]
+ UTF_16BE [field]  [API 19+]
+ UTF_16LE [field]  [API 19+]
+ UTF_8 [field]  [API 19+]
```

## UnmappableCharacterException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 1 -)

```
+ UnmappableCharacterException(int)  [API 1+]
- getInputLength(): int  [API 1+]
+ getMessage(): String  [API 1+]
```

## UnsupportedCharsetException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnsupportedCharsetException(String)  [API 1+]
+ getCharsetName(): String  [API 1+]
```


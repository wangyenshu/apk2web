# org.apache.http.message

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractHttpMessage — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ AbstractHttpMessage()  [API 1-22, removed in 23]
+ AbstractHttpMessage(HttpParams)  [API 1-22, removed in 23]
+ headergroup [field]  [API 1-22, removed in 23]
+ params [field]  [API 1-22, removed in 23]
```

## BasicHeader — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicHeader(String, String)  [API 1-22, removed in 23]
```

## BasicHeaderElement — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicHeaderElement(String, String)  [API 1-22, removed in 23]
+ BasicHeaderElement(String, String, NameValuePair[])  [API 1-22, removed in 23]
```

## BasicHeaderElementIterator — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ BasicHeaderElementIterator(HeaderIterator)  [API 1-22, removed in 23]
+ BasicHeaderElementIterator(HeaderIterator, HeaderValueParser)  [API 1-22, removed in 23]
```

## BasicHeaderIterator — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ BasicHeaderIterator(Header[], String)  [API 1-22, removed in 23]
+ allHeaders [field]  [API 1-22, removed in 23]
+ currentIndex [field]  [API 1-22, removed in 23]
+ filterHeader(int): boolean  [API 1-22, removed in 23]
+ findNext(int): int  [API 1-22, removed in 23]
+ headerName [field]  [API 1-22, removed in 23]
```

## BasicHeaderValueFormatter — bundled library; API 1-22, removed in 23, deprecated in 22 (12 +, 0 *, 0 ~, 0 -)

```
+ BasicHeaderValueFormatter()  [API 1-22, removed in 23]
+ doFormatValue(CharArrayBuffer, String, boolean): void  [API 1-22, removed in 23]
+ estimateElementsLen(HeaderElement[]): int  [API 1-22, removed in 23]
+ estimateHeaderElementLen(HeaderElement): int  [API 1-22, removed in 23]
+ estimateNameValuePairLen(NameValuePair): int  [API 1-22, removed in 23]
+ estimateParametersLen(NameValuePair[]): int  [API 1-22, removed in 23]
+ formatElements(HeaderElement[], boolean, HeaderValueFormatter): String  [API 1-22, removed in 23]
+ formatHeaderElement(HeaderElement, boolean, HeaderValueFormatter): String  [API 1-22, removed in 23]
+ formatNameValuePair(NameValuePair, boolean, HeaderValueFormatter): String  [API 1-22, removed in 23]
+ formatParameters(NameValuePair[], boolean, HeaderValueFormatter): String  [API 1-22, removed in 23]
+ isSeparator(char): boolean  [API 1-22, removed in 23]
+ isUnsafe(char): boolean  [API 1-22, removed in 23]
```

## BasicHeaderValueParser — bundled library; API 1-22, removed in 23, deprecated in 22 (8 +, 0 *, 0 ~, 0 -)

```
+ BasicHeaderValueParser()  [API 1-22, removed in 23]
+ createHeaderElement(String, String, NameValuePair[]): HeaderElement  [API 1-22, removed in 23]
+ createNameValuePair(String, String): NameValuePair  [API 1-22, removed in 23]
+ parseElements(String, HeaderValueParser): HeaderElement[]  [API 1-22, removed in 23]
+ parseHeaderElement(String, HeaderValueParser): HeaderElement  [API 1-22, removed in 23]
+ parseNameValuePair(CharArrayBuffer, ParserCursor, char[]): NameValuePair  [API 1-22, removed in 23]
+ parseNameValuePair(String, HeaderValueParser): NameValuePair  [API 1-22, removed in 23]
+ parseParameters(String, HeaderValueParser): NameValuePair[]  [API 1-22, removed in 23]
```

## BasicHttpEntityEnclosingRequest — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ BasicHttpEntityEnclosingRequest(RequestLine)  [API 1-22, removed in 23]
+ BasicHttpEntityEnclosingRequest(String, String)  [API 1-22, removed in 23]
+ BasicHttpEntityEnclosingRequest(String, String, ProtocolVersion)  [API 1-22, removed in 23]
```

## BasicHttpRequest — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ BasicHttpRequest(RequestLine)  [API 1-22, removed in 23]
+ BasicHttpRequest(String, String)  [API 1-22, removed in 23]
+ BasicHttpRequest(String, String, ProtocolVersion)  [API 1-22, removed in 23]
```

## BasicHttpResponse — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ BasicHttpResponse(ProtocolVersion, int, String)  [API 1-22, removed in 23]
+ BasicHttpResponse(StatusLine)  [API 1-22, removed in 23]
+ BasicHttpResponse(StatusLine, ReasonPhraseCatalog, Locale)  [API 1-22, removed in 23]
+ getReason(int): String  [API 1-22, removed in 23]
```

## BasicLineFormatter — bundled library; API 1-22, removed in 23, deprecated in 22 (10 +, 0 *, 0 ~, 0 -)

```
+ BasicLineFormatter()  [API 1-22, removed in 23]
+ doFormatHeader(CharArrayBuffer, Header): void  [API 1-22, removed in 23]
+ doFormatRequestLine(CharArrayBuffer, RequestLine): void  [API 1-22, removed in 23]
+ doFormatStatusLine(CharArrayBuffer, StatusLine): void  [API 1-22, removed in 23]
+ estimateProtocolVersionLen(ProtocolVersion): int  [API 1-22, removed in 23]
+ formatHeader(Header, LineFormatter): String  [API 1-22, removed in 23]
+ formatProtocolVersion(ProtocolVersion, LineFormatter): String  [API 1-22, removed in 23]
+ formatRequestLine(RequestLine, LineFormatter): String  [API 1-22, removed in 23]
+ formatStatusLine(StatusLine, LineFormatter): String  [API 1-22, removed in 23]
+ initBuffer(CharArrayBuffer): CharArrayBuffer  [API 1-22, removed in 23]
```

## BasicLineParser — bundled library; API 1-22, removed in 23, deprecated in 22 (10 +, 0 *, 0 ~, 0 -)

```
+ BasicLineParser()  [API 1-22, removed in 23]
+ BasicLineParser(ProtocolVersion)  [API 1-22, removed in 23]
+ createProtocolVersion(int, int): ProtocolVersion  [API 1-22, removed in 23]
+ createRequestLine(String, String, ProtocolVersion): RequestLine  [API 1-22, removed in 23]
+ createStatusLine(ProtocolVersion, int, String): StatusLine  [API 1-22, removed in 23]
+ parseHeader(String, LineParser): Header  [API 1-22, removed in 23]
+ parseProtocolVersion(String, LineParser): ProtocolVersion  [API 1-22, removed in 23]
+ parseRequestLine(String, LineParser): RequestLine  [API 1-22, removed in 23]
+ parseStatusLine(String, LineParser): StatusLine  [API 1-22, removed in 23]
+ protocol [field]  [API 1-22, removed in 23]
+ skipWhitespace(CharArrayBuffer, ParserCursor): void  [API 1-22, removed in 23]
```

## BasicListHeaderIterator — bundled library; API 1-22, removed in 23, deprecated in 22 (3 +, 0 *, 0 ~, 0 -)

```
+ BasicListHeaderIterator(List, String)  [API 1-22, removed in 23]
+ allHeaders [field]  [API 1-22, removed in 23]
+ currentIndex [field]  [API 1-22, removed in 23]
+ filterHeader(int): boolean  [API 1-22, removed in 23]
+ findNext(int): int  [API 1-22, removed in 23]
+ headerName [field]  [API 1-22, removed in 23]
+ lastIndex [field]  [API 1-22, removed in 23]
```

## BasicNameValuePair — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicNameValuePair(String, String)  [API 1-22, removed in 23]
```

## BasicRequestLine — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicRequestLine(String, String, ProtocolVersion)  [API 1-22, removed in 23]
```

## BasicStatusLine — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BasicStatusLine(ProtocolVersion, int, String)  [API 1-22, removed in 23]
```

## BasicTokenIterator — bundled library; API 1-22, removed in 23, deprecated in 22 (10 +, 0 *, 0 ~, 0 -)

```
+ BasicTokenIterator(HeaderIterator)  [API 1-22, removed in 23]
+ createToken(String, int, int): String  [API 1-22, removed in 23]
+ currentHeader [field]  [API 1-22, removed in 23]
+ currentToken [field]  [API 1-22, removed in 23]
+ findNext(int): int  [API 1-22, removed in 23]
+ findTokenEnd(int): int  [API 1-22, removed in 23]
+ findTokenSeparator(int): int  [API 1-22, removed in 23]
+ findTokenStart(int): int  [API 1-22, removed in 23]
+ headerIt [field]  [API 1-22, removed in 23]
+ isHttpSeparator(char): boolean  [API 1-22, removed in 23]
+ isTokenChar(char): boolean  [API 1-22, removed in 23]
+ isTokenSeparator(char): boolean  [API 1-22, removed in 23]
+ isWhitespace(char): boolean  [API 1-22, removed in 23]
+ searchPos [field]  [API 1-22, removed in 23]
```

## BufferedHeader — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ BufferedHeader(CharArrayBuffer)  [API 1-22, removed in 23]
```

## HeaderGroup — bundled library; API 1-22, removed in 23, deprecated in 22 (15 +, 0 *, 0 ~, 0 -)

```
+ HeaderGroup()  [API 1-22, removed in 23]
+ addHeader(Header): void  [API 1-22, removed in 23]
+ clear(): void  [API 1-22, removed in 23]
+ containsHeader(String): boolean  [API 1-22, removed in 23]
+ copy(): HeaderGroup  [API 1-22, removed in 23]
+ getAllHeaders(): Header[]  [API 1-22, removed in 23]
+ getCondensedHeader(String): Header  [API 1-22, removed in 23]
+ getFirstHeader(String): Header  [API 1-22, removed in 23]
+ getHeaders(String): Header[]  [API 1-22, removed in 23]
+ getLastHeader(String): Header  [API 1-22, removed in 23]
+ iterator(): HeaderIterator  [API 1-22, removed in 23]
+ iterator(String): HeaderIterator  [API 1-22, removed in 23]
+ removeHeader(Header): void  [API 1-22, removed in 23]
+ setHeaders(Header[]): void  [API 1-22, removed in 23]
+ updateHeader(Header): void  [API 1-22, removed in 23]
```

## HeaderValueFormatter — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ formatElements(CharArrayBuffer, HeaderElement[], boolean): CharArrayBuffer  [API 1-22, removed in 23]
+ formatHeaderElement(CharArrayBuffer, HeaderElement, boolean): CharArrayBuffer  [API 1-22, removed in 23]
+ formatNameValuePair(CharArrayBuffer, NameValuePair, boolean): CharArrayBuffer  [API 1-22, removed in 23]
+ formatParameters(CharArrayBuffer, NameValuePair[], boolean): CharArrayBuffer  [API 1-22, removed in 23]
```

## HeaderValueParser — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ parseElements(CharArrayBuffer, ParserCursor): HeaderElement[]  [API 1-22, removed in 23]
+ parseHeaderElement(CharArrayBuffer, ParserCursor): HeaderElement  [API 1-22, removed in 23]
+ parseNameValuePair(CharArrayBuffer, ParserCursor): NameValuePair  [API 1-22, removed in 23]
+ parseParameters(CharArrayBuffer, ParserCursor): NameValuePair[]  [API 1-22, removed in 23]
```

## LineFormatter — bundled library; API 1-22, removed in 23, deprecated in 22 (4 +, 0 *, 0 ~, 0 -)

```
+ appendProtocolVersion(CharArrayBuffer, ProtocolVersion): CharArrayBuffer  [API 1-22, removed in 23]
+ formatHeader(CharArrayBuffer, Header): CharArrayBuffer  [API 1-22, removed in 23]
+ formatRequestLine(CharArrayBuffer, RequestLine): CharArrayBuffer  [API 1-22, removed in 23]
+ formatStatusLine(CharArrayBuffer, StatusLine): CharArrayBuffer  [API 1-22, removed in 23]
```

## LineParser — bundled library; API 1-22, removed in 23, deprecated in 22 (5 +, 0 *, 0 ~, 0 -)

```
+ hasProtocolVersion(CharArrayBuffer, ParserCursor): boolean  [API 1-22, removed in 23]
+ parseHeader(CharArrayBuffer): Header  [API 1-22, removed in 23]
+ parseProtocolVersion(CharArrayBuffer, ParserCursor): ProtocolVersion  [API 1-22, removed in 23]
+ parseRequestLine(CharArrayBuffer, ParserCursor): RequestLine  [API 1-22, removed in 23]
+ parseStatusLine(CharArrayBuffer, ParserCursor): StatusLine  [API 1-22, removed in 23]
```

## ParserCursor — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ ParserCursor(int, int)  [API 1-22, removed in 23]
+ atEnd(): boolean  [API 1-22, removed in 23]
+ getLowerBound(): int  [API 1-22, removed in 23]
+ getPos(): int  [API 1-22, removed in 23]
+ getUpperBound(): int  [API 1-22, removed in 23]
+ updatePos(int): void  [API 1-22, removed in 23]
```


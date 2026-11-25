# org.apache.http.params

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractHttpParams — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ AbstractHttpParams()  [API 1-22, removed in 23]
```

## BasicHttpParams — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ BasicHttpParams()  [API 1-22, removed in 23]
+ clear(): void  [API 1-22, removed in 23]
+ copyParams(HttpParams): void  [API 1-22, removed in 23]
+ isParameterSet(String): boolean  [API 1-22, removed in 23]
+ isParameterSetLocally(String): boolean  [API 1-22, removed in 23]
+ setParameters(String[], Object): void  [API 1-22, removed in 23]
```

## CoreConnectionPNames — bundled library; API 1+, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## CoreProtocolPNames — bundled library; API 1-22, removed in 23, deprecated in 22 (0 +, 0 *, 0 ~, 0 -)

## DefaultedHttpParams — bundled library; API 1-22, removed in 23, deprecated in 22 (2 +, 0 *, 0 ~, 0 -)

```
+ DefaultedHttpParams(HttpParams, HttpParams)  [API 1-22, removed in 23]
+ getDefaults(): HttpParams  [API 1-22, removed in 23]
```

## HttpAbstractParamBean — bundled library; API 1-22, removed in 23, deprecated in 22 (1 +, 0 *, 0 ~, 0 -)

```
+ HttpAbstractParamBean(HttpParams)  [API 1-22, removed in 23]
+ params [field]  [API 1-22, removed in 23]
```

## HttpConnectionParamBean — bundled library; API 1-22, removed in 23, deprecated in 22 (7 +, 0 *, 0 ~, 0 -)

```
+ HttpConnectionParamBean(HttpParams)  [API 1-22, removed in 23]
+ setConnectionTimeout(int): void  [API 1-22, removed in 23]
+ setLinger(int): void  [API 1-22, removed in 23]
+ setSoTimeout(int): void  [API 1-22, removed in 23]
+ setSocketBufferSize(int): void  [API 1-22, removed in 23]
+ setStaleCheckingEnabled(boolean): void  [API 1-22, removed in 23]
+ setTcpNoDelay(boolean): void  [API 1-22, removed in 23]
```

## HttpConnectionParams — bundled library; API 1+, deprecated in 22 (12 +, 0 *, 0 ~, 0 -)

```
+ getConnectionTimeout(HttpParams): int [static]  [API 1+]
+ getLinger(HttpParams): int [static]  [API 1+]
+ getSoTimeout(HttpParams): int [static]  [API 1+]
+ getSocketBufferSize(HttpParams): int [static]  [API 1+]
+ getTcpNoDelay(HttpParams): boolean [static]  [API 1+]
+ isStaleCheckingEnabled(HttpParams): boolean [static]  [API 1+]
+ setConnectionTimeout(HttpParams, int): void [static]  [API 1+]
+ setLinger(HttpParams, int): void [static]  [API 1+]
+ setSoTimeout(HttpParams, int): void [static]  [API 1+]
+ setSocketBufferSize(HttpParams, int): void [static]  [API 1+]
+ setStaleCheckingEnabled(HttpParams, boolean): void [static]  [API 1+]
+ setTcpNoDelay(HttpParams, boolean): void [static]  [API 1+]
```

## HttpParams — bundled library; API 1+, deprecated in 22 (14 +, 0 *, 0 ~, 0 -)

```
+ copy(): HttpParams  [API 1+]
+ getBooleanParameter(String, boolean): boolean  [API 1+]
+ getDoubleParameter(String, double): double  [API 1+]
+ getIntParameter(String, int): int  [API 1+]
+ getLongParameter(String, long): long  [API 1+]
+ getParameter(String): Object  [API 1+]
+ isParameterFalse(String): boolean  [API 1+]
+ isParameterTrue(String): boolean  [API 1+]
+ removeParameter(String): boolean  [API 1+]
+ setBooleanParameter(String, boolean): HttpParams  [API 1+]
+ setDoubleParameter(String, double): HttpParams  [API 1+]
+ setIntParameter(String, int): HttpParams  [API 1+]
+ setLongParameter(String, long): HttpParams  [API 1+]
+ setParameter(String, Object): HttpParams  [API 1+]
```

## HttpProtocolParamBean — bundled library; API 1-22, removed in 23, deprecated in 22 (6 +, 0 *, 0 ~, 0 -)

```
+ HttpProtocolParamBean(HttpParams)  [API 1-22, removed in 23]
+ setContentCharset(String): void  [API 1-22, removed in 23]
+ setHttpElementCharset(String): void  [API 1-22, removed in 23]
+ setUseExpectContinue(boolean): void  [API 1-22, removed in 23]
+ setUserAgent(String): void  [API 1-22, removed in 23]
+ setVersion(HttpVersion): void  [API 1-22, removed in 23]
```

## HttpProtocolParams — bundled library; API 1-22, removed in 23, deprecated in 22 (10 +, 0 *, 0 ~, 0 -)

```
+ getContentCharset(HttpParams): String  [API 1-22, removed in 23]
+ getHttpElementCharset(HttpParams): String  [API 1-22, removed in 23]
+ getUserAgent(HttpParams): String  [API 1-22, removed in 23]
+ getVersion(HttpParams): ProtocolVersion  [API 1-22, removed in 23]
+ setContentCharset(HttpParams, String): void  [API 1-22, removed in 23]
+ setHttpElementCharset(HttpParams, String): void  [API 1-22, removed in 23]
+ setUseExpectContinue(HttpParams, boolean): void  [API 1-22, removed in 23]
+ setUserAgent(HttpParams, String): void  [API 1-22, removed in 23]
+ setVersion(HttpParams, ProtocolVersion): void  [API 1-22, removed in 23]
+ useExpectContinue(HttpParams): boolean  [API 1-22, removed in 23]
```


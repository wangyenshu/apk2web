# android.webkit

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CacheManager — declaration; API 1-16, removed in 17, deprecated in 11 (0 +, 0 *, 0 ~, 7 -)

```
- CacheManager()  [API 1-16, removed in 17]
- cacheDisabled(): boolean  [API 1-16, removed in 17]
- endCacheTransaction(): boolean  [API 1-16, removed in 17]
- getCacheFile(String, Map): CacheManager.CacheResult  [API 1-16, removed in 17]
- getCacheFileBaseDir(): File  [API 1-16, removed in 17]
- saveCacheFile(String, CacheManager.CacheResult): void  [API 1-16, removed in 17]
- startCacheTransaction(): boolean  [API 1-16, removed in 17]
```

## CacheManager.CacheResult — declaration; API 1-16, removed in 17, deprecated in 11 (0 +, 5 *, 0 ~, 11 -)

```
* CacheResult()  [API 1-16, removed in 17]
- getContentDisposition(): String  [API 7-16, removed in 17]
- getContentLength(): long  [API 1-16, removed in 17]
- getETag(): String  [API 1-16, removed in 17]
* getEncoding(): String  [API 1-16, removed in 17]
- getExpires(): long  [API 1-16, removed in 17]
- getExpiresString(): String  [API 7-16, removed in 17]
- getHttpStatusCode(): int  [API 1-16, removed in 17]
* getInputStream(): InputStream  [API 1-16, removed in 17]
- getLastModified(): String  [API 1-16, removed in 17]
- getLocalPath(): String  [API 1-16, removed in 17]
- getLocation(): String  [API 1-16, removed in 17]
- getMimeType(): String  [API 1-16, removed in 17]
- getOutputStream(): OutputStream  [API 1-16, removed in 17]
* setEncoding(String): void  [API 1-16, removed in 17]
* setInputStream(InputStream): void  [API 1-16, removed in 17]
```

## ClientCertRequest — declaration; API 21+ (8 +, 0 *, 0 ~, 0 -)

```
+ ClientCertRequest()  [API 21+]
+ cancel(): void  [API 21+]
+ getHost(): String  [API 21+]
+ getKeyTypes(): String[]  [API 21+]
+ getPort(): int  [API 21+]
+ getPrincipals(): Principal[]  [API 21+]
+ ignore(): void  [API 21+]
+ proceed(PrivateKey, X509Certificate[]): void  [API 21+]
```

## ConsoleMessage — declaration; API 8+ (0 +, 0 *, 0 ~, 5 -)

```
- ConsoleMessage(String, String, int, ConsoleMessage.MessageLevel)  [API 8+]
- lineNumber(): int  [API 8+]
- message(): String  [API 8+]
- messageLevel(): ConsoleMessage.MessageLevel  [API 8+]
- sourceId(): String  [API 8+]
```

## ConsoleMessage.MessageLevel — declaration; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ DEBUG [field]  [API 8+]
+ ERROR [field]  [API 8+]
+ LOG [field]  [API 8+]
+ TIP [field]  [API 8+]
+ WARNING [field]  [API 8+]
+ valueOf(String): ConsoleMessage.MessageLevel [static]  [API 8+]
+ values(): ConsoleMessage.MessageLevel[] [static]  [API 8+]
```

## CookieManager — framework; API 1+ (3 +, 0 *, 8 ~, 8 -)

```
+ CookieManager()  [API 22+, deprecated in 29]
~ acceptCookie(): boolean  [API 1+]
- acceptThirdPartyCookies(WebView): boolean  [API 21+]
- allowFileSchemeCookies(): boolean [static]  [API 12+]
+ clone(): Object  [API 1+]
- flush(): void  [API 21+]
~ getCookie(String): String  [API 1+]
+ getInstance(): CookieManager [static]  [API 1+]
~ hasCookies(): boolean  [API 1+]
~ removeAllCookie(): void  [API 1+, deprecated in 21]
- removeAllCookies(ValueCallback): void  [API 21+]
~ removeExpiredCookie(): void  [API 1+, deprecated in 21]
~ removeSessionCookie(): void  [API 1+, deprecated in 21]
- removeSessionCookies(ValueCallback): void  [API 21+]
~ setAcceptCookie(boolean): void  [API 1+]
- setAcceptFileSchemeCookies(boolean): void [static]  [API 12+, deprecated in 30]
- setAcceptThirdPartyCookies(WebView, boolean): void  [API 21+]
~ setCookie(String, String): void  [API 1+]
- setCookie(String, String, ValueCallback): void  [API 21+]
```

## CookieSyncManager — framework; API 1+, deprecated in 21 (2 +, 0 *, 4 ~, 3 -)

```
- CookieSyncManager(Context, String)  [API 1-27, removed in 28]
+ createInstance(Context): CookieSyncManager [static]  [API 1+]
+ getInstance(): CookieSyncManager [static]  [API 1+]
- mDataBase [field]  [API 21-27, removed in 28]
- mHandler [field]  [API 21-27, removed in 28]
~ resetSync(): void  [API 1+]
- run(): void  [API 1+]
~ startSync(): void  [API 1+]
~ stopSync(): void  [API 1+]
~ sync(): void  [API 1+]
- syncFromRamToFlash(): void  [API 1+]
```

## DateSorter — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- DateSorter(Context)  [API 1+]
- getBoundary(int): long  [API 1+]
- getIndex(long): int  [API 1+]
- getLabel(int): String  [API 1+]
```

## DownloadListener — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDownloadStart(String, String, String, String, long): void  [API 1+]
```

## GeolocationPermissions — declaration; API 5+ (0 +, 0 *, 0 ~, 7 -)

```
- GeolocationPermissions()  [API 5-15, removed in 16]
- allow(String): void  [API 7+]
- clear(String): void  [API 7+]
- clearAll(): void  [API 7+]
- getAllowed(String, ValueCallback): void  [API 7+]
- getInstance(): GeolocationPermissions [static]  [API 7+]
- getOrigins(ValueCallback): void  [API 7+]
```

## GeolocationPermissions.Callback — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ invoke(String, boolean, boolean): void  [API 5+]
```

## HttpAuthHandler — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- cancel(): void  [API 1+]
- proceed(String, String): void  [API 1+]
- useHttpAuthUsernamePassword(): boolean  [API 1+]
```

## JavascriptInterface — framework; API 17+ (0 +, 0 *, 0 ~, 0 -)

## JsPromptResult — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- confirm(String): void  [API 1+]
```

## JsResult — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- cancel(): void  [API 1+]
- confirm(): void  [API 1+]
- mProxy [field]  [API 1-7, removed in 8]
+ mResult [field]  [API 1-15, removed in 16]
- wakeUp(): void  [API 1-15, removed in 16]
```

## MimeTypeMap — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- getExtensionFromMimeType(String): String  [API 1+]
- getFileExtensionFromUrl(String): String [static]  [API 1+]
- getMimeTypeFromExtension(String): String  [API 1+]
- getSingleton(): MimeTypeMap [static]  [API 1+]
- hasExtension(String): boolean  [API 1+]
- hasMimeType(String): boolean  [API 1+]
```

## PermissionRequest — declaration; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ PermissionRequest()  [API 21+]
+ deny(): void  [API 21+]
+ getOrigin(): Uri  [API 21+]
+ getResources(): String[]  [API 21+]
+ grant(String[]): void  [API 21+]
```

## Plugin — declaration; API 1-11, removed in 12, deprecated in 5 (0 +, 9 *, 0 ~, 2 -)

```
* Plugin(String, String, String, String)  [API 1-11, removed in 12]
- dispatchClickEvent(Context): void  [API 1-11, removed in 12]
* getDescription(): String  [API 1-11, removed in 12]
* getFileName(): String  [API 1-11, removed in 12]
* getName(): String  [API 1-11, removed in 12]
* getPath(): String  [API 1-11, removed in 12]
- setClickHandler(Plugin.PreferencesClickHandler): void  [API 1-11, removed in 12]
* setDescription(String): void  [API 1-11, removed in 12]
* setFileName(String): void  [API 1-11, removed in 12]
* setName(String): void  [API 1-11, removed in 12]
* setPath(String): void  [API 1-11, removed in 12]
```

## Plugin.PreferencesClickHandler — declaration; API 1-11, removed in 12 (1 +, 0 *, 0 ~, 0 -)

```
+ handleClickEvent(Context): void  [API 1-11, removed in 12]
```

## PluginData — declaration; API 3-11, removed in 12, deprecated in 5 (0 +, 0 *, 0 ~, 5 -)

```
- PluginData(InputStream, long, Map, int)  [API 3-11, removed in 12]
- getContentLength(): long  [API 3-11, removed in 12]
- getHeaders(): Map  [API 3-11, removed in 12]
- getInputStream(): InputStream  [API 3-11, removed in 12]
- getStatusCode(): int  [API 3-11, removed in 12]
```

## PluginList — declaration; API 1-11, removed in 12, deprecated in 5 (0 +, 0 *, 0 ~, 6 -)

```
- PluginList()  [API 1-11, removed in 12]
- addPlugin(Plugin): void  [API 1-11, removed in 12]
- clear(): void  [API 1-11, removed in 12]
- getList(): List  [API 1-11, removed in 12]
- pluginClicked(Context, int): void  [API 1-11, removed in 12]
- removePlugin(Plugin): void  [API 1-11, removed in 12]
```

## PluginStub — declaration; API 5+ (2 +, 0 *, 0 ~, 0 -)

```
+ getEmbeddedView(int, Context): View  [API 5+]
+ getFullScreenView(int, Context): View  [API 5+]
```

## RenderProcessGoneDetail — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ RenderProcessGoneDetail()  [API 26+, deprecated in 29]
+ didCrash(): boolean  [API 26+]
+ rendererPriorityAtExit(): int  [API 26+]
```

## SafeBrowsingResponse — declaration; API 27+ (4 +, 0 *, 0 ~, 0 -)

```
+ SafeBrowsingResponse()  [API 27+, deprecated in 29]
+ backToSafety(boolean): void  [API 27+]
+ proceed(boolean): void  [API 27+]
+ showInterstitial(boolean): void  [API 27+]
```

## ServiceWorkerClient — declaration; API 24+ (0 +, 0 *, 0 ~, 2 -)

```
- ServiceWorkerClient()  [API 24+]
- shouldInterceptRequest(WebResourceRequest): WebResourceResponse  [API 24+]
```

## ServiceWorkerController — declaration; API 24+ (2 +, 0 *, 0 ~, 2 -)

```
- ServiceWorkerController()  [API 24+, deprecated in 29]
- getInstance(): ServiceWorkerController [static]  [API 24+]
+ getServiceWorkerWebSettings(): ServiceWorkerWebSettings  [API 24+]
+ setServiceWorkerClient(ServiceWorkerClient): void  [API 24+]
```

## ServiceWorkerWebSettings — declaration; API 24+ (9 +, 0 *, 0 ~, 0 -)

```
+ ServiceWorkerWebSettings()  [API 24+]
+ getAllowContentAccess(): boolean  [API 24+]
+ getAllowFileAccess(): boolean  [API 24+]
+ getBlockNetworkLoads(): boolean  [API 24+]
+ getCacheMode(): int  [API 24+]
+ setAllowContentAccess(boolean): void  [API 24+]
+ setAllowFileAccess(boolean): void  [API 24+]
+ setBlockNetworkLoads(boolean): void  [API 24+]
+ setCacheMode(int): void  [API 24+]
```

## SslErrorHandler — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- cancel(): void  [API 1+]
- proceed(): void  [API 1+]
```

## TracingConfig — declaration; API 28+ (0 +, 1 *, 0 ~, 2 -)

```
- getCustomIncludedCategories(): List  [API 28+]
- getPredefinedCategories(): int  [API 28+]
* getTracingMode(): int  [API 28+]
```

## TracingConfig.Builder — declaration; API 28+ (0 +, 3 *, 0 ~, 3 -)

```
* Builder()  [API 28+]
- addCategories(Collection): TracingConfig.Builder  [API 28+]
- addCategories(String[]): TracingConfig.Builder  [API 28+]
- addCategories(int[]): TracingConfig.Builder  [API 28+]
* build(): TracingConfig  [API 28+]
* setTracingMode(int): TracingConfig.Builder  [API 28+]
```

## TracingController — declaration; API 28+ (3 +, 0 *, 0 ~, 2 -)

```
- TracingController()  [API 28+, deprecated in 29]
- getInstance(): TracingController [static]  [API 28+]
+ isTracing(): boolean  [API 28+]
+ start(TracingConfig): void  [API 28+]
+ stop(OutputStream, Executor): boolean  [API 28+]
```

## URLUtil — declaration; API 1+ (0 +, 0 *, 0 ~, 17 -)

```
- URLUtil()  [API 1+]
- composeSearchUrl(String, String, String): String [static]  [API 1+]
- decode(byte[]): byte[] [static]  [API 1+]
- guessFileName(String, String, String): String [static]  [API 1+]
- guessUrl(String): String [static]  [API 1+]
- isAboutUrl(String): boolean [static]  [API 1+]
- isAssetUrl(String): boolean [static]  [API 1+]
- isContentUrl(String): boolean [static]  [API 1+]
- isCookielessProxyUrl(String): boolean [static]  [API 1+, deprecated in 15]
- isDataUrl(String): boolean [static]  [API 1+]
- isFileUrl(String): boolean [static]  [API 1+]
- isHttpUrl(String): boolean [static]  [API 1+]
- isHttpsUrl(String): boolean [static]  [API 1+]
- isJavaScriptUrl(String): boolean [static]  [API 1+]
- isNetworkUrl(String): boolean [static]  [API 1+]
- isValidUrl(String): boolean [static]  [API 1+]
- stripAnchor(String): String [static]  [API 1+]
```

## UrlInterceptHandler — declaration; API 1-11, removed in 12, deprecated in 5 (2 +, 0 *, 0 ~, 0 -)

```
+ getPluginData(String, Map): PluginData  [API 3-11, removed in 12]
+ service(String, Map): CacheManager.CacheResult  [API 1-11, removed in 12]
```

## UrlInterceptRegistry — declaration; API 1-11, removed in 12, deprecated in 5 (0 +, 0 *, 0 ~, 7 -)

```
- UrlInterceptRegistry()  [API 1-11, removed in 12]
- getPluginData(String, Map): PluginData  [API 3-11, removed in 12]
- getSurrogate(String, Map): CacheManager.CacheResult  [API 1-11, removed in 12]
- registerHandler(UrlInterceptHandler): boolean  [API 1-11, removed in 12]
- setUrlInterceptDisabled(boolean): void  [API 1-11, removed in 12]
- unregisterHandler(UrlInterceptHandler): boolean  [API 1-11, removed in 12]
- urlInterceptDisabled(): boolean  [API 1-11, removed in 12]
```

## ValueCallback — framework; API 7+ (1 +, 0 *, 0 ~, 0 -)

```
+ onReceiveValue(Object): void  [API 7+]
```

## WebBackForwardList — declaration; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ WebBackForwardList()  [API 22+]
+ clone(): WebBackForwardList  [API 1+]
+ getCurrentIndex(): int  [API 1+]
+ getCurrentItem(): WebHistoryItem  [API 1+]
+ getItemAtIndex(int): WebHistoryItem  [API 1+]
+ getSize(): int  [API 1+]
```

## WebChromeClient — framework; API 1+ (7 +, 0 *, 0 ~, 21 -)

```
+ WebChromeClient()  [API 1+]
- getDefaultVideoPoster(): Bitmap  [API 7+]
- getVideoLoadingProgressView(): View  [API 7+]
- getVisitedHistory(ValueCallback): void  [API 7+]
+ onCloseWindow(WebView): void  [API 1+]
- onConsoleMessage(ConsoleMessage): boolean  [API 8+]
- onConsoleMessage(String, int, String): void  [API 7+, deprecated in 15]
- onCreateWindow(WebView, boolean, boolean, Message): boolean  [API 1+]
- onExceededDatabaseQuota(String, String, long, long, long, WebStorage.QuotaUpdater): void  [API 5+, deprecated in 19]
- onGeolocationPermissionsHidePrompt(): void  [API 5+]
- onGeolocationPermissionsShowPrompt(String, GeolocationPermissions.Callback): void  [API 5+]
+ onHideCustomView(): void  [API 7+]
- onJsAlert(WebView, String, String, JsResult): boolean  [API 1+]
- onJsBeforeUnload(WebView, String, String, JsResult): boolean  [API 1+]
- onJsConfirm(WebView, String, String, JsResult): boolean  [API 1+]
- onJsPrompt(WebView, String, String, String, JsPromptResult): boolean  [API 1+]
- onJsTimeout(): boolean  [API 7+, deprecated in 17]
- onPermissionRequest(PermissionRequest): void  [API 21+]
- onPermissionRequestCanceled(PermissionRequest): void  [API 21+]
+ onProgressChanged(WebView, int): void  [API 1+]
- onReachedMaxAppCacheSize(long, long, WebStorage.QuotaUpdater): void  [API 7-32, removed in 33, deprecated in 19]
- onReceivedIcon(WebView, Bitmap): void  [API 1+]
+ onReceivedTitle(WebView, String): void  [API 1+]
- onReceivedTouchIconUrl(WebView, String, boolean): void  [API 7+]
- onRequestFocus(WebView): void  [API 1+]
+ onShowCustomView(View, WebChromeClient.CustomViewCallback): void  [API 7+]
+ onShowCustomView(View, int, WebChromeClient.CustomViewCallback): void  [API 14+, deprecated in 18]
- onShowFileChooser(WebView, ValueCallback, WebChromeClient.FileChooserParams): boolean  [API 21+]
```

## WebChromeClient.CustomViewCallback — framework; API 7+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCustomViewHidden(): void  [API 7+]
```

## WebChromeClient.FileChooserParams — declaration; API 21+ (6 +, 0 *, 0 ~, 2 -)

```
- FileChooserParams()  [API 21+]
+ createIntent(): Intent  [API 21+]
+ getAcceptTypes(): String[]  [API 21+]
+ getFilenameHint(): String  [API 21+]
+ getMode(): int  [API 21+]
+ getTitle(): CharSequence  [API 21+]
+ isCaptureEnabled(): boolean  [API 21+]
- parseResult(int, Intent): Uri[] [static]  [API 21+]
```

## WebHistoryItem — declaration; API 1+ (5 +, 0 *, 0 ~, 2 -)

```
- WebHistoryItem()  [API 22+]
+ clone(): WebHistoryItem  [API 1+]
+ getFavicon(): Bitmap  [API 1+]
- getId(): int  [API 1-16, removed in 17, deprecated in 15]
+ getOriginalUrl(): String  [API 3+]
+ getTitle(): String  [API 1+]
+ getUrl(): String  [API 1+]
```

## WebIconDatabase — declaration; API 1+, deprecated in 18 (6 +, 0 *, 0 ~, 2 -)

```
- WebIconDatabase()  [API 22+, deprecated in 22]
+ close(): void  [API 1+]
- getInstance(): WebIconDatabase [static]  [API 1+]
+ open(String): void  [API 1+]
+ releaseIconForPageUrl(String): void  [API 1+]
+ removeAllIcons(): void  [API 1+]
+ requestIconForPageUrl(String, WebIconDatabase.IconListener): void  [API 1+]
+ retainIconForPageUrl(String): void  [API 1+]
```

## WebIconDatabase.IconListener — declaration; API 1+, deprecated in 18 (1 +, 0 *, 0 ~, 0 -)

```
+ onReceivedIcon(String, Bitmap): void  [API 1+]
```

## WebMessage — declaration; API 23+ (0 +, 4 *, 0 ~, 0 -)

```
* WebMessage(String)  [API 23+]
* WebMessage(String, WebMessagePort[])  [API 23+]
* getData(): String  [API 23+]
* getPorts(): WebMessagePort[]  [API 23+]
```

## WebMessagePort — declaration; API 23+ (4 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 23+]
+ postMessage(WebMessage): void  [API 23+]
+ setWebMessageCallback(WebMessagePort.WebMessageCallback): void  [API 23+]
+ setWebMessageCallback(WebMessagePort.WebMessageCallback, Handler): void  [API 23+]
```

## WebMessagePort.WebMessageCallback — declaration; API 23+ (2 +, 0 *, 0 ~, 0 -)

```
+ WebMessageCallback()  [API 23+]
+ onMessage(WebMessagePort, WebMessage): void  [API 23+]
```

## WebResourceError — declaration; API 23+ (2 +, 0 *, 0 ~, 0 -)

```
+ getDescription(): CharSequence  [API 23+]
+ getErrorCode(): int  [API 23+]
```

## WebResourceRequest — declaration; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
+ getMethod(): String  [API 21+]
+ getRequestHeaders(): Map  [API 21+]
+ getUrl(): Uri  [API 21+]
+ hasGesture(): boolean  [API 21+]
+ isForMainFrame(): boolean  [API 21+]
+ isRedirect(): boolean  [API 24+]
```

## WebResourceResponse — declaration; API 11+ (0 +, 12 *, 0 ~, 1 -)

```
* WebResourceResponse(String, String, InputStream)  [API 11+]
* WebResourceResponse(String, String, int, String, Map, InputStream)  [API 21+]
* getData(): InputStream  [API 11+]
* getEncoding(): String  [API 11+]
* getMimeType(): String  [API 11+]
* getReasonPhrase(): String  [API 21+]
* getResponseHeaders(): Map  [API 21+]
* getStatusCode(): int  [API 21+]
* setData(InputStream): void  [API 11+]
* setEncoding(String): void  [API 11+]
* setMimeType(String): void  [API 11+]
* setResponseHeaders(Map): void  [API 21+]
- setStatusCodeAndReasonPhrase(int, String): void  [API 21+]
```

## WebSettings — framework; API 1+ (102 +, 0 *, 13 ~, 0 -)

```
+ WebSettings()  [API 22+]
+ enableSmoothTransition(): boolean  [API 11+, deprecated in 17]
+ getAllowContentAccess(): boolean  [API 11+]
+ getAllowFileAccess(): boolean  [API 3+]
+ getAllowFileAccessFromFileURLs(): boolean  [API 16+]
+ getAllowUniversalAccessFromFileURLs(): boolean  [API 16+]
+ getBlockNetworkImage(): boolean  [API 1+]
+ getBlockNetworkLoads(): boolean  [API 8+]
+ getBuiltInZoomControls(): boolean  [API 3+]
+ getCacheMode(): int  [API 1+]
+ getCursiveFontFamily(): String  [API 1+]
+ getDatabaseEnabled(): boolean  [API 5+, deprecated in 35]
+ getDatabasePath(): String  [API 5+, deprecated in 19]
+ getDefaultFixedFontSize(): int  [API 1+]
+ getDefaultFontSize(): int  [API 1+]
+ getDefaultTextEncodingName(): String  [API 1+]
~ getDefaultUserAgent(Context): String [static]  [API 17+]
+ getDefaultZoom(): WebSettings.ZoomDensity  [API 7+, deprecated in 19]
+ getDisabledActionModeMenuItems(): int  [API 24+]
+ getDisplayZoomControls(): boolean  [API 11+]
+ getDomStorageEnabled(): boolean  [API 7+]
+ getFantasyFontFamily(): String  [API 1+]
+ getFixedFontFamily(): String  [API 1+]
+ getForceDark(): int  [API 29+, deprecated in 33]
+ getJavaScriptCanOpenWindowsAutomatically(): boolean  [API 1+]
+ getJavaScriptEnabled(): boolean  [API 1+]
+ getLayoutAlgorithm(): WebSettings.LayoutAlgorithm  [API 1+]
+ getLightTouchEnabled(): boolean  [API 1+, deprecated in 18]
+ getLoadWithOverviewMode(): boolean  [API 7+]
+ getLoadsImagesAutomatically(): boolean  [API 1+]
+ getMediaPlaybackRequiresUserGesture(): boolean  [API 17+]
+ getMinimumFontSize(): int  [API 1+]
+ getMinimumLogicalFontSize(): int  [API 1+]
+ getMixedContentMode(): int  [API 21+]
+ getNavDump(): boolean  [API 1-16, removed in 17, deprecated in 15]
+ getOffscreenPreRaster(): boolean  [API 23+]
+ getPluginState(): WebSettings.PluginState  [API 8+, deprecated in 18]
+ getPluginsEnabled(): boolean  [API 1-17, removed in 18, deprecated in 15]
~ getPluginsPath(): String  [API 1-17, removed in 18, deprecated in 15]
+ getSafeBrowsingEnabled(): boolean  [API 26+]
+ getSansSerifFontFamily(): String  [API 1+]
+ getSaveFormData(): boolean  [API 1+, deprecated in 26]
+ getSavePassword(): boolean  [API 1+, deprecated in 18]
+ getSerifFontFamily(): String  [API 1+]
+ getStandardFontFamily(): String  [API 1+]
+ getTextSize(): WebSettings.TextSize  [API 1+, deprecated in 15]
+ getTextZoom(): int  [API 14+]
~ getUseDoubleTree(): boolean  [API 1-16, removed in 17, deprecated in 15]
~ getUseWebViewBackgroundForOverscrollBackground(): boolean  [API 9-16, removed in 17, deprecated in 15]
+ getUseWideViewPort(): boolean  [API 1+]
~ getUserAgent(): int  [API 1-16, removed in 17, deprecated in 15]
+ getUserAgentString(): String  [API 3+]
+ isAlgorithmicDarkeningAllowed(): boolean  [API 33+]
+ setAlgorithmicDarkeningAllowed(boolean): void  [API 33+]
+ setAllowContentAccess(boolean): void  [API 11+]
+ setAllowFileAccess(boolean): void  [API 3+]
+ setAllowFileAccessFromFileURLs(boolean): void  [API 16+, deprecated in 30]
+ setAllowUniversalAccessFromFileURLs(boolean): void  [API 16+, deprecated in 30]
+ setAppCacheEnabled(boolean): void  [API 7-32, removed in 33, deprecated in 30]
~ setAppCacheMaxSize(long): void  [API 7-32, removed in 33, deprecated in 18]
~ setAppCachePath(String): void  [API 7-32, removed in 33, deprecated in 30]
+ setBlockNetworkImage(boolean): void  [API 1+]
+ setBlockNetworkLoads(boolean): void  [API 8+]
+ setBuiltInZoomControls(boolean): void  [API 3+]
+ setCacheMode(int): void  [API 1+]
+ setCursiveFontFamily(String): void  [API 1+]
+ setDatabaseEnabled(boolean): void  [API 5+, deprecated in 35]
+ setDatabasePath(String): void  [API 5+, deprecated in 19]
+ setDefaultFixedFontSize(int): void  [API 1+]
+ setDefaultFontSize(int): void  [API 1+]
+ setDefaultTextEncodingName(String): void  [API 1+]
+ setDefaultZoom(WebSettings.ZoomDensity): void  [API 7+, deprecated in 19]
+ setDisabledActionModeMenuItems(int): void  [API 24+]
+ setDisplayZoomControls(boolean): void  [API 11+]
+ setDomStorageEnabled(boolean): void  [API 7+]
+ setEnableSmoothTransition(boolean): void  [API 11+, deprecated in 17]
+ setFantasyFontFamily(String): void  [API 1+]
+ setFixedFontFamily(String): void  [API 1+]
+ setForceDark(int): void  [API 29+, deprecated in 33]
~ setGeolocationDatabasePath(String): void  [API 5+, deprecated in 24]
+ setGeolocationEnabled(boolean): void  [API 5+]
+ setJavaScriptCanOpenWindowsAutomatically(boolean): void  [API 1+]
+ setJavaScriptEnabled(boolean): void  [API 1+]
+ setLayoutAlgorithm(WebSettings.LayoutAlgorithm): void  [API 1+]
+ setLightTouchEnabled(boolean): void  [API 1+, deprecated in 18]
+ setLoadWithOverviewMode(boolean): void  [API 7+]
+ setLoadsImagesAutomatically(boolean): void  [API 1+]
+ setMediaPlaybackRequiresUserGesture(boolean): void  [API 17+]
+ setMinimumFontSize(int): void  [API 1+]
+ setMinimumLogicalFontSize(int): void  [API 1+]
+ setMixedContentMode(int): void  [API 21+]
+ setNavDump(boolean): void  [API 1-16, removed in 17, deprecated in 15]
+ setNeedInitialFocus(boolean): void  [API 1+]
+ setOffscreenPreRaster(boolean): void  [API 23+]
+ setPluginState(WebSettings.PluginState): void  [API 8+, deprecated in 18]
+ setPluginsEnabled(boolean): void  [API 1-17, removed in 18, deprecated in 15]
~ setPluginsPath(String): void  [API 1-17, removed in 18, deprecated in 15]
~ setRenderPriority(WebSettings.RenderPriority): void  [API 1+, deprecated in 18]
+ setSafeBrowsingEnabled(boolean): void  [API 26+]
+ setSansSerifFontFamily(String): void  [API 1+]
+ setSaveFormData(boolean): void  [API 1+, deprecated in 26]
+ setSavePassword(boolean): void  [API 1+, deprecated in 18]
+ setSerifFontFamily(String): void  [API 1+]
+ setStandardFontFamily(String): void  [API 1+]
+ setSupportMultipleWindows(boolean): void  [API 1+]
+ setSupportZoom(boolean): void  [API 1+]
+ setTextSize(WebSettings.TextSize): void  [API 1+, deprecated in 15]
+ setTextZoom(int): void  [API 14+]
~ setUseDoubleTree(boolean): void  [API 1-16, removed in 17, deprecated in 15]
~ setUseWebViewBackgroundForOverscrollBackground(boolean): void  [API 9-16, removed in 17, deprecated in 15]
+ setUseWideViewPort(boolean): void  [API 1+]
~ setUserAgent(int): void  [API 1-16, removed in 17, deprecated in 15]
+ setUserAgentString(String): void  [API 3+]
+ supportMultipleWindows(): boolean  [API 1+]
+ supportZoom(): boolean  [API 1+]
```

## WebSettings.LayoutAlgorithm — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NARROW_COLUMNS [field]  [API 1+, deprecated in 29]
+ NORMAL [field]  [API 1+]
+ SINGLE_COLUMN [field]  [API 1+, deprecated in 29]
+ TEXT_AUTOSIZING [field]  [API 19+]
+ valueOf(String): WebSettings.LayoutAlgorithm [static]  [API 1+]
+ values(): WebSettings.LayoutAlgorithm[] [static]  [API 1+]
```

## WebSettings.PluginState — framework; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ OFF [field]  [API 8+]
+ ON [field]  [API 8+]
+ ON_DEMAND [field]  [API 8+]
+ valueOf(String): WebSettings.PluginState [static]  [API 8+]
+ values(): WebSettings.PluginState[] [static]  [API 8+]
```

## WebSettings.RenderPriority — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ HIGH [field]  [API 1+]
+ LOW [field]  [API 1+]
+ NORMAL [field]  [API 1+]
+ valueOf(String): WebSettings.RenderPriority [static]  [API 1+]
+ values(): WebSettings.RenderPriority[] [static]  [API 1+]
```

## WebSettings.TextSize — framework; API 1+, deprecated in 15 (2 +, 0 *, 0 ~, 0 -)

```
+ LARGER [field]  [API 1+]
+ LARGEST [field]  [API 1+]
+ NORMAL [field]  [API 1+]
+ SMALLER [field]  [API 1+]
+ SMALLEST [field]  [API 1+]
+ valueOf(String): WebSettings.TextSize [static]  [API 1+]
+ values(): WebSettings.TextSize[] [static]  [API 1+]
```

## WebSettings.ZoomDensity — framework; API 7+ (2 +, 0 *, 0 ~, 0 -)

```
+ CLOSE [field]  [API 7+]
+ FAR [field]  [API 7+]
+ MEDIUM [field]  [API 7+]
+ valueOf(String): WebSettings.ZoomDensity [static]  [API 7+]
+ values(): WebSettings.ZoomDensity[] [static]  [API 7+]
```

## WebStorage — declaration; API 5+ (0 +, 0 *, 0 ~, 8 -)

```
- WebStorage()  [API 5-15, removed in 16]
- deleteAllData(): void  [API 7+]
- deleteOrigin(String): void  [API 7+]
- getInstance(): WebStorage [static]  [API 7+]
- getOrigins(ValueCallback): void  [API 7+]
- getQuotaForOrigin(String, ValueCallback): void  [API 7+]
- getUsageForOrigin(String, ValueCallback): void  [API 7+]
- setQuotaForOrigin(String, long): void  [API 7+, deprecated in 18]
```

## WebStorage.Origin — declaration; API 11+ (0 +, 0 *, 0 ~, 3 -)

```
- getOrigin(): String  [API 11+]
- getQuota(): long  [API 11+]
- getUsage(): long  [API 11+]
```

## WebStorage.QuotaUpdater — declaration; API 5+, deprecated in 19 (1 +, 0 *, 0 ~, 0 -)

```
+ updateQuota(long): void  [API 5+]
```

## WebView — framework; API 1+ (67 +, 0 *, 20 ~, 90 -)

```
+ WebView(Context)  [API 1+]
+ WebView(Context, AttributeSet)  [API 1+]
+ WebView(Context, AttributeSet, int)  [API 1+]
- WebView(Context, AttributeSet, int, boolean)  [API 11+, deprecated in 17]
- WebView(Context, AttributeSet, int, int)  [API 21+]
+ addJavascriptInterface(Object, String): void  [API 1+]
~ autofill(SparseArray): void  [API 1+]
~ canGoBack(): boolean  [API 1+]
- canGoBackOrForward(int): boolean  [API 1+]
~ canGoForward(): boolean  [API 1+]
- canZoomIn(): boolean  [API 11+, deprecated in 17]
- canZoomOut(): boolean  [API 11+, deprecated in 17]
- capturePicture(): Picture  [API 1+, deprecated in 19]
~ clearCache(boolean): void  [API 1+]
- clearClientCertPreferences(Runnable): void [static]  [API 21+]
~ clearFormData(): void  [API 1+]
~ clearHistory(): void  [API 1+]
- clearMatches(): void  [API 3+]
- clearSslPreferences(): void  [API 1+]
- clearView(): void  [API 1+, deprecated in 18]
+ computeHorizontalScrollOffset(): int  [API 1+]
+ computeHorizontalScrollRange(): int  [API 1+]
~ computeScroll(): void  [API 1+]
+ computeVerticalScrollExtent(): int  [API 1+]
+ computeVerticalScrollOffset(): int  [API 1+]
+ computeVerticalScrollRange(): int  [API 1+]
- copyBackForwardList(): WebBackForwardList  [API 1+]
- createPrintDocumentAdapter(): PrintDocumentAdapter  [API 19+, deprecated in 21]
- createPrintDocumentAdapter(String): PrintDocumentAdapter  [API 21+]
- createWebMessageChannel(): WebMessagePort[]  [API 23+]
- debugDump(): void  [API 1-16, removed in 17, deprecated in 15]
+ destroy(): void  [API 1+]
- disablePlatformNotifications(): void  [API 1-16, removed in 17, deprecated in 15]
- disableWebView(): void [static]  [API 28+]
+ dispatchCreateViewTranslationRequest(Map, int[], TranslationCapability, List): void  [API 1+]
+ dispatchDraw(Canvas): void  [API 1+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 1+]
- documentHasImages(Message): void  [API 1+]
- emulateShiftHeld(): void  [API 8-16, removed in 17, deprecated in 15]
- enablePlatformNotifications(): void  [API 1-16, removed in 17, deprecated in 15]
- enableSlowWholeDocumentDraw(): void [static]  [API 21+]
+ evaluateJavascript(String, ValueCallback): void  [API 19+]
- findAddress(String): String [static]  [API 1+, deprecated in 28]
- findAll(String): int  [API 3+, deprecated in 16]
- findAllAsync(String): void  [API 16+]
+ findFocus(): View  [API 1+]
- findNext(boolean): void  [API 3+]
- flingScroll(int, int): void  [API 1+]
- freeMemory(): void  [API 7+, deprecated in 19]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAccessibilityNodeProvider(): AccessibilityNodeProvider  [API 1+]
- getCertificate(): SslCertificate  [API 1+]
- getContentHeight(): int  [API 1+]
- getCurrentWebViewPackage(): PackageInfo [static]  [API 26+]
- getFavicon(): Bitmap  [API 1+]
+ getHandler(): Handler  [API 1+]
- getHitTestResult(): WebView.HitTestResult  [API 1+]
- getHttpAuthUsernamePassword(String, String): String[]  [API 1+, deprecated in 26]
+ getOriginalUrl(): String  [API 3+]
- getPluginList(): PluginList  [API 1-11, removed in 12]
~ getProgress(): int  [API 1+]
- getRendererPriorityWaivedWhenNotVisible(): boolean  [API 26+]
- getRendererRequestedPriority(): int  [API 26+]
- getSafeBrowsingPrivacyPolicyUrl(): Uri [static]  [API 27+]
- getScale(): float  [API 1+, deprecated in 17]
+ getSettings(): WebSettings  [API 1+]
- getTextClassifier(): TextClassifier  [API 27+]
~ getTitle(): String  [API 1+]
+ getUrl(): String  [API 1+]
- getVisibleTitleHeight(): int  [API 11-16, removed in 17, deprecated in 15]
- getWebChromeClient(): WebChromeClient  [API 26+]
- getWebViewClassLoader(): ClassLoader [static]  [API 28+]
- getWebViewClient(): WebViewClient  [API 26+]
- getWebViewLooper(): Looper  [API 28+]
- getWebViewRenderProcess(): WebViewRenderProcess  [API 29+]
- getWebViewRenderProcessClient(): WebViewRenderProcessClient  [API 29+]
- getZoomControls(): View  [API 1-15, removed in 16, deprecated in 15]
~ goBack(): void  [API 1+]
- goBackOrForward(int): void  [API 1+]
~ goForward(): void  [API 1+]
- invokeZoomPicker(): void  [API 1+]
- isPrivateBrowsingEnabled(): boolean  [API 11+]
+ isVisibleToUserForAutofill(int): boolean  [API 1+]
+ loadData(String, String, String): void  [API 1+]
+ loadDataWithBaseURL(String, String, String, String, String): void  [API 1+]
+ loadUrl(String): void  [API 1+]
+ loadUrl(String, Map): void  [API 8+]
+ onApplyWindowInsets(WindowInsets): WindowInsets  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onCheckIsTextEditor(): boolean  [API 1+]
- onChildViewAdded(View, View): void  [API 1+]
- onChildViewRemoved(View, View): void  [API 1+]
+ onConfigurationChanged(Configuration): void  [API 1+]
+ onCreateInputConnection(EditorInfo): InputConnection  [API 1+]
+ onCreateVirtualViewTranslationRequests(long[], int[], Consumer): void  [API 1+]
+ onDragEvent(DragEvent): boolean  [API 1+]
+ onDraw(Canvas): void  [API 1+]
+ onFinishTemporaryDetach(): void  [API 1+]
+ onFocusChanged(boolean, int, Rect): void  [API 1+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 1+]
- onGlobalFocusChanged(View, View): void  [API 1+]
+ onHoverEvent(MotionEvent): boolean  [API 1+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onOverScrolled(int, int, boolean, boolean): void  [API 1+]
+ onPause(): void  [API 11+]
+ onProvideAutofillVirtualStructure(ViewStructure, int): void  [API 1+]
+ onProvideContentCaptureStructure(ViewStructure, int): void  [API 1+]
+ onProvideVirtualStructure(ViewStructure): void  [API 1+]
+ onResolvePointerIcon(MotionEvent, int): PointerIcon  [API 1+]
+ onResume(): void  [API 11+]
+ onScrollChanged(int, int, int, int): void  [API 1+]
+ onSizeChanged(int, int, int, int): void  [API 1+]
+ onStartTemporaryDetach(): void  [API 1+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ onTrackballEvent(MotionEvent): boolean  [API 1+]
+ onVirtualViewTranslationResponses(LongSparseArray): void  [API 1+]
+ onVisibilityChanged(View, int): void  [API 1+]
+ onWindowFocusChanged(boolean): void  [API 1+]
+ onWindowVisibilityChanged(int): void  [API 1+]
- overlayHorizontalScrollbar(): boolean  [API 1+, deprecated in 23]
- overlayVerticalScrollbar(): boolean  [API 1+, deprecated in 23]
- pageDown(boolean): boolean  [API 1+]
- pageUp(boolean): boolean  [API 1+]
~ pauseTimers(): void  [API 1+]
+ performLongClick(): boolean  [API 1+]
- postUrl(String, byte[]): void  [API 5+]
- postVisualStateCallback(long, WebView.VisualStateCallback): void  [API 23+]
- postWebMessage(WebMessage, Uri): void  [API 23+]
- refreshPlugins(boolean): void  [API 1-11, removed in 12]
+ reload(): void  [API 1+]
+ removeJavascriptInterface(String): void  [API 11+]
~ requestChildRectangleOnScreen(View, Rect, boolean): boolean  [API 1+]
+ requestFocus(int, Rect): boolean  [API 1+]
- requestFocusNodeHref(Message): void  [API 1+]
- requestImageRef(Message): void  [API 1+]
- restorePicture(Bundle, File): boolean  [API 3-16, removed in 17, deprecated in 15]
- restoreState(Bundle): WebBackForwardList  [API 1+]
~ resumeTimers(): void  [API 1+]
- savePassword(String, String, String): void  [API 1+, deprecated in 18]
- savePicture(Bundle, File): boolean  [API 3-16, removed in 17, deprecated in 15]
- saveState(Bundle): WebBackForwardList  [API 1+]
- saveWebArchive(String): void  [API 11+]
- saveWebArchive(String, boolean, ValueCallback): void  [API 11+]
+ setBackgroundColor(int): void  [API 1+]
- setCertificate(SslCertificate): void  [API 1+, deprecated in 17]
- setDataDirectorySuffix(String): void [static]  [API 28+]
- setDownloadListener(DownloadListener): void  [API 1+]
- setFindListener(WebView.FindListener): void  [API 16+]
- setHorizontalScrollbarOverlay(boolean): void  [API 1+, deprecated in 23]
- setHttpAuthUsernamePassword(String, String, String, String): void  [API 1+, deprecated in 26]
~ setInitialScale(int): void  [API 1+]
~ setLayerType(int, Paint): void  [API 1+]
+ setLayoutParams(ViewGroup.LayoutParams): void  [API 1+]
- setMapTrackballToArrowKeys(boolean): void  [API 1+, deprecated in 17]
- setNetworkAvailable(boolean): void  [API 3+]
+ setOverScrollMode(int): void  [API 1+]
- setPictureListener(WebView.PictureListener): void  [API 1+, deprecated in 15]
- setRendererPriorityPolicy(int, boolean): void  [API 26+]
- setSafeBrowsingWhitelist(List, ValueCallback): void [static]  [API 27+]
~ setScrollBarStyle(int): void  [API 1+]
- setTextClassifier(TextClassifier): void  [API 27+]
- setVerticalScrollbarOverlay(boolean): void  [API 1+, deprecated in 23]
+ setWebChromeClient(WebChromeClient): void  [API 1+]
~ setWebContentsDebuggingEnabled(boolean): void [static]  [API 19+]
+ setWebViewClient(WebViewClient): void  [API 1+]
- setWebViewRenderProcessClient(Executor, WebViewRenderProcessClient): void  [API 29+]
- setWebViewRenderProcessClient(WebViewRenderProcessClient): void  [API 29+]
~ shouldDelayChildPressedState(): boolean  [API 1+]
- showFindDialog(String, boolean): boolean  [API 11+, deprecated in 18]
- startSafeBrowsing(Context, ValueCallback): void [static]  [API 27+]
~ stopLoading(): void  [API 1+]
- zoomBy(float): void  [API 21+]
- zoomIn(): boolean  [API 1+]
- zoomOut(): boolean  [API 1+]
```

## WebView.FindListener — declaration; API 16+ (1 +, 0 *, 0 ~, 0 -)

```
+ onFindResultReceived(int, int, boolean): void  [API 16+]
```

## WebView.HitTestResult — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- getExtra(): String  [API 1+]
- getType(): int  [API 1+]
```

## WebView.PictureListener — declaration; API 1+, deprecated in 12 (1 +, 0 *, 0 ~, 0 -)

```
+ onNewPicture(WebView, Picture): void  [API 1+]
```

## WebView.VisualStateCallback — declaration; API 23+ (2 +, 0 *, 0 ~, 0 -)

```
+ VisualStateCallback()  [API 23+]
+ onComplete(long): void  [API 23+]
```

## WebView.WebViewTransport — declaration; API 1+ (0 +, 2 *, 0 ~, 1 -)

```
- WebViewTransport(WebView)  [API 1+]
* getWebView(): WebView  [API 1+]
* setWebView(WebView): void  [API 1+]
```

## WebViewClient — framework; API 1+ (6 +, 0 *, 2 ~, 17 -)

```
+ WebViewClient()  [API 1+]
~ doUpdateVisitedHistory(WebView, String, boolean): void  [API 1+]
- onFormResubmission(WebView, Message, Message): void  [API 1+]
+ onLoadResource(WebView, String): void  [API 1+]
- onPageCommitVisible(WebView, String): void  [API 23+]
+ onPageFinished(WebView, String): void  [API 1+]
+ onPageStarted(WebView, String, Bitmap): void  [API 1+]
- onReceivedClientCertRequest(WebView, ClientCertRequest): void  [API 21+]
- onReceivedError(WebView, WebResourceRequest, WebResourceError): void  [API 23+]
+ onReceivedError(WebView, int, String, String): void  [API 1+, deprecated in 23]
- onReceivedHttpAuthRequest(WebView, HttpAuthHandler, String, String): void  [API 1+]
- onReceivedHttpError(WebView, WebResourceRequest, WebResourceResponse): void  [API 23+]
- onReceivedLoginRequest(WebView, String, String, String): void  [API 12+]
- onReceivedSslError(WebView, SslErrorHandler, SslError): void  [API 8+]
- onRenderProcessGone(WebView, RenderProcessGoneDetail): boolean  [API 26+]
- onSafeBrowsingHit(WebView, WebResourceRequest, int, SafeBrowsingResponse): void  [API 27+]
+ onScaleChanged(WebView, float, float): void  [API 1+]
- onTooManyRedirects(WebView, Message, Message): void  [API 1+, deprecated in 15]
- onUnhandledInputEvent(WebView, InputEvent): void  [API 21-23, removed in 24]
- onUnhandledKeyEvent(WebView, KeyEvent): void  [API 1+]
- shouldInterceptRequest(WebView, String): WebResourceResponse  [API 11+, deprecated in 21]
- shouldInterceptRequest(WebView, WebResourceRequest): WebResourceResponse  [API 21+]
- shouldOverrideKeyEvent(WebView, KeyEvent): boolean  [API 1+]
~ shouldOverrideUrlLoading(WebView, String): boolean  [API 1+, deprecated in 24]
- shouldOverrideUrlLoading(WebView, WebResourceRequest): boolean  [API 24+]
```

## WebViewDatabase — declaration; API 1+ (8 +, 0 *, 0 ~, 2 -)

```
- WebViewDatabase()  [API 22+, deprecated in 29]
+ clearFormData(): void  [API 1+, deprecated in 26]
+ clearHttpAuthUsernamePassword(): void  [API 1+]
+ clearUsernamePassword(): void  [API 1+, deprecated in 18]
+ getHttpAuthUsernamePassword(String, String): String[]  [API 26+]
- getInstance(Context): WebViewDatabase [static]  [API 1+]
+ hasFormData(): boolean  [API 1+, deprecated in 26]
+ hasHttpAuthUsernamePassword(): boolean  [API 1+]
+ hasUsernamePassword(): boolean  [API 1+, deprecated in 18]
+ setHttpAuthUsernamePassword(String, String, String, String): void  [API 26+]
```

## WebViewFragment — declaration; API 11+, deprecated in 28 (0 +, 0 *, 0 ~, 7 -)

```
- WebViewFragment()  [API 11+]
- getWebView(): WebView  [API 11+]
- onCreateView(LayoutInflater, ViewGroup, Bundle): View  [API 11+]
- onDestroy(): void  [API 11+]
- onDestroyView(): void  [API 11+]
- onPause(): void  [API 11+]
- onResume(): void  [API 11+]
```

## WebViewRenderProcess — declaration; API 29+ (2 +, 0 *, 0 ~, 0 -)

```
+ WebViewRenderProcess()  [API 29+]
+ terminate(): boolean  [API 29+]
```

## WebViewRenderProcessClient — declaration; API 29+ (3 +, 0 *, 0 ~, 0 -)

```
+ WebViewRenderProcessClient()  [API 29+]
+ onRenderProcessResponsive(WebView, WebViewRenderProcess): void  [API 29+]
+ onRenderProcessUnresponsive(WebView, WebViewRenderProcess): void  [API 29+]
```


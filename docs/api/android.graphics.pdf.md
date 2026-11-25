# android.graphics.pdf

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## LoadParams — declaration; API 35+ (0 +, 1 *, 0 ~, 0 -)

```
* getPassword(): String  [API 35+]
```

## LoadParams.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): LoadParams  [API 35+]
* setPassword(String): LoadParams.Builder  [API 35+]
```

## PdfDocument — declaration; API 19+ (0 +, 0 *, 0 ~, 7 -)

```
- PdfDocument()  [API 19+]
- close(): void  [API 19+]
- finalize(): void  [API 19+]
- finishPage(PdfDocument.Page): void  [API 19+]
- getPages(): List  [API 19+]
- startPage(PdfDocument.PageInfo): PdfDocument.Page  [API 19+]
- writeTo(OutputStream): void  [API 19+]
```

## PdfDocument.Page — declaration; API 19+ (0 +, 0 *, 0 ~, 2 -)

```
- getCanvas(): Canvas  [API 19+]
- getInfo(): PdfDocument.PageInfo  [API 19+]
```

## PdfDocument.PageInfo — declaration; API 19+ (0 +, 0 *, 0 ~, 4 -)

```
- getContentRect(): Rect  [API 19+]
- getPageHeight(): int  [API 19+]
- getPageNumber(): int  [API 19+]
- getPageWidth(): int  [API 19+]
```

## PdfDocument.PageInfo.Builder — declaration; API 19+ (0 +, 0 *, 0 ~, 3 -)

```
- Builder(int, int, int)  [API 19+]
- create(): PdfDocument.PageInfo  [API 19+]
- setContentRect(Rect): PdfDocument.PageInfo.Builder  [API 19+]
```

## PdfRenderer — declaration; API 21+ (0 +, 0 *, 0 ~, 10 -)

```
- PdfRenderer(ParcelFileDescriptor)  [API 21+]
- PdfRenderer(ParcelFileDescriptor, LoadParams)  [API 35+]
- close(): void  [API 21+]
- finalize(): void  [API 21+]
- getDocumentLinearizationType(): int  [API 35+]
- getPageCount(): int  [API 21+]
- getPdfFormType(): int  [API 35+]
- openPage(int): PdfRenderer.Page  [API 21+]
- shouldScaleForPrinting(): boolean  [API 21+]
- write(ParcelFileDescriptor, boolean): void  [API 35+]
```

## PdfRenderer.Page — declaration; API 21+ (0 +, 0 *, 0 ~, 18 -)

```
- applyEdit(FormEditRecord): List  [API 35+]
- close(): void  [API 21+]
- finalize(): void  [API 21+]
- getFormWidgetInfoAtIndex(int): FormWidgetInfo  [API 35+]
- getFormWidgetInfoAtPosition(int, int): FormWidgetInfo  [API 35+]
- getFormWidgetInfos(): List  [API 35+]
- getFormWidgetInfos(int[]): List  [API 35+]
- getGotoLinks(): List  [API 35+]
- getHeight(): int  [API 21+]
- getImageContents(): List  [API 35+]
- getIndex(): int  [API 21+]
- getLinkContents(): List  [API 35+]
- getTextContents(): List  [API 35+]
- getWidth(): int  [API 21+]
- render(Bitmap, Rect, Matrix, RenderParams): void  [API 35+]
- render(Bitmap, Rect, Matrix, int): void  [API 21+]
- searchText(String): List  [API 35+]
- selectContent(SelectionBoundary, SelectionBoundary): PageSelection  [API 35+]
```

## PdfRendererPreV — declaration; API 35+ (0 +, 0 *, 0 ~, 9 -)

```
- PdfRendererPreV(ParcelFileDescriptor)  [API 35+]
- PdfRendererPreV(ParcelFileDescriptor, LoadParams)  [API 35+]
- close(): void  [API 35+]
- finalize(): void  [API 35+]
- getDocumentLinearizationType(): int  [API 35+]
- getPageCount(): int  [API 35+]
- getPdfFormType(): int  [API 35+]
- openPage(int): PdfRendererPreV.Page  [API 35+]
- write(ParcelFileDescriptor, boolean): void  [API 35+]
```

## PdfRendererPreV.Page — declaration; API 35+ (0 +, 0 *, 0 ~, 17 -)

```
- applyEdit(FormEditRecord): List  [API 35+]
- close(): void  [API 35+]
- finalize(): void  [API 35+]
- getFormWidgetInfoAtIndex(int): FormWidgetInfo  [API 35+]
- getFormWidgetInfoAtPosition(int, int): FormWidgetInfo  [API 35+]
- getFormWidgetInfos(): List  [API 35+]
- getFormWidgetInfos(int[]): List  [API 35+]
- getGotoLinks(): List  [API 35+]
- getHeight(): int  [API 35+]
- getImageContents(): List  [API 35+]
- getIndex(): int  [API 35+]
- getLinkContents(): List  [API 35+]
- getTextContents(): List  [API 35+]
- getWidth(): int  [API 35+]
- render(Bitmap, Rect, Matrix, RenderParams): void  [API 35+]
- searchText(String): List  [API 35+]
- selectContent(SelectionBoundary, SelectionBoundary): PageSelection  [API 35+]
```

## RenderParams — declaration; API 35+ (0 +, 2 *, 0 ~, 0 -)

```
* getRenderFlags(): int  [API 35+]
* getRenderMode(): int  [API 35+]
```

## RenderParams.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(int)  [API 35+]
* build(): RenderParams  [API 35+]
* setRenderFlags(int): RenderParams.Builder  [API 35+]
- setRenderFlags(int, int): RenderParams.Builder  [API 35+]
```


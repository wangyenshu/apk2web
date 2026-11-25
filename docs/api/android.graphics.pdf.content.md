# android.graphics.pdf.content

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## PdfPageGotoLinkContent — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* PdfPageGotoLinkContent(List, PdfPageGotoLinkContent.Destination)  [API 35+]
* describeContents(): int  [API 35+]
* getBounds(): List  [API 35+]
* getDestination(): PdfPageGotoLinkContent.Destination  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## PdfPageGotoLinkContent.Destination — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* Destination(int, float, float, float)  [API 35+]
* describeContents(): int  [API 35+]
* getPageNumber(): int  [API 35+]
* getXCoordinate(): float  [API 35+]
* getYCoordinate(): float  [API 35+]
* getZoom(): float  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## PdfPageImageContent — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* PdfPageImageContent(String)  [API 35+]
* describeContents(): int  [API 35+]
* getAltText(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## PdfPageLinkContent — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* PdfPageLinkContent(List, Uri)  [API 35+]
* describeContents(): int  [API 35+]
* getBounds(): List  [API 35+]
* getUri(): Uri  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## PdfPageTextContent — declaration; API 35+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* PdfPageTextContent(String)  [API 35+]
* PdfPageTextContent(String, List)  [API 35+]
* describeContents(): int  [API 35+]
* getBounds(): List  [API 35+]
* getText(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```


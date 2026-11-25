# android.graphics.pdf.models.selection

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## PageSelection — declaration; API 35+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 35+]
- PageSelection(int, SelectionBoundary, SelectionBoundary, List)  [API 35+]
- describeContents(): int  [API 35+]
- getPage(): int  [API 35+]
- getSelectedTextContents(): List  [API 35+]
- getStart(): SelectionBoundary  [API 35+]
- getStop(): SelectionBoundary  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## SelectionBoundary — declaration; API 35+ (0 +, 4 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 35+]
* SelectionBoundary(Point)  [API 35+]
* SelectionBoundary(int)  [API 35+]
- describeContents(): int  [API 35+]
* getIndex(): int  [API 35+]
- getIsRtl(): boolean  [API 35+]
* getPoint(): Point  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```


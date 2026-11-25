# android.graphics.pdf.models

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## FormEditRecord — declaration; API 35+ (0 +, 10 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getClickPoint(): Point  [API 35+]
* getPageNumber(): int  [API 35+]
* getSelectedIndices(): int[]  [API 35+]
* getText(): String  [API 35+]
* getType(): int  [API 35+]
* getWidgetIndex(): int  [API 35+]
* hashCode(): int  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## FormEditRecord.Builder — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(int, int, int)  [API 35+]
* build(): FormEditRecord  [API 35+]
* setClickPoint(Point): FormEditRecord.Builder  [API 35+]
* setSelectedIndices(int[]): FormEditRecord.Builder  [API 35+]
* setText(String): FormEditRecord.Builder  [API 35+]
```

## FormWidgetInfo — declaration; API 35+ (0 +, 17 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getAccessibilityLabel(): String  [API 35+]
* getFontSize(): float  [API 35+]
* getListItems(): List  [API 35+]
* getMaxLength(): int  [API 35+]
* getTextValue(): String  [API 35+]
* getWidgetIndex(): int  [API 35+]
* getWidgetRect(): Rect  [API 35+]
* getWidgetType(): int  [API 35+]
* hashCode(): int  [API 35+]
* isEditableText(): boolean  [API 35+]
* isMultiLineText(): boolean  [API 35+]
* isMultiSelect(): boolean  [API 35+]
* isReadOnly(): boolean  [API 35+]
* toString(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## FormWidgetInfo.Builder — declaration; API 35+ (0 +, 9 *, 0 ~, 0 -)

```
* Builder(int, int, Rect, String, String)  [API 35+]
* build(): FormWidgetInfo  [API 35+]
* setEditableText(boolean): FormWidgetInfo.Builder  [API 35+]
* setFontSize(float): FormWidgetInfo.Builder  [API 35+]
* setListItems(List): FormWidgetInfo.Builder  [API 35+]
* setMaxLength(int): FormWidgetInfo.Builder  [API 35+]
* setMultiLineText(boolean): FormWidgetInfo.Builder  [API 35+]
* setMultiSelect(boolean): FormWidgetInfo.Builder  [API 35+]
* setReadOnly(boolean): FormWidgetInfo.Builder  [API 35+]
```

## ListItem — declaration; API 35+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* ListItem(String, boolean)  [API 35+]
* describeContents(): int  [API 35+]
* equals(Object): boolean  [API 35+]
* getLabel(): String  [API 35+]
* hashCode(): int  [API 35+]
* isSelected(): boolean  [API 35+]
* toString(): String  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```

## PageMatchBounds — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
* PageMatchBounds(List, int)  [API 35+]
* describeContents(): int  [API 35+]
* getBounds(): List  [API 35+]
* getTextStartIndex(): int  [API 35+]
* writeToParcel(Parcel, int): void  [API 35+]
```


# android.service.controls.templates

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ControlButton — declaration; API 30+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* ControlButton(boolean, CharSequence)  [API 30+]
* describeContents(): int  [API 30+]
* getActionDescription(): CharSequence  [API 30+]
* isChecked(): boolean  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## ControlTemplate — declaration; API 30+ (1 +, 1 *, 0 ~, 2 -)

```
- getErrorTemplate(): ControlTemplate [static]  [API 30+]
- getNoTemplateObject(): ControlTemplate [static]  [API 30+]
* getTemplateId(): String  [API 30+]
+ getTemplateType(): int  [API 30+]
```

## RangeTemplate — declaration; API 30+ (0 +, 6 *, 0 ~, 1 -)

```
* RangeTemplate(String, float, float, float, float, CharSequence)  [API 30+]
* getCurrentValue(): float  [API 30+]
* getFormatString(): CharSequence  [API 30+]
* getMaxValue(): float  [API 30+]
* getMinValue(): float  [API 30+]
* getStepValue(): float  [API 30+]
- getTemplateType(): int  [API 30+]
```

## StatelessTemplate — declaration; API 30+ (0 +, 1 *, 0 ~, 1 -)

```
* StatelessTemplate(String)  [API 30+]
- getTemplateType(): int  [API 30+]
```

## TemperatureControlTemplate — declaration; API 30+ (0 +, 0 *, 0 ~, 6 -)

```
- TemperatureControlTemplate(String, ControlTemplate, int, int, int)  [API 30+]
- getCurrentActiveMode(): int  [API 30+]
- getCurrentMode(): int  [API 30+]
- getModes(): int  [API 30+]
- getTemplate(): ControlTemplate  [API 30+]
- getTemplateType(): int  [API 30+]
```

## ThumbnailTemplate — declaration; API 31+ (0 +, 4 *, 0 ~, 1 -)

```
* ThumbnailTemplate(String, boolean, Icon, CharSequence)  [API 31+]
* getContentDescription(): CharSequence  [API 31+]
- getTemplateType(): int  [API 31+]
* getThumbnail(): Icon  [API 31+]
* isActive(): boolean  [API 31+]
```

## ToggleRangeTemplate — declaration; API 30+ (0 +, 4 *, 0 ~, 2 -)

```
- ToggleRangeTemplate(String, ControlButton, RangeTemplate)  [API 30+]
* ToggleRangeTemplate(String, boolean, CharSequence, RangeTemplate)  [API 30+]
* getActionDescription(): CharSequence  [API 30+]
* getRange(): RangeTemplate  [API 30+]
- getTemplateType(): int  [API 30+]
* isChecked(): boolean  [API 30+]
```

## ToggleTemplate — declaration; API 30+ (0 +, 0 *, 0 ~, 4 -)

```
- ToggleTemplate(String, ControlButton)  [API 30+]
- getContentDescription(): CharSequence  [API 30+]
- getTemplateType(): int  [API 30+]
- isChecked(): boolean  [API 30+]
```


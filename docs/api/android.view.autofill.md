# android.view.autofill

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AutofillId — framework; API 26+ (5 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 26+]
+ create(View, int): AutofillId [static]  [API 34+]
~ describeContents(): int  [API 26+]
+ equals(Object): boolean  [API 26+]
+ hashCode(): int  [API 26+]
+ toString(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## AutofillManager — framework; API 26+ (0 +, 0 *, 0 ~, 31 -)

```
- cancel(): void  [API 26+]
- commit(): void  [API 26+]
- disableAutofillServices(): void  [API 26+]
- getAutofillServiceComponentName(): ComponentName  [API 28+]
- getAvailableFieldClassificationAlgorithms(): List  [API 28+]
- getDefaultFieldClassificationAlgorithm(): String  [API 28+]
- getNextAutofillId(): AutofillId  [API 28+]
- getUserData(): UserData  [API 28+]
- getUserDataId(): String  [API 28+]
- hasEnabledAutofillServices(): boolean  [API 26+]
- isAutofillSupported(): boolean  [API 26+]
- isEnabled(): boolean  [API 26+]
- isFieldClassificationEnabled(): boolean  [API 28+]
- notifyValueChanged(View): void  [API 26+]
- notifyValueChanged(View, int, AutofillValue): void  [API 26+]
- notifyViewClicked(View): void  [API 28+]
- notifyViewClicked(View, int): void  [API 28+]
- notifyViewEntered(View): void  [API 26+]
- notifyViewEntered(View, int, Rect): void  [API 26+]
- notifyViewExited(View): void  [API 26+]
- notifyViewExited(View, int): void  [API 26+]
- notifyViewVisibilityChanged(View, boolean): void  [API 27+]
- notifyViewVisibilityChanged(View, int, boolean): void  [API 27+]
- notifyVirtualViewsReady(View, SparseArray): void  [API 34+]
- registerCallback(AutofillManager.AutofillCallback): void  [API 26+]
- requestAutofill(View): void  [API 26+]
- requestAutofill(View, int, Rect): void  [API 26+]
- setUserData(UserData): void  [API 28+]
- showAutofillDialog(View): boolean  [API 33+]
- showAutofillDialog(View, int): boolean  [API 33+]
- unregisterCallback(AutofillManager.AutofillCallback): void  [API 26+]
```

## AutofillManager.AutofillCallback — framework; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ AutofillCallback()  [API 26+]
+ onAutofillEvent(View, int): void  [API 26+]
+ onAutofillEvent(View, int, int): void  [API 26+]
```

## AutofillValue — framework; API 26+ (16 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 26+]
~ describeContents(): int  [API 26+]
+ equals(Object): boolean  [API 26+]
+ forDate(long): AutofillValue [static]  [API 26+]
+ forList(int): AutofillValue [static]  [API 26+]
+ forText(CharSequence): AutofillValue [static]  [API 26+]
+ forToggle(boolean): AutofillValue [static]  [API 26+]
+ getDateValue(): long  [API 26+]
+ getListValue(): int  [API 26+]
+ getTextValue(): CharSequence  [API 26+]
+ getToggleValue(): boolean  [API 26+]
+ hashCode(): int  [API 26+]
+ isDate(): boolean  [API 26+]
+ isList(): boolean  [API 26+]
+ isText(): boolean  [API 26+]
+ isToggle(): boolean  [API 26+]
+ toString(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## VirtualViewFillInfo — declaration; API 34+ (0 +, 1 *, 0 ~, 0 -)

```
* getAutofillHints(): String[]  [API 34+]
```

## VirtualViewFillInfo.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): VirtualViewFillInfo  [API 34+]
* setAutofillHints(String[]): VirtualViewFillInfo.Builder  [API 34+]
```


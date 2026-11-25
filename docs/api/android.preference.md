# android.preference

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CheckBoxPreference — framework; API 1+, deprecated in 29 (15 +, 0 *, 0 ~, 0 -)

```
+ CheckBoxPreference(Context)  [API 1+]
+ CheckBoxPreference(Context, AttributeSet)  [API 1+]
+ CheckBoxPreference(Context, AttributeSet, int)  [API 1+]
+ CheckBoxPreference(Context, AttributeSet, int, int)  [API 21+]
+ getDisableDependentsState(): boolean  [API 1-13, removed in 14]
+ getSummaryOff(): CharSequence  [API 1-13, removed in 14]
+ getSummaryOn(): CharSequence  [API 1-13, removed in 14]
+ isChecked(): boolean  [API 1-13, removed in 14]
+ onBindView(View): void  [API 1+]
+ setChecked(boolean): void  [API 1-13, removed in 14]
+ setDisableDependentsState(boolean): void  [API 1-13, removed in 14]
+ setSummaryOff(CharSequence): void  [API 1-13, removed in 14]
+ setSummaryOff(int): void  [API 1-13, removed in 14]
+ setSummaryOn(CharSequence): void  [API 1-13, removed in 14]
+ setSummaryOn(int): void  [API 1-13, removed in 14]
```

## DialogPreference — framework; API 1+, deprecated in 29 (33 +, 0 *, 0 ~, 0 -)

```
+ DialogPreference(Context)  [API 21+]
+ DialogPreference(Context, AttributeSet)  [API 1+]
+ DialogPreference(Context, AttributeSet, int)  [API 1+]
+ DialogPreference(Context, AttributeSet, int, int)  [API 21+]
+ getDialog(): Dialog  [API 3+]
+ getDialogIcon(): Drawable  [API 1+]
+ getDialogLayoutResource(): int  [API 1+]
+ getDialogMessage(): CharSequence  [API 1+]
+ getDialogTitle(): CharSequence  [API 1+]
+ getNegativeButtonText(): CharSequence  [API 1+]
+ getPositiveButtonText(): CharSequence  [API 1+]
+ onActivityDestroy(): void  [API 1+]
+ onBindDialogView(View): void  [API 1+]
+ onClick(): void  [API 1+]
+ onClick(DialogInterface, int): void  [API 1+]
+ onCreateDialogView(): View  [API 1+]
+ onDialogClosed(boolean): void  [API 1+]
+ onDismiss(DialogInterface): void  [API 1+]
+ onPrepareDialogBuilder(AlertDialog.Builder): void  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ setDialogIcon(Drawable): void  [API 1+]
+ setDialogIcon(int): void  [API 1+]
+ setDialogLayoutResource(int): void  [API 1+]
+ setDialogMessage(CharSequence): void  [API 1+]
+ setDialogMessage(int): void  [API 1+]
+ setDialogTitle(CharSequence): void  [API 1+]
+ setDialogTitle(int): void  [API 1+]
+ setNegativeButtonText(CharSequence): void  [API 1+]
+ setNegativeButtonText(int): void  [API 1+]
+ setPositiveButtonText(CharSequence): void  [API 1+]
+ setPositiveButtonText(int): void  [API 1+]
+ showDialog(Bundle): void  [API 1+]
```

## EditTextPreference — framework; API 1+, deprecated in 29 (16 +, 0 *, 0 ~, 0 -)

```
+ EditTextPreference(Context)  [API 1+]
+ EditTextPreference(Context, AttributeSet)  [API 1+]
+ EditTextPreference(Context, AttributeSet, int)  [API 1+]
+ EditTextPreference(Context, AttributeSet, int, int)  [API 21+]
+ getEditText(): EditText  [API 1+]
+ getText(): String  [API 1+]
+ onAddEditTextToDialogView(View, EditText): void  [API 1+]
+ onBindDialogView(View): void  [API 1+]
+ onDialogClosed(boolean): void  [API 1+]
+ onGetDefaultValue(TypedArray, int): Object  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ onSetInitialValue(boolean, Object): void  [API 1+]
+ setText(String): void  [API 1+]
+ shouldDisableDependents(): boolean  [API 1+]
+ showDialog(Bundle): void  [API 1+]
```

## ListPreference — framework; API 1+, deprecated in 29 (23 +, 0 *, 0 ~, 0 -)

```
+ ListPreference(Context)  [API 1+]
+ ListPreference(Context, AttributeSet)  [API 1+]
+ ListPreference(Context, AttributeSet, int)  [API 21+]
+ ListPreference(Context, AttributeSet, int, int)  [API 21+]
+ findIndexOfValue(String): int  [API 1+]
+ getEntries(): CharSequence[]  [API 1+]
+ getEntry(): CharSequence  [API 1+]
+ getEntryValues(): CharSequence[]  [API 1+]
+ getSummary(): CharSequence  [API 1+]
+ getValue(): String  [API 1+]
+ onDialogClosed(boolean): void  [API 1+]
+ onGetDefaultValue(TypedArray, int): Object  [API 1+]
+ onPrepareDialogBuilder(AlertDialog.Builder): void  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ onSetInitialValue(boolean, Object): void  [API 1+]
+ setEntries(CharSequence[]): void  [API 1+]
+ setEntries(int): void  [API 1+]
+ setEntryValues(CharSequence[]): void  [API 1+]
+ setEntryValues(int): void  [API 1+]
+ setSummary(CharSequence): void  [API 1+]
+ setValue(String): void  [API 1+]
+ setValueIndex(int): void  [API 1+]
```

## MultiSelectListPreference — framework; API 11+, deprecated in 29 (18 +, 0 *, 0 ~, 0 -)

```
+ MultiSelectListPreference(Context)  [API 11+]
+ MultiSelectListPreference(Context, AttributeSet)  [API 11+]
+ MultiSelectListPreference(Context, AttributeSet, int)  [API 21+]
+ MultiSelectListPreference(Context, AttributeSet, int, int)  [API 21+]
+ findIndexOfValue(String): int  [API 11+]
+ getEntries(): CharSequence[]  [API 11+]
+ getEntryValues(): CharSequence[]  [API 11+]
+ getValues(): Set  [API 11+]
+ onDialogClosed(boolean): void  [API 11+]
+ onGetDefaultValue(TypedArray, int): Object  [API 11+]
+ onPrepareDialogBuilder(AlertDialog.Builder): void  [API 11+]
+ onSaveInstanceState(): Parcelable  [API 11+]
+ onSetInitialValue(boolean, Object): void  [API 11+]
+ setEntries(CharSequence[]): void  [API 11+]
+ setEntries(int): void  [API 11+]
+ setEntryValues(CharSequence[]): void  [API 11+]
+ setEntryValues(int): void  [API 11+]
+ setValues(Set): void  [API 11+]
```

## Preference — framework; API 1+, deprecated in 29 (86 +, 0 *, 8 ~, 0 -)

```
+ Preference(Context)  [API 1+]
+ Preference(Context, AttributeSet)  [API 1+]
+ Preference(Context, AttributeSet, int)  [API 1+]
+ Preference(Context, AttributeSet, int, int)  [API 21+]
+ callChangeListener(Object): boolean  [API 1+]
+ compareTo(Preference): int  [API 1+]
+ findPreferenceInHierarchy(String): Preference  [API 1+]
+ getContext(): Context  [API 1+]
+ getDependency(): String  [API 1+]
+ getEditor(): SharedPreferences.Editor  [API 1+]
+ getExtras(): Bundle  [API 11+]
+ getFragment(): String  [API 11+]
+ getIcon(): Drawable  [API 11+]
+ getIntent(): Intent  [API 1+]
+ getKey(): String  [API 1+]
+ getLayoutResource(): int  [API 1+]
+ getOnPreferenceChangeListener(): Preference.OnPreferenceChangeListener  [API 1+]
+ getOnPreferenceClickListener(): Preference.OnPreferenceClickListener  [API 1+]
+ getOrder(): int  [API 1+]
+ getParent(): PreferenceGroup  [API 26+]
+ getPersistedBoolean(boolean): boolean  [API 1+]
+ getPersistedFloat(float): float  [API 1+]
+ getPersistedInt(int): int  [API 1+]
+ getPersistedLong(long): long  [API 1+]
+ getPersistedString(String): String  [API 1+]
+ getPersistedStringSet(Set): Set  [API 24+]
~ getPreferenceDataStore(): PreferenceDataStore  [API 26+]
+ getPreferenceManager(): PreferenceManager  [API 1+]
+ getSharedPreferences(): SharedPreferences  [API 1+]
+ getShouldDisableView(): boolean  [API 1+]
+ getSummary(): CharSequence  [API 1+]
+ getTitle(): CharSequence  [API 1+]
+ getTitleRes(): int  [API 14+]
+ getView(View, ViewGroup): View  [API 1+]
+ getWidgetLayoutResource(): int  [API 1+]
+ hasKey(): boolean  [API 1+]
+ isEnabled(): boolean  [API 1+]
+ isIconSpaceReserved(): boolean  [API 26+]
+ isPersistent(): boolean  [API 1+]
~ isRecycleEnabled(): boolean  [API 26+]
+ isSelectable(): boolean  [API 1+]
~ isSingleLineTitle(): boolean  [API 26+]
+ notifyChanged(): void  [API 1+]
+ notifyDependencyChange(boolean): void  [API 1+]
+ notifyHierarchyChanged(): void  [API 1+]
+ onAttachedToActivity(): void  [API 1+]
+ onAttachedToHierarchy(PreferenceManager): void  [API 1+]
+ onBindView(View): void  [API 1+]
+ onClick(): void  [API 1+]
+ onCreateView(ViewGroup): View  [API 1+]
+ onDependencyChanged(Preference, boolean): void  [API 1+]
+ onGetDefaultValue(TypedArray, int): Object  [API 1+]
+ onParentChanged(Preference, boolean): void  [API 19+]
+ onPrepareForRemoval(): void  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ onSetInitialValue(boolean, Object): void  [API 1+]
+ peekExtras(): Bundle  [API 11+]
+ persistBoolean(boolean): boolean  [API 1+]
+ persistFloat(float): boolean  [API 1+]
+ persistInt(int): boolean  [API 1+]
+ persistLong(long): boolean  [API 1+]
+ persistString(String): boolean  [API 1+]
+ persistStringSet(Set): boolean  [API 24+]
~ restoreHierarchyState(Bundle): void  [API 1+]
~ saveHierarchyState(Bundle): void  [API 1+]
+ setDefaultValue(Object): void  [API 1+]
+ setDependency(String): void  [API 1+]
+ setEnabled(boolean): void  [API 1+]
+ setFragment(String): void  [API 11+]
+ setIcon(Drawable): void  [API 11+]
+ setIcon(int): void  [API 11+]
+ setIconSpaceReserved(boolean): void  [API 26+]
+ setIntent(Intent): void  [API 1+]
+ setKey(String): void  [API 1+]
+ setLayoutResource(int): void  [API 1+]
+ setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener): void  [API 1+]
+ setOnPreferenceClickListener(Preference.OnPreferenceClickListener): void  [API 1+]
+ setOrder(int): void  [API 1+]
+ setPersistent(boolean): void  [API 1+]
~ setPreferenceDataStore(PreferenceDataStore): void  [API 26+]
~ setRecycleEnabled(boolean): void  [API 26+]
+ setSelectable(boolean): void  [API 1+]
+ setShouldDisableView(boolean): void  [API 1+]
~ setSingleLineTitle(boolean): void  [API 26+]
+ setSummary(CharSequence): void  [API 1+]
+ setSummary(int): void  [API 1+]
+ setTitle(CharSequence): void  [API 1+]
+ setTitle(int): void  [API 1+]
+ setWidgetLayoutResource(int): void  [API 1+]
+ shouldCommit(): boolean  [API 1+]
+ shouldDisableDependents(): boolean  [API 1+]
+ shouldPersist(): boolean  [API 1+]
+ toString(): String  [API 1+]
```

## Preference.BaseSavedState — framework; API 1+, deprecated in 29 (2 +, 0 *, 0 ~, 0 -)

```
+ BaseSavedState(Parcel)  [API 1+]
+ BaseSavedState(Parcelable)  [API 1+]
+ CREATOR [field]  [API 1+]
```

## Preference.OnPreferenceChangeListener — framework; API 1+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onPreferenceChange(Preference, Object): boolean  [API 1+]
```

## Preference.OnPreferenceClickListener — framework; API 1+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onPreferenceClick(Preference): boolean  [API 1+]
```

## PreferenceActivity — framework; API 1+, deprecated in 29 (32 +, 0 *, 7 ~, 3 -)

```
+ PreferenceActivity()  [API 1+]
+ addPreferencesFromIntent(Intent): void  [API 1+, deprecated in 15]
+ addPreferencesFromResource(int): void  [API 1+, deprecated in 15]
+ findPreference(CharSequence): Preference  [API 1+, deprecated in 15]
+ finishPreferencePanel(Fragment, int, Intent): void  [API 11+]
+ getPreferenceManager(): PreferenceManager  [API 1+, deprecated in 15]
+ getPreferenceScreen(): PreferenceScreen  [API 1+, deprecated in 15]
+ hasHeaders(): boolean  [API 11+]
+ invalidateHeaders(): void  [API 11+]
~ isMultiPane(): boolean  [API 11+]
~ isValidFragment(String): boolean  [API 19+]
+ loadHeadersFromResource(int, List): void  [API 11+]
+ onActivityResult(int, int, Intent): void  [API 1+]
+ onBackPressed(): void  [API 1+]
+ onBuildHeaders(List): void  [API 11+]
+ onBuildStartFragmentIntent(String, Bundle, int, int): Intent  [API 14+]
+ onContentChanged(): void  [API 1+]
+ onCreate(Bundle): void  [API 1+]
+ onDestroy(): void  [API 1+]
- onGetInitialHeader(): PreferenceActivity.Header  [API 11+]
- onGetNewHeader(): PreferenceActivity.Header  [API 11+]
+ onHeaderClick(PreferenceActivity.Header, int): void  [API 11+]
+ onIsHidingHeaders(): boolean  [API 11+]
+ onIsMultiPane(): boolean  [API 11+]
+ onListItemClick(ListView, View, int, long): void  [API 1+]
+ onNewIntent(Intent): void  [API 1+]
+ onOptionsItemSelected(MenuItem): boolean  [API 1+]
- onPreferenceStartFragment(PreferenceFragment, Preference): boolean  [API 1+]
+ onPreferenceTreeClick(PreferenceScreen, Preference): boolean  [API 1+, deprecated in 15]
+ onRestoreInstanceState(Bundle): void  [API 1+]
+ onSaveInstanceState(Bundle): void  [API 1+]
+ onStop(): void  [API 1+]
~ setListFooter(View): void  [API 11+]
~ setParentTitle(CharSequence, CharSequence, View.OnClickListener): void  [API 11+]
+ setPreferenceScreen(PreferenceScreen): void  [API 1+, deprecated in 15]
~ showBreadCrumbs(CharSequence, CharSequence): void  [API 11+]
~ startPreferenceFragment(Fragment, boolean): void  [API 11+]
+ startPreferencePanel(String, Bundle, int, CharSequence, Fragment, int): void  [API 11+]
+ startWithFragment(String, Bundle, Fragment, int): void  [API 11+]
+ startWithFragment(String, Bundle, Fragment, int, int, int): void  [API 14+]
+ switchToHeader(PreferenceActivity.Header): void  [API 11+]
~ switchToHeader(String, Bundle): void  [API 11+]
```

## PreferenceActivity.Header — framework; API 11+, deprecated in 29 (5 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 11+]
+ Header()  [API 11+]
+ breadCrumbShortTitle [field]  [API 11+]
+ breadCrumbShortTitleRes [field]  [API 11+]
+ breadCrumbTitle [field]  [API 11+]
+ breadCrumbTitleRes [field]  [API 11+]
- describeContents(): int  [API 11+]
+ extras [field]  [API 11+]
+ fragment [field]  [API 11+]
+ fragmentArguments [field]  [API 11+]
+ getBreadCrumbShortTitle(Resources): CharSequence  [API 11+]
+ getBreadCrumbTitle(Resources): CharSequence  [API 11+]
+ getSummary(Resources): CharSequence  [API 11+]
+ getTitle(Resources): CharSequence  [API 11+]
+ iconRes [field]  [API 11+]
+ id [field]  [API 11+]
+ intent [field]  [API 11+]
- readFromParcel(Parcel): void  [API 11+]
+ summary [field]  [API 11+]
+ summaryRes [field]  [API 11+]
+ title [field]  [API 11+]
+ titleRes [field]  [API 11+]
- writeToParcel(Parcel, int): void  [API 11+]
```

## PreferenceCategory — framework; API 1+, deprecated in 29 (6 +, 0 *, 1 ~, 0 -)

```
+ PreferenceCategory(Context)  [API 1+]
+ PreferenceCategory(Context, AttributeSet)  [API 1+]
+ PreferenceCategory(Context, AttributeSet, int)  [API 1+]
+ PreferenceCategory(Context, AttributeSet, int, int)  [API 21+]
~ isEnabled(): boolean  [API 1+]
+ onPrepareAddPreference(Preference): boolean  [API 1+]
+ shouldDisableDependents(): boolean  [API 1+]
```

## PreferenceDataStore — framework; API 26+, deprecated in 29 (6 +, 0 *, 6 ~, 0 -)

```
+ getBoolean(String, boolean): boolean  [API 26+]
+ getFloat(String, float): float  [API 26+]
+ getInt(String, int): int  [API 26+]
+ getLong(String, long): long  [API 26+]
+ getString(String, String): String  [API 26+]
+ getStringSet(String, Set): Set  [API 26+]
~ putBoolean(String, boolean): void  [API 26+]
~ putFloat(String, float): void  [API 26+]
~ putInt(String, int): void  [API 26+]
~ putLong(String, long): void  [API 26+]
~ putString(String, String): void  [API 26+]
~ putStringSet(String, Set): void  [API 26+]
```

## PreferenceFragment — framework; API 11+, deprecated in 28 (18 +, 0 *, 0 ~, 0 -)

```
+ PreferenceFragment()  [API 11+]
+ addPreferencesFromIntent(Intent): void  [API 11+]
+ addPreferencesFromResource(int): void  [API 11+]
+ findPreference(CharSequence): Preference  [API 11+]
+ getPreferenceManager(): PreferenceManager  [API 11+]
+ getPreferenceScreen(): PreferenceScreen  [API 11+]
+ onActivityCreated(Bundle): void  [API 11+]
+ onActivityResult(int, int, Intent): void  [API 11+]
+ onCreate(Bundle): void  [API 11+]
+ onCreateView(LayoutInflater, ViewGroup, Bundle): View  [API 11+]
+ onDestroy(): void  [API 11+]
+ onDestroyView(): void  [API 11+]
+ onPreferenceTreeClick(PreferenceScreen, Preference): boolean  [API 11+]
+ onSaveInstanceState(Bundle): void  [API 11+]
+ onStart(): void  [API 11+]
+ onStop(): void  [API 11+]
+ onViewCreated(View, Bundle): void  [API 11+]
+ setPreferenceScreen(PreferenceScreen): void  [API 11+]
```

## PreferenceFragment.OnPreferenceStartFragmentCallback — framework; API 11+, deprecated in 28 (1 +, 0 *, 0 ~, 0 -)

```
+ onPreferenceStartFragment(PreferenceFragment, Preference): boolean  [API 11+]
```

## PreferenceGroup — framework; API 1+, deprecated in 29 (16 +, 0 *, 1 ~, 2 -)

```
+ PreferenceGroup(Context, AttributeSet)  [API 1+]
+ PreferenceGroup(Context, AttributeSet, int)  [API 1+]
+ PreferenceGroup(Context, AttributeSet, int, int)  [API 21+]
+ addItemFromInflater(Preference): void  [API 1+]
+ addPreference(Preference): boolean  [API 1+]
- dispatchRestoreInstanceState(Bundle): void  [API 1+]
- dispatchSaveInstanceState(Bundle): void  [API 1+]
+ findPreference(CharSequence): Preference  [API 1+]
+ getPreference(int): Preference  [API 1+]
+ getPreferenceCount(): int  [API 1+]
~ isOnSameScreenAsChildren(): boolean  [API 1+]
+ isOrderingAsAdded(): boolean  [API 1+]
+ notifyDependencyChange(boolean): void  [API 1+]
+ onAttachedToActivity(): void  [API 1+]
+ onPrepareAddPreference(Preference): boolean  [API 1+]
+ onPrepareForRemoval(): void  [API 1+]
+ removeAll(): void  [API 1+]
+ removePreference(Preference): boolean  [API 1+]
+ setOrderingAsAdded(boolean): void  [API 1+]
```

## PreferenceManager — framework; API 1+, deprecated in 29 (11 +, 0 *, 6 ~, 0 -)

```
+ createPreferenceScreen(Context): PreferenceScreen  [API 1+]
+ findPreference(CharSequence): Preference  [API 1+]
+ getDefaultSharedPreferences(Context): SharedPreferences [static]  [API 1+]
+ getDefaultSharedPreferencesName(Context): String [static]  [API 24+]
~ getPreferenceDataStore(): PreferenceDataStore  [API 26+]
+ getSharedPreferences(): SharedPreferences  [API 1+]
+ getSharedPreferencesMode(): int  [API 1+]
+ getSharedPreferencesName(): String  [API 1+]
~ isStorageDefault(): boolean  [API 24+]
~ isStorageDeviceProtected(): boolean  [API 24+]
+ setDefaultValues(Context, String, int, int, boolean): void [static]  [API 1+]
+ setDefaultValues(Context, int, boolean): void [static]  [API 1+]
~ setPreferenceDataStore(PreferenceDataStore): void  [API 26+]
+ setSharedPreferencesMode(int): void  [API 1+]
+ setSharedPreferencesName(String): void  [API 1+]
~ setStorageDefault(): void  [API 24+]
~ setStorageDeviceProtected(): void  [API 24+]
```

## PreferenceManager.OnActivityDestroyListener — framework; API 1+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onActivityDestroy(): void  [API 1+]
```

## PreferenceManager.OnActivityResultListener — framework; API 1+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onActivityResult(int, int, Intent): boolean  [API 1+]
```

## PreferenceManager.OnActivityStopListener — framework; API 1+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onActivityStop(): void  [API 1+]
```

## PreferenceScreen — framework; API 1+, deprecated in 29 (9 +, 0 *, 1 ~, 0 -)

```
+ bind(ListView): void  [API 1+]
+ getDialog(): Dialog  [API 1+]
+ getRootAdapter(): ListAdapter  [API 1+]
~ isOnSameScreenAsChildren(): boolean  [API 1+]
+ onClick(): void  [API 1+]
+ onCreateRootAdapter(): ListAdapter  [API 1+]
+ onDismiss(DialogInterface): void  [API 1+]
+ onItemClick(AdapterView, View, int, long): void  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
```

## RingtonePreference — framework; API 1+, deprecated in 29 (17 +, 0 *, 0 ~, 1 -)

```
+ RingtonePreference(Context)  [API 1+]
+ RingtonePreference(Context, AttributeSet)  [API 1+]
+ RingtonePreference(Context, AttributeSet, int)  [API 1+]
+ RingtonePreference(Context, AttributeSet, int, int)  [API 21+]
+ getRingtoneType(): int  [API 1+]
+ getShowDefault(): boolean  [API 1+]
+ getShowSilent(): boolean  [API 1+]
+ onActivityResult(int, int, Intent): boolean  [API 1+]
+ onAttachedToHierarchy(PreferenceManager): void  [API 1+]
+ onClick(): void  [API 1+]
+ onGetDefaultValue(TypedArray, int): Object  [API 1+]
- onPrepareRingtonePickerIntent(Intent): void  [API 1+]
+ onRestoreRingtone(): Uri  [API 1+]
+ onSaveRingtone(Uri): void  [API 1+]
+ onSetInitialValue(boolean, Object): void  [API 1+]
+ setRingtoneType(int): void  [API 1+]
+ setShowDefault(boolean): void  [API 1+]
+ setShowSilent(boolean): void  [API 1+]
```

## SwitchPreference — framework; API 14+, deprecated in 29 (11 +, 0 *, 0 ~, 0 -)

```
+ SwitchPreference(Context)  [API 14+]
+ SwitchPreference(Context, AttributeSet)  [API 14+]
+ SwitchPreference(Context, AttributeSet, int)  [API 14+]
+ SwitchPreference(Context, AttributeSet, int, int)  [API 21+]
+ getSwitchTextOff(): CharSequence  [API 14+]
+ getSwitchTextOn(): CharSequence  [API 14+]
+ onBindView(View): void  [API 14+]
+ setSwitchTextOff(CharSequence): void  [API 14+]
+ setSwitchTextOff(int): void  [API 14+]
+ setSwitchTextOn(CharSequence): void  [API 14+]
+ setSwitchTextOn(int): void  [API 14+]
```

## TwoStatePreference — framework; API 14+, deprecated in 29 (20 +, 0 *, 0 ~, 0 -)

```
+ TwoStatePreference(Context)  [API 14+]
+ TwoStatePreference(Context, AttributeSet)  [API 14+]
+ TwoStatePreference(Context, AttributeSet, int)  [API 14+]
+ TwoStatePreference(Context, AttributeSet, int, int)  [API 21+]
+ getDisableDependentsState(): boolean  [API 14+]
+ getSummaryOff(): CharSequence  [API 14+]
+ getSummaryOn(): CharSequence  [API 14+]
+ isChecked(): boolean  [API 14+]
+ onClick(): void  [API 14+]
+ onGetDefaultValue(TypedArray, int): Object  [API 14+]
+ onRestoreInstanceState(Parcelable): void  [API 14+]
+ onSaveInstanceState(): Parcelable  [API 14+]
+ onSetInitialValue(boolean, Object): void  [API 14+]
+ setChecked(boolean): void  [API 14+]
+ setDisableDependentsState(boolean): void  [API 14+]
+ setSummaryOff(CharSequence): void  [API 14+]
+ setSummaryOff(int): void  [API 14+]
+ setSummaryOn(CharSequence): void  [API 14+]
+ setSummaryOn(int): void  [API 14+]
+ shouldDisableDependents(): boolean  [API 14+]
```


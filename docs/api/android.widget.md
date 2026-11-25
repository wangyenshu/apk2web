# android.widget

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbsListView — framework; API 1+ (130 +, 0 *, 18 ~, 0 -)

```
+ AbsListView(Context)  [API 1+]
+ AbsListView(Context, AttributeSet)  [API 1+]
+ AbsListView(Context, AttributeSet, int)  [API 1+]
+ AbsListView(Context, AttributeSet, int, int)  [API 21+]
+ addTouchables(ArrayList): void  [API 1+]
~ afterTextChanged(Editable): void  [API 1+]
~ beforeTextChanged(CharSequence, int, int, int): void  [API 1+]
+ canScrollList(int): boolean  [API 19+]
~ checkInputConnectionProxy(View): boolean  [API 1+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
+ clearChoices(): void  [API 11+]
+ clearTextFilter(): void  [API 1+]
+ computeVerticalScrollExtent(): int  [API 1+]
+ computeVerticalScrollOffset(): int  [API 1+]
+ computeVerticalScrollRange(): int  [API 1+]
~ deferNotifyDataSetChanged(): void  [API 14+]
+ dispatchDraw(Canvas): void  [API 1+]
~ dispatchDrawableHotspotChanged(float, float): void  [API 1+]
~ dispatchSetPressed(boolean): void  [API 1+]
+ draw(Canvas): void  [API 1+]
+ drawableStateChanged(): void  [API 1+]
+ fling(int): void  [API 21+]
+ generateDefaultLayoutParams(): ViewGroup.LayoutParams  [API 1+]
+ generateLayoutParams(AttributeSet): AbsListView.LayoutParams  [API 1+]
+ generateLayoutParams(ViewGroup.LayoutParams): ViewGroup.LayoutParams  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getBottomEdgeEffectColor(): int  [API 29+]
~ getBottomFadingEdgeStrength(): float  [API 1+]
~ getBottomPaddingOffset(): int  [API 1+]
+ getCacheColorHint(): int  [API 1+]
+ getCheckedItemCount(): int  [API 11+]
+ getCheckedItemIds(): long[]  [API 11+]
+ getCheckedItemPosition(): int  [API 11+]
+ getCheckedItemPositions(): SparseBooleanArray  [API 11+]
+ getChoiceMode(): int  [API 11+]
+ getContextMenuInfo(): ContextMenu.ContextMenuInfo  [API 1+]
+ getFocusedRect(Rect): void  [API 1+]
~ getLeftPaddingOffset(): int  [API 1+]
+ getListPaddingBottom(): int  [API 1+]
+ getListPaddingLeft(): int  [API 1+]
+ getListPaddingRight(): int  [API 1+]
+ getListPaddingTop(): int  [API 1+]
~ getRightPaddingOffset(): int  [API 1+]
+ getSelectedView(): View  [API 1+]
+ getSelector(): Drawable  [API 1+]
~ getSolidColor(): int  [API 1+]
+ getTextFilter(): CharSequence  [API 3+]
+ getTopEdgeEffectColor(): int  [API 29+]
~ getTopFadingEdgeStrength(): float  [API 1+]
~ getTopPaddingOffset(): int  [API 1+]
+ getTranscriptMode(): int  [API 1+]
+ getVerticalScrollbarWidth(): int  [API 1+]
+ handleDataChanged(): void  [API 1+]
+ hasTextFilter(): boolean  [API 1+]
+ invalidateViews(): void  [API 1+]
+ isDrawSelectorOnTop(): boolean  [API 29+]
+ isFastScrollAlwaysVisible(): boolean  [API 11+]
+ isFastScrollEnabled(): boolean  [API 3+]
+ isInFilterMode(): boolean  [API 1+]
+ isItemChecked(int): boolean  [API 11+]
~ isPaddingOffsetRequired(): boolean  [API 1+]
+ isScrollingCacheEnabled(): boolean  [API 1+]
+ isSelectedChildViewEnabled(): boolean  [API 33+]
+ isSmoothScrollbarEnabled(): boolean  [API 3+]
+ isStackFromBottom(): boolean  [API 1+]
+ isTextFilterEnabled(): boolean  [API 1+]
~ jumpDrawablesToCurrentState(): void  [API 1+]
+ layoutChildren(): void  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onCancelPendingInputEvents(): void  [API 1+]
+ onCreateInputConnection(EditorInfo): InputConnection  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onDisplayHint(int): void  [API 1+]
+ onFilterComplete(int): void  [API 1+]
+ onFocusChanged(boolean, int, Rect): void  [API 1+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 1+]
+ onGlobalLayout(): void  [API 1+]
+ onInitializeAccessibilityNodeInfoForItem(View, int, AccessibilityNodeInfo): void  [API 19+]
+ onInterceptHoverEvent(MotionEvent): boolean  [API 1+]
+ onInterceptTouchEvent(MotionEvent): boolean  [API 1+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onNestedFling(View, float, float, boolean): boolean  [API 1+]
+ onNestedScroll(View, int, int, int, int): void  [API 1+]
+ onNestedScrollAccepted(View, View, int): void  [API 1+]
+ onOverScrolled(int, int, boolean, boolean): void  [API 1+]
+ onProvideContentCaptureStructure(ViewStructure, int): void  [API 1+]
+ onRemoteAdapterConnected(): boolean  [API 11+]
+ onRemoteAdapterDisconnected(): void  [API 11+]
+ onResolvePointerIcon(MotionEvent, int): PointerIcon  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onRtlPropertiesChanged(int): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ onSizeChanged(int, int, int, int): void  [API 1+]
+ onStartNestedScroll(View, View, int): boolean  [API 1+]
+ onTextChanged(CharSequence, int, int, int): void  [API 1+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ onTouchModeChanged(boolean): void  [API 1+]
+ onWindowFocusChanged(boolean): void  [API 1+]
+ performItemClick(View, int, long): boolean  [API 1+]
+ pointToPosition(int, int): int  [API 1+]
+ pointToRowId(int, int): long  [API 1+]
+ reclaimViews(List): void  [API 1+]
+ requestDisallowInterceptTouchEvent(boolean): void  [API 1+]
+ requestLayout(): void  [API 1+]
+ scrollListBy(int): void  [API 19+]
+ setAdapter(ListAdapter): void  [API 11+]
+ setBottomEdgeEffectColor(int): void  [API 29+]
+ setCacheColorHint(int): void  [API 1+]
+ setChoiceMode(int): void  [API 11+]
+ setDrawSelectorOnTop(boolean): void  [API 1+]
+ setEdgeEffectColor(int): void  [API 29+]
+ setFastScrollAlwaysVisible(boolean): void  [API 11+]
+ setFastScrollEnabled(boolean): void  [API 3+]
+ setFastScrollStyle(int): void  [API 21+]
+ setFilterText(String): void  [API 1+]
~ setFriction(float): void  [API 11+]
+ setItemChecked(int, boolean): void  [API 11+]
+ setMultiChoiceModeListener(AbsListView.MultiChoiceModeListener): void  [API 11+]
+ setOnScrollListener(AbsListView.OnScrollListener): void  [API 1+]
+ setRecyclerListener(AbsListView.RecyclerListener): void  [API 1+]
~ setRemoteViewsAdapter(Intent): void  [API 11+]
+ setScrollBarStyle(int): void  [API 1+]
+ setScrollIndicators(View, View): void  [API 1+]
+ setScrollingCacheEnabled(boolean): void  [API 1+]
+ setSelectedChildViewEnabled(boolean): void  [API 33+]
+ setSelectionFromTop(int, int): void  [API 21+]
+ setSelector(Drawable): void  [API 1+]
+ setSelector(int): void  [API 1+]
+ setSmoothScrollbarEnabled(boolean): void  [API 3+]
+ setStackFromBottom(boolean): void  [API 1+]
+ setTextFilterEnabled(boolean): void  [API 1+]
+ setTopEdgeEffectColor(int): void  [API 29+]
+ setTranscriptMode(int): void  [API 1+]
~ setVelocityScale(float): void  [API 11+]
+ setVerticalScrollbarPosition(int): void  [API 1+]
+ showContextMenu(): boolean  [API 1+]
+ showContextMenu(float, float): boolean  [API 1+]
+ showContextMenuForChild(View): boolean  [API 1+]
+ showContextMenuForChild(View, float, float): boolean  [API 1+]
+ smoothScrollBy(int, int): void  [API 8+]
+ smoothScrollToPosition(int): void  [API 8+]
+ smoothScrollToPosition(int, int): void  [API 8+]
+ smoothScrollToPositionFromTop(int, int): void  [API 11+]
+ smoothScrollToPositionFromTop(int, int, int): void  [API 11+]
+ verifyDrawable(Drawable): boolean  [API 1+]
```

## AbsListView.LayoutParams — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ LayoutParams(Context, AttributeSet)  [API 1+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 1+]
+ LayoutParams(int, int)  [API 1+]
+ LayoutParams(int, int, int)  [API 1+]
```

## AbsListView.MultiChoiceModeListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onItemCheckedStateChanged(ActionMode, int, long, boolean): void  [API 11+]
```

## AbsListView.OnScrollListener — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ onScroll(AbsListView, int, int, int): void  [API 1+]
+ onScrollStateChanged(AbsListView, int): void  [API 1+]
```

## AbsListView.RecyclerListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onMovedToScrapHeap(View): void  [API 1+]
```

## AbsListView.SelectionBoundsAdjuster — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ adjustListItemSelectionBounds(Rect): void  [API 11+]
```

## AbsSeekBar — framework; API 1+ (39 +, 0 *, 1 ~, 0 -)

```
+ AbsSeekBar(Context)  [API 1+]
+ AbsSeekBar(Context, AttributeSet)  [API 1+]
+ AbsSeekBar(Context, AttributeSet, int)  [API 1+]
+ AbsSeekBar(Context, AttributeSet, int, int)  [API 21+]
+ drawableHotspotChanged(float, float): void  [API 1+]
+ drawableStateChanged(): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getKeyProgressIncrement(): int  [API 3+]
+ getSplitTrack(): boolean  [API 21+]
+ getThumb(): Drawable  [API 16+]
+ getThumbOffset(): int  [API 1+]
+ getThumbTintBlendMode(): BlendMode  [API 29+]
+ getThumbTintList(): ColorStateList  [API 21+]
+ getThumbTintMode(): PorterDuff.Mode  [API 21+]
+ getTickMark(): Drawable  [API 24+]
+ getTickMarkTintBlendMode(): BlendMode  [API 29+]
+ getTickMarkTintList(): ColorStateList  [API 24+]
+ getTickMarkTintMode(): PorterDuff.Mode  [API 24+]
~ jumpDrawablesToCurrentState(): void  [API 1+]
+ onDraw(Canvas): void  [API 1+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onRtlPropertiesChanged(int): void  [API 1+]
+ onSizeChanged(int, int, int, int): void  [API 1+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ setKeyProgressIncrement(int): void  [API 3+]
+ setMax(int): void  [API 1+]
+ setMin(int): void  [API 1+]
+ setSplitTrack(boolean): void  [API 21+]
+ setSystemGestureExclusionRects(List): void  [API 1+]
+ setThumb(Drawable): void  [API 1+]
+ setThumbOffset(int): void  [API 1+]
+ setThumbTintBlendMode(BlendMode): void  [API 29+]
+ setThumbTintList(ColorStateList): void  [API 21+]
+ setThumbTintMode(PorterDuff.Mode): void  [API 21+]
+ setTickMark(Drawable): void  [API 24+]
+ setTickMarkTintBlendMode(BlendMode): void  [API 29+]
+ setTickMarkTintList(ColorStateList): void  [API 24+]
+ setTickMarkTintMode(PorterDuff.Mode): void  [API 24+]
+ verifyDrawable(Drawable): boolean  [API 1+]
```

## AbsSpinner — framework; API 1+ (17 +, 0 *, 3 ~, 1 -)

```
+ AbsSpinner(Context)  [API 1+]
+ AbsSpinner(Context, AttributeSet)  [API 1+]
+ AbsSpinner(Context, AttributeSet, int)  [API 1+]
- AbsSpinner(Context, AttributeSet, int, int)  [API 21+]
~ autofill(AutofillValue): void  [API 1+]
+ dispatchRestoreInstanceState(SparseArray): void  [API 1+]
+ generateDefaultLayoutParams(): ViewGroup.LayoutParams  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAdapter(): SpinnerAdapter  [API 1+]
~ getAutofillType(): int  [API 1+]
~ getAutofillValue(): AutofillValue  [API 1+]
+ getCount(): int  [API 1+]
+ getSelectedView(): View  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ pointToPosition(int, int): int  [API 1+]
+ requestLayout(): void  [API 1+]
+ setAdapter(SpinnerAdapter): void  [API 1+]
+ setSelection(int): void  [API 1+]
+ setSelection(int, boolean): void  [API 1+]
```

## AbsoluteLayout — framework; API 1+, deprecated in 3 (9 +, 0 *, 1 ~, 1 -)

```
+ AbsoluteLayout(Context)  [API 1+]
+ AbsoluteLayout(Context, AttributeSet)  [API 1+]
+ AbsoluteLayout(Context, AttributeSet, int)  [API 1+]
- AbsoluteLayout(Context, AttributeSet, int, int)  [API 21+, deprecated in 21]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
+ generateDefaultLayoutParams(): ViewGroup.LayoutParams  [API 1+]
+ generateLayoutParams(AttributeSet): ViewGroup.LayoutParams  [API 1+]
+ generateLayoutParams(ViewGroup.LayoutParams): ViewGroup.LayoutParams  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
~ shouldDelayChildPressedState(): boolean  [API 1+]
```

## AbsoluteLayout.LayoutParams — framework; API 1+, deprecated in 29 (3 +, 0 *, 0 ~, 1 -)

```
+ LayoutParams(Context, AttributeSet)  [API 1+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 1+]
+ LayoutParams(int, int, int, int)  [API 1+]
- debug(String): String  [API 1+]
+ x [field]  [API 1+]
+ y [field]  [API 1+]
```

## ActionMenuView — framework; API 21+ (20 +, 0 *, 0 ~, 0 -)

```
+ ActionMenuView(Context)  [API 21+]
+ ActionMenuView(Context, AttributeSet)  [API 21+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 21+]
+ dismissPopupMenus(): void  [API 21+]
+ generateDefaultLayoutParams(): ActionMenuView.LayoutParams  [API 21+]
+ generateLayoutParams(AttributeSet): ActionMenuView.LayoutParams  [API 21+]
+ generateLayoutParams(ViewGroup.LayoutParams): ActionMenuView.LayoutParams  [API 21+]
+ getMenu(): Menu  [API 21+]
+ getOverflowIcon(): Drawable  [API 23+]
+ getPopupTheme(): int  [API 21+]
+ hideOverflowMenu(): boolean  [API 21+]
+ isOverflowMenuShowing(): boolean  [API 21+]
+ onConfigurationChanged(Configuration): void  [API 21+]
+ onDetachedFromWindow(): void  [API 21+]
+ onLayout(boolean, int, int, int, int): void  [API 21+]
+ onMeasure(int, int): void  [API 21+]
+ setOnMenuItemClickListener(ActionMenuView.OnMenuItemClickListener): void  [API 21+]
+ setOverflowIcon(Drawable): void  [API 23+]
+ setPopupTheme(int): void  [API 21+]
+ showOverflowMenu(): boolean  [API 21+]
```

## ActionMenuView.LayoutParams — framework; API 21+ (4 +, 0 *, 0 ~, 0 -)

```
+ LayoutParams(ActionMenuView.LayoutParams)  [API 21+]
+ LayoutParams(Context, AttributeSet)  [API 21+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 21+]
+ LayoutParams(int, int)  [API 21+]
```

## ActionMenuView.OnMenuItemClickListener — framework; API 21+ (1 +, 0 *, 0 ~, 0 -)

```
+ onMenuItemClick(MenuItem): boolean  [API 21+]
```

## Adapter — framework; API 1+ (10 +, 0 *, 0 ~, 1 -)

```
- getAutofillOptions(): CharSequence[]  [API 26+]
+ getCount(): int  [API 1+]
+ getItem(int): Object  [API 1+]
+ getItemId(int): long  [API 1+]
+ getItemViewType(int): int  [API 1+]
+ getView(int, View, ViewGroup): View  [API 1+]
+ getViewTypeCount(): int  [API 1+]
+ hasStableIds(): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ registerDataSetObserver(DataSetObserver): void  [API 1+]
+ unregisterDataSetObserver(DataSetObserver): void  [API 1+]
```

## AdapterView — framework; API 1+ (34 +, 0 *, 8 ~, 1 -)

```
+ AdapterView(Context)  [API 1+]
+ AdapterView(Context, AttributeSet)  [API 1+]
+ AdapterView(Context, AttributeSet, int)  [API 1+]
- AdapterView(Context, AttributeSet, int, int)  [API 21+]
~ addView(View): void  [API 1+]
~ addView(View, ViewGroup.LayoutParams): void  [API 1+]
~ addView(View, int): void  [API 1+]
~ addView(View, int, ViewGroup.LayoutParams): void  [API 1+]
+ canAnimate(): boolean  [API 1+]
+ dispatchRestoreInstanceState(SparseArray): void  [API 1+]
+ dispatchSaveInstanceState(SparseArray): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAdapter(): Adapter  [API 1+]
+ getCount(): int  [API 1+]
+ getEmptyView(): View  [API 1+]
+ getFirstVisiblePosition(): int  [API 1+]
+ getItemAtPosition(int): Object  [API 1+]
+ getItemIdAtPosition(int): long  [API 1+]
+ getLastVisiblePosition(): int  [API 1+]
+ getOnItemClickListener(): AdapterView.OnItemClickListener  [API 1+]
+ getOnItemLongClickListener(): AdapterView.OnItemLongClickListener  [API 1+]
+ getOnItemSelectedListener(): AdapterView.OnItemSelectedListener  [API 1+]
+ getPositionForView(View): int  [API 1+]
+ getSelectedItem(): Object  [API 1+]
+ getSelectedItemId(): long  [API 1+]
+ getSelectedItemPosition(): int  [API 1+]
+ getSelectedView(): View  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onProvideAutofillStructure(ViewStructure, int): void  [API 1+]
+ performItemClick(View, int, long): boolean  [API 1+]
~ removeAllViews(): void  [API 1+]
~ removeView(View): void  [API 1+]
~ removeViewAt(int): void  [API 1+]
+ setAdapter(Adapter): void  [API 1+]
+ setEmptyView(View): void  [API 1+]
+ setFocusable(int): void  [API 1+]
+ setFocusableInTouchMode(boolean): void  [API 1+]
~ setOnClickListener(View.OnClickListener): void  [API 1+]
+ setOnItemClickListener(AdapterView.OnItemClickListener): void  [API 1+]
+ setOnItemLongClickListener(AdapterView.OnItemLongClickListener): void  [API 1+]
+ setOnItemSelectedListener(AdapterView.OnItemSelectedListener): void  [API 1+]
+ setSelection(int): void  [API 1+]
```

## AdapterView.AdapterContextMenuInfo — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ AdapterContextMenuInfo(View, int, long)  [API 1+]
+ id [field]  [API 1+]
+ position [field]  [API 1+]
+ targetView [field]  [API 1+]
```

## AdapterView.OnItemClickListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onItemClick(AdapterView, View, int, long): void  [API 1+]
```

## AdapterView.OnItemLongClickListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onItemLongClick(AdapterView, View, int, long): boolean  [API 1+]
```

## AdapterView.OnItemSelectedListener — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ onItemSelected(AdapterView, View, int, long): void  [API 1+]
+ onNothingSelected(AdapterView): void  [API 1+]
```

## AdapterViewAnimator — declaration; API 11+ (4 +, 0 *, 0 ~, 29 -)

```
- AdapterViewAnimator(Context)  [API 11+]
- AdapterViewAnimator(Context, AttributeSet)  [API 11+]
- AdapterViewAnimator(Context, AttributeSet, int)  [API 14+]
- AdapterViewAnimator(Context, AttributeSet, int, int)  [API 21+]
- advance(): void  [API 11+]
- deferNotifyDataSetChanged(): void  [API 14+]
- fyiWillBeAdvancedByHostKThx(): void  [API 11+]
- getAccessibilityClassName(): CharSequence  [API 11+]
- getAdapter(): Adapter  [API 11+]
- getBaseline(): int  [API 11+]
- getCurrentView(): View  [API 11+]
- getDisplayedChild(): int  [API 11+]
- getInAnimation(): ObjectAnimator  [API 11+]
- getOutAnimation(): ObjectAnimator  [API 11+]
- getSelectedView(): View  [API 11+]
+ onLayout(boolean, int, int, int, int): void  [API 11+]
+ onMeasure(int, int): void  [API 11+]
- onRemoteAdapterConnected(): boolean  [API 11+]
+ onRemoteAdapterDisconnected(): void  [API 11+]
+ onRestoreInstanceState(Parcelable): void  [API 11+]
- onSaveInstanceState(): Parcelable  [API 11+]
- onTouchEvent(MotionEvent): boolean  [API 11+]
- setAdapter(Adapter): void  [API 11+]
- setAnimateFirstView(boolean): void  [API 11+]
- setDisplayedChild(int): void  [API 11+]
- setInAnimation(Context, int): void  [API 11+]
- setInAnimation(ObjectAnimator): void  [API 11+]
- setOutAnimation(Context, int): void  [API 11+]
- setOutAnimation(ObjectAnimator): void  [API 11+]
- setRemoteViewsAdapter(Intent): void  [API 11+]
- setSelection(int): void  [API 11+]
- showNext(): void  [API 11+]
- showPrevious(): void  [API 11+]
```

## AdapterViewFlipper — declaration; API 11+ (0 +, 0 *, 0 ~, 19 -)

```
- AdapterViewFlipper(Context)  [API 11+]
- AdapterViewFlipper(Context, AttributeSet)  [API 11+]
- AdapterViewFlipper(Context, AttributeSet, int)  [API 21+]
- AdapterViewFlipper(Context, AttributeSet, int, int)  [API 21+]
- fyiWillBeAdvancedByHostKThx(): void  [API 11+]
- getAccessibilityClassName(): CharSequence  [API 11+]
- getFlipInterval(): int  [API 16+]
- isAutoStart(): boolean  [API 11+]
- isFlipping(): boolean  [API 11+]
- onAttachedToWindow(): void  [API 11+]
- onDetachedFromWindow(): void  [API 11+]
- onWindowVisibilityChanged(int): void  [API 11+]
- setAdapter(Adapter): void  [API 11+]
- setAutoStart(boolean): void  [API 11+]
- setFlipInterval(int): void  [API 11+]
- showNext(): void  [API 11+]
- showPrevious(): void  [API 11+]
- startFlipping(): void  [API 11+]
- stopFlipping(): void  [API 11+]
```

## Advanceable — framework; API 16+ (2 +, 0 *, 0 ~, 0 -)

```
+ advance(): void  [API 16+]
+ fyiWillBeAdvancedByHostKThx(): void  [API 16+]
```

## AlphabetIndexer — framework; API 3+ (8 +, 0 *, 0 ~, 0 -)

```
+ AlphabetIndexer(Cursor, int, CharSequence)  [API 3+]
+ compare(String, String): int  [API 3+]
+ getPositionForSection(int): int  [API 3+]
+ getSectionForPosition(int): int  [API 3+]
+ getSections(): Object[]  [API 3+]
+ mAlphabet [field]  [API 3+]
+ mColumnIndex [field]  [API 3+]
+ mDataCursor [field]  [API 3+]
+ onChanged(): void  [API 3+]
+ onInvalidated(): void  [API 3+]
+ setCursor(Cursor): void  [API 3+]
```

## AnalogClock — framework; API 1+, deprecated in 23 (32 +, 0 *, 0 ~, 0 -)

```
+ AnalogClock(Context)  [API 1+]
+ AnalogClock(Context, AttributeSet)  [API 1+]
+ AnalogClock(Context, AttributeSet, int)  [API 1+]
+ AnalogClock(Context, AttributeSet, int, int)  [API 21+]
+ getDialTintBlendMode(): BlendMode  [API 31+, deprecated in 31]
+ getDialTintList(): ColorStateList  [API 31+, deprecated in 31]
+ getHourHandTintBlendMode(): BlendMode  [API 31+, deprecated in 31]
+ getHourHandTintList(): ColorStateList  [API 31+, deprecated in 31]
+ getMinuteHandTintBlendMode(): BlendMode  [API 31+, deprecated in 31]
+ getMinuteHandTintList(): ColorStateList  [API 31+, deprecated in 31]
+ getSecondHandTintBlendMode(): BlendMode  [API 31+, deprecated in 31]
+ getSecondHandTintList(): ColorStateList  [API 31+, deprecated in 31]
+ getTimeZone(): String  [API 31+, deprecated in 31]
+ onAttachedToWindow(): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onDraw(Canvas): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onSizeChanged(int, int, int, int): void  [API 1+]
+ onVisibilityAggregated(boolean): void  [API 1+]
+ setDial(Icon): void  [API 31+, deprecated in 31]
+ setDialTintBlendMode(BlendMode): void  [API 31+, deprecated in 31]
+ setDialTintList(ColorStateList): void  [API 31+, deprecated in 31]
+ setHourHand(Icon): void  [API 31+, deprecated in 31]
+ setHourHandTintBlendMode(BlendMode): void  [API 31+, deprecated in 31]
+ setHourHandTintList(ColorStateList): void  [API 31+, deprecated in 31]
+ setMinuteHand(Icon): void  [API 31+, deprecated in 31]
+ setMinuteHandTintBlendMode(BlendMode): void  [API 31+, deprecated in 31]
+ setMinuteHandTintList(ColorStateList): void  [API 31+, deprecated in 31]
+ setSecondHand(Icon): void  [API 31+, deprecated in 31]
+ setSecondHandTintBlendMode(BlendMode): void  [API 31+, deprecated in 31]
+ setSecondHandTintList(ColorStateList): void  [API 31+, deprecated in 31]
+ setTimeZone(String): void  [API 31+, deprecated in 31]
```

## ArrayAdapter — framework; API 1+ (25 +, 0 *, 2 ~, 1 -)

```
+ ArrayAdapter(Context, int)  [API 1+]
+ ArrayAdapter(Context, int, List)  [API 1+]
+ ArrayAdapter(Context, int, Object[])  [API 1+]
+ ArrayAdapter(Context, int, int)  [API 1+]
+ ArrayAdapter(Context, int, int, List)  [API 1+]
+ ArrayAdapter(Context, int, int, Object[])  [API 1+]
+ add(Object): void  [API 1+]
+ addAll(Collection): void  [API 11+]
+ addAll(Object[]): void  [API 11+]
+ clear(): void  [API 1+]
+ createFromResource(Context, int, int): ArrayAdapter [static]  [API 1+]
- getAutofillOptions(): CharSequence[]  [API 1+]
+ getContext(): Context  [API 1+]
+ getCount(): int  [API 1+]
+ getDropDownView(int, View, ViewGroup): View  [API 1+]
~ getDropDownViewTheme(): Resources.Theme  [API 1+]
+ getFilter(): Filter  [API 1+]
+ getItem(int): Object  [API 1+]
+ getItemId(int): long  [API 1+]
+ getPosition(Object): int  [API 1+]
+ getView(int, View, ViewGroup): View  [API 1+]
+ insert(Object, int): void  [API 1+]
+ notifyDataSetChanged(): void  [API 1+]
+ remove(Object): void  [API 1+]
+ setDropDownViewResource(int): void  [API 1+]
~ setDropDownViewTheme(Resources.Theme): void  [API 1+]
+ setNotifyOnChange(boolean): void  [API 1+]
+ sort(Comparator): void  [API 3+]
```

## AutoCompleteTextView — framework; API 1+ (59 +, 0 *, 0 ~, 5 -)

```
+ AutoCompleteTextView(Context)  [API 1+]
+ AutoCompleteTextView(Context, AttributeSet)  [API 1+]
+ AutoCompleteTextView(Context, AttributeSet, int)  [API 1+]
+ AutoCompleteTextView(Context, AttributeSet, int, int)  [API 21+]
- AutoCompleteTextView(Context, AttributeSet, int, int, Resources.Theme)  [API 24+]
+ clearListSelection(): void  [API 3+]
+ convertSelectionToString(Object): CharSequence  [API 1+]
+ dismissDropDown(): void  [API 1+]
+ enoughToFilter(): boolean  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAdapter(): ListAdapter  [API 1+]
+ getCompletionHint(): CharSequence  [API 16+]
+ getDropDownAnchor(): int  [API 3+]
+ getDropDownBackground(): Drawable  [API 5+]
+ getDropDownHeight(): int  [API 4+]
+ getDropDownHorizontalOffset(): int  [API 5+]
+ getDropDownVerticalOffset(): int  [API 5+]
+ getDropDownWidth(): int  [API 3+]
+ getFilter(): Filter  [API 1+]
- getInputMethodMode(): int  [API 29+]
- getItemClickListener(): AdapterView.OnItemClickListener  [API 1+, deprecated in 15]
- getItemSelectedListener(): AdapterView.OnItemSelectedListener  [API 1+, deprecated in 15]
+ getListSelection(): int  [API 3+]
+ getOnItemClickListener(): AdapterView.OnItemClickListener  [API 3+]
+ getOnItemSelectedListener(): AdapterView.OnItemSelectedListener  [API 3+]
+ getThreshold(): int  [API 1+]
+ getValidator(): AutoCompleteTextView.Validator  [API 1+]
+ isPerformingCompletion(): boolean  [API 3+]
+ isPopupShowing(): boolean  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onCommitCompletion(CompletionInfo): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onDisplayHint(int): void  [API 1+]
+ onFilterComplete(int): void  [API 1+]
+ onFocusChanged(boolean, int, Rect): void  [API 1+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyPreIme(int, KeyEvent): boolean  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onWindowFocusChanged(boolean): void  [API 1+]
+ performCompletion(): void  [API 1+]
+ performFiltering(CharSequence, int): void  [API 1+]
+ performValidation(): void  [API 1+]
+ refreshAutoCompleteResults(): void  [API 29+]
+ replaceText(CharSequence): void  [API 1+]
+ setAdapter(ListAdapter): void  [API 1+]
+ setCompletionHint(CharSequence): void  [API 1+]
+ setDropDownAnchor(int): void  [API 3+]
+ setDropDownBackgroundDrawable(Drawable): void  [API 5+]
+ setDropDownBackgroundResource(int): void  [API 5+]
+ setDropDownHeight(int): void  [API 4+]
+ setDropDownHorizontalOffset(int): void  [API 5+]
+ setDropDownVerticalOffset(int): void  [API 5+]
+ setDropDownWidth(int): void  [API 3+]
+ setFrame(int, int, int, int): boolean  [API 1+]
- setInputMethodMode(int): void  [API 29+]
+ setListSelection(int): void  [API 3+]
+ setOnClickListener(View.OnClickListener): void  [API 1+]
+ setOnDismissListener(AutoCompleteTextView.OnDismissListener): void  [API 17+]
+ setOnItemClickListener(AdapterView.OnItemClickListener): void  [API 1+]
+ setOnItemSelectedListener(AdapterView.OnItemSelectedListener): void  [API 1+]
+ setText(CharSequence, boolean): void  [API 17+]
+ setThreshold(int): void  [API 1+]
+ setValidator(AutoCompleteTextView.Validator): void  [API 1+]
+ showDropDown(): void  [API 1+]
```

## AutoCompleteTextView.OnDismissListener — framework; API 17+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDismiss(): void  [API 17+]
```

## AutoCompleteTextView.Validator — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ fixText(CharSequence): CharSequence  [API 1+]
+ isValid(CharSequence): boolean  [API 1+]
```

## BaseAdapter — framework; API 1+ (7 +, 0 *, 5 ~, 2 -)

```
+ BaseAdapter()  [API 1+]
~ areAllItemsEnabled(): boolean  [API 1+]
- getAutofillOptions(): CharSequence[]  [API 1+]
+ getDropDownView(int, View, ViewGroup): View  [API 1+]
~ getItemViewType(int): int  [API 1+]
~ getViewTypeCount(): int  [API 1+]
~ hasStableIds(): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
~ isEnabled(int): boolean  [API 1+]
+ notifyDataSetChanged(): void  [API 1+]
+ notifyDataSetInvalidated(): void  [API 1+]
+ registerDataSetObserver(DataSetObserver): void  [API 1+]
- setAutofillOptions(CharSequence[]): void  [API 27+]
+ unregisterDataSetObserver(DataSetObserver): void  [API 1+]
```

## BaseExpandableListAdapter — framework; API 1+ (10 +, 0 *, 5 ~, 0 -)

```
+ BaseExpandableListAdapter()  [API 1+]
~ areAllItemsEnabled(): boolean  [API 1+]
~ getChildType(int, int): int  [API 1+]
~ getChildTypeCount(): int  [API 1+]
+ getCombinedChildId(long, long): long  [API 1+]
+ getCombinedGroupId(long): long  [API 1+]
~ getGroupType(int): int  [API 1+]
~ getGroupTypeCount(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ notifyDataSetChanged(): void  [API 1+]
+ notifyDataSetInvalidated(): void  [API 1+]
+ onGroupCollapsed(int): void  [API 1+]
+ onGroupExpanded(int): void  [API 1+]
+ registerDataSetObserver(DataSetObserver): void  [API 1+]
+ unregisterDataSetObserver(DataSetObserver): void  [API 1+]
```

## Button — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ Button(Context)  [API 1+]
+ Button(Context, AttributeSet)  [API 1+]
+ Button(Context, AttributeSet, int)  [API 1+]
+ Button(Context, AttributeSet, int, int)  [API 21+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ onResolvePointerIcon(MotionEvent, int): PointerIcon  [API 1+]
```

## CalendarView — framework; API 11+ (16 +, 0 *, 2 ~, 19 -)

```
+ CalendarView(Context)  [API 11+]
+ CalendarView(Context, AttributeSet)  [API 11+]
+ CalendarView(Context, AttributeSet, int)  [API 11+]
+ CalendarView(Context, AttributeSet, int, int)  [API 21+]
+ getAccessibilityClassName(): CharSequence  [API 11+]
+ getDate(): long  [API 11+]
- getDateTextAppearance(): int  [API 16+]
+ getFirstDayOfWeek(): int  [API 11+]
- getFocusedMonthDateColor(): int  [API 16+, deprecated in 23]
+ getMaxDate(): long  [API 11+]
+ getMinDate(): long  [API 11+]
- getSelectedDateVerticalBar(): Drawable  [API 16+, deprecated in 23]
- getSelectedWeekBackgroundColor(): int  [API 16+, deprecated in 23]
~ getShowWeekNumber(): boolean  [API 11+, deprecated in 24]
- getShownWeekCount(): int  [API 16+, deprecated in 23]
- getUnfocusedMonthDateColor(): int  [API 16+, deprecated in 23]
- getWeekDayTextAppearance(): int  [API 16+]
- getWeekNumberColor(): int  [API 16+, deprecated in 23]
- getWeekSeparatorLineColor(): int  [API 16+, deprecated in 23]
+ onConfigurationChanged(Configuration): void  [API 11+]
+ setDate(long): void  [API 11+]
+ setDate(long, boolean, boolean): void  [API 11+]
- setDateTextAppearance(int): void  [API 16+]
+ setFirstDayOfWeek(int): void  [API 11+]
- setFocusedMonthDateColor(int): void  [API 16+, deprecated in 23]
+ setMaxDate(long): void  [API 11+]
+ setMinDate(long): void  [API 11+]
+ setOnDateChangeListener(CalendarView.OnDateChangeListener): void  [API 11+]
- setSelectedDateVerticalBar(Drawable): void  [API 16+, deprecated in 23]
- setSelectedDateVerticalBar(int): void  [API 16+, deprecated in 23]
- setSelectedWeekBackgroundColor(int): void  [API 16+, deprecated in 23]
~ setShowWeekNumber(boolean): void  [API 11+, deprecated in 24]
- setShownWeekCount(int): void  [API 16+, deprecated in 23]
- setUnfocusedMonthDateColor(int): void  [API 16+, deprecated in 23]
- setWeekDayTextAppearance(int): void  [API 16+]
- setWeekNumberColor(int): void  [API 16+, deprecated in 23]
- setWeekSeparatorLineColor(int): void  [API 16+, deprecated in 23]
```

## CalendarView.OnDateChangeListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSelectedDayChange(CalendarView, int, int, int): void  [API 11+]
```

## CheckBox — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ CheckBox(Context)  [API 1+]
+ CheckBox(Context, AttributeSet)  [API 1+]
+ CheckBox(Context, AttributeSet, int)  [API 1+]
+ CheckBox(Context, AttributeSet, int, int)  [API 21+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
```

## Checkable — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ isChecked(): boolean  [API 1+]
+ setChecked(boolean): void  [API 1+]
+ toggle(): void  [API 1+]
```

## CheckedTextView — framework; API 1+ (27 +, 0 *, 0 ~, 1 -)

```
+ CheckedTextView(Context)  [API 1+]
+ CheckedTextView(Context, AttributeSet)  [API 1+]
+ CheckedTextView(Context, AttributeSet, int)  [API 1+]
+ CheckedTextView(Context, AttributeSet, int, int)  [API 21+]
+ drawableHotspotChanged(float, float): void  [API 1+]
+ drawableStateChanged(): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getCheckMarkDrawable(): Drawable  [API 16+]
+ getCheckMarkTintBlendMode(): BlendMode  [API 29+]
+ getCheckMarkTintList(): ColorStateList  [API 21+]
+ getCheckMarkTintMode(): PorterDuff.Mode  [API 21+]
+ isChecked(): boolean  [API 1+]
+ jumpDrawablesToCurrentState(): void  [API 1+]
+ onCreateDrawableState(int): int[]  [API 1+]
+ onDraw(Canvas): void  [API 1+]
- onPaddingChanged(int): void  [API 16-16, removed in 17]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onRtlPropertiesChanged(int): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ setCheckMarkDrawable(Drawable): void  [API 1+]
+ setCheckMarkDrawable(int): void  [API 1+]
+ setCheckMarkTintBlendMode(BlendMode): void  [API 29+]
+ setCheckMarkTintList(ColorStateList): void  [API 21+]
+ setCheckMarkTintMode(PorterDuff.Mode): void  [API 21+]
+ setChecked(boolean): void  [API 1+]
+ setVisibility(int): void  [API 1+]
+ toggle(): void  [API 1+]
+ verifyDrawable(Drawable): boolean  [API 1+]
```

## Chronometer — framework; API 1+ (20 +, 0 *, 0 ~, 0 -)

```
+ Chronometer(Context)  [API 1+]
+ Chronometer(Context, AttributeSet)  [API 1+]
+ Chronometer(Context, AttributeSet, int)  [API 1+]
+ Chronometer(Context, AttributeSet, int, int)  [API 21+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getBase(): long  [API 1+]
+ getContentDescription(): CharSequence  [API 1+]
+ getFormat(): String  [API 1+]
+ getOnChronometerTickListener(): Chronometer.OnChronometerTickListener  [API 3+]
+ isCountDown(): boolean  [API 24+]
+ isTheFinalCountDown(): boolean  [API 26+]
+ onDetachedFromWindow(): void  [API 1+]
+ onVisibilityChanged(View, int): void  [API 1+]
+ onWindowVisibilityChanged(int): void  [API 1+]
+ setBase(long): void  [API 1+]
+ setCountDown(boolean): void  [API 24+]
+ setFormat(String): void  [API 1+]
+ setOnChronometerTickListener(Chronometer.OnChronometerTickListener): void  [API 3+]
+ start(): void  [API 1+]
+ stop(): void  [API 1+]
```

## Chronometer.OnChronometerTickListener — framework; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ onChronometerTick(Chronometer): void  [API 3+]
```

## CompoundButton — framework; API 1+ (34 +, 0 *, 0 ~, 0 -)

```
+ CompoundButton(Context)  [API 1+]
+ CompoundButton(Context, AttributeSet)  [API 1+]
+ CompoundButton(Context, AttributeSet, int)  [API 1+]
+ CompoundButton(Context, AttributeSet, int, int)  [API 21+]
+ autofill(AutofillValue): void  [API 1+]
+ drawableHotspotChanged(float, float): void  [API 1+]
+ drawableStateChanged(): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAutofillType(): int  [API 1+]
+ getAutofillValue(): AutofillValue  [API 1+]
+ getButtonDrawable(): Drawable  [API 23+]
+ getButtonTintBlendMode(): BlendMode  [API 29+]
+ getButtonTintList(): ColorStateList  [API 21+]
+ getButtonTintMode(): PorterDuff.Mode  [API 21+]
+ getCompoundPaddingLeft(): int  [API 1+]
+ getCompoundPaddingRight(): int  [API 1+]
+ isChecked(): boolean  [API 1+]
+ jumpDrawablesToCurrentState(): void  [API 1+]
+ onCreateDrawableState(int): int[]  [API 1+]
+ onDraw(Canvas): void  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ performClick(): boolean  [API 1+]
+ setButtonDrawable(Drawable): void  [API 1+]
+ setButtonDrawable(int): void  [API 1+]
+ setButtonIcon(Icon): void  [API 31+]
+ setButtonTintBlendMode(BlendMode): void  [API 29+]
+ setButtonTintList(ColorStateList): void  [API 21+]
+ setButtonTintMode(PorterDuff.Mode): void  [API 21+]
+ setChecked(boolean): void  [API 1+]
+ setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener): void  [API 1+]
+ setStateDescription(CharSequence): void  [API 1+]
+ toggle(): void  [API 1+]
+ verifyDrawable(Drawable): boolean  [API 1+]
```

## CompoundButton.OnCheckedChangeListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCheckedChanged(CompoundButton, boolean): void  [API 1+]
```

## CursorAdapter — framework; API 1+ (21 +, 0 *, 1 ~, 2 -)

```
+ CursorAdapter(Context, Cursor)  [API 1+, deprecated in 15]
+ CursorAdapter(Context, Cursor, boolean)  [API 1+]
+ CursorAdapter(Context, Cursor, int)  [API 11+]
+ bindView(View, Context, Cursor): void  [API 1+]
+ changeCursor(Cursor): void  [API 1+]
+ convertToString(Cursor): CharSequence  [API 1+]
+ getCount(): int  [API 1+]
+ getCursor(): Cursor  [API 1+]
+ getDropDownView(int, View, ViewGroup): View  [API 1+]
- getDropDownViewTheme(): Resources.Theme  [API 1+]
+ getFilter(): Filter  [API 1+]
+ getFilterQueryProvider(): FilterQueryProvider  [API 1+]
+ getItem(int): Object  [API 1+]
+ getItemId(int): long  [API 1+]
+ getView(int, View, ViewGroup): View  [API 1+]
~ hasStableIds(): boolean  [API 1+]
+ init(Context, Cursor, boolean): void  [API 1+, deprecated in 15]
+ newDropDownView(Context, Cursor, ViewGroup): View  [API 1+]
+ newView(Context, Cursor, ViewGroup): View  [API 1+]
+ onContentChanged(): void  [API 3+]
+ runQueryOnBackgroundThread(CharSequence): Cursor  [API 1+]
- setDropDownViewTheme(Resources.Theme): void  [API 1+]
+ setFilterQueryProvider(FilterQueryProvider): void  [API 1+]
+ swapCursor(Cursor): Cursor  [API 11+]
```

## CursorTreeAdapter — declaration; API 1+ (6 +, 0 *, 0 ~, 24 -)

```
- CursorTreeAdapter(Cursor, Context)  [API 1+]
- CursorTreeAdapter(Cursor, Context, boolean)  [API 1+]
+ bindChildView(View, Context, Cursor, boolean): void  [API 1+]
+ bindGroupView(View, Context, Cursor, boolean): void  [API 1+]
- changeCursor(Cursor): void  [API 1+]
- convertToString(Cursor): String  [API 1+]
- getChild(int, int): Cursor  [API 1+]
- getChildId(int, int): long  [API 1+]
- getChildView(int, int, boolean, View, ViewGroup): View  [API 1+]
- getChildrenCount(int): int  [API 1+]
+ getChildrenCursor(Cursor): Cursor  [API 1+]
- getCursor(): Cursor  [API 1+]
- getFilter(): Filter  [API 1+]
- getFilterQueryProvider(): FilterQueryProvider  [API 1+]
- getGroup(int): Cursor  [API 1+]
- getGroupCount(): int  [API 1+]
- getGroupId(int): long  [API 1+]
- getGroupView(int, boolean, View, ViewGroup): View  [API 1+]
- hasStableIds(): boolean  [API 1+]
- isChildSelectable(int, int): boolean  [API 1+]
+ newChildView(Context, Cursor, boolean, ViewGroup): View  [API 1+]
+ newGroupView(Context, Cursor, boolean, ViewGroup): View  [API 1+]
- notifyDataSetChanged(): void  [API 1+]
- notifyDataSetChanged(boolean): void  [API 1+]
- notifyDataSetInvalidated(): void  [API 1+]
+ onGroupCollapsed(int): void  [API 1+]
- runQueryOnBackgroundThread(CharSequence): Cursor  [API 1+]
- setChildrenCursor(int, Cursor): void  [API 1+]
- setFilterQueryProvider(FilterQueryProvider): void  [API 1+]
- setGroupCursor(Cursor): void  [API 1+]
```

## DatePicker — framework; API 1+ (25 +, 0 *, 7 ~, 0 -)

```
+ DatePicker(Context)  [API 1+]
+ DatePicker(Context, AttributeSet)  [API 1+]
+ DatePicker(Context, AttributeSet, int)  [API 1+]
+ DatePicker(Context, AttributeSet, int, int)  [API 21+]
~ autofill(AutofillValue): void  [API 1+]
+ dispatchProvideAutofillStructure(ViewStructure, int): void  [API 1+]
+ dispatchRestoreInstanceState(SparseArray): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
~ getAutofillType(): int  [API 1+]
~ getAutofillValue(): AutofillValue  [API 1+]
+ getCalendarView(): CalendarView  [API 12+, deprecated in 24]
~ getCalendarViewShown(): boolean  [API 11+, deprecated in 24]
+ getDayOfMonth(): int  [API 1+]
+ getFirstDayOfWeek(): int  [API 21+]
+ getMaxDate(): long  [API 11+]
+ getMinDate(): long  [API 11+]
+ getMonth(): int  [API 1+]
~ getSpinnersShown(): boolean  [API 11+, deprecated in 24]
+ getYear(): int  [API 1+]
+ init(int, int, int, DatePicker.OnDateChangedListener): void  [API 1+]
+ isEnabled(): boolean  [API 1+]
+ onConfigurationChanged(Configuration): void  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
~ setCalendarViewShown(boolean): void  [API 11+, deprecated in 24]
+ setEnabled(boolean): void  [API 1+]
+ setFirstDayOfWeek(int): void  [API 21+]
+ setMaxDate(long): void  [API 11+]
+ setMinDate(long): void  [API 11+]
+ setOnDateChangedListener(DatePicker.OnDateChangedListener): void  [API 26+]
~ setSpinnersShown(boolean): void  [API 11+, deprecated in 24]
+ updateDate(int, int, int): void  [API 1+]
```

## DatePicker.OnDateChangedListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDateChanged(DatePicker, int, int, int): void  [API 1+]
```

## DialerFilter — declaration; API 1+, deprecated in 26 (0 +, 0 *, 0 ~, 19 -)

```
- DialerFilter(Context)  [API 1+]
- DialerFilter(Context, AttributeSet)  [API 1+]
- append(String): void  [API 1+]
- clearText(): void  [API 1+]
- getDigits(): CharSequence  [API 1+]
- getFilterText(): CharSequence  [API 1+]
- getLetters(): CharSequence  [API 1+]
- getMode(): int  [API 1+]
- isQwertyKeyboard(): boolean  [API 1+]
- onFinishInflate(): void  [API 1+]
- onFocusChanged(boolean, int, Rect): void  [API 1+]
- onKeyDown(int, KeyEvent): boolean  [API 1+]
- onKeyUp(int, KeyEvent): boolean  [API 1+]
- onModeChange(int, int): void  [API 1+]
- removeFilterWatcher(TextWatcher): void  [API 1+]
- setDigitsWatcher(TextWatcher): void  [API 1+]
- setFilterWatcher(TextWatcher): void  [API 1+]
- setLettersWatcher(TextWatcher): void  [API 1+]
- setMode(int): void  [API 1+]
```

## DigitalClock — framework; API 1+, deprecated in 17 (5 +, 0 *, 0 ~, 0 -)

```
+ DigitalClock(Context)  [API 1+]
+ DigitalClock(Context, AttributeSet)  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
```

## EdgeEffect — framework; API 14+ (15 +, 0 *, 0 ~, 2 -)

```
- DEFAULT_BLEND_MODE [field]  [API 29+]
+ EdgeEffect(Context)  [API 14+]
+ EdgeEffect(Context, AttributeSet)  [API 31+]
+ draw(Canvas): boolean  [API 14+]
+ finish(): void  [API 14+]
- getBlendMode(): BlendMode  [API 29+]
+ getColor(): int  [API 21+]
+ getDistance(): float  [API 31+]
+ getMaxHeight(): int  [API 21+]
+ isFinished(): boolean  [API 14+]
+ onAbsorb(int): void  [API 14+]
+ onPull(float): void  [API 14+]
+ onPull(float, float): void  [API 21+]
+ onPullDistance(float, float): float  [API 31+]
+ onRelease(): void  [API 14+]
- setBlendMode(BlendMode): void  [API 29+]
+ setColor(int): void  [API 21+]
+ setSize(int, int): void  [API 14+]
```

## EditText — framework; API 1+ (13 +, 0 *, 3 ~, 3 -)

```
+ EditText(Context)  [API 1+]
+ EditText(Context, AttributeSet)  [API 1+]
+ EditText(Context, AttributeSet, int)  [API 1+]
- EditText(Context, AttributeSet, int, int)  [API 21+]
+ extendSelection(int): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
~ getDefaultEditable(): boolean  [API 1+]
~ getDefaultMovementMethod(): MovementMethod  [API 1+]
~ getFreezesText(): boolean  [API 1+]
+ getText(): Editable  [API 1+]
- isStyleShortcutEnabled(): boolean  [API 34+]
+ onKeyShortcut(int, KeyEvent): boolean  [API 1+]
+ onTextContextMenuItem(int): boolean  [API 1+]
+ selectAll(): void  [API 1+]
+ setEllipsize(TextUtils.TruncateAt): void  [API 1+]
+ setSelection(int): void  [API 1+]
+ setSelection(int, int): void  [API 1+]
- setStyleShortcutsEnabled(boolean): void  [API 34+]
+ setText(CharSequence, TextView.BufferType): void  [API 1+]
```

## ExpandableListAdapter — framework; API 1+ (18 +, 0 *, 0 ~, 0 -)

```
+ areAllItemsEnabled(): boolean  [API 1+]
+ getChild(int, int): Object  [API 1+]
+ getChildId(int, int): long  [API 1+]
+ getChildView(int, int, boolean, View, ViewGroup): View  [API 1+]
+ getChildrenCount(int): int  [API 1+]
+ getCombinedChildId(long, long): long  [API 1+]
+ getCombinedGroupId(long): long  [API 1+]
+ getGroup(int): Object  [API 1+]
+ getGroupCount(): int  [API 1+]
+ getGroupId(int): long  [API 1+]
+ getGroupView(int, boolean, View, ViewGroup): View  [API 1+]
+ hasStableIds(): boolean  [API 1+]
+ isChildSelectable(int, int): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ onGroupCollapsed(int): void  [API 1+]
+ onGroupExpanded(int): void  [API 1+]
+ registerDataSetObserver(DataSetObserver): void  [API 1+]
+ unregisterDataSetObserver(DataSetObserver): void  [API 1+]
```

## ExpandableListView — framework; API 1+ (36 +, 0 *, 4 ~, 2 -)

```
+ ExpandableListView(Context)  [API 1+]
+ ExpandableListView(Context, AttributeSet)  [API 1+]
+ ExpandableListView(Context, AttributeSet, int)  [API 1+]
- ExpandableListView(Context, AttributeSet, int, int)  [API 21+]
+ collapseGroup(int): boolean  [API 1+]
+ dispatchDraw(Canvas): void  [API 1+]
+ expandGroup(int): boolean  [API 1+]
+ expandGroup(int, boolean): boolean  [API 14+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAdapter(): ListAdapter  [API 1+]
+ getExpandableListAdapter(): ExpandableListAdapter  [API 1+]
+ getExpandableListPosition(int): long  [API 1+]
+ getFlatListPosition(long): int  [API 1+]
+ getPackedPositionChild(long): int [static]  [API 1+]
+ getPackedPositionForChild(int, int): long [static]  [API 1+]
+ getPackedPositionForGroup(int): long [static]  [API 1+]
+ getPackedPositionGroup(long): int [static]  [API 1+]
+ getPackedPositionType(long): int [static]  [API 1+]
+ getSelectedId(): long  [API 1+]
+ getSelectedPosition(): long  [API 1+]
+ isGroupExpanded(int): boolean  [API 1+]
+ onInitializeAccessibilityNodeInfoForItem(View, int, AccessibilityNodeInfo): void  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onRtlPropertiesChanged(int): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ performItemClick(View, int, long): boolean  [API 1+]
+ setAdapter(ExpandableListAdapter): void  [API 1+]
~ setAdapter(ListAdapter): void  [API 1+]
~ setChildDivider(Drawable): void  [API 1+]
~ setChildIndicator(Drawable): void  [API 1+]
~ setChildIndicatorBounds(int, int): void  [API 1+]
- setChildIndicatorBoundsRelative(int, int): void  [API 18+]
+ setGroupIndicator(Drawable): void  [API 1+]
+ setIndicatorBounds(int, int): void  [API 1+]
+ setIndicatorBoundsRelative(int, int): void  [API 18+]
+ setOnChildClickListener(ExpandableListView.OnChildClickListener): void  [API 1+]
+ setOnGroupClickListener(ExpandableListView.OnGroupClickListener): void  [API 1+]
+ setOnGroupCollapseListener(ExpandableListView.OnGroupCollapseListener): void  [API 1+]
+ setOnGroupExpandListener(ExpandableListView.OnGroupExpandListener): void  [API 1+]
+ setOnItemClickListener(AdapterView.OnItemClickListener): void  [API 1+]
+ setSelectedChild(int, int, boolean): boolean  [API 1+]
+ setSelectedGroup(int): void  [API 1+]
```

## ExpandableListView.ExpandableListContextMenuInfo — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ExpandableListContextMenuInfo(View, long, long)  [API 1+]
+ id [field]  [API 1+]
+ packedPosition [field]  [API 1+]
+ targetView [field]  [API 1+]
```

## ExpandableListView.OnChildClickListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onChildClick(ExpandableListView, View, int, int, long): boolean  [API 1+]
```

## ExpandableListView.OnGroupClickListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGroupClick(ExpandableListView, View, int, long): boolean  [API 1+]
```

## ExpandableListView.OnGroupCollapseListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGroupCollapse(int): void  [API 1+]
```

## ExpandableListView.OnGroupExpandListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGroupExpand(int): void  [API 1+]
```

## Filter — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ Filter()  [API 1+]
+ convertResultToString(Object): CharSequence  [API 1+]
+ filter(CharSequence): void  [API 1+]
+ filter(CharSequence, Filter.FilterListener): void  [API 1+]
+ performFiltering(CharSequence): Filter.FilterResults  [API 1+]
+ publishResults(CharSequence, Filter.FilterResults): void  [API 1+]
```

## Filter.FilterListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onFilterComplete(int): void  [API 1+]
```

## Filter.FilterResults — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ FilterResults()  [API 1+]
+ count [field]  [API 1+]
+ values [field]  [API 1+]
```

## FilterQueryProvider — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ runQuery(CharSequence): Cursor  [API 1+]
```

## Filterable — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getFilter(): Filter  [API 1+]
```

## FrameLayout — framework; API 1+ (21 +, 0 *, 1 ~, 1 -)

```
+ FrameLayout(Context)  [API 1+]
+ FrameLayout(Context, AttributeSet)  [API 1+]
+ FrameLayout(Context, AttributeSet, int)  [API 1+]
+ FrameLayout(Context, AttributeSet, int, int)  [API 21+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
+ generateDefaultLayoutParams(): FrameLayout.LayoutParams  [API 1+]
+ generateLayoutParams(AttributeSet): FrameLayout.LayoutParams  [API 1+]
+ generateLayoutParams(ViewGroup.LayoutParams): ViewGroup.LayoutParams  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getConsiderGoneChildrenWhenMeasuring(): boolean  [API 1+, deprecated in 15]
+ getForeground(): Drawable  [API 1-22, removed in 23]
+ getForegroundGravity(): int  [API 16-22, removed in 23]
+ getForegroundTintList(): ColorStateList  [API 21-22, removed in 23]
+ getForegroundTintMode(): PorterDuff.Mode  [API 21-22, removed in 23]
- getMeasureAllChildren(): boolean  [API 14+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ setForeground(Drawable): void  [API 1-22, removed in 23]
+ setForegroundGravity(int): void  [API 1+]
+ setForegroundTintList(ColorStateList): void  [API 21-22, removed in 23]
+ setForegroundTintMode(PorterDuff.Mode): void  [API 21-22, removed in 23]
+ setMeasureAllChildren(boolean): void  [API 1+]
~ shouldDelayChildPressedState(): boolean  [API 1+]
```

## FrameLayout.LayoutParams — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ LayoutParams(Context, AttributeSet)  [API 1+]
+ LayoutParams(FrameLayout.LayoutParams)  [API 19+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 1+]
+ LayoutParams(ViewGroup.MarginLayoutParams)  [API 1+]
+ LayoutParams(int, int)  [API 1+]
+ LayoutParams(int, int, int)  [API 1+]
+ gravity [field]  [API 1+]
```

## Gallery — declaration; API 1+, deprecated in 16 (0 +, 0 *, 0 ~, 39 -)

```
- Gallery(Context)  [API 1+]
- Gallery(Context, AttributeSet)  [API 1+]
- Gallery(Context, AttributeSet, int)  [API 1+]
- Gallery(Context, AttributeSet, int, int)  [API 21+, deprecated in 21]
- checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
- computeHorizontalScrollExtent(): int  [API 1+]
- computeHorizontalScrollOffset(): int  [API 1+]
- computeHorizontalScrollRange(): int  [API 1+]
- dispatchKeyEvent(KeyEvent): boolean  [API 1+]
- dispatchSetPressed(boolean): void  [API 1+]
- dispatchSetSelected(boolean): void  [API 1+]
- generateDefaultLayoutParams(): ViewGroup.LayoutParams  [API 1+]
- generateLayoutParams(AttributeSet): ViewGroup.LayoutParams  [API 1+]
- generateLayoutParams(ViewGroup.LayoutParams): ViewGroup.LayoutParams  [API 1+]
- getAccessibilityClassName(): CharSequence  [API 1+]
- getChildDrawingOrder(int, int): int  [API 1+]
- getChildStaticTransformation(View, Transformation): boolean  [API 1+]
- getContextMenuInfo(): ContextMenu.ContextMenuInfo  [API 1+]
- onAttachedToWindow(): void  [API 1+]
- onDown(MotionEvent): boolean  [API 1+]
- onFling(MotionEvent, MotionEvent, float, float): boolean  [API 1+]
- onFocusChanged(boolean, int, Rect): void  [API 1+]
- onKeyDown(int, KeyEvent): boolean  [API 1+]
- onKeyUp(int, KeyEvent): boolean  [API 1+]
- onLayout(boolean, int, int, int, int): void  [API 1+]
- onLongPress(MotionEvent): void  [API 1+]
- onScroll(MotionEvent, MotionEvent, float, float): boolean  [API 1+]
- onShowPress(MotionEvent): void  [API 1+]
- onSingleTapUp(MotionEvent): boolean  [API 1+]
- onTouchEvent(MotionEvent): boolean  [API 1+]
- setAnimationDuration(int): void  [API 1+]
- setCallbackDuringFling(boolean): void  [API 1+]
- setGravity(int): void  [API 1+]
- setSpacing(int): void  [API 1+]
- setUnselectedAlpha(float): void  [API 1+]
- showContextMenu(): boolean  [API 1+]
- showContextMenu(float, float): boolean  [API 1+]
- showContextMenuForChild(View): boolean  [API 1+]
- showContextMenuForChild(View, float, float): boolean  [API 1+]
```

## Gallery.LayoutParams — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 3 -)

```
- LayoutParams(Context, AttributeSet)  [API 1+]
- LayoutParams(ViewGroup.LayoutParams)  [API 1+]
- LayoutParams(int, int)  [API 1+]
```

## GridLayout — framework; API 14+ (36 +, 0 *, 0 ~, 0 -)

```
+ BASELINE [field]  [API 14+]
+ BOTTOM [field]  [API 14+]
+ CENTER [field]  [API 14+]
+ END [field]  [API 16+]
+ FILL [field]  [API 14+]
+ GridLayout(Context)  [API 14+]
+ GridLayout(Context, AttributeSet)  [API 14+]
+ GridLayout(Context, AttributeSet, int)  [API 14+]
+ GridLayout(Context, AttributeSet, int, int)  [API 21+]
+ LEFT [field]  [API 14+]
+ RIGHT [field]  [API 14+]
+ START [field]  [API 16+]
+ TOP [field]  [API 14+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 14+]
+ generateDefaultLayoutParams(): GridLayout.LayoutParams  [API 14+]
+ generateLayoutParams(AttributeSet): GridLayout.LayoutParams  [API 14+]
+ generateLayoutParams(ViewGroup.LayoutParams): GridLayout.LayoutParams  [API 14+]
+ getAccessibilityClassName(): CharSequence  [API 14+]
+ getAlignmentMode(): int  [API 14+]
+ getColumnCount(): int  [API 14+]
+ getOrientation(): int  [API 14+]
+ getRowCount(): int  [API 14+]
+ getUseDefaultMargins(): boolean  [API 14+]
+ isColumnOrderPreserved(): boolean  [API 14+]
+ isRowOrderPreserved(): boolean  [API 14+]
+ onLayout(boolean, int, int, int, int): void  [API 14+]
+ onMeasure(int, int): void  [API 14+]
+ onViewAdded(View): void  [API 14+]
+ onViewRemoved(View): void  [API 14+]
+ requestLayout(): void  [API 14+]
+ setAlignmentMode(int): void  [API 14+]
+ setColumnCount(int): void  [API 14+]
+ setColumnOrderPreserved(boolean): void  [API 14+]
+ setOrientation(int): void  [API 14+]
+ setRowCount(int): void  [API 14+]
+ setRowOrderPreserved(boolean): void  [API 14+]
+ setUseDefaultMargins(boolean): void  [API 14+]
+ spec(int): GridLayout.Spec [static]  [API 14+]
+ spec(int, GridLayout.Alignment): GridLayout.Spec [static]  [API 14+]
+ spec(int, GridLayout.Alignment, float): GridLayout.Spec [static]  [API 21+]
+ spec(int, float): GridLayout.Spec [static]  [API 21+]
+ spec(int, int): GridLayout.Spec [static]  [API 14+]
+ spec(int, int, GridLayout.Alignment): GridLayout.Spec [static]  [API 14+]
+ spec(int, int, GridLayout.Alignment, float): GridLayout.Spec [static]  [API 21+]
+ spec(int, int, float): GridLayout.Spec [static]  [API 21+]
```

## GridLayout.Alignment — framework; API 14+ (0 +, 0 *, 0 ~, 0 -)

## GridLayout.LayoutParams — framework; API 14+ (10 +, 0 *, 0 ~, 0 -)

```
+ LayoutParams()  [API 14+]
+ LayoutParams(Context, AttributeSet)  [API 14+]
+ LayoutParams(GridLayout.LayoutParams)  [API 14+]
+ LayoutParams(GridLayout.Spec, GridLayout.Spec)  [API 14+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 14+]
+ LayoutParams(ViewGroup.MarginLayoutParams)  [API 14+]
+ columnSpec [field]  [API 14+]
+ equals(Object): boolean  [API 14+]
+ hashCode(): int  [API 14+]
+ rowSpec [field]  [API 14+]
+ setBaseAttributes(TypedArray, int, int): void  [API 14+]
+ setGravity(int): void  [API 14+]
```

## GridLayout.Spec — framework; API 14+ (2 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 14+]
+ hashCode(): int  [API 14+]
```

## GridView — framework; API 1+ (29 +, 0 *, 1 ~, 6 -)

```
+ GridView(Context)  [API 1+]
+ GridView(Context, AttributeSet)  [API 1+]
+ GridView(Context, AttributeSet, int)  [API 1+]
- GridView(Context, AttributeSet, int, int)  [API 21+]
+ attachLayoutAnimationParameters(View, ViewGroup.LayoutParams, int, int): void  [API 1+]
+ computeVerticalScrollExtent(): int  [API 1+]
+ computeVerticalScrollOffset(): int  [API 1+]
+ computeVerticalScrollRange(): int  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAdapter(): ListAdapter  [API 1+]
- getColumnWidth(): int  [API 16+]
- getGravity(): int  [API 16+]
+ getHorizontalSpacing(): int  [API 16+]
+ getNumColumns(): int  [API 11+]
- getRequestedColumnWidth(): int  [API 16+]
- getRequestedHorizontalSpacing(): int  [API 16+]
+ getStretchMode(): int  [API 1+]
+ getVerticalSpacing(): int  [API 16+]
+ layoutChildren(): void  [API 1+]
+ onFocusChanged(boolean, int, Rect): void  [API 1+]
+ onInitializeAccessibilityNodeInfoForItem(View, int, AccessibilityNodeInfo): void  [API 1+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ setAdapter(ListAdapter): void  [API 1+]
+ setColumnWidth(int): void  [API 1+]
+ setGravity(int): void  [API 1+]
+ setHorizontalSpacing(int): void  [API 1+]
+ setNumColumns(int): void  [API 1+]
~ setRemoteViewsAdapter(Intent): void  [API 1+]
+ setSelection(int): void  [API 1+]
+ setStretchMode(int): void  [API 1+]
+ setVerticalSpacing(int): void  [API 1+]
- smoothScrollByOffset(int): void  [API 11+]
+ smoothScrollToPosition(int): void  [API 1+]
```

## HeaderViewListAdapter — framework; API 1+ (19 +, 0 *, 0 ~, 0 -)

```
+ HeaderViewListAdapter(ArrayList, ArrayList, ListAdapter)  [API 1+]
+ areAllItemsEnabled(): boolean  [API 1+]
+ getCount(): int  [API 1+]
+ getFilter(): Filter  [API 1+]
+ getFootersCount(): int  [API 1+]
+ getHeadersCount(): int  [API 1+]
+ getItem(int): Object  [API 1+]
+ getItemId(int): long  [API 1+]
+ getItemViewType(int): int  [API 1+]
+ getView(int, View, ViewGroup): View  [API 1+]
+ getViewTypeCount(): int  [API 1+]
+ getWrappedAdapter(): ListAdapter  [API 1+]
+ hasStableIds(): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ isEnabled(int): boolean  [API 1+]
+ registerDataSetObserver(DataSetObserver): void  [API 1+]
+ removeFooter(View): boolean  [API 1+]
+ removeHeader(View): boolean  [API 1+]
+ unregisterDataSetObserver(DataSetObserver): void  [API 1+]
```

## HeterogeneousExpandableList — framework; API 8+ (4 +, 0 *, 0 ~, 0 -)

```
+ getChildType(int, int): int  [API 8+]
+ getChildTypeCount(): int  [API 8+]
+ getGroupType(int): int  [API 8+]
+ getGroupTypeCount(): int  [API 8+]
```

## HorizontalScrollView — framework; API 3+ (43 +, 0 *, 2 ~, 7 -)

```
+ HorizontalScrollView(Context)  [API 3+]
+ HorizontalScrollView(Context, AttributeSet)  [API 3+]
+ HorizontalScrollView(Context, AttributeSet, int)  [API 3+]
- HorizontalScrollView(Context, AttributeSet, int, int)  [API 21+]
+ addView(View): void  [API 3+]
+ addView(View, ViewGroup.LayoutParams): void  [API 3+]
+ addView(View, int): void  [API 3+]
+ addView(View, int, ViewGroup.LayoutParams): void  [API 3+]
+ arrowScroll(int): boolean  [API 3+]
+ computeHorizontalScrollOffset(): int  [API 3+]
+ computeHorizontalScrollRange(): int  [API 3+]
+ computeScroll(): void  [API 3+]
+ computeScrollDeltaToGetChildRectOnScreen(Rect): int  [API 3+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 3+]
+ draw(Canvas): void  [API 3+]
+ executeKeyEvent(KeyEvent): boolean  [API 3+]
+ fling(int): void  [API 3+]
+ fullScroll(int): boolean  [API 3+]
+ getAccessibilityClassName(): CharSequence  [API 3+]
- getLeftEdgeEffectColor(): int  [API 29+]
+ getLeftFadingEdgeStrength(): float  [API 3+]
+ getMaxScrollAmount(): int  [API 3+]
- getRightEdgeEffectColor(): int  [API 29+]
+ getRightFadingEdgeStrength(): float  [API 3+]
+ isFillViewport(): boolean  [API 3+]
- isSmoothScrollingEnabled(): boolean  [API 3+]
+ measureChild(View, int, int): void  [API 3+]
+ measureChildWithMargins(View, int, int, int, int): void  [API 3+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 3+]
+ onInterceptTouchEvent(MotionEvent): boolean  [API 3+]
+ onLayout(boolean, int, int, int, int): void  [API 3+]
+ onMeasure(int, int): void  [API 3+]
+ onOverScrolled(int, int, boolean, boolean): void  [API 3+]
+ onRequestFocusInDescendants(int, Rect): boolean  [API 3+]
+ onRestoreInstanceState(Parcelable): void  [API 3+]
+ onSaveInstanceState(): Parcelable  [API 3+]
+ onSizeChanged(int, int, int, int): void  [API 3+]
+ onTouchEvent(MotionEvent): boolean  [API 3+]
+ pageScroll(int): boolean  [API 3+]
+ requestChildFocus(View, View): void  [API 3+]
~ requestChildRectangleOnScreen(View, Rect, boolean): boolean  [API 3+]
+ requestDisallowInterceptTouchEvent(boolean): void  [API 3+]
+ requestLayout(): void  [API 3+]
+ scrollTo(int, int): void  [API 3+]
- setEdgeEffectColor(int): void  [API 29+]
+ setFillViewport(boolean): void  [API 3+]
- setLeftEdgeEffectColor(int): void  [API 29+]
- setRightEdgeEffectColor(int): void  [API 29+]
+ setSmoothScrollingEnabled(boolean): void  [API 3+]
~ shouldDelayChildPressedState(): boolean  [API 3+]
+ smoothScrollBy(int, int): void  [API 3+]
+ smoothScrollTo(int, int): void  [API 3+]
```

## ImageButton — framework; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ ImageButton(Context)  [API 1+]
+ ImageButton(Context, AttributeSet)  [API 1+]
+ ImageButton(Context, AttributeSet, int)  [API 1+]
+ ImageButton(Context, AttributeSet, int, int)  [API 21+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ onResolvePointerIcon(MotionEvent, int): PointerIcon  [API 1+]
+ onSetAlpha(int): boolean  [API 1+]
```

## ImageSwitcher — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ ImageSwitcher(Context)  [API 1+]
+ ImageSwitcher(Context, AttributeSet)  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ setImageDrawable(Drawable): void  [API 1+]
+ setImageResource(int): void  [API 1+]
+ setImageURI(Uri): void  [API 1+]
```

## ImageView — framework; API 1+ (47 +, 0 *, 2 ~, 12 -)

```
+ ImageView(Context)  [API 1+]
+ ImageView(Context, AttributeSet)  [API 1+]
+ ImageView(Context, AttributeSet, int)  [API 1+]
- ImageView(Context, AttributeSet, int, int)  [API 21+]
- animateTransform(Matrix): void  [API 29+]
+ clearColorFilter(): void  [API 1+]
+ drawableHotspotChanged(float, float): void  [API 1+]
+ drawableStateChanged(): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAdjustViewBounds(): boolean  [API 16+]
+ getBaseline(): int  [API 1+]
- getBaselineAlignBottom(): boolean  [API 11+]
+ getColorFilter(): ColorFilter  [API 16+]
+ getCropToPadding(): boolean  [API 16+]
+ getDrawable(): Drawable  [API 1+]
+ getImageAlpha(): int  [API 16+]
+ getImageMatrix(): Matrix  [API 1+]
- getImageTintBlendMode(): BlendMode  [API 29+]
- getImageTintList(): ColorStateList  [API 21+]
- getImageTintMode(): PorterDuff.Mode  [API 21+]
- getMaxHeight(): int  [API 16+]
- getMaxWidth(): int  [API 16+]
+ getScaleType(): ImageView.ScaleType  [API 1+]
~ hasOverlappingRendering(): boolean  [API 1+]
+ invalidateDrawable(Drawable): void  [API 1+]
+ isOpaque(): boolean  [API 1+]
~ jumpDrawablesToCurrentState(): void  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onCreateDrawableState(int): int[]  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onDraw(Canvas): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onRtlPropertiesChanged(int): void  [API 1+]
+ onVisibilityAggregated(boolean): void  [API 1+]
+ setAdjustViewBounds(boolean): void  [API 1+]
+ setAlpha(int): void  [API 1+, deprecated in 16]
+ setBaseline(int): void  [API 11+]
+ setBaselineAlignBottom(boolean): void  [API 11+]
+ setColorFilter(ColorFilter): void  [API 1+]
+ setColorFilter(int): void  [API 8+]
+ setColorFilter(int, PorterDuff.Mode): void  [API 1+]
+ setCropToPadding(boolean): void  [API 16+]
+ setFrame(int, int, int, int): boolean  [API 1+]
+ setImageAlpha(int): void  [API 16+]
+ setImageBitmap(Bitmap): void  [API 1+]
+ setImageDrawable(Drawable): void  [API 1+]
- setImageIcon(Icon): void  [API 23+]
+ setImageLevel(int): void  [API 1+]
+ setImageMatrix(Matrix): void  [API 1+]
+ setImageResource(int): void  [API 1+]
+ setImageState(int[], boolean): void  [API 1+]
- setImageTintBlendMode(BlendMode): void  [API 29+]
- setImageTintList(ColorStateList): void  [API 21+]
- setImageTintMode(PorterDuff.Mode): void  [API 21+]
+ setImageURI(Uri): void  [API 1+]
+ setMaxHeight(int): void  [API 1+]
+ setMaxWidth(int): void  [API 1+]
+ setScaleType(ImageView.ScaleType): void  [API 1+]
+ setSelected(boolean): void  [API 1+]
+ setVisibility(int): void  [API 1+]
+ verifyDrawable(Drawable): boolean  [API 1+]
```

## ImageView.ScaleType — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CENTER [field]  [API 1+]
+ CENTER_CROP [field]  [API 1+]
+ CENTER_INSIDE [field]  [API 1+]
+ FIT_CENTER [field]  [API 1+]
+ FIT_END [field]  [API 1+]
+ FIT_START [field]  [API 1+]
+ FIT_XY [field]  [API 1+]
+ MATRIX [field]  [API 1+]
+ valueOf(String): ImageView.ScaleType [static]  [API 1+]
+ values(): ImageView.ScaleType[] [static]  [API 1+]
```

## LinearLayout — framework; API 1+ (33 +, 0 *, 1 ~, 1 -)

```
+ LinearLayout(Context)  [API 1+]
+ LinearLayout(Context, AttributeSet)  [API 1+]
+ LinearLayout(Context, AttributeSet, int)  [API 11+]
- LinearLayout(Context, AttributeSet, int, int)  [API 21+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
+ generateDefaultLayoutParams(): LinearLayout.LayoutParams  [API 1+]
+ generateLayoutParams(AttributeSet): LinearLayout.LayoutParams  [API 1+]
+ generateLayoutParams(ViewGroup.LayoutParams): LinearLayout.LayoutParams  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getBaseline(): int  [API 1+]
+ getBaselineAlignedChildIndex(): int  [API 1+]
+ getDividerDrawable(): Drawable  [API 16+]
+ getDividerPadding(): int  [API 14+]
+ getGravity(): int  [API 24+]
+ getOrientation(): int  [API 1+]
+ getShowDividers(): int  [API 11+]
+ getWeightSum(): float  [API 1+]
+ isBaselineAligned(): boolean  [API 1+]
+ isMeasureWithLargestChildEnabled(): boolean  [API 11+]
+ onDraw(Canvas): void  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onRtlPropertiesChanged(int): void  [API 1+]
+ setBaselineAligned(boolean): void  [API 1+]
+ setBaselineAlignedChildIndex(int): void  [API 1+]
+ setDividerDrawable(Drawable): void  [API 11+]
+ setDividerPadding(int): void  [API 14+]
+ setGravity(int): void  [API 1+]
+ setHorizontalGravity(int): void  [API 1+]
+ setMeasureWithLargestChildEnabled(boolean): void  [API 11+]
+ setOrientation(int): void  [API 1+]
+ setShowDividers(int): void  [API 11+]
+ setVerticalGravity(int): void  [API 1+]
+ setWeightSum(float): void  [API 1+]
~ shouldDelayChildPressedState(): boolean  [API 1+]
```

## LinearLayout.LayoutParams — framework; API 1+ (6 +, 0 *, 0 ~, 1 -)

```
+ LayoutParams(Context, AttributeSet)  [API 1+]
+ LayoutParams(LinearLayout.LayoutParams)  [API 19+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 1+]
+ LayoutParams(ViewGroup.MarginLayoutParams)  [API 1+]
+ LayoutParams(int, int)  [API 1+]
+ LayoutParams(int, int, float)  [API 1+]
- debug(String): String  [API 1+]
+ gravity [field]  [API 1+]
+ weight [field]  [API 1+]
```

## ListAdapter — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ areAllItemsEnabled(): boolean  [API 1+]
+ isEnabled(int): boolean  [API 1+]
```

## ListPopupWindow — framework; API 11+ (47 +, 0 *, 3 ~, 4 -)

```
+ ListPopupWindow(Context)  [API 11+]
+ ListPopupWindow(Context, AttributeSet)  [API 11+]
+ ListPopupWindow(Context, AttributeSet, int)  [API 11+]
+ ListPopupWindow(Context, AttributeSet, int, int)  [API 11+]
+ clearListSelection(): void  [API 11+]
~ createDragToOpenListener(View): View.OnTouchListener  [API 19+]
+ dismiss(): void  [API 11+]
+ getAnchorView(): View  [API 11+]
+ getAnimationStyle(): int  [API 11+]
+ getBackground(): Drawable  [API 11+]
- getEpicenterBounds(): Rect  [API 29+]
+ getHeight(): int  [API 11+]
+ getHorizontalOffset(): int  [API 11+]
+ getInputMethodMode(): int  [API 11+]
+ getListView(): ListView  [API 11+]
+ getPromptPosition(): int  [API 11+]
+ getSelectedItem(): Object  [API 11+]
+ getSelectedItemId(): long  [API 11+]
+ getSelectedItemPosition(): int  [API 11+]
+ getSelectedView(): View  [API 11+]
+ getSoftInputMode(): int  [API 11+]
+ getVerticalOffset(): int  [API 11+]
+ getWidth(): int  [API 11+]
+ isInputMethodNotNeeded(): boolean  [API 11+]
+ isModal(): boolean  [API 11+]
+ isShowing(): boolean  [API 11+]
- onKeyDown(int, KeyEvent): boolean  [API 11+]
- onKeyPreIme(int, KeyEvent): boolean  [API 11+]
- onKeyUp(int, KeyEvent): boolean  [API 11+]
+ performItemClick(int): boolean  [API 11+]
+ postShow(): void  [API 11+]
+ setAdapter(ListAdapter): void  [API 11+]
+ setAnchorView(View): void  [API 11+]
+ setAnimationStyle(int): void  [API 11+]
+ setBackgroundDrawable(Drawable): void  [API 11+]
+ setContentWidth(int): void  [API 11+]
+ setDropDownGravity(int): void  [API 19+]
~ setEpicenterBounds(Rect): void  [API 29+]
+ setHeight(int): void  [API 11+]
+ setHorizontalOffset(int): void  [API 11+]
+ setInputMethodMode(int): void  [API 11+]
~ setListSelector(Drawable): void  [API 11+]
+ setModal(boolean): void  [API 11+]
+ setOnDismissListener(PopupWindow.OnDismissListener): void  [API 11+]
+ setOnItemClickListener(AdapterView.OnItemClickListener): void  [API 11+]
+ setOnItemSelectedListener(AdapterView.OnItemSelectedListener): void  [API 11+]
+ setPromptPosition(int): void  [API 11+]
+ setPromptView(View): void  [API 11+]
+ setSelection(int): void  [API 11+]
+ setSoftInputMode(int): void  [API 11+]
+ setVerticalOffset(int): void  [API 11+]
+ setWidth(int): void  [API 11+]
+ setWindowLayoutType(int): void  [API 23+]
+ show(): void  [API 11+]
```

## ListView — framework; API 1+ (52 +, 0 *, 2 ~, 10 -)

```
+ ListView(Context)  [API 1+]
+ ListView(Context, AttributeSet)  [API 1+]
+ ListView(Context, AttributeSet, int)  [API 1+]
- ListView(Context, AttributeSet, int, int)  [API 21+]
+ addFooterView(View): void  [API 1+]
+ addFooterView(View, Object, boolean): void  [API 1+]
+ addHeaderView(View): void  [API 1+]
+ addHeaderView(View, Object, boolean): void  [API 1+]
- areFooterDividersEnabled(): boolean  [API 19+]
- areHeaderDividersEnabled(): boolean  [API 19+]
+ canAnimate(): boolean  [API 1+]
+ clearChoices(): void  [API 1-10, removed in 11]
+ dispatchDraw(Canvas): void  [API 1+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 1+]
+ drawChild(Canvas, View, long): boolean  [API 1+]
+ findViewTraversal(int): View  [API 1-25, removed in 26]
+ findViewWithTagTraversal(Object): View  [API 1-25, removed in 26]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAdapter(): ListAdapter  [API 1+]
- getCheckItemIds(): long[]  [API 4+, deprecated in 15]
+ getCheckedItemIds(): long[]  [API 8-10, removed in 11]
+ getCheckedItemPosition(): int  [API 1-10, removed in 11]
+ getCheckedItemPositions(): SparseBooleanArray  [API 1-10, removed in 11]
+ getChoiceMode(): int  [API 1-10, removed in 11]
+ getDivider(): Drawable  [API 1+]
+ getDividerHeight(): int  [API 1+]
+ getFooterViewsCount(): int  [API 1+]
+ getHeaderViewsCount(): int  [API 1+]
+ getItemsCanFocus(): boolean  [API 1+]
- getMaxScrollAmount(): int  [API 1+]
- getOverscrollFooter(): Drawable  [API 9+]
- getOverscrollHeader(): Drawable  [API 9+]
+ isItemChecked(int): boolean  [API 1-10, removed in 11]
+ isOpaque(): boolean  [API 1+]
+ layoutChildren(): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onFinishInflate(): void  [API 1+]
+ onFocusChanged(boolean, int, Rect): void  [API 1+]
+ onInitializeAccessibilityNodeInfoForItem(View, int, AccessibilityNodeInfo): void  [API 1+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onSizeChanged(int, int, int, int): void  [API 1+]
+ removeFooterView(View): boolean  [API 1+]
+ removeHeaderView(View): boolean  [API 1+]
~ requestChildRectangleOnScreen(View, Rect, boolean): boolean  [API 1+]
+ setAdapter(ListAdapter): void  [API 1+]
+ setCacheColorHint(int): void  [API 1+]
+ setChoiceMode(int): void  [API 1-10, removed in 11]
+ setDivider(Drawable): void  [API 1+]
+ setDividerHeight(int): void  [API 1+]
+ setFooterDividersEnabled(boolean): void  [API 3+]
+ setHeaderDividersEnabled(boolean): void  [API 3+]
+ setItemChecked(int, boolean): void  [API 1-10, removed in 11]
+ setItemsCanFocus(boolean): void  [API 1+]
- setOverscrollFooter(Drawable): void  [API 9+]
- setOverscrollHeader(Drawable): void  [API 9+]
~ setRemoteViewsAdapter(Intent): void  [API 1+]
+ setSelection(int): void  [API 1+]
+ setSelectionAfterHeaderView(): void  [API 1+]
+ setSelectionFromTop(int, int): void  [API 1-20, removed in 21]
- smoothScrollByOffset(int): void  [API 11+]
+ smoothScrollToPosition(int): void  [API 1+]
```

## ListView.FixedViewInfo — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ FixedViewInfo(ListView)  [API 1+]
+ data [field]  [API 1+]
+ isSelectable [field]  [API 1+]
+ view [field]  [API 1+]
```

## Magnifier — declaration; API 28+ (0 +, 6 *, 0 ~, 13 -)

```
- Magnifier(View)  [API 28+, deprecated in 29]
- dismiss(): void  [API 28+]
* getCornerRadius(): float  [API 29+]
- getDefaultHorizontalSourceToMagnifierOffset(): int  [API 29+]
- getDefaultVerticalSourceToMagnifierOffset(): int  [API 29+]
* getElevation(): float  [API 29+]
- getHeight(): int  [API 28+]
* getOverlay(): Drawable  [API 29+]
- getPosition(): Point  [API 29+]
- getSourceHeight(): int  [API 29+]
- getSourcePosition(): Point  [API 29+]
- getSourceWidth(): int  [API 29+]
- getWidth(): int  [API 28+]
* getZoom(): float  [API 28+]
* isClippingEnabled(): boolean  [API 29+]
* setZoom(float): void  [API 29+]
- show(float, float): void  [API 28+]
- show(float, float, float, float): void  [API 29+]
- update(): void  [API 28+]
```

## Magnifier.Builder — declaration; API 29+ (0 +, 5 *, 0 ~, 5 -)

```
- Builder(View)  [API 29+]
* build(): Magnifier  [API 29+]
* setClippingEnabled(boolean): Magnifier.Builder  [API 29+]
* setCornerRadius(float): Magnifier.Builder  [API 29+]
- setDefaultSourceToMagnifierOffset(int, int): Magnifier.Builder  [API 29+]
* setElevation(float): Magnifier.Builder  [API 29+]
- setInitialZoom(float): Magnifier.Builder  [API 29+]
* setOverlay(Drawable): Magnifier.Builder  [API 29+]
- setSize(int, int): Magnifier.Builder  [API 29+]
- setSourceBounds(int, int, int, int): Magnifier.Builder  [API 29+]
```

## MediaController — declaration; API 1+ (0 +, 0 *, 0 ~, 16 -)

```
- MediaController(Context)  [API 1+]
- MediaController(Context, AttributeSet)  [API 1+]
- MediaController(Context, boolean)  [API 1+]
- dispatchKeyEvent(KeyEvent): boolean  [API 1+]
- getAccessibilityClassName(): CharSequence  [API 1+]
- hide(): void  [API 1+]
- isShowing(): boolean  [API 1+]
- onFinishInflate(): void  [API 1+]
- onTouchEvent(MotionEvent): boolean  [API 1+]
- onTrackballEvent(MotionEvent): boolean  [API 1+]
- setAnchorView(View): void  [API 1+]
- setEnabled(boolean): void  [API 1+]
- setMediaPlayer(MediaController.MediaPlayerControl): void  [API 1+]
- setPrevNextListeners(View.OnClickListener, View.OnClickListener): void  [API 1+]
- show(): void  [API 1+]
- show(int): void  [API 1+]
```

## MediaController.MediaPlayerControl — declaration; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ canPause(): boolean  [API 5+]
+ canSeekBackward(): boolean  [API 5+]
+ canSeekForward(): boolean  [API 5+]
+ getAudioSessionId(): int  [API 18+]
+ getBufferPercentage(): int  [API 1+]
+ getCurrentPosition(): int  [API 1+]
+ getDuration(): int  [API 1+]
+ isPlaying(): boolean  [API 1+]
+ pause(): void  [API 1+]
+ seekTo(int): void  [API 1+]
+ start(): void  [API 1+]
```

## MultiAutoCompleteTextView — framework; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ MultiAutoCompleteTextView(Context)  [API 1+]
+ MultiAutoCompleteTextView(Context, AttributeSet)  [API 1+]
+ MultiAutoCompleteTextView(Context, AttributeSet, int)  [API 1+]
+ MultiAutoCompleteTextView(Context, AttributeSet, int, int)  [API 21+]
+ enoughToFilter(): boolean  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ performFiltering(CharSequence, int): void  [API 1+]
+ performFiltering(CharSequence, int, int, int): void  [API 1+]
+ performValidation(): void  [API 1+]
+ replaceText(CharSequence): void  [API 1+]
+ setTokenizer(MultiAutoCompleteTextView.Tokenizer): void  [API 1+]
```

## MultiAutoCompleteTextView.CommaTokenizer — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CommaTokenizer()  [API 1+]
+ findTokenEnd(CharSequence, int): int  [API 1+]
+ findTokenStart(CharSequence, int): int  [API 1+]
+ terminateToken(CharSequence): CharSequence  [API 1+]
```

## MultiAutoCompleteTextView.Tokenizer — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ findTokenEnd(CharSequence, int): int  [API 1+]
+ findTokenStart(CharSequence, int): int  [API 1+]
+ terminateToken(CharSequence): CharSequence  [API 1+]
```

## NumberPicker — framework; API 11+ (40 +, 0 *, 8 ~, 0 -)

```
+ NumberPicker(Context)  [API 11+]
+ NumberPicker(Context, AttributeSet)  [API 11+]
+ NumberPicker(Context, AttributeSet, int)  [API 11+]
+ NumberPicker(Context, AttributeSet, int, int)  [API 21+]
~ computeScroll(): void  [API 11+]
+ computeVerticalScrollExtent(): int  [API 11+]
+ computeVerticalScrollOffset(): int  [API 11+]
+ computeVerticalScrollRange(): int  [API 11+]
+ dispatchHoverEvent(MotionEvent): boolean  [API 11+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 11+]
+ dispatchTouchEvent(MotionEvent): boolean  [API 11+]
+ dispatchTrackballEvent(MotionEvent): boolean  [API 11+]
+ drawableStateChanged(): void  [API 11+]
+ getAccessibilityNodeProvider(): AccessibilityNodeProvider  [API 11+]
~ getBottomFadingEdgeStrength(): float  [API 11+]
+ getDisplayedValues(): String[]  [API 11+]
+ getMaxValue(): int  [API 11+]
+ getMinValue(): int  [API 11+]
~ getSelectionDividerHeight(): int  [API 29+]
~ getSolidColor(): int  [API 11+]
+ getTextColor(): int  [API 29+]
+ getTextSize(): float  [API 29+]
~ getTopFadingEdgeStrength(): float  [API 11+]
+ getValue(): int  [API 11+]
+ getWrapSelectorWheel(): boolean  [API 11+]
~ jumpDrawablesToCurrentState(): void  [API 11+]
+ onDetachedFromWindow(): void  [API 11+]
+ onDraw(Canvas): void  [API 11+]
+ onInterceptTouchEvent(MotionEvent): boolean  [API 11+]
+ onLayout(boolean, int, int, int, int): void  [API 11+]
+ onMeasure(int, int): void  [API 11+]
+ onTouchEvent(MotionEvent): boolean  [API 11+]
+ performClick(): boolean  [API 11+]
+ performLongClick(): boolean  [API 11+]
+ scrollBy(int, int): void  [API 11+]
+ setDisplayedValues(String[]): void  [API 11+]
+ setEnabled(boolean): void  [API 11+]
+ setFormatter(NumberPicker.Formatter): void  [API 11+]
+ setMaxValue(int): void  [API 11+]
+ setMinValue(int): void  [API 11+]
~ setOnLongPressUpdateInterval(long): void  [API 11+]
+ setOnScrollListener(NumberPicker.OnScrollListener): void  [API 11+]
+ setOnValueChangedListener(NumberPicker.OnValueChangeListener): void  [API 11+]
~ setSelectionDividerHeight(int): void  [API 29+]
+ setTextColor(int): void  [API 29+]
+ setTextSize(float): void  [API 29+]
+ setValue(int): void  [API 11+]
+ setWrapSelectorWheel(boolean): void  [API 11+]
```

## NumberPicker.Formatter — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ format(int): String  [API 11+]
```

## NumberPicker.OnScrollListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onScrollStateChange(NumberPicker, int): void  [API 11+]
```

## NumberPicker.OnValueChangeListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onValueChange(NumberPicker, int, int): void  [API 11+]
```

## OverScroller — framework; API 9+ (19 +, 0 *, 5 ~, 0 -)

```
+ OverScroller(Context)  [API 9+]
+ OverScroller(Context, Interpolator)  [API 9+]
+ OverScroller(Context, Interpolator, float, float)  [API 9+, deprecated in 26]
+ OverScroller(Context, Interpolator, float, float, boolean)  [API 11+, deprecated in 26]
+ abortAnimation(): void  [API 9+]
+ computeScrollOffset(): boolean  [API 9+]
+ fling(int, int, int, int, int, int, int, int): void  [API 9+]
+ fling(int, int, int, int, int, int, int, int, int, int): void  [API 9+]
+ forceFinished(boolean): void  [API 9+]
+ getCurrVelocity(): float  [API 14+]
+ getCurrX(): int  [API 9+]
+ getCurrY(): int  [API 9+]
+ getFinalX(): int  [API 9+]
+ getFinalY(): int  [API 9+]
+ getStartX(): int  [API 9+]
+ getStartY(): int  [API 9+]
+ isFinished(): boolean  [API 9+]
~ isOverScrolled(): boolean  [API 9+]
~ notifyHorizontalEdgeReached(int, int, int): void  [API 9+]
~ notifyVerticalEdgeReached(int, int, int): void  [API 9+]
~ setFriction(float): void  [API 11+]
~ springBack(int, int, int, int, int, int): boolean  [API 9+]
+ startScroll(int, int, int, int): void  [API 9+]
+ startScroll(int, int, int, int, int): void  [API 9+]
```

## PopupMenu — framework; API 11+ (12 +, 0 *, 2 ~, 0 -)

```
+ PopupMenu(Context, View)  [API 11+]
+ PopupMenu(Context, View, int)  [API 19+]
+ PopupMenu(Context, View, int, int, int)  [API 22+]
+ dismiss(): void  [API 11+]
~ getDragToOpenListener(): View.OnTouchListener  [API 19+]
+ getGravity(): int  [API 23+]
+ getMenu(): Menu  [API 11+]
+ getMenuInflater(): MenuInflater  [API 11+]
+ inflate(int): void  [API 14+]
~ setForceShowIcon(boolean): void  [API 29+]
+ setGravity(int): void  [API 23+]
+ setOnDismissListener(PopupMenu.OnDismissListener): void  [API 14+]
+ setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener): void  [API 11+]
+ show(): void  [API 11+]
```

## PopupMenu.OnDismissListener — framework; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDismiss(PopupMenu): void  [API 14+]
```

## PopupMenu.OnMenuItemClickListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onMenuItemClick(MenuItem): boolean  [API 11+]
```

## PopupWindow — framework; API 1+ (71 +, 0 *, 1 ~, 1 -)

```
+ PopupWindow()  [API 1+]
+ PopupWindow(Context)  [API 1+]
+ PopupWindow(Context, AttributeSet)  [API 1+]
+ PopupWindow(Context, AttributeSet, int)  [API 1+]
+ PopupWindow(Context, AttributeSet, int, int)  [API 11+]
+ PopupWindow(View)  [API 1+]
+ PopupWindow(View, int, int)  [API 1+]
+ PopupWindow(View, int, int, boolean)  [API 1+]
+ PopupWindow(int, int)  [API 1+]
+ dismiss(): void  [API 1+]
+ getAnimationStyle(): int  [API 1+]
+ getBackground(): Drawable  [API 1+]
+ getContentView(): View  [API 1+]
+ getElevation(): float  [API 21+]
+ getEnterTransition(): Transition  [API 24+]
+ getEpicenterBounds(): Rect  [API 29+]
+ getExitTransition(): Transition  [API 24+]
+ getHeight(): int  [API 1+]
+ getInputMethodMode(): int  [API 3+]
+ getMaxAvailableHeight(View): int  [API 1+]
+ getMaxAvailableHeight(View, int): int  [API 3+]
+ getMaxAvailableHeight(View, int, boolean): int  [API 24+]
+ getOverlapAnchor(): boolean  [API 23+]
+ getSoftInputMode(): int  [API 4+]
+ getWidth(): int  [API 1+]
+ getWindowLayoutType(): int  [API 23+]
+ isAboveAnchor(): boolean  [API 3+]
+ isAttachedInDecor(): boolean  [API 22+]
+ isClippedToScreen(): boolean  [API 29+]
+ isClippingEnabled(): boolean  [API 3+]
+ isFocusable(): boolean  [API 1+]
+ isLaidOutInScreen(): boolean  [API 29+]
+ isOutsideTouchable(): boolean  [API 3+]
+ isShowing(): boolean  [API 1+]
+ isSplitTouchEnabled(): boolean  [API 11+]
- isTouchModal(): boolean  [API 29+]
+ isTouchable(): boolean  [API 3+]
+ setAnimationStyle(int): void  [API 1+]
+ setAttachedInDecor(boolean): void  [API 22+]
+ setBackgroundDrawable(Drawable): void  [API 1+]
+ setClippingEnabled(boolean): void  [API 3+]
+ setContentView(View): void  [API 1+]
+ setElevation(float): void  [API 21+]
+ setEnterTransition(Transition): void  [API 23+]
+ setEpicenterBounds(Rect): void  [API 29+]
+ setExitTransition(Transition): void  [API 23+]
+ setFocusable(boolean): void  [API 1+]
+ setHeight(int): void  [API 1+]
+ setIgnoreCheekPress(): void  [API 1+]
+ setInputMethodMode(int): void  [API 3+]
+ setIsClippedToScreen(boolean): void  [API 29+]
+ setIsLaidOutInScreen(boolean): void  [API 29+]
+ setOnDismissListener(PopupWindow.OnDismissListener): void  [API 1+]
+ setOutsideTouchable(boolean): void  [API 3+]
+ setOverlapAnchor(boolean): void  [API 23+]
+ setSoftInputMode(int): void  [API 4+]
+ setSplitTouchEnabled(boolean): void  [API 11+]
+ setTouchInterceptor(View.OnTouchListener): void  [API 3+]
~ setTouchModal(boolean): void  [API 29+]
+ setTouchable(boolean): void  [API 3+]
+ setWidth(int): void  [API 1+]
+ setWindowLayoutMode(int, int): void  [API 3+, deprecated in 23]
+ setWindowLayoutType(int): void  [API 23+]
+ showAsDropDown(View): void  [API 1+]
+ showAsDropDown(View, int, int): void  [API 1+]
+ showAsDropDown(View, int, int, int): void  [API 19+]
+ showAtLocation(View, int, int, int): void  [API 1+]
+ update(): void  [API 3+]
+ update(View, int, int): void  [API 1+]
+ update(View, int, int, int, int): void  [API 1+]
+ update(int, int): void  [API 4+]
+ update(int, int, int, int): void  [API 1+]
+ update(int, int, int, int, boolean): void  [API 3+]
```

## PopupWindow.OnDismissListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDismiss(): void  [API 1+]
```

## ProgressBar — framework; API 1+ (75 +, 0 *, 1 ~, 0 -)

```
+ ProgressBar(Context)  [API 1+]
+ ProgressBar(Context, AttributeSet)  [API 1+]
+ ProgressBar(Context, AttributeSet, int)  [API 1+]
+ ProgressBar(Context, AttributeSet, int, int)  [API 21+]
+ drawableHotspotChanged(float, float): void  [API 1+]
+ drawableStateChanged(): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getCurrentDrawable(): Drawable  [API 29+]
+ getIndeterminateDrawable(): Drawable  [API 1+]
+ getIndeterminateTintBlendMode(): BlendMode  [API 29+]
+ getIndeterminateTintList(): ColorStateList  [API 21+]
+ getIndeterminateTintMode(): PorterDuff.Mode  [API 21+]
+ getInterpolator(): Interpolator  [API 1+]
+ getMax(): int  [API 1+]
+ getMaxHeight(): int  [API 29+]
+ getMaxWidth(): int  [API 29+]
+ getMin(): int  [API 26+]
+ getMinHeight(): int  [API 29+]
+ getMinWidth(): int  [API 29+]
+ getProgress(): int  [API 1+]
+ getProgressBackgroundTintBlendMode(): BlendMode  [API 29+]
+ getProgressBackgroundTintList(): ColorStateList  [API 21+]
+ getProgressBackgroundTintMode(): PorterDuff.Mode  [API 21+]
+ getProgressDrawable(): Drawable  [API 1+]
+ getProgressTintBlendMode(): BlendMode  [API 29+]
+ getProgressTintList(): ColorStateList  [API 21+]
+ getProgressTintMode(): PorterDuff.Mode  [API 21+]
+ getSecondaryProgress(): int  [API 1+]
+ getSecondaryProgressTintBlendMode(): BlendMode  [API 29+]
+ getSecondaryProgressTintList(): ColorStateList  [API 21+]
+ getSecondaryProgressTintMode(): PorterDuff.Mode  [API 21+]
+ incrementProgressBy(int): void  [API 1+]
+ incrementSecondaryProgressBy(int): void  [API 1+]
+ invalidateDrawable(Drawable): void  [API 1+]
+ isAnimating(): boolean  [API 26+]
+ isIndeterminate(): boolean  [API 1+]
~ jumpDrawablesToCurrentState(): void  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onDraw(Canvas): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ onSizeChanged(int, int, int, int): void  [API 1+]
+ onVisibilityAggregated(boolean): void  [API 1+]
+ postInvalidate(): void  [API 1+]
+ setIndeterminate(boolean): void  [API 1+]
+ setIndeterminateDrawable(Drawable): void  [API 1+]
+ setIndeterminateDrawableTiled(Drawable): void  [API 21+]
+ setIndeterminateTintBlendMode(BlendMode): void  [API 29+]
+ setIndeterminateTintList(ColorStateList): void  [API 21+]
+ setIndeterminateTintMode(PorterDuff.Mode): void  [API 21+]
+ setInterpolator(Context, int): void  [API 1+]
+ setInterpolator(Interpolator): void  [API 1+]
+ setMax(int): void  [API 1+]
+ setMaxHeight(int): void  [API 29+]
+ setMaxWidth(int): void  [API 29+]
+ setMin(int): void  [API 26+]
+ setMinHeight(int): void  [API 29+]
+ setMinWidth(int): void  [API 29+]
+ setProgress(int): void  [API 1+]
+ setProgress(int, boolean): void  [API 24+]
+ setProgressBackgroundTintBlendMode(BlendMode): void  [API 29+]
+ setProgressBackgroundTintList(ColorStateList): void  [API 21+]
+ setProgressBackgroundTintMode(PorterDuff.Mode): void  [API 21+]
+ setProgressDrawable(Drawable): void  [API 1+]
+ setProgressDrawableTiled(Drawable): void  [API 21+]
+ setProgressTintBlendMode(BlendMode): void  [API 29+]
+ setProgressTintList(ColorStateList): void  [API 21+]
+ setProgressTintMode(PorterDuff.Mode): void  [API 21+]
+ setSecondaryProgress(int): void  [API 1+]
+ setSecondaryProgressTintBlendMode(BlendMode): void  [API 29+]
+ setSecondaryProgressTintList(ColorStateList): void  [API 21+]
+ setSecondaryProgressTintMode(PorterDuff.Mode): void  [API 21+]
+ setStateDescription(CharSequence): void  [API 1+]
+ verifyDrawable(Drawable): boolean  [API 1+]
```

## QuickContactBadge — declaration; API 5+ (0 +, 0 *, 0 ~, 20 -)

```
- QuickContactBadge(Context)  [API 5+]
- QuickContactBadge(Context, AttributeSet)  [API 5+]
- QuickContactBadge(Context, AttributeSet, int)  [API 5+]
- QuickContactBadge(Context, AttributeSet, int, int)  [API 21+]
- assignContactFromEmail(String, boolean): void  [API 5+]
- assignContactFromEmail(String, boolean, Bundle): void  [API 18+]
- assignContactFromPhone(String, boolean): void  [API 5+]
- assignContactFromPhone(String, boolean, Bundle): void  [API 18+]
- assignContactUri(Uri): void  [API 5+]
- drawableHotspotChanged(float, float): void  [API 5+]
- drawableStateChanged(): void  [API 5+]
- getAccessibilityClassName(): CharSequence  [API 5+]
+ mExcludeMimes [field]  [API 5+]
- onAttachedToWindow(): void  [API 5+]
- onClick(View): void  [API 5+]
- onDraw(Canvas): void  [API 5+]
- setExcludeMimes(String[]): void  [API 5+]
- setImageToDefault(): void  [API 11+]
- setMode(int): void  [API 5+]
- setOverlay(Drawable): void  [API 21+]
- setPrioritizedMimeType(String): void  [API 23+]
```

## RadioButton — framework; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ RadioButton(Context)  [API 1+]
+ RadioButton(Context, AttributeSet)  [API 1+]
+ RadioButton(Context, AttributeSet, int)  [API 1+]
+ RadioButton(Context, AttributeSet, int, int)  [API 21+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo): void  [API 1+]
+ toggle(): void  [API 1+]
```

## RadioGroup — framework; API 1+ (14 +, 0 *, 3 ~, 0 -)

```
+ RadioGroup(Context)  [API 1+]
+ RadioGroup(Context, AttributeSet)  [API 1+]
+ addView(View, int, ViewGroup.LayoutParams): void  [API 1+]
~ autofill(AutofillValue): void  [API 1+]
+ check(int): void  [API 1+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
+ clearCheck(): void  [API 1+]
+ generateDefaultLayoutParams(): LinearLayout.LayoutParams  [API 1+]
+ generateLayoutParams(AttributeSet): RadioGroup.LayoutParams  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
~ getAutofillType(): int  [API 1+]
~ getAutofillValue(): AutofillValue  [API 1+]
+ getCheckedRadioButtonId(): int  [API 1+]
+ onFinishInflate(): void  [API 1+]
+ onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo): void  [API 1+]
+ setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener): void  [API 1+]
+ setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener): void  [API 1+]
```

## RadioGroup.LayoutParams — framework; API 1+ (5 +, 0 *, 0 ~, 1 -)

```
+ LayoutParams(Context, AttributeSet)  [API 1+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 1+]
- LayoutParams(ViewGroup.MarginLayoutParams)  [API 1+]
+ LayoutParams(int, int)  [API 1+]
+ LayoutParams(int, int, float)  [API 1+]
+ setBaseAttributes(TypedArray, int, int): void  [API 1+]
```

## RadioGroup.OnCheckedChangeListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCheckedChanged(RadioGroup, int): void  [API 1+]
```

## RatingBar — framework; API 1+ (16 +, 0 *, 0 ~, 1 -)

```
+ RatingBar(Context)  [API 1+]
+ RatingBar(Context, AttributeSet)  [API 1+]
+ RatingBar(Context, AttributeSet, int)  [API 1+]
- RatingBar(Context, AttributeSet, int, int)  [API 21+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getNumStars(): int  [API 1+]
+ getOnRatingBarChangeListener(): RatingBar.OnRatingBarChangeListener  [API 1+]
+ getRating(): float  [API 1+]
+ getStepSize(): float  [API 1+]
+ isIndicator(): boolean  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ setIsIndicator(boolean): void  [API 1+]
+ setMax(int): void  [API 1+]
+ setNumStars(int): void  [API 1+]
+ setOnRatingBarChangeListener(RatingBar.OnRatingBarChangeListener): void  [API 1+]
+ setRating(float): void  [API 1+]
+ setStepSize(float): void  [API 1+]
```

## RatingBar.OnRatingBarChangeListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onRatingChanged(RatingBar, float, boolean): void  [API 1+]
```

## RelativeLayout — framework; API 1+ (17 +, 0 *, 1 ~, 2 -)

```
+ RelativeLayout(Context)  [API 1+]
+ RelativeLayout(Context, AttributeSet)  [API 1+]
+ RelativeLayout(Context, AttributeSet, int)  [API 1+]
- RelativeLayout(Context, AttributeSet, int, int)  [API 21+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
+ generateDefaultLayoutParams(): ViewGroup.LayoutParams  [API 1+]
+ generateLayoutParams(AttributeSet): RelativeLayout.LayoutParams  [API 1+]
+ generateLayoutParams(ViewGroup.LayoutParams): ViewGroup.LayoutParams  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getBaseline(): int  [API 1+]
+ getGravity(): int  [API 16+]
- getIgnoreGravity(): int  [API 29+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ requestLayout(): void  [API 1+]
+ setGravity(int): void  [API 1+]
+ setHorizontalGravity(int): void  [API 1+]
+ setIgnoreGravity(int): void  [API 1+]
+ setVerticalGravity(int): void  [API 1+]
~ shouldDelayChildPressedState(): boolean  [API 1+]
```

## RelativeLayout.LayoutParams — framework; API 1+ (9 +, 0 *, 1 ~, 2 -)

```
+ LayoutParams(Context, AttributeSet)  [API 1+]
+ LayoutParams(RelativeLayout.LayoutParams)  [API 19+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 1+]
+ LayoutParams(ViewGroup.MarginLayoutParams)  [API 1+]
+ LayoutParams(int, int)  [API 1+]
+ addRule(int): void  [API 1+]
+ addRule(int, int): void  [API 1+]
+ alignWithParent [field]  [API 1+]
- debug(String): String  [API 1+]
- getRule(int): int  [API 23+]
+ getRules(): int[]  [API 1+]
+ removeRule(int): void  [API 17+]
~ resolveLayoutDirection(int): void  [API 1+]
```

## RemoteViews — framework; API 1+ (2 +, 0 *, 7 ~, 90 -)

```
- CREATOR [field]  [API 1+]
- RemoteViews(Map)  [API 31+]
- RemoteViews(Parcel)  [API 1+]
- RemoteViews(RemoteViews)  [API 28+]
- RemoteViews(RemoteViews, RemoteViews)  [API 16+]
- RemoteViews(RemoteViews.DrawInstructions)  [API 35+]
+ RemoteViews(String, int)  [API 1+]
- RemoteViews(String, int, int)  [API 31+]
- addStableView(int, RemoteViews, int): void  [API 31+]
- addView(int, RemoteViews): void  [API 7+]
- apply(Context, ViewGroup): View  [API 1+]
+ clone(): RemoteViews  [API 9+, deprecated in 28]
~ describeContents(): int  [API 1+]
- getLayoutId(): int  [API 1+]
- getPackage(): String  [API 1+]
- getViewId(): int  [API 31+]
- onLoadClass(Class): boolean  [API 1+]
- reapply(Context, View): void  [API 1+]
- removeAllViews(int): void  [API 7+]
- setAccessibilityTraversalAfter(int, int): void  [API 22+]
- setAccessibilityTraversalBefore(int, int): void  [API 22+]
- setBitmap(int, String, Bitmap): void  [API 3+]
- setBlendMode(int, String, BlendMode): void  [API 31+]
- setBoolean(int, String, boolean): void  [API 3+]
- setBundle(int, String, Bundle): void  [API 8+]
- setByte(int, String, byte): void  [API 3+]
- setChar(int, String, char): void  [API 3+]
- setCharSequence(int, String, CharSequence): void  [API 3+]
- setCharSequence(int, String, int): void  [API 31+]
- setCharSequenceAttr(int, String, int): void  [API 31+]
- setChronometer(int, long, String, boolean): void  [API 1+]
- setChronometerCountDown(int, boolean): void  [API 24+]
- setColor(int, String, int): void  [API 31+]
- setColorAttr(int, String, int): void  [API 31+]
- setColorInt(int, String, int, int): void  [API 31+]
- setColorStateList(int, String, ColorStateList): void  [API 31+]
- setColorStateList(int, String, ColorStateList, ColorStateList): void  [API 31+]
- setColorStateList(int, String, int): void  [API 31+]
- setColorStateListAttr(int, String, int): void  [API 31+]
- setCompoundButtonChecked(int, boolean): void  [API 31+]
- setContentDescription(int, CharSequence): void  [API 15+]
- setDisplayedChild(int, int): void  [API 12+]
- setDouble(int, String, double): void  [API 3+]
- setEmptyView(int, int): void  [API 11+]
- setFloat(int, String, float): void  [API 3+]
- setFloatDimen(int, String, float, int): void  [API 31+]
- setFloatDimen(int, String, int): void  [API 31+]
- setFloatDimenAttr(int, String, int): void  [API 31+]
- setIcon(int, String, Icon): void  [API 23+]
- setIcon(int, String, Icon, Icon): void  [API 31+]
- setImageViewBitmap(int, Bitmap): void  [API 3+]
- setImageViewIcon(int, Icon): void  [API 23+]
~ setImageViewResource(int, int): void  [API 1+]
- setImageViewUri(int, Uri): void  [API 1+]
- setInt(int, String, int): void  [API 3+]
- setIntDimen(int, String, float, int): void  [API 31+]
- setIntDimen(int, String, int): void  [API 31+]
- setIntDimenAttr(int, String, int): void  [API 31+]
- setIntent(int, String, Intent): void  [API 11+]
- setLabelFor(int, int): void  [API 17+]
- setLightBackgroundLayoutId(int): void  [API 29+]
- setLong(int, String, long): void  [API 3+]
- setOnCheckedChangeResponse(int, RemoteViews.RemoteResponse): void  [API 31+]
- setOnClickFillInIntent(int, Intent): void  [API 11+]
~ setOnClickPendingIntent(int, PendingIntent): void  [API 3+]
- setOnClickResponse(int, RemoteViews.RemoteResponse): void  [API 29+]
- setOnStylusHandwritingPendingIntent(int, PendingIntent): void  [API 35+]
- setPendingIntentTemplate(int, PendingIntent): void  [API 11+]
~ setProgressBar(int, int, int, boolean): void  [API 1+]
- setRadioGroupChecked(int, int): void  [API 31+]
- setRelativeScrollPosition(int, int): void  [API 11+]
- setRemoteAdapter(int, Intent): void  [API 14+, deprecated in 35]
- setRemoteAdapter(int, RemoteViews.RemoteCollectionItems): void  [API 31+]
- setRemoteAdapter(int, int, Intent): void  [API 11+, deprecated in 15]
- setScrollPosition(int, int): void  [API 11+]
- setShort(int, String, short): void  [API 3+]
- setString(int, String, String): void  [API 3+]
- setTextColor(int, int): void  [API 3+]
- setTextViewCompoundDrawables(int, int, int, int, int): void  [API 16+]
- setTextViewCompoundDrawablesRelative(int, int, int, int, int): void  [API 16+]
~ setTextViewText(int, CharSequence): void  [API 1+]
- setTextViewTextSize(int, int, float): void  [API 16+]
- setUri(int, String, Uri): void  [API 3+]
- setViewLayoutHeight(int, float, int): void  [API 31+]
- setViewLayoutHeightAttr(int, int): void  [API 31+]
- setViewLayoutHeightDimen(int, int): void  [API 31+]
- setViewLayoutMargin(int, int, float, int): void  [API 31+]
- setViewLayoutMarginAttr(int, int, int): void  [API 31+]
- setViewLayoutMarginDimen(int, int, int): void  [API 31+]
- setViewLayoutWidth(int, float, int): void  [API 31+]
- setViewLayoutWidthAttr(int, int): void  [API 31+]
- setViewLayoutWidthDimen(int, int): void  [API 31+]
- setViewOutlinePreferredRadius(int, float, int): void  [API 31+]
- setViewOutlinePreferredRadiusAttr(int, int): void  [API 31+]
- setViewOutlinePreferredRadiusDimen(int, int): void  [API 31+]
- setViewPadding(int, int, int, int, int): void  [API 16+]
~ setViewVisibility(int, int): void  [API 1+]
- showNext(int): void  [API 11+, deprecated in 33]
- showPrevious(int): void  [API 11+, deprecated in 33]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## RemoteViews.ActionException — declaration; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
- ActionException(Exception)  [API 3+]
+ ActionException(String)  [API 1+]
```

## RemoteViews.DrawInstructions — declaration; API 35+ (0 +, 0 *, 0 ~, 1 -)

```
- getSupportedVersion(): long [static]  [API 35+]
```

## RemoteViews.DrawInstructions.Builder — declaration; API 35+ (0 +, 0 *, 0 ~, 2 -)

```
- Builder(List)  [API 35+]
- build(): RemoteViews.DrawInstructions  [API 35+]
```

## RemoteViews.RemoteCollectionItems — declaration; API 31+ (0 +, 1 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- getItemCount(): int  [API 31+]
- getItemId(int): long  [API 31+]
- getItemView(int): RemoteViews  [API 31+]
* getViewTypeCount(): int  [API 31+]
- hasStableIds(): boolean  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## RemoteViews.RemoteCollectionItems.Builder — declaration; API 31+ (0 +, 3 *, 0 ~, 2 -)

```
* Builder()  [API 31+]
- addItem(long, RemoteViews): RemoteViews.RemoteCollectionItems.Builder  [API 31+]
* build(): RemoteViews.RemoteCollectionItems  [API 31+]
- setHasStableIds(boolean): RemoteViews.RemoteCollectionItems.Builder  [API 31+]
* setViewTypeCount(int): RemoteViews.RemoteCollectionItems.Builder  [API 31+]
```

## RemoteViews.RemoteResponse — declaration; API 29+ (0 +, 0 *, 0 ~, 4 -)

```
- RemoteResponse()  [API 29+]
- addSharedElement(int, String): RemoteViews.RemoteResponse  [API 29+]
- fromFillInIntent(Intent): RemoteViews.RemoteResponse [static]  [API 29+]
- fromPendingIntent(PendingIntent): RemoteViews.RemoteResponse [static]  [API 29+]
```

## RemoteViews.RemoteView — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## RemoteViews.RemoteViewOutlineProvider — declaration; API 31+ (0 +, 0 *, 0 ~, 3 -)

```
- RemoteViewOutlineProvider(float)  [API 31+]
- getOutline(View, Outline): void  [API 31+]
- getRadius(): float  [API 31+]
```

## RemoteViewsService — declaration; API 11+ (1 +, 0 *, 0 ~, 2 -)

```
- RemoteViewsService()  [API 11+]
- onBind(Intent): IBinder  [API 11+]
+ onGetViewFactory(Intent): RemoteViewsService.RemoteViewsFactory  [API 11+]
```

## RemoteViewsService.RemoteViewsFactory — declaration; API 11+ (9 +, 0 *, 0 ~, 0 -)

```
+ getCount(): int  [API 11+]
+ getItemId(int): long  [API 11+]
+ getLoadingView(): RemoteViews  [API 11+]
+ getViewAt(int): RemoteViews  [API 11+]
+ getViewTypeCount(): int  [API 11+]
+ hasStableIds(): boolean  [API 11+]
+ onCreate(): void  [API 11+]
+ onDataSetChanged(): void  [API 11+]
+ onDestroy(): void  [API 11+]
```

## ResourceCursorAdapter — framework; API 1+ (7 +, 0 *, 0 ~, 1 -)

```
+ ResourceCursorAdapter(Context, int, Cursor)  [API 1+, deprecated in 15]
+ ResourceCursorAdapter(Context, int, Cursor, boolean)  [API 3+]
+ ResourceCursorAdapter(Context, int, Cursor, int)  [API 11+]
+ newDropDownView(Context, Cursor, ViewGroup): View  [API 1+]
+ newView(Context, Cursor, ViewGroup): View  [API 1+]
+ setDropDownViewResource(int): void  [API 1+]
- setDropDownViewTheme(Resources.Theme): void  [API 1+]
+ setViewResource(int): void  [API 3+]
```

## ResourceCursorTreeAdapter — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- ResourceCursorTreeAdapter(Context, Cursor, int, int)  [API 1+]
- ResourceCursorTreeAdapter(Context, Cursor, int, int, int)  [API 1+]
- ResourceCursorTreeAdapter(Context, Cursor, int, int, int, int)  [API 1+]
- newChildView(Context, Cursor, boolean, ViewGroup): View  [API 1+]
- newGroupView(Context, Cursor, boolean, ViewGroup): View  [API 1+]
```

## ScrollView — framework; API 1+ (49 +, 0 *, 3 ~, 7 -)

```
+ ScrollView(Context)  [API 1+]
+ ScrollView(Context, AttributeSet)  [API 1+]
+ ScrollView(Context, AttributeSet, int)  [API 1+]
- ScrollView(Context, AttributeSet, int, int)  [API 21+]
+ addView(View): void  [API 1+]
+ addView(View, ViewGroup.LayoutParams): void  [API 1+]
+ addView(View, int): void  [API 1+]
+ addView(View, int, ViewGroup.LayoutParams): void  [API 1+]
+ arrowScroll(int): boolean  [API 1+]
+ computeScroll(): void  [API 1+]
+ computeScrollDeltaToGetChildRectOnScreen(Rect): int  [API 1+]
+ computeVerticalScrollOffset(): int  [API 1+]
+ computeVerticalScrollRange(): int  [API 1+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 1+]
+ draw(Canvas): void  [API 1+]
+ executeKeyEvent(KeyEvent): boolean  [API 1+]
+ fling(int): void  [API 1+]
+ fullScroll(int): boolean  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
- getBottomEdgeEffectColor(): int  [API 29+]
~ getBottomFadingEdgeStrength(): float  [API 1+]
+ getMaxScrollAmount(): int  [API 1+]
- getTopEdgeEffectColor(): int  [API 29+]
~ getTopFadingEdgeStrength(): float  [API 1+]
+ isFillViewport(): boolean  [API 1+]
+ isSmoothScrollingEnabled(): boolean  [API 1+]
+ measureChild(View, int, int): void  [API 1+]
+ measureChildWithMargins(View, int, int, int, int): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 1+]
+ onInterceptTouchEvent(MotionEvent): boolean  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onNestedFling(View, float, float, boolean): boolean  [API 1+]
+ onNestedScroll(View, int, int, int, int): void  [API 1+]
+ onNestedScrollAccepted(View, View, int): void  [API 1+]
+ onOverScrolled(int, int, boolean, boolean): void  [API 1+]
+ onRequestFocusInDescendants(int, Rect): boolean  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ onSizeChanged(int, int, int, int): void  [API 1+]
+ onStartNestedScroll(View, View, int): boolean  [API 1+]
+ onStopNestedScroll(View): void  [API 1+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ pageScroll(int): boolean  [API 1+]
+ requestChildFocus(View, View): void  [API 1+]
+ requestChildRectangleOnScreen(View, Rect, boolean): boolean  [API 1+]
+ requestDisallowInterceptTouchEvent(boolean): void  [API 1+]
+ requestLayout(): void  [API 1+]
+ scrollTo(int, int): void  [API 1+]
- scrollToDescendant(View): void  [API 29+]
- setBottomEdgeEffectColor(int): void  [API 29+]
- setEdgeEffectColor(int): void  [API 29+]
+ setFillViewport(boolean): void  [API 1+]
+ setSmoothScrollingEnabled(boolean): void  [API 1+]
- setTopEdgeEffectColor(int): void  [API 29+]
~ shouldDelayChildPressedState(): boolean  [API 1+]
+ smoothScrollBy(int, int): void  [API 1+]
+ smoothScrollTo(int, int): void  [API 1+]
```

## Scroller — framework; API 1+ (22 +, 0 *, 1 ~, 0 -)

```
+ Scroller(Context)  [API 1+]
+ Scroller(Context, Interpolator)  [API 1+]
+ Scroller(Context, Interpolator, boolean)  [API 11+]
+ abortAnimation(): void  [API 1+]
+ computeScrollOffset(): boolean  [API 1+]
+ extendDuration(int): void  [API 1+]
+ fling(int, int, int, int, int, int, int, int): void  [API 1+]
+ forceFinished(boolean): void  [API 1+]
+ getCurrVelocity(): float  [API 14+]
+ getCurrX(): int  [API 1+]
+ getCurrY(): int  [API 1+]
+ getDuration(): int  [API 1+]
+ getFinalX(): int  [API 1+]
+ getFinalY(): int  [API 1+]
+ getStartX(): int  [API 3+]
+ getStartY(): int  [API 3+]
+ isFinished(): boolean  [API 1+]
+ setFinalX(int): void  [API 1+]
+ setFinalY(int): void  [API 1+]
~ setFriction(float): void  [API 11+]
+ startScroll(int, int, int, int): void  [API 1+]
+ startScroll(int, int, int, int, int): void  [API 1+]
+ timePassed(): int  [API 1+]
```

## SearchView — framework; API 11+ (43 +, 0 *, 0 ~, 0 -)

```
+ SearchView(Context)  [API 11+]
+ SearchView(Context, AttributeSet)  [API 11+]
+ SearchView(Context, AttributeSet, int)  [API 21+]
+ SearchView(Context, AttributeSet, int, int)  [API 21+]
+ clearFocus(): void  [API 11+]
+ getAccessibilityClassName(): CharSequence  [API 11+]
+ getImeOptions(): int  [API 16+]
+ getInputType(): int  [API 16+]
+ getMaxWidth(): int  [API 16+]
+ getQuery(): CharSequence  [API 11+]
+ getQueryHint(): CharSequence  [API 16+]
+ getSuggestionsAdapter(): CursorAdapter  [API 11+]
+ isIconfiedByDefault(): boolean  [API 11+, deprecated in 29]
+ isIconified(): boolean  [API 11+]
+ isIconifiedByDefault(): boolean  [API 29+]
+ isQueryRefinementEnabled(): boolean  [API 11+]
+ isSubmitButtonEnabled(): boolean  [API 11+]
+ onActionViewCollapsed(): void  [API 11+]
+ onActionViewExpanded(): void  [API 11+]
+ onDetachedFromWindow(): void  [API 11+]
+ onKeyDown(int, KeyEvent): boolean  [API 11+]
+ onLayout(boolean, int, int, int, int): void  [API 11+]
+ onMeasure(int, int): void  [API 11+]
+ onRestoreInstanceState(Parcelable): void  [API 11+]
+ onSaveInstanceState(): Parcelable  [API 11+]
+ onWindowFocusChanged(boolean): void  [API 11+]
+ requestFocus(int, Rect): boolean  [API 11+]
+ setIconified(boolean): void  [API 11+]
+ setIconifiedByDefault(boolean): void  [API 11+]
+ setImeOptions(int): void  [API 14+]
+ setInputType(int): void  [API 14+]
+ setMaxWidth(int): void  [API 11+]
+ setOnCloseListener(SearchView.OnCloseListener): void  [API 11+]
+ setOnQueryTextFocusChangeListener(View.OnFocusChangeListener): void  [API 11+]
+ setOnQueryTextListener(SearchView.OnQueryTextListener): void  [API 11+]
+ setOnSearchClickListener(View.OnClickListener): void  [API 11+]
+ setOnSuggestionListener(SearchView.OnSuggestionListener): void  [API 11+]
+ setQuery(CharSequence, boolean): void  [API 11+]
+ setQueryHint(CharSequence): void  [API 11+]
+ setQueryRefinementEnabled(boolean): void  [API 11+]
+ setSearchableInfo(SearchableInfo): void  [API 11+]
+ setSubmitButtonEnabled(boolean): void  [API 11+]
+ setSuggestionsAdapter(CursorAdapter): void  [API 11+]
```

## SearchView.OnCloseListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onClose(): boolean  [API 11+]
```

## SearchView.OnQueryTextListener — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ onQueryTextChange(String): boolean  [API 11+]
+ onQueryTextSubmit(String): boolean  [API 11+]
```

## SearchView.OnSuggestionListener — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ onSuggestionClick(int): boolean  [API 11+]
+ onSuggestionSelect(int): boolean  [API 11+]
```

## SectionIndexer — framework; API 3+ (3 +, 0 *, 0 ~, 0 -)

```
+ getPositionForSection(int): int  [API 3+]
+ getSectionForPosition(int): int  [API 3+]
+ getSections(): Object[]  [API 3+]
```

## SeekBar — framework; API 1+ (5 +, 0 *, 0 ~, 1 -)

```
+ SeekBar(Context)  [API 1+]
+ SeekBar(Context, AttributeSet)  [API 1+]
+ SeekBar(Context, AttributeSet, int)  [API 1+]
- SeekBar(Context, AttributeSet, int, int)  [API 21+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener): void  [API 1+]
```

## SeekBar.OnSeekBarChangeListener — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ onProgressChanged(SeekBar, int, boolean): void  [API 1+]
+ onStartTrackingTouch(SeekBar): void  [API 1+]
+ onStopTrackingTouch(SeekBar): void  [API 1+]
```

## ShareActionProvider — declaration; API 14+ (0 +, 0 *, 0 ~, 7 -)

```
- ShareActionProvider(Context)  [API 14+]
- hasSubMenu(): boolean  [API 14+]
- onCreateActionView(): View  [API 14+]
- onPrepareSubMenu(SubMenu): void  [API 14+]
- setOnShareTargetSelectedListener(ShareActionProvider.OnShareTargetSelectedListener): void  [API 14+]
- setShareHistoryFileName(String): void  [API 14+]
- setShareIntent(Intent): void  [API 14+]
```

## ShareActionProvider.OnShareTargetSelectedListener — declaration; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ onShareTargetSelected(ShareActionProvider, Intent): boolean  [API 14+]
```

## SimpleAdapter — framework; API 1+ (12 +, 0 *, 1 ~, 2 -)

```
+ SimpleAdapter(Context, List, int, String[], int[])  [API 1+]
+ getCount(): int  [API 1+]
+ getDropDownView(int, View, ViewGroup): View  [API 1+]
- getDropDownViewTheme(): Resources.Theme  [API 1+]
~ getFilter(): Filter  [API 1+]
+ getItem(int): Object  [API 1+]
+ getItemId(int): long  [API 1+]
+ getView(int, View, ViewGroup): View  [API 1+]
+ getViewBinder(): SimpleAdapter.ViewBinder  [API 1+]
+ setDropDownViewResource(int): void  [API 1+]
- setDropDownViewTheme(Resources.Theme): void  [API 1+]
+ setViewBinder(SimpleAdapter.ViewBinder): void  [API 1+]
+ setViewImage(ImageView, String): void  [API 1+]
+ setViewImage(ImageView, int): void  [API 1+]
+ setViewText(TextView, String): void  [API 1+]
```

## SimpleAdapter.ViewBinder — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ setViewValue(View, Object, String): boolean  [API 1+]
```

## SimpleCursorAdapter — framework; API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ SimpleCursorAdapter(Context, int, Cursor, String[], int[])  [API 1+, deprecated in 15]
+ SimpleCursorAdapter(Context, int, Cursor, String[], int[], int)  [API 11+]
+ bindView(View, Context, Cursor): void  [API 1+]
+ changeCursorAndColumns(Cursor, String[], int[]): void  [API 3+]
+ convertToString(Cursor): CharSequence  [API 1+]
+ getCursorToStringConverter(): SimpleCursorAdapter.CursorToStringConverter  [API 1+]
+ getStringConversionColumn(): int  [API 1+]
+ getViewBinder(): SimpleCursorAdapter.ViewBinder  [API 1+]
+ setCursorToStringConverter(SimpleCursorAdapter.CursorToStringConverter): void  [API 1+]
+ setStringConversionColumn(int): void  [API 1+]
+ setViewBinder(SimpleCursorAdapter.ViewBinder): void  [API 1+]
+ setViewImage(ImageView, String): void  [API 1+]
+ setViewText(TextView, String): void  [API 1+]
+ swapCursor(Cursor): Cursor  [API 1+]
```

## SimpleCursorAdapter.CursorToStringConverter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ convertToString(Cursor): CharSequence  [API 1+]
```

## SimpleCursorAdapter.ViewBinder — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ setViewValue(View, Cursor, int): boolean  [API 1+]
```

## SimpleCursorTreeAdapter — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- SimpleCursorTreeAdapter(Context, Cursor, int, String[], int[], int, String[], int[])  [API 1+]
- SimpleCursorTreeAdapter(Context, Cursor, int, int, String[], int[], int, String[], int[])  [API 1+]
- SimpleCursorTreeAdapter(Context, Cursor, int, int, String[], int[], int, int, String[], int[])  [API 1+]
- bindChildView(View, Context, Cursor, boolean): void  [API 1+]
- bindGroupView(View, Context, Cursor, boolean): void  [API 1+]
- getViewBinder(): SimpleCursorTreeAdapter.ViewBinder  [API 5+]
- setViewBinder(SimpleCursorTreeAdapter.ViewBinder): void  [API 5+]
- setViewImage(ImageView, String): void  [API 1+]
- setViewText(TextView, String): void  [API 5+]
```

## SimpleCursorTreeAdapter.ViewBinder — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ setViewValue(View, Cursor, int): boolean  [API 5+]
```

## SimpleExpandableListAdapter — framework; API 1+ (11 +, 0 *, 2 ~, 2 -)

```
+ SimpleExpandableListAdapter(Context, List, int, String[], int[], List, int, String[], int[])  [API 1+]
+ SimpleExpandableListAdapter(Context, List, int, int, String[], int[], List, int, String[], int[])  [API 1+]
+ SimpleExpandableListAdapter(Context, List, int, int, String[], int[], List, int, int, String[], int[])  [API 1+]
+ getChild(int, int): Object  [API 1+]
+ getChildId(int, int): long  [API 1+]
+ getChildView(int, int, boolean, View, ViewGroup): View  [API 1+]
+ getChildrenCount(int): int  [API 1+]
+ getGroup(int): Object  [API 1+]
+ getGroupCount(): int  [API 1+]
+ getGroupId(int): long  [API 1+]
+ getGroupView(int, boolean, View, ViewGroup): View  [API 1+]
~ hasStableIds(): boolean  [API 1+]
~ isChildSelectable(int, int): boolean  [API 1+]
- newChildView(boolean, ViewGroup): View  [API 1+]
- newGroupView(boolean, ViewGroup): View  [API 1+]
```

## SlidingDrawer — declaration; API 3+, deprecated in 17 (0 +, 0 *, 0 ~, 25 -)

```
- SlidingDrawer(Context, AttributeSet)  [API 3+]
- SlidingDrawer(Context, AttributeSet, int)  [API 3+]
- SlidingDrawer(Context, AttributeSet, int, int)  [API 21+, deprecated in 21]
- animateClose(): void  [API 3+]
- animateOpen(): void  [API 3+]
- animateToggle(): void  [API 3+]
- close(): void  [API 3+]
- dispatchDraw(Canvas): void  [API 3+]
- getAccessibilityClassName(): CharSequence  [API 3+]
- getContent(): View  [API 3+]
- getHandle(): View  [API 3+]
- isMoving(): boolean  [API 3+]
- isOpened(): boolean  [API 3+]
- lock(): void  [API 3+]
- onFinishInflate(): void  [API 3+]
- onInterceptTouchEvent(MotionEvent): boolean  [API 3+]
- onLayout(boolean, int, int, int, int): void  [API 3+]
- onMeasure(int, int): void  [API 3+]
- onTouchEvent(MotionEvent): boolean  [API 3+]
- open(): void  [API 3+]
- setOnDrawerCloseListener(SlidingDrawer.OnDrawerCloseListener): void  [API 3+]
- setOnDrawerOpenListener(SlidingDrawer.OnDrawerOpenListener): void  [API 3+]
- setOnDrawerScrollListener(SlidingDrawer.OnDrawerScrollListener): void  [API 3+]
- toggle(): void  [API 3+]
- unlock(): void  [API 3+]
```

## SlidingDrawer.OnDrawerCloseListener — declaration; API 3+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onDrawerClosed(): void  [API 3+]
```

## SlidingDrawer.OnDrawerOpenListener — declaration; API 3+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onDrawerOpened(): void  [API 3+]
```

## SlidingDrawer.OnDrawerScrollListener — declaration; API 3+, deprecated in 29 (2 +, 0 *, 0 ~, 0 -)

```
+ onScrollEnded(): void  [API 3+]
+ onScrollStarted(): void  [API 3+]
```

## Space — framework; API 14+ (4 +, 0 *, 1 ~, 1 -)

```
+ Space(Context)  [API 14+]
+ Space(Context, AttributeSet)  [API 14+]
+ Space(Context, AttributeSet, int)  [API 14+]
- Space(Context, AttributeSet, int, int)  [API 21+]
~ draw(Canvas): void  [API 14+]
+ onMeasure(int, int): void  [API 14+]
```

## Spinner — framework; API 1+ (20 +, 0 *, 6 ~, 10 -)

```
+ Spinner(Context)  [API 1+]
+ Spinner(Context, AttributeSet)  [API 1+]
+ Spinner(Context, AttributeSet, int)  [API 1+]
+ Spinner(Context, AttributeSet, int, int)  [API 11+]
- Spinner(Context, AttributeSet, int, int, int)  [API 21+]
- Spinner(Context, AttributeSet, int, int, int, Resources.Theme)  [API 23+]
+ Spinner(Context, int)  [API 11+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
~ getBaseline(): int  [API 1+]
- getDropDownHorizontalOffset(): int  [API 16+]
- getDropDownVerticalOffset(): int  [API 16+]
- getDropDownWidth(): int  [API 16+]
- getGravity(): int  [API 16+]
- getPopupBackground(): Drawable  [API 16+]
- getPopupContext(): Context  [API 23+]
+ getPrompt(): CharSequence  [API 1+]
- onClick(DialogInterface, int): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onResolvePointerIcon(MotionEvent, int): PointerIcon  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ performClick(): boolean  [API 1+]
+ setAdapter(SpinnerAdapter): void  [API 1+]
~ setDropDownHorizontalOffset(int): void  [API 16+]
~ setDropDownVerticalOffset(int): void  [API 16+]
~ setDropDownWidth(int): void  [API 16+]
+ setEnabled(boolean): void  [API 1+]
~ setGravity(int): void  [API 11+]
+ setOnItemClickListener(AdapterView.OnItemClickListener): void  [API 1+]
- setPopupBackgroundDrawable(Drawable): void  [API 16+]
~ setPopupBackgroundResource(int): void  [API 16+]
+ setPrompt(CharSequence): void  [API 1+]
+ setPromptId(int): void  [API 1+]
```

## SpinnerAdapter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getDropDownView(int, View, ViewGroup): View  [API 1+]
```

## StackView — declaration; API 11+ (0 +, 0 *, 0 ~, 14 -)

```
- StackView(Context)  [API 11+]
- StackView(Context, AttributeSet)  [API 11+]
- StackView(Context, AttributeSet, int)  [API 14+]
- StackView(Context, AttributeSet, int, int)  [API 21+]
- advance(): void  [API 11+]
- dispatchDraw(Canvas): void  [API 11+]
- getAccessibilityClassName(): CharSequence  [API 11+]
- onGenericMotionEvent(MotionEvent): boolean  [API 11+]
- onInterceptTouchEvent(MotionEvent): boolean  [API 11+]
- onLayout(boolean, int, int, int, int): void  [API 11+]
- onMeasure(int, int): void  [API 11+]
- onTouchEvent(MotionEvent): boolean  [API 11+]
- showNext(): void  [API 11+]
- showPrevious(): void  [API 11+]
```

## Switch — framework; API 14+ (56 +, 0 *, 0 ~, 0 -)

```
+ Switch(Context)  [API 14+]
+ Switch(Context, AttributeSet)  [API 14+]
+ Switch(Context, AttributeSet, int)  [API 14+]
+ Switch(Context, AttributeSet, int, int)  [API 21+]
+ draw(Canvas): void  [API 14+]
+ drawableHotspotChanged(float, float): void  [API 14+]
+ drawableStateChanged(): void  [API 14+]
+ getAccessibilityClassName(): CharSequence  [API 14+]
+ getCompoundPaddingLeft(): int  [API 14+]
+ getCompoundPaddingRight(): int  [API 14+]
+ getShowText(): boolean  [API 21+]
+ getSplitTrack(): boolean  [API 21+]
+ getSwitchMinWidth(): int  [API 16+]
+ getSwitchPadding(): int  [API 16+]
+ getTextOff(): CharSequence  [API 14+]
+ getTextOn(): CharSequence  [API 14+]
+ getThumbDrawable(): Drawable  [API 16+]
+ getThumbTextPadding(): int  [API 16+]
+ getThumbTintBlendMode(): BlendMode  [API 29+]
+ getThumbTintList(): ColorStateList  [API 23+]
+ getThumbTintMode(): PorterDuff.Mode  [API 23+]
+ getTrackDrawable(): Drawable  [API 16+]
+ getTrackTintBlendMode(): BlendMode  [API 29+]
+ getTrackTintList(): ColorStateList  [API 23+]
+ getTrackTintMode(): PorterDuff.Mode  [API 23+]
+ jumpDrawablesToCurrentState(): void  [API 14+]
+ onCreateDrawableState(int): int[]  [API 14+]
+ onDraw(Canvas): void  [API 14+]
+ onLayout(boolean, int, int, int, int): void  [API 14+]
+ onMeasure(int, int): void  [API 14+]
+ onTouchEvent(MotionEvent): boolean  [API 14+]
+ setChecked(boolean): void  [API 14+]
+ setShowText(boolean): void  [API 21+]
+ setSplitTrack(boolean): void  [API 21+]
+ setSwitchMinWidth(int): void  [API 16+]
+ setSwitchPadding(int): void  [API 16+]
+ setSwitchTextAppearance(Context, int): void  [API 14+]
+ setSwitchTypeface(Typeface): void  [API 14+]
+ setSwitchTypeface(Typeface, int): void  [API 14+]
+ setTextOff(CharSequence): void  [API 14+]
+ setTextOn(CharSequence): void  [API 14+]
+ setThumbDrawable(Drawable): void  [API 16+]
+ setThumbIcon(Icon): void  [API 31+]
+ setThumbResource(int): void  [API 16+]
+ setThumbTextPadding(int): void  [API 16+]
+ setThumbTintBlendMode(BlendMode): void  [API 29+]
+ setThumbTintList(ColorStateList): void  [API 23+]
+ setThumbTintMode(PorterDuff.Mode): void  [API 23+]
+ setTrackDrawable(Drawable): void  [API 16+]
+ setTrackIcon(Icon): void  [API 31+]
+ setTrackResource(int): void  [API 16+]
+ setTrackTintBlendMode(BlendMode): void  [API 29+]
+ setTrackTintList(ColorStateList): void  [API 23+]
+ setTrackTintMode(PorterDuff.Mode): void  [API 23+]
+ toggle(): void  [API 14+]
+ verifyDrawable(Drawable): boolean  [API 14+]
```

## TabHost — framework; API 1+, deprecated in 30 (20 +, 0 *, 0 ~, 2 -)

```
+ TabHost(Context)  [API 1+]
+ TabHost(Context, AttributeSet)  [API 1+]
+ TabHost(Context, AttributeSet, int)  [API 21+]
- TabHost(Context, AttributeSet, int, int)  [API 21+]
+ addTab(TabHost.TabSpec): void  [API 1+]
+ clearAllTabs(): void  [API 1+]
+ dispatchKeyEvent(KeyEvent): boolean  [API 1+]
+ dispatchWindowFocusChanged(boolean): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getCurrentTab(): int  [API 1+]
+ getCurrentTabTag(): String  [API 1+]
+ getCurrentTabView(): View  [API 1+]
+ getCurrentView(): View  [API 1+]
+ getTabContentView(): FrameLayout  [API 1+]
+ getTabWidget(): TabWidget  [API 1+]
+ newTabSpec(String): TabHost.TabSpec  [API 1+]
- onTouchModeChanged(boolean): void  [API 1+]
+ setCurrentTab(int): void  [API 1+]
+ setCurrentTabByTag(String): void  [API 1+]
+ setOnTabChangedListener(TabHost.OnTabChangeListener): void  [API 1+]
+ setup(): void  [API 1+]
+ setup(LocalActivityManager): void  [API 1+]
```

## TabHost.OnTabChangeListener — framework; API 1+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ onTabChanged(String): void  [API 1+]
```

## TabHost.TabContentFactory — framework; API 1+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ createTabContent(String): View  [API 1+]
```

## TabHost.TabSpec — framework; API 1+, deprecated in 30 (7 +, 0 *, 0 ~, 0 -)

```
+ getTag(): String  [API 4+]
+ setContent(Intent): TabHost.TabSpec  [API 1+]
+ setContent(TabHost.TabContentFactory): TabHost.TabSpec  [API 1+]
+ setContent(int): TabHost.TabSpec  [API 1+]
+ setIndicator(CharSequence): TabHost.TabSpec  [API 1+]
+ setIndicator(CharSequence, Drawable): TabHost.TabSpec  [API 1+]
+ setIndicator(View): TabHost.TabSpec  [API 4+]
```

## TabWidget — framework; API 1+, deprecated in 30 (21 +, 0 *, 4 ~, 3 -)

```
+ TabWidget(Context)  [API 1+]
+ TabWidget(Context, AttributeSet)  [API 1+]
+ TabWidget(Context, AttributeSet, int)  [API 1+]
- TabWidget(Context, AttributeSet, int, int)  [API 21+]
+ addView(View): void  [API 1+]
+ childDrawableStateChanged(View): void  [API 1+]
+ dispatchDraw(Canvas): void  [API 1+]
+ focusCurrentTab(int): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getChildDrawingOrder(int, int): int  [API 1+]
+ getChildTabViewAt(int): View  [API 4+]
- getLeftStripDrawable(): Drawable  [API 24+]
- getRightStripDrawable(): Drawable  [API 24+]
+ getTabCount(): int  [API 4+]
+ isStripEnabled(): boolean  [API 8+]
+ onFocusChange(View, boolean): void  [API 1+]
+ onResolvePointerIcon(MotionEvent, int): PointerIcon  [API 1+]
+ onSizeChanged(int, int, int, int): void  [API 1+]
+ removeAllViews(): void  [API 1+]
+ setCurrentTab(int): void  [API 1+]
+ setDividerDrawable(Drawable): void  [API 4+]
+ setDividerDrawable(int): void  [API 4+]
+ setEnabled(boolean): void  [API 1+]
~ setLeftStripDrawable(Drawable): void  [API 8+]
~ setLeftStripDrawable(int): void  [API 8+]
~ setRightStripDrawable(Drawable): void  [API 8+]
~ setRightStripDrawable(int): void  [API 8+]
+ setStripEnabled(boolean): void  [API 8+]
```

## TableLayout — framework; API 1+ (20 +, 0 *, 0 ~, 5 -)

```
+ TableLayout(Context)  [API 1+]
+ TableLayout(Context, AttributeSet)  [API 1+]
+ addView(View): void  [API 1+]
+ addView(View, ViewGroup.LayoutParams): void  [API 1+]
+ addView(View, int): void  [API 1+]
+ addView(View, int, ViewGroup.LayoutParams): void  [API 1+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
+ generateDefaultLayoutParams(): LinearLayout.LayoutParams  [API 1+]
+ generateLayoutParams(AttributeSet): TableLayout.LayoutParams  [API 1+]
+ generateLayoutParams(ViewGroup.LayoutParams): LinearLayout.LayoutParams  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
- isColumnCollapsed(int): boolean  [API 1+]
- isColumnShrinkable(int): boolean  [API 1+]
- isColumnStretchable(int): boolean  [API 1+]
- isShrinkAllColumns(): boolean  [API 1+]
- isStretchAllColumns(): boolean  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ requestLayout(): void  [API 1+]
+ setColumnCollapsed(int, boolean): void  [API 1+]
+ setColumnShrinkable(int, boolean): void  [API 1+]
+ setColumnStretchable(int, boolean): void  [API 1+]
+ setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener): void  [API 1+]
+ setShrinkAllColumns(boolean): void  [API 1+]
+ setStretchAllColumns(boolean): void  [API 1+]
```

## TableLayout.LayoutParams — framework; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ LayoutParams()  [API 1+]
+ LayoutParams(Context, AttributeSet)  [API 1+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 1+]
+ LayoutParams(ViewGroup.MarginLayoutParams)  [API 1+]
+ LayoutParams(int, int)  [API 1+]
+ LayoutParams(int, int, float)  [API 1+]
+ setBaseAttributes(TypedArray, int, int): void  [API 1+]
```

## TableRow — framework; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ TableRow(Context)  [API 1+]
+ TableRow(Context, AttributeSet)  [API 1+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 1+]
+ generateDefaultLayoutParams(): LinearLayout.LayoutParams  [API 1+]
+ generateLayoutParams(AttributeSet): TableRow.LayoutParams  [API 1+]
+ generateLayoutParams(ViewGroup.LayoutParams): LinearLayout.LayoutParams  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getVirtualChildAt(int): View  [API 1+]
+ getVirtualChildCount(): int  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener): void  [API 1+]
```

## TableRow.LayoutParams — framework; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ LayoutParams()  [API 1+]
+ LayoutParams(Context, AttributeSet)  [API 1+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 1+]
+ LayoutParams(ViewGroup.MarginLayoutParams)  [API 1+]
+ LayoutParams(int)  [API 1+]
+ LayoutParams(int, int)  [API 1+]
+ LayoutParams(int, int, float)  [API 1+]
+ column [field]  [API 1+]
+ setBaseAttributes(TypedArray, int, int): void  [API 1+]
+ span [field]  [API 1+]
```

## TextClock — framework; API 17+ (15 +, 0 *, 0 ~, 0 -)

```
+ DEFAULT_FORMAT_12_HOUR [field]  [API 17+, deprecated in 18]
+ DEFAULT_FORMAT_24_HOUR [field]  [API 17+, deprecated in 18]
+ TextClock(Context)  [API 17+]
+ TextClock(Context, AttributeSet)  [API 17+]
+ TextClock(Context, AttributeSet, int)  [API 17+]
+ TextClock(Context, AttributeSet, int, int)  [API 21+]
+ getFormat12Hour(): CharSequence  [API 17+]
+ getFormat24Hour(): CharSequence  [API 17+]
+ getTimeZone(): String  [API 17+]
+ is24HourModeEnabled(): boolean  [API 17+]
+ onAttachedToWindow(): void  [API 17+]
+ onDetachedFromWindow(): void  [API 17+]
+ onVisibilityAggregated(boolean): void  [API 17+]
+ refreshTime(): void  [API 30+]
+ setFormat12Hour(CharSequence): void  [API 17+]
+ setFormat24Hour(CharSequence): void  [API 17+]
+ setTimeZone(String): void  [API 17+]
```

## TextSwitcher — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ TextSwitcher(Context)  [API 1+]
+ TextSwitcher(Context, AttributeSet)  [API 1+]
+ addView(View, int, ViewGroup.LayoutParams): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ setCurrentText(CharSequence): void  [API 1+]
+ setText(CharSequence): void  [API 1+]
```

## TextView — framework; API 1+ (322 +, 0 *, 22 ~, 2 -)

```
+ TextView(Context)  [API 1+]
+ TextView(Context, AttributeSet)  [API 1+]
+ TextView(Context, AttributeSet, int)  [API 1+]
+ TextView(Context, AttributeSet, int, int)  [API 21+]
~ addExtraDataToAccessibilityNodeInfo(AccessibilityNodeInfo, String, Bundle): void  [API 1+]
+ addTextChangedListener(TextWatcher): void  [API 1+]
+ append(CharSequence): void  [API 1+]
+ append(CharSequence, int, int): void  [API 1+]
~ autofill(AutofillValue): void  [API 1+]
~ beginBatchEdit(): void  [API 3+]
+ bringPointIntoView(int): boolean  [API 3+]
+ bringPointIntoView(int, boolean): boolean  [API 34+]
+ cancelLongPress(): void  [API 1+]
+ clearComposingText(): void  [API 3+]
+ computeHorizontalScrollRange(): int  [API 1+]
~ computeScroll(): void  [API 1+]
+ computeVerticalScrollExtent(): int  [API 1+]
+ computeVerticalScrollRange(): int  [API 1+]
+ debug(int): void  [API 1+]
~ didTouchFocusSelect(): boolean  [API 3+]
+ drawableHotspotChanged(float, float): void  [API 1+]
+ drawableStateChanged(): void  [API 1+]
~ endBatchEdit(): void  [API 3+]
+ extractText(ExtractedTextRequest, ExtractedText): boolean  [API 3+]
~ findViewsWithText(ArrayList, CharSequence, int): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getAutoLinkMask(): int  [API 1+]
+ getAutoSizeMaxTextSize(): int  [API 26+]
+ getAutoSizeMinTextSize(): int  [API 26+]
+ getAutoSizeStepGranularity(): int  [API 26+]
+ getAutoSizeTextAvailableSizes(): int[]  [API 26+]
+ getAutoSizeTextType(): int  [API 26+]
+ getAutofillHints(): String[]  [API 1+]
~ getAutofillType(): int  [API 1+]
~ getAutofillValue(): AutofillValue  [API 1+]
+ getBaseline(): int  [API 1+]
+ getBottomPaddingOffset(): int  [API 1+]
+ getBreakStrategy(): int  [API 23+]
+ getCompoundDrawablePadding(): int  [API 1+]
+ getCompoundDrawableTintBlendMode(): BlendMode  [API 29+]
+ getCompoundDrawableTintList(): ColorStateList  [API 23+]
+ getCompoundDrawableTintMode(): PorterDuff.Mode  [API 23+]
+ getCompoundDrawables(): Drawable[]  [API 1+]
+ getCompoundDrawablesRelative(): Drawable[]  [API 17+]
+ getCompoundPaddingBottom(): int  [API 1+]
+ getCompoundPaddingEnd(): int  [API 17+]
+ getCompoundPaddingLeft(): int  [API 1+]
+ getCompoundPaddingRight(): int  [API 1+]
+ getCompoundPaddingStart(): int  [API 17+]
+ getCompoundPaddingTop(): int  [API 1+]
+ getCurrentHintTextColor(): int  [API 1+]
+ getCurrentTextColor(): int  [API 1+]
+ getCustomInsertionActionModeCallback(): ActionMode.Callback  [API 23+]
+ getCustomSelectionActionModeCallback(): ActionMode.Callback  [API 11+]
~ getDefaultEditable(): boolean  [API 1+]
~ getDefaultMovementMethod(): MovementMethod  [API 1+]
+ getEditableText(): Editable  [API 3+]
+ getEllipsize(): TextUtils.TruncateAt  [API 1+]
+ getError(): CharSequence  [API 1+]
+ getExtendedPaddingBottom(): int  [API 1+]
+ getExtendedPaddingTop(): int  [API 1+]
+ getFilters(): InputFilter[]  [API 1+]
+ getFirstBaselineToTopHeight(): int  [API 28+]
+ getFocusedRect(Rect): void  [API 1+]
+ getFocusedSearchResultHighlightColor(): int  [API 34+]
+ getFocusedSearchResultIndex(): int  [API 34+]
+ getFontFeatureSettings(): String  [API 21+]
+ getFontVariationSettings(): String  [API 26+]
+ getFreezesText(): boolean  [API 1+]
+ getGravity(): int  [API 1+]
+ getHighlightColor(): int  [API 16+]
+ getHighlights(): Highlights  [API 34+]
+ getHint(): CharSequence  [API 1+]
+ getHintTextColors(): ColorStateList  [API 1+]
+ getHyphenationFrequency(): int  [API 23+]
+ getImeActionId(): int  [API 3+]
+ getImeActionLabel(): CharSequence  [API 3+]
+ getImeHintLocales(): LocaleList  [API 24+]
+ getImeOptions(): int  [API 3+]
+ getIncludeFontPadding(): boolean  [API 16+]
+ getInputExtras(boolean): Bundle  [API 3+]
+ getInputType(): int  [API 3+]
+ getJustificationMode(): int  [API 26+]
+ getKeyListener(): KeyListener  [API 1+]
+ getLastBaselineToBottomHeight(): int  [API 28+]
+ getLayout(): Layout  [API 1+]
+ getLeftFadingEdgeStrength(): float  [API 1+]
+ getLeftPaddingOffset(): int  [API 1+]
+ getLetterSpacing(): float  [API 21+]
+ getLineBounds(int, Rect): int  [API 1+]
+ getLineBreakStyle(): int  [API 33+]
+ getLineBreakWordStyle(): int  [API 33+]
+ getLineCount(): int  [API 1+]
+ getLineHeight(): int  [API 1+]
+ getLineSpacingExtra(): float  [API 16+]
+ getLineSpacingMultiplier(): float  [API 16+]
+ getLinkTextColors(): ColorStateList  [API 1+]
+ getLinksClickable(): boolean  [API 1+]
+ getMarqueeRepeatLimit(): int  [API 16+]
+ getMaxEms(): int  [API 16+]
+ getMaxHeight(): int  [API 16+]
+ getMaxLines(): int  [API 16+]
+ getMaxWidth(): int  [API 16+]
+ getMinEms(): int  [API 16+]
+ getMinHeight(): int  [API 16+]
+ getMinLines(): int  [API 16+]
+ getMinWidth(): int  [API 16+]
+ getMinimumFontMetrics(): Paint.FontMetrics  [API 35+]
+ getMovementMethod(): MovementMethod  [API 1+]
+ getOffsetForPosition(float, float): int  [API 14+]
+ getPaint(): TextPaint  [API 1+]
+ getPaintFlags(): int  [API 1+]
+ getPrivateImeOptions(): String  [API 3+]
+ getRightFadingEdgeStrength(): float  [API 1+]
+ getRightPaddingOffset(): int  [API 1+]
+ getSearchResultHighlightColor(): int  [API 34+]
+ getSearchResultHighlights(): int[]  [API 34+]
+ getSelectionEnd(): int  [API 1+]
+ getSelectionStart(): int  [API 1+]
+ getShadowColor(): int  [API 16+]
+ getShadowDx(): float  [API 16+]
+ getShadowDy(): float  [API 16+]
+ getShadowRadius(): float  [API 16+]
+ getShiftDrawingOffsetForStartOverhang(): boolean  [API 35+]
+ getShowSoftInputOnFocus(): boolean  [API 21+]
+ getText(): CharSequence  [API 1+]
+ getTextClassifier(): TextClassifier  [API 26+]
- getTextColor(Context, TypedArray, int): int  [API 1-20, removed in 21]
+ getTextColors(): ColorStateList  [API 1+]
- getTextColors(Context, TypedArray): ColorStateList  [API 1-20, removed in 21]
+ getTextCursorDrawable(): Drawable  [API 29+]
+ getTextDirectionHeuristic(): TextDirectionHeuristic  [API 29+]
+ getTextLocale(): Locale  [API 17+]
+ getTextLocales(): LocaleList  [API 24+]
+ getTextMetricsParams(): PrecomputedText.Params  [API 28+]
+ getTextScaleX(): float  [API 1+]
+ getTextSelectHandle(): Drawable  [API 29+]
+ getTextSelectHandleLeft(): Drawable  [API 29+]
+ getTextSelectHandleRight(): Drawable  [API 29+]
+ getTextSize(): float  [API 1+]
+ getTextSizeUnit(): int  [API 30+]
+ getTopPaddingOffset(): int  [API 1+]
+ getTotalPaddingBottom(): int  [API 1+]
+ getTotalPaddingEnd(): int  [API 17+]
+ getTotalPaddingLeft(): int  [API 1+]
+ getTotalPaddingRight(): int  [API 1+]
+ getTotalPaddingStart(): int  [API 17+]
+ getTotalPaddingTop(): int  [API 1+]
+ getTransformationMethod(): TransformationMethod  [API 1+]
+ getTypeface(): Typeface  [API 1+]
+ getUrls(): URLSpan[]  [API 1+]
+ getUseBoundsForWidth(): boolean  [API 35+]
~ hasOverlappingRendering(): boolean  [API 1+]
+ hasSelection(): boolean  [API 1+]
+ invalidateDrawable(Drawable): void  [API 1+]
+ isAllCaps(): boolean  [API 28+]
+ isAutoHandwritingEnabled(): boolean  [API 1+]
+ isCursorVisible(): boolean  [API 16+]
+ isElegantTextHeight(): boolean  [API 28+]
+ isFallbackLineSpacing(): boolean  [API 28+]
+ isHorizontallyScrollable(): boolean  [API 29+]
+ isInputMethodTarget(): boolean  [API 3+]
+ isLocalePreferredLineHeightForMinimumUsed(): boolean  [API 35+]
+ isPaddingOffsetRequired(): boolean  [API 1+]
+ isSingleLine(): boolean  [API 29+]
+ isSuggestionsEnabled(): boolean  [API 14+]
~ isTextSelectable(): boolean  [API 11+]
~ jumpDrawablesToCurrentState(): void  [API 1+]
+ length(): int  [API 1+]
+ moveCursorToVisibleOffset(): boolean  [API 3+]
+ onAttachedToWindow(): void  [API 1+]
+ onBeginBatchEdit(): void  [API 3+]
+ onCheckIsTextEditor(): boolean  [API 1+]
+ onCommitCompletion(CompletionInfo): void  [API 3+]
+ onCommitCorrection(CorrectionInfo): void  [API 11+]
+ onConfigurationChanged(Configuration): void  [API 1+]
+ onCreateContextMenu(ContextMenu): void  [API 1+]
+ onCreateDrawableState(int): int[]  [API 1+]
+ onCreateInputConnection(EditorInfo): InputConnection  [API 1+]
+ onCreateViewTranslationRequest(int[], Consumer): void  [API 1+]
+ onDragEvent(DragEvent): boolean  [API 1+]
+ onDraw(Canvas): void  [API 1+]
+ onEditorAction(int): void  [API 3+]
+ onEndBatchEdit(): void  [API 3+]
+ onFocusChanged(boolean, int, Rect): void  [API 1+]
+ onGenericMotionEvent(MotionEvent): boolean  [API 1+]
+ onKeyDown(int, KeyEvent): boolean  [API 1+]
+ onKeyMultiple(int, int, KeyEvent): boolean  [API 1+]
+ onKeyPreIme(int, KeyEvent): boolean  [API 1+]
+ onKeyShortcut(int, KeyEvent): boolean  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onLayout(boolean, int, int, int, int): void  [API 1+]
+ onMeasure(int, int): void  [API 1+]
+ onPreDraw(): boolean  [API 1+]
+ onPrivateIMECommand(String, Bundle): boolean  [API 3+]
+ onReceiveContent(ContentInfo): ContentInfo  [API 1+]
+ onResolvePointerIcon(MotionEvent, int): PointerIcon  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onRtlPropertiesChanged(int): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ onScreenStateChanged(int): void  [API 1+]
+ onScrollChanged(int, int, int, int): void  [API 1+]
+ onSelectionChanged(int, int): void  [API 3+]
+ onTextChanged(CharSequence, int, int, int): void  [API 1+]
+ onTextContextMenuItem(int): boolean  [API 3+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ onTrackballEvent(MotionEvent): boolean  [API 1+]
+ onVisibilityAggregated(boolean): void  [API 1+]
+ onVisibilityChanged(View, int): void  [API 1+]
+ onWindowFocusChanged(boolean): void  [API 1+]
+ performLongClick(): boolean  [API 1+]
+ removeTextChangedListener(TextWatcher): void  [API 1+]
~ resetResolvedDrawables(): void  [API 14-16, removed in 17]
~ resetResolvedLayoutDirection(): void  [API 14-15, removed in 16]
~ resolveDrawables(): void  [API 14-16, removed in 17]
~ resolveTextDirection(): void  [API 14-15, removed in 16]
+ sendAccessibilityEventUnchecked(AccessibilityEvent): void  [API 1+]
+ setAllCaps(boolean): void  [API 14+]
+ setAutoLinkMask(int): void  [API 1+]
+ setAutoSizeTextTypeUniformWithConfiguration(int, int, int, int): void  [API 26+]
+ setAutoSizeTextTypeUniformWithPresetSizes(int[], int): void  [API 26+]
+ setAutoSizeTextTypeWithDefaults(int): void  [API 26+]
+ setBreakStrategy(int): void  [API 23+]
+ setCompoundDrawablePadding(int): void  [API 1+]
+ setCompoundDrawableTintBlendMode(BlendMode): void  [API 29+]
+ setCompoundDrawableTintList(ColorStateList): void  [API 23+]
+ setCompoundDrawableTintMode(PorterDuff.Mode): void  [API 23+]
+ setCompoundDrawables(Drawable, Drawable, Drawable, Drawable): void  [API 1+]
+ setCompoundDrawablesRelative(Drawable, Drawable, Drawable, Drawable): void  [API 17+]
+ setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable, Drawable, Drawable, Drawable): void  [API 17+]
+ setCompoundDrawablesRelativeWithIntrinsicBounds(int, int, int, int): void  [API 17+]
+ setCompoundDrawablesWithIntrinsicBounds(Drawable, Drawable, Drawable, Drawable): void  [API 1+]
+ setCompoundDrawablesWithIntrinsicBounds(int, int, int, int): void  [API 3+]
+ setCursorVisible(boolean): void  [API 1+]
+ setCustomInsertionActionModeCallback(ActionMode.Callback): void  [API 23+]
+ setCustomSelectionActionModeCallback(ActionMode.Callback): void  [API 11+]
~ setEditableFactory(Editable.Factory): void  [API 1+]
+ setElegantTextHeight(boolean): void  [API 21+]
+ setEllipsize(TextUtils.TruncateAt): void  [API 1+]
+ setEms(int): void  [API 1+]
+ setEnabled(boolean): void  [API 1+]
+ setError(CharSequence): void  [API 1+]
+ setError(CharSequence, Drawable): void  [API 1+]
+ setExtractedText(ExtractedText): void  [API 3+]
+ setFallbackLineSpacing(boolean): void  [API 28+]
+ setFilters(InputFilter[]): void  [API 1+]
+ setFirstBaselineToTopHeight(int): void  [API 28+]
+ setFocusedSearchResultHighlightColor(int): void  [API 34+]
+ setFocusedSearchResultIndex(int): void  [API 34+]
+ setFontFeatureSettings(String): void  [API 21+]
+ setFontVariationSettings(String): boolean  [API 26+]
+ setFrame(int, int, int, int): boolean  [API 1+]
+ setFreezesText(boolean): void  [API 1+]
+ setGravity(int): void  [API 1+]
+ setHeight(int): void  [API 1+]
+ setHighlightColor(int): void  [API 1+]
+ setHighlights(Highlights): void  [API 34+]
+ setHint(CharSequence): void  [API 1+]
+ setHint(int): void  [API 1+]
+ setHintTextColor(ColorStateList): void  [API 1+]
+ setHintTextColor(int): void  [API 1+]
+ setHorizontallyScrolling(boolean): void  [API 1+]
+ setHyphenationFrequency(int): void  [API 23+]
+ setImeActionLabel(CharSequence, int): void  [API 3+]
+ setImeHintLocales(LocaleList): void  [API 24+]
+ setImeOptions(int): void  [API 3+]
+ setIncludeFontPadding(boolean): void  [API 1+]
+ setInputExtras(int): void  [API 3+]
+ setInputType(int): void  [API 3+]
+ setJustificationMode(int): void  [API 26+]
+ setKeyListener(KeyListener): void  [API 1+]
+ setLastBaselineToBottomHeight(int): void  [API 28+]
+ setLetterSpacing(float): void  [API 21+]
+ setLineBreakStyle(int): void  [API 33+]
+ setLineBreakWordStyle(int): void  [API 33+]
+ setLineHeight(int): void  [API 28+]
+ setLineHeight(int, float): void  [API 34+]
+ setLineSpacing(float, float): void  [API 1+]
+ setLines(int): void  [API 1+]
+ setLinkTextColor(ColorStateList): void  [API 1+]
~ setLinkTextColor(int): void  [API 1+]
+ setLinksClickable(boolean): void  [API 1+]
+ setLocalePreferredLineHeightForMinimumUsed(boolean): void  [API 35+]
+ setMarqueeRepeatLimit(int): void  [API 2+]
+ setMaxEms(int): void  [API 1+]
+ setMaxHeight(int): void  [API 1+]
+ setMaxLines(int): void  [API 1+]
+ setMaxWidth(int): void  [API 1+]
+ setMinEms(int): void  [API 1+]
+ setMinHeight(int): void  [API 1+]
+ setMinLines(int): void  [API 1+]
+ setMinWidth(int): void  [API 1+]
+ setMinimumFontMetrics(Paint.FontMetrics): void  [API 35+]
+ setMovementMethod(MovementMethod): void  [API 1+]
+ setOnEditorActionListener(TextView.OnEditorActionListener): void  [API 3+]
+ setPadding(int, int, int, int): void  [API 1+]
+ setPaddingRelative(int, int, int, int): void  [API 16+]
+ setPaintFlags(int): void  [API 1+]
+ setPrivateImeOptions(String): void  [API 3+]
+ setRawInputType(int): void  [API 3+]
~ setScroller(Scroller): void  [API 1+]
+ setSearchResultHighlightColor(int): void  [API 34+]
+ setSearchResultHighlights(int[]): void  [API 34+]
+ setSelectAllOnFocus(boolean): void  [API 1+]
+ setSelected(boolean): void  [API 1+]
+ setShadowLayer(float, float, float, int): void  [API 1+]
+ setShiftDrawingOffsetForStartOverhang(boolean): void  [API 35+]
+ setShowSoftInputOnFocus(boolean): void  [API 21+]
+ setSingleLine(): void  [API 1+]
+ setSingleLine(boolean): void  [API 1+]
+ setSpannableFactory(Spannable.Factory): void  [API 1+]
+ setText(CharSequence): void  [API 1+]
+ setText(CharSequence, TextView.BufferType): void  [API 1+]
+ setText(char[], int, int): void  [API 1+]
+ setText(int): void  [API 1+]
+ setText(int, TextView.BufferType): void  [API 1+]
+ setTextAppearance(Context, int): void  [API 1+, deprecated in 23]
+ setTextAppearance(int): void  [API 23+]
+ setTextClassifier(TextClassifier): void  [API 26+]
+ setTextColor(ColorStateList): void  [API 1+]
+ setTextColor(int): void  [API 1+]
+ setTextCursorDrawable(Drawable): void  [API 29+]
+ setTextCursorDrawable(int): void  [API 29+]
~ setTextIsSelectable(boolean): void  [API 11+]
+ setTextKeepState(CharSequence): void  [API 1+]
+ setTextKeepState(CharSequence, TextView.BufferType): void  [API 1+]
+ setTextLocale(Locale): void  [API 17+]
+ setTextLocales(LocaleList): void  [API 24+]
+ setTextMetricsParams(PrecomputedText.Params): void  [API 28+]
+ setTextScaleX(float): void  [API 1+]
+ setTextSelectHandle(Drawable): void  [API 29+]
+ setTextSelectHandle(int): void  [API 29+]
+ setTextSelectHandleLeft(Drawable): void  [API 29+]
+ setTextSelectHandleLeft(int): void  [API 29+]
+ setTextSelectHandleRight(Drawable): void  [API 29+]
+ setTextSelectHandleRight(int): void  [API 29+]
+ setTextSize(float): void  [API 1+]
+ setTextSize(int, float): void  [API 1+]
+ setTransformationMethod(TransformationMethod): void  [API 1+]
+ setTypeface(Typeface): void  [API 1+]
+ setTypeface(Typeface, int): void  [API 1+]
+ setUseBoundsForWidth(boolean): void  [API 35+]
+ setWidth(int): void  [API 1+]
+ showContextMenu(): boolean  [API 1+]
+ showContextMenu(float, float): boolean  [API 1+]
+ verifyDrawable(Drawable): boolean  [API 1+]
```

## TextView.BufferType — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ EDITABLE [field]  [API 1+]
+ NORMAL [field]  [API 1+]
+ SPANNABLE [field]  [API 1+]
+ valueOf(String): TextView.BufferType [static]  [API 1+]
+ values(): TextView.BufferType[] [static]  [API 1+]
```

## TextView.OnEditorActionListener — framework; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ onEditorAction(TextView, int, KeyEvent): boolean  [API 3+]
```

## TextView.SavedState — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## ThemedSpinnerAdapter — framework; API 23+ (0 +, 0 *, 2 ~, 0 -)

```
~ getDropDownViewTheme(): Resources.Theme  [API 23+]
~ setDropDownViewTheme(Resources.Theme): void  [API 23+]
```

## TimePicker — framework; API 1+ (21 +, 0 *, 5 ~, 0 -)

```
+ TimePicker(Context)  [API 1+]
+ TimePicker(Context, AttributeSet)  [API 1+]
+ TimePicker(Context, AttributeSet, int)  [API 1+]
+ TimePicker(Context, AttributeSet, int, int)  [API 21+]
~ autofill(AutofillValue): void  [API 1+]
+ dispatchProvideAutofillStructure(ViewStructure, int): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
~ getAutofillType(): int  [API 1+]
~ getAutofillValue(): AutofillValue  [API 1+]
~ getBaseline(): int  [API 1+]
+ getCurrentHour(): Integer  [API 1+, deprecated in 23]
+ getCurrentMinute(): Integer  [API 1+, deprecated in 23]
+ getHour(): int  [API 23+]
+ getMinute(): int  [API 23+]
+ is24HourView(): boolean  [API 1+]
+ isEnabled(): boolean  [API 1+]
+ onRestoreInstanceState(Parcelable): void  [API 1+]
+ onSaveInstanceState(): Parcelable  [API 1+]
+ setCurrentHour(Integer): void  [API 1+, deprecated in 23]
+ setCurrentMinute(Integer): void  [API 1+, deprecated in 23]
+ setEnabled(boolean): void  [API 1+]
+ setHour(int): void  [API 23+]
+ setIs24HourView(Boolean): void  [API 1+]
+ setMinute(int): void  [API 23+]
+ setOnTimeChangedListener(TimePicker.OnTimeChangedListener): void  [API 1+]
~ validateInput(): boolean  [API 26+]
```

## TimePicker.OnTimeChangedListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTimeChanged(TimePicker, int, int): void  [API 1+]
```

## Toast — framework; API 1+ (16 +, 0 *, 0 ~, 4 -)

```
+ Toast(Context)  [API 1+]
- addCallback(Toast.Callback): void  [API 30+]
+ cancel(): void  [API 1+]
+ getDuration(): int  [API 1+]
+ getGravity(): int  [API 1+]
- getHorizontalMargin(): float  [API 1+]
- getVerticalMargin(): float  [API 1+]
+ getView(): View  [API 1+, deprecated in 30]
+ getXOffset(): int  [API 1+]
+ getYOffset(): int  [API 1+]
+ makeText(Context, CharSequence, int): Toast [static]  [API 1+]
+ makeText(Context, int, int): Toast [static]  [API 1+]
- removeCallback(Toast.Callback): void  [API 30+]
+ setDuration(int): void  [API 1+]
+ setGravity(int, int, int): void  [API 1+]
+ setMargin(float, float): void  [API 1+]
+ setText(CharSequence): void  [API 1+]
+ setText(int): void  [API 1+]
+ setView(View): void  [API 1+, deprecated in 30]
+ show(): void  [API 1+]
```

## Toast.Callback — declaration; API 30+ (3 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 30+]
+ onToastHidden(): void  [API 30+]
+ onToastShown(): void  [API 30+]
```

## ToggleButton — framework; API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ ToggleButton(Context)  [API 1+]
+ ToggleButton(Context, AttributeSet)  [API 1+]
+ ToggleButton(Context, AttributeSet, int)  [API 1+]
+ ToggleButton(Context, AttributeSet, int, int)  [API 21+]
+ drawableStateChanged(): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getDisabledAlpha(): float  [API 29+]
+ getTextOff(): CharSequence  [API 1+]
+ getTextOn(): CharSequence  [API 1+]
+ onFinishInflate(): void  [API 1+]
+ setBackgroundDrawable(Drawable): void  [API 1+]
+ setChecked(boolean): void  [API 1+]
+ setTextOff(CharSequence): void  [API 1+]
+ setTextOn(CharSequence): void  [API 1+]
```

## Toolbar — framework; API 21+ (81 +, 0 *, 0 ~, 0 -)

```
+ Toolbar(Context)  [API 21+]
+ Toolbar(Context, AttributeSet)  [API 21+]
+ Toolbar(Context, AttributeSet, int)  [API 21+]
+ Toolbar(Context, AttributeSet, int, int)  [API 21+]
+ checkLayoutParams(ViewGroup.LayoutParams): boolean  [API 21+]
+ collapseActionView(): void  [API 21+]
+ dismissPopupMenus(): void  [API 21+]
+ generateDefaultLayoutParams(): Toolbar.LayoutParams  [API 21+]
+ generateLayoutParams(AttributeSet): Toolbar.LayoutParams  [API 21+]
+ generateLayoutParams(ViewGroup.LayoutParams): Toolbar.LayoutParams  [API 21+]
+ getCollapseContentDescription(): CharSequence  [API 29+]
+ getCollapseIcon(): Drawable  [API 29+]
+ getContentInsetEnd(): int  [API 21+]
+ getContentInsetEndWithActions(): int  [API 24+]
+ getContentInsetLeft(): int  [API 21+]
+ getContentInsetRight(): int  [API 21+]
+ getContentInsetStart(): int  [API 21+]
+ getContentInsetStartWithNavigation(): int  [API 24+]
+ getCurrentContentInsetEnd(): int  [API 24+]
+ getCurrentContentInsetLeft(): int  [API 24+]
+ getCurrentContentInsetRight(): int  [API 24+]
+ getCurrentContentInsetStart(): int  [API 24+]
+ getLogo(): Drawable  [API 21+]
+ getLogoDescription(): CharSequence  [API 21+]
+ getMenu(): Menu  [API 21+]
+ getNavigationContentDescription(): CharSequence  [API 21+]
+ getNavigationIcon(): Drawable  [API 21+]
+ getOverflowIcon(): Drawable  [API 23+]
+ getPopupTheme(): int  [API 21+]
+ getSubtitle(): CharSequence  [API 21+]
+ getTitle(): CharSequence  [API 21+]
+ getTitleMarginBottom(): int  [API 24+]
+ getTitleMarginEnd(): int  [API 24+]
+ getTitleMarginStart(): int  [API 24+]
+ getTitleMarginTop(): int  [API 24+]
+ hasExpandedActionView(): boolean  [API 21+]
+ hideOverflowMenu(): boolean  [API 21+]
+ inflateMenu(int): void  [API 21+]
+ isOverflowMenuShowing(): boolean  [API 21+]
+ onAttachedToWindow(): void  [API 21+]
+ onDetachedFromWindow(): void  [API 21+]
+ onLayout(boolean, int, int, int, int): void  [API 21+]
+ onMeasure(int, int): void  [API 21+]
+ onRestoreInstanceState(Parcelable): void  [API 21+]
+ onRtlPropertiesChanged(int): void  [API 21+]
+ onSaveInstanceState(): Parcelable  [API 21+]
+ onTouchEvent(MotionEvent): boolean  [API 21+]
+ setCollapseContentDescription(CharSequence): void  [API 29+]
+ setCollapseContentDescription(int): void  [API 29+]
+ setCollapseIcon(Drawable): void  [API 29+]
+ setCollapseIcon(int): void  [API 29+]
+ setContentInsetEndWithActions(int): void  [API 24+]
+ setContentInsetStartWithNavigation(int): void  [API 24+]
+ setContentInsetsAbsolute(int, int): void  [API 21+]
+ setContentInsetsRelative(int, int): void  [API 21+]
+ setLogo(Drawable): void  [API 21+]
+ setLogo(int): void  [API 21+]
+ setLogoDescription(CharSequence): void  [API 21+]
+ setLogoDescription(int): void  [API 21+]
+ setNavigationContentDescription(CharSequence): void  [API 21+]
+ setNavigationContentDescription(int): void  [API 21+]
+ setNavigationIcon(Drawable): void  [API 21+]
+ setNavigationIcon(int): void  [API 21+]
+ setNavigationOnClickListener(View.OnClickListener): void  [API 21+]
+ setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener): void  [API 21+]
+ setOverflowIcon(Drawable): void  [API 23+]
+ setPopupTheme(int): void  [API 21+]
+ setSubtitle(CharSequence): void  [API 21+]
+ setSubtitle(int): void  [API 21+]
+ setSubtitleTextAppearance(Context, int): void  [API 21+]
+ setSubtitleTextColor(int): void  [API 21+]
+ setTitle(CharSequence): void  [API 21+]
+ setTitle(int): void  [API 21+]
+ setTitleMargin(int, int, int, int): void  [API 24+]
+ setTitleMarginBottom(int): void  [API 24+]
+ setTitleMarginEnd(int): void  [API 24+]
+ setTitleMarginStart(int): void  [API 24+]
+ setTitleMarginTop(int): void  [API 24+]
+ setTitleTextAppearance(Context, int): void  [API 21+]
+ setTitleTextColor(int): void  [API 21+]
+ showOverflowMenu(): boolean  [API 21+]
```

## Toolbar.LayoutParams — framework; API 21+ (8 +, 0 *, 0 ~, 0 -)

```
+ LayoutParams(ActionBar.LayoutParams)  [API 21+]
+ LayoutParams(Context, AttributeSet)  [API 21+]
+ LayoutParams(Toolbar.LayoutParams)  [API 21+]
+ LayoutParams(ViewGroup.LayoutParams)  [API 21+]
+ LayoutParams(ViewGroup.MarginLayoutParams)  [API 21+]
+ LayoutParams(int)  [API 21+]
+ LayoutParams(int, int)  [API 21+]
+ LayoutParams(int, int, int)  [API 21+]
```

## Toolbar.OnMenuItemClickListener — framework; API 21+ (1 +, 0 *, 0 ~, 0 -)

```
+ onMenuItemClick(MenuItem): boolean  [API 21+]
```

## TwoLineListItem — framework; API 1+, deprecated in 17 (7 +, 0 *, 0 ~, 1 -)

```
+ TwoLineListItem(Context)  [API 1+]
+ TwoLineListItem(Context, AttributeSet)  [API 1+]
+ TwoLineListItem(Context, AttributeSet, int)  [API 1+]
- TwoLineListItem(Context, AttributeSet, int, int)  [API 21+, deprecated in 21]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getText1(): TextView  [API 1+]
+ getText2(): TextView  [API 1+]
+ onFinishInflate(): void  [API 1+]
```

## VideoView — declaration; API 1+ (0 +, 0 *, 0 ~, 39 -)

```
- VideoView(Context)  [API 1+]
- VideoView(Context, AttributeSet)  [API 1+]
- VideoView(Context, AttributeSet, int)  [API 1+]
- VideoView(Context, AttributeSet, int, int)  [API 21+]
- addSubtitleSource(InputStream, MediaFormat): void  [API 19+]
- canPause(): boolean  [API 1+]
- canSeekBackward(): boolean  [API 1+]
- canSeekForward(): boolean  [API 1+]
- draw(Canvas): void  [API 1+]
- getAccessibilityClassName(): CharSequence  [API 1+]
- getAudioSessionId(): int  [API 1+]
- getBufferPercentage(): int  [API 1+]
- getCurrentPosition(): int  [API 1+]
- getDuration(): int  [API 1+]
- isPlaying(): boolean  [API 1+]
- onAttachedToWindow(): void  [API 1+]
- onDetachedFromWindow(): void  [API 1+]
- onKeyDown(int, KeyEvent): boolean  [API 1+]
- onLayout(boolean, int, int, int, int): void  [API 1+]
- onMeasure(int, int): void  [API 1+]
- onTouchEvent(MotionEvent): boolean  [API 1+]
- onTrackballEvent(MotionEvent): boolean  [API 1+]
- pause(): void  [API 1+]
- resolveAdjustedSize(int, int): int  [API 1+]
- resume(): void  [API 8+]
- seekTo(int): void  [API 1+]
- setAudioAttributes(AudioAttributes): void  [API 26+]
- setAudioFocusRequest(int): void  [API 26+]
- setMediaController(MediaController): void  [API 1+]
- setOnCompletionListener(MediaPlayer.OnCompletionListener): void  [API 1+]
- setOnErrorListener(MediaPlayer.OnErrorListener): void  [API 1+]
- setOnInfoListener(MediaPlayer.OnInfoListener): void  [API 17+]
- setOnPreparedListener(MediaPlayer.OnPreparedListener): void  [API 1+]
- setVideoPath(String): void  [API 1+]
- setVideoURI(Uri): void  [API 1+]
- setVideoURI(Uri, Map): void  [API 21+]
- start(): void  [API 1+]
- stopPlayback(): void  [API 1+]
- suspend(): void  [API 8+]
```

## ViewAnimator — framework; API 1+ (21 +, 0 *, 2 ~, 1 -)

```
+ ViewAnimator(Context)  [API 1+]
+ ViewAnimator(Context, AttributeSet)  [API 1+]
+ addView(View, int, ViewGroup.LayoutParams): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
- getAnimateFirstView(): boolean  [API 17+]
~ getBaseline(): int  [API 1+]
+ getCurrentView(): View  [API 1+]
+ getDisplayedChild(): int  [API 1+]
+ getInAnimation(): Animation  [API 1+]
+ getOutAnimation(): Animation  [API 1+]
+ removeAllViews(): void  [API 1+]
+ removeView(View): void  [API 1+]
+ removeViewAt(int): void  [API 1+]
+ removeViewInLayout(View): void  [API 1+]
+ removeViews(int, int): void  [API 1+]
+ removeViewsInLayout(int, int): void  [API 1+]
~ setAnimateFirstView(boolean): void  [API 1+]
+ setDisplayedChild(int): void  [API 1+]
+ setInAnimation(Animation): void  [API 1+]
+ setInAnimation(Context, int): void  [API 1+]
+ setOutAnimation(Animation): void  [API 1+]
+ setOutAnimation(Context, int): void  [API 1+]
+ showNext(): void  [API 1+]
+ showPrevious(): void  [API 1+]
```

## ViewFlipper — framework; API 1+ (12 +, 0 *, 0 ~, 1 -)

```
+ ViewFlipper(Context)  [API 1+]
+ ViewFlipper(Context, AttributeSet)  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
- getFlipInterval(): int  [API 29+]
+ isAutoStart(): boolean  [API 7+]
+ isFlipping(): boolean  [API 1+]
+ onAttachedToWindow(): void  [API 1+]
+ onDetachedFromWindow(): void  [API 1+]
+ onWindowVisibilityChanged(int): void  [API 1+]
+ setAutoStart(boolean): void  [API 7+]
+ setFlipInterval(int): void  [API 1+]
+ startFlipping(): void  [API 1+]
+ stopFlipping(): void  [API 1+]
```

## ViewSwitcher — framework; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ ViewSwitcher(Context)  [API 1+]
+ ViewSwitcher(Context, AttributeSet)  [API 1+]
+ addView(View, int, ViewGroup.LayoutParams): void  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ getNextView(): View  [API 1+]
+ reset(): void  [API 1+]
+ setFactory(ViewSwitcher.ViewFactory): void  [API 1+]
```

## ViewSwitcher.ViewFactory — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ makeView(): View  [API 1+]
```

## WrapperListAdapter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getWrappedAdapter(): ListAdapter  [API 1+]
```

## ZoomButton — framework; API 1+, deprecated in 26 (11 +, 0 *, 0 ~, 0 -)

```
+ ZoomButton(Context)  [API 1+]
+ ZoomButton(Context, AttributeSet)  [API 1+]
+ ZoomButton(Context, AttributeSet, int)  [API 1+]
+ ZoomButton(Context, AttributeSet, int, int)  [API 21+]
+ dispatchUnhandledMove(View, int): boolean  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ onKeyUp(int, KeyEvent): boolean  [API 1+]
+ onLongClick(View): boolean  [API 1+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ setEnabled(boolean): void  [API 1+]
+ setZoomSpeed(long): void  [API 1+]
```

## ZoomButtonsController — declaration; API 4+, deprecated in 26 (0 +, 0 *, 0 ~, 13 -)

```
- ZoomButtonsController(View)  [API 4+]
- getContainer(): ViewGroup  [API 4+]
- getZoomControls(): View  [API 4+]
- isAutoDismissed(): boolean  [API 4+]
- isVisible(): boolean  [API 4+]
- onTouch(View, MotionEvent): boolean  [API 4+]
- setAutoDismissed(boolean): void  [API 4+]
- setFocusable(boolean): void  [API 4+]
- setOnZoomListener(ZoomButtonsController.OnZoomListener): void  [API 4+]
- setVisible(boolean): void  [API 4+]
- setZoomInEnabled(boolean): void  [API 4+]
- setZoomOutEnabled(boolean): void  [API 4+]
- setZoomSpeed(long): void  [API 4+]
```

## ZoomButtonsController.OnZoomListener — declaration; API 4+, deprecated in 29 (2 +, 0 *, 0 ~, 0 -)

```
+ onVisibilityChanged(boolean): void  [API 4+]
+ onZoom(boolean): void  [API 4+]
```

## ZoomControls — framework; API 1+, deprecated in 29 (12 +, 0 *, 0 ~, 0 -)

```
+ ZoomControls(Context)  [API 1+]
+ ZoomControls(Context, AttributeSet)  [API 1+]
+ getAccessibilityClassName(): CharSequence  [API 1+]
+ hasFocus(): boolean  [API 1+]
+ hide(): void  [API 1+]
+ onTouchEvent(MotionEvent): boolean  [API 1+]
+ setIsZoomInEnabled(boolean): void  [API 1+]
+ setIsZoomOutEnabled(boolean): void  [API 1+]
+ setOnZoomInClickListener(View.OnClickListener): void  [API 1+]
+ setOnZoomOutClickListener(View.OnClickListener): void  [API 1+]
+ setZoomSpeed(long): void  [API 1+]
+ show(): void  [API 1+]
```


package android.preference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** One setting: title, summary, an optional widget; its value persists in the SharedPreferences of its PreferenceManager. */
public class Preference implements Comparable<Preference> {
  public static final int DEFAULT_ORDER = Integer.MAX_VALUE;
  public interface OnPreferenceChangeListener { boolean onPreferenceChange(Preference preference, Object newValue); }
  public interface OnPreferenceClickListener { boolean onPreferenceClick(Preference preference); }
  interface OnPreferenceChangeInternalListener { void onPreferenceChange(Preference preference); void onPreferenceHierarchyChange(Preference preference); }
  public static class BaseSavedState extends android.view.AbsSavedState {
    public BaseSavedState(android.os.Parcel source) { super(source); }
    public BaseSavedState(android.os.Parcelable superState) { super(superState); }
  }
  static final int[] ATTRS = {android.R.attr.icon, android.R.attr.key, android.R.attr.title, android.R.attr.summary, android.R.attr.order,
      android.R.attr.fragment, android.R.attr.layout, android.R.attr.widgetLayout, android.R.attr.enabled, android.R.attr.selectable,
      android.R.attr.persistent, android.R.attr.dependency, android.R.attr.defaultValue, android.R.attr.shouldDisableView, android.R.attr.iconSpaceReserved};

  private final Context mContext;
  private PreferenceManager mPreferenceManager;
  private long mId;
  private OnPreferenceChangeListener mOnChangeListener;
  private OnPreferenceClickListener mOnClickListener;
  private int mOrder = DEFAULT_ORDER;
  private CharSequence mTitle, mSummary;
  private int mTitleRes, mIconResId;
  private Drawable mIcon;
  private String mKey, mFragment, mDependencyKey;
  private Intent mIntent;
  private Bundle mExtras;
  private boolean mEnabled = true, mSelectable = true, mRequiresKey, mPersistent = true, mDependencyMet = true, mParentDependencyMet = true;
  private boolean mShouldDisableView = true, mIconSpaceReserved, mVisible = true;
  private Object mDefaultValue;
  private int mLayoutResId, mWidgetLayoutResId;
  private OnPreferenceChangeInternalListener mListener;
  private List<Preference> mDependents;
  private PreferenceGroup mParentGroup;
  private boolean mBaseMethodCalled;

  public Preference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mContext = context;
    if (attrs == null) return;
    TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);
    mIconResId = a.getResourceId(0, 0);
    mKey = a.getString(1);
    mTitleRes = a.getResourceId(2, 0);
    mTitle = a.getText(2);
    mSummary = a.getText(3);
    mOrder = a.getInt(4, mOrder);
    mFragment = a.getString(5);
    mLayoutResId = a.getResourceId(6, 0);
    mWidgetLayoutResId = a.getResourceId(7, mWidgetLayoutResId);
    mEnabled = a.getBoolean(8, true);
    mSelectable = a.getBoolean(9, true);
    mPersistent = a.getBoolean(10, mPersistent);
    mDependencyKey = a.getString(11);
    if (a.hasValue(12)) mDefaultValue = onGetDefaultValue(a, 12);
    mShouldDisableView = a.getBoolean(13, mShouldDisableView);
    mIconSpaceReserved = a.getBoolean(14, false);
    a.recycle();
  }
  public Preference(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public Preference(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public Preference(Context context) { this(context, null); }

  protected Object onGetDefaultValue(TypedArray a, int index) { return null; }
  public void setIntent(Intent intent) { mIntent = intent; }
  public Intent getIntent() { return mIntent; }
  public void setFragment(String fragment) { mFragment = fragment; }
  public String getFragment() { return mFragment; }
  public Bundle getExtras() { if (mExtras == null) mExtras = new Bundle(); return mExtras; }
  public Bundle peekExtras() { return mExtras; }
  public void setLayoutResource(int layoutResId) { mLayoutResId = layoutResId; }
  public int getLayoutResource() { return mLayoutResId; }
  public void setWidgetLayoutResource(int widgetLayoutResId) { mWidgetLayoutResId = widgetLayoutResId; }
  public int getWidgetLayoutResource() { return mWidgetLayoutResId; }

  // ---------------------------------------------------------------- views
  public View getView(View convertView, ViewGroup parent) {
    if (convertView == null) convertView = onCreateView(parent);
    onBindView(convertView);
    return convertView;
  }
  static float dp(Context c, float v) { return v * c.getResources().getDisplayMetrics().density; }
  protected View onCreateView(ViewGroup parent) {
    if (mLayoutResId != 0) {
      View v = LayoutInflater.from(mContext).inflate(mLayoutResId, parent, false);
      ViewGroup frame = (ViewGroup) v.findViewById(android.R.id.widget_frame);
      if (frame != null) { if (mWidgetLayoutResId != 0) LayoutInflater.from(mContext).inflate(mWidgetLayoutResId, frame); else frame.setVisibility(View.GONE); }
      return v;
    }
    // the framework's preference row: [icon] title / summary [widget]
    LinearLayout row = new LinearLayout(mContext);
    row.setOrientation(LinearLayout.HORIZONTAL);
    row.setGravity(Gravity.CENTER_VERTICAL);
    row.setMinimumHeight((int) dp(mContext, 64));
    int pad = (int) dp(mContext, 16);
    row.setPadding(pad, (int) dp(mContext, 8), pad / 2, (int) dp(mContext, 8));
    ImageView icon = new ImageView(mContext);
    icon.setId(android.R.id.icon);
    LinearLayout.LayoutParams ilp = new LinearLayout.LayoutParams((int) dp(mContext, 40), (int) dp(mContext, 40));
    ilp.rightMargin = pad;
    row.addView(icon, ilp);
    LinearLayout texts = new LinearLayout(mContext);
    texts.setOrientation(LinearLayout.VERTICAL);
    TextView title = new TextView(mContext);
    title.setId(android.R.id.title);
    title.setTextSize(18);
    title.setSingleLine(true);
    texts.addView(title, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    TextView summary = new TextView(mContext);
    summary.setId(android.R.id.summary);
    summary.setTextSize(14);
    summary.setAlpha(0.7f);
    summary.setMaxLines(4);
    texts.addView(summary, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    row.addView(texts, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
    LinearLayout frame = new LinearLayout(mContext);
    frame.setId(android.R.id.widget_frame);
    frame.setGravity(Gravity.CENTER_VERTICAL);
    frame.setPadding(pad / 2, 0, pad / 2, 0);
    row.addView(frame, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
    View widget = onCreateWidget(frame);
    if (mWidgetLayoutResId != 0) LayoutInflater.from(mContext).inflate(mWidgetLayoutResId, frame);
    else if (widget != null) frame.addView(widget);
    else frame.setVisibility(View.GONE);
    return row;
  }
  /** web: the built-in widget of the framework row (a CheckBox for CheckBoxPreference...) */
  View onCreateWidget(ViewGroup frame) { return null; }
  protected void onBindView(View view) {
    final TextView titleView = (TextView) view.findViewById(android.R.id.title);
    if (titleView != null) {
      final CharSequence title = getTitle();
      if (!TextUtils.isEmpty(title)) { titleView.setText(title); titleView.setVisibility(View.VISIBLE); }
      else titleView.setVisibility(View.GONE);
    }
    final TextView summaryView = (TextView) view.findViewById(android.R.id.summary);
    if (summaryView != null) {
      final CharSequence summary = getSummary();
      if (!TextUtils.isEmpty(summary)) { summaryView.setText(summary); summaryView.setVisibility(View.VISIBLE); }
      else summaryView.setVisibility(View.GONE);
    }
    final ImageView imageView = (ImageView) view.findViewById(android.R.id.icon);
    if (imageView != null) {
      if (mIconResId != 0 || mIcon != null) {
        if (mIcon == null) mIcon = getContext().getResources().getDrawable(mIconResId);
        if (mIcon != null) imageView.setImageDrawable(mIcon);
      }
      imageView.setVisibility(mIcon != null ? View.VISIBLE : mIconSpaceReserved ? View.INVISIBLE : View.GONE);
    }
    if (mShouldDisableView) setEnabledStateOnViews(view, isEnabled());
  }
  private void setEnabledStateOnViews(View v, boolean enabled) {
    v.setEnabled(enabled);
    v.setAlpha(enabled ? 1f : 0.5f);
    if (v instanceof ViewGroup) { ViewGroup vg = (ViewGroup) v; for (int i = vg.getChildCount() - 1; i >= 0; i--) vg.getChildAt(i).setEnabled(enabled); }
  }

  // ---------------------------------------------------------------- properties
  public void setOrder(int order) { if (order != mOrder) { mOrder = order; notifyHierarchyChanged(); } }
  public int getOrder() { return mOrder; }
  public void setTitle(CharSequence title) { if (title == null && mTitle != null || title != null && !title.equals(mTitle)) { mTitleRes = 0; mTitle = title; notifyChanged(); } }
  public void setTitle(int titleResId) { setTitle(mContext.getString(titleResId)); mTitleRes = titleResId; }
  public int getTitleRes() { return mTitleRes; }
  public CharSequence getTitle() { return mTitle; }
  public void setIcon(Drawable icon) { if ((icon == null && mIcon != null) || (icon != null && mIcon != icon)) { mIcon = icon; notifyChanged(); } }
  public void setIcon(int iconResId) { if (mIconResId != iconResId) { mIconResId = iconResId; setIcon(iconResId == 0 ? null : mContext.getResources().getDrawable(iconResId)); } }
  public Drawable getIcon() { if (mIcon == null && mIconResId != 0) mIcon = getContext().getResources().getDrawable(mIconResId); return mIcon; }
  public CharSequence getSummary() { return mSummary; }
  public void setSummary(CharSequence summary) { if (summary == null && mSummary != null || summary != null && !summary.equals(mSummary)) { mSummary = summary; notifyChanged(); } }
  public void setSummary(int summaryResId) { setSummary(mContext.getString(summaryResId)); }
  public void setEnabled(boolean enabled) { if (mEnabled != enabled) { mEnabled = enabled; notifyDependencyChange(shouldDisableDependents()); notifyChanged(); } }
  public boolean isEnabled() { return mEnabled && mDependencyMet && mParentDependencyMet; }
  public void setSelectable(boolean selectable) { if (mSelectable != selectable) { mSelectable = selectable; notifyChanged(); } }
  public boolean isSelectable() { return mSelectable; }
  public void setShouldDisableView(boolean shouldDisableView) { mShouldDisableView = shouldDisableView; notifyChanged(); }
  public boolean getShouldDisableView() { return mShouldDisableView; }
  public void setIconSpaceReserved(boolean r) { mIconSpaceReserved = r; notifyChanged(); }
  public boolean isIconSpaceReserved() { return mIconSpaceReserved; }
  public void setSingleLineTitle(boolean b) {}
  public boolean isSingleLineTitle() { return true; }
  public void setRecycleEnabled(boolean b) {}
  public boolean isRecycleEnabled() { return true; }
  long getId() { return mId; }
  public void setKey(String key) { mKey = key; if (mRequiresKey && !hasKey()) requireKey(); }
  public String getKey() { return mKey; }
  void requireKey() { if (mKey == null) throw new IllegalStateException("Preference does not have a key assigned."); mRequiresKey = true; }
  public boolean hasKey() { return !TextUtils.isEmpty(mKey); }
  public boolean isPersistent() { return mPersistent; }
  protected boolean shouldPersist() { return mPreferenceManager != null && isPersistent() && hasKey(); }
  public void setPersistent(boolean persistent) { mPersistent = persistent; }
  protected boolean callChangeListener(Object newValue) { return mOnChangeListener == null || mOnChangeListener.onPreferenceChange(this, newValue); }
  public void setOnPreferenceChangeListener(OnPreferenceChangeListener l) { mOnChangeListener = l; }
  public OnPreferenceChangeListener getOnPreferenceChangeListener() { return mOnChangeListener; }
  public void setOnPreferenceClickListener(OnPreferenceClickListener l) { mOnClickListener = l; }
  public OnPreferenceClickListener getOnPreferenceClickListener() { return mOnClickListener; }
  public void performClick(PreferenceScreen preferenceScreen) {
    if (!isEnabled()) return;
    onClick();
    if (mOnClickListener != null && mOnClickListener.onPreferenceClick(this)) return;
    PreferenceManager preferenceManager = getPreferenceManager();
    if (preferenceManager != null) {
      PreferenceManager.OnPreferenceTreeClickListener listener = preferenceManager.getOnPreferenceTreeClickListener();
      if (preferenceScreen != null && listener != null && listener.onPreferenceTreeClick(preferenceScreen, this)) return;
    }
    if (mIntent != null) getContext().startActivity(mIntent);
  }
  protected void onClick() {}
  public Context getContext() { return mContext; }
  public SharedPreferences getSharedPreferences() { return mPreferenceManager == null ? null : mPreferenceManager.getSharedPreferences(); }
  public SharedPreferences.Editor getEditor() { return mPreferenceManager == null ? null : mPreferenceManager.getEditor(); }
  public boolean shouldCommit() { return mPreferenceManager != null && mPreferenceManager.shouldCommit(); }
  public PreferenceDataStore getPreferenceDataStore() { return null; }
  public void setPreferenceDataStore(PreferenceDataStore dataStore) {}
  public int compareTo(Preference another) {
    if (mOrder != another.mOrder) return mOrder - another.mOrder;
    if (mTitle == another.mTitle) return 0;
    if (mTitle == null) return 1;
    if (another.mTitle == null) return -1;
    return mTitle.toString().compareToIgnoreCase(another.mTitle.toString());
  }
  final void setOnPreferenceChangeInternalListener(OnPreferenceChangeInternalListener listener) { mListener = listener; }
  protected void notifyChanged() { if (mListener != null) mListener.onPreferenceChange(this); }
  protected void notifyHierarchyChanged() { if (mListener != null) mListener.onPreferenceHierarchyChange(this); }
  public PreferenceManager getPreferenceManager() { return mPreferenceManager; }
  protected void onAttachedToHierarchy(PreferenceManager preferenceManager) {
    mPreferenceManager = preferenceManager;
    mId = preferenceManager.getNextId();
    dispatchSetInitialValue();
  }
  protected void onAttachedToActivity() { registerDependency(); }
  void assignParent(PreferenceGroup parentGroup) { mParentGroup = parentGroup; }
  public PreferenceGroup getParent() { return mParentGroup; }
  private void registerDependency() {
    if (TextUtils.isEmpty(mDependencyKey)) return;
    Preference preference = findPreferenceInHierarchy(mDependencyKey);
    if (preference != null) preference.registerDependent(this);
    else throw new IllegalStateException("Dependency \"" + mDependencyKey + "\" not found for preference \"" + mKey + "\" (title: \"" + mTitle + "\"");
  }
  private void unregisterDependency() {
    if (mDependencyKey != null) { final Preference oldDependency = findPreferenceInHierarchy(mDependencyKey); if (oldDependency != null) oldDependency.unregisterDependent(this); }
  }
  protected Preference findPreferenceInHierarchy(String key) {
    if (TextUtils.isEmpty(key) || mPreferenceManager == null) return null;
    return mPreferenceManager.findPreference(key);
  }
  private void registerDependent(Preference dependent) {
    if (mDependents == null) mDependents = new ArrayList<>();
    mDependents.add(dependent);
    dependent.onDependencyChanged(this, shouldDisableDependents());
  }
  private void unregisterDependent(Preference dependent) { if (mDependents != null) mDependents.remove(dependent); }
  public void notifyDependencyChange(boolean disableDependents) {
    final List<Preference> dependents = mDependents;
    if (dependents == null) return;
    for (Preference d : new ArrayList<>(dependents)) d.onDependencyChanged(this, disableDependents);
  }
  public void onDependencyChanged(Preference dependency, boolean disableDependent) {
    if (mDependencyMet == disableDependent) { mDependencyMet = !disableDependent; notifyDependencyChange(shouldDisableDependents()); notifyChanged(); }
  }
  public void onParentChanged(Preference parent, boolean disableChild) {
    if (mParentDependencyMet == disableChild) { mParentDependencyMet = !disableChild; notifyDependencyChange(shouldDisableDependents()); notifyChanged(); }
  }
  public boolean shouldDisableDependents() { return !isEnabled(); }
  public void setDependency(String dependencyKey) { unregisterDependency(); mDependencyKey = dependencyKey; registerDependency(); }
  public String getDependency() { return mDependencyKey; }
  protected void onPrepareForRemoval() { unregisterDependency(); }
  public void setDefaultValue(Object defaultValue) { mDefaultValue = defaultValue; }
  private void dispatchSetInitialValue() {
    final boolean shouldPersist = shouldPersist();
    if (!shouldPersist || !getSharedPreferences().contains(mKey)) { if (mDefaultValue != null) onSetInitialValue(false, mDefaultValue); }
    else onSetInitialValue(true, null);
  }
  protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {}
  private void tryCommit(SharedPreferences.Editor editor) { editor.apply(); }
  protected boolean persistString(String value) {
    if (!shouldPersist()) return false;
    if (TextUtils.equals(value, getPersistedString(null))) return true;
    SharedPreferences.Editor editor = getEditor(); editor.putString(mKey, value); tryCommit(editor); return true;
  }
  protected String getPersistedString(String defaultReturnValue) { return shouldPersist() ? getSharedPreferences().getString(mKey, defaultReturnValue) : defaultReturnValue; }
  public boolean persistStringSet(Set<String> values) {
    if (!shouldPersist()) return false;
    SharedPreferences.Editor editor = getEditor(); editor.putStringSet(mKey, values); tryCommit(editor); return true;
  }
  public Set<String> getPersistedStringSet(Set<String> defaultReturnValue) { return shouldPersist() ? getSharedPreferences().getStringSet(mKey, defaultReturnValue) : defaultReturnValue; }
  protected boolean persistInt(int value) {
    if (!shouldPersist()) return false;
    SharedPreferences.Editor editor = getEditor(); editor.putInt(mKey, value); tryCommit(editor); return true;
  }
  protected int getPersistedInt(int defaultReturnValue) { return shouldPersist() ? getSharedPreferences().getInt(mKey, defaultReturnValue) : defaultReturnValue; }
  protected boolean persistFloat(float value) {
    if (!shouldPersist()) return false;
    SharedPreferences.Editor editor = getEditor(); editor.putFloat(mKey, value); tryCommit(editor); return true;
  }
  protected float getPersistedFloat(float defaultReturnValue) { return shouldPersist() ? getSharedPreferences().getFloat(mKey, defaultReturnValue) : defaultReturnValue; }
  protected boolean persistLong(long value) {
    if (!shouldPersist()) return false;
    SharedPreferences.Editor editor = getEditor(); editor.putLong(mKey, value); tryCommit(editor); return true;
  }
  protected long getPersistedLong(long defaultReturnValue) { return shouldPersist() ? getSharedPreferences().getLong(mKey, defaultReturnValue) : defaultReturnValue; }
  protected boolean persistBoolean(boolean value) {
    if (!shouldPersist()) return false;
    SharedPreferences.Editor editor = getEditor(); editor.putBoolean(mKey, value); tryCommit(editor); return true;
  }
  protected boolean getPersistedBoolean(boolean defaultReturnValue) { return shouldPersist() ? getSharedPreferences().getBoolean(mKey, defaultReturnValue) : defaultReturnValue; }
  @Override public String toString() { return getFilterableStringBuilder().toString(); }
  StringBuilder getFilterableStringBuilder() {
    StringBuilder sb = new StringBuilder();
    CharSequence title = getTitle(); if (!TextUtils.isEmpty(title)) sb.append(title).append(' ');
    CharSequence summary = getSummary(); if (!TextUtils.isEmpty(summary)) sb.append(summary).append(' ');
    if (sb.length() > 0) sb.setLength(sb.length() - 1);
    return sb;
  }
  public void saveHierarchyState(Bundle container) {}
  public void restoreHierarchyState(Bundle container) {}
  protected android.os.Parcelable onSaveInstanceState() { return android.view.AbsSavedState.EMPTY_STATE; }
  protected void onRestoreInstanceState(android.os.Parcelable state) {}
}

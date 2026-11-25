package android.preference;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PreferenceGroup extends Preference implements GenericInflater.Parent<Preference> {
  private final List<Preference> mPreferenceList = new ArrayList<>();
  private boolean mOrderingAsAdded = true;
  private int mCurrentPreferenceOrder = 0;
  private boolean mAttachedToActivity = false;
  public PreferenceGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    if (attrs != null) mOrderingAsAdded = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "orderingFromXml", true);
  }
  public PreferenceGroup(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public PreferenceGroup(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public void setOrderingAsAdded(boolean orderingAsAdded) { mOrderingAsAdded = orderingAsAdded; }
  public boolean isOrderingAsAdded() { return mOrderingAsAdded; }
  public void addItemFromInflater(Preference preference) { addPreference(preference); }
  public int getPreferenceCount() { return mPreferenceList.size(); }
  public Preference getPreference(int index) { return mPreferenceList.get(index); }
  public boolean addPreference(Preference preference) {
    if (mPreferenceList.contains(preference)) return true;
    if (preference.getOrder() == DEFAULT_ORDER) {
      if (mOrderingAsAdded) preference.setOrder(mCurrentPreferenceOrder++);
      if (preference instanceof PreferenceGroup) ((PreferenceGroup) preference).setOrderingAsAdded(mOrderingAsAdded);
    }
    int insertionIndex = Collections.binarySearch(mPreferenceList, preference);
    if (insertionIndex < 0) insertionIndex = insertionIndex * -1 - 1;
    if (!onPrepareAddPreference(preference)) return false;
    synchronized (this) { mPreferenceList.add(insertionIndex, preference); }
    preference.assignParent(this);
    if (getPreferenceManager() != null) preference.onAttachedToHierarchy(getPreferenceManager());
    if (mAttachedToActivity) preference.onAttachedToActivity();
    notifyHierarchyChanged();
    return true;
  }
  public boolean removePreference(Preference preference) { final boolean returnValue = removePreferenceInt(preference); notifyHierarchyChanged(); return returnValue; }
  private boolean removePreferenceInt(Preference preference) {
    synchronized (this) {
      preference.onPrepareForRemoval();
      preference.assignParent(null);
      return mPreferenceList.remove(preference);
    }
  }
  public void removeAll() {
    synchronized (this) { for (int i = mPreferenceList.size() - 1; i >= 0; i--) removePreferenceInt(mPreferenceList.get(0)); }
    notifyHierarchyChanged();
  }
  protected boolean onPrepareAddPreference(Preference preference) {
    preference.onParentChanged(this, shouldDisableDependents());
    return true;
  }
  public Preference findPreference(CharSequence key) {
    if (key == null) throw new IllegalArgumentException("Key cannot be null");
    if (TextUtils.equals(getKey(), key)) return this;
    final int preferenceCount = getPreferenceCount();
    for (int i = 0; i < preferenceCount; i++) {
      final Preference preference = getPreference(i);
      final String curKey = preference.getKey();
      if (curKey != null && curKey.equals(key.toString())) return preference;
      if (preference instanceof PreferenceGroup) {
        final Preference returnedPreference = ((PreferenceGroup) preference).findPreference(key);
        if (returnedPreference != null) return returnedPreference;
      }
    }
    return null;
  }
  protected boolean isOnSameScreenAsChildren() { return true; }
  @Override protected void onAttachedToHierarchy(PreferenceManager preferenceManager) {
    super.onAttachedToHierarchy(preferenceManager);
    for (Preference p : new ArrayList<>(mPreferenceList)) p.onAttachedToHierarchy(preferenceManager);
  }
  @Override protected void onAttachedToActivity() {
    super.onAttachedToActivity();
    mAttachedToActivity = true;
    for (Preference p : new ArrayList<>(mPreferenceList)) p.onAttachedToActivity();
  }
  @Override protected void onPrepareForRemoval() { super.onPrepareForRemoval(); mAttachedToActivity = false; }
  @Override public void notifyDependencyChange(boolean disableDependents) {
    super.notifyDependencyChange(disableDependents);
    for (Preference p : new ArrayList<>(mPreferenceList)) p.onParentChanged(this, disableDependents);
  }
  void sortPreferences() { synchronized (this) { Collections.sort(mPreferenceList); } }
}

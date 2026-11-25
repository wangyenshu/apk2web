package android.preference;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/** The flattened preferences of a screen (categories and their children; nested screens as single rows). */
class PreferenceGroupAdapter extends BaseAdapter implements Preference.OnPreferenceChangeInternalListener {
  private final PreferenceGroup mPreferenceGroup;
  private List<Preference> mPreferenceList = new ArrayList<>();
  private final ArrayList<Class<?>> mLayouts = new ArrayList<>();
  PreferenceGroupAdapter(PreferenceGroup preferenceGroup) {
    mPreferenceGroup = preferenceGroup;
    mPreferenceGroup.setOnPreferenceChangeInternalListener(this);
    syncMyPreferences();
  }
  private void syncMyPreferences() {
    List<Preference> newPreferenceList = new ArrayList<>(mPreferenceList.size());
    flattenPreferenceGroup(newPreferenceList, mPreferenceGroup);
    mPreferenceList = newPreferenceList;
    notifyDataSetChanged();
  }
  private void flattenPreferenceGroup(List<Preference> preferences, PreferenceGroup group) {
    group.sortPreferences();
    final int groupSize = group.getPreferenceCount();
    for (int i = 0; i < groupSize; i++) {
      final Preference preference = group.getPreference(i);
      preferences.add(preference);
      if (!mLayouts.contains(preference.getClass())) mLayouts.add(preference.getClass());
      if (preference instanceof PreferenceGroup) {
        final PreferenceGroup preferenceAsGroup = (PreferenceGroup) preference;
        if (preferenceAsGroup.isOnSameScreenAsChildren()) flattenPreferenceGroup(preferences, preferenceAsGroup);
      }
      preference.setOnPreferenceChangeInternalListener(this);
    }
  }
  public int getCount() { return mPreferenceList.size(); }
  public Preference getItem(int position) { return position < 0 || position >= getCount() ? null : mPreferenceList.get(position); }
  public long getItemId(int position) { return position < 0 || position >= getCount() ? Long.MIN_VALUE : getItem(position).getId(); }
  public View getView(int position, View convertView, ViewGroup parent) {
    final Preference preference = getItem(position);
    if (convertView != null && convertView.getTag() != preference.getClass()) convertView = null;
    View v = preference.getView(convertView, parent);
    v.setTag(preference.getClass());
    return v;
  }
  @Override public boolean isEnabled(int position) { return position >= 0 && position < getCount() && getItem(position).isSelectable(); }
  @Override public boolean areAllItemsEnabled() { return false; }
  public void onPreferenceChange(Preference preference) { notifyDataSetChanged(); }
  public void onPreferenceHierarchyChange(Preference preference) { syncMyPreferences(); }
  @Override public boolean hasStableIds() { return true; }
  @Override public int getItemViewType(int position) { return Math.max(0, mLayouts.indexOf(getItem(position).getClass())) % getViewTypeCount(); }
  @Override public int getViewTypeCount() { return 32; }
}

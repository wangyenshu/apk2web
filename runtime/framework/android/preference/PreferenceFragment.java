package android.preference;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public abstract class PreferenceFragment extends Fragment implements PreferenceManager.OnPreferenceTreeClickListener {
  public interface OnPreferenceStartFragmentCallback { boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref); }
  private PreferenceManager mPreferenceManager;
  private ListView mList;
  private boolean mHavePrefs, mInitDone;
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPreferenceManager = new PreferenceManager(getActivity(), 100);
    mPreferenceManager.setOnPreferenceTreeClickListener(this);
  }
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ListView l = new ListView(getActivity());
    l.setId(android.R.id.list);
    return l;
  }
  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (mHavePrefs) bindPreferences();
    mInitDone = true;
  }
  @Override public void onDestroyView() { mList = null; super.onDestroyView(); }
  @Override public void onDestroy() { super.onDestroy(); mPreferenceManager.dispatchActivityDestroy(); }
  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) { super.onActivityResult(requestCode, resultCode, data); mPreferenceManager.dispatchActivityResult(requestCode, resultCode, data); }
  public PreferenceManager getPreferenceManager() { return mPreferenceManager; }
  public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
    if (mPreferenceManager.setPreferences(preferenceScreen) && preferenceScreen != null) {
      mHavePrefs = true;
      if (mInitDone) bindPreferences();
    }
  }
  public PreferenceScreen getPreferenceScreen() { return mPreferenceManager.getPreferenceScreen(); }
  public void addPreferencesFromIntent(Intent intent) { setPreferenceScreen(mPreferenceManager.inflateFromIntent(intent, getPreferenceScreen())); }
  public void addPreferencesFromResource(int preferencesResId) { setPreferenceScreen(mPreferenceManager.inflateFromResource(getActivity(), preferencesResId, getPreferenceScreen())); }
  public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    if (preference.getFragment() != null && getActivity() instanceof OnPreferenceStartFragmentCallback)
      return ((OnPreferenceStartFragmentCallback) getActivity()).onPreferenceStartFragment(this, preference);
    return false;
  }
  public Preference findPreference(CharSequence key) { return mPreferenceManager == null ? null : mPreferenceManager.findPreference(key); }
  private void bindPreferences() {
    final PreferenceScreen preferenceScreen = getPreferenceScreen();
    if (preferenceScreen != null) preferenceScreen.bind(getListView());
  }
  public final ListView getListView() {
    if (mList == null) {
      View root = getView();
      View rawListView = root == null ? null : root.findViewById(android.R.id.list);
      if (!(rawListView instanceof ListView)) throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
      mList = (ListView) rawListView;
    }
    return mList;
  }
  public boolean hasListView() { return getView() != null && getView().findViewById(android.R.id.list) instanceof ListView; }
}

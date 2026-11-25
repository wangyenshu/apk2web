package android.preference;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import a2w.XmlNode;
import java.util.ArrayList;
import java.util.List;

/** Default shared preferences, preference XML inflation, and the hierarchy's shared state. */
public class PreferenceManager {
  public static final String KEY_HAS_SET_DEFAULT_VALUES = "_has_set_default_values";
  public static final String METADATA_KEY_PREFERENCES = "android.preference";
  public interface OnPreferenceTreeClickListener { boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference); }
  public interface OnActivityResultListener { boolean onActivityResult(int requestCode, int resultCode, Intent data); }
  public interface OnActivityStopListener { void onActivityStop(); }
  public interface OnActivityDestroyListener { void onActivityDestroy(); }

  private final Context ctx;
  private Activity mActivity;
  private long mNextId = 0;
  private String name;
  private int mode;
  private SharedPreferences.Editor mEditor;
  private boolean mNoCommit;
  private PreferenceScreen mPreferenceScreen;
  private OnPreferenceTreeClickListener mOnPreferenceTreeClickListener;
  private final List<OnActivityDestroyListener> mActivityDestroyListeners = new ArrayList<>();
  private final List<OnActivityResultListener> mActivityResultListeners = new ArrayList<>();
  private final List<DialogInterface> mPreferencesScreens = new ArrayList<>();

  PreferenceManager(Context c) { ctx = c; name = getDefaultSharedPreferencesName(c); }
  PreferenceManager(Activity activity, int firstRequestCode) { this(activity); mActivity = activity; }
  public static String getDefaultSharedPreferencesName(Context c) { return c.getPackageName() + "_preferences"; }
  public static SharedPreferences getDefaultSharedPreferences(Context c) {
    return c.getSharedPreferences(getDefaultSharedPreferencesName(c), Context.MODE_PRIVATE);
  }
  public static void setDefaultValues(Context c, int resId, boolean readAgain) {
    setDefaultValues(c, getDefaultSharedPreferencesName(c), Context.MODE_PRIVATE, resId, readAgain);
  }
  public static void setDefaultValues(Context c, String name, int mode, int resId, boolean readAgain) {
    SharedPreferences done = c.getSharedPreferences(KEY_HAS_SET_DEFAULT_VALUES, Context.MODE_PRIVATE);
    if (!readAgain && done.getBoolean(KEY_HAS_SET_DEFAULT_VALUES, false)) return;
    // inflating the hierarchy sets each preference's default where nothing is stored, as on Android
    PreferenceManager pm = new PreferenceManager(c);
    pm.setSharedPreferencesName(name);
    pm.setSharedPreferencesMode(mode);
    try { pm.inflateFromResource(c, resId, null); }
    catch (RuntimeException e) { android.util.Log.w("PreferenceManager", "setDefaultValues: " + e); }
    done.edit().putBoolean(KEY_HAS_SET_DEFAULT_VALUES, true).apply();
  }

  // ---------------------------------------------------------------- inflation
  /** web: the hierarchy in a preferences XML resource */
  public PreferenceScreen inflateFromResource(Context context, int resId, PreferenceScreen rootPreferences) {
    setNoCommit(true);
    XmlNode root = XmlNode.of(a2w.Res.xml(resId));
    if (root == null) throw new android.content.res.Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(resId));
    Preference p = a2w.PrefInflater.inflate(context, root, rootPreferences);
    PreferenceScreen screen = p instanceof PreferenceScreen ? (PreferenceScreen) p : rootPreferences;
    if (screen == null) { screen = createPreferenceScreen(context); if (p != null) screen.addPreference(p); }
    screen.onAttachedToHierarchy(this);
    setNoCommit(false);
    return screen;
  }
  PreferenceScreen inflateFromIntent(Intent queryIntent, PreferenceScreen rootPreferences) {
    return rootPreferences != null ? rootPreferences : createPreferenceScreen(ctx);
  }
  public PreferenceScreen createPreferenceScreen(Context context) {
    final PreferenceScreen preferenceScreen = new PreferenceScreen(context, null);
    preferenceScreen.onAttachedToHierarchy(this);
    return preferenceScreen;
  }
  long getNextId() { synchronized (this) { return mNextId++; } }

  // ---------------------------------------------------------------- storage
  public SharedPreferences getSharedPreferences() { return ctx.getSharedPreferences(name, mode); }
  public String getSharedPreferencesName() { return name; }
  public void setSharedPreferencesName(String n) { name = n; mEditor = null; }
  public int getSharedPreferencesMode() { return mode; }
  public void setSharedPreferencesMode(int m) { mode = m; mEditor = null; }
  public void setStorageDefault() {}
  public void setStorageDeviceProtected() {}
  public boolean isStorageDefault() { return true; }
  public boolean isStorageDeviceProtected() { return false; }
  public PreferenceDataStore getPreferenceDataStore() { return null; }
  public void setPreferenceDataStore(PreferenceDataStore dataStore) {}
  SharedPreferences.Editor getEditor() {
    if (mNoCommit) { if (mEditor == null) mEditor = getSharedPreferences().edit(); return mEditor; }
    return getSharedPreferences().edit();
  }
  boolean shouldCommit() { return !mNoCommit; }
  private void setNoCommit(boolean noCommit) {
    if (!noCommit && mEditor != null) mEditor.apply();
    mNoCommit = noCommit;
  }

  // ---------------------------------------------------------------- hierarchy
  PreferenceScreen getPreferenceScreen() { return mPreferenceScreen; }
  boolean setPreferences(PreferenceScreen preferenceScreen) {
    if (preferenceScreen != mPreferenceScreen) { mPreferenceScreen = preferenceScreen; return true; }
    return false;
  }
  public Preference findPreference(CharSequence key) { return mPreferenceScreen == null ? null : mPreferenceScreen.findPreference(key); }
  Activity getActivity() { return mActivity; }
  Context getContext() { return ctx; }
  void registerOnActivityResultListener(OnActivityResultListener l) { synchronized (this) { if (!mActivityResultListeners.contains(l)) mActivityResultListeners.add(l); } }
  void unregisterOnActivityResultListener(OnActivityResultListener l) { synchronized (this) { mActivityResultListeners.remove(l); } }
  void dispatchActivityResult(int requestCode, int resultCode, Intent data) {
    for (OnActivityResultListener l : new ArrayList<>(mActivityResultListeners)) if (l.onActivityResult(requestCode, resultCode, data)) break;
  }
  void registerOnActivityDestroyListener(OnActivityDestroyListener l) { synchronized (this) { if (!mActivityDestroyListeners.contains(l)) mActivityDestroyListeners.add(l); } }
  void unregisterOnActivityDestroyListener(OnActivityDestroyListener l) { synchronized (this) { mActivityDestroyListeners.remove(l); } }
  void dispatchActivityDestroy() {
    for (OnActivityDestroyListener l : new ArrayList<>(mActivityDestroyListeners)) l.onActivityDestroy();
    for (DialogInterface d : new ArrayList<>(mPreferencesScreens)) d.dismiss();
    mPreferencesScreens.clear();
  }
  void addPreferencesScreen(DialogInterface screen) { synchronized (this) { mPreferencesScreens.add(screen); } }
  void removePreferencesScreen(DialogInterface screen) { synchronized (this) { mPreferencesScreens.remove(screen); } }
  void setOnPreferenceTreeClickListener(OnPreferenceTreeClickListener l) { mOnPreferenceTreeClickListener = l; }
  OnPreferenceTreeClickListener getOnPreferenceTreeClickListener() { return mOnPreferenceTreeClickListener; }
}

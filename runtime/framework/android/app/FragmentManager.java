package android.app;

import android.os.Bundle;
import java.util.List;

public abstract class FragmentManager {
  public interface BackStackEntry {
    int getId();
    String getName();
    int getBreadCrumbTitleRes();
    int getBreadCrumbShortTitleRes();
    CharSequence getBreadCrumbTitle();
    CharSequence getBreadCrumbShortTitle();
  }
  public interface OnBackStackChangedListener { void onBackStackChanged(); }
  public abstract static class FragmentLifecycleCallbacks {
    public void onFragmentPreAttached(FragmentManager fm, Fragment f, android.content.Context context) {}
    public void onFragmentAttached(FragmentManager fm, Fragment f, android.content.Context context) {}
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {}
    public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {}
    public void onFragmentViewCreated(FragmentManager fm, Fragment f, android.view.View v, Bundle savedInstanceState) {}
    public void onFragmentStarted(FragmentManager fm, Fragment f) {}
    public void onFragmentResumed(FragmentManager fm, Fragment f) {}
    public void onFragmentPaused(FragmentManager fm, Fragment f) {}
    public void onFragmentStopped(FragmentManager fm, Fragment f) {}
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {}
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {}
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {}
    public void onFragmentDetached(FragmentManager fm, Fragment f) {}
  }
  public static final int POP_BACK_STACK_INCLUSIVE = 1;
  public abstract FragmentTransaction beginTransaction();
  @Deprecated public FragmentTransaction openTransaction() { return beginTransaction(); }
  public abstract boolean executePendingTransactions();
  public abstract Fragment findFragmentById(int id);
  public abstract Fragment findFragmentByTag(String tag);
  public abstract void popBackStack();
  public abstract boolean popBackStackImmediate();
  public abstract void popBackStack(String name, int flags);
  public abstract boolean popBackStackImmediate(String name, int flags);
  public abstract void popBackStack(int id, int flags);
  public abstract boolean popBackStackImmediate(int id, int flags);
  public abstract int getBackStackEntryCount();
  public abstract BackStackEntry getBackStackEntryAt(int index);
  public abstract void addOnBackStackChangedListener(OnBackStackChangedListener listener);
  public abstract void removeOnBackStackChangedListener(OnBackStackChangedListener listener);
  public abstract void putFragment(Bundle bundle, String key, Fragment fragment);
  public abstract Fragment getFragment(Bundle bundle, String key);
  public abstract List<Fragment> getFragments();
  public abstract Fragment.SavedState saveFragmentInstanceState(Fragment f);
  public abstract boolean isDestroyed();
  public abstract void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks cb, boolean recursive);
  public abstract void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks cb);
  public abstract Fragment getPrimaryNavigationFragment();
  public abstract void dump(String prefix, java.io.FileDescriptor fd, java.io.PrintWriter writer, String[] args);
  public static void enableDebugLogging(boolean enabled) {}
  public void invalidateOptionsMenu() {}
  public boolean isStateSaved() { return false; }
}

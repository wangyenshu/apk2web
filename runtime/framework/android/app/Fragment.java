package android.app;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/** android.app.Fragment: a piece of an activity's UI with its own lifecycle, run by the activity's FragmentManager. */
public class Fragment implements ComponentCallbacks2, View.OnCreateContextMenuListener {
  static final int INITIALIZING = 0, CREATED = 1, ACTIVITY_CREATED = 2, STOPPED = 3, STARTED = 4, RESUMED = 5;
  public static class SavedState implements Parcelable {
    final Bundle mState;
    SavedState(Bundle state) { mState = state; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
  }
  public static class InstantiationException extends android.util.AndroidRuntimeException {
    public InstantiationException(String msg, Exception cause) { super(msg, cause); }
  }
  int mState = INITIALIZING;
  Bundle mSavedFragmentState;
  int mIndex = -1;
  String mWho;
  Bundle mArguments;
  Fragment mTarget;
  int mTargetRequestCode;
  boolean mAdded, mRemoving, mFromLayout, mInLayout, mRestored, mHidden, mDetached, mRetainInstance, mHasMenu = false, mMenuVisible = true;
  boolean mCalled, mUserVisibleHint = true;
  int mBackStackNesting;
  FragmentManagerImpl mFragmentManager;
  Activity mActivity;
  FragmentManagerImpl mChildFragmentManager;
  Fragment mParentFragment;
  int mFragmentId, mContainerId;
  String mTag;
  ViewGroup mContainer;
  View mView;
  LayoutInflater mLayoutInflater;

  public Fragment() {}
  public static Fragment instantiate(Context context, String fname) { return instantiate(context, fname, null); }
  public static Fragment instantiate(Context context, String fname, Bundle args) {
    Object o = a2w.Reflection.newInstance(fname, new Class<?>[0]);
    if (!(o instanceof Fragment)) throw new InstantiationException("Unable to instantiate fragment " + fname + ": make sure class name exists, is public, and has an empty constructor that is public", null);
    Fragment f = (Fragment) o;
    if (args != null) f.setArguments(args);
    return f;
  }
  @Override public final boolean equals(Object o) { return super.equals(o); }
  @Override public final int hashCode() { return super.hashCode(); }
  @Override public String toString() {
    StringBuilder sb = new StringBuilder(128);
    sb.append(getClass().getSimpleName()).append("{").append(Integer.toHexString(System.identityHashCode(this)));
    if (mIndex >= 0) sb.append(" #").append(mIndex);
    if (mFragmentId != 0) sb.append(" id=0x").append(Integer.toHexString(mFragmentId));
    if (mTag != null) sb.append(" ").append(mTag);
    return sb.append('}').toString();
  }
  public final int getId() { return mFragmentId; }
  public final String getTag() { return mTag; }
  public void setArguments(Bundle args) {
    if (mIndex >= 0 && isStateSaved()) throw new IllegalStateException("Fragment already active");
    mArguments = args;
  }
  public final Bundle getArguments() { return mArguments; }
  public final boolean isStateSaved() { return false; }
  public void setInitialSavedState(SavedState state) { mSavedFragmentState = state != null ? state.mState : null; }
  public void setTargetFragment(Fragment fragment, int requestCode) { mTarget = fragment; mTargetRequestCode = requestCode; }
  public final Fragment getTargetFragment() { return mTarget; }
  public final int getTargetRequestCode() { return mTargetRequestCode; }
  public Context getContext() { return mActivity; }
  public final Activity getActivity() { return mActivity; }
  public final Object getHost() { return mActivity; }
  public final Resources getResources() {
    if (mActivity == null) throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    return mActivity.getResources();
  }
  public final CharSequence getText(int resId) { return getResources().getText(resId); }
  public final String getString(int resId) { return getResources().getString(resId); }
  public final String getString(int resId, Object... formatArgs) { return getResources().getString(resId, formatArgs); }
  public final FragmentManager getFragmentManager() { return mFragmentManager; }
  public final FragmentManager getChildFragmentManager() {
    if (mChildFragmentManager == null) {
      mChildFragmentManager = new FragmentManagerImpl(mActivity, this);
      mChildFragmentManager.moveToState(mState >= RESUMED ? RESUMED : mState);
    }
    return mChildFragmentManager;
  }
  public final Fragment getParentFragment() { return mParentFragment; }
  public final boolean isAdded() { return mActivity != null && mAdded; }
  public final boolean isDetached() { return mDetached; }
  public final boolean isRemoving() { return mRemoving; }
  public final boolean isInLayout() { return mInLayout; }
  public final boolean isResumed() { return mState >= RESUMED; }
  public final boolean isVisible() { return isAdded() && !isHidden() && mView != null && mView.getWindowToken() != null && mView.getVisibility() == View.VISIBLE; }
  public final boolean isHidden() { return mHidden; }
  public void onHiddenChanged(boolean hidden) {}
  public void setRetainInstance(boolean retain) { mRetainInstance = retain; }
  public final boolean getRetainInstance() { return mRetainInstance; }
  public void setHasOptionsMenu(boolean hasMenu) {
    if (mHasMenu != hasMenu) { mHasMenu = hasMenu; if (isAdded() && !isHidden()) mActivity.invalidateOptionsMenu(); }
  }
  public void setMenuVisibility(boolean menuVisible) {
    if (mMenuVisible != menuVisible) { mMenuVisible = menuVisible; if (mHasMenu && isAdded() && !isHidden()) mActivity.invalidateOptionsMenu(); }
  }
  public void setUserVisibleHint(boolean isVisibleToUser) { mUserVisibleHint = isVisibleToUser; }
  public boolean getUserVisibleHint() { return mUserVisibleHint; }
  public LoaderManager getLoaderManager() { return mActivity.getLoaderManager(); }
  public void startActivity(Intent intent) { startActivity(intent, null); }
  public void startActivity(Intent intent, Bundle options) {
    if (mActivity == null) throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    mActivity.startActivity(intent);
  }
  public void startActivityForResult(Intent intent, int requestCode) { startActivityForResult(intent, requestCode, null); }
  public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
    if (mActivity == null) throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    mActivity.startActivityFromFragment(this, intent, requestCode);
  }
  public void startIntentSenderForResult(android.content.IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) {}
  public void onActivityResult(int requestCode, int resultCode, Intent data) {}
  public final void requestPermissions(String[] permissions, int requestCode) {
    int[] r = new int[permissions.length];
    onRequestPermissionsResult(requestCode, permissions, r);
  }
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {}
  public boolean shouldShowRequestPermissionRationale(String permission) { return false; }
  public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) { return mActivity.getLayoutInflater().cloneInContext(mActivity); }
  @Deprecated public LayoutInflater getLayoutInflater(Bundle savedInstanceState) { return onGetLayoutInflater(savedInstanceState); }
  public final LayoutInflater getLayoutInflater() { if (mLayoutInflater == null) mLayoutInflater = onGetLayoutInflater(null); return mLayoutInflater; }
  public void onInflate(AttributeSet attrs, Bundle savedInstanceState) { mCalled = true; }
  public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) { onInflate(attrs, savedInstanceState); mCalled = true; if (context instanceof Activity) onInflate((Activity) context, attrs, savedInstanceState); }
  @Deprecated public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) { mCalled = true; }
  public void onAttachFragment(Fragment childFragment) {}
  public void onAttach(Context context) { mCalled = true; if (context instanceof Activity) onAttach((Activity) context); }
  @Deprecated public void onAttach(Activity activity) { mCalled = true; }
  public android.animation.Animator onCreateAnimator(int transit, boolean enter, int nextAnim) { return null; }
  public void onCreate(Bundle savedInstanceState) { mCalled = true; }
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { return null; }
  public void onViewCreated(View view, Bundle savedInstanceState) {}
  public View getView() { return mView; }
  public void onActivityCreated(Bundle savedInstanceState) { mCalled = true; }
  public void onViewStateRestored(Bundle savedInstanceState) { mCalled = true; }
  public void onStart() { mCalled = true; }
  public void onResume() { mCalled = true; }
  public void onSaveInstanceState(Bundle outState) {}
  public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {}
  public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {}
  public void onConfigurationChanged(Configuration newConfig) { mCalled = true; }
  public void onPause() { mCalled = true; }
  public void onStop() { mCalled = true; }
  public void onLowMemory() { mCalled = true; }
  public void onTrimMemory(int level) { mCalled = true; }
  public void onDestroyView() { mCalled = true; }
  public void onDestroy() { mCalled = true; }
  public void onDetach() { mCalled = true; }
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {}
  public void onPrepareOptionsMenu(Menu menu) {}
  public void onDestroyOptionsMenu() {}
  public boolean onOptionsItemSelected(MenuItem item) { return false; }
  public void onOptionsMenuClosed(Menu menu) {}
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { getActivity().onCreateContextMenu(menu, v, menuInfo); }
  public void registerForContextMenu(View view) { view.setOnCreateContextMenuListener(this); }
  public void unregisterForContextMenu(View view) { view.setOnCreateContextMenuListener(null); }
  public boolean onContextItemSelected(MenuItem item) { return false; }
  public void setEnterSharedElementCallback(SharedElementCallback callback) {}
  public void setExitSharedElementCallback(SharedElementCallback callback) {}
  public void setEnterTransition(android.transition.Transition transition) {}
  public void setExitTransition(android.transition.Transition transition) {}
  public void setReenterTransition(android.transition.Transition transition) {}
  public void setReturnTransition(android.transition.Transition transition) {}
  public void setSharedElementEnterTransition(android.transition.Transition transition) {}
  public void setSharedElementReturnTransition(android.transition.Transition transition) {}
  public void setAllowEnterTransitionOverlap(boolean allow) {}
  public void setAllowReturnTransitionOverlap(boolean allow) {}
  public void postponeEnterTransition() {}
  public void startPostponedEnterTransition() {}
  public void dump(String prefix, java.io.FileDescriptor fd, java.io.PrintWriter writer, String[] args) {}
}

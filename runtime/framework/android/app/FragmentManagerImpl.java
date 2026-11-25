package android.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/** The fragments of one activity (or of a parent fragment): their states follow the host's, transactions run in order. */
final class FragmentManagerImpl extends FragmentManager {
  static final String TAG = "FragmentManager";
  final Activity mHost;
  final Fragment mParent;
  final ArrayList<Fragment> mActive = new ArrayList<>(), mAdded = new ArrayList<>();
  final ArrayList<BackStackRecord> mBackStack = new ArrayList<>();
  final ArrayList<BackStackRecord> mPending = new ArrayList<>();
  final ArrayList<OnBackStackChangedListener> mBackStackChangeListeners = new ArrayList<>();
  final ArrayList<FragmentLifecycleCallbacks> mCallbacks = new ArrayList<>();
  int mCurState = Fragment.INITIALIZING;
  boolean mDestroyed, mExecuting, mPosted;
  int mNextIndex;
  private final Handler mHandler = new Handler(Looper.getMainLooper());

  FragmentManagerImpl(Activity host, Fragment parent) { mHost = host; mParent = parent; }

  // ---------------------------------------------------------------- transactions
  @Override public FragmentTransaction beginTransaction() { return new BackStackRecord(this); }
  void enqueue(BackStackRecord r) {
    mPending.add(r);
    if (!mPosted) { mPosted = true; mHandler.post(this::executePendingTransactions); }
  }
  @Override public boolean executePendingTransactions() {
    mPosted = false;
    if (mExecuting) return false;
    boolean did = false;
    mExecuting = true;
    try {
      while (!mPending.isEmpty()) { BackStackRecord r = mPending.remove(0); r.run(); did = true; }
    } finally { mExecuting = false; }
    return did;
  }
  void addBackStackState(BackStackRecord r) { mBackStack.add(r); reportBackStackChanged(); }
  void reportBackStackChanged() { for (OnBackStackChangedListener l : new ArrayList<>(mBackStackChangeListeners)) l.onBackStackChanged(); }

  void makeActive(Fragment f) {
    if (f.mIndex >= 0) return;
    f.mIndex = mNextIndex++;
    f.mWho = (mParent != null ? mParent.mWho + ":" : "android:fragment:") + f.mIndex;
    mActive.add(f);
  }
  void makeInactive(Fragment f) { mActive.remove(f); f.mIndex = -1; f.mWho = null; f.mAdded = false; f.mRemoving = false; f.mFragmentManager = null; f.mActivity = null; f.mParentFragment = null; }
  void addFragment(Fragment f, boolean moveToStateNow) {
    makeActive(f);
    if (!f.mDetached) {
      if (mAdded.contains(f)) throw new IllegalStateException("Fragment already added: " + f);
      mAdded.add(f);
      f.mAdded = true;
      f.mRemoving = false;
      if (f.mHasMenu && f.mMenuVisible) mHost.invalidateOptionsMenu();
      if (moveToStateNow) moveToState(f, mCurState);
    }
  }
  void removeFragment(Fragment f) {
    final boolean inactive = f.mBackStackNesting <= 0;
    if (!f.mDetached || inactive) {
      mAdded.remove(f);
      if (f.mHasMenu && f.mMenuVisible) mHost.invalidateOptionsMenu();
      f.mAdded = false;
      f.mRemoving = true;
      moveToState(f, inactive ? Fragment.INITIALIZING : Fragment.CREATED);
    }
  }
  void hideFragment(Fragment f) {
    if (!f.mHidden) {
      f.mHidden = true;
      if (f.mView != null) f.mView.setVisibility(View.GONE);
      if (f.mAdded && f.mHasMenu && f.mMenuVisible) mHost.invalidateOptionsMenu();
      f.onHiddenChanged(true);
    }
  }
  void showFragment(Fragment f) {
    if (f.mHidden) {
      f.mHidden = false;
      if (f.mView != null) f.mView.setVisibility(View.VISIBLE);
      if (f.mAdded && f.mHasMenu && f.mMenuVisible) mHost.invalidateOptionsMenu();
      f.onHiddenChanged(false);
    }
  }
  void detachFragment(Fragment f) {
    if (!f.mDetached) {
      f.mDetached = true;
      if (f.mAdded) {
        mAdded.remove(f);
        if (f.mHasMenu && f.mMenuVisible) mHost.invalidateOptionsMenu();
        f.mAdded = false;
        moveToState(f, Fragment.CREATED);
      }
    }
  }
  void attachFragment(Fragment f) {
    if (f.mDetached) {
      f.mDetached = false;
      if (!f.mAdded) {
        if (mAdded.contains(f)) throw new IllegalStateException("Fragment already added: " + f);
        mAdded.add(f);
        f.mAdded = true;
        if (f.mHasMenu && f.mMenuVisible) mHost.invalidateOptionsMenu();
        moveToState(f, mCurState);
      }
    }
  }

  // ---------------------------------------------------------------- states
  void moveToState(int newState) {
    if (newState == mCurState) return;
    mCurState = newState;
    for (Fragment f : new ArrayList<>(mActive)) moveToState(f, newState);
  }
  ViewGroup container(int id) {
    if (id == 0) return null;
    View v = mParent != null ? (mParent.mView == null ? null : mParent.mView.findViewById(id)) : mHost.findViewById(id);
    return v instanceof ViewGroup ? (ViewGroup) v : null;
  }
  void moveToState(Fragment f, int newState) {
    if ((!f.mAdded || f.mDetached) && newState > Fragment.CREATED) newState = Fragment.CREATED;
    if (f.mRemoving && newState > f.mState) newState = f.mState;
    try {
      while (f.mState < newState) {
        switch (f.mState) {
          case Fragment.INITIALIZING:
            f.mActivity = mHost;
            f.mParentFragment = mParent;
            f.mFragmentManager = this;
            f.mCalled = false;
            for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentPreAttached(this, f, mHost);
            f.onAttach((Context) mHost);
            if (mParent == null) mHost.onAttachFragment(f); else mParent.onAttachFragment(f);
            for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentAttached(this, f, mHost);
            f.mCalled = false;
            f.onCreate(f.mSavedFragmentState);
            for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentCreated(this, f, f.mSavedFragmentState);
            f.mState = Fragment.CREATED;
            if (f.mFromLayout) {
              f.mView = f.onCreateView(f.getLayoutInflater(), null, f.mSavedFragmentState);
              if (f.mView != null) { if (f.mHidden) f.mView.setVisibility(View.GONE); f.onViewCreated(f.mView, f.mSavedFragmentState); }
            }
            break;
          case Fragment.CREATED:
            if (!f.mFromLayout) {
              ViewGroup c = container(f.mContainerId);
              if (f.mContainerId != 0 && c == null) Log.w(TAG, "No view found for id 0x" + Integer.toHexString(f.mContainerId) + " for fragment " + f);
              f.mContainer = c;
              f.mView = f.onCreateView(f.getLayoutInflater(), c, f.mSavedFragmentState);
              if (f.mView != null) {
                if (c != null) { if (f.mView.getParent() instanceof ViewGroup) ((ViewGroup) f.mView.getParent()).removeView(f.mView); c.addView(f.mView); }
                if (f.mHidden) f.mView.setVisibility(View.GONE);
                f.onViewCreated(f.mView, f.mSavedFragmentState);
                for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentViewCreated(this, f, f.mView, f.mSavedFragmentState);
              }
            }
            f.mCalled = false;
            f.onActivityCreated(f.mSavedFragmentState);
            for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentActivityCreated(this, f, f.mSavedFragmentState);
            if (f.mView != null) f.onViewStateRestored(f.mSavedFragmentState);
            if (f.mChildFragmentManager != null) f.mChildFragmentManager.moveToState(Fragment.ACTIVITY_CREATED);
            f.mSavedFragmentState = null;
            f.mState = Fragment.ACTIVITY_CREATED;
            break;
          case Fragment.ACTIVITY_CREATED:
            f.mState = Fragment.STOPPED;
            break;
          case Fragment.STOPPED:
            f.mCalled = false;
            f.onStart();
            if (f.mChildFragmentManager != null) f.mChildFragmentManager.moveToState(Fragment.STARTED);
            for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentStarted(this, f);
            f.mState = Fragment.STARTED;
            break;
          case Fragment.STARTED:
            f.mCalled = false;
            f.onResume();
            if (f.mChildFragmentManager != null) f.mChildFragmentManager.moveToState(Fragment.RESUMED);
            for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentResumed(this, f);
            f.mState = Fragment.RESUMED;
            break;
          default: f.mState = newState;
        }
      }
      while (f.mState > newState) {
        switch (f.mState) {
          case Fragment.RESUMED:
            if (f.mChildFragmentManager != null) f.mChildFragmentManager.moveToState(Fragment.STARTED);
            f.onPause();
            for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentPaused(this, f);
            f.mState = Fragment.STARTED;
            break;
          case Fragment.STARTED:
            if (f.mChildFragmentManager != null) f.mChildFragmentManager.moveToState(Fragment.STOPPED);
            f.onStop();
            for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentStopped(this, f);
            f.mState = Fragment.STOPPED;
            break;
          case Fragment.STOPPED:
          case Fragment.ACTIVITY_CREATED:
            if (f.mChildFragmentManager != null) f.mChildFragmentManager.moveToState(Fragment.CREATED);
            if (f.mView != null) {
              f.onDestroyView();
              for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentViewDestroyed(this, f);
              if (f.mContainer != null && f.mView.getParent() == f.mContainer) f.mContainer.removeView(f.mView);
              else if (f.mView.getParent() instanceof ViewGroup && !f.mFromLayout) ((ViewGroup) f.mView.getParent()).removeView(f.mView);
            }
            f.mContainer = null;
            f.mView = null;
            f.mInLayout = false;
            f.mState = Fragment.CREATED;
            break;
          case Fragment.CREATED:
            if (f.mChildFragmentManager != null) f.mChildFragmentManager.moveToState(Fragment.INITIALIZING);
            if (!f.mRetainInstance || mDestroyed) { f.onDestroy(); for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentDestroyed(this, f); }
            f.onDetach();
            for (FragmentLifecycleCallbacks cb : mCallbacks) cb.onFragmentDetached(this, f);
            f.mState = Fragment.INITIALIZING;
            if (!f.mRetainInstance || mDestroyed) makeInactive(f);
            break;
          default: f.mState = newState;
        }
      }
    } catch (RuntimeException e) { a2w.Host.uncaught(e); }
  }
  void dispatchCreate() { moveToState(Fragment.CREATED); }
  void dispatchActivityCreated() { moveToState(Fragment.ACTIVITY_CREATED); }
  void dispatchStart() { executePendingTransactions(); moveToState(Fragment.STARTED); }
  void dispatchResume() { executePendingTransactions(); moveToState(Fragment.RESUMED); }
  void dispatchPause() { moveToState(Fragment.STARTED); }
  void dispatchStop() { moveToState(Fragment.STOPPED); }
  void dispatchDestroy() { mDestroyed = true; executePendingTransactions(); moveToState(Fragment.INITIALIZING); }

  // ---------------------------------------------------------------- <fragment> in layouts
  View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
    String ns = "http://schemas.android.com/apk/res/android";
    String fname = attrs.getAttributeValue(null, "class");
    if (fname == null) fname = attrs.getAttributeValue(ns, "name");
    int id = attrs.getAttributeResourceValue(ns, "id", View.NO_ID);
    String tag = attrs.getAttributeValue(ns, "tag");
    int containerId = parent != null ? parent.getId() : 0;
    if (fname != null && fname.startsWith(".")) fname = context.getPackageName() + fname;
    Fragment fragment = id != View.NO_ID ? findFragmentById(id) : null;
    if (fragment == null && tag != null) fragment = findFragmentByTag(tag);
    if (fragment == null && containerId != View.NO_ID && containerId != 0) fragment = findFragmentById(containerId);
    if (fragment == null) {
      fragment = Fragment.instantiate(context, fname);
      fragment.mFromLayout = true;
      fragment.mFragmentId = id != View.NO_ID ? id : containerId;
      fragment.mContainerId = containerId;
      fragment.mTag = tag;
      fragment.mInLayout = true;
      fragment.mFragmentManager = this;
      fragment.mActivity = mHost;
      fragment.onInflate(mHost, attrs, fragment.mSavedFragmentState);
      addFragment(fragment, true);
    } else {
      fragment.mInLayout = true;
      fragment.mActivity = mHost;
      if (!fragment.mRetainInstance) fragment.onInflate(mHost, attrs, fragment.mSavedFragmentState);
    }
    if (mCurState < Fragment.CREATED && fragment.mFromLayout) moveToState(fragment, Fragment.CREATED);
    else moveToState(fragment, Math.max(mCurState, Fragment.CREATED));
    if (fragment.mView == null) throw new IllegalStateException("Fragment " + fname + " did not create a view.");
    if (id != View.NO_ID) fragment.mView.setId(id);
    if (fragment.mView.getTag() == null) fragment.mView.setTag(tag);
    return fragment.mView;
  }

  // ---------------------------------------------------------------- lookups, back stack
  @Override public Fragment findFragmentById(int id) {
    for (int i = mAdded.size() - 1; i >= 0; i--) { Fragment f = mAdded.get(i); if (f != null && f.mFragmentId == id) return f; }
    for (int i = mActive.size() - 1; i >= 0; i--) { Fragment f = mActive.get(i); if (f != null && f.mFragmentId == id) return f; }
    return null;
  }
  @Override public Fragment findFragmentByTag(String tag) {
    if (tag == null) return null;
    for (int i = mAdded.size() - 1; i >= 0; i--) { Fragment f = mAdded.get(i); if (f != null && tag.equals(f.mTag)) return f; }
    for (int i = mActive.size() - 1; i >= 0; i--) { Fragment f = mActive.get(i); if (f != null && tag.equals(f.mTag)) return f; }
    return null;
  }
  @Override public void popBackStack() { mHandler.post(this::popBackStackImmediate); }
  @Override public boolean popBackStackImmediate() { executePendingTransactions(); return popBackStackState(null, -1, 0); }
  @Override public void popBackStack(String name, int flags) { mHandler.post(() -> popBackStackImmediate(name, flags)); }
  @Override public boolean popBackStackImmediate(String name, int flags) { executePendingTransactions(); return popBackStackState(name, -1, flags); }
  @Override public void popBackStack(int id, int flags) { mHandler.post(() -> popBackStackImmediate(id, flags)); }
  @Override public boolean popBackStackImmediate(int id, int flags) { executePendingTransactions(); return popBackStackState(null, id, flags); }
  boolean popBackStackState(String name, int id, int flags) {
    if (mBackStack.isEmpty()) return false;
    int index = mBackStack.size() - 1;
    if (name != null || id >= 0) {
      while (index >= 0) {
        BackStackRecord bss = mBackStack.get(index);
        if (name != null && name.equals(bss.getName()) || id >= 0 && id == bss.mIndex) break;
        index--;
      }
      if (index < 0) return false;
      if ((flags & POP_BACK_STACK_INCLUSIVE) != 0) {
        index--;
        while (index >= 0) {
          BackStackRecord bss = mBackStack.get(index);
          if (name != null && name.equals(bss.getName()) || id >= 0 && id == bss.mIndex) index--; else break;
        }
      }
    } else index = mBackStack.size() - 2;
    if (index == mBackStack.size() - 1) return false;
    for (int i = mBackStack.size() - 1; i > index; i--) mBackStack.remove(i).popFromBackStack();
    reportBackStackChanged();
    return true;
  }
  @Override public int getBackStackEntryCount() { return mBackStack.size(); }
  @Override public BackStackEntry getBackStackEntryAt(int index) { return mBackStack.get(index); }
  @Override public void addOnBackStackChangedListener(OnBackStackChangedListener listener) { mBackStackChangeListeners.add(listener); }
  @Override public void removeOnBackStackChangedListener(OnBackStackChangedListener listener) { mBackStackChangeListeners.remove(listener); }
  @Override public void putFragment(Bundle bundle, String key, Fragment fragment) { bundle.putInt(key, fragment.mIndex); }
  @Override public Fragment getFragment(Bundle bundle, String key) {
    int index = bundle.getInt(key, -1);
    for (Fragment f : mActive) if (f.mIndex == index) return f;
    return null;
  }
  @Override public List<Fragment> getFragments() { return new ArrayList<>(mAdded); }
  @Override public Fragment.SavedState saveFragmentInstanceState(Fragment f) { return null; }
  @Override public boolean isDestroyed() { return mDestroyed; }
  @Override public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks cb, boolean recursive) { mCallbacks.add(cb); }
  @Override public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks cb) { mCallbacks.remove(cb); }
  @Override public Fragment getPrimaryNavigationFragment() { return null; }
  @Override public void dump(String prefix, java.io.FileDescriptor fd, java.io.PrintWriter writer, String[] args) {}
  @Override public void invalidateOptionsMenu() { mHost.invalidateOptionsMenu(); }

  // ---------------------------------------------------------------- menus, results
  boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    boolean show = false;
    for (Fragment f : new ArrayList<>(mAdded)) if (!f.mHidden && f.mHasMenu && f.mMenuVisible) { show = true; f.onCreateOptionsMenu(menu, inflater); }
    for (Fragment f : new ArrayList<>(mAdded)) if (f.mChildFragmentManager != null) show |= f.mChildFragmentManager.dispatchCreateOptionsMenu(menu, inflater);
    return show;
  }
  boolean dispatchPrepareOptionsMenu(Menu menu) {
    boolean show = false;
    for (Fragment f : new ArrayList<>(mAdded)) if (!f.mHidden && f.mHasMenu && f.mMenuVisible) { show = true; f.onPrepareOptionsMenu(menu); }
    return show;
  }
  boolean dispatchOptionsItemSelected(MenuItem item) {
    for (Fragment f : new ArrayList<>(mAdded)) {
      if (!f.mHidden && f.mHasMenu && f.mMenuVisible && f.onOptionsItemSelected(item)) return true;
      if (f.mChildFragmentManager != null && f.mChildFragmentManager.dispatchOptionsItemSelected(item)) return true;
    }
    return false;
  }
  boolean dispatchContextItemSelected(MenuItem item) {
    for (Fragment f : new ArrayList<>(mAdded)) if (!f.mHidden && f.onContextItemSelected(item)) return true;
    return false;
  }
  Fragment findFragmentByWho(String who) {
    for (Fragment f : mActive) {
      if (who.equals(f.mWho)) return f;
      if (f.mChildFragmentManager != null) { Fragment c = f.mChildFragmentManager.findFragmentByWho(who); if (c != null) return c; }
    }
    return null;
  }
  void dispatchConfigurationChanged(android.content.res.Configuration c) { for (Fragment f : new ArrayList<>(mAdded)) f.onConfigurationChanged(c); }
  void dispatchLowMemory() { for (Fragment f : new ArrayList<>(mAdded)) f.onLowMemory(); }
}

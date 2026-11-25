package android.app;

import java.util.ArrayList;

/** A fragment transaction: its operations run in order when executed and run backwards when popped. */
final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, Runnable {
  static final int OP_ADD = 1, OP_REPLACE = 2, OP_REMOVE = 3, OP_HIDE = 4, OP_SHOW = 5, OP_DETACH = 6, OP_ATTACH = 7;
  static final class Op { int cmd; Fragment fragment; ArrayList<Fragment> removed; }
  final FragmentManagerImpl mManager;
  final ArrayList<Op> mOps = new ArrayList<>();
  final ArrayList<Runnable> mCommitRunnables = new ArrayList<>();
  boolean mAddToBackStack, mAllowAddToBackStack = true, mCommitted;
  String mName;
  int mIndex = -1, mBreadCrumbTitleRes, mBreadCrumbShortTitleRes;
  CharSequence mBreadCrumbTitleText, mBreadCrumbShortTitleText;
  BackStackRecord(FragmentManagerImpl manager) { mManager = manager; }
  public int getId() { return mIndex; }
  public String getName() { return mName; }
  public int getBreadCrumbTitleRes() { return mBreadCrumbTitleRes; }
  public int getBreadCrumbShortTitleRes() { return mBreadCrumbShortTitleRes; }
  public CharSequence getBreadCrumbTitle() { return mBreadCrumbTitleRes != 0 ? mManager.mHost.getText(mBreadCrumbTitleRes) : mBreadCrumbTitleText; }
  public CharSequence getBreadCrumbShortTitle() { return mBreadCrumbShortTitleRes != 0 ? mManager.mHost.getText(mBreadCrumbShortTitleRes) : mBreadCrumbShortTitleText; }
  private void op(int cmd, Fragment f) { Op o = new Op(); o.cmd = cmd; o.fragment = f; mOps.add(o); }
  private void doAddOp(int containerViewId, Fragment fragment, String tag, int opcmd) {
    if (tag != null) {
      if (fragment.mTag != null && !tag.equals(fragment.mTag)) throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + tag);
      fragment.mTag = tag;
    }
    if (containerViewId != 0) {
      if (fragment.mFragmentId != 0 && fragment.mFragmentId != containerViewId) throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + containerViewId);
      fragment.mContainerId = fragment.mFragmentId = containerViewId;
    }
    op(opcmd, fragment);
  }
  public FragmentTransaction add(Fragment fragment, String tag) { doAddOp(0, fragment, tag, OP_ADD); return this; }
  public FragmentTransaction add(int containerViewId, Fragment fragment) { doAddOp(containerViewId, fragment, null, OP_ADD); return this; }
  public FragmentTransaction add(int containerViewId, Fragment fragment, String tag) { doAddOp(containerViewId, fragment, tag, OP_ADD); return this; }
  public FragmentTransaction replace(int containerViewId, Fragment fragment) { return replace(containerViewId, fragment, null); }
  public FragmentTransaction replace(int containerViewId, Fragment fragment, String tag) {
    if (containerViewId == 0) throw new IllegalArgumentException("Must use non-zero containerViewId");
    doAddOp(containerViewId, fragment, tag, OP_REPLACE);
    return this;
  }
  public FragmentTransaction remove(Fragment fragment) { op(OP_REMOVE, fragment); return this; }
  public FragmentTransaction hide(Fragment fragment) { op(OP_HIDE, fragment); return this; }
  public FragmentTransaction show(Fragment fragment) { op(OP_SHOW, fragment); return this; }
  public FragmentTransaction detach(Fragment fragment) { op(OP_DETACH, fragment); return this; }
  public FragmentTransaction attach(Fragment fragment) { op(OP_ATTACH, fragment); return this; }
  public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment) { return this; }
  public boolean isEmpty() { return mOps.isEmpty(); }
  public FragmentTransaction setCustomAnimations(int enter, int exit) { return this; }
  public FragmentTransaction setCustomAnimations(int enter, int exit, int popEnter, int popExit) { return this; }
  public FragmentTransaction addSharedElement(android.view.View sharedElement, String name) { return this; }
  public FragmentTransaction setTransition(int transition) { return this; }
  public FragmentTransaction setTransitionStyle(int styleRes) { return this; }
  public FragmentTransaction addToBackStack(String name) {
    if (!mAllowAddToBackStack) throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    mAddToBackStack = true;
    mName = name;
    return this;
  }
  public boolean isAddToBackStackAllowed() { return mAllowAddToBackStack; }
  public FragmentTransaction disallowAddToBackStack() {
    if (mAddToBackStack) throw new IllegalStateException("This transaction is already being added to the back stack");
    mAllowAddToBackStack = false;
    return this;
  }
  public FragmentTransaction setBreadCrumbTitle(int res) { mBreadCrumbTitleRes = res; return this; }
  public FragmentTransaction setBreadCrumbTitle(CharSequence text) { mBreadCrumbTitleRes = 0; mBreadCrumbTitleText = text; return this; }
  public FragmentTransaction setBreadCrumbShortTitle(int res) { mBreadCrumbShortTitleRes = res; return this; }
  public FragmentTransaction setBreadCrumbShortTitle(CharSequence text) { mBreadCrumbShortTitleRes = 0; mBreadCrumbShortTitleText = text; return this; }
  public FragmentTransaction setReorderingAllowed(boolean reorderingAllowed) { return this; }
  public FragmentTransaction runOnCommit(Runnable runnable) { mCommitRunnables.add(runnable); return this; }
  public int commit() { return commitInternal(); }
  public int commitAllowingStateLoss() { return commitInternal(); }
  public void commitNow() { disallowAddToBackStack(); mCommitted = true; run(); }
  public void commitNowAllowingStateLoss() { commitNow(); }
  int commitInternal() {
    if (mCommitted) throw new IllegalStateException("commit already called");
    mCommitted = true;
    mIndex = mAddToBackStack ? mManager.mBackStack.size() : -1;
    mManager.enqueue(this);
    return mIndex;
  }
  void bumpBackStackNesting(int amt) {
    if (!mAddToBackStack) return;
    for (Op op : mOps) {
      if (op.fragment != null) op.fragment.mBackStackNesting += amt;
      if (op.removed != null) for (Fragment r : op.removed) r.mBackStackNesting += amt;
    }
  }
  public void run() {
    bumpBackStackNesting(1);
    for (Op op : mOps) {
      Fragment f = op.fragment;
      switch (op.cmd) {
        case OP_ADD: mManager.addFragment(f, false); break;
        case OP_REPLACE: {
          int containerId = f.mContainerId;
          for (Fragment old : new ArrayList<>(mManager.mAdded)) {
            if (old.mContainerId == containerId) {
              if (old == f) { op.fragment = f = null; break; }
              if (op.removed == null) op.removed = new ArrayList<>();
              op.removed.add(old);
              if (mAddToBackStack) old.mBackStackNesting += 1;
              mManager.removeFragment(old);
            }
          }
          if (f != null) mManager.addFragment(f, false);
          break;
        }
        case OP_REMOVE: mManager.removeFragment(f); break;
        case OP_HIDE: mManager.hideFragment(f); break;
        case OP_SHOW: mManager.showFragment(f); break;
        case OP_DETACH: mManager.detachFragment(f); break;
        case OP_ATTACH: mManager.attachFragment(f); break;
      }
    }
    for (Fragment f : new ArrayList<>(mManager.mActive)) mManager.moveToState(f, mManager.mCurState);
    if (mAddToBackStack) mManager.addBackStackState(this);
    for (Runnable r : mCommitRunnables) r.run();
    mCommitRunnables.clear();
  }
  void popFromBackStack() {
    bumpBackStackNesting(-1);
    for (int i = mOps.size() - 1; i >= 0; i--) {
      Op op = mOps.get(i);
      Fragment f = op.fragment;
      switch (op.cmd) {
        case OP_ADD: mManager.removeFragment(f); break;
        case OP_REPLACE:
          if (f != null) mManager.removeFragment(f);
          if (op.removed != null) for (Fragment old : op.removed) mManager.addFragment(old, false);
          break;
        case OP_REMOVE: mManager.addFragment(f, false); break;
        case OP_HIDE: mManager.showFragment(f); break;
        case OP_SHOW: mManager.hideFragment(f); break;
        case OP_DETACH: mManager.attachFragment(f); break;
        case OP_ATTACH: mManager.detachFragment(f); break;
      }
    }
    for (Fragment f : new ArrayList<>(mManager.mActive)) mManager.moveToState(f, mManager.mCurState);
  }
}

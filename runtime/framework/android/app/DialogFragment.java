package android.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
  public static final int STYLE_NORMAL = 0, STYLE_NO_TITLE = 1, STYLE_NO_FRAME = 2, STYLE_NO_INPUT = 3;
  int mStyle = STYLE_NORMAL, mTheme = 0;
  boolean mCancelable = true, mShowsDialog = true, mViewDestroyed, mDismissed, mShownByMe;
  int mBackStackId = -1;
  Dialog mDialog;
  public DialogFragment() {}
  public void setStyle(int style, int theme) { mStyle = style; if (mStyle == STYLE_NO_FRAME || mStyle == STYLE_NO_INPUT) mTheme = android.R.style.Theme_Panel; if (theme != 0) mTheme = theme; }
  public void show(FragmentManager manager, String tag) {
    mDismissed = false;
    mShownByMe = true;
    FragmentTransaction ft = manager.beginTransaction();
    ft.add(this, tag);
    ft.commit();
  }
  public void showNow(FragmentManager manager, String tag) {
    mDismissed = false;
    mShownByMe = true;
    FragmentTransaction ft = manager.beginTransaction();
    ft.add(this, tag);
    ft.commitNow();
  }
  public int show(FragmentTransaction transaction, String tag) {
    mDismissed = false;
    mShownByMe = true;
    transaction.add(this, tag);
    mViewDestroyed = false;
    mBackStackId = transaction.commit();
    return mBackStackId;
  }
  public void dismiss() { dismissInternal(false); }
  public void dismissAllowingStateLoss() { dismissInternal(true); }
  void dismissInternal(boolean allowStateLoss) {
    if (mDismissed) return;
    mDismissed = true;
    mShownByMe = false;
    if (mDialog != null) { mDialog.dismiss(); mDialog = null; }
    mViewDestroyed = true;
    if (getFragmentManager() == null) return;
    if (mBackStackId >= 0) { getFragmentManager().popBackStack(mBackStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE); mBackStackId = -1; }
    else { FragmentTransaction ft = getFragmentManager().beginTransaction(); ft.remove(this); ft.commit(); }
  }
  public Dialog getDialog() { return mDialog; }
  public int getTheme() { return mTheme; }
  public void setCancelable(boolean cancelable) { mCancelable = cancelable; if (mDialog != null) mDialog.setCancelable(cancelable); }
  public boolean isCancelable() { return mCancelable; }
  public void setShowsDialog(boolean showsDialog) { mShowsDialog = showsDialog; }
  public boolean getShowsDialog() { return mShowsDialog; }
  @Override public void onAttach(android.content.Context context) { super.onAttach(context); if (!mShownByMe) mDismissed = false; }
  @Override public void onDetach() { super.onDetach(); if (!mShownByMe && !mDismissed) mDismissed = true; }
  @Override public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); mShowsDialog = mContainerId == 0; }
  @Override public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) {
    if (!mShowsDialog) return super.onGetLayoutInflater(savedInstanceState);
    mDialog = onCreateDialog(savedInstanceState);
    switch (mStyle) {
      case STYLE_NO_INPUT: mDialog.getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
      case STYLE_NO_FRAME: case STYLE_NO_TITLE: mDialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
    }
    return (LayoutInflater) mDialog.getContext().getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
  }
  public Dialog onCreateDialog(Bundle savedInstanceState) { return new Dialog(getActivity(), getTheme()); }
  public void onCancel(DialogInterface dialog) {}
  public void onDismiss(DialogInterface dialog) { if (!mViewDestroyed) dismissInternal(true); }
  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (!mShowsDialog) return;
    View view = getView();
    if (view != null) {
      if (view.getParent() != null) throw new IllegalStateException("DialogFragment can not be attached to a container view");
      mDialog.setContentView(view);
    }
    if (getActivity() != null) mDialog.setOwnerActivity(getActivity());
    mDialog.setCancelable(mCancelable);
    mDialog.setOnCancelListener(this);
    mDialog.setOnDismissListener(this);
  }
  @Override public void onStart() { super.onStart(); if (mDialog != null) { mViewDestroyed = false; mDialog.show(); } }
  @Override public void onStop() { super.onStop(); if (mDialog != null) mDialog.hide(); }
  @Override public void onDestroyView() {
    super.onDestroyView();
    if (mDialog != null) { mViewDestroyed = true; mDialog.dismiss(); mDialog = null; }
  }
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { return null; }
}

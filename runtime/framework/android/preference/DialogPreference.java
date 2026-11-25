package android.preference;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public abstract class DialogPreference extends Preference implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener, PreferenceManager.OnActivityDestroyListener {
  private AlertDialog.Builder mBuilder;
  private CharSequence mDialogTitle, mDialogMessage, mPositiveButtonText, mNegativeButtonText;
  private Drawable mDialogIcon;
  private int mDialogLayoutResId;
  private Dialog mDialog;
  private int mWhichButtonClicked;
  public DialogPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    if (attrs != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, new int[] {android.R.attr.dialogTitle, android.R.attr.dialogMessage, android.R.attr.dialogIcon,
          android.R.attr.positiveButtonText, android.R.attr.negativeButtonText, android.R.attr.dialogLayout});
      mDialogTitle = a.getText(0);
      mDialogMessage = a.getText(1);
      mDialogIcon = a.getDrawable(2);
      mPositiveButtonText = a.getText(3);
      mNegativeButtonText = a.getText(4);
      mDialogLayoutResId = a.getResourceId(5, mDialogLayoutResId);
      a.recycle();
    }
    if (mDialogTitle == null) mDialogTitle = getTitle();
    if (mPositiveButtonText == null) mPositiveButtonText = context.getString(android.R.string.ok);
    if (mNegativeButtonText == null) mNegativeButtonText = context.getString(android.R.string.cancel);
  }
  public DialogPreference(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public DialogPreference(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public DialogPreference(Context context) { this(context, null); }
  public void setDialogTitle(CharSequence dialogTitle) { mDialogTitle = dialogTitle; }
  public void setDialogTitle(int dialogTitleResId) { setDialogTitle(getContext().getString(dialogTitleResId)); }
  public CharSequence getDialogTitle() { return mDialogTitle; }
  public void setDialogMessage(CharSequence dialogMessage) { mDialogMessage = dialogMessage; }
  public void setDialogMessage(int dialogMessageResId) { setDialogMessage(getContext().getString(dialogMessageResId)); }
  public CharSequence getDialogMessage() { return mDialogMessage; }
  public void setDialogIcon(Drawable dialogIcon) { mDialogIcon = dialogIcon; }
  public void setDialogIcon(int dialogIconRes) { mDialogIcon = getContext().getResources().getDrawable(dialogIconRes); }
  public Drawable getDialogIcon() { return mDialogIcon; }
  public void setPositiveButtonText(CharSequence t) { mPositiveButtonText = t; }
  public void setPositiveButtonText(int r) { setPositiveButtonText(getContext().getString(r)); }
  public CharSequence getPositiveButtonText() { return mPositiveButtonText; }
  public void setNegativeButtonText(CharSequence t) { mNegativeButtonText = t; }
  public void setNegativeButtonText(int r) { setNegativeButtonText(getContext().getString(r)); }
  public CharSequence getNegativeButtonText() { return mNegativeButtonText; }
  public void setDialogLayoutResource(int dialogLayoutResId) { mDialogLayoutResId = dialogLayoutResId; }
  public int getDialogLayoutResource() { return mDialogLayoutResId; }
  protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {}
  @Override protected void onClick() { if (mDialog != null && mDialog.isShowing()) return; showDialog(null); }
  protected void showDialog(android.os.Bundle state) {
    Context context = getContext();
    mWhichButtonClicked = DialogInterface.BUTTON_NEGATIVE;
    mBuilder = new AlertDialog.Builder(context).setTitle(mDialogTitle).setIcon(mDialogIcon).setPositiveButton(mPositiveButtonText, this).setNegativeButton(mNegativeButtonText, this);
    View contentView = onCreateDialogView();
    if (contentView != null) { onBindDialogView(contentView); mBuilder.setView(contentView); }
    else mBuilder.setMessage(mDialogMessage);
    onPrepareDialogBuilder(mBuilder);
    if (getPreferenceManager() != null) getPreferenceManager().registerOnActivityDestroyListener(this);
    final Dialog dialog = mDialog = mBuilder.create();
    dialog.setOnDismissListener(this);
    dialog.show();
  }
  protected View onCreateDialogView() {
    if (mDialogLayoutResId == 0) return null;
    return LayoutInflater.from(mBuilder.getContext()).inflate(mDialogLayoutResId, null);
  }
  protected void onBindDialogView(View view) {
    View dialogMessageView = view.findViewById(android.R.id.message);
    if (dialogMessageView instanceof TextView) {
      final CharSequence message = getDialogMessage();
      int newVisibility = View.GONE;
      if (message != null && message.length() > 0) { ((TextView) dialogMessageView).setText(message); newVisibility = View.VISIBLE; }
      dialogMessageView.setVisibility(newVisibility);
    }
  }
  public void onClick(DialogInterface dialog, int which) { mWhichButtonClicked = which; }
  public void onDismiss(DialogInterface dialog) {
    if (getPreferenceManager() != null) getPreferenceManager().unregisterOnActivityDestroyListener(this);
    mDialog = null;
    onDialogClosed(mWhichButtonClicked == DialogInterface.BUTTON_POSITIVE);
  }
  protected void onDialogClosed(boolean positiveResult) {}
  public Dialog getDialog() { return mDialog; }
  public void onActivityDestroy() { if (mDialog != null && mDialog.isShowing()) mDialog.dismiss(); }
}

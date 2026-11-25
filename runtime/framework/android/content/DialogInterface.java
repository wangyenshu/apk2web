package android.content;

public interface DialogInterface {
  int BUTTON_POSITIVE = -1, BUTTON_NEGATIVE = -2, BUTTON_NEUTRAL = -3, BUTTON1 = -1, BUTTON2 = -2, BUTTON3 = -3;
  void cancel();
  void dismiss();
  interface OnCancelListener { void onCancel(DialogInterface d); }
  interface OnDismissListener { void onDismiss(DialogInterface d); }
  interface OnShowListener { void onShow(DialogInterface d); }
  interface OnClickListener { void onClick(DialogInterface d, int which); }
  interface OnMultiChoiceClickListener { void onClick(DialogInterface d, int which, boolean checked); }
  interface OnKeyListener { boolean onKey(DialogInterface d, int keyCode, android.view.KeyEvent e); }
}

package android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;

/** Picks a ringtone on a device; here only the stored value and "silent"/default are kept. */
public class RingtonePreference extends Preference implements PreferenceManager.OnActivityResultListener {
  private int mRingtoneType;
  private boolean mShowDefault, mShowSilent;
  public RingtonePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    if (attrs != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, new int[] {android.R.attr.ringtoneType, android.R.attr.showDefault, android.R.attr.showSilent});
      mRingtoneType = a.getInt(0, 1); mShowDefault = a.getBoolean(1, true); mShowSilent = a.getBoolean(2, true);
      a.recycle();
    }
  }
  public RingtonePreference(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public RingtonePreference(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public RingtonePreference(Context context) { this(context, null); }
  public int getRingtoneType() { return mRingtoneType; }
  public void setRingtoneType(int type) { mRingtoneType = type; }
  public boolean getShowDefault() { return mShowDefault; }
  public void setShowDefault(boolean showDefault) { mShowDefault = showDefault; }
  public boolean getShowSilent() { return mShowSilent; }
  public void setShowSilent(boolean showSilent) { mShowSilent = showSilent; }
  protected void onSaveRingtone(Uri ringtoneUri) { persistString(ringtoneUri != null ? ringtoneUri.toString() : ""); }
  protected Uri onRestoreRingtone() { final String uriString = getPersistedString(null); return uriString != null && !uriString.isEmpty() ? Uri.parse(uriString) : null; }
  @Override protected Object onGetDefaultValue(TypedArray a, int index) { return a.getString(index); }
  @Override protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValueObj) {
    String defaultValue = (String) defaultValueObj;
    if (restorePersistedValue) return;
    if (defaultValue != null && !defaultValue.isEmpty()) onSaveRingtone(Uri.parse(defaultValue));
  }
  public boolean onActivityResult(int requestCode, int resultCode, android.content.Intent data) { return false; }
}

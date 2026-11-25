package android.app;

import android.os.Bundle;

public class ActivityGroup extends Activity {
  static final String STATES_KEY = "android:states";
  private final LocalActivityManager mLocalActivityManager;
  public ActivityGroup() { this(true); }
  public ActivityGroup(boolean singleActivityMode) { mLocalActivityManager = new LocalActivityManager(this, singleActivityMode); }
  @Override protected void onCreate(Bundle b) { super.onCreate(b); mLocalActivityManager.dispatchCreate(b); }
  @Override protected void onResume() { super.onResume(); mLocalActivityManager.dispatchResume(); }
  @Override protected void onPause() { super.onPause(); mLocalActivityManager.dispatchPause(isFinishing()); }
  @Override protected void onStop() { super.onStop(); mLocalActivityManager.dispatchStop(); }
  @Override protected void onDestroy() { super.onDestroy(); mLocalActivityManager.dispatchDestroy(isFinishing()); }
  public Activity getCurrentActivity() { return mLocalActivityManager.getCurrentActivity(); }
  public final LocalActivityManager getLocalActivityManager() { return mLocalActivityManager; }
}

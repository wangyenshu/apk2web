package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * The search framework: an activity's searchable configuration (getSearchableInfo), the search dialog (startSearch: a
 * SearchView at the top of the window that launches the searchable activity with ACTION_SEARCH) and direct searches
 * (triggerSearch). There is no global search provider here.
 */
public class SearchManager implements android.content.DialogInterface.OnDismissListener, android.content.DialogInterface.OnCancelListener {
  public static final java.lang.String ACTION_KEY = "action_key";
  public static final java.lang.String ACTION_MSG = "action_msg";
  public static final java.lang.String APP_DATA = "app_data";
  public static final java.lang.String CURSOR_EXTRA_KEY_IN_PROGRESS = "in_progress";
  public static final java.lang.String EXTRA_DATA_KEY = "intent_extra_data_key";
  public static final java.lang.String EXTRA_NEW_SEARCH = "new_search";
  public static final java.lang.String EXTRA_SELECT_QUERY = "select_query";
  public static final java.lang.String EXTRA_WEB_SEARCH_PENDINGINTENT = "web_search_pendingintent";
  public static final int FLAG_QUERY_REFINEMENT = 1;
  public static final java.lang.String INTENT_ACTION_GLOBAL_SEARCH = "android.search.action.GLOBAL_SEARCH";
  public static final java.lang.String INTENT_ACTION_SEARCHABLES_CHANGED = "android.search.action.SEARCHABLES_CHANGED";
  public static final java.lang.String INTENT_ACTION_SEARCH_SETTINGS = "android.search.action.SEARCH_SETTINGS";
  public static final java.lang.String INTENT_ACTION_SEARCH_SETTINGS_CHANGED = "android.search.action.SETTINGS_CHANGED";
  public static final java.lang.String INTENT_ACTION_WEB_SEARCH_SETTINGS = "android.search.action.WEB_SEARCH_SETTINGS";
  public static final java.lang.String INTENT_GLOBAL_SEARCH_ACTIVITY_CHANGED = "android.search.action.GLOBAL_SEARCH_ACTIVITY_CHANGED";
  public static final char MENU_KEY = (char) 115;
  public static final int MENU_KEYCODE = 47;
  public static final java.lang.String QUERY = "query";
  public static final java.lang.String SHORTCUT_MIME_TYPE = "vnd.android.cursor.item/vnd.android.search.suggest";
  public static final java.lang.String SUGGEST_COLUMN_AUDIO_CHANNEL_CONFIG = "suggest_audio_channel_config";
  public static final java.lang.String SUGGEST_COLUMN_CONTENT_TYPE = "suggest_content_type";
  public static final java.lang.String SUGGEST_COLUMN_DURATION = "suggest_duration";
  public static final java.lang.String SUGGEST_COLUMN_FLAGS = "suggest_flags";
  public static final java.lang.String SUGGEST_COLUMN_FORMAT = "suggest_format";
  public static final java.lang.String SUGGEST_COLUMN_ICON_1 = "suggest_icon_1";
  public static final java.lang.String SUGGEST_COLUMN_ICON_2 = "suggest_icon_2";
  public static final java.lang.String SUGGEST_COLUMN_INTENT_ACTION = "suggest_intent_action";
  public static final java.lang.String SUGGEST_COLUMN_INTENT_DATA = "suggest_intent_data";
  public static final java.lang.String SUGGEST_COLUMN_INTENT_DATA_ID = "suggest_intent_data_id";
  public static final java.lang.String SUGGEST_COLUMN_INTENT_EXTRA_DATA = "suggest_intent_extra_data";
  public static final java.lang.String SUGGEST_COLUMN_IS_LIVE = "suggest_is_live";
  public static final java.lang.String SUGGEST_COLUMN_LAST_ACCESS_HINT = "suggest_last_access_hint";
  public static final java.lang.String SUGGEST_COLUMN_PRODUCTION_YEAR = "suggest_production_year";
  public static final java.lang.String SUGGEST_COLUMN_PURCHASE_PRICE = "suggest_purchase_price";
  public static final java.lang.String SUGGEST_COLUMN_QUERY = "suggest_intent_query";
  public static final java.lang.String SUGGEST_COLUMN_RATING_SCORE = "suggest_rating_score";
  public static final java.lang.String SUGGEST_COLUMN_RATING_STYLE = "suggest_rating_style";
  public static final java.lang.String SUGGEST_COLUMN_RENTAL_PRICE = "suggest_rental_price";
  public static final java.lang.String SUGGEST_COLUMN_RESULT_CARD_IMAGE = "suggest_result_card_image";
  public static final java.lang.String SUGGEST_COLUMN_SHORTCUT_ID = "suggest_shortcut_id";
  public static final java.lang.String SUGGEST_COLUMN_SPINNER_WHILE_REFRESHING = "suggest_spinner_while_refreshing";
  public static final java.lang.String SUGGEST_COLUMN_TEXT_1 = "suggest_text_1";
  public static final java.lang.String SUGGEST_COLUMN_TEXT_2 = "suggest_text_2";
  public static final java.lang.String SUGGEST_COLUMN_TEXT_2_URL = "suggest_text_2_url";
  public static final java.lang.String SUGGEST_COLUMN_VIDEO_HEIGHT = "suggest_video_height";
  public static final java.lang.String SUGGEST_COLUMN_VIDEO_WIDTH = "suggest_video_width";
  public static final java.lang.String SUGGEST_MIME_TYPE = "vnd.android.cursor.dir/vnd.android.search.suggest";
  public static final java.lang.String SUGGEST_NEVER_MAKE_SHORTCUT = "_-1";
  public static final java.lang.String SUGGEST_PARAMETER_LIMIT = "limit";
  public static final java.lang.String SUGGEST_URI_PATH_QUERY = "search_suggest_query";
  public static final java.lang.String SUGGEST_URI_PATH_SHORTCUT = "search_suggest_shortcut";
  public static final java.lang.String USER_QUERY = "user_query";
  static final String SEARCHABLE_META_DATA_KEY = "android.app.searchable";
  public interface OnDismissListener { void onDismiss(); }
  public interface OnCancelListener { void onCancel(); }
  private final Context mContext;
  private OnDismissListener mDismissListener;
  private OnCancelListener mCancelListener;
  private Dialog mSearchDialog;
  /** web: the service object (Context.getSystemService(SEARCH_SERVICE)) */
  public SearchManager(Context context) { mContext = context; }

  public SearchableInfo getSearchableInfo(ComponentName componentName) {
    if (componentName == null) return null;
    SearchableInfo s = SearchableInfo.forActivity(mContext, componentName);
    if (s != null) return s;
    // an activity can point to the searchable activity with "android.app.default_searchable"
    try {
      android.content.pm.ActivityInfo ai = mContext.getPackageManager().getActivityInfo(componentName, android.content.pm.PackageManager.GET_META_DATA);
      String target = ai.metaData != null ? ai.metaData.getString("android.app.default_searchable") : null;
      if (target == null) {
        android.content.pm.ApplicationInfo app = mContext.getApplicationInfo();
        target = app.metaData != null ? app.metaData.getString("android.app.default_searchable") : null;
      }
      if (target == null || target.equals("*")) return null;
      String cls = target.startsWith(".") ? componentName.getPackageName() + target : target;
      if (cls.equals(componentName.getClassName())) return null;
      return SearchableInfo.forActivity(mContext, new ComponentName(componentName.getPackageName(), cls));
    } catch (Exception e) { return null; }
  }
  public java.util.List<SearchableInfo> getSearchablesInGlobalSearch() { return new java.util.ArrayList<>(); }
  /** no global search activity is installed */
  public ComponentName getGlobalSearchActivity() { return null; }

  public void startSearch(String initialQuery, boolean selectInitialQuery, ComponentName launchActivity, Bundle appSearchData, boolean globalSearch) {
    if (globalSearch) return;   // there is no global search activity
    final SearchableInfo info = getSearchableInfo(launchActivity);
    if (info == null) return;
    stopSearch();
    Context ctx = mContext;
    if (ctx instanceof Activity && ((Activity) ctx).getComponentName() != null && !((Activity) ctx).getComponentName().equals(launchActivity)) { /* the caller's window */ }
    final Dialog d = new Dialog(ctx);
    d.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
    android.widget.SearchView sv = new android.widget.SearchView(ctx);
    sv.setSearchableInfo(info);
    sv.setIconifiedByDefault(false);
    sv.setSubmitButtonEnabled(true);
    sv.a2wSetAppSearchData(appSearchData);
    if (initialQuery != null) { sv.setQuery(initialQuery, false); if (selectInitialQuery) sv.a2wSelectQuery(); }
    sv.setOnCloseListener(() -> { d.cancel(); return true; });
    sv.a2wSetOnSubmitted(d::dismiss);
    d.setContentView(sv);
    android.view.Window w = d.getWindow();
    if (w != null) {
      w.setGravity(android.view.Gravity.TOP | android.view.Gravity.FILL_HORIZONTAL);
      w.setLayout(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    d.setOnDismissListener(this);
    d.setOnCancelListener(this);
    mSearchDialog = d;
    d.show();
    sv.requestFocus();
  }
  /** starts the searchable activity with ACTION_SEARCH and the query, without a dialog */
  public void triggerSearch(String query, ComponentName launchActivity, Bundle appSearchData) {
    if (query == null || android.text.TextUtils.getTrimmedLength(query) == 0) return;
    SearchableInfo info = getSearchableInfo(launchActivity);
    if (info == null) return;
    mContext.startActivity(searchIntent(info, query, appSearchData));
  }
  static Intent searchIntent(SearchableInfo info, String query, Bundle appSearchData) {
    Intent intent = new Intent(Intent.ACTION_SEARCH);
    intent.setComponent(info.getSearchActivity());
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra(USER_QUERY, query);
    intent.putExtra(QUERY, query);
    if (appSearchData != null) intent.putExtra(APP_DATA, appSearchData);
    return intent;
  }
  public void stopSearch() { if (mSearchDialog != null) { Dialog d = mSearchDialog; mSearchDialog = null; d.dismiss(); } }
  public void setOnDismissListener(OnDismissListener listener) { mDismissListener = listener; }
  public void setOnCancelListener(OnCancelListener listener) { mCancelListener = listener; }
  @Deprecated public void onCancel(android.content.DialogInterface dialog) { if (mCancelListener != null) mCancelListener.onCancel(); }
  @Deprecated public void onDismiss(android.content.DialogInterface dialog) { if (dialog == mSearchDialog) mSearchDialog = null; if (mDismissListener != null) mDismissListener.onDismiss(); }
}

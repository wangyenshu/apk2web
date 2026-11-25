package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.Parcel;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;

/** An activity's search configuration: its android.app.searchable meta-data (the &lt;searchable&gt; XML resource). */
public final class SearchableInfo implements android.os.Parcelable {
  static final String NS = "http://schemas.android.com/apk/res/android";
  static final int SEARCH_MODE_BADGE_LABEL = 0x04, SEARCH_MODE_BADGE_ICON = 0x08, SEARCH_MODE_QUERY_REWRITE_FROM_DATA = 0x10, SEARCH_MODE_QUERY_REWRITE_FROM_TEXT = 0x20;
  static final int VOICE_SEARCH_SHOW_BUTTON = 1, VOICE_SEARCH_LAUNCH_WEB_SEARCH = 2, VOICE_SEARCH_LAUNCH_RECOGNIZER = 4;
  private int mLabelId, mHintId, mSearchMode, mIconId, mSearchButtonText, mSearchInputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL;
  private int mSearchImeOptions = EditorInfo.IME_ACTION_GO, mSettingsDescriptionId, mSuggestThreshold, mVoiceSearchMode, mVoiceLanguageModeId, mVoicePromptTextId;
  private int mVoiceLanguageId, mVoiceMaxResults;
  private boolean mIncludeInGlobalSearch, mQueryAfterZeroResults, mAutoUrlDetect;
  private String mSuggestAuthority, mSuggestPath, mSuggestSelection, mSuggestIntentAction, mSuggestIntentData, mSuggestProviderPackage;
  private ComponentName mSearchActivity;
  private SearchableInfo() {}

  /** the activity's configuration, or null when it has none (no meta-data, or no label) */
  static SearchableInfo forActivity(Context context, ComponentName activity) {
    try {
      PackageManager pm = context.getPackageManager();
      ActivityInfo ai = pm.getActivityInfo(activity, PackageManager.GET_META_DATA);
      XmlResourceParser xml = ai.loadXmlMetaData(pm, SearchManager.SEARCHABLE_META_DATA_KEY);
      if (xml == null) return null;
      try {
        int type;
        while ((type = xml.next()) != XmlResourceParser.END_DOCUMENT) {
          if (type == XmlResourceParser.START_TAG && "searchable".equals(xml.getName())) {
            SearchableInfo s = new SearchableInfo();
            s.mSearchActivity = activity;
            s.read(xml);
            if (s.mLabelId == 0) throw new IllegalArgumentException("Search label must be a resource reference.");
            readActionKeys(xml);
            // the providers are the app's own
            if (s.mSuggestAuthority != null) s.mSuggestProviderPackage = context.getPackageName();
            return s;
          }
        }
      } finally { xml.close(); }
    } catch (Exception e) { android.util.Log.w("SearchableInfo", "Invalid searchable metadata for " + activity.flattenToShortString() + ": " + e.getMessage()); }
    return null;
  }
  private static void readActionKeys(XmlResourceParser xml) {}
  private void read(XmlResourceParser x) {
    mLabelId = x.getAttributeResourceValue(NS, "label", 0);
    mHintId = x.getAttributeResourceValue(NS, "hint", 0);
    mIconId = x.getAttributeResourceValue(NS, "icon", 0);
    mSearchButtonText = x.getAttributeResourceValue(NS, "searchButtonText", 0);
    String v;
    if ((v = x.getAttributeValue(NS, "searchMode")) != null) mSearchMode = flags(v, new String[] {"showSearchLabelAsBadge", "showSearchIconAsBadge", "queryRewriteFromData", "queryRewriteFromText"},
        new int[] {SEARCH_MODE_BADGE_LABEL, SEARCH_MODE_BADGE_ICON, SEARCH_MODE_QUERY_REWRITE_FROM_DATA, SEARCH_MODE_QUERY_REWRITE_FROM_TEXT});
    if ((v = x.getAttributeValue(NS, "inputType")) != null) mSearchInputType = a2w.Values.inputType(v);
    if ((v = x.getAttributeValue(NS, "imeOptions")) != null) mSearchImeOptions = a2w.Values.imeOptions(v);
    mIncludeInGlobalSearch = x.getAttributeBooleanValue(NS, "includeInGlobalSearch", false);
    mQueryAfterZeroResults = x.getAttributeBooleanValue(NS, "queryAfterZeroResults", false);
    mAutoUrlDetect = x.getAttributeBooleanValue(NS, "autoUrlDetect", false);
    mSettingsDescriptionId = x.getAttributeResourceValue(NS, "searchSettingsDescription", 0);
    mSuggestAuthority = x.getAttributeValue(NS, "searchSuggestAuthority");
    mSuggestPath = x.getAttributeValue(NS, "searchSuggestPath");
    mSuggestSelection = x.getAttributeValue(NS, "searchSuggestSelection");
    mSuggestIntentAction = x.getAttributeValue(NS, "searchSuggestIntentAction");
    mSuggestIntentData = x.getAttributeValue(NS, "searchSuggestIntentData");
    mSuggestThreshold = x.getAttributeIntValue(NS, "searchSuggestThreshold", 0);
    if ((v = x.getAttributeValue(NS, "voiceSearchMode")) != null) mVoiceSearchMode = flags(v, new String[] {"showVoiceSearchButton", "launchWebSearch", "launchRecognizer"},
        new int[] {VOICE_SEARCH_SHOW_BUTTON, VOICE_SEARCH_LAUNCH_WEB_SEARCH, VOICE_SEARCH_LAUNCH_RECOGNIZER});
    mVoiceLanguageModeId = x.getAttributeResourceValue(NS, "voiceLanguageModel", 0);
    mVoicePromptTextId = x.getAttributeResourceValue(NS, "voicePromptText", 0);
    mVoiceLanguageId = x.getAttributeResourceValue(NS, "voiceLanguage", 0);
    mVoiceMaxResults = x.getAttributeIntValue(NS, "voiceMaxResults", 0);
  }
  private static int flags(String v, String[] names, int[] values) {
    int r = 0;
    for (String p : v.split("\\|")) {
      p = p.trim();
      boolean found = false;
      for (int i = 0; i < names.length; i++) if (names[i].equals(p)) { r |= values[i]; found = true; }
      if (!found) { try { r |= p.startsWith("0x") ? Integer.parseInt(p.substring(2), 16) : Integer.parseInt(p); } catch (NumberFormatException e) { /* unknown flag */ } }
    }
    return r;
  }

  public String getSuggestAuthority() { return mSuggestAuthority; }
  public String getSuggestPackage() { return mSuggestProviderPackage; }
  public ComponentName getSearchActivity() { return mSearchActivity; }
  public String getSuggestPath() { return mSuggestPath; }
  public String getSuggestSelection() { return mSuggestSelection; }
  public String getSuggestIntentAction() { return mSuggestIntentAction; }
  public String getSuggestIntentData() { return mSuggestIntentData; }
  public int getSuggestThreshold() { return mSuggestThreshold; }
  /** the label (hidden on Android) */
  int getLabelId() { return mLabelId; }
  public int getHintId() { return mHintId; }
  public int getSettingsDescriptionId() { return mSettingsDescriptionId; }
  public boolean getVoiceSearchEnabled() { return (mVoiceSearchMode & VOICE_SEARCH_SHOW_BUTTON) != 0; }
  public boolean getVoiceSearchLaunchWebSearch() { return (mVoiceSearchMode & VOICE_SEARCH_LAUNCH_WEB_SEARCH) != 0; }
  public boolean getVoiceSearchLaunchRecognizer() { return (mVoiceSearchMode & VOICE_SEARCH_LAUNCH_RECOGNIZER) != 0; }
  public int getVoiceLanguageModeId() { return mVoiceLanguageModeId; }
  public int getVoicePromptTextId() { return mVoicePromptTextId; }
  public int getVoiceLanguageId() { return mVoiceLanguageId; }
  public int getVoiceMaxResults() { return mVoiceMaxResults; }
  public int getInputType() { return mSearchInputType; }
  public int getImeOptions() { return mSearchImeOptions; }
  public boolean shouldRewriteQueryFromData() { return (mSearchMode & SEARCH_MODE_QUERY_REWRITE_FROM_DATA) != 0; }
  public boolean shouldRewriteQueryFromText() { return (mSearchMode & SEARCH_MODE_QUERY_REWRITE_FROM_TEXT) != 0; }
  public boolean shouldIncludeInGlobalSearch() { return mIncludeInGlobalSearch; }
  public boolean queryAfterZeroResults() { return mQueryAfterZeroResults; }
  public boolean autoUrlDetect() { return mAutoUrlDetect; }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(mLabelId); dest.writeString(mSearchActivity == null ? null : mSearchActivity.flattenToString()); dest.writeInt(mHintId); dest.writeInt(mSearchMode);
    dest.writeInt(mIconId); dest.writeInt(mSearchButtonText); dest.writeInt(mSearchInputType); dest.writeInt(mSearchImeOptions);
    dest.writeInt(mIncludeInGlobalSearch ? 1 : 0); dest.writeInt(mQueryAfterZeroResults ? 1 : 0); dest.writeInt(mAutoUrlDetect ? 1 : 0);
    dest.writeInt(mSettingsDescriptionId); dest.writeString(mSuggestAuthority); dest.writeString(mSuggestPath); dest.writeString(mSuggestSelection);
    dest.writeString(mSuggestIntentAction); dest.writeString(mSuggestIntentData); dest.writeInt(mSuggestThreshold); dest.writeString(mSuggestProviderPackage);
    dest.writeInt(mVoiceSearchMode); dest.writeInt(mVoiceLanguageModeId); dest.writeInt(mVoicePromptTextId); dest.writeInt(mVoiceLanguageId); dest.writeInt(mVoiceMaxResults);
  }
  public static final android.os.Parcelable.Creator<SearchableInfo> CREATOR = new Creator();
  static final class Creator implements android.os.Parcelable.Creator<SearchableInfo> {
    public SearchableInfo createFromParcel(Parcel in) {
      SearchableInfo s = new SearchableInfo();
      s.mLabelId = in.readInt(); String a = in.readString(); s.mSearchActivity = a == null ? null : ComponentName.unflattenFromString(a);
      s.mHintId = in.readInt(); s.mSearchMode = in.readInt(); s.mIconId = in.readInt(); s.mSearchButtonText = in.readInt(); s.mSearchInputType = in.readInt();
      s.mSearchImeOptions = in.readInt(); s.mIncludeInGlobalSearch = in.readInt() != 0; s.mQueryAfterZeroResults = in.readInt() != 0; s.mAutoUrlDetect = in.readInt() != 0;
      s.mSettingsDescriptionId = in.readInt(); s.mSuggestAuthority = in.readString(); s.mSuggestPath = in.readString(); s.mSuggestSelection = in.readString();
      s.mSuggestIntentAction = in.readString(); s.mSuggestIntentData = in.readString(); s.mSuggestThreshold = in.readInt(); s.mSuggestProviderPackage = in.readString();
      s.mVoiceSearchMode = in.readInt(); s.mVoiceLanguageModeId = in.readInt(); s.mVoicePromptTextId = in.readInt(); s.mVoiceLanguageId = in.readInt(); s.mVoiceMaxResults = in.readInt();
      return s;
    }
    public SearchableInfo[] newArray(int size) { return new SearchableInfo[size]; }
  }
}

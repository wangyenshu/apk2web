package android.content;

import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

/**
 * An intent: action, data, type, categories, component, flags, extras, selector, clip data and source bounds. Parcels
 * as AOSP writes it; toUri/parseUri use AOSP's "#Intent;...;end" format.
 */
public class Intent implements Parcelable, Cloneable {
  public static final String ACTION_AIRPLANE_MODE_CHANGED = "android.intent.action.AIRPLANE_MODE";
  public static final String ACTION_ALL_APPS = "android.intent.action.ALL_APPS";
  public static final String ACTION_ANSWER = "android.intent.action.ANSWER";
  public static final String ACTION_APPLICATION_LOCALE_CHANGED = "android.intent.action.APPLICATION_LOCALE_CHANGED";
  public static final String ACTION_APPLICATION_PREFERENCES = "android.intent.action.APPLICATION_PREFERENCES";
  public static final String ACTION_APPLICATION_RESTRICTIONS_CHANGED = "android.intent.action.APPLICATION_RESTRICTIONS_CHANGED";
  public static final String ACTION_APP_ERROR = "android.intent.action.APP_ERROR";
  public static final String ACTION_ASSIST = "android.intent.action.ASSIST";
  public static final String ACTION_ATTACH_DATA = "android.intent.action.ATTACH_DATA";
  public static final String ACTION_AUTO_REVOKE_PERMISSIONS = "android.intent.action.AUTO_REVOKE_PERMISSIONS";
  public static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";
  public static final String ACTION_BATTERY_LOW = "android.intent.action.BATTERY_LOW";
  public static final String ACTION_BATTERY_OKAY = "android.intent.action.BATTERY_OKAY";
  public static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
  public static final String ACTION_BUG_REPORT = "android.intent.action.BUG_REPORT";
  public static final String ACTION_CALL = "android.intent.action.CALL";
  public static final String ACTION_CALL_BUTTON = "android.intent.action.CALL_BUTTON";
  public static final String ACTION_CAMERA_BUTTON = "android.intent.action.CAMERA_BUTTON";
  public static final String ACTION_CARRIER_SETUP = "android.intent.action.CARRIER_SETUP";
  public static final String ACTION_CHOOSER = "android.intent.action.CHOOSER";
  public static final String ACTION_CLOSE_SYSTEM_DIALOGS = "android.intent.action.CLOSE_SYSTEM_DIALOGS";
  public static final String ACTION_CONFIGURATION_CHANGED = "android.intent.action.CONFIGURATION_CHANGED";
  public static final String ACTION_CREATE_DOCUMENT = "android.intent.action.CREATE_DOCUMENT";
  public static final String ACTION_CREATE_NOTE = "android.intent.action.CREATE_NOTE";
  public static final String ACTION_CREATE_REMINDER = "android.intent.action.CREATE_REMINDER";
  public static final String ACTION_CREATE_SHORTCUT = "android.intent.action.CREATE_SHORTCUT";
  public static final String ACTION_DATE_CHANGED = "android.intent.action.DATE_CHANGED";
  public static final String ACTION_DEFAULT = "android.intent.action.VIEW";
  public static final String ACTION_DEFINE = "android.intent.action.DEFINE";
  public static final String ACTION_DELETE = "android.intent.action.DELETE";
  public static final String ACTION_DEVICE_STORAGE_LOW = "android.intent.action.DEVICE_STORAGE_LOW";
  public static final String ACTION_DEVICE_STORAGE_OK = "android.intent.action.DEVICE_STORAGE_OK";
  public static final String ACTION_DIAL = "android.intent.action.DIAL";
  public static final String ACTION_DOCK_EVENT = "android.intent.action.DOCK_EVENT";
  public static final String ACTION_DREAMING_STARTED = "android.intent.action.DREAMING_STARTED";
  public static final String ACTION_DREAMING_STOPPED = "android.intent.action.DREAMING_STOPPED";
  public static final String ACTION_EDIT = "android.intent.action.EDIT";
  public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
  public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
  public static final String ACTION_FACTORY_TEST = "android.intent.action.FACTORY_TEST";
  public static final String ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT";
  public static final String ACTION_GET_RESTRICTION_ENTRIES = "android.intent.action.GET_RESTRICTION_ENTRIES";
  public static final String ACTION_GTALK_SERVICE_CONNECTED = "android.intent.action.GTALK_CONNECTED";
  public static final String ACTION_GTALK_SERVICE_DISCONNECTED = "android.intent.action.GTALK_DISCONNECTED";
  public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
  public static final String ACTION_INPUT_METHOD_CHANGED = "android.intent.action.INPUT_METHOD_CHANGED";
  public static final String ACTION_INSERT = "android.intent.action.INSERT";
  public static final String ACTION_INSERT_OR_EDIT = "android.intent.action.INSERT_OR_EDIT";
  public static final String ACTION_INSTALL_FAILURE = "android.intent.action.INSTALL_FAILURE";
  public static final String ACTION_INSTALL_PACKAGE = "android.intent.action.INSTALL_PACKAGE";
  public static final String ACTION_LAUNCH_CAPTURE_CONTENT_ACTIVITY_FOR_NOTE = "android.intent.action.LAUNCH_CAPTURE_CONTENT_ACTIVITY_FOR_NOTE";
  public static final String ACTION_LOCALE_CHANGED = "android.intent.action.LOCALE_CHANGED";
  public static final String ACTION_LOCKED_BOOT_COMPLETED = "android.intent.action.LOCKED_BOOT_COMPLETED";
  public static final String ACTION_MAIN = "android.intent.action.MAIN";
  public static final String ACTION_MANAGED_PROFILE_ADDED = "android.intent.action.MANAGED_PROFILE_ADDED";
  public static final String ACTION_MANAGED_PROFILE_AVAILABLE = "android.intent.action.MANAGED_PROFILE_AVAILABLE";
  public static final String ACTION_MANAGED_PROFILE_REMOVED = "android.intent.action.MANAGED_PROFILE_REMOVED";
  public static final String ACTION_MANAGED_PROFILE_UNAVAILABLE = "android.intent.action.MANAGED_PROFILE_UNAVAILABLE";
  public static final String ACTION_MANAGED_PROFILE_UNLOCKED = "android.intent.action.MANAGED_PROFILE_UNLOCKED";
  public static final String ACTION_MANAGE_NETWORK_USAGE = "android.intent.action.MANAGE_NETWORK_USAGE";
  public static final String ACTION_MANAGE_PACKAGE_STORAGE = "android.intent.action.MANAGE_PACKAGE_STORAGE";
  public static final String ACTION_MANAGE_UNUSED_APPS = "android.intent.action.MANAGE_UNUSED_APPS";
  public static final String ACTION_MEDIA_BAD_REMOVAL = "android.intent.action.MEDIA_BAD_REMOVAL";
  public static final String ACTION_MEDIA_BUTTON = "android.intent.action.MEDIA_BUTTON";
  public static final String ACTION_MEDIA_CHECKING = "android.intent.action.MEDIA_CHECKING";
  public static final String ACTION_MEDIA_EJECT = "android.intent.action.MEDIA_EJECT";
  public static final String ACTION_MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";
  public static final String ACTION_MEDIA_NOFS = "android.intent.action.MEDIA_NOFS";
  public static final String ACTION_MEDIA_REMOVED = "android.intent.action.MEDIA_REMOVED";
  public static final String ACTION_MEDIA_SCANNER_FINISHED = "android.intent.action.MEDIA_SCANNER_FINISHED";
  public static final String ACTION_MEDIA_SCANNER_SCAN_FILE = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";
  public static final String ACTION_MEDIA_SCANNER_STARTED = "android.intent.action.MEDIA_SCANNER_STARTED";
  public static final String ACTION_MEDIA_SHARED = "android.intent.action.MEDIA_SHARED";
  public static final String ACTION_MEDIA_UNMOUNTABLE = "android.intent.action.MEDIA_UNMOUNTABLE";
  public static final String ACTION_MEDIA_UNMOUNTED = "android.intent.action.MEDIA_UNMOUNTED";
  public static final String ACTION_MY_PACKAGE_REPLACED = "android.intent.action.MY_PACKAGE_REPLACED";
  public static final String ACTION_MY_PACKAGE_SUSPENDED = "android.intent.action.MY_PACKAGE_SUSPENDED";
  public static final String ACTION_MY_PACKAGE_UNSUSPENDED = "android.intent.action.MY_PACKAGE_UNSUSPENDED";
  public static final String ACTION_NEW_OUTGOING_CALL = "android.intent.action.NEW_OUTGOING_CALL";
  public static final String ACTION_OPEN_DOCUMENT = "android.intent.action.OPEN_DOCUMENT";
  public static final String ACTION_OPEN_DOCUMENT_TREE = "android.intent.action.OPEN_DOCUMENT_TREE";
  public static final String ACTION_PACKAGES_SUSPENDED = "android.intent.action.PACKAGES_SUSPENDED";
  public static final String ACTION_PACKAGES_UNSUSPENDED = "android.intent.action.PACKAGES_UNSUSPENDED";
  public static final String ACTION_PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";
  public static final String ACTION_PACKAGE_CHANGED = "android.intent.action.PACKAGE_CHANGED";
  public static final String ACTION_PACKAGE_DATA_CLEARED = "android.intent.action.PACKAGE_DATA_CLEARED";
  public static final String ACTION_PACKAGE_FIRST_LAUNCH = "android.intent.action.PACKAGE_FIRST_LAUNCH";
  public static final String ACTION_PACKAGE_FULLY_REMOVED = "android.intent.action.PACKAGE_FULLY_REMOVED";
  public static final String ACTION_PACKAGE_INSTALL = "android.intent.action.PACKAGE_INSTALL";
  public static final String ACTION_PACKAGE_NEEDS_VERIFICATION = "android.intent.action.PACKAGE_NEEDS_VERIFICATION";
  public static final String ACTION_PACKAGE_REMOVED = "android.intent.action.PACKAGE_REMOVED";
  public static final String ACTION_PACKAGE_REPLACED = "android.intent.action.PACKAGE_REPLACED";
  public static final String ACTION_PACKAGE_RESTARTED = "android.intent.action.PACKAGE_RESTARTED";
  public static final String ACTION_PACKAGE_UNSTOPPED = "android.intent.action.PACKAGE_UNSTOPPED";
  public static final String ACTION_PACKAGE_VERIFIED = "android.intent.action.PACKAGE_VERIFIED";
  public static final String ACTION_PASTE = "android.intent.action.PASTE";
  public static final String ACTION_PICK = "android.intent.action.PICK";
  public static final String ACTION_PICK_ACTIVITY = "android.intent.action.PICK_ACTIVITY";
  public static final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
  public static final String ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";
  public static final String ACTION_POWER_USAGE_SUMMARY = "android.intent.action.POWER_USAGE_SUMMARY";
  public static final String ACTION_PROCESS_TEXT = "android.intent.action.PROCESS_TEXT";
  public static final String ACTION_PROFILE_ACCESSIBLE = "android.intent.action.PROFILE_ACCESSIBLE";
  public static final String ACTION_PROFILE_ADDED = "android.intent.action.PROFILE_ADDED";
  public static final String ACTION_PROFILE_AVAILABLE = "android.intent.action.PROFILE_AVAILABLE";
  public static final String ACTION_PROFILE_INACCESSIBLE = "android.intent.action.PROFILE_INACCESSIBLE";
  public static final String ACTION_PROFILE_REMOVED = "android.intent.action.PROFILE_REMOVED";
  public static final String ACTION_PROFILE_UNAVAILABLE = "android.intent.action.PROFILE_UNAVAILABLE";
  public static final String ACTION_PROVIDER_CHANGED = "android.intent.action.PROVIDER_CHANGED";
  public static final String ACTION_QUICK_CLOCK = "android.intent.action.QUICK_CLOCK";
  public static final String ACTION_QUICK_VIEW = "android.intent.action.QUICK_VIEW";
  public static final String ACTION_REBOOT = "android.intent.action.REBOOT";
  public static final String ACTION_RUN = "android.intent.action.RUN";
  public static final String ACTION_SAFETY_CENTER = "android.intent.action.SAFETY_CENTER";
  public static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";
  public static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";
  public static final String ACTION_SEARCH = "android.intent.action.SEARCH";
  public static final String ACTION_SEARCH_LONG_PRESS = "android.intent.action.SEARCH_LONG_PRESS";
  public static final String ACTION_SEND = "android.intent.action.SEND";
  public static final String ACTION_SENDTO = "android.intent.action.SENDTO";
  public static final String ACTION_SEND_MULTIPLE = "android.intent.action.SEND_MULTIPLE";
  public static final String ACTION_SET_WALLPAPER = "android.intent.action.SET_WALLPAPER";
  public static final String ACTION_SHOW_APP_INFO = "android.intent.action.SHOW_APP_INFO";
  public static final String ACTION_SHOW_WORK_APPS = "android.intent.action.SHOW_WORK_APPS";
  public static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
  public static final String ACTION_SYNC = "android.intent.action.SYNC";
  public static final String ACTION_SYSTEM_TUTORIAL = "android.intent.action.SYSTEM_TUTORIAL";
  public static final String ACTION_TIMEZONE_CHANGED = "android.intent.action.TIMEZONE_CHANGED";
  public static final String ACTION_TIME_CHANGED = "android.intent.action.TIME_SET";
  public static final String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";
  public static final String ACTION_TRANSLATE = "android.intent.action.TRANSLATE";
  public static final String ACTION_UID_REMOVED = "android.intent.action.UID_REMOVED";
  public static final String ACTION_UMS_CONNECTED = "android.intent.action.UMS_CONNECTED";
  public static final String ACTION_UMS_DISCONNECTED = "android.intent.action.UMS_DISCONNECTED";
  public static final String ACTION_UNARCHIVE_PACKAGE = "android.intent.action.UNARCHIVE_PACKAGE";
  public static final String ACTION_UNINSTALL_PACKAGE = "android.intent.action.UNINSTALL_PACKAGE";
  public static final String ACTION_USER_BACKGROUND = "android.intent.action.USER_BACKGROUND";
  public static final String ACTION_USER_FOREGROUND = "android.intent.action.USER_FOREGROUND";
  public static final String ACTION_USER_INITIALIZE = "android.intent.action.USER_INITIALIZE";
  public static final String ACTION_USER_PRESENT = "android.intent.action.USER_PRESENT";
  public static final String ACTION_USER_UNLOCKED = "android.intent.action.USER_UNLOCKED";
  public static final String ACTION_VIEW = "android.intent.action.VIEW";
  public static final String ACTION_VIEW_LOCUS = "android.intent.action.VIEW_LOCUS";
  public static final String ACTION_VIEW_PERMISSION_USAGE = "android.intent.action.VIEW_PERMISSION_USAGE";
  public static final String ACTION_VIEW_PERMISSION_USAGE_FOR_PERIOD = "android.intent.action.VIEW_PERMISSION_USAGE_FOR_PERIOD";
  public static final String ACTION_VOICE_COMMAND = "android.intent.action.VOICE_COMMAND";
  public static final String ACTION_WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED";
  public static final String ACTION_WEB_SEARCH = "android.intent.action.WEB_SEARCH";
  public static final int CAPTURE_CONTENT_FOR_NOTE_BLOCKED_BY_ADMIN = 4;
  public static final int CAPTURE_CONTENT_FOR_NOTE_FAILED = 1;
  public static final int CAPTURE_CONTENT_FOR_NOTE_SUCCESS = 0;
  public static final int CAPTURE_CONTENT_FOR_NOTE_USER_CANCELED = 2;
  public static final int CAPTURE_CONTENT_FOR_NOTE_WINDOW_MODE_UNSUPPORTED = 3;
  public static final String CATEGORY_ACCESSIBILITY_SHORTCUT_TARGET = "android.intent.category.ACCESSIBILITY_SHORTCUT_TARGET";
  public static final String CATEGORY_ALTERNATIVE = "android.intent.category.ALTERNATIVE";
  public static final String CATEGORY_APP_BROWSER = "android.intent.category.APP_BROWSER";
  public static final String CATEGORY_APP_CALCULATOR = "android.intent.category.APP_CALCULATOR";
  public static final String CATEGORY_APP_CALENDAR = "android.intent.category.APP_CALENDAR";
  public static final String CATEGORY_APP_CONTACTS = "android.intent.category.APP_CONTACTS";
  public static final String CATEGORY_APP_EMAIL = "android.intent.category.APP_EMAIL";
  public static final String CATEGORY_APP_FILES = "android.intent.category.APP_FILES";
  public static final String CATEGORY_APP_FITNESS = "android.intent.category.APP_FITNESS";
  public static final String CATEGORY_APP_GALLERY = "android.intent.category.APP_GALLERY";
  public static final String CATEGORY_APP_MAPS = "android.intent.category.APP_MAPS";
  public static final String CATEGORY_APP_MARKET = "android.intent.category.APP_MARKET";
  public static final String CATEGORY_APP_MESSAGING = "android.intent.category.APP_MESSAGING";
  public static final String CATEGORY_APP_MUSIC = "android.intent.category.APP_MUSIC";
  public static final String CATEGORY_APP_WEATHER = "android.intent.category.APP_WEATHER";
  public static final String CATEGORY_BROWSABLE = "android.intent.category.BROWSABLE";
  public static final String CATEGORY_CAR_DOCK = "android.intent.category.CAR_DOCK";
  public static final String CATEGORY_CAR_MODE = "android.intent.category.CAR_MODE";
  public static final String CATEGORY_DEFAULT = "android.intent.category.DEFAULT";
  public static final String CATEGORY_DESK_DOCK = "android.intent.category.DESK_DOCK";
  public static final String CATEGORY_DEVELOPMENT_PREFERENCE = "android.intent.category.DEVELOPMENT_PREFERENCE";
  public static final String CATEGORY_EMBED = "android.intent.category.EMBED";
  public static final String CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST = "android.intent.category.FRAMEWORK_INSTRUMENTATION_TEST";
  public static final String CATEGORY_HE_DESK_DOCK = "android.intent.category.HE_DESK_DOCK";
  public static final String CATEGORY_HOME = "android.intent.category.HOME";
  public static final String CATEGORY_INFO = "android.intent.category.INFO";
  public static final String CATEGORY_LAUNCHER = "android.intent.category.LAUNCHER";
  public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
  public static final String CATEGORY_LE_DESK_DOCK = "android.intent.category.LE_DESK_DOCK";
  public static final String CATEGORY_MONKEY = "android.intent.category.MONKEY";
  public static final String CATEGORY_OPENABLE = "android.intent.category.OPENABLE";
  public static final String CATEGORY_PREFERENCE = "android.intent.category.PREFERENCE";
  public static final String CATEGORY_SAMPLE_CODE = "android.intent.category.SAMPLE_CODE";
  public static final String CATEGORY_SECONDARY_HOME = "android.intent.category.SECONDARY_HOME";
  public static final String CATEGORY_SELECTED_ALTERNATIVE = "android.intent.category.SELECTED_ALTERNATIVE";
  public static final String CATEGORY_TAB = "android.intent.category.TAB";
  public static final String CATEGORY_TEST = "android.intent.category.TEST";
  public static final String CATEGORY_TYPED_OPENABLE = "android.intent.category.TYPED_OPENABLE";
  public static final String CATEGORY_UNIT_TEST = "android.intent.category.UNIT_TEST";
  public static final String CATEGORY_VOICE = "android.intent.category.VOICE";
  public static final String CATEGORY_VR_HOME = "android.intent.category.VR_HOME";
  public static final int CHOOSER_CONTENT_TYPE_ALBUM = 1;
  public static final String EXTRA_ALARM_COUNT = "android.intent.extra.ALARM_COUNT";
  public static final String EXTRA_ALLOW_MULTIPLE = "android.intent.extra.ALLOW_MULTIPLE";
  public static final String EXTRA_ALLOW_REPLACE = "android.intent.extra.ALLOW_REPLACE";
  public static final String EXTRA_ALTERNATE_INTENTS = "android.intent.extra.ALTERNATE_INTENTS";
  public static final String EXTRA_ARCHIVAL = "android.intent.extra.ARCHIVAL";
  public static final String EXTRA_ASSIST_CONTEXT = "android.intent.extra.ASSIST_CONTEXT";
  public static final String EXTRA_ASSIST_INPUT_DEVICE_ID = "android.intent.extra.ASSIST_INPUT_DEVICE_ID";
  public static final String EXTRA_ASSIST_INPUT_HINT_KEYBOARD = "android.intent.extra.ASSIST_INPUT_HINT_KEYBOARD";
  public static final String EXTRA_ASSIST_PACKAGE = "android.intent.extra.ASSIST_PACKAGE";
  public static final String EXTRA_ASSIST_UID = "android.intent.extra.ASSIST_UID";
  public static final String EXTRA_ATTRIBUTION_TAGS = "android.intent.extra.ATTRIBUTION_TAGS";
  public static final String EXTRA_AUTO_LAUNCH_SINGLE_CHOICE = "android.intent.extra.AUTO_LAUNCH_SINGLE_CHOICE";
  public static final String EXTRA_BCC = "android.intent.extra.BCC";
  public static final String EXTRA_BUG_REPORT = "android.intent.extra.BUG_REPORT";
  public static final String EXTRA_CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE = "android.intent.extra.CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE";
  public static final String EXTRA_CC = "android.intent.extra.CC";
  public static final String EXTRA_CHANGED_COMPONENT_NAME = "android.intent.extra.changed_component_name";
  public static final String EXTRA_CHANGED_COMPONENT_NAME_LIST = "android.intent.extra.changed_component_name_list";
  public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
  public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
  public static final String EXTRA_CHOOSER_ADDITIONAL_CONTENT_URI = "android.intent.extra.CHOOSER_ADDITIONAL_CONTENT_URI";
  public static final String EXTRA_CHOOSER_CONTENT_TYPE_HINT = "android.intent.extra.CHOOSER_CONTENT_TYPE_HINT";
  public static final String EXTRA_CHOOSER_CUSTOM_ACTIONS = "android.intent.extra.CHOOSER_CUSTOM_ACTIONS";
  public static final String EXTRA_CHOOSER_FOCUSED_ITEM_POSITION = "android.intent.extra.CHOOSER_FOCUSED_ITEM_POSITION";
  public static final String EXTRA_CHOOSER_MODIFY_SHARE_ACTION = "android.intent.extra.CHOOSER_MODIFY_SHARE_ACTION";
  public static final String EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER = "android.intent.extra.CHOOSER_REFINEMENT_INTENT_SENDER";
  public static final String EXTRA_CHOOSER_RESULT = "android.intent.extra.CHOOSER_RESULT";
  public static final String EXTRA_CHOOSER_RESULT_INTENT_SENDER = "android.intent.extra.CHOOSER_RESULT_INTENT_SENDER";
  public static final String EXTRA_CHOOSER_TARGETS = "android.intent.extra.CHOOSER_TARGETS";
  public static final String EXTRA_CHOSEN_COMPONENT = "android.intent.extra.CHOSEN_COMPONENT";
  public static final String EXTRA_CHOSEN_COMPONENT_INTENT_SENDER = "android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER";
  public static final String EXTRA_COMPONENT_NAME = "android.intent.extra.COMPONENT_NAME";
  public static final String EXTRA_CONTENT_ANNOTATIONS = "android.intent.extra.CONTENT_ANNOTATIONS";
  public static final String EXTRA_CONTENT_QUERY = "android.intent.extra.CONTENT_QUERY";
  public static final String EXTRA_DATA_REMOVED = "android.intent.extra.DATA_REMOVED";
  public static final String EXTRA_DOCK_STATE = "android.intent.extra.DOCK_STATE";
  public static final int EXTRA_DOCK_STATE_CAR = 2;
  public static final int EXTRA_DOCK_STATE_DESK = 1;
  public static final int EXTRA_DOCK_STATE_HE_DESK = 4;
  public static final int EXTRA_DOCK_STATE_LE_DESK = 3;
  public static final int EXTRA_DOCK_STATE_UNDOCKED = 0;
  public static final String EXTRA_DONT_KILL_APP = "android.intent.extra.DONT_KILL_APP";
  public static final String EXTRA_DURATION_MILLIS = "android.intent.extra.DURATION_MILLIS";
  public static final String EXTRA_EMAIL = "android.intent.extra.EMAIL";
  public static final String EXTRA_END_TIME = "android.intent.extra.END_TIME";
  public static final String EXTRA_EXCLUDE_COMPONENTS = "android.intent.extra.EXCLUDE_COMPONENTS";
  public static final String EXTRA_FROM_STORAGE = "android.intent.extra.FROM_STORAGE";
  public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
  public static final String EXTRA_INDEX = "android.intent.extra.INDEX";
  public static final String EXTRA_INITIAL_INTENTS = "android.intent.extra.INITIAL_INTENTS";
  public static final String EXTRA_INSTALLER_PACKAGE_NAME = "android.intent.extra.INSTALLER_PACKAGE_NAME";
  public static final String EXTRA_INTENT = "android.intent.extra.INTENT";
  public static final String EXTRA_KEY_EVENT = "android.intent.extra.KEY_EVENT";
  public static final String EXTRA_LOCALE_LIST = "android.intent.extra.LOCALE_LIST";
  public static final String EXTRA_LOCAL_ONLY = "android.intent.extra.LOCAL_ONLY";
  public static final String EXTRA_LOCUS_ID = "android.intent.extra.LOCUS_ID";
  public static final String EXTRA_METADATA_TEXT = "android.intent.extra.METADATA_TEXT";
  public static final String EXTRA_MIME_TYPES = "android.intent.extra.MIME_TYPES";
  public static final String EXTRA_NOT_UNKNOWN_SOURCE = "android.intent.extra.NOT_UNKNOWN_SOURCE";
  public static final String EXTRA_ORIGINATING_URI = "android.intent.extra.ORIGINATING_URI";
  public static final String EXTRA_PACKAGES = "android.intent.extra.PACKAGES";
  public static final String EXTRA_PACKAGE_NAME = "android.intent.extra.PACKAGE_NAME";
  public static final String EXTRA_PERMISSION_GROUP_NAME = "android.intent.extra.PERMISSION_GROUP_NAME";
  public static final String EXTRA_PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";
  public static final String EXTRA_PROCESS_TEXT = "android.intent.extra.PROCESS_TEXT";
  public static final String EXTRA_PROCESS_TEXT_READONLY = "android.intent.extra.PROCESS_TEXT_READONLY";
  public static final String EXTRA_QUICK_VIEW_FEATURES = "android.intent.extra.QUICK_VIEW_FEATURES";
  public static final String EXTRA_QUIET_MODE = "android.intent.extra.QUIET_MODE";
  public static final String EXTRA_REFERRER = "android.intent.extra.REFERRER";
  public static final String EXTRA_REFERRER_NAME = "android.intent.extra.REFERRER_NAME";
  public static final String EXTRA_REMOTE_INTENT_TOKEN = "android.intent.extra.remote_intent_token";
  public static final String EXTRA_REPLACEMENT_EXTRAS = "android.intent.extra.REPLACEMENT_EXTRAS";
  public static final String EXTRA_REPLACING = "android.intent.extra.REPLACING";
  public static final String EXTRA_RESTRICTIONS_BUNDLE = "android.intent.extra.restrictions_bundle";
  public static final String EXTRA_RESTRICTIONS_INTENT = "android.intent.extra.restrictions_intent";
  public static final String EXTRA_RESTRICTIONS_LIST = "android.intent.extra.restrictions_list";
  public static final String EXTRA_RESULT_RECEIVER = "android.intent.extra.RESULT_RECEIVER";
  public static final String EXTRA_RETURN_RESULT = "android.intent.extra.RETURN_RESULT";
  public static final String EXTRA_SHORTCUT_ICON = "android.intent.extra.shortcut.ICON";
  public static final String EXTRA_SHORTCUT_ICON_RESOURCE = "android.intent.extra.shortcut.ICON_RESOURCE";
  public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
  public static final String EXTRA_SHORTCUT_INTENT = "android.intent.extra.shortcut.INTENT";
  public static final String EXTRA_SHORTCUT_NAME = "android.intent.extra.shortcut.NAME";
  public static final String EXTRA_SHUTDOWN_USERSPACE_ONLY = "android.intent.extra.SHUTDOWN_USERSPACE_ONLY";
  public static final String EXTRA_SPLIT_NAME = "android.intent.extra.SPLIT_NAME";
  public static final String EXTRA_START_TIME = "android.intent.extra.START_TIME";
  public static final String EXTRA_STREAM = "android.intent.extra.STREAM";
  public static final String EXTRA_SUBJECT = "android.intent.extra.SUBJECT";
  public static final String EXTRA_SUSPENDED_PACKAGE_EXTRAS = "android.intent.extra.SUSPENDED_PACKAGE_EXTRAS";
  public static final String EXTRA_TEMPLATE = "android.intent.extra.TEMPLATE";
  public static final String EXTRA_TEXT = "android.intent.extra.TEXT";
  public static final String EXTRA_TIME = "android.intent.extra.TIME";
  public static final String EXTRA_TIMEZONE = "time-zone";
  public static final String EXTRA_TITLE = "android.intent.extra.TITLE";
  public static final String EXTRA_UID = "android.intent.extra.UID";
  public static final String EXTRA_USER = "android.intent.extra.USER";
  public static final String EXTRA_USER_INITIATED = "android.intent.extra.USER_INITIATED";
  public static final String EXTRA_USE_STYLUS_MODE = "android.intent.extra.USE_STYLUS_MODE";
  public static final int FILL_IN_ACTION = 1;
  public static final int FILL_IN_CATEGORIES = 4;
  public static final int FILL_IN_CLIP_DATA = 128;
  public static final int FILL_IN_COMPONENT = 8;
  public static final int FILL_IN_DATA = 2;
  public static final int FILL_IN_IDENTIFIER = 256;
  public static final int FILL_IN_PACKAGE = 16;
  public static final int FILL_IN_SELECTOR = 64;
  public static final int FILL_IN_SOURCE_BOUNDS = 32;
  public static final int FLAG_ACTIVITY_BROUGHT_TO_FRONT = 4194304;
  public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
  public static final int FLAG_ACTIVITY_CLEAR_TOP = 67108864;
  public static final int FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET = 524288;
  public static final int FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS = 8388608;
  public static final int FLAG_ACTIVITY_FORWARD_RESULT = 33554432;
  public static final int FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY = 1048576;
  public static final int FLAG_ACTIVITY_LAUNCH_ADJACENT = 4096;
  public static final int FLAG_ACTIVITY_MATCH_EXTERNAL = 2048;
  public static final int FLAG_ACTIVITY_MULTIPLE_TASK = 134217728;
  public static final int FLAG_ACTIVITY_NEW_DOCUMENT = 524288;
  public static final int FLAG_ACTIVITY_NEW_TASK = 268435456;
  public static final int FLAG_ACTIVITY_NO_ANIMATION = 65536;
  public static final int FLAG_ACTIVITY_NO_HISTORY = 1073741824;
  public static final int FLAG_ACTIVITY_NO_USER_ACTION = 262144;
  public static final int FLAG_ACTIVITY_PREVIOUS_IS_TOP = 16777216;
  public static final int FLAG_ACTIVITY_REORDER_TO_FRONT = 131072;
  public static final int FLAG_ACTIVITY_REQUIRE_DEFAULT = 512;
  public static final int FLAG_ACTIVITY_REQUIRE_NON_BROWSER = 1024;
  public static final int FLAG_ACTIVITY_RESET_TASK_IF_NEEDED = 2097152;
  public static final int FLAG_ACTIVITY_RETAIN_IN_RECENTS = 8192;
  public static final int FLAG_ACTIVITY_SINGLE_TOP = 536870912;
  public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
  public static final int FLAG_DEBUG_LOG_RESOLUTION = 8;
  public static final int FLAG_DIRECT_BOOT_AUTO = 256;
  public static final int FLAG_EXCLUDE_STOPPED_PACKAGES = 16;
  public static final int FLAG_FROM_BACKGROUND = 4;
  public static final int FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 64;
  public static final int FLAG_GRANT_PREFIX_URI_PERMISSION = 128;
  public static final int FLAG_GRANT_READ_URI_PERMISSION = 1;
  public static final int FLAG_GRANT_WRITE_URI_PERMISSION = 2;
  public static final int FLAG_INCLUDE_STOPPED_PACKAGES = 32;
  public static final int FLAG_RECEIVER_FOREGROUND = 268435456;
  public static final int FLAG_RECEIVER_NO_ABORT = 134217728;
  public static final int FLAG_RECEIVER_REGISTERED_ONLY = 1073741824;
  public static final int FLAG_RECEIVER_REPLACE_PENDING = 536870912;
  public static final int FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS = 2097152;
  public static final String METADATA_DOCK_HOME = "android.dock_home";
  public static final int URI_ALLOW_UNSAFE = 4;
  public static final int URI_ANDROID_APP_SCHEME = 2;
  public static final int URI_INTENT_SCHEME = 1;

  public static class ShortcutIconResource implements Parcelable {
    public String packageName, resourceName;
    public ShortcutIconResource() {}
    public static ShortcutIconResource fromContext(Context c, int id) {
      ShortcutIconResource r = new ShortcutIconResource();
      r.packageName = c.getPackageName();
      r.resourceName = c.getResources().getResourceName(id);
      return r;
    }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel p, int f) { p.writeString(packageName); p.writeString(resourceName); }
    @Override public String toString() { return resourceName; }
    public static final Parcelable.Creator<ShortcutIconResource> CREATOR = new Parcelable.Creator<ShortcutIconResource>() {
      public ShortcutIconResource createFromParcel(Parcel p) { ShortcutIconResource r = new ShortcutIconResource(); r.packageName = p.readString(); r.resourceName = p.readString(); return r; }
      public ShortcutIconResource[] newArray(int size) { return new ShortcutIconResource[size]; }
    };
  }
  /** Wraps an intent for comparisons with filterEquals/filterHashCode. */
  public static final class FilterComparison {
    private final Intent mIntent;
    private final int mHashCode;
    public FilterComparison(Intent intent) { mIntent = intent; mHashCode = intent.filterHashCode(); }
    public Intent getIntent() { return mIntent; }
    @Override public boolean equals(Object obj) { return obj instanceof FilterComparison && mIntent.filterEquals(((FilterComparison) obj).mIntent); }
    @Override public int hashCode() { return mHashCode; }
  }

  String action, type, pkg, identifier;
  Uri data;
  ComponentName component;
  int flags;
  android.util.ArraySet<String> categories;
  Bundle extras;
  Rect sourceBounds;
  Intent selector;
  ClipData clipData;

  public Intent() {}
  public Intent(Intent o) {
    action = o.action; type = o.type; pkg = o.pkg; identifier = o.identifier; data = o.data; component = o.component; flags = o.flags;
    if (o.categories != null) categories = new android.util.ArraySet<>(o.categories);
    if (o.extras != null) extras = new Bundle(o.extras);
    if (o.sourceBounds != null) sourceBounds = new Rect(o.sourceBounds);
    if (o.selector != null) selector = new Intent(o.selector);
    clipData = o.clipData;
  }
  public Intent(String action) { this.action = action; }
  public Intent(String action, Uri uri) { this.action = action; data = uri; }
  public Intent(Context ctx, Class<?> cls) { component = new ComponentName(ctx, cls); }
  public Intent(String action, Uri uri, Context ctx, Class<?> cls) { this(ctx, cls); this.action = action; data = uri; }
  protected Intent(Parcel in) { readFromParcel(in); }

  public static Intent createChooser(Intent target, CharSequence title) { return createChooser(target, title, null); }
  public static Intent createChooser(Intent target, CharSequence title, IntentSender sender) {
    Intent i = new Intent(ACTION_CHOOSER);
    i.putExtra(EXTRA_INTENT, target);
    if (title != null) i.putExtra(EXTRA_TITLE, title);
    if (sender != null) i.putExtra(EXTRA_CHOSEN_COMPONENT_INTENT_SENDER, sender);
    int permFlags = target.getFlags() & (FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION | FLAG_GRANT_PERSISTABLE_URI_PERMISSION | FLAG_GRANT_PREFIX_URI_PERMISSION);
    if (permFlags != 0) {
      ClipData targetClip = target.getClipData();
      if (targetClip == null && target.getData() != null) {
        ClipData.Item item = new ClipData.Item(target.getData());
        String[] mimeTypes = target.getType() != null ? new String[] {target.getType()} : new String[0];
        targetClip = new ClipData(null, mimeTypes, item);
      }
      if (targetClip != null) { i.setClipData(targetClip); i.addFlags(permFlags); }
    }
    return i;
  }
  public static Intent makeMainActivity(ComponentName mainActivity) {
    Intent i = new Intent(ACTION_MAIN);
    i.setComponent(mainActivity);
    i.addCategory(CATEGORY_LAUNCHER);
    return i;
  }
  public static Intent makeMainSelectorActivity(String selectorAction, String selectorCategory) {
    Intent i = new Intent(ACTION_MAIN);
    i.addCategory(CATEGORY_LAUNCHER);
    Intent sel = new Intent();
    sel.setAction(selectorAction);
    sel.addCategory(selectorCategory);
    i.setSelector(sel);
    return i;
  }
  public static Intent makeRestartActivityTask(ComponentName mainActivity) {
    Intent i = makeMainActivity(mainActivity);
    i.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
    return i;
  }
  @Override public Object clone() { return new Intent(this); }
  public Intent cloneFilter() {
    Intent i = new Intent();
    i.action = action; i.data = data; i.type = type; i.identifier = identifier; i.pkg = pkg; i.component = component;
    if (categories != null) i.categories = new android.util.ArraySet<>(categories);
    return i;
  }

  public String getAction() { return action; }
  public Intent setAction(String a) { action = a != null ? a.intern() : null; return this; }
  public Uri getData() { return data; }
  public String getDataString() { return data == null ? null : data.toString(); }
  public Intent setData(Uri d) { data = d; type = null; return this; }
  public Intent setDataAndNormalize(Uri d) { return setData(d.normalizeScheme()); }
  public String getScheme() { return data == null ? null : data.getScheme(); }
  public String getType() { return type; }
  public Intent setType(String t) { data = null; type = t; return this; }
  public Intent setTypeAndNormalize(String t) { return setType(normalizeMimeType(t)); }
  public Intent setDataAndType(Uri d, String t) { data = d; type = t; return this; }
  public Intent setDataAndTypeAndNormalize(Uri d, String t) { return setDataAndType(d.normalizeScheme(), normalizeMimeType(t)); }
  public String getIdentifier() { return identifier; }
  public Intent setIdentifier(String id) { identifier = id; return this; }
  /** whether the flags grant read or write access to the intent's URIs (hidden API) */
  public static boolean isAccessUriMode(int modeFlags) { return (modeFlags & (FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION)) != 0; }
  public static String normalizeMimeType(String type) {
    if (type == null) return null;
    type = type.trim().toLowerCase(java.util.Locale.ROOT);
    int semicolon = type.indexOf(';');
    if (semicolon != -1) type = type.substring(0, semicolon);
    return type;
  }
  public String resolveType(Context context) { return resolveType(context.getContentResolver()); }
  public String resolveType(ContentResolver resolver) {
    if (type != null) return type;
    if (data != null && "content".equals(data.getScheme())) return resolver.getType(data);
    return null;
  }
  public String resolveTypeIfNeeded(ContentResolver resolver) {
    if (component != null && (Build_isAtLeast(29) || component != null)) return type;
    return resolveType(resolver);
  }
  private static boolean Build_isAtLeast(int level) { return android.os.Build.VERSION.SDK_INT >= level; }
  public String getPackage() { return pkg; }
  public Intent setPackage(String p) {
    if (p != null && selector != null) throw new IllegalArgumentException("Can't set package name when selector is already set");
    pkg = p;
    return this;
  }
  public ComponentName getComponent() { return component; }
  public Intent setComponent(ComponentName c) { component = c; return this; }
  public Intent setClass(Context ctx, Class<?> cls) { component = new ComponentName(ctx, cls); return this; }
  public Intent setClassName(Context ctx, String cls) { component = new ComponentName(ctx, cls); return this; }
  public Intent setClassName(String p, String cls) { component = new ComponentName(p, cls); return this; }
  public ComponentName resolveActivity(android.content.pm.PackageManager pm) {
    if (component != null) return component;
    android.content.pm.ResolveInfo info = pm.resolveActivity(this, android.content.pm.PackageManager.MATCH_DEFAULT_ONLY);
    return info != null ? new ComponentName(info.activityInfo.applicationInfo.packageName, info.activityInfo.name) : null;
  }
  public android.content.pm.ActivityInfo resolveActivityInfo(android.content.pm.PackageManager pm, int flags) {
    if (component != null) { try { return pm.getActivityInfo(component, flags); } catch (android.content.pm.PackageManager.NameNotFoundException e) { return null; } }
    android.content.pm.ResolveInfo info = pm.resolveActivity(this, android.content.pm.PackageManager.MATCH_DEFAULT_ONLY | flags);
    return info != null ? info.activityInfo : null;
  }
  public int getFlags() { return flags; }
  public Intent setFlags(int f) { flags = f; return this; }
  public Intent addFlags(int f) { flags |= f; return this; }
  public void removeFlags(int f) { flags &= ~f; }
  public Set<String> getCategories() { return categories; }
  public boolean hasCategory(String c) { return categories != null && categories.contains(c); }
  public Intent addCategory(String c) { if (categories == null) categories = new android.util.ArraySet<>(); categories.add(c.intern()); return this; }
  public void removeCategory(String c) { if (categories != null) { categories.remove(c); if (categories.isEmpty()) categories = null; } }
  public Intent getSelector() { return selector; }
  public void setSelector(Intent sel) {
    if (sel == this) throw new IllegalArgumentException("Intent being set as a selector of itself");
    if (sel != null && pkg != null) throw new IllegalArgumentException("Can't set selector when package name is already set");
    selector = sel;
  }
  public ClipData getClipData() { return clipData; }
  public void setClipData(ClipData clip) { clipData = clip; }
  public Rect getSourceBounds() { return sourceBounds; }
  public void setSourceBounds(Rect r) { sourceBounds = r != null ? new Rect(r) : null; }
  /** extras in a converted app hold no file descriptors that cross a process */
  public boolean hasFileDescriptors() { return extras != null && extras.hasFileDescriptors(); }
  public boolean isMismatchingFilter() { return false; }
  public void setExtrasClassLoader(ClassLoader loader) { if (extras != null) extras.setClassLoader(loader); }

  // ---------------------------------------------------------------- extras
  private Bundle ex() { if (extras == null) extras = new Bundle(); return extras; }
  public Bundle getExtras() { return extras == null ? null : new Bundle(extras); }
  public boolean hasExtra(String k) { return extras != null && extras.containsKey(k); }
  public Intent putExtras(Bundle b) { if (b != null) ex().putAll(b); return this; }
  public Intent putExtras(Intent i) { if (i.extras != null) ex().putAll(i.extras); return this; }
  public Intent replaceExtras(Bundle b) { extras = b == null ? null : new Bundle(b); return this; }
  public Intent replaceExtras(Intent src) { extras = src.extras == null ? null : new Bundle(src.extras); return this; }
  public void removeExtra(String k) { if (extras != null) { extras.remove(k); if (extras.size() == 0) extras = null; } }
  public Intent putExtra(String k, boolean v) { ex().putBoolean(k, v); return this; }
  public Intent putExtra(String k, byte v) { ex().putByte(k, v); return this; }
  public Intent putExtra(String k, char v) { ex().putChar(k, v); return this; }
  public Intent putExtra(String k, short v) { ex().putShort(k, v); return this; }
  public Intent putExtra(String k, int v) { ex().putInt(k, v); return this; }
  public Intent putExtra(String k, long v) { ex().putLong(k, v); return this; }
  public Intent putExtra(String k, float v) { ex().putFloat(k, v); return this; }
  public Intent putExtra(String k, double v) { ex().putDouble(k, v); return this; }
  public Intent putExtra(String k, String v) { ex().putString(k, v); return this; }
  public Intent putExtra(String k, CharSequence v) { ex().putCharSequence(k, v); return this; }
  public Intent putExtra(String k, Parcelable v) { ex().putParcelable(k, v); return this; }
  public Intent putExtra(String k, Serializable v) { ex().putSerializable(k, v); return this; }
  public Intent putExtra(String k, Bundle v) { ex().putBundle(k, v); return this; }
  public Intent putExtra(String k, boolean[] v) { ex().putBooleanArray(k, v); return this; }
  public Intent putExtra(String k, byte[] v) { ex().putByteArray(k, v); return this; }
  public Intent putExtra(String k, short[] v) { ex().putShortArray(k, v); return this; }
  public Intent putExtra(String k, char[] v) { ex().putCharArray(k, v); return this; }
  public Intent putExtra(String k, int[] v) { ex().putIntArray(k, v); return this; }
  public Intent putExtra(String k, long[] v) { ex().putLongArray(k, v); return this; }
  public Intent putExtra(String k, float[] v) { ex().putFloatArray(k, v); return this; }
  public Intent putExtra(String k, double[] v) { ex().putDoubleArray(k, v); return this; }
  public Intent putExtra(String k, String[] v) { ex().putStringArray(k, v); return this; }
  public Intent putExtra(String k, CharSequence[] v) { ex().putCharSequenceArray(k, v); return this; }
  public Intent putExtra(String k, Parcelable[] v) { ex().putParcelableArray(k, v); return this; }
  public Intent putStringArrayListExtra(String k, ArrayList<String> v) { ex().putStringArrayList(k, v); return this; }
  public Intent putIntegerArrayListExtra(String k, ArrayList<Integer> v) { ex().putIntegerArrayList(k, v); return this; }
  public Intent putCharSequenceArrayListExtra(String k, ArrayList<CharSequence> v) { ex().putCharSequenceArrayList(k, v); return this; }
  public Intent putParcelableArrayListExtra(String k, ArrayList<? extends Parcelable> v) { ex().putParcelableArrayList(k, v); return this; }
  public boolean getBooleanExtra(String k, boolean d) { return extras == null ? d : extras.getBoolean(k, d); }
  public byte getByteExtra(String k, byte d) { return extras == null ? d : extras.getByte(k, d); }
  public short getShortExtra(String k, short d) { return extras == null ? d : extras.getShort(k, d); }
  public char getCharExtra(String k, char d) { return extras == null ? d : extras.getChar(k, d); }
  public int getIntExtra(String k, int d) { return extras == null ? d : extras.getInt(k, d); }
  public long getLongExtra(String k, long d) { return extras == null ? d : extras.getLong(k, d); }
  public float getFloatExtra(String k, float d) { return extras == null ? d : extras.getFloat(k, d); }
  public double getDoubleExtra(String k, double d) { return extras == null ? d : extras.getDouble(k, d); }
  public String getStringExtra(String k) { return extras == null ? null : extras.getString(k); }
  public CharSequence getCharSequenceExtra(String k) { return extras == null ? null : extras.getCharSequence(k); }
  public <T extends Parcelable> T getParcelableExtra(String k) { return extras == null ? null : extras.getParcelable(k); }
  public <T> T getParcelableExtra(String k, Class<T> clazz) { return extras == null ? null : extras.getParcelable(k, clazz); }
  public Parcelable[] getParcelableArrayExtra(String k) { return extras == null ? null : extras.getParcelableArray(k); }
  public <T> T[] getParcelableArrayExtra(String k, Class<T> clazz) { return extras == null ? null : extras.getParcelableArray(k, clazz); }
  public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(String k) { return extras == null ? null : extras.getParcelableArrayList(k); }
  public <T> ArrayList<T> getParcelableArrayListExtra(String k, Class<? extends T> clazz) { return extras == null ? null : extras.getParcelableArrayList(k, clazz); }
  public Serializable getSerializableExtra(String k) { return extras == null ? null : extras.getSerializable(k); }
  public <T extends Serializable> T getSerializableExtra(String k, Class<T> clazz) { return extras == null ? null : extras.getSerializable(k, clazz); }
  public Bundle getBundleExtra(String k) { return extras == null ? null : extras.getBundle(k); }
  public boolean[] getBooleanArrayExtra(String k) { return extras == null ? null : extras.getBooleanArray(k); }
  public byte[] getByteArrayExtra(String k) { return extras == null ? null : extras.getByteArray(k); }
  public short[] getShortArrayExtra(String k) { return extras == null ? null : extras.getShortArray(k); }
  public char[] getCharArrayExtra(String k) { return extras == null ? null : extras.getCharArray(k); }
  public int[] getIntArrayExtra(String k) { return extras == null ? null : extras.getIntArray(k); }
  public long[] getLongArrayExtra(String k) { return extras == null ? null : extras.getLongArray(k); }
  public float[] getFloatArrayExtra(String k) { return extras == null ? null : extras.getFloatArray(k); }
  public double[] getDoubleArrayExtra(String k) { return extras == null ? null : extras.getDoubleArray(k); }
  public String[] getStringArrayExtra(String k) { return extras == null ? null : extras.getStringArray(k); }
  public CharSequence[] getCharSequenceArrayExtra(String k) { return extras == null ? null : extras.getCharSequenceArray(k); }
  public ArrayList<String> getStringArrayListExtra(String k) { return extras == null ? null : extras.getStringArrayList(k); }
  public ArrayList<Integer> getIntegerArrayListExtra(String k) { return extras == null ? null : extras.getIntegerArrayList(k); }
  public ArrayList<CharSequence> getCharSequenceArrayListExtra(String k) { return extras == null ? null : extras.getCharSequenceArrayList(k); }

  // ---------------------------------------------------------------- comparisons and fill-in
  public boolean filterEquals(Intent o) {
    return o != null && eq(action, o.action) && eq(data, o.data) && eq(type, o.type) && eq(identifier, o.identifier) && eq(pkg, o.pkg)
        && eq(component, o.component) && eq(categories, o.categories);
  }
  public int filterHashCode() {
    int code = 0;
    if (action != null) code += action.hashCode();
    if (data != null) code += data.hashCode();
    if (type != null) code += type.hashCode();
    if (identifier != null) code += identifier.hashCode();
    if (pkg != null) code += pkg.hashCode();
    if (component != null) code += component.hashCode();
    if (categories != null) code += categories.hashCode();
    return code;
  }
  static boolean eq(Object a, Object b) { return a == null ? b == null : a.equals(b); }
  /** copies what this intent lacks (or what the flags allow overriding) from another; returns what changed */
  public int fillIn(Intent o, int fl) {
    int changed = 0;
    boolean mayHaveCopiedUris = false;
    if (o.action != null && (action == null || (fl & FILL_IN_ACTION) != 0)) { action = o.action; changed |= FILL_IN_ACTION; }
    if ((o.data != null || o.type != null) && (data == null && type == null || (fl & FILL_IN_DATA) != 0)) { data = o.data; type = o.type; changed |= FILL_IN_DATA; mayHaveCopiedUris = true; }
    if (o.identifier != null && (identifier == null || (fl & FILL_IN_IDENTIFIER) != 0)) { identifier = o.identifier; changed |= FILL_IN_IDENTIFIER; }
    if (o.categories != null && (categories == null || (fl & FILL_IN_CATEGORIES) != 0)) { categories = new android.util.ArraySet<>(o.categories); changed |= FILL_IN_CATEGORIES; }
    if (o.pkg != null && (pkg == null || (fl & FILL_IN_PACKAGE) != 0) && selector == null) { pkg = o.pkg; changed |= FILL_IN_PACKAGE; }
    if (o.selector != null && (fl & FILL_IN_SELECTOR) != 0 && pkg == null) { selector = new Intent(o.selector); pkg = null; changed |= FILL_IN_SELECTOR; }
    if (o.clipData != null && (clipData == null || (fl & FILL_IN_CLIP_DATA) != 0)) { clipData = o.clipData; changed |= FILL_IN_CLIP_DATA; mayHaveCopiedUris = true; }
    if (o.component != null && (fl & FILL_IN_COMPONENT) != 0) { component = o.component; changed |= FILL_IN_COMPONENT; }
    flags |= o.flags;
    if (o.sourceBounds != null && (sourceBounds == null || (fl & FILL_IN_SOURCE_BOUNDS) != 0)) { sourceBounds = new Rect(o.sourceBounds); changed |= FILL_IN_SOURCE_BOUNDS; }
    if (extras == null) { if (o.extras != null) extras = new Bundle(o.extras); }
    else if (o.extras != null) { Bundle b = new Bundle(o.extras); b.putAll(extras); extras = b; }
    return changed;
  }

  // ---------------------------------------------------------------- URIs
  public String toURI() { return toUri(0); }
  public String toUri(int flags) {
    StringBuilder uri = new StringBuilder(128);
    if ((flags & URI_ANDROID_APP_SCHEME) != 0) {
      if (pkg == null) throw new IllegalArgumentException("Intent must include an explicit package name to build an android-app: " + this);
      uri.append("android-app://").append(pkg);
      String scheme = null;
      if (data != null) {
        scheme = data.getScheme();
        if (scheme != null) {
          uri.append('/').append(scheme);
          String authority = data.getEncodedAuthority();
          if (authority != null) {
            uri.append('/').append(authority);
            String path = data.getEncodedPath();
            if (path != null) uri.append(path);
            String queryParams = data.getEncodedQuery();
            if (queryParams != null) uri.append('?').append(queryParams);
            String fragment = data.getEncodedFragment();
            if (fragment != null) uri.append('#').append(fragment);
          }
        }
      }
      toUriFragment(uri, null, scheme == null ? ACTION_MAIN : ACTION_VIEW, pkg, flags);
      return uri.toString();
    }
    String scheme = null;
    if (data != null) {
      String d = data.toString();
      if ((flags & URI_INTENT_SCHEME) != 0) {
        int n = d.length();
        for (int i = 0; i < n; i++) {
          char c = d.charAt(i);
          if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c == '.' || c == '-' || c == '+') continue;
          if (c == ':' && i > 0) { scheme = d.substring(0, i); uri.append("intent:"); d = d.substring(i + 1); }
          break;
        }
      }
      uri.append(d);
    } else if ((flags & URI_INTENT_SCHEME) != 0) uri.append("intent:");
    toUriFragment(uri, scheme, ACTION_VIEW, null, flags);
    return uri.toString();
  }
  private void toUriFragment(StringBuilder uri, String scheme, String defAction, String defPackage, int flags) {
    StringBuilder frag = new StringBuilder(128);
    toUriInner(frag, scheme, defAction, defPackage, flags);
    if (selector != null) {
      frag.append("SEL;");
      selector.toUriInner(frag, selector.data != null ? selector.data.getScheme() : null, null, null, flags);
    }
    if (frag.length() > 0) uri.append("#Intent;").append(frag).append("end");
  }
  private void toUriInner(StringBuilder uri, String scheme, String defAction, String defPackage, int flags) {
    if (scheme != null) uri.append("scheme=").append(Uri.encode(scheme)).append(';');
    if (action != null && !action.equals(defAction)) uri.append("action=").append(Uri.encode(action)).append(';');
    if (categories != null) for (String c : categories) uri.append("category=").append(Uri.encode(c)).append(';');
    if (type != null) uri.append("type=").append(Uri.encode(type, "/")).append(';');
    if (identifier != null) uri.append("identifier=").append(Uri.encode(identifier, "/")).append(';');
    if (this.flags != 0) uri.append("launchFlags=0x").append(Integer.toHexString(this.flags)).append(';');
    if (pkg != null && !pkg.equals(defPackage)) uri.append("package=").append(Uri.encode(pkg)).append(';');
    if (component != null) uri.append("component=").append(Uri.encode(component.flattenToShortString(), "/")).append(';');
    if (sourceBounds != null) uri.append("sourceBounds=").append(Uri.encode(sourceBounds.flattenToString())).append(';');
    if (extras != null) {
      for (String key : extras.keySet()) {
        Object value = extras.get(key);
        char t = value instanceof String ? 'S' : value instanceof Boolean ? 'B' : value instanceof Byte ? 'b' : value instanceof Character ? 'c'
            : value instanceof Double ? 'd' : value instanceof Float ? 'f' : value instanceof Integer ? 'i' : value instanceof Long ? 'l' : value instanceof Short ? 's' : '\0';
        if (t != '\0') uri.append(t).append('.').append(Uri.encode(key)).append('=').append(Uri.encode(value.toString())).append(';');
      }
    }
  }
  @Deprecated public static Intent getIntent(String uri) throws java.net.URISyntaxException { return parseUri(uri, 0); }
  public static Intent parseUri(String uri, int flags) throws java.net.URISyntaxException {
    int i = 0;
    try {
      boolean androidApp = uri.startsWith("android-app:");
      if ((flags & (URI_INTENT_SCHEME | URI_ANDROID_APP_SCHEME)) != 0 && !uri.startsWith("intent:") && !androidApp) {
        Intent intent = new Intent(ACTION_VIEW);
        try { intent.setData(Uri.parse(uri)); } catch (IllegalArgumentException e) { throw new java.net.URISyntaxException(uri, e.getMessage()); }
        return intent;
      }
      i = uri.lastIndexOf("#");
      if (i == -1) { if (!androidApp) return new Intent(ACTION_VIEW, Uri.parse(uri)); }
      else if (!uri.startsWith("#Intent;", i)) { if (!androidApp) return getIntentOld(uri, flags); i = -1; }
      Intent intent = new Intent(ACTION_VIEW), baseIntent = intent;
      boolean explicitAction = false, inSelector = false;
      String scheme = null, d;
      if (i >= 0) { d = uri.substring(0, i); i += 8; } else d = uri;
      while (i >= 0 && !uri.startsWith("end", i)) {
        int eq = uri.indexOf('=', i);
        if (eq < 0) eq = i - 1;
        int semi = uri.indexOf(';', i);
        String value = eq < semi ? Uri.decode(uri.substring(eq + 1, semi)) : "";
        if (uri.startsWith("action=", i)) { intent.setAction(value); if (!inSelector) explicitAction = true; }
        else if (uri.startsWith("category=", i)) intent.addCategory(value);
        else if (uri.startsWith("type=", i)) intent.type = value;
        else if (uri.startsWith("identifier=", i)) intent.identifier = value;
        else if (uri.startsWith("launchFlags=", i)) {
          intent.flags = Integer.decode(value);
          if ((flags & URI_ALLOW_UNSAFE) == 0) intent.flags &= ~(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION | FLAG_GRANT_PERSISTABLE_URI_PERMISSION | FLAG_GRANT_PREFIX_URI_PERMISSION);
        }
        else if (uri.startsWith("package=", i)) intent.pkg = value;
        else if (uri.startsWith("component=", i)) intent.component = ComponentName.unflattenFromString(value);
        else if (uri.startsWith("scheme=", i)) { if (inSelector) intent.data = Uri.parse(value + ":"); else scheme = value; }
        else if (uri.startsWith("sourceBounds=", i)) intent.sourceBounds = Rect.unflattenFromString(value);
        else if (semi == i + 3 && uri.startsWith("SEL", i)) { intent = new Intent(); inSelector = true; }
        else {
          String key = Uri.decode(uri.substring(i + 2, eq));
          if (intent.extras == null) intent.extras = new Bundle();
          Bundle b = intent.extras;
          if (uri.startsWith("S.", i)) b.putString(key, value);
          else if (uri.startsWith("B.", i)) b.putBoolean(key, Boolean.parseBoolean(value));
          else if (uri.startsWith("b.", i)) b.putByte(key, Byte.parseByte(value));
          else if (uri.startsWith("c.", i)) b.putChar(key, value.charAt(0));
          else if (uri.startsWith("d.", i)) b.putDouble(key, Double.parseDouble(value));
          else if (uri.startsWith("f.", i)) b.putFloat(key, Float.parseFloat(value));
          else if (uri.startsWith("i.", i)) b.putInt(key, Integer.parseInt(value));
          else if (uri.startsWith("l.", i)) b.putLong(key, Long.parseLong(value));
          else if (uri.startsWith("s.", i)) b.putShort(key, Short.parseShort(value));
          else throw new java.net.URISyntaxException(uri, "unknown EXTRA type", i);
        }
        i = semi + 1;
      }
      if (inSelector) { if (baseIntent.pkg == null) baseIntent.setSelector(intent); intent = baseIntent; }
      if (d != null) {
        if (d.startsWith("intent:")) {
          d = d.substring(7);
          if (scheme != null) d = scheme + ':' + d;
        } else if (d.startsWith("android-app:")) {
          if (d.charAt(12) == '/' && d.charAt(13) == '/') {
            int end = d.indexOf('/', 14);
            if (end < 0) { intent.pkg = d.substring(14); if (!explicitAction) intent.setAction(ACTION_MAIN); d = ""; }
            else {
              String authority = null;
              intent.pkg = d.substring(14, end);
              int newEnd;
              if (end + 1 < d.length()) {
                if ((newEnd = d.indexOf('/', end + 1)) >= 0) {
                  scheme = d.substring(end + 1, newEnd);
                  end = newEnd;
                  if (end < d.length() && (newEnd = d.indexOf('/', end + 1)) >= 0) { authority = d.substring(end + 1, newEnd); end = newEnd; }
                } else scheme = d.substring(end + 1);
              }
              if (scheme == null) { if (!explicitAction) intent.setAction(ACTION_MAIN); d = ""; }
              else if (authority == null) d = scheme + ":";
              else d = scheme + "://" + authority + d.substring(end);
            }
          } else d = "";
        }
        if (d.length() > 0) {
          try { intent.data = Uri.parse(d); } catch (IllegalArgumentException e) { throw new java.net.URISyntaxException(uri, e.getMessage()); }
        }
      }
      return intent;
    } catch (IndexOutOfBoundsException e) {
      throw new java.net.URISyntaxException(uri, "illegal Intent URI format", i);
    }
  }
  /** the format before "#Intent;": "uri#action(...)categories(...)..." is read for its data only */
  public static Intent getIntentOld(String uri) throws java.net.URISyntaxException { return getIntentOld(uri, 0); }
  private static Intent getIntentOld(String uri, int flags) throws java.net.URISyntaxException {
    int i = uri.lastIndexOf('#');
    Intent intent = new Intent(ACTION_VIEW);
    if (i >= 0) {
      String rest = uri.substring(i + 1);
      int a = rest.indexOf("action(");
      if (a >= 0) { int e = rest.indexOf(')', a); if (e > a) intent.setAction(rest.substring(a + 7, e)); }
      uri = uri.substring(0, i);
    }
    intent.setData(Uri.parse(uri));
    return intent;
  }
  /** an intent from an <intent> element of a resource (attributes action, data, mimeType, targetPackage, targetClass; children categories and extras) */
  public static Intent parseIntent(android.content.res.Resources resources, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs)
      throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
    Intent intent = new Intent();
    String ns = "http://schemas.android.com/apk/res/android";
    intent.setAction(attrs.getAttributeValue(ns, "action"));
    String data = attrs.getAttributeValue(ns, "data"), mimeType = attrs.getAttributeValue(ns, "mimeType");
    intent.setDataAndType(data != null ? Uri.parse(data) : null, mimeType);
    String identifier = attrs.getAttributeValue(ns, "identifier");
    if (identifier != null) intent.setIdentifier(identifier);
    String packageName = attrs.getAttributeValue(ns, "targetPackage"), className = attrs.getAttributeValue(ns, "targetClass");
    if (packageName != null && className != null) intent.setComponent(new ComponentName(packageName, className));
    int outerDepth = parser.getDepth(), t;
    while ((t = parser.next()) != org.xmlpull.v1.XmlPullParser.END_DOCUMENT && (t != org.xmlpull.v1.XmlPullParser.END_TAG || parser.getDepth() > outerDepth)) {
      if (t == org.xmlpull.v1.XmlPullParser.END_TAG || t == org.xmlpull.v1.XmlPullParser.TEXT) continue;
      String nodeName = parser.getName();
      if (nodeName.equals("categories")) { String cat = attrs.getAttributeValue(ns, "name"); if (cat != null) intent.addCategory(cat); }
      else if (nodeName.equals("extra")) {
        if (intent.extras == null) intent.extras = new Bundle();
        resources.parseBundleExtra("extra", attrs, intent.extras);
      }
    }
    return intent;
  }

  // ---------------------------------------------------------------- parcels
  public int describeContents() { return extras != null ? extras.describeContents() : 0; }
  public void writeToParcel(Parcel out, int f) {
    out.writeString(action);
    Uri.writeToParcel(out, data);
    out.writeString(type);
    out.writeString(identifier);
    out.writeInt(flags);
    out.writeString(pkg);
    ComponentName.writeToParcel(component, out);
    if (sourceBounds != null) { out.writeInt(1); sourceBounds.writeToParcel(out, f); } else out.writeInt(0);
    if (categories != null) { out.writeInt(categories.size()); for (String c : categories) out.writeString(c); } else out.writeInt(0);
    if (selector != null) { out.writeInt(1); selector.writeToParcel(out, f); } else out.writeInt(0);
    if (clipData != null) { out.writeInt(1); clipData.writeToParcel(out, f); } else out.writeInt(0);
    out.writeInt(-2);   // content user hint: the current user
    out.writeBundle(extras);
  }
  public void readFromParcel(Parcel in) {
    setAction(in.readString());
    data = Uri.CREATOR.createFromParcel(in);
    type = in.readString();
    identifier = in.readString();
    flags = in.readInt();
    pkg = in.readString();
    component = ComponentName.readFromParcel(in);
    sourceBounds = in.readInt() != 0 ? Rect.CREATOR.createFromParcel(in) : null;
    int n = in.readInt();
    if (n > 0) { categories = new android.util.ArraySet<>(); for (int i = 0; i < n; i++) categories.add(in.readString().intern()); } else categories = null;
    selector = in.readInt() != 0 ? new Intent(in) : null;
    clipData = in.readInt() != 0 ? ClipData.CREATOR.createFromParcel(in) : null;
    in.readInt();
    extras = in.readBundle();
  }
  public static final Parcelable.Creator<Intent> CREATOR = new Parcelable.Creator<Intent>() {
    public Intent createFromParcel(Parcel in) { return new Intent(in); }
    public Intent[] newArray(int size) { return new Intent[size]; }
  };

  @Override public String toString() {
    StringBuilder b = new StringBuilder(128).append("Intent { ");
    toShortString(b, true, true, true, false);
    return b.append(" }").toString();
  }
  /** the fields as "act=... cat=[...] dat=... flg=0x... cmp=..." (hidden API) */
  public String toShortString(boolean secure, boolean comp, boolean extras, boolean clip) {
    StringBuilder b = new StringBuilder(128);
    toShortString(b, secure, comp, extras, clip);
    return b.toString();
  }
  public void toShortString(StringBuilder b, boolean secure, boolean comp, boolean extras, boolean clip) {
    boolean first = true;
    if (action != null) { b.append("act=").append(action); first = false; }
    if (categories != null) { if (!first) b.append(' '); first = false; b.append("cat=["); boolean f = true; for (String c : categories) { if (!f) b.append(','); b.append(c); f = false; } b.append(']'); }
    if (data != null) { if (!first) b.append(' '); first = false; b.append("dat=").append(secure ? data.toSafeString() : data); }
    if (type != null) { if (!first) b.append(' '); first = false; b.append("typ=").append(type); }
    if (identifier != null) { if (!first) b.append(' '); first = false; b.append("id=").append(identifier); }
    if (flags != 0) { if (!first) b.append(' '); first = false; b.append("flg=0x").append(Integer.toHexString(flags)); }
    if (pkg != null) { if (!first) b.append(' '); first = false; b.append("pkg=").append(pkg); }
    if (comp && component != null) { if (!first) b.append(' '); first = false; b.append("cmp=").append(component.flattenToShortString()); }
    if (sourceBounds != null) { if (!first) b.append(' '); first = false; b.append("bnds=").append(sourceBounds.toShortString()); }
    if (extras && this.extras != null) { if (!first) b.append(' '); first = false; b.append("(has extras)"); }
    if (selector != null) { b.append(" sel="); selector.toShortString(b, secure, comp, extras, clip); b.append('}'); }
  }
}

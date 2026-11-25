// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.telecom;

public class TelecomManager {
  public static final java.lang.String ACTION_CHANGE_DEFAULT_DIALER = "android.telecom.action.CHANGE_DEFAULT_DIALER";
  public static final java.lang.String ACTION_CHANGE_PHONE_ACCOUNTS = "android.telecom.action.CHANGE_PHONE_ACCOUNTS";
  public static final java.lang.String ACTION_CONFIGURE_PHONE_ACCOUNT = "android.telecom.action.CONFIGURE_PHONE_ACCOUNT";
  public static final java.lang.String ACTION_DEFAULT_CALL_SCREENING_APP_CHANGED = "android.telecom.action.DEFAULT_CALL_SCREENING_APP_CHANGED";
  public static final java.lang.String ACTION_DEFAULT_DIALER_CHANGED = "android.telecom.action.DEFAULT_DIALER_CHANGED";
  public static final java.lang.String ACTION_INCOMING_CALL = "android.telecom.action.INCOMING_CALL";
  public static final java.lang.String ACTION_PHONE_ACCOUNT_REGISTERED = "android.telecom.action.PHONE_ACCOUNT_REGISTERED";
  public static final java.lang.String ACTION_PHONE_ACCOUNT_UNREGISTERED = "android.telecom.action.PHONE_ACCOUNT_UNREGISTERED";
  public static final java.lang.String ACTION_POST_CALL = "android.telecom.action.POST_CALL";
  public static final java.lang.String ACTION_SHOW_CALL_ACCESSIBILITY_SETTINGS = "android.telecom.action.SHOW_CALL_ACCESSIBILITY_SETTINGS";
  public static final java.lang.String ACTION_SHOW_CALL_SETTINGS = "android.telecom.action.SHOW_CALL_SETTINGS";
  public static final java.lang.String ACTION_SHOW_MISSED_CALLS_NOTIFICATION = "android.telecom.action.SHOW_MISSED_CALLS_NOTIFICATION";
  public static final java.lang.String ACTION_SHOW_RESPOND_VIA_SMS_SETTINGS = "android.telecom.action.SHOW_RESPOND_VIA_SMS_SETTINGS";
  public static final char DTMF_CHARACTER_PAUSE = (char) 44;
  public static final char DTMF_CHARACTER_WAIT = (char) 59;
  public static final int DURATION_LONG = 3;
  public static final int DURATION_MEDIUM = 2;
  public static final int DURATION_SHORT = 1;
  public static final int DURATION_VERY_SHORT = 0;
  public static final java.lang.String EXTRA_CALL_BACK_NUMBER = "android.telecom.extra.CALL_BACK_NUMBER";
  public static final java.lang.String EXTRA_CALL_DISCONNECT_CAUSE = "android.telecom.extra.CALL_DISCONNECT_CAUSE";
  public static final java.lang.String EXTRA_CALL_DISCONNECT_MESSAGE = "android.telecom.extra.CALL_DISCONNECT_MESSAGE";
  public static final java.lang.String EXTRA_CALL_DURATION = "android.telecom.extra.CALL_DURATION";
  public static final java.lang.String EXTRA_CALL_LOG_URI = "android.telecom.extra.CALL_LOG_URI";
  public static final java.lang.String EXTRA_CALL_NETWORK_TYPE = "android.telecom.extra.CALL_NETWORK_TYPE";
  public static final java.lang.String EXTRA_CALL_SUBJECT = "android.telecom.extra.CALL_SUBJECT";
  public static final java.lang.String EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME = "android.telecom.extra.CHANGE_DEFAULT_DIALER_PACKAGE_NAME";
  public static final java.lang.String EXTRA_DEFAULT_CALL_SCREENING_APP_COMPONENT_NAME = "android.telecom.extra.DEFAULT_CALL_SCREENING_APP_COMPONENT_NAME";
  public static final java.lang.String EXTRA_DISCONNECT_CAUSE = "android.telecom.extra.DISCONNECT_CAUSE";
  public static final java.lang.String EXTRA_HANDLE = "android.telecom.extra.HANDLE";
  public static final java.lang.String EXTRA_HAS_PICTURE = "android.telecom.extra.HAS_PICTURE";
  public static final java.lang.String EXTRA_INCOMING_CALL_ADDRESS = "android.telecom.extra.INCOMING_CALL_ADDRESS";
  public static final java.lang.String EXTRA_INCOMING_CALL_EXTRAS = "android.telecom.extra.INCOMING_CALL_EXTRAS";
  public static final java.lang.String EXTRA_INCOMING_VIDEO_STATE = "android.telecom.extra.INCOMING_VIDEO_STATE";
  public static final java.lang.String EXTRA_IS_DEFAULT_CALL_SCREENING_APP = "android.telecom.extra.IS_DEFAULT_CALL_SCREENING_APP";
  public static final java.lang.String EXTRA_LOCATION = "android.telecom.extra.LOCATION";
  public static final java.lang.String EXTRA_NOTIFICATION_COUNT = "android.telecom.extra.NOTIFICATION_COUNT";
  public static final java.lang.String EXTRA_NOTIFICATION_PHONE_NUMBER = "android.telecom.extra.NOTIFICATION_PHONE_NUMBER";
  public static final java.lang.String EXTRA_OUTGOING_CALL_EXTRAS = "android.telecom.extra.OUTGOING_CALL_EXTRAS";
  public static final java.lang.String EXTRA_OUTGOING_PICTURE = "android.telecom.extra.OUTGOING_PICTURE";
  public static final java.lang.String EXTRA_PHONE_ACCOUNT_HANDLE = "android.telecom.extra.PHONE_ACCOUNT_HANDLE";
  public static final java.lang.String EXTRA_PICTURE_URI = "android.telecom.extra.PICTURE_URI";
  public static final java.lang.String EXTRA_PRIORITY = "android.telecom.extra.PRIORITY";
  public static final java.lang.String EXTRA_START_CALL_WITH_RTT = "android.telecom.extra.START_CALL_WITH_RTT";
  public static final java.lang.String EXTRA_START_CALL_WITH_SPEAKERPHONE = "android.telecom.extra.START_CALL_WITH_SPEAKERPHONE";
  public static final java.lang.String EXTRA_START_CALL_WITH_VIDEO_STATE = "android.telecom.extra.START_CALL_WITH_VIDEO_STATE";
  public static final java.lang.String EXTRA_USE_ASSISTED_DIALING = "android.telecom.extra.USE_ASSISTED_DIALING";
  public static final java.lang.String GATEWAY_ORIGINAL_ADDRESS = "android.telecom.extra.GATEWAY_ORIGINAL_ADDRESS";
  public static final java.lang.String GATEWAY_PROVIDER_PACKAGE = "android.telecom.extra.GATEWAY_PROVIDER_PACKAGE";
  public static final java.lang.String METADATA_INCLUDE_EXTERNAL_CALLS = "android.telecom.INCLUDE_EXTERNAL_CALLS";
  public static final java.lang.String METADATA_INCLUDE_SELF_MANAGED_CALLS = "android.telecom.INCLUDE_SELF_MANAGED_CALLS";
  public static final java.lang.String METADATA_IN_CALL_SERVICE_CAR_MODE_UI = "android.telecom.IN_CALL_SERVICE_CAR_MODE_UI";
  public static final java.lang.String METADATA_IN_CALL_SERVICE_RINGING = "android.telecom.IN_CALL_SERVICE_RINGING";
  public static final java.lang.String METADATA_IN_CALL_SERVICE_UI = "android.telecom.IN_CALL_SERVICE_UI";
  public static final int PRESENTATION_ALLOWED = 1;
  public static final int PRESENTATION_PAYPHONE = 4;
  public static final int PRESENTATION_RESTRICTED = 2;
  public static final int PRESENTATION_UNAVAILABLE = 5;
  public static final int PRESENTATION_UNKNOWN = 3;
  public static final int PRIORITY_NORMAL = 0;
  public static final int PRIORITY_URGENT = 1;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public TelecomManager(a2w.Hidden hidden) { }
  @a2w.Absent public void acceptHandover(android.net.Uri srcAddr, int videoState, android.telecom.PhoneAccountHandle destAcct) {  }
  @a2w.Absent public void acceptRingingCall() {  }
  @a2w.Absent public void acceptRingingCall(int videoState) {  }
  @a2w.Absent public void addCall(android.telecom.CallAttributes callAttributes, java.util.concurrent.Executor executor, android.os.OutcomeReceiver<android.telecom.CallControl, android.telecom.CallException> pendingControl, android.telecom.CallControlCallback handshakes, android.telecom.CallEventCallback events) {  }
  @a2w.Absent public void addNewIncomingCall(android.telecom.PhoneAccountHandle phoneAccount, android.os.Bundle extras) {  }
  @a2w.Absent public void addNewIncomingConference(android.telecom.PhoneAccountHandle phoneAccount, android.os.Bundle extras) {  }
  @a2w.Absent public void cancelMissedCallsNotification() {  }
  @a2w.Absent public android.content.Intent createManageBlockedNumbersIntent() { return null; }
  @a2w.Absent public boolean endCall() { return false; }
  @a2w.Absent public android.net.Uri getAdnUriForPhoneAccount(android.telecom.PhoneAccountHandle accountHandle) { return null; }
  @a2w.Absent public java.util.List<android.telecom.PhoneAccountHandle> getCallCapablePhoneAccounts() { return java.util.Collections.emptyList(); }
  @a2w.Absent public java.lang.String getDefaultDialerPackage() { return null; }
  @a2w.Absent public android.telecom.PhoneAccountHandle getDefaultOutgoingPhoneAccount(java.lang.String uriScheme) { return null; }
  @a2w.Absent public java.lang.String getLine1Number(android.telecom.PhoneAccountHandle accountHandle) { return null; }
  @a2w.Absent public java.util.List<android.telecom.PhoneAccountHandle> getOwnSelfManagedPhoneAccounts() { return java.util.Collections.emptyList(); }
  @a2w.Absent public android.telecom.PhoneAccount getPhoneAccount(android.telecom.PhoneAccountHandle account) { return null; }
  @a2w.Absent public java.util.List<android.telecom.PhoneAccount> getRegisteredPhoneAccounts() { return java.util.Collections.emptyList(); }
  @a2w.Absent public java.util.List<android.telecom.PhoneAccountHandle> getSelfManagedPhoneAccounts() { return java.util.Collections.emptyList(); }
  @a2w.Absent public android.telecom.PhoneAccountHandle getSimCallManager() { return null; }
  @a2w.Absent public android.telecom.PhoneAccountHandle getSimCallManagerForSubscription(int subscriptionId) { return null; }
  @a2w.Absent public java.lang.String getSystemDialerPackage() { return null; }
  @a2w.Absent public android.telecom.PhoneAccountHandle getUserSelectedOutgoingPhoneAccount() { return null; }
  @a2w.Absent public java.lang.String getVoiceMailNumber(android.telecom.PhoneAccountHandle accountHandle) { return null; }
  @a2w.Absent public boolean handleMmi(java.lang.String dialString) { return false; }
  @a2w.Absent public boolean handleMmi(java.lang.String dialString, android.telecom.PhoneAccountHandle accountHandle) { return false; }
  @a2w.Absent public boolean hasManageOngoingCallsPermission() { return false; }
  @a2w.Absent public boolean isInCall() { return false; }
  @a2w.Absent public boolean isInManagedCall() { return false; }
  @a2w.Absent public boolean isIncomingCallPermitted(android.telecom.PhoneAccountHandle phoneAccountHandle) { return false; }
  @a2w.Absent public boolean isOutgoingCallPermitted(android.telecom.PhoneAccountHandle phoneAccountHandle) { return false; }
  @a2w.Absent public boolean isTtySupported() { return false; }
  @a2w.Absent public boolean isVoiceMailNumber(android.telecom.PhoneAccountHandle accountHandle, java.lang.String number) { return false; }
  @a2w.Absent public void placeCall(android.net.Uri address, android.os.Bundle extras) {  }
  @a2w.Absent public void registerPhoneAccount(android.telecom.PhoneAccount account) {  }
  @a2w.Absent public void showInCallScreen(boolean showDialpad) {  }
  @a2w.Absent public void silenceRinger() {  }
  @a2w.Absent public void startConference(java.util.List<android.net.Uri> participants, android.os.Bundle extras) {  }
  @a2w.Absent public void unregisterPhoneAccount(android.telecom.PhoneAccountHandle accountHandle) {  }
}

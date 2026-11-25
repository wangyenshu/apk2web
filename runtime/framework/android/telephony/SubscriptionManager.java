// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.telephony;

public class SubscriptionManager {
  public static final java.lang.String ACTION_DEFAULT_SMS_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SMS_SUBSCRIPTION_CHANGED";
  public static final java.lang.String ACTION_DEFAULT_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SUBSCRIPTION_CHANGED";
  public static final java.lang.String ACTION_MANAGE_SUBSCRIPTION_PLANS = "android.telephony.action.MANAGE_SUBSCRIPTION_PLANS";
  public static final java.lang.String ACTION_REFRESH_SUBSCRIPTION_PLANS = "android.telephony.action.REFRESH_SUBSCRIPTION_PLANS";
  public static final int D2D_SHARING_ALL = 3;
  public static final int D2D_SHARING_ALL_CONTACTS = 1;
  public static final int D2D_SHARING_DISABLED = 0;
  public static final int D2D_SHARING_SELECTED_CONTACTS = 2;
  public static final java.lang.String D2D_STATUS_SHARING = "d2d_sharing_status";
  public static final java.lang.String D2D_STATUS_SHARING_SELECTED_CONTACTS = "d2d_sharing_contacts";
  public static final int DATA_ROAMING_DISABLE = 0;
  public static final int DATA_ROAMING_ENABLE = 1;
  public static final int DEFAULT_SUBSCRIPTION_ID = 2147483647;
  public static final java.lang.String EXTRA_SLOT_INDEX = "android.telephony.extra.SLOT_INDEX";
  public static final java.lang.String EXTRA_SUBSCRIPTION_INDEX = "android.telephony.extra.SUBSCRIPTION_INDEX";
  public static final int INVALID_SIM_SLOT_INDEX = -1;
  public static final int INVALID_SUBSCRIPTION_ID = -1;
  public static final int PHONE_NUMBER_SOURCE_CARRIER = 2;
  public static final int PHONE_NUMBER_SOURCE_IMS = 3;
  public static final int PHONE_NUMBER_SOURCE_UICC = 1;
  public static final int SERVICE_CAPABILITY_DATA = 3;
  public static final int SERVICE_CAPABILITY_SMS = 2;
  public static final int SERVICE_CAPABILITY_VOICE = 1;
  public static final int SUBSCRIPTION_TYPE_LOCAL_SIM = 0;
  public static final int SUBSCRIPTION_TYPE_REMOTE_SIM = 1;
  public static final int USAGE_SETTING_DATA_CENTRIC = 2;
  public static final int USAGE_SETTING_DEFAULT = 0;
  public static final int USAGE_SETTING_UNKNOWN = -1;
  public static final int USAGE_SETTING_VOICE_CENTRIC = 1;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public SubscriptionManager(a2w.Hidden hidden) { }
  @a2w.Absent public void addOnOpportunisticSubscriptionsChangedListener(java.util.concurrent.Executor executor, android.telephony.SubscriptionManager.OnOpportunisticSubscriptionsChangedListener listener) {  }
  @a2w.Absent public void addOnSubscriptionsChangedListener(android.telephony.SubscriptionManager.OnSubscriptionsChangedListener listener) {  }
  @a2w.Absent public void addOnSubscriptionsChangedListener(java.util.concurrent.Executor executor, android.telephony.SubscriptionManager.OnSubscriptionsChangedListener listener) {  }
  @a2w.Absent public void addSubscriptionsIntoGroup(java.util.List<java.lang.Integer> subIdList, android.os.ParcelUuid groupUuid) {  }
  @a2w.Absent public boolean canManageSubscription(android.telephony.SubscriptionInfo info) { return false; }
  @a2w.Absent public android.os.ParcelUuid createSubscriptionGroup(java.util.List<java.lang.Integer> subIdList) { return null; }
  @a2w.Absent public static android.telephony.SubscriptionManager from(android.content.Context context) { return null; }
  @a2w.Absent public java.util.List<android.telephony.SubscriptionInfo> getAccessibleSubscriptionInfoList() { return java.util.Collections.emptyList(); }
  @a2w.Absent public static int getActiveDataSubscriptionId() { return android.telephony.SubscriptionManager.INVALID_SUBSCRIPTION_ID; }
  @a2w.Absent public android.telephony.SubscriptionInfo getActiveSubscriptionInfo(int subId) { return null; }
  @a2w.Absent public int getActiveSubscriptionInfoCount() { return 0; }
  @a2w.Absent public int getActiveSubscriptionInfoCountMax() { return 0; }
  @a2w.Absent public android.telephony.SubscriptionInfo getActiveSubscriptionInfoForSimSlotIndex(int slotIndex) { return null; }
  @a2w.Absent public java.util.List<android.telephony.SubscriptionInfo> getActiveSubscriptionInfoList() { return java.util.Collections.emptyList(); }
  @a2w.Absent public java.util.List<android.telephony.SubscriptionInfo> getAllSubscriptionInfoList() { return java.util.Collections.emptyList(); }
  @a2w.Absent public java.util.List<android.telephony.SubscriptionInfo> getCompleteActiveSubscriptionInfoList() { return java.util.Collections.emptyList(); }
  @a2w.Absent public static int getDefaultDataSubscriptionId() { return android.telephony.SubscriptionManager.INVALID_SUBSCRIPTION_ID; }
  @a2w.Absent public static int getDefaultSmsSubscriptionId() { return android.telephony.SubscriptionManager.INVALID_SUBSCRIPTION_ID; }
  @a2w.Absent public static int getDefaultSubscriptionId() { return android.telephony.SubscriptionManager.INVALID_SUBSCRIPTION_ID; }
  @a2w.Absent public static int getDefaultVoiceSubscriptionId() { return android.telephony.SubscriptionManager.INVALID_SUBSCRIPTION_ID; }
  @a2w.Absent public java.util.List<android.net.Uri> getDeviceToDeviceStatusSharingContacts(int subscriptionId) { return java.util.Collections.emptyList(); }
  @a2w.Absent public int getDeviceToDeviceStatusSharingPreference(int subscriptionId) { return 0; }
  @a2w.Absent public java.util.List<android.telephony.SubscriptionInfo> getOpportunisticSubscriptions() { return java.util.Collections.emptyList(); }
  @a2w.Absent public java.lang.String getPhoneNumber(int subscriptionId) { return null; }
  @a2w.Absent public java.lang.String getPhoneNumber(int subscriptionId, int source) { return null; }
  @a2w.Absent public static int getSlotIndex(int subscriptionId) { return 0; }
  @a2w.Absent public static int getSubscriptionId(int slotIndex) { return 0; }
  @a2w.Absent public int[] getSubscriptionIds(int slotIndex) { return new int[0]; }
  @a2w.Absent public java.util.List<android.telephony.SubscriptionPlan> getSubscriptionPlans(int subId) { return java.util.Collections.emptyList(); }
  @a2w.Absent public java.util.List<android.telephony.SubscriptionInfo> getSubscriptionsInGroup(android.os.ParcelUuid groupUuid) { return java.util.Collections.emptyList(); }
  @a2w.Absent public boolean isActiveSubscriptionId(int subscriptionId) { return false; }
  @a2w.Absent public boolean isNetworkRoaming(int subId) { return false; }
  @a2w.Absent public static boolean isUsableSubscriptionId(int subscriptionId) { return subscriptionId >= 0; }
  @a2w.Absent public static boolean isValidSubscriptionId(int subscriptionId) { return subscriptionId >= 0; }
  @a2w.Absent public void removeOnOpportunisticSubscriptionsChangedListener(android.telephony.SubscriptionManager.OnOpportunisticSubscriptionsChangedListener listener) {  }
  @a2w.Absent public void removeOnSubscriptionsChangedListener(android.telephony.SubscriptionManager.OnSubscriptionsChangedListener listener) {  }
  @a2w.Absent public void removeSubscriptionsFromGroup(java.util.List<java.lang.Integer> subIdList, android.os.ParcelUuid groupUuid) {  }
  @a2w.Absent public void setCarrierPhoneNumber(int subscriptionId, java.lang.String number) {  }
  @a2w.Absent public void setDeviceToDeviceStatusSharingContacts(int subscriptionId, java.util.List<android.net.Uri> contacts) {  }
  @a2w.Absent public void setDeviceToDeviceStatusSharingPreference(int subscriptionId, int sharing) {  }
  @a2w.Absent public boolean setOpportunistic(boolean opportunistic, int subId) { return false; }
  @a2w.Absent public void setSubscriptionOverrideCongested(int subId, boolean overrideCongested, long expirationDurationMillis) {  }
  @a2w.Absent public void setSubscriptionOverrideCongested(int subId, boolean overrideCongested, int[] networkTypes, long expirationDurationMillis) {  }
  @a2w.Absent public void setSubscriptionOverrideUnmetered(int subId, boolean overrideUnmetered, long expirationDurationMillis) {  }
  @a2w.Absent public void setSubscriptionOverrideUnmetered(int subId, boolean overrideUnmetered, int[] networkTypes, long expirationDurationMillis) {  }
  @a2w.Absent public void setSubscriptionPlans(int subId, java.util.List<android.telephony.SubscriptionPlan> plans) {  }
  @a2w.Absent public void setSubscriptionPlans(int subId, java.util.List<android.telephony.SubscriptionPlan> plans, long expirationDurationMillis) {  }
  @a2w.Absent public void switchToSubscription(int subId, android.app.PendingIntent callbackIntent) {  }

    public static class OnOpportunisticSubscriptionsChangedListener {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public OnOpportunisticSubscriptionsChangedListener(a2w.Hidden hidden) { }
    public OnOpportunisticSubscriptionsChangedListener() { this((a2w.Hidden) null); }
    public void onOpportunisticSubscriptionsChanged() { }
  }

    public static class OnSubscriptionsChangedListener {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public OnSubscriptionsChangedListener(a2w.Hidden hidden) { }
    public OnSubscriptionsChangedListener() { this((a2w.Hidden) null); }
    public void onSubscriptionsChanged() { }
  }
}

// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.accounts;

public class AccountManager {
  public static final java.lang.String ACTION_ACCOUNT_REMOVED = "android.accounts.action.ACCOUNT_REMOVED";
  public static final java.lang.String ACTION_AUTHENTICATOR_INTENT = "android.accounts.AccountAuthenticator";
  public static final java.lang.String AUTHENTICATOR_ATTRIBUTES_NAME = "account-authenticator";
  public static final java.lang.String AUTHENTICATOR_META_DATA_NAME = "android.accounts.AccountAuthenticator";
  public static final int ERROR_CODE_BAD_ARGUMENTS = 7;
  public static final int ERROR_CODE_BAD_AUTHENTICATION = 9;
  public static final int ERROR_CODE_BAD_REQUEST = 8;
  public static final int ERROR_CODE_CANCELED = 4;
  public static final int ERROR_CODE_INVALID_RESPONSE = 5;
  public static final int ERROR_CODE_NETWORK_ERROR = 3;
  public static final int ERROR_CODE_REMOTE_EXCEPTION = 1;
  public static final int ERROR_CODE_UNSUPPORTED_OPERATION = 6;
  public static final java.lang.String KEY_ACCOUNTS = "accounts";
  public static final java.lang.String KEY_ACCOUNT_AUTHENTICATOR_RESPONSE = "accountAuthenticatorResponse";
  public static final java.lang.String KEY_ACCOUNT_MANAGER_RESPONSE = "accountManagerResponse";
  public static final java.lang.String KEY_ACCOUNT_NAME = "authAccount";
  public static final java.lang.String KEY_ACCOUNT_SESSION_BUNDLE = "accountSessionBundle";
  public static final java.lang.String KEY_ACCOUNT_STATUS_TOKEN = "accountStatusToken";
  public static final java.lang.String KEY_ACCOUNT_TYPE = "accountType";
  public static final java.lang.String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
  public static final java.lang.String KEY_AUTHENTICATOR_TYPES = "authenticator_types";
  public static final java.lang.String KEY_AUTHTOKEN = "authtoken";
  public static final java.lang.String KEY_AUTH_FAILED_MESSAGE = "authFailedMessage";
  public static final java.lang.String KEY_AUTH_TOKEN_LABEL = "authTokenLabelKey";
  public static final java.lang.String KEY_BOOLEAN_RESULT = "booleanResult";
  public static final java.lang.String KEY_CALLER_PID = "callerPid";
  public static final java.lang.String KEY_CALLER_UID = "callerUid";
  public static final java.lang.String KEY_ERROR_CODE = "errorCode";
  public static final java.lang.String KEY_ERROR_MESSAGE = "errorMessage";
  public static final java.lang.String KEY_INTENT = "intent";
  public static final java.lang.String KEY_LAST_AUTHENTICATED_TIME = "lastAuthenticatedTime";
  public static final java.lang.String KEY_PASSWORD = "password";
  public static final java.lang.String KEY_USERDATA = "userdata";
  public static final java.lang.String LOGIN_ACCOUNTS_CHANGED_ACTION = "android.accounts.LOGIN_ACCOUNTS_CHANGED";
  public static final java.lang.String PACKAGE_NAME_KEY_LEGACY_NOT_VISIBLE = "android:accounts:key_legacy_not_visible";
  public static final java.lang.String PACKAGE_NAME_KEY_LEGACY_VISIBLE = "android:accounts:key_legacy_visible";
  public static final int VISIBILITY_NOT_VISIBLE = 3;
  public static final int VISIBILITY_UNDEFINED = 0;
  public static final int VISIBILITY_USER_MANAGED_NOT_VISIBLE = 4;
  public static final int VISIBILITY_USER_MANAGED_VISIBLE = 2;
  public static final int VISIBILITY_VISIBLE = 1;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public AccountManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> addAccount(java.lang.String accountType, java.lang.String authTokenType, java.lang.String[] requiredFeatures, android.os.Bundle addAccountOptions, android.app.Activity activity, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.addAccount"); return null; }
  @a2w.NotImplemented public boolean addAccountExplicitly(android.accounts.Account account, java.lang.String password, android.os.Bundle userdata) { a2w.Stubs.hit("android.accounts.AccountManager.addAccountExplicitly"); return false; }
  @a2w.NotImplemented public boolean addAccountExplicitly(android.accounts.Account account, java.lang.String password, android.os.Bundle extras, java.util.Map<java.lang.String, java.lang.Integer> visibility) { a2w.Stubs.hit("android.accounts.AccountManager.addAccountExplicitly"); return false; }
  @a2w.NotImplemented public void addOnAccountsUpdatedListener(android.accounts.OnAccountsUpdateListener listener, android.os.Handler handler, boolean updateImmediately) { a2w.Stubs.hit("android.accounts.AccountManager.addOnAccountsUpdatedListener"); }
  @a2w.NotImplemented public void addOnAccountsUpdatedListener(android.accounts.OnAccountsUpdateListener listener, android.os.Handler handler, boolean updateImmediately, java.lang.String[] accountTypes) { a2w.Stubs.hit("android.accounts.AccountManager.addOnAccountsUpdatedListener"); }
  @a2w.NotImplemented public java.lang.String blockingGetAuthToken(android.accounts.Account account, java.lang.String authTokenType, boolean notifyAuthFailure) throws android.accounts.AuthenticatorException, java.io.IOException, android.accounts.OperationCanceledException { a2w.Stubs.hit("android.accounts.AccountManager.blockingGetAuthToken"); return null; }
  @a2w.NotImplemented public void clearPassword(android.accounts.Account account) { a2w.Stubs.hit("android.accounts.AccountManager.clearPassword"); }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> confirmCredentials(android.accounts.Account account, android.os.Bundle options, android.app.Activity activity, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.confirmCredentials"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> editProperties(java.lang.String accountType, android.app.Activity activity, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.editProperties"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> finishSession(android.os.Bundle sessionBundle, android.app.Activity activity, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.finishSession"); return null; }
  @a2w.NotImplemented public static android.accounts.AccountManager get(android.content.Context context) { a2w.Stubs.hit("android.accounts.AccountManager.get"); return null; }
  @a2w.NotImplemented public int getAccountVisibility(android.accounts.Account account, java.lang.String packageName) { a2w.Stubs.hit("android.accounts.AccountManager.getAccountVisibility"); return 0; }
  @a2w.NotImplemented public android.accounts.Account[] getAccounts() { a2w.Stubs.hit("android.accounts.AccountManager.getAccounts"); return null; }
  @a2w.NotImplemented public java.util.Map<android.accounts.Account, java.lang.Integer> getAccountsAndVisibilityForPackage(java.lang.String packageName, java.lang.String accountType) { a2w.Stubs.hit("android.accounts.AccountManager.getAccountsAndVisibilityForPackage"); return null; }
  @a2w.NotImplemented public android.accounts.Account[] getAccountsByType(java.lang.String type) { a2w.Stubs.hit("android.accounts.AccountManager.getAccountsByType"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.accounts.Account[]> getAccountsByTypeAndFeatures(java.lang.String type, java.lang.String[] features, android.accounts.AccountManagerCallback<android.accounts.Account[]> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.getAccountsByTypeAndFeatures"); return null; }
  @a2w.NotImplemented public android.accounts.Account[] getAccountsByTypeForPackage(java.lang.String type, java.lang.String packageName) { a2w.Stubs.hit("android.accounts.AccountManager.getAccountsByTypeForPackage"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> getAuthToken(android.accounts.Account account, java.lang.String authTokenType, android.os.Bundle options, android.app.Activity activity, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.getAuthToken"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> getAuthToken(android.accounts.Account account, java.lang.String authTokenType, android.os.Bundle options, boolean notifyAuthFailure, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.getAuthToken"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> getAuthToken(android.accounts.Account account, java.lang.String authTokenType, boolean notifyAuthFailure, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.getAuthToken"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> getAuthTokenByFeatures(java.lang.String accountType, java.lang.String authTokenType, java.lang.String[] features, android.app.Activity activity, android.os.Bundle addAccountOptions, android.os.Bundle getAuthTokenOptions, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.getAuthTokenByFeatures"); return null; }
  @a2w.NotImplemented public android.accounts.AuthenticatorDescription[] getAuthenticatorTypes() { a2w.Stubs.hit("android.accounts.AccountManager.getAuthenticatorTypes"); return null; }
  @a2w.NotImplemented public java.util.Map<java.lang.String, java.lang.Integer> getPackagesAndVisibilityForAccount(android.accounts.Account account) { a2w.Stubs.hit("android.accounts.AccountManager.getPackagesAndVisibilityForAccount"); return null; }
  @a2w.NotImplemented public java.lang.String getPassword(android.accounts.Account account) { a2w.Stubs.hit("android.accounts.AccountManager.getPassword"); return null; }
  @a2w.NotImplemented public java.lang.String getPreviousName(android.accounts.Account account) { a2w.Stubs.hit("android.accounts.AccountManager.getPreviousName"); return null; }
  @a2w.NotImplemented public java.lang.String getUserData(android.accounts.Account account, java.lang.String key) { a2w.Stubs.hit("android.accounts.AccountManager.getUserData"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<java.lang.Boolean> hasFeatures(android.accounts.Account account, java.lang.String[] features, android.accounts.AccountManagerCallback<java.lang.Boolean> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.hasFeatures"); return null; }
  @a2w.NotImplemented public void invalidateAuthToken(java.lang.String accountType, java.lang.String authToken) { a2w.Stubs.hit("android.accounts.AccountManager.invalidateAuthToken"); }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<java.lang.Boolean> isCredentialsUpdateSuggested(android.accounts.Account account, java.lang.String statusToken, android.accounts.AccountManagerCallback<java.lang.Boolean> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.isCredentialsUpdateSuggested"); return null; }
  @a2w.NotImplemented public static android.content.Intent newChooseAccountIntent(android.accounts.Account selectedAccount, java.util.ArrayList<android.accounts.Account> allowableAccounts, java.lang.String[] allowableAccountTypes, boolean alwaysPromptForAccount, java.lang.String descriptionOverrideText, java.lang.String addAccountAuthTokenType, java.lang.String[] addAccountRequiredFeatures, android.os.Bundle addAccountOptions) { a2w.Stubs.hit("android.accounts.AccountManager.newChooseAccountIntent"); return null; }
  @a2w.NotImplemented public static android.content.Intent newChooseAccountIntent(android.accounts.Account selectedAccount, java.util.List<android.accounts.Account> allowableAccounts, java.lang.String[] allowableAccountTypes, java.lang.String descriptionOverrideText, java.lang.String addAccountAuthTokenType, java.lang.String[] addAccountRequiredFeatures, android.os.Bundle addAccountOptions) { a2w.Stubs.hit("android.accounts.AccountManager.newChooseAccountIntent"); return null; }
  @a2w.NotImplemented public boolean notifyAccountAuthenticated(android.accounts.Account account) { a2w.Stubs.hit("android.accounts.AccountManager.notifyAccountAuthenticated"); return false; }
  @a2w.NotImplemented public java.lang.String peekAuthToken(android.accounts.Account account, java.lang.String authTokenType) { a2w.Stubs.hit("android.accounts.AccountManager.peekAuthToken"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<java.lang.Boolean> removeAccount(android.accounts.Account account, android.accounts.AccountManagerCallback<java.lang.Boolean> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.removeAccount"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> removeAccount(android.accounts.Account account, android.app.Activity activity, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.removeAccount"); return null; }
  @a2w.NotImplemented public boolean removeAccountExplicitly(android.accounts.Account account) { a2w.Stubs.hit("android.accounts.AccountManager.removeAccountExplicitly"); return false; }
  @a2w.NotImplemented public void removeOnAccountsUpdatedListener(android.accounts.OnAccountsUpdateListener listener) { a2w.Stubs.hit("android.accounts.AccountManager.removeOnAccountsUpdatedListener"); }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.accounts.Account> renameAccount(android.accounts.Account account, java.lang.String newName, android.accounts.AccountManagerCallback<android.accounts.Account> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.renameAccount"); return null; }
  @a2w.NotImplemented public boolean setAccountVisibility(android.accounts.Account account, java.lang.String packageName, int visibility) { a2w.Stubs.hit("android.accounts.AccountManager.setAccountVisibility"); return false; }
  @a2w.NotImplemented public void setAuthToken(android.accounts.Account account, java.lang.String authTokenType, java.lang.String authToken) { a2w.Stubs.hit("android.accounts.AccountManager.setAuthToken"); }
  @a2w.NotImplemented public void setPassword(android.accounts.Account account, java.lang.String password) { a2w.Stubs.hit("android.accounts.AccountManager.setPassword"); }
  @a2w.NotImplemented public void setUserData(android.accounts.Account account, java.lang.String key, java.lang.String value) { a2w.Stubs.hit("android.accounts.AccountManager.setUserData"); }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> startAddAccountSession(java.lang.String accountType, java.lang.String authTokenType, java.lang.String[] requiredFeatures, android.os.Bundle options, android.app.Activity activity, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.startAddAccountSession"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> startUpdateCredentialsSession(android.accounts.Account account, java.lang.String authTokenType, android.os.Bundle options, android.app.Activity activity, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.startUpdateCredentialsSession"); return null; }
  @a2w.NotImplemented public android.accounts.AccountManagerFuture<android.os.Bundle> updateCredentials(android.accounts.Account account, java.lang.String authTokenType, android.os.Bundle options, android.app.Activity activity, android.accounts.AccountManagerCallback<android.os.Bundle> callback, android.os.Handler handler) { a2w.Stubs.hit("android.accounts.AccountManager.updateCredentials"); return null; }
}

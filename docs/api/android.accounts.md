# android.accounts

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractAccountAuthenticator — declaration; API 5+ (7 +, 0 *, 0 ~, 9 -)

```
- AbstractAccountAuthenticator(Context)  [API 5+]
+ addAccount(AccountAuthenticatorResponse, String, String, String[], Bundle): Bundle  [API 5+]
- addAccountFromCredentials(AccountAuthenticatorResponse, Account, Bundle): Bundle  [API 18+]
+ confirmCredentials(AccountAuthenticatorResponse, Account, Bundle): Bundle  [API 5+]
+ editProperties(AccountAuthenticatorResponse, String): Bundle  [API 5+]
- finishSession(AccountAuthenticatorResponse, String, Bundle): Bundle  [API 26+]
- getAccountCredentialsForCloning(AccountAuthenticatorResponse, Account): Bundle  [API 18+]
- getAccountRemovalAllowed(AccountAuthenticatorResponse, Account): Bundle  [API 5+]
+ getAuthToken(AccountAuthenticatorResponse, Account, String, Bundle): Bundle  [API 5+]
+ getAuthTokenLabel(String): String  [API 5+]
- getIBinder(): IBinder  [API 5+]
+ hasFeatures(AccountAuthenticatorResponse, Account, String[]): Bundle  [API 5+]
- isCredentialsUpdateSuggested(AccountAuthenticatorResponse, Account, String): Bundle  [API 26+]
- startAddAccountSession(AccountAuthenticatorResponse, String, String, String[], Bundle): Bundle  [API 26+]
- startUpdateCredentialsSession(AccountAuthenticatorResponse, Account, String, Bundle): Bundle  [API 26+]
+ updateCredentials(AccountAuthenticatorResponse, Account, String, Bundle): Bundle  [API 5+]
```

## Account — framework; API 5+ (4 +, 0 *, 2 ~, 1 -)

```
- Account(Parcel)  [API 5+]
+ Account(String, String)  [API 5+]
- CREATOR [field]  [API 5+]
~ describeContents(): int  [API 5+]
+ equals(Object): boolean  [API 5+]
+ hashCode(): int  [API 5+]
+ name [field]  [API 5+]
+ toString(): String  [API 5+]
+ type [field]  [API 5+]
~ writeToParcel(Parcel, int): void  [API 5+]
```

## AccountAuthenticatorActivity — declaration; API 5+, deprecated in 30 (0 +, 0 *, 0 ~, 4 -)

```
- AccountAuthenticatorActivity()  [API 5+]
- finish(): void  [API 5+]
- onCreate(Bundle): void  [API 5+]
- setAccountAuthenticatorResult(Bundle): void  [API 5+]
```

## AccountAuthenticatorResponse — declaration; API 5+ (0 +, 0 *, 0 ~, 6 -)

```
- AccountAuthenticatorResponse(Parcel)  [API 5+]
- CREATOR [field]  [API 5+]
- describeContents(): int  [API 5+]
- onError(int, String): void  [API 5+]
- onRequestContinued(): void  [API 5+]
- onResult(Bundle): void  [API 5+]
- writeToParcel(Parcel, int): void  [API 5+]
```

## AccountManager — framework; API 5+ (0 +, 0 *, 0 ~, 45 -)

```
- addAccount(String, String, String[], Bundle, Activity, AccountManagerCallback, Handler): AccountManagerFuture  [API 5+]
- addAccountExplicitly(Account, String, Bundle): boolean  [API 5+]
- addAccountExplicitly(Account, String, Bundle, Map): boolean  [API 26+]
- addOnAccountsUpdatedListener(OnAccountsUpdateListener, Handler, boolean): void  [API 5+]
- addOnAccountsUpdatedListener(OnAccountsUpdateListener, Handler, boolean, String[]): void  [API 26+]
- blockingGetAuthToken(Account, String, boolean): String  [API 5+]
- clearPassword(Account): void  [API 5+]
- confirmCredentials(Account, Bundle, Activity, AccountManagerCallback, Handler): AccountManagerFuture  [API 5+]
- editProperties(String, Activity, AccountManagerCallback, Handler): AccountManagerFuture  [API 5+]
- finishSession(Bundle, Activity, AccountManagerCallback, Handler): AccountManagerFuture  [API 26+]
- get(Context): AccountManager [static]  [API 5+]
- getAccountVisibility(Account, String): int  [API 26+]
- getAccounts(): Account[]  [API 5+]
- getAccountsAndVisibilityForPackage(String, String): Map  [API 26+]
- getAccountsByType(String): Account[]  [API 5+]
- getAccountsByTypeAndFeatures(String, String[], AccountManagerCallback, Handler): AccountManagerFuture  [API 5+]
- getAccountsByTypeForPackage(String, String): Account[]  [API 18+]
- getAuthToken(Account, String, Bundle, Activity, AccountManagerCallback, Handler): AccountManagerFuture  [API 5+]
- getAuthToken(Account, String, Bundle, boolean, AccountManagerCallback, Handler): AccountManagerFuture  [API 14+]
- getAuthToken(Account, String, boolean, AccountManagerCallback, Handler): AccountManagerFuture  [API 5+, deprecated in 15]
- getAuthTokenByFeatures(String, String, String[], Activity, Bundle, Bundle, AccountManagerCallback, Handler): AccountManagerFuture  [API 5+]
- getAuthenticatorTypes(): AuthenticatorDescription[]  [API 5+]
- getPackagesAndVisibilityForAccount(Account): Map  [API 26+]
- getPassword(Account): String  [API 5+]
- getPreviousName(Account): String  [API 21+]
- getUserData(Account, String): String  [API 5+]
- hasFeatures(Account, String[], AccountManagerCallback, Handler): AccountManagerFuture  [API 8+]
- invalidateAuthToken(String, String): void  [API 5+]
- isCredentialsUpdateSuggested(Account, String, AccountManagerCallback, Handler): AccountManagerFuture  [API 26+]
- newChooseAccountIntent(Account, ArrayList, String[], boolean, String, String, String[], Bundle): Intent [static]  [API 14+, deprecated in 23]
- newChooseAccountIntent(Account, List, String[], String, String, String[], Bundle): Intent [static]  [API 23+]
- notifyAccountAuthenticated(Account): boolean  [API 23+]
- peekAuthToken(Account, String): String  [API 5+]
- removeAccount(Account, AccountManagerCallback, Handler): AccountManagerFuture  [API 5+, deprecated in 22]
- removeAccount(Account, Activity, AccountManagerCallback, Handler): AccountManagerFuture  [API 22+]
- removeAccountExplicitly(Account): boolean  [API 22+]
- removeOnAccountsUpdatedListener(OnAccountsUpdateListener): void  [API 5+]
- renameAccount(Account, String, AccountManagerCallback, Handler): AccountManagerFuture  [API 21+]
- setAccountVisibility(Account, String, int): boolean  [API 26+]
- setAuthToken(Account, String, String): void  [API 5+]
- setPassword(Account, String): void  [API 5+]
- setUserData(Account, String, String): void  [API 5+]
- startAddAccountSession(String, String, String[], Bundle, Activity, AccountManagerCallback, Handler): AccountManagerFuture  [API 26+]
- startUpdateCredentialsSession(Account, String, Bundle, Activity, AccountManagerCallback, Handler): AccountManagerFuture  [API 26+]
- updateCredentials(Account, String, Bundle, Activity, AccountManagerCallback, Handler): AccountManagerFuture  [API 5+]
```

## AccountManagerCallback — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ run(AccountManagerFuture): void  [API 5+]
```

## AccountManagerFuture — declaration; API 5+ (5 +, 0 *, 0 ~, 0 -)

```
+ cancel(boolean): boolean  [API 5+]
+ getResult(): Object  [API 5+]
+ getResult(long, TimeUnit): Object  [API 5+]
+ isCancelled(): boolean  [API 5+]
+ isDone(): boolean  [API 5+]
```

## AccountsException — declaration; API 5+ (4 +, 0 *, 0 ~, 0 -)

```
+ AccountsException()  [API 5+]
+ AccountsException(String)  [API 5+]
+ AccountsException(String, Throwable)  [API 5+]
+ AccountsException(Throwable)  [API 5+]
```

## AuthenticatorDescription — declaration; API 5+ (0 +, 0 *, 0 ~, 8 -)

```
- AuthenticatorDescription(String, String, int, int, int, int)  [API 5+]
- AuthenticatorDescription(String, String, int, int, int, int, boolean)  [API 11+]
- CREATOR [field]  [API 5+]
- accountPreferencesId [field]  [API 5+]
- customTokens [field]  [API 11+]
- describeContents(): int  [API 5+]
- equals(Object): boolean  [API 5+]
- hashCode(): int  [API 5+]
- iconId [field]  [API 5+]
- labelId [field]  [API 5+]
- newKey(String): AuthenticatorDescription [static]  [API 5+]
- packageName [field]  [API 5+]
- smallIconId [field]  [API 5+]
- toString(): String  [API 5+]
- type [field]  [API 5+]
- writeToParcel(Parcel, int): void  [API 5+]
```

## AuthenticatorException — declaration; API 5+ (4 +, 0 *, 0 ~, 0 -)

```
+ AuthenticatorException()  [API 5+]
+ AuthenticatorException(String)  [API 5+]
+ AuthenticatorException(String, Throwable)  [API 5+]
+ AuthenticatorException(Throwable)  [API 5+]
```

## NetworkErrorException — declaration; API 5+ (4 +, 0 *, 0 ~, 0 -)

```
+ NetworkErrorException()  [API 5+]
+ NetworkErrorException(String)  [API 5+]
+ NetworkErrorException(String, Throwable)  [API 5+]
+ NetworkErrorException(Throwable)  [API 5+]
```

## OnAccountsUpdateListener — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ onAccountsUpdated(Account[]): void  [API 5+]
```

## OperationCanceledException — declaration; API 5+ (4 +, 0 *, 0 ~, 0 -)

```
+ OperationCanceledException()  [API 5+]
+ OperationCanceledException(String)  [API 5+]
+ OperationCanceledException(String, Throwable)  [API 5+]
+ OperationCanceledException(Throwable)  [API 5+]
```


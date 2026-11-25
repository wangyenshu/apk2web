# javax.security.auth

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AuthPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- AuthPermission(String)  [API 1+]
- AuthPermission(String, String)  [API 1+]
```

## DestroyFailedException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ DestroyFailedException()  [API 1+]
+ DestroyFailedException(String)  [API 1+]
```

## Destroyable — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- destroy(): void  [API 1+]
- isDestroyed(): boolean  [API 1+]
```

## PrivateCredentialPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- PrivateCredentialPermission(String, String)  [API 1+]
- getActions(): String  [API 1+]
- getCredentialClass(): String  [API 1+]
- getPrincipals(): String[][]  [API 1+]
- implies(Permission): boolean  [API 1+]
```

## Subject — declaration; API 1+ (0 +, 0 *, 0 ~, 18 -)

```
- Subject()  [API 1+]
- Subject(boolean, Set, Set, Set)  [API 1+]
- doAs(Subject, PrivilegedAction): Object [static]  [API 1+]
- doAs(Subject, PrivilegedExceptionAction): Object [static]  [API 1+]
- doAsPrivileged(Subject, PrivilegedAction, AccessControlContext): Object [static]  [API 1+]
- doAsPrivileged(Subject, PrivilegedExceptionAction, AccessControlContext): Object [static]  [API 1+]
- equals(Object): boolean  [API 1+]
- getPrincipals(): Set  [API 1+]
- getPrincipals(Class): Set  [API 1+]
- getPrivateCredentials(): Set  [API 1+]
- getPrivateCredentials(Class): Set  [API 1+]
- getPublicCredentials(): Set  [API 1+]
- getPublicCredentials(Class): Set  [API 1+]
- getSubject(AccessControlContext): Subject [static]  [API 1+]
- hashCode(): int  [API 1+]
- isReadOnly(): boolean  [API 1+]
- setReadOnly(): void  [API 1+]
- toString(): String  [API 1+]
```

## SubjectDomainCombiner — declaration; API 1+ (0 +, 2 *, 0 ~, 1 -)

```
* SubjectDomainCombiner(Subject)  [API 1+]
- combine(ProtectionDomain[], ProtectionDomain[]): ProtectionDomain[]  [API 1+]
* getSubject(): Subject  [API 1+]
```


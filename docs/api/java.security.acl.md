# java.security.acl

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Acl — declaration; API 1+, deprecated in 33 (8 +, 0 *, 0 ~, 0 -)

```
+ addEntry(Principal, AclEntry): boolean  [API 1+]
+ checkPermission(Principal, Permission): boolean  [API 1+]
+ entries(): Enumeration  [API 1+]
+ getName(): String  [API 1+]
+ getPermissions(Principal): Enumeration  [API 1+]
+ removeEntry(Principal, AclEntry): boolean  [API 1+]
+ setName(Principal, String): void  [API 1+]
+ toString(): String  [API 1+]
```

## AclEntry — declaration; API 1+, deprecated in 33 (10 +, 0 *, 0 ~, 0 -)

```
+ addPermission(Permission): boolean  [API 1+]
+ checkPermission(Permission): boolean  [API 1+]
+ clone(): Object  [API 1+]
+ getPrincipal(): Principal  [API 1+]
+ isNegative(): boolean  [API 1+]
+ permissions(): Enumeration  [API 1+]
+ removePermission(Permission): boolean  [API 1+]
+ setNegativePermissions(): void  [API 1+]
+ setPrincipal(Principal): boolean  [API 1+]
+ toString(): String  [API 1+]
```

## AclNotFoundException — declaration; API 1+, deprecated in 33 (1 +, 0 *, 0 ~, 0 -)

```
+ AclNotFoundException()  [API 1+]
```

## Group — declaration; API 1+, deprecated in 33 (4 +, 0 *, 0 ~, 0 -)

```
+ addMember(Principal): boolean  [API 1+]
+ isMember(Principal): boolean  [API 1+]
+ members(): Enumeration  [API 1+]
+ removeMember(Principal): boolean  [API 1+]
```

## LastOwnerException — declaration; API 1+, deprecated in 33 (1 +, 0 *, 0 ~, 0 -)

```
+ LastOwnerException()  [API 1+]
```

## NotOwnerException — declaration; API 1+, deprecated in 33 (1 +, 0 *, 0 ~, 0 -)

```
+ NotOwnerException()  [API 1+]
```

## Owner — declaration; API 1+, deprecated in 33 (3 +, 0 *, 0 ~, 0 -)

```
+ addOwner(Principal, Principal): boolean  [API 1+]
+ deleteOwner(Principal, Principal): boolean  [API 1+]
+ isOwner(Principal): boolean  [API 1+]
```

## Permission — declaration; API 1+, deprecated in 33 (0 +, 0 *, 0 ~, 0 -)


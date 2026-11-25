# android.net.wifi.hotspot2.pps

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Credential — declaration; API 26+ (0 +, 22 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 26+]
* Credential()  [API 26+]
- Credential(Credential)  [API 26+]
* describeContents(): int  [API 26+]
* equals(Object): boolean  [API 26+]
* getCaCertificate(): X509Certificate  [API 26+]
* getCertCredential(): Credential.CertificateCredential  [API 26+]
* getClientCertificateChain(): X509Certificate[]  [API 26+]
* getClientPrivateKey(): PrivateKey  [API 26+]
* getMinimumTlsVersion(): int  [API 34+]
* getRealm(): String  [API 26+]
* getSimCredential(): Credential.SimCredential  [API 26+]
* getUserCredential(): Credential.UserCredential  [API 26+]
* hashCode(): int  [API 26+]
* setCaCertificate(X509Certificate): void  [API 26+]
* setCertCredential(Credential.CertificateCredential): void  [API 26+]
* setClientCertificateChain(X509Certificate[]): void  [API 26+]
* setClientPrivateKey(PrivateKey): void  [API 26+]
* setMinimumTlsVersion(int): void  [API 34+]
* setRealm(String): void  [API 26+]
* setSimCredential(Credential.SimCredential): void  [API 26+]
* setUserCredential(Credential.UserCredential): void  [API 26+]
* toString(): String  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## Credential.CertificateCredential — declaration; API 26+ (0 +, 10 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 26+]
* CertificateCredential()  [API 26+]
- CertificateCredential(Credential.CertificateCredential)  [API 26+]
* describeContents(): int  [API 26+]
* equals(Object): boolean  [API 26+]
* getCertSha256Fingerprint(): byte[]  [API 26+]
* getCertType(): String  [API 26+]
* hashCode(): int  [API 26+]
* setCertSha256Fingerprint(byte[]): void  [API 26+]
* setCertType(String): void  [API 26+]
* toString(): String  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## Credential.SimCredential — declaration; API 26+ (0 +, 10 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 26+]
* SimCredential()  [API 26+]
- SimCredential(Credential.SimCredential)  [API 26+]
* describeContents(): int  [API 26+]
* equals(Object): boolean  [API 26+]
* getEapType(): int  [API 26+]
* getImsi(): String  [API 26+]
* hashCode(): int  [API 26+]
* setEapType(int): void  [API 26+]
* setImsi(String): void  [API 26+]
* toString(): String  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## Credential.UserCredential — declaration; API 26+ (0 +, 14 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 26+]
* UserCredential()  [API 26+]
- UserCredential(Credential.UserCredential)  [API 26+]
* describeContents(): int  [API 26+]
* equals(Object): boolean  [API 26+]
* getEapType(): int  [API 26+]
* getNonEapInnerMethod(): String  [API 26+]
* getPassword(): String  [API 26+]
* getUsername(): String  [API 26+]
* hashCode(): int  [API 26+]
* setEapType(int): void  [API 26+]
* setNonEapInnerMethod(String): void  [API 26+]
* setPassword(String): void  [API 26+]
* setUsername(String): void  [API 26+]
* toString(): String  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## HomeSp — declaration; API 26+ (0 +, 18 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 26+]
* HomeSp()  [API 26+]
- HomeSp(HomeSp)  [API 26+]
* describeContents(): int  [API 26+]
* equals(Object): boolean  [API 26+]
* getFqdn(): String  [API 26+]
* getFriendlyName(): String  [API 26+]
* getMatchAllOis(): long[]  [API 31+]
* getMatchAnyOis(): long[]  [API 31+]
* getOtherHomePartnersList(): Collection  [API 31+]
* getRoamingConsortiumOis(): long[]  [API 26+]
* hashCode(): int  [API 26+]
* setFqdn(String): void  [API 26+]
* setFriendlyName(String): void  [API 26+]
* setMatchAllOis(long[]): void  [API 31+]
* setMatchAnyOis(long[]): void  [API 31+]
* setOtherHomePartnersList(Collection): void  [API 31+]
* setRoamingConsortiumOis(long[]): void  [API 26+]
* toString(): String  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```


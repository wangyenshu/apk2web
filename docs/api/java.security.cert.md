# java.security.cert

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CRL — declaration; API 1+ (1 +, 3 *, 0 ~, 0 -)

```
* CRL(String)  [API 1+]
* getType(): String  [API 1+]
+ isRevoked(Certificate): boolean  [API 1+]
* toString(): String  [API 1+]
```

## CRLException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CRLException()  [API 1+]
+ CRLException(String)  [API 1+]
+ CRLException(String, Throwable)  [API 1+]
+ CRLException(Throwable)  [API 1+]
```

## CRLReason — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ AA_COMPROMISE [field]  [API 24+]
+ AFFILIATION_CHANGED [field]  [API 24+]
+ CA_COMPROMISE [field]  [API 24+]
+ CERTIFICATE_HOLD [field]  [API 24+]
+ CESSATION_OF_OPERATION [field]  [API 24+]
+ KEY_COMPROMISE [field]  [API 24+]
+ PRIVILEGE_WITHDRAWN [field]  [API 24+]
+ REMOVE_FROM_CRL [field]  [API 24+]
+ SUPERSEDED [field]  [API 24+]
+ UNSPECIFIED [field]  [API 24+]
+ UNUSED [field]  [API 24+]
+ valueOf(String): CRLReason [static]  [API 24+]
+ values(): CRLReason[] [static]  [API 24+]
```

## CRLSelector — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ clone(): Object  [API 1+]
+ match(CRL): boolean  [API 1+]
```

## CertPath — declaration; API 1+ (4 +, 5 *, 0 ~, 1 -)

```
* CertPath(String)  [API 1+]
* equals(Object): boolean  [API 1+]
+ getCertificates(): List  [API 1+]
+ getEncoded(): byte[]  [API 1+]
+ getEncoded(String): byte[]  [API 1+]
+ getEncodings(): Iterator  [API 1+]
* getType(): String  [API 1+]
* hashCode(): int  [API 1+]
* toString(): String  [API 1+]
- writeReplace(): Object  [API 1+]
```

## CertPath.CertPathRep — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- CertPathRep(String, byte[])  [API 1+]
- readResolve(): Object  [API 1+]
```

## CertPathBuilder — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- CertPathBuilder(CertPathBuilderSpi, Provider, String)  [API 1+]
- build(CertPathParameters): CertPathBuilderResult  [API 1+]
- getAlgorithm(): String  [API 1+]
- getDefaultType(): String [static]  [API 1+]
- getInstance(String): CertPathBuilder [static]  [API 1+]
- getInstance(String, Provider): CertPathBuilder [static]  [API 1+]
- getInstance(String, String): CertPathBuilder [static]  [API 1+]
- getProvider(): Provider  [API 1+]
- getRevocationChecker(): CertPathChecker  [API 24+]
```

## CertPathBuilderException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CertPathBuilderException()  [API 1+]
+ CertPathBuilderException(String)  [API 1+]
+ CertPathBuilderException(String, Throwable)  [API 1+]
+ CertPathBuilderException(Throwable)  [API 1+]
```

## CertPathBuilderResult — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ clone(): Object  [API 1+]
+ getCertPath(): CertPath  [API 1+]
```

## CertPathBuilderSpi — declaration; API 1+ (1 +, 0 *, 0 ~, 2 -)

```
- CertPathBuilderSpi()  [API 1+]
+ engineBuild(CertPathParameters): CertPathBuilderResult  [API 1+]
- engineGetRevocationChecker(): CertPathChecker  [API 24+]
```

## CertPathChecker — declaration; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ check(Certificate): void  [API 24+]
+ init(boolean): void  [API 24+]
+ isForwardCheckingSupported(): boolean  [API 24+]
```

## CertPathParameters — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ clone(): Object  [API 1+]
```

## CertPathValidator — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- CertPathValidator(CertPathValidatorSpi, Provider, String)  [API 1+]
- getAlgorithm(): String  [API 1+]
- getDefaultType(): String [static]  [API 1+]
- getInstance(String): CertPathValidator [static]  [API 1+]
- getInstance(String, Provider): CertPathValidator [static]  [API 1+]
- getInstance(String, String): CertPathValidator [static]  [API 1+]
- getProvider(): Provider  [API 1+]
- getRevocationChecker(): CertPathChecker  [API 24+]
- validate(CertPath, CertPathParameters): CertPathValidatorResult  [API 1+]
```

## CertPathValidatorException — declaration; API 1+ (4 +, 0 *, 0 ~, 5 -)

```
+ CertPathValidatorException()  [API 1+]
+ CertPathValidatorException(String)  [API 1+]
+ CertPathValidatorException(String, Throwable)  [API 1+]
- CertPathValidatorException(String, Throwable, CertPath, int)  [API 1+]
- CertPathValidatorException(String, Throwable, CertPath, int, CertPathValidatorException.Reason)  [API 24+]
+ CertPathValidatorException(Throwable)  [API 1+]
- getCertPath(): CertPath  [API 1+]
- getIndex(): int  [API 1+]
- getReason(): CertPathValidatorException.Reason  [API 24+]
```

## CertPathValidatorException.BasicReason — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ ALGORITHM_CONSTRAINED [field]  [API 24+]
+ EXPIRED [field]  [API 24+]
+ INVALID_SIGNATURE [field]  [API 24+]
+ NOT_YET_VALID [field]  [API 24+]
+ REVOKED [field]  [API 24+]
+ UNDETERMINED_REVOCATION_STATUS [field]  [API 24+]
+ UNSPECIFIED [field]  [API 24+]
+ valueOf(String): CertPathValidatorException.BasicReason [static]  [API 24+]
+ values(): CertPathValidatorException.BasicReason[] [static]  [API 24+]
```

## CertPathValidatorException.Reason — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## CertPathValidatorResult — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ clone(): Object  [API 1+]
```

## CertPathValidatorSpi — declaration; API 1+ (1 +, 0 *, 0 ~, 2 -)

```
- CertPathValidatorSpi()  [API 1+]
- engineGetRevocationChecker(): CertPathChecker  [API 24+]
+ engineValidate(CertPath, CertPathParameters): CertPathValidatorResult  [API 1+]
```

## CertSelector — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ clone(): Object  [API 1+]
+ match(Certificate): boolean  [API 1+]
```

## CertStore — declaration; API 1+ (0 +, 0 *, 0 ~, 10 -)

```
- CertStore(CertStoreSpi, Provider, String, CertStoreParameters)  [API 1+]
- getCRLs(CRLSelector): Collection  [API 1+]
- getCertStoreParameters(): CertStoreParameters  [API 1+]
- getCertificates(CertSelector): Collection  [API 1+]
- getDefaultType(): String [static]  [API 1+]
- getInstance(String, CertStoreParameters): CertStore [static]  [API 1+]
- getInstance(String, CertStoreParameters, Provider): CertStore [static]  [API 1+]
- getInstance(String, CertStoreParameters, String): CertStore [static]  [API 1+]
- getProvider(): Provider  [API 1+]
- getType(): String  [API 1+]
```

## CertStoreException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CertStoreException()  [API 1+]
+ CertStoreException(String)  [API 1+]
+ CertStoreException(String, Throwable)  [API 1+]
+ CertStoreException(Throwable)  [API 1+]
```

## CertStoreParameters — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ clone(): Object  [API 1+]
```

## CertStoreSpi — declaration; API 1+ (2 +, 0 *, 0 ~, 1 -)

```
- CertStoreSpi(CertStoreParameters)  [API 1+]
+ engineGetCRLs(CRLSelector): Collection  [API 1+]
+ engineGetCertificates(CertSelector): Collection  [API 1+]
```

## Certificate — declaration; API 1+ (4 +, 5 *, 0 ~, 2 -)

```
* Certificate(String)  [API 1+]
* equals(Object): boolean  [API 1+]
+ getEncoded(): byte[]  [API 1+]
+ getPublicKey(): PublicKey  [API 1+]
* getType(): String  [API 1+]
* hashCode(): int  [API 1+]
* toString(): String  [API 1+]
+ verify(PublicKey): void  [API 1+]
- verify(PublicKey, Provider): void  [API 26+]
+ verify(PublicKey, String): void  [API 1+]
- writeReplace(): Object  [API 1+]
```

## Certificate.CertificateRep — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- CertificateRep(String, byte[])  [API 1+]
- readResolve(): Object  [API 1+]
```

## CertificateEncodingException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CertificateEncodingException()  [API 1+]
+ CertificateEncodingException(String)  [API 1+]
+ CertificateEncodingException(String, Throwable)  [API 1+]
+ CertificateEncodingException(Throwable)  [API 1+]
```

## CertificateException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CertificateException()  [API 1+]
+ CertificateException(String)  [API 1+]
+ CertificateException(String, Throwable)  [API 1+]
+ CertificateException(Throwable)  [API 1+]
```

## CertificateExpiredException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CertificateExpiredException()  [API 1+]
+ CertificateExpiredException(String)  [API 1+]
```

## CertificateFactory — declaration; API 1+ (0 +, 0 *, 0 ~, 14 -)

```
- CertificateFactory(CertificateFactorySpi, Provider, String)  [API 1+]
- generateCRL(InputStream): CRL  [API 1+]
- generateCRLs(InputStream): Collection  [API 1+]
- generateCertPath(InputStream): CertPath  [API 1+]
- generateCertPath(InputStream, String): CertPath  [API 1+]
- generateCertPath(List): CertPath  [API 1+]
- generateCertificate(InputStream): Certificate  [API 1+]
- generateCertificates(InputStream): Collection  [API 1+]
- getCertPathEncodings(): Iterator  [API 1+]
- getInstance(String): CertificateFactory [static]  [API 1+]
- getInstance(String, Provider): CertificateFactory [static]  [API 1+]
- getInstance(String, String): CertificateFactory [static]  [API 1+]
- getProvider(): Provider  [API 1+]
- getType(): String  [API 1+]
```

## CertificateFactorySpi — declaration; API 1+ (4 +, 0 *, 0 ~, 5 -)

```
- CertificateFactorySpi()  [API 1+]
+ engineGenerateCRL(InputStream): CRL  [API 1+]
+ engineGenerateCRLs(InputStream): Collection  [API 1+]
- engineGenerateCertPath(InputStream): CertPath  [API 1+]
- engineGenerateCertPath(InputStream, String): CertPath  [API 1+]
- engineGenerateCertPath(List): CertPath  [API 1+]
+ engineGenerateCertificate(InputStream): Certificate  [API 1+]
+ engineGenerateCertificates(InputStream): Collection  [API 1+]
- engineGetCertPathEncodings(): Iterator  [API 1+]
```

## CertificateNotYetValidException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ CertificateNotYetValidException()  [API 1+]
+ CertificateNotYetValidException(String)  [API 1+]
```

## CertificateParsingException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CertificateParsingException()  [API 1+]
+ CertificateParsingException(String)  [API 1+]
+ CertificateParsingException(String, Throwable)  [API 1+]
+ CertificateParsingException(Throwable)  [API 1+]
```

## CertificateRevokedException — declaration; API 24+ (0 +, 0 *, 0 ~, 7 -)

```
- CertificateRevokedException(Date, CRLReason, X500Principal, Map)  [API 24+]
- getAuthorityName(): X500Principal  [API 24+]
- getExtensions(): Map  [API 24+]
- getInvalidityDate(): Date  [API 24+]
- getMessage(): String  [API 24+]
- getRevocationDate(): Date  [API 24+]
- getRevocationReason(): CRLReason  [API 24+]
```

## CollectionCertStoreParameters — declaration; API 1+ (0 +, 3 *, 0 ~, 2 -)

```
- CollectionCertStoreParameters()  [API 1+]
* CollectionCertStoreParameters(Collection)  [API 1+]
- clone(): Object  [API 1+]
* getCollection(): Collection  [API 1+]
* toString(): String  [API 1+]
```

## Extension — declaration; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ encode(OutputStream): void  [API 24+]
+ getId(): String  [API 24+]
+ getValue(): byte[]  [API 24+]
+ isCritical(): boolean  [API 24+]
```

## LDAPCertStoreParameters — declaration; API 1+ (0 +, 5 *, 0 ~, 2 -)

```
- LDAPCertStoreParameters()  [API 1+]
* LDAPCertStoreParameters(String)  [API 1+]
* LDAPCertStoreParameters(String, int)  [API 1+]
- clone(): Object  [API 1+]
* getPort(): int  [API 1+]
* getServerName(): String  [API 1+]
* toString(): String  [API 1+]
```

## PKIXBuilderParameters — declaration; API 1+ (0 +, 3 *, 0 ~, 2 -)

```
- PKIXBuilderParameters(KeyStore, CertSelector)  [API 1+]
- PKIXBuilderParameters(Set, CertSelector)  [API 1+]
* getMaxPathLength(): int  [API 1+]
* setMaxPathLength(int): void  [API 1+]
* toString(): String  [API 1+]
```

## PKIXCertPathBuilderResult — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- PKIXCertPathBuilderResult(CertPath, TrustAnchor, PolicyNode, PublicKey)  [API 1+]
- getCertPath(): CertPath  [API 1+]
- toString(): String  [API 1+]
```

## PKIXCertPathChecker — declaration; API 1+ (4 +, 0 *, 0 ~, 3 -)

```
- PKIXCertPathChecker()  [API 1+]
- check(Certificate): void  [API 1+]
+ check(Certificate, Collection): void  [API 1+]
- clone(): Object  [API 1+]
+ getSupportedExtensions(): Set  [API 1+]
+ init(boolean): void  [API 1+]
+ isForwardCheckingSupported(): boolean  [API 1+]
```

## PKIXCertPathValidatorResult — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- PKIXCertPathValidatorResult(TrustAnchor, PolicyNode, PublicKey)  [API 1+]
- clone(): Object  [API 1+]
- getPolicyTree(): PolicyNode  [API 1+]
- getPublicKey(): PublicKey  [API 1+]
- getTrustAnchor(): TrustAnchor  [API 1+]
- toString(): String  [API 1+]
```

## PKIXParameters — declaration; API 1+ (0 +, 26 *, 0 ~, 4 -)

```
- PKIXParameters(KeyStore)  [API 1+]
* PKIXParameters(Set)  [API 1+]
- addCertPathChecker(PKIXCertPathChecker): void  [API 1+]
- addCertStore(CertStore): void  [API 1+]
- clone(): Object  [API 1+]
* getCertPathCheckers(): List  [API 1+]
* getCertStores(): List  [API 1+]
* getDate(): Date  [API 1+]
* getInitialPolicies(): Set  [API 1+]
* getPolicyQualifiersRejected(): boolean  [API 1+]
* getSigProvider(): String  [API 1+]
* getTargetCertConstraints(): CertSelector  [API 1+]
* getTrustAnchors(): Set  [API 1+]
* isAnyPolicyInhibited(): boolean  [API 1+]
* isExplicitPolicyRequired(): boolean  [API 1+]
* isPolicyMappingInhibited(): boolean  [API 1+]
* isRevocationEnabled(): boolean  [API 1+]
* setAnyPolicyInhibited(boolean): void  [API 1+]
* setCertPathCheckers(List): void  [API 1+]
* setCertStores(List): void  [API 1+]
* setDate(Date): void  [API 1+]
* setExplicitPolicyRequired(boolean): void  [API 1+]
* setInitialPolicies(Set): void  [API 1+]
* setPolicyMappingInhibited(boolean): void  [API 1+]
* setPolicyQualifiersRejected(boolean): void  [API 1+]
* setRevocationEnabled(boolean): void  [API 1+]
* setSigProvider(String): void  [API 1+]
* setTargetCertConstraints(CertSelector): void  [API 1+]
* setTrustAnchors(Set): void  [API 1+]
* toString(): String  [API 1+]
```

## PKIXReason — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ INVALID_KEY_USAGE [field]  [API 24+]
+ INVALID_NAME [field]  [API 24+]
+ INVALID_POLICY [field]  [API 24+]
+ NAME_CHAINING [field]  [API 24+]
+ NOT_CA_CERT [field]  [API 24+]
+ NO_TRUST_ANCHOR [field]  [API 24+]
+ PATH_TOO_LONG [field]  [API 24+]
+ UNRECOGNIZED_CRIT_EXT [field]  [API 24+]
+ valueOf(String): PKIXReason [static]  [API 24+]
+ values(): PKIXReason[] [static]  [API 24+]
```

## PKIXRevocationChecker — declaration; API 24+ (1 +, 11 *, 0 ~, 1 -)

```
* PKIXRevocationChecker()  [API 24+]
- clone(): PKIXRevocationChecker  [API 24+]
* getOcspExtensions(): List  [API 24+]
* getOcspResponder(): URI  [API 24+]
* getOcspResponderCert(): X509Certificate  [API 24+]
* getOcspResponses(): Map  [API 24+]
* getOptions(): Set  [API 24+]
+ getSoftFailExceptions(): List  [API 24+]
* setOcspExtensions(List): void  [API 24+]
* setOcspResponder(URI): void  [API 24+]
* setOcspResponderCert(X509Certificate): void  [API 24+]
* setOcspResponses(Map): void  [API 24+]
* setOptions(Set): void  [API 24+]
```

## PKIXRevocationChecker.Option — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ NO_FALLBACK [field]  [API 24+]
+ ONLY_END_ENTITY [field]  [API 24+]
+ PREFER_CRLS [field]  [API 24+]
+ SOFT_FAIL [field]  [API 24+]
+ valueOf(String): PKIXRevocationChecker.Option [static]  [API 24+]
+ values(): PKIXRevocationChecker.Option[] [static]  [API 24+]
```

## PolicyNode — declaration; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ getChildren(): Iterator  [API 1+]
+ getDepth(): int  [API 1+]
+ getExpectedPolicies(): Set  [API 1+]
+ getParent(): PolicyNode  [API 1+]
+ getPolicyQualifiers(): Set  [API 1+]
+ getValidPolicy(): String  [API 1+]
+ isCritical(): boolean  [API 1+]
```

## PolicyQualifierInfo — declaration; API 1+ (0 +, 2 *, 0 ~, 3 -)

```
* PolicyQualifierInfo(byte[])  [API 1+]
* getEncoded(): byte[]  [API 1+]
- getPolicyQualifier(): byte[]  [API 1+]
- getPolicyQualifierId(): String  [API 1+]
- toString(): String  [API 1+]
```

## TrustAnchor — declaration; API 1+ (0 +, 3 *, 0 ~, 6 -)

```
- TrustAnchor(String, PublicKey, byte[])  [API 1+]
- TrustAnchor(X500Principal, PublicKey, byte[])  [API 1+]
* TrustAnchor(X509Certificate, byte[])  [API 1+]
- getCA(): X500Principal  [API 1+]
- getCAName(): String  [API 1+]
- getCAPublicKey(): PublicKey  [API 1+]
* getNameConstraints(): byte[]  [API 1+]
* getTrustedCert(): X509Certificate  [API 1+]
- toString(): String  [API 1+]
```

## URICertStoreParameters — declaration; API 33+ (0 +, 6 *, 0 ~, 0 -)

```
* URICertStoreParameters(URI)  [API 33+]
* clone(): URICertStoreParameters  [API 33+]
* equals(Object): boolean  [API 33+]
* getURI(): URI  [API 33+]
* hashCode(): int  [API 33+]
* toString(): String  [API 33+]
```

## X509CRL — declaration; API 1+ (14 +, 0 *, 0 ~, 6 -)

```
- X509CRL()  [API 1+]
- equals(Object): boolean  [API 1+]
+ getEncoded(): byte[]  [API 1+]
+ getIssuerDN(): Principal  [API 1+]
- getIssuerX500Principal(): X500Principal  [API 1+]
+ getNextUpdate(): Date  [API 1+]
+ getRevokedCertificate(BigInteger): X509CRLEntry  [API 1+]
- getRevokedCertificate(X509Certificate): X509CRLEntry  [API 1+]
+ getRevokedCertificates(): Set  [API 1+]
+ getSigAlgName(): String  [API 1+]
+ getSigAlgOID(): String  [API 1+]
+ getSigAlgParams(): byte[]  [API 1+]
+ getSignature(): byte[]  [API 1+]
+ getTBSCertList(): byte[]  [API 1+]
+ getThisUpdate(): Date  [API 1+]
+ getVersion(): int  [API 1+]
- hashCode(): int  [API 1+]
+ verify(PublicKey): void  [API 1+]
- verify(PublicKey, Provider): void  [API 26+]
+ verify(PublicKey, String): void  [API 1+]
```

## X509CRLEntry — declaration; API 1+ (5 +, 0 *, 0 ~, 5 -)

```
- X509CRLEntry()  [API 1+]
- equals(Object): boolean  [API 1+]
- getCertificateIssuer(): X500Principal  [API 1+]
+ getEncoded(): byte[]  [API 1+]
+ getRevocationDate(): Date  [API 1+]
- getRevocationReason(): CRLReason  [API 24+]
+ getSerialNumber(): BigInteger  [API 1+]
+ hasExtensions(): boolean  [API 1+]
- hashCode(): int  [API 1+]
+ toString(): String  [API 1+]
```

## X509CRLSelector — declaration; API 1+ (0 +, 9 *, 0 ~, 10 -)

```
* X509CRLSelector()  [API 1+]
- addIssuer(X500Principal): void  [API 1+]
- addIssuerName(String): void  [API 1+]
- addIssuerName(byte[]): void  [API 1+]
- clone(): Object  [API 1+]
* getCertificateChecking(): X509Certificate  [API 1+]
* getDateAndTime(): Date  [API 1+]
* getIssuerNames(): Collection  [API 1+]
* getIssuers(): Collection  [API 1+]
- getMaxCRL(): BigInteger  [API 1+]
- getMinCRL(): BigInteger  [API 1+]
- match(CRL): boolean  [API 1+]
* setCertificateChecking(X509Certificate): void  [API 1+]
* setDateAndTime(Date): void  [API 1+]
* setIssuerNames(Collection): void  [API 1+]
* setIssuers(Collection): void  [API 1+]
- setMaxCRLNumber(BigInteger): void  [API 1+]
- setMinCRLNumber(BigInteger): void  [API 1+]
- toString(): String  [API 1+]
```

## X509CertSelector — declaration; API 1+ (0 +, 37 *, 0 ~, 16 -)

```
* X509CertSelector()  [API 1+]
- addPathToName(int, String): void  [API 1+]
- addPathToName(int, byte[]): void  [API 1+]
- addSubjectAlternativeName(int, String): void  [API 1+]
- addSubjectAlternativeName(int, byte[]): void  [API 1+]
- clone(): Object  [API 1+]
* getAuthorityKeyIdentifier(): byte[]  [API 1+]
* getBasicConstraints(): int  [API 1+]
* getCertificate(): X509Certificate  [API 1+]
* getCertificateValid(): Date  [API 1+]
* getExtendedKeyUsage(): Set  [API 1+]
* getIssuer(): X500Principal  [API 1+]
- getIssuerAsBytes(): byte[]  [API 1+]
- getIssuerAsString(): String  [API 1+]
* getKeyUsage(): boolean[]  [API 1+]
* getMatchAllSubjectAltNames(): boolean  [API 1+]
* getNameConstraints(): byte[]  [API 1+]
* getPathToNames(): Collection  [API 1+]
* getPolicy(): Set  [API 1+]
* getPrivateKeyValid(): Date  [API 1+]
* getSerialNumber(): BigInteger  [API 1+]
* getSubject(): X500Principal  [API 1+]
* getSubjectAlternativeNames(): Collection  [API 1+]
- getSubjectAsBytes(): byte[]  [API 1+]
- getSubjectAsString(): String  [API 1+]
* getSubjectKeyIdentifier(): byte[]  [API 1+]
* getSubjectPublicKey(): PublicKey  [API 1+]
* getSubjectPublicKeyAlgID(): String  [API 1+]
- match(Certificate): boolean  [API 1+]
* setAuthorityKeyIdentifier(byte[]): void  [API 1+]
* setBasicConstraints(int): void  [API 1+]
* setCertificate(X509Certificate): void  [API 1+]
* setCertificateValid(Date): void  [API 1+]
* setExtendedKeyUsage(Set): void  [API 1+]
- setIssuer(String): void  [API 1+]
* setIssuer(X500Principal): void  [API 1+]
- setIssuer(byte[]): void  [API 1+]
* setKeyUsage(boolean[]): void  [API 1+]
* setMatchAllSubjectAltNames(boolean): void  [API 1+]
* setNameConstraints(byte[]): void  [API 1+]
* setPathToNames(Collection): void  [API 1+]
* setPolicy(Set): void  [API 1+]
* setPrivateKeyValid(Date): void  [API 1+]
* setSerialNumber(BigInteger): void  [API 1+]
- setSubject(String): void  [API 1+]
* setSubject(X500Principal): void  [API 1+]
- setSubject(byte[]): void  [API 1+]
* setSubjectAlternativeNames(Collection): void  [API 1+]
* setSubjectKeyIdentifier(byte[]): void  [API 1+]
* setSubjectPublicKey(PublicKey): void  [API 1+]
- setSubjectPublicKey(byte[]): void  [API 1+]
* setSubjectPublicKeyAlgID(String): void  [API 1+]
- toString(): String  [API 1+]
```

## X509Certificate — declaration; API 1+ (17 +, 0 *, 0 ~, 7 -)

```
- X509Certificate()  [API 1+]
+ checkValidity(): void  [API 1+]
+ checkValidity(Date): void  [API 1+]
+ getBasicConstraints(): int  [API 1+]
- getExtendedKeyUsage(): List  [API 1+]
- getIssuerAlternativeNames(): Collection  [API 1+]
+ getIssuerDN(): Principal  [API 1+]
+ getIssuerUniqueID(): boolean[]  [API 1+]
- getIssuerX500Principal(): X500Principal  [API 1+]
+ getKeyUsage(): boolean[]  [API 1+]
+ getNotAfter(): Date  [API 1+]
+ getNotBefore(): Date  [API 1+]
+ getSerialNumber(): BigInteger  [API 1+]
+ getSigAlgName(): String  [API 1+]
+ getSigAlgOID(): String  [API 1+]
+ getSigAlgParams(): byte[]  [API 1+]
+ getSignature(): byte[]  [API 1+]
- getSubjectAlternativeNames(): Collection  [API 1+]
+ getSubjectDN(): Principal  [API 1+]
+ getSubjectUniqueID(): boolean[]  [API 1+]
- getSubjectX500Principal(): X500Principal  [API 1+]
+ getTBSCertificate(): byte[]  [API 1+]
+ getVersion(): int  [API 1+]
- verify(PublicKey, Provider): void  [API 24+]
```

## X509Extension — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ getCriticalExtensionOIDs(): Set  [API 1+]
+ getExtensionValue(String): byte[]  [API 1+]
+ getNonCriticalExtensionOIDs(): Set  [API 1+]
+ hasUnsupportedCriticalExtension(): boolean  [API 1+]
```


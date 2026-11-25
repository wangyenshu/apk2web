# java.security

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AccessControlContext — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- AccessControlContext(AccessControlContext, DomainCombiner)  [API 1+]
- AccessControlContext(ProtectionDomain[])  [API 1+]
- checkPermission(Permission): void  [API 1+]
- getDomainCombiner(): DomainCombiner  [API 1+]
```

## AccessControlException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ AccessControlException(String)  [API 1+]
+ AccessControlException(String, Permission)  [API 1+]
+ getPermission(): Permission  [API 1+]
```

## AccessController — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 6 -)

```
- checkPermission(Permission): void [static]  [API 1+]
+ doPrivileged(PrivilegedAction): Object [static]  [API 1+]
- doPrivileged(PrivilegedAction, AccessControlContext): Object [static]  [API 1+]
+ doPrivileged(PrivilegedExceptionAction): Object [static]  [API 1+]
- doPrivileged(PrivilegedExceptionAction, AccessControlContext): Object [static]  [API 1+]
- doPrivilegedWithCombiner(PrivilegedAction): Object [static]  [API 9+]
- doPrivilegedWithCombiner(PrivilegedExceptionAction): Object [static]  [API 9+]
- getContext(): AccessControlContext [static]  [API 1+]
```

## AlgorithmConstraints — declaration; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ permits(Set, Key): boolean  [API 24+]
+ permits(Set, String, AlgorithmParameters): boolean  [API 24+]
+ permits(Set, String, Key, AlgorithmParameters): boolean  [API 24+]
```

## AlgorithmParameterGenerator — declaration; API 1+ (0 +, 0 *, 0 ~, 11 -)

```
- AlgorithmParameterGenerator(AlgorithmParameterGeneratorSpi, Provider, String)  [API 1+]
- generateParameters(): AlgorithmParameters  [API 1+]
- getAlgorithm(): String  [API 1+]
- getInstance(String): AlgorithmParameterGenerator [static]  [API 1+]
- getInstance(String, Provider): AlgorithmParameterGenerator [static]  [API 1+]
- getInstance(String, String): AlgorithmParameterGenerator [static]  [API 1+]
- getProvider(): Provider  [API 1+]
- init(AlgorithmParameterSpec): void  [API 1+]
- init(AlgorithmParameterSpec, SecureRandom): void  [API 1+]
- init(int): void  [API 1+]
- init(int, SecureRandom): void  [API 1+]
```

## AlgorithmParameterGeneratorSpi — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ AlgorithmParameterGeneratorSpi()  [API 1+]
+ engineGenerateParameters(): AlgorithmParameters  [API 1+]
+ engineInit(AlgorithmParameterSpec, SecureRandom): void  [API 1+]
+ engineInit(int, SecureRandom): void  [API 1+]
```

## AlgorithmParameters — declaration; API 1+ (0 +, 0 *, 0 ~, 13 -)

```
- AlgorithmParameters(AlgorithmParametersSpi, Provider, String)  [API 1+]
- getAlgorithm(): String  [API 1+]
- getEncoded(): byte[]  [API 1+]
- getEncoded(String): byte[]  [API 1+]
- getInstance(String): AlgorithmParameters [static]  [API 1+]
- getInstance(String, Provider): AlgorithmParameters [static]  [API 1+]
- getInstance(String, String): AlgorithmParameters [static]  [API 1+]
- getParameterSpec(Class): AlgorithmParameterSpec  [API 1+]
- getProvider(): Provider  [API 1+]
- init(AlgorithmParameterSpec): void  [API 1+]
- init(byte[]): void  [API 1+]
- init(byte[], String): void  [API 1+]
- toString(): String  [API 1+]
```

## AlgorithmParametersSpi — declaration; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ AlgorithmParametersSpi()  [API 1+]
+ engineGetEncoded(): byte[]  [API 1+]
+ engineGetEncoded(String): byte[]  [API 1+]
+ engineGetParameterSpec(Class): AlgorithmParameterSpec  [API 1+]
+ engineInit(AlgorithmParameterSpec): void  [API 1+]
+ engineInit(byte[]): void  [API 1+]
+ engineInit(byte[], String): void  [API 1+]
+ engineToString(): String  [API 1+]
```

## AllPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- AllPermission()  [API 1+]
- AllPermission(String, String)  [API 1+]
- getActions(): String  [API 1+]
- implies(Permission): boolean  [API 1+]
```

## AuthProvider — declaration; API 1+ (3 +, 0 *, 0 ~, 1 -)

```
- AuthProvider(String, double, String)  [API 1+]
+ login(Subject, CallbackHandler): void  [API 1+]
+ logout(): void  [API 1+]
+ setCallbackHandler(CallbackHandler): void  [API 1+]
```

## BasicPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- BasicPermission(String)  [API 1+]
- BasicPermission(String, String)  [API 1+]
- getActions(): String  [API 1+]
- implies(Permission): boolean  [API 1+]
```

## Certificate — declaration; API 1+, deprecated in 3 (7 +, 0 *, 0 ~, 0 -)

```
+ decode(InputStream): void  [API 1+]
+ encode(OutputStream): void  [API 1+]
+ getFormat(): String  [API 1+]
+ getGuarantor(): Principal  [API 1+]
+ getPrincipal(): Principal  [API 1+]
+ getPublicKey(): PublicKey  [API 1+]
+ toString(boolean): String  [API 1+]
```

## CodeSigner — declaration; API 1+ (0 +, 6 *, 0 ~, 0 -)

```
* CodeSigner(CertPath, Timestamp)  [API 1+]
* equals(Object): boolean  [API 1+]
* getSignerCertPath(): CertPath  [API 1+]
* getTimestamp(): Timestamp  [API 1+]
* hashCode(): int  [API 1+]
* toString(): String  [API 1+]
```

## CodeSource — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- CodeSource(URL, Certificate[])  [API 1+]
- CodeSource(URL, CodeSigner[])  [API 1+]
- getCertificates(): Certificate[]  [API 1+]
- getCodeSigners(): CodeSigner[]  [API 1+]
- getLocation(): URL  [API 1+]
- implies(CodeSource): boolean  [API 1+]
```

## CryptoPrimitive — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ BLOCK_CIPHER [field]  [API 24+]
+ KEY_AGREEMENT [field]  [API 24+]
+ KEY_ENCAPSULATION [field]  [API 24+]
+ KEY_WRAP [field]  [API 24+]
+ MAC [field]  [API 24+]
+ MESSAGE_DIGEST [field]  [API 24+]
+ PUBLIC_KEY_ENCRYPTION [field]  [API 24+]
+ SECURE_RANDOM [field]  [API 24+]
+ SIGNATURE [field]  [API 24+]
+ STREAM_CIPHER [field]  [API 24+]
+ valueOf(String): CryptoPrimitive [static]  [API 24+]
+ values(): CryptoPrimitive[] [static]  [API 24+]
```

## DigestException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ DigestException()  [API 1+]
+ DigestException(String)  [API 1+]
+ DigestException(String, Throwable)  [API 1+]
+ DigestException(Throwable)  [API 1+]
```

## DigestInputStream — declaration; API 1+ (0 +, 0 *, 0 ~, 7 -)

```
- DigestInputStream(InputStream, MessageDigest)  [API 1+]
+ digest [field]  [API 1+]
- getMessageDigest(): MessageDigest  [API 1+]
- on(boolean): void  [API 1+]
- read(): int  [API 1+]
- read(byte[], int, int): int  [API 1+]
- setMessageDigest(MessageDigest): void  [API 1+]
- toString(): String  [API 1+]
```

## DigestOutputStream — declaration; API 1+ (0 +, 0 *, 0 ~, 7 -)

```
- DigestOutputStream(OutputStream, MessageDigest)  [API 1+]
+ digest [field]  [API 1+]
- getMessageDigest(): MessageDigest  [API 1+]
- on(boolean): void  [API 1+]
- setMessageDigest(MessageDigest): void  [API 1+]
- toString(): String  [API 1+]
- write(byte[], int, int): void  [API 1+]
- write(int): void  [API 1+]
```

## DomainCombiner — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ combine(ProtectionDomain[], ProtectionDomain[]): ProtectionDomain[]  [API 1+]
```

## DomainLoadStoreParameter — declaration; API 26+ (0 +, 3 *, 0 ~, 1 -)

```
* DomainLoadStoreParameter(URI, Map)  [API 26+]
* getConfiguration(): URI  [API 26+]
- getProtectionParameter(): KeyStore.ProtectionParameter  [API 26+]
* getProtectionParams(): Map  [API 26+]
```

## DrbgParameters — declaration; API 35+ (0 +, 0 *, 0 ~, 3 -)

```
- instantiation(int, DrbgParameters.Capability, byte[]): DrbgParameters.Instantiation [static]  [API 35+]
- nextBytes(int, boolean, byte[]): DrbgParameters.NextBytes [static]  [API 35+]
- reseed(boolean, byte[]): DrbgParameters.Reseed [static]  [API 35+]
```

## DrbgParameters.Capability — declaration; API 35+ (2 +, 0 *, 0 ~, 3 -)

```
+ NONE [field]  [API 35+]
+ PR_AND_RESEED [field]  [API 35+]
+ RESEED_ONLY [field]  [API 35+]
- supportsPredictionResistance(): boolean  [API 35+]
- supportsReseeding(): boolean  [API 35+]
- toString(): String  [API 35+]
+ valueOf(String): DrbgParameters.Capability [static]  [API 35+]
+ values(): DrbgParameters.Capability[] [static]  [API 35+]
```

## DrbgParameters.Instantiation — declaration; API 35+ (0 +, 0 *, 0 ~, 4 -)

```
- getCapability(): DrbgParameters.Capability  [API 35+]
- getPersonalizationString(): byte[]  [API 35+]
- getStrength(): int  [API 35+]
- toString(): String  [API 35+]
```

## DrbgParameters.NextBytes — declaration; API 35+ (0 +, 0 *, 0 ~, 3 -)

```
- getAdditionalInput(): byte[]  [API 35+]
- getPredictionResistance(): boolean  [API 35+]
- getStrength(): int  [API 35+]
```

## DrbgParameters.Reseed — declaration; API 35+ (0 +, 0 *, 0 ~, 2 -)

```
- getAdditionalInput(): byte[]  [API 35+]
- getPredictionResistance(): boolean  [API 35+]
```

## GeneralSecurityException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ GeneralSecurityException()  [API 1+]
+ GeneralSecurityException(String)  [API 1+]
+ GeneralSecurityException(String, Throwable)  [API 1+]
+ GeneralSecurityException(Throwable)  [API 1+]
```

## Guard — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ checkGuard(Object): void  [API 1+]
```

## GuardedObject — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- GuardedObject(Object, Guard)  [API 1+]
- getObject(): Object  [API 1+]
```

## Identity — declaration; API 1+, deprecated in 3 (0 +, 12 *, 0 ~, 5 -)

```
* Identity()  [API 1+]
* Identity(String)  [API 1+]
* Identity(String, IdentityScope)  [API 1+]
- addCertificate(Certificate): void  [API 1+]
- certificates(): Certificate[]  [API 1+]
* equals(Object): boolean  [API 1+]
* getInfo(): String  [API 1+]
* getName(): String  [API 1+]
* getPublicKey(): PublicKey  [API 1+]
* getScope(): IdentityScope  [API 1+]
* hashCode(): int  [API 1+]
- identityEquals(Identity): boolean  [API 1+]
- removeCertificate(Certificate): void  [API 1+]
* setInfo(String): void  [API 1+]
* setPublicKey(PublicKey): void  [API 1+]
* toString(): String  [API 1+]
- toString(boolean): String  [API 1+]
```

## IdentityScope — declaration; API 1+, deprecated in 3 (6 +, 3 *, 0 ~, 4 -)

```
- IdentityScope()  [API 1+]
* IdentityScope(String)  [API 1+]
* IdentityScope(String, IdentityScope)  [API 1+]
+ addIdentity(Identity): void  [API 1+]
- getIdentity(Principal): Identity  [API 1+]
+ getIdentity(PublicKey): Identity  [API 1+]
+ getIdentity(String): Identity  [API 1+]
- getSystemScope(): IdentityScope [static]  [API 1+]
+ identities(): Enumeration  [API 1+]
+ removeIdentity(Identity): void  [API 1+]
- setSystemScope(IdentityScope): void [static]  [API 1+]
+ size(): int  [API 1+]
* toString(): String  [API 1+]
```

## InvalidAlgorithmParameterException — framework (a2w.jdk.sec.InvalidAlgorithmParameterException); API 1+ (2 +, 0 *, 0 ~, 2 -)

```
+ InvalidAlgorithmParameterException()  [API 1+]
+ InvalidAlgorithmParameterException(String)  [API 1+]
- InvalidAlgorithmParameterException(String, Throwable)  [API 1+]
- InvalidAlgorithmParameterException(Throwable)  [API 1+]
```

## InvalidKeyException — framework (a2w.jdk.sec.InvalidKeyException); API 1+ (2 +, 0 *, 0 ~, 2 -)

```
+ InvalidKeyException()  [API 1+]
+ InvalidKeyException(String)  [API 1+]
- InvalidKeyException(String, Throwable)  [API 1+]
- InvalidKeyException(Throwable)  [API 1+]
```

## InvalidParameterException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ InvalidParameterException()  [API 1+]
+ InvalidParameterException(String)  [API 1+]
```

## Key — framework (a2w.jdk.sec.Key); API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ getAlgorithm(): String  [API 1+]
+ getEncoded(): byte[]  [API 1+]
+ getFormat(): String  [API 1+]
```

## KeyException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ KeyException()  [API 1+]
+ KeyException(String)  [API 1+]
+ KeyException(String, Throwable)  [API 1+]
+ KeyException(Throwable)  [API 1+]
```

## KeyFactory — declaration; API 1+ (0 +, 0 *, 0 ~, 10 -)

```
- KeyFactory(KeyFactorySpi, Provider, String)  [API 1+]
- generatePrivate(KeySpec): PrivateKey  [API 1+]
- generatePublic(KeySpec): PublicKey  [API 1+]
- getAlgorithm(): String  [API 1+]
- getInstance(String): KeyFactory [static]  [API 1+]
- getInstance(String, Provider): KeyFactory [static]  [API 1+]
- getInstance(String, String): KeyFactory [static]  [API 1+]
- getKeySpec(Key, Class): KeySpec  [API 1+]
- getProvider(): Provider  [API 1+]
- translateKey(Key): Key  [API 1+]
```

## KeyFactorySpi — declaration; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ KeyFactorySpi()  [API 1+]
+ engineGeneratePrivate(KeySpec): PrivateKey  [API 1+]
+ engineGeneratePublic(KeySpec): PublicKey  [API 1+]
+ engineGetKeySpec(Key, Class): KeySpec  [API 1+]
+ engineTranslateKey(Key): Key  [API 1+]
```

## KeyManagementException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ KeyManagementException()  [API 1+]
+ KeyManagementException(String)  [API 1+]
+ KeyManagementException(String, Throwable)  [API 1+]
+ KeyManagementException(Throwable)  [API 1+]
```

## KeyPair — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- KeyPair(PublicKey, PrivateKey)  [API 1+]
- getPrivate(): PrivateKey  [API 1+]
- getPublic(): PublicKey  [API 1+]
```

## KeyPairGenerator — declaration; API 1+ (0 +, 2 *, 0 ~, 10 -)

```
* KeyPairGenerator(String)  [API 1+]
- genKeyPair(): KeyPair  [API 1+]
- generateKeyPair(): KeyPair  [API 1+]
* getAlgorithm(): String  [API 1+]
- getInstance(String): KeyPairGenerator [static]  [API 1+]
- getInstance(String, Provider): KeyPairGenerator [static]  [API 1+]
- getInstance(String, String): KeyPairGenerator [static]  [API 1+]
- getProvider(): Provider  [API 1+]
- initialize(AlgorithmParameterSpec): void  [API 1+]
- initialize(AlgorithmParameterSpec, SecureRandom): void  [API 1+]
- initialize(int): void  [API 1+]
- initialize(int, SecureRandom): void  [API 1+]
```

## KeyPairGeneratorSpi — declaration; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
- KeyPairGeneratorSpi()  [API 1+]
+ generateKeyPair(): KeyPair  [API 1+]
- initialize(AlgorithmParameterSpec, SecureRandom): void  [API 1+]
+ initialize(int, SecureRandom): void  [API 1+]
```

## KeyRep — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- KeyRep(KeyRep.Type, String, String, byte[])  [API 1+]
- readResolve(): Object  [API 1+]
```

## KeyRep.Type — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ PRIVATE [field]  [API 1+]
+ PUBLIC [field]  [API 1+]
+ SECRET [field]  [API 1+]
+ valueOf(String): KeyRep.Type [static]  [API 1+]
+ values(): KeyRep.Type[] [static]  [API 1+]
```

## KeyStore — declaration; API 1+ (0 +, 0 *, 0 ~, 30 -)

```
- KeyStore(KeyStoreSpi, Provider, String)  [API 1+]
- aliases(): Enumeration  [API 1+]
- containsAlias(String): boolean  [API 1+]
- deleteEntry(String): void  [API 1+]
- entryInstanceOf(String, Class): boolean  [API 1+]
- getCertificate(String): Certificate  [API 1+]
- getCertificateAlias(Certificate): String  [API 1+]
- getCertificateChain(String): Certificate[]  [API 1+]
- getCreationDate(String): Date  [API 1+]
- getDefaultType(): String [static]  [API 1+]
- getEntry(String, KeyStore.ProtectionParameter): KeyStore.Entry  [API 1+]
- getInstance(File, KeyStore.LoadStoreParameter): KeyStore [static]  [API 33+]
- getInstance(File, char[]): KeyStore [static]  [API 33+]
- getInstance(String): KeyStore [static]  [API 1+]
- getInstance(String, Provider): KeyStore [static]  [API 1+]
- getInstance(String, String): KeyStore [static]  [API 1+]
- getKey(String, char[]): Key  [API 1+]
- getProvider(): Provider  [API 1+]
- getType(): String  [API 1+]
- isCertificateEntry(String): boolean  [API 1+]
- isKeyEntry(String): boolean  [API 1+]
- load(InputStream, char[]): void  [API 1+]
- load(KeyStore.LoadStoreParameter): void  [API 1+]
- setCertificateEntry(String, Certificate): void  [API 1+]
- setEntry(String, KeyStore.Entry, KeyStore.ProtectionParameter): void  [API 1+]
- setKeyEntry(String, Key, char[], Certificate[]): void  [API 1+]
- setKeyEntry(String, byte[], Certificate[]): void  [API 1+]
- size(): int  [API 1+]
- store(KeyStore.LoadStoreParameter): void  [API 1+]
- store(OutputStream, char[]): void  [API 1+]
```

## KeyStore.Builder — declaration; API 1+ (2 +, 0 *, 0 ~, 5 -)

```
- Builder()  [API 1+]
+ getKeyStore(): KeyStore  [API 1+]
+ getProtectionParameter(String): KeyStore.ProtectionParameter  [API 1+]
- newInstance(File, KeyStore.ProtectionParameter): KeyStore.Builder [static]  [API 33+]
- newInstance(KeyStore, KeyStore.ProtectionParameter): KeyStore.Builder [static]  [API 1+]
- newInstance(String, Provider, File, KeyStore.ProtectionParameter): KeyStore.Builder [static]  [API 1+]
- newInstance(String, Provider, KeyStore.ProtectionParameter): KeyStore.Builder [static]  [API 1+]
```

## KeyStore.CallbackHandlerProtection — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- CallbackHandlerProtection(CallbackHandler)  [API 1+]
- getCallbackHandler(): CallbackHandler  [API 1+]
```

## KeyStore.Entry — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- getAttributes(): Set  [API 26+]
```

## KeyStore.Entry.Attribute — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ getName(): String  [API 26+]
+ getValue(): String  [API 26+]
```

## KeyStore.LoadStoreParameter — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getProtectionParameter(): KeyStore.ProtectionParameter  [API 1+]
```

## KeyStore.PasswordProtection — declaration; API 1+ (0 +, 5 *, 0 ~, 2 -)

```
* PasswordProtection(char[])  [API 1+]
* PasswordProtection(char[], String, AlgorithmParameterSpec)  [API 26+]
- destroy(): void  [API 1+]
* getPassword(): char[]  [API 1+]
* getProtectionAlgorithm(): String  [API 26+]
* getProtectionParameters(): AlgorithmParameterSpec  [API 26+]
- isDestroyed(): boolean  [API 1+]
```

## KeyStore.PrivateKeyEntry — declaration; API 1+ (0 +, 0 *, 0 ~, 7 -)

```
- PrivateKeyEntry(PrivateKey, Certificate[])  [API 1+]
- PrivateKeyEntry(PrivateKey, Certificate[], Set)  [API 26+]
- getAttributes(): Set  [API 1+]
- getCertificate(): Certificate  [API 1+]
- getCertificateChain(): Certificate[]  [API 1+]
- getPrivateKey(): PrivateKey  [API 1+]
- toString(): String  [API 1+]
```

## KeyStore.ProtectionParameter — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## KeyStore.SecretKeyEntry — declaration; API 1+ (0 +, 5 *, 0 ~, 0 -)

```
* SecretKeyEntry(SecretKey)  [API 1+]
* SecretKeyEntry(SecretKey, Set)  [API 26+]
* getAttributes(): Set  [API 1+]
* getSecretKey(): SecretKey  [API 1+]
* toString(): String  [API 1+]
```

## KeyStore.TrustedCertificateEntry — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- TrustedCertificateEntry(Certificate)  [API 1+]
- TrustedCertificateEntry(Certificate, Set)  [API 26+]
- getAttributes(): Set  [API 1+]
- getTrustedCertificate(): Certificate  [API 1+]
- toString(): String  [API 1+]
```

## KeyStoreException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ KeyStoreException()  [API 1+]
+ KeyStoreException(String)  [API 1+]
+ KeyStoreException(String, Throwable)  [API 1+]
+ KeyStoreException(Throwable)  [API 1+]
```

## KeyStoreSpi — declaration; API 1+ (16 +, 0 *, 0 ~, 7 -)

```
- KeyStoreSpi()  [API 1+]
+ engineAliases(): Enumeration  [API 1+]
+ engineContainsAlias(String): boolean  [API 1+]
+ engineDeleteEntry(String): void  [API 1+]
- engineEntryInstanceOf(String, Class): boolean  [API 1+]
+ engineGetCertificate(String): Certificate  [API 1+]
+ engineGetCertificateAlias(Certificate): String  [API 1+]
+ engineGetCertificateChain(String): Certificate[]  [API 1+]
+ engineGetCreationDate(String): Date  [API 1+]
- engineGetEntry(String, KeyStore.ProtectionParameter): KeyStore.Entry  [API 1+]
+ engineGetKey(String, char[]): Key  [API 1+]
+ engineIsCertificateEntry(String): boolean  [API 1+]
+ engineIsKeyEntry(String): boolean  [API 1+]
+ engineLoad(InputStream, char[]): void  [API 1+]
- engineLoad(KeyStore.LoadStoreParameter): void  [API 1+]
- engineProbe(InputStream): boolean  [API 33+]
+ engineSetCertificateEntry(String, Certificate): void  [API 1+]
- engineSetEntry(String, KeyStore.Entry, KeyStore.ProtectionParameter): void  [API 1+]
+ engineSetKeyEntry(String, Key, char[], Certificate[]): void  [API 1+]
+ engineSetKeyEntry(String, byte[], Certificate[]): void  [API 1+]
+ engineSize(): int  [API 1+]
- engineStore(KeyStore.LoadStoreParameter): void  [API 1+]
+ engineStore(OutputStream, char[]): void  [API 1+]
```

## MessageDigest — framework (a2w.jdk.MessageDigest); API 1+ (15 +, 0 *, 0 ~, 3 -)

```
+ MessageDigest(String)  [API 1+]
+ clone(): Object  [API 1+]
+ digest(): byte[]  [API 1+]
+ digest(byte[]): byte[]  [API 1+]
+ digest(byte[], int, int): int  [API 1+]
+ getAlgorithm(): String  [API 1+]
+ getDigestLength(): int  [API 1+]
+ getInstance(String): MessageDigest [static]  [API 1+]
- getInstance(String, Provider): MessageDigest [static]  [API 1+]
+ getInstance(String, String): MessageDigest [static]  [API 1+]
- getProvider(): Provider  [API 1+]
+ isEqual(byte[], byte[]): boolean [static]  [API 1+]
+ reset(): void  [API 1+]
+ toString(): String  [API 1+]
- update(ByteBuffer): void  [API 1+]
+ update(byte): void  [API 1+]
+ update(byte[]): void  [API 1+]
+ update(byte[], int, int): void  [API 1+]
```

## MessageDigestSpi — declaration; API 1+ (4 +, 0 *, 0 ~, 5 -)

```
- MessageDigestSpi()  [API 1+]
- clone(): Object  [API 1+]
+ engineDigest(): byte[]  [API 1+]
- engineDigest(byte[], int, int): int  [API 1+]
- engineGetDigestLength(): int  [API 1+]
+ engineReset(): void  [API 1+]
- engineUpdate(ByteBuffer): void  [API 1+]
+ engineUpdate(byte): void  [API 1+]
+ engineUpdate(byte[], int, int): void  [API 1+]
```

## NoSuchAlgorithmException — framework (a2w.jdk.NoSuchAlgorithmException); API 1+ (2 +, 0 *, 0 ~, 2 -)

```
+ NoSuchAlgorithmException()  [API 1+]
+ NoSuchAlgorithmException(String)  [API 1+]
- NoSuchAlgorithmException(String, Throwable)  [API 1+]
- NoSuchAlgorithmException(Throwable)  [API 1+]
```

## NoSuchProviderException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NoSuchProviderException()  [API 1+]
+ NoSuchProviderException(String)  [API 1+]
```

## PKCS12Attribute — declaration; API 26+ (0 +, 8 *, 0 ~, 0 -)

```
* PKCS12Attribute(String, String)  [API 26+]
* PKCS12Attribute(byte[])  [API 26+]
* equals(Object): boolean  [API 26+]
* getEncoded(): byte[]  [API 26+]
* getName(): String  [API 26+]
* getValue(): String  [API 26+]
* hashCode(): int  [API 26+]
* toString(): String  [API 26+]
```

## Permission — TeaVM class library; API 1+ (5 +, 0 *, 0 ~, 1 -)

```
+ Permission(String)  [API 1+]
+ checkGuard(Object): void  [API 1+]
+ getActions(): String  [API 1+]
+ getName(): String  [API 1+]
+ implies(Permission): boolean  [API 1+]
- newPermissionCollection(): PermissionCollection  [API 1+]
```

## PermissionCollection — declaration; API 1+ (3 +, 0 *, 0 ~, 3 -)

```
- PermissionCollection()  [API 1+]
+ add(Permission): void  [API 1+]
+ elements(): Enumeration  [API 1+]
+ implies(Permission): boolean  [API 1+]
- isReadOnly(): boolean  [API 1+]
- setReadOnly(): void  [API 1+]
```

## Permissions — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- Permissions()  [API 1+]
- add(Permission): void  [API 1+]
- elements(): Enumeration  [API 1+]
- implies(Permission): boolean  [API 1+]
```

## Policy — declaration; API 1+ (0 +, 0 *, 0 ~, 13 -)

```
- Policy()  [API 1+]
- UNSUPPORTED_EMPTY_COLLECTION [field]  [API 9+]
- getInstance(String, Policy.Parameters): Policy [static]  [API 9+]
- getInstance(String, Policy.Parameters, Provider): Policy [static]  [API 9+]
- getInstance(String, Policy.Parameters, String): Policy [static]  [API 9+]
- getParameters(): Policy.Parameters  [API 9+]
- getPermissions(CodeSource): PermissionCollection  [API 1+]
- getPermissions(ProtectionDomain): PermissionCollection  [API 1+]
- getPolicy(): Policy [static]  [API 1+]
- getProvider(): Provider  [API 9+]
- getType(): String  [API 9+]
- implies(ProtectionDomain, Permission): boolean  [API 1+]
- refresh(): void  [API 1+]
- setPolicy(Policy): void [static]  [API 1+]
```

## Policy.Parameters — declaration; API 9+ (0 +, 0 *, 0 ~, 0 -)

## PolicySpi — declaration; API 9+ (1 +, 0 *, 0 ~, 4 -)

```
- PolicySpi()  [API 9+]
- engineGetPermissions(CodeSource): PermissionCollection  [API 9+]
- engineGetPermissions(ProtectionDomain): PermissionCollection  [API 9+]
+ engineImplies(ProtectionDomain, Permission): boolean  [API 9+]
- engineRefresh(): void  [API 9+]
```

## Principal — declaration; API 1+ (4 +, 0 *, 0 ~, 1 -)

```
+ equals(Object): boolean  [API 1+]
+ getName(): String  [API 1+]
+ hashCode(): int  [API 1+]
- implies(Subject): boolean  [API 26+]
+ toString(): String  [API 1+]
```

## PrivateKey — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## PrivilegedAction — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ run(): Object  [API 1+]
```

## PrivilegedActionException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 2 -)

```
- PrivilegedActionException(Exception)  [API 1+]
+ getCause(): Throwable  [API 1+]
- getException(): Exception  [API 1+]
+ toString(): String  [API 1+]
```

## PrivilegedExceptionAction — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ run(): Object  [API 1+]
```

## ProtectionDomain — declaration; API 1+ (0 +, 6 *, 0 ~, 1 -)

```
* ProtectionDomain(CodeSource, PermissionCollection)  [API 1+]
* ProtectionDomain(CodeSource, PermissionCollection, ClassLoader, Principal[])  [API 1+]
* getClassLoader(): ClassLoader  [API 1+]
* getCodeSource(): CodeSource  [API 1+]
* getPermissions(): PermissionCollection  [API 1+]
* getPrincipals(): Principal[]  [API 1+]
- implies(Permission): boolean  [API 1+]
```

## Provider — declaration; API 1+ (0 +, 0 *, 0 ~, 32 -)

```
- Provider(String, double, String)  [API 1+]
- clear(): void  [API 1+]
- compute(Object, BiFunction): Object  [API 1+]
- computeIfAbsent(Object, Function): Object  [API 1+]
- computeIfPresent(Object, BiFunction): Object  [API 1+]
- elements(): Enumeration  [API 1+]
- entrySet(): Set  [API 1+]
- forEach(BiConsumer): void  [API 1+]
- get(Object): Object  [API 1+]
- getInfo(): String  [API 1+]
- getName(): String  [API 1+]
- getOrDefault(Object, Object): Object  [API 1+]
- getProperty(String): String  [API 1+]
- getService(String, String): Provider.Service  [API 1+]
- getServices(): Set  [API 1+]
- getVersion(): double  [API 1+]
- keySet(): Set  [API 1+]
- keys(): Enumeration  [API 1+]
- load(InputStream): void  [API 1+]
- merge(Object, Object, BiFunction): Object  [API 1+]
- put(Object, Object): Object  [API 1+]
- putAll(Map): void  [API 1+]
- putIfAbsent(Object, Object): Object  [API 1+]
- putService(Provider.Service): void  [API 1+]
- remove(Object): Object  [API 1+]
- remove(Object, Object): boolean  [API 1+]
- removeService(Provider.Service): void  [API 1+]
- replace(Object, Object): Object  [API 1+]
- replace(Object, Object, Object): boolean  [API 1+]
- replaceAll(BiFunction): void  [API 1+]
- toString(): String  [API 1+]
- values(): Collection  [API 1+]
```

## Provider.Service — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- Service(Provider, String, String, String, List, Map)  [API 1+]
- getAlgorithm(): String  [API 1+]
- getAttribute(String): String  [API 1+]
- getClassName(): String  [API 1+]
- getProvider(): Provider  [API 1+]
- getType(): String  [API 1+]
- newInstance(Object): Object  [API 1+]
- supportsParameter(Object): boolean  [API 1+]
- toString(): String  [API 1+]
```

## ProviderException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ ProviderException()  [API 1+]
+ ProviderException(String)  [API 1+]
+ ProviderException(String, Throwable)  [API 1+]
+ ProviderException(Throwable)  [API 1+]
```

## PublicKey — declaration; API 1+ (0 +, 0 *, 0 ~, 0 -)

## SecureClassLoader — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- SecureClassLoader()  [API 1+]
- SecureClassLoader(ClassLoader)  [API 1+]
- defineClass(String, ByteBuffer, CodeSource): Class  [API 1+]
- defineClass(String, byte[], int, int, CodeSource): Class  [API 1+]
- getPermissions(CodeSource): PermissionCollection  [API 1+]
```

## SecureRandom — TeaVM class library; API 1+ (12 +, 0 *, 0 ~, 11 -)

```
+ SecureRandom()  [API 1+]
- SecureRandom(SecureRandomSpi, Provider)  [API 1+]
+ SecureRandom(byte[])  [API 1+]
+ generateSeed(int): byte[]  [API 1+]
+ getAlgorithm(): String  [API 1+]
+ getInstance(String): SecureRandom [static]  [API 1+]
- getInstance(String, Provider): SecureRandom [static]  [API 1+]
- getInstance(String, SecureRandomParameters): SecureRandom [static]  [API 35+]
- getInstance(String, SecureRandomParameters, Provider): SecureRandom [static]  [API 35+]
- getInstance(String, SecureRandomParameters, String): SecureRandom [static]  [API 35+]
- getInstance(String, String): SecureRandom [static]  [API 1+]
- getInstanceStrong(): SecureRandom [static]  [API 26+]
- getParameters(): SecureRandomParameters  [API 35+]
- getProvider(): Provider  [API 1+]
+ getSeed(int): byte[] [static]  [API 1+]
+ next(int): int  [API 1+]
+ nextBytes(byte[]): void  [API 1+]
- nextBytes(byte[], SecureRandomParameters): void  [API 35+]
+ reseed(): void  [API 35+]
- reseed(SecureRandomParameters): void  [API 35+]
+ setSeed(byte[]): void  [API 1+]
+ setSeed(long): void  [API 1+]
+ toString(): String  [API 1+]
```

## SecureRandomParameters — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## SecureRandomSpi — declaration; API 1+ (3 +, 0 *, 0 ~, 6 -)

```
- SecureRandomSpi()  [API 1+]
- SecureRandomSpi(SecureRandomParameters)  [API 35+]
+ engineGenerateSeed(int): byte[]  [API 1+]
- engineGetParameters(): SecureRandomParameters  [API 35+]
+ engineNextBytes(byte[]): void  [API 1+]
- engineNextBytes(byte[], SecureRandomParameters): void  [API 35+]
- engineReseed(SecureRandomParameters): void  [API 35+]
+ engineSetSeed(byte[]): void  [API 1+]
- toString(): String  [API 1+]
```

## Security — declaration; API 1+ (0 +, 0 *, 0 ~, 11 -)

```
- addProvider(Provider): int [static]  [API 1+]
- getAlgorithmProperty(String, String): String [static]  [API 1+, deprecated in 15]
- getAlgorithms(String): Set [static]  [API 1+]
- getProperty(String): String [static]  [API 1+]
- getProvider(String): Provider [static]  [API 1+]
- getProviders(): Provider[] [static]  [API 1+]
- getProviders(Map): Provider[] [static]  [API 1+]
- getProviders(String): Provider[] [static]  [API 1+]
- insertProviderAt(Provider, int): int [static]  [API 1+]
- removeProvider(String): void [static]  [API 1+]
- setProperty(String, String): void [static]  [API 1+]
```

## SecurityPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- SecurityPermission(String)  [API 1+]
- SecurityPermission(String, String)  [API 1+]
```

## Signature — declaration; API 1+ (0 +, 2 *, 0 ~, 22 -)

```
* Signature(String)  [API 1+]
- clone(): Object  [API 1+]
* getAlgorithm(): String  [API 1+]
- getInstance(String): Signature [static]  [API 1+]
- getInstance(String, Provider): Signature [static]  [API 1+]
- getInstance(String, String): Signature [static]  [API 1+]
- getParameter(String): Object  [API 1+, deprecated in 15]
- getParameters(): AlgorithmParameters  [API 1+]
- getProvider(): Provider  [API 1+]
- initSign(PrivateKey): void  [API 1+]
- initSign(PrivateKey, SecureRandom): void  [API 1+]
- initVerify(Certificate): void  [API 1+]
- initVerify(PublicKey): void  [API 1+]
- setParameter(AlgorithmParameterSpec): void  [API 1+]
- setParameter(String, Object): void  [API 1+, deprecated in 15]
- sign(): byte[]  [API 1+]
- sign(byte[], int, int): int  [API 1+]
+ state [field]  [API 1+]
- toString(): String  [API 1+]
- update(ByteBuffer): void  [API 1+]
- update(byte): void  [API 1+]
- update(byte[]): void  [API 1+]
- update(byte[], int, int): void  [API 1+]
- verify(byte[]): boolean  [API 1+]
- verify(byte[], int, int): boolean  [API 1+]
```

## SignatureException — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ SignatureException()  [API 1+]
+ SignatureException(String)  [API 1+]
+ SignatureException(String, Throwable)  [API 1+]
+ SignatureException(Throwable)  [API 1+]
```

## SignatureSpi — declaration; API 1+ (8 +, 0 *, 0 ~, 8 -)

```
- SignatureSpi()  [API 1+]
+ appRandom [field]  [API 1+]
- clone(): Object  [API 1+]
+ engineGetParameter(String): Object  [API 1+, deprecated in 15]
- engineGetParameters(): AlgorithmParameters  [API 1+]
+ engineInitSign(PrivateKey): void  [API 1+]
- engineInitSign(PrivateKey, SecureRandom): void  [API 1+]
+ engineInitVerify(PublicKey): void  [API 1+]
- engineSetParameter(AlgorithmParameterSpec): void  [API 1+]
+ engineSetParameter(String, Object): void  [API 1+, deprecated in 15]
+ engineSign(): byte[]  [API 1+]
- engineSign(byte[], int, int): int  [API 1+]
- engineUpdate(ByteBuffer): void  [API 1+]
+ engineUpdate(byte): void  [API 1+]
+ engineUpdate(byte[], int, int): void  [API 1+]
+ engineVerify(byte[]): boolean  [API 1+]
- engineVerify(byte[], int, int): boolean  [API 1+]
```

## SignedObject — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- SignedObject(Serializable, PrivateKey, Signature)  [API 1+]
- getAlgorithm(): String  [API 1+]
- getObject(): Object  [API 1+]
- getSignature(): byte[]  [API 1+]
- verify(PublicKey, Signature): boolean  [API 1+]
```

## Signer — declaration; API 1+, deprecated in 3 (0 +, 2 *, 0 ~, 4 -)

```
- Signer()  [API 1+]
* Signer(String)  [API 1+]
* Signer(String, IdentityScope)  [API 1+]
- getPrivateKey(): PrivateKey  [API 1+]
- setKeyPair(KeyPair): void  [API 1+]
- toString(): String  [API 1+]
```

## Timestamp — declaration; API 1+ (0 +, 6 *, 0 ~, 0 -)

```
* Timestamp(Date, CertPath)  [API 1+]
* equals(Object): boolean  [API 1+]
* getSignerCertPath(): CertPath  [API 1+]
* getTimestamp(): Date  [API 1+]
* hashCode(): int  [API 1+]
* toString(): String  [API 1+]
```

## UnrecoverableEntryException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnrecoverableEntryException()  [API 1+]
+ UnrecoverableEntryException(String)  [API 1+]
```

## UnrecoverableKeyException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnrecoverableKeyException()  [API 1+]
+ UnrecoverableKeyException(String)  [API 1+]
```

## UnresolvedPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 7 -)

```
- UnresolvedPermission(String, String, String, Certificate[])  [API 1+]
- getActions(): String  [API 1+]
- getUnresolvedActions(): String  [API 1+]
- getUnresolvedCerts(): Certificate[]  [API 1+]
- getUnresolvedName(): String  [API 1+]
- getUnresolvedType(): String  [API 1+]
- implies(Permission): boolean  [API 1+]
```


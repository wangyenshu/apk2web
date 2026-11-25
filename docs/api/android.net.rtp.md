# android.net.rtp

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AudioCodec — declaration; API 12+, deprecated in 31 (0 +, 0 *, 0 ~, 2 -)

```
- AMR [field]  [API 12+]
- GSM [field]  [API 12+]
- GSM_EFR [field]  [API 12+]
- PCMA [field]  [API 12+]
- PCMU [field]  [API 12+]
- fmtp [field]  [API 12+]
- getCodec(int, String, String): AudioCodec [static]  [API 12+]
- getCodecs(): AudioCodec[] [static]  [API 12+]
- rtpmap [field]  [API 12+]
- type [field]  [API 12+]
```

## AudioGroup — declaration; API 12+, deprecated in 31 (0 +, 3 *, 0 ~, 5 -)

```
* AudioGroup()  [API 12+, deprecated in 30]
- AudioGroup(Context)  [API 30+]
- clear(): void  [API 12+]
- finalize(): void  [API 12+]
* getMode(): int  [API 12+]
- getStreams(): AudioStream[]  [API 12+]
- sendDtmf(int): void  [API 12+]
* setMode(int): void  [API 12+]
```

## AudioStream — declaration; API 12+, deprecated in 31 (0 +, 4 *, 0 ~, 4 -)

```
- AudioStream(InetAddress)  [API 12+]
* getCodec(): AudioCodec  [API 12+]
* getDtmfType(): int  [API 12+]
- getGroup(): AudioGroup  [API 12+]
- isBusy(): boolean  [API 12+]
- join(AudioGroup): void  [API 12+]
* setCodec(AudioCodec): void  [API 12+]
* setDtmfType(int): void  [API 12+]
```

## RtpStream — declaration; API 12+, deprecated in 31 (0 +, 2 *, 0 ~, 8 -)

```
- associate(InetAddress, int): void  [API 12+]
- finalize(): void  [API 12+]
- getLocalAddress(): InetAddress  [API 12+]
- getLocalPort(): int  [API 12+]
* getMode(): int  [API 12+]
- getRemoteAddress(): InetAddress  [API 12+]
- getRemotePort(): int  [API 12+]
- isBusy(): boolean  [API 12+]
- release(): void  [API 12+]
* setMode(int): void  [API 12+]
```


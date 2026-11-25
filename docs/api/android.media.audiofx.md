# android.media.audiofx

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AcousticEchoCanceler — declaration; API 16+ (0 +, 0 *, 0 ~, 2 -)

```
- create(int): AcousticEchoCanceler [static]  [API 16+]
- isAvailable(): boolean [static]  [API 16+]
```

## AudioEffect — declaration; API 9+ (0 +, 0 *, 0 ~, 10 -)

```
- EFFECT_TYPE_AEC [field]  [API 18+]
- EFFECT_TYPE_AGC [field]  [API 18+]
- EFFECT_TYPE_BASS_BOOST [field]  [API 18+]
- EFFECT_TYPE_DYNAMICS_PROCESSING [field]  [API 28+]
- EFFECT_TYPE_ENV_REVERB [field]  [API 18+]
- EFFECT_TYPE_EQUALIZER [field]  [API 18+]
- EFFECT_TYPE_HAPTIC_GENERATOR [field]  [API 31+]
- EFFECT_TYPE_LOUDNESS_ENHANCER [field]  [API 19+]
- EFFECT_TYPE_NS [field]  [API 18+]
- EFFECT_TYPE_PRESET_REVERB [field]  [API 18+]
- EFFECT_TYPE_VIRTUALIZER [field]  [API 18+]
- finalize(): void  [API 9+]
- getDescriptor(): AudioEffect.Descriptor  [API 9+]
- getEnabled(): boolean  [API 9+]
- getId(): int  [API 9+]
- hasControl(): boolean  [API 9+]
- queryEffects(): AudioEffect.Descriptor[] [static]  [API 9+]
- release(): void  [API 9+]
- setControlStatusListener(AudioEffect.OnControlStatusChangeListener): void  [API 9+]
- setEnableStatusListener(AudioEffect.OnEnableStatusChangeListener): void  [API 9+]
- setEnabled(boolean): int  [API 9+]
```

## AudioEffect.Descriptor — declaration; API 9+ (0 +, 0 *, 0 ~, 4 -)

```
- Descriptor()  [API 9+]
- Descriptor(String, String, String, String, String)  [API 9+]
+ connectMode [field]  [API 9+]
- equals(Object): boolean  [API 9+]
- hashCode(): int  [API 9+]
+ implementor [field]  [API 9+]
+ name [field]  [API 9+]
+ type [field]  [API 9+]
+ uuid [field]  [API 9+]
```

## AudioEffect.OnControlStatusChangeListener — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ onControlStatusChange(AudioEffect, boolean): void  [API 9+]
```

## AudioEffect.OnEnableStatusChangeListener — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ onEnableStatusChange(AudioEffect, boolean): void  [API 9+]
```

## AutomaticGainControl — declaration; API 16+ (0 +, 0 *, 0 ~, 2 -)

```
- create(int): AutomaticGainControl [static]  [API 16+]
- isAvailable(): boolean [static]  [API 16+]
```

## BassBoost — declaration; API 9+ (0 +, 2 *, 0 ~, 5 -)

```
- BassBoost(int, int)  [API 9+]
* getProperties(): BassBoost.Settings  [API 9+]
- getRoundedStrength(): short  [API 9+]
- getStrengthSupported(): boolean  [API 9+]
- setParameterListener(BassBoost.OnParameterChangeListener): void  [API 9+]
* setProperties(BassBoost.Settings): void  [API 9+]
- setStrength(short): void  [API 9+]
```

## BassBoost.OnParameterChangeListener — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ onParameterChange(BassBoost, int, int, short): void  [API 9+]
```

## BassBoost.Settings — declaration; API 9+ (0 +, 0 *, 0 ~, 3 -)

```
- Settings()  [API 9+]
- Settings(String)  [API 9+]
+ strength [field]  [API 9+]
- toString(): String  [API 9+]
```

## DynamicsProcessing — declaration; API 28+ (0 +, 0 *, 0 ~, 31 -)

```
- DynamicsProcessing(int)  [API 28+]
- DynamicsProcessing(int, int, DynamicsProcessing.Config)  [API 28+]
- getChannelByChannelIndex(int): DynamicsProcessing.Channel  [API 28+]
- getChannelCount(): int  [API 28+]
- getConfig(): DynamicsProcessing.Config  [API 28+]
- getInputGainByChannelIndex(int): float  [API 28+]
- getLimiterByChannelIndex(int): DynamicsProcessing.Limiter  [API 28+]
- getMbcBandByChannelIndex(int, int): DynamicsProcessing.MbcBand  [API 28+]
- getMbcByChannelIndex(int): DynamicsProcessing.Mbc  [API 28+]
- getPostEqBandByChannelIndex(int, int): DynamicsProcessing.EqBand  [API 28+]
- getPostEqByChannelIndex(int): DynamicsProcessing.Eq  [API 28+]
- getPreEqBandByChannelIndex(int, int): DynamicsProcessing.EqBand  [API 28+]
- getPreEqByChannelIndex(int): DynamicsProcessing.Eq  [API 28+]
- setAllChannelsTo(DynamicsProcessing.Channel): void  [API 28+]
- setChannelTo(int, DynamicsProcessing.Channel): void  [API 28+]
- setInputGainAllChannelsTo(float): void  [API 28+]
- setInputGainbyChannel(int, float): void  [API 28+]
- setLimiterAllChannelsTo(DynamicsProcessing.Limiter): void  [API 28+]
- setLimiterByChannelIndex(int, DynamicsProcessing.Limiter): void  [API 28+]
- setMbcAllChannelsTo(DynamicsProcessing.Mbc): void  [API 28+]
- setMbcBandAllChannelsTo(int, DynamicsProcessing.MbcBand): void  [API 28+]
- setMbcBandByChannelIndex(int, int, DynamicsProcessing.MbcBand): void  [API 28+]
- setMbcByChannelIndex(int, DynamicsProcessing.Mbc): void  [API 28+]
- setPostEqAllChannelsTo(DynamicsProcessing.Eq): void  [API 28+]
- setPostEqBandAllChannelsTo(int, DynamicsProcessing.EqBand): void  [API 28+]
- setPostEqBandByChannelIndex(int, int, DynamicsProcessing.EqBand): void  [API 28+]
- setPostEqByChannelIndex(int, DynamicsProcessing.Eq): void  [API 28+]
- setPreEqAllChannelsTo(DynamicsProcessing.Eq): void  [API 28+]
- setPreEqBandAllChannelsTo(int, DynamicsProcessing.EqBand): void  [API 28+]
- setPreEqBandByChannelIndex(int, int, DynamicsProcessing.EqBand): void  [API 28+]
- setPreEqByChannelIndex(int, DynamicsProcessing.Eq): void  [API 28+]
```

## DynamicsProcessing.BandBase — declaration; API 28+ (0 +, 6 *, 0 ~, 0 -)

```
* BandBase(boolean, float)  [API 28+]
* getCutoffFrequency(): float  [API 28+]
* isEnabled(): boolean  [API 28+]
* setCutoffFrequency(float): void  [API 28+]
* setEnabled(boolean): void  [API 28+]
* toString(): String  [API 28+]
```

## DynamicsProcessing.BandStage — declaration; API 28+ (0 +, 3 *, 0 ~, 0 -)

```
* BandStage(boolean, boolean, int)  [API 28+]
* getBandCount(): int  [API 28+]
* toString(): String  [API 28+]
```

## DynamicsProcessing.Channel — declaration; API 28+ (0 +, 11 *, 0 ~, 8 -)

```
- Channel(DynamicsProcessing.Channel)  [API 28+]
- Channel(float, boolean, int, boolean, int, boolean, int, boolean)  [API 28+]
* getInputGain(): float  [API 28+]
* getLimiter(): DynamicsProcessing.Limiter  [API 28+]
* getMbc(): DynamicsProcessing.Mbc  [API 28+]
- getMbcBand(int): DynamicsProcessing.MbcBand  [API 28+]
* getPostEq(): DynamicsProcessing.Eq  [API 28+]
- getPostEqBand(int): DynamicsProcessing.EqBand  [API 28+]
* getPreEq(): DynamicsProcessing.Eq  [API 28+]
- getPreEqBand(int): DynamicsProcessing.EqBand  [API 28+]
* setInputGain(float): void  [API 28+]
* setLimiter(DynamicsProcessing.Limiter): void  [API 28+]
* setMbc(DynamicsProcessing.Mbc): void  [API 28+]
- setMbcBand(int, DynamicsProcessing.MbcBand): void  [API 28+]
* setPostEq(DynamicsProcessing.Eq): void  [API 28+]
- setPostEqBand(int, DynamicsProcessing.EqBand): void  [API 28+]
* setPreEq(DynamicsProcessing.Eq): void  [API 28+]
- setPreEqBand(int, DynamicsProcessing.EqBand): void  [API 28+]
* toString(): String  [API 28+]
```

## DynamicsProcessing.Config — declaration; API 28+ (0 +, 2 *, 0 ~, 35 -)

```
- getChannelByChannelIndex(int): DynamicsProcessing.Channel  [API 28+]
- getInputGainByChannelIndex(int): float  [API 28+]
- getLimiterByChannelIndex(int): DynamicsProcessing.Limiter  [API 28+]
- getMbcBandByChannelIndex(int, int): DynamicsProcessing.MbcBand  [API 28+]
- getMbcBandCount(): int  [API 28+]
- getMbcByChannelIndex(int): DynamicsProcessing.Mbc  [API 28+]
- getPostEqBandByChannelIndex(int, int): DynamicsProcessing.EqBand  [API 28+]
- getPostEqBandCount(): int  [API 28+]
- getPostEqByChannelIndex(int): DynamicsProcessing.Eq  [API 28+]
- getPreEqBandByChannelIndex(int, int): DynamicsProcessing.EqBand  [API 28+]
- getPreEqBandCount(): int  [API 28+]
- getPreEqByChannelIndex(int): DynamicsProcessing.Eq  [API 28+]
* getPreferredFrameDuration(): float  [API 28+]
* getVariant(): int  [API 28+]
- isLimiterInUse(): boolean  [API 28+]
- isMbcInUse(): boolean  [API 28+]
- isPostEqInUse(): boolean  [API 28+]
- isPreEqInUse(): boolean  [API 28+]
- setAllChannelsTo(DynamicsProcessing.Channel): void  [API 28+]
- setChannelTo(int, DynamicsProcessing.Channel): void  [API 28+]
- setInputGainAllChannelsTo(float): void  [API 28+]
- setInputGainByChannelIndex(int, float): void  [API 28+]
- setLimiterAllChannelsTo(DynamicsProcessing.Limiter): void  [API 28+]
- setLimiterByChannelIndex(int, DynamicsProcessing.Limiter): void  [API 28+]
- setMbcAllChannelsTo(DynamicsProcessing.Mbc): void  [API 28+]
- setMbcBandAllChannelsTo(int, DynamicsProcessing.MbcBand): void  [API 28+]
- setMbcBandByChannelIndex(int, int, DynamicsProcessing.MbcBand): void  [API 28+]
- setMbcByChannelIndex(int, DynamicsProcessing.Mbc): void  [API 28+]
- setPostEqAllChannelsTo(DynamicsProcessing.Eq): void  [API 28+]
- setPostEqBandAllChannelsTo(int, DynamicsProcessing.EqBand): void  [API 28+]
- setPostEqBandByChannelIndex(int, int, DynamicsProcessing.EqBand): void  [API 28+]
- setPostEqByChannelIndex(int, DynamicsProcessing.Eq): void  [API 28+]
- setPreEqAllChannelsTo(DynamicsProcessing.Eq): void  [API 28+]
- setPreEqBandAllChannelsTo(int, DynamicsProcessing.EqBand): void  [API 28+]
- setPreEqBandByChannelIndex(int, int, DynamicsProcessing.EqBand): void  [API 28+]
- setPreEqByChannelIndex(int, DynamicsProcessing.Eq): void  [API 28+]
- toString(): String  [API 28+]
```

## DynamicsProcessing.Config.Builder — declaration; API 28+ (0 +, 2 *, 0 ~, 13 -)

```
- Builder(int, int, boolean, int, boolean, int, boolean, int, boolean)  [API 28+]
* build(): DynamicsProcessing.Config  [API 28+]
- setAllChannelsTo(DynamicsProcessing.Channel): DynamicsProcessing.Config.Builder  [API 28+]
- setChannelTo(int, DynamicsProcessing.Channel): DynamicsProcessing.Config.Builder  [API 28+]
- setInputGainAllChannelsTo(float): DynamicsProcessing.Config.Builder  [API 28+]
- setInputGainByChannelIndex(int, float): DynamicsProcessing.Config.Builder  [API 28+]
- setLimiterAllChannelsTo(DynamicsProcessing.Limiter): DynamicsProcessing.Config.Builder  [API 28+]
- setLimiterByChannelIndex(int, DynamicsProcessing.Limiter): DynamicsProcessing.Config.Builder  [API 28+]
- setMbcAllChannelsTo(DynamicsProcessing.Mbc): DynamicsProcessing.Config.Builder  [API 28+]
- setMbcByChannelIndex(int, DynamicsProcessing.Mbc): DynamicsProcessing.Config.Builder  [API 28+]
- setPostEqAllChannelsTo(DynamicsProcessing.Eq): DynamicsProcessing.Config.Builder  [API 28+]
- setPostEqByChannelIndex(int, DynamicsProcessing.Eq): DynamicsProcessing.Config.Builder  [API 28+]
- setPreEqAllChannelsTo(DynamicsProcessing.Eq): DynamicsProcessing.Config.Builder  [API 28+]
- setPreEqByChannelIndex(int, DynamicsProcessing.Eq): DynamicsProcessing.Config.Builder  [API 28+]
* setPreferredFrameDuration(float): DynamicsProcessing.Config.Builder  [API 28+]
```

## DynamicsProcessing.Eq — declaration; API 28+ (0 +, 2 *, 0 ~, 3 -)

```
- Eq(DynamicsProcessing.Eq)  [API 28+]
* Eq(boolean, boolean, int)  [API 28+]
- getBand(int): DynamicsProcessing.EqBand  [API 28+]
- setBand(int, DynamicsProcessing.EqBand): void  [API 28+]
* toString(): String  [API 28+]
```

## DynamicsProcessing.EqBand — declaration; API 28+ (0 +, 4 *, 0 ~, 1 -)

```
- EqBand(DynamicsProcessing.EqBand)  [API 28+]
* EqBand(boolean, float, float)  [API 28+]
* getGain(): float  [API 28+]
* setGain(float): void  [API 28+]
* toString(): String  [API 28+]
```

## DynamicsProcessing.Limiter — declaration; API 28+ (0 +, 14 *, 0 ~, 1 -)

```
- Limiter(DynamicsProcessing.Limiter)  [API 28+]
* Limiter(boolean, boolean, int, float, float, float, float, float)  [API 28+]
* getAttackTime(): float  [API 28+]
* getLinkGroup(): int  [API 28+]
* getPostGain(): float  [API 28+]
* getRatio(): float  [API 28+]
* getReleaseTime(): float  [API 28+]
* getThreshold(): float  [API 28+]
* setAttackTime(float): void  [API 28+]
* setLinkGroup(int): void  [API 28+]
* setPostGain(float): void  [API 28+]
* setRatio(float): void  [API 28+]
* setReleaseTime(float): void  [API 28+]
* setThreshold(float): void  [API 28+]
* toString(): String  [API 28+]
```

## DynamicsProcessing.Mbc — declaration; API 28+ (0 +, 2 *, 0 ~, 3 -)

```
- Mbc(DynamicsProcessing.Mbc)  [API 28+]
* Mbc(boolean, boolean, int)  [API 28+]
- getBand(int): DynamicsProcessing.MbcBand  [API 28+]
- setBand(int, DynamicsProcessing.MbcBand): void  [API 28+]
* toString(): String  [API 28+]
```

## DynamicsProcessing.MbcBand — declaration; API 28+ (0 +, 20 *, 0 ~, 1 -)

```
- MbcBand(DynamicsProcessing.MbcBand)  [API 28+]
* MbcBand(boolean, float, float, float, float, float, float, float, float, float, float)  [API 28+]
* getAttackTime(): float  [API 28+]
* getExpanderRatio(): float  [API 28+]
* getKneeWidth(): float  [API 28+]
* getNoiseGateThreshold(): float  [API 28+]
* getPostGain(): float  [API 28+]
* getPreGain(): float  [API 28+]
* getRatio(): float  [API 28+]
* getReleaseTime(): float  [API 28+]
* getThreshold(): float  [API 28+]
* setAttackTime(float): void  [API 28+]
* setExpanderRatio(float): void  [API 28+]
* setKneeWidth(float): void  [API 28+]
* setNoiseGateThreshold(float): void  [API 28+]
* setPostGain(float): void  [API 28+]
* setPreGain(float): void  [API 28+]
* setRatio(float): void  [API 28+]
* setReleaseTime(float): void  [API 28+]
* setThreshold(float): void  [API 28+]
* toString(): String  [API 28+]
```

## DynamicsProcessing.Stage — declaration; API 28+ (0 +, 5 *, 0 ~, 0 -)

```
* Stage(boolean, boolean)  [API 28+]
* isEnabled(): boolean  [API 28+]
* isInUse(): boolean  [API 28+]
* setEnabled(boolean): void  [API 28+]
* toString(): String  [API 28+]
```

## EnvironmentalReverb — declaration; API 9+ (0 +, 22 *, 0 ~, 2 -)

```
- EnvironmentalReverb(int, int)  [API 9+]
* getDecayHFRatio(): short  [API 9+]
* getDecayTime(): int  [API 9+]
* getDensity(): short  [API 9+]
* getDiffusion(): short  [API 9+]
* getProperties(): EnvironmentalReverb.Settings  [API 9+]
* getReflectionsDelay(): int  [API 9+]
* getReflectionsLevel(): short  [API 9+]
* getReverbDelay(): int  [API 9+]
* getReverbLevel(): short  [API 9+]
* getRoomHFLevel(): short  [API 9+]
* getRoomLevel(): short  [API 9+]
* setDecayHFRatio(short): void  [API 9+]
* setDecayTime(int): void  [API 9+]
* setDensity(short): void  [API 9+]
* setDiffusion(short): void  [API 9+]
- setParameterListener(EnvironmentalReverb.OnParameterChangeListener): void  [API 9+]
* setProperties(EnvironmentalReverb.Settings): void  [API 9+]
* setReflectionsDelay(int): void  [API 9+]
* setReflectionsLevel(short): void  [API 9+]
* setReverbDelay(int): void  [API 9+]
* setReverbLevel(short): void  [API 9+]
* setRoomHFLevel(short): void  [API 9+]
* setRoomLevel(short): void  [API 9+]
```

## EnvironmentalReverb.OnParameterChangeListener — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ onParameterChange(EnvironmentalReverb, int, int, int): void  [API 9+]
```

## EnvironmentalReverb.Settings — declaration; API 9+ (0 +, 0 *, 0 ~, 3 -)

```
- Settings()  [API 9+]
- Settings(String)  [API 9+]
+ decayHFRatio [field]  [API 9+]
+ decayTime [field]  [API 9+]
+ density [field]  [API 9+]
+ diffusion [field]  [API 9+]
+ reflectionsDelay [field]  [API 9+]
+ reflectionsLevel [field]  [API 9+]
+ reverbDelay [field]  [API 9+]
+ reverbLevel [field]  [API 9+]
+ roomHFLevel [field]  [API 9+]
+ roomLevel [field]  [API 9+]
- toString(): String  [API 9+]
```

## Equalizer — declaration; API 9+ (0 +, 2 *, 0 ~, 13 -)

```
- Equalizer(int, int)  [API 9+]
- getBand(int): short  [API 9+]
- getBandFreqRange(short): int[]  [API 9+]
- getBandLevel(short): short  [API 9+]
- getBandLevelRange(): short[]  [API 9+]
- getCenterFreq(short): int  [API 9+]
- getCurrentPreset(): short  [API 9+]
- getNumberOfBands(): short  [API 9+]
- getNumberOfPresets(): short  [API 9+]
- getPresetName(short): String  [API 9+]
* getProperties(): Equalizer.Settings  [API 9+]
- setBandLevel(short, short): void  [API 9+]
- setParameterListener(Equalizer.OnParameterChangeListener): void  [API 9+]
* setProperties(Equalizer.Settings): void  [API 9+]
- usePreset(short): void  [API 9+]
```

## Equalizer.OnParameterChangeListener — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ onParameterChange(Equalizer, int, int, int, int): void  [API 9+]
```

## Equalizer.Settings — declaration; API 9+ (0 +, 0 *, 0 ~, 3 -)

```
- Settings()  [API 9+]
- Settings(String)  [API 9+]
+ bandLevels [field]  [API 9+]
+ curPreset [field]  [API 9+]
+ numBands [field]  [API 9+]
- toString(): String  [API 9+]
```

## HapticGenerator — declaration; API 31+ (0 +, 0 *, 0 ~, 5 -)

```
- close(): void  [API 31+]
- create(int): HapticGenerator [static]  [API 31+]
- isAvailable(): boolean [static]  [API 31+]
- release(): void  [API 31+]
- setEnabled(boolean): int  [API 31+]
```

## LoudnessEnhancer — declaration; API 19+ (0 +, 0 *, 0 ~, 3 -)

```
- LoudnessEnhancer(int)  [API 19+]
- getTargetGain(): float  [API 19+]
- setTargetGain(int): void  [API 19+]
```

## NoiseSuppressor — declaration; API 16+ (0 +, 0 *, 0 ~, 2 -)

```
- create(int): NoiseSuppressor [static]  [API 16+]
- isAvailable(): boolean [static]  [API 16+]
```

## PresetReverb — declaration; API 9+ (0 +, 4 *, 0 ~, 2 -)

```
- PresetReverb(int, int)  [API 9+]
* getPreset(): short  [API 9+]
* getProperties(): PresetReverb.Settings  [API 9+]
- setParameterListener(PresetReverb.OnParameterChangeListener): void  [API 9+]
* setPreset(short): void  [API 9+]
* setProperties(PresetReverb.Settings): void  [API 9+]
```

## PresetReverb.OnParameterChangeListener — declaration; API 9+ (1 +, 0 *, 0 ~, 0 -)

```
+ onParameterChange(PresetReverb, int, int, short): void  [API 9+]
```

## PresetReverb.Settings — declaration; API 9+ (0 +, 0 *, 0 ~, 3 -)

```
- Settings()  [API 9+]
- Settings(String)  [API 9+]
+ preset [field]  [API 9+]
- toString(): String  [API 9+]
```

## Virtualizer — declaration; API 9+, deprecated in 35 (0 +, 2 *, 0 ~, 9 -)

```
- Virtualizer(int, int)  [API 9+]
- canVirtualize(int, int): boolean  [API 21+]
- forceVirtualizationMode(int): boolean  [API 21+]
* getProperties(): Virtualizer.Settings  [API 9+]
- getRoundedStrength(): short  [API 9+]
- getSpeakerAngles(int, int, int[]): boolean  [API 21+]
- getStrengthSupported(): boolean  [API 9+]
- getVirtualizationMode(): int  [API 21+]
- setParameterListener(Virtualizer.OnParameterChangeListener): void  [API 9+]
* setProperties(Virtualizer.Settings): void  [API 9+]
- setStrength(short): void  [API 9+]
```

## Virtualizer.OnParameterChangeListener — declaration; API 9+, deprecated in 35 (1 +, 0 *, 0 ~, 0 -)

```
+ onParameterChange(Virtualizer, int, int, short): void  [API 9+]
```

## Virtualizer.Settings — declaration; API 9+, deprecated in 35 (0 +, 0 *, 0 ~, 3 -)

```
- Settings()  [API 9+]
- Settings(String)  [API 9+]
+ strength [field]  [API 9+]
- toString(): String  [API 9+]
```

## Visualizer — declaration; API 9+ (0 +, 0 *, 0 ~, 18 -)

```
- Visualizer(int)  [API 9+]
- finalize(): void  [API 9+]
- getCaptureSize(): int  [API 9+]
- getCaptureSizeRange(): int[] [static]  [API 9+]
- getEnabled(): boolean  [API 9+]
- getFft(byte[]): int  [API 9+]
- getMaxCaptureRate(): int [static]  [API 9+]
- getMeasurementMode(): int  [API 19+]
- getMeasurementPeakRms(Visualizer.MeasurementPeakRms): int  [API 19+]
- getSamplingRate(): int  [API 9+]
- getScalingMode(): int  [API 16+]
- getWaveForm(byte[]): int  [API 9+]
- release(): void  [API 9+]
- setCaptureSize(int): int  [API 9+]
- setDataCaptureListener(Visualizer.OnDataCaptureListener, int, boolean, boolean): int  [API 9+]
- setEnabled(boolean): int  [API 9+]
- setMeasurementMode(int): int  [API 19+]
- setScalingMode(int): int  [API 16+]
```

## Visualizer.MeasurementPeakRms — declaration; API 19+ (1 +, 0 *, 0 ~, 0 -)

```
+ MeasurementPeakRms()  [API 19+]
+ mPeak [field]  [API 19+]
+ mRms [field]  [API 19+]
```

## Visualizer.OnDataCaptureListener — declaration; API 9+ (2 +, 0 *, 0 ~, 0 -)

```
+ onFftDataCapture(Visualizer, byte[], int): void  [API 9+]
+ onWaveFormDataCapture(Visualizer, byte[], int): void  [API 9+]
```


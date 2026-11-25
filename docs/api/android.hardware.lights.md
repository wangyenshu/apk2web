# android.hardware.lights

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Light — declaration; API 31+ (11 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 31+]
+ describeContents(): int  [API 31+]
+ equals(Object): boolean  [API 31+]
+ getId(): int  [API 31+]
+ getName(): String  [API 31+]
+ getOrdinal(): int  [API 31+]
+ getType(): int  [API 31+]
+ hasBrightnessControl(): boolean  [API 31+]
+ hasRgbControl(): boolean  [API 31+]
+ hashCode(): int  [API 31+]
+ toString(): String  [API 31+]
+ writeToParcel(Parcel, int): void  [API 31+]
```

## LightState — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* getColor(): int  [API 31+]
* getPlayerId(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## LightState.Builder — declaration; API 31+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* build(): LightState  [API 31+]
* setColor(int): LightState.Builder  [API 31+]
* setPlayerId(int): LightState.Builder  [API 31+]
```

## LightsManager — declaration; API 31+ (3 +, 0 *, 0 ~, 0 -)

```
+ getLightState(Light): LightState  [API 31+]
+ getLights(): List  [API 31+]
+ openSession(): LightsManager.LightsSession  [API 31+]
```

## LightsManager.LightsSession — declaration; API 31+ (2 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 31+]
+ requestLights(LightsRequest): void  [API 31+]
```

## LightsRequest — declaration; API 31+ (3 +, 0 *, 0 ~, 0 -)

```
+ getLightStates(): List  [API 31+]
+ getLights(): List  [API 31+]
+ getLightsAndStates(): Map  [API 31+]
```

## LightsRequest.Builder — declaration; API 31+ (3 +, 0 *, 0 ~, 1 -)

```
- Builder()  [API 31+]
+ addLight(Light, LightState): LightsRequest.Builder  [API 31+]
+ build(): LightsRequest  [API 31+]
+ clearLight(Light): LightsRequest.Builder  [API 31+]
```


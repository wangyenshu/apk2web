# android.hardware.input

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## HostUsiVersion — declaration; API 34+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- getMajorVersion(): int  [API 34+]
- getMinorVersion(): int  [API 34+]
- hashCode(): int  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## InputManager — declaration; API 16+ (0 +, 0 *, 0 ~, 9 -)

```
- getHostUsiVersion(Display): HostUsiVersion  [API 34+]
- getInputDevice(int): InputDevice  [API 16+]
- getInputDeviceIds(): int[]  [API 16+]
- getInputDeviceViewBehavior(int): InputDevice.ViewBehavior  [API 35+]
- getMaximumObscuringOpacityForTouch(): float  [API 31+]
- isStylusPointerIconEnabled(): boolean  [API 34+]
- registerInputDeviceListener(InputManager.InputDeviceListener, Handler): void  [API 16+]
- unregisterInputDeviceListener(InputManager.InputDeviceListener): void  [API 16+]
- verifyInputEvent(InputEvent): VerifiedInputEvent  [API 30+]
```

## InputManager.InputDeviceListener — declaration; API 16+ (3 +, 0 *, 0 ~, 0 -)

```
+ onInputDeviceAdded(int): void  [API 16+]
+ onInputDeviceChanged(int): void  [API 16+]
+ onInputDeviceRemoved(int): void  [API 16+]
```


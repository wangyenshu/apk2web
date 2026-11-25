# android.hardware.location

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## GeofenceHardware — declaration; API 18-20, removed in 21 (8 +, 0 *, 0 ~, 0 -)

```
+ addGeofence(int, int, GeofenceHardwareRequest, GeofenceHardwareCallback): boolean  [API 18-20, removed in 21]
+ getMonitoringTypes(): int[]  [API 18-20, removed in 21]
+ getStatusOfMonitoringType(int): int  [API 18-20, removed in 21]
+ pauseGeofence(int, int): boolean  [API 18-20, removed in 21]
+ registerForMonitorStateChangeCallback(int, GeofenceHardwareMonitorCallback): boolean  [API 18-20, removed in 21]
+ removeGeofence(int, int): boolean  [API 18-20, removed in 21]
+ resumeGeofence(int, int, int): boolean  [API 18-20, removed in 21]
+ unregisterForMonitorStateChangeCallback(int, GeofenceHardwareMonitorCallback): boolean  [API 18-20, removed in 21]
```

## GeofenceHardwareCallback — declaration; API 18-20, removed in 21 (6 +, 0 *, 0 ~, 0 -)

```
+ GeofenceHardwareCallback()  [API 18-20, removed in 21]
+ onGeofenceAdd(int, int): void  [API 18-20, removed in 21]
+ onGeofencePause(int, int): void  [API 18-20, removed in 21]
+ onGeofenceRemove(int, int): void  [API 18-20, removed in 21]
+ onGeofenceResume(int, int): void  [API 18-20, removed in 21]
+ onGeofenceTransition(int, int, Location, long, int): void  [API 18-20, removed in 21]
```

## GeofenceHardwareMonitorCallback — declaration; API 18-20, removed in 21 (2 +, 0 *, 0 ~, 0 -)

```
+ GeofenceHardwareMonitorCallback()  [API 18-20, removed in 21]
+ onMonitoringSystemChange(int, boolean, Location): void  [API 18-20, removed in 21]
```

## GeofenceHardwareRequest — declaration; API 18-20, removed in 21 (4 +, 9 *, 0 ~, 0 -)

```
* GeofenceHardwareRequest()  [API 18-20, removed in 21]
+ createCircularGeofence(double, double, double): GeofenceHardwareRequest  [API 18-20, removed in 21]
* getLastTransition(): int  [API 18-20, removed in 21]
+ getLatitude(): double  [API 18-20, removed in 21]
+ getLongitude(): double  [API 18-20, removed in 21]
* getMonitorTransitions(): int  [API 18-20, removed in 21]
* getNotificationResponsiveness(): int  [API 18-20, removed in 21]
+ getRadius(): double  [API 18-20, removed in 21]
* getUnknownTimer(): int  [API 18-20, removed in 21]
* setLastTransition(int): void  [API 18-20, removed in 21]
* setMonitorTransitions(int): void  [API 18-20, removed in 21]
* setNotificationResponsiveness(int): void  [API 18-20, removed in 21]
* setUnknownTimer(int): void  [API 18-20, removed in 21]
```


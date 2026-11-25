# java.beans

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## IndexedPropertyChangeEvent — declaration; API 3+ (0 +, 0 *, 0 ~, 2 -)

```
- IndexedPropertyChangeEvent(Object, String, Object, Object, int)  [API 3+]
- getIndex(): int  [API 3+]
```

## PropertyChangeEvent — declaration; API 3+ (0 +, 0 *, 0 ~, 7 -)

```
- PropertyChangeEvent(Object, String, Object, Object)  [API 3+]
- getNewValue(): Object  [API 3+]
- getOldValue(): Object  [API 3+]
- getPropagationId(): Object  [API 3+]
- getPropertyName(): String  [API 3+]
- setPropagationId(Object): void  [API 3+]
- toString(): String  [API 3+]
```

## PropertyChangeListener — declaration; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ propertyChange(PropertyChangeEvent): void  [API 3+]
```

## PropertyChangeListenerProxy — declaration; API 3+ (0 +, 0 *, 0 ~, 3 -)

```
- PropertyChangeListenerProxy(String, PropertyChangeListener)  [API 3+]
- getPropertyName(): String  [API 3+]
- propertyChange(PropertyChangeEvent): void  [API 3+]
```

## PropertyChangeSupport — declaration; API 3+ (0 +, 0 *, 0 ~, 15 -)

```
- PropertyChangeSupport(Object)  [API 3+]
- addPropertyChangeListener(PropertyChangeListener): void  [API 3+]
- addPropertyChangeListener(String, PropertyChangeListener): void  [API 3+]
- fireIndexedPropertyChange(String, int, Object, Object): void  [API 3+]
- fireIndexedPropertyChange(String, int, boolean, boolean): void  [API 3+]
- fireIndexedPropertyChange(String, int, int, int): void  [API 3+]
- firePropertyChange(PropertyChangeEvent): void  [API 3+]
- firePropertyChange(String, Object, Object): void  [API 3+]
- firePropertyChange(String, boolean, boolean): void  [API 3+]
- firePropertyChange(String, int, int): void  [API 3+]
- getPropertyChangeListeners(): PropertyChangeListener[]  [API 3+]
- getPropertyChangeListeners(String): PropertyChangeListener[]  [API 3+]
- hasListeners(String): boolean  [API 3+]
- removePropertyChangeListener(PropertyChangeListener): void  [API 3+]
- removePropertyChangeListener(String, PropertyChangeListener): void  [API 3+]
```


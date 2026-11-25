# java.util.logging

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ConsoleHandler — declaration; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- ConsoleHandler()  [API 1+]
- close(): void  [API 1+]
- publish(LogRecord): void  [API 1+]
```

## ErrorManager — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- ErrorManager()  [API 1+]
- error(String, Exception, int): void  [API 1+]
```

## FileHandler — declaration; API 1+ (0 +, 0 *, 0 ~, 7 -)

```
- FileHandler()  [API 1+]
- FileHandler(String)  [API 1+]
- FileHandler(String, boolean)  [API 1+]
- FileHandler(String, int, int)  [API 1+]
- FileHandler(String, int, int, boolean)  [API 1+]
- close(): void  [API 1+]
- publish(LogRecord): void  [API 1+]
```

## Filter — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ isLoggable(LogRecord): boolean  [API 1+]
```

## Formatter — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 3 -)

```
+ Formatter()  [API 1+]
+ format(LogRecord): String  [API 1+]
- formatMessage(LogRecord): String  [API 1+]
- getHead(Handler): String  [API 1+]
- getTail(Handler): String  [API 1+]
```

## Handler — declaration; API 1+ (3 +, 11 *, 0 ~, 2 -)

```
* Handler()  [API 1+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
* getEncoding(): String  [API 1+]
* getErrorManager(): ErrorManager  [API 1+]
* getFilter(): Filter  [API 1+]
* getFormatter(): Formatter  [API 1+]
* getLevel(): Level  [API 1+]
- isLoggable(LogRecord): boolean  [API 1+]
+ publish(LogRecord): void  [API 1+]
- reportError(String, Exception, int): void  [API 1+]
* setEncoding(String): void  [API 1+]
* setErrorManager(ErrorManager): void  [API 1+]
* setFilter(Filter): void  [API 1+]
* setFormatter(Formatter): void  [API 1+]
* setLevel(Level): void  [API 1+]
```

## Level — TeaVM class library; API 1+ (6 +, 0 *, 0 ~, 4 -)

```
+ ALL [field]  [API 1+]
+ CONFIG [field]  [API 1+]
+ FINE [field]  [API 1+]
+ FINER [field]  [API 1+]
+ FINEST [field]  [API 1+]
+ INFO [field]  [API 1+]
+ Level(String, int)  [API 1+]
- Level(String, int, String)  [API 1+]
+ OFF [field]  [API 1+]
+ SEVERE [field]  [API 1+]
+ WARNING [field]  [API 1+]
+ equals(Object): boolean  [API 1+]
- getLocalizedName(): String  [API 1+]
+ getName(): String  [API 1+]
- getResourceBundleName(): String  [API 1+]
+ hashCode(): int  [API 1+]
+ intValue(): int  [API 1+]
- parse(String): Level [static]  [API 1+]
+ toString(): String  [API 1+]
```

## LogManager — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 10 -)

```
+ LogManager()  [API 1+]
- addLogger(Logger): boolean  [API 1+]
- addPropertyChangeListener(PropertyChangeListener): void  [API 3+, deprecated in 26]
- checkAccess(): void  [API 1+]
+ getLogManager(): LogManager [static]  [API 1+]
- getLogger(String): Logger  [API 1+]
- getLoggerNames(): Enumeration  [API 1+]
- getLoggingMXBean(): LoggingMXBean [static]  [API 3+]
- getProperty(String): String  [API 1+]
- readConfiguration(): void  [API 1+]
+ readConfiguration(InputStream): void  [API 1+]
- removePropertyChangeListener(PropertyChangeListener): void  [API 3+, deprecated in 26]
- reset(): void  [API 1+]
```

## LogRecord — TeaVM class library; API 1+ (19 +, 0 *, 0 ~, 6 -)

```
+ LogRecord(Level, String)  [API 1+]
+ getLevel(): Level  [API 1+]
+ getLoggerName(): String  [API 1+]
+ getMessage(): String  [API 1+]
+ getMillis(): long  [API 1+]
+ getParameters(): Object[]  [API 1+]
- getResourceBundle(): ResourceBundle  [API 1+]
- getResourceBundleName(): String  [API 1+]
+ getSequenceNumber(): long  [API 1+]
+ getSourceClassName(): String  [API 1+]
+ getSourceMethodName(): String  [API 1+]
- getThreadID(): int  [API 1+]
+ getThrown(): Throwable  [API 1+]
+ setLevel(Level): void  [API 1+]
+ setLoggerName(String): void  [API 1+]
+ setMessage(String): void  [API 1+]
+ setMillis(long): void  [API 1+]
+ setParameters(Object[]): void  [API 1+]
- setResourceBundle(ResourceBundle): void  [API 1+]
- setResourceBundleName(String): void  [API 1+]
+ setSequenceNumber(long): void  [API 1+]
+ setSourceClassName(String): void  [API 1+]
+ setSourceMethodName(String): void  [API 1+]
- setThreadID(int): void  [API 1+]
+ setThrown(Throwable): void  [API 1+]
```

## Logger — TeaVM class library; API 1+ (35 +, 0 *, 0 ~, 26 -)

```
- Logger(String, String)  [API 1+]
- addHandler(Handler): void  [API 1+]
+ config(String): void  [API 1+]
+ config(Supplier): void  [API 26+]
- entering(String, String): void  [API 1+]
- entering(String, String, Object): void  [API 1+]
- entering(String, String, Object[]): void  [API 1+]
- exiting(String, String): void  [API 1+]
- exiting(String, String, Object): void  [API 1+]
+ fine(String): void  [API 1+]
+ fine(Supplier): void  [API 26+]
+ finer(String): void  [API 1+]
+ finer(Supplier): void  [API 26+]
+ finest(String): void  [API 1+]
+ finest(Supplier): void  [API 26+]
+ getAnonymousLogger(): Logger [static]  [API 1+]
- getAnonymousLogger(String): Logger [static]  [API 1+]
- getFilter(): Filter  [API 1+]
+ getGlobal(): Logger [static]  [API 19+]
- getHandlers(): Handler[]  [API 1+]
- getLevel(): Level  [API 1+]
+ getLogger(String): Logger [static]  [API 1+]
- getLogger(String, String): Logger [static]  [API 1+]
+ getName(): String  [API 1+]
+ getParent(): Logger  [API 1+]
- getResourceBundle(): ResourceBundle  [API 1+]
- getResourceBundleName(): String  [API 1+]
- getUseParentHandlers(): boolean  [API 1+]
+ global [field]  [API 1+, deprecated in 15]
+ info(String): void  [API 1+]
+ info(Supplier): void  [API 26+]
+ isLoggable(Level): boolean  [API 1+]
+ log(Level, String): void  [API 1+]
+ log(Level, String, Object): void  [API 1+]
+ log(Level, String, Object[]): void  [API 1+]
+ log(Level, String, Throwable): void  [API 1+]
+ log(Level, Supplier): void  [API 26+]
+ log(Level, Throwable, Supplier): void  [API 26+]
+ log(LogRecord): void  [API 1+]
+ logp(Level, String, String, String): void  [API 1+]
+ logp(Level, String, String, String, Object): void  [API 1+]
+ logp(Level, String, String, String, Object[]): void  [API 1+]
+ logp(Level, String, String, String, Throwable): void  [API 1+]
+ logp(Level, String, String, Supplier): void  [API 26+]
+ logp(Level, String, String, Throwable, Supplier): void  [API 26+]
- logrb(Level, String, String, ResourceBundle, String, Object[]): void  [API 26+]
- logrb(Level, String, String, ResourceBundle, String, Throwable): void  [API 26+]
- logrb(Level, String, String, String, String): void  [API 1+, deprecated in 26]
- logrb(Level, String, String, String, String, Object): void  [API 1+, deprecated in 26]
- logrb(Level, String, String, String, String, Object[]): void  [API 1+, deprecated in 26]
- logrb(Level, String, String, String, String, Throwable): void  [API 1+, deprecated in 26]
- removeHandler(Handler): void  [API 1+]
- setFilter(Filter): void  [API 1+]
- setLevel(Level): void  [API 1+]
+ setParent(Logger): void  [API 1+]
- setResourceBundle(ResourceBundle): void  [API 26+]
- setUseParentHandlers(boolean): void  [API 1+]
+ severe(String): void  [API 1+]
+ severe(Supplier): void  [API 26+]
+ throwing(String, String, Throwable): void  [API 1+]
+ warning(String): void  [API 1+]
+ warning(Supplier): void  [API 26+]
```

## LoggingMXBean — declaration; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ getLoggerLevel(String): String  [API 1+]
+ getLoggerNames(): List  [API 1+]
+ getParentLoggerName(String): String  [API 1+]
+ setLoggerLevel(String, String): void  [API 1+]
```

## LoggingPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- LoggingPermission(String, String)  [API 1+]
```

## MemoryHandler — declaration; API 1+ (0 +, 3 *, 0 ~, 6 -)

```
* MemoryHandler()  [API 1+]
- MemoryHandler(Handler, int, Level)  [API 1+]
- close(): void  [API 1+]
- flush(): void  [API 1+]
* getPushLevel(): Level  [API 1+]
- isLoggable(LogRecord): boolean  [API 1+]
- publish(LogRecord): void  [API 1+]
- push(): void  [API 1+]
* setPushLevel(Level): void  [API 1+]
```

## SimpleFormatter — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- SimpleFormatter()  [API 1+]
- format(LogRecord): String  [API 1+]
```

## SocketHandler — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- SocketHandler()  [API 1+]
- SocketHandler(String, int)  [API 1+]
- close(): void  [API 1+]
- publish(LogRecord): void  [API 1+]
```

## StreamHandler — declaration; API 1+ (0 +, 2 *, 0 ~, 6 -)

```
* StreamHandler()  [API 1+]
- StreamHandler(OutputStream, Formatter)  [API 1+]
- close(): void  [API 1+]
- flush(): void  [API 1+]
- isLoggable(LogRecord): boolean  [API 1+]
- publish(LogRecord): void  [API 1+]
* setEncoding(String): void  [API 1+]
- setOutputStream(OutputStream): void  [API 1+]
```

## XMLFormatter — declaration; API 1+ (0 +, 0 *, 0 ~, 4 -)

```
- XMLFormatter()  [API 1+]
- format(LogRecord): String  [API 1+]
- getHead(Handler): String  [API 1+]
- getTail(Handler): String  [API 1+]
```


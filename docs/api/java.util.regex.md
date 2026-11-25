# java.util.regex

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## MatchResult — TeaVM class library; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ end(): int  [API 1+]
+ end(int): int  [API 1+]
+ group(): String  [API 1+]
+ group(int): String  [API 1+]
+ groupCount(): int  [API 1+]
+ start(): int  [API 1+]
+ start(int): int  [API 1+]
```

## Matcher — TeaVM class library; API 1+ (39 +, 0 *, 0 ~, 0 -)

```
+ appendReplacement(StringBuffer, String): Matcher  [API 1+]
+ appendReplacement(StringBuilder, String): Matcher  [API 34+]
+ appendTail(StringBuffer): StringBuffer  [API 1+]
+ appendTail(StringBuilder): StringBuilder  [API 34+]
+ end(): int  [API 1+]
+ end(String): int  [API 26+]
+ end(int): int  [API 1+]
+ find(): boolean  [API 1+]
+ find(int): boolean  [API 1+]
+ group(): String  [API 1+]
+ group(String): String  [API 26+]
+ group(int): String  [API 1+]
+ groupCount(): int  [API 1+]
+ hasAnchoringBounds(): boolean  [API 1+]
+ hasTransparentBounds(): boolean  [API 1+]
+ hitEnd(): boolean  [API 1+]
+ lookingAt(): boolean  [API 1+]
+ matches(): boolean  [API 1+]
+ pattern(): Pattern  [API 1+]
+ quoteReplacement(String): String [static]  [API 1+]
+ region(int, int): Matcher  [API 1+]
+ regionEnd(): int  [API 1+]
+ regionStart(): int  [API 1+]
+ replaceAll(Function): String  [API 34+]
+ replaceAll(String): String  [API 1+]
+ replaceFirst(Function): String  [API 34+]
+ replaceFirst(String): String  [API 1+]
+ requireEnd(): boolean  [API 1+]
+ reset(): Matcher  [API 1+]
+ reset(CharSequence): Matcher  [API 1+]
+ results(): Stream  [API 34+]
+ start(): int  [API 1+]
+ start(String): int  [API 26+]
+ start(int): int  [API 1+]
+ toMatchResult(): MatchResult  [API 1+]
+ toString(): String  [API 1+]
+ useAnchoringBounds(boolean): Matcher  [API 1+]
+ usePattern(Pattern): Matcher  [API 1+]
+ useTransparentBounds(boolean): Matcher  [API 1+]
```

## Pattern — TeaVM class library; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ asMatchPredicate(): Predicate  [API 34+]
+ asPredicate(): Predicate  [API 24+]
+ compile(String): Pattern [static]  [API 1+]
+ compile(String, int): Pattern [static]  [API 1+]
+ flags(): int  [API 1+]
+ matcher(CharSequence): Matcher  [API 1+]
+ matches(String, CharSequence): boolean [static]  [API 1+]
+ pattern(): String  [API 1+]
+ quote(String): String [static]  [API 1+]
+ split(CharSequence): String[]  [API 1+]
+ split(CharSequence, int): String[]  [API 1+]
+ splitAsStream(CharSequence): Stream  [API 24+]
+ toString(): String  [API 1+]
```

## PatternSyntaxException — TeaVM class library; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ PatternSyntaxException(String, String, int)  [API 1+]
+ getDescription(): String  [API 1+]
+ getIndex(): int  [API 1+]
+ getMessage(): String  [API 1+]
+ getPattern(): String  [API 1+]
```


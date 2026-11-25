# android.text.util

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Linkify — framework; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ Linkify()  [API 1+]
+ addLinks(Spannable, Pattern, String): boolean [static]  [API 1+]
+ addLinks(Spannable, Pattern, String, Linkify.MatchFilter, Linkify.TransformFilter): boolean [static]  [API 1+]
+ addLinks(Spannable, Pattern, String, String[], Linkify.MatchFilter, Linkify.TransformFilter): boolean [static]  [API 24+]
+ addLinks(Spannable, Pattern, String, String[], Linkify.MatchFilter, Linkify.TransformFilter, Function): boolean [static]  [API 29+]
+ addLinks(Spannable, int): boolean [static]  [API 1+]
+ addLinks(Spannable, int, Function): boolean [static]  [API 29+]
+ addLinks(TextView, Pattern, String): void [static]  [API 1+]
+ addLinks(TextView, Pattern, String, Linkify.MatchFilter, Linkify.TransformFilter): void [static]  [API 1+]
+ addLinks(TextView, Pattern, String, String[], Linkify.MatchFilter, Linkify.TransformFilter): void [static]  [API 24+]
+ addLinks(TextView, int): boolean [static]  [API 1+]
+ sPhoneNumberMatchFilter [field]  [API 1+]
+ sPhoneNumberTransformFilter [field]  [API 1+]
+ sUrlMatchFilter [field]  [API 1+]
```

## Linkify.MatchFilter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ acceptMatch(CharSequence, int, int): boolean  [API 1+]
```

## Linkify.TransformFilter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ transformUrl(Matcher, String): String  [API 1+]
```

## Rfc822Token — framework; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ Rfc822Token(String, String, String)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getAddress(): String  [API 1+]
+ getComment(): String  [API 1+]
+ getName(): String  [API 1+]
+ hashCode(): int  [API 1+]
+ quoteComment(String): String [static]  [API 1+]
+ quoteName(String): String [static]  [API 1+]
+ quoteNameIfNecessary(String): String [static]  [API 1+]
+ setAddress(String): void  [API 1+]
+ setComment(String): void  [API 1+]
+ setName(String): void  [API 1+]
+ toString(): String  [API 1+]
```

## Rfc822Tokenizer — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ Rfc822Tokenizer()  [API 1+]
+ findTokenEnd(CharSequence, int): int  [API 1+]
+ findTokenStart(CharSequence, int): int  [API 1+]
+ terminateToken(CharSequence): CharSequence  [API 1+]
+ tokenize(CharSequence): Rfc822Token[] [static]  [API 1+]
+ tokenize(CharSequence, Collection): void [static]  [API 8+]
```


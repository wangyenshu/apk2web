package a2w;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A member of a generated data class or builder whose body tools/genapi.sh derived from the declaration: getters
 * return what the class's setters, constructors or builder stored; equals, hashCode, toString and Parcelable use
 * those values. Defaults are Java's (0, false, null), not necessarily the platform's.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface Derived {}

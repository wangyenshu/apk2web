package a2w;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A member declared as the SDK declares it whose behaviour is not implemented: a method or constructor that calls
 * Stubs.hit and returns a default, or a field whose value is not known. docs/API.md counts these as missing.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface NotImplemented {}

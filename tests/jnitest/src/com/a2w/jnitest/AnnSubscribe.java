package com.a2w.jnitest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
@interface AnnSubscribe { Class<?> type() default Object.class; int priority() default 0; String[] tags() default {}; boolean sticky() default false; }

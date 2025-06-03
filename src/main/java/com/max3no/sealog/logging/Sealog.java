package com.max3no.sealog.logging;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Sealog Logging Framework
 * Created by Max3no - https://github.com/max3no/sealog-logging
 * Licensed under MIT
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sealog {
    String value() default ""; // optional custom message
}


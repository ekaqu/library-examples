package com.ekaqu.example.guice.billing.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date: 2/26/12
 * Time: 9:45 AM
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
public @interface NotOnWeekends {
}

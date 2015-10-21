package com.skt.tracking.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface TrackingData
{
    String key() default "";

    String value() default "";
}

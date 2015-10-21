package com.skt.tracking.annotation;

import com.skt.tracking.TrackingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.CLASS)
public @interface TrackScreenView
{
    String name() default "screen";

    TrackingData[] fields() default {};

    TrackingType trackingType() default TrackingType.GOOGLE_ANALYTICS;
}

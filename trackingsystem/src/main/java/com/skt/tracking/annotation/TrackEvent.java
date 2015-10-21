package com.skt.tracking.annotation;

import com.skt.tracking.TrackingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.CLASS)
public @interface TrackEvent
{
    /**
     * Category in which the event will be filed
     *
     * @return category name
     */
    String category() default "category";

    /**
     * Action associated with the event
     *
     * @return action name
     */
    String action() default "action";

    String label() default "label";

    long value() default 0;

    TrackingType trackingType() default TrackingType.GOOGLE_ANALYTICS;
}

package com.skt.tracking.aspect;

import com.skt.tracking.TrackingType;
import com.skt.tracking.annotation.TrackEvent;
import com.skt.tracking.ga.GaManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class EventAspect
{
    public static EventAspect aspectOf()
    {
        return new EventAspect();
    }

    @Pointcut("within(@com.skt.tracking.annotation.TrackEvent *)")
    public void withinAnnotatedClass()
    {
    }

    @Pointcut("execution(@com.skt.tracking.annotation.TrackEvent * *(..)) || methodInsideAnnotatedType()")
    public void method()
    {
    }

    @Pointcut("execution(@com.skt.tracking.annotation.TrackEvent *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor()
    {
    }

    @Pointcut("execution(* *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType()
    {
    }

    @Pointcut("execution(*.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType()
    {
    }

    @Around("method() || constructor()")
    public Object sendTrack(ProceedingJoinPoint joinPoint) throws Throwable
    {
        TrackEvent annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(TrackEvent.class);
        if(annotation.trackingType() == TrackingType.GOOGLE_ANALYTICS)
        {
            GaManager.getInstance().trackEvent(annotation.category(), annotation.action(), annotation.label(), annotation.value());
        }

        return joinPoint.proceed();
    }
}

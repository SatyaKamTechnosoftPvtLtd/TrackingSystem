package com.skt.tracking.aspect;

import com.skt.tracking.TrackingType;
import com.skt.tracking.annotation.TrackScreenView;
import com.skt.tracking.annotation.TrackingData;
import com.skt.tracking.ga.GaManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class ScreenViewAspect
{
    public static ScreenViewAspect aspectOf()
    {
        return new ScreenViewAspect();
    }

    @Pointcut("within(@com.skt.tracking.annotation.TrackScreenView *)")
    public void withinAnnotatedClass()
    {
    }

    @Pointcut("execution(@com.skt.tracking.annotation.TrackScreenView * *(..)) || methodInsideAnnotatedType()")
    public void method()
    {
    }

    @Pointcut("execution(@com.skt.tracking.annotation.TrackScreenView *.new(..)) || constructorInsideAnnotatedType()")
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
        TrackScreenView annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(TrackScreenView.class);
        TrackingData[] fields = annotation.fields();
        Map<String, String> fieldMap = new HashMap<>();
        if(fields != null)
        {
            for(TrackingData dataField : fields)
            {
                fieldMap.put(dataField.key(), dataField.value());
            }
        }
        if(annotation.trackingType() == TrackingType.GOOGLE_ANALYTICS)
        {
            GaManager.getInstance().trackScreen(annotation.name(), fieldMap);
        }

        return joinPoint.proceed();
    }
}

package com.dorothy.web.filter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;


@Aspect
@Component
public class TimeAspect {

    @Around(value = "execution(* com.dorothy.DemoApplication.*(..))")
    public Object handleControllerTime(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aspect start");
        Long start = new Date().getTime();
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }

        Object result = pjp.proceed();
        System.out.println("time aspect 耗时:"+(new Date().getTime()-start));
        System.out.println("aspect finish");
        return result;
    }
}

package com.rgarcia.w2m.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Slf4j
@Component
@Aspect
public class TimedAspect {

    @Pointcut("@annotation(com.rgarcia.w2m.common.Timed)")
    public void timedPointcut(){}

    @Around("timedPointcut()")
    public Object timedAllMethodCallsAdviceAround(ProceedingJoinPoint pjp) throws Throwable{
        LocalDateTime startAt = LocalDateTime.now();
        log.info("target "+pjp.getTarget().toString());
        log.info("method "+pjp.getSignature().getName());

        log.info("startAt "+startAt.toString());
        Object retVal = pjp.proceed();
        LocalDateTime endAt = LocalDateTime.now();
        Duration duration = Duration.between(startAt, LocalDateTime.now());
        log.info("endAt "+endAt.toString());
        log.info("duration "+duration.toString());
        return retVal;
    }

}

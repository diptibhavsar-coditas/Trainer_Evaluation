package com.example.Ecommerce.aspect;

import com.example.Ecommerce.exception.UnAuthorizedAccessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around("execution(* com.example.ecommerce.service.impl..(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        long timeTaken = endTime - startTime;

        log.info("Method {} executed in {} ms",
                joinPoint.getSignature().getName(),
                timeTaken);

        return result;
    }
}


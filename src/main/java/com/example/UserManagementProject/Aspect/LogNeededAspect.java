package com.example.UserManagementProject.Aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Slf4j
@Aspect
@Configuration
@Order(2)
public class LogNeededAspect {


    @Pointcut("@annotation(com.example.UserManagementProject.Annotation.LogNeeded)")
    public void methodLogging() {
    }

    @Around("methodLogging()")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method: " + joinPoint.getSignature().toString() + " is invoked!");
        joinPoint.proceed();
        log.info("Method Invocation is finished!");
    }

    @Pointcut("execution(* com.example.UserManagementProject.Service.*.*(..))")
    public void allServicesLogging() {
    }

    @Before("allServicesLogging()")
    public void _allServiceLogging(JoinPoint jp) {
        log.info("Method: " + jp.getSignature().toString() + " is going to invoke!");
    }

    @After("allServicesLogging()")
    public void __allServiceLogging(JoinPoint jp) {
        log.info("Method: " + jp.getSignature().toString() + " was invoked! Either Threw exception or completed completely");
    }

    @AfterReturning("allServicesLogging()")
    public void ___allServiceLogging(JoinPoint jp) {
        log.info("Method: " + jp.getSignature().toString() + " was invoked! Completed completely");
    }

    @AfterThrowing("allServicesLogging()")
    public void ____allServiceLogging(JoinPoint jp) {
        log.info("Method: " + jp.getSignature().toString() + " was invoked! Threw exception");
    }


}

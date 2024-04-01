package com.example.orderservice.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class LoggerAspect {

  @Pointcut("execution(public * com.example.orderservice..*(..))")
  public void execMethodsOrderAndUserService(){}

  @Before("execMethodsOrderAndUserService()")
  public void beforeExecMethodsOrderAndUserService(JoinPoint point){
    log.info("Before methods: " + point.getSignature().getName() +
      " Args:" + Arrays.toString(point.getArgs()) +
      " Location: " + point.getSignature().getDeclaringType().getSimpleName());
  }

  @After("execMethodsOrderAndUserService()")
  public void afterExecMethodsOrderAndUserService(JoinPoint point){
    log.info("After: " + point.toString());
  }
}

package com.example.orderservice.aspect;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class LoggerAspect {

  @Pointcut("execution(public * com.example.orderservice.service.*.*(..))")
  public void execMethodsOrderAndUserService() {
  }

  @Before("execMethodsOrderAndUserService()")
  public void beforeExecMethodsOrderAndUserService(JoinPoint point) {
    log.log(Level.toLevel("INFO METHOD ASPECT"), "Before methods: " + point.getSignature().getName() +
      " Args:" + Arrays.toString(point.getArgs()) +
      " Location: " + point.getSignature().getDeclaringType().getSimpleName());
  }

  @After("execMethodsOrderAndUserService()")
  public void afterExecMethodsOrderAndUserService(JoinPoint point) {
    log.log(Level.toLevel("INFO METHOD ASPECT"),"After methods: " + point.getSignature().getName() +
      " Args:" + Arrays.toString(point.getArgs()) +
      " Location: " + point.getSignature().getDeclaringType().getSimpleName());
  }

}
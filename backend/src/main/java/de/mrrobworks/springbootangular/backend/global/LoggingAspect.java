package de.mrrobworks.springbootangular.backend.global;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Around("execution(* de.mrrobworks.springbootangular.backend.controller.*.*(..))")
  public Object logTime(final ProceedingJoinPoint joinPoint) throws Throwable {
    final Object ret;
    final long startTime = System.currentTimeMillis();
    ret = joinPoint.proceed();
    final long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
    log.info("Method {} took {} sec.", joinPoint.getSignature(), seconds);
    return ret;
  }
}

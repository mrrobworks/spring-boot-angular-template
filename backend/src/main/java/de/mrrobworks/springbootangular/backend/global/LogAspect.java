package de.mrrobworks.springbootangular.backend.global;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class LogAspect {

  @Around("execution(* de.mrrobworks.springbootangular.backend.*.*ServiceImpl.*(..))")
  public static Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Object ret;
    long startTime = System.currentTimeMillis();
    ret = joinPoint.proceed();
    long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
    log.info("Method \"{}\" took {} sec.", joinPoint.getSignature(), seconds);
    return ret;
  }

  @AfterThrowing(
      value = "execution(* de.mrrobworks.springbootangular.backend.*.*ServiceImpl.*(..))",
      throwing = "e")
  public static void logException(JoinPoint joinPoint, Throwable e) {
    log.error(joinPoint + " -> " + e.getMessage(), e);
  }
}

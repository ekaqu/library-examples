package com.ekaqu.example.aspectj;

import com.google.common.collect.ImmutableList;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 2/25/12
 * Time: 6:04 PM
 */
@Aspect
public class CacheModifier {
  public static final Logger LOG = LoggerFactory.getLogger(CacheModifier.class);

  @Pointcut("call(void MockCache.set(*,*)) && args(key, value)")
  public void onSet(String key, String value) {}

  @Around("onSet(key, value)")
  public void changeSet(ProceedingJoinPoint joinPoint, String key, String value)
    throws Throwable {
    LOG.info("key = {}, value = {}", key, value);
    joinPoint.proceed(new Object[]{key, value + getClass().getSimpleName()});
  }
  
  @Before("call(* MockCache.*(..))")
  public void printMethod(JoinPoint joinPoint) {
    LOG.info("printMethod");
    LOG.info("\t - Kind :\t\t{}", joinPoint.getKind());
    LOG.info("\t - Signature :\t{}", joinPoint.getSignature());
    LOG.info("\t - Part :\t\t{}", joinPoint.getStaticPart());
    LOG.info("\t - Location :\t{}", joinPoint.getSourceLocation());
    LOG.info("\t - Target :\t\t{}", joinPoint.getTarget());
    LOG.info("\t - This :\t\t{}", joinPoint.getThis());
    LOG.info("\t - Args :\t\t{}",
      ImmutableList.copyOf(joinPoint.getArgs()));
  }
}

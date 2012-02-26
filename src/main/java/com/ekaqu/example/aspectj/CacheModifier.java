package com.ekaqu.example.aspectj;

import com.google.common.collect.ImmutableList;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Date: 2/25/12
 * Time: 6:04 PM
 */
@Aspect
public class CacheModifier {

  @Pointcut("call(void MockCache.set(*,*)) && args(key, value)")
  public void onSet(String key, String value) {}

  @Around("onSet(key, value)")
  public void changeSet(ProceedingJoinPoint joinPoint, String key, String value)
    throws Throwable {
    System.out.println("key = " + key + ", value = " + value);
    joinPoint.proceed(new Object[]{key, value + getClass().getSimpleName()});
  }
  
  @Before("call(* MockCache.*(..))")
  public void printMethod(JoinPoint joinPoint) {
    System.out.println("printMethod");
    System.out.println("\t - Kind :\t\t" + joinPoint.getKind());
    System.out.println("\t - Signature :\t" + joinPoint.getSignature());
    System.out.println("\t - Part :\t\t" + joinPoint.getStaticPart());
    System.out.println("\t - Location :\t" + joinPoint.getSourceLocation());
    System.out.println("\t - Target :\t\t" + joinPoint.getTarget());
    System.out.println("\t - This :\t\t" + joinPoint.getThis());
    System.out.println("\t - Args :\t\t" +
      ImmutableList.copyOf(joinPoint.getArgs()));
  }
}

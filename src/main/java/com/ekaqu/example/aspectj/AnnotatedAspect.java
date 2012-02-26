package com.ekaqu.example.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Date: 2/25/12
 * Time: 2:57 PM
 */
@Aspect
public class AnnotatedAspect {

  @Pointcut("call(void AspectDemo.*(..))")
  public void listOperation() {}

  @Before("listOperation()")
  public void print() {
    System.out.println("--- Print");
  }

}

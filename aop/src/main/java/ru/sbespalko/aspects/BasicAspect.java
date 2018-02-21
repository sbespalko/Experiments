/* BasicAspect.java */
package ru.sbespalko.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BasicAspect {


  @Pointcut("execution(* ru.sbespalko..doWork(int))")
  public void doSomething() {}

  @After("doSomething()")
  public void beforeCall(JoinPoint joinPoint) {


  }

  @After("execution(* ru.sbespalko.*.doWork(..))")
  public void afterPrintlnCall() {
    StringBuilder s = new StringBuilder(10 + 2 + "ABC" + 4 + 5);
    s.append(s.delete(3, 6));
    System.out.println(s);
  }
}